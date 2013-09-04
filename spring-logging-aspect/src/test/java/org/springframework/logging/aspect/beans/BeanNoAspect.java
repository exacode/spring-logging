package org.springframework.logging.aspect.beans;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.logging.timer.Timer;
import org.springframework.stereotype.Component;

/**
 * Logging test.
 * 
 * @author mendlik
 */
@Component
public class BeanNoAspect {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void logSayHello() throws Throwable {
		logger.trace(">>>> Entering method: {}", "sayHello");
		logArguments(new Object[] { null }, false, logger);
		Timer timer = new Timer();
		try {
			timer.start();
			sayHello();
			timer.stop();
		} catch (Throwable e) {
			if (logger.isErrorEnabled()) {
				logger.error("XXXX Error occured: ", e);
			}
			throw e;
		} finally {
			if (logger.isTraceEnabled()) {
				logger.trace("<<<< Returning: {} ({})", null,
						timer.getFormattedResult());
			}
		}
	}

	public void logSayHelloNoTryCatch() throws Throwable {
		logger.trace(">>>> Entering method: {}", "sayHello");
		logArguments(new Object[] { null }, false, logger);
		Timer timer = new Timer();
		timer.start();
		sayHello();
		timer.stop();
		if (logger.isTraceEnabled()) {
			logger.trace("<<<< Returning: {} ({})", null,
					timer.getFormattedResult());
		}
	}

	private void logArguments(Object[] args, boolean newLines, Logger logger) {
		if (newLines) {
			logger.trace(">>>> Arguments ({})", args.length);
			for (int i = 0; i < args.length; ++i) {
				logger.trace("\t Argument ({}): {}", i + 1, args[i]);
			}
		} else {
			logger.trace(">>>> Parameters ({}): {}", args.length,
					Arrays.toString(args));
		}
	}

	public void sayHello() {
		logger.info("Inside method sayHello");
	}

}
