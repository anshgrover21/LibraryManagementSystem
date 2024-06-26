package com.example.minor1.repository;

import com.example.minor1.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student,Integer> {

    List<Student> findByPhoneNo(String phoneNo);
}
