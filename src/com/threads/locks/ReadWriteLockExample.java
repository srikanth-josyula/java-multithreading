package com.threads.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {

    private StringBuilder sharedString = new StringBuilder(); // Shared resource
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock(); // Create a ReadWriteLock
    
    private final Lock readLock = rwLock.readLock();  // Read Lock
    private final Lock writeLock = rwLock.writeLock(); // Write Lock

    // Method to append using the write lock
    public void appendWithWriteLock(String str) {
        writeLock.lock(); // Acquire the write lock
        try {
            sharedString.append(str).append(" ");
            System.out.println(Thread.currentThread().getName() + " (Write): " + sharedString);
        } finally {
            writeLock.unlock(); // Ensure the lock is released
        }
    }

    // Method to read the string using the read lock
    public void readWithReadLock() {
        readLock.lock(); // Acquire the read lock
        try {
            System.out.println(Thread.currentThread().getName() + " (Read): " + sharedString);
        } finally {
            readLock.unlock(); // Ensure the lock is released
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteLockExample example = new ReadWriteLockExample(); // Shared instance

        // Create threads that try to read the shared resource
        Thread t1 = new Thread(example::readWithReadLock);
        Thread t2 = new Thread(example::readWithReadLock);
        
        // Create a thread that tries to write to the shared resource
        Thread t3 = new Thread(() -> example.appendWithWriteLock("Hello"));
        Thread t4 = new Thread(() -> example.appendWithWriteLock("World"));
        
        // Create threads that try to read the shared resource
        Thread t5 = new Thread(example::readWithReadLock);
        Thread t6 = new Thread(example::readWithReadLock);

        // Start read threads first
        t1.start();
        t2.start();

        // Start write threads
        t3.start();
        t4.start();
        
        // Wait for all threads to finish
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        
        // Start read threads next
        t5.start();
        t6.start();
        t5.join();
        t6.join();
    }
}

