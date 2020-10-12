package com.myung9.spring.project1.Mycontact.service;

import com.myung9.spring.project1.Mycontact.domain.Person;
import com.myung9.spring.project1.Mycontact.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
//        return people.stream().filter(person -> person.getBlock() == null).collect(Collectors.toList());
        /*block데이터가 없는 사람들만 가져온다*/
        return personRepository.findByBlockIsNull();
    }

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
}

