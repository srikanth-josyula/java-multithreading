package com.threads.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class CustomThreadPool {
	private final int poolSize;
	private final BlockingQueue<Runnable> taskQueue;
	private final Thread[] threads;

	public CustomThreadPool(int poolSize) {
		this.poolSize = poolSize;
		this.taskQueue = new LinkedBlockingQueue<>();
		this.threads = new Thread[poolSize];

		for (int i = 0; i < poolSize; i++) {
			threads[i] = new WorkerThread();
			threads[i].start();
		}
	}

	public void submitTask(Runnable task) {
		synchronized (taskQueue) {
			taskQueue.add(task);
			taskQueue.notify();
		}
	}

	public void shutdown() {
		System.out.println("Shutting down thread pool");
		for (int i = 0; i < poolSize; i++) {
			threads[i] = null;
		}
	}

	private class WorkerThread extends Thread {
		@Override
		public void run() {
			Runnable task;

			while (true) {
				synchronized (taskQueue) {
					while (taskQueue.isEmpty()) {
						try {
							taskQueue.wait();
						} catch (InterruptedException e) {
							System.out.println("An error occurred while queue is waiting: " + e.getMessage());
						}
					}
					task = (Runnable) taskQueue.poll();
				}

				try {
					task.run();
				} catch (RuntimeException e) {
					System.out.println("Thread pool is interrupted due to an issue: " + e.getMessage());
				}
			}
		}
	}
}

public class CustomThreadPoolExample {
	public static void main(String[] args) {
		
		CustomThreadPool customThreadPool = new CustomThreadPool(2);

		for (int j = 0; j < 10; j++) {
			final int finalJ = j; // Declare j as final
			Runnable task = () -> System.out
					.println("Task-" + finalJ + " executed by " + Thread.currentThread().getName());
			customThreadPool.submitTask(task);
		}

		// Shutdown the thread pool when done
		customThreadPool.shutdown();
	}
}

