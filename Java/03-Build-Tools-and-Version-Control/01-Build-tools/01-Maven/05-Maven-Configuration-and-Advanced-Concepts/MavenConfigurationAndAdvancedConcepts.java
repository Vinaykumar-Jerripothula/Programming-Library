
public class MavenConfigurationAndAdvancedConcepts {

    /*
     * =================================================================
     *             MAVEN CONFIGURATION AND ADVANCED CONCEPTS
     * =================================================================
     *
     * 1. MAVEN CONFIGURATION
     *
     * Maven configuration controls how Maven behaves when
     * building and managing projects.
     *
     * Configuration can be defined in different places:
     *
     * 1. pom.xml
     * 2. settings.xml
     * 3. Maven installation configuration
     * 4. Command-line properties
     * 5. Environment variables
     *
     * Each has a different purpose.
     *
     *
     * 2. WHAT IS settings.xml?
     *
     * settings.xml is a Maven configuration file that
     * contains user-specific or installation-specific
     * settings.
     *
     * It is generally used to configure:
     *
     * - Local repository location.
     * - Remote repository credentials.
     * - Repository mirrors.
     * - Proxies.
     * - Active profiles.
     * - Other Maven execution settings.
     *
     * Unlike pom.xml, settings.xml is not normally
     * committed as part of an individual project.
     *
     *
     * 3. LOCATION OF settings.xml
     *
     * Maven supports two main settings files.
     *
     * USER SETTINGS:
     *
     * Linux:
     *
     * ~/.m2/settings.xml
     *
     * Windows:
     *
     * C:\Users\username\.m2\settings.xml
     *
     * INSTALLATION SETTINGS:
     *
     * <MAVEN_HOME>/conf/settings.xml
     *
     * User settings generally take precedence over
     * installation settings when both define the
     * same setting.
     *
     * Important:
     * Do not confuse settings.xml with pom.xml.
     *
     *
     * 4. pom.xml VS settings.xml
     *
     * pom.xml:
     *
     * - Defines project configuration.
     * - Declares dependencies.
     * - Configures plugins.
     * - Defines project properties.
     * - Defines build profiles.
     * - Defines project modules.
     *
     * settings.xml:
     *
     * - Defines user or installation configuration.
     * - Configures repository credentials.
     * - Configures mirrors.
     * - Configures proxies.
     * - Configures the local repository location.
     * - Activates settings profiles.
     *
     * Simple understanding:
     *
     * pom.xml = How this project should be built.
     *
     * settings.xml = How Maven should behave for this
     *                user or Maven installation.
     *
     *
     * 5. LOCAL REPOSITORY CONFIGURATION
     *
     * By default, Maven stores downloaded artifacts in:
     *
     * ~/.m2/repository
     *
     * You can change this location using settings.xml.
     *
     * Example:
     *
     * <settings>
     *     <localRepository>/home/user/maven-repository</localRepository>
     * </settings>
     *
     * This changes where Maven stores its local artifacts.
     *
     *
     * 6. WHAT IS A MAVEN PROFILE?
     *
     * A Maven profile allows you to define alternative
     * project or build configurations.
     *
     * Profiles are useful when the same project needs
     * different configurations for different environments
     * or build requirements.
     *
     * Examples:
     *
     * - Development.
     * - Testing.
     * - Staging.
     * - Production.
     *
     * A profile can modify or add configuration such as:
     *
     * - Properties.
     * - Dependencies.
     * - Plugin configuration.
     * - Build configuration.
     * - Repositories.
     *
     *
     * 7. DEFINING PROFILES IN pom.xml
     *
     * Example:
     *
     * <profiles>
     *
     *     <profile>
     *         <id>dev</id>
     *
     *         <properties>
     *             <environment.name>development</environment.name>
     *         </properties>
     *     </profile>
     *
     *     <profile>
     *         <id>prod</id>
     *
     *         <properties>
     *             <environment.name>production</environment.name>
     *         </properties>
     *     </profile>
     *
     * </profiles>
     *
     * Here:
     *
     * dev = Development profile.
     *
     * prod = Production profile.
     *
     * These profiles define different Maven properties.
     *
     *
     * 8. ACTIVATING A MAVEN PROFILE
     *
     * You can activate a profile using the command line.
     *
     * Activate dev:
     *
     * mvn package -Pdev
     *
     * Activate prod:
     *
     * mvn package -Pprod
     *
     * Activate multiple profiles:
     *
     * mvn package -Pdev,testing
     *
     * Deactivate a profile:
     *
     * mvn package -P!dev
     *
     * You can also configure profile activation
     * through settings.xml or profile activation rules.
     *
     *
     * 9. PROFILE ACTIVATION
     *
     * Maven profiles can be activated in different ways.
     *
     * Common activation methods:
     *
     * 1. Explicit command-line activation.
     * 2. Activation by property.
     * 3. Activation by JDK version.
     * 4. Activation by operating system.
     * 5. Activation by file presence or absence.
     * 6. Activation configured in settings.xml.
     *
     * Example:
     *
     * <profile>
     *     <id>development</id>
     *
     *     <activation>
     *         <property>
     *             <name>environment</name>
     *             <value>dev</value>
     *         </property>
     *     </activation>
     *
     *     <properties>
     *         <environment.name>development</environment.name>
     *     </properties>
     * </profile>
     *
     * Activate it using:
     *
     * mvn package -Denvironment=dev
     *
     *
     * 10. MAVEN PROFILES VS SPRING PROFILES
     *
     * These are two different concepts.
     *
     * MAVEN PROFILES:
     *
     * Control Maven build configuration.
     *
     * Example:
     *
     * mvn package -Pprod
     *
     * SPRING PROFILES:
     *
     * Control which Spring application configuration
     * is active when the application runs.
     *
     * Example:
     *
     * spring.profiles.active=prod
     *
     * Or:
     *
     * java -jar application.jar --spring.profiles.active=prod
     *
     * Important:
     *
     * Maven profiles operate during the build.
     *
     * Spring profiles operate inside the Spring application.
     *
     * They can be used together, but they are not interchangeable.
     *
     *
     * 11. MAVEN PROPERTIES
     *
     * Properties allow reusable values to be defined
     * in pom.xml.
     *
     * Example:
     *
     * <properties>
     *     <java.version>21</java.version>
     *     <project.encoding>UTF-8</project.encoding>
     * </properties>
     *
     * You can reference a property using:
     *
     * ${property.name}
     *
     * Example:
     *
     * <configuration>
     *     <release>${java.version}</release>
     * </configuration>
     *
     * Benefits:
     *
     * - Avoids repeated values.
     * - Makes configuration easier to maintain.
     * - Simplifies version and build configuration.
     *
     *
     * 12. COMMON MAVEN PROJECT PROPERTIES
     *
     * Some useful built-in Maven properties:
     *
     * ${project.groupId}
     * ${project.artifactId}
     * ${project.version}
     * ${project.packaging}
     * ${project.basedir}
     * ${project.build.directory}
     * ${project.build.outputDirectory}
     * ${project.build.sourceDirectory}
     * ${project.build.testSourceDirectory}
     *
     * Examples:
     *
     * ${project.basedir}
     *
     * Refers to the project directory.
     *
     * ${project.build.directory}
     *
     * Refers to the build output directory,
     * normally target.
     *
     *
     * 13. COMMAND-LINE PROPERTIES
     *
     * Maven supports passing properties through
     * command-line options.
     *
     * Example:
     *
     * mvn package -DskipTests=true
     *
     * Here:
     *
     * -D defines a system property for the Maven execution.
     *
     * Another example:
     *
     * mvn package -Dapplication.environment=dev
     *
     * A property can be referenced in the POM using:
     *
     * ${application.environment}
     *
     * Important:
     * A Maven property is not automatically the same
     * as a Spring Boot application property.
     *
     * Passing a Maven property does not automatically
     * configure your running Spring application.
     *
     *
     * 14. WHAT IS A MAVEN MIRROR?
     *
     * A mirror is an alternative repository endpoint
     * that Maven uses in place of another repository.
     *
     * Organizations commonly configure mirrors to route
     * dependency requests through an internal repository
     * manager.
     *
     * Example:
     *
     * <mirrors>
     *     <mirror>
     *         <id>company-mirror</id>
     *         <name>Company Maven Mirror</name>
     *         <url>https://repo.company.example/repository/maven-public/</url>
     *         <mirrorOf>*</mirrorOf>
     *     </mirror>
     * </mirrors>
     *
     * This is an illustrative URL.
     *
     * mirrorOf:
     *
     * Determines which repositories the mirror replaces.
     *
     * Common values:
     *
     * *       = All repositories.
     * central = Maven Central.
     *
     * A mirror can help an organization:
     *
     * - Control dependency downloads.
     * - Cache external artifacts.
     * - Provide internal artifacts.
     * - Apply repository security policies.
     *
     *
     * 15. WHAT IS A MAVEN PROXY?
     *
     * A proxy acts as an intermediary between Maven
     * and remote repositories.
     *
     * Some company networks require a proxy to access
     * the internet.
     *
     * Example:
     *
     * <proxies>
     *     <proxy>
     *         <id>company-proxy</id>
     *         <active>true</active>
     *         <protocol>http</protocol>
     *         <host>proxy.company.example</host>
     *         <port>8080</port>
     *     </proxy>
     * </proxies>
     *
     * This is an illustrative configuration.
     *
     * Actual proxy details must come from your network
     * or system administrator.
     *
     *
     * 16. REPOSITORY CREDENTIALS
     *
     * Sometimes dependencies or deployment repositories
     * require authentication.
     *
     * Maven supports server credentials in settings.xml.
     *
     * Example:
     *
     * <servers>
     *     <server>
     *         <id>company-repository</id>
     *         <username>your-username</username>
     *         <password>your-password</password>
     *     </server>
     * </servers>
     *
     * Important:
     *
     * The server id must match the repository or
     * distributionManagement repository id that
     * requires authentication.
     *
     * Security recommendations:
     *
     * - Do not commit passwords to Git.
     * - Do not put credentials directly in pom.xml.
     * - Use appropriate secret management.
     * - Consider Maven password encryption where applicable.
     *
     *
     * 17. WHAT IS MAVEN PASSWORD ENCRYPTION?
     *
     * Maven supports password encryption for credentials
     * stored in settings.xml.
     *
     * This helps avoid storing passwords as plain text.
     *
     * Maven uses a master password and encrypted
     * server credentials.
     *
     * Important:
     * Encrypted Maven credentials should not be treated
     * as a complete secret-management solution.
     *
     * Protect the master password and configuration files.
     *
     *
     * 18. WHAT IS A MULTI-MODULE MAVEN PROJECT?
     *
     * A multi-module project contains multiple Maven
     * projects organized under a parent project.
     *
     * It is useful when a large application is divided
     * into smaller modules.
     *
     * Example:
     *
     * company-application/
     * |
     * |---- pom.xml
     * |
     * |---- common/
     * |       |
     * |       |---- pom.xml
     * |
     * |---- user-service/
     * |       |
     * |       |---- pom.xml
     * |
     * |---- order-service/
     *         |
     *         |---- pom.xml
     *
     * Each module can have its own source code,
     * dependencies and build configuration.
     *
     *
     * 19. PARENT POM IN MULTI-MODULE PROJECTS
     *
     * The root POM commonly acts as an aggregator
     * and may also act as a parent.
     *
     * Example:
     *
     * <project>
     *
     *     <modelVersion>4.0.0</modelVersion>
     *
     *     <groupId>com.company</groupId>
     *     <artifactId>company-application</artifactId>
     *     <version>1.0.0</version>
     *
     *     <packaging>pom</packaging>
     *
     *     <modules>
     *         <module>common</module>
     *         <module>user-service</module>
     *         <module>order-service</module>
     *     </modules>
     *
     * </project>
     *
     * Important:
     *
     * The packaging type pom is commonly used for
     * aggregator and parent projects.
     *
     *
     * 20. WHAT IS MODULE AGGREGATION?
     *
     * Aggregation means a root Maven project lists
     * its child modules.
     *
     * Example:
     *
     * <modules>
     *     <module>common</module>
     *     <module>user-service</module>
     *     <module>order-service</module>
     * </modules>
     *
     * This allows Maven to build the listed modules
     * together from the aggregator project.
     *
     *
     * 21. WHAT IS INHERITANCE?
     *
     * Inheritance means a child POM receives applicable
     * configuration from its parent POM.
     *
     * A child POM references its parent:
     *
     * <parent>
     *     <groupId>com.company</groupId>
     *     <artifactId>company-application</artifactId>
     *     <version>1.0.0</version>
     * </parent>
     *
     * A parent can provide:
     *
     * - Properties.
     * - Dependency management.
     * - Plugin management.
     * - Build configuration.
     * - Other inherited POM configuration.
     *
     * Important:
     *
     * Aggregation and inheritance are different concepts.
     *
     * A project can aggregate modules without being
     * their parent.
     *
     * A project can be a parent without aggregating
     * its children.
     *
     * A root POM often performs both roles.
     *
     *
     * 22. MULTI-MODULE BUILD ORDER
     *
     * Maven builds modules in an order that respects
     * their dependency relationships.
     *
     * Example:
     *
     * common
     *   |
     *   v
     * user-service
     *
     * If user-service depends on common, Maven builds
     * common before user-service in the reactor build.
     *
     * Maven's reactor is responsible for coordinating
     * multi-module builds.
     *
     *
     * 23. WHAT IS THE MAVEN REACTOR?
     *
     * The Maven reactor is the mechanism that collects
     * and builds projects in a multi-module build.
     *
     * It:
     *
     * - Identifies projects in the build.
     * - Determines their relationships.
     * - Calculates a suitable build order.
     * - Coordinates their build execution.
     *
     * Useful commands:
     *
     * mvn clean install
     *
     * Builds the projects in the reactor.
     *
     * mvn -pl user-service package
     *
     * Builds a selected project.
     *
     * mvn -pl user-service -am package
     *
     * Builds the selected project and its required
     * reactor dependencies.
     *
     * mvn -pl user-service -amd package
     *
     * Also builds reactor projects that depend on
     * the selected project.
     *
     * -pl = Select projects.
     *
     * -am = Also make required projects.
     *
     * -amd = Also make dependent projects.
     *
     *
     * 24. MAVEN WRAPPER
     *
     * Maven Wrapper allows a project to use a specified
     * Maven distribution without requiring every developer
     * to install that Maven version manually.
     *
     * Common wrapper files:
     *
     * mvnw
     * mvnw.cmd
     * .mvn/wrapper/maven-wrapper.properties
     *
     * Linux:
     *
     * ./mvnw clean install
     *
     * Windows:
     *
     * mvnw.cmd clean install
     *
     * Benefits:
     *
     * - Consistent Maven version across developers.
     * - Easier project setup.
     * - More reproducible builds.
     * - Useful in CI/CD pipelines.
     *
     * Important:
     * Maven Wrapper still requires a suitable Java runtime
     * to run Maven.
     *
     *
     * 25. MAVEN ENVIRONMENT VARIABLES
     *
     * Common environment variables:
     *
     * JAVA_HOME:
     * Points to the JDK installation directory.
     *
     * MAVEN_HOME:
     * Points to the Maven installation directory.
     *
     * PATH:
     * Contains directories searched by the operating
     * system when executing commands.
     *
     * Example Linux checks:
     *
     * java -version
     *
     * mvn -version
     *
     * echo $JAVA_HOME
     *
     * echo $MAVEN_HOME
     *
     * Important:
     * MAVEN_HOME is not always required when using
     * Maven Wrapper.
     *
     *
     * 26. MAVEN SETTINGS AND ENVIRONMENT VARIABLES
     *
     * Maven can use environment variables inside
     * configuration values.
     *
     * Example:
     *
     * <localRepository>${user.home}/.m2/repository</localRepository>
     *
     * You can also use environment variables through
     * Maven's environment property syntax.
     *
     * Example:
     *
     * ${env.JAVA_HOME}
     *
     * This references the JAVA_HOME environment variable.
     *
     *
     * 27. BUILD PROFILES VS BUILD ENVIRONMENTS
     *
     * Maven profiles can change build configuration,
     * but they are not a replacement for secure runtime
     * environment configuration.
     *
     * Avoid putting environment-specific secrets such as:
     *
     * - Database passwords.
     * - API keys.
     * - Access tokens.
     *
     * directly into committed POM files.
     *
     * Prefer environment variables or a suitable
     * secret-management system.
     *
     *
     * 28. WHAT IS CI/CD IN RELATION TO MAVEN?
     *
     * CI/CD stands for:
     *
     * CI = Continuous Integration.
     *
     * CD = Continuous Delivery or Continuous Deployment.
     *
     * Maven is commonly used inside CI/CD pipelines
     * to build and test Java applications.
     *
     * Example pipeline:
     *
     * Developer pushes code
     *          |
     *          v
     * Source control
     *          |
     *          v
     * CI pipeline starts
     *          |
     *          v
     * Maven clean verify
     *          |
     *          v
     * Tests and verification
     *          |
     *          v
     * Package application
     *          |
     *          v
     * Publish artifact or deploy application
     *
     * Maven performs the build-related tasks.
     *
     * A CI/CD platform coordinates the overall pipeline.
     *
     *
     * 29. MAVEN IN CI/CD PLATFORMS
     *
     * Maven can be used with:
     *
     * - Jenkins.
     * - GitHub Actions.
     * - GitLab CI/CD.
     * - Azure DevOps.
     *
     * Example command in a pipeline:
     *
     * ./mvnw clean verify
     *
     * Using the wrapper helps ensure that the project
     * uses its configured Maven version.
     *
     *
     * 30. REPRODUCIBLE BUILDS
     *
     * A reproducible build aims to produce the same
     * build output from the same source and build
     * configuration under controlled conditions.
     *
     * Good practices:
     *
     * - Use fixed dependency versions or managed versions.
     * - Use Maven Wrapper.
     * - Pin important plugin versions.
     * - Use a controlled JDK version.
     * - Avoid dynamic dependency versions.
     * - Keep build configuration consistent.
     * - Use trusted repositories.
     *
     * Reproducibility also depends on plugins,
     * build environment, timestamps and other inputs.
     *
     *
     * 31. MAVEN ENFORCER PLUGIN
     *
     * The Maven Enforcer Plugin can enforce project
     * and build rules.
     *
     * Examples of rules:
     *
     * - Require a particular Maven version.
     * - Require a particular Java version.
     * - Ban certain dependencies.
     * - Detect dependency convergence problems.
     *
     * It helps teams maintain consistent build standards.
     *
     *
     * 32. MAVEN AND SECURITY
     *
     * Dependencies can contain security vulnerabilities.
     *
     * Good practices:
     *
     * - Use trusted repositories.
     * - Keep dependencies updated.
     * - Review dependency changes.
     * - Avoid unnecessary dependencies.
     * - Monitor known vulnerabilities.
     * - Protect repository credentials.
     * - Avoid committing secrets.
     * - Use organization-approved dependency sources.
     *
     * Maven resolves dependencies, but dependency
     * resolution alone does not guarantee that
     * dependencies are secure.
     *
     *
     * 33. USEFUL ADVANCED MAVEN COMMANDS
     *
     * Display Maven version:
     *
     * mvn -version
     *
     * Display effective POM:
     *
     * mvn help:effective-pom
     *
     * Display active profiles:
     *
     * mvn help:active-profiles
     *
     * Display project information:
     *
     * mvn help:describe -Dcmd=compile
     *
     * Display plugin information:
     *
     * mvn help:describe
     *     -Dplugin=org.apache.maven.plugins:maven-compiler-plugin
     *
     * Force dependency updates:
     *
     * mvn -U clean install
     *
     * Run Maven in offline mode:
     *
     * mvn -o clean install
     *
     * Run Maven with debug output:
     *
     * mvn -X clean install
     *
     * Run Maven quietly:
     *
     * mvn -q clean install
     *
     * Important:
     *
     * -U forces checks for updated snapshots and missing
     *   releases; it does not mean every cached release
     *   is always downloaded again.
     *
     * -o runs Maven in offline mode and uses locally
     *   available artifacts.
     *
     * -X enables detailed debug output.
     *
     *
     * 34. COMMON MAVEN CONFIGURATION MISTAKES
     *
     * 1. Confusing pom.xml with settings.xml.
     *
     * 2. Committing repository passwords to Git.
     *
     * 3. Confusing Maven profiles with Spring profiles.
     *
     * 4. Assuming a Maven profile automatically activates
     *    a Spring profile.
     *
     * 5. Confusing aggregation with inheritance.
     *
     * 6. Assuming every multi-module project must have
     *    the same parent and aggregator.
     *
     * 7. Using inconsistent Maven versions across developers.
     *
     * 8. Hardcoding environment-specific secrets.
     *
     * 9. Putting unnecessary configuration into the POM.
     *
     * 10. Ignoring dependency and plugin version consistency.
     *
     *
     * 35. INTERVIEW QUESTIONS
     *
     * 1. What is settings.xml?
     * 2. What is the difference between pom.xml and settings.xml?
     * 3. Where is settings.xml located?
     * 4. What is a Maven profile?
     * 5. How do you activate a Maven profile?
     * 6. What is the difference between Maven and Spring profiles?
     * 7. What is a Maven mirror?
     * 8. What is mirrorOf?
     * 9. What is a Maven proxy?
     * 10. Where should repository credentials be stored?
     * 11. What is a multi-module Maven project?
     * 12. What is the difference between aggregation and inheritance?
     * 13. What is the Maven reactor?
     * 14. What is the purpose of Maven Wrapper?
     * 15. What is the purpose of JAVA_HOME?
     * 16. What is the purpose of MAVEN_HOME?
     * 17. What is a reproducible build?
     * 18. What is the Maven Enforcer Plugin?
     * 19. How is Maven used in CI/CD?
     * 20. What is the purpose of mvn help:effective-pom?
     *
     *
     * 36. QUICK REVISION
     *
     * settings.xml:
     * User-level or installation-level Maven configuration.
     *
     * pom.xml:
     * Project-level Maven configuration.
     *
     * Maven profile:
     * Alternative build configuration.
     *
     * Spring profile:
     * Runtime application configuration.
     *
     * Properties:
     * Reusable configuration values.
     *
     * Mirror:
     * Alternative repository endpoint.
     *
     * Proxy:
     * Intermediary for network access.
     *
     * Multi-module project:
     * A Maven project containing multiple modules.
     *
     * Aggregation:
     * A root project listing modules to build together.
     *
     * Inheritance:
     * A child project receiving applicable parent configuration.
     *
     * Reactor:
     * Coordinates multi-module builds and build order.
     *
     * Maven Wrapper:
     * Provides a project-specific Maven distribution.
     *
     * CI/CD:
     * Automates integration, building, testing and delivery.
     *
     * Reproducible build:
     * A build that aims to produce consistent output
     * from controlled inputs.
     *
     * =================================================================
     *            END OF MAVEN CONFIGURATION AND ADVANCED CONCEPTS
     * =================================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Maven Configuration and Advanced Concepts , Happy Learning ..!");
    }
}
