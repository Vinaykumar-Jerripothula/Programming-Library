
class IntroductionToJava {

    /*
        ============================================================
                         INTRODUCTION TO JAVA
        ============================================================


        1. WHAT IS JAVA?

        Java is a high-level, general-purpose, class-based,
        statically typed, object-oriented programming language
        and computing platform.

        It is designed to develop applications that can run
        across different operating systems and hardware
        architectures using a compatible Java Runtime Environment.

        Java source code is compiled into an intermediate
        representation called bytecode.

        Bytecode is executed by the Java Virtual Machine (JVM).

        Important characteristics:

        - High-level programming language.
        - General-purpose programming language.
        - Statically typed language.
        - Strongly typed language.
        - Class-based programming language.
        - Object-oriented programming language.
        - Platform-independent at the bytecode level.
        - Supports multithreading and concurrency.
        - Provides automatic memory management.
        - Supports exception handling.
        - Supports dynamic class loading.


        2. WHY WAS JAVA DEVELOPED?

        Before Java became popular, programming languages
        such as C and C++ were widely used for software development.

        However, software developers faced several challenges:

        - Programs were often dependent on specific platforms.
        - Porting applications between operating systems
          and hardware architectures required effort.
        - Manual memory management could introduce errors.
        - Pointer manipulation could lead to memory corruption.
        - Developing portable network-based applications
          was difficult.
        - Software needed better runtime safety mechanisms.

        Java was designed to address many of these challenges.

        Major design goals:

        - Platform independence.
        - Portability.
        - Simplicity.
        - Security.
        - Robustness.
        - Object-oriented programming.
        - Multithreading support.
        - Distributed computing support.
        - High performance.
        - Dynamic class loading.

        Important:
        Java does not eliminate every programming problem.
        It provides language and runtime mechanisms that help
        developers build safer and more portable applications.


        3. ORIGIN OF JAVA

        Java originated at Sun Microsystems.

        Important people involved:

        James Gosling:
        One of the principal designers of Java.
        Commonly known as the Father of Java.

        Mike Sheridan:
        One of the early members of the Green Project.

        Patrick Naughton:
        One of the early members of the Green Project.

        The Green Project:

        - Started in 1991 at Sun Microsystems.
        - Focused on developing software for consumer
          electronic devices.
        - Explored the possibility of a portable language
          for different hardware platforms.
        - Led to the development of the language that
          eventually became Java.


        4. HISTORY AND EVOLUTION OF JAVA

        1991:
        - The Green Project started at Sun Microsystems.
        - The project was led by a team including
          James Gosling, Mike Sheridan and Patrick Naughton.
        - The language was initially called Oak.

        1992:
        - The team demonstrated the Star7 handheld
          interactive device.

        1994:
        - The team recognized the potential of the language
          for the emerging World Wide Web.

        1995:
        - Oak was renamed Java because the name Oak
          was already trademarked.
        - Java was publicly introduced.
        - Java gained attention through its ability to
          execute code using a Java-enabled environment.

        1996:
        - JDK 1.0 was released.
        - Java started gaining adoption among developers.

        1998:
        - Java 2 was introduced.
        - The platform was divided into different editions,
          including J2SE, J2EE and J2ME.

        2004:
        - Java 5 introduced major language improvements,
          including generics, annotations, enums,
          enhanced for-loop and autoboxing.

        2006:
        - Sun Microsystems announced its intention to
          release much of Java as open-source software.

        2007:
        - Most of the core Java implementation was released
          under the GNU General Public License (GPL)
          through the OpenJDK project.

        2009:
        - Oracle announced its agreement to acquire
          Sun Microsystems.

        2010:
        - Oracle completed the acquisition of Sun Microsystems.
        - Oracle became the steward of Java.

        2017:
        - Java 9 introduced the Java Platform Module System.
        - Java moved to a regular six-month feature release cycle.

        2018:
        - Java 11 became an important Long-Term Support release.

        2021:
        - Java 17 became an important Long-Term Support release.

        2023:
        - Java 21 became an important Long-Term Support release.

        2025:
        - Java 25 became an important Long-Term Support release.

        2026:
        - Java 27 was released.
        - Java continued its six-month feature release cycle.


        5. WHY WAS OAK RENAMED TO JAVA?

        The language was initially named Oak.

        The name Oak was changed because it was already
        trademarked.

        The development team selected the name Java.

        The name Java is associated with coffee.

        Important points:

        - Java is not an acronym.
        - Java is a programming language name.
        - The coffee cup is associated with Java branding.
        - Oak was the original name of the language.


        6. JAVA DESIGN PHILOSOPHY

        Java was designed around several important principles.

        A. WRITE ONCE, RUN ANYWHERE (WORA)

        Java follows the principle:

        Write Once, Run Anywhere.

        Meaning:

        A Java program can be compiled once into bytecode
        and executed on different platforms that provide
        compatible JVM implementations.

        General process:

        Java Source Code
                |
                v
        Java Compiler
                |
                v
        Bytecode (.class)
                |
                v
        Compatible JVM
                |
                v
        Program Execution

        Important:
        WORA does not mean that every Java application
        automatically works on every platform.

        Platform-specific native libraries, external
        dependencies, operating-system assumptions and
        incompatible runtime versions can affect portability.


        B. OBJECT-ORIENTED DESIGN

        Java encourages organizing programs using classes
        and objects.

        This helps developers:

        - Organize code into reusable components.
        - Model real-world entities.
        - Maintain large applications.
        - Apply abstraction and encapsulation.
        - Extend existing functionality.

        Important:
        Java is class-based and object-oriented, but it is
        not a purely object-oriented language because it
        supports primitive types and static members.


        C. SECURITY BY DESIGN

        Java includes language and runtime mechanisms
        intended to improve application safety.

        Examples:

        - Strong type checking.
        - Bytecode verification.
        - Controlled memory access.
        - Class loading mechanisms.
        - Runtime access checks.
        - Security-related standard APIs.

        Important:
        Java applications can still contain security
        vulnerabilities. Secure coding practices are necessary.


        D. AUTOMATIC MEMORY MANAGEMENT

        Java provides automatic memory management.

        The JVM manages the allocation and reclamation
        of memory used by Java objects.

        Garbage collection helps reclaim memory occupied
        by objects that are no longer reachable.

        Important:
        Automatic garbage collection does not guarantee
        that every resource is automatically closed.

        Files, sockets and database connections must be
        managed appropriately.


        E. MULTITHREADED PROGRAMMING

        Java provides built-in language and library support
        for multithreaded programming.

        Applications can perform multiple tasks concurrently.

        Examples:

        - Processing multiple requests.
        - Performing background operations.
        - Handling independent tasks.
        - Executing parallel computations.


        F. PORTABLE SOFTWARE

        Java aims to provide consistent behaviour across
        supported platforms.

        It achieves portability through:

        - Standardized language behaviour.
        - Standardized bytecode format.
        - Standard Java APIs.
        - Compatible JVM implementations.

        Platform-specific dependencies can still reduce
        portability.


        
        ============================================================
                         7. FEATURES OF JAVA
        ============================================================

        Java is known for several important features.

        These features describe the language's design,
        runtime behaviour and programming capabilities.


        A. SIMPLE

        Java was designed to be relatively simple to learn
        and use compared with languages such as C++.

        Reasons:

        1. No explicit pointer arithmetic.
        2. Automatic memory management.
        3. No user-defined operator overloading.
        4. No multiple inheritance of classes.
        5. Simplified memory management.
        6. No manual memory deallocation using free() or delete.
        7. No header files like C and C++.
        8. No preprocessor directives like #define and #include.

        Important:

        - Java still uses references to access objects.
        - Java does not expose direct pointer manipulation.
        - Java supports the new operator for object creation.
        - Java supports method overloading.
        - Java supports multiple inheritance through interfaces.
        - Java is not necessarily easier for every programming task.


        B. OBJECT-ORIENTED

        Java supports object-oriented programming.

        It organizes software around classes and objects.

        Major OOP principles:

        1. Encapsulation
        2. Inheritance
        3. Polymorphism
        4. Abstraction

        Benefits:

        - Code reusability.
        - Modularity.
        - Maintainability.
        - Extensibility.
        - Better organization of large applications.

        Important:

        - Java is class-based.
        - Java supports classes and objects.
        - Java supports interfaces and abstract classes.
        - Java supports inheritance and polymorphism.
        - Java is not purely object-oriented because it has
          primitive data types and static members.

        Detailed OOP concepts are covered in the OOP lesson.


        C. PLATFORM-INDEPENDENT

        Java is platform-independent at the bytecode level.

        Meaning:

        Java source code is compiled into bytecode.

        The bytecode can execute on different operating systems
        using compatible JVM implementations.

        Example:

        Java Source Code
                |
                v
        Java Compiler (javac)
                |
                v
        Bytecode (.class)
                |
                +----------------------+
                |          |           |
                v          v           v
             Windows     Linux       macOS
               JVM        JVM         JVM
                |          |           |
                v          v           v
             Execution  Execution   Execution

        Important:

        - Java source code is not directly compiled into
          native machine code by the standard javac compiler.
        - javac generates JVM bytecode.
        - Each platform needs a compatible JVM implementation.
        - Platform-specific dependencies can affect portability.
        - Platform independence does not mean that the JVM
          itself is platform-independent.


        D. PORTABLE

        Portability means that software can be moved
        between different environments with minimal changes.

        Java provides portability through:

        1. Standardized primitive data type sizes.
        2. Standardized bytecode format.
        3. Standard Java APIs.
        4. JVM abstraction.
        5. Defined language behaviour.

        Example:

        Java's int type is always a signed 32-bit integer.

        It does not become 16-bit on one operating system
        and 32-bit on another.

        Important:

        - Platform independence and portability are related,
          but they are not exactly the same.
        - Native libraries, file paths, environment variables
          and operating-system dependencies can affect portability.


        E. ARCHITECTURE-NEUTRAL

        Java bytecode is not designed for one specific
        processor architecture.

        The compiler generates bytecode that follows
        the JVM specification.

        A compatible JVM translates or executes that
        bytecode for the underlying processor architecture.

        Example:

        The same bytecode can be executed on:

        - x86 processors.
        - x86-64 processors.
        - ARM-based processors.

        Important:

        Architecture-neutral means that the bytecode
        is not tied to a specific CPU instruction set.

        It does not mean that all processors execute
        the same native machine instructions.


        F. SECURE

        Java provides several mechanisms to improve
        application security and runtime safety.

        Important mechanisms:

        1. Bytecode Verification

           The JVM verifies bytecode before execution
           to check structural and type-safety constraints.

        2. Class Loading

           Classes are loaded through class loaders.

        3. Runtime Checks

           Java performs checks such as array bounds checking
           and type checking.

        4. Memory Safety

           Java does not expose ordinary direct pointer
           arithmetic to application code.

        5. Access Control

           Java provides access modifiers such as:
           private, protected and public.

        6. Security APIs

           Java provides cryptographic and security-related
           APIs through its standard libraries.

        7. Type Safety

           Java's type system helps prevent many invalid
           operations.

        Important limitations:

        - Java is not completely immune to vulnerabilities.
        - Incorrect application logic can create security issues.
        - Vulnerable libraries can compromise applications.
        - Unsafe APIs and native code can introduce risks.
        - Secure coding and configuration are still necessary.

        Note:

        The historical Java Security Manager and sandbox model
        should not be confused with modern Java application
        security. The Security Manager was deprecated for removal
        in Java 17 and permanently disabled in Java 24.


        G. ROBUST

        Robust means reliable and capable of handling
        many programming errors safely.

        Java provides robustness through:

        1. Strong type checking.
        2. Exception handling.
        3. Automatic memory management.
        4. Array bounds checking.
        5. No direct pointer arithmetic.
        6. Runtime checks.
        7. Garbage collection.

        Benefits:

        - Reduces memory-related programming errors.
        - Helps detect invalid operations.
        - Provides structured error handling.
        - Improves application reliability.

        Important:

        Java does not prevent every runtime error.

        Examples of possible problems:

        - NullPointerException.
        - ArrayIndexOutOfBoundsException.
        - ArithmeticException.
        - OutOfMemoryError.
        - StackOverflowError.


        H. MULTITHREADED

        Java provides built-in support for multithreaded
        programming.

        A thread is an independent path of execution
        within a process.

        Multithreading allows multiple threads to execute
        concurrently.

        Examples:

        - Processing multiple client requests.
        - Running background tasks.
        - Downloading files while performing other operations.
        - Processing independent computations.

        Java provides:

        - Thread class.
        - Runnable interface.
        - Callable interface.
        - Synchronization mechanisms.
        - Locks.
        - Executor Framework.
        - Concurrent Collections.
        - CompletableFuture.
        - Virtual Threads (Java 21).

        Important:

        - Concurrency means dealing with multiple tasks
          that can make progress during overlapping periods.
        - Parallelism means executing multiple tasks
          simultaneously.
        - Multithreading does not guarantee parallel execution.
        - Actual parallelism depends on available processors
          and runtime scheduling.

        Detailed coverage is in the Multithreading lesson.


        I. DISTRIBUTED

        Java provides APIs and libraries for developing
        distributed applications.

        A distributed application consists of components
        running on different computers or processes that
        communicate through a network.

        Java supports distributed programming through:

        - Socket APIs.
        - Networking APIs.
        - HTTP Client API.
        - Remote communication technologies.
        - Serialization APIs.
        - Standard networking libraries.

        Examples:

        - Client-server applications.
        - Network-based applications.
        - Distributed business systems.
        - Communication between services.

        Important:

        Java provides tools for distributed computing.
        It does not automatically make an application distributed.


        J. HIGH PERFORMANCE

        Java is designed to provide good application performance.

        Important mechanisms:

        1. Just-In-Time (JIT) Compilation

           Frequently executed bytecode can be compiled
           into native machine code at runtime.

        2. Runtime Optimization

           The JVM can optimize frequently executed code
           based on runtime information.

        3. Adaptive Optimization

           The JVM can use information about actual
           application execution to improve performance.

        4. Garbage Collection

           Automatic memory management reduces the need
           for manual memory deallocation.

        5. Efficient Runtime Implementations

           Modern JVM implementations provide optimized
           execution engines and memory management.

        Important:

        - Java is not always faster than C or C++.
        - Performance depends on workload and implementation.
        - Startup time, memory usage and latency also matter.
        - JIT compilation may require warm-up time.
        - Performance should be measured rather than assumed.


        K. COMPILED AND INTERPRETED

        Java uses both compilation and runtime execution.

        Compilation:

        Java source code (.java) is compiled by javac
        into bytecode (.class).

        Runtime:

        The JVM loads and executes bytecode.

        It can use:

        - Bytecode interpretation.
        - Just-In-Time compilation.
        - Runtime optimization.

        General process:

        Source Code (.java)
                |
                v
             javac
                |
                v
           Bytecode (.class)
                |
                v
               JVM
                |
          +-----+------+
          |            |
          v            v
       Interpreter    JIT Compiler
          |            |
          +-----+------+
                |
                v
          Native Execution

        Important:

        - javac is a compiler.
        - Bytecode is an intermediate representation.
        - The JVM can interpret bytecode.
        - The JVM can compile frequently executed code
          into native machine code.
        - Java is therefore commonly described as
          both compiled and interpreted.

        Note:

        Some Java implementations and execution modes
        can use ahead-of-time compilation as well.


        L. DYNAMIC

        Java supports dynamic behaviour at runtime.

        Important capabilities:

        1. Dynamic Class Loading

           Classes can be loaded when they are required.

        2. Dynamic Linking

           Class references and dependencies can be resolved
           during the execution process.

        3. Reflection

           Applications can inspect classes, methods,
           constructors and fields at runtime.

        4. Runtime Object Creation

           Objects can be created dynamically using reflection
           and other runtime mechanisms.

        Benefits:

        - Flexible application design.
        - Extensibility.
        - Plugin-based architectures.
        - Runtime inspection.

        Important:

        Dynamic behaviour does not mean Java is dynamically typed.

        Java remains a statically typed language.


        ============================================================
                     8. IMPORTANT FEATURE DISTINCTIONS
        ============================================================

        1. PLATFORM INDEPENDENCE vs PORTABILITY

        Platform independence:
        Bytecode can execute on different platforms through
        compatible JVM implementations.

        Portability:
        Software can be moved between environments with
        minimal changes.

        2. ARCHITECTURE-NEUTRAL vs PLATFORM-INDEPENDENT

        Architecture-neutral:
        Bytecode is not tied to a specific processor instruction set.

        Platform-independent:
        Java applications can execute on different operating
        systems through compatible JVM implementations.

        3. CONCURRENCY vs PARALLELISM

        Concurrency:
        Multiple tasks make progress during overlapping periods.

        Parallelism:
        Multiple tasks execute simultaneously.

        4. STATICALLY TYPED vs DYNAMICALLY TYPED

        Statically typed:
        Types are checked primarily at compile time.

        Dynamically typed:
        Types are determined or checked primarily at runtime.

        Java is statically typed.

        5. COMPILED vs INTERPRETED

        Compiled:
        Source code is translated into another representation.

        Interpreted:
        Instructions are executed by an interpreter.

        Java uses source compilation and JVM runtime execution.


        ============================================================
                     9. IMPORTANT INTERVIEW POINTS
        ============================================================

        1. Java is a high-level, general-purpose language.
        2. Java is class-based and object-oriented.
        3. Java is statically typed.
        4. Java is platform-independent at the bytecode level.
        5. Java follows Write Once, Run Anywhere.
        6. Java source code is compiled into bytecode.
        7. Bytecode is executed by a compatible JVM.
        8. Java supports automatic memory management.
        9. Java supports multithreading.
        10. Java supports concurrency and parallel programming.
        11. Java is not a purely object-oriented language.
        12. Java does not support multiple inheritance of classes.
        13. Java supports multiple inheritance of type through
            interfaces.
        14. Java does not support direct pointer arithmetic.
        15. Java is statically typed, not dynamically typed.
        16. Java uses interpretation and JIT compilation.
        17. Java is architecture-neutral at the bytecode level.
        18. Java is not completely immune to security vulnerabilities.
        19. Java's performance depends on the JVM and workload.
        20. Dynamic class loading does not make Java dynamically typed.


        
        ============================================================
                     10. JAVA EDITIONS AND PLATFORMS
        ============================================================

        Java provides different platforms and specifications
        for different application requirements.

        The major traditional Java platform editions are:

        1. Java SE  - Standard Edition.
        2. Java EE  - Enterprise Edition (historical name).
        3. Java ME  - Micro Edition.

        Java EE evolved into Jakarta EE.

        Each platform targets different application environments.


        A. JAVA SE (JAVA PLATFORM, STANDARD EDITION)

        Java SE is the foundation of the Java platform.

        It provides the core language, runtime and standard
        libraries required to develop and execute general-purpose
        Java applications.

        Main areas provided by Java SE:

        - Java programming language.
        - Java Virtual Machine specification.
        - Core language APIs.
        - Collections Framework.
        - Exception handling support.
        - Multithreading APIs.
        - Input and Output APIs.
        - Networking APIs.
        - Date and Time API.
        - Security APIs.
        - JDBC API specification.

        Common application areas:

        - Standalone applications.
        - Desktop applications.
        - Command-line applications.
        - General-purpose software.
        - Server-side application foundations.
        - Enterprise application foundations.

        Important:

        - Java SE is the foundation on which other Java
          enterprise technologies are built.
        - Core Java primarily focuses on Java SE.
        - Java SE does not mean only desktop applications.
        - Java SE is also widely used in server-side development.


        B. JAVA EE (JAVA PLATFORM, ENTERPRISE EDITION)

        Java EE was designed to provide specifications
        and APIs for developing enterprise applications.

        It extended the Java SE platform with additional
        enterprise-level capabilities.

        Historical names:

        J2EE:
        Java 2 Platform, Enterprise Edition.

        Java EE:
        Java Platform, Enterprise Edition.

        Jakarta EE:
        The continuation of Java EE under the Eclipse Foundation.

        Enterprise application requirements:

        - Web application development.
        - Enterprise components.
        - Transaction management.
        - Dependency injection.
        - Persistence.
        - Messaging.
        - Security.
        - Distributed application support.

        Historical Java EE technologies included:

        - Servlets.
        - JavaServer Pages (JSP).
        - Enterprise JavaBeans (EJB).
        - Java Persistence API (JPA).
        - Java Message Service (JMS).
        - Java API for RESTful Web Services (JAX-RS).

        Important:

        - Java EE was transferred to the Eclipse Foundation.
        - It was renamed Jakarta EE.
        - Jakarta EE is maintained as an open standard
          through the Eclipse Foundation.
        - Java EE is a historical name, not a separate
          currently evolving platform.
        - Jakarta EE is based on Java SE.

        Note:

        Jakarta EE technologies are outside the scope of
        our Core Java lessons. They belong to separate
        enterprise Java learning topics.


        C. JAVA ME (JAVA PLATFORM, MICRO EDITION)

        Java ME was designed for developing applications
        for resource-constrained devices and embedded systems.

        It targets environments where memory, processing power
        and other resources may be limited.

        Application areas:

        - Embedded devices.
        - Feature phones.
        - Small connected devices.
        - Specialized electronic devices.
        - Resource-constrained environments.

        Important characteristics:

        - Designed for limited-resource environments.
        - Provides APIs suitable for supported device profiles.
        - Has different configurations and profiles.
        - Differs from the general-purpose Java SE environment.

        Important:

        - Java ME is not simply a smaller installation of Java SE.
        - It has its own platform specifications and APIs.
        - It is not the standard platform used for modern
          Android application development.


        D. JAVA CARD

        Java Card is a specialized Java platform designed
        for secure applications on smart cards and
        similarly constrained secure devices.

        Application areas:

        - Smart cards.
        - SIM cards.
        - Secure identity cards.
        - Payment cards.
        - Secure elements.

        Characteristics:

        - Designed for highly constrained devices.
        - Provides a specialized runtime environment.
        - Supports secure application execution.
        - Uses a subset and specialized form of Java
          technology and APIs.

        Important:

        - Java Card is not the same as Java SE.
        - It is not one of the three traditional editions
          commonly listed as SE, EE and ME.
        - It is a specialized platform for secure devices.


        ============================================================
                    11. COMPARISON OF JAVA EDITIONS
        ============================================================

        Java SE:

        Full Name:
        Java Platform, Standard Edition.

        Main Purpose:
        General-purpose Java application development.

        Target Environment:
        Desktops, servers and general-purpose systems.

        Foundation:
        Core Java language, JVM and standard APIs.


        Java EE / Jakarta EE:

        Full Name:
        Java Platform, Enterprise Edition (historical name).
        Jakarta EE (current name).

        Main Purpose:
        Enterprise application development.

        Target Environment:
        Enterprise application servers and compatible runtimes.

        Foundation:
        Java SE plus enterprise specifications and APIs.


        Java ME:

        Full Name:
        Java Platform, Micro Edition.

        Main Purpose:
        Applications for resource-constrained devices.

        Target Environment:
        Embedded and specialized devices.

        Foundation:
        Specialized configurations, profiles and APIs.


        Java Card:

        Main Purpose:
        Secure applications on smart cards and secure devices.

        Target Environment:
        Smart cards and secure elements.

        Foundation:
        Specialized Java Card platform and runtime.


        ============================================================
                    12. IMPORTANT EDITION DISTINCTIONS
        ============================================================

        1. JAVA SE vs JAVA EE / JAKARTA EE

        Java SE:
        Provides the core Java platform.

        Jakarta EE:
        Provides enterprise specifications and APIs
        built on the Java SE foundation.

        2. JAVA EE vs JAKARTA EE

        Java EE:
        Historical enterprise Java platform name.

        Jakarta EE:
        Its continuation under the Eclipse Foundation.

        3. JAVA SE vs JAVA ME

        Java SE:
        General-purpose computing platform.

        Java ME:
        Specialized platform for resource-constrained devices.

        4. JAVA ME vs JAVA CARD

        Java ME:
        Designed for various resource-constrained environments.

        Java Card:
        Designed specifically for secure smart-card
        and secure-element environments.

        5. JAVA EDITIONS vs JAVA VERSIONS

        Editions:
        Represent different Java platform specifications
        and application environments.

        Versions:
        Represent successive releases of the Java platform.

        Example:

        Java SE 21:
        Standard Edition, version 21.

        Jakarta EE 11:
        Enterprise platform specification version 11.

        These numbers do not represent the same release series.

        6. JAVA SE vs CORE JAVA

        Java SE:
        An official Java platform specification.

        Core Java:
        A commonly used educational and industry term
        for the foundational Java language and APIs.

        Core Java is not a separate official Java edition.


        ============================================================
                    13. IMPORTANT INTERVIEW POINTS
        ============================================================

        1. Java SE stands for Java Platform, Standard Edition.
        2. Java SE provides the foundation of the Java platform.
        3. Java EE stands for Java Platform, Enterprise Edition.
        4. Java EE is the historical name of the enterprise platform.
        5. Jakarta EE is the continuation of Java EE.
        6. Jakarta EE is maintained by the Eclipse Foundation.
        7. Jakarta EE is built on Java SE.
        8. Java ME stands for Java Platform, Micro Edition.
        9. Java ME targets resource-constrained environments.
        10. Java Card targets secure smart-card environments.
        11. Core Java is not an official Java edition.
        12. Java SE is not limited to desktop applications.
        13. Java editions and Java versions are different concepts.
        14. Java EE and Jakarta EE are not two unrelated platforms.
        15. Java SE is the primary platform covered by
            our Core Java learning roadmap.


        
        ============================================================
                    14. JAVA VERSIONS AND RELEASE HISTORY
        ============================================================

        Java has evolved continuously since its first public
        release in 1995.

        Each new version can introduce:

        - New language features.
        - New APIs and libraries.
        - JVM improvements.
        - Performance enhancements.
        - Security improvements.
        - Deprecated features.
        - Removed features.
        - Developer productivity improvements.

        Java versions are commonly identified by major version
        numbers.

        Examples:

        Java 8
        Java 11
        Java 17
        Java 21
        Java 25
        Java 27


        A. IMPORTANT JAVA VERSION HISTORY

        Java 1.0 (1996)

        - First official public JDK release.
        - Established the foundation of the Java platform.
        - Introduced the original Java language and runtime.


        Java 1.1 (1997)

        Important additions:

        - Inner classes.
        - JDBC.
        - Reflection.
        - JavaBeans.
        - RMI improvements.


        Java 2 / J2SE 1.2 (1998)

        Important additions:

        - Collections Framework.
        - Swing.
        - Java 2 platform naming.
        - Major improvements to the standard libraries.


        Java 1.3 (2000)

        Important improvements:

        - JVM performance improvements.
        - HotSpot JVM became the main reference
          implementation technology.
        - JNDI.
        - Java Sound API.


        Java 1.4 (2002)

        Important additions:

        - Assertions.
        - Regular Expressions.
        - NIO.
        - Logging API.
        - Exception chaining.
        - Improved networking APIs.


        Java 5 / Java 1.5 (2004)

        A major language evolution release.

        Important additions:

        - Generics.
        - Annotations.
        - Enumerations.
        - Autoboxing and Unboxing.
        - Enhanced for-loop.
        - Varargs.
        - Static imports.
        - Formatted output improvements.


        Java 6 (2006)

        Important improvements:

        - Performance enhancements.
        - Compiler API.
        - Scripting support.
        - Improvements to web services APIs.
        - Monitoring and management improvements.


        Java 7 (2011)

        Important additions:

        - try-with-resources.
        - Diamond operator.
        - String in switch.
        - Multi-catch.
        - Binary integer literals.
        - Underscores in numeric literals.
        - NIO.2.
        - Fork/Join Framework.


        Java 8 (2014) - LTS

        One of the most important Java releases.

        Important additions:

        - Lambda Expressions.
        - Functional Interfaces.
        - Stream API.
        - Method References.
        - Default methods in interfaces.
        - Static methods in interfaces.
        - Optional API.
        - New Date and Time API.
        - CompletableFuture.
        - PermGen removal and Metaspace introduction.

        Note:
        Java 8 significantly changed how developers
        write Java code.


        Java 9 (2017)

        Important additions:

        - Java Platform Module System (JPMS).
        - JShell.
        - Private methods in interfaces.
        - Collection factory methods.
        - Stream API improvements.
        - G1 became the default garbage collector
          in standard HotSpot configurations.


        Java 10 (2018)

        Important additions:

        - Local-variable type inference using var.
        - Application Class-Data Sharing improvements.
        - Garbage Collector interface.
        - Parallel Full GC for G1.


        Java 11 (2018) - LTS

        Important additions:

        - Standard HTTP Client API.
        - New String methods.
        - New Files methods.
        - Local-variable syntax for lambda parameters.
        - Single-file source-code execution.
        - Removal of Java EE and CORBA modules
          from the JDK distribution.


        Java 12 (2019)

        Important additions:

        - Switch expressions (preview).
        - Compact Number Formatting.
        - JVM performance improvements.


        Java 13 (2019)

        Important additions:

        - Text Blocks (preview).
        - Switch Expressions improvements (preview).
        - Dynamic CDS Archives.


        Java 14 (2020)

        Important additions:

        - Records (preview).
        - Pattern Matching for instanceof (preview).
        - Switch Expressions became permanent.
        - Helpful NullPointerExceptions.
        - Text Blocks (second preview).


        Java 15 (2020)

        Important additions:

        - Text Blocks became permanent.
        - Sealed Classes (preview).
        - Records (second preview).
        - Hidden Classes.
        - Nashorn JavaScript Engine removed.


        Java 16 (2021)

        Important additions:

        - Records became permanent.
        - Pattern Matching for instanceof became permanent.
        - Sealed Classes (second preview).
        - Strong encapsulation of JDK internals.
        - Unix-domain socket channels.


        Java 17 (2021) - LTS

        Important additions:

        - Sealed Classes became permanent.
        - Pattern Matching for switch (preview).
        - Strong encapsulation of JDK internals.
        - Security and performance improvements.

        Important:
        Java 17 is an important release for modern
        enterprise Java development.


        Java 18 (2022)

        Important additions:

        - Simple Web Server.
        - UTF-8 became the default charset.
        - Code snippets in Java API documentation.
        - Finalization deprecated for removal.


        Java 19 (2022)

        Important additions:

        - Virtual Threads (preview).
        - Record Patterns (preview).
        - Pattern Matching for switch (preview).
        - Structured Concurrency (incubator).


        Java 20 (2023)

        Important additions:

        - Virtual Threads (second preview).
        - Record Patterns (second preview).
        - Pattern Matching for switch (fourth preview).
        - Scoped Values (incubator).
        - Structured Concurrency (second incubator).


        Java 21 (2023) - LTS

        Important additions:

        - Virtual Threads became permanent.
        - Record Patterns became permanent.
        - Pattern Matching for switch became permanent.
        - Sequenced Collections.
        - String Templates (preview).
        - Generational ZGC.
        - Unnamed Patterns and Variables (preview).
        - Unnamed Classes and Instance Main Methods (preview).

        Note:
        String Templates were previewed in Java 21 and 22,
        but were subsequently withdrawn from the feature path.
        They are not a permanent Java feature.


        Java 22 (2024)

        Important additions:

        - Foreign Function and Memory API became permanent.
        - Stream Gatherers (incubator).
        - Class-File API (preview).
        - Statements before super(...) (preview).
        - Unnamed Variables and Patterns became permanent.


        Java 23 (2024)

        Important additions:

        - Markdown Documentation Comments (preview).
        - Module Import Declarations (preview).
        - Primitive Types in Patterns (preview).
        - Stream Gatherers (preview).
        - Class-File API (second preview).


        Java 24 (2025)

        Important additions:

        - Stream Gatherers became permanent.
        - Class-File API became permanent.
        - Flexible Constructor Bodies (preview).
        - Module Import Declarations (preview).
        - Simple Source Files and Instance Main Methods (preview).
        - Compact Object Headers (experimental).


        Java 25 (2025) - LTS

        Important additions:

        - Module Import Declarations became permanent.
        - Compact Source Files and Instance Main Methods
          became permanent.
        - Flexible Constructor Bodies became permanent.
        - Scoped Values became permanent.
        - Stable Values (preview).
        - Primitive Types in Patterns, instanceof and switch
          (preview).
        - Structured Concurrency (fifth preview).
        - PEM Encodings of Cryptographic Objects.

        Note:
        Java 25 is an important LTS release.


        Java 26 (2026)

        Important additions include:

        - HTTP/3 support in the HTTP Client API.
        - Structured Concurrency (sixth preview).
        - Primitive Types in Patterns, instanceof and switch
          (second preview).
        - G1 Garbage Collector improvements.
        - Additional performance and security improvements.


        Java 27 (2026)

        Important additions include:

        - Post-Quantum Hybrid Key Exchange for TLS 1.3.
        - Primitive Types in Patterns, instanceof and switch
          (third preview).
        - Structured Concurrency (seventh preview).
        - Additional JVM, security and library improvements.

        Important:
        Java 27 was released in September 2026.
        It is a non-LTS feature release.


        ============================================================
                    15. JAVA RELEASE CYCLE
        ============================================================

        Before Java 9:

        Java did not follow the current predictable
        six-month feature release schedule.

        Major releases were separated by varying periods.

        Examples:

        Java 7 -> 2011
        Java 8 -> 2014

        After Java 9:

        Java adopted a six-month feature release cadence.

        The planned release months are:

        - March
        - September

        Example:

        Java 17 -> September 2021
        Java 18 -> March 2022
        Java 19 -> September 2022
        Java 20 -> March 2023
        Java 21 -> September 2023

        The pattern continues:

        Java 25 -> September 2025
        Java 26 -> March 2026
        Java 27 -> September 2026

        Important:

        - Feature releases happen approximately every six months.
        - Each feature release receives new language,
          JVM or library improvements.
        - Feature releases can contain preview features.
        - Release schedules are planned, not a guarantee
          that every feature will become permanent.


        ============================================================
                    16. LTS (LONG-TERM SUPPORT)
        ============================================================

        LTS stands for Long-Term Support.

        An LTS release is a Java version for which a vendor
        provides an extended support and update lifecycle
        compared with ordinary feature releases.

        Why are LTS releases important?

        Production applications often require:

        - Long-term stability.
        - Security updates.
        - Bug fixes.
        - Predictable maintenance.
        - Compatibility over several years.
        - Reduced upgrade frequency.

        Organizations often prefer LTS releases for
        long-running production systems.

        Important:

        - LTS is a support designation, not a language feature.
        - Not every Java version is an LTS release.
        - Support duration depends on the vendor and its
          support policy.
        - Different vendors can provide different support
          periods for the same Java version.


        A. IMPORTANT LTS RELEASES

        Important LTS releases:

        Java 8  -> 2014
        Java 11 -> 2018
        Java 17 -> 2021
        Java 21 -> 2023
        Java 25 -> 2025

        Historical Oracle LTS designations also include
        older releases such as Java 1.0, 1.1, 2, 3, 4,
        5, 6 and 7 under its historical support roadmap.

        Important:
        LTS designation and support availability are not
        identical for every JDK vendor.


        B. LTS vs NON-LTS

        LTS:

        - Intended for longer support lifecycles.
        - Often preferred for production environments.
        - Receives extended vendor support options.
        - Allows organizations to reduce upgrade frequency.

        Non-LTS:

        - Part of the regular six-month feature release cycle.
        - Provides access to newer features sooner.
        - Usually has a shorter support lifecycle.
        - Useful for evaluating and adopting recent improvements.

        Example:

        Java 21 -> LTS
        Java 22 -> Non-LTS
        Java 23 -> Non-LTS
        Java 24 -> Non-LTS
        Java 25 -> LTS
        Java 26 -> Non-LTS
        Java 27 -> Non-LTS


        C. LTS RELEASE INTERVAL

        Since Java 25, Oracle has moved to a planned
        two-year LTS cadence.

        Examples:

        Java 21 -> September 2023
        Java 25 -> September 2025
        Java 29 -> Planned September 2027

        Important:

        This describes Oracle's planned LTS schedule.
        It should not be interpreted as a guarantee that
        every Java vendor follows exactly the same policy.


        ============================================================
                    17. JAVA VERSION NUMBERING
        ============================================================

        Java uses version numbers to identify releases
        and updates.

        Example:

        21.0.7

        21 -> Major feature release.
        0  -> Interim version field.
        7  -> Update number.

        Another example:

        25.0.4

        25 -> Major feature release.
        0  -> Interim version field.
        4  -> Update number.

        Important:

        Modern Java release versions are commonly identified
        by their major version number.

        Examples:

        Java 8
        Java 11
        Java 17
        Java 21
        Java 25
        Java 27

        Historical naming:

        Java 1.5 was commonly called Java 5.
        Java 1.6 was commonly called Java 6.
        Java 1.7 was commonly called Java 7.
        Java 1.8 was commonly called Java 8.

        Java 9 introduced the modern version numbering
        convention.


        ============================================================
                    18. IMPORTANT JAVA VERSION COMPARISONS
        ============================================================

        JAVA 8 vs JAVA 11

        Java 8:
        - Lambda Expressions.
        - Stream API.
        - Optional API.
        - New Date and Time API.

        Java 11:
        - Standard HTTP Client API.
        - Additional String methods.
        - Single-file source-code execution.
        - Java EE and CORBA modules removed from the JDK.


        JAVA 11 vs JAVA 17

        Java 17:
        - Sealed Classes became permanent.
        - Pattern Matching for instanceof became permanent.
        - Strong encapsulation of JDK internals.
        - Numerous JVM and language improvements.


        JAVA 17 vs JAVA 21

        Java 21:
        - Virtual Threads became permanent.
        - Record Patterns became permanent.
        - Pattern Matching for switch became permanent.
        - Sequenced Collections.
        - Additional concurrency improvements.


        JAVA 21 vs JAVA 25

        Java 25:
        - Compact Source Files and Instance Main Methods
          became permanent.
        - Module Import Declarations became permanent.
        - Flexible Constructor Bodies became permanent.
        - Scoped Values became permanent.
        - Additional JVM and API improvements.


        JAVA 25 vs JAVA 27

        Java 27:
        - New feature-release improvements.
        - Post-Quantum Hybrid Key Exchange for TLS 1.3.
        - Additional preview and runtime improvements.

        Java 25 remains an LTS release.
        Java 27 is a non-LTS feature release.


        ============================================================
                    19. WHICH JAVA VERSION SHOULD WE LEARN?
        ============================================================

        Our learning baseline:

        Java 21.

        Reasons:

        - Java 21 is an LTS release.
        - It includes modern language features.
        - It includes permanent Virtual Threads.
        - It includes permanent Record Patterns.
        - It includes permanent Pattern Matching for switch.
        - It is widely relevant to modern Java development.

        Our learning approach:

        1. Learn the fundamental Java concepts using Java 21.
        2. Understand important features introduced in earlier
           releases.
        3. Identify the version in which a feature was introduced.
        4. Learn whether a feature is permanent, preview,
           incubator, deprecated or removed.
        5. Study newer Java features when they are relevant.

        Important:

        Java 21 is our learning baseline, not necessarily
        the latest Java version.

        The latest feature release as of November 2026
        is Java 27.

        The latest LTS release is Java 25.


        ============================================================
                    20. IMPORTANT INTERVIEW POINTS
        ============================================================

        1. Java was publicly introduced in 1995.
        2. JDK 1.0 was released in 1996.
        3. Java 5 introduced Generics and Annotations.
        4. Java 7 introduced try-with-resources.
        5. Java 8 introduced Lambda Expressions and Stream API.
        6. Java 9 introduced the Module System.
        7. Java 10 introduced var for local-variable type inference.
        8. Java 11 introduced the standard HTTP Client API.
        9. Java 14 made Switch Expressions permanent.
        10. Java 16 made Records permanent.
        11. Java 17 made Sealed Classes permanent.
        12. Java 21 made Virtual Threads permanent.
        13. Java 21 is an LTS release.
        14. Java 25 is an LTS release.
        15. Java 27 is a non-LTS feature release.
        16. Java adopted a six-month release cadence after Java 9.
        17. Feature releases are scheduled around March and September.
        18. LTS stands for Long-Term Support.
        19. LTS is a vendor support designation.
        20. LTS and non-LTS releases have different support lifecycles.
        21. Java 29 is the next planned Oracle LTS release.
        22. Java 21 is our learning baseline.
        23. Preview features are not necessarily permanent features.
        24. An incubator feature is an experimental API feature
            intended for evaluation and feedback.


        
        ============================================================
                    21. JAVA VS OTHER PROGRAMMING LANGUAGES
        ============================================================

        Java is a general-purpose programming language.
        It shares some characteristics with other languages,
        but its design, execution model and ecosystem differ.


        A. JAVA VS C

        C:

        - Procedural programming language.
        - Supports structured programming.
        - Uses pointers explicitly.
        - Supports manual memory management.
        - Compiles source code into native machine code.
        - Does not provide built-in garbage collection.
        - Commonly used in operating systems, embedded systems
          and system-level programming.

        Java:

        - Class-based, object-oriented language.
        - Supports procedural-style programming within classes.
        - Uses references instead of exposing direct pointer
          arithmetic to ordinary Java code.
        - Provides automatic memory management through GC.
        - Normally compiles source code into bytecode.
        - Bytecode executes on a JVM.
        - Commonly used in enterprise, backend and Android-related
          development (Android uses its own runtime and toolchain).

        Main distinction:

        C generally compiles to native machine code for a target
        platform, whereas Java normally compiles to JVM bytecode
        that can run on compatible JVM implementations.


        B. JAVA VS C++

        C++:

        - Supports procedural, object-oriented and generic
          programming.
        - Supports multiple inheritance of classes.
        - Supports pointers and pointer arithmetic.
        - Supports manual memory management and RAII.
        - Supports operator overloading.
        - Compiles primarily to native machine code.
        - Does not require a JVM.

        Java:

        - Primarily class-based and object-oriented.
        - Does not support multiple inheritance of classes.
        - Does not expose pointer arithmetic in ordinary Java.
        - Provides automatic garbage collection.
        - Does not support user-defined operator overloading.
        - Normally compiles to JVM bytecode.
        - Requires a compatible Java runtime to execute
          ordinary Java bytecode.

        Important:

        Java does support method overloading.
        Java does not support user-defined operator overloading.

        Java supports multiple inheritance of type through
        interfaces, but not multiple inheritance of classes.


        C. JAVA VS PYTHON

        Python:

        - High-level, general-purpose language.
        - Dynamically typed.
        - Supports multiple programming paradigms.
        - Uses indentation to define code blocks.
        - Commonly executed by a Python interpreter/runtime.
        - Has extensive libraries for automation, AI,
          data science and scripting.

        Java:

        - Statically typed.
        - Class-based and object-oriented.
        - Uses braces to define code blocks.
        - Normally compiles source code into bytecode.
        - Uses JVM execution and runtime optimization.
        - Widely used in enterprise and backend development.

        Important:

        Python is not simply an interpreted language in every
        implementation detail. Common Python implementations
        also compile source code into an intermediate form.

        Similarly, Java is not simply a compiled language:
        its JVM can interpret bytecode and compile code using JIT.


        D. JAVA VS JAVASCRIPT

        JavaScript:

        - Dynamically typed.
        - Prototype-based object model.
        - Supports multiple programming paradigms.
        - Commonly used for browser-side development.
        - Also used on servers through runtimes such as Node.js.
        - Commonly executed by JavaScript engines.

        Java:

        - Statically typed.
        - Class-based object model.
        - Uses JVM bytecode for its normal execution model.
        - Commonly used for backend, enterprise and general-purpose
          application development.

        Important:

        Java and JavaScript are different programming languages.

        Their similar names do not mean that they share the
        same language design or runtime.


        E. COMPARISON TABLE

        Feature             C          C++        Java       Python
        ------------------------------------------------------------
        Typing              Static     Static     Static     Dynamic
        Main style          Procedural Multi-     OOP and    Multi-
                                      paradigm   multi-     paradigm
                                                 paradigm
        Compilation         Native     Native     Bytecode   Impl.-dependent
        Memory management   Manual     Manual/    Automatic Automatic/
                                      RAII       GC         GC
        Pointer arithmetic  Yes        Yes        No         No
        Multiple class      N/A        Yes        No         No
        inheritance
        Operator overload   No         Yes        No*        Yes

        *Java does not support user-defined operator overloading.
         The + operator is specially defined for String concatenation
         and arithmetic operations.

        Note:

        This table gives a simplified comparison. Individual
        implementations and language features can have exceptions.


        ============================================================
                    22. STATIC TYPING VS DYNAMIC TYPING
        ============================================================

        A programming language's type system determines how
        types are associated with values and variables and
        when type rules are checked.

        A. STATICALLY TYPED LANGUAGES

        In a statically typed language, type checking is
        primarily performed before program execution,
        typically during compilation.

        Java example:

        int age = 22;

        String name = "Vinay";

        age = "Hello";  // Compilation error

        The compiler detects that a String cannot be assigned
        to an int variable.

        Examples of statically typed languages:

        - Java.
        - C.
        - C++.
        - Go.
        - Rust.
        - Kotlin.


        B. DYNAMICALLY TYPED LANGUAGES

        In a dynamically typed language, type checking is
        primarily performed during program execution.

        Python example:

        value = 10;
        value = "Hello";

        The same variable name can refer to values of
        different types at different times.

        Examples:

        - Python.
        - JavaScript.
        - Ruby.
        - PHP (with dynamic typing in common usage).


        C. IMPORTANT DISTINCTION

        Static typing does not mean that values cannot change.

        Example:

        int number = 10;
        number = 20;

        The value changes, but the variable's declared type
        remains int.

        Dynamic typing does not mean that a language has
        no type system.

        Python and JavaScript both have type systems.
        Their type checks are performed differently.


        ============================================================
                    23. COMPILED VS INTERPRETED LANGUAGES
        ============================================================

        These terms describe different ways programs can
        be translated and executed.

        A. COMPILATION

        Compilation is the process of translating source code
        into another representation, such as:

        - Machine code.
        - Bytecode.
        - Intermediate representation.

        A compiler performs this translation.

        General flow:

        Source Code
             |
             v
          Compiler
             |
             v
        Target Representation


        B. INTERPRETATION

        Interpretation is the process of executing a program
        by processing its instructions through an interpreter
        or runtime environment.

        An interpreter may execute instructions directly or
        use an intermediate representation.

        Interpretation does not necessarily mean that source
        code is executed one line at a time.


        C. HOW JAVA EXECUTES A PROGRAM

        Java normally uses both compilation and runtime
        execution techniques.

        Step 1:

        Developer writes source code.

        Example:

        Hello.java

        Step 2:

        Java compiler (javac) compiles source code.

        Hello.java
             |
             v
           javac
             |
             v
        Hello.class

        The .class file contains JVM bytecode.

        Step 3:

        JVM loads and executes the bytecode.

        The JVM may:

        - Interpret bytecode.
        - Compile frequently executed code into native
          machine code using Just-In-Time compilation.
        - Apply runtime optimizations.

        General execution flow:

        Java Source Code
                |
                v
              javac
                |
                v
           Bytecode (.class)
                |
                v
                JVM
                |
          +-----+------+
          |            |
          v            v
       Interpreter    JIT Compiler
          |            |
          +-----+------+
                |
                v
        Native Machine Execution


        D. JUST-IN-TIME COMPILATION (JIT)

        JIT stands for Just-In-Time compilation.

        It is a runtime compilation technique.

        The JVM can identify frequently executed code
        and compile it into native machine code.

        Benefits:

        - Improves execution performance.
        - Allows runtime profiling.
        - Enables optimizations based on actual execution.
        - Avoids repeatedly interpreting some frequently
          executed code.

        Important:

        JIT compilation is one of the reasons Java can achieve
        high performance.

        The exact compilation strategy depends on the JVM
        implementation and runtime configuration.


        E. AHEAD-OF-TIME COMPILATION (AOT)

        AOT stands for Ahead-Of-Time compilation.

        It means compiling code before normal application
        execution rather than waiting for runtime JIT compilation.

        AOT techniques can be used in Java environments.

        Example:

        GraalVM Native Image can compile Java applications
        into native executables.

        Important:

        Java is not restricted to only JIT compilation.


        ============================================================
                    24. JAVA LANGUAGE VS JAVA PLATFORM
        ============================================================

        These two terms are related, but they are not identical.


        A. JAVA PROGRAMMING LANGUAGE

        Java language defines:

        - Syntax.
        - Keywords.
        - Data types.
        - Variables.
        - Operators.
        - Expressions.
        - Statements.
        - Classes and objects.
        - Interfaces.
        - Inheritance.
        - Methods.
        - Exception handling.
        - Generics.
        - Language rules.

        It defines how developers write Java programs.

        Example:

        public class Hello {
            public static void main(String[] args) {
                System.out.println("Hello Java");
            }
        }


        B. JAVA PLATFORM

        The Java platform provides the environment and
        capabilities required to execute Java applications.

        It includes:

        - Java Virtual Machine (JVM).
        - Standard Java APIs and libraries.
        - Runtime support.
        - Development and execution tools in the JDK.

        The Java platform provides services such as:

        - Memory management.
        - Class loading.
        - Bytecode execution.
        - Garbage collection.
        - Thread management.
        - Security-related runtime checks.
        - Standard library functionality.

        Important:

        The Java language defines how source code is written.

        The Java platform provides the runtime and libraries
        used to execute Java applications.


        C. JAVA LANGUAGE VS JAVA PLATFORM

        Java Language:
        - Defines programming syntax and semantics.
        - Defines language-level constructs.
        - Used to write source code.

        Java Platform:
        - Provides execution environment.
        - Provides standard APIs.
        - Provides JVM and runtime services.
        - Executes compiled Java bytecode.


        ============================================================
                    25. OVERVIEW OF JVM, JRE AND JDK
        ============================================================

        These three terms are fundamental to understanding
        the Java platform.

        A. JVM (JAVA VIRTUAL MACHINE)

        JVM stands for Java Virtual Machine.

        It is an abstract machine specified by the Java
        Virtual Machine Specification.

        Its responsibilities include:

        - Loading class files.
        - Verifying bytecode.
        - Executing bytecode.
        - Managing runtime memory.
        - Supporting garbage collection.
        - Managing threads.
        - Providing runtime services.

        Different JVM implementations can exist for
        different operating systems and hardware platforms.


        B. JRE (JAVA RUNTIME ENVIRONMENT)

        JRE stands for Java Runtime Environment.

        Traditionally, the JRE refers to the runtime
        components required to run Java applications.

        It includes:

        - JVM.
        - Required runtime libraries.
        - Supporting runtime files.

        Important:

        In modern Java distributions, a separately
        downloadable JRE is not always provided.

        Runtime images can also be created using tools
        such as jlink.


        C. JDK (JAVA DEVELOPMENT KIT)

        JDK stands for Java Development Kit.

        It provides tools required to develop Java applications.

        It includes:

        - Java compiler (javac).
        - Java launcher (java).
        - JVM and runtime components.
        - Java API documentation tool (javadoc).
        - Debugging and diagnostic tools.
        - Other development utilities.

        Important commands:

        javac -> Compiles Java source code.
        java  -> Launches Java applications.
        javadoc -> Generates API documentation.
        jshell -> Interactive Java shell.


        D. RELATIONSHIP BETWEEN JVM, JRE AND JDK

        Conceptually:

        JDK
         |
         +---- Development Tools
         |
         +---- Runtime Components
                    |
                    +---- JVM
                    |
                    +---- Runtime Libraries

        Important:

        This is a conceptual relationship, not a strict
        directory layout for every modern JDK distribution.

        We will study JVM internals and memory management
        in their dedicated folders.


        ============================================================
                    26. JAVA ECOSYSTEM
        ============================================================

        An ecosystem is the collection of technologies,
        tools, libraries, implementations and communities
        surrounding a programming language or platform.

        The Java ecosystem includes:


        A. JDK DISTRIBUTIONS

        A JDK distribution is a packaged implementation
        of the Java Development Kit.

        Different organizations provide JDK distributions.

        Examples:

        - Oracle JDK.
        - Eclipse Temurin.
        - Amazon Corretto.
        - Azul Zulu.
        - Microsoft Build of OpenJDK.
        - Red Hat build of OpenJDK.

        These distributions provide Java development
        and runtime components.

        Important:

        OpenJDK is an open-source implementation and
        development project for the Java platform.

        Oracle JDK is an Oracle-provided JDK distribution.

        JDK distributions can differ in:

        - Licensing.
        - Support policies.
        - Update schedules.
        - Additional tools or patches.
        - Platform availability.

        They generally aim to maintain compatibility
        with the applicable Java specifications.


        B. JAVA VIRTUAL MACHINE IMPLEMENTATIONS

        Examples:

        - HotSpot JVM.
        - OpenJ9.

        Different JVM implementations can execute
        compatible Java bytecode.

        They can differ in:

        - Memory management.
        - Garbage collection.
        - JIT compilation.
        - Startup performance.
        - Runtime performance.
        - Resource consumption.


        C. JAVA LIBRARIES AND FRAMEWORKS

        Java developers use libraries and frameworks
        to build applications.

        Examples of libraries and frameworks include:

        - JUnit.
        - Mockito.
        - Log4j.
        - Hibernate.
        - Spring Framework.

        Important:

        A library provides reusable functionality.

        A framework provides a structure and conventions
        for building applications and often controls
        application execution flow.

        These are part of the broader Java ecosystem,
        not part of the Java language itself.

        Our current Core Java learning scope focuses on
        the language and Java SE platform, not frameworks.


        D. JAVA DEVELOPMENT TOOLS

        Common development tools include:

        - IntelliJ IDEA.
        - Eclipse IDE.
        - Visual Studio Code.
        - Apache Maven.
        - Gradle.
        - Git.
        - JShell.

        These tools support development, building,
        testing, debugging and project management.

        They are not all included in the Java language
        or the JDK.


        ============================================================
                    27. IMPORTANT INTERVIEW POINTS
        ============================================================

        1. Java is statically typed.
        2. Python and JavaScript are dynamically typed.
        3. Java does not support pointer arithmetic.
        4. Java does not support multiple inheritance of classes.
        5. Java supports multiple inheritance of type through
           interfaces.
        6. Java supports method overloading.
        7. Java does not support user-defined operator overloading.
        8. Java normally compiles source code into bytecode.
        9. JVM can interpret bytecode and compile it using JIT.
        10. JIT stands for Just-In-Time compilation.
        11. AOT stands for Ahead-Of-Time compilation.
        12. Java language and Java platform are different concepts.
        13. JVM executes Java bytecode.
        14. JRE traditionally provides runtime components.
        15. JDK provides development tools and runtime components.
        16. OpenJDK is an open-source Java implementation project.
        17. Oracle JDK is an Oracle-provided JDK distribution.
        18. Different JVM implementations can execute compatible
            Java bytecode.
        19. Java and JavaScript are different languages.
        20. Java is not purely an interpreted language.
        21. Java is not purely a native-compiled language.
        22. Java is a combination of compile-time and runtime
            execution techniques.


       
        ============================================================
                    28. JAVA STANDARDS AND GOVERNANCE
        ============================================================

        Java is an evolving platform. Its language features,
        APIs and runtime specifications are developed through
        formal processes and open-source development.

        Important organizations and terms include:

        A. JCP (JAVA COMMUNITY PROCESS)

        JCP stands for Java Community Process.

        It is the formal process used to develop and revise
        Java specifications.

        Its purpose includes:

        - Defining new Java platform features.
        - Developing Java specifications.
        - Reviewing proposed changes.
        - Encouraging community participation.
        - Maintaining compatibility and consistency.

        The JCP provides a process through which organizations
        and individuals can participate in Java specification
        development.


        B. JSR (JAVA SPECIFICATION REQUEST)

        JSR stands for Java Specification Request.

        A JSR is a formal proposal to develop a new Java
        specification or make changes to an existing one.

        Examples of work that can be described through JSRs:

        - New Java language features.
        - New Java APIs.
        - Changes to the Java platform.
        - New platform specifications.

        A JSR typically goes through review and approval
        stages under the JCP.

        Important:

        JCP = The process.
        JSR = A specification request within that process.


        C. JEP (JDK ENHANCEMENT PROPOSAL)

        JEP stands for JDK Enhancement Proposal.

        JEPs are used to propose, discuss and track enhancements
        to the JDK.

        They can describe:

        - New language features.
        - JVM improvements.
        - New APIs.
        - Performance improvements.
        - Security enhancements.
        - Deprecations and removals.
        - Other JDK changes.

        Example:

        JEP 444 -> Virtual Threads.

        Virtual Threads became a permanent feature in Java 21.

        Important:

        JEP = Proposal and tracking mechanism for JDK enhancements.


        D. OPENJDK

        OpenJDK is the open-source project and reference
        implementation ecosystem for developing the Java platform.

        It provides:

        - Java source code.
        - JDK implementation work.
        - JVM implementation work.
        - Java libraries.
        - Development and testing infrastructure.

        Many Java distributions are built from OpenJDK
        source code, sometimes with additional changes
        and vendor-specific components.

        Important:

        OpenJDK is not a programming language.
        OpenJDK is not an IDE.
        OpenJDK is not a framework.

        It is an open-source Java development project.


        E. ORACLE AND THE JAVA ECOSYSTEM

        Oracle is a major organization involved in Java.

        Oracle:

        - Maintains and develops Java technologies.
        - Provides Oracle JDK distributions.
        - Participates in Java specification development.
        - Provides commercial Java support offerings.
        - Contributes to OpenJDK development.

        Important:

        Java is not exclusively owned or developed by
        one organization in the sense of having only
        one implementation or distribution.

        Java has an ecosystem involving multiple vendors,
        contributors and implementations.


        F. RELATIONSHIP BETWEEN IMPORTANT TERMS

        JCP:
        Formal Java specification development process.

        JSR:
        Formal specification request.

        JEP:
        Proposal and tracking mechanism for JDK enhancements.

        OpenJDK:
        Open-source Java development project.

        Oracle JDK:
        Oracle's JDK distribution.

        These terms are related but are not interchangeable.


        ============================================================
                    29. WHERE IS JAVA USED?
        ============================================================

        Java is a general-purpose programming language.
        It is used to develop different kinds of applications.


        A. ENTERPRISE APPLICATIONS

        Java is widely used in enterprise software.

        Examples:

        - Banking applications.
        - Insurance systems.
        - Business management systems.
        - Enterprise resource planning systems.
        - Large organizational applications.

        Reasons:

        - Mature ecosystem.
        - Strong type system.
        - Concurrency support.
        - Security APIs.
        - Long-term maintenance options.
        - Large library ecosystem.


        B. BACKEND DEVELOPMENT

        Java is commonly used to develop backend systems.

        Examples:

        - REST APIs.
        - Authentication services.
        - Payment services.
        - Business logic.
        - Database-backed applications.
        - Distributed services.

        Java provides networking APIs, concurrency support
        and libraries that help build backend applications.


        C. ANDROID DEVELOPMENT

        Java has historically been an important language
        for Android development.

        Android applications can use Java source code.

        Important:

        Android does not execute ordinary Java bytecode
        using a standard desktop/server JVM.

        Android uses its own runtime and execution toolchain,
        including DEX bytecode and ART.

        Kotlin is also widely used for Android development.


        D. DESKTOP APPLICATIONS

        Java can be used to develop desktop applications.

        Technologies include:

        - Swing.
        - JavaFX.

        Examples:

        - Desktop management systems.
        - Utility applications.
        - Data-entry applications.
        - Cross-platform desktop software.


        E. WEB APPLICATIONS

        Java can be used for server-side web applications.

        Examples:

        - E-commerce applications.
        - Banking portals.
        - Business websites.
        - Web services.
        - Enterprise portals.

        Java provides networking and HTTP-related APIs.
        Enterprise frameworks can provide additional
        application-development capabilities.


        F. BIG DATA AND DATA PROCESSING

        Java is used in parts of the big data ecosystem.

        Examples include technologies and systems such as:

        - Apache Hadoop.
        - Apache Kafka.
        - Apache Spark (which also supports other languages).

        Java can be used to build data-processing applications
        and distributed services.


        G. CLOUD AND DISTRIBUTED SYSTEMS

        Java is used in:

        - Cloud applications.
        - Microservices.
        - Distributed systems.
        - Backend services.
        - Enterprise infrastructure.

        Java provides networking, concurrency and runtime
        capabilities useful in these environments.


        H. EMBEDDED SYSTEMS

        Java has been used in embedded environments.

        Examples:

        - Smart devices.
        - Industrial devices.
        - Specialized hardware systems.
        - Java Card applications.

        The exact Java platform and runtime depend on
        the device's capabilities and requirements.


        ============================================================
                    30. IMPORTANT JAVA TERMINOLOGY
        ============================================================

        1. SOURCE CODE

        Human-readable program code written by a developer.

        Example:
        Hello.java


        2. BYTECODE

        An intermediate instruction format generated by
        the Java compiler.

        It is stored in .class files and executed by a JVM.


        3. MACHINE CODE

        Instructions that can be executed by a processor.

        JIT compilation can translate bytecode into
        native machine code at runtime.


        4. COMPILER

        A program that translates source code into
        another representation.

        Java compiler:
        javac


        5. INTERPRETER

        A program or runtime component that executes
        instructions through interpretation.

        A JVM can interpret bytecode.


        6. RUNTIME

        The environment and supporting components used
        while an application executes.


        7. API

        API stands for Application Programming Interface.

        It is a defined interface through which software
        components interact.

        Java provides standard APIs for:

        - Collections.
        - Input and output.
        - Networking.
        - Date and time.
        - Concurrency.
        - Security.


        8. LIBRARY

        A collection of reusable code that developers
        can use in their applications.


        9. FRAMEWORK

        A reusable structure that provides conventions
        and controls or coordinates parts of an application's
        execution.


        10. PLATFORM

        A combination of software and runtime capabilities
        that provides an environment for applications.


        11. IMPLEMENTATION

        A concrete realization of a specification.

        Example:

        Different JVM implementations can implement
        the JVM specification.


        12. SPECIFICATION

        A formal document defining expected behavior,
        interfaces or requirements.

        Example:

        Java Language Specification (JLS).
        Java Virtual Machine Specification (JVMS).


        13. GARBAGE COLLECTION

        Automatic memory management that reclaims memory
        occupied by objects that are no longer reachable.


        14. JIT

        Just-In-Time compilation.

        A runtime technique that compiles code into
        native machine code during execution.


        15. AOT

        Ahead-Of-Time compilation.

        Compilation performed before normal application
        execution.


        ============================================================
                    31. IMPORTANT JAVA SPECIFICATIONS
        ============================================================

        Java has formal specifications that define how
        different parts of the platform should behave.

        A. JLS (JAVA LANGUAGE SPECIFICATION)

        The JLS defines the Java programming language.

        It describes:

        - Syntax.
        - Types.
        - Variables.
        - Expressions.
        - Statements.
        - Classes.
        - Interfaces.
        - Methods.
        - Exceptions.
        - Generics.
        - Other language rules.

        It specifies how valid Java programs should behave
        at the language level.


        B. JVMS (JAVA VIRTUAL MACHINE SPECIFICATION)

        The JVMS defines the Java Virtual Machine.

        It describes:

        - Class file format.
        - Runtime data areas.
        - Instruction set.
        - Class loading and linking.
        - Verification.
        - Execution behavior.

        It specifies the behavior expected from a JVM
        implementation.


        C. JAVA SE API SPECIFICATION

        The Java SE API specification documents the standard
        APIs provided by the Java SE platform.

        Examples:

        - java.lang
        - java.util
        - java.io
        - java.net
        - java.time
        - java.sql
        - java.util.concurrent

        These APIs provide standard functionality that
        Java applications can use.


        D. IMPORTANT DISTINCTION

        JLS:
        Defines the Java language.

        JVMS:
        Defines the JVM.

        Java SE API:
        Defines the standard Java SE APIs.

        These specifications work together to define
        important parts of the Java platform.


        ============================================================
                    32. COMMON JAVA MISCONCEPTIONS
        ============================================================

        1. JAVA AND JAVASCRIPT ARE THE SAME.

        Incorrect.

        They are different programming languages.


        2. JAVA IS PURELY OBJECT-ORIENTED.

        Incorrect.

        Java is primarily object-oriented, but it also
        contains primitive types and static members.


        3. JAVA IS ONLY AN INTERPRETED LANGUAGE.

        Incorrect.

        Java normally compiles source code into bytecode.
        The JVM can interpret bytecode and use JIT compilation.


        4. JAVA IS ONLY A COMPILED LANGUAGE.

        Incorrect.

        Java uses compilation and runtime execution techniques.


        5. JAVA IS PLATFORM INDEPENDENT WITHOUT ANY REQUIREMENTS.

        Incorrect.

        Java bytecode requires a compatible runtime.
        Native libraries and platform-specific dependencies
        can reduce portability.


        6. JVM, JRE AND JDK ARE THE SAME.

        Incorrect.

        They represent different concepts and responsibilities.


        7. JAVA DOES NOT SUPPORT POINTERS.

        This statement needs clarification.

        Java uses references to access objects.

        However, ordinary Java code does not expose
        pointer arithmetic or direct memory-address
        manipulation like C and C++.

        Java also has specialized low-level capabilities
        such as the Foreign Function and Memory API.


        8. JAVA DOES NOT SUPPORT MULTIPLE INHERITANCE.

        This statement is incomplete.

        Java does not support multiple inheritance of classes.

        It supports multiple inheritance of type through
        interfaces.


        9. JAVA IS ALWAYS SLOWER THAN C OR C++.

        Not necessarily.

        Performance depends on:

        - Application workload.
        - Runtime implementation.
        - Memory behavior.
        - JIT optimizations.
        - Algorithm efficiency.
        - System architecture.

        Java can achieve high performance in many workloads.


        10. GARBAGE COLLECTION MEANS MEMORY LEAKS CANNOT OCCUR.

        Incorrect.

        Memory leaks can still happen when objects remain
        reachable even though the application no longer
        needs them.


        11. JAVA SOURCE CODE RUNS DIRECTLY ON EVERY OPERATING SYSTEM.

        Incorrect.

        Java source code must be compiled or otherwise
        processed by a suitable toolchain.

        The resulting bytecode requires a compatible JVM.


        12. EVERY JAVA VERSION IS AN LTS RELEASE.

        Incorrect.

        Java has both LTS and non-LTS feature releases.


        13. ALL JAVA DISTRIBUTIONS ARE IDENTICAL.

        Incorrect.

        They aim for compatibility but can differ in
        support, licensing, packaging and additional components.


        ============================================================
                    33. FINAL INTRODUCTION TO JAVA CHECKLIST
        ============================================================

        Use this checklist to verify your understanding
        of the Introduction to Java folder.

        [ ] What is Java?
        [ ] Why was Java developed?
        [ ] Who developed Java?
        [ ] What is the history of Java?
        [ ] What does WORA mean?
        [ ] What are the main features of Java?
        [ ] What are Java editions?
        [ ] What is Java SE?
        [ ] What is Java EE and Jakarta EE?
        [ ] What is Java ME?
        [ ] What is Java Card?
        [ ] What are Java versions?
        [ ] What is the Java release cycle?
        [ ] What is an LTS release?
        [ ] What is a non-LTS release?
        [ ] What is the difference between Java 8 and Java 11?
        [ ] What are the major features of Java 17?
        [ ] What are the major features of Java 21?
        [ ] What is the difference between Java and C?
        [ ] What is the difference between Java and C++?
        [ ] What is the difference between Java and Python?
        [ ] What is the difference between Java and JavaScript?
        [ ] What is static typing?
        [ ] What is dynamic typing?
        [ ] What is compilation?
        [ ] What is interpretation?
        [ ] How does Java execute a program?
        [ ] What is bytecode?
        [ ] What is machine code?
        [ ] What is JIT compilation?
        [ ] What is AOT compilation?
        [ ] What is the difference between Java language and platform?
        [ ] What is JVM?
        [ ] What is JRE?
        [ ] What is JDK?
        [ ] What is OpenJDK?
        [ ] What is a JDK distribution?
        [ ] What is the Java ecosystem?
        [ ] What is JCP?
        [ ] What is JSR?
        [ ] What is JEP?
        [ ] What is JLS?
        [ ] What is JVMS?
        [ ] Where is Java used?
        [ ] What are the common Java misconceptions?


        ============================================================
                    34. INTRODUCTION TO JAVA - FINAL SUMMARY
        ============================================================

        Java is a high-level, general-purpose, statically typed,
        class-based programming language and platform.

        It was developed at Sun Microsystems and publicly
        introduced in 1995.

        Java source code is normally compiled into bytecode.

        The bytecode is executed by a compatible JVM.

        Java provides:

        - Object-oriented programming.
        - Platform-independent bytecode.
        - Automatic memory management.
        - Exception handling.
        - Multithreading and concurrency support.
        - Standard libraries and APIs.
        - Runtime security mechanisms.
        - Runtime performance optimizations.

        Java is used in:

        - Enterprise applications.
        - Backend development.
        - Android development.
        - Desktop applications.
        - Web applications.
        - Distributed systems.
        - Data-processing systems.
        - Embedded environments.

        Java continues to evolve through its release cycle,
        specifications, open-source development and vendor
        distributions.

        Our learning baseline is Java 21.


        ============================================================
                    END OF INTRODUCTION TO JAVA
        ============================================================

*/




    */

    public static void main(String[] args) {

        System.out.println("Introduction to Java - Practice Area");

    }
}
