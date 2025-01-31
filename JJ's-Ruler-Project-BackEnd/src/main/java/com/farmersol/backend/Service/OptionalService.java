package com.farmersol.backend.Service;


import com.farmersol.backend.Domain.Option;
import com.farmersol.backend.Repository.OptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OptionalService {


    private final OptionRepository optionRepository;


    public OptionalService(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }


    public List<Option> getOptionsByQuestion(Long questionId) {
        return optionRepository.findByQuestionId(questionId);
    }


    public Optional<Option> getOptionById(Long optionId) {
        return optionRepository.findById(optionId);
    }

    public Option createOption(Option option) {
        return optionRepository.save(option);
    }


    public void deleteOptionById(Long optionId) {
        optionRepository.deleteById(optionId);
    }
}
