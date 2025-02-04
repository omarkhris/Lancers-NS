package com.farmersol.backend.service;

import com.farmersol.backend.entity.Analysis;

import java.util.List;
import java.util.Optional;

public interface AnalysisService {


    List<Analysis> getAllAnalysis();

    Optional<Analysis> getAnalysisById(Long id);

    Analysis saveAnalysis(Analysis analysis);
    Analysis updateAnalysis(Long id, Analysis analysis);

    void deleteAnalysis(Long id);

}
