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

@SpringBootTest
class PersonRepositoryTest {
    @Autowired
    PersonRepository personRepository;

    @Test
    public void crud(){
        Person person = Person.builder()
                .name("Brown")
                .hobby("puzzle")
                .address("서울시 동대문구")
                .job("backend engineer")
                .build();

        Person newPerson = personRepository.save(person);
        Assertions.assertNotNull(newPerson);
        System.out.println(personRepository.findAll());
        System.out.println(personRepository.findByName("sophia").get());
    }

    /*@Test // bloodtype 지웠음. 테스트케이스도 같이 제거
    public void getByBloodType(){
        List<Person> result = personRepository.findByBloodType("O").get();
        personRepository.findAll().forEach(System.out::println);
        Assertions.assertEquals(result.size(), 2);
        Assertions.assertEquals(result.get(0).getName(), "benny");
    }*/

    public void givenPerson(String name, LocalDate birthday) {
        Person p = Person.builder()
                .name(name)
                .build();

        p.setBirthday(Birthday.of(birthday));
        Person newPerson = personRepository.save(p);
    }

    @Test
    public void getByBirthday(){
        personRepository.findByMonthOfBirthday(8).get().forEach(System.out::println);

        Person martin = new Person();
        martin.birthdayToday();
        martin.age();
    }
}