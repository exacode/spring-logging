package org.springframework.utils.logging.aspect.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.utils.logging.aspect.Logged;

/**
 * Logging test.
 * 
 * @author mendlik
 */
@Component
@Logged(eachArgumentInNewLine = true)
public class BeanArguments {

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
