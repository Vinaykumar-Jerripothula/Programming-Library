
public class ConcurrentHashMap {

    /*
     * ============================================================
     *                  CONCURRENTHASHMAP IN JAVA
     * ============================================================
     *
     * 1. WHAT IS CONCURRENTHASHMAP?
     *
     * ConcurrentHashMap is a thread-safe implementation of
     * the Map interface available in java.util.concurrent.
     *
     * It is designed to support concurrent access by multiple
     * threads.
     *
     * Example:
     *
     * ConcurrentHashMap<Integer, String> map =
     *     new ConcurrentHashMap<>();
     *
     * map.put(101, "Vinay");
     * map.put(102, "Rahul");
     *
     * Multiple threads can safely access and update this Map
     * using its supported concurrent operations.
     *
     *
     * 2. WHY DO WE NEED CONCURRENTHASHMAP?
     *
     * Imagine a web application handling thousands of requests.
     *
     * Multiple threads may access a shared Map simultaneously.
     *
     * Example:
     *
     * Thread 1 -> Adds user information.
     * Thread 2 -> Reads user information.
     * Thread 3 -> Updates user information.
     *
     * With an ordinary HashMap, concurrent modifications
     * are not safe.
     *
     * Hashtable provides synchronized individual methods,
     * but its synchronization can cause contention because
     * threads coordinate through the same object monitor.
     *
     * ConcurrentHashMap was designed to provide thread-safe
     * operations with better concurrency.
     *
     *
     * 3. PACKAGE AND HIERARCHY
     *
     * Package:
     *
     * java.util.concurrent
     *
     * Hierarchy:
     *
     * Map
     *  |
     * ConcurrentMap
     *  |
     * ConcurrentHashMap
     *
     * ConcurrentHashMap implements:
     *
     * - ConcurrentMap<K, V>
     * - Serializable
     *
     * ConcurrentMap extends Map.
     *
     *
     * 4. CREATING A CONCURRENTHASHMAP
     *
     * Import:
     *
     * import java.util.concurrent.ConcurrentHashMap;
     *
     * Default constructor:
     *
     * ConcurrentHashMap<Integer, String> map =
     *     new ConcurrentHashMap<>();
     *
     * Constructor with initial capacity:
     *
     * ConcurrentHashMap<Integer, String> map =
     *     new ConcurrentHashMap<>(32);
     *
     * Constructor with initial capacity and load factor:
     *
     * ConcurrentHashMap<Integer, String> map =
     *     new ConcurrentHashMap<>(32, 0.75f);
     *
     * Constructor with concurrency level:
     *
     * ConcurrentHashMap<Integer, String> map =
     *     new ConcurrentHashMap<>(32, 0.75f, 8);
     *
     * IMPORTANT:
     *
     * The concurrency-level constructor parameter is a sizing
     * hint, not a promise that the Map will create exactly
     * that many locks or segments.
     *
     *
     * 5. INTERNAL DATA STRUCTURE
     *
     * Modern ConcurrentHashMap uses:
     *
     * - An array of buckets.
     * - Linked nodes for collision handling.
     * - Red-Black Trees for certain heavily populated buckets.
     * - CAS operations.
     * - Synchronization on individual bins when required.
     *
     * It is based on a hash-table design.
     *
     * A bucket can contain:
     *
     * - A single node.
     * - A linked list of nodes.
     * - A tree bin for sufficiently large collision groups.
     *
     * IMPORTANT:
     *
     * Modern ConcurrentHashMap does not use the old
     * fixed Segment-based architecture.
     *
     * The old segmented design belonged to earlier
     * Java implementations.
     *
     *
     * 6. HOW DOES CONCURRENTHASHMAP WORK?
     *
     * Consider multiple threads updating different keys.
     *
     * ConcurrentHashMap allows a high degree of concurrency.
     *
     * It does not require every operation to lock the entire Map.
     *
     * Modern implementations use:
     *
     * 1. CAS (Compare-And-Set) for certain updates.
     *
     * 2. Synchronization on a bucket when needed.
     *
     * 3. Volatile reads and writes for visibility.
     *
     * 4. Coordination mechanisms for resizing.
     *
     * This helps independent operations proceed concurrently.
     *
     *
     * 7. WHAT IS CAS?
     *
     * CAS stands for Compare-And-Set.
     *
     * It is an atomic operation.
     *
     * Conceptually:
     *
     * Update a variable only if its current value matches
     * the expected value.
     *
     * Example:
     *
     * Current value = 10
     * Expected value = 10
     * New value = 20
     *
     * CAS checks:
     *
     * Is current value still 10?
     *
     * If yes:
     * Update it to 20.
     *
     * If no:
     * Do not perform the update.
     *
     * The comparison and update happen atomically.
     *
     * ConcurrentHashMap uses CAS for certain internal
     * operations, particularly when initializing or inserting
     * into an empty bucket.
     *
     *
     * 8. BUCKET-LEVEL SYNCHRONIZATION
     *
     * When multiple threads update the same bucket,
     * coordination is required.
     *
     * Modern ConcurrentHashMap can synchronize on the
     * relevant bucket's first node during certain updates.
     *
     * This allows updates to different buckets to proceed
     * concurrently in many situations.
     *
     * IMPORTANT:
     *
     * It is not accurate to say that every operation is
     * completely lock-free.
     *
     * ConcurrentHashMap combines CAS, synchronization
     * and other coordination techniques.
     *
     *
     * 9. NULL KEYS AND NULL VALUES
     *
     * ConcurrentHashMap does not allow null keys.
     *
     * It does not allow null values.
     *
     * Example:
     *
     * map.put(null, "Java");
     *
     * Throws NullPointerException.
     *
     * map.put(101, null);
     *
     * Also throws NullPointerException.
     *
     * WHY?
     *
     * In concurrent code, a null result from get() can
     * unambiguously indicate that no mapping was found.
     *
     * If null values were allowed, get() returning null
     * could mean either:
     *
     * - No mapping exists.
     * - A mapping exists with a null value.
     *
     * Prohibiting null values avoids this ambiguity.
     *
     *
     * 10. DUPLICATE KEYS AND VALUES
     *
     * Duplicate keys are not allowed.
     *
     * Inserting the same key again replaces its value.
     *
     * Duplicate values are allowed.
     *
     * Key equality follows the normal Map contract using
     * hashCode() and equals().
     *
     *
     * 11. IMPORTANT MAP METHODS
     *
     * ConcurrentHashMap supports ordinary Map operations:
     *
     * put()
     * get()
     * remove()
     * containsKey()
     * containsValue()
     * putAll()
     * size()
     * isEmpty()
     * clear()
     *
     * It also supports atomic operations from ConcurrentMap:
     *
     * putIfAbsent()
     * remove(key, value)
     * replace(key, oldValue, newValue)
     * replace(key, value)
     *
     *
     * 12. ATOMIC OPERATIONS
     *
     * Atomic methods are one of the most important features
     * of ConcurrentHashMap.
     *
     * Consider:
     *
     * if (!map.containsKey(101)) {
     *     map.put(101, "Vinay");
     * }
     *
     * This is not an atomic sequence.
     *
     * Two threads can both observe that the key is absent
     * before either inserts it.
     *
     * Better:
     *
     * map.putIfAbsent(101, "Vinay");
     *
     * This performs the check and insertion atomically.
     *
     *
     * 13. COMPUTEIFABSENT()
     *
     * computeIfAbsent() is useful when a value should be
     * created only if the key is not already mapped.
     *
     * Example:
     *
     * ConcurrentHashMap<String, List<String>> map =
     *     new ConcurrentHashMap<>();
     *
     * map.computeIfAbsent(
     *     "Java",
     *     key -> new ArrayList<>()
     * );
     *
     * If the key is absent, the mapping function computes
     * a value and the mapping is installed atomically.
     *
     * If another thread is computing the same key,
     * the operations are coordinated.
     *
     * IMPORTANT:
     *
     * The mapping function should be short and simple.
     *
     * It should not recursively update the same Map in
     * a way that causes an illegal recursive update.
     *
     *
     * 14. COMPUTEIFPRESENT()
     *
     * Computes a new value only when the key is already mapped.
     *
     * Example:
     *
     * map.computeIfPresent(
     *     "Java",
     *     (key, value) -> value + " Programming"
     * );
     *
     * If the function returns null, the mapping is removed.
     *
     *
     * 15. COMPUTE()
     *
     * Computes a new mapping using the current value,
     * if any, and the supplied remapping function.
     *
     * Example:
     *
     * map.compute(
     *     "Java",
     *     (key, oldValue) ->
     *         oldValue == null ? "Core Java" : oldValue + " Advanced"
     * );
     *
     * The computation for the key is performed atomically.
     *
     * If the function returns null, the mapping is removed.
     *
     *
     * 16. MERGE()
     *
     * merge() is useful for combining values.
     *
     * Example: Frequency counter.
     *
     * ConcurrentHashMap<String, Integer> frequency =
     *     new ConcurrentHashMap<>();
     *
     * frequency.merge("Java", 1, Integer::sum);
     *
     * frequency.merge("Java", 1, Integer::sum);
     *
     * Result:
     *
     * Java -> 2
     *
     * The merge operation for the key is atomic.
     *
     *
     * 17. ITERATORS
     *
     * ConcurrentHashMap iterators are WEAKLY CONSISTENT.
     *
     * This means:
     *
     * - They do not throw ConcurrentModificationException
     *   merely because the Map is modified during iteration.
     *
     * - They may reflect some modifications made after
     *   the iterator was created.
     *
     * - They are not guaranteed to reflect every concurrent
     *   modification.
     *
     * Example:
     *
     * for (Map.Entry<Integer, String> entry :
     *      map.entrySet()) {
     *
     *     System.out.println(entry);
     *
     *     map.put(200, "New Value");
     * }
     *
     * This does not cause ConcurrentModificationException
     * merely because of that concurrent modification.
     *
     *
     * 18. FAIL-FAST VS WEAKLY CONSISTENT ITERATORS
     *
     * HashMap:
     *
     * Iterators are fail-fast on a best-effort basis.
     *
     * ConcurrentHashMap:
     *
     * Iterators are weakly consistent.
     *
     * Weakly consistent iteration supports concurrent
     * modifications without fail-fast exceptions.
     *
     * It does not provide a fixed snapshot of the Map.
     *
     *
     * 19. SIZE() AND AGGREGATE OPERATIONS
     *
     * ConcurrentHashMap supports:
     *
     * size()
     * isEmpty()
     * containsValue()
     *
     * Under concurrent updates, aggregate observations
     * may reflect transient states.
     *
     * For example:
     *
     * size() is useful for monitoring or estimation,
     * but should not generally be used as a synchronization
     * condition when other threads can modify the Map.
     *
     * ConcurrentHashMap also provides:
     *
     * mappingCount()
     *
     * It returns a long count of mappings.
     *
     *
     * 20. THREAD SAFETY VS ATOMICITY
     *
     * Thread safety:
     *
     * Concurrent operations do not corrupt the internal
     * data structure.
     *
     * Atomicity:
     *
     * An operation happens as one indivisible logical action.
     *
     * ConcurrentHashMap provides thread-safe individual
     * operations and specific atomic compound methods.
     *
     * But an arbitrary sequence of Map calls is not
     * automatically atomic.
     *
     * Prefer methods such as:
     *
     * putIfAbsent()
     * computeIfAbsent()
     * computeIfPresent()
     * compute()
     * merge()
     *
     * when their semantics match the required operation.
     *
     *
     * 21. CONCURRENTHASHMAP VS HASHTABLE
     *
     * Hashtable:
     *
     * - Legacy synchronized Map.
     * - Individual methods synchronize on the whole object.
     * - Does not allow null keys or values.
     * - Can suffer from greater lock contention.
     *
     * ConcurrentHashMap:
     *
     * - Designed for concurrent access.
     * - Uses more scalable internal coordination.
     * - Does not allow null keys or values.
     * - Provides atomic compound operations.
     * - Supports weakly consistent iterators.
     *
     *
     * 22. CONCURRENTHASHMAP VS HASHMAP
     *
     * HashMap:
     *
     * - Not thread-safe.
     * - Allows one null key and multiple null values.
     * - Iterators are fail-fast on a best-effort basis.
     * - Suitable for ordinary non-concurrent use.
     *
     * ConcurrentHashMap:
     *
     * - Thread-safe.
     * - Does not allow null keys or values.
     * - Iterators are weakly consistent.
     * - Designed for concurrent access.
     *
     *
     * 23. CONCURRENTHASHMAP VS SYNCHRONIZEDMAP
     *
     * Synchronized Map:
     *
     * Map<K, V> map =
     *     Collections.synchronizedMap(new HashMap<>());
     *
     * It wraps a HashMap and synchronizes access through
     * the wrapper's monitor.
     *
     * ConcurrentHashMap:
     *
     * Uses a specialized concurrent implementation.
     *
     * It generally allows more concurrency than a single
     * synchronized wrapper around a HashMap.
     *
     * IMPORTANT:
     *
     * When iterating over a synchronized Map wrapper,
     * manually synchronize on the wrapper.
     *
     * ConcurrentHashMap iterators do not require that
     * external iteration lock.
     *
     *
     * 24. PARALLEL BULK OPERATIONS
     *
     * ConcurrentHashMap provides bulk operations that can
     * process entries in parallel.
     *
     * Examples:
     *
     * forEach()
     * search()
     * reduce()
     *
     * These methods can use the ForkJoin common pool.
     *
     * A parallelism threshold controls when parallel
     * processing is considered.
     *
     * Example:
     *
     * map.forEach(
     *     1,
     *     (key, value) ->
     *         System.out.println(key + " = " + value)
     * );
     *
     * The threshold is an input controlling parallelism.
     *
     * A value of 1 can allow parallel processing even
     * for relatively small maps.
     *
     * Parallel execution is not always faster.
     *
     *
     * 25. PERFORMANCE AND TIME COMPLEXITY
     *
     * Common operations:
     *
     * get()             -> O(1) expected
     * put()             -> O(1) expected
     * remove()          -> O(1) expected
     * containsKey()     -> O(1) expected
     *
     * Heavy collision cases may require additional work.
     *
     * Tree bins help handle sufficiently large collision groups.
     *
     * Actual performance also depends on:
     *
     * - Hash distribution.
     * - Number of threads.
     * - Contention on particular keys or buckets.
     * - Resizing.
     * - Cost of computation functions.
     *
     *
     * 26. REAL-WORLD USE CASES
     *
     * ConcurrentHashMap is useful for:
     *
     * 1. Shared application caches.
     *
     * 2. Tracking request counts.
     *
     * 3. Maintaining concurrent user-session metadata.
     *
     * 4. Thread-safe frequency counters.
     *
     * 5. Maintaining shared configuration or lookup data.
     *
     * 6. Managing shared in-memory state in backend services.
     *
     * Example:
     *
     * ConcurrentHashMap<String, Long> requestCounts =
     *     new ConcurrentHashMap<>();
     *
     * requestCounts.merge("/api/users", 1L, Long::sum);
     *
     *
     * 27. WHEN SHOULD WE USE CONCURRENTHASHMAP?
     *
     * Use it when:
     *
     * - Multiple threads access a shared Map.
     * - Concurrent reads and updates are required.
     * - Null keys and values are not needed.
     * - Atomic per-key operations are useful.
     *
     * Use HashMap when:
     *
     * - The Map is not shared across concurrent threads.
     * - Ordinary non-concurrent operations are sufficient.
     *
     *
     * 28. COMMON MISTAKES
     *
     * 1. Assuming ConcurrentHashMap allows null values.
     *    It does not.
     *
     * 2. Assuming it locks the entire Map for every operation.
     *    Modern implementations use more granular coordination.
     *
     * 3. Assuming it is completely lock-free.
     *    It uses synchronization in some operations.
     *
     * 4. Assuming iterators provide a snapshot.
     *    They are weakly consistent.
     *
     * 5. Assuming thread safety makes multiple calls atomic.
     *    Use the appropriate atomic Map methods.
     *
     * 6. Performing long-running work inside compute functions.
     *    Keep mapping functions short and simple.
     *
     * 7. Using size() as a synchronization mechanism.
     *    Concurrent updates can make aggregate observations
     *    transient.
     *
     *
     * 29. INTERVIEW QUESTIONS
     *
     * 1. What is ConcurrentHashMap?
     * 2. Why do we need ConcurrentHashMap?
     * 3. Which interface does it implement?
     * 4. How is it different from Hashtable?
     * 5. How is it different from HashMap?
     * 6. Does it allow null keys or values?
     * 7. What is CAS?
     * 8. What is bucket-level synchronization?
     * 9. Does modern ConcurrentHashMap use Segment objects?
     * 10. What are weakly consistent iterators?
     * 11. What is putIfAbsent()?
     * 12. What is computeIfAbsent()?
     * 13. What is merge()?
     * 14. Are multiple Map operations automatically atomic?
     * 15. What is the purpose of mappingCount()?
     * 16. What are ConcurrentHashMap bulk operations?
     * 17. What happens when multiple threads update the same key?
     * 18. What is the difference between thread safety and
     *     atomicity?
     *
     *
     * 30. QUICK REVISION
     *
     * - ConcurrentHashMap belongs to java.util.concurrent.
     * - It implements ConcurrentMap.
     * - It is thread-safe.
     * - It does not allow null keys or values.
     * - Modern implementations use bucket arrays, CAS,
     *   synchronization and tree bins.
     * - It does not use the old fixed Segment architecture.
     * - It supports atomic per-key operations.
     * - It provides weakly consistent iterators.
     * - It does not guarantee snapshot iteration.
     * - It is generally preferred over Hashtable for
     *   modern concurrent Map use cases.
     *
     * ============================================================
     *                 END OF CONCURRENTHASHMAP
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about ConcurrentHashMap , Happy Learning ..!");
    }
}
