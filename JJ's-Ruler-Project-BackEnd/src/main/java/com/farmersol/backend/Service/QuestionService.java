package com.farmersol.backend.Service;
import com.farmersol.backend.Domain.Question;
import com.farmersol.backend.Repository.QuestionRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {


    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    public List<Question>  getQuestionsByQuestionnaire(Long questionnaireId) {
        return questionRepository.findByQuestionnaireId(questionnaireId);
    }


    public Optional<Question> getQuestionById(Long questionId){
        return questionRepository.findById(questionId);
    }


    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

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


    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }

}