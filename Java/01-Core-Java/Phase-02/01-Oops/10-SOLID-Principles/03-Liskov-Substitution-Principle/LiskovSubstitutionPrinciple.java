
public class LiskovSubstitutionPrinciple {

    
    /*
     * ============================================================
     * LISKOV SUBSTITUTION PRINCIPLE (LSP)
     * ============================================================
     *
     * 1. WHAT IS LSP?
     *
     * LSP is the third principle of the SOLID design principles.
     *
     * Definition:
     *
     * Objects of a superclass should be replaceable with objects
     * of its subclasses without breaking the correctness of
     * the program.
     *
     * This principle was introduced by Barbara Liskov in 1987.
     * It was later incorporated into the SOLID principles by
     * Robert C. Martin.
     *
     * In simple words:
     *
     * If a program works with a parent type, it should continue
     * to work correctly when we pass an appropriate child object
     * instead.
     *
     * A child class should honor the behavior promised by
     * its parent class.
     *
     *
     * 2. UNDERSTANDING LSP WITH A SIMPLE EXAMPLE
     *
     * Imagine a parent class called Bird.
     *
     * We create two child classes:
     *
     *   - Sparrow
     *   - Penguin
     *
     * Both are birds.
     *
     * But consider this parent class:
     *
     * class Bird {
     *
     *     public void fly() {
     *         System.out.println("Bird is flying");
     *     }
     * }
     *
     * class Sparrow extends Bird {
     *
     *     // Sparrow can fly.
     * }
     *
     * class Penguin extends Bird {
     *
     *     // Penguin cannot fly.
     *
     *     @Override
     *     public void fly() {
     *         throw new UnsupportedOperationException(
     *             "Penguins cannot fly"
     *         );
     *     }
     * }
     *
     * Now consider:
     *
     * void makeBirdFly(Bird bird) {
     *     bird.fly();
     * }
     *
     * We can pass a Sparrow:
     *
     * makeBirdFly(new Sparrow());
     *
     * It works.
     *
     * But passing a Penguin causes an exception:
     *
     * makeBirdFly(new Penguin());
     *
     * Why?
     *
     * Because the parent class promised that every Bird can fly,
     * but Penguin cannot satisfy that promise.
     *
     * The Penguin object cannot safely substitute for every
     * Bird object in code that expects flying behavior.
     *
     * This is an LSP violation.
     *
     * IMPORTANT:
     * The problem is not that penguins cannot fly.
     * The problem is that the parent abstraction promises
     * a behavior that is not valid for all its subclasses.
     *
     *
     * 3. APPLYING LSP - BETTER DESIGN
     *
     * Instead of putting fly() in the general Bird class,
     * separate flying behavior into its own abstraction.
     *
     * Example:
     *
     * class Bird {
     *
     *     public void eat() {
     *         System.out.println("Bird is eating");
     *     }
     * }
     *
     * interface Flyable {
     *
     *     void fly();
     * }
     *
     * class Sparrow extends Bird implements Flyable {
     *
     *     @Override
     *     public void fly() {
     *         System.out.println("Sparrow is flying");
     *     }
     * }
     *
     * class Penguin extends Bird {
     *
     *     // Penguin does not implement Flyable.
     * }
     *
     * Now:
     *
     * void makeBirdEat(Bird bird) {
     *     bird.eat();
     * }
     *
     * void makeBirdFly(Flyable bird) {
     *     bird.fly();
     * }
     *
     * Any object passed to makeBirdFly() guarantees the
     * flying behavior through the Flyable contract.
     *
     * Penguin is still a Bird, but it is not forced to
     * implement a behavior it cannot support.
     *
     * This design respects the behavior expected from
     * each abstraction.
     *
     *
     * 4. WHY DOES LSP EXIST?
     *
     * Inheritance and polymorphism allow us to write code
     * that works with parent types instead of concrete classes.
     *
     * For example:
     *
     * List<Animal> animals = new ArrayList<>();
     *
     * Code can work with Animal without knowing the exact
     * subclass of every object.
     *
     * This flexibility is useful only when subclasses behave
     * consistently with the contracts of their parent types.
     *
     * If a subclass unexpectedly changes the meaning of
     * inherited operations, code using the parent type may break.
     *
     * LSP helps us design reliable inheritance hierarchies
     * and polymorphic APIs.
     *
     *
     * 5. CLASSIC LSP EXAMPLE - RECTANGLE AND SQUARE
     *
     * A rectangle has:
     *
     *   - Width
     *   - Height
     *
     * Its area is:
     *
     *   Area = Width * Height
     *
     * A square is mathematically a special kind of rectangle.
     *
     * But making Square inherit from a mutable Rectangle
     * can cause behavioral problems.
     *
     * Consider:
     *
     * class Rectangle {
     *
     *     protected int width;
     *     protected int height;
     *
     *     public void setWidth(int width) {
     *         this.width = width;
     *     }
     *
     *     public void setHeight(int height) {
     *         this.height = height;
     *     }
     *
     *     public int getArea() {
     *         return width * height;
     *     }
     * }
     *
     * class Square extends Rectangle {
     *
     *     @Override
     *     public void setWidth(int width) {
     *         this.width = width;
     *         this.height = width;
     *     }
     *
     *     @Override
     *     public void setHeight(int height) {
     *         this.width = height;
     *         this.height = height;
     *     }
     * }
     *
     * Now imagine client code:
     *
     * void calculateArea(Rectangle rectangle) {
     *
     *     rectangle.setWidth(5);
     *     rectangle.setHeight(10);
     *
     *     System.out.println(rectangle.getArea());
     * }
     *
     * With a normal Rectangle:
     *
     *   Width  = 5
     *   Height = 10
     *   Area   = 50
     *
     * With a Square:
     *
     *   setWidth(5)  -> Width = 5, Height = 5
     *   setHeight(10)-> Width = 10, Height = 10
     *
     *   Area = 100
     *
     * The client expected an area of 50 but received 100.
     *
     * The Square subclass changed the behavior expected from
     * the Rectangle abstraction.
     *
     * This is a classic LSP problem.
     *
     * IMPORTANT:
     * The mathematical relationship between two types does not
     * automatically mean that inheritance is appropriate.
     *
     * Behavioral substitutability matters more than simply
     * sharing a real-world category.
     *
     *
     * 6. LSP AND DESIGN BY CONTRACT
     *
     * LSP is closely related to Design by Contract.
     *
     * A contract describes:
     *
     *   - Preconditions
     *   - Postconditions
     *   - Invariants
     *
     * PRECONDITION:
     *
     * A condition that must be true before a method executes.
     *
     * POSTCONDITION:
     *
     * A condition that must be true after a method executes
     * successfully.
     *
     * INVARIANT:
     *
     * A condition that must remain true for an object throughout
     * its valid lifetime.
     *
     * LSP requires subclasses to respect the behavioral contract
     * of their parent types.
     *
     *
     * 7. LSP CONTRACT RULES
     *
     * RULE 1: A subclass must not strengthen preconditions.
     *
     * Meaning:
     * A subclass should not require more from the caller than
     * the parent class requires.
     *
     * Example:
     *
     * Parent method accepts any positive number.
     *
     * A subclass should not suddenly require that the number
     * must be greater than 100.
     *
     *
     * RULE 2: A subclass must not weaken postconditions.
     *
     * Meaning:
     * A subclass should not guarantee less than the parent
     * method promises.
     *
     * Example:
     *
     * Parent method promises to return a non-null result.
     *
     * A subclass should not return null for valid inputs.
     *
     *
     * RULE 3: A subclass must preserve invariants.
     *
     * Meaning:
     * A subclass must preserve the valid state guarantees
     * expected by the parent abstraction.
     *
     *
     * RULE 4: A subclass should preserve behavioral expectations.
     *
     * It should not unexpectedly:
     *
     *   - Throw unsupported-operation exceptions for promised
     *     operations.
     *   - Change the meaning of inherited methods.
     *   - Break valid client assumptions.
     *   - Introduce unexpected side effects.
     *
     *
     * 8. EXCEPTION RULES IN JAVA
     *
     * Java enforces some overriding rules at compile time.
     *
     * A subclass cannot declare a broader checked exception
     * than the overridden parent method declares.
     *
     * Example:
     *
     * class Parent {
     *
     *     public void process() throws IOException {
     *     }
     * }
     *
     * class Child extends Parent {
     *
     *     @Override
     *     public void process() throws FileNotFoundException {
     *     }
     * }
     *
     * This is valid because FileNotFoundException is a subclass
     * of IOException.
     *
     * But declaring SQLException in the overriding method
     * would not be allowed because it is an unrelated checked
     * exception.
     *
     * A subclass may also remove the checked exception
     * declaration or declare a narrower checked exception.
     *
     * Runtime exceptions are not restricted by this checked
     * exception rule.
     *
     * IMPORTANT:
     * Satisfying Java's compiler rules does not automatically
     * guarantee LSP.
     *
     * LSP is a behavioral design principle, not merely
     * a collection of compiler restrictions.
     *
     *
     * 9. LSP AND METHOD OVERRIDING
     *
     * Method overriding enables runtime polymorphism.
     *
     * But a subclass should override methods without breaking
     * the expectations established by the parent contract.
     *
     * A technically valid override can still violate LSP.
     *
     * Example:
     *
     * class Account {
     *
     *     public void withdraw(double amount) {
     *         // Withdrawal logic
     *     }
     * }
     *
     * class ReadOnlyAccount extends Account {
     *
     *     @Override
     *     public void withdraw(double amount) {
     *         throw new UnsupportedOperationException();
     *     }
     * }
     *
     * If clients expect every Account to support withdrawal,
     * ReadOnlyAccount cannot safely substitute for Account.
     *
     * A better design may separate readable and withdrawable
     * account capabilities into different abstractions.
     *
     *
     * 10. LSP AND INTERFACES
     *
     * LSP applies to interfaces as well as class inheritance.
     *
     * If a class implements an interface, it should honor
     * the behavior promised by that interface.
     *
     * Example:
     *
     * interface PaymentProcessor {
     *
     *     void processPayment(double amount);
     * }
     *
     * Any implementation should provide behavior consistent
     * with the PaymentProcessor contract.
     *
     * An implementation that silently ignores payments or
     * unexpectedly rejects all valid requests may violate
     * the intended contract.
     *
     * Therefore, LSP is about behavioral substitutability,
     * not only parent-child class relationships.
     *
     *
     * 11. LSP IN REAL-WORLD JAVA BACKEND APPLICATIONS
     *
     * Consider a notification application.
     *
     * interface NotificationSender {
     *
     *     void send(String message);
     * }
     *
     * Implementations:
     *
     *   EmailNotificationSender
     *   SmsNotificationSender
     *   PushNotificationSender
     *
     * A service can depend on NotificationSender.
     *
     * Each implementation should honor the contract:
     *
     *   - Accept valid messages according to the contract.
     *   - Perform the expected sending operation.
     *   - Report failures consistently.
     *
     * If one implementation silently does nothing, it may
     * break client expectations.
     *
     * In Spring Boot, these implementations can be managed
     * as beans and selected through dependency injection.
     *
     *
     * 12. HOW TO IDENTIFY AN LSP VIOLATION
     *
     * Ask these questions:
     *
     *   1. Can I replace the parent object with a subclass
     *      without breaking client code?
     *
     *   2. Does the subclass honor the parent's method contracts?
     *
     *   3. Does the subclass require stricter input conditions?
     *
     *   4. Does the subclass provide weaker guarantees?
     *
     *   5. Does the subclass reject behavior that the parent
     *      promises to support?
     *
     *   6. Does the subclass preserve the parent's invariants?
     *
     * If the answer reveals broken expectations, review
     * the inheritance or interface design.
     *
     *
     * 13. ADVANTAGES OF LSP
     *
     *   - Reliable polymorphism.
     *   - Safer inheritance hierarchies.
     *   - More reusable components.
     *   - Predictable behavior.
     *   - Easier testing through parent abstractions.
     *   - Fewer unexpected runtime failures.
     *
     *
     * 14. COMMON MISCONCEPTIONS
     *
     * Misconception 1:
     * Every subclass must support every method in its parent.
     *
     * Reality:
     * A parent abstraction should expose only behavior that
     * its valid implementations can honor.
     *
     * Misconception 2:
     * If code compiles, LSP is satisfied.
     *
     * Reality:
     * Compile-time correctness does not guarantee behavioral
     * substitutability.
     *
     * Misconception 3:
     * LSP applies only to inheritance.
     *
     * Reality:
     * It also applies to interface implementations and
     * other substitutable components.
     *
     * Misconception 4:
     * A child class must always be a real-world type of
     * its parent.
     *
     * Reality:
     * The important requirement is behavioral compatibility
     * with the abstraction's contract.
     *
     * Misconception 5:
     * Composition automatically solves every LSP problem.
     *
     * Reality:
     * Composition can help avoid unsuitable inheritance,
     * but the contracts of composed components still matter.
     *
     *
     * 15. INTERVIEW QUESTIONS
     *
     * Q1. What is Liskov Substitution Principle?
     * A. Objects of a superclass should be replaceable with
     *    objects of its subclasses without breaking correctness.
     *
     * Q2. Who introduced LSP?
     * A. Barbara Liskov in 1987.
     *
     * Q3. What is the classic LSP example?
     * A. The mutable Rectangle-Square inheritance problem.
     *
     * Q4. What is behavioral substitutability?
     * A. A replacement object preserves the behavior expected
     *    by code using the original abstraction.
     *
     * Q5. What is Design by Contract?
     * A. A method and its callers work under preconditions,
     *    postconditions and invariants.
     *
     * Q6. Can a subclass strengthen preconditions?
     * A. No. It should not require more than the parent contract.
     *
     * Q7. Can a subclass weaken postconditions?
     * A. No. It should preserve or strengthen the guarantees
     *    made by the parent.
     *
     * Q8. Does a valid Java override always satisfy LSP?
     * A. No. A method can compile and still break behavioral
     *    expectations.
     *
     * Q9. Does LSP apply to interfaces?
     * A. Yes. Implementations must honor the interface contract.
     *
     *
     * 16. QUICK REVISION
     *
     *   - LSP is the third SOLID principle.
     *   - Introduced by Barbara Liskov in 1987.
     *   - Subtypes must be behaviorally substitutable.
     *   - Preserve parent contracts and invariants.
     *   - Do not strengthen preconditions.
     *   - Do not weaken postconditions.
     *   - Applies to classes and interfaces.
     *   - Compiler acceptance alone does not prove LSP.
     *
     * CORE IDEA:
     *
     * A child object must behave as the parent abstraction
     * promises, so client code can use either without breaking.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Liskov-Substitution Principle , Happy Learning ..!");
    }
}
