package com.threads;

public class ThreadStartPhase {
	
	public static void main(String[] args) {
		
		// Creating a thread in the new state
        Thread thread = new Thread(new MyRunnable());
		
		
        // Thread is in the new state until start() is called
        System.out.println("Thread created. Current state: " + thread.getState()); // NEW
        
        // Memory is allocated, but the thread hasn't started yet
        System.out.println("Thread name: " + thread.getName());			//Thread-0
        System.out.println("Thread priority: " + thread.getPriority());	//5
        
        // Configuring properties when thread is NEW state
        thread.setName("MyCustomThread");
        thread.setPriority(Thread.MAX_PRIORITY); // Set to maximum priority
        System.out.println("Thread name set to: " + thread.getName());	//MyCustomThread
        System.out.println("Thread priority set to: " + thread.getPriority()); //10
        
        // This would execute run() in the current thread context, not in a new thread it will run for main thread
        thread.run(); 	//Thread is now running: main
        
        
        // Calling start() to move to the runnable state
        thread.start();
        System.out.println("After calling start(), thread state: " + thread.getState()); // RUNNABLE or TERMINATED
        //This start will run the run method now we will see run output too as 'Thread is now running: MyCustomThread'

        
        // Wait for the thread to finish execution
        try {
            thread.join(); // Wait for the thread to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Check the thread state after completion
        System.out.println("Thread state after completion: " + thread.getState()); // TERMINATED
	}
	
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        // This is the code that runs in the thread when it starts.
        System.out.println("Thread is now running: " + Thread.currentThread().getName());
    }
}
