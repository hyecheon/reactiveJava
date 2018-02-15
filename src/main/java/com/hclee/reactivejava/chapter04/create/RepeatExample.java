package com.hclee.reactivejava.chapter04.create;

import com.hclee.reactivejava.common.CommonUtils;
import com.hclee.reactivejava.common.Log;
import com.hclee.reactivejava.common.MarbleDiagram;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

import static com.hclee.reactivejava.common.Shape.*;

public class RepeatExample implements MarbleDiagram {
    @Override
    public void marbleDiagram() {
        String[] balls = {RED, GREEN, BLUE};
        final Observable<String> source = Observable.fromArray(balls)
                .repeat(3);
        source.doOnComplete(() -> Log.d("onComplete"))
                .subscribe(Log::i);
        CommonUtils.exampleComplete();
    }

    public void heartbeatV1() {
        CommonUtils.exampleStart();
        final String serverUrl = "https://api.github.com/zen";
        Observable.timer(2, TimeUnit.SECONDS)
                .map(val -> serverUrl)
                .repeat()
                .subscribe(res -> Log.it("Ping Result : " + res));
        CommonUtils.sleep(10000);
        CommonUtils.exampleComplete();
    }

    public static void main(String[] args) {
        final RepeatExample demo = new RepeatExample();
        demo.marbleDiagram();
        demo.heartbeatV1();
    }
}
