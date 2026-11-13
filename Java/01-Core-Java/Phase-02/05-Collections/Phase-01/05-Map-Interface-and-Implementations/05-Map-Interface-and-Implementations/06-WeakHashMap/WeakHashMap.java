
public class WeakHashMap {

    /*
     * ============================================================
     *                     WEAKHASHMAP IN JAVA
     * ============================================================
     *
     * 1. WHAT IS WEAKHASHMAP?
     *
     * WeakHashMap is a Map implementation in java.util.
     *
     * It stores key-value pairs like HashMap.
     *
     * But it has one important difference:
     *
     * WeakHashMap stores its keys using WEAK REFERENCES.
     *
     * This means:
     *
     * If a key object has no strong references elsewhere,
     * it becomes eligible for garbage collection.
     *
     * When the garbage collector collects that key,
     * its corresponding mapping can be removed from WeakHashMap.
     *
     *
     * 2. WHY DO WE NEED WEAKHASHMAP?
     *
     * Imagine an application that maintains a cache.
     *
     * Every time an object is created, we store some additional
     * information about it in a Map.
     *
     * Problem with HashMap:
     *
     * HashMap strongly references its keys.
     *
     * Even if the application no longer needs a key object,
     * the HashMap still holds a strong reference to it.
     *
     * Therefore, that key cannot be garbage-collected while
     * the mapping remains in the HashMap.
     *
     * This can cause memory retention.
     *
     * WeakHashMap helps by allowing keys to be garbage-collected
     * when no strong references to them remain.
     *
     *
     * 3. UNDERSTANDING STRONG AND WEAK REFERENCES
     *
     * STRONG REFERENCE:
     *
     * Object obj = new Object();
     *
     * This is a normal Java reference.
     *
     * As long as an object is reachable through strong references,
     * the garbage collector does not collect it.
     *
     *
     * WEAK REFERENCE:
     *
     * WeakReference<Object> ref =
     *     new WeakReference<>(new Object());
     *
     * A weak reference does not prevent its referent
     * from being garbage-collected.
     *
     * If the object has no strong references, it may be collected.
     *
     * WeakHashMap uses weak references for its keys.
     *
     *
     * 4. HASHMAP VS WEAKHASHMAP
     *
     * HashMap:
     *
     * Map strongly references its keys.
     *
     * Example:
     *
     * HashMap<Object, String> map = new HashMap<>();
     *
     * Object key = new Object();
     *
     * map.put(key, "Java");
     *
     * key = null;
     *
     * The object is still strongly reachable through the Map.
     *
     * It cannot be garbage-collected while that mapping remains.
     *
     *
     * WeakHashMap:
     *
     * WeakHashMap does not keep keys alive through strong
     * references.
     *
     * Example:
     *
     * WeakHashMap<Object, String> map = new WeakHashMap<>();
     *
     * Object key = new Object();
     *
     * map.put(key, "Java");
     *
     * key = null;
     *
     * Now, if no other strong reference to the key exists,
     * it becomes eligible for garbage collection.
     *
     * Once collected, its mapping can be removed from the map.
     *
     *
     * 5. INTERNAL WORKING
     *
     * WeakHashMap uses:
     *
     * - Hash table for storing entries.
     * - WeakReference-based keys.
     * - ReferenceQueue for tracking collected keys.
     *
     * Understand the process:
     *
     * Step 1:
     * A key-value pair is inserted.
     *
     * Step 2:
     * WeakHashMap stores the key through a weak reference.
     *
     * Step 3:
     * The application removes all strong references to the key.
     *
     * Step 4:
     * The key becomes eligible for garbage collection.
     *
     * Step 5:
     * The garbage collector may collect the key.
     *
     * Step 6:
     * The weak reference is cleared and registered with
     * the associated ReferenceQueue.
     *
     * Step 7:
     * WeakHashMap processes the queue and removes the
     * corresponding stale entry.
     *
     *
     * 6. WHAT IS A REFERENCEQUEUE?
     *
     * ReferenceQueue belongs to java.lang.ref.
     *
     * It helps an application learn when reference objects
     * have been cleared by the garbage collector.
     *
     * WeakHashMap uses a ReferenceQueue internally.
     *
     * When a key is collected, its weak reference can be
     * enqueued.
     *
     * WeakHashMap processes queued references and removes
     * the corresponding entries.
     *
     * IMPORTANT:
     *
     * The garbage collector does not directly remove the
     * mapping from the Map.
     *
     * WeakHashMap performs stale-entry cleanup.
     *
     *
     * 7. BASIC WEAKHASHMAP EXAMPLE
     *
     * import java.util.WeakHashMap;
     *
     * public class Example {
     *
     *     public static void main(String[] args) {
     *
     *         WeakHashMap<Object, String> map =
     *             new WeakHashMap<>();
     *
     *         Object key = new Object();
     *
     *         map.put(key, "Java");
     *
     *         System.out.println(map);
     *
     *         key = null;
     *
     *         System.gc();
     *
     *         System.out.println(map);
     *     }
     * }
     *
     * IMPORTANT:
     *
     * System.gc() only requests garbage collection.
     *
     * It does not guarantee that garbage collection will happen
     * immediately.
     *
     * Therefore, the second output is not guaranteed to be empty
     * at that exact moment.
     *
     *
     * 8. A MORE RELIABLE WAY TO UNDERSTAND THE EXAMPLE
     *
     * Object key = new Object();
     *
     * map.put(key, "Java");
     *
     * At this point:
     *
     * Application variable -> key object
     * WeakHashMap           -> weak reference to key object
     *
     * Now execute:
     *
     * key = null;
     *
     * The application variable no longer refers to the object.
     *
     * If no other strong reference exists, the object is
     * eligible for garbage collection.
     *
     * After the object is collected and WeakHashMap processes
     * the stale entry, the mapping disappears.
     *
     *
     * 9. IMPORTANT: VALUES ARE STRONGLY REFERENCED
     *
     * This is a very important concept.
     *
     * WeakHashMap uses weak references for KEYS.
     *
     * Its values are strongly referenced by the map entries.
     *
     * Example:
     *
     * Object key = new Object();
     * Object value = new Object();
     *
     * WeakHashMap<Object, Object> map = new WeakHashMap<>();
     *
     * map.put(key, value);
     *
     * key = null;
     *
     * Suppose the value object also holds a strong reference
     * to the key object.
     *
     * Then:
     *
     * WeakHashMap -> value -> key
     *
     * The key remains strongly reachable through the value.
     *
     * Therefore, the key may not be collected.
     *
     * This can prevent the expected cleanup.
     *
     * IMPORTANT:
     *
     * Weak keys do not automatically guarantee that the
     * entire key-value relationship can be garbage-collected.
     *
     * Be careful when values refer back to their keys.
     *
     *
     * 10. NULL KEYS AND VALUES
     *
     * WeakHashMap permits one null key.
     *
     * It permits multiple null values.
     *
     * Example:
     *
     * WeakHashMap<Object, String> map = new WeakHashMap<>();
     *
     * map.put(null, "Java");
     *
     * map.put(new Object(), null);
     *
     * Null key behavior is a special case:
     *
     * The null key is handled without an ordinary weak reference.
     *
     * It is not removed through garbage collection like
     * normal weakly referenced keys.
     *
     *
     * 11. DUPLICATE KEYS AND VALUES
     *
     * Duplicate keys are not allowed.
     *
     * Inserting an equivalent key again replaces its value.
     *
     * Duplicate values are allowed.
     *
     * WeakHashMap follows the normal Map key equality rules:
     *
     * hashCode() helps locate a bucket.
     * equals() determines key equality.
     *
     *
     * 12. IMPORTANT METHODS
     *
     * WeakHashMap implements Map.
     *
     * It supports ordinary Map operations:
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
     * It also inherits default Map methods such as:
     *
     * putIfAbsent()
     * computeIfAbsent()
     * computeIfPresent()
     * compute()
     * merge()
     * replace()
     * replaceAll()
     *
     * Note:
     *
     * The apparent size can decrease when stale entries
     * are cleaned up.
     *
     * Garbage collection and cleanup are not immediate.
     *
     *
     * 13. ITERATION AND ORDER
     *
     * WeakHashMap does not guarantee iteration order.
     *
     * Its iterators are fail-fast on a best-effort basis
     * for structural modifications.
     *
     * Because garbage collection can cause entries to disappear,
     * do not assume that mappings remain present indefinitely
     * just because they were inserted earlier.
     *
     *
     * 14. THREAD SAFETY
     *
     * WeakHashMap is NOT thread-safe.
     *
     * If multiple threads access it and at least one thread
     * modifies it, external synchronization may be required.
     *
     * Example:
     *
     * Map<Object, String> map =
     *     Collections.synchronizedMap(new WeakHashMap<>());
     *
     * Synchronizing the wrapper protects access to the Map,
     * but does not change the weak-reference behavior of keys.
     *
     * When iterating, synchronize on the wrapper:
     *
     * synchronized (map) {
     *     for (Map.Entry<Object, String> entry :
     *          map.entrySet()) {
     *
     *         System.out.println(entry);
     *     }
     * }
     *
     *
     * 15. WEAKHASHMAP VS HASHMAP
     *
     * HashMap:
     *
     * - Strongly references keys.
     * - Keys remain reachable through the Map.
     * - Entries remain until explicitly removed or the Map
     *   itself becomes unreachable.
     *
     * WeakHashMap:
     *
     * - Weakly references keys.
     * - Keys can be collected when no strong references remain.
     * - Stale entries are cleaned up after key collection.
     *
     *
     * 16. WEAKHASHMAP VS HASHTABLE
     *
     * Hashtable:
     *
     * - Synchronized individual methods.
     * - Strong references to keys.
     * - Does not allow null keys or null values.
     *
     * WeakHashMap:
     *
     * - Not synchronized.
     * - Weak references to keys.
     * - Allows one null key and null values.
     *
     *
     * 17. REAL-WORLD USE CASES
     *
     * WeakHashMap can be useful for:
     *
     * 1. Metadata associated with objects.
     *
     * 2. Caches where entries should not keep key objects alive.
     *
     * 3. Auxiliary information associated with objects whose
     *    lifecycle is controlled elsewhere.
     *
     * 4. Some memory-sensitive lookup tables.
     *
     * IMPORTANT:
     *
     * WeakHashMap is not a general-purpose predictable cache.
     *
     * Entries can disappear because of garbage collection.
     *
     * If an application requires predictable eviction based
     * on time, size or usage, use an appropriate cache design.
     *
     *
     * 18. WHEN SHOULD WE USE WEAKHASHMAP?
     *
     * Use it when:
     *
     * - Keys should not be kept alive merely by the Map.
     * - Metadata should follow the lifecycle of key objects.
     * - You can tolerate entries disappearing after key collection.
     *
     * Do not use it when:
     *
     * - Every inserted entry must remain available.
     * - You need predictable cache eviction.
     * - You need thread-safe concurrent access without additional
     *   synchronization.
     *
     *
     * 19. COMMON MISTAKES
     *
     * 1. Assuming System.gc() guarantees immediate collection.
     *    It does not.
     *
     * 2. Assuming WeakHashMap uses weak references for values.
     *    It uses weak references for keys.
     *
     * 3. Assuming setting a key variable to null immediately
     *    removes its mapping.
     *    Garbage collection and cleanup may happen later.
     *
     * 4. Assuming a key can be collected even when the value
     *    strongly references it.
     *    The value-to-key reference can keep it alive.
     *
     * 5. Assuming WeakHashMap is thread-safe.
     *    It is not.
     *
     * 6. Using WeakHashMap for a cache that requires predictable
     *    expiration or eviction.
     *
     *
     * 20. INTERVIEW QUESTIONS
     *
     * 1. What is WeakHashMap?
     * 2. How is it different from HashMap?
     * 3. What is a weak reference?
     * 4. What is a strong reference?
     * 5. How does garbage collection affect WeakHashMap?
     * 6. What is ReferenceQueue?
     * 7. Does WeakHashMap use weak keys or weak values?
     * 8. Does WeakHashMap allow null keys?
     * 9. Does WeakHashMap allow null values?
     * 10. Is WeakHashMap thread-safe?
     * 11. What happens when a key is no longer strongly reachable?
     * 12. Does System.gc() guarantee garbage collection?
     * 13. Can a value prevent its key from being collected?
     * 14. Does WeakHashMap preserve insertion order?
     * 15. What are practical use cases of WeakHashMap?
     *
     *
     * 21. QUICK REVISION
     *
     * - WeakHashMap is a Map implementation.
     * - It uses weak references for keys.
     * - Values are strongly referenced.
     * - It uses a hash table internally.
     * - It uses ReferenceQueue for stale-key cleanup.
     * - Keys can be garbage-collected when no strong references
     *   remain.
     * - Entry removal is not necessarily immediate.
     * - System.gc() is only a request.
     * - It allows one null key and multiple null values.
     * - It does not guarantee iteration order.
     * - It is not thread-safe.
     * - It is useful for object-associated metadata and
     *   memory-sensitive mappings.
     *
     * ============================================================
     *                    END OF WEAKHASHMAP
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about WeakHashMap , Happy Learning ..!");
    }
}
