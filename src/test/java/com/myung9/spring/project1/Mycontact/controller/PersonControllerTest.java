package com.myung9.spring.project1.Mycontact.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class PersonControllerTest {
    @Autowired
    private PersonController personController;

    private MockMvc mockMvc;

    @Test
    void getPerson() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/person/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("martin"));
    }

    @Test
    void postPerson() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/person")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content("{\n" +
                            "    \"name\": \"martin2\",\n" +
                            "    \"age\": 20,\n" +
                            "    \"bloodType\": \"A\"\n" +
                            "}"))
                .andDo(print())
//                .andExpect(status().isOk()); // 200ok
                .andExpect(status().isCreated()); //201created
    }
}