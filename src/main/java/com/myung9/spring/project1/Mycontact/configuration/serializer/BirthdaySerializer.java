package com.myung9.spring.project1.Mycontact.configuration.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.myung9.spring.project1.Mycontact.domain.dto.Birthday;

import java.io.IOException;
import java.time.LocalDate;

public class BirthdaySerializer extends JsonSerializer<Birthday> {
    @Override
    public void serialize(Birthday value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if(value != null) {
            gen.writeObject(LocalDate.of(value.getYearOfBirthday(), value.getMonthOfBirthday(), value.getDayOfBirthday()));
        }
    }
}
