package com.farmersol.backend.service;


import com.farmersol.backend.entity.Farmer_User;
import com.farmersol.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Farmer_User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<Farmer_User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public Optional<Farmer_User> getUserByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return Optional.empty();  }
        return userRepository.findByUsername(username);
    }

    public Farmer_User CreateUser(Farmer_User farmerUser) {
        return userRepository.save(farmerUser);
    }

    public Farmer_User updateUser(Long userId, Farmer_User farmerUserToUpdate) {
        return userRepository.findById(userId).map(user -> {
            user.setUsername(farmerUserToUpdate.getUsername());
            user.setPassword(farmerUserToUpdate.getPassword());
            user.setEmail(farmerUserToUpdate.getEmail());
            return userRepository.save(user);}).orElseThrow(
                    () -> new IllegalArgumentException("Farmer_User not found")
        );

    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

}




