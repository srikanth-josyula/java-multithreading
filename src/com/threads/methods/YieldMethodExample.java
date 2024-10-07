package com.threads.methods;

public class YieldMethodExample {

	public static void main(String[] args) {

		Thread t1 = new Thread(new RunnableExample());
		Thread t2 = new Thread(new RunnableExample());

		t1.start();

		t2.start();
	}

}

class RunnableExample implements Runnable {

	@Override
	public void run() {
		try {
			System.out.println("Thread is now running: " + Thread.currentThread().getName());
			// Simulating work by sleeping for 2 seconds
			Thread.sleep(2000);

			// Yield control to other threads
			Thread.yield(); // This is now correctly placed within the run method

			System.out.println("Thread completed: " + Thread.currentThread().getName());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

//Output
//Thread is now running: Thread-0
//Thread is now running: Thread-1
//Thread completed: Thread-1
//Thread completed: Thread-0

//This thread-0 has gave up for thread-1
