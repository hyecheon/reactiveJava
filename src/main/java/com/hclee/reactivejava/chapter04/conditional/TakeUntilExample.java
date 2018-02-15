package com.hclee.reactivejava.chapter04.conditional;

import com.hclee.reactivejava.common.CommonUtils;
import com.hclee.reactivejava.common.Log;
import com.hclee.reactivejava.common.MarbleDiagram;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

import static com.hclee.reactivejava.common.Shape.*;

public class TakeUntilExample implements MarbleDiagram {
    @Override
    public void marbleDiagram() {
        final String[] data = {RED, YELLOW, GREEN, SKY, BLUE, PUPPLE};
        final Observable<String> source = Observable.fromArray(data)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS),
                        (val, notUsed) -> val)
                .takeUntil(Observable.timer(500L, TimeUnit.MILLISECONDS));
        source.subscribe(Log::i);
        CommonUtils.sleep(1000);
        CommonUtils.exampleComplete();
    }

    public static void main(String[] args) {
        TakeUntilExample demo = new TakeUntilExample();
        demo.marbleDiagram();
    }
}
