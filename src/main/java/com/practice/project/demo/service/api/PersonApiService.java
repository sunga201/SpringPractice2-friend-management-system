package com.practice.project.demo.service.api;

import com.practice.project.demo.Repository.PersonRepository;
import com.practice.project.demo.entity.Person;
import com.practice.project.demo.network.request.PersonRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonApiService {

    private final PersonRepository personRepository;

    public List<Person> getPeopleByName(String name){
        /*List<Person> people = personRepository.findAll();  // findAll()로 모든 튜플 불러온 뒤, name을 가진
                                                             // 튜플만 필터링 -> 비효율적

        return people.stream()
                .filter(person->{
                    return person.getName().equals(name);
                })
                .collect(Collectors.toList());*/

        return personRepository.findByName(name).get();
    }

    public Person get(Long id){
        Person person = personRepository.findById(id).orElse(null);
        log.info("{}", person);
        return person;
    }

    public void put(PersonRequest body){
        log.info("body : {}", body);
        System.out.println("id : " + body.getId());
        Person renewalPerson = personRepository.findById(body.getId())
                .orElseThrow(()->new RuntimeException("데이터가 존재하지 않습니다."));

        log.info("in service, birthday : {}", body.getBirthday());
        renewalPerson.set(body);
        log.info("renewal person : {}", renewalPerson);
        personRepository.save(renewalPerson);
    }

    public void delete(Long id){
        Person person = personRepository.findById(id).orElseThrow(()
                -> new RuntimeException("데이터가 존재하지 않습니다."));

        person.setDeleted(true);
        personRepository.save(person);
    }

    public void save(PersonRequest body){
        Person person = new Person();
        person.set(body);
        log.info("person : {}", person);
        personRepository.save(person);
    }
}
