package com.farmersol.backend.Service;


import com.farmersol.backend.Domain.Farmer_User;
import com.farmersol.backend.Domain.Response;
import com.farmersol.backend.Repository.AnalysisRepository;
import com.farmersol.backend.Repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AnalysisService {

    @Autowired
    private final ResponseRepository responseRepository;

    private final AnalysisRepository analysisRepository;

    private final RestTemplate restTemplate;

    public AnalysisService(ResponseRepository responseRepository, AnalysisRepository analysisRepository, RestTemplate restTemplate) {
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

}
