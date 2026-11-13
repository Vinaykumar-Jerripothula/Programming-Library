
public class MavenWithSpringBootAndBestPractices {

    /*
     * =================================================================
     *          MAVEN WITH SPRING BOOT AND BEST PRACTICES
     * =================================================================
     *
     * 1. WHY MAVEN IS IMPORTANT FOR SPRING BOOT
     *
     * Spring Boot applications usually depend on multiple
     * libraries and frameworks.
     *
     * For example:
     *
     * - Spring Boot.
     * - Spring MVC.
     * - Spring Data JPA.
     * - Hibernate.
     * - Spring Security.
     * - JDBC drivers.
     * - Testing libraries.
     *
     * Maven helps manage these dependencies and automates
     * the application build process.
     *
     * In a Spring Boot project, Maven is commonly used to:
     *
     * - Manage dependencies.
     * - Compile Java code.
     * - Run tests.
     * - Package applications.
     * - Build executable JAR files.
     * - Run Spring Boot applications.
     * - Publish artifacts.
     *
     *
     * 2. CREATING A SPRING BOOT PROJECT
     *
     * A Spring Boot project can be generated using
     * Spring Initializr.
     *
     * Website:
     *
     * https://start.spring.io/
     *
     * Common project options:
     *
     * Project: Maven
     * Language: Java
     * Packaging: Jar
     * Java: 21
     *
     * You can select the dependencies required by
     * your application.
     *
     * Spring Initializr generates the project structure
     * and its initial pom.xml.
     *
     *
     * 3. TYPICAL SPRING BOOT PROJECT STRUCTURE
     *
     * employee-management/
     * |
     * |---- pom.xml
     * |---- mvnw
     * |---- mvnw.cmd
     * |
     * |---- .mvn/
     * |       |
     * |       |---- wrapper/
     * |
     * |---- src/
     * |     |
     * |     |---- main/
     * |     |      |
     * |     |      |---- java/
     * |     |      |      |
     * |     |      |      |---- com/company/employee/
     * |     |      |             |
     * |     |      |             |---- EmployeeApplication.java
     * |     |      |
     * |     |      |---- resources/
     * |     |             |
     * |     |             |---- application.properties
     * |     |
     * |     |---- test/
     * |            |
     * |            |---- java/
     * |                   |
     * |                   |---- EmployeeApplicationTests.java
     * |
     * |---- target/
     *
     * Important:
     *
     * target is generated during the Maven build.
     * It does not need to be manually created.
     *
     *
     * 4. UNDERSTANDING A SPRING BOOT pom.xml
     *
     * A typical Spring Boot Maven project contains:
     *
     * 1. Project coordinates.
     * 2. Parent POM.
     * 3. Properties.
     * 4. Dependencies.
     * 5. Build plugins.
     *
     * Example:
     *
     * <project xmlns="http://maven.apache.org/POM/4.0.0"
     *          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
     *          xsi:schemaLocation="
     *          http://maven.apache.org/POM/4.0.0
     *          https://maven.apache.org/xsd/maven-4.0.0.xsd">
     *
     *     <modelVersion>4.0.0</modelVersion>
     *
     *     <parent>
     *         <groupId>org.springframework.boot</groupId>
     *         <artifactId>spring-boot-starter-parent</artifactId>
     *         <version>3.5.0</version>
     *         <relativePath/>
     *     </parent>
     *
     *     <groupId>com.company</groupId>
     *     <artifactId>employee-management</artifactId>
     *     <version>1.0.0</version>
     *
     *     <properties>
     *         <java.version>21</java.version>
     *     </properties>
     *
     *     <dependencies>
     *
     *         <dependency>
     *             <groupId>org.springframework.boot</groupId>
     *             <artifactId>spring-boot-starter-web</artifactId>
     *         </dependency>
     *
     *         <dependency>
     *             <groupId>org.springframework.boot</groupId>
     *             <artifactId>spring-boot-starter-data-jpa</artifactId>
     *         </dependency>
     *
     *         <dependency>
     *             <groupId>com.mysql</groupId>
     *             <artifactId>mysql-connector-j</artifactId>
     *             <scope>runtime</scope>
     *         </dependency>
     *
     *         <dependency>
     *             <groupId>org.springframework.boot</groupId>
     *             <artifactId>spring-boot-starter-test</artifactId>
     *             <scope>test</scope>
     *         </dependency>
     *
     *     </dependencies>
     *
     *     <build>
     *         <plugins>
     *
     *             <plugin>
     *                 <groupId>org.springframework.boot</groupId>
     *                 <artifactId>spring-boot-maven-plugin</artifactId>
     *             </plugin>
     *
     *         </plugins>
     *     </build>
     *
     * </project>
     *
     * The Spring Boot version above is illustrative.
     *
     *
     * 5. UNDERSTANDING THE SPRING BOOT PARENT
     *
     * spring-boot-starter-parent is a parent POM
     * provided by Spring Boot.
     *
     * It provides useful Maven configuration, including:
     *
     * - Dependency version management.
     * - Plugin management.
     * - Compiler-related defaults.
     * - Resource filtering configuration.
     * - Other build conventions.
     *
     * This reduces the amount of configuration developers
     * need to write manually.
     *
     * Important:
     *
     * The parent POM does not mean that your application
     * inherits Java classes from Spring Boot.
     *
     * Maven POM inheritance is build configuration inheritance.
     *
     *
     * 6. WHY SPRING BOOT DEPENDENCY VERSIONS ARE OFTEN OMITTED
     *
     * Consider:
     *
     * <dependency>
     *     <groupId>org.springframework.boot</groupId>
     *     <artifactId>spring-boot-starter-web</artifactId>
     * </dependency>
     *
     * Notice that the version is not specified.
     *
     * Why?
     *
     * The Spring Boot parent manages compatible versions
     * of many dependencies.
     *
     * Therefore, Maven can obtain the managed version.
     *
     * This helps avoid incompatible combinations of
     * Spring-related libraries.
     *
     *
     * 7. SPRING BOOT BOM WITHOUT THE PARENT POM
     *
     * Sometimes an organization already has its own
     * parent POM.
     *
     * In that situation, you may not be able to use
     * spring-boot-starter-parent as the direct parent.
     *
     * You can import Spring Boot's dependency BOM instead.
     *
     * Example:
     *
     * <dependencyManagement>
     *     <dependencies>
     *
     *         <dependency>
     *             <groupId>org.springframework.boot</groupId>
     *             <artifactId>spring-boot-dependencies</artifactId>
     *             <version>3.5.0</version>
     *             <type>pom</type>
     *             <scope>import</scope>
     *         </dependency>
     *
     *     </dependencies>
     * </dependencyManagement>
     *
     * The version is illustrative.
     *
     * Important:
     *
     * Importing the BOM manages dependency versions.
     *
     * It does not automatically configure every plugin
     * the same way as inheriting from the Spring Boot parent.
     *
     *
     * 8. COMMON SPRING BOOT STARTERS
     *
     * Spring Boot starters are convenient dependency
     * descriptors that bring together commonly required
     * libraries for a particular feature.
     *
     * Common examples:
     *
     * spring-boot-starter-web:
     * Web application and REST API development.
     *
     * spring-boot-starter-data-jpa:
     * Spring Data JPA integration.
     *
     * spring-boot-starter-security:
     * Spring Security integration.
     *
     * spring-boot-starter-validation:
     * Bean validation support.
     *
     * spring-boot-starter-test:
     * Testing support.
     *
     * spring-boot-starter-actuator:
     * Application monitoring and management endpoints.
     *
     * Exact starter names can vary across Spring Boot
     * major versions.
     *
     *
     * 9. HOW STARTERS HELP
     *
     * Suppose you want to build REST APIs.
     *
     * Without a starter, you may need to identify and
     * declare multiple compatible dependencies manually.
     *
     * With a starter:
     *
     * <dependency>
     *     <groupId>org.springframework.boot</groupId>
     *     <artifactId>spring-boot-starter-web</artifactId>
     * </dependency>
     *
     * Maven resolves the starter and its transitive
     * dependencies.
     *
     * This makes project setup easier.
     *
     *
     * 10. SPRING BOOT MAVEN PLUGIN
     *
     * The Spring Boot Maven Plugin provides Spring Boot
     * specific build functionality.
     *
     * Its common uses include:
     *
     * - Running the application through Maven.
     * - Repackaging an archive into an executable archive.
     * - Supporting Spring Boot application packaging.
     *
     * Configuration:
     *
     * <build>
     *     <plugins>
     *
     *         <plugin>
     *             <groupId>org.springframework.boot</groupId>
     *             <artifactId>spring-boot-maven-plugin</artifactId>
     *         </plugin>
     *
     *     </plugins>
     * </build>
     *
     *
     * 11. RUNNING A SPRING BOOT APPLICATION
     *
     * You can run a Spring Boot application using:
     *
     * mvn spring-boot:run
     *
     * Or with Maven Wrapper:
     *
     * ./mvnw spring-boot:run
     *
     * This invokes the Spring Boot Maven Plugin.
     *
     * The application runs from the project build setup
     * without requiring you to manually create the
     * executable JAR first.
     *
     *
     * 12. BUILDING A SPRING BOOT APPLICATION
     *
     * Compile:
     *
     * mvn compile
     *
     * Run tests:
     *
     * mvn test
     *
     * Package:
     *
     * mvn package
     *
     * Clean and package:
     *
     * mvn clean package
     *
     * Verify:
     *
     * mvn verify
     *
     * Install:
     *
     * mvn install
     *
     * Recommended wrapper commands:
     *
     * ./mvnw clean verify
     *
     * ./mvnw clean package
     *
     *
     * 13. WHAT HAPPENS WHEN YOU RUN mvn package?
     *
     * Maven executes the default lifecycle up to package.
     *
     * Simplified flow:
     *
     * Validate project
     *        |
     *        v
     * Process resources
     *        |
     *        v
     * Compile Java code
     *        |
     *        v
     * Compile tests
     *        |
     *        v
     * Run tests
     *        |
     *        v
     * Package application
     *
     * The build plugins perform the corresponding operations.
     *
     * When the Spring Boot Maven Plugin is configured
     * and its repackage goal is bound to the package phase,
     * the resulting archive can be repackaged as an
     * executable Spring Boot archive.
     *
     *
     * 14. EXECUTABLE JAR
     *
     * A normal JAR contains compiled classes and resources.
     *
     * A Spring Boot executable JAR also includes the
     * application dependencies in its packaged structure
     * and provides the necessary launcher.
     *
     * This allows the application to be started using:
     *
     * java -jar target/employee-management-1.0.0.jar
     *
     * The exact file name depends on the artifact coordinates
     * and build configuration.
     *
     *
     * 15. SPRING BOOT REPACKAGE GOAL
     *
     * The repackage goal transforms an ordinary archive
     * into an executable Spring Boot archive.
     *
     * Command:
     *
     * mvn spring-boot:repackage
     *
     * It is commonly bound to the package phase when
     * the Spring Boot Maven Plugin is configured
     * appropriately.
     *
     * Important:
     *
     * The repackage goal does not replace Maven's
     * entire build lifecycle.
     *
     *
     * 16. SKIPPING TESTS
     *
     * During development, you may sometimes need to
     * skip tests temporarily.
     *
     * Skip test execution:
     *
     * mvn package -DskipTests
     *
     * Skip test compilation and execution:
     *
     * mvn package -Dmaven.test.skip=true
     *
     * Important:
     *
     * Skipping tests should not become your normal
     * production build practice.
     *
     * Tests help detect problems before deployment.
     *
     *
     * 17. MAVEN WRAPPER IN SPRING BOOT PROJECTS
     *
     * Spring Initializr commonly includes Maven Wrapper
     * files in generated Maven projects.
     *
     * Linux:
     *
     * ./mvnw clean package
     *
     * Windows:
     *
     * mvnw.cmd clean package
     *
     * Why use the wrapper?
     *
     * - Developers use a consistent Maven version.
     * - CI/CD uses the project-configured Maven version.
     * - Developers do not need to install Maven separately.
     *
     * A suitable JDK is still required.
     *
     *
     * 18. MAVEN AND JAVA VERSION CONFIGURATION
     *
     * Spring Boot projects must use a Java version
     * supported by the selected Spring Boot version.
     *
     * Example:
     *
     * <properties>
     *     <java.version>21</java.version>
     * </properties>
     *
     * This property is commonly used by Spring Boot's
     * parent configuration to configure Java compilation.
     *
     * Check your Java version:
     *
     * java -version
     *
     * Check Maven's Java runtime:
     *
     * mvn -version
     *
     * Important:
     *
     * The JDK running Maven and the Java release targeted
     * by compilation must be compatible.
     *
     *
     * 19. MAVEN DEPENDENCY TREE IN SPRING BOOT
     *
     * Spring Boot starters bring multiple transitive
     * dependencies.
     *
     * To inspect them:
     *
     * mvn dependency:tree
     *
     * This helps you understand:
     *
     * - Which dependencies are included.
     * - Which dependencies are transitive.
     * - Which versions Maven selected.
     * - Whether multiple dependency paths request
     *   different versions.
     *
     * This command is especially useful when debugging
     * dependency conflicts.
     *
     *
     * 20. MAVEN PROFILES IN REAL PROJECTS
     *
     * Maven profiles can be used to change build-time
     * configuration.
     *
     * Example:
     *
     * <profiles>
     *
     *     <profile>
     *         <id>development</id>
     *         <properties>
     *             <build.environment>development</build.environment>
     *         </properties>
     *     </profile>
     *
     *     <profile>
     *         <id>production</id>
     *         <properties>
     *             <build.environment>production</build.environment>
     *         </properties>
     *     </profile>
     *
     * </profiles>
     *
     * Activate a profile:
     *
     * mvn package -Pdevelopment
     *
     * Important:
     *
     * Maven profiles control the build.
     *
     * Spring profiles control application configuration.
     *
     * Do not assume activating a Maven profile
     * automatically activates a Spring profile.
     *
     *
     * 21. MAVEN IN CI/CD
     *
     * Maven is commonly executed by CI/CD systems.
     *
     * Example:
     *
     * Developer pushes code
     *          |
     *          v
     * Git repository
     *          |
     *          v
     * CI pipeline
     *          |
     *          v
     * ./mvnw clean verify
     *          |
     *          v
     * Tests and verification
     *          |
     *          v
     * Package application
     *          |
     *          v
     * Publish artifact
     *          |
     *          v
     * Deployment
     *
     * Maven handles project build operations.
     *
     * The CI/CD platform coordinates the complete pipeline.
     *
     *
     * 22. MAVEN AND DOCKER
     *
     * Maven can build the application artifact that
     * is later included in a Docker image.
     *
     * Example workflow:
     *
     * 1. Build the Spring Boot JAR using Maven.
     * 2. Create a Docker image containing the JAR.
     * 3. Run the container.
     *
     * Example Dockerfile:
     *
     * FROM eclipse-temurin:21-jre
     *
     * WORKDIR /app
     *
     * COPY target/employee-management.jar app.jar
     *
     * ENTRYPOINT ["java", "-jar", "app.jar"]
     *
     * This is a simplified example.
     *
     * Maven builds the JAR.
     *
     * Docker packages the application and its runtime
     * environment into a container image.
     *
     *
     * 23. MAVEN BEST PRACTICES
     *
     * DEPENDENCIES:
     *
     * - Use trusted dependencies.
     * - Avoid unnecessary libraries.
     * - Prefer Spring Boot managed versions.
     * - Avoid random dependency version overrides.
     * - Inspect transitive dependencies when needed.
     * - Keep dependencies updated.
     *
     * PLUGINS:
     *
     * - Use stable plugin versions.
     * - Keep important plugin versions controlled.
     * - Avoid unnecessary plugin configuration.
     *
     * PROJECT CONFIGURATION:
     *
     * - Keep pom.xml organized.
     * - Use meaningful Maven coordinates.
     * - Use properties for reusable values.
     * - Avoid putting secrets in POM files.
     *
     * BUILD:
     *
     * - Use Maven Wrapper.
     * - Run tests before packaging.
     * - Use clean verify in CI pipelines.
     * - Keep Java and Maven versions consistent.
     *
     * SECURITY:
     *
     * - Protect repository credentials.
     * - Use trusted artifact repositories.
     * - Monitor dependency vulnerabilities.
     * - Avoid committing passwords and API keys.
     *
     *
     * 24. COMMON MAVEN ERRORS IN SPRING BOOT PROJECTS
     *
     * 1. Dependency resolution failure.
     *
     *    Possible causes:
     *    - Network problems.
     *    - Incorrect dependency coordinates.
     *    - Repository authentication failure.
     *    - Repository unavailable.
     *
     * 2. Unsupported Java release.
     *
     *    Possible causes:
     *    - Maven is running with an older JDK.
     *    - Configured compiler release is unsupported.
     *
     * 3. Plugin resolution failure.
     *
     *    Possible causes:
     *    - Plugin version cannot be resolved.
     *    - Repository access failure.
     *    - Incorrect plugin configuration.
     *
     * 4. Tests failing.
     *
     *    Possible causes:
     *    - Application logic errors.
     *    - Incorrect test configuration.
     *    - Missing test dependencies.
     *
     * 5. Main class not found when running the JAR.
     *
     *    Possible causes:
     *    - Incorrect packaging.
     *    - Incorrect main class configuration.
     *    - The archive is not a properly repackaged
     *      executable Spring Boot archive.
     *
     *
     * 25. TROUBLESHOOTING COMMANDS
     *
     * Check Maven and Java versions:
     *
     * mvn -version
     *
     * Check dependency hierarchy:
     *
     * mvn dependency:tree
     *
     * Display effective POM:
     *
     * mvn help:effective-pom
     *
     * Force update checks:
     *
     * mvn -U clean install
     *
     * Enable debug logs:
     *
     * mvn -X clean package
     *
     * Run offline:
     *
     * mvn -o clean package
     *
     * Clean previous build output:
     *
     * mvn clean
     *
     * Remember:
     * Debug logs can contain environment or configuration
     * details. Avoid sharing secrets in logs.
     *
     *
     * 26. MAVEN KNOWLEDGE REQUIRED BEFORE SPRING BOOT
     *
     * You do not need to become a Maven expert before
     * starting Spring Boot.
     *
     * You should be comfortable with:
     *
     * 1. What Maven is.
     * 2. What pom.xml does.
     * 3. Maven project structure.
     * 4. Dependencies and dependency scopes.
     * 5. Transitive dependencies.
     * 6. dependencyManagement and BOM.
     * 7. Maven build lifecycle.
     * 8. Lifecycle phases and plugin goals.
     * 9. Common Maven commands.
     * 10. Maven Wrapper.
     * 11. Spring Boot parent POM.
     * 12. Spring Boot starters.
     * 13. Spring Boot Maven Plugin.
     * 14. Basic dependency troubleshooting.
     *
     * You can learn advanced Maven configuration
     * when your project requires it.
     *
     *
     * 27. INTERVIEW QUESTIONS
     *
     * 1. Why is Maven used in Spring Boot?
     * 2. What is the purpose of spring-boot-starter-parent?
     * 3. Why can Spring Boot dependency versions be omitted?
     * 4. What is the Spring Boot BOM?
     * 5. What is a Spring Boot starter?
     * 6. What is the Spring Boot Maven Plugin?
     * 7. How do you run a Spring Boot application using Maven?
     * 8. How do you package a Spring Boot application?
     * 9. What is an executable JAR?
     * 10. What is the purpose of the repackage goal?
     * 11. What is the purpose of Maven Wrapper?
     * 12. How do you inspect Spring Boot dependencies?
     * 13. How do you configure the Java version?
     * 14. What is the difference between Maven and Docker?
     * 15. How is Maven used in CI/CD?
     * 16. What is the difference between Maven and Spring profiles?
     * 17. Why should dependency versions be managed consistently?
     * 18. What is the purpose of mvn clean verify?
     * 19. How do you troubleshoot dependency resolution failures?
     * 20. What Maven knowledge is important before learning Spring Boot?
     *
     *
     * 28. FINAL MAVEN REVISION
     *
     * Maven:
     * Build automation and project management tool.
     *
     * pom.xml:
     * Main Maven project configuration file.
     *
     * Dependency:
     * External library required by the project.
     *
     * Dependency scope:
     * Determines where a dependency is available.
     *
     * Transitive dependency:
     * Dependency required by another dependency.
     *
     * BOM:
     * Manages compatible dependency versions.
     *
     * Lifecycle:
     * Predefined build process.
     *
     * Phase:
     * Stage in a lifecycle.
     *
     * Goal:
     * Specific task provided by a plugin.
     *
     * Plugin:
     * Provides build functionality.
     *
     * package:
     * Creates the project artifact.
     *
     * install:
     * Installs the artifact in the local repository.
     *
     * deploy:
     * Publishes the artifact to a remote repository.
     *
     * settings.xml:
     * User or installation-level Maven configuration.
     *
     * Maven Wrapper:
     * Uses a project-configured Maven distribution.
     *
     * Spring Boot Parent:
     * Provides useful Maven defaults and dependency management.
     *
     * Spring Boot Starter:
     * Simplifies dependency configuration for a feature.
     *
     * Spring Boot Maven Plugin:
     * Provides Spring Boot build and run functionality.
     *
     * Executable JAR:
     * Packaged Spring Boot application that can be
     * launched using java -jar.
     *
     * =================================================================
     *         END OF MAVEN WITH SPRING BOOT AND BEST PRACTICES
     * =================================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Maven with Spring Boot and Best Practices , Happy Learning ..!");
    }
}
