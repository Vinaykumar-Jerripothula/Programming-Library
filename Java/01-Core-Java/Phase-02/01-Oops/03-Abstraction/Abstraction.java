
public class Abstraction {

    

    /*
     * =================================================================
     *                         JAVA ABSTRACTION
     * =================================================================
     *
     * 1. INTRODUCTION TO ABSTRACTION
     *
     * Abstraction is one of the four fundamental pillars of
     * Object-Oriented Programming.
     *
     * Abstraction means exposing essential functionality while
     * hiding unnecessary implementation details from the user.
     *
     * It focuses on:
     *
     *     WHAT an object does rather than HOW it does it.
     *
     * REAL-WORLD EXAMPLE:
     *
     * Consider an ATM machine.
     *
     * A user can:
     *
     *     withdrawMoney()
     *     depositMoney()
     *     checkBalance()
     *
     * The user does not need to understand the internal details of:
     *
     *     Bank server communication
     *     Database queries
     *     Transaction processing
     *     Security validation
     *
     * The ATM exposes required operations and hides their
     * implementation details.
     *
     * JAVA ABSTRACTION:
     *
     * Java primarily provides two mechanisms for abstraction:
     *
     *     1. Abstract classes
     *     2. Interfaces
     *
     * IMPORTANT:
     *
     * Abstraction is a design principle. Abstract classes and
     * interfaces are language features that help implement it.
     *
     *
     * =================================================================
     * 2. WHY DO WE NEED ABSTRACTION?
     * =================================================================
     *
     * 1. REDUCES COMPLEXITY
     *
     *    Users interact with essential operations without needing
     *    to understand all implementation details.
     *
     * 2. HIDES IMPLEMENTATION DETAILS
     *
     *    Internal implementation can change without necessarily
     *    changing the exposed contract.
     *
     * 3. IMPROVES MAINTAINABILITY
     *
     *    Implementation changes can be isolated behind stable
     *    abstractions.
     *
     * 4. IMPROVES REUSABILITY
     *
     *    Common contracts can be used by multiple implementations.
     *
     * 5. SUPPORTS LOOSE COUPLING
     *
     *    Code can depend on abstractions rather than concrete
     *    implementation classes.
     *
     * 6. SUPPORTS EXTENSIBILITY
     *
     *    New implementations can be introduced while preserving
     *    existing contracts.
     *
     *
     * =================================================================
     * 3. ABSTRACTION VS ENCAPSULATION
     * =================================================================
     *
     * Both are important OOP concepts, but they solve different
     * problems.
     *
     * ABSTRACTION:
     *
     * Hides implementation complexity and exposes essential
     * functionality.
     *
     * ENCAPSULATION:
     *
     * Combines data and related methods within a class and controls
     * access to internal state.
     *
     * COMPARISON:
     *
     * Feature          Abstraction          Encapsulation
     * ------------------------------------------------------
     * Focus            What to expose       How to protect state
     * Purpose          Hide complexity      Control access
     * Mechanisms       Abstract classes     Access modifiers
     *                  and interfaces       and class design
     * Concern          Functionality        Data and access
     *
     * EXAMPLE:
     *
     *     abstract class Payment {
     *
     *         private double amount;
     *
     *         abstract void pay();
     *
     *         public double getAmount() {
     *             return amount;
     *         }
     *     }
     *
     * Abstraction:
     *     The pay() contract specifies an operation.
     *
     * Encapsulation:
     *     The amount field is private.
     *
     * IMPORTANT:
     *
     * Abstraction and encapsulation often work together.
     * They are not interchangeable terms.
     *
     *
     * =================================================================
     * 4. ABSTRACT CLASS
     * =================================================================
     *
     * An abstract class is a class declared using the abstract
     * keyword.
     *
     * It cannot be instantiated directly.
     *
     * SYNTAX:
     *
     *     abstract class ClassName {
     *
     *         // Fields
     *
     *         // Constructors
     *
     *         // Concrete methods
     *
     *         // Abstract methods
     *     }
     *
     * EXAMPLE:
     *
     *     abstract class Animal {
     *
     *         abstract void sound();
     *
     *         void eat() {
     *             System.out.println("Eating");
     *         }
     *     }
     *
     * IMPORTANT:
     *
     * - An abstract class cannot be instantiated directly.
     * - It can contain abstract methods.
     * - It can contain concrete methods.
     * - It can contain constructors.
     * - It can contain instance and static fields.
     * - It can contain static methods.
     * - It can implement interfaces.
     * - It can extend another class.
     * - It can have access modifiers.
     *
     * An abstract class can contain no abstract methods.
     * It can still be declared abstract to prevent direct
     * instantiation or to represent an incomplete base type.
     *
     *
     * =================================================================
     * 5. ABSTRACT METHOD
     * =================================================================
     *
     * An abstract method is a method declaration without a method body.
     *
     * It specifies a behaviour that a concrete subclass must
     * implement, unless an implementation is inherited.
     *
     * SYNTAX:
     *
     *     abstract returnType methodName(parameters);
     *
     * EXAMPLE:
     *
     *     abstract class Shape {
     *
     *         abstract double calculateArea();
     *     }
     *
     * The method declares what operation is required, not how
     * the area is calculated.
     *
     * IMPLEMENTATION:
     *
     *     class Circle extends Shape {
     *
     *         double radius;
     *
     *         Circle(double radius) {
     *             this.radius = radius;
     *         }
     *
     *         @Override
     *         double calculateArea() {
     *             return Math.PI * radius * radius;
     *         }
     *     }
     *
     *     class Rectangle extends Shape {
     *
     *         double length;
     *         double width;
     *
     *         Rectangle(double length, double width) {
     *             this.length = length;
     *             this.width = width;
     *         }
     *
     *         @Override
     *         double calculateArea() {
     *             return length * width;
     *         }
     *     }
     *
     * IMPORTANT RULES:
     *
     * 1. An abstract method has no method body.
     * 2. It must be declared inside an abstract class or interface.
     * 3. A concrete subclass must implement inherited abstract
     *    methods unless it inherits a valid implementation.
     * 4. An abstract method cannot be private.
     * 5. An abstract method cannot be final.
     * 6. An abstract method cannot be static.
     * 7. An abstract method cannot be synchronized.
     * 8. An abstract method cannot be native.
     *
     *
     * =================================================================
     * 6. ABSTRACT CLASS INSTANTIATION
     * =================================================================
     *
     * An abstract class cannot be instantiated directly.
     *
     * EXAMPLE:
     *
     *     abstract class Animal {
     *         abstract void sound();
     *     }
     *
     *     Animal a = new Animal(); // Compilation error
     *
     * However, a reference variable of an abstract class type
     * can be declared.
     *
     *     Animal a;
     *
     * It can refer to an object of a concrete subclass.
     *
     *     Animal a = new Dog();
     *
     * This is valid because Dog is concrete and extends Animal.
     *
     * EXAMPLE:
     *
     *     abstract class Animal {
     *         abstract void sound();
     *     }
     *
     *     class Dog extends Animal {
     *
     *         @Override
     *         void sound() {
     *             System.out.println("Bark");
     *         }
     *     }
     *
     *     Animal animal = new Dog();
     *     animal.sound();
     *
     * OUTPUT:
     *
     *     Bark
     *
     * IMPORTANT:
     *
     * The reference type determines which members can be accessed
     * at compile time, while overridden instance method execution
     * is determined by the runtime object type.
     *
     *
     * =================================================================
     * 7. CONCRETE SUBCLASS OF ABSTRACT CLASS
     * =================================================================
     *
     * A concrete subclass must provide implementations for all
     * inherited abstract methods that remain unimplemented.
     *
     * EXAMPLE:
     *
     *     abstract class Vehicle {
     *
     *         abstract void start();
     *
     *         abstract void stop();
     *     }
     *
     *     class Car extends Vehicle {
     *
     *         @Override
     *         void start() {
     *             System.out.println("Car started");
     *         }
     *
     *         @Override
     *         void stop() {
     *             System.out.println("Car stopped");
     *         }
     *     }
     *
     * If a subclass does not implement every required abstract
     * method, it must itself be declared abstract.
     *
     * EXAMPLE:
     *
     *     abstract class Vehicle {
     *         abstract void start();
     *         abstract void stop();
     *     }
     *
     *     abstract class Car extends Vehicle {
     *
     *         @Override
     *         void start() {
     *             System.out.println("Started");
     *         }
     *
     *         // stop() remains unimplemented.
     *     }
     *
     *     class SportsCar extends Car {
     *
     *         @Override
     *         void stop() {
     *             System.out.println("Stopped");
     *         }
     *     }
     *
     * SportsCar is concrete because all inherited abstract
     * methods have implementations.
     *
     *
     * =================================================================
     * 8. CONSTRUCTORS IN ABSTRACT CLASSES
     * =================================================================
     *
     * Abstract classes can have constructors.
     *
     * Although an abstract class cannot be instantiated directly,
     * its constructor executes when a concrete subclass object
     * is created.
     *
     * EXAMPLE:
     *
     *     abstract class Animal {
     *
     *         Animal() {
     *             System.out.println("Animal constructor");
     *         }
     *     }
     *
     *     class Dog extends Animal {
     *
     *         Dog() {
     *             System.out.println("Dog constructor");
     *         }
     *     }
     *
     *     Dog d = new Dog();
     *
     * OUTPUT:
     *
     *     Animal constructor
     *     Dog constructor
     *
     * WHY ARE CONSTRUCTORS ALLOWED?
     *
     * An abstract class can contain fields that require initialization.
     * Its constructor initializes the inherited portion of the
     * concrete subclass object.
     *
     * IMPORTANT:
     *
     * - Constructors are not inherited.
     * - A subclass constructor invokes a superclass constructor.
     * - An abstract class constructor cannot be invoked using new
     *   directly from application code.
     * - Abstract classes can have private, protected, package-private
     *   and public constructors.
     *
     *
     * =================================================================
     * 9. ABSTRACT CLASS WITH FIELDS AND METHODS
     * =================================================================
     *
     * An abstract class can combine shared state, common behaviour
     * and abstract operations.
     *
     * EXAMPLE:
     *
     *     abstract class Employee {
     *
     *         protected String name;
     *         protected double salary;
     *
     *         Employee(String name, double salary) {
     *             this.name = name;
     *             this.salary = salary;
     *         }
     *
     *         void displayDetails() {
     *             System.out.println(name);
     *             System.out.println(salary);
     *         }
     *
     *         abstract double calculateBonus();
     *     }
     *
     *     class Developer extends Employee {
     *
     *         Developer(String name, double salary) {
     *             super(name, salary);
     *         }
     *
     *         @Override
     *         double calculateBonus() {
     *             return salary * 0.10;
     *         }
     *     }
     *
     * Here:
     *
     * - name and salary represent shared state.
     * - displayDetails() represents shared behaviour.
     * - calculateBonus() represents behaviour that varies.
     *
     * This is a common reason to choose an abstract class.
     *
     *
     * =================================================================
     * 10. ABSTRACT CLASS VS CONCRETE CLASS
     * =================================================================
     *
     * ABSTRACT CLASS:
     *
     * - Declared using abstract.
     * - Cannot be instantiated directly.
     * - May contain abstract methods.
     * - May contain concrete methods.
     * - Can be extended by other classes.
     *
     * CONCRETE CLASS:
     *
     * - Is not declared abstract.
     * - Can be instantiated if it has an accessible constructor.
     * - Must satisfy all inherited abstract method requirements.
     * - Provides complete implementations of its abstract methods.
     *
     * IMPORTANT:
     *
     * A concrete class does not necessarily need to declare
     * methods of its own. It may inherit implementations.
     *
     *
     * =================================================================
     * 11. INTERFACE IN JAVA
     * =================================================================
     *
     * An interface is a reference type that defines a contract
     * which implementing classes agree to provide.
     *
     * It is commonly used to achieve abstraction and support
     * multiple inheritance of type.
     *
     * SYNTAX:
     *
     *     interface InterfaceName {
     *
     *         // Constants
     *         // Abstract methods
     *         // Default methods
     *         // Static methods
     *         // Private methods
     *     }
     *
     * EXAMPLE:
     *
     *     interface Payment {
     *
     *         void pay(double amount);
     *     }
     *
     *     class UpiPayment implements Payment {
     *
     *         @Override
     *         public void pay(double amount) {
     *             System.out.println("Paid using UPI");
     *         }
     *     }
     *
     *     class CardPayment implements Payment {
     *
     *         @Override
     *         public void pay(double amount) {
     *             System.out.println("Paid using card");
     *         }
     *     }
     *
     * IMPORTANT:
     *
     * - An interface cannot be instantiated directly.
     * - A class uses implements to implement an interface.
     * - An interface can extend other interfaces.
     * - A class can implement multiple interfaces.
     * - An interface can contain abstract, default, static and
     *   private methods under the applicable Java version rules.
     *
     *
     * =================================================================
     * 12. IMPLEMENTING AN INTERFACE
     * =================================================================
     *
     * A class implements an interface using the implements keyword.
     *
     * EXAMPLE:
     *
     *     interface Animal {
     *
     *         void sound();
     *     }
     *
     *     class Dog implements Animal {
     *
     *         @Override
     *         public void sound() {
     *             System.out.println("Bark");
     *         }
     *     }
     *
     * IMPORTANT:
     *
     * Ordinary abstract methods declared in an interface are
     * implicitly public and abstract.
     *
     * Therefore, the implementing method must be public.
     *
     * This is invalid:
     *
     *     class Dog implements Animal {
     *
     *         void sound() {
     *             System.out.println("Bark");
     *         }
     *     }
     *
     * It attempts to reduce the visibility of the public
     * interface method.
     *
     *
     * =================================================================
     * 13. INTERFACE VARIABLES
     * =================================================================
     *
     * Every field declared in an interface is implicitly:
     *
     *     public static final
     *
     * EXAMPLE:
     *
     *     interface Configuration {
     *
     *         int MAX_USERS = 100;
     *     }
     *
     * Equivalent declaration:
     *
     *     interface Configuration {
     *
     *         public static final int MAX_USERS = 100;
     *     }
     *
     * IMPORTANT:
     *
     * 1. Interface fields are constants.
     * 2. They must be initialized.
     * 3. They belong to the interface, not individual objects.
     * 4. They cannot be reassigned.
     *
     * Access:
     *
     *     System.out.println(Configuration.MAX_USERS);
     *
     *
     * =================================================================
     * 14. TYPES OF METHODS IN INTERFACES
     * =================================================================
     *
     * Modern Java interfaces support several kinds of methods.
     *
     * A. ABSTRACT METHODS
     *
     * Declared without a body.
     *
     *     interface Vehicle {
     *         void start();
     *     }
     *
     * These methods are implicitly public and abstract.
     *
     * B. DEFAULT METHODS
     *
     * Introduced in Java 8.
     *
     * They contain an implementation and are inherited by
     * implementing classes under interface method rules.
     *
     *     interface Vehicle {
     *
     *         default void stop() {
     *             System.out.println("Vehicle stopped");
     *         }
     *     }
     *
     * C. STATIC METHODS
     *
     * Introduced in Java 8.
     *
     * They belong to the interface and are called using its name.
     *
     *     interface Vehicle {
     *
     *         static void info() {
     *             System.out.println("Vehicle interface");
     *         }
     *     }
     *
     *     Vehicle.info();
     *
     * Interface static methods are not inherited as instance
     * methods by implementing classes.
     *
     * D. PRIVATE METHODS
     *
     * Introduced in Java 9.
     *
     * They allow code reuse among methods within the interface.
     *
     *     interface Vehicle {
     *
     *         private void log() {
     *             System.out.println("Logging");
     *         }
     *
     *         default void start() {
     *             log();
     *             System.out.println("Started");
     *         }
     *     }
     *
     * IMPORTANT:
     *
     * Private interface methods cannot be accessed directly
     * by implementing classes.
     *
     *
     * =================================================================
     * 15. MULTIPLE INHERITANCE THROUGH INTERFACES
     * =================================================================
     *
     * Java does not support multiple inheritance of classes.
     *
     * A class cannot directly extend more than one class.
     *
     * However, a class can implement multiple interfaces.
     *
     * EXAMPLE:
     *
     *     interface Printable {
     *         void print();
     *     }
     *
     *     interface Scannable {
     *         void scan();
     *     }
     *
     *     class Machine implements Printable, Scannable {
     *
     *         @Override
     *         public void print() {
     *             System.out.println("Printing");
     *         }
     *
     *         @Override
     *         public void scan() {
     *             System.out.println("Scanning");
     *         }
     *     }
     *
     * This provides multiple inheritance of type and allows
     * a class to implement contracts from multiple interfaces.
     *
     *
     * =================================================================
     * 16. INTERFACE EXTENDING ANOTHER INTERFACE
     * =================================================================
     *
     * An interface can extend one or more interfaces using extends.
     *
     * EXAMPLE:
     *
     *     interface Animal {
     *         void eat();
     *     }
     *
     *     interface Pet extends Animal {
     *         void play();
     *     }
     *
     *     class Dog implements Pet {
     *
     *         @Override
     *         public void eat() {
     *             System.out.println("Eating");
     *         }
     *
     *         @Override
     *         public void play() {
     *             System.out.println("Playing");
     *         }
     *     }
     *
     * Pet inherits the contract declared by Animal.
     *
     * An interface can extend multiple interfaces:
     *
     *     interface SmartDevice extends Camera, MusicPlayer {
     *     }
     *
     *
     * =================================================================
     * 17. ABSTRACT CLASS VS INTERFACE
     * =================================================================
     *
     * ABSTRACT CLASS:
     *
     * - Declared using abstract class.
     * - A class can extend only one class.
     * - Can have instance fields and constructors.
     * - Can maintain object state.
     * - Can have abstract and concrete methods.
     * - Members can use different access modifiers.
     * - Can contain static members.
     *
     * INTERFACE:
     *
     * - Declared using interface.
     * - A class can implement multiple interfaces.
     * - Cannot have ordinary instance fields.
     * - Cannot have constructors.
     * - Fields are implicitly public static final.
     * - Supports abstract, default, static and private methods
     *   according to Java version rules.
     * - Supports multiple inheritance of type.
     *
     * COMPARISON:
     *
     * Feature             Abstract Class       Interface
     * -------------------------------------------------------
     * Declaration         abstract class       interface
     * Instantiation       Not directly         Not directly
     * Constructors        Yes                  No
     * Instance fields     Yes                  No
     * Constants           Possible             Implicitly static
     *                                         final and public
     * Concrete methods    Yes                  Yes, with supported
     *                                         method forms
     * Abstract methods    Yes                  Yes
     * Multiple extension  One class            Multiple interfaces
     * Inheritance keyword extends              implements
     * Interface extension extends              extends
     *
     * WHEN TO USE AN ABSTRACT CLASS:
     *
     * 1. Related classes share common state.
     * 2. Related classes share common implementation.
     * 3. Constructors are needed for common initialization.
     * 4. A common base class represents a meaningful hierarchy.
     *
     * WHEN TO USE AN INTERFACE:
     *
     * 1. Different classes should follow a common contract.
     * 2. Multiple unrelated classes need the same capability.
     * 3. A class needs to implement multiple types.
     * 4. Loose coupling and interchangeable implementations
     *    are important.
     *
     * IMPORTANT:
     *
     * Modern interfaces can contain implementations and abstract
     * classes can contain no abstract methods. Choose based on
     * design requirements, not merely on whether implementation
     * code is present.
     *
     *
     * =================================================================
     * 18. DIAMOND PROBLEM IN INTERFACES
     * =================================================================
     *
     * The diamond problem occurs when a type inherits competing
     * implementations of the same method through multiple paths.
     *
     * Java interfaces can contain default methods, so conflicts
     * may arise.
     *
     * EXAMPLE:
     *
     *     interface A {
     *
     *         default void display() {
     *             System.out.println("A");
     *         }
     *     }
     *
     *     interface B {
     *
     *         default void display() {
     *             System.out.println("B");
     *         }
     *     }
     *
     *     class C implements A, B {
     *
     *         @Override
     *         public void display() {
     *
     *             A.super.display();
     *             B.super.display();
     *         }
     *     }
     *
     * C must resolve the conflicting default implementations.
     *
     * Java's interface inheritance rules also define precedence:
     *
     * 1. A concrete class method takes precedence over an interface
     *    default method.
     * 2. A more specific interface default method takes precedence
     *    over a less specific one.
     * 3. Unresolved conflicts between unrelated defaults require
     *    an explicit implementation.
     *
     * This is how Java avoids ambiguous inherited default behaviour.
     *
     *
     * =================================================================
     * 19. MULTIPLE INHERITANCE: CLASS VS INTERFACE
     * =================================================================
     *
     * Java does not permit:
     *
     *     class C extends A, B {
     *     }
     *
     * A class can extend only one direct superclass.
     *
     * Java permits:
     *
     *     class C implements A, B {
     *     }
     *
     * WHY?
     *
     * Multiple class inheritance can introduce ambiguity involving
     * inherited state, constructors and method implementations.
     *
     * Interfaces provide multiple type relationships with explicit
     * rules for resolving default-method conflicts.
     *
     * IMPORTANT:
     *
     * Java supports multiple inheritance of type through interfaces.
     * It does not support multiple inheritance of classes.
     *
     *
     * =================================================================
     * 20. FUNCTIONAL INTERFACES
     * =================================================================
     *
     * A functional interface is an interface with exactly one
     * abstract method, after accounting for methods that do not
     * count as its functional method under Java's rules.
     *
     * It may contain:
     *
     * - Default methods
     * - Static methods
     * - Private methods
     *
     * EXAMPLE:
     *
     *     @FunctionalInterface
     *     interface Calculator {
     *
     *         int calculate(int a, int b);
     *     }
     *
     * A functional interface can be implemented using a lambda
     * expression.
     *
     *     Calculator add = (a, b) -> a + b;
     *
     *     System.out.println(add.calculate(10, 20));
     *
     * OUTPUT:
     *
     *     30
     *
     * IMPORTANT:
     *
     * @FunctionalInterface is optional.
     *
     * When present, the compiler verifies that the interface
     * satisfies the functional-interface requirements.
     *
     * Functional interfaces are important for lambda expressions
     * and functional-style programming.
     *
     * Examples from Java:
     *
     *     Runnable
     *     Callable
     *     Comparator
     *     Predicate
     *     Function
     *     Consumer
     *     Supplier
     *
     *
     * =================================================================
     * 21. MARKER INTERFACES
     * =================================================================
     *
     * A marker interface is an interface that declares no methods
     * or fields.
     *
     * It is used to identify or mark a class as having a particular
     * characteristic recognized by a framework or API.
     *
     * EXAMPLES:
     *
     *     Serializable
     *     Cloneable
     *
     * Example:
     *
     *     class Employee implements java.io.Serializable {
     *     }
     *
     * The Serializable interface indicates that an object is
     * eligible for Java serialization under the relevant rules.
     *
     * Cloneable indicates that Object.clone() is permitted to
     * perform its field-by-field copying mechanism for the object.
     *
     * IMPORTANT:
     *
     * A marker interface is not the same as an annotation.
     *
     *
     * =================================================================
     * 22. ABSTRACTION THROUGH LOOSE COUPLING
     * =================================================================
     *
     * Loose coupling means reducing direct dependency between
     * components.
     *
     * A component depends on an abstraction rather than a
     * specific implementation.
     *
     * EXAMPLE:
     *
     *     interface Notification {
     *         void send(String message);
     *     }
     *
     *     class EmailNotification implements Notification {
     *
     *         @Override
     *         public void send(String message) {
     *             System.out.println("Email: " + message);
     *         }
     *     }
     *
     *     class SmsNotification implements Notification {
     *
     *         @Override
     *         public void send(String message) {
     *             System.out.println("SMS: " + message);
     *         }
     *     }
     *
     *     class AlertService {
     *
     *         private final Notification notification;
     *
     *         AlertService(Notification notification) {
     *             this.notification = notification;
     *         }
     *
     *         void alert(String message) {
     *             notification.send(message);
     *         }
     *     }
     *
     * Usage:
     *
     *     Notification notification = new EmailNotification();
     *
     *     AlertService service = new AlertService(notification);
     *
     *     service.alert("Server is down");
     *
     * AlertService depends on the Notification abstraction.
     *
     * A different implementation can be supplied without changing
     * the AlertService class.
     *
     * This is a foundation for dependency injection and
     * interface-based application design.
     *
     *
     * =================================================================
     * 23. ABSTRACTION IN REAL-WORLD JAVA APPLICATIONS
     * =================================================================
     *
     * Abstraction is widely used in Java APIs and frameworks.
     *
     * A. JDBC
     *
     * java.sql.Connection, Statement and ResultSet provide
     * standard database operation contracts.
     *
     * Different JDBC drivers provide implementations for
     * different database systems.
     *
     * B. COLLECTIONS
     *
     * List is an interface.
     *
     * ArrayList and LinkedList are implementations.
     *
     * Example:
     *
     *     List<String> names = new ArrayList<>();
     *
     * Application code can use the List contract without depending
     * on every implementation detail of ArrayList.
     *
     * C. SPRING FRAMEWORK
     *
     * Spring commonly uses interfaces and abstractions to reduce
     * coupling between application components.
     *
     * D. FILE HANDLING
     *
     * InputStream and OutputStream provide abstract stream
     * contracts, with concrete implementations for different
     * sources and destinations.
     *
     * IMPORTANT:
     *
     * These examples show how abstractions enable interchangeable
     * implementations. They do not mean every implementation can
     * be substituted without considering its behavioural contract.
     *
     *
     * =================================================================
     * 24. COMMON MISTAKES
     * =================================================================
     *
     * 1. Assuming an abstract class cannot contain a constructor.
     *
     * 2. Assuming an abstract class must contain an abstract method.
     *
     * 3. Trying to instantiate an abstract class directly.
     *
     * 4. Forgetting to implement inherited abstract methods.
     *
     * 5. Implementing an interface method with weaker access.
     *
     * 6. Assuming interfaces can have constructors.
     *
     * 7. Assuming interfaces can contain ordinary instance fields.
     *
     * 8. Assuming all interface methods are abstract in modern Java.
     *
     * 9. Assuming static interface methods are inherited by
     *    implementing classes.
     *
     * 10. Assuming a class can extend multiple classes.
     *
     * 11. Assuming an interface can be instantiated directly.
     *
     * 12. Confusing abstraction with encapsulation.
     *
     * 13. Assuming an interface can have only one implementation.
     *
     * 14. Assuming every abstract class is a better choice than
     *     an interface whenever methods have implementations.
     *
     * 15. Forgetting to resolve conflicting interface default methods.
     *
     *
     * =================================================================
     * 25. INTERVIEW QUESTIONS
     * =================================================================
     *
     * Q1. What is abstraction?
     *
     * Exposing essential functionality while hiding implementation
     * details.
     *
     * Q2. How is abstraction achieved in Java?
     *
     * Mainly through abstract classes and interfaces.
     *
     * Q3. What is an abstract class?
     *
     * A class declared with abstract that cannot be instantiated
     * directly.
     *
     * Q4. What is an abstract method?
     *
     * A method declaration without an implementation body.
     *
     * Q5. Can an abstract class have constructors?
     *
     * Yes. They initialize the superclass portion of subclass objects.
     *
     * Q6. Can an abstract class have no abstract methods?
     *
     * Yes.
     *
     * Q7. Can an abstract class be instantiated?
     *
     * No, but its reference can point to a concrete subclass object.
     *
     * Q8. Can an abstract method be private?
     *
     * No.
     *
     * Q9. Can an abstract method be static?
     *
     * No.
     *
     * Q10. Can an abstract method be final?
     *
     * No.
     *
     * Q11. What is an interface?
     *
     * A reference type that defines a contract for implementing types.
     *
     * Q12. Can an interface have constructors?
     *
     * No.
     *
     * Q13. Can an interface contain variables?
     *
     * Yes. They are implicitly public, static and final.
     *
     * Q14. Can an interface have implemented methods?
     *
     * Yes. It can contain default, static and private methods
     * under the applicable Java version rules.
     *
     * Q15. Can a class implement multiple interfaces?
     *
     * Yes.
     *
     * Q16. Can a class extend multiple classes?
     *
     * No.
     *
     * Q17. Can an interface extend multiple interfaces?
     *
     * Yes.
     *
     * Q18. What is the difference between an abstract class
     *     and an interface?
     *
     * An abstract class supports shared state and constructors.
     * An interface defines a contract and supports multiple
     * inheritance of type.
     *
     * Q19. What is a functional interface?
     *
     * An interface with one abstract method under Java's
     * functional-interface rules.
     *
     * Q20. What is a marker interface?
     *
     * An interface with no declared methods or fields that marks
     * a type for special recognition.
     *
     * Q21. What is the diamond problem?
     *
     * A method implementation conflict caused by multiple
     * inheritance paths.
     *
     * Q22. How does Java resolve default method conflicts?
     *
     * Through class-method precedence, interface specificity
     * and explicit overriding where required.
     *
     * Q23. What is loose coupling?
     *
     * Reducing direct dependencies between components by depending
     * on abstractions and contracts.
     *
     *
     * =================================================================
     * 26. FINAL REVISION CHECKLIST
     * =================================================================
     *
     * FUNDAMENTALS:
     *
     * [ ] Definition of abstraction
     * [ ] Why abstraction is needed
     * [ ] Abstraction vs encapsulation
     *
     * ABSTRACT CLASSES:
     *
     * [ ] Abstract class declaration
     * [ ] Abstract methods
     * [ ] Concrete methods
     * [ ] Constructors in abstract classes
     * [ ] Abstract class instantiation
     * [ ] Concrete subclasses
     * [ ] Abstract class with fields and methods
     *
     * INTERFACES:
     *
     * [ ] Interface declaration
     * [ ] Implementing interfaces
     * [ ] Interface fields
     * [ ] Abstract methods
     * [ ] Default methods
     * [ ] Static methods
     * [ ] Private methods
     * [ ] Interface inheritance
     * [ ] Multiple interfaces
     *
     * ADVANCED CONCEPTS:
     *
     * [ ] Abstract class vs interface
     * [ ] Diamond problem
     * [ ] Multiple inheritance through interfaces
     * [ ] Functional interfaces
     * [ ] Marker interfaces
     * [ ] Loose coupling
     * [ ] Real-world Java API examples
     *
     * INTERVIEW PREPARATION:
     *
     * [ ] Abstract class rules
     * [ ] Interface rules
     * [ ] Invalid modifier combinations
     * [ ] Default method conflicts
     * [ ] Practical design scenarios
     *
     *
     * =================================================================
     *                       END OF ABSTRACTION
     * =================================================================
     */

    public static void main(String[] args) {
        System.out.print("The above is all about Abstraction , Happy Learning ..!");
    }

}
