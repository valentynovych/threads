package org.example.createThreads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Main {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread thread2 = new Thread(new MyRunnable());

        myThread.run();

        myThread.start();
        thread2.start();
        System.out.println("-----------------------------------------------------------");
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("New thread creating on anonim class" + Thread.currentThread().getName());
            }
        }).start();
        System.out.println("-----------------------------------------------------------");
        new Thread(() -> System.out.println("New Thread creating on lambda " + Thread.currentThread().getName()))
                .start();

        System.out.println("-----------------------------------------------------------");
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            System.out.println("New Thread creating on future task" + Thread.currentThread().getName());
            return "Returned string";
        });

        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            String result = futureTask.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("New thread " + Thread.currentThread().getName());
    }
}

class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("New MyRunnable " + Thread.currentThread().getName());
    }
}