package com.threads.executors;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample {

    private StringBuilder sharedString = new StringBuilder(); // Shared resource

    // Method to append to the shared StringBuilder without synchronization
    public void appendMethod(String str) {
        // Append to the shared resource (no synchronization)
        sharedString.append(str).append(" ");
        System.out.println(Thread.currentThread().getName() + " (CompletableFuture): " + sharedString);
    }

    public static void main(String[] args) {

        CompletableFutureExample example = new CompletableFutureExample(); // Single shared instance

        // Create a completed future with an immediate result
        CompletableFuture<Void> completedFuture = CompletableFuture.completedFuture(null);

        // Run an asynchronous task using completedFuture
        completedFuture.thenRun(() -> example.appendMethod("Hello"));

        // Run another task asynchronously
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> example.appendMethod("World"));

        try {
            // Wait for the second task to complete
            future2.get();

            System.out.println("Both tasks have completed.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
