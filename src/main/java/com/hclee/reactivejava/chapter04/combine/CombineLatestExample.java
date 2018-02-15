package com.hclee.reactivejava.chapter04.combine;

import com.hclee.reactivejava.common.CommonUtils;
import com.hclee.reactivejava.common.Log;
import com.hclee.reactivejava.common.MarbleDiagram;
import com.hclee.reactivejava.common.Shape;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

import static com.hclee.reactivejava.common.Shape.*;

public class CombineLatestExample implements MarbleDiagram {
    @Override
    public void marbleDiagram() {
        String[] data1 = {PUPPLE, ORANGE, SKY, YELLOW};//6, 7, 4, 2
        String[] data2 = {DIAMOND, STAR, PENTAGON};
        Observable<String> source = Observable.combineLatest(
                Observable.fromArray(data1).zipWith(
                        Observable.interval(100L, TimeUnit.MILLISECONDS),
                        (shape, notUsed) -> Shape.getColor(shape)),
                Observable.fromArray(data2).zipWith(
                        Observable.interval(150L, 200L, TimeUnit.MILLISECONDS),
                        (shape, notUsed) -> Shape.getSuffix(shape)),
                (s, u) -> s + u);
        source.subscribe(Log::i);
        CommonUtils.sleep(1000);
        CommonUtils.exampleComplete();
    }

    public void tmp01() {
        final Observable<String> source1 = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(CommonUtils::numberToAlphabet);
        final Observable<Long> source2 = Observable.interval(200L, TimeUnit.MILLISECONDS);
        final Observable<String> source = Observable.combineLatest(source1, source2, (s1, s2) -> s1 + s2);
        source.subscribe(System.out::println);
        CommonUtils.sleep(500);
        CommonUtils.exampleComplete();
    }

    public static void main(String[] args) {
        final CombineLatestExample demo = new CombineLatestExample();
        demo.tmp01();
    }
}
