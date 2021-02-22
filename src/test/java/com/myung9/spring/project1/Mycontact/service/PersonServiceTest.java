package com.myung9.spring.project1.Mycontact.service;

import com.myung9.spring.project1.Mycontact.domain.Person;
import com.myung9.spring.project1.Mycontact.repository.PersonRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/*서비스 테스트경우 보통 mocktest를 많이 사용*/
@ExtendWith(MockitoExtension.class) // mockito 기능을 사용해서 테스트를 진
class PersonServiceTest {
    @InjectMocks // 테스트의 주체 // 테스트의 대상이 되는 곳에 달아주는 어노테이션
    private PersonService personService;
    @Mock//PersonService에 의존성을 가지고 있는 PersonRepository
    private PersonRepository personRepository;

    @Test
    void getPeopleByName(){
        when(personRepository.findByName("martin"))
           .thenReturn(Lists.newArrayList(new Person("martin")));

        List<Person> result = personService.getPeopleByName("martin");

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("martin");
    }
}