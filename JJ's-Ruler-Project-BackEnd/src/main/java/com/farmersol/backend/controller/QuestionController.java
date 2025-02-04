package com.farmersol.backend.controller;

import com.farmersol.backend.entity.Question;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/questions")
public interface QuestionController {

    @GetMapping
    ResponseEntity<List<Question>> getAllQuestions();

    @GetMapping("/{id}")
    ResponseEntity<Optional<Question>> getQuestionById(@PathVariable Long id);

    @PostMapping
    ResponseEntity<Question> createQuestion(@RequestBody Question question);

    @PostMapping("/{id}")
    ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question question);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteQuestion(@PathVariable Long id);

}
