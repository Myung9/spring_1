package com.myung9.spring.project1.Mycontact.service;

import com.myung9.spring.project1.Mycontact.domain.Block;
import com.myung9.spring.project1.Mycontact.domain.Person;
import com.myung9.spring.project1.Mycontact.repository.BlockRepository;
import com.myung9.spring.project1.Mycontact.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/*서비스 테스트경우 보통 mocktest를 많이 사용*/
@SpringBootTest
class PersonServiceTest {
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private BlockRepository blockRepository;

    @Test
    void getPeopleExcludeBlocks(){
        givenPeople();
        givenBlocks();

        List<Person> result = personService.getPeopleExcludeBlocks();

//        System.out.println(result);
        result.forEach(System.out::println); // 리스트에 객체가 각 한 줄씩 노출
    }
    private void givenBlockPerson(String name, int age, String bloodType){
        Person blockPerson = new Person(name, age, bloodType);
        blockPerson.setBlock(givenBlock(name));

        personRepository.save(blockPerson);
    }

    private void givenBlocks() {
        givenBlock("myung9");
    }

    private Block givenBlock(String name) {

        return blockRepository.save(new Block(name));
    }

    private void givenPeople() {
        givenPerson("myung9", 31, "O");
        givenPerson("myung8", 32, "O");
        givenBlockPerson("myung7", 33, "O");
        givenBlockPerson("myung9", 34, "O");
    }

    private void givenPerson(String name, int age, String bloodType) {
        personRepository.save(new Person(name, age, bloodType));
    }
}