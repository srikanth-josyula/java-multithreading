package com.threads.synchronization;

public class SynchronizationClassLevelExample {

	// Static StringBuilder for the static method (shared across all instances)
	private static StringBuilder sharedString = new StringBuilder();

	// Static Synchronized Method: Locks at the class level
	public static synchronized void appendWithStaticLock(String str) {
		sharedString.append(str).append(" ");
		System.out.println(Thread.currentThread().getName() + " (Static): " + sharedString);
	}

	public static void main(String[] args) throws InterruptedException {
		// Two threads calling the static synchronized method
		Thread t1 = new Thread(() -> SynchronizationClassLevelExample.appendWithStaticLock("Hello"));
		Thread t2 = new Thread(() -> SynchronizationClassLevelExample.appendWithStaticLock("World"));

		t1.start();
		t2.start();

		t1.join();
		t2.join();
	}
}
