package com.farmersol.backend.controller;

import com.farmersol.backend.entity.Quiz;
import com.farmersol.backend.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class QuizControllerImpl implements QuizController{

    @Autowired
    private final QuizService quizService;
    public QuizControllerImpl(QuizService quizService) {
        this.quizService = quizService;
    }


    @Override
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }

    @Override
    public ResponseEntity<Optional<Quiz>> getQuizById(Long id) {
        return ResponseEntity.ok(quizService.getQuizById(id));
    }

    @Override
    public ResponseEntity<Quiz> createQuiz(Quiz quiz) {
        return ResponseEntity.ok(quizService.createQuiz(quiz));
    }

    @Override
    public ResponseEntity<Quiz> updateQuiz(Long id, Quiz quiz) {
        return ResponseEntity.ok(quizService.updateQuiz(id, quiz));
    }

    @Override
    public ResponseEntity<Void> deleteQuiz(Long id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.noContent().build();

    }
}
