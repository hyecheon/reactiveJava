package com.hclee.reactivejava.chapter03;

import com.hclee.reactivejava.common.CommonUtils;
import com.hclee.reactivejava.common.MarbleDiagram;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;


import static com.hclee.reactivejava.common.Shape.*;

public class ReduceExample implements MarbleDiagram {
    @Override
    public void marbleDiagram() {
        String[] balls = {RED, GREEN, BLUE}; //1,3,5
        final Maybe<String> source = Observable.fromArray(balls)
                .reduce((s1, s2) -> String.format("%s ( %s )", s2, s1));
        source.subscribe(System.out::println);
        CommonUtils.exampleComplete();
    }

    public void biFunction() {
        BiFunction<String, String, String> mergeBalls =
                (s1, s2) -> String.format("%s ( %s )", s2, s1);
        String[] balls = {RED, GREEN, BLUE};
        final Maybe<String> source = Observable.fromArray(balls)
                .reduce(mergeBalls);
        source.subscribe(System.out::println);
        CommonUtils.exampleComplete();
    }

    public static void main(String[] args) {
        final ReduceExample demo = new ReduceExample();
        demo.marbleDiagram();
        demo.biFunction();
    }
}
