package com.farmersol.backend.Repository;

import com.farmersol.backend.Domain.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository <Option, Long> {
}
