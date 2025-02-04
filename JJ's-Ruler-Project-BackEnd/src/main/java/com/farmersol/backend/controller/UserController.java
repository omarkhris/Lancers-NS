package com.farmersol.backend.controller;

import com.farmersol.backend.entity.Farmer_User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/users")
public interface UserController {

    @GetMapping
    ResponseEntity<List<Farmer_User>> getAllUsers();

    @GetMapping("/{id}")
    ResponseEntity<Optional<Farmer_User>> getUserById(@PathVariable("id") Long id);

    @PostMapping
    ResponseEntity<Farmer_User> addUser(@RequestBody Farmer_User user);

    @PutMapping("/{id}")
    ResponseEntity<Farmer_User> updateUser(@PathVariable("id") Long id, @RequestBody Farmer_User updatedUser);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable("id") Long id);
}
