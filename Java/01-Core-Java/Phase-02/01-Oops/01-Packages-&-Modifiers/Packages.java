
public class Packages {

    

    /*
     * ================================================================
     *                         JAVA PACKAGES
     * ================================================================
     *
     * 1. INTRODUCTION TO PACKAGES
     *
     * A package in Java is a namespace that groups related classes,
     * interfaces, enums, records and annotations.
     *
     * It provides a structured way to organize Java code, avoid naming
     * conflicts, control accessibility and improve maintainability.
     *
     * Example:
     *
     *     java.util
     *     java.io
     *     java.sql
     *     com.company.project
     *
     * In the following example, Employee belongs to the package
     * com.company.hr:
     *
     *     package com.company.hr;
     *
     *     public class Employee {
     *     }
     *
     * The fully qualified name (FQN) of Employee is:
     *
     *     com.company.hr.Employee
     *
     * IMPORTANT PURPOSES:
     *
     * 1. Organization:
     *    Groups logically related types together.
     *
     * 2. Namespace management:
     *    Allows different packages to contain types with the same
     *    simple name.
     *
     *    com.company.hr.Employee
     *    com.company.sales.Employee
     *
     *    Both classes can exist because their fully qualified names
     *    are different.
     *
     * 3. Access control:
     *    Package membership affects package-private and protected
     *    accessibility.
     *
     * 4. Reusability:
     *    Types can be made available to other packages through
     *    appropriate access and imports.
     *
     * 5. Maintainability:
     *    Separates application functionality into meaningful units.
     *
     *
     * ================================================================
     * 2. TYPES OF PACKAGES
     * ================================================================
     *
     * Packages are commonly classified into:
     *
     * A. Java API packages
     * B. User-defined packages
     * C. Unnamed (default) package
     *
     * A. JAVA API PACKAGES:
     *
     * Java provides standard API packages containing reusable classes
     * and interfaces.
     *
     * Examples:
     *
     *     java.lang
     *     java.util
     *     java.io
     *     java.net
     *     java.time
     *     java.sql
     *     java.nio
     *
     * These packages are part of the Java platform APIs.
     *
     * B. USER-DEFINED PACKAGES:
     *
     * Developers create packages to organize application code.
     *
     * Example:
     *
     *     package com.company.employee;
     *
     *     public class Employee {
     *     }
     *
     * C. UNNAMED PACKAGE:
     *
     * If a source file does not contain a package declaration, its
     * top-level types belong to an unnamed package.
     *
     * Example:
     *
     *     public class Demo {
     *     }
     *
     * IMPORTANT:
     *
     * - The unnamed package is sometimes called the default package.
     * - It is suitable mainly for small experiments and simple
     *   programs.
     * - Types in named packages cannot import types from the unnamed
     *   package.
     * - Avoid using it in real-world application development.
     *
     *
     * ================================================================
     * 3. CREATING A USER-DEFINED PACKAGE
     * ================================================================
     *
     * A package is declared using the package keyword.
     *
     * SYNTAX:
     *
     *     package packageName;
     *
     * EXAMPLE:
     *
     *     package com.company.hr;
     *
     *     public class Employee {
     *         public void display() {
     *             System.out.println("Employee details");
     *         }
     *     }
     *
     * PACKAGE DECLARATION RULES:
     *
     * 1. A package declaration, when present, must appear before import
     *    declarations and top-level type declarations.
     *
     * 2. Comments and whitespace may appear before it.
     *
     * 3. A source file can declare only one package.
     *
     * 4. A source file cannot declare multiple package declarations.
     *
     * 5. Package names are case-sensitive.
     *
     * 6. Package names conventionally use lowercase letters.
     *
     * 7. Package names must follow Java identifier rules and cannot
     *    use Java keywords as ordinary identifier components.
     *
     * 8. A package declaration does not itself create a directory.
     *    The compiler and build tools use the package name when
     *    organizing and locating compiled classes.
     *
     * DIRECTORY STRUCTURE:
     *
     * For package:
     *
     *     com.company.hr
     *
     * The conventional directory structure is:
     *
     *     com/
     *       company/
     *         hr/
     *           Employee.java
     *
     * Each package-name component corresponds to a directory level
     * under a source or classpath root.
     *
     *
     * ================================================================
     * 4. IMPORT STATEMENT
     * ================================================================
     *
     * The import statement allows a type or static member to be
     * referenced using a shorter name instead of its fully qualified
     * name.
     *
     * IMPORTANT:
     *
     * Importing a type does not copy, move or load its class.
     * Imports primarily help the compiler resolve names.
     *
     * TYPES OF IMPORTS:
     *
     * A. Single-type import
     * B. Type-import-on-demand (wildcard import)
     * C. Static single import
     * D. Static-import-on-demand
     *
     *
     * A. SINGLE-TYPE IMPORT:
     *
     * Imports one accessible type.
     *
     * SYNTAX:
     *
     *     import packageName.TypeName;
     *
     * EXAMPLE:
     *
     *     import java.util.ArrayList;
     *
     *     public class Demo {
     *         public static void main(String[] args) {
     *             ArrayList<String> names = new ArrayList<>();
     *         }
     *     }
     *
     * Without the import:
     *
     *     java.util.ArrayList<String> names =
     *             new java.util.ArrayList<>();
     *
     *
     * B. WILDCARD IMPORT:
     *
     * Imports accessible types declared directly in a package.
     *
     * SYNTAX:
     *
     *     import packageName.*;
     *
     * EXAMPLE:
     *
     *     import java.util.*;
     *
     * This allows the use of types such as:
     *
     *     ArrayList
     *     HashMap
     *     HashSet
     *     Scanner
     *
     * IMPORTANT RULES:
     *
     * 1. A wildcard import does not import subpackages.
     *
     *    import java.util.*;
     *
     *    Does not import:
     *
     *    java.util.concurrent.*
     *
     * 2. A wildcard import does not import members of classes.
     *
     * 3. It does not import nested types merely because their
     *    enclosing class belongs to the package.
     *
     * 4. Multiple wildcard imports can coexist.
     *
     * 5. If two wildcard-imported packages contain a type with the
     *    same simple name, using that name may produce ambiguity.
     *
     * 6. A wildcard import does not automatically cause every class
     *    in the package to be loaded.
     *
     *
     * C. STATIC IMPORT:
     *
     * Static imports allow static members of a type to be accessed
     * without qualifying them with the type name.
     *
     * Introduced in Java 5.
     *
     * SYNTAX:
     *
     *     import static packageName.TypeName.memberName;
     *
     * EXAMPLE:
     *
     *     import static java.lang.Math.PI;
     *     import static java.lang.Math.sqrt;
     *
     *     public class Demo {
     *         public static void main(String[] args) {
     *             System.out.println(PI);
     *             System.out.println(sqrt(25));
     *         }
     *     }
     *
     * Without static import:
     *
     *     System.out.println(Math.PI);
     *     System.out.println(Math.sqrt(25));
     *
     * Static import can be used for:
     *
     * - Static fields
     * - Static methods
     * - Accessible static nested types
     *
     *
     * D. STATIC WILDCARD IMPORT:
     *
     * Imports accessible static members declared by a type.
     *
     * SYNTAX:
     *
     *     import static packageName.TypeName.*;
     *
     * EXAMPLE:
     *
     *     import static java.lang.Math.*;
     *
     *     public class Demo {
     *         public static void main(String[] args) {
     *             System.out.println(PI);
     *             System.out.println(max(10, 20));
     *             System.out.println(sqrt(64));
     *         }
     *     }
     *
     * CAUTION:
     *
     * Static imports can make code harder to understand if many
     * unrelated members have been imported.
     *
     *
     * ================================================================
     * 5. FULLY QUALIFIED NAME (FQN)
     * ================================================================
     *
     * A fully qualified name identifies a type using its complete
     * package name followed by its type name.
     *
     * EXAMPLE:
     *
     *     java.util.Scanner
     *
     * Here:
     *
     *     java.util = package
     *     Scanner   = type
     *
     * A type can be used without an import by writing its FQN.
     *
     * EXAMPLE:
     *
     *     public class Demo {
     *         public static void main(String[] args) {
     *             java.util.Scanner sc =
     *                     new java.util.Scanner(System.in);
     *         }
     *     }
     *
     * WHEN TO USE FQN:
     *
     * 1. When two imported types have the same simple name.
     * 2. When avoiding import declarations is preferred.
     * 3. When explicitly identifying the source package improves
     *    readability.
     *
     * EXAMPLE OF NAME CONFLICT:
     *
     *     java.util.Date
     *     java.sql.Date
     *
     * Both have the simple name Date.
     *
     * Java does not support importing both under the same simple
     * name and using Date ambiguously.
     *
     * Use the fully qualified name for at least one:
     *
     *     java.util.Date utilDate;
     *     java.sql.Date sqlDate;
     *
     *
     * ================================================================
     * 6. AUTOMATIC AND IMPLICIT IMPORTS
     * ================================================================
     *
     * A. java.lang PACKAGE:
     *
     * The java.lang package is implicitly available to every Java
     * compilation unit.
     *
     * Therefore, an explicit import is not required for common types.
     *
     * Examples:
     *
     *     String
     *     System
     *     Object
     *     Math
     *     Thread
     *     Exception
     *     RuntimeException
     *
     * We can write:
     *
     *     String name = "Java";
     *     System.out.println(name);
     *
     * Instead of:
     *
     *     java.lang.String name = "Java";
     *     java.lang.System.out.println(name);
     *
     * B. SAME-PACKAGE TYPES:
     *
     * Types belonging to the same package can generally refer to
     * one another without import declarations, subject to accessibility
     * rules.
     *
     * C. IMPORTS ARE NOT AUTOMATIC FOR ALL PACKAGES:
     *
     * Only java.lang receives the special implicit package import.
     *
     * For example, Scanner requires:
     *
     *     import java.util.Scanner;
     *
     *
     * ================================================================
     * 7. PACKAGE ACCESSIBILITY
     * ================================================================
     *
     * Package membership plays an important role in Java access
     * control.
     *
     * Java access levels:
     *
     *     private
     *     package-private (no modifier)
     *     protected
     *     public
     *
     * PACKAGE-RELATED SUMMARY:
     *
     * 1. private:
     *    Accessible only within the enclosing top-level class's
     *    permitted private-access scope, including nested classes
     *    under Java's access rules.
     *
     * 2. package-private:
     *    Accessible to types in the same package.
     *
     * 3. protected:
     *    Accessible within the same package and through inheritance
     *    under additional subclass access restrictions.
     *
     * 4. public:
     *    Accessible from other packages, subject to enclosing-type
     *    accessibility and module accessibility.
     *
     * PACKAGE-PRIVATE EXAMPLE:
     *
     * File: com/company/hr/Employee.java
     *
     *     package com.company.hr;
     *
     *     class Employee {
     *         void display() {
     *             System.out.println("Employee");
     *         }
     *     }
     *
     * Employee has no access modifier, so it is package-private.
     *
     * It can be accessed by other types in com.company.hr, but not
     * directly by types in unrelated packages.
     *
     * IMPORTANT:
     *
     * A package-private member is not accessible to a subpackage
     * merely because its package name starts with the parent package.
     *
     * Detailed access modifier rules, inheritance examples and
     * access tables are covered in Modifiers.java.
     *
     *
     * ================================================================
     * 8. SUBPACKAGES
     * ================================================================
     *
     * A subpackage is a package whose name extends another package
     * name using additional components.
     *
     * EXAMPLE:
     *
     *     com.company
     *     com.company.hr
     *     com.company.sales
     *     com.company.hr.payroll
     *
     * These names form a naming hierarchy.
     *
     * IMPORTANT:
     *
     * Java does not treat subpackages as members of their parent
     * package for access-control purposes.
     *
     * For example:
     *
     *     com.company
     *     com.company.hr
     *
     * These are two separate packages.
     *
     * If a class in com.company declares a package-private member,
     * a class in com.company.hr cannot access it merely because
     * com.company.hr is named as a subpackage.
     *
     * SUBPACKAGE RULES:
     *
     * 1. Each package has its own package identity.
     * 2. A subpackage does not inherit package-private access from
     *    its parent.
     * 3. A parent package does not automatically import its
     *    subpackages.
     * 4. Wildcard imports are not recursive.
     * 5. The package hierarchy is primarily a naming and
     *    organization structure.
     *
     *
     * ================================================================
     * 9. PACKAGE VS DIRECTORY
     * ================================================================
     *
     * Package and directory are related but different concepts.
     *
     * PACKAGE:
     * A logical namespace used by Java to organize types.
     *
     * DIRECTORY:
     * A physical filesystem location used to store source files
     * and compiled class files.
     *
     * EXAMPLE:
     *
     *     package com.company.hr;
     *
     * Conventional source directory:
     *
     *     src/com/company/hr/Employee.java
     *
     * Conventional compiled output:
     *
     *     out/com/company/hr/Employee.class
     *
     * The package name is:
     *
     *     com.company.hr
     *
     * The directory path is:
     *
     *     com/company/hr/
     *
     * IMPORTANT:
     *
     * Package names use dots; filesystem paths use directory
     * separators.
     *
     * On Unix-like systems, directory separators are usually '/'.
     * On Windows, they are usually '\\'.
     *
     * The Java compiler and class loaders use package names and
     * classpath roots to locate classes according to the relevant
     * compilation and loading rules.
     *
     *
     * ================================================================
     * 10. COMPILING AND EXECUTING PACKAGED CLASSES
     * ================================================================
     *
     * Consider this source file:
     *
     * File: src/com/company/app/Main.java
     *
     *     package com.company.app;
     *
     *     public class Main {
     *         public static void main(String[] args) {
     *             System.out.println("Hello Java");
     *         }
     *     }
     *
     * A. COMPILE:
     *
     * From the project root:
     *
     *     javac -d out src/com/company/app/Main.java
     *
     * Explanation:
     *
     *     javac = Java compiler
     *     -d    = specifies destination directory for class files
     *     out   = output directory
     *
     * The compiler creates:
     *
     *     out/com/company/app/Main.class
     *
     * B. EXECUTE:
     *
     *     java -cp out com.company.app.Main
     *
     * Explanation:
     *
     *     java                = launches the Java application
     *     -cp out             = sets the classpath to out
     *     com.company.app.Main = fully qualified main class name
     *
     * IMPORTANT:
     *
     * 1. When launching a packaged class, provide its fully qualified
     *    class name.
     *
     * 2. Do not include .class in the class name.
     *
     * 3. Do not provide the package directory as the class name.
     *
     * 4. The classpath should point to the root above the package
     *    directory, not to the package directory itself.
     *
     * 5. The package name and directory layout must be consistent
     *    with the classpath-based lookup convention.
     *
     *
     * ================================================================
     * 11. CLASSPATH AND SOURCEPATH
     * ================================================================
     *
     * CLASSPATH:
     *
     * Specifies locations from which Java tools and the application
     * class loader can find classes and resources, depending on the
     * tool and execution mode.
     *
     * It can contain:
     *
     * - Directories containing package roots
     * - JAR files
     * - Other supported classpath entries
     *
     * EXAMPLE:
     *
     *     java -cp out:lib/library.jar com.company.app.Main
     *
     * On Windows, classpath entries are separated using semicolons:
     *
     *     java -cp out;lib/library.jar com.company.app.Main
     *
     * SOURCEPATH:
     *
     * Specifies locations in which javac searches for source files
     * needed during compilation.
     *
     * EXAMPLE:
     *
     *     javac -sourcepath src -d out src/com/company/app/Main.java
     *
     * IMPORTANT DIFFERENCE:
     *
     *     -cp / --class-path : locates class files and other entries.
     *
     *     -sourcepath        : locates source files during compilation.
     *
     * Classpath and sourcepath are not interchangeable.
     *
     *
     * ================================================================
     * 12. IMPORTANT JAVA API PACKAGES
     * ================================================================
     *
     * 1. java.lang
     *
     *    Fundamental language classes.
     *
     *    Examples:
     *    String, Object, System, Math, Thread, Exception.
     *
     *    Implicitly imported.
     *
     * 2. java.util
     *
     *    Utility classes and collections framework.
     *
     *    Examples:
     *    ArrayList, HashMap, HashSet, Scanner, Collections.
     *
     * 3. java.io
     *
     *    Stream-based input/output and file-related APIs.
     *
     *    Examples:
     *    File, FileInputStream, BufferedReader, PrintWriter.
     *
     * 4. java.nio
     *
     *    Buffer-oriented I/O and related APIs.
     *
     *    Examples:
     *    ByteBuffer, CharBuffer.
     *
     * 5. java.nio.file
     *
     *    File and filesystem operations.
     *
     *    Examples:
     *    Path, Paths, Files.
     *
     * 6. java.net
     *
     *    Networking APIs.
     *
     *    Examples:
     *    URL, URI, Socket, ServerSocket.
     *
     * 7. java.time
     *
     *    Modern date and time API.
     *
     *    Examples:
     *    LocalDate, LocalTime, LocalDateTime, Instant.
     *
     * 8. java.sql
     *
     *    JDBC-related interfaces and classes.
     *
     *    Examples:
     *    Connection, DriverManager, Statement, ResultSet.
     *
     * 9. java.math
     *
     *    Arbitrary-precision numeric operations.
     *
     *    Examples:
     *    BigInteger, BigDecimal.
     *
     * 10. java.util.concurrent
     *
     *     Concurrency utilities.
     *
     *     Examples:
     *     ExecutorService, Future, CountDownLatch.
     *
     * IMPORTANT:
     *
     * java.util.concurrent is a separate subpackage. Importing
     * java.util.* does not import it.
     *
     * Also distinguish Java SE packages from third-party libraries.
     * For example, jakarta.persistence and org.springframework are
     * not packages supplied by the core Java SE API.
     *
     *
     * ================================================================
     * 13. PACKAGE NAMING CONVENTIONS
     * ================================================================
     *
     * Java package names conventionally use lowercase letters.
     *
     * A common convention is reverse-domain naming.
     *
     * EXAMPLE:
     *
     *     Domain: company.com
     *
     *     Package:
     *
     *     com.company
     *
     * Application packages:
     *
     *     com.company.employee
     *     com.company.payment
     *     com.company.authentication
     *
     * WHY REVERSE DOMAIN?
     *
     * Organizations generally control their domain names. Reversing
     * the domain helps reduce naming collisions between organizations.
     *
     * BEST PRACTICES:
     *
     * 1. Use lowercase package names.
     * 2. Prefer meaningful names.
     * 3. Follow reverse-domain naming for organization-owned code.
     * 4. Avoid unnecessarily deep package hierarchies.
     * 5. Avoid Java keywords as package-name components.
     * 6. Avoid the unnamed package in application code.
     * 7. Keep related types grouped logically.
     *
     *
     * ================================================================
     * 14. PACKAGE-INFO.JAVA AND PACKAGE ANNOTATIONS
     * ================================================================
     *
     * package-info.java is a special source file used to document
     * a package and declare package-level annotations.
     *
     * EXAMPLE:
     *
     *     /src/com/company/hr/package-info.java
     *
     *     /*
     *      * Contains employee and HR-related types.
     *      * This package handles employee management.
     *      *\/
     *
     *     @Deprecated
     *     package com.company.hr;
     *
     * Package annotations are annotations whose target includes
     * ElementType.PACKAGE.
     *
     * IMPORTANT:
     *
     * 1. Package annotations apply to the package declaration.
     * 2. package-info.java is also useful for package documentation.
     * 3. It is not a normal application class.
     * 4. Package-level annotations are distinct from class-level
     *    and method-level annotations.
     *
     *
     * ================================================================
     * 15. PACKAGES AND JAVA MODULES
     * ================================================================
     *
     * The Java Platform Module System (JPMS) was introduced in Java 9.
     *
     * A PACKAGE and a MODULE are not the same thing.
     *
     * PACKAGE:
     * A namespace that organizes related types.
     *
     * MODULE:
     * A higher-level unit that groups packages and declares
     * dependencies and accessibility boundaries.
     *
     * A named module generally has a module-info.java file.
     *
     * EXAMPLE:
     *
     *     module com.company.application {
     *         requires java.sql;
     *         exports com.company.api;
     *     }
     *
     * IMPORTANT DIRECTIVES:
     *
     * 1. requires:
     *    Declares a dependency on another module.
     *
     * 2. exports:
     *    Makes a package accessible to other modules at compile time
     *    and runtime under module rules.
     *
     * 3. opens:
     *    Allows reflective access to a package under module rules.
     *
     * 4. uses:
     *    Declares a service interface used by a module.
     *
     * 5. provides:
     *    Declares a service implementation supplied by a module.
     *
     * MODULE VS PACKAGE:
     *
     * - One module can contain multiple packages.
     * - A package belongs to a module in a modular application.
     * - A package alone does not declare module dependencies.
     * - Module exports and package accessibility are separate
     *   concerns from Java access modifiers.
     *
     * SPLIT PACKAGES:
     *
     * A split package occurs when the same package name is present
     * in multiple modules.
     *
     * Named modules impose restrictions on split packages, and
     * attempting to use them can cause resolution or configuration
     * problems.
     *
     * Avoid split packages in modular applications.
     *
     *
     * ================================================================
     * 16. COMMON PACKAGE-RELATED CONFUSIONS
     * ================================================================
     *
     * 1. PACKAGE VS IMPORT:
     *
     *    package declares the package of a compilation unit.
     *    import helps resolve type or static-member names.
     *
     * 2. PACKAGE VS DIRECTORY:
     *
     *    Package is a logical namespace.
     *    Directory is a physical filesystem location.
     *
     * 3. PARENT PACKAGE VS SUBPACKAGE:
     *
     *    They are separate packages.
     *    Package-private access is not inherited by subpackages.
     *
     * 4. IMPORT VS CLASS LOADING:
     *
     *    Import does not load or copy a class.
     *    Class loading is a separate runtime process.
     *
     * 5. WILDCARD IMPORT:
     *
     *    import java.util.*;
     *
     *    Does not import java.util.concurrent.
     *
     * 6. java.lang:
     *
     *    Implicitly available; explicit import is unnecessary.
     *
     * 7. UNNAMED PACKAGE:
     *
     *    Named packages cannot import its types.
     *
     * 8. CLASSpath:
     *
     *    The classpath points to package roots, not normally to
     *    individual package directories.
     *
     * 9. PACKAGE DECLARATION:
     *
     *    A source file can have only one package declaration.
     *
     * 10. STATIC IMPORT:
     *
     *     Imports static members, not ordinary instance members.
     *
     *
     * ================================================================
     * 17. COMMON ERRORS AND MISTAKES
     * ================================================================
     *
     * 1. Placing an import statement before the package declaration.
     *
     * 2. Declaring more than one package in a source file.
     *
     * 3. Using an incorrect package name.
     *
     * 4. Keeping a packaged source file in a directory structure
     *    inconsistent with the project's source layout.
     *
     * 5. Running a packaged class using only its simple class name
     *    when the launcher expects its fully qualified name.
     *
     * 6. Including .class in the class name while launching a class.
     *
     * 7. Setting the classpath to the package directory rather than
     *    its classpath root.
     *
     * 8. Assuming a wildcard import includes subpackages.
     *
     * 9. Assuming subpackages share package-private access with
     *    their parent packages.
     *
     * 10. Assuming imports copy or load classes.
     *
     * 11. Trying to import a type from the unnamed package into a
     *     named package.
     *
     * 12. Forgetting that package names are case-sensitive.
     *
     * 13. Confusing classpath with sourcepath.
     *
     * 14. Assuming every package must have a physical directory
     *     in every possible deployment or compilation environment.
     *
     *
     * ================================================================
     * 18. INTERVIEW QUESTIONS AND QUICK ANSWERS
     * ================================================================
     *
     * Q1. What is a package in Java?
     *
     * A namespace that organizes related types and helps manage
     * naming conflicts and access control.
     *
     * Q2. Why are packages used?
     *
     * Organization, namespace management, reusability,
     * maintainability and access control.
     *
     * Q3. What are the main types of packages?
     *
     * Java API packages, user-defined packages and the unnamed
     * package are common classifications.
     *
     * Q4. What is the default package?
     *
     * The unnamed package used when no package declaration exists.
     *
     * Q5. Which package is implicitly imported?
     *
     * java.lang.
     *
     * Q6. Is importing a package mandatory?
     *
     * No. A type can be referenced using its fully qualified name.
     *
     * Q7. Can we import all subpackages using *?
     *
     * No. Wildcard imports are not recursive.
     *
     * Q8. Can two packages contain classes with the same name?
     *
     * Yes, if their fully qualified names are different.
     *
     * Q9. Are subpackages treated as part of the parent package
     *    for access control?
     *
     * No. They are separate packages.
     *
     * Q10. Can a named package import a class from the unnamed package?
     *
     * No.
     *
     * Q11. What is a fully qualified class name?
     *
     * The complete package name followed by the type name.
     *
     * Q12. What is static import?
     *
     * It allows accessible static members to be referenced without
     * qualifying them with the declaring type name.
     *
     * Q13. Does an import statement load a class?
     *
     * No. Import is a compile-time name-resolution mechanism.
     *
     * Q14. What is package-info.java?
     *
     * A special source file used for package documentation and
     * package-level annotations.
     *
     * Q15. What is the difference between a package and a module?
     *
     * A package organizes types; a module groups packages and
     * declares dependencies and accessibility boundaries.
     *
     *
     * ================================================================
     * 19. FINAL REVISION CHECKLIST
     * ================================================================
     *
     * [ ] Definition and purpose of packages
     * [ ] Java API and user-defined packages
     * [ ] Unnamed/default package
     * [ ] Package declaration syntax and rules
     * [ ] Package naming conventions
     * [ ] Directory structure and package mapping
     * [ ] Single-type imports
     * [ ] Wildcard imports
     * [ ] Static imports
     * [ ] Fully qualified names
     * [ ] Implicit java.lang import
     * [ ] Same-package type access
     * [ ] Package-private accessibility
     * [ ] Protected access across packages
     * [ ] Parent packages and subpackages
     * [ ] Package versus directory
     * [ ] Compilation using javac -d
     * [ ] Execution using fully qualified class names
     * [ ] Classpath and sourcepath
     * [ ] Important Java API packages
     * [ ] package-info.java
     * [ ] Package annotations
     * [ ] Packages and Java modules
     * [ ] Split packages
     * [ ] Common errors and interview questions
     *
     *
     * ================================================================
     * END OF JAVA PACKAGES
     * ================================================================
     */

    public static void main(String[] args) {
        System.out.print("The above is all about Packages , Happy Learning ...!");
    }
}
