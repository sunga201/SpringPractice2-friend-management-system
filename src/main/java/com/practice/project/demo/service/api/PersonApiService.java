package com.practice.project.demo.service.api;

import com.practice.project.demo.Repository.BlockRepository;
import com.practice.project.demo.Repository.PersonRepository;
import com.practice.project.demo.entity.Block;
import com.practice.project.demo.entity.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonApiService {

    private final PersonRepository personRepository;
    //private final BlockRepository blockRepository;

    public List<Person> getPeopleExcludeBlocks(){
        //List<Person> people = personRepository.findAll(); // 일반적으로 findAll 대신 findById를 사용
        /*List<Block> blocks = blockRepository.findAll(); // 1:1 관계를 사용하지 않는 코드
        List<String> blockNames=blocks
                .stream()
                .map(Block::getName)
                .collect(Collectors.toList());*/
        
        // 1 : 1 관계 사용
        /*return people     //findAll() 사용, 비효율적
                .stream()
                .filter(person -> person.getBlock()==null)
                .collect(Collectors.toList());*/
        
        return personRepository.findByBlockIsNull().get(); // isNull을 이용한 find 메소드 사용
    }

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

    public Person getPerson(Long id){
        Person person = personRepository.findById(id).get();

        log.info("{}", person);
        return person;
    }
}
