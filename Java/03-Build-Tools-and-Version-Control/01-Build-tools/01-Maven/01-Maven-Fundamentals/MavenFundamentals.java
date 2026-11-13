
public class MavenFundamentals {

    /*
     * ================================================================
     *                       MAVEN FUNDAMENTALS
     * ================================================================
     *
     * 1. WHAT IS MAVEN?
     *
     * Apache Maven is a build automation and project management
     * tool primarily used for Java projects.
     *
     * Imagine developing a Java application.
     *
     * You need to:
     *
     * 1. Download required libraries.
     * 2. Add those libraries to your project.
     * 3. Compile Java source files.
     * 4. Run tests.
     * 5. Package the application into a JAR or WAR.
     * 6. Manage different versions of dependencies.
     *
     * Performing all these tasks manually becomes difficult,
     * especially when a project grows.
     *
     * Maven automates these tasks using a standard project
     * structure and a configuration file called pom.xml.
     *
     *
     * 2. WHY DO WE NEED MAVEN?
     *
     * Consider a Java application that uses:
     *
     * - Spring Boot
     * - Hibernate
     * - MySQL Connector
     * - Jackson
     * - JUnit
     *
     * Without a build tool, you may need to:
     *
     * - Download every JAR manually.
     * - Add every JAR to the classpath.
     * - Manage compatible versions yourself.
     * - Compile source files manually.
     * - Configure testing manually.
     * - Package the application manually.
     *
     * This creates unnecessary complexity.
     *
     * Maven solves these problems through:
     *
     * - Dependency management.
     * - Build automation.
     * - Standard project structure.
     * - Plugin management.
     * - Project configuration.
     * - Reproducible build processes.
     *
     *
     * 3. WHAT IS A BUILD TOOL?
     *
     * A build tool automates the process of converting
     * source code into an executable or distributable artifact.
     *
     * A typical Java build process:
     *
     * Java source code
     *       |
     *       v
     * Dependency resolution
     *       |
     *       v
     * Compilation
     *       |
     *       v
     * Testing
     *       |
     *       v
     * Packaging
     *       |
     *       v
     * JAR / WAR
     *
     * Maven automates this workflow.
     *
     * Important:
     * Maven is not a Java compiler.
     *
     * It invokes tools such as the Java compiler through
     * its build lifecycle and plugins.
     *
     *
     * 4. WHAT IS DEPENDENCY MANAGEMENT?
     *
     * A dependency is an external library or module that
     * your application needs.
     *
     * Example:
     *
     * Suppose your application needs Spring Boot.
     *
     * Instead of manually downloading Spring Boot JAR files,
     * you declare the required dependency in pom.xml.
     *
     * Maven resolves the dependency and its required
     * transitive dependencies.
     *
     * Example dependency:
     *
     * <dependency>
     *     <groupId>org.springframework.boot</groupId>
     *     <artifactId>spring-boot-starter-web</artifactId>
     *     <version>3.5.0</version>
     * </dependency>
     *
     * The example version is illustrative.
     *
     * Maven downloads required artifacts from configured
     * repositories.
     *
     *
     * 5. WHAT IS APACHE MAVEN?
     *
     * Apache Maven is an open-source project maintained
     * by the Apache Software Foundation.
     *
     * Official website:
     *
     * https://maven.apache.org/
     *
     * Maven is primarily associated with Java projects,
     * but it can also build projects using other languages
     * through suitable plugins.
     *
     *
     * 6. MAVEN VS MANUAL JAVA PROJECT
     *
     * Manual Java project:
     *
     * - Dependencies may need to be downloaded manually.
     * - Classpath configuration may be manual.
     * - Build steps may be executed manually.
     * - Project structure may differ between developers.
     *
     * Maven project:
     *
     * - Dependencies are declared in pom.xml.
     * - Maven resolves dependencies.
     * - Build tasks are automated.
     * - Standard project structure is encouraged.
     * - Build configuration can be shared through source control.
     *
     *
     * 7. MAVEN VS GRADLE
     *
     * Both are popular Java build automation tools.
     *
     * Maven:
     *
     * - Uses XML configuration.
     * - Uses pom.xml.
     * - Uses predefined build lifecycles.
     * - Follows convention-based project structure.
     *
     * Gradle:
     *
     * - Commonly uses Groovy or Kotlin DSL.
     * - Uses build.gradle or build.gradle.kts.
     * - Offers flexible task-based build configuration.
     * - Supports highly customizable build logic.
     *
     * Neither tool is universally better.
     *
     * Maven is widely used in enterprise Java applications.
     * Gradle is also widely used, especially in Android
     * and many modern Java projects.
     *
     *
     * 8. WHAT IS A MAVEN PROJECT?
     *
     * A Maven project is a project configured according
     * to Maven's project model.
     *
     * Its main configuration file is:
     *
     * pom.xml
     *
     * POM means Project Object Model.
     *
     * The pom.xml file describes information such as:
     *
     * - Project identity.
     * - Dependencies.
     * - Build configuration.
     * - Plugins.
     * - Project properties.
     * - Parent project information.
     *
     *
     * 9. STANDARD MAVEN PROJECT STRUCTURE
     *
     * A typical Maven project looks like this:
     *
     * my-project/
     * |
     * |-- pom.xml
     * |
     * |-- src/
     * |   |
     * |   |-- main/
     * |   |   |
     * |   |   |-- java/
     * |   |   |-- resources/
     * |   |
     * |   |-- test/
     * |       |
     * |       |-- java/
     * |       |-- resources/
     * |
     * |-- target/
     *
     * Explanation:
     *
     * pom.xml:
     * Main Maven project configuration file.
     *
     * src/main/java:
     * Application Java source code.
     *
     * src/main/resources:
     * Application resources such as configuration files.
     *
     * src/test/java:
     * Test source code.
     *
     * src/test/resources:
     * Resources required by tests.
     *
     * target:
     * Generated build output, including compiled classes,
     * test reports and packaged artifacts.
     *
     * Important:
     * target is generated during the build.
     * It is normally not committed to Git.
     *
     *
     * 10. WHAT IS POM.XML?
     *
     * POM stands for Project Object Model.
     *
     * It is the fundamental configuration file of a
     * Maven project.
     *
     * Maven reads pom.xml to understand how the project
     * should be built and which dependencies it needs.
     *
     * Example:
     *
     * <project xmlns="http://maven.apache.org/POM/4.0.0"
     *          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
     *          xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
     *          https://maven.apache.org/xsd/maven-4.0.0.xsd">
     *
     *     <modelVersion>4.0.0</modelVersion>
     *
     *     <groupId>com.example</groupId>
     *     <artifactId>demo</artifactId>
     *     <version>1.0.0</version>
     *
     * </project>
     *
     * We will study the complete POM structure in file 02.
     *
     *
     * 11. HOW MAVEN WORKS
     *
     * Consider a developer adding a dependency to pom.xml.
     *
     * Maven follows a process:
     *
     * 1. Reads the project configuration.
     * 2. Identifies declared dependencies.
     * 3. Resolves required dependencies.
     * 4. Downloads missing artifacts from repositories.
     * 5. Executes requested build goals and phases.
     * 6. Invokes appropriate plugins.
     * 7. Produces the requested build output.
     *
     * Example:
     *
     * mvn package
     *
     * Maven resolves dependencies, compiles source code,
     * runs the applicable tests and packages the application.
     *
     *
     * 12. MAVEN ARCHITECTURE - IMPORTANT COMPONENTS
     *
     * Maven uses several components to perform builds.
     *
     * 1. POM:
     *    Describes the project and its build configuration.
     *
     * 2. Maven Core:
     *    Coordinates the build process.
     *
     * 3. Dependency Resolver:
     *    Resolves project dependencies and their requirements.
     *
     * 4. Plugins:
     *    Provide actual build functionality.
     *
     * 5. Repositories:
     *    Store and provide dependencies and plugins.
     *
     * 6. Lifecycle:
     *    Defines standard build phases.
     *
     * 7. Settings:
     *    Provides user-specific and environment-specific
     *    Maven configuration.
     *
     *
     * 13. WHAT ARE MAVEN REPOSITORIES?
     *
     * A repository is a location where Maven artifacts
     * are stored and retrieved.
     *
     * Maven commonly works with three repository types:
     *
     * 1. Local Repository
     * 2. Central Repository
     * 3. Remote Repository
     *
     * Local Repository:
     *
     * - Exists on your machine.
     * - Stores downloaded dependencies and plugins.
     * - Common default location on Linux:
     *
     *   ~/.m2/repository
     *
     * Central Repository:
     *
     * - A public repository provided by Maven Central.
     * - Contains many commonly used Java libraries.
     *
     * Website:
     * https://central.sonatype.com/
     *
     * Remote Repository:
     *
     * - An additional repository configured by an organization
     *   or project.
     * - Can contain internal company libraries.
     *
     * Example:
     *
     * Your project needs a dependency.
     *
     * Maven checks its local repository.
     *
     * If the required artifact is unavailable locally,
     * Maven attempts to retrieve it from configured
     * remote repositories.
     *
     *
     * 14. WHAT IS AN ARTIFACT?
     *
     * An artifact is a packaged or published build output
     * identified by Maven coordinates.
     *
     * Examples:
     *
     * - JAR files.
     * - WAR files.
     * - POM files.
     * - Maven plugins.
     *
     * Example:
     *
     * spring-boot-starter-web-3.5.0.jar
     *
     * An artifact is generally identified by:
     *
     * groupId
     * artifactId
     * version
     *
     * These coordinates help Maven locate and identify it.
     *
     *
     * 15. WHAT IS A MAVEN PLUGIN?
     *
     * Maven itself coordinates the build process.
     *
     * Plugins provide functionality for individual build tasks.
     *
     * Examples:
     *
     * Maven Compiler Plugin:
     * Compiles Java source code.
     *
     * Maven Surefire Plugin:
     * Executes unit tests.
     *
     * Maven JAR Plugin:
     * Creates JAR packages.
     *
     * Maven WAR Plugin:
     * Creates WAR packages.
     *
     * Spring Boot Maven Plugin:
     * Supports Spring Boot application packaging and execution.
     *
     * We will study plugins in detail in file 04.
     *
     *
     * 16. WHAT IS MAVEN LIFECYCLE?
     *
     * A Maven lifecycle defines the sequence of phases
     * involved in a particular build process.
     *
     * Important lifecycles:
     *
     * 1. clean
     * 2. default
     * 3. site
     *
     * The default lifecycle contains phases such as:
     *
     * validate
     * compile
     * test
     * package
     * verify
     * install
     * deploy
     *
     * Example:
     *
     * mvn package
     *
     * Maven executes the earlier phases of the default
     * lifecycle before reaching package.
     *
     * We will explore this in detail in file 04.
     *
     *
     * 17. INSTALLING MAVEN
     *
     * Maven requires a Java Development Kit (JDK).
     *
     * First verify Java:
     *
     * java -version
     *
     * javac -version
     *
     * Verify whether Maven is installed:
     *
     * mvn -version
     *
     * If Maven is installed correctly, the command displays:
     *
     * - Maven version.
     * - Java version.
     * - Java home.
     * - Operating system information.
     *
     * On Linux, Maven can be installed using a package manager
     * or by downloading the official Apache Maven distribution.
     *
     * Official installation guide:
     *
     * https://maven.apache.org/install.html
     *
     * Important:
     * JAVA_HOME should point to a suitable JDK installation.
     *
     * PATH should allow the system to locate the Maven
     * executable.
     *
     *
     * 18. IMPORTANT MAVEN COMMANDS
     *
     * mvn -version
     * Displays Maven and Java environment information.
     *
     * mvn validate
     * Validates the project configuration.
     *
     * mvn compile
     * Compiles application source code.
     *
     * mvn test
     * Runs the test phase.
     *
     * mvn package
     * Packages the application.
     *
     * mvn verify
     * Runs verification checks.
     *
     * mvn install
     * Installs the project artifact into the local repository.
     *
     * mvn clean
     * Removes generated build output.
     *
     * mvn clean install
     * Cleans, builds, tests, packages and installs the project.
     *
     * mvn dependency:tree
     * Displays the dependency hierarchy.
     *
     * mvn help:effective-pom
     * Displays the effective POM.
     *
     * mvn spring-boot:run
     * Runs a Spring Boot application when the Spring Boot
     * Maven Plugin is configured and available.
     *
     * Important:
     * These commands will be explored in their respective files.
     *
     *
     * 19. WHAT IS MAVEN WRAPPER?
     *
     * Maven Wrapper allows a project to use a specified
     * Maven distribution without requiring developers
     * to install Maven globally.
     *
     * Common wrapper files:
     *
     * mvnw
     * mvnw.cmd
     * .mvn/wrapper/maven-wrapper.properties
     *
     * On Linux:
     *
     * ./mvnw clean package
     *
     * On Windows:
     *
     * mvnw.cmd clean package
     *
     * Benefits:
     *
     * - Consistent Maven version across developers.
     * - Easier project setup.
     * - Useful for CI/CD pipelines.
     * - Reduces dependency on global Maven installation.
     *
     * Important:
     * A suitable JDK is still required.
     *
     *
     * 20. MAVEN IN SPRING BOOT DEVELOPMENT
     *
     * Maven is commonly used to build Spring Boot projects.
     *
     * Consider a Spring Boot REST API.
     *
     * It may require:
     *
     * - Spring Boot Web Starter.
     * - Spring Data JPA Starter.
     * - Spring Security Starter.
     * - MySQL Connector.
     * - Validation Starter.
     * - Testing dependencies.
     *
     * Maven manages these dependencies through pom.xml.
     *
     * It also helps:
     *
     * - Compile the application.
     * - Run tests.
     * - Package the application.
     * - Create executable JAR files using the
     *   Spring Boot Maven Plugin.
     *
     * This is why understanding Maven is important
     * before working deeply with Spring Boot.
     *
     *
     * 21. MAVEN VS SPRING BOOT
     *
     * Maven:
     *
     * - Build automation and dependency management tool.
     * - Manages project dependencies.
     * - Executes build lifecycle phases.
     * - Uses pom.xml.
     *
     * Spring Boot:
     *
     * - Framework built on the Spring ecosystem.
     * - Simplifies Spring application development.
     * - Provides auto-configuration and starter dependencies.
     * - Supports embedded application servers.
     *
     * They solve different problems and work together.
     *
     *
     * 22. MAVEN VS MAVEN CENTRAL
     *
     * Maven:
     * The build automation and dependency management tool.
     *
     * Maven Central:
     * A public repository that hosts Java artifacts.
     *
     * Maven can download dependencies from Maven Central.
     *
     * Maven and Maven Central are not the same thing.
     *
     *
     * 23. COMMON MAVEN MISTAKES
     *
     * 1. Forgetting to configure a suitable JDK.
     *
     * 2. Confusing Maven with a Java compiler.
     *
     * 3. Confusing Maven with Maven Central.
     *
     * 4. Adding dependencies without checking compatibility.
     *
     * 5. Committing the target directory unnecessarily.
     *
     * 6. Assuming Maven downloads every dependency
     *    from the internet on every build.
     *
     * 7. Confusing dependency management with
     *    dependency declaration.
     *
     * 8. Assuming mvn package and mvn install do the
     *    same thing.
     *
     * 9. Forgetting that Maven Wrapper still requires
     *    a compatible JDK.
     *
     *
     * 24. INTERVIEW QUESTIONS
     *
     * 1. What is Apache Maven?
     * 2. Why do we need Maven?
     * 3. What is a build automation tool?
     * 4. What is dependency management?
     * 5. What is a POM file?
     * 6. What is an artifact?
     * 7. What are Maven repositories?
     * 8. What is the difference between local and central
     *    repositories?
     * 9. What is a Maven plugin?
     * 10. What is a Maven lifecycle?
     * 11. What is the difference between Maven and Gradle?
     * 12. What is Maven Wrapper?
     * 13. What is the purpose of the target directory?
     * 14. Why is Maven used in Spring Boot projects?
     * 15. What is the difference between Maven and Spring Boot?
     * 16. What is the difference between mvn package
     *     and mvn install?
     * 17. Does Maven compile Java code itself?
     *
     *
     * 25. QUICK REVISION
     *
     * Maven:
     * Build automation and project management tool.
     *
     * POM:
     * Project Object Model; Maven project configuration.
     *
     * Dependency:
     * An external library or module required by a project.
     *
     * Artifact:
     * A packaged or published project component.
     *
     * Repository:
     * A location where Maven artifacts are stored.
     *
     * Plugin:
     * Provides functionality for Maven build tasks.
     *
     * Lifecycle:
     * Defines standard build phases.
     *
     * Local Repository:
     * Stores downloaded artifacts on the developer's machine.
     *
     * Maven Central:
     * Public repository hosting Java artifacts.
     *
     * Maven Wrapper:
     * Allows a project to use a specified Maven distribution.
     *
     * ================================================================
     *                    END OF MAVEN FUNDAMENTALS
     * ================================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Maven Fundamentals , Happy Learning ..!");
    }
}
