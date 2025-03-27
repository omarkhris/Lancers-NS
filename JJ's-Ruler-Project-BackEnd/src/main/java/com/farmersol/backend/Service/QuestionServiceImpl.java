package com.farmersol.backend.service;
import com.farmersol.backend.entity.Option;
import com.farmersol.backend.entity.Question;
import com.farmersol.backend.entity.QuestionType;
import com.farmersol.backend.repository.OptionRepository;
import com.farmersol.backend.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final OptionRepository optionRepository;
    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository, OptionRepository optionRepository) {
        this.questionRepository = questionRepository;
        this.optionRepository = optionRepository;
    }


    public List<Question>  getQuestionsByQuestionnaire(Long questionnaireId) {
        return questionRepository.findByQuestionnaireId(questionnaireId);
    }

    @Override
    public Optional<Question> getQuestionById(Long questionId){
        return questionRepository.findById(questionId)
                .map(question -> {
                    question.getOptions().size(); // Force Hibernate to load options
                    return question;
                });
    }

    @Override
    public Question saveQuestion(Question question) {
        return null;
    }

    @Override
    public Question createQuestion(Question question) {
        // Save the question to get its ID assigned
//        question = questionRepository.save(question);
//        System.out.println("Saved Question ID: " + question.getId());
//        System.out.println(question.getQuestionText());
//        System.out.println(question.getQuestionType());
//        System.out.println(question.getOptions().size());
//        question.getOptions().forEach(option -> System.out.println(option.getOptionText()));
//        // Check if the question type is MCQ (Multiple Choice)
//        if (question.getQuestionType() == QuestionType.MCQ) {
//            // Create options for MCQ by looping over the options (array of strings)
//            List<Option> options = new ArrayList<>();
//
//            for (Option option : question.getOptions()) {
//                Option new_option = new Option(option.getOptionText(), question);
//                options.add(optionRepository.save(new_option));
//                System.out.println("Saved Option: " + new_option.getOptionText());
//            }
//
//            // Set the options back on the question (Optional, depending on your needs)
//            question.setOptions(options);
//        }


        // If it's not MCQ (for example, text-based), we don't need to do anything with options
        // Just return the saved question (with no options for non-MCQ types)
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
        questionRepository.findAll().forEach(question -> System.out.println(question.getOptions()));
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