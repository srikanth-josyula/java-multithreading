package com.threads.semaphore;

public class WithOutSemaphoreExample {

	private StringBuilder sharedString = new StringBuilder(); // Shared resource

	public void appendWithSemaphore(String str) {

		sharedString.append(str).append(" ");
		System.out.println(Thread.currentThread().getName() + " (Without Semaphore): " + sharedString);

	}

	public static void main(String[] args) throws InterruptedException {
		WithOutSemaphoreExample example = new WithOutSemaphoreExample(); // Single shared instance

		// Create two threads that try to call the method using the semaphore
		Thread t1 = new Thread(() -> example.appendWithSemaphore("Hello"));
		Thread t2 = new Thread(() -> example.appendWithSemaphore("World"));

		t1.start();
		t2.start();

		t1.join();
		t2.join();
	}
}

//OutPut
/*
 * Thread-0 (Without Semaphore): Hello World 
 * Thread-1 (Without Semaphore): Hello World 
 */
