package com.farmersol.backend.service;
import com.farmersol.backend.entity.Question;
import com.farmersol.backend.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {


    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    public List<Question>  getQuestionsByQuestionnaire(Long questionnaireId) {
        return questionRepository.findByQuestionnaireId(questionnaireId);
    }

    @Override
    public Optional<Question> getQuestionById(Long questionId){
        return questionRepository.findById(questionId);
    }

    @Override
    public Question saveQuestion(Question question) {
        return null;
    }

    @Override
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }
    @Override
    public Question updateQuestion(Long questionId, Question question) {
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if (questionOptional.isPresent()) {
            Question questionToUpdate = questionOptional.get();
            questionToUpdate = question;
            questionRepository.save(questionToUpdate);
            return questionToUpdate;
        }

        return createQuestion(question);
    }

    //*//*to be altered later based on data retrieval need
    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
    //*//*
    @Override
    public List<Question> getQuestionsByQuestionnaireId(Long questionnaireId) {
        return questionRepository.findByQuestionnaireId(questionnaireId);
    }

    @Override
    public void deleteQuestionById(Long questionId) {
        questionRepository.deleteById(questionId);
    }

}