
public class SpringBootMainClass {

    /*
     * ============================================================
     *                  SPRING BOOT MAIN CLASS
     * ============================================================
     *
     * 1. WHAT IS THE SPRING BOOT MAIN CLASS?
     *
     * The Spring Boot main class is the primary entry point of a
     * Spring Boot application.
     *
     * It contains the main() method that allows the JVM to start
     * executing the application.
     *
     * It is commonly annotated with @SpringBootApplication.
     *
     * Example:
     *
     * @SpringBootApplication
     * public class EmployeeApplication {
     *
     *     public static void main(String[] args) {
     *         SpringApplication.run(EmployeeApplication.class, args);
     *     }
     * }
     *
     * Two important elements:
     *
     * 1. main()
     *    - Entry point recognized by the JVM.
     *
     * 2. @SpringBootApplication
     *    - Enables Spring Boot application configuration,
     *      auto-configuration, and component scanning.
     *
     *
     * 2. UNDERSTANDING THE MAIN() METHOD
     *
     * The main() method is the standard Java application entry point.
     *
     * Syntax:
     *
     * public static void main(String[] args)
     *
     * public:
     * - Allows the JVM to access the method.
     *
     * static:
     * - Allows the JVM to invoke it without creating an object
     *   of the class.
     *
     * void:
     * - Indicates that the method does not return a value.
     *
     * String[] args:
     * - Receives command-line arguments.
     *
     * In Spring Boot:
     *
     * public static void main(String[] args) {
     *     SpringApplication.run(EmployeeApplication.class, args);
     * }
     *
     * The JVM invokes main(), and SpringApplication.run() starts
     * the Spring Boot application.
     *
     *
     * 3. WHAT IS @SPRINGBOOTAPPLICATION?
     *
     * @SpringBootApplication is a convenience annotation provided
     * by Spring Boot.
     *
     * It combines three important annotations:
     *
     * 1. @SpringBootConfiguration
     * 2. @EnableAutoConfiguration
     * 3. @ComponentScan
     *
     * Conceptually:
     *
     * @SpringBootApplication
     *
     * is equivalent to:
     *
     * @SpringBootConfiguration
     * @EnableAutoConfiguration
     * @ComponentScan
     *
     * These annotations provide the foundation for a typical
     * Spring Boot application.
     *
     *
     * 4. UNDERSTANDING @SPRINGBOOTCONFIGURATION
     *
     * @SpringBootConfiguration identifies a class as a source
     * of Spring Boot application configuration.
     *
     * It is a specialized form of @Configuration.
     *
     * @Configuration indicates that a class can declare Spring
     * beans using @Bean methods.
     *
     * Example:
     *
     * @SpringBootConfiguration
     * public class ApplicationConfiguration {
     *
     *     @Bean
     *     public MyService myService() {
     *         return new MyService();
     *     }
     * }
     *
     * In a normal Spring Boot application, we usually do not need
     * to declare @SpringBootConfiguration separately because
     * @SpringBootApplication already includes it.
     *
     *
     * 5. UNDERSTANDING @ENABLEAUTOCONFIGURATION
     *
     * @EnableAutoConfiguration enables Spring Boot's
     * auto-configuration mechanism.
     *
     * Auto-configuration attempts to configure application
     * infrastructure based on:
     *
     * - Dependencies available on the classpath.
     * - Existing Spring beans.
     * - Application properties.
     * - Other configuration conditions.
     *
     * Example:
     *
     * When web dependencies are available, Spring Boot can
     * configure suitable web application infrastructure.
     *
     * When database dependencies and configuration are available,
     * Spring Boot can configure a DataSource.
     *
     * Important:
     *
     * Auto-configuration is conditional. It does not blindly
     * configure every available feature.
     *
     * We will study auto-configuration in detail in its
     * dedicated topic.
     *
     *
     * 6. UNDERSTANDING @COMPONENTSCAN
     *
     * @ComponentScan tells Spring where to search for
     * Spring-managed components.
     *
     * It can discover classes annotated with stereotype annotations
     * such as:
     *
     * - @Component
     * - @Service
     * - @Repository
     * - @Controller
     * - @RestController
     *
     * Example:
     *
     * com.company.employee
     * |
     * +-- EmployeeApplication.java
     * |
     * +-- controller/
     * |   +-- EmployeeController.java
     * |
     * +-- service/
     * |   +-- EmployeeService.java
     * |
     * +-- repository/
     *     +-- EmployeeRepository.java
     *
     * If EmployeeApplication is in com.company.employee,
     * its default component scan includes that package and
     * its subpackages.
     *
     * Important:
     *
     * The default scan does not automatically cover unrelated
     * packages outside the main class's package hierarchy.
     *
     *
     * 7. WHY SHOULD THE MAIN CLASS BE IN THE ROOT PACKAGE?
     *
     * Consider:
     *
     * com.company.employee
     * |
     * +-- EmployeeApplication.java
     * |
     * +-- controller/
     * +-- service/
     * +-- repository/
     * +-- config/
     *
     * This is a recommended placement.
     *
     * The component scan starts from the package containing
     * the main application class.
     *
     * Therefore, the application components inside its
     * subpackages can be discovered automatically.
     *
     * Incorrect example:
     *
     * com.company.employee.application
     *     EmployeeApplication.java
     *
     * com.company.employee.controller
     *     EmployeeController.java
     *
     * Here, the controller package is outside the default
     * component scanning hierarchy.
     *
     * Possible solutions:
     *
     * - Move the main class to a common root package.
     * - Configure scanBasePackages in @SpringBootApplication.
     * - Use an appropriate @ComponentScan configuration.
     *
     *
     * 8. COMPLETE SPRING BOOT MAIN CLASS EXAMPLE
     *
     * package com.company.employee;
     *
     * import org.springframework.boot.SpringApplication;
     * import org.springframework.boot.autoconfigure.SpringBootApplication;
     *
     * @SpringBootApplication
     * public class EmployeeApplication {
     *
     *     public static void main(String[] args) {
     *         SpringApplication.run(EmployeeApplication.class, args);
     *     }
     * }
     *
     * This is the standard structure of a basic Spring Boot
     * application entry point.
     *
     *
     * 9. UNDERSTANDING SPRINGAPPLICATION.RUN()
     *
     * SpringApplication.run() is the method that launches
     * the Spring Boot application.
     *
     * Syntax:
     *
     * SpringApplication.run(ApplicationClass.class, args);
     *
     * Parameters:
     *
     * ApplicationClass.class:
     * - Identifies the primary source of application configuration.
     *
     * args:
     * - Passes command-line arguments to Spring Boot.
     *
     * Return value:
     *
     * - ConfigurableApplicationContext
     *
     * The returned context represents the running Spring application
     * and can be used to interact with its container.
     *
     * Example:
     *
     * ConfigurableApplicationContext context =
     *     SpringApplication.run(EmployeeApplication.class, args);
     *
     * We can retrieve a Spring bean from the context:
     *
     * MyService service = context.getBean(MyService.class);
     *
     * Important:
     *
     * Retrieving beans manually from the context is useful in
     * specific situations, but normal application components
     * should generally receive dependencies through DI.
     *
     *
     * 10. PASSING COMMAND-LINE ARGUMENTS
     *
     * The main() method receives command-line arguments.
     *
     * Example:
     *
     * public static void main(String[] args) {
     *     SpringApplication.run(EmployeeApplication.class, args);
     * }
     *
     * Running:
     *
     * java -jar employee-management.jar --server.port=8085
     *
     * Spring Boot can interpret the command-line option
     * --server.port=8085 as a property.
     *
     * The application can then start using port 8085,
     * subject to its configuration.
     *
     * Command-line arguments can also be passed when running:
     *
     * mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8085"
     *
     *
     * 11. CUSTOMIZING COMPONENT SCANNING
     *
     * By default, component scanning starts from the package
     * containing the main application class.
     *
     * We can customize the scan packages.
     *
     * Example:
     *
     * @SpringBootApplication(
     *     scanBasePackages = {
     *         "com.company.employee",
     *         "com.company.shared"
     *     }
     * )
     * public class EmployeeApplication {
     *
     *     public static void main(String[] args) {
     *         SpringApplication.run(EmployeeApplication.class, args);
     *     }
     * }
     *
     * This allows Spring to scan the specified package roots.
     *
     * Important:
     *
     * Use explicit package scanning only when necessary.
     * A common root package is usually simpler and easier to maintain.
     *
     *
     * 12. EXCLUDING AUTO-CONFIGURATION
     *
     * Sometimes an application does not need a particular
     * auto-configuration.
     *
     * @SpringBootApplication allows exclusions.
     *
     * Example:
     *
     * @SpringBootApplication(
     *     exclude = DataSourceAutoConfiguration.class
     * )
     * public class EmployeeApplication {
     *
     *     public static void main(String[] args) {
     *         SpringApplication.run(EmployeeApplication.class, args);
     *     }
     * }
     *
     * This excludes DataSourceAutoConfiguration.
     *
     * The corresponding class must be available to the project.
     *
     * Exclusions should be used only when the application has
     * a valid reason not to use the relevant auto-configuration.
     *
     *
     * 13. USING SPRINGAPPLICATION DIRECTLY
     *
     * We can create a SpringApplication object instead of using
     * only the static run() method.
     *
     * Example:
     *
     * SpringApplication application =
     *     new SpringApplication(EmployeeApplication.class);
     *
     * application.run(args);
     *
     * This approach is useful when we need to customize
     * SpringApplication before starting the application.
     *
     * Examples of customization:
     *
     * - Setting application type.
     * - Adding listeners.
     * - Adding initializers.
     * - Configuring banner behavior.
     * - Setting additional default properties.
     *
     *
     * 14. APPLICATION TYPE
     *
     * Spring Boot supports different application types.
     *
     * Common types:
     *
     * 1. SERVLET
     *
     *    Used for traditional servlet-based web applications.
     *
     *    Example:
     *    REST APIs using Spring MVC.
     *
     * 2. REACTIVE
     *
     *    Used for reactive web applications.
     *
     *    Example:
     *    Applications using Spring WebFlux.
     *
     * 3. NONE
     *
     *    Used for non-web applications.
     *
     *    Example:
     *    Command-line applications and background processing.
     *
     * Application type can be configured programmatically:
     *
     * SpringApplication application =
     *     new SpringApplication(EmployeeApplication.class);
     *
     * application.setWebApplicationType(WebApplicationType.NONE);
     *
     * application.run(args);
     *
     * This prevents Spring Boot from starting a web application
     * context.
     *
     * Note:
     *
     * The exact context type and available infrastructure depend
     * on the selected application type and classpath.
     *
     *
     * 15. BANNER CONFIGURATION
     *
     * Spring Boot displays a startup banner by default.
     *
     * We can disable it programmatically:
     *
     * SpringApplication application =
     *     new SpringApplication(EmployeeApplication.class);
     *
     * application.setBannerMode(Banner.Mode.OFF);
     *
     * application.run(args);
     *
     * Banner modes include:
     *
     * - CONSOLE
     * - LOG
     * - OFF
     *
     * We can also provide a custom banner using a banner.txt
     * resource in the application's resources directory.
     *
     *
     * 16. SPRINGAPPLICATION VS SPRINGAPPLICATION.RUN()
     *
     * SpringApplication:
     *
     * - A class provided by Spring Boot.
     * - Used to configure and launch applications.
     * - Can be instantiated and customized.
     *
     * SpringApplication.run():
     *
     * - A static convenience method.
     * - Creates and runs the application.
     * - Commonly used directly inside main().
     *
     * Example:
     *
     * SpringApplication.run(EmployeeApplication.class, args);
     *
     * This is the simplest and most common approach.
     *
     *
     * 17. CAN WE HAVE MULTIPLE MAIN CLASSES?
     *
     * Yes.
     *
     * A Java project can contain multiple classes with main()
     * methods.
     *
     * However, a Spring Boot application normally has one
     * designated primary application class.
     *
     * If multiple main classes exist, the build configuration
     * or execution command must identify which one should run.
     *
     * Multiple main classes can be useful for:
     *
     * - Separate tools.
     * - Standalone utilities.
     * - Different application entry points.
     *
     * Avoid creating multiple competing Spring Boot application
     * entry points unless there is a clear architectural reason.
     *
     *
     * 18. COMMON MISTAKES
     *
     * Mistake 1:
     * Thinking main() itself creates the Spring container.
     *
     * Correction:
     * main() invokes SpringApplication.run(), which coordinates
     * application startup.
     *
     * Mistake 2:
     * Thinking @SpringBootApplication is a single-purpose annotation.
     *
     * Correction:
     * It combines three important annotations.
     *
     * Mistake 3:
     * Placing the main class in an unsuitable package.
     *
     * Correction:
     * Prefer a common root package for component scanning.
     *
     * Mistake 4:
     * Thinking component scanning and auto-configuration are identical.
     *
     * Correction:
     * Component scanning discovers application components.
     * Auto-configuration configures infrastructure conditionally.
     *
     * Mistake 5:
     * Assuming every dependency automatically becomes a Spring bean.
     *
     * Correction:
     * A library being on the classpath does not automatically make
     * every class in it a Spring-managed bean.
     *
     * Mistake 6:
     * Retrieving every dependency manually using getBean().
     *
     * Correction:
     * Prefer dependency injection for normal application components.
     *
     *
     * 19. INTERVIEW QUESTIONS
     *
     * BASIC:
     *
     * 1. What is the Spring Boot main class?
     * 2. Why do we need the main() method?
     * 3. What is @SpringBootApplication?
     * 4. Which annotations are combined inside @SpringBootApplication?
     * 5. What does SpringApplication.run() do?
     *
     * INTERMEDIATE:
     *
     * 6. What is the purpose of @SpringBootConfiguration?
     * 7. What is the purpose of @EnableAutoConfiguration?
     * 8. What is the purpose of @ComponentScan?
     * 9. Why should the main class be placed in the root package?
     * 10. What does SpringApplication.run() return?
     * 11. How can you customize component scanning?
     * 12. How can you disable a particular auto-configuration?
     *
     * ADVANCED:
     *
     * 13. Can we create a SpringApplication object directly?
     * 14. What are the different Spring Boot application types?
     * 15. How can you run Spring Boot without a web server?
     * 16. Can a Java project contain multiple main() methods?
     * 17. What is the difference between component scanning
     *     and auto-configuration?
     *
     *
     * 20. PRACTICE EXERCISES
     *
     * Exercise 1:
     * Create a Spring Boot main class with @SpringBootApplication.
     *
     * Exercise 2:
     * Run the application using SpringApplication.run().
     *
     * Exercise 3:
     * Move the main class into a nested package and observe
     * component scanning behavior.
     *
     * Exercise 4:
     * Configure scanBasePackages.
     *
     * Exercise 5:
     * Disable banner output.
     *
     * Exercise 6:
     * Configure the application type as NONE.
     *
     * Exercise 7:
     * Capture the ApplicationContext returned by run().
     *
     * Exercise 8:
     * Pass --server.port=8085 as a command-line argument and
     * verify the application's port.
     *
     *
     * 21. QUICK REVISION
     *
     * - main() is the JVM entry point.
     * - SpringApplication launches the Spring Boot application.
     * - SpringApplication.run() is the common startup method.
     * - @SpringBootApplication is a convenience annotation.
     * - It combines @SpringBootConfiguration,
     *   @EnableAutoConfiguration, and @ComponentScan.
     * - Component scanning discovers Spring-managed components.
     * - Auto-configuration configures infrastructure conditionally.
     * - The main class should normally be in the root package.
     * - SpringApplication can be customized before running.
     * - SpringApplication.run() returns an ApplicationContext.
     *
     * ============================================================
     *                    END OF NOTES
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("Spring Boot Main Class - Learning Notes");
    }

}
