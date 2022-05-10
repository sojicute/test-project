package com.sojicute.testproject.dto;

import com.sojicute.testproject.domain.Type;
import lombok.Data;

@Data
public class RequestDto {

    private String matrixInput;

    private String firstInput;

    private String secondInput;

    private Type type;
}
