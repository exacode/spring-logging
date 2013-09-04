package org.springframework.utils.logging.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.springframework.utils.logging.aspect.LoggedAspect.DefaultLoggerProvider;
import org.springframework.utils.logging.aspect.LoggedAspect.LoggerProvider;

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