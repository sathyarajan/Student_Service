package com.github.elizabetht;

import com.github.elizabetht.model.Student;
import com.github.elizabetht.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody Student student) {
        studentService.save(student);
        return new ResponseEntity<String>("Success", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> findByLogin(@RequestBody User user) {
        Boolean status = studentService.findByLogin(user);
        return new ResponseEntity<Boolean>(status, HttpStatus.OK);
    }

    @GetMapping("{userName}")
    public ResponseEntity<Boolean> findByLogin(@PathVariable String userName) {
        Boolean status = studentService.findByUserName(userName);
        return new ResponseEntity<Boolean>(status, HttpStatus.OK);
    }
}
