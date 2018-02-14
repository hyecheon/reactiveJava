package com.hclee.reactivejava.chapter02;

import com.hclee.reactivejava.common.CommonUtils;
import com.hclee.reactivejava.common.MarbleDiagram;
import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

import java.util.concurrent.TimeUnit;

import static com.hclee.reactivejava.common.Shape.BLUE;
import static com.hclee.reactivejava.common.Shape.GREEN;
import static com.hclee.reactivejava.common.Shape.RED;

public class ConnectableObservableExample implements MarbleDiagram {
    @Override
    public void marbleDiagram() {
        String[] dt = {RED, GREEN, BLUE};
        final Observable<String> balls = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(i -> dt[i])
                .take(dt.length);
        final ConnectableObservable<String> source = balls.publish();
        source.subscribe(data -> System.out.println("Subscriber #1 => " + data));
        source.subscribe(data -> System.out.println("Subscriber #2 => " + data));
        source.connect();

        CommonUtils.sleep(250);
        source.subscribe(data -> System.out.println("Subscriber #3 => " + data));
        CommonUtils.sleep(100);
        CommonUtils.exampleComplete();
    }

    public static void main(String[] args) {
        final ConnectableObservableExample connectableObservableExample = new ConnectableObservableExample();
        connectableObservableExample.marbleDiagram();
    }
}
