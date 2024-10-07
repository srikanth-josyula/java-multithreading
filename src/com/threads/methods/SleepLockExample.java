package com.threads.methods;

public class SleepLockExample {

	private static final Object lock = new Object();

	public static void main(String[] args) {
		Thread thread1 = new Thread(() -> {
			synchronized (lock) {
				System.out.println("Thread 1: Holding the lock...");
				try {
					// Thread 1 goes to sleep while holding the lock
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					System.out.println("Thread 1 was interrupted.");
				}
				System.out.println("Thread 1: Released the lock.");
			}
		});

		Thread thread2 = new Thread(() -> {
			synchronized (lock) {
				System.out.println("Thread 2: Acquired the lock.");
			}
			System.out.println("Thread 2: Released the lock."); // Indicate when thread 2 releases the lock
		});

		thread1.start();
		// Ensure Thread 1 starts first
		try {
			Thread.sleep(100); // Short sleep to ensure thread1 starts first
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread2.start();
	}
}

//OUTPUT
/*Thread 1: Holding the lock...
Thread 1: Yielding control...
Thread 2: (This may or may not print immediately as it waits for Thread 1 to release the lock)
// After Thread 1 completes its sleep:
Thread 1: Released the lock.
// Now Thread 2 can acquire the lock:
Thread 2: Acquired the lock.
Thread 2: Released the lock.
*/