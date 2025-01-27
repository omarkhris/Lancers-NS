package com.farmersol.backend.Repository;

import com.farmersol.backend.Domain.Response;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponseRepository extends JpaRepository <Response, Long> {

//    List<Response> findByQuizId(Long quizId);  // fetch responses for a specific quiz
//    List<Response> findByUserId(Long userId);
//    List<Response> findByQuestionId(Long );
}
