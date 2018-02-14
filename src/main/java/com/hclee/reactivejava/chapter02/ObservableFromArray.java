package com.hclee.reactivejava.chapter02;

import com.hclee.reactivejava.common.CommonUtils;
import io.reactivex.Observable;

import java.util.stream.IntStream;

public class ObservableFromArray {
    public void integerArray() {
        Integer[] arr = {100, 200, 300};
        final Observable<Integer> source = Observable.fromArray(arr);
        source.subscribe(System.out::println);
        CommonUtils.exampleComplete();
    }

    public void intArray() {
        int[] intArray = {400, 500, 600};
        final Observable<Integer> source = Observable.fromArray(toIntegerArray(intArray));
        source.subscribe(System.out::println);
        CommonUtils.exampleComplete();

    }

    public void intArrayWrong() {
        int[] intArray = {400, 500, 600};
        Observable.fromArray(intArray).subscribe(System.out::println);
        CommonUtils.exampleComplete();
    }

    private static Integer[] toIntegerArray(int[] intArray) {
        return IntStream.of(intArray).boxed().toArray(Integer[]::new);
    }

    public static void main(String[] args) {
        final ObservableFromArray demo = new ObservableFromArray();
        demo.intArray();
        demo.integerArray();
        demo.intArrayWrong();
    }
}
