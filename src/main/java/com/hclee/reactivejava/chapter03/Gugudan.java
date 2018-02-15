package com.hclee.reactivejava.chapter03;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

import java.util.Scanner;

public class Gugudan {
    public void plainJava() {
        try (final Scanner scanner = new Scanner(System.in)) {
            System.out.println("Gugudan Input:");
            int dan = scanner.nextInt();
            for (int row = 1; row < 9; row++) {
                System.out.println(dan + " * " + row + " = " + dan * row);
            }
        }
    }

    public void reactiveV1() {
        try (final Scanner in = new Scanner(System.in);) {
            System.out.print("Gugudan Input : ");
            int dan = in.nextInt();
            final Observable<Integer> source = Observable.range(1, 9);
            source.subscribe(row -> System.out.println(dan + "*" + row + "=" + dan * row));
        }
    }

    public void reactiveV2() {
        try (final Scanner in = new Scanner(System.in);) {
            System.out.print("Gugudan Input : ");
            int dan = in.nextInt();
            Function<Integer, Observable<String>> gugudan = num ->
                    Observable.range(1, 9)
                            .map(row -> num + " * " + row + " = " + dan * row);
            final Observable<String> source =
                    Observable.just(dan).flatMap(gugudan);

            source.subscribe(System.out::println);
        }
    }

    public void usingResultSelector() {
        try (final Scanner in = new Scanner(System.in);) {
            System.out.print("Gugudan Input : ");
            int dan = in.nextInt();
            final Observable<String> source = Observable.just(dan)
                    .flatMap(gugu -> Observable.range(1, 9),
                            (gugu, i) -> gugu + "*" + i + "=" + gugu * i);
            source.subscribe(System.out::println);
        }
    }

    public void reactiveV3() {

        try (final Scanner in = new Scanner(System.in);) {
            System.out.print("Gugudan Input : ");
            int dan = in.nextInt();
            final Observable<String> source =
                    Observable.just(dan)
                            .flatMap(dna -> Observable.range(1, 9)
                                    .map(row -> dan + " * " + row + " = " + dan * row));

            source.subscribe(System.out::println);
        }
    }

    public static void main(String[] args) {
        final Gugudan gugudan = new Gugudan();
        gugudan.reactiveV3();
    }
}
