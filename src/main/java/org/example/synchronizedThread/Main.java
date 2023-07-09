package org.example.synchronizedThread;

public class Main {
    public static void main(String[] args) {

        Res res = new Res();
        for (int i = 1; i < 6; i++){
            Thread thread = new Thread(new MyThread(res));
            thread.setName("Thread " + i);
            thread.start();
        }
    }
}

class MyThread implements Runnable {
    Res res;

    public MyThread(Res res) {
        this.res = res;
    }

    @Override
    public void run() {
        res.increment();
    }
}

class Res {
     int x = 0;
     synchronized void increment() {
         x = 1;
         for (int i = 1; i < 5; i++) {
             System.out.println(Thread.currentThread().getName() + " - " + x);
             x++;
             try {
                 Thread.sleep(100);
             } catch (InterruptedException e) {
                 throw new RuntimeException(e);
             }
         }
     }
}
