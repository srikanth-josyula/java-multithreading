package com.threads.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {
	
	private StringBuilder sharedString = new StringBuilder(); // Shared resource
	
	private final Lock lock = new ReentrantLock(); // Create a lock

	// Method to append using ReentrantLock
	public void appendWithLock(String str) {
		lock.lock(); // Acquire the lock
		try {
			sharedString.append(str).append(" ");
			System.out.println(Thread.currentThread().getName() + " (Lock): " + sharedString);
		} finally {
			lock.unlock(); // Ensure the lock is released
		}
	}

	public static void main(String[] args) throws InterruptedException {
		LockExample example = new LockExample(); // Single shared instance

		// Create two threads that try to call the method using the lock
		Thread t1 = new Thread(() -> example.appendWithLock("Hello"));
		Thread t2 = new Thread(() -> example.appendWithLock("World"));

		t1.start();
		t2.start();

		t1.join();
		t2.join();
	}
}
