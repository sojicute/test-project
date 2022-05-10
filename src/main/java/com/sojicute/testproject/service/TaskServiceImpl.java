package com.sojicute.testproject.service;

import com.sojicute.testproject.domain.Task;
import com.sojicute.testproject.dto.RequestDto;
import com.sojicute.testproject.dto.ResponseDto;
import com.sojicute.testproject.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sojicute.testproject.domain.Type.LEXICOGRAPHIC;
import static com.sojicute.testproject.domain.Type.MAGIC_SQUARE;


@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;


    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAllByOrderByType();
    }

    @Override
    public ResponseDto findTaskById(Long id) {
        Task task = taskRepository.getById(id);
        ResponseDto responseDto = new ResponseDto();

        if (task.getType() == MAGIC_SQUARE) {
            responseDto.setFirstInput(task.getInput());
        } else if (task.getType() == LEXICOGRAPHIC) {

            String[] input = task.getInput().split(";");

            responseDto.setFirstInput(input[0]);
            responseDto.setSecondInput(input[1]);
        }
        responseDto.setType(task.getType());

        return responseDto;
    }

    @Override
    public void save(RequestDto requestDto) {
        Task task = new Task();


        if (requestDto.getType().equals(MAGIC_SQUARE)) {
            task.setInput(requestDto.getMatrixInput());

        } else if (requestDto.getType().equals(LEXICOGRAPHIC)) {
            task.setInput(requestDto.getFirstInput() +";"+ requestDto.getSecondInput());
            System.out.println(requestDto.getFirstInput());
        }
        task.setType(requestDto.getType());

        taskRepository.save(task);
    }
}
