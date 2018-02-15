package com.hclee.reactivejava.chapter04.create;

import com.hclee.reactivejava.common.Log;
import io.reactivex.Observable;

public class RangeExample {
    public void forLoop() {
        final Observable<Integer> source = Observable.range(1, 10)
                .filter(n -> n % 2 == 0);
        source.subscribe(Log::i);
    }

    public static void main(String[] args) {
        final RangeExample rangeExample = new RangeExample();
        rangeExample.forLoop();
    }
}
