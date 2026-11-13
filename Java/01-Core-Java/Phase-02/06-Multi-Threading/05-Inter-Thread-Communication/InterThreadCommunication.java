
public class InterThreadCommunication {

    /*
     * ============================================================
     * 1. WHY DO THREADS NEED TO COMMUNICATE?
     * ============================================================
     *
     * Synchronization allows threads to access shared resources
     * safely.
     *
     * But sometimes threads need more than mutual exclusion.
     *
     * They need to coordinate their activities.
     *
     * Example:
     *
     * Imagine a producer and a consumer.
     *
     * Producer:
     * Creates data and places it into a shared buffer.
     *
     * Consumer:
     * Takes data from the shared buffer.
     *
     * What if the buffer is empty?
     *
     * The consumer should wait until data becomes available.
     *
     * What if the buffer is full?
     *
     * The producer should wait until space becomes available.
     *
     * This is called inter-thread communication.
     *
     *
     * ============================================================
     * 2. WHAT IS INTER-THREAD COMMUNICATION?
     * ============================================================
     *
     * Inter-thread communication is a mechanism through which
     * threads coordinate their execution based on shared state
     * or conditions.
     *
     * Java traditionally provides three Object methods:
     *
     * 1. wait()
     * 2. notify()
     * 3. notifyAll()
     *
     * These methods belong to the Object class.
     *
     * They are used with an object's intrinsic monitor lock.
     *
     *
     * ============================================================
     * 3. WHY DO THESE METHODS BELONG TO OBJECT?
     * ============================================================
     *
     * Every Java object can act as a monitor.
     *
     * Threads coordinate through a shared object.
     *
     * Therefore, wait(), notify() and notifyAll() are
     * defined in Object rather than Thread.
     *
     * The object whose methods are called acts as the
     * coordination monitor.
     *
     *
     * ============================================================
     * 4. WAIT()
     * ============================================================
     *
     * wait() causes the current thread to wait until it is
     * notified, interrupted, or otherwise awakened.
     *
     * Most importantly:
     *
     * wait() releases the monitor lock of the object on
     * which it is called.
     *
     * Example:
     *
     * synchronized (lock) {
     *
     *     lock.wait();
     *
     * }
     *
     * Execution:
     *
     * 1. Thread acquires lock's monitor.
     * 2. Thread calls lock.wait().
     * 3. Thread releases that monitor.
     * 4. Thread enters the WAITING state.
     * 5. Another thread can acquire the same monitor.
     *
     * The waiting thread must reacquire the monitor before
     * wait() returns normally.
     *
     *
     * ============================================================
     * 5. WAIT() MUST BE CALLED INSIDE SYNCHRONIZATION
     * ============================================================
     *
     * The current thread must own the object's monitor
     * before calling wait().
     *
     * Correct:
     *
     * synchronized (lock) {
     *     lock.wait();
     * }
     *
     * Incorrect:
     *
     * lock.wait();
     *
     * Calling wait() without owning the object's monitor
     * throws IllegalMonitorStateException.
     *
     *
     * ============================================================
     * 6. NOTIFY()
     * ============================================================
     *
     * notify() wakes up one thread waiting on the same
     * object's monitor.
     *
     * Example:
     *
     * synchronized (lock) {
     *     lock.notify();
     * }
     *
     * IMPORTANT:
     *
     * notify() does not immediately release the monitor.
     *
     * The notifying thread continues executing inside
     * its synchronized region.
     *
     * The awakened thread can proceed only after it
     * successfully reacquires the same monitor.
     *
     * If multiple threads are waiting, Java does not
     * guarantee which waiting thread notify() selects.
     *
     *
     * ============================================================
     * 7. NOTIFYALL()
     * ============================================================
     *
     * notifyAll() wakes up all threads waiting on the
     * same object's monitor.
     *
     * Example:
     *
     * synchronized (lock) {
     *     lock.notifyAll();
     * }
     *
     * All waiting threads become eligible to compete
     * for the monitor.
     *
     * They do not all execute the synchronized section
     * simultaneously.
     *
     * Only one thread can own that object's monitor
     * at a time.
     *
     *
     * ============================================================
     * 8. WAIT VS NOTIFY VS NOTIFYALL
     * ============================================================
     *
     * wait():
     *
     * - Makes the current thread wait.
     * - Releases the object's monitor.
     * - Requires ownership of that monitor.
     *
     * notify():
     *
     * - Wakes one waiting thread.
     * - Does not immediately release the monitor.
     * - Requires ownership of that monitor.
     *
     * notifyAll():
     *
     * - Wakes all threads waiting on that monitor.
     * - Does not immediately release the monitor.
     * - Requires ownership of that monitor.
     *
     *
     * ============================================================
     * 9. THE WAIT SET
     * ============================================================
     *
     * Each object's monitor has an associated wait set.
     *
     * When a thread calls wait():
     *
     * - It releases the object's monitor.
     * - It enters that object's wait set.
     *
     * When notify() or notifyAll() is called:
     *
     * - One or all waiting threads are removed from the
     *   wait set as applicable.
     * - They must still compete to reacquire the monitor.
     *
     * A thread that has been notified is not guaranteed
     * to execute immediately.
     *
     *
     * ============================================================
     * 10. WAIT() VS SLEEP()
     * ============================================================
     *
     * Both methods can cause a thread to pause.
     *
     * But their behavior is different.
     *
     * Thread.sleep():
     *
     * - Belongs to Thread.
     * - Pauses the current thread for a time period.
     * - Does not release any monitor lock held by the thread.
     * - Does not require synchronized code.
     *
     * Object.wait():
     *
     * - Belongs to Object.
     * - Waits for notification, interruption or another
     *   permitted wake-up condition.
     * - Releases the monitor of the object on which it is called.
     * - Requires ownership of that object's monitor.
     *
     *
     * ============================================================
     * 11. WAIT() VS JOIN()
     * ============================================================
     *
     * wait():
     *
     * Waits for coordination through an object's monitor.
     *
     * join():
     *
     * Waits for another thread to terminate.
     *
     * Example:
     *
     * worker.join();
     *
     * The calling thread waits until worker terminates,
     * unless it is interrupted or a timed join expires.
     *
     * join() is a Thread method.
     *
     * wait() is an Object method.
     *
     *
     * ============================================================
     * 12. WHY WAIT() SHOULD BE USED INSIDE A WHILE LOOP
     * ============================================================
     *
     * This is one of the most important rules.
     *
     * Consider:
     *
     * synchronized (lock) {
     *
     *     if (!condition) {
     *         lock.wait();
     *     }
     *
     *     // Continue processing.
     * }
     *
     * This is not the recommended pattern.
     *
     * Why?
     *
     * A waiting thread can wake up without receiving
     * a notification. This is called a spurious wakeup.
     *
     * Also, after being notified, another thread may change
     * the condition before the awakened thread reacquires
     * the monitor.
     *
     * Therefore, the condition must be checked again.
     *
     * Correct pattern:
     *
     * synchronized (lock) {
     *
     *     while (!condition) {
     *         lock.wait();
     *     }
     *
     *     // Condition is currently satisfied.
     * }
     *
     * Remember:
     *
     * Always wait in a loop that checks the required condition.
     *
     *
     * ============================================================
     * 13. A SIMPLE WAIT AND NOTIFY EXAMPLE
     * ============================================================
     *
     * class SharedResource {
     *
     *     private boolean available = false;
     *
     *     synchronized void consume() throws InterruptedException {
     *
     *         while (!available) {
     *             wait();
     *         }
     *
     *         System.out.println("Consumed resource");
     *
     *         available = false;
     *     }
     *
     *     synchronized void produce() {
     *
     *         available = true;
     *
     *         System.out.println("Resource produced");
     *
     *         notifyAll();
     *     }
     * }
     *
     * Explanation:
     *
     * Consumer:
     *
     * 1. Acquires the SharedResource object's monitor.
     * 2. Checks available.
     * 3. If false, calls wait().
     * 4. Releases the monitor and waits.
     *
     * Producer:
     *
     * 1. Acquires the same object's monitor.
     * 2. Sets available to true.
     * 3. Calls notifyAll().
     * 4. Eventually releases the monitor.
     *
     * Consumer:
     *
     * 1. Becomes eligible to reacquire the monitor.
     * 2. Reacquires it.
     * 3. Checks available again.
     * 4. Continues if the condition is true.
     *
     *
     * ============================================================
     * 14. WHY NOTIFY DOES NOT RELEASE THE LOCK
     * ============================================================
     *
     * Consider:
     *
     * synchronized (lock) {
     *
     *     lock.notify();
     *
     *     System.out.println("Notifying thread continues");
     * }
     *
     * The notifying thread still owns the monitor after
     * calling notify().
     *
     * The awakened thread cannot enter a synchronized
     * section guarded by that monitor until the notifying
     * thread releases it.
     *
     * This is important when predicting thread execution.
     *
     *
     * ============================================================
     * 15. TIMED WAIT
     * ============================================================
     *
     * Java provides overloaded wait() methods.
     *
     * wait():
     * Waits without a specified timeout.
     *
     * wait(long timeout):
     * Waits for up to the specified number of milliseconds,
     * subject to the method's timing behavior.
     *
     * wait(long timeout, int nanos):
     * Supports millisecond and additional nanosecond precision.
     *
     * Example:
     *
     * synchronized (lock) {
     *
     *     lock.wait(3000);
     * }
     *
     * The thread can wake because of:
     *
     * - Notification.
     * - Interruption.
     * - Timeout.
     * - A spurious wakeup.
     *
     * A timeout does not guarantee that the thread immediately
     * resumes execution at that exact time.
     *
     * It must reacquire the monitor first.
     *
     *
     * ============================================================
     * 16. INTERRUPTING A WAITING THREAD
     * ============================================================
     *
     * A thread waiting through wait() can be interrupted.
     *
     * Example:
     *
     * Thread worker = new Thread(() -> {
     *
     *     synchronized (lock) {
     *
     *         try {
     *             lock.wait();
     *
     *         } catch (InterruptedException e) {
     *
     *             Thread.currentThread().interrupt();
     *         }
     *     }
     * });
     *
     * worker.start();
     *
     * worker.interrupt();
     *
     * wait() throws InterruptedException.
     *
     * When InterruptedException is thrown, the interrupt
     * status is cleared.
     *
     * The thread should handle interruption appropriately,
     * such as propagating the exception or restoring the
     * interrupt status.
     *
     *
     * ============================================================
     * 17. PRODUCER-CONSUMER PROBLEM
     * ============================================================
     *
     * The producer-consumer problem is a classic example
     * of inter-thread communication.
     *
     * Producer:
     * Generates items and puts them into a buffer.
     *
     * Consumer:
     * Takes items from the buffer.
     *
     * Buffer:
     * Shared storage between producer and consumer.
     *
     * Two important conditions:
     *
     * 1. Producer must wait when the buffer is full.
     * 2. Consumer must wait when the buffer is empty.
     *
     * wait() and notifyAll() can coordinate these operations.
     *
     *
     * ============================================================
     * 18. PRODUCER-CONSUMER USING A SINGLE-SLOT BUFFER
     * ============================================================
     *
     * Example:
     *
     * class SharedBuffer {
     *
     *     private Integer item;
     *
     *     synchronized void put(int value)
     *             throws InterruptedException {
     *
     *         while (item != null) {
     *             wait();
     *         }
     *
     *         item = value;
     *
     *         System.out.println("Produced: " + value);
     *
     *         notifyAll();
     *     }
     *
     *     synchronized int take()
     *             throws InterruptedException {
     *
     *         while (item == null) {
     *             wait();
     *         }
     *
     *         int value = item;
     *
     *         item = null;
     *
     *         System.out.println("Consumed: " + value);
     *
     *         notifyAll();
     *
     *         return value;
     *     }
     * }
     *
     * Explanation:
     *
     * When the buffer contains an item:
     *
     * - Another producer waits.
     * - A consumer can take the item.
     *
     * When the buffer is empty:
     *
     * - A consumer waits.
     * - A producer can put an item.
     *
     * The while loops protect against spurious wakeups
     * and changes in the condition before monitor reacquisition.
     *
     *
     * ============================================================
     * 19. WHY NOTIFYALL IS OFTEN PREFERRED
     * ============================================================
     *
     * Imagine:
     *
     * - Multiple producers.
     * - Multiple consumers.
     * - One shared buffer.
     *
     * Both producers and consumers may wait on the same
     * object's monitor.
     *
     * notify() wakes an arbitrary waiting thread.
     *
     * It might wake a producer when the buffer is still full.
     *
     * That producer checks the condition and waits again.
     *
     * A consumer that could have made progress might remain
     * asleep.
     *
     * notifyAll() wakes all waiting threads.
     *
     * Each thread reacquires the monitor and checks its own
     * condition in a while loop.
     *
     * This can avoid certain notification-selection problems.
     *
     * However, waking all threads can cause extra contention.
     *
     *
     * ============================================================
     * 20. MISSED NOTIFICATIONS
     * ============================================================
     *
     * A notification is not stored as a permanent signal.
     *
     * If notify() is called when no thread is waiting,
     * that notification does not remain available for a
     * future waiter.
     *
     * Therefore, do not use notification as a standalone
     * message that can be sent before a thread starts waiting.
     *
     * Instead:
     *
     * 1. Maintain shared state representing the condition.
     * 2. Check that condition while holding the monitor.
     * 3. Call wait() only while the condition is false.
     * 4. Update the condition under the same monitor.
     * 5. Notify waiting threads after the update.
     *
     * The shared condition is the source of truth,
     * not the notification itself.
     *
     *
     * ============================================================
     * 21. WAITING ON THE WRONG OBJECT
     * ============================================================
     *
     * Example:
     *
     * synchronized (lockA) {
     *     lockB.wait();
     * }
     *
     * This is incorrect unless the current thread also owns
     * lockB's monitor.
     *
     * It throws IllegalMonitorStateException.
     *
     * Correct:
     *
     * synchronized (lockB) {
     *     lockB.wait();
     * }
     *
     * The object used for synchronization must match
     * the object on which wait(), notify() or notifyAll()
     * is called.
     *
     *
     * ============================================================
     * 22. WAIT SET VS BLOCKED STATE
     * ============================================================
     *
     * WAITING:
     *
     * A thread can enter WAITING when it calls wait()
     * without a timeout.
     *
     * It has released the monitor associated with wait().
     *
     * BLOCKED:
     *
     * A thread enters BLOCKED when it is trying to acquire
     * an intrinsic monitor lock owned by another thread.
     *
     * After notification, a waiting thread must reacquire
     * the monitor.
     *
     * While waiting to reacquire it, it may be BLOCKED.
     *
     *
     * ============================================================
     * 23. WAIT() AND REENTRANT LOCKS
     * ============================================================
     *
     * Intrinsic locks are reentrant.
     *
     * If a thread owns the same monitor multiple times,
     * calling wait() releases that monitor completely.
     *
     * When the thread wakes and reacquires the monitor,
     * it restores its previous reentrant lock-hold count.
     *
     * This is important when synchronized methods call
     * other synchronized methods on the same object.
     *
     *
     * ============================================================
     * 24. COMMON MISTAKES
     * ============================================================
     *
     * 1. Calling wait() outside synchronized code.
     *
     * 2. Calling notify() without owning the object's monitor.
     *
     * 3. Calling wait() on a different object from the one
     *    whose monitor is held.
     *
     * 4. Using if instead of while around a wait condition.
     *
     * 5. Assuming notify() immediately releases the monitor.
     *
     * 6. Assuming notify() wakes a particular thread.
     *
     * 7. Assuming notifyAll() lets all awakened threads
     *    execute simultaneously.
     *
     * 8. Forgetting to handle InterruptedException.
     *
     * 9. Treating notification as a stored message.
     *
     * 10. Updating the shared condition outside the
     *     synchronization strategy that protects it.
     *
     *
     * ============================================================
     * 25. INTERVIEW REVISION
     * ============================================================
     *
     * Q1. What is inter-thread communication?
     *
     * Q2. Why do wait(), notify() and notifyAll()
     *     belong to Object?
     *
     * Q3. What does wait() do to the monitor lock?
     *
     * Q4. Why must wait() be called inside synchronized code?
     *
     * Q5. What is the difference between notify() and notifyAll()?
     *
     * Q6. Does notify() release the lock immediately?
     *
     * Q7. Why should wait() be used inside a while loop?
     *
     * Q8. What is a spurious wakeup?
     *
     * Q9. What is the difference between wait() and sleep()?
     *
     * Q10. What happens when a waiting thread is interrupted?
     *
     * Q11. What is the producer-consumer problem?
     *
     * Q12. What is a missed notification?
     *
     * Q13. What is the difference between WAITING and BLOCKED?
     *
     * Q14. Does notifyAll() mean all waiting threads execute
     *      at the same time?
     *
     *
     * ============================================================
     * 26. MODERN ALTERNATIVES
     * ============================================================
     *
     * wait() and notify() are fundamental Java mechanisms.
     *
     * But modern applications often use higher-level
     * concurrency utilities.
     *
     * Examples:
     *
     * - BlockingQueue
     * - CountDownLatch
     * - CyclicBarrier
     * - Semaphore
     * - Condition
     * - CompletableFuture
     *
     * BlockingQueue is especially useful for producer-consumer
     * designs because it handles waiting and coordination
     * internally.
     *
     * These utilities are covered in other concurrency topics.
     *
     *
     * ============================================================
     * 27. FINAL TAKEAWAY
     * ============================================================
     *
     * wait():
     * Makes the current thread wait and releases the
     * object's monitor.
     *
     * notify():
     * Wakes one thread waiting on that object's monitor.
     *
     * notifyAll():
     * Wakes all threads waiting on that object's monitor.
     *
     * while:
     * Rechecks the condition after every wake-up.
     *
     * Producer-consumer:
     * A classic example of inter-thread coordination.
     *
     * Remember:
     *
     * Threads do not communicate safely just by waking
     * each other up.
     *
     * They coordinate through shared state protected
     * by a consistent synchronization strategy.
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Inter-Thread Communication , Happy Learning ..!");
    }
}
