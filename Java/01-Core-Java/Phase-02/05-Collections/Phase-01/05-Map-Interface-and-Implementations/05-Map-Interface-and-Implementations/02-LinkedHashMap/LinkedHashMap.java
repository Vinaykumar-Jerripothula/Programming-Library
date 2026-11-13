
public class LinkedHashMap {

    /*
     * ============================================================
     *                    LINKEDHASHMAP IN JAVA
     * ============================================================
     *
     * 1. WHAT IS LINKEDHASHMAP?
     *
     * LinkedHashMap is a class in java.util that implements
     * the Map interface.
     *
     * It combines two important data structures:
     *
     * 1. Hash table
     * 2. Doubly linked list
     *
     * Hash table:
     * Helps provide efficient key-based operations.
     *
     * Doubly linked list:
     * Maintains the encounter order of entries.
     *
     * In simple words:
     *
     * HashMap + Linked ordering = LinkedHashMap
     *
     * Example:
     *
     * Map<Integer, String> map = new LinkedHashMap<>();
     *
     * map.put(101, "Vinay");
     * map.put(102, "Rahul");
     * map.put(103, "Kiran");
     *
     * Iteration order:
     *
     * 101 -> Vinay
     * 102 -> Rahul
     * 103 -> Kiran
     *
     * The default ordering is insertion order.
     *
     *
     * 2. WHY DO WE NEED LINKEDHASHMAP?
     *
     * Consider inserting these entries:
     *
     * 30 -> A
     * 10 -> B
     * 20 -> C
     *
     * HashMap:
     * Does not guarantee iteration order.
     *
     * LinkedHashMap:
     * Maintains insertion order by default.
     *
     * Output order:
     *
     * 30 -> A
     * 10 -> B
     * 20 -> C
     *
     * This is useful when we need:
     *
     * - Fast average key-based lookup.
     * - Predictable iteration order.
     * - Insertion-order preservation.
     * - Access-order tracking for cache-like structures.
     *
     *
     * 3. CREATING A LINKEDHASHMAP
     *
     * Import:
     *
     * import java.util.LinkedHashMap;
     * import java.util.Map;
     *
     * Default constructor:
     *
     * LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
     *
     * With initial capacity:
     *
     * LinkedHashMap<Integer, String> map =
     *     new LinkedHashMap<>(32);
     *
     * With initial capacity and load factor:
     *
     * LinkedHashMap<Integer, String> map =
     *     new LinkedHashMap<>(32, 0.75f);
     *
     * With access-order mode:
     *
     * LinkedHashMap<Integer, String> map =
     *     new LinkedHashMap<>(16, 0.75f, true);
     *
     * The third constructor argument controls ordering:
     *
     * false -> Insertion order
     * true  -> Access order
     *
     *
     * 4. INTERNAL DATA STRUCTURE
     *
     * LinkedHashMap extends HashMap.
     *
     * Its entries are linked together using previous and
     * next references.
     *
     * Conceptually:
     *
     * Hash table:
     *
     * Bucket 0 -> Entry
     * Bucket 1 -> Entry
     * Bucket 2 -> Entry
     *
     * Linked ordering:
     *
     * HEAD
     *  |
     *  v
     * [Entry 1] <-> [Entry 2] <-> [Entry 3]
     *                                      |
     *                                     TAIL
     *
     * The hash table supports key-based lookup.
     * The linked structure supports ordered traversal.
     *
     * Important:
     *
     * The linked list is not a separate List object exposed
     * through the API. It is part of LinkedHashMap's internal
     * implementation.
     *
     *
     * 5. HOW DOES INSERTION ORDER WORK?
     *
     * Consider:
     *
     * LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
     *
     * map.put(30, "A");
     * map.put(10, "B");
     * map.put(20, "C");
     *
     * Iteration order:
     *
     * 30 -> A
     * 10 -> B
     * 20 -> C
     *
     * The order is based on insertion, not key sorting.
     *
     * Important:
     *
     * Updating the value of an existing key does not move
     * that entry to the end in insertion-order mode.
     *
     * Example:
     *
     * map.put(30, "Updated A");
     *
     * The entry for key 30 remains in its original position.
     *
     *
     * 6. INSERTION ORDER VS ACCESS ORDER
     *
     * LinkedHashMap supports two ordering modes.
     *
     * A. INSERTION ORDER
     *
     * This is the default behavior.
     *
     * Entries are traversed in the order they were inserted.
     *
     * Example:
     *
     * LinkedHashMap<Integer, String> map =
     *     new LinkedHashMap<>();
     *
     * map.put(1, "A");
     * map.put(2, "B");
     * map.put(3, "C");
     *
     * Iteration:
     *
     * 1 -> A
     * 2 -> B
     * 3 -> C
     *
     *
     * B. ACCESS ORDER
     *
     * In access-order mode, entries are arranged from
     * least recently accessed to most recently accessed.
     *
     * Create it using:
     *
     * LinkedHashMap<Integer, String> map =
     *     new LinkedHashMap<>(16, 0.75f, true);
     *
     * Example:
     *
     * map.put(1, "A");
     * map.put(2, "B");
     * map.put(3, "C");
     *
     * Initial order:
     *
     * 1 -> 2 -> 3
     *
     * Now access key 1:
     *
     * map.get(1);
     *
     * New order:
     *
     * 2 -> 3 -> 1
     *
     * Key 1 becomes the most recently accessed entry.
     *
     * Access key 2:
     *
     * map.get(2);
     *
     * New order:
     *
     * 3 -> 1 -> 2
     *
     * This is useful for implementing LRU caches.
     *
     *
     * 7. WHAT COUNTS AS AN ACCESS?
     *
     * In access-order mode, successful operations such as
     * these can move an entry to the most-recently-accessed end:
     *
     * - get(key)
     * - getOrDefault(key, defaultValue)
     * - put(key, value), when the key already exists
     * - putIfAbsent(key, value), when the key already exists
     * - compute(key, function)
     * - computeIfAbsent(key, function), when the key exists
     * - computeIfPresent(key, function)
     * - merge(key, value, function)
     *
     * Operations on collection views do not generally count
     * as accesses.
     *
     * For example:
     *
     * map.containsKey(1);
     *
     * does not move the entry to the end.
     *
     * Similarly, iterating over the map does not itself
     * count as accessing each entry.
     *
     *
     * 8. COMPLETE INSERTION-ORDER EXAMPLE
     *
     * LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
     *
     * map.put(101, "Vinay");
     * map.put(102, "Rahul");
     * map.put(103, "Kiran");
     *
     * map.put(102, "Updated Rahul");
     *
     * for (Map.Entry<Integer, String> entry : map.entrySet()) {
     *
     *     System.out.println(
     *         entry.getKey() + " = " + entry.getValue()
     *     );
     * }
     *
     * Output:
     *
     * 101 = Vinay
     * 102 = Updated Rahul
     * 103 = Kiran
     *
     * Updating key 102 does not change its insertion position.
     *
     *
     * 9. ACCESS-ORDER EXAMPLE
     *
     * LinkedHashMap<Integer, String> map =
     *     new LinkedHashMap<>(16, 0.75f, true);
     *
     * map.put(1, "A");
     * map.put(2, "B");
     * map.put(3, "C");
     *
     * System.out.println(map.keySet());
     *
     * Output:
     * [1, 2, 3]
     *
     * map.get(1);
     *
     * System.out.println(map.keySet());
     *
     * Output:
     * [2, 3, 1]
     *
     * map.get(2);
     *
     * System.out.println(map.keySet());
     *
     * Output:
     * [3, 1, 2]
     *
     *
     * 10. IMPLEMENTING AN LRU CACHE
     *
     * LRU means Least Recently Used.
     *
     * Imagine a cache that can store only three entries.
     *
     * When a new entry arrives and the cache is full:
     *
     * The least recently used entry should be removed.
     *
     * LinkedHashMap makes this easier using access-order mode
     * and removeEldestEntry().
     *
     * Example:
     *
     * import java.util.LinkedHashMap;
     * import java.util.Map;
     *
     * class LRUCache<K, V> extends LinkedHashMap<K, V> {
     *
     *     private final int capacity;
     *
     *     LRUCache(int capacity) {
     *
     *         super(16, 0.75f, true);
     *
     *         this.capacity = capacity;
     *     }
     *
     *     @Override
     *     protected boolean removeEldestEntry(
     *         Map.Entry<K, V> eldest
     *     ) {
     *
     *         return size() > capacity;
     *     }
     * }
     *
     * Usage:
     *
     * LRUCache<Integer, String> cache = new LRUCache<>(3);
     *
     * cache.put(1, "A");
     * cache.put(2, "B");
     * cache.put(3, "C");
     *
     * cache.get(1);
     *
     * cache.put(4, "D");
     *
     * System.out.println(cache);
     *
     * Key 2 is removed because it is now the least recently
     * accessed entry.
     *
     * Remaining order:
     *
     * 3 -> 1 -> 4
     *
     * Important:
     *
     * removeEldestEntry() is checked after a new mapping
     * is inserted or an existing mapping is updated.
     *
     * Returning true requests removal of the eldest entry.
     *
     * This simple example is not thread-safe.
     *
     *
     * 11. IMPORTANT METHODS
     *
     * LinkedHashMap inherits the Map API and HashMap operations.
     *
     * Common methods:
     *
     * put()
     * get()
     * getOrDefault()
     * putIfAbsent()
     * putAll()
     * remove()
     * containsKey()
     * containsValue()
     * replace()
     * replaceAll()
     * compute()
     * computeIfAbsent()
     * computeIfPresent()
     * merge()
     * keySet()
     * values()
     * entrySet()
     * clear()
     *
     * Special protected method:
     *
     * removeEldestEntry(Map.Entry<K, V> eldest)
     *
     * It can be overridden to implement automatic removal
     * of the eldest entry.
     *
     *
     * 12. NULL KEYS AND VALUES
     *
     * LinkedHashMap inherits HashMap's null handling.
     *
     * It permits:
     *
     * - One null key.
     * - Multiple null values.
     *
     * Example:
     *
     * LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
     *
     * map.put(null, 100);
     * map.put("A", null);
     * map.put("B", null);
     *
     * This is valid.
     *
     *
     * 13. DUPLICATE KEYS AND VALUES
     *
     * Duplicate keys:
     * Not allowed.
     *
     * Inserting an existing key replaces its value.
     *
     * Duplicate values:
     * Allowed.
     *
     *
     * 14. ITERATION ORDER
     *
     * LinkedHashMap provides predictable iteration order.
     *
     * In insertion-order mode:
     * Entries are traversed in insertion order.
     *
     * In access-order mode:
     * Entries are traversed from least recently accessed
     * to most recently accessed.
     *
     * Iteration using entrySet():
     *
     * for (Map.Entry<Integer, String> entry : map.entrySet()) {
     *
     *     System.out.println(
     *         entry.getKey() + " = " + entry.getValue()
     *     );
     * }
     *
     *
     * 15. TIME COMPLEXITY
     *
     * Operation:
     *
     * put()          -> O(1) average expected
     * get()          -> O(1) average expected
     * remove()       -> O(1) average expected
     * containsKey()  -> O(1) average expected
     * containsValue()-> O(n)
     * size()         -> O(1)
     *
     * Iteration:
     * O(n), where n is the number of mappings.
     *
     * Access-order maintenance adds linked-order updates
     * when an entry is accessed.
     *
     * Worst-case behavior depends on hashing, collisions
     * and implementation details.
     *
     *
     * 16. LINKEDHASHMAP VS HASHMAP
     *
     * HashMap:
     *
     * - Does not guarantee iteration order.
     * - Uses a hash table.
     * - Usually has slightly less overhead.
     *
     * LinkedHashMap:
     *
     * - Maintains insertion order by default.
     * - Supports access-order mode.
     * - Uses hash-based lookup plus linked ordering.
     * - Requires additional memory for ordering references.
     *
     * Both:
     *
     * - Implement Map.
     * - Allow one null key.
     * - Allow multiple null values.
     * - Allow duplicate values.
     * - Are not synchronized.
     *
     *
     * 17. LINKEDHASHMAP VS TREEMAP
     *
     * LinkedHashMap:
     *
     * - Maintains insertion or access order.
     * - Uses hash-based lookup.
     * - Does not sort keys.
     *
     * TreeMap:
     *
     * - Maintains keys in sorted order.
     * - Uses a Red-Black Tree.
     * - Supports navigation and range operations.
     *
     *
     * 18. THREAD SAFETY
     *
     * LinkedHashMap is NOT thread-safe.
     *
     * Concurrent access involving modifications requires
     * suitable synchronization.
     *
     * Collections.synchronizedMap() can provide a synchronized
     * wrapper.
     *
     * For example:
     *
     * Map<Integer, String> map =
     *     Collections.synchronizedMap(new LinkedHashMap<>());
     *
     * Synchronize on the returned map when iterating over it.
     *
     * Access-order operations such as get() can change the
     * iteration order, so they matter when coordinating access.
     *
     *
     * 19. FAIL-FAST ITERATORS
     *
     * LinkedHashMap iterators are fail-fast on a best-effort basis.
     *
     * Structural modifications outside the iterator may cause
     * ConcurrentModificationException.
     *
     * In access-order mode, an operation that changes the
     * encounter order can also be considered a structural
     * modification.
     *
     * Do not rely on fail-fast behavior for thread safety.
     *
     *
     * 20. WHEN SHOULD WE USE LINKEDHASHMAP?
     *
     * Use LinkedHashMap when:
     *
     * - Insertion order must be preserved.
     * - Access order is useful.
     * - Predictable iteration is required.
     * - A simple LRU-style cache is needed.
     *
     * Use HashMap when ordering is unnecessary.
     *
     * Use TreeMap when sorted key order is required.
     *
     *
     * 21. COMMON MISTAKES
     *
     * 1. Assuming LinkedHashMap sorts keys.
     *    It maintains encounter order, not sorted key order.
     *
     * 2. Assuming access order is enabled by default.
     *    Insertion order is the default.
     *
     * 3. Assuming get() always changes order.
     *    Only access-order mode changes order on qualifying access.
     *
     * 4. Assuming updating an existing key moves it in
     *    insertion-order mode.
     *    It does not.
     *
     * 5. Assuming LinkedHashMap is thread-safe.
     *    It is not.
     *
     * 6. Assuming removeEldestEntry() runs before insertion.
     *    It is checked after insertion or update.
     *
     *
     * 22. INTERVIEW QUESTIONS
     *
     * 1. What is LinkedHashMap?
     * 2. Which class does LinkedHashMap extend?
     * 3. Which interface does it implement?
     * 4. How does LinkedHashMap maintain insertion order?
     * 5. What is the difference between HashMap and LinkedHashMap?
     * 6. What is access-order mode?
     * 7. How do you enable access-order mode?
     * 8. What is an LRU cache?
     * 9. What is removeEldestEntry()?
     * 10. Does LinkedHashMap allow null?
     * 11. Is LinkedHashMap thread-safe?
     * 12. Does updating an existing key change insertion order?
     * 13. Does get() change order in access-order mode?
     * 14. What is the difference between LinkedHashMap and TreeMap?
     * 15. What additional memory does LinkedHashMap require?
     *
     *
     * 23. QUICK REVISION
     *
     * - LinkedHashMap extends HashMap.
     * - It implements Map.
     * - It combines hashing with linked ordering.
     * - Insertion order is the default.
     * - Access-order mode can be enabled using the constructor.
     * - Access order is useful for LRU cache implementation.
     * - removeEldestEntry() supports automatic eldest-entry removal.
     * - One null key and multiple null values are allowed.
     * - Duplicate keys are not allowed.
     * - Duplicate values are allowed.
     * - Average expected basic operations are O(1).
     * - Iteration takes O(n).
     * - It is not thread-safe.
     *
     * ============================================================
     *                    END OF LINKEDHASHMAP
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about LinkedHashMap , Happy Learning ..!");
    }
}
