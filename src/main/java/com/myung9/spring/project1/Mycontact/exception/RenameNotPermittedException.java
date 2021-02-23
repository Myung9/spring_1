package com.myung9.spring.project1.Mycontact.exception;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RenameNotPermittedException extends RuntimeException{
    private static final String MESSAGE = "이름 변경 허용되지 않습니다.";

    public RenameNotPermittedException(){
        super(MESSAGE);
        log.error(MESSAGE);
    }
}
