package com.farmersol.backend.controller;

import com.farmersol.backend.entity.Analysis;
import com.farmersol.backend.service.AnalysisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class AnalysisControllerImpl implements AnalysisController {

    private final AnalysisService analysisService;

    public AnalysisControllerImpl(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }
    @Override
    public ResponseEntity<List<Analysis>> getAllAnalysis() {
        return ResponseEntity.ok(analysisService.getAllAnalysis());
    }

    @Override
    public ResponseEntity<Optional<Analysis>> getAnalysisById(Long id) {
        return ResponseEntity.ok(analysisService.getAnalysisById(id));
    }

    @Override
    public ResponseEntity<Analysis> saveAnalysis(Analysis analysis) {
        return ResponseEntity.ok(analysisService.saveAnalysis(analysis));
    }

    @Override
    public ResponseEntity<Analysis> updateAnalysis(Long id, Analysis updatedAnalysis) {
        return ResponseEntity.ok(analysisService.updateAnalysis(id, updatedAnalysis));
    }

    @Override
    public ResponseEntity<Void> deleteAnalysis(Long id) {
        analysisService.deleteAnalysis(id);
        return ResponseEntity.noContent().build();
    }
}