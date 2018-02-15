package com.hclee.reactivejava.chapter04.etc;

import com.hclee.reactivejava.common.CommonUtils;
import com.hclee.reactivejava.common.Log;
import com.hclee.reactivejava.common.MarbleDiagram;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

import static com.hclee.reactivejava.common.Shape.*;

public class DelayExample implements MarbleDiagram {
    @Override
    public void marbleDiagram() {
        CommonUtils.exampleStart();
        String[] data = {RED, ORANGE, YELLOW, GREEN, SKY};
        Observable<String> source = Observable.fromArray(data)
                .delay(100L, TimeUnit.MILLISECONDS);
        source.subscribe(Log::it);
        CommonUtils.sleep(1000);
        CommonUtils.exampleComplete();
    }

    public static void main(String[] args) {
        final DelayExample demo = new DelayExample();
        demo.marbleDiagram();
    }
}
