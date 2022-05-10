package com.sojicute.testproject.dto;

import com.sojicute.testproject.domain.Type;
import lombok.Data;

@Data
public class TaskDto {

    private long id;

    private String firstStr;

    private String secondStr;

    private Object result;

    private Type type;

}
