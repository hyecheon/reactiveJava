package com.hclee.reactivejava.chapter03;

import com.hclee.reactivejava.common.CommonUtils;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class QueryTvSales {
    public void run() {
        List<Pair<String, Integer>> sales = new ArrayList<>(4);
        sales.add(Pair.of("TV", 2500));
        sales.add(Pair.of("Camera", 300));
        sales.add(Pair.of("TV", 1600));
        sales.add(Pair.of("Phone", 800));

        final Maybe<Integer> source = Observable.fromIterable(sales)
                .filter(sale -> sale.getLeft().equals("TV"))
                .map(sale -> sale.getRight())
                .reduce((integer, integer2) -> integer + integer2);
        source.subscribe(System.out::println);
        CommonUtils.exampleComplete();
    }

    public static void main(String[] args) {
        final QueryTvSales demo = new QueryTvSales();
        demo.run();
    }
}
