package com.zikzak.zikzakbackend.service;

import com.zikzak.zikzakbackend.model.validation.Validation;
import com.zikzak.zikzakbackend.repository.ValidationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ValidationService {

    @Autowired
    private ValidationRepository validationRepository;

    public void saveValidation(Validation validation) {
        validationRepository.save(validation);
    }

    public void deleteValidationByCode(UUID validationCode) {
        validationRepository.deleteByValidationCode(validationCode);
    }

    public Validation createValidationForUser(Long userId) {
        return Validation.builder()
                .userId(userId)
                .validationCode(UUID.randomUUID())
                .build();
    }
}
