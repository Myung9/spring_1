package com.myung9.spring.project1.Mycontact.repository;

import com.myung9.spring.project1.Mycontact.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//cmd shift t -> new test
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByName(String name);


//    @Query(value = "select person from Person person where person.birthday.monthOfBirthday = ?1 and person.birthday.dayOfBirthday = ?2")
//    List<Person> findByMonthOfBirthday(int monthOfBirthday, int dayOfBirthday);

    @Query(value = "select person from Person person where person.birthday.monthOfBirthday = :monthOfBirthday")
    List<Person> findByMonthOfBirthday(@Param("monthOfBirthday") int monthOfBirthday);

//    List<Person> testBirthday(@Param("monthOfBirthday") int monthOfBirhday, @Param("dayOfBirthday") int dayOfBirthday);

    //nativeQuery를 사용해서 실제 entity를 사용하지 않는 쿼리를 사용
    @Query(value = "select * from Person person where person.deleted = true", nativeQuery = true)
    List<Person> findPeopleDeleted();
}
