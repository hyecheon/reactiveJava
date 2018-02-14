package com.hclee.reactivejava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReactiveJavaApplication implements ApplicationRunner {

    private final FirstExample firstExample;

    public ReactiveJavaApplication(@Autowired FirstExample firstExample) {
        this.firstExample = firstExample;
    }

    public static void main(String[] args) {
        SpringApplication.run(ReactiveJavaApplication.class, args);

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        firstExample.emit();
        /*final FirstExample firstExample = new FirstExample();
        firstExample.emit();*/
    }
}
