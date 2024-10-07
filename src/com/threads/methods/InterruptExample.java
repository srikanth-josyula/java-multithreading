package com.threads.methods;

public class InterruptExample {

    public static void main(String[] args) {
        
        // Create a thread that will sleep for a long time
        Thread sleepingThread = new Thread(new SleepingRunnable(), "SleepingThread");

        // Start the sleeping thread
        sleepingThread.start();
        
        // Let the sleeping thread run for a short time
        try {
            Thread.sleep(1000);  // Main thread sleeps for 1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Now interrupt the sleeping thread
        System.out.println("Main thread is interrupting " + sleepingThread.getName());
        sleepingThread.interrupt();  // Interrupts the sleeping thread
    }
}

class SleepingRunnable implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " is going to sleep...");
            // Thread sleeps for 10 seconds (simulating long work)
            Thread.sleep(10000); 
            System.out.println(Thread.currentThread().getName() + " woke up normally.");
        } catch (InterruptedException e) {
            // Catch the interruption and handle it
            System.out.println(Thread.currentThread().getName() + " was interrupted during sleep!");
        }
    }
}
//Output
/*SleepingThread is going to sleep...
  Main thread is interrupting SleepingThread
  SleepingThread was interrupted during sleep!
*/