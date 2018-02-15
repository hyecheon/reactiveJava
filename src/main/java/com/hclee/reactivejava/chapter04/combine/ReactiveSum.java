package com.hclee.reactivejava.chapter04.combine;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.observables.ConnectableObservable;

import java.util.Scanner;

public class ReactiveSum {
    public void run() {
        final ConnectableObservable<String> source = userInput();
        final Observable<Integer> a = source.filter(s -> s.startsWith("a:"))
                .map(s -> s.replace("a:", ""))
                .map(Integer::parseInt);
        final Observable<Integer> b = source.filter(s -> s.startsWith("b:"))
                .map(s -> s.replace("b:", ""))
                .map(Integer::parseInt);

        Observable.combineLatest(a.startWith(0), b.startWith(0), (x, y) -> x + y)
                .skip(1)
                .subscribe(result -> System.out.println("Result : " + result));
        source.connect();
    }

    private ConnectableObservable<String> userInput() {
        return Observable.create((ObservableEmitter<String> e) -> {
            try (Scanner in = new Scanner(System.in)) {
                while (true) {
                    System.out.print("Input : ");
                    final String line = in.nextLine();
                    e.onNext(line);
                    if (line.indexOf("exit") >= 0) {
                        break;
                    }
                }
            }
        }).publish();
    }

    public static void main(String[] args) {
        new ReactiveSum().run();
    }

}
