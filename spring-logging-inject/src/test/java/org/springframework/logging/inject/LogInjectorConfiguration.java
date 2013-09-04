package org.springframework.logging.inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.logging.inject.LogInjector;

@Configuration
public class LogInjectorConfiguration {
	@Bean
	public LogInjector logInjector() {
		return new LogInjector();
	}
}
