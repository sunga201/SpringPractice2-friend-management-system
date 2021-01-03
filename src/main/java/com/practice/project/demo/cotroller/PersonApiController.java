package com.practice.project.demo.cotroller;

import com.practice.project.demo.Repository.PersonRepository;
import com.practice.project.demo.entity.Person;
import com.practice.project.demo.network.request.PersonRequest;
import com.practice.project.demo.service.api.PersonApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("api/person")
@RequiredArgsConstructor
@Slf4j
public class PersonApiController {
    private final PersonApiService personApiService;
    private final PersonRepository personRepository;

    @GetMapping("{id}")
    public Person get(@PathVariable Long id){
        return personApiService.get(id);
    }

    @PostMapping("")
    public void post(@RequestBody Person person){
        log.info("people : {}", personRepository.findAll());
        personApiService.save(person);
    }

    @PutMapping("")
    public void put(@RequestBody PersonRequest personRequest){
        personApiService.put(personRequest);
        log.info("people : {}", personRepository.findAll());
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        personApiService.delete(id);
        log.info("people : {}", personRepository.findAll());
        personRepository.findByDeletedIsTrue().stream().forEach(data -> log.info("{}", data));
    }
}
