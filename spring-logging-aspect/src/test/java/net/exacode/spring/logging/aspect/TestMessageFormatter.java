package net.exacode.spring.logging.aspect;

import net.exacode.spring.logging.aspect.Logged;
import net.exacode.spring.logging.aspect.message.DefaultMessageFormatter;
import net.exacode.spring.logging.utils.timer.Timer;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;

public class TestMessageFormatter extends DefaultMessageFormatter {

	private int methodHeaders;

	private int methodReturns;

	private int methodExceptions;

	@Override
	public void logMethodInvocationHeader(ProceedingJoinPoint pjp,
			Logger logger, Logged log, Object[] args) {
		methodHeaders++;
		super.logMethodInvocationHeader(pjp, logger, log, args);
	}

	@Override
	public void logMethodReturn(ProceedingJoinPoint pjp, Logger logger,
			Logged log, Timer timer, Object result) {
		methodReturns++;
		super.logMethodReturn(pjp, logger, log, timer, result);
	}

	@Override
	public void logMethodException(ProceedingJoinPoint pjp, Logger logger,
			Logged log, Exception e) {
		methodExceptions++;
		super.logMethodException(pjp, logger, log, e);
	}

	public int getMethodHeaders() {
		return methodHeaders;
	}

	public int getMethodReturns() {
		return methodReturns;
	}

	public int getMethodExceptions() {
		return methodExceptions;
	}

	public void reset() {
		methodHeaders = 0;
		methodExceptions = 0;
		methodReturns = 0;
	}

}
