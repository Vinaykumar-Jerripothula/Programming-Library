
public class DependenciesAndDependencyManagement {

    /*
     * ================================================================
     *             DEPENDENCIES AND DEPENDENCY MANAGEMENT
     * ================================================================
     *
     * 1. WHAT IS A DEPENDENCY?
     *
     * A dependency is an external library or module that
     * a project requires to perform certain functionality.
     *
     * Imagine building a Spring Boot application.
     *
     * You need:
     *
     * - Spring Web for REST APIs.
     * - Hibernate for ORM.
     * - MySQL Connector to connect to MySQL.
     * - Jackson for JSON processing.
     * - JUnit for testing.
     *
     * Instead of implementing all these features yourself,
     * you use existing libraries.
     *
     * These libraries become dependencies of your project.
     *
     *
     * 2. WHAT IS DEPENDENCY MANAGEMENT?
     *
     * Dependency management is the process of declaring,
     * resolving, organizing and controlling the libraries
     * required by a project.
     *
     * Maven helps manage:
     *
     * - Dependency coordinates.
     * - Dependency versions.
     * - Dependency scopes.
     * - Transitive dependencies.
     * - Dependency conflicts.
     * - Exclusions.
     * - Optional dependencies.
     *
     *
     * 3. HOW TO DECLARE A DEPENDENCY
     *
     * Dependencies are declared inside the dependencies
     * element of pom.xml.
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
     * The version is illustrative.
     *
     * Explanation:
     *
     * groupId:
     * Identifies the group that publishes the artifact.
     *
     * artifactId:
     * Identifies the dependency.
     *
     * version:
     * Specifies the requested dependency version.
     *
     * These are the fundamental Maven coordinates.
     *
     *
     * 4. HOW MAVEN RESOLVES DEPENDENCIES
     *
     * Suppose your project declares a dependency.
     *
     * Maven performs dependency resolution.
     *
     * Simplified process:
     *
     * 1. Reads the dependency declaration.
     * 2. Determines the required coordinates.
     * 3. Checks the local repository.
     * 4. If needed, searches configured remote repositories.
     * 5. Retrieves the required artifact and metadata.
     * 6. Resolves its required transitive dependencies.
     * 7. Makes resolved dependencies available to the build.
     *
     * Important:
     * Maven may use cached metadata and artifacts.
     * It does not download every dependency again on
     * every build.
     *
     *
     * 5. MAVEN LOCAL REPOSITORY
     *
     * The local repository stores downloaded artifacts
     * on your machine.
     *
     * Default location on Linux:
     *
     * ~/.m2/repository
     *
     * Example:
     *
     * ~/.m2/repository/org/springframework/boot/
     *
     * Benefits:
     *
     * - Reuses downloaded dependencies.
     * - Reduces unnecessary network requests.
     * - Stores locally installed project artifacts.
     * - Stores downloaded Maven plugins.
     *
     * You can inspect the local repository to see
     * downloaded dependencies.
     *
     *
     * 6. MAVEN CENTRAL REPOSITORY
     *
     * Maven Central is a public repository that hosts
     * many Java libraries and Maven artifacts.
     *
     * Website:
     *
     * https://central.sonatype.com/
     *
     * Maven can retrieve dependencies from Maven Central
     * when they are not available locally and the repository
     * is configured for resolution.
     *
     *
     * 7. REMOTE REPOSITORIES
     *
     * A remote repository is a repository accessed over
     * a network.
     *
     * Examples:
     *
     * - Maven Central.
     * - Company artifact repositories.
     * - Third-party repositories.
     *
     * Organizations may use repository managers such as:
     *
     * - Sonatype Nexus Repository.
     * - JFrog Artifactory.
     *
     * These can host internal libraries and proxy external
     * repositories.
     *
     *
     * 8. DEPENDENCY SCOPE
     *
     * Dependency scope determines the classpaths in which
     * a dependency is available.
     *
     * Maven provides six commonly encountered scopes:
     *
     * 1. compile
     * 2. provided
     * 3. runtime
     * 4. test
     * 5. system
     * 6. import
     *
     * The import scope is used only for dependencies of
     * type pom inside dependencyManagement.
     *
     *
     * 9. COMPILE SCOPE
     *
     * compile is the default dependency scope.
     *
     * Example:
     *
     * <dependency>
     *     <groupId>org.springframework</groupId>
     *     <artifactId>spring-context</artifactId>
     *     <version>6.2.0</version>
     *     <scope>compile</scope>
     * </dependency>
     *
     * A compile dependency is available during:
     *
     * - Main compilation.
     * - Test compilation.
     * - Test execution.
     * - Runtime.
     *
     * It is also transitive by default.
     *
     * Use it when the application needs the dependency
     * for its normal functionality.
     *
     *
     * 10. PROVIDED SCOPE
     *
     * provided means the dependency is expected to be
     * supplied by the runtime environment.
     *
     * Example:
     *
     * <dependency>
     *     <groupId>jakarta.servlet</groupId>
     *     <artifactId>jakarta.servlet-api</artifactId>
     *     <version>6.1.0</version>
     *     <scope>provided</scope>
     * </dependency>
     *
     * A provided dependency is available during:
     *
     * - Main compilation.
     * - Test compilation.
     * - Test execution.
     *
     * It is not normally included on the runtime classpath
     * of the application in the same way as compile dependencies.
     *
     * It is also not transitive.
     *
     * Common example:
     *
     * Servlet API when deploying a WAR to a servlet container
     * that provides the Servlet API.
     *
     *
     * 11. RUNTIME SCOPE
     *
     * runtime dependencies are needed when the application
     * runs but are not required to compile the application's
     * source code.
     *
     * Example:
     *
     * <dependency>
     *     <groupId>com.mysql</groupId>
     *     <artifactId>mysql-connector-j</artifactId>
     *     <version>9.2.0</version>
     *     <scope>runtime</scope>
     * </dependency>
     *
     * A runtime dependency is available during:
     *
     * - Test compilation.
     * - Test execution.
     * - Runtime.
     *
     * It is not available during main source compilation.
     *
     * Common example:
     * A JDBC driver loaded by the application at runtime.
     *
     *
     * 12. TEST SCOPE
     *
     * test dependencies are required only for testing.
     *
     * Example:
     *
     * <dependency>
     *     <groupId>org.junit.jupiter</groupId>
     *     <artifactId>junit-jupiter</artifactId>
     *     <version>5.11.0</version>
     *     <scope>test</scope>
     * </dependency>
     *
     * A test dependency is available during:
     *
     * - Test compilation.
     * - Test execution.
     *
     * It is not available during main compilation or
     * normal application runtime.
     *
     * Common examples:
     *
     * - JUnit.
     * - Mockito.
     * - Spring Boot Test.
     *
     *
     * 13. SYSTEM SCOPE
     *
     * system scope is similar to provided scope, but the
     * dependency is referenced using an explicit local
     * filesystem path.
     *
     * Example:
     *
     * <dependency>
     *     <groupId>com.example</groupId>
     *     <artifactId>custom-library</artifactId>
     *     <version>1.0</version>
     *     <scope>system</scope>
     *     <systemPath>${project.basedir}/lib/custom.jar</systemPath>
     * </dependency>
     *
     * Important:
     *
     * - systemPath must point to a local file.
     * - The dependency is not resolved from a repository.
     * - It is not transitive.
     * - It makes builds less portable.
     *
     * Avoid system scope in normal projects.
     *
     * Prefer publishing the library to a repository or
     * installing it into an appropriate repository.
     *
     *
     * 14. IMPORT SCOPE
     *
     * import scope is used for a dependency of type pom
     * inside dependencyManagement.
     *
     * It allows a BOM's dependency management entries
     * to be imported into the current project's
     * dependency management.
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
     * import scope is not a normal dependency scope
     * for application libraries.
     *
     *
     * 15. DEPENDENCY SCOPE COMPARISON
     *
     * Scope       Main Compile   Test   Runtime
     *
     * compile         Yes        Yes      Yes
     * provided        Yes        Yes      No*
     * runtime         No         Yes      Yes
     * test            No         Yes      No
     * system          Yes        Yes      No*
     * import          N/A        N/A      N/A
     *
     * * provided and system dependencies are not normally
     * included in the project's runtime classpath as
     * ordinary compile dependencies are.
     *
     * Their treatment can also depend on packaging and
     * deployment configuration.
     *
     *
     * 16. TRANSITIVE DEPENDENCIES
     *
     * A transitive dependency is a dependency required
     * by another dependency.
     *
     * Example:
     *
     * Your project
     *      |
     *      v
     * Library A
     *      |
     *      v
     * Library B
     *      |
     *      v
     * Library C
     *
     * Your project directly declares Library A.
     *
     * Library A requires Library B.
     *
     * Library B requires Library C.
     *
     * Maven can resolve these dependencies automatically.
     *
     * Therefore:
     *
     * Library A = Direct dependency.
     *
     * Library B and Library C = Transitive dependencies.
     *
     * Benefits:
     *
     * - Avoids manually declaring every required library.
     * - Simplifies project configuration.
     *
     *
     * 17. DIRECT VS TRANSITIVE DEPENDENCIES
     *
     * Direct dependency:
     *
     * Explicitly declared in your project's POM.
     *
     * Transitive dependency:
     *
     * Required by another dependency.
     *
     * Example:
     *
     * Your application directly declares Spring Web.
     *
     * Spring Web requires other libraries.
     *
     * Those libraries may be resolved transitively.
     *
     * Important:
     * You should explicitly declare dependencies that
     * your application directly uses, even if they are
     * currently available transitively.
     *
     * This makes your project configuration more reliable.
     *
     *
     * 18. DEPENDENCY EXCLUSIONS
     *
     * Sometimes a dependency brings another library
     * that your project does not want.
     *
     * Maven supports exclusions to prevent a particular
     * transitive dependency from being included through
     * a specific dependency path.
     *
     * Example:
     *
     * <dependency>
     *     <groupId>com.example</groupId>
     *     <artifactId>library-a</artifactId>
     *     <version>1.0.0</version>
     *
     *     <exclusions>
     *
     *         <exclusion>
     *             <groupId>com.example</groupId>
     *             <artifactId>library-b</artifactId>
     *         </exclusion>
     *
     *     </exclusions>
     * </dependency>
     *
     * Meaning:
     *
     * Library A requires Library B.
     *
     * The exclusion prevents Library B from being
     * included through this dependency declaration.
     *
     * Important:
     * An exclusion applies to the dependency path where
     * it is declared. Another dependency may still bring
     * in the same artifact.
     *
     *
     * 19. OPTIONAL DEPENDENCIES
     *
     * A dependency can be marked optional.
     *
     * Example:
     *
     * <dependency>
     *     <groupId>com.example</groupId>
     *     <artifactId>optional-library</artifactId>
     *     <version>1.0.0</version>
     *     <optional>true</optional>
     * </dependency>
     *
     * An optional dependency is used by the current
     * project but is not propagated transitively to
     * downstream consumers as a normal dependency.
     *
     * Example:
     *
     * Library A uses Library B internally.
     *
     * Library A marks Library B as optional.
     *
     * A project depending on Library A does not
     * automatically receive Library B as a transitive
     * dependency through that declaration.
     *
     * Important:
     * Optional does not mean the current project
     * does not use the dependency.
     *
     *
     * 20. DEPENDENCY CONFLICTS
     *
     * A dependency conflict occurs when different
     * dependency paths request different versions
     * of the same artifact.
     *
     * Example:
     *
     * Your project
     *      |
     *      |---- Library A
     *      |        |
     *      |        |---- Common Library 1.0
     *      |
     *      |---- Library B
     *               |
     *               |---- Common Library 2.0
     *
     * Both libraries require the same artifact,
     * but at different versions.
     *
     * Maven must select a version for the dependency graph.
     *
     *
     * 21. DEPENDENCY MEDIATION
     *
     * Maven uses dependency mediation to choose between
     * competing versions of the same artifact.
     *
     * The general rule is:
     *
     * Nearest definition wins.
     *
     * If two versions are at the same depth,
     * the first declaration encountered wins.
     *
     * Example:
     *
     * Your project
     *      |
     *      |---- Library A
     *      |        |
     *      |        |---- Common Library 1.0
     *      |
     *      |---- Library B
     *               |
     *               |---- Common Library 2.0
     *
     * Both versions are at the same depth.
     *
     * The dependency declaration order can determine
     * which version Maven selects.
     *
     * Important:
     * dependencyManagement can be used to control
     * dependency versions explicitly.
     *
     *
     * 22. DEPENDENCY TREE
     *
     * The dependency tree displays direct and transitive
     * dependencies in a hierarchical structure.
     *
     * Command:
     *
     * mvn dependency:tree
     *
     * Useful when:
     *
     * - A dependency is unexpectedly present.
     * - Multiple versions are requested.
     * - A transitive dependency causes an issue.
     * - You need to investigate dependency conflicts.
     *
     * Example conceptual output:
     *
     * com.company:employee-service:jar:1.0
     * |
     * +--- org.springframework:spring-core:jar:6.x
     * |
     * +--- com.fasterxml.jackson.core:jackson-databind:jar:2.x
     * |
     * +--- org.junit.jupiter:junit-jupiter:jar:5.x:test
     *
     *
     * 23. DEPENDENCY MANAGEMENT
     *
     * dependencyManagement centralizes dependency
     * configuration, especially dependency versions.
     *
     * Example:
     *
     * <dependencyManagement>
     *     <dependencies>
     *
     *         <dependency>
     *             <groupId>org.junit.jupiter</groupId>
     *             <artifactId>junit-jupiter</artifactId>
     *             <version>5.11.0</version>
     *         </dependency>
     *
     *     </dependencies>
     * </dependencyManagement>
     *
     * A child or current project's dependencies section
     * can then declare:
     *
     * <dependency>
     *     <groupId>org.junit.jupiter</groupId>
     *     <artifactId>junit-jupiter</artifactId>
     * </dependency>
     *
     * The managed version is applied.
     *
     * Important:
     * dependencyManagement does not add the dependency
     * to the project by itself.
     *
     *
     * 24. BOM - BILL OF MATERIALS
     *
     * BOM stands for Bill of Materials.
     *
     * In Maven, a BOM is a POM that provides a coordinated
     * set of dependency versions through dependencyManagement.
     *
     * It helps maintain compatible dependency versions.
     *
     * Example:
     *
     * Spring Boot provides a dependency management BOM.
     *
     * When using the Spring Boot parent POM, its dependency
     * management is inherited.
     *
     * Alternatively, import the BOM:
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
     * Benefits:
     *
     * - Centralized version management.
     * - Compatible dependency versions.
     * - Less repetitive configuration.
     * - Easier upgrades.
     *
     *
     * 25. SPRING BOOT DEPENDENCY MANAGEMENT
     *
     * Spring Boot provides curated dependency versions.
     *
     * Consider:
     *
     * <dependency>
     *     <groupId>org.springframework.boot</groupId>
     *     <artifactId>spring-boot-starter-data-jpa</artifactId>
     * </dependency>
     *
     * When the Spring Boot parent or BOM manages its version,
     * you generally do not need to specify a version here.
     *
     * Spring Boot manages compatible versions of many
     * dependencies used by its ecosystem.
     *
     * Important:
     * Do not randomly override managed dependency versions.
     *
     * Override versions only when you understand compatibility
     * and have a specific reason.
     *
     *
     * 26. DEPENDENCY VERSION CONVENTIONS
     *
     * Common version examples:
     *
     * 1.0.0
     * 2.1.0
     * 3.0.0
     * 1.0.0-SNAPSHOT
     *
     * Maven supports version ranges and other version
     * specifications, but fixed versions are generally
     * easier to reproduce and maintain.
     *
     * Avoid dynamic version ranges in production builds
     * unless there is a deliberate reason.
     *
     *
     * 27. CLASSIFIER AND TYPE
     *
     * A dependency can have additional coordinates
     * and artifact details.
     *
     * type:
     * Identifies the artifact type.
     *
     * classifier:
     * Distinguishes artifacts built from the same project
     * with different purposes or contents.
     *
     * Example:
     *
     * <dependency>
     *     <groupId>com.example</groupId>
     *     <artifactId>library</artifactId>
     *     <version>1.0.0</version>
     *     <type>jar</type>
     *     <classifier>tests</classifier>
     * </dependency>
     *
     * The classifier identifies a particular classified
     * artifact.
     *
     * Important:
     * The default dependency type is jar.
     *
     * Maven artifact coordinates can contain more than
     * just groupId, artifactId and version.
     *
     *
     * 28. DEPENDENCY SCOPE VS OPTIONAL
     *
     * Scope determines where a dependency is available
     * in the build and runtime classpaths.
     *
     * Optional determines whether the dependency is
     * propagated to downstream consumers.
     *
     * They solve different problems.
     *
     * Example:
     *
     * A library may be declared with compile scope
     * and optional=true.
     *
     * The current project can use it, but downstream
     * consumers do not automatically receive it
     * through that dependency declaration.
     *
     *
     * 29. USEFUL MAVEN DEPENDENCY COMMANDS
     *
     * Display dependency tree:
     *
     * mvn dependency:tree
     *
     * Display dependency tree with verbose information:
     *
     * mvn dependency:tree -Dverbose
     *
     * Display dependency tree for a particular artifact:
     *
     * mvn dependency:tree -Dincludes=org.springframework
     *
     * Display dependency information:
     *
     * mvn dependency:list
     *
     * Analyze declared and used dependencies:
     *
     * mvn dependency:analyze
     *
     * Copy dependencies into a directory:
     *
     * mvn dependency:copy-dependencies
     *
     * These commands are provided by the Maven Dependency Plugin.
     *
     *
     * 30. COMMON DEPENDENCY MANAGEMENT MISTAKES
     *
     * 1. Adding unnecessary dependencies.
     *
     * 2. Declaring a dependency without understanding its purpose.
     *
     * 3. Using incompatible library versions.
     *
     * 4. Randomly overriding Spring Boot managed versions.
     *
     * 5. Confusing direct and transitive dependencies.
     *
     * 6. Assuming optional means unused by the current project.
     *
     * 7. Assuming dependencyManagement adds dependencies
     *    automatically.
     *
     * 8. Using system scope unnecessarily.
     *
     * 9. Ignoring dependency conflicts.
     *
     * 10. Failing to inspect dependency:tree during troubleshooting.
     *
     *
     * 31. INTERVIEW QUESTIONS
     *
     * 1. What is a Maven dependency?
     * 2. What is dependency management?
     * 3. What are the Maven dependency scopes?
     * 4. What is the default dependency scope?
     * 5. What is a transitive dependency?
     * 6. What is the difference between direct and transitive
     *    dependencies?
     * 7. What is dependency mediation?
     * 8. What does nearest definition wins mean?
     * 9. What is a dependency exclusion?
     * 10. What is an optional dependency?
     * 11. What is the difference between optional and scope?
     * 12. What is dependencyManagement?
     * 13. What is a BOM?
     * 14. What is import scope?
     * 15. How does Spring Boot manage dependency versions?
     * 16. How do you identify dependency conflicts?
     * 17. What is the purpose of mvn dependency:tree?
     * 18. What is the difference between provided and runtime?
     * 19. What is the difference between test and compile?
     * 20. Why should system scope generally be avoided?
     *
     *
     * 32. QUICK REVISION
     *
     * Dependency:
     * An external library or module required by a project.
     *
     * Dependency management:
     * Controls dependency configuration and resolution.
     *
     * Compile:
     * Available in main compilation, testing and runtime.
     *
     * Provided:
     * Available for compilation and testing; supplied by
     * the runtime environment.
     *
     * Runtime:
     * Available during testing and runtime, not main compilation.
     *
     * Test:
     * Available only for test compilation and execution.
     *
     * System:
     * Uses an explicit local filesystem dependency path.
     *
     * Import:
     * Imports a BOM's dependency management entries.
     *
     * Transitive dependency:
     * A dependency required by another dependency.
     *
     * Exclusion:
     * Prevents a transitive dependency from being included
     * through a particular dependency path.
     *
     * Optional:
     * Prevents normal transitive propagation to downstream
     * consumers.
     *
     * Dependency mediation:
     * Selects a version when dependency paths request
     * different versions.
     *
     * BOM:
     * Bill of Materials; coordinates dependency versions.
     *
     * Dependency tree:
     * Shows the dependency hierarchy.
     *
     * ================================================================
     *          END OF DEPENDENCIES AND DEPENDENCY MANAGEMENT
     * ================================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Dependencies and Dependency Management , Happy Learning ..!");
    }
}
