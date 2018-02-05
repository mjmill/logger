package org.usfirst.frc.team2557.robot.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;

public class Logger extends Thread {

	/*
	 * Singleton section.
	 */
	
	/* The only instance of this Singleton. */
	private static Logger s_logger;
	
	/* Private constructor to enforce the Singleton. */
	private Logger() {
		queue = new ArrayBlockingQueue<>(INITIAL_QUEUE_SIZE);
	}
	
	/* The way to get the one and only instance (Singleton) of the class. */
	public static Logger getInstance() {
		/* See if there is a instance yet. */
		if (s_logger == null) {
			s_logger = new Logger();
			
			/* Start the thread so nothing else needs to. */
			s_logger.start();
		}
		
		return s_logger;
	}
	
	/*
	 * Thread section.
	 */
	
	public static final int INITIAL_QUEUE_SIZE = 100;
	
	private ArrayBlockingQueue<String> queue;
	
	@Override
	public void run() {
		FileWriter currentFile = null;
		Boolean keepRunning = true;
		try {
			while (keepRunning || !queue.isEmpty()) {
				try {
					currentFile = new FileWriter(logFileName, true);
					String currentMessage = queue.take();
					currentFile.write(currentMessage + "\r\n");
					currentFile.close();
					currentFile = null;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (this.isInterrupted()) {
					keepRunning = false;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s_logger = null;
	}
	
	/*
	 * Logging section.
	 */
	
	public static final int CRITICAL = 0;
	public static final int EMERGENCY = 1;
	public static final int WARNING = 2;
	public static final int INFORMATION = 3;
	public static final int DEBUG = 4;
	
	// private LogLevel currentLogLevel = LogLevel.INFORMATION;
	public static int currentLogLevel = INFORMATION;
	public static String logFileName = "azthar.out";
	
	public static void LogMessage(int level, String message) {
		if (level > currentLogLevel) {
			return;
		}
		System.out.println(message);
		try {
			getInstance().queue.put(message);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void LogCritical(String message) {
		LogMessage(CRITICAL, message);
	}
	
	public static void LogEmergency(String message) {
		LogMessage(EMERGENCY, message);
	}
	
	public static void LogWarning(String message) {
		LogMessage(WARNING, message);
	}
	
	public static void LogInformation(String message) {
		LogMessage(INFORMATION, message);
	}
	
	public static void LogDebug(String message) {
		LogMessage(DEBUG, message);
	}
}
