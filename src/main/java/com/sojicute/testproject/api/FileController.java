package com.sojicute.testproject.api;

import com.sojicute.testproject.domain.Type;
import com.sojicute.testproject.dto.RequestDto;
import com.sojicute.testproject.dto.ResponseDto;
import com.sojicute.testproject.utils.LexicographicOrder;
import com.sojicute.testproject.utils.MagicSquare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
@RequestMapping("")
public class FileController {

    private final MagicSquare magicSquare;
    private final LexicographicOrder lexicographicOrder;

    @Autowired
    public FileController(MagicSquare magicSquare, LexicographicOrder lexicographicOrder) {
        this.magicSquare = magicSquare;
        this.lexicographicOrder = lexicographicOrder;
    }

    @PostMapping(value = "/import")
    public ResponseEntity<ResponseDto> importTXT(@RequestParam("file") MultipartFile file) {

        ResponseDto response = new ResponseDto();

        try {
            byte[] bytes = file.getBytes();
            String str = new String(bytes);
            String[] array = str.split("\n");
            System.out.println(str);
            Type type = Type.valueOf(array[0]);

            switch (type) {
                case MAGIC_SQUARE:
                    int cost = magicSquare.getCost(array[1]);
                    response.setFirstInput(array[1]);
                    response.setResult(cost);
                    break;
                case LEXICOGRAPHIC:
                    List<String> list = lexicographicOrder.inArray(array[1], array[2]);
                    response.setFirstInput(array[1]);
                    response.setSecondInput(array[2]);
                    response.setResult(list.stream().collect(Collectors.joining(", ")));
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/export")
    public ResponseEntity<Resource> exportTXT(@RequestBody RequestDto requestDto) {

        String data;

        switch (requestDto.getType()) {
            case MAGIC_SQUARE:
                data = requestDto.getType() +"\n"+ requestDto.getMatrixInput();
                break;
            case LEXICOGRAPHIC:
                data = requestDto.getType() +"\n"+ requestDto.getFirstInput() +"\n"+ requestDto.getSecondInput();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + requestDto.getType());
        }

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date());
        String filename = timeStamp+"task.txt";

        HttpHeaders headers = new HttpHeaders();

        ByteArrayResource resource = new ByteArrayResource(data.getBytes());

        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline;attachment; filename="+filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        headers.setAccessControlExposeHeaders(Arrays.asList(HttpHeaders.CONTENT_DISPOSITION));
        headers.setContentLength(resource.contentLength());

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
