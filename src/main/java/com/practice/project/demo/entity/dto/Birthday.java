package com.practice.project.demo.entity.dto;

import lombok.*;

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

    /*@Min(1) // LocalDate로 체크하므로, 필요없다.
    @Max(12)*/
    private Integer monthOfBirthday;

    /*@Min(1) // LocalDate로 체크하므로, 필요없다.
    @Max(31)*/
    private Integer dayOfBirthday;

    private Birthday(LocalDate birthday){
        this.yearOfBirthday = birthday.getYear();
        this.monthOfBirthday = birthday.getMonthValue();
        this.dayOfBirthday = birthday.getDayOfMonth();
    }

    public int age(){
        return LocalDate.now().getYear() - this.yearOfBirthday + 1;
    }

    public boolean birthdayToday(){
        return LocalDate.now().equals(LocalDate.of(yearOfBirthday, monthOfBirthday, dayOfBirthday));
    }
    public static Birthday of(LocalDate birthday){
        return new Birthday(birthday);
    }
}
