package com.practice.project.demo.configuration.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.practice.project.demo.entity.dto.Birthday;

import java.io.IOException;
import java.time.LocalDate;

public class BirthdaySerializer extends JsonSerializer {

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if(value!=null){
            Birthday birthday=(Birthday)value;
            gen.writeObject(LocalDate.of(birthday.getYearOfBirthday(),
                    birthday.getMonthOfBirthday(),
                    birthday.getDayOfBirthday()));
        }
    }
}
