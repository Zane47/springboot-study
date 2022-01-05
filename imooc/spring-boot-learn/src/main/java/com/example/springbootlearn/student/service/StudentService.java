package com.example.springbootlearn.student.service;

import com.example.springbootlearn.student.entity.Student;
import com.example.springbootlearn.student.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;


    public Student findStudentById(Integer id) {
        return studentMapper.findById(id);
    }

}
