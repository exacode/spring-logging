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
@Logged
public class BeanTypeAnnotated {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void sayHello() {
		logger.info("Inside method sayHello");
	}

	public void sayHelloParam(String hello) {
		logger.info("Inside method sayHelloParam. Hello: {}", hello);
	}

	public String sayHelloReturn(String hello) {
		logger.info("Inside method sayHelloReturn. Hello: {}", hello);
		return hello;
	}

	public String sayHelloReturnException(String hello) {
		logger.info("Inside method sayHelloReturnException. Hello: {}", hello);
		throw new NullPointerException("Test exception");
	}
}
