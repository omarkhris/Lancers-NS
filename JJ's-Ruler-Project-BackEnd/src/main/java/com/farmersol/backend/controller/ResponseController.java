package com.farmersol.backend.controller;

import com.farmersol.backend.entity.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/responses")
public interface ResponseController {

    @GetMapping
    ResponseEntity<List<Response>> getAllResponses();

    @GetMapping("/user/{userId}")
    ResponseEntity<List<Response>> getResponseByUserId(@PathVariable("userId") Long userId);

    @PostMapping("/{userId}/{quizId}/{questionId}/{optionId}/")
    ResponseEntity<Response> createResponse(@PathVariable("userId") Long userId, @PathVariable("quizId") Long quizId,
                                            @PathVariable("optionId") Long optionId, @PathVariable("questionId") Long questionId, @RequestBody Response response);

    @PutMapping("/{id}")
    ResponseEntity<Response> updateResponse(@PathVariable("id") Long id, @RequestBody Response updatedResponse);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteResponse(@PathVariable("id") Long id);
}
