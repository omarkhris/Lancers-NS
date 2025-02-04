package com.farmersol.backend.controller;

import com.farmersol.backend.entity.Option;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/options")
public interface OptionController {

    @GetMapping
    ResponseEntity<List<Option>> getAllOptions();

    @GetMapping("/{id}")
    ResponseEntity<Optional<Option>> getOptionById(@PathVariable("id") Long id);

    @PostMapping
    ResponseEntity<Option> createOption(@RequestBody Option option);

    @PostMapping("/{id}")
    ResponseEntity<Option> updateOption(@PathVariable("id") Long id, @RequestBody Option option);

    @DeleteMapping("/{id}")
    ResponseEntity<Option> deleteOption(@PathVariable("id") Long id);

}
