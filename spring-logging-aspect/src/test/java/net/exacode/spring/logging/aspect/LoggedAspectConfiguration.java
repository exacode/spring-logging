package net.exacode.spring.logging.aspect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackageClasses = LoggedAspectConfiguration.class)
public class LoggedAspectConfiguration {

	@Bean
	public TestMessageFormatter messageFormatter() {
		return new TestMessageFormatter();
	}

	@Bean
	public LoggedAspect loggedAspect() {
		return new LoggedAspect(messageFormatter());
	}

}
