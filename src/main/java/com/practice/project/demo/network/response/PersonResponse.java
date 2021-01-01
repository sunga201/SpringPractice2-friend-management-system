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

    @NonNull
    @NotEmpty
    @Column(nullable = false)
    private String name;

    @NonNull
    @Min(1)
    @Column(nullable = false)
    private Integer age;

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

    private int blockId;
}