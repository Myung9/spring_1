package com.myung9.spring.project1.Mycontact.repository;

import com.myung9.spring.project1.Mycontact.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Person person1 = new Person("myung9", 31, "O");
        Person person2 = new Person("myung9", 31, "O");

        System.out.println(person1.equals(person2));
        System.out.println(person1.hashCode());
        System.out.println(person2.hashCode());

        Map<Person, Integer> map = new HashMap<>();
        map.put(person1, person1.getAge());

        System.out.println(map);
        System.out.println(map.get(person2));
    }

    @Test
    void findByBloodType(){
        givenPerson("myung9", 31, "O");
        givenPerson("myung8", 32, "O");
        givenPerson("myung7", 33, "Ooo");
        givenPerson("myung6", 34, "Oooo");
        givenPerson("ebenny", 6, "O");
        givenPerson("abenny", 6, "A");

        List<Person> result = personRepository.findByBloodType("O");


        result.forEach(System.out::println);
    }

    @Test
    void findByBirthdayBetween(){
        givenPerson("myung9", 31, "O", LocalDate.of(1990, 7, 9));
        givenPerson("myung8", 32, "O", LocalDate.of(1991, 4, 3));
        givenPerson("myung7", 33, "Ooo", LocalDate.of(1990, 7, 21));
        givenPerson("myung6", 34, "Oooo", LocalDate.of(1991, 3, 6));
        givenPerson("ebenny", 6, "O", LocalDate.of(1990, 4, 3));
        givenPerson("abenny", 6, "A", LocalDate.of(1991, 4, 21));

        List<Person> result = personRepository.findByBirthdayBetween(LocalDate.of(1990, 7, 1), LocalDate.of(1990, 7, 31));

        result.forEach(System.out::println);
    }

    //임시 대안으로 메소드 오버로딩
    private void givenPerson(String name, int age, String bloodType){
        givenPerson(name, age, bloodType, null);
    }

    private void givenPerson(String name, int age, String bloodType, LocalDate birthday){
        Person person = new Person(name, age, bloodType);
        person.setBirthday(birthday);

        personRepository.save(person);
    }
}