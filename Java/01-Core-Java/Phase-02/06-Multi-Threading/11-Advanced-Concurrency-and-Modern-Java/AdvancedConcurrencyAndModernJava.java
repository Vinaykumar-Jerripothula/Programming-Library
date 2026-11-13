
public class AdvancedConcurrencyAndModernJava {

    /*
     * ============================================================
     * 1. WHAT IS ADVANCED CONCURRENCY?
     * ============================================================
     *
     * Basic multithreading teaches us how to create threads
     * and execute tasks concurrently.
     *
     * Advanced concurrency focuses on coordinating multiple
     * threads and building reliable concurrent applications.
     *
     * Important areas:
     *
     * - Fork/Join Framework.
     * - CountDownLatch.
     * - CyclicBarrier.
     * - Phaser.
     * - Semaphore.
     * - Exchanger.
     * - CompletableFuture.
     * - Virtual Threads.
     * - Structured Concurrency.
     * - Scoped Values.
     *
     * These tools solve different concurrency problems.
     *
     *
     * ============================================================
     * 2. COUNTDOWNLATCH
     * ============================================================
     *
     * Imagine an application that needs to perform
     * three independent initialization tasks:
     *
     * 1. Load configuration.
     * 2. Connect to the database.
     * 3. Load application data.
     *
     * The application should continue only after all
     * three tasks finish.
     *
     * CountDownLatch solves this problem.
     *
     * CountDownLatch is a synchronization aid that allows
     * one or more threads to wait until a counter reaches zero.
     *
     * Example:
     *
     * CountDownLatch latch = new CountDownLatch(3);
     *
     * ExecutorService executor =
     *     Executors.newFixedThreadPool(3);
     *
     * for (int i = 1; i <= 3; i++) {
     *
     *     executor.submit(() -> {
     *
     *         try {
     *             System.out.println("Task completed");
     *
     *         } finally {
     *             latch.countDown();
     *         }
     *     });
     * }
     *
     * latch.await();
     *
     * System.out.println("All tasks completed");
     *
     * executor.shutdown();
     *
     * Important methods:
     *
     * countDown():
     * Decreases the counter by one.
     *
     * await():
     * Waits until the counter reaches zero.
     *
     * getCount():
     * Returns the current count.
     *
     * IMPORTANT:
     *
     * CountDownLatch is one-time use.
     *
     * Once its count reaches zero, it cannot be reset.
     *
     *
     * ============================================================
     * 3. CYCLICBARRIER
     * ============================================================
     *
     * Imagine four players participating in a game.
     *
     * Each player must finish the current round before
     * the next round begins.
     *
     * CyclicBarrier allows a group of threads to wait
     * for one another at a common synchronization point.
     *
     * Example:
     *
     * CyclicBarrier barrier = new CyclicBarrier(3);
     *
     * Runnable task = () -> {
     *
     *     try {
     *
     *         System.out.println(
     *             Thread.currentThread().getName()
     *             + " completed its work"
     *         );
     *
     *         barrier.await();
     *
     *         System.out.println("Next phase started");
     *
     *     } catch (Exception e) {
     *         e.printStackTrace();
     *     }
     * };
     *
     * ExecutorService executor =
     *     Executors.newFixedThreadPool(3);
     *
     * executor.submit(task);
     * executor.submit(task);
     * executor.submit(task);
     *
     * executor.shutdown();
     *
     * Important methods:
     *
     * await():
     * Waits until the required number of parties arrive.
     *
     * reset():
     * Resets the barrier to its initial state.
     *
     * getNumberWaiting():
     * Returns the approximate number of waiting parties.
     *
     * isBroken():
     * Checks whether the barrier is in a broken state.
     *
     * IMPORTANT:
     *
     * A CyclicBarrier can be reused after its parties
     * successfully pass through it.
     *
     *
     * ============================================================
     * 4. COUNTDOWNLATCH VS CYCLICBARRIER
     * ============================================================
     *
     * CountDownLatch:
     *
     * - Waits for a counter to reach zero.
     * - Commonly used when tasks signal completion.
     * - Cannot be reset.
     * - One-time synchronization.
     *
     * CyclicBarrier:
     *
     * - Waits for a fixed group of parties to arrive.
     * - Commonly used for phase-based coordination.
     * - Can be reused.
     *
     * Remember:
     *
     * CountDownLatch:
     * Wait until the required events are completed.
     *
     * CyclicBarrier:
     * Wait until all participating threads reach the barrier.
     *
     *
     * ============================================================
     * 5. PHASER
     * ============================================================
     *
     * Phaser is a more flexible synchronization aid.
     *
     * It coordinates threads across multiple phases.
     *
     * Unlike CountDownLatch and CyclicBarrier, the number
     * of registered parties can change dynamically.
     *
     * Example:
     *
     * Phaser phaser = new Phaser(3);
     *
     * Runnable task = () -> {
     *
     *     System.out.println("Phase 1 work");
     *
     *     phaser.arriveAndAwaitAdvance();
     *
     *     System.out.println("Phase 2 work");
     *
     *     phaser.arriveAndAwaitAdvance();
     * };
     *
     * ExecutorService executor =
     *     Executors.newFixedThreadPool(3);
     *
     * executor.submit(task);
     * executor.submit(task);
     * executor.submit(task);
     *
     * executor.shutdown();
     *
     * Important methods:
     *
     * register():
     * Adds a new registered party.
     *
     * arrive():
     * Signals arrival without waiting for the phase to advance.
     *
     * arriveAndAwaitAdvance():
     * Signals arrival and waits for the next phase.
     *
     * arriveAndDeregister():
     * Signals arrival and removes the party.
     *
     * getPhase():
     * Returns the current phase number.
     *
     * IMPORTANT:
     *
     * Phaser is useful when the number of participants
     * or synchronization phases changes dynamically.
     *
     *
     * ============================================================
     * 6. SEMAPHORE
     * ============================================================
     *
     * Imagine a parking area with five parking spaces.
     *
     * Only five cars can park at a time.
     *
     * When a car leaves, another car can enter.
     *
     * Semaphore solves this type of resource-limiting problem.
     *
     * A Semaphore maintains a number of permits.
     *
     * Example:
     *
     * Semaphore semaphore = new Semaphore(5);
     *
     * semaphore.acquire();
     *
     * try {
     *
     *     System.out.println("Using shared resource");
     *
     * } finally {
     *
     *     semaphore.release();
     * }
     *
     * Important methods:
     *
     * acquire():
     * Acquires a permit, waiting if necessary.
     *
     * release():
     * Returns a permit.
     *
     * tryAcquire():
     * Attempts to acquire without waiting indefinitely.
     *
     * availablePermits():
     * Returns the current number of available permits.
     *
     * IMPORTANT:
     *
     * Semaphore is commonly used to limit concurrent
     * access to a resource.
     *
     *
     * ============================================================
     * 7. BINARY SEMAPHORE VS MUTEX
     * ============================================================
     *
     * A binary semaphore has one permit.
     *
     * It can be used to restrict access to one resource
     * at a time.
     *
     * However, a binary semaphore is not identical
     * to a mutex.
     *
     * Mutex:
     *
     * - Provides mutual exclusion.
     * - Has ownership semantics in common mutex designs.
     * - The thread that acquires it is expected to release it.
     *
     * Semaphore:
     *
     * - Controls access using permits.
     * - Does not enforce thread ownership of permits.
     * - A different thread can release a permit.
     *
     *
     * ============================================================
     * 8. EXCHANGER
     * ============================================================
     *
     * Exchanger allows two threads to exchange objects
     * at a synchronization point.
     *
     * Example:
     *
     * Exchanger<String> exchanger = new Exchanger<>();
     *
     * Thread first = new Thread(() -> {
     *
     *     try {
     *
     *         String received =
     *             exchanger.exchange("Data from A");
     *
     *         System.out.println(received);
     *
     *     } catch (InterruptedException e) {
     *
     *         Thread.currentThread().interrupt();
     *     }
     * });
     *
     * Thread second = new Thread(() -> {
     *
     *     try {
     *
     *         String received =
     *             exchanger.exchange("Data from B");
     *
     *         System.out.println(received);
     *
     *     } catch (InterruptedException e) {
     *
     *         Thread.currentThread().interrupt();
     *     }
     * });
     *
     * first.start();
     * second.start();
     *
     * Each thread receives the object supplied by the other.
     *
     * Common use:
     *
     * - Producer-consumer designs.
     * - Exchanging buffers.
     * - Passing data between two threads.
     *
     *
     * ============================================================
     * 9. FORK/JOIN FRAMEWORK
     * ============================================================
     *
     * Imagine calculating the sum of one million numbers.
     *
     * Instead of one thread processing every number,
     * we can divide the work into smaller tasks.
     *
     * Each task can divide its work further.
     *
     * Finally, the partial results are combined.
     *
     * This is the divide-and-conquer approach.
     *
     * Java provides the Fork/Join Framework for this style
     * of parallel task execution.
     *
     * Important classes:
     *
     * ForkJoinPool
     * ForkJoinTask
     * RecursiveTask
     * RecursiveAction
     *
     *
     * ============================================================
     * 10. FORKJOINPOOL
     * ============================================================
     *
     * ForkJoinPool is a specialized ExecutorService
     * designed for fork/join tasks.
     *
     * It uses work-stealing.
     *
     * Work-stealing:
     *
     * When a worker has no work, it can steal tasks
     * from another worker's queue.
     *
     * This helps distribute tasks among workers.
     *
     * Example:
     *
     * ForkJoinPool pool = new ForkJoinPool();
     *
     * pool.submit(new MyTask());
     *
     * pool.shutdown();
     *
     * ForkJoinPool is useful for suitable CPU-intensive
     * divide-and-conquer workloads.
     *
     *
     * ============================================================
     * 11. RECURSIVETASK
     * ============================================================
     *
     * RecursiveTask<V> represents a fork/join task
     * that returns a result.
     *
     * It is used when a computation can be divided
     * into smaller computations whose results are combined.
     *
     * Example:
     *
     * class SumTask extends RecursiveTask<Long> {
     *
     *     private final int[] numbers;
     *     private final int start;
     *     private final int end;
     *
     *     SumTask(int[] numbers, int start, int end) {
     *         this.numbers = numbers;
     *         this.start = start;
     *         this.end = end;
     *     }
     *
     *     @Override
     *     protected Long compute() {
     *
     *         if (end - start <= 1000) {
     *
     *             long sum = 0;
     *
     *             for (int i = start; i < end; i++) {
     *                 sum += numbers[i];
     *             }
     *
     *             return sum;
     *         }
     *
     *         int middle = (start + end) / 2;
     *
     *         SumTask left =
     *             new SumTask(numbers, start, middle);
     *
     *         SumTask right =
     *             new SumTask(numbers, middle, end);
     *
     *         left.fork();
     *
     *         long rightResult = right.compute();
     *
     *         long leftResult = left.join();
     *
     *         return leftResult + rightResult;
     *     }
     * }
     *
     * Key methods:
     *
     * compute():
     * Defines the computation.
     *
     * fork():
     * Schedules a subtask for asynchronous execution.
     *
     * join():
     * Waits for and retrieves a subtask's result.
     *
     * invoke():
     * Executes a task and waits for its result.
     *
     *
     * ============================================================
     * 12. RECURSIVEACTION
     * ============================================================
     *
     * RecursiveAction represents a fork/join task
     * that does not return a result.
     *
     * It is similar to RecursiveTask, but its compute()
     * method returns void.
     *
     * Use:
     *
     * RecursiveTask:
     * When a result is required.
     *
     * RecursiveAction:
     * When the task performs an action without returning a result.
     *
     *
     * ============================================================
     * 13. FORK/JOIN VS EXECUTOR FRAMEWORK
     * ============================================================
     *
     * ExecutorService:
     *
     * - General-purpose task execution.
     * - Supports Runnable and Callable.
     * - Can use different queueing and scheduling strategies.
     *
     * ForkJoinPool:
     *
     * - Specialized for fork/join task structures.
     * - Uses work-stealing.
     * - Particularly useful for divide-and-conquer workloads.
     *
     * ForkJoinPool implements ExecutorService.
     *
     *
     * ============================================================
     * 14. COMPLETIONSTAGE
     * ============================================================
     *
     * CompletionStage represents a stage of an asynchronous
     * computation.
     *
     * It supports:
     *
     * - Dependent computations.
     * - Result transformations.
     * - Combining stages.
     * - Completion handling.
     *
     * CompletableFuture implements CompletionStage.
     *
     * Remember:
     *
     * CompletionStage defines the asynchronous stage API.
     *
     * CompletableFuture is a concrete implementation
     * that also implements Future.
     *
     *
     * ============================================================
     * 15. VIRTUAL THREADS
     * ============================================================
     *
     * Traditional Java threads are platform threads.
     *
     * They are generally backed by operating-system threads.
     *
     * Creating very large numbers of platform threads
     * can consume significant resources.
     *
     * Virtual threads are lightweight threads managed
     * by the Java runtime.
     *
     * They were introduced as a preview feature in Java 19
     * and finalized in Java 21.
     *
     * Example:
     *
     * Thread thread = Thread.startVirtualThread(() -> {
     *
     *     System.out.println(
     *         Thread.currentThread()
     *     );
     * });
     *
     * thread.join();
     *
     * Virtual threads make it practical to support
     * many concurrent blocking tasks.
     *
     *
     * ============================================================
     * 16. CREATING VIRTUAL THREADS
     * ============================================================
     *
     * Method 1: Thread.startVirtualThread()
     *
     * Thread.startVirtualThread(() -> {
     *
     *     System.out.println("Virtual thread");
     * });
     *
     *
     * Method 2: Thread.ofVirtual()
     *
     * Thread thread = Thread.ofVirtual()
     *     .name("My-Virtual-Thread")
     *     .start(() -> {
     *
     *         System.out.println("Running");
     *     });
     *
     *
     * Method 3: Virtual thread executor
     *
     * try (ExecutorService executor =
     *          Executors.newVirtualThreadPerTaskExecutor()) {
     *
     *     executor.submit(() -> {
     *         System.out.println("Task running");
     *     });
     * }
     *
     * The virtual-thread executor creates a new virtual
     * thread for each submitted task.
     *
     *
     * ============================================================
     * 17. PLATFORM THREAD VS VIRTUAL THREAD
     * ============================================================
     *
     * Platform thread:
     *
     * - Typically backed by an OS thread.
     * - Has higher resource overhead.
     * - Commonly managed through bounded thread pools.
     *
     * Virtual thread:
     *
     * - Managed by the JVM.
     * - Much lighter in resource usage.
     * - Can be created in very large numbers.
     * - Designed especially for blocking workloads.
     *
     * IMPORTANT:
     *
     * Virtual threads do not make CPU-bound calculations
     * execute faster than the available CPU capacity.
     *
     * They improve scalability for workloads that spend
     * significant time waiting.
     *
     *
     * ============================================================
     * 18. CARRIER THREADS AND SCHEDULING
     * ============================================================
     *
     * Virtual threads are scheduled onto platform threads.
     *
     * These platform threads are called carrier threads.
     *
     * A virtual thread can run on a carrier thread.
     *
     * When it blocks in a supported operation, the JVM
     * can suspend the virtual thread and use the carrier
     * for other virtual threads.
     *
     * When the virtual thread is ready to continue,
     * it can be scheduled again.
     *
     * This is why virtual threads can support many
     * concurrent blocking tasks with fewer OS threads.
     *
     *
     * ============================================================
     * 19. PINNING IN VIRTUAL THREADS
     * ============================================================
     *
     * Pinning occurs when a virtual thread cannot unmount
     * from its carrier thread during certain operations.
     *
     * Historically, entering a synchronized block or method
     * while blocking could cause pinning in some JDK versions.
     *
     * IMPORTANT VERSION NOTE:
     *
     * JDK 24 changed monitor implementation behavior
     * through JEP 491, allowing virtual threads to unmount
     * while holding Java monitors in the usual cases.
     *
     * Native and foreign-function interactions can still
     * cause pinning in relevant circumstances.
     *
     * Do not automatically replace every synchronized block
     * with ReentrantLock just because virtual threads are used.
     *
     *
     * ============================================================
     * 20. VIRTUAL THREAD BEST PRACTICES
     * ============================================================
     *
     * 1. Prefer one virtual thread per task.
     *
     * 2. Do not create a traditional pool merely to reuse
     *    virtual threads.
     *
     * 3. Limit scarce resources separately.
     *
     * 4. Use connection pools for database connections.
     *
     * 5. Use semaphores when access to a resource must
     *    be limited.
     *
     * 6. Avoid excessive ThreadLocal usage when a value
     *    can instead be passed explicitly.
     *
     * 7. Measure performance under realistic workloads.
     *
     * 8. Do not expect CPU-bound work to become faster.
     *
     * 9. Keep interruption and cancellation cooperative.
     *
     * 10. Avoid creating unbounded downstream database
     *     or network load.
     *
     *
     * ============================================================
     * 21. STRUCTURED CONCURRENCY
     * ============================================================
     *
     * Imagine a request that starts three subtasks.
     *
     * If one subtask fails, the application should often
     * cancel the other subtasks and report the failure.
     *
     * Structured concurrency organizes related concurrent
     * tasks into a clear parent-child relationship.
     *
     * The parent task manages the lifetime of its subtasks.
     *
     * This helps make concurrent code easier to reason about.
     *
     * IMPORTANT VERSION NOTE:
     *
     * Structured concurrency was introduced as a preview
     * API in Java 19.
     *
     * Its API has evolved across preview releases.
     *
     * It remains a preview feature in Java 25.
     *
     * Preview APIs require explicit enablement and may change.
     *
     *
     * ============================================================
     * 22. STRUCTUREDSCOPE
     * ============================================================
     *
     * StructuredTaskScope is the main API used by
     * structured concurrency.
     *
     * It manages a group of related subtasks.
     *
     * Conceptual example:
     *
     * try (var scope =
     *          new StructuredTaskScope.ShutdownOnFailure()) {
     *
     *     var user = scope.fork(() -> findUser());
     *
     *     var orders = scope.fork(() -> findOrders());
     *
     *     scope.join();
     *
     *     scope.throwIfFailed();
     *
     *     return combine(user.get(), orders.get());
     * }
     *
     * This illustrates the structured-concurrency model.
     *
     * Exact API names and constructors depend on the
     * Java preview version being used.
     *
     * Key ideas:
     *
     * - Fork related subtasks.
     * - Join their completion.
     * - Propagate failures.
     * - Cancel unfinished work when required.
     * - Keep task lifetimes within a lexical scope.
     *
     *
     * ============================================================
     * 23. SHUTDOWNONFAILURE VS SHUTDOWNONSUCCESS
     * ============================================================
     *
     * Structured task scopes can express different
     * completion policies.
     *
     * ShutdownOnFailure:
     *
     * Requests cancellation of unfinished subtasks
     * when a subtask fails.
     *
     * ShutdownOnSuccess:
     *
     * Requests cancellation of unfinished subtasks
     * after one subtask succeeds.
     *
     * These policies are useful for different workflows.
     *
     * Exact class names and API details are subject
     * to the preview version.
     *
     *
     * ============================================================
     * 24. SCOPED VALUES
     * ============================================================
     *
     * ThreadLocal stores data associated with a thread.
     *
     * However, passing contextual data across many
     * method calls can become difficult.
     *
     * Scoped values provide a mechanism for sharing
     * immutable contextual data within a bounded scope.
     *
     * Example concept:
     *
     * ScopedValue<String> USER =
     *     ScopedValue.newInstance();
     *
     * ScopedValue.where(USER, "Vinay")
     *     .run(() -> {
     *
     *         System.out.println(USER.get());
     *     });
     *
     * IMPORTANT VERSION NOTE:
     *
     * Scoped values were introduced as a preview feature
     * in Java 20.
     *
     * They were finalized in Java 25.
     *
     * They are designed for scoped, immutable context
     * sharing rather than unrestricted mutable thread-local state.
     *
     *
     * ============================================================
     * 25. THREADLOCAL VS SCOPEDVALUE
     * ============================================================
     *
     * ThreadLocal:
     *
     * - Associates a value with a thread.
     * - Supports changing the thread's value.
     * - Requires cleanup in reusable thread pools when appropriate.
     *
     * ScopedValue:
     *
     * - Shares a value within a bounded dynamic scope.
     * - Designed for immutable contextual data.
     * - Supports structured context propagation.
     *
     * Examples of contextual data:
     *
     * - User identity.
     * - Request correlation ID.
     * - Security context.
     * - Tracing information.
     *
     *
     * ============================================================
     * 26. CONCURRENT COLLECTIONS
     * ============================================================
     *
     * Concurrent collections are designed for safe
     * concurrent access.
     *
     * Examples:
     *
     * ConcurrentHashMap
     * ConcurrentLinkedQueue
     * ConcurrentLinkedDeque
     * CopyOnWriteArrayList
     * CopyOnWriteArraySet
     * BlockingQueue implementations
     *
     * These collections use different synchronization
     * strategies depending on their purpose.
     *
     * IMPORTANT:
     *
     * Thread-safe collections do not automatically make
     * every multi-step operation atomic.
     *
     * Example:
     *
     * if (!map.containsKey(key)) {
     *
     *     map.put(key, value);
     * }
     *
     * The check and insertion are separate operations.
     *
     * Prefer an atomic method such as:
     *
     * map.putIfAbsent(key, value);
     *
     *
     * ============================================================
     * 27. LOCK-FREE PROGRAMMING
     * ============================================================
     *
     * Lock-free programming aims to coordinate concurrent
     * operations without using traditional blocking locks
     * for the relevant algorithm.
     *
     * It often uses:
     *
     * - Compare-And-Set.
     * - Atomic variables.
     * - VarHandle.
     * - Atomic reference updates.
     *
     * Benefits can include:
     *
     * - Avoiding lock contention.
     * - Avoiding some blocking.
     * - Improved scalability for suitable algorithms.
     *
     * IMPORTANT:
     *
     * Lock-free does not mean wait-free.
     *
     * Lock-free:
     * The system as a whole continues making progress.
     *
     * Wait-free:
     * Every operation completes within a bounded number
     * of its own steps.
     *
     *
     * ============================================================
     * 28. VARHANDLE
     * ============================================================
     *
     * VarHandle was introduced in Java 9.
     *
     * It provides controlled access to variables,
     * including atomic and memory-ordering operations.
     *
     * It supports operations such as:
     *
     * - get()
     * - set()
     * - getVolatile()
     * - setVolatile()
     * - getAcquire()
     * - setRelease()
     * - compareAndSet()
     * - getAndAdd()
     *
     * It provides low-level concurrency capabilities.
     *
     * It is useful when building advanced concurrent
     * data structures and libraries.
     *
     *
     * ============================================================
     * 29. MEMORY CONSISTENCY AND CONCURRENT UTILITIES
     * ============================================================
     *
     * Java concurrency utilities define memory-consistency
     * guarantees.
     *
     * Examples:
     *
     * Actions before submitting a task to an ExecutorService
     * happen-before the task's actions.
     *
     * Actions performed by a task happen-before successful
     * retrieval of its result through Future.get().
     *
     * Actions before placing an object into a concurrent
     * collection happen-before subsequent access or removal
     * of that element by another thread according to the
     * collection's memory-consistency contract.
     *
     * These guarantees help establish visibility
     * between threads.
     *
     *
     * ============================================================
     * 30. THREAD DUMP AND THREAD DIAGNOSTICS
     * ============================================================
     *
     * A thread dump provides information about threads
     * in a running JVM.
     *
     * It can help identify:
     *
     * - Deadlocks.
     * - Blocked threads.
     * - Waiting threads.
     * - Thread pool saturation.
     * - Long-running tasks.
     * - Lock contention.
     *
     * Common tools:
     *
     * jcmd
     * jstack
     * Java Flight Recorder
     * Java Mission Control
     *
     * Example command:
     *
     * jcmd <pid> Thread.print
     *
     * Another command:
     *
     * jstack <pid>
     *
     * Thread dumps are particularly useful when
     * diagnosing production application issues.
     *
     *
     * ============================================================
     * 31. JAVA FLIGHT RECORDER
     * ============================================================
     *
     * Java Flight Recorder (JFR) is a profiling
     * and diagnostic tool built into the JDK.
     *
     * It can record information about:
     *
     * - Thread activity.
     * - CPU usage.
     * - Garbage collection.
     * - Lock contention.
     * - I/O activity.
     * - JVM events.
     *
     * JFR helps investigate performance problems
     * with relatively low overhead when configured appropriately.
     *
     *
     * ============================================================
     * 32. CONCURRENCY VS PARALLELISM
     * ============================================================
     *
     * Concurrency:
     *
     * Multiple tasks make progress during overlapping periods.
     *
     * Parallelism:
     *
     * Multiple tasks execute at the same instant
     * on different processing resources.
     *
     * A concurrent program may execute on one CPU core.
     *
     * Parallel execution requires multiple available
     * execution resources.
     *
     *
     * ============================================================
     * 33. CHOOSING THE RIGHT CONCURRENCY TOOL
     * ============================================================
     *
     * Need to wait for a fixed number of events?
     *
     * CountDownLatch.
     *
     * Need several threads to meet at a reusable barrier?
     *
     * CyclicBarrier.
     *
     * Need dynamic participants and multiple phases?
     *
     * Phaser.
     *
     * Need to limit simultaneous access to a resource?
     *
     * Semaphore.
     *
     * Need two threads to exchange objects?
     *
     * Exchanger.
     *
     * Need divide-and-conquer parallel computation?
     *
     * ForkJoinPool.
     *
     * Need lightweight threads for many blocking tasks?
     *
     * Virtual threads.
     *
     * Need to manage related subtasks as one unit?
     *
     * Structured concurrency.
     *
     * Need scoped immutable contextual data?
     *
     * Scoped values.
     *
     *
     * ============================================================
     * 34. COMMON MISTAKES
     * ============================================================
     *
     * 1. Confusing CountDownLatch with CyclicBarrier.
     *
     * 2. Forgetting to release a Semaphore permit.
     *
     * 3. Assuming a binary semaphore has mutex ownership rules.
     *
     * 4. Using ForkJoinPool for arbitrary blocking tasks
     *    without considering worker starvation.
     *
     * 5. Assuming virtual threads make CPU-bound code faster.
     *
     * 6. Creating a traditional pool to reuse virtual threads.
     *
     * 7. Assuming concurrent collections make compound
     *    operations automatically atomic.
     *
     * 8. Ignoring cancellation and interruption.
     *
     * 9. Using ThreadLocal without considering cleanup
     *    and context propagation.
     *
     * 10. Treating lock-free and wait-free as identical.
     *
     * 11. Using preview APIs without checking the target JDK.
     *
     * 12. Ignoring thread dumps and runtime diagnostics
     *     when investigating concurrency issues.
     *
     *
     * ============================================================
     * 35. IMPORTANT JAVA VERSION TIMELINE
     * ============================================================
     *
     * Java 5:
     *
     * - Executor Framework.
     * - CountDownLatch.
     * - CyclicBarrier.
     * - Semaphore.
     * - Exchanger.
     * - Concurrent collections.
     * - Atomic variables.
     *
     * Java 7:
     *
     * - Fork/Join Framework.
     *
     * Java 8:
     *
     * - CompletableFuture.
     *
     * Java 9:
     *
     * - VarHandle.
     *
     * Java 19:
     *
     * - Virtual threads preview.
     * - Structured concurrency preview.
     *
     * Java 20:
     *
     * - Scoped values preview.
     *
     * Java 21:
     *
     * - Virtual threads finalized.
     * - Structured concurrency preview.
     * - Scoped values preview.
     *
     * Java 24:
     *
     * - Monitor implementation improvements for virtual threads
     *   through JEP 491.
     *
     * Java 25:
     *
     * - Scoped values finalized.
     * - Structured concurrency remains a preview API.
     *
     *
     * ============================================================
     * 36. INTERVIEW REVISION
     * ============================================================
     *
     * Q1. What is CountDownLatch?
     *
     * Q2. Why is CountDownLatch one-time use?
     *
     * Q3. What is CyclicBarrier?
     *
     * Q4. Difference between CountDownLatch and CyclicBarrier?
     *
     * Q5. What is Phaser?
     *
     * Q6. What is Semaphore?
     *
     * Q7. Difference between binary semaphore and mutex?
     *
     * Q8. What is Exchanger?
     *
     * Q9. What is the Fork/Join Framework?
     *
     * Q10. What is work-stealing?
     *
     * Q11. Difference between RecursiveTask and RecursiveAction?
     *
     * Q12. What are virtual threads?
     *
     * Q13. Difference between platform and virtual threads?
     *
     * Q14. What is a carrier thread?
     *
     * Q15. What is virtual-thread pinning?
     *
     * Q16. Why should we not pool virtual threads?
     *
     * Q17. What is structured concurrency?
     *
     * Q18. What are scoped values?
     *
     * Q19. Difference between ThreadLocal and ScopedValue?
     *
     * Q20. What is lock-free programming?
     *
     * Q21. Difference between lock-free and wait-free?
     *
     * Q22. What is VarHandle?
     *
     * Q23. What is a thread dump?
     *
     * Q24. What is Java Flight Recorder?
     *
     *
     * ============================================================
     * 37. FINAL TAKEAWAY
     * ============================================================
     *
     * CountDownLatch:
     * Waits for a counter to reach zero.
     *
     * CyclicBarrier:
     * Synchronizes a fixed group of threads at a barrier.
     *
     * Phaser:
     * Coordinates dynamically registered parties across phases.
     *
     * Semaphore:
     * Controls access using permits.
     *
     * Exchanger:
     * Allows two threads to exchange objects.
     *
     * ForkJoinPool:
     * Executes divide-and-conquer tasks using work-stealing.
     *
     * RecursiveTask:
     * Fork/join task that returns a result.
     *
     * RecursiveAction:
     * Fork/join task that does not return a result.
     *
     * Virtual threads:
     * Lightweight JVM-managed threads for scalable concurrency.
     *
     * Structured concurrency:
     * Organizes related concurrent tasks into a managed scope.
     *
     * Scoped values:
     * Share immutable contextual data within a bounded scope.
     *
     * VarHandle:
     * Provides low-level variable access and atomic operations.
     *
     * Remember:
     *
     * Advanced concurrency is not about using every available
     * API in an application.
     *
     * It is about choosing the right coordination mechanism
     * for the problem, controlling resource usage, and
     * making concurrent behavior predictable and reliable.
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Advanced Concurrency and Modern Java , Happy Learning ..!");
    }
}
