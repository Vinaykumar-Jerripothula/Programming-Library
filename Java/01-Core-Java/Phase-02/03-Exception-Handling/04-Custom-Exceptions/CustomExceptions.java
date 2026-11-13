
public class CustomExceptions {

    /*
     * ============================================================
     *                     CUSTOM EXCEPTIONS
     * ============================================================
     *
     * 1. WHAT IS A CUSTOM EXCEPTION?
     *
     * Java provides predefined exceptions such as:
     *
     * - IOException
     * - SQLException
     * - NullPointerException
     * - ArithmeticException
     * - IllegalArgumentException
     *
     * But imagine developing a banking application.
     *
     * A customer tries to withdraw ₹50,000 when the account
     * balance is only ₹10,000.
     *
     * Which predefined exception accurately describes this
     * business-specific situation?
     *
     * We could use IllegalArgumentException, but a more
     * meaningful exception would be:
     *
     * InsufficientBalanceException
     *
     * An exception class created by the programmer to represent
     * application-specific or business-specific failure conditions
     * is called a custom exception or user-defined exception.
     *
     *
     * 2. WHY DO WE NEED CUSTOM EXCEPTIONS?
     *
     * Custom exceptions help us:
     *
     * - Represent business-specific failures.
     * - Communicate errors using meaningful names.
     * - Separate different failure conditions.
     * - Attach useful information to an exception.
     * - Make application code easier to understand and maintain.
     * - Allow callers to handle specific application failures.
     *
     * Examples:
     *
     * Banking:
     * - InsufficientBalanceException
     * - AccountNotFoundException
     *
     * Authentication:
     * - InvalidCredentialsException
     * - UserNotFoundException
     *
     * E-commerce:
     * - ProductOutOfStockException
     * - InvalidCouponException
     *
     * College application:
     * - InvalidStudentIdException
     * - CourseNotAvailableException
     *
     *
     * 3. HOW DO WE CREATE A CUSTOM EXCEPTION?
     *
     * We create a class that extends an appropriate subclass
     * of Throwable.
     *
     * The two most common choices are:
     *
     * 1. Exception
     * 2. RuntimeException
     *
     * Extending Exception creates a checked exception,
     * provided the class does not extend RuntimeException.
     *
     * Extending RuntimeException creates an unchecked exception.
     *
     *
     * 4. CREATING A CHECKED CUSTOM EXCEPTION
     *
     * Example:
     *
     * class InvalidAgeException extends Exception {
     *
     *     public InvalidAgeException() {
     *         super();
     *     }
     *
     *     public InvalidAgeException(String message) {
     *         super(message);
     *     }
     * }
     *
     * Explanation:
     *
     * - InvalidAgeException is our custom class.
     * - It extends Exception.
     * - Therefore, it is a checked exception.
     * - The constructor accepts a message.
     * - super(message) passes that message to the parent
     *   Exception constructor.
     *
     *
     * 5. USING A CHECKED CUSTOM EXCEPTION
     *
     * Example:
     *
     * class InvalidAgeException extends Exception {
     *
     *     public InvalidAgeException(String message) {
     *         super(message);
     *     }
     * }
     *
     * class VotingService {
     *
     *     public static void validateAge(int age)
     *             throws InvalidAgeException {
     *
     *         if (age < 18) {
     *             throw new InvalidAgeException(
     *                 "Age must be at least 18"
     *             );
     *         }
     *
     *         System.out.println("Eligible to vote");
     *     }
     * }
     *
     * public class Example {
     *
     *     public static void main(String[] args) {
     *
     *         try {
     *             VotingService.validateAge(16);
     *         }
     *         catch (InvalidAgeException e) {
     *             System.out.println(e.getMessage());
     *         }
     *     }
     * }
     *
     * Output:
     * Age must be at least 18
     *
     * Notice:
     *
     * - The custom exception extends Exception.
     * - validateAge() declares it using throws.
     * - throw creates and throws the exception object.
     * - main() handles it using catch.
     *
     *
     * 6. CREATING AN UNCHECKED CUSTOM EXCEPTION
     *
     * Example:
     *
     * class InsufficientBalanceException
     *         extends RuntimeException {
     *
     *     public InsufficientBalanceException(String message) {
     *         super(message);
     *     }
     * }
     *
     * Because it extends RuntimeException, it is unchecked.
     *
     * The compiler does not require methods that throw it
     * to catch or declare it.
     *
     *
     * 7. USING AN UNCHECKED CUSTOM EXCEPTION
     *
     * Example:
     *
     * class InsufficientBalanceException
     *         extends RuntimeException {
     *
     *     public InsufficientBalanceException(String message) {
     *         super(message);
     *     }
     * }
     *
     * class BankAccount {
     *
     *     private double balance;
     *
     *     public BankAccount(double balance) {
     *         this.balance = balance;
     *     }
     *
     *     public void withdraw(double amount) {
     *
     *         if (amount > balance) {
     *             throw new InsufficientBalanceException(
     *                 "Insufficient account balance"
     *             );
     *         }
     *
     *         balance -= amount;
     *
     *         System.out.println("Withdrawal successful");
     *     }
     * }
     *
     * public class Example {
     *
     *     public static void main(String[] args) {
     *
     *         BankAccount account = new BankAccount(10000);
     *
     *         try {
     *             account.withdraw(50000);
     *         }
     *         catch (InsufficientBalanceException e) {
     *             System.out.println(e.getMessage());
     *         }
     *     }
     * }
     *
     * Output:
     * Insufficient account balance
     *
     * Notice:
     *
     * The withdraw() method does not need a throws declaration.
     * The caller can still catch the exception.
     *
     *
     * 8. CHECKED VS UNCHECKED CUSTOM EXCEPTIONS
     *
     * ------------------------------------------------------------
     * Feature             Checked          Unchecked
     * ------------------------------------------------------------
     * Parent class        Exception        RuntimeException
     *
     * Compiler checks    Yes               No
     *
     * Catch required     Yes, if not       No
     *                     declared
     *
     * throws required    Yes, if not       No
     *                     caught
     *
     * Typical use        Conditions       Invalid arguments,
     *                     callers may      invalid state,
     *                     reasonably       programming/API
     *                     handle           misuse
     * ------------------------------------------------------------
     *
     * Important:
     * These are design choices, not absolute rules for every
     * business condition.
     *
     * Choose based on the contract you want your API to provide.
     *
     *
     * 9. WHICH PARENT CLASS SHOULD WE EXTEND?
     *
     * Use Exception when:
     *
     * - You want a checked custom exception.
     * - Callers should be required to handle or declare it.
     *
     * Use RuntimeException when:
     *
     * - You want an unchecked custom exception.
     * - The compiler should not force callers to catch or declare it.
     *
     * Avoid extending Throwable directly in ordinary application
     * code because it creates a checked throwable outside the
     * usual Exception hierarchy.
     *
     * Avoid extending Error for normal business exceptions.
     *
     *
     * 10. COMMON CONSTRUCTORS IN CUSTOM EXCEPTIONS
     *
     * A well-designed custom exception often provides constructors
     * similar to those available in standard Java exceptions.
     *
     * Example:
     *
     * class PaymentException extends Exception {
     *
     *     public PaymentException() {
     *         super();
     *     }
     *
     *     public PaymentException(String message) {
     *         super(message);
     *     }
     *
     *     public PaymentException(Throwable cause) {
     *         super(cause);
     *     }
     *
     *     public PaymentException(String message,
     *                             Throwable cause) {
     *         super(message, cause);
     *     }
     * }
     *
     * These constructors support:
     *
     * 1. No detail message.
     * 2. A meaningful detail message.
     * 3. An original cause.
     * 4. Both a detail message and original cause.
     *
     * Important:
     * Calling super(message) initializes the detail message
     * inherited from Throwable.
     *
     *
     * 11. WHY DO WE CALL super(message)?
     *
     * Consider:
     *
     * class InvalidAgeException extends Exception {
     *
     *     public InvalidAgeException(String message) {
     *         super(message);
     *     }
     * }
     *
     * When we write:
     *
     * throw new InvalidAgeException("Invalid age");
     *
     * The message is passed to the parent Exception class.
     *
     * Therefore:
     *
     * e.getMessage()
     *
     * returns:
     *
     * Invalid age
     *
     * Without passing the message to the parent constructor,
     * the inherited getMessage() method would not automatically
     * know about a separate custom field that we created.
     *
     *
     * 12. CUSTOM EXCEPTION WITH ADDITIONAL FIELDS
     *
     * Sometimes an exception needs to carry structured information,
     * not just a message.
     *
     * Example:
     *
     * class InsufficientBalanceException
     *         extends RuntimeException {
     *
     *     private final double currentBalance;
     *     private final double requestedAmount;
     *
     *     public InsufficientBalanceException(
     *             double currentBalance,
     *             double requestedAmount) {
     *
     *         super("Insufficient balance");
     *
     *         this.currentBalance = currentBalance;
     *         this.requestedAmount = requestedAmount;
     *     }
     *
     *     public double getCurrentBalance() {
     *         return currentBalance;
     *     }
     *
     *     public double getRequestedAmount() {
     *         return requestedAmount;
     *     }
     * }
     *
     * Now the caller can access:
     *
     * e.getCurrentBalance();
     * e.getRequestedAmount();
     *
     * This can be useful when structured error details are
     * genuinely part of the application's error contract.
     *
     * Avoid placing sensitive information in exception messages
     * or exposing internal details to clients.
     *
     *
     * 13. EXCEPTION CHAINING
     *
     * Imagine a service calls a database repository.
     *
     * The repository throws SQLException.
     *
     * The service wants to communicate a higher-level problem:
     *
     * UserDataAccessException
     *
     * Instead of losing the original SQLException, we can
     * preserve it as the cause of the new exception.
     *
     * Example:
     *
     * class UserDataAccessException extends RuntimeException {
     *
     *     public UserDataAccessException(
     *             String message,
     *             Throwable cause) {
     *
     *         super(message, cause);
     *     }
     * }
     *
     * Usage:
     *
     * try {
     *     // Database operation
     * }
     * catch (SQLException e) {
     *     throw new UserDataAccessException(
     *         "Unable to retrieve user",
     *         e
     *     );
     * }
     *
     * Here:
     *
     * - SQLException is the original exception.
     * - UserDataAccessException is the new exception.
     * - The original exception is preserved as the cause.
     *
     * We can retrieve it using:
     *
     * Throwable cause = e.getCause();
     *
     * This is called exception chaining.
     *
     *
     * 14. WHY IS EXCEPTION CHAINING IMPORTANT?
     *
     * Without chaining:
     *
     * catch (SQLException e) {
     *     throw new RuntimeException("Database failure");
     * }
     *
     * The original SQLException is lost as the cause.
     *
     * With chaining:
     *
     * catch (SQLException e) {
     *     throw new RuntimeException("Database failure", e);
     * }
     *
     * The original cause is preserved.
     *
     * Benefits:
     *
     * - Maintains the original failure information.
     * - Makes debugging easier.
     * - Preserves lower-level technical details.
     * - Allows higher layers to expose meaningful errors.
     *
     *
     * 15. CUSTOM EXCEPTION NAMING CONVENTIONS
     *
     * Prefer meaningful names ending with Exception.
     *
     * Good:
     *
     * - UserNotFoundException
     * - InvalidCredentialsException
     * - InsufficientBalanceException
     * - ProductOutOfStockException
     * - PaymentProcessingException
     *
     * Avoid vague names:
     *
     * - MyException
     * - Error1
     * - ProblemException
     *
     * A class name should communicate the failure condition.
     *
     *
     * 16. CUSTOM EXCEPTIONS IN REAL-WORLD BACKEND APPLICATIONS
     *
     * Consider a Spring Boot application.
     *
     * Typical flow:
     *
     * Controller
     *     |
     *     v
     * Service
     *     |
     *     v
     * Repository
     *     |
     *     v
     * Database
     *
     * The service may detect a business-specific condition:
     *
     * if (user == null) {
     *     throw new UserNotFoundException("User not found");
     * }
     *
     * A global exception handler can translate the exception
     * into an appropriate HTTP response.
     *
     * For example:
     *
     * UserNotFoundException -> HTTP 404
     *
     * InvalidCredentialsException -> HTTP 401
     *
     * InvalidRequestException -> HTTP 400
     *
     * Important:
     * These mappings are application design decisions.
     * Java itself does not automatically map custom exceptions
     * to HTTP status codes.
     *
     * Spring Boot's @RestControllerAdvice and @ExceptionHandler
     * are commonly used for centralized API exception handling.
     *
     *
     * 17. WHEN SHOULD WE NOT CREATE A CUSTOM EXCEPTION?
     *
     * Do not create a custom exception for every possible
     * failure condition.
     *
     * Use existing exceptions when they accurately describe
     * the problem.
     *
     * Examples:
     *
     * IllegalArgumentException:
     * An argument is invalid.
     *
     * IllegalStateException:
     * An operation is invalid for the current object state.
     *
     * NullPointerException:
     * A null reference is used where an object is required.
     *
     * IOException:
     * An input/output operation fails.
     *
     * Create a custom exception when it provides meaningful
     * domain information or a useful handling boundary.
     *
     *
     * 18. COMMON MISTAKES
     *
     * 1. Extending Throwable when Exception or RuntimeException
     *    is more appropriate.
     *
     * 2. Extending Error for ordinary business failures.
     *
     * 3. Forgetting to call super(message).
     *
     * 4. Creating vague exception class names.
     *
     * 5. Losing the original cause while wrapping an exception.
     *
     * 6. Creating too many unnecessary exception classes.
     *
     * 7. Exposing passwords, tokens, or sensitive data in
     *    exception messages.
     *
     * 8. Using custom exceptions instead of appropriate
     *    existing Java exceptions without a good reason.
     *
     *
     * 19. BEST PRACTICES
     *
     * - Choose the correct parent class.
     * - Give the exception a meaningful name.
     * - Provide useful constructors.
     * - Preserve the original cause when wrapping failures.
     * - Keep exception messages clear and actionable.
     * - Add fields only when structured information is needed.
     * - Do not expose sensitive information.
     * - Avoid unnecessary custom exception classes.
     * - Document exceptions that are part of a public API contract.
     *
     *
     * 20. INTERVIEW QUESTIONS
     *
     * Q1. What is a custom exception?
     *
     * Q2. How do you create a checked custom exception?
     *
     * Q3. How do you create an unchecked custom exception?
     *
     * Q4. What is the difference between extending Exception
     *     and RuntimeException?
     *
     * Q5. Why do we call super(message)?
     *
     * Q6. Can a custom exception have additional fields?
     *
     * Q7. What is exception chaining?
     *
     * Q8. How do you preserve the original exception?
     *
     * Q9. Can we create a custom exception by extending Throwable?
     *
     * Q10. Should business exceptions extend Error?
     *
     * Q11. How are custom exceptions used in Spring Boot?
     *
     * Q12. When should we use IllegalArgumentException instead
     *      of creating a custom exception?
     *
     *
     * 21. QUICK REVISION
     *
     * Custom exception:
     * An application-defined exception class.
     *
     * Checked custom exception:
     * Extends Exception, directly or indirectly, without
     * extending RuntimeException.
     *
     * Unchecked custom exception:
     * Extends RuntimeException, directly or indirectly.
     *
     * super(message):
     * Passes the detail message to the parent Throwable hierarchy.
     *
     * Exception chaining:
     * Preserves an original exception as the cause of another.
     *
     * IMPORTANT:
     *
     * Extends Exception        -> Checked
     * Extends RuntimeException -> Unchecked
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Custom Exceptions , Happy Learning ..!");
    }
}
