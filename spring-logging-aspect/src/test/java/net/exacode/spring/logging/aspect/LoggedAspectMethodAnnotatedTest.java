package net.exacode.spring.logging.aspect;

import net.exacode.spring.logging.aspect.LoggedAspect;
import net.exacode.spring.logging.aspect.beans.BeanMethodAnnotated;
import net.exacode.spring.logging.aspect.beans.BeanNoAspect;

import org.fest.assertions.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class LoggedAspectMethodAnnotatedTest extends LoggedAspectTestBase {
	@Autowired
	private TestMessageFormatter messageFormatter;

	@Autowired
	private LoggedAspect loggedAspect;

	@Autowired
	private BeanMethodAnnotated beanMethodAnnotated;

	@Autowired
	private BeanNoAspect beanNoAspect;

	@Before
	public void setUp() {
		messageFormatter.reset();
	}

	@Test
	public void shouldLogMethodInvocation() {
		beanMethodAnnotated.sayHello();
		Assertions.assertThat(messageFormatter.getMethodHeaders()).isEqualTo(1);
		Assertions.assertThat(messageFormatter.getMethodReturns()).isEqualTo(1);
	}

	@Test
	public void shouldLogException() {
		try {
			beanMethodAnnotated.sayHelloReturnException("xxx");
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

}
