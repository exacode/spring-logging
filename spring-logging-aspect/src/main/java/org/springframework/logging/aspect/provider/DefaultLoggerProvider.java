package org.springframework.logging.aspect.provider;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultLoggerProvider implements LoggerProvider {

	@Override
	public Logger getLogger(ProceedingJoinPoint pjp) {
		return LoggerFactory.getLogger(pjp.getSignature().getDeclaringType());
	}

}