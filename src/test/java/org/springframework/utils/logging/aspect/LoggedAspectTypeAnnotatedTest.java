package org.springframework.utils.logging.aspect;

import org.fest.assertions.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.utils.logging.aspect.beans.BeanArgumentNewLine;
import org.springframework.utils.logging.aspect.beans.BeanArguments;
import org.springframework.utils.logging.aspect.beans.BeanNoAspect;
import org.springframework.utils.logging.aspect.beans.BeanTypeAnnotated;

public class LoggedAspectTypeAnnotatedTest extends LoggedAspectTestBase {

	private TestLoggerProvider testLoggerProvider;

	@Autowired
	private LoggedAspect loggedAspect;

	@Autowired
	private BeanTypeAnnotated beanTypeAnnotated;

	@Autowired
	private BeanArguments beanArguments;

	@Autowired
	private BeanArgumentNewLine beanArgumentNewLine;

	@Autowired
	private BeanNoAspect beanNoAspect;

	@Before
	public void setUp() {
		testLoggerProvider = new TestLoggerProvider();
		loggedAspect.loggerProvider = testLoggerProvider;
	}

	@Test
	public void shouldLogMethodInvocation() {
		beanTypeAnnotated.sayHello();
		Assertions.assertThat(testLoggerProvider.getInvocations()).isEqualTo(1);
	}

	@Test
	public void shouldLogException() {
		try {
			beanTypeAnnotated.sayHelloReturnException("xxx");
		} catch (NullPointerException e) {
			Assertions.assertThat(testLoggerProvider.getInvocations())
					.isEqualTo(1);
			return;
		}
		Assertions.failBecauseExceptionWasNotThrown(NullPointerException.class);
	}

	@Test
	public void loggingArgumentsAnnotatedTest() {
		beanArguments.zeroArgMethod();
		beanArguments.oneArgMethod("a1");
		beanArguments.twoArgMethod("a1", "a2");
		Assertions.assertThat(testLoggerProvider.getInvocations()).isEqualTo(3);
	}

	@Test
	public void loggingArgumentNewLineAnnotatedTest() {
		beanArgumentNewLine.zeroArgMethod();
		beanArgumentNewLine.oneArgMethod("a1");
		beanArgumentNewLine.twoArgMethod("a1", "a2");
		Assertions.assertThat(testLoggerProvider.getInvocations()).isEqualTo(3);
	}

}
