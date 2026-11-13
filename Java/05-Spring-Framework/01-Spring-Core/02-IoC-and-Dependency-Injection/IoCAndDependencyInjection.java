
public class IoCAndDependencyInjection {

    /*
     * =====================================================================
     *                  INVERSION OF CONTROL AND DEPENDENCY INJECTION
     * =====================================================================
     *
     *
     * 1. WHAT IS INVERSION OF CONTROL (IoC)?
     *
     * Inversion of Control is a software design principle in which
     * the responsibility for controlling certain operations is
     * transferred from application code to an external component
     * such as a framework or container.
     *
     * In traditional Java development:
     *
     * - Developers create objects.
     * - Developers manage object dependencies.
     * - Developers decide when objects are created.
     *
     * With Spring IoC:
     *
     * - Spring manages application objects.
     * - Spring manages their dependencies.
     * - Spring controls their creation and lifecycle.
     *
     * In simple words:
     *
     * Instead of your application controlling everything,
     * the Spring container takes responsibility for managing
     * the objects configured within it.
     *
     *
     * 2. WHY DO WE NEED IoC?
     *
     * Consider a real application:
     *
     * EmployeeController
     *        |
     *        v
     * EmployeeService
     *        |
     *        v
     * EmployeeRepository
     *        |
     *        v
     * Database
     *
     * EmployeeController needs EmployeeService.
     *
     * EmployeeService needs EmployeeRepository.
     *
     * EmployeeRepository communicates with the database.
     *
     * Every class depends on another class to perform its work.
     *
     * These relationships are called dependencies.
     *
     * Someone must create these objects and connect them.
     *
     * Without IoC, application classes may take responsibility
     * for creating and managing their own dependencies.
     *
     * This can lead to tight coupling and difficult maintenance.
     *
     * IoC allows the responsibility for object management
     * to be handled externally.
     *
     *
     * 3. UNDERSTANDING DEPENDENCY
     *
     * A dependency is an object or service that another
     * object requires to perform its functionality.
     *
     * Example:
     *
     * class EmployeeService {
     *
     *     private EmployeeRepository repository;
     *
     *     public void saveEmployee() {
     *         repository.save();
     *     }
     * }
     *
     * EmployeeService depends on EmployeeRepository.
     *
     * Why?
     *
     * Because EmployeeService cannot perform its save
     * operation without a repository implementation.
     *
     * Therefore:
     *
     * EmployeeRepository = Dependency.
     *
     * EmployeeService = Dependent object.
     *
     *
     * 4. WHAT IS TIGHT COUPLING?
     *
     * Tight coupling occurs when one class is strongly
     * dependent on a specific implementation of another class.
     *
     * Example:
     *
     * class MySQLDatabase {
     *
     *     public void connect() {
     *         System.out.println("Connected to MySQL");
     *     }
     * }
     *
     * class EmployeeService {
     *
     *     private MySQLDatabase database = new MySQLDatabase();
     *
     *     public void saveEmployee() {
     *         database.connect();
     *     }
     * }
     *
     * Here:
     *
     * EmployeeService directly creates MySQLDatabase.
     *
     * Problems:
     *
     * 1. EmployeeService is tied to MySQLDatabase.
     *
     * 2. Replacing MySQLDatabase with PostgreSQLDatabase
     *    requires changing EmployeeService.
     *
     * 3. Testing EmployeeService independently becomes harder.
     *
     * 4. Object creation and business logic are mixed.
     *
     * 5. Reusing EmployeeService with another implementation
     *    becomes difficult.
     *
     * This is an example of tight coupling.
     *
     *
     * 5. WHAT IS LOOSE COUPLING?
     *
     * Loose coupling means reducing direct dependency
     * between classes and allowing implementations to
     * be changed with minimal impact.
     *
     * We can achieve this by depending on abstractions
     * rather than concrete implementations.
     *
     * Example:
     *
     * interface Database {
     *
     *     void connect();
     * }
     *
     * class MySQLDatabase implements Database {
     *
     *     public void connect() {
     *         System.out.println("Connected to MySQL");
     *     }
     * }
     *
     * class PostgreSQLDatabase implements Database {
     *
     *     public void connect() {
     *         System.out.println("Connected to PostgreSQL");
     *     }
     * }
     *
     * class EmployeeService {
     *
     *     private Database database;
     *
     *     public EmployeeService(Database database) {
     *         this.database = database;
     *     }
     *
     *     public void saveEmployee() {
     *         database.connect();
     *     }
     * }
     *
     * Now EmployeeService depends on the Database interface
     * instead of a particular database implementation.
     *
     * Benefits:
     *
     * - Implementations can be replaced.
     * - Testing becomes easier.
     * - Classes become more reusable.
     * - Changes are easier to manage.
     *
     *
     * 6. DEPENDING ON ABSTRACTIONS
     *
     * An abstraction defines what an object should do
     * without specifying every implementation detail.
     *
     * In Java, interfaces and abstract classes are
     * common ways to define abstractions.
     *
     * Example:
     *
     * interface PaymentService {
     *
     *     void pay(double amount);
     * }
     *
     * class UpiPaymentService implements PaymentService {
     *
     *     public void pay(double amount) {
     *         System.out.println("Paid using UPI");
     *     }
     * }
     *
     * class CardPaymentService implements PaymentService {
     *
     *     public void pay(double amount) {
     *         System.out.println("Paid using Card");
     *     }
     * }
     *
     * A checkout service can depend on PaymentService
     * instead of a particular payment implementation.
     *
     * This makes implementation replacement easier.
     *
     * Important:
     *
     * An interface alone does not automatically create
     * loose coupling.
     *
     * The dependent class must also avoid controlling
     * the selection and creation of a specific implementation.
     *
     *
     * 7. WHAT IS DEPENDENCY INJECTION (DI)?
     *
     * Dependency Injection is a design technique in which
     * an object receives the dependencies it requires
     * from an external source.
     *
     * Instead of creating its own dependencies,
     * the object receives them.
     *
     * Example:
     *
     * class EmployeeService {
     *
     *     private final EmployeeRepository repository;
     *
     *     public EmployeeService(EmployeeRepository repository) {
     *         this.repository = repository;
     *     }
     * }
     *
     * EmployeeService receives EmployeeRepository
     * through its constructor.
     *
     * It does not create the repository itself.
     *
     * The external code responsible for assembling
     * the objects supplies the dependency.
     *
     *
     * 8. RELATIONSHIP BETWEEN IoC AND DI
     *
     * IoC is a broader design principle.
     *
     * DI is one technique used to implement IoC.
     *
     * Relationship:
     *
     *             IoC
     *              |
     *              v
     *      Dependency Injection
     *
     * IoC:
     * Transfers control of certain responsibilities
     * to an external component.
     *
     * DI:
     * Supplies required dependencies from outside
     * the dependent object.
     *
     * Important:
     *
     * IoC and DI are related but are not synonyms.
     *
     *
     * 9. TYPES OF DEPENDENCY INJECTION
     *
     * Spring commonly supports three injection styles:
     *
     * 1. Constructor Injection.
     * 2. Setter Injection.
     * 3. Field Injection.
     *
     * We will understand each one.
     *
     *
     * 10. CONSTRUCTOR INJECTION
     *
     * Constructor Injection means supplying dependencies
     * through a class constructor.
     *
     * Example:
     *
     * class EmployeeRepository {
     *
     *     public void save() {
     *         System.out.println("Employee saved");
     *     }
     * }
     *
     * class EmployeeService {
     *
     *     private final EmployeeRepository repository;
     *
     *     public EmployeeService(EmployeeRepository repository) {
     *         this.repository = repository;
     *     }
     *
     *     public void saveEmployee() {
     *         repository.save();
     *     }
     * }
     *
     * Here:
     *
     * EmployeeRepository is passed through the constructor.
     *
     * EmployeeService receives the dependency during creation.
     *
     * Benefits:
     *
     * 1. Required dependencies are available when the object
     *    is constructed.
     *
     * 2. Fields can be final.
     *
     * 3. The object cannot be created without its required
     *    dependencies.
     *
     * 4. Unit testing becomes easier.
     *
     * 5. Dependencies are explicit.
     *
     * 6. Supports immutable object design.
     *
     * Spring recommendation:
     *
     * Prefer constructor injection for mandatory dependencies.
     *
     *
     * 11. CONSTRUCTOR INJECTION WITH MULTIPLE DEPENDENCIES
     *
     * Example:
     *
     * class EmployeeService {
     *
     *     private final EmployeeRepository repository;
     *     private final EmailService emailService;
     *     private final AuditService auditService;
     *
     *     public EmployeeService(
     *             EmployeeRepository repository,
     *             EmailService emailService,
     *             AuditService auditService) {
     *
     *         this.repository = repository;
     *         this.emailService = emailService;
     *         this.auditService = auditService;
     *     }
     * }
     *
     * All mandatory dependencies are supplied through
     * the constructor.
     *
     * Design consideration:
     *
     * A constructor with too many dependencies may indicate
     * that the class has too many responsibilities.
     *
     * Consider improving the class design rather than
     * blindly adding more dependencies.
     *
     *
     * 12. SETTER INJECTION
     *
     * Setter Injection means supplying dependencies
     * through setter methods.
     *
     * Example:
     *
     * class EmployeeService {
     *
     *     private EmployeeRepository repository;
     *
     *     public void setRepository(EmployeeRepository repository) {
     *         this.repository = repository;
     *     }
     *
     *     public void saveEmployee() {
     *         repository.save();
     *     }
     * }
     *
     * The dependency is supplied after object creation.
     *
     * Benefits:
     *
     * - Useful for optional dependencies.
     * - Allows dependencies to be changed after construction.
     * - Useful when a dependency is not mandatory.
     *
     * Disadvantages:
     *
     * - The object may exist before its dependency is supplied.
     * - The dependency field may remain null.
     * - It can make the object's state mutable.
     *
     * Use setter injection when the dependency is genuinely
     * optional or needs to be configurable after construction.
     *
     *
     * 13. FIELD INJECTION
     *
     * Field Injection means injecting a dependency directly
     * into a class field.
     *
     * Spring example:
     *
     * @Component
     * class EmployeeService {
     *
     *     @Autowired
     *     private EmployeeRepository repository;
     *
     * }
     *
     * Spring injects the dependency into the field.
     *
     * Benefits:
     *
     * - Less code.
     * - Simple to write.
     *
     * Disadvantages:
     *
     * - Dependencies are less visible.
     * - Fields cannot be final in the usual field-injection model.
     * - Plain unit testing becomes less convenient.
     * - Object construction does not clearly express
     *   required dependencies.
     *
     * Professional recommendation:
     *
     * Prefer constructor injection over field injection.
     *
     *
     * 14. COMPARISON OF INJECTION TYPES
     *
     * Constructor Injection:
     *
     * - Uses a constructor.
     * - Best for mandatory dependencies.
     * - Supports final fields.
     * - Makes dependencies explicit.
     *
     * Setter Injection:
     *
     * - Uses setter methods.
     * - Useful for optional dependencies.
     * - Allows dependencies to be changed.
     *
     * Field Injection:
     *
     * - Uses fields, commonly with @Autowired.
     * - Requires less code.
     * - Makes dependencies less explicit.
     * - Generally not recommended for application services.
     *
     * General recommendation:
     *
     * Constructor injection for required dependencies.
     *
     * Setter injection for optional dependencies where appropriate.
     *
     *
     * 15. HOW DOES SPRING PERFORM DEPENDENCY INJECTION?
     *
     * Spring uses its IoC container to manage application objects.
     *
     * A simplified process:
     *
     * 1. Spring reads application configuration.
     *
     * 2. Spring discovers or registers bean definitions.
     *
     * 3. Spring determines which objects need to be created.
     *
     * 4. Spring creates bean instances.
     *
     * 5. Spring resolves their dependencies.
     *
     * 6. Spring injects the required dependencies.
     *
     * 7. Spring completes bean initialization.
     *
     * 8. The managed beans become available for application use.
     *
     * This is a conceptual overview.
     *
     * The actual container lifecycle includes additional
     * processing steps and extension points.
     *
     *
     * 16. DEPENDENCY INJECTION USING SPRING ANNOTATIONS
     *
     * Spring provides annotations that help register
     * and configure application components.
     *
     * Example:
     *
     * @Component
     * class EmployeeRepository {
     *
     *     public void save() {
     *         System.out.println("Employee saved");
     *     }
     * }
     *
     * @Component
     * class EmployeeService {
     *
     *     private final EmployeeRepository repository;
     *
     *     public EmployeeService(EmployeeRepository repository) {
     *         this.repository = repository;
     *     }
     *
     *     public void saveEmployee() {
     *         repository.save();
     *     }
     * }
     *
     * With component scanning configured, Spring can:
     *
     * - Discover EmployeeRepository.
     * - Discover EmployeeService.
     * - Register their bean definitions.
     * - Create their instances.
     * - Resolve EmployeeRepository as a constructor dependency.
     * - Inject it into EmployeeService.
     *
     * Notice:
     *
     * There is no need to write:
     *
     * new EmployeeRepository()
     *
     * inside EmployeeService.
     *
     *
     * 17. IS @Autowired REQUIRED FOR CONSTRUCTOR INJECTION?
     *
     * In modern Spring, if a bean has only one constructor,
     * @Autowired is not required on that constructor.
     *
     * Example:
     *
     * @Component
     * class EmployeeService {
     *
     *     private final EmployeeRepository repository;
     *
     *     public EmployeeService(EmployeeRepository repository) {
     *         this.repository = repository;
     *     }
     * }
     *
     * Spring can use the single constructor to resolve
     * the required dependency.
     *
     * If a class has multiple constructors, explicit
     * constructor selection may be necessary.
     *
     * Important:
     *
     * @Autowired is still available and useful in
     * appropriate injection scenarios.
     *
     *
     * 18. WHAT IF MULTIPLE IMPLEMENTATIONS EXIST?
     *
     * Suppose:
     *
     * interface PaymentService {
     *
     *     void pay();
     * }
     *
     * @Component
     * class UpiPaymentService implements PaymentService {
     *
     *     public void pay() {
     *         System.out.println("UPI payment");
     *     }
     * }
     *
     * @Component
     * class CardPaymentService implements PaymentService {
     *
     *     public void pay() {
     *         System.out.println("Card payment");
     *     }
     * }
     *
     * Now consider:
     *
     * @Component
     * class CheckoutService {
     *
     *     private final PaymentService paymentService;
     *
     *     public CheckoutService(PaymentService paymentService) {
     *         this.paymentService = paymentService;
     *     }
     * }
     *
     * Spring finds two candidates:
     *
     * UpiPaymentService.
     *
     * CardPaymentService.
     *
     * Spring cannot choose a unique candidate automatically
     * based only on the interface type.
     *
     * This can result in NoUniqueBeanDefinitionException.
     *
     * We need to specify which implementation should be used.
     *
     *
     * 19. USING @Primary
     *
     * @Primary indicates the preferred bean when multiple
     * candidates match a dependency.
     *
     * Example:
     *
     * @Component
     * @Primary
     * class UpiPaymentService implements PaymentService {
     *
     *     public void pay() {
     *         System.out.println("UPI payment");
     *     }
     * }
     *
     * @Component
     * class CardPaymentService implements PaymentService {
     *
     *     public void pay() {
     *         System.out.println("Card payment");
     *     }
     * }
     *
     * Now UpiPaymentService is the preferred candidate
     * for an ordinary single-valued PaymentService injection.
     *
     * Important:
     *
     * @Primary establishes a preference.
     *
     * It does not remove the other bean.
     *
     *
     * 20. USING @Qualifier
     *
     * @Qualifier identifies a particular bean candidate
     * when multiple beans have the same compatible type.
     *
     * Example:
     *
     * @Component("upiPayment")
     * class UpiPaymentService implements PaymentService {
     *
     *     public void pay() {
     *         System.out.println("UPI payment");
     *     }
     * }
     *
     * @Component("cardPayment")
     * class CardPaymentService implements PaymentService {
     *
     *     public void pay() {
     *         System.out.println("Card payment");
     *     }
     * }
     *
     * @Component
     * class CheckoutService {
     *
     *     private final PaymentService paymentService;
     *
     *     public CheckoutService(
     *             @Qualifier("cardPayment")
     *             PaymentService paymentService) {
     *
     *         this.paymentService = paymentService;
     *     }
     * }
     *
     * Spring injects the bean identified by cardPayment.
     *
     * Difference:
     *
     * @Primary:
     * Marks a preferred candidate.
     *
     * @Qualifier:
     * Narrows the candidates using a qualifier value.
     *
     *
     * 21. INJECTING COLLECTIONS OF DEPENDENCIES
     *
     * Sometimes an application needs all implementations
     * of an interface rather than only one.
     *
     * Spring can inject collections of matching beans.
     *
     * Example:
     *
     * @Component
     * class CheckoutService {
     *
     *     private final List<PaymentService> paymentServices;
     *
     *     public CheckoutService(List<PaymentService> paymentServices) {
     *         this.paymentServices = paymentServices;
     *     }
     * }
     *
     * Spring can supply the matching PaymentService beans.
     *
     * Other supported collection forms include arrays,
     * sets and maps, depending on the injection type.
     *
     * This is useful for plugin-style designs and
     * multiple strategy implementations.
     *
     *
     * 22. OPTIONAL DEPENDENCIES
     *
     * Not every dependency is mandatory.
     *
     * Sometimes an application component can work
     * even when a particular dependency is unavailable.
     *
     * Spring supports optional dependency approaches,
     * including:
     *
     * - java.util.Optional.
     * - @Nullable.
     * - Optional injection configuration.
     *
     * Example:
     *
     * @Component
     * class NotificationService {
     *
     *     private final EmailService emailService;
     *
     *     public NotificationService(
     *             Optional<EmailService> emailService) {
     *
     *         this.emailService = emailService.orElse(null);
     *     }
     * }
     *
     * The exact design should make the optional behaviour
     * explicit and safe.
     *
     * Do not make a required dependency optional just to
     * hide a missing-bean configuration problem.
     *
     *
     * 23. WHAT IS DEPENDENCY LOOKUP?
     *
     * Dependency Lookup is an approach in which application
     * code explicitly requests a dependency from a container
     * or registry.
     *
     * Example:
     *
     * ApplicationContext context = ...;
     *
     * EmployeeService service =
     *         context.getBean(EmployeeService.class);
     *
     * Here, application code asks the Spring container
     * to provide the bean.
     *
     * This is dependency lookup.
     *
     * It differs from Dependency Injection:
     *
     * Dependency Injection:
     * The dependency is supplied to the dependent object.
     *
     * Dependency Lookup:
     * The object or application code requests the dependency.
     *
     * In normal Spring application design, prefer
     * Dependency Injection for application components.
     *
     *
     * 24. DEPENDENCY INJECTION VS DEPENDENCY LOOKUP
     *
     * Dependency Injection:
     *
     * - Dependencies are supplied externally.
     * - The dependent class does not need to request them.
     * - Promotes explicit dependency relationships.
     *
     * Dependency Lookup:
     *
     * - The application explicitly asks a container
     *   or registry for an object.
     * - The requesting code knows about the lookup mechanism.
     *
     * Both are ways to obtain dependencies.
     *
     * They differ in who initiates the dependency acquisition.
     *
     *
     * 25. WHAT IS A COMPOSITION ROOT?
     *
     * A Composition Root is the part of an application
     * where application components are assembled
     * and their dependencies are connected.
     *
     * In a Spring application, the container and
     * application configuration perform this responsibility.
     *
     * Instead of allowing business classes to construct
     * their entire dependency graph, object composition
     * is centralized in the application's configuration
     * and container.
     *
     * This improves separation of concerns.
     *
     *
     * 26. INVERSION OF CONTROL IN PLAIN JAVA
     *
     * IoC and DI are not exclusive to Spring.
     *
     * They are design principles and techniques that
     * can be implemented using plain Java.
     *
     * Example:
     *
     * interface MessageService {
     *
     *     void sendMessage();
     * }
     *
     * class EmailService implements MessageService {
     *
     *     public void sendMessage() {
     *         System.out.println("Email sent");
     *     }
     * }
     *
     * class NotificationService {
     *
     *     private final MessageService messageService;
     *
     *     public NotificationService(MessageService messageService) {
     *         this.messageService = messageService;
     *     }
     *
     *     public void notifyUser() {
     *         messageService.sendMessage();
     *     }
     * }
     *
     * public class Main {
     *
     *     public static void main(String[] args) {
     *
     *         MessageService service = new EmailService();
     *
     *         NotificationService notification =
     *                 new NotificationService(service);
     *
     *         notification.notifyUser();
     *     }
     * }
     *
     * Here, Main assembles the objects and supplies the dependency.
     *
     * No Spring framework is required.
     *
     * Spring provides a container that automates and extends
     * this kind of object assembly and management.
     *
     *
     * 27. HOW DI IMPROVES UNIT TESTING
     *
     * Consider a service that directly creates its repository.
     *
     * Testing it with a fake repository becomes difficult
     * because the service controls its own dependency creation.
     *
     * With constructor injection, a test can supply
     * a mock or fake implementation.
     *
     * Example:
     *
     * EmployeeRepository fakeRepository = new EmployeeRepository();
     *
     * EmployeeService service = new EmployeeService(fakeRepository);
     *
     * In a real unit test, you might use Mockito:
     *
     * EmployeeRepository repository =
     *         Mockito.mock(EmployeeRepository.class);
     *
     * EmployeeService service = new EmployeeService(repository);
     *
     * This allows the service to be tested independently
     * from the real database implementation.
     *
     *
     * 28. CIRCULAR DEPENDENCY
     *
     * A circular dependency occurs when two or more
     * components depend on one another in a cycle.
     *
     * Example:
     *
     * EmployeeService depends on DepartmentService.
     *
     * DepartmentService depends on EmployeeService.
     *
     * Dependency graph:
     *
     * EmployeeService
     *        |
     *        v
     * DepartmentService
     *        |
     *        v
     * EmployeeService
     *
     * This creates a dependency cycle.
     *
     * Constructor-based circular dependencies generally
     * cannot be resolved by Spring and result in a
     * BeanCurrentlyInCreationException.
     *
     * Best solution:
     *
     * Reconsider the design and remove the unnecessary cycle.
     *
     * Do not immediately use lazy injection or setter injection
     * just to hide a poor dependency structure.
     *
     *
     * 29. DEPENDENCY INJECTION AND SOLID PRINCIPLES
     *
     * DI supports several SOLID principles.
     *
     * SINGLE RESPONSIBILITY PRINCIPLE:
     *
     * A class should have one primary responsibility.
     *
     * DI helps separate object creation from business logic.
     *
     * OPEN/CLOSED PRINCIPLE:
     *
     * Classes should be open for extension and closed
     * for modification.
     *
     * Depending on abstractions helps replace implementations.
     *
     * LISKOV SUBSTITUTION PRINCIPLE:
     *
     * Implementations should be usable wherever their
     * abstraction is expected without breaking correctness.
     *
     * INTERFACE SEGREGATION PRINCIPLE:
     *
     * Clients should not be forced to depend on methods
     * they do not need.
     *
     * DEPENDENCY INVERSION PRINCIPLE:
     *
     * High-level and low-level modules should depend
     * on abstractions rather than concrete details.
     *
     * Important:
     *
     * DI is not the same as the Dependency Inversion Principle.
     *
     * DI is a technique.
     *
     * Dependency Inversion is a design principle.
     *
     * DI can help implement Dependency Inversion.
     *
     *
     * 30. COMMON DEPENDENCY INJECTION MISTAKES
     *
     * 1. Using field injection everywhere.
     *
     * 2. Creating dependencies using new inside
     *    Spring-managed service classes.
     *
     * 3. Injecting too many dependencies into one class.
     *
     * 4. Depending directly on concrete implementations
     *    when an abstraction is more appropriate.
     *
     * 5. Ignoring circular dependencies.
     *
     * 6. Making required dependencies optional unnecessarily.
     *
     * 7. Using @Primary and @Qualifier without understanding
     *    their selection behaviour.
     *
     * 8. Assuming every Java object is managed by Spring.
     *
     * 9. Confusing IoC with DI.
     *
     * 10. Using ApplicationContext.getBean() everywhere
     *     instead of normal dependency injection.
     *
     *
     * 31. BEST PRACTICES
     *
     * 1. Prefer constructor injection for required dependencies.
     *
     * 2. Declare mandatory dependency fields as final.
     *
     * 3. Depend on suitable abstractions.
     *
     * 4. Keep classes focused on a clear responsibility.
     *
     * 5. Avoid unnecessary dependencies.
     *
     * 6. Avoid circular dependencies.
     *
     * 7. Use @Qualifier when a specific implementation
     *    must be selected.
     *
     * 8. Use @Primary when a default implementation
     *    should be preferred.
     *
     * 9. Make optional dependencies explicit.
     *
     * 10. Keep object construction separate from business logic.
     *
     *
     * 32. INTERVIEW QUESTIONS - BASIC LEVEL
     *
     * 1. What is Inversion of Control?
     *
     * 2. What is Dependency Injection?
     *
     * 3. What is a dependency?
     *
     * 4. What is tight coupling?
     *
     * 5. What is loose coupling?
     *
     * 6. What is the relationship between IoC and DI?
     *
     * 7. What are the types of Dependency Injection?
     *
     * 8. What is constructor injection?
     *
     * 9. What is setter injection?
     *
     * 10. What is field injection?
     *
     *
     * 33. INTERVIEW QUESTIONS - INTERMEDIATE LEVEL
     *
     * 1. Why is constructor injection recommended?
     *
     * 2. What is the difference between constructor
     *    and setter injection?
     *
     * 3. Is @Autowired required on a single constructor?
     *
     * 4. How does Spring resolve constructor dependencies?
     *
     * 5. What happens when multiple beans implement
     *    the same interface?
     *
     * 6. What is the purpose of @Primary?
     *
     * 7. What is the purpose of @Qualifier?
     *
     * 8. What is the difference between @Primary and @Qualifier?
     *
     * 9. Can Spring inject a List of matching beans?
     *
     * 10. What is dependency lookup?
     *
     * 11. What is the difference between DI and dependency lookup?
     *
     * 12. Can DI be implemented without Spring?
     *
     *
     * 34. INTERVIEW QUESTIONS - ADVANCED LEVEL
     *
     * 1. Explain the internal process of dependency resolution
     *    in the Spring container.
     *
     * 2. What is a composition root?
     *
     * 3. Why is field injection generally discouraged?
     *
     * 4. How does constructor injection improve immutability?
     *
     * 5. What is a circular dependency?
     *
     * 6. Why do constructor-based circular dependencies fail?
     *
     * 7. What is the relationship between DI and SOLID principles?
     *
     * 8. What is the difference between DI and
     *    Dependency Inversion Principle?
     *
     * 9. How can dependency injection improve unit testing?
     *
     * 10. Does using interfaces automatically guarantee
     *     loose coupling?
     *
     *
     * 35. INTERVIEW QUESTIONS - REAL-WORLD SCENARIOS
     *
     * 1. Your EmployeeService directly creates a MySQLRepository.
     *    Your company wants to migrate to PostgreSQL.
     *    How would you redesign the dependency?
     *
     * 2. Two PaymentService implementations are registered
     *    as Spring beans. Spring reports NoUniqueBeanDefinitionException.
     *    How would you resolve it?
     *
     * 3. A service has eight constructor dependencies.
     *    What might this indicate about the class design?
     *
     * 4. A service works in production but is difficult to
     *    unit test because it creates its own dependencies.
     *    How would DI help?
     *
     * 5. Two services depend on each other through constructors.
     *    How would you investigate and fix the design?
     *
     * 6. Your application needs to execute all implementations
     *    of a common interface. How can Spring inject them?
     *
     * 7. A dependency is required only for an optional feature.
     *    How could you represent that requirement?
     *
     * 8. Your service depends on a concrete payment class.
     *    How would you make it easier to support multiple
     *    payment providers?
     *
     *
     * 36. PRACTICAL EXERCISES
     *
     * Exercise 1:
     *
     * Create a Database interface.
     *
     * Implement:
     * - MySQLDatabase.
     * - PostgreSQLDatabase.
     *
     * Create an EmployeeService that depends on Database.
     *
     * Supply the dependency using constructor injection.
     *
     *
     * Exercise 2:
     *
     * Create a PaymentService interface.
     *
     * Implement:
     * - UpiPaymentService.
     * - CardPaymentService.
     * - NetBankingPaymentService.
     *
     * Design a CheckoutService that receives the required
     * implementation through constructor injection.
     *
     *
     * Exercise 3:
     *
     * Implement the same application using plain Java.
     *
     * Then convert it into Spring-managed components
     * using @Component and constructor injection.
     *
     *
     * Exercise 4:
     *
     * Create two implementations of NotificationService.
     *
     * Resolve the ambiguity using:
     * - @Primary.
     * - @Qualifier.
     *
     *
     * Exercise 5:
     *
     * Write a unit test for EmployeeService using
     * a mock EmployeeRepository.
     *
     *
     * =====================================================================
     *                  END OF IoC AND DEPENDENCY INJECTION
     * =====================================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about IoC and Dependency Injection , Happy Learning ..!");
    }
}
