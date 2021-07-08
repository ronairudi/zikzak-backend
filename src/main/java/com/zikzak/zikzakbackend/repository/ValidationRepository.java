package com.zikzak.zikzakbackend.repository;

import com.zikzak.zikzakbackend.model.validation.Validation;
import com.zikzak.zikzakbackend.model.validation.ValidationKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface ValidationRepository extends JpaRepository<Validation, ValidationKey> {

    @Transactional
    void deleteByValidationCode(UUID validationCode);
}
