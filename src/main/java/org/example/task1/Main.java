package org.example.task1;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        PrinterChar printerChar = new PrinterChar();
        Thread thread1 = new PrintA(printerChar);
        Thread thread2 = new PrintB(printerChar);
        Thread thread3 = new PrintC(printerChar);

        thread1.start();
        thread2.start();
        thread3.start();

    }
}
