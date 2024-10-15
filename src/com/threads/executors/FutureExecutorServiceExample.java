package com.threads.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;

public class FutureExecutorServiceExample {

    private StringBuilder sharedString = new StringBuilder(); // Shared resource

    // Method to append to the shared StringBuilder without synchronization
    public void appendMethod(String str) {
        // Append to the shared resource (no synchronization)
        sharedString.append(str).append(" ");
        System.out.println(Thread.currentThread().getName() + " (ExecutorService): " + sharedString);
    }

    public static void main(String[] args) {
        FutureExecutorServiceExample example = new FutureExecutorServiceExample(); // Single shared instance

        // Create an ExecutorService with a fixed thread pool (2 threads)
        ExecutorService executor = Executors.newFixedThreadPool(2);

        try {
            // Submit first task to the ExecutorService and get Future object
            Future<?> future1 = executor.submit(() -> example.appendMethod("Hello"));

            // Wait for the first task to complete (synchronous behavior)
            future1.get(); // Blocks until the task completes

            // Submit second task to the ExecutorService and get Future object
            Future<?> future2 = executor.submit(() -> example.appendMethod("World"));

            // Wait for the second task to complete (synchronous behavior)
            future2.get(); // Blocks until the task completes

            System.out.println("Both tasks have completed synchronously.");

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            // Shutdown the executor
            executor.shutdown();
        }
    }
}

