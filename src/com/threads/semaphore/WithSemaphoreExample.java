package com.threads.semaphore;

import java.util.concurrent.Semaphore;

public class WithSemaphoreExample {

	private StringBuilder sharedString = new StringBuilder(); // Shared resource
	private final Semaphore semaphore = new Semaphore(1); // Create a semaphore with 1 permit

	// Method to append using Semaphore
	public void appendWithSemaphore(String str) {
		try {

			semaphore.acquire(); // Acquire a permit

			sharedString.append(str).append(" ");
			System.out.println(Thread.currentThread().getName() + " (Semaphore): " + sharedString);

		} catch (InterruptedException e) {
			Thread.currentThread().interrupt(); // Restore the interrupted status
			System.err.println("Thread was interrupted: " + e.getMessage());

		} finally {

			semaphore.release(); // Release the permit
		}
	}

	public static void main(String[] args) throws InterruptedException {
		WithSemaphoreExample example = new WithSemaphoreExample(); // Single shared instance

		// Create two threads that try to call the method using the semaphore
		Thread t1 = new Thread(() -> example.appendWithSemaphore("Hello"));
		Thread t2 = new Thread(() -> example.appendWithSemaphore("World"));

		t1.start();
		t2.start();

		t1.join();
		t2.join();
	}
}
