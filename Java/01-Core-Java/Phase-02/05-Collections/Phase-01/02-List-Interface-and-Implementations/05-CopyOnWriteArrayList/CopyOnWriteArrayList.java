
public class CopyOnWriteArrayList {

    /*
     * ============================================================
     *                    COPYONWRITEARRAYLIST
     * ============================================================
     *
     * 1. WHAT IS COPYONWRITEARRAYLIST?
     *
     * Imagine a list shared by multiple threads.
     *
     * Many threads read the list, but only a few threads
     * occasionally modify it.
     *
     * With an ordinary ArrayList, concurrent modifications
     * require external synchronization.
     *
     * CopyOnWriteArrayList provides a different approach.
     *
     * Whenever a thread modifies the list, it creates a new
     * copy of the underlying array, performs the modification
     * on that copy, and then publishes the new array.
     *
     * Readers can continue reading the previous array safely.
     *
     * This technique is called:
     *
     *     COPY-ON-WRITE (COW)
     *
     * In simple words:
     *
     * Read  -> Use the existing array.
     * Write -> Create a modified copy and publish it.
     *
     *
     * 2. PACKAGE AND DECLARATION
     *
     * Package:
     *     java.util.concurrent
     *
     * Declaration:
     *
     *     public class CopyOnWriteArrayList<E>
     *         implements List<E>, RandomAccess,
     *                    Cloneable, Serializable
     *
     * E represents the element type.
     *
     * IMPORTANT:
     *
     * CopyOnWriteArrayList is a class.
     *
     * It implements List and RandomAccess.
     *
     * It belongs to the Java concurrency utilities.
     *
     *
     * 3. WHY WAS COPYONWRITEARRAYLIST INTRODUCED?
     *
     * Consider a list of application listeners:
     *
     *     List<Listener> listeners;
     *
     * Many threads may read or notify these listeners.
     *
     * Occasionally, a listener may be registered or removed.
     *
     * We want:
     *
     * - Safe concurrent reads.
     * - Safe concurrent modifications.
     * - Readers not to block while a writer modifies the list.
     *
     * CopyOnWriteArrayList is designed for this kind of
     * read-heavy workload.
     *
     * Its main trade-off:
     *
     * Reads are efficient.
     * Writes are relatively expensive.
     *
     *
     * 4. CREATING COPYONWRITEARRAYLIST
     *
     * METHOD 1: Default constructor
     *
     *     CopyOnWriteArrayList<String> names =
     *         new CopyOnWriteArrayList<>();
     *
     * METHOD 2: From another collection
     *
     *     CopyOnWriteArrayList<String> names =
     *         new CopyOnWriteArrayList<>(
     *             List.of("Vinay", "Ravi")
     *         );
     *
     * METHOD 3: From an array
     *
     *     String[] values = {"A", "B", "C"};
     *
     *     CopyOnWriteArrayList<String> names =
     *         new CopyOnWriteArrayList<>(values);
     *
     * METHOD 4: Using List reference
     *
     *     List<String> names =
     *         new CopyOnWriteArrayList<>();
     *
     *
     * 5. INTERNAL DATA STRUCTURE
     *
     * CopyOnWriteArrayList internally uses an array.
     *
     * In OpenJDK implementations, it maintains a reference
     * to a volatile Object[] backing array.
     *
     * Conceptually:
     *
     *     private transient volatile Object[] array;
     *
     * The volatile reference helps ensure that threads
     * observe a properly published array reference.
     *
     * IMPORTANT:
     *
     * The array itself is not modified in place after
     * publication.
     *
     * A writer creates a replacement array.
     *
     *
     * 6. UNDERSTANDING COPY-ON-WRITE
     *
     * Suppose:
     *
     *     CopyOnWriteArrayList<Integer> numbers =
     *         new CopyOnWriteArrayList<>();
     *
     *     numbers.add(10);
     *     numbers.add(20);
     *     numbers.add(30);
     *
     * Conceptual backing array:
     *
     *     [10] [20] [30]
     *
     * Now execute:
     *
     *     numbers.add(40);
     *
     * Copy-on-write process:
     *
     * 1. Acquire the writer lock.
     * 2. Read the current array.
     * 3. Create a new array with additional space.
     * 4. Copy the existing elements.
     * 5. Add 40 to the new array.
     * 6. Publish the new array reference.
     * 7. Release the writer lock.
     *
     * Before:
     *
     *     Old array -> [10] [20] [30]
     *
     * After:
     *
     *     Old array -> [10] [20] [30]
     *
     *     New array -> [10] [20] [30] [40]
     *
     * The list now points to the new array.
     *
     * The old array remains available to readers that
     * already obtained a reference to it.
     *
     * Once no references to the old array remain,
     * it becomes eligible for garbage collection.
     *
     *
     * 7. HOW ADD() WORKS
     *
     * Example:
     *
     *     CopyOnWriteArrayList<Integer> numbers =
     *         new CopyOnWriteArrayList<>();
     *
     *     numbers.add(10);
     *     numbers.add(20);
     *
     * Each successful add creates and publishes a new
     * backing array.
     *
     * Therefore, adding an element is generally O(n)
     * because existing references must be copied.
     *
     * IMPORTANT:
     *
     * Unlike ArrayList, CopyOnWriteArrayList does not
     * simply reuse spare capacity for ordinary writes.
     *
     * Its copy-on-write design makes modifications expensive.
     *
     *
     * 8. HOW SET() WORKS
     *
     * set(index, element) replaces an element.
     *
     * Example:
     *
     *     CopyOnWriteArrayList<Integer> numbers =
     *         new CopyOnWriteArrayList<>(
     *             List.of(10, 20, 30)
     *         );
     *
     *     numbers.set(1, 200);
     *
     * Result:
     *
     *     [10, 200, 30]
     *
     * Conceptually:
     *
     * 1. Create a new array.
     * 2. Copy the existing elements.
     * 3. Replace the requested position.
     * 4. Publish the new array.
     *
     * Time complexity:
     *
     *     O(n)
     *
     *
     * 9. HOW REMOVE() WORKS
     *
     * Example:
     *
     *     CopyOnWriteArrayList<Integer> numbers =
     *         new CopyOnWriteArrayList<>(
     *             List.of(10, 20, 30)
     *         );
     *
     *     numbers.remove(Integer.valueOf(20));
     *
     * Result:
     *
     *     [10, 30]
     *
     * Conceptually:
     *
     * 1. Locate the element.
     * 2. Create a new array without that element.
     * 3. Copy the remaining references.
     * 4. Publish the new array.
     *
     * Time complexity:
     *
     *     O(n)
     *
     *
     * 10. HOW READ OPERATIONS WORK
     *
     * Consider:
     *
     *     CopyOnWriteArrayList<Integer> numbers =
     *         new CopyOnWriteArrayList<>(
     *             List.of(10, 20, 30)
     *         );
     *
     *     int value = numbers.get(1);
     *
     * The reader accesses the currently published array.
     *
     * It does not need to lock against ordinary writers
     * for this read operation.
     *
     * Common read operations:
     *
     *     get()
     *     size()
     *     contains()
     *     indexOf()
     *     toArray()
     *
     * Typical complexity:
     *
     *     get(index) = O(1)
     *     size()     = O(1)
     *     contains() = O(n)
     *
     *
     * 11. WHY ARE READS EFFICIENT?
     *
     * Readers access an already-published array.
     *
     * Writers do not modify that published array in place.
     *
     * Therefore, readers can continue using a stable
     * snapshot while a writer prepares another array.
     *
     * This avoids reader-writer locking for ordinary reads.
     *
     * IMPORTANT:
     *
     * Efficient concurrent reads do not mean every operation
     * in every usage pattern is lock-free.
     *
     *
     * 12. WHAT IS A SNAPSHOT ITERATOR?
     *
     * This is one of the most important concepts.
     *
     * An iterator created from CopyOnWriteArrayList observes
     * the array snapshot that existed when the iterator
     * was created.
     *
     * Example:
     *
     *     CopyOnWriteArrayList<String> names =
     *         new CopyOnWriteArrayList<>();
     *
     *     names.add("Vinay");
     *     names.add("Ravi");
     *
     *     Iterator<String> iterator = names.iterator();
     *
     *     names.add("Kiran");
     *
     *     while (iterator.hasNext()) {
     *         System.out.println(iterator.next());
     *     }
     *
     * Output:
     *
     *     Vinay
     *     Ravi
     *
     * The iterator does not see Kiran.
     *
     * WHY?
     *
     * The iterator retains a reference to the array
     * that existed when it was created.
     *
     * It does not automatically switch to the newly
     * published array.
     *
     *
     * 13. SNAPSHOT ITERATOR VS FAIL-FAST ITERATOR
     *
     * ArrayList:
     *
     * - Iterator is generally fail-fast.
     * - Unexpected structural modifications may cause
     *   ConcurrentModificationException.
     *
     * CopyOnWriteArrayList:
     *
     * - Iterator is snapshot-based.
     * - It observes the original array snapshot.
     * - It does not throw ConcurrentModificationException
     *   because of concurrent modifications to the list.
     *
     * IMPORTANT:
     *
     * Snapshot iterators do not reflect changes made
     * to the List after iterator creation.
     *
     *
     * 14. CAN WE REMOVE ELEMENTS USING ITS ITERATOR?
     *
     * No.
     *
     * CopyOnWriteArrayList iterators do not support
     * iterator-based modification.
     *
     * Example:
     *
     *     Iterator<String> iterator = names.iterator();
     *
     *     iterator.next();
     *
     *     iterator.remove();
     *
     * This throws:
     *
     *     UnsupportedOperationException
     *
     * Similarly, ListIterator modification methods such as
     * add() and set() are unsupported.
     *
     * To modify the List, use its own methods:
     *
     *     names.remove("Ravi");
     *
     *
     * 15. CONCURRENT READS AND WRITES
     *
     * Imagine:
     *
     *     Thread A -> Reading elements.
     *     Thread B -> Adding an element.
     *
     * Thread A can continue reading its existing snapshot.
     *
     * Thread B creates and publishes a new array.
     *
     * The reader does not need to observe a partially
     * modified array.
     *
     * This is the central benefit of copy-on-write.
     *
     *
     * 16. HOW WRITERS ARE SYNCHRONIZED
     *
     * CopyOnWriteArrayList allows concurrent readers,
     * but coordinates writers.
     *
     * In common OpenJDK implementations, write operations
     * use a lock to prevent conflicting updates.
     *
     * Example:
     *
     *     Thread A -> add(10)
     *     Thread B -> add(20)
     *
     * Both writers cannot independently publish arrays
     * based on the same old state without coordination.
     *
     * The write lock serializes modifications.
     *
     * IMPORTANT:
     *
     * CopyOnWriteArrayList is thread-safe, but its write
     * operations are not intended to execute concurrently
     * without coordination.
     *
     *
     * 17. COPYONWRITEARRAYLIST TIME COMPLEXITY
     *
     * Operation                 Typical complexity
     * ---------------------------------------------
     * get(index)                O(1)
     * size()                    O(1)
     * contains(element)         O(n)
     * indexOf(element)          O(n)
     * add(element)              O(n)
     * add(index, element)       O(n)
     * set(index, element)       O(n)
     * remove(index)             O(n)
     * remove(object)            O(n)
     * Iteration                 O(n)
     *
     * IMPORTANT:
     *
     * These are typical algorithmic costs.
     * Actual performance also depends on locking,
     * allocation, copying, element equality and garbage collection.
     *
     *
     * 18. COPYONWRITEARRAYLIST AND MEMORY
     *
     * Every write may allocate a new backing array.
     *
     * Example:
     *
     *     Old array -> [10] [20] [30]
     *     New array -> [10] [20] [30] [40]
     *
     * During the transition, both arrays may remain reachable.
     *
     * This creates temporary additional memory usage.
     *
     * If many writes happen frequently:
     *
     * - Many arrays may be allocated.
     * - More references must be copied.
     * - Garbage collection pressure may increase.
     *
     * Therefore, CopyOnWriteArrayList is not suitable
     * for write-heavy workloads.
     *
     *
     * 19. WHEN SHOULD YOU USE IT?
     *
     * Prefer CopyOnWriteArrayList when:
     *
     * - Reads greatly outnumber writes.
     * - The collection is relatively small.
     * - Readers need stable snapshots.
     * - Concurrent traversal is frequent.
     * - Occasional writes are acceptable.
     *
     * Examples:
     *
     * - Event listeners.
     * - Registered callbacks.
     * - Configuration listeners.
     * - Observer lists.
     * - Read-mostly application metadata.
     *
     *
     * 20. WHEN SHOULD YOU AVOID IT?
     *
     * Avoid CopyOnWriteArrayList when:
     *
     * - Writes happen frequently.
     * - The collection is very large.
     * - Many elements are continuously added or removed.
     * - You require iterators to reflect live changes.
     * - Copying the backing array is too expensive.
     *
     * Alternatives depend on the use case:
     *
     * - ArrayList with external synchronization.
     * - Collections.synchronizedList().
     * - ConcurrentLinkedQueue for queue-based workloads.
     * - Other suitable concurrent collections.
     *
     *
     * 21. COPYONWRITEARRAYLIST VS ARRAYLIST
     *
     * Feature                 ArrayList
     * -----------------------------------------------
     * Thread safety           No
     * Read operations         Efficient
     * Write operations        Usually efficient
     * Iterator                Generally fail-fast
     * Write strategy          Modifies backing array
     *
     * CopyOnWriteArrayList:
     *
     * Thread safety           Yes
     * Read operations         Efficient
     * Write operations        Expensive
     * Iterator                Snapshot-based
     * Write strategy          Copies backing array
     *
     * MAIN DIFFERENCE:
     *
     * ArrayList is a general-purpose non-synchronized List.
     *
     * CopyOnWriteArrayList is a concurrent List designed
     * for read-heavy workloads.
     *
     *
     * 22. COPYONWRITEARRAYLIST VS SYNCHRONIZEDLIST
     *
     * Synchronized wrapper:
     *
     *     List<Integer> numbers =
     *         Collections.synchronizedList(
     *             new ArrayList<>()
     *         );
     *
     * CopyOnWriteArrayList:
     *
     *     List<Integer> numbers =
     *         new CopyOnWriteArrayList<>();
     *
     * SynchronizedList:
     *
     * - Synchronizes access through the wrapper.
     * - Readers and writers may contend for the same lock.
     * - Iteration requires external synchronization.
     *
     * CopyOnWriteArrayList:
     *
     * - Ordinary reads use a published array snapshot.
     * - Writers coordinate through a lock.
     * - Iterators use snapshots.
     * - Iteration does not require external synchronization.
     *
     * IMPORTANT:
     *
     * These two approaches have different performance
     * and consistency characteristics.
     *
     *
     * 23. COPYONWRITEARRAYLIST VS VECTOR
     *
     * Feature                 Vector
     * -----------------------------------------------
     * Internal structure      Resizable array
     * Thread safety            Synchronized methods
     * Iterator                 Generally fail-fast
     * Writes                   No copy on every write
     *
     * CopyOnWriteArrayList:
     *
     * Internal structure      Copy-on-write array
     * Thread safety            Concurrent implementation
     * Iterator                 Snapshot-based
     * Writes                   Copies backing array
     *
     * Vector synchronizes individual methods.
     *
     * CopyOnWriteArrayList separates readers from writers
     * through immutable published array snapshots.
     *
     *
     * 24. COPYONWRITEARRAYLIST AND NULL
     *
     * CopyOnWriteArrayList permits null elements.
     *
     * Example:
     *
     *     CopyOnWriteArrayList<String> names =
     *         new CopyOnWriteArrayList<>();
     *
     *     names.add("Vinay");
     *     names.add(null);
     *     names.add("Ravi");
     *
     * Result:
     *
     *     [Vinay, null, Ravi]
     *
     *
     * 25. COPYONWRITEARRAYLIST AND DUPLICATES
     *
     * Duplicate elements are allowed.
     *
     * Example:
     *
     *     CopyOnWriteArrayList<Integer> numbers =
     *         new CopyOnWriteArrayList<>();
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
     * 26. ADDALL() AND BULK OPERATIONS
     *
     * CopyOnWriteArrayList supports bulk operations.
     *
     * Example:
     *
     *     CopyOnWriteArrayList<Integer> numbers =
     *         new CopyOnWriteArrayList<>();
     *
     *     numbers.addAll(List.of(10, 20, 30, 40));
     *
     * Bulk operations can be more efficient than performing
     * many individual additions.
     *
     * Why?
     *
     * Multiple individual additions may each create a new
     * backing array.
     *
     * A bulk operation can construct and publish a resulting
     * array in one operation.
     *
     * IMPORTANT:
     *
     * The exact implementation and optimization depend
     * on the JDK version and operation.
     *
     *
     * 27. ADDIFABSENT()
     *
     * CopyOnWriteArrayList provides:
     *
     *     addIfAbsent(element)
     *
     * It adds an element only if an equal element is not
     * already present.
     *
     * Example:
     *
     *     CopyOnWriteArrayList<String> names =
     *         new CopyOnWriteArrayList<>();
     *
     *     names.add("Vinay");
     *
     *     names.addIfAbsent("Ravi");
     *     names.addIfAbsent("Vinay");
     *
     * Result:
     *
     *     [Vinay, Ravi]
     *
     * The second attempt to add Vinay does not add
     * another occurrence.
     *
     * This method performs its check and addition as
     * a coordinated operation.
     *
     *
     * 28. ADDALLABSENT()
     *
     * CopyOnWriteArrayList also provides:
     *
     *     addAllAbsent(collection)
     *
     * It adds elements from the supplied collection
     * that are not already present.
     *
     * Example:
     *
     *     CopyOnWriteArrayList<Integer> numbers =
     *         new CopyOnWriteArrayList<>(
     *             List.of(10, 20)
     *         );
     *
     *     numbers.addAllAbsent(
     *         List.of(20, 30, 40)
     *     );
     *
     * Result:
     *
     *     [10, 20, 30, 40]
     *
     * Existing elements are not added again.
     *
     *
     * 29. SPLITERATOR
     *
     * CopyOnWriteArrayList provides a snapshot-based
     * Spliterator.
     *
     * It can support traversal and parallel processing.
     *
     * Its Spliterator reports characteristics including:
     *
     * - ORDERED
     * - SIZED
     * - SUBSIZED
     * - IMMUTABLE
     *
     * IMPORTANT:
     *
     * IMMUTABLE here describes the snapshot traversal source.
     *
     * It does not mean the CopyOnWriteArrayList itself
     * cannot be modified.
     *
     * A Spliterator created earlier continues to represent
     * its captured snapshot.
     *
     *
     * 30. COPYONWRITEARRAYLIST AND STREAMS
     *
     * Example:
     *
     *     CopyOnWriteArrayList<Integer> numbers =
     *         new CopyOnWriteArrayList<>(
     *             List.of(10, 20, 30, 40)
     *         );
     *
     *     numbers.stream()
     *            .filter(n -> n > 20)
     *            .forEach(System.out::println);
     *
     * Output:
     *
     *     30
     *     40
     *
     * The stream's traversal is based on the collection's
     * snapshot-oriented Spliterator.
     *
     * Modifications after stream traversal is established
     * are not reflected in that snapshot.
     *
     *
     * 31. IMPORTANT: SHALLOW THREAD SAFETY
     *
     * CopyOnWriteArrayList protects its collection structure.
     *
     * It does not automatically make the objects stored
     * inside it thread-safe.
     *
     * Example:
     *
     *     CopyOnWriteArrayList<Student> students =
     *         new CopyOnWriteArrayList<>();
     *
     * Multiple threads can safely access the List structure.
     *
     * But if two threads modify the same Student object's
     * fields, those modifications need their own
     * thread-safety strategy.
     *
     * IMPORTANT:
     *
     * Thread-safe collection does not mean thread-safe elements.
     *
     *
     * 32. MEMORY VISIBILITY
     *
     * CopyOnWriteArrayList uses safe publication techniques.
     *
     * In common OpenJDK implementations, the backing array
     * reference is volatile.
     *
     * Publishing a new array through that volatile reference
     * establishes visibility according to Java Memory Model
     * volatile rules.
     *
     * Readers that obtain the published array can observe
     * the array contents as initialized before publication.
     *
     * IMPORTANT:
     *
     * This does not automatically make later mutations
     * to referenced element objects thread-safe.
     *
     *
     * 33. COMMON EXCEPTIONS
     *
     * IndexOutOfBoundsException:
     * - Invalid index supplied to positional operations.
     *
     * UnsupportedOperationException:
     * - Iterator or ListIterator modification operations
     *   such as remove(), set() or add().
     *
     * NullPointerException:
     * - May occur when an operation requires a non-null
     *   argument in a context where null is not accepted.
     *
     *
     * 34. COMMON MISTAKES
     *
     * 1. Assuming CopyOnWriteArrayList modifies its array in place.
     *    WRONG: Writes publish a replacement array.
     *
     * 2. Assuming writes are cheap.
     *    WRONG: They generally require copying.
     *
     * 3. Assuming iterators see newly added elements.
     *    WRONG: Iterators use a snapshot.
     *
     * 4. Assuming iterator.remove() is supported.
     *    WRONG: Snapshot iterators do not support modification.
     *
     * 5. Assuming it is suitable for write-heavy workloads.
     *    WRONG: Repeated array copying can be expensive.
     *
     * 6. Assuming every operation is lock-free.
     *    WRONG: Writers coordinate through locking.
     *
     * 7. Assuming stored objects are automatically thread-safe.
     *    WRONG: Only the collection structure is protected.
     *
     * 8. Assuming snapshot iterators are live views.
     *    WRONG: They retain the array snapshot captured
     *    at iterator creation.
     *
     *
     * 35. REAL-WORLD BACKEND USE CASES
     *
     * EVENT LISTENERS:
     *
     *     CopyOnWriteArrayList<Listener> listeners;
     *
     * Many threads may notify listeners.
     *
     * Registration and removal happen occasionally.
     *
     * CONFIGURATION LISTENERS:
     *
     *     CopyOnWriteArrayList<ConfigListener> listeners;
     *
     * READ-MOSTLY METADATA:
     *
     *     CopyOnWriteArrayList<String> supportedFeatures;
     *
     * OBSERVER PATTERN:
     *
     * A subject can maintain a list of observers.
     *
     * Readers can traverse a stable observer snapshot
     * while registration changes occur.
     *
     * These are good candidates when writes are infrequent.
     *
     *
     * 36. INTERVIEW QUESTIONS
     *
     * FUNDAMENTALS:
     *
     * 1. What is CopyOnWriteArrayList?
     * 2. Which package contains it?
     * 3. Which interfaces does it implement?
     * 4. What is copy-on-write?
     * 5. Why was it introduced?
     *
     * INTERNAL WORKING:
     *
     * 6. How does add() work internally?
     * 7. Why does a write create a new array?
     * 8. How does set() work?
     * 9. How does remove() work?
     * 10. Why are reads efficient?
     *
     * ITERATORS:
     *
     * 11. What is a snapshot iterator?
     * 12. Does it throw ConcurrentModificationException?
     * 13. Does it see modifications made after its creation?
     * 14. Can iterator.remove() be used?
     *
     * CONCURRENCY:
     *
     * 15. Is CopyOnWriteArrayList thread-safe?
     * 16. How are concurrent writes coordinated?
     * 17. What is the role of volatile?
     * 18. Does it make stored objects thread-safe?
     *
     * PERFORMANCE:
     *
     * 19. Why are writes expensive?
     * 20. What is the memory overhead?
     * 21. When should CopyOnWriteArrayList be used?
     * 22. When should it be avoided?
     *
     * COMPARISONS:
     *
     * 23. ArrayList vs CopyOnWriteArrayList?
     * 24. Vector vs CopyOnWriteArrayList?
     * 25. synchronizedList() vs CopyOnWriteArrayList?
     *
     *
     * 37. FINAL REVISION CHECKLIST
     *
     * [ ] Understand CopyOnWriteArrayList.
     * [ ] Know its package and hierarchy.
     * [ ] Understand copy-on-write.
     * [ ] Understand its internal array.
     * [ ] Understand how add(), set() and remove() work.
     * [ ] Understand why writes are expensive.
     * [ ] Understand efficient concurrent reads.
     * [ ] Understand snapshot iterators.
     * [ ] Compare snapshot and fail-fast iterators.
     * [ ] Understand writer synchronization.
     * [ ] Understand volatile publication.
     * [ ] Understand memory overhead.
     * [ ] Understand addIfAbsent().
     * [ ] Understand addAllAbsent().
     * [ ] Understand Spliterator characteristics.
     * [ ] Understand thread safety of elements versus collection.
     * [ ] Compare it with ArrayList.
     * [ ] Compare it with Vector.
     * [ ] Compare it with synchronizedList().
     * [ ] Know when to use and avoid it.
     *
     *
     * 38. WHAT YOU SHOULD REMEMBER
     *
     * CopyOnWriteArrayList =
     *     Thread-safe List + Copy-on-write mechanism.
     *
     * Package:
     *     java.util.concurrent
     *
     * Implements:
     *     List + RandomAccess.
     *
     * Reads:
     *     Access the published backing array.
     *
     * Writes:
     *     Create and publish a replacement array.
     *
     * Iterators:
     *     Snapshot-based.
     *
     * Iterator modification:
     *     Unsupported.
     *
     * Thread safety:
     *     Collection structure is thread-safe.
     *
     * Best workload:
     *     Many reads + Few writes.
     *
     * Main disadvantage:
     *     Expensive writes and additional memory allocation.
     *
     * ============================================================
     *                END OF COPYONWRITEARRAYLIST
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about CopyOnWriteArrayList , Happy Learning ..!");
    }
}
