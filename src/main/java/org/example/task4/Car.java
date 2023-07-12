package org.example.task4;

import java.util.Random;
import java.util.concurrent.*;

class Car implements Runnable {
    protected static final int NUM_PARTICIPANTS = 10;
    protected static final int MAX_TUNNEL_CAPACITY = 3;
    private static final int ROAD_DISTANCE = 1000;
    private static final int TUNNEL_DISTANCE = 500;
    private static final int MAX_PREPARATION_TIME = 500;
    private static final int MAX_ROAD_TIME = 100;
    private static final int MAX_TUNNEL_TIME = 200;
    private static final Random random = new Random();
    private volatile int fullTimeRoad = 0;

    private final int carNumber;
    private final CyclicBarrier startBarrier;
    private final Semaphore tunnel;
    private final CountDownLatch finishLatch;
    private final CopyOnWriteArrayList<Car> raceResult;

    public Car(int carNumber,
               CyclicBarrier startBarrier,
               Semaphore tunnel,
               CountDownLatch finishLatch,
               CopyOnWriteArrayList<Car> raceResult) {
        this.carNumber = carNumber;
        this.startBarrier = startBarrier;
        this.tunnel = tunnel;
        this.finishLatch = finishLatch;
        this.raceResult = raceResult;
    }

    @Override
    public void run() {
        try {
            long before = System.nanoTime();
            prepareForRace();
            startBarrier.await();
            driveOnRoad(ROAD_DISTANCE);
            driveThroughTunnel(TUNNEL_DISTANCE);
            driveOnRoad(ROAD_DISTANCE);
            finishRace();
            long after = System.nanoTime();
            fullTimeRoad = (int) (after - before) / 1000000;
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();
        } finally {
            finishLatch.countDown();
        }
    }

    private void prepareForRace() throws InterruptedException {
        int preparationTime = random.nextInt(MAX_PREPARATION_TIME);
        System.out.println("Car " + carNumber + " is preparing for the race. Time: " + preparationTime);
        Thread.sleep(preparationTime);
        System.out.println("Car " + carNumber + " is ready!");
    }

    private void driveOnRoad(int distance) throws InterruptedException {
        int roadTime = random.nextInt(MAX_ROAD_TIME);
        System.out.println("Car " + carNumber + " is driving on the road. Distance: " + distance + ", Time: " + roadTime);
        Thread.sleep(roadTime);
    }

    private void driveThroughTunnel(int distance) throws InterruptedException {
        System.out.println("Car " + carNumber + " is waiting to enter the tunnel.");
        tunnel.acquire();
        int tunnelTime = random.nextInt(MAX_TUNNEL_TIME);
        System.out.println("Car " + carNumber + " is driving through the tunnel. Distance: " + distance + ", Time: " + tunnelTime);
        Thread.sleep(tunnelTime);
        System.out.println("Car " + carNumber + " has exited the tunnel.");
        tunnel.release();
    }

    private void finishRace() {
        raceResult.add(this);
        System.out.println("Car " + carNumber + " has finished the race!");
    }

    public synchronized int getFullTimeRoad() {
        return fullTimeRoad;
    }

    public synchronized int getCarNumber() {
        return carNumber;
    }
}

