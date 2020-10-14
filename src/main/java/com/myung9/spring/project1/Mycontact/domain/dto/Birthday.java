package com.myung9.spring.project1.Mycontact.domain.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;


@Embeddable //entity에 속해 있는 dto라는 것을 표시
@NoArgsConstructor
@Data
public class Birthday {
    private Integer yearOfBirthday;

    @Min(1)
    @Max(12)
    private Integer monthOfBirthday;

    @Min(1)
    @Max(31)
    private Integer dayOfBirthday;


    public Birthday(LocalDate birthday){
        /*
        birthday를 LocalDate 타입으로 한번 mapping해주어서 validation이 자동으로 적용
        */
        this.yearOfBirthday = birthday.getYear();
        this.monthOfBirthday = birthday.getMonthValue();
        this.dayOfBirthday = birthday.getDayOfMonth();
    }
}
