package com.farmersol.backend.controller;

import com.farmersol.backend.entity.Analysis;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/analysis")
public interface AnalysisController {
    @GetMapping
    ResponseEntity<List<Analysis>> getAllAnalysis();
    @GetMapping("/{id}")
    ResponseEntity<Optional<Analysis>> getAnalysisById(@PathVariable Long id);
    @PostMapping
    ResponseEntity<Analysis> saveAnalysis(@RequestBody Analysis analysis);
    @PostMapping("/{id}")
    ResponseEntity<Analysis> updateAnalysis(@PathVariable Long id, @RequestBody Analysis updatedAnalysis);
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteAnalysis(@PathVariable Long id);
}
