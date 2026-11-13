
public class Vector {

    /*
     * ============================================================
     *                           VECTOR
     * ============================================================
     *
     * 1. WHAT IS VECTOR?
     *
     * Vector is a growable-array implementation of the List
     * interface.
     *
     * Imagine an array that can automatically increase its
     * capacity when more elements are added.
     *
     * That is the basic idea behind Vector.
     *
     * Vector is similar to ArrayList, but there is one important
     * difference:
     *
     * Vector's individual methods are synchronized.
     *
     * This provides basic thread safety for individual method
     * invocations.
     *
     * Package:
     *     java.util
     *
     * In simple words:
     *
     * Vector = Resizable Array + Synchronized Methods.
     *
     *
     * 2. VECTOR DECLARATION
     *
     * Declaration:
     *
     *     public class Vector<E>
     *         extends AbstractList<E>
     *         implements List<E>, RandomAccess,
     *                    Cloneable, Serializable
     *
     * E represents the type of elements stored.
     *
     * IMPORTANT:
     * Vector is a class, whereas List is an interface.
     *
     *
     * 3. VECTOR HIERARCHY
     *
     *                    Iterable
     *                        |
     *                    Collection
     *                        |
     *                      List
     *                        |
     *                  AbstractList
     *                        |
     *                     Vector
     *                        |
     *                      Stack
     *
     * Vector also implements:
     *
     * - RandomAccess
     * - Cloneable
     * - Serializable
     *
     * Stack extends Vector.
     *
     *
     * 4. WHY WAS VECTOR INTRODUCED?
     *
     * Vector was introduced in Java 1.0.
     *
     * The modern Collections Framework was introduced in Java 1.2.
     *
     * Vector existed before the Collections Framework.
     *
     * When the Collections Framework was introduced, Vector
     * was integrated into it by implementing the List interface.
     *
     * Therefore, Vector is considered a legacy collection class.
     *
     *
     * 5. CREATING VECTOR OBJECTS
     *
     * METHOD 1: Default constructor
     *
     *     Vector<Integer> numbers = new Vector<>();
     *
     * METHOD 2: Initial capacity
     *
     *     Vector<Integer> numbers = new Vector<>(20);
     *
     * METHOD 3: Initial capacity and capacity increment
     *
     *     Vector<Integer> numbers = new Vector<>(10, 5);
     *
     * METHOD 4: From another collection
     *
     *     Vector<String> names =
     *         new Vector<>(List.of("Vinay", "Ravi"));
     *
     * METHOD 5: Using List reference
     *
     *     List<Integer> numbers = new Vector<>();
     *
     *
     * 6. INTERNAL DATA STRUCTURE
     *
     * Vector internally uses a resizable array.
     *
     * Conceptually:
     *
     *     Object[] elementData;
     *
     * It also maintains information such as:
     *
     *     int elementCount;
     *     int capacityIncrement;
     *
     * These names represent common OpenJDK implementation
     * details.
     *
     * Example:
     *
     *     Vector<Integer> numbers = new Vector<>();
     *
     *     numbers.add(10);
     *     numbers.add(20);
     *     numbers.add(30);
     *
     * Conceptual representation:
     *
     *     Index:      0       1       2       3       4
     *               [10]    [20]    [30]    [  ]    [  ]
     *
     * The first three positions contain elements.
     *
     * The remaining positions are unused capacity.
     *
     *
     * 7. SIZE VS CAPACITY
     *
     * SIZE:
     * Number of elements currently stored.
     *
     * CAPACITY:
     * Number of elements the internal array can hold
     * without growing.
     *
     * Example:
     *
     *     Vector<Integer> numbers = new Vector<>(10);
     *
     *     numbers.add(10);
     *     numbers.add(20);
     *
     *     System.out.println(numbers.size());     // 2
     *     System.out.println(numbers.capacity()); // 10
     *
     * Unlike ArrayList, Vector exposes capacity() publicly.
     *
     * IMPORTANT:
     * Size and capacity are not the same.
     *
     *
     * 8. VECTOR DEFAULT CAPACITY
     *
     * In common OpenJDK implementations, the default
     * constructor creates a Vector with:
     *
     *     Initial capacity = 10
     *
     * Example:
     *
     *     Vector<Integer> numbers = new Vector<>();
     *
     *     System.out.println(numbers.capacity()); // 10
     *
     * Unlike the default-constructed ArrayList in common
     * OpenJDK implementations, Vector allocates its initial
     * backing array during construction.
     *
     * This is an implementation detail.
     *
     *
     * 9. HOW VECTOR GROWS
     *
     * Vector supports two relevant constructor parameters:
     *
     * 1. Initial capacity.
     * 2. Capacity increment.
     *
     * Example:
     *
     *     Vector<Integer> numbers = new Vector<>(10, 5);
     *
     * Initial capacity = 10.
     * Capacity increment = 5.
     *
     * When the capacity is full and another element is added:
     *
     *     New capacity = Old capacity + Capacity increment
     *
     * Example:
     *
     *     Old capacity = 10
     *     Increment = 5
     *     New capacity = 15
     *
     * Next growth:
     *
     *     15 + 5 = 20
     *
     * IMPORTANT:
     *
     * If capacityIncrement is zero or negative, common
     * OpenJDK implementations generally double the capacity
     * when growth is required.
     *
     * This differs from the approximately 1.5x growth used
     * by common OpenJDK ArrayList implementations.
     *
     *
     * 10. VECTOR CAPACITY METHODS
     *
     * Vector provides several capacity-related methods.
     *
     * capacity():
     *     Returns the current internal capacity.
     *
     * ensureCapacity(minCapacity):
     *     Requests sufficient capacity.
     *
     * trimToSize():
     *     Requests that capacity be reduced to the current size.
     *
     * setSize(newSize):
     *     Changes the logical size of the Vector.
     *
     * IMPORTANT:
     *
     * setSize() is different from ensureCapacity().
     *
     * ensureCapacity() changes storage capacity if necessary.
     *
     * setSize() changes the number of logical elements.
     *
     *
     * 11. HOW SETSIZE() WORKS
     *
     * Example:
     *
     *     Vector<Integer> numbers = new Vector<>();
     *
     *     numbers.add(10);
     *     numbers.add(20);
     *
     *     numbers.setSize(5);
     *
     * Result:
     *
     *     [10, 20, null, null, null]
     *
     * The size becomes 5.
     *
     * New positions are filled with null references.
     *
     * If the new size is smaller:
     *
     *     numbers.setSize(1);
     *
     * Result:
     *
     *     [10]
     *
     * Elements beyond the new size are removed from
     * the logical contents.
     *
     * IMPORTANT:
     * setSize() is a Vector-specific method, not a List method.
     *
     *
     * 12. HOW VECTOR ADDITION WORKS
     *
     * Consider:
     *
     *     Vector<Integer> numbers = new Vector<>();
     *
     *     numbers.add(10);
     *     numbers.add(20);
     *
     * Conceptual process:
     *
     * 1. Acquire the Vector object's monitor.
     * 2. Check whether enough capacity is available.
     * 3. Grow the internal array if necessary.
     * 4. Insert the element.
     * 5. Increase the element count.
     * 6. Release the monitor.
     *
     * In common OpenJDK implementations, add() is synchronized.
     *
     *
     * 13. HOW VECTOR INSERTION WORKS
     *
     * Suppose:
     *
     *     [10] [20] [30] [40]
     *
     * Insert 15 at index 1:
     *
     *     numbers.add(1, 15);
     *
     * Existing elements shift right.
     *
     * Result:
     *
     *     [10] [15] [20] [30] [40]
     *
     * Time complexity:
     *
     *     add(index, element) = O(n)
     *
     * Synchronization does not remove the cost of shifting.
     *
     *
     * 14. HOW VECTOR REMOVAL WORKS
     *
     * Suppose:
     *
     *     [10] [20] [30] [40]
     *
     * Execute:
     *
     *     numbers.remove(1);
     *
     * Result:
     *
     *     [10] [30] [40]
     *
     * Remaining elements shift left.
     *
     * Time complexity:
     *
     *     remove(index) = O(n)
     *
     * The operation is synchronized in Vector's
     * corresponding public method.
     *
     *
     * 15. VECTOR IMPORTANT METHODS
     *
     * Vector provides List methods:
     *
     * add()
     * get()
     * set()
     * remove()
     * contains()
     * size()
     * isEmpty()
     * clear()
     * indexOf()
     * lastIndexOf()
     *
     * Vector also has legacy methods:
     *
     * addElement(element)
     *     Adds an element.
     *
     * elementAt(index)
     *     Returns the element at an index.
     *
     * firstElement()
     *     Returns the first element.
     *
     * lastElement()
     *     Returns the last element.
     *
     * insertElementAt(element, index)
     *     Inserts an element at an index.
     *
     * removeElement(element)
     *     Removes the first matching element.
     *
     * removeElementAt(index)
     *     Removes the element at an index.
     *
     * removeAllElements()
     *     Removes all elements.
     *
     * These older method names are retained for compatibility.
     *
     *
     * 16. VECTOR TIME COMPLEXITY
     *
     * Operation                   Complexity
     * ---------------------------------------
     * get(index)                  O(1)
     * set(index, element)         O(1)
     * add(element)                Amortized O(1)
     * add(index, element)         O(n)
     * remove(index)               O(n)
     * remove(object)              O(n)
     * contains(element)           O(n)
     * indexOf(element)             O(n)
     * Iteration                   O(n)
     *
     * These complexities describe algorithmic work.
     *
     * Synchronization adds locking overhead.
     *
     *
     * 17. WHAT DOES SYNCHRONIZED MEAN?
     *
     * Consider two threads:
     *
     *     Thread A
     *     Thread B
     *
     * Both attempt to modify the same Vector.
     *
     * Without synchronization, concurrent modifications
     * may interfere with one another.
     *
     * Synchronization provides mutual exclusion for
     * synchronized methods using the same object monitor.
     *
     * Example:
     *
     *     Vector<Integer> numbers = new Vector<>();
     *
     *     numbers.add(10);
     *
     * In common OpenJDK implementations, add() is synchronized.
     *
     * If one thread is executing a synchronized instance
     * method on this Vector, another thread must acquire
     * the same monitor before executing another synchronized
     * instance method on that Vector.
     *
     * This provides visibility and mutual exclusion
     * according to Java's monitor rules.
     *
     *
     * 18. HOW VECTOR PROVIDES BASIC THREAD SAFETY
     *
     * Imagine two threads:
     *
     * Thread A:
     *     numbers.add(10);
     *
     * Thread B:
     *     numbers.add(20);
     *
     * If both invoke synchronized methods on the same Vector,
     * they cannot execute those synchronized method bodies
     * simultaneously while holding the same monitor.
     *
     * One thread obtains the monitor first.
     * The other waits until the monitor is released.
     *
     * This protects individual synchronized operations.
     *
     *
     * 19. IMPORTANT LIMITATION:
     *     INDIVIDUAL SYNCHRONIZATION VS ATOMICITY
     *
     * Consider:
     *
     *     if (!vector.contains(10)) {
     *         vector.add(10);
     *     }
     *
     * Both contains() and add() may be synchronized.
     *
     * However, the complete sequence is not atomic.
     *
     * Possible execution:
     *
     * 1. Thread A checks contains(10): false.
     * 2. Thread B checks contains(10): false.
     * 3. Thread A adds 10.
     * 4. Thread B adds 10.
     *
     * Duplicate values may be added.
     *
     * To make the entire sequence atomic:
     *
     *     synchronized (vector) {
     *         if (!vector.contains(10)) {
     *             vector.add(10);
     *         }
     *     }
     *
     * IMPORTANT:
     *
     * Synchronizing individual methods does not automatically
     * make a sequence of multiple method calls atomic.
     *
     *
     * 20. VECTOR ITERATION
     *
     * Vector supports:
     *
     * - Iterator
     * - ListIterator
     * - Enhanced for-loop
     * - forEach()
     * - Enumeration
     *
     * Example:
     *
     *     Vector<String> names = new Vector<>();
     *
     *     names.add("Vinay");
     *     names.add("Ravi");
     *
     *     for (String name : names) {
     *         System.out.println(name);
     *     }
     *
     *
     * 21. ENUMERATION
     *
     * Enumeration is a legacy traversal interface.
     *
     * Vector provides:
     *
     *     elements()
     *
     * It returns an Enumeration.
     *
     * Example:
     *
     *     Vector<String> names = new Vector<>();
     *
     *     names.add("Vinay");
     *     names.add("Ravi");
     *
     *     Enumeration<String> enumeration =
     *         names.elements();
     *
     *     while (enumeration.hasMoreElements()) {
     *         System.out.println(enumeration.nextElement());
     *     }
     *
     * Important methods:
     *
     * hasMoreElements()
     * nextElement()
     *
     * Enumeration does not provide an element-removal method.
     *
     * It is older than Iterator.
     *
     *
     * 22. FAIL-FAST ITERATORS
     *
     * Vector's Iterator and ListIterator are generally
     * fail-fast.
     *
     * Example:
     *
     *     Vector<String> names =
     *         new Vector<>(List.of("Vinay", "Ravi", "Kiran"));
     *
     *     for (String name : names) {
     *         if (name.equals("Ravi")) {
     *             names.remove(name);
     *         }
     *     }
     *
     * This may throw ConcurrentModificationException.
     *
     * Vector's synchronized methods do not make its
     * iterators automatically synchronized.
     *
     * Fail-fast behavior is best-effort bug detection,
     * not a thread-safety guarantee.
     *
     *
     * 23. ENUMERATION VS ITERATOR
     *
     * Enumeration:
     *
     * - Legacy traversal interface.
     * - Uses hasMoreElements().
     * - Uses nextElement().
     * - Does not provide remove().
     *
     * Iterator:
     *
     * - Modern traversal interface.
     * - Uses hasNext().
     * - Uses next().
     * - Supports optional remove().
     *
     * Both can be used to traverse a Vector.
     *
     *
     * 24. VECTOR AND NULL
     *
     * Vector permits null elements.
     *
     * Example:
     *
     *     Vector<String> names = new Vector<>();
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
     *
     * 25. VECTOR AND DUPLICATES
     *
     * Vector allows duplicate elements.
     *
     * Example:
     *
     *     Vector<Integer> numbers = new Vector<>();
     *
     *     numbers.add(10);
     *     numbers.add(20);
     *     numbers.add(10);
     *
     * Result:
     *
     *     [10, 20, 10]
     *
     *
     * 26. VECTOR VS ARRAYLIST
     *
     * Feature              Vector             ArrayList
     * ---------------------------------------------------
     * Internal structure   Resizable array    Resizable array
     * Synchronization      Yes                No
     * RandomAccess         Yes                Yes
     * Duplicates           Allowed            Allowed
     * Null                 Allowed            Allowed
     * Index access         O(1)               O(1)
     * Add at end           Amortized O(1)     Amortized O(1)
     * Middle insertion     O(n)               O(n)
     * Status               Legacy             Modern
     *
     * MAIN DIFFERENCE:
     *
     * Vector synchronizes individual methods.
     * ArrayList does not.
     *
     * Therefore, ArrayList generally has less synchronization
     * overhead in single-threaded use.
     *
     *
     * 27. VECTOR VS LINKEDLIST
     *
     * Feature              Vector             LinkedList
     * ---------------------------------------------------
     * Internal structure   Resizable array    Doubly linked list
     * get(index)           O(1)               O(n)
     * Add at end           Amortized O(1)     O(1)
     * Add at front         O(n)               O(1)
     * Memory overhead      Generally lower   Generally higher
     * Synchronization      Yes                No
     *
     * They use different data structures.
     *
     *
     * 28. VECTOR VS STACK
     *
     * Vector:
     * - Growable array.
     * - Implements List.
     * - Supports indexed operations.
     *
     * Stack:
     * - Extends Vector.
     * - Represents LIFO behavior.
     * - Provides push(), pop() and peek().
     *
     * Stack inherits Vector's public operations.
     *
     * Therefore, Stack is not restricted to only stack operations.
     *
     * ArrayDeque is generally preferred for modern
     * general-purpose stack usage.
     *
     *
     * 29. VECTOR AND COLLECTIONS.SYNCHRONIZEDLIST()
     *
     * Another way to obtain a synchronized List is:
     *
     *     List<Integer> numbers =
     *         Collections.synchronizedList(
     *             new ArrayList<>()
     *         );
     *
     * This creates a synchronized wrapper around an ArrayList.
     *
     * Difference:
     *
     * Vector:
     * - A concrete class.
     * - Synchronization is built into its methods.
     *
     * synchronizedList():
     * - A wrapper around another List.
     * - Provides synchronized access through the wrapper.
     *
     * IMPORTANT:
     *
     * Iteration over a synchronized wrapper must be manually
     * synchronized on the returned List.
     *
     *     synchronized (numbers) {
     *         for (Integer number : numbers) {
     *             System.out.println(number);
     *         }
     *     }
     *
     *
     * 30. WHY VECTOR IS A LEGACY CLASS
     *
     * Vector was introduced in Java 1.0.
     *
     * It predates the modern Collections Framework.
     *
     * It was later integrated into the framework.
     *
     * Modern applications generally prefer:
     *
     * - ArrayList for general-purpose List usage.
     * - CopyOnWriteArrayList for read-heavy concurrent usage.
     * - Concurrent collections for appropriate concurrent workloads.
     *
     * Vector is not removed or obsolete in the sense of being
     * unavailable. It remains part of Java.
     *
     * Legacy means it belongs to the older collection design
     * and is generally not the preferred new-code choice.
     *
     *
     * 31. WHEN SHOULD YOU USE VECTOR?
     *
     * Vector may be appropriate when:
     *
     * - Maintaining legacy code that already uses Vector.
     * - You specifically need its synchronized method behavior.
     * - Existing APIs require Vector.
     *
     * For new applications:
     *
     * Prefer ArrayList for ordinary List operations.
     *
     * Choose a suitable concurrent collection or explicit
     * synchronization when concurrency requirements exist.
     *
     *
     * 32. WHEN SHOULD YOU AVOID VECTOR?
     *
     * Avoid choosing Vector by default when:
     *
     * - You do not need synchronized methods.
     * - You want a modern general-purpose List.
     * - You need efficient concurrent read-heavy iteration.
     * - You require a specialized concurrent data structure.
     *
     * Remember:
     *
     * Synchronized individual methods do not automatically
     * solve every concurrency problem.
     *
     *
     * 33. COMMON VECTOR EXCEPTIONS
     *
     * ArrayIndexOutOfBoundsException:
     * - Can occur for invalid indexes in Vector's legacy
     *   elementAt(), insertElementAt() and related methods.
     *
     * IndexOutOfBoundsException:
     * - Can occur for invalid List positional operations.
     *
     * IllegalArgumentException:
     * - Negative initial capacity or invalid constructor
     *   capacity parameters.
     *
     * ConcurrentModificationException:
     * - May occur when a fail-fast iterator detects
     *   unexpected structural modification.
     *
     * NoSuchElementException:
     * - May occur when a traversal operation requests
     *   another element after traversal has ended.
     *
     *
     * 34. COMMON MISTAKES
     *
     * 1. Assuming Vector and ArrayList use different
     *    internal data structures.
     *    WRONG: Both use resizable arrays.
     *
     * 2. Assuming Vector makes every sequence of operations atomic.
     *    WRONG: Individual synchronization is not enough.
     *
     * 3. Assuming Vector's iterators are synchronized.
     *    WRONG: Iterators require appropriate coordination.
     *
     * 4. Assuming Vector is completely obsolete.
     *    WRONG: It is still available in Java.
     *
     * 5. Assuming capacity() returns the number of elements.
     *    WRONG: size() returns the number of elements.
     *
     * 6. Confusing setSize() and ensureCapacity().
     *    setSize() changes logical size.
     *    ensureCapacity() requests backing storage.
     *
     * 7. Assuming Vector is always the best thread-safe List.
     *    WRONG: Workload-specific concurrent collections
     *    may be more suitable.
     *
     * 8. Assuming synchronization removes algorithmic costs.
     *    WRONG: Shifting elements still costs O(n).
     *
     *
     * 35. REAL-WORLD USE CASES
     *
     * Vector may appear in:
     *
     * - Older Java applications.
     * - Legacy APIs.
     * - Existing codebases using synchronized collection methods.
     *
     * For modern backend development:
     *
     * - ArrayList is generally preferred for ordinary lists.
     * - CopyOnWriteArrayList can suit read-heavy concurrent data.
     * - Other concurrent collections may suit specialized workloads.
     *
     *
     * 36. INTERVIEW QUESTIONS
     *
     * FUNDAMENTALS:
     *
     * 1. What is Vector?
     * 2. Which interface does Vector implement?
     * 3. Is Vector a legacy class?
     * 4. When was Vector introduced?
     * 5. Does Vector allow duplicates and null?
     *
     * INTERNAL WORKING:
     *
     * 6. Which data structure does Vector use internally?
     * 7. What is the default capacity?
     * 8. What is the difference between size and capacity?
     * 9. How does Vector grow?
     * 10. What is capacityIncrement?
     * 11. What happens when capacityIncrement is zero?
     *
     * CONCURRENCY:
     *
     * 12. Why is Vector synchronized?
     * 13. What does synchronized mean?
     * 14. Is Vector completely thread-safe?
     * 15. Why is a multi-step operation not automatically atomic?
     * 16. Are Vector iterators synchronized?
     *
     * COMPARISONS:
     *
     * 17. Vector vs ArrayList?
     * 18. Vector vs LinkedList?
     * 19. Vector vs Stack?
     * 20. Vector vs synchronizedList()?
     *
     *
     * 37. FINAL REVISION CHECKLIST
     *
     * [ ] Understand Vector and its hierarchy.
     * [ ] Know why Vector is called a legacy class.
     * [ ] Understand its internal resizable array.
     * [ ] Understand size versus capacity.
     * [ ] Know the default initial capacity.
     * [ ] Understand capacityIncrement.
     * [ ] Understand Vector's growth behavior.
     * [ ] Know capacity(), ensureCapacity() and trimToSize().
     * [ ] Understand setSize().
     * [ ] Understand synchronized methods.
     * [ ] Understand synchronization versus atomicity.
     * [ ] Understand Enumeration and Iterator.
     * [ ] Understand fail-fast behavior.
     * [ ] Know Vector's time complexities.
     * [ ] Compare Vector with ArrayList.
     * [ ] Compare Vector with LinkedList.
     * [ ] Compare Vector with Stack.
     * [ ] Understand when to use and avoid Vector.
     *
     *
     * 38. WHAT YOU SHOULD REMEMBER
     *
     * Vector = Synchronized Resizable Array.
     *
     * Introduced in Java 1.0.
     *
     * Integrated into the Collections Framework in Java 1.2.
     *
     * Implements:
     *     List + RandomAccess.
     *
     * Extends:
     *     AbstractList.
     *
     * Supports:
     *     Duplicates + Null elements + Index access.
     *
     * Default capacity in common OpenJDK implementations:
     *     10.
     *
     * Individual methods:
     *     Synchronized.
     *
     * Growth:
     *     Capacity increment if positive;
     *     otherwise, commonly doubles when full.
     *
     * Modern general-purpose alternative:
     *     ArrayList.
     *
     * IMPORTANT:
     * Synchronization of individual methods does not guarantee
     * atomicity of multiple method calls.
     *
     * ============================================================
     *                       END OF VECTOR
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Vector , Happy Learning ..!");
    }
}
