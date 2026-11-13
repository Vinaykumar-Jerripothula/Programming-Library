
public class CopyOnWriteArraySet {

    /*
     * ============================================================
     *                 JAVA COPYONWRITEARRAYSET
     * ============================================================
     *
     * 1. WHAT IS COPYONWRITEARRAYSET?
     *
     * Imagine an application where multiple threads read a
     * collection of registered listeners.
     *
     * Reading happens very frequently, but adding or removing
     * listeners happens rarely.
     *
     * We need:
     *
     * - Thread-safe operations.
     * - Safe iteration while other threads modify the Set.
     * - No duplicate elements.
     *
     * CopyOnWriteArraySet is designed for this situation.
     *
     * CopyOnWriteArraySet is a thread-safe implementation of
     * the Set interface.
     *
     * It uses a CopyOnWriteArrayList internally.
     *
     * When a modification is performed, a new copy of the
     * underlying array is created.
     *
     * IMPORTANT:
     *
     * - Does not allow duplicate elements.
     * - Does not guarantee insertion-order iteration.
     * - Allows one null element.
     * - Is thread-safe.
     * - Iterators work on snapshots.
     * - Best suited for read-heavy, modification-light workloads.
     *
     *
     * ============================================================
     * 2. PACKAGE AND IMPORT
     * ============================================================
     *
     * Package:
     *
     *     java.util.concurrent
     *
     * Imports:
     *
     *     import java.util.Set;
     *     import java.util.concurrent.CopyOnWriteArraySet;
     *
     * Example:
     *
     *     Set<String> names = new CopyOnWriteArraySet<>();
     *
     *
     * ============================================================
     * 3. DECLARATION AND HIERARCHY
     * ============================================================
     *
     * Declaration:
     *
     *     public class CopyOnWriteArraySet<E>
     *         extends AbstractSet<E>
     *         implements Serializable
     *
     * It is a concrete implementation of Set.
     *
     * It uses CopyOnWriteArrayList internally to provide
     * thread-safe storage and modification behavior.
     *
     * Conceptually:
     *
     *     CopyOnWriteArraySet
     *              |
     *              v
     *     CopyOnWriteArrayList
     *              |
     *              v
     *         Array storage
     *
     *
     * ============================================================
     * 4. CREATING COPYONWRITEARRAYSET
     * ============================================================
     *
     * Empty Set:
     *
     *     CopyOnWriteArraySet<String> names =
     *         new CopyOnWriteArraySet<>();
     *
     * From another collection:
     *
     *     List<String> values =
     *         Arrays.asList("Java", "Python", "Java");
     *
     *     CopyOnWriteArraySet<String> languages =
     *         new CopyOnWriteArraySet<>(values);
     *
     * Result:
     *
     *     [Java, Python]
     *
     * Duplicate elements are eliminated.
     *
     * It does not provide constructors for initial capacity
     * or load factor because it does not use a hash table.
     *
     *
     * ============================================================
     * 5. BASIC OPERATIONS
     * ============================================================
     *
     *     CopyOnWriteArraySet<String> languages =
     *         new CopyOnWriteArraySet<>();
     *
     * ADD:
     *
     *     languages.add("Java");
     *     languages.add("Python");
     *
     * ADD DUPLICATE:
     *
     *     languages.add("Java"); // false
     *
     * SEARCH:
     *
     *     languages.contains("Java");
     *
     * REMOVE:
     *
     *     languages.remove("Python");
     *
     * SIZE:
     *
     *     languages.size();
     *
     * CLEAR:
     *
     *     languages.clear();
     *
     * These methods are thread-safe.
     *
     *
     * ============================================================
     * 6. WHAT IS COPY-ON-WRITE?
     * ============================================================
     *
     * Copy-on-write is a technique in which the existing data
     * is shared for reading, but a new copy is created when
     * modification is required.
     *
     * Imagine an array:
     *
     *     [A, B, C]
     *
     * A thread wants to add D.
     *
     * Instead of modifying the existing array directly,
     * a new array is created:
     *
     *     Old array: [A, B, C]
     *
     *     New array: [A, B, C, D]
     *
     * After the new array is ready, the collection publishes
     * the new array as its current data.
     *
     * Readers that already obtained the old array can continue
     * reading it safely.
     *
     * This reduces interference between readers and writers.
     *
     *
     * ============================================================
     * 7. HOW DOES COPYONWRITEARRAYSET WORK INTERNALLY?
     * ============================================================
     *
     * CopyOnWriteArraySet internally delegates storage and
     * operations to CopyOnWriteArrayList.
     *
     * The underlying list is protected using synchronization
     * for modifications.
     *
     * When add() is called:
     *
     * 1. The modification operation is synchronized.
     *
     * 2. The existing elements are checked for an equal element.
     *
     * 3. If an equal element exists, nothing is added.
     *
     * 4. Otherwise, a new array is created with the new element.
     *
     * 5. The new array becomes the current array.
     *
     * When remove() is called:
     *
     * 1. The modification operation is synchronized.
     *
     * 2. The element is located.
     *
     * 3. A new array is created without that element.
     *
     * 4. The new array becomes the current array.
     *
     * This is the basic copy-on-write mechanism.
     *
     * IMPORTANT:
     *
     * These are implementation details of the standard
     * JDK implementation.
     *
     *
     * ============================================================
     * 8. WHY IS IT CALLED COPYONWRITEARRAYSET?
     * ============================================================
     *
     * The name describes its internal behavior:
     *
     * Copy:
     *     A new array is created for modifications.
     *
     * On:
     *     When a write operation occurs.
     *
     * Write:
     *     An operation that modifies the collection.
     *
     * Array:
     *     Its underlying storage is array-based.
     *
     * Set:
     *     It does not allow duplicate elements.
     *
     *
     * ============================================================
     * 9. THREAD SAFETY
     * ============================================================
     *
     * CopyOnWriteArraySet is thread-safe.
     *
     * Multiple threads can safely access the collection
     * without requiring external synchronization for
     * individual collection operations.
     *
     * Example:
     *
     *     CopyOnWriteArraySet<String> names =
     *         new CopyOnWriteArraySet<>();
     *
     *     Thread t1 = new Thread(() -> {
     *         names.add("Java");
     *     });
     *
     *     Thread t2 = new Thread(() -> {
     *         names.add("Python");
     *     });
     *
     *     t1.start();
     *     t2.start();
     *
     * Both threads can safely perform these additions.
     *
     * IMPORTANT:
     *
     * Thread safety of collection operations does not
     * automatically make a sequence of multiple operations
     * atomic.
     *
     *
     * ============================================================
     * 10. WHAT IS A SNAPSHOT ITERATOR?
     * ============================================================
     *
     * This is one of the most important concepts.
     *
     * A CopyOnWriteArraySet iterator works on a snapshot
     * of the collection at the time the iterator is created.
     *
     * Example:
     *
     *     CopyOnWriteArraySet<Integer> numbers =
     *         new CopyOnWriteArraySet<>();
     *
     *     numbers.add(10);
     *     numbers.add(20);
     *
     *     Iterator<Integer> iterator = numbers.iterator();
     *
     *     numbers.add(30);
     *
     *     while (iterator.hasNext()) {
     *         System.out.println(iterator.next());
     *     }
     *
     * Output:
     *
     *     10
     *     20
     *
     * Why is 30 not printed?
     *
     * Because the iterator was created before 30 was added.
     *
     * It continues reading the snapshot containing:
     *
     *     [10, 20]
     *
     * The current collection contains:
     *
     *     [10, 20, 30]
     *
     * But the iterator does not automatically switch to
     * the newer array.
     *
     *
     * ============================================================
     * 11. SNAPSHOT ITERATOR VS FAIL-FAST ITERATOR
     * ============================================================
     *
     * HashSet and LinkedHashSet iterators are generally
     * fail-fast on a best-effort basis.
     *
     * Structural modification outside the iterator can cause
     * ConcurrentModificationException.
     *
     * CopyOnWriteArraySet uses snapshot iterators.
     *
     * Differences:
     *
     * FAIL-FAST ITERATOR:
     *
     * - Detects certain structural modifications.
     * - May throw ConcurrentModificationException.
     * - Does not provide a stable snapshot.
     *
     * SNAPSHOT ITERATOR:
     *
     * - Iterates over an array captured at iterator creation.
     * - Does not reflect later modifications.
     * - Does not throw ConcurrentModificationException
     *   because of those later modifications.
     *
     * IMPORTANT:
     *
     * A snapshot iterator is not a live view of the current Set.
     *
     *
     * ============================================================
     * 12. ITERATOR REMOVE() IS NOT SUPPORTED
     * ============================================================
     *
     * CopyOnWriteArraySet snapshot iterators do not support
     * Iterator.remove().
     *
     * Example:
     *
     *     Iterator<Integer> iterator = numbers.iterator();
     *
     *     while (iterator.hasNext()) {
     *         Integer value = iterator.next();
     *
     *         if (value == 10) {
     *             iterator.remove();
     *         }
     *     }
     *
     * This throws:
     *
     *     UnsupportedOperationException
     *
     * To remove an element, use the Set directly:
     *
     *     numbers.remove(10);
     *
     * Remember that the existing iterator continues to use
     * its original snapshot.
     *
     *
     * ============================================================
     * 13. DUPLICATE DETECTION
     * ============================================================
     *
     * CopyOnWriteArraySet does not allow duplicate elements.
     *
     * It checks existing elements using equality.
     *
     * Example:
     *
     *     CopyOnWriteArraySet<String> names =
     *         new CopyOnWriteArraySet<>();
     *
     *     names.add("Java");
     *     names.add("Python");
     *     names.add("Java");
     *
     * Result:
     *
     *     [Java, Python]
     *
     * For custom objects, implement equals() correctly
     * when logical equality is required.
     *
     * Unlike HashSet, its duplicate checking is based on
     * array/list-style equality searching, not hash buckets.
     *
     *
     * ============================================================
     * 14. NULL ELEMENT
     * ============================================================
     *
     * CopyOnWriteArraySet allows one null element.
     *
     * Example:
     *
     *     CopyOnWriteArraySet<String> names =
     *         new CopyOnWriteArraySet<>();
     *
     *     names.add(null);
     *     names.add("Java");
     *     names.add(null);
     *
     * Only one null is stored.
     *
     *
     * ============================================================
     * 15. PERFORMANCE AND TIME COMPLEXITY
     * ============================================================
     *
     * CopyOnWriteArraySet uses array-based storage.
     *
     * Operation             Typical complexity
     *
     * contains()            O(n)
     * add()                 O(n)
     * remove()              O(n)
     * iteration             O(n)
     *
     * Why are modifications expensive?
     *
     * Because a modification generally requires:
     *
     * - Searching for the element.
     * - Creating a new array.
     * - Copying existing elements.
     * - Publishing the new array.
     *
     * Therefore, frequent writes can be expensive.
     *
     * Reading is efficient because readers can access
     * the current array without locking the collection.
     *
     * IMPORTANT:
     *
     * These are typical complexity characteristics, not
     * guarantees for every operation or implementation.
     *
     *
     * ============================================================
     * 16. WHY IS IT SUITABLE FOR READ-HEAVY WORKLOADS?
     * ============================================================
     *
     * Consider:
     *
     * - 10,000 read operations.
     * - 2 write operations.
     *
     * CopyOnWriteArraySet can be a good choice because
     * modifications are rare.
     *
     * The cost of copying the array is paid only occasionally.
     *
     * But consider:
     *
     * - 10,000 read operations.
     * - 10,000 write operations.
     *
     * Frequent copying can create significant overhead.
     *
     * Therefore:
     *
     * Read-heavy + write-light -> Good candidate.
     *
     * Write-heavy              -> Usually poor candidate.
     *
     *
     * ============================================================
     * 17. MEMORY CONSIDERATIONS
     * ============================================================
     *
     * Every modification may allocate a new array.
     *
     * During a write:
     *
     * - The old array may still be referenced by existing
     *   iterators.
     * - The new array becomes the current collection storage.
     *
     * The old array becomes eligible for garbage collection
     * when no references to it remain.
     *
     * Frequent modifications can therefore create:
     *
     * - Additional memory allocations.
     * - Increased garbage collection activity.
     * - More memory copying.
     *
     * This is another reason to avoid it for write-heavy
     * collections.
     *
     *
     * ============================================================
     * 18. COPYONWRITEARRAYSET VS HASHSET
     * ============================================================
     *
     * HashSet:
     *
     * - Hash-based storage.
     * - Expected O(1) basic operations.
     * - Not thread-safe by default.
     * - Fail-fast iterators on a best-effort basis.
     * - Suitable for general-purpose Set operations.
     *
     * CopyOnWriteArraySet:
     *
     * - Array-based storage.
     * - O(n) searching and modifications typically.
     * - Thread-safe.
     * - Snapshot iterators.
     * - Suitable for read-heavy concurrent workloads.
     *
     * Choose HashSet for general-purpose non-concurrent
     * use when ordering is unnecessary.
     *
     * Choose CopyOnWriteArraySet when safe concurrent reads
     * and infrequent modifications are important.
     *
     *
     * ============================================================
     * 19. COPYONWRITEARRAYSET VS CONCURRENTSKIPLISTSET
     * ============================================================
     *
     * CopyOnWriteArraySet:
     *
     * - Array-based.
     * - Snapshot iteration.
     * - O(n) searching and modification.
     * - No sorted-order guarantee.
     * - Good for small, read-heavy Sets.
     *
     * ConcurrentSkipListSet:
     *
     * - Skip-list-based.
     * - Maintains sorted order.
     * - Supports concurrent operations.
     * - Expected O(log n) basic operations.
     * - Iterators are weakly consistent.
     *
     * Choose according to:
     *
     * - Expected collection size.
     * - Read/write frequency.
     * - Whether sorted ordering is required.
     * - Iteration consistency requirements.
     *
     *
     * ============================================================
     * 20. COMMON USE CASES
     * ============================================================
     *
     * 1. Event listener collections.
     *
     * 2. Application configuration that changes rarely.
     *
     * 3. Registered callbacks.
     *
     * 4. Read-heavy feature registries.
     *
     * 5. Small collections shared among multiple threads.
     *
     * 6. Collections where iteration must not be interrupted
     *    by concurrent modifications.
     *
     *
     * ============================================================
     * 21. COMMON MISTAKES
     * ============================================================
     *
     * 1. Using it for frequent writes.
     *    Every modification can require copying the array.
     *
     * 2. Assuming its iterator reflects live changes.
     *    It uses a snapshot.
     *
     * 3. Calling Iterator.remove().
     *    It is unsupported.
     *
     * 4. Assuming it maintains sorted order.
     *    It does not.
     *
     * 5. Assuming thread safety makes multiple operations
     *    automatically atomic.
     *
     * 6. Assuming it is faster than HashSet.
     *    Its search and modification operations are typically
     *    O(n), unlike expected O(1) HashSet operations.
     *
     *
     * ============================================================
     * 22. INTERVIEW QUICK REVISION
     * ============================================================
     *
     * Q1. What is CopyOnWriteArraySet?
     * A thread-safe Set implementation backed by
     * CopyOnWriteArrayList.
     *
     * Q2. What is copy-on-write?
     * Creating a new copy of the underlying array when
     * a modification is performed.
     *
     * Q3. Does it allow duplicates?
     * No.
     *
     * Q4. Does it allow null?
     * Yes, one null element.
     *
     * Q5. Is it thread-safe?
     * Yes.
     *
     * Q6. What type of iterator does it provide?
     * Snapshot iterator.
     *
     * Q7. Does its iterator support remove()?
     * No. It throws UnsupportedOperationException.
     *
     * Q8. Does its iterator reflect later modifications?
     * No.
     *
     * Q9. What is the typical complexity of contains()?
     * O(n).
     *
     * Q10. Why is it good for read-heavy workloads?
     * Readers can access an existing array without locking,
     * while writes create a new array.
     *
     * Q11. Why is it inefficient for write-heavy workloads?
     * Frequent modifications cause repeated array copying.
     *
     * Q12. Which class backs CopyOnWriteArraySet?
     * CopyOnWriteArrayList.
     *
     * Q13. Does it maintain sorted order?
     * No.
     *
     * Q14. What happens to an old array after modification?
     * It remains available while referenced, such as by an
     * existing iterator, and becomes eligible for garbage
     * collection when no references remain.
     *
     *
     * ============================================================
     * 23. FINAL UNDERSTANDING
     * ============================================================
     *
     * Remember the main idea:
     *
     * Ordinary Set:
     *     Modify the collection's data structure.
     *
     * CopyOnWriteArraySet:
     *     Create a new array when modification is required.
     *
     * This approach makes concurrent reading simple and safe,
     * but makes modifications relatively expensive.
     *
     * Use it when:
     *
     * - The collection is relatively small.
     * - Many threads read it.
     * - Modifications are rare.
     * - Snapshot iteration is acceptable.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about CopyOnWriteArraySet , Happy Learning ..!");
    }
}
