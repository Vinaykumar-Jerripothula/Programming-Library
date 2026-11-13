
public class POMAndProjectStructure {

    /*
     * ================================================================
     *                  POM AND PROJECT STRUCTURE
     * ================================================================
     *
     * 1. WHAT IS POM?
     *
     * POM stands for Project Object Model.
     *
     * It is the fundamental configuration model used by Maven
     * to understand and manage a project.
     *
     * Every Maven project has a POM file.
     *
     * Its default filename is:
     *
     * pom.xml
     *
     * The POM describes:
     *
     * - Project identity.
     * - Project version.
     * - Dependencies.
     * - Dependency management.
     * - Build configuration.
     * - Plugins.
     * - Project properties.
     * - Parent project.
     * - Profiles.
     *
     * Maven reads this information to determine how the
     * project should be built.
     *
     *
     * 2. WHY IS POM.XML IMPORTANT?
     *
     * Imagine working on a Spring Boot application.
     *
     * Your application requires:
     *
     * - Spring Web.
     * - Spring Data JPA.
     * - Spring Security.
     * - MySQL Connector.
     * - Validation.
     * - JUnit.
     *
     * Instead of manually downloading and configuring
     * every library, you declare them in pom.xml.
     *
     * Maven reads the POM and resolves the dependencies.
     *
     * Similarly, you can configure:
     *
     * - Java compilation version.
     * - Build plugins.
     * - Test execution.
     * - Packaging.
     * - Application build behavior.
     *
     *
     * 3. BASIC STRUCTURE OF POM.XML
     *
     * A minimal POM:
     *
     * <project xmlns="http://maven.apache.org/POM/4.0.0"
     *          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
     *          xsi:schemaLocation="
     *              http://maven.apache.org/POM/4.0.0
     *              https://maven.apache.org/xsd/maven-4.0.0.xsd">
     *
     *     <modelVersion>4.0.0</modelVersion>
     *
     *     <groupId>com.example</groupId>
     *     <artifactId>demo</artifactId>
     *     <version>1.0.0</version>
     *
     * </project>
     *
     * Important:
     *
     * - project is the root element.
     * - modelVersion identifies the POM model version.
     * - groupId identifies the organization or project group.
     * - artifactId identifies this project.
     * - version identifies the project version.
     *
     * The XML namespace and schema declarations help
     * identify and validate the POM structure.
     *
     *
     * 4. MODEL VERSION
     *
     * <modelVersion>4.0.0</modelVersion>
     *
     * It specifies the version of the Maven POM model.
     *
     * Important:
     *
     * modelVersion is NOT the Maven software version.
     *
     * For example:
     *
     * Maven software version: 3.9.x
     * POM model version:       4.0.0
     *
     * Most commonly used Maven projects use modelVersion 4.0.0.
     *
     *
     * 5. MAVEN COORDINATES
     *
     * Maven identifies a project or artifact using coordinates.
     *
     * The three fundamental coordinates are:
     *
     * 1. groupId
     * 2. artifactId
     * 3. version
     *
     * Example:
     *
     * <groupId>com.company</groupId>
     * <artifactId>employee-service</artifactId>
     * <version>1.0.0</version>
     *
     *
     * 6. GROUP ID
     *
     * groupId identifies the organization, group or namespace
     * responsible for the project.
     *
     * Example:
     *
     * <groupId>com.company</groupId>
     *
     * Common conventions:
     *
     * com.company
     * org.example
     * io.project
     *
     * It is commonly written using reverse-domain notation.
     *
     * Example:
     *
     * Company domain:
     * company.com
     *
     * Maven groupId:
     * com.company
     *
     * Important:
     * The groupId is an identifier, not necessarily a real
     * website or package that must exist.
     *
     *
     * 7. ARTIFACT ID
     *
     * artifactId identifies the particular project or module.
     *
     * Example:
     *
     * <artifactId>employee-service</artifactId>
     *
     * A company may have multiple projects:
     *
     * com.company:employee-service
     * com.company:payment-service
     * com.company:notification-service
     *
     * Their groupId may be the same, but artifactIds differ.
     *
     *
     * 8. VERSION
     *
     * version identifies the version of the project artifact.
     *
     * Example:
     *
     * <version>1.0.0</version>
     *
     * Common version examples:
     *
     * 1.0.0
     * 1.1.0
     * 2.0.0
     * 1.0.0-SNAPSHOT
     *
     * SNAPSHOT:
     *
     * A SNAPSHOT version represents a development version
     * that may change as development continues.
     *
     * Example:
     *
     * <version>1.0.0-SNAPSHOT</version>
     *
     * Release version:
     *
     * <version>1.0.0</version>
     *
     * A release version is intended to represent a stable
     * published version.
     *
     * Important:
     * SNAPSHOT is a Maven version convention.
     * It is not a separate packaging type.
     *
     *
     * 9. PACKAGING
     *
     * packaging specifies the type of artifact Maven
     * should produce for the project.
     *
     * Example:
     *
     * <packaging>jar</packaging>
     *
     * Common packaging types:
     *
     * jar:
     * Creates a Java Archive.
     *
     * war:
     * Creates a Web Application Archive.
     *
     * pom:
     * Commonly used for parent and aggregator projects.
     *
     * If packaging is not specified, Maven uses jar
     * as the default packaging.
     *
     * Important:
     * Spring Boot applications commonly use JAR packaging.
     *
     *
     * 10. NAME AND DESCRIPTION
     *
     * These elements provide human-readable project information.
     *
     * Example:
     *
     * <name>Employee Management System</name>
     *
     * <description>
     *     Backend application for employee management.
     * </description>
     *
     * <url>https://example.com</url>
     *
     * They are optional project metadata.
     *
     *
     * 11. PARENT POM
     *
     * A POM can inherit configuration from another POM.
     *
     * The POM that provides configuration is called
     * the parent POM.
     *
     * The POM receiving that configuration is called
     * the child POM.
     *
     * Example:
     *
     * <parent>
     *     <groupId>org.springframework.boot</groupId>
     *     <artifactId>spring-boot-starter-parent</artifactId>
     *     <version>3.5.0</version>
     *     <relativePath/>
     * </parent>
     *
     * The example version is illustrative.
     *
     * This is commonly seen in Spring Boot Maven projects.
     *
     * A parent POM can provide:
     *
     * - Dependency management.
     * - Plugin management.
     * - Properties.
     * - Build configuration.
     * - Other inherited project configuration.
     *
     * Important:
     * A parent POM is not the same as a Java superclass.
     *
     * It is Maven project configuration inheritance.
     *
     *
     * 12. RELATIVE PATH IN PARENT
     *
     * Example:
     *
     * <parent>
     *     <groupId>com.company</groupId>
     *     <artifactId>company-parent</artifactId>
     *     <version>1.0.0</version>
     *     <relativePath>../pom.xml</relativePath>
     * </parent>
     *
     * relativePath tells Maven where to look for the
     * parent POM in the local filesystem.
     *
     * If omitted, Maven normally checks ../pom.xml first.
     *
     * If specified as:
     *
     * <relativePath/>
     *
     * Local relative-path lookup is disabled, and Maven
     * resolves the parent through configured repositories.
     *
     *
     * 13. SUPER POM
     *
     * Every Maven POM inherits configuration from
     * a built-in parent called the Super POM.
     *
     * The Super POM provides default Maven configuration.
     *
     * It includes defaults related to:
     *
     * - Build directories.
     * - Plugin repositories.
     * - Repositories.
     * - Build behavior.
     *
     * Conceptual inheritance:
     *
     * Super POM
     *     |
     *     v
     * Parent POM
     *     |
     *     v
     * Child POM
     *
     * If a project has no explicitly declared parent,
     * it still inherits from the Super POM.
     *
     * To inspect the effective POM:
     *
     * mvn help:effective-pom
     *
     *
     * 14. PROPERTIES
     *
     * Properties allow us to define reusable values
     * in pom.xml.
     *
     * Example:
     *
     * <properties>
     *     <maven.compiler.release>21</maven.compiler.release>
     *     <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
     *     <junit.version>5.11.0</junit.version>
     * </properties>
     *
     * Access a property using:
     *
     * ${propertyName}
     *
     * Example:
     *
     * <version>${junit.version}</version>
     *
     * Benefits:
     *
     * - Avoid repeated values.
     * - Centralize version configuration.
     * - Make configuration easier to maintain.
     *
     * Common built-in project properties:
     *
     * ${project.groupId}
     * ${project.artifactId}
     * ${project.version}
     * ${project.name}
     * ${project.build.directory}
     *
     *
     * 15. BUILD ELEMENT
     *
     * The build element configures how Maven builds
     * the project.
     *
     * Example:
     *
     * <build>
     *
     *     <finalName>employee-service</finalName>
     *
     *     <plugins>
     *         <!-- Build plugins are configured here. -->
     *     </plugins>
     *
     * </build>
     *
     * Important configuration includes:
     *
     * - Source directory.
     * - Test source directory.
     * - Output directory.
     * - Resources.
     * - Plugins.
     * - Final artifact name.
     *
     * We will study build plugins and lifecycle in file 04.
     *
     *
     * 16. SOURCE DIRECTORY AND TEST DIRECTORY
     *
     * Maven follows standard directory conventions.
     *
     * Main source:
     *
     * src/main/java
     *
     * Test source:
     *
     * src/test/java
     *
     * Main resources:
     *
     * src/main/resources
     *
     * Test resources:
     *
     * src/test/resources
     *
     * Maven recognizes these directories automatically
     * in a standard project.
     *
     * You can customize them through build configuration,
     * but normally you should follow the standard structure.
     *
     *
     * 17. RESOURCES ELEMENT
     *
     * Resources are non-Java files required by an application.
     *
     * Examples:
     *
     * - application.properties
     * - application.yml
     * - XML configuration files.
     * - Templates.
     * - Static files.
     *
     * Example:
     *
     * <build>
     *     <resources>
     *         <resource>
     *             <directory>src/main/resources</directory>
     *         </resource>
     *     </resources>
     * </build>
     *
     * Maven copies configured resources into the build output.
     *
     * Important:
     * Standard Maven projects already recognize
     * src/main/resources by default.
     *
     *
     * 18. DEPENDENCIES ELEMENT
     *
     * The dependencies element declares libraries
     * required by the project.
     *
     * Example:
     *
     * <dependencies>
     *
     *     <dependency>
     *         <groupId>org.springframework.boot</groupId>
     *         <artifactId>spring-boot-starter-web</artifactId>
     *         <version>3.5.0</version>
     *     </dependency>
     *
     * </dependencies>
     *
     * The example version is illustrative.
     *
     * Maven resolves this dependency and its transitive
     * dependencies.
     *
     * We will study dependencies in detail in file 03.
     *
     *
     * 19. DEPENDENCY MANAGEMENT ELEMENT
     *
     * dependencyManagement controls dependency versions
     * and related dependency configuration for projects.
     *
     * Example:
     *
     * <dependencyManagement>
     *     <dependencies>
     *
     *         <dependency>
     *             <groupId>org.junit</groupId>
     *             <artifactId>junit-bom</artifactId>
     *             <version>5.11.0</version>
     *             <type>pom</type>
     *             <scope>import</scope>
     *         </dependency>
     *
     *     </dependencies>
     * </dependencyManagement>
     *
     * Important:
     *
     * dependencies:
     * Declares dependencies that the current project uses.
     *
     * dependencyManagement:
     * Defines dependency configuration that can be
     * inherited or referenced without declaring the
     * dependency as an active dependency by itself.
     *
     * A dependency listed only in dependencyManagement
     * is not automatically added to the project's classpath.
     *
     *
     * 20. PLUGIN MANAGEMENT
     *
     * pluginManagement provides default configuration
     * for plugins.
     *
     * Example:
     *
     * <build>
     *     <pluginManagement>
     *         <plugins>
     *
     *             <plugin>
     *                 <groupId>org.apache.maven.plugins</groupId>
     *                 <artifactId>maven-compiler-plugin</artifactId>
     *                 <version>3.13.0</version>
     *             </plugin>
     *
     *         </plugins>
     *     </pluginManagement>
     * </build>
     *
     * Important:
     *
     * pluginManagement does not normally activate the
     * managed plugin by itself.
     *
     * It provides default configuration and versions
     * when the plugin is used.
     *
     * Compare:
     *
     * plugins:
     * Declares plugins for use in the current build.
     *
     * pluginManagement:
     * Manages plugin defaults, especially across
     * parent and child projects.
     *
     *
     * 21. PROFILES
     *
     * Maven profiles allow different build configurations
     * for different environments or conditions.
     *
     * Example:
     *
     * <profiles>
     *
     *     <profile>
     *         <id>development</id>
     *         <properties>
     *             <environment>development</environment>
     *         </properties>
     *     </profile>
     *
     *     <profile>
     *         <id>production</id>
     *         <properties>
     *             <environment>production</environment>
     *         </properties>
     *     </profile>
     *
     * </profiles>
     *
     * Activate a profile:
     *
     * mvn package -Pproduction
     *
     * We will study profiles in file 05.
     *
     * Important:
     * Maven profiles configure the build.
     * They are not the same as Spring Boot application profiles.
     *
     *
     * 22. MODULES
     *
     * Maven supports multi-module projects.
     *
     * A parent or aggregator POM can list child modules.
     *
     * Example:
     *
     * <modules>
     *     <module>employee-service</module>
     *     <module>payment-service</module>
     *     <module>notification-service</module>
     * </modules>
     *
     * Each module usually has its own pom.xml.
     *
     * A multi-module project helps organize larger
     * applications into separate components.
     *
     * Important:
     * Aggregation and inheritance are related but different.
     *
     * Aggregation:
     * Builds multiple modules together.
     *
     * Inheritance:
     * Shares configuration from a parent POM.
     *
     * A POM can perform both roles.
     *
     *
     * 23. COMPLETE SPRING BOOT POM EXAMPLE
     *
     * The following example demonstrates a common
     * Spring Boot Maven project structure.
     *
     * The version is illustrative.
     *
     * <project xmlns="http://maven.apache.org/POM/4.0.0"
     *          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
     *          xsi:schemaLocation="
     *              http://maven.apache.org/POM/4.0.0
     *              https://maven.apache.org/xsd/maven-4.0.0.xsd">
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
     *     <artifactId>employee-service</artifactId>
     *     <version>1.0.0-SNAPSHOT</version>
     *
     *     <name>employee-service</name>
     *     <description>Employee management backend</description>
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
     * Explanation:
     *
     * parent:
     * Inherits Spring Boot's parent POM configuration.
     *
     * groupId:
     * Identifies the project group.
     *
     * artifactId:
     * Identifies the application.
     *
     * version:
     * Identifies the project version.
     *
     * properties:
     * Defines the Java version.
     *
     * dependencies:
     * Declares application libraries.
     *
     * build/plugins:
     * Configures the Spring Boot Maven Plugin.
     *
     * Notice:
     * The dependencies do not specify versions.
     *
     * The Spring Boot parent manages compatible dependency
     * versions through dependency management.
     *
     *
     * 24. EFFECTIVE POM
     *
     * The effective POM is the final POM configuration
     * after Maven applies inheritance, profiles,
     * interpolation and other model-building rules.
     *
     * It helps you understand the configuration Maven
     * actually uses.
     *
     * Generate it using:
     *
     * mvn help:effective-pom
     *
     * Useful when:
     *
     * - A parent POM provides unexpected configuration.
     * - Dependency versions are inherited.
     * - Plugin configuration comes from a parent.
     * - You need to troubleshoot a build.
     *
     *
     * 25. SETTINGS.XML VS POM.XML
     *
     * pom.xml:
     *
     * - Project-level configuration.
     * - Shared with the project.
     * - Defines dependencies and build configuration.
     *
     * settings.xml:
     *
     * - User or installation-level configuration.
     * - Usually not committed with the project.
     * - Can configure local repository location.
     * - Can configure repository credentials and mirrors.
     * - Can activate settings-level profiles.
     *
     * Common settings locations:
     *
     * User settings:
     * ~/.m2/settings.xml
     *
     * Global settings:
     * Maven installation/conf/settings.xml
     *
     * Important:
     * Do not commit passwords, tokens or repository
     * credentials into a project's pom.xml.
     *
     *
     * 26. POM.XML VS SETTINGS.XML
     *
     * pom.xml:
     * Describes the project.
     *
     * settings.xml:
     * Describes Maven environment and user configuration.
     *
     * Example:
     *
     * A project declares a dependency in pom.xml.
     *
     * A developer's settings.xml may configure a company
     * repository mirror from which Maven retrieves it.
     *
     *
     * 27. COMMON POM.XML MISTAKES
     *
     * 1. Confusing modelVersion with Maven version.
     *
     * 2. Confusing groupId with artifactId.
     *
     * 3. Thinking dependencyManagement automatically adds
     *    dependencies to the classpath.
     *
     * 4. Thinking pluginManagement automatically executes
     *    every managed plugin.
     *
     * 5. Confusing Maven profiles with Spring profiles.
     *
     * 6. Assuming a parent POM is a Java superclass.
     *
     * 7. Hardcoding dependency versions unnecessarily
     *    when a suitable BOM manages them.
     *
     * 8. Storing credentials in pom.xml.
     *
     * 9. Confusing aggregation with inheritance.
     *
     *
     * 28. INTERVIEW QUESTIONS
     *
     * 1. What is POM?
     * 2. What is modelVersion?
     * 3. What are Maven coordinates?
     * 4. What is the difference between groupId and artifactId?
     * 5. What is a SNAPSHOT version?
     * 6. What is packaging in Maven?
     * 7. What is a parent POM?
     * 8. What is the Super POM?
     * 9. What is an effective POM?
     * 10. What is the purpose of properties?
     * 11. What is the difference between dependencies and
     *     dependencyManagement?
     * 12. What is the difference between plugins and
     *     pluginManagement?
     * 13. What is a Maven profile?
     * 14. What is a multi-module project?
     * 15. What is the difference between aggregation
     *     and inheritance?
     * 16. What is the difference between pom.xml
     *     and settings.xml?
     * 17. Why does a Spring Boot POM often omit dependency
     *     versions?
     *
     *
     * 29. QUICK REVISION
     *
     * POM:
     * Project Object Model.
     *
     * pom.xml:
     * Main Maven project configuration file.
     *
     * modelVersion:
     * Version of the POM model.
     *
     * groupId:
     * Project group identifier.
     *
     * artifactId:
     * Project artifact identifier.
     *
     * version:
     * Project version.
     *
     * packaging:
     * Type of artifact produced.
     *
     * Parent POM:
     * Provides inherited project configuration.
     *
     * Super POM:
     * Built-in Maven parent configuration.
     *
     * Properties:
     * Reusable configuration values.
     *
     * dependencies:
     * Declares dependencies used by the project.
     *
     * dependencyManagement:
     * Controls dependency configuration and versions.
     *
     * plugins:
     * Declares build plugins.
     *
     * pluginManagement:
     * Provides managed plugin defaults.
     *
     * Profiles:
     * Conditional build configurations.
     *
     * Modules:
     * Child projects in a multi-module build.
     *
     * Effective POM:
     * Final calculated project configuration.
     *
     * settings.xml:
     * Maven user and installation configuration.
     *
     * ================================================================
     *                 END OF POM AND PROJECT STRUCTURE
     * ================================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about POM and Project Structure , Happy Learning ..!");
    }
}
