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
        List<Person> people = personRepository.findAll(); // 일반적으로 findAll 대신 findById를 사용
        /*List<Block> blocks = blockRepository.findAll(); // 1:1 관계를 사용하지 않는 코드
        List<String> blockNames=blocks
                .stream()
                .map(Block::getName)
                .collect(Collectors.toList());*/
        return people
                .stream()
                .filter(person -> person.getBlock()==null)
                .collect(Collectors.toList());
    }

    public Person getPerson(Long id){
        Person person = personRepository.findById(id).get();

        log.info("{}", person);
        return person;
    }
}
