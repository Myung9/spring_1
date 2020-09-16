package com.myung9.spring.project1.Mycontact.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Person {
    @Id
    @GeneratedValue //자동으로 생성
    private Long id; //person 객체 하나하나를 유니크하게

    //lombok Getter, Setter로 대체
    @Getter
    @Setter
    private  String name;

    @Getter
    @Setter
    private int age;

    @Getter
    @Setter
    private String hobby;

    @Getter
    @Setter
    private String bloodType;

    @Getter
    @Setter
    private String address;

    @Getter
    @Setter
    private LocalDate birthday;

    @Getter
    @Setter
    private String job;

    //cmd n
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
