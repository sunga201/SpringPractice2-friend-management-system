package com.practice.project.demo.service.api;

import com.practice.project.demo.Repository.PersonRepository;
import com.practice.project.demo.entity.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class PersonApiServiceTest {
    @Autowired
    private PersonApiService personApiService;

    @Autowired
    private PersonRepository personRepository;
    
    @Test
    @Transactional
    public void getPeopleByName(){
        // givenPeople(); //data.sql을 이용한 자동화로 대체

        List<Person> peopleList = personApiService.getPeopleByName("sophia");

        Assertions.assertEquals(peopleList.size(), 1);
        Assertions.assertEquals(peopleList.get(0).getName(), "sophia");
    }

    @Test
    @Transactional
    public void cascadeTest(){
        // givenPeople(); //data.sql을 이용한 자동화로 대체

        List<Person> people = personRepository.findAll();

        people.forEach(System.out::println);

        Person person = people.get(3);
        personRepository.save(person);
        personRepository.findAll().forEach(System.out::println);

        // personRepository.delete(person);
        // personRepository.findAll().forEach(System.out::println);
        // blockRepository.findAll().forEach(System.out::println);

        personRepository.save(person);
        personRepository.findAll().forEach(System.out::println);
    }

    public void getPerson(){
        // givenPeople(); //data.sql을 이용한 자동화로 대체

        Person person = personApiService.get(1L);
        System.out.println(person);
    }

    public void givenPerson(String name, int age, String bloodType) {
        Person p = Person.builder()
                .name(name)
                .build();
        personRepository.save(p);
    }

    public void givenBlockPerson(String name, int age, String bloodType){
        Person blockPerson = Person.builder()
                .name(name)
                .build();

       personRepository.save(blockPerson);
    }
}