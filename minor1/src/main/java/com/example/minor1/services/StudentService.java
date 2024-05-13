package com.example.minor1.services;

import com.example.minor1.model.*;
import com.example.minor1.repository.StudentRepo;
import com.example.minor1.request.StudentCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public Student createStudent(StudentCreateRequest studentCreateRequest) {


        List<Student> studentList = studentRepo.findByPhoneNo(studentCreateRequest.getPhoneNo());
        Student studentFromDb = null;
        if (studentList == null || studentList.isEmpty()) {
            studentFromDb = studentRepo.save(studentCreateRequest.toStudent());
            return studentFromDb;
        }
        studentFromDb = studentList.get(0);

        return studentFromDb;

    }

    public List<Student> filterBy(StudentFilterType prefix, Operator operator, String value) {

        switch (operator) {
            case EQUAL:
                switch (prefix) {
                    case CONTACT:
                        return studentRepo.findByPhoneNo(value);
                }
            default: return new ArrayList<>();

        }

    }



}
