package org.example.demonThread;

public class Main {
    public static void main(String[] args) {
        Thread thread = new UserThread("Users Thread 1");
        Thread thread2 = new UserThread("Users Thread 2");

        System.out.println(thread.isDaemon());
        System.out.println(thread2.isDaemon());
        System.out.println(new DemonThread().isDaemon());

        thread.start();
        new DemonThread();
        thread2.start();
    }
}
