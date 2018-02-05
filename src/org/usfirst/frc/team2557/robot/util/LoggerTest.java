package org.usfirst.frc.team2557.robot.util;

public class LoggerTest {

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			Logger.currentLogLevel = i;
			for (int j = 0; j < 5; j++) {
				Logger.LogMessage(j, "Logging log level: \"" + j + "\" with current level at: \"" + i + "\"");
			}
		}
	}

}
