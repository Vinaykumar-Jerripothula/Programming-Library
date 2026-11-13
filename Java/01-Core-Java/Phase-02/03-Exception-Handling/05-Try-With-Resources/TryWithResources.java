
public class TryWithResources {

    /*
     * ============================================================
     *                    TRY-WITH-RESOURCES
     * ============================================================
     *
     * 1. WHY DO WE NEED TRY-WITH-RESOURCES?
     *
     * Imagine opening a file:
     *
     * FileReader reader = new FileReader("data.txt");
     *
     * After using the file, we must close it.
     *
     * Why?
     *
     * Because resources such as files, database connections,
     * and network streams use system resources.
     *
     * If we forget to close them:
     *
     * - File handles may remain occupied.
     * - Database connections may not return to the connection pool.
     * - Network resources may remain allocated.
     * - Applications may eventually run out of resources.
     *
     * Traditionally, we used finally to close resources.
     *
     * However, writing cleanup code manually can be repetitive
     * and error-prone.
     *
     * Java 7 introduced try-with-resources to automate this.
     *
     *
     * 2. WHAT IS TRY-WITH-RESOURCES?
     *
     * Try-with-resources is a Java construct that automatically
     * closes resources when execution leaves the try statement.
     *
     * Syntax:
     *
     * try (ResourceType resource = createResource()) {
     *
     *     // Use the resource
     *
     * }
     *
     * Java automatically invokes the resource's close() method.
     *
     * This happens when the try statement completes normally
     * or when an exception causes it to exit.
     *
     *
     * 3. BASIC EXAMPLE
     *
     * import java.io.FileReader;
     * import java.io.IOException;
     *
     * public class Example {
     *
     *     public static void main(String[] args) {
     *
     *         try (FileReader reader =
     *                  new FileReader("data.txt")) {
     *
     *             int character;
     *
     *             while ((character = reader.read()) != -1) {
     *                 System.out.print((char) character);
     *             }
     *
     *         }
     *         catch (IOException e) {
     *             System.out.println(e.getMessage());
     *         }
     *     }
     * }
     *
     * Explanation:
     *
     * 1. FileReader is created inside the resource specification.
     * 2. The try block reads the file.
     * 3. When the try statement exits, Java closes the reader.
     * 4. If reading throws an exception, Java still attempts
     *    to close the resource.
     *
     * We do not need to write reader.close() manually.
     *
     *
     * 4. WHAT TYPES OF RESOURCES CAN WE USE?
     *
     * A resource used in try-with-resources must implement
     * java.lang.AutoCloseable.
     *
     * Common examples:
     *
     * - FileReader
     * - FileWriter
     * - BufferedReader
     * - BufferedWriter
     * - InputStream
     * - OutputStream
     * - Connection
     * - Statement
     * - ResultSet
     *
     * Many Java I/O and JDBC classes implement AutoCloseable,
     * directly or through an inheritance hierarchy.
     *
     *
     * 5. WHAT IS AutoCloseable?
     *
     * AutoCloseable is an interface in java.lang.
     *
     * Declaration:
     *
     * public interface AutoCloseable {
     *
     *     void close() throws Exception;
     * }
     *
     * It represents a resource that can be closed automatically.
     *
     * When a resource implements AutoCloseable, it can be used
     * in a try-with-resources statement.
     *
     * Important:
     *
     * - AutoCloseable was introduced in Java 7.
     * - It declares close().
     * - Its close() method can declare Exception.
     * - Implementations should release the resource.
     *
     *
     * 6. CREATING OUR OWN AutoCloseable RESOURCE
     *
     * We can create custom resources that work with
     * try-with-resources.
     *
     * Example:
     *
     * class MyResource implements AutoCloseable {
     *
     *     public void use() {
     *         System.out.println("Using resource");
     *     }
     *
     *     @Override
     *     public void close() {
     *         System.out.println("Resource closed");
     *     }
     * }
     *
     * public class Example {
     *
     *     public static void main(String[] args) {
     *
     *         try (MyResource resource = new MyResource()) {
     *
     *             resource.use();
     *
     *         }
     *     }
     * }
     *
     * Output:
     *
     * Using resource
     * Resource closed
     *
     * Explanation:
     *
     * - MyResource implements AutoCloseable.
     * - Java recognizes it as a closeable resource.
     * - When the try statement exits, close() is invoked.
     *
     *
     * 7. WHAT IS Closeable?
     *
     * Closeable is an interface in java.io.
     *
     * Declaration:
     *
     * public interface Closeable extends AutoCloseable {
     *
     *     void close() throws IOException;
     * }
     *
     * Closeable is a specialized resource-closing contract
     * commonly used by I/O classes.
     *
     * Important differences:
     *
     * AutoCloseable:
     * - Present in java.lang.
     * - Introduced in Java 7.
     * - close() can throw Exception.
     *
     * Closeable:
     * - Present in java.io.
     * - Extends AutoCloseable.
     * - close() declares IOException.
     * - Closing an already-closed resource should have no effect.
     *
     * MEMORY RULE:
     *
     * Every Closeable is an AutoCloseable.
     * But not every AutoCloseable is a Closeable.
     *
     *
     * 8. MULTIPLE RESOURCES
     *
     * A try-with-resources statement can manage multiple resources.
     *
     * Syntax:
     *
     * try (
     *     ResourceType resource1 = createResource1();
     *     ResourceType resource2 = createResource2()
     * ) {
     *
     *     // Use both resources
     *
     * }
     *
     * Resources are separated using semicolons.
     *
     * Example:
     *
     * import java.io.BufferedReader;
     * import java.io.FileReader;
     * import java.io.BufferedWriter;
     * import java.io.FileWriter;
     * import java.io.IOException;
     *
     * public class Example {
     *
     *     public static void main(String[] args) {
     *
     *         try (
     *             BufferedReader reader =
     *                 new BufferedReader(new FileReader("input.txt"));
     *
     *             BufferedWriter writer =
     *                 new BufferedWriter(new FileWriter("output.txt"))
     *         ) {
     *
     *             String line;
     *
     *             while ((line = reader.readLine()) != null) {
     *                 writer.write(line);
     *                 writer.newLine();
     *             }
     *
     *         }
     *         catch (IOException e) {
     *             System.out.println(e.getMessage());
     *         }
     *     }
     * }
     *
     * Both resources are automatically closed.
     *
     *
     * 9. IN WHICH ORDER ARE RESOURCES CLOSED?
     *
     * Resources are closed in the reverse order of initialization.
     *
     * Example:
     *
     * try (
     *     Resource A = new Resource("A");
     *     Resource B = new Resource("B");
     *     Resource C = new Resource("C")
     * ) {
     *     System.out.println("Inside try");
     * }
     *
     * Initialization order:
     *
     * A -> B -> C
     *
     * Closing order:
     *
     * C -> B -> A
     *
     * Why reverse order?
     *
     * Resources are often dependent on previously initialized
     * resources.
     *
     * Closing in reverse order helps release dependent resources
     * before the resources they depend on.
     *
     *
     * 10. WHAT IF INITIALIZATION OF A RESOURCE FAILS?
     *
     * Consider:
     *
     * try (
     *     Resource A = new Resource("A");
     *     Resource B = createResourceB();
     *     Resource C = new Resource("C")
     * ) {
     *     // Work
     * }
     *
     * Suppose:
     *
     * - A initializes successfully.
     * - B initialization throws an exception.
     * - C is never initialized.
     *
     * What happens?
     *
     * - The try body does not execute.
     * - A is closed.
     * - C is not closed because it was never initialized.
     * - The initialization exception propagates or is handled
     *   by an appropriate catch block.
     *
     * Any resource successfully initialized before the failure
     * is automatically closed.
     *
     *
     * 11. WHAT ARE EFFECTIVELY FINAL RESOURCES?
     *
     * Java 9 expanded try-with-resources.
     *
     * Before Java 9, resources generally had to be declared
     * directly inside the try resource specification.
     *
     * Java 9 allows an existing final or effectively final
     * variable to be used as a resource.
     *
     * Example:
     *
     * BufferedReader reader =
     *     new BufferedReader(new FileReader("data.txt"));
     *
     * try (reader) {
     *
     *     System.out.println(reader.readLine());
     *
     * }
     *
     * This is valid in Java 9 and later.
     *
     * Effectively final means the variable is not reassigned
     * after initialization.
     *
     * Example of invalid reassignment:
     *
     * BufferedReader reader = createReader();
     * reader = createAnotherReader();
     *
     * try (reader) {
     *     // Invalid because reader is not effectively final
     * }
     *
     * Note:
     * The resource variable cannot be reassigned inside
     * the resource specification or try block.
     *
     *
     * 12. WHAT HAPPENS IF THE TRY BLOCK THROWS AN EXCEPTION?
     *
     * Suppose:
     *
     * try (MyResource resource = new MyResource()) {
     *
     *     throw new RuntimeException("Main failure");
     *
     * }
     *
     * Java:
     *
     * 1. Encounters RuntimeException.
     * 2. Attempts to close the resource.
     * 3. Continues propagating the primary exception.
     *
     * What if close() also throws an exception?
     *
     * Java has a special mechanism for preserving both failures.
     *
     * This is called suppressed exceptions.
     *
     *
     * 13. WHAT ARE SUPPRESSED EXCEPTIONS?
     *
     * Consider:
     *
     * - The try block throws IOException.
     * - The close() method also throws IOException.
     *
     * Which exception should be the primary exception?
     *
     * The exception from the try body is kept as the primary
     * exception.
     *
     * The exception from close() is attached as a suppressed
     * exception.
     *
     * This prevents the original failure from being replaced
     * by the cleanup failure.
     *
     * The primary exception can be inspected using:
     *
     * e.getMessage();
     *
     * Suppressed exceptions can be inspected using:
     *
     * e.getSuppressed();
     *
     *
     * 14. UNDERSTANDING SUPPRESSED EXCEPTIONS WITH AN EXAMPLE
     *
     * class MyResource implements AutoCloseable {
     *
     *     public void use() throws Exception {
     *         throw new Exception("Primary failure");
     *     }
     *
     *     @Override
     *     public void close() throws Exception {
     *         throw new Exception("Close failure");
     *     }
     * }
     *
     * public class Example {
     *
     *     public static void main(String[] args) {
     *
     *         try (MyResource resource = new MyResource()) {
     *
     *             resource.use();
     *
     *         }
     *         catch (Exception e) {
     *
     *             System.out.println(
     *                 "Primary: " + e.getMessage()
     *             );
     *
     *             for (Throwable suppressed : e.getSuppressed()) {
     *
     *                 System.out.println(
     *                     "Suppressed: " + suppressed.getMessage()
     *                 );
     *             }
     *         }
     *     }
     * }
     *
     * Output:
     *
     * Primary: Primary failure
     * Suppressed: Close failure
     *
     * This is a key advantage of try-with-resources.
     *
     *
     * 15. WHAT IF ONLY close() THROWS AN EXCEPTION?
     *
     * If the try body completes normally but close() throws:
     *
     * - The close exception becomes the primary exception.
     * - It propagates to the appropriate catch block or caller.
     *
     * If multiple resources fail during closing, the first
     * close failure in closing order becomes primary.
     * Later close failures are suppressed.
     *
     *
     * 16. WHAT IF MULTIPLE RESOURCES THROW DURING CLOSING?
     *
     * Consider resources A, B, and C.
     *
     * Initialization:
     *
     * A -> B -> C
     *
     * Closing:
     *
     * C -> B -> A
     *
     * Suppose all three close() methods throw exceptions
     * and the try body completes normally.
     *
     * Then:
     *
     * - C's close exception becomes primary.
     * - B's close exception becomes suppressed.
     * - A's close exception becomes suppressed.
     *
     * If the try body already threw an exception, that body
     * exception remains primary and all close failures
     * are suppressed.
     *
     *
     * 17. TRY-WITH-RESOURCES VS FINALLY
     *
     * ------------------------------------------------------------
     * Feature              try-with-resources   finally
     * ------------------------------------------------------------
     * Closing resources    Automatic            Manual
     *
     * Syntax               Compact              More code
     *
     * Multiple resources  Supported            Manual handling
     *
     * Closing order       Reverse initialization Must be managed
     *
     * Close failures      Suppressed exceptions Can replace an
     *                                          earlier failure
     *
     * Introduced          Java 7                Java language
     *                                          feature
     * ------------------------------------------------------------
     *
     * Important:
     * finally is still useful for cleanup that is not naturally
     * represented by an AutoCloseable resource.
     *
     *
     * 18. HOW WAS RESOURCE MANAGEMENT DONE BEFORE JAVA 7?
     *
     * Before try-with-resources, developers commonly used
     * try-catch-finally.
     *
     * Example:
     *
     * FileReader reader = null;
     *
     * try {
     *     reader = new FileReader("data.txt");
     *     // Read file
     * }
     * catch (IOException e) {
     *     System.out.println(e.getMessage());
     * }
     * finally {
     *
     *     if (reader != null) {
     *         try {
     *             reader.close();
     *         }
     *         catch (IOException e) {
     *             System.out.println("Closing failed");
     *         }
     *     }
     * }
     *
     * Problems:
     *
     * - More code.
     * - Null checks.
     * - Separate handling for close failures.
     * - Risk of replacing an original exception.
     * - More difficult when managing multiple resources.
     *
     * Try-with-resources handles these concerns automatically.
     *
     *
     * 19. TRY-WITH-RESOURCES WITH JDBC
     *
     * JDBC resources commonly implement AutoCloseable.
     *
     * Examples:
     *
     * - Connection
     * - Statement
     * - PreparedStatement
     * - ResultSet
     *
     * Example:
     *
     * import java.sql.Connection;
     * import java.sql.PreparedStatement;
     * import java.sql.ResultSet;
     * import java.sql.SQLException;
     *
     * public class Example {
     *
     *     public static void main(String[] args) {
     *
     *         String sql = "SELECT id, name FROM users";
     *
     *         try (
     *             Connection con = getConnection();
     *             PreparedStatement ps = con.prepareStatement(sql);
     *             ResultSet rs = ps.executeQuery()
     *         ) {
     *
     *             while (rs.next()) {
     *                 System.out.println(rs.getString("name"));
     *             }
     *
     *         }
     *         catch (SQLException e) {
     *             System.out.println(e.getMessage());
     *         }
     *     }
     * }
     *
     * Note:
     * getConnection() is illustrative and must be implemented
     * to return a valid database connection.
     *
     * Resources close in this order:
     *
     * ResultSet -> PreparedStatement -> Connection
     *
     * Closing a JDBC connection obtained from a connection pool
     * normally returns it to the pool rather than physically
     * shutting down the database connection.
     *
     *
     * 20. IMPORTANT JAVA VERSION DIFFERENCES
     *
     * Java 7:
     * - Introduced try-with-resources.
     * - Resource variables could be declared inside try.
     *
     * Java 9:
     * - Allowed existing final or effectively final variables
     *   to be used directly in the resource specification.
     *
     * Example:
     *
     * BufferedReader reader = createReader();
     *
     * try (reader) {
     *     // Java 9+
     * }
     *
     *
     * 21. IMPORTANT RULES TO REMEMBER
     *
     * 1. Resource must implement AutoCloseable.
     *
     * 2. Closeable extends AutoCloseable.
     *
     * 3. close() is invoked automatically when the try statement
     *    exits.
     *
     * 4. Resources close in reverse initialization order.
     *
     * 5. Successfully initialized resources are closed even
     *    if a later resource initialization fails.
     *
     * 6. A resource that was never initialized is not closed.
     *
     * 7. Exceptions from close() are handled by the
     *    try-with-resources mechanism.
     *
     * 8. If the try body throws an exception, it remains primary
     *    and close failures are suppressed.
     *
     * 9. If the body succeeds but close() fails, the close
     *    failure propagates as the primary exception.
     *
     * 10. Java 9 allows existing effectively final resources.
     *
     *
     * 22. COMMON MISTAKES
     *
     * - Using an object that does not implement AutoCloseable.
     * - Forgetting that multiple resources close in reverse order.
     * - Assuming close() is called if resource initialization
     *   itself fails before producing a resource.
     * - Assuming close exceptions always replace body exceptions.
     * - Thinking Closeable and AutoCloseable are identical.
     * - Forgetting that Java 9 changed resource declaration rules.
     * - Manually closing a resource unnecessarily inside the
     *   try block and then continuing to use it.
     *
     *
     * 23. BEST PRACTICES
     *
     * - Prefer try-with-resources for AutoCloseable resources.
     * - Use meaningful resource scopes.
     * - Initialize resources in dependency order.
     * - Avoid manually closing resources already managed by
     *   try-with-resources unless there is a specific reason.
     * - Inspect suppressed exceptions when diagnosing
     *   complicated cleanup failures.
     * - Implement AutoCloseable carefully in custom resources.
     * - Make close() release resources reliably and safely.
     *
     *
     * 24. INTERVIEW QUESTIONS
     *
     * Q1. What is try-with-resources?
     *
     * Q2. Why was it introduced?
     *
     * Q3. What is AutoCloseable?
     *
     * Q4. What is Closeable?
     *
     * Q5. What is the difference between AutoCloseable
     *     and Closeable?
     *
     * Q6. Can we use multiple resources?
     *
     * Q7. In which order are resources closed?
     *
     * Q8. What happens if resource initialization fails?
     *
     * Q9. What are suppressed exceptions?
     *
     * Q10. What happens if both the try body and close()
     *      throw exceptions?
     *
     * Q11. What happens if only close() throws an exception?
     *
     * Q12. What is the difference between finally and
     *      try-with-resources?
     *
     * Q13. Can we use an existing resource variable?
     *
     * Q14. What changed in Java 9?
     *
     * Q15. How is try-with-resources useful in JDBC?
     *
     *
     * 25. QUICK REVISION
     *
     * try-with-resources:
     * Automatically closes resources.
     *
     * AutoCloseable:
     * Interface that defines the close() contract.
     *
     * Closeable:
     * I/O-specific interface extending AutoCloseable.
     *
     * Resource closing order:
     * Reverse of initialization order.
     *
     * Suppressed exception:
     * A secondary exception retained alongside a primary failure.
     *
     * Java 7:
     * Introduced try-with-resources.
     *
     * Java 9:
     * Allowed effectively final existing variables as resources.
     *
     * MEMORY TRICK:
     *
     * Open A -> Open B -> Open C
     *
     * Close C -> Close B -> Close A
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Try With Resources , Happy Learning ..!");
    }
}
