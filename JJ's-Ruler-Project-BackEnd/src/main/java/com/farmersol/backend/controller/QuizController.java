package com.farmersol.backend.controller;

import com.farmersol.backend.entity.Quiz;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/quizzes")
public interface QuizController {

    @GetMapping
    ResponseEntity<List<Quiz>> getAllQuizzes();

    @GetMapping("/{id}")
    ResponseEntity<Optional<Quiz>> getQuizById(@PathVariable("id") Long id);

    @PostMapping
    ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz);

    @PutMapping("/{id}")
    ResponseEntity<Quiz> updateQuiz(@PathVariable("id") Long id, @RequestBody Quiz quiz);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteQuiz(@PathVariable("id") Long id);
}
