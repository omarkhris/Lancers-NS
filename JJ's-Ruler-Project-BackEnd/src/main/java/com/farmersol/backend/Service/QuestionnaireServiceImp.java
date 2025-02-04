package com.farmersol.backend.service;
import com.farmersol.backend.entity.Questionnaire;
import com.farmersol.backend.repository.QuestionnaireRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionnaireServiceImp implements QuestionnaireService{


    private final QuestionnaireRepository questionnaireRepository;

    public QuestionnaireServiceImp(QuestionnaireRepository questionnaireRepository) {
        this.questionnaireRepository = questionnaireRepository;
    }
    @Override
    public List<Questionnaire> getAllQuestionnaires() {
        return questionnaireRepository.findAll();
    }
    @Override
    public Optional<Questionnaire> getQuestionnaireById(Long id) {
        return questionnaireRepository.findById(id);
    }

    @Override
    public Questionnaire saveQuestionnaire(Questionnaire questionnaire) {
        return null;
    }

    @Override
    public Questionnaire createQuestionnaire(Questionnaire questionnaire) {
        return questionnaireRepository.save(questionnaire);
    }



    @Override
    public Questionnaire updateQuestionnaire(Long id, Questionnaire questionnaire) {
        Optional<Questionnaire> questionnaireOptional = questionnaireRepository.findById(id);
        if (questionnaireOptional.isPresent()) {
            Questionnaire questionnaireToUpdate = questionnaireOptional.get();
            questionnaireToUpdate = questionnaire;
            questionnaireToUpdate = questionnaireRepository.save(questionnaireToUpdate);
            return questionnaireToUpdate;
        }
        return createQuestionnaire(questionnaire);
    }

    @Override
    public void deleteQuestionnaire(Long id) {
        questionnaireRepository.deleteById(id);
    }


}
