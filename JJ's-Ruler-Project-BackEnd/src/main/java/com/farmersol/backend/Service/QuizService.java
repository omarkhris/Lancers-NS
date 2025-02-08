package com.farmersol.backend.service;

import com.farmersol.backend.entity.Farmer_User;
import com.farmersol.backend.entity.Questionnaire;
import com.farmersol.backend.entity.Quiz;
import com.farmersol.backend.repository.QuizRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public Quiz startQuiz(Farmer_User farmerUser, Questionnaire questionnaire) {
        Quiz quiz = new Quiz(farmerUser, questionnaire, LocalDateTime.now(), "IN_PROGRESS");
        return quizRepository.save(quiz);
    }

    public Optional<Quiz> getQuizById(Long id) {
        return quizRepository.findById(id);
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Quiz createQuiz(Quiz quiz) {
        quiz.setStartTime(LocalDateTime.now());
        quiz.setStatus("IN_PROGRESS");
        return quizRepository.save(quiz);
    }

    public Quiz updateQuiz(Long id, Quiz updatedQuiz) {
        return quizRepository.findById(id).map(quiz -> {
            quiz.setQuestionnaire(updatedQuiz.getQuestionnaire());
            quiz.setStatus(updatedQuiz.getStatus());
            quiz.setScore(updatedQuiz.getScore());
            return quizRepository.save(quiz);
        }).orElse(null);
    }

    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }

    public void completeQuiz(Long quizId) {
        quizRepository.findById(quizId).ifPresent(quiz -> {
            quiz.setEndTime(LocalDateTime.now());
            quiz.setStatus("COMPLETED");
            quiz.setScore(quiz.getScore() + 1);
            quizRepository.save(quiz);
        });
    }
}
