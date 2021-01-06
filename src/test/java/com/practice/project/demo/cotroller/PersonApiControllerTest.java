package com.practice.project.demo.cotroller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.project.demo.Repository.PersonRepository;
import com.practice.project.demo.entity.Person;
import com.practice.project.demo.entity.dto.Birthday;
import com.practice.project.demo.network.request.PersonRequest;
import com.practice.project.demo.service.api.PersonApiService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.descriptor.web.FragmentJarScannerCallback;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;
import org.springframework.test.web.servlet.result.JsonPathResultMatchersDsl;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.jayway.jsonpath.JsonPath;

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

    @Autowired
    private MappingJackson2HttpMessageConverter messageConverter;
    private MockMvc mockMvc;

    @BeforeEach
    void beforeEach(){
        mockMvc = MockMvcBuilders.standaloneSetup(personApiController).setMessageConverters(messageConverter).build();
    }
    @Test
    void get() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/person/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("martin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hobby").isEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").isEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthday").value("1996-08-15"));
    }

    @Test
    void post() throws Exception{
        PersonRequest personRequest = PersonRequest.builder()
                .name("martin")
                .hobby("Programming")
                .address("판교")
                .birthday(LocalDate.now())
                .job("programmer")
                .build();

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(toJSON(personRequest))
        ).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        Person person = personRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
                .get(0);

        System.out.println("get person : " + person);
        Assertions.assertAll(
                ()->Assertions.assertEquals(person.getName(), "martin"),
                ()->Assertions.assertEquals(person.getAddress(), "판교"),
                ()->Assertions.assertEquals(person.getHobby(), "Programming"),
                ()->Assertions.assertEquals(person.getBirthday(), Birthday.of(LocalDate.now())),
                ()->Assertions.assertEquals(person.getJob(), "programmer")
        );
    }

    @Test
    void put() throws Exception{
        PersonRequest personRequest = PersonRequest.builder()
                .id(1L)
                .name("changeMartin")
                .address("판교")
                .birthday(LocalDate.now())
                .hobby("programming")
                .job("programmer")
                .build();

        System.out.println("toJSON, value : " + toJSON(personRequest));
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(toJSON(personRequest))
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

    private String toJSON(PersonRequest personRequest) throws JsonProcessingException {
        return objectMapper.writeValueAsString(personRequest);
    }
}