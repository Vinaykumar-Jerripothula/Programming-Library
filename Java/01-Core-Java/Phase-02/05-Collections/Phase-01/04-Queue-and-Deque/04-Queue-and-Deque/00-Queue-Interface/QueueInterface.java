
public class QueueInterface {

    /*
     * ============================================================
     *                  QUEUE INTERFACE IN JAVA
     * ============================================================
     *
     * 1. WHAT IS A QUEUE?
     *
     * Imagine people standing in a line to buy movie tickets.
     *
     * The person who enters the line first gets the ticket first.
     * A person joining later must wait behind the existing people.
     *
     * Java Queue follows a similar idea.
     *
     * A Queue is a collection designed to hold elements before
     * they are processed.
     *
     * It generally follows FIFO:
     * FIFO = First In, First Out.
     *
     * Example:
     *
     * Insert: 10, 20, 30, 40
     *
     * Front                          Rear
     *   |                              |
     *   v                              v
     *  [10] -> [20] -> [30] -> [40]
     *
     * Removal order:
     * 10 -> 20 -> 30 -> 40
     *
     * The element inserted first is normally processed first.
     *
     *
     * 2. WHY DO WE NEED A QUEUE?
     *
     * Consider a printer receiving three print requests:
     *
     * 1. Document A
     * 2. Document B
     * 3. Document C
     *
     * The printer should normally process them in the order
     * in which they arrived.
     *
     * A Queue is useful for this kind of processing.
     *
     * Real-world applications:
     *
     * - Printer job scheduling
     * - Task scheduling
     * - Request processing
     * - Breadth-First Search (BFS)
     * - Message processing
     * - Handling incoming customer requests
     * - Producer-consumer applications
     *
     *
     * 3. QUEUE IS AN INTERFACE
     *
     * Queue is an interface available in java.util.
     *
     * It extends the Collection interface.
     *
     * Collection
     *     |
     *    Queue
     *     |
     * Implementations
     *
     * Common implementations:
     *
     * - PriorityQueue
     * - LinkedList
     * - ArrayDeque (through Deque)
     *
     * Concurrent implementations:
     *
     * - ConcurrentLinkedQueue
     * - ArrayBlockingQueue
     * - LinkedBlockingQueue
     * - PriorityBlockingQueue
     *
     * Import:
     *
     * import java.util.Queue;
     * import java.util.LinkedList;
     *
     * Example:
     *
     * Queue<Integer> queue = new LinkedList<>();
     *
     * We use the Queue reference type and an implementation
     * to create the actual object.
     *
     * This is programming to an interface.
     *
     *
     * 4. IMPORTANT QUEUE METHODS
     *
     * Queue provides six primary methods for inserting,
     * removing and examining the head element.
     *
     * A. INSERTION
     *
     * add(E element)
     *
     * - Inserts an element into the queue.
     * - Returns true when insertion succeeds.
     * - Throws IllegalStateException if a capacity restriction
     *   prevents insertion.
     *
     * offer(E element)
     *
     * - Also inserts an element.
     * - Returns true when insertion succeeds.
     * - Returns false if a capacity restriction prevents insertion.
     *
     * Difference:
     *
     * add()   -> May throw an exception when insertion fails.
     * offer() -> Returns false when insertion fails.
     *
     * Example:
     *
     * queue.add(10);
     * queue.offer(20);
     *
     * Prefer offer() when working with a capacity-restricted
     * queue and you want to handle insertion failure normally.
     *
     *
     * B. REMOVAL
     *
     * remove()
     *
     * - Removes and returns the head element.
     * - Throws NoSuchElementException if the queue is empty.
     *
     * poll()
     *
     * - Removes and returns the head element.
     * - Returns null if the queue is empty.
     *
     * Difference:
     *
     * remove() -> Exception when empty.
     * poll()   -> null when empty.
     *
     * Example:
     *
     * Integer first = queue.poll();
     *
     * if (first == null) {
     *     System.out.println("Queue is empty");
     * }
     *
     * Important:
     * poll() returning null can indicate an empty queue.
     * This is why many Queue implementations prohibit null elements.
     *
     *
     * C. EXAMINING THE HEAD
     *
     * element()
     *
     * - Returns the head element without removing it.
     * - Throws NoSuchElementException if the queue is empty.
     *
     * peek()
     *
     * - Returns the head element without removing it.
     * - Returns null if the queue is empty.
     *
     * Difference:
     *
     * element() -> Exception when empty.
     * peek()    -> null when empty.
     *
     * Example:
     *
     * Integer first = queue.peek();
     *
     * The element remains inside the queue.
     *
     *
     * 5. THE SIX METHODS AT A GLANCE
     *
     * Operation       Exception-based     Special-value-based
     *
     * Insert          add(e)              offer(e)
     * Remove head     remove()            poll()
     * Examine head    element()           peek()
     *
     * Remember:
     *
     * add() and offer() insert.
     * remove() and poll() remove.
     * element() and peek() only examine.
     *
     * The special-value methods use false or null when the
     * operation cannot be completed.
     *
     *
     * 6. COMPLETE QUEUE EXAMPLE
     *
     * import java.util.Queue;
     * import java.util.LinkedList;
     *
     * public class QueueExample {
     *
     *     public static void main(String[] args) {
     *
     *         Queue<Integer> queue = new LinkedList<>();
     *
     *         // Insertion
     *         queue.offer(10);
     *         queue.offer(20);
     *         queue.offer(30);
     *
     *         System.out.println(queue);
     *
     *         // Examine head
     *         System.out.println(queue.peek());
     *
     *         // Remove head
     *         System.out.println(queue.poll());
     *
     *         // Examine new head
     *         System.out.println(queue.peek());
     *
     *         // Remove remaining elements
     *         while (!queue.isEmpty()) {
     *             System.out.println(queue.poll());
     *         }
     *
     *         System.out.println(queue.isEmpty());
     *     }
     * }
     *
     * Output:
     *
     * [10, 20, 30]
     * 10
     * 10
     * 20
     * 20
     * 30
     * true
     *
     *
     * 7. QUEUE DOES NOT ALWAYS MEAN FIFO
     *
     * FIFO is the most common Queue behavior, but the Queue
     * interface does not force every implementation to use FIFO.
     *
     * Example: PriorityQueue
     *
     * A PriorityQueue processes elements according to priority
     * rather than insertion order.
     *
     * Example:
     *
     * Insert: 40, 10, 30, 20
     *
     * With natural integer ordering, the elements are retrieved
     * in this order:
     *
     * 10 -> 20 -> 30 -> 40
     *
     * Even though 40 was inserted first, 10 is retrieved first.
     *
     * We will study PriorityQueue separately.
     *
     *
     * 8. QUEUE DOES NOT PROVIDE INDEX-BASED ACCESS
     *
     * List provides index-based access:
     *
     * list.get(2);
     *
     * Queue does not provide get(index).
     *
     * Why?
     *
     * Queue is designed around processing elements at its head,
     * not accessing arbitrary positions.
     *
     * If you need to inspect all elements, you can use iteration.
     *
     *
     * 9. ITERATING OVER A QUEUE
     *
     * A Queue can be traversed using an Iterator or enhanced
     * for loop.
     *
     * Example:
     *
     * Queue<Integer> queue = new LinkedList<>();
     *
     * queue.offer(10);
     * queue.offer(20);
     * queue.offer(30);
     *
     * for (Integer value : queue) {
     *     System.out.println(value);
     * }
     *
     * Important:
     *
     * The iteration order depends on the implementation.
     * For example, LinkedList iterates in its linked-list order,
     * while PriorityQueue's iterator is not guaranteed to return
     * elements in priority order.
     *
     * To retrieve elements according to the queue's head-removal
     * behavior, repeatedly use poll().
     *
     * Be aware that poll() modifies the queue.
     *
     *
     * 10. remove() vs remove(Object)
     *
     * Queue inherits overloaded remove methods from Collection.
     *
     * remove()
     *
     * Removes and returns the head element.
     *
     * remove(Object object)
     *
     * Removes one matching occurrence of the specified object.
     * Returns true if an element was removed.
     *
     * Example:
     *
     * queue.remove();       // Removes the head.
     * queue.remove(30);     // Removes the matching value 30.
     *
     * Do not confuse these two methods.
     *
     *
     * 11. NULL ELEMENTS IN QUEUE
     *
     * The Queue interface does not universally prohibit null.
     *
     * However, many commonly used implementations reject null:
     *
     * - PriorityQueue
     * - ArrayDeque
     * - ConcurrentLinkedQueue
     * - BlockingQueue implementations
     *
     * LinkedList permits null elements.
     *
     * Why do many implementations reject null?
     *
     * Because poll() and peek() return null when the queue is
     * empty. If null were also a valid element, it could become
     * difficult to distinguish an empty queue from a null head.
     *
     * Best practice:
     * Avoid inserting null into queues.
     *
     *
     * 12. QUEUE VS LIST
     *
     * List:
     *
     * - Maintains an ordered sequence.
     * - Supports index-based access.
     * - Allows duplicate elements.
     * - Provides methods such as get(index) and set(index, value).
     *
     * Queue:
     *
     * - Designed for processing elements.
     * - Provides head-oriented operations.
     * - Does not provide index-based access.
     * - Ordering depends on the implementation.
     *
     *
     * 13. QUEUE VS DEQUE
     *
     * Queue:
     *
     * - Primarily designed for insertion and removal at the
     *   queue's tail and head.
     *
     * Deque:
     *
     * - Double-ended queue.
     * - Supports insertion and removal at both ends.
     * - Can behave as a FIFO queue or a LIFO stack.
     *
     * We will study Deque separately.
     *
     *
     * 14. QUEUE VS BLOCKINGQUEUE
     *
     * Queue:
     *
     * - Provides ordinary insertion, removal and examination.
     * - Does not provide waiting behavior by itself.
     *
     * BlockingQueue:
     *
     * - Part of java.util.concurrent.
     * - Supports operations that can wait when the queue is
     *   empty or full.
     * - Useful in producer-consumer applications.
     *
     * Example methods:
     *
     * put()     -> Waits if the queue is full.
     * take()    -> Waits if the queue is empty.
     * offer()   -> Can use a timeout in BlockingQueue overloads.
     * poll()    -> Can use a timeout in BlockingQueue overloads.
     *
     * BlockingQueue will be covered in the concurrent collections
     * section.
     *
     *
     * 15. TIME COMPLEXITY
     *
     * Queue is an interface, so it does not guarantee a particular
     * time complexity for its operations.
     *
     * Complexity depends on the implementation.
     *
     * For a typical linked queue:
     *
     * offer()  -> O(1)
     * poll()   -> O(1)
     * peek()   -> O(1)
     *
     * For PriorityQueue:
     *
     * offer()  -> O(log n)
     * poll()   -> O(log n)
     * peek()   -> O(1)
     *
     * These are implementation-specific complexities.
     *
     *
     * 16. COMMON MISTAKES
     *
     * 1. Assuming every Queue follows FIFO.
     *    PriorityQueue is based on priority.
     *
     * 2. Confusing peek() with poll().
     *    peek() only reads; poll() removes.
     *
     * 3. Confusing remove() with remove(Object).
     *    They are different overloaded methods.
     *
     * 4. Assuming Queue supports get(index).
     *    Queue has no index-based access method.
     *
     * 5. Assuming all Queue implementations allow null.
     *    Many reject null.
     *
     * 6. Assuming iteration always follows removal order.
     *    This is not guaranteed for every implementation.
     *
     * 7. Assuming Queue itself is thread-safe.
     *    Thread safety depends on the implementation.
     *
     *
     * 17. INTERVIEW QUESTIONS
     *
     * 1. What is the Queue interface in Java?
     * 2. Which interface does Queue extend?
     * 3. What is FIFO?
     * 4. Does every Queue implementation follow FIFO?
     * 5. What is the difference between add() and offer()?
     * 6. What is the difference between remove() and poll()?
     * 7. What is the difference between element() and peek()?
     * 8. Why do many Queue implementations prohibit null?
     * 9. Does Queue support index-based access?
     * 10. Is Queue thread-safe?
     * 11. What is the difference between Queue and Deque?
     * 12. What is the difference between Queue and BlockingQueue?
     * 13. What is the difference between remove() and
     *     remove(Object)?
     * 14. Does iterating over a PriorityQueue produce sorted order?
     * 15. Which Queue implementation processes elements by priority?
     *
     *
     * 18. QUICK REVISION
     *
     * - Queue is an interface in java.util.
     * - Queue extends Collection.
     * - FIFO is common, but not mandatory for every implementation.
     * - offer() inserts and can return false.
     * - add() inserts and can throw IllegalStateException.
     * - poll() removes the head and returns null when empty.
     * - remove() removes the head and throws when empty.
     * - peek() reads the head and returns null when empty.
     * - element() reads the head and throws when empty.
     * - Queue has no index-based access.
     * - Null support depends on the implementation.
     * - Thread safety depends on the implementation.
     *
     * ============================================================
     *                    END OF QUEUE INTERFACE
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Queue Interface , Happy Learning ..!");
    }
}
