package com.practice.project.demo.service.api;

import com.practice.project.demo.Repository.BlockRepository;
import com.practice.project.demo.Repository.PersonRepository;
import com.practice.project.demo.entity.Block;
import com.practice.project.demo.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    public void getPeopleExcludeBlocks(){
        givenPeople();

        personApiService.getPeopleExcludeBlocks().forEach(element->{
            System.out.println(element);
        });
    }

    @Test
    @Transactional
    public void cascadeTest(){
        givenPeople();

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

    @Test
    @Transactional
    public void getPerson(){
        givenPeople();

        Person person = personApiService.getPerson(93L);
        System.out.println(person);
    }

    public void givenPeople() {
        givenPerson("martin",
                10,
                "A");

        givenPerson("david",
                9,
                "B");

        givenBlockPerson("dannis",
                7,
                "O");

        givenBlockPerson("martin",
                11,
                "AB"); // 첫 번째 martin과 달리 검색이 되도록 한다.
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