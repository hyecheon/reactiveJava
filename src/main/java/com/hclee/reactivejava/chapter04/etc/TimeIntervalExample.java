package com.hclee.reactivejava.chapter04.etc;

import com.hclee.reactivejava.common.CommonUtils;
import com.hclee.reactivejava.common.Log;
import com.hclee.reactivejava.common.MarbleDiagram;
import io.reactivex.Observable;
import io.reactivex.schedulers.Timed;

public class TimeIntervalExample implements MarbleDiagram {
    @Override
    public void marbleDiagram() {
        String[] data = {"RED", "GREEN", "ORANGE"};

        CommonUtils.exampleStart();
        Observable<Timed<String>> source = Observable.fromArray(data)
                .delay(item -> {
                    CommonUtils.doSomething();
                    return Observable.just(item);
                })
                .timeInterval();

        source.subscribe(Log::it);
        CommonUtils.sleep(1000);
    }

    public static void main(String[] args) {
        TimeIntervalExample demo = new TimeIntervalExample();
        demo.marbleDiagram();
    }
}