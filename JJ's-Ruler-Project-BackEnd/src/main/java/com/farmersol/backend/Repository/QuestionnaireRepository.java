package com.farmersol.backend.repository;

import com.farmersol.backend.entity.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionnaireRepository extends JpaRepository <Questionnaire, Long> {
}
