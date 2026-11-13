
public class SpringBootFeatures {

    /*
     * ================================================================
     * SPRING BOOT - FUNDAMENTALS
     * FILE 03: SPRING BOOT FEATURES
     * ================================================================
     *
     * CONTENTS
     *
     * 1.  Introduction
     * 2.  Auto-Configuration
     * 3.  Starter Dependencies
     * 4.  Embedded Servers
     * 5.  Standalone Applications
     * 6.  Externalized Configuration
     * 7.  Type-Safe Configuration
     * 8.  Profiles
     * 9.  Spring Boot CLI
     * 10. Spring Initializr
     * 11. Actuator
     * 12. Logging Support
     * 13. Testing Support
     * 14. DevTools
     * 15. Application Events and Startup Callbacks
     * 16. Database Integration
     * 17. Web Development Support
     * 18. REST API Development
     * 19. Security Integration
     * 20. Caching Support
     * 21. Scheduling and Asynchronous Processing
     * 22. Messaging Integration
     * 23. Reactive Application Support
     * 24. Docker and Container Support
     * 25. AOT and Native Image Support
     * 26. Observability
     * 27. Feature Relationships
     * 28. Advantages and Trade-offs
     * 29. Interview Questions
     * 30. Practical Scenarios
     * 31. Quick Revision
     *
     *
     * ================================================================
     * 1. INTRODUCTION
     * ================================================================
     *
     * Spring Boot provides several features that simplify
     * Java application development.
     *
     * These features help developers:
     *
     * - Configure applications.
     * - Manage dependencies.
     * - Run applications.
     * - Develop REST APIs.
     * - Connect to databases.
     * - Handle application configuration.
     * - Test application functionality.
     * - Monitor production applications.
     * - Deploy applications.
     *
     * Some features are provided directly by Spring Boot.
     *
     * Others are provided through integration with the
     * Spring Framework, Spring projects and third-party libraries.
     *
     * Spring Boot makes these capabilities easier to configure
     * and use together.
     *
     *
     * ================================================================
     * 2. AUTO-CONFIGURATION
     * ================================================================
     *
     * Auto-configuration is one of the most important
     * Spring Boot features.
     *
     * It automatically configures application infrastructure
     * based on the dependencies and conditions present
     * in the application.
     *
     *
     * EXAMPLE:
     *
     * If an application includes the Spring Boot web starter,
     * Spring Boot can configure common web infrastructure.
     *
     * Depending on the application type and classpath,
     * this may include:
     *
     * - Spring MVC.
     * - HTTP message converters.
     * - Embedded servlet server configuration.
     * - Web-related infrastructure beans.
     *
     *
     * ANOTHER EXAMPLE:
     *
     * If the application includes database dependencies
     * and provides valid database configuration,
     * Spring Boot can configure a DataSource.
     *
     *
     * IMPORTANT CHARACTERISTICS:
     *
     * - Auto-configuration is condition-based.
     * - It considers available classes.
     * - It considers existing beans.
     * - It can consider application properties.
     * - It can back off when user-defined configuration
     *   makes its default unnecessary.
     *
     *
     * IMPORTANT:
     *
     * Auto-configuration does not mean that every possible
     * feature is automatically enabled.
     *
     * The required dependencies and conditions must be present.
     *
     *
     * ================================================================
     * 3. STARTER DEPENDENCIES
     * ================================================================
     *
     * Spring Boot provides starter dependencies to simplify
     * dependency management.
     *
     * A starter groups commonly required dependencies
     * for a particular application capability.
     *
     *
     * EXAMPLES:
     *
     * spring-boot-starter-web
     * spring-boot-starter-data-jpa
     * spring-boot-starter-security
     * spring-boot-starter-validation
     * spring-boot-starter-test
     * spring-boot-starter-actuator
     *
     *
     * WHY ARE STARTERS USEFUL?
     *
     * Without starters, developers may need to identify
     * and declare several related dependencies individually.
     *
     * Starters simplify this process.
     *
     *
     * IMPORTANT:
     *
     * A starter is primarily a dependency descriptor.
     *
     * It does not independently implement all the features
     * that it brings into the application.
     *
     *
     * ================================================================
     * 4. EMBEDDED SERVERS
     * ================================================================
     *
     * Spring Boot supports embedded servers for web applications.
     *
     * An embedded server is included as an application
     * dependency and can run as part of the application.
     *
     *
     * COMMON SERVLET SERVER OPTIONS:
     *
     * - Tomcat.
     * - Jetty.
     * - Undertow, where supported by the chosen dependencies
     *   and Spring Boot version.
     *
     *
     * COMMON REACTIVE SERVER:
     *
     * - Netty.
     *
     *
     * BENEFITS:
     *
     * - No separately installed server is required
     *   for common embedded-server deployments.
     * - Convenient local development.
     * - Convenient executable JAR deployment.
     * - Easier containerization.
     *
     *
     * EXAMPLE CONFIGURATION:
     *
     * server.port=8081
     *
     *
     * This configures the server port for a typical
     * Spring Boot web application.
     *
     *
     * IMPORTANT:
     *
     * A non-web Spring Boot application does not require
     * an embedded HTTP server.
     *
     *
     * ================================================================
     * 5. STANDALONE APPLICATIONS
     * ================================================================
     *
     * Spring Boot applications can commonly be packaged
     * and executed as standalone applications.
     *
     * A typical executable JAR includes:
     *
     * - Application classes.
     * - Application resources.
     * - Required dependencies.
     * - Spring Boot loader components.
     *
     *
     * EXECUTION:
     *
     * java -jar application.jar
     *
     *
     * BENEFITS:
     *
     * - Convenient execution.
     * - Simplified deployment.
     * - Consistent application packaging.
     * - Easier integration with container platforms.
     *
     *
     * IMPORTANT:
     *
     * Standalone execution does not mean that the
     * application has no external requirements.
     *
     * It may still need a database, message broker,
     * external API or other services.
     *
     *
     * ================================================================
     * 6. EXTERNALIZED CONFIGURATION
     * ================================================================
     *
     * Spring Boot supports externalized configuration.
     *
     * It allows application settings to be supplied
     * from sources outside Java source code.
     *
     *
     * COMMON CONFIGURATION SOURCES:
     *
     * - application.properties.
     * - application.yml.
     * - Environment variables.
     * - Java system properties.
     * - Command-line arguments.
     * - External configuration sources.
     *
     *
     * EXAMPLE:
     *
     * server.port=8081
     *
     *
     * DATABASE EXAMPLE:
     *
     * spring.datasource.url=jdbc:postgresql://localhost:5432/appdb
     * spring.datasource.username=appuser
     * spring.datasource.password=secret
     *
     *
     * IMPORTANT:
     *
     * Real production credentials should not be committed
     * to source control.
     *
     * Use suitable secret-management mechanisms
     * or protected environment-specific configuration.
     *
     *
     * BENEFITS:
     *
     * - Different environments can use different settings.
     * - Configuration can change without changing Java code.
     * - Deployment-specific values can be supplied externally.
     *
     *
     * ================================================================
     * 7. TYPE-SAFE CONFIGURATION
     * ================================================================
     *
     * Spring Boot supports binding external configuration
     * to Java objects using @ConfigurationProperties.
     *
     * This is useful when an application has multiple
     * related configuration properties.
     *
     *
     * EXAMPLE:
     *
     * @ConfigurationProperties(prefix = "app")
     * public class AppProperties {
     *
     *     private String name;
     *     private String version;
     *
     *     // Getters and setters
     *
     * }
     *
     *
     * CONFIGURATION:
     *
     * app.name=FilmGallery
     * app.version=1.0
     *
     *
     * Spring Boot can bind these properties to
     * the corresponding Java object.
     *
     *
     * BENEFITS:
     *
     * - Organized configuration.
     * - Type conversion.
     * - Easier validation.
     * - Better maintainability.
     *
     *
     * IMPORTANT:
     *
     * @ConfigurationProperties is especially useful
     * for groups of related settings.
     *
     *
     * ================================================================
     * 8. PROFILES
     * ================================================================
     *
     * Profiles allow applications to use different
     * configurations for different environments.
     *
     *
     * COMMON ENVIRONMENTS:
     *
     * - Development.
     * - Testing.
     * - Staging.
     * - Production.
     *
     *
     * EXAMPLE FILES:
     *
     * application.properties
     * application-dev.properties
     * application-test.properties
     * application-prod.properties
     *
     *
     * ACTIVATION:
     *
     * spring.profiles.active=dev
     *
     *
     * Alternatively, an active profile can be supplied
     * through supported external configuration mechanisms.
     *
     *
     * BENEFITS:
     *
     * - Environment-specific configuration.
     * - Different database settings.
     * - Different service endpoints.
     * - Environment-specific beans.
     *
     *
     * IMPORTANT:
     *
     * Profiles help separate configuration.
     *
     * They should not be used as a substitute for
     * proper secret management.
     *
     *
     * ================================================================
     * 9. SPRING BOOT CLI
     * ================================================================
     *
     * Spring Boot CLI is a command-line tool that can
     * help develop and run Spring applications.
     *
     * It supports a Groovy-based development model
     * and can simplify certain scripting and prototyping tasks.
     *
     *
     * USE CASES:
     *
     * - Quick application prototypes.
     * - Command-line development workflows.
     * - Running supported Spring scripts.
     *
     *
     * IMPORTANT:
     *
     * Spring Boot CLI is optional.
     *
     * Most conventional Spring Boot enterprise applications
     * are developed using Java, Maven or Gradle and an IDE.
     *
     *
     * ================================================================
     * 10. SPRING INITIALIZR
     * ================================================================
     *
     * Spring Initializr is a project-generation service
     * used to create Spring Boot project structures.
     *
     * It allows developers to select:
     *
     * - Project type.
     * - Language.
     * - Spring Boot version.
     * - Build tool.
     * - Java version.
     * - Project metadata.
     * - Dependencies.
     *
     *
     * COMMON PROJECT TYPES:
     *
     * - Maven.
     * - Gradle.
     *
     *
     * OUTPUT:
     *
     * Spring Initializr generates a project containing
     * the selected build configuration and dependencies.
     *
     *
     * BENEFITS:
     *
     * - Faster project creation.
     * - Consistent project structure.
     * - Easier dependency selection.
     *
     *
     * ================================================================
     * 11. SPRING BOOT ACTUATOR
     * ================================================================
     *
     * Spring Boot Actuator provides production-oriented
     * monitoring and management capabilities.
     *
     * It exposes endpoints that provide information
     * about the running application.
     *
     *
     * COMMON ENDPOINTS:
     *
     * /actuator/health
     * /actuator/info
     * /actuator/metrics
     * /actuator/env
     * /actuator/beans
     *
     *
     * ENDPOINT PURPOSES:
     *
     *
     * HEALTH:
     *
     * Provides health information about the application
     * and configured health indicators.
     *
     *
     * INFO:
     *
     * Provides application information when configured.
     *
     *
     * METRICS:
     *
     * Exposes application metrics.
     *
     *
     * ENV:
     *
     * Provides environment and property information,
     * subject to exposure and sanitization rules.
     *
     *
     * BEANS:
     *
     * Provides information about registered Spring beans.
     *
     *
     * IMPORTANT:
     *
     * Not every endpoint is exposed over HTTP by default.
     *
     * Exposure and security must be configured carefully.
     *
     * Sensitive endpoints should not be publicly accessible
     * without appropriate protection.
     *
     *
     * ================================================================
     * 12. LOGGING SUPPORT
     * ================================================================
     *
     * Logging is essential for understanding application
     * behavior and diagnosing problems.
     *
     * Spring Boot provides convenient logging integration.
     *
     * Its standard logging facade is commonly SLF4J,
     * with Logback as the default logging implementation
     * in a typical starter-based setup.
     *
     *
     * COMMON LOG LEVELS:
     *
     * TRACE
     * DEBUG
     * INFO
     * WARN
     * ERROR
     *
     *
     * EXAMPLE:
     *
     * logging.level.root=INFO
     * logging.level.com.example=DEBUG
     *
     *
     * BENEFITS:
     *
     * - Application diagnostics.
     * - Error investigation.
     * - Runtime visibility.
     * - Configurable logging levels.
     *
     *
     * IMPORTANT:
     *
     * Avoid logging passwords, access tokens,
     * sensitive personal data or confidential information.
     *
     *
     * ================================================================
     * 13. TESTING SUPPORT
     * ================================================================
     *
     * Spring Boot integrates with common Java testing tools.
     *
     * Common testing technologies include:
     *
     * - JUnit Jupiter.
     * - Mockito.
     * - Spring Test.
     * - MockMvc.
     * - WebTestClient.
     * - Testcontainers.
     *
     *
     * COMMON TESTING APPROACHES:
     *
     *
     * UNIT TESTING:
     *
     * Tests a small unit of application functionality
     * independently.
     *
     *
     * INTEGRATION TESTING:
     *
     * Tests interactions between multiple application
     * components or infrastructure.
     *
     *
     * WEB LAYER TESTING:
     *
     * Tests web request handling and responses.
     *
     *
     * FULL APPLICATION TESTING:
     *
     * Tests application behavior with a Spring
     * ApplicationContext.
     *
     *
     * IMPORTANT:
     *
     * Testing support does not mean every test must start
     * the entire Spring application.
     *
     * Choose the test scope according to what must be verified.
     *
     *
     * ================================================================
     * 14. DEVTOOLS
     * ================================================================
     *
     * Spring Boot DevTools provides development-time
     * productivity features.
     *
     * Common capabilities include:
     *
     * - Automatic application restart.
     * - Development-friendly configuration.
     * - Optional LiveReload support.
     *
     *
     * WHY IS IT USEFUL?
     *
     * During development, developers frequently modify
     * source files and restart applications.
     *
     * DevTools can reduce this repetitive effort.
     *
     *
     * IMPORTANT:
     *
     * DevTools is intended for development.
     *
     * It should not be treated as a production feature.
     *
     *
     * ================================================================
     * 15. APPLICATION EVENTS AND STARTUP CALLBACKS
     * ================================================================
     *
     * Spring Boot integrates with Spring's application
     * event mechanism.
     *
     * Applications can publish and listen to events.
     *
     * It also provides startup callback interfaces.
     *
     *
     * IMPORTANT STARTUP CALLBACKS:
     *
     * ApplicationRunner
     *
     * CommandLineRunner
     *
     *
     * APPLICATIONRUNNER:
     *
     * Receives command-line arguments through
     * an ApplicationArguments object.
     *
     *
     * COMMANDLINERUNNER:
     *
     * Receives command-line arguments as a String array.
     *
     *
     * COMMON USE CASES:
     *
     * - Initial application setup.
     * - Loading reference data.
     * - Validating startup configuration.
     * - Executing startup tasks.
     *
     *
     * IMPORTANT:
     *
     * Startup callbacks should be used carefully.
     *
     * Long-running startup operations can delay
     * application readiness.
     *
     *
     * ================================================================
     * 16. DATABASE INTEGRATION
     * ================================================================
     *
     * Spring Boot simplifies integration with databases.
     *
     * Common technologies include:
     *
     * - JDBC.
     * - Spring Data JPA.
     * - Hibernate.
     * - Redis.
     * - MongoDB.
     *
     *
     * FEATURES AND INTEGRATIONS:
     *
     * - DataSource configuration.
     * - Database driver integration.
     * - Connection pooling.
     * - JPA provider integration.
     * - Repository configuration.
     * - Transaction management integration.
     *
     *
     * EXAMPLE:
     *
     * Adding the Spring Boot Data JPA starter and
     * configuring a compatible database allows Spring Boot
     * to configure much of the common JPA infrastructure.
     *
     *
     * IMPORTANT:
     *
     * Spring Boot does not replace JDBC, JPA or Hibernate.
     *
     * It simplifies their integration into an application.
     *
     *
     * ================================================================
     * 17. WEB DEVELOPMENT SUPPORT
     * ================================================================
     *
     * Spring Boot supports web application development
     * through its integration with Spring MVC and WebFlux.
     *
     *
     * SERVLET-BASED WEB DEVELOPMENT:
     *
     * Commonly uses:
     *
     * - Spring MVC.
     * - Servlet API.
     * - Embedded servlet server.
     * - HTTP message converters.
     *
     *
     * REACTIVE WEB DEVELOPMENT:
     *
     * Commonly uses:
     *
     * - Spring WebFlux.
     * - Reactive Streams.
     * - Project Reactor.
     *
     *
     * Spring Boot simplifies the setup of these web stacks.
     *
     *
     * ================================================================
     * 18. REST API DEVELOPMENT
     * ================================================================
     *
     * Spring Boot is widely used for building REST APIs.
     *
     * It integrates with Spring MVC to provide:
     *
     * - Request mapping.
     * - HTTP method handling.
     * - Request body processing.
     * - Response body conversion.
     * - JSON serialization.
     * - Exception handling.
     * - Validation integration.
     * - CORS configuration.
     *
     *
     * EXAMPLE:
     *
     * @RestController
     * @RequestMapping("/api/users")
     * public class UserController {
     *
     *     @GetMapping("/{id}")
     *     public User getUser(@PathVariable Long id) {
     *
     *         return userService.getUser(id);
     *
     *     }
     *
     * }
     *
     *
     * Spring MVC handles the request-mapping and
     * request-processing functionality.
     *
     * Spring Boot simplifies configuring and running
     * the required infrastructure.
     *
     *
     * ================================================================
     * 19. SECURITY INTEGRATION
     * ================================================================
     *
     * Spring Boot integrates with Spring Security.
     *
     * By adding the appropriate security dependency,
     * developers can configure application security.
     *
     *
     * SECURITY CAPABILITIES INCLUDE:
     *
     * - Authentication.
     * - Authorization.
     * - Password encoding.
     * - Request protection.
     * - Method-level security.
     * - OAuth 2.0 integration.
     * - Resource server support.
     *
     *
     * IMPORTANT:
     *
     * Spring Boot does not replace Spring Security.
     *
     * Spring Security provides the security infrastructure.
     *
     * Spring Boot simplifies its dependency management
     * and application integration.
     *
     *
     * ================================================================
     * 20. CACHING SUPPORT
     * ================================================================
     *
     * Caching stores the results of selected operations
     * so that repeated requests can avoid unnecessary work.
     *
     * Spring Boot integrates with Spring's caching abstraction.
     *
     * Common cache providers include:
     *
     * - ConcurrentMap-based caching.
     * - Caffeine.
     * - Redis.
     * - Other supported cache implementations.
     *
     *
     * COMMON ANNOTATIONS:
     *
     * @EnableCaching
     * @Cacheable
     * @CachePut
     * @CacheEvict
     *
     *
     * BENEFITS:
     *
     * - Reduced repeated computation.
     * - Reduced database access in suitable cases.
     * - Improved response time.
     *
     *
     * IMPORTANT:
     *
     * Caching requires correct cache design.
     *
     * Developers must consider expiration, invalidation,
     * consistency and memory usage.
     *
     *
     * ================================================================
     * 21. SCHEDULING AND ASYNCHRONOUS PROCESSING
     * ================================================================
     *
     * Spring Boot integrates with Spring's task execution
     * and scheduling infrastructure.
     *
     *
     * SCHEDULING:
     *
     * Allows methods to execute at configured times
     * or intervals.
     *
     * Common annotation:
     *
     * @Scheduled
     *
     *
     * ASYNCHRONOUS PROCESSING:
     *
     * Allows selected method execution to happen
     * asynchronously.
     *
     * Common annotation:
     *
     * @Async
     *
     *
     * COMMON USE CASES:
     *
     * - Sending notifications.
     * - Scheduled reports.
     * - Background processing.
     * - Periodic cleanup.
     *
     *
     * IMPORTANT:
     *
     * Scheduling and asynchronous processing require
     * suitable configuration.
     *
     * Thread pools, task execution, exception handling
     * and shutdown behavior must be considered.
     *
     *
     * ================================================================
     * 22. MESSAGING INTEGRATION
     * ================================================================
     *
     * Spring Boot integrates with messaging technologies.
     *
     * Examples include:
     *
     * - Apache Kafka.
     * - RabbitMQ.
     * - JMS providers.
     *
     *
     * MESSAGING IS USEFUL FOR:
     *
     * - Asynchronous communication.
     * - Event-driven architecture.
     * - Background processing.
     * - Decoupling application services.
     * - Communication between distributed services.
     *
     *
     * IMPORTANT:
     *
     * Spring Boot provides integration support.
     *
     * The messaging broker is a separate infrastructure
     * component in common deployment architectures.
     *
     *
     * ================================================================
     * 23. REACTIVE APPLICATION SUPPORT
     * ================================================================
     *
     * Spring Boot supports reactive application development
     * through Spring WebFlux.
     *
     * Reactive applications use non-blocking processing
     * and reactive data streams.
     *
     *
     * COMMON TECHNOLOGIES:
     *
     * - Spring WebFlux.
     * - Project Reactor.
     * - Reactive Streams.
     * - Netty.
     *
     *
     * COMMON REACTIVE TYPES:
     *
     * Mono<T>
     *
     * Represents a reactive sequence that produces
     * zero or one item.
     *
     *
     * Flux<T>
     *
     * Represents a reactive sequence that can produce
     * zero to many items.
     *
     *
     * IMPORTANT:
     *
     * Reactive programming requires an appropriate
     * end-to-end design.
     *
     * Blocking operations can reduce its benefits.
     *
     *
     * ================================================================
     * 24. DOCKER AND CONTAINER SUPPORT
     * ================================================================
     *
     * Spring Boot applications can be packaged
     * and deployed using containers.
     *
     * Docker is commonly used for this purpose.
     *
     *
     * BENEFITS:
     *
     * - Consistent runtime environments.
     * - Portable application packaging.
     * - Simplified deployment.
     * - Easier integration with orchestration systems.
     *
     *
     * Spring Boot also provides build-related support
     * and integrations that can help create container images.
     *
     * The exact approach depends on the build tool
     * and project configuration.
     *
     *
     * IMPORTANT:
     *
     * Docker is not part of the Spring Boot framework.
     *
     * It is an external containerization technology.
     *
     *
     * ================================================================
     * 25. AOT AND NATIVE IMAGE SUPPORT
     * ================================================================
     *
     * Spring Boot supports ahead-of-time processing
     * and native image generation workflows.
     *
     *
     * AOT:
     *
     * Ahead-of-Time processing performs selected processing
     * during build time rather than relying entirely
     * on runtime discovery.
     *
     *
     * NATIVE IMAGE:
     *
     * A native image is a compiled executable produced
     * using a native image toolchain such as GraalVM Native Image.
     *
     *
     * POTENTIAL BENEFITS:
     *
     * - Faster startup.
     * - Reduced memory usage in suitable workloads.
     * - Convenient deployment for certain environments.
     *
     *
     * TRADE-OFFS:
     *
     * - Build complexity.
     * - Compatibility constraints.
     * - Reflection and resource configuration requirements.
     * - Different runtime characteristics.
     *
     *
     * IMPORTANT:
     *
     * Native image generation is not the default
     * execution model for every Spring Boot application.
     *
     *
     * ================================================================
     * 26. OBSERVABILITY
     * ================================================================
     *
     * Observability helps developers understand the
     * internal behavior of an application through
     * externally collected information.
     *
     * Three important pillars are:
     *
     * 1. Logs.
     * 2. Metrics.
     * 3. Traces.
     *
     *
     * LOGS:
     *
     * Record application events and diagnostic information.
     *
     *
     * METRICS:
     *
     * Provide numerical measurements such as:
     *
     * - Request count.
     * - Response time.
     * - Error rate.
     * - Memory usage.
     *
     *
     * TRACES:
     *
     * Help follow a request as it travels through
     * multiple services.
     *
     *
     * IMPORTANT TECHNOLOGIES:
     *
     * - Spring Boot Actuator.
     * - Micrometer.
     * - Micrometer Tracing.
     * - OpenTelemetry integrations.
     *
     *
     * IMPORTANT:
     *
     * Monitoring and observability are related,
     * but observability provides broader insight into
     * system behavior.
     *
     *
     * ================================================================
     * 27. FEATURE RELATIONSHIPS
     * ================================================================
     *
     * Spring Boot features work together.
     *
     * Consider developing a REST API with database access.
     *
     *
     * 1. SPRING INITIALIZR
     *
     *    Generates the project structure.
     *
     *
     * 2. STARTER DEPENDENCIES
     *
     *    Add the required web and database libraries.
     *
     *
     * 3. AUTO-CONFIGURATION
     *
     *    Configures suitable application infrastructure.
     *
     *
     * 4. EMBEDDED SERVER
     *
     *    Runs the web application.
     *
     *
     * 5. SPRING MVC
     *
     *    Handles HTTP request processing.
     *
     *
     * 6. SPRING IoC CONTAINER
     *
     *    Manages application components.
     *
     *
     * 7. DATABASE INTEGRATION
     *
     *    Provides database connectivity and data access.
     *
     *
     * 8. EXTERNALIZED CONFIGURATION
     *
     *    Supplies environment-specific settings.
     *
     *
     * 9. ACTUATOR
     *
     *    Exposes configured monitoring and management endpoints.
     *
     *
     * 10. LOGGING AND OBSERVABILITY
     *
     *     Help diagnose and monitor application behavior.
     *
     *
     * ================================================================
     * 28. ADVANTAGES AND TRADE-OFFS
     * ================================================================
     *
     * ADVANTAGES:
     *
     * - Faster project setup.
     * - Reduced manual configuration.
     * - Convenient dependency management.
     * - Embedded server support.
     * - Externalized configuration.
     * - Testing integration.
     * - Production monitoring support.
     * - Integration with the Spring ecosystem.
     *
     *
     * TRADE-OFFS:
     *
     * - Auto-configuration can be difficult to debug initially.
     * - Applications may have significant dependency trees.
     * - Startup and memory overhead may be higher
     *   than minimal frameworks.
     * - Dependency versions must remain compatible.
     * - Developers still need to understand Spring internals.
     *
     *
     * IMPORTANT:
     *
     * The right features should be selected according
     * to actual application requirements.
     *
     *
     * ================================================================
     * 29. INTERVIEW QUESTIONS
     * ================================================================
     *
     * BASIC:
     *
     * 1. What are the main features of Spring Boot?
     *
     * 2. What is auto-configuration?
     *
     * 3. What are starter dependencies?
     *
     * 4. What is an embedded server?
     *
     * 5. What is externalized configuration?
     *
     * 6. What are Spring Boot profiles?
     *
     * 7. What is Spring Initializr?
     *
     * 8. What is Spring Boot Actuator?
     *
     * 9. What is Spring Boot DevTools?
     *
     * 10. What is Spring Boot CLI?
     *
     *
     * INTERMEDIATE:
     *
     * 11. How does Spring Boot auto-configure a DataSource?
     *
     * 12. What is the purpose of @ConfigurationProperties?
     *
     * 13. How are profiles useful in real-world applications?
     *
     * 14. How does Spring Boot support testing?
     *
     * 15. How does Spring Boot integrate with Spring Security?
     *
     * 16. What is the difference between logging and metrics?
     *
     * 17. What are ApplicationRunner and CommandLineRunner?
     *
     * 18. What is the purpose of DevTools?
     *
     * 19. How does Spring Boot support caching?
     *
     * 20. How does Spring Boot support scheduled tasks?
     *
     *
     * ADVANCED:
     *
     * 21. How does Spring Boot decide whether to apply
     *     an auto-configuration?
     *
     * 22. What happens when a user-defined bean conflicts
     *     with an auto-configured bean?
     *
     * 23. What is the difference between servlet and
     *     reactive web applications?
     *
     * 24. What are the advantages and trade-offs of
     *     native image compilation?
     *
     * 25. How do logs, metrics and traces work together?
     *
     * 26. Why should secrets not be stored in source control?
     *
     * 27. What considerations are important when using
     *     asynchronous processing?
     *
     * 28. How can Spring Boot applications integrate
     *     with message brokers?
     *
     *
     * ================================================================
     * 30. PRACTICAL SCENARIOS
     * ================================================================
     *
     * SCENARIO 1:
     *
     * Your application must run on port 9090 instead
     * of the default port.
     *
     * QUESTION:
     *
     * Which Spring Boot feature helps?
     *
     * EXPECTED UNDERSTANDING:
     *
     * Externalized configuration.
     *
     *
     * SCENARIO 2:
     *
     * Your application uses different databases
     * in development and production.
     *
     * QUESTION:
     *
     * Which feature can help organize the configuration?
     *
     * EXPECTED UNDERSTANDING:
     *
     * Profiles and externalized configuration.
     *
     *
     * SCENARIO 3:
     *
     * You need to check whether the application is healthy.
     *
     * QUESTION:
     *
     * Which feature should you investigate?
     *
     * EXPECTED UNDERSTANDING:
     *
     * Spring Boot Actuator health endpoint.
     *
     *
     * SCENARIO 4:
     *
     * You want to avoid repeating expensive database queries.
     *
     * QUESTION:
     *
     * Which feature might help?
     *
     * EXPECTED UNDERSTANDING:
     *
     * Caching, when the data and consistency requirements
     * make caching appropriate.
     *
     *
     * SCENARIO 5:
     *
     * Your application must send notifications
     * without making the request wait for the entire operation.
     *
     * QUESTION:
     *
     * Which feature might help?
     *
     * EXPECTED UNDERSTANDING:
     *
     * Asynchronous processing, with suitable task execution
     * and error handling.
     *
     *
     * SCENARIO 6:
     *
     * Your application needs to communicate with
     * another service through messages.
     *
     * QUESTION:
     *
     * Which integration should you consider?
     *
     * EXPECTED UNDERSTANDING:
     *
     * Messaging integration such as Kafka or RabbitMQ.
     *
     *
     * ================================================================
     * 31. QUICK REVISION
     * ================================================================
     *
     * CORE FEATURES:
     *
     * - Auto-Configuration.
     * - Starter Dependencies.
     * - Embedded Servers.
     * - Standalone Execution.
     * - Externalized Configuration.
     * - Profiles.
     *
     *
     * DEVELOPMENT FEATURES:
     *
     * - Spring Initializr.
     * - Spring Boot CLI.
     * - DevTools.
     * - Testing Support.
     *
     *
     * APPLICATION FEATURES:
     *
     * - REST API Development.
     * - Database Integration.
     * - Security Integration.
     * - Caching.
     * - Scheduling.
     * - Asynchronous Processing.
     * - Messaging.
     * - Reactive Applications.
     *
     *
     * PRODUCTION FEATURES:
     *
     * - Actuator.
     * - Logging.
     * - Metrics.
     * - Distributed Tracing.
     * - Container Support.
     * - AOT and Native Image Support.
     *
     *
     * MOST IMPORTANT UNDERSTANDING:
     *
     * Spring Boot provides a convenient development and
     * runtime platform by combining auto-configuration,
     * dependency management, application bootstrapping
     * and integration with the Spring ecosystem.
     *
     *
     * ================================================================
     * END OF FILE 03
     * ================================================================
     */

    public static void main(String[] args) {

        System.out.println(
            "Spring Boot Features"
        );

    }

}
