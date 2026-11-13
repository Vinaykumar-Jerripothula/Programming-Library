
public class TryCatchFinally {

    /*
     * ============================================================
     *                    TRY, CATCH AND FINALLY
     * ============================================================
     *
     * 1. WHY DO WE NEED TRY, CATCH AND FINALLY?
     *
     * Imagine an application reads a file.
     *
     * What happens if the file does not exist?
     *
     * Without exception handling, the exception may terminate
     * the current thread's normal execution.
     *
     * Java provides three important blocks:
     *
     * try    -> Contains code that may cause an exception.
     * catch  -> Handles a matching exception.
     * finally-> Contains cleanup code that normally executes
     *           whether an exception occurs or not.
     *
     *
     * 2. BASIC SYNTAX
     *
     * try {
     *     // Risky code
     * }
     * catch (ExceptionType e) {
     *     // Exception-handling code
     * }
     * finally {
     *     // Cleanup code
     * }
     *
     * Important:
     * - A try block must be followed by at least one catch
     *   block or a finally block.
     * - A try block can have multiple catch blocks.
     * - A try block can have only finally and no catch.
     * - A try block can have catch blocks without finally.
     *
     *
     * 3. HOW DOES A TRY BLOCK WORK?
     *
     * Consider:
     *
     * try {
     *     int result = 10 / 2;
     *     System.out.println(result);
     *     System.out.println("Inside try");
     * }
     * catch (ArithmeticException e) {
     *     System.out.println("Exception occurred");
     * }
     *
     * Output:
     *
     * 5
     * Inside try
     *
     * When no exception occurs:
     * - Statements execute normally.
     * - Catch blocks are skipped.
     *
     * When an exception occurs:
     * - Remaining statements in that try block are skipped.
     * - Java searches for a matching catch block.
     *
     *
     * 4. HOW DOES A CATCH BLOCK WORK?
     *
     * A catch block handles an exception whose type matches
     * the catch parameter.
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
     * Output:
     * Cannot divide by zero
     *
     * The catch parameter:
     *
     * ArithmeticException e
     *
     * Here:
     * - ArithmeticException is the exception type.
     * - e is a reference to the exception object.
     *
     * Through e, we can access methods inherited from Throwable:
     *
     * e.getMessage();
     * e.toString();
     * e.printStackTrace();
     * e.getCause();
     *
     * Note:
     * Catching an exception does not automatically fix the
     * underlying problem. The catch block must take appropriate
     * action, such as recovery, reporting, or rethrowing.
     *
     *
     * 5. MULTIPLE CATCH BLOCKS
     *
     * One try block can have multiple catch blocks.
     *
     * This is useful when different exceptions need different
     * handling logic.
     *
     * Example:
     *
     * try {
     *     int[] arr = {10, 20, 30};
     *     int result = 10 / 0;
     *     System.out.println(arr[5]);
     * }
     * catch (ArithmeticException e) {
     *     System.out.println("Arithmetic problem");
     * }
     * catch (ArrayIndexOutOfBoundsException e) {
     *     System.out.println("Invalid array index");
     * }
     * catch (Exception e) {
     *     System.out.println("Some other exception occurred");
     * }
     *
     * Output:
     * Arithmetic problem
     *
     * Why?
     * The division throws ArithmeticException first.
     * The remaining statements in try are skipped.
     * The matching catch block executes.
     *
     * Important:
     * Only one matching catch block executes for a single
     * thrown exception in a given try statement.
     *
     *
     * 6. CATCH BLOCK ORDERING
     *
     * Catch blocks must be ordered from more specific exception
     * types to more general exception types.
     *
     * Correct:
     *
     * try {
     *     // Risky code
     * }
     * catch (ArithmeticException e) {
     *     // Specific exception
     * }
     * catch (RuntimeException e) {
     *     // More general exception
     * }
     * catch (Exception e) {
     *     // General exception
     * }
     *
     * Incorrect:
     *
     * try {
     *     // Risky code
     * }
     * catch (Exception e) {
     *     // General exception
     * }
     * catch (ArithmeticException e) {
     *     // Unreachable catch block
     * }
     *
     * Why is the second example invalid?
     *
     * ArithmeticException extends RuntimeException,
     * and RuntimeException extends Exception.
     *
     * Therefore, the first catch block can already catch
     * ArithmeticException.
     *
     * The second catch block can never be reached.
     *
     * The compiler reports an error.
     *
     * MEMORY RULE:
     * Child exception first, parent exception later.
     *
     *
     * 7. MULTI-CATCH
     *
     * Java allows us to handle multiple unrelated exception
     * types in a single catch block using the | operator.
     *
     * Syntax:
     *
     * catch (ExceptionType1 | ExceptionType2 e) {
     *     // Common handling
     * }
     *
     * Example:
     *
     * try {
     *     int[] arr = {10, 20, 30};
     *     System.out.println(arr[5]);
     * }
     * catch (ArithmeticException | ArrayIndexOutOfBoundsException e) {
     *     System.out.println("Invalid operation");
     * }
     *
     * Important rules:
     *
     * - The alternatives must not have a subclass/superclass
     *   relationship.
     * - The same exception type cannot be repeated.
     * - The multi-catch parameter is implicitly final.
     * - You cannot assign a new value to e.
     *
     * Invalid:
     *
     * catch (Exception | ArithmeticException e) {
     * }
     *
     * Why?
     * ArithmeticException is already a subclass of Exception.
     *
     * Multi-catch was introduced in Java 7.
     *
     *
     * 8. NESTED TRY BLOCKS
     *
     * A try block can be placed inside another try block.
     *
     * This is called nested try.
     *
     * Example:
     *
     * try {
     *
     *     System.out.println("Outer try");
     *
     *     try {
     *         int result = 10 / 0;
     *     }
     *     catch (ArithmeticException e) {
     *         System.out.println("Inner catch");
     *     }
     *
     *     System.out.println("After inner try");
     * }
     * catch (Exception e) {
     *     System.out.println("Outer catch");
     * }
     *
     * Output:
     *
     * Outer try
     * Inner catch
     * After inner try
     *
     * Explanation:
     * - The inner try throws ArithmeticException.
     * - The inner catch handles it.
     * - Execution continues after the inner try-catch.
     * - The outer catch does not execute.
     *
     * Another possibility:
     *
     * If the inner try does not handle an exception, it can
     * propagate to an appropriate outer catch block.
     *
     * Nested try blocks are useful when different operations
     * need separate handling boundaries.
     *
     *
     * 9. TRY WITHOUT CATCH
     *
     * Java allows try with finally and no catch block.
     *
     * Example:
     *
     * try {
     *     System.out.println("Inside try");
     * }
     * finally {
     *     System.out.println("Inside finally");
     * }
     *
     * Output:
     *
     * Inside try
     * Inside finally
     *
     * If an exception occurs in this try block:
     * - finally normally executes.
     * - The exception continues propagating if not handled
     *   by an enclosing handler.
     *
     * This form is useful when cleanup is required but the
     * current method does not intend to handle the exception.
     *
     *
     * 10. TRY WITHOUT CATCH AND WITHOUT FINALLY?
     *
     * Invalid:
     *
     * try {
     *     System.out.println("Hello");
     * }
     *
     * A try statement must be followed by catch or finally.
     *
     *
     * 11. WHAT IS FINALLY?
     *
     * finally is a block used for cleanup operations.
     *
     * It normally executes whether:
     * - The try block completes successfully.
     * - An exception occurs and is caught.
     * - An exception occurs and is not caught locally.
     * - A return statement executes in try or catch.
     *
     * Example:
     *
     * try {
     *     System.out.println("Try");
     * }
     * catch (Exception e) {
     *     System.out.println("Catch");
     * }
     * finally {
     *     System.out.println("Finally");
     * }
     *
     * Output:
     * Try
     * Finally
     *
     * finally is commonly associated with releasing resources
     * such as streams or database-related resources.
     *
     * Modern Java generally prefers try-with-resources
     * for resources that implement AutoCloseable.
     *
     *
     * 12. FINALLY EXECUTES WHEN NO EXCEPTION OCCURS
     *
     * Example:
     *
     * try {
     *     System.out.println("A");
     * }
     * catch (Exception e) {
     *     System.out.println("B");
     * }
     * finally {
     *     System.out.println("C");
     * }
     *
     * Output:
     * A
     * C
     *
     * The catch block is skipped, but finally executes.
     *
     *
     * 13. FINALLY EXECUTES WHEN AN EXCEPTION IS CAUGHT
     *
     * Example:
     *
     * try {
     *     int result = 10 / 0;
     * }
     * catch (ArithmeticException e) {
     *     System.out.println("Caught");
     * }
     * finally {
     *     System.out.println("Finally");
     * }
     *
     * Output:
     * Caught
     * Finally
     *
     *
     * 14. FINALLY EXECUTES WHEN AN EXCEPTION IS NOT CAUGHT
     *
     * Example:
     *
     * try {
     *     int result = 10 / 0;
     * }
     * finally {
     *     System.out.println("Finally");
     * }
     *
     * Output:
     * Finally
     *
     * After finally completes, ArithmeticException continues
     * propagating to the caller.
     *
     *
     * 15. FINALLY WITH RETURN
     *
     * Consider:
     *
     * public static int test() {
     *
     *     try {
     *         return 10;
     *     }
     *     finally {
     *         System.out.println("Finally");
     *     }
     * }
     *
     * Output:
     * Finally
     *
     * Return value:
     * 10
     *
     * Explanation:
     * The return expression is evaluated, but control must pass
     * through finally before the method actually returns.
     *
     * Important:
     * finally normally executes before the method returns.
     *
     *
     * 16. RETURN INSIDE FINALLY
     *
     * Consider:
     *
     * public static int test() {
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
     * Why?
     * A return from finally overrides the pending return from try.
     *
     * IMPORTANT:
     * Never return from finally.
     *
     * It can suppress:
     * - A return value from try or catch.
     * - An exception thrown from try or catch.
     *
     * This makes debugging extremely difficult.
     *
     *
     * 17. FINALLY WITH EXCEPTION
     *
     * Consider:
     *
     * try {
     *     throw new RuntimeException("Original exception");
     * }
     * finally {
     *     throw new RuntimeException("Finally exception");
     * }
     *
     * The exception thrown from finally replaces the exception
     * that was already propagating.
     *
     * The original exception may be lost as the primary failure.
     *
     * This is one reason why throwing exceptions from finally
     * should generally be avoided.
     *
     * Try-with-resources handles close failures differently
     * by preserving the primary exception and recording
     * close failures as suppressed exceptions.
     *
     *
     * 18. IS FINALLY GUARANTEED TO EXECUTE?
     *
     * No. finally normally executes during ordinary Java
     * control flow, but it is not absolutely guaranteed.
     *
     * Situations where it may not execute:
     *
     * 1. System.exit() terminates the JVM.
     *
     * 2. Runtime.getRuntime().halt() forcibly halts the JVM.
     *
     * 3. The process is forcibly terminated externally.
     *
     * 4. The machine or JVM crashes.
     *
     * 5. Power failure or similar system-level interruption.
     *
     * Therefore, do not treat finally as a guarantee that
     * cleanup will happen under every possible termination.
     *
     *
     * 19. FINALLY VS FINAL VS FINALIZE()
     *
     * final:
     * - Keyword.
     * - Used with variables, methods, and classes.
     *
     * finally:
     * - Block associated with exception handling.
     * - Used for cleanup code.
     *
     * finalize():
     * - A method historically associated with garbage collection.
     * - Deprecated for removal since Java 9.
     * - Unreliable for resource cleanup.
     *
     * Prefer try-with-resources for resource management.
     *
     *
     * 20. CAN WE HAVE MULTIPLE FINALLY BLOCKS?
     *
     * A single try statement cannot have multiple finally blocks.
     *
     * However, nested try statements can each have their own
     * finally block.
     *
     * Example:
     *
     * try {
     *     try {
     *         System.out.println("Inner try");
     *     }
     *     finally {
     *         System.out.println("Inner finally");
     *     }
     * }
     * finally {
     *     System.out.println("Outer finally");
     * }
     *
     * Output:
     *
     * Inner try
     * Inner finally
     * Outer finally
     *
     *
     * 21. CAN WE WRITE CATCH WITHOUT TRY?
     *
     * No.
     *
     * A catch block must be associated with a try statement.
     *
     * Invalid:
     *
     * catch (Exception e) {
     * }
     *
     *
     * 22. CAN WE WRITE FINALLY WITHOUT TRY?
     *
     * No.
     *
     * A finally block must be associated with a try statement.
     *
     *
     * 23. CAN WE WRITE CATCH AFTER FINALLY?
     *
     * No.
     *
     * Correct order:
     *
     * try {
     * }
     * catch (Exception e) {
     * }
     * finally {
     * }
     *
     * Incorrect order:
     *
     * try {
     * }
     * finally {
     * }
     * catch (Exception e) {
     * }
     *
     *
     * 24. EXCEPTION HANDLING EXECUTION FLOW
     *
     *                  try block
     *                      |
     *              Exception occurs?
     *                 /        \
     *               No          Yes
     *               |            |
     *        Skip catch blocks   Find matching catch
     *               |            |
     *               |        Matching catch found?
     *               |           /       \
     *               |         Yes        No
     *               |          |          |
     *               |      Execute catch |
     *               |          |          |
     *               +----------+----------+
     *                          |
     *                       finally
     *                          |
     *                    Continue flow
     *                  or propagate failure
     *
     * This is a simplified view. Return, break, continue,
     * and exceptions can also affect control flow.
     *
     *
     * 25. COMMON MISTAKES
     *
     * 1. Catching Exception before its subclasses.
     *
     * 2. Writing unrelated exception types in an invalid
     *    multi-catch relationship.
     *
     * 3. Keeping large amounts of unrelated code inside one try.
     *
     * 4. Catching an exception and doing nothing.
     *
     * 5. Returning from finally.
     *
     * 6. Throwing a new exception from finally and losing
     *    the original failure.
     *
     * 7. Assuming finally executes even after JVM termination.
     *
     * 8. Using finally for resource management when
     *    try-with-resources is more appropriate.
     *
     * 9. Catching Throwable or Error without a specific reason.
     *
     * 10. Assuming catch blocks execute for every exception.
     *
     *
     * 26. BEST PRACTICES
     *
     * - Catch the most specific exception you can handle.
     * - Keep try blocks focused on operations that may fail.
     * - Do not silently swallow exceptions.
     * - Do not expose internal stack traces to end users.
     * - Avoid returning from finally.
     * - Avoid throwing new exceptions from finally.
     * - Use try-with-resources for AutoCloseable resources.
     * - Preserve the original cause when wrapping exceptions.
     * - Log an exception at an appropriate boundary, not at
     *   every layer through which it propagates.
     *
     *
     * 27. INTERVIEW QUESTIONS
     *
     * Q1. Can we write try without catch?
     *
     * Q2. Can we write try without catch and finally?
     *
     * Q3. Can we have multiple catch blocks?
     *
     * Q4. Why should child exceptions be caught before parent
     *     exceptions?
     *
     * Q5. What is multi-catch? In which Java version was it
     *     introduced?
     *
     * Q6. Can we catch two exceptions with a parent-child
     *     relationship in one multi-catch?
     *
     * Q7. Does finally execute when an exception is not caught?
     *
     * Q8. Does finally execute when a return statement occurs?
     *
     * Q9. What happens if finally contains a return statement?
     *
     * Q10. What happens if finally throws an exception?
     *
     * Q11. Is finally guaranteed to execute?
     *
     * Q12. Can we write multiple finally blocks?
     *
     * Q13. Can catch exist without try?
     *
     * Q14. What is the difference between final, finally,
     *      and finalize()?
     *
     * Q15. What is the difference between finally and
     *      try-with-resources?
     *
     *
     * 28. QUICK REVISION
     *
     * try:
     * Contains code that may throw an exception.
     *
     * catch:
     * Handles a matching exception.
     *
     * finally:
     * Normally executes during control flow leaving try/catch,
     * including return and exception propagation.
     *
     * Multiple catch:
     * Handles different exception types separately.
     *
     * Multi-catch:
     * Handles multiple unrelated exception types in one block.
     *
     * Nested try:
     * A try block inside another try block.
     *
     * MOST IMPORTANT RULES:
     *
     * 1. Specific catch before general catch.
     * 2. Only one matching catch executes for a thrown exception.
     * 3. finally normally executes even when an exception propagates.
     * 4. finally is not guaranteed after JVM/process termination.
     * 5. Avoid return or throwing a new exception from finally.
     * 6. Prefer try-with-resources for AutoCloseable resources.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Try Catch Finally , Happy Learning ..!");
    }
}
