Spring logging
==============

Logging library to be used with Spring Framework.
Contains two mechanisms:

- Logger injection 
- Logging aspects

Logger injection
----------------

In order to inject a logger to spring managed bean use `@Log` annotation.

Example usage:

		
		import org.slf4j.Logger;
		import org.springframework.stereotype.Component;

		@Component
		public class BeanWithLogger {
			
			@Log
			private Logger logger;
			
			/* ... */
			
		}

Logger aspects
--------------

In order to log method invocations with aspects use `@Logged` accotation.
You can annotate single methods or classes.

Example usage:

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

Console output for `BeanWithAspectLogging#sayHelloAndThrowNullPointerException(String hello)`:

		2013-09-04 11:43:24,561 [TRACE] o.s.u.l.a.b.BeanTypeAnnotated:118 - >>> BeanTypeAnnotated.sayHelloReturnException(..): [xxx]
		/* Logs printed during method invocation */
		2013-09-04 11:43:24,582 [ERROR] o.s.u.l.a.b.BeanTypeAnnotated:134 - XXX Error occured: 
		/* Exception stack trace */
		2013-09-04 11:41:30,448 [TRACE] o.s.u.l.a.b.BeanTypeAnnotated:140 - <<< Returning BeanWithAspectLogging.sayHelloAndThrowNullPointerException(): null (14[ms])

Maven repository
----------------

In order to use this library in your project add (repository)[http://github.com/mendlik/mvn-repo] location in your `pom.xml`:

		<repositories>
		    <repository>
			<id>mendlik-releases</id>
			<url>https://github.com/mendlik/mvn-repo/raw/master/releases</url>
		    </repository>
		</repositories>
