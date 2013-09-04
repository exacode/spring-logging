package org.springframework.logging.inject;

import org.fest.assertions.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.logging.inject.Log;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { LogInjectorConfiguration.class }, loader = AnnotationConfigContextLoader.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
public class LogInjectorTest {

	@Log
	private Logger logger;

	@Test
	public void shouldInjectLogger() {
		Assertions.assertThat(logger).isNotNull();
	}

	@Test
	public void shouldLogMessage() {
		logger.info("Message from injected logger");
	}
}
