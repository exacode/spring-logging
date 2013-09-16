package org.springframework.logging.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker used by logging aspect. All marked methods are logged by slf4j logger.
 * 
 * @see LoggedAspect
 * @author mendlik
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD, ElementType.TYPE })
public @interface Logged {

	/**
	 * True mens that method is invoked in the catch finally block. So timer is
	 * always stopped, all exceptions are logged on console. (Default: true).
	 * <p>
	 * 
	 * Note: It slows down the method invocation.
	 * 
	 * @return
	 */
	boolean tryCatchFinally() default true;

}