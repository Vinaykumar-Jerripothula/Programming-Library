
public class SynchronizedCollections {

    /*
     * ============================================================
     * SYNCHRONIZED COLLECTIONS IN JAVA
     * ============================================================
     *
     * 1. WHY DO WE NEED SYNCHRONIZED COLLECTIONS?
     *
     * Consider:
     *
     * List<Integer> list = new ArrayList<>();
     *
     * Thread A -> list.add(10);
     * Thread B -> list.add(20);
     *
     * ArrayList is not designed for concurrent structural
     * modifications without external coordination.
     *
     * Multiple threads modifying it simultaneously can cause
     * incorrect behavior.
     *
     * Java provides synchronized collection wrappers to
     * coordinate access using synchronization.
     *
     *
     * ============================================================
     * 2. WHAT ARE SYNCHRONIZED COLLECTIONS?
     * ============================================================
     *
     * Synchronized collections are ordinary collections wrapped
     * with synchronization.
     *
     * Java provides factory methods through:
     *
     * java.util.Collections
     *
     * Important methods:
     *
     * Collections.synchronizedList()
     * Collections.synchronizedSet()
     * Collections.synchronizedMap()
     * Collections.synchronizedCollection()
     * Collections.synchronizedSortedSet()
     * Collections.synchronizedSortedMap()
     * Collections.synchronizedNavigableSet()
     * Collections.synchronizedNavigableMap()
     *
     * These methods return synchronized views of collections.
     *
     * IMPORTANT:
     *
     * They do not create entirely new collection implementations
     * from scratch. They wrap the supplied collection.
     *
     *
     * ============================================================
     * 3. SYNCHRONIZED LIST
     * ============================================================
     *
     * Syntax:
     *
     * List<Integer> list =
     *     Collections.synchronizedList(new ArrayList<>());
     *
     * Now individual operations are synchronized.
     *
     * Example:
     *
     * list.add(10);
     * list.add(20);
     * list.remove(Integer.valueOf(10));
     *
     * The wrapper coordinates access to these operations.
     *
     * You can also wrap a LinkedList:
     *
     * List<Integer> list =
     *     Collections.synchronizedList(new LinkedList<>());
     *
     * The underlying implementation remains a LinkedList.
     *
     *
     * ============================================================
     * 4. SYNCHRONIZED SET
     * ============================================================
     *
     * Syntax:
     *
     * Set<String> set =
     *     Collections.synchronizedSet(new HashSet<>());
     *
     * Individual operations such as:
     *
     * set.add("Java");
     * set.contains("Java");
     * set.remove("Java");
     *
     * are synchronized through the wrapper.
     *
     * The underlying Set still determines:
     *
     * - Whether null is permitted.
     * - How elements are stored.
     * - Whether iteration follows a particular order.
     *
     *
     * ============================================================
     * 5. SYNCHRONIZED MAP
     * ============================================================
     *
     * Syntax:
     *
     * Map<String, Integer> map =
     *     Collections.synchronizedMap(new HashMap<>());
     *
     * Individual operations are synchronized.
     *
     * Example:
     *
     * map.put("Java", 10);
     * map.get("Java");
     * map.remove("Java");
     *
     * The underlying HashMap still determines its usual
     * key and value behavior.
     *
     *
     * ============================================================
     * 6. HOW DOES SYNCHRONIZED WRAPPING WORK?
     * ============================================================
     *
     * Conceptually:
     *
     * Synchronized wrapper
     *         |
     *         v
     * Acquires a monitor lock
     *         |
     *         v
     * Executes the collection operation
     *         |
     *         v
     * Releases the lock
     *
     * Example:
     *
     * List<Integer> list =
     *     Collections.synchronizedList(new ArrayList<>());
     *
     * list.add(10);
     *
     * The wrapper synchronizes the operation using its
     * associated mutex.
     *
     * In the standard wrappers, the wrapper itself is normally
     * the mutex unless a different mutex is used internally
     * for a backed view.
     *
     *
     * ============================================================
     * 7. IMPORTANT: ITERATION REQUIRES EXTERNAL SYNCHRONIZATION
     * ============================================================
     *
     * This is one of the most important rules.
     *
     * Individual methods are synchronized, but iteration
     * involves multiple operations.
     *
     * Example:
     *
     * List<Integer> list =
     *     Collections.synchronizedList(new ArrayList<>());
     *
     * list.add(10);
     * list.add(20);
     * list.add(30);
     *
     * Correct iteration:
     *
     * synchronized (list) {
     *
     *     for (Integer value : list) {
     *         System.out.println(value);
     *     }
     * }
     *
     * Why?
     *
     * The iterator itself does not automatically hold the
     * collection's lock throughout the entire traversal.
     *
     * Another thread could modify the list between iterator
     * operations if iteration is not externally synchronized.
     *
     * Synchronizing on the wrapper prevents other threads
     * following the same wrapper-lock protocol from modifying
     * the collection during traversal.
     *
     * IMPORTANT:
     *
     * Synchronize on the returned wrapper, not directly on
     * the original backing collection.
     *
     *
     * ============================================================
     * 8. COMPOUND OPERATIONS
     * ============================================================
     *
     * A compound operation combines multiple operations
     * into one logical action.
     *
     * Example:
     *
     * if (!list.contains(50)) {
     *     list.add(50);
     * }
     *
     * Even though contains() and add() are individually
     * synchronized, another thread may modify the list
     * between these calls.
     *
     * Therefore, the complete operation is not automatically
     * atomic.
     *
     * Correct coordination:
     *
     * synchronized (list) {
     *
     *     if (!list.contains(50)) {
     *         list.add(50);
     *     }
     * }
     *
     * Both operations now execute under the same wrapper lock.
     *
     * IMPORTANT:
     *
     * Every thread accessing the same shared collection must
     * follow the same synchronization protocol for this
     * compound operation to remain protected.
     *
     *
     * ============================================================
     * 9. SYNCHRONIZED COLLECTION VS CONCURRENT COLLECTION
     * ============================================================
     *
     * Synchronized collection:
     *
     * - Uses synchronization around individual operations.
     * - Operations may contend for the same lock.
     * - Iteration often requires external synchronization.
     *
     * Concurrent collection:
     *
     * - Designed specifically for concurrent access.
     * - May use different concurrency techniques.
     * - May support weakly consistent or snapshot iterators.
     * - Often offers better scalability for suitable workloads.
     *
     * Examples:
     *
     * Synchronized:
     * Collections.synchronizedMap(new HashMap<>());
     *
     * Concurrent:
     * ConcurrentHashMap.
     *
     * Copy-on-write:
     * CopyOnWriteArrayList.
     *
     * IMPORTANT:
     *
     * A concurrent collection is not automatically faster
     * in every workload. Choose based on access patterns
     * and required behavior.
     *
     *
     * ============================================================
     * 10. VECTOR AND HASHTABLE VS SYNCHRONIZED WRAPPERS
     * ============================================================
     *
     * Vector:
     *
     * - Legacy synchronized List implementation.
     * - Individual methods are synchronized.
     *
     * Hashtable:
     *
     * - Legacy synchronized Map implementation.
     * - Individual methods are synchronized.
     *
     * Synchronized wrappers:
     *
     * - Allow an ordinary collection to be wrapped.
     * - Provide synchronized access through the wrapper.
     *
     * Example:
     *
     * List<Integer> list =
     *     Collections.synchronizedList(new ArrayList<>());
     *
     * Map<String, Integer> map =
     *     Collections.synchronizedMap(new HashMap<>());
     *
     * Modern code commonly uses collection wrappers
     * when simple synchronized access is sufficient.
     *
     *
     * ============================================================
     * 11. SYNCHRONIZED SORTED COLLECTIONS
     * ============================================================
     *
     * Java also provides synchronized wrappers for
     * sorted collections.
     *
     * Synchronized SortedSet:
     *
     * SortedSet<Integer> set =
     *     Collections.synchronizedSortedSet(new TreeSet<>());
     *
     * Synchronized NavigableSet:
     *
     * NavigableSet<Integer> set =
     *     Collections.synchronizedNavigableSet(new TreeSet<>());
     *
     * Synchronized SortedMap:
     *
     * SortedMap<Integer, String> map =
     *     Collections.synchronizedSortedMap(new TreeMap<>());
     *
     * Synchronized NavigableMap:
     *
     * NavigableMap<Integer, String> map =
     *     Collections.synchronizedNavigableMap(new TreeMap<>());
     *
     * These wrappers preserve the underlying collection's
     * sorted or navigable behavior.
     *
     * IMPORTANT:
     *
     * Iterating over these collections and their views
     * still requires the documented synchronization protocol.
     *
     *
     * ============================================================
     * 12. SYNCHRONIZED WRAPPERS AND BACKED VIEWS
     * ============================================================
     *
     * Some collection operations return views.
     *
     * Examples:
     *
     * Map.keySet()
     * Map.values()
     * Map.entrySet()
     *
     * These views are connected to the original Map.
     *
     * When using synchronized wrappers, access the views
     * through the synchronized Map.
     *
     * Example:
     *
     * Map<String, Integer> map =
     *     Collections.synchronizedMap(new HashMap<>());
     *
     * Set<String> keys = map.keySet();
     *
     * Synchronize on the map while iterating:
     *
     * synchronized (map) {
     *
     *     for (String key : keys) {
     *         System.out.println(key);
     *     }
     * }
     *
     * IMPORTANT:
     *
     * The Map and its backed views share the same
     * synchronization protocol.
     *
     *
     * ============================================================
     * 13. NULL HANDLING
     * ============================================================
     *
     * Synchronized wrappers generally preserve the
     * null-handling behavior of the underlying collection.
     *
     * Examples:
     *
     * synchronizedList(new ArrayList<>())
     *     -> Allows null elements.
     *
     * synchronizedSet(new HashSet<>())
     *     -> Allows one null element.
     *
     * synchronizedMap(new HashMap<>())
     *     -> Allows one null key and multiple null values.
     *
     * synchronizedSortedSet(new TreeSet<>())
     *     -> Follows TreeSet's ordering and null restrictions.
     *
     * The wrapper does not automatically change the
     * underlying collection's null policy.
     *
     *
     * ============================================================
     * 14. IMPORTANT LIMITATIONS
     * ============================================================
     *
     * 1. Synchronization can reduce concurrency when many
     *    threads contend for the same lock.
     *
     * 2. Iteration needs external synchronization.
     *
     * 3. Multiple method calls are not automatically one
     *    atomic transaction.
     *
     * 4. Code that bypasses the wrapper and accesses the
     *    original collection can break the coordination.
     *
     * 5. Synchronization does not make the stored objects
     *    themselves thread-safe.
     *
     * Example:
     *
     * List<StringBuilder> list =
     *     Collections.synchronizedList(new ArrayList<>());
     *
     * The list operations are synchronized through the wrapper.
     *
     * But two threads modifying the same StringBuilder object
     * still need suitable coordination.
     *
     *
     * ============================================================
     * 15. WHEN SHOULD WE USE SYNCHRONIZED WRAPPERS?
     * ============================================================
     *
     * Use them when:
     *
     * - You need a simple synchronized collection.
     * - The workload is relatively straightforward.
     * - A single shared lock is acceptable.
     * - Existing code expects a standard collection interface.
     *
     * Consider concurrent collections when:
     *
     * - Many threads access the collection frequently.
     * - You need scalable concurrent operations.
     * - You need specialized concurrent behavior.
     *
     *
     * ============================================================
     * 16. IMPORTANT COMPARISON
     * ============================================================
     *
     * Collections.synchronizedList():
     *
     * - Wraps an ordinary List.
     * - Synchronizes individual operations.
     * - Requires external synchronization for iteration.
     *
     * CopyOnWriteArrayList:
     *
     * - Creates a new backing array on mutation.
     * - Provides snapshot iterators.
     * - Useful for read-heavy workloads.
     *
     * ConcurrentLinkedQueue:
     *
     * - Non-blocking concurrent queue.
     * - Uses concurrent algorithms.
     *
     * ConcurrentHashMap:
     *
     * - Concurrent hash-based Map.
     * - Supports concurrent access without one global
     *   lock for all operations.
     *
     *
     * ============================================================
     * 17. COMMON INTERVIEW QUESTIONS
     * ============================================================
     *
     * 1. What are synchronized collections?
     *
     * 2. What does Collections.synchronizedList() do?
     *
     * 3. How do you create a synchronized Set?
     *
     * 4. How do you create a synchronized Map?
     *
     * 5. Why is external synchronization required during
     *    iteration?
     *
     * 6. Why are compound operations not automatically atomic?
     *
     * 7. Difference between synchronizedMap and ConcurrentHashMap?
     *
     * 8. Difference between Vector and synchronizedList?
     *
     * 9. Do synchronized wrappers make stored objects
     *    thread-safe?
     *
     * 10. What happens if a thread modifies the original
     *     collection instead of using the wrapper?
     *
     *
     * ============================================================
     * 18. QUICK REVISION
     * ============================================================
     *
     * Collections.synchronizedList():
     *     -> Synchronized List wrapper.
     *
     * Collections.synchronizedSet():
     *     -> Synchronized Set wrapper.
     *
     * Collections.synchronizedMap():
     *     -> Synchronized Map wrapper.
     *
     * Synchronized wrapper:
     *     -> Coordinates operations using a shared mutex.
     *
     * Iteration:
     *     -> Requires external synchronization.
     *
     * Compound operation:
     *     -> Must be protected as one logical unit.
     *
     * Backed view:
     *     -> Shares the underlying collection's data.
     *
     * Thread safety:
     *     -> Does not automatically make stored objects safe.
     *
     * ============================================================
     * END OF SYNCHRONIZED COLLECTIONS
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Synchronized Collections , Happy Learning ..!");
    }
}
