package com.threads;

public class MainThread {

	public static void main(String[] args) {
		 // Get the current thread (main thread)
        Thread mainThread = Thread.currentThread();
        
        System.out.println(mainThread.getName());
        System.out.println(mainThread.getState()); //RUNNABLE
	}
}
