package com.threads.methods;

public class WaitNotifyExample {

    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread 1: Holding the lock...");
                try {
                    // Thread 1 goes into a wait state
                    System.out.println("Thread 1: Going to wait...");
                    lock.wait(); // Release the lock and wait
                    System.out.println("Thread 1: Woke up and acquired the lock again.");
                } catch (InterruptedException e) {
                    System.out.println("Thread 1 was interrupted.");
                }
                System.out.println("Thread 1: Released the lock.");
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread 2: Acquired the lock.");
                try {
                    // Simulate some work
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println("Thread 2 was interrupted.");
                }
                System.out.println("Thread 2: Notifying Thread 1 to wake up...");
                lock.notify(); // Wake up Thread 1
                System.out.println("Thread 2: Released the lock.");
            }
        });

        thread1.start();
        // Ensure Thread 1 starts first
        try {
            Thread.sleep(100); // Short sleep to ensure thread1 starts first
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }
}
/*
Thread 1: Holding the lock...
Thread 1: Going to wait...
Thread 2: Acquired the lock.
Thread 2: (Waits for 2 seconds...)
Thread 2: Notifying Thread 1 to wake up...
Thread 2: Released the lock.
Thread 1: Woke up and acquired the lock again.
Thread 1: Released the lock.
*/
