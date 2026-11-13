
public class ThreadControlAndManagement {

    /*
     * ============================================================
     * 1. WHY DO WE NEED THREAD CONTROL?
     * ============================================================
     *
     * Creating threads is only the beginning.
     *
     * In real applications, we also need to:
     *
     * - Identify threads.
     * - Request threads to stop their work.
     * - Control background threads.
     * - Handle uncaught exceptions.
     * - Monitor thread execution.
     * - Maintain thread-specific data.
     *
     * Java provides several Thread APIs for these purposes.
     *
     *
     * ============================================================
     * 2. THREAD NAMING
     * ============================================================
     *
     * Every Thread has a name.
     *
     * Default names commonly look like:
     *
     * Thread-0
     * Thread-1
     * Thread-2
     *
     * You can provide a meaningful name.
     *
     * Example:
     *
     * Thread worker = new Thread(
     *     () -> System.out.println("Processing"),
     *     "File-Processor"
     * );
     *
     * worker.start();
     *
     * Get the name:
     *
     * System.out.println(worker.getName());
     *
     * Change the name:
     *
     * worker.setName("Database-Worker");
     *
     * WHY IS NAMING IMPORTANT?
     *
     * Meaningful names make:
     *
     * - Logs easier to understand.
     * - Thread dumps easier to analyze.
     * - Debugging easier.
     * - Production monitoring clearer.
     *
     * Naming is especially important when an application
     * uses thread pools.
     *
     *
     * ============================================================
     * 3. CURRENTTHREAD()
     * ============================================================
     *
     * Thread.currentThread() returns the Thread object
     * representing the currently executing thread.
     *
     * Example:
     *
     * public static void main(String[] args) {
     *
     *     Thread current = Thread.currentThread();
     *
     *     System.out.println(current.getName());
     * }
     *
     * Typical output:
     *
     * main
     *
     * It is useful inside Runnable tasks too:
     *
     * Runnable task = () -> {
     *
     *     Thread current = Thread.currentThread();
     *
     *     System.out.println(current.getName());
     * };
     *
     *
     * ============================================================
     * 4. THREAD ID
     * ============================================================
     *
     * Every Thread has a unique identifier.
     *
     * Example:
     *
     * Thread current = Thread.currentThread();
     *
     * long id = current.threadId();
     *
     * threadId() was introduced in Java 19.
     *
     * The older getId() method was deprecated in Java 19.
     *
     * A Java thread ID is not necessarily the same as
     * its operating-system thread ID.
     *
     *
     * ============================================================
     * 5. THREAD PRIORITY
     * ============================================================
     *
     * Java provides thread priorities.
     *
     * Priority values:
     *
     * MIN_PRIORITY  = 1
     * NORM_PRIORITY = 5
     * MAX_PRIORITY = 10
     *
     * Example:
     *
     * Thread worker = new Thread(() -> {
     *     System.out.println("Working");
     * });
     *
     * worker.setPriority(Thread.MAX_PRIORITY);
     *
     * worker.start();
     *
     * Get priority:
     *
     * worker.getPriority();
     *
     * IMPORTANT:
     *
     * Priority is a scheduling hint.
     *
     * It does not guarantee:
     *
     * - Which thread executes first.
     * - How long a thread executes.
     * - That a higher-priority thread finishes first.
     *
     * Do not depend on priority for program correctness.
     *
     * Modern JVMs and operating systems may map priorities
     * differently.
     *
     *
     * ============================================================
     * 6. DAEMON THREADS
     * ============================================================
     *
     * Java threads are classified as:
     *
     * - Daemon threads.
     * - Non-daemon threads.
     *
     * A daemon thread performs background support work.
     *
     * Examples of JVM background activities may include:
     *
     * - Garbage collection.
     * - Certain runtime maintenance activities.
     *
     * A non-daemon thread is a normal application thread.
     *
     * IMPORTANT JVM RULE:
     *
     * The JVM continues running while at least one
     * non-daemon thread is alive.
     *
     * When all non-daemon threads have terminated, the JVM
     * may exit even if daemon threads are still running.
     *
     *
     * ============================================================
     * 7. CREATING A DAEMON THREAD
     * ============================================================
     *
     * Example:
     *
     * Thread worker = new Thread(() -> {
     *
     *     while (true) {
     *         System.out.println("Background task");
     *     }
     * });
     *
     * worker.setDaemon(true);
     *
     * worker.start();
     *
     * IMPORTANT:
     *
     * setDaemon(true) must be called before start().
     *
     * Calling setDaemon() after the thread has started
     * throws IllegalThreadStateException.
     *
     * Check whether a thread is a daemon:
     *
     * worker.isDaemon();
     *
     *
     * ============================================================
     * 8. DAEMON VS NON-DAEMON THREADS
     * ============================================================
     *
     * DAEMON:
     *
     * - Performs background support work.
     * - Does not keep the JVM alive by itself.
     * - Can be terminated when the JVM exits.
     * - Should not be relied on for critical cleanup.
     *
     * NON-DAEMON:
     *
     * - Normal application thread.
     * - Keeps the JVM alive while running.
     * - Must complete or terminate for normal JVM shutdown.
     *
     * IMPORTANT:
     *
     * Daemon threads are not simply lower-priority threads.
     *
     * Daemon status and thread priority are separate concepts.
     *
     *
     * ============================================================
     * 9. INTERRUPTING A THREAD
     * ============================================================
     *
     * Sometimes a thread needs to stop its current work.
     *
     * Java provides interruption as a cooperative
     * cancellation mechanism.
     *
     * Call:
     *
     * thread.interrupt();
     *
     * This requests interruption.
     *
     * It does not forcibly terminate the thread.
     *
     * The target thread must respond appropriately.
     *
     * Example:
     *
     * Thread worker = new Thread(() -> {
     *
     *     while (!Thread.currentThread().isInterrupted()) {
     *
     *         System.out.println("Working...");
     *     }
     *
     *     System.out.println("Stopping");
     * });
     *
     * worker.start();
     *
     * worker.interrupt();
     *
     * The worker checks its interrupt status and exits
     * when it observes the request.
     *
     *
     * ============================================================
     * 10. WHAT IS INTERRUPT STATUS?
     * ============================================================
     *
     * A thread maintains an interrupt status.
     *
     * Java provides two commonly used methods:
     *
     * isInterrupted()
     * interrupted()
     *
     * isInterrupted():
     *
     * - Instance method.
     * - Checks the interrupt status of that Thread object.
     * - Does not clear the status.
     *
     * interrupted():
     *
     * - Static method.
     * - Checks the current thread's interrupt status.
     * - Clears the status if it is set.
     *
     * Example:
     *
     * Thread.currentThread().interrupt();
     *
     * System.out.println(
     *     Thread.currentThread().isInterrupted()
     * );
     *
     * // true
     *
     * System.out.println(Thread.interrupted());
     *
     * // true
     *
     * System.out.println(
     *     Thread.currentThread().isInterrupted()
     * );
     *
     * // false
     *
     * The final result is false because interrupted()
     * cleared the status.
     *
     *
     * ============================================================
     * 11. INTERRUPTED EXCEPTION
     * ============================================================
     *
     * Some blocking methods respond to interruption by
     * throwing InterruptedException.
     *
     * Examples:
     *
     * Thread.sleep()
     * Object.wait()
     * Thread.join()
     *
     * Example:
     *
     * Thread worker = new Thread(() -> {
     *
     *     try {
     *
     *         Thread.sleep(10000);
     *
     *     } catch (InterruptedException e) {
     *
     *         System.out.println("Worker interrupted");
     *     }
     * });
     *
     * worker.start();
     *
     * worker.interrupt();
     *
     * When sleep() detects the interruption, it throws
     * InterruptedException.
     *
     * IMPORTANT:
     *
     * When InterruptedException is thrown, the interrupt
     * status is cleared.
     *
     *
     * ============================================================
     * 12. HOW TO HANDLE INTERRUPTION CORRECTLY
     * ============================================================
     *
     * If your method cannot propagate InterruptedException,
     * a common approach is to restore the interrupt status.
     *
     * Example:
     *
     * try {
     *
     *     Thread.sleep(5000);
     *
     * } catch (InterruptedException e) {
     *
     *     Thread.currentThread().interrupt();
     *
     *     return;
     * }
     *
     * Why restore it?
     *
     * The exception cleared the interrupt status.
     *
     * Restoring it allows higher-level code to observe
     * the interruption request.
     *
     * If your method can declare InterruptedException,
     * you can propagate it instead.
     *
     * Example:
     *
     * public void performTask() throws InterruptedException {
     *
     *     Thread.sleep(5000);
     * }
     *
     * IMPORTANT:
     *
     * Do not silently swallow InterruptedException.
     *
     *
     * ============================================================
     * 13. INTERRUPT AS COOPERATIVE CANCELLATION
     * ============================================================
     *
     * Java does not normally force a thread to stop.
     *
     * Instead, interruption communicates:
     *
     * "Please stop or cancel your current work."
     *
     * The thread decides how to respond.
     *
     * Example:
     *
     * Thread worker = new Thread(() -> {
     *
     *     while (!Thread.currentThread().isInterrupted()) {
     *
     *         // Perform a small unit of work.
     *
     *     }
     *
     *     // Perform appropriate cleanup.
     * });
     *
     * worker.start();
     *
     * worker.interrupt();
     *
     * This is safer than forcibly terminating a thread.
     *
     * A long-running task should check interruption
     * regularly or use interruptible blocking operations.
     *
     *
     * ============================================================
     * 14. DEPRECATED THREAD TERMINATION METHODS
     * ============================================================
     *
     * Older Java APIs included:
     *
     * stop()
     * suspend()
     * resume()
     *
     * These methods are deprecated.
     *
     * WHY IS stop() UNSAFE?
     *
     * It can terminate a thread while it is modifying
     * shared data.
     *
     * This may leave objects in inconsistent states.
     *
     * WHY ARE suspend() AND resume() UNSAFE?
     *
     * Suspending a thread while it holds a lock can prevent
     * other threads from making progress.
     *
     * Use cooperative interruption and proper coordination
     * instead.
     *
     *
     * ============================================================
     * 15. ISALIVE()
     * ============================================================
     *
     * isAlive() checks whether a thread has been started
     * and has not yet terminated.
     *
     * Example:
     *
     * Thread worker = new Thread(() -> {
     *
     *     System.out.println("Working");
     * });
     *
     * System.out.println(worker.isAlive());
     * // false
     *
     * worker.start();
     *
     * System.out.println(worker.isAlive());
     *
     * The result depends on timing.
     *
     * After the thread terminates:
     *
     * worker.isAlive();
     *
     * // false
     *
     * Use join() when you need to wait for completion.
     *
     *
     * ============================================================
     * 16. UNCAUGHT EXCEPTIONS IN THREADS
     * ============================================================
     *
     * What happens if an exception escapes from a thread's
     * run() method?
     *
     * The thread terminates.
     *
     * Java provides an UncaughtExceptionHandler to handle
     * uncaught exceptions.
     *
     * Interface:
     *
     * Thread.UncaughtExceptionHandler
     *
     * Its method:
     *
     * void uncaughtException(
     *     Thread thread,
     *     Throwable exception
     * );
     *
     *
     * ============================================================
     * 17. SETTING AN UNCAUGHT EXCEPTION HANDLER
     * ============================================================
     *
     * Example:
     *
     * Thread worker = new Thread(() -> {
     *
     *     throw new RuntimeException("Something went wrong");
     * });
     *
     * worker.setUncaughtExceptionHandler(
     *     (thread, exception) -> {
     *
     *         System.out.println(
     *             "Thread: " + thread.getName()
     *         );
     *
     *         System.out.println(
     *             "Error: " + exception.getMessage()
     *         );
     *     }
     * );
     *
     * worker.start();
     *
     * The handler receives an exception that was not caught
     * by the thread's execution code.
     *
     * IMPORTANT:
     *
     * The handler does not automatically restart the thread.
     *
     *
     * ============================================================
     * 18. DEFAULT UNCAUGHT EXCEPTION HANDLER
     * ============================================================
     *
     * Java allows configuring a default uncaught exception
     * handler for threads that do not have a more specific
     * handler.
     *
     * Example:
     *
     * Thread.setDefaultUncaughtExceptionHandler(
     *     (thread, exception) -> {
     *
     *         System.out.println(
     *             thread.getName() + " failed"
     *         );
     *     }
     * );
     *
     * This is useful for centralized logging.
     *
     * A thread-specific handler takes precedence over
     * the default handler.
     *
     *
     * ============================================================
     * 19. THREADGROUP
     * ============================================================
     *
     * ThreadGroup is a legacy API that represents a group
     * of threads.
     *
     * It allows threads to be grouped together.
     *
     * Example:
     *
     * ThreadGroup group = new ThreadGroup("Workers");
     *
     * Thread t1 = new Thread(group, () -> {
     *     System.out.println("Task 1");
     * });
     *
     * Thread t2 = new Thread(group, () -> {
     *     System.out.println("Task 2");
     * });
     *
     * t1.start();
     * t2.start();
     *
     * ThreadGroup can provide information about threads
     * in the group.
     *
     * IMPORTANT:
     *
     * ThreadGroup is largely considered a legacy mechanism.
     *
     * Modern applications generally use executors and
     * structured task management instead of relying on
     * ThreadGroup for lifecycle management.
     *
     *
     * ============================================================
     * 20. THREADLOCAL
     * ============================================================
     *
     * ThreadLocal provides thread-specific variables.
     *
     * Each thread accessing a ThreadLocal has its own
     * independently initialized value.
     *
     * Example:
     *
     * ThreadLocal<Integer> local =
     *     ThreadLocal.withInitial(() -> 0);
     *
     * Thread t1 = new Thread(() -> {
     *
     *     local.set(100);
     *
     *     System.out.println(local.get());
     * });
     *
     * Thread t2 = new Thread(() -> {
     *
     *     local.set(200);
     *
     *     System.out.println(local.get());
     * });
     *
     * t1.start();
     * t2.start();
     *
     * Possible output:
     *
     * 100
     * 200
     *
     * Each thread has its own value.
     *
     * The values do not overwrite each other.
     *
     *
     * ============================================================
     * 21. WHY DO WE NEED THREADLOCAL?
     * ============================================================
     *
     * Normally, multiple threads can access the same shared
     * object.
     *
     * This may require synchronization if mutable state
     * is shared.
     *
     * ThreadLocal provides separate values per thread.
     *
     * Common use cases:
     *
     * - Request-specific context.
     * - User context in older thread-per-request systems.
     * - Per-thread formatting objects.
     * - Per-thread diagnostic information.
     *
     * IMPORTANT:
     *
     * ThreadLocal does not make an object globally thread-safe.
     *
     * It gives each thread its own associated value.
     *
     *
     * ============================================================
     * 22. THREADLOCAL METHODS
     * ============================================================
     *
     * set(value):
     * Stores a value for the current thread.
     *
     * get():
     * Retrieves the current thread's value.
     *
     * remove():
     * Removes the current thread's value.
     *
     * withInitial(supplier):
     * Creates a ThreadLocal with an initial-value supplier.
     *
     * Example:
     *
     * ThreadLocal<String> user =
     *     ThreadLocal.withInitial(() -> "Guest");
     *
     * user.set("Vinay");
     *
     * System.out.println(user.get());
     *
     * user.remove();
     *
     *
     * ============================================================
     * 23. WHY MUST WE REMOVE THREADLOCAL VALUES?
     * ============================================================
     *
     * This is especially important when using thread pools.
     *
     * Thread-pool threads are reused for multiple tasks.
     *
     * Suppose:
     *
     * Task A stores user information in a ThreadLocal.
     *
     * Task A finishes.
     *
     * The same worker thread executes Task B.
     *
     * If Task A does not remove its value, Task B may
     * accidentally observe stale data.
     *
     * Example:
     *
     * try {
     *
     *     userContext.set("Vinay");
     *
     *     // Process request.
     *
     * } finally {
     *
     *     userContext.remove();
     * }
     *
     * IMPORTANT:
     *
     * Remove ThreadLocal values when their intended scope ends,
     * especially when working with pooled threads.
     *
     *
     * ============================================================
     * 24. INHERITABLETHREADLOCAL
     * ============================================================
     *
     * InheritableThreadLocal allows a child thread to inherit
     * an initial value from its parent thread when the child
     * thread is created.
     *
     * Example:
     *
     * InheritableThreadLocal<String> context =
     *     new InheritableThreadLocal<>();
     *
     * context.set("Parent Context");
     *
     * Thread child = new Thread(() -> {
     *
     *     System.out.println(context.get());
     * });
     *
     * child.start();
     *
     * Possible output:
     *
     * Parent Context
     *
     * IMPORTANT:
     *
     * Inheritance happens when the child thread is created.
     *
     * It is not automatic context propagation for arbitrary
     * asynchronous tasks or thread-pool tasks.
     *
     * It can also be problematic when thread pools reuse threads.
     *
     *
     * ============================================================
     * 25. THREADLOCAL VS INHERITABLETHREADLOCAL
     * ============================================================
     *
     * ThreadLocal:
     *
     * - Each thread has its own value.
     * - A newly created child thread does not automatically
     *   inherit the parent's value.
     *
     * InheritableThreadLocal:
     *
     * - Child threads can inherit the parent's initial value
     *   at child-thread creation time.
     *
     * Neither is a general-purpose replacement for explicit
     * context propagation in asynchronous systems.
     *
     *
     * ============================================================
     * 26. THREAD CONTROL IN BACKEND APPLICATIONS
     * ============================================================
     *
     * In backend applications, thread management matters
     * for reliability and observability.
     *
     * Examples:
     *
     * 1. Meaningful thread names help identify request workers.
     *
     * 2. Interruption supports cooperative cancellation.
     *
     * 3. Uncaught exception handlers help with logging.
     *
     * 4. ThreadLocal can hold request-specific context in
     *    appropriate thread-bound designs.
     *
     * 5. Thread pools control resource usage and reuse workers.
     *
     * IMPORTANT:
     *
     * In asynchronous applications, request context may need
     * explicit propagation.
     *
     * ThreadLocal does not automatically follow work when
     * execution moves to another thread.
     *
     *
     * ============================================================
     * 27. COMMON MISTAKES
     * ============================================================
     *
     * 1. Thinking interrupt() forcibly kills a thread.
     *
     * 2. Ignoring InterruptedException.
     *
     * 3. Swallowing InterruptedException without restoring
     *    the interrupt status or propagating it.
     *
     * 4. Calling setDaemon() after start().
     *
     * 5. Assuming a higher priority guarantees earlier execution.
     *
     * 6. Assuming daemon threads always complete their tasks.
     *
     * 7. Forgetting ThreadLocal.remove() in pooled threads.
     *
     * 8. Assuming ThreadLocal values automatically propagate
     *    to every child or asynchronous task.
     *
     * 9. Treating ThreadGroup as a modern thread-pool mechanism.
     *
     * 10. Assuming an uncaught exception handler restarts a thread.
     *
     *
     * ============================================================
     * 28. INTERVIEW REVISION
     * ============================================================
     *
     * Q1. What is the purpose of thread naming?
     *
     * Q2. What is thread priority?
     *
     * Q3. Does thread priority guarantee execution order?
     *
     * Q4. What is a daemon thread?
     *
     * Q5. When can the JVM exit?
     *
     * Q6. What is the difference between daemon and non-daemon?
     *
     * Q7. What does interrupt() do?
     *
     * Q8. What is the difference between isInterrupted()
     *     and interrupted()?
     *
     * Q9. Why should InterruptedException not be swallowed?
     *
     * Q10. What is cooperative cancellation?
     *
     * Q11. What is an UncaughtExceptionHandler?
     *
     * Q12. What is ThreadGroup?
     *
     * Q13. What is ThreadLocal?
     *
     * Q14. Why should ThreadLocal.remove() be used?
     *
     * Q15. What is InheritableThreadLocal?
     *
     * Q16. Does ThreadLocal automatically propagate context
     *      to executor threads?
     *
     *
     * ============================================================
     * 29. FINAL TAKEAWAY
     * ============================================================
     *
     * Thread naming:
     * Makes threads easier to identify.
     *
     * Priority:
     * Provides a scheduling hint.
     *
     * Daemon:
     * A background thread that does not keep the JVM alive.
     *
     * Interrupt:
     * Requests cooperative cancellation.
     *
     * UncaughtExceptionHandler:
     * Handles exceptions escaping a thread's execution.
     *
     * ThreadLocal:
     * Stores a separate value for each thread.
     *
     * InheritableThreadLocal:
     * Allows child threads to inherit values at creation.
     *
     * Remember:
     *
     * Good thread management is not about forcibly controlling
     * threads.
     *
     * It is about designing clear ownership, cancellation,
     * cleanup, monitoring and execution policies.
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Thread Control and Management , Happy Learning ..!");
    }
}
