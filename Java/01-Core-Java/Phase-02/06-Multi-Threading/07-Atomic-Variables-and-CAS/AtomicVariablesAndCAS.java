
public class AtomicVariablesAndCAS {

    /*
     * ============================================================
     * 1. WHY DO WE NEED ATOMIC VARIABLES?
     * ============================================================
     *
     * Consider this shared counter:
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
     * If multiple threads execute increment() simultaneously,
     * some updates may be lost.
     *
     * Why?
     *
     * count++ is not one indivisible operation.
     *
     * It involves:
     *
     * 1. Reading count.
     * 2. Adding 1.
     * 3. Writing the result.
     *
     * We can protect it using synchronized:
     *
     * synchronized void increment() {
     *     count++;
     * }
     *
     * But Java also provides atomic classes for performing
     * thread-safe operations on individual variables.
     *
     *
     * ============================================================
     * 2. WHAT ARE ATOMIC VARIABLES?
     * ============================================================
     *
     * Atomic variables are classes from:
     *
     * java.util.concurrent.atomic
     *
     * They provide thread-safe operations on individual
     * values without requiring us to write explicit
     * synchronized blocks.
     *
     * Examples:
     *
     * AtomicInteger
     * AtomicLong
     * AtomicBoolean
     * AtomicReference
     * AtomicIntegerArray
     * AtomicLongArray
     * AtomicReferenceArray
     *
     * Common operations include:
     *
     * get()
     * set()
     * getAndSet()
     * compareAndSet()
     * getAndIncrement()
     * incrementAndGet()
     * getAndAdd()
     * addAndGet()
     *
     *
     * ============================================================
     * 3. ATOMICINTEGER
     * ============================================================
     *
     * AtomicInteger is used to maintain an integer value
     * safely across multiple threads.
     *
     * Example:
     *
     * AtomicInteger count = new AtomicInteger(0);
     *
     * count.incrementAndGet();
     *
     * System.out.println(count.get());
     *
     * Output:
     *
     * 1
     *
     * Multiple threads can call incrementAndGet()
     * without losing increments.
     *
     * The increment operation is atomic.
     *
     *
     * ============================================================
     * 4. WHY NOT JUST USE INT?
     * ============================================================
     *
     * Normal integer:
     *
     * int count = 0;
     *
     * count++;
     *
     * The compound increment is not atomic.
     *
     * AtomicInteger:
     *
     * AtomicInteger count = new AtomicInteger(0);
     *
     * count.incrementAndGet();
     *
     * The atomic class provides an indivisible
     * read-modify-write operation.
     *
     * IMPORTANT:
     *
     * AtomicInteger does not make every operation involving
     * that integer automatically atomic.
     *
     * For example, a sequence of separate atomic operations
     * is not automatically one indivisible transaction.
     *
     *
     * ============================================================
     * 5. CREATING ATOMICINTEGER
     * ============================================================
     *
     * Example:
     *
     * AtomicInteger a = new AtomicInteger();
     *
     * Default value:
     * 0
     *
     * With initial value:
     *
     * AtomicInteger b = new AtomicInteger(100);
     *
     * Initial value:
     * 100
     *
     * Reading:
     *
     * b.get();
     *
     * Updating:
     *
     * b.set(200);
     *
     *
     * ============================================================
     * 6. IMPORTANT ATOMICINTEGER METHODS
     * ============================================================
     *
     * get():
     * Returns the current value.
     *
     * set(value):
     * Sets the value.
     *
     * lazySet(value):
     * Performs a weaker, eventually visible update.
     * Prefer set() unless weaker ordering is specifically needed.
     *
     * getAndSet(value):
     * Atomically replaces the value and returns the old value.
     *
     * incrementAndGet():
     * Increments and returns the new value.
     *
     * getAndIncrement():
     * Increments and returns the old value.
     *
     * decrementAndGet():
     * Decrements and returns the new value.
     *
     * getAndDecrement():
     * Decrements and returns the old value.
     *
     * addAndGet(value):
     * Adds a value and returns the new result.
     *
     * getAndAdd(value):
     * Adds a value and returns the old result.
     *
     * compareAndSet(expected, update):
     * Updates only if the current value equals expected.
     *
     *
     * ============================================================
     * 7. GETANDINCREMENT VS INCREMENTANDGET
     * ============================================================
     *
     * This is an important difference.
     *
     * getAndIncrement():
     *
     * - Increments the value.
     * - Returns the old value.
     *
     * incrementAndGet():
     *
     * - Increments the value.
     * - Returns the new value.
     *
     * Example:
     *
     * AtomicInteger count = new AtomicInteger(10);
     *
     * int oldValue = count.getAndIncrement();
     *
     * oldValue = 10
     * count = 11
     *
     * int newValue = count.incrementAndGet();
     *
     * newValue = 12
     * count = 12
     *
     *
     * ============================================================
     * 8. COMPARE-AND-SET (CAS)
     * ============================================================
     *
     * CAS stands for Compare-And-Set.
     *
     * It is also commonly called Compare-And-Swap.
     *
     * CAS is an important synchronization technique
     * used by atomic classes.
     *
     * It performs an operation based on three values:
     *
     * 1. Current value.
     * 2. Expected value.
     * 3. New value.
     *
     * Basic idea:
     *
     * If current value == expected value:
     *
     *     Replace current value with new value.
     *
     * Otherwise:
     *
     *     Do not update.
     *
     * The comparison and update happen atomically.
     *
     *
     * ============================================================
     * 9. CAS EXAMPLE
     * ============================================================
     *
     * AtomicInteger count = new AtomicInteger(10);
     *
     * boolean result = count.compareAndSet(10, 20);
     *
     * The current value is 10.
     *
     * Expected value is 10.
     *
     * They match.
     *
     * Therefore:
     *
     * count becomes 20.
     *
     * result = true
     *
     * Another example:
     *
     * boolean result = count.compareAndSet(10, 30);
     *
     * Current value is now 20.
     *
     * Expected value is 10.
     *
     * They do not match.
     *
     * Therefore:
     *
     * count remains 20.
     *
     * result = false
     *
     *
     * ============================================================
     * 10. HOW CAS WORKS
     * ============================================================
     *
     * Imagine a shared value:
     *
     * value = 100
     *
     * Thread A wants to change it to 200.
     *
     * It expects the current value to be 100.
     *
     * CAS checks:
     *
     * Is current value still 100?
     *
     * YES:
     * Update it to 200.
     *
     * NO:
     * Do not update.
     *
     * This operation is indivisible.
     *
     * No other thread can change the value between
     * the comparison and the update as part of that CAS.
     *
     * This allows threads to coordinate without using
     * a traditional mutual-exclusion lock for that operation.
     *
     *
     * ============================================================
     * 11. WHY IS CAS IMPORTANT?
     * ============================================================
     *
     * CAS is commonly used to build non-blocking algorithms.
     *
     * A thread attempts an update.
     *
     * If another thread changes the value first,
     * the CAS operation fails.
     *
     * The thread can then retry using the latest value.
     *
     * This approach is commonly called optimistic concurrency.
     *
     * CAS is an important foundation of many atomic operations
     * and concurrent data structures.
     *
     *
     * ============================================================
     * 12. CAS RETRY LOOP
     * ============================================================
     *
     * Consider implementing an increment using CAS.
     *
     * Example:
     *
     * AtomicInteger count = new AtomicInteger(0);
     *
     * void increment() {
     *
     *     while (true) {
     *
     *         int current = count.get();
     *
     *         int updated = current + 1;
     *
     *         if (count.compareAndSet(current, updated)) {
     *             break;
     *         }
     *     }
     * }
     *
     * Explanation:
     *
     * 1. Read the current value.
     * 2. Calculate the new value.
     * 3. Attempt CAS.
     * 4. If successful, finish.
     * 5. If unsuccessful, another thread changed the value.
     * 6. Read the latest value and retry.
     *
     * This is called a CAS loop.
     *
     * IMPORTANT:
     *
     * AtomicInteger already provides incrementAndGet().
     *
     * You normally do not need to implement this loop yourself.
     *
     *
     * ============================================================
     * 13. LOCK-FREE VS LOCK-BASED APPROACH
     * ============================================================
     *
     * Lock-based approach:
     *
     * synchronized (lock) {
     *     count++;
     * }
     *
     * Threads may block while waiting for the lock.
     *
     * CAS-based approach:
     *
     * count.compareAndSet(expected, updated);
     *
     * An individual CAS operation does not acquire
     * a traditional application-level mutex.
     *
     * A CAS-based algorithm may retry when another thread
     * changes the value.
     *
     * IMPORTANT:
     *
     * Do not assume every atomic class operation is guaranteed
     * to be lock-free on every platform.
     *
     * Also, lock-free does not mean contention-free.
     *
     *
     * ============================================================
     * 14. ATOMICLONG
     * ============================================================
     *
     * AtomicLong provides atomic operations on long values.
     *
     * Example:
     *
     * AtomicLong total = new AtomicLong(0L);
     *
     * total.incrementAndGet();
     *
     * total.addAndGet(500L);
     *
     * long value = total.get();
     *
     * Common use cases:
     *
     * - Request counters.
     * - Sequence numbers.
     * - Event counts.
     * - Long-valued metrics.
     *
     *
     * ============================================================
     * 15. ATOMICBOOLEAN
     * ============================================================
     *
     * AtomicBoolean provides atomic operations on boolean values.
     *
     * Example:
     *
     * AtomicBoolean running = new AtomicBoolean(false);
     *
     * running.set(true);
     *
     * boolean status = running.get();
     *
     * It also provides compareAndSet().
     *
     * Example:
     *
     * boolean started = running.compareAndSet(false, true);
     *
     * This allows only one thread to successfully change
     * false to true through that CAS operation.
     *
     * Common use cases:
     *
     * - One-time initialization flags.
     * - State transitions.
     * - Cancellation flags.
     *
     *
     * ============================================================
     * 16. ATOMICREFERENCE
     * ============================================================
     *
     * AtomicReference provides atomic operations on
     * object references.
     *
     * Example:
     *
     * AtomicReference<String> name =
     *     new AtomicReference<>("Vinay");
     *
     * name.set("Java");
     *
     * System.out.println(name.get());
     *
     * It can atomically replace one reference with another.
     *
     * Example:
     *
     * boolean changed =
     *     name.compareAndSet("Java", "Spring Boot");
     *
     * The reference is updated only if the current reference
     * matches the expected reference according to the
     * atomic reference operation's identity comparison.
     *
     * IMPORTANT:
     *
     * AtomicReference makes reference updates atomic.
     *
     * It does not automatically make the referenced object
     * itself thread-safe.
     *
     *
     * ============================================================
     * 17. ATOMICREFERENCE AND IMMUTABLE OBJECTS
     * ============================================================
     *
     * AtomicReference is especially useful when replacing
     * immutable objects.
     *
     * Example:
     *
     * record User(String name, int age) {}
     *
     * AtomicReference<User> user =
     *     new AtomicReference<>(new User("Vinay", 22));
     *
     * user.updateAndGet(
     *     oldUser -> new User(
     *         oldUser.name(),
     *         oldUser.age() + 1
     *     )
     * );
     *
     * Instead of modifying the existing object,
     * we create a new object and atomically replace
     * the stored reference.
     *
     * This is a useful approach for managing shared
     * immutable state.
     *
     *
     * ============================================================
     * 18. UPDATEANDGET()
     * ============================================================
     *
     * Atomic classes provide functional update methods.
     *
     * Example:
     *
     * AtomicInteger count = new AtomicInteger(10);
     *
     * int updated = count.updateAndGet(
     *     value -> value + 5
     * );
     *
     * Result:
     *
     * count = 15
     * updated = 15
     *
     * The update function may be applied more than once
     * when competing updates cause retries.
     *
     * IMPORTANT:
     *
     * Keep update functions free of side effects.
     *
     * Do not perform database writes, send emails or
     * increment unrelated counters inside a function
     * that may be retried.
     *
     *
     * ============================================================
     * 19. ACCUMULATEANDGET()
     * ============================================================
     *
     * accumulateAndGet() combines the current value with
     * another value using a supplied accumulator function.
     *
     * Example:
     *
     * AtomicInteger total = new AtomicInteger(100);
     *
     * int result = total.accumulateAndGet(
     *     50,
     *     (current, value) -> current + value
     * );
     *
     * Result:
     *
     * total = 150
     * result = 150
     *
     * The function may be applied more than once
     * under contention.
     *
     *
     * ============================================================
     * 20. GETANDUPDATE() VS UPDATEANDGET()
     * ============================================================
     *
     * getAndUpdate():
     *
     * - Applies the update.
     * - Returns the previous value.
     *
     * updateAndGet():
     *
     * - Applies the update.
     * - Returns the updated value.
     *
     * Example:
     *
     * AtomicInteger count = new AtomicInteger(10);
     *
     * int oldValue = count.getAndUpdate(x -> x + 5);
     *
     * oldValue = 10
     * count = 15
     *
     * int newValue = count.updateAndGet(x -> x + 5);
     *
     * newValue = 20
     * count = 20
     *
     *
     * ============================================================
     * 21. ATOMIC ARRAYS
     * ============================================================
     *
     * Java provides atomic array classes.
     *
     * Examples:
     *
     * AtomicIntegerArray
     * AtomicLongArray
     * AtomicReferenceArray
     *
     * Example:
     *
     * AtomicIntegerArray numbers =
     *     new AtomicIntegerArray(5);
     *
     * numbers.set(0, 10);
     *
     * numbers.incrementAndGet(0);
     *
     * System.out.println(numbers.get(0));
     *
     * Output:
     *
     * 11
     *
     * Individual array-element operations are atomic.
     *
     * This does not make an arbitrary sequence of operations
     * across multiple elements one atomic transaction.
     *
     *
     * ============================================================
     * 22. ATOMICFIELDUPDATER
     * ============================================================
     *
     * AtomicFieldUpdater classes allow atomic updates to
     * selected volatile fields of existing objects.
     *
     * Types:
     *
     * AtomicIntegerFieldUpdater
     * AtomicLongFieldUpdater
     * AtomicReferenceFieldUpdater
     *
     * Example:
     *
     * class User {
     *
     *     volatile int age;
     * }
     *
     * AtomicIntegerFieldUpdater<User> updater =
     *     AtomicIntegerFieldUpdater.newUpdater(
     *         User.class,
     *         "age"
     *     );
     *
     * User user = new User();
     *
     * updater.incrementAndGet(user);
     *
     * IMPORTANT:
     *
     * The target field must satisfy the updater's requirements,
     * including being volatile and having appropriate
     * accessibility.
     *
     * These classes are useful in specialized designs
     * where creating a separate atomic wrapper per object
     * is undesirable.
     *
     *
     * ============================================================
     * 23. ATOMICMARKABLEREFERENCE
     * ============================================================
     *
     * AtomicMarkableReference stores:
     *
     * - An object reference.
     * - A boolean mark.
     *
     * Both are updated atomically as one pair.
     *
     * Example:
     *
     * AtomicMarkableReference<String> reference =
     *     new AtomicMarkableReference<>("A", false);
     *
     * It can be used when a reference needs an additional
     * boolean state.
     *
     * Common conceptual use:
     *
     * Marking a reference as logically deleted.
     *
     *
     * ============================================================
     * 24. ATOMICSTAMPEDREFERENCE
     * ============================================================
     *
     * AtomicStampedReference stores:
     *
     * - An object reference.
     * - An integer stamp.
     *
     * Both are updated atomically as one pair.
     *
     * The stamp can help detect changes to a reference
     * even when it later returns to an earlier value.
     *
     * This is useful in addressing certain ABA problems.
     *
     *
     * ============================================================
     * 25. ABA PROBLEM
     * ============================================================
     *
     * ABA is a problem that can occur in CAS-based algorithms.
     *
     * Consider:
     *
     * Shared value = A
     *
     * Thread 1 reads A.
     *
     * Thread 1 is paused.
     *
     * Thread 2 changes:
     *
     * A -> B
     *
     * Then changes:
     *
     * B -> A
     *
     * Thread 1 resumes.
     *
     * It checks whether the current value is A.
     *
     * The value is A, so CAS succeeds.
     *
     * But the value changed in between.
     *
     * Thread 1 cannot detect that history from the value alone.
     *
     * This is called the ABA problem.
     *
     *
     * ============================================================
     * 26. WHY ABA CAN BE DANGEROUS
     * ============================================================
     *
     * In simple counters, seeing the same value again
     * may not matter.
     *
     * But in data structures such as lock-free stacks,
     * the object represented by a reference may have changed
     * in ways that make an old operation unsafe.
     *
     * The problem is not merely that the value returned
     * to A.
     *
     * It is that an algorithm may incorrectly assume
     * nothing changed because the current value looks
     * identical to the previously observed value.
     *
     *
     * ============================================================
     * 27. SOLVING ABA WITH STAMPS
     * ============================================================
     *
     * AtomicStampedReference associates an integer stamp
     * with the reference.
     *
     * Conceptual example:
     *
     * Initial:
     *
     * (A, 1)
     *
     * After first change:
     *
     * (B, 2)
     *
     * After second change:
     *
     * (A, 3)
     *
     * Although the reference is A again,
     * the stamp is now different.
     *
     * A CAS operation that expects (A, 1) will fail
     * when the actual pair is (A, 3).
     *
     * IMPORTANT:
     *
     * A stamp is useful only when updates consistently
     * change it according to the algorithm's requirements.
     *
     *
     * ============================================================
     * 28. LONGADDER
     * ============================================================
     *
     * AtomicLong is useful for thread-safe counters.
     *
     * But under very high contention, many threads may
     * repeatedly compete to update the same atomic value.
     *
     * LongAdder is designed to improve throughput
     * for highly contended statistical counters.
     *
     * It distributes updates across multiple internal
     * cells and combines them when sum() is requested.
     *
     * Example:
     *
     * LongAdder counter = new LongAdder();
     *
     * counter.increment();
     *
     * counter.add(5);
     *
     * long total = counter.sum();
     *
     * Common use cases:
     *
     * - Request counts.
     * - Metrics.
     * - Event statistics.
     * - High-contention counters.
     *
     *
     * ============================================================
     * 29. LONGADDER VS ATOMICLONG
     * ============================================================
     *
     * AtomicLong:
     *
     * - Maintains one atomic long value.
     * - Provides atomic read-modify-write operations.
     * - Useful when the current value is frequently needed.
     *
     * LongAdder:
     *
     * - Distributes updates across internal cells.
     * - Often provides better throughput under high contention.
     * - Combines values when sum() is called.
     * - Does not provide the same atomic snapshot semantics
     *   for sum() during concurrent updates.
     *
     * Choose AtomicLong when you need operations such as
     * compareAndSet() or an exact atomic state transition.
     *
     * Choose LongAdder for high-contention statistics where
     * frequent independent increments are more important
     * than an immediately consistent snapshot.
     *
     *
     * ============================================================
     * 30. LONGACCUMULATOR
     * ============================================================
     *
     * LongAccumulator generalizes the idea of LongAdder.
     *
     * It combines values using a supplied associative
     * accumulation function and an identity value.
     *
     * Example:
     *
     * LongAccumulator maximum =
     *     new LongAccumulator(Long::max, Long.MIN_VALUE);
     *
     * maximum.accumulate(10);
     * maximum.accumulate(50);
     * maximum.accumulate(20);
     *
     * long result = maximum.get();
     *
     * Result:
     *
     * 50
     *
     * It is useful for highly contended aggregation tasks.
     *
     *
     * ============================================================
     * 31. ATOMIC VS SYNCHRONIZED
     * ============================================================
     *
     * synchronized:
     *
     * - Provides mutual exclusion.
     * - Can protect multiple related operations.
     * - Provides memory-visibility guarantees.
     * - Uses intrinsic monitor locks.
     *
     * Atomic classes:
     *
     * - Provide atomic operations on individual values.
     * - Commonly use CAS-based techniques.
     * - Can avoid traditional application-level locking
     *   for supported operations.
     * - Are useful for counters and simple state transitions.
     *
     * Example:
     *
     * synchronized:
     *
     * synchronized void increment() {
     *     count++;
     * }
     *
     * Atomic:
     *
     * atomicCount.incrementAndGet();
     *
     * IMPORTANT:
     *
     * Atomic variables are not a universal replacement
     * for synchronization.
     *
     * If several variables must be updated consistently
     * as one operation, a lock or another coordination
     * mechanism may be more appropriate.
     *
     *
     * ============================================================
     * 32. ATOMIC VS VOLATILE
     * ============================================================
     *
     * volatile:
     *
     * - Provides visibility and ordering guarantees.
     * - Does not make compound operations atomic.
     *
     * AtomicInteger:
     *
     * - Provides visibility guarantees.
     * - Provides atomic read-modify-write operations.
     *
     * Example:
     *
     * volatile int count = 0;
     *
     * count++;
     *
     * This is not atomic.
     *
     * AtomicInteger count = new AtomicInteger(0);
     *
     * count.incrementAndGet();
     *
     * This increment is atomic.
     *
     *
     * ============================================================
     * 33. VARHANDLE
     * ============================================================
     *
     * VarHandle was introduced in Java 9.
     *
     * It provides a flexible mechanism for performing
     * variable access and atomic operations.
     *
     * It can operate on:
     *
     * - Fields.
     * - Static fields.
     * - Array elements.
     * - Other supported variable locations.
     *
     * It supports operations such as:
     *
     * get()
     * set()
     * compareAndSet()
     * getAndSet()
     * getAndAdd()
     *
     * It also supports different memory-ordering modes.
     *
     * VarHandle is part of:
     *
     * java.lang.invoke
     *
     *
     * ============================================================
     * 34. WHY VARHANDLE WAS INTRODUCED
     * ============================================================
     *
     * Before VarHandle, developers commonly used:
     *
     * - Atomic classes.
     * - volatile fields.
     * - Specialized field updaters.
     * - Unsafe in low-level implementations.
     *
     * VarHandle provides a supported API for flexible
     * variable access with different memory semantics.
     *
     * It is useful for advanced concurrent programming
     * and library implementation.
     *
     *
     * ============================================================
     * 35. CAS VS LOCKING
     * ============================================================
     *
     * Locking:
     *
     * A thread acquires a lock before entering a critical section.
     *
     * Other threads may block until the lock becomes available.
     *
     * CAS:
     *
     * A thread attempts an atomic conditional update.
     *
     * If another thread changed the value first,
     * the operation fails and may be retried.
     *
     * Locking is often easier to understand for complex
     * multi-step operations.
     *
     * CAS can be useful for simple state transitions
     * and non-blocking algorithms.
     *
     *
     * ============================================================
     * 36. COMMON ATOMIC-VARIABLE MISTAKES
     * ============================================================
     *
     * 1. Assuming AtomicInteger makes every operation
     *    involving the integer atomic.
     *
     * 2. Assuming AtomicReference makes the referenced
     *    object itself thread-safe.
     *
     * 3. Confusing getAndIncrement() with incrementAndGet().
     *
     * 4. Ignoring CAS failures.
     *
     * 5. Writing retry loops without understanding contention.
     *
     * 6. Performing side effects inside update functions
     *    that may be retried.
     *
     * 7. Assuming LongAdder.sum() is an atomic snapshot
     *    during concurrent updates.
     *
     * 8. Ignoring the ABA problem in low-level algorithms.
     *
     * 9. Using atomic variables when a multi-variable
     *    invariant requires a larger critical section.
     *
     * 10. Assuming lock-free automatically means faster.
     *
     *
     * ============================================================
     * 37. INTERVIEW REVISION
     * ============================================================
     *
     * Q1. What are atomic variables?
     *
     * Q2. What is AtomicInteger?
     *
     * Q3. What is CAS?
     *
     * Q4. What is Compare-And-Set?
     *
     * Q5. How does a CAS loop work?
     *
     * Q6. What is the difference between lock-based
     *     and CAS-based approaches?
     *
     * Q7. What is AtomicReference?
     *
     * Q8. Does AtomicReference make the referenced object
     *     thread-safe?
     *
     * Q9. What is the difference between getAndIncrement()
     *     and incrementAndGet()?
     *
     * Q10. What is the ABA problem?
     *
     * Q11. How does AtomicStampedReference help with ABA?
     *
     * Q12. What is LongAdder?
     *
     * Q13. What is the difference between AtomicLong
     *      and LongAdder?
     *
     * Q14. What is LongAccumulator?
     *
     * Q15. What is AtomicFieldUpdater?
     *
     * Q16. What is VarHandle?
     *
     * Q17. When would you use an atomic variable instead
     *      of synchronized?
     *
     *
     * ============================================================
     * 38. FINAL TAKEAWAY
     * ============================================================
     *
     * Atomic variables:
     * Provide thread-safe operations on individual values.
     *
     * CAS:
     * Updates a value only when it matches an expected value.
     *
     * AtomicInteger:
     * Provides atomic integer operations.
     *
     * AtomicReference:
     * Provides atomic reference updates.
     *
     * ABA:
     * A value changes and later returns to its original value,
     * potentially hiding an intermediate change.
     *
     * AtomicStampedReference:
     * Associates a stamp with a reference to help detect changes.
     *
     * LongAdder:
     * Improves throughput for highly contended counters.
     *
     * LongAccumulator:
     * Supports highly contended accumulation operations.
     *
     * VarHandle:
     * Provides flexible variable access and atomic operations.
     *
     * Remember:
     *
     * Atomic operations are powerful for individual values
     * and simple state transitions.
     *
     * For complex operations involving multiple shared values,
     * choose a synchronization strategy that protects
     * the complete invariant.
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Atomic Variables and CAS , Happy Learning ..!");
    }
}
