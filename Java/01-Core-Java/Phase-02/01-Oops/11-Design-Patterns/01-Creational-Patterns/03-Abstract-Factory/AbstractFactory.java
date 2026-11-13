
public class AbstractFactory {

    /*
     * ============================================================
     * 1. INTRODUCTION
     * ============================================================
     *
     * Imagine you are developing an application that supports
     * Windows and macOS.
     *
     * Your application needs two UI components:
     *   1. Button
     *   2. Checkbox
     *
     * Windows requires WindowsButton and WindowsCheckbox.
     * macOS requires MacButton and MacCheckbox.
     *
     * You could create these objects individually, but what if
     * someone accidentally combines a WindowsButton with a
     * MacCheckbox?
     *
     * That would create an inconsistent UI.
     *
     * We need a mechanism that creates a FAMILY of related objects
     * while ensuring that the objects belong to the same family.
     *
     * This is exactly what Abstract Factory solves.
     *
     *
     * DEFINITION:
     *
     * Abstract Factory is a creational design pattern that provides
     * an interface for creating families of related or dependent
     * objects without specifying their concrete classes.
     *
     *
     * ============================================================
     * 2. REAL-WORLD ANALOGY
     * ============================================================
     *
     * Think about buying furniture.
     *
     * You have two furniture styles:
     *
     * MODERN FURNITURE FACTORY:
     *   - Modern Chair
     *   - Modern Sofa
     *   - Modern Table
     *
     * VICTORIAN FURNITURE FACTORY:
     *   - Victorian Chair
     *   - Victorian Sofa
     *   - Victorian Table
     *
     * You select one factory, and that factory supplies furniture
     * belonging to the same style.
     *
     * You don't need to know how each furniture item is created.
     *
     * You simply ask the selected factory for the required product.
     *
     * Remember:
     * One factory creates a family of related products.
     *
     *
     * ============================================================
     * 3. STRUCTURE AND PARTICIPANTS
     * ============================================================
     *
     * Abstract Factory pattern contains these main participants:
     *
     * 1. Abstract Factory
     *    Declares methods for creating different product types.
     *
     * 2. Concrete Factory
     *    Implements the abstract factory and creates a specific
     *    family of products.
     *
     * 3. Abstract Products
     *    Interfaces representing different product types.
     *
     * 4. Concrete Products
     *    Actual implementations belonging to a particular family.
     *
     * 5. Client
     *    Uses the abstract factory and abstract products.
     *    It does not depend directly on concrete implementations.
     *
     *
     * ============================================================
     * 4. COMPLETE JAVA IMPLEMENTATION
     * ============================================================
     *
     * STEP 1: Define abstract product interfaces.
     *
     * interface Button {
     *     void render();
     * }
     *
     * interface Checkbox {
     *     void render();
     * }
     *
     *
     * STEP 2: Create concrete products for Windows.
     *
     * class WindowsButton implements Button {
     *     public void render() {
     *         System.out.println("Rendering Windows Button");
     *     }
     * }
     *
     * class WindowsCheckbox implements Checkbox {
     *     public void render() {
     *         System.out.println("Rendering Windows Checkbox");
     *     }
     * }
     *
     *
     * STEP 3: Create concrete products for macOS.
     *
     * class MacButton implements Button {
     *     public void render() {
     *         System.out.println("Rendering Mac Button");
     *     }
     * }
     *
     * class MacCheckbox implements Checkbox {
     *     public void render() {
     *         System.out.println("Rendering Mac Checkbox");
     *     }
     * }
     *
     *
     * STEP 4: Define the Abstract Factory.
     *
     * interface GUIFactory {
     *     Button createButton();
     *     Checkbox createCheckbox();
     * }
     *
     * Notice that this factory declares methods for creating
     * multiple related product types.
     *
     *
     * STEP 5: Create concrete factories.
     *
     * class WindowsFactory implements GUIFactory {
     *
     *     public Button createButton() {
     *         return new WindowsButton();
     *     }
     *
     *     public Checkbox createCheckbox() {
     *         return new WindowsCheckbox();
     *     }
     * }
     *
     * class MacFactory implements GUIFactory {
     *
     *     public Button createButton() {
     *         return new MacButton();
     *     }
     *
     *     public Checkbox createCheckbox() {
     *         return new MacCheckbox();
     *     }
     * }
     *
     *
     * STEP 6: Create the client application.
     *
     * class Application {
     *
     *     private final Button button;
     *     private final Checkbox checkbox;
     *
     *     Application(GUIFactory factory) {
     *         button = factory.createButton();
     *         checkbox = factory.createCheckbox();
     *     }
     *
     *     void renderUI() {
     *         button.render();
     *         checkbox.render();
     *     }
     * }
     *
     *
     * STEP 7: Use the factory.
     *
     * public class AbstractFactory {
     *
     *     public static void main(String[] args) {
     *
     *         GUIFactory factory = new WindowsFactory();
     *
     *         Application app = new Application(factory);
     *
     *         app.renderUI();
     *
     *         System.out.println("----------------");
     *
     *         factory = new MacFactory();
     *
     *         app = new Application(factory);
     *
     *         app.renderUI();
     *     }
     * }
     *
     *
     * OUTPUT:
     *
     * Rendering Windows Button
     * Rendering Windows Checkbox
     * ----------------
     * Rendering Mac Button
     * Rendering Mac Checkbox
     *
     *
     * ============================================================
     * 5. UNDERSTANDING THE EXECUTION FLOW
     * ============================================================
     *
     * Consider:
     *
     * GUIFactory factory = new WindowsFactory();
     *
     * Step 1:
     * A WindowsFactory object is created.
     *
     * Step 2:
     * It is stored using the GUIFactory reference.
     *
     * Step 3:
     * The Application constructor receives that factory.
     *
     * Step 4:
     * factory.createButton() creates WindowsButton.
     *
     * Step 5:
     * factory.createCheckbox() creates WindowsCheckbox.
     *
     * Step 6:
     * The application uses both objects through their interfaces.
     *
     * Now replace WindowsFactory with MacFactory.
     *
     * The application code remains unchanged.
     * Only the family of products changes.
     *
     *
     * ============================================================
     * 6. WHY IS IT CALLED ABSTRACT FACTORY?
     * ============================================================
     *
     * The factory is abstract because the client interacts with
     * an abstraction (GUIFactory), not a specific factory class.
     *
     * Different concrete factories provide different product
     * families.
     *
     * The client doesn't need to know the concrete product classes.
     *
     *
     * ============================================================
     * 7. ABSTRACT FACTORY VS FACTORY METHOD
     * ============================================================
     *
     * FACTORY METHOD:
     *
     * - Primarily focuses on creating one product type.
     * - Uses a factory method that subclasses override.
     * - Commonly relies on inheritance.
     * - Example: NotificationCreator creates a Notification.
     *
     * ABSTRACT FACTORY:
     *
     * - Focuses on creating families of related products.
     * - Provides multiple creation methods.
     * - Commonly uses composition and factory injection.
     * - Example: GUIFactory creates Button and Checkbox.
     *
     * IMPORTANT:
     *
     * Abstract Factory can internally use Factory Methods.
     * Each creation method in an Abstract Factory is itself
     * a factory-style method.
     *
     * However, the overall patterns solve different problems.
     *
     *
     * ============================================================
     * 8. ABSTRACT FACTORY VS SIMPLE FACTORY
     * ============================================================
     *
     * SIMPLE FACTORY:
     *
     * - Usually a class with a method that creates objects.
     * - Often uses if-else or switch statements.
     * - Not one of the original 23 GoF design patterns.
     *
     * ABSTRACT FACTORY:
     *
     * - Provides an abstraction for creating product families.
     * - Supports multiple related product types.
     * - Concrete factories implement the same factory interface.
     *
     *
     * ============================================================
     * 9. ABSTRACT FACTORY AND SOLID PRINCIPLES
     * ============================================================
     *
     * 1. OPEN/CLOSED PRINCIPLE:
     *
     * We can introduce another product family by implementing
     * GUIFactory without changing the existing product families.
     *
     * However, adding an entirely new product type may require
     * changes to the factory interface and its implementations.
     *
     * 2. DEPENDENCY INVERSION PRINCIPLE:
     *
     * The client depends on GUIFactory, Button and Checkbox
     * abstractions rather than concrete classes.
     *
     * 3. SINGLE RESPONSIBILITY PRINCIPLE:
     *
     * Product creation is separated from the application's
     * business logic.
     *
     *
     * ============================================================
     * 10. REAL-WORLD APPLICATIONS
     * ============================================================
     *
     * 1. Cross-platform UI applications:
     *    Windows, macOS and Linux component families.
     *
     * 2. Database access:
     *    A database-specific factory can provide related objects
     *    such as connections, commands and parameters.
     *
     * 3. Cloud providers:
     *    Provider-specific implementations can create related
     *    storage, messaging and other service clients.
     *
     * 4. Theme systems:
     *    Light and dark themes can provide matching buttons,
     *    text fields, menus and other components.
     *
     * 5. Game development:
     *    Different environments can provide matching characters,
     *    weapons and resources.
     *
     *
     * ============================================================
     * 11. ABSTRACT FACTORY IN JAVA AND SPRING
     * ============================================================
     *
     * Java:
     *
     * JDBC provides database-specific implementations behind
     * common interfaces. Related JDBC objects can be obtained
     * from a connection.
     *
     * This illustrates abstraction and provider-specific
     * implementations, but JDBC itself should not automatically
     * be labelled as a textbook Abstract Factory implementation.
     *
     * Spring:
     *
     * Spring's ApplicationContext can create and provide beans.
     * You can register different concrete factory implementations
     * and inject them through an interface.
     *
     * Spring's FactoryBean is another factory-related mechanism,
     * but it is not the same thing as the GoF Abstract Factory.
     *
     *
     * ============================================================
     * 12. ADVANTAGES
     * ============================================================
     *
     * 1. Creates families of related objects.
     * 2. Helps maintain consistency between product families.
     * 3. Hides concrete product implementations from clients.
     * 4. Supports switching between product families.
     * 5. Encourages programming to interfaces.
     * 6. Separates object creation from application logic.
     *
     *
     * ============================================================
     * 13. DISADVANTAGES
     * ============================================================
     *
     * 1. Introduces additional interfaces and classes.
     * 2. Can increase overall code complexity.
     * 3. Adding a new product family is straightforward,
     *    but adding a new product type can require changes
     *    across all concrete factories.
     * 4. May be unnecessary for applications with only a few
     *    simple object-creation requirements.
     *
     *
     * ============================================================
     * 14. WHEN SHOULD WE USE IT?
     * ============================================================
     *
     * Use Abstract Factory when:
     *
     * - You need multiple families of related objects.
     * - Products from one family must work together.
     * - You need to switch product families easily.
     * - You want clients to remain independent of concrete classes.
     *
     * Avoid it when:
     *
     * - You only need to create one simple object.
     * - Product families do not have meaningful relationships.
     * - The additional abstraction provides no real benefit.
     *
     *
     * ============================================================
     * 15. COMMON MISCONCEPTIONS
     * ============================================================
     *
     * 1. Abstract Factory and Factory Method are identical.
     *    FALSE. They have different primary purposes.
     *
     * 2. Abstract Factory creates only one object.
     *    FALSE. It is designed to create families of products.
     *
     * 3. Abstract Factory guarantees thread safety.
     *    FALSE. Thread safety depends on the implementation.
     *
     * 4. Abstract Factory automatically guarantees OCP.
     *    FALSE. Adding a new product type can require modifying
     *    the abstract factory and concrete factories.
     *
     * 5. Abstract Factory means every factory must be abstract.
     *    FALSE. Concrete factories are commonly instantiated.
     *
     *
     * ============================================================
     * 16. INTERVIEW QUESTIONS
     * ============================================================
     *
     * Q1. What is Abstract Factory?
     *
     * A: A creational design pattern that provides an interface
     *    for creating families of related objects without
     *    specifying their concrete classes.
     *
     * Q2. What problem does it solve?
     *
     * A: It ensures that related products can be created from
     *    the same family without coupling client code to
     *    concrete product implementations.
     *
     * Q3. What are its main participants?
     *
     * A: Abstract Factory, Concrete Factory, Abstract Product,
     *    Concrete Product and Client.
     *
     * Q4. What is the main difference between Factory Method
     *    and Abstract Factory?
     *
     * A: Factory Method primarily varies the creation of a
     *    product through an overridable method. Abstract Factory
     *    creates families of related products.
     *
     * Q5. Can Abstract Factory use Factory Method?
     *
     * A: Yes. Its product-creation operations are commonly
     *    implemented as factory methods.
     *
     * Q6. What happens when a new product family is introduced?
     *
     * A: A new concrete factory and its corresponding products
     *    can be added without changing the client abstraction.
     *
     * Q7. What happens when a new product type is introduced?
     *
     * A: The abstract factory interface and concrete factories
     *    generally need to be updated.
     *
     *
     * ============================================================
     * 17. QUICK REVISION
     * ============================================================
     *
     * PATTERN: Abstract Factory
     *
     * CATEGORY: Creational
     *
     * PURPOSE:
     * Create families of related objects.
     *
     * MAIN IDEA:
     * One factory creates multiple related product types.
     *
     * KEY BENEFIT:
     * Keeps products within a family consistent.
     *
     * KEY DIFFERENCE:
     * Factory Method -> Primarily one product creation variation.
     * Abstract Factory -> Families of related products.
     *
     * REMEMBER:
     *
     * Factory Method = Choose how one product is created.
     *
     * Abstract Factory = Choose which family of products to create.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Abstract Factory Design Pattern , Happy Learning ..!");
    }
}
