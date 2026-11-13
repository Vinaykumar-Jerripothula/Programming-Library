
public class SpringAdvancedContainerFeatures {

    /*
     * ================================================================
     * SPRING CORE - 06: SPRING ADVANCED CONTAINER FEATURES
     * ================================================================
     *
     * CONTENTS
     *
     * 1.  Introduction to Advanced Container Features
     * 2.  BeanFactoryPostProcessor
     * 3.  BeanDefinitionRegistryPostProcessor
     * 4.  BeanPostProcessor
     * 5.  FactoryBean
     * 6.  ApplicationContext Events
     * 7.  Built-in Spring Events
     * 8.  Custom Application Events
     * 9.  Event Publishing
     * 10. Event Listening
     * 11. Synchronous and Asynchronous Events
     * 12. Event Ordering
     * 13. ApplicationContext and Environment
     * 14. PropertySources
     * 15. Property Resolution
     * 16. Profiles and Profile Expressions
     * 17. Resource Abstraction
     * 18. Resource Implementations
     * 19. Resource Loading
     * 20. ResourceLoaderAware
     * 21. Internationalization
     * 22. MessageSource
     * 23. ApplicationContext Hierarchy
     * 24. Parent and Child Contexts
     * 25. ApplicationContextInitializer
     * 26. Context Refresh and Lifecycle
     * 27. Shutdown Hooks
     * 28. Spring Container Extension Points
     * 29. Common Mistakes
     * 30. Real-World Scenarios
     * 31. Interview Questions
     * 32. Practice Exercises
     * 33. Quick Revision
     *
     *
     * ================================================================
     * 1. INTRODUCTION TO ADVANCED CONTAINER FEATURES
     * ================================================================
     *
     * The Spring IoC container does more than create beans and
     * inject dependencies.
     *
     * It provides additional infrastructure for managing an
     * application's configuration, resources, events and
     * bean creation process.
     *
     * Important advanced features include:
     *
     * - BeanFactoryPostProcessor
     * - BeanDefinitionRegistryPostProcessor
     * - BeanPostProcessor
     * - FactoryBean
     * - Application events
     * - Environment abstraction
     * - Property sources
     * - Resource abstraction
     * - Internationalization
     * - ApplicationContext hierarchy
     * - ApplicationContextInitializer
     *
     * These features are especially useful when building
     * enterprise applications, reusable libraries and
     * framework-level infrastructure.
     *
     *
     * ================================================================
     * 2. BEANFACTORYPOSTPROCESSOR
     * ================================================================
     *
     * BeanFactoryPostProcessor is a Spring extension interface
     * that allows modification of bean definitions before
     * regular application beans are instantiated.
     *
     * It works with bean definition metadata rather than
     * ordinary bean instances.
     *
     * INTERFACE:
     *
     * public interface BeanFactoryPostProcessor {
     *
     *     void postProcessBeanFactory(
     *         ConfigurableListableBeanFactory beanFactory
     *     );
     * }
     *
     * IMPORTANT:
     *
     * A BeanDefinition describes how a bean should be created.
     *
     * A BeanFactoryPostProcessor can inspect or modify that
     * definition before the corresponding bean is instantiated.
     *
     *
     * EXAMPLE:
     *
     * public class CustomBeanFactoryPostProcessor
     *         implements BeanFactoryPostProcessor {
     *
     *     @Override
     *     public void postProcessBeanFactory(
     *             ConfigurableListableBeanFactory beanFactory) {
     *
     *         System.out.println(
     *             "Processing bean definitions"
     *         );
     *     }
     * }
     *
     * Registering it:
     *
     * @Configuration
     * public class AppConfig {
     *
     *     @Bean
     *     public static CustomBeanFactoryPostProcessor
     *             customBeanFactoryPostProcessor() {
     *
     *         return new CustomBeanFactoryPostProcessor();
     *     }
     * }
     *
     * The static declaration is recommended for @Bean methods
     * that declare BeanFactoryPostProcessor infrastructure,
     * because it allows Spring to create the processor without
     * prematurely instantiating its configuration class.
     *
     *
     * COMMON USE CASES:
     *
     * - Modifying bean definition metadata.
     * - Replacing property values in definitions.
     * - Applying configuration changes before bean creation.
     * - Implementing custom configuration infrastructure.
     *
     * IMPORTANT DISTINCTION:
     *
     * BeanFactoryPostProcessor works with bean definitions.
     *
     * BeanPostProcessor works with bean instances.
     *
     *
     * ================================================================
     * 3. BEANDEFINITIONREGISTRYPOSTPROCESSOR
     * ================================================================
     *
     * BeanDefinitionRegistryPostProcessor extends
     * BeanFactoryPostProcessor.
     *
     * It provides an additional callback that allows registration
     * of new bean definitions before regular BeanFactoryPostProcessor
     * processing is completed.
     *
     * INTERFACE:
     *
     * public interface BeanDefinitionRegistryPostProcessor
     *         extends BeanFactoryPostProcessor {
     *
     *     void postProcessBeanDefinitionRegistry(
     *         BeanDefinitionRegistry registry
     *     );
     * }
     *
     * It has two important processing stages:
     *
     * 1. postProcessBeanDefinitionRegistry()
     * 2. postProcessBeanFactory()
     *
     *
     * EXAMPLE:
     *
     * public class CustomRegistryProcessor
     *         implements BeanDefinitionRegistryPostProcessor {
     *
     *     @Override
     *     public void postProcessBeanDefinitionRegistry(
     *             BeanDefinitionRegistry registry) {
     *
     *         System.out.println(
     *             "Registering additional bean definitions"
     *         );
     *     }
     *
     *     @Override
     *     public void postProcessBeanFactory(
     *             ConfigurableListableBeanFactory beanFactory) {
     *
     *         System.out.println(
     *             "Processing bean factory"
     *         );
     *     }
     * }
     *
     *
     * DIFFERENCE:
     *
     * BeanFactoryPostProcessor:
     * Modifies existing bean definition metadata.
     *
     * BeanDefinitionRegistryPostProcessor:
     * Can register additional bean definitions and also
     * participates in bean factory post-processing.
     *
     * USE CASES:
     *
     * - Dynamic bean registration.
     * - Framework infrastructure.
     * - Custom component registration.
     * - Advanced configuration processing.
     *
     *
     * ================================================================
     * 4. BEANPOSTPROCESSOR
     * ================================================================
     *
     * BeanPostProcessor is an extension point that allows
     * processing of bean instances before and after initialization.
     *
     * INTERFACE:
     *
     * public interface BeanPostProcessor {
     *
     *     Object postProcessBeforeInitialization(
     *         Object bean,
     *         String beanName
     *     );
     *
     *     Object postProcessAfterInitialization(
     *         Object bean,
     *         String beanName
     *     );
     * }
     *
     *
     * A. BEFORE INITIALIZATION
     *
     * postProcessBeforeInitialization() is invoked before
     * initialization callbacks such as @PostConstruct.
     *
     *
     * B. AFTER INITIALIZATION
     *
     * postProcessAfterInitialization() is invoked after
     * initialization callbacks.
     *
     *
     * EXAMPLE:
     *
     * public class LoggingBeanPostProcessor
     *         implements BeanPostProcessor {
     *
     *     @Override
     *     public Object postProcessBeforeInitialization(
     *             Object bean,
     *             String beanName) {
     *
     *         System.out.println(
     *             "Before initialization: " + beanName
     *         );
     *
     *         return bean;
     *     }
     *
     *     @Override
     *     public Object postProcessAfterInitialization(
     *             Object bean,
     *             String beanName) {
     *
     *         System.out.println(
     *             "After initialization: " + beanName
     *         );
     *
     *         return bean;
     *     }
     * }
     *
     *
     * REGISTRATION:
     *
     * @Configuration
     * public class AppConfig {
     *
     *     @Bean
     *     public static LoggingBeanPostProcessor
     *             loggingBeanPostProcessor() {
     *
     *         return new LoggingBeanPostProcessor();
     *     }
     * }
     *
     *
     * IMPORTANT:
     *
     * A BeanPostProcessor can return:
     *
     * - The original bean.
     * - A modified object.
     * - A proxy wrapping the original bean.
     *
     * Spring AOP uses bean post-processing infrastructure
     * to create proxies for eligible beans.
     *
     *
     * ================================================================
     * 5. FACTORYBEAN
     * ================================================================
     *
     * FactoryBean<T> is a special Spring interface used when
     * an object needs to be created through a custom factory.
     *
     * It is useful when object creation is more complicated
     * than simply calling a constructor.
     *
     * INTERFACE:
     *
     * public interface FactoryBean<T> {
     *
     *     T getObject() throws Exception;
     *
     *     Class<?> getObjectType();
     *
     *     default boolean isSingleton() {
     *         return true;
     *     }
     * }
     *
     *
     * EXAMPLE:
     *
     * public class ConnectionFactoryBean
     *         implements FactoryBean<DatabaseConnection> {
     *
     *     @Override
     *     public DatabaseConnection getObject() {
     *         return new DatabaseConnection();
     *     }
     *
     *     @Override
     *     public Class<?> getObjectType() {
     *         return DatabaseConnection.class;
     *     }
     *
     *     @Override
     *     public boolean isSingleton() {
     *         return true;
     *     }
     * }
     *
     *
     * CONFIGURATION:
     *
     * @Configuration
     * public class AppConfig {
     *
     *     @Bean
     *     public ConnectionFactoryBean connectionFactoryBean() {
     *         return new ConnectionFactoryBean();
     *     }
     * }
     *
     *
     * IMPORTANT BEHAVIOR:
     *
     * When requesting the bean by its normal name:
     *
     * context.getBean("connectionFactoryBean");
     *
     * Spring returns the object produced by the FactoryBean.
     *
     * To obtain the FactoryBean itself:
     *
     * context.getBean("&connectionFactoryBean");
     *
     * The ampersand prefix requests the factory rather than
     * its produced object.
     *
     *
     * DIFFERENCE:
     *
     * @Bean:
     * A method-based mechanism for declaring a bean.
     *
     * FactoryBean:
     * A Spring interface for implementing custom object creation.
     *
     *
     * USE CASES:
     *
     * - Creating complex objects.
     * - Integrating third-party libraries.
     * - Creating framework infrastructure objects.
     * - Producing objects that require special initialization.
     *
     *
     * ================================================================
     * 6. APPLICATIONCONTEXT EVENTS
     * ================================================================
     *
     * Spring provides an event mechanism that allows components
     * to communicate through events.
     *
     * Instead of one component directly calling another,
     * a component can publish an event describing something
     * that has happened.
     *
     * Other components can listen for that event.
     *
     *
     * EXAMPLE:
     *
     * An order has been placed.
     *
     * OrderService publishes an OrderPlacedEvent.
     *
     * Other components listen for it:
     *
     * - EmailService sends a confirmation email.
     * - InventoryService updates stock.
     * - AnalyticsService records the order.
     *
     *
     * GENERAL FLOW:
     *
     *       OrderService
     *            |
     *            v
     *      Publish Event
     *            |
     *            v
     *    ApplicationEventPublisher
     *            |
     *            v
     *      ApplicationContext
     *            |
     *            v
     *       Event Listeners
     *            |
     *       +----+----+
     *       |    |    |
     *       v    v    v
     *     Email Stock Analytics
     *
     *
     * BENEFITS:
     *
     * - Loose coupling between components.
     * - Easier extension of application behavior.
     * - Separation of responsibilities.
     * - Multiple listeners can react to one event.
     *
     * IMPORTANT:
     *
     * Spring application events are generally an in-process
     * communication mechanism.
     *
     * They are not automatically a distributed messaging system.
     *
     *
     * ================================================================
     * 7. BUILT-IN SPRING EVENTS
     * ================================================================
     *
     * Spring provides several built-in application events.
     *
     *
     * A. ContextRefreshedEvent
     *
     * Published when an ApplicationContext is initialized
     * or refreshed.
     *
     *
     * B. ContextStartedEvent
     *
     * Published when the context receives a start signal.
     *
     *
     * C. ContextStoppedEvent
     *
     * Published when the context receives a stop signal.
     *
     *
     * D. ContextClosedEvent
     *
     * Published when the ApplicationContext is closed.
     *
     *
     * E. RequestHandledEvent
     *
     * Represents completion of an HTTP request handled by
     * Spring's web request infrastructure.
     *
     * Its availability depends on the web application setup.
     *
     *
     * IMPORTANT:
     *
     * These events have specific lifecycle meanings.
     * Context start and stop events are not the same as
     * refreshing or closing the context.
     *
     *
     * ================================================================
     * 8. CUSTOM APPLICATION EVENTS
     * ================================================================
     *
     * Applications can define their own events.
     *
     * A custom event represents something meaningful that
     * happened inside the application.
     *
     * Examples:
     *
     * - UserRegisteredEvent
     * - OrderPlacedEvent
     * - PaymentCompletedEvent
     * - PasswordChangedEvent
     *
     *
     * MODERN SPRING APPROACH:
     *
     * A custom event can be represented by a normal Java class
     * or record.
     *
     * Example:
     *
     * public record UserRegisteredEvent(
     *         String email,
     *         String username
     * ) {
     * }
     *
     * The event does not need to extend ApplicationEvent.
     *
     * Older Spring examples often extend ApplicationEvent,
     * but modern Spring supports arbitrary event objects.
     *
     *
     * ================================================================
     * 9. EVENT PUBLISHING
     * ================================================================
     *
     * ApplicationEventPublisher is used to publish events.
     *
     * Example:
     *
     * @Service
     * public class UserService {
     *
     *     private final ApplicationEventPublisher publisher;
     *
     *     public UserService(ApplicationEventPublisher publisher) {
     *         this.publisher = publisher;
     *     }
     *
     *     public void registerUser(
     *             String email,
     *             String username) {
     *
     *         // Save the user first.
     *
     *         UserRegisteredEvent event =
     *                 new UserRegisteredEvent(email, username);
     *
     *         publisher.publishEvent(event);
     *     }
     * }
     *
     * Spring delivers the published event to matching listeners.
     *
     *
     * ================================================================
     * 10. EVENT LISTENING
     * ================================================================
     *
     * A component can listen for events using @EventListener.
     *
     * Example:
     *
     * @Component
     * public class EmailNotificationListener {
     *
     *     @EventListener
     *     public void handleUserRegistered(
     *             UserRegisteredEvent event) {
     *
     *         System.out.println(
     *             "Sending email to: " + event.email()
     *         );
     *     }
     * }
     *
     * Spring identifies the listener method and invokes it
     * when a matching event is published.
     *
     * A listener can also use ApplicationListener.
     *
     * Example:
     *
     * @Component
     * public class UserRegisteredListener
     *         implements ApplicationListener<UserRegisteredEvent> {
     *
     *     @Override
     *     public void onApplicationEvent(
     *             UserRegisteredEvent event) {
     *
     *         System.out.println(event.username());
     *     }
     * }
     *
     * @EventListener is commonly preferred for simple listener
     * methods because it requires less boilerplate.
     *
     *
     * ================================================================
     * 11. SYNCHRONOUS AND ASYNCHRONOUS EVENTS
     * ================================================================
     *
     * By default, Spring application event listeners are
     * generally invoked synchronously by the event multicaster.
     *
     * This means the publisher thread performs listener execution.
     *
     *
     * SYNCHRONOUS FLOW:
     *
     * Publisher
     *     |
     *     v
     * Publish Event
     *     |
     *     v
     * Listener 1
     *     |
     *     v
     * Listener 2
     *     |
     *     v
     * Return to Publisher
     *
     * The publisher normally waits for the listeners to finish.
     *
     *
     * ASYNCHRONOUS LISTENING:
     *
     * Spring can execute listener methods asynchronously
     * when asynchronous event processing is configured.
     *
     * Example:
     *
     * @Configuration
     * @EnableAsync
     * public class AsyncConfig {
     * }
     *
     * @Component
     * public class EmailListener {
     *
     *     @Async
     *     @EventListener
     *     public void sendEmail(UserRegisteredEvent event) {
     *
     *         System.out.println("Sending email asynchronously");
     *     }
     * }
     *
     * The application must configure asynchronous execution
     * and provide an appropriate executor when required.
     *
     *
     * SYNCHRONOUS EVENTS:
     *
     * Advantages:
     * - Simple execution model.
     * - Listener completion can be observed by the publisher.
     * - Easier to reason about ordering.
     *
     * Disadvantages:
     * - Slow listeners delay the publisher.
     * - Listener exceptions can affect event publication.
     *
     *
     * ASYNCHRONOUS EVENTS:
     *
     * Advantages:
     * - Can reduce publisher-thread waiting.
     * - Suitable for independent background work.
     *
     * Disadvantages:
     * - Execution order and completion are not guaranteed
     *   in the same way as synchronous execution.
     * - Exceptions need separate handling.
     * - Thread management becomes important.
     *
     * IMPORTANT:
     *
     * Asynchronous event handling does not automatically provide
     * durable delivery or guaranteed execution after application
     * crashes.
     *
     * For reliable distributed messaging, consider a message broker.
     *
     *
     * ================================================================
     * 12. EVENT ORDERING
     * ================================================================
     *
     * When multiple listeners handle the same event,
     * execution order can be controlled using @Order.
     *
     * Example:
     *
     * @Component
     * public class FirstListener {
     *
     *     @EventListener
     *     @Order(1)
     *     public void handle(UserRegisteredEvent event) {
     *         System.out.println("First listener");
     *     }
     * }
     *
     * @Component
     * public class SecondListener {
     *
     *     @EventListener
     *     @Order(2)
     *     public void handle(UserRegisteredEvent event) {
     *         System.out.println("Second listener");
     *     }
     * }
     *
     * For synchronous listener execution, ordering metadata
     * can determine invocation order.
     *
     * With asynchronous listeners, completion order should not
     * be assumed.
     *
     *
     * ================================================================
     * 13. APPLICATIONCONTEXT AND ENVIRONMENT
     * ================================================================
     *
     * Environment is an abstraction that provides access to
     * properties and active profiles.
     *
     * ApplicationContext exposes the Environment through:
     *
     * getEnvironment()
     *
     * Example:
     *
     * @Component
     * public class ApplicationInfo {
     *
     *     private final Environment environment;
     *
     *     public ApplicationInfo(Environment environment) {
     *         this.environment = environment;
     *     }
     *
     *     public void printInformation() {
     *
     *         String name = environment.getProperty(
     *             "application.name"
     *         );
     *
     *         String[] profiles =
     *                 environment.getActiveProfiles();
     *
     *         System.out.println(name);
     *         System.out.println(Arrays.toString(profiles));
     *     }
     * }
     *
     *
     * IMPORTANT ENVIRONMENT METHODS:
     *
     * - getProperty()
     * - getRequiredProperty()
     * - containsProperty()
     * - getActiveProfiles()
     * - getDefaultProfiles()
     * - acceptsProfiles()
     *
     *
     * ================================================================
     * 14. PROPERTYSOURCES
     * ================================================================
     *
     * Environment can contain multiple property sources.
     *
     * A PropertySource represents a source from which
     * configuration properties can be read.
     *
     * Examples:
     *
     * - JVM system properties.
     * - Operating system environment variables.
     * - Properties files.
     * - Command-line arguments.
     * - Custom property sources.
     *
     * Different sources may contain the same property name.
     *
     * Spring resolves the value according to the configured
     * property source precedence.
     *
     * Example:
     *
     * Environment environment = context.getEnvironment();
     *
     * String port = environment.getProperty("server.port");
     *
     *
     * IMPORTANT:
     *
     * Property precedence is determined by the Environment
     * and the property sources registered with it.
     *
     * Do not assume every application uses exactly the same
     * property source order.
     *
     *
     * ================================================================
     * 15. PROPERTY RESOLUTION
     * ================================================================
     *
     * Spring supports resolving external configuration values.
     *
     * Example:
     *
     * application.properties:
     *
     * app.name=Film Gallery
     * app.timeout=30
     *
     * Java:
     *
     * @Value("${app.name}")
     * private String applicationName;
     *
     * @Value("${app.timeout}")
     * private int timeout;
     *
     * The placeholder is resolved using the configured
     * property sources.
     *
     *
     * DEFAULT VALUE:
     *
     * @Value("${app.timeout:60}")
     * private int timeout;
     *
     * If the property is unavailable, 60 is used.
     *
     *
     * ENVIRONMENT ACCESS:
     *
     * String name = environment.getProperty("app.name");
     *
     * REQUIRED PROPERTY:
     *
     * String name =
     *         environment.getRequiredProperty("app.name");
     *
     * The required form throws an exception if the property
     * cannot be resolved.
     *
     *
     * ================================================================
     * 16. PROFILES AND PROFILE EXPRESSIONS
     * ================================================================
     *
     * Profiles allow different configurations to be activated
     * in different environments.
     *
     * Common profiles:
     *
     * - dev
     * - test
     * - staging
     * - prod
     *
     * Example:
     *
     * @Configuration
     * @Profile("dev")
     * public class DevelopmentConfig {
     *
     *     @Bean
     *     public PaymentGateway paymentGateway() {
     *         return new MockPaymentGateway();
     *     }
     * }
     *
     *
     * PROFILE EXPRESSIONS:
     *
     * Spring supports logical profile expressions.
     *
     * AND:
     *
     * @Profile("dev & cloud")
     *
     * OR:
     *
     * @Profile("dev | test")
     *
     * NOT:
     *
     * @Profile("!prod")
     *
     * Expressions can combine profile names using logical operators.
     *
     *
     * ACTIVATION:
     *
     * In Spring Boot:
     *
     * spring.profiles.active=dev
     *
     * Profiles can also be activated through environment
     * variables, system properties and programmatic configuration.
     *
     * IMPORTANT:
     *
     * Spring Core provides profile support.
     *
     * Spring Boot adds convenient external configuration
     * mechanisms for activating and configuring profiles.
     *
     *
     * ================================================================
     * 17. RESOURCE ABSTRACTION
     * ================================================================
     *
     * Java applications often need to access external resources.
     *
     * Examples:
     *
     * - Files
     * - Classpath resources
     * - URLs
     * - Configuration files
     * - Images
     * - XML documents
     *
     * Directly using different APIs for every resource type
     * can make application code harder to maintain.
     *
     * Spring provides the Resource abstraction.
     *
     * Resource is an interface that represents a resource.
     *
     * It provides a common way to access different resource types.
     *
     *
     * IMPORTANT RESOURCE METHODS:
     *
     * - exists()
     * - isReadable()
     * - isOpen()
     * - getInputStream()
     * - getURL()
     * - getFile()
     * - contentLength()
     * - lastModified()
     * - getFilename()
     *
     * Not every resource supports every operation.
     *
     * For example, getFile() may not work for a resource
     * inside a packaged JAR.
     *
     *
     * ================================================================
     * 18. RESOURCE IMPLEMENTATIONS
     * ================================================================
     *
     * Spring provides several Resource implementations.
     *
     *
     * A. ClassPathResource
     *
     * Represents a resource available on the application classpath.
     *
     * Example:
     *
     * Resource resource =
     *         new ClassPathResource("config/app.properties");
     *
     *
     * B. FileSystemResource
     *
     * Represents a resource in the file system.
     *
     * Example:
     *
     * Resource resource =
     *         new FileSystemResource("/data/app.txt");
     *
     *
     * C. UrlResource
     *
     * Represents a resource identified by a URL.
     *
     * Example:
     *
     * Resource resource =
     *         new UrlResource("https://example.com/file.txt");
     *
     *
     * D. ByteArrayResource
     *
     * Represents a resource backed by a byte array.
     *
     *
     * E. InputStreamResource
     *
     * Represents a resource backed by an InputStream.
     *
     * It should be used carefully because the stream may
     * be single-use and its availability may be limited.
     *
     *
     * ================================================================
     * 19. RESOURCE LOADING
     * ================================================================
     *
     * ResourceLoader provides a common interface for loading
     * resources.
     *
     * It defines:
     *
     * getResource(String location)
     *
     *
     * COMMON RESOURCE PREFIXES:
     *
     * classpath:
     * Loads a resource from the classpath.
     *
     * file:
     * Loads a file-system resource.
     *
     * http:
     * Loads a resource through an HTTP URL.
     *
     * https:
     * Loads a secure HTTP resource.
     *
     *
     * EXAMPLE:
     *
     * @Component
     * public class ResourceService {
     *
     *     private final ResourceLoader resourceLoader;
     *
     *     public ResourceService(ResourceLoader resourceLoader) {
     *         this.resourceLoader = resourceLoader;
     *     }
     *
     *     public void readResource() throws IOException {
     *
     *         Resource resource =
     *             resourceLoader.getResource(
     *                 "classpath:config/app.properties"
     *             );
     *
     *         try (InputStream inputStream =
     *                  resource.getInputStream()) {
     *
     *             // Read resource contents.
     *         }
     *     }
     * }
     *
     * IMPORTANT:
     *
     * Always close streams after use.
     *
     * Try-with-resources is a good way to manage stream cleanup.
     *
     *
     * ================================================================
     * 20. RESOURCELOADERAWARE
     * ================================================================
     *
     * ResourceLoaderAware is an Aware interface.
     *
     * It allows a bean to receive a ResourceLoader reference.
     *
     * Example:
     *
     * public class ResourceService implements ResourceLoaderAware {
     *
     *     private ResourceLoader resourceLoader;
     *
     *     @Override
     *     public void setResourceLoader(
     *             ResourceLoader resourceLoader) {
     *
     *         this.resourceLoader = resourceLoader;
     *     }
     * }
     *
     * In ordinary application code, constructor injection of
     * ResourceLoader is generally simpler and preferred.
     *
     *
     * ================================================================
     * 21. INTERNATIONALIZATION
     * ================================================================
     *
     * Internationalization is commonly abbreviated as i18n.
     *
     * It means designing an application so that it can support
     * multiple languages and regional formats.
     *
     * Example:
     *
     * An application may support:
     *
     * - English
     * - Hindi
     * - Telugu
     * - French
     *
     * Instead of hardcoding messages inside Java classes,
     * messages can be stored in separate resource bundles.
     *
     * Example:
     *
     * messages.properties
     *
     * welcome.message=Welcome
     *
     * messages_hi.properties
     *
     * welcome.message=Swagat hai
     *
     * messages_te.properties
     *
     * welcome.message=Swagatham
     *
     * Spring's MessageSource provides access to these messages.
     *
     *
     * ================================================================
     * 22. MESSAGESOURCE
     * ================================================================
     *
     * MessageSource is a Spring interface used to resolve
     * messages from resource bundles.
     *
     * It supports locale-specific message resolution.
     *
     *
     * CONFIGURATION:
     *
     * @Configuration
     * public class MessageConfig {
     *
     *     @Bean
     *     public MessageSource messageSource() {
     *
     *         ResourceBundleMessageSource source =
     *                 new ResourceBundleMessageSource();
     *
     *         source.setBasename("messages");
     *
     *         return source;
     *     }
     * }
     *
     *
     * USAGE:
     *
     * @Component
     * public class GreetingService {
     *
     *     private final MessageSource messageSource;
     *
     *     public GreetingService(MessageSource messageSource) {
     *         this.messageSource = messageSource;
     *     }
     *
     *     public String getGreeting(Locale locale) {
     *
     *         return messageSource.getMessage(
     *             "welcome.message",
     *             null,
     *             locale
     *         );
     *     }
     * }
     *
     *
     * IMPORTANT METHODS:
     *
     * - getMessage(String code, Object[] args, Locale locale)
     * - getMessage(String code, Object[] args,
     *              String defaultMessage, Locale locale)
     * - getMessage(MessageSourceResolvable resolvable,
     *              Locale locale)
     *
     * USE CASES:
     *
     * - Multilingual applications.
     * - Localized error messages.
     * - User-facing messages.
     * - Locale-specific application text.
     *
     *
     * ================================================================
     * 23. APPLICATIONCONTEXT HIERARCHY
     * ================================================================
     *
     * Spring supports parent-child ApplicationContext relationships.
     *
     * A context can have another context as its parent.
     *
     * This is called an ApplicationContext hierarchy.
     *
     * Example:
     *
     *          Root Context
     *                |
     *          +-----+-----+
     *          |           |
     *          v           v
     *       Web Context  Admin Context
     *
     * The child contexts can access eligible beans from
     * their parent context.
     *
     * The parent context does not automatically access beans
     * defined only in its child contexts.
     *
     * This is useful for separating shared infrastructure
     * from application-specific components.
     *
     *
     * ================================================================
     * 24. PARENT AND CHILD CONTEXTS
     * ================================================================
     *
     * A parent context can contain:
     *
     * - Shared services.
     * - Database infrastructure.
     * - Security infrastructure.
     * - Shared configuration.
     *
     * A child context can contain:
     *
     * - Web controllers.
     * - View-related components.
     * - Web-specific services.
     *
     *
     * EXAMPLE:
     *
     * AnnotationConfigApplicationContext parent =
     *         new AnnotationConfigApplicationContext();
     *
     * parent.register(RootConfig.class);
     * parent.refresh();
     *
     * AnnotationConfigApplicationContext child =
     *         new AnnotationConfigApplicationContext();
     *
     * child.setParent(parent);
     * child.register(WebConfig.class);
     * child.refresh();
     *
     *
     * IMPORTANT:
     *
     * Child contexts can resolve beans from their parent.
     *
     * Parent contexts cannot resolve beans from their children.
     *
     * A child context can define a bean with the same name
     * as a parent bean, effectively hiding the parent bean
     * during lookup from that child.
     *
     * This is called bean shadowing.
     *
     * USE CASES:
     *
     * - Shared root configuration.
     * - Multiple web application contexts.
     * - Modular applications.
     * - Separating infrastructure from web components.
     *
     *
     * ================================================================
     * 25. APPLICATIONCONTEXTINITIALIZER
     * ================================================================
     *
     * ApplicationContextInitializer is a callback interface
     * used to initialize or customize an ApplicationContext
     * before it is refreshed.
     *
     * INTERFACE:
     *
     * public interface ApplicationContextInitializer<C
     *         extends ConfigurableApplicationContext> {
     *
     *     void initialize(C applicationContext);
     * }
     *
     *
     * EXAMPLE:
     *
     * public class CustomContextInitializer
     *         implements ApplicationContextInitializer<
     *             ConfigurableApplicationContext> {
     *
     *     @Override
     *     public void initialize(
     *             ConfigurableApplicationContext context) {
     *
     *         System.out.println(
     *             "Customizing application context"
     *         );
     *     }
     * }
     *
     * It can be used to customize the environment or context
     * before bean creation and context refresh.
     *
     * IMPORTANT:
     *
     * ApplicationContextInitializer is especially relevant
     * in Spring Boot startup customization.
     *
     *
     * ================================================================
     * 26. CONTEXT REFRESH AND LIFECYCLE
     * ================================================================
     *
     * ApplicationContext has a lifecycle.
     *
     * A typical configurable application context supports:
     *
     * - refresh()
     * - close()
     * - isActive()
     *
     *
     * REFRESH:
     *
     * refresh() initializes or refreshes the context.
     *
     * During refresh, Spring performs important operations such as:
     *
     * - Preparing the context.
     * - Processing bean factory post-processors.
     * - Registering bean post-processors.
     * - Initializing applicable singleton beans.
     * - Publishing the context refreshed event.
     *
     *
     * CLOSE:
     *
     * close() shuts down the context.
     *
     * It triggers destruction processing for managed singleton beans.
     *
     *
     * IMPORTANT:
     *
     * A context cannot generally be refreshed repeatedly in
     * every implementation. The exact behavior depends on
     * the ApplicationContext implementation.
     *
     * ================================================================
     * 27. SHUTDOWN HOOKS
     * ================================================================
     *
     * A shutdown hook allows a Spring context to close
     * automatically when the JVM shuts down normally.
     *
     * Example:
     *
     * AnnotationConfigApplicationContext context =
     *         new AnnotationConfigApplicationContext(AppConfig.class);
     *
     * context.registerShutdownHook();
     *
     * Spring registers a JVM shutdown hook.
     *
     * During normal JVM shutdown, the hook closes the context
     * and triggers applicable bean destruction callbacks.
     *
     * IMPORTANT:
     *
     * Shutdown hooks do not guarantee cleanup after:
     *
     * - Forced process termination.
     * - JVM crash.
     * - Operating system failure.
     * - Abrupt container termination without graceful handling.
     *
     * Applications should also use proper external resource
     * management and graceful shutdown strategies.
     *
     *
     * ================================================================
     * 28. SPRING CONTAINER EXTENSION POINTS
     * ================================================================
     *
     * Spring provides extension points that allow developers
     * to customize its internal behavior.
     *
     *
     * BEANFACTORYPOSTPROCESSOR:
     *
     * Modifies bean definition metadata.
     *
     *
     * BEANDEFINITIONREGISTRYPOSTPROCESSOR:
     *
     * Registers additional bean definitions and participates
     * in bean factory post-processing.
     *
     *
     * BEANPOSTPROCESSOR:
     *
     * Processes bean instances before and after initialization.
     *
     *
     * FACTORYBEAN:
     *
     * Provides custom object creation.
     *
     *
     * APPLICATIONEVENTPUBLISHER:
     *
     * Publishes application events.
     *
     *
     * APPLICATIONLISTENER / @EVENTLISTENER:
     *
     * Handles published events.
     *
     *
     * RESOURCELOADER:
     *
     * Loads resources through a common abstraction.
     *
     *
     * MESSAGESOURCE:
     *
     * Resolves localized messages.
     *
     *
     * APPLICATIONCONTEXTINITIALIZER:
     *
     * Customizes a context before refresh.
     *
     *
     * ================================================================
     * 29. COMMON MISTAKES
     * ================================================================
     *
     * MISTAKE 1:
     *
     * Confusing BeanFactoryPostProcessor with BeanPostProcessor.
     *
     * CORRECTION:
     *
     * BeanFactoryPostProcessor works with bean definitions.
     *
     * BeanPostProcessor works with bean instances.
     *
     *
     * MISTAKE 2:
     *
     * Assuming FactoryBean and @Bean are the same.
     *
     * CORRECTION:
     *
     * @Bean declares a bean through a method.
     *
     * FactoryBean is an interface that produces objects.
     *
     *
     * MISTAKE 3:
     *
     * Assuming Spring application events are distributed messages.
     *
     * CORRECTION:
     *
     * They are normally in-process events within an application.
     *
     *
     * MISTAKE 4:
     *
     * Assuming asynchronous events are guaranteed to execute.
     *
     * CORRECTION:
     *
     * Asynchronous execution does not automatically provide
     * durable delivery.
     *
     *
     * MISTAKE 5:
     *
     * Assuming every resource supports getFile().
     *
     * CORRECTION:
     *
     * Resources inside JAR files may not be accessible
     * as ordinary filesystem files.
     *
     *
     * MISTAKE 6:
     *
     * Assuming a child ApplicationContext is visible to its parent.
     *
     * CORRECTION:
     *
     * Parent beans are visible to children, not vice versa.
     *
     *
     * MISTAKE 7:
     *
     * Assuming a JVM shutdown hook always runs.
     *
     * CORRECTION:
     *
     * It runs during normal JVM shutdown, not every possible
     * termination scenario.
     *
     *
     * ================================================================
     * 30. REAL-WORLD SCENARIOS
     * ================================================================
     *
     * SCENARIO 1: DYNAMIC BEAN REGISTRATION
     *
     * A framework needs to register beans based on external
     * configuration.
     *
     * Suitable feature:
     *
     * BeanDefinitionRegistryPostProcessor.
     *
     *
     * SCENARIO 2: AUTOMATIC PROXY CREATION
     *
     * A framework needs to wrap beans with additional behavior.
     *
     * Suitable feature:
     *
     * BeanPostProcessor.
     *
     *
     * SCENARIO 3: ORDER CONFIRMATION EMAIL
     *
     * When an order is placed, a notification component should
     * react without the order service directly depending on it.
     *
     * Suitable feature:
     *
     * Application events.
     *
     *
     * SCENARIO 4: MULTILINGUAL APPLICATION
     *
     * Users should receive messages in their preferred language.
     *
     * Suitable feature:
     *
     * MessageSource and resource bundles.
     *
     *
     * SCENARIO 5: LOADING CONFIGURATION FILES
     *
     * An application needs to read a classpath configuration file.
     *
     * Suitable feature:
     *
     * Resource and ResourceLoader.
     *
     *
     * SCENARIO 6: SHARED AND WEB-SPECIFIC CONFIGURATION
     *
     * An application separates shared services from
     * web-specific components.
     *
     * Suitable feature:
     *
     * Parent-child ApplicationContext hierarchy.
     *
     *
     * ================================================================
     * 31. INTERVIEW QUESTIONS
     * ================================================================
     *
     * BASIC:
     *
     * 1. What is BeanFactoryPostProcessor?
     * 2. What is BeanPostProcessor?
     * 3. What is FactoryBean?
     * 4. What are Spring application events?
     * 5. What is ApplicationEventPublisher?
     * 6. What is @EventListener?
     * 7. What is Environment in Spring?
     * 8. What is Resource abstraction?
     * 9. What is MessageSource?
     * 10. What is ApplicationContextInitializer?
     *
     *
     * INTERMEDIATE:
     *
     * 11. What is the difference between BeanFactoryPostProcessor
     *     and BeanPostProcessor?
     * 12. What is BeanDefinitionRegistryPostProcessor?
     * 13. What is the difference between @Bean and FactoryBean?
     * 14. What is the difference between synchronous and
     *     asynchronous event handling?
     * 15. What are ContextRefreshedEvent and ContextClosedEvent?
     * 16. How do you publish a custom application event?
     * 17. How do you listen to an application event?
     * 18. What is a PropertySource?
     * 19. What are ResourceLoader and Resource?
     * 20. What is internationalization in Spring?
     *
     *
     * ADVANCED:
     *
     * 21. At what stage does BeanFactoryPostProcessor execute?
     * 22. How does BeanPostProcessor help Spring AOP?
     * 23. How do you obtain a FactoryBean instead of its product?
     * 24. Are Spring application events durable?
     * 25. How do asynchronous event listeners handle exceptions?
     * 26. What is the difference between a parent and child context?
     * 27. Can a parent context access beans in a child context?
     * 28. Why might Resource.getFile() fail for a JAR resource?
     * 29. What is the purpose of ApplicationContextInitializer?
     * 30. What happens when an ApplicationContext is refreshed?
     *
     *
     * ================================================================
     * 32. PRACTICE EXERCISES
     * ================================================================
     *
     * EXERCISE 1:
     *
     * Implement BeanFactoryPostProcessor and log when
     * its callback executes.
     *
     * EXERCISE 2:
     *
     * Implement BeanDefinitionRegistryPostProcessor and
     * register a custom bean definition.
     *
     * EXERCISE 3:
     *
     * Create a BeanPostProcessor that logs bean names
     * before and after initialization.
     *
     * EXERCISE 4:
     *
     * Implement FactoryBean for a custom object.
     * Retrieve both the product and the factory.
     *
     * EXERCISE 5:
     *
     * Create a UserRegisteredEvent record.
     *
     * EXERCISE 6:
     *
     * Publish UserRegisteredEvent from UserService.
     *
     * EXERCISE 7:
     *
     * Create two event listeners for the same event.
     * Use @Order to control synchronous listener invocation order.
     *
     * EXERCISE 8:
     *
     * Configure an asynchronous event listener.
     *
     * EXERCISE 9:
     *
     * Read an application property through Environment.
     *
     * EXERCISE 10:
     *
     * Load a classpath resource using ResourceLoader.
     *
     * EXERCISE 11:
     *
     * Configure MessageSource with English and Hindi messages.
     *
     * EXERCISE 12:
     *
     * Create a parent and child ApplicationContext.
     * Verify the direction of bean visibility.
     *
     * EXERCISE 13:
     *
     * Register a shutdown hook and observe bean destruction.
     *
     *
     * ================================================================
     * 33. QUICK REVISION
     * ================================================================
     *
     * BeanFactoryPostProcessor:
     * Modifies bean definition metadata.
     *
     * BeanDefinitionRegistryPostProcessor:
     * Can register additional bean definitions.
     *
     * BeanPostProcessor:
     * Processes bean instances before and after initialization.
     *
     * FactoryBean:
     * Creates and exposes a product object.
     *
     * ApplicationEventPublisher:
     * Publishes application events.
     *
     * @EventListener:
     * Handles application events.
     *
     * Environment:
     * Provides access to properties and profiles.
     *
     * PropertySource:
     * Represents a source of configuration properties.
     *
     * Resource:
     * Common abstraction for external resources.
     *
     * ResourceLoader:
     * Loads resources.
     *
     * MessageSource:
     * Resolves localized messages.
     *
     * ApplicationContextInitializer:
     * Customizes a context before refresh.
     *
     * PARENT CONTEXT:
     * Provides beans that can be accessed by child contexts.
     *
     * CHILD CONTEXT:
     * Can access eligible parent beans and its own beans.
     *
     * SYNCHRONOUS EVENTS:
     * Listeners normally execute in the publishing thread.
     *
     * ASYNCHRONOUS EVENTS:
     * Listeners can execute through an asynchronous executor.
     *
     * IMPORTANT:
     *
     * BeanFactoryPostProcessor works with definitions.
     *
     * BeanPostProcessor works with instances.
     *
     * Spring application events are not automatically durable
     * or distributed.
     *
     * ================================================================
     * END OF SPRING CORE - FILE 06
     * ================================================================
     */

    public static void main(String[] args) {

        System.out.println(
            "Spring Core - Advanced Container Features"
        );

    }

}
