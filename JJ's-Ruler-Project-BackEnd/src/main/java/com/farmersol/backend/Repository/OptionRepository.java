package com.farmersol.backend.Repository;

import com.farmersol.backend.Domain.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository <Option, Long> {


    List<Option> findByQuestionId(Long questionId);
}
