package org.example.stopThread;

public class MyThread1 extends Thread {
    @Override
    public void run() {
        System.out.println("Thread - " + Thread.currentThread().getName() + " started ...");
        int count = 1;
        while (!isInterrupted()){
            System.out.println("Iteration - " + count++);
        }
        System.out.println("Thread - " + Thread.currentThread().getName() + " ends ");
    }
}
