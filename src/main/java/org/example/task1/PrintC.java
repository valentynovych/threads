package org.example.task1;

public class PrintC extends Thread {
    PrinterChar printerChar = new PrinterChar();
    PrintC(PrinterChar printerChar){
        this.printerChar = printerChar;
    }
    @Override
    public void run() {
        for (int i = 0; i <= 5; i++) {
            try {
                printerChar.print('C', 2);
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
