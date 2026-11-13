
public class ExplicitLocksAndConditions {

    /*
     * ============================================================
     * 1. WHY DO WE NEED EXPLICIT LOCKS?
     * ============================================================
     *
     * Java provides synchronized for thread synchronization.
     *
     * It is useful, simple and automatically releases its
     * intrinsic lock when execution leaves the synchronized
     * method or block.
     *
     * However, sometimes we need additional control:
     *
     * - Try acquiring a lock without waiting indefinitely.
     * - Wait for a limited amount of time to acquire a lock.
     * - Interrupt a thread while it is waiting for a lock.
     * - Allow multiple condition queues for one lock.
     * - Acquire and release locks explicitly.
     *
     * The java.util.concurrent.locks package provides
     * explicit locking mechanisms for these requirements.
     *
     *
     * ============================================================
     * 2. LOCK INTERFACE
     * ============================================================
     *
     * Lock is an interface in:
     *
     * java.util.concurrent.locks
     *
     * It defines an explicit locking protocol.
     *
     * Important methods:
     *
     * lock()
     * lockInterruptibly()
     * tryLock()
     * tryLock(long time, TimeUnit unit)
     * unlock()
     * newCondition()
     *
     * Common implementation:
     *
     * ReentrantLock
     *
     *
     * ============================================================
     * 3. REENTRANTLOCK
     * ============================================================
     *
     * ReentrantLock is an implementation of the Lock interface.
     *
     * It provides a reentrant mutual-exclusion lock.
     *
     * Reentrant means:
     *
     * A thread that already owns a lock can acquire that
     * same lock again without blocking itself.
     *
     * Example:
     *
     * import java.util.concurrent.locks.ReentrantLock;
     *
     * class Counter {
     *
     *     private int count = 0;
     *
     *     private final ReentrantLock lock =
     *         new ReentrantLock();
     *
     *     void increment() {
     *
     *         lock.lock();
     *
     *         try {
     *             count++;
     *
     *         } finally {
     *             lock.unlock();
     *         }
     *     }
     * }
     *
     * The lock protects the critical section.
     *
     *
     * ============================================================
     * 4. LOCK()
     * ============================================================
     *
     * lock() attempts to acquire the lock.
     *
     * If another thread owns it:
     *
     * - The current thread waits.
     * - It can proceed after acquiring the lock.
     *
     * Example:
     *
     * lock.lock();
     *
     * // Critical section.
     *
     * lock.unlock();
     *
     * IMPORTANT:
     *
     * lock() does not automatically release the lock.
     *
     * You must call unlock() after acquiring it.
     *
     *
     * ============================================================
     * 5. WHY UNLOCK() SHOULD BE INSIDE FINALLY
     * ============================================================
     *
     * Consider:
     *
     * lock.lock();
     *
     * performOperation();
     *
     * lock.unlock();
     *
     * What if performOperation() throws an exception?
     *
     * Execution may skip unlock().
     *
     * The lock could remain held, preventing other threads
     * from making progress.
     *
     * Correct pattern:
     *
     * lock.lock();
     *
     * try {
     *
     *     performOperation();
     *
     * } finally {
     *
     *     lock.unlock();
     * }
     *
     * The finally block executes when control leaves the
     * try block, including when an exception is thrown.
     *
     * This is the standard explicit-lock pattern.
     *
     *
     * ============================================================
     * 6. LOCK() VS SYNCHRONIZED
     * ============================================================
     *
     * synchronized:
     *
     * - Language keyword.
     * - Uses intrinsic monitor locks.
     * - Lock acquisition and release are automatic.
     * - Cannot directly attempt a non-blocking acquisition.
     * - Cannot interrupt a thread merely waiting to enter
     *   a synchronized block.
     *
     * Lock:
     *
     * - Explicit API.
     * - Lock acquisition and release are controlled by code.
     * - Supports tryLock().
     * - Supports timed acquisition.
     * - Supports interruptible acquisition.
     * - Can provide Condition objects.
     *
     * Both can provide mutual exclusion and memory-visibility
     * guarantees when used correctly.
     *
     *
     * ============================================================
     * 7. TRYLOCK()
     * ============================================================
     *
     * tryLock() attempts to acquire a lock immediately.
     *
     * It does not wait indefinitely.
     *
     * It returns:
     *
     * true  -> Lock acquired.
     * false -> Lock not acquired.
     *
     * Example:
     *
     * if (lock.tryLock()) {
     *
     *     try {
     *         // Critical section.
     *
     *     } finally {
     *         lock.unlock();
     *     }
     *
     * } else {
     *
     *     // Lock was unavailable.
     * }
     *
     * USE CASE:
     *
     * When a thread should do something else instead of
     * waiting indefinitely for a lock.
     *
     * IMPORTANT:
     *
     * If tryLock() returns false, the current thread
     * does not own the lock and must not unlock it.
     *
     *
     * ============================================================
     * 8. TIMED TRYLOCK()
     * ============================================================
     *
     * You can specify how long to wait for a lock.
     *
     * Method:
     *
     * tryLock(long time, TimeUnit unit)
     *
     * Example:
     *
     * boolean acquired = false;
     *
     * try {
     *
     *     acquired = lock.tryLock(
     *         3,
     *         TimeUnit.SECONDS
     *     );
     *
     *     if (acquired) {
     *
     *         // Critical section.
     *     }
     *
     * } catch (InterruptedException e) {
     *
     *     Thread.currentThread().interrupt();
     *
     * } finally {
     *
     *     if (acquired) {
     *         lock.unlock();
     *     }
     * }
     *
     * The method can:
     *
     * - Acquire the lock.
     * - Return false when the waiting time expires.
     * - Throw InterruptedException if interrupted.
     *
     *
     * ============================================================
     * 9. LOCKINTERRUPTIBLY()
     * ============================================================
     *
     * lockInterruptibly() attempts to acquire a lock,
     * allowing the waiting thread to be interrupted.
     *
     * Example:
     *
     * try {
     *
     *     lock.lockInterruptibly();
     *
     *     try {
     *         // Critical section.
     *
     *     } finally {
     *         lock.unlock();
     *     }
     *
     * } catch (InterruptedException e) {
     *
     *     Thread.currentThread().interrupt();
     * }
     *
     * Difference:
     *
     * lock():
     * Waiting for the lock is not interruptible through
     * the method's interruptible acquisition protocol.
     *
     * lockInterruptibly():
     * Waiting for the lock can be cancelled through interruption.
     *
     *
     * ============================================================
     * 10. UNLOCK()
     * ============================================================
     *
     * unlock() releases a lock held by the current thread.
     *
     * Example:
     *
     * lock.lock();
     *
     * try {
     *     // Critical section.
     *
     * } finally {
     *     lock.unlock();
     * }
     *
     * IMPORTANT:
     *
     * The current thread must own the lock before calling
     * unlock().
     *
     * Otherwise, ReentrantLock throws
     * IllegalMonitorStateException.
     *
     * Unlike synchronized, explicit locks require you
     * to manage release correctly.
     *
     *
     * ============================================================
     * 11. REENTRANTLOCK AND REENTRANCY
     * ============================================================
     *
     * ReentrantLock allows the owning thread to acquire
     * the same lock multiple times.
     *
     * Example:
     *
     * ReentrantLock lock = new ReentrantLock();
     *
     * lock.lock(); // First acquisition.
     * lock.lock(); // Second acquisition by the same thread.
     *
     * lock.unlock(); // Releases one acquisition.
     * lock.unlock(); // Releases the remaining acquisition.
     *
     * The lock is fully available to other threads only
     * after the hold count reaches zero.
     *
     * IMPORTANT:
     *
     * Every successful acquisition must have a corresponding
     * unlock().
     *
     *
     * ============================================================
     * 12. HOLD COUNT
     * ============================================================
     *
     * ReentrantLock maintains a hold count.
     *
     * It represents how many times the current owning thread
     * has acquired the lock without corresponding releases.
     *
     * Example:
     *
     * lock.lock();
     * lock.lock();
     * lock.lock();
     *
     * Hold count = 3
     *
     * After one unlock():
     *
     * Hold count = 2
     *
     * After another unlock():
     *
     * Hold count = 1
     *
     * After the final unlock():
     *
     * Hold count = 0
     *
     * getHoldCount() can inspect the current thread's
     * hold count.
     *
     * It is primarily useful for diagnostics.
     *
     *
     * ============================================================
     * 13. FAIRNESS
     * ============================================================
     *
     * ReentrantLock supports an optional fairness policy.
     *
     * Default:
     *
     * ReentrantLock lock = new ReentrantLock();
     *
     * Fair:
     *
     * ReentrantLock lock = new ReentrantLock(true);
     *
     * A fair lock generally favors the longest-waiting
     * eligible thread when choosing the next owner.
     *
     * A non-fair lock does not promise this ordering.
     *
     * IMPORTANT:
     *
     * Fairness does not guarantee operating-system scheduling
     * order or absolute freedom from starvation.
     *
     * Fair locks may reduce throughput because they can
     * impose additional coordination.
     *
     *
     * ============================================================
     * 14. ISFAIR()
     * ============================================================
     *
     * ReentrantLock provides isFair().
     *
     * Example:
     *
     * ReentrantLock lock = new ReentrantLock(true);
     *
     * System.out.println(lock.isFair());
     *
     * Output:
     *
     * true
     *
     *
     * ============================================================
     * 15. ISLOCKED()
     * ============================================================
     *
     * isLocked() checks whether the lock is currently held
     * by any thread.
     *
     * Example:
     *
     * if (lock.isLocked()) {
     *     System.out.println("Lock is held");
     * }
     *
     * It is useful for monitoring and diagnostics.
     *
     * IMPORTANT:
     *
     * The result is only a snapshot.
     *
     * Another thread may acquire or release the lock
     * immediately after the check.
     *
     * Do not use isLocked() as a substitute for acquiring
     * the lock.
     *
     *
     * ============================================================
     * 16. ISHELD BY CURRENT THREAD
     * ============================================================
     *
     * isHeldByCurrentThread() checks whether the current
     * thread owns the ReentrantLock.
     *
     * Example:
     *
     * if (lock.isHeldByCurrentThread()) {
     *     System.out.println("Current thread owns the lock");
     * }
     *
     * This can be useful for assertions and diagnostics.
     *
     *
     * ============================================================
     * 17. GETQUEUELENGTH()
     * ============================================================
     *
     * getQueueLength() provides an estimate of the number
     * of threads waiting to acquire the lock.
     *
     * Example:
     *
     * int waiting = lock.getQueueLength();
     *
     * IMPORTANT:
     *
     * It is an estimate, not an exact synchronization guarantee.
     *
     * Do not use it to make correctness decisions.
     *
     *
     * ============================================================
     * 18. LOCK AND CONDITION
     * ============================================================
     *
     * Earlier, we studied:
     *
     * wait()
     * notify()
     * notifyAll()
     *
     * These methods use an object's intrinsic monitor.
     *
     * The Lock framework provides another mechanism:
     *
     * Condition
     *
     * A Condition is associated with a Lock.
     *
     * It provides methods such as:
     *
     * await()
     * signal()
     * signalAll()
     *
     * Think of Condition as an explicit-lock equivalent
     * of the traditional monitor waiting mechanism.
     *
     *
     * ============================================================
     * 19. CREATING A CONDITION
     * ============================================================
     *
     * Example:
     *
     * ReentrantLock lock = new ReentrantLock();
     *
     * Condition condition = lock.newCondition();
     *
     * The Condition is associated with the lock that
     * created it.
     *
     * A thread must hold the associated lock when calling
     * await(), signal() or signalAll().
     *
     *
     * ============================================================
     * 20. AWAIT()
     * ============================================================
     *
     * await() makes the current thread wait for a condition.
     *
     * It:
     *
     * 1. Requires the current thread to own the associated lock.
     * 2. Releases the associated lock.
     * 3. Suspends the current thread.
     * 4. Waits for a signal, interruption or another permitted
     *    wake-up condition.
     * 5. Reacquires the lock before returning normally.
     *
     * Example:
     *
     * lock.lock();
     *
     * try {
     *
     *     while (!conditionSatisfied) {
     *         condition.await();
     *     }
     *
     *     // Continue when the condition is satisfied.
     *
     * } finally {
     *
     *     lock.unlock();
     * }
     *
     * Like wait(), await() should normally be used
     * inside a while loop.
     *
     *
     * ============================================================
     * 21. SIGNAL()
     * ============================================================
     *
     * signal() wakes one thread waiting on that Condition.
     *
     * Example:
     *
     * lock.lock();
     *
     * try {
     *
     *     condition.signal();
     *
     * } finally {
     *
     *     lock.unlock();
     * }
     *
     * IMPORTANT:
     *
     * The signalled thread must reacquire the associated
     * lock before it can continue.
     *
     * The signalling thread still owns the lock after
     * calling signal().
     *
     *
     * ============================================================
     * 22. SIGNALALL()
     * ============================================================
     *
     * signalAll() wakes all threads waiting on that Condition.
     *
     * Example:
     *
     * lock.lock();
     *
     * try {
     *
     *     condition.signalAll();
     *
     * } finally {
     *
     *     lock.unlock();
     * }
     *
     * All signalled threads must compete to reacquire
     * the associated lock.
     *
     *
     * ============================================================
     * 23. AWAIT VS SIGNAL VS SIGNALALL
     * ============================================================
     *
     * await():
     *
     * - Makes the current thread wait.
     * - Releases the associated lock.
     * - Requires the thread to hold that lock.
     *
     * signal():
     *
     * - Signals one waiting thread.
     * - Does not immediately release the lock.
     * - Requires the thread to hold the associated lock.
     *
     * signalAll():
     *
     * - Signals all waiting threads.
     * - Does not immediately release the lock.
     * - Requires the thread to hold the associated lock.
     *
     *
     * ============================================================
     * 24. MULTIPLE CONDITIONS FOR ONE LOCK
     * ============================================================
     *
     * One major advantage of Condition is that a single
     * Lock can have multiple Condition objects.
     *
     * Example:
     *
     * ReentrantLock lock = new ReentrantLock();
     *
     * Condition notEmpty = lock.newCondition();
     *
     * Condition notFull = lock.newCondition();
     *
     * In a bounded buffer:
     *
     * notEmpty:
     * Consumers wait here when the buffer is empty.
     *
     * notFull:
     * Producers wait here when the buffer is full.
     *
     * When an item is added:
     *
     * notEmpty.signal();
     *
     * When an item is removed:
     *
     * notFull.signal();
     *
     * This separates different waiting conditions.
     *
     * With one intrinsic monitor, waiters share the same
     * wait set for that object.
     *
     *
     * ============================================================
     * 25. PRODUCER-CONSUMER USING REENTRANTLOCK AND CONDITION
     * ============================================================
     *
     * Example:
     *
     * class BoundedBuffer {
     *
     *     private final Queue<Integer> queue =
     *         new ArrayDeque<>();
     *
     *     private final int capacity = 5;
     *
     *     private final ReentrantLock lock =
     *         new ReentrantLock();
     *
     *     private final Condition notFull =
     *         lock.newCondition();
     *
     *     private final Condition notEmpty =
     *         lock.newCondition();
     *
     *     void put(int value) throws InterruptedException {
     *
     *         lock.lockInterruptibly();
     *
     *         try {
     *
     *             while (queue.size() == capacity) {
     *                 notFull.await();
     *             }
     *
     *             queue.add(value);
     *
     *             notEmpty.signal();
     *
     *         } finally {
     *
     *             lock.unlock();
     *         }
     *     }
     *
     *     int take() throws InterruptedException {
     *
     *         lock.lockInterruptibly();
     *
     *         try {
     *
     *             while (queue.isEmpty()) {
     *                 notEmpty.await();
     *             }
     *
     *             int value = queue.remove();
     *
     *             notFull.signal();
     *
     *             return value;
     *
     *         } finally {
     *
     *             lock.unlock();
     *         }
     *     }
     * }
     *
     * Explanation:
     *
     * Producer:
     *
     * - Waits on notFull when the buffer is full.
     * - Adds an item when space becomes available.
     * - Signals notEmpty.
     *
     * Consumer:
     *
     * - Waits on notEmpty when the buffer is empty.
     * - Removes an item when available.
     * - Signals notFull.
     *
     * This is a conceptual implementation of a bounded buffer.
     *
     * In production, prefer a suitable BlockingQueue unless
     * custom coordination is genuinely required.
     *
     *
     * ============================================================
     * 26. CONDITION VS OBJECT WAIT/NOTIFY
     * ============================================================
     *
     * Object.wait()/notify()/notifyAll():
     *
     * - Work with intrinsic monitor locks.
     * - Require synchronized ownership.
     * - Provide one wait set per object monitor.
     *
     * Condition:
     *
     * - Works with an associated Lock.
     * - Requires ownership of that Lock.
     * - Allows multiple condition queues for one Lock.
     * - Provides timed and interruptible waiting variants.
     *
     *
     * ============================================================
     * 27. CONDITION METHODS
     * ============================================================
     *
     * Common Condition methods include:
     *
     * await()
     * awaitUninterruptibly()
     * awaitNanos(long nanosTimeout)
     * await(long time, TimeUnit unit)
     * awaitUntil(Date deadline)
     * signal()
     * signalAll()
     *
     * await():
     * Waits and responds to interruption.
     *
     * awaitUninterruptibly():
     * Waits without terminating the wait due to interruption.
     *
     * awaitNanos():
     * Waits for up to a specified number of nanoseconds.
     *
     * await(time, unit):
     * Waits for a specified duration.
     *
     * awaitUntil():
     * Waits until a specified deadline.
     *
     * signal():
     * Signals one waiter.
     *
     * signalAll():
     * Signals all waiters.
     *
     *
     * ============================================================
     * 28. READWRITELOCK
     * ============================================================
     *
     * Sometimes an application has:
     *
     * - Many read operations.
     * - Very few write operations.
     *
     * Multiple readers can often read the same data safely
     * at the same time.
     *
     * But writers need exclusive access to update it.
     *
     * ReadWriteLock is designed for this situation.
     *
     * It is an interface in:
     *
     * java.util.concurrent.locks
     *
     * It provides:
     *
     * readLock()
     * writeLock()
     *
     * A common implementation is ReentrantReadWriteLock.
     *
     *
     * ============================================================
     * 29. REENTRANTREADWRITELOCK
     * ============================================================
     *
     * ReentrantReadWriteLock provides:
     *
     * - A shared read lock.
     * - An exclusive write lock.
     *
     * Multiple threads can hold the read lock simultaneously,
     * provided no conflicting write lock is held.
     *
     * The write lock is exclusive.
     *
     * Example:
     *
     * ReentrantReadWriteLock rwLock =
     *     new ReentrantReadWriteLock();
     *
     * Lock readLock = rwLock.readLock();
     *
     * Lock writeLock = rwLock.writeLock();
     *
     *
     * ============================================================
     * 30. READ LOCK
     * ============================================================
     *
     * Multiple readers can acquire the read lock together.
     *
     * Example:
     *
     * readLock.lock();
     *
     * try {
     *
     *     // Read shared data.
     *
     * } finally {
     *
     *     readLock.unlock();
     * }
     *
     * A reader can proceed only when the locking rules
     * allow shared reading.
     *
     *
     * ============================================================
     * 31. WRITE LOCK
     * ============================================================
     *
     * The write lock provides exclusive access.
     *
     * Example:
     *
     * writeLock.lock();
     *
     * try {
     *
     *     // Modify shared data.
     *
     * } finally {
     *
     *     writeLock.unlock();
     * }
     *
     * While a writer owns the write lock:
     *
     * - Other readers cannot acquire the read lock.
     * - Other writers cannot acquire the write lock.
     *
     *
     * ============================================================
     * 32. READWRITELOCK EXAMPLE
     * ============================================================
     *
     * class SharedData {
     *
     *     private String value = "Initial";
     *
     *     private final ReentrantReadWriteLock rwLock =
     *         new ReentrantReadWriteLock();
     *
     *     String read() {
     *
     *         Lock lock = rwLock.readLock();
     *
     *         lock.lock();
     *
     *         try {
     *             return value;
     *
     *         } finally {
     *             lock.unlock();
     *         }
     *     }
     *
     *     void write(String newValue) {
     *
     *         Lock lock = rwLock.writeLock();
     *
     *         lock.lock();
     *
     *         try {
     *             value = newValue;
     *
     *         } finally {
     *             lock.unlock();
     *         }
     *     }
     * }
     *
     * Multiple readers can read concurrently.
     *
     * A writer gets exclusive access.
     *
     *
     * ============================================================
     * 33. READ LOCK VS WRITE LOCK
     * ============================================================
     *
     * Read lock:
     *
     * - Shared access.
     * - Multiple readers may hold it together.
     * - Does not permit conflicting writes.
     *
     * Write lock:
     *
     * - Exclusive access.
     * - Only one writer owns it at a time.
     * - Conflicts with both readers and other writers.
     *
     *
     * ============================================================
     * 34. LOCK DOWNGRADING
     * ============================================================
     *
     * Lock downgrading means:
     *
     * A thread acquires the write lock, performs an update,
     * acquires the read lock, and then releases the write lock.
     *
     * This allows it to continue reading while retaining
     * read access.
     *
     * Conceptual example:
     *
     * writeLock.lock();
     *
     * try {
     *
     *     // Update shared data.
     *
     *     readLock.lock();
     *
     * } finally {
     *
     *     writeLock.unlock();
     * }
     *
     * try {
     *
     *     // Read the updated data.
     *
     * } finally {
     *
     *     readLock.unlock();
     * }
     *
     * The read lock must be acquired before releasing
     * the write lock.
     *
     *
     * ============================================================
     * 35. LOCK UPGRADE
     * ============================================================
     *
     * Lock upgrading means attempting to move from a read lock
     * to a write lock.
     *
     * ReentrantReadWriteLock does not support a direct,
     * safe read-to-write upgrade.
     *
     * If a thread holds a read lock and waits for the write lock,
     * it may block indefinitely because it is still holding
     * a read lock that prevents exclusive write access.
     *
     * A common approach:
     *
     * 1. Release the read lock.
     * 2. Acquire the write lock.
     * 3. Recheck the condition.
     * 4. Perform the update if still required.
     *
     * Rechecking is necessary because shared data may have
     * changed between releasing the read lock and acquiring
     * the write lock.
     *
     *
     * ============================================================
     * 36. STAMPEDLOCK
     * ============================================================
     *
     * StampedLock is another explicit locking mechanism.
     *
     * It was introduced in Java 8.
     *
     * It supports:
     *
     * - Write locks.
     * - Read locks.
     * - Optimistic reads.
     *
     * Unlike ReentrantLock, StampedLock is not reentrant.
     *
     * Its lock methods return a stamp.
     *
     * The stamp is used to release or validate the lock.
     *
     *
     * ============================================================
     * 37. STAMPEDLOCK MODES
     * ============================================================
     *
     * 1. Write lock:
     *
     * long stamp = lock.writeLock();
     *
     * 2. Read lock:
     *
     * long stamp = lock.readLock();
     *
     * 3. Optimistic read:
     *
     * long stamp = lock.tryOptimisticRead();
     *
     * An optimistic read does not acquire a conventional
     * read lock.
     *
     * It allows reading while checking whether a conflicting
     * write occurred.
     *
     *
     * ============================================================
     * 38. OPTIMISTIC READ
     * ============================================================
     *
     * Optimistic reading is useful when reads are frequent
     * and writes are relatively uncommon.
     *
     * Example:
     *
     * StampedLock lock = new StampedLock();
     *
     * long stamp = lock.tryOptimisticRead();
     *
     * int localValue = sharedValue;
     *
     * if (!lock.validate(stamp)) {
     *
     *     stamp = lock.readLock();
     *
     *     try {
     *         localValue = sharedValue;
     *
     *     } finally {
     *         lock.unlockRead(stamp);
     *     }
     * }
     *
     * Explanation:
     *
     * 1. Attempt an optimistic read.
     * 2. Read the required fields.
     * 3. Validate the stamp.
     * 4. If validation fails, acquire a real read lock.
     * 5. Read the data again.
     * 6. Release the read lock.
     *
     * IMPORTANT:
     *
     * An optimistic read does not block writers.
     *
     * A writer may modify data while the optimistic read
     * is in progress.
     *
     * Therefore, validate before relying on the result.
     *
     * Keep optimistic-read sections simple and avoid
     * performing irreversible side effects before validation.
     *
     *
     * ============================================================
     * 39. RELEASING STAMPEDLOCK
     * ============================================================
     *
     * StampedLock uses different release methods:
     *
     * unlockRead(stamp)
     * unlockWrite(stamp)
     * unlock(stamp)
     *
     * Example:
     *
     * long stamp = lock.writeLock();
     *
     * try {
     *
     *     // Write operation.
     *
     * } finally {
     *
     *     lock.unlockWrite(stamp);
     * }
     *
     * Always release a successfully acquired lock
     * using its corresponding stamp.
     *
     *
     * ============================================================
     * 40. REENTRANTLOCK VS READWRITELOCK VS STAMPEDLOCK
     * ============================================================
     *
     * ReentrantLock:
     *
     * - Exclusive lock.
     * - Reentrant.
     * - Supports fairness option.
     * - Supports Conditions.
     *
     * ReentrantReadWriteLock:
     *
     * - Separate read and write locks.
     * - Multiple readers can proceed together.
     * - Writers get exclusive access.
     * - Reentrant.
     *
     * StampedLock:
     *
     * - Read, write and optimistic-read modes.
     * - Not reentrant.
     * - Uses stamps for lock management.
     * - Does not implement Lock.
     * - Does not provide Condition objects.
     *
     *
     * ============================================================
     * 41. WHEN SHOULD WE USE EACH?
     * ============================================================
     *
     * Use synchronized when:
     *
     * - Basic mutual exclusion is sufficient.
     * - The critical section is straightforward.
     * - Automatic lock release is desirable.
     *
     * Use ReentrantLock when:
     *
     * - tryLock() is needed.
     * - Timed acquisition is needed.
     * - Interruptible lock acquisition is needed.
     * - Multiple Condition queues are useful.
     * - An optional fairness policy is required.
     *
     * Use ReentrantReadWriteLock when:
     *
     * - Reads are frequent.
     * - Writes are relatively infrequent.
     * - Concurrent readers can improve throughput.
     *
     * Use StampedLock when:
     *
     * - Optimistic reads are useful.
     * - The application can correctly manage stamps.
     * - The non-reentrant design fits the use case.
     *
     * Do not choose a more complicated lock just because
     * it has more features.
     *
     *
     * ============================================================
     * 42. COMMON EXPLICIT-LOCK MISTAKES
     * ============================================================
     *
     * 1. Forgetting unlock().
     *
     * 2. Calling unlock() without owning the lock.
     *
     * 3. Not placing unlock() inside finally.
     *
     * 4. Forgetting to release every reentrant acquisition.
     *
     * 5. Treating tryLock() as if it always succeeds.
     *
     * 6. Ignoring InterruptedException from interruptible
     *    lock operations.
     *
     * 7. Using a Condition without holding its associated lock.
     *
     * 8. Using if instead of while around await().
     *
     * 9. Assuming a fair lock guarantees execution order.
     *
     * 10. Attempting an unsafe read-to-write lock upgrade.
     *
     * 11. Forgetting to validate optimistic reads.
     *
     * 12. Using StampedLock as if it were reentrant.
     *
     *
     * ============================================================
     * 43. INTERVIEW REVISION
     * ============================================================
     *
     * Q1. What is the Lock interface?
     *
     * Q2. What is ReentrantLock?
     *
     * Q3. What does reentrant mean?
     *
     * Q4. Why should unlock() be called inside finally?
     *
     * Q5. What is the difference between lock() and tryLock()?
     *
     * Q6. What is lockInterruptibly()?
     *
     * Q7. What is the hold count?
     *
     * Q8. What is a fair lock?
     *
     * Q9. Does fairness guarantee scheduling order?
     *
     * Q10. What is a Condition?
     *
     * Q11. What is the difference between await() and wait()?
     *
     * Q12. What is the advantage of multiple Condition objects?
     *
     * Q13. What is ReadWriteLock?
     *
     * Q14. What is the difference between read lock and write lock?
     *
     * Q15. What is lock downgrading?
     *
     * Q16. Why is read-to-write upgrading problematic?
     *
     * Q17. What is StampedLock?
     *
     * Q18. What is optimistic reading?
     *
     * Q19. Is StampedLock reentrant?
     *
     * Q20. When would you choose ReentrantLock over synchronized?
     *
     *
     * ============================================================
     * 44. FINAL TAKEAWAY
     * ============================================================
     *
     * Lock:
     * Provides explicit control over lock acquisition and release.
     *
     * ReentrantLock:
     * A reentrant exclusive lock with advanced acquisition options.
     *
     * tryLock():
     * Attempts to acquire a lock without waiting indefinitely.
     *
     * Condition:
     * Provides explicit condition-based thread coordination.
     *
     * ReadWriteLock:
     * Supports shared reading and exclusive writing.
     *
     * StampedLock:
     * Supports read, write and optimistic-read modes.
     *
     * Remember:
     *
     * Explicit locks provide flexibility, but they also
     * make the developer responsible for correct lock management.
     *
     * Always design for correctness first.
     * Then measure whether a more advanced locking mechanism
     * actually improves the application.
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Explicit Locks and Conditions , Happy Learning ..!");
    }
}
