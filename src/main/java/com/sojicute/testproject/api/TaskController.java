package com.sojicute.testproject.api;

import com.sojicute.testproject.domain.Task;
import com.sojicute.testproject.dto.LexicographicDto;
import com.sojicute.testproject.dto.MagicSquareDto;
import com.sojicute.testproject.dto.RequestDto;
import com.sojicute.testproject.dto.ResponseDto;
import com.sojicute.testproject.service.TaskService;
import com.sojicute.testproject.utils.LexicographicOrder;
import com.sojicute.testproject.utils.MagicSquare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.sojicute.testproject.domain.Type.LEXICOGRAPHIC;
import static com.sojicute.testproject.domain.Type.MAGIC_SQUARE;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TaskController {

    private final TaskService taskService;
    private final MagicSquare magicSquare;
    private final LexicographicOrder lexicographicOrder;

    @Autowired
    public TaskController(TaskService taskService, MagicSquare magicSquare, LexicographicOrder lexicographicOrder) {
        this.taskService = taskService;
        this.magicSquare = magicSquare;
        this.lexicographicOrder = lexicographicOrder;
    }


    @GetMapping("/task")
    public ResponseEntity<List<Task>> getAll() {
        List<Task> tasks = taskService.findAll();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<ResponseDto> get(@PathVariable long id) {
        ResponseDto responseDto = taskService.findTaskById(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/task")
    public ResponseEntity<Void> save(@RequestBody RequestDto requestDto) {
        taskService.save(requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
 
    @PostMapping("/magic-square")
    public ResponseEntity<ResponseDto> calculate(@Valid @RequestBody MagicSquareDto mgDto) {
        ResponseDto response = new ResponseDto();

        if (mgDto.getType() == MAGIC_SQUARE) {

            int cost = magicSquare.getCost(mgDto.getInput());

            response.setFirstInput(mgDto.getInput());
            response.setResult(cost);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/lexicographic")
    public ResponseEntity<ResponseDto> calculate(@Valid @RequestBody LexicographicDto lgDto) {
        ResponseDto response = new ResponseDto();

        if(lgDto.getType() == LEXICOGRAPHIC) {

            List<String> list = lexicographicOrder.inArray(lgDto.getFirstInput(), lgDto.getSecondInput());

            response.setFirstInput(lgDto.getFirstInput());
            response.setSecondInput(lgDto.getSecondInput());
            response.setResult(list.stream().collect(Collectors.joining(", ")));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
