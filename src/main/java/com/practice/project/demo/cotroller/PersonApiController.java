package com.practice.project.demo.cotroller;

import com.practice.project.demo.Repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/person")
@RequiredArgsConstructor
public class PersonApiController {
    private final PersonRepository personRepository;

}
