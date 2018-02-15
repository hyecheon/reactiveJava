package com.hclee.reactivejava.chapter04.combine;

import com.hclee.reactivejava.common.CommonUtils;
import com.hclee.reactivejava.common.Log;
import com.hclee.reactivejava.common.MarbleDiagram;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

import static com.hclee.reactivejava.common.Shape.*;

public class MergeExample implements MarbleDiagram {
    @Override
    public void marbleDiagram() {
        final String[] data1 = {RED, GREEN};
        final String[] data2 = {YELLOW, SKY, PUPPLE};

        final Observable<String> source1 = Observable.interval(0L, 100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(idx -> data1[idx])
                .take(data1.length);
        final Observable<String> source2 = Observable.interval(50L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(idx -> data2[idx])
                .take(data2.length);
        final Observable<String> source = Observable.merge(source1, source2);
        source.subscribe(Log::i);
        CommonUtils.sleep(1000);
        CommonUtils.exampleComplete();
    }

    public static void main(String[] args) {
        final MergeExample mergeExample = new MergeExample();
        mergeExample.marbleDiagram();
    }
}
