
public class SpringAOP {

    /*
     * ================================================================
     * SPRING CORE - 07: ASPECT-ORIENTED PROGRAMMING (AOP)
     * ================================================================
     *
     * CONTENTS
     *
     * 1.  Introduction to AOP
     * 2.  Problems with Traditional Programming
     * 3.  Cross-Cutting Concerns
     * 4.  What Is Aspect-Oriented Programming?
     * 5.  AOP Terminology
     * 6.  Aspect
     * 7.  Join Point
     * 8.  Pointcut
     * 9.  Advice
     * 10. Target Object
     * 11. AOP Proxy
     * 12. Weaving
     * 13. Spring AOP Architecture
     * 14. Types of Advice
     * 15. @Before
     * 16. @AfterReturning
     * 17. @AfterThrowing
     * 18. @After
     * 19. @Around
     * 20. Pointcut Expressions
     * 21. Pointcut Designators
     * 22. Combining Pointcuts
     * 23. Reusable Named Pointcuts
     * 24. Accessing Method Arguments
     * 25. Accessing Return Values
     * 26. Accessing Exceptions
     * 27. Ordering Multiple Aspects
     * 28. JDK Dynamic Proxies
     * 29. CGLIB Proxies
     * 30. Proxy-Based Execution Flow
     * 31. Self-Invocation Problem
     * 32. Spring AOP Limitations
     * 33. AOP and Spring Boot
     * 34. Real-World Applications
     * 35. Common Mistakes
     * 36. Interview Questions
     * 37. Practice Exercises
     * 38. Quick Revision
     *
     *
     * ================================================================
     * 1. INTRODUCTION TO AOP
     * ================================================================
     *
     * AOP stands for Aspect-Oriented Programming.
     *
     * It is a programming approach that helps separate
     * cross-cutting concerns from the main business logic.
     *
     * In enterprise applications, many services require
     * common functionality.
     *
     * Examples:
     *
     * - Logging
     * - Security checks
     * - Transaction management
     * - Performance monitoring
     * - Auditing
     * - Exception tracking
     * - Caching
     *
     * These concerns may be needed in many different classes.
     *
     * Instead of implementing the same logic repeatedly,
     * AOP allows us to define it separately and apply it
     * to selected execution points.
     *
     *
     * ================================================================
     * 2. PROBLEMS WITH TRADITIONAL PROGRAMMING
     * ================================================================
     *
     * Consider an application with:
     *
     * - UserService
     * - OrderService
     * - PaymentService
     * - ProductService
     *
     * All these services may require logging.
     *
     * Without AOP:
     *
     * public class UserService {
     *
     *     public void createUser() {
     *
     *         System.out.println("Method started");
     *
     *         // Business logic
     *
     *         System.out.println("Method completed");
     *     }
     * }
     *
     * public class OrderService {
     *
     *     public void createOrder() {
     *
     *         System.out.println("Method started");
     *
     *         // Business logic
     *
     *         System.out.println("Method completed");
     *     }
     * }
     *
     * public class PaymentService {
     *
     *     public void processPayment() {
     *
     *         System.out.println("Method started");
     *
     *         // Business logic
     *
     *         System.out.println("Method completed");
     *     }
     * }
     *
     * Problems:
     *
     * - Duplicate code.
     * - Difficult maintenance.
     * - Business logic gets mixed with infrastructure logic.
     * - Changing logging behavior requires modifying many classes.
     *
     * AOP addresses this problem by separating common behavior.
     *
     *
     * ================================================================
     * 3. CROSS-CUTTING CONCERNS
     * ================================================================
     *
     * A cross-cutting concern is functionality that affects
     * multiple parts of an application.
     *
     * Examples:
     *
     * LOGGING:
     * Multiple services need execution logs.
     *
     * SECURITY:
     * Multiple methods need authorization checks.
     *
     * TRANSACTION MANAGEMENT:
     * Multiple service methods need transaction boundaries.
     *
     * PERFORMANCE MONITORING:
     * Multiple methods need execution-time measurement.
     *
     * AUDITING:
     * Multiple operations need activity tracking.
     *
     * EXCEPTION TRACKING:
     * Multiple methods need consistent error monitoring.
     *
     * AOP helps centralize these concerns.
     *
     *
     * ================================================================
     * 4. WHAT IS ASPECT-ORIENTED PROGRAMMING?
     * ================================================================
     *
     * Aspect-Oriented Programming separates cross-cutting
     * concerns from the application's core business logic.
     *
     * It allows us to define:
     *
     * - What behavior should execute.
     * - Where that behavior should execute.
     * - When that behavior should execute.
     *
     * Example:
     *
     * Instead of writing logging code in every service,
     * define one logging aspect.
     *
     * The aspect can be configured to execute before or after
     * selected service methods.
     *
     *
     * OOP VS AOP:
     *
     * OBJECT-ORIENTED PROGRAMMING:
     *
     * Organizes application behavior around objects and classes.
     *
     * ASPECT-ORIENTED PROGRAMMING:
     *
     * Separates behavior that cuts across multiple objects
     * and classes.
     *
     * IMPORTANT:
     *
     * AOP does not replace OOP.
     *
     * It complements object-oriented application design.
     *
     *
     * ================================================================
     * 5. AOP TERMINOLOGY
     * ================================================================
     *
     * Important AOP terms:
     *
     * 1. Aspect
     * 2. Join Point
     * 3. Pointcut
     * 4. Advice
     * 5. Target Object
     * 6. AOP Proxy
     * 7. Weaving
     *
     * Understanding these terms is essential for Spring AOP.
     *
     *
     * ================================================================
     * 6. ASPECT
     * ================================================================
     *
     * An aspect is a module that contains cross-cutting behavior.
     *
     * Example:
     *
     * LoggingAspect
     * SecurityAspect
     * TransactionAspect
     * PerformanceAspect
     *
     * An aspect combines pointcut definitions and advice methods.
     *
     * Example:
     *
     * @Aspect
     * @Component
     * public class LoggingAspect {
     *
     *     @Before("execution(* com.example.service.*.*(..))")
     *     public void logBeforeMethod() {
     *         System.out.println("Method execution started");
     *     }
     * }
     *
     * Here:
     *
     * @Aspect identifies the class as an aspect.
     *
     * @Component registers the aspect as a Spring bean.
     *
     * @Before defines advice that executes before matching methods.
     *
     *
     * ================================================================
     * 7. JOIN POINT
     * ================================================================
     *
     * A join point is a point during program execution where
     * additional behavior can be applied.
     *
     * In general AOP systems, join points may include:
     *
     * - Method execution
     * - Method calls
     * - Constructor execution
     * - Field access
     *
     * IMPORTANT:
     *
     * Spring AOP supports method-execution join points.
     *
     * Therefore, in Spring AOP, the join point is generally
     * the execution of a method on a Spring-managed target
     * through its proxy.
     *
     * Example:
     *
     * userService.createUser()
     *
     * The execution of createUser() can be a Spring AOP
     * join point.
     *
     *
     * ================================================================
     * 8. POINTCUT
     * ================================================================
     *
     * A pointcut is an expression that identifies which
     * join points should receive advice.
     *
     * Example:
     *
     * @Before("execution(* com.example.service.*.*(..))")
     *
     * This pointcut matches method executions in the specified
     * service package, subject to the expression's pattern.
     *
     * A pointcut answers:
     *
     * WHERE should the advice execute?
     *
     * Examples:
     *
     * - All methods in a service package.
     * - All methods with a particular name.
     * - Methods belonging to a particular class.
     * - Methods carrying a specific annotation.
     *
     *
     * ================================================================
     * 9. ADVICE
     * ================================================================
     *
     * Advice represents the action that an aspect performs
     * at a selected join point.
     *
     * It answers:
     *
     * WHAT should execute, and WHEN?
     *
     * Spring AOP supports five main advice types:
     *
     * 1. @Before
     * 2. @AfterReturning
     * 3. @AfterThrowing
     * 4. @After
     * 5. @Around
     *
     *
     * ================================================================
     * 10. TARGET OBJECT
     * ================================================================
     *
     * The target object is the actual object whose methods
     * are being intercepted by an aspect.
     *
     * Example:
     *
     * @Service
     * public class UserService {
     *
     *     public void createUser() {
     *         System.out.println("Creating user");
     *     }
     * }
     *
     * Here, the UserService instance is the target object.
     *
     * Spring may expose a proxy that delegates to this target.
     *
     *
     * ================================================================
     * 11. AOP PROXY
     * ================================================================
     *
     * A proxy is an object that acts as an intermediary
     * between the caller and the target object.
     *
     * Instead of directly invoking the target method,
     * the caller invokes the proxy.
     *
     * The proxy can:
     *
     * - Execute advice.
     * - Delegate to the target method.
     * - Process the result.
     * - Handle exceptions.
     *
     * CONCEPTUAL FLOW:
     *
     *        Client
     *          |
     *          v
     *      AOP Proxy
     *          |
     *          v
     *      Advice Chain
     *          |
     *          v
     *      Target Object
     *          |
     *          v
     *       Result
     *
     * Spring AOP uses proxies to implement method interception.
     *
     *
     * ================================================================
     * 12. WEAVING
     * ================================================================
     *
     * Weaving means combining aspects with the application
     * code so that cross-cutting behavior is applied.
     *
     * Different AOP implementations can perform weaving
     * at different stages.
     *
     * COMMON TYPES:
     *
     * 1. Compile-time weaving
     * 2. Post-compile weaving
     * 3. Load-time weaving
     * 4. Runtime proxy-based weaving
     *
     *
     * COMPILE-TIME WEAVING:
     *
     * Aspects are applied during compilation.
     *
     *
     * POST-COMPILE WEAVING:
     *
     * Aspects are applied to already compiled classes.
     *
     *
     * LOAD-TIME WEAVING:
     *
     * Aspects are applied when classes are loaded.
     *
     *
     * RUNTIME PROXY-BASED WEAVING:
     *
     * Spring AOP creates proxies at runtime to intercept
     * method calls.
     *
     * IMPORTANT:
     *
     * Standard Spring AOP uses runtime proxies.
     *
     * AspectJ supports more extensive weaving mechanisms.
     *
     *
     * ================================================================
     * 13. SPRING AOP ARCHITECTURE
     * ================================================================
     *
     * Spring AOP applies advice using proxies around
     * Spring-managed beans.
     *
     * GENERAL FLOW:
     *
     * 1. Spring creates the target bean.
     * 2. Spring detects applicable aspects and advisors.
     * 3. Spring determines whether the bean needs a proxy.
     * 4. A proxy is created when applicable.
     * 5. The proxy is exposed to the application.
     * 6. Method calls through the proxy can trigger advice.
     * 7. The target method executes when the advice chain
     *    proceeds to it.
     *
     * IMPORTANT COMPONENTS:
     *
     * - Aspect
     * - Advice
     * - Pointcut
     * - Advisor
     * - Proxy
     * - Target object
     * - Interceptor chain
     *
     *
     * ================================================================
     * 14. TYPES OF ADVICE
     * ================================================================
     *
     * Spring AOP provides five main advice annotations.
     *
     *
     * @Before:
     * Executes before the target method.
     *
     * @AfterReturning:
     * Executes after the target method returns normally.
     *
     * @AfterThrowing:
     * Executes when the target method exits by throwing
     * an exception.
     *
     * @After:
     * Executes after the method completes, whether normally
     * or exceptionally.
     *
     * @Around:
     * Wraps the method invocation and controls whether
     * and when the target method proceeds.
     *
     *
     * ================================================================
     * 15. @BEFORE ADVICE
     * ================================================================
     *
     * @Before executes before the matching target method.
     *
     * Example:
     *
     * @Aspect
     * @Component
     * public class LoggingAspect {
     *
     *     @Before(
     *         "execution(* com.example.service.*.*(..))"
     *     )
     *     public void logBefore() {
     *
     *         System.out.println("Before method execution");
     *     }
     * }
     *
     * When a matching service method is invoked through
     * the proxy, the advice executes first.
     *
     * USE CASES:
     *
     * - Logging method entry.
     * - Checking execution context.
     * - Recording audit information.
     *
     * IMPORTANT:
     *
     * @Before does not prevent the target method from executing
     * unless the advice itself throws an exception or another
     * applicable mechanism changes the flow.
     *
     *
     * ================================================================
     * 16. @AFTERRETURNING ADVICE
     * ================================================================
     *
     * @AfterReturning executes after the target method
     * returns normally.
     *
     * Example:
     *
     * @Aspect
     * @Component
     * public class LoggingAspect {
     *
     *     @AfterReturning(
     *         pointcut =
     *             "execution(* com.example.service.*.*(..))",
     *         returning = "result"
     *     )
     *     public void logResult(Object result) {
     *
     *         System.out.println("Returned: " + result);
     *     }
     * }
     *
     * The returning attribute binds the target method's
     * returned value to the advice parameter.
     *
     * USE CASES:
     *
     * - Logging successful results.
     * - Recording completed operations.
     * - Performing post-success monitoring.
     *
     * IMPORTANT:
     *
     * This advice executes only for normal returns.
     * It does not execute when the target method throws an exception.
     *
     *
     * ================================================================
     * 17. @AFTERTHROWING ADVICE
     * ================================================================
     *
     * @AfterThrowing executes when the target method exits
     * by throwing an exception.
     *
     * Example:
     *
     * @Aspect
     * @Component
     * public class ExceptionAspect {
     *
     *     @AfterThrowing(
     *         pointcut =
     *             "execution(* com.example.service.*.*(..))",
     *         throwing = "exception"
     *     )
     *     public void logException(Exception exception) {
     *
     *         System.out.println(
     *             "Exception: " + exception.getMessage()
     *         );
     *     }
     * }
     *
     * The throwing attribute binds the thrown exception
     * to the advice parameter.
     *
     * USE CASES:
     *
     * - Exception monitoring.
     * - Error logging.
     * - Failure auditing.
     *
     * IMPORTANT:
     *
     * @AfterThrowing observes the exception.
     *
     * It does not automatically suppress or handle it.
     * The exception normally continues to the caller.
     *
     *
     * ================================================================
     * 18. @AFTER ADVICE
     * ================================================================
     *
     * @After executes after the target method completes,
     * whether it returns normally or throws an exception.
     *
     * Example:
     *
     * @Aspect
     * @Component
     * public class CleanupAspect {
     *
     *     @After(
     *         "execution(* com.example.service.*.*(..))"
     *     )
     *     public void cleanup() {
     *
     *         System.out.println("After method completion");
     *     }
     * }
     *
     * It is similar to finally-style execution.
     *
     * USE CASES:
     *
     * - Cleanup.
     * - Releasing resources.
     * - Final execution logging.
     *
     * IMPORTANT:
     *
     * @After is commonly called after advice.
     * It is not the same as @AfterReturning.
     *
     *
     * ================================================================
     * 19. @AROUND ADVICE
     * ================================================================
     *
     * @Around provides the greatest control over method execution.
     *
     * It surrounds the target method invocation.
     *
     * It can:
     *
     * - Execute before the target method.
     * - Execute the target method.
     * - Execute after the target method.
     * - Measure execution time.
     * - Inspect arguments.
     * - Inspect the returned value.
     * - Transform the returned value.
     * - Handle or rethrow exceptions.
     * - Prevent the target method from executing.
     *
     *
     * EXAMPLE:
     *
     * @Aspect
     * @Component
     * public class PerformanceAspect {
     *
     *     @Around(
     *         "execution(* com.example.service.*.*(..))"
     *     )
     *     public Object measureExecutionTime(
     *             ProceedingJoinPoint joinPoint)
     *             throws Throwable {
     *
     *         long start = System.currentTimeMillis();
     *
     *         try {
     *
     *             return joinPoint.proceed();
     *
     *         } finally {
     *
     *             long end = System.currentTimeMillis();
     *
     *             System.out.println(
     *                 "Execution time: " + (end - start) + " ms"
     *             );
     *         }
     *     }
     * }
     *
     *
     * PROCEED():
     *
     * joinPoint.proceed() continues the invocation chain.
     *
     * If proceed() is not called, the target method is not
     * executed by that invocation.
     *
     * The around advice is responsible for returning an
     * appropriate result.
     *
     * IMPORTANT:
     *
     * Use @Around only when its additional control is needed.
     * Prefer simpler advice types when they are sufficient.
     *
     *
     * ================================================================
     * 20. POINTCUT EXPRESSIONS
     * ================================================================
     *
     * Pointcut expressions define which method executions
     * should receive advice.
     *
     * Spring AOP commonly uses AspectJ pointcut expression syntax.
     *
     * Example:
     *
     * execution(* com.example.service.*.*(..))
     *
     * GENERAL STRUCTURE:
     *
     * execution(
     *     modifiers-pattern?
     *     return-type-pattern
     *     declaring-type-pattern?
     *     method-name-pattern
     *     (parameters)
     *     throws-pattern?
     * )
     *
     * Example:
     *
     * execution(
     *     public String com.example.service.UserService.getName()
     * )
     *
     * This identifies a particular method execution pattern.
     *
     *
     * COMMON SYMBOLS:
     *
     * *:
     * Matches a single pattern element.
     *
     * ..:
     * Matches any number of parameters or package levels,
     * depending on where it appears.
     *
     *
     * EXAMPLE 1:
     *
     * execution(* com.example.service.*.*(..))
     *
     * Matches methods declared in types directly within
     * com.example.service.
     *
     *
     * EXAMPLE 2:
     *
     * execution(* com.example.service..*.*(..))
     *
     * Matches methods declared in types within
     * com.example.service and its subpackages.
     *
     *
     * EXAMPLE 3:
     *
     * execution(public * *(..))
     *
     * Matches public method executions.
     *
     *
     * EXAMPLE 4:
     *
     * execution(* *Service.*(..))
     *
     * Matches methods in types whose names end with Service,
     * subject to the type pattern.
     *
     *
     * EXAMPLE 5:
     *
     * execution(* com.example.service.UserService.*(..))
     *
     * Matches methods declared by UserService.
     *
     *
     * ================================================================
     * 21. POINTCUT DESIGNATORS
     * ================================================================
     *
     * Pointcut designators identify different matching conditions.
     *
     * Common AspectJ pointcut designators include:
     *
     * - execution()
     * - within()
     * - this()
     * - target()
     * - args()
     * - @annotation()
     * - @within()
     * - @target()
     * - @args()
     * - bean()
     *
     *
     * A. execution()
     *
     * Matches method execution join points.
     *
     * Example:
     *
     * execution(* com.example.service.*.*(..))
     *
     *
     * B. within()
     *
     * Matches join points within specified types.
     *
     * Example:
     *
     * within(com.example.service.*)
     *
     *
     * C. this()
     *
     * Matches when the proxy object is of a specified type.
     *
     *
     * D. target()
     *
     * Matches when the target object is of a specified type.
     *
     *
     * E. args()
     *
     * Matches based on runtime argument types.
     *
     *
     * F. @annotation()
     *
     * Matches method executions where the method has
     * a specified annotation.
     *
     * Example:
     *
     * @annotation(com.example.LogExecution)
     *
     *
     * G. @within()
     *
     * Matches methods declared within types carrying
     * a specified annotation.
     *
     *
     * H. @target()
     *
     * Matches based on the annotation on the target object's
     * runtime type.
     *
     *
     * I. bean()
     *
     * Spring-specific pointcut designator.
     *
     * It matches Spring beans by bean name.
     *
     * Example:
     *
     * bean(userService)
     *
     *
     * IMPORTANT:
     *
     * Spring AOP supports a subset of AspectJ pointcut
     * expression capabilities.
     *
     * It does not support every AspectJ join point or
     * pointcut designator.
     *
     *
     * ================================================================
     * 22. COMBINING POINTCUTS
     * ================================================================
     *
     * Pointcuts can be combined using logical operators.
     *
     * AND:
     *
     * &&
     *
     * OR:
     *
     * ||
     *
     * NOT:
     *
     * !
     *
     *
     * EXAMPLE:
     *
     * execution(* com.example.service.*.*(..))
     * &&
     * execution(public * *(..))
     *
     * Matches public methods in the specified service package.
     *
     *
     * EXAMPLE:
     *
     * execution(* com.example.service.*.*(..))
     * ||
     * execution(* com.example.repository.*.*(..))
     *
     * Matches service or repository methods.
     *
     *
     * EXAMPLE:
     *
     * execution(* com.example.service.*.*(..))
     * &&
     * !execution(* com.example.service.InternalService.*(..))
     *
     * Excludes methods declared by InternalService.
     *
     *
     * ================================================================
     * 23. REUSABLE NAMED POINTCUTS
     * ================================================================
     *
     * A named pointcut is a reusable pointcut definition.
     *
     * Instead of repeating the same expression in many advice
     * annotations, define it once.
     *
     * Example:
     *
     * @Aspect
     * @Component
     * public class LoggingAspect {
     *
     *     @Pointcut(
     *         "execution(* com.example.service.*.*(..))"
     *     )
     *     public void serviceLayer() {
     *     }
     *
     *     @Before("serviceLayer()")
     *     public void logBefore() {
     *         System.out.println("Service method started");
     *     }
     *
     *     @After("serviceLayer()")
     *     public void logAfter() {
     *         System.out.println("Service method completed");
     *     }
     * }
     *
     * ADVANTAGES:
     *
     * - Reusability.
     * - Less duplication.
     * - Easier maintenance.
     * - Centralized pointcut definitions.
     *
     * Named pointcuts can also be referenced from other aspects
     * using their fully qualified method names.
     *
     *
     * ================================================================
     * 24. ACCESSING METHOD ARGUMENTS
     * ================================================================
     *
     * Advice can access the arguments supplied to the target method.
     *
     * Example:
     *
     * @Aspect
     * @Component
     * public class LoggingAspect {
     *
     *     @Before(
     *         value =
     *             "execution(* com.example.service.UserService.createUser(..))"
     *             + " && args(name, email)",
     *         argNames = "name,email"
     *     )
     *     public void logArguments(
     *             String name,
     *             String email) {
     *
     *         System.out.println("Name: " + name);
     *         System.out.println("Email: " + email);
     *     }
     * }
     *
     * args() binds matching method arguments to advice parameters.
     *
     * The declared argument names and types must be compatible
     * with the actual method signature.
     *
     *
     * ================================================================
     * 25. ACCESSING RETURN VALUES
     * ================================================================
     *
     * @AfterReturning can access the returned value.
     *
     * Example:
     *
     * @Aspect
     * @Component
     * public class ResultAspect {
     *
     *     @AfterReturning(
     *         pointcut =
     *             "execution(* com.example.service.UserService.getUser(..))",
     *         returning = "result"
     *     )
     *     public void logResult(Object result) {
     *
     *         System.out.println("Result: " + result);
     *     }
     * }
     *
     * The returning attribute binds the result to the advice
     * method parameter.
     *
     * IMPORTANT:
     *
     * @AfterReturning observes the return value.
     *
     * It does not replace the return value returned by
     * the target method.
     *
     * Use @Around when the returned value must be transformed.
     *
     *
     * ================================================================
     * 26. ACCESSING EXCEPTIONS
     * ================================================================
     *
     * @AfterThrowing can access an exception thrown by
     * the target method.
     *
     * Example:
     *
     * @Aspect
     * @Component
     * public class ExceptionAspect {
     *
     *     @AfterThrowing(
     *         pointcut =
     *             "execution(* com.example.service.*.*(..))",
     *         throwing = "exception"
     *     )
     *     public void handleException(Exception exception) {
     *
     *         System.out.println(
     *             exception.getMessage()
     *         );
     *     }
     * }
     *
     * The throwing attribute binds the exception.
     *
     * The advice can observe and log the exception.
     *
     * It does not automatically replace the exception with
     * a different result.
     *
     *
     * ================================================================
     * 27. ORDERING MULTIPLE ASPECTS
     * ================================================================
     *
     * An application can contain multiple aspects.
     *
     * Example:
     *
     * LoggingAspect
     * SecurityAspect
     * TransactionAspect
     *
     * Sometimes execution order matters.
     *
     * Spring supports @Order and Ordered for ordering aspects.
     *
     * Example:
     *
     * @Aspect
     * @Component
     * @Order(1)
     * public class SecurityAspect {
     * }
     *
     * @Aspect
     * @Component
     * @Order(2)
     * public class LoggingAspect {
     * }
     *
     * Lower order values have higher precedence.
     *
     * IMPORTANT:
     *
     * Advice ordering and the nesting of before/after behavior
     * must be understood carefully.
     *
     * For example, the aspect with higher precedence generally
     * enters first and exits last when advice is nested.
     *
     * Do not assume that every advice annotation executes
     * in the same simple ascending order.
     *
     *
     * ================================================================
     * 28. JDK DYNAMIC PROXIES
     * ================================================================
     *
     * JDK dynamic proxies are a Java mechanism for creating
     * runtime proxy objects for interfaces.
     *
     * Spring AOP can use JDK dynamic proxies when the target
     * exposes interfaces.
     *
     * Example:
     *
     * public interface UserService {
     *
     *     void createUser();
     * }
     *
     * public class UserServiceImpl implements UserService {
     *
     *     @Override
     *     public void createUser() {
     *         System.out.println("Creating user");
     *     }
     * }
     *
     * Spring can create a proxy implementing UserService.
     *
     * The caller invokes the interface method through
     * the proxy.
     *
     * The proxy applies the relevant advice and delegates
     * to the target.
     *
     *
     * CHARACTERISTICS:
     *
     * - Based on Java interface proxying.
     * - The proxy implements the target's interfaces.
     * - The target class itself is not subclassed.
     * - Injection by the exposed interface is generally preferred.
     *
     *
     * ================================================================
     * 29. CGLIB PROXIES
     * ================================================================
     *
     * CGLIB-based proxies create subclasses of target classes
     * to intercept eligible method calls.
     *
     * This allows Spring to proxy classes that do not expose
     * interfaces.
     *
     * Example:
     *
     * public class UserService {
     *
     *     public void createUser() {
     *         System.out.println("Creating user");
     *     }
     * }
     *
     * Spring can create a subclass-based proxy of UserService.
     *
     *
     * LIMITATIONS:
     *
     * - final classes cannot be subclassed.
     * - final methods cannot be overridden.
     * - private methods cannot be overridden.
     * - Static methods cannot be intercepted through
     *   normal subclass proxying.
     *
     * IMPORTANT:
     *
     * Spring's class-based proxying uses CGLIB functionality.
     *
     *
     * ================================================================
     * 30. PROXY-BASED EXECUTION FLOW
     * ================================================================
     *
     * Consider:
     *
     * @Service
     * public class PaymentService {
     *
     *     public void processPayment() {
     *         System.out.println("Processing payment");
     *     }
     * }
     *
     * An aspect intercepts processPayment().
     *
     * GENERAL EXECUTION:
     *
     * 1. Client requests PaymentService from Spring.
     *
     * 2. Spring provides the proxy when AOP applies.
     *
     * 3. Client invokes processPayment() on the proxy.
     *
     * 4. Proxy finds the applicable interceptor chain.
     *
     * 5. Before advice executes.
     *
     * 6. Target method executes when the chain proceeds.
     *
     * 7. Returning or throwing advice executes as applicable.
     *
     * 8. Control returns to the client.
     *
     *
     * ================================================================
     * 31. SELF-INVOCATION PROBLEM
     * ================================================================
     *
     * Self-invocation occurs when a method inside a class
     * directly calls another method of the same class.
     *
     * Example:
     *
     * @Service
     * public class UserService {
     *
     *     public void registerUser() {
     *         saveUser();
     *     }
     *
     *     @Transactional
     *     public void saveUser() {
     *         // Save user
     *     }
     * }
     *
     * When registerUser() calls saveUser() using this.saveUser()
     * implicitly, the call does not pass through the Spring proxy.
     *
     * Therefore, proxy-based advice on saveUser() is generally
     * not applied to that internal call.
     *
     * This is called the self-invocation problem.
     *
     *
     * WHY?
     *
     * External call:
     *
     * Client -> Proxy -> Target method
     *
     * Internal call:
     *
     * Target method -> Another method in same target object
     *
     * The internal call bypasses the proxy.
     *
     *
     * POSSIBLE SOLUTIONS:
     *
     * 1. Move the advised method into another Spring bean.
     *
     * 2. Restructure the application so the call passes
     *    through a Spring-managed proxy.
     *
     * 3. Use an appropriate AspectJ weaving approach when
     *    proxy-based interception is insufficient.
     *
     * IMPORTANT:
     *
     * Do not depend on self-injection as the default design solution.
     *
     *
     * ================================================================
     * 32. SPRING AOP LIMITATIONS
     * ================================================================
     *
     * Spring AOP is proxy-based and has important limitations.
     *
     * 1. It supports method-execution join points.
     *
     * 2. It intercepts calls that pass through the proxy.
     *
     * 3. Self-invocation generally bypasses advice.
     *
     * 4. Private methods cannot be intercepted through
     *    normal Spring proxy-based AOP.
     *
     * 5. Static methods are not intercepted by Spring AOP proxies.
     *
     * 6. Final methods cannot be overridden by class-based proxies.
     *
     * 7. Final classes cannot be subclassed by class-based proxies.
     *
     * 8. Calls on objects created manually using new are not
     *    automatically intercepted by Spring AOP.
     *
     * 9. Not every AspectJ pointcut expression is supported.
     *
     *
     * SPRING AOP VS ASPECTJ:
     *
     * SPRING AOP:
     *
     * - Proxy-based.
     * - Focuses on method execution.
     * - Integrates with Spring-managed beans.
     * - Simpler for common enterprise concerns.
     *
     * ASPECTJ:
     *
     * - Supports a wider range of join points.
     * - Supports compile-time and load-time weaving.
     * - Can intercept behavior beyond Spring proxy boundaries.
     * - Requires AspectJ weaving configuration and tooling.
     *
     * IMPORTANT:
     *
     * Spring AOP uses AspectJ pointcut expression syntax,
     * but Spring AOP is not the same as the full AspectJ
     * weaving system.
     *
     *
     * ================================================================
     * 33. AOP AND SPRING BOOT
     * ================================================================
     *
     * Spring Boot supports AOP through its starter dependency.
     *
     * Maven dependency:
     *
     * <dependency>
     *     <groupId>org.springframework.boot</groupId>
     *     <artifactId>spring-boot-starter-aop</artifactId>
     * </dependency>
     *
     * This provides the necessary AOP integration.
     *
     * AOP configuration can be enabled explicitly using:
     *
     * @EnableAspectJAutoProxy
     *
     * In Spring Boot applications, the AOP starter normally
     * configures the required auto-proxy infrastructure.
     *
     * Example:
     *
     * @SpringBootApplication
     * public class Application {
     *
     *     public static void main(String[] args) {
     *         SpringApplication.run(Application.class, args);
     *     }
     * }
     *
     * Aspect:
     *
     * @Aspect
     * @Component
     * public class LoggingAspect {
     *
     *     @Before(
     *         "execution(* com.example.service.*.*(..))"
     *     )
     *     public void log() {
     *         System.out.println("Service method called");
     *     }
     * }
     *
     * Spring Boot makes it convenient to integrate AOP
     * into backend applications.
     *
     *
     * ================================================================
     * 34. REAL-WORLD APPLICATIONS
     * ================================================================
     *
     * SCENARIO 1: LOGGING
     *
     * Every service method should log its execution.
     *
     * Suitable advice:
     *
     * @Before and @AfterReturning
     *
     *
     * SCENARIO 2: PERFORMANCE MONITORING
     *
     * Measure how long each service method takes.
     *
     * Suitable advice:
     *
     * @Around
     *
     *
     * SCENARIO 3: SECURITY
     *
     * Check whether a user is allowed to execute an operation.
     *
     * Suitable approach:
     *
     * Security interceptors and method security infrastructure.
     *
     * Custom AOP can also implement specialized cross-cutting
     * authorization behavior, but should not replace established
     * Spring Security mechanisms unnecessarily.
     *
     *
     * SCENARIO 4: TRANSACTION MANAGEMENT
     *
     * Ensure database operations execute within transactions.
     *
     * Suitable approach:
     *
     * @Transactional and Spring's transaction infrastructure.
     *
     * Transaction management is implemented using AOP-style
     * interception in common proxy-based configurations.
     *
     *
     * SCENARIO 5: AUDITING
     *
     * Record important application operations.
     *
     * Suitable advice:
     *
     * @AfterReturning or @Around, depending on requirements.
     *
     *
     * SCENARIO 6: CACHING
     *
     * Cache frequently requested method results.
     *
     * Suitable approach:
     *
     * Spring caching abstraction with @Cacheable,
     * @CachePut and @CacheEvict.
     *
     * Caching also uses proxy-based interception in common
     * Spring configurations.
     *
     *
     * ================================================================
     * 35. COMMON MISTAKES
     * ================================================================
     *
     * MISTAKE 1:
     *
     * Confusing pointcut and advice.
     *
     * CORRECTION:
     *
     * Pointcut identifies where advice should execute.
     *
     * Advice defines what should execute.
     *
     *
     * MISTAKE 2:
     *
     * Assuming every method call is intercepted.
     *
     * CORRECTION:
     *
     * Spring AOP intercepts eligible calls passing through
     * the proxy.
     *
     *
     * MISTAKE 3:
     *
     * Expecting self-invocation to trigger advice.
     *
     * CORRECTION:
     *
     * Internal calls on the target object bypass the proxy.
     *
     *
     * MISTAKE 4:
     *
     * Assuming @AfterReturning runs when an exception occurs.
     *
     * CORRECTION:
     *
     * @AfterReturning handles normal completion.
     *
     *
     * MISTAKE 5:
     *
     * Assuming @AfterThrowing suppresses exceptions.
     *
     * CORRECTION:
     *
     * It observes the thrown exception but does not
     * automatically suppress it.
     *
     *
     * MISTAKE 6:
     *
     * Forgetting joinPoint.proceed() inside @Around advice.
     *
     * CORRECTION:
     *
     * Without proceeding, the target invocation does not
     * continue through the invocation chain.
     *
     *
     * MISTAKE 7:
     *
     * Using @Around for every requirement.
     *
     * CORRECTION:
     *
     * Prefer simpler advice types when they satisfy the need.
     *
     *
     * MISTAKE 8:
     *
     * Assuming Spring AOP and AspectJ are identical.
     *
     * CORRECTION:
     *
     * Spring AOP is proxy-based, while AspectJ supports
     * more extensive weaving capabilities.
     *
     *
     * ================================================================
     * 36. INTERVIEW QUESTIONS
     * ================================================================
     *
     * BASIC:
     *
     * 1. What is AOP?
     * 2. Why is AOP required?
     * 3. What is a cross-cutting concern?
     * 4. What is an aspect?
     * 5. What is a join point?
     * 6. What is a pointcut?
     * 7. What is advice?
     * 8. What is a target object?
     * 9. What is an AOP proxy?
     * 10. What is weaving?
     *
     *
     * INTERMEDIATE:
     *
     * 11. What are the five types of advice?
     * 12. What is the difference between @Before and @After?
     * 13. What is the difference between @AfterReturning
     *     and @AfterThrowing?
     * 14. What is the purpose of @Around?
     * 15. What is ProceedingJoinPoint?
     * 16. What is an execution pointcut expression?
     * 17. What is the difference between within() and execution()?
     * 18. What is @Pointcut?
     * 19. How do you access method arguments in advice?
     * 20. How do you access a returned value in advice?
     *
     *
     * ADVANCED:
     *
     * 21. How does Spring AOP work internally?
     * 22. What is the difference between JDK and CGLIB proxies?
     * 23. Why can final methods not be intercepted using
     *     class-based proxies?
     * 24. What is the self-invocation problem?
     * 25. How can self-invocation be avoided?
     * 26. What are the limitations of Spring AOP?
     * 27. What is the difference between Spring AOP and AspectJ?
     * 28. How does @Transactional use proxy-based AOP?
     * 29. How can multiple aspects be ordered?
     * 30. Why is Spring AOP not a distributed messaging mechanism?
     *
     *
     * ================================================================
     * 37. PRACTICE EXERCISES
     * ================================================================
     *
     * EXERCISE 1:
     *
     * Create a LoggingAspect that logs before every service method.
     *
     * EXERCISE 2:
     *
     * Create an aspect that logs successful method return values.
     *
     * EXERCISE 3:
     *
     * Create an aspect that logs exceptions from service methods.
     *
     * EXERCISE 4:
     *
     * Create an @Around advice to measure execution time.
     *
     * EXERCISE 5:
     *
     * Create a named pointcut for the service layer.
     *
     * EXERCISE 6:
     *
     * Create a pointcut that matches methods with a particular name.
     *
     * EXERCISE 7:
     *
     * Create a custom annotation named LogExecution.
     * Apply an aspect to methods carrying that annotation.
     *
     * EXERCISE 8:
     *
     * Create two aspects and control their order using @Order.
     *
     * EXERCISE 9:
     *
     * Create a service method that calls another method in
     * the same class. Observe the self-invocation problem.
     *
     * EXERCISE 10:
     *
     * Enable Spring AOP in a Spring Boot application and
     * implement a reusable performance monitoring aspect.
     *
     *
     * ================================================================
     * 38. QUICK REVISION
     * ================================================================
     *
     * AOP:
     * Separates cross-cutting concerns from business logic.
     *
     * ASPECT:
     * Contains cross-cutting behavior.
     *
     * JOIN POINT:
     * A point during execution where advice can be applied.
     *
     * POINTCUT:
     * Identifies matching join points.
     *
     * ADVICE:
     * Defines the behavior to execute.
     *
     * TARGET OBJECT:
     * The actual object being advised.
     *
     * PROXY:
     * Intermediary that intercepts method calls.
     *
     * WEAVING:
     * Combining aspects with application behavior.
     *
     * @Before:
     * Executes before the target method.
     *
     * @AfterReturning:
     * Executes after normal return.
     *
     * @AfterThrowing:
     * Executes when the target method throws an exception.
     *
     * @After:
     * Executes after normal or exceptional completion.
     *
     * @Around:
     * Wraps and controls the method invocation.
     *
     * JDK DYNAMIC PROXY:
     * Creates a proxy implementing interfaces.
     *
     * CGLIB PROXY:
     * Creates a subclass-based proxy.
     *
     * SELF-INVOCATION:
     * Internal method calls bypass the Spring proxy.
     *
     * SPRING AOP:
     * Uses runtime proxies and supports method-execution join points.
     *
     * ASPECTJ:
     * Supports broader join points and different weaving mechanisms.
     *
     * ================================================================
     * END OF SPRING CORE - FILE 07
     * ================================================================
     */

    public static void main(String[] args) {

        System.out.println(
            "Spring Core - Aspect-Oriented Programming"
        );

    }

}
