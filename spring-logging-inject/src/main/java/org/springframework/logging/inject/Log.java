package org.springframework.logging.inject;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker for {@link org.slf4j.Logger} injection.
 * 
 * @see LogInjector
 * @author mendlik
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Log {
	/**
	 * Specifies logger name. Injected logger will be named corresponding to the
	 * class passed as parameter. The default class is the class of a bean where
	 * injection takes place.
	 */
	Class<?> fromClass() default DEFAULT.class;

	/**
	 * Create as a default value for {@link Log#fromClass()}.
	 * 
	 * @author mendlik
	 * 
	 */
	static class DEFAULT {
	}

}