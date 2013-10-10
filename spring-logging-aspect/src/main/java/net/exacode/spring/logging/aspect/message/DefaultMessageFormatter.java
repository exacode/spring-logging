package net.exacode.spring.logging.aspect.message;

import java.util.Arrays;

import net.exacode.spring.logging.aspect.Logged;
import net.exacode.spring.logging.utils.timer.Timer;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;

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
