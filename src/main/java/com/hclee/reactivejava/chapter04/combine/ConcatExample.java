package com.hclee.reactivejava.chapter04.combine;

import com.hclee.reactivejava.common.CommonUtils;
import com.hclee.reactivejava.common.Log;
import com.hclee.reactivejava.common.MarbleDiagram;
import io.reactivex.Observable;
import io.reactivex.functions.Action;

import java.util.concurrent.TimeUnit;

public class ConcatExample implements MarbleDiagram {
    @Override
    public void marbleDiagram() {
        Action onCompleteAction = () -> Log.d("onComplete()");

        String[] data1 = {"RED", "GREEN", "BLUE"};
        String[] data2 = {"YELLOW", "SKY", "PUPPLE"};

        final Observable<String> source1 = Observable.fromArray(data1)
                .doOnComplete(onCompleteAction);
        final Observable<String> source2 = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(index -> data2[index])
                .take(data2.length)
                .doOnComplete(onCompleteAction);
        final Observable<String> source = Observable.concat(source1, source2)
                .doOnComplete(onCompleteAction);
        source.subscribe(Log::i);
        CommonUtils.sleep(1000);
        CommonUtils.exampleComplete();
    }

    public static void main(String[] args) {
        final ConcatExample demo = new ConcatExample();
        demo.marbleDiagram();
    }
}
