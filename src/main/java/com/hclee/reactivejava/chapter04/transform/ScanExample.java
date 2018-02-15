package com.hclee.reactivejava.chapter04.transform;

import com.hclee.reactivejava.common.CommonUtils;
import com.hclee.reactivejava.common.Log;
import com.hclee.reactivejava.common.MarbleDiagram;
import io.reactivex.Observable;

import static com.hclee.reactivejava.common.Shape.BLUE;
import static com.hclee.reactivejava.common.Shape.GREEN;
import static com.hclee.reactivejava.common.Shape.RED;

public class ScanExample implements MarbleDiagram {
    public void marbleDiagram() {
        String[] balls = {RED, GREEN, BLUE}; //1,3,5
        Observable<String> source = Observable.fromArray(balls)
                .scan((ball1, ball2) -> ball2 + "(" + ball1 + ")");
        source.subscribe(Log::i);
        CommonUtils.exampleComplete();
    }

    public static void main(String[] args) {
        ScanExample demo = new ScanExample();
        demo.marbleDiagram();
    }
}
