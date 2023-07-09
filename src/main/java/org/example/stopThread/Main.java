package org.example.stopThread;

public class Main {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread);
        System.out.println("Start main");
        thread.start();

        try {
            Thread.sleep(2000);
            myThread.disable();
            Thread.sleep(1000);
            System.out.println("Main ends");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        MyThread1 myThread1 = new MyThread1();
        System.out.println("Start main");
        myThread1.start();

        try {
            Thread.sleep(5);
            myThread1.interrupt();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Main ends");
    }

}
