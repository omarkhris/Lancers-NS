package com.farmersol.backend.controller;

import com.farmersol.backend.entity.Questionnaire;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/questionnaires")
public interface QuestionnaireController {

    @GetMapping
    ResponseEntity<List<Questionnaire>> getAllQuestionnaires();


    @GetMapping("/{id}")
    ResponseEntity<Optional<Questionnaire>> getQuestionnaireById(@PathVariable("id") Long id);

    @PostMapping
    ResponseEntity<Questionnaire> createQuestionnaire(@RequestBody Questionnaire questionnaire);

    @PutMapping("/{id}")
    ResponseEntity<Questionnaire> updateQuestionnaire(@PathVariable("id") Long id, @RequestBody Questionnaire questionnaire);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteQuestionnaire(@PathVariable("id") Long id);

}
