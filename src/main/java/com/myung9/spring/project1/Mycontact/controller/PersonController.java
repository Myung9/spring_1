package com.myung9.spring.project1.Mycontact.controller;

import com.myung9.spring.project1.Mycontact.controller.dto.PersonDto;
import com.myung9.spring.project1.Mycontact.domain.Person;
import com.myung9.spring.project1.Mycontact.repository.PersonRepository;
import com.myung9.spring.project1.Mycontact.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/person")
@RestController
@Slf4j
public class PersonController {
    @Autowired
    private PersonService personService; // personService bean injectcion

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable Long id) {
        return personService.getPerson(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postPerson(@RequestBody PersonDto personDto){
        personService.put(personDto);

        log.info("person -> {} ", personRepository.findAll());
    }

    @PutMapping("/{id}")
    public void modifyPerson(@PathVariable Long id, @RequestBody PersonDto personDto){//json body를 받기 위해서 requestbody
        //Rest규약에 맞춰서 id에도 PathVariable
        personService.modify(id, personDto);

        log.info("person -> {} ", personRepository.findAll());
    }

    //진짜 이름이 바뀌는경우 별도의 api를 통해서 이름을 update
    @PatchMapping("/{id}")//
    public void modifyPerson(@PathVariable Long id, String name){
        personService.modify(id, name);//dto대신 StringValue를 사용
        log.info("person -> {}", personRepository.findAll());
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id){
        personService.delete(id);

        log.info("person -> {}", personRepository.findAll());
    }
}
