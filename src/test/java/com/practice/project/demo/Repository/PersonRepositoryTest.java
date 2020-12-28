package com.practice.project.demo.Repository;

import com.practice.project.demo.entity.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTest {
    @Autowired
    PersonRepository personRepository;

    @Test
    public void crud(){
        Person person = Person.builder()
                .name("Jack")
                .age(64)
                .hobby("puzzle")
                .address("서울시 동대문구")
                .birthday(LocalDate.now())
                .bloodType("O")
                .job("backend engineer")
                .build();
        Person newPerson = personRepository.save(person);
        Assertions.assertNotNull(newPerson);

        System.out.println(personRepository.findAll());
    }
}