package com.hclee.reactivejava.chapter04.conditional;

import com.hclee.reactivejava.common.Log;
import com.hclee.reactivejava.common.MarbleDiagram;
import com.hclee.reactivejava.common.Shape;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;

import static com.hclee.reactivejava.common.Shape.*;

public class AllFunctionExample implements MarbleDiagram {
    @Override
    public void marbleDiagram() {
        String[] data = {RED, YELLOW, GREEN, SKY};

        Single<Boolean> source = Observable.fromArray(data)
                .map(Shape::getShape)
                .all(Shape.BALL::equals);
        //.all(val -> Shape.BALL.equals(Shape.getShape(val)));
        source.subscribe((Consumer<? super Boolean>) Log::i);
    }

    public void wrongCase() {
        String[] data = {RED, rectangle(YELLOW), GREEN, SKY};
        final Single<Boolean> source = Observable.fromArray(data)
                .map(Shape::getShape)
                .doOnNext(Log::d)
                .doOnComplete(() -> Log.d("onComplete"))
                .all(Shape.BALL::equals)
                .doOnSuccess(v -> Log.d("onSuccess : val = " + v));
        source.subscribe((Consumer<? super Boolean>) Log::i);
    }

    public static void main(String[] args) {
        final AllFunctionExample demo = new AllFunctionExample();
        demo.marbleDiagram();
        demo.wrongCase();
    }
}
