
public class Prototype {

    /*
     * ============================================================
     * 1. INTRODUCTION
     * ============================================================
     *
     * Imagine you are developing a game.
     *
     * You need to create 100 enemy objects.
     *
     * Every enemy has:
     *   - Health
     *   - Speed
     *   - Weapon
     *   - Appearance
     *   - Configuration
     *
     * One approach is to create every enemy from scratch.
     *
     * But what if creating an object is expensive?
     *
     * For example:
     *   - It requires database calls.
     *   - It loads large configuration files.
     *   - It performs complex initialization.
     *   - It creates many dependent objects.
     *
     * Repeating the same construction process can be inefficient.
     *
     * Instead, we can create one configured object and make
     * copies of it whenever needed.
     *
     * This is the idea behind the Prototype pattern.
     *
     *
     * DEFINITION:
     *
     * Prototype is a creational design pattern that creates
     * new objects by copying an existing object, known as
     * the prototype.
     *
     *
     * ============================================================
     * 2. WHY DO WE NEED PROTOTYPE?
     * ============================================================
     *
     * Consider:
     *
     * Enemy enemy1 = new Enemy(
     *     100,
     *     20,
     *     "Sword"
     * );
     *
     * Enemy enemy2 = new Enemy(
     *     100,
     *     20,
     *     "Sword"
     * );
     *
     * Enemy enemy3 = new Enemy(
     *     100,
     *     20,
     *     "Sword"
     * );
     *
     * We repeatedly pass the same configuration.
     *
     * Instead:
     *
     * Enemy prototype = new Enemy(100, 20, "Sword");
     *
     * Enemy enemy1 = prototype.clone();
     * Enemy enemy2 = prototype.clone();
     * Enemy enemy3 = prototype.clone();
     *
     * We create new objects by copying an existing object.
     *
     * IMPORTANT:
     *
     * Prototype does not guarantee that copying is faster than
     * constructing a new object.
     *
     * It is useful when copying an initialized object is simpler
     * or more efficient than repeating its construction process.
     *
     *
     * ============================================================
     * 3. REAL-WORLD ANALOGY
     * ============================================================
     *
     * Imagine creating a document template.
     *
     * You prepare one document containing:
     *
     *   - Company logo
     *   - Header
     *   - Footer
     *   - Formatting
     *   - Standard sections
     *
     * Whenever a new document is required, you make a copy
     * of the template and modify its contents.
     *
     * You don't redesign the entire document every time.
     *
     * The original template acts as the prototype.
     *
     *
     * ============================================================
     * 4. STRUCTURE AND PARTICIPANTS
     * ============================================================
     *
     * Main participants:
     *
     * 1. Prototype:
     *    Declares an operation for copying itself.
     *
     * 2. Concrete Prototype:
     *    Implements the copying operation.
     *
     * 3. Client:
     *    Requests new objects by cloning existing prototypes.
     *
     * 4. Prototype Registry (optional):
     *    Stores prototypes so clients can retrieve and clone them.
     *
     *
     * ============================================================
     * 5. PROTOTYPE USING CLONEABLE
     * ============================================================
     *
     * Java provides:
     *
     * java.lang.Cloneable
     *
     * It is a marker interface.
     *
     * It indicates that an object permits cloning through
     * Object.clone().
     *
     * Object.clone() is protected.
     *
     * A class normally overrides clone() and exposes it
     * with an appropriate access level.
     *
     *
     * COMPLETE EXAMPLE:
     *
     * class Enemy implements Cloneable {
     *
     *     private int health;
     *     private int speed;
     *     private String weapon;
     *
     *     Enemy(int health, int speed, String weapon) {
     *         this.health = health;
     *         this.speed = speed;
     *         this.weapon = weapon;
     *     }
     *
     *     @Override
     *     public Enemy clone() {
     *
     *         try {
     *             return (Enemy) super.clone();
     *         }
     *         catch (CloneNotSupportedException e) {
     *             throw new AssertionError(e);
     *         }
     *     }
     *
     *     void showDetails() {
     *         System.out.println("Health: " + health);
     *         System.out.println("Speed: " + speed);
     *         System.out.println("Weapon: " + weapon);
     *     }
     *
     *     void setHealth(int health) {
     *         this.health = health;
     *     }
     * }
     *
     *
     * USAGE:
     *
     * Enemy prototype = new Enemy(100, 20, "Sword");
     *
     * Enemy enemy1 = prototype.clone();
     * Enemy enemy2 = prototype.clone();
     *
     * enemy1.setHealth(50);
     *
     * prototype.showDetails();
     * enemy1.showDetails();
     * enemy2.showDetails();
     *
     *
     * OUTPUT:
     *
     * Prototype:
     * Health: 100
     * Speed: 20
     * Weapon: Sword
     *
     * Enemy 1:
     * Health: 50
     * Speed: 20
     * Weapon: Sword
     *
     * Enemy 2:
     * Health: 100
     * Speed: 20
     * Weapon: Sword
     *
     *
     * ============================================================
     * 6. WHAT DOES super.clone() DO?
     * ============================================================
     *
     * Object is the parent class of every Java class.
     *
     * It provides a protected clone() method.
     *
     * Calling:
     *
     * super.clone()
     *
     * requests a field-by-field copy of the current object.
     *
     * For ordinary objects, this produces a shallow copy.
     *
     * It does not call the class constructor to initialize
     * the copied object.
     *
     * IMPORTANT:
     *
     * If the object does not implement Cloneable,
     * Object.clone() throws CloneNotSupportedException.
     *
     *
     * ============================================================
     * 7. SHALLOW COPY
     * ============================================================
     *
     * A shallow copy creates a new outer object but copies
     * field values directly.
     *
     * For primitive fields:
     *   Their values are copied.
     *
     * For reference fields:
     *   The references are copied, not the referenced objects.
     *
     * Example:
     *
     * class Address {
     *     String city;
     * }
     *
     * class Student implements Cloneable {
     *
     *     String name;
     *     Address address;
     *
     *     @Override
     *     public Student clone() {
     *         try {
     *             return (Student) super.clone();
     *         }
     *         catch (CloneNotSupportedException e) {
     *             throw new AssertionError(e);
     *         }
     *     }
     * }
     *
     * Student s1 = new Student();
     * s1.name = "Vinay";
     * s1.address = new Address();
     * s1.address.city = "Hyderabad";
     *
     * Student s2 = s1.clone();
     *
     * Now:
     *
     * s1 != s2
     *
     * But:
     *
     * s1.address == s2.address
     *
     * Both Student objects refer to the SAME Address object.
     *
     * Therefore:
     *
     * s2.address.city = "Vizag";
     *
     * Also changes the city observed through s1.address.
     *
     *
     * ============================================================
     * 8. DEEP COPY
     * ============================================================
     *
     * A deep copy creates a new outer object and also copies
     * the mutable objects referenced by it.
     *
     * Example:
     *
     * class Address {
     *     String city;
     *
     *     Address(String city) {
     *         this.city = city;
     *     }
     *
     *     Address copy() {
     *         return new Address(city);
     *     }
     * }
     *
     * class Student implements Cloneable {
     *
     *     String name;
     *     Address address;
     *
     *     @Override
     *     public Student clone() {
     *
     *         try {
     *             Student copy = (Student) super.clone();
     *
     *             copy.address = this.address.copy();
     *
     *             return copy;
     *         }
     *         catch (CloneNotSupportedException e) {
     *             throw new AssertionError(e);
     *         }
     *     }
     * }
     *
     * Student s2 = s1.clone();
     *
     * Now:
     *
     * s1 != s2
     *
     * s1.address != s2.address
     *
     * Both Student objects have independent Address objects.
     *
     * IMPORTANT:
     *
     * Deep copying must consider the entire object graph.
     *
     * Nested objects may themselves contain references to
     * other mutable objects.
     *
     * Also consider:
     *   - Circular references
     *   - Shared references
     *   - Immutable objects
     *   - Collections
     *   - Resource objects
     *
     *
     * ============================================================
     * 9. SHALLOW COPY VS DEEP COPY
     * ============================================================
     *
     * SHALLOW COPY:
     *
     * - Creates a new outer object.
     * - Copies primitive field values.
     * - Copies references as references.
     * - Nested mutable objects may be shared.
     *
     * DEEP COPY:
     *
     * - Creates a new outer object.
     * - Copies nested mutable objects as well.
     * - Attempts to make the copied object graph independent.
     * - Requires more careful implementation.
     *
     * REMEMBER:
     *
     * Shallow copy -> New object, shared referenced objects.
     *
     * Deep copy -> New object, independently copied mutable
     *              referenced objects.
     *
     *
     * ============================================================
     * 10. CLONEABLE AND OBJECT.CLONE()
     * ============================================================
     *
     * Cloneable:
     *
     * - java.lang.Cloneable is a marker interface.
     * - It declares no clone() method.
     * - It permits Object.clone() to perform cloning.
     *
     * Object.clone():
     *
     * - Is protected.
     * - Performs a shallow field-by-field copy.
     * - Does not invoke the constructor.
     * - Can throw CloneNotSupportedException.
     *
     * IMPORTANT:
     *
     * Implementing Cloneable does not automatically make
     * clone() public.
     *
     * You generally override clone() if clients should be able
     * to call it.
     *
     *
     * ============================================================
     * 11. ALTERNATIVES TO CLONE()
     * ============================================================
     *
     * Java's clone mechanism has several design limitations.
     *
     * A copy constructor or explicit copy method is often easier
     * to understand and maintain.
     *
     *
     * COPY CONSTRUCTOR:
     *
     * class Employee {
     *
     *     String name;
     *     int age;
     *
     *     Employee(String name, int age) {
     *         this.name = name;
     *         this.age = age;
     *     }
     *
     *     Employee(Employee other) {
     *         this.name = other.name;
     *         this.age = other.age;
     *     }
     * }
     *
     * Usage:
     *
     * Employee e1 = new Employee("Vinay", 22);
     * Employee e2 = new Employee(e1);
     *
     *
     * EXPLICIT COPY METHOD:
     *
     * Employee copy = original.copy();
     *
     *
     * ADVANTAGES:
     *
     * - Clear control over copied fields.
     * - No CloneNotSupportedException.
     * - Easier to implement deep copying.
     * - Can enforce validation and invariants.
     *
     * NOTE:
     *
     * A copy constructor is an alternative copying technique.
     * It is not automatically the GoF Prototype pattern.
     * The overall design must use existing objects as prototypes
     * to create new objects.
     *
     *
     * ============================================================
     * 12. PROTOTYPE REGISTRY
     * ============================================================
     *
     * A Prototype Registry stores commonly used prototype objects.
     *
     * The client requests a prototype using a key and receives
     * a copy of the stored object.
     *
     * Example:
     *
     * Map<String, Enemy> registry = new HashMap<>();
     *
     * registry.put(
     *     "basic",
     *     new Enemy(100, 10, "Sword")
     * );
     *
     * registry.put(
     *     "boss",
     *     new Enemy(500, 5, "Axe")
     * );
     *
     * Enemy basicEnemy = registry.get("basic").clone();
     *
     * Enemy bossEnemy = registry.get("boss").clone();
     *
     * The registry avoids repeatedly configuring the same
     * prototype.
     *
     * IMPORTANT:
     *
     * A production registry should consider:
     * - Missing keys.
     * - Defensive copying.
     * - Thread safety.
     * - Whether prototypes can be modified.
     *
     *
     * ============================================================
     * 13. PROTOTYPE VS FACTORY METHOD
     * ============================================================
     *
     * FACTORY METHOD:
     *
     * Creates objects through a factory method.
     * Subclasses can decide which concrete product to create.
     *
     * PROTOTYPE:
     *
     * Creates objects by copying existing prototype instances.
     *
     * Example:
     *
     * Factory Method:
     * A NotificationCreator creates an EmailNotification.
     *
     * Prototype:
     * An existing configured Enemy is cloned to create another.
     *
     *
     * ============================================================
     * 14. PROTOTYPE VS BUILDER
     * ============================================================
     *
     * BUILDER:
     *
     * Constructs a complex object step by step.
     *
     * PROTOTYPE:
     *
     * Creates a new object by copying an existing object.
     *
     * Example:
     *
     * Builder:
     * Configure a Computer with CPU, RAM and Storage.
     *
     * Prototype:
     * Copy an already-configured Computer.
     *
     *
     * ============================================================
     * 15. PROTOTYPE VS COPY CONSTRUCTOR
     * ============================================================
     *
     * COPY CONSTRUCTOR:
     *
     * A constructor receives an existing object and copies
     * its relevant state.
     *
     * PROTOTYPE:
     *
     * A design approach in which an existing object acts as
     * a template for creating new objects.
     *
     * A copy constructor can be used to implement copying
     * within a Prototype-based design.
     *
     *
     * ============================================================
     * 16. REAL-WORLD APPLICATIONS
     * ============================================================
     *
     * 1. Game development:
     *    Copy configured enemies, characters and game objects.
     *
     * 2. Document systems:
     *    Duplicate templates with predefined formatting.
     *
     * 3. Graphics applications:
     *    Duplicate configured shapes and drawing objects.
     *
     * 4. Configuration systems:
     *    Copy predefined configurations and customize them.
     *
     * 5. Testing:
     *    Create test objects based on prepared templates.
     *
     *
     * ============================================================
     * 17. ADVANTAGES
     * ============================================================
     *
     * 1. Avoids repeating complex initialization.
     * 2. Creates objects from existing configured instances.
     * 3. Can reduce coupling to concrete construction classes.
     * 4. Allows runtime selection of prototypes.
     * 5. Supports creating variations from a common template.
     *
     *
     * ============================================================
     * 18. DISADVANTAGES
     * ============================================================
     *
     * 1. Deep copying can be complicated.
     * 2. Circular references require special handling.
     * 3. Mutable nested objects can cause accidental sharing.
     * 4. Java's clone mechanism has design limitations.
     * 5. Copying may not be cheaper than normal construction.
     *
     *
     * ============================================================
     * 19. WHEN SHOULD WE USE PROTOTYPE?
     * ============================================================
     *
     * Use Prototype when:
     *
     * - Object creation is complex or expensive.
     * - You already have a suitable configured object.
     * - You need many similar objects with small variations.
     * - You want to avoid depending on concrete construction logic.
     *
     * Avoid it when:
     *
     * - Objects are simple and cheap to create.
     * - Copying involves complicated object graphs.
     * - Objects contain resources that cannot safely be duplicated.
     *
     *
     * ============================================================
     * 20. COMMON MISCONCEPTIONS
     * ============================================================
     *
     * 1. Prototype always makes object creation faster.
     *    FALSE. Performance depends on construction and copying cost.
     *
     * 2. clone() always creates a deep copy.
     *    FALSE. Object.clone() performs a shallow copy.
     *
     * 3. Cloneable declares the clone() method.
     *    FALSE. It is a marker interface.
     *
     * 4. Cloning calls the constructor.
     *    FALSE. Object.clone() does not invoke the constructor.
     *
     * 5. A copy constructor is automatically the Prototype pattern.
     *    FALSE. It is a copying technique; the overall design matters.
     *
     * 6. A cloned object shares no references with the original.
     *    FALSE. Shallow cloning can share referenced objects.
     *
     *
     * ============================================================
     * 21. INTERVIEW QUESTIONS
     * ============================================================
     *
     * Q1. What is Prototype?
     *
     * A: A creational design pattern that creates new objects
     *    by copying existing prototype instances.
     *
     * Q2. What is the purpose of Cloneable?
     *
     * A: It is a marker interface that permits Object.clone()
     *    to perform cloning for an implementing class.
     *
     * Q3. What is a shallow copy?
     *
     * A: A copy that creates a new outer object but copies
     *    reference fields as references.
     *
     * Q4. What is a deep copy?
     *
     * A: A copy that also creates independent copies of
     *    referenced mutable objects.
     *
     * Q5. Does Object.clone() call the constructor?
     *
     * A: No. It performs a field-by-field copy.
     *
     * Q6. Does Cloneable contain a clone() method?
     *
     * A: No. It is a marker interface.
     *
     * Q7. What are alternatives to clone()?
     *
     * A: Copy constructors, explicit copy methods and carefully
     *    designed serialization-based copying.
     *
     * Q8. What is a Prototype Registry?
     *
     * A: A collection of reusable prototype instances from which
     *    clients can request copies.
     *
     *
     * ============================================================
     * 22. QUICK REVISION
     * ============================================================
     *
     * PATTERN: Prototype
     *
     * CATEGORY: Creational
     *
     * PURPOSE:
     * Create objects by copying existing objects.
     *
     * MAIN IDEA:
     * Use an existing object as a template.
     *
     * JAVA MECHANISM:
     * Cloneable and Object.clone() are one possible implementation.
     *
     * COPY TYPES:
     * Shallow Copy and Deep Copy.
     *
     * ALTERNATIVES:
     * Copy Constructor and Explicit Copy Method.
     *
     * OPTIONAL COMPONENT:
     * Prototype Registry.
     *
     * REMEMBER:
     *
     * Builder -> Construct step by step.
     *
     * Prototype -> Copy an existing object.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Prototype Design Pattern , Happy Learning ..!");
    }
}
