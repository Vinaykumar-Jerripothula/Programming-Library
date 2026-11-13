
public class DeadlocksPerformanceAndBestPractices {

    /*
     * ============================================================
     * 1. WHY DO WE NEED CONCURRENCY BEST PRACTICES?
     * ============================================================
     *
     * Writing multithreaded code is relatively easy.
     *
     * Writing multithreaded code that is:
     *
     * - Correct.
     * - Thread-safe.
     * - Deadlock-free.
     * - Efficient.
     * - Scalable.
     * - Maintainable.
     *
     * is much harder.
     *
     * A program may work correctly during testing but fail
     * under heavy production traffic.
     *
     * This section focuses on understanding such problems
     * and preventing them.
     *
     *
     * ============================================================
     * 2. DEADLOCK
     * ============================================================
     *
     * Imagine two people:
     *
     * Person A holds a pen and waits for a notebook.
     *
     * Person B holds the notebook and waits for the pen.
     *
     * Neither person releases what they hold.
     *
     * Both wait forever.
     *
     * This is the idea behind a deadlock.
     *
     * A deadlock occurs when two or more threads are
     * permanently blocked because each is waiting for
     * a resource held by another thread in the group.
     *
     *
     * ============================================================
     * 3. DEADLOCK EXAMPLE
     * ============================================================
     *
     * Object lockA = new Object();
     * Object lockB = new Object();
     *
     * Thread first = new Thread(() -> {
     *
     *     synchronized (lockA) {
     *
     *         System.out.println("Thread 1 acquired A");
     *
     *         synchronized (lockB) {
     *
     *             System.out.println("Thread 1 acquired B");
     *         }
     *     }
     * });
     *
     * Thread second = new Thread(() -> {
     *
     *     synchronized (lockB) {
     *
     *         System.out.println("Thread 2 acquired B");
     *
     *         synchronized (lockA) {
     *
     *             System.out.println("Thread 2 acquired A");
     *         }
     *     }
     * });
     *
     * first.start();
     * second.start();
     *
     * Possible execution:
     *
     * Thread 1 acquires lockA.
     * Thread 2 acquires lockB.
     *
     * Thread 1 waits for lockB.
     * Thread 2 waits for lockA.
     *
     * Neither can proceed.
     *
     * DEADLOCK!
     *
     * IMPORTANT:
     *
     * This example demonstrates a possible deadlock.
     * It is not guaranteed to deadlock on every execution.
     *
     *
     * ============================================================
     * 4. FOUR NECESSARY CONDITIONS FOR DEADLOCK
     * ============================================================
     *
     * Deadlock theory identifies four necessary conditions.
     *
     * All four must hold simultaneously for a deadlock
     * to occur in the classic resource-allocation model.
     *
     * 1. MUTUAL EXCLUSION
     *
     * A resource can be held by only one thread at a time.
     *
     * 2. HOLD AND WAIT
     *
     * A thread holds one resource while waiting for another.
     *
     * 3. NO PREEMPTION
     *
     * A resource cannot simply be taken away from its owner.
     *
     * 4. CIRCULAR WAIT
     *
     * A circular chain of threads exists, where each thread
     * waits for a resource held by the next thread.
     *
     * Remember:
     *
     * Mutual Exclusion.
     * Hold and Wait.
     * No Preemption.
     * Circular Wait.
     *
     *
     * ============================================================
     * 5. DEADLOCK PREVENTION
     * ============================================================
     *
     * We can prevent deadlocks by breaking at least
     * one of the four necessary conditions.
     *
     * Common strategies:
     *
     * 1. Acquire locks in a consistent global order.
     * 2. Avoid holding unnecessary locks while waiting.
     * 3. Use timed lock acquisition where appropriate.
     * 4. Keep critical sections small.
     * 5. Avoid nested locks when possible.
     *
     *
     * ============================================================
     * 6. CONSISTENT LOCK ORDERING
     * ============================================================
     *
     * One of the most effective deadlock-prevention techniques
     * is acquiring multiple locks in a consistent order.
     *
     * Example:
     *
     * Both threads must acquire:
     *
     * lockA first.
     * lockB second.
     *
     * synchronized (lockA) {
     *
     *     synchronized (lockB) {
     *
     *         // Critical section.
     *     }
     * }
     *
     * Even if two threads need both resources,
     * they follow the same acquisition order.
     *
     * This prevents the circular-wait condition
     * in this locking arrangement.
     *
     *
     * ============================================================
     * 7. TRYLOCK() TO AVOID INDEFINITE WAITING
     * ============================================================
     *
     * ReentrantLock provides tryLock().
     *
     * Unlike lock(), tryLock() can attempt to acquire
     * a lock without waiting indefinitely.
     *
     * Example:
     *
     * if (lock.tryLock()) {
     *
     *     try {
     *
     *         // Critical section.
     *
     *     } finally {
     *
     *         lock.unlock();
     *     }
     *
     * } else {
     *
     *     // Lock unavailable.
     * }
     *
     * A timed version is also available:
     *
     * lock.tryLock(2, TimeUnit.SECONDS);
     *
     * If the lock cannot be acquired within the timeout,
     * the thread can take another action.
     *
     * IMPORTANT:
     *
     * tryLock() does not automatically make an algorithm
     * deadlock-free.
     *
     * The application must handle failure and retry behavior
     * carefully.
     *
     *
     * ============================================================
     * 8. DEADLOCK DETECTION
     * ============================================================
     *
     * Deadlocks can be detected using JVM diagnostic tools.
     *
     * Common tools:
     *
     * - jstack
     * - jcmd
     * - Java Mission Control
     *
     * A thread dump may show:
     *
     * - Threads in BLOCKED state.
     * - Which monitor each thread owns.
     * - Which monitor each thread is waiting for.
     * - A JVM-detected Java-level deadlock.
     *
     * Example:
     *
     * jstack <pid>
     *
     * Or:
     *
     * jcmd <pid> Thread.print
     *
     * The output can reveal the threads involved
     * and their lock dependencies.
     *
     *
     * ============================================================
     * 9. THREADMXBEAN DEADLOCK DETECTION
     * ============================================================
     *
     * Java provides ThreadMXBean for thread monitoring.
     *
     * It can detect certain monitor and ownable-synchronizer
     * deadlocks.
     *
     * Example:
     *
     * ThreadMXBean bean =
     *     ManagementFactory.getThreadMXBean();
     *
     * long[] deadlockedThreads =
     *     bean.findDeadlockedThreads();
     *
     * if (deadlockedThreads != null) {
     *
     *     System.out.println("Deadlock detected");
     * }
     *
     * Related methods:
     *
     * findMonitorDeadlockedThreads():
     * Detects monitor-based deadlocks.
     *
     * findDeadlockedThreads():
     * Detects monitor and ownable synchronizer deadlocks.
     *
     *
     * ============================================================
     * 10. STARVATION
     * ============================================================
     *
     * Starvation occurs when a thread is repeatedly denied
     * the resources or execution time it needs to make progress.
     *
     * Example:
     *
     * A low-priority thread is continuously ignored
     * while higher-priority threads keep executing.
     *
     * Other causes include:
     *
     * - Unfair lock acquisition.
     * - A saturated thread pool.
     * - Long-running tasks occupying all workers.
     * - Tasks waiting indefinitely for limited resources.
     *
     * A starved thread may remain alive but make little
     * or no useful progress.
     *
     *
     * ============================================================
     * 11. STARVATION PREVENTION
     * ============================================================
     *
     * Possible approaches:
     *
     * 1. Use fair locks when fairness is important.
     * 2. Avoid unnecessarily long critical sections.
     * 3. Avoid excessively large or unbounded task queues.
     * 4. Separate unrelated workloads into suitable executors.
     * 5. Monitor task waiting times.
     * 6. Avoid relying on thread priorities for correctness.
     *
     * IMPORTANT:
     *
     * Fairness can reduce throughput in some workloads.
     *
     * Choose fairness based on application requirements.
     *
     *
     * ============================================================
     * 12. LIVELOCK
     * ============================================================
     *
     * Deadlock:
     *
     * Threads are blocked and cannot proceed.
     *
     * Livelock:
     *
     * Threads are active and keep responding to one another,
     * but they fail to make useful progress.
     *
     * Example:
     *
     * Two people meet in a narrow corridor.
     *
     * Person A moves left to give way.
     *
     * Person B also moves left.
     *
     * Both then move right.
     *
     * They repeatedly react to each other and still
     * cannot pass.
     *
     * This is a livelock-like situation.
     *
     *
     * ============================================================
     * 13. LIVELOCK PREVENTION
     * ============================================================
     *
     * Possible approaches:
     *
     * - Introduce randomized backoff.
     * - Use different retry timings.
     * - Avoid immediate synchronized retries.
     * - Establish a priority or coordination rule.
     * - Limit repeated retry attempts.
     *
     * Randomized backoff is useful in some distributed
     * and concurrent retry scenarios.
     *
     *
     * ============================================================
     * 14. DEADLOCK VS STARVATION VS LIVELOCK
     * ============================================================
     *
     * DEADLOCK:
     *
     * Threads wait for one another.
     * No participant can proceed.
     *
     * STARVATION:
     *
     * A thread repeatedly fails to obtain resources
     * or execution opportunities.
     *
     * LIVELOCK:
     *
     * Threads remain active but repeatedly interfere
     * with one another without useful progress.
     *
     *
     * ============================================================
     * 15. RACE CONDITION
     * ============================================================
     *
     * A race condition occurs when program correctness
     * depends on the timing or interleaving of concurrent
     * operations.
     *
     * Example:
     *
     * class Counter {
     *
     *     int count = 0;
     *
     *     void increment() {
     *
     *         count++;
     *     }
     * }
     *
     * If multiple threads call increment() concurrently,
     * updates may be lost.
     *
     * Why?
     *
     * count++ is a read-modify-write operation.
     *
     * It is not an atomic operation.
     *
     * Solutions include:
     *
     * - synchronized.
     * - ReentrantLock.
     * - AtomicInteger.
     * - Other suitable concurrent designs.
     *
     *
     * ============================================================
     * 16. THREAD SAFETY
     * ============================================================
     *
     * Thread safety means an object's behavior remains
     * correct when accessed concurrently according to
     * its documented contract.
     *
     * Common ways to achieve thread safety:
     *
     * 1. Immutability.
     * 2. Synchronization.
     * 3. Explicit locks.
     * 4. Atomic variables.
     * 5. Thread confinement.
     * 6. Concurrent collections.
     * 7. Message passing.
     *
     * IMPORTANT:
     *
     * Thread safety is a property of the design,
     * not merely the presence of synchronized keywords.
     *
     *
     * ============================================================
     * 17. CONTENTION
     * ============================================================
     *
     * Contention occurs when multiple threads compete
     * for the same resource.
     *
     * Example:
     *
     * Ten threads attempt to acquire the same lock.
     *
     * Only one can hold that lock at a time.
     *
     * Other threads must wait.
     *
     * High contention can reduce throughput and increase
     * response time.
     *
     * Ways to reduce contention:
     *
     * - Reduce critical-section size.
     * - Reduce shared mutable state.
     * - Use suitable concurrent data structures.
     * - Partition data.
     * - Use read/write locks where appropriate.
     * - Avoid unnecessary synchronization.
     *
     *
     * ============================================================
     * 18. LOCK GRANULARITY
     * ============================================================
     *
     * Lock granularity describes how much data or behavior
     * is protected by a lock.
     *
     * COARSE-GRAINED LOCKING:
     *
     * One lock protects a large section of functionality.
     *
     * Advantages:
     * Simpler synchronization.
     *
     * Disadvantages:
     * More contention and less concurrency.
     *
     *
     * FINE-GRAINED LOCKING:
     *
     * Different locks protect smaller independent sections.
     *
     * Advantages:
     * More concurrency may be possible.
     *
     * Disadvantages:
     * More complex lock management and deadlock risk.
     *
     * Choose an appropriate balance.
     *
     *
     * ============================================================
     * 19. FALSE SHARING
     * ============================================================
     *
     * Modern processors use cache lines when accessing memory.
     *
     * False sharing occurs when independent variables
     * used by different threads occupy the same cache line.
     *
     * Even though threads update different variables,
     * cache-coherence traffic can reduce performance.
     *
     * This is a hardware-level performance issue.
     *
     * It can matter in highly optimized concurrent code.
     *
     * Possible techniques:
     *
     * - Data layout changes.
     * - Padding where appropriate.
     * - Separating frequently updated state.
     * - Using suitable specialized structures.
     *
     * IMPORTANT:
     *
     * Do not optimize for false sharing without measurement.
     *
     *
     * ============================================================
     * 20. CONTEXT SWITCHING OVERHEAD
     * ============================================================
     *
     * A context switch occurs when the CPU stops executing
     * one thread and starts executing another.
     *
     * Context switching involves saving and restoring
     * execution state.
     *
     * Too many runnable threads can cause excessive
     * context switching.
     *
     * This can reduce useful CPU time.
     *
     * Thread pools help control platform-thread usage,
     * but an excessively large pool can still cause problems.
     *
     *
     * ============================================================
     * 21. CPU-BOUND VS I/O-BOUND TASKS
     * ============================================================
     *
     * CPU-BOUND:
     *
     * Tasks spend most of their time performing computation.
     *
     * Examples:
     *
     * - Mathematical calculations.
     * - Image processing.
     * - Data transformations.
     *
     * I/O-BOUND:
     *
     * Tasks spend significant time waiting for external
     * operations.
     *
     * Examples:
     *
     * - Database queries.
     * - Network calls.
     * - File operations.
     *
     * Pool-sizing decisions should consider the workload.
     *
     *
     * ============================================================
     * 22. PERFORMANCE METRICS
     * ============================================================
     *
     * Important measurements:
     *
     * THROUGHPUT:
     *
     * Number of tasks or requests completed per unit of time.
     *
     * LATENCY:
     *
     * Time taken to complete one operation.
     *
     * RESPONSE TIME:
     *
     * Time between receiving a request and returning
     * the response.
     *
     * CPU UTILIZATION:
     *
     * How much processor capacity is being used.
     *
     * QUEUE LENGTH:
     *
     * Number of tasks waiting for execution.
     *
     * CONTENTION:
     *
     * Time or effort spent competing for shared resources.
     *
     * THREAD COUNT:
     *
     * Number of active or existing threads.
     *
     * These measurements help identify bottlenecks.
     *
     *
     * ============================================================
     * 23. Amdahl's LAW
     * ============================================================
     *
     * Amdahl's Law explains the theoretical limit
     * of parallel speedup.
     *
     * Even if part of a program is parallelized,
     * the sequential portion limits the overall improvement.
     *
     * Formula:
     *
     * Speedup = 1 / ((1 - P) + (P / N))
     *
     * Where:
     *
     * P = Parallelizable fraction of the program.
     *
     * N = Number of processing units.
     *
     * Example:
     *
     * If 90% of a program can execute in parallel
     * and 10% must remain sequential:
     *
     * P = 0.9
     * N = 4
     *
     * Speedup =
     * 1 / (0.1 + 0.9 / 4)
     *
     * Speedup is approximately 3.08.
     *
     * Adding more processors cannot eliminate
     * the sequential portion.
     *
     *
     * ============================================================
     * 24. IMMUTABILITY AND CONCURRENCY
     * ============================================================
     *
     * Immutable objects cannot change their state
     * after construction.
     *
     * They are easier to share between threads because
     * their state cannot be modified after publication.
     *
     * Example:
     *
     * final class User {
     *
     *     private final String name;
     *
     *     User(String name) {
     *         this.name = name;
     *     }
     *
     *     public String getName() {
     *         return name;
     *     }
     * }
     *
     * This is a simple immutable class because String
     * is immutable and there is no state-changing method.
     *
     * Benefits:
     *
     * - Easier reasoning.
     * - Reduced synchronization needs.
     * - Safer sharing.
     *
     * IMPORTANT:
     *
     * A final reference does not automatically make
     * the referenced object immutable.
     *
     *
     * ============================================================
     * 25. THREAD CONFINEMENT
     * ============================================================
     *
     * Thread confinement means data is accessed by
     * only one thread.
     *
     * If mutable state is not shared between threads,
     * synchronization may not be necessary for that state.
     *
     * Examples:
     *
     * - Local variables.
     * - Objects owned by a single worker.
     * - ThreadLocal state, when used carefully.
     *
     * Thread confinement reduces shared-state complexity.
     *
     *
     * ============================================================
     * 26. MINIMIZE SHARED MUTABLE STATE
     * ============================================================
     *
     * Shared mutable state is a major source of
     * concurrency bugs.
     *
     * Whenever possible:
     *
     * - Prefer immutable objects.
     * - Use local variables.
     * - Pass data explicitly.
     * - Use message passing.
     * - Use concurrent collections when appropriate.
     *
     * The less mutable state threads share,
     * the easier the program is to reason about.
     *
     *
     * ============================================================
     * 27. MESSAGE PASSING
     * ============================================================
     *
     * Instead of allowing multiple threads to modify
     * the same object, threads can communicate through messages.
     *
     * Example:
     *
     * BlockingQueue<String> queue =
     *     new LinkedBlockingQueue<>();
     *
     * Producer:
     *
     * queue.put("Order received");
     *
     * Consumer:
     *
     * String message = queue.take();
     *
     * The queue coordinates communication between threads.
     *
     * Message passing can reduce the need for shared
     * mutable state.
     *
     *
     * ============================================================
     * 28. INTERRUPTIBLE TASKS
     * ============================================================
     *
     * Long-running tasks should respond appropriately
     * to interruption when cancellation is supported.
     *
     * Example:
     *
     * Runnable task = () -> {
     *
     *     while (!Thread.currentThread().isInterrupted()) {
     *
     *         // Perform a unit of work.
     *     }
     *
     *     // Cleanup if necessary.
     * };
     *
     * If a blocking method throws InterruptedException,
     * the interrupt status is normally cleared.
     *
     * Restore it when the current method cannot
     * fully handle the interruption:
     *
     * catch (InterruptedException e) {
     *
     *     Thread.currentThread().interrupt();
     * }
     *
     * Interruption is a cooperative cancellation mechanism.
     *
     *
     * ============================================================
     * 29. AVOID BLOCKING WHILE HOLDING LOCKS
     * ============================================================
     *
     * Avoid performing slow operations while holding
     * a shared lock whenever possible.
     *
     * Examples:
     *
     * - Network requests.
     * - Database calls.
     * - File operations.
     * - Waiting for another task.
     *
     * Why?
     *
     * Other threads may be unable to access the protected
     * resource during the entire wait.
     *
     * Better approach:
     *
     * 1. Acquire the lock.
     * 2. Read or update the required shared state.
     * 3. Release the lock.
     * 4. Perform slow independent work outside the lock.
     *
     * Ensure the resulting design remains correct.
     *
     *
     * ============================================================
     * 30. AVOID THREAD POOL STARVATION
     * ============================================================
     *
     * Thread pool starvation can occur when all worker
     * threads are occupied by tasks waiting for other
     * tasks submitted to the same saturated pool.
     *
     * Example:
     *
     * A fixed pool has two workers.
     *
     * Both workers execute parent tasks.
     *
     * Each parent task submits a child task to the same pool
     * and blocks waiting for its result.
     *
     * The child tasks remain queued because no worker
     * is available.
     *
     * The parent tasks never finish.
     *
     * This can result in thread-starvation deadlock.
     *
     * Prevention:
     *
     * - Avoid blocking on dependent work in the same
     *   small saturated executor.
     * - Use asynchronous composition where appropriate.
     * - Separate workloads when needed.
     * - Design task dependencies carefully.
     *
     *
     * ============================================================
     * 31. THREADLOCAL CLEANUP
     * ============================================================
     *
     * ThreadLocal stores values associated with threads.
     *
     * In thread pools, worker threads are reused.
     *
     * If a task stores data in a ThreadLocal and does not
     * remove it, a later task may observe stale data.
     *
     * Example:
     *
     * try {
     *
     *     userContext.set(user);
     *
     *     // Execute task.
     *
     * } finally {
     *
     *     userContext.remove();
     * }
     *
     * Cleanup is especially important when using
     * reusable worker threads.
     *
     *
     * ============================================================
     * 32. SAFE PUBLICATION
     * ============================================================
     *
     * Safe publication means making an object available
     * to other threads in a way that guarantees they can
     * observe its properly initialized state.
     *
     * Common techniques include:
     *
     * - Static initialization.
     * - Volatile references.
     * - Synchronization.
     * - Final fields with proper construction.
     * - Thread-safe concurrent structures.
     *
     * Publishing an object without appropriate
     * synchronization can expose partially initialized state
     * in unsafe designs.
     *
     *
     * ============================================================
     * 33. PERFORMANCE OPTIMIZATION APPROACH
     * ============================================================
     *
     * Do not optimize concurrent code based only on guesses.
     *
     * Follow this process:
     *
     * 1. Establish a baseline.
     * 2. Measure throughput and latency.
     * 3. Identify the bottleneck.
     * 4. Inspect thread activity and lock contention.
     * 5. Change one important factor.
     * 6. Measure again.
     * 7. Verify correctness under concurrency.
     *
     * Correctness must not be sacrificed for speed.
     *
     *
     * ============================================================
     * 34. JAVA CONCURRENCY DIAGNOSTIC TOOLS
     * ============================================================
     *
     * jstack:
     *
     * Prints thread stacks for a JVM process.
     *
     * jcmd:
     *
     * Provides diagnostic commands for a running JVM.
     *
     * Java Flight Recorder:
     *
     * Records JVM and application events.
     *
     * Java Mission Control:
     *
     * Helps analyze recordings and runtime behavior.
     *
     * VisualVM:
     *
     * Provides monitoring and profiling capabilities.
     *
     * JMH:
     *
     * Java Microbenchmark Harness, used for reliable
     * JVM microbenchmarking.
     *
     * jcstress:
     *
     * OpenJDK testing harness for investigating
     * concurrency correctness and memory-model behavior.
     *
     *
     * ============================================================
     * 35. THREAD DUMP ANALYSIS
     * ============================================================
     *
     * A thread dump contains:
     *
     * - Thread names.
     * - Thread states.
     * - Stack traces.
     * - Lock ownership and waiting information.
     *
     * Common states:
     *
     * RUNNABLE:
     * Executing or otherwise runnable.
     *
     * BLOCKED:
     * Waiting to acquire a monitor lock.
     *
     * WAITING:
     * Waiting indefinitely for another action.
     *
     * TIMED_WAITING:
     * Waiting for a specified time.
     *
     * Thread dumps help diagnose:
     *
     * - Deadlocks.
     * - Lock contention.
     * - Thread pool starvation.
     * - Unexpected blocking.
     *
     *
     * ============================================================
     * 36. JVM CONCURRENCY AND GARBAGE COLLECTION
     * ============================================================
     *
     * Concurrent applications can create many temporary
     * objects and retain shared references.
     *
     * This can affect:
     *
     * - Allocation rate.
     * - Garbage collection frequency.
     * - Heap usage.
     * - Pause behavior.
     *
     * Performance investigation should consider both
     * thread behavior and memory behavior.
     *
     * Avoid object pooling by default.
     *
     * Object pooling can add complexity and may hurt
     * performance when allocation is already efficient.
     *
     *
     * ============================================================
     * 37. CONCURRENCY BEST PRACTICES
     * ============================================================
     *
     * 1. Prefer immutable data where practical.
     *
     * 2. Minimize shared mutable state.
     *
     * 3. Keep critical sections small.
     *
     * 4. Acquire multiple locks in a consistent order.
     *
     * 5. Always release explicit locks in finally blocks.
     *
     * 6. Use concurrent collections when appropriate.
     *
     * 7. Use atomic classes for suitable atomic updates.
     *
     * 8. Prefer high-level concurrency utilities
     *    over manually implementing low-level protocols.
     *
     * 9. Handle interruption correctly.
     *
     * 10. Shut down executors properly.
     *
     * 11. Use bounded queues when workload control is required.
     *
     * 12. Monitor thread pools and queue lengths.
     *
     * 13. Avoid blocking while holding locks.
     *
     * 14. Clean up ThreadLocal values in reusable workers.
     *
     * 15. Do not assume thread priority guarantees scheduling.
     *
     * 16. Measure before optimizing.
     *
     * 17. Test concurrent behavior under realistic load.
     *
     * 18. Choose virtual threads for suitable blocking workloads.
     *
     * 19. Limit access to scarce external resources separately.
     *
     * 20. Make task cancellation and failure handling explicit.
     *
     *
     * ============================================================
     * 38. CONCURRENCY IN JAVA BACKEND APPLICATIONS
     * ============================================================
     *
     * In a Spring Boot backend application, concurrency
     * appears in many places:
     *
     * - HTTP request processing.
     * - Database connection management.
     * - Background jobs.
     * - Scheduled tasks.
     * - Asynchronous service calls.
     * - Message consumers.
     * - File processing.
     * - Cache access.
     *
     * Important technologies and APIs:
     *
     * Java Threads.
     * ExecutorService.
     * ThreadPoolExecutor.
     * CompletableFuture.
     * ConcurrentHashMap.
     * BlockingQueue.
     * ReentrantLock.
     * Semaphore.
     * Virtual Threads.
     *
     * Understanding Java concurrency helps when configuring
     * Spring task executors, request handling and background work.
     *
     *
     * ============================================================
     * 39. FINAL COMPARISON OF CONCURRENCY PROBLEMS
     * ============================================================
     *
     * Race Condition:
     *
     * Incorrect behavior due to uncontrolled interleaving
     * of concurrent operations.
     *
     * Deadlock:
     *
     * Threads wait permanently for resources held
     * by one another.
     *
     * Starvation:
     *
     * A thread repeatedly fails to obtain resources
     * or execution opportunities.
     *
     * Livelock:
     *
     * Threads remain active but fail to make useful progress.
     *
     * Contention:
     *
     * Threads compete for shared resources.
     *
     * False Sharing:
     *
     * Independent variables share a cache line and
     * cause unnecessary cache-coherence traffic.
     *
     * Thread Pool Starvation:
     *
     * Tasks occupy all workers while waiting for work
     * that cannot run because no workers are available.
     *
     *
     * ============================================================
     * 40. INTERVIEW REVISION
     * ============================================================
     *
     * Q1. What is deadlock?
     *
     * Q2. What are the four necessary conditions for deadlock?
     *
     * Q3. How can deadlocks be prevented?
     *
     * Q4. How can we detect deadlocks in Java?
     *
     * Q5. What is starvation?
     *
     * Q6. What is livelock?
     *
     * Q7. Difference between deadlock, starvation and livelock?
     *
     * Q8. What is race condition?
     *
     * Q9. What is thread safety?
     *
     * Q10. What is lock contention?
     *
     * Q11. What is lock granularity?
     *
     * Q12. What is false sharing?
     *
     * Q13. What is context switching?
     *
     * Q14. What is Amdahl's Law?
     *
     * Q15. What is thread pool starvation?
     *
     * Q16. Why should we avoid blocking while holding locks?
     *
     * Q17. What is thread confinement?
     *
     * Q18. Why is immutability useful in concurrent applications?
     *
     * Q19. What is safe publication?
     *
     * Q20. How do you analyze a thread dump?
     *
     * Q21. What tools help diagnose Java concurrency issues?
     *
     * Q22. How do you improve concurrent application performance?
     *
     *
     * ============================================================
     * 41. FINAL TAKEAWAY
     * ============================================================
     *
     * Deadlock:
     * Threads wait for one another and cannot proceed.
     *
     * Starvation:
     * A thread cannot obtain sufficient execution opportunities.
     *
     * Livelock:
     * Threads remain active without useful progress.
     *
     * Race condition:
     * Concurrent timing affects correctness.
     *
     * Contention:
     * Threads compete for shared resources.
     *
     * Amdahl's Law:
     * Sequential work limits parallel speedup.
     *
     * Thread dump:
     * Helps inspect thread states, stacks and lock ownership.
     *
     * JFR:
     * Helps investigate runtime and performance behavior.
     *
     * JMH:
     * Helps create reliable JVM microbenchmarks.
     *
     * jcstress:
     * Helps test concurrency correctness.
     *
     * Remember:
     *
     * A good concurrent application is not simply one
     * that uses many threads.
     *
     * It is one that makes correct progress, uses resources
     * efficiently, handles failures safely and remains
     * predictable under load.
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Deadlocks, Performance and Best Practices , Happy Learning ..!");
    }
}
