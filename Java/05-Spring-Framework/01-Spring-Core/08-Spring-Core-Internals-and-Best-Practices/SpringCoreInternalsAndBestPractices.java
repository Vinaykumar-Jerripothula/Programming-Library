
public class SpringCoreInternalsAndBestPractices {

    /*
     * ================================================================
     * SPRING CORE - 08: INTERNALS AND BEST PRACTICES
     * ================================================================
     *
     * CONTENTS
     *
     * 1.  Introduction
     * 2.  Spring Core Architecture
     * 3.  Spring Container Internals
     * 4.  ApplicationContext Startup Flow
     * 5.  Bean Definition Internals
     * 6.  Bean Creation Internals
     * 7.  Dependency Injection Internals
     * 8.  Bean Lifecycle Recap
     * 9.  BeanPostProcessor Internals
     * 10. BeanFactoryPostProcessor Internals
     * 11. ApplicationContext and Environment
     * 12. Component Scanning Internals
     * 13. Configuration Class Processing
     * 14. Autowiring Internals
     * 15. Circular Dependencies
     * 16. Proxy Creation and AOP Integration
     * 17. Spring Events Internals
     * 18. Resource Management
     * 19. Exception Handling in the Container
     * 20. Debugging Spring Applications
     * 21. Spring Core Best Practices
     * 22. Common Design Mistakes
     * 23. Production Considerations
     * 24. Interview Questions
     * 25. Practice Exercises
     * 26. Quick Revision
     *
     *
     * ================================================================
     * 1. INTRODUCTION
     * ================================================================
     *
     * Until now, we have studied the major concepts of Spring Core:
     *
     * - Introduction to Spring
     * - IoC and Dependency Injection
     * - Spring Container and Beans
     * - Bean Configuration and Autowiring
     * - Bean Lifecycle and Scopes
     * - Advanced Container Features
     * - Spring AOP
     *
     * In this file, we connect these concepts and understand
     * how they work together internally.
     *
     * The objective is to understand:
     *
     * - How Spring starts an application.
     * - How Spring discovers bean definitions.
     * - How Spring creates objects.
     * - How Spring injects dependencies.
     * - How Spring initializes beans.
     * - How Spring creates proxies.
     * - How Spring manages the application lifecycle.
     *
     * Understanding these internals helps when debugging
     * real-world Spring applications.
     *
     *
     * ================================================================
     * 2. SPRING CORE ARCHITECTURE
     * ================================================================
     *
     * Spring Core is built around the IoC Container.
     *
     * IMPORTANT COMPONENTS:
     *
     * 1. BeanFactory
     * 2. ApplicationContext
     * 3. BeanDefinition
     * 4. BeanDefinitionRegistry
     * 5. BeanFactoryPostProcessor
     * 6. BeanPostProcessor
     * 7. Environment
     * 8. ResourceLoader
     * 9. ApplicationEventPublisher
     * 10. AOP infrastructure
     *
     *
     * GENERAL RELATIONSHIP:
     *
     * ApplicationContext
     *        |
     *        v
     *     BeanFactory
     *        |
     *        v
     *    BeanDefinition
     *        |
     *        v
     *    Bean Creation
     *        |
     *        v
     * Dependency Injection
     *        |
     *        v
     * BeanPostProcessors
     *        |
     *        v
     * Initialized Bean
     *
     * This is a conceptual overview, not a complete internal
     * call sequence.
     *
     *
     * ================================================================
     * 3. SPRING CONTAINER INTERNALS
     * ================================================================
     *
     * The Spring IoC Container is responsible for managing
     * application objects.
     *
     * Its responsibilities include:
     *
     * - Registering bean definitions.
     * - Creating bean instances.
     * - Resolving dependencies.
     * - Injecting dependencies.
     * - Managing bean lifecycle callbacks.
     * - Applying bean post-processors.
     * - Managing bean scopes.
     * - Managing bean destruction.
     *
     *
     * BEANFACTORY:
     *
     * BeanFactory is the fundamental container abstraction.
     *
     * It provides bean creation and retrieval capabilities.
     *
     *
     * APPLICATIONCONTEXT:
     *
     * ApplicationContext builds on BeanFactory and provides
     * additional enterprise application features.
     *
     * Examples:
     *
     * - Event publishing.
     * - Internationalization.
     * - Resource loading.
     * - Environment abstraction.
     *
     * In most Spring applications, ApplicationContext is used
     * as the primary container interface.
     *
     *
     * ================================================================
     * 4. APPLICATIONCONTEXT STARTUP FLOW
     * ================================================================
     *
     * Consider a Spring Boot application.
     *
     * SpringApplication.run(Application.class, args);
     *
     * This starts the application context.
     *
     * A simplified startup flow is:
     *
     * 1. SpringApplication is created.
     *
     * 2. Application environment is prepared.
     *
     * 3. Application properties and profiles are processed.
     *
     * 4. An appropriate ApplicationContext is created.
     *
     * 5. Initializers and application listeners participate
     *    in startup.
     *
     * 6. Bean definitions are registered.
     *
     * 7. Configuration classes are processed.
     *
     * 8. Component scanning discovers eligible components.
     *
     * 9. BeanFactoryPostProcessors execute.
     *
     * 10. Non-lazy singleton beans are created.
     *
     * 11. Dependencies are resolved and injected.
     *
     * 12. BeanPostProcessors process bean instances.
     *
     * 13. Initialization callbacks execute.
     *
     * 14. Applicable proxies are created or exposed.
     *
     * 15. The context completes refresh and becomes ready.
     *
     * IMPORTANT:
     *
     * This is a simplified conceptual sequence.
     *
     * Actual Spring startup contains additional steps,
     * and some infrastructure registration and post-processing
     * occur at different points during refresh.
     *
     *
     * ================================================================
     * 5. BEAN DEFINITION INTERNALS
     * ================================================================
     *
     * A BeanDefinition describes how Spring should create
     * and configure a bean.
     *
     * It contains metadata such as:
     *
     * - Bean class.
     * - Scope.
     * - Constructor arguments.
     * - Property values.
     * - Lazy initialization setting.
     * - Primary setting.
     * - Factory bean name.
     * - Factory method name.
     * - Dependency information.
     *
     * IMPORTANT DISTINCTION:
     *
     * BeanDefinition is not the actual bean object.
     *
     * BeanDefinition is metadata used by Spring to create
     * and manage the bean.
     *
     *
     * EXAMPLE:
     *
     * @Component
     * public class UserService {
     * }
     *
     * Spring discovers this component and registers
     * corresponding bean-definition metadata.
     *
     * Later, Spring uses that metadata to create the object.
     *
     *
     * BEANDEFINITIONREGISTRY:
     *
     * BeanDefinitionRegistry is responsible for registering
     * and managing bean definitions.
     *
     * It supports operations such as:
     *
     * - Registering a bean definition.
     * - Checking whether a definition exists.
     * - Retrieving a definition.
     * - Removing a definition.
     *
     *
     * ================================================================
     * 6. BEAN CREATION INTERNALS
     * ================================================================
     *
     * Bean creation is more than simply calling new.
     *
     * Spring manages the process.
     *
     * SIMPLIFIED CREATION FLOW:
     *
     * 1. Spring identifies a bean definition.
     *
     * 2. Spring determines the bean's creation strategy.
     *
     * 3. Spring instantiates the bean.
     *
     * 4. Spring populates dependencies.
     *
     * 5. Aware callbacks may execute.
     *
     * 6. BeanPostProcessors process the bean before initialization.
     *
     * 7. Initialization callbacks execute.
     *
     * 8. BeanPostProcessors process the bean after initialization.
     *
     * 9. The final bean reference is made available.
     *
     * 10. For applicable beans, Spring may expose a proxy
     *     rather than the raw target reference.
     *
     *
     * INSTANTIATION:
     *
     * Spring creates the object using an appropriate constructor
     * or factory method.
     *
     *
     * POPULATION:
     *
     * Spring supplies required dependencies and property values.
     *
     *
     * INITIALIZATION:
     *
     * Spring executes initialization callbacks.
     *
     *
     * POST-PROCESSING:
     *
     * Spring allows registered processors to inspect or wrap
     * bean instances.
     *
     *
     * ================================================================
     * 7. DEPENDENCY INJECTION INTERNALS
     * ================================================================
     *
     * Dependency Injection means supplying an object's
     * dependencies from outside instead of making the object
     * create those dependencies itself.
     *
     * Spring resolves dependencies using registered bean
     * definitions and available bean instances.
     *
     *
     * CONSTRUCTOR INJECTION:
     *
     * @Service
     * public class OrderService {
     *
     *     private final PaymentService paymentService;
     *
     *     public OrderService(PaymentService paymentService) {
     *         this.paymentService = paymentService;
     *     }
     * }
     *
     * Spring identifies the constructor dependency and
     * resolves a suitable PaymentService bean.
     *
     *
     * DEPENDENCY RESOLUTION CAN CONSIDER:
     *
     * - Required type.
     * - Qualifier information.
     * - Primary bean designation.
     * - Bean name in applicable resolution scenarios.
     * - Generic type information.
     * - Candidate eligibility.
     *
     *
     * MULTIPLE CANDIDATES:
     *
     * If multiple beans match a dependency and Spring cannot
     * select one unambiguously, dependency resolution fails.
     *
     * Common solutions:
     *
     * - @Primary
     * - @Qualifier
     * - More precise bean configuration
     *
     *
     * ================================================================
     * 8. BEAN LIFECYCLE RECAP
     * ================================================================
     *
     * The Spring bean lifecycle describes how a bean is created,
     * initialized, used and destroyed.
     *
     * SIMPLIFIED ORDER:
     *
     * 1. Instantiation.
     *
     * 2. Dependency population.
     *
     * 3. Aware callbacks.
     *
     * 4. BeanPostProcessor before-initialization callbacks.
     *
     * 5. Initialization callbacks:
     *
     *    - @PostConstruct
     *    - InitializingBean.afterPropertiesSet()
     *    - Custom init method
     *
     * 6. BeanPostProcessor after-initialization callbacks.
     *
     * 7. Bean is ready for use.
     *
     * 8. Destruction callbacks when the container shuts down
     *    or the applicable managed lifecycle ends.
     *
     *
     * IMPORTANT:
     *
     * Exact lifecycle details depend on the bean type,
     * scope and registered infrastructure.
     *
     * Prototype beans are not fully managed through
     * destruction callbacks by the container.
     *
     *
     * ================================================================
     * 9. BEANPOSTPROCESSOR INTERNALS
     * ================================================================
     *
     * BeanPostProcessor allows custom processing of bean
     * instances during bean creation.
     *
     * It provides two main callbacks:
     *
     * postProcessBeforeInitialization()
     *
     * postProcessAfterInitialization()
     *
     *
     * BEFORE INITIALIZATION:
     *
     * The processor receives the bean before initialization
     * callbacks are executed.
     *
     *
     * AFTER INITIALIZATION:
     *
     * The processor receives the bean after initialization
     * callbacks have executed.
     *
     *
     * EXAMPLE:
     *
     * public class CustomBeanPostProcessor
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
     * IMPORTANT:
     *
     * A BeanPostProcessor can return a different object.
     *
     * This is one mechanism used by Spring infrastructure
     * to wrap beans with proxies.
     *
     *
     * ================================================================
     * 10. BEANFACTORYPOSTPROCESSOR INTERNALS
     * ================================================================
     *
     * BeanFactoryPostProcessor works with bean-definition
     * metadata before regular beans are instantiated.
     *
     * It can inspect or modify bean definitions.
     *
     * EXAMPLE:
     *
     * @Component
     * public class CustomFactoryPostProcessor
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
     *
     * IMPORTANT DIFFERENCE:
     *
     * BeanFactoryPostProcessor:
     * Works primarily with bean-definition metadata.
     *
     * BeanPostProcessor:
     * Works with bean instances during their creation.
     *
     *
     * BEANDEFINITIONREGISTRYPOSTPROCESSOR:
     *
     * A specialized extension of BeanFactoryPostProcessor.
     *
     * It can interact with the bean-definition registry
     * and register additional bean definitions.
     *
     *
     * ================================================================
     * 11. APPLICATIONCONTEXT AND ENVIRONMENT
     * ================================================================
     *
     * Spring's Environment abstraction represents the
     * application's environment.
     *
     * It provides access to:
     *
     * - Active profiles.
     * - Default profiles.
     * - Property sources.
     * - Environment-specific configuration.
     *
     *
     * EXAMPLE:
     *
     * @Component
     * public class ApplicationInformation {
     *
     *     private final Environment environment;
     *
     *     public ApplicationInformation(
     *             Environment environment) {
     *
     *         this.environment = environment;
     *     }
     *
     *     public void display() {
     *
     *         String appName =
     *             environment.getProperty("spring.application.name");
     *
     *         System.out.println(appName);
     *     }
     * }
     *
     *
     * PROPERTY SOURCES:
     *
     * Properties can originate from:
     *
     * - Application properties files.
     * - Application YAML files.
     * - Environment variables.
     * - System properties.
     * - Command-line arguments.
     * - Custom property sources.
     *
     * Their precedence depends on the configuration mechanism
     * and property source ordering.
     *
     *
     * PROFILES:
     *
     * Profiles allow environment-specific configuration.
     *
     * Examples:
     *
     * dev
     * test
     * staging
     * prod
     *
     * Example:
     *
     * @Profile("dev")
     * @Component
     * public class DevelopmentService {
     * }
     *
     * This bean is eligible for registration when the dev
     * profile is active.
     *
     *
     * ================================================================
     * 12. COMPONENT SCANNING INTERNALS
     * ================================================================
     *
     * Component scanning allows Spring to discover
     * eligible components automatically.
     *
     * Common annotations:
     *
     * - @Component
     * - @Service
     * - @Repository
     * - @Controller
     * - @RestController
     *
     *
     * EXAMPLE:
     *
     * @ComponentScan("com.example")
     * @Configuration
     * public class AppConfig {
     * }
     *
     *
     * SIMPLIFIED PROCESS:
     *
     * 1. Spring receives package scanning configuration.
     *
     * 2. Spring examines eligible classes in the package.
     *
     * 3. Candidate components are identified.
     *
     * 4. Bean definitions are registered.
     *
     * 5. The container later creates their instances
     *    according to lifecycle rules.
     *
     *
     * IMPORTANT:
     *
     * Component scanning discovers bean definitions.
     *
     * It does not mean every discovered class is immediately
     * instantiated.
     *
     *
     * SPRING BOOT:
     *
     * @SpringBootApplication includes component scanning
     * based on the package of the application class
     * and its subpackages by default.
     *
     * This is why placing the main application class
     * in an appropriate root package is important.
     *
     *
     * ================================================================
     * 13. CONFIGURATION CLASS PROCESSING
     * ================================================================
     *
     * @Configuration classes provide bean definitions
     * through @Bean methods.
     *
     * EXAMPLE:
     *
     * @Configuration
     * public class AppConfig {
     *
     *     @Bean
     *     public PaymentService paymentService() {
     *
     *         return new PaymentService();
     *     }
     * }
     *
     *
     * Spring processes configuration classes to discover
     * and register bean definitions.
     *
     * It also handles configuration-related features such as:
     *
     * - @Bean methods.
     * - @Import.
     * - @ComponentScan.
     * - @PropertySource.
     * - Conditional configuration.
     *
     *
     * FULL CONFIGURATION MODE:
     *
     * A full @Configuration class is enhanced by Spring
     * to preserve container-managed semantics for
     * inter-bean @Bean method calls.
     *
     * Example:
     *
     * @Configuration
     * public class AppConfig {
     *
     *     @Bean
     *     public UserRepository userRepository() {
     *         return new UserRepository();
     *     }
     *
     *     @Bean
     *     public UserService userService() {
     *
     *         return new UserService(userRepository());
     *     }
     * }
     *
     * In full configuration mode, the call to
     * userRepository() is intercepted so that the managed
     * bean is returned.
     *
     *
     * LITE CONFIGURATION MODE:
     *
     * A class containing @Bean methods without full
     * @Configuration enhancement may operate in lite mode.
     *
     * Direct calls between its @Bean methods behave like
     * normal Java method calls.
     *
     * Therefore, they may create separate objects.
     *
     *
     * BEST PRACTICE:
     *
     * Use dependency method parameters in @Bean methods
     * when practical.
     *
     * Example:
     *
     * @Bean
     * public UserService userService(
     *         UserRepository userRepository) {
     *
     *     return new UserService(userRepository);
     * }
     *
     *
     * ================================================================
     * 14. AUTOWIRING INTERNALS
     * ================================================================
     *
     * Spring resolves dependencies using bean metadata,
     * type information and dependency-resolution rules.
     *
     * Constructor injection is generally preferred.
     *
     *
     * EXAMPLE:
     *
     * @Service
     * public class OrderService {
     *
     *     private final PaymentService paymentService;
     *     private final OrderRepository orderRepository;
     *
     *     public OrderService(
     *             PaymentService paymentService,
     *             OrderRepository orderRepository) {
     *
     *         this.paymentService = paymentService;
     *         this.orderRepository = orderRepository;
     *     }
     * }
     *
     *
     * WHY CONSTRUCTOR INJECTION?
     *
     * - Dependencies are explicit.
     * - Required dependencies are available at construction.
     * - Fields can be final.
     * - Objects are easier to unit test.
     * - Invalid partially initialized objects are avoided.
     *
     *
     * FIELD INJECTION:
     *
     * @Autowired
     * private PaymentService paymentService;
     *
     * It is supported, but generally discouraged for
     * application service design.
     *
     *
     * SETTER INJECTION:
     *
     * Useful when a dependency is optional or can be changed
     * after object construction.
     *
     *
     * MULTIPLE IMPLEMENTATIONS:
     *
     * @Primary and @Qualifier help select the correct bean.
     *
     *
     * ================================================================
     * 15. CIRCULAR DEPENDENCIES
     * ================================================================
     *
     * A circular dependency occurs when two or more beans
     * depend on one another in a cycle.
     *
     * EXAMPLE:
     *
     * UserService depends on OrderService.
     *
     * OrderService depends on UserService.
     *
     * This creates:
     *
     * UserService -> OrderService -> UserService
     *
     *
     * WHY IS IT A PROBLEM?
     *
     * Spring may be unable to complete the creation of
     * the required beans.
     *
     * Constructor-based circular dependencies generally
     * cannot be resolved by the standard container mechanism.
     *
     * Some circular references involving setter or field
     * injection may be resolvable under certain configurations,
     * but relying on them is not recommended.
     *
     *
     * POSSIBLE SOLUTIONS:
     *
     * 1. Redesign responsibilities.
     *
     * 2. Introduce a third service to coordinate shared behavior.
     *
     * 3. Remove unnecessary bidirectional dependencies.
     *
     * 4. Use events when appropriate for decoupled communication.
     *
     * 5. Use ObjectProvider or another lazy-resolution approach
     *    only when the dependency is genuinely needed lazily.
     *
     *
     * BEST PRACTICE:
     *
     * Treat circular dependencies as a design warning.
     *
     *
     * ================================================================
     * 16. PROXY CREATION AND AOP INTEGRATION
     * ================================================================
     *
     * Spring can create proxies to apply cross-cutting behavior.
     *
     * Examples:
     *
     * - AOP advice.
     * - Transaction management.
     * - Method security.
     * - Caching.
     *
     *
     * TWO COMMON PROXY TYPES:
     *
     * JDK DYNAMIC PROXY:
     *
     * Creates a proxy implementing interfaces.
     *
     *
     * CGLIB PROXY:
     *
     * Creates a subclass-based proxy.
     *
     *
     * GENERAL FLOW:
     *
     * Client
     *   |
     *   v
     * Proxy
     *   |
     *   v
     * Interceptor Chain
     *   |
     *   v
     * Target Method
     *
     *
     * IMPORTANT:
     *
     * The injected reference may be a proxy rather than
     * the raw target object.
     *
     * This is why a bean's runtime class may not always
     * be the class originally declared by the application.
     *
     *
     * PROXY LIMITATIONS:
     *
     * - Self-invocation generally bypasses proxy advice.
     * - Private methods cannot be intercepted through
     *   normal Spring proxy-based AOP.
     * - Static methods are not intercepted by proxies.
     * - Final methods cannot be overridden by class-based proxies.
     * - Final classes cannot be subclassed by class-based proxies.
     *
     *
     * ================================================================
     * 17. SPRING EVENTS INTERNALS
     * ================================================================
     *
     * Spring provides an event mechanism for communication
     * between application components.
     *
     * IMPORTANT COMPONENTS:
     *
     * - ApplicationEventPublisher
     * - ApplicationEvent
     * - ApplicationListener
     * - @EventListener
     *
     *
     * EXAMPLE:
     *
     * public record UserRegisteredEvent(
     *         String email) {
     * }
     *
     *
     * @Service
     * public class UserService {
     *
     *     private final ApplicationEventPublisher publisher;
     *
     *     public UserService(
     *             ApplicationEventPublisher publisher) {
     *
     *         this.publisher = publisher;
     *     }
     *
     *     public void registerUser(String email) {
     *
     *         // Registration logic
     *
     *         publisher.publishEvent(
     *             new UserRegisteredEvent(email)
     *         );
     *     }
     * }
     *
     *
     * @Component
     * public class UserEventListener {
     *
     *     @EventListener
     *     public void handle(
     *             UserRegisteredEvent event) {
     *
     *         System.out.println(
     *             "Registered: " + event.email()
     *         );
     *     }
     * }
     *
     *
     * DEFAULT BEHAVIOR:
     *
     * Spring application events are normally delivered
     * synchronously using the configured event multicaster.
     *
     * The listener generally executes in the publisher's
     * calling thread unless asynchronous execution is configured.
     *
     *
     * ASYNCHRONOUS LISTENERS:
     *
     * @Async can be used with event listeners when
     * asynchronous execution is configured.
     *
     * IMPORTANT:
     *
     * Spring application events are in-process events.
     *
     * They are not automatically durable messages and do not
     * replace messaging systems such as Kafka or RabbitMQ.
     *
     *
     * ================================================================
     * 18. RESOURCE MANAGEMENT
     * ================================================================
     *
     * Spring provides the Resource abstraction for accessing
     * external resources.
     *
     * COMMON IMPLEMENTATIONS:
     *
     * - ClassPathResource
     * - FileSystemResource
     * - UrlResource
     * - ByteArrayResource
     * - InputStreamResource
     *
     *
     * RESOURCELOADER:
     *
     * ResourceLoader loads resources using resource locations.
     *
     * EXAMPLE:
     *
     * @Component
     * public class FileService {
     *
     *     private final ResourceLoader resourceLoader;
     *
     *     public FileService(
     *             ResourceLoader resourceLoader) {
     *
     *         this.resourceLoader = resourceLoader;
     *     }
     *
     *     public void load() {
     *
     *         Resource resource =
     *             resourceLoader.getResource(
     *                 "classpath:application.properties"
     *             );
     *     }
     * }
     *
     *
     * IMPORTANT:
     *
     * Resource abstracts the way an application accesses
     * a resource.
     *
     * It does not mean every resource is loaded into memory
     * immediately.
     *
     *
     * ================================================================
     * 19. EXCEPTION HANDLING IN THE CONTAINER
     * ================================================================
     *
     * Spring provides exceptions that help identify
     * container and bean-creation problems.
     *
     * COMMON EXCEPTIONS:
     *
     * 1. NoSuchBeanDefinitionException
     *
     *    No matching bean definition was found.
     *
     *
     * 2. NoUniqueBeanDefinitionException
     *
     *    Multiple matching beans exist and Spring cannot
     *    choose one unambiguously.
     *
     *
     * 3. BeanCreationException
     *
     *    A bean could not be created successfully.
     *
     *
     * 4. BeanCurrentlyInCreationException
     *
     *    A bean is currently being created and a circular
     *    creation dependency may be involved.
     *
     *
     * 5. UnsatisfiedDependencyException
     *
     *    A required dependency could not be satisfied.
     *
     *
     * 6. BeanDefinitionOverrideException
     *
     *    A bean definition conflicts with an existing
     *    definition when overriding is not allowed.
     *
     *
     * DEBUGGING APPROACH:
     *
     * 1. Read the complete exception message.
     *
     * 2. Identify the first relevant application class
     *    in the stack trace.
     *
     * 3. Check the bean name and type.
     *
     * 4. Check component scanning and configuration.
     *
     * 5. Check constructor dependencies.
     *
     * 6. Check profiles and property values.
     *
     * 7. Inspect the root cause rather than only
     *    the outer exception.
     *
     *
     * ================================================================
     * 20. DEBUGGING SPRING APPLICATIONS
     * ================================================================
     *
     * Debugging Spring requires understanding how beans
     * are discovered, created, injected and proxied.
     *
     *
     * PROBLEM 1:
     *
     * NoSuchBeanDefinitionException
     *
     * CHECK:
     *
     * - Is the class registered as a bean?
     * - Is component scanning configured correctly?
     * - Is the bean excluded by a profile or condition?
     * - Is the required configuration imported?
     *
     *
     * PROBLEM 2:
     *
     * NoUniqueBeanDefinitionException
     *
     * CHECK:
     *
     * - Are multiple implementations registered?
     * - Should @Primary be used?
     * - Should @Qualifier be used?
     *
     *
     * PROBLEM 3:
     *
     * UnsatisfiedDependencyException
     *
     * CHECK:
     *
     * - Does the required bean exist?
     * - Can Spring resolve its constructor arguments?
     * - Does the dependency itself fail during creation?
     *
     *
     * PROBLEM 4:
     *
     * AOP ADVICE IS NOT EXECUTING
     *
     * CHECK:
     *
     * - Is the aspect registered as a Spring bean?
     * - Is AOP infrastructure configured?
     * - Does the pointcut match the method?
     * - Is the call going through the proxy?
     * - Is self-invocation bypassing the proxy?
     * - Is the method eligible for proxy interception?
     *
     *
     * PROBLEM 5:
     *
     * WRONG CONFIGURATION IS LOADED
     *
     * CHECK:
     *
     * - Which profiles are active?
     * - Which property source provides the value?
     * - Is the property name correct?
     * - Is the configuration class discovered?
     *
     *
     * USEFUL DEBUGGING TOOLS:
     *
     * - IDE debugger and breakpoints.
     * - Application logs.
     * - Spring Boot condition evaluation report.
     * - Actuator beans endpoint, when enabled and secured.
     * - Actuator conditions endpoint, when enabled and secured.
     * - BeanFactory and ApplicationContext inspection.
     *
     *
     * AOP INSPECTION:
     *
     * AopUtils.isAopProxy(bean)
     *
     * AopUtils.isJdkDynamicProxy(bean)
     *
     * AopUtils.isCglibProxy(bean)
     *
     * These methods help inspect whether an object is
     * a Spring AOP proxy and identify its proxy type.
     *
     *
     * ================================================================
     * 21. SPRING CORE BEST PRACTICES
     * ================================================================
     *
     * 1. PREFER CONSTRUCTOR INJECTION
     *
     * Use constructor injection for required dependencies.
     *
     * Benefits:
     *
     * - Explicit dependencies.
     * - Better testability.
     * - Immutable fields.
     * - Easier object construction.
     *
     *
     * 2. PROGRAM TO INTERFACES WHEN APPROPRIATE
     *
     * Depend on suitable abstractions rather than concrete
     * implementations when this improves flexibility.
     *
     * Avoid creating interfaces without a meaningful reason.
     *
     *
     * 3. KEEP BEANS FOCUSED
     *
     * A service should have a clear responsibility.
     *
     * Avoid large classes that perform unrelated operations.
     *
     *
     * 4. PREFER IMMUTABLE DEPENDENCIES
     *
     * Use final fields with constructor injection
     * wherever practical.
     *
     *
     * 5. AVOID FIELD INJECTION
     *
     * It hides dependencies and makes ordinary unit testing
     * more difficult.
     *
     *
     * 6. AVOID CIRCULAR DEPENDENCIES
     *
     * Reconsider class responsibilities when circular
     * dependencies appear.
     *
     *
     * 7. USE PROFILES FOR ENVIRONMENT CONFIGURATION
     *
     * Separate development, testing and production settings.
     *
     *
     * 8. USE CONFIGURATION PROPERTIES FOR RELATED SETTINGS
     *
     * Group related application configuration rather than
     * scattering many unrelated @Value fields.
     *
     *
     * 9. USE SINGLETON BEANS CAREFULLY
     *
     * Singleton beans are shared.
     *
     * Avoid mutable request-specific state in singleton services.
     *
     *
     * 10. KEEP BUSINESS LOGIC OUT OF CONFIGURATION CLASSES
     *
     * Configuration classes should primarily define
     * application wiring.
     *
     *
     * 11. USE AOP FOR GENUINELY CROSS-CUTTING BEHAVIOR
     *
     * Do not use aspects to hide ordinary business logic.
     *
     *
     * 12. USE SPRING EVENTS WHEN THEY IMPROVE DECOUPLING
     *
     * Do not introduce events when a simple direct dependency
     * is clearer.
     *
     *
     * 13. USE LAZY INITIALIZATION SELECTIVELY
     *
     * Lazy initialization can reduce startup work, but it
     * can also postpone failures until a bean is first used.
     *
     *
     * 14. HANDLE RESOURCE LIFECYCLES CAREFULLY
     *
     * Close resources and manage external connections
     * through suitable lifecycle mechanisms.
     *
     *
     * 15. WRITE UNIT TESTS
     *
     * Keep business logic testable independently of
     * the Spring container wherever practical.
     *
     *
     * ================================================================
     * 22. COMMON DESIGN MISTAKES
     * ================================================================
     *
     * MISTAKE 1:
     *
     * Creating objects manually with new when they need
     * Spring-managed dependencies or infrastructure.
     *
     * CORRECTION:
     *
     * Register and inject Spring-managed beans when
     * container management is required.
     *
     *
     * MISTAKE 2:
     *
     * Putting every class in one package without considering
     * component scanning boundaries.
     *
     * CORRECTION:
     *
     * Use a clear package structure with an appropriate
     * root application package.
     *
     *
     * MISTAKE 3:
     *
     * Making all methods and classes depend directly on
     * concrete implementations.
     *
     * CORRECTION:
     *
     * Use appropriate abstractions and constructor injection.
     *
     *
     * MISTAKE 4:
     *
     * Storing user-specific or request-specific mutable state
     * in singleton service fields.
     *
     * CORRECTION:
     *
     * Keep singleton services stateless where practical.
     *
     *
     * MISTAKE 5:
     *
     * Using @Lazy to hide a circular dependency.
     *
     * CORRECTION:
     *
     * Fix the underlying design when possible.
     *
     *
     * MISTAKE 6:
     *
     * Expecting AOP to intercept every internal method call.
     *
     * CORRECTION:
     *
     * Understand proxy boundaries and self-invocation.
     *
     *
     * MISTAKE 7:
     *
     * Putting database access, business logic and configuration
     * in a single class.
     *
     * CORRECTION:
     *
     * Separate responsibilities into appropriate layers.
     *
     *
     * ================================================================
     * 23. PRODUCTION CONSIDERATIONS
     * ================================================================
     *
     * Spring Core concepts directly affect production systems.
     *
     *
     * A. BEAN THREAD SAFETY
     *
     * Singleton beans are shared across requests.
     *
     * Therefore, mutable shared state can introduce
     * concurrency problems.
     *
     * Prefer stateless service classes.
     *
     *
     * B. STARTUP TIME
     *
     * A large application may create many singleton beans
     * during startup.
     *
     * Avoid unnecessary beans and expensive initialization.
     *
     *
     * C. CONFIGURATION MANAGEMENT
     *
     * Keep environment-specific settings outside source code.
     *
     * Use suitable configuration sources and secret management.
     *
     *
     * D. OBSERVABILITY
     *
     * Use appropriate logging, metrics and tracing
     * mechanisms to understand application behavior.
     *
     *
     * E. RESOURCE MANAGEMENT
     *
     * Database connections, files and external resources
     * must have clear ownership and lifecycle management.
     *
     *
     * F. SECURITY
     *
     * Do not expose internal container information or
     * management endpoints publicly without appropriate controls.
     *
     *
     * G. TESTABILITY
     *
     * Keep application logic independent of unnecessary
     * container-specific details.
     *
     *
     * ================================================================
     * 24. INTERVIEW QUESTIONS
     * ================================================================
     *
     * BASIC:
     *
     * 1. What is the Spring IoC Container?
     * 2. What is the difference between BeanFactory
     *    and ApplicationContext?
     * 3. What is a BeanDefinition?
     * 4. What happens when ApplicationContext starts?
     * 5. What is component scanning?
     * 6. What is the bean lifecycle?
     * 7. What is dependency resolution?
     * 8. What is BeanPostProcessor?
     * 9. What is BeanFactoryPostProcessor?
     * 10. What is the Environment abstraction?
     *
     *
     * INTERMEDIATE:
     *
     * 11. How does Spring create a bean?
     * 12. How does constructor injection work?
     * 13. How does Spring resolve multiple matching beans?
     * 14. What is the difference between BeanDefinition
     *     and a bean instance?
     * 15. What happens during context refresh?
     * 16. How does Spring process @Configuration classes?
     * 17. What is the difference between full and lite
     *     configuration mode?
     * 18. How do profiles affect bean registration?
     * 19. How does Spring handle circular dependencies?
     * 20. Why is constructor injection preferred?
     *
     *
     * ADVANCED:
     *
     * 21. Explain the internal bean creation lifecycle.
     * 22. What is the difference between BeanFactoryPostProcessor
     *     and BeanPostProcessor?
     * 23. How are Spring AOP proxies created?
     * 24. Why does self-invocation bypass proxy-based AOP?
     * 25. How does Spring resolve constructor dependencies?
     * 26. How do configuration classes get enhanced?
     * 27. How do application events work?
     * 28. Why are singleton beans not automatically thread-safe?
     * 29. How would you debug a missing bean?
     * 30. How would you investigate an unexpected proxy?
     *
     *
     * ================================================================
     * 25. PRACTICE EXERCISES
     * ================================================================
     *
     * EXERCISE 1:
     *
     * Create a Spring application containing three services.
     * Observe how Spring creates and injects their dependencies.
     *
     *
     * EXERCISE 2:
     *
     * Implement a custom BeanPostProcessor.
     * Print messages before and after initialization.
     *
     *
     * EXERCISE 3:
     *
     * Implement a BeanFactoryPostProcessor.
     * Observe when it executes relative to regular bean creation.
     *
     *
     * EXERCISE 4:
     *
     * Create two implementations of one interface.
     * Resolve them using @Primary and @Qualifier.
     *
     *
     * EXERCISE 5:
     *
     * Create development and production profiles.
     * Register different implementations using @Profile.
     *
     *
     * EXERCISE 6:
     *
     * Create a circular dependency between two services.
     * Observe the startup failure and redesign the dependency.
     *
     *
     * EXERCISE 7:
     *
     * Create a service with an AOP aspect.
     * Inspect whether the injected object is a proxy.
     *
     *
     * EXERCISE 8:
     *
     * Publish a custom application event and handle it
     * using @EventListener.
     *
     *
     * EXERCISE 9:
     *
     * Create a singleton service with mutable shared state.
     * Understand the concurrency risk and redesign it.
     *
     *
     * EXERCISE 10:
     *
     * Debug a Spring application containing:
     *
     * - A missing bean.
     * - Multiple implementations of one interface.
     * - An invalid profile.
     * - An AOP pointcut that does not match.
     *
     *
     * ================================================================
     * 26. QUICK REVISION
     * ================================================================
     *
     * IOC CONTAINER:
     * Creates and manages Spring beans.
     *
     * APPLICATIONCONTEXT:
     * Advanced container interface built on BeanFactory.
     *
     * BEANDEFINITION:
     * Metadata describing how to create and configure a bean.
     *
     * BEANDEFINITIONREGISTRY:
     * Registers and manages bean definitions.
     *
     * BEANFACTORYPOSTPROCESSOR:
     * Processes bean-definition metadata.
     *
     * BEANPOSTPROCESSOR:
     * Processes bean instances during creation.
     *
     * DEPENDENCY INJECTION:
     * Supplies dependencies to objects from outside.
     *
     * COMPONENT SCANNING:
     * Discovers eligible components and registers definitions.
     *
     * CONFIGURATION CLASS:
     * Defines application configuration and bean methods.
     *
     * ENVIRONMENT:
     * Provides access to profiles and properties.
     *
     * APPLICATION EVENT:
     * Provides in-process communication between components.
     *
     * AOP PROXY:
     * Intercepts eligible method calls to apply additional behavior.
     *
     * CONSTRUCTOR INJECTION:
     * Preferred approach for required dependencies.
     *
     * SINGLETON BEAN:
     * One managed instance per bean definition per container.
     *
     * CIRCULAR DEPENDENCY:
     * A dependency cycle between beans.
     *
     * BEST PRACTICE:
     * Keep beans focused, dependencies explicit and services
     * stateless wherever practical.
     *
     *
     * ================================================================
     * SPRING CORE COMPLETION
     * ================================================================
     *
     * You have completed the eight Spring Core topics:
     *
     * 01. Introduction to Spring
     * 02. IoC and Dependency Injection
     * 03. Spring Container and Beans
     * 04. Bean Configuration and Autowiring
     * 05. Bean Lifecycle and Scopes
     * 06. Spring Advanced Container Features
     * 07. Spring AOP
     * 08. Spring Core Internals and Best Practices
     *
     * You now have the foundational Spring Core knowledge
     * needed to understand how Spring manages application
     * objects and provides its core infrastructure.
     *
     * NEXT FOLDER:
     *
     * 02-Spring-MVC
     *
     * ================================================================
     * END OF SPRING CORE
     * ================================================================
     */

    public static void main(String[] args) {

        System.out.println(
            "Spring Core - Internals and Best Practices"
        );

    }

}
