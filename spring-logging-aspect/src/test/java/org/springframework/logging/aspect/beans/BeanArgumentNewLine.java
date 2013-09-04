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
public class BeanArgumentNewLine {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void zeroArgMethod() {
		logger.info("Inside method zeroArgMethod.");
	}

	public void oneArgMethod(String a1) {
		logger.info("Inside method thaArgMethod. Args: {}", a1);
	}

	public void twoArgMethod(String a1, String a2) {
		logger.info("Inside method thaArgMethod. Args: {}, {}", a1, a2);
	}

}
