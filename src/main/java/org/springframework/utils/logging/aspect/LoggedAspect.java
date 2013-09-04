package org.springframework.utils.logging.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.utils.logging.timer.Timer;

/**
 * Aspect that logs information about method invocation.
 * <p>
 * Log levels are hardcoded because slf4j does not support log levels changes in
 * runtime.
 * <p>
 * In order to activate {@link Logged} aspects register {@link LoggedAspect} in
 * the spring context.
 * 
 * @see Logged
 * @author mendlik
 */
@Aspect
public class LoggedAspect {

	static interface LoggerProvider {

		Logger getLogger(ProceedingJoinPoint pjp);

	}

	static class DefaultLoggerProvider implements LoggerProvider {

		@Override
		public Logger getLogger(ProceedingJoinPoint pjp) {
			return LoggerFactory.getLogger(pjp.getSignature()
					.getDeclaringType());
		}

	}

	protected LoggerProvider loggerProvider = new DefaultLoggerProvider();

	/**
	 * Aspect activated around method invocation. All method arguments are
	 * filled automatically by aspectJ framework.
	 * 
	 * @param pjp
	 *            - {@link ProceedingJoinPoint}
	 * @param log
	 *            - log annotation. {@link Logged}
	 * @return
	 * @throws Throwable
	 */
	@Around(value = "@annotation(log)", argNames = "pjp, log")
	public Object logAnnotatedMethod(final ProceedingJoinPoint pjp,
			final Logged log) throws Throwable {
		return logMethodInvocation(pjp, log);
	}

	/**
	 * Aspect activated around method invocation. All method arguments are
	 * filled automatically by aspectJ framework.
	 * 
	 * @param pjp
	 *            - {@link ProceedingJoinPoint}
	 * @param log
	 *            - log annotation. {@link Logged}
	 * @return
	 * @throws Throwable
	 */
	@Around(value = "@within(log)", argNames = "pjp, log")
	public Object logTypeAnnotatedMethod(final ProceedingJoinPoint pjp,
			final Logged log) throws Throwable {
		return logMethodInvocation(pjp, log);
	}

	/**
	 * {@link LoggerFactory} caches loggers so no additional cache is necessary.
	 * 
	 * @param pjp
	 * @return
	 */
	private Logger getLogger(ProceedingJoinPoint pjp) {
		return loggerProvider.getLogger(pjp);
	}

	/**
	 * Invokes method and log crucial information.
	 * 
	 * @param pjp
	 * @param log
	 * @return
	 * @throws Throwable
	 */
	private Object logMethodInvocation(ProceedingJoinPoint pjp, Logged log)
			throws Throwable {
		Logger logger = getLogger(pjp);
		Object[] args = pjp.getArgs();

		if (logger.isTraceEnabled()) {
			logMethodInvocationArguments(pjp, logger, log, args);
		}

		return logMethodInocation(pjp, logger, log, args);
	}

	private void logMethodInvocationArguments(ProceedingJoinPoint pjp,
			Logger logger, Logged log, Object[] args) {
		if (log.eachArgumentInNewLine()) {
			logger.trace(">>> {}", pjp.getSignature().getName());
			logger.trace(">>> Arguments ({})", args.length);
			for (int i = 0; i < args.length; ++i) {
				logger.trace("\t Argument ({}): {}", i + 1, args[i]);
			}
		} else {
			logger.trace(">>> {}: {}", pjp.getSignature().toShortString(),
					Arrays.toString(args));
		}
	}

	private Object logMethodInocation(ProceedingJoinPoint pjp, Logger logger,
			Logged log, Object[] args) throws Throwable {
		Object result = null;
		if (!logger.isTraceEnabled() && !logger.isErrorEnabled()) {
			result = pjp.proceed(args);
		} else if (log.tryCatchFinally()) {
			Timer timer = new Timer().start();
			try {
				result = pjp.proceed(args);
			} catch (Exception e) {
				if (logger.isErrorEnabled()) {
					logger.error("XXX Error occured: ", e);
				}
				throw e;
			} finally {
				timer.stop();
				if (logger.isTraceEnabled()) {
					logger.trace("<<< Returning {}: {} ({})", pjp
							.getSignature().toShortString(), result, timer
							.getFormattedResult());
				}
			}
		} else {
			Timer timer = new Timer();
			timer.start();
			result = pjp.proceed(args);
			timer.stop();
			if (logger.isTraceEnabled()) {
				logger.trace("<<< Returning {}: {} ({})", pjp.getSignature()
						.toShortString(), result, timer.getFormattedResult());
			}
		}
		return result;
	}

}
