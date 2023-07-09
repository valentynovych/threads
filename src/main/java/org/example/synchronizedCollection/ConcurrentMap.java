package org.example.synchronizedCollection;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMap {
    public static void main(String[] args) {
        ConcurrentHashMap<Integer, String> concurrentHashMap = new ConcurrentHashMap<>(); // lock на бакет
        concurrentHashMap.put(1, "One");
        concurrentHashMap.put(2, "Two");
        concurrentHashMap.put(3, "Three");
        concurrentHashMap.put(4, "Four");
        concurrentHashMap.put(5, "Five");
        System.out.println(concurrentHashMap);

        Runnable runnable = () -> {
            Iterator<Integer> iterator = concurrentHashMap.keySet().iterator();
            while (iterator.hasNext()){
                Integer i = iterator.next();
                System.out.println(i + ":" + concurrentHashMap.get(i));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Runnable runnable1 = () -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            concurrentHashMap.put(6, "Six");
        };

        Thread thread = new Thread(runnable);
        Thread thread1 = new Thread(runnable1);
        thread.start();
        thread1.start();
        try {
            thread.join();
            thread1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(concurrentHashMap);

    }
}
