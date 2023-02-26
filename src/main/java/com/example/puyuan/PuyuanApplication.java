package com.example.puyuan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PuyuanApplication {

    public static void main(String[] args) {
        SpringApplication.run(PuyuanApplication.class, args);
    }

}
