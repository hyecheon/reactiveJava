package com.hclee.reactivejava.chapter02;

import com.hclee.reactivejava.common.CommonUtils;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class ObservableCreateExample {
    public void basic() {
        final Observable<Integer> source = Observable.create(e -> {
            e.onNext(100);
            e.onNext(200);
            e.onNext(300);
            e.onComplete();
        });
        source.subscribe(System.out::println);
        CommonUtils.exampleComplete();
    }

    public void notSubscribed() {
        final Observable<Integer> source = Observable.create(e -> {
            e.onNext(100);
            e.onNext(200);
            e.onNext(300);
            e.onComplete();
        });
        CommonUtils.exampleComplete();
    }

    public void subscribeLambda() {
        final Observable<Integer> source = Observable.create(e -> {
            e.onNext(100);
            e.onNext(200);
            e.onNext(300);
            e.onComplete();
        });
        source.subscribe(data -> System.out.println("Result : " + data));
        CommonUtils.exampleComplete();
    }

    public void subscribeAnonymously() {
        final Observable<Integer> source = Observable.create(e -> {
            e.onNext(100);
            e.onNext(200);
            e.onNext(300);
            e.onComplete();
        });
        source.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer data) throws Exception {
                System.out.println("Result : " + data);
            }
        });

    }

    public static void main(String[] args) {
        final ObservableCreateExample observableCreateExample = new ObservableCreateExample();
        observableCreateExample.basic();
        observableCreateExample.notSubscribed();
        observableCreateExample.subscribeLambda();
        observableCreateExample.subscribeAnonymously();
    }
}
