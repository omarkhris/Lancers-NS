package com.farmersol.backend.Repository;

import com.farmersol.backend.Domain.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionnaireRepository extends JpaRepository <Questionnaire, Long> {
}
