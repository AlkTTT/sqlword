package com.tools.sqlword;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tools.sqlword.mapper")
public class SqlwordApplication {

    public static void main(String[] args) {
        SpringApplication.run(SqlwordApplication.class, args);
    }


}
