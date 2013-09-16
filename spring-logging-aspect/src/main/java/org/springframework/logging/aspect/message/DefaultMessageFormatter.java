package org.springframework.logging.aspect.message;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.springframework.logging.aspect.Logged;
import org.springframework.logging.utils.timer.Timer;

public class DefaultMessageFormatter implements MessageFormatter {

	@Override
	public void logMethodInvocationHeader(ProceedingJoinPoint pjp,
			Logger logger, Logged log, Object[] args) {
		if (logger.isTraceEnabled()) {
			logger.trace(">>> {}: {}", pjp.getSignature().toShortString(),
					Arrays.toString(args));
		}
	}

	@Override
	public void logMethodReturn(ProceedingJoinPoint pjp, Logger logger,
			Logged log, Timer timer, Object result) {
		if (logger.isTraceEnabled()) {
			logger.trace("<<< Returning {}: {} ({})", pjp.getSignature()
					.toShortString(), result, timer.getTextResult());
		}
	}

	@Override
	public void logMethodException(ProceedingJoinPoint pjp, Logger logger,
			Logged log, Exception e) {
		if (logger.isErrorEnabled()) {
			logger.error("XXX Error occured: ", e);
		}
	}

}
