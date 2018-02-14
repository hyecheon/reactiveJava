package com.hclee.reactivejava.chapter02.subjects;

import io.reactivex.subjects.ReplaySubject;

import static com.hclee.reactivejava.common.Shape.BLUE;
import static com.hclee.reactivejava.common.Shape.GREEN;
import static com.hclee.reactivejava.common.Shape.RED;


public class ReplaySubjectExample {
    public void marbleDiagram() {
        ReplaySubject<String> subject = ReplaySubject.create();
        subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
        subject.onNext(RED);
        subject.onNext(GREEN);
        subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
        subject.onNext(BLUE);
        subject.onComplete();
    }

    public static void main(String[] args) {
        ReplaySubjectExample demo = new ReplaySubjectExample();
        demo.marbleDiagram();
    }
}
