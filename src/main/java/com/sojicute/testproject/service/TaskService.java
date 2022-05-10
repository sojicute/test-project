package com.sojicute.testproject.service;

import com.sojicute.testproject.domain.Task;
import com.sojicute.testproject.dto.RequestDto;
import com.sojicute.testproject.dto.ResponseDto;

import java.util.List;

public interface TaskService {
    List<Task> findAll();
    ResponseDto findTaskById(Long id);
    void save(RequestDto requestDto);
}
