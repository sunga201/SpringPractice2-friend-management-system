package com.practice.project.demo.cotroller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.project.demo.Repository.PersonRepository;
import com.practice.project.demo.entity.Person;
import com.practice.project.demo.entity.dto.Birthday;
import com.practice.project.demo.service.api.PersonApiService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.descriptor.web.FragmentJarScannerCallback;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
class PersonApiControllerTest {
    @Autowired
    private PersonApiController personApiController;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    void beforeEach(){
        mockMvc = MockMvcBuilders.standaloneSetup(personApiController).build();
    }
    @Test
    void get() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/person/2")
        ).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void post() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\" : \"martin2\", \"age\" : 16, \"bloodType\" : \"O\"}")
        ).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void put() throws Exception{
        Person person = Person.builder()
                .id(2L)
                .name("changeMartin")
                .address("판교")
                .birthday(Birthday.of(LocalDate.now()))
                .hobby("programming")
                .job("programmer")
                .build();

        System.out.println("toJSON, value : " + toJSON(person));
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJSON(person))
        ).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        //Assertions.assertNotNull(personRepository.findById(2L).get().getBirthday());
    }

    @Test
    void delete() throws Exception{
        long id = 2L;
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/person/{id}", id)
        ).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        System.out.println(personRepository.findAll());

        Assertions.assertTrue(personRepository.findByDeletedIsTrue().get().stream().anyMatch(person
                -> person.getId().equals(id)));
    }

    @Test
    void checkJsonParser() throws JsonProcessingException {
        Person person = Person.builder()
                .name("testName")
                .address("testAddress")
                .job("testJob")
                .hobby("testHobby")
                .birthday(Birthday.of(LocalDate.now()))
                .build();
        System.out.println(toJSON(person));
    }

    private String toJSON(Person person) throws JsonProcessingException {
        return objectMapper.writeValueAsString(person);
    }
}