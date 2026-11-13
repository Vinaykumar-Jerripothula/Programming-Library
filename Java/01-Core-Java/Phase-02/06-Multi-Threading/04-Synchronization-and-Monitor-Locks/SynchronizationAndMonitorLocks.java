
public class SynchronizationAndMonitorLocks {

    /*
     * ============================================================
     * 1. WHY DO WE NEED SYNCHRONIZATION?
     * ============================================================
     *
     * Imagine a bank account with a balance of 1000.
     *
     * Two threads access the same account:
     *
     * Thread A withdraws 700.
     * Thread B withdraws 500.
     *
     * Both threads read the balance before either updates it.
     *
     * Both may conclude that sufficient balance is available.
     *
     * This can lead to an incorrect final balance.
     *
     * The problem occurs because multiple threads access
     * shared mutable data without proper coordination.
     *
     * Synchronization provides controlled access to shared data.
     *
     *
     * ============================================================
     * 2. RACE CONDITION
     * ============================================================
     *
     * A race condition occurs when the correctness of a program
     * depends on the unpredictable timing or ordering of threads.
     *
     * Example:
     *
     * class Counter {
     *
     *     int count = 0;
     *
     *     void increment() {
     *         count++;
     *     }
     * }
     *
     * Suppose two threads execute increment() simultaneously.
     *
     * Expected result:
     *
     * count = 2
     *
     * But count++ is not one indivisible operation.
     *
     * Conceptually, it involves:
     *
     * 1. Read count.
     * 2. Add 1.
     * 3. Write the result.
     *
     * Possible execution:
     *
     * Thread A reads 0.
     * Thread B reads 0.
     * Thread A calculates 1.
     * Thread B calculates 1.
     * Thread A writes 1.
     * Thread B writes 1.
     *
     * Actual result:
     *
     * count = 1
     *
     * One increment is lost.
     *
     * This is called a lost-update problem.
     *
     *
     * ============================================================
     * 3. CRITICAL SECTION
     * ============================================================
     *
     * A critical section is a section of code that accesses
     * shared mutable data or a shared resource and must be
     * protected against conflicting concurrent operations.
     *
     * Example:
     *
     * balance = balance - amount;
     *
     * If multiple threads modify balance concurrently,
     * this operation needs appropriate coordination.
     *
     * Synchronization can ensure that only one thread at a time
     * executes a particular protected critical section.
     *
     *
     * ============================================================
     * 4. MUTUAL EXCLUSION
     * ============================================================
     *
     * Mutual exclusion means that only one thread at a time
     * can enter a protected critical section guarded by
     * the same lock.
     *
     * Imagine a room with one key.
     *
     * - A thread obtains the key and enters the room.
     * - Other threads must wait.
     * - When the thread leaves, it releases the key.
     * - Another waiting thread can enter.
     *
     * Java intrinsic locks work using a similar idea.
     *
     *
     * ============================================================
     * 5. JAVA SYNCHRONIZATION
     * ============================================================
     *
     * Java provides the synchronized keyword.
     *
     * It can be used with:
     *
     * 1. Instance methods.
     * 2. Static methods.
     * 3. Blocks of code.
     *
     * Every Java object can be associated with an intrinsic lock,
     * also called a monitor lock.
     *
     * A thread must acquire the appropriate lock before
     * entering a synchronized method or block.
     *
     *
     * ============================================================
     * 6. SYNCHRONIZED INSTANCE METHOD
     * ============================================================
     *
     * Example:
     *
     * class Counter {
     *
     *     private int count = 0;
     *
     *     synchronized void increment() {
     *         count++;
     *     }
     *
     *     synchronized int getCount() {
     *         return count;
     *     }
     * }
     *
     * When a thread calls increment():
     *
     * 1. It attempts to acquire the object's intrinsic lock.
     * 2. If the lock is available, it enters the method.
     * 3. If another thread owns the lock, it waits.
     * 4. After the method completes, the lock is released.
     *
     * The same lock protects both synchronized instance methods
     * of the same object.
     *
     *
     * ============================================================
     * 7. SYNCHRONIZED BLOCK
     * ============================================================
     *
     * Sometimes synchronizing an entire method is unnecessary.
     *
     * We may want to protect only a small portion of code.
     *
     * Example:
     *
     * class Counter {
     *
     *     private int count = 0;
     *
     *     void increment() {
     *
     *         synchronized (this) {
     *             count++;
     *         }
     *     }
     * }
     *
     * synchronized (this) means:
     *
     * Acquire the intrinsic lock of the current object.
     *
     * The lock is held while the synchronized block executes.
     *
     * It is released when execution leaves the block,
     * including when an exception exits the block.
     *
     *
     * ============================================================
     * 8. SYNCHRONIZED METHOD VS BLOCK
     * ============================================================
     *
     * Synchronized method:
     *
     * synchronized void update() {
     *     // Entire method is protected.
     * }
     *
     * Synchronized block:
     *
     * void update() {
     *
     *     // Other work can happen without this lock.
     *
     *     synchronized (this) {
     *         // Only this section is protected.
     *     }
     * }
     *
     * A synchronized block provides more control over:
     *
     * - The code protected by the lock.
     * - The lock object being acquired.
     *
     * Use the smallest sensible critical section,
     * while keeping all related operations correctly protected.
     *
     *
     * ============================================================
     * 9. OBJECT LOCK
     * ============================================================
     *
     * Every Java object can act as an intrinsic lock.
     *
     * Example:
     *
     * class Account {
     *
     *     private int balance = 1000;
     *
     *     synchronized void withdraw(int amount) {
     *
     *         if (balance >= amount) {
     *             balance -= amount;
     *         }
     *     }
     * }
     *
     * The synchronized instance method locks the Account object.
     *
     * If two threads call withdraw() on the same Account object,
     * they cannot execute that synchronized method concurrently.
     *
     * IMPORTANT:
     *
     * Synchronization works only when competing threads use
     * the same lock object.
     *
     *
     * ============================================================
     * 10. DIFFERENT OBJECTS HAVE DIFFERENT LOCKS
     * ============================================================
     *
     * Example:
     *
     * Account account1 = new Account();
     * Account account2 = new Account();
     *
     * account1 and account2 are different objects.
     *
     * Each object has its own intrinsic lock.
     *
     * Therefore:
     *
     * Thread A can hold account1's lock.
     * Thread B can hold account2's lock.
     *
     * Both may execute synchronized instance methods
     * concurrently because they use different locks.
     *
     *
     * ============================================================
     * 11. STATIC SYNCHRONIZED METHOD
     * ============================================================
     *
     * A static synchronized method locks the Class object
     * associated with the class.
     *
     * Example:
     *
     * class Counter {
     *
     *     static int count = 0;
     *
     *     static synchronized void increment() {
     *         count++;
     *     }
     * }
     *
     * The lock is:
     *
     * Counter.class
     *
     * It is not the lock of an individual Counter instance.
     *
     *
     * ============================================================
     * 12. STATIC VS INSTANCE SYNCHRONIZATION
     * ============================================================
     *
     * Instance synchronized method:
     *
     * synchronized void display() {
     *     // Locks this object.
     * }
     *
     * Static synchronized method:
     *
     * static synchronized void display() {
     *     // Locks ClassName.class.
     * }
     *
     * These locks are different.
     *
     * A static synchronized method and an instance synchronized
     * method can execute concurrently because they use
     * different locks.
     *
     * Example:
     *
     * synchronized (this) {
     *     // Instance object's lock.
     * }
     *
     * synchronized (Counter.class) {
     *     // Class object's lock.
     * }
     *
     *
     * ============================================================
     * 13. LOCK RELEASE
     * ============================================================
     *
     * When does a synchronized lock get released?
     *
     * The JVM releases the intrinsic lock when execution leaves
     * the synchronized method or block.
     *
     * This includes:
     *
     * - Normal completion.
     * - Return statement.
     * - Exception escaping the synchronized region.
     *
     * You do not manually release an intrinsic lock.
     *
     * Java handles it automatically.
     *
     *
     * ============================================================
     * 14. SYNCHRONIZATION AND MEMORY VISIBILITY
     * ============================================================
     *
     * Synchronization does more than prevent simultaneous
     * execution of critical sections.
     *
     * It also establishes memory-visibility guarantees.
     *
     * Suppose Thread A updates a shared variable inside
     * a synchronized block.
     *
     * Thread B later acquires the same lock.
     *
     * The unlock by Thread A happens-before the subsequent
     * lock by Thread B.
     *
     * Therefore, Thread B is guaranteed to observe the
     * effects that happened before Thread A released the lock,
     * subject to the Java Memory Model rules.
     *
     * This is an important part of synchronization.
     *
     *
     * ============================================================
     * 15. HAPPENS-BEFORE RELATIONSHIP
     * ============================================================
     *
     * Happens-before is a relationship defined by the
     * Java Memory Model.
     *
     * It establishes ordering and visibility guarantees
     * between actions performed by different threads.
     *
     * Important rule:
     *
     * An unlock on a monitor happens-before every subsequent
     * lock on that same monitor.
     *
     * Example:
     *
     * synchronized (lock) {
     *     sharedValue = 100;
     * }
     *
     * Another thread:
     *
     * synchronized (lock) {
     *     System.out.println(sharedValue);
     * }
     *
     * If the second thread acquires the lock after the first
     * thread releases it, the write is visible to it.
     *
     * The same lock is essential.
     *
     *
     * ============================================================
     * 16. SYNCHRONIZATION AND VISIBILITY
     * ============================================================
     *
     * Consider:
     *
     * class SharedData {
     *
     *     boolean ready = false;
     *
     *     void update() {
     *         ready = true;
     *     }
     *
     *     void read() {
     *         while (!ready) {
     *             // Wait.
     *         }
     *     }
     * }
     *
     * Without suitable synchronization or another visibility
     * mechanism, the reading thread is not guaranteed to
     * observe the update.
     *
     * Protecting both accesses with the same lock can establish
     * the required visibility relationship.
     *
     * The volatile keyword is another mechanism for visibility;
     * it is covered in the Java Memory Model section.
     *
     *
     * ============================================================
     * 17. REENTRANT SYNCHRONIZATION
     * ============================================================
     *
     * Java intrinsic locks are reentrant.
     *
     * Reentrant means:
     *
     * A thread that already owns a lock can acquire that
     * same lock again.
     *
     * Example:
     *
     * class Demo {
     *
     *     synchronized void methodA() {
     *
     *         System.out.println("A");
     *
     *         methodB();
     *     }
     *
     *     synchronized void methodB() {
     *
     *         System.out.println("B");
     *     }
     * }
     *
     * When a thread calls methodA():
     *
     * 1. It acquires this object's lock.
     * 2. It enters methodA().
     * 3. methodA() calls methodB().
     * 4. methodB() attempts to acquire the same lock.
     * 5. The thread already owns the lock.
     * 6. It can enter methodB().
     *
     * The thread does not block itself.
     *
     * The JVM maintains ownership and reentrancy information
     * for intrinsic locks.
     *
     *
     * ============================================================
     * 18. LOCK OWNERSHIP AND THREAD STATES
     * ============================================================
     *
     * When a thread tries to enter a synchronized region
     * whose lock is owned by another thread, it cannot proceed.
     *
     * It enters the BLOCKED state while waiting to acquire
     * that monitor lock.
     *
     * Once the lock becomes available, it may acquire it
     * and continue.
     *
     * IMPORTANT:
     *
     * BLOCKED specifically describes waiting to acquire
     * an intrinsic monitor lock.
     *
     * It is different from WAITING and TIMED_WAITING.
     *
     *
     * ============================================================
     * 19. SYNCHRONIZATION DOES NOT AUTOMATICALLY PROTECT
     *     EVERY ACCESS TO A VARIABLE
     * ============================================================
     *
     * Example:
     *
     * class Counter {
     *
     *     int count = 0;
     *
     *     synchronized void increment() {
     *         count++;
     *     }
     *
     *     int getCount() {
     *         return count;
     *     }
     * }
     *
     * increment() is synchronized.
     *
     * getCount() is not synchronized.
     *
     * The read in getCount() does not acquire the same lock.
     *
     * Therefore, it does not receive the same lock-based
     * visibility guarantee.
     *
     * If consistent synchronized access is required,
     * protect reads and writes using the same lock.
     *
     *
     * ============================================================
     * 20. SYNCHRONIZATION AND INHERITANCE
     * ============================================================
     *
     * A synchronized method can be overridden.
     *
     * The synchronized modifier is not automatically inherited
     * by an overriding method.
     *
     * Example:
     *
     * class Parent {
     *
     *     synchronized void display() {
     *         System.out.println("Parent");
     *     }
     * }
     *
     * class Child extends Parent {
     *
     *     @Override
     *     void display() {
     *         System.out.println("Child");
     *     }
     * }
     *
     * Child.display() is not synchronized.
     *
     * If synchronization is required in the overriding method,
     * declare it synchronized explicitly.
     *
     *
     * ============================================================
     * 21. SYNCHRONIZATION AND CONSTRUCTORS
     * ============================================================
     *
     * Constructors cannot be declared synchronized.
     *
     * Example:
     *
     * synchronized MyClass() {
     *     // Compilation error.
     * }
     *
     * Why?
     *
     * A constructor is used to initialize a new object.
     *
     * The object should not normally be shared with other
     * threads before construction is safely completed.
     *
     * If construction publishes this object to other threads,
     * additional care is required.
     *
     * The synchronized keyword cannot be applied to a
     * constructor declaration.
     *
     *
     * ============================================================
     * 22. SYNCHRONIZATION AND LAMBDA EXPRESSIONS
     * ============================================================
     *
     * A synchronized block can be used inside a lambda.
     *
     * Example:
     *
     * Object lock = new Object();
     *
     * Runnable task = () -> {
     *
     *     synchronized (lock) {
     *         System.out.println("Protected task");
     *     }
     * };
     *
     * The lambda does not change the rules of synchronization.
     *
     *
     * ============================================================
     * 23. CHOOSING A LOCK OBJECT
     * ============================================================
     *
     * You can synchronize on any non-null object reference.
     *
     * Example:
     *
     * private final Object lock = new Object();
     *
     * void update() {
     *
     *     synchronized (lock) {
     *         // Protected operations.
     *     }
     * }
     *
     * Prefer a private final lock object when you need
     * a dedicated lock.
     *
     * WHY PRIVATE?
     *
     * Other classes cannot directly acquire the lock
     * through that field.
     *
     * WHY FINAL?
     *
     * The lock reference cannot be reassigned after construction.
     *
     * A public lock object can allow unrelated code to acquire
     * the same monitor and unexpectedly block your operations.
     *
     *
     * ============================================================
     * 24. WHY NOT SYNCHRONIZE ON STRING LITERALS?
     * ============================================================
     *
     * Avoid using publicly accessible or interned objects
     * as private application locks.
     *
     * Example to avoid:
     *
     * synchronized ("LOCK") {
     *     // Critical section.
     * }
     *
     * String literals may refer to shared interned objects.
     *
     * Unrelated code may synchronize on the same object.
     *
     * Prefer:
     *
     * private final Object lock = new Object();
     *
     *
     * ============================================================
     * 25. SYNCHRONIZATION AND NULL
     * ============================================================
     *
     * Synchronizing on null causes NullPointerException.
     *
     * Example:
     *
     * Object lock = null;
     *
     * synchronized (lock) {
     *     // Never entered.
     * }
     *
     * Always ensure that the lock reference is non-null.
     *
     *
     * ============================================================
     * 26. SYNCHRONIZATION AND PERFORMANCE
     * ============================================================
     *
     * Synchronization has some costs:
     *
     * - Threads may block while waiting for a lock.
     * - Lock contention can reduce throughput.
     * - Excessive locking can reduce concurrency.
     * - Large critical sections can increase waiting time.
     *
     * Modern JVMs optimize many synchronization operations.
     *
     * Do not assume every synchronized operation is
     * automatically expensive.
     *
     * First write correct code.
     *
     * Then measure performance before optimizing.
     *
     *
     * ============================================================
     * 27. LOCK CONTENTION
     * ============================================================
     *
     * Lock contention occurs when multiple threads frequently
     * compete for the same lock.
     *
     * Example:
     *
     * Many threads call the same synchronized method
     * on one shared object.
     *
     * They may spend significant time waiting.
     *
     * Ways to reduce unnecessary contention:
     *
     * - Keep critical sections reasonably small.
     * - Avoid unrelated work while holding a lock.
     * - Avoid unnecessary shared mutable state.
     * - Use suitable concurrent data structures.
     * - Consider other synchronization mechanisms when needed.
     *
     *
     * ============================================================
     * 28. SYNCHRONIZATION VS ATOMICITY
     * ============================================================
     *
     * Atomicity means an operation appears indivisible
     * to other threads.
     *
     * Synchronization can provide mutual exclusion for
     * a group of operations.
     *
     * Example:
     *
     * synchronized void transfer(Account target, int amount) {
     *
     *     // Validate and update related account balances.
     * }
     *
     * Protecting the complete operation can prevent other
     * threads from observing intermediate states through
     * code that uses the same lock.
     *
     * Atomic classes provide atomic operations for specific
     * variables and are covered separately.
     *
     *
     * ============================================================
     * 29. SYNCHRONIZATION VS VOLATILE
     * ============================================================
     *
     * synchronized:
     *
     * - Provides mutual exclusion for a lock.
     * - Provides memory-visibility guarantees.
     * - Can protect compound operations.
     * - Uses intrinsic monitor locks.
     *
     * volatile:
     *
     * - Provides visibility and ordering guarantees
     *   for a variable.
     * - Does not provide mutual exclusion.
     * - Does not make compound operations such as count++
     *   atomic.
     *
     * Example:
     *
     * volatile int count = 0;
     *
     * count++;
     *
     * This increment is still not atomic.
     *
     *
     * ============================================================
     * 30. SYNCHRONIZATION VS LOCK INTERFACE
     * ============================================================
     *
     * synchronized:
     *
     * - Built into the Java language.
     * - Uses intrinsic monitor locks.
     * - Lock acquisition and release are automatic.
     * - Lock acquisition cannot be interrupted.
     *
     * Lock interface:
     *
     * - Explicit locking API.
     * - Provides methods such as lock() and unlock().
     * - Supports additional features such as tryLock()
     *   and interruptible lock acquisition.
     *
     * ReentrantLock is a common implementation.
     *
     * Explicit locks are covered in the next synchronization
     * topic.
     *
     *
     * ============================================================
     * 31. COMMON SYNCHRONIZATION MISTAKES
     * ============================================================
     *
     * 1. Synchronizing different objects while protecting
     *    the same shared variable.
     *
     * 2. Synchronizing only writes but not related reads.
     *
     * 3. Assuming synchronized methods on different instances
     *    use the same lock.
     *
     * 4. Assuming static synchronized methods lock an instance.
     *
     * 5. Keeping critical sections unnecessarily large.
     *
     * 6. Synchronizing on publicly accessible objects.
     *
     * 7. Using mutable objects as locks when their references
     *    can change.
     *
     * 8. Assuming volatile makes count++ atomic.
     *
     * 9. Assuming synchronized automatically protects code
     *    that does not acquire the same lock.
     *
     * 10. Ignoring lock ordering when acquiring multiple locks.
     *
     *
     * ============================================================
     * 32. INTERVIEW REVISION
     * ============================================================
     *
     * Q1. What is synchronization?
     *
     * Q2. What is a race condition?
     *
     * Q3. What is a critical section?
     *
     * Q4. What is mutual exclusion?
     *
     * Q5. What is an intrinsic lock?
     *
     * Q6. What is a monitor lock?
     *
     * Q7. What is the difference between synchronized
     *     method and synchronized block?
     *
     * Q8. What object is locked by an instance synchronized
     *     method?
     *
     * Q9. What object is locked by a static synchronized method?
     *
     * Q10. Can two threads execute synchronized methods
     *      on different objects at the same time?
     *
     * Q11. What is reentrant synchronization?
     *
     * Q12. When is a synchronized lock released?
     *
     * Q13. How does synchronization provide visibility?
     *
     * Q14. What is happens-before?
     *
     * Q15. Does synchronization protect an unsynchronized read?
     *
     * Q16. Is synchronization inherited by an overriding method?
     *
     * Q17. Can a constructor be synchronized?
     *
     * Q18. Why should we avoid synchronizing on String literals?
     *
     * Q19. What is lock contention?
     *
     * Q20. What is the difference between synchronized and volatile?
     *
     *
     * ============================================================
     * 33. FINAL TAKEAWAY
     * ============================================================
     *
     * Race condition:
     * A correctness problem caused by uncontrolled concurrent
     * access and timing.
     *
     * Critical section:
     * Code that needs coordinated access to shared state.
     *
     * synchronized:
     * Acquires an intrinsic lock before entering protected code.
     *
     * Instance synchronized method:
     * Locks the current object.
     *
     * Static synchronized method:
     * Locks the corresponding Class object.
     *
     * Synchronized block:
     * Protects a selected section using a chosen lock object.
     *
     * Reentrancy:
     * A thread can acquire a lock it already owns.
     *
     * Happens-before:
     * Provides ordering and visibility guarantees.
     *
     * Remember:
     *
     * Synchronization is not just about allowing one thread
     * at a time.
     *
     * It also ensures that threads see each other's updates
     * according to the Java Memory Model.
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Synchronization and Monitor Locks , Happy Learning ..!");
    }
}
