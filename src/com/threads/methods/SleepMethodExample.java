package com.threads.methods;

public class SleepMethodExample {

    public static void main(String[] args) {
        // Create two threads
        Thread t1 = new Thread(new RunnableExample2(), "Thread-1");
        Thread t2 = new Thread(new RunnableExample2(), "Thread-2");

        // Start both threads
        t1.start();
        t2.start();
        
        // Wait for both threads to finish
        try {
            t1.join(); // Wait for Thread-1 to complete
            t2.join(); // Wait for Thread-2 to complete
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
            e.printStackTrace();
        }

        System.out.println("Both threads have completed execution.");
    }
}

class RunnableExample2 implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " is now running.");

            // sleeping for 2 seconds
            Thread.sleep(2000); // Transition to TIMED_WAITING state
            System.out.println(Thread.currentThread().getName() +" In State "+Thread.currentThread().getState());
            System.out.println(Thread.currentThread().getName() + " has finished sleeping.");

            // Continue execution after yielding
            System.out.println(Thread.currentThread().getName() + " has resumed.");
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " was interrupted during sleep.");
            e.printStackTrace(); // Handle the interruption appropriately
        }
    }
}
