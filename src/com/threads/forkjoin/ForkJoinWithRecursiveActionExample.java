package com.threads.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinWithRecursiveActionExample {

    private StringBuilder sharedString = new StringBuilder(); // Shared resource

    // Method to append to the shared StringBuilder without synchronization
    public void appendMethod(String str) {
        // Append to the shared resource (no synchronization)
        sharedString.append(str).append(" ");
        System.out.println(Thread.currentThread().getName() + " (ForkJoinPool): " + sharedString);
    }

    public static void main(String[] args) {
        ForkJoinWithRecursiveActionExample example = new ForkJoinWithRecursiveActionExample(); // Single shared instance

        // ForkJoinPool with default parallelism
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        // Create and invoke a RecursiveAction to append "Hello"
        RecursiveAction task1 = new RecursiveAction() {
            @Override
            protected void compute() {
                example.appendMethod("Hello");
            }
        };

        // Create and invoke a RecursiveAction to append "World"
        RecursiveAction task2 = new RecursiveAction() {
            @Override
            protected void compute() {
                example.appendMethod("World");
            }
        };

        // Fork and join the tasks (run asynchronously and wait for completion)
        forkJoinPool.invoke(task1);
        forkJoinPool.invoke(task2);

        // Shutdown the ForkJoinPool
        forkJoinPool.shutdown();
    }
}
