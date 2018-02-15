package com.hclee.reactivejava.chapter03;

import com.hclee.reactivejava.common.CommonUtils;
import com.hclee.reactivejava.common.MarbleDiagram;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Predicate;

import static com.hclee.reactivejava.common.Shape.*;

public class FilterExample implements MarbleDiagram {
    @Override
    public void marbleDiagram() {
        String[] objs = {RED + " CIRCLE", YELLOW + " DIAMOND", GREEN + " TRIANGLE",
                SKY + " DIAMOND", BLUE + " CIRCLE", PUPPLE + " HEXAGON"}; //1,2,3,4,5,6
        final Observable<String> source = Observable.fromArray(objs)
                .filter(s -> s.endsWith("CIRCLE"));
        source.subscribe(s -> System.out.println(s));
        CommonUtils.exampleComplete();
    }

    public void usingPredicate() {
        Predicate<String> filterCircle = s -> s.endsWith("CIRCLE");
        String[] objs = {RED + " CIRCLE", YELLOW + " DIAMOND", GREEN + " TRIANGLE",
                SKY + " DIAMOND", BLUE + " CIRCLE", PUPPLE + " HEXAGON"}; //1,2,3,4,5,6
        final Observable<String> source = Observable.fromArray(objs)
                .filter(filterCircle);
        source.subscribe(s -> System.out.println(s));
        CommonUtils.exampleComplete();
    }

    public void evenNumber() {
        Integer[] data = {100, 34, 27, 99, 50};
        final Observable<Integer> source = Observable.fromArray(data)
                .filter(i -> i % 2 == 0);
        source.subscribe(integer -> System.out.println(integer));
        CommonUtils.exampleComplete();
    }

    public void otherFilters() {
        Integer[] numbers = {100, 200, 300, 400, 500};
        final Single<Integer> first = Observable.fromArray(numbers).first(-1);
        first.subscribe(System.out::println);

        final Single<Integer> last = Observable.fromArray(numbers).last(999);
        last.subscribe(System.out::println);

        final Observable<Integer> take = Observable.fromArray(numbers).take(3);
        take.subscribe(System.out::println);

        final Observable<Integer> takeLast = Observable.fromArray(numbers).takeLast(3);
        takeLast.subscribe(System.out::println);

        final Observable<Integer> skip = Observable.fromArray(numbers).skip(2);
        skip.subscribe(System.out::println);

        final Observable<Integer> skipLast = Observable.fromArray(numbers).skipLast(2);
        skipLast.subscribe(System.out::println);
    }
}
