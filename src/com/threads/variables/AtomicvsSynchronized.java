package com.threads.variables;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicvsSynchronized {

    private AtomicInteger atomicCounter = new AtomicInteger(0);
    private int syncCounter = 0;

    public void atomicIncrement() {
        atomicCounter.incrementAndGet(); // Atomically increments the counter
    }

    public int atomicGetCounter() {
        return atomicCounter.get(); // Gets the current value
    }

    public synchronized void syncIncrement() {
        syncCounter++; // Increments the counter with synchronization
    }

    public synchronized int syncGetCounter() {
        return syncCounter; // Gets the current value with synchronization
    }

    public static void main(String[] args) throws InterruptedException {
        final int THREAD_COUNT = 1000; // Number of threads
        final int INCREMENTS_PER_THREAD = 1000; // Number of increments per thread
        
        // Create the test instance
        AtomicvsSynchronized example = new AtomicvsSynchronized();

        // Measure performance for AtomicInteger
        long atomicStartTime = System.nanoTime();
        Thread[] atomicThreads = new Thread[THREAD_COUNT];
        
        for (int i = 0; i < THREAD_COUNT; i++) {
            atomicThreads[i] = new Thread(() -> {
                for (int j = 0; j < INCREMENTS_PER_THREAD; j++) {
                    example.atomicIncrement();
                }
            });
            atomicThreads[i].start();
        }

        // Wait for all atomic threads to finish
        for (Thread thread : atomicThreads) {
            thread.join();
        }

        long atomicEndTime = System.nanoTime();
        System.out.println("Atomic Counter: " + example.atomicGetCounter());
        System.out.println("Time taken with AtomicInteger: " + (atomicEndTime - atomicStartTime) + " ns");

        // Reset the counter for synchronized test
        example.syncCounter = 0;

        // Measure performance for synchronized
        long syncStartTime = System.nanoTime();
        Thread[] syncThreads = new Thread[THREAD_COUNT];

        for (int i = 0; i < THREAD_COUNT; i++) {
            syncThreads[i] = new Thread(() -> {
                for (int j = 0; j < INCREMENTS_PER_THREAD; j++) {
                    example.syncIncrement();
                }
            });
            syncThreads[i].start();
        }

        // Wait for all synchronized threads to finish
        for (Thread thread : syncThreads) {
            thread.join();
        }

        long syncEndTime = System.nanoTime();
        System.out.println("Synchronized Counter: " + example.syncGetCounter());
        System.out.println("Time taken with synchronized: " + (syncEndTime - syncStartTime) + " ns");
    }
}

//OutPut
/*
 * Atomic Counter: 1000000
 * Time taken with AtomicInteger: 76016834 ns
 * Synchronized Counter: 1000000
 * Time taken with synchronized: 128659292 ns 
 */
