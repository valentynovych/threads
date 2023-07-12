package org.example.task4;


import java.util.Iterator;
import java.util.concurrent.*;

public class Race {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier startBarrier = new CyclicBarrier(Car.NUM_PARTICIPANTS);
        Semaphore tunnel = new Semaphore(Car.MAX_TUNNEL_CAPACITY);
        CountDownLatch finishLatch = new CountDownLatch(Car.NUM_PARTICIPANTS);
        CopyOnWriteArrayList<Car> raceResult = new CopyOnWriteArrayList<>();

        ExecutorService executorService = Executors.newFixedThreadPool(Car.NUM_PARTICIPANTS);
        for (int i = 1; i <= Car.NUM_PARTICIPANTS; i++) {
            executorService.execute(new Car(i, startBarrier, tunnel, finishLatch, raceResult));
        }

        finishLatch.await();
        executorService.shutdown();

        System.out.println("\nRace results:");

        Iterator<Car> iterator = raceResult.iterator();
        while (iterator.hasNext()) {
            Car index = iterator.next();
            System.out.println("Car: " + index.getCarNumber() + " time: " + index.getFullTimeRoad());
        }
    }
}

