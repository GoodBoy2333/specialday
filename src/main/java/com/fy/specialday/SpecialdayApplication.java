package com.fy.specialday;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fy.specialday.Mapper")
public class SpecialdayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpecialdayApplication.class, args);
    }

}
