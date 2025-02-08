package com.farmersol.backend.controller;

import com.farmersol.backend.entity.Questionnaire;
import com.farmersol.backend.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class QuestionnaireControllerImpl implements QuestionnaireController {
    @Autowired
    private final QuestionnaireService questionnaireService;

    public QuestionnaireControllerImpl(QuestionnaireService questionnaireService) {
        this.questionnaireService = questionnaireService;
    }


    @Override
    public ResponseEntity<List<Questionnaire>> getAllQuestionnaires() {
        return ResponseEntity.ok(questionnaireService.getAllQuestionnaires());
    }

    @Override
    public ResponseEntity<Optional<Questionnaire>> getQuestionnaireById(Long id) {
        return ResponseEntity.ok(questionnaireService.getQuestionnaireById(id));
    }

    @Override
    public ResponseEntity<Questionnaire> createQuestionnaire(Questionnaire questionnaire) {
        return ResponseEntity.ok(questionnaireService.createQuestionnaire(questionnaire));
    }

    @Override
    public ResponseEntity<Questionnaire> updateQuestionnaire(Long id, Questionnaire questionnaire) {
        return ResponseEntity.ok(questionnaireService.updateQuestionnaire(id, questionnaire));
    }

    @Override
    public ResponseEntity<Void> deleteQuestionnaire(Long id) {
        questionnaireService.deleteQuestionnaire(id);
        return ResponseEntity.noContent().build();
    }
}
