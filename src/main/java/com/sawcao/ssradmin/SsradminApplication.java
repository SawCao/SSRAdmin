package com.sawcao.ssradmin;

import com.sawcao.ssradmin.admin.controller.MainControll;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class SsradminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsradminApplication.class, args);
    }
}
