package ru.abdullaev.alfabankinternshiptask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AlfabankinternshiptaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlfabankinternshiptaskApplication.class, args);
    }

}
