package com.myung9.spring.project1.Mycontact.controller;

import com.myung9.spring.project1.Mycontact.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@Transactional
class PersonControllerTest {
    @Autowired
    private PersonController personController;

    private MockMvc mockMvc;

    //mocMVC지속적으로 생성되는것 리펙토링
    //@BeforeEach를 달게 되면 매 test마다 실

    @Autowired
    private PersonRepository personRepository;// 인젝션

    @BeforeEach
    void beforeEach(){
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    }

    @Test
    void getPerson() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/person/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("martin"));
    }

    @Test
    void postPerson() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/person")
                    .contentType(MediaType.APPLICATION_JSON_UTF8) // json타입을 명시
                    .content("{\n" +
                            "    \"name\": \"martin2\",\n" +
                            "    \"age\": 20,\n" +
                            "    \"bloodType\": \"A\"\n" +
                            "}"))
                .andDo(print())
//                .andExpect(status().isOk()); // 200ok
                .andExpect(status().isCreated()); //201created
    }

    @Test
    void modifyPerson() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/person/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\n" +
                                "    \"name\": \"martin\",\n" +
                                "    \"age\": 20,\n" +
                                "    \"bloodType\": \"A\"\n" +
                                "}"))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    void modifyName() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.patch("/api/person/1")
                .param("name", "martin22"))//name이 하나이기 때문에 requestbody가 아니라 param으로
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deletePerson() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/person/1"))
                .andDo(print())
                .andExpect(status().isOk());
        log.info("people delted : {}", personRepository.findPeopleDeleted());

    }
}