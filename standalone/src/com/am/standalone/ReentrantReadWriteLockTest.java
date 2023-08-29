package com.am.standalone;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTest {

	private static final ReentrantReadWriteLock lock
		= new ReentrantReadWriteLock(true);
	
	private static final ReentrantReadWriteLock lock2
	= new ReentrantReadWriteLock(true);

	// Initially the string contains only 1 character
	private static String message = "a";

	// Main driver method
	public static void main(String[] args)
		throws InterruptedException
	{

		// Creating threads
		Thread t1 = new Thread(new Read1());
		Thread t2 = new Thread(new Read2());
		Thread t3 = new Thread(new Write1());
		Thread t4 = new Thread(new Write2());
		// Starting threads with help of start() method
		
		
		t1.start();
		t3.start();
		t4.start();
		t2.start();
		
		
		t1.join();
		t2.join();
		t3.join();
		t4.join();
	}

	static class Read1 implements Runnable {

		// run() method for thread
		public void run()
		{

			for (int i = 0; i <= 100; i++) {
				if (lock.isWriteLocked()) {
					System.out.println(
						"I'll take the lock from Write ReadThread1  "+i);
				}

				// operating lock()
				

				System.out.println(
					"ReadThread 1 "
					+ " Message is " + message);
				
			}
		}
	}

	static class Read2 implements Runnable {

		public void run()
		{
			lock2.readLock().lock();
			for (int i = 0; i <= 100; i++) {
				if (lock.isWriteLocked()) {
					System.out.println(
						"I'll take the lock from Write ReadThread2  "+i);
				}

				// operating lock()
				System.out.println(
					"ReadThread 2 "
					+ " Message is " + message +"   "+ i);
				
			}
			lock2.readLock().unlock();
		}
		/*
		// run() method for thread
		public void run()
		{

			for (int i = 0; i <= 10; i++) {

				// Try block to check for exceptions
				try {
					lock.writeLock().lock();
					message = message.concat("b");
				}
				finally {
					lock.writeLock().unlock();
				}
			}
		}  */
	}
	
	static class Write1 implements Runnable {

		// run() method for thread
		public void run()
		{
			//lock.writeLock().lock();
			for (int i = 0; i <= 100; i++) {

				// Try block to check fr exceptions
				try {
					message = message.concat("c");
					System.out.println("Write Thread3  "+i);
				}
				finally {
					
				}
			}
			//lock.writeLock().unlock();
		}
	}
	
	static class Write2 implements Runnable {

		// run() method for thread
		public void run()
		{
			lock.writeLock().lock();
			for (int i = 0; i <= 100; i++) {

				// Try block to check fr exceptions
				try {
					message = message.concat("d");
					System.out.println("Write Lock Thread4  "+i);
				}
				finally {
					
				}
			}
			lock.writeLock().unlock();
		}
	}
}

