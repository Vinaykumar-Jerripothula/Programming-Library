
public class IntroductionToSpringBoot {

    /*
     * ================================================================
     * SPRING BOOT - FUNDAMENTALS
     * FILE 01: INTRODUCTION TO SPRING BOOT
     * ================================================================
     *
     * CONTENTS
     *
     * 1.  Introduction
     * 2.  What Is Spring Boot?
     * 3.  Why Was Spring Boot Introduced?
     * 4.  Problems with Traditional Spring Development
     * 5.  The Purpose of Spring Boot
     * 6.  Spring Boot and the Spring Ecosystem
     * 7.  Spring Boot History and Evolution
     * 8.  Spring Boot Design Philosophy
     * 9.  Opinionated Framework
     * 10. Convention over Configuration
     * 11. Standalone Applications
     * 12. Production-Ready Applications
     * 13. Types of Applications Built with Spring Boot
     * 14. Spring Boot Use Cases
     * 15. Spring Boot and Microservices
     * 16. Spring Boot and Enterprise Development
     * 17. Spring Boot Advantages
     * 18. Spring Boot Limitations
     * 19. Spring Boot Terminology
     * 20. Common Misconceptions
     * 21. Interview Questions
     * 22. Practical Understanding
     * 23. Quick Revision
     *
     *
     * ================================================================
     * 1. INTRODUCTION
     * ================================================================
     *
     * Spring Boot is one of the most widely used technologies
     * for developing Java backend applications.
     *
     * It is built on top of the Spring Framework.
     *
     * It simplifies the process of developing, configuring,
     * running, testing and deploying Spring applications.
     *
     * Before Spring Boot, developers had to perform considerable
     * configuration to prepare a Spring application.
     *
     * Spring Boot reduces much of that configuration by
     * providing sensible defaults and automatic setup.
     *
     * Today, Spring Boot is commonly used for:
     *
     * - REST API development.
     * - Enterprise backend applications.
     * - Microservices.
     * - Web applications.
     * - Cloud-native applications.
     * - Distributed systems.
     * - Backend services.
     *
     * It allows developers to focus more on application
     * functionality rather than repetitive infrastructure setup.
     *
     *
     * ================================================================
     * 2. WHAT IS SPRING BOOT?
     * ================================================================
     *
     * Spring Boot is an open-source, Java-based framework
     * built on top of the Spring Framework.
     *
     * It simplifies the development of Spring applications
     * by providing:
     *
     * 1. Auto-configuration.
     * 2. Starter dependencies.
     * 3. Embedded servers.
     * 4. Externalized configuration.
     * 5. Production-ready features.
     * 6. Simplified application startup.
     *
     * A developer can create a Spring Boot application,
     * add the required dependencies and start developing
     * application functionality with minimal initial setup.
     *
     *
     * IMPORTANT:
     *
     * Spring Boot is not a replacement for Spring Framework.
     *
     * Spring Boot uses Spring Framework underneath.
     *
     * Spring Framework provides the underlying programming
     * infrastructure.
     *
     * Spring Boot simplifies configuring and using that
     * infrastructure.
     *
     *
     * SIMPLE EXAMPLE:
     *
     * Suppose we want to develop a REST API.
     *
     * With Spring Boot, we can create a project with the
     * required web dependency and start developing controllers.
     *
     * Spring Boot can automatically configure much of the
     * required web infrastructure.
     *
     * We do not need to manually configure every component.
     *
     *
     * ================================================================
     * 3. WHY WAS SPRING BOOT INTRODUCED?
     * ================================================================
     *
     * The Spring Framework was already powerful.
     *
     * It provided:
     *
     * - Inversion of Control.
     * - Dependency Injection.
     * - Aspect-Oriented Programming.
     * - Transaction Management.
     * - Spring MVC.
     * - Data Access Integration.
     *
     * However, developing a complete application required
     * developers to configure many components.
     *
     * As applications became larger, configuration and
     * dependency management became increasingly important.
     *
     * Developers wanted:
     *
     * - Less repetitive configuration.
     * - Faster project setup.
     * - Easier dependency management.
     * - Embedded application servers.
     * - Simpler deployment.
     * - Production monitoring support.
     *
     * Spring Boot was introduced to address these requirements.
     *
     * Its central goal was to make Spring application
     * development easier and faster.
     *
     *
     * ================================================================
     * 4. PROBLEMS WITH TRADITIONAL SPRING DEVELOPMENT
     * ================================================================
     *
     * Consider developing a web application using the
     * traditional Spring Framework.
     *
     * Depending on the application, developers might need
     * to configure:
     *
     * 1. Spring dependencies.
     * 2. Spring MVC infrastructure.
     * 3. DispatcherServlet.
     * 4. Component scanning.
     * 5. View resolvers or message converters.
     * 6. DataSource.
     * 7. Transaction manager.
     * 8. Application server deployment.
     * 9. Application-specific configuration.
     *
     * These configurations are valid and sometimes necessary.
     *
     * However, repeatedly performing similar setup across
     * projects increases development effort.
     *
     *
     * EXAMPLE:
     *
     * Imagine creating five different REST API projects.
     *
     * All five projects may require:
     *
     * - Spring MVC.
     * - JSON conversion.
     * - An embedded or external server.
     * - Component scanning.
     *
     * Without convenient defaults, developers may repeatedly
     * configure similar infrastructure.
     *
     * Spring Boot reduces this repetitive work.
     *
     *
     * ================================================================
     * 5. THE PURPOSE OF SPRING BOOT
     * ================================================================
     *
     * Spring Boot focuses on simplifying application development.
     *
     * Its major objectives are:
     *
     *
     * A. SIMPLIFIED CONFIGURATION
     *
     * Spring Boot automatically configures many components
     * based on the application's dependencies and settings.
     *
     *
     * B. SIMPLIFIED DEPENDENCY MANAGEMENT
     *
     * Starter dependencies provide convenient groups of
     * related libraries.
     *
     *
     * C. SIMPLIFIED EXECUTION
     *
     * Applications can run using embedded servers without
     * requiring a separately installed application server.
     *
     *
     * D. SIMPLIFIED DEPLOYMENT
     *
     * Applications can commonly be packaged as executable JARs.
     *
     *
     * E. PRODUCTION SUPPORT
     *
     * Spring Boot provides Actuator and integrates with
     * monitoring and observability technologies.
     *
     *
     * F. DEVELOPER PRODUCTIVITY
     *
     * Developers can spend more time implementing business
     * requirements instead of configuring infrastructure.
     *
     *
     * ================================================================
     * 6. SPRING BOOT AND THE SPRING ECOSYSTEM
     * ================================================================
     *
     * Spring Boot belongs to the larger Spring ecosystem.
     *
     * The Spring ecosystem contains multiple projects.
     *
     * IMPORTANT PROJECTS:
     *
     *
     * SPRING FRAMEWORK:
     *
     * Provides core infrastructure such as:
     *
     * - IoC Container.
     * - Dependency Injection.
     * - AOP.
     * - Spring MVC.
     * - Transaction management.
     *
     *
     * SPRING BOOT:
     *
     * Simplifies the configuration and execution of
     * Spring applications.
     *
     *
     * SPRING DATA:
     *
     * Simplifies data-access development and integrates
     * with technologies such as JPA, JDBC and Redis.
     *
     *
     * SPRING SECURITY:
     *
     * Provides authentication, authorization and
     * application security infrastructure.
     *
     *
     * SPRING CLOUD:
     *
     * Provides tools and integrations for distributed
     * systems and cloud-native application development.
     *
     *
     * SPRING BATCH:
     *
     * Supports batch processing of large amounts of data.
     *
     *
     * SPRING INTEGRATION:
     *
     * Supports enterprise integration patterns.
     *
     *
     * SPRING FOR APACHE KAFKA:
     *
     * Provides integration with Apache Kafka.
     *
     *
     * IMPORTANT:
     *
     * Spring Boot integrates with many of these projects.
     *
     * They are not all built into the Spring Boot core.
     *
     * Their functionality is generally added through
     * appropriate dependencies and configuration.
     *
     *
     * ================================================================
     * 7. SPRING BOOT HISTORY AND EVOLUTION
     * ================================================================
     *
     * Spring Boot was created by the Spring team at Pivotal
     * to simplify Spring application development.
     *
     * IMPORTANT HISTORICAL MILESTONES:
     *
     * 2013:
     *
     * Spring Boot development began.
     *
     *
     * 2014:
     *
     * Spring Boot 1.0 was released.
     *
     *
     * SPRING BOOT 1.x:
     *
     * Established the initial Spring Boot programming model
     * and simplified application configuration.
     *
     *
     * SPRING BOOT 2.x:
     *
     * Introduced major improvements, including support for
     * newer Spring Framework generations and reactive
     * application development through Spring WebFlux.
     *
     *
     * SPRING BOOT 3.x:
     *
     * Introduced major platform changes:
     *
     * - Requires Java 17 or later.
     * - Uses Spring Framework 6.
     * - Migrated from Java EE javax packages to
     *   Jakarta EE jakarta packages where applicable.
     * - Improved native image and AOT support.
     *
     *
     * SPRING BOOT 4.x:
     *
     * Represents the next major generation of Spring Boot,
     * based on the Spring Framework 7 generation.
     *
     * It continues the evolution of the framework and
     * its modular architecture.
     *
     *
     * IMPORTANT VERSIONING RULE:
     *
     * Spring Boot, Spring Framework and Java versions
     * must be compatible.
     *
     * Always consult the official Spring Boot system
     * requirements and release documentation when selecting
     * versions for a project.
     *
     *
     * ================================================================
     * 8. SPRING BOOT DESIGN PHILOSOPHY
     * ================================================================
     *
     * Spring Boot follows several important design principles.
     *
     * 1. Convention over Configuration.
     *
     * 2. Opinionated Defaults.
     *
     * 3. Auto-Configuration.
     *
     * 4. Developer Productivity.
     *
     * 5. Standalone Application Execution.
     *
     * 6. Production Readiness.
     *
     * These principles reduce unnecessary configuration
     * while keeping the application customizable.
     *
     *
     * ================================================================
     * 9. OPINIONATED FRAMEWORK
     * ================================================================
     *
     * An opinionated framework provides recommended defaults
     * and conventions for common development requirements.
     *
     * Spring Boot follows this approach.
     *
     * For example:
     *
     * When a suitable web starter is present, Spring Boot
     * can configure a web application with an embedded
     * server and common Spring MVC infrastructure.
     *
     * Developers do not have to manually configure
     * every component.
     *
     *
     * IMPORTANT:
     *
     * Opinionated does not mean inflexible.
     *
     * Developers can override default configurations
     * when application requirements demand it.
     *
     *
     * ================================================================
     * 10. CONVENTION OVER CONFIGURATION
     * ================================================================
     *
     * Convention over Configuration means using established
     * defaults instead of explicitly configuring every detail.
     *
     * Example:
     *
     * Spring Boot applications commonly use:
     *
     * - application.properties
     * - application.yml
     * - A conventional source directory.
     * - A conventional resource directory.
     * - Standard auto-configuration rules.
     *
     * When developers follow these conventions,
     * Spring Boot can automatically discover and configure
     * many application components.
     *
     *
     * IMPORTANT:
     *
     * Developers can still customize these conventions.
     *
     * Convention over Configuration reduces configuration
     * effort; it does not remove the ability to configure.
     *
     *
     * ================================================================
     * 11. STANDALONE APPLICATIONS
     * ================================================================
     *
     * A standalone application can run independently
     * without requiring a separately managed application
     * server installation.
     *
     * Spring Boot supports standalone execution.
     *
     * For example, a Spring Boot web application can
     * include an embedded server such as Tomcat.
     *
     * The application can be packaged into an executable JAR.
     *
     * It can then be started using:
     *
     * java -jar application.jar
     *
     *
     * IMPORTANT:
     *
     * Embedded does not mean the server is written into
     * the application source code.
     *
     * The server library is included as a dependency
     * and packaged appropriately with the application.
     *
     *
     * ================================================================
     * 12. PRODUCTION-READY APPLICATIONS
     * ================================================================
     *
     * Developing an application is only one part of
     * software engineering.
     *
     * Applications must also be monitored and maintained
     * after deployment.
     *
     * Spring Boot provides production-oriented support.
     *
     * Examples:
     *
     * - Health information.
     * - Application metrics.
     * - Environment information.
     * - Logging integration.
     * - Monitoring endpoints.
     *
     * Spring Boot Actuator provides many of these capabilities.
     *
     * It can integrate with monitoring systems and observability
     * platforms.
     *
     *
     * IMPORTANT:
     *
     * Having Actuator does not automatically make an application
     * production-ready.
     *
     * Developers must still configure security, monitoring,
     * deployment, resilience and operational practices properly.
     *
     *
     * ================================================================
     * 13. TYPES OF APPLICATIONS BUILT WITH SPRING BOOT
     * ================================================================
     *
     * Spring Boot supports several application types.
     *
     *
     * A. WEB APPLICATIONS
     *
     * Applications that provide web pages and browser-based
     * functionality.
     *
     *
     * B. REST API APPLICATIONS
     *
     * Applications that expose HTTP endpoints and exchange
     * data, commonly using JSON.
     *
     *
     * C. MICROSERVICES
     *
     * Independently deployable services that implement
     * specific business capabilities.
     *
     *
     * D. BATCH APPLICATIONS
     *
     * Applications that process data in batches.
     *
     *
     * E. SCHEDULED APPLICATIONS
     *
     * Applications that execute tasks at specified times
     * or intervals.
     *
     *
     * F. MESSAGING APPLICATIONS
     *
     * Applications that communicate through message brokers
     * and messaging systems.
     *
     *
     * G. REACTIVE APPLICATIONS
     *
     * Applications that use non-blocking, reactive programming
     * models through technologies such as Spring WebFlux.
     *
     *
     * H. COMMAND-LINE APPLICATIONS
     *
     * Applications that execute tasks without requiring
     * a web server.
     *
     *
     * ================================================================
     * 14. SPRING BOOT USE CASES
     * ================================================================
     *
     * REAL-WORLD EXAMPLES:
     *
     *
     * BANKING:
     *
     * - Account management.
     * - Transaction processing.
     * - Payment services.
     * - Customer management.
     *
     *
     * E-COMMERCE:
     *
     * - Product management.
     * - Shopping cart.
     * - Order processing.
     * - Payment integration.
     *
     *
     * HEALTHCARE:
     *
     * - Patient management.
     * - Appointment scheduling.
     * - Medical record services.
     *
     *
     * EDUCATION:
     *
     * - Student management.
     * - Course registration.
     * - Examination systems.
     *
     *
     * ENTERPRISE:
     *
     * - Employee management.
     * - Inventory systems.
     * - Internal business applications.
     *
     *
     * ================================================================
     * 15. SPRING BOOT AND MICROSERVICES
     * ================================================================
     *
     * Spring Boot is widely used to develop microservices.
     *
     * A microservice is an independently deployable
     * application or service that implements a specific
     * business capability.
     *
     * Example:
     *
     * An e-commerce system might contain:
     *
     * - User Service.
     * - Product Service.
     * - Order Service.
     * - Payment Service.
     * - Notification Service.
     *
     * Each service can be developed and deployed independently.
     *
     * Spring Boot simplifies building these services.
     *
     *
     * IMPORTANT:
     *
     * Spring Boot does not automatically turn an application
     * into a microservice.
     *
     * Microservices require architectural decisions involving:
     *
     * - Service boundaries.
     * - Communication.
     * - Data ownership.
     * - Deployment.
     * - Monitoring.
     * - Fault tolerance.
     *
     * Spring Cloud provides additional tools for certain
     * distributed-system requirements.
     *
     *
     * ================================================================
     * 16. SPRING BOOT AND ENTERPRISE DEVELOPMENT
     * ================================================================
     *
     * Enterprise applications often require:
     *
     * - Security.
     * - Database connectivity.
     * - Transaction management.
     * - Logging.
     * - Monitoring.
     * - External system integration.
     * - Testing.
     * - Deployment automation.
     *
     * Spring Boot integrates with the wider Spring ecosystem
     * and other Java libraries to support these requirements.
     *
     * It is suitable for both small applications and
     * large enterprise systems.
     *
     *
     * ================================================================
     * 17. SPRING BOOT ADVANTAGES
     * ================================================================
     *
     * 1. REDUCED CONFIGURATION
     *
     *    Less repetitive infrastructure setup.
     *
     *
     * 2. FASTER DEVELOPMENT
     *
     *    Developers can focus on business requirements.
     *
     *
     * 3. STARTER DEPENDENCIES
     *
     *    Convenient dependency groups.
     *
     *
     * 4. AUTO-CONFIGURATION
     *
     *    Automatic configuration based on application
     *    dependencies and settings.
     *
     *
     * 5. EMBEDDED SERVERS
     *
     *    Convenient standalone execution.
     *
     *
     * 6. PRODUCTION SUPPORT
     *
     *    Actuator and monitoring integrations.
     *
     *
     * 7. SPRING ECOSYSTEM INTEGRATION
     *
     *    Integration with Spring Data, Spring Security,
     *    Spring Cloud and other projects.
     *
     *
     * 8. LARGE ECOSYSTEM
     *
     *    Extensive community, documentation and libraries.
     *
     *
     * 9. TESTING SUPPORT
     *
     *    Integration with Spring testing infrastructure
     *    and popular Java testing libraries.
     *
     *
     * 10. DEPLOYMENT FLEXIBILITY
     *
     *     Supports executable JARs, containers and
     *     other deployment models.
     *
     *
     * ================================================================
     * 18. SPRING BOOT LIMITATIONS
     * ================================================================
     *
     * Spring Boot simplifies development, but it has
     * limitations and trade-offs.
     *
     *
     * 1. AUTO-CONFIGURATION COMPLEXITY
     *
     *    Developers may find it difficult to understand
     *    why a particular configuration is active.
     *
     *
     * 2. STARTUP OVERHEAD
     *
     *    Applications may initialize many components
     *    during startup.
     *
     *
     * 3. DEPENDENCY MANAGEMENT
     *
     *    Incorrect dependency overrides can create
     *    compatibility problems.
     *
     *
     * 4. MEMORY USAGE
     *
     *    A Spring application may consume more resources
     *    than a small application built with a minimal framework.
     *
     *
     * 5. ABSTRACTION COMPLEXITY
     *
     *    Developers must understand the underlying Spring
     *    Framework when debugging advanced behavior.
     *
     *
     * 6. VERSION COMPATIBILITY
     *
     *    Java, Spring Boot and third-party dependencies
     *    must be compatible.
     *
     *
     * 7. NOT ALWAYS THE RIGHT TOOL
     *
     *    Very small command-line utilities or highly
     *    resource-constrained applications may not need
     *    the full Spring ecosystem.
     *
     *
     * ================================================================
     * 19. SPRING BOOT TERMINOLOGY
     * ================================================================
     *
     * SPRING BOOT:
     * Framework built on Spring Framework to simplify
     * application development.
     *
     *
     * AUTO-CONFIGURATION:
     * Automatic configuration based on available
     * dependencies and application conditions.
     *
     *
     * STARTER:
     * A convenient dependency that brings together
     * commonly required libraries.
     *
     *
     * EMBEDDED SERVER:
     * A server included as an application dependency
     * and packaged for convenient execution.
     *
     *
     * EXTERNALIZED CONFIGURATION:
     * Configuration supplied from outside the application
     * code, such as properties and environment variables.
     *
     *
     * ACTUATOR:
     * Spring Boot project providing production-oriented
     * monitoring and management features.
     *
     *
     * SPRING INITIALIZR:
     * A project-generation tool for creating Spring Boot
     * project structures.
     *
     *
     * SPRINGAPPLICATION:
     * The Spring Boot class used to bootstrap an application.
     *
     *
     * @SPRINGBOOTAPPLICATION:
     * Main annotation commonly used on a Spring Boot
     * application class.
     *
     *
     * EXECUTABLE JAR:
     * A JAR packaged so that the application can be started
     * using the Java launcher.
     *
     *
     * ================================================================
     * 20. COMMON MISCONCEPTIONS
     * ================================================================
     *
     * MISCONCEPTION 1:
     *
     * Spring Boot and Spring Framework are the same.
     *
     * CORRECTION:
     *
     * Spring Boot is built on top of Spring Framework.
     *
     *
     * MISCONCEPTION 2:
     *
     * Spring Boot replaces Spring MVC.
     *
     * CORRECTION:
     *
     * Spring Boot can configure and run applications
     * that use Spring MVC.
     *
     *
     * MISCONCEPTION 3:
     *
     * Spring Boot removes the need for configuration.
     *
     * CORRECTION:
     *
     * It reduces manual configuration through defaults
     * and auto-configuration.
     *
     *
     * MISCONCEPTION 4:
     *
     * Spring Boot is only used for microservices.
     *
     * CORRECTION:
     *
     * It supports many application types, including
     * monolithic and modular applications.
     *
     *
     * MISCONCEPTION 5:
     *
     * Every Spring Boot application requires a web server.
     *
     * CORRECTION:
     *
     * Non-web applications can run without an embedded
     * web server.
     *
     *
     * MISCONCEPTION 6:
     *
     * Spring Boot automatically makes an application
     * production-ready.
     *
     * CORRECTION:
     *
     * Production readiness still requires proper
     * security, monitoring, testing and deployment practices.
     *
     *
     * MISCONCEPTION 7:
     *
     * Spring Boot and Spring Cloud are the same.
     *
     * CORRECTION:
     *
     * Spring Boot simplifies application development.
     *
     * Spring Cloud provides additional tools for distributed
     * and cloud-native systems.
     *
     *
     * ================================================================
     * 21. INTERVIEW QUESTIONS
     * ================================================================
     *
     * BASIC:
     *
     * 1. What is Spring Boot?
     *
     * 2. Why was Spring Boot introduced?
     *
     * 3. Is Spring Boot a replacement for Spring Framework?
     *
     * 4. What are the major features of Spring Boot?
     *
     * 5. What is auto-configuration?
     *
     * 6. What is a Spring Boot starter?
     *
     * 7. What is an embedded server?
     *
     * 8. What is Spring Boot Actuator?
     *
     * 9. What is Spring Initializr?
     *
     * 10. What is an executable JAR?
     *
     *
     * INTERMEDIATE:
     *
     * 11. How does Spring Boot simplify Spring development?
     *
     * 12. What is the difference between Spring Framework
     *     and Spring Boot?
     *
     * 13. What is the convention-over-configuration principle?
     *
     * 14. What does it mean when a framework is opinionated?
     *
     * 15. Can Spring Boot applications run without a web server?
     *
     * 16. Can Spring Boot be used for monolithic applications?
     *
     * 17. What is the relationship between Spring Boot
     *     and Spring Cloud?
     *
     * 18. What is the role of Actuator?
     *
     * 19. What is externalized configuration?
     *
     * 20. What are the advantages of Spring Boot?
     *
     *
     * ADVANCED:
     *
     * 21. How does Spring Boot reduce configuration?
     *
     * 22. Does Spring Boot internally use the Spring IoC Container?
     *
     * 23. What is the difference between auto-configuration
     *     and component scanning?
     *
     * 24. Why can a Spring Boot application start without
     *     a separately installed application server?
     *
     * 25. What are the trade-offs of using Spring Boot?
     *
     * 26. What is the difference between Spring Boot and
     *     Spring Cloud?
     *
     * 27. How does Spring Boot fit into microservice architecture?
     *
     * 28. Why is understanding Spring Framework important
     *     when working with Spring Boot?
     *
     *
     * ================================================================
     * 22. PRACTICAL UNDERSTANDING
     * ================================================================
     *
     * SCENARIO 1:
     *
     * You are asked to create a REST API for an e-commerce
     * application.
     *
     * QUESTION:
     *
     * Why would you choose Spring Boot?
     *
     * EXPECTED UNDERSTANDING:
     *
     * It provides convenient web infrastructure, dependency
     * management, configuration and application execution.
     *
     *
     * SCENARIO 2:
     *
     * Your company has five backend services.
     *
     * QUESTION:
     *
     * Can all five be developed using Spring Boot?
     *
     * EXPECTED UNDERSTANDING:
     *
     * Yes. Each service can be an independent Spring Boot
     * application with its own dependencies and configuration.
     *
     *
     * SCENARIO 3:
     *
     * Your application needs to run in development,
     * testing and production environments.
     *
     * QUESTION:
     *
     * Can Spring Boot support different configurations
     * for these environments?
     *
     * EXPECTED UNDERSTANDING:
     *
     * Yes. Spring Boot supports externalized configuration
     * and profiles.
     *
     *
     * SCENARIO 4:
     *
     * Your application must expose health information
     * to a monitoring system.
     *
     * QUESTION:
     *
     * Which Spring Boot project can help?
     *
     * EXPECTED UNDERSTANDING:
     *
     * Spring Boot Actuator.
     *
     *
     * ================================================================
     * 23. QUICK REVISION
     * ================================================================
     *
     * Spring Boot:
     *
     * - Built on top of Spring Framework.
     * - Simplifies Spring application development.
     * - Provides auto-configuration.
     * - Provides starter dependencies.
     * - Supports embedded servers.
     * - Supports externalized configuration.
     * - Provides Actuator for production-oriented features.
     *
     *
     * DESIGN PHILOSOPHY:
     *
     * - Convention over Configuration.
     * - Opinionated Defaults.
     * - Developer Productivity.
     * - Simplified Deployment.
     *
     *
     * APPLICATION TYPES:
     *
     * - REST APIs.
     * - Web Applications.
     * - Microservices.
     * - Batch Applications.
     * - Messaging Applications.
     * - Reactive Applications.
     * - Command-Line Applications.
     *
     *
     * MOST IMPORTANT POINT:
     *
     * Spring Boot simplifies the use of Spring Framework.
     *
     * It does not replace the underlying Spring infrastructure.
     *
     *
     * ================================================================
     * END OF FILE 01
     * ================================================================
     */

    public static void main(String[] args) {

        System.out.println(
            "Introduction to Spring Boot"
        );

    }

}
