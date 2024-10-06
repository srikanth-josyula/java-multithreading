package com.threads.creation;

class MyThread extends Thread {

	public void run() {
		try {
			System.out.println("Thread is now running: " + Thread.currentThread().getName());
			// Simulating work by sleeping for 2 seconds
			Thread.sleep(2000);
			System.out.println("Thread completed: " + Thread.currentThread().getName());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

//public class CreationUsingThreadClass extends BaseClass, Thread { } // Compilation error
public class CreationUsingThreadClass extends Thread {

	public static void main(String[] args) {
		MyThread thread = new MyThread();
		// Start the thread start() calls the run()
		thread.start();
	}
}