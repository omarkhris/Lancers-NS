package com.farmersol.backend.service;

import com.farmersol.backend.entity.Questionnaire;

import java.util.List;
import java.util.Optional;

public interface QuestionnaireService {
    Questionnaire createQuestionnaire(Questionnaire questionnaire);
    List<Questionnaire> getAllQuestionnaires();
    Optional<Questionnaire> getQuestionnaireById(Long id);
    Questionnaire saveQuestionnaire(Questionnaire questionnaire);
    Questionnaire updateQuestionnaire(Long id, Questionnaire questionnaire);
    void deleteQuestionnaire(Long id);
}
