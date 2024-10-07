package com.threads.methods;

public class WaitNotifyInterruptExample {

    private static final Object lock = new Object();

    public static void main(String[] args) {
        // Thread 1 - Will go into wait state
        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread 1: Holding the lock...");
                try {
                    // Thread 1 goes into a wait state
                    System.out.println("Thread 1: Going to wait...");
                    lock.wait(); // Release the lock and wait
                    System.out.println("Thread 1: Woke up and acquired the lock again.");
                } catch (InterruptedException e) {
                    System.out.println("Thread 1: Was interrupted while waiting!");
                }
                System.out.println("Thread 1: Released the lock.");
            }
        });

        // Thread 2 - Will notify thread 1 after some time
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

        // Start Thread 1
        thread1.start();

        // Ensure Thread 1 starts and goes to wait state
        try {
            Thread.sleep(100); // Short sleep to ensure thread1 goes into wait
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Interrupt Thread 1 before Thread 2 notifies it
        System.out.println("Main Thread: Interrupting Thread 1 before it is notified.");
        thread1.interrupt(); // Interrupt Thread 1

        // Start Thread 2
        thread2.start();
    }
}

/*
Sample Output:
Thread 1: Holding the lock...
Thread 1: Going to wait...
Main Thread: Interrupting Thread 1 before it is notified.
Thread 1: Was interrupted while waiting!
Thread 1: Released the lock.
Thread 2: Acquired the lock.
Thread 2: (Waits for 2 seconds...)
Thread 2: Notifying Thread 1 to wake up...
Thread 2: Released the lock.
*/
