package com.myung9.spring.project1.Mycontact.domain;


//import antlr.StringUtils;

import com.myung9.spring.project1.Mycontact.controller.dto.PersonDto;
import com.myung9.spring.project1.Mycontact.domain.dto.Birthday;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Where(clause = "deleted = false")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // data.sql을 이용해서 넣는 값과 personRepository에서 넣는값이 충돌나서 strategy를 설정
    private Long id; //person 객체 하나하나를 유니크하게

    //lombok Getter, Setter로 대체
    @NonNull
    @NotEmpty
    @Column(nullable = false)
    private String name;

    private String hobby;

    private String address;

    @Valid
    @Embedded
    private Birthday birthday;

    private String job;

    private String phoneNumber;

    @ColumnDefault("0") // 0 : false
    private boolean deleted;

    public void set(PersonDto personDto){ // personDto의 setter
        if (!StringUtils.isEmpty(personDto.getHobby())){
            this.setHobby(personDto.getHobby());
        }
        if(!StringUtils.isEmpty(personDto.getHobby())){
            this.setHobby(personDto.getHobby());
        }
        if(!StringUtils.isEmpty(personDto.getAddress())){
            this.setAddress(personDto.getAddress());
        }
        if(!StringUtils.isEmpty(personDto.getJob())) {
            this.setJob(personDto.getJob());
        }
        if(!StringUtils.isEmpty(personDto.getPhoneNumber())){
            this.setPhoneNumber(personDto.getPhoneNumber());
        }
        if(personDto.getBirthday() != null){
            this.setBirthday(Birthday.of(personDto.getBirthday()));
        }
    }


    public Integer getAge(){ // primitive Type은 null이 안되기 때문에 wrapper type으로 리턴
        if(this.birthday != null) {
            return LocalDate.now().getYear() - this.birthday.getYearOfBirthday() + 1;
        }
        else{
//            return Integer.parseInt(null);
            return null;
        }
    }

    public boolean isBirthdayToday(){
        return LocalDate.now().equals(LocalDate.of(this.birthday.getYearOfBirthday(), this.birthday.getMonthOfBirthday(), this.birthday.getDayOfBirthday()));
    }
}
