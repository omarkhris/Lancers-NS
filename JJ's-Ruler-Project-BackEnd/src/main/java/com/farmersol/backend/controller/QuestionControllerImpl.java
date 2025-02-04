package com.farmersol.backend.controller;

import com.farmersol.backend.entity.Question;
import com.farmersol.backend.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class QuestionControllerImpl implements QuestionController {
    @Autowired
    private final QuestionService questionService;

    public QuestionControllerImpl(QuestionService questionService) {
        this.questionService = questionService;
    }


    @Override
    public ResponseEntity<List<Question>> getAllQuestions() {
        return ResponseEntity.ok(questionService.getAllQuestions());
    }

    @Override
    public ResponseEntity<Optional<Question>> getQuestionById(Long id) {
        return ResponseEntity.ok(questionService.getQuestionById(id));
    }

    @Override
    public ResponseEntity<Question> createQuestion(Question question) {
        return ResponseEntity.ok(questionService.createQuestion(question));
    }

    @Override
    public ResponseEntity<Question> updateQuestion(Long id, Question question) {
        return ResponseEntity.ok(questionService.updateQuestion(id, question));
    }

    @Override
    public ResponseEntity<Void> deleteQuestion(Long id) {
        questionService.deleteQuestionById(id);
        return ResponseEntity.noContent().build();
    }
}
