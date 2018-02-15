package com.hclee.reactivejava.chapter04.combine;

import com.hclee.reactivejava.common.CommonUtils;
import com.hclee.reactivejava.common.Log;
import io.reactivex.Observable;
import org.apache.commons.lang3.tuple.Pair;

import java.text.DecimalFormat;

import static org.apache.commons.lang3.ObjectUtils.max;
import static org.apache.commons.lang3.ObjectUtils.min;

public class ElectricBills {
    private int index = 0;

    public void electricBillV1() {
        String[] data = {
                "100",
                "300",
                "800"
        };
        Observable<Integer> basePrice = getBasePrice(data);
        Observable<Integer> usagePrice = getUsagePrice(data);
        final Observable<Integer> source = Observable.zip(basePrice, usagePrice, (b, u) -> b + u);
        source.map(val -> new DecimalFormat("#.###").format(val))
                .subscribe(val -> {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Usage: " + data[index] + "KWh => ");
                    stringBuilder.append("Price: " + val + "원");
                    Log.i(stringBuilder.toString());
                    index++;
                });
        CommonUtils.exampleComplete();
    }

    public void electricBillV2() {
        String[] data = {
                "100",
                "300",
                "800"
        };
        Observable<Integer> basePrice = getBasePrice(data);
        Observable<Integer> usagePrice = getUsagePrice(data);
        final Observable<Pair<String, Integer>> source = Observable.zip(basePrice, usagePrice,
                Observable.fromArray(data),
                (v1, v2, i) -> Pair.of(i, v1 + v2));
        source.map(val -> Pair.of(val.getLeft(),
                new DecimalFormat("#.##").format(val.getRight())))
                .subscribe(val -> {
                    String stringBuilder = ("Usage: " + val.getLeft() + "KWh => ") +
                            "Price: " + val.getRight() + "원";
                    Log.i(stringBuilder);
                });
        CommonUtils.exampleComplete();
    }

    private Observable<Integer> getBasePrice(String[] data) {
        final Observable<Integer> basePrice = Observable.fromArray(data)
                .map(Integer::parseInt)
                .map(val -> {
                    if (val < 200) return 910;
                    if (val <= 400) return 1600;
                    return 7300;
                });
        return basePrice;
    }

    private Observable<Integer> getUsagePrice(String[] data) {
        final Observable<Integer> usagePrice = Observable.fromArray(data)
                .map(Integer::parseInt)
                .map(val -> {
                    final double series1 = min(200, val) * 93.3;
                    final double series2 = min(200, max(val - 200, 0)) * 187.9;
                    final double series3 = max(0, max(val - 400, 0)) * 280.65;
                    return (int) (series1 + series2 + series3);
                });
        return usagePrice;
    }

    public static void main(String[] args) {
        final ElectricBills electricBills = new ElectricBills();
        electricBills.electricBillV1();
        electricBills.electricBillV2();
    }
}
