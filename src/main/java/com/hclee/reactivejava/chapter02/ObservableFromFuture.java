package com.hclee.reactivejava.chapter02;

import com.hclee.reactivejava.common.CommonUtils;
import io.reactivex.Observable;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ObservableFromFuture {
    public void basic() {
        final Future<String> future = Executors.newSingleThreadExecutor()
                .submit(() -> {
                    Thread.sleep(1000);
                    return "Hello Future";
                });
        final Observable<String> source = Observable.fromFuture(future);
        source.subscribe(System.out::println);
        CommonUtils.exampleComplete();
    }

    public void withoutLambda() {
        Future<String> future = Executors.newSingleThreadExecutor()
                .submit(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        Thread.sleep(1000);
                        return "Hello Future(No Lambda)";
                    }
                });
        Observable<String> source = Observable.fromFuture(future);
        source.subscribe(System.out::println);
    }

    public static void main(String[] args) {
        final ObservableFromFuture demo = new ObservableFromFuture();
        demo.basic();
        demo.withoutLambda();
    }
}
