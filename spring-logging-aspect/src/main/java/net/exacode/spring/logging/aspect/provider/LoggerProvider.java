package net.exacode.spring.logging.aspect.provider;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;

public interface LoggerProvider {

	Logger getLogger(ProceedingJoinPoint pjp);

}