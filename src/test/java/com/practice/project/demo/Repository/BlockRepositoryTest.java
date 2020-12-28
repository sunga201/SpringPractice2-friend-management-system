package com.practice.project.demo.Repository;

import com.practice.project.demo.entity.Block;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlockRepositoryTest {
    @Autowired
    BlockRepository blockRepository;

    @Test
    public void crud(){
        Block block = Block.builder()
                .name("martin")
                .reason("안친해서")
                .build();

        blockRepository.save(block);

        System.out.println(blockRepository.findAll());
    }
}