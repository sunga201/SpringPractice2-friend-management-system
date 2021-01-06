package com.practice.project.demo.network.request;

import com.practice.project.demo.entity.dto.Birthday;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonRequest {

    private Long id;

    private String name;

    private String hobby;

    private String address;

    private boolean deleted;

    private LocalDate birthday;

    private String job;
}
