package org.springframework.utils.logging.aspect;

import org.fest.assertions.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.utils.logging.aspect.beans.BeanMethodAnnotated;
import org.springframework.utils.logging.aspect.beans.BeanNoAspect;

public class LoggedAspectMethodAnnotatedTest extends LoggedAspectTestBase {

	private TestLoggerProvider testLoggerProvider;

	@Autowired
	private LoggedAspect loggedAspect;

	@Autowired
	private BeanMethodAnnotated beanMethodAnnotated;

	@Autowired
	private BeanNoAspect beanNoAspect;

	@Before
	public void setUp() {
		testLoggerProvider = new TestLoggerProvider();
		loggedAspect.loggerProvider = testLoggerProvider;
	}

	@Test
	public void shouldLogMethodInvocation() {
		beanMethodAnnotated.sayHello();
		Assertions.assertThat(testLoggerProvider.getInvocations()).isEqualTo(1);
	}

	@Test
	public void shouldLogException() {
		try {
			beanMethodAnnotated.sayHelloReturnException("xxx");
		} catch (NullPointerException e) {
			Assertions.assertThat(testLoggerProvider.getInvocations())
					.isEqualTo(1);
			return;
		}
		Assertions.failBecauseExceptionWasNotThrown(NullPointerException.class);
	}

}
