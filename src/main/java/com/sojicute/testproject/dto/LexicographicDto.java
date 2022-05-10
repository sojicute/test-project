package com.sojicute.testproject.dto;

import com.sojicute.testproject.annotations.TypeValidation;
import com.sojicute.testproject.domain.Type;
import lombok.Data;

@Data
public class LexicographicDto {

    private String firstInput;

    private String secondInput;

    @TypeValidation
    private Type type;
}
