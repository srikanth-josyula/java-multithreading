package com.threads.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExample {

	private StringBuilder sharedString = new StringBuilder(); // Shared resource

	// Method to append to the shared StringBuilder without synchronization
	public void appendMethod(String str) {
		// Append to the shared resource (no synchronization)
		sharedString.append(str).append(" ");
		System.out.println(Thread.currentThread().getName() + " (ExecutorService): " + sharedString);
	}

	public static void main(String[] args) {
		ExecutorServiceExample example = new ExecutorServiceExample(); // Single shared instance

		// Create an ExecutorService with a fixed thread pool (2 threads)
		ExecutorService executor = Executors.newFixedThreadPool(2);

		// Submit two tasks to the ExecutorService
		executor.submit(() -> example.appendMethod("Hello"));
		executor.submit(() -> example.appendMethod("World"));

		// Shutdown the executor
		executor.shutdown();
	}
}
