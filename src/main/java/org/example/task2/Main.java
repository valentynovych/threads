package org.example.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("Roma");
        strings.add("Olia");
        strings.add("Sasha");
        strings.add("Kostia");
        strings.add("Diana");
        strings.add("Vasia");

        Scanner scanner = new Scanner(System.in);
        System.out.println(strings);
        System.out.println("Enter index element for collection");
        Integer index = scanner.nextInt();

        Callable<String> callable = new UserThread(strings, index);
        FutureTask<String> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        new Demon();
        try {
            System.out.println(futureTask.get());
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}

class Demon extends Thread {
    Demon() {
        setDaemon(true);
        start();
    }

    @Override
    public void run() {
        System.out.println();
        while (Thread.currentThread().isAlive()) {
            System.out.print(".");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class UserThread implements Callable<String> {

    List<String> strings;
    Integer index;

    UserThread(List<String> strings, Integer index) {
        this.strings = strings;
        this.index = index;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(5000);
        return strings.get(index);
    }
}
