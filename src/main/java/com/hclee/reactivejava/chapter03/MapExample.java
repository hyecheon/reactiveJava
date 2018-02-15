package com.hclee.reactivejava.chapter03;

import com.hclee.reactivejava.common.CommonUtils;
import com.hclee.reactivejava.common.Log;
import com.hclee.reactivejava.common.MarbleDiagram;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

import static com.hclee.reactivejava.common.Shape.*;

public class MapExample implements MarbleDiagram {
    @Override
    public void marbleDiagram() {
        String[] balls = {RED, YELLOW, GREEN, BLUE};
        final Observable<String> source = Observable.fromArray(balls)
                .map(ball -> ball + "<>");
        source.subscribe(Log::i);
        CommonUtils.exampleComplete();

    }

    public void mapFunction() {
        Function<String, String> getDiamond = s -> s + "<>";
        String[] balls = {RED, YELLOW, GREEN, BLUE};
        final Observable<String> source = Observable.fromArray(balls)
                .map(getDiamond);
        source.subscribe(Log::i);
        CommonUtils.exampleComplete();
    }

    public void mappingType() {
        Function<String, Integer> getIndex = ball -> {
            switch (ball) {
                case RED:
                    return Integer.valueOf((RED));
                case YELLOW:
                    return Integer.valueOf((YELLOW));
                case GREEN:
                    return Integer.valueOf((GREEN));
                case BLUE:
                    return Integer.valueOf((BLUE));
                default:
                    return -1;

            }
        };
        String[] balls = {RED, YELLOW, GREEN, BLUE};
        final Observable<Integer> source = Observable.fromArray(balls)
                .map(getIndex);
        source.subscribe(Log::i);
        CommonUtils.exampleComplete();

    }

    public static void main(String[] args) {
        final MapExample demo = new MapExample();
        demo.marbleDiagram();
        demo.mapFunction();
        demo.mappingType();
    }
}
