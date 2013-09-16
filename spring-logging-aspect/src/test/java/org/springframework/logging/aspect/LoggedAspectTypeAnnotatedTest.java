package org.springframework.logging.aspect;

import org.fest.assertions.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.logging.aspect.beans.BeanArgumentNewLine;
import org.springframework.logging.aspect.beans.BeanNoAspect;
import org.springframework.logging.aspect.beans.BeanTypeAnnotated;

public class LoggedAspectTypeAnnotatedTest extends LoggedAspectTestBase {
	@Autowired
	private TestMessageFormatter messageFormatter;

	@Autowired
	private LoggedAspect loggedAspect;

	@Autowired
	private BeanTypeAnnotated beanTypeAnnotated;

	@Autowired
	private BeanArgumentNewLine beanArgumentNewLine;

	@Autowired
	private BeanNoAspect beanNoAspect;

	@Before
	public void setUp() {
		messageFormatter.reset();
	}

	@Test
	public void shouldLogMethodInvocation() {
		beanTypeAnnotated.sayHello();
		Assertions.assertThat(messageFormatter.getMethodHeaders()).isEqualTo(1);
		Assertions.assertThat(messageFormatter.getMethodReturns()).isEqualTo(1);
		Assertions.assertThat(messageFormatter.getMethodExceptions())
				.isEqualTo(0);
	}

	@Test
	public void shouldLogException() {
		try {
			beanTypeAnnotated.sayHelloReturnException("xxx");
		} catch (NullPointerException e) {
			Assertions.assertThat(messageFormatter.getMethodHeaders())
					.isEqualTo(1);
			Assertions.assertThat(messageFormatter.getMethodReturns())
					.isEqualTo(1);
			Assertions.assertThat(messageFormatter.getMethodExceptions())
					.isEqualTo(1);
			return;
		}
		Assertions.failBecauseExceptionWasNotThrown(NullPointerException.class);
	}

	@Test
	public void loggingArgumentNewLineAnnotatedTest() {
		beanArgumentNewLine.zeroArgMethod();
		beanArgumentNewLine.oneArgMethod("a1");
		beanArgumentNewLine.twoArgMethod("a1", "a2");
		Assertions.assertThat(messageFormatter.getMethodHeaders()).isEqualTo(3);
		Assertions.assertThat(messageFormatter.getMethodReturns()).isEqualTo(3);
		Assertions.assertThat(messageFormatter.getMethodExceptions())
				.isEqualTo(0);
	}
}
