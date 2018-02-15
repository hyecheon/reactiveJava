package com.hclee.reactivejava.chapter04.create;

import com.hclee.reactivejava.common.CommonUtils;
import com.hclee.reactivejava.common.Log;
import com.hclee.reactivejava.common.MarbleDiagram;
import com.hclee.reactivejava.common.Shape;
import io.reactivex.Observable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.Callable;


import static com.hclee.reactivejava.common.Shape.*;

public class DeferExample implements MarbleDiagram {
    Iterator<String> colors = Arrays.asList(RED, GREEN, BLUE, PUPPLE).iterator();

    @Override
    public void marbleDiagram() {
        Callable<Observable<String>> supplier = () -> getObservable();
        Observable<String> source = Observable.defer(supplier);

        source.subscribe(val -> Log.i("Subscriber #1:" + val));
        source.subscribe(val -> Log.i("Subscriber #2:" + val));
        CommonUtils.exampleComplete();
    }

    private Observable<String> getObservable() {
        if (colors.hasNext()) {
            String color = colors.next();
            return Observable.just(
                    Shape.getString(color, Shape.BALL),
                    Shape.getString(color, Shape.RECTANGLE),
                    Shape.getString(color, Shape.PENTAGON));
        }
        return Observable.empty();
    }

    public void notDeferred() {
        final Observable<String> source = getObservable();
        source.subscribe(val -> Log.i("Subscriber #1:" + val));
        source.subscribe(val -> Log.i("Subscriber #2:" + val));
        CommonUtils.exampleComplete();
    }

    public static void main(String[] args) {
        final DeferExample demo = new DeferExample();
        demo.marbleDiagram();
        demo.notDeferred();
    }
}
