package com.practice.project.demo.cotroller;

import com.practice.project.demo.service.api.PersonApiService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.descriptor.web.FragmentJarScannerCallback;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
class PersonApiControllerTest {
    @Autowired
    private PersonApiController personApiController;

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
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\" : 1," +
                        " \"name\" : \"changeMartin\"," +
                        " \"age\" : 26, \"bloodType\" : \"A\"," +
                        " \"blockId\" : \"null\"}")
        ).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void delete() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/person/1")
        ).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}