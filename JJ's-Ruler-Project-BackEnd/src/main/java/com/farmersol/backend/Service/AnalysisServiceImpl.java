package com.farmersol.backend.service;


import com.farmersol.backend.entity.Analysis;
import com.farmersol.backend.entity.Farmer_User;
import com.farmersol.backend.entity.Response;
import com.farmersol.backend.repository.AnalysisRepository;
import com.farmersol.backend.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class AnalysisServiceImpl implements AnalysisService {

    @Autowired
    private final ResponseRepository responseRepository;

    private final AnalysisRepository analysisRepository;

    private final RestTemplate restTemplate;

    public AnalysisServiceImpl(ResponseRepository responseRepository, AnalysisRepository analysisRepository, RestTemplate restTemplate) {
        this.responseRepository = responseRepository;
        this.analysisRepository = analysisRepository;
        this.restTemplate = restTemplate;
    }


    public String analyzeResponse(Long quizId) {
        List <Response> responses = responseRepository.findByQuizId(quizId);


        String mlApiUrl = "http://localhost:8080/api/analysis";
        String analysisResult = restTemplate.postForObject(mlApiUrl, responses, String.class);

        return analysisResult;
    }


    public List<Response> getResponsesByFarmerUser(Farmer_User farmerUser) {
        return responseRepository.findByFarmerUser(farmerUser);  // Pass the Farmer_User entity
    }

    @Override
    public List<Analysis> getAllAnalysis() {
        return List.of();
    }

    @Override
    public Optional<Analysis> getAnalysisById(Long id) {
        return Optional.empty();
    }

    @Override
    public Analysis saveAnalysis(Analysis analysis) {
        return null;
    }

    @Override
    public Analysis updateAnalysis(Long id, Analysis analysis) {
        return null;
    }

    @Override
    public void deleteAnalysis(Long id) {

    }
}
