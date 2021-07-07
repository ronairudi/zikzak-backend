package com.zikzak.zikzakbackend.repository;

import com.zikzak.zikzakbackend.model.validation.Validation;
import com.zikzak.zikzakbackend.model.validation.ValidationKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValidationRepository extends JpaRepository<Validation, ValidationKey> {
}
