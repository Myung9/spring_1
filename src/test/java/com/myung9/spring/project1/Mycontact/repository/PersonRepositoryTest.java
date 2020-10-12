package com.myung9.spring.project1.Mycontact.repository;

import com.myung9.spring.project1.Mycontact.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    void crud(){
        Person person = new Person();
        person.setName("john");
        person.setAge(31);
        person.setBloodType("O");

        personRepository.save(person);

        List<Person> result = personRepository.findByName("john");

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("john");
        assertThat(result.get(0).getAge()).isEqualTo(31);
        assertThat(result.get(0).getBloodType()).isEqualTo("O");
    }


    @Test
    void findByBloodType(){
        List<Person> result = personRepository.findByBloodType("A");

        result.forEach(System.out::println);

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getName()).isEqualTo("martin");
        assertThat(result.get(1).getName()).isEqualTo("benny");
    }

    @Test
    void findByBirthdayBetween(){
        List<Person> result = personRepository.findByMonthOfBirthday(8);

        System.out.println(result.size());
        result.forEach(System.out::println);

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getName()).isEqualTo("martin");
        assertThat(result.get(1).getName()).isEqualTo("sophia");
    }
}