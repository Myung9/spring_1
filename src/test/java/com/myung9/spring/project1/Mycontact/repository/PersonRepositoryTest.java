package com.myung9.spring.project1.Mycontact.repository;

import com.myung9.spring.project1.Mycontact.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    void crud(){
        Person person = new Person();
        person.setName("myung9");
        person.setAge(31);
        person.setBloodType("O");


        personRepository.save(person);

        System.out.println(personRepository.findAll()); // 전체 데이터를 다가져옴

        List<Person> people = personRepository.findAll();

        assertThat(people.size()).isEqualTo(1);
        assertThat(people.get(0).getName()).isEqualTo("myung9");
        assertThat(people.get(0).getAge()).isEqualTo(31);
        assertThat(people.get(0).getBloodType()).isEqualTo("O");
    }
    @Test
    void hashCodeAndEquals(){
        Person person1 = new Person("myung9", 31);
        Person person2 = new Person("myung9", 31);

        System.out.println(person1.equals(person2));
        System.out.println(person1.hashCode());
        System.out.println(person2.hashCode());
    }

}