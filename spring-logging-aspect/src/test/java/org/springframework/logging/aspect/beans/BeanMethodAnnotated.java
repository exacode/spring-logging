package org.springframework.logging.aspect.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.logging.aspect.Logged;
import org.springframework.stereotype.Component;

/**
 * Logging test.
 * 
 * @author mendlik
 */
@Component
public class BeanMethodAnnotated {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Logged(tryCatchFinally = true)
	public void sayHello() {
		logger.info("Inside method sayHello");
	}

	@Logged
	public void sayHelloParam(String hello) {
		logger.info("Inside method sayHelloParam. Hello: {}", hello);
	}

	@Logged
	public String sayHelloReturn(String hello) {
		logger.info("Inside method sayHelloReturn. Hello: {}", hello);
		return hello;
	}

	@Logged
	public String sayHelloReturnException(String hello) {
		logger.info("Inside method sayHelloReturnException. Hello: {}", hello);
		throw new NullPointerException("Test exception");
	}
}
