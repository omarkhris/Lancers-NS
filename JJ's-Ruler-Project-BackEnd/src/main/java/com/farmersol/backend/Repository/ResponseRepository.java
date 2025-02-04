package com.farmersol.backend.repository;

import com.farmersol.backend.entity.Farmer_User;
import com.farmersol.backend.entity.Response;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponseRepository extends JpaRepository <Response, Long> {

   List<Response> findByQuizId(Long quizId);
   // fetch responses for a specific quiz
   List<Response> findByFarmerUserId(Long userId);
//    List<Response> findByQuestionId(Long );

   List<Response>  findByFarmerUserIdAndQuizId(Long user, Long quizId);

   List<Response> findByFarmerUser(Farmer_User farmerUser);
}
