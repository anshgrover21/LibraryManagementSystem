package com.example.minor1.repository;

import com.example.minor1.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author, Integer> {

    Author findByEmail(String email);

}
