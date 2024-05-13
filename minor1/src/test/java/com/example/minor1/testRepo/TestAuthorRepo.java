package com.example.minor1.testRepo;


import com.example.minor1.model.Author;
import com.example.minor1.repository.AuthorRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;

@DataJpaTest
public class TestAuthorRepo  {

    @Autowired
    private AuthorRepo authorRepo;

    private Author author;

    @BeforeEach
    public void setup(){
         author = Author.builder().email("ansd@gmail.com").build();
        authorRepo.save(author);
    }


@Test
    public void testFindByEmail(){

       Author author1 = authorRepo.findByEmail("ansd@gmail.com");
        Assertions.assertEquals(author1.getEmail(),"ansd@gmail.com");


    }
}
