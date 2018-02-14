package com.hclee.reactivejava.chapter02;

import com.hclee.reactivejava.common.CommonUtils;
import io.reactivex.Observable;

import java.util.concurrent.Callable;

public class ObservableFromCallable {
    public void basic() {
        Callable<String> callable = () -> {
            Thread.sleep(1000);
            return "Hello Callable";
        };
        final Observable<String> source = Observable.fromCallable(callable);
        source.subscribe(System.out::println);
        CommonUtils.exampleComplete();
    }

    public void withoutLambda() {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return "Hello Callable";
            }
        };

        final Observable<String> source = Observable.fromCallable(callable);
        source.subscribe(System.out::println);
        CommonUtils.exampleComplete();
    }

    public static void main(String[] args) {
        final ObservableFromCallable demo = new ObservableFromCallable();
        demo.basic();
        demo.withoutLambda();
    }
}
