package com.practice.project.demo.network.response;

import com.practice.project.demo.entity.dto.Birthday;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonResponse {
    private Long id;

    private String name;

    private String hobby;

    private boolean deleted;

    private String address;

    private Birthday birthday;

    private String job;
}