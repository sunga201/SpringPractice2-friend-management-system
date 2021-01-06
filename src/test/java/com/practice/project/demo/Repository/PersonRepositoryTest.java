package com.practice.project.demo.Repository;

import com.practice.project.demo.entity.Person;
import com.practice.project.demo.entity.dto.Birthday;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootExceptionReporter;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class PersonRepositoryTest {
    @Autowired
    PersonRepository personRepository;

    @Test
    public void findByName() throws Exception{
        List<Person> people = personRepository.findByName("timmy")
                .orElseThrow(()->new Exception("데이터가 존재하지 않습니다."));
        Assertions.assertEquals(people.size(), 1);

        Person person = people.get(0);
        System.out.println("people : " + people);
        Assertions.assertAll(
                ()->Assertions.assertEquals(person.getName(), "timmy"),
                ()->Assertions.assertEquals(person.getHobby(), "programming"),
                ()->Assertions.assertEquals(person.getAddress(), "판교"),
                ()->Assertions.assertEquals(person.getJob(), "programmer"),
                ()->Assertions.assertEquals(person.getBirthday(), Birthday.of(LocalDate.of(2003, 12, 14))),
                ()->Assertions.assertEquals(person.isDeleted(), false)
        );
    }

    @Test
    public void findByNameIfDeleted() throws Exception{
        List<Person> people = personRepository.findByName("andrew")
                .orElseThrow(()->new Exception("데이터가 존재하지 않습니다."));
    }

    @Test
    public void findByMonthOfBirthday() throws Exception{
        List<Person> people = personRepository.findByMonthOfBirthday(7)
                .orElseThrow(() -> new Exception("데이터가 존재하지 않습니다."));

        Assertions.assertEquals(people.size(), 1);

        Person person = people.get(0);

        Assertions.assertAll(
                ()->Assertions.assertEquals(person.getName(), "dannis"),
                ()->Assertions.assertEquals(person.getBirthday().getDayOfBirthday(), 13)
        );
    }

    @Test
    public void findByDeletedIsTrue() throws Exception{
        List<Person> people = personRepository.findByDeletedIsTrue()
                .orElseThrow(()->new Exception("데이터가 존재하지 않습니다."));

        Assertions.assertEquals(people.size(), 1);
        Person person = people.get(0);

        Assertions.assertEquals(person.getName(), "andrew");
    }
}