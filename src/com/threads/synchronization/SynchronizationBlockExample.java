package com.threads.synchronization;

public class SynchronizationBlockExample {

	private StringBuilder sharedString = new StringBuilder(); // Shared resource

	// Synchronized Method: Locks at the instance level
	public void appendMethod(String str) {

		synchronized (sharedString) {
			sharedString.append(str).append(" ");
		}

		System.out.println(Thread.currentThread().getName() + " (synchronized Block): " + sharedString);
	}

	public static void main(String[] args) throws InterruptedException {
		SynchronizationBlockExample example = new SynchronizationBlockExample(); // Single shared instance

		// Create two threads that try to call the synchronized method
		Thread t1 = new Thread(() -> example.appendMethod("Hello"));
		Thread t2 = new Thread(() -> example.appendMethod("World"));

		t1.start();
		t2.start();

		t1.join();
		t2.join();

	}
}

//OUTPUT
/*
 * Thread-0 (synchronized Block): Hello 
 * Thread-1 (synchronized Block): Hello World 
 */
