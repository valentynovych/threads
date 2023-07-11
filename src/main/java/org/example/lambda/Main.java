package org.example.lambda;

public class Main {
    public static void main(String[] args) {

        FunctionalInterface func = (a, b) -> {
            return a + b;
        };

        System.out.println(func.sum(1, 2));
        System.out.println("----------------");

        summ((a, b) -> {
            return a + b;
        });
    }

    static void summ(FunctionalInterface fun) {
        System.out.println(fun.sum(1, 5));
    }
}
