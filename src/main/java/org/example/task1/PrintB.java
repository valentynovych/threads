package org.example.task1;

public class PrintB extends Thread {
    PrinterChar printerChar;
    PrintB(PrinterChar printerChar){
        this.printerChar = printerChar;
    }
    @Override
    public void run() {
        for (int i = 0; i <= 5; i++) {
            try {
                printerChar.print('B', 1);
                Thread.sleep(1100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
