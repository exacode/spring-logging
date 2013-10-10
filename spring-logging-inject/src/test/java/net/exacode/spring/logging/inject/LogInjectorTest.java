package net.exacode.spring.logging.inject;

import net.exacode.spring.logging.inject.Log;

import org.fest.assertions.api.Assertions;
import org.junit.Test;
import org.slf4j.Logger;

public class LogInjectorTest extends LogInjectorTestBase {

	@Log
	private Logger logger;

	@Test
	public void shouldInjectLogger() {
		Assertions.assertThat(logger).isNotNull();
	}

	@Test
	public void shouldNameLoggerAfterThisClass() {
		Assertions.assertThat(logger.getName()).isEqualTo(
				this.getClass().getName());
	}

	@Test
	public void shouldLogMessage() {
		logger.info("Message from injected logger");
	}
}
