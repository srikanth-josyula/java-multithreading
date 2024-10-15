package com.threads.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinWithRecursiveTaskExample {

    private StringBuilder sharedString = new StringBuilder(); // Shared resource

    // Method to append to the shared StringBuilder without synchronization
    public void appendMethod(String str) {
        // Append to the shared resource (no synchronization)
        sharedString.append(str).append(" ");
        System.out.println(Thread.currentThread().getName() + " (ForkJoinPool): " + sharedString);
    }

    public static void main(String[] args) {
        ForkJoinWithRecursiveTaskExample example = new ForkJoinWithRecursiveTaskExample(); // Single shared instance

        // ForkJoinPool with default parallelism
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        // Create and invoke a RecursiveTask to append "Hello"
        RecursiveTask<Void> task1 = new RecursiveTask<Void>() {
            @Override
            protected Void compute() {
                example.appendMethod("Hello");
                return null; // Return null as we don't need a result
            }
        };

        // Create and invoke a RecursiveTask to append "World"
        RecursiveTask<Void> task2 = new RecursiveTask<Void>() {
            @Override
            protected Void compute() {
                example.appendMethod("World");
                return null; // Return null as we don't need a result
            }
        };

        // Fork and join the tasks (run asynchronously and wait for completion)
        forkJoinPool.invoke(task1);
        forkJoinPool.invoke(task2);

        // Shutdown the ForkJoinPool
        forkJoinPool.shutdown();
    }
}
