package com.farmersol.backend.Service;
import com.farmersol.backend.Domain.Questionnaire;
import com.farmersol.backend.Repository.QuestionnaireRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionnaireService {


    private final QuestionnaireRepository questionnaireRepository;

    public QuestionnaireService(QuestionnaireRepository questionnaireRepository) {
        this.questionnaireRepository = questionnaireRepository;
    }

    public List<Questionnaire> getAllQuestionnaires() {
        return questionnaireRepository.findAll();
    }

    public Optional<Questionnaire> getQuestionnaireById(Long id) {
        return questionnaireRepository.findById(id);
    }

    public Questionnaire createQuestionnaire(Questionnaire questionnaire) {
        return questionnaireRepository.save(questionnaire);
    }


    public void deleteQuestionnaireById(Long id) {
        questionnaireRepository.deleteById(id);
    }

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


}
