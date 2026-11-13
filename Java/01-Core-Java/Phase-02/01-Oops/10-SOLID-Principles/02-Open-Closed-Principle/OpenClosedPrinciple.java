
public class OpenClosedPrinciple {


    /*
     * ============================================================
     * OPEN/CLOSED PRINCIPLE (OCP)
     * ============================================================
     *
     * 1. WHAT IS OCP?
     *
     * OCP is the second principle of the SOLID design principles.
     *
     * Definition:
     * Software entities (classes, modules, functions, etc.)
     * should be open for extension but closed for modification.
     *
     * This principle was introduced by Bertrand Meyer and later
     * popularized by Robert C. Martin.
     *
     * What does "open for extension" mean?
     *
     * We should be able to add new functionality to an existing
     * system without unnecessarily changing its existing,
     * working source code.
     *
     * What does "closed for modification" mean?
     *
     * Once a component is working, adding a new feature should
     * ideally not require modifying that component's existing
     * implementation.
     *
     * IMPORTANT:
     * Closed for modification does NOT mean that source code
     * can never be changed.
     *
     * Bug fixes, refactoring and genuine requirement changes
     * may still require modifications.
     *
     * The goal is to minimize changes to stable code when
     * introducing new behavior.
     *
     *
     * 2. UNDERSTANDING OCP WITH A SIMPLE EXAMPLE
     *
     * Imagine an application that calculates discounts.
     *
     * Initially, the application supports:
     *
     *   - Regular customers: 5% discount
     *
     * Later, the business introduces:
     *
     *   - Premium customers: 10% discount
     *   - VIP customers: 20% discount
     *
     * A poorly designed implementation might use if-else
     * conditions to check every customer type.
     *
     * Every time a new customer type is introduced, we must
     * modify the existing discount calculation class.
     *
     * This makes the class increasingly difficult to maintain.
     *
     *
     * 3. VIOLATION OF OCP
     *
     * Example:
     *
     * class DiscountCalculator {
     *
     *     public double calculateDiscount(
     *             String customerType, double amount) {
     *
     *         if (customerType.equals("REGULAR")) {
     *             return amount * 0.05;
     *         }
     *
     *         else if (customerType.equals("PREMIUM")) {
     *             return amount * 0.10;
     *         }
     *
     *         else if (customerType.equals("VIP")) {
     *             return amount * 0.20;
     *         }
     *
     *         return 0;
     *     }
     * }
     *
     * What happens when we introduce a GOLD customer?
     *
     * We must modify DiscountCalculator and add another
     * condition.
     *
     * What happens when we introduce a PLATINUM customer?
     *
     * We must modify the same class again.
     *
     * The class depends on knowing every possible customer type.
     *
     * As the number of types grows, the conditional logic grows.
     *
     * This is a typical sign that the design may be violating OCP.
     *
     * NOTE:
     * An if-else or switch statement does not automatically
     * violate OCP. The issue is whether every new variation
     * repeatedly forces changes to a stable component.
     *
     *
     * 4. APPLYING OCP USING ABSTRACTION
     *
     * Instead of making the calculator understand every
     * customer type, define a common abstraction.
     *
     * Example:
     *
     * interface DiscountStrategy {
     *
     *     double calculateDiscount(double amount);
     * }
     *
     * class RegularDiscount implements DiscountStrategy {
     *
     *     public double calculateDiscount(double amount) {
     *         return amount * 0.05;
     *     }
     * }
     *
     * class PremiumDiscount implements DiscountStrategy {
     *
     *     public double calculateDiscount(double amount) {
     *         return amount * 0.10;
     *     }
     * }
     *
     * class VipDiscount implements DiscountStrategy {
     *
     *     public double calculateDiscount(double amount) {
     *         return amount * 0.20;
     *     }
     * }
     *
     * class DiscountCalculator {
     *
     *     public double calculate(
     *             DiscountStrategy strategy, double amount) {
     *
     *         return strategy.calculateDiscount(amount);
     *     }
     * }
     *
     * Now suppose the business introduces GOLD customers.
     *
     * We can add:
     *
     * class GoldDiscount implements DiscountStrategy {
     *
     *     public double calculateDiscount(double amount) {
     *         return amount * 0.15;
     *     }
     * }
     *
     * We have extended the system by adding a new implementation.
     *
     * We did not need to modify DiscountCalculator.
     *
     * This is an example of applying OCP.
     *
     *
     * 5. HOW DOES THIS DESIGN WORK?
     *
     * The key idea is polymorphism.
     *
     * DiscountCalculator depends on the DiscountStrategy
     * abstraction rather than concrete discount classes.
     *
     * The calculator does not need to know whether it receives:
     *
     *   - RegularDiscount
     *   - PremiumDiscount
     *   - VipDiscount
     *   - GoldDiscount
     *
     * It only knows that the supplied object implements
     * DiscountStrategy.
     *
     * Each implementation provides its own discount behavior.
     *
     * This allows new behavior to be introduced without changing
     * the calculator's existing logic.
     *
     *
     * 6. OCP AND ABSTRACTION
     *
     * Abstraction helps us apply OCP.
     *
     * Instead of depending directly on specific implementations,
     * a component depends on a stable contract.
     *
     * New implementations can then be introduced behind that
     * contract.
     *
     * Common Java mechanisms include:
     *
     *   - Interfaces
     *   - Abstract classes
     *   - Inheritance
     *   - Composition
     *   - Polymorphism
     *
     * IMPORTANT:
     * OCP does not require an interface for every class.
     * Use abstraction where meaningful variation is expected.
     *
     *
     * 7. OCP IN REAL-WORLD JAVA BACKEND APPLICATIONS
     *
     * Consider a payment application.
     *
     * Initially, it supports credit card payments.
     *
     * Later, it must support:
     *
     *   - UPI
     *   - Net banking
     *   - Wallets
     *   - Debit cards
     *
     * A possible abstraction:
     *
     * interface PaymentMethod {
     *
     *     void pay(double amount);
     * }
     *
     * Implementations:
     *
     *   CreditCardPayment
     *   UpiPayment
     *   NetBankingPayment
     *   WalletPayment
     *
     * A payment service can work with the PaymentMethod
     * abstraction instead of containing separate payment
     * implementation logic for every payment type.
     *
     * This makes it easier to introduce new payment methods.
     *
     * In Spring applications, dependency injection can help
     * supply different implementations.
     *
     * For example:
     *
     * @Service
     * class PaymentService {
     *
     *     private final PaymentMethod paymentMethod;
     *
     *     PaymentService(PaymentMethod paymentMethod) {
     *         this.paymentMethod = paymentMethod;
     *     }
     *
     *     public void processPayment(double amount) {
     *         paymentMethod.pay(amount);
     *     }
     * }
     *
     * IMPORTANT:
     * If multiple PaymentMethod implementations are registered
     * as Spring beans, Spring needs a way to decide which one
     * to inject. We can use @Qualifier, @Primary, or inject
     * a collection or map of implementations, depending on
     * the application's design.
     *
     * The example illustrates the design idea, not a complete
     * payment-processing implementation.
     *
     *
     * 8. OCP AND THE STRATEGY DESIGN PATTERN
     *
     * The discount example is also an example of the
     * Strategy design pattern.
     *
     * Strategy pattern:
     * Defines a family of algorithms, encapsulates each one,
     * and makes them interchangeable.
     *
     * OCP:
     * Encourages extending behavior without repeatedly
     * modifying stable existing code.
     *
     * The Strategy pattern is one way to achieve OCP.
     *
     * However, OCP can be implemented using other techniques too.
     *
     *
     * 9. OCP DOES NOT MEAN NEVER MODIFY EXISTING CODE
     *
     * Consider a class with a genuine calculation bug.
     *
     * Fixing the bug requires modifying the class.
     *
     * That does not mean OCP has been violated.
     *
     * OCP is primarily about designing for anticipated
     * extensions and limiting the impact of new requirements.
     *
     * It is not a prohibition against all source-code changes.
     *
     *
     * 10. ADVANTAGES OF OCP
     *
     *   1. Extensibility:
     *      New behavior can be added more easily.
     *
     *   2. Maintainability:
     *      Stable existing code needs fewer changes.
     *
     *   3. Reduced regression risk:
     *      Less modification of working code can reduce
     *      the chance of introducing unrelated bugs.
     *
     *   4. Reusability:
     *      Abstractions can support multiple implementations.
     *
     *   5. Testability:
     *      Different implementations can be tested independently.
     *
     *   6. Flexibility:
     *      Implementations can be substituted or selected
     *      according to application requirements.
     *
     *
     * 11. DISADVANTAGES AND TRADE-OFFS
     *
     *   1. More abstractions:
     *      Interfaces and implementations introduce additional
     *      types and files.
     *
     *   2. More complexity:
     *      A simple problem can become unnecessarily complicated
     *      if we design for too many hypothetical extensions.
     *
     *   3. Abstraction design:
     *      Poorly designed abstractions can make future changes
     *      harder rather than easier.
     *
     *   4. Not every change can be predicted:
     *      An abstraction designed for one type of extension
     *      may not support a fundamentally different requirement.
     *
     *
     * 12. WHEN SHOULD WE APPLY OCP?
     *
     * Consider OCP when:
     *
     *   - A feature is likely to have multiple implementations.
     *   - New business rules are frequently introduced.
     *   - A class repeatedly changes to support new variations.
     *   - Different algorithms need to be interchangeable.
     *   - You want to add plugins or extensions to a system.
     *
     * Examples:
     *
     *   - Payment methods
     *   - Discount strategies
     *   - Notification channels
     *   - File export formats
     *   - Authentication providers
     *   - Shipping cost calculations
     *
     *
     * 13. WHEN SHOULD WE AVOID OVERAPPLYING OCP?
     *
     * Do not create abstractions for every possible future change.
     *
     * If a class is simple and unlikely to have multiple variations,
     * direct implementation may be easier to understand.
     *
     * Introduce abstractions when there is a real or reasonably
     * anticipated variation point.
     *
     * The goal is not to eliminate every modification.
     * The goal is to make expected extensions safer and easier.
     *
     *
     * 14. COMMON MISCONCEPTIONS
     *
     * Misconception 1:
     * Existing source code must never be modified.
     *
     * Reality:
     * Bug fixes and genuine changes can require modifications.
     *
     * Misconception 2:
     * OCP always requires interfaces.
     *
     * Reality:
     * Interfaces are one useful technique, not a mandatory rule.
     *
     * Misconception 3:
     * Every if-else statement violates OCP.
     *
     * Reality:
     * Conditional logic is not automatically a violation.
     * Repeated modification for every new variation is the
     * more important warning sign.
     *
     * Misconception 4:
     * OCP means creating a separate class for every feature.
     *
     * Reality:
     * Separate implementations are useful when they represent
     * meaningful variations in behavior.
     *
     * Misconception 5:
     * Following OCP guarantees that a system never needs changes.
     *
     * Reality:
     * No design principle can eliminate the need to modify
     * software as requirements evolve.
     *
     *
     * 15. INTERVIEW QUESTIONS
     *
     * Q1. What is the Open/Closed Principle?
     * A. Software entities should be open for extension but
     *    closed for modification.
     *
     * Q2. Who introduced OCP?
     * A. Bertrand Meyer introduced the principle.
     *    Robert C. Martin later popularized it.
     *
     * Q3. What does open for extension mean?
     * A. New behavior can be added without unnecessarily
     *    changing existing stable code.
     *
     * Q4. What does closed for modification mean?
     * A. Existing code should be protected from frequent
     *    modifications when new features are introduced.
     *
     * Q5. How can Java implement OCP?
     * A. Through abstraction, interfaces, inheritance,
     *    composition and polymorphism.
     *
     * Q6. Does OCP mean we cannot modify a class?
     * A. No. Bug fixes, refactoring and genuine changes
     *    may require modifications.
     *
     * Q7. Is Strategy an example of OCP?
     * A. Yes. Strategy can allow new algorithms to be added
     *    without modifying the component that uses them.
     *
     * Q8. Does every switch statement violate OCP?
     * A. No. It depends on whether new variations repeatedly
     *    force modifications to stable code.
     *
     * Q9. What is the relationship between OCP and polymorphism?
     * A. Polymorphism allows a component to work with different
     *    implementations through a common abstraction.
     *
     *
     * 16. QUICK REVISION
     *
     *   - OCP is the second SOLID principle.
     *   - Introduced by Bertrand Meyer.
     *   - Popularized by Robert C. Martin.
     *   - Open for extension.
     *   - Closed for modification.
     *   - Encourages stable abstractions and interchangeable
     *     implementations.
     *   - Polymorphism is a common way to apply OCP.
     *   - Strategy is one practical design pattern that supports it.
     *   - Avoid unnecessary abstractions.
     *
     * CORE IDEA:
     *
     * Add new behavior by extending the system rather than
     * repeatedly changing stable existing code.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Open-Closed Principle , Happy Learning ..!");
    }
}
