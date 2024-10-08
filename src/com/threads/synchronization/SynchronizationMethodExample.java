package com.threads.synchronization;

public class SynchronizationMethodExample {

	private StringBuilder sharedString = new StringBuilder(); // Shared resource
	private StringBuilder sharedString1 = new StringBuilder(); // Shared resource

	// Synchronized Method: Locks at the instance level
	public synchronized void appendWithSyncMethod(String str) {
		sharedString.append(str).append(" ");
		System.out.println(Thread.currentThread().getName() + " (appendWithSyncMethod Method): " + sharedString);
	}

	// Synchronized Method: Locks at the instance level
	public void appendWithoutSyncMethod(String str) {
		sharedString1.append(str).append(" ");
		System.out.println(Thread.currentThread().getName() + " (appendWithoutSyncMethod Method): " + sharedString);
	}

	public static void main(String[] args) throws InterruptedException {
		SynchronizationMethodExample example = new SynchronizationMethodExample(); // Single shared instance

		// Create two threads that try to call the synchronized method
		Thread t1 = new Thread(() -> example.appendWithSyncMethod("Hello"));
		Thread t2 = new Thread(() -> example.appendWithSyncMethod("World"));

		t1.start();
		t2.start();

		t1.join();
		t2.join();
		
		//Using non-sync way
		Thread t3 = new Thread(() -> example.appendWithoutSyncMethod("Hello"));
		Thread t4 = new Thread(() -> example.appendWithoutSyncMethod("World"));

		t3.start();
		t4.start();

		t3.join();
		t4.join();
	}
}

//OUTPUT
/*
 * Thread-0 (appendWithSyncMethod Method): Hello 
 * Thread-1 (appendWithSyncMethod Method): Hello World 
 * Thread-2 (appendWithoutSyncMethod Method): Hello World 
 * Thread-3 (appendWithoutSyncMethod Method): Hello World 
 * 
 */
