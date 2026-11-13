
public class SpringContainerAndBeans {

    /*
     * =====================================================================
     *                    SPRING CONTAINER AND BEANS
     * =====================================================================
     *
     *
     * 1. WHAT IS THE SPRING CONTAINER?
     *
     * The Spring Container is the core infrastructure responsible
     * for creating, configuring and managing objects in a
     * Spring application.
     *  
     * Its major responsibilities include:
     *
     * - Creating application objects.
     * - Configuring objects.
     * - Resolving dependencies.
     * - Injecting dependencies.
     * - Managing bean lifecycles.
     * - Providing managed objects to application components.
     *
     * Objects managed by the Spring Container are called Spring Beans.
     *
     * Example:
     *
     * EmployeeController
     *         |
     *         v
     * EmployeeService
     *         |
     *         v
     * EmployeeRepository
     *
     * The Spring Container can create these objects and connect
     * their dependencies.
     *
     * Instead of manually creating and connecting every object,
     * the developer describes how application components
     * should be configured.
     *
     *
     * 2. WHY DO WE NEED A SPRING CONTAINER?
     *
     * Consider a traditional Java application:
     *
     * EmployeeRepository repository = new EmployeeRepository();
     *
     * EmployeeService service = new EmployeeService(repository);
     *
     * EmployeeController controller = new EmployeeController(service);
     *
     * Here, the developer manually creates every object
     * and connects its dependencies.
     *
     * In a large application, this becomes difficult to maintain.
     *
     * Problems:
     *
     * - Object creation logic is spread across the application.
     * - Dependency management becomes complicated.
     * - Replacing implementations requires more changes.
     * - Object lifecycle management becomes difficult.
     * - Testing and configuration become harder.
     *
     * Spring Container centralizes these responsibilities.
     *
     *
     * 3. WHAT IS A SPRING BEAN?
     *
     * A Spring Bean is an object that is instantiated,
     * assembled and managed by the Spring IoC Container.
     *
     * Example:
     *
     * @Component
     * class EmployeeService {
     *
     *     public void display() {
     *         System.out.println("Employee Service");
     *     }
     * }
     *
     * When Spring discovers this component through
     * component scanning, it registers a bean definition
     * and creates a managed bean according to its configuration.
     *
     * Important:
     *
     * A Spring Bean is an ordinary Java object managed
     * by the Spring Container.
     *
     * Not every object created using the new keyword
     * automatically becomes a Spring Bean.
     *
     *
     * 4. BEAN VS JAVA OBJECT
     *
     * Every Spring Bean is a Java object.
     *
     * But every Java object is not necessarily a Spring Bean.
     *
     * Example:
     *
     * Employee employee = new Employee();
     *
     * This creates an ordinary Java object.
     *
     * Spring does not automatically manage it merely
     * because the class exists in the application.
     *
     * However:
     *
     * @Component
     * class EmployeeService {
     *
     * }
     *
     * If Spring discovers and registers this component,
     * its instance becomes a Spring Bean.
     *
     *
     * 5. HOW DOES A CLASS BECOME A SPRING BEAN?
     *
     * Common ways of registering beans:
     *
     * 1. Component scanning.
     * 2. Java-based configuration using @Bean.
     * 3. XML-based bean configuration.
     * 4. Programmatic bean registration.
     *
     * Each approach provides a way to register objects
     * with the Spring Container.
     *
     * We will study the configuration approaches
     * in a separate file.
     *
     *
     * 6. WHAT IS A BEAN DEFINITION?
     *
     * A BeanDefinition is metadata describing how
     * Spring should create and configure a bean.
     *
     * It can contain information such as:
     *
     * - Bean class.
     * - Scope.
     * - Constructor arguments.
     * - Property values.
     * - Lazy initialization.
     * - Primary status.
     * - Other bean configuration metadata.
     *
     * Important distinction:
     *
     * BeanDefinition:
     * Describes how a bean should be created.
     *
     * Bean instance:
     * The actual object created by Spring.
     *
     * Example:
     *
     * A BeanDefinition describes EmployeeService.
     *
     * Spring uses that metadata to create an
     * EmployeeService object.
     *
     *
     * 7. WHAT IS BEAN REGISTRATION?
     *
     * Bean registration means making a bean definition
     * known to the Spring Container.
     *
     * Example:
     *
     * @Component
     * class EmployeeService {
     *
     * }
     *
     * During component scanning, Spring can discover
     * this class and register its bean definition.
     *
     * Another example:
     *
     * @Configuration
     * class AppConfig {
     *
     *     @Bean
     *     EmployeeService employeeService() {
     *         return new EmployeeService();
     *     }
     * }
     *
     * Here, the @Bean method provides an object that
     * Spring registers as a bean.
     *
     * Important:
     *
     * Registration and instantiation are different stages.
     *
     * Registration makes the bean definition available.
     *
     * Instantiation creates the actual object.
     *
     *
     * 8. TYPES OF SPRING CONTAINERS
     *
     * Spring provides two important container interfaces:
     *
     * 1. BeanFactory.
     * 2. ApplicationContext.
     *
     * Both are part of Spring's IoC infrastructure.
     *
     *
     * 9. WHAT IS BeanFactory?
     *
     * BeanFactory is the basic IoC container interface
     * in Spring.
     *
     * It provides fundamental bean management functionality.
     *
     * Its responsibilities include:
     *
     * - Managing bean definitions.
     * - Creating beans when required.
     * - Resolving dependencies.
     * - Providing access to beans.
     *
     * Example:
     *
     * BeanFactory factory = ...;
     *
     * EmployeeService service =
     *         factory.getBean(EmployeeService.class);
     *
     * Here, getBean() retrieves a bean from the container.
     *
     * BeanFactory is the fundamental container abstraction.
     *
     *
     * 10. WHAT IS ApplicationContext?
     *
     * ApplicationContext is a more feature-rich
     * container interface built on top of BeanFactory.
     *
     * It provides BeanFactory functionality along with
     * additional application infrastructure.
     *
     * Important capabilities:
     *
     * - Bean management.
     * - Dependency Injection.
     * - Application event publication.
     * - Internationalization.
     * - Resource loading.
     * - Environment and profile support.
     * - Integration with annotation-based configuration.
     *
     * ApplicationContext is commonly used in real
     * Spring applications.
     *
     *
     * 11. BeanFactory VS ApplicationContext
     *
     * BeanFactory:
     *
     * - Fundamental IoC container interface.
     * - Provides basic bean management.
     * - Commonly uses lazy initialization for singleton beans
     *   when they are not requested earlier.
     * - Provides fewer application-level facilities.
     *
     * ApplicationContext:
     *
     * - Extends BeanFactory.
     * - Provides additional application infrastructure.
     * - Supports events and internationalization.
     * - Supports resource loading and environment abstraction.
     * - Eagerly creates non-lazy singleton beans by default
     *   during context refresh.
     *
     * Important:
     *
     * ApplicationContext is not a completely separate
     * replacement for BeanFactory.
     *
     * It extends the BeanFactory contract.
     *
     *
     * 12. APPLICATIONCONTEXT HIERARCHY
     *
     * Simplified relationship:
     *
     *             BeanFactory
     *                  |
     *                  v
     *        ApplicationContext
     *
     * ApplicationContext extends ListableBeanFactory
     * and HierarchicalBeanFactory, both of which are
     * related to the BeanFactory abstraction.
     *
     * It also extends additional interfaces for application
     * infrastructure.
     *
     *
     * 13. COMMON APPLICATIONCONTEXT IMPLEMENTATIONS
     *
     * Important implementations include:
     *
     * 1. AnnotationConfigApplicationContext
     *
     *    Used for Java-based annotation configuration
     *    in non-web applications.
     *
     * 2. ClassPathXmlApplicationContext
     *
     *    Loads Spring bean configuration from XML files
     *    available on the classpath.
     *
     * 3. FileSystemXmlApplicationContext
     *
     *    Loads XML configuration from a filesystem path.
     *
     * 4. AnnotationConfigWebApplicationContext
     *
     *    Provides annotation-based configuration support
     *    for Servlet web applications.
     *
     * 5. WebApplicationContext
     *
     *    An ApplicationContext specialization for web
     *    applications.
     *
     * Modern Spring applications commonly use
     * Java-based configuration.
     *
     *
     * 14. CREATING AN APPLICATIONCONTEXT
     *
     * Example:
     *
     * AnnotationConfigApplicationContext context =
     *         new AnnotationConfigApplicationContext(AppConfig.class);
     *
     * Here:
     *
     * AppConfig.class provides Java-based configuration.
     *
     * The context processes the configuration and manages
     * the registered beans.
     *
     * Retrieve a bean:
     *
     * EmployeeService service =
     *         context.getBean(EmployeeService.class);
     *
     * Close the context:
     *
     * context.close();
     *
     * Closing the context allows the container to perform
     * its applicable shutdown and destruction processing.
     *
     *
     * 15. WHAT HAPPENS WHEN APPLICATIONCONTEXT STARTS?
     *
     * Consider:
     *
     * ApplicationContext context =
     *         new AnnotationConfigApplicationContext(AppConfig.class);
     *
     * A simplified startup process:
     *
     * 1. Configuration sources are processed.
     *
     * 2. Bean definitions are registered.
     *
     * 3. BeanFactoryPostProcessors are invoked.
     *
     * 4. Bean instances are created when required by the
     *    container's initialization strategy.
     *
     * 5. Dependencies are resolved and injected.
     *
     * 6. Bean post-processors process bean instances.
     *
     * 7. Initialization callbacks are executed.
     *
     * 8. The application context completes its refresh.
     *
     * Important:
     *
     * This is a simplified overview.
     *
     * Spring's actual startup sequence contains additional
     * steps and extension points.
     *
     *
     * 16. EAGER INITIALIZATION
     *
     * Eager initialization means creating a bean early,
     * generally during application context initialization.
     *
     * By default, ApplicationContext eagerly creates
     * non-lazy singleton beans during context refresh.
     *
     * Example:
     *
     * @Component
     * class EmployeeService {
     *
     *     public EmployeeService() {
     *         System.out.println("EmployeeService created");
     *     }
     * }
     *
     * When the application context starts, this singleton
     * bean is normally created even if no other bean
     * has requested it.
     *
     * Benefits:
     *
     * - Detects many configuration errors during startup.
     * - Required beans are ready when the application starts.
     *
     *
     * 17. LAZY INITIALIZATION
     *
     * Lazy initialization means creating a bean only when
     * it is first required.
     *
     * Spring provides @Lazy.
     *
     * Example:
     *
     * @Component
     * @Lazy
     * class ReportService {
     *
     *     public ReportService() {
     *         System.out.println("ReportService created");
     *     }
     * }
     *
     * The bean is not created during normal eager
     * singleton initialization.
     *
     * It is created when first requested or required
     * by another bean.
     *
     * Important:
     *
     * Lazy initialization can defer startup work.
     *
     * It can also defer the discovery of some configuration
     * and initialization problems until later.
     *
     *
     * 18. WHAT IS BEAN SCOPE?
     *
     * Bean scope determines how Spring creates and manages
     * instances of a bean.
     *
     * Important scopes:
     *
     * 1. singleton.
     * 2. prototype.
     * 3. request.
     * 4. session.
     * 5. application.
     * 6. websocket.
     *
     * These scopes are discussed in detail in the
     * Bean Lifecycle and Scopes file.
     *
     *
     * 19. SINGLETON BEAN
     *
     * Singleton is the default Spring bean scope.
     *
     * It means Spring maintains one bean instance per
     * bean definition per container.
     *
     * Example:
     *
     * @Component
     * class EmployeeService {
     *
     * }
     *
     * Multiple requests for this bean from the same
     * container normally return the same instance.
     *
     * Important:
     *
     * Spring singleton does not mean one object
     * across the entire JVM.
     *
     * Different containers can have different
     * singleton instances.
     *
     *
     * 20. PROTOTYPE BEAN
     *
     * Prototype scope means Spring creates a new bean
     * instance whenever that bean is requested from
     * the container.
     *
     * Example:
     *
     * @Component
     * @Scope("prototype")
     * class ReportGenerator {
     *
     * }
     *
     * Each separate container lookup normally produces
     * a new ReportGenerator instance.
     *
     * Important:
     *
     * Spring does not manage the complete destruction
     * lifecycle of prototype beans after handing them
     * to the caller.
     *
     *
     * 21. BEAN FACTORY METHODS
     *
     * Spring can create beans through configuration
     * factory methods.
     *
     * Example:
     *
     * @Configuration
     * class AppConfig {
     *
     *     @Bean
     *     EmployeeService employeeService() {
     *         return new EmployeeService();
     *     }
     * }
     *
     * Here:
     *
     * @Bean marks the method as a bean-producing method.
     *
     * The returned object is registered with the
     * Spring Container.
     *
     * The method name is normally used as the bean name
     * unless a different name is configured.
     *
     *
     * 22. WHAT IS A BEAN NAME?
     *
     * Every registered Spring bean has one or more names
     * or aliases through which it can be identified.
     *
     * Example:
     *
     * @Component("employeeService")
     * class EmployeeService {
     *
     * }
     *
     * Retrieve it by name:
     *
     * EmployeeService service =
     *         context.getBean("employeeService",
     *                         EmployeeService.class);
     *
     * When a component name is not explicitly provided,
     * Spring generally generates a default bean name
     * based on the class name.
     *
     * For example:
     *
     * EmployeeService becomes employeeService.
     *
     *
     * 23. WHAT IS A BEAN ALIAS?
     *
     * A bean can have additional names called aliases.
     *
     * Example:
     *
     * @Configuration
     * class AppConfig {
     *
     *     @Bean({"employeeService", "staffService"})
     *     EmployeeService employeeService() {
     *         return new EmployeeService();
     *     }
     * }
     *
     * Both names refer to the same bean definition
     * and its managed bean instance.
     *
     *
     * 24. WHAT IS A HIERARCHICAL APPLICATIONCONTEXT?
     *
     * Spring supports parent-child ApplicationContext
     * relationships.
     *
     * A child context can access beans defined in its
     * parent context.
     *
     * A parent context cannot access beans defined
     * only in its child.
     *
     * Example:
     *
     * Parent Context
     *       |
     *       |---- SharedService
     *       |
     *       v
     * Child Context
     *       |
     *       |---- EmployeeController
     *
     * The child can access SharedService from the parent.
     *
     * This arrangement has historically been useful in
     * Servlet-based web applications with separate
     * application and web contexts.
     *
     *
     * 25. WHAT IS THE ENVIRONMENT ABSTRACTION?
     *
     * Spring provides the Environment abstraction.
     *
     * It represents the application's environment and
     * provides access to:
     *
     * - Properties.
     * - Active profiles.
     * - Default profiles.
     * - Property sources.
     *
     * Example:
     *
     * Environment environment = context.getEnvironment();
     *
     * String value = environment.getProperty("app.name");
     *
     * This allows application configuration to be
     * accessed through a common abstraction.
     *
     *
     * 26. WHAT ARE APPLICATION EVENTS?
     *
     * Application events allow components to communicate
     * through an event publication and listener mechanism.
     *
     * Example:
     *
     * A user registers successfully.
     *
     * The application publishes a UserRegisteredEvent.
     *
     * A notification listener receives the event and
     * sends an email.
     *
     * This helps separate the event-producing component
     * from the components that react to the event.
     *
     * Spring provides:
     *
     * - ApplicationEventPublisher.
     * - ApplicationListener.
     * - @EventListener.
     *
     * We will cover these in the advanced container features file.
     *
     *
     * 27. WHAT IS RESOURCE LOADING?
     *
     * Spring provides the Resource abstraction for
     * accessing resources in a consistent way.
     *
     * Examples:
     *
     * - Classpath files.
     * - Filesystem files.
     * - URLs.
     *
     * Example resource prefixes:
     *
     * classpath:config.properties
     *
     * file:/home/user/config.properties
     *
     * https://example.com/config.json
     *
     * ResourceLoader helps locate and access resources.
     *
     *
     * 28. WHAT IS INTERNATIONALIZATION?
     *
     * Internationalization means designing an application
     * so it can support multiple languages and locales.
     *
     * Spring provides MessageSource for resolving
     * localized messages.
     *
     * Example:
     *
     * messages.properties
     * messages_en.properties
     * messages_hi.properties
     *
     * Different messages can be provided for different locales.
     *
     *
     * 29. APPLICATIONCONTEXT VS SPRING BOOT APPLICATION
     *
     * In a traditional Spring application, developers may
     * create an ApplicationContext explicitly.
     *
     * In a Spring Boot application, Spring Boot creates
     * and configures the application context as part
     * of application startup.
     *
     * Example:
     *
     * @SpringBootApplication
     * public class EmployeeApplication {
     *
     *     public static void main(String[] args) {
     *
     *         SpringApplication.run(
     *                 EmployeeApplication.class, args);
     *     }
     * }
     *
     * SpringApplication.run() starts the Spring Boot
     * application and creates or refreshes the appropriate
     * ApplicationContext.
     *
     * The exact context implementation depends on the
     * application type and configuration.
     *
     *
     * 30. HOW TO RETRIEVE A BEAN
     *
     * Spring provides getBean() methods.
     *
     * Retrieve by type:
     *
     * EmployeeService service =
     *         context.getBean(EmployeeService.class);
     *
     * Retrieve by name:
     *
     * EmployeeService service =
     *         context.getBean("employeeService",
     *                         EmployeeService.class);
     *
     * Retrieve by name and type:
     *
     * context.getBean("employeeService", EmployeeService.class);
     *
     * Important:
     *
     * getBean() is useful for explicit lookup and framework
     * infrastructure.
     *
     * In normal application components, prefer constructor
     * injection instead of repeatedly calling getBean().
     *
     *
     * 31. WHAT IS AWARE INTERFACE?
     *
     * Spring provides several Aware interfaces.
     *
     * They allow a bean to receive references to certain
     * Spring infrastructure objects.
     *
     * Examples:
     *
     * BeanNameAware:
     * Provides the bean name.
     *
     * BeanFactoryAware:
     * Provides access to the BeanFactory.
     *
     * ApplicationContextAware:
     * Provides access to the ApplicationContext.
     *
     * Important:
     *
     * Aware interfaces are useful in certain infrastructure
     * scenarios.
     *
     * Avoid using them unnecessarily in ordinary business
     * components because they increase dependence on Spring APIs.
     *
     *
     * 32. BEANFACTORYPOSTPROCESSOR
     *
     * BeanFactoryPostProcessor is an extension point that
     * allows modification of bean definition metadata
     * before ordinary beans are instantiated.
     *
     * It works on bean definitions, not on normal
     * fully initialized bean instances.
     *
     * Example use cases:
     *
     * - Modifying bean definition properties.
     * - Processing configuration metadata.
     *
     * Spring itself uses infrastructure implementations
     * of this extension point.
     *
     *
     * 33. BEANPOSTPROCESSOR
     *
     * BeanPostProcessor is an extension point that allows
     * processing bean instances before and after
     * initialization callbacks.
     *
     * It can:
     *
     * - Inspect bean instances.
     * - Modify bean instances.
     * - Wrap bean instances with proxies.
     *
     * It is an important part of Spring's internal
     * bean-processing infrastructure.
     *
     * Examples of Spring features that rely on
     * bean post-processing include annotation processing
     * and proxy-based infrastructure.
     *
     *
     * 34. BEANFACTORYPOSTPROCESSOR VS BEANPOSTPROCESSOR
     *
     * BeanFactoryPostProcessor:
     *
     * - Processes bean definition metadata.
     * - Runs before regular bean instantiation.
     *
     * BeanPostProcessor:
     *
     * - Processes bean instances.
     * - Runs around the initialization callbacks.
     *
     * Simple understanding:
     *
     * BeanFactoryPostProcessor:
     * Works mainly with bean definitions.
     *
     * BeanPostProcessor:
     * Works with created bean instances.
     *
     *
     * 35. COMMON CONTAINER MISTAKES
     *
     * 1. Assuming every Java object is a Spring Bean.
     *
     * 2. Confusing BeanDefinition with a bean instance.
     *
     * 3. Confusing BeanFactory with ApplicationContext.
     *
     * 4. Assuming Spring singleton means one object
     *    across the entire JVM.
     *
     * 5. Assuming lazy initialization applies to every bean
     *    in every situation.
     *
     * 6. Assuming Spring completely manages the destruction
     *    lifecycle of prototype beans.
     *
     * 7. Calling getBean() everywhere instead of using DI.
     *
     * 8. Confusing bean registration with bean instantiation.
     *
     * 9. Ignoring the role of BeanPostProcessor.
     *
     * 10. Assuming ApplicationContext creates all beans
     *     before every possible configuration-processing step.
     *
     *
     * 36. INTERVIEW QUESTIONS - BASIC LEVEL
     *
     * 1. What is the Spring Container?
     *
     * 2. What is a Spring Bean?
     *
     * 3. What is a BeanDefinition?
     *
     * 4. What is the difference between a Java object
     *    and a Spring Bean?
     *
     * 5. What is BeanFactory?
     *
     * 6. What is ApplicationContext?
     *
     * 7. What is the default Spring bean scope?
     *
     * 8. What is lazy initialization?
     *
     * 9. What is eager initialization?
     *
     * 10. How can a class become a Spring Bean?
     *
     *
     * 37. INTERVIEW QUESTIONS - INTERMEDIATE LEVEL
     *
     * 1. What is the difference between BeanFactory
     *    and ApplicationContext?
     *
     * 2. What are the common ApplicationContext implementations?
     *
     * 3. What happens when an ApplicationContext starts?
     *
     * 4. What is the difference between bean registration
     *    and bean instantiation?
     *
     * 5. What is the difference between singleton and prototype?
     *
     * 6. What is the purpose of @Lazy?
     *
     * 7. What is the purpose of @Bean?
     *
     * 8. What is the difference between bean name and alias?
     *
     * 9. What is a hierarchical ApplicationContext?
     *
     * 10. How does Spring Boot create its ApplicationContext?
     *
     *
     * 38. INTERVIEW QUESTIONS - ADVANCED LEVEL
     *
     * 1. Explain the high-level ApplicationContext refresh process.
     *
     * 2. What is the difference between BeanFactoryPostProcessor
     *    and BeanPostProcessor?
     *
     * 3. What is the role of BeanDefinition metadata?
     *
     * 4. What is the role of Environment?
     *
     * 5. What are ApplicationContext events?
     *
     * 6. What are Spring Aware interfaces?
     *
     * 7. How does Spring process bean instances before
     *    and after initialization?
     *
     * 8. How does a child ApplicationContext access parent beans?
     *
     * 9. What happens when a prototype bean is requested
     *    multiple times?
     *
     * 10. Why are BeanPostProcessors important for Spring
     *     infrastructure features?
     *
     *
     * 39. INTERVIEW QUESTIONS - REAL-WORLD SCENARIOS
     *
     * 1. A bean is not available for injection.
     *    What would you inspect in the Spring Container configuration?
     *
     * 2. Your application starts successfully but a lazy bean
     *    fails when first requested. Why might that happen?
     *
     * 3. A developer assumes a singleton bean is created
     *    only once across multiple ApplicationContexts.
     *    Is that correct?
     *
     * 4. You need to modify bean definitions before normal
     *    application beans are instantiated.
     *    Which extension point would you consider?
     *
     * 5. You need to wrap bean instances after their creation
     *    or initialization.
     *    Which extension point would you consider?
     *
     * 6. Your application needs to publish an event when
     *    a customer registers. Which Spring infrastructure
     *    can you use?
     *
     * 7. A prototype bean is injected into a singleton bean.
     *    Will the singleton automatically receive a new
     *    prototype instance every time it uses that field?
     *
     * 8. A child web context needs a service registered
     *    in its parent context. Can it access that bean?
     *
     *
     * 40. PRACTICAL EXERCISES
     *
     * Exercise 1:
     *
     * Create an AnnotationConfigApplicationContext.
     *
     * Register an EmployeeService bean.
     *
     * Retrieve the bean using getBean().
     *
     * Close the context.
     *
     *
     * Exercise 2:
     *
     * Create a configuration class using @Configuration.
     *
     * Register two beans using @Bean.
     *
     * Inject one bean into another.
     *
     *
     * Exercise 3:
     *
     * Create two beans with singleton scope.
     *
     * Retrieve each bean multiple times.
     *
     * Compare their object references.
     *
     *
     * Exercise 4:
     *
     * Create a prototype bean.
     *
     * Retrieve it multiple times.
     *
     * Compare the object references.
     *
     *
     * Exercise 5:
     *
     * Create a custom BeanFactoryPostProcessor.
     *
     * Observe when it executes relative to normal
     * bean instantiation.
     *
     *
     * Exercise 6:
     *
     * Create a BeanPostProcessor.
     *
     * Log the bean name before and after initialization.
     *
     *
     * =====================================================================
     *                   END OF SPRING CONTAINER AND BEANS
     * =====================================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Spring Container and Beans , Happy Learning ..!");
    }
}
