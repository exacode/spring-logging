Spring logging
==============

Logging library to be used with Spring Framework.
Contains two mechanisms:

- Logger injection 
- Logging aspects

Logger injection
----------------

In order to inject a logger instance into a spring managed bean use `@Log` annotation.

### Example usage:

		
		import org.slf4j.Logger;
		import org.springframework.stereotype.Component;

		@Component
		public class BeanWithLogger {
			
			@Log
			private Logger logger;

			@Log(fromClass = SomeOther.class)
			private Logger logger2;
			
			/* ... */
			
		}

### Configuration

To enable logger injection with `@Log`, simply register `LogInjector` in spring context.

Example using spring-java-config:

		import org.springframework.logging.inject.LogInjector;
		import org.springframework.context.annotation.Bean;
		import org.springframework.context.annotation.Configuration;

		@Configuration
		public class LogInjectorConfiguration {
			@Bean
			public LogInjector logInjector() {
				return new LogInjector();
			}
		}

Logger aspects
--------------

In order to log method invocations with aspects use `@Logged` annotation.
You can use this annotation on methods and classes.

### Example usage:

		import org.slf4j.Logger;
		import org.springframework.stereotype.Component;

		@Component
		@Logged
		public class BeanWithAspectLogging {
	
			public void sayHello() {
				/* ... */
			}

			public void sayHello(String hello) {
				/* ... */
			}

			public String sayHelloAndThrowNullPointerException(String hello) {
				logger.info("Inside method sayHelloReturnException. Hello: {}", hello);
				throw new NullPointerException("Test exception");
			}
	
		}

### Configuration

To enable aspect logging just register `LoggedAspect` in spring context.

Example using spring-java-config:

		import org.springframework.logging.aspect.LoggedAspect;
		import org.springframework.context.annotation.Bean;
		import org.springframework.context.annotation.Configuration;
		import org.springframework.context.annotation.EnableAspectJAutoProxy;

		@Configuration
		@EnableAspectJAutoProxy(proxyTargetClass = true)
		public class LoggedAspectConfiguration {

			@Bean
			public LoggedAspect loggedAspect() {
				return new LoggedAspect();
			}

		}

### Console output

Default console output for `BeanWithAspectLogging#sayHelloAndThrowNullPointerException(String hello)`:

		2013-09-04 11:43:24,561 [TRACE] o.s.u.l.a.b.BeanTypeAnnotated:118 - >>> BeanTypeAnnotated.sayHelloReturnException(..): [xxx]
		/* Logs printed during method invocation */
		2013-09-04 11:43:24,582 [ERROR] o.s.u.l.a.b.BeanTypeAnnotated:134 - XXX Error occured: 
		/* Exception stack trace */
		2013-09-04 11:41:30,448 [TRACE] o.s.u.l.a.b.BeanTypeAnnotated:140 - <<< Returning BeanWithAspectLogging.sayHelloAndThrowNullPointerException(): null (14[ms])

*Default console output can be overriden with custom implementation of `MessageFormatter`.*
*Default logger provider mechanism can be overriden with custom implementation of `LoggerProvider`.*


Maven dependency
----------------

In order to use this library add [repository](http://github.com/exacode/mvn-repo) location into your `pom.xml` and add appropriate dependency.

		<dependency>
			<groupId>net.exacode.spring.logging</groupId>
			<artifactId>spring-aspect</artifactId>
			<version>${version.spring-logging}</version>
		</dependency>
		<!-- or -->
		<dependency>
			<groupId>net.exacode.spring.logging</groupId>
			<artifactId>spring-inject</artifactId>
			<version>${version.spring-logging}</version>
		</dependency>

<a href='http://www.pledgie.com/campaigns/22342'><img alt='Click here to lend your support to: Exacode open projects and make a donation at www.pledgie.com !' src='http://www.pledgie.com/campaigns/22342.png?skin_name=chrome' border='0' /></a>
