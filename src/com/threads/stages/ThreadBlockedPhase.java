package com.threads;

public class ThreadBlockedPhase {

	public static void main(String[] args) {

		// Create a shared resource object
		MyRunnable2 resource = new MyRunnable2();

		// Create two threads that will try to access the same resource
		Thread thread1 = new Thread(resource);
		Thread thread2 = new Thread(resource);

		// Starting the first thread
		System.out.println("Starting thread1: " + thread1.getName());
		thread1.start(); // Moves thread1 to the runnable state

		// Starting the second thread
		System.out.println("Starting thread2: " + thread2.getName());
		thread2.start(); // Moves thread2 to the runnable state

		// Wait for both threads to finish execution
		try {
			System.out.println("State of thread1: " +thread1.getState()); //TIMED_WAITING or RUNNABLE
			System.out.println("State of thread2: " +thread2.getState()); //BLOCKED
			
			thread1.join(); // Main thread waits for thread1 to complete
			thread2.join(); // Main thread waits for thread2 to complete
		} catch (InterruptedException e) {
			System.out.println("Main thread was interrupted.");
		}

		System.out.println("Both threads have completed execution.");
	}

}

class MyRunnable2 implements Runnable {

	@Override
	public void run() {
		accessResource();
	}

	// Synchronized method that simulates access to a shared resource
	public synchronized void accessResource() {
		System.out.println(Thread.currentThread().getName() + " is accessing the resource...");

		try {
			// Simulating work being done with the resource
			Thread.sleep(2000); // Holds the lock for 2 seconds
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName() + " was interrupted.");
		}

		System.out.println(Thread.currentThread().getName() + " has finished accessing the resource.");
	}
}
