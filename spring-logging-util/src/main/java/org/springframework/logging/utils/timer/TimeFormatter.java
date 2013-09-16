package org.springframework.logging.utils.timer;

/**
 * Formats time duration.
 * 
 * @author mendlik
 */
public interface TimeFormatter {

	TimeFormatter DEFAULT = new DynamicTimeFormatter();

	/**
	 * Formats time duration expressed in milliseconds.
	 * 
	 * @param millis
	 * @return formatted text with time duration
	 */
	String format(long millis);
}
