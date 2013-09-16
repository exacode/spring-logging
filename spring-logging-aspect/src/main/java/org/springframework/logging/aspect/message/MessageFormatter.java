package org.springframework.logging.aspect.message;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.springframework.logging.aspect.Logged;
import org.springframework.logging.utils.timer.Timer;

public interface MessageFormatter {

	void logMethodInvocationHeader(ProceedingJoinPoint pjp, Logger logger,
			Logged log, Object[] args);

	void logMethodReturn(ProceedingJoinPoint pjp, Logger logger, Logged log,
			Timer timer, Object result);

	void logMethodException(ProceedingJoinPoint pjp, Logger logger, Logged log,
			Exception e);
}
