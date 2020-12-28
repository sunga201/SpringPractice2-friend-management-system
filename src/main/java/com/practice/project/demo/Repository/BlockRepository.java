package com.practice.project.demo.Repository;

import com.practice.project.demo.entity.Block;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepository extends JpaRepository<Block, Long> {

}
