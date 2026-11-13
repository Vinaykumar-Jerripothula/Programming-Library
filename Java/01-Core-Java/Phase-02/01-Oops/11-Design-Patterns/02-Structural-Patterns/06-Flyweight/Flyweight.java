
public class Flyweight {

    /*
     * ============================================================
     * 1. INTRODUCTION
     * ============================================================
     *
     * Imagine you are developing a game that contains a forest.
     *
     * The forest has 10,000 trees.
     *
     * Every tree has information such as:
     *
     *   - Tree type
     *   - Color
     *   - Texture
     *   - Shape
     *   - Position (x, y)
     *
     * Consider this:
     *
     * All oak trees share the same type, color, texture and shape.
     *
     * But every oak tree has a different position.
     *
     * If we create 10,000 completely independent objects,
     * we may unnecessarily duplicate the same data thousands
     * of times.
     *
     * Instead, what if we store the common tree information
     * only once and share it among all oak tree objects?
     *
     * Each tree would only need to store its individual position.
     *
     * This can significantly reduce memory consumption.
     *
     * This is the central idea behind Flyweight.
     *
     *
     * DEFINITION:
     *
     * Flyweight is a structural design pattern that minimizes
     * memory usage by sharing common, reusable object state
     * among multiple objects.
     *
     *
     * SIMPLE DEFINITION:
     *
     * Share common data instead of storing duplicate copies
     * in every object.
     *
     *
     * ============================================================
     * 2. WHY DO WE NEED FLYWEIGHT?
     * ============================================================
     *
     * Consider creating 10,000 tree objects.
     *
     * Without Flyweight:
     *
     * Tree 1 -> Oak, Green, Oak Texture, Position (10, 20)
     * Tree 2 -> Oak, Green, Oak Texture, Position (30, 40)
     * Tree 3 -> Oak, Green, Oak Texture, Position (50, 60)
     *
     * Every object stores the same oak-related information.
     *
     * With Flyweight:
     *
     * Shared Oak Data:
     *   - Type: Oak
     *   - Color: Green
     *   - Texture: Oak Texture
     *
     * Individual Tree Objects:
     *   - Position (10, 20)
     *   - Position (30, 40)
     *   - Position (50, 60)
     *
     * The common information is shared.
     *
     * Only individual information is stored separately.
     *
     * This reduces duplicated state.
     *
     *
     * ============================================================
     * 3. REAL-WORLD ANALOGY
     * ============================================================
     *
     * Imagine a classroom with 100 students.
     *
     * Every student belongs to the same college.
     *
     * The college information includes:
     *
     *   - College name
     *   - College address
     *   - College code
     *
     * Each student has individual information:
     *
     *   - Student name
     *   - Roll number
     *   - Marks
     *
     * Instead of duplicating college information in every
     * student object, the application can share one College
     * object among all students.
     *
     * Remember:
     *
     * Common information -> Shared.
     *
     * Individual information -> Stored separately.
     *
     *
     * ============================================================
     * 4. INTRINSIC STATE VS EXTRINSIC STATE
     * ============================================================
     *
     * This is the MOST IMPORTANT concept in Flyweight.
     *
     * Flyweight divides object state into two categories.
     *
     *
     * A. INTRINSIC STATE
     *
     * Information that is common and independent of the
     * particular context in which the object is used.
     *
     * Examples:
     *
     *   - Tree type
     *   - Tree color
     *   - Tree texture
     *   - Font family
     *   - Character glyph
     *
     * Intrinsic state is stored inside the shared Flyweight.
     *
     *
     * B. EXTRINSIC STATE
     *
     * Information that depends on the particular context
     * in which the object is used.
     *
     * Examples:
     *
     *   - Tree position
     *   - Student roll number
     *   - Text character position
     *   - Object-specific coordinates
     *
     * Extrinsic state is supplied by the client when needed.
     *
     *
     * REMEMBER:
     *
     * Intrinsic = Shared state.
     *
     * Extrinsic = Context-specific state.
     *
     *
     * ============================================================
     * 5. STRUCTURE AND PARTICIPANTS
     * ============================================================
     *
     * Main participants:
     *
     * 1. Flyweight:
     *    Defines the interface through which shared objects
     *    can be used.
     *
     * 2. Concrete Flyweight:
     *    Stores intrinsic state that can be shared.
     *
     * 3. Flyweight Factory:
     *    Creates and manages reusable Flyweight objects.
     *
     * 4. Client:
     *    Maintains or supplies extrinsic state and uses
     *    Flyweight objects.
     *
     *
     * BASIC STRUCTURE:
     *
     *                 Client
     *                   |
     *                   v
     *             Flyweight Factory
     *                   |
     *                   v
     *             Concrete Flyweight
     *                   |
     *                   v
     *              Shared State
     *
     * Client supplies extrinsic state when using the Flyweight.
     *
     *
     * ============================================================
     * 6. COMPLETE JAVA IMPLEMENTATION
     * ============================================================
     *
     * We will implement the forest example.
     *
     *
     * STEP 1: Create the Flyweight interface.
     *
     * interface TreeType {
     *
     *     void display(int x, int y);
     * }
     *
     * The position is passed as an argument.
     *
     * It is not stored in the shared TreeType object.
     *
     *
     * STEP 2: Create the Concrete Flyweight.
     *
     * class ConcreteTreeType implements TreeType {
     *
     *     private final String name;
     *     private final String color;
     *     private final String texture;
     *
     *     public ConcreteTreeType(
     *         String name,
     *         String color,
     *         String texture
     *     ) {
     *         this.name = name;
     *         this.color = color;
     *         this.texture = texture;
     *     }
     *
     *     @Override
     *     public void display(int x, int y) {
     *
     *         System.out.println(
     *             name + " tree at (" + x + ", " + y + ")" +
     *             " | Color: " + color +
     *             " | Texture: " + texture
     *         );
     *     }
     * }
     *
     *
     * STEP 3: Create the Flyweight Factory.
     *
     * import java.util.HashMap;
     * import java.util.Map;
     *
     * class TreeFactory {
     *
     *     private final Map<String, TreeType> treeTypes =
     *         new HashMap<>();
     *
     *     public TreeType getTreeType(
     *         String name,
     *         String color,
     *         String texture
     *     ) {
     *
     *         String key = name + "|" + color + "|" + texture;
     *
     *         if (!treeTypes.containsKey(key)) {
     *
     *             treeTypes.put(
     *                 key,
     *                 new ConcreteTreeType(name, color, texture)
     *             );
     *
     *             System.out.println(
     *                 "Creating new tree type: " + name
     *             );
     *         }
     *
     *         return treeTypes.get(key);
     *     }
     *
     *     public int getTotalTreeTypes() {
     *         return treeTypes.size();
     *     }
     * }
     *
     *
     * STEP 4: Create the Context object.
     *
     * class Tree {
     *
     *     private final int x;
     *     private final int y;
     *     private final TreeType type;
     *
     *     public Tree(int x, int y, TreeType type) {
     *         this.x = x;
     *         this.y = y;
     *         this.type = type;
     *     }
     *
     *     public void display() {
     *         type.display(x, y);
     *     }
     * }
     *
     * Notice:
     *
     * Tree stores the individual position.
     *
     * TreeType stores the shared information.
     *
     *
     * STEP 5: Use Flyweight.
     *
     * TreeFactory factory = new TreeFactory();
     *
     * Tree tree1 = new Tree(
     *     10, 20,
     *     factory.getTreeType("Oak", "Green", "Oak Texture")
     * );
     *
     * Tree tree2 = new Tree(
     *     30, 40,
     *     factory.getTreeType("Oak", "Green", "Oak Texture")
     * );
     *
     * Tree tree3 = new Tree(
     *     50, 60,
     *     factory.getTreeType("Pine", "Dark Green", "Pine Texture")
     * );
     *
     * tree1.display();
     * tree2.display();
     * tree3.display();
     *
     * System.out.println(
     *     "Unique tree types: " + factory.getTotalTreeTypes()
     * );
     *
     * System.out.println(
     *     "tree1 and tree2 share TreeType: " +
     *     (factory.getTreeType(
     *         "Oak", "Green", "Oak Texture"
     *     ) == factory.getTreeType(
     *         "Oak", "Green", "Oak Texture"
     *     ))
     * );
     *
     *
     * OUTPUT:
     *
     * Creating new tree type: Oak
     * Creating new tree type: Pine
     * Oak tree at (10, 20) | Color: Green | Texture: Oak Texture
     * Oak tree at (30, 40) | Color: Green | Texture: Oak Texture
     * Pine tree at (50, 60) | Color: Dark Green | Texture: Pine Texture
     * Unique tree types: 2
     * tree1 and tree2 share TreeType: true
     *
     *
     * ============================================================
     * 7. UNDERSTANDING THE EXECUTION FLOW
     * ============================================================
     *
     * Consider:
     *
     * factory.getTreeType("Oak", "Green", "Oak Texture");
     *
     * FIRST CALL:
     *
     * Step 1:
     * Factory checks whether the Oak tree type exists.
     *
     * Step 2:
     * It does not exist.
     *
     * Step 3:
     * Factory creates a ConcreteTreeType object.
     *
     * Step 4:
     * It stores the object in the Map.
     *
     * SECOND CALL WITH SAME DATA:
     *
     * Step 1:
     * Factory checks the Map again.
     *
     * Step 2:
     * The Oak tree type already exists.
     *
     * Step 3:
     * Factory returns the existing object.
     *
     * No new ConcreteTreeType object is created.
     *
     * This is object sharing.
     *
     *
     * ============================================================
     * 8. WHY DO WE NEED A FLYWEIGHT FACTORY?
     * ============================================================
     *
     * Without a factory:
     *
     * TreeType oak1 =
     *     new ConcreteTreeType("Oak", "Green", "Oak Texture");
     *
     * TreeType oak2 =
     *     new ConcreteTreeType("Oak", "Green", "Oak Texture");
     *
     * Both objects contain identical intrinsic state.
     *
     * The factory maintains a collection of existing Flyweights.
     *
     * Before creating a new object, it checks whether an
     * equivalent object is already available.
     *
     * If available:
     * Return the existing object.
     *
     * Otherwise:
     * Create and store a new object.
     *
     * IMPORTANT:
     *
     * A Flyweight Factory is a common participant in the pattern.
     * It is what manages sharing and reuse.
     *
     *
     * ============================================================
     * 9. WHY SHOULD FLYWEIGHT OBJECTS BE IMMUTABLE?
     * ============================================================
     *
     * Imagine tree1 and tree2 share the same TreeType object.
     *
     * If tree1 changes the shared tree color to Red,
     * tree2 would also observe Red.
     *
     * This may be incorrect because both objects share
     * the same intrinsic state.
     *
     * Therefore, shared Flyweight objects should generally
     * be immutable.
     *
     * In our example:
     *
     * private final String name;
     * private final String color;
     * private final String texture;
     *
     * We use final fields and provide no setters.
     *
     * This makes the shared state immutable for this example.
     *
     * IMPORTANT:
     *
     * A final reference does not automatically make the
     * referenced object deeply immutable.
     *
     * If a Flyweight stores mutable collections or objects,
     * those must also be protected from unwanted modification.
     *
     *
     * ============================================================
     * 10. FLYWEIGHT VS SINGLETON
     * ============================================================
     *
     * Both patterns can involve shared objects.
     *
     * But their purposes are different.
     *
     * SINGLETON:
     *
     * - Ensures a class has one instance within its defined scope.
     * - Provides a controlled way to access that instance.
     *
     * FLYWEIGHT:
     *
     * - Shares reusable objects to reduce memory consumption.
     * - Can maintain multiple Flyweight instances.
     * - Reuses an instance for equivalent intrinsic state.
     *
     * Example:
     *
     * Singleton:
     * One shared application configuration object.
     *
     * Flyweight:
     * Shared Oak, Pine and Banyan tree-type objects.
     *
     * REMEMBER:
     *
     * Singleton -> Control the number of instances.
     *
     * Flyweight -> Reduce memory through sharing.
     *
     *
     * ============================================================
     * 11. FLYWEIGHT VS PROTOTYPE
     * ============================================================
     *
     * PROTOTYPE:
     *
     * Creates new objects by copying existing objects.
     *
     * FLYWEIGHT:
     *
     * Reuses existing objects by sharing common state.
     *
     * Example:
     *
     * Prototype:
     * Copy an already-configured enemy to create another enemy.
     *
     * Flyweight:
     * Share the same tree-type information among thousands
     * of tree instances.
     *
     *
     * ============================================================
     * 12. FLYWEIGHT VS COMPOSITE
     * ============================================================
     *
     * COMPOSITE:
     *
     * Organizes objects into tree structures.
     *
     * FLYWEIGHT:
     *
     * Shares common object state to reduce memory consumption.
     *
     * These patterns can also be used together.
     *
     * For example:
     *
     * A large document may use Composite to represent
     * its structure and Flyweight to share repeated
     * formatting or character data.
     *
     *
     * ============================================================
     * 13. FLYWEIGHT IN JAVA
     * ============================================================
     *
     * Java provides examples of object reuse and sharing.
     *
     *
     * A. STRING INTERNING
     *
     * String s1 = "Java";
     * String s2 = "Java";
     *
     * The JVM can reuse the same interned String object
     * for equal string literals.
     *
     * System.out.println(s1 == s2);
     *
     * Output:
     * true
     *
     * String.intern() can also return the canonical
     * representation of a string.
     *
     * IMPORTANT:
     *
     * String interning demonstrates object sharing,
     * but it is not automatically a complete textbook
     * implementation of the GoF Flyweight pattern.
     *
     *
     * B. INTEGER CACHING
     *
     * Integer a = Integer.valueOf(100);
     * Integer b = Integer.valueOf(100);
     *
     * System.out.println(a == b);
     *
     * Output:
     * true
     *
     * Java caches certain Integer values.
     *
     * The guaranteed cache range includes -128 to 127.
     *
     * Implementations may cache additional values.
     *
     * Do not use == to compare numeric values in general.
     * Use equals() for value comparison.
     *
     * This is an example of object reuse, not by itself
     * a full GoF Flyweight implementation.
     *
     *
     * ============================================================
     * 14. FLYWEIGHT IN BACKEND DEVELOPMENT
     * ============================================================
     *
     * Flyweight can be useful when a backend application
     * processes large numbers of objects sharing common data.
     *
     * Examples:
     *
     * 1. Product catalogs:
     *
     *    Many products may share category metadata.
     *
     * 2. Text processing:
     *
     *    Reuse common formatting or character information.
     *
     * 3. Game servers:
     *
     *    Share immutable information about character types.
     *
     * 4. Configuration:
     *
     *    Reuse immutable configuration descriptors.
     *
     * 5. Graphics:
     *
     *    Share textures, shapes and rendering metadata.
     *
     * IMPORTANT:
     *
     * Sharing mutable objects between concurrent requests
     * can introduce race conditions.
     *
     * Prefer immutable shared Flyweights.
     *
     *
     * ============================================================
     * 15. FLYWEIGHT AND SOLID PRINCIPLES
     * ============================================================
     *
     * 1. SINGLE RESPONSIBILITY PRINCIPLE:
     *
     * The Flyweight Factory manages object reuse.
     *
     * Concrete Flyweights hold shared intrinsic state.
     *
     * Client objects manage or supply context-specific state.
     *
     *
     * 2. OPEN/CLOSED PRINCIPLE:
     *
     * New Flyweight types can be introduced through
     * implementations of the Flyweight abstraction.
     *
     *
     * 3. DEPENDENCY INVERSION PRINCIPLE:
     *
     * Clients can depend on the Flyweight interface rather
     * than concrete Flyweight classes.
     *
     *
     * ============================================================
     * 16. MEMORY BENEFIT AND PERFORMANCE
     * ============================================================
     *
     * Flyweight is especially useful when:
     *
     * - A very large number of similar objects exists.
     * - Many objects share the same intrinsic state.
     * - The shared state occupies meaningful memory.
     *
     * Conceptually:
     *
     * WITHOUT FLYWEIGHT:
     *
     * N objects store:
     *
     * N * (Intrinsic State + Extrinsic State)
     *
     *
     * WITH FLYWEIGHT:
     *
     * K shared Flyweights store intrinsic state.
     *
     * N context objects store extrinsic state and references.
     *
     * Here:
     *
     * N = Total number of objects.
     *
     * K = Number of unique intrinsic-state combinations.
     *
     * Usually K is much smaller than N for Flyweight to be useful.
     *
     * The actual memory savings depend on object layout,
     * references, collection overhead and implementation details.
     *
     * The factory also introduces lookup and management overhead.
     *
     *
     * ============================================================
     * 17. THREAD SAFETY CONSIDERATIONS
     * ============================================================
     *
     * A shared Flyweight may be accessed by many threads.
     *
     * Therefore:
     *
     * - Prefer immutable Flyweights.
     * - Make the factory thread-safe when shared concurrently.
     * - Avoid storing request-specific state inside Flyweights.
     * - Do not expose mutable shared data carelessly.
     *
     * Our HashMap-based TreeFactory is not thread-safe.
     *
     * In concurrent applications, consider:
     *
     * - ConcurrentHashMap.
     * - Atomic creation techniques such as computeIfAbsent().
     * - Immutable Flyweight implementations.
     *
     *
     * ============================================================
     * 18. ADVANTAGES
     * ============================================================
     *
     * 1. Reduces duplicated object state.
     * 2. Can significantly reduce memory consumption.
     * 3. Encourages reuse of common objects.
     * 4. Separates intrinsic and extrinsic state.
     * 5. Useful for applications with huge numbers of similar objects.
     *
     *
     * ============================================================
     * 19. DISADVANTAGES
     * ============================================================
     *
     * 1. Introduces additional factory and management logic.
     * 2. Requires careful separation of intrinsic and extrinsic state.
     * 3. Shared mutable state can create concurrency problems.
     * 4. Lookup and caching introduce runtime overhead.
     * 5. May not help when objects have very little shared state.
     *
     *
     * ============================================================
     * 20. WHEN SHOULD WE USE FLYWEIGHT?
     * ============================================================
     *
     * Use Flyweight when:
     *
     * - Your application creates a very large number of objects.
     * - Many objects share common immutable information.
     * - Memory consumption is a significant concern.
     * - Shared objects can safely be reused.
     *
     * Avoid Flyweight when:
     *
     * - Only a small number of objects are created.
     * - Objects have very little common state.
     * - Sharing introduces more complexity than memory savings.
     * - Shared state is highly mutable and difficult to manage.
     *
     *
     * ============================================================
     * 21. COMMON MISCONCEPTIONS
     * ============================================================
     *
     * 1. Flyweight means creating only one object.
     *    FALSE. It can manage many shared Flyweight objects.
     *
     * 2. Flyweight and Singleton are identical.
     *    FALSE. Their primary purposes are different.
     *
     * 3. Every object field must be shared.
     *    FALSE. Only intrinsic state is shared.
     *
     * 4. Flyweight automatically improves performance.
     *    FALSE. It primarily targets memory usage.
     *    Lookup overhead may offset the benefit.
     *
     * 5. Flyweights should store request-specific data.
     *    FALSE. Extrinsic state should generally be supplied
     *    by the client.
     *
     * 6. Every cached object is a Flyweight.
     *    FALSE. The overall design must follow the Flyweight
     *    intent of sharing intrinsic state among contexts.
     *
     *
     * ============================================================
     * 22. INTERVIEW QUESTIONS
     * ============================================================
     *
     * Q1. What is Flyweight?
     *
     * A: A structural design pattern that reduces memory usage
     *    by sharing common object state.
     *
     * Q2. What are intrinsic and extrinsic states?
     *
     * A: Intrinsic state is shared and stored in the Flyweight.
     *    Extrinsic state is context-specific and supplied
     *    by the client.
     *
     * Q3. What is the role of Flyweight Factory?
     *
     * A: It creates and manages reusable Flyweight objects.
     *
     * Q4. Why should Flyweights generally be immutable?
     *
     * A: Multiple clients share them, so changing shared state
     *    can unexpectedly affect other clients.
     *
     * Q5. What is the difference between Singleton and Flyweight?
     *
     * A: Singleton controls the number of instances;
     *    Flyweight shares reusable objects to reduce memory.
     *
     * Q6. What is the difference between Prototype and Flyweight?
     *
     * A: Prototype creates copies; Flyweight reuses shared objects.
     *
     * Q7. What is the main goal of Flyweight?
     *
     * A: Reduce memory consumption when many objects share
     *    common intrinsic state.
     *
     * Q8. Is Java String interning an example of Flyweight?
     *
     * A: It demonstrates object sharing and reuse, but is not
     *    automatically a complete textbook GoF implementation.
     *
     * Q9. Is HashMap thread-safe?
     *
     * A: No. Concurrent access involving modifications requires
     *    appropriate synchronization or a concurrent collection.
     *
     *
     * ============================================================
     * 23. QUICK REVISION
     * ============================================================
     *
     * PATTERN: Flyweight
     *
     * CATEGORY: Structural
     *
     * PURPOSE:
     * Reduce memory usage through object sharing.
     *
     * MAIN IDEA:
     * Share common state instead of duplicating it.
     *
     * IMPORTANT TERMS:
     *
     * Intrinsic State -> Shared state.
     *
     * Extrinsic State -> Context-specific state.
     *
     * MAIN PARTICIPANTS:
     *
     * Flyweight
     * Concrete Flyweight
     * Flyweight Factory
     * Client
     *
     * COMMON EXAMPLE:
     * Thousands of trees sharing a small number of tree types.
     *
     * REMEMBER:
     *
     * Singleton -> One controlled instance.
     *
     * Prototype -> Copy existing objects.
     *
     * Flyweight -> Share common object state.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Flyweight Design Pattern , Happy Learning ..!");
    }
}
