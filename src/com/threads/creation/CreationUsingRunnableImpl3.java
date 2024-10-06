package com.threads.creation;

public class CreationUsingRunnableImpl3 {

	public static void main(String[] args) {

		Thread threadObj = new Thread(() -> {

			try {
				System.out.println("Thread is now running: " + Thread.currentThread().getName());
				// Simulating work by sleeping for 2 seconds
				Thread.sleep(2000);
				System.out.println("Thread completed: " + Thread.currentThread().getName());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		threadObj.start();
	}
}
