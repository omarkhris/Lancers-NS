package com.farmersol.backend.service;


import com.farmersol.backend.entity.Option;
import com.farmersol.backend.repository.OptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OptionalServiceImp implements OptionalService {


    private final OptionRepository optionRepository;


    public OptionalServiceImp(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    @Override
    public List<Option> getAllOptions() {
        return optionRepository.findAll();
    }

    @Override
    public Optional<Option> getOptionById(Long optionId) {
        return optionRepository.findById(optionId);
    }

    @Override
    public List<Option> getOptionsByQuestionId(Long questionId) {
        return optionRepository.findByQuestionId(questionId);
    }

    @Override
    public Option saveOption(Option option) {
        return optionRepository.save(option);
    }

    @Override
    public Option updateOption(Long id, Option updatedOption) {
        return optionRepository.findById(id).map(option->{
            option.setOptionText(updatedOption.getOptionText());
            return optionRepository.save(option);
        }).orElseThrow(()->new IllegalArgumentException("Option not found"));
    }

    @Override
    public void deleteOption(Long id) {
        optionRepository.deleteById(id);
    }

    @Override
    public Option createOption(Option option) {
        return optionRepository.save(option);
    }

}
