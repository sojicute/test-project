package com.sojicute.testproject.dto;

import com.sojicute.testproject.annotations.InputValidation;
import com.sojicute.testproject.annotations.TypeValidation;
import com.sojicute.testproject.domain.Type;
import lombok.Data;

@Data
public class MagicSquareDto {

    @InputValidation
    private String input;

    @TypeValidation
    private Type type;
}
