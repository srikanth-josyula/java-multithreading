package com.threads.variables;

public class VolatileExample {

	private volatile boolean flag = false; // Shared variable
	//Output
	/* *
	 * Thread 1: Flag is set to true!
	 * Thread 2: Flag is set to true!
	 * */
	
	//private  boolean flag = false; // Shared variable
	//Output
	/* *
	 * Thread 1: Flag is set to true!
	 * note: thread 2 will not print as it considers false still 
	 * */

	public void setFlag() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		flag = true; // Thread 1 sets the flag to true
		System.out.println("Thread 1: Flag is set to true!");
	}

	public void checkFlag() {
		while (!flag) { // Thread 2 checks the flag to be true
			// Busy-waiting goes to infinte
		}
		System.out.println("Thread 2: Flag is set to true!");
	}

	public static void main(String[] args) {
		VolatileExample example = new VolatileExample();

		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				example.setFlag();
			}
		});
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				example.checkFlag();
			}
		});

		thread2.start();
		thread1.start();
	}
}
