package com.farmersol.backend.Repository;

import com.farmersol.backend.Domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {


    List<Question> findByQuestionnaireId(Long questionnaireId);
}
