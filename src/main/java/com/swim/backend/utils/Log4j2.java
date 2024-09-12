package com.swim.backend.utils;

import com.swim.backend.enumeration.LogEventType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.spi.AbstractLogger;
import org.apache.logging.log4j.spi.ExtendedLoggerWrapper;

public class Log4j2 extends ExtendedLoggerWrapper{
        
    private static final long serialVersionUID = 1L;

	private static final String IGNORE_CLASS_NAME = Log4j2.class.getName();

	private final ExtendedLoggerWrapper extendedLogger;

    private static final Log4j2 systemLogger = new Log4j2(LogManager.getLogger("system"));

    public Log4j2(final Logger extendedLogger) {
		super((AbstractLogger) extendedLogger, extendedLogger.getName(), extendedLogger.getMessageFactory());
		this.extendedLogger = this;
	}

    public void generalLog(Level level, Marker marker, String msg, Throwable throwable) {
		extendedLogger.logIfEnabled(IGNORE_CLASS_NAME, level, marker, msg, throwable);
	}

	public static void entryLog(String username, String className, String message) {
		logSystemInfo(LogEventType.START, username, className, message);
	}

	public static void exitLog(String username, String className, String message) {
		logSystemInfo(LogEventType.END, username, className, message);
	}

	public static void logSystemInfo(LogEventType eventType, String username, String className, String message) {
		if (systemLogger.isInfoEnabled()) {
			systemLogger.generalLog(Level.INFO, null, "[" + eventType + "], [" + username + "], [" + className + "], [" + message + "]", null);
		}
	}
        
	public static void logSystemError(LogEventType eventType, String username, String className, String message) {
		if (systemLogger.isEnabled(Level.ERROR)) {
			systemLogger.generalLog(Level.ERROR, null, "[" + eventType + "], [" + username + "], [" + className + "], [" + message + "]", null);
		}
	}
	public static void logSystemError(LogEventType eventType, String message) {
		if (systemLogger.isEnabled(Level.ERROR)) {
			systemLogger.generalLog(Level.ERROR, null, "[" + eventType + "], [" + message + "]", null);
		}
	}

}
