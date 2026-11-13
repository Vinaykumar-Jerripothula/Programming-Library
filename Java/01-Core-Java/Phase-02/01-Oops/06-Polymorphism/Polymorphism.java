
public class Polymorphism {
    

    /*
     * ============================================================
     *                         POLYMORPHISM
     * ============================================================
     *
     * 1. INTRODUCTION
     *
     * Polymorphism means "many forms".
     *
     * In Java, polymorphism allows the same method name, reference,
     * or operation to represent different behaviors depending on
     * the context.
     *
     * Example:
     *
     *      Animal animal = new Dog();
     *      animal.sound();
     *
     * The reference type is Animal, but the actual object is Dog.
     * If Dog overrides sound(), Dog's implementation executes.
     *
     * The same method call can therefore produce different
     * behavior for different actual object types.
     *
     * Main advantages:
     * - Flexible and extensible code.
     * - Reduced coupling between components.
     * - Code reuse through common parent types and interfaces.
     * - Easier implementation of interchangeable behaviors.
     * - Supports the Open/Closed Principle.
     *
     *
     * 2. TYPES OF POLYMORPHISM IN JAVA
     *
     * Java commonly demonstrates two forms:
     *
     * A. Compile-time polymorphism
     * B. Runtime polymorphism
     *
     * COMPILE-TIME POLYMORPHISM:
     * - Commonly achieved through method overloading.
     * - The compiler selects the method during compilation.
     * - Selection depends on method signatures and argument types.
     * - Also called static polymorphism or early binding.
     *
     * RUNTIME POLYMORPHISM:
     * - Commonly achieved through method overriding.
     * - An overridden instance method is selected at runtime.
     * - Selection depends on the actual object's class.
     * - Also called dynamic polymorphism or late binding.
     *
     * Important:
     * These names describe when method selection occurs.
     * They do not mean that every Java method call is dynamically
     * dispatched.
     *
     *
     * 3. METHOD OVERLOADING
     *
     * Method overloading means defining multiple methods with
     * the same name but different parameter lists in one class
     * or across inherited methods.
     *
     * Different parameter lists can have:
     * - Different number of parameters.
     * - Different parameter data types.
     * - Different parameter order.
     *
     * Example:
     *
     *      class Calculator {
     *          int add(int a, int b) {
     *              return a + b;
     *          }
     *
     *          double add(double a, double b) {
     *              return a + b;
     *          }
     *
     *          int add(int a, int b, int c) {
     *              return a + b + c;
     *          }
     *      }
     *
     *      Calculator c = new Calculator();
     *      c.add(10, 20);       // int version
     *      c.add(10.5, 20.5);   // double version
     *      c.add(1, 2, 3);      // three-parameter version
     *
     * The compiler determines which overloaded method to call.
     *
     * IMPORTANT RULES:
     *
     * 1. Return type alone cannot distinguish overloaded methods.
     *
     *      int calculate(int a) { return a; }
     *      double calculate(int a) { return a; }
     *
     *      // Compilation error: duplicate method signature.
     *
     * 2. Changing parameter names does not create overloading.
     *
     *      void display(int a) {}
     *      void display(int value) {}
     *
     *      // Compilation error.
     *
     * 3. Changing parameter order can create overloading.
     *
     *      void show(int a, String b) {}
     *      void show(String a, int b) {}
     *
     * 4. Changing access modifiers alone does not create
     *    overloading.
     *
     * 5. Static methods can be overloaded.
     *
     * 6. Instance methods can be overloaded.
     *
     * 7. Constructors can be overloaded.
     *
     * 8. Overloading can happen in the same class or through
     *    inherited methods.
     *
     * METHOD SIGNATURE:
     * A method signature consists of:
     * - Method name.
     * - Number of formal parameters.
     * - Types of formal parameters.
     * - Order of formal parameter types.
     *
     * Return type, parameter names, and access modifiers are
     * not part of a method signature.
     *
     * Generic method signatures have additional rules involving
     * type parameters and type erasure.
     *
     *
     * 4. OVERLOADING WITH PRIMITIVE TYPES
     *
     * Java may widen a primitive argument to match an overloaded
     * method.
     *
     * Example:
     *
     *      class Demo {
     *          void show(long x) {
     *              System.out.println("long");
     *          }
     *
     *          void show(double x) {
     *              System.out.println("double");
     *          }
     *      }
     *
     *      Demo d = new Demo();
     *      d.show(10);
     *
     * Output:
     *      long
     *
     * An int can widen to long or double.
     * The compiler chooses the more specific applicable method.
     *
     * Primitive widening order examples:
     *
     *      byte -> short -> int -> long -> float -> double
     *
     *      char -> int -> long -> float -> double
     *
     * Note:
     * This is a useful conversion overview, not a complete
     * description of every permitted primitive conversion.
     *
     * Narrowing conversions are not automatically applied
     * during ordinary method invocation.
     *
     * Example:
     *
     *      void show(int x) {}
     *      show(10L);
     *
     * This does not automatically narrow long to int.
     *
     *
     * 5. OVERLOADING WITH REFERENCE TYPES
     *
     * A reference argument can match a method parameter whose
     * type is the same as, or a supertype of, the argument's
     * compile-time type.
     *
     * Example:
     *
     *      class Animal {}
     *      class Dog extends Animal {}
     *
     *      class Demo {
     *          void display(Animal a) {
     *              System.out.println("Animal");
     *          }
     *
     *          void display(Dog d) {
     *              System.out.println("Dog");
     *          }
     *      }
     *
     *      Dog dog = new Dog();
     *      Demo demo = new Demo();
     *      demo.display(dog);
     *
     * Output:
     *      Dog
     *
     * The Dog overload is more specific than the Animal overload.
     *
     * IMPORTANT:
     * Overload selection uses the compile-time type of the
     * argument expression, not its runtime object class.
     *
     * Example:
     *
     *      Animal animal = new Dog();
     *      demo.display(animal);
     *
     * Output:
     *      Animal
     *
     * Although the actual object is Dog, the expression's
     * compile-time type is Animal.
     *
     *
     * 6. OVERLOADING WITH NULL
     *
     * null can be passed to reference-type parameters.
     *
     * Example:
     *
     *      class Demo {
     *          void show(Object obj) {
     *              System.out.println("Object");
     *          }
     *
     *          void show(String str) {
     *              System.out.println("String");
     *          }
     *      }
     *
     *      Demo d = new Demo();
     *      d.show(null);
     *
     * Output:
     *      String
     *
     * String is more specific than Object.
     *
     * AMBIGUOUS NULL:
     *
     *      void display(String s) {}
     *      void display(Integer i) {}
     *
     *      display(null);
     *
     * Compilation error because String and Integer are
     * unrelated reference types and neither is more specific.
     *
     * If one overload parameter type is a subtype of another,
     * the subtype overload is generally more specific.
     *
     * Primitive parameters cannot directly receive null.
     *
     *
     * 7. OVERLOADING AND AUTOBOXING
     *
     * Java supports boxing and unboxing conversions.
     *
     * Boxing:
     *      int -> Integer
     *
     * Unboxing:
     *      Integer -> int
     *
     * Example:
     *
     *      class Demo {
     *          void show(int x) {
     *              System.out.println("primitive");
     *          }
     *
     *          void show(Integer x) {
     *              System.out.println("wrapper");
     *          }
     *      }
     *
     *      Demo d = new Demo();
     *      d.show(10);
     *
     * Output:
     *      primitive
     *
     * The exact primitive match is preferred over boxing.
     *
     * IMPORTANT:
     * For ordinary method invocation, Java generally considers
     * applicable methods in phases:
     *
     * Phase 1:
     * Strict invocation: identity and permitted widening
     * conversions, without boxing or variable arity.
     *
     * Phase 2:
     * Loose invocation: includes boxing and unboxing.
     *
     * Phase 3:
     * Variable-arity invocation.
     *
     * The compiler selects the most specific method in the
     * earliest phase containing applicable methods.
     *
     * This explains why widening can be preferred over boxing.
     *
     * Example:
     *
     *      void show(long x) {}
     *      void show(Integer x) {}
     *
     *      show(10);
     *
     * The long overload is applicable in the strict phase.
     * The Integer overload requires boxing in a later phase.
     * Therefore, the long overload is selected.
     *
     *
     * 8. OVERLOADING WITH VARARGS
     *
     * Varargs allow a method to accept zero or more arguments
     * of a specified type.
     *
     * Syntax:
     *
     *      void display(int... values) {
     *          for (int value : values) {
     *              System.out.println(value);
     *          }
     *      }
     *
     * Calls:
     *
     *      display();
     *      display(10);
     *      display(10, 20, 30);
     *
     * Internally, varargs are handled as arrays.
     *
     * A varargs parameter must be the last parameter.
     *
     * Only one varargs parameter is allowed in a method.
     *
     * Example:
     *
     *      void show(String name, int... marks) {}
     *
     * INVALID:
     *
     *      void show(int... values, String name) {}
     *
     * Varargs and overloading:
     *
     *      void display(int x) {}
     *      void display(int... x) {}
     *
     *      display(10);  // fixed-arity method is preferred
     *      display();    // varargs method
     *
     * When an applicable fixed-arity method exists, it is
     * generally considered before variable-arity invocation.
     *
     * Be careful when overloading multiple varargs methods:
     *
     *      void show(String... values) {}
     *      void show(Integer... values) {}
     *
     *      show(); // ambiguous
     *
     *
     * 9. CONSTRUCTOR OVERLOADING
     *
     * Constructor overloading means defining multiple
     * constructors with different parameter lists.
     *
     * Example:
     *
     *      class Student {
     *          String name;
     *          int age;
     *
     *          Student() {
     *              this("Unknown", 0);
     *          }
     *
     *          Student(String name) {
     *              this(name, 0);
     *          }
     *
     *          Student(String name, int age) {
     *              this.name = name;
     *              this.age = age;
     *          }
     *      }
     *
     *      Student s1 = new Student();
     *      Student s2 = new Student("Vinay");
     *      Student s3 = new Student("Vinay", 22);
     *
     * Constructor chaining through this() avoids duplicated
     * initialization logic.
     *
     * A constructor is not inherited and cannot be overridden.
     *
     *
     * 10. METHOD OVERRIDING
     *
     * Method overriding occurs when a subclass provides its
     * own implementation of an inherited instance method.
     *
     * The overriding method must satisfy Java's overriding rules.
     *
     * Example:
     *
     *      class Animal {
     *          void sound() {
     *              System.out.println("Animal sound");
     *          }
     *      }
     *
     *      class Dog extends Animal {
     *          @Override
     *          void sound() {
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
     * The subclass implementation replaces the inherited
     * implementation for calls dynamically dispatched on Dog.
     *
     * WHY OVERRIDING?
     * - Allows specialized behavior in subclasses.
     * - Supports runtime polymorphism.
     * - Allows code to work with common parent types.
     * - Enables extensible and interchangeable implementations.
     *
     *
     * 11. RULES OF METHOD OVERRIDING
     *
     * 1. The method must be inherited or otherwise eligible
     *    for overriding.
     *
     * 2. The overriding method must have the same name.
     *
     * 3. Its parameter types must match the overridden method.
     *
     * 4. The return type must be the same or covariant.
     *
     * 5. The overriding method cannot reduce visibility.
     *
     * 6. It cannot throw broader checked exceptions than
     *    permitted by the overridden method.
     *
     * 7. A final method cannot be overridden.
     *
     * 8. A private method is not overridden because it is
     *    not inherited by the subclass.
     *
     * 9. Static methods are hidden, not overridden.
     *
     * 10. Constructors cannot be overridden.
     *
     * 11. The @Override annotation is recommended.
     *
     *
     * 12. @OVERRIDE ANNOTATION
     *
     * @Override tells the compiler that a method is intended
     * to override a superclass method or implement an
     * interface method.
     *
     * Example:
     *
     *      class Animal {
     *          void sound() {}
     *      }
     *
     *      class Dog extends Animal {
     *          @Override
     *          void sound() {}
     *      }
     *
     * If the method does not actually override a compatible
     * method, compilation fails.
     *
     * Example of a typo:
     *
     *      class Dog extends Animal {
     *          @Override
     *          void sounds() {}
     *      }
     *
     * Compilation error if no compatible sounds() method
     * exists in a supertype.
     *
     * @Override improves readability and prevents accidental
     * method declarations caused by spelling mistakes.
     *
     *
     * 13. ACCESS MODIFIERS IN OVERRIDING
     *
     * An overriding method cannot be less accessible than
     * the overridden method.
     *
     * Visibility order:
     *
     *      private < package-private < protected < public
     *
     * Examples:
     *
     *      Parent: protected void show()
     *      Child:  public void show()       // valid
     *
     *      Parent: public void show()
     *      Child:  protected void show()    // invalid
     *
     *      Parent: public void show()
     *      Child:  public void show()       // valid
     *
     * A package-private method has special cross-package
     * inheritance behavior. A same-signature method in a
     * different package does not automatically override a
     * package-private method that is not inherited there.
     *
     * Private methods are not overridden.
     *
     *
     * 14. CHECKED EXCEPTIONS IN OVERRIDING
     *
     * An overriding method cannot declare broader checked
     * exceptions than the overridden method permits.
     *
     * Example:
     *
     *      class Parent {
     *          void read() throws IOException {}
     *      }
     *
     *      class Child extends Parent {
     *          @Override
     *          void read() throws FileNotFoundException {}
     *      }
     *
     * Valid because FileNotFoundException is a subclass
     * of IOException.
     *
     * Also valid:
     *
     *      class Child extends Parent {
     *          @Override
     *          void read() {}
     *      }
     *
     * The overriding method may declare fewer checked
     * exceptions or none.
     *
     * Invalid:
     *
     *      class Child extends Parent {
     *          @Override
     *          void read() throws Exception {}
     *      }
     *
     * Exception is broader than IOException.
     *
     * Unchecked exceptions are not restricted by this rule.
     *
     *
     * 15. COVARIANT RETURN TYPES
     *
     * An overriding method may return a subtype of the
     * return type declared by the overridden method.
     *
     * This is called a covariant return type.
     *
     * Example:
     *
     *      class Animal {}
     *      class Dog extends Animal {}
     *
     *      class Parent {
     *          Animal getObject() {
     *              return new Animal();
     *          }
     *      }
     *
     *      class Child extends Parent {
     *          @Override
     *          Dog getObject() {
     *              return new Dog();
     *          }
     *      }
     *
     * Valid because Dog is a subtype of Animal.
     *
     * Primitive return types do not support covariant returns.
     *
     * Example:
     *
     *      int calculate() { return 10; }
     *      long calculate() { return 10L; }
     *
     * This is not valid overriding merely because int can
     * widen to long.
     *
     *
     * 16. RUNTIME POLYMORPHISM
     *
     * Runtime polymorphism occurs when an overridden instance
     * method is selected based on the actual object's class.
     *
     * Example:
     *
     *      class Animal {
     *          void sound() {
     *              System.out.println("Animal sound");
     *          }
     *      }
     *
     *      class Dog extends Animal {
     *          @Override
     *          void sound() {
     *              System.out.println("Dog barks");
     *          }
     *      }
     *
     *      class Cat extends Animal {
     *          @Override
     *          void sound() {
     *              System.out.println("Cat meows");
     *          }
     *      }
     *
     *      Animal a1 = new Dog();
     *      Animal a2 = new Cat();
     *
     *      a1.sound(); // Dog barks
     *      a2.sound(); // Cat meows
     *
     * The same parent reference type can refer to different
     * subclass objects.
     *
     * The actual object's overridden implementation executes.
     *
     *
     * 17. DYNAMIC METHOD DISPATCH
     *
     * Dynamic method dispatch is the mechanism through which
     * Java selects an overridden instance method at runtime.
     *
     * It is the foundation of runtime polymorphism.
     *
     * Consider:
     *
     *      Animal animal = new Dog();
     *      animal.sound();
     *
     * Step 1:
     * The compiler checks whether sound() is accessible and
     * declared or inherited for the reference type Animal.
     *
     * Step 2:
     * The compiler verifies that the method invocation is valid.
     *
     * Step 3:
     * At runtime, the JVM determines the actual receiver object.
     *
     * Step 4:
     * The applicable overriding implementation is selected.
     *
     * Step 5:
     * Dog.sound() executes.
     *
     * IMPORTANT:
     * The compiler checks method availability using the
     * reference's compile-time type.
     *
     * Runtime dispatch selects the implementation using
     * the actual object's class.
     *
     *
     * 18. REFERENCE TYPE VS OBJECT TYPE
     *
     * Example:
     *
     *      Animal animal = new Dog();
     *
     * Reference type:
     *      Animal
     *
     * Actual object type:
     *      Dog
     *
     * The reference type determines:
     * - Which members are accessible through the reference.
     * - Which overloaded method is selected.
     * - Whether an assignment or cast is permitted.
     * - The compile-time type of a method invocation.
     *
     * The actual object type determines:
     * - Which overridden instance method implementation runs.
     * - The runtime result of instanceof.
     *
     * Example:
     *
     *      class Animal {
     *          void eat() {}
     *      }
     *
     *      class Dog extends Animal {
     *          void bark() {}
     *      }
     *
     *      Animal animal = new Dog();
     *
     *      animal.eat();  // valid
     *      animal.bark(); // compilation error
     *
     * The object is Dog, but the reference type is Animal.
     * bark() is not declared in Animal.
     *
     * A cast is required:
     *
     *      ((Dog) animal).bark();
     *
     * The cast is safe here because the actual object is Dog.
     *
     *
     * 19. OVERLOADING VS OVERRIDING
     *
     * OVERLOADING:
     * - Same method name, different parameter lists.
     * - Usually occurs within one class.
     * - Can also involve inherited methods.
     * - Compile-time method selection.
     * - Return type alone cannot distinguish overloads.
     * - Static methods can be overloaded.
     *
     * OVERRIDING:
     * - Subclass provides a new implementation of an inherited
     *   instance method.
     * - Requires an inheritance or interface implementation
     *   relationship.
     * - Runtime selection for overridden instance methods.
     * - Return type must be same or covariant.
     * - Static methods are hidden, not overridden.
     *
     * Example:
     *
     *      class Parent {
     *          void display(int x) {}
     *          void display(String x) {} // overloading
     *
     *          void show() {}            // may be overridden
     *      }
     *
     *      class Child extends Parent {
     *          @Override
     *          void show() {}            // overriding
     *      }
     *
     *
     * 20. OVERLOADING AND RUNTIME POLYMORPHISM TOGETHER
     *
     * Overloading and overriding can coexist.
     *
     * Example:
     *
     *      class Animal {
     *          void sound() {
     *              System.out.println("Animal");
     *          }
     *
     *          void sound(int times) {
     *              System.out.println("Animal x " + times);
     *          }
     *      }
     *
     *      class Dog extends Animal {
     *          @Override
     *          void sound() {
     *              System.out.println("Dog");
     *          }
     *      }
     *
     *      Animal a = new Dog();
     *
     *      a.sound();      // Dog
     *      a.sound(3);     // Animal x 3
     *
     * The compiler chooses the overloaded signature.
     * Runtime dispatch chooses the implementation of the
     * selected overridable instance method.
     *
     *
     * 21. STATIC METHOD HIDING
     *
     * Static methods belong to classes rather than individual
     * objects.
     *
     * A subclass can declare a static method with the same
     * signature as an accessible static method in its parent.
     *
     * This is called method hiding, not overriding.
     *
     * Example:
     *
     *      class Parent {
     *          static void display() {
     *              System.out.println("Parent");
     *          }
     *      }
     *
     *      class Child extends Parent {
     *          static void display() {
     *              System.out.println("Child");
     *          }
     *      }
     *
     *      Parent p = new Child();
     *      p.display();
     *
     * Output:
     *      Parent
     *
     * The method is selected using the reference's compile-time
     * type, not the actual object type.
     *
     * Calling a static method through an instance reference is
     * allowed, but discouraged.
     *
     * Prefer:
     *
     *      Parent.display();
     *      Child.display();
     *
     * Static methods cannot be overridden.
     *
     *
     * 22. FIELD HIDING
     *
     * Fields are not dynamically dispatched like overridden
     * instance methods.
     *
     * If a subclass declares a field with the same name as
     * an inherited field, it hides that field.
     *
     * Example:
     *
     *      class Parent {
     *          int value = 10;
     *      }
     *
     *      class Child extends Parent {
     *          int value = 20;
     *      }
     *
     *      Parent p = new Child();
     *      System.out.println(p.value);
     *
     * Output:
     *      10
     *
     * The field is selected using the reference type.
     *
     * A Child reference accesses Child.value:
     *
     *      Child c = new Child();
     *      System.out.println(c.value); // 20
     *
     * Fields are hidden, not overridden.
     *
     * IMPORTANT:
     * Runtime polymorphism applies to overridable instance
     * methods, not ordinary field access.
     *
     *
     * 23. PRIVATE METHODS AND POLYMORPHISM
     *
     * Private methods are not inherited by subclasses.
     *
     * Therefore, a same-signature private method in a subclass
     * does not override the private method in the parent.
     *
     * Example:
     *
     *      class Parent {
     *          private void show() {
     *              System.out.println("Parent");
     *          }
     *
     *          void callShow() {
     *              show();
     *          }
     *      }
     *
     *      class Child extends Parent {
     *          private void show() {
     *              System.out.println("Child");
     *          }
     *      }
     *
     *      Parent p = new Child();
     *      p.callShow();
     *
     * Output:
     *      Parent
     *
     * Parent.callShow() invokes the private method belonging
     * to Parent.
     *
     * The Child method is a separate method.
     *
     *
     * 24. FINAL METHODS AND POLYMORPHISM
     *
     * A final instance method cannot be overridden.
     *
     * Example:
     *
     *      class Parent {
     *          final void display() {}
     *      }
     *
     *      class Child extends Parent {
     *          // void display() {} // compilation error
     *      }
     *
     * A final method can still be inherited and called.
     *
     * A final class cannot be subclassed.
     *
     * A final variable cannot be reassigned after initialization.
     *
     * Note:
     * final does not automatically make an object immutable.
     *
     *
     * 25. ABSTRACT CLASSES AND POLYMORPHISM
     *
     * Abstract classes are commonly used to define a common
     * parent type with shared behavior and abstract operations.
     *
     * Example:
     *
     *      abstract class Shape {
     *          abstract double area();
     *      }
     *
     *      class Circle extends Shape {
     *          double radius;
     *
     *          Circle(double radius) {
     *              this.radius = radius;
     *          }
     *
     *          @Override
     *          double area() {
     *              return Math.PI * radius * radius;
     *          }
     *      }
     *
     *      class Rectangle extends Shape {
     *          double width;
     *          double height;
     *
     *          Rectangle(double width, double height) {
     *              this.width = width;
     *              this.height = height;
     *          }
     *
     *          @Override
     *          double area() {
     *              return width * height;
     *          }
     *      }
     *
     *      Shape s1 = new Circle(5);
     *      Shape s2 = new Rectangle(4, 6);
     *
     *      System.out.println(s1.area());
     *      System.out.println(s2.area());
     *
     * The same Shape reference type supports different
     * implementations of area().
     *
     *
     * 26. INTERFACES AND POLYMORPHISM
     *
     * Interfaces are a major way to achieve polymorphism.
     *
     * Different classes can implement the same interface
     * and provide different implementations.
     *
     * Example:
     *
     *      interface Payment {
     *          void pay(double amount);
     *      }
     *
     *      class UpiPayment implements Payment {
     *          @Override
     *          public void pay(double amount) {
     *              System.out.println("UPI: " + amount);
     *          }
     *      }
     *
     *      class CardPayment implements Payment {
     *          @Override
     *          public void pay(double amount) {
     *              System.out.println("Card: " + amount);
     *          }
     *      }
     *
     *      Payment payment = new UpiPayment();
     *      payment.pay(500);
     *
     *      payment = new CardPayment();
     *      payment.pay(1000);
     *
     * The calling code depends on the Payment interface,
     * not on a particular implementation.
     *
     * This makes implementations interchangeable.
     *
     * Interface references can refer to objects of any
     * compatible implementing class.
     *
     *
     * 27. DEFAULT METHODS AND POLYMORPHISM
     *
     * Interfaces can define default instance methods.
     *
     * An implementing class inherits the default method
     * unless it overrides it or a conflict requires resolution.
     *
     * Example:
     *
     *      interface Vehicle {
     *          default void start() {
     *              System.out.println("Vehicle starts");
     *          }
     *      }
     *
     *      class Car implements Vehicle {
     *          @Override
     *          public void start() {
     *              System.out.println("Car starts");
     *          }
     *      }
     *
     *      Vehicle v = new Car();
     *      v.start();
     *
     * Output:
     *      Car starts
     *
     * Default methods participate in instance method dispatch.
     *
     *
     * 28. DEFAULT METHOD CONFLICTS
     *
     * If a class inherits unrelated default methods with the
     * same signature from multiple interfaces, it must resolve
     * the conflict by overriding the method.
     *
     * Example:
     *
     *      interface A {
     *          default void show() {}
     *      }
     *
     *      interface B {
     *          default void show() {}
     *      }
     *
     *      class C implements A, B {
     *          @Override
     *          public void show() {
     *              A.super.show();
     *          }
     *      }
     *
     * The class explicitly chooses an implementation.
     *
     * Method resolution rules also give precedence to
     * applicable class methods over interface default methods.
     *
     *
     * 29. UPCASTING
     *
     * Upcasting means assigning a subclass object reference
     * to a superclass reference.
     *
     * Example:
     *
     *      Dog dog = new Dog();
     *      Animal animal = dog;
     *
     * Upcasting is implicit and generally safe.
     *
     * The object remains a Dog.
     * Only the reference type changes.
     *
     * Benefits:
     * - Common parent-type programming.
     * - Runtime polymorphism.
     * - Reusable methods accepting a parent type.
     *
     * Example:
     *
     *      void makeSound(Animal animal) {
     *          animal.sound();
     *      }
     *
     *      makeSound(new Dog());
     *      makeSound(new Cat());
     *
     * The same method accepts different subclasses.
     *
     *
     * 30. DOWNCASTING
     *
     * Downcasting means converting a superclass reference
     * into a subclass reference.
     *
     * Example:
     *
     *      Animal animal = new Dog();
     *      Dog dog = (Dog) animal;
     *
     * Downcasting is explicit.
     *
     * It is safe only when the actual object is compatible
     * with the target type.
     *
     * Unsafe example:
     *
     *      Animal animal = new Cat();
     *      Dog dog = (Dog) animal;
     *
     * Runtime exception:
     *      ClassCastException
     *
     * The compiler may allow a cast that cannot be proven
     * impossible from the static types, but runtime checks
     * determine whether the actual object is compatible.
     *
     *
     * 31. INSTANCEOF OPERATOR
     *
     * instanceof checks whether an object is compatible with
     * a specified reference type.
     *
     * Example:
     *
     *      Animal animal = new Dog();
     *
     *      if (animal instanceof Dog) {
     *          Dog dog = (Dog) animal;
     *          dog.bark();
     *      }
     *
     * instanceof returns false for null.
     *
     * Example:
     *
     *      Animal animal = null;
     *      System.out.println(animal instanceof Dog);
     *
     * Output:
     *      false
     *
     * The compiler can reject certain casts or instanceof
     * checks when the types are statically incompatible.
     *
     *
     * 32. PATTERN MATCHING FOR INSTANCEOF
     *
     * Pattern matching for instanceof became final in Java 16.
     *
     * It combines the type check and variable declaration.
     *
     * Traditional:
     *
     *      if (obj instanceof String) {
     *          String text = (String) obj;
     *          System.out.println(text.length());
     *      }
     *
     * Pattern matching:
     *
     *      if (obj instanceof String text) {
     *          System.out.println(text.length());
     *      }
     *
     * The pattern variable is available where the compiler
     * can prove the pattern matched.
     *
     * Pattern variables have flow-scoped rules.
     * They are not necessarily available after the if block.
     *
     *
     * 33. POLYMORPHISM WITH ARRAYS
     *
     * Arrays of reference types are covariant in Java.
     *
     * Example:
     *
     *      Dog[] dogs = new Dog[3];
     *      Animal[] animals = dogs;
     *
     * This assignment is allowed because Dog extends Animal.
     *
     * However, storing an incompatible object can cause
     * ArrayStoreException at runtime.
     *
     * Example:
     *
     *      Animal[] animals = new Dog[2];
     *      animals[0] = new Dog();
     *      animals[1] = new Cat();
     *
     * The second assignment throws ArrayStoreException.
     *
     * The runtime array remembers its actual component type.
     *
     * IMPORTANT:
     * Array covariance is different from generic type
     * invariance.
     *
     *
     * 34. POLYMORPHISM WITH GENERICS
     *
     * Generic types in Java are invariant.
     *
     * Example:
     *
     *      List<Dog> dogs = new ArrayList<>();
     *      List<Animal> animals = dogs;
     *
     * This is a compilation error.
     *
     * Even though Dog extends Animal,
     * List<Dog> is not a subtype of List<Animal>.
     *
     * WHY?
     * If the assignment were allowed, a Cat could be added
     * through the List<Animal> reference into a List<Dog>.
     *
     * Use wildcards for flexible generic APIs.
     *
     *      List<? extends Animal>
     *
     * This can refer to a list of some specific subtype
     * of Animal. It is useful for reading values as Animal.
     *
     *      List<? super Dog>
     *
     * This can refer to a list whose element type is Dog
     * or a supertype of Dog. It is useful for adding Dogs.
     *
     * PECS:
     * Producer Extends, Consumer Super.
     *
     * This is a guideline for choosing wildcard bounds.
     *
     *
     * 35. GENERIC METHOD OVERLOADING AND TYPE ERASURE
     *
     * Java implements generics through type erasure.
     *
     * Generic type information is mostly erased during
     * compilation, subject to metadata and runtime checks.
     *
     * This can cause methods that look different in source
     * code to have the same erased signature.
     *
     * Example:
     *
     *      void display(List<String> list) {}
     *      void display(List<Integer> list) {}
     *
     * Compilation error due to the same erasure.
     *
     * Both parameter types erase to List.
     *
     * Generic methods can also be overloaded when their
     * signatures remain distinct after erasure and satisfy
     * Java's method signature rules.
     *
     *
     * 36. BRIDGE METHODS
     *
     * A bridge method is a synthetic method generated by
     * the Java compiler to preserve polymorphism after
     * type erasure.
     *
     * Example:
     *
     *      class Parent<T> {
     *          T get() {
     *              return null;
     *          }
     *      }
     *
     *      class Child extends Parent<String> {
     *          @Override
     *          String get() {
     *              return "Hello";
     *          }
     *      }
     *
     * The source-level return types differ:
     * Parent<T>.get() uses T.
     * Child.get() returns String.
     *
     * After erasure, the parent method's return type is Object.
     * The compiler may generate a synthetic bridge method
     * in Child to preserve the overriding relationship.
     *
     * Bridge methods:
     * - Are compiler-generated.
     * - Support overriding with generic types and covariant
     *   return types after erasure.
     * - Are marked synthetic in class-file metadata.
     * - Are generally not written manually by developers.
     *
     *
     * 37. POLYMORPHISM AND OBJECT CLASS METHODS
     *
     * Every class directly or indirectly extends Object
     * unless it extends another class.
     *
     * Important Object methods include:
     *
     *      toString()
     *      equals(Object obj)
     *      hashCode()
     *      getClass()
     *
     * Subclasses commonly override toString(), equals(),
     * and hashCode().
     *
     * Example:
     *
     *      class Student {
     *          String name;
     *
     *          @Override
     *          public String toString() {
     *              return "Student: " + name;
     *          }
     *      }
     *
     * Calling toString() through an Object reference can
     * execute the Student implementation.
     *
     *      Object obj = new Student();
     *      System.out.println(obj.toString());
     *
     * getClass() is final and cannot be overridden.
     *
     *
     * 38. EQUALS METHOD AND OVERLOADING TRAP
     *
     * Object declares:
     *
     *      public boolean equals(Object obj)
     *
     * A common mistake is to overload equals instead of
     * overriding it.
     *
     * Incorrect:
     *
     *      class Student {
     *          boolean equals(Student other) {
     *              return true;
     *          }
     *      }
     *
     * This overloads equals; it does not override
     * Object.equals(Object).
     *
     * Correct signature:
     *
     *      @Override
     *      public boolean equals(Object obj) {
     *          // Implement equality logic.
     *          return super.equals(obj);
     *      }
     *
     * Correctly overriding equals should also follow its
     * contract and be consistent with hashCode().
     *
     *
     * 39. POLYMORPHISM AND BINDING
     *
     * Binding means associating a method call with the
     * method implementation that will execute.
     *
     * STATIC BINDING:
     * - Method selection is resolved at compile time.
     * - Common examples include overloaded methods,
     *   static methods, and private methods.
     *
     * DYNAMIC BINDING:
     * - Overridden instance method implementation is selected
     *   at runtime based on the actual object.
     *
     * Important:
     * The terms early binding and late binding are common
     * teaching descriptions. The Java Language Specification
     * defines the exact method selection and invocation rules.
     *
     * Do not assume that every non-static method call is
     * dynamically dispatched. Private, final, and other
     * non-overridable cases have different rules.
     *
     *
     * 40. POLYMORPHISM AND COMPOSITION
     *
     * Polymorphism is often used with composition.
     *
     * Example:
     *
     *      interface Notification {
     *          void send(String message);
     *      }
     *
     *      class EmailNotification implements Notification {
     *          public void send(String message) {}
     *      }
     *
     *      class SmsNotification implements Notification {
     *          public void send(String message) {}
     *      }
     *
     *      class AlertService {
     *          private final Notification notification;
     *
     *          AlertService(Notification notification) {
     *              this.notification = notification;
     *          }
     *
     *          void alert(String message) {
     *              notification.send(message);
     *          }
     *      }
     *
     *      AlertService service =
     *          new AlertService(new EmailNotification());
     *
     * The service depends on an abstraction.
     * Different notification implementations can be supplied
     * without changing AlertService.
     *
     * This is a common example of dependency inversion and
     * dependency injection.
     *
     *
     * 41. REAL-WORLD USE CASES
     *
     * 1. Payment systems:
     *    Payment interface with UPI, card, and net-banking
     *    implementations.
     *
     * 2. Notification systems:
     *    Common Notification interface with email, SMS,
     *    and push implementations.
     *
     * 3. Database access:
     *    A common repository abstraction with different
     *    implementations.
     *
     * 4. Logging:
     *    Common logger abstraction with different providers.
     *
     * 5. Collections:
     *    List reference can refer to ArrayList or LinkedList.
     *
     * 6. Frameworks:
     *    Framework code invokes user-provided overridden
     *    methods or interface implementations.
     *
     * Example:
     *
     *      List<String> names = new ArrayList<>();
     *
     * The code uses the List interface rather than depending
     * on ArrayList-specific behavior.
     *
     *
     * 42. ADVANTAGES OF POLYMORPHISM
     *
     * - Loose coupling.
     * - Extensibility.
     * - Reusable common code.
     * - Easier testing through interchangeable implementations.
     * - Supports abstraction and interface-based design.
     * - Reduces conditional logic based on concrete types.
     * - Helps implement the Open/Closed Principle.
     *
     *
     * 43. LIMITATIONS AND TRADE-OFFS
     *
     * - Dynamic dispatch can make execution flow harder to trace.
     * - Excessive inheritance can create tight coupling.
     * - Incorrect casts can cause ClassCastException.
     * - Overloaded methods can create ambiguous calls.
     * - Complex hierarchies can make code difficult to maintain.
     * - Generic invariance can surprise developers.
     * - Polymorphic behavior requires careful API design.
     *
     * Prefer interfaces and composition when they provide
     * a clearer abstraction than a deep inheritance hierarchy.
     *
     *
     * 44. COMMON CONFUSIONS
     *
     * 1. Polymorphism is not the same as inheritance.
     *    Inheritance provides one mechanism for polymorphism.
     *    Interfaces also enable polymorphic behavior.
     *
     * 2. Overloading is not overriding.
     *    Overloading changes the parameter list.
     *    Overriding replaces an inherited instance method's
     *    implementation.
     *
     * 3. Reference type is not object type.
     *    Animal a = new Dog();
     *    Reference type: Animal.
     *    Object type: Dog.
     *
     * 4. Static methods are not overridden.
     *    They are hidden.
     *
     * 5. Fields are not overridden.
     *    They can be hidden.
     *
     * 6. Constructors are not inherited or overridden.
     *
     * 7. Private methods are not overridden.
     *
     * 8. Return type alone cannot overload a method.
     *
     * 9. A parent reference cannot directly access
     *    subclass-only members.
     *
     * 10. Overload selection is not based on the runtime
     *     object type of an argument.
     *
     * 11. Overridden instance method selection is based on
     *     the actual receiver object.
     *
     * 12. Array covariance does not mean generic covariance.
     *
     *
     * 45. OUTPUT-BASED QUESTIONS
     *
     * QUESTION 1:
     *
     *      class A {
     *          void show() {
     *              System.out.println("A");
     *          }
     *      }
     *
     *      class B extends A {
     *          void show() {
     *              System.out.println("B");
     *          }
     *      }
     *
     *      A obj = new B();
     *      obj.show();
     *
     * Output:
     *      B
     *
     * Reason:
     * Runtime dispatch selects B.show().
     *
     *
     * QUESTION 2:
     *
     *      class A {
     *          static void show() {
     *              System.out.println("A");
     *          }
     *      }
     *
     *      class B extends A {
     *          static void show() {
     *              System.out.println("B");
     *          }
     *      }
     *
     *      A obj = new B();
     *      obj.show();
     *
     * Output:
     *      A
     *
     * Reason:
     * Static method hiding uses the reference's compile-time type.
     *
     *
     * QUESTION 3:
     *
     *      class A {
     *          int value = 10;
     *      }
     *
     *      class B extends A {
     *          int value = 20;
     *      }
     *
     *      A obj = new B();
     *      System.out.println(obj.value);
     *
     * Output:
     *      10
     *
     * Reason:
     * Field access is resolved using the reference type.
     *
     *
     * QUESTION 4:
     *
     *      class Demo {
     *          void show(long x) {
     *              System.out.println("long");
     *          }
     *
     *          void show(Integer x) {
     *              System.out.println("Integer");
     *          }
     *      }
     *
     *      new Demo().show(10);
     *
     * Output:
     *      long
     *
     * Reason:
     * Primitive widening is considered before boxing.
     *
     *
     * QUESTION 5:
     *
     *      class Demo {
     *          void show(Object obj) {
     *              System.out.println("Object");
     *          }
     *
     *          void show(String str) {
     *              System.out.println("String");
     *          }
     *      }
     *
     *      new Demo().show(null);
     *
     * Output:
     *      String
     *
     * Reason:
     * String is more specific than Object.
     *
     *
     * QUESTION 6:
     *
     *      class Animal {}
     *      class Dog extends Animal {}
     *
     *      class Demo {
     *          void show(Animal a) {
     *              System.out.println("Animal");
     *          }
     *
     *          void show(Dog d) {
     *              System.out.println("Dog");
     *          }
     *      }
     *
     *      Animal a = new Dog();
     *      new Demo().show(a);
     *
     * Output:
     *      Animal
     *
     * Reason:
     * Overload resolution uses the expression's compile-time type.
     *
     *
     * QUESTION 7:
     *
     *      class A {
     *          private void show() {
     *              System.out.println("A");
     *          }
     *
     *          void call() {
     *              show();
     *          }
     *      }
     *
     *      class B extends A {
     *          private void show() {
     *              System.out.println("B");
     *          }
     *      }
     *
     *      new B().call();
     *
     * Output:
     *      A
     *
     * Reason:
     * A's private method is not overridden by B.
     *
     *
     * 46. INTERVIEW QUESTIONS AND ANSWERS
     *
     * Q1. What is polymorphism?
     * A. Polymorphism allows a common name, reference, or
     *    operation to represent different behaviors.
     *
     * Q2. What are the main types of polymorphism in Java?
     * A. Compile-time polymorphism and runtime polymorphism.
     *
     * Q3. How is compile-time polymorphism achieved?
     * A. Primarily through method overloading.
     *
     * Q4. How is runtime polymorphism achieved?
     * A. Through overriding of instance methods and dynamic
     *    method dispatch.
     *
     * Q5. Can static methods be overridden?
     * A. No. Static methods can be hidden.
     *
     * Q6. Can private methods be overridden?
     * A. No. Private methods are not inherited.
     *
     * Q7. Can final methods be overridden?
     * A. No.
     *
     * Q8. Can constructors be overridden?
     * A. No. Constructors are not inherited.
     *
     * Q9. Can constructors be overloaded?
     * A. Yes.
     *
     * Q10. Can static methods be overloaded?
     * A. Yes.
     *
     * Q11. Can a method be overloaded by changing only
     *     its return type?
     * A. No.
     *
     * Q12. Can an overriding method have a different
     *     return type?
     * A. It can have the same return type or a covariant
     *    reference return type.
     *
     * Q13. What is dynamic method dispatch?
     * A. Runtime selection of an overridden instance method
     *    based on the actual receiver object's class.
     *
     * Q14. What is the difference between reference type
     *     and object type?
     * A. Reference type controls compile-time access and
     *    overload resolution; object type controls the
     *    selected overriding implementation.
     *
     * Q15. Can a parent reference hold a child object?
     * A. Yes. This is upcasting.
     *
     * Q16. Can a child reference hold a parent object?
     * A. Not directly. A compatible explicit cast may be
     *    possible, but an incompatible actual object causes
     *    ClassCastException.
     *
     * Q17. What is covariant return type?
     * A. Returning a subtype of the overridden method's
     *    reference return type.
     *
     * Q18. Why is @Override useful?
     * A. It makes the compiler verify that the method
     *    actually overrides a compatible method.
     *
     * Q19. Can an overloaded method be selected at runtime
     *     based on the actual argument object?
     * A. No. Overload resolution is based on compile-time
     *    argument types.
     *
     * Q20. What is the difference between method hiding
     *     and overriding?
     * A. Hiding applies to static methods and is resolved
     *    using the compile-time type. Overriding applies
     *    to instance methods and uses runtime dispatch.
     *
     * Q21. What is bridge method generation?
     * A. Compiler-generated synthetic methods that preserve
     *    overriding relationships after type erasure or
     *    covariant return adaptation.
     *
     * Q22. Are Java arrays covariant?
     * A. Yes, reference-type arrays are covariant. An
     *    incompatible store can cause ArrayStoreException.
     *
     * Q23. Are Java generic types covariant?
     * A. Ordinary generic types are invariant. Wildcards
     *    provide bounded flexibility.
     *
     * Q24. Can an interface reference hold an object?
     * A. Yes, if the object's class implements that interface.
     *
     * Q25. Is inheritance required for polymorphism?
     * A. Class inheritance is not always required. Different
     *    classes can implement the same interface.
     *
     *
     * 47. FINAL REVISION CHECKLIST
     *
     * FUNDAMENTALS
     * [ ] Meaning and purpose of polymorphism.
     * [ ] Compile-time vs runtime polymorphism.
     * [ ] Overloading vs overriding.
     *
     * OVERLOADING
     * [ ] Method signature rules.
     * [ ] Primitive widening and narrowing.
     * [ ] Reference-type overload resolution.
     * [ ] null argument ambiguity.
     * [ ] Boxing and unboxing.
     * [ ] Varargs and overload resolution phases.
     * [ ] Constructor overloading.
     *
     * OVERRIDING
     * [ ] Overriding rules.
     * [ ] @Override annotation.
     * [ ] Access modifier restrictions.
     * [ ] Checked exception restrictions.
     * [ ] Covariant return types.
     * [ ] Private, static, and final method behavior.
     *
     * RUNTIME POLYMORPHISM
     * [ ] Dynamic method dispatch.
     * [ ] Reference type vs actual object type.
     * [ ] Upcasting and downcasting.
     * [ ] instanceof and pattern matching.
     * [ ] Abstract class polymorphism.
     * [ ] Interface polymorphism.
     * [ ] Default method conflicts.
     *
     * ADVANCED
     * [ ] Field hiding.
     * [ ] Static method hiding.
     * [ ] Array covariance.
     * [ ] Generic invariance and wildcards.
     * [ ] Type erasure.
     * [ ] Bridge methods.
     * [ ] Object method overriding.
     *
     * DESIGN
     * [ ] Loose coupling.
     * [ ] Interface-based programming.
     * [ ] Composition with polymorphism.
     * [ ] Real-world use cases.
     *
     * INTERVIEW PREPARATION
     * [ ] Explain output-based questions.
     * [ ] Identify compile-time errors.
     * [ ] Identify runtime exceptions.
     * [ ] Explain overload resolution.
     * [ ] Explain dynamic dispatch step by step.
     *
     * ============================================================
     * END OF POLYMORPHISM
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.print("The above is all about Polymorphism , Happy Learning ..!");
    }

}
