package com.hclee.reactivejava.chapter03;

import com.hclee.reactivejava.common.CommonUtils;
import com.hclee.reactivejava.common.Log;
import com.hclee.reactivejava.common.MarbleDiagram;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

import static com.hclee.reactivejava.common.Shape.*;

public class FlatMapExample implements MarbleDiagram {
    @Override
    public void marbleDiagram() {
        Function<String, Observable<String>> getDoubleDiamonds =
                ball -> Observable.just(ball + "<>", ball + "<>");
        String[] balls = {RED, GREEN, BLUE};
        final Observable<String> source = Observable.fromArray(balls)
                .flatMap(getDoubleDiamonds);
        source.subscribe(Log::i);
        CommonUtils.exampleComplete();
    }

    public void flatMapLambda() {
        String[] balls = {RED, GREEN, BLUE};
        final Observable<String> source = Observable.fromArray(balls)
                .flatMap(s -> Observable.just(s + "<>", s + "<>"));
        source.subscribe(Log::i);
        CommonUtils.exampleComplete();
    }

    public static void main(String[] args) {
        final FlatMapExample demo = new FlatMapExample();
        demo.marbleDiagram();
        demo.flatMapLambda();
    }
}
