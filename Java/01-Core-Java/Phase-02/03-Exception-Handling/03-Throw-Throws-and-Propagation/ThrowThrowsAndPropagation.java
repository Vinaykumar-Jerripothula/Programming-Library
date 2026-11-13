
public class ThrowThrowsAndPropagation {

    /*
     * ============================================================
     *             THROW, THROWS AND EXCEPTION PROPAGATION
     * ============================================================
     *
     * 1. WHY DO WE NEED THROW AND THROWS?
     *
     * Consider a banking application.
     *
     * A user tries to withdraw ₹50,000 from an account that
     * contains only ₹10,000.
     *
     * Java cannot automatically understand every business rule.
     *
     * We need to explicitly identify the invalid condition
     * and communicate the problem.
     *
     * This is where throw and throws are useful.
     *
     * throw  -> Actually throws an exception object.
     * throws -> Declares exceptions that a method may propagate
     *           to its caller.
     *
     *
     * 2. WHAT IS throw?
     *
     * The throw keyword is used to explicitly throw an exception.
     *
     * Syntax:
     *
     * throw new ExceptionType("Message");
     *
     * Example:
     *
     * public static void validateAge(int age) {
     *
     *     if (age < 18) {
     *         throw new IllegalArgumentException(
     *             "Age must be at least 18"
     *         );
     *     }
     *
     *     System.out.println("Eligible");
     * }
     *
     * Explanation:
     *
     * 1. The method receives an age.
     * 2. It checks whether age is below 18.
     * 3. If invalid, an IllegalArgumentException object is created.
     * 4. The throw statement throws that object.
     * 5. Normal execution of the method stops at that point.
     * 6. The exception is handled by a matching caller-side
     *    catch block or continues propagating.
     *
     *
     * 3. WHAT CAN WE THROW?
     *
     * Java's throw statement requires an expression whose
     * value is a reference to a Throwable object.
     *
     * Valid examples:
     *
     * throw new Exception("Something went wrong");
     *
     * throw new RuntimeException("Unexpected condition");
     *
     * throw new ArithmeticException("Invalid calculation");
     *
     * Invalid:
     *
     * throw "Something went wrong";
     *
     * Why?
     * String does not extend Throwable.
     *
     * Important:
     * You can throw an existing Throwable reference too.
     *
     * Exception e = new Exception("Failure");
     * throw e;
     *
     *
     * 4. WHAT IS throws?
     *
     * The throws keyword is used in a method declaration
     * to declare checked exceptions that the method may pass
     * to its caller.
     *
     * Syntax:
     *
     * returnType methodName() throws ExceptionType {
     *     // Method body
     * }
     *
     * Example:
     *
     * import java.io.FileReader;
     * import java.io.IOException;
     *
     * public static void readFile() throws IOException {
     *
     *     FileReader reader = new FileReader("data.txt");
     * }
     *
     * Explanation:
     *
     * FileReader construction can throw FileNotFoundException,
     * which is a subclass of IOException.
     *
     * The method declares IOException using throws.
     *
     * The caller must handle the checked exception or declare
     * an appropriate exception itself.
     *
     * Note:
     * This example demonstrates declaration. In production,
     * use try-with-resources to close the reader.
     *
     *
     * 5. DIFFERENCE BETWEEN throw AND throws
     *
     * ------------------------------------------------------------
     * Feature          throw             throws
     * ------------------------------------------------------------
     * Purpose          Throws an         Declares exceptions
     *                  exception         a method may propagate
     *
     * Position         Inside method     Method declaration
     *                  or block
     *
     * Followed by      Throwable object  Exception type names
     *
     * Count            One object per    One or more types
     *                  statement         separated by commas
     *
     * Execution        Transfers control  Does not itself throw
     *                  due to exception  an exception
     *
     * Example          throw new         void read() throws
     *                  IOException();    IOException
     * ------------------------------------------------------------
     *
     * MEMORY TRICK:
     *
     * throw  -> I am throwing an exception.
     * throws -> This method declares that an exception
     *           may be passed to its caller.
     *
     *
     * 6. THROWING CHECKED EXCEPTIONS
     *
     * When a checked exception is thrown, Java requires it
     * to be caught or declared.
     *
     * Example:
     *
     * public static void validate() throws Exception {
     *     throw new Exception("Invalid data");
     * }
     *
     * The method declares the checked exception.
     *
     * Alternatively:
     *
     * public static void validate() {
     *
     *     try {
     *         throw new Exception("Invalid data");
     *     }
     *     catch (Exception e) {
     *         System.out.println(e.getMessage());
     *     }
     * }
     *
     * Here the checked exception is handled locally.
     *
     *
     * 7. THROWING UNCHECKED EXCEPTIONS
     *
     * Unchecked exceptions do not need to be caught or declared.
     *
     * Example:
     *
     * public static void validateAge(int age) {
     *
     *     if (age < 18) {
     *         throw new IllegalArgumentException(
     *             "Invalid age"
     *         );
     *     }
     * }
     *
     * No throws declaration is required.
     *
     * You can still declare an unchecked exception using throws,
     * but it is generally unnecessary.
     *
     *
     * 8. CAN WE USE MULTIPLE EXCEPTION TYPES WITH throws?
     *
     * Yes.
     *
     * Example:
     *
     * public static void process()
     *         throws IOException, SQLException {
     *
     *     // Operations that may throw these exceptions
     * }
     *
     * Multiple exception types are separated by commas.
     *
     * The caller must handle or declare the applicable
     * checked exceptions.
     *
     * If one declared type is a superclass of another,
     * the more specific type may be redundant.
     *
     *
     * 9. WHAT IS EXCEPTION PROPAGATION?
     *
     * Imagine three methods:
     *
     * main() -> methodA() -> methodB()
     *
     * Suppose methodB() throws an exception.
     *
     * If methodB() does not handle it:
     *
     * 1. The exception leaves methodB().
     * 2. Java looks for a matching handler in methodA().
     * 3. If methodA() does not handle it, the exception
     *    continues to main().
     * 4. If main() does not handle it, it reaches the
     *    uncaught-exception handling mechanism.
     *
     * This movement through method calls is called
     * exception propagation.
     *
     *
     * 10. UNDERSTANDING STACK UNWINDING
     *
     * Every method invocation creates an execution frame
     * on the thread's call stack.
     *
     * When an exception is thrown:
     *
     * - Java searches the current method for a matching handler.
     * - If none exists, the current method's frame is unwound.
     * - The caller's frame is examined.
     * - This continues until a handler is found or the
     *   exception reaches the thread's uncaught-exception handler.
     *
     * This process is called stack unwinding.
     *
     * Example:
     *
     * public static void main(String[] args) {
     *     methodA();
     * }
     *
     * static void methodA() {
     *     methodB();
     * }
     *
     * static void methodB() {
     *     int result = 10 / 0;
     * }
     *
     * Flow:
     *
     * main()
     *   |
     *   v
     * methodA()
     *   |
     *   v
     * methodB()
     *   |
     *   v
     * ArithmeticException
     *
     * If no method handles it, the exception moves back:
     *
     * methodB() -> methodA() -> main()
     *
     *
     * 11. HANDLING EXCEPTIONS AT DIFFERENT LEVELS
     *
     * An exception can be handled at any suitable level
     * in the call chain.
     *
     * Example:
     *
     * public static void main(String[] args) {
     *
     *     try {
     *         methodA();
     *     }
     *     catch (ArithmeticException e) {
     *         System.out.println("Handled in main");
     *     }
     * }
     *
     * static void methodA() {
     *     methodB();
     * }
     *
     * static void methodB() {
     *     int result = 10 / 0;
     * }
     *
     * Output:
     * Handled in main
     *
     * The exception originates in methodB(), passes through
     * methodA(), and is handled in main().
     *
     *
     * 12. PROPAGATION OF CHECKED EXCEPTIONS
     *
     * Checked exceptions have an additional compiler rule.
     *
     * If a method does not handle a checked exception,
     * it must declare it when required.
     *
     * Example:
     *
     * import java.io.IOException;
     *
     * static void methodB() throws IOException {
     *     throw new IOException("File failure");
     * }
     *
     * static void methodA() throws IOException {
     *     methodB();
     * }
     *
     * public static void main(String[] args) {
     *
     *     try {
     *         methodA();
     *     }
     *     catch (IOException e) {
     *         System.out.println(e.getMessage());
     *     }
     * }
     *
     * Explanation:
     *
     * methodB() declares IOException.
     * methodA() does not handle it, so it declares IOException.
     * main() handles it using catch.
     *
     * This demonstrates checked exception propagation.
     *
     *
     * 13. PROPAGATION OF UNCHECKED EXCEPTIONS
     *
     * Unchecked exceptions can propagate without any
     * throws declaration.
     *
     * Example:
     *
     * static void methodB() {
     *     int result = 10 / 0;
     * }
     *
     * static void methodA() {
     *     methodB();
     * }
     *
     * public static void main(String[] args) {
     *
     *     try {
     *         methodA();
     *     }
     *     catch (ArithmeticException e) {
     *         System.out.println("Handled");
     *     }
     * }
     *
     * No throws declaration is necessary.
     *
     *
     * 14. DOES PROPAGATION MEAN THE SAME EXCEPTION OBJECT?
     *
     * Normally, yes.
     *
     * When an exception propagates without being replaced,
     * the same throwable object moves through the call stack.
     *
     * Java does not automatically create a new exception
     * object at every method boundary.
     *
     * A method may deliberately create a different exception
     * while wrapping the original as its cause.
     *
     * This is called exception chaining and is covered in
     * Folder 04 and the advanced concepts folder.
     *
     *
     * 15. CAN WE HANDLE AN EXCEPTION AND THROW IT AGAIN?
     *
     * Yes. This is called rethrowing.
     *
     * Example:
     *
     * public static void process() {
     *
     *     try {
     *         int result = 10 / 0;
     *     }
     *     catch (ArithmeticException e) {
     *
     *         System.out.println("Logging the failure");
     *
     *         throw e;
     *     }
     * }
     *
     * The catch block receives the exception and throws
     * the same object again.
     *
     * The caller can then handle it.
     *
     * Important:
     * Rethrowing an exception is different from creating
     * a new exception object.
     *
     *
     * 16. THROWING A NEW EXCEPTION
     *
     * Example:
     *
     * catch (IOException e) {
     *     throw new RuntimeException("Operation failed", e);
     * }
     *
     * Here:
     *
     * - A new RuntimeException is created.
     * - The original IOException is stored as its cause.
     * - The caller receives the new RuntimeException.
     *
     * This is commonly called exception wrapping.
     *
     * Preserving the original cause is important for debugging.
     *
     *
     * 17. CAN WE THROW AN EXCEPTION FROM A CONSTRUCTOR?
     *
     * Yes.
     *
     * Constructors can throw checked and unchecked exceptions.
     *
     * Example:
     *
     * class User {
     *
     *     User(String username) throws Exception {
     *
     *         if (username == null || username.isBlank()) {
     *             throw new Exception("Invalid username");
     *         }
     *     }
     * }
     *
     * The code creating the object must handle or declare
     * the checked exception.
     *
     *
     * 18. CAN WE THROW EXCEPTIONS FROM STATIC METHODS?
     *
     * Yes.
     *
     * Static methods follow the same exception rules
     * as instance methods.
     *
     * Example:
     *
     * static void validate(int number) {
     *
     *     if (number < 0) {
     *         throw new IllegalArgumentException(
     *             "Number cannot be negative"
     *         );
     *     }
     * }
     *
     *
     * 19. CAN WE THROW AN EXCEPTION FROM main()?
     *
     * Yes.
     *
     * Example:
     *
     * public static void main(String[] args)
     *         throws IOException {
     *
     *     // Code that may throw IOException
     * }
     *
     * The JVM invokes main().
     *
     * If a checked exception is declared by main() and
     * not handled inside it, the exception can reach the
     * thread's uncaught-exception handling mechanism.
     *
     * Declaring throws does not mean the JVM handles the
     * exception successfully.
     *
     *
     * 20. EXCEPTION HANDLING IN METHOD OVERRIDING
     *
     * This is an important Java language rule.
     *
     * When overriding a method:
     *
     * - The overriding method may declare the same checked
     *   exception.
     * - It may declare a narrower checked exception.
     * - It may declare no checked exception.
     * - It cannot declare a broader checked exception than
     *   the overridden method permits.
     *
     * Example:
     *
     * class Parent {
     *
     *     void read() throws IOException {
     *     }
     * }
     *
     * class Child extends Parent {
     *
     *     @Override
     *     void read() throws FileNotFoundException {
     *     }
     * }
     *
     * Valid because FileNotFoundException extends IOException.
     *
     * Invalid:
     *
     * class Child extends Parent {
     *
     *     @Override
     *     void read() throws Exception {
     *     }
     * }
     *
     * Invalid because Exception is broader than IOException.
     *
     * Important:
     * These restrictions apply to checked exceptions.
     * Unchecked exceptions are not restricted in the same way.
     *
     * Constructors are not overridden; they are not subject
     * to method overriding rules.
     *
     *
     * 21. WHAT IF A PARENT METHOD DOES NOT DECLARE CHECKED EXCEPTIONS?
     *
     * If the parent method declares no checked exception,
     * the overriding method cannot introduce a new checked
     * exception in its throws clause.
     *
     * Example:
     *
     * class Parent {
     *     void display() {
     *     }
     * }
     *
     * class Child extends Parent {
     *
     *     @Override
     *     void display() throws IOException {
     *     }
     * }
     *
     * This is invalid.
     *
     * However, the overriding method can throw unchecked
     * exceptions because they are not subject to the same
     * checked-exception restriction.
     *
     *
     * 22. IMPORTANT DISTINCTION: HANDLING VS DECLARING
     *
     * Handling:
     *
     * try {
     *     riskyOperation();
     * }
     * catch (IOException e) {
     *     // Handle it here
     * }
     *
     * Declaring:
     *
     * void process() throws IOException {
     *     riskyOperation();
     * }
     *
     * Handling means the method takes responsibility
     * for the exception.
     *
     * Declaring means the method communicates that the
     * caller may need to handle it.
     *
     * A method can also handle an exception and then
     * deliberately throw another exception.
     *
     *
     * 23. COMMON MISTAKES
     *
     * 1. Confusing throw with throws.
     *
     * 2. Thinking throws itself throws an exception.
     *
     * 3. Thinking every exception needs a throws declaration.
     *
     * 4. Forgetting to declare an unhandled checked exception.
     *
     * 5. Thinking unchecked exceptions cannot propagate.
     *
     * 6. Creating a new exception without preserving the
     *    original cause when wrapping is intended.
     *
     * 7. Declaring a broader checked exception in an
     *    overriding method.
     *
     * 8. Assuming an exception is automatically handled
     *    just because a method declares it.
     *
     *
     * 24. BEST PRACTICES
     *
     * - Use throw to report an invalid condition explicitly.
     * - Choose meaningful exception types.
     * - Use IllegalArgumentException for invalid arguments.
     * - Use IllegalStateException for invalid object state.
     * - Use checked exceptions when callers can reasonably
     *   be expected to handle a recoverable condition.
     * - Use unchecked exceptions for programming errors
     *   and invalid API usage where appropriate.
     * - Do not declare overly broad exceptions without reason.
     * - Preserve the cause when wrapping exceptions.
     * - Handle exceptions at a layer that has enough context
     *   to make a meaningful decision.
     *
     *
     * 25. INTERVIEW QUESTIONS
     *
     * Q1. What is the difference between throw and throws?
     *
     * Q2. Can we use throw without throws?
     *
     * Q3. Can we use throws without throw?
     *
     * Q4. Can a method declare multiple exceptions?
     *
     * Q5. What is exception propagation?
     *
     * Q6. What is stack unwinding?
     *
     * Q7. How do checked exceptions propagate?
     *
     * Q8. Do unchecked exceptions require throws declarations?
     *
     * Q9. Can a constructor throw an exception?
     *
     * Q10. Can main() declare throws?
     *
     * Q11. Can an overriding method throw a broader checked
     *      exception than the parent method?
     *
     * Q12. Can an overriding method throw unchecked exceptions?
     *
     * Q13. What is rethrowing?
     *
     * Q14. What is exception wrapping?
     *
     * Q15. Does exception propagation create a new exception
     *      object at every method call?
     *
     *
     * 26. QUICK REVISION
     *
     * throw:
     * Explicitly throws a Throwable object.
     *
     * throws:
     * Declares exceptions in a method or constructor signature.
     *
     * Propagation:
     * Movement of an unhandled exception through the call stack.
     *
     * Stack unwinding:
     * The process of leaving method frames while searching
     * for an appropriate exception handler.
     *
     * Rethrowing:
     * Throwing an exception again, often the same object.
     *
     * Wrapping:
     * Throwing a new exception while preserving the original
     * exception as its cause.
     *
     * OVERRIDING RULE:
     * An overriding method cannot declare a broader checked
     * exception than the overridden method permits.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Throw Throws and Propagation , Happy Learning ..!");
    }
}
