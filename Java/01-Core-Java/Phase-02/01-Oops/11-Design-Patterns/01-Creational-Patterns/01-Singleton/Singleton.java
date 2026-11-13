
public class Singleton {

    /*
     * ============================================================
     *                    SINGLETON DESIGN PATTERN
     * ============================================================
     *
     * 1. INTRODUCTION
     *
     * Singleton is a Creational Design Pattern.
     *
     * Creational design patterns deal with object creation.
     *
     * Singleton ensures that:
     *
     *   1. A class has only one instance within a defined scope.
     *   2. A globally accessible way is provided to obtain that
     *      instance.
     *
     * In simple words:
     *
     * Instead of allowing different parts of an application to
     * create their own objects of a particular class, Singleton
     * controls object creation and provides a shared instance.
     *
     * Example:
     *
     * Imagine an application has a central configuration manager.
     *
     * Different parts of the application need to access the
     * same configuration.
     *
     * Instead of creating multiple independent configuration
     * manager objects, we can provide one shared instance.
     *
     *
     * 2. WHY DO WE NEED SINGLETON?
     *
     * First understand the problem.
     *
     * Normally, Java allows us to create multiple objects
     * of the same class.
     *
     * Example:
     *
     * class DatabaseConnection {
     *
     * }
     *
     * DatabaseConnection db1 = new DatabaseConnection();
     * DatabaseConnection db2 = new DatabaseConnection();
     * DatabaseConnection db3 = new DatabaseConnection();
     *
     * Here, three separate objects are created.
     *
     * Each object has its own identity and instance state.
     *
     * But sometimes we want a particular class to provide
     * one shared instance instead of independently created
     * instances.
     *
     * Examples of possible use cases:
     *
     *   - Application-wide configuration.
     *   - Shared application logging service.
     *   - A registry of application components.
     *   - A shared cache manager.
     *   - A service coordinating access to shared resources.
     *
     * IMPORTANT:
     *
     * Singleton does not mean that every resource in the
     * application must have only one object.
     *
     * It is useful only when a single shared instance is
     * appropriate for the particular responsibility.
     *
     *
     * 3. REAL-WORLD ANALOGY
     *
     * Imagine a company has one central reception desk.
     *
     * Every department contacts that same reception desk
     * to get information.
     *
     * The departments do not create a completely new
     * reception desk whenever they need information.
     *
     * The reception desk represents one shared service.
     *
     * Similarly, Singleton provides one shared instance
     * through which different parts of an application
     * access a particular service.
     *
     * This is an analogy for shared access, not a claim
     * that every application-wide service must be a Singleton.
     *
     *
     * 4. BASIC REQUIREMENTS OF SINGLETON
     *
     * A typical Singleton implementation uses three things:
     *
     *   1. A private constructor.
     *   2. A private static instance variable.
     *   3. A public static access method.
     *
     * WHY PRIVATE CONSTRUCTOR?
     *
     * A constructor controls object creation.
     *
     * If the constructor is public, external code can use new
     * to create multiple objects.
     *
     * By making it private, normal external construction
     * using new is prevented.
     *
     * WHY STATIC INSTANCE VARIABLE?
     *
     * A static variable belongs to the class rather than
     * to individual objects.
     *
     * It can hold the shared instance.
     *
     * WHY STATIC ACCESS METHOD?
     *
     * Because the constructor is private, external code needs
     * another way to obtain the instance.
     *
     * A static method can be called using the class name
     * without creating an object.
     *
     *
     * 5. EAGER INITIALIZATION SINGLETON
     *
     * In eager initialization, the instance is created when
     * the class is initialized.
     *
     * Example:
     *
     * class EagerSingleton {
     *
     *     private static final EagerSingleton INSTANCE =
     *             new EagerSingleton();
     *
     *     private EagerSingleton() {
     *         System.out.println("Object created");
     *     }
     *
     *     public static EagerSingleton getInstance() {
     *         return INSTANCE;
     *     }
     * }
     *
     * Usage:
     *
     * EagerSingleton obj1 = EagerSingleton.getInstance();
     * EagerSingleton obj2 = EagerSingleton.getInstance();
     *
     * System.out.println(obj1 == obj2);
     *
     * Output:
     *
     * Object created
     * true
     *
     * EXPLANATION:
     *
     * 1. The class is initialized.
     *
     * 2. The static INSTANCE field is initialized.
     *
     * 3. The private constructor creates the object.
     *
     * 4. getInstance() returns the same reference.
     *
     * 5. Both obj1 and obj2 refer to the same object.
     *
     * WHY IS IT CALLED EAGER?
     *
     * Because the object is created during class initialization,
     * rather than waiting until the first getInstance() call.
     *
     * ADVANTAGES:
     *
     *   - Simple implementation.
     *   - Thread-safe under normal class initialization.
     *   - No explicit synchronization required.
     *
     * DISADVANTAGES:
     *
     *   - The object is created even if it is never used.
     *   - Initialization may happen earlier than necessary.
     *   - Not suitable when construction requires parameters
     *     that are unavailable during class initialization.
     *
     * IMPORTANT:
     *
     * Java class initialization is synchronized by the JVM.
     * This makes the initialization of the static instance
     * thread-safe.
     *
     *
     * 6. LAZY INITIALIZATION SINGLETON
     *
     * In lazy initialization, the object is created only when
     * it is requested for the first time.
     *
     * Example:
     *
     * class LazySingleton {
     *
     *     private static LazySingleton instance;
     *
     *     private LazySingleton() {
     *         System.out.println("Object created");
     *     }
     *
     *     public static LazySingleton getInstance() {
     *
     *         if (instance == null) {
     *             instance = new LazySingleton();
     *         }
     *
     *         return instance;
     *     }
     * }
     *
     * Usage:
     *
     * LazySingleton obj1 = LazySingleton.getInstance();
     * LazySingleton obj2 = LazySingleton.getInstance();
     *
     * System.out.println(obj1 == obj2);
     *
     * Output:
     *
     * Object created
     * true
     *
     * EXPLANATION:
     *
     * Initially:
     *   instance = null
     *
     * First getInstance() call:
     *   instance == null
     *   A new object is created.
     *   The reference is assigned to instance.
     *
     * Second getInstance() call:
     *   instance != null
     *   The existing object is returned.
     *
     * WHY IS IT CALLED LAZY?
     *
     * Because object creation is delayed until the instance
     * is actually requested.
     *
     * ADVANTAGES:
     *
     *   - Object is created only when needed.
     *   - Simple implementation.
     *
     * DISADVANTAGES:
     *
     *   - Not thread-safe.
     *   - Multiple threads can create multiple objects.
     *
     * IMPORTANT:
     *
     * This implementation is suitable only when concurrent
     * access is not possible or thread safety is otherwise
     * guaranteed.
     *
     *
     * 7. WHY IS BASIC LAZY INITIALIZATION NOT THREAD-SAFE?
     *
     * Consider two threads:
     *
     *   Thread 1
     *   Thread 2
     *
     * Initially:
     *   instance = null
     *
     * Possible execution:
     *
     *   1. Thread 1 checks instance == null.
     *   2. Thread 2 checks instance == null.
     *   3. Thread 1 creates an object.
     *   4. Thread 2 creates another object.
     *   5. Thread 1 assigns its object to instance.
     *   6. Thread 2 assigns its object to instance.
     *
     * Two objects have been created.
     *
     * Both threads may have received different references.
     *
     * Therefore, the Singleton guarantee is broken.
     *
     * This is a race condition.
     *
     *
     * 8. THREAD-SAFE SINGLETON USING SYNCHRONIZED METHOD
     *
     * We can use synchronized to prevent multiple threads
     * from executing the instance-creation method concurrently.
     *
     * Example:
     *
     * class SynchronizedSingleton {
     *
     *     private static SynchronizedSingleton instance;
     *
     *     private SynchronizedSingleton() {
     *     }
     *
     *     public static synchronized SynchronizedSingleton
     *             getInstance() {
     *
     *         if (instance == null) {
     *             instance = new SynchronizedSingleton();
     *         }
     *
     *         return instance;
     *     }
     * }
     *
     * HOW DOES IT WORK?
     *
     * The synchronized static method acquires the class-level
     * monitor lock before executing.
     *
     * Only one thread at a time can execute this synchronized
     * method for this class.
     *
     * If Thread 1 creates the object:
     *
     *   - Thread 1 acquires the lock.
     *   - Thread 1 creates the instance.
     *   - Thread 1 releases the lock.
     *   - Thread 2 acquires the lock.
     *   - Thread 2 sees the existing instance.
     *   - Thread 2 returns the same object.
     *
     * ADVANTAGES:
     *
     *   - Simple thread-safe lazy initialization.
     *   - Prevents concurrent creation through this accessor.
     *
     * DISADVANTAGES:
     *
     *   - Every getInstance() call requires synchronization.
     *   - After initialization, the instance already exists,
     *     but the method still acquires the lock.
     *
     * This synchronization overhead may matter in frequently
     * accessed code, although its real impact depends on
     * the application and JVM.
     *
     *
     * 9. DOUBLE-CHECKED LOCKING SINGLETON
     *
     * Double-checked locking attempts to avoid acquiring
     * a lock on every access after initialization.
     *
     * It checks whether the instance is null twice.
     *
     * Example:
     *
     * class DoubleCheckedSingleton {
     *
     *     private static volatile DoubleCheckedSingleton instance;
     *
     *     private DoubleCheckedSingleton() {
     *     }
     *
     *     public static DoubleCheckedSingleton getInstance() {
     *
     *         if (instance == null) {
     *
     *             synchronized (DoubleCheckedSingleton.class) {
     *
     *                 if (instance == null) {
     *
     *                     instance = new DoubleCheckedSingleton();
     *                 }
     *             }
     *         }
     *
     *         return instance;
     *     }
     * }
     *
     * WHY TWO CHECKS?
     *
     * FIRST CHECK:
     *
     *   if (instance == null)
     *
     * Avoids acquiring the lock when the instance already exists.
     *
     * SECOND CHECK:
     *
     *   if (instance == null)
     *
     * Checks again after acquiring the lock.
     *
     * This is necessary because another thread may have created
     * the instance while the current thread was waiting.
     *
     * WHY VOLATILE?
     *
     * volatile provides visibility guarantees and ordering
     * guarantees for access to the instance reference.
     *
     * Without volatile, older Java memory-model guarantees
     * were insufficient for safe double-checked locking.
     *
     * The volatile modifier is essential for this pattern
     * in modern Java as well.
     *
     * IMPORTANT:
     *
     * Double-checked locking without volatile is incorrect
     * as a safe Singleton implementation.
     *
     * ADVANTAGES:
     *
     *   - Lazy initialization.
     *   - Thread-safe creation.
     *   - Locking is avoided on normal access after initialization.
     *
     * DISADVANTAGES:
     *
     *   - More complicated than other implementations.
     *   - Easy to implement incorrectly.
     *
     *
     * 10. BILL PUGH SINGLETON / INITIALIZATION-ON-DEMAND HOLDER
     *
     * This is a popular way to implement lazy, thread-safe
     * Singleton initialization in Java.
     *
     * It uses a nested static holder class.
     *
     * Example:
     *
     * class HolderSingleton {
     *
     *     private HolderSingleton() {
     *     }
     *
     *     private static class Holder {
     *
     *         private static final HolderSingleton INSTANCE =
     *                 new HolderSingleton();
     *     }
     *
     *     public static HolderSingleton getInstance() {
     *
     *         return Holder.INSTANCE;
     *     }
     * }
     *
     * HOW DOES IT WORK?
     *
     * 1. HolderSingleton is loaded.
     *
     * 2. The nested Holder class is not initialized merely
     *    because its enclosing class is initialized.
     *
     * 3. The first call to getInstance() accesses Holder.INSTANCE.
     *
     * 4. This triggers initialization of Holder.
     *
     * 5. The JVM initializes Holder and creates the instance.
     *
     * 6. Later calls return the same instance.
     *
     * WHY IS IT THREAD-SAFE?
     *
     * Java class initialization is thread-safe.
     *
     * The JVM ensures that class initialization is performed
     * safely even when multiple threads trigger it concurrently.
     *
     * ADVANTAGES:
     *
     *   - Lazy initialization.
     *   - Thread-safe without explicit synchronized methods.
     *   - Simple and efficient access after initialization.
     *   - Avoids manually managing volatile and locks.
     *
     * DISADVANTAGES:
     *
     *   - Requires understanding nested class initialization.
     *   - Does not automatically protect against every way
     *     of creating additional instances, such as reflection.
     *
     * IMPORTANT:
     *
     * The holder idiom is often a good default for a simple
     * lazy Singleton when enum-specific behavior is not needed.
     *
     *
     * 11. ENUM SINGLETON
     *
     * Java provides a particularly robust Singleton approach
     * using enums.
     *
     * Example:
     *
     * enum EnumSingleton {
     *
     *     INSTANCE;
     *
     *     public void performTask() {
     *         System.out.println("Task performed");
     *     }
     * }
     *
     * Usage:
     *
     * EnumSingleton obj1 = EnumSingleton.INSTANCE;
     * EnumSingleton obj2 = EnumSingleton.INSTANCE;
     *
     * System.out.println(obj1 == obj2);
     *
     * Output:
     *
     * true
     *
     * WHY IS ENUM SINGLETON SPECIAL?
     *
     * Java guarantees that each enum constant is instantiated
     * only once per enum class and class loader.
     *
     * It also provides special protection against:
     *
     *   - Ordinary reflective construction.
     *   - Default Java serialization creating a separate
     *     enum object.
     *
     * Enum deserialization preserves the enum constant identity.
     *
     * ADVANTAGES:
     *
     *   - Simple implementation.
     *   - Thread-safe initialization.
     *   - Serialization-safe.
     *   - Strong protection against ordinary reflection attacks.
     *
     * DISADVANTAGES:
     *
     *   - Enum instances are initialized as part of enum
     *     class initialization.
     *   - Not suitable when ordinary class inheritance is needed.
     *   - Cannot extend another class because enums already
     *     extend java.lang.Enum.
     *
     * IMPORTANT:
     *
     * Enum Singleton is often considered the strongest simple
     * Singleton implementation in Java.
     *
     *
     * 12. COMPARISON OF SINGLETON IMPLEMENTATIONS
     *
     * EAGER INITIALIZATION:
     *   - Creation: During class initialization.
     *   - Lazy: No.
     *   - Thread-safe: Yes.
     *   - Complexity: Low.
     *
     * BASIC LAZY INITIALIZATION:
     *   - Creation: First access.
     *   - Lazy: Yes.
     *   - Thread-safe: No.
     *   - Complexity: Low.
     *
     * SYNCHRONIZED METHOD:
     *   - Creation: First access.
     *   - Lazy: Yes.
     *   - Thread-safe: Yes.
     *   - Complexity: Low.
     *
     * DOUBLE-CHECKED LOCKING:
     *   - Creation: First access.
     *   - Lazy: Yes.
     *   - Thread-safe: Yes, with volatile.
     *   - Complexity: High.
     *
     * HOLDER IDIOM:
     *   - Creation: First access to holder.
     *   - Lazy: Yes.
     *   - Thread-safe: Yes.
     *   - Complexity: Moderate.
     *
     * ENUM:
     *   - Creation: During enum class initialization.
     *   - Lazy: No, in the usual implementation.
     *   - Thread-safe: Yes.
     *   - Serialization-safe: Yes.
     *   - Complexity: Low.
     *
     *
     * 13. SINGLETON AND THREAD SAFETY
     *
     * Thread safety means that concurrent operations do not
     * produce incorrect behavior or corrupt shared state.
     *
     * A Singleton object may be shared by multiple threads.
     *
     * Therefore, we must distinguish two guarantees:
     *
     *   1. Single-instance creation:
     *      Multiple threads cannot create separate instances
     *      through the supported access mechanism.
     *
     *   2. Thread-safe object behavior:
     *      Concurrent operations on the object's mutable
     *      state are handled correctly.
     *
     * IMPORTANT:
     *
     * A thread-safe Singleton creation mechanism does not
     * automatically make its methods or mutable fields thread-safe.
     *
     * Example:
     *
     * class CounterSingleton {
     *
     *     private static final CounterSingleton INSTANCE =
     *             new CounterSingleton();
     *
     *     private int count = 0;
     *
     *     private CounterSingleton() {
     *     }
     *
     *     public static CounterSingleton getInstance() {
     *         return INSTANCE;
     *     }
     *
     *     public void increment() {
     *         count++;
     *     }
     *
     *     public int getCount() {
     *         return count;
     *     }
     * }
     *
     * The instance creation is thread-safe.
     *
     * But count++ is not an atomic operation.
     *
     * Concurrent calls may cause lost updates.
     *
     * We could use synchronized, AtomicInteger or another
     * suitable concurrency mechanism to protect the state.
     *
     * The right choice depends on the required behavior.
     *
     *
     * 14. SINGLETON AND JAVA REFLECTION
     *
     * Reflection allows Java code to inspect and interact
     * with classes, constructors, methods and fields at runtime.
     *
     * A private constructor prevents ordinary external
     * construction using new.
     *
     * However, reflection can sometimes bypass access checks.
     *
     * Example:
     *
     * Constructor<HolderSingleton> constructor =
     *         HolderSingleton.class.getDeclaredConstructor();
     *
     * constructor.setAccessible(true);
     *
     * HolderSingleton another =
     *         constructor.newInstance();
     *
     * This can create another instance of an ordinary
     * class-based Singleton in environments where access
     * restrictions permit it.
     *
     * The holder idiom alone does not prevent this.
     *
     * ENUM PROTECTION:
     *
     * Java prevents reflective creation of enum constants
     * through Constructor.newInstance().
     *
     * Attempting to create an enum constant reflectively
     * results in an exception.
     *
     * IMPORTANT:
     *
     * Strong encapsulation, security restrictions and
     * runtime environments can affect reflective access.
     *
     * Do not treat private constructors as an absolute
     * security boundary.
     *
     *
     * 15. SINGLETON AND SERIALIZATION
     *
     * Serialization converts an object's state into a form
     * that can be stored or transferred.
     *
     * Deserialization reconstructs an object from serialized data.
     *
     * A normal Serializable class-based Singleton may produce
     * a different object during deserialization unless
     * identity is explicitly preserved.
     *
     * Example:
     *
     * class SerializableSingleton implements Serializable {
     *
     *     private static final SerializableSingleton INSTANCE =
     *             new SerializableSingleton();
     *
     *     private SerializableSingleton() {
     *     }
     *
     *     public static SerializableSingleton getInstance() {
     *         return INSTANCE;
     *     }
     *
     *     protected Object readResolve() {
     *         return INSTANCE;
     *     }
     * }
     *
     * WHAT DOES readResolve() DO?
     *
     * During Java serialization deserialization, readResolve()
     * can specify the object that should be returned instead
     * of the newly reconstructed object.
     *
     * Here, it returns the existing Singleton instance.
     *
     * IMPORTANT:
     *
     * readResolve() protects against the ordinary serialization
     * identity problem, but it does not protect against every
     * possible attack or construction mechanism.
     *
     * Enum Singleton handles ordinary Java serialization
     * identity automatically.
     *
     *
     * 16. SINGLETON AND CLONING
     *
     * Java cloning can create another object from an existing
     * object.
     *
     * If a Singleton implements Cloneable and exposes a
     * clone operation, it may allow another instance to be
     * created.
     *
     * Example of a defensive implementation:
     *
     * @Override
     * protected Object clone() throws CloneNotSupportedException {
     *
     *     throw new CloneNotSupportedException(
     *             "Singleton cannot be cloned"
     *     );
     * }
     *
     * Another possible defense is returning the existing
     * instance from clone(), if that behavior is intentionally
     * designed and correctly implemented.
     *
     * Enum Singleton avoids ordinary cloning concerns because
     * enum constants cannot be cloned through normal Java APIs.
     *
     *
     * 17. SINGLETON AND CLASS LOADERS
     *
     * A Java class is identified at runtime by:
     *
     *   - Its fully qualified class name.
     *   - The class loader that defines it.
     *
     * If the same Singleton class is independently loaded
     * by two different class loaders, each class definition
     * can have its own static instance.
     *
     * Therefore, a class-based Singleton is not automatically
     * one instance across an entire JVM in every possible
     * class-loading arrangement.
     *
     * Its guarantee is generally one instance per defining
     * class loader and per Singleton implementation.
     *
     *
     * 18. SINGLETON IN SPRING FRAMEWORK
     *
     * Spring has its own concept of Singleton scope.
     *
     * By default, a Spring bean has singleton scope.
     *
     * Example:
     *
     * @Service
     * class EmployeeService {
     *
     *     public void process() {
     *         System.out.println("Processing employee");
     *     }
     * }
     *
     * Spring normally creates and manages one shared
     * EmployeeService bean instance per bean definition
     * within a particular Spring IoC container.
     *
     * Important differences:
     *
     * CLASSIC SINGLETON:
     *
     *   - The class controls its own instance creation.
     *   - Usually uses a private constructor.
     *   - Provides a static access method.
     *
     * SPRING SINGLETON SCOPE:
     *
     *   - Spring controls bean creation and lifecycle.
     *   - Does not require a private constructor.
     *   - Does not require a static getInstance() method.
     *   - One instance per bean definition per container.
     *
     * IMPORTANT:
     *
     * Spring singleton scope does not mean exactly one
     * instance across the entire JVM.
     *
     * Different Spring containers can have separate instances.
     *
     * Also, Spring singleton scope does not automatically
     * make a bean thread-safe.
     *
     * Stateless singleton services are generally easier
     * to use safely across concurrent requests.
     *
     *
     * 19. SINGLETON AND DATABASE CONNECTIONS
     *
     * A common misconception is that a database connection
     * should always be implemented as a Singleton.
     *
     * This is generally not a good approach for a backend
     * application handling concurrent requests.
     *
     * A single database connection may be shared by multiple
     * threads in ways that are unsafe or unsuitable.
     *
     * Connection pools are generally preferred.
     *
     * A connection pool manages multiple reusable connections
     * and provides a connection to a caller when needed.
     *
     * Example:
     *
     *   HikariCP
     *
     * A Spring Boot application commonly uses a connection
     * pool rather than one global database connection.
     *
     * A shared DataSource or connection-pool manager may
     * itself be managed as a Singleton-scoped bean.
     *
     * But that does not mean every request must use
     * the same physical database connection.
     *
     *
     * 20. ADVANTAGES OF SINGLETON
     *
     *   1. Controlled instance creation:
     *      Provides one shared instance within its defined scope.
     *
     *   2. Shared access:
     *      Multiple parts of an application can access
     *      the same object.
     *
     *   3. Centralized management:
     *      A shared service or registry can be accessed
     *      through one controlled instance.
     *
     *   4. Reduced repeated construction:
     *      Avoids repeatedly creating an expensive object
     *      when sharing that object is appropriate.
     *
     *   5. Consistent shared state:
     *      All callers can access the same instance state.
     *
     *
     * 21. DISADVANTAGES OF SINGLETON
     *
     *   1. Global state:
     *      A globally accessible mutable object can make
     *      application behavior difficult to reason about.
     *
     *   2. Testing difficulty:
     *      Tests may share state through the same instance.
     *
     *   3. Hidden dependencies:
     *      Classes may access Singleton instances directly
     *      instead of declaring their dependencies.
     *
     *   4. Concurrency concerns:
     *      Shared mutable state requires appropriate
     *      synchronization or concurrency controls.
     *
     *   5. Tight coupling:
     *      Code that directly calls a specific Singleton
     *      can become difficult to replace or mock.
     *
     *   6. Lifecycle limitations:
     *      Static instance management can be less flexible
     *      than dependency injection containers.
     *
     *
     * 22. WHEN SHOULD WE USE SINGLETON?
     *
     * Consider Singleton when:
     *
     *   - One shared instance is genuinely required.
     *   - The object represents a shared service or registry.
     *   - Centralized access is useful.
     *   - Its lifecycle is well understood.
     *   - Its concurrency behavior is properly designed.
     *
     * Possible examples:
     *
     *   - Immutable application configuration.
     *   - A carefully designed shared registry.
     *   - A stateless utility service where global access
     *     is genuinely appropriate.
     *
     *
     * 23. WHEN SHOULD WE AVOID SINGLETON?
     *
     * Avoid Singleton when:
     *
     *   - Multiple independent instances are required.
     *   - The object contains request-specific state.
     *   - Different users or tenants need isolated state.
     *   - Direct global access creates hidden dependencies.
     *   - Dependency injection provides a cleaner solution.
     *   - A shared mutable instance creates concurrency problems.
     *
     * Do not use Singleton simply because creating an object
     * appears expensive.
     *
     * First consider object lifecycle, caching, pooling,
     * and whether sharing is actually appropriate.
     *
     *
     * 24. SINGLETON VS STATIC CLASS
     *
     * SINGLETON:
     *
     *   - Represents an object.
     *   - Can implement interfaces.
     *   - Can be passed as a dependency.
     *   - Supports instance methods and instance state.
     *   - Can participate in polymorphism.
     *
     * STATIC CLASS:
     *
     * Java does not support top-level static classes.
     * A nested class can be declared static.
     *
     * A utility class with static methods is often used
     * when object identity and instance state are unnecessary.
     *
     * Utility class example:
     *
     * final class MathUtility {
     *
     *     private MathUtility() {
     *     }
     *
     *     public static int add(int a, int b) {
     *         return a + b;
     *     }
     * }
     *
     * DIFFERENCE:
     *
     * A Singleton is one object.
     *
     * A utility class provides static operations without
     * requiring an instance.
     *
     *
     * 25. SINGLETON VS FACTORY PATTERN
     *
     * SINGLETON:
     *
     * Controls access to one shared instance.
     *
     * FACTORY:
     *
     * Encapsulates or centralizes object creation.
     *
     * They solve different problems.
     *
     * A factory can create multiple objects.
     *
     * A factory can also return a shared Singleton instance
     * if that is part of its design.
     *
     *
     * 26. SINGLETON AND SOLID PRINCIPLES
     *
     * Singleton is not automatically a violation of SOLID.
     *
     * However, careless Singleton usage can create design problems.
     *
     * SRP:
     * A Singleton class should still have a focused responsibility.
     *
     * OCP:
     * Direct dependence on a concrete Singleton can make
     * replacement or extension difficult.
     *
     * LSP:
     * If the Singleton is exposed through an abstraction,
     * its implementation must honor that contract.
     *
     * ISP:
     * Do not expose a huge interface merely because it is
     * implemented by one Singleton.
     *
     * DIP:
     * Classes directly accessing a concrete Singleton through
     * a static method can create hidden dependencies.
     *
     * Dependency injection may be a better alternative
     * in many application architectures.
     *
     *
     * 27. COMMON MISTAKES AND MISCONCEPTIONS
     *
     * MISCONCEPTION 1:
     * Singleton means one object across every JVM and class loader.
     *
     * REALITY:
     * The guarantee depends on the implementation and scope.
     * Class-based implementations commonly provide one instance
     * per defining class loader.
     *
     *
     * MISCONCEPTION 2:
     * A private constructor alone guarantees Singleton.
     *
     * REALITY:
     * A private constructor prevents normal external construction,
     * but other mechanisms such as reflection, serialization
     * or cloning must also be considered where relevant.
     *
     *
     * MISCONCEPTION 3:
     * A thread-safe Singleton is automatically thread-safe.
     *
     * REALITY:
     * Safe instance creation and safe concurrent access to
     * mutable object state are separate concerns.
     *
     *
     * MISCONCEPTION 4:
     * Lazy initialization is always better than eager initialization.
     *
     * REALITY:
     * It depends on construction cost, initialization requirements
     * and whether the instance is actually needed.
     *
     *
     * MISCONCEPTION 5:
     * Spring singleton beans are implemented using the
     * traditional Singleton pattern.
     *
     * REALITY:
     * Spring manages singleton bean scope through its container.
     * It is not the same as a class controlling its own instance.
     *
     *
     * MISCONCEPTION 6:
     * One database connection should be shared by the entire
     * application because of Singleton.
     *
     * REALITY:
     * Connection pooling is generally more appropriate.
     *
     *
     * MISCONCEPTION 7:
     * Singleton is always an optimization.
     *
     * REALITY:
     * It may introduce global state, coupling and concurrency
     * problems. Sharing an instance is not automatically faster
     * or better.
     *
     *
     * 28. INTERVIEW QUESTIONS AND ANSWERS
     *
     * Q1. What is Singleton Design Pattern?
     *
     * A. A creational pattern that ensures a class has one
     *    instance within a defined scope and provides a
     *    global access point to it.
     *
     *
     * Q2. Which category does Singleton belong to?
     *
     * A. Creational Design Patterns.
     *
     *
     * Q3. What are the main components of a traditional
     *    Singleton implementation?
     *
     * A. Private constructor, private static instance variable
     *    and public static access method.
     *
     *
     * Q4. What is the difference between eager and lazy
     *    initialization?
     *
     * A. Eager initialization creates the instance during
     *    class initialization. Lazy initialization creates
     *    it when it is first requested.
     *
     *
     * Q5. Is basic lazy initialization thread-safe?
     *
     * A. No. Multiple threads can create multiple instances
     *    if they access the method concurrently.
     *
     *
     * Q6. How can we make lazy Singleton thread-safe?
     *
     * A. Using synchronized access, double-checked locking
     *    with volatile, or the initialization-on-demand
     *    holder idiom.
     *
     *
     * Q7. Why is volatile required in double-checked locking?
     *
     * A. It provides the necessary visibility and ordering
     *    guarantees so that another thread does not observe
     *    an incorrectly published instance.
     *
     *
     * Q8. What is the holder idiom?
     *
     * A. A Singleton technique using a nested static holder
     *    class to obtain lazy, thread-safe initialization
     *    through JVM class initialization guarantees.
     *
     *
     * Q9. Which is generally considered the strongest simple
     *    Singleton implementation in Java?
     *
     * A. Enum Singleton, especially when serialization and
     *    ordinary reflection resistance are important.
     *
     *
     * Q10. Can reflection break a traditional Singleton?
     *
     * A. It can bypass private-constructor access restrictions
     *     in permitted environments and create another instance.
     *
     *
     * Q11. Can serialization break Singleton?
     *
     * A. Ordinary deserialization can create a separate instance.
     *    A readResolve() method can preserve identity.
     *    Enum Singleton handles this automatically.
     *
     *
     * Q12. Can cloning break Singleton?
     *
     * A. Yes, if cloning is exposed and not properly prevented
     *    or handled.
     *
     *
     * Q13. Is Singleton thread-safe if its instance creation
     *     is thread-safe?
     *
     * A. Not necessarily. Mutable state and methods must also
     *    be designed for concurrent access.
     *
     *
     * Q14. Is Spring's singleton scope the same as the
     *     traditional Singleton pattern?
     *
     * A. No. Spring manages one bean instance per bean definition
     *    per IoC container.
     *
     *
     * Q15. Can a Singleton class implement an interface?
     *
     * A. Yes. A Singleton object is still an ordinary object
     *    and can implement interfaces.
     *
     *
     * Q16. Can a Singleton constructor accept parameters?
     *
     * A. A traditional static Singleton usually cannot obtain
     *    different constructor arguments for different callers.
     *    If initialization needs external configuration,
     *    dependency injection or a factory may be more suitable.
     *
     *
     * Q17. Is Singleton suitable for database connections?
     *
     * A. A single physical connection is generally not suitable
     *    for a concurrent backend application. A connection pool
     *    is usually preferred.
     *
     *
     * Q18. Can there be multiple Singleton instances?
     *
     * A. Yes, if the guarantee is scoped differently, such as
     *    separate class loaders, separate JVM processes,
     *    separate Spring containers or separate application
     *    contexts.
     *
     *
     * 29. QUICK REVISION
     *
     *   - Singleton is a Creational Design Pattern.
     *   - It provides one shared instance within a defined scope.
     *   - It commonly uses a private constructor.
     *   - It commonly uses a private static instance field.
     *   - It provides a static access method.
     *   - Eager initialization creates the object during
     *     class initialization.
     *   - Lazy initialization creates it on first access.
     *   - Basic lazy initialization is not thread-safe.
     *   - Synchronized methods provide thread-safe creation.
     *   - Double-checked locking requires volatile.
     *   - Holder idiom provides lazy, thread-safe initialization.
     *   - Enum Singleton provides strong built-in protections.
     *   - Thread-safe creation does not guarantee thread-safe state.
     *   - Spring singleton scope differs from classic Singleton.
     *   - Singleton should not be overused.
     *
     *
     * 30. FINAL MENTAL MODEL
     *
     * Normal object creation:
     *
     *   new Service()
     *   new Service()
     *   new Service()
     *
     *   Multiple independent objects can be created.
     *
     *
     * Singleton:
     *
     *   Service.getInstance()
     *   Service.getInstance()
     *   Service.getInstance()
     *
     *   All calls return the same shared instance, within
     *   the guarantee provided by the implementation.
     *
     * REMEMBER:
     *
     * Singleton is about controlling object creation and
     * providing shared access to one instance.
     *
     * Its real value is not merely having one object.
     * Its value is having one appropriately shared object
     * when the application's requirements justify it.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Singleton Design Pattern , Happy Learning ..!");
    }
}
