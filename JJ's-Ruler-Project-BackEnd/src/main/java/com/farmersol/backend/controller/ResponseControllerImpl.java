package com.farmersol.backend.controller;

import com.farmersol.backend.entity.Response;
import com.farmersol.backend.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ResponseControllerImpl implements ResponseController {

    @Autowired
    private final ResponseService responseService;

    public ResponseControllerImpl(ResponseService responseService) {
        this.responseService = responseService;
    }


    @Override
    public ResponseEntity<List<Response>> getAllResponses() {
        return ResponseEntity.ok(responseService.getAllResponses());
    }

    @Override
    public ResponseEntity<List<Response>> getResponseByUserId(Long userId) {
        return ResponseEntity.ok(responseService.getResponsesByUser(userId));
    }

    @Override
    public ResponseEntity<Response> createResponse(Long userId, Long quizId, Long optionId, Long questionId, Response response) {
        return ResponseEntity.ok(responseService.saveResponse(userId, quizId, optionId, questionId, response.toString()));
    }


    @Override
    public ResponseEntity<Response> updateResponse(Long id, Response updatedResponse) {
        return ResponseEntity.ok(responseService.updateResponse(id, updatedResponse));
    }

    @Override
    public ResponseEntity<Void> deleteResponse(Long id) {
        responseService.deleteResponse(id);
        return ResponseEntity.noContent().build();
    }
}
