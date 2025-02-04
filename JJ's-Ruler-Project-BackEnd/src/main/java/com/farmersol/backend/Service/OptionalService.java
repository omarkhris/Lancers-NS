package com.farmersol.backend.service;

import com.farmersol.backend.entity.Option;

import java.util.List;
import java.util.Optional;

public interface OptionalService {
    Option createOption(Option option);
    List<Option> getAllOptions();
    Optional<Option> getOptionById(Long id);
//    List<Option> getOptionsByName(String name);
//    List<Option> getOptionsByDescription(String description);
    List<Option> getOptionsByQuestionId(Long questionId);
    Option saveOption(Option option);
    Option updateOption(Long id, Option updatedOption);
    void deleteOption(Long id);
}
