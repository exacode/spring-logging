package org.springframework.logging.timer;

/**
 * Constructed to make time records. Uses System.currentTimeMillis().
 * 
 * @author mendlik
 */
public class Timer {

	private Long startTime = null;

	private Long result = null;

	private TimeFormatter timeFormatter = new DynamicTimeFormatter();

	public Timer() {
		this(new DynamicTimeFormatter());
	}

	public Timer(TimeFormatter timeFormatter) {
		this.timeFormatter = timeFormatter;
	}

	/**
	 * Starts the timer.
	 * 
	 * @return this instance
	 */
	public Timer start() {
		startTime = System.currentTimeMillis();
		return this;
	}

	/**
	 * Stops the timer.
	 * 
	 * @return this instance
	 */
	public Timer stop() {
		if (startTime == null) {
			throw new IllegalArgumentException(
					"Stop method invoked before start method");
		}
		result = System.currentTimeMillis() - startTime;
		return this;
	}

	/**
	 * Returns result as value in milliseconds.
	 * 
	 * @return
	 */
	public long getResult() {
		if (result == null) {
			throw new IllegalArgumentException(
					"Get results invoked before stop method");
		}
		return result;
	}

	public String getFormattedResult() {
		return timeFormatter.format(getResult());
	}

}
