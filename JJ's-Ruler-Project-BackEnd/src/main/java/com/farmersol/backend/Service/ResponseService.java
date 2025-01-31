package com.farmersol.backend.Service;


import com.farmersol.backend.Domain.*;
import com.farmersol.backend.Repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResponseService {

    private final ResponseRepository responseRepository;
    private final UserRepository userRepository;
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;


    public ResponseService(ResponseRepository responseRepository, QuizRepository quizRepository,
                           UserRepository userRepository, QuestionRepository questionRepository,
                           OptionRepository optionRepository) {
        this.responseRepository = responseRepository;
        this.quizRepository = quizRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.optionRepository = optionRepository;
    }


    public List<Response> getResponsesByQuiz(Long quizId, Long userId){
        return responseRepository.findByFarmerUserIdAndQuizId(quizId, userId);
    }


    public Response saveResponse(Long userId, Long quizId, Long questionId, Long optionId,
                                 String answerText) {
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        Optional<Question> question = questionRepository.findById(questionId);
        Optional<Farmer_User> user = userRepository.findById(userId);
        if(user.isEmpty() || quiz.isEmpty() || question.isEmpty()){
            throw new IllegalArgumentException("Farmer_User, quiz and question are required");
        }

        Response response = new Response();
        response.setFarmerUser(user.get());
        response.setQuiz(quiz.get());
        response.setQuestion(question.get());
        response.setQuestionType(question.get().getQuestionType());

        if(question.get().getQuestionType() == QuestionType.MCQ){
            if(optionId == null){
                throw new IllegalArgumentException("MCQ must have a selected option.");
            }
            response.setSelectedOption(optionRepository.findById(optionId).orElseThrow(
                    () -> new IllegalArgumentException("Option not found")));
        }


        if(question.get().getQuestionType() == QuestionType.TEXT){
            if(answerText == null || answerText.isEmpty()){
                throw new IllegalArgumentException("Text must have a valid answer.");
            }
            response.setAnswerText(answerText);
        }
        return responseRepository.save(response);
    }



    public List<Response> getResponsesByUser(Long userId){
        return responseRepository.findByFarmerUserId(userId);
    }

    public List<Response> getResponsesByUserAndQuiz(Long userId, Long quizId){
        return responseRepository.findByFarmerUserIdAndQuizId(userId, quizId);
    }
}
