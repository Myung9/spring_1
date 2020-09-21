package com.myung9.spring.project1.Mycontact.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;



@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Person {
    @Id
    @GeneratedValue //자동으로 생성
    private Long id; //person 객체 하나하나를 유니크하게

    //lombok Getter, Setter로 대체
    @NonNull
    private String name;

    @NonNull
    private int age;

    private String hobby;

    @NonNull
    private String bloodType;

    private String address;

    private LocalDate birthday;

    private String job;

    @ToString.Exclude
    private String phoneNumber;

    private boolean block;

    private String blcokReason;

    private LocalDate blockStartDate;

    private LocalDate blockEndDate;

    /*
    public Person(Long id, @NonNull String name, @NonNull int age, String hobby, String bloodType, String address, LocalDate birthday, String job, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.hobby = hobby;
        this.bloodType = bloodType;
        this.address = address;
        this.birthday = birthday;
        this.job = job;
        this.phoneNumber = phoneNumber;
    }
    */
    //equals를 해줘야 제대로 비교가능
    /*
    public boolean equals(Object object){
        if(object == null){
            return false;
        }
        Person person = (Person) object;

        if(!person.getName().equals(this.getName())){
            return false;
        }
        return true;
    }
    //해쉬코드 오버라이딩
    public int hashCode(){
        return (name + age).hashCode();
    }
    */

}
