package org.springframework.logging.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.springframework.logging.aspect.LoggedAspect.DefaultLoggerProvider;
import org.springframework.logging.aspect.LoggedAspect.LoggerProvider;

class TestLoggerProvider implements LoggerProvider {

	private final LoggerProvider defaultLoggerProvider = new DefaultLoggerProvider();

	private int invocations;

	@Override
	public Logger getLogger(ProceedingJoinPoint pjp) {
		invocations++;
		return defaultLoggerProvider.getLogger(pjp);
	}

	public int getInvocations() {
		return invocations;
	}

}