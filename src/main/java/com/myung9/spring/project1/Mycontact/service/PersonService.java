package com.myung9.spring.project1.Mycontact.service;

import com.myung9.spring.project1.Mycontact.domain.Person;
import com.myung9.spring.project1.Mycontact.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j // lombok에서 제공
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> getPeopleExcludeBlocks(){
        /*차단된 사람을 제외하고 전체 사람을 가져오는 로직*/
        /*blocks를 순회하며 이름*/
        List<Person> people = personRepository.findAll();
//        List<Block> blocks = blockRepository.findAll();
//        List<String> blockNames = blocks.stream().map(Block::getName).collect(Collectors.toList());

//        return people.stream().filter(person -> blockNames.contains(person.getName())).collect(Collectors.toList());
        return people.stream().filter(person -> person.getBlock() == null).collect(Collectors.toList());
        /*block데이터가 없는 사람들만 가져온다*/
    }

    @Transactional(readOnly = true)
    public Person getPerson(Long id){
        Person person = personRepository.findById(id).get();

//        System.out.println("person : " + person);
        log.info("person : {}", person); //production code에서는 systemout보다 log를 사용하는 것이 좋음
        //log를 찍었기 때문에 person과 block이 한꺼번에 호출되었음

        return person;
    }
}

