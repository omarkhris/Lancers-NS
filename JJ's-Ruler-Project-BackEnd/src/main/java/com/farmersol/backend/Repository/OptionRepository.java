package com.farmersol.backend.repository;

import com.farmersol.backend.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository <Option, Long> {


    List<Option> findByQuestionId(Long questionId);
}
