package com.farmersol.backend.Repository;

import com.farmersol.backend.Domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
