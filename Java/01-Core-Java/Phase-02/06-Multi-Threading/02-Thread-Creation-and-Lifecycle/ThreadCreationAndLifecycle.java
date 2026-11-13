
public class ThreadCreationAndLifecycle {

    /*
     * ============================================================
     * 1. HOW DOES JAVA EXECUTE A THREAD?
     * ============================================================
     *
     * Every Java application starts with an initial thread
     * that executes the main() method.
     *
     * Example:
     *
     * public class Demo {
     *
     *     public static void main(String[] args) {
     *         System.out.println("Hello Java");
     *     }
     * }
     *
     * The main thread executes the statements inside main().
     *
     * We can create additional threads to execute other tasks.
     *
     * Java provides the Thread class and Runnable interface
     * as fundamental thread-creation mechanisms.
     *
     *
     * ============================================================
     * 2. WHAT IS THE THREAD CLASS?
     * ============================================================
     *
     * Thread is a class in the java.lang package.
     *
     * It represents a thread of execution.
     *
     * It provides methods to:
     *
     * - Start a thread.
     * - Get its name and ID.
     * - Check its state.
     * - Interrupt it.
     * - Wait for its completion.
     * - Set its priority.
     *
     * Package:
     *
     * java.lang
     *
     * No explicit import is required.
     *
     *
     * ============================================================
     * 3. FIRST WAY: EXTENDING THE THREAD CLASS
     * ============================================================
     *
     * We can create a thread by extending the Thread class.
     *
     * Example:
     *
     * class MyThread extends Thread {
     *
     *     @Override
     *     public void run() {
     *         System.out.println("Child thread is running");
     *     }
     * }
     *
     * public class Demo {
     *
     *     public static void main(String[] args) {
     *
     *         MyThread thread = new MyThread();
     *
     *         thread.start();
     *     }
     * }
     *
     * Explanation:
     *
     * 1. MyThread extends Thread.
     *
     * 2. We override run() to define the task.
     *
     * 3. We create a MyThread object.
     *
     * 4. We call start().
     *
     * 5. The JVM starts a new thread of execution.
     *
     * 6. The new thread executes run().
     *
     * IMPORTANT:
     *
     * Calling start() is what starts a new thread.
     *
     * Merely creating a Thread object does not start a thread.
     *
     *
     * ============================================================
     * 4. SECOND WAY: IMPLEMENTING RUNNABLE
     * ============================================================
     *
     * Runnable is a functional interface in java.lang.
     *
     * It represents a task that can be executed by a thread.
     *
     * Its main method is:
     *
     * public void run();
     *
     * Example:
     *
     * class MyTask implements Runnable {
     *
     *     @Override
     *     public void run() {
     *         System.out.println("Runnable task is running");
     *     }
     * }
     *
     * public class Demo {
     *
     *     public static void main(String[] args) {
     *
     *         MyTask task = new MyTask();
     *
     *         Thread thread = new Thread(task);
     *
     *         thread.start();
     *     }
     * }
     *
     * Explanation:
     *
     * 1. MyTask implements Runnable.
     *
     * 2. Its run() method contains the task.
     *
     * 3. We pass the task to a Thread object.
     *
     * 4. We call start().
     *
     * 5. The new thread executes the Runnable task.
     *
     *
     * ============================================================
     * 5. THIRD WAY: USING A LAMBDA EXPRESSION
     * ============================================================
     *
     * Runnable is a functional interface.
     *
     * Therefore, we can use a lambda expression.
     *
     * Example:
     *
     * public class Demo {
     *
     *     public static void main(String[] args) {
     *
     *         Thread thread = new Thread(() -> {
     *
     *             System.out.println("Task running");
     *
     *         });
     *
     *         thread.start();
     *     }
     * }
     *
     * This is a concise way of supplying a Runnable task.
     *
     * Equivalent traditional implementation:
     *
     * Thread thread = new Thread(new Runnable() {
     *
     *     @Override
     *     public void run() {
     *         System.out.println("Task running");
     *     }
     * });
     *
     *
     * ============================================================
     * 6. THREAD CLASS VS RUNNABLE INTERFACE
     * ============================================================
     *
     * EXTENDING THREAD:
     *
     * - Your class becomes a Thread subclass.
     * - You override run().
     * - You cannot extend another class.
     * - Task and thread representation are coupled.
     *
     * IMPLEMENTING RUNNABLE:
     *
     * - Your class represents a task.
     * - You pass the task to a Thread.
     * - Your class can extend another class.
     * - Task and thread management are separated.
     * - The same Runnable can be supplied to different threads.
     *
     * PREFERRED APPROACH:
     *
     * Runnable is generally preferred when you only need
     * to represent a task.
     *
     * Why?
     *
     * Java supports single class inheritance.
     *
     * Implementing Runnable does not consume your class's
     * ability to extend another class.
     *
     *
     * ============================================================
     * 7. IMPORTANT: START() VS RUN()
     * ============================================================
     *
     * This is one of the most important multithreading concepts.
     *
     * start():
     *
     * - Starts a new thread of execution.
     * - The JVM schedules the new thread.
     * - The new thread executes run().
     * - A Thread object can be started only once.
     *
     * run():
     *
     * - Contains the task's execution logic.
     * - Calling it directly is an ordinary method call.
     * - It does not create a new thread.
     *
     * Example:
     *
     * Thread thread = new Thread(() -> {
     *     System.out.println(Thread.currentThread().getName());
     * });
     *
     * thread.run();
     *
     * Output:
     * main
     *
     * Why?
     *
     * We called run() directly from the main thread.
     *
     * Now:
     *
     * thread.start();
     *
     * Output:
     * Thread-0
     *
     * The task executes on the newly started thread.
     *
     * IMPORTANT:
     *
     * Never call run() when your intention is to start
     * a separate thread.
     *
     *
     * ============================================================
     * 8. CAN WE CALL START() TWICE?
     * ============================================================
     *
     * No.
     *
     * A Thread object can be started only once.
     *
     * Example:
     *
     * Thread thread = new Thread(() -> {
     *     System.out.println("Running");
     * });
     *
     * thread.start();
     *
     * thread.start();
     *
     * The second call throws:
     *
     * IllegalThreadStateException
     *
     * If you need to execute the same task again, create
     * another Thread object or use an appropriate executor.
     *
     *
     * ============================================================
     * 9. USING ANONYMOUS INNER CLASS
     * ============================================================
     *
     * Before lambda expressions became available, Runnable
     * was commonly supplied using an anonymous inner class.
     *
     * Example:
     *
     * Thread thread = new Thread(new Runnable() {
     *
     *     @Override
     *     public void run() {
     *
     *         System.out.println("Running in another thread");
     *     }
     * });
     *
     * thread.start();
     *
     * Modern Java usually uses a lambda for this simple case.
     *
     *
     * ============================================================
     * 10. WHAT IS CALLABLE?
     * ============================================================
     *
     * Callable is an interface in java.util.concurrent.
     *
     * It represents a task that returns a result and can
     * throw checked exceptions.
     *
     * Its method is:
     *
     * V call() throws Exception;
     *
     * Compare:
     *
     * Runnable:
     * - run() returns void.
     * - Cannot declare checked exceptions in its method signature.
     *
     * Callable:
     * - call() returns a value.
     * - Can declare checked exceptions.
     *
     * Example:
     *
     * Callable<Integer> task = () -> {
     *     return 10 + 20;
     * };
     *
     * IMPORTANT:
     *
     * Callable cannot be passed directly to the Thread constructor.
     *
     * It is normally submitted to an ExecutorService.
     *
     * Example:
     *
     * ExecutorService executor =
     *     Executors.newSingleThreadExecutor();
     *
     * Future<Integer> result = executor.submit(task);
     *
     * System.out.println(result.get());
     *
     * executor.shutdown();
     *
     * Callable and Future will be studied in the
     * Executor and asynchronous programming sections.
     *
     *
     * ============================================================
     * 11. WHAT IS THE MAIN THREAD?
     * ============================================================
     *
     * The main thread is the initial thread that executes
     * the main() method of a Java application.
     *
     * Example:
     *
     * public static void main(String[] args) {
     *
     *     System.out.println(
     *         Thread.currentThread().getName()
     *     );
     * }
     *
     * Typical output:
     *
     * main
     *
     * currentThread() returns the Thread object representing
     * the currently executing thread.
     *
     * getName() returns its name.
     *
     *
     * ============================================================
     * 12. NAMING THREADS
     * ============================================================
     *
     * Every Thread has a name.
     *
     * By default, Java assigns names such as:
     *
     * Thread-0
     * Thread-1
     * Thread-2
     *
     * You can supply a custom name.
     *
     * Example:
     *
     * Thread thread = new Thread(
     *     () -> System.out.println("Working"),
     *     "File-Processor"
     * );
     *
     * thread.start();
     *
     * Or:
     *
     * thread.setName("Database-Worker");
     *
     * Get the name:
     *
     * thread.getName();
     *
     * Meaningful thread names make debugging easier.
     *
     *
     * ============================================================
     * 13. THREAD ID
     * ============================================================
     *
     * Every Thread has an identifier.
     *
     * Example:
     *
     * Thread thread = Thread.currentThread();
     *
     * System.out.println(thread.threadId());
     *
     * threadId() was introduced in Java 19.
     *
     * It returns the thread's unique ID.
     *
     * The older getId() method was deprecated in Java 19.
     *
     * Thread IDs are not necessarily equal to operating-system
     * thread IDs.
     *
     *
     * ============================================================
     * 14. CURRENTTHREAD()
     * ============================================================
     *
     * Thread.currentThread() returns the Thread object
     * representing the currently executing thread.
     *
     * Example:
     *
     * System.out.println(
     *     Thread.currentThread().getName()
     * );
     *
     * Useful for identifying which thread is executing code.
     *
     *
     * ============================================================
     * 15. WHAT IS THE THREAD LIFECYCLE?
     * ============================================================
     *
     * A thread passes through different states during
     * its lifetime.
     *
     * Java defines six Thread.State values:
     *
     * 1. NEW
     * 2. RUNNABLE
     * 3. BLOCKED
     * 4. WAITING
     * 5. TIMED_WAITING
     * 6. TERMINATED
     *
     * These are represented by the nested enum:
     *
     * Thread.State
     *
     *
     * ============================================================
     * 16. NEW STATE
     * ============================================================
     *
     * A Thread object is in NEW state after construction
     * and before start() is called.
     *
     * Example:
     *
     * Thread thread = new Thread(() -> {
     *     System.out.println("Running");
     * });
     *
     * System.out.println(thread.getState());
     *
     * Output:
     * NEW
     *
     * The thread object exists, but its execution has not started.
     *
     *
     * ============================================================
     * 17. RUNNABLE STATE
     * ============================================================
     *
     * After start() is called, the thread enters RUNNABLE state.
     *
     * Java's RUNNABLE state includes:
     *
     * - Ready to run.
     * - Currently running on a CPU.
     *
     * Example:
     *
     * thread.start();
     *
     * System.out.println(thread.getState());
     *
     * It may report RUNNABLE, but the exact state depends
     * on timing.
     *
     * IMPORTANT:
     *
     * Java does not expose a separate RUNNING state.
     *
     * A thread that is actually executing CPU instructions
     * is still reported as RUNNABLE.
     *
     *
     * ============================================================
     * 18. BLOCKED STATE
     * ============================================================
     *
     * A thread enters BLOCKED state when it is waiting to
     * acquire an intrinsic monitor lock.
     *
     * Example:
     *
     * synchronized (lock) {
     *     // Critical section
     * }
     *
     * If another thread already owns the same monitor,
     * this thread may enter BLOCKED state.
     *
     * It remains blocked until it can acquire the monitor.
     *
     *
     * ============================================================
     * 19. WAITING STATE
     * ============================================================
     *
     * A thread enters WAITING state when it waits indefinitely
     * for another thread to perform a particular action.
     *
     * Common examples:
     *
     * - Object.wait()
     * - Thread.join() without a timeout
     * - LockSupport.park()
     *
     * Example:
     *
     * synchronized (lock) {
     *     lock.wait();
     * }
     *
     * The thread waits until it is notified, interrupted,
     * or otherwise awakened.
     *
     * Object.wait() releases the monitor while waiting.
     *
     *
     * ============================================================
     * 20. TIMED_WAITING STATE
     * ============================================================
     *
     * A thread enters TIMED_WAITING when it waits for
     * a specified period.
     *
     * Common examples:
     *
     * Thread.sleep(1000);
     *
     * Object.wait(1000);
     *
     * Thread.join(1000);
     *
     * LockSupport.parkNanos(...);
     *
     * After the timeout, the thread becomes eligible to
     * continue, subject to scheduling and other conditions.
     *
     *
     * ============================================================
     * 21. TERMINATED STATE
     * ============================================================
     *
     * A thread enters TERMINATED state after its run()
     * method completes or ends because of an uncaught exception.
     *
     * Example:
     *
     * Thread thread = new Thread(() -> {
     *     System.out.println("Task completed");
     * });
     *
     * thread.start();
     *
     * After the task finishes:
     *
     * thread.getState();
     *
     * It returns TERMINATED.
     *
     * A terminated Thread cannot be restarted.
     *
     *
     * ============================================================
     * 22. THREAD LIFECYCLE SUMMARY
     * ============================================================
     *
     * NEW
     *  |
     *  | start()
     *  v
     * RUNNABLE
     *  |
     *  +---- Waiting for monitor lock ----> BLOCKED
     *  |
     *  +---- Waiting indefinitely --------> WAITING
     *  |
     *  +---- Waiting with timeout --------> TIMED_WAITING
     *  |
     *  +---- run() completes -------------> TERMINATED
     *
     * BLOCKED, WAITING and TIMED_WAITING threads can return
     * to RUNNABLE when the relevant conditions are satisfied.
     *
     * This is a conceptual lifecycle representation.
     *
     *
     * ============================================================
     * 23. GETSTATE()
     * ============================================================
     *
     * getState() returns the current state of a Thread.
     *
     * Example:
     *
     * Thread thread = new Thread(() -> {
     *     System.out.println("Working");
     * });
     *
     * System.out.println(thread.getState());
     * // NEW
     *
     * thread.start();
     *
     * System.out.println(thread.getState());
     *
     * The second result depends on timing.
     *
     * IMPORTANT:
     *
     * Thread state is mainly useful for monitoring and debugging.
     *
     * Do not use getState() as a synchronization mechanism.
     *
     *
     * ============================================================
     * 24. SLEEP()
     * ============================================================
     *
     * Thread.sleep() pauses the currently executing thread
     * for at least the requested duration, subject to
     * scheduling and timer precision.
     *
     * Example:
     *
     * try {
     *
     *     System.out.println("Before sleep");
     *
     *     Thread.sleep(2000);
     *
     *     System.out.println("After sleep");
     *
     * } catch (InterruptedException e) {
     *     Thread.currentThread().interrupt();
     * }
     *
     * The thread enters TIMED_WAITING during the sleep.
     *
     * IMPORTANT:
     *
     * 1. sleep() is a static method of Thread.
     *
     * 2. It pauses the current thread.
     *
     * 3. It does not release intrinsic locks already held
     *    by that thread.
     *
     * 4. It can throw InterruptedException.
     *
     * 5. It does not guarantee exact wake-up time.
     *
     *
     * ============================================================
     * 25. SLEEP() VS WAIT()
     * ============================================================
     *
     * sleep():
     *
     * - Method of Thread.
     * - Does not release intrinsic monitor locks.
     * - Used to pause execution for a duration.
     * - Can be called without owning an object monitor.
     *
     * wait():
     *
     * - Method of Object.
     * - Must be called while owning that object's monitor.
     * - Releases that object's monitor while waiting.
     * - Used for inter-thread coordination.
     *
     * Both can throw InterruptedException.
     *
     * We will study wait(), notify() and notifyAll()
     * in the inter-thread communication section.
     *
     *
     * ============================================================
     * 26. JOIN()
     * ============================================================
     *
     * join() makes the current thread wait until another
     * thread completes.
     *
     * Example:
     *
     * Thread worker = new Thread(() -> {
     *
     *     System.out.println("Worker started");
     *
     *     System.out.println("Worker completed");
     * });
     *
     * worker.start();
     *
     * try {
     *     worker.join();
     * } catch (InterruptedException e) {
     *     Thread.currentThread().interrupt();
     * }
     *
     * System.out.println("Main continues");
     *
     * Output:
     *
     * Worker started
     * Worker completed
     * Main continues
     *
     * The main thread waits for worker to finish.
     *
     *
     * ============================================================
     * 27. JOIN() OVERLOADS
     * ============================================================
     *
     * Java provides:
     *
     * join()
     *
     * join(long millis)
     *
     * join(long millis, int nanos)
     *
     * join():
     * Waits until the target thread terminates.
     *
     * join(long millis):
     * Waits for at most the specified number of milliseconds.
     *
     * join(long millis, int nanos):
     * Supports a more precise timeout.
     *
     * The timed join methods may return even if the target
     * thread is still running.
     *
     * IMPORTANT:
     *
     * join() throws InterruptedException.
     *
     *
     * ============================================================
     * 28. YIELD()
     * ============================================================
     *
     * Thread.yield() is a static method.
     *
     * It hints to the scheduler that the current thread
     * is willing to yield its current use of the CPU.
     *
     * Example:
     *
     * Thread.yield();
     *
     * IMPORTANT:
     *
     * - It is only a scheduling hint.
     * - The scheduler may ignore it.
     * - It does not guarantee another thread will execute.
     * - It should not be used for correctness or synchronization.
     *
     *
     * ============================================================
     * 29. INTERRUPTING A THREAD
     * ============================================================
     *
     * interrupt() requests interruption of a thread.
     *
     * It does not forcibly terminate the thread.
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
     *     System.out.println("Stopping cooperatively");
     * });
     *
     * worker.start();
     *
     * worker.interrupt();
     *
     * The worker checks its interrupt status and can stop
     * cooperatively.
     *
     * Detailed interruption handling will be covered in
     * Thread Control and Management.
     *
     *
     * ============================================================
     * 30. DAEMON VS NON-DAEMON THREADS
     * ============================================================
     *
     * Java has:
     *
     * - Daemon threads.
     * - Non-daemon threads.
     *
     * A daemon thread performs background support work.
     *
     * A non-daemon thread is a normal application thread.
     *
     * The JVM normally continues running while at least
     * one non-daemon thread is alive.
     *
     * Once all non-daemon threads have terminated, the JVM
     * may exit even if daemon threads are still running.
     *
     * Example:
     *
     * Thread worker = new Thread(() -> {
     *     System.out.println("Background work");
     * });
     *
     * worker.setDaemon(true);
     * worker.start();
     *
     * IMPORTANT:
     *
     * setDaemon() must be called before start().
     *
     * Daemon threads should not be relied on to complete
     * critical work during JVM shutdown.
     *
     * More details are covered in Thread Control and Management.
     *
     *
     * ============================================================
     * 31. THREAD PRIORITY
     * ============================================================
     *
     * Java provides priorities from:
     *
     * Thread.MIN_PRIORITY = 1
     * Thread.NORM_PRIORITY = 5
     * Thread.MAX_PRIORITY = 10
     *
     * Example:
     *
     * Thread thread = new Thread(() -> {
     *     System.out.println("Working");
     * });
     *
     * thread.setPriority(Thread.MAX_PRIORITY);
     *
     * thread.start();
     *
     * IMPORTANT:
     *
     * Thread priority is a scheduling hint.
     *
     * It does not guarantee that a higher-priority thread
     * executes first.
     *
     * Do not build application correctness around priorities.
     *
     *
     * ============================================================
     * 32. COMMON THREAD METHODS
     * ============================================================
     *
     * start()
     * run()
     * sleep()
     * join()
     * yield()
     * interrupt()
     * isInterrupted()
     * interrupted()
     * isAlive()
     * currentThread()
     * getName()
     * setName()
     * getState()
     * getPriority()
     * setPriority()
     * isDaemon()
     * setDaemon()
     * threadId()
     *
     * Note:
     *
     * Some older Thread methods, such as stop(), suspend()
     * and resume(), are deprecated because they are unsafe.
     *
     *
     * ============================================================
     * 33. COMMON MISTAKES
     * ============================================================
     *
     * 1. Calling run() instead of start().
     *
     *    run() is an ordinary method call.
     *
     * 2. Starting the same Thread object twice.
     *
     *    This throws IllegalThreadStateException.
     *
     * 3. Assuming thread execution order is guaranteed.
     *
     * 4. Assuming sleep() releases a lock.
     *
     *    It does not.
     *
     * 5. Assuming sleep(1000) means the thread wakes up
     *    at exactly 1000 milliseconds.
     *
     * 6. Using yield() to guarantee scheduling order.
     *
     * 7. Assuming Java has a separate RUNNING state.
     *
     *    Java reports executing threads as RUNNABLE.
     *
     * 8. Using getState() to coordinate threads.
     *
     * 9. Assuming interrupt() forcibly kills a thread.
     *
     * 10. Forgetting to handle InterruptedException.
     *
     *
     * ============================================================
     * 34. INTERVIEW REVISION
     * ============================================================
     *
     * Q1. How do you create a thread in Java?
     *
     * Q2. What is the difference between Thread and Runnable?
     *
     * Q3. Why is implementing Runnable generally preferred?
     *
     * Q4. What is the difference between start() and run()?
     *
     * Q5. Can a Thread object be started twice?
     *
     * Q6. What is Callable?
     *
     * Q7. What is the difference between Runnable and Callable?
     *
     * Q8. What are the six states of a Java thread?
     *
     * Q9. Why does Java not have a separate RUNNING state?
     *
     * Q10. What is the difference between BLOCKED and WAITING?
     *
     * Q11. What is the difference between sleep() and wait()?
     *
     * Q12. What is the purpose of join()?
     *
     * Q13. What does yield() do?
     *
     * Q14. Does interrupt() terminate a thread?
     *
     * Q15. What is the difference between daemon and non-daemon
     *      threads?
     *
     * Q16. Does thread priority guarantee execution order?
     *
     *
     * ============================================================
     * 35. FINAL TAKEAWAY
     * ============================================================
     *
     * Thread:
     * Represents a thread of execution.
     *
     * Runnable:
     * Represents a task that returns no result.
     *
     * Callable:
     * Represents a task that returns a result and can throw
     * checked exceptions.
     *
     * start():
     * Starts a new thread of execution.
     *
     * run():
     * Contains the task logic.
     *
     * sleep():
     * Pauses the current thread for a duration.
     *
     * join():
     * Waits for another thread to terminate.
     *
     * interrupt():
     * Requests cooperative interruption.
     *
     * Thread.State:
     * Represents the six official Java thread states.
     *
     * Remember:
     *
     * Creating a Thread object and starting a thread
     * are two different things.
     *
     * Calling start() begins a new execution path.
     *
     * This distinction is the foundation of Java
     * thread creation and lifecycle.
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Thread Creation and Lifecycle , Happy Learning ..!");
    }
}
