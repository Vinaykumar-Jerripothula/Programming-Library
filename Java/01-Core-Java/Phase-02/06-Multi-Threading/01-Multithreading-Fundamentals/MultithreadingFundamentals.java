
public class MultithreadingFundamentals {

    /*
     * ============================================================
     * 1. WHY DO WE NEED MULTITHREADING?
     * ============================================================
     *
     * Imagine you are using a music application.
     *
     * While listening to music, you:
     *
     * - Download another song.
     * - Search for an artist.
     * - Update your playlist.
     * - Display song information.
     *
     * Would you want the entire application to stop playing music
     * while downloading another song?
     *
     * Obviously not!
     *
     * We want multiple activities to make progress without
     * unnecessarily blocking one another.
     *
     * This is where MULTITHREADING becomes useful.
     *
     * Multithreading allows a single process to execute multiple
     * threads of execution.
     *
     * Each thread represents an independent path of execution
     * within the same process.
     *
     *
     * ============================================================
     * 2. WHAT IS A PROGRAM?
     * ============================================================
     *
     * A program is a set of instructions written to perform
     * a particular task.
     *
     * Example:
     *
     * A Java program that calculates the sum of two numbers.
     *
     * A program is generally stored on secondary storage
     * as a file, such as a .java or .class file.
     *
     * A program itself is not an executing process.
     *
     *
     * ============================================================
     * 3. WHAT IS A PROCESS?
     * ============================================================
     *
     * A PROCESS is an instance of a program that is currently
     * executing.
     *
     * Example:
     *
     * When you open a browser, the operating system creates
     * one or more processes to run it.
     *
     * When you open a Java application, the operating system
     * creates a process for the JVM.
     *
     * A process generally has:
     *
     * - Its own virtual address space.
     * - Allocated memory resources.
     * - Operating-system resources.
     * - One or more threads.
     *
     * IMPORTANT:
     *
     * A process is a resource and execution container.
     *
     * A process can contain multiple threads.
     *
     *
     * ============================================================
     * 4. WHAT IS A THREAD?
     * ============================================================
     *
     * A THREAD is an independent path of execution within
     * a process.
     *
     * It is the smallest unit of execution scheduled by
     * the operating system or runtime.
     *
     * A process can contain multiple threads.
     *
     * Example:
     *
     * A Java application may have:
     *
     * Thread 1 -> Handles user requests.
     * Thread 2 -> Processes background tasks.
     * Thread 3 -> Performs logging.
     * Thread 4 -> Handles scheduled operations.
     *
     * These threads belong to the same process.
     *
     * IMPORTANT:
     *
     * Threads within a process share many process resources,
     * including the heap and loaded class data.
     *
     * However, each thread has its own:
     *
     * - Program counter.
     * - JVM stack.
     * - Stack frames for method execution.
     *
     * This distinction is fundamental to understanding
     * multithreading.
     *
     *
     * ============================================================
     * 5. PROCESS VS THREAD
     * ============================================================
     *
     * PROCESS:
     *
     * - An executing instance of a program.
     * - Has its own virtual address space.
     * - Owns process-level resources.
     * - Can contain multiple threads.
     * - Communication between processes generally requires
     *   inter-process communication mechanisms.
     *
     * THREAD:
     *
     * - An execution path inside a process.
     * - Shares process resources with other threads.
     * - Has its own execution stack and program counter.
     * - Multiple threads can execute within one process.
     * - Communication through shared memory is possible.
     *
     * ANALOGY:
     *
     * Process = A company.
     *
     * Threads = Employees working inside that company.
     *
     * The employees share company resources, but each employee
     * performs their own work.
     *
     *
     * ============================================================
     * 6. WHAT IS SINGLE-THREADING?
     * ============================================================
     *
     * Single-threading means an application performs its
     * execution through one thread.
     *
     * Example:
     *
     * public class SingleThreadExample {
     *
     *     public static void main(String[] args) {
     *
     *         System.out.println("Task 1");
     *         System.out.println("Task 2");
     *         System.out.println("Task 3");
     *     }
     * }
     *
     * Output:
     *
     * Task 1
     * Task 2
     * Task 3
     *
     * These statements execute sequentially on the main thread.
     *
     * If Task 2 takes a long time, Task 3 must wait.
     *
     *
     * ============================================================
     * 7. WHAT IS MULTITHREADING?
     * ============================================================
     *
     * Multithreading means executing multiple threads
     * within a single process.
     *
     * Example:
     *
     * public class Example {
     *
     *     public static void main(String[] args) {
     *
     *         Thread thread = new Thread(() -> {
     *
     *             System.out.println("Background task");
     *         });
     *
     *         thread.start();
     *
     *         System.out.println("Main task");
     *     }
     * }
     *
     * Here:
     *
     * - The main thread executes main().
     * - Another thread executes the supplied task.
     *
     * Both threads can make progress independently.
     *
     * IMPORTANT:
     *
     * Their output order is not guaranteed.
     *
     * Possible output:
     *
     * Main task
     * Background task
     *
     * Another possible output:
     *
     * Background task
     * Main task
     *
     * The scheduler determines when threads execute.
     *
     *
     * ============================================================
     * 8. WHAT IS CONCURRENCY?
     * ============================================================
     *
     * Concurrency means multiple tasks can make progress
     * during overlapping periods.
     *
     * It does not necessarily mean they execute at exactly
     * the same instant.
     *
     * Imagine one CPU core handling two tasks:
     *
     * Time 1 -> Task A
     * Time 2 -> Task B
     * Time 3 -> Task A
     * Time 4 -> Task B
     *
     * The CPU switches between tasks.
     *
     * Both tasks make progress over the same period.
     *
     * This is concurrency.
     *
     * IMPORTANT:
     *
     * Concurrency is about managing multiple tasks that
     * overlap in their execution.
     *
     *
     * ============================================================
     * 9. WHAT IS PARALLELISM?
     * ============================================================
     *
     * Parallelism means executing multiple tasks at the
     * same instant using multiple execution resources.
     *
     * Example:
     *
     * CPU Core 1 -> Task A
     * CPU Core 2 -> Task B
     *
     * Both tasks execute simultaneously.
     *
     * This is parallelism.
     *
     * Parallelism generally requires multiple available
     * execution resources, such as CPU cores.
     *
     *
     * ============================================================
     * 10. CONCURRENCY VS PARALLELISM
     * ============================================================
     *
     * CONCURRENCY:
     *
     * - Multiple tasks make progress over overlapping periods.
     * - Tasks may take turns on one CPU core.
     * - Does not require simultaneous execution.
     *
     * PARALLELISM:
     *
     * - Multiple tasks execute at the same instant.
     * - Uses multiple execution resources.
     * - Can reduce completion time for suitable workloads.
     *
     * EASY WAY TO REMEMBER:
     *
     * Concurrency:
     * "Dealing with multiple tasks at once."
     *
     * Parallelism:
     * "Doing multiple tasks at the exact same time."
     *
     * A system can support concurrency without parallelism.
     *
     * A multicore system can support both.
     *
     *
     * ============================================================
     * 11. WHAT IS A CPU CORE?
     * ============================================================
     *
     * A CPU core is an individual processing unit within
     * a CPU.
     *
     * Each core can execute instructions independently.
     *
     * Example:
     *
     * A quad-core CPU has four physical cores.
     *
     * These cores can execute multiple threads in parallel,
     * subject to the processor and operating-system scheduling.
     *
     * IMPORTANT:
     *
     * Number of Java threads does not need to equal the
     * number of CPU cores.
     *
     * An application can create many more threads than
     * the machine has CPU cores.
     *
     *
     * ============================================================
     * 12. WHAT IS CONTEXT SWITCHING?
     * ============================================================
     *
     * Context switching happens when the CPU stops executing
     * one thread and switches to another.
     *
     * The execution state of the current thread must be saved,
     * and the next thread's state must be restored.
     *
     * Example:
     *
     * CPU executes Thread A.
     *
     * The scheduler switches execution to Thread B.
     *
     * Later, it switches back to Thread A.
     *
     * The saved state allows Thread A to continue from
     * where it was interrupted.
     *
     * Context switching is necessary for multitasking.
     *
     * However, it has overhead.
     *
     * Too many unnecessary context switches can reduce
     * application performance.
     *
     *
     * ============================================================
     * 13. WHAT IS THE THREAD SCHEDULER?
     * ============================================================
     *
     * A thread scheduler determines which eligible thread
     * should execute on an available CPU.
     *
     * On typical Java platforms, platform-thread scheduling
     * is managed by the operating system.
     *
     * The JVM works with the operating system to execute
     * Java threads.
     *
     * IMPORTANT:
     *
     * Java does not guarantee a particular execution order
     * between independently running threads.
     *
     * Thread priority is only a scheduling hint.
     *
     * Do not write programs that depend on a particular
     * scheduling order.
     *
     *
     * ============================================================
     * 14. WHAT IS MULTITASKING?
     * ============================================================
     *
     * Multitasking means managing multiple tasks so that
     * they make progress during overlapping periods.
     *
     * Two commonly discussed forms:
     *
     * PROCESS-BASED MULTITASKING:
     *
     * Multiple processes execute concurrently.
     *
     * Example:
     *
     * - Browser process.
     * - Music player process.
     * - Code editor process.
     *
     * THREAD-BASED MULTITASKING:
     *
     * Multiple threads execute within a process.
     *
     * Example:
     *
     * - UI thread.
     * - Download thread.
     * - Background processing thread.
     *
     *
     * ============================================================
     * 15. WHY IS MULTITHREADING USEFUL?
     * ============================================================
     *
     * 1. RESPONSIVENESS
     *
     * Long-running work can be moved away from the UI thread.
     *
     * 2. RESOURCE UTILIZATION
     *
     * Multiple CPU cores can execute suitable work in parallel.
     *
     * 3. THROUGHPUT
     *
     * A server can process multiple requests concurrently.
     *
     * 4. BACKGROUND PROCESSING
     *
     * Tasks such as logging, scheduled work and file processing
     * can run separately from request handling.
     *
     * 5. BETTER TASK ORGANIZATION
     *
     * Independent tasks can have separate execution paths.
     *
     *
     * ============================================================
     * 16. CPU-BOUND TASKS
     * ============================================================
     *
     * A CPU-bound task spends most of its time performing
     * computations.
     *
     * Examples:
     *
     * - Mathematical calculations.
     * - Image processing.
     * - Data compression.
     * - Encryption.
     * - Large sorting operations.
     *
     * These tasks need CPU processing power.
     *
     * Parallel execution may improve performance when tasks
     * can be divided into independent pieces.
     *
     * However, creating too many threads can introduce
     * scheduling and coordination overhead.
     *
     *
     * ============================================================
     * 17. I/O-BOUND TASKS
     * ============================================================
     *
     * An I/O-bound task spends much of its time waiting for
     * external operations.
     *
     * Examples:
     *
     * - Database queries.
     * - HTTP requests.
     * - File reading.
     * - File writing.
     * - Network communication.
     *
     * During I/O waits, a thread may not be using the CPU.
     *
     * Other tasks can make progress while the operation waits.
     *
     * This is one reason concurrency is valuable in backend
     * applications.
     *
     *
     * ============================================================
     * 18. CPU-BOUND VS I/O-BOUND
     * ============================================================
     *
     * CPU-BOUND:
     *
     * - Limited mainly by CPU processing.
     * - Performs intensive computation.
     * - Parallelism can improve throughput.
     *
     * I/O-BOUND:
     *
     * - Limited mainly by external I/O waits.
     * - Often spends time waiting.
     * - Concurrency can help utilize available resources.
     *
     * Example:
     *
     * Calculating prime numbers -> CPU-bound.
     *
     * Waiting for a database response -> I/O-bound.
     *
     *
     * ============================================================
     * 19. SHARED MEMORY IN MULTITHREADING
     * ============================================================
     *
     * Threads belonging to the same process share the
     * process's heap memory.
     *
     * Example:
     *
     * class Counter {
     *     int count = 0;
     * }
     *
     * Multiple threads can access the same Counter object
     * if they receive a reference to it.
     *
     * This allows threads to communicate through shared data.
     *
     * But it also introduces risks.
     *
     * If multiple threads modify shared data without proper
     * coordination, race conditions can occur.
     *
     * We will study this in detail in the synchronization
     * section.
     *
     *
     * ============================================================
     * 20. WHAT IS A RACE CONDITION?
     * ============================================================
     *
     * A race condition occurs when program behavior depends
     * on the timing or interleaving of concurrent operations.
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
     * Suppose two threads call increment() simultaneously.
     *
     * count++ is not one indivisible operation.
     *
     * It involves reading, modifying and writing the value.
     *
     * Both threads may read the same old value and overwrite
     * one another's updates.
     *
     * This can produce an incorrect result.
     *
     * We will study race conditions and their solutions
     * in detail later.
     *
     *
     * ============================================================
     * 21. WHAT IS THREAD SAFETY?
     * ============================================================
     *
     * Thread safety means code behaves correctly when accessed
     * by multiple threads according to its intended contract.
     *
     * A thread-safe class protects its correctness when
     * multiple threads use it concurrently.
     *
     * Common techniques:
     *
     * - Synchronization.
     * - Locks.
     * - Atomic variables.
     * - Immutable objects.
     * - Thread confinement.
     * - Concurrent collections.
     *
     * Thread safety is not automatically achieved just because
     * an application uses multiple threads.
     *
     *
     * ============================================================
     * 22. WHAT IS THREAD CONTENTION?
     * ============================================================
     *
     * Thread contention occurs when multiple threads compete
     * for the same limited resource.
     *
     * Example:
     *
     * Multiple threads need to acquire the same lock.
     *
     * Only one thread can hold an exclusive lock at a time.
     *
     * Other threads may have to wait.
     *
     * Excessive contention can reduce throughput.
     *
     *
     * ============================================================
     * 23. MULTITHREADING CHALLENGES
     * ============================================================
     *
     * Multithreading introduces complexity.
     *
     * Common challenges:
     *
     * 1. RACE CONDITIONS
     *    Multiple threads interfere with shared data.
     *
     * 2. DEADLOCK
     *    Threads wait indefinitely for one another's locks.
     *
     * 3. STARVATION
     *    A thread does not get sufficient access to resources.
     *
     * 4. LIVENESS PROBLEMS
     *    Threads fail to make useful progress.
     *
     * 5. VISIBILITY PROBLEMS
     *    One thread's updates may not become visible to another
     *    without proper synchronization.
     *
     * 6. DEBUGGING DIFFICULTY
     *    Thread scheduling can make failures difficult to reproduce.
     *
     * 7. PERFORMANCE OVERHEAD
     *    Thread creation, coordination and context switching
     *    consume resources.
     *
     *
     * ============================================================
     * 24. HOW JAVA SUPPORTS MULTITHREADING
     * ============================================================
     *
     * Java provides APIs for creating and managing concurrent
     * execution.
     *
     * Important classes and interfaces:
     *
     * Thread
     * Runnable
     * Callable
     * Executor
     * ExecutorService
     * Future
     * CompletableFuture
     * ReentrantLock
     * Semaphore
     * CountDownLatch
     * CyclicBarrier
     * Phaser
     * AtomicInteger
     * ConcurrentHashMap
     *
     * These APIs help developers build concurrent applications.
     *
     * We will study them in the appropriate sections.
     *
     *
     * ============================================================
     * 25. PLATFORM THREADS VS VIRTUAL THREADS
     * ============================================================
     *
     * Java supports two important kinds of threads.
     *
     * PLATFORM THREAD:
     *
     * - Represents a traditional Java thread backed by an
     *   operating-system thread.
     * - Useful for general-purpose execution.
     * - Has a relatively higher resource cost than a virtual thread.
     *
     * VIRTUAL THREAD:
     *
     * - Lightweight Java thread.
     * - Introduced as a preview feature in Java 19.
     * - Finalized in Java 21.
     * - Managed by the JVM and scheduled onto platform threads.
     * - Particularly useful for high-concurrency blocking workloads.
     *
     * IMPORTANT:
     *
     * Virtual threads do not make CPU-bound calculations
     * execute faster by themselves.
     *
     * They make it practical to support many concurrent tasks
     * that spend substantial time waiting.
     *
     * We will study virtual threads in the advanced section.
     *
     *
     * ============================================================
     * 26. WHERE IS MULTITHREADING USED IN REAL APPLICATIONS?
     * ============================================================
     *
     * OPERATING SYSTEMS:
     *
     * Multiple tasks and services execute concurrently.
     *
     * WEB SERVERS:
     *
     * Multiple client requests can be handled concurrently.
     *
     * DATABASE SYSTEMS:
     *
     * Multiple queries and background operations may execute
     * concurrently.
     *
     * BANKING APPLICATIONS:
     *
     * Multiple transactions and requests may be processed.
     *
     * MEDIA APPLICATIONS:
     *
     * Playback, downloading and user interaction can progress
     * independently.
     *
     * BACKEND APPLICATIONS:
     *
     * Request processing, asynchronous operations, scheduled
     * jobs and background tasks use concurrency.
     *
     *
     * ============================================================
     * 27. MULTITHREADING IN SPRING BOOT
     * ============================================================
     *
     * Multithreading is important in Java backend development.
     *
     * Examples:
     *
     * - Handling HTTP requests.
     * - Running asynchronous service methods.
     * - Processing background jobs.
     * - Executing scheduled tasks.
     * - Sending emails asynchronously.
     * - Calling multiple external APIs concurrently.
     *
     * Spring provides abstractions such as:
     *
     * @Async
     * TaskExecutor
     * ThreadPoolTaskExecutor
     * @Scheduled
     *
     * These build on Java concurrency facilities.
     *
     * IMPORTANT:
     *
     * Spring's @Async does not automatically make shared
     * mutable state thread-safe.
     *
     * Developers must still design thread-safe operations.
     *
     *
     * ============================================================
     * 28. IMPORTANT TERMS - QUICK REVISION
     * ============================================================
     *
     * PROGRAM:
     * A set of instructions.
     *
     * PROCESS:
     * An executing instance of a program.
     *
     * THREAD:
     * An independent path of execution within a process.
     *
     * MULTITHREADING:
     * Multiple threads executing within one process.
     *
     * CONCURRENCY:
     * Multiple tasks making progress during overlapping periods.
     *
     * PARALLELISM:
     * Multiple tasks executing simultaneously.
     *
     * CPU CORE:
     * An individual processing unit within a CPU.
     *
     * CONTEXT SWITCH:
     * Switching CPU execution from one thread to another.
     *
     * RACE CONDITION:
     * Behavior depends on timing or interleaving of operations.
     *
     * THREAD SAFETY:
     * Correct behavior under concurrent access.
     *
     * CONTENTION:
     * Multiple threads competing for a shared resource.
     *
     *
     * ============================================================
     * 29. INTERVIEW REVISION
     * ============================================================
     *
     * Q1. What is a process?
     *
     * Q2. What is a thread?
     *
     * Q3. What is the difference between a process and a thread?
     *
     * Q4. Can a process contain multiple threads?
     *
     * Q5. What is multithreading?
     *
     * Q6. What is the difference between concurrency and parallelism?
     *
     * Q7. Can concurrency happen on a single-core CPU?
     *
     * Q8. What is a CPU core?
     *
     * Q9. What is context switching?
     *
     * Q10. What is a thread scheduler?
     *
     * Q11. What is the difference between CPU-bound and I/O-bound work?
     *
     * Q12. What is a race condition?
     *
     * Q13. What is thread safety?
     *
     * Q14. What is thread contention?
     *
     * Q15. What are the common challenges of multithreading?
     *
     * Q16. What is the difference between platform threads
     *      and virtual threads?
     *
     *
     * ============================================================
     * 30. FINAL TAKEAWAY
     * ============================================================
     *
     * A PROGRAM is a set of instructions.
     *
     * A PROCESS is an executing instance of a program.
     *
     * A THREAD is an execution path inside a process.
     *
     * MULTITHREADING allows multiple threads to execute
     * within a single process.
     *
     * CONCURRENCY allows multiple tasks to make progress
     * during overlapping periods.
     *
     * PARALLELISM allows multiple tasks to execute at
     * the same instant.
     *
     * Remember:
     *
     * Multiple threads provide opportunities for better
     * responsiveness and resource utilization.
     *
     * But shared data introduces concurrency challenges.
     *
     * Understanding both the benefits and the risks is
     * the foundation of Java Multithreading.
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Multithreading Fundamentals , Happy Learning ..!");
    }
}
