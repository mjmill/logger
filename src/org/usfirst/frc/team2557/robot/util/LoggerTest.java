package org.usfirst.frc.team2557.robot.util;

public class LoggerTest {

	public static void main(String[] args) {
		// Set the log file name to something other than the default.
		Logger myLogger = new Logger("LoggerTest.txt");
		
		for (int i = 0; i < 5; i++) {
			// Each iteration increases the log level the logger will log messages at.
			myLogger.currentLogLevel = i;
			
			for (int j = 0; j < 5; j++) {
				// Test logging at each level for this log level.
				myLogger.LogMessage(j, "Logging log level: \"" + j + "\" with current level at: \"" + i + "\"");
			}
		}
	}

}
