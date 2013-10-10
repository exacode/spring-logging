package net.exacode.spring.logging.aspect.message;

import net.exacode.spring.logging.aspect.Logged;
import net.exacode.spring.logging.utils.timer.Timer;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;

public interface MessageFormatter {

	void logMethodInvocationHeader(ProceedingJoinPoint pjp, Logger logger,
			Logged log, Object[] args);

	void logMethodReturn(ProceedingJoinPoint pjp, Logger logger, Logged log,
			Timer timer, Object result);

	void logMethodException(ProceedingJoinPoint pjp, Logger logger, Logged log,
			Exception e);
}
