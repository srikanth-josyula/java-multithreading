package com.threads.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleRunnableExecuteExample {
	public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(1);

        // Execute a Runnable task
        Runnable task = () -> {
            System.out.println("Task is being executed!");
            // Simulate some work
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task completed!");
        };

        executor.execute(task);  // Fire-and-forget
        executor.shutdown();
    }
}
