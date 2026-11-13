
public class LinkedListAsQueueAndDeque {

    /*
     * ============================================================
     *             LINKEDLIST AS QUEUE AND DEQUE IN JAVA
     * ============================================================
     *
     * 1. WHAT IS LINKEDLIST?
     *
     * LinkedList is a class in java.util.
     *
     * It implements multiple interfaces:
     *
     * - List
     * - Deque
     * - Queue (through Deque)
     *
     * This means the same LinkedList object can be used as:
     *
     * - A List
     * - A Queue
     * - A Deque
     *
     * Example:
     *
     * LinkedList<Integer> list = new LinkedList<>();
     *
     * It can be used through different reference types:
     *
     * List<Integer> list = new LinkedList<>();
     *
     * Queue<Integer> queue = new LinkedList<>();
     *
     * Deque<Integer> deque = new LinkedList<>();
     *
     *
     * 2. INTERNAL DATA STRUCTURE
     *
     * LinkedList internally uses a DOUBLY LINKED LIST.
     *
     * Every element is stored inside a node.
     *
     * A node contains:
     *
     * 1. Previous node reference.
     * 2. Element value.
     * 3. Next node reference.
     *
     * Conceptual node:
     *
     * +----------+-------+----------+
     * | Previous | Value | Next     |
     * +----------+-------+----------+
     *
     * Example:
     *
     * null <- [10] <-> [20] <-> [30] -> null
     *
     * LinkedList maintains references to:
     *
     * - First node (head).
     * - Last node (tail).
     *
     * This allows efficient insertion and removal at both ends.
     *
     *
     * 3. USING LINKEDLIST AS A QUEUE
     *
     * A Queue generally follows FIFO:
     * First In, First Out.
     *
     * To use LinkedList as a FIFO Queue:
     *
     * - Insert at the rear.
     * - Remove from the front.
     *
     * Example:
     *
     * import java.util.Queue;
     * import java.util.LinkedList;
     *
     * Queue<Integer> queue = new LinkedList<>();
     *
     * queue.offer(10);
     * queue.offer(20);
     * queue.offer(30);
     *
     * System.out.println(queue.poll()); // 10
     * System.out.println(queue.poll()); // 20
     * System.out.println(queue.poll()); // 30
     *
     * Output:
     *
     * 10
     * 20
     * 30
     *
     * This is FIFO behavior.
     *
     *
     * 4. USING LINKEDLIST AS A DEQUE
     *
     * LinkedList also implements Deque.
     *
     * Therefore, we can insert and remove elements from both ends.
     *
     * Example:
     *
     * import java.util.Deque;
     * import java.util.LinkedList;
     *
     * Deque<Integer> deque = new LinkedList<>();
     *
     * deque.offerFirst(10);
     * deque.offerLast(20);
     * deque.offerFirst(5);
     * deque.offerLast(30);
     *
     * System.out.println(deque);
     *
     * Output:
     *
     * [5, 10, 20, 30]
     *
     * Remove from both ends:
     *
     * System.out.println(deque.pollFirst()); // 5
     * System.out.println(deque.pollLast());  // 30
     *
     * Remaining:
     *
     * [10, 20]
     *
     *
     * 5. USING LINKEDLIST AS A STACK
     *
     * LinkedList can also behave as a Stack through Deque methods.
     *
     * Example:
     *
     * Deque<Integer> stack = new LinkedList<>();
     *
     * stack.push(10);
     * stack.push(20);
     * stack.push(30);
     *
     * System.out.println(stack.pop()); // 30
     * System.out.println(stack.pop()); // 20
     * System.out.println(stack.pop()); // 10
     *
     * It follows LIFO:
     * Last In, First Out.
     *
     * However, ArrayDeque is generally preferred for ordinary
     * single-threaded stack usage.
     *
     *
     * 6. IMPORTANT QUEUE METHODS
     *
     * offer(element)
     *
     * - Inserts at the rear.
     * - Returns true when successful.
     *
     * add(element)
     *
     * - Inserts at the rear.
     *
     * poll()
     *
     * - Removes and returns the front element.
     * - Returns null if empty.
     *
     * remove()
     *
     * - Removes and returns the front element.
     * - Throws NoSuchElementException if empty.
     *
     * peek()
     *
     * - Returns the front element without removing it.
     * - Returns null if empty.
     *
     * element()
     *
     * - Returns the front element without removing it.
     * - Throws NoSuchElementException if empty.
     *
     *
     * 7. IMPORTANT DEQUE METHODS
     *
     * Insertion:
     *
     * addFirst(e)
     * addLast(e)
     * offerFirst(e)
     * offerLast(e)
     *
     * Removal:
     *
     * removeFirst()
     * removeLast()
     * pollFirst()
     * pollLast()
     *
     * Examination:
     *
     * getFirst()
     * getLast()
     * peekFirst()
     * peekLast()
     *
     * Stack operations:
     *
     * push(e)
     * pop()
     *
     * LinkedList supports all these methods because it implements
     * the Deque interface.
     *
     *
     * 8. LINKEDLIST PERMITS NULL
     *
     * Unlike ArrayDeque and PriorityQueue, LinkedList permits null.
     *
     * Example:
     *
     * LinkedList<Integer> list = new LinkedList<>();
     *
     * list.offer(10);
     * list.offer(null);
     * list.offer(20);
     *
     * System.out.println(list);
     *
     * Output:
     *
     * [10, null, 20]
     *
     * Important:
     *
     * Although LinkedList permits null, using null in Queue or
     * Deque operations is not recommended.
     *
     * Why?
     *
     * poll() and peek() return null when the queue is empty.
     * A null element can therefore make the result ambiguous.
     *
     *
     * 9. DUPLICATE ELEMENTS
     *
     * LinkedList permits duplicate elements.
     *
     * Example:
     *
     * LinkedList<Integer> list = new LinkedList<>();
     *
     * list.offer(10);
     * list.offer(10);
     * list.offer(20);
     *
     * Output:
     *
     * [10, 10, 20]
     *
     *
     * 10. TIME COMPLEXITY
     *
     * Operation                         Complexity
     *
     * offer()                           O(1)
     * poll()                            O(1)
     * peek()                            O(1)
     * addFirst()                        O(1)
     * addLast()                         O(1)
     * removeFirst()                     O(1)
     * removeLast()                      O(1)
     * peekFirst()                       O(1)
     * peekLast()                        O(1)
     * get(index)                        O(n)
     * contains(object)                  O(n)
     * remove(object)                    O(n)
     *
     * Why are end operations O(1)?
     *
     * LinkedList maintains references to its first and last nodes.
     *
     * It does not need to traverse the entire list to insert
     * or remove at either end.
     *
     * Why is get(index) O(n)?
     *
     * LinkedList must traverse nodes to reach the requested index.
     *
     *
     * 11. LINKEDLIST VS ARRAYDEQUE
     *
     * LinkedList:
     *
     * - Uses a doubly linked list.
     * - Implements List and Deque.
     * - Permits null.
     * - Has node and reference memory overhead.
     * - Supports index-based List operations.
     *
     * ArrayDeque:
     *
     * - Uses a resizable circular array.
     * - Implements Deque.
     * - Does not permit null.
     * - Usually has better memory locality.
     * - Does not support index-based access.
     *
     * Which should we choose?
     *
     * For ordinary Queue or Deque operations:
     *
     * ArrayDeque is often preferred.
     *
     * Choose LinkedList when you also need List functionality
     * or specifically need its linked-list behavior.
     *
     *
     * 12. LINKEDLIST VS PRIORITYQUEUE
     *
     * LinkedList:
     *
     * - Can provide FIFO Queue behavior.
     * - Can provide Deque behavior.
     * - Does not automatically order elements by priority.
     *
     * PriorityQueue:
     *
     * - Orders elements using natural ordering or a Comparator.
     * - Provides priority-based retrieval.
     * - Uses a binary heap.
     * - Does not permit null.
     *
     *
     * 13. THREAD SAFETY
     *
     * LinkedList is NOT thread-safe.
     *
     * Multiple threads modifying the same LinkedList concurrently
     * require external synchronization or another suitable
     * concurrent collection.
     *
     * For concurrent queue processing, Java provides:
     *
     * ConcurrentLinkedQueue
     * LinkedBlockingQueue
     *
     * For concurrent deque processing:
     *
     * ConcurrentLinkedDeque
     * LinkedBlockingDeque
     *
     * These are separate classes in java.util.concurrent.
     *
     *
     * 14. ITERATION
     *
     * LinkedList supports forward and backward traversal.
     *
     * Forward:
     *
     * Iterator<Integer> iterator = list.iterator();
     *
     * Backward:
     *
     * Iterator<Integer> iterator = list.descendingIterator();
     *
     * The descending iterator traverses from the last element
     * toward the first.
     *
     * LinkedList iterators are fail-fast on a best-effort basis.
     * Concurrent structural modifications may cause
     * ConcurrentModificationException.
     *
     *
     * 15. IMPORTANT INTERNAL DETAILS
     *
     * - LinkedList is a doubly linked list.
     * - Each node stores an element and two references.
     * - The list maintains first and last node references.
     * - Insertion and removal at either end are O(1).
     * - Searching for an element is O(n).
     * - Accessing an index is O(n).
     * - Null and duplicate elements are permitted.
     * - It is not thread-safe.
     *
     *
     * 16. COMMON MISTAKES
     *
     * 1. Assuming LinkedList is only a List.
     *    It also implements Deque and Queue.
     *
     * 2. Assuming LinkedList uses a singly linked list.
     *    It uses a doubly linked list.
     *
     * 3. Assuming get(index) is O(1).
     *    It requires traversal and is O(n).
     *
     * 4. Assuming LinkedList is thread-safe.
     *    It is not.
     *
     * 5. Assuming null is always safe in Queue usage.
     *    It can make poll() and peek() results ambiguous.
     *
     * 6. Assuming LinkedList is always faster than ArrayDeque.
     *    LinkedList has node allocation and memory overhead.
     *
     *
     * 17. INTERVIEW QUESTIONS
     *
     * 1. Can LinkedList be used as a Queue?
     * 2. Can LinkedList be used as a Deque?
     * 3. Which data structure does LinkedList use internally?
     * 4. Why are insertion and removal at the ends O(1)?
     * 5. Does LinkedList permit null?
     * 6. Does LinkedList allow duplicates?
     * 7. Is LinkedList thread-safe?
     * 8. What is the difference between LinkedList and ArrayDeque?
     * 9. What is the difference between LinkedList and PriorityQueue?
     * 10. Why is get(index) O(n)?
     * 11. What is descendingIterator()?
     * 12. Which concurrent collections can be used instead?
     *
     *
     * 18. QUICK REVISION
     *
     * - LinkedList is a class in java.util.
     * - It implements List and Deque.
     * - It can be used as a Queue, Deque or Stack.
     * - Internally, it uses a doubly linked list.
     * - It maintains first and last node references.
     * - End insertion and removal are O(1).
     * - Index-based access is O(n).
     * - Searching is O(n).
     * - Null elements are permitted.
     * - Duplicate elements are permitted.
     * - It is not thread-safe.
     * - ArrayDeque is often preferred for ordinary Queue/Deque
     *   operations when List functionality is not required.
     *
     * ============================================================
     *          END OF LINKEDLIST AS QUEUE AND DEQUE
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about LinkedList as Queue and Deque , Happy Learning ..!");
    }
}
