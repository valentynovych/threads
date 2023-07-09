package org.example.synchronizedCollection;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class CopyOnWrite {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> arrayList = new CopyOnWriteArrayList<>();
        CopyOnWriteArraySet<String> arraySet = new CopyOnWriteArraySet<>();
        arrayList.add("One");
        arrayList.add("Two");
        arrayList.add("Tree");
        arrayList.add("Four");
        arrayList.add("Five");
        System.out.println(arrayList);

        Runnable runnable = () -> {
            Iterator<String> iterator = arrayList.listIterator();
            while (iterator.hasNext()){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(iterator.next());
            }
        };

        Runnable runnable1 = () -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            arrayList.remove(4);
            arrayList.add("Six");
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
        System.out.println(arrayList);

    }
}
