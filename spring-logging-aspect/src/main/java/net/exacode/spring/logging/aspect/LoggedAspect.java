package net.exacode.spring.logging.aspect;

import net.exacode.spring.logging.aspect.message.DefaultMessageFormatter;
import net.exacode.spring.logging.aspect.message.MessageFormatter;
import net.exacode.spring.logging.aspect.provider.DefaultLoggerProvider;
import net.exacode.spring.logging.aspect.provider.LoggerProvider;
import net.exacode.spring.logging.utils.timer.Timer;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;

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

	private final LoggerProvider loggerProvider;

	private final MessageFormatter messageFormatter;

	public LoggedAspect() {
		this(new DefaultLoggerProvider(), new DefaultMessageFormatter());
	}

	public LoggedAspect(LoggerProvider loggerProvider) {
		this(loggerProvider, new DefaultMessageFormatter());
	}

	public LoggedAspect(MessageFormatter messageFormatter) {
		this(new DefaultLoggerProvider(), messageFormatter);
	}

	public LoggedAspect(LoggerProvider loggerProvider,
			MessageFormatter messageFormatter) {
		super();
		this.loggerProvider = loggerProvider;
		this.messageFormatter = messageFormatter;
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

	private Object logMethodInvocation(ProceedingJoinPoint pjp, Logged log)
			throws Throwable {
		Logger logger = loggerProvider.getLogger(pjp);
		Object[] args = pjp.getArgs();
		messageFormatter.logMethodInvocationHeader(pjp, logger, log, args);
		Object result = null;
		if (log.tryCatchFinally()) {
			Timer timer = new Timer().start();
			try {
				result = pjp.proceed(args);
			} catch (Exception e) {
				messageFormatter.logMethodException(pjp, logger, log, e);
				throw e;
			} finally {
				timer.stop();
				messageFormatter.logMethodReturn(pjp, logger, log, timer,
						result);
			}
		} else {
			Timer timer = new Timer();
			timer.start();
			result = pjp.proceed(args);
			timer.stop();
			messageFormatter.logMethodReturn(pjp, logger, log, timer, result);
		}
		return result;
	}

}
