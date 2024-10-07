package com.threads.methods;

public class JoinInterruptExample {

    public static void main(String[] args) {
        // Thread 1 - The thread to be joined
        Thread thread1 = new Thread(() -> {
            System.out.println("Thread 1: Started.");
            try {
                // Simulate some work with sleep
                Thread.sleep(5000); // Thread 1 will sleep for 5 seconds
                System.out.println("Thread 1: Finished work.");
            } catch (InterruptedException e) {
                System.out.println("Thread 1: Was interrupted while sleeping.");
            }
        });

        // Thread 2 - Will wait for thread1 using join()
        Thread thread2 = new Thread(() -> {
            System.out.println("Thread 2: Waiting for Thread 1 to finish using join().");
            try {
                // Thread 2 is waiting for thread1 to complete
                thread1.join();
                System.out.println("Thread 2: Thread 1 has finished, so resuming.");
            } catch (InterruptedException e) {
                System.out.println("Thread 2: Was interrupted while waiting for Thread 1.");
            }
        });

        // Start both threads
        thread1.start();
        thread2.start();

        // Ensure thread2 starts and waits for thread1 to finish
        try {
            Thread.sleep(1000); // Short sleep to ensure thread2 starts waiting
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Interrupt thread2 while it is waiting for thread1 to join
        System.out.println("Main Thread: Interrupting Thread 2 while it's waiting for Thread 1.");
        thread2.interrupt(); // Interrupt thread2

        // Main thread waits for both threads to finish
        try {
            thread1.join(); // Ensure thread1 finishes before main thread exits
        } catch (InterruptedException e) {
            System.out.println("Main thread was interrupted while waiting for Thread 1.");
        }
    }
}

/*
Sample Output:
Thread 1: Started.
Thread 2: Waiting for Thread 1 to finish using join().
Main Thread: Interrupting Thread 2 while it's waiting for Thread 1.
Thread 2: Was interrupted while waiting for Thread 1.
Thread 1: Finished work.
*/
