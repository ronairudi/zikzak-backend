package com.zikzak.zikzakbackend.model.validation;

import java.io.Serializable;
import java.util.UUID;

public class ValidationKey implements Serializable {
    Long userId;
    UUID validationCode;
}
