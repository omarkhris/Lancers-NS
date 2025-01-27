package com.farmersol.backend.Repository;

import com.farmersol.backend.Domain.Analysis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalysisRepository extends JpaRepository<Analysis, Long> {
}
