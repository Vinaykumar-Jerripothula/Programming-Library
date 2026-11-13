
public class Strategy {

    /*
     * ============================================================
     * 1. INTRODUCTION
     * ============================================================
     *
     * Imagine you are developing an e-commerce application.
     *
     * A customer can pay using different payment methods:
     *
     * - Credit Card
     * - UPI
     * - Net Banking
     * - PayPal
     *
     * All these payment methods perform the same general task:
     *
     *     Process a payment.
     *
     * But each method follows a different algorithm.
     *
     * One approach is to write all payment logic inside
     * a single PaymentService using if-else or switch statements.
     *
     * As more payment methods are added, the service becomes
     * increasingly difficult to maintain.
     *
     * What if we could define each payment algorithm separately
     * and select the required algorithm at runtime?
     *
     * This is exactly what the Strategy pattern provides.
     *
     *
     * DEFINITION:
     *
     * Strategy is a behavioral design pattern that defines
     * a family of interchangeable algorithms, encapsulates
     * each algorithm in a separate class and makes them
     * interchangeable at runtime.
     *
     *
     * SIMPLE DEFINITION:
     *
     * Put different ways of performing a task into separate
     * classes and select the required behavior when needed.
     *
     *
     * ============================================================
     * 2. WHY DO WE NEED STRATEGY?
     * ============================================================
     *
     * Consider this implementation:
     *
     * class PaymentService {
     *
     *     public void pay(String type, double amount) {
     *
     *         if (type.equals("UPI")) {
     *
     *             System.out.println("Paying through UPI");
     *
     *         } else if (type.equals("CARD")) {
     *
     *             System.out.println("Paying through Card");
     *
     *         } else if (type.equals("PAYPAL")) {
     *
     *             System.out.println("Paying through PayPal");
     *         }
     *     }
     * }
     *
     *
     * PROBLEMS:
     *
     * 1. Every new payment method requires modifying this class.
     *
     * 2. Different algorithms are mixed into one class.
     *
     * 3. The class becomes difficult to test independently.
     *
     * 4. Conditional logic keeps growing.
     *
     * 5. The service knows the implementation details
     *    of every payment method.
     *
     *
     * STRATEGY SOLUTION:
     *
     * Create a common PaymentStrategy interface.
     *
     * Implement each payment method in its own class.
     *
     * The PaymentService depends on the interface,
     * not on individual payment implementations.
     *
     * Now we can change the payment algorithm without
     * changing the PaymentService.
     *
     *
     * ============================================================
     * 3. REAL-WORLD ANALOGY
     * ============================================================
     *
     * Imagine travelling from Hyderabad to Bengaluru.
     *
     * You can choose:
     *
     * - Bus
     * - Train
     * - Flight
     *
     * Your destination remains the same.
     *
     * Only the travel strategy changes.
     *
     * Each option has a different algorithm:
     *
     * Bus   -> Road route.
     * Train -> Railway route.
     * Flight -> Air route.
     *
     * You select the strategy depending on your requirements.
     *
     * Similarly, Strategy allows an application to select
     * one of several algorithms for the same task.
     *
     *
     * ============================================================
     * 4. STRUCTURE AND PARTICIPANTS
     * ============================================================
     *
     * Main participants:
     *
     *
     * 1. Strategy:
     *
     *    Defines a common interface for all algorithms.
     *
     *
     * 2. Concrete Strategies:
     *
     *    Implement different versions of the algorithm.
     *
     *    Examples:
     *
     *    - UpiPayment
     *    - CardPayment
     *    - PayPalPayment
     *
     *
     * 3. Context:
     *
     *    Maintains a reference to a Strategy object.
     *
     *    Delegates the actual work to the selected strategy.
     *
     *
     * 4. Client:
     *
     *    Selects or provides the required strategy.
     *
     *
     * STRUCTURE:
     *
     *                  Client
     *                    |
     *                    v
     *                 Context
     *                    |
     *                    v
     *                 Strategy
     *                    |
     *          ---------------------
     *          |         |         |
     *          v         v         v
     *       StrategyA StrategyB StrategyC
     *
     *
     * ============================================================
     * 5. COMPLETE JAVA IMPLEMENTATION
     * ============================================================
     *
     * Example: Payment Processing System.
     *
     *
     * STEP 1: Create the Strategy interface.
     *
     * interface PaymentStrategy {
     *
     *     void pay(double amount);
     * }
     *
     * This interface defines the common payment operation.
     *
     *
     * STEP 2: Create Concrete Strategies.
     *
     * A. UPI Payment
     *
     * class UpiPayment implements PaymentStrategy {
     *
     *     private String upiId;
     *
     *     public UpiPayment(String upiId) {
     *
     *         this.upiId = upiId;
     *     }
     *
     *     @Override
     *     public void pay(double amount) {
     *
     *         System.out.println(
     *             "Paid Rs." + amount + " using UPI: " + upiId
     *         );
     *     }
     * }
     *
     *
     * B. Card Payment
     *
     * class CardPayment implements PaymentStrategy {
     *
     *     private String cardNumber;
     *
     *     public CardPayment(String cardNumber) {
     *
     *         this.cardNumber = cardNumber;
     *     }
     *
     *     @Override
     *     public void pay(double amount) {
     *
     *         System.out.println(
     *             "Paid Rs." + amount + " using Card: "
     *             + cardNumber
     *         );
     *     }
     * }
     *
     *
     * C. PayPal Payment
     *
     * class PayPalPayment implements PaymentStrategy {
     *
     *     private String email;
     *
     *     public PayPalPayment(String email) {
     *
     *         this.email = email;
     *     }
     *
     *     @Override
     *     public void pay(double amount) {
     *
     *         System.out.println(
     *             "Paid Rs." + amount + " using PayPal: " + email
     *         );
     *     }
     * }
     *
     *
     * Each class implements the same interface,
     * but provides its own payment algorithm.
     *
     *
     * STEP 3: Create the Context.
     *
     * class PaymentService {
     *
     *     private PaymentStrategy strategy;
     *
     *     public PaymentService(PaymentStrategy strategy) {
     *
     *         this.strategy = strategy;
     *     }
     *
     *     public void setStrategy(PaymentStrategy strategy) {
     *
     *         this.strategy = strategy;
     *     }
     *
     *     public void checkout(double amount) {
     *
     *         strategy.pay(amount);
     *     }
     * }
     *
     * PaymentService is the Context.
     *
     * It does not know the internal implementation
     * of UPI, Card or PayPal payments.
     *
     * It only knows the PaymentStrategy interface.
     *
     *
     * STEP 4: Client code.
     *
     * PaymentService paymentService =
     *     new PaymentService(new UpiPayment("vinay@upi"));
     *
     * paymentService.checkout(1000);
     *
     * // Change strategy at runtime.
     *
     * paymentService.setStrategy(
     *     new CardPayment("1234-5678")
     * );
     *
     * paymentService.checkout(2000);
     *
     * paymentService.setStrategy(
     *     new PayPalPayment("vinay@example.com")
     * );
     *
     * paymentService.checkout(3000);
     *
     *
     * OUTPUT:
     *
     * Paid Rs.1000.0 using UPI: vinay@upi
     * Paid Rs.2000.0 using Card: 1234-5678
     * Paid Rs.3000.0 using PayPal: vinay@example.com
     *
     *
     * ============================================================
     * 6. UNDERSTANDING THE EXECUTION FLOW
     * ============================================================
     *
     * STEP 1:
     *
     * Client creates a PaymentService with UpiPayment.
     *
     * PaymentService stores the reference:
     *
     * strategy = UpiPayment object
     *
     *
     * STEP 2:
     *
     * paymentService.checkout(1000);
     *
     * Context calls:
     *
     * strategy.pay(1000);
     *
     * Since strategy refers to UpiPayment,
     * UpiPayment.pay() executes.
     *
     *
     * STEP 3:
     *
     * Client changes the strategy:
     *
     * paymentService.setStrategy(new CardPayment(...));
     *
     * Now:
     *
     * strategy = CardPayment object
     *
     *
     * STEP 4:
     *
     * paymentService.checkout(2000);
     *
     * The same Context delegates to CardPayment.
     *
     *
     * STEP 5:
     *
     * Client selects PayPal.
     *
     * The Context delegates to PayPalPayment.
     *
     *
     * IMPORTANT:
     *
     * The PaymentService object remains the same.
     *
     * Only its strategy reference changes.
     *
     * This is runtime behavior selection through polymorphism.
     *
     *
     * ============================================================
     * 7. WHY IS THIS CALLED RUNTIME STRATEGY SELECTION?
     * ============================================================
     *
     * Consider:
     *
     * PaymentStrategy strategy = new UpiPayment("vinay@upi");
     *
     * strategy.pay(1000);
     *
     * The reference type is:
     *
     * PaymentStrategy
     *
     * The actual object type is:
     *
     * UpiPayment
     *
     * Java uses runtime polymorphism to execute
     * UpiPayment.pay().
     *
     * If the actual object is CardPayment,
     * CardPayment.pay() executes instead.
     *
     * The Context does not need to know the concrete class.
     *
     *
     * ============================================================
     * 8. STRATEGY AND POLYMORPHISM
     * ============================================================
     *
     * Strategy relies heavily on:
     *
     * - Interfaces.
     * - Composition.
     * - Runtime polymorphism.
     *
     *
     * COMPOSITION:
     *
     * PaymentService HAS-A PaymentStrategy.
     *
     * It does not inherit from UpiPayment or CardPayment.
     *
     *
     * POLYMORPHISM:
     *
     * Different concrete strategy objects can be referenced
     * through the same PaymentStrategy interface.
     *
     *
     * This allows the Context to use different algorithms
     * without changing its own implementation.
     *
     *
     * ============================================================
     * 9. STRATEGY VS INHERITANCE
     * ============================================================
     *
     * Suppose we create:
     *
     * class UpiPaymentService extends PaymentService { }
     *
     * class CardPaymentService extends PaymentService { }
     *
     * This approach creates separate subclasses
     * for different payment behaviors.
     *
     * As the number of behaviors grows, the class hierarchy
     * can become difficult to manage.
     *
     *
     * STRATEGY USES COMPOSITION:
     *
     * class PaymentService {
     *
     *     private PaymentStrategy strategy;
     * }
     *
     * Different strategies can be supplied to the same
     * PaymentService object.
     *
     *
     * REMEMBER:
     *
     * Inheritance -> Reuse or specialize behavior through
     *                an IS-A relationship.
     *
     * Strategy -> Change behavior by composing an object
     *             with a different strategy.
     *
     *
     * ============================================================
     * 10. STRATEGY VS STATE DESIGN PATTERN
     * ============================================================
     *
     * Both patterns use:
     *
     * - Interfaces.
     * - Composition.
     * - Polymorphism.
     *
     * Their class structures can look very similar.
     *
     *
     * STRATEGY:
     *
     * Encapsulates interchangeable algorithms.
     *
     * Example:
     *
     * - UPI payment.
     * - Card payment.
     * - PayPal payment.
     *
     * The client or application chooses the algorithm.
     *
     *
     * STATE:
     *
     * Encapsulates behavior that depends on an object's
     * current internal state.
     *
     * Example:
     *
     * - Playing.
     * - Paused.
     * - Stopped.
     *
     * State objects may control transitions between states.
     *
     *
     * REMEMBER:
     *
     * Strategy -> Which algorithm should be used?
     *
     * State -> How should the object behave in its current state?
     *
     *
     * ============================================================
     * 11. STRATEGY VS FACTORY METHOD
     * ============================================================
     *
     * These patterns solve different problems.
     *
     *
     * STRATEGY:
     *
     * Selects and uses an algorithm.
     *
     * Example:
     *
     * Select UPI or Card payment processing.
     *
     *
     * FACTORY METHOD:
     *
     * Delegates object creation to a creator's factory method.
     *
     * Example:
     *
     * Create a UpiPayment or CardPayment object.
     *
     *
     * THEY CAN WORK TOGETHER:
     *
     * A Factory can create a suitable PaymentStrategy.
     *
     * PaymentService then executes that strategy.
     *
     *
     * REMEMBER:
     *
     * Factory Method -> How is an object created?
     *
     * Strategy -> Which algorithm performs the task?
     *
     *
     * ============================================================
     * 12. STRATEGY VS TEMPLATE METHOD
     * ============================================================
     *
     * Both patterns help vary an algorithm's behavior.
     *
     *
     * STRATEGY:
     *
     * Uses composition.
     *
     * Different algorithm implementations are placed
     * in separate strategy classes.
     *
     *
     * TEMPLATE METHOD:
     *
     * Uses inheritance.
     *
     * A base class defines the overall algorithm structure
     * and subclasses implement selected steps.
     *
     *
     * REMEMBER:
     *
     * Strategy -> Composition.
     *
     * Template Method -> Inheritance.
     *
     *
     * ============================================================
     * 13. STRATEGY USING LAMBDA EXPRESSIONS
     * ============================================================
     *
     * Java functional interfaces make simple strategies
     * easier to implement.
     *
     * Example:
     *
     * @FunctionalInterface
     * interface CalculatorStrategy {
     *
     *     int calculate(int a, int b);
     * }
     *
     *
     * CalculatorStrategy addition = (a, b) -> a + b;
     *
     * CalculatorStrategy subtraction = (a, b) -> a - b;
     *
     * CalculatorStrategy multiplication = (a, b) -> a * b;
     *
     *
     * class Calculator {
     *
     *     public int execute(
     *         int a,
     *         int b,
     *         CalculatorStrategy strategy
     *     ) {
     *
     *         return strategy.calculate(a, b);
     *     }
     * }
     *
     *
     * Client:
     *
     * Calculator calculator = new Calculator();
     *
     * System.out.println(
     *     calculator.execute(10, 5, addition)
     * );
     *
     * System.out.println(
     *     calculator.execute(10, 5, subtraction)
     * );
     *
     *
     * OUTPUT:
     *
     * 15
     * 5
     *
     *
     * WHY DOES THIS WORK?
     *
     * CalculatorStrategy is a functional interface.
     *
     * A lambda expression provides its implementation.
     *
     * The lambda can be passed wherever a
     * CalculatorStrategy is expected.
     *
     * Therefore, each lambda acts as a strategy.
     *
     *
     * IMPORTANT:
     *
     * Lambdas are useful for small, stateless or simple strategies.
     *
     * Separate classes may be clearer when a strategy has
     * substantial logic, dependencies or internal state.
     *
     *
     * ============================================================
     * 14. STRATEGY IN JAVA APIs
     * ============================================================
     *
     * Java's Comparator is a familiar example of
     * a strategy-style abstraction.
     *
     * Example:
     *
     * List<Integer> numbers =
     *     Arrays.asList(5, 2, 9, 1);
     *
     * Comparator<Integer> ascending =
     *     (a, b) -> Integer.compare(a, b);
     *
     * Comparator<Integer> descending =
     *     (a, b) -> Integer.compare(b, a);
     *
     * numbers.sort(ascending);
     *
     * numbers.sort(descending);
     *
     *
     * Comparator defines how two objects should be compared.
     *
     * Different Comparator implementations provide
     * different sorting strategies.
     *
     * Other strategy-style examples:
     *
     * - Predicate<T>: Defines a condition.
     * - Function<T, R>: Defines a transformation.
     * - UnaryOperator<T>: Defines an operation on one value.
     *
     * These functional interfaces can be used to supply
     * interchangeable behavior.
     *
     *
     * ============================================================
     * 15. STRATEGY IN SPRING BOOT
     * ============================================================
     *
     * Strategy is especially useful in backend applications
     * where multiple implementations perform the same task.
     *
     * Example: Different notification channels.
     *
     *
     * STEP 1: Define the Strategy.
     *
     * public interface NotificationStrategy {
     *
     *     void send(String message);
     * }
     *
     *
     * STEP 2: Implement Concrete Strategies.
     *
     * @Component("email")
     * public class EmailNotification
     *         implements NotificationStrategy {
     *
     *     public void send(String message) {
     *
     *         System.out.println("Email: " + message);
     *     }
     * }
     *
     *
     * @Component("sms")
     * public class SmsNotification
     *         implements NotificationStrategy {
     *
     *     public void send(String message) {
     *
     *         System.out.println("SMS: " + message);
     *     }
     * }
     *
     *
     * STEP 3: Create the Context / Service.
     *
     * @Service
     * public class NotificationService {
     *
     *     private final Map<String, NotificationStrategy>
     *         strategies;
     *
     *     public NotificationService(
     *         Map<String, NotificationStrategy> strategies
     *     ) {
     *
     *         this.strategies = strategies;
     *     }
     *
     *     public void notifyUser(
     *         String type,
     *         String message
     *     ) {
     *
     *         NotificationStrategy strategy =
     *             strategies.get(type);
     *
     *         if (strategy == null) {
     *             throw new IllegalArgumentException(
     *                 "Unsupported notification type: " + type
     *             );
     *         }
     *
     *         strategy.send(message);
     *     }
     * }
     *
     *
     * STEP 4: Use the service.
     *
     * notificationService.notifyUser(
     *     "email",
     *     "Welcome!"
     * );
     *
     * notificationService.notifyUser(
     *     "sms",
     *     "Your OTP is 123456"
     * );
     *
     *
     * SPRING EXPLANATION:
     *
     * Spring injects beans implementing
     * NotificationStrategy into the Map.
     *
     * The bean names become the map keys.
     *
     * The service selects the appropriate implementation.
     *
     * This is a practical Strategy-style design using
     * Spring dependency injection.
     *
     * IMPORTANT:
     *
     * The Map lookup still contains a selection decision.
     *
     * Strategy does not mean that all conditional or
     * selection logic disappears.
     *
     *
     * ============================================================
     * 16. STRATEGY AND SOLID PRINCIPLES
     * ============================================================
     *
     * Strategy is closely related to several SOLID principles.
     *
     *
     * A. OPEN/CLOSED PRINCIPLE
     *
     * New algorithms can often be added by implementing
     * the Strategy interface without modifying the Context.
     *
     *
     * B. DEPENDENCY INVERSION PRINCIPLE
     *
     * The Context depends on the Strategy abstraction
     * instead of concrete algorithm classes.
     *
     *
     * C. SINGLE RESPONSIBILITY PRINCIPLE
     *
     * Each Concrete Strategy owns one algorithm or
     * closely related behavior.
     *
     *
     * D. INTERFACE SEGREGATION PRINCIPLE
     *
     * The Strategy interface should contain only
     * the operations required by its implementations.
     *
     *
     * IMPORTANT:
     *
     * A design pattern can support SOLID principles,
     * but using a pattern does not automatically guarantee
     * a SOLID design.
     *
     *
     * ============================================================
     * 17. ADVANTAGES
     * ============================================================
     *
     * 1. Encapsulates algorithms in separate classes.
     *
     * 2. Allows algorithms to be exchanged at runtime.
     *
     * 3. Reduces conditional logic inside the Context.
     *
     * 4. Supports the Open/Closed Principle.
     *
     * 5. Makes algorithms easier to test independently.
     *
     * 6. Reduces dependency on concrete implementations.
     *
     * 7. Supports composition instead of large inheritance
     *    hierarchies.
     *
     *
     * ============================================================
     * 18. DISADVANTAGES
     * ============================================================
     *
     * 1. Introduces additional interfaces and classes.
     *
     * 2. The client or application still needs to select
     *    the appropriate strategy.
     *
     * 3. Too many strategies can make the design unnecessarily
     *    complex.
     *
     * 4. Different strategies may require different
     *    configuration or parameters.
     *
     * 5. The pattern may be unnecessary when the algorithm
     *    is simple and unlikely to change.
     *
     *
     * ============================================================
     * 19. WHEN SHOULD WE USE STRATEGY?
     * ============================================================
     *
     * Use Strategy when:
     *
     * - Multiple algorithms solve the same general problem.
     * - You need to switch algorithms at runtime.
     * - Conditional logic is becoming difficult to maintain.
     * - Algorithms should be tested independently.
     * - You want to add new algorithms without modifying
     *   the Context.
     *
     *
     * Avoid Strategy when:
     *
     * - There is only one simple algorithm.
     * - The behavior is unlikely to change.
     * - A simple method or switch is easier to understand.
     * - Creating multiple strategy classes adds more
     *   complexity than value.
     *
     *
     * ============================================================
     * 20. COMMON MISCONCEPTIONS
     * ============================================================
     *
     * 1. Strategy and State are exactly the same.
     *
     *    FALSE.
     *
     *    Strategy selects interchangeable algorithms.
     *    State represents behavior associated with
     *    an object's current state.
     *
     *
     * 2. Strategy always requires separate concrete classes.
     *
     *    FALSE.
     *
     *    Lambdas can implement simple functional strategies.
     *
     *
     * 3. Strategy eliminates all if-else statements.
     *
     *    FALSE.
     *
     *    The client may still need to select the strategy.
     *
     *
     * 4. Strategy uses inheritance between Context
     *    and Concrete Strategies.
     *
     *    FALSE.
     *
     *    The Context typically uses composition.
     *
     *
     * 5. Strategy is only useful for payment systems.
     *
     *    FALSE.
     *
     *    It is useful wherever multiple interchangeable
     *    algorithms perform a common task.
     *
     *
     * ============================================================
     * 21. INTERVIEW QUESTIONS
     * ============================================================
     *
     * Q1. What is Strategy Design Pattern?
     *
     * A: A behavioral pattern that encapsulates a family
     *    of interchangeable algorithms and allows them
     *    to be selected independently.
     *
     *
     * Q2. What are the main participants?
     *
     * A: Strategy, Concrete Strategy, Context and Client.
     *
     *
     * Q3. Why do we use Strategy?
     *
     * A: To separate algorithms, reduce conditional logic
     *    and make behavior interchangeable.
     *
     *
     * Q4. Does Strategy support runtime algorithm selection?
     *
     * A: Yes. The Context can receive or switch to
     *    another Strategy implementation.
     *
     *
     * Q5. Does Strategy use inheritance or composition?
     *
     * A: The Context uses composition to hold a Strategy.
     *    Concrete Strategies implement the common interface.
     *
     *
     * Q6. What is the difference between State and Strategy?
     *
     * A: Strategy encapsulates interchangeable algorithms.
     *    State encapsulates state-dependent behavior.
     *
     *
     * Q7. Can lambda expressions implement Strategy?
     *
     * A: Yes. A lambda can implement a functional interface
     *    representing a strategy.
     *
     *
     * Q8. How is Strategy useful in Spring Boot?
     *
     * A: Multiple Spring beans can implement a common
     *    strategy interface and be selected through
     *    dependency injection.
     *
     *
     * Q9. Which SOLID principles does Strategy support?
     *
     * A: It can support OCP, DIP, SRP and ISP when
     *    appropriately designed.
     *
     *
     * Q10. Give real-world examples.
     *
     * A: Payment processing, sorting, pricing rules,
     *    discount calculation, authentication and
     *    notification delivery.
     *
     *
     * ============================================================
     * 22. QUICK REVISION
     * ============================================================
     *
     * PATTERN:
     * Strategy
     *
     * CATEGORY:
     * Behavioral Design Pattern
     *
     * PURPOSE:
     * Encapsulate interchangeable algorithms.
     *
     * MAIN IDEA:
     * Select the required behavior through a common interface.
     *
     * PARTICIPANTS:
     *
     * Strategy
     * Concrete Strategy
     * Context
     * Client
     *
     * COMMON USE CASES:
     *
     * Payment Processing
     * Sorting
     * Discount Calculation
     * Notification Delivery
     * Authentication
     *
     * IMPORTANT CONCEPTS:
     *
     * Encapsulation
     * Composition
     * Runtime Polymorphism
     * Algorithm Selection
     * Functional Interfaces
     * Dependency Injection
     *
     * REMEMBER:
     *
     * Strategy -> Defines the algorithm contract.
     *
     * Concrete Strategy -> Implements an algorithm.
     *
     * Context -> Uses the selected strategy.
     *
     * Client -> Selects or supplies the strategy.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Strategy Design Pattern , Happy Learning ..!");
    }
}
