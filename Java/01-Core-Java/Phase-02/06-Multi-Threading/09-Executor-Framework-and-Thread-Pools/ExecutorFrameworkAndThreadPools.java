
public class ExecutorFrameworkAndThreadPools {

    /*
     * ============================================================
     * 1. WHY DO WE NEED THE EXECUTOR FRAMEWORK?
     * ============================================================
     *
     * Consider an application that receives 1,000 requests.
     *
     * One approach:
     *
     * For every request:
     *     Create a new Thread.
     *     Execute the task.
     *     Finish the thread.
     *
     * Problems:
     *
     * - Creating threads has overhead.
     * - Too many threads consume memory.
     * - Excessive context switching reduces efficiency.
     * - Managing thread creation and termination becomes difficult.
     * - There is no centralized control over task execution.
     *
     * A better approach:
     *
     * Create a pool of reusable worker threads.
     *
     * Submit tasks to that pool.
     *
     * Available workers execute the submitted tasks.
     *
     * This is the idea behind the Executor Framework.
     *
     *
     * ============================================================
     * 2. WHAT IS THE EXECUTOR FRAMEWORK?
     * ============================================================
     *
     * The Executor Framework is Java's higher-level API
     * for managing and executing asynchronous tasks.
     *
     * It belongs mainly to:
     *
     * java.util.concurrent
     *
     * It separates:
     *
     * TASK:
     * What needs to be executed.
     *
     * EXECUTION:
     * Which thread executes the task and when.
     *
     * Instead of creating threads manually, we submit
     * tasks to an Executor.
     *
     *
     * ============================================================
     * 3. EXECUTOR INTERFACE
     * ============================================================
     *
     * Executor is the basic interface of the framework.
     *
     * It defines one method:
     *
     * void execute(Runnable command);
     *
     * Example:
     *
     * Executor executor = command -> {
     *
     *     new Thread(command).start();
     * };
     *
     * executor.execute(() -> {
     *
     *     System.out.println("Task executing");
     * });
     *
     * IMPORTANT:
     *
     * Executor does not necessarily create a thread pool.
     *
     * An implementation decides how and where tasks execute.
     *
     *
     * ============================================================
     * 4. EXECUTOR VS THREAD
     * ============================================================
     *
     * Thread:
     *
     * - Represents a thread of execution.
     * - Can execute a Runnable task.
     * - Requires explicit lifecycle management when used directly.
     *
     * Executor:
     *
     * - Accepts tasks for execution.
     * - Separates task submission from execution details.
     * - Can reuse threads or use another execution strategy.
     *
     * Example:
     *
     * Thread:
     *
     * new Thread(task).start();
     *
     * Executor:
     *
     * executor.execute(task);
     *
     *
     * ============================================================
     * 5. EXECUTORSERVICE
     * ============================================================
     *
     * ExecutorService extends Executor.
     *
     * It provides additional lifecycle and task-management methods.
     *
     * Important methods:
     *
     * submit()
     * shutdown()
     * shutdownNow()
     * isShutdown()
     * isTerminated()
     * awaitTermination()
     * invokeAll()
     * invokeAny()
     *
     * It is one of the most commonly used interfaces
     * in Java concurrency.
     *
     *
     * ============================================================
     * 6. SUBMITTING A RUNNABLE TASK
     * ============================================================
     *
     * Example:
     *
     * ExecutorService executor =
     *     Executors.newSingleThreadExecutor();
     *
     * executor.submit(() -> {
     *
     *     System.out.println("Task running");
     * });
     *
     * submit() accepts a Runnable or Callable.
     *
     * It returns a Future representing the submitted task.
     *
     * Unlike execute(), submit() provides a result handle
     * through which completion or failure can be observed.
     *
     *
     * ============================================================
     * 7. EXECUTE() VS SUBMIT()
     * ============================================================
     *
     * execute(Runnable):
     *
     * - Defined by Executor.
     * - Submits a Runnable for execution.
     * - Returns void.
     * - Does not return a Future.
     *
     * submit(Runnable):
     *
     * - Defined by ExecutorService.
     * - Returns a Future<?>.
     * - Allows checking completion or retrieving failure.
     *
     * submit(Callable<T>):
     *
     * - Accepts a task that returns a result.
     * - Returns Future<T>.
     *
     *
     * ============================================================
     * 8. WHAT IS A THREAD POOL?
     * ============================================================
     *
     * A thread pool is a group of worker threads managed
     * for executing submitted tasks.
     *
     * Instead of creating a new thread for every task:
     *
     * 1. Create or obtain a thread pool.
     * 2. Submit tasks.
     * 3. Workers pick up tasks.
     * 4. Workers execute tasks.
     * 5. Workers become available for more tasks.
     *
     * Benefits:
     *
     * - Thread reuse.
     * - Controlled concurrency.
     * - Reduced thread-creation overhead.
     * - Centralized task management.
     * - Better resource management.
     *
     *
     * ============================================================
     * 9. EXECUTORS UTILITY CLASS
     * ============================================================
     *
     * Executors is a utility class that provides factory
     * methods for creating common ExecutorService implementations.
     *
     * Common methods:
     *
     * newFixedThreadPool()
     * newSingleThreadExecutor()
     * newCachedThreadPool()
     * newScheduledThreadPool()
     * newWorkStealingPool()
     *
     * These methods provide convenient ways to create
     * common execution services.
     *
     *
     * ============================================================
     * 10. FIXED THREAD POOL
     * ============================================================
     *
     * newFixedThreadPool(n) creates a pool with a fixed
     * number of worker threads.
     *
     * Example:
     *
     * ExecutorService executor =
     *     Executors.newFixedThreadPool(3);
     *
     * Submit tasks:
     *
     * for (int i = 1; i <= 10; i++) {
     *
     *     int taskId = i;
     *
     *     executor.submit(() -> {
     *
     *         System.out.println(
     *             "Task " + taskId +
     *             " : " + Thread.currentThread().getName()
     *         );
     *     });
     * }
     *
     * executor.shutdown();
     *
     * At most three pool workers execute tasks concurrently.
     *
     * Remaining tasks wait in the executor's work queue.
     *
     * IMPORTANT:
     *
     * The standard newFixedThreadPool factory uses an
     * unbounded shared queue.
     *
     * Therefore, submitting tasks faster than they can
     * execute can cause the queue to grow significantly.
     *
     *
     * ============================================================
     * 11. SINGLE THREAD EXECUTOR
     * ============================================================
     *
     * newSingleThreadExecutor() creates an executor that
     * uses one worker thread to execute tasks sequentially.
     *
     * Example:
     *
     * ExecutorService executor =
     *     Executors.newSingleThreadExecutor();
     *
     * executor.submit(() -> {
     *     System.out.println("Task 1");
     * });
     *
     * executor.submit(() -> {
     *     System.out.println("Task 2");
     * });
     *
     * executor.submit(() -> {
     *     System.out.println("Task 3");
     * });
     *
     * executor.shutdown();
     *
     * Tasks execute one at a time in submission order
     * under the normal single-thread executor contract.
     *
     * Common use cases:
     *
     * - Sequential background processing.
     * - A dedicated task-processing thread.
     * - Operations that must not execute concurrently.
     *
     *
     * ============================================================
     * 12. CACHED THREAD POOL
     * ============================================================
     *
     * newCachedThreadPool() creates an executor that can
     * create new worker threads as needed and reuse
     * previously constructed threads when available.
     *
     * Example:
     *
     * ExecutorService executor =
     *     Executors.newCachedThreadPool();
     *
     * executor.submit(() -> {
     *     System.out.println("Task running");
     * });
     *
     * executor.shutdown();
     *
     * Characteristics:
     *
     * - Can create additional threads when needed.
     * - Reuses idle workers.
     * - Idle threads are eventually removed.
     * - Does not impose a fixed upper bound on worker count.
     *
     * CAUTION:
     *
     * A large number of simultaneously submitted tasks
     * can cause a large number of threads to be created.
     *
     * Use carefully when task arrival rates are unpredictable.
     *
     *
     * ============================================================
     * 13. SCHEDULED THREAD POOL
     * ============================================================
     *
     * ScheduledThreadPoolExecutor supports delayed and
     * periodic task execution.
     *
     * Factory:
     *
     * Executors.newScheduledThreadPool(n)
     *
     * Example:
     *
     * ScheduledExecutorService scheduler =
     *     Executors.newScheduledThreadPool(2);
     *
     * scheduler.schedule(
     *     () -> System.out.println("Executed later"),
     *     5,
     *     TimeUnit.SECONDS
     * );
     *
     * The task becomes eligible for execution after
     * the specified delay.
     *
     * The delay is not an exact execution-time guarantee.
     *
     *
     * ============================================================
     * 14. SCHEDULEATFIXEDRATE()
     * ============================================================
     *
     * scheduleAtFixedRate() schedules repeated executions
     * using a fixed rate.
     *
     * Example:
     *
     * scheduler.scheduleAtFixedRate(
     *     () -> System.out.println("Running"),
     *     0,
     *     5,
     *     TimeUnit.SECONDS
     * );
     *
     * Arguments:
     *
     * 1. Task.
     * 2. Initial delay.
     * 3. Period.
     * 4. Time unit.
     *
     * The next execution is scheduled relative to the
     * intended start times of previous executions.
     *
     * If an execution takes longer than the period,
     * subsequent executions may start late.
     *
     * IMPORTANT:
     *
     * Executions of the same periodic task do not overlap.
     *
     * If an execution throws an exception, subsequent
     * executions of that periodic task are suppressed.
     *
     *
     * ============================================================
     * 15. SCHEDULEWITHFIXEDDELAY()
     * ============================================================
     *
     * scheduleWithFixedDelay() schedules repeated executions
     * with a fixed delay between the termination of one
     * execution and the commencement of the next.
     *
     * Example:
     *
     * scheduler.scheduleWithFixedDelay(
     *     () -> System.out.println("Running"),
     *     0,
     *     5,
     *     TimeUnit.SECONDS
     * );
     *
     * Difference:
     *
     * Fixed rate:
     * Based on the intended start-time schedule.
     *
     * Fixed delay:
     * Waits for the configured delay after an execution ends.
     *
     * IMPORTANT:
     *
     * If an execution throws an exception, subsequent
     * executions of that periodic task are suppressed.
     *
     *
     * ============================================================
     * 16. FIXED RATE VS FIXED DELAY
     * ============================================================
     *
     * Suppose a task takes 3 seconds.
     *
     * Period or delay = 5 seconds.
     *
     * Fixed rate:
     *
     * Intended starts:
     * 0, 5, 10, 15 ...
     *
     * Fixed delay:
     *
     * Starts approximately:
     * 0, 8, 16, 24 ...
     *
     * The fixed-delay example assumes each execution
     * takes 3 seconds and the configured delay is 5 seconds.
     *
     * Choose based on whether you need a fixed schedule
     * or a delay after completion.
     *
     *
     * ============================================================
     * 17. WORK-STEALING POOL
     * ============================================================
     *
     * newWorkStealingPool() creates a work-stealing
     * ExecutorService.
     *
     * It is based on ForkJoinPool.
     *
     * Worker threads can process tasks from their own
     * queues and steal tasks from other workers when useful.
     *
     * Example:
     *
     * ExecutorService executor =
     *     Executors.newWorkStealingPool();
     *
     * executor.submit(() -> {
     *     System.out.println("Parallel task");
     * });
     *
     * executor.shutdown();
     *
     * It is useful for suitable workloads involving
     * many small tasks and parallel computation.
     *
     * IMPORTANT:
     *
     * Execution order is not guaranteed.
     *
     *
     * ============================================================
     * 18. THREADPOOL EXECUTOR
     * ============================================================
     *
     * ThreadPoolExecutor is a configurable implementation
     * of ExecutorService.
     *
     * It provides control over:
     *
     * - Core pool size.
     * - Maximum pool size.
     * - Keep-alive time.
     * - Work queue.
     * - Thread factory.
     * - Rejection policy.
     *
     * It belongs to:
     *
     * java.util.concurrent
     *
     * It is important when building production thread pools.
     *
     *
     * ============================================================
     * 19. THREADPOOLEXECUTOR CONSTRUCTOR
     * ============================================================
     *
     * A commonly used constructor is:
     *
     * ThreadPoolExecutor(
     *     int corePoolSize,
     *     int maximumPoolSize,
     *     long keepAliveTime,
     *     TimeUnit unit,
     *     BlockingQueue<Runnable> workQueue
     * )
     *
     * Example:
     *
     * ThreadPoolExecutor executor =
     *     new ThreadPoolExecutor(
     *         2,
     *         5,
     *         60,
     *         TimeUnit.SECONDS,
     *         new ArrayBlockingQueue<>(100)
     *     );
     *
     * This creates a pool with:
     *
     * Core size = 2
     * Maximum size = 5
     * Keep-alive = 60 seconds
     * Queue capacity = 100
     *
     * Additional constructor arguments can configure
     * ThreadFactory and RejectedExecutionHandler.
     *
     *
     * ============================================================
     * 20. CORE POOL SIZE
     * ============================================================
     *
     * Core pool size is the target number of core worker
     * threads maintained by the executor.
     *
     * When tasks arrive:
     *
     * The executor generally creates workers until it
     * reaches the core pool size.
     *
     * Core workers normally remain available even when
     * temporarily idle.
     *
     * allowCoreThreadTimeOut(true) can change this behavior.
     *
     *
     * ============================================================
     * 21. MAXIMUM POOL SIZE
     * ============================================================
     *
     * Maximum pool size is the maximum number of worker
     * threads the executor can create.
     *
     * It is used when the core workers are busy and the
     * work queue cannot accept another task.
     *
     * IMPORTANT:
     *
     * The maximum size does not mean the executor immediately
     * creates that many threads.
     *
     * The work queue and task-arrival behavior determine
     * when additional workers are created.
     *
     *
     * ============================================================
     * 22. KEEP-ALIVE TIME
     * ============================================================
     *
     * Keep-alive time controls how long eligible idle
     * non-core workers wait for new tasks before terminating.
     *
     * Example:
     *
     * keepAliveTime = 60
     * unit = SECONDS
     *
     * Non-core idle workers may terminate after this period.
     *
     * By default, core workers do not time out.
     *
     * allowCoreThreadTimeOut(true) allows core workers
     * to time out when the keep-alive policy permits.
     *
     *
     * ============================================================
     * 23. WORK QUEUE
     * ============================================================
     *
     * The work queue stores tasks waiting for execution.
     *
     * Common queue choices:
     *
     * 1. ArrayBlockingQueue
     * 2. LinkedBlockingQueue
     * 3. SynchronousQueue
     * 4. PriorityBlockingQueue
     *
     * ArrayBlockingQueue:
     * Bounded array-based queue.
     *
     * LinkedBlockingQueue:
     * Linked-node queue; can be bounded or effectively
     * unbounded depending on its constructor.
     *
     * SynchronousQueue:
     * Does not store elements; each insertion must
     * rendezvous with a corresponding removal.
     *
     * PriorityBlockingQueue:
     * Orders queued tasks by priority according to
     * their comparison rules.
     *
     *
     * ============================================================
     * 24. HOW THREADPOOLEXECUTOR HANDLES TASKS
     * ============================================================
     *
     * A simplified task-submission flow:
     *
     * 1. If fewer than corePoolSize workers exist,
     *    try to create a worker for the task.
     *
     * 2. Otherwise, try to place the task in the work queue.
     *
     * 3. If the queue is full, try to create a worker
     *    up to maximumPoolSize.
     *
     * 4. If the task cannot be accepted, apply the
     *    rejection policy.
     *
     * This is the central pool-sizing and queueing behavior.
     *
     * IMPORTANT:
     *
     * An unbounded queue usually prevents the pool from
     * growing beyond corePoolSize under normal operation,
     * because new tasks continue to enter the queue.
     *
     *
     * ============================================================
     * 25. REJECTION POLICY
     * ============================================================
     *
     * A task can be rejected when the executor is shut down
     * or when its capacity limits prevent acceptance.
     *
     * ThreadPoolExecutor provides four built-in policies.
     *
     * 1. AbortPolicy
     * 2. CallerRunsPolicy
     * 3. DiscardPolicy
     * 4. DiscardOldestPolicy
     *
     *
     * ============================================================
     * 26. ABORTPOLICY
     * ============================================================
     *
     * AbortPolicy is the default rejection policy.
     *
     * It throws RejectedExecutionException.
     *
     * Example:
     *
     * new ThreadPoolExecutor.AbortPolicy();
     *
     * Useful when rejection must be explicitly reported.
     *
     *
     * ============================================================
     * 27. CALLERRUNSPOLICY
     * ============================================================
     *
     * CallerRunsPolicy executes the rejected task
     * in the thread that submitted it, provided the
     * executor is not shut down.
     *
     * This can slow down task submission and create
     * a basic feedback mechanism.
     *
     * If the executor is already shut down, the task
     * is not executed by this policy.
     *
     *
     * ============================================================
     * 28. DISCARDPOLICY
     * ============================================================
     *
     * DiscardPolicy silently discards the rejected task.
     *
     * No exception is thrown by the policy.
     *
     * Use only when silently losing the task is acceptable.
     *
     *
     * ============================================================
     * 29. DISCARDOLDESTPOLICY
     * ============================================================
     *
     * DiscardOldestPolicy removes the task at the head
     * of the work queue and retries submission of the
     * new task, provided the executor is not shut down.
     *
     * The removed task is lost unless the application
     * has another mechanism to recover it.
     *
     *
     * ============================================================
     * 30. CUSTOM REJECTION POLICY
     * ============================================================
     *
     * You can implement RejectedExecutionHandler to define
     * custom rejection behavior.
     *
     * Example:
     *
     * RejectedExecutionHandler handler =
     *     (task, executor) -> {
     *
     *         System.out.println("Task rejected");
     *     };
     *
     * A custom handler can:
     *
     * - Log the rejection.
     * - Send the task to another system.
     * - Record metrics.
     * - Apply an application-specific policy.
     *
     * Be careful not to create an uncontrolled retry loop.
     *
     *
     * ============================================================
     * 31. THREADFACTORY
     * ============================================================
     *
     * ThreadFactory creates new threads for an executor.
     *
     * It is useful for customizing:
     *
     * - Thread names.
     * - Daemon status.
     * - Priorities.
     * - Uncaught exception handlers.
     *
     * Example:
     *
     * ThreadFactory factory = task -> {
     *
     *     Thread thread = new Thread(task);
     *
     *     thread.setName("My-Worker");
     *
     *     return thread;
     * };
     *
     * ThreadPoolExecutor can receive this factory
     * through its constructor.
     *
     * Production applications commonly use a naming
     * convention that gives each worker a distinct name.
     *
     *
     * ============================================================
     * 32. THREAD POOL SIZING
     * ============================================================
     *
     * Pool size should depend on workload characteristics.
     *
     * CPU-BOUND TASKS:
     *
     * Tasks spend most of their time performing computation.
     *
     * A pool around the number of available processors
     * is a common starting point.
     *
     * I/O-BOUND TASKS:
     *
     * Tasks spend significant time waiting for:
     *
     * - Database responses.
     * - Network responses.
     * - File operations.
     *
     * A larger pool may be useful because some workers
     * spend time blocked.
     *
     * IMPORTANT:
     *
     * There is no universally correct thread-pool size.
     *
     * Consider:
     *
     * - CPU count.
     * - Task duration.
     * - Blocking behavior.
     * - Memory usage.
     * - Downstream system capacity.
     * - Queue length.
     * - Latency and throughput measurements.
     *
     *
     * ============================================================
     * 33. AVAILABLE PROCESSORS
     * ============================================================
     *
     * Runtime provides:
     *
     * Runtime.getRuntime().availableProcessors();
     *
     * This returns the number of processors available
     * to the Java runtime.
     *
     * It can be used as one input when estimating
     * a CPU-bound pool size.
     *
     * It is not a complete sizing formula.
     *
     *
     * ============================================================
     * 34. SHUTDOWN()
     * ============================================================
     *
     * shutdown() initiates an orderly shutdown.
     *
     * It:
     *
     * - Stops accepting new tasks.
     * - Allows previously submitted tasks to execute.
     * - Does not wait for all tasks to finish.
     *
     * Example:
     *
     * executor.shutdown();
     *
     * You can call awaitTermination() to wait for completion.
     *
     *
     * ============================================================
     * 35. SHUTDOWNNOW()
     * ============================================================
     *
     * shutdownNow() attempts to stop active task execution.
     *
     * It:
     *
     * - Attempts to interrupt running worker threads.
     * - Prevents queued tasks from starting.
     * - Returns tasks that were awaiting execution.
     *
     * IMPORTANT:
     *
     * It is a best-effort attempt.
     *
     * It does not forcibly terminate tasks that ignore
     * interruption.
     *
     * Tasks should cooperate with interruption.
     *
     *
     * ============================================================
     * 36. IS SHUTDOWN VS IS TERMINATED
     * ============================================================
     *
     * isShutdown():
     *
     * Returns true after shutdown has been initiated.
     *
     * isTerminated():
     *
     * Returns true when shutdown has occurred and all
     * tasks have completed.
     *
     * Example:
     *
     * executor.shutdown();
     *
     * System.out.println(executor.isShutdown());
     *
     * The result can be true even while submitted tasks
     * are still executing.
     *
     *
     * ============================================================
     * 37. AWAITTERMINATION()
     * ============================================================
     *
     * awaitTermination() waits for the executor to terminate,
     * up to the specified timeout.
     *
     * Example:
     *
     * executor.shutdown();
     *
     * try {
     *
     *     if (!executor.awaitTermination(
     *             10,
     *             TimeUnit.SECONDS)) {
     *
     *         executor.shutdownNow();
     *     }
     *
     * } catch (InterruptedException e) {
     *
     *     executor.shutdownNow();
     *
     *     Thread.currentThread().interrupt();
     * }
     *
     * This is a common graceful-shutdown pattern.
     *
     *
     * ============================================================
     * 38. INVOKEALL()
     * ============================================================
     *
     * invokeAll() submits a collection of Callable tasks
     * and waits for all of them to complete.
     *
     * It returns a list of Futures.
     *
     * Example:
     *
     * List<Callable<Integer>> tasks = List.of(
     *     () -> 10,
     *     () -> 20,
     *     () -> 30
     * );
     *
     * List<Future<Integer>> results =
     *     executor.invokeAll(tasks);
     *
     * for (Future<Integer> future : results) {
     *
     *     System.out.println(future.get());
     * }
     *
     * The returned futures correspond to the tasks
     * in the input collection's iteration order.
     *
     * If invokeAll() is interrupted, unfinished tasks
     * are cancelled according to its contract.
     *
     *
     * ============================================================
     * 39. INVOKEANY()
     * ============================================================
     *
     * invokeAny() executes a collection of Callable tasks
     * and returns the result of one task that completes
     * successfully.
     *
     * Example:
     *
     * List<Callable<String>> tasks = List.of(
     *     () -> "Result A",
     *     () -> "Result B",
     *     () -> "Result C"
     * );
     *
     * String result = executor.invokeAny(tasks);
     *
     * It returns one successful result.
     *
     * The remaining unfinished tasks are cancelled
     * when a successful result is obtained.
     *
     * If no task completes successfully, an
     * ExecutionException is thrown.
     *
     *
     * ============================================================
     * 40. SUBMITTING CALLABLE TASKS
     * ============================================================
     *
     * Callable represents a task that returns a result
     * and may throw a checked exception.
     *
     * Example:
     *
     * Callable<Integer> task = () -> {
     *
     *     return 100 + 200;
     * };
     *
     * Future<Integer> future = executor.submit(task);
     *
     * The task executes asynchronously.
     *
     * The Future represents its eventual result.
     *
     *
     * ============================================================
     * 41. EXECUTOR VS EXECUTORS
     * ============================================================
     *
     * Executor:
     *
     * An interface that defines task execution.
     *
     * Executors:
     *
     * A utility class containing factory methods
     * for creating executor services.
     *
     * ExecutorService:
     *
     * An interface that adds task submission and lifecycle
     * management.
     *
     * ThreadPoolExecutor:
     *
     * A configurable implementation of ExecutorService.
     *
     *
     * ============================================================
     * 42. EXECUTOR FRAMEWORK AND SPRING BOOT
     * ============================================================
     *
     * Spring applications often execute background tasks.
     *
     * Spring provides abstractions such as:
     *
     * TaskExecutor
     * ThreadPoolTaskExecutor
     * @Async
     *
     * These are Spring features built around task execution
     * and concurrency mechanisms.
     *
     * Example:
     *
     * @Async
     * public void sendEmail() {
     *     // Execute asynchronously.
     * }
     *
     * Spring's asynchronous execution must be configured
     * appropriately.
     *
     * It is important to understand the underlying
     * executor and its resource limits.
     *
     *
     * ============================================================
     * 43. VIRTUAL THREAD EXECUTOR
     * ============================================================
     *
     * Java 21 introduced virtual threads as a final feature.
     *
     * Executors provides:
     *
     * Executors.newVirtualThreadPerTaskExecutor()
     *
     * It creates a new virtual thread for each submitted task.
     *
     * Example:
     *
     * try (ExecutorService executor =
     *          Executors.newVirtualThreadPerTaskExecutor()) {
     *
     *     executor.submit(() -> {
     *         System.out.println(
     *             Thread.currentThread()
     *         );
     *     });
     * }
     *
     * Virtual threads are lightweight threads managed
     * by the JVM.
     *
     * IMPORTANT:
     *
     * This executor does not reuse a fixed pool of virtual
     * worker threads in the traditional thread-pool sense.
     *
     * It creates a virtual thread per task.
     *
     * Virtual threads are particularly useful for workloads
     * with many concurrent blocking tasks.
     *
     * They do not make CPU-bound work execute faster
     * than the available processor capacity.
     *
     *
     * ============================================================
     * 44. TRY-WITH-RESOURCES AND EXECUTORS
     * ============================================================
     *
     * ExecutorService implements AutoCloseable in modern Java.
     *
     * Therefore, it can be used with try-with-resources.
     *
     * Example:
     *
     * try (ExecutorService executor =
     *          Executors.newFixedThreadPool(3)) {
     *
     *     executor.submit(() -> {
     *         System.out.println("Task running");
     *     });
     *
     * }
     *
     * Closing the executor initiates an orderly shutdown
     * and waits for termination according to the
     * ExecutorService close() contract.
     *
     * If the waiting thread is interrupted, close()
     * can initiate shutdownNow() and propagate interruption.
     *
     * This behavior is available in modern Java
     * (ExecutorService became AutoCloseable in Java 19).
     *
     *
     * ============================================================
     * 45. THREAD POOL MONITORING
     * ============================================================
     *
     * ThreadPoolExecutor provides monitoring methods.
     *
     * getPoolSize():
     * Current number of worker threads.
     *
     * getActiveCount():
     * Approximate number of active workers.
     *
     * getTaskCount():
     * Approximate number of tasks scheduled for execution.
     *
     * getCompletedTaskCount():
     * Approximate number of completed tasks.
     *
     * getLargestPoolSize():
     * Largest number of workers that have existed.
     *
     * getQueue():
     * Returns the work queue.
     *
     * These methods are useful for monitoring and diagnostics.
     *
     * Some values are estimates and can change while
     * the executor is operating.
     *
     *
     * ============================================================
     * 46. THREAD POOL AND BACKPRESSURE
     * ============================================================
     *
     * What happens when tasks arrive faster than workers
     * can execute them?
     *
     * The work queue grows.
     *
     * If the queue is bounded and becomes full:
     *
     * - Additional workers may be created up to the maximum.
     * - Tasks may be rejected when capacity is exhausted.
     *
     * Backpressure means slowing down or controlling
     * incoming work when the system cannot process it
     * at the current arrival rate.
     *
     * Techniques include:
     *
     * - Bounded queues.
     * - Rejection policies.
     * - Rate limiting.
     * - Admission control.
     * - Upstream request throttling.
     *
     * An unbounded queue can hide overload until memory
     * consumption becomes a serious problem.
     *
     *
     * ============================================================
     * 47. COMMON THREAD POOL MISTAKES
     * ============================================================
     *
     * 1. Creating a new thread for every task unnecessarily.
     *
     * 2. Forgetting to shut down an ExecutorService.
     *
     * 3. Assuming shutdown() waits for all tasks to finish.
     *
     * 4. Assuming shutdownNow() forcibly kills every task.
     *
     * 5. Using an unbounded queue without considering memory.
     *
     * 6. Creating an excessively large pool.
     *
     * 7. Using a CPU-sized pool for every I/O-bound workload
     *    without measuring.
     *
     * 8. Ignoring rejected tasks.
     *
     * 9. Ignoring exceptions captured by Future.
     *
     * 10. Blocking worker threads unnecessarily.
     *
     * 11. Sharing one small pool between unrelated workloads
     *     without considering resource isolation.
     *
     * 12. Assuming virtual threads eliminate all resource limits.
     *
     *
     * ============================================================
     * 48. INTERVIEW REVISION
     * ============================================================
     *
     * Q1. What is the Executor Framework?
     *
     * Q2. Why do we use thread pools?
     *
     * Q3. What is the Executor interface?
     *
     * Q4. What is ExecutorService?
     *
     * Q5. What is the difference between execute() and submit()?
     *
     * Q6. What is a fixed thread pool?
     *
     * Q7. What is a single-thread executor?
     *
     * Q8. What is a cached thread pool?
     *
     * Q9. What is ScheduledExecutorService?
     *
     * Q10. What is the difference between fixed rate
     *      and fixed delay?
     *
     * Q11. What is ThreadPoolExecutor?
     *
     * Q12. What are corePoolSize and maximumPoolSize?
     *
     * Q13. What is keepAliveTime?
     *
     * Q14. What is the purpose of the work queue?
     *
     * Q15. Explain the task-submission sequence.
     *
     * Q16. What is a rejection policy?
     *
     * Q17. Explain the four built-in rejection policies.
     *
     * Q18. What is ThreadFactory?
     *
     * Q19. What is the difference between shutdown()
     *      and shutdownNow()?
     *
     * Q20. What is awaitTermination()?
     *
     * Q21. What is the difference between isShutdown()
     *      and isTerminated()?
     *
     * Q22. What is invokeAll()?
     *
     * Q23. What is invokeAny()?
     *
     * Q24. How do you size a thread pool?
     *
     * Q25. What is backpressure?
     *
     * Q26. How does a virtual-thread-per-task executor differ
     *      from a traditional fixed thread pool?
     *
     *
     * ============================================================
     * 49. FINAL TAKEAWAY
     * ============================================================
     *
     * Executor:
     * Defines task execution.
     *
     * ExecutorService:
     * Adds task submission, results and lifecycle management.
     *
     * Thread pool:
     * Reuses workers to execute multiple tasks.
     *
     * ThreadPoolExecutor:
     * Provides configurable worker and queue management.
     *
     * ScheduledExecutorService:
     * Executes delayed and periodic tasks.
     *
     * Rejection policy:
     * Defines what happens when a task cannot be accepted.
     *
     * shutdown():
     * Initiates orderly shutdown.
     *
     * shutdownNow():
     * Attempts to interrupt running tasks and returns
     * tasks that have not started.
     *
     * Backpressure:
     * Controls incoming work when processing capacity
     * is insufficient.
     *
     * Remember:
     *
     * In production applications, managing task execution
     * is just as important as writing the task itself.
     *
     * A good executor design balances throughput,
     * latency, resource usage and reliability.
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Executor Framework and Thread Pools , Happy Learning ..!");
    }
}
