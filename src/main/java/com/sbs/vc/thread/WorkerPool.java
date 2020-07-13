package com.sbs.vc.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class WorkerPool {
	
	private static RejectedExecutionHandlerImpl rejectionHandler;
	private static ThreadFactory threadFactory;
	private static ThreadPoolExecutor executorPool;
	private static MyMonitorThread monitor;
	static {
		    rejectionHandler = new RejectedExecutionHandlerImpl();
	        //Get the ThreadFactory implementation to use
	        threadFactory = Executors.defaultThreadFactory();
	        //creating the ThreadPoolExecutor
	        executorPool = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2), threadFactory, rejectionHandler);
	        monitor = new MyMonitorThread(executorPool, 3);
	        Thread monitorThread = new Thread(monitor);
	        monitorThread.start();
	}

    public static void jobSubmit(Runnable  job ) throws InterruptedException{
        executorPool.execute(job);
        
    }
    
    public static void destroy() throws InterruptedException  {
    	 executorPool.shutdown();
         //shut down the monitor thread
         Thread.sleep(5000);
         monitor.shutdown();
    }
    
}