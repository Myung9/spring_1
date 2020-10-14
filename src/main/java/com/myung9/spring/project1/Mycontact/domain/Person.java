package com.myung9.spring.project1.Mycontact.domain;


import com.myung9.spring.project1.Mycontact.domain.dto.Birthday;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // data.sql을 이용해서 넣는 값과 personRepository에서 넣는값이 충돌나서 strategy를 설정
    private Long id; //person 객체 하나하나를 유니크하게

    //lombok Getter, Setter로 대체
    @NonNull
    @NotEmpty
    @Column(nullable = false)
    private String name;

    @NonNull
    @Min(1)
    private int age;

    private String hobby;

    @NonNull
    @NotEmpty
    @Column(nullable = false)
    private String bloodType;

    private String address;

    @Valid
    @Embedded
    private Birthday birthday;

    private String job;

    @ToString.Exclude
    private String phoneNumber;

//    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})  // person entity에서 block에 대한 영속성을 함께 관리하겠다
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true) // optional = false로 block객체의 값은 항상 필요하다
    @ToString.Exclude //불필요한 query 호출을 줄이는데 도움이 됨
    private Block block;
    //CascadeType.MERGE를 해서 Test의 'start date' 'end date'를 출력하게 한다
    //CascadeType.REMOVE를 해서 Test에서의 entity를 수정한다
    //CascadeType.ALL로 하면 전체가 다 적용됨

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
