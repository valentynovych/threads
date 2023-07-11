package org.example.task3;

import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<Integer> integerArray = new CopyOnWriteArrayList<>();

        System.out.println("Size array before fill - " + integerArray.size());

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                integerArray.add(i * 3 + i % 3);
            }
        });
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                integerArray.add(i * 3 + i % 3);
            }
        });
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();

        System.out.println("Size array after fill - " + integerArray.size());
    }
}
