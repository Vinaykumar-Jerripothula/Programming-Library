
public class FactoryMethod {


    /*
     * ============================================================
     *                  FACTORY METHOD DESIGN PATTERN
     * ============================================================
     *
     * 1. INTRODUCTION
     *
     * Factory Method is a Creational Design Pattern.
     *
     * Creational patterns deal with object creation.
     *
     * Definition:
     *
     * Factory Method defines an interface for creating an object,
     * but lets subclasses decide which concrete class to
     * instantiate.
     *
     * In simple words:
     *
     * Instead of directly creating objects using new throughout
     * our application, we delegate object creation to a separate
     * method.
     *
     * The actual type of object created is determined by the
     * implementation of that creation method.
     *
     * The key idea:
     *
     * Separate object creation from the code that uses the object.
     *
     *
     * 2. WHY DO WE NEED FACTORY METHOD?
     *
     * First understand the problem.
     *
     * Imagine we are developing a notification application.
     *
     * It supports:
     *
     *   - Email notifications
     *   - SMS notifications
     *   - Push notifications
     *
     * We have a common interface:
     *
     * interface Notification {
     *
     *     void send(String message);
     * }
     *
     * Implementations:
     *
     * class EmailNotification implements Notification {
     *
     *     public void send(String message) {
     *         System.out.println("Sending email: " + message);
     *     }
     * }
     *
     * class SMSNotification implements Notification {
     *
     *     public void send(String message) {
     *         System.out.println("Sending SMS: " + message);
     *     }
     * }
     *
     * class PushNotification implements Notification {
     *
     *     public void send(String message) {
     *         System.out.println("Sending push: " + message);
     *     }
     * }
     *
     * Now consider:
     *
     * class NotificationService {
     *
     *     public void notifyUser(String type, String message) {
     *
     *         Notification notification;
     *
     *         if (type.equals("EMAIL")) {
     *             notification = new EmailNotification();
     *         }
     *         else if (type.equals("SMS")) {
     *             notification = new SMSNotification();
     *         }
     *         else {
     *             notification = new PushNotification();
     *         }
     *
     *         notification.send(message);
     *     }
     * }
     *
     * What is the problem?
     *
     * NotificationService is responsible for:
     *
     *   1. Deciding which notification to create.
     *   2. Creating the concrete notification object.
     *   3. Using that notification to send a message.
     *
     * The service is directly coupled to all concrete
     * notification classes.
     *
     * Every time we add a new notification type, we must
     * modify the service's creation logic.
     *
     * As the application grows, object creation logic can
     * become scattered throughout the application.
     *
     * Factory Method helps separate these concerns.
     *
     *
     * 3. REAL-WORLD ANALOGY
     *
     * Imagine a vehicle manufacturing company.
     *
     * The company has different factories:
     *
     *   - Car factory
     *   - Bike factory
     *   - Truck factory
     *
     * Each factory knows how to create its particular vehicle.
     *
     * The customer does not need to know every manufacturing
     * step.
     *
     * The customer requests a vehicle, and the appropriate
     * factory provides it.
     *
     * Similarly, Factory Method delegates object creation
     * to a specialized creation method.
     *
     * IMPORTANT:
     *
     * This is an analogy. The GoF Factory Method pattern has
     * a specific structure involving creators and products.
     * Not every class called Factory implements that pattern.
     *
     *
     * 4. UNDERSTANDING THE FACTORY METHOD STRUCTURE
     *
     * The classic Factory Method pattern contains four
     * main participants:
     *
     * 1. PRODUCT
     *
     * Defines the common interface for objects that can
     * be created.
     *
     * Example:
     *   Notification
     *
     *
     * 2. CONCRETE PRODUCT
     *
     * Implements the Product interface.
     *
     * Examples:
     *   EmailNotification
     *   SMSNotification
     *   PushNotification
     *
     *
     * 3. CREATOR
     *
     * Declares the factory method that returns a Product.
     *
     * It may also contain business logic that uses the
     * product returned by the factory method.
     *
     * Example:
     *   NotificationCreator
     *
     *
     * 4. CONCRETE CREATOR
     *
     * Overrides the factory method and decides which
     * Concrete Product to create.
     *
     * Examples:
     *   EmailNotificationCreator
     *   SMSNotificationCreator
     *   PushNotificationCreator
     *
     *
     * 5. IMPLEMENTING FACTORY METHOD IN JAVA
     *
     * Let's implement the complete pattern.
     *
     * STEP 1: CREATE THE PRODUCT INTERFACE
     *
     * interface Notification {
     *
     *     void send(String message);
     * }
     *
     * This is the common contract for all notification types.
     *
     *
     * STEP 2: CREATE CONCRETE PRODUCTS
     *
     * class EmailNotification implements Notification {
     *
     *     @Override
     *     public void send(String message) {
     *         System.out.println("Email: " + message);
     *     }
     * }
     *
     * class SMSNotification implements Notification {
     *
     *     @Override
     *     public void send(String message) {
     *         System.out.println("SMS: " + message);
     *     }
     * }
     *
     * class PushNotification implements Notification {
     *
     *     @Override
     *     public void send(String message) {
     *         System.out.println("Push: " + message);
     *     }
     * }
     *
     * Each class provides its own notification behavior.
     *
     *
     * STEP 3: CREATE THE CREATOR
     *
     * abstract class NotificationCreator {
     *
     *     // Factory Method
     *     public abstract Notification createNotification();
     *
     *     // Business operation using the created product
     *     public void notifyUser(String message) {
     *
     *         Notification notification = createNotification();
     *
     *         notification.send(message);
     *     }
     * }
     *
     * IMPORTANT:
     *
     * createNotification() is the Factory Method.
     *
     * notifyUser() is an operation that uses the product
     * returned by the factory method.
     *
     * The creator does not need to know the exact concrete
     * notification class.
     *
     *
     * STEP 4: CREATE CONCRETE CREATORS
     *
     * class EmailNotificationCreator extends NotificationCreator {
     *
     *     @Override
     *     public Notification createNotification() {
     *
     *         return new EmailNotification();
     *     }
     * }
     *
     * class SMSNotificationCreator extends NotificationCreator {
     *
     *     @Override
     *     public Notification createNotification() {
     *
     *         return new SMSNotification();
     *     }
     * }
     *
     * class PushNotificationCreator extends NotificationCreator {
     *
     *     @Override
     *     public Notification createNotification() {
     *
     *         return new PushNotification();
     *     }
     * }
     *
     * Each concrete creator decides which product to instantiate.
     *
     *
     * STEP 5: USE THE FACTORY METHOD
     *
     * public class FactoryMethodDemo {
     *
     *     public static void main(String[] args) {
     *
     *         NotificationCreator creator =
     *                 new EmailNotificationCreator();
     *
     *         creator.notifyUser("Welcome!");
     *
     *         creator = new SMSNotificationCreator();
     *
     *         creator.notifyUser("Your OTP is 1234");
     *     }
     * }
     *
     * Output:
     *
     * Email: Welcome!
     * SMS: Your OTP is 1234
     *
     *
     * 6. HOW DOES THE EXECUTION WORK?
     *
     * Consider:
     *
     * NotificationCreator creator =
     *         new EmailNotificationCreator();
     *
     * Step 1:
     *
     * An EmailNotificationCreator object is created.
     *
     * Step 2:
     *
     * creator.notifyUser("Welcome!") is called.
     *
     * Step 3:
     *
     * The inherited notifyUser() method executes.
     *
     * Step 4:
     *
     * It calls createNotification().
     *
     * Step 5:
     *
     * Runtime polymorphism invokes the overridden method
     * in EmailNotificationCreator.
     *
     * Step 6:
     *
     * An EmailNotification object is created.
     *
     * Step 7:
     *
     * The returned object is referenced through Notification.
     *
     * Step 8:
     *
     * notification.send(message) executes the concrete
     * EmailNotification implementation.
     *
     * IMPORTANT:
     *
     * Two different polymorphic operations are involved:
     *
     *   - Creator polymorphism chooses the creation method.
     *   - Product polymorphism chooses the notification behavior.
     *
     *
     * 7. WHY IS IT CALLED FACTORY METHOD?
     *
     * Because a method is responsible for creating and returning
     * an object.
     *
     * In the classic pattern:
     *
     *   createNotification()
     *
     * is declared by the Creator and implemented by
     * Concrete Creators.
     *
     * The method acts as a controlled extension point for
     * object creation.
     *
     *
     * 8. FACTORY METHOD AND POLYMORPHISM
     *
     * Polymorphism is fundamental to the classic pattern.
     *
     * Consider:
     *
     * NotificationCreator creator =
     *         new SMSNotificationCreator();
     *
     * The reference type is:
     *   NotificationCreator
     *
     * The actual object type is:
     *   SMSNotificationCreator
     *
     * When createNotification() is called, Java selects
     * the overridden method at runtime.
     *
     * This is runtime polymorphism through method overriding.
     *
     * The creator abstraction allows client code to work
     * without directly depending on a concrete creator.
     *
     *
     * 9. FACTORY METHOD AND OPEN/CLOSED PRINCIPLE
     *
     * Factory Method can help apply the Open/Closed Principle.
     *
     * OCP says:
     *
     * Software entities should be open for extension
     * but closed for modification.
     *
     * Suppose we introduce WhatsApp notifications.
     *
     * We can add:
     *
     * class WhatsAppNotification implements Notification {
     *
     *     @Override
     *     public void send(String message) {
     *         System.out.println("WhatsApp: " + message);
     *     }
     * }
     *
     * class WhatsAppNotificationCreator
     *         extends NotificationCreator {
     *
     *     @Override
     *     public Notification createNotification() {
     *
     *         return new WhatsAppNotification();
     *     }
     * }
     *
     * We extend the system with new product and creator classes.
     *
     * The existing NotificationCreator contract and
     * notifyUser() implementation do not need modification.
     *
     * IMPORTANT:
     *
     * A client still needs some way to choose or receive
     * the appropriate creator.
     *
     * If a central client contains a large switch statement
     * for every new creator, that selection logic may itself
     * require modification.
     *
     * Factory Method does not magically remove every
     * conditional from an application.
     *
     *
     * 10. FACTORY METHOD AND DEPENDENCY INVERSION
     *
     * The creator's business operation works with the
     * Notification abstraction rather than a concrete product.
     *
     * Example:
     *
     * Notification notification = createNotification();
     *
     * notification.send(message);
     *
     * The business operation does not depend on:
     *
     *   EmailNotification
     *   SMSNotification
     *   PushNotification
     *
     * This supports dependency inversion.
     *
     * However, the concrete creator still knows which
     * implementation it creates.
     *
     * This is expected because object creation must happen
     * somewhere.
     *
     *
     * 11. FACTORY METHOD VS SIMPLE FACTORY
     *
     * These two approaches are often confused.
     *
     * SIMPLE FACTORY:
     *
     * A separate class or method contains the logic for
     * selecting and creating concrete objects.
     *
     * Example:
     *
     * class NotificationFactory {
     *
     *     public static Notification create(String type) {
     *
     *         if (type.equals("EMAIL")) {
     *             return new EmailNotification();
     *         }
     *
     *         if (type.equals("SMS")) {
     *             return new SMSNotification();
     *         }
     *
     *         throw new IllegalArgumentException(
     *                 "Unknown notification type: " + type
     *         );
     *     }
     * }
     *
     * Usage:
     *
     * Notification notification =
     *         NotificationFactory.create("EMAIL");
     *
     *
     * FACTORY METHOD:
     *
     * A Creator declares a factory method.
     *
     * Concrete Creator subclasses override that method
     * to decide which product to create.
     *
     *
     * KEY DIFFERENCES:
     *
     * Simple Factory:
     *   - Usually one factory class.
     *   - Often uses if-else or switch.
     *   - Selection logic is centralized.
     *   - Does not necessarily use inheritance.
     *
     * Factory Method:
     *   - Uses a Creator abstraction.
     *   - Uses Concrete Creator subclasses.
     *   - Uses method overriding.
     *   - Creation is delegated to subclasses.
     *
     * IMPORTANT:
     *
     * Simple Factory is a commonly used idiom, but it is not
     * one of the 23 GoF design patterns.
     *
     * The classic GoF Factory Method pattern has the
     * Creator/Concrete Creator structure.
     *
     *
     * 12. FACTORY METHOD VS ABSTRACT FACTORY
     *
     * Both are Creational Design Patterns.
     *
     * FACTORY METHOD:
     *
     * Focuses on creating a product through an overridable
     * creation method.
     *
     * It commonly creates one product type or product hierarchy.
     *
     * ABSTRACT FACTORY:
     *
     * Provides an interface for creating families of related
     * or dependent products without specifying their concrete
     * classes.
     *
     * Example:
     *
     * Factory Method:
     *   NotificationCreator creates a Notification.
     *
     * Abstract Factory:
     *   GUIFactory creates related Button and Checkbox objects.
     *
     * A useful distinction:
     *
     * Factory Method commonly uses inheritance to vary creation.
     *
     * Abstract Factory commonly uses composition by providing
     * a factory object that exposes multiple creation methods.
     *
     * IMPORTANT:
     *
     * Real implementations can combine both patterns.
     *
     *
     * 13. FACTORY METHOD VS BUILDER
     *
     * FACTORY METHOD:
     *
     * Delegates the decision of which concrete product to create.
     *
     * BUILDER:
     *
     * Separates the step-by-step construction of a complex object
     * from its final representation.
     *
     * Example:
     *
     * Factory Method:
     *   Create an EmailNotification or SMSNotification.
     *
     * Builder:
     *   Construct a complex HTTP request with headers,
     *   body, timeout and other options.
     *
     *
     * 14. FACTORY METHOD VS PROTOTYPE
     *
     * FACTORY METHOD:
     *
     * Creates products by delegating creation to a method.
     *
     * PROTOTYPE:
     *
     * Creates new objects by copying or cloning an existing
     * prototype.
     *
     * Factory Method focuses on creation decisions.
     *
     * Prototype focuses on copying existing objects.
     *
     *
     * 15. FACTORY METHOD IN REAL-WORLD JAVA
     *
     * Factory Method is useful when:
     *
     *   - A framework needs to let subclasses choose products.
     *   - Different implementations share one product interface.
     *   - Creation logic varies between subclasses.
     *   - The client should depend on abstractions.
     *
     * Examples of product families or implementations:
     *
     *   - Notification providers.
     *   - Document parsers.
     *   - Payment processors.
     *   - File readers.
     *   - Report exporters.
     *
     * These are possible applications, not automatic
     * examples of the GoF pattern.
     *
     *
     * 16. FACTORY METHOD IN JAVA FRAMEWORKS
     *
     * Java libraries and frameworks often use factory methods
     * or factory-style APIs.
     *
     * Example:
     *
     * Calendar calendar = Calendar.getInstance();
     *
     * The caller asks for a Calendar without directly
     * constructing a concrete Calendar implementation.
     *
     * Another example:
     *
     * NumberFormat formatter =
     *         NumberFormat.getInstance();
     *
     * The API provides a suitable formatter implementation.
     *
     * IMPORTANT:
     *
     * These are factory-style creation APIs.
     *
     * They should not automatically be classified as the
     * exact GoF Factory Method pattern.
     *
     * The classic GoF pattern specifically involves a Creator
     * whose factory method is overridden by subclasses.
     *
     *
     * 17. FACTORY METHOD AND SPRING FRAMEWORK
     *
     * Spring uses several object-creation mechanisms.
     *
     * A particularly relevant example is FactoryBean<T>.
     *
     * FactoryBean<T> is an interface that allows a Spring bean
     * to act as a factory for another object.
     *
     * Example:
     *
     * public class MyObjectFactoryBean
     *         implements FactoryBean<MyObject> {
     *
     *     @Override
     *     public MyObject getObject() {
     *         return new MyObject();
     *     }
     *
     *     @Override
     *     public Class<?> getObjectType() {
     *         return MyObject.class;
     *     }
     * }
     *
     * This is a Spring factory mechanism.
     *
     * IMPORTANT:
     *
     * Spring's FactoryBean is not automatically the classic
     * GoF Factory Method pattern.
     *
     * It is a framework-specific factory abstraction.
     *
     *
     * 18. FACTORY METHOD AND OBJECT CREATION RESPONSIBILITY
     *
     * A major benefit of Factory Method is separation
     * of responsibilities.
     *
     * Without the pattern:
     *
     * Client:
     *   - Knows concrete product classes.
     *   - Creates objects.
     *   - Uses objects.
     *
     * With the pattern:
     *
     * Creator:
     *   - Defines the creation method.
     *   - Uses the returned product when appropriate.
     *
     * Concrete Creator:
     *   - Decides which concrete product to create.
     *
     * Product:
     *   - Defines the common behavior.
     *
     * Concrete Product:
     *   - Implements the behavior.
     *
     * The result is a more flexible object-creation design.
     *
     *
     * 19. ADVANTAGES OF FACTORY METHOD
     *
     *   1. Loose coupling:
     *      Client code can depend on product abstractions.
     *
     *   2. Extensibility:
     *      New product and creator implementations can be added.
     *
     *   3. Separation of concerns:
     *      Object creation is separated from product usage.
     *
     *   4. Better maintainability:
     *      Creation decisions are localized in creators.
     *
     *   5. Supports polymorphism:
     *      Different concrete creators can be substituted.
     *
     *   6. Supports OCP:
     *      New product variations can be introduced through
     *      new creator implementations.
     *
     *
     * 20. DISADVANTAGES OF FACTORY METHOD
     *
     *   1. More classes:
     *      Each concrete creator and product may require
     *      a separate class.
     *
     *   2. More complexity:
     *      A simple object-creation problem may become
     *      unnecessarily complicated.
     *
     *   3. Creator selection:
     *      Some part of the application must still decide
     *      which concrete creator to use.
     *
     *   4. Inheritance dependency:
     *      The classic pattern uses Creator inheritance,
     *      which may not fit every application architecture.
     *
     *
     * 21. WHEN SHOULD WE USE FACTORY METHOD?
     *
     * Consider Factory Method when:
     *
     *   - A class cannot determine the exact product type
     *     it should create.
     *
     *   - Subclasses should decide which product to instantiate.
     *
     *   - You expect new product implementations to be introduced.
     *
     *   - You want the creator's business logic to work with
     *     a common product abstraction.
     *
     *   - Object creation is becoming tightly coupled to
     *     concrete classes.
     *
     *
     * 22. WHEN SHOULD WE AVOID FACTORY METHOD?
     *
     * Avoid it when:
     *
     *   - Object creation is very simple.
     *
     *   - There is only one stable implementation.
     *
     *   - There is no meaningful variation in creation.
     *
     *   - Adding Creator subclasses creates more complexity
     *     than value.
     *
     *   - A simpler constructor, static factory method,
     *     dependency injection or registry is more appropriate.
     *
     *
     * 23. COMMON MISCONCEPTIONS
     *
     * MISCONCEPTION 1:
     *
     * Every class named Factory implements Factory Method.
     *
     * REALITY:
     *
     * A class with a create() method may simply be a
     * Simple Factory.
     *
     *
     * MISCONCEPTION 2:
     *
     * Factory Method means creating objects using a static method.
     *
     * REALITY:
     *
     * The classic GoF pattern uses a factory method declared
     * in a Creator and overridden by Concrete Creators.
     *
     *
     * MISCONCEPTION 3:
     *
     * Factory Method eliminates all object creation.
     *
     * REALITY:
     *
     * Objects are still created. Their creation is delegated
     * to the appropriate creator.
     *
     *
     * MISCONCEPTION 4:
     *
     * Factory Method always removes if-else statements.
     *
     * REALITY:
     *
     * The code that selects a concrete creator may still
     * contain conditional logic.
     *
     *
     * MISCONCEPTION 5:
     *
     * Factory Method and Abstract Factory are the same.
     *
     * REALITY:
     *
     * Factory Method focuses on one creation method.
     *
     * Abstract Factory focuses on creating families of
     * related products.
     *
     *
     * MISCONCEPTION 6:
     *
     * Every factory-style Java API is a GoF Factory Method.
     *
     * REALITY:
     *
     * A factory-style API may use a different creation approach.
     * The classic GoF structure must be examined before
     * assigning the pattern name.
     *
     *
     * 24. INTERVIEW QUESTIONS AND ANSWERS
     *
     * Q1. What is Factory Method Design Pattern?
     *
     * A. A Creational Design Pattern that defines a creation
     *    method in a Creator and lets subclasses decide which
     *    concrete product to instantiate.
     *
     *
     * Q2. Which category does Factory Method belong to?
     *
     * A. Creational Design Patterns.
     *
     *
     * Q3. What are the main participants?
     *
     * A. Product, Concrete Product, Creator and Concrete Creator.
     *
     *
     * Q4. What is the Factory Method?
     *
     * A. The method declared by the Creator and overridden
     *    by Concrete Creators to return a product.
     *
     *
     * Q5. Why is an interface used for the Product?
     *
     * A. It allows the creator's business logic to work with
     *    different concrete products through one abstraction.
     *
     *
     * Q6. What is the difference between Simple Factory
     *    and Factory Method?
     *
     * A. Simple Factory usually centralizes selection logic
     *    in one factory. Factory Method delegates product
     *    creation to overridable methods in creator subclasses.
     *
     *
     * Q7. How does Factory Method support OCP?
     *
     * A. New product variations can be introduced using new
     *    concrete products and creators without changing
     *    existing creator business logic.
     *
     *
     * Q8. Does Factory Method use inheritance?
     *
     * A. The classic GoF implementation uses Creator
     *    inheritance and method overriding.
     *
     *
     * Q9. What is the difference between Factory Method
     *    and Abstract Factory?
     *
     * A. Factory Method delegates creation through a method.
     *    Abstract Factory provides an interface for creating
     *    families of related products.
     *
     *
     * Q10. Does Factory Method always eliminate conditionals?
     *
     * A. No. The code selecting a concrete creator may still
     *    require conditions or configuration.
     *
     *
     * Q11. Can Factory Method return an interface type?
     *
     * A. Yes. Returning a Product abstraction hides the
     *    concrete implementation from the creator's client.
     *
     *
     * Q12. Is Calendar.getInstance() the exact GoF
     *     Factory Method pattern?
     *
     * A. It is a factory-style API, but it does not by itself
     *    demonstrate the classic Creator/Concrete Creator
     *    inheritance structure.
     *
     *
     * Q13. What is the role of the Creator?
     *
     * A. It declares the factory method and may contain
     *    operations that use the created product.
     *
     *
     * Q14. What is the main disadvantage of Factory Method?
     *
     * A. It can introduce additional creator and product
     *    classes, increasing complexity.
     *
     *
     * 25. QUICK REVISION
     *
     *   - Factory Method is a Creational Design Pattern.
     *   - It separates object creation from object usage.
     *   - The Product defines a common interface.
     *   - Concrete Products implement the Product interface.
     *   - The Creator declares the factory method.
     *   - Concrete Creators override the factory method.
     *   - Runtime polymorphism selects the concrete creator method.
     *   - It can support OCP and DIP.
     *   - Simple Factory is not one of the 23 GoF patterns.
     *   - Abstract Factory creates families of related products.
     *   - Do not use the pattern when a simple solution is enough.
     *
     *
     * 26. FINAL MENTAL MODEL
     *
     * Without Factory Method:
     *
     *   Client
     *     |
     *     +---- new EmailNotification()
     *     |
     *     +---- new SMSNotification()
     *     |
     *     +---- new PushNotification()
     *
     * The client knows concrete product classes.
     *
     *
     * With Factory Method:
     *
     *   Client
     *     |
     *     v
     *   Creator
     *     |
     *     v
     *   Factory Method
     *     |
     *     v
     *   Product abstraction
     *     ^
     *     |
     *   Concrete Products
     *
     * Concrete Creator subclasses decide which product
     * implementation is created.
     *
     * REMEMBER:
     *
     * Factory Method does not remove object creation.
     *
     * It moves the decision about which concrete object
     * to create into an overridable creation method.
     *
     * ============================================================
     */
   
    public static void main(String[] args) {
        System.out.println("The above is all about Factory-Method Design Pattern , Happy Learning ..!");
    }
}
