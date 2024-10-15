package com.threads.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Callable;

public class CallableExecutorServiceExample {

    private StringBuilder sharedString = new StringBuilder(); // Shared resource

    // Method to append to the shared StringBuilder without synchronization
    public void appendMethod(String str) {
        // Append to the shared resource (no synchronization)
        sharedString.append(str).append(" ");
        System.out.println(Thread.currentThread().getName() + " (ExecutorService): " + sharedString);
    }

    public static void main(String[] args) {
        CallableExecutorServiceExample example = new CallableExecutorServiceExample(); // Single shared instance

        // Create an ExecutorService with a fixed thread pool (2 threads)
        ExecutorService executor = Executors.newFixedThreadPool(2);

        try {
            // Define two Callable tasks using anonymous inner classes
            Future<?> future1 = executor.submit(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    example.appendMethod("Hello");
                    return null; // No return value needed
                }
            });

            Future<?> future2 = executor.submit(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    example.appendMethod("World");
                    return null; // No return value needed
                }
            });

            // Wait for the tasks to complete
            future1.get(); // Blocks until the task completes
            future2.get(); // Blocks until the task completes

            System.out.println("Both tasks have completed.");

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            // Shutdown the executor
            executor.shutdown();
        }
    }
}
