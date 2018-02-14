package com.hclee.reactivejava;

import io.reactivex.Observable;
import org.springframework.stereotype.Component;

@Component
public class FirstExample {
    public void emit() {
        Observable.just("Hello", "RxJava 2 !!")
                .subscribe(System.out::println);
    }

}
