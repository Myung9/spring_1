package com.myung9.spring.project1.Mycontact.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@ToString
public class Person {
    @Id
    @GeneratedValue //자동으로 생성
    private Long id; //person 객체 하나하나를 유니크하게

    //lombok Getter, Setter로 대체

    private  String name;

    private int age;

    private String hobby;

    private String bloodType;

    private String address;

    private LocalDate birthday;

    private String job;

    //cmd n


}
