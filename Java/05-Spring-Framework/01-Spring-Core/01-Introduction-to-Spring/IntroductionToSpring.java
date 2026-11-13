
public class IntroductionToSpring {

    /*
     * =====================================================================
     *                         INTRODUCTION TO SPRING
     * =====================================================================
     *
     *
     * 1. WHAT IS SPRING?
     *
     * Spring is an open-source Java application development framework.
     *
     * It provides infrastructure and programming capabilities that help
     * developers build enterprise-level Java applications.
     *
     * Spring simplifies the development of applications by providing
     * features such as:
     *
     * - Dependency Injection (DI).
     * - Inversion of Control (IoC).
     * - Aspect-Oriented Programming (AOP).
     * - Transaction management.
     * - Data access integration.
     * - Web application development.
     * - Security integration.
     * - Application configuration.
     *
     * Instead of implementing all these infrastructure capabilities
     * manually, developers can use Spring's existing functionality.
     *
     *
     * 2. WHY WAS SPRING CREATED?
     *
     * Before Spring became popular, developing enterprise Java
     * applications often involved considerable complexity.
     *
     * Developers had to deal with:
     *
     * - Complex object creation and management.
     * - Tight coupling between classes.
     * - Difficult dependency management.
     * - Complicated enterprise Java technologies.
     * - Repetitive infrastructure code.
     * - Difficult unit testing.
     * - Transaction management complexity.
     *
     * These problems made applications harder to develop,
     * maintain, test and extend.
     *
     * Spring was created to simplify enterprise Java development
     * by providing lightweight infrastructure and encouraging
     * loosely coupled application design.
     *
     *
     * 3. PROBLEMS WITH TRADITIONAL JAVA DEVELOPMENT
     *
     * Consider an application with three classes:
     *
     * EmployeeService
     *       |
     *       v
     * EmployeeRepository
     *       |
     *       v
     * Database
     *
     * EmployeeService needs EmployeeRepository to perform
     * database operations.
     *
     * Without Dependency Injection, EmployeeService might
     * create its dependency directly.
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
     *     private EmployeeRepository repository =
     *             new EmployeeRepository();
     *
     *     public void saveEmployee() {
     *         repository.save();
     *     }
     * }
     *
     * Here, EmployeeService is responsible for creating
     * EmployeeRepository.
     *
     * Problems:
     *
     * 1. EmployeeService is tightly coupled to EmployeeRepository.
     *
     * 2. Replacing EmployeeRepository with another implementation
     *    requires changes to EmployeeService.
     *
     * 3. Testing becomes more difficult because the dependency
     *    is created internally.
     *
     * 4. Object creation responsibilities are mixed with
     *    business logic.
     *
     * Spring provides mechanisms to address these problems.
     *
     *
     * 4. WHAT DOES SPRING PROVIDE?
     *
     * Spring provides a collection of modules and infrastructure
     * capabilities.
     *
     * Important capabilities:
     *
     * 1. IoC Container:
     *    Manages application objects and their dependencies.
     *
     * 2. Dependency Injection:
     *    Supplies required dependencies to objects.
     *
     * 3. AOP:
     *    Separates cross-cutting concerns from business logic.
     *
     * 4. Transaction Management:
     *    Provides declarative and programmatic transaction support.
     *
     * 5. Data Access Integration:
     *    Integrates with JDBC, ORM frameworks and other data
     *    access technologies.
     *
     * 6. Web Development:
     *    Provides support for building web applications and REST APIs.
     *
     * 7. Testing Support:
     *    Provides integration with testing frameworks.
     *
     * 8. Application Configuration:
     *    Supports different ways to configure application components.
     *
     *
     * 5. WHAT IS A LIGHTWEIGHT FRAMEWORK?
     *
     * Spring is commonly described as a lightweight framework.
     *
     * In this context, lightweight refers primarily to its
     * programming model and infrastructure approach.
     *
     * Spring encourages:
     *
     * - POJO-based application development.
     * - Loose coupling.
     * - Dependency Injection.
     * - Modular architecture.
     * - Testable application design.
     *
     * You do not need to make every application class inherit
     * from a Spring-specific base class.
     *
     * A normal Java class can be managed by Spring.
     *
     * Important:
     *
     * Lightweight does not mean that every Spring application
     * has a small memory footprint or requires no dependencies.
     *
     *
     * 6. WHAT IS A POJO?
     *
     * POJO stands for Plain Old Java Object.
     *
     * It refers to an ordinary Java object that is not required
     * to follow a special framework-specific inheritance model.
     *
     * Example:
     *
     * class Employee {
     *
     *     private int id;
     *     private String name;
     *
     *     public int getId() {
     *         return id;
     *     }
     *
     *     public void setId(int id) {
     *         this.id = id;
     *     }
     *
     *     public String getName() {
     *         return name;
     *     }
     *
     *     public void setName(String name) {
     *         this.name = name;
     *     }
     * }
     *
     * This is an ordinary Java class.
     *
     * Spring can manage instances of such classes without
     * requiring them to extend a Spring framework class.
     *
     * Benefits:
     *
     * - Less framework dependency in business classes.
     * - Easier unit testing.
     * - Better separation of responsibilities.
     * - Improved maintainability.
     *
     *
     * 7. WHAT IS A FRAMEWORK?
     *
     * A framework is a reusable software platform that provides
     * structure, rules and infrastructure for building applications.
     *
     * It allows developers to focus on application-specific
     * functionality instead of implementing everything from scratch.
     *
     * A framework generally controls important parts of the
     * application's execution flow.
     *
     * Developers provide application code that works within
     * the framework's structure.
     *
     *
     * 8. FRAMEWORK VS LIBRARY
     *
     * LIBRARY:
     *
     * A library provides reusable functionality.
     *
     * Your application calls the library when it needs
     * a particular operation.
     *
     * Example:
     *
     * List<String> names = new ArrayList<>();
     *
     * names.add("Vinay");
     *
     * Your code explicitly calls the library functionality.
     *
     * FRAMEWORK:
     *
     * A framework provides an overall structure and can
     * invoke your application code at predefined points.
     *
     * Example:
     *
     * Spring manages application components and invokes
     * framework-managed functionality when required.
     *
     * Important difference:
     *
     * Library:
     * Your code calls the library.
     *
     * Framework:
     * The framework controls the application flow and calls
     * your code where appropriate.
     *
     * This relationship is commonly called Inversion of Control.
     *
     *
     * 9. WHAT IS INVERSION OF CONTROL?
     *
     * Inversion of Control is an architectural principle in which
     * responsibility for controlling certain application operations
     * is transferred from application code to a framework or container.
     *
     * In traditional Java development:
     *
     * Your code creates and manages its dependencies.
     *
     * With Spring:
     *
     * The Spring IoC container manages objects and their
     * dependencies according to the application configuration.
     *
     * Example:
     *
     * Without IoC:
     *
     * EmployeeService creates EmployeeRepository.
     *
     * With Spring IoC:
     *
     * Spring creates and manages both objects and supplies
     * EmployeeRepository to EmployeeService.
     *
     * Important:
     *
     * IoC is a principle.
     *
     * Dependency Injection is one of the main techniques
     * used to implement IoC.
     *
     * We will study this in detail in the next file.
     *
     *
     * 10. WHAT IS DEPENDENCY INJECTION?
     *
     * Dependency Injection is a technique in which an object's
     * required dependencies are supplied from outside rather
     * than being created directly by that object.
     *
     * Example:
     *
     * class EmployeeService {
     *
     *     private final EmployeeRepository repository;
     *
     *     EmployeeService(EmployeeRepository repository) {
     *         this.repository = repository;
     *     }
     * }
     *
     * EmployeeService receives EmployeeRepository through
     * its constructor.
     *
     * It does not create the repository itself.
     *
     * Spring can manage the creation and injection of these objects.
     *
     * Benefits:
     *
     * - Loose coupling.
     * - Easier testing.
     * - Better maintainability.
     * - Easier replacement of implementations.
     *
     *
     * 11. WHAT IS THE SPRING CONTAINER?
     *
     * The Spring Container is the central component responsible
     * for managing Spring application objects.
     *
     * Its responsibilities include:
     *
     * - Creating objects.
     * - Configuring objects.
     * - Injecting dependencies.
     * - Managing bean lifecycles.
     * - Providing managed objects when requested.
     *
     * Objects managed by the Spring container are called beans.
     *
     * Two important container interfaces are:
     *
     * 1. BeanFactory.
     * 2. ApplicationContext.
     *
     * We will study them in a dedicated file.
     *
     *
     * 12. WHAT IS A SPRING BEAN?
     *
     * A Spring Bean is an object that is instantiated,
     * assembled and managed by the Spring IoC container.
     *
     * Example:
     *
     * @Component
     * class EmployeeService {
     *
     * }
     *
     * Spring can detect this component through component
     * scanning and register it as a bean.
     *
     * Beans can also be registered using Java configuration
     * with @Bean.
     *
     * Important:
     *
     * Not every Java object is automatically a Spring Bean.
     *
     * An object must be registered with or created by
     * the Spring container to be managed as a bean.
     *
     *
     * 13. SPRING FRAMEWORK ARCHITECTURE
     *
     * Spring Framework is modular.
     *
     * Its modules provide different capabilities that
     * can be used independently or together.
     *
     * Major conceptual areas:
     *
     * 1. Core Container.
     * 2. AOP and Instrumentation.
     * 3. Data Access and Integration.
     * 4. Web.
     * 5. Messaging.
     * 6. Testing.
     *
     * These modules are organized to provide a flexible
     * application development environment.
     *
     *
     * 14. CORE CONTAINER
     *
     * The Core Container provides the foundation of Spring.
     *
     * Important modules:
     *
     * 1. spring-core
     *
     *    Provides fundamental Spring utilities and
     *    infrastructure capabilities.
     *
     * 2. spring-jcl
     *
     *    Provides Spring's common logging abstraction.
     *
     * 3. spring-beans
     *
     *    Provides bean-related infrastructure.
     *
     * 4. spring-context
     *
     *    Provides ApplicationContext and additional
     *    application infrastructure.
     *
     * 5. spring-expression
     *
     *    Provides Spring Expression Language (SpEL).
     *
     * Core Container is the foundation for many other
     * Spring modules.
     *
     *
     * 15. AOP AND INSTRUMENTATION
     *
     * AOP stands for Aspect-Oriented Programming.
     *
     * It helps separate cross-cutting concerns from
     * the main business logic.
     *
     * Examples of cross-cutting concerns:
     *
     * - Logging.
     * - Security checks.
     * - Transaction management.
     * - Performance monitoring.
     *
     * Instead of repeating the same infrastructure logic
     * across many methods, AOP can apply it through
     * configured aspects.
     *
     * Instrumentation provides capabilities related to
     * class instrumentation and class loading.
     *
     * We will study Spring AOP separately.
     *
     *
     * 16. DATA ACCESS AND INTEGRATION
     *
     * This area provides support for interacting with
     * databases and integrating with other technologies.
     *
     * Important capabilities include:
     *
     * - JDBC integration.
     * - Transaction management.
     * - ORM integration.
     * - Object/XML mapping support.
     *
     * Spring can integrate with technologies such as:
     *
     * - JDBC.
     * - Hibernate.
     * - JPA.
     *
     * Important:
     *
     * Spring does not replace Hibernate or JPA.
     *
     * It provides integration and infrastructure support
     * for working with them.
     *
     *
     * 17. WEB MODULE
     *
     * Spring provides web application development support.
     *
     * Important technologies include:
     *
     * - Spring Web.
     * - Spring Web MVC.
     * - Spring WebFlux.
     *
     * Spring Web MVC provides a Servlet-based web framework.
     *
     * Spring WebFlux provides a reactive web framework.
     *
     * They support different application programming models.
     *
     * We will study Spring MVC separately.
     *
     *
     * 18. MESSAGING
     *
     * Spring provides messaging-related infrastructure.
     *
     * It supports integration with messaging technologies
     * and message-driven application patterns.
     *
     * Examples of technologies used with Spring:
     *
     * - JMS.
     * - RabbitMQ through Spring AMQP.
     * - Apache Kafka through Spring for Apache Kafka.
     *
     * These integrations are useful in distributed systems
     * and asynchronous communication.
     *
     *
     * 19. TESTING SUPPORT
     *
     * Spring provides testing infrastructure for
     * applications built using the framework.
     *
     * It integrates with testing tools such as:
     *
     * - JUnit.
     * - TestNG.
     * - Mockito.
     *
     * Spring Test provides support for testing Spring-managed
     * components and application contexts.
     *
     * Testing is essential for building reliable applications.
     *
     *
     * 20. SPRING FRAMEWORK MODULES
     *
     * Important Spring Framework modules and capabilities:
     *
     * spring-core:
     * Fundamental utilities.
     *
     * spring-beans:
     * Bean creation and management infrastructure.
     *
     * spring-context:
     * ApplicationContext and application infrastructure.
     *
     * spring-expression:
     * Spring Expression Language.
     *
     * spring-aop:
     * Aspect-oriented programming support.
     *
     * spring-jdbc:
     * JDBC integration.
     *
     * spring-tx:
     * Transaction management.
     *
     * spring-orm:
     * ORM integration.
     *
     * spring-web:
     * Web-related infrastructure.
     *
     * spring-webmvc:
     * Spring MVC framework.
     *
     * spring-webflux:
     * Reactive web framework.
     *
     * spring-test:
     * Testing support.
     *
     * These are examples of Spring Framework modules.
     * The complete module ecosystem is broader.
     *
     *
     * 21. WHAT IS THE SPRING ECOSYSTEM?
     *
     * The Spring ecosystem consists of the Spring Framework
     * and related projects that provide additional capabilities.
     *
     * Important projects include:
     *
     * 1. Spring Boot.
     * 2. Spring Data.
     * 3. Spring Security.
     * 4. Spring Cloud.
     * 5. Spring Batch.
     * 6. Spring Integration.
     * 7. Spring for Apache Kafka.
     * 8. Spring AMQP.
     *
     * Each project addresses a particular application
     * development requirement.
     *
     *
     * 22. WHAT IS SPRING BOOT?
     *
     * Spring Boot is a project built on top of the
     * Spring Framework.
     *
     * It simplifies the setup and development of
     * Spring applications.
     *
     * Important features:
     *
     * - Auto-configuration.
     * - Starter dependencies.
     * - Embedded server support.
     * - Externalized configuration.
     * - Production-oriented features.
     *
     * Spring Boot reduces the amount of manual configuration
     * generally required to create a Spring application.
     *
     * Important:
     *
     * Spring Boot does not replace Spring Framework.
     *
     * It uses Spring Framework and makes application
     * development easier.
     *
     *
     * 23. SPRING FRAMEWORK VS SPRING BOOT
     *
     * SPRING FRAMEWORK:
     *
     * - Provides the core application framework.
     * - Provides IoC and Dependency Injection.
     * - Provides AOP support.
     * - Provides transaction management.
     * - Provides web development support.
     * - Supports multiple configuration approaches.
     *
     * SPRING BOOT:
     *
     * - Builds on the Spring Framework.
     * - Provides auto-configuration.
     * - Provides starter dependencies.
     * - Simplifies application setup.
     * - Supports embedded servers.
     * - Provides production-oriented features.
     *
     * Simple understanding:
     *
     * Spring Framework provides the foundation.
     *
     * Spring Boot simplifies building and running
     * applications using that foundation.
     *
     *
     * 24. SPRING VS SPRING MVC VS SPRING BOOT
     *
     * SPRING:
     *
     * The overall framework and its infrastructure.
     *
     * SPRING MVC:
     *
     * The Servlet-based web framework within the
     * Spring ecosystem.
     *
     * SPRING BOOT:
     *
     * The application development project that simplifies
     * Spring configuration and setup.
     *
     * They are related, but they are not interchangeable names.
     *
     *
     * 25. WHAT IS A STARTER DEPENDENCY?
     *
     * A Spring Boot starter is a convenient dependency
     * descriptor that groups dependencies commonly
     * needed for a particular feature.
     *
     * Examples:
     *
     * spring-boot-starter-web:
     * Web application development.
     *
     * spring-boot-starter-data-jpa:
     * Spring Data JPA integration.
     *
     * spring-boot-starter-security:
     * Spring Security integration.
     *
     * spring-boot-starter-test:
     * Testing support.
     *
     * Starters reduce the need to identify and configure
     * every related dependency manually.
     *
     *
     * 26. SPRING FRAMEWORK VS JAVA EE / JAKARTA EE
     *
     * Jakarta EE is a set of specifications for
     * enterprise Java application development.
     *
     * Spring is an application framework with its
     * own programming model and infrastructure.
     *
     * They are not the same technology.
     *
     * Spring can integrate with several Jakarta EE
     * technologies and specifications.
     *
     * Examples:
     *
     * - Jakarta Servlet.
     * - Jakarta Persistence.
     * - Jakarta Validation.
     *
     * Spring MVC, for example, is built on the
     * Servlet API.
     *
     *
     * 27. SPRING FRAMEWORK VS SPRING BOOT VS SPRING CLOUD
     *
     * Spring Framework:
     *
     * Core application development infrastructure.
     *
     * Spring Boot:
     *
     * Simplifies application creation, configuration
     * and execution.
     *
     * Spring Cloud:
     *
     * Provides tools and patterns for distributed
     * systems and microservices.
     *
     * Examples of Spring Cloud capabilities:
     *
     * - Service discovery integrations.
     * - Centralized configuration.
     * - Gateway integrations.
     * - Distributed system patterns.
     *
     * Important:
     *
     * Spring Cloud is not a replacement for Spring Boot.
     *
     *
     * 28. EVOLUTION OF SPRING
     *
     * Spring has evolved significantly over time.
     *
     * Important milestones:
     *
     * - Early Spring versions introduced a lightweight
     *   alternative programming model for enterprise Java.
     *
     * - Annotation-based configuration became increasingly
     *   important.
     *
     * - Java-based configuration improved type-safe setup.
     *
     * - Spring Framework 5 introduced support for
     *   reactive programming through Spring WebFlux.
     *
     * - Spring Framework 6 introduced a Java 17 baseline
     *   and Jakarta EE 9+ namespace alignment.
     *
     * - Spring Framework 7 continues the framework's
     *   evolution for modern Java applications.
     *
     * Always check the compatibility requirements of
     * the Spring version selected for a project.
     *
     *
     * 29. SPRING FRAMEWORK VERSION COMPATIBILITY
     *
     * Different Spring Framework and Spring Boot
     * versions have different Java requirements.
     *
     * For example:
     *
     * Spring Framework 6 requires Java 17 or later.
     *
     * Spring Boot 3 requires Java 17 or later.
     *
     * Spring Boot 4 has a newer Java baseline.
     *
     * Important:
     *
     * Always check the official compatibility documentation
     * before selecting framework and Java versions.
     *
     * Do not mix incompatible versions of Spring libraries.
     *
     *
     * 30. WHERE IS SPRING USED?
     *
     * Spring is widely used to develop:
     *
     * - Enterprise applications.
     * - REST APIs.
     * - Banking applications.
     * - E-commerce applications.
     * - Microservices.
     * - Internal business systems.
     * - Data processing applications.
     * - Cloud-native applications.
     *
     * Example:
     *
     * An e-commerce backend may use:
     *
     * Spring Boot:
     * Application setup and execution.
     *
     * Spring MVC:
     * REST API request handling.
     *
     * Spring Data JPA:
     * Repository-based data access.
     *
     * Hibernate:
     * ORM implementation.
     *
     * Spring Security:
     * Authentication and authorization.
     *
     * Spring Cloud:
     * Distributed system integrations where required.
     *
     *
     * 31. BENEFITS OF SPRING
     *
     * 1. Loose coupling.
     * 2. Dependency Injection.
     * 3. Modular architecture.
     * 4. Easier unit testing.
     * 5. Transaction management.
     * 6. AOP support.
     * 7. Database integration.
     * 8. Web application development.
     * 9. Integration with other Java technologies.
     * 10. Flexible application configuration.
     * 11. Strong ecosystem.
     * 12. Support for enterprise application development.
     *
     *
     * 32. LIMITATIONS AND CHALLENGES OF SPRING
     *
     * Spring is powerful, but it also introduces concepts
     * that developers must understand.
     *
     * Common challenges:
     *
     * 1. Learning curve:
     *    The ecosystem contains many modules and abstractions.
     *
     * 2. Configuration complexity:
     *    Large applications may have complicated configuration.
     *
     * 3. Debugging:
     *    Understanding container behaviour and proxy-based
     *    functionality can require deeper knowledge.
     *
     * 4. Dependency management:
     *    Incompatible library versions can cause problems.
     *
     * 5. Runtime overhead:
     *    Spring applications have framework infrastructure
     *    and associated resource requirements.
     *
     * 6. Misuse of abstractions:
     *    Developers may use annotations without understanding
     *    the underlying behaviour.
     *
     * These challenges can be managed through good design
     * and proper understanding of the framework.
     *
     *
     * 33. IMPORTANT SPRING TERMINOLOGY
     *
     * Framework:
     * Reusable application development structure.
     *
     * IoC:
     * Inversion of Control.
     *
     * DI:
     * Dependency Injection.
     *
     * Container:
     * Manages application objects and their dependencies.
     *
     * Bean:
     * An object managed by the Spring container.
     *
     * ApplicationContext:
     * A central Spring container interface.
     *
     * AOP:
     * Aspect-Oriented Programming.
     *
     * POJO:
     * Plain Old Java Object.
     *
     * Auto-configuration:
     * Spring Boot's mechanism for configuring an application
     * based on available classes, properties and conditions.
     *
     * Starter:
     * A convenient dependency descriptor for a feature.
     *
     * ORM:
     * Object-Relational Mapping.
     *
     * Transaction:
     * A unit of work whose operations are managed
     * according to transaction rules.
     *
     *
     * 34. INTERVIEW QUESTIONS - BASIC LEVEL
     *
     * 1. What is the Spring Framework?
     *
     * 2. Why was Spring created?
     *
     * 3. What problems does Spring solve?
     *
     * 4. Why is Spring called a lightweight framework?
     *
     * 5. What is a POJO?
     *
     * 6. What is a framework?
     *
     * 7. What is the difference between a framework and a library?
     *
     * 8. What is Inversion of Control?
     *
     * 9. What is Dependency Injection?
     *
     * 10. What is a Spring Bean?
     *
     * 11. What is the Spring Container?
     *
     * 12. What are the main modules of Spring?
     *
     * 13. What is Spring Boot?
     *
     * 14. What is a Spring Boot starter?
     *
     * 15. What is the difference between Spring and Spring Boot?
     *
     *
     * 35. INTERVIEW QUESTIONS - INTERMEDIATE LEVEL
     *
     * 1. Explain the high-level architecture of Spring.
     *
     * 2. What are the responsibilities of the Core Container?
     *
     * 3. What is the difference between BeanFactory
     *    and ApplicationContext?
     *
     * 4. How does Spring help achieve loose coupling?
     *
     * 5. How does Spring manage object creation?
     *
     * 6. What is the relationship between IoC and DI?
     *
     * 7. What is the difference between Spring MVC
     *    and Spring WebFlux?
     *
     * 8. Does Spring replace Hibernate?
     *
     * 9. What is the purpose of Spring AOP?
     *
     * 10. How does Spring integrate with JDBC and JPA?
     *
     * 11. What is the difference between Spring Framework
     *     and Jakarta EE?
     *
     * 12. What is the difference between Spring Boot
     *     and Spring Cloud?
     *
     *
     * 36. INTERVIEW QUESTIONS - ADVANCED LEVEL
     *
     * 1. Explain Inversion of Control with a practical example.
     *
     * 2. Why is Dependency Injection preferred over
     *    direct object creation?
     *
     * 3. How does Spring achieve framework-level control
     *    over application objects?
     *
     * 4. Why does Spring encourage POJO-based development?
     *
     * 5. How does Spring's modular architecture help
     *    application development?
     *
     * 6. Can Spring be used without Spring Boot?
     *
     * 7. Can Spring Boot be used without the Spring Framework?
     *
     * 8. Does Spring automatically manage every object
     *    created using the new keyword?
     *
     * 9. Is every Java class in a Spring application
     *    automatically a Spring Bean?
     *
     * 10. Why is Spring described as non-invasive?
     *
     *
     * 37. INTERVIEW QUESTIONS - REAL-WORLD SCENARIOS
     *
     * 1. Your service class directly creates a repository
     *    object using new. What design problem can this cause?
     *
     * 2. You want to replace a database repository implementation
     *    without changing your business service. Which Spring
     *    concept helps you achieve this?
     *
     * 3. Your application has logging code repeated across
     *    many service methods. Which Spring capability can
     *    help separate this concern?
     *
     * 4. Your application needs database operations to execute
     *    as a single transaction. Which Spring capability
     *    can help?
     *
     * 5. You are building a REST API and want to avoid
     *    manually configuring every web infrastructure
     *    component. Which Spring project simplifies this?
     *
     * 6. Your application has many Java objects that depend
     *    on each other. Which Spring component is responsible
     *    for managing their creation and dependencies?
     *
     * 7. Your application uses Hibernate. Does Spring
     *    replace Hibernate, or integrate with it?
     *
     * 8. You need to build a reactive web application.
     *    Which Spring web framework can you consider?
     *
     *
     * 38. QUICK REVISION
     *
     * Spring:
     * Open-source Java application development framework.
     *
     * IoC:
     * Principle of transferring control to a framework
     * or container.
     *
     * DI:
     * Supplying dependencies from outside an object.
     *
     * Spring Container:
     * Manages Spring application objects.
     *
     * Spring Bean:
     * Object managed by the Spring container.
     *
     * POJO:
     * Ordinary Java object.
     *
     * AOP:
     * Separates cross-cutting concerns.
     *
     * Spring Core:
     * Provides fundamental Spring infrastructure.
     *
     * Spring MVC:
     * Servlet-based web framework.
     *
     * Spring Boot:
     * Simplifies Spring application setup and development.
     *
     * Spring Data:
     * Provides abstractions for data access.
     *
     * Spring Security:
     * Provides application security infrastructure.
     *
     * Spring Cloud:
     * Supports distributed system development.
     *
     * =====================================================================
     *                     END OF INTRODUCTION TO SPRING
     * =====================================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Introduction to Spring , Happy Learning ..!");
    }
}
