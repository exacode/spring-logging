package org.springframework.utils.logging.timer;

/**
 * Formats time duration.
 * 
 * @author mendlik
 */
public interface TimeFormatter {

	/**
	 * Formats time duration expressed in milliseconds.
	 * 
	 * @param millis
	 * @return formatted text with time duration
	 */
	String format(long millis);
}
