
public class Decorator {

    /*
     * ============================================================
     * 1. INTRODUCTION
     * ============================================================
     *
     * Imagine you are building a coffee ordering application.
     *
     * Initially, a customer orders a simple coffee.
     *
     * Now the customer wants to add:
     *
     *   - Milk
     *   - Sugar
     *   - Chocolate
     *   - Whipped cream
     *
     * One approach is to create separate classes:
     *
     *   SimpleCoffee
     *   CoffeeWithMilk
     *   CoffeeWithSugar
     *   CoffeeWithMilkAndSugar
     *   CoffeeWithMilkAndChocolate
     *   CoffeeWithMilkSugarAndChocolate
     *
     * What happens when more toppings are introduced?
     *
     * The number of combinations can grow rapidly.
     *
     * We need a way to add responsibilities to an object
     * dynamically without creating a separate subclass
     * for every possible combination.
     *
     * This is exactly what the Decorator pattern provides.
     *
     *
     * DEFINITION:
     *
     * Decorator is a structural design pattern that attaches
     * additional responsibilities to an object dynamically.
     *
     * Decorator provides a flexible alternative to subclassing
     * for extending functionality.
     *
     *
     * SIMPLE DEFINITION:
     *
     * Wrap an object with another object to add extra behavior
     * while keeping the same interface.
     *
     *
     * ============================================================
     * 2. WHY DO WE NEED DECORATOR?
     * ============================================================
     *
     * Consider a basic Coffee class.
     *
     * We want to support:
     *
     *   - Coffee
     *   - Coffee + Milk
     *   - Coffee + Sugar
     *   - Coffee + Milk + Sugar
     *   - Coffee + Milk + Sugar + Chocolate
     *
     * Using inheritance, we may need a class for every combination.
     *
     * This causes:
     *
     *   - Class explosion.
     *   - Duplicate code.
     *   - Difficult maintenance.
     *   - Less flexibility at runtime.
     *
     * Decorator solves this through composition.
     *
     * We wrap one object inside another:
     *
     * Coffee
     *   |
     *   v
     * MilkDecorator
     *   |
     *   v
     * SugarDecorator
     *   |
     *   v
     * ChocolateDecorator
     *
     * Each decorator adds its own responsibility.
     *
     *
     * ============================================================
     * 3. REAL-WORLD ANALOGY
     * ============================================================
     *
     * Think about a basic mobile phone.
     *
     * You can add accessories:
     *
     *   - Protective case
     *   - Screen protector
     *   - External lens
     *
     * The original phone remains the same.
     *
     * Accessories provide additional functionality or protection.
     *
     * Similarly, Decorator wraps an existing object and adds
     * responsibilities without changing its original class.
     *
     * REMEMBER:
     *
     * Decorator = Add functionality by wrapping an object.
     *
     *
     * ============================================================
     * 4. STRUCTURE AND PARTICIPANTS
     * ============================================================
     *
     * Main participants:
     *
     * 1. COMPONENT:
     *    Defines the common interface.
     *
     * 2. CONCRETE COMPONENT:
     *    The original object whose behavior can be extended.
     *
     * 3. DECORATOR:
     *    Maintains a reference to another Component and implements
     *    the same Component interface.
     *
     * 4. CONCRETE DECORATOR:
     *    Adds specific responsibilities.
     *
     * 5. CLIENT:
     *    Uses the Component interface.
     *
     *
     * BASIC STRUCTURE:
     *
     *             Component
     *                 |
     *          +------+------+
     *          |             |
     *   ConcreteComponent  Decorator
     *                         |
     *                         | wraps
     *                         v
     *                      Component
     *                         |
     *                  ConcreteDecorator
     *
     *
     * ============================================================
     * 5. COMPLETE JAVA IMPLEMENTATION
     * ============================================================
     *
     * We will implement the Coffee example.
     *
     *
     * STEP 1: Create the Component interface.
     *
     * interface Coffee {
     *
     *     String getDescription();
     *
     *     double getCost();
     * }
     *
     *
     * STEP 2: Create the Concrete Component.
     *
     * class SimpleCoffee implements Coffee {
     *
     *     public String getDescription() {
     *         return "Simple Coffee";
     *     }
     *
     *     public double getCost() {
     *         return 100;
     *     }
     * }
     *
     *
     * STEP 3: Create the Abstract Decorator.
     *
     * abstract class CoffeeDecorator implements Coffee {
     *
     *     protected final Coffee coffee;
     *
     *     public CoffeeDecorator(Coffee coffee) {
     *         this.coffee = coffee;
     *     }
     * }
     *
     * Notice:
     *
     * CoffeeDecorator implements Coffee.
     *
     * It also contains a Coffee reference.
     *
     * This allows a decorator to wrap either a SimpleCoffee
     * or another CoffeeDecorator.
     *
     *
     * STEP 4: Create Concrete Decorators.
     *
     * class MilkDecorator extends CoffeeDecorator {
     *
     *     public MilkDecorator(Coffee coffee) {
     *         super(coffee);
     *     }
     *
     *     public String getDescription() {
     *         return coffee.getDescription() + ", Milk";
     *     }
     *
     *     public double getCost() {
     *         return coffee.getCost() + 20;
     *     }
     * }
     *
     * class SugarDecorator extends CoffeeDecorator {
     *
     *     public SugarDecorator(Coffee coffee) {
     *         super(coffee);
     *     }
     *
     *     public String getDescription() {
     *         return coffee.getDescription() + ", Sugar";
     *     }
     *
     *     public double getCost() {
     *         return coffee.getCost() + 10;
     *     }
     * }
     *
     * class ChocolateDecorator extends CoffeeDecorator {
     *
     *     public ChocolateDecorator(Coffee coffee) {
     *         super(coffee);
     *     }
     *
     *     public String getDescription() {
     *         return coffee.getDescription() + ", Chocolate";
     *     }
     *
     *     public double getCost() {
     *         return coffee.getCost() + 30;
     *     }
     * }
     *
     *
     * STEP 5: Use the Decorators.
     *
     * Coffee coffee = new SimpleCoffee();
     *
     * coffee = new MilkDecorator(coffee);
     *
     * coffee = new SugarDecorator(coffee);
     *
     * coffee = new ChocolateDecorator(coffee);
     *
     * System.out.println(coffee.getDescription());
     * System.out.println(coffee.getCost());
     *
     *
     * OUTPUT:
     *
     * Simple Coffee, Milk, Sugar, Chocolate
     * 160.0
     *
     *
     * ============================================================
     * 6. UNDERSTANDING THE EXECUTION FLOW
     * ============================================================
     *
     * Consider:
     *
     * Coffee coffee = new SimpleCoffee();
     *
     * Step 1:
     * Create the original SimpleCoffee object.
     *
     * Cost = 100.
     *
     *
     * Step 2:
     *
     * coffee = new MilkDecorator(coffee);
     *
     * MilkDecorator wraps SimpleCoffee.
     *
     * Cost = 100 + 20 = 120.
     *
     *
     * Step 3:
     *
     * coffee = new SugarDecorator(coffee);
     *
     * SugarDecorator wraps the MilkDecorator.
     *
     * Cost = 120 + 10 = 130.
     *
     *
     * Step 4:
     *
     * coffee = new ChocolateDecorator(coffee);
     *
     * ChocolateDecorator wraps the SugarDecorator.
     *
     * Cost = 130 + 30 = 160.
     *
     *
     * FINAL STRUCTURE:
     *
     * ChocolateDecorator
     *       |
     *       v
     * SugarDecorator
     *       |
     *       v
     * MilkDecorator
     *       |
     *       v
     * SimpleCoffee
     *
     *
     * When getCost() is called:
     *
     * ChocolateDecorator adds 30.
     * SugarDecorator adds 10.
     * MilkDecorator adds 20.
     * SimpleCoffee returns 100.
     *
     * Total = 160.
     *
     *
     * ============================================================
     * 7. WHY MUST DECORATOR IMPLEMENT THE SAME INTERFACE?
     * ============================================================
     *
     * Consider:
     *
     * interface Coffee {
     *     double getCost();
     * }
     *
     * Both SimpleCoffee and CoffeeDecorator implement Coffee.
     *
     * Therefore:
     *
     * Coffee coffee = new SimpleCoffee();
     *
     * coffee = new MilkDecorator(coffee);
     *
     * coffee = new SugarDecorator(coffee);
     *
     * Every decorator can be used wherever a Coffee is expected.
     *
     * This is runtime polymorphism.
     *
     * It also allows decorators to wrap other decorators.
     *
     *
     * ============================================================
     * 8. DECORATOR AND COMPOSITION
     * ============================================================
     *
     * In our example:
     *
     * abstract class CoffeeDecorator implements Coffee {
     *
     *     protected final Coffee coffee;
     * }
     *
     * CoffeeDecorator HAS-A Coffee.
     *
     * This is composition.
     *
     * The decorator also IS-A Coffee because it implements
     * the Coffee interface.
     *
     * This combination is what makes wrapping possible.
     *
     * REMEMBER:
     *
     * Decorator = Same interface + Wrapped object + Extra behavior.
     *
     *
     * ============================================================
     * 9. DECORATOR VS INHERITANCE
     * ============================================================
     *
     * INHERITANCE:
     *
     * Adds behavior through subclasses.
     *
     * Example:
     *
     * CoffeeWithMilk extends SimpleCoffee
     *
     * Problem:
     *
     * Different combinations may require many subclasses.
     *
     *
     * DECORATOR:
     *
     * Adds behavior by wrapping objects.
     *
     * Example:
     *
     * new SugarDecorator(
     *     new MilkDecorator(
     *         new SimpleCoffee()
     *     )
     * );
     *
     * Advantages:
     *
     * - Combinations are created at runtime.
     * - Decorators can be combined in different orders.
     * - Each decorator has one focused responsibility.
     *
     * IMPORTANT:
     *
     * Decorator does not eliminate inheritance completely.
     *
     * Concrete decorators commonly extend an abstract
     * Decorator class.
     *
     * It avoids relying on a subclass for every combination.
     *
     *
     * ============================================================
     * 10. DECORATOR VS ADAPTER
     * ============================================================
     *
     * ADAPTER:
     *
     * Converts an existing interface into another interface
     * expected by the client.
     *
     * DECORATOR:
     *
     * Preserves the component interface and adds responsibilities.
     *
     * Example:
     *
     * Adapter:
     * Make a third-party payment API compatible with
     * PaymentProcessor.
     *
     * Decorator:
     * Add logging, caching or compression around a component.
     *
     *
     * ============================================================
     * 11. DECORATOR VS COMPOSITE
     * ============================================================
     *
     * Both patterns commonly implement a common interface
     * and contain references to components.
     *
     * COMPOSITE:
     *
     * - Represents part-whole hierarchies.
     * - A Composite contains multiple child components.
     * - Allows individual and grouped objects to be treated alike.
     *
     * DECORATOR:
     *
     * - Adds responsibilities to an object.
     * - Commonly wraps one component.
     * - Preserves the component interface.
     *
     * Example:
     *
     * Composite:
     * A Folder contains files and other folders.
     *
     * Decorator:
     * A coffee object is wrapped with MilkDecorator.
     *
     *
     * ============================================================
     * 12. DECORATOR VS PROXY
     * ============================================================
     *
     * Both patterns commonly wrap another object and implement
     * the same interface.
     *
     * PROXY:
     *
     * Controls access to the underlying object.
     *
     * DECORATOR:
     *
     * Adds responsibilities to the underlying object.
     *
     * Example:
     *
     * Proxy:
     * Check authorization before allowing access to a service.
     *
     * Decorator:
     * Add logging or compression to a service.
     *
     * IMPORTANT:
     *
     * Their structures can be very similar.
     * Their primary design intentions are different.
     *
     *
     * ============================================================
     * 13. DECORATOR IN JAVA
     * ============================================================
     *
     * Java I/O provides excellent examples of Decorator.
     *
     * Example:
     *
     * InputStream input =
     *     new FileInputStream("data.txt");
     *
     * InputStream buffered =
     *     new BufferedInputStream(input);
     *
     * InputStream decorated =
     *     new DataInputStream(buffered);
     *
     * Each wrapper adds functionality while preserving
     * the InputStream-based interface.
     *
     * Another example:
     *
     * Reader reader =
     *     new BufferedReader(
     *         new FileReader("data.txt")
     *     );
     *
     * BufferedReader adds buffering and additional
     * reading operations.
     *
     * Another example:
     *
     * OutputStream output =
     *     new FileOutputStream("data.txt");
     *
     * OutputStream bufferedOutput =
     *     new BufferedOutputStream(output);
     *
     * The wrappers can be combined to add multiple behaviors.
     *
     * IMPORTANT:
     *
     * These are classic examples of decorator-style wrapping
     * in the Java I/O API.
     *
     *
     * ============================================================
     * 14. DECORATOR IN BACKEND DEVELOPMENT
     * ============================================================
     *
     * Imagine you have a service:
     *
     * interface OrderService {
     *     void placeOrder();
     * }
     *
     * You want to add:
     *
     * - Logging
     * - Metrics
     * - Caching
     * - Transaction handling
     *
     * You can create wrappers that implement OrderService
     * and delegate to another OrderService.
     *
     * Example:
     *
     * class LoggingOrderService implements OrderService {
     *
     *     private final OrderService target;
     *
     *     LoggingOrderService(OrderService target) {
     *         this.target = target;
     *     }
     *
     *     public void placeOrder() {
     *         System.out.println("Order started");
     *
     *         target.placeOrder();
     *
     *         System.out.println("Order completed");
     *     }
     * }
     *
     * The wrapper adds logging without changing the original
     * service implementation.
     *
     * IMPORTANT:
     *
     * Real production decorators should also consider exceptions,
     * logging failures and whether the extra behavior changes
     * the original operation's semantics.
     *
     *
     * ============================================================
     * 15. DECORATOR AND SPRING FRAMEWORK
     * ============================================================
     *
     * Spring applications frequently use proxies and interceptors
     * to add behavior around method execution.
     *
     * Examples include:
     *
     * - Transaction management.
     * - Method security.
     * - Caching.
     * - Method interception.
     *
     * These mechanisms are often proxy-based.
     *
     * They may resemble Decorator structurally, but they should
     * not automatically be classified as GoF Decorator.
     *
     * Their main purpose and framework implementation matter.
     *
     *
     * ============================================================
     * 16. DECORATOR AND SOLID PRINCIPLES
     * ============================================================
     *
     * 1. OPEN/CLOSED PRINCIPLE:
     *
     * We can introduce new decorators without modifying
     * the original component implementation.
     *
     * 2. SINGLE RESPONSIBILITY PRINCIPLE:
     *
     * Each decorator can focus on one additional responsibility.
     *
     * 3. LISKOV SUBSTITUTION PRINCIPLE:
     *
     * A decorator should preserve the behavioral contract
     * of the Component interface.
     *
     * 4. DEPENDENCY INVERSION PRINCIPLE:
     *
     * Decorators depend on the Component abstraction rather
     * than specific concrete components.
     *
     *
     * ============================================================
     * 17. ADVANTAGES
     * ============================================================
     *
     * 1. Adds behavior dynamically.
     * 2. Avoids creating subclasses for every combination.
     * 3. Supports combining multiple responsibilities.
     * 4. Follows composition over inheritance.
     * 5. Supports the Open/Closed Principle.
     * 6. Keeps additional responsibilities in separate classes.
     *
     *
     * ============================================================
     * 18. DISADVANTAGES
     * ============================================================
     *
     * 1. Can create many small decorator classes.
     * 2. Multiple wrappers can make debugging difficult.
     * 3. The order of decorators may affect behavior.
     * 4. The actual underlying object may be difficult to identify.
     * 5. Object configuration can become complicated.
     *
     *
     * ============================================================
     * 19. WHEN SHOULD WE USE DECORATOR?
     * ============================================================
     *
     * Use Decorator when:
     *
     * - You need to add behavior dynamically.
     * - You want to combine optional responsibilities.
     * - Inheritance would create too many subclasses.
     * - You want to extend an object without modifying its class.
     * - Different objects need different combinations of behavior.
     *
     * Avoid Decorator when:
     *
     * - The object has very simple functionality.
     * - There are only a few fixed variations.
     * - Multiple wrappers make the design harder to understand.
     * - A straightforward implementation is sufficient.
     *
     *
     * ============================================================
     * 20. COMMON MISCONCEPTIONS
     * ============================================================
     *
     * 1. Decorator changes the component interface.
     *    FALSE. It generally preserves the same interface.
     *
     * 2. Decorator always uses inheritance alone.
     *    FALSE. It uses composition to wrap an object.
     *
     * 3. Decorator and Proxy are identical.
     *    FALSE. Their structures may be similar, but their
     *    primary intentions differ.
     *
     * 4. Decorator can only wrap one object.
     *    A typical decorator wraps one component. More complex
     *    designs can coordinate multiple objects, but that is
     *    not the usual GoF structure.
     *
     * 5. Decorators must be applied in a fixed order.
     *    FALSE. The order depends on the behavior and requirements.
     *
     * 6. Every wrapper is a Decorator.
     *    FALSE. It must add responsibilities while preserving
     *    the component abstraction.
     *
     *
     * ============================================================
     * 21. INTERVIEW QUESTIONS
     * ============================================================
     *
     * Q1. What is Decorator?
     *
     * A: A structural design pattern that dynamically adds
     *    responsibilities to an object by wrapping it.
     *
     * Q2. What are its main participants?
     *
     * A: Component, Concrete Component, Decorator,
     *    Concrete Decorator and Client.
     *
     * Q3. Why does Decorator implement the same interface?
     *
     * A: So that the decorator can be used wherever the
     *    original component is expected.
     *
     * Q4. What is the main difference between Decorator
     *    and inheritance?
     *
     * A: Decorator adds behavior through object composition
     *    and wrapping, while inheritance extends behavior
     *    through subclasses.
     *
     * Q5. Which Java API demonstrates Decorator?
     *
     * A: Java I/O streams, such as BufferedInputStream
     *    and DataInputStream.
     *
     * Q6. What is the difference between Decorator and Proxy?
     *
     * A: Decorator primarily adds responsibilities; Proxy
     *    primarily controls access to an object.
     *
     * Q7. Can decorators be combined?
     *
     * A: Yes. One decorator can wrap another decorator.
     *
     * Q8. Does Decorator support OCP?
     *
     * A: Yes. New responsibilities can often be added through
     *    new decorators without modifying existing components.
     *
     *
     * ============================================================
     * 22. QUICK REVISION
     * ============================================================
     *
     * PATTERN: Decorator
     *
     * CATEGORY: Structural
     *
     * PURPOSE:
     * Add responsibilities to objects dynamically.
     *
     * MAIN IDEA:
     * Wrap an object with another object.
     *
     * KEY RELATIONSHIP:
     * Decorator IS-A Component and HAS-A Component.
     *
     * COMMON TECHNIQUES:
     * Composition, delegation and polymorphism.
     *
     * JAVA EXAMPLE:
     * BufferedInputStream wrapping InputStream.
     *
     * REMEMBER:
     *
     * Adapter -> Change interface for compatibility.
     *
     * Decorator -> Add behavior while preserving interface.
     *
     * Composite -> Represent part-whole hierarchies.
     *
     * Proxy -> Control access to an object.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Decorator Design Pattern , Happy Learning ..!");
    }
}
