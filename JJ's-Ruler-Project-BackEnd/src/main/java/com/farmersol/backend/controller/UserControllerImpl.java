package com.farmersol.backend.controller;

import com.farmersol.backend.entity.Farmer_User;
import com.farmersol.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }
    @Override
    public ResponseEntity<List<Farmer_User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Override
    public ResponseEntity<Optional<Farmer_User>> getUserById(Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Override
    public ResponseEntity<Farmer_User> addUser(Farmer_User user) {
        return ResponseEntity.ok(userService.CreateUser(user));
    }

    @Override
    public ResponseEntity<Farmer_User> updateUser(Long id, Farmer_User updatedUser) {
        return ResponseEntity.ok(userService.updateUser(id, updatedUser));
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
