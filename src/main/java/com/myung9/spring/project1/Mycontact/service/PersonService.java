package com.myung9.spring.project1.Mycontact.service;

import com.myung9.spring.project1.Mycontact.domain.Block;
import com.myung9.spring.project1.Mycontact.domain.Person;
import com.myung9.spring.project1.Mycontact.repository.BlockRepository;
import com.myung9.spring.project1.Mycontact.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private BlockRepository blockRepository;

    public List<Person> getPeopleExcludeBlocks(){
        /*차단된 사람을 제외하고 전체 사람을 가져오는 로직*/
        /*blocks를 순회하며 이름*/
        List<Person> people = personRepository.findAll();
        List<Block> blocks = blockRepository.findAll();
        List<String> blockNames = blocks.stream().map(Block::getName).collect(Collectors.toList());

        return people.stream().filter(person -> blockNames.contains(person.getName())).collect(Collectors.toList());
    }
}
