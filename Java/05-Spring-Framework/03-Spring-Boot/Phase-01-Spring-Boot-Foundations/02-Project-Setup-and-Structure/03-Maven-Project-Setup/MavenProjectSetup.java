
public class MavenProjectSetup {

    /*
     * ============================================================
     *                  MAVEN PROJECT SETUP
     * ============================================================
     *
     * 1. WHAT IS MAVEN PROJECT SETUP?
     *
     * Maven is a build automation and project management tool
     * commonly used in Java and Spring Boot applications.
     *
     * Maven helps developers:
     *
     * - Create standardized Java projects.
     * - Manage project dependencies.
     * - Compile Java source code.
     * - Execute tests.
     * - Package applications.
     * - Run build plugins.
     * - Manage project build lifecycles.
     *
     * A Spring Boot Maven project uses Maven to manage its build
     * process and dependencies.
     *
     *
     * 2. WAYS TO CREATE A SPRING BOOT MAVEN PROJECT
     *
     * Common approaches:
     *
     * 1. Spring Initializr website.
     * 2. Spring Initializr integration in IDEs.
     * 3. Manually creating a Maven project.
     * 4. Using an existing Spring Boot project template.
     *
     * Spring Initializr is the most common starting point for
     * creating a new Spring Boot project.
     *
     *
     * 3. WHAT IS SPRING INITIALIZR?
     *
     * Spring Initializr is a project-generation service.
     *
     * It generates a ready-to-use Spring Boot project based on
     * the options selected by the developer.
     *
     * Common selections include:
     *
     * - Project type: Maven or Gradle.
     * - Language: Java, Kotlin, or Groovy.
     * - Spring Boot version.
     * - Group.
     * - Artifact.
     * - Project name.
     * - Description.
     * - Package name.
     * - Packaging type.
     * - Java version.
     * - Dependencies.
     *
     * Website:
     *
     * https://start.spring.io
     *
     *
     * 4. UNDERSTANDING PROJECT METADATA
     *
     * Group:
     * - Identifies the organization or logical group.
     * - Commonly represented as a reverse-domain name.
     *
     * Example:
     *
     * com.company
     *
     * Artifact:
     * - Identifies the project or generated artifact.
     *
     * Example:
     *
     * employee-management
     *
     * Name:
     * - Human-readable project name.
     *
     * Description:
     * - Short explanation of the project.
     *
     * Package name:
     * - Base Java package for generated source code.
     *
     * Example:
     *
     * com.company.employee
     *
     * Version:
     * - Identifies the Spring Boot version used by the project.
     *
     * Java version:
     * - Selects the Java language and runtime compatibility level.
     *
     *
     * 5. MAVEN PROJECT STRUCTURE
     *
     * A generated Spring Boot Maven project commonly contains:
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
     * |   |   |
     * |   |   +-- resources/
     * |   |
     * |   +-- test/
     * |       |
     * |       +-- java/
     * |       |
     * |       +-- resources/
     * |
     * +-- target/
     *
     * The target directory is generated during builds.
     *
     * The Maven Wrapper files are commonly included by
     * Spring Initializr.
     *
     *
     * 6. UNDERSTANDING POM.XML
     *
     * POM stands for Project Object Model.
     *
     * pom.xml is the central configuration file of a Maven project.
     *
     * It describes how Maven should build and manage the project.
     *
     * A typical Spring Boot Maven POM contains:
     *
     * - Project coordinates.
     * - Parent POM or dependency management.
     * - Properties.
     * - Dependencies.
     * - Build plugins.
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
     *         <version>YOUR_SPRING_BOOT_VERSION</version>
     *         <relativePath/>
     *     </parent>
     *
     *     <groupId>com.company</groupId>
     *     <artifactId>employee-management</artifactId>
     *     <version>0.0.1-SNAPSHOT</version>
     *
     *     <properties>
     *         <java.version>YOUR_JAVA_VERSION</java.version>
     *     </properties>
     *
     *     <dependencies>
     *
     *         <dependency>
     *             <groupId>org.springframework.boot</groupId>
     *             <artifactId>spring-boot-starter-web</artifactId>
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
     * This is an illustrative POM. Replace the placeholders with
     * compatible versions before using it in a real project.
     *
     *
     * 7. UNDERSTANDING PROJECT COORDINATES
     *
     * Maven identifies a project using coordinates.
     *
     * The main coordinates are:
     *
     * groupId:
     * - Identifies the organization or group.
     *
     * artifactId:
     * - Identifies the project artifact.
     *
     * version:
     * - Identifies the artifact version.
     *
     * Example:
     *
     * <groupId>com.company</groupId>
     * <artifactId>employee-management</artifactId>
     * <version>1.0.0</version>
     *
     * Together:
     *
     * com.company:employee-management:1.0.0
     *
     * These coordinates help Maven identify and resolve artifacts.
     *
     *
     * 8. UNDERSTANDING THE SPRING BOOT PARENT POM
     *
     * A common Spring Boot Maven project uses:
     *
     * spring-boot-starter-parent
     *
     * Example:
     *
     * <parent>
     *     <groupId>org.springframework.boot</groupId>
     *     <artifactId>spring-boot-starter-parent</artifactId>
     *     <version>YOUR_SPRING_BOOT_VERSION</version>
     *     <relativePath/>
     * </parent>
     *
     * It provides useful Maven defaults, including:
     *
     * - Dependency management.
     * - Plugin management.
     * - Build configuration defaults.
     * - Resource filtering defaults.
     *
     * Benefits:
     *
     * - Less repetitive configuration.
     * - Compatible dependency versions.
     * - Convenient plugin defaults.
     *
     * Important:
     *
     * The parent POM is optional. A project can use the
     * spring-boot-dependencies BOM through dependencyManagement
     * instead.
     *
     *
     * 9. UNDERSTANDING DEPENDENCIES
     *
     * Dependencies are external libraries required by the project.
     *
     * Example:
     *
     * <dependency>
     *     <groupId>org.springframework.boot</groupId>
     *     <artifactId>spring-boot-starter-web</artifactId>
     * </dependency>
     *
     * This adds Spring Boot web application dependencies.
     *
     * Another example:
     *
     * <dependency>
     *     <groupId>org.springframework.boot</groupId>
     *     <artifactId>spring-boot-starter-data-jpa</artifactId>
     * </dependency>
     *
     * This adds Spring Data JPA integration and its managed
     * dependencies.
     *
     * Dependencies are declared inside the dependencies element.
     *
     * Detailed dependency management and starters are covered
     * in their dedicated topics.
     *
     *
     * 10. UNDERSTANDING BUILD PLUGINS
     *
     * Maven plugins perform build tasks.
     *
     * Examples:
     *
     * - Compiling Java code.
     * - Running tests.
     * - Packaging JAR files.
     * - Creating executable Spring Boot JARs.
     *
     * Spring Boot Maven Plugin:
     *
     * <plugin>
     *     <groupId>org.springframework.boot</groupId>
     *     <artifactId>spring-boot-maven-plugin</artifactId>
     * </plugin>
     *
     * It provides Spring Boot-specific Maven goals.
     *
     * For example:
     *
     * mvn spring-boot:run
     *
     * Runs the application using the Spring Boot Maven plugin.
     *
     * The plugin can also create executable JAR archives when
     * the appropriate packaging goal is executed.
     *
     *
     * 11. MAVEN WRAPPER
     *
     * Maven Wrapper allows a project to use a configured Maven
     * distribution without requiring developers to install
     * Maven separately.
     *
     * Common files:
     *
     * mvnw
     * - Linux and macOS wrapper script.
     *
     * mvnw.cmd
     * - Windows wrapper script.
     *
     * .mvn/wrapper/
     * - Wrapper configuration.
     *
     * Linux command:
     *
     * ./mvnw clean package
     *
     * Windows command:
     *
     * mvnw.cmd clean package
     *
     * Benefits:
     *
     * - Consistent Maven version.
     * - Easier project setup.
     * - Reproducible builds.
     *
     *
     * 12. IMPORTANT MAVEN COMMANDS
     *
     * Check Maven installation:
     *
     * mvn -version
     *
     * Check Java installation:
     *
     * java -version
     *
     * Clean previous build output:
     *
     * mvn clean
     *
     * Compile production source:
     *
     * mvn compile
     *
     * Run tests:
     *
     * mvn test
     *
     * Package the project:
     *
     * mvn package
     *
     * Install the artifact into the local Maven repository:
     *
     * mvn install
     *
     * Run a Spring Boot application:
     *
     * mvn spring-boot:run
     *
     * Run with Maven Wrapper:
     *
     * ./mvnw spring-boot:run
     *
     *
     * 13. CREATING A PROJECT USING SPRING INITIALIZR
     *
     * Typical workflow:
     *
     * STEP 1:
     * Open https://start.spring.io
     *
     * STEP 2:
     * Select Maven as the project type.
     *
     * STEP 3:
     * Select Java as the language.
     *
     * STEP 4:
     * Select a compatible Spring Boot version.
     *
     * STEP 5:
     * Enter Group and Artifact.
     *
     * STEP 6:
     * Choose the package name.
     *
     * STEP 7:
     * Select the Java version.
     *
     * STEP 8:
     * Add required dependencies.
     *
     * STEP 9:
     * Generate and download the project.
     *
     * STEP 10:
     * Extract the downloaded ZIP file.
     *
     * STEP 11:
     * Open the extracted project in an IDE.
     *
     * STEP 12:
     * Allow Maven to import and resolve dependencies.
     *
     *
     * 14. OPENING A MAVEN PROJECT IN AN IDE
     *
     * Common IDEs:
     *
     * - IntelliJ IDEA
     * - Eclipse
     * - Spring Tool Suite
     * - Visual Studio Code
     *
     * General process:
     *
     * 1. Open the project directory.
     * 2. Make sure the IDE recognizes pom.xml.
     * 3. Allow Maven dependency resolution.
     * 4. Verify the configured JDK.
     * 5. Run the main application class.
     *
     * In VS Code, useful extensions include:
     *
     * - Extension Pack for Java.
     * - Spring Boot Extension Pack.
     *
     * These extensions provide Java and Spring Boot development
     * support.
     *
     *
     * 15. RUNNING A SPRING BOOT MAVEN PROJECT
     *
     * Method 1: Run through Maven.
     *
     * mvn spring-boot:run
     *
     * Method 2: Run using Maven Wrapper.
     *
     * ./mvnw spring-boot:run
     *
     * Method 3: Run the main method from the IDE.
     *
     * Method 4: Package and execute the generated JAR.
     *
     * mvn clean package
     *
     * java -jar target/application.jar
     *
     * The actual JAR filename depends on the project's artifact
     * name and version.
     *
     *
     * 16. COMMON MAVEN PROJECT SETUP ERRORS
     *
     * ERROR 1: JAVA VERSION MISMATCH
     *
     * Possible cause:
     * - Project requires a different JDK version.
     *
     * Check:
     *
     * java -version
     * mvn -version
     *
     * Ensure Maven and the IDE use a compatible JDK.
     *
     *
     * ERROR 2: DEPENDENCY RESOLUTION FAILURE
     *
     * Possible causes:
     * - Network problems.
     * - Incorrect dependency coordinates.
     * - Repository unavailable.
     * - Incompatible versions.
     *
     * Useful command:
     *
     * mvn dependency:tree
     *
     *
     * ERROR 3: MAVEN WRAPPER PERMISSION DENIED
     *
     * On Linux:
     *
     * chmod +x mvnw
     *
     * Then:
     *
     * ./mvnw clean package
     *
     *
     * ERROR 4: POM.XML SYNTAX ERROR
     *
     * Possible causes:
     * - Missing closing XML tags.
     * - Incorrect nesting.
     * - Invalid XML characters.
     *
     * Check the Maven build output for the exact line and position.
     *
     *
     * ERROR 5: MAIN CLASS NOT FOUND
     *
     * Possible causes:
     * - Incorrect package declaration.
     * - Incorrect main class configuration.
     * - Source compilation failure.
     *
     * Verify the main application class and build output.
     *
     *
     * 17. BEST PRACTICES
     *
     * - Use Spring Initializr for standard project generation.
     * - Use Maven Wrapper in shared projects.
     * - Select a Java version supported by the chosen Spring Boot
     *   release.
     * - Keep pom.xml organized.
     * - Use Spring Boot dependency management.
     * - Avoid unnecessary dependency version overrides.
     * - Use meaningful groupId and artifactId values.
     * - Keep secrets outside source-controlled configuration.
     * - Build and test the project before committing changes.
     * - Use a consistent project structure across the team.
     *
     *
     * 18. INTERVIEW QUESTIONS
     *
     * BASIC:
     *
     * 1. What is Maven?
     * 2. What is pom.xml?
     * 3. What is Spring Initializr?
     * 4. What are groupId and artifactId?
     * 5. What is the Maven Wrapper?
     *
     * INTERMEDIATE:
     *
     * 6. What is the purpose of spring-boot-starter-parent?
     * 7. What is the Spring Boot Maven Plugin?
     * 8. What is the difference between mvn package and mvn install?
     * 9. How do you run a Spring Boot Maven application?
     * 10. Why do we use Maven Wrapper?
     *
     * ADVANCED:
     *
     * 11. Can we use Spring Boot without spring-boot-starter-parent?
     * 12. What is the difference between dependency management
     *     and declaring dependencies?
     * 13. How do you investigate dependency resolution problems?
     * 14. Why can Maven and an IDE use different JDK versions?
     * 15. How would you make a Maven build consistent across
     *     a development team?
     *
     *
     * 19. PRACTICE EXERCISES
     *
     * Exercise 1:
     * Generate a Spring Boot Maven project using Spring Initializr.
     *
     * Exercise 2:
     * Inspect the generated pom.xml.
     *
     * Exercise 3:
     * Identify the project coordinates.
     *
     * Exercise 4:
     * Add spring-boot-starter-web.
     *
     * Exercise 5:
     * Run the application using ./mvnw spring-boot:run.
     *
     * Exercise 6:
     * Execute mvn clean package.
     *
     * Exercise 7:
     * Inspect the target directory.
     *
     * Exercise 8:
     * Run the packaged executable JAR.
     *
     * Exercise 9:
     * Compare the Maven Wrapper version with an installed Maven
     * version, if both are available.
     *
     *
     * 20. QUICK REVISION
     *
     * - Maven manages dependencies and builds Java projects.
     * - Spring Initializr generates Spring Boot project structures.
     * - pom.xml is the Maven project configuration file.
     * - groupId identifies the project group.
     * - artifactId identifies the project artifact.
     * - spring-boot-starter-parent provides useful Maven defaults.
     * - Dependencies provide external libraries.
     * - Plugins perform build tasks.
     * - Maven Wrapper provides a consistent Maven distribution.
     * - ./mvnw spring-boot:run starts the application.
     * - mvn clean package builds the application package.
     *
     * ============================================================
     *                    END OF NOTES
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("Maven Project Setup - Learning Notes");
    }

}
