package com.example.minor1.controller;


import com.example.minor1.model.*;
import com.example.minor1.request.StudentCreateRequest;
import com.example.minor1.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Student")
public class StudentController {

@Autowired
private StudentService studentService;

    @PostMapping("/addStudent")
    public Student createStudent(@RequestBody @Valid StudentCreateRequest studentCreateRequest){

       return studentService.createStudent(studentCreateRequest);


    }

    @GetMapping("/filter")
    public List<Student> filterBy(@RequestParam("prefix") StudentFilterType prefix, @RequestParam("operator") Operator operator , @RequestParam("value") String value){
        return studentService.filterBy(prefix,operator,value);
    }

    //creating a filter for student as well here i am searching student from the db

}
