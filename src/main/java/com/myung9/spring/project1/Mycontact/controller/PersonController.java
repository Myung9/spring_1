package com.myung9.spring.project1.Mycontact.controller;

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
    public void postPerson(@RequestBody Person person){
        personService.put(person);

        log.info("person -> {} ", personRepository.findAll());
    }
}
