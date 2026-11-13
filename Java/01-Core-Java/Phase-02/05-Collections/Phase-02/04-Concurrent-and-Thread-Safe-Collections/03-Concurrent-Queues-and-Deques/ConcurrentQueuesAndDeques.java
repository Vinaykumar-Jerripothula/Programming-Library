
public class ConcurrentQueuesAndDeques {

    /*
     * ============================================================
     * CONCURRENT QUEUES AND DEQUES IN JAVA
     * ============================================================
     *
     * 1. WHY DO WE NEED CONCURRENT QUEUES?
     *
     * Imagine an application where multiple threads produce
     * tasks and multiple threads consume those tasks.
     *
     * Producer threads:
     *     Thread A -> Adds task 1.
     *     Thread B -> Adds task 2.
     *     Thread C -> Adds task 3.
     *
     * Consumer threads:
     *     Thread X -> Retrieves a task.
     *     Thread Y -> Retrieves a task.
     *
     * All threads access the same queue.
     *
     * An ordinary LinkedList or ArrayDeque is not designed
     * for concurrent modification without coordination.
     *
     * Java provides concurrent queue implementations
     * for such situations.
     *
     *
     * ============================================================
     * 2. WHAT IS A CONCURRENT QUEUE?
     * ============================================================
     *
     * A concurrent queue is a queue designed to support
     * concurrent operations by multiple threads.
     *
     * It provides thread-safe queue operations.
     *
     * Java provides important concurrent queue implementations:
     *
     * 1. ConcurrentLinkedQueue.
     * 2. ConcurrentLinkedDeque.
     *
     * Both are non-blocking concurrent data structures.
     *
     *
     * ============================================================
     * 3. WHAT IS NON-BLOCKING?
     * ============================================================
     *
     * Non-blocking means an operation does not wait for
     * another thread to release a lock before making progress.
     *
     * ConcurrentLinkedQueue and ConcurrentLinkedDeque use
     * non-blocking algorithms based on atomic operations.
     *
     * They commonly use CAS (Compare-And-Set) operations.
     *
     * IMPORTANT:
     *
     * Non-blocking does not mean every operation completes
     * immediately.
     *
     * An operation may retry when it detects interference
     * from another thread.
     *
     *
     * ============================================================
     * 4. CONCURRENTLINKEDQUEUE
     * ============================================================
     *
     * ConcurrentLinkedQueue is a thread-safe,
     * unbounded FIFO queue.
     *
     * FIFO:
     * First In, First Out.
     *
     * Example:
     *
     * ConcurrentLinkedQueue<Integer> queue =
     *     new ConcurrentLinkedQueue<>();
     *
     * queue.offer(10);
     * queue.offer(20);
     * queue.offer(30);
     *
     * queue.poll(); // 10
     * queue.poll(); // 20
     *
     * queue.peek(); // 30
     *
     * It supports concurrent insertion and removal.
     *
     *
     * ============================================================
     * 5. CONCURRENTLINKEDQUEUE INTERNAL WORKING
     * ============================================================
     *
     * ConcurrentLinkedQueue is based on a linked-node structure.
     *
     * Conceptual node:
     *
     * class Node<E> {
     *     volatile E item;
     *     volatile Node<E> next;
     * }
     *
     * A node contains:
     *
     * - Element.
     * - Reference to the next node.
     *
     * Conceptual structure:
     *
     * HEAD
     *  |
     *  v
     * [10] -> [20] -> [30] -> null
     *
     * The queue maintains references that help locate
     * the beginning and end of the linked structure.
     *
     * IMPORTANT:
     *
     * This is a conceptual representation.
     * Exact internal node management is an implementation detail.
     *
     *
     * ============================================================
     * 6. CONCURRENTLINKEDQUEUE - OFFER
     * ============================================================
     *
     * offer() inserts an element into the queue.
     *
     * Example:
     *
     * queue.offer(40);
     *
     * Conceptual steps:
     *
     * 1. Locate the current tail area.
     * 2. Prepare a new node.
     * 3. Use an atomic operation to link the new node.
     * 4. Retry if another thread changes the expected state.
     *
     * Multiple threads can attempt insertion concurrently.
     *
     * Time complexity:
     *
     * Usually O(1) expected.
     *
     * The precise implementation and cost can depend
     * on contention and runtime conditions.
     *
     *
     * ============================================================
     * 7. CONCURRENTLINKEDQUEUE - POLL
     * ============================================================
     *
     * poll() retrieves and removes the head element.
     *
     * Example:
     *
     * queue.poll();
     *
     * Conceptual steps:
     *
     * 1. Read the current head.
     * 2. Check whether an element is available.
     * 3. Attempt an atomic update to claim/remove it.
     * 4. Retry if another thread wins the race.
     *
     * If the queue is empty:
     *
     * poll() returns null.
     *
     *
     * ============================================================
     * 8. CONCURRENTLINKEDQUEUE - PEEK
     * ============================================================
     *
     * peek() returns the head element without removing it.
     *
     * Example:
     *
     * queue.peek();
     *
     * If the queue is empty:
     *
     * Returns null.
     *
     *
     * ============================================================
     * 9. CONCURRENTLINKEDQUEUE METHODS
     * ============================================================
     *
     * offer(e):
     * Inserts an element.
     *
     * add(e):
     * Inserts an element.
     *
     * poll():
     * Retrieves and removes the head.
     *
     * peek():
     * Retrieves but does not remove the head.
     *
     * remove():
     * Retrieves and removes the head.
     * Throws NoSuchElementException if empty.
     *
     * element():
     * Retrieves but does not remove the head.
     * Throws NoSuchElementException if empty.
     *
     * isEmpty():
     * Checks whether the queue is empty.
     *
     * size():
     * Returns the number of elements.
     *
     * contains():
     * Checks whether an element exists.
     *
     * IMPORTANT:
     *
     * ConcurrentLinkedQueue does not permit null elements.
     *
     *
     * ============================================================
     * 10. CONCURRENTLINKEDQUEUE TIME COMPLEXITY
     * ============================================================
     *
     * offer():
     * Expected O(1).
     *
     * poll():
     * Expected O(1).
     *
     * peek():
     * O(1).
     *
     * contains():
     * O(n).
     *
     * remove(Object):
     * O(n).
     *
     * size():
     * O(n).
     *
     * IMPORTANT:
     *
     * size() may require traversal and is not constant-time.
     *
     * Under concurrent modification, its result may be
     * inaccurate as an instantaneous count.
     *
     *
     * ============================================================
     * 11. CONCURRENTLINKEDDEQUE
     * ============================================================
     *
     * ConcurrentLinkedDeque is a thread-safe,
     * unbounded concurrent Deque.
     *
     * Deque means Double Ended Queue.
     *
     * Elements can be inserted and removed at both ends.
     *
     * Example:
     *
     * ConcurrentLinkedDeque<Integer> deque =
     *     new ConcurrentLinkedDeque<>();
     *
     * deque.offerFirst(10);
     * deque.offerLast(20);
     *
     * deque.pollFirst(); // 10
     * deque.pollLast();  // 20
     *
     * It supports concurrent access from multiple threads.
     *
     *
     * ============================================================
     * 12. CONCURRENTLINKEDDEQUE OPERATIONS
     * ============================================================
     *
     * Insert at beginning:
     *
     * offerFirst(e)
     * addFirst(e)
     *
     * Insert at end:
     *
     * offerLast(e)
     * addLast(e)
     *
     * Remove from beginning:
     *
     * pollFirst()
     * removeFirst()
     *
     * Remove from end:
     *
     * pollLast()
     * removeLast()
     *
     * Examine beginning:
     *
     * peekFirst()
     * getFirst()
     *
     * Examine end:
     *
     * peekLast()
     * getLast()
     *
     * Null behavior:
     *
     * Null elements are not permitted.
     *
     *
     * ============================================================
     * 13. CONCURRENTLINKEDDEQUE INTERNAL WORKING
     * ============================================================
     *
     * It uses a concurrent doubly linked structure.
     *
     * Conceptual node:
     *
     * class Node<E> {
     *     volatile E item;
     *     volatile Node<E> prev;
     *     volatile Node<E> next;
     * }
     *
     * Conceptual structure:
     *
     * null <- [10] <-> [20] <-> [30] -> null
     *
     * It supports operations at both ends.
     *
     * Atomic operations and retry mechanisms help coordinate
     * concurrent modifications.
     *
     * IMPORTANT:
     *
     * The exact node layout and algorithms are implementation
     * details and may vary across JDK versions.
     *
     *
     * ============================================================
     * 14. CONCURRENTLINKEDDEQUE VS CONCURRENTLINKEDQUEUE
     * ============================================================
     *
     * ConcurrentLinkedQueue:
     *
     * - FIFO queue.
     * - Insert at the tail.
     * - Remove from the head.
     * - Singly linked structure.
     *
     * ConcurrentLinkedDeque:
     *
     * - Double-ended queue.
     * - Insert at both ends.
     * - Remove from both ends.
     * - Doubly linked structure.
     *
     * Use ConcurrentLinkedQueue when ordinary FIFO behavior
     * is sufficient.
     *
     * Use ConcurrentLinkedDeque when both ends are needed.
     *
     *
     * ============================================================
     * 15. WHAT IS A WEAKLY CONSISTENT ITERATOR?
     * ============================================================
     *
     * ConcurrentLinkedQueue and ConcurrentLinkedDeque provide
     * weakly consistent iterators.
     *
     * A weakly consistent iterator:
     *
     * - Does not throw ConcurrentModificationException
     *   merely because another thread modifies the collection.
     *
     * - May reflect some modifications made after the
     *   iterator was created.
     *
     * - Does not necessarily represent one fixed snapshot.
     *
     * This allows traversal to proceed alongside concurrent
     * modifications.
     *
     *
     * ============================================================
     * 16. CONCURRENT QUEUES VS BLOCKING QUEUES
     * ============================================================
     *
     * ConcurrentLinkedQueue:
     *
     * If the queue is empty:
     * poll() returns null.
     *
     * It does not wait for an element to arrive.
     *
     * BlockingQueue:
     *
     * take() waits when the queue is empty.
     *
     * put() may wait when the queue is full.
     *
     * IMPORTANT:
     *
     * ConcurrentLinkedQueue is non-blocking and unbounded.
     *
     * BlockingQueue provides blocking operations and may
     * be bounded or unbounded depending on implementation.
     *
     *
     * ============================================================
     * 17. PRODUCER-CONSUMER USING CONCURRENTLINKEDQUEUE
     * ============================================================
     *
     * Producer:
     *
     * Adds elements using offer().
     *
     * Consumer:
     *
     * Retrieves elements using poll().
     *
     * Example:
     *
     * ConcurrentLinkedQueue<String> queue =
     *     new ConcurrentLinkedQueue<>();
     *
     * // Producer
     * queue.offer("Task-1");
     * queue.offer("Task-2");
     *
     * // Consumer
     * String task;
     *
     * while ((task = queue.poll()) != null) {
     *     System.out.println(task);
     * }
     *
     * IMPORTANT:
     *
     * This example demonstrates the queue operations.
     * It does not demonstrate waiting for new tasks.
     *
     * A consumer that needs to wait for tasks should consider
     * a BlockingQueue.
     *
     *
     * ============================================================
     * 18. WHEN SHOULD WE USE CONCURRENTLINKEDQUEUE?
     * ============================================================
     *
     * Use it when:
     *
     * - Multiple threads add and remove queue elements.
     * - FIFO processing is required.
     * - Non-blocking operations are desirable.
     * - An unbounded queue is acceptable.
     * - Consumers do not need to wait for new elements.
     *
     * Examples:
     *
     * - Concurrent task handoff.
     * - Event processing.
     * - Message buffering.
     * - Multi-threaded work pipelines.
     *
     *
     * ============================================================
     * 19. WHEN SHOULD WE USE CONCURRENTLINKEDDEQUE?
     * ============================================================
     *
     * Use it when:
     *
     * - Multiple threads need access to both ends.
     * - Concurrent insertion/removal at either end is required.
     * - Non-blocking operations are suitable.
     * - An unbounded deque is acceptable.
     *
     *
     * ============================================================
     * 20. IMPORTANT COMPARISON
     * ============================================================
     *
     * ArrayDeque:
     *
     * - Not thread-safe by itself.
     * - Resizable circular array.
     *
     * LinkedList:
     *
     * - Not thread-safe by itself.
     * - Doubly linked list.
     *
     * ConcurrentLinkedQueue:
     *
     * - Thread-safe FIFO queue.
     * - Non-blocking.
     * - Linked-node structure.
     *
     * ConcurrentLinkedDeque:
     *
     * - Thread-safe double-ended queue.
     * - Non-blocking.
     * - Concurrent doubly linked structure.
     *
     * LinkedBlockingQueue:
     *
     * - Blocking queue.
     * - Supports waiting operations.
     * - Can be bounded.
     *
     *
     * ============================================================
     * 21. COMMON MISTAKES
     * ============================================================
     *
     * 1. Assuming ConcurrentLinkedQueue blocks when empty.
     *
     * 2. Assuming concurrent means blocking.
     *
     * 3. Assuming non-blocking means zero waiting or retries.
     *
     * 4. Assuming size() is O(1).
     *
     * 5. Assuming weakly consistent iterators are snapshots.
     *
     * 6. Assuming ConcurrentLinkedQueue permits null.
     *
     * 7. Using a concurrent queue when a bounded queue
     *    is required.
     *
     * 8. Assuming a consumer using poll() automatically waits
     *    for future tasks.
     *
     *
     * ============================================================
     * 22. IMPORTANT INTERVIEW QUESTIONS
     * ============================================================
     *
     * 1. What is a concurrent queue?
     *
     * 2. What is ConcurrentLinkedQueue?
     *
     * 3. Is ConcurrentLinkedQueue thread-safe?
     *
     * 4. What does non-blocking mean?
     *
     * 5. How does ConcurrentLinkedQueue handle concurrent updates?
     *
     * 6. What is CAS?
     *
     * 7. Difference between poll() and remove()?
     *
     * 8. Why does ConcurrentLinkedQueue not permit null?
     *
     * 9. What is a weakly consistent iterator?
     *
     * 10. What is ConcurrentLinkedDeque?
     *
     * 11. Difference between ConcurrentLinkedQueue
     *     and ConcurrentLinkedDeque?
     *
     * 12. Difference between ConcurrentLinkedQueue
     *     and BlockingQueue?
     *
     * 13. Why is size() expensive in ConcurrentLinkedQueue?
     *
     * 14. When would you use a concurrent deque?
     *
     *
     * ============================================================
     * 23. QUICK REVISION
     * ============================================================
     *
     * Concurrent Queue:
     *     -> Queue designed for concurrent access.
     *
     * ConcurrentLinkedQueue:
     *     -> Unbounded, thread-safe FIFO queue.
     *
     * ConcurrentLinkedDeque:
     *     -> Unbounded, thread-safe double-ended queue.
     *
     * Non-blocking:
     *     -> Does not wait for another thread to release
     *        a lock before making progress.
     *
     * CAS:
     *     -> Atomic compare-and-set operation.
     *
     * offer():
     *     -> Inserts an element.
     *
     * poll():
     *     -> Removes and returns the head, or null.
     *
     * peek():
     *     -> Returns the head without removing it.
     *
     * Weakly consistent iterator:
     *     -> Can traverse while concurrent modifications occur.
     *
     * BlockingQueue:
     *     -> Provides operations that can wait for availability
     *        or capacity.
     *
     * ============================================================
     * END OF CONCURRENT QUEUES AND DEQUES
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Concurrent Queues and Deques , Happy Learning ..!");
    }
}
