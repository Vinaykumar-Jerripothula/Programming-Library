
public class BeanConfigurationAndAutowiring {

    /*
     * ================================================================
     * SPRING CORE - 04: BEAN CONFIGURATION AND AUTOWIRING
     * ================================================================
     *
     * CONTENTS
     *
     * 1.  Introduction to Bean Configuration
     * 2.  Bean Configuration Approaches
     * 3.  Java-Based Configuration
     * 4.  @Configuration
     * 5.  @Bean
     * 6.  Bean Factory Methods and Dependency Resolution
     * 7.  Full and Lite Configuration
     * 8.  Component Scanning
     * 9.  Stereotype Annotations
     * 10. @ComponentScan
     * 11. @Import and @ImportResource
     * 12. Autowiring
     * 13. Types of Dependency Injection
     * 14. @Autowired
     * 15. @Qualifier and @Primary
     * 16. @Resource and @Inject
     * 17. Optional Dependencies
     * 18. Collection and Map Injection
     * 19. ObjectProvider
     * 20. @Value and Property Injection
     * 21. Environment and Profiles
     * 22. Conditional Bean Registration
     * 23. Bean Naming and Conflicts
     * 24. Common Configuration Exceptions
     * 25. Best Practices
     * 26. Real-World Scenarios
     * 27. Interview Questions
     * 28. Practice Exercises
     * 29. Quick Revision
     *
     *
     * ================================================================
     * 1. INTRODUCTION TO BEAN CONFIGURATION
     * ================================================================
     *
     * Bean configuration means providing Spring with information
     * about the objects that it should manage and how they should
     * be created and connected.
     *
     * Spring needs to know:
     *
     * - Which classes should become Spring beans.
     * - How objects should be instantiated.
     * - What dependencies each object requires.
     * - How dependencies should be injected.
     * - Under which conditions a bean should be registered.
     *
     * Consider:
     *
     *       OrderController
     *              |
     *              v
     *         OrderService
     *              |
     *              v
     *        OrderRepository
     *
     * All three objects can be managed by Spring.
     *
     * Bean configuration tells Spring how these objects should
     * be registered and connected.
     *
     * AUTOWIRING:
     *
     * Autowiring is Spring's mechanism for resolving and supplying
     * dependencies to managed objects.
     *
     * Bean configuration defines the beans and their metadata.
     * Dependency injection supplies the required dependencies.
     *
     *
     * ================================================================
     * 2. BEAN CONFIGURATION APPROACHES
     * ================================================================
     *
     * Spring supports four commonly discussed configuration
     * approaches:
     *
     * 1. XML-based configuration
     * 2. Annotation-based configuration
     * 3. Java-based configuration
     * 4. Programmatic configuration
     *
     *
     * A. XML-BASED CONFIGURATION
     *
     * Beans are declared in XML configuration files.
     *
     * Example:
     *
     * <beans>
     *
     *     <bean id="emailService"
     *           class="com.example.EmailService"/>
     *
     *     <bean id="notificationService"
     *           class="com.example.NotificationService">
     *
     *         <constructor-arg ref="emailService"/>
     *
     *     </bean>
     *
     * </beans>
     *
     * Spring reads the XML file and registers the bean definitions.
     *
     * Advantages:
     * - Configuration is separated from Java source code.
     * - Useful in older Spring applications.
     *
     * Disadvantages:
     * - More verbose.
     * - Class and dependency information is expressed as strings.
     * - Refactoring can be harder.
     *
     * XML configuration is still supported, but modern Spring
     * applications generally prefer annotations and Java configuration.
     *
     *
     * B. ANNOTATION-BASED CONFIGURATION
     *
     * Annotations identify classes that Spring should manage.
     *
     * Example:
     *
     * @Component
     * public class EmailService {
     * }
     *
     * With component scanning enabled, Spring discovers this class
     * and registers it as a bean.
     *
     *
     * C. JAVA-BASED CONFIGURATION
     *
     * Java classes and methods define Spring beans.
     *
     * Example:
     *
     * @Configuration
     * public class AppConfig {
     *
     *     @Bean
     *     public EmailService emailService() {
     *         return new EmailService();
     *     }
     * }
     *
     * Useful when:
     * - Creating objects from third-party libraries.
     * - Configuring classes that cannot be annotated.
     * - Explicitly controlling object creation.
     * - Defining application-level configuration.
     *
     *
     * D. PROGRAMMATIC CONFIGURATION
     *
     * Beans can also be registered using Spring APIs.
     *
     * Example:
     *
     * GenericApplicationContext context =
     *         new GenericApplicationContext();
     *
     * context.registerBean(
     *         EmailService.class,
     *         EmailService::new
     * );
     *
     * context.refresh();
     *
     * EmailService service =
     *         context.getBean(EmailService.class);
     *
     * This approach is more common in advanced framework
     * development and dynamic configuration.
     *
     *
     * ================================================================
     * 3. JAVA-BASED CONFIGURATION
     * ================================================================
     *
     * Java-based configuration uses Java classes and annotations
     * to define beans.
     *
     * Important annotations:
     *
     * - @Configuration
     * - @Bean
     * - @Import
     * - @ImportResource
     *
     * Example:
     *
     * @Configuration
     * public class AppConfig {
     *
     *     @Bean
     *     public EmailService emailService() {
     *         return new EmailService();
     *     }
     *
     *     @Bean
     *     public NotificationService notificationService(
     *             EmailService emailService) {
     *
     *         return new NotificationService(emailService);
     *     }
     * }
     *
     * Here:
     *
     * - AppConfig is a configuration class.
     * - emailService() declares an EmailService bean.
     * - notificationService() declares a NotificationService bean.
     * - Spring resolves the EmailService parameter.
     * - Spring injects the dependency while creating the bean.
     *
     * This is constructor-based dependency injection through
     * a @Bean factory method.
     *
     *
     * ================================================================
     * 4. @CONFIGURATION ANNOTATION
     * ================================================================
     *
     * @Configuration identifies a class as a source of bean
     * definitions.
     *
     * Example:
     *
     * @Configuration
     * public class DatabaseConfig {
     *
     *     @Bean
     *     public DatabaseConnection databaseConnection() {
     *         return new DatabaseConnection();
     *     }
     * }
     *
     * Spring processes the configuration class and registers
     * the beans declared by its @Bean methods.
     *
     * IMPORTANT:
     *
     * @Configuration is itself a specialized form of @Component.
     *
     * Therefore, configuration classes can be discovered through
     * component scanning.
     *
     * Configuration classes can also import other configuration
     * classes.
     *
     *
     * ================================================================
     * 5. @BEAN ANNOTATION
     * ================================================================
     *
     * @Bean is used on a method to declare the returned object
     * as a Spring-managed bean.
     *
     * Example:
     *
     * @Configuration
     * public class AppConfig {
     *
     *     @Bean
     *     public EmailService emailService() {
     *         return new EmailService();
     *     }
     * }
     *
     * Spring calls the factory method and manages its returned object.
     *
     * DEFAULT BEAN NAME:
     *
     * The method name becomes the default bean name.
     *
     * In this example:
     *
     * Bean name = emailService
     *
     * CUSTOM BEAN NAME:
     *
     * @Bean(name = "primaryEmailService")
     * public EmailService emailService() {
     *     return new EmailService();
     * }
     *
     * Multiple names can be specified:
     *
     * @Bean({"emailService", "mailService"})
     * public EmailService createEmailService() {
     *     return new EmailService();
     * }
     *
     * The first name is the bean name; the remaining names
     * are aliases.
     *
     * OTHER BEAN METADATA:
     *
     * @Bean(initMethod = "initialize",
     *       destroyMethod = "close")
     * public DatabaseConnection databaseConnection() {
     *     return new DatabaseConnection();
     * }
     *
     * @Bean can also be combined with scope and lazy initialization
     * annotations.
     *
     * Example:
     *
     * @Bean
     * @Lazy
     * @Scope("prototype")
     * public ReportGenerator reportGenerator() {
     *     return new ReportGenerator();
     * }
     *
     * IMPORTANT:
     *
     * @Bean methods are commonly declared in configuration classes.
     * They can also be declared in certain component classes.
     *
     *
     * ================================================================
     * 6. BEAN FACTORY METHODS AND DEPENDENCY RESOLUTION
     * ================================================================
     *
     * A method annotated with @Bean is called a factory method.
     *
     * It creates and returns an object that Spring manages.
     *
     * Example:
     *
     * @Configuration
     * public class AppConfig {
     *
     *     @Bean
     *     public EmailService emailService() {
     *         return new EmailService();
     *     }
     *
     *     @Bean
     *     public NotificationService notificationService(
     *             EmailService emailService) {
     *
     *         return new NotificationService(emailService);
     *     }
     * }
     *
     * Spring resolves the EmailService parameter and supplies
     * the matching bean.
     *
     * This is preferable to:
     *
     * @Bean
     * public NotificationService notificationService() {
     *     return new NotificationService(new EmailService());
     * }
     *
     * WHY?
     *
     * In the first approach:
     *
     * - Spring resolves the dependency.
     * - The dependency can be managed independently.
     * - Its configured scope is respected.
     * - Dependency relationships are explicit.
     *
     * A @Bean method parameter is a dependency injection point.
     *
     *
     * ================================================================
     * 7. FULL AND LITE CONFIGURATION
     * ================================================================
     *
     * This is an important distinction when working with @Bean methods.
     *
     *
     * A. FULL CONFIGURATION MODE
     *
     * A class annotated with @Configuration uses full configuration
     * processing by default.
     *
     * Spring enhances the configuration class using a proxy mechanism.
     *
     * Example:
     *
     * @Configuration
     * public class AppConfig {
     *
     *     @Bean
     *     public EmailService emailService() {
     *         return new EmailService();
     *     }
     *
     *     @Bean
     *     public NotificationService notificationService() {
     *         return new NotificationService(emailService());
     *     }
     * }
     *
     * Here, notificationService() directly calls emailService().
     *
     * In full configuration mode, Spring intercepts this call
     * and returns the managed bean rather than creating another
     * singleton instance through a normal method call.
     *
     *
     * B. LITE CONFIGURATION MODE
     *
     * Lite mode applies when @Bean methods are declared in classes
     * that are not processed as full @Configuration classes.
     *
     * Example:
     *
     * @Configuration(proxyBeanMethods = false)
     * public class AppConfig {
     *
     *     @Bean
     *     public EmailService emailService() {
     *         return new EmailService();
     *     }
     *
     *     @Bean
     *     public NotificationService notificationService() {
     *         return new NotificationService(emailService());
     *     }
     * }
     *
     * Here, direct calls between @Bean methods follow normal
     * Java method-call semantics.
     *
     * Calling emailService() directly creates another object.
     * It does not automatically retrieve the managed singleton bean.
     *
     *
     * RECOMMENDED APPROACH:
     *
     * @Configuration(proxyBeanMethods = false)
     * public class AppConfig {
     *
     *     @Bean
     *     public EmailService emailService() {
     *         return new EmailService();
     *     }
     *
     *     @Bean
     *     public NotificationService notificationService(
     *             EmailService emailService) {
     *
     *         return new NotificationService(emailService);
     *     }
     * }
     *
     * Method parameter injection works correctly in both
     * full and lite configuration modes.
     *
     * INTERVIEW POINT:
     *
     * Full mode:
     * Direct @Bean method calls are intercepted.
     *
     * Lite mode:
     * Direct @Bean method calls use normal Java semantics.
     *
     *
     * ================================================================
     * 8. COMPONENT SCANNING
     * ================================================================
     *
     * Component scanning allows Spring to automatically discover
     * eligible components from specified packages.
     *
     * Example:
     *
     * @Component
     * public class EmailService {
     * }
     *
     * @Component
     * public class NotificationService {
     * }
     *
     * When their package is scanned, Spring discovers these classes
     * and registers them as beans.
     *
     * GENERAL PROCESS:
     *
     * 1. Spring receives the component scan configuration.
     * 2. It searches the specified packages.
     * 3. It identifies eligible annotated classes.
     * 4. It registers bean definitions for them.
     * 5. Their dependencies are resolved during bean creation.
     *
     * Component scanning reduces the need for manually declaring
     * every application component.
     *
     *
     * ================================================================
     * 9. STEREOTYPE ANNOTATIONS
     * ================================================================
     *
     * Stereotype annotations identify classes that have specific
     * application responsibilities.
     *
     *
     * A. @COMPONENT
     *
     * A general-purpose annotation indicating that a class
     * is a Spring-managed component.
     *
     * Example:
     *
     * @Component
     * public class EmailService {
     * }
     *
     *
     * B. @SERVICE
     *
     * Identifies the service or business logic layer.
     *
     * Example:
     *
     * @Service
     * public class PaymentService {
     * }
     *
     *
     * C. @REPOSITORY
     *
     * Identifies the persistence or data access layer.
     *
     * Example:
     *
     * @Repository
     * public class UserRepository {
     * }
     *
     * It also participates in Spring's persistence exception
     * translation infrastructure when the relevant integration
     * and post-processors are configured.
     *
     *
     * D. @CONTROLLER
     *
     * Identifies a Spring MVC controller.
     *
     * Example:
     *
     * @Controller
     * public class HomeController {
     * }
     *
     *
     * E. @RESTCONTROLLER
     *
     * Combines:
     *
     * @Controller
     * @ResponseBody
     *
     * It is commonly used for REST APIs.
     *
     * Example:
     *
     * @RestController
     * public class UserController {
     * }
     *
     *
     * RELATIONSHIP:
     *
     * @Component
     *      |
     *      |---- @Service
     *      |
     *      |---- @Repository
     *      |
     *      |---- @Controller
     *
     * @RestController is composed of @Controller and @ResponseBody.
     *
     *
     * WHY USE DIFFERENT STEREOTYPES?
     *
     * - They communicate class responsibility.
     * - They improve application organization.
     * - They make code easier to understand.
     * - Some stereotypes provide additional framework semantics.
     *
     *
     * ================================================================
     * 10. @COMPONENTSCAN
     * ================================================================
     *
     * @ComponentScan tells Spring where to search for components.
     *
     * Example:
     *
     * @Configuration
     * @ComponentScan(basePackages = "com.example")
     * public class AppConfig {
     * }
     *
     * Spring scans com.example and its subpackages.
     *
     * MULTIPLE PACKAGES:
     *
     * @ComponentScan(
     *     basePackages = {
     *         "com.example.service",
     *         "com.example.repository"
     *     }
     * )
     *
     * TYPE-SAFE SCANNING:
     *
     * @ComponentScan(basePackageClasses = {
     *     UserService.class,
     *     UserRepository.class
     * })
     *
     * This avoids package names as strings.
     *
     *
     * DEFAULT SCANNING BEHAVIOR:
     *
     * If no package is specified, Spring scans the package
     * containing the configuration class and its subpackages.
     *
     *
     * INCLUDE AND EXCLUDE FILTERS:
     *
     * Component scanning supports filters to control which
     * classes should be included or excluded.
     *
     * Common filter types:
     *
     * - ANNOTATION
     * - ASSIGNABLE_TYPE
     * - ASPECTJ
     * - REGEX
     * - CUSTOM
     *
     * Example:
     *
     * @ComponentScan(
     *     basePackages = "com.example",
     *     excludeFilters = @ComponentScan.Filter(
     *         type = FilterType.ANNOTATION,
     *         classes = DeprecatedComponent.class
     *     )
     * )
     *
     *
     * SPRING BOOT COMPONENT SCANNING:
     *
     * @SpringBootApplication includes @ComponentScan.
     *
     * By default, scanning starts from the package containing
     * the main application class and continues into subpackages.
     *
     * Recommended structure:
     *
     * com.example.application
     *       |
     *       |---- Application.java
     *       |
     *       |---- controller
     *       |
     *       |---- service
     *       |
     *       |---- repository
     *
     * Placing the main application class in the root package
     * makes default scanning easier to manage.
     *
     *
     * ================================================================
     * 11. @IMPORT AND @IMPORTRESOURCE
     * ================================================================
     *
     * A. @IMPORT
     *
     * Imports another configuration class.
     *
     * Example:
     *
     * @Configuration
     * public class DatabaseConfig {
     *
     *     @Bean
     *     public DatabaseConnection databaseConnection() {
     *         return new DatabaseConnection();
     *     }
     * }
     *
     * @Configuration
     * @Import(DatabaseConfig.class)
     * public class AppConfig {
     * }
     *
     * AppConfig includes the configuration provided by
     * DatabaseConfig.
     *
     * Multiple classes can be imported:
     *
     * @Import({
     *     DatabaseConfig.class,
     *     SecurityConfig.class
     * })
     *
     * Useful for modular configuration.
     *
     *
     * B. @IMPORTRESOURCE
     *
     * Imports XML-based Spring configuration.
     *
     * Example:
     *
     * @Configuration
     * @ImportResource("classpath:legacy-config.xml")
     * public class AppConfig {
     * }
     *
     * Useful when integrating older XML configurations
     * with Java-based configuration.
     *
     *
     * ================================================================
     * 12. AUTOWIRING
     * ================================================================
     *
     * Autowiring is the process through which Spring automatically
     * resolves dependencies and supplies them to managed objects.
     *
     * Example:
     *
     * @Component
     * public class EmailService {
     * }
     *
     * @Component
     * public class NotificationService {
     *
     *     @Autowired
     *     private EmailService emailService;
     * }
     *
     * Spring identifies that NotificationService requires
     * EmailService and injects the matching bean.
     *
     * Autowiring is primarily type-oriented.
     *
     * If multiple candidates of the required type exist,
     * Spring needs additional information to select one.
     *
     *
     * ================================================================
     * 13. TYPES OF DEPENDENCY INJECTION
     * ================================================================
     *
     * Common dependency injection styles:
     *
     * 1. Constructor injection
     * 2. Setter injection
     * 3. Field injection
     * 4. Method injection
     *
     *
     * A. CONSTRUCTOR INJECTION
     *
     * Dependencies are supplied through the class constructor.
     *
     * Example:
     *
     * @Component
     * public class OrderService {
     *
     *     private final PaymentService paymentService;
     *
     *     public OrderService(PaymentService paymentService) {
     *         this.paymentService = paymentService;
     *     }
     * }
     *
     * Advantages:
     *
     * - Supports immutable dependencies.
     * - Makes required dependencies explicit.
     * - Ensures dependencies are supplied during construction.
     * - Makes unit testing easier.
     * - Helps identify classes with too many dependencies.
     *
     * RECOMMENDATION:
     *
     * Prefer constructor injection for required dependencies.
     *
     *
     * B. SETTER INJECTION
     *
     * Dependencies are supplied through setter methods.
     *
     * Example:
     *
     * @Component
     * public class ReportService {
     *
     *     private ExportService exportService;
     *
     *     @Autowired
     *     public void setExportService(
     *             ExportService exportService) {
     *
     *         this.exportService = exportService;
     *     }
     * }
     *
     * Useful when a dependency is optional or can be changed
     * after object construction.
     *
     * However, the object can temporarily exist without
     * that dependency.
     *
     *
     * C. FIELD INJECTION
     *
     * Dependencies are injected directly into fields.
     *
     * Example:
     *
     * @Component
     * public class UserService {
     *
     *     @Autowired
     *     private UserRepository userRepository;
     * }
     *
     * Advantages:
     * - Shorter code.
     * - Simple to write.
     *
     * Disadvantages:
     * - Dependencies are less visible.
     * - Fields cannot be final.
     * - Plain unit testing becomes harder.
     * - The object can be created outside Spring without
     *   its required dependency.
     * - It hides construction requirements.
     *
     * Generally avoid field injection in production services.
     *
     *
     * D. METHOD INJECTION
     *
     * Spring can inject dependencies through an arbitrary method.
     *
     * Example:
     *
     * @Component
     * public class NotificationService {
     *
     *     private EmailService emailService;
     *
     *     @Autowired
     *     public void configure(EmailService emailService) {
     *         this.emailService = emailService;
     *     }
     * }
     *
     * Useful when multiple related dependencies are supplied
     * through one configuration method.
     *
     *
     * ================================================================
     * 14. @AUTOWIRED ANNOTATION
     * ================================================================
     *
     * @Autowired tells Spring to resolve and inject a dependency.
     *
     * It can be used on:
     *
     * - Constructors
     * - Fields
     * - Setter methods
     * - Other methods
     *
     *
     * A. CONSTRUCTOR INJECTION
     *
     * @Component
     * public class UserService {
     *
     *     private final UserRepository userRepository;
     *
     *     @Autowired
     *     public UserService(UserRepository userRepository) {
     *         this.userRepository = userRepository;
     *     }
     * }
     *
     * IMPORTANT:
     *
     * If a class has only one constructor, Spring does not require
     * @Autowired on that constructor.
     *
     * Example:
     *
     * @Component
     * public class UserService {
     *
     *     private final UserRepository userRepository;
     *
     *     public UserService(UserRepository userRepository) {
     *         this.userRepository = userRepository;
     *     }
     * }
     *
     * Spring uses the single constructor for dependency injection.
     *
     * If multiple constructors exist, Spring needs an appropriate
     * indication of which constructor to use.
     *
     *
     * B. REQUIRED ATTRIBUTE
     *
     * @Autowired has a required attribute.
     *
     * Example:
     *
     * @Autowired(required = false)
     * public void setAuditService(AuditService auditService) {
     *     this.auditService = auditService;
     * }
     *
     * This means the injection point does not have to be satisfied.
     *
     * If no matching dependency exists, Spring can skip the
     * injection point.
     *
     * IMPORTANT:
     *
     * required=false is not a universal solution for all
     * optional dependencies.
     *
     * For constructor dependencies, consider Optional<T>,
     * @Nullable or ObjectProvider<T>.
     *
     *
     * ================================================================
     * 15. @QUALIFIER AND @PRIMARY
     * ================================================================
     *
     * PROBLEM: MULTIPLE BEANS OF THE SAME TYPE
     *
     * Suppose:
     *
     * @Component
     * public class EmailNotificationService
     *         implements NotificationService {
     * }
     *
     * @Component
     * public class SmsNotificationService
     *         implements NotificationService {
     * }
     *
     * Now:
     *
     * @Component
     * public class AlertService {
     *
     *     private final NotificationService notificationService;
     *
     *     public AlertService(
     *             NotificationService notificationService) {
     *
     *         this.notificationService = notificationService;
     *     }
     * }
     *
     * Spring finds two candidates:
     *
     * - EmailNotificationService
     * - SmsNotificationService
     *
     * It cannot determine which one should be injected.
     *
     * This can cause NoUniqueBeanDefinitionException.
     *
     *
     * A. @QUALIFIER
     *
     * @Qualifier narrows the candidate selection.
     *
     * Example:
     *
     * @Component("emailNotificationService")
     * public class EmailNotificationService
     *         implements NotificationService {
     * }
     *
     * @Component("smsNotificationService")
     * public class SmsNotificationService
     *         implements NotificationService {
     * }
     *
     * @Component
     * public class AlertService {
     *
     *     private final NotificationService notificationService;
     *
     *     public AlertService(
     *         @Qualifier("emailNotificationService")
     *         NotificationService notificationService) {
     *
     *         this.notificationService = notificationService;
     *     }
     * }
     *
     * Spring selects the matching qualified candidate.
     *
     * A qualifier is a candidate-selection criterion.
     * Bean names are commonly used as qualifier values, but
     * custom qualifier annotations are also supported.
     *
     *
     * B. @PRIMARY
     *
     * @Primary marks a preferred bean when multiple candidates
     * of the same type exist.
     *
     * Example:
     *
     * @Component
     * @Primary
     * public class EmailNotificationService
     *         implements NotificationService {
     * }
     *
     * @Component
     * public class SmsNotificationService
     *         implements NotificationService {
     * }
     *
     * An unqualified single-valued dependency normally receives
     * EmailNotificationService.
     *
     *
     * DIFFERENCE:
     *
     * @Primary:
     * Defines a preferred candidate.
     *
     * @Qualifier:
     * Narrows the candidates for a specific injection point.
     *
     * A matching explicit qualifier can select a different bean
     * even when another bean is marked @Primary.
     *
     * USE @PRIMARY WHEN:
     * One implementation should be the default.
     *
     * USE @QUALIFIER WHEN:
     * A specific injection point requires a particular implementation.
     *
     *
     * ================================================================
     * 16. @RESOURCE AND @INJECT
     * ================================================================
     *
     * Spring supports standard dependency injection annotations
     * in addition to @Autowired.
     *
     *
     * A. @RESOURCE
     *
     * @Resource is a standard injection annotation.
     *
     * Example:
     *
     * @Resource(name = "emailService")
     * private EmailService emailService;
     *
     * It commonly uses name-oriented resolution.
     *
     * When no name is supplied, the default name is generally
     * derived from the field or property name.
     *
     *
     * B. @INJECT
     *
     * @Inject is a standard dependency injection annotation
     * from Jakarta Inject or the older javax.inject API.
     *
     * Example:
     *
     * @Inject
     * public UserService(UserRepository userRepository) {
     *     this.userRepository = userRepository;
     * }
     *
     * It provides standard injection semantics.
     *
     * Unlike @Autowired, it does not have Spring's required
     * attribute.
     *
     *
     * COMPARISON:
     *
     * @Autowired:
     * - Spring-specific.
     * - Primarily type-oriented.
     * - Supports required attribute.
     * - Works with @Qualifier.
     *
     * @Resource:
     * - Standard annotation.
     * - Commonly name-oriented.
     *
     * @Inject:
     * - Standard annotation.
     * - Similar to type-oriented injection.
     * - Does not provide Spring-specific attributes.
     *
     * The corresponding annotation API must be available
     * in the application.
     *
     *
     * ================================================================
     * 17. OPTIONAL DEPENDENCIES
     * ================================================================
     *
     * Sometimes a dependency is not mandatory.
     *
     * Example:
     *
     * A notification service may work without an audit service.
     *
     * Common approaches:
     *
     * A. OPTIONAL<T>
     *
     * @Component
     * public class NotificationService {
     *
     *     private final Optional<AuditService> auditService;
     *
     *     public NotificationService(
     *             Optional<AuditService> auditService) {
     *
     *         this.auditService = auditService;
     *     }
     * }
     *
     * Spring can inject:
     *
     * Optional containing the bean, when available.
     * Optional.empty(), when no matching bean is available.
     *
     *
     * B. @NULLABLE
     *
     * @Component
     * public class NotificationService {
     *
     *     private final AuditService auditService;
     *
     *     public NotificationService(
     *             @Nullable AuditService auditService) {
     *
     *         this.auditService = auditService;
     *     }
     * }
     *
     * @Nullable indicates that the dependency may be null.
     *
     *
     * C. @AUTOWIRED(REQUIRED = FALSE)
     *
     * This can be used for optional field or method injection.
     *
     * Example:
     *
     * @Autowired(required = false)
     * private AuditService auditService;
     *
     * If no matching dependency exists, Spring can leave
     * the injection point unsatisfied.
     *
     * IMPORTANT:
     *
     * Do not make a required dependency optional just to
     * suppress startup errors.
     *
     *
     * ================================================================
     * 18. COLLECTION AND MAP INJECTION
     * ================================================================
     *
     * Spring can inject multiple beans of the same type.
     *
     * Example:
     *
     * public interface PaymentProcessor {
     *     void process();
     * }
     *
     * @Component
     * public class CardPaymentProcessor
     *         implements PaymentProcessor {
     *
     *     public void process() {
     *         System.out.println("Card payment");
     *     }
     * }
     *
     * @Component
     * public class UpiPaymentProcessor
     *         implements PaymentProcessor {
     *
     *     public void process() {
     *         System.out.println("UPI payment");
     *     }
     * }
     *
     *
     * A. LIST INJECTION
     *
     * @Component
     * public class PaymentService {
     *
     *     private final List<PaymentProcessor> processors;
     *
     *     public PaymentService(List<PaymentProcessor> processors) {
     *         this.processors = processors;
     *     }
     * }
     *
     * Spring supplies all matching PaymentProcessor beans.
     *
     *
     * B. SET INJECTION
     *
     * public PaymentService(Set<PaymentProcessor> processors) {
     *     this.processors = processors;
     * }
     *
     *
     * C. ARRAY INJECTION
     *
     * public PaymentService(PaymentProcessor[] processors) {
     *     this.processors = processors;
     * }
     *
     *
     * D. MAP INJECTION
     *
     * public PaymentService(
     *         Map<String, PaymentProcessor> processors) {
     *
     *     this.processors = processors;
     * }
     *
     * Map keys are bean names.
     * Map values are bean instances.
     *
     *
     * E. ORDERING
     *
     * Spring supports ordering metadata such as @Order and Ordered.
     *
     * Example:
     *
     * @Component
     * @Order(1)
     * public class CardPaymentProcessor
     *         implements PaymentProcessor {
     * }
     *
     * @Component
     * @Order(2)
     * public class UpiPaymentProcessor
     *         implements PaymentProcessor {
     * }
     *
     * Ordering is useful when multiple implementations must
     * execute in a defined sequence.
     *
     *
     * ================================================================
     * 19. OBJECTPROVIDER
     * ================================================================
     *
     * ObjectProvider<T> provides programmatic access to a bean.
     *
     * It is useful when a dependency should be resolved only
     * when required.
     *
     * Example:
     *
     * @Component
     * public class ReportService {
     *
     *     private final ObjectProvider<ReportGenerator> generator;
     *
     *     public ReportService(
     *             ObjectProvider<ReportGenerator> generator) {
     *
     *         this.generator = generator;
     *     }
     *
     *     public void generate() {
     *
     *         ReportGenerator reportGenerator =
     *                 generator.getObject();
     *
     *         reportGenerator.generate();
     *     }
     * }
     *
     * IMPORTANT METHODS:
     *
     * - getObject()
     * - getIfAvailable()
     * - getIfUnique()
     * - ifAvailable()
     * - stream()
     * - orderedStream()
     *
     * getObject():
     * Requests the bean and expects successful resolution.
     *
     * getIfAvailable():
     * Returns null when no suitable bean is available.
     * It can still fail if multiple candidates are ambiguous.
     *
     * getIfUnique():
     * Returns the unique candidate when one can be identified.
     *
     * USE CASES:
     *
     * - Optional dependencies.
     * - Lazy access to beans.
     * - Repeatedly requesting prototype-scoped beans.
     * - Delaying dependency resolution.
     *
     * ObjectProvider does not itself change a bean's scope.
     *
     *
     * ================================================================
     * 20. @VALUE AND PROPERTY INJECTION
     * ================================================================
     *
     * Applications often need external configuration values.
     *
     * Examples:
     *
     * - Database URL
     * - Application name
     * - Server port
     * - Email configuration
     * - API endpoints
     *
     * Spring supports @Value for injecting values.
     *
     *
     * A. DIRECT VALUE
     *
     * @Component
     * public class ApplicationInfo {
     *
     *     @Value("Production Application")
     *     private String applicationName;
     * }
     *
     *
     * B. PROPERTY PLACEHOLDER
     *
     * application.properties:
     *
     * application.name=Payment Service
     * application.version=1.0
     *
     * Java:
     *
     * @Component
     * public class ApplicationInfo {
     *
     *     @Value("${application.name}")
     *     private String applicationName;
     *
     *     @Value("${application.version}")
     *     private String version;
     * }
     *
     * ${...} represents a property placeholder.
     *
     * Spring resolves it using configured property sources.
     *
     *
     * C. DEFAULT VALUES
     *
     * @Value("${application.timeout:30}")
     * private int timeout;
     *
     * If application.timeout is unavailable, 30 is used.
     *
     *
     * D. SPRING EXPRESSION LANGUAGE
     *
     * SpEL stands for Spring Expression Language.
     *
     * It supports expression evaluation.
     *
     * Example:
     *
     * @Value("#{10 + 20}")
     * private int total;
     *
     * The expression evaluates to 30.
     *
     * Another example:
     *
     * @Value("#{systemProperties['user.name']}")
     * private String userName;
     *
     *
     * DIFFERENCE:
     *
     * ${...} -> Resolves external properties.
     *
     * #{...} -> Evaluates a SpEL expression.
     *
     *
     * ================================================================
     * 21. ENVIRONMENT AND PROFILES
     * ================================================================
     *
     * Environment provides access to application environment
     * information and property sources.
     *
     * It can be used to:
     *
     * - Read properties.
     * - Check active profiles.
     * - Check default profiles.
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
     *     public void printEnvironment() {
     *
     *         String name = environment.getProperty(
     *                 "application.name");
     *
     *         String[] profiles = environment.getActiveProfiles();
     *
     *         System.out.println(name);
     *         System.out.println(Arrays.toString(profiles));
     *     }
     * }
     *
     *
     * SPRING PROFILES
     *
     * Profiles allow different configurations or beans to be
     * activated in different environments.
     *
     * Common environments:
     *
     * - Development
     * - Testing
     * - Staging
     * - Production
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
     * Production:
     *
     * @Configuration
     * @Profile("prod")
     * public class ProductionConfig {
     *
     *     @Bean
     *     public PaymentGateway paymentGateway() {
     *         return new RealPaymentGateway();
     *     }
     * }
     *
     * Only configuration matching the active profile is applied.
     *
     *
     * ACTIVATING PROFILES:
     *
     * Profiles can be activated using:
     *
     * - Environment variables.
     * - JVM system properties.
     * - Application configuration.
     * - Programmatic environment configuration.
     *
     * Spring Boot commonly uses:
     *
     * spring.profiles.active=dev
     *
     * IMPORTANT:
     *
     * Profiles select environment-specific configuration.
     * They are not a replacement for secure secret management.
     *
     *
     * ================================================================
     * 22. CONDITIONAL BEAN REGISTRATION
     * ================================================================
     *
     * Sometimes a bean should be registered only when a condition
     * is satisfied.
     *
     * Spring Core provides the @Conditional mechanism.
     *
     * A custom condition implements Condition.
     *
     * Conceptual example:
     *
     * public class WindowsCondition implements Condition {
     *
     *     @Override
     *     public boolean matches(
     *             ConditionContext context,
     *             AnnotatedTypeMetadata metadata) {
     *
     *         String os = context.getEnvironment()
     *                 .getProperty("os.name", "");
     *
     *         return os.toLowerCase().contains("windows");
     *     }
     * }
     *
     * Configuration:
     *
     * @Configuration
     * @Conditional(WindowsCondition.class)
     * public class WindowsConfig {
     *
     *     @Bean
     *     public OperatingSystemService operatingSystemService() {
     *         return new WindowsOperatingSystemService();
     *     }
     * }
     *
     * The configuration is applied only when the condition matches.
     *
     *
     * SPRING BOOT CONDITIONAL ANNOTATIONS:
     *
     * - @ConditionalOnClass
     * - @ConditionalOnMissingClass
     * - @ConditionalOnBean
     * - @ConditionalOnMissingBean
     * - @ConditionalOnProperty
     * - @ConditionalOnWebApplication
     *
     * These annotations belong to Spring Boot's auto-configuration
     * infrastructure, not general Spring Core.
     *
     *
     * CONFIGURATION PROPERTIES:
     *
     * @ConfigurationProperties is commonly used in Spring Boot
     * to bind related external properties to Java objects.
     *
     * Example:
     *
     * @ConfigurationProperties(prefix = "app.mail")
     * public class MailProperties {
     *
     *     private String host;
     *     private int port;
     *
     *     // Getters and setters
     * }
     *
     * This is generally more maintainable than using many
     * individual @Value annotations for related properties.
     *
     * @ConfigurationProperties is part of Spring Boot's
     * external configuration support.
     *
     *
     * ================================================================
     * 23. BEAN NAMING AND CONFIGURATION CONFLICTS
     * ================================================================
     *
     * Spring beans have names.
     *
     * COMPONENT-SCANNED BEANS:
     *
     * The default name is generally derived from the class name
     * with its first character lowercased.
     *
     * Example:
     *
     * @Component
     * public class UserService {
     * }
     *
     * Default bean name:
     * userService
     *
     *
     * @BEAN METHODS:
     *
     * The method name is the default bean name.
     *
     * Example:
     *
     * @Bean
     * public EmailService emailService() {
     *     return new EmailService();
     * }
     *
     * Default bean name:
     * emailService
     *
     *
     * NAMING COLLISIONS:
     *
     * @Component("paymentService")
     * public class CardPaymentService {
     * }
     *
     * @Component("paymentService")
     * public class UpiPaymentService {
     * }
     *
     * Both components attempt to use the same bean name.
     *
     * Depending on the application context and bean definition
     * overriding configuration, this may result in a startup
     * error or an overriding situation.
     *
     * BEST PRACTICES:
     *
     * - Use meaningful bean names.
     * - Avoid accidental duplicate names.
     * - Use @Qualifier when selecting implementations.
     * - Use profiles or conditions for environment-specific beans.
     * - Do not rely on bean overriding to hide configuration conflicts.
     *
     *
     * ================================================================
     * 24. COMMON CONFIGURATION EXCEPTIONS
     * ================================================================
     *
     * A. NoSuchBeanDefinitionException
     *
     * MEANING:
     *
     * Spring cannot find a bean matching the requested type or name.
     *
     * POSSIBLE REASONS:
     *
     * - Component is not annotated.
     * - Component scanning does not include its package.
     * - Required configuration was not imported.
     * - Bean was excluded by a profile or condition.
     * - Incorrect bean name or type was requested.
     *
     * SOLUTION:
     *
     * Check component registration, scanning, profiles and imports.
     *
     *
     * B. NoUniqueBeanDefinitionException
     *
     * MEANING:
     *
     * Spring finds multiple matching beans when one is expected.
     *
     * POSSIBLE REASONS:
     *
     * - Multiple implementations of an interface exist.
     * - No suitable qualifier was supplied.
     * - No primary candidate was configured.
     *
     * SOLUTIONS:
     *
     * - Use @Qualifier.
     * - Use @Primary.
     * - Inject a collection if all candidates are required.
     *
     *
     * C. UnsatisfiedDependencyException
     *
     * MEANING:
     *
     * Spring cannot satisfy a dependency required by a bean.
     *
     * POSSIBLE REASONS:
     *
     * - Missing dependency bean.
     * - Dependency creation failed.
     * - Multiple candidates caused ambiguity.
     * - A dependency chain contains another startup failure.
     *
     * Always inspect the root cause in the stack trace.
     *
     *
     * D. BeanCurrentlyInCreationException
     *
     * MEANING:
     *
     * A bean is requested while it is still being created.
     *
     * A common reason is circular dependency.
     *
     * Example:
     *
     * ServiceA requires ServiceB.
     * ServiceB requires ServiceA.
     *
     * Constructor-based circular dependencies cannot be resolved
     * through normal constructor injection and generally cause
     * application startup failure.
     *
     * SOLUTION:
     *
     * Redesign the dependency relationship.
     *
     *
     * E. BeanDefinitionOverrideException
     *
     * MEANING:
     *
     * A bean definition conflicts with an existing definition,
     * and overriding is not permitted.
     *
     * POSSIBLE REASONS:
     *
     * - Duplicate bean names.
     * - Two configuration classes define the same bean name.
     * - A component name conflicts with an @Bean name.
     *
     *
     * ================================================================
     * 25. DEBUGGING AND BEST PRACTICES
     * ================================================================
     *
     * WHEN A BEAN IS NOT FOUND:
     *
     * 1. Check whether the class is annotated or declared with @Bean.
     * 2. Check component scanning packages.
     * 3. Check whether configuration classes are imported.
     * 4. Check active profiles.
     * 5. Check conditional bean registration.
     * 6. Check the requested bean type and name.
     *
     *
     * WHEN MULTIPLE BEANS ARE FOUND:
     *
     * 1. Identify all matching candidates.
     * 2. Decide whether one should be the default.
     * 3. Use @Primary for a preferred implementation.
     * 4. Use @Qualifier for a specific injection point.
     * 5. Inject a collection if all candidates are required.
     *
     *
     * GENERAL BEST PRACTICES:
     *
     * - Prefer constructor injection for required dependencies.
     * - Use final fields for constructor-injected dependencies.
     * - Use @Bean for third-party classes and explicit configuration.
     * - Use stereotypes for application components.
     * - Keep configuration classes focused and modular.
     * - Keep component scanning within intended packages.
     * - Use meaningful bean names when necessary.
     * - Use @Qualifier for explicit implementation selection.
     * - Use profiles for environment-specific configuration.
     * - Externalize environment-specific configuration.
     * - Avoid field injection in application services.
     * - Avoid circular dependencies.
     * - Avoid unnecessary use of @Lazy.
     * - Do not use optional injection to conceal design problems.
     *
     *
     * ================================================================
     * 26. REAL-WORLD SCENARIOS
     * ================================================================
     *
     * SCENARIO 1: MULTIPLE PAYMENT PROVIDERS
     *
     * An application supports:
     *
     * - UPI
     * - Credit card
     * - Net banking
     *
     * All implement PaymentProcessor.
     *
     * Possible solutions:
     *
     * - @Primary for the default processor.
     * - @Qualifier for a specific processor.
     * - List<PaymentProcessor> when all processors are required.
     *
     *
     * SCENARIO 2: DIFFERENT DATABASE CONFIGURATIONS
     *
     * Development uses a local database.
     * Production uses a managed database.
     *
     * Use @Profile to select the correct configuration.
     *
     *
     * SCENARIO 3: THIRD-PARTY LIBRARY
     *
     * A third-party library provides an object that cannot be
     * annotated with @Component.
     *
     * Use @Configuration and @Bean to register it.
     *
     *
     * SCENARIO 4: OPTIONAL AUDIT SERVICE
     *
     * A service can work without an audit implementation.
     *
     * Use Optional<AuditService> or ObjectProvider<AuditService>
     * when the dependency is genuinely optional.
     *
     *
     * SCENARIO 5: CONFIGURABLE APPLICATION
     *
     * Database URL, email host and timeout values differ by
     * environment.
     *
     * Use external properties, Environment and suitable
     * configuration binding.
     *
     *
     * ================================================================
     * 27. INTERVIEW QUESTIONS
     * ================================================================
     *
     * BASIC:
     *
     * 1. What is bean configuration in Spring?
     * 2. What are the different bean configuration approaches?
     * 3. What is Java-based configuration?
     * 4. What is the purpose of @Configuration?
     * 5. What is the purpose of @Bean?
     * 6. What is component scanning?
     * 7. What are stereotype annotations?
     * 8. What is @Autowired?
     * 9. What is the difference between @Component and @Service?
     * 10. What is the purpose of @Repository?
     *
     *
     * INTERMEDIATE:
     *
     * 11. What is the difference between @Bean and @Component?
     * 12. What is the difference between @Configuration and @Component?
     * 13. What is the difference between @Primary and @Qualifier?
     * 14. Why is constructor injection recommended?
     * 15. Does @Autowired need to be used on a single constructor?
     * 16. What happens when multiple beans of the same type exist?
     * 17. What is the difference between @Resource and @Autowired?
     * 18. What is the purpose of @ComponentScan?
     * 19. How does @Profile work?
     * 20. What is the difference between ${...} and #{...}?
     *
     *
     * ADVANCED:
     *
     * 21. What is the difference between full and lite configuration?
     * 22. What happens when one @Bean method directly calls another?
     * 23. Why prefer method parameter injection in lite mode?
     * 24. How does Spring resolve an ambiguous dependency?
     * 25. How can multiple beans be injected into a List or Map?
     * 26. What is ObjectProvider?
     * 27. What is the purpose of @Conditional?
     * 28. How does Spring Boot component scanning work by default?
     * 29. What causes BeanDefinitionOverrideException?
     * 30. How would you troubleshoot UnsatisfiedDependencyException?
     *
     *
     * ================================================================
     * 28. PRACTICE EXERCISES
     * ================================================================
     *
     * EXERCISE 1:
     *
     * Create an EmailService class and register it using @Component.
     *
     * EXERCISE 2:
     *
     * Create a NotificationService that receives EmailService
     * through constructor injection.
     *
     * EXERCISE 3:
     *
     * Create a configuration class and register a third-party-style
     * object using @Bean.
     *
     * EXERCISE 4:
     *
     * Create two implementations of a PaymentProcessor interface.
     * Use @Qualifier to select one.
     *
     * EXERCISE 5:
     *
     * Mark one implementation as @Primary and observe how Spring
     * resolves an unqualified dependency.
     *
     * EXERCISE 6:
     *
     * Inject all PaymentProcessor implementations into a List.
     *
     * EXERCISE 7:
     *
     * Create separate development and production configurations
     * using @Profile.
     *
     * EXERCISE 8:
     *
     * Read an application property using @Value.
     *
     * EXERCISE 9:
     *
     * Configure @Configuration(proxyBeanMethods = false).
     * Compare direct @Bean method calls with method parameter injection.
     *
     * EXERCISE 10:
     *
     * Deliberately create an ambiguous dependency and inspect
     * the resulting exception. Resolve it using @Qualifier.
     *
     *
     * ================================================================
     * 29. QUICK REVISION
     * ================================================================
     *
     * @Configuration
     *     Identifies a configuration class.
     *
     * @Bean
     *     Declares a bean through a factory method.
     *
     * @Component
     *     Marks a class as a Spring-managed component.
     *
     * @Service
     *     Identifies the service layer.
     *
     * @Repository
     *     Identifies the persistence layer.
     *
     * @Controller
     *     Identifies an MVC controller.
     *
     * @RestController
     *     Combines @Controller and @ResponseBody.
     *
     * @ComponentScan
     *     Defines packages to scan for components.
     *
     * @Import
     *     Imports additional configuration.
     *
     * @Autowired
     *     Resolves and injects a dependency.
     *
     * @Qualifier
     *     Narrows dependency candidate selection.
     *
     * @Primary
     *     Marks a preferred candidate.
     *
     * @Resource
     *     Supports name-oriented dependency injection.
     *
     * @Inject
     *     Standard dependency injection annotation.
     *
     * @Value
     *     Injects a property or expression value.
     *
     * @Profile
     *     Activates configuration for selected profiles.
     *
     * @Conditional
     *     Applies configuration when a condition matches.
     *
     * ObjectProvider
     *     Provides programmatic access to a bean.
     *
     * CONSTRUCTOR INJECTION
     *     Preferred for required dependencies.
     *
     * FULL CONFIGURATION
     *     Intercepts direct calls between @Bean methods.
     *
     * LITE CONFIGURATION
     *     Uses normal Java method-call semantics.
     *
     * ================================================================
     * END OF SPRING CORE - FILE 04
     * ================================================================
     */

    public static void main(String[] args) {

        System.out.println(
            "Spring Core - Bean Configuration and Autowiring"
        );

    }

}
