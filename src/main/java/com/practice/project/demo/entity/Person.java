package com.practice.project.demo.entity;

import com.practice.project.demo.entity.dto.Birthday;
import com.practice.project.demo.network.request.PersonRequest;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
@Where(clause = "deleted = false")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotEmpty
    @Column(nullable = false)
    private String name;

    private String hobby;

    @ColumnDefault("0")
    private boolean deleted;

    private String address;

    @Valid
    @Embedded
    private Birthday birthday;

    private String job;

    public void set(PersonRequest body){
        if(body.getName()!=null){
            this.name=body.getName();
        }

        if(body.getBirthday()!=null){
            this.birthday=body.getBirthday();
        }

        if(body.getHobby()!=null){
            this.hobby=body.getHobby();
        }

        if(body.getJob()!=null){
            this.job=body.getJob();
        }
    }

    public boolean isBirthdayToday(){
        if(birthday == null) return false;
        return birthday.isBirthdayToday();
    }

    public Integer getAge(){
        if(birthday == null) return null;
        return birthday.getAge();
    }
}
