package org.example.demonThread;

public class DemonThread extends Thread {

    public DemonThread(){
        super("Demon Thread");
        setDaemon(true);
        start();
    }

    @Override
    public void run() {
        while (true){
            System.out.println(Thread.currentThread().getName() + " working ....");
        }
    }
}
