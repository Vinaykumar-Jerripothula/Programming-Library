
public class ProjectStructure {

    /*
     * ============================================================
     *                  SPRING BOOT PROJECT STRUCTURE
     * ============================================================
     *
     * 1. WHAT IS A PROJECT STRUCTURE?
     *
     * Project structure refers to the organization of files,
     * packages, directories, resources, and configuration files
     * inside a software project.
     *
     * A well-organized project structure helps developers:
     *
     * - Understand the application easily.
     * - Maintain code as the application grows.
     * - Separate responsibilities.
     * - Locate classes and resources quickly.
     * - Collaborate with other developers.
     * - Test and debug applications efficiently.
     *
     *
     * 2. DEFAULT SPRING BOOT PROJECT STRUCTURE
     *
     * A typical Spring Boot Maven project looks like this:
     *
     * employee-management/
     * |
     * +-- .mvn/
     * |
     * +-- mvnw
     * +-- mvnw.cmd
     * |
     * +-- pom.xml
     * |
     * +-- src/
     * |   |
     * |   +-- main/
     * |   |   |
     * |   |   +-- java/
     * |   |   |   |
     * |   |   |   +-- com/example/employee/
     * |   |   |       |
     * |   |   |       +-- EmployeeApplication.java
     * |   |   |       |
     * |   |   |       +-- controller/
     * |   |   |       |
     * |   |   |       +-- service/
     * |   |   |       |
     * |   |   |       +-- repository/
     * |   |   |       |
     * |   |   |       +-- entity/
     * |   |   |       |
     * |   |   |       +-- dto/
     * |   |   |       |
     * |   |   |       +-- config/
     * |   |   |       |
     * |   |   |       +-- exception/
     * |   |   |
     * |   |   +-- resources/
     * |   |       |
     * |   |       +-- application.properties
     * |   |       +-- static/
     * |   |       +-- templates/
     * |   |
     * |   +-- test/
     * |       |
     * |       +-- java/
     * |       |   |
     * |       |   +-- com/example/employee/
     * |       |
     * |       +-- resources/
     * |
     * +-- target/
     *
     * This is an illustrative structure. Actual packages depend
     * on the application's architecture and requirements.
     *
     *
     * 3. UNDERSTANDING THE ROOT DIRECTORY
     *
     * The root directory is the main directory of the project.
     *
     * Example:
     *
     * employee-management/
     *
     * It contains important project-level files and directories:
     *
     * - pom.xml
     * - src/
     * - .mvn/
     * - mvnw
     * - mvnw.cmd
     * - target/ (generated during builds)
     *
     *
     * 4. UNDERSTANDING THE SRC DIRECTORY
     *
     * src stands for source.
     *
     * It contains the application's source code, resources,
     * and test code.
     *
     * Standard Maven structure:
     *
     * src/
     * |
     * +-- main/
     * |
     * +-- test/
     *
     * main:
     * Contains application code and application resources.
     *
     * test:
     * Contains test code and test resources.
     *
     * Important:
     *
     * Maven follows a standard directory layout. Spring Boot
     * projects commonly use this layout.
     *
     *
     * 5. UNDERSTANDING SRC/MAIN/JAVA
     *
     * src/main/java is the location for production Java source code.
     *
     * Example:
     *
     * src/main/java/com/example/employee/
     *
     * It contains Java packages and classes such as:
     *
     * - Main application class
     * - Controllers
     * - Services
     * - Repositories
     * - Entities
     * - DTOs
     * - Configuration classes
     * - Exception handlers
     *
     * Maven compiles Java source files from this directory.
     *
     * Important:
     *
     * Java package declarations must correspond to the directory
     * hierarchy.
     *
     * Example directory:
     *
     * com/example/employee/service/
     *
     * Corresponding package declaration:
     *
     * package com.example.employee.service;
     *
     *
     * 6. UNDERSTANDING SRC/MAIN/RESOURCES
     *
     * src/main/resources contains non-Java application resources.
     *
     * Examples:
     *
     * - application.properties
     * - application.yml
     * - Static files
     * - Templates
     * - SQL scripts
     * - Logging configuration
     * - Other configuration files
     *
     * These resources are included in the application's runtime
     * classpath.
     *
     * Common directories:
     *
     * static/
     * - Static web resources such as CSS, JavaScript, and images.
     *
     * templates/
     * - Server-side rendering templates.
     *
     * Important:
     *
     * Static resources and templates are commonly used by
     * Spring Boot web applications.
     *
     * They are not mandatory for every REST API project.
     *
     *
     * 7. APPLICATION.PROPERTIES
     *
     * application.properties is a configuration file.
     *
     * Default location:
     *
     * src/main/resources/application.properties
     *
     * It is used to configure application behavior.
     *
     * Example:
     *
     * server.port=8081
     * spring.application.name=employee-management
     *
     * spring.datasource.url=jdbc:postgresql://localhost:5432/employee
     * spring.datasource.username=postgres
     * spring.datasource.password=secret
     *
     * spring.jpa.hibernate.ddl-auto=update
     *
     * Important:
     *
     * These are illustrative settings. Database properties require
     * the appropriate database driver and data-access dependencies.
     *
     * We will study configuration in detail in the dedicated
     * configuration topics.
     *
     *
     * 8. UNDERSTANDING SRC/TEST/JAVA
     *
     * src/test/java contains test source code.
     *
     * It is used for:
     *
     * - Unit testing
     * - Integration testing
     * - Controller testing
     * - Repository testing
     * - Service testing
     *
     * Example:
     *
     * src/test/java/com/example/employee/
     *
     *     EmployeeServiceTest.java
     *     EmployeeControllerTest.java
     *
     * Maven compiles test source code separately from production
     * source code.
     *
     * Test code is not normally included in the production
     * application JAR.
     *
     *
     * 9. UNDERSTANDING SRC/TEST/RESOURCES
     *
     * src/test/resources contains resources used during testing.
     *
     * Examples:
     *
     * - Test configuration files
     * - Test application.properties
     * - Test SQL scripts
     * - JSON test data
     * - Sample files
     *
     * These resources are available on the test classpath.
     *
     * Example:
     *
     * src/test/resources/application-test.properties
     *
     *
     * 10. UNDERSTANDING POM.XML
     *
     * pom.xml stands for Project Object Model.
     *
     * It is the main configuration file of a Maven project.
     *
     * It defines:
     *
     * - Project information
     * - Dependencies
     * - Plugins
     * - Build configuration
     * - Dependency management
     * - Parent POM
     * - Maven properties
     *
     * Example:
     *
     * <project>
     *
     *     <modelVersion>4.0.0</modelVersion>
     *
     *     <parent>
     *         <groupId>org.springframework.boot</groupId>
     *         <artifactId>spring-boot-starter-parent</artifactId>
     *         <version>YOUR_SPRING_BOOT_VERSION</version>
     *         <relativePath/>
     *     </parent>
     *
     *     <groupId>com.example</groupId>
     *     <artifactId>employee-management</artifactId>
     *     <version>0.0.1-SNAPSHOT</version>
     *
     *     <dependencies>
     *         <!-- Application dependencies -->
     *     </dependencies>
     *
     * </project>
     *
     * The above is a simplified illustration, not a complete
     * buildable POM.
     *
     * We will study POM configuration and dependency management
     * in the dedicated Maven and Spring Boot topics.
     *
     *
     * 11. UNDERSTANDING THE .MVN DIRECTORY
     *
     * .mvn is used for Maven Wrapper configuration.
     *
     * It commonly contains:
     *
     * .mvn/
     * |
     * +-- wrapper/
     *     |
     *     +-- maven-wrapper.properties
     *     +-- maven-wrapper.jar (depending on wrapper setup)
     *
     * It stores configuration used by the Maven Wrapper.
     *
     * The exact contents depend on the Maven Wrapper version
     * and configuration.
     *
     *
     * 12. WHAT IS MAVEN WRAPPER?
     *
     * Maven Wrapper allows developers to run Maven builds without
     * requiring a separately installed Maven version.
     *
     * Common files:
     *
     * mvnw
     * - Wrapper script for Linux and macOS.
     *
     * mvnw.cmd
     * - Wrapper script for Windows.
     *
     * Usage on Linux:
     *
     * ./mvnw clean package
     *
     * Usage on Windows:
     *
     * mvnw.cmd clean package
     *
     * The wrapper downloads and uses the configured Maven
     * distribution when necessary.
     *
     * Benefits:
     *
     * - Consistent Maven version across developers.
     * - Easier project setup.
     * - More predictable builds.
     * - Useful in CI/CD pipelines.
     *
     *
     * 13. UNDERSTANDING THE TARGET DIRECTORY
     *
     * target is a generated build directory.
     *
     * Maven creates it when the project is built.
     *
     * Example:
     *
     * target/
     * |
     * +-- classes/
     * |
     * +-- test-classes/
     * |
     * +-- generated-sources/
     * |
     * +-- generated-test-sources/
     * |
     * +-- employee-management-0.0.1-SNAPSHOT.jar
     *
     * Common contents:
     *
     * classes/
     * - Compiled production classes and copied resources.
     *
     * test-classes/
     * - Compiled test classes and test resources.
     *
     * Generated directories:
     * - Sources generated by build plugins or annotation processors.
     *
     * JAR:
     * - Packaged application artifact, when the build produces one.
     *
     * Important:
     *
     * target is generated output, not the place to write source code.
     *
     * It can normally be deleted and regenerated by running a build.
     *
     *
     * 14. UNDERSTANDING JAVA PACKAGES IN SPRING BOOT
     *
     * Packages organize related Java classes.
     *
     * Example:
     *
     * com.example.employee
     * |
     * +-- EmployeeApplication.java
     * |
     * +-- controller/
     * |
     * +-- service/
     * |
     * +-- repository/
     * |
     * +-- entity/
     * |
     * +-- dto/
     * |
     * +-- config/
     * |
     * +-- exception/
     *
     * Each package groups classes with related responsibilities.
     *
     * Example:
     *
     * package com.example.employee.controller;
     *
     * public class EmployeeController {
     * }
     *
     * The package declaration identifies the class's package.
     *
     *
     * 15. COMMON APPLICATION LAYERS
     *
     * A typical layered Spring Boot application may contain:
     *
     * Controller Layer:
     * - Receives HTTP requests.
     * - Validates and processes request input at the API boundary.
     * - Returns HTTP responses.
     *
     * Service Layer:
     * - Contains business logic.
     * - Coordinates application operations.
     *
     * Repository Layer:
     * - Handles data-access operations.
     * - Communicates with persistence infrastructure.
     *
     * Entity Layer:
     * - Represents persistent domain data when using an ORM.
     *
     * DTO Layer:
     * - Represents data transferred between application boundaries.
     *
     * Configuration Layer:
     * - Contains application configuration classes.
     *
     * Exception Layer:
     * - Contains custom exceptions and error-handling components.
     *
     * Typical request flow:
     *
     * Client
     *   |
     *   v
     * Controller
     *   |
     *   v
     * Service
     *   |
     *   v
     * Repository
     *   |
     *   v
     * Database
     *
     * This is a common organization, not a mandatory Spring Boot rule.
     *
     *
     * 16. WHERE SHOULD THE MAIN APPLICATION CLASS BE PLACED?
     *
     * The main application class is commonly placed in the root
     * package of the application.
     *
     * Example:
     *
     * com.example.employee
     * |
     * +-- EmployeeApplication.java
     * |
     * +-- controller/
     * +-- service/
     * +-- repository/
     *
     * Example:
     *
     * package com.example.employee;
     *
     * @SpringBootApplication
     * public class EmployeeApplication {
     *
     *     public static void main(String[] args) {
     *         SpringApplication.run(
     *             EmployeeApplication.class, args
     *         );
     *     }
     * }
     *
     * Why is this placement important?
     *
     * @SpringBootApplication includes component scanning behavior.
     *
     * By default, component scanning starts from the package
     * containing the main application class and scans its
     * subpackages.
     *
     * Example:
     *
     * com.example.employee
     * |
     * +-- EmployeeApplication.java
     * |
     * +-- controller/
     * |
     * +-- service/
     *
     * Components inside these subpackages can be discovered
     * through the default component scan.
     *
     * Incorrect placement example:
     *
     * com.example.employee.application
     *     EmployeeApplication.java
     *
     * com.example.employee.controller
     *     EmployeeController.java
     *
     * Here, the controller package is outside the main class's
     * package hierarchy. It will not be discovered by the default
     * component scan.
     *
     * Solutions include:
     *
     * - Move the main class to a common root package.
     * - Configure component scanning explicitly.
     *
     *
     * 17. COMMON PACKAGE ORGANIZATION STYLES
     *
     * There are two commonly used organizational approaches.
     *
     * A. LAYERED PACKAGE STRUCTURE
     *
     * com.example.employee
     * |
     * +-- controller/
     * +-- service/
     * +-- repository/
     * +-- entity/
     * +-- dto/
     * +-- config/
     *
     * Classes are grouped according to technical responsibility.
     *
     * Advantages:
     *
     * - Simple to understand.
     * - Familiar to many developers.
     * - Suitable for small and medium applications.
     *
     * Disadvantages:
     *
     * - Large packages can contain many unrelated classes.
     * - A feature's classes may be spread across multiple packages.
     *
     *
     * B. FEATURE-BASED PACKAGE STRUCTURE
     *
     * com.example.employee
     * |
     * +-- employee/
     * |   |
     * |   +-- EmployeeController.java
     * |   +-- EmployeeService.java
     * |   +-- EmployeeRepository.java
     * |   +-- Employee.java
     * |
     * +-- department/
     * |   |
     * |   +-- DepartmentController.java
     * |   +-- DepartmentService.java
     * |   +-- DepartmentRepository.java
     * |   +-- Department.java
     * |
     * +-- config/
     * |
     * +-- exception/
     *
     * Classes are grouped according to business features.
     *
     * Advantages:
     *
     * - Related functionality stays together.
     * - Easier to understand large business modules.
     * - Features can be maintained more independently.
     *
     * Disadvantages:
     *
     * - Requires consistent package organization.
     * - Shared components need careful placement.
     *
     * Neither approach is universally best.
     * Choose based on application size, team conventions,
     * and architectural requirements.
     *
     *
     * 18. WHAT IS A RESOURCE DIRECTORY?
     *
     * A resource directory stores non-Java files required by
     * the application.
     *
     * Common examples:
     *
     * application.properties
     * application.yml
     * static/
     * templates/
     * schema.sql
     * data.sql
     * logback-spring.xml
     *
     * Maven copies main resources into the output directory
     * during the build.
     *
     * These resources can then be loaded from the classpath.
     *
     *
     * 19. APPLICATION CONFIGURATION FILE LOCATIONS
     *
     * Common configuration files include:
     *
     * application.properties
     * application.yml
     *
     * They are normally placed inside:
     *
     * src/main/resources/
     *
     * Spring Boot also supports configuration from external
     * locations and other property sources.
     *
     * External configuration is useful because:
     *
     * - Development and production settings differ.
     * - Database connection details may vary.
     * - Server ports may vary.
     * - Secrets should not be hardcoded in source code.
     *
     * We will study configuration locations, precedence,
     * profiles, and externalized properties separately.
     *
     *
     * 20. IMPORTANT STRUCTURE RULES
     *
     * Rule 1:
     * Keep production Java code inside src/main/java.
     *
     * Rule 2:
     * Keep production resources inside src/main/resources.
     *
     * Rule 3:
     * Keep test Java code inside src/test/java.
     *
     * Rule 4:
     * Keep test resources inside src/test/resources.
     *
     * Rule 5:
     * Keep Maven configuration in pom.xml.
     *
     * Rule 6:
     * Do not manually place application source files inside target.
     *
     * Rule 7:
     * Keep the main application class in a suitable root package.
     *
     * Rule 8:
     * Organize packages according to clear responsibilities.
     *
     * Rule 9:
     * Do not put secrets directly into source-controlled files.
     *
     * Rule 10:
     * Follow a consistent naming and package convention.
     *
     *
     * 21. COMMON PROJECT STRUCTURE MISTAKES
     *
     * Mistake 1:
     * Placing the main application class in a package that does not
     * include the controller, service, and repository packages.
     *
     * Result:
     * Components may not be discovered by default component scanning.
     *
     * Mistake 2:
     * Writing Java classes inside src/main/resources.
     *
     * Correction:
     * Java production source code belongs in src/main/java.
     *
     * Mistake 3:
     * Writing production code inside src/test/java.
     *
     * Correction:
     * Keep production and test source code separate.
     *
     * Mistake 4:
     * Manually editing files inside target.
     *
     * Correction:
     * target contains generated build output.
     *
     * Mistake 5:
     * Keeping every class in one package.
     *
     * Correction:
     * Organize classes into meaningful packages as the project grows.
     *
     * Mistake 6:
     * Committing passwords and API secrets into source control.
     *
     * Correction:
     * Use secure external configuration and secret management.
     *
     *
     * 22. REAL-WORLD BACKEND PROJECT STRUCTURE
     *
     * Example: Employee Management System
     *
     * employee-management/
     * |
     * +-- pom.xml
     * |
     * +-- src/
     * |   |
     * |   +-- main/
     * |   |   |
     * |   |   +-- java/com/company/employee/
     * |   |       |
     * |   |       +-- EmployeeApplication.java
     * |   |       |
     * |   |       +-- employee/
     * |   |       |   +-- EmployeeController.java
     * |   |       |   +-- EmployeeService.java
     * |   |       |   +-- EmployeeRepository.java
     * |   |       |   +-- Employee.java
     * |   |       |   +-- EmployeeRequest.java
     * |   |       |   +-- EmployeeResponse.java
     * |   |       |
     * |   |       +-- department/
     * |   |       |
     * |   |       +-- config/
     * |   |       |
     * |   |       +-- exception/
     * |   |       |
     * |   |       +-- security/
     * |   |
     * |   +-- resources/
     * |       +-- application.properties
     * |
     * +-- src/test/
     *     +-- java/
     *     +-- resources/
     *
     * This example uses feature-based organization.
     *
     * The actual structure can differ depending on whether the
     * application uses layered architecture, modular architecture,
     * or another design.
     *
     *
     * 23. INTERVIEW QUESTIONS
     *
     * BASIC:
     *
     * 1. What is the standard Spring Boot project structure?
     * 2. What is the purpose of src/main/java?
     * 3. What is the purpose of src/main/resources?
     * 4. What is the purpose of src/test/java?
     * 5. What is the purpose of pom.xml?
     * 6. What is the target directory?
     *
     * INTERMEDIATE:
     *
     * 7. Why should the main application class be placed in
     *    the root package?
     * 8. What is the difference between main and test resources?
     * 9. What is the Maven Wrapper?
     * 10. What is the difference between layered and feature-based
     *     package structures?
     * 11. How does Spring Boot discover application components?
     *
     * ADVANCED:
     *
     * 12. What happens if a controller is outside the default
     *     component scanning hierarchy?
     * 13. How would you organize a large Spring Boot application?
     * 14. Why should generated build output not be committed
     *     unnecessarily?
     * 15. How would you organize packages for a modular monolith?
     *
     *
     * 24. PRACTICE EXERCISES
     *
     * Exercise 1:
     * Create a Spring Boot Maven project and inspect its directory
     * structure.
     *
     * Exercise 2:
     * Locate pom.xml and identify its main sections.
     *
     * Exercise 3:
     * Locate the main application class.
     *
     * Exercise 4:
     * Create controller, service, and repository packages.
     *
     * Exercise 5:
     * Create an application.properties file.
     *
     * Exercise 6:
     * Create a test class under src/test/java.
     *
     * Exercise 7:
     * Run a Maven build and inspect the target directory.
     *
     * Exercise 8:
     * Move the main application class into a nested package and
     * investigate component scanning behavior.
     *
     * Exercise 9:
     * Organize a sample application using feature-based packages.
     *
     *
     * 25. QUICK REVISION
     *
     * - src/main/java = production Java source code.
     * - src/main/resources = production resources.
     * - src/test/java = test source code.
     * - src/test/resources = test resources.
     * - pom.xml = Maven project configuration.
     * - .mvn = Maven Wrapper configuration.
     * - mvnw = Linux/macOS Maven Wrapper script.
     * - mvnw.cmd = Windows Maven Wrapper script.
     * - target = generated build output.
     * - application.properties = application configuration.
     * - Main application class is commonly placed in the root package.
     * - Default component scanning covers its package and subpackages.
     * - Packages can be organized by layer or by business feature.
     *
     * ============================================================
     *                    END OF NOTES
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("Spring Boot Project Structure - Learning Notes");
    }

}
