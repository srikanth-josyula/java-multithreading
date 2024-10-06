package com.threads;

public class ThreadRunnablePhase {

	public static void main(String[] args) {

		// Create a thread, which starts in the NEW state
		Thread thread1 = new Thread(new MyRunnable1());
		Thread thread2 = new Thread(new MyRunnable1());

		// Transition to runnable state by calling start()
		System.out.println("Starting thread1: " + thread1.getName());
		thread1.start(); // Moves thread1 to the runnable state

		System.out.println("Starting thread2: " + thread2.getName());
		thread2.start(); // Moves thread2 to the runnable state

		// All threads that are ready to run, including the currently running thread,
		// are in the runnable state
		System.out.println("Thread1 state after start(): " + thread1.getState()); // RUNNABLE
		System.out.println("Thread2 state after start(): " + thread2.getState()); // WAITING or TIMED_WAITING

		// Simulating a higher-priority thread that can preempt thread1 or thread2
		Thread highPriorityThread = new Thread(() -> {
			try {
				System.out.println("High priority thread running: " + Thread.currentThread().getName());
				Thread.sleep(5000); // Sleep to simulate doing some work
				System.out.println("High priority thread completed: " + Thread.currentThread().getName());
			} catch (InterruptedException e) {

			}
		});

		highPriorityThread.setPriority(Thread.MAX_PRIORITY);
		highPriorityThread.start(); // This may preempt thread1 or thread2 if they are running

		// Wait for the threads to finish
		try {
			thread1.join(); // Main thread waits for thread1 to complete
			thread2.join(); // Main thread waits for thread2 to complete
			highPriorityThread.join(); // Main thread waits for high-priority thread to complete
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//Thread state after completion
        System.out.println("Thread1 state after completion: " + thread1.getState()); // TERMINATED
        System.out.println("Thread2 state after completion: " + thread2.getState()); // TERMINATED
        System.out.println("High priority thread state after completion: " + highPriorityThread.getState()); // TERMINATED
    
	}

}

class MyRunnable1 implements Runnable {
	@Override
	public void run() {
		try {
			// Simulate some work being done
			System.out.println("Thread is now running: " + Thread.currentThread().getName());
			Thread.sleep(2000); // Simulating work by sleeping for 2 seconds
			System.out.println("Thread completed: " + Thread.currentThread().getName());
		} catch (InterruptedException e) {

		}
	}
}
