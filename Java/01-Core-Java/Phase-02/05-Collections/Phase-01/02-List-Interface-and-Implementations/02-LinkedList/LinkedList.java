
public class LinkedList {

    /*
     * ============================================================
     *                         LINKEDLIST
     * ============================================================
     *
     * 1. WHAT IS LINKEDLIST?
     *
     * Imagine storing student records.
     *
     * In an array, elements are stored in consecutive positions
     * in an internal array.
     *
     * But what if you want to insert or remove elements
     * frequently at the beginning or end?
     *
     * Array-based structures may need to shift elements.
     *
     * A LinkedList uses a different approach.
     *
     * It stores elements inside separate objects called NODES.
     *
     * Each node maintains references to neighboring nodes.
     *
     * LinkedList is a doubly linked list implementation of:
     *
     * - List
     * - Deque
     *
     * Package:
     *     java.util
     *
     * In simple words:
     *
     * LinkedList = Nodes + References + Connections.
     *
     *
     * 2. LINKEDLIST DECLARATION
     *
     * Declaration:
     *
     *     public class LinkedList<E>
     *         extends AbstractSequentialList<E>
     *         implements List<E>, Deque<E>,
     *                    Cloneable, Serializable
     *
     * E represents the element type.
     *
     * IMPORTANT:
     * LinkedList implements both List and Deque.
     *
     * Therefore, it can be used as:
     *
     * - A List.
     * - A Queue.
     * - A Deque.
     *
     *
     * 3. LINKEDLIST HIERARCHY
     *
     *                      Iterable
     *                          |
     *                      Collection
     *                          |
     *                 +--------+--------+
     *                 |                 |
     *                List             Queue
     *                 |                 |
     *                 +--------+--------+
     *                          |
     *                         Deque
     *                          |
     *                      LinkedList
     *
     * Simplified conceptual diagram.
     *
     * More accurately:
     *
     * - LinkedList extends AbstractSequentialList.
     * - AbstractSequentialList extends AbstractList.
     * - AbstractList implements List.
     * - LinkedList also implements Deque.
     *
     *
     * 4. CREATING LINKEDLIST OBJECTS
     *
     * METHOD 1:
     *
     *     LinkedList<String> names = new LinkedList<>();
     *
     * METHOD 2:
     *
     *     LinkedList<Integer> numbers =
     *         new LinkedList<>();
     *
     * METHOD 3: Using List reference
     *
     *     List<String> names = new LinkedList<>();
     *
     * METHOD 4: Using Queue reference
     *
     *     Queue<Integer> queue = new LinkedList<>();
     *
     * METHOD 5: Using Deque reference
     *
     *     Deque<Integer> deque = new LinkedList<>();
     *
     * The reference type determines which interface methods
     * are directly available.
     *
     * The actual object is still a LinkedList.
     *
     *
     * 5. WHAT IS A NODE?
     *
     * A node is an individual object that stores:
     *
     * 1. Data.
     * 2. Reference to the previous node.
     * 3. Reference to the next node.
     *
     * Conceptual structure:
     *
     *     class Node<E> {
     *         E item;
     *         Node<E> next;
     *         Node<E> prev;
     *     }
     *
     * This is a conceptual representation, not the exact
     * source code of the JDK implementation.
     *
     * Each node connects to its neighboring nodes.
     *
     *
     * 6. HOW A DOUBLY LINKED LIST LOOKS
     *
     * Example:
     *
     *     null <- [10] <-> [20] <-> [30] -> null
     *
     * Every node has:
     *
     * - A previous reference.
     * - Its element.
     * - A next reference.
     *
     * The first node has no previous node.
     *
     * The last node has no next node.
     *
     * In a conventional LinkedList implementation:
     *
     * - The first node is called the head.
     * - The last node is called the tail.
     *
     * In OpenJDK, these are represented by first and last
     * node references.
     *
     *
     * 7. INTERNAL WORKING OF LINKEDLIST
     *
     * Common OpenJDK implementation maintains:
     *
     *     int size;
     *     Node<E> first;
     *     Node<E> last;
     *
     * Conceptually:
     *
     *     first -> [10] <-> [20] <-> [30] <- last
     *
     * size = 3
     *
     * The first reference points to the first node.
     *
     * The last reference points to the final node.
     *
     * This allows efficient operations at both ends.
     *
     * IMPORTANT:
     * LinkedList does not use an internal array like ArrayList.
     *
     *
     * 8. HOW LINKEDLIST STORES ELEMENTS
     *
     * Consider:
     *
     *     LinkedList<Integer> numbers = new LinkedList<>();
     *
     *     numbers.add(10);
     *     numbers.add(20);
     *     numbers.add(30);
     *
     * Conceptual structure:
     *
     *     first
     *       |
     *       v
     *     [null | 10 | next] <-> [prev | 20 | next]
     *                                      |
     *                                      v
     *                               [prev | 30 | null]
     *                                      ^
     *                                      |
     *                                     last
     *
     * Each node stores an element reference and two links.
     *
     * The nodes do not need to occupy adjacent memory locations.
     *
     *
     * 9. ADDING ELEMENTS AT THE END
     *
     * Consider an empty LinkedList:
     *
     *     first = null
     *     last  = null
     *
     * Execute:
     *
     *     numbers.add(10);
     *
     * A new node is created.
     *
     * Since the list was empty:
     *
     *     first -> [10] <- last
     *
     * Now:
     *
     *     numbers.add(20);
     *
     * New structure:
     *
     *     first -> [10] <-> [20] <- last
     *
     * Adding another element:
     *
     *     numbers.add(30);
     *
     * Result:
     *
     *     first -> [10] <-> [20] <-> [30] <- last
     *
     * Since LinkedList maintains a last reference,
     * adding at the end does not require traversing
     * the entire list.
     *
     * Time complexity:
     *
     *     add(element) = O(1)
     *
     *
     * 10. ADDING AT THE BEGINNING
     *
     * Consider:
     *
     *     [10] <-> [20] <-> [30]
     *
     * Execute:
     *
     *     numbers.addFirst(5);
     *
     * Conceptual process:
     *
     * 1. Create a new node.
     * 2. Connect its next reference to the old first node.
     * 3. Update the old first node's previous reference.
     * 4. Update the first reference.
     *
     * Result:
     *
     *     [5] <-> [10] <-> [20] <-> [30]
     *
     * Time complexity:
     *
     *     addFirst() = O(1)
     *
     *
     * 11. ADDING AT THE END USING ADDLAST()
     *
     * Method:
     *
     *     addLast(element)
     *
     * Example:
     *
     *     numbers.addLast(40);
     *
     * Result:
     *
     *     [5] <-> [10] <-> [20] <-> [30] <-> [40]
     *
     * LinkedList updates the old tail and last reference.
     *
     * Time complexity:
     *
     *     addLast() = O(1)
     *
     *
     * 12. INSERTING AT A SPECIFIC INDEX
     *
     * Consider:
     *
     *     [10] <-> [20] <-> [30]
     *
     * Insert 15 at index 1:
     *
     *     numbers.add(1, 15);
     *
     * Conceptual process:
     *
     * 1. Locate the node currently at index 1.
     * 2. Create a new node.
     * 3. Connect the new node to its neighbors.
     * 4. Update neighboring references.
     *
     * Result:
     *
     *     [10] <-> [15] <-> [20] <-> [30]
     *
     * IMPORTANT:
     *
     * Linking a node requires only a few reference updates.
     * However, finding an arbitrary index may require traversal.
     *
     * Therefore:
     *
     *     add(index, element) = O(n) in general.
     *
     *
     * 13. HOW LINKEDLIST FINDS AN INDEX
     *
     * Suppose:
     *
     *     [10] <-> [20] <-> [30] <-> [40] <-> [50]
     *
     * You request:
     *
     *     numbers.get(3);
     *
     * LinkedList can start from:
     *
     * - The first node if the requested index is closer
     *   to the beginning.
     * - The last node if the requested index is closer
     *   to the end.
     *
     * It follows next or previous references until it
     * reaches the required node.
     *
     * In OpenJDK, the traversal direction is selected
     * based on the requested index.
     *
     * Time complexity:
     *
     *     get(index) = O(n)
     *
     * More precisely, traversal requires work proportional
     * to the distance from the closer end.
     *
     *
     * 14. GETTING ELEMENTS
     *
     * Method:
     *
     *     get(index)
     *
     * Example:
     *
     *     LinkedList<Integer> numbers =
     *         new LinkedList<>(List.of(10, 20, 30));
     *
     *     System.out.println(numbers.get(1));
     *
     * Output:
     *
     *     20
     *
     * This requires node traversal.
     *
     * LinkedList also provides:
     *
     *     getFirst()
     *     getLast()
     *
     * These operations retrieve elements at the ends.
     *
     * Time complexity:
     *
     *     getFirst() = O(1)
     *     getLast()  = O(1)
     *     get(index) = O(n) in general
     *
     *
     * 15. REMOVING THE FIRST NODE
     *
     * Consider:
     *
     *     [10] <-> [20] <-> [30]
     *
     * Execute:
     *
     *     numbers.removeFirst();
     *
     * Conceptual process:
     *
     * 1. Store the first node's element.
     * 2. Move the first reference to the next node.
     * 3. Set the new first node's previous reference to null.
     * 4. Disconnect the old first node.
     * 5. Decrease the size.
     *
     * Result:
     *
     *     [20] <-> [30]
     *
     * Time complexity:
     *
     *     removeFirst() = O(1)
     *
     *
     * 16. REMOVING THE LAST NODE
     *
     * Consider:
     *
     *     [10] <-> [20] <-> [30]
     *
     * Execute:
     *
     *     numbers.removeLast();
     *
     * The last reference moves to the previous node.
     *
     * The new last node's next reference becomes null.
     *
     * Result:
     *
     *     [10] <-> [20]
     *
     * Time complexity:
     *
     *     removeLast() = O(1)
     *
     *
     * 17. REMOVING AT A SPECIFIC INDEX
     *
     * Consider:
     *
     *     [10] <-> [20] <-> [30] <-> [40]
     *
     * Execute:
     *
     *     numbers.remove(1);
     *
     * The node containing 20 is removed.
     *
     * Its neighboring nodes are connected directly.
     *
     * Result:
     *
     *     [10] <-> [30] <-> [40]
     *
     * IMPORTANT:
     *
     * Finding the node requires traversal.
     *
     * Once the node is located, unlinking it requires
     * only a constant number of reference updates.
     *
     * Therefore:
     *
     *     remove(index) = O(n) in general.
     *
     *
     * 18. REMOVING BY VALUE
     *
     * Method:
     *
     *     remove(Object object)
     *
     * Example:
     *
     *     LinkedList<Integer> numbers =
     *         new LinkedList<>(List.of(10, 20, 30, 20));
     *
     *     numbers.remove(Integer.valueOf(20));
     *
     * Result:
     *
     *     [10, 30, 20]
     *
     * It removes the first matching occurrence.
     *
     * Searching for the element may require traversal.
     *
     * Time complexity:
     *
     *     remove(object) = O(n)
     *
     *
     * 19. LINKEDLIST TIME COMPLEXITY
     *
     * Operation                  Complexity
     * --------------------------------------
     * getFirst()                 O(1)
     * getLast()                  O(1)
     * get(index)                 O(n)
     * set(index, element)        O(n)
     * add(element)               O(1)
     * addFirst(element)          O(1)
     * addLast(element)           O(1)
     * add(index, element)        O(n)
     * removeFirst()              O(1)
     * removeLast()               O(1)
     * remove(index)              O(n)
     * remove(object)             O(n)
     * contains(element)          O(n)
     * indexOf(element)           O(n)
     * Iteration                  O(n)
     *
     * IMPORTANT:
     *
     * LinkedList's indexed operations are generally O(n).
     * End operations are efficient because it maintains
     * first and last references.
     *
     *
     * 20. LINKEDLIST AS A LIST
     *
     * LinkedList implements List.
     *
     * Therefore, it supports:
     *
     *     add()
     *     get()
     *     set()
     *     remove()
     *     indexOf()
     *     lastIndexOf()
     *     subList()
     *     listIterator()
     *
     * Example:
     *
     *     List<String> names = new LinkedList<>();
     *
     *     names.add("Vinay");
     *     names.add("Ravi");
     *     names.add("Kiran");
     *
     *     System.out.println(names.get(1));
     *
     * Output:
     *
     *     Ravi
     *
     * The operations follow the List contract.
     * Their performance comes from LinkedList's implementation.
     *
     *
     * 21. LINKEDLIST AS A QUEUE
     *
     * LinkedList also implements Queue.
     *
     * A Queue commonly follows FIFO:
     *
     *     First In, First Out.
     *
     * Think of people waiting at a ticket counter.
     *
     * First person to enter the queue is generally served first.
     *
     * Example:
     *
     *     Queue<Integer> queue = new LinkedList<>();
     *
     *     queue.offer(10);
     *     queue.offer(20);
     *     queue.offer(30);
     *
     *     System.out.println(queue.poll()); // 10
     *     System.out.println(queue.poll()); // 20
     *
     * Important Queue methods:
     *
     * offer(element)
     *     Inserts an element.
     *
     * poll()
     *     Removes and returns the head, or null if empty.
     *
     * peek()
     *     Returns the head without removing it, or null.
     *
     * add(element)
     *     Inserts an element or throws if it cannot.
     *
     * remove()
     *     Removes the head or throws if empty.
     *
     * element()
     *     Retrieves the head or throws if empty.
     *
     *
     * 22. LINKEDLIST AS A DEQUE
     *
     * Deque means Double-Ended Queue.
     *
     * It allows insertion and removal at both ends.
     *
     * Example:
     *
     *     Deque<Integer> deque = new LinkedList<>();
     *
     *     deque.addFirst(20);
     *     deque.addLast(30);
     *     deque.addFirst(10);
     *
     * Result:
     *
     *     [10, 20, 30]
     *
     * Remove from the front:
     *
     *     deque.removeFirst();
     *
     * Remove from the rear:
     *
     *     deque.removeLast();
     *
     * Important Deque methods:
     *
     * addFirst()
     * addLast()
     * offerFirst()
     * offerLast()
     * removeFirst()
     * removeLast()
     * pollFirst()
     * pollLast()
     * getFirst()
     * getLast()
     * peekFirst()
     * peekLast()
     *
     *
     * 23. LINKEDLIST AS A STACK
     *
     * A Deque can be used as a stack.
     *
     * Stack follows:
     *
     *     LIFO = Last In, First Out.
     *
     * Example:
     *
     *     Deque<Integer> stack = new LinkedList<>();
     *
     *     stack.push(10);
     *     stack.push(20);
     *     stack.push(30);
     *
     *     System.out.println(stack.pop());  // 30
     *     System.out.println(stack.peek()); // 20
     *
     * ArrayDeque is generally preferred for normal stack
     * and queue operations when null support is not needed.
     *
     *
     * 24. IMPORTANT DIFFERENCE:
     *     QUEUE METHODS VS DEQUE METHODS
     *
     * Queue:
     *
     *     offer()
     *     poll()
     *     peek()
     *
     * These operate on the queue head and tail according
     * to Queue's contract.
     *
     * Deque:
     *
     *     offerFirst()
     *     offerLast()
     *     pollFirst()
     *     pollLast()
     *     peekFirst()
     *     peekLast()
     *
     * These allow operations at either end.
     *
     *
     * 25. LINKEDLIST AND NULL
     *
     * LinkedList permits null elements.
     *
     * Example:
     *
     *     LinkedList<String> names = new LinkedList<>();
     *
     *     names.add("Vinay");
     *     names.add(null);
     *     names.add("Ravi");
     *
     * Result:
     *
     *     [Vinay, null, Ravi]
     *
     * Multiple null elements are allowed.
     *
     * IMPORTANT:
     *
     * Some Queue and Deque method contracts use null
     * as an empty-result indicator.
     *
     * Therefore, null elements can make methods such as
     * poll() and peek() ambiguous when used on a LinkedList.
     *
     * Avoid null elements in queue-style usage.
     *
     *
     * 26. LINKEDLIST AND DUPLICATES
     *
     * LinkedList permits duplicate elements.
     *
     * Example:
     *
     *     LinkedList<Integer> numbers = new LinkedList<>();
     *
     *     numbers.add(10);
     *     numbers.add(20);
     *     numbers.add(10);
     *
     * Result:
     *
     *     [10, 20, 10]
     *
     * Every occurrence occupies its own node.
     *
     *
     * 27. LINKEDLIST AND ITERATORS
     *
     * LinkedList supports:
     *
     * - Iterator
     * - ListIterator
     * - Enhanced for-loop
     * - forEach()
     *
     * Example:
     *
     *     LinkedList<String> names =
     *         new LinkedList<>(List.of("Vinay", "Ravi", "Kiran"));
     *
     *     Iterator<String> iterator = names.iterator();
     *
     *     while (iterator.hasNext()) {
     *         System.out.println(iterator.next());
     *     }
     *
     * LinkedList also supports bidirectional traversal
     * through ListIterator.
     *
     * Its iterator is generally fail-fast.
     *
     * Detailed iterator behavior belongs in the dedicated
     * Iterators folder.
     *
     *
     * 28. LINKEDLIST FAIL-FAST BEHAVIOR
     *
     * Example:
     *
     *     LinkedList<String> names =
     *         new LinkedList<>(List.of("Vinay", "Ravi", "Kiran"));
     *
     *     for (String name : names) {
     *         if (name.equals("Ravi")) {
     *             names.remove(name);
     *         }
     *     }
     *
     * This may throw ConcurrentModificationException.
     *
     * In common OpenJDK implementations, LinkedList tracks
     * structural changes using a modification count.
     *
     * Its fail-fast iterators detect unexpected structural
     * modifications on a best-effort basis.
     *
     * This is not a thread-safety guarantee.
     *
     *
     * 29. SAFE REMOVAL WHILE ITERATING
     *
     * METHOD 1: Iterator.remove()
     *
     *     Iterator<String> iterator = names.iterator();
     *
     *     while (iterator.hasNext()) {
     *         String name = iterator.next();
     *
     *         if (name.equals("Ravi")) {
     *             iterator.remove();
     *         }
     *     }
     *
     * METHOD 2: removeIf()
     *
     *     names.removeIf(name -> name.equals("Ravi"));
     *
     * Both approaches avoid directly modifying the list
     * through a separate reference during iterator traversal.
     *
     *
     * 30. LINKEDLIST MEMORY USAGE
     *
     * Every LinkedList node stores:
     *
     * - Element reference.
     * - Previous node reference.
     * - Next node reference.
     *
     * Additionally, the LinkedList object maintains
     * first and last references and a size value.
     *
     * Compared with ArrayList:
     *
     * ArrayList:
     *     Stores element references in an array.
     *
     * LinkedList:
     *     Stores element references inside separate nodes.
     *
     * LinkedList generally has higher memory overhead.
     *
     * Why?
     *
     * Because every node requires additional references
     * and object metadata.
     *
     * Exact memory consumption depends on JVM implementation,
     * object layout, compressed references and alignment.
     *
     *
     * 31. CACHE LOCALITY
     *
     * ArrayList:
     * - Uses a contiguous backing array of references.
     * - Iteration often benefits from good cache locality.
     *
     * LinkedList:
     * - Nodes are separate objects.
     * - Traversal follows references between nodes.
     * - Memory locality is generally poorer.
     *
     * This is one reason ArrayList can outperform LinkedList
     * even in workloads where their asymptotic complexities
     * appear similar.
     *
     *
     * 32. LINKEDLIST VS ARRAYLIST
     *
     * Feature              ArrayList          LinkedList
     * ----------------------------------------------------
     * Internal structure   Resizable array    Doubly linked list
     * get(index)           O(1)               O(n)
     * set(index)           O(1)               O(n)
     * Add at end           Amortized O(1)     O(1)
     * Add at front         O(n)               O(1)
     * Remove at front      O(n)               O(1)
     * Memory overhead      Generally lower   Generally higher
     * Cache locality       Better              Generally poorer
     * Implements Deque     No                 Yes
     * RandomAccess         Yes                No
     *
     * IMPORTANT:
     *
     * LinkedList is not automatically faster for every
     * insertion or deletion.
     *
     * For an arbitrary index:
     * - LinkedList must find the node.
     * - ArrayList may need to shift references.
     *
     * Both operations can take O(n).
     *
     *
     * 33. LINKEDLIST VS ARRAYDEQUE
     *
     * Both can implement Deque-based queue and stack behavior.
     *
     * Feature              LinkedList         ArrayDeque
     * ----------------------------------------------------
     * Internal structure   Doubly linked list Resizable array
     * Null elements        Allowed            Not allowed
     * End operations       O(1)                Amortized O(1)
     * Memory overhead      Generally higher   Generally lower
     * Random access        No                  No
     * Thread safety        No                  No
     *
     * Prefer ArrayDeque for ordinary queue and stack
     * operations when null elements are not required.
     *
     * Prefer LinkedList when you also need its List behavior
     * or when its particular node-based capabilities fit
     * your use case.
     *
     *
     * 34. LINKEDLIST VS VECTOR
     *
     * LinkedList:
     * - Doubly linked list.
     * - Not synchronized.
     * - Implements List and Deque.
     *
     * Vector:
     * - Resizable array.
     * - Individual methods are synchronized.
     * - Implements List.
     *
     * They use completely different internal data structures.
     *
     *
     * 35. LINKEDLIST THREAD SAFETY
     *
     * LinkedList is not synchronized.
     *
     * Concurrent modification from multiple threads
     * requires proper coordination.
     *
     * One possible wrapper:
     *
     *     List<Integer> numbers =
     *         Collections.synchronizedList(
     *             new LinkedList<>()
     *         );
     *
     * For iteration, synchronize on the returned List:
     *
     *     synchronized (numbers) {
     *         for (Integer number : numbers) {
     *             System.out.println(number);
     *         }
     *     }
     *
     * However, a synchronized wrapper does not automatically
     * make arbitrary multi-step operations atomic.
     *
     * Choose a concurrency design based on the workload.
     *
     *
     * 36. LINKEDLIST AND GARBAGE COLLECTION
     *
     * When a node is removed:
     *
     * - Neighboring nodes are connected directly.
     * - The removed node is disconnected.
     * - LinkedList updates its size.
     *
     * In common OpenJDK implementations, the removed node's
     * item and links are cleared.
     *
     * This helps avoid retaining unnecessary references.
     *
     * If no reachable references remain to the removed node
     * or its element object, those objects may become eligible
     * for garbage collection.
     *
     * Removing an element does not immediately destroy it.
     *
     *
     * 37. COMMON LINKEDLIST EXCEPTIONS
     *
     * NoSuchElementException:
     * - Can occur when calling getFirst(), getLast(),
     *   removeFirst(), removeLast(), element() or remove()
     *   on an empty LinkedList, depending on the operation.
     *
     * IndexOutOfBoundsException:
     * - Invalid index supplied to indexed operations.
     *
     * ConcurrentModificationException:
     * - May be thrown when a fail-fast iterator detects
     *   unexpected structural modification.
     *
     * NullPointerException:
     * - May occur when an operation attempts to use
     *   a null reference as an object receiver or argument
     *   in a context requiring non-null.
     *
     *
     * 38. COMMON MISTAKES
     *
     * 1. Assuming LinkedList uses an array.
     *    WRONG: It uses linked nodes.
     *
     * 2. Assuming LinkedList get(index) is O(1).
     *    WRONG: It requires traversal.
     *
     * 3. Assuming every insertion is O(1).
     *    WRONG: Finding an arbitrary index can take O(n).
     *
     * 4. Assuming LinkedList always outperforms ArrayList.
     *    WRONG: Memory locality and node overhead matter.
     *
     * 5. Ignoring the first and last references.
     *    They make end operations efficient.
     *
     * 6. Assuming LinkedList is thread-safe.
     *    WRONG: It is not synchronized.
     *
     * 7. Assuming LinkedList is only a List.
     *    WRONG: It also implements Deque.
     *
     * 8. Using null in queue-style operations.
     *    WRONG: It can make null-returning methods ambiguous.
     *
     * 9. Assuming removing a node immediately destroys its data.
     *    WRONG: Garbage collection depends on reachability.
     *
     *
     * 39. REAL-WORLD USE CASES
     *
     * LinkedList can be considered for:
     *
     * - Deque operations.
     * - Queue-style processing.
     * - Adding and removing elements at both ends.
     * - Workloads where node references are already available
     *   through an appropriate traversal workflow.
     *
     * Examples:
     *
     * - A simple task queue.
     * - A deque-based workflow.
     * - A sequence that needs both-end operations.
     *
     * IMPORTANT:
     *
     * For normal queue and stack workloads, ArrayDeque is
     * often the better default.
     *
     * Do not choose LinkedList only because insertion or
     * deletion is described as O(1).
     *
     *
     * 40. INTERVIEW QUESTIONS
     *
     * FUNDAMENTALS:
     *
     * 1. What is LinkedList?
     * 2. Which interfaces does LinkedList implement?
     * 3. Is LinkedList a singly or doubly linked list?
     * 4. What is a node?
     * 5. What are head and tail references?
     *
     * INTERNAL WORKING:
     *
     * 6. How does LinkedList store elements?
     * 7. What does a node contain?
     * 8. How does LinkedList add an element at the end?
     * 9. How does LinkedList add an element at the beginning?
     * 10. How does LinkedList find an element by index?
     * 11. Why is get(index) O(n)?
     * 12. Why is addFirst() O(1)?
     * 13. Why is add(index, element) O(n) in general?
     *
     * COMPARISONS:
     *
     * 14. ArrayList vs LinkedList?
     * 15. LinkedList vs ArrayDeque?
     * 16. LinkedList vs Vector?
     *
     * ADVANCED:
     *
     * 17. Can LinkedList be used as a Queue?
     * 18. Can LinkedList be used as a Stack?
     * 19. What is the difference between List and Deque operations?
     * 20. Does LinkedList allow null?
     * 21. Is LinkedList thread-safe?
     * 22. What is fail-fast behavior?
     * 23. Why does LinkedList consume more memory?
     * 24. What is cache locality?
     *
     *
     * 41. FINAL REVISION CHECKLIST
     *
     * [ ] Understand LinkedList and its hierarchy.
     * [ ] Understand doubly linked nodes.
     * [ ] Understand first and last references.
     * [ ] Understand internal node structure.
     * [ ] Understand insertion at both ends.
     * [ ] Understand removal at both ends.
     * [ ] Understand indexed insertion and deletion.
     * [ ] Understand why indexed access is O(n).
     * [ ] Know all major time complexities.
     * [ ] Understand LinkedList as a List.
     * [ ] Understand LinkedList as a Queue.
     * [ ] Understand LinkedList as a Deque.
     * [ ] Understand stack operations using Deque.
     * [ ] Understand null and duplicate support.
     * [ ] Understand fail-fast iterators.
     * [ ] Understand memory overhead.
     * [ ] Understand cache locality.
     * [ ] Compare LinkedList with ArrayList.
     * [ ] Compare LinkedList with ArrayDeque.
     * [ ] Understand thread-safety limitations.
     * [ ] Know when to choose and avoid LinkedList.
     *
     *
     * 42. WHAT YOU SHOULD REMEMBER
     *
     * LinkedList = Doubly Linked List.
     *
     * Each node contains:
     *     Previous reference + Element + Next reference.
     *
     * LinkedList maintains:
     *     First reference + Last reference + Size.
     *
     * Implements:
     *     List + Deque.
     *
     * Allows:
     *     Duplicates + Null elements.
     *
     * Fast operations:
     *     addFirst()
     *     addLast()
     *     removeFirst()
     *     removeLast()
     *
     * Indexed operations:
     *     get(index) = O(n)
     *     add(index, element) = O(n) generally
     *     remove(index) = O(n) generally
     *
     * LinkedList is not synchronized.
     *
     * Remember:
     *
     * Insertion and deletion of a known node can be O(1),
     * but finding an arbitrary node usually takes O(n).
     *
     * ============================================================
     *                      END OF LINKEDLIST
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about LinkedList , Happy Learning ..!");
    }
}
