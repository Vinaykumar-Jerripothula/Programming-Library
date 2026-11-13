
public class Interfaces {
    

    /*
     * ============================================================
     *                           INTERFACES
     * ============================================================
     *
     * 1. INTRODUCTION TO INTERFACES
     *
     * An interface in Java is a reference type that defines a
     * contract that implementing classes agree to follow.
     *
     * It specifies what behavior a type exposes without
     * necessarily specifying how that behavior is implemented.
     *
     * An interface can contain:
     * - Abstract methods.
     * - Default methods.
     * - Static methods.
     * - Private methods.
     * - Constants.
     * - Nested types.
     *
     * Interfaces are primarily used for:
     * - Abstraction.
     * - Runtime polymorphism.
     * - Loose coupling.
     * - Multiple inheritance of type.
     * - Defining common contracts.
     * - Designing extensible APIs.
     *
     * Example:
     *
     *      interface Animal {
     *          void sound();
     *      }
     *
     *      class Dog implements Animal {
     *          @Override
     *          public void sound() {
     *              System.out.println("Dog barks");
     *          }
     *      }
     *
     *      Animal animal = new Dog();
     *      animal.sound();
     *
     * Output:
     *      Dog barks
     *
     * The Animal interface defines the contract.
     * Dog provides its implementation.
     *
     *
     * 2. WHY DO WE NEED INTERFACES?
     *
     * Consider a payment application supporting multiple
     * payment methods.
     *
     * Without an interface:
     *
     *      class UpiPayment {
     *          void pay(double amount) {}
     *      }
     *
     *      class CardPayment {
     *          void pay(double amount) {}
     *      }
     *
     *      class PaymentService {
     *          void processUpi(UpiPayment payment) {}
     *          void processCard(CardPayment payment) {}
     *      }
     *
     * PaymentService needs separate methods for each type.
     * Adding a new payment method may require changing it.
     *
     * With an interface:
     *
     *      interface Payment {
     *          void pay(double amount);
     *      }
     *
     *      class UpiPayment implements Payment {
     *          public void pay(double amount) {}
     *      }
     *
     *      class CardPayment implements Payment {
     *          public void pay(double amount) {}
     *      }
     *
     *      class PaymentService {
     *          void process(Payment payment) {
     *              payment.pay(1000);
     *          }
     *      }
     *
     * PaymentService depends on the Payment abstraction.
     * Any compatible implementation can be supplied.
     *
     * Benefits:
     * - Reduced dependency on concrete classes.
     * - Easier extension.
     * - Easier unit testing.
     * - Better separation of responsibilities.
     * - Supports dependency injection.
     *
     *
     * 3. DECLARING AN INTERFACE
     *
     * Syntax:
     *
     *      interface InterfaceName {
     *          // Constants
     *          // Abstract methods
     *          // Default methods
     *          // Static methods
     *      }
     *
     * Example:
     *
     *      interface Vehicle {
     *          void start();
     *          void stop();
     *      }
     *
     * An interface is declared using the interface keyword.
     *
     * An interface cannot be instantiated directly.
     *
     * INVALID:
     *
     *      Vehicle vehicle = new Vehicle();
     *
     * An interface reference can hold an object of a class
     * that implements that interface.
     *
     *      Vehicle vehicle = new Car();
     *
     *
     * 4. IMPLEMENTING AN INTERFACE
     *
     * A class uses the implements keyword to implement
     * an interface.
     *
     * Example:
     *
     *      interface Vehicle {
     *          void start();
     *          void stop();
     *      }
     *
     *      class Car implements Vehicle {
     *          @Override
     *          public void start() {
     *              System.out.println("Car started");
     *          }
     *
     *          @Override
     *          public void stop() {
     *              System.out.println("Car stopped");
     *          }
     *      }
     *
     * IMPORTANT RULES:
     *
     * 1. A concrete implementing class must implement all
     *    inherited abstract methods unless it inherits a
     *    suitable concrete implementation.
     *
     * 2. An abstract class can implement an interface without
     *    implementing every abstract method.
     *
     * 3. Interface methods declared as public abstract must
     *    be implemented with public visibility.
     *
     * 4. An implementation cannot reduce the visibility
     *    of an interface method.
     *
     * 5. The @Override annotation is recommended.
     *
     *
     * 5. INTERFACE METHODS: IMPLICIT MODIFIERS
     *
     * An ordinary interface method declared without a body
     * is implicitly:
     *
     *      public abstract
     *
     * Example:
     *
     *      interface Animal {
     *          void sound();
     *      }
     *
     * Equivalent declaration:
     *
     *      interface Animal {
     *          public abstract void sound();
     *      }
     *
     * Therefore:
     *
     *      class Dog implements Animal {
     *          void sound() {}
     *      }
     *
     * This causes a compilation error because the method
     * has weaker package-private visibility.
     *
     * Correct:
     *
     *      class Dog implements Animal {
     *          public void sound() {}
     *      }
     *
     * Interface abstract methods cannot be declared private,
     * protected, or package-private.
     *
     * Modern interfaces also support default, static,
     * and private methods, which have different rules.
     *
     *
     * 6. INTERFACE VARIABLES / CONSTANTS
     *
     * Every field declared in an interface is implicitly:
     *
     *      public static final
     *
     * Example:
     *
     *      interface Config {
     *          int MAX_USERS = 100;
     *      }
     *
     * Equivalent:
     *
     *      interface Config {
     *          public static final int MAX_USERS = 100;
     *      }
     *
     * Access:
     *
     *      System.out.println(Config.MAX_USERS);
     *
     * IMPORTANT:
     *
     * - Interface fields are constants.
     * - They belong to the interface, not implementing objects.
     * - They must be initialized when declared.
     * - They cannot be reassigned.
     * - They are public and accessible through the interface.
     *
     * INVALID:
     *
     *      interface Config {
     *          int MAX_USERS;
     *      }
     *
     *      // Compilation error: constant not initialized.
     *
     * INVALID:
     *
     *      Config.MAX_USERS = 200;
     *
     * Interface fields are static, so they are not inherited
     * as instance state.
     *
     * A class can declare a field with the same name, but
     * that field is a separate declaration.
     *
     * Avoid using interfaces as containers for unrelated
     * global constants. Prefer suitable classes or enums
     * when they better represent the domain.
     *
     *
     * 7. INTERFACE VS CLASS VARIABLES
     *
     * Class instance variable:
     *
     *      class Student {
     *          int age;
     *      }
     *
     * Each Student object has its own age field.
     *
     * Interface field:
     *
     *      interface StudentConfig {
     *          int MAX_AGE = 100;
     *      }
     *
     * MAX_AGE is a shared constant associated with the
     * interface.
     *
     * It is not an instance variable.
     *
     *
     * 8. MULTIPLE INTERFACE IMPLEMENTATION
     *
     * A Java class can implement multiple interfaces.
     *
     * Syntax:
     *
     *      class ClassName implements Interface1, Interface2 {
     *      }
     *
     * Example:
     *
     *      interface Printable {
     *          void print();
     *      }
     *
     *      interface Scannable {
     *          void scan();
     *      }
     *
     *      class MultiFunctionPrinter
     *              implements Printable, Scannable {
     *
     *          public void print() {
     *              System.out.println("Printing");
     *          }
     *
     *          public void scan() {
     *              System.out.println("Scanning");
     *          }
     *      }
     *
     * A class can implement multiple interfaces because
     * Java allows multiple inheritance of type through
     * interfaces.
     *
     * Java does not allow a class to extend multiple classes.
     *
     * Example:
     *
     *      class C extends A, B {} // Invalid
     *
     * But:
     *
     *      class C implements A, B {} // Valid if A and B
     *                                  // are interfaces
     *
     *
     * 9. INTERFACE INHERITANCE
     *
     * An interface can extend another interface using
     * the extends keyword.
     *
     * Example:
     *
     *      interface Animal {
     *          void eat();
     *      }
     *
     *      interface Pet extends Animal {
     *          void play();
     *      }
     *
     *      class Dog implements Pet {
     *          public void eat() {}
     *          public void play() {}
     *      }
     *
     * Pet inherits the contract of Animal.
     *
     * A class implementing Pet must satisfy the inherited
     * abstract method requirements as well.
     *
     * IMPORTANT:
     * An interface extends another interface.
     * A class implements an interface.
     *
     * INVALID:
     *
     *      interface B implements A {}
     *
     * CORRECT:
     *
     *      interface B extends A {}
     *
     *
     * 10. MULTIPLE INTERFACE INHERITANCE
     *
     * An interface can extend multiple interfaces.
     *
     * Example:
     *
     *      interface Printable {
     *          void print();
     *      }
     *
     *      interface Scannable {
     *          void scan();
     *      }
     *
     *      interface MultiFunction
     *              extends Printable, Scannable {
     *          void fax();
     *      }
     *
     *      class Machine implements MultiFunction {
     *          public void print() {}
     *          public void scan() {}
     *          public void fax() {}
     *      }
     *
     * This supports multiple inheritance of type and
     * inherited interface contracts.
     *
     *
     * 11. CLASS EXTENDING A CLASS AND IMPLEMENTING INTERFACES
     *
     * A class can extend one class and implement multiple
     * interfaces at the same time.
     *
     * Syntax:
     *
     *      class Child extends Parent
     *              implements Interface1, Interface2 {
     *      }
     *
     * Example:
     *
     *      class Machine {}
     *
     *      interface Printable {
     *          void print();
     *      }
     *
     *      interface Scannable {
     *          void scan();
     *      }
     *
     *      class Printer extends Machine
     *              implements Printable, Scannable {
     *
     *          public void print() {}
     *          public void scan() {}
     *      }
     *
     * The extends clause must appear before implements.
     *
     *
     * 12. INTERFACE REFERENCES AND POLYMORPHISM
     *
     * An interface reference can point to any object whose
     * class implements that interface.
     *
     * Example:
     *
     *      interface Payment {
     *          void pay();
     *      }
     *
     *      class UpiPayment implements Payment {
     *          public void pay() {
     *              System.out.println("UPI payment");
     *          }
     *      }
     *
     *      class CardPayment implements Payment {
     *          public void pay() {
     *              System.out.println("Card payment");
     *          }
     *      }
     *
     *      Payment p = new UpiPayment();
     *      p.pay();
     *
     *      p = new CardPayment();
     *      p.pay();
     *
     * Output:
     *      UPI payment
     *      Card payment
     *
     * The reference type is Payment.
     * The actual object can be UpiPayment or CardPayment.
     *
     * The implementation is selected through runtime
     * dispatch for the overridden instance method.
     *
     *
     * 13. DEFAULT METHODS
     *
     * Default methods were introduced in Java 8.
     *
     * They allow an interface to provide an instance method
     * implementation using the default keyword.
     *
     * Syntax:
     *
     *      interface InterfaceName {
     *          default void methodName() {
     *              // Implementation
     *          }
     *      }
     *
     * Example:
     *
     *      interface Vehicle {
     *          void start();
     *
     *          default void horn() {
     *              System.out.println("Vehicle horn");
     *          }
     *      }
     *
     *      class Car implements Vehicle {
     *          public void start() {
     *              System.out.println("Car started");
     *          }
     *      }
     *
     *      Car car = new Car();
     *      car.start();
     *      car.horn();
     *
     * Car inherits the default horn() implementation.
     *
     * A class can override a default method.
     *
     *      class Bike implements Vehicle {
     *          public void start() {}
     *
     *          @Override
     *          public void horn() {
     *              System.out.println("Bike horn");
     *          }
     *      }
     *
     * WHY DEFAULT METHODS?
     *
     * Before Java 8, adding a new abstract method to an
     * existing widely implemented interface could break
     * existing implementations.
     *
     * Default methods allow interfaces to evolve by
     * supplying a default implementation.
     *
     * Default methods are instance methods, not static methods.
     *
     *
     * 14. STATIC METHODS IN INTERFACES
     *
     * Static interface methods were introduced in Java 8.
     *
     * They belong to the interface itself.
     *
     * Example:
     *
     *      interface MathUtil {
     *          static int add(int a, int b) {
     *              return a + b;
     *          }
     *      }
     *
     *      int result = MathUtil.add(10, 20);
     *
     * A static interface method is called using the
     * interface name.
     *
     * It is not inherited as an ordinary instance method
     * by implementing classes.
     *
     * Example:
     *
     *      class Calculator implements MathUtil {}
     *
     *      MathUtil.add(10, 20);       // Valid
     *      // Calculator.add(10, 20); // Invalid
     *
     * Static interface methods are not overridden.
     *
     * A class can declare its own static method with the
     * same signature, but that is a separate declaration.
     *
     *
     * 15. PRIVATE METHODS IN INTERFACES
     *
     * Private interface methods were introduced in Java 9.
     *
     * They allow code reuse among methods inside an interface.
     *
     * Example:
     *
     *      interface Logger {
     *
     *          default void info(String message) {
     *              log("INFO", message);
     *          }
     *
     *          default void error(String message) {
     *              log("ERROR", message);
     *          }
     *
     *          private void log(String level, String message) {
     *              System.out.println(
     *                  "[" + level + "] " + message
     *              );
     *          }
     *      }
     *
     * Private methods:
     * - Have method bodies.
     * - Are used internally by the interface.
     * - Cannot be accessed by implementing classes.
     * - Cannot be overridden.
     * - Cannot be abstract.
     *
     * Private static methods are also allowed.
     *
     * Example:
     *
     *      interface Utility {
     *
     *          static int add(int a, int b) {
     *              return validate(a, b);
     *          }
     *
     *          private static int validate(int a, int b) {
     *              return a + b;
     *          }
     *      }
     *
     *
     * 16. INTERFACE METHOD TYPES
     *
     * An interface can contain different kinds of methods.
     *
     * ABSTRACT METHOD:
     *      void display();
     *
     * DEFAULT METHOD:
     *      default void displayInfo() {}
     *
     * STATIC METHOD:
     *      static void utility() {}
     *
     * PRIVATE INSTANCE METHOD:
     *      private void helper() {}
     *
     * PRIVATE STATIC METHOD:
     *      private static void helper() {}
     *
     * Their differences:
     *
     * Abstract methods:
     * - Define behavior to be supplied by implementations.
     * - Implicitly public abstract when declared normally.
     *
     * Default methods:
     * - Provide inherited instance behavior.
     * - Can be overridden.
     *
     * Static methods:
     * - Belong to the interface.
     * - Called using the interface name.
     * - Are not overridden.
     *
     * Private methods:
     * - Support internal code reuse.
     * - Cannot be accessed by implementing classes.
     *
     *
     * 17. DEFAULT METHOD CONFLICTS
     *
     * A conflict can occur when a class inherits two
     * unrelated default methods with the same signature.
     *
     * Example:
     *
     *      interface A {
     *          default void show() {
     *              System.out.println("A");
     *          }
     *      }
     *
     *      interface B {
     *          default void show() {
     *              System.out.println("B");
     *          }
     *      }
     *
     *      class C implements A, B {
     *          // Compilation error without resolution.
     *      }
     *
     * The class must override the conflicting method.
     *
     *      class C implements A, B {
     *          @Override
     *          public void show() {
     *              System.out.println("C");
     *          }
     *      }
     *
     * It can explicitly call one interface's default method:
     *
     *      class C implements A, B {
     *          @Override
     *          public void show() {
     *              A.super.show();
     *          }
     *      }
     *
     * Syntax:
     *
     *      InterfaceName.super.methodName();
     *
     * This syntax is used to invoke an inherited default
     * implementation from a direct superinterface.
     *
     *
     * 18. METHOD RESOLUTION RULES
     *
     * Java has rules for resolving inherited methods.
     *
     * IMPORTANT GENERAL RULES:
     *
     * 1. A concrete method from a superclass takes precedence
     *    over an interface default method with the same
     *    compatible signature.
     *
     * 2. A more specific interface's default method takes
     *    precedence over a less specific superinterface's
     *    default method.
     *
     * 3. If unrelated interfaces provide conflicting defaults,
     *    the implementing class must resolve the conflict.
     *
     * 4. A class's own method can provide the implementation.
     *
     * Example: class method wins.
     *
     *      class Parent {
     *          public void show() {
     *              System.out.println("Parent");
     *          }
     *      }
     *
     *      interface A {
     *          default void show() {
     *              System.out.println("A");
     *          }
     *      }
     *
     *      class Child extends Parent implements A {}
     *
     *      new Child().show();
     *
     * Output:
     *      Parent
     *
     * The inherited concrete class method wins over
     * the interface default method.
     *
     * Example: more specific interface wins.
     *
     *      interface A {
     *          default void show() {
     *              System.out.println("A");
     *          }
     *      }
     *
     *      interface B extends A {
     *          default void show() {
     *              System.out.println("B");
     *          }
     *      }
     *
     *      class C implements A, B {}
     *
     *      new C().show();
     *
     * Output:
     *      B
     *
     * B is more specific than A.
     *
     *
     * 19. FUNCTIONAL INTERFACES
     *
     * A functional interface is an interface with exactly
     * one abstract method, known as its functional method.
     *
     * It can contain:
     * - One abstract method.
     * - Any number of default methods.
     * - Any number of static methods.
     * - Private methods.
     *
     * Methods corresponding to public methods of Object
     * do not count as additional distinct abstract methods
     * for functional-interface determination.
     *
     * Example:
     *
     *      @FunctionalInterface
     *      interface Calculator {
     *          int calculate(int a, int b);
     *      }
     *
     * Functional interfaces are the foundation of
     * lambda expressions and method references.
     *
     *
     * 20. @FUNCTIONALINTERFACE ANNOTATION
     *
     * @FunctionalInterface is an annotation used to declare
     * that an interface is intended to be functional.
     *
     * Example:
     *
     *      @FunctionalInterface
     *      interface Greeting {
     *          void sayHello();
     *      }
     *
     * The compiler verifies that the interface satisfies
     * the functional-interface rules.
     *
     * If incompatible additional abstract methods are added,
     * compilation fails.
     *
     * The annotation is not mandatory for an interface
     * to be a functional interface.
     *
     * It is recommended because it makes the intention clear
     * and provides compiler validation.
     *
     *
     * 21. LAMBDA EXPRESSIONS WITH FUNCTIONAL INTERFACES
     *
     * Lambda expressions were introduced in Java 8.
     *
     * A lambda expression provides an implementation of
     * a functional interface's abstract method.
     *
     * Traditional implementation:
     *
     *      Calculator calculator = new Calculator() {
     *          public int calculate(int a, int b) {
     *              return a + b;
     *          }
     *      };
     *
     * Using lambda:
     *
     *      Calculator calculator = (a, b) -> a + b;
     *
     * Calling:
     *
     *      System.out.println(calculator.calculate(10, 20));
     *
     * Output:
     *      30
     *
     * The lambda is compatible with the target functional
     * interface type.
     *
     * A lambda expression is not simply an anonymous class
     * written with shorter syntax. Its runtime implementation
     * is different and is commonly based on invokedynamic.
     *
     *
     * 22. BUILT-IN FUNCTIONAL INTERFACES
     *
     * Java provides commonly used functional interfaces
     * in java.util.function.
     *
     * IMPORTANT INTERFACES:
     *
     * A. Predicate<T>
     *
     *      boolean test(T value)
     *
     * Used to test a condition.
     *
     * Example:
     *
     *      Predicate<Integer> isEven = n -> n % 2 == 0;
     *
     *      isEven.test(10); // true
     *
     *
     * B. Function<T, R>
     *
     *      R apply(T value)
     *
     * Accepts a value and returns a result.
     *
     * Example:
     *
     *      Function<String, Integer> length =
     *          str -> str.length();
     *
     *      length.apply("Java"); // 4
     *
     *
     * C. Consumer<T>
     *
     *      void accept(T value)
     *
     * Accepts a value and performs an operation without
     * returning a result.
     *
     * Example:
     *
     *      Consumer<String> print =
     *          str -> System.out.println(str);
     *
     *
     * D. Supplier<T>
     *
     *      T get()
     *
     * Supplies a value without accepting an input.
     *
     * Example:
     *
     *      Supplier<Double> random =
     *          () -> Math.random();
     *
     *
     * E. UnaryOperator<T>
     *
     *      T apply(T value)
     *
     * Accepts and returns the same type.
     *
     * It is a specialization of Function<T, T>.
     *
     * Example:
     *
     *      UnaryOperator<Integer> square = n -> n * n;
     *
     *
     * F. BinaryOperator<T>
     *
     *      T apply(T first, T second)
     *
     * Accepts two values of the same type and returns
     * a value of that same type.
     *
     * Example:
     *
     *      BinaryOperator<Integer> sum = (a, b) -> a + b;
     *
     *
     * G. BiPredicate<T, U>
     *
     *      boolean test(T first, U second)
     *
     *
     * H. BiFunction<T, U, R>
     *
     *      R apply(T first, U second)
     *
     *
     * I. BiConsumer<T, U>
     *
     *      void accept(T first, U second)
     *
     *
     * 23. PRIMITIVE SPECIALIZATIONS
     *
     * Java provides primitive-specialized functional interfaces
     * to reduce boxing and unboxing.
     *
     * Examples:
     *
     * IntPredicate
     * LongPredicate
     * DoublePredicate
     *
     * IntFunction<R>
     * LongFunction<R>
     * DoubleFunction<R>
     *
     * ToIntFunction<T>
     * ToLongFunction<T>
     * ToDoubleFunction<T>
     *
     * IntConsumer
     * LongConsumer
     * DoubleConsumer
     *
     * IntSupplier
     * LongSupplier
     * DoubleSupplier
     *
     * IntUnaryOperator
     * LongUnaryOperator
     * DoubleUnaryOperator
     *
     * IntBinaryOperator
     * LongBinaryOperator
     * DoubleBinaryOperator
     *
     * These are useful in performance-sensitive operations
     * involving primitive values.
     *
     *
     * 24. METHOD REFERENCES
     *
     * A method reference is a compact way to refer to
     * an existing method or constructor.
     *
     * It is used where a functional interface is expected.
     *
     * Four common forms:
     *
     * 1. Reference to a static method.
     * 2. Reference to an instance method of a particular object.
     * 3. Reference to an instance method of an arbitrary
     *    object of a particular type.
     * 4. Reference to a constructor.
     *
     * STATIC METHOD:
     *
     *      Function<String, Integer> parser =
     *          Integer::parseInt;
     *
     * PARTICULAR OBJECT INSTANCE METHOD:
     *
     *      Consumer<String> printer =
     *          System.out::println;
     *
     * ARBITRARY OBJECT INSTANCE METHOD:
     *
     *      Function<String, String> upper =
     *          String::toUpperCase;
     *
     * CONSTRUCTOR REFERENCE:
     *
     *      Supplier<ArrayList<String>> creator =
     *          ArrayList::new;
     *
     * The method reference must be compatible with the
     * target functional interface's method signature.
     *
     *
     * 25. ANONYMOUS INNER CLASSES AND INTERFACES
     *
     * An anonymous class is a class without a declared name
     * that is instantiated at the point of use.
     *
     * It can implement an interface or extend a class.
     *
     * Example:
     *
     *      Runnable task = new Runnable() {
     *          @Override
     *          public void run() {
     *              System.out.println("Running");
     *          }
     *      };
     *
     * The anonymous class provides the implementation
     * of Runnable.run().
     *
     * Lambda alternative:
     *
     *      Runnable task = () ->
     *          System.out.println("Running");
     *
     * Lambdas are available only when a functional interface
     * is the target type.
     *
     * Anonymous classes can have additional fields,
     * initializer blocks, and multiple methods.
     *
     * A lambda does not declare a new class body in the
     * same way as an anonymous class.
     *
     *
     * 26. MARKER INTERFACES
     *
     * A marker interface is an interface that declares
     * no abstract methods and is used to mark a class
     * as having a particular property or capability.
     *
     * Examples from the Java platform:
     *
     * Serializable
     * Cloneable
     * Remote
     *
     * Example:
     *
     *      class Student implements Serializable {
     *      }
     *
     * Serializable marks objects of the class as eligible
     * for Java's serialization mechanism, subject to
     * serialization rules.
     *
     * Cloneable indicates that Object.clone() may perform
     * a field-by-field copy rather than throwing
     * CloneNotSupportedException.
     *
     * Implementing Cloneable does not automatically provide
     * a public clone method.
     *
     * Marker interfaces can be detected using instanceof
     * or reflection.
     *
     *
     * 27. TAGGING VS FUNCTIONAL INTERFACES
     *
     * Marker interface:
     * - Has no functional method.
     * - Represents a marker or capability.
     *
     * Functional interface:
     * - Has one functional abstract method.
     * - Can be used as a lambda target type.
     *
     * Example marker:
     *
     *      interface Auditable {}
     *
     * Example functional:
     *
     *      @FunctionalInterface
     *      interface Action {
     *          void execute();
     *      }
     *
     * An interface with no abstract methods is not a
     * functional interface.
     *
     *
     * 28. NESTED INTERFACES
     *
     * An interface can be declared inside another class
     * or interface.
     *
     * Example:
     *
     *      class Outer {
     *          interface Inner {
     *              void display();
     *          }
     *      }
     *
     * Implementation:
     *
     *      class Demo implements Outer.Inner {
     *          public void display() {}
     *      }
     *
     * A member interface is implicitly static.
     *
     * It does not require an instance of the enclosing
     * class to be referenced.
     *
     * An interface can also declare nested types.
     *
     *
     * 29. INTERFACES AND GENERICS
     *
     * Interfaces can declare type parameters.
     *
     * Example:
     *
     *      interface Repository<T> {
     *          void save(T value);
     *          T findById(int id);
     *      }
     *
     * Implementation:
     *
     *      class StudentRepository
     *              implements Repository<String> {
     *
     *          public void save(String value) {}
     *
     *          public String findById(int id) {
     *              return "Student";
     *          }
     *      }
     *
     * A generic interface can be implemented using:
     *
     * 1. A concrete type argument.
     * 2. A type parameter of the implementing class.
     * 3. A raw type, which is generally discouraged.
     *
     * Example with a generic implementing class:
     *
     *      class RepositoryImpl<T> implements Repository<T> {
     *          public void save(T value) {}
     *
     *          public T findById(int id) {
     *              return null;
     *          }
     *      }
     *
     *
     * 30. GENERIC INTERFACES WITH BOUNDED TYPES
     *
     * A generic interface can restrict its type parameter.
     *
     * Example:
     *
     *      interface NumberProcessor<T extends Number> {
     *          double process(T value);
     *      }
     *
     * Valid type arguments include:
     *
     *      Integer
     *      Double
     *      Long
     *
     * Invalid type argument:
     *
     *      String
     *
     * because String does not extend Number.
     *
     * Multiple bounds can be declared:
     *
     *      interface Processor<
     *          T extends Number & Comparable<T>> {
     *          void process(T value);
     *      }
     *
     * When using a class bound and interface bounds,
     * the class bound must appear first.
     *
     *
     * 31. GENERIC INTERFACES AND WILDCARDS
     *
     * Generic types are invariant.
     *
     * If Dog extends Animal:
     *
     *      Repository<Dog>
     *
     * is not a subtype of:
     *
     *      Repository<Animal>
     *
     * Use bounded wildcards when a flexible API is required.
     *
     * Upper-bounded wildcard:
     *
     *      Repository<? extends Animal>
     *
     * Lower-bounded wildcard:
     *
     *      Repository<? super Dog>
     *
     * PECS guideline:
     *
     *      Producer Extends
     *      Consumer Super
     *
     * Use extends when the structure primarily produces
     * values for reading.
     *
     * Use super when the structure primarily consumes
     * values for writing.
     *
     *
     * 32. INTERFACE AND ABSTRACT CLASS
     *
     * Both interfaces and abstract classes support abstraction.
     *
     * INTERFACE:
     * - A class can implement multiple interfaces.
     * - Cannot have constructors.
     * - Cannot have ordinary instance fields.
     * - Fields are public static final constants.
     * - Supports abstract, default, static, and private methods.
     * - Represents a contract or capability.
     *
     * ABSTRACT CLASS:
     * - A class can extend only one class.
     * - Can have constructors.
     * - Can have instance fields and state.
     * - Can have abstract and concrete methods.
     * - Can have different access modifiers.
     * - Can provide shared state and implementation.
     *
     * Use an interface when:
     * - Defining a capability or contract.
     * - Unrelated classes need a common type.
     * - Multiple implementations should be interchangeable.
     *
     * Use an abstract class when:
     * - Related classes share state.
     * - Constructors or protected members are needed.
     * - Common implementation belongs in a base class.
     *
     * Modern Java interfaces can contain behavior through
     * default and static methods, so the difference is not
     * simply "interfaces contain no implementation".
     *
     *
     * 33. INTERFACE VS ABSTRACT CLASS: EXAMPLE
     *
     *      interface Flyable {
     *          void fly();
     *      }
     *
     *      abstract class Bird {
     *          String name;
     *
     *          Bird(String name) {
     *              this.name = name;
     *          }
     *
     *          abstract void eat();
     *
     *          void sleep() {
     *              System.out.println("Sleeping");
     *          }
     *      }
     *
     *      class Eagle extends Bird implements Flyable {
     *
     *          Eagle(String name) {
     *              super(name);
     *          }
     *
     *          public void fly() {
     *              System.out.println("Flying");
     *          }
     *
     *          void eat() {
     *              System.out.println("Eating");
     *          }
     *      }
     *
     * Eagle inherits state and behavior from Bird
     * and implements the Flyable capability.
     *
     *
     * 34. INTERFACES AND DEPENDENCY INJECTION
     *
     * Dependency injection means providing an object's
     * dependencies from outside rather than making it
     * construct concrete dependencies internally.
     *
     * Example:
     *
     *      interface MessageSender {
     *          void send(String message);
     *      }
     *
     *      class EmailSender implements MessageSender {
     *          public void send(String message) {}
     *      }
     *
     *      class SmsSender implements MessageSender {
     *          public void send(String message) {}
     *      }
     *
     *      class NotificationService {
     *          private final MessageSender sender;
     *
     *          NotificationService(MessageSender sender) {
     *              this.sender = sender;
     *          }
     *
     *          void notifyUser(String message) {
     *              sender.send(message);
     *          }
     *      }
     *
     * Usage:
     *
     *      NotificationService service =
     *          new NotificationService(new EmailSender());
     *
     * The service depends on MessageSender rather than
     * EmailSender.
     *
     * A different implementation can be injected without
     * modifying NotificationService.
     *
     * This is commonly used in Spring applications.
     *
     *
     * 35. INTERFACES AND LOOSE COUPLING
     *
     * Loose coupling means reducing direct dependencies
     * between software components.
     *
     * Tightly coupled:
     *
     *      class OrderService {
     *          private final MySqlRepository repository =
     *              new MySqlRepository();
     *      }
     *
     * OrderService depends directly on MySqlRepository.
     *
     * More loosely coupled:
     *
     *      interface OrderRepository {
     *          void save();
     *      }
     *
     *      class MySqlRepository implements OrderRepository {
     *          public void save() {}
     *      }
     *
     *      class OrderService {
     *          private final OrderRepository repository;
     *
     *          OrderService(OrderRepository repository) {
     *              this.repository = repository;
     *          }
     *      }
     *
     * The service can work with different repository
     * implementations.
     *
     *
     * 36. INTERFACES AND COLLECTION FRAMEWORK
     *
     * The Java Collections Framework uses interfaces
     * extensively.
     *
     * Important interfaces:
     *
     * Collection
     * List
     * Set
     * Queue
     * Deque
     * Map
     * SortedSet
     * NavigableSet
     * SortedMap
     * NavigableMap
     *
     * Examples:
     *
     *      List<String> names = new ArrayList<>();
     *
     *      Set<Integer> numbers = new HashSet<>();
     *
     *      Map<Integer, String> students = new HashMap<>();
     *
     * The reference uses an interface while the object
     * uses a concrete implementation.
     *
     * Benefits:
     * - Implementation can be changed easily.
     * - APIs depend on abstractions.
     * - Code becomes more flexible.
     *
     *
     * 37. COMPARABLE AND COMPARATOR
     *
     * Comparable and Comparator are important interfaces
     * used for ordering objects.
     *
     * Comparable:
     *
     *      public interface Comparable<T> {
     *          int compareTo(T other);
     *      }
     *
     * A class implements Comparable to define its natural
     * ordering.
     *
     * Comparator:
     *
     *      public interface Comparator<T> {
     *          int compare(T first, T second);
     *      }
     *
     * Comparator defines an external comparison strategy.
     *
     * Example:
     *
     *      class Student implements Comparable<Student> {
     *          int marks;
     *
     *          public int compareTo(Student other) {
     *              return Integer.compare(marks, other.marks);
     *          }
     *      }
     *
     * Comparator example:
     *
     *      Comparator<Student> byMarks =
     *          (a, b) -> Integer.compare(a.marks, b.marks);
     *
     * Comparable:
     * - Natural ordering.
     * - Implemented by the class being compared.
     * - One primary natural ordering is generally provided.
     *
     * Comparator:
     * - External ordering strategy.
     * - Multiple strategies can be defined.
     * - Does not require modifying the compared class.
     *
     *
     * 38. CLONEABLE AND SERIALIZABLE
     *
     * Cloneable and Serializable are marker interfaces.
     *
     * Cloneable:
     * - Located in java.lang.
     * - Marks support for Object.clone()'s field-copy behavior.
     * - Does not declare a clone() method.
     *
     * Serializable:
     * - Located in java.io.
     * - Marks a class as eligible for Java serialization.
     * - Does not declare serialization methods.
     *
     * Serialization still has additional requirements and
     * behavior, including serialVersionUID and transient fields.
     *
     * These interfaces are examples of marker interfaces
     * used by the Java platform.
     *
     *
     * 39. SEALED INTERFACES
     *
     * Sealed classes and interfaces became final in Java 17.
     *
     * A sealed interface restricts which classes or
     * interfaces may directly extend or implement it.
     *
     * Example:
     *
     *      public sealed interface Shape
     *              permits Circle, Rectangle {
     *      }
     *
     *      final class Circle implements Shape {}
     *
     *      final class Rectangle implements Shape {}
     *
     * Permitted direct subtypes must declare one of:
     *
     *      final
     *      sealed
     *      non-sealed
     *
     * Example:
     *
     *      public sealed interface Payment
     *              permits CardPayment, OtherPayment {
     *      }
     *
     *      final class CardPayment implements Payment {}
     *
     *      non-sealed class OtherPayment implements Payment {}
     *
     * A non-sealed subtype allows further unrestricted
     * inheritance from that point.
     *
     * Sealed types are useful when a domain has a controlled
     * set of permitted implementations.
     *
     *
     * 40. RECORDS IMPLEMENTING INTERFACES
     *
     * Records became a standard feature in Java 16.
     *
     * A record can implement one or more interfaces.
     *
     * Example:
     *
     *      interface Identifiable {
     *          long id();
     *      }
     *
     *      record User(long id, String name)
     *              implements Identifiable {
     *      }
     *
     * The record component id automatically provides
     * an accessor method named id().
     *
     * Records cannot extend arbitrary classes because
     * they implicitly extend java.lang.Record.
     *
     * They can implement interfaces.
     *
     *
     * 41. ENUMS IMPLEMENTING INTERFACES
     *
     * An enum can implement one or more interfaces.
     *
     * Example:
     *
     *      interface Describable {
     *          String description();
     *      }
     *
     *      enum Status implements Describable {
     *          ACTIVE,
     *          INACTIVE;
     *
     *          public String description() {
     *              return name().toLowerCase();
     *          }
     *      }
     *
     * Each enum constant is an instance of the enum type,
     * and the enum implements the declared interface.
     *
     *
     * 42. INTERFACE AND MODULE SYSTEM
     *
     * Java Platform Module System was introduced in Java 9.
     *
     * Interfaces can be declared in packages belonging
     * to named modules.
     *
     * A module can export a package so other modules
     * can access its public types.
     *
     * Example module-info.java:
     *
     *      module com.example.api {
     *          exports com.example.api;
     *      }
     *
     * An interface declared public in an exported package
     * can be accessible to other modules that read the module.
     *
     * Public visibility alone does not guarantee that a
     * type in a named module is accessible to every other
     * module; module readability and exports also matter.
     *
     *
     * 43. INTERFACE AND API DESIGN
     *
     * A good interface should:
     *
     * - Represent a clear contract.
     * - Expose only necessary operations.
     * - Avoid unrelated responsibilities.
     * - Use meaningful method names.
     * - Avoid unnecessary constants.
     * - Prefer stable abstractions.
     * - Document method behavior and exceptions.
     * - Avoid exposing implementation details.
     *
     * Interface design affects every implementing class.
     * Changes to an interface can affect its implementations.
     *
     * Adding an abstract method to an interface can break
     * existing concrete implementations.
     *
     * Default methods can help evolve interfaces, but
     * they must be designed carefully to avoid conflicts
     * and unexpected behavior.
     *
     *
     * 44. INTERFACE SEGREGATION PRINCIPLE
     *
     * Interface Segregation Principle is one of the SOLID
     * design principles.
     *
     * It states that clients should not be forced to depend
     * on methods they do not use.
     *
     * BAD DESIGN:
     *
     *      interface Machine {
     *          void print();
     *          void scan();
     *          void fax();
     *      }
     *
     * A basic printer may not support scanning or faxing.
     *
     * BETTER DESIGN:
     *
     *      interface Printer {
     *          void print();
     *      }
     *
     *      interface Scanner {
     *          void scan();
     *      }
     *
     *      interface Fax {
     *          void fax();
     *      }
     *
     * A multifunction machine can implement all three.
     * A basic printer only implements Printer.
     *
     * Smaller interfaces often produce clearer contracts.
     *
     *
     * 45. INTERFACE AND OPEN/CLOSED PRINCIPLE
     *
     * Open/Closed Principle:
     * Software entities should be open for extension but
     * closed for modification.
     *
     * Interfaces help support this principle by allowing
     * new implementations to be introduced without changing
     * the code that depends on the interface.
     *
     * Example:
     *
     *      interface Discount {
     *          double apply(double amount);
     *      }
     *
     *      class FestivalDiscount implements Discount {
     *          public double apply(double amount) {
     *              return amount * 0.90;
     *          }
     *      }
     *
     *      class StudentDiscount implements Discount {
     *          public double apply(double amount) {
     *              return amount * 0.80;
     *          }
     *      }
     *
     * A service can accept Discount and work with new
     * implementations without changing its core logic.
     *
     *
     * 46. COMMON INTERFACE ERRORS
     *
     * ERROR 1: Forgetting public visibility.
     *
     *      interface A {
     *          void show();
     *      }
     *
     *      class B implements A {
     *          void show() {}
     *      }
     *
     * Error:
     * The implementation is weaker than public.
     *
     * ERROR 2: Trying to instantiate an interface.
     *
     *      A obj = new A();
     *
     * Error:
     * Interfaces cannot be instantiated directly.
     *
     * ERROR 3: Using implements instead of extends
     * for interface inheritance.
     *
     *      interface B implements A {}
     *
     * Error:
     * An interface extends another interface.
     *
     * ERROR 4: Trying to change an interface constant.
     *
     *      A.VALUE = 100;
     *
     * Error:
     * Interface fields are final.
     *
     * ERROR 5: Calling a static interface method through
     * an implementing class.
     *
     *      ImplementingClass.utility();
     *
     * Error if utility() is only declared as a static
     * method in the interface.
     *
     * ERROR 6: Conflicting default methods.
     *
     * Error:
     * A class must resolve unrelated inherited defaults.
     *
     * ERROR 7: Trying to override a private interface method.
     *
     * Error:
     * Private interface methods are not inherited.
     *
     *
     * 47. INTERFACE VERSION HISTORY
     *
     * Java 1.0:
     * - Interfaces introduced as a core language feature.
     * - Abstract methods and constants.
     *
     * Java 5:
     * - Generics introduced.
     * - Generic interfaces became possible.
     *
     * Java 8:
     * - Default methods introduced.
     * - Static interface methods introduced.
     * - Functional interfaces and lambda expressions.
     * - Method references.
     *
     * Java 9:
     * - Private interface methods introduced.
     * - Private static interface methods supported.
     * - Java Platform Module System introduced.
     *
     * Java 16:
     * - Records became a standard feature.
     * - Records can implement interfaces.
     *
     * Java 17:
     * - Sealed interfaces became a standard feature.
     *
     * Java 21:
     * - Pattern matching for switch became a standard feature.
     * - Sealed hierarchies can work with exhaustive switch
     *   analysis in suitable cases.
     *
     * Java 25:
     * - No new general-purpose interface method category
     *   is introduced in this overview.
     *
     * When learning version-specific behavior, verify the
     * language rules for the target Java release.
     *
     *
     * 48. INTERVIEW QUESTIONS AND ANSWERS
     *
     * Q1. What is an interface?
     * A. An interface is a reference type that defines a
     *    contract that implementing types agree to follow.
     *
     * Q2. Why do we use interfaces?
     * A. To define contracts, support abstraction, enable
     *    polymorphism, and reduce coupling.
     *
     * Q3. Can we create an object of an interface?
     * A. No. But an interface reference can refer to an
     *    object of an implementing class.
     *
     * Q4. Can an interface have variables?
     * A. Yes. They are implicitly public static final.
     *
     * Q5. Can an interface have a constructor?
     * A. No.
     *
     * Q6. Can an interface have a main method?
     * A. Yes. An interface can declare a static main method
     *    that can serve as an application entry point.
     *
     * Q7. Can an interface extend a class?
     * A. No. An interface can extend other interfaces.
     *
     * Q8. Can an interface extend multiple interfaces?
     * A. Yes.
     *
     * Q9. Can a class implement multiple interfaces?
     * A. Yes.
     *
     * Q10. Can an abstract class implement an interface?
     * A. Yes. It may leave abstract methods unimplemented.
     *
     * Q11. Can an interface contain implemented methods?
     * A. Yes. It can contain default, static, and private
     *     methods.
     *
     * Q12. What is a default method?
     * A. An instance method with a body declared using
     *    the default keyword.
     *
     * Q13. When were default methods introduced?
     * A. Java 8.
     *
     * Q14. Can a static interface method be overridden?
     * A. No.
     *
     * Q15. Can an implementing class access a private
     *     interface method?
     * A. No.
     *
     * Q16. When were private interface methods introduced?
     * A. Java 9.
     *
     * Q17. What happens when two interfaces contain the
     *     same default method?
     * A. The implementing class must resolve the conflict
     *    by overriding the method.
     *
     * Q18. Which takes precedence: a superclass method
     *     or an interface default method?
     * A. An applicable concrete superclass method takes
     *    precedence.
     *
     * Q19. What is a functional interface?
     * A. An interface with exactly one functional abstract
     *    method, subject to Java's functional-interface rules.
     *
     * Q20. Is @FunctionalInterface mandatory?
     * A. No. It is optional but recommended.
     *
     * Q21. Can a functional interface contain default methods?
     * A. Yes.
     *
     * Q22. Can a functional interface contain static methods?
     * A. Yes.
     *
     * Q23. Can a functional interface contain private methods?
     * A. Yes, in Java 9 and later.
     *
     * Q24. What is a marker interface?
     * A. An interface with no functional abstract method
     *    that marks a class with a particular property
     *    or capability.
     *
     * Q25. Give examples of marker interfaces.
     * A. Serializable and Cloneable.
     *
     * Q26. What is the difference between Comparable
     *     and Comparator?
     * A. Comparable defines natural ordering inside the
     *    class; Comparator provides an external ordering
     *    strategy.
     *
     * Q27. Can an interface be generic?
     * A. Yes.
     *
     * Q28. Can an enum implement an interface?
     * A. Yes.
     *
     * Q29. Can a record implement an interface?
     * A. Yes.
     *
     * Q30. What is a sealed interface?
     * A. An interface that restricts its permitted direct
     *    subtypes.
     *
     * Q31. Can an interface contain a nested interface?
     * A. Yes.
     *
     * Q32. Are nested interfaces static?
     * A. Member interfaces are implicitly static.
     *
     * Q33. Can an interface have protected methods?
     * A. No. Ordinary interface methods cannot be protected.
     *
     * Q34. Can an interface have private methods?
     * A. Yes, since Java 9.
     *
     * Q35. Can an interface have final abstract methods?
     * A. No. An abstract method cannot be final.
     *
     *
     * 49. FINAL REVISION CHECKLIST
     *
     * FUNDAMENTALS
     * [ ] Interface meaning and purpose.
     * [ ] Interface declaration syntax.
     * [ ] Implementing an interface.
     * [ ] Interface reference and object type.
     * [ ] Implicit method modifiers.
     * [ ] Interface constants.
     *
     * INHERITANCE
     * [ ] Interface extends interface.
     * [ ] Multiple interface inheritance.
     * [ ] Class implements multiple interfaces.
     * [ ] Class extends a class and implements interfaces.
     *
     * INTERFACE METHODS
     * [ ] Abstract methods.
     * [ ] Default methods.
     * [ ] Static methods.
     * [ ] Private instance methods.
     * [ ] Private static methods.
     * [ ] Default method conflict resolution.
     * [ ] Class method precedence.
     *
     * FUNCTIONAL PROGRAMMING
     * [ ] Functional interfaces.
     * [ ] @FunctionalInterface.
     * [ ] Lambda expressions.
     * [ ] Method references.
     * [ ] Anonymous classes.
     * [ ] Predicate, Function, Consumer, Supplier.
     * [ ] Primitive-specialized functional interfaces.
     *
     * ADVANCED
     * [ ] Marker interfaces.
     * [ ] Nested interfaces.
     * [ ] Generic interfaces.
     * [ ] Bounded type parameters.
     * [ ] Wildcards and PECS.
     * [ ] Comparable and Comparator.
     * [ ] Cloneable and Serializable.
     * [ ] Sealed interfaces.
     * [ ] Records and enums implementing interfaces.
     * [ ] Java module system and exported packages.
     *
     * DESIGN
     * [ ] Interface vs abstract class.
     * [ ] Loose coupling.
     * [ ] Dependency injection.
     * [ ] Interface Segregation Principle.
     * [ ] Open/Closed Principle.
     * [ ] Interface API design.
     *
     * ============================================================
     *                    END OF INTERFACES
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Interfaces. Happy Learning ..!");
    }

}
