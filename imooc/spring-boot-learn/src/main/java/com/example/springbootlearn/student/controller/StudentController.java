package com.example.springbootlearn.student.controller;

import com.example.springbootlearn.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/student")
    public String student(@RequestParam Integer id) {
        return studentService.findStudentById(id).toString();
    }

}
