package com.myung9.spring.project1.Mycontact.repository;


import com.myung9.spring.project1.Mycontact.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

//cmd shift t -> new test
public interface PersonRepository extends JpaRepository<Person, Long> {
}
