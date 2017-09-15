package com.github.elizabetht;

import com.github.elizabetht.model.Student;
import com.github.elizabetht.model.StudentResponse;
import com.github.elizabetht.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentResponse> save(@RequestBody Student student) {
        StudentResponse response = studentService.save(student);
        if("Success".equals(response.getStatus())) {
            return new ResponseEntity<StudentResponse>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<StudentResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<StudentResponse> findByLogin(@RequestBody User user) {
        StudentResponse response = studentService.findByLogin(user);
        if("Success".equals(response.getStatus())) {
            return new ResponseEntity<StudentResponse>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<StudentResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{userName}")
    public ResponseEntity<StudentResponse> findByLogin(@PathVariable String userName) {
        StudentResponse response = studentService.findByUserName(userName);
        if("Success".equals(response.getStatus())) {
            return new ResponseEntity<StudentResponse>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<StudentResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
