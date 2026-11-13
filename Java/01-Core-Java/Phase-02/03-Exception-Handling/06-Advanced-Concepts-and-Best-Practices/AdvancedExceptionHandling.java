
public class AdvancedExceptionHandling {

    /*
     * ============================================================
     *       ADVANCED EXCEPTION HANDLING AND BEST PRACTICES
     * ============================================================
     *
     * 1. EXCEPTION HANDLING IN CONSTRUCTORS
     *
     * Constructors can throw both checked and unchecked exceptions.
     *
     * Example:
     *
     * class DatabaseConnection {
     *
     *     DatabaseConnection(String url) throws SQLException {
     *
     *         if (url == null || url.isBlank()) {
     *             throw new SQLException("Invalid database URL");
     *         }
     *     }
     * }
     *
     * Important:
     * - A constructor can declare exceptions using throws.
     * - A constructor can throw exceptions using throw.
     * - The code creating the object must handle or declare
     *   checked exceptions.
     * - Constructors are not overridden.
     *
     *
     * 2. EXCEPTIONS DURING OBJECT INITIALIZATION
     *
     * Java objects are initialized in a defined sequence:
     *
     * 1. Memory is allocated.
     * 2. Fields receive default values.
     * 3. Superclass initialization takes place.
     * 4. Instance field initializers and instance initializer
     *    blocks execute in their textual order.
     * 5. Constructor body executes.
     *
     * If an exception occurs during initialization:
     *
     * - The object is not successfully constructed.
     * - The exception propagates to the caller unless handled.
     * - The caller does not receive a normally constructed object.
     *
     * Important:
     * Java does not automatically roll back external side effects
     * performed during construction.
     *
     *
     * 3. EXCEPTIONS IN STATIC INITIALIZERS
     *
     * Static initializers execute during class initialization.
     *
     * Example:
     *
     * class DatabaseConfig {
     *
     *     static {
     *         if (System.getProperty("db.url") == null) {
     *             throw new IllegalStateException(
     *                 "Database URL is missing"
     *             );
     *         }
     *     }
     * }
     *
     * If class initialization fails because of an exception
     * other than Error, the JVM generally wraps it in
     * ExceptionInInitializerError.
     *
     * Subsequent attempts to initialize that class may fail
     * with NoClassDefFoundError because the class is erroneous.
     *
     * Important:
     * Static initializers cannot declare checked exceptions
     * using throws.
     *
     *
     * 4. EXCEPTIONS IN METHOD OVERRIDING
     *
     * Checked exception rules apply when overriding methods.
     *
     * Parent:
     *
     * class Parent {
     *
     *     void read() throws IOException {
     *     }
     * }
     *
     * Child:
     *
     * class Child extends Parent {
     *
     *     @Override
     *     void read() throws FileNotFoundException {
     *     }
     * }
     *
     * Valid:
     * FileNotFoundException is a subclass of IOException.
     *
     * An overriding method may:
     *
     * - Declare the same checked exception.
     * - Declare a narrower checked exception.
     * - Declare no checked exception.
     *
     * It cannot introduce a broader checked exception than
     * the overridden method permits.
     *
     * Unchecked exceptions are not restricted by this rule.
     *
     *
     * 5. EXCEPTIONS IN INTERFACES
     *
     * An interface method can declare checked exceptions.
     *
     * Example:
     *
     * interface FileProcessor {
     *
     *     void process() throws IOException;
     * }
     *
     * class MyFileProcessor implements FileProcessor {
     *
     *     @Override
     *     public void process() throws FileNotFoundException {
     *     }
     * }
     *
     * The implementation follows the same checked exception
     * compatibility rules as method overriding.
     *
     *
     * 6. EXCEPTIONS IN LAMBDA EXPRESSIONS
     *
     * A lambda expression must follow the throws contract
     * of its target functional interface method.
     *
     * Example:
     *
     * @FunctionalInterface
     * interface FileTask {
     *     void execute() throws IOException;
     * }
     *
     * FileTask task = () -> {
     *     throw new IOException("File failure");
     * };
     *
     * This is valid because execute() declares IOException.
     *
     * But consider:
     *
     * Runnable task = () -> {
     *     // throw new IOException("Failure");
     * };
     *
     * Runnable.run() does not declare checked exceptions.
     * Therefore, the lambda cannot directly throw IOException.
     *
     * It can still throw unchecked exceptions.
     *
     * Important:
     * A lambda does not get an independent exception contract.
     * It follows the abstract method of its target interface.
     *
     *
     * 7. EXCEPTIONS IN STREAM OPERATIONS
     *
     * Java Stream operations commonly use functional interfaces
     * such as:
     *
     * - Predicate
     * - Function
     * - Consumer
     * - Supplier
     *
     * Their standard abstract methods do not declare checked
     * exceptions.
     *
     * Therefore, checked exceptions from operations such as
     * file access cannot be thrown directly from these lambdas.
     *
     * Example:
     *
     * List<String> files = List.of("a.txt", "b.txt");
     *
     * files.stream().forEach(file -> {
     *     // A checked IOException cannot be thrown directly
     *     // unless it is handled or translated.
     * });
     *
     * Common approaches:
     *
     * - Handle the checked exception inside the lambda.
     * - Wrap it in an unchecked exception when appropriate.
     * - Use a custom functional interface declaring the
     *   required checked exception.
     *
     * Avoid wrapping exceptions without a meaningful reason.
     *
     *
     * 8. PRECISE RE-THROW
     *
     * Java supports precise rethrow analysis.
     *
     * Example:
     *
     * static void process() throws IOException, SQLException {
     *
     *     try {
     *         performOperation();
     *     }
     *     catch (Exception e) {
     *         throw e;
     *     }
     * }
     *
     * Assume performOperation() declares only:
     *
     * IOException, SQLException
     *
     * Under the precise rethrow rules, the compiler can infer
     * that the rethrown exception can only be IOException
     * or SQLException.
     *
     * The method does not need to declare every possible
     * subclass of Exception.
     *
     * Important:
     * The catch parameter must not be reassigned for this
     * precise analysis.
     *
     * Java 7 introduced precise rethrow improvements.
     *
     *
     * 9. EXCEPTIONS IN FINALLY
     *
     * A finally block normally executes when control leaves
     * a try/catch statement.
     *
     * However, exceptions or return statements inside finally
     * can replace a pending return or exception.
     *
     * Example:
     *
     * try {
     *     throw new IOException("Original");
     * }
     * finally {
     *     throw new RuntimeException("Cleanup failure");
     * }
     *
     * The RuntimeException becomes the primary propagated
     * exception. The original IOException is not automatically
     * preserved as its cause.
     *
     * Best practice:
     * Avoid throwing new exceptions from finally.
     *
     * Use try-with-resources for AutoCloseable resources,
     * because it preserves cleanup failures as suppressed
     * exceptions when appropriate.
     *
     *
     * 10. EXCEPTIONS AND RETURN VALUES
     *
     * Consider:
     *
     * static int calculate() {
     *
     *     try {
     *         return 10;
     *     }
     *     finally {
     *         System.out.println("Cleanup");
     *     }
     * }
     *
     * Return value:
     * 10
     *
     * The finally block executes before the method returns.
     *
     * However:
     *
     * static int calculate() {
     *
     *     try {
     *         return 10;
     *     }
     *     finally {
     *         return 20;
     *     }
     * }
     *
     * Return value:
     * 20
     *
     * The return from finally overrides the pending return.
     *
     * Best practice:
     * Never return from finally.
     *
     *
     * 11. EXCEPTIONS AND ASSERTIONS
     *
     * Java assertions help verify assumptions during development.
     *
     * Example:
     *
     * assert age >= 0 : "Age cannot be negative";
     *
     * If the assertion condition is false and assertions
     * are enabled, Java throws AssertionError.
     *
     * Assertions are disabled by default.
     *
     * Enable them using:
     *
     * java -ea MyClass
     *
     * Important:
     *
     * - AssertionError extends Error.
     * - Assertions are useful for internal invariants.
     * - Do not use assertions for validating external user input.
     * - Do not depend on assertions for mandatory application logic.
     *
     *
     * 12. EXCEPTIONS AND THREADS
     *
     * An uncaught exception in a thread normally terminates
     * that thread.
     *
     * It does not automatically terminate every other thread
     * in the JVM.
     *
     * Example:
     *
     * Thread thread = new Thread(() -> {
     *     throw new RuntimeException("Thread failure");
     * });
     *
     * thread.start();
     *
     * Java provides an UncaughtExceptionHandler.
     *
     * Example:
     *
     * thread.setUncaughtExceptionHandler((t, e) -> {
     *     System.out.println(
     *         t.getName() + " failed: " + e.getMessage()
     *     );
     * });
     *
     * This handler is invoked when an exception escapes
     * the thread's execution without being caught.
     *
     * Important:
     * An uncaught-exception handler is a reporting mechanism.
     * It does not automatically resume the failed thread.
     *
     *
     * 13. INTERRUPTED EXCEPTION
     *
     * InterruptedException is a checked exception.
     *
     * It commonly occurs during interruptible operations such as:
     *
     * - Thread.sleep()
     * - Object.wait()
     * - Thread.join()
     *
     * Example:
     *
     * try {
     *     Thread.sleep(1000);
     * }
     * catch (InterruptedException e) {
     *     Thread.currentThread().interrupt();
     * }
     *
     * Why restore the interrupt status?
     *
     * Many interruptible methods clear the interrupted status
     * when they throw InterruptedException.
     *
     * If the current method cannot fully handle the interruption,
     * restoring the interrupt flag allows higher-level code
     * to observe the interruption.
     *
     * Alternative:
     * Declare throws InterruptedException and let the caller
     * decide how to respond.
     *
     * Important:
     * Do not silently swallow InterruptedException.
     *
     *
     * 14. EXCEPTION HANDLING AND LOGGING
     *
     * Logging records important information about application
     * execution and failures.
     *
     * Example using java.util.logging:
     *
     * import java.util.logging.Level;
     * import java.util.logging.Logger;
     *
     * class UserService {
     *
     *     private static final Logger logger =
     *         Logger.getLogger(UserService.class.getName());
     *
     *     void loadUser() {
     *
     *         try {
     *             // Risky operation
     *         }
     *         catch (RuntimeException e) {
     *
     *             logger.log(
     *                 Level.SEVERE,
     *                 "Unable to load user",
     *                 e
     *             );
     *
     *             throw e;
     *         }
     *     }
     * }
     *
     * Best practices:
     *
     * - Log meaningful context.
     * - Preserve stack traces when useful.
     * - Do not log the same failure repeatedly at every layer.
     * - Avoid logging passwords, access tokens, or sensitive data.
     * - Use appropriate log levels.
     * - Do not expose internal stack traces to API clients.
     *
     *
     * 15. EXCEPTION HANDLING IN LAYERED APPLICATIONS
     *
     * Consider a Spring Boot backend:
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
     * Example:
     *
     * - Repository encounters a database failure.
     * - Service applies business rules and may translate
     *   lower-level failures into application exceptions.
     * - Controller advice translates exceptions into HTTP responses.
     *
     * In Spring Boot, commonly used mechanisms include:
     *
     * @ControllerAdvice
     * @RestControllerAdvice
     * @ExceptionHandler
     *
     * Example:
     *
     * @RestControllerAdvice
     * class GlobalExceptionHandler {
     *
     *     @ExceptionHandler(UserNotFoundException.class)
     *     public ResponseEntity<String> handleUserNotFound(
     *             UserNotFoundException e) {
     *
     *         return ResponseEntity
     *             .status(HttpStatus.NOT_FOUND)
     *             .body(e.getMessage());
     *     }
     * }
     *
     * Note:
     * This is a Spring example, not a standalone Java class.
     * The relevant Spring imports and exception class must
     * also be provided.
     *
     * The purpose is to separate exception handling from
     * individual controller methods.
     *
     *
     * 16. EXCEPTION TRANSLATION
     *
     * Exception translation means converting a lower-level
     * exception into a higher-level exception that makes
     * more sense to the calling layer.
     *
     * Example:
     *
     * SQLException
     *       |
     *       v
     * DataAccessException
     *       |
     *       v
     * Service-level handling
     *
     * Spring's data-access exception hierarchy provides
     * a runtime exception abstraction over many persistence
     * technology-specific failures.
     *
     * Important:
     * Preserve the original cause when translating exceptions.
     *
     *
     * 17. EXCEPTION HANDLING AND PERFORMANCE
     *
     * Creating exceptions and capturing stack traces has a cost.
     *
     * Exceptions should represent exceptional conditions,
     * not ordinary business control flow.
     *
     * Avoid:
     *
     * try {
     *     // Repeatedly use exceptions to control normal loops
     * }
     * catch (Exception e) {
     * }
     *
     * Prefer normal conditions:
     *
     * if (value != null) {
     *     // Normal processing
     * }
     *
     * Important:
     * Do not prematurely optimize exception handling.
     * Use exceptions where they make the API and failure
     * semantics clearer.
     *
     *
     * 18. SHOULD WE CATCH Exception OR Throwable?
     *
     * Prefer catching specific exception types.
     *
     * Example:
     *
     * catch (IOException e) {
     *     // Handle I/O failure
     * }
     *
     * Catching Exception is sometimes appropriate at a
     * carefully chosen application boundary.
     *
     * Catching Throwable is generally dangerous because
     * it also catches Error subclasses.
     *
     * Examples of serious Errors:
     *
     * - OutOfMemoryError
     * - StackOverflowError
     * - LinkageError
     *
     * These generally should not be handled as ordinary
     * application exceptions.
     *
     * Some infrastructure boundaries may catch Throwable
     * for narrowly defined cleanup or reporting purposes,
     * but recovery must not be assumed safe.
     *
     *
     * 19. EXCEPTION SWALLOWING
     *
     * Exception swallowing means catching an exception
     * without meaningful handling, logging, or propagation.
     *
     * Bad example:
     *
     * try {
     *     saveData();
     * }
     * catch (Exception e) {
     *     // Do nothing
     * }
     *
     * Problem:
     *
     * - The caller believes the operation may have succeeded.
     * - The actual failure is hidden.
     * - Debugging becomes difficult.
     * - Data consistency problems may go unnoticed.
     *
     * Better:
     *
     * catch (IOException e) {
     *     throw new RuntimeException("Unable to save data", e);
     * }
     *
     * Or handle the failure meaningfully if recovery is possible.
     *
     *
     * 20. LOGGING AND RETHROWING
     *
     * Avoid logging the same exception at every layer.
     *
     * Example:
     *
     * Repository logs the exception.
     * Service logs the same exception.
     * Controller logs the same exception.
     *
     * This can create duplicate stack traces.
     *
     * Better approach:
     *
     * - Add useful context at the layer that understands it.
     * - Propagate the exception.
     * - Log at an appropriate boundary.
     * - Avoid duplicate logs unless each log adds meaningful value.
     *
     *
     * 21. EXCEPTION MESSAGES
     *
     * Good messages explain what failed and, where useful,
     * provide safe context.
     *
     * Good:
     *
     * "User with the requested ID was not found"
     *
     * "Unable to process the payment"
     *
     * "Invalid account state for withdrawal"
     *
     * Avoid:
     *
     * "Error"
     *
     * "Something went wrong"
     *
     * "Failed"
     *
     * Never include passwords, tokens, secret keys, or
     * unnecessary personal information in exception messages.
     *
     *
     * 22. EXCEPTIONS VS RETURN CODES
     *
     * Exceptions:
     *
     * - Represent exceptional failure conditions.
     * - Can propagate through method calls.
     * - Carry type, message, cause, and stack trace.
     *
     * Return codes:
     *
     * - Represent results through ordinary return values.
     * - Require callers to inspect the returned value.
     * - Can be suitable for expected outcomes.
     *
     * Use the mechanism that best communicates the method's
     * contract.
     *
     *
     * 23. FINAL BEST PRACTICES CHECKLIST
     *
     * [ ] Catch specific exceptions where possible.
     *
     * [ ] Do not swallow exceptions silently.
     *
     * [ ] Do not catch Error without a strong reason.
     *
     * [ ] Avoid catching Throwable broadly.
     *
     * [ ] Do not use exceptions for ordinary control flow.
     *
     * [ ] Preserve the original cause when wrapping exceptions.
     *
     * [ ] Use meaningful exception names and messages.
     *
     * [ ] Do not return from finally.
     *
     * [ ] Prefer try-with-resources for AutoCloseable resources.
     *
     * [ ] Restore the interrupt flag when appropriate.
     *
     * [ ] Avoid duplicate exception logging.
     *
     * [ ] Never expose sensitive data in exception messages.
     *
     * [ ] Handle exceptions at the layer with enough context.
     *
     *
     * 24. INTERVIEW QUESTIONS
     *
     * Q1. Can constructors throw exceptions?
     *
     * Q2. What happens when a static initializer fails?
     *
     * Q3. Can a static initializer declare throws?
     *
     * Q4. Can a lambda throw a checked exception?
     *
     * Q5. What is precise rethrow?
     *
     * Q6. What happens to a thread when an uncaught exception occurs?
     *
     * Q7. What is UncaughtExceptionHandler?
     *
     * Q8. Why should InterruptedException not be swallowed?
     *
     * Q9. Why should we restore the interrupt flag?
     *
     * Q10. What is exception translation?
     *
     * Q11. Why should we avoid catching Throwable?
     *
     * Q12. What is exception swallowing?
     *
     * Q13. Why should exceptions not be used for normal control flow?
     *
     * Q14. How does Spring Boot handle exceptions globally?
     *
     * Q15. What is the difference between logging and rethrowing?
     *
     *
     * 25. FINAL REVISION
     *
     * Exception fundamentals:
     * Understand Throwable, Error, Exception, and RuntimeException.
     *
     * Try-catch-finally:
     * Understand handling, execution flow, and cleanup.
     *
     * Throw, throws, propagation:
     * Understand throwing, declaring, and stack unwinding.
     *
     * Custom exceptions:
     * Understand application-specific checked and unchecked failures.
     *
     * Try-with-resources:
     * Understand automatic resource closing and suppressed exceptions.
     *
     * Advanced concepts:
     * Understand Java language rules, threading, logging,
     * and real-world application practices.
     *
     * FINAL MEMORY RULE:
     *
     * Detect the failure.
     * Choose the right exception.
     * Handle it at the right layer.
     * Preserve its cause.
     * Release resources.
     * Communicate the failure safely.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Advanced Exception Handling and Best Practices , Happy Learning ..!");
    }
}
