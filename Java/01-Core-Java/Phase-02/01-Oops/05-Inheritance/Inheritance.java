
public class Inheritance {

    public static void main(String[] args) {

    }

    /*
     * =================================================================
     *                         JAVA INHERITANCE
     * =================================================================
     *
     * 1. INTRODUCTION TO INHERITANCE
     *
     * Inheritance is one of the four fundamental pillars of
     * Object-Oriented Programming.
     *
     * Inheritance allows one class to acquire accessible fields
     * and methods from another class.
     *
     * The existing class is called:
     *
     *     Parent class / Superclass / Base class
     *
     * The new class is called:
     *
     *     Child class / Subclass / Derived class
     *
     * Java uses the extends keyword for class inheritance.
     *
     * SYNTAX:
     *
     *     class Parent {
     *         // Fields and methods
     *     }
     *
     *     class Child extends Parent {
     *         // Additional fields and methods
     *     }
     *
     * EXAMPLE:
     *
     *     class Animal {
     *
     *         void eat() {
     *             System.out.println("Eating");
     *         }
     *     }
     *
     *     class Dog extends Animal {
     *
     *         void bark() {
     *             System.out.println("Barking");
     *         }
     *     }
     *
     *     Dog dog = new Dog();
     *     dog.eat();
     *     dog.bark();
     *
     * Dog inherits the accessible eat() method from Animal and
     * defines its own bark() method.
     *
     *
     * =================================================================
     * 2. WHY DO WE NEED INHERITANCE?
     * =================================================================
     *
     * 1. CODE REUSABILITY
     *
     *    Common functionality can be defined in a superclass and
     *    reused by subclasses.
     *
     * 2. EXTENSIBILITY
     *
     *    Subclasses can introduce additional fields and methods.
     *
     * 3. METHOD OVERRIDING
     *
     *    Subclasses can provide specialized implementations of
     *    inherited instance methods.
     *
     * 4. POLYMORPHISM
     *
     *    Superclass references can refer to subclass objects.
     *
     * 5. ORGANIZATION
     *
     *    Related classes can be represented through a hierarchy.
     *
     * IMPORTANT:
     *
     * Inheritance should represent a meaningful IS-A relationship.
     * Do not use inheritance merely to reuse a few methods.
     *
     * Composition may be a better choice when one object simply
     * uses or contains another object.
     *
     *
     * =================================================================
     * 3. PARENT CLASS AND CHILD CLASS
     * =================================================================
     *
     * Consider:
     *
     *     class Employee {
     *
     *         String name;
     *
     *         void work() {
     *             System.out.println("Working");
     *         }
     *     }
     *
     *     class Developer extends Employee {
     *
     *         void writeCode() {
     *             System.out.println("Writing code");
     *         }
     *     }
     *
     * Here:
     *
     * Employee  = Parent class / Superclass
     * Developer = Child class / Subclass
     *
     * Developer can use accessible inherited members of Employee.
     *
     * A Developer object contains the inherited instance state
     * defined by Employee as part of the object.
     *
     * IMPORTANT:
     *
     * Inheritance does not mean that the child class receives
     * a separate copy of the parent's source code.
     *
     * The JVM creates an object according to the complete class
     * hierarchy and its initialization rules.
     *
     *
     * =================================================================
     * 4. TYPES OF INHERITANCE
     * =================================================================
     *
     * Common inheritance structures:
     *
     * 1. Single inheritance
     * 2. Multilevel inheritance
     * 3. Hierarchical inheritance
     * 4. Multiple inheritance
     * 5. Hybrid inheritance
     *
     * A. SINGLE INHERITANCE
     *
     * One child class extends one parent class.
     *
     *     class Animal { }
     *
     *     class Dog extends Animal { }
     *
     * Structure:
     *
     *          Animal
     *             |
     *            Dog
     *
     *
     * B. MULTILEVEL INHERITANCE
     *
     * A class extends another child class, creating multiple levels.
     *
     *     class Animal { }
     *
     *     class Mammal extends Animal { }
     *
     *     class Dog extends Mammal { }
     *
     * Structure:
     *
     *          Animal
     *             |
     *           Mammal
     *             |
     *            Dog
     *
     * Dog can inherit accessible members through the hierarchy.
     *
     *
     * C. HIERARCHICAL INHERITANCE
     *
     * Multiple child classes extend the same parent class.
     *
     *     class Animal { }
     *
     *     class Dog extends Animal { }
     *
     *     class Cat extends Animal { }
     *
     * Structure:
     *
     *             Animal
     *             /    \
     *           Dog    Cat
     *
     *
     * D. MULTIPLE INHERITANCE
     *
     * Multiple inheritance of classes means one class directly
     * extending more than one class.
     *
     * Java does not support this:
     *
     *     class C extends A, B { } // Compilation error
     *
     * However, Java supports implementing multiple interfaces.
     *
     *     interface A { }
     *
     *     interface B { }
     *
     *     class C implements A, B { }
     *
     * This supports multiple inheritance of type through interfaces.
     *
     *
     * E. HYBRID INHERITANCE
     *
     * Hybrid inheritance combines multiple inheritance structures.
     *
     * Java does not support arbitrary hybrid inheritance involving
     * multiple inheritance of classes.
     *
     * Some hybrid structures can be modeled using class inheritance
     * together with interfaces.
     *
     *
     * =================================================================
     * 5. JAVA INHERITANCE HIERARCHY
     * =================================================================
     *
     * Every ordinary Java class directly or indirectly extends
     * java.lang.Object.
     *
     * EXAMPLE:
     *
     *     class Animal { }
     *
     *     class Dog extends Animal { }
     *
     * Conceptually:
     *
     *     Object
     *        |
     *      Animal
     *        |
     *       Dog
     *
     * If a class does not explicitly extend another class,
     * it implicitly extends Object.
     *
     * IMPORTANT:
     *
     * - Object is the root superclass of the ordinary class hierarchy.
     * - Interfaces do not extend Object.
     * - An interface reference can still refer to an object whose
     *   runtime class indirectly extends Object.
     * - A class has only one direct superclass.
     *
     *
     * =================================================================
     * 6. ACCESSING INHERITED MEMBERS
     * =================================================================
     *
     * A subclass can access inherited members according to
     * Java access-control rules.
     *
     * EXAMPLE:
     *
     *     class Parent {
     *
     *         public int a = 10;
     *         protected int b = 20;
     *         int c = 30;
     *         private int d = 40;
     *     }
     *
     *     class Child extends Parent {
     *
     *         void display() {
     *
     *             System.out.println(a);
     *             System.out.println(b);
     *             System.out.println(c);
     *
     *             // System.out.println(d); // Error
     *         }
     *     }
     *
     * Here, assuming both classes belong to the same package:
     *
     * - public is accessible.
     * - protected is accessible.
     * - package-private is accessible.
     * - private is not directly accessible in Child.
     *
     * IMPORTANT:
     *
     * Private members still belong to the parent portion of
     * the subclass object, but they are not directly accessible
     * by subclass code.
     *
     * They may be accessed indirectly through accessible
     * superclass methods.
     *
     *
     * =================================================================
     * 7. INHERITANCE AND ACCESS MODIFIERS
     * =================================================================
     *
     * Accessibility depends on the declaration's modifier and
     * the relationship between the accessing code and the class.
     *
     * GENERAL SUMMARY:
     *
     * Modifier          Same package     Different package
     * -------------------------------------------------------
     * public            Accessible       Accessible*
     * protected         Accessible       Subclass rules
     * package-private   Accessible       Not accessible
     * private           Private scope    Private scope
     *
     * * Subject to enclosing-type and module accessibility.
     *
     * IMPORTANT PROTECTED RULE:
     *
     * A subclass in another package can access an inherited
     * protected instance member through itself or an appropriate
     * subclass-typed reference.
     *
     * It cannot access that protected instance member through
     * an arbitrary superclass-typed reference.
     *
     * Detailed access rules are covered in Modifiers.java.
     *
     *
     * =================================================================
     * 8. SUPER KEYWORD
     * =================================================================
     *
     * The super keyword refers to the superclass portion of
     * the current object.
     *
     * It is commonly used for:
     *
     * 1. Accessing superclass fields.
     * 2. Calling superclass methods.
     * 3. Invoking superclass constructors using super().
     *
     *
     * A. ACCESSING SUPERCLASS FIELDS
     *
     * When a subclass field hides a superclass field, super can
     * be used to access the superclass field.
     *
     * EXAMPLE:
     *
     *     class Parent {
     *         int value = 10;
     *     }
     *
     *     class Child extends Parent {
     *
     *         int value = 20;
     *
     *         void display() {
     *             System.out.println(value);
     *             System.out.println(super.value);
     *         }
     *     }
     *
     * OUTPUT:
     *
     *     20
     *     10
     *
     *
     * B. CALLING SUPERCLASS METHODS
     *
     * super can invoke the superclass implementation of an
     * overridden instance method.
     *
     * EXAMPLE:
     *
     *     class Parent {
     *
     *         void display() {
     *             System.out.println("Parent");
     *         }
     *     }
     *
     *     class Child extends Parent {
     *
     *         @Override
     *         void display() {
     *
     *             super.display();
     *             System.out.println("Child");
     *         }
     *     }
     *
     * OUTPUT:
     *
     *     Parent
     *     Child
     *
     *
     * C. INVOKING SUPERCLASS CONSTRUCTORS
     *
     * super() invokes a constructor of the direct superclass.
     *
     * EXAMPLE:
     *
     *     class Parent {
     *
     *         Parent() {
     *             System.out.println("Parent constructor");
     *         }
     *     }
     *
     *     class Child extends Parent {
     *
     *         Child() {
     *             super();
     *             System.out.println("Child constructor");
     *         }
     *     }
     *
     * OUTPUT:
     *
     *     Parent constructor
     *     Child constructor
     *
     * IMPORTANT:
     *
     * - super and super() are different.
     * - super accesses superclass members.
     * - super() invokes a superclass constructor.
     * - super cannot be used in a static context.
     * - A constructor can invoke only one constructor using
     *   this() or super() as its explicit constructor invocation.
     *
     *
     * =================================================================
     * 9. CONSTRUCTOR CHAINING IN INHERITANCE
     * =================================================================
     *
     * When a subclass object is created, its superclass
     * constructor chain is processed.
     *
     * EXAMPLE:
     *
     *     class A {
     *
     *         A() {
     *             System.out.println("A");
     *         }
     *     }
     *
     *     class B extends A {
     *
     *         B() {
     *             System.out.println("B");
     *         }
     *     }
     *
     *     class C extends B {
     *
     *         C() {
     *             System.out.println("C");
     *         }
     *     }
     *
     *     C obj = new C();
     *
     * OUTPUT:
     *
     *     A
     *     B
     *     C
     *
     * WHY?
     *
     * The constructor chain proceeds from the superclass hierarchy
     * towards the most-derived class.
     *
     * EXPLICIT SUPERCLASS CONSTRUCTOR:
     *
     *     class Parent {
     *
     *         Parent(int id) {
     *             System.out.println(id);
     *         }
     *     }
     *
     *     class Child extends Parent {
     *
     *         Child() {
     *             super(101);
     *         }
     *     }
     *
     * If a superclass does not have an accessible no-argument
     * constructor, the subclass must explicitly invoke an
     * appropriate superclass constructor.
     *
     * IMPLICIT SUPER():
     *
     * If a constructor does not explicitly invoke another
     * constructor, Java normally inserts an implicit super()
     * invocation, subject to the language's constructor rules.
     *
     * If the superclass has no accessible no-argument constructor,
     * compilation fails unless another valid constructor invocation
     * is provided.
     *
     * VERSION NOTE:
     *
     * Flexible constructor bodies were introduced as a preview
     * feature in Java 22 and finalized in Java 25.
     * Constructor invocation placement rules therefore depend
     * on the language version and enabled features.
     *
     *
     * =================================================================
     * 10. METHOD OVERRIDING
     * =================================================================
     *
     * Method overriding occurs when a subclass provides a new
     * implementation of an inherited instance method with a
     * compatible signature.
     *
     * EXAMPLE:
     *
     *     class Animal {
     *
     *         void sound() {
     *             System.out.println("Animal sound");
     *         }
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
     * The overridden instance method is selected according to
     * the runtime class of the object.
     *
     * OVERRIDING RULES:
     *
     * 1. The method must be inherited and eligible for overriding.
     * 2. The overriding method must have a compatible signature.
     * 3. Return types must be compatible; covariant reference
     *    return types are allowed.
     * 4. Access cannot be reduced.
     * 5. Checked exceptions cannot be broadened beyond what
     *    the overridden method permits.
     * 6. final methods cannot be overridden.
     * 7. Private methods are not overridden.
     * 8. Static methods are hidden, not overridden.
     *
     * @Override:
     *
     * The annotation asks the compiler to verify that the method
     * actually overrides or implements an eligible method.
     *
     *
     * =================================================================
     * 11. METHOD OVERRIDING VS METHOD OVERLOADING
     * =================================================================
     *
     * METHOD OVERRIDING:
     *
     * - Occurs between superclass and subclass relationships.
     * - Uses a compatible method signature.
     * - Provides a specialized implementation.
     * - Supports runtime polymorphism for instance methods.
     *
     * METHOD OVERLOADING:
     *
     * - Uses the same method name with different parameter lists.
     * - Can occur in the same class or across inherited declarations.
     * - Is resolved at compile time.
     * - Does not require inheritance.
     *
     * COMPARISON:
     *
     * Feature          Overloading          Overriding
     * ----------------------------------------------------
     * Parameters       Different            Same signature
     * Inheritance      Not required          Required relationship
     * Resolution       Compile time          Runtime dispatch
     * Purpose          Multiple forms        Specialized behaviour
     *
     *
     * =================================================================
     * 12. DYNAMIC METHOD DISPATCH
     * =================================================================
     *
     * Dynamic method dispatch is the mechanism by which an
     * overridden instance method is selected at runtime according
     * to the actual object's class.
     *
     * EXAMPLE:
     *
     *     class Animal {
     *
     *         void sound() {
     *             System.out.println("Animal");
     *         }
     *     }
     *
     *     class Dog extends Animal {
     *
     *         @Override
     *         void sound() {
     *             System.out.println("Dog");
     *         }
     *     }
     *
     *     class Cat extends Animal {
     *
     *         @Override
     *         void sound() {
     *             System.out.println("Cat");
     *         }
     *     }
     *
     *     Animal a1 = new Dog();
     *     Animal a2 = new Cat();
     *
     *     a1.sound();
     *     a2.sound();
     *
     * OUTPUT:
     *
     *     Dog
     *     Cat
     *
     * COMPILE-TIME TYPE:
     *
     * Animal determines which members are available for
     * compile-time checking.
     *
     * RUNTIME TYPE:
     *
     * Dog or Cat determines which overridden implementation
     * executes.
     *
     * IMPORTANT:
     *
     * Dynamic dispatch applies to eligible overridden instance
     * methods, not fields or static methods.
     *
     *
     * =================================================================
     * 13. FIELD HIDING
     * =================================================================
     *
     * Fields are not overridden.
     *
     * When a subclass declares a field with the same name as
     * an inherited field, the subclass field hides the superclass
     * field.
     *
     * EXAMPLE:
     *
     *     class Parent {
     *         int value = 10;
     *     }
     *
     *     class Child extends Parent {
     *         int value = 20;
     *     }
     *
     *     Parent obj = new Child();
     *
     *     System.out.println(obj.value);
     *
     * OUTPUT:
     *
     *     10
     *
     * The field selected is based on the compile-time reference type.
     *
     * To access the child field:
     *
     *     Child child = new Child();
     *     System.out.println(child.value);
     *
     * OUTPUT:
     *
     *     20
     *
     * IMPORTANT:
     *
     * Method overriding and field hiding follow different rules.
     *
     *
     * =================================================================
     * 14. STATIC METHOD HIDING
     * =================================================================
     *
     * Static methods belong to classes rather than individual objects.
     *
     * If a subclass declares a compatible static method with the
     * same signature, it hides the superclass static method.
     *
     * EXAMPLE:
     *
     *     class Parent {
     *
     *         static void display() {
     *             System.out.println("Parent");
     *         }
     *     }
     *
     *     class Child extends Parent {
     *
     *         static void display() {
     *             System.out.println("Child");
     *         }
     *     }
     *
     *     Parent obj = new Child();
     *     obj.display();
     *
     * OUTPUT:
     *
     *     Parent
     *
     * Prefer calling static methods using the class name:
     *
     *     Parent.display();
     *     Child.display();
     *
     * IMPORTANT:
     *
     * Static method hiding is resolved using the compile-time
     * type of the qualifying class or expression, not runtime
     * polymorphic dispatch.
     *
     *
     * =================================================================
     * 15. COVARIANT RETURN TYPES
     * =================================================================
     *
     * An overriding method can return a subtype of the return
     * type declared by the overridden method, when the types
     * are compatible reference types.
     *
     * EXAMPLE:
     *
     *     class Animal {
     *     }
     *
     *     class Dog extends Animal {
     *     }
     *
     *     class Parent {
     *
     *         Animal getAnimal() {
     *             return new Animal();
     *         }
     *     }
     *
     *     class Child extends Parent {
     *
     *         @Override
     *         Dog getAnimal() {
     *             return new Dog();
     *         }
     *     }
     *
     * Dog is a subtype of Animal, so this return type is valid.
     *
     * Primitive return types cannot use covariant return types.
     *
     *
     * =================================================================
     * 16. FINAL AND INHERITANCE
     * =================================================================
     *
     * The final modifier restricts inheritance and overriding.
     *
     * A. FINAL CLASS
     *
     * A final class cannot be extended.
     *
     *     final class Security {
     *     }
     *
     *     // class AdvancedSecurity extends Security { }
     *     // Compilation error
     *
     * B. FINAL METHOD
     *
     * A final method cannot be overridden.
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
     *         // void display() { }
     *         // Compilation error
     *     }
     *
     * C. FINAL VARIABLE
     *
     * A final variable cannot be reassigned after initialization.
     *
     * IMPORTANT:
     *
     * A final class can inherit from another class.
     * It simply cannot have subclasses of its own.
     *
     *
     * =================================================================
     * 17. ABSTRACT CLASSES AND INHERITANCE
     * =================================================================
     *
     * An abstract class can be extended by another abstract
     * or concrete class.
     *
     * EXAMPLE:
     *
     *     abstract class Vehicle {
     *
     *         abstract void start();
     *     }
     *
     *     abstract class Car extends Vehicle {
     *
     *         void fuel() {
     *             System.out.println("Fuel available");
     *         }
     *     }
     *
     *     class ElectricCar extends Car {
     *
     *         @Override
     *         void start() {
     *             System.out.println("Electric car started");
     *         }
     *     }
     *
     * ElectricCar must implement the inherited abstract method.
     *
     * Detailed abstraction rules are covered in Abstraction.java.
     *
     *
     * =================================================================
     * 18. CONSTRUCTOR VS INHERITANCE
     * =================================================================
     *
     * Constructors are not inherited by subclasses.
     *
     * However, a subclass constructor invokes a superclass
     * constructor during object construction.
     *
     * EXAMPLE:
     *
     *     class Parent {
     *
     *         Parent(int value) {
     *             System.out.println(value);
     *         }
     *     }
     *
     *     class Child extends Parent {
     *
     *         Child() {
     *             super(100);
     *         }
     *     }
     *
     *     Child child = new Child();
     *
     * OUTPUT:
     *
     *     100
     *
     * If the parent has only parameterized constructors, the
     * child must invoke an accessible matching constructor.
     *
     *
     * =================================================================
     * 19. UPCASTING AND DOWNCASTING
     * =================================================================
     *
     * UPCASTING:
     *
     * Assigning a subclass object reference to a superclass
     * reference.
     *
     * It is generally implicit.
     *
     *     Dog dog = new Dog();
     *
     *     Animal animal = dog;
     *
     * DOWNCASTING:
     *
     * Converting a superclass reference to a subclass reference.
     *
     * It requires an explicit cast.
     *
     *     Animal animal = new Dog();
     *
     *     Dog dog = (Dog) animal;
     *
     * This succeeds because the actual object is a Dog.
     *
     * UNSAFE DOWNCAST:
     *
     *     Animal animal = new Animal();
     *
     *     Dog dog = (Dog) animal;
     *
     * This compiles but throws ClassCastException at runtime.
     *
     * INSTANCEOF:
     *
     * Use instanceof to test whether an object is compatible
     * with a particular type before casting.
     *
     *     if (animal instanceof Dog) {
     *         Dog dog = (Dog) animal;
     *     }
     *
     * PATTERN MATCHING:
     *
     * Modern Java supports pattern matching for instanceof.
     *
     *     if (animal instanceof Dog dog) {
     *         dog.bark();
     *     }
     *
     * Pattern matching for instanceof became final in Java 16.
     *
     * IMPORTANT:
     *
     * Upcasting restricts the members available through the
     * reference at compile time, but overridden methods can
     * still execute according to the runtime object type.
     *
     *
     * =================================================================
     * 20. INHERITANCE VS COMPOSITION
     * =================================================================
     *
     * INHERITANCE:
     *
     * Represents an IS-A relationship.
     *
     * Example:
     *
     *     Dog IS-A Animal.
     *
     * COMPOSITION:
     *
     * Represents a HAS-A relationship.
     *
     * Example:
     *
     *     Car HAS-A Engine.
     *
     * INHERITANCE EXAMPLE:
     *
     *     class Animal {
     *         void eat() { }
     *     }
     *
     *     class Dog extends Animal {
     *     }
     *
     * COMPOSITION EXAMPLE:
     *
     *     class Engine {
     *
     *         void start() { }
     *     }
     *
     *     class Car {
     *
     *         private final Engine engine = new Engine();
     *
     *         void start() {
     *             engine.start();
     *         }
     *     }
     *
     * COMPARISON:
     *
     * Feature          Inheritance          Composition
     * ----------------------------------------------------
     * Relationship     IS-A                 HAS-A
     * Coupling         Often tighter        Can be more flexible
     * Reuse            Inherited behaviour  Delegated behaviour
     * Change           Hierarchy changes    Component replacement
     *
     * DESIGN GUIDELINE:
     *
     * Prefer composition when an object uses another object
     * rather than being a specialized form of that object.
     *
     * Inheritance should represent a valid substitutable
     * relationship.
     *
     *
     * =================================================================
     * 21. LISKOV SUBSTITUTION PRINCIPLE
     * =================================================================
     *
     * The Liskov Substitution Principle (LSP) is one of the
     * SOLID design principles.
     *
     * It states that objects of a subtype should be usable
     * wherever objects of the base type are expected without
     * breaking the correctness of the program.
     *
     * EXAMPLE:
     *
     * If a method accepts an Animal:
     *
     *     void makeSound(Animal animal) {
     *         animal.sound();
     *     }
     *
     * Suitable Animal subtypes should be usable by this method
     * while preserving the expected contract.
     *
     * IMPORTANT:
     *
     * Inheritance is not only about acquiring methods.
     * A well-designed subclass must preserve the behavioural
     * expectations of its superclass.
     *
     *
     * =================================================================
     * 22. SEALED CLASSES AND INHERITANCE
     * =================================================================
     *
     * Sealed classes restrict which classes may directly extend them.
     *
     * Finalized in Java 17.
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
     * Permitted subclasses must be declared:
     *
     *     final
     *     sealed
     *     non-sealed
     *
     * A sealed hierarchy provides more controlled inheritance
     * than an unrestricted class hierarchy.
     *
     * IMPORTANT:
     *
     * A sealed class can restrict its direct subclasses, but
     * non-sealed descendants may extend further under ordinary rules.
     *
     *
     * =================================================================
     * 23. COMMON INHERITANCE CONFUSIONS
     * =================================================================
     *
     * 1. Inheritance vs composition:
     *
     *    IS-A vs HAS-A.
     *
     * 2. Overriding vs overloading:
     *
     *    Specialized inherited method vs different parameter list.
     *
     * 3. Overriding vs hiding:
     *
     *    Instance method overriding vs static method hiding.
     *
     * 4. Field hiding vs method overriding:
     *
     *    Fields are resolved by reference type; overridden methods
     *    use runtime dispatch.
     *
     * 5. Constructor inheritance:
     *
     *    Constructors are not inherited.
     *
     * 6. Private members:
     *
     *    Private members are not directly accessible in subclasses.
     *
     * 7. Superclass constructor:
     *
     *    The superclass constructor executes during subclass
     *    object initialization.
     *
     * 8. Multiple inheritance:
     *
     *    Java does not support multiple class inheritance but
     *    supports implementing multiple interfaces.
     *
     * 9. Upcasting:
     *
     *    Usually implicit and safe for a valid subtype relationship.
     *
     * 10. Downcasting:
     *
     *     Can fail at runtime if the actual object is incompatible.
     *
     *
     * =================================================================
     * 24. INTERVIEW QUESTIONS
     * =================================================================
     *
     * Q1. What is inheritance?
     *
     * A mechanism through which a class acquires accessible
     * members and behaviour from another class.
     *
     * Q2. Which keyword is used for class inheritance?
     *
     * extends.
     *
     * Q3. Which keyword is used to implement an interface?
     *
     * implements.
     *
     * Q4. Does Java support multiple inheritance of classes?
     *
     * No.
     *
     * Q5. Does Java support multilevel inheritance?
     *
     * Yes.
     *
     * Q6. Does Java support hierarchical inheritance?
     *
     * Yes.
     *
     * Q7. What is the root superclass of Java classes?
     *
     * java.lang.Object.
     *
     * Q8. Are constructors inherited?
     *
     * No.
     *
     * Q9. What is super?
     *
     * A keyword used to access superclass members.
     *
     * Q10. What is super()?
     *
     * A constructor invocation that calls a superclass constructor.
     *
     * Q11. What is method overriding?
     *
     * Providing a specialized implementation of an inherited
     * instance method.
     *
     * Q12. Can static methods be overridden?
     *
     * No. Compatible static methods are hidden.
     *
     * Q13. Can private methods be overridden?
     *
     * No. They are not inherited in the ordinary overriding sense.
     *
     * Q14. Can final methods be overridden?
     *
     * No.
     *
     * Q15. What is dynamic method dispatch?
     *
     * Runtime selection of an overridden instance method based
     * on the actual object's class.
     *
     * Q16. What is upcasting?
     *
     * Treating a subclass object as an instance of its superclass.
     *
     * Q17. What is downcasting?
     *
     * Casting a superclass reference to a compatible subclass type.
     *
     * Q18. What happens during an invalid downcast?
     *
     * ClassCastException can occur at runtime.
     *
     * Q19. What is field hiding?
     *
     * A subclass declares a field with the same name as an
     * inherited field.
     *
     * Q20. What is constructor chaining?
     *
     * Invoking constructors in a superclass/subclass hierarchy
     * during object construction.
     *
     * Q21. What is the Liskov Substitution Principle?
     *
     * A subtype should be usable wherever its base type is expected
     * without breaking program correctness.
     *
     * Q22. What is the difference between inheritance and composition?
     *
     * Inheritance represents IS-A; composition represents HAS-A.
     *
     *
     * =================================================================
     * 25. FINAL REVISION CHECKLIST
     * =================================================================
     *
     * FUNDAMENTALS:
     *
     * [ ] Definition and purpose of inheritance
     * [ ] Parent and child classes
     * [ ] IS-A relationship
     * [ ] Inheritance hierarchy
     *
     * TYPES:
     *
     * [ ] Single inheritance
     * [ ] Multilevel inheritance
     * [ ] Hierarchical inheritance
     * [ ] Multiple inheritance restrictions
     * [ ] Hybrid inheritance
     *
     * INHERITANCE MECHANICS:
     *
     * [ ] extends keyword
     * [ ] Inherited member accessibility
     * [ ] super keyword
     * [ ] super() constructor invocation
     * [ ] Constructor chaining
     * [ ] Initialization order
     *
     * METHOD BEHAVIOUR:
     *
     * [ ] Method overriding
     * [ ] Method overloading
     * [ ] Dynamic method dispatch
     * [ ] Field hiding
     * [ ] Static method hiding
     * [ ] Covariant return types
     * [ ] @Override
     *
     * ADVANCED:
     *
     * [ ] final and abstract inheritance rules
     * [ ] Upcasting and downcasting
     * [ ] instanceof and pattern matching
     * [ ] Inheritance vs composition
     * [ ] Liskov Substitution Principle
     * [ ] Sealed classes
     *
     * INTERVIEW PREPARATION:
     *
     * [ ] Access restrictions
     * [ ] Constructor execution output questions
     * [ ] Method overriding output questions
     * [ ] Static hiding and field hiding
     * [ ] Casting edge cases
     * [ ] Common inheritance mistakes
     *
     *
     * =================================================================
     *                       END OF INHERITANCE
     * =================================================================
     */
}
