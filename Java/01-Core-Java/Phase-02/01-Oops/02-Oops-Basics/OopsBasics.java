
public class OopsBasics {

    

    /*
     * =================================================================
     *                 OBJECT-ORIENTED PROGRAMMING (OOP)
     * =================================================================
     *
     * 1. INTRODUCTION TO OBJECT-ORIENTED PROGRAMMING
     *
     * Object-Oriented Programming (OOP) is a programming paradigm
     * in which programs are designed and organized around objects.
     *
     * Objects represent entities that contain:
     *
     * 1. State      - Data or properties of an object.
     * 2. Behaviour  - Operations or actions an object can perform.
     * 3. Identity   - A characteristic that distinguishes one object
     *                from another.
     *
     * Example: A Car object
     *
     * State:
     *     brand = "BMW"
     *     color = "Black"
     *     speed = 100
     *
     * Behaviour:
     *     start()
     *     accelerate()
     *     brake()
     *
     * Identity:
     *     A particular car object is distinct from another car object,
     *     even if both have identical data.
     *
     * OOP helps developers build software using reusable, modular
     * and maintainable components.
     *
     *
     * 2. PROGRAMMING PARADIGMS
     *
     * A programming paradigm is a style or approach used to design
     * and structure programs.
     *
     * Major programming paradigms include:
     *
     * 1. Procedural Programming
     * 2. Object-Oriented Programming
     * 3. Functional Programming
     * 4. Logic Programming
     *
     * PROCEDURAL PROGRAMMING:
     *
     * - Organizes programs around procedures or functions.
     * - Focuses on the sequence of operations.
     * - Data and functions are often organized separately.
     * - Suitable for many algorithmic and procedural tasks.
     *
     * OBJECT-ORIENTED PROGRAMMING:
     *
     * - Organizes programs around objects.
     * - Combines data and related behaviour.
     * - Supports encapsulation, inheritance, abstraction
     *   and polymorphism.
     * - Useful for modeling complex applications.
     *
     * PROCEDURAL VS OOP:
     *
     * Feature             Procedural          OOP
     * ------------------------------------------------
     * Main focus          Functions           Objects
     * Organization        Procedures          Classes/objects
     * Data handling       Often separate      Encapsulated
     * Reusability         Functions            Classes/inheritance
     * Design approach     Top-down             Often bottom-up
     * Data protection     Depends on design    Encapsulation
     * Modularity          Function-based       Object-based
     *
     * IMPORTANT:
     *
     * This is a conceptual comparison, not an absolute rule.
     * Procedural programs can also be modular and well-structured.
     * OOP does not automatically guarantee good software design.
     *
     *
     * 3. FOUR PILLARS OF OOP
     *
     * The four commonly recognized pillars are:
     *
     * 1. Encapsulation
     * 2. Inheritance
     * 3. Polymorphism
     * 4. Abstraction
     *
     * A. ENCAPSULATION
     *
     * Encapsulation means combining data and the methods that operate
     * on that data within a class, while controlling direct access.
     *
     * Example:
     *
     *     class BankAccount {
     *
     *         private double balance;
     *
     *         public void deposit(double amount) {
     *             if (amount > 0) {
     *                 balance += amount;
     *             }
     *         }
     *
     *         public double getBalance() {
     *             return balance;
     *         }
     *     }
     *
     * Here, balance is private and can be modified through
     * controlled operations.
     *
     * B. INHERITANCE
     *
     * Inheritance allows a class to acquire accessible members
     * and behaviour from another class.
     *
     * Example:
     *
     *     class Animal {
     *         void eat() { }
     *     }
     *
     *     class Dog extends Animal {
     *         void bark() { }
     *     }
     *
     * Dog inherits the accessible eat() method.
     *
     * C. POLYMORPHISM
     *
     * Polymorphism means one interface or reference can represent
     * different implementations or behaviours.
     *
     * Common forms in Java:
     *
     * 1. Compile-time polymorphism through method overloading.
     * 2. Runtime polymorphism through method overriding.
     *
     * D. ABSTRACTION
     *
     * Abstraction means exposing essential operations while hiding
     * implementation details.
     *
     * It can be achieved using:
     *
     * 1. Abstract classes
     * 2. Interfaces
     *
     * NOTE:
     *
     * These four concepts are introduced here.
     * Detailed explanations, rules and examples are maintained in
     * their respective dedicated files.
     *
     *
     * =================================================================
     * 4. CLASS IN JAVA
     * =================================================================
     *
     * A class is a blueprint or template used to define objects.
     *
     * It specifies the fields, methods, constructors and other
     * members that its objects can have.
     *
     * SYNTAX:
     *
     *     class ClassName {
     *
     *         // Fields
     *
     *         // Constructors
     *
     *         // Methods
     *     }
     *
     * EXAMPLE:
     *
     *     class Student {
     *
     *         int id;
     *         String name;
     *
     *         void study() {
     *             System.out.println("Studying");
     *         }
     *     }
     *
     * COMPONENTS OF A CLASS:
     *
     * 1. Fields:
     *    Represent the data or state associated with objects.
     *
     * 2. Constructors:
     *    Initialize newly created objects.
     *
     * 3. Methods:
     *    Define operations or behaviour.
     *
     * 4. Initializer blocks:
     *    Perform initialization during class or object initialization.
     *
     * 5. Nested types:
     *    Classes, interfaces, enums and other supported type
     *    declarations defined within another type.
     *
     * IMPORTANT:
     *
     * - A class declaration does not automatically create an object.
     * - Multiple objects can be created from the same class.
     * - Each object normally has its own instance fields.
     * - Static members belong to the class rather than individual
     *   object instances.
     *
     *
     * =================================================================
     * 5. OBJECT IN JAVA
     * =================================================================
     *
     * An object is a runtime entity created from a class.
     *
     * It has:
     *
     * 1. State
     * 2. Behaviour
     * 3. Identity
     *
     * EXAMPLE:
     *
     *     class Student {
     *
     *         int id;
     *         String name;
     *
     *         void display() {
     *             System.out.println(id + " " + name);
     *         }
     *     }
     *
     *     Student s1 = new Student();
     *
     * Here:
     *
     *     Student = Reference type
     *     s1      = Reference variable
     *     new     = Object creation expression
     *     Student() = Constructor invocation
     *
     *     s1.display();
     *
     * This invokes the method using the object referenced by s1.
     *
     * MULTIPLE OBJECTS:
     *
     *     Student s1 = new Student();
     *     Student s2 = new Student();
     *
     * These are two distinct object instances.
     *
     * Changes to the instance fields of one do not automatically
     * change the instance fields of the other.
     *
     *
     * =================================================================
     * 6. CLASS VS OBJECT
     * =================================================================
     *
     * CLASS:
     *
     * - A type definition or blueprint.
     * - Declares fields and methods.
     * - Used to create objects.
     * - Does not represent one particular object instance.
     *
     * OBJECT:
     *
     * - A runtime instance of a class.
     * - Has its own identity.
     * - Has instance state.
     * - Can invoke accessible instance methods.
     *
     * EXAMPLE:
     *
     *     class Car {
     *         String color;
     *         void start() { }
     *     }
     *
     *     Car car1 = new Car();
     *     Car car2 = new Car();
     *
     * Car is the class.
     * car1 and car2 are reference variables.
     * The two new expressions create separate objects.
     *
     * REAL-WORLD ANALOGY:
     *
     * Class:
     *     A blueprint describing how houses are designed.
     *
     * Objects:
     *     Individual houses constructed using that blueprint.
     *
     * IMPORTANT:
     *
     * A class is a reference type in Java.
     * A reference variable can refer to an object or contain null.
     *
     *
     * =================================================================
     * 7. OBJECT CREATION IN JAVA
     * =================================================================
     *
     * Objects are commonly created using the new keyword.
     *
     * SYNTAX:
     *
     *     ClassName reference = new ClassName();
     *
     * EXAMPLE:
     *
     *     Student student = new Student();
     *
     * CONCEPTUAL BREAKDOWN:
     *
     * 1. Student:
     *    Declares the reference variable's type.
     *
     * 2. student:
     *    Name of the reference variable.
     *
     * 3. new:
     *    Creates a new object instance.
     *
     * 4. Student():
     *    Invokes a constructor.
     *
     * The expression returns a reference to the created object.
     *
     * IMPORTANT:
     *
     * The reference variable and the object are different things.
     * The reference variable is not the object itself.
     *
     * OTHER OBJECT CREATION MECHANISMS:
     *
     * 1. Constructor invocation using new.
     * 2. Reflection using APIs such as Constructor.newInstance().
     * 3. Cloning using clone(), when supported and properly implemented.
     * 4. Deserialization.
     * 5. Factory methods that return object references.
     *
     * IMPORTANT:
     *
     * A factory method may internally use new, reflection,
     * caching or another creation mechanism.
     *
     * Objects can also be created without explicitly writing new
     * at the call site, for example through deserialization.
     *
     *
     * =================================================================
     * 8. CONSTRUCTORS
     * =================================================================
     *
     * A constructor is a special class member used to initialize
     * objects during object creation.
     *
     * It has the same name as its class and has no return type.
     *
     * SYNTAX:
     *
     *     class Student {
     *
     *         Student() {
     *             // Initialization
     *         }
     *     }
     *
     * CONSTRUCTOR CHARACTERISTICS:
     *
     * 1. Constructor name must match the class name.
     * 2. It has no return type, not even void.
     * 3. It is invoked during object creation through constructor
     *    invocation.
     * 4. Constructors can be overloaded.
     * 5. Constructors are not inherited.
     * 6. Constructors cannot be overridden.
     * 7. Constructors can use access modifiers.
     * 8. Constructors cannot be static, final or abstract.
     *
     * A. DEFAULT CONSTRUCTOR
     *
     * If a class declares no constructor, the Java compiler
     * implicitly provides a default constructor.
     *
     * Example:
     *
     *     class Student {
     *         int id;
     *     }
     *
     * The compiler provides a no-argument default constructor
     * whose behaviour includes invoking the superclass constructor.
     *
     * IMPORTANT:
     *
     * The compiler provides a default constructor only when
     * no constructor is declared in the class.
     *
     * B. NO-ARGUMENT CONSTRUCTOR
     *
     * A constructor explicitly declared without parameters.
     *
     * Example:
     *
     *     class Student {
     *
     *         Student() {
     *             System.out.println("Created");
     *         }
     *     }
     *
     * This is a user-declared no-argument constructor.
     * It is not the compiler-provided default constructor.
     *
     * C. PARAMETERIZED CONSTRUCTOR
     *
     * A constructor that accepts parameters.
     *
     * Example:
     *
     *     class Student {
     *
     *         int id;
     *         String name;
     *
     *         Student(int id, String name) {
     *             this.id = id;
     *             this.name = name;
     *         }
     *     }
     *
     *     Student s = new Student(101, "Vinay");
     *
     * D. CONSTRUCTOR OVERLOADING
     *
     * Multiple constructors can exist in one class with different
     * parameter lists.
     *
     * Example:
     *
     *     class Student {
     *
     *         Student() { }
     *
     *         Student(int id) { }
     *
     *         Student(int id, String name) { }
     *     }
     *
     * E. CONSTRUCTOR CHAINING
     *
     * One constructor can invoke another constructor using this().
     *
     * Example:
     *
     *     class Student {
     *
     *         Student() {
     *             this(101);
     *         }
     *
     *         Student(int id) {
     *             System.out.println(id);
     *         }
     *     }
     *
     * A constructor can also invoke a superclass constructor
     * using super().
     *
     * In Java 25 and earlier, explicit constructor invocation
     * traditionally had to be the first statement in a constructor.
     * Java 25 introduced flexible constructor bodies as a preview
     * feature; follow the language version and feature status in use.
     *
     * F. CONSTRUCTOR VS METHOD
     *
     * Feature          Constructor          Method
     * ------------------------------------------------
     * Name             Class name            Any valid name
     * Return type      No return type        Required or void
     * Purpose          Initialization        Behaviour
     * Invocation       Object creation       Method call
     * Inheritance      Not inherited         May be inherited
     * Overriding       Not possible          Possible
     * Overloading      Yes                   Yes
     *
     *
     * =================================================================
     * 9. REFERENCE VARIABLES
     * =================================================================
     *
     * A reference variable stores a reference that can identify
     * an object.
     *
     * EXAMPLE:
     *
     *     Student s1 = new Student();
     *
     * Here s1 refers to the created Student object.
     *
     * REFERENCE ASSIGNMENT:
     *
     *     Student s1 = new Student();
     *     Student s2 = s1;
     *
     * Both s1 and s2 now refer to the same object.
     *
     * Therefore:
     *
     *     s2.name = "Vinay";
     *
     * The change is observable through s1 as well because both
     * references identify the same object.
     *
     * IMPORTANT:
     *
     * Assigning one reference variable to another does not create
     * a new object.
     *
     * NULL REFERENCE:
     *
     *     Student s1 = null;
     *
     * null means the reference does not currently refer to an object.
     *
     * Accessing an instance member through a null reference causes
     * NullPointerException.
     *
     * REFERENCE COMPARISON:
     *
     *     s1 == s2
     *
     * For reference operands, == checks whether both references
     * identify the same object, or are both null.
     *
     * It does not compare object contents.
     *
     * Object content equality is commonly implemented using equals().
     *
     *
     * =================================================================
     * 10. THIS KEYWORD
     * =================================================================
     *
     * this refers to the current object in an instance context.
     *
     * A. RESOLVING VARIABLE NAME CONFLICTS
     *
     * Example:
     *
     *     class Student {
     *
     *         int id;
     *
     *         Student(int id) {
     *             this.id = id;
     *         }
     *     }
     *
     * this.id refers to the instance field.
     * id refers to the constructor parameter.
     *
     * B. INVOKING AN INSTANCE METHOD
     *
     *     this.display();
     *
     * C. CONSTRUCTOR CHAINING
     *
     *     Student() {
     *         this(101);
     *     }
     *
     * D. PASSING THE CURRENT OBJECT
     *
     *     register(this);
     *
     * E. RETURNING THE CURRENT OBJECT
     *
     *     Student getStudent() {
     *         return this;
     *     }
     *
     * IMPORTANT:
     *
     * 1. this cannot be used in a static context.
     * 2. this refers to the current object, not the class.
     * 3. this() invokes another constructor in the same class.
     * 4. this and this() have different purposes.
     *
     *
     * =================================================================
     * 11. INSTANCE MEMBERS VS STATIC MEMBERS
     * =================================================================
     *
     * INSTANCE MEMBERS:
     *
     * Members associated with individual object instances.
     *
     * Example:
     *
     *     class Employee {
     *
     *         String name;
     *
     *         void display() {
     *             System.out.println(name);
     *         }
     *     }
     *
     * Each Employee object has its own name field.
     *
     * STATIC MEMBERS:
     *
     * Members associated with the class.
     *
     * Example:
     *
     *     class Employee {
     *
     *         static String company = "ABC";
     *     }
     *
     * The static field is shared at the class level.
     *
     * COMPARISON:
     *
     * Feature             Instance             Static
     * --------------------------------------------------
     * Belongs to          Object                Class
     * Field copies        Per object            Class-level field
     * Access              Object reference      Class name preferred
     * this keyword        Available             Not available
     * Memory model        Object state          Class-level state
     *
     * IMPORTANT:
     *
     * Static members are associated with a class as loaded and
     * initialized by a particular class loader.
     *
     * Avoid saying that every static member exists only once
     * across the entire JVM. Different class loaders can load
     * separate copies of the same class.
     *
     *
     * =================================================================
     * 12. OBJECT INITIALIZATION
     * =================================================================
     *
     * Object initialization is the process of assigning initial
     * values to an object's instance fields and executing
     * initialization logic.
     *
     * DEFAULT FIELD VALUES:
     *
     * Type                       Default value
     * ------------------------------------------------
     * byte, short, int           0
     * long                       0L
     * float                      0.0f
     * double                     0.0d
     * char                       '\u0000'
     * boolean                    false
     * Reference types            null
     *
     * IMPORTANT:
     *
     * Default values apply to fields and array elements.
     * Local variables do not receive automatic default values
     * for definite-assignment purposes.
     *
     * A. FIELD INITIALIZERS
     *
     *     int age = 21;
     *
     * B. INSTANCE INITIALIZER BLOCK
     *
     *     {
     *         System.out.println("Instance initialization");
     *     }
     *
     * C. CONSTRUCTOR INITIALIZATION
     *
     *     Student() {
     *         age = 21;
     *     }
     *
     * SIMPLIFIED INITIALIZATION ORDER:
     *
     * 1. Memory for the object is allocated and fields receive
     *    default values.
     *
     * 2. The superclass constructor chain is processed.
     *
     * 3. Instance field initializers and instance initializer
     *    blocks execute in textual order for each class,
     *    following the language's constructor initialization rules.
     *
     * 4. The constructor body executes.
     *
     * IMPORTANT:
     *
     * Initialization across inheritance is more detailed than
     * simply saying "parent constructor, then child constructor".
     * Field initializers, initializer blocks and constructor
     * invocation order must all be considered.
     *
     * Static initialization is separate from instance initialization.
     *
     *
     * =================================================================
     * 13. OBJECT LIFECYCLE
     * =================================================================
     *
     * An object's lifecycle broadly includes:
     *
     * 1. Creation
     * 2. Initialization
     * 3. Usage
     * 4. Becoming unreachable
     * 5. Garbage collection, if and when performed
     *
     * A. CREATION
     *
     *     Student s = new Student();
     *
     * An object is created and a reference is returned.
     *
     * B. USAGE
     *
     *     s.display();
     *
     * C. BECOMING UNREACHABLE
     *
     *     s = null;
     *
     * This removes the reference held by s.
     *
     * It does not necessarily mean the object is unreachable,
     * because other references may still point to it.
     *
     * D. GARBAGE COLLECTION
     *
     * The JVM may reclaim memory occupied by objects that are
     * no longer reachable, according to garbage-collection rules.
     *
     * IMPORTANT:
     *
     * 1. Java does not provide deterministic object destruction.
     * 2. Garbage collection timing is not guaranteed.
     * 3. System.gc() is only a request or suggestion to the JVM.
     * 4. Unreachable objects are eligible for collection, but
     *    collection is not guaranteed to occur immediately.
     * 5. Garbage collection does not replace explicit resource cleanup.
     *
     * Use try-with-resources for resources such as files,
     * database connections and streams.
     *
     *
     * =================================================================
     * 14. OBJECT RELATIONSHIPS
     * =================================================================
     *
     * Objects can be connected to represent relationships
     * in a real-world application.
     *
     * Common relationship concepts:
     *
     * 1. Association
     * 2. Aggregation
     * 3. Composition
     *
     * A. ASSOCIATION
     *
     * Association represents a relationship between independent
     * objects.
     *
     * Example:
     *
     *     class Teacher {
     *     }
     *
     *     class Student {
     *
     *         Teacher teacher;
     *     }
     *
     * A Student has a relationship with a Teacher.
     *
     * B. AGGREGATION
     *
     * Aggregation is commonly described as a weak whole-part
     * relationship where the contained object can exist independently
     * of the containing object.
     *
     * Example:
     *
     *     class Department {
     *
     *         List<Employee> employees;
     *     }
     *
     * Employees can exist independently of a particular Department.
     *
     * C. COMPOSITION
     *
     * Composition is commonly described as a strong whole-part
     * relationship where the part's lifecycle is conceptually
     * controlled by the whole.
     *
     * Example:
     *
     *     class House {
     *
     *         private final Room room = new Room();
     *     }
     *
     * The House creates and owns its Room in this simple model.
     *
     * IMPORTANT:
     *
     * Java does not provide special keywords for association,
     * aggregation or composition.
     *
     * These are object-oriented design concepts.
     * Their meaning depends on object ownership and lifecycle
     * design, not merely on declaring a reference field.
     *
     *
     * =================================================================
     * 15. OBJECT CLASS
     * =================================================================
     *
     * java.lang.Object is the root superclass of the Java class
     * hierarchy.
     *
     * Every class directly or indirectly extends Object,
     * except interfaces and certain special type declarations.
     *
     * If a class does not explicitly extend another class,
     * it implicitly extends Object.
     *
     * Example:
     *
     *     class Student {
     *     }
     *
     * Conceptually:
     *
     *     class Student extends Object {
     *     }
     *
     * IMPORTANT OBJECT METHODS:
     *
     * 1. toString()
     *
     *    Returns a string representation of the object.
     *
     *    Default implementation is based on the class name
     *    and object's hash code representation.
     *
     * 2. equals(Object obj)
     *
     *    Tests logical equality according to the implementation.
     *
     *    The default implementation in Object compares identity.
     *
     * 3. hashCode()
     *
     *    Returns an integer hash code.
     *
     *    Equal objects must have equal hash codes.
     *    Unequal objects may still have the same hash code.
     *
     * 4. getClass()
     *
     *    Returns the runtime Class object representing the object's
     *    actual class.
     *
     * 5. clone()
     *
     *    Supports a field-by-field copy mechanism under its
     *    documented rules.
     *
     *    The class generally needs to implement Cloneable for
     *    Object.clone() to succeed.
     *
     * 6. wait()
     *
     *    Causes the current thread to wait on an object's monitor.
     *
     * 7. notify()
     *
     *    Wakes one thread waiting on that object's monitor.
     *
     * 8. notifyAll()
     *
     *    Wakes all threads waiting on that object's monitor.
     *
     * 9. finalize()
     *
     *    Deprecated for removal. Do not use it for resource cleanup.
     *
     * IMPORTANT:
     *
     * wait(), notify() and notifyAll() must be called while
     * owning the corresponding object's monitor.
     *
     * The equals() and hashCode() contract is especially important
     * when objects are used as keys in HashMap or elements in HashSet.
     *
     *
     * =================================================================
     * 16. JAVA IS NOT PURELY OBJECT-ORIENTED
     * =================================================================
     *
     * Java is commonly described as an object-oriented language,
     * but it is not considered purely object-oriented.
     *
     * REASONS:
     *
     * 1. Java supports primitive data types:
     *
     *    byte, short, int, long, float, double, char, boolean.
     *
     * 2. Primitive values are not objects.
     *
     * 3. Java supports static fields and methods that can be
     *    accessed without creating an object.
     *
     * 4. Java supports procedural-style operations through methods
     *    and static utility classes.
     *
     * WRAPPER CLASSES:
     *
     * Java provides wrapper classes for primitive types:
     *
     *     int     -> Integer
     *     char    -> Character
     *     double  -> Double
     *     boolean -> Boolean
     *
     * Wrapper classes allow primitive values to be represented
     * as objects when needed.
     *
     * Autoboxing and unboxing simplify conversions between
     * primitives and their corresponding wrapper types.
     *
     * Detailed coverage belongs in WrapperClasses.java.
     *
     *
     * =================================================================
     * 17. ADVANTAGES OF OOP
     * =================================================================
     *
     * 1. MODULARITY
     *
     *    Applications can be divided into independent classes
     *    and components.
     *
     * 2. REUSABILITY
     *
     *    Existing classes and components can be reused.
     *
     * 3. MAINTAINABILITY
     *
     *    Well-designed objects make changes easier to manage.
     *
     * 4. ENCAPSULATION
     *
     *    Internal state can be protected through controlled access.
     *
     * 5. EXTENSIBILITY
     *
     *    New functionality can be added through composition,
     *    interfaces and inheritance.
     *
     * 6. ABSTRACTION
     *
     *    Users can work with essential operations without
     *    depending on implementation details.
     *
     * 7. POLYMORPHISM
     *
     *    Different implementations can be used through common
     *    interfaces or superclass references.
     *
     * 8. REAL-WORLD MODELING
     *
     *    Objects can represent entities and relationships
     *    found in real-world domains.
     *
     *
     * =================================================================
     * 18. LIMITATIONS OF OOP
     * =================================================================
     *
     * 1. DESIGN COMPLEXITY
     *
     *    Poorly designed class hierarchies can make applications
     *    difficult to understand.
     *
     * 2. OVERENGINEERING
     *
     *    Creating unnecessary classes and abstractions increases
     *    complexity.
     *
     * 3. MEMORY OVERHEAD
     *
     *    Object allocation and references can introduce overhead.
     *
     * 4. PERFORMANCE OVERHEAD
     *
     *    Some object-oriented designs introduce additional
     *    indirection or allocation costs.
     *
     * 5. INHERITANCE COUPLING
     *
     *    Deep inheritance hierarchies can make changes difficult.
     *
     * 6. NOT ALWAYS THE BEST FIT
     *
     *    Some problems are better expressed using procedural,
     *    functional or data-oriented approaches.
     *
     * IMPORTANT:
     *
     * OOP is a design approach, not a guarantee of better
     * performance or maintainability.
     *
     *
     * =================================================================
     * 19. REAL-WORLD OOP EXAMPLE
     * =================================================================
     *
     * Consider a simple banking application.
     *
     * ENTITY: BankAccount
     *
     * STATE:
     *
     *     accountNumber
     *     accountHolder
     *     balance
     *
     * BEHAVIOUR:
     *
     *     deposit()
     *     withdraw()
     *     getBalance()
     *
     * Example:
     *
     *     class BankAccount {
     *
     *         private String accountNumber;
     *         private String accountHolder;
     *         private double balance;
     *
     *         BankAccount(String number, String holder,
     *                     double initialBalance) {
     *
     *             accountNumber = number;
     *             accountHolder = holder;
     *             balance = initialBalance;
     *         }
     *
     *         void deposit(double amount) {
     *
     *             if (amount > 0) {
     *                 balance += amount;
     *             }
     *         }
     *
     *         boolean withdraw(double amount) {
     *
     *             if (amount > 0 && amount <= balance) {
     *                 balance -= amount;
     *                 return true;
     *             }
     *
     *             return false;
     *         }
     *
     *         double getBalance() {
     *             return balance;
     *         }
     *     }
     *
     * Object creation:
     *
     *     BankAccount account =
     *         new BankAccount("A101", "Vinay", 5000);
     *
     *     account.deposit(1000);
     *     account.withdraw(2000);
     *
     *     System.out.println(account.getBalance());
     *
     * This example demonstrates:
     *
     * - Class definition
     * - Object creation
     * - Instance fields
     * - Constructor initialization
     * - Instance methods
     * - Object state
     * - Object behaviour
     * - Encapsulation
     *
     * NOTE:
     *
     * A production banking application would need additional
     * validation, transaction handling, concurrency control,
     * persistence and security.
     *
     *
     * =================================================================
     * 20. COMMON MISTAKES AND CONFUSIONS
     * =================================================================
     *
     * 1. Class vs Object:
     *
     *    A class defines a type; an object is an instance.
     *
     * 2. Reference vs Object:
     *
     *    A reference variable identifies an object; it is not
     *    the object itself.
     *
     * 3. Constructor vs Method:
     *
     *    A constructor initializes an object; a method defines
     *    behaviour.
     *
     * 4. Default vs No-Argument Constructor:
     *
     *    A default constructor is compiler-provided only when
     *    no constructor is declared.
     *
     * 5. Object Assignment:
     *
     *    Assigning one reference to another does not create
     *    another object.
     *
     * 6. Static vs Instance:
     *
     *    Static members belong to the class; instance members
     *    belong to individual objects.
     *
     * 7. Null vs Empty Object:
     *
     *    null is not an object with empty fields.
     *
     * 8. == vs equals():
     *
     *    == compares reference identity for reference operands.
     *    equals() checks logical equality according to its
     *    implementation.
     *
     * 9. Garbage Collection:
     *
     *    Setting a reference to null does not guarantee immediate
     *    garbage collection.
     *
     * 10. Constructor Inheritance:
     *
     *     Constructors are not inherited by subclasses.
     *
     * 11. Java OOP:
     *
     *     Java supports primitive values as well as objects.
     *
     * 12. Object Initialization:
     *
     *     Fields receive default values; local variables must be
     *     definitely assigned before use.
     *
     *
     * =================================================================
     * 21. INTERVIEW QUESTIONS
     * =================================================================
     *
     * Q1. What is OOP?
     *
     * A programming paradigm that organizes programs around
     * objects containing state and behaviour.
     *
     * Q2. What is a class?
     *
     * A reference type definition used to describe objects.
     *
     * Q3. What is an object?
     *
     * A runtime instance of a class.
     *
     * Q4. What are the four pillars of OOP?
     *
     * Encapsulation, Inheritance, Polymorphism and Abstraction.
     *
     * Q5. What is the difference between a class and an object?
     *
     * A class defines a type; an object is an instance of that type.
     *
     * Q6. What is a constructor?
     *
     * A special member used to initialize objects during
     * construction.
     *
     * Q7. Does a constructor have a return type?
     *
     * No.
     *
     * Q8. When does the compiler provide a default constructor?
     *
     * When no constructor is explicitly declared in the class.
     *
     * Q9. Can constructors be overloaded?
     *
     * Yes.
     *
     * Q10. Can constructors be inherited?
     *
     * No.
     *
     * Q11. What is this?
     *
     * A reference to the current object in an instance context.
     *
     * Q12. Can this be used in a static method?
     *
     * No.
     *
     * Q13. What happens when one reference is assigned to another?
     *
     * Both references can identify the same object.
     *
     * Q14. What is object identity?
     *
     * The characteristic that distinguishes one object instance
     * from another.
     *
     * Q15. What is the root class of Java's class hierarchy?
     *
     * java.lang.Object.
     *
     * Q16. What is the difference between == and equals()?
     *
     * == compares reference identity for references; equals()
     * checks logical equality according to its implementation.
     *
     * Q17. What is garbage collection?
     *
     * Automatic memory reclamation for eligible unreachable objects.
     *
     * Q18. Is Java purely object-oriented?
     *
     * No. It supports primitive types and static members.
     *
     * Q19. What is association?
     *
     * A relationship between independent objects.
     *
     * Q20. What is the difference between aggregation and composition?
     *
     * Aggregation represents a weaker whole-part relationship;
     * composition represents stronger ownership and lifecycle
     * dependence.
     *
     * Q21. Can an object have multiple references?
     *
     * Yes. Multiple reference variables can identify the same object.
     *
     * Q22. Does null represent an empty object?
     *
     * No. It represents the absence of an object reference.
     *
     *
     * =================================================================
     * 22. FINAL REVISION CHECKLIST
     * =================================================================
     *
     * OOP FUNDAMENTALS:
     *
     * [ ] Definition and purpose of OOP
     * [ ] Programming paradigms
     * [ ] Procedural vs OOP
     * [ ] State, behaviour and identity
     * [ ] Four pillars of OOP
     *
     * CLASSES AND OBJECTS:
     *
     * [ ] Class definition and syntax
     * [ ] Class members
     * [ ] Object creation
     * [ ] Class vs object
     * [ ] Multiple object instances
     * [ ] Object references
     * [ ] Reference assignment
     * [ ] null references
     *
     * CONSTRUCTORS:
     *
     * [ ] Default constructor
     * [ ] No-argument constructor
     * [ ] Parameterized constructor
     * [ ] Constructor overloading
     * [ ] Constructor chaining
     * [ ] this() and super()
     * [ ] Constructor vs method
     *
     * OBJECT INITIALIZATION:
     *
     * [ ] Default field values
     * [ ] Field initializers
     * [ ] Instance initializer blocks
     * [ ] Constructor execution
     * [ ] Initialization order
     *
     * OBJECT-ORIENTED DESIGN:
     *
     * [ ] this keyword
     * [ ] Static vs instance members
     * [ ] Association
     * [ ] Aggregation
     * [ ] Composition
     * [ ] Object class methods
     * [ ] Object lifecycle
     * [ ] Garbage collection
     *
     * UNDERSTANDING:
     *
     * [ ] Java is not purely object-oriented
     * [ ] Advantages and limitations of OOP
     * [ ] Real-world OOP modeling
     * [ ] Common mistakes
     * [ ] Interview questions
     *
     *
     * =================================================================
     *                         END OF OOP BASICS
     * =================================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Oops Basics , Happy Learning ...!");
    }

}
