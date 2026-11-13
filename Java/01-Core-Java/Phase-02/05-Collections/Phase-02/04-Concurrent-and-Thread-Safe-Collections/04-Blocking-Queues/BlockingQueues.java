
public class BlockingQueues {

    /*
     * ============================================================
     * BLOCKING QUEUES IN JAVA
     * ============================================================
     *
     * 1. WHY DO WE NEED BLOCKING QUEUES?
     *
     * Imagine an application with two types of threads:
     *
     * Producer:
     *     Creates tasks and adds them to a queue.
     *
     * Consumer:
     *     Retrieves tasks and processes them.
     *
     * Consider this situation:
     *
     * Producer adds Task-1.
     * Consumer retrieves Task-1.
     * Queue becomes empty.
     *
     * What should the consumer do now?
     *
     * It should wait until another task arrives.
     *
     * Another situation:
     *
     * Queue is full.
     *
     * What should the producer do?
     *
     * It should wait until the consumer removes an element.
     *
     * BlockingQueue solves these problems.
     *
     *
     * ============================================================
     * 2. WHAT IS A BLOCKING QUEUE?
     * ============================================================
     *
     * BlockingQueue is an interface in:
     *
     * java.util.concurrent
     *
     * It extends the Queue interface.
     *
     * It is designed for communication between threads.
     *
     * It provides operations that can wait when:
     *
     * - The queue is empty and an element is requested.
     * - The queue is full and an element is inserted.
     *
     * IMPORTANT:
     *
     * A BlockingQueue can be bounded or unbounded,
     * depending on its implementation and configuration.
     *
     *
     * ============================================================
     * 3. FOUR GROUPS OF BLOCKINGQUEUE METHODS
     * ============================================================
     *
     * BlockingQueue provides four categories of operations.
     *
     * A. EXCEPTION-THROWING OPERATIONS
     *
     * add(e):
     * Inserts an element.
     * Throws IllegalStateException if capacity is unavailable.
     *
     * remove():
     * Retrieves and removes the head.
     * Throws NoSuchElementException if empty.
     *
     * element():
     * Retrieves but does not remove the head.
     * Throws NoSuchElementException if empty.
     *
     *
     * B. SPECIAL-VALUE OPERATIONS
     *
     * offer(e):
     * Inserts if possible.
     * Returns false if capacity is unavailable.
     *
     * poll():
     * Retrieves and removes the head.
     * Returns null if empty.
     *
     * peek():
     * Retrieves but does not remove the head.
     * Returns null if empty.
     *
     *
     * C. BLOCKING OPERATIONS
     *
     * put(e):
     * Waits if necessary until space becomes available.
     *
     * take():
     * Waits if necessary until an element becomes available.
     *
     *
     * D. TIMED OPERATIONS
     *
     * offer(e, time, unit):
     * Waits up to the specified time for space.
     *
     * poll(time, unit):
     * Waits up to the specified time for an element.
     *
     *
     * ============================================================
     * 4. BLOCKINGQUEUE METHOD COMPARISON
     * ============================================================
     *
     * Operation       Full Queue       Empty Queue
     *
     * add(e)          Exception        Not applicable
     *
     * offer(e)        false            Not applicable
     *
     * put(e)          Waits            Not applicable
     *
     * offer(e,time)   Waits up to time Not applicable
     *
     * remove()        Not applicable   Exception
     *
     * poll()          Not applicable   null
     *
     * take()          Not applicable   Waits
     *
     * poll(time)      Not applicable   Waits up to time
     *
     * IMPORTANT:
     *
     * BlockingQueue does not permit null elements.
     *
     *
     * ============================================================
     * 5. ARRAYBLOCKINGQUEUE
     * ============================================================
     *
     * ArrayBlockingQueue is a bounded blocking queue.
     *
     * Bounded means it has a fixed capacity.
     *
     * Internally, it uses an array.
     *
     * Example:
     *
     * ArrayBlockingQueue<Integer> queue =
     *     new ArrayBlockingQueue<>(3);
     *
     * Capacity = 3.
     *
     * queue.put(10);
     * queue.put(20);
     * queue.put(30);
     *
     * Queue is now full.
     *
     * queue.put(40);
     *
     * The producer waits until space becomes available.
     *
     * When a consumer removes an element, the producer
     * can continue.
     *
     *
     * ============================================================
     * 6. ARRAYBLOCKINGQUEUE INTERNAL WORKING
     * ============================================================
     *
     * It uses a fixed-size circular array.
     *
     * Conceptual representation:
     *
     * [10, 20, 30]
     *
     * It maintains positions for:
     *
     * - Next insertion.
     * - Next removal.
     *
     * When an element is removed, the positions move
     * circularly through the array.
     *
     * It uses a ReentrantLock to coordinate access.
     *
     * Conditions are used to manage waiting producers
     * and consumers.
     *
     * IMPORTANT:
     *
     * The internal implementation is more detailed than
     * this conceptual representation.
     *
     *
     * ============================================================
     * 7. ARRAYBLOCKINGQUEUE FAIRNESS
     * ============================================================
     *
     * ArrayBlockingQueue supports an optional fairness policy.
     *
     * Default:
     *
     * Fairness = false.
     *
     * Example:
     *
     * ArrayBlockingQueue<Integer> queue =
     *     new ArrayBlockingQueue<>(5, true);
     *
     * true requests fair access for waiting threads.
     *
     * Fairness generally favors threads waiting longer.
     *
     * Trade-off:
     *
     * Fairness can reduce throughput compared with
     * non-fair access.
     *
     *
     * ============================================================
     * 8. LINKEDBLOCKINGQUEUE
     * ============================================================
     *
     * LinkedBlockingQueue is a blocking queue based on
     * linked nodes.
     *
     * It can be:
     *
     * - Bounded.
     * - Effectively unbounded when no capacity is supplied.
     *
     * Example - bounded:
     *
     * LinkedBlockingQueue<Integer> queue =
     *     new LinkedBlockingQueue<>(5);
     *
     * Example - default capacity:
     *
     * LinkedBlockingQueue<Integer> queue =
     *     new LinkedBlockingQueue<>();
     *
     * Its default capacity is Integer.MAX_VALUE.
     *
     * IMPORTANT:
     *
     * A very large theoretical capacity does not mean
     * unlimited memory.
     *
     * Actual available memory still limits the queue.
     *
     *
     * ============================================================
     * 9. LINKEDBLOCKINGQUEUE INTERNAL WORKING
     * ============================================================
     *
     * It uses a linked-node structure.
     *
     * Conceptual node:
     *
     * class Node<E> {
     *     E item;
     *     Node<E> next;
     * }
     *
     * Conceptual structure:
     *
     * HEAD -> [10] -> [20] -> [30] -> TAIL
     *
     * It maintains separate locking mechanisms for
     * insertion and removal.
     *
     * This can allow a producer and consumer to make
     * progress concurrently in suitable situations.
     *
     * It also uses conditions to coordinate waiting threads.
     *
     *
     * ============================================================
     * 10. ARRAYBLOCKINGQUEUE VS LINKEDBLOCKINGQUEUE
     * ============================================================
     *
     * ArrayBlockingQueue:
     *
     * - Array-based.
     * - Fixed capacity.
     * - Capacity must be supplied.
     * - Uses one main lock for queue operations.
     * - Lower node-allocation overhead.
     *
     * LinkedBlockingQueue:
     *
     * - Linked-node-based.
     * - Can be bounded or effectively unbounded.
     * - Can use separate put and take locks.
     * - Requires node allocation.
     *
     * Choose based on:
     *
     * - Memory requirements.
     * - Capacity requirements.
     * - Throughput.
     * - Workload.
     *
     *
     * ============================================================
     * 11. PRIORITYBLOCKINGQUEUE
     * ============================================================
     *
     * PriorityBlockingQueue is a blocking priority queue.
     *
     * It orders elements according to:
     *
     * - Natural ordering.
     * - A supplied Comparator.
     *
     * Example:
     *
     * PriorityBlockingQueue<Integer> queue =
     *     new PriorityBlockingQueue<>();
     *
     * queue.offer(30);
     * queue.offer(10);
     * queue.offer(20);
     *
     * queue.take(); // 10
     *
     * By default, the smallest element has the highest priority.
     *
     * IMPORTANT:
     *
     * PriorityBlockingQueue is logically unbounded.
     *
     * It does not block producers because of a fixed
     * user-defined capacity.
     *
     * Its internal array may grow as required,
     * subject to available memory and implementation limits.
     *
     *
     * ============================================================
     * 12. PRIORITYBLOCKINGQUEUE INTERNAL WORKING
     * ============================================================
     *
     * It uses a heap-based priority queue.
     *
     * The head contains the least element according
     * to its ordering.
     *
     * offer():
     * Inserts an element and restores heap ordering.
     *
     * take():
     * Retrieves and removes the highest-priority element.
     *
     * IMPORTANT:
     *
     * PriorityBlockingQueue is not FIFO for all elements.
     *
     * Elements are retrieved according to priority.
     *
     * Elements with equal priority have no guaranteed
     * FIFO ordering.
     *
     *
     * ============================================================
     * 13. DELAYQUEUE
     * ============================================================
     *
     * DelayQueue is an unbounded blocking queue.
     *
     * An element becomes available for removal only after
     * its delay has expired.
     *
     * Its elements must implement:
     *
     * Delayed
     *
     * The Delayed interface provides:
     *
     * getDelay(TimeUnit unit)
     *
     * Elements are ordered according to their remaining delay.
     *
     * Example use cases:
     *
     * - Scheduled task expiration.
     * - Temporary cache entry expiration.
     * - Delayed job execution.
     * - Retry scheduling.
     *
     *
     * ============================================================
     * 14. HOW DELAYQUEUE WORKS
     * ============================================================
     *
     * Consider three tasks:
     *
     * Task A -> Delay of 5 seconds.
     * Task B -> Delay of 10 seconds.
     * Task C -> Delay of 2 seconds.
     *
     * The task with the shortest remaining delay is
     * eligible first.
     *
     * A consumer calling take() waits until an expired
     * element becomes available.
     *
     * IMPORTANT:
     *
     * Merely inserting an element does not mean it can
     * be removed immediately.
     *
     * Its delay must expire.
     *
     *
     * ============================================================
     * 15. SYNCHRONOUSQUEUE
     * ============================================================
     *
     * SynchronousQueue is a special BlockingQueue.
     *
     * It has no internal capacity for storing elements.
     *
     * Capacity = 0.
     *
     * It works through direct handoff between producer
     * and consumer threads.
     *
     * Example:
     *
     * SynchronousQueue<String> queue =
     *     new SynchronousQueue<>();
     *
     * Producer:
     * queue.put("Task");
     *
     * Consumer:
     * queue.take();
     *
     * The producer waits until a consumer receives
     * the element.
     *
     * The consumer waits until a producer provides
     * an element.
     *
     *
     * ============================================================
     * 16. SYNCHRONOUSQUEUE - IMPORTANT BEHAVIOR
     * ============================================================
     *
     * It does not store elements for later retrieval.
     *
     * Every successful insertion must be matched
     * with a corresponding removal.
     *
     * It is useful for direct thread handoff.
     *
     * Example:
     *
     * Thread A -> Produces a task.
     * Thread B -> Immediately receives that task.
     *
     * It is used in some thread-pool execution strategies.
     *
     *
     * ============================================================
     * 17. TRANSFERQUEUE
     * ============================================================
     *
     * TransferQueue is an extension of BlockingQueue.
     *
     * It provides a transfer operation.
     *
     * transfer(element):
     *
     * Waits until another thread receives the element.
     *
     * Java provides:
     *
     * LinkedTransferQueue
     *
     * It is a concurrent queue that supports transfer
     * and ordinary queue operations.
     *
     * It can use direct handoff when a consumer is waiting,
     * or queue elements for later consumption.
     *
     *
     * ============================================================
     * 18. LINKEDTRANSFERQUEUE
     * ============================================================
     *
     * LinkedTransferQueue is an unbounded concurrent
     * TransferQueue.
     *
     * It supports:
     *
     * - offer().
     * - put().
     * - take().
     * - transfer().
     * - tryTransfer().
     *
     * Important difference:
     *
     * put():
     * Inserts an element without waiting for a consumer
     * to receive it.
     *
     * transfer():
     * Waits until a consumer receives the element.
     *
     * tryTransfer():
     * Attempts to transfer immediately to a waiting consumer.
     *
     * It uses a non-blocking linked structure internally.
     *
     *
     * ============================================================
     * 19. PRODUCER-CONSUMER PROBLEM
     * ============================================================
     *
     * The Producer-Consumer problem is a classic
     * concurrency problem.
     *
     * Producer:
     * Creates data and inserts it into a shared queue.
     *
     * Consumer:
     * Retrieves and processes data.
     *
     * Without proper coordination:
     *
     * - Producer may insert into a full bounded buffer.
     * - Consumer may try to retrieve from an empty buffer.
     * - Shared state may become inconsistent.
     *
     * BlockingQueue provides coordination through put()
     * and take().
     *
     *
     * ============================================================
     * 20. PRODUCER-CONSUMER EXAMPLE
     * ============================================================
     *
     * import java.util.concurrent.*;
     *
     * class Producer implements Runnable {
     *
     *     private final BlockingQueue<Integer> queue;
     *
     *     Producer(BlockingQueue<Integer> queue) {
     *         this.queue = queue;
     *     }
     *
     *     public void run() {
     *
     *         try {
     *
     *             for (int i = 1; i <= 5; i++) {
     *
     *                 queue.put(i);
     *
     *                 System.out.println("Produced: " + i);
     *             }
     *
     *         } catch (InterruptedException e) {
     *
     *             Thread.currentThread().interrupt();
     *         }
     *     }
     * }
     *
     * class Consumer implements Runnable {
     *
     *     private final BlockingQueue<Integer> queue;
     *
     *     Consumer(BlockingQueue<Integer> queue) {
     *         this.queue = queue;
     *     }
     *
     *     public void run() {
     *
     *         try {
     *
     *             for (int i = 1; i <= 5; i++) {
     *
     *                 int value = queue.take();
     *
     *                 System.out.println("Consumed: " + value);
     *             }
     *
     *         } catch (InterruptedException e) {
     *
     *             Thread.currentThread().interrupt();
     *         }
     *     }
     * }
     *
     * public class ProducerConsumerExample {
     *
     *     public static void main(String[] args) {
     *
     *         BlockingQueue<Integer> queue =
     *             new ArrayBlockingQueue<>(2);
     *
     *         Thread producer = new Thread(new Producer(queue));
     *
     *         Thread consumer = new Thread(new Consumer(queue));
     *
     *         producer.start();
     *         consumer.start();
     *     }
     * }
     *
     * IMPORTANT:
     *
     * The queue capacity is only 2.
     *
     * The producer may wait when the queue is full.
     *
     * The consumer may wait when the queue is empty.
     *
     * This example demonstrates basic coordination.
     *
     *
     * ============================================================
     * 21. INTERRUPTED EXCEPTION
     * ============================================================
     *
     * Blocking operations such as:
     *
     * - put().
     * - take().
     * - timed offer().
     * - timed poll().
     *
     * Can throw InterruptedException.
     *
     * Interruption is a cooperative cancellation mechanism.
     *
     * When InterruptedException is caught and the method
     * cannot propagate it, a common practice is:
     *
     * Thread.currentThread().interrupt();
     *
     * This restores the thread's interrupt status.
     *
     * IMPORTANT:
     *
     * Do not silently ignore InterruptedException.
     *
     * Handle interruption according to the application's
     * cancellation and shutdown requirements.
     *
     *
     * ============================================================
     * 22. BLOCKINGQUEUE NULL HANDLING
     * ============================================================
     *
     * BlockingQueue implementations do not permit null elements.
     *
     * Why?
     *
     * poll() uses null to indicate that no element is available.
     *
     * If null were a valid element, the result would be ambiguous.
     *
     *
     * ============================================================
     * 23. BLOCKINGQUEUE IMPLEMENTATIONS COMPARISON
     * ============================================================
     *
     * ArrayBlockingQueue:
     *     -> Bounded, array-based.
     *
     * LinkedBlockingQueue:
     *     -> Linked-node-based, optionally bounded.
     *
     * PriorityBlockingQueue:
     *     -> Priority-based, logically unbounded.
     *
     * DelayQueue:
     *     -> Elements become available after their delays expire.
     *
     * SynchronousQueue:
     *     -> Zero-capacity direct handoff.
     *
     * LinkedTransferQueue:
     *     -> Supports transfer and concurrent queue operations.
     *
     *
     * ============================================================
     * 24. BLOCKINGQUEUE VS CONCURRENTLINKEDQUEUE
     * ============================================================
     *
     * BlockingQueue:
     *
     * - Provides put() and take().
     * - Can wait for capacity or availability.
     * - Useful for producer-consumer coordination.
     * - Implementations may be bounded or unbounded.
     *
     * ConcurrentLinkedQueue:
     *
     * - Non-blocking queue operations.
     * - poll() returns null when empty.
     * - Does not wait for a producer to add an element.
     * - Unbounded.
     *
     * Choose based on whether consumers and producers
     * should wait for one another.
     *
     *
     * ============================================================
     * 25. WHEN SHOULD WE USE BLOCKING QUEUES?
     * ============================================================
     *
     * Use BlockingQueue when:
     *
     * - Producers and consumers run independently.
     * - Consumers should wait for new tasks.
     * - Producers should wait when capacity is exhausted.
     * - You need a producer-consumer pipeline.
     *
     * Real-world examples:
     *
     * - Background job processing.
     * - Message processing.
     * - Worker thread communication.
     * - Task scheduling.
     * - Bounded request buffering.
     *
     *
     * ============================================================
     * 26. COMMON MISTAKES
     * ============================================================
     *
     * 1. Assuming every BlockingQueue is bounded.
     *
     * 2. Assuming PriorityBlockingQueue blocks when full.
     *
     * 3. Assuming SynchronousQueue stores elements.
     *
     * 4. Confusing put() with transfer().
     *
     * 5. Ignoring InterruptedException.
     *
     * 6. Assuming DelayQueue elements are immediately available.
     *
     * 7. Assuming BlockingQueue permits null elements.
     *
     * 8. Using an unbounded queue without considering memory growth.
     *
     * 9. Assuming priority queues preserve FIFO order
     *    for equal-priority elements.
     *
     *
     * ============================================================
     * 27. IMPORTANT INTERVIEW QUESTIONS
     * ============================================================
     *
     * 1. What is BlockingQueue?
     *
     * 2. What are the four groups of BlockingQueue methods?
     *
     * 3. Difference between add(), offer() and put()?
     *
     * 4. Difference between remove(), poll() and take()?
     *
     * 5. What is ArrayBlockingQueue?
     *
     * 6. What is LinkedBlockingQueue?
     *
     * 7. Difference between ArrayBlockingQueue and
     *    LinkedBlockingQueue?
     *
     * 8. What is PriorityBlockingQueue?
     *
     * 9. Is PriorityBlockingQueue bounded?
     *
     * 10. What is DelayQueue?
     *
     * 11. What is the Delayed interface?
     *
     * 12. What is SynchronousQueue?
     *
     * 13. Why is SynchronousQueue called zero-capacity?
     *
     * 14. What is TransferQueue?
     *
     * 15. Difference between put() and transfer()?
     *
     * 16. Explain the Producer-Consumer problem.
     *
     * 17. Why does BlockingQueue reject null?
     *
     * 18. Why must InterruptedException be handled?
     *
     *
     * ============================================================
     * 28. QUICK REVISION
     * ============================================================
     *
     * BlockingQueue:
     *     -> Queue supporting blocking operations.
     *
     * put():
     *     -> Waits for available capacity.
     *
     * take():
     *     -> Waits for an available element.
     *
     * offer():
     *     -> Inserts if possible without waiting.
     *
     * poll():
     *     -> Retrieves immediately or returns null.
     *
     * ArrayBlockingQueue:
     *     -> Fixed-capacity circular-array queue.
     *
     * LinkedBlockingQueue:
     *     -> Linked-node blocking queue.
     *
     * PriorityBlockingQueue:
     *     -> Priority-based logically unbounded queue.
     *
     * DelayQueue:
     *     -> Makes elements available after their delay.
     *
     * SynchronousQueue:
     *     -> Direct handoff with zero storage capacity.
     *
     * TransferQueue:
     *     -> Supports waiting for another thread to receive
     *        a transferred element.
     *
     * Producer:
     *     -> Creates and inserts tasks.
     *
     * Consumer:
     *     -> Retrieves and processes tasks.
     *
     * ============================================================
     * END OF BLOCKING QUEUES
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Blocking Queues , Happy Learning ..!");
    }
}
