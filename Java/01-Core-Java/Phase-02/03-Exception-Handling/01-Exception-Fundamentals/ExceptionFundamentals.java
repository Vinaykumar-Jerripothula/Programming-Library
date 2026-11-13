
public class ExceptionFundamentals {

    /*
     * ============================================================
     *                 JAVA EXCEPTION FUNDAMENTALS
     * ============================================================
     *
     * 1. WHAT IS AN EXCEPTION?
     *
     * Imagine you are writing a program to divide two numbers.
     *
     * int result = 10 / 2;  // Works perfectly.
     * int result = 10 / 0;  // Problem!
     *
     * The second operation cannot be completed because division
     * by zero is mathematically undefined for integers.
     *
     * An exception is an object that represents an abnormal
     * condition or an event that interrupts the normal flow
     * of program execution.
     *
     * In simple words:
     * An exception tells Java that something went wrong while
     * the program was executing.
     *
     * Examples:
     * - Dividing an integer by zero.
     * - Accessing an array using an invalid index.
     * - Accessing a method through a null reference.
     * - Reading a file that does not exist.
     * - Connecting to a database that is unavailable.
     *
     *
     * 2. WHY DO WE NEED EXCEPTION HANDLING?
     *
     * Without proper exception handling, an unexpected problem
     * can interrupt the normal execution of a program.
     *
     * Exception handling gives us a mechanism to:
     *
     * - Detect exceptional situations.
     * - Handle recoverable problems.
     * - Display meaningful error messages.
     * - Release resources.
     * - Maintain application stability where possible.
     * - Propagate failures to an appropriate layer.
     *
     * Important:
     * Exception handling does not mean that every error can
     * be recovered from. Some failures are not safely recoverable.
     *
     *
     * 3. WHAT HAPPENS WHEN AN EXCEPTION OCCURS?
     *
     * Consider:
     *
     * int a = 10;
     * int b = 0;
     * int result = a / b;
     * System.out.println("Hello");
     *
     * When Java evaluates a / b:
     *
     * 1. The JVM detects an integer division by zero.
     * 2. An ArithmeticException object is created and thrown.
     * 3. Normal execution of the current statement stops.
     * 4. Java looks for an appropriate exception handler.
     * 5. If no handler is found, the exception propagates
     *    through the call stack.
     * 6. If it remains unhandled, the thread terminates after
     *    the uncaught-exception handling mechanism runs.
     *
     * Therefore, "Hello" is not printed in this example.
     *
     * The detailed handling and propagation rules are covered
     * in their respective folders.
     *
     *
     * 4. WHAT IS THE DIFFERENCE BETWEEN AN ERROR AND AN EXCEPTION?
     *
     * Both are represented by objects in Java's Throwable hierarchy,
     * but they generally represent different kinds of problems.
     *
     * ERROR:
     * - Generally represents serious problems involving the JVM,
     *   runtime environment, or application execution.
     * - Applications generally should not attempt to recover from
     *   these conditions using ordinary exception handling.
     *
     * Examples:
     * - OutOfMemoryError
     * - StackOverflowError
     * - NoClassDefFoundError
     * - AssertionError
     *
     * EXCEPTION:
     * - Represents conditions that an application may want
     *   to handle or communicate to its caller.
     *
     * Examples:
     * - IOException
     * - SQLException
     * - NullPointerException
     * - ArithmeticException
     *
     * Important:
     * Error is also a Java throwable object. It is not something
     * completely separate from exceptions in the type hierarchy.
     *
     * Also, not every Exception is recoverable, and not every
     * Error necessarily means the JVM itself is broken.
     *
     *
     * 5. THE COMPLETE JAVA EXCEPTION HIERARCHY
     *
     * Java provides a predefined hierarchy for representing
     * exceptional conditions.
     *
     *                         Object
     *                            |
     *                         Throwable
     *                         /       \
     *                      Error     Exception
     *                        |          |
     *                   Subclasses   IOException
     *                   of Error     SQLException
     *                                ClassNotFoundException
     *                                RuntimeException
     *                                      |
     *                                Subclasses of
     *                                RuntimeException
     *
     * This is a simplified view. The complete hierarchy contains
     * many more classes and intermediate subclasses.
     *
     *
     * 6. WHAT IS Throwable?
     *
     * Throwable is the root class of Java's exception hierarchy.
     *
     * Declaration:
     *
     * public class Throwable
     *     extends Object
     *     implements Serializable
     *
     * Both Error and Exception directly extend Throwable.
     *
     * Throwable provides common functionality for all objects
     * that can be thrown using Java's throw mechanism.
     *
     * Important methods:
     *
     * 1. getMessage()
     *    Returns the detail message associated with the throwable.
     *
     * 2. toString()
     *    Returns the class name and detail message.
     *
     * 3. printStackTrace()
     *    Prints the throwable and its stack trace, normally
     *    to System.err when no stream is specified.
     *
     * 4. getCause()
     *    Returns the original cause of this throwable, if present.
     *
     * 5. initCause()
     *    Initializes the cause of a throwable when permitted.
     *
     * 6. getStackTrace()
     *    Returns the stack trace elements.
     *
     * 7. fillInStackTrace()
     *    Records information about the current execution stack.
     *
     * 8. getSuppressed()
     *    Returns exceptions suppressed by this throwable.
     *
     * 9. addSuppressed()
     *    Adds an exception to this throwable's suppressed list.
     *
     * Note:
     * Cause and suppressed exceptions are different concepts.
     * They are especially useful when one failure occurs while
     * another failure is already being handled.
     *
     *
     * 7. WHAT IS Error?
     *
     * Error is a subclass of Throwable.
     *
     * Declaration:
     *
     * public class Error extends Throwable
     *
     * It represents serious problems that applications generally
     * should not try to handle through ordinary recovery logic.
     *
     * Common Error subclasses:
     *
     * 1. OutOfMemoryError
     *    JVM cannot allocate the required memory.
     *
     * 2. StackOverflowError
     *    A thread's stack is exhausted, commonly because of
     *    excessively deep or infinite recursion.
     *
     * 3. NoClassDefFoundError
     *    A class definition that was available during compilation
     *    or earlier execution cannot be found when required.
     *
     * 4. ExceptionInInitializerError
     *    An unexpected exception occurs during class initialization.
     *
     * 5. AssertionError
     *    An assertion fails.
     *
     * Important:
     * All Error subclasses are unchecked.
     *
     * This means the compiler does not require us to catch or
     * declare them.
     *
     * Generally, do not catch Error or Throwable broadly just
     * to keep an application running.
     *
     *
     * 8. WHAT IS Exception?
     *
     * Exception is a subclass of Throwable.
     *
     * Declaration:
     *
     * public class Exception extends Throwable
     *
     * It represents conditions that applications may want
     * to handle.
     *
     * Exception has two important categories:
     *
     * 1. Checked Exceptions
     * 2. Unchecked Exceptions
     *
     * This classification is based on the Java compiler's
     * exception-checking rules.
     *
     *
     * 9. WHAT ARE CHECKED EXCEPTIONS?
     *
     * Checked exceptions are exceptions that the compiler checks.
     *
     * If a method can throw a checked exception, the caller
     * generally must either:
     *
     * - Handle it using an appropriate catch block, or
     * - Declare it using a throws clause.
     *
     * Otherwise, compilation fails.
     *
     * Examples:
     *
     * - IOException
     * - SQLException
     * - ClassNotFoundException
     * - InterruptedException
     * - ParseException
     *
     * Example:
     *
     * import java.io.FileReader;
     * import java.io.IOException;
     *
     * public class Example {
     *
     *     public static void main(String[] args) {
     *
     *         try {
     *             FileReader reader = new FileReader("data.txt");
     *         }
     *         catch (IOException e) {
     *             System.out.println("File operation failed");
     *         }
     *     }
     * }
     *
     * FileReader construction can throw FileNotFoundException,
     * which is a subclass of IOException.
     *
     * Note:
     * This example demonstrates handling a checked exception.
     * Resource closing is covered in Try-With-Resources.
     *
     *
     * 10. WHAT ARE UNCHECKED EXCEPTIONS?
     *
     * Unchecked exceptions are not checked by the compiler.
     *
     * We are not required to catch or declare them.
     *
     * In Java, unchecked throwables include:
     *
     * - RuntimeException and all its subclasses.
     * - Error and all its subclasses.
     *
     * Common RuntimeException subclasses:
     *
     * 1. NullPointerException
     *    Accessing a member through a null reference.
     *
     * 2. ArithmeticException
     *    An illegal arithmetic operation, such as integer
     *    division by zero.
     *
     * 3. ArrayIndexOutOfBoundsException
     *    Accessing an array using an invalid index.
     *
     * 4. ClassCastException
     *    Performing an invalid runtime type cast.
     *
     * 5. IllegalArgumentException
     *    Passing an inappropriate argument to a method.
     *
     * 6. IllegalStateException
     *    Calling a method when the object's current state
     *    does not permit the operation.
     *
     * 7. NumberFormatException
     *    Converting an invalid string into a numeric value.
     *    It extends IllegalArgumentException.
     *
     * Example:
     *
     * public class Example {
     *
     *     public static void main(String[] args) {
     *
     *         int a = 10;
     *         int b = 0;
     *
     *         int result = a / b;
     *
     *         System.out.println(result);
     *     }
     * }
     *
     * This compiles successfully, but throws ArithmeticException
     * at runtime.
     *
     * The compiler does not force us to handle it.
     *
     *
     * 11. CHECKED VS UNCHECKED EXCEPTIONS
     *
     * ------------------------------------------------------------
     * Feature             Checked       Unchecked
     * ------------------------------------------------------------
     * Compiler checks     Yes           No
     * Catch required      Yes, if not   No
     *                     declared
     * Declare required    Yes, if not   No
     *                     caught
     * Main category       Exception     RuntimeException
     *                     excluding     subclasses
     *                     RuntimeException
     * Typical examples   IOException   NullPointerException
     *                     SQLException  ArithmeticException
     * ------------------------------------------------------------
     *
     * Important clarification:
     *
     * Unchecked does not mean that an exception cannot be caught.
     * It means the compiler does not require it to be caught
     * or declared.
     *
     * Also, checked exceptions are not necessarily errors that
     * can always be recovered from. They are a compiler-level
     * classification.
     *
     *
     * 12. HOW DOES JAVA IDENTIFY CHECKED AND UNCHECKED EXCEPTIONS?
     *
     * Remember this rule:
     *
     * Checked:
     * Subclasses of Exception, excluding RuntimeException
     * and all its subclasses.
     *
     * Unchecked:
     * RuntimeException and its subclasses, plus Error
     * and its subclasses.
     *
     * Examples:
     *
     * IOException
     *     -> Exception
     *     -> Checked
     *
     * SQLException
     *     -> Exception
     *     -> Checked
     *
     * NullPointerException
     *     -> RuntimeException
     *     -> Exception
     *     -> Unchecked
     *
     * NumberFormatException
     *     -> IllegalArgumentException
     *     -> RuntimeException
     *     -> Exception
     *     -> Unchecked
     *
     * OutOfMemoryError
     *     -> Error
     *     -> Unchecked
     *
     *
     * 13. EXCEPTION VS ERROR VS RUNTIME EXCEPTION
     *
     * Exception:
     * A class that represents exceptional conditions.
     *
     * Error:
     * A class representing serious conditions that applications
     * generally should not try to recover from.
     *
     * RuntimeException:
     * A subclass of Exception representing unchecked exceptions.
     *
     * Important:
     * RuntimeException IS an Exception.
     *
     * It is not a separate branch directly under Throwable.
     *
     *
     * 14. CAN WE CATCH UNCHECKED EXCEPTIONS?
     *
     * Yes.
     *
     * Unchecked means that handling is not compulsory.
     * It does not prohibit handling.
     *
     * Example:
     *
     * try {
     *     int result = 10 / 0;
     * }
     * catch (ArithmeticException e) {
     *     System.out.println("Cannot divide by zero");
     * }
     *
     * The compiler allows this.
     *
     *
     * 15. CAN WE THROW CHECKED AND UNCHECKED EXCEPTIONS?
     *
     * Yes.
     *
     * Both checked and unchecked exceptions can be thrown.
     *
     * The difference is in the compiler's handling requirements.
     *
     * A checked exception must be caught or declared.
     * An unchecked exception does not have this requirement.
     *
     * Detailed throw and throws rules are covered in Folder 03.
     *
     *
     * 16. CAN WE CREATE OUR OWN EXCEPTION?
     *
     * Yes.
     *
     * Java allows us to create custom exception classes.
     *
     * To create a checked custom exception:
     *
     * class InvalidAgeException extends Exception {
     *
     *     public InvalidAgeException(String message) {
     *         super(message);
     *     }
     * }
     *
     * To create an unchecked custom exception:
     *
     * class InvalidAgeException extends RuntimeException {
     *
     *     public InvalidAgeException(String message) {
     *         super(message);
     *     }
     * }
     *
     * The superclass determines whether the custom exception
     * is checked or unchecked.
     *
     * Custom exception design is covered in Folder 04.
     *
     *
     * 17. IMPORTANT RULES TO REMEMBER
     *
     * 1. Throwable is the root of Java's throwable hierarchy.
     *
     * 2. Throwable directly extends Object.
     *
     * 3. Error and Exception directly extend Throwable.
     *
     * 4. RuntimeException extends Exception.
     *
     * 5. All RuntimeException subclasses are unchecked.
     *
     * 6. All Error subclasses are unchecked.
     *
     * 7. Checked exceptions are Exception subclasses excluding
     *    RuntimeException and its subclasses.
     *
     * 8. Checked exceptions must be caught or declared when
     *    required by the compiler.
     *
     * 9. Unchecked exceptions can be caught, but the compiler
     *    does not require it.
     *
     * 10. An exception is an object, not just an error message.
     *
     * 11. throw is used to throw a throwable object.
     *
     * 12. throws is used in a method declaration to specify
     *     exceptions that may be propagated to its caller.
     *
     *
     * 18. COMMON MISCONCEPTIONS
     *
     * WRONG:
     * All exceptions are checked.
     *
     * CORRECT:
     * RuntimeException and its subclasses are unchecked.
     *
     * WRONG:
     * Errors are not part of the exception hierarchy.
     *
     * CORRECT:
     * Error extends Throwable.
     *
     * WRONG:
     * Unchecked exceptions cannot be handled.
     *
     * CORRECT:
     * They can be caught. Handling is simply not compulsory.
     *
     * WRONG:
     * RuntimeException directly extends Throwable.
     *
     * CORRECT:
     * RuntimeException extends Exception.
     *
     * WRONG:
     * Every exception indicates a programming mistake.
     *
     * CORRECT:
     * Some exceptions represent external conditions, such as
     * unavailable files, network failures, or database problems.
     *
     * WRONG:
     * Catching Throwable is a good way to handle every problem.
     *
     * CORRECT:
     * Catch specific exceptions where possible. Catching Error
     * or Throwable broadly can hide serious failures.
     *
     *
     * 19. INTERVIEW QUESTIONS
     *
     * Q1. What is an exception in Java?
     *
     * Q2. What is the root class of Java's exception hierarchy?
     *
     * Q3. What is the difference between Error and Exception?
     *
     * Q4. Is RuntimeException a checked or unchecked exception?
     *
     * Q5. Does RuntimeException extend Throwable directly?
     *
     * Q6. Is NullPointerException checked or unchecked?
     *
     * Q7. Is IOException checked or unchecked?
     *
     * Q8. Are all Error subclasses unchecked?
     *
     * Q9. Can we catch an unchecked exception?
     *
     * Q10. Can we create our own checked and unchecked exceptions?
     *
     * Q11. What happens if an exception is not handled?
     *
     * Q12. What is the difference between getMessage() and
     *      printStackTrace()?
     *
     * Q13. What is exception chaining?
     *
     * Q14. What are suppressed exceptions?
     *
     * Q15. Why should applications generally avoid catching Error?
     *
     *
     * 20. QUICK REVISION
     *
     * Throwable
     *    |
     *    |-- Error                  -> Unchecked
     *    |
     *    |-- Exception
     *          |
     *          |-- Checked exceptions
     *          |
     *          |-- RuntimeException -> Unchecked
     *                    |
     *                    |-- NullPointerException
     *                    |-- ArithmeticException
     *                    |-- ClassCastException
     *                    |-- IllegalArgumentException
     *                    |-- IllegalStateException
     *                    |-- NumberFormatException
     *
     * ONE-LINE MEMORY TRICK:
     *
     * Exception is the parent category.
     * RuntimeException is its unchecked branch.
     * Error is another unchecked branch under Throwable.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Exception Fundamentals , Happy Learning ..!");
    }
}
