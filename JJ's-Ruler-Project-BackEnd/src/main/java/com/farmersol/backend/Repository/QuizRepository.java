package com.farmersol.backend.Repository;

import com.farmersol.backend.Domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
