package org.example.synchronizedCollection;

import java.util.*;

public class SynchronizedCollection {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 30; i++){
            arrayList.add(i);
        }
//        ArrayList<Integer> arrayList1 = new ArrayList<>();
        List<Integer> arrayList1 = Collections.synchronizedList(new ArrayList<>()); // lock на колекцію
        Runnable runnable = () -> arrayList1.addAll(arrayList);

        Thread thread = new Thread(runnable);
        Thread thread1 = new Thread(runnable);
        thread1.start();
        thread.start();
        try {
            thread1.join();
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(arrayList1);

        Set<Integer> integerSet = Collections.synchronizedSet(new HashSet<>());
        Map<Integer, Integer> integerMap = Collections.synchronizedMap(new HashMap<>());
    }
}
