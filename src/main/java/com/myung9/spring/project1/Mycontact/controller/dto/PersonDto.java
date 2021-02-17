package com.myung9.spring.project1.Mycontact.controller.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonDto {
    private String name;
    private int age;
    private String hobby;
    private String bloodType;
    private String address;
    //private Birthday birthday;
    private LocalDate birthday;
    private String job;
    private String phoneNumber;
}