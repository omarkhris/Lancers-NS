package com.farmersol.backend.repository;

import com.farmersol.backend.entity.Farmer_User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Farmer_User, Long> {

    Optional<Farmer_User> findByUsername(String username);
}
