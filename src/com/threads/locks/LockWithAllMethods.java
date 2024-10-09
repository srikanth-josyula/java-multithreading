package com.threads.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockWithAllMethods {
	
	private StringBuilder sharedString = new StringBuilder(); // Shared resource
	
    private final Lock lock = new ReentrantLock(); // Create a lock

    // Method to append using ReentrantLock and lock()
    public void appendWithLock(String str) {
        lock.lock(); // Acquire the lock
        try {
            sharedString.append(str).append(" ");
            System.out.println(Thread.currentThread().getName() + " (Lock): " + sharedString);
        } finally {
            lock.unlock(); // Ensure the lock is released
        }
    }

    // Method to append using tryLock()
    public void appendWithTryLock(String str) {
        if (lock.tryLock()) { // Attempt to acquire the lock
            try {
                sharedString.append(str).append(" ");
                System.out.println(Thread.currentThread().getName() + " (TryLock): " + sharedString);
            } finally {
                lock.unlock(); // Ensure the lock is released
            }
        } else {
            System.out.println(Thread.currentThread().getName() + " (TryLock): Unable to acquire lock");
        }
    }

    // Method to append using lockInterruptibly()
    public void appendWithLockInterruptibly(String str) {
        try {
            lock.lockInterruptibly(); // Acquire the lock, allow interruption
            try {
                sharedString.append(str).append(" ");
                System.out.println(Thread.currentThread().getName() + " (LockInterruptibly): " + sharedString);
            } finally {
                lock.unlock(); // Ensure the lock is released
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " was interrupted while waiting for the lock.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
    	LockWithAllMethods example = new LockWithAllMethods(); // Single shared instance

        // Create threads for each method to demonstrate different lock usages
        Thread t1 = new Thread(() -> example.appendWithLock("Hello"));
        Thread t2 = new Thread(() -> example.appendWithTryLock("World"));
        Thread t3 = new Thread(() -> example.appendWithLockInterruptibly("from Interruptible"));

        // Start threads
        t1.start();
        t2.start();
        t3.start();

        // Wait for threads to finish
        t1.join();
        t2.join();
        t3.join();
    }
}
