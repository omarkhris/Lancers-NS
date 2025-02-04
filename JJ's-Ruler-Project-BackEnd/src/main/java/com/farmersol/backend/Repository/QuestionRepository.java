package com.farmersol.backend.repository;

import com.farmersol.backend.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {


    List<Question> findByQuestionnaireId(Long questionnaireId);
}
