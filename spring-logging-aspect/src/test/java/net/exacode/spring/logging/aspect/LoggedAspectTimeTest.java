package net.exacode.spring.logging.aspect;

import net.exacode.spring.logging.aspect.beans.BeanNoAspect;
import net.exacode.spring.logging.aspect.beans.BeanTypeAnnotated;
import net.exacode.spring.logging.utils.timer.Timer;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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
		logger.info("Standard logging: {}", timer.getTextResult());
		logger.info("Framework logging: {}", frameworkTimer.getTextResult());
	}
}
