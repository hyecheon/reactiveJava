package com.hclee.reactivejava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReactivejavaApplication implements ApplicationRunner {

    private final FirstExample firstExample;

    public ReactivejavaApplication(@Autowired FirstExample firstExample) {
        this.firstExample = firstExample;
    }

    public static void main(String[] args) {
        SpringApplication.run(ReactivejavaApplication.class, args);

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        firstExample.emit();
        /*final FirstExample firstExample = new FirstExample();
        firstExample.emit();*/
    }
}
