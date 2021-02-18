package com.myung9.spring.project1.Mycontact.service;

import com.myung9.spring.project1.Mycontact.controller.dto.PersonDto;
import com.myung9.spring.project1.Mycontact.domain.Person;
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


    public List<Person> getPeopleByName(String name){
//        List<Person> people = personRepository.findAll();
//
//        return people.stream().filter(person -> person.getName().equals(name)).collect(Collectors.toList());
        return personRepository.findByName(name); //query 조건으로 where기능
    }

    @Transactional(readOnly = true)
    public Person getPerson(Long id){
//        Person person = personRepository.findById(id).get(); // 값의 유무를 확인하지 않고 get해서 warnning발
        Person person = personRepository.findById(id).orElse(null);// java7에서 부터 지원하는 orElse()
        // -> 가지고 있는 값이 없으면 null을 return

//        System.out.println("person : " + person);
        log.info("person : {}", person); //production code에서는 systemout보다 log를 사용하는 것이 좋음
        //log를 찍었기 때문에 person과 block이 한꺼번에 호출되었음

        return person;
    }

    @Transactional
    public void put(Person person){
        personRepository.save(person);
    }

    @Transactional
    public void modify(Long id, @NotNull PersonDto personDto){
        Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("ID is not exist"));

        if(!person.getName().equals(personDto.getName())){
            throw new RuntimeException("이름이 다릅니다.");
        }
//        personAtDb.setName(personDto.getName());
//        personAtDb.setAge(personDto.getAge());
//        personAtDb.setPhoneNumber(personDto.getPhoneNumber());
//        personAtDb.setJob(personDto.getJob());
//        //personAtDb.setBirthday(personDto.getBirthday()); // localDate type이라서
//
//        if(personDto.getBirthday() != null){
//            personAtDb.setBirthday(new Birthday(personDto.getBirthday()));
//        }
//
//        personAtDb.setAddress(personDto.getAddress());
//        personAtDb.setBloodType(personDto.getBloodType());
//        personAtDb.setHobby(personDto.getHobby());
        person.set(personDto);
        personRepository.save(person);
    }

    @Transactional
    public void modify(Long id, String name) { // id값을받아서 이름만 수정하는 로직
        Person person = personRepository.findById(id).orElseThrow(()->new RuntimeException("아이디가 존재하지 않습니다."));

        person.setName(name);
        personRepository.save(person);
    }

    @Transactional
    public void delete(Long id){
        /*
        Person person = personRepository.findById(id).orElseThrow(()->new RuntimeException("아이디가 존재하지 않습니다."));

        personRepository.delete(person);
         */
//        personRepository.deleteById(id);

        Person person = personRepository.findById(id).orElseThrow(()-> new RuntimeException("아이디가 존재하지 않습니다."));

        person.setDeleted(true);

        personRepository.save(person);
    }
}

