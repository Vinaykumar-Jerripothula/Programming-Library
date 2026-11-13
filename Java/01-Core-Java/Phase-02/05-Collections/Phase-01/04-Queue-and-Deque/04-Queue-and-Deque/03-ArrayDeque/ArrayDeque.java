
public class ArrayDeque {

    /*
     * ============================================================
     *                     ARRAY DEQUE IN JAVA
     * ============================================================
     *
     * 1. WHAT IS ARRAYDEQUE?
     *
     * ArrayDeque is a resizable-array implementation of the
     * Deque interface.
     *
     * Remember:
     *
     * Deque is an interface.
     * ArrayDeque is a class that implements Deque.
     *
     * It allows us to insert and remove elements from both ends:
     *
     * - Front
     * - Rear
     *
     * Example:
     *
     * Front                         Rear
     *   |                              |
     *   v                              v
     *  [10] <-> [20] <-> [30] <-> [40]
     *
     * We can insert or remove elements from either side.
     *
     * ArrayDeque can behave as:
     *
     * - A Queue (FIFO)
     * - A Stack (LIFO)
     * - A Double-Ended Queue
     *
     * Package:
     *
     * java.util
     *
     * Declaration:
     *
     * import java.util.ArrayDeque;
     *
     * ArrayDeque<Integer> deque = new ArrayDeque<>();
     *
     *
     * 2. WHY DO WE NEED ARRAYDEQUE?
     *
     * Java provides different implementations for Queue and Deque.
     *
     * ArrayDeque is useful when we need efficient operations
     * at both ends without the overhead of linked-list nodes.
     *
     * Common reasons to use it:
     *
     * - Implementing a stack.
     * - Implementing a FIFO queue.
     * - Sliding-window algorithms.
     * - Undo/redo-style operations.
     * - Processing elements from either end.
     *
     * For ordinary single-threaded stack and queue operations,
     * ArrayDeque is often a good default choice.
     *
     *
     * 3. CREATING AN ARRAYDEQUE
     *
     * Default constructor:
     *
     * ArrayDeque<Integer> deque = new ArrayDeque<>();
     *
     * Constructor with initial capacity:
     *
     * ArrayDeque<Integer> deque = new ArrayDeque<>(20);
     *
     * Constructor using another collection:
     *
     * Collection<Integer> values = List.of(10, 20, 30);
     *
     * ArrayDeque<Integer> deque = new ArrayDeque<>(values);
     *
     * Important:
     *
     * The initial capacity is not a maximum capacity.
     * ArrayDeque grows automatically when required.
     *
     *
     * 4. INTERNAL DATA STRUCTURE
     *
     * ArrayDeque uses a resizable circular array.
     *
     * What is a circular array?
     *
     * Imagine an ordinary array with a fixed number of positions.
     *
     * In a normal linear arrangement, after reaching the final
     * position, we cannot continue at the beginning.
     *
     * A circular array treats the last position as connected
     * to the first position.
     *
     * Example:
     *
     *       0    1    2    3    4
     *     +----+----+----+----+----+
     *     | 10 | 20 | 30 |    |    |
     *     +----+----+----+----+----+
     *
     * If the rear reaches the last array position, it can wrap
     * around to the beginning when space is available.
     *
     * This allows efficient use of available array positions.
     *
     * Conceptually, ArrayDeque maintains:
     *
     * - An array to store elements.
     * - A head position representing the front.
     * - A tail position representing the next insertion position
     *   at the rear.
     *
     * These are conceptual roles; exact internal implementation
     * details can vary by Java version.
     *
     *
     * 5. HOW CIRCULAR ARRAY INDEXING WORKS
     *
     * Suppose we have an array of length 5.
     *
     * Valid indexes:
     *
     * 0, 1, 2, 3, 4
     *
     * If an index moves beyond 4, it should wrap around to 0.
     *
     * Conceptually:
     *
     * nextIndex = (currentIndex + 1) % arrayLength
     *
     * Example:
     *
     * currentIndex = 4
     * arrayLength = 5
     *
     * nextIndex = (4 + 1) % 5
     *           = 0
     *
     * Similarly, moving backward:
     *
     * previousIndex =
     *     (currentIndex - 1 + arrayLength) % arrayLength
     *
     * Example:
     *
     * currentIndex = 0
     * arrayLength = 5
     *
     * previousIndex = (0 - 1 + 5) % 5
     *               = 4
     *
     * These formulas explain circular indexing conceptually.
     * Do not assume that every Java version uses these exact
     * expressions internally.
     *
     *
     * 6. INSERTING ELEMENTS AT BOTH ENDS
     *
     * ArrayDeque provides:
     *
     * addFirst(element)
     * offerFirst(element)
     * addLast(element)
     * offerLast(element)
     *
     * Example:
     *
     * ArrayDeque<Integer> deque = new ArrayDeque<>();
     *
     * deque.addLast(20);
     * deque.addLast(30);
     * deque.addFirst(10);
     *
     * Result:
     *
     * [10, 20, 30]
     *
     * Another example:
     *
     * deque.offerFirst(5);
     * deque.offerLast(40);
     *
     * Result:
     *
     * [5, 10, 20, 30, 40]
     *
     * addFirst() and addLast():
     *
     * - Insert at the requested end.
     * - Throw IllegalStateException if insertion cannot
     *   be completed because of capacity restrictions.
     *
     * offerFirst() and offerLast():
     *
     * - Return true when insertion succeeds.
     * - Return false if insertion cannot be completed because
     *   of capacity restrictions.
     *
     * ArrayDeque is resizable and does not have a user-defined
     * fixed maximum capacity.
     *
     *
     * 7. REMOVING ELEMENTS FROM BOTH ENDS
     *
     * ArrayDeque provides:
     *
     * removeFirst()
     * pollFirst()
     * removeLast()
     * pollLast()
     *
     * Example:
     *
     * ArrayDeque<Integer> deque = new ArrayDeque<>();
     *
     * deque.offerLast(10);
     * deque.offerLast(20);
     * deque.offerLast(30);
     *
     * System.out.println(deque.pollFirst()); // 10
     * System.out.println(deque.pollLast());  // 30
     *
     * Remaining:
     *
     * [20]
     *
     * Difference:
     *
     * removeFirst():
     * - Removes the first element.
     * - Throws NoSuchElementException if empty.
     *
     * pollFirst():
     * - Removes the first element.
     * - Returns null if empty.
     *
     * removeLast():
     * - Removes the last element.
     * - Throws NoSuchElementException if empty.
     *
     * pollLast():
     * - Removes the last element.
     * - Returns null if empty.
     *
     *
     * 8. EXAMINING ELEMENTS
     *
     * getFirst()
     * peekFirst()
     * getLast()
     * peekLast()
     *
     * Example:
     *
     * ArrayDeque<Integer> deque = new ArrayDeque<>();
     *
     * deque.offerLast(10);
     * deque.offerLast(20);
     * deque.offerLast(30);
     *
     * System.out.println(deque.getFirst());  // 10
     * System.out.println(deque.peekFirst()); // 10
     *
     * System.out.println(deque.getLast());   // 30
     * System.out.println(deque.peekLast());  // 30
     *
     * These methods do not remove elements.
     *
     * getFirst() and getLast():
     * - Throw NoSuchElementException if empty.
     *
     * peekFirst() and peekLast():
     * - Return null if empty.
     *
     *
     * 9. USING ARRAYDEQUE AS A QUEUE (FIFO)
     *
     * FIFO means First In, First Out.
     *
     * To use ArrayDeque as a Queue:
     *
     * - Insert at the rear.
     * - Remove from the front.
     *
     * Example:
     *
     * ArrayDeque<Integer> queue = new ArrayDeque<>();
     *
     * queue.offerLast(10);
     * queue.offerLast(20);
     * queue.offerLast(30);
     *
     * System.out.println(queue.pollFirst()); // 10
     * System.out.println(queue.pollFirst()); // 20
     * System.out.println(queue.pollFirst()); // 30
     *
     * Output:
     *
     * 10
     * 20
     * 30
     *
     * We can also use the Queue reference:
     *
     * Queue<Integer> queue = new ArrayDeque<>();
     *
     * queue.offer(10);
     * queue.offer(20);
     * queue.poll();
     *
     * The Queue reference exposes Queue operations.
     *
     *
     * 10. USING ARRAYDEQUE AS A STACK (LIFO)
     *
     * LIFO means Last In, First Out.
     *
     * To use ArrayDeque as a Stack:
     *
     * - Push elements at the front.
     * - Pop elements from the front.
     *
     * Example:
     *
     * ArrayDeque<Integer> stack = new ArrayDeque<>();
     *
     * stack.push(10);
     * stack.push(20);
     * stack.push(30);
     *
     * System.out.println(stack.pop()); // 30
     * System.out.println(stack.pop()); // 20
     * System.out.println(stack.pop()); // 10
     *
     * Stack order:
     *
     *          TOP
     *           |
     *          [30]
     *          [20]
     *          [10]
     *
     * push() inserts at the front.
     * pop() removes from the front.
     *
     * ArrayDeque is generally preferred over the legacy Stack
     * class for ordinary single-threaded stack usage.
     *
     *
     * 11. IMPORTANT STACK METHODS
     *
     * push(element)
     *
     * - Inserts at the front.
     * - Equivalent to addFirst(element).
     *
     * pop()
     *
     * - Removes and returns the front element.
     * - Equivalent to removeFirst().
     * - Throws NoSuchElementException if empty.
     *
     * peek()
     *
     * - Examines the front element.
     * - Returns null if empty.
     *
     * Example:
     *
     * ArrayDeque<String> stack = new ArrayDeque<>();
     *
     * stack.push("A");
     * stack.push("B");
     *
     * System.out.println(stack.peek()); // B
     * System.out.println(stack.pop());  // B
     *
     *
     * 12. ARRAYDEQUE DOES NOT ALLOW NULL
     *
     * ArrayDeque prohibits null elements.
     *
     * Example:
     *
     * ArrayDeque<Integer> deque = new ArrayDeque<>();
     *
     * deque.offer(null);
     *
     * This throws NullPointerException.
     *
     * Why?
     *
     * Methods such as poll(), peek(), pollFirst() and peekFirst()
     * return null when no element is available.
     *
     * If null were also a valid element, it could become difficult
     * to distinguish an empty deque from a deque containing null.
     *
     *
     * 13. DUPLICATE ELEMENTS
     *
     * ArrayDeque allows duplicate elements.
     *
     * Example:
     *
     * ArrayDeque<Integer> deque = new ArrayDeque<>();
     *
     * deque.offer(10);
     * deque.offer(10);
     * deque.offer(20);
     *
     * Result:
     *
     * [10, 10, 20]
     *
     * ArrayDeque does not enforce uniqueness like Set.
     *
     *
     * 14. ITERATION
     *
     * ArrayDeque supports forward and reverse traversal.
     *
     * Forward iteration:
     *
     * ArrayDeque<Integer> deque = new ArrayDeque<>();
     *
     * deque.offer(10);
     * deque.offer(20);
     * deque.offer(30);
     *
     * for (Integer value : deque) {
     *     System.out.println(value);
     * }
     *
     * Output:
     *
     * 10
     * 20
     * 30
     *
     * Reverse iteration:
     *
     * Iterator<Integer> iterator = deque.descendingIterator();
     *
     * while (iterator.hasNext()) {
     *     System.out.println(iterator.next());
     * }
     *
     * Output:
     *
     * 30
     * 20
     * 10
     *
     * descendingIterator() traverses from rear to front.
     *
     * Important:
     *
     * ArrayDeque iterators are fail-fast on a best-effort basis.
     * If the deque is structurally modified while iterating,
     * except through the iterator's own permitted operations,
     * the iterator may throw ConcurrentModificationException.
     *
     * Do not rely on fail-fast behavior for correctness.
     *
     *
     * 15. IMPORTANT ARRAYDEQUE METHODS
     *
     * addFirst(e)
     * addLast(e)
     * offerFirst(e)
     * offerLast(e)
     * removeFirst()
     * removeLast()
     * pollFirst()
     * pollLast()
     * getFirst()
     * getLast()
     * peekFirst()
     * peekLast()
     * push(e)
     * pop()
     * peek()
     * removeFirstOccurrence(o)
     * removeLastOccurrence(o)
     * descendingIterator()
     *
     * Additional Collection methods:
     *
     * size()
     * isEmpty()
     * contains(o)
     * remove(o)
     * clear()
     * iterator()
     *
     *
     * 16. TIME COMPLEXITY
     *
     * Operation                         Complexity
     *
     * addFirst()                        O(1) amortized
     * addLast()                         O(1) amortized
     * offerFirst()                      O(1) amortized
     * offerLast()                       O(1) amortized
     * removeFirst()                     O(1)
     * removeLast()                      O(1)
     * pollFirst()                       O(1)
     * pollLast()                        O(1)
     * getFirst()                        O(1)
     * getLast()                         O(1)
     * peekFirst()                       O(1)
     * peekLast()                        O(1)
     * contains()                        O(n)
     * removeFirstOccurrence()           O(n)
     * removeLastOccurrence()            O(n)
     * size()                            O(1)
     *
     * What does amortized O(1) mean?
     *
     * Most insertions take constant time.
     *
     * Occasionally, the internal array needs to grow and
     * existing elements must be copied or rearranged.
     *
     * That individual insertion can take O(n).
     *
     * However, averaged across a long sequence of insertions,
     * the cost per insertion is O(1) amortized.
     *
     *
     * 17. ARRAYDEQUE VS LINKEDLIST
     *
     * ArrayDeque:
     *
     * - Uses a resizable circular array.
     * - Does not create a separate node for every element.
     * - Does not permit null.
     * - Usually has better memory locality.
     * - Efficient operations at both ends.
     *
     * LinkedList:
     *
     * - Uses a doubly linked list.
     * - Stores elements in separate nodes.
     * - Permits null.
     * - Supports List operations as well as Deque operations.
     * - Has additional node and reference overhead.
     *
     * For ordinary Queue and Deque operations, ArrayDeque is
     * often the preferred choice.
     *
     *
     * 18. ARRAYDEQUE VS STACK
     *
     * Stack:
     *
     * - Legacy class.
     * - Extends Vector.
     * - Its methods are synchronized.
     * - Supports LIFO operations.
     *
     * ArrayDeque:
     *
     * - Modern Deque implementation.
     * - Supports stack and queue behavior.
     * - Not synchronized.
     * - Generally preferred for single-threaded stack usage.
     *
     * Example:
     *
     * Deque<Integer> stack = new ArrayDeque<>();
     *
     * stack.push(10);
     * stack.push(20);
     * stack.pop();
     *
     * If multiple threads need to access a stack safely,
     * choose an appropriate concurrent design rather than
     * assuming ArrayDeque is thread-safe.
     *
     *
     * 19. THREAD SAFETY
     *
     * ArrayDeque is NOT thread-safe.
     *
     * If multiple threads modify the same ArrayDeque concurrently,
     * external synchronization is required.
     *
     * For concurrent deque operations, Java provides:
     *
     * ConcurrentLinkedDeque
     *
     * It is a thread-safe, non-blocking Deque implementation.
     *
     * LinkedBlockingDeque
     *
     * It is a blocking Deque implementation that supports
     * waiting operations.
     *
     * These classes belong to java.util.concurrent.
     *
     *
     * 20. IMPORTANT INTERNAL IMPLEMENTATION DETAILS
     *
     * - ArrayDeque is backed by an array.
     * - It uses circular indexing to manage both ends.
     * - It grows automatically when necessary.
     * - It does not permit null.
     * - It maintains elements in deque order.
     *
     * Java implementation details can change between releases.
     *
     * For example, the exact initial capacity, resizing strategy,
     * index calculations and internal field names are not
     * guaranteed by the public API.
     *
     * Focus on the circular-array concept rather than memorizing
     * private implementation details.
     *
     *
     * 21. COMMON USE CASES
     *
     * 1. Stack implementation
     * 2. Queue implementation
     * 3. Sliding-window maximum/minimum algorithms
     * 4. Palindrome checking
     * 5. Undo and redo operations
     * 6. Browser navigation history
     * 7. Processing elements from both ends
     *
     *
     * 22. COMMON MISTAKES
     *
     * 1. Thinking ArrayDeque is an interface.
     *    It is a concrete class.
     *
     * 2. Thinking it is backed by a linked list.
     *    It uses a resizable circular array.
     *
     * 3. Assuming null is permitted.
     *    ArrayDeque rejects null.
     *
     * 4. Confusing push() and addLast().
     *    push() inserts at the front.
     *
     * 5. Confusing pop() and pollLast().
     *    pop() removes from the front.
     *
     * 6. Assuming it is thread-safe.
     *    It is not thread-safe.
     *
     * 7. Assuming every insertion always takes exactly O(1).
     *    Resizing can make an individual insertion O(n).
     *
     *
     * 23. INTERVIEW QUESTIONS
     *
     * 1. What is ArrayDeque?
     * 2. Which interface does ArrayDeque implement?
     * 3. Which data structure is used internally?
     * 4. What is a circular array?
     * 5. How does circular indexing work?
     * 6. Can ArrayDeque be used as a Stack?
     * 7. Can ArrayDeque be used as a Queue?
     * 8. Does ArrayDeque allow null?
     * 9. Does ArrayDeque allow duplicate elements?
     * 10. Is ArrayDeque thread-safe?
     * 11. What is the difference between ArrayDeque and LinkedList?
     * 12. What is the difference between ArrayDeque and Stack?
     * 13. What is amortized O(1)?
     * 14. What happens when the internal array becomes full?
     * 15. What is descendingIterator()?
     * 16. Why is ArrayDeque often preferred over Stack?
     * 17. What is ConcurrentLinkedDeque?
     * 18. What is LinkedBlockingDeque?
     *
     *
     * 24. QUICK REVISION
     *
     * - ArrayDeque is a class in java.util.
     * - It implements Deque.
     * - It uses a resizable circular array.
     * - It supports insertion and removal at both ends.
     * - It can behave as a FIFO Queue or LIFO Stack.
     * - push() inserts at the front.
     * - pop() removes from the front.
     * - Null elements are prohibited.
     * - Duplicate elements are allowed.
     * - It is not thread-safe.
     * - End operations are O(1) amortized for insertion
     *   and O(1) for removal and examination.
     * - Searching and occurrence-based removal are O(n).
     *
     * ============================================================
     *                      END OF ARRAYDEQUE
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about ArrayDeque , Happy Learning ..!");
    }
}
