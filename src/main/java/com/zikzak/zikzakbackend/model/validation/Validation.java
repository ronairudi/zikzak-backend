package com.zikzak.zikzakbackend.model.validation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(ValidationKey.class)
public class Validation {

    @Id
    private Long userId;

    @Id
    private UUID validationCode;
}
