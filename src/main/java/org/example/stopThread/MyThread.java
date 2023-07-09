package org.example.stopThread;

public class MyThread implements Runnable {

    private boolean isActive;

    void disable() {
        isActive = false;
    }

    MyThread() {
        isActive = true;
    }

    @Override
    public void run() {
        int count = 1;
        while (isActive) { //!Thread.currentThread().isInterrupted();
            System.out.println("Thread " + Thread.currentThread().getName() + " iteration - " + count++);
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Thread " + Thread.currentThread().getName() + " ends");
    }
}
