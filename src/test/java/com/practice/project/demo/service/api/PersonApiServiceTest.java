package com.practice.project.demo.service.api;

import com.practice.project.demo.Repository.BlockRepository;
import com.practice.project.demo.Repository.PersonRepository;
import com.practice.project.demo.entity.Block;
import com.practice.project.demo.entity.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonApiServiceTest {
    @Autowired
    private PersonApiService personApiService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BlockRepository blockRepository;

    @Test
    @Transactional
    public void getPeopleExcludeBlocks(){
        // givenPeople(); //data.sql을 이용한 자동화로 대체

        List<Person> list = personApiService.getPeopleExcludeBlocks();

        list.forEach(element->{
            System.out.println(element);
        });

        Assertions.assertEquals(list.size(), 4);
    }

    @Test
    @Transactional
    public void getPeopleByName(){
        // givenPeople(); //data.sql을 이용한 자동화로 대체

        List<Person> peopleList = personApiService.getPeopleByName("martin");

        Assertions.assertEquals(peopleList.size(), 1);
        Assertions.assertEquals(peopleList.get(0).getName(), "martin");
    }

    @Test
    @Transactional
    public void cascadeTest(){
        // givenPeople(); //data.sql을 이용한 자동화로 대체

        List<Person> people = personRepository.findAll();

        people.forEach(System.out::println);

        Person person = people.get(3);
        person.getBlock().setRegisteredAt(LocalDate.now());
        person.getBlock().setUnregisteredAt(LocalDate.now());
        personRepository.save(person);
        personRepository.findAll().forEach(System.out::println);

        // personRepository.delete(person);
        // personRepository.findAll().forEach(System.out::println);
        // blockRepository.findAll().forEach(System.out::println);

        person.setBlock(null);
        personRepository.save(person);
        personRepository.findAll().forEach(System.out::println);
        blockRepository.findAll().forEach(System.out::println);
    }

    public void getPerson(){
        // givenPeople(); //data.sql을 이용한 자동화로 대체

        Person person = personApiService.getPerson(1L);
        System.out.println(person);
    }

    public void givenPerson(String name, int age, String bloodType) {
        Person p = Person.builder()
                .name(name)
                .age(age)
                .bloodType(bloodType)
                .build();
        personRepository.save(p);
    }

    public void givenBlockPerson(String name, int age, String bloodType){
        Person blockPerson = Person.builder()
                .name(name)
                .age(age)
                .bloodType(bloodType)
                .block(Block.builder().name(name).reason("그냥").build())
                .build();

       personRepository.save(blockPerson);
    }
}