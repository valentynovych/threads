package org.example.task1;

public class PrintA extends Thread{
    PrinterChar printerChar;

    PrintA(PrinterChar printerChar){
        this.printerChar = printerChar;
    }
    @Override
    public void run() {
        for (int i = 0; i <= 5; i++) {
            try {
                printerChar.print('A', 0);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
