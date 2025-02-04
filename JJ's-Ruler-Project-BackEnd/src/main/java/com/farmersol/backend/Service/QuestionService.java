package com.farmersol.backend.service;

import com.farmersol.backend.entity.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    Question createQuestion(Question question);
    Optional<Question> getQuestionById(Long id);
    Question saveQuestion(Question question);
    Question updateQuestion(Long id, Question updatedQuestion);
    List<Question> getAllQuestions();
    List<Question> getQuestionsByQuestionnaireId(Long questionnaireId);
    void deleteQuestionById(Long id);
}
