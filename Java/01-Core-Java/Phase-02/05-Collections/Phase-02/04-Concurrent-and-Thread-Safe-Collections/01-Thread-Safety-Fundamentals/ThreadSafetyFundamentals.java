
public class ThreadSafetyFundamentals {

    /*
     * ============================================================
     * THREAD SAFETY FUNDAMENTALS
     * ============================================================
     *
     * 1. WHY DO WE NEED THREAD SAFETY?
     *
     * Imagine an application where 100 users access the same
     * bank account at the same time.
     *
     * Account balance = 1000
     *
     * Two users try to withdraw 500 simultaneously.
     *
     * Both threads might read the balance as 1000.
     *
     * Both calculate:
     *
     * 1000 - 500 = 500
     *
     * Both write 500.
     *
     * Expected balance = 0
     * Actual balance   = 500
     *
     * One withdrawal was effectively lost.
     *
     * This is a concurrency problem.
     *
     * Thread safety helps prevent incorrect behavior when
     * multiple threads access shared data.
     *
     *
     * ============================================================
     * 2. WHAT IS A THREAD?
     * ============================================================
     *
     * A thread is an independent path of execution
     * within a process.
     *
     * A Java application can execute multiple threads.
     *
     * Example:
     *
     * Thread 1 -> Handles a user request.
     * Thread 2 -> Processes another request.
     * Thread 3 -> Writes data to a file.
     *
     * Threads within the same process share resources,
     * including heap-allocated objects.
     *
     * Each thread has its own execution stack.
     *
     * Shared objects are one of the main reasons
     * thread safety matters.
     *
     *
     * ============================================================
     * 3. WHAT IS CONCURRENCY?
     * ============================================================
     *
     * Concurrency means managing multiple tasks whose
     * execution overlaps in time.
     *
     * Tasks may make progress by taking turns on a single CPU
     * core or by running simultaneously on multiple cores.
     *
     * Example:
     *
     * Thread A -> Reads data.
     * Thread B -> Processes data.
     *
     * Their execution can overlap.
     *
     * IMPORTANT:
     *
     * Concurrency is about dealing with multiple tasks
     * during overlapping periods.
     *
     * Parallelism means tasks actually execute simultaneously.
     *
     * Concurrency does not always require parallel execution.
     *
     *
     * ============================================================
     * 4. WHAT IS SHARED MUTABLE STATE?
     * ============================================================
     *
     * Shared state:
     * Data that multiple threads can access.
     *
     * Mutable state:
     * Data that can be changed after creation.
     *
     * Shared mutable state:
     * Data that multiple threads can access and modify.
     *
     * Example:
     *
     * class BankAccount {
     *     int balance = 1000;
     * }
     *
     * If multiple threads access the same BankAccount object,
     * they share its balance field.
     *
     * If multiple threads modify it without coordination,
     * incorrect results may occur.
     *
     * IMPORTANT:
     *
     * Shared mutable state is a major source of
     * concurrency bugs.
     *
     *
     * ============================================================
     * 5. WHAT IS THREAD SAFETY?
     * ============================================================
     *
     * A class or operation is thread-safe when it behaves
     * correctly under the concurrency conditions it supports.
     *
     * It must preserve its required correctness guarantees
     * even when multiple threads interact with it.
     *
     * Example:
     *
     * If multiple threads update a shared counter,
     * a thread-safe implementation ensures that updates
     * are not lost.
     *
     * Thread safety can be achieved using:
     *
     * - Synchronization.
     * - Locks.
     * - Atomic variables.
     * - Immutable objects.
     * - Thread confinement.
     * - Concurrent data structures.
     *
     *
     * ============================================================
     * 6. WHAT IS A RACE CONDITION?
     * ============================================================
     *
     * A race condition occurs when the correctness of a program
     * depends on the timing or interleaving of operations
     * performed by multiple threads.
     *
     * Example:
     *
     * class Counter {
     *     int count = 0;
     *
     *     void increment() {
     *         count++;
     *     }
     * }
     *
     * Suppose two threads execute increment() simultaneously.
     *
     * Expected:
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
     * Final count = 1.
     *
     * One increment is lost.
     *
     * This is called a lost update.
     *
     *
     * ============================================================
     * 7. WHAT IS A CRITICAL SECTION?
     * ============================================================
     *
     * A critical section is a part of a program that accesses
     * shared data or resources and requires coordination
     * between threads.
     *
     * Example:
     *
     * void withdraw(int amount) {
     *
     *     // Critical section
     *
     *     if (balance >= amount) {
     *         balance -= amount;
     *     }
     * }
     *
     * If multiple threads execute this operation concurrently,
     * they might interfere with one another.
     *
     * We need a mechanism to protect this section.
     *
     * Synchronization and locks are common solutions.
     *
     *
     * ============================================================
     * 8. WHAT IS SYNCHRONIZATION?
     * ============================================================
     *
     * Synchronization coordinates access to shared resources
     * between threads.
     *
     * It can ensure that only one thread at a time executes
     * a protected critical section using the same lock.
     *
     * Java provides the synchronized keyword.
     *
     * Example:
     *
     * class Counter {
     *
     *     private int count = 0;
     *
     *     public synchronized void increment() {
     *         count++;
     *     }
     * }
     *
     * When a thread enters this synchronized instance method,
     * it must acquire the object's intrinsic monitor lock.
     *
     * Another thread attempting to enter a synchronized
     * instance method or block guarded by the same object
     * monitor must wait until the lock becomes available.
     *
     * IMPORTANT:
     *
     * Synchronization works only when threads coordinate
     * using the same lock.
     *
     * It does not automatically protect an object from
     * code that accesses it without acquiring that lock.
     *
     *
     * ============================================================
     * 9. OBJECT LOCK AND CLASS LOCK
     * ============================================================
     *
     * OBJECT LOCK:
     *
     * Every Java object can be used as an intrinsic monitor.
     *
     * A synchronized instance method acquires the monitor
     * associated with the current object (this).
     *
     * Example:
     *
     * synchronized void display() {
     *     // Uses this object's monitor.
     * }
     *
     * CLASS LOCK:
     *
     * A synchronized static method acquires the monitor
     * associated with the Class object.
     *
     * Example:
     *
     * static synchronized void display() {
     *     // Uses ClassName.class monitor.
     * }
     *
     * IMPORTANT:
     *
     * An object lock and a class lock are different locks.
     *
     * Two threads can execute a synchronized instance method
     * and a synchronized static method of the same class
     * simultaneously because they acquire different monitors.
     *
     *
     * ============================================================
     * 10. WHAT IS ATOMICITY?
     * ============================================================
     *
     * Atomicity means an operation happens as one indivisible
     * unit from the perspective of other threads.
     *
     * An operation is atomic when other threads cannot observe
     * it in a partially completed state.
     *
     * Example:
     *
     * count++;
     *
     * This is not atomic for an ordinary shared int variable.
     *
     * Java provides AtomicInteger:
     *
     * AtomicInteger count = new AtomicInteger(0);
     *
     * count.incrementAndGet();
     *
     * The increment operation is atomic.
     *
     * IMPORTANT:
     *
     * Atomicity does not automatically make an entire
     * sequence of multiple operations atomic.
     *
     *
     * ============================================================
     * 11. WHAT IS VISIBILITY?
     * ============================================================
     *
     * Visibility means one thread can observe changes
     * made by another thread.
     *
     * Consider:
     *
     * class Worker {
     *
     *     boolean running = true;
     *
     *     void work() {
     *
     *         while (running) {
     *             // Continue working
     *         }
     *     }
     *
     *     void stop() {
     *         running = false;
     *     }
     * }
     *
     * One thread runs work().
     *
     * Another thread calls stop().
     *
     * Without proper synchronization, the first thread
     * is not guaranteed to observe the updated value.
     *
     * The Java Memory Model defines the rules governing
     * visibility between threads.
     *
     * The volatile keyword is one way to establish
     * visibility guarantees.
     *
     *
     * ============================================================
     * 12. WHAT IS THE VOLATILE KEYWORD?
     * ============================================================
     *
     * volatile is used with fields shared between threads.
     *
     * Example:
     *
     * class Worker {
     *
     *     private volatile boolean running = true;
     *
     *     void work() {
     *         while (running) {
     *             // Work
     *         }
     *     }
     *
     *     void stop() {
     *         running = false;
     *     }
     * }
     *
     * A write to a volatile field happens-before
     * a subsequent read of that field that observes the write.
     *
     * This provides visibility and ordering guarantees.
     *
     * IMPORTANT:
     *
     * volatile does not make compound operations atomic.
     *
     * Example:
     *
     * volatile int count = 0;
     *
     * count++;
     *
     * This is still not an atomic increment.
     *
     * Use AtomicInteger or synchronization when an atomic
     * read-modify-write operation is required.
     *
     *
     * ============================================================
     * 13. WHAT IS THE JAVA MEMORY MODEL (JMM)?
     * ============================================================
     *
     * The Java Memory Model defines how threads interact
     * through memory.
     *
     * It specifies:
     *
     * - Visibility of shared variable updates.
     * - Ordering of memory operations.
     * - Synchronization relationships.
     * - Happens-before guarantees.
     *
     * Without the JMM, reasoning about concurrent programs
     * would be extremely difficult.
     *
     * The JMM does not simply say that every thread always
     * reads directly from main memory.
     *
     * It defines observable behavior and ordering guarantees
     * for correctly synchronized programs.
     *
     *
     * ============================================================
     * 14. WHAT IS HAPPENS-BEFORE?
     * ============================================================
     *
     * Happens-before is a relationship defined by the
     * Java Memory Model.
     *
     * If action A happens-before action B:
     *
     * - A is visible to B according to the JMM guarantees.
     * - The required ordering between A and B is established.
     *
     * Important happens-before relationships:
     *
     * 1. Program order:
     *
     *    Within a thread, earlier actions happen-before
     *    later actions in program order.
     *
     * 2. Monitor lock:
     *
     *    An unlock of a monitor happens-before every
     *    subsequent lock of that same monitor.
     *
     * 3. Volatile:
     *
     *    A write to a volatile field happens-before
     *    a subsequent read of that field that observes it.
     *
     * 4. Thread start:
     *
     *    A call to start() happens-before actions in
     *    the started thread.
     *
     * 5. Thread termination:
     *
     *    All actions in a thread happen-before another thread
     *    successfully detects its termination, for example
     *    through a successful join().
     *
     * 6. Transitivity:
     *
     *    If A happens-before B and B happens-before C,
     *    then A happens-before C.
     *
     *
     * ============================================================
     * 15. WHAT IS DEADLOCK?
     * ============================================================
     *
     * Deadlock occurs when two or more threads wait forever
     * for resources held by one another.
     *
     * Example:
     *
     * Thread A:
     * Holds Lock 1.
     * Waits for Lock 2.
     *
     * Thread B:
     * Holds Lock 2.
     * Waits for Lock 1.
     *
     * Neither thread can continue.
     *
     * Four classic necessary conditions:
     *
     * 1. Mutual exclusion:
     *    A resource can be held by only one thread at a time.
     *
     * 2. Hold and wait:
     *    A thread holds one resource while waiting for another.
     *
     * 3. No preemption:
     *    Resources cannot be forcibly taken away.
     *
     * 4. Circular wait:
     *    A circular chain of threads waits for resources.
     *
     * Prevention techniques:
     *
     * - Acquire locks in a consistent global order.
     * - Avoid unnecessary nested locking.
     * - Use timed lock attempts where appropriate.
     * - Keep critical sections small.
     *
     *
     * ============================================================
     * 16. WHAT IS STARVATION?
     * ============================================================
     *
     * Starvation occurs when a thread repeatedly fails
     * to obtain the CPU time or resources it needs
     * because other threads keep getting priority.
     *
     * A thread may remain unable to make progress
     * for an indefinite period.
     *
     * Example:
     *
     * A low-priority thread rarely gets CPU time because
     * higher-priority threads keep running.
     *
     * Another possibility is a thread repeatedly losing
     * access to a heavily contended lock.
     *
     * Possible solutions:
     *
     * - Fair locks where appropriate.
     * - Reasonable thread priorities.
     * - Avoiding excessive lock contention.
     *
     *
     * ============================================================
     * 17. WHAT IS LIVENESS?
     * ============================================================
     *
     * Liveness means a concurrent program can continue
     * making progress.
     *
     * Safety:
     * Nothing incorrect happens.
     *
     * Liveness:
     * Something useful eventually happens.
     *
     * Common liveness problems:
     *
     * - Deadlock.
     * - Starvation.
     * - Livelock.
     *
     *
     * ============================================================
     * 18. WHAT IS LIVELOCK?
     * ============================================================
     *
     * Livelock occurs when threads remain active but
     * repeatedly respond to one another in a way that
     * prevents useful progress.
     *
     * Unlike deadlock:
     *
     * Deadlock:
     * Threads are blocked waiting.
     *
     * Livelock:
     * Threads are active but cannot make progress.
     *
     * Example:
     *
     * Two people approaching each other in a narrow corridor.
     *
     * Both move left.
     * Both move right.
     * Both move left again.
     *
     * They keep reacting but never pass each other.
     *
     *
     * ============================================================
     * 19. WHAT IS MUTUAL EXCLUSION?
     * ============================================================
     *
     * Mutual exclusion ensures that only one thread at a time
     * enters a protected critical section guarded by a
     * particular lock.
     *
     * Java mechanisms:
     *
     * - synchronized.
     * - ReentrantLock.
     * - Other locking utilities.
     *
     * IMPORTANT:
     *
     * Mutual exclusion applies to threads coordinating
     * through the same lock.
     *
     *
     * ============================================================
     * 20. WHAT IS AN ATOMIC VARIABLE?
     * ============================================================
     *
     * Java provides atomic classes in:
     *
     * java.util.concurrent.atomic
     *
     * Examples:
     *
     * - AtomicInteger.
     * - AtomicLong.
     * - AtomicBoolean.
     * - AtomicReference.
     * - LongAdder.
     *
     * Example:
     *
     * AtomicInteger counter = new AtomicInteger(0);
     *
     * counter.incrementAndGet();
     *
     * Atomic classes provide thread-safe atomic operations
     * without requiring ordinary synchronized blocks
     * for every operation.
     *
     * Many atomic classes use compare-and-set techniques.
     *
     * LongAdder is useful for high-contention statistics
     * counters, where its design can improve throughput
     * compared with a single AtomicLong.
     *
     *
     * ============================================================
     * 21. WHAT IS CAS?
     * ============================================================
     *
     * CAS means Compare-And-Set (also called Compare-And-Swap).
     *
     * It is an atomic operation.
     *
     * Conceptual process:
     *
     * 1. Read the current value.
     * 2. Compare it with an expected value.
     * 3. If it matches, update it.
     * 4. If it does not match, leave it unchanged.
     *
     * Example:
     *
     * AtomicInteger count = new AtomicInteger(5);
     *
     * count.compareAndSet(5, 10);
     *
     * If the current value is 5:
     *
     * The value becomes 10.
     *
     * If the current value is not 5:
     *
     * The update fails.
     *
     * CAS is useful for implementing lock-free algorithms.
     *
     * IMPORTANT:
     *
     * CAS does not automatically solve every concurrency problem.
     *
     * Algorithms must still handle retries, contention,
     * memory ordering and possible ABA issues.
     *
     *
     * ============================================================
     * 22. WHAT IS THE ABA PROBLEM?
     * ============================================================
     *
     * Consider a thread reading value A.
     *
     * Another thread changes:
     *
     * A -> B -> A
     *
     * The first thread performs CAS expecting A.
     *
     * The comparison succeeds because the current value
     * is A again.
     *
     * However, the value changed in between.
     *
     * This is called the ABA problem.
     *
     * One solution:
     *
     * AtomicStampedReference.
     *
     * It associates a stamp/version with the reference,
     * allowing changes to be detected even if the reference
     * returns to its previous value.
     *
     *
     * ============================================================
     * 23. IMMUTABILITY AND THREAD SAFETY
     * ============================================================
     *
     * An immutable object cannot be changed after construction.
     *
     * Example:
     *
     * String value = "Java";
     *
     * Strings are immutable.
     *
     * Their contents cannot be modified after creation.
     *
     * Immutable objects are naturally easier to share
     * between threads because their state cannot be changed.
     *
     * Good immutable-class practices:
     *
     * - Make the class final when appropriate.
     * - Make fields private and final.
     * - Initialize fields completely in the constructor.
     * - Do not expose mutable internal objects directly.
     * - Use defensive copies when needed.
     *
     * IMPORTANT:
     *
     * A final reference does not make the referenced object
     * immutable.
     *
     * final List<String> names = new ArrayList<>();
     *
     * The reference cannot be reassigned, but the list
     * can still be modified.
     *
     *
     * ============================================================
     * 24. THREAD CONFINEMENT
     * ============================================================
     *
     * Thread confinement means an object is accessed
     * by only one thread.
     *
     * If an object is not shared between threads,
     * many synchronization problems disappear.
     *
     * Example:
     *
     * A local variable created inside a method is normally
     * confined to the executing thread, provided it is not
     * published or shared with another thread.
     *
     * Thread confinement is a useful way to reduce
     * shared mutable state.
     *
     *
     * ============================================================
     * 25. WHAT IS SAFE PUBLICATION?
     * ============================================================
     *
     * Safe publication means making an object available
     * to other threads in a way that ensures they can observe
     * its properly initialized state.
     *
     * Common safe-publication techniques include:
     *
     * - Storing a reference in a properly synchronized field.
     * - Publishing through a volatile field.
     * - Publishing through a final field under its
     *   construction guarantees.
     * - Publishing through thread-safe collections.
     * - Using static initialization.
     *
     * Avoid allowing another thread to observe an object
     * before its construction is complete.
     *
     *
     * ============================================================
     * 26. THREAD-SAFE VS SYNCHRONIZED COLLECTIONS
     * ============================================================
     *
     * Thread-safe collection:
     *
     * A collection designed to support concurrent access
     * according to its documented guarantees.
     *
     * Synchronized collection:
     *
     * A collection whose operations are protected using
     * synchronization.
     *
     * Examples:
     *
     * Collections.synchronizedList(new ArrayList<>());
     *
     * ConcurrentHashMap.
     *
     * CopyOnWriteArrayList.
     *
     * These collections use different strategies.
     *
     * IMPORTANT:
     *
     * Thread-safe does not mean every sequence of multiple
     * collection operations is automatically atomic.
     *
     * Example:
     *
     * if (!map.containsKey(key)) {
     *     map.put(key, value);
     * }
     *
     * Another thread may modify the map between the check
     * and the insertion.
     *
     * Prefer atomic operations such as putIfAbsent()
     * when supported and appropriate.
     *
     *
     * ============================================================
     * 27. WHY ITERATION NEEDS SPECIAL CARE
     * ============================================================
     *
     * A collection may be thread-safe for individual operations
     * while iteration still requires special handling.
     *
     * Example:
     *
     * List<Integer> list =
     *     Collections.synchronizedList(new ArrayList<>());
     *
     * Synchronize on the returned list while iterating:
     *
     * synchronized (list) {
     *
     *     for (Integer value : list) {
     *         System.out.println(value);
     *     }
     * }
     *
     * This prevents other threads that follow the same
     * synchronization protocol from modifying the list
     * during this traversal.
     *
     * Concurrent collections may provide different iterator
     * behavior, such as snapshot or weakly consistent iteration.
     *
     *
     * ============================================================
     * 28. THREAD SAFETY LEVELS
     * ============================================================
     *
     * THREAD-UNSAFE:
     *
     * No built-in coordination for concurrent modifications.
     *
     * Examples:
     *
     * ArrayList, HashMap, HashSet.
     *
     * These can still be used safely when confined to one thread
     * or protected by suitable external coordination.
     *
     * SYNCHRONIZED:
     *
     * Uses locking to coordinate access.
     *
     * Examples:
     *
     * Vector, Hashtable, synchronized collection wrappers.
     *
     * CONCURRENT:
     *
     * Designed for concurrent access using strategies such as
     * fine-grained locking, CAS, snapshots or non-blocking
     * algorithms.
     *
     * Examples:
     *
     * ConcurrentHashMap.
     * ConcurrentLinkedQueue.
     * CopyOnWriteArrayList.
     *
     *
     * ============================================================
     * 29. COMMON CONCURRENCY MISTAKES
     * ============================================================
     *
     * 1. Assuming count++ is atomic.
     *
     * 2. Assuming volatile makes every operation thread-safe.
     *
     * 3. Assuming synchronized protects data from threads
     *    that do not use the same lock.
     *
     * 4. Confusing concurrency with parallelism.
     *
     * 5. Assuming every thread-safe method makes a sequence
     *    of method calls atomic.
     *
     * 6. Ignoring visibility problems.
     *
     * 7. Modifying shared collections without coordination.
     *
     * 8. Assuming final makes a referenced object immutable.
     *
     * 9. Acquiring locks in inconsistent orders.
     *
     * 10. Using excessive synchronization and creating
     *     unnecessary contention.
     *
     * 11. Ignoring safe publication.
     *
     * 12. Assuming CAS automatically prevents ABA problems.
     *
     *
     * ============================================================
     * 30. IMPORTANT INTERVIEW QUESTIONS
     * ============================================================
     *
     * 1. What is a thread?
     *
     * 2. What is concurrency?
     *
     * 3. Difference between concurrency and parallelism?
     *
     * 4. What is thread safety?
     *
     * 5. What is shared mutable state?
     *
     * 6. What is a race condition?
     *
     * 7. What is a critical section?
     *
     * 8. What is synchronization?
     *
     * 9. What is the difference between object lock and class lock?
     *
     * 10. What is atomicity?
     *
     * 11. What is visibility?
     *
     * 12. What is the Java Memory Model?
     *
     * 13. What is happens-before?
     *
     * 14. What is the difference between synchronized and volatile?
     *
     * 15. What is deadlock?
     *
     * 16. What are the four necessary conditions for deadlock?
     *
     * 17. Difference between deadlock, starvation and livelock?
     *
     * 18. What is CAS?
     *
     * 19. What is the ABA problem?
     *
     * 20. How do immutable objects help with thread safety?
     *
     * 21. What is thread confinement?
     *
     * 22. What is safe publication?
     *
     * 23. Why is a thread-safe collection not necessarily
     *     enough to make compound operations atomic?
     *
     *
     * ============================================================
     * 31. QUICK REVISION
     * ============================================================
     *
     * Thread:
     *     -> Independent path of execution.
     *
     * Concurrency:
     *     -> Managing overlapping tasks.
     *
     * Parallelism:
     *     -> Actual simultaneous execution.
     *
     * Shared mutable state:
     *     -> Data multiple threads can access and modify.
     *
     * Thread safety:
     *     -> Correct behavior under supported concurrent access.
     *
     * Race condition:
     *     -> Result depends on timing/interleaving.
     *
     * Critical section:
     *     -> Code requiring coordinated access.
     *
     * Synchronization:
     *     -> Coordinates access using locks.
     *
     * Atomicity:
     *     -> Operation behaves as one indivisible unit.
     *
     * Visibility:
     *     -> One thread can observe another thread's updates.
     *
     * volatile:
     *     -> Provides visibility and ordering guarantees,
     *        not compound-operation atomicity.
     *
     * Java Memory Model:
     *     -> Defines memory and ordering rules between threads.
     *
     * Happens-before:
     *     -> Establishes visibility and ordering guarantees.
     *
     * Deadlock:
     *     -> Threads wait indefinitely for one another.
     *
     * Starvation:
     *     -> A thread repeatedly fails to obtain resources.
     *
     * Livelock:
     *     -> Threads remain active but make no useful progress.
     *
     * CAS:
     *     -> Atomic compare-and-update operation.
     *
     * Immutability:
     *     -> State cannot change after construction.
     *
     * Thread confinement:
     *     -> Object is accessed by only one thread.
     *
     * Safe publication:
     *     -> Makes initialized objects safely visible to threads.
     *
     * ============================================================
     * END OF THREAD SAFETY FUNDAMENTALS
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Thread Safety Fundamentals , Happy Learning ..!");
    }
}
