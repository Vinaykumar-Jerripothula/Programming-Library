
public class ConcurrentSkipListMap {

    /*
     * ============================================================
     *                CONCURRENTSKIPLISTMAP IN JAVA
     * ============================================================
     *
     * 1. WHAT IS CONCURRENTSKIPLISTMAP?
     *
     * ConcurrentSkipListMap is a thread-safe, sorted Map
     * implementation available in java.util.concurrent.
     *
     * It stores key-value pairs and maintains keys in sorted order.
     *
     * It implements ConcurrentNavigableMap.
     *
     * Example:
     *
     * ConcurrentSkipListMap<Integer, String> map =
     *     new ConcurrentSkipListMap<>();
     *
     * map.put(30, "A");
     * map.put(10, "B");
     * map.put(20, "C");
     *
     * Iteration order:
     *
     * 10 -> B
     * 20 -> C
     * 30 -> A
     *
     *
     * 2. WHY DO WE NEED IT?
     *
     * Consider a backend application where multiple threads
     * update a shared Map.
     *
     * At the same time, the application needs:
     *
     * - Thread-safe operations.
     * - Sorted keys.
     * - Range-based queries.
     * - Navigation to nearby keys.
     *
     * HashMap:
     * Not thread-safe and does not guarantee sorted order.
     *
     * ConcurrentHashMap:
     * Thread-safe but does not maintain sorted keys.
     *
     * TreeMap:
     * Maintains sorted keys but is not thread-safe.
     *
     * ConcurrentSkipListMap:
     * Provides concurrent access and sorted key operations.
     *
     *
     * 3. PACKAGE AND INTERFACE HIERARCHY
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
     * ConcurrentNavigableMap
     *  |
     * ConcurrentSkipListMap
     *
     * ConcurrentNavigableMap extends:
     *
     * - ConcurrentMap
     * - NavigableMap
     *
     * Therefore, ConcurrentSkipListMap provides both
     * concurrent Map operations and sorted navigation.
     *
     *
     * 4. CREATING A CONCURRENTSKIPLISTMAP
     *
     * Import:
     *
     * import java.util.concurrent.ConcurrentSkipListMap;
     *
     * Default constructor:
     *
     * ConcurrentSkipListMap<Integer, String> map =
     *     new ConcurrentSkipListMap<>();
     *
     * Using Comparator:
     *
     * ConcurrentSkipListMap<Integer, String> map =
     *     new ConcurrentSkipListMap<>(
     *         Comparator.reverseOrder()
     *     );
     *
     * Using another Map:
     *
     * ConcurrentSkipListMap<Integer, String> map =
     *     new ConcurrentSkipListMap<>(anotherMap);
     *
     * Using another SortedMap:
     *
     * ConcurrentSkipListMap<Integer, String> map =
     *     new ConcurrentSkipListMap<>(anotherSortedMap);
     *
     *
     * 5. WHAT IS A SKIP LIST?
     *
     * A Skip List is a probabilistic data structure.
     *
     * It stores sorted elements in multiple levels of
     * linked lists.
     *
     * The bottom level contains all elements.
     *
     * Higher levels contain selected elements that act
     * like shortcuts.
     *
     * These shortcuts help us skip multiple elements
     * instead of visiting every element.
     *
     * Imagine searching for 90 in a normal linked list:
     *
     * 10 -> 20 -> 30 -> 40 -> 50 -> 60 -> 70 -> 80 -> 90
     *
     * We may need to visit many nodes.
     *
     * A Skip List adds higher-level links:
     *
     * Level 2: 10 ----------------> 50 ----------------> 90
     *
     * Level 1: 10 ------> 30 -----> 50 ------> 70 -----> 90
     *
     * Level 0: 10 -> 20 -> 30 -> 40 -> 50 -> 60 -> 70 -> 80 -> 90
     *
     * Searching can use higher levels to move forward faster.
     *
     * The diagram is conceptual; actual levels are
     * determined probabilistically.
     *
     *
     * 6. HOW DOES SKIP LIST SEARCH WORK?
     *
     * Suppose we want to find 70.
     *
     * Step 1:
     * Start from the highest available level.
     *
     * Step 2:
     * Move forward while the next key is smaller than 70.
     *
     * Step 3:
     * If the next key is greater than or equal to 70,
     * move down one level.
     *
     * Step 4:
     * Continue until reaching the bottom level.
     *
     * Step 5:
     * Find the required key or determine that it is absent.
     *
     * Higher levels reduce the number of nodes visited.
     *
     *
     * 7. WHY IS IT CALLED PROBABILISTIC?
     *
     * When a new element is inserted, its level is selected
     * using a random or pseudo-random process.
     *
     * Some elements appear only at the bottom level.
     *
     * Others appear at multiple higher levels.
     *
     * This produces a layered structure.
     *
     * A well-distributed Skip List provides expected
     * logarithmic search, insertion and deletion.
     *
     * Expected complexity:
     *
     * Search     -> O(log n)
     * Insertion  -> O(log n)
     * Deletion   -> O(log n)
     *
     * Worst-case complexity can be O(n).
     *
     *
     * 8. INTERNAL WORKING OF CONCURRENTSKIPLISTMAP
     *
     * ConcurrentSkipListMap uses a concurrent Skip List
     * structure internally.
     *
     * It supports concurrent operations without requiring
     * one global lock for every operation.
     *
     * Its implementation uses:
     *
     * - Ordered nodes.
     * - Multiple levels of indexing.
     * - Atomic and volatile operations.
     * - CAS-based coordination in important update paths.
     *
     * This helps multiple threads access and update
     * the structure concurrently.
     *
     * IMPORTANT:
     *
     * Its implementation is more sophisticated than
     * a simple linked list with random shortcuts.
     *
     * Do not assume that every internal update is
     * completely lock-free.
     *
     *
     * 9. SORTED KEY ORDER
     *
     * Keys are maintained in sorted order.
     *
     * By default, natural ordering is used.
     *
     * Example:
     *
     * ConcurrentSkipListMap<Integer, String> map =
     *     new ConcurrentSkipListMap<>();
     *
     * map.put(40, "A");
     * map.put(10, "B");
     * map.put(30, "C");
     *
     * Output:
     *
     * {10=B, 30=C, 40=A}
     *
     * Custom ordering is supported using Comparator.
     *
     *
     * 10. COMPARABLE AND COMPARATOR
     *
     * Comparable:
     *
     * Defines natural ordering inside the key class.
     *
     * Comparator:
     *
     * Defines external or custom ordering.
     *
     * Example:
     *
     * ConcurrentSkipListMap<String, Integer> map =
     *     new ConcurrentSkipListMap<>(
     *         Comparator.reverseOrder()
     *     );
     *
     * Keys are maintained in descending order.
     *
     * IMPORTANT:
     *
     * Keys must be mutually comparable according to
     * the natural ordering or supplied Comparator.
     *
     *
     * 11. NULL KEYS AND VALUES
     *
     * ConcurrentSkipListMap does not permit null keys.
     *
     * It does not permit null values.
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
     * This is consistent with ConcurrentHashMap.
     *
     *
     * 12. DUPLICATE KEYS AND VALUES
     *
     * Duplicate keys are not allowed.
     *
     * Inserting an equivalent key again replaces its value.
     *
     * Duplicate values are allowed.
     *
     * As with TreeMap, key equivalence is determined by
     * the ordering comparison.
     *
     * If the comparator returns zero for two keys,
     * they are treated as equivalent map keys.
     *
     *
     * 13. NAVIGATION METHODS
     *
     * ConcurrentSkipListMap implements NavigableMap.
     *
     * Therefore, it provides:
     *
     * lowerKey(key)
     * floorKey(key)
     * ceilingKey(key)
     * higherKey(key)
     *
     * Example:
     *
     * ConcurrentSkipListMap<Integer, String> map =
     *     new ConcurrentSkipListMap<>();
     *
     * map.put(10, "A");
     * map.put(20, "B");
     * map.put(30, "C");
     * map.put(40, "D");
     *
     * System.out.println(map.lowerKey(30));   // 20
     * System.out.println(map.floorKey(30));   // 30
     * System.out.println(map.ceilingKey(30)); // 30
     * System.out.println(map.higherKey(30));  // 40
     *
     *
     * 14. FIRST AND LAST ENTRIES
     *
     * firstKey()
     *
     * Returns the smallest key.
     *
     * lastKey()
     *
     * Returns the largest key.
     *
     * firstEntry()
     *
     * Returns the first entry.
     *
     * lastEntry()
     *
     * Returns the last entry.
     *
     * pollFirstEntry()
     *
     * Removes and returns the first entry.
     *
     * pollLastEntry()
     *
     * Removes and returns the last entry.
     *
     *
     * 15. RANGE VIEW METHODS
     *
     * ConcurrentSkipListMap supports:
     *
     * subMap()
     * headMap()
     * tailMap()
     *
     * Example:
     *
     * map.subMap(20, 40);
     *
     * Returns a view containing keys:
     *
     * 20 <= key < 40
     *
     * Other methods:
     *
     * map.headMap(30);
     *
     * Returns keys strictly less than 30.
     *
     * map.tailMap(30);
     *
     * Returns keys greater than or equal to 30.
     *
     * Inclusive overloads are also available.
     *
     * These are backed views, not independent copies.
     *
     * They support concurrent access according to the
     * ConcurrentNavigableMap contract.
     *
     *
     * 16. DESCENDING ORDER
     *
     * descendingMap()
     *
     * Returns a reverse-order view of the Map.
     *
     * descendingKeySet()
     *
     * Returns a reverse-order view of the keys.
     *
     * Example:
     *
     * System.out.println(map.descendingMap());
     *
     * These views reflect changes in the original Map.
     *
     *
     * 17. ITERATORS
     *
     * ConcurrentSkipListMap iterators are weakly consistent.
     *
     * This means:
     *
     * - They do not throw ConcurrentModificationException
     *   merely because the Map is modified during iteration.
     *
     * - They may reflect some concurrent modifications.
     *
     * - They do not provide a fixed snapshot.
     *
     * Example:
     *
     * for (Map.Entry<Integer, String> entry :
     *      map.entrySet()) {
     *
     *     System.out.println(entry);
     *
     *     map.put(50, "New Value");
     * }
     *
     * The iteration does not fail merely because the Map
     * is concurrently modified.
     *
     *
     * 18. THREAD SAFETY
     *
     * ConcurrentSkipListMap is thread-safe.
     *
     * Multiple threads can safely perform supported
     * operations concurrently.
     *
     * However:
     *
     * Thread safety of individual operations does not mean
     * an arbitrary sequence of operations is atomic.
     *
     * Use suitable atomic Map methods when needed.
     *
     *
     * 19. IMPORTANT METHODS
     *
     * Ordinary Map methods:
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
     * ConcurrentMap methods:
     *
     * putIfAbsent()
     * remove(key, value)
     * replace(key, oldValue, newValue)
     * replace(key, value)
     *
     * NavigableMap methods:
     *
     * lowerKey()
     * floorKey()
     * ceilingKey()
     * higherKey()
     * firstKey()
     * lastKey()
     * descendingMap()
     * subMap()
     * headMap()
     * tailMap()
     *
     *
     * 20. CONCURRENTSKIPLISTMAP VS TREEMAP
     *
     * TreeMap:
     *
     * - Uses a Red-Black Tree.
     * - Maintains sorted keys.
     * - Not thread-safe.
     * - Basic operations take O(log n).
     *
     * ConcurrentSkipListMap:
     *
     * - Uses a concurrent Skip List.
     * - Maintains sorted keys.
     * - Thread-safe.
     * - Search, insertion and deletion take O(log n)
     *   expected time.
     *
     * Use TreeMap for ordinary non-concurrent sorted Map operations.
     *
     * Use ConcurrentSkipListMap when concurrent access
     * and sorted navigation are both required.
     *
     *
     * 21. CONCURRENTSKIPLISTMAP VS CONCURRENTHASHMAP
     *
     * ConcurrentHashMap:
     *
     * - Thread-safe.
     * - Does not guarantee sorted key order.
     * - Hash-table based.
     * - Expected O(1) basic operations.
     *
     * ConcurrentSkipListMap:
     *
     * - Thread-safe.
     * - Maintains sorted key order.
     * - Skip-List based.
     * - Expected O(log n) basic operations.
     * - Provides navigation and range operations.
     *
     *
     * 22. SIZE() AND CONCURRENT UPDATES
     *
     * size() returns the current number of mappings.
     *
     * However, when other threads modify the Map concurrently,
     * the result can reflect a transient state.
     *
     * Do not use size() as a synchronization mechanism.
     *
     * containsValue() requires traversal and is generally
     * more expensive than key-based lookup.
     *
     *
     * 23. TIME COMPLEXITY
     *
     * Expected complexity:
     *
     * get()             -> O(log n)
     * put()             -> O(log n)
     * remove()          -> O(log n)
     * containsKey()     -> O(log n)
     * lowerKey()        -> O(log n)
     * floorKey()        -> O(log n)
     * ceilingKey()      -> O(log n)
     * higherKey()       -> O(log n)
     *
     * containsValue()   -> O(n)
     *
     * Iteration:
     * O(n) for all entries.
     *
     * size():
     * Generally O(1) for the current implementation, but
     * concurrent updates can make the observed count transient.
     *
     * Skip List operations have expected logarithmic complexity,
     * not a strict logarithmic worst-case guarantee.
     *
     *
     * 24. REAL-WORLD USE CASES
     *
     * ConcurrentSkipListMap is useful for:
     *
     * 1. Concurrent scheduling systems.
     *
     * 2. Maintaining events ordered by timestamp.
     *
     * 3. Concurrent price or score indexes.
     *
     * 4. Time-based lookup systems.
     *
     * 5. Maintaining sorted data shared by multiple threads.
     *
     * 6. Finding the nearest available key in concurrent systems.
     *
     *
     * 25. WHEN SHOULD WE USE IT?
     *
     * Use ConcurrentSkipListMap when:
     *
     * - Multiple threads access and update a shared Map.
     * - Keys must remain sorted.
     * - You need navigation methods.
     * - You need range-based views.
     *
     * Use ConcurrentHashMap when:
     *
     * - Concurrent access is required.
     * - Sorted key order is unnecessary.
     * - Expected constant-time key operations are preferred.
     *
     *
     * 26. COMMON MISTAKES
     *
     * 1. Assuming it uses a Red-Black Tree.
     *    It uses a Skip List.
     *
     * 2. Assuming it preserves insertion order.
     *    It maintains sorted key order.
     *
     * 3. Assuming it allows null keys or values.
     *    It does not.
     *
     * 4. Assuming it provides snapshot iterators.
     *    Its iterators are weakly consistent.
     *
     * 5. Assuming it is completely lock-free.
     *    Do not oversimplify its internal coordination.
     *
     * 6. Confusing it with ConcurrentHashMap.
     *    ConcurrentHashMap is hash-based and does not
     *    guarantee sorted order.
     *
     * 7. Assuming size() is a synchronization mechanism.
     *    It is not.
     *
     *
     * 27. INTERVIEW QUESTIONS
     *
     * 1. What is ConcurrentSkipListMap?
     * 2. Which interface does it implement?
     * 3. What is a Skip List?
     * 4. Why is a Skip List called probabilistic?
     * 5. How does searching work in a Skip List?
     * 6. What is the expected time complexity?
     * 7. Is ConcurrentSkipListMap thread-safe?
     * 8. Does it allow null keys or values?
     * 9. What are weakly consistent iterators?
     * 10. What is the difference between TreeMap and
     *     ConcurrentSkipListMap?
     * 11. What is the difference between ConcurrentHashMap
     *     and ConcurrentSkipListMap?
     * 12. What is ConcurrentNavigableMap?
     * 13. What are navigation methods?
     * 14. What are range views?
     * 15. Does it guarantee insertion order?
     *
     *
     * 28. QUICK REVISION
     *
     * - ConcurrentSkipListMap belongs to java.util.concurrent.
     * - It implements ConcurrentNavigableMap.
     * - It is thread-safe.
     * - It maintains sorted keys.
     * - It uses a concurrent Skip List internally.
     * - Skip Lists use multiple linked-list levels.
     * - Higher levels act as search shortcuts.
     * - Basic operations have expected O(log n) complexity.
     * - It does not allow null keys or values.
     * - Iterators are weakly consistent.
     * - It supports navigation and range operations.
     * - It is a concurrent sorted alternative to TreeMap.
     *
     * ============================================================
     *              END OF CONCURRENTSKIPLISTMAP
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about ConcurrentSkipListMap , Happy Learning ..!");
    }
}
