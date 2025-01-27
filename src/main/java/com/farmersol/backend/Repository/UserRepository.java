package com.farmersol.backend.Repository;

import com.farmersol.backend.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
