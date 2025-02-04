package com.farmersol.backend.controller;

import com.farmersol.backend.entity.Option;
import com.farmersol.backend.service.OptionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class OptionControllerImpl implements OptionController {
    @Autowired
    private final OptionalService optionalService;

    public OptionControllerImpl(OptionalService optionalService) {
        this.optionalService = optionalService;
    }


    @Override
    public ResponseEntity<List<Option>> getAllOptions() {
        return ResponseEntity.ok(optionalService.getAllOptions());
    }

    @Override
    public ResponseEntity<Optional<Option>> getOptionById(Long id) {
        return ResponseEntity.ok(optionalService.getOptionById(id));
    }

    @Override
    public ResponseEntity<Option> createOption(Option option) {
        return ResponseEntity.ok(optionalService.createOption(option));
    }

    @Override
    public ResponseEntity<Option> updateOption(Long id, Option option) {
        return ResponseEntity.ok(optionalService.updateOption(id, option));
    }

    @Override
    public ResponseEntity<Option> deleteOption(Long id) {
        optionalService.deleteOption(id);
        return ResponseEntity.noContent().build();
    }
}
