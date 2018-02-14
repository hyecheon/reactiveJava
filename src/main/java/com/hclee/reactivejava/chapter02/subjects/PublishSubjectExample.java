package com.hclee.reactivejava.chapter02.subjects;

import io.reactivex.subjects.PublishSubject;

import static com.hclee.reactivejava.common.Shape.BLUE;
import static com.hclee.reactivejava.common.Shape.GREEN;
import static com.hclee.reactivejava.common.Shape.RED;

public class PublishSubjectExample {
    public void marbleDiagram() {
        PublishSubject<String> subject = PublishSubject.create();
        subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
        subject.onNext(RED);
        subject.onNext(GREEN);
        subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
        subject.onNext(BLUE);
        subject.onComplete();
    }

    public static void main(String[] args) {
        PublishSubjectExample demo = new PublishSubjectExample();
        demo.marbleDiagram();
    }
}
