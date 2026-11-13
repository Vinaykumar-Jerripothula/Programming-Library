
public class Composite {

    /*
     * ============================================================
     * 1. INTRODUCTION
     * ============================================================
     *
     * Imagine you are building a file explorer.
     *
     * A file explorer contains:
     *
     * - Files
     * - Folders
     *
     * A folder can contain:
     *
     * - Files
     * - Other folders
     *
     * Those folders can contain even more files and folders.
     *
     * Example:
     *
     * Java-Projects/
     * |
     * |-- Main.java
     * |
     * |-- Backend/
     * |   |-- User.java
     * |   |-- Service.java
     * |
     * |-- Frontend/
     *     |-- App.jsx
     *     |-- Navbar.jsx
     *
     * Now imagine you want to calculate the total size of
     * Java-Projects.
     *
     * A file has its own size.
     *
     * A folder's size is the sum of the sizes of everything
     * inside it, including nested folders.
     *
     * Wouldn't it be convenient if both files and folders
     * supported the same operation?
     *
     * For example:
     *
     * component.getSize();
     *
     * Whether component represents a File or Folder,
     * the client can use the same method.
     *
     * This is the central idea of the Composite pattern.
     *
     *
     * DEFINITION:
     *
     * Composite is a structural design pattern that composes
     * objects into tree structures to represent part-whole
     * hierarchies and allows clients to treat individual objects
     * and compositions of objects uniformly.
     *
     *
     * SIMPLE DEFINITION:
     *
     * Treat one object and a group of objects through
     * the same interface.
     *
     *
     * ============================================================
     * 2. WHY DO WE NEED COMPOSITE?
     * ============================================================
     *
     * Suppose we have:
     *
     * class File {
     *     long getSize();
     * }
     *
     * class Folder {
     *     long calculateTotalSize();
     * }
     *
     * The client must distinguish between the two types:
     *
     * if (object instanceof File) {
     *     // Handle file
     * }
     * else if (object instanceof Folder) {
     *     // Handle folder
     * }
     *
     * As the hierarchy grows, this becomes inconvenient.
     *
     * What if folders contain other folders?
     *
     * We need recursive processing.
     *
     * Composite solves this by introducing a common abstraction.
     *
     * interface FileSystemComponent {
     *     long getSize();
     * }
     *
     * Both File and Folder implement FileSystemComponent.
     *
     * The client can now call:
     *
     * component.getSize();
     *
     * without knowing whether it is a file or folder.
     *
     *
     * ============================================================
     * 3. REAL-WORLD ANALOGY
     * ============================================================
     *
     * Think about an organization.
     *
     * An employee may work individually.
     *
     * A manager may supervise several employees.
     *
     * Some employees may themselves be managers.
     *
     * This creates a hierarchy.
     *
     * If every member supports an operation such as:
     *
     * getTotalSalary()
     *
     * An individual employee returns their own salary.
     *
     * A department returns the combined salaries of all
     * employees and nested departments.
     *
     * Both can be treated through the same abstraction.
     *
     * REMEMBER:
     *
     * Individual object + Group of objects = Common interface.
     *
     *
     * ============================================================
     * 4. STRUCTURE AND PARTICIPANTS
     * ============================================================
     *
     * Composite has three main participants:
     *
     * 1. COMPONENT
     *
     *    Defines the common interface for all objects
     *    in the hierarchy.
     *
     *    Example:
     *
     *    FileSystemComponent
     *
     *
     * 2. LEAF
     *
     *    Represents an individual object that does not contain
     *    child components in the Composite structure.
     *
     *    Example:
     *
     *    File
     *
     *
     * 3. COMPOSITE
     *
     *    Represents a container that holds child components.
     *
     *    A Composite can contain both Leaves and other Composites.
     *
     *    Example:
     *
     *    Folder
     *
     *
     * 4. CLIENT
     *
     *    Interacts with objects through the Component interface.
     *
     *
     * BASIC STRUCTURE:
     *
     *              Component
     *                 |
     *          +------+------+
     *          |             |
     *         Leaf        Composite
     *                        |
     *                        | contains
     *                        v
     *                    Component
     *
     *
     * ============================================================
     * 5. COMPLETE JAVA IMPLEMENTATION
     * ============================================================
     *
     * We will implement a simple file system.
     *
     * A File has a fixed size.
     *
     * A Folder calculates its size by adding the sizes
     * of its children.
     *
     *
     * STEP 1: Create the Component interface.
     *
     * interface FileSystemComponent {
     *
     *     String getName();
     *
     *     long getSize();
     *
     *     void display(String indentation);
     * }
     *
     *
     * STEP 2: Create the Leaf.
     *
     * class FileItem implements FileSystemComponent {
     *
     *     private final String name;
     *     private final long size;
     *
     *     public FileItem(String name, long size) {
     *
     *         if (size < 0) {
     *             throw new IllegalArgumentException(
     *                 "File size cannot be negative"
     *             );
     *         }
     *
     *         this.name = name;
     *         this.size = size;
     *     }
     *
     *     @Override
     *     public String getName() {
     *         return name;
     *     }
     *
     *     @Override
     *     public long getSize() {
     *         return size;
     *     }
     *
     *     @Override
     *     public void display(String indentation) {
     *
     *         System.out.println(
     *             indentation + "File: " + name +
     *             " (" + size + " bytes)"
     *         );
     *     }
     * }
     *
     *
     * STEP 3: Create the Composite.
     *
     * // Imports needed in a standalone implementation:
     * // import java.util.ArrayList;
     * // import java.util.List;
     *
     * class Folder implements FileSystemComponent {
     *
     *     private final String name;
     *
     *     private final java.util.List<FileSystemComponent> children
     *         = new java.util.ArrayList<>();
     *
     *     public Folder(String name) {
     *         this.name = name;
     *     }
     *
     *     public void add(FileSystemComponent component) {
     *         children.add(component);
     *     }
     *
     *     public boolean remove(FileSystemComponent component) {
     *         return children.remove(component);
     *     }
     *
     *     @Override
     *     public String getName() {
     *         return name;
     *     }
     *
     *     @Override
     *     public long getSize() {
     *
     *         long total = 0;
     *
     *         for (FileSystemComponent child : children) {
     *             total = Math.addExact(total, child.getSize());
     *         }
     *
     *         return total;
     *     }
     *
     *     @Override
     *     public void display(String indentation) {
     *
     *         System.out.println(
     *             indentation + "Folder: " + name
     *         );
     *
     *         for (FileSystemComponent child : children) {
     *             child.display(indentation + "  ");
     *         }
     *     }
     * }
     *
     *
     * STEP 4: Create the hierarchy.
     *
     * Folder root = new Folder("Java-Projects");
     *
     * FileSystemComponent main =
     *     new FileItem("Main.java", 100);
     *
     * Folder backend = new Folder("Backend");
     *
     * backend.add(new FileItem("User.java", 200));
     * backend.add(new FileItem("Service.java", 300));
     *
     * Folder frontend = new Folder("Frontend");
     *
     * frontend.add(new FileItem("App.jsx", 150));
     * frontend.add(new FileItem("Navbar.jsx", 250));
     *
     * root.add(main);
     * root.add(backend);
     * root.add(frontend);
     *
     *
     * STEP 5: Use the hierarchy.
     *
     * root.display("");
     *
     * System.out.println(
     *     "Total size: " + root.getSize() + " bytes"
     * );
     *
     *
     * OUTPUT:
     *
     * Folder: Java-Projects
     *   File: Main.java (100 bytes)
     *   Folder: Backend
     *     File: User.java (200 bytes)
     *     File: Service.java (300 bytes)
     *   Folder: Frontend
     *     File: App.jsx (150 bytes)
     *     File: Navbar.jsx (250 bytes)
     *
     * Total size: 1000 bytes
     *
     *
     * IMPORTANT:
     *
     * This is a simplified in-memory file-system model.
     *
     * It does not read actual operating-system files.
     *
     * It also assumes the structure is a proper tree without
     * circular references or unintended shared children.
     *
     *
     * ============================================================
     * 6. UNDERSTANDING THE EXECUTION FLOW
     * ============================================================
     *
     * Consider:
     *
     * root.getSize();
     *
     * Step 1:
     *
     * Java calls Folder.getSize() on the root folder.
     *
     * Step 2:
     *
     * The root iterates through its children.
     *
     * Step 3:
     *
     * Main.java is a Leaf.
     *
     * Its getSize() returns 100.
     *
     * Step 4:
     *
     * Backend is another Folder.
     *
     * Its getSize() calls getSize() on its children.
     *
     * User.java returns 200.
     * Service.java returns 300.
     *
     * Backend returns 500.
     *
     * Step 5:
     *
     * Frontend calculates:
     *
     * 150 + 250 = 400.
     *
     * Step 6:
     *
     * Root calculates:
     *
     * 100 + 500 + 400 = 1000.
     *
     * Notice:
     *
     * The same getSize() operation works for both
     * individual files and folders.
     *
     *
     * ============================================================
     * 7. RECURSION IN COMPOSITE
     * ============================================================
     *
     * Recursion is frequently used with Composite.
     *
     * Why?
     *
     * Because a Composite can contain another Composite.
     *
     * Example:
     *
     * Folder A
     *   |
     *   |-- File 1
     *   |
     *   |-- Folder B
     *         |
     *         |-- File 2
     *         |
     *         |-- Folder C
     *               |
     *               |-- File 3
     *
     * When getSize() is called on Folder A:
     *
     * Folder A calls getSize() on Folder B.
     *
     * Folder B calls getSize() on Folder C.
     *
     * Folder C calls getSize() on File 3.
     *
     * File 3 returns its size.
     *
     * The results return upward through the hierarchy.
     *
     * IMPORTANT:
     *
     * Composite does not require recursion for every operation.
     *
     * However, recursive traversal is a natural way to process
     * nested Composite structures.
     *
     *
     * ============================================================
     * 8. WHAT IS A PART-WHOLE HIERARCHY?
     * ============================================================
     *
     * A part-whole hierarchy represents relationships where
     * a larger object consists of smaller objects.
     *
     * Examples:
     *
     * - Folder contains files and folders.
     * - Department contains employees and departments.
     * - Menu contains menu items and submenus.
     * - UI container contains components and containers.
     *
     * The whole is represented by the Composite.
     *
     * The individual parts are represented by Leaves
     * or nested Composites.
     *
     *
     * ============================================================
     * 9. TRANSPARENT VS SAFE COMPOSITE
     * ============================================================
     *
     * There are two common approaches to designing the
     * Component interface.
     *
     *
     * A. TRANSPARENT COMPOSITE
     *
     * The Component interface includes child-management methods.
     *
     * Example:
     *
     * interface Component {
     *
     *     void operation();
     *
     *     void add(Component child);
     *
     *     void remove(Component child);
     * }
     *
     * Both Leaf and Composite implement this interface.
     *
     * Problem:
     *
     * A Leaf does not logically support adding children.
     *
     * Therefore, its add() method may need to throw
     * UnsupportedOperationException.
     *
     * Advantage:
     *
     * Clients can use a uniform interface.
     *
     * Disadvantage:
     *
     * Some operations are invalid for Leaves.
     *
     *
     * B. SAFE COMPOSITE
     *
     * The Component interface declares only operations
     * meaningful to all components.
     *
     * Example:
     *
     * interface Component {
     *     void operation();
     * }
     *
     * class Folder implements Component {
     *
     *     void add(Component child) {
     *         // Add child.
     *     }
     * }
     *
     * Leaf does not need to implement add().
     *
     * Advantage:
     *
     * Better type safety and clearer responsibilities.
     *
     * Disadvantage:
     *
     * Clients need a Composite reference to manage children.
     *
     *
     * OUR FILE-SYSTEM EXAMPLE:
     *
     * Uses the safe approach.
     *
     * FileSystemComponent contains common operations.
     *
     * Only Folder exposes add() and remove().
     *
     *
     * ============================================================
     * 10. WHY IS POLYMORPHISM IMPORTANT?
     * ============================================================
     *
     * Consider:
     *
     * FileSystemComponent component;
     *
     * component = new FileItem("A.java", 100);
     *
     * component.getSize();
     *
     * Now:
     *
     * component = new Folder("Documents");
     *
     * component.getSize();
     *
     * The same interface reference can point to different
     * concrete implementations.
     *
     * Java selects the appropriate overridden method
     * at runtime.
     *
     * This is runtime polymorphism.
     *
     * Composite uses polymorphism to treat Leaves and
     * Composites uniformly.
     *
     *
     * ============================================================
     * 11. WHY DOES COMPOSITE USE COMPOSITION?
     * ============================================================
     *
     * Consider:
     *
     * class Folder implements FileSystemComponent {
     *
     *     private final List<FileSystemComponent> children;
     * }
     *
     * Folder HAS-A collection of FileSystemComponent objects.
     *
     * This is a has-a relationship.
     *
     * In Java terminology, it is object composition.
     *
     * The pattern also uses interface implementation:
     *
     * Folder IS-A FileSystemComponent.
     *
     * Therefore, Composite combines:
     *
     * - Polymorphism
     * - Composition
     * - Recursive structures
     *
     *
     * ============================================================
     * 12. COMPOSITE VS DECORATOR
     * ============================================================
     *
     * COMPOSITE:
     *
     * Represents tree structures containing multiple components.
     *
     * A Composite commonly holds a collection of child objects.
     *
     * DECORATOR:
     *
     * Adds responsibilities to an object while preserving
     * its interface.
     *
     * A Decorator commonly wraps one component.
     *
     * Example:
     *
     * Composite:
     * A Folder containing Files and other Folders.
     *
     * Decorator:
     * A DataSource wrapped with encryption or compression.
     *
     * KEY DIFFERENCE:
     *
     * Composite -> Organize part-whole hierarchies.
     *
     * Decorator -> Extend behavior through wrapping.
     *
     *
     * ============================================================
     * 13. COMPOSITE VS BRIDGE
     * ============================================================
     *
     * COMPOSITE:
     *
     * Creates tree structures of objects sharing a common
     * component interface.
     *
     * BRIDGE:
     *
     * Separates abstraction and implementation into
     * independently varying hierarchies.
     *
     * Example:
     *
     * Composite:
     * Folder -> Files and Folders.
     *
     * Bridge:
     * Remote -> Device.
     *
     *
     * ============================================================
     * 14. COMPOSITE VS CHAIN OF RESPONSIBILITY
     * ============================================================
     *
     * COMPOSITE:
     *
     * Represents a hierarchy of objects.
     *
     * Operations may recursively process multiple children.
     *
     * CHAIN OF RESPONSIBILITY:
     *
     * Passes a request through a chain of handlers.
     *
     * Each handler decides whether to process or forward it.
     *
     * Example:
     *
     * Composite:
     * Calculate the total size of a directory tree.
     *
     * Chain of Responsibility:
     * Pass an HTTP request through authentication,
     * authorization and other processing filters.
     *
     *
     * ============================================================
     * 15. COMPOSITE IN JAVA
     * ============================================================
     *
     * Java GUI frameworks demonstrate Composite-like structures.
     *
     * Example:
     *
     * java.awt.Component
     *
     * java.awt.Container
     *
     * A Container is a Component that can contain
     * other Components.
     *
     * This allows nested UI hierarchies.
     *
     * Another example is Swing:
     *
     * JPanel can contain buttons, labels and other panels.
     *
     * IMPORTANT:
     *
     * These frameworks contain Composite-like designs.
     * Their complete APIs include additional responsibilities
     * beyond the simplified GoF pattern.
     *
     *
     * ============================================================
     * 16. COMPOSITE IN BACKEND DEVELOPMENT
     * ============================================================
     *
     * Composite is useful in backend systems for:
     *
     * 1. Organization hierarchies:
     *
     *    Company
     *      |
     *      |-- Department
     *      |     |-- Team
     *      |     |    |-- Employee
     *
     *
     * 2. Nested categories:
     *
     *    Electronics
     *      |
     *      |-- Computers
     *      |     |-- Laptops
     *      |
     *      |-- Mobiles
     *
     *
     * 3. Menu structures:
     *
     *    Main Menu
     *      |
     *      |-- Products
     *      |     |-- Add Product
     *      |     |-- View Products
     *
     *
     * 4. Permission groups:
     *
     *    A group may contain permissions and other groups.
     *
     *
     * 5. Expression trees:
     *
     *    Complex expressions can be represented using
     *    individual operands and composite operations.
     *
     *
     * ============================================================
     * 17. COMPOSITE AND SOLID PRINCIPLES
     * ============================================================
     *
     * 1. OPEN/CLOSED PRINCIPLE:
     *
     * New Leaf and Composite implementations can be introduced
     * without changing clients that depend on the common
     * Component interface.
     *
     * 2. LISKOV SUBSTITUTION PRINCIPLE:
     *
     * Leaves and Composites should honor the behavioral
     * contract of the Component interface.
     *
     * 3. DEPENDENCY INVERSION PRINCIPLE:
     *
     * Clients can depend on Component abstractions rather
     * than concrete Leaf and Composite implementations.
     *
     * 4. INTERFACE SEGREGATION PRINCIPLE:
     *
     * The safe Composite approach avoids forcing Leaves
     * to implement unsupported child-management operations.
     *
     *
     * ============================================================
     * 18. IMPORTANT DESIGN CONSIDERATIONS
     * ============================================================
     *
     * A. CIRCULAR REFERENCES
     *
     * Consider:
     *
     * Folder A contains Folder B.
     *
     * Folder B contains Folder A.
     *
     * Calling getSize() could cause infinite recursion.
     *
     * Therefore, a robust Composite implementation should
     * prevent cycles when the structure must be a tree.
     *
     *
     * B. SHARED CHILDREN
     *
     * If the same object is added to multiple folders,
     * the structure may become a graph rather than a tree.
     *
     * Depending on the operation, shared children may be
     * counted multiple times.
     *
     *
     * C. PARENT REFERENCES
     *
     * Some Composite implementations store parent references
     * to support upward navigation.
     *
     * Parent references must be maintained consistently
     * when adding, moving or removing children.
     *
     *
     * D. THREAD SAFETY
     *
     * A mutable Composite is not automatically thread-safe.
     *
     * Concurrent modifications may require synchronization
     * or other concurrency-control techniques.
     *
     *
     * E. PERFORMANCE
     *
     * Recursive traversal commonly visits every descendant
     * needed by the operation.
     *
     * For a tree with N nodes, calculating a full aggregate
     * such as total size generally takes O(N) time.
     *
     * Deep recursion may also consume significant stack space.
     *
     *
     * ============================================================
     * 19. ADVANTAGES
     * ============================================================
     *
     * 1. Treats individual objects and groups uniformly.
     * 2. Naturally represents tree structures.
     * 3. Simplifies recursive operations.
     * 4. Reduces type-checking logic in clients.
     * 5. Supports adding new Component implementations.
     * 6. Encourages polymorphism and composition.
     *
     *
     * ============================================================
     * 20. DISADVANTAGES
     * ============================================================
     *
     * 1. Can make interfaces too general.
     * 2. Some operations may not apply to every component.
     * 3. Recursive structures require careful cycle handling.
     * 4. Complex hierarchies can be difficult to debug.
     * 5. Shared mutable children can introduce consistency issues.
     * 6. Traversing large trees can be expensive.
     *
     *
     * ============================================================
     * 21. WHEN SHOULD WE USE COMPOSITE?
     * ============================================================
     *
     * Use Composite when:
     *
     * - Your data naturally forms a tree.
     * - Objects contain other objects of the same abstraction.
     * - Clients should treat individual objects and groups alike.
     * - You need recursive operations across a hierarchy.
     *
     * Avoid Composite when:
     *
     * - Objects do not form a meaningful hierarchy.
     * - There is no need to treat individual and grouped
     *   objects uniformly.
     * - A simpler collection or data structure is sufficient.
     *
     *
     * ============================================================
     * 22. COMMON MISCONCEPTIONS
     * ============================================================
     *
     * 1. Composite is the same as composition.
     *    FALSE. Composition is a general object relationship.
     *    Composite is a specific GoF design pattern.
     *
     * 2. Every Composite must contain multiple children.
     *    FALSE. A Composite can contain zero or more children.
     *
     * 3. Every Component must support add() and remove().
     *    FALSE. Safe Composite exposes these only on containers.
     *
     * 4. Composite always requires an abstract class.
     *    FALSE. Interfaces can also define the Component contract.
     *
     * 5. Composite automatically prevents circular references.
     *    FALSE. Cycle prevention must be designed explicitly.
     *
     * 6. Composite is only used in file systems.
     *    FALSE. It is useful in GUI, organizational, category
     *    and expression hierarchies.
     *
     *
     * ============================================================
     * 23. INTERVIEW QUESTIONS
     * ============================================================
     *
     * Q1. What is the Composite pattern?
     *
     * A: A structural pattern that represents part-whole
     *    hierarchies and lets clients treat individual objects
     *    and groups uniformly.
     *
     * Q2. What are the main participants?
     *
     * A: Component, Leaf, Composite and Client.
     *
     * Q3. What is a Leaf?
     *
     * A: An individual component that does not contain child
     *    components in the Composite structure.
     *
     * Q4. What is a Composite?
     *
     * A: A component that contains other components and
     *    coordinates operations across them.
     *
     * Q5. Why is recursion commonly used?
     *
     * A: Because Composites can contain nested Composites.
     *
     * Q6. What is the difference between transparent and
     *    safe Composite?
     *
     * A: Transparent Composite exposes child-management methods
     *    through the common interface; safe Composite keeps
     *    those methods on container types.
     *
     * Q7. What is the difference between Composite and Decorator?
     *
     * A: Composite represents object hierarchies; Decorator
     *    adds responsibilities through wrapping.
     *
     * Q8. Which Java API demonstrates Composite-like behavior?
     *
     * A: java.awt.Component and java.awt.Container.
     *
     * Q9. What happens if a Composite contains a cycle?
     *
     * A: Recursive operations may never terminate unless
     *    cycles are prevented or explicitly handled.
     *
     * Q10. What is the time complexity of traversing a tree?
     *
     * A: A complete traversal generally takes O(N), where
     *    N is the number of visited nodes.
     *
     *
     * ============================================================
     * 24. QUICK REVISION
     * ============================================================
     *
     * PATTERN: Composite
     *
     * CATEGORY: Structural
     *
     * PURPOSE:
     * Treat individual objects and groups uniformly.
     *
     * MAIN PARTICIPANTS:
     * Component, Leaf, Composite, Client.
     *
     * KEY STRUCTURE:
     * Tree / Part-Whole Hierarchy.
     *
     * IMPORTANT CONCEPTS:
     * Polymorphism, Composition, Recursion.
     *
     * COMMON EXAMPLE:
     * File and Folder hierarchy.
     *
     * REMEMBER:
     *
     * Leaf -> Individual object.
     *
     * Composite -> Container of Components.
     *
     * Component -> Common interface.
     *
     * Adapter -> Interface compatibility.
     *
     * Bridge -> Independent abstraction and implementation.
     *
     * Composite -> Individual and grouped objects together.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Composite Design Pattern , Happy Learning ..!");
    }
}
