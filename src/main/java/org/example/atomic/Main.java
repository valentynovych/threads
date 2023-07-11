package org.example.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static AtomicInteger integer = new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++){
            new MyThread().start();
        }
        Thread.sleep(2000);
        System.out.println(integer.get());
    }
    static class MyThread extends Thread {
        @Override
        public void run() {
           integer.incrementAndGet();
        }
    }
}
