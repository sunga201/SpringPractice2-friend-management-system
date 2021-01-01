package com.practice.project.demo.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlIDREF;
import java.time.LocalDate;

@Embeddable
@Data
@NoArgsConstructor
public class Birthday {

    //yearOfBirthday, monthOfBirthday, dayOfBirthday에 정해진 값의 범위가 없으므로
    //비정상적인 값이 들어올 수 있다. (13월이라던가, 33일이라던가..)
    //이런 값들을 체크해줘야 한다.

    private Integer yearOfBirthday;

    @Min(1)
    @Max(12)
    private Integer monthOfBirthday;

    @Min(1)
    @Max(31)
    private Integer dayOfBirthday;

    public Birthday(LocalDate birthday){
        this.yearOfBirthday = birthday.getYear();
        this.monthOfBirthday = birthday.getMonthValue();
        this.dayOfBirthday = birthday.getDayOfMonth();
    }
}