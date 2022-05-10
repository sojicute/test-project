package com.sojicute.testproject.dto;

import com.sojicute.testproject.domain.Type;
import lombok.Data;

@Data
public class ResponseDto {

    private Object firstInput;

    private Object secondInput;

    private Object result;

    private Type type;
}
