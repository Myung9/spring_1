package com.myung9.spring.project1.Mycontact.service;

import com.myung9.spring.project1.Mycontact.controller.dto.PersonDto;
import com.myung9.spring.project1.Mycontact.domain.Person;
import com.myung9.spring.project1.Mycontact.exception.PersonNotFoundException;
import com.myung9.spring.project1.Mycontact.exception.RenameNotPermittedException;
import com.myung9.spring.project1.Mycontact.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Slf4j // lombok에서 제공
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAll(){
        return personRepository.findAll();
    }
    
    public List<Person> getPeopleByName(String name){
        return personRepository.findByName(name); //query 조건으로 where기능
    }

    @Transactional(readOnly = true)
    public Person getPerson(Long id){
        return personRepository.findById(id).orElse(null);// java7에서 부터 지원하는 orElse()
        // -> 가지고 있는 값이 없으면 null을 return
    }

    @Transactional
    public void put(PersonDto personDto){
        Person person = new Person();
        person.set(personDto);
        person.setName(personDto.getName());

        personRepository.save(person);
    }

    @Transactional
    public void modify(Long id, @NotNull PersonDto personDto){
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);

        if(!person.getName().equals(personDto.getName())){
            throw new RenameNotPermittedException();
        }
        person.set(personDto);
        personRepository.save(person);
    }

    @Transactional
    public void modify(Long id, String name) { // id값을받아서 이름만 수정하는 로직
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        person.setName(name);
        personRepository.save(person);
    }

    @Transactional
    public void delete(Long id){

        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);

        person.setDeleted(true);

        personRepository.save(person);
    }
}

