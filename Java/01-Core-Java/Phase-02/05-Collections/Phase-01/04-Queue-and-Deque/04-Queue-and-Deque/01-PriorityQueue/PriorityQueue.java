
public class PriorityQueue {

    /*
     * ============================================================
     *                       PRIORITY QUEUE
     * ============================================================
     *
     * 1. WHAT IS A PRIORITY QUEUE?
     *
     * A normal queue generally follows FIFO:
     * First In, First Out.
     *
     * But what if elements should be processed according to
     * their priority instead of their insertion order?
     *
     * Example:
     *
     * Imagine patients arriving at a hospital:
     *
     * Patient A -> Normal condition
     * Patient B -> Critical condition
     * Patient C -> Serious condition
     *
     * Even if Patient A arrives first, Patient B may need
     * immediate attention because the condition is critical.
     *
     * This is the basic idea behind a PriorityQueue.
     *
     * A PriorityQueue processes elements according to their
     * priority rather than simply their insertion order.
     *
     * In Java, PriorityQueue is a class in java.util.
     *
     * It implements the Queue interface.
     *
     *
     * 2. FIFO QUEUE VS PRIORITY QUEUE
     *
     * Normal FIFO Queue:
     *
     * Insert: 40, 10, 30, 20
     *
     * Removal order:
     * 40 -> 10 -> 30 -> 20
     *
     * PriorityQueue with natural integer ordering:
     *
     * Insert: 40, 10, 30, 20
     *
     * Removal order:
     * 10 -> 20 -> 30 -> 40
     *
     * Why?
     *
     * Java's PriorityQueue uses natural ordering by default.
     * For numbers, the smallest number has the highest priority.
     *
     * Important:
     * PriorityQueue does not mean that the largest element
     * automatically has the highest priority.
     * The ordering depends on natural ordering or the Comparator
     * supplied during construction.
     *
     *
     * 3. CREATING A PRIORITY QUEUE
     *
     * Import:
     *
     * import java.util.PriorityQueue;
     *
     * Example:
     *
     * PriorityQueue<Integer> pq = new PriorityQueue<>();
     *
     * Insert elements:
     *
     * pq.offer(40);
     * pq.offer(10);
     * pq.offer(30);
     * pq.offer(20);
     *
     * Remove elements:
     *
     * while (!pq.isEmpty()) {
     *     System.out.println(pq.poll());
     * }
     *
     * Output:
     *
     * 10
     * 20
     * 30
     * 40
     *
     *
     * 4. INTERNAL DATA STRUCTURE: BINARY HEAP
     *
     * How does Java maintain priority efficiently?
     *
     * PriorityQueue is internally backed by a resizable array
     * that represents a binary heap.
     *
     * A binary heap is a complete binary tree.
     *
     * Complete binary tree:
     *
     * - Every level is completely filled except possibly
     *   the last level.
     * - The last level is filled from left to right.
     *
     * Java's default PriorityQueue maintains a MIN-HEAP.
     *
     * MIN-HEAP PROPERTY:
     *
     * Every parent element is less than or equal to its children.
     *
     * Example:
     *
     *               10
     *             /    \
     *           20      30
     *          /  \
     *        40    50
     *
     * The smallest element is at the root.
     *
     * Therefore:
     *
     * peek() -> 10
     * poll() -> 10
     *
     * Important:
     * This is one valid heap arrangement, not the only possible
     * internal arrangement for those elements.
     *
     *
     * 5. HOW IS A HEAP STORED IN AN ARRAY?
     *
     * A binary heap does not require separate Node objects
     * connected by left and right references.
     *
     * It can be represented using an array.
     *
     * Example tree:
     *
     *               10
     *             /    \
     *           20      30
     *          /  \
     *        40    50
     *
     * Array representation:
     *
     * Index:   0   1   2   3   4
     * Value:  10  20  30  40  50
     *
     * For a node at index i:
     *
     * Parent index:
     * (i - 1) / 2
     *
     * Left child index:
     * 2 * i + 1
     *
     * Right child index:
     * 2 * i + 2
     *
     * These formulas use integer division for the parent index.
     *
     * Example:
     *
     * For index 1:
     *
     * Parent = (1 - 1) / 2 = 0
     * Left   = 2 * 1 + 1 = 3
     * Right  = 2 * 1 + 2 = 4
     *
     *
     * 6. HOW INSERTION WORKS: OFFER()
     *
     * Suppose we insert:
     *
     * 10, 20, 30, 5
     *
     * Step 1:
     *
     * Insert 10:
     *
     *       10
     *
     * Step 2:
     *
     * Insert 20:
     *
     *       10
     *      /
     *     20
     *
     * Step 3:
     *
     * Insert 30:
     *
     *       10
     *      /  \
     *     20  30
     *
     * Step 4:
     *
     * Insert 5 at the next available position:
     *
     *         10
     *        /  \
     *       20  30
     *      /
     *     5
     *
     * But the min-heap property is violated because 5 is
     * smaller than its parent 20.
     *
     * The heap performs SIFT UP (also called bubble up).
     *
     * Compare 5 with 20 and swap:
     *
     *         10
     *        /  \
     *        5  30
     *       /
     *      20
     *
     * Compare 5 with 10 and swap:
     *
     *          5
     *        /   \
     *      10    30
     *     /
     *    20
     *
     * Now the min-heap property is satisfied.
     *
     * Insertion complexity:
     * O(log n) worst case.
     *
     *
     * 7. HOW REMOVAL WORKS: POLL()
     *
     * poll() removes the root, which is the highest-priority
     * element.
     *
     * Consider:
     *
     *          5
     *        /   \
     *      10    30
     *     /  \
     *    20   25
     *
     * Step 1:
     * Remove root 5.
     *
     * Step 2:
     * Move the last element, 25, to the root.
     *
     *          25
     *        /    \
     *      10     30
     *     /
     *    20
     *
     * Step 3:
     * Restore the heap property using SIFT DOWN
     * (also called bubble down).
     *
     * Compare 25 with its children.
     * The smaller child is 10, so swap.
     *
     *          10
     *        /    \
     *      25     30
     *     /
     *    20
     *
     * Compare 25 with its child 20 and swap:
     *
     *          10
     *        /    \
     *      20     30
     *     /
     *    25
     *
     * The heap property is restored.
     *
     * Removal complexity:
     * O(log n) worst case.
     *
     *
     * 8. MIN-HEAP VS MAX-HEAP
     *
     * MIN-HEAP:
     *
     * Parent <= children.
     * Smallest element is at the root.
     *
     * Example:
     *
     *          10
     *         /  \
     *        20  30
     *
     * MAX-HEAP:
     *
     * Parent >= children.
     * Largest element is at the root.
     *
     * Example:
     *
     *          30
     *         /  \
     *        20  10
     *
     * Java PriorityQueue uses min-heap behavior by default.
     *
     * To get max-heap behavior, supply a reverse Comparator.
     *
     *
     * 9. CREATING A MAX-HEAP
     *
     * Example:
     *
     * import java.util.PriorityQueue;
     * import java.util.Comparator;
     *
     * PriorityQueue<Integer> maxHeap =
     *     new PriorityQueue<>(Comparator.reverseOrder());
     *
     * maxHeap.offer(10);
     * maxHeap.offer(40);
     * maxHeap.offer(20);
     * maxHeap.offer(30);
     *
     * while (!maxHeap.isEmpty()) {
     *     System.out.println(maxHeap.poll());
     * }
     *
     * Output:
     *
     * 40
     * 30
     * 20
     * 10
     *
     * Comparator.reverseOrder() reverses natural ordering.
     *
     * Another option:
     *
     * PriorityQueue<Integer> maxHeap =
     *     new PriorityQueue<>((a, b) -> b.compareTo(a));
     *
     * Prefer Comparator.reverseOrder() when it expresses
     * the required ordering clearly.
     *
     *
     * 10. USING A CUSTOM COMPARATOR
     *
     * PriorityQueue can store custom objects.
     *
     * Example:
     *
     * class Student {
     *     int marks;
     *     String name;
     *
     *     Student(String name, int marks) {
     *         this.name = name;
     *         this.marks = marks;
     *     }
     *
     *     public String toString() {
     *         return name + " - " + marks;
     *     }
     * }
     *
     * PriorityQueue<Student> pq =
     *     new PriorityQueue<>(
     *         (a, b) -> Integer.compare(a.marks, b.marks)
     *     );
     *
     * pq.offer(new Student("A", 80));
     * pq.offer(new Student("B", 95));
     * pq.offer(new Student("C", 70));
     *
     * while (!pq.isEmpty()) {
     *     System.out.println(pq.poll());
     * }
     *
     * Output:
     *
     * C - 70
     * A - 80
     * B - 95
     *
     * The student with the lowest marks is processed first.
     *
     * To process the highest marks first:
     *
     * PriorityQueue<Student> pq =
     *     new PriorityQueue<>(
     *         (a, b) -> Integer.compare(b.marks, a.marks)
     *     );
     *
     * IMPORTANT:
     *
     * Use Integer.compare() rather than:
     *
     * (a, b) -> a.marks - b.marks
     *
     * Subtraction can overflow for extreme integer values and
     * produce an incorrect comparison result.
     *
     *
     * 11. COMPARABLE VS COMPARATOR
     *
     * Comparable:
     *
     * - Defines the natural ordering of a class.
     * - Implemented by the class itself.
     * - Uses compareTo().
     *
     * Comparator:
     *
     * - Defines an external or custom ordering.
     * - Can be supplied when constructing PriorityQueue.
     * - Uses compare().
     *
     * Example:
     *
     * class Student implements Comparable<Student> {
     *
     *     int marks;
     *
     *     public int compareTo(Student other) {
     *         return Integer.compare(this.marks, other.marks);
     *     }
     * }
     *
     * PriorityQueue<Student> pq = new PriorityQueue<>();
     *
     * The natural ordering is used.
     *
     * If you need different orderings in different situations,
     * Comparator is usually more flexible.
     *
     *
     * 12. IMPORTANT PRIORITYQUEUE METHODS
     *
     * offer(element)
     * - Inserts an element.
     * - O(log n) worst case.
     *
     * add(element)
     * - Inserts an element.
     * - Same core insertion behavior as offer().
     *
     * poll()
     * - Removes and returns the head.
     * - O(log n).
     * - Returns null if empty.
     *
     * remove()
     * - Removes and returns the head.
     * - O(log n).
     * - Throws NoSuchElementException if empty.
     *
     * peek()
     * - Returns the head without removing it.
     * - O(1).
     * - Returns null if empty.
     *
     * element()
     * - Returns the head without removing it.
     * - O(1).
     * - Throws NoSuchElementException if empty.
     *
     * size()
     * - Returns the number of elements.
     * - O(1).
     *
     * contains(object)
     * - Checks whether an element exists.
     * - O(n).
     *
     * remove(object)
     * - Removes one matching element.
     * - O(n).
     *
     * clear()
     * - Removes all elements.
     *
     * Important:
     * PriorityQueue does not provide an efficient operation to
     * find or remove an arbitrary element by value.
     *
     *
     * 13. ITERATION ORDER IS NOT SORTED ORDER
     *
     * This is a very important interview point.
     *
     * Consider:
     *
     * PriorityQueue<Integer> pq = new PriorityQueue<>();
     *
     * pq.offer(40);
     * pq.offer(10);
     * pq.offer(30);
     * pq.offer(20);
     *
     * System.out.println(pq);
     *
     * The printed order is NOT guaranteed to be:
     *
     * [10, 20, 30, 40]
     *
     * Why?
     *
     * The internal array only needs to satisfy the heap property.
     * It is not a fully sorted array.
     *
     * Similarly:
     *
     * for (Integer value : pq) {
     *     System.out.println(value);
     * }
     *
     * The iterator does not guarantee priority order.
     *
     * To retrieve elements in priority order:
     *
     * while (!pq.isEmpty()) {
     *     System.out.println(pq.poll());
     * }
     *
     * Note:
     * This empties the original queue.
     *
     * If you need to preserve it, iterate over a copy:
     *
     * PriorityQueue<Integer> copy = new PriorityQueue<>(pq);
     *
     * while (!copy.isEmpty()) {
     *     System.out.println(copy.poll());
     * }
     *
     *
     * 14. DUPLICATE ELEMENTS
     *
     * PriorityQueue permits duplicate elements.
     *
     * Example:
     *
     * PriorityQueue<Integer> pq = new PriorityQueue<>();
     *
     * pq.offer(10);
     * pq.offer(10);
     * pq.offer(20);
     *
     * The queue contains three elements.
     *
     * Removal:
     *
     * 10 -> 10 -> 20
     *
     * Unlike Set, PriorityQueue does not enforce uniqueness.
     *
     *
     * 15. NULL ELEMENTS
     *
     * PriorityQueue does not permit null elements.
     *
     * Example:
     *
     * PriorityQueue<Integer> pq = new PriorityQueue<>();
     *
     * pq.offer(null);
     *
     * This throws NullPointerException.
     *
     * Why?
     *
     * PriorityQueue needs to compare elements to maintain
     * heap ordering. Null does not have a natural ordering.
     *
     * Also, poll() and peek() use null to indicate that the
     * queue is empty.
     *
     *
     * 16. CAPACITY AND RESIZING
     *
     * PriorityQueue is an unbounded priority queue.
     *
     * It grows automatically when more elements are inserted.
     *
     * Example:
     *
     * PriorityQueue<Integer> pq = new PriorityQueue<>();
     *
     * The default initial capacity is 11.
     *
     * You can also specify an initial capacity:
     *
     * PriorityQueue<Integer> pq = new PriorityQueue<>(50);
     *
     * Important:
     *
     * Initial capacity is not a maximum capacity.
     * The queue can grow beyond it.
     *
     * The growth policy is an implementation detail and may
     * vary across Java versions.
     *
     *
     * 17. CONSTRUCTORS
     *
     * Common constructors:
     *
     * PriorityQueue()
     *
     * Creates a queue with default initial capacity and
     * natural ordering.
     *
     * PriorityQueue(int initialCapacity)
     *
     * Creates a queue with a specified initial capacity.
     *
     * PriorityQueue(Comparator<? super E> comparator)
     *
     * Creates a queue using the supplied comparator.
     *
     * PriorityQueue(int initialCapacity,
     *               Comparator<? super E> comparator)
     *
     * Specifies both initial capacity and comparator.
     *
     * PriorityQueue(Collection<? extends E> collection)
     *
     * Creates a queue containing elements from a collection.
     *
     * Other constructors accept SortedSet and PriorityQueue.
     *
     *
     * 18. TIME COMPLEXITY
     *
     * Operation                 Complexity
     *
     * peek()                    O(1)
     * element()                 O(1)
     * offer()                   O(log n) worst case
     * add()                     O(log n) worst case
     * poll()                    O(log n)
     * remove()                  O(log n)
     * contains()                O(n)
     * remove(Object)            O(n)
     * size()                    O(1)
     *
     * Building a heap from an existing collection can take
     * O(n) time when heapified.
     *
     * Repeatedly inserting n elements takes O(n log n)
     * in the worst case.
     *
     *
     * 19. PRIORITYQUEUE VS ARRAYLIST
     *
     * ArrayList:
     *
     * - Maintains insertion order.
     * - Supports index-based access.
     * - Does not automatically maintain priority ordering.
     *
     * PriorityQueue:
     *
     * - Maintains heap ordering.
     * - Provides efficient access to the highest-priority element.
     * - Does not support index-based access.
     * - Iterator order is not guaranteed to be sorted.
     *
     *
     * 20. PRIORITYQUEUE VS TREESET
     *
     * PriorityQueue:
     *
     * - Allows duplicate elements.
     * - Provides efficient access to the head.
     * - Does not keep all elements fully sorted.
     *
     * TreeSet:
     *
     * - Does not allow duplicate elements according to its
     *   comparison/equality ordering.
     * - Maintains elements in sorted order.
     * - Supports navigation and range operations.
     *
     *
     * 21. REAL-WORLD USE CASES
     *
     * - Hospital emergency scheduling.
     * - CPU/task scheduling models.
     * - Dijkstra's shortest-path algorithm.
     * - Prim's minimum spanning tree algorithm.
     * - Huffman coding.
     * - Finding the K largest or K smallest elements.
     * - Merging multiple sorted lists.
     * - Event simulation.
     *
     *
     * 22. IMPORTANT LIMITATIONS
     *
     * - Not thread-safe.
     * - Does not allow null.
     * - Does not provide efficient arbitrary searching.
     * - Does not guarantee sorted iteration.
     * - Does not support index-based access.
     * - Elements should not be mutated in a way that changes
     *   their ordering while they are inside the queue.
     *
     * If an object's priority field changes after insertion,
     * the heap is not automatically rebuilt.
     *
     * Remove and reinsert the object, or use a different design,
     * when its priority changes.
     *
     * For concurrent priority-based processing, Java provides
     * PriorityBlockingQueue.
     *
     *
     * 23. COMMON INTERVIEW QUESTIONS
     *
     * 1. What is a PriorityQueue?
     * 2. Which data structure is used internally?
     * 3. What is a binary heap?
     * 4. What is the difference between min-heap and max-heap?
     * 5. What is the default ordering of PriorityQueue?
     * 6. How do you create a max-heap?
     * 7. What is the time complexity of offer() and poll()?
     * 8. Why is peek() O(1)?
     * 9. Does PriorityQueue allow duplicates?
     * 10. Does PriorityQueue allow null?
     * 11. Is PriorityQueue thread-safe?
     * 12. Does its iterator return sorted elements?
     * 13. How do Comparable and Comparator differ?
     * 14. How can you store custom objects?
     * 15. What is heapify?
     * 16. What are sift-up and sift-down?
     * 17. How is a binary heap represented in an array?
     * 18. Why is arbitrary removal O(n)?
     * 19. What happens if an object's priority changes?
     * 20. What is PriorityBlockingQueue?
     *
     *
     * 24. QUICK REVISION
     *
     * - PriorityQueue implements Queue.
     * - It is backed by a binary heap.
     * - Default behavior is min-heap.
     * - Smallest element is at the head by natural ordering.
     * - Comparator can customize ordering.
     * - Duplicates are allowed.
     * - Null is not allowed.
     * - offer() and poll() are O(log n) worst case.
     * - peek() is O(1).
     * - contains() and remove(Object) are O(n).
     * - Iterator order is not guaranteed to be sorted.
     * - It is not thread-safe.
     *
     * ============================================================
     *                    END OF PRIORITY QUEUE
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Priority Queue , Happy Learning ..!");
    }
}
