package com.hclee.reactivejava.chapter02;

import com.hclee.reactivejava.common.CommonUtils;
import io.reactivex.Observable;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

public class ObservableFromPublisher {
    public void basic() {
        Publisher<String> publisher = (Subscriber<? super String> s) -> {
            s.onNext("Hello Observable.fromPublisher()");
            s.onComplete();
        };
        final Observable<String> source = Observable.fromPublisher(publisher);
        source.subscribe(s -> System.out.println(s));
        CommonUtils.exampleComplete();
    }

    public void withoutLambda() {
        Publisher<String> publisher = new Publisher<String>() {
            @Override
            public void subscribe(Subscriber<? super String> s) {
                s.onNext("Hello Observable.fromPublisher()");
                s.onComplete();
            }
        };
        Observable<String> source = Observable.fromPublisher(publisher);
        source.subscribe(System.out::println);
        CommonUtils.exampleComplete();
    }

    public static void main(String[] args) {
        ObservableFromPublisher demo = new ObservableFromPublisher();
        demo.basic();
        demo.withoutLambda();
    }
}
