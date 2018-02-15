package com.hclee.reactivejava.chapter04.conditional;

import com.hclee.reactivejava.common.CommonUtils;
import com.hclee.reactivejava.common.Log;
import com.hclee.reactivejava.common.MarbleDiagram;
import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.hclee.reactivejava.common.Shape.*;

public class AmbExample implements MarbleDiagram {
    @Override
    public void marbleDiagram() {
        String[] data1 = {RED, GREEN, BLUE};
        String[] data2 = {rectangle(YELLOW), rectangle(SKY)};

        final List<Observable<String>> sources = Arrays.asList(
                Observable.fromArray(data1)
                        .doOnComplete(() -> Log.d("Observable #1 : onComplete()")),
                Observable.fromArray(data2)
                        .delay(100L, TimeUnit.MILLISECONDS)
                        .doOnComplete(() -> Log.d("Observable #2 : onComplete()"))
        );
        Observable.amb(sources)
                .doOnComplete(() -> Log.d("Result : onComplete()"))
                .subscribe(Log::i);
        CommonUtils.sleep(1000);
        CommonUtils.exampleComplete();
    }

    public static void main(String[] args) {
        final AmbExample ambExample = new AmbExample();
        ambExample.marbleDiagram();
    }
}
