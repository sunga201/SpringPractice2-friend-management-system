package com.practice.project.demo.Repository;

import com.practice.project.demo.entity.Block;
import com.practice.project.demo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<List<Person>> findByName(String name);

    Optional<List<Person>> findByBlockIsNull();

    Optional<List<Person>> findByBloodType(String bloodType);

    @Query(value = "select * from person where month_of_birthday = :monthOfBirthday",
            nativeQuery = true)
    Optional<List<Person>> findByMonthOfBirthday(@Param("monthOfBirthday") int monthOfBirthday);

    @Query(value = "select * from person where deleted is true", nativeQuery = true)
    Optional<List<Person>> findByDeletedIsTrue();
}
