
public class DequeInterface {

    /*
     * ============================================================
     *                    DEQUE INTERFACE IN JAVA
     * ============================================================
     *
     * 1. WHAT IS A DEQUE?
     *
     * Deque stands for Double-Ended Queue.
     *
     * A normal Queue primarily inserts elements at the rear
     * and removes elements from the front.
     *
     * A Deque allows us to insert and remove elements at BOTH ends.
     *
     * Think of a line where people can enter and leave from
     * either side.
     *
     * Front                              Rear
     *   |                                  |
     *   v                                  v
     *  [10] <-> [20] <-> [30] <-> [40]
     *
     * We can:
     *
     * - Insert at the front.
     * - Insert at the rear.
     * - Remove from the front.
     * - Remove from the rear.
     *
     * This flexibility makes Deque useful for implementing
     * both Queue and Stack behavior.
     *
     *
     * 2. DEQUE IS AN INTERFACE
     *
     * Deque is available in java.util.
     *
     * It extends the Queue interface.
     *
     * Collection
     *     |
     *    Queue
     *     |
     *    Deque
     *     |
     * Implementations
     *
     * Important implementations:
     *
     * - ArrayDeque
     * - LinkedList
     *
     * Example:
     *
     * import java.util.Deque;
     * import java.util.ArrayDeque;
     *
     * Deque<Integer> deque = new ArrayDeque<>();
     *
     * We use the Deque interface as the reference type and
     * ArrayDeque as the implementation.
     *
     *
     * 3. IMPORTANT DEQUE METHODS
     *
     * Deque provides methods for operations at both ends.
     *
     * A. INSERT AT THE FRONT
     *
     * addFirst(element)
     *
     * - Inserts an element at the front.
     * - Throws IllegalStateException if insertion cannot be
     *   performed because of capacity restrictions.
     *
     * offerFirst(element)
     *
     * - Inserts an element at the front.
     * - Returns false if insertion cannot be performed.
     *
     *
     * B. INSERT AT THE REAR
     *
     * addLast(element)
     *
     * - Inserts an element at the rear.
     * - Throws IllegalStateException if insertion cannot be
     *   performed because of capacity restrictions.
     *
     * offerLast(element)
     *
     * - Inserts an element at the rear.
     * - Returns false if insertion cannot be performed.
     *
     *
     * C. REMOVE FROM THE FRONT
     *
     * removeFirst()
     *
     * - Removes and returns the first element.
     * - Throws NoSuchElementException if empty.
     *
     * pollFirst()
     *
     * - Removes and returns the first element.
     * - Returns null if empty.
     *
     *
     * D. REMOVE FROM THE REAR
     *
     * removeLast()
     *
     * - Removes and returns the last element.
     * - Throws NoSuchElementException if empty.
     *
     * pollLast()
     *
     * - Removes and returns the last element.
     * - Returns null if empty.
     *
     *
     * E. EXAMINE BOTH ENDS
     *
     * getFirst()
     *
     * - Returns the first element without removing it.
     * - Throws NoSuchElementException if empty.
     *
     * peekFirst()
     *
     * - Returns the first element without removing it.
     * - Returns null if empty.
     *
     * getLast()
     *
     * - Returns the last element without removing it.
     * - Throws NoSuchElementException if empty.
     *
     * peekLast()
     *
     * - Returns the last element without removing it.
     * - Returns null if empty.
     *
     *
     * 4. DEQUE METHOD SUMMARY
     *
     * Operation          Exception-based     Special-value-based
     *
     * Insert at front    addFirst(e)         offerFirst(e)
     * Insert at rear     addLast(e)          offerLast(e)
     * Remove from front  removeFirst()       pollFirst()
     * Remove from rear   removeLast()        pollLast()
     * Examine front      getFirst()          peekFirst()
     * Examine rear       getLast()           peekLast()
     *
     * Remember:
     *
     * add  -> Insert
     * remove -> Remove
     * get/peek -> Examine without removing
     *
     * The exception-based methods throw when the operation
     * cannot be completed because the deque is empty or full.
     *
     * The special-value methods return false or null instead.
     *
     *
     * 5. COMPLETE DEQUE EXAMPLE
     *
     * import java.util.Deque;
     * import java.util.ArrayDeque;
     *
     * public class DequeExample {
     *
     *     public static void main(String[] args) {
     *
     *         Deque<Integer> deque = new ArrayDeque<>();
     *
     *         // Insert at rear
     *         deque.offerLast(20);
     *         deque.offerLast(30);
     *
     *         // Insert at front
     *         deque.offerFirst(10);
     *
     *         System.out.println(deque);
     *
     *         // Examine both ends
     *         System.out.println(deque.peekFirst());
     *         System.out.println(deque.peekLast());
     *
     *         // Remove from both ends
     *         System.out.println(deque.pollFirst());
     *         System.out.println(deque.pollLast());
     *
     *         System.out.println(deque);
     *     }
     * }
     *
     * Output:
     *
     * [10, 20, 30]
     * 10
     * 30
     * 10
     * 30
     * [20]
     *
     *
     * 6. USING DEQUE AS A QUEUE (FIFO)
     *
     * A Deque can behave exactly like a normal FIFO Queue.
     *
     * Insert at rear.
     * Remove from front.
     *
     * Example:
     *
     * Deque<Integer> deque = new ArrayDeque<>();
     *
     * deque.offerLast(10);
     * deque.offerLast(20);
     * deque.offerLast(30);
     *
     * System.out.println(deque.pollFirst()); // 10
     * System.out.println(deque.pollFirst()); // 20
     * System.out.println(deque.pollFirst()); // 30
     *
     * Insertion order:
     * 10 -> 20 -> 30
     *
     * Removal order:
     * 10 -> 20 -> 30
     *
     * This is FIFO behavior.
     *
     *
     * 7. USING DEQUE AS A STACK (LIFO)
     *
     * A Stack follows LIFO:
     * Last In, First Out.
     *
     * A Deque can replace the legacy Stack class.
     *
     * Insert at the front.
     * Remove from the front.
     *
     * Example:
     *
     * Deque<Integer> stack = new ArrayDeque<>();
     *
     * stack.push(10);
     * stack.push(20);
     * stack.push(30);
     *
     * System.out.println(stack.pop()); // 30
     * System.out.println(stack.pop()); // 20
     * System.out.println(stack.pop()); // 10
     *
     * Insertion:
     * 10 -> 20 -> 30
     *
     * Removal:
     * 30 -> 20 -> 10
     *
     * This is LIFO behavior.
     *
     *
     * 8. STACK METHODS PROVIDED BY DEQUE
     *
     * Deque provides methods that make it behave like a Stack.
     *
     * push(element)
     *
     * - Inserts the element at the front.
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
     * - Returns the front element without removing it.
     * - Returns null if empty.
     *
     * Example:
     *
     * Deque<Integer> stack = new ArrayDeque<>();
     *
     * stack.push(100);
     * stack.push(200);
     *
     * System.out.println(stack.peek()); // 200
     * System.out.println(stack.pop());  // 200
     *
     * Important:
     * push(), pop() and peek() operate at the front of the Deque.
     *
     *
     * 9. QUEUE METHODS INHERITED BY DEQUE
     *
     * Since Deque extends Queue, it inherits Queue operations.
     *
     * offer(element)
     * - Equivalent to offerLast(element).
     *
     * add(element)
     * - Equivalent to addLast(element).
     *
     * poll()
     * - Equivalent to pollFirst().
     *
     * remove()
     * - Equivalent to removeFirst().
     *
     * peek()
     * - Equivalent to peekFirst().
     *
     * element()
     * - Equivalent to getFirst().
     *
     * This means a Deque can be used wherever a Queue is expected.
     *
     *
     * 10. DEQUE VS QUEUE
     *
     * Queue:
     *
     * - Designed primarily for processing elements at the head.
     * - Commonly inserts at the tail and removes from the head.
     * - Does not expose general operations for both ends.
     *
     * Deque:
     *
     * - Supports insertion at both ends.
     * - Supports removal at both ends.
     * - Can behave as a Queue or Stack.
     *
     *
     * 11. DEQUE VS STACK
     *
     * Stack:
     *
     * - Follows LIFO.
     * - Traditional Stack class provides push(), pop() and peek().
     *
     * Deque:
     *
     * - Can provide LIFO behavior using push() and pop().
     * - Also supports operations at the opposite end.
     * - Is generally preferred over the legacy Stack class for
     *   single-threaded stack usage.
     *
     * Recommended:
     *
     * Deque<Integer> stack = new ArrayDeque<>();
     *
     *
     * 12. NULL ELEMENTS
     *
     * Deque implementations may have different null policies.
     *
     * ArrayDeque:
     * - Does not permit null.
     *
     * LinkedList:
     * - Permits null.
     *
     * Why is null generally discouraged?
     *
     * Methods such as pollFirst(), pollLast(), peekFirst()
     * and peekLast() return null when no element is available.
     *
     * Using null as an actual element can make the result
     * ambiguous.
     *
     * Best practice:
     * Avoid null elements in a Deque.
     *
     *
     * 13. ITERATION
     *
     * Deque supports iteration.
     *
     * Example:
     *
     * Deque<Integer> deque = new ArrayDeque<>();
     *
     * deque.offerLast(10);
     * deque.offerLast(20);
     * deque.offerLast(30);
     *
     * // Forward iteration
     * for (Integer value : deque) {
     *     System.out.println(value);
     * }
     *
     * // Reverse iteration
     * Iterator<Integer> iterator = deque.descendingIterator();
     *
     * while (iterator.hasNext()) {
     *     System.out.println(iterator.next());
     * }
     *
     * descendingIterator() traverses from the last element
     * toward the first element.
     *
     * It does not remove elements.
     *
     *
     * 14. IMPORTANT DEQUE METHODS
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
     * removeFirstOccurrence(o)
     * removeLastOccurrence(o)
     * descendingIterator()
     *
     * removeFirstOccurrence(o):
     * Removes the first occurrence when searching from the front.
     *
     * removeLastOccurrence(o):
     * Removes the first matching occurrence when searching
     * from the rear.
     *
     *
     * 15. DEQUE IMPLEMENTATIONS
     *
     * Important implementations:
     *
     * 1. ArrayDeque
     *
     * - Resizable array-based implementation.
     * - Efficient operations at both ends.
     * - Does not permit null.
     *
     * 2. LinkedList
     *
     * - Doubly linked list implementation.
     * - Supports Queue and Deque operations.
     * - Permits null.
     *
     * We will study these implementations separately.
     *
     *
     * 16. THREAD SAFETY
     *
     * Deque itself does not guarantee thread safety.
     *
     * ArrayDeque and LinkedList are not thread-safe.
     *
     * For concurrent deque operations, Java provides:
     *
     * ConcurrentLinkedDeque
     *
     * It is a thread-safe, non-blocking implementation.
     *
     * For blocking deque operations, Java provides:
     *
     * LinkedBlockingDeque
     *
     * It supports operations that can wait when the deque
     * is full or empty.
     *
     * These belong to java.util.concurrent.
     *
     *
     * 17. COMMON USE CASES
     *
     * - Implementing stacks and queues.
     * - Undo and redo operations.
     * - Browser navigation history.
     * - Palindrome checking.
     * - Sliding window algorithms.
     * - Work scheduling.
     * - Removing elements from both ends of a sequence.
     *
     *
     * 18. COMMON MISTAKES
     *
     * 1. Thinking Deque is a separate implementation.
     *    Deque is an interface.
     *
     * 2. Thinking Deque only follows FIFO.
     *    It can also provide LIFO behavior.
     *
     * 3. Confusing push() with offerLast().
     *    push() inserts at the front.
     *
     * 4. Confusing pop() with pollLast().
     *    pop() removes from the front.
     *
     * 5. Assuming every Deque implementation allows null.
     *    ArrayDeque rejects null.
     *
     * 6. Assuming Deque is automatically thread-safe.
     *    Thread safety depends on the implementation.
     *
     *
     * 19. INTERVIEW QUESTIONS
     *
     * 1. What is a Deque?
     * 2. What does Deque stand for?
     * 3. Which interface does Deque extend?
     * 4. What is the difference between Queue and Deque?
     * 5. Can Deque be used as a Stack?
     * 6. How does push() work?
     * 7. What does pop() remove?
     * 8. What is the difference between addFirst() and
     *    offerFirst()?
     * 9. What is the difference between pollFirst() and
     *    removeFirst()?
     * 10. What does descendingIterator() do?
     * 11. Which classes implement Deque?
     * 12. Is ArrayDeque thread-safe?
     * 13. Does ArrayDeque permit null?
     * 14. What is ConcurrentLinkedDeque?
     * 15. Why is Deque preferred over Stack?
     *
     *
     * 20. QUICK REVISION
     *
     * - Deque means Double-Ended Queue.
     * - Deque extends Queue.
     * - Insertion and removal are supported at both ends.
     * - addFirst()/offerFirst() insert at the front.
     * - addLast()/offerLast() insert at the rear.
     * - removeFirst()/pollFirst() remove from the front.
     * - removeLast()/pollLast() remove from the rear.
     * - push() inserts at the front.
     * - pop() removes from the front.
     * - Deque can behave as FIFO Queue or LIFO Stack.
     * - ArrayDeque and LinkedList are common implementations.
     * - Deque itself does not guarantee thread safety.
     *
     * ============================================================
     *                    END OF DEQUE INTERFACE
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Deque Interface , Happy Learning ..!");
    }
}
