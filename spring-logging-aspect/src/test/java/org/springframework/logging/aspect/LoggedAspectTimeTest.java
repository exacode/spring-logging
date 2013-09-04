package org.springframework.logging.aspect;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.logging.aspect.beans.BeanNoAspect;
import org.springframework.logging.aspect.beans.BeanTypeAnnotated;
import org.springframework.logging.timer.Timer;

public class LoggedAspectTimeTest extends LoggedAspectTestBase {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private BeanTypeAnnotated beanTypeAnnotated;

	@Autowired
	private BeanNoAspect beanNoAspect;

	@Test
	@Ignore
	public void timeTest() throws Throwable {
		int n = 1000;

		Timer timer = new Timer();
		timer.start();
		for (int i = 0; i < n; ++i) {
			beanNoAspect.logSayHelloNoTryCatch();
		}
		timer.stop();

		Timer frameworkTimer = new Timer();
		frameworkTimer.start();
		for (int i = 0; i < n; ++i) {
			beanTypeAnnotated.sayHello();
		}
		frameworkTimer.stop();

		logger.info("Log count: {}", n);
		logger.info("Standard logging: {}", timer.getFormattedResult());
		logger.info("Framework logging: {}",
				frameworkTimer.getFormattedResult());
	}
}
