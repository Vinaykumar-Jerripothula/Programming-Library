
public class Modifiers {

    /*
     * ================================================================
     *                       JAVA MODIFIERS
     * ================================================================
     *
     * 1. INTRODUCTION TO MODIFIERS
     *
     * Modifiers are keywords in Java that control the accessibility,
     * behaviour, characteristics and restrictions of classes,
     * methods, variables, constructors and other declarations.
     *
     * Java modifiers are broadly classified into:
     *
     * 1. Access Modifiers
     * 2. Non-Access Modifiers
     *
     * ACCESS MODIFIERS:
     *
     *     public
     *     protected
     *     default (package-private: no keyword)
     *     private
     *
     * NON-ACCESS MODIFIERS:
     *
     *     static
     *     final
     *     abstract
     *     synchronized
     *     native
     *     transient
     *     volatile
     *     strictfp
     *
     * Additional declaration-related keywords and modifiers include:
     *
     *     sealed
     *     non-sealed
     *     default (interface methods)
     *
     * IMPORTANT:
     *
     * The word default has two different uses:
     *
     * 1. No access modifier on a class or member means package-private.
     * 2. The default keyword declares a default method in an interface.
     *
     * These are not the same concept.
     *
     *
     * ================================================================
     * 2. ACCESS MODIFIERS
     * ================================================================
     *
     * Access modifiers control where a class, member, constructor
     * or nested type can be accessed.
     *
     * Java provides four access levels:
     *
     *     public
     *     protected
     *     package-private
     *     private
     *
     *
     * A. PUBLIC
     *
     * The public modifier provides the widest Java language-level
     * accessibility.
     *
     * A public member can be accessed from other packages, provided
     * its enclosing types and module boundaries also permit access.
     *
     * EXAMPLE:
     *
     *     public class Employee {
     *
     *         public String name = "Vinay";
     *
     *         public void display() {
     *             System.out.println(name);
     *         }
     *     }
     *
     * A public class or member can be referenced from another package
     * when the relevant enclosing types are accessible.
     *
     * IMPORTANT:
     *
     * public does not mean accessible from absolutely everywhere.
     * Java modules, enclosing-type accessibility and other language
     * rules can still restrict access.
     *
     *
     * B. PRIVATE
     *
     * The private modifier restricts access to the enclosing top-level
     * class's permitted private-access scope.
     *
     * It is commonly used for encapsulation.
     *
     * EXAMPLE:
     *
     *     class Employee {
     *
     *         private double salary = 50000;
     *
     *         private void calculateSalary() {
     *             System.out.println(salary);
     *         }
     *
     *         public void displaySalary() {
     *             calculateSalary();
     *         }
     *     }
     *
     * The salary field and calculateSalary() method cannot be
     * accessed directly by unrelated classes.
     *
     * IMPORTANT:
     *
     * Java permits access between nested classes and their enclosing
     * classes to private members under its private-access rules.
     *
     * private is not permitted on a top-level class or interface.
     * It can be used for nested classes and other eligible members.
     *
     *
     * C. PACKAGE-PRIVATE (DEFAULT ACCESS)
     *
     * When no access modifier is specified, the declaration receives
     * package-private accessibility, where applicable.
     *
     * There is no keyword named default for this access level.
     *
     * EXAMPLE:
     *
     *     class Employee {
     *
     *         String name = "Vinay";
     *
     *         void display() {
     *             System.out.println(name);
     *         }
     *     }
     *
     * Employee, name and display() are package-private.
     *
     * They can be accessed by eligible code in the same package.
     *
     * They cannot be accessed directly from unrelated packages.
     *
     * IMPORTANT:
     *
     * Subpackages are separate packages. Package-private access
     * does not extend to subpackages.
     *
     *
     * D. PROTECTED
     *
     * The protected modifier provides:
     *
     * 1. Access within the same package.
     * 2. Access through inheritance from subclasses in other packages,
     *    subject to additional access restrictions.
     *
     * EXAMPLE:
     *
     *     package company;
     *
     *     public class Employee {
     *
     *         protected String name = "Vinay";
     *
     *         protected void display() {
     *             System.out.println(name);
     *         }
     *     }
     *
     * A class in another package can access protected members
     * through inheritance under the applicable rules.
     *
     * IMPORTANT CROSS-PACKAGE RULE:
     *
     * Consider:
     *
     *     package other;
     *
     *     import company.Employee;
     *
     *     public class Manager extends Employee {
     *
     *         void show() {
     *             System.out.println(name); // Allowed
     *         }
     *     }
     *
     * A subclass in another package can access an inherited
     * protected instance member through itself or an appropriate
     * subclass-typed reference.
     *
     * However, it cannot access that protected instance member
     * through an arbitrary superclass-typed reference.
     *
     * Example:
     *
     *     Employee employee = new Employee();
     *
     *     System.out.println(employee.name); // Not allowed here
     *
     * This restriction is important in cross-package inheritance.
     *
     * Protected static members have different access details from
     * protected instance members; follow the Java access rules
     * applicable to the expression and declaring type.
     *
     *
     * ================================================================
     * 3. ACCESS MODIFIER COMPARISON
     * ================================================================
     *
     * GENERAL ACCESSIBILITY TABLE:
     *
     * Declaration location       private  default  protected  public
     *
     * Same class                    YES      YES       YES       YES
     * Same package                  NO       YES       YES       YES
     * Different package,
     * non-subclass                  NO       NO        NO        YES
     * Different package,
     * subclass                     NO       NO        YES*      YES
     *
     * * protected in a different-package subclass is subject to
     *   the special inheritance and qualifying-reference rules.
     *
     * This table assumes the enclosing types and modules are
     * themselves accessible.
     *
     * ACCESSIBILITY ORDER:
     *
     *     private
     *        |
     *     package-private
     *        |
     *     protected
     *        |
     *     public
     *
     * The order represents increasing general accessibility.
     *
     * IMPORTANT:
     *
     * A more accessible declaration can generally override a
     * less accessible inherited method, but an overriding method
     * cannot reduce the accessibility of the overridden method.
     *
     *
     * ================================================================
     * 4. WHERE ACCESS MODIFIERS CAN BE USED
     * ================================================================
     *
     * TOP-LEVEL CLASSES:
     *
     * A top-level class can be:
     *
     *     public
     *     package-private
     *
     * A top-level class cannot be:
     *
     *     private
     *     protected
     *
     * NESTED CLASSES:
     *
     * A nested class can use:
     *
     *     public
     *     protected
     *     private
     *     package-private
     *
     * METHODS:
     *
     * Methods can use all four access levels.
     *
     * VARIABLES / FIELDS:
     *
     * Fields can use all four access levels.
     *
     * CONSTRUCTORS:
     *
     * Constructors can use all four access levels.
     *
     * LOCAL VARIABLES:
     *
     * Local variables cannot have access modifiers.
     *
     * Example:
     *
     *     void display() {
     *         private int value = 10; // Compilation error
     *     }
     *
     * IMPORTANT:
     *
     * Access modifiers control visibility. They do not directly
     * determine object creation, inheritance or memory allocation.
     *
     *
     * ================================================================
     * 5. STATIC MODIFIER
     * ================================================================
     *
     * The static modifier makes a member belong to the class rather
     * than to individual instances.
     *
     * It can be used with:
     *
     *     Fields
     *     Methods
     *     Static initialization blocks
     *     Nested classes
     *
     *
     * A. STATIC VARIABLE
     *
     * A static variable is associated with the class.
     * There is one class-level variable for that declaration,
     * rather than a separate instance field for each object.
     *
     * EXAMPLE:
     *
     *     class Employee {
     *
     *         static String company = "ABC";
     *
     *         String name;
     *     }
     *
     *     Employee e1 = new Employee();
     *     Employee e2 = new Employee();
     *
     *     Employee.company = "XYZ";
     *
     * Both objects observe the updated class-level value.
     *
     * Access static fields preferably through the class name.
     *
     *     Employee.company
     *
     *
     * B. STATIC METHOD
     *
     * A static method belongs to the class.
     *
     * EXAMPLE:
     *
     *     class Calculator {
     *
     *         static int add(int a, int b) {
     *             return a + b;
     *         }
     *     }
     *
     *     int result = Calculator.add(10, 20);
     *
     * IMPORTANT RULES:
     *
     * 1. A static method can directly access static members.
     * 2. It cannot directly access instance members without an
     *    object reference.
     * 3. It cannot use this or super.
     * 4. Static methods are hidden, not overridden, in subclasses.
     * 5. Static methods cannot be abstract.
     *
     * EXAMPLE:
     *
     *     class Demo {
     *
     *         int value = 10;
     *         static int count = 20;
     *
     *         static void display() {
     *             System.out.println(count); // Allowed
     *
     *             // System.out.println(value); // Error
     *             // System.out.println(this.value); // Error
     *         }
     *     }
     *
     *
     * C. STATIC BLOCK
     *
     * A static initialization block executes when the class is
     * initialized.
     *
     * It is commonly used for complex static initialization.
     *
     * EXAMPLE:
     *
     *     class Demo {
     *
     *         static int value;
     *
     *         static {
     *             value = 100;
     *             System.out.println("Static block");
     *         }
     *     }
     *
     * Static initialization generally occurs once per class
     * initialization by a given class loader.
     *
     *
     * D. STATIC NESTED CLASS
     *
     * A static nested class is a nested class declared using static.
     *
     * EXAMPLE:
     *
     *     class Outer {
     *
     *         static int value = 100;
     *
     *         static class Nested {
     *
     *             void display() {
     *                 System.out.println(value);
     *             }
     *         }
     *     }
     *
     *     Outer.Nested obj = new Outer.Nested();
     *
     * IMPORTANT:
     *
     * A static nested class does not require an instance of the
     * enclosing class.
     *
     * A non-static inner class normally requires an enclosing
     * instance.
     *
     * A top-level class cannot be declared static.
     *
     *
     * ================================================================
     * 6. FINAL MODIFIER
     * ================================================================
     *
     * The final modifier restricts further modification or extension,
     * depending on where it is applied.
     *
     * It can be used with:
     *
     *     Variables
     *     Methods
     *     Classes
     *
     *
     * A. FINAL VARIABLE
     *
     * A final variable can be assigned only once.
     *
     * EXAMPLE:
     *
     *     final int age = 21;
     *
     *     // age = 22; // Compilation error
     *
     * A final variable must be definitely assigned before it is read.
     *
     * FINAL INSTANCE VARIABLE:
     *
     *     class Employee {
     *
     *         final int id;
     *
     *         Employee(int id) {
     *             this.id = id;
     *         }
     *     }
     *
     * A blank final instance variable can be initialized in an
     * instance initializer or constructor, subject to definite
     * assignment rules.
     *
     * FINAL STATIC VARIABLE:
     *
     *     class Configuration {
     *
     *         static final int MAX_USERS;
     *
     *         static {
     *             MAX_USERS = 100;
     *         }
     *     }
     *
     * A blank final static variable can be initialized in a static
     * initializer or at its declaration.
     *
     * FINAL REFERENCE:
     *
     * A final reference cannot be reassigned to another object.
     * However, the referenced object's state may still change.
     *
     * EXAMPLE:
     *
     *     final StringBuilder sb = new StringBuilder("Java");
     *
     *     sb.append(" Programming"); // Allowed
     *
     *     // sb = new StringBuilder("Python"); // Error
     *
     * IMPORTANT:
     *
     * final does not automatically make an object immutable.
     *
     *
     * B. FINAL METHOD
     *
     * A final method cannot be overridden by a subclass.
     *
     * EXAMPLE:
     *
     *     class Parent {
     *
     *         final void display() {
     *             System.out.println("Parent");
     *         }
     *     }
     *
     *     class Child extends Parent {
     *
     *         // void display() { } // Compilation error
     *     }
     *
     * IMPORTANT:
     *
     * A final method can still be overloaded.
     *
     * A private method is not inherited in the ordinary overriding
     * sense, so it cannot be overridden.
     *
     *
     * C. FINAL CLASS
     *
     * A final class cannot be subclassed.
     *
     * EXAMPLE:
     *
     *     final class Security {
     *     }
     *
     *     // class AdvancedSecurity extends Security { }
     *     // Compilation error
     *
     * Examples from Java:
     *
     *     String
     *     Integer
     *     Double
     *
     * These classes are final.
     *
     * IMPORTANT:
     *
     * A final class cannot be abstract because a final class
     * cannot be subclassed, while an abstract class is designed
     * to be extended or implemented through inheritance.
     *
     *
     * ================================================================
     * 7. ABSTRACT MODIFIER
     * ================================================================
     *
     * The abstract modifier indicates an incomplete class or method
     * that requires implementation through a concrete subclass.
     *
     * It can be used with:
     *
     *     Classes
     *     Methods
     *
     *
     * A. ABSTRACT CLASS
     *
     * An abstract class cannot be instantiated directly.
     *
     * EXAMPLE:
     *
     *     abstract class Animal {
     *
     *         void eat() {
     *             System.out.println("Eating");
     *         }
     *
     *         abstract void sound();
     *     }
     *
     *     class Dog extends Animal {
     *
     *         @Override
     *         void sound() {
     *             System.out.println("Barking");
     *         }
     *     }
     *
     *     Dog dog = new Dog();
     *     dog.eat();
     *     dog.sound();
     *
     * IMPORTANT:
     *
     * 1. An abstract class cannot be instantiated directly.
     * 2. It can contain abstract and concrete methods.
     * 3. It can contain constructors.
     * 4. It can contain fields and static methods.
     * 5. It can implement interfaces.
     * 6. It can extend another class.
     * 7. A subclass must implement inherited abstract methods
     *    unless that subclass is also abstract.
     *
     *
     * B. ABSTRACT METHOD
     *
     * An abstract method declares a method signature without
     * providing an implementation.
     *
     * EXAMPLE:
     *
     *     abstract class Shape {
     *
     *         abstract double calculateArea();
     *     }
     *
     * The concrete subclass provides the implementation.
     *
     * IMPORTANT RULES:
     *
     * 1. An abstract method cannot be private.
     * 2. An abstract method cannot be final.
     * 3. An abstract method cannot be static.
     * 4. An abstract method cannot be synchronized.
     * 5. An abstract method cannot be native.
     * 6. An abstract method has no method body.
     *
     * A method cannot be both abstract and final because abstract
     * requires implementation by a subclass, while final prevents
     * overriding.
     *
     * A method cannot be both abstract and private because private
     * methods are not inherited for overriding.
     *
     * An abstract class is not required to contain an abstract method.
     *
     *
     * ================================================================
     * 8. SYNCHRONIZED MODIFIER
     * ================================================================
     *
     * The synchronized modifier is used to control concurrent access
     * to critical sections of code using an object's monitor or
     * the class object's monitor.
     *
     * It can be applied to methods and synchronized blocks.
     *
     * A. SYNCHRONIZED INSTANCE METHOD
     *
     * A synchronized instance method acquires the monitor of the
     * current object (this).
     *
     * EXAMPLE:
     *
     *     class Counter {
     *
     *         int count = 0;
     *
     *         synchronized void increment() {
     *             count++;
     *         }
     *     }
     *
     * Only one thread at a time can execute synchronized instance
     * methods holding the same object's monitor.
     *
     *
     * B. SYNCHRONIZED STATIC METHOD
     *
     * A synchronized static method acquires the monitor associated
     * with the Class object.
     *
     * EXAMPLE:
     *
     *     class Counter {
     *
     *         static int count = 0;
     *
     *         static synchronized void increment() {
     *             count++;
     *         }
     *     }
     *
     * The monitor is associated with Counter.class.
     *
     *
     * C. SYNCHRONIZED BLOCK
     *
     * A synchronized block allows synchronization over a specific
     * section of code.
     *
     * EXAMPLE:
     *
     *     synchronized (this) {
     *         count++;
     *     }
     *
     * Static-context example:
     *
     *     synchronized (Counter.class) {
     *         // Critical section
     *     }
     *
     * IMPORTANT:
     *
     * 1. Synchronization provides mutual exclusion for threads
     *    coordinating through the same monitor.
     * 2. It also establishes memory-visibility guarantees.
     * 3. Synchronized methods and blocks use intrinsic monitors.
     * 4. Java intrinsic locks are reentrant.
     * 5. Synchronization does not automatically make every operation
     *    in a class thread-safe.
     * 6. Different object monitors do not block one another.
     * 7. Excessive synchronization can reduce performance.
     *
     * A constructor cannot be declared synchronized.
     *
     *
     * ================================================================
     * 9. VOLATILE MODIFIER
     * ================================================================
     *
     * The volatile modifier is used with fields to provide
     * visibility and ordering guarantees between threads.
     *
     * EXAMPLE:
     *
     *     class SharedData {
     *
     *         volatile boolean running = true;
     *     }
     *
     * When one thread updates a volatile field, other threads that
     * subsequently read that field are guaranteed to observe the
     * appropriate volatile write under Java Memory Model rules.
     *
     * IMPORTANT:
     *
     * 1. volatile applies to fields.
     * 2. It provides visibility guarantees.
     * 3. It establishes ordering guarantees around volatile reads
     *    and writes.
     * 4. It does not make compound operations atomic.
     * 5. It does not make count++ thread-safe.
     *
     * EXAMPLE:
     *
     *     volatile int count = 0;
     *
     *     count++;
     *
     * count++ involves reading, incrementing and writing.
     * Multiple threads can still interfere.
     *
     * Use synchronized, atomic classes or other concurrency
     * mechanisms when atomic updates are required.
     *
     * VOLATILE VS SYNCHRONIZED:
     *
     * volatile:
     * - Primarily provides visibility and ordering.
     * - Does not provide mutual exclusion.
     * - Does not make compound operations atomic.
     *
     * synchronized:
     * - Provides monitor-based mutual exclusion.
     * - Provides memory-visibility and ordering guarantees.
     * - Protects critical sections when used consistently.
     *
     *
     * ================================================================
     * 10. TRANSIENT MODIFIER
     * ================================================================
     *
     * The transient modifier is used with instance fields to indicate
     * that a field should be skipped by the default serialization
     * mechanism.
     *
     * EXAMPLE:
     *
     *     class User implements java.io.Serializable {
     *
     *         String username;
     *
     *         transient String password;
     *     }
     *
     * During default Java serialization, the password field is not
     * serialized as part of the object's default field data.
     *
     * IMPORTANT:
     *
     * 1. transient applies to instance fields.
     * 2. It is relevant to Java's default serialization mechanism.
     * 3. Static fields are not serialized as ordinary instance state
     *    regardless of transient.
     * 4. A transient field receives its default value during
     *    ordinary deserialization unless restored separately.
     * 5. Custom serialization methods can explicitly handle
     *    transient fields.
     *
     * transient is not a complete security mechanism for protecting
     * sensitive information.
     *
     *
     * ================================================================
     * 11. NATIVE MODIFIER
     * ================================================================
     *
     * The native modifier indicates that a method is implemented
     * using native code outside the Java language, commonly through
     * the Java Native Interface (JNI).
     *
     * EXAMPLE:
     *
     *     public class NativeDemo {
     *
     *         public native void display();
     *     }
     *
     * A native method declaration has no Java method body.
     *
     * IMPORTANT:
     *
     * 1. Native methods are implemented outside Java source code.
     * 2. JNI is a common mechanism for connecting Java with native
     *    libraries.
     * 3. Native methods are not abstract merely because they have
     *    no Java body.
     * 4. A native method cannot be abstract.
     * 5. Native methods cannot be declared strictfp.
     *
     * A native implementation must be made available at runtime
     * through the appropriate native library mechanism.
     *
     *
     * ================================================================
     * 12. STRICTFP MODIFIER
     * ================================================================
     *
     * strictfp historically enforced strict floating-point evaluation
     * rules for the applicable class, interface or method.
     *
     * Before Java 17, floating-point evaluation could differ in
     * certain situations due to value-set rules and implementation
     * behaviour.
     *
     * Since Java 17, floating-point expressions are always evaluated
     * strictly under the modern language rules.
     *
     * Therefore, strictfp has no additional effect in Java 17 and
     * later.
     *
     * EXAMPLE:
     *
     *     strictfp class Calculator {
     *
     *         double calculate(double a, double b) {
     *             return a / b;
     *         }
     *     }
     *
     * VERSION NOTE:
     *
     * Java 17 made strict floating-point evaluation the default.
     * The strictfp modifier is redundant and may generate a compiler
     * warning in modern Java versions.
     *
     *
     * ================================================================
     * 13. SEALED AND NON-SEALED CLASSES
     * ================================================================
     *
     * Sealed classes and interfaces were finalized in Java 17.
     *
     * They control which classes are permitted to extend or implement
     * a particular type.
     *
     * A. SEALED CLASS
     *
     * A sealed class restricts its permitted direct subclasses.
     *
     * EXAMPLE:
     *
     *     public sealed class Shape
     *             permits Circle, Rectangle {
     *     }
     *
     *     final class Circle extends Shape {
     *     }
     *
     *     non-sealed class Rectangle extends Shape {
     *     }
     *
     * The permits clause lists the permitted direct subclasses when
     * they are not inferred under the applicable rules.
     *
     *
     * B. NON-SEALED CLASS
     *
     * A non-sealed class removes the sealed restriction for its
     * descendants.
     *
     * EXAMPLE:
     *
     *     non-sealed class Rectangle extends Shape {
     *     }
     *
     * Other classes may extend Rectangle, subject to normal rules.
     *
     *
     * C. FINAL SUBCLASS
     *
     * A permitted subclass may be declared final.
     *
     *     final class Circle extends Shape {
     *     }
     *
     * This prevents further subclassing.
     *
     * PERMITTED DIRECT SUBCLASSES MUST:
     *
     * 1. Be declared final, sealed or non-sealed.
     * 2. Satisfy the applicable package or module restrictions.
     * 3. Be direct subclasses or direct implementors listed or
     *    inferred under the language rules.
     *
     * SEALED HIERARCHY:
     *
     *     sealed
     *        |
     *        +---- final
     *        |
     *        +---- sealed
     *        |
     *        +---- non-sealed
     *
     *
     * ================================================================
     * 14. DEFAULT METHODS IN INTERFACES
     * ================================================================
     *
     * The default keyword can declare a method with an implementation
     * inside an interface.
     *
     * Introduced in Java 8.
     *
     * EXAMPLE:
     *
     *     interface Vehicle {
     *
     *         default void start() {
     *             System.out.println("Vehicle started");
     *         }
     *     }
     *
     *     class Car implements Vehicle {
     *     }
     *
     *     Car car = new Car();
     *     car.start();
     *
     * IMPORTANT:
     *
     * 1. A default interface method has an implementation.
     * 2. Implementing classes inherit it unless they override it
     *    or another applicable rule determines the method.
     * 3. Default methods are instance methods, not static methods.
     * 4. They cannot be declared private abstract methods.
     *
     * Since Java 9, interfaces can also declare private methods
     * to share implementation code among their methods.
     *
     * INTERFACE METHOD MODIFIERS:
     *
     * - Ordinary abstract interface methods are implicitly public
     *   and abstract when declared without a body.
     * - Default methods are public instance methods.
     * - Static interface methods are public unless another permitted
     *   access modifier is explicitly specified under the language
     *   rules.
     * - Private interface methods are supported from Java 9.
     *
     *
     * ================================================================
     * 15. MODIFIERS IN INTERFACES
     * ================================================================
     *
     * Interface fields are implicitly:
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
     * Interface fields must be initialized and represent constants.
     *
     * TOP-LEVEL INTERFACES:
     *
     * A top-level interface can be public or package-private.
     *
     * NESTED INTERFACES:
     *
     * Nested interfaces are implicitly static.
     *
     * ABSTRACT INTERFACES:
     *
     * Interfaces are implicitly abstract. Writing abstract explicitly
     * is redundant.
     *
     * SEALED INTERFACES:
     *
     * Interfaces can also be sealed, non-sealed or final? 
     *
     * IMPORTANT CORRECTION:
     *
     * Interfaces cannot be final. A sealed interface can restrict
     * its direct permitted subinterfaces and implementing classes.
     *
     *
     * ================================================================
     * 16. MODIFIERS IN CONSTRUCTORS
     * ================================================================
     *
     * Constructors can use access modifiers:
     *
     *     public
     *     protected
     *     package-private
     *     private
     *
     * Constructors cannot be:
     *
     *     static
     *     final
     *     abstract
     *     synchronized
     *     native
     *
     * A. PRIVATE CONSTRUCTOR
     *
     * Restricts construction from unrelated classes.
     *
     * EXAMPLE:
     *
     *     class Singleton {
     *
     *         private static final Singleton INSTANCE =
     *                 new Singleton();
     *
     *         private Singleton() {
     *         }
     *
     *         public static Singleton getInstance() {
     *             return INSTANCE;
     *         }
     *     }
     *
     * B. PROTECTED CONSTRUCTOR
     *
     * Can be accessed within the same package and by subclasses
     * under constructor access rules.
     *
     * C. PACKAGE-PRIVATE CONSTRUCTOR
     *
     * Can be accessed by eligible classes in the same package.
     *
     * D. PUBLIC CONSTRUCTOR
     *
     * Can be accessed wherever the declaring class is accessible
     * and module rules permit it.
     *
     *
     * ================================================================
     * 17. MODIFIER COMBINATIONS AND RESTRICTIONS
     * ================================================================
     *
     * IMPORTANT COMBINATIONS:
     *
     * 1. public final class:
     *    A publicly accessible class that cannot be subclassed.
     *
     * 2. public abstract class:
     *    A publicly accessible class that cannot be instantiated
     *    directly and is intended for extension.
     *
     * 3. private static method:
     *    A class-level method accessible only within its private
     *    access scope.
     *
     * 4. protected final method:
     *    A protected method that subclasses cannot override.
     *
     * 5. static final variable:
     *    A class-level variable that can be assigned only once.
     *
     * 6. public static final variable:
     *    Commonly used to declare constants.
     *
     * INVALID OR RESTRICTED COMBINATIONS:
     *
     * 1. abstract final class:
     *    Invalid because abstract classes are designed for extension
     *    and final classes cannot be extended.
     *
     * 2. abstract final method:
     *    Invalid because abstract methods require implementation
     *    through overriding, while final methods cannot be overridden.
     *
     * 3. abstract private method:
     *    Invalid because private methods are not inherited for
     *    overriding.
     *
     * 4. abstract static method:
     *    Invalid because static methods are not overridden.
     *
     * 5. abstract synchronized method:
     *    Invalid because an abstract method has no implementation
     *    on which to perform method-level synchronization.
     *
     * 6. final static method:
     *    Legal, but final is redundant for preventing overriding
     *    because static methods are hidden rather than overridden.
     *
     * 7. private static method:
     *    Legal.
     *
     * 8. private final method:
     *    Legal, although final is redundant because a private method
     *    cannot be overridden.
     *
     * 9. native abstract method:
     *    Invalid because native methods declare external
     *    implementations, whereas abstract methods have no
     *    implementation.
     *
     * 10. synchronized static method:
     *     Legal. It locks the declaring class's Class object.
     *
     * 11. volatile method:
     *     Invalid. volatile applies to fields.
     *
     * 12. transient method:
     *     Invalid. transient applies to fields.
     *
     *
     * ================================================================
     * 18. METHOD OVERRIDING AND MODIFIERS
     * ================================================================
     *
     * Method modifiers affect inheritance and overriding.
     *
     * A. FINAL METHODS
     *
     * Cannot be overridden.
     *
     * B. STATIC METHODS
     *
     * Are hidden when a subclass declares a compatible static method
     * with the same signature. This is not runtime polymorphic
     * overriding.
     *
     * C. PRIVATE METHODS
     *
     * Are not inherited in the ordinary overriding sense.
     * A same-signature method in a subclass is a separate method.
     *
     * D. ABSTRACT METHODS
     *
     * Must be implemented by a concrete subclass unless an
     * implementation is inherited.
     *
     * E. ACCESSIBILITY
     *
     * An overriding method cannot reduce the accessibility of the
     * inherited method.
     *
     * EXAMPLE:
     *
     *     class Parent {
     *
     *         protected void display() {
     *         }
     *     }
     *
     *     class Child extends Parent {
     *
     *         @Override
     *         public void display() {
     *         }
     *     }
     *
     * This is valid because public is more accessible than protected.
     *
     * The reverse is invalid:
     *
     *     class Parent {
     *
     *         public void display() {
     *         }
     *     }
     *
     *     class Child extends Parent {
     *
     *         // protected void display() { }
     *         // Compilation error: reduced accessibility.
     *     }
     *
     *
     * ================================================================
     * 19. FINAL VS FINALLY VS FINALIZE
     * ================================================================
     *
     * These three terms are different.
     *
     * A. final
     *
     * A Java keyword used as a modifier for variables, methods
     * and classes.
     *
     * B. finally
     *
     * A block used with try/catch to execute cleanup code after
     * the try/catch processing, subject to termination conditions.
     *
     * EXAMPLE:
     *
     *     try {
     *         // Risky operation
     *     } finally {
     *         // Cleanup
     *     }
     *
     * C. finalize()
     *
     * A method historically associated with garbage collection.
     *
     * It is deprecated for removal and should not be used for
     * resource management.
     *
     * Use try-with-resources and explicit cleanup instead.
     *
     *
     * ================================================================
     * 20. STATIC VS INSTANCE
     * ================================================================
     *
     * STATIC MEMBER:
     *
     * - Associated with the class.
     * - Can be accessed through the class name when accessible.
     * - Does not require an instance for access.
     * - Static methods cannot directly use this or super.
     *
     * INSTANCE MEMBER:
     *
     * - Associated with an object.
     * - Each object has its own instance fields.
     * - Instance methods can access both instance and static members.
     * - Requires an object context for ordinary instance access.
     *
     * IMPORTANT:
     *
     * Static fields are not necessarily constants.
     * A static field can be modified unless additional restrictions
     * such as final are applied.
     *
     *
     * ================================================================
     * 21. FINAL VS VOLATILE
     * ================================================================
     *
     * FINAL:
     *
     * - Restricts reassignment of a variable.
     * - Restricts overriding of a method.
     * - Restricts subclassing of a class.
     *
     * VOLATILE:
     *
     * - Applies to fields.
     * - Provides visibility and ordering guarantees across threads.
     * - Does not prevent reassignment.
     * - Does not make compound operations atomic.
     *
     * A field can be both final and volatile?
     *
     * No. A field cannot be both final and volatile because their
     * variable semantics conflict under Java's modifier rules.
     *
     *
     * ================================================================
     * 22. COMMON MISTAKES
     * ================================================================
     *
     * 1. Assuming default is an access modifier keyword.
     *    Package-private access is represented by the absence
     *    of an access modifier.
     *
     * 2. Assuming protected means accessible everywhere in subclasses.
     *    Cross-package protected access has qualifying-reference rules.
     *
     * 3. Assuming final makes an object immutable.
     *    It prevents reassignment of the reference, not necessarily
     *    mutation of the referenced object.
     *
     * 4. Assuming static methods are overridden.
     *    They are hidden.
     *
     * 5. Assuming static methods can access instance fields directly.
     *    They need an object reference.
     *
     * 6. Assuming volatile makes count++ atomic.
     *    It does not.
     *
     * 7. Assuming abstract classes cannot have constructors.
     *    They can.
     *
     * 8. Assuming every abstract class must contain an abstract method.
     *    It does not.
     *
     * 9. Assuming private methods can be overridden.
     *    They are not inherited in the ordinary overriding sense.
     *
     * 10. Assuming final methods cannot be overloaded.
     *     They can be overloaded.
     *
     * 11. Assuming constructors can be static or final.
     *     They cannot.
     *
     * 12. Assuming interfaces can be instantiated.
     *     They cannot be instantiated directly.
     *
     * 13. Assuming a final class can be abstract.
     *     It cannot.
     *
     * 14. Assuming synchronized applies to fields.
     *     It applies to methods and synchronized blocks.
     *
     * 15. Assuming transient prevents all forms of serialization.
     *     It affects default serialization; custom serialization
     *     can handle fields separately.
     *
     * 16. Assuming strictfp is still needed for strict floating-point
     *     behaviour in modern Java.
     *     Java 17 and later use always-strict floating-point semantics.
     *
     *
     * ================================================================
     * 23. INTERVIEW QUESTIONS AND QUICK ANSWERS
     * ================================================================
     *
     * Q1. What are modifiers in Java?
     *
     * Keywords that control accessibility and characteristics
     * of Java declarations.
     *
     * Q2. What are the four access levels?
     *
     * public, protected, package-private and private.
     *
     * Q3. What is the difference between private and protected?
     *
     * private is restricted to its private-access scope.
     * protected permits same-package access and qualified subclass
     * access across packages.
     *
     * Q4. Can a top-level class be private?
     *
     * No.
     *
     * Q5. Can a constructor be private?
     *
     * Yes. It is commonly used in singleton and factory patterns.
     *
     * Q6. Can a constructor be static?
     *
     * No.
     *
     * Q7. Can a static method access an instance variable directly?
     *
     * No. It requires an object reference.
     *
     * Q8. Can a static method be overridden?
     *
     * No. Static methods are hidden.
     *
     * Q9. Can a final method be overloaded?
     *
     * Yes.
     *
     * Q10. Can a final class be inherited?
     *
     * No.
     *
     * Q11. Can an abstract class have a constructor?
     *
     * Yes.
     *
     * Q12. Can an abstract class have no abstract methods?
     *
     * Yes.
     *
     * Q13. Can an abstract method be private?
     *
     * No.
     *
     * Q14. Can an interface contain implemented methods?
     *
     * Yes. It can contain default, static and private methods
     * under the applicable Java version rules.
     *
     * Q15. What does synchronized do?
     *
     * It provides monitor-based mutual exclusion and memory
     * visibility guarantees.
     *
     * Q16. Does volatile guarantee atomicity?
     *
     * No. It provides visibility and ordering guarantees.
     *
     * Q17. What is transient?
     *
     * A field modifier that excludes a field from default
     * Java serialization.
     *
     * Q18. What is native?
     *
     * A method modifier indicating implementation outside Java,
     * commonly through JNI.
     *
     * Q19. What is strictfp?
     *
     * A floating-point modifier that became redundant from Java 17.
     *
     * Q20. What are sealed classes?
     *
     * Classes that restrict which types may directly extend them.
     *
     * Q21. Can a method be both static and final?
     *
     * Yes, although final is redundant for preventing overriding
     * because static methods are hidden.
     *
     * Q22. Can a field be both final and volatile?
     *
     * No.
     *
     * Q23. Can an overriding method have greater accessibility?
     *
     * Yes. It cannot have reduced accessibility.
     *
     *
     * ================================================================
     * 24. FINAL REVISION CHECKLIST
     * ================================================================
     *
     * ACCESS MODIFIERS:
     *
     * [ ] public
     * [ ] private
     * [ ] protected
     * [ ] package-private
     * [ ] Accessibility matrix
     * [ ] Protected cross-package rules
     * [ ] Top-level vs nested class access
     *
     * NON-ACCESS MODIFIERS:
     *
     * [ ] static
     * [ ] final
     * [ ] abstract
     * [ ] synchronized
     * [ ] volatile
     * [ ] transient
     * [ ] native
     * [ ] strictfp
     * [ ] sealed
     * [ ] non-sealed
     * [ ] Interface default methods
     *
     * ADVANCED RULES:
     *
     * [ ] Static vs instance
     * [ ] Final variable, method and class
     * [ ] Abstract class and method
     * [ ] Synchronization and monitors
     * [ ] Volatile and Java Memory Model
     * [ ] Default serialization and transient
     * [ ] Native methods and JNI
     * [ ] Java 17 strict floating-point changes
     * [ ] Sealed class hierarchy
     * [ ] Interface modifier rules
     * [ ] Constructor restrictions
     * [ ] Modifier combinations
     * [ ] Overriding and method hiding
     * [ ] Common mistakes and interview questions
     *
     *
     * ================================================================
     * END OF JAVA MODIFIERS
     * ================================================================
     */

    public static void main(String[] args) {
        System.out.print("The above is all about Modifiers , Happy Learning ...!");
    }
}
