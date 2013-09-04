package org.springframework.utils.logging.timer;

import java.util.concurrent.TimeUnit;

/**
 * Dynamically formats time duration.
 * <p>
 * Displays only significant parts of time duration.
 * 
 * @author mendlik
 */
public class DynamicTimeFormatter implements TimeFormatter {

	@Override
	public String format(long milliseconds) {
		long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds);
		long hours = TimeUnit.MILLISECONDS.toHours(milliseconds);
		milliseconds -= TimeUnit.SECONDS.toMillis(seconds);
		seconds -= TimeUnit.MINUTES.toSeconds(minutes);
		minutes -= TimeUnit.HOURS.toMinutes(hours);

		String result = null;
		if (hours > 0) {
			result = String.format("%d:%02d:%02d.%03d[h:m:s.ms]", hours,
					minutes, seconds, milliseconds);
		} else if (minutes > 0) {
			result = String.format("%d:%02d.%03d[m:s.ms]", minutes, seconds,
					milliseconds);
		} else if (seconds > 0) {
			result = String.format("%d.%03d[s.ms]", seconds, milliseconds);
		} else {
			result = String.format("%d[ms]", milliseconds);
		}
		return result;
	}

}
