package org.example.task1;

public class PrinterChar {
    int count = 0;

    public synchronized void print(char charset, int priority) throws InterruptedException {

        notify();
        if (count%3 != priority) {
            wait();
        }

        count++;
        System.out.print(charset);
        notifyAll();
    }
}
