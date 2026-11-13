
public class TreeMap {

    /*
     * ============================================================
     *                       TREEMAP IN JAVA
     * ============================================================
     *
     * 1. WHAT IS TREEMAP?
     *
     * TreeMap is a class in java.util that implements the
     * NavigableMap interface.
     *
     * It stores key-value pairs and maintains keys in sorted order.
     *
     * Example:
     *
     * Map<Integer, String> map = new TreeMap<>();
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
     * Notice:
     *
     * We inserted 30, 10, 20.
     * But TreeMap arranges entries according to key ordering.
     *
     * This is the main difference between TreeMap and HashMap.
     *
     *
     * 2. WHY DO WE NEED TREEMAP?
     *
     * Consider storing employee records using employee IDs.
     *
     * If we need:
     *
     * - Fast average key lookup without ordering -> HashMap
     * - Insertion-order iteration -> LinkedHashMap
     * - Sorted key iteration -> TreeMap
     *
     * TreeMap is useful when sorted keys and navigation operations
     * are important.
     *
     * Real-world examples:
     *
     * - Maintaining students in roll-number order.
     * - Maintaining products by price.
     * - Finding the nearest available appointment time.
     * - Finding the next or previous key.
     * - Performing range-based queries.
     *
     *
     * 3. TREEMAP HIERARCHY
     *
     * Map
     *  |
     * SortedMap
     *  |
     * NavigableMap
     *  |
     * TreeMap
     *
     * TreeMap implements NavigableMap.
     *
     * NavigableMap extends SortedMap.
     *
     * SortedMap extends Map.
     *
     * This gives TreeMap additional sorted and navigation methods.
     *
     *
     * 4. CREATING A TREEMAP
     *
     * Import:
     *
     * import java.util.TreeMap;
     * import java.util.Map;
     *
     * Default constructor:
     *
     * TreeMap<Integer, String> map = new TreeMap<>();
     *
     * Using Comparator:
     *
     * TreeMap<Integer, String> map =
     *     new TreeMap<>(Comparator.reverseOrder());
     *
     * Using another Map:
     *
     * TreeMap<Integer, String> map = new TreeMap<>(anotherMap);
     *
     * Using a SortedMap:
     *
     * TreeMap<Integer, String> map = new TreeMap<>(anotherSortedMap);
     *
     *
     * 5. INTERNAL DATA STRUCTURE
     *
     * TreeMap uses a RED-BLACK TREE internally.
     *
     * A Red-Black Tree is a self-balancing Binary Search Tree.
     *
     * First understand a Binary Search Tree.
     *
     * For every node:
     *
     * - Values smaller than the node go to the left subtree.
     * - Values greater than the node go to the right subtree.
     *
     * Example:
     *
     *             20
     *            /  \
     *          10    30
     *         / \    / \
     *        5  15  25  40
     *
     * This structure makes searching possible by comparing
     * the target with the current node.
     *
     * However, an ordinary BST can become unbalanced.
     *
     * Example:
     *
     * 10
     *   \
     *    20
     *      \
     *       30
     *         \
     *          40
     *
     * This behaves almost like a linked list.
     *
     * Searching can become O(n).
     *
     * A Red-Black Tree maintains balance using coloring rules
     * and tree rotations.
     *
     * This keeps operations logarithmic.
     *
     *
     * 6. RED-BLACK TREE PROPERTIES
     *
     * A Red-Black Tree follows these rules:
     *
     * 1. Every node is either RED or BLACK.
     *
     * 2. The root is BLACK.
     *
     * 3. Every NIL (null-leaf) node is considered BLACK.
     *
     * 4. A RED node cannot have a RED child.
     *
     * 5. Every path from a node to its descendant NIL leaves
     *    contains the same number of BLACK nodes.
     *
     * These rules keep the tree approximately balanced.
     *
     * Important result:
     *
     * Tree height is O(log n).
     *
     * Therefore:
     *
     * Search       -> O(log n)
     * Insertion    -> O(log n)
     * Deletion     -> O(log n)
     *
     *
     * 7. TREE ROTATIONS
     *
     * Rotations are operations used to restore balance
     * in a Red-Black Tree.
     *
     * Two important rotations:
     *
     * 1. Left rotation
     * 2. Right rotation
     *
     * Left rotation:
     *
     *       X                  Y
     *        \                /
     *         Y      ->      X
     *        /                \
     *       T                 T
     *
     * Right rotation:
     *
     *          Y             X
     *         /               \
     *        X       ->        Y
     *         \               /
     *          T             T
     *
     * These diagrams are simplified.
     * Real rotations also reconnect the surrounding subtrees.
     *
     * TreeMap handles these balancing operations internally.
     *
     *
     * 8. NATURAL ORDERING
     *
     * By default, TreeMap uses natural ordering of keys.
     *
     * For Integer:
     * Ascending numerical order.
     *
     * For String:
     * Lexicographical order.
     *
     * Example:
     *
     * TreeMap<Integer, String> map = new TreeMap<>();
     *
     * map.put(40, "A");
     * map.put(10, "B");
     * map.put(30, "C");
     *
     * Output:
     *
     * {10=B, 30=C, 40=A}
     *
     * Keys must be mutually comparable.
     *
     *
     * 9. USING COMPARATOR
     *
     * Comparator allows us to define custom key ordering.
     *
     * Example: Descending order.
     *
     * TreeMap<Integer, String> map =
     *     new TreeMap<>(Comparator.reverseOrder());
     *
     * map.put(10, "A");
     * map.put(20, "B");
     * map.put(30, "C");
     *
     * Output:
     *
     * {30=C, 20=B, 10=A}
     *
     * Another example:
     *
     * TreeMap<String, Integer> map =
     *     new TreeMap<>(Comparator.comparingInt(String::length));
     *
     * This compares strings by their lengths.
     *
     * IMPORTANT:
     *
     * If a Comparator returns zero for two keys, TreeMap
     * considers them equivalent for map ordering.
     *
     * A new mapping with such a key replaces the existing
     * mapping's value.
     *
     * Example:
     *
     * TreeMap<String, Integer> map =
     *     new TreeMap<>(Comparator.comparingInt(String::length));
     *
     * map.put("Java", 1);
     * map.put("Code", 2);
     *
     * Both strings have length 4.
     *
     * The comparator returns zero, so only one mapping remains.
     *
     * This is important when designing custom comparators.
     *
     *
     * 10. NULL KEYS AND VALUES
     *
     * TreeMap with natural ordering does not permit null keys.
     *
     * Example:
     *
     * TreeMap<Integer, String> map = new TreeMap<>();
     *
     * map.put(null, "A");
     *
     * This throws NullPointerException.
     *
     * Why?
     *
     * Natural ordering requires comparing keys.
     * Null cannot be compared using Integer's natural ordering.
     *
     * A custom Comparator can support null keys if it explicitly
     * handles them.
     *
     * Example:
     *
     * TreeMap<String, Integer> map =
     *     new TreeMap<>(Comparator.nullsFirst(
     *         Comparator.naturalOrder()
     *     ));
     *
     * map.put(null, 0);
     * map.put("Java", 1);
     *
     * Null values are permitted.
     *
     * Example:
     *
     * map.put("A", null);
     *
     *
     * 11. DUPLICATE KEYS AND VALUES
     *
     * Duplicate keys are not allowed.
     *
     * If an equivalent key is inserted again, its value is
     * replaced.
     *
     * Duplicate values are allowed.
     *
     * Important:
     *
     * TreeMap determines key equivalence using comparison:
     *
     * compareTo() or Comparator.compare().
     *
     * It does not use equals() as the primary mechanism
     * for deciding whether two keys represent the same mapping.
     *
     * Ideally, ordering should be consistent with equals()
     * to correctly obey the general Map contract.
     *
     *
     * 12. IMPORTANT TREEMAP METHODS
     *
     * TreeMap inherits ordinary Map methods:
     *
     * put()
     * get()
     * remove()
     * containsKey()
     * containsValue()
     * size()
     * clear()
     *
     * It also provides SortedMap and NavigableMap methods.
     *
     *
     * 13. FIRST AND LAST ENTRY
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
     * Returns the entry with the smallest key.
     *
     * lastEntry()
     *
     * Returns the entry with the largest key.
     *
     * Example:
     *
     * TreeMap<Integer, String> map = new TreeMap<>();
     *
     * map.put(30, "A");
     * map.put(10, "B");
     * map.put(20, "C");
     *
     * System.out.println(map.firstKey());  // 10
     * System.out.println(map.lastKey());   // 30
     * System.out.println(map.firstEntry());
     * System.out.println(map.lastEntry());
     *
     *
     * 14. NAVIGATION METHODS
     *
     * These are among the most useful TreeMap methods.
     *
     * lowerKey(key)
     *
     * Returns the greatest key strictly less than the given key.
     *
     * floorKey(key)
     *
     * Returns the greatest key less than or equal to the given key.
     *
     * higherKey(key)
     *
     * Returns the smallest key strictly greater than the given key.
     *
     * ceilingKey(key)
     *
     * Returns the smallest key greater than or equal to the given key.
     *
     * Example:
     *
     * TreeMap<Integer, String> map = new TreeMap<>();
     *
     * map.put(10, "A");
     * map.put(20, "B");
     * map.put(30, "C");
     * map.put(40, "D");
     *
     * System.out.println(map.lowerKey(30));   // 20
     * System.out.println(map.floorKey(30));   // 30
     * System.out.println(map.higherKey(30));  // 40
     * System.out.println(map.ceilingKey(30)); // 30
     *
     * These methods return null when no matching key exists.
     *
     *
     * 15. NAVIGATION ENTRY METHODS
     *
     * TreeMap also provides:
     *
     * lowerEntry(key)
     * floorEntry(key)
     * higherEntry(key)
     * ceilingEntry(key)
     *
     * These return Map.Entry objects instead of only keys.
     *
     * Example:
     *
     * Map.Entry<Integer, String> entry =
     *     map.floorEntry(30);
     *
     * if (entry != null) {
     *     System.out.println(entry.getKey());
     *     System.out.println(entry.getValue());
     * }
     *
     *
     * 16. POLLING FIRST AND LAST ENTRIES
     *
     * pollFirstEntry()
     *
     * - Returns and removes the first entry.
     *
     * pollLastEntry()
     *
     * - Returns and removes the last entry.
     *
     * Example:
     *
     * System.out.println(map.pollFirstEntry());
     * System.out.println(map.pollLastEntry());
     *
     * These methods return null when the map is empty.
     *
     *
     * 17. RANGE VIEW METHODS
     *
     * TreeMap supports range-based views.
     *
     * subMap(fromKey, toKey)
     *
     * Returns keys from the starting key (inclusive)
     * to the ending key (exclusive).
     *
     * headMap(toKey)
     *
     * Returns keys strictly less than the specified key.
     *
     * tailMap(fromKey)
     *
     * Returns keys greater than or equal to the specified key.
     *
     * Example:
     *
     * TreeMap<Integer, String> map = new TreeMap<>();
     *
     * map.put(10, "A");
     * map.put(20, "B");
     * map.put(30, "C");
     * map.put(40, "D");
     * map.put(50, "E");
     *
     * System.out.println(map.subMap(20, 40));
     *
     * Output:
     * {20=B, 30=C}
     *
     * System.out.println(map.headMap(30));
     *
     * Output:
     * {10=A, 20=B}
     *
     * System.out.println(map.tailMap(30));
     *
     * Output:
     * {30=C, 40=D, 50=E}
     *
     * These are backed views, not independent copies.
     *
     * Changes through a supported view operation are reflected
     * in the original TreeMap.
     *
     *
     * 18. INCLUSIVE AND EXCLUSIVE RANGE BOUNDARIES
     *
     * NavigableMap provides an overload:
     *
     * subMap(fromKey, fromInclusive, toKey, toInclusive)
     *
     * Example:
     *
     * map.subMap(20, true, 40, true);
     *
     * Includes both 20 and 40.
     *
     * Other overloads:
     *
     * headMap(toKey, inclusive)
     * tailMap(fromKey, inclusive)
     *
     * These allow precise control over range boundaries.
     *
     *
     * 19. DESCENDING ORDER
     *
     * descendingMap()
     *
     * Returns a reverse-order view of the map.
     *
     * Example:
     *
     * System.out.println(map.descendingMap());
     *
     * descendingKeySet()
     *
     * Returns a reverse-order view of the keys.
     *
     * Example:
     *
     * System.out.println(map.descendingKeySet());
     *
     * These are backed views.
     *
     *
     * 20. COMPLETE TREEMAP EXAMPLE
     *
     * import java.util.TreeMap;
     * import java.util.Map;
     *
     * public class TreeMapExample {
     *
     *     public static void main(String[] args) {
     *
     *         TreeMap<Integer, String> map = new TreeMap<>();
     *
     *         map.put(30, "Kiran");
     *         map.put(10, "Vinay");
     *         map.put(20, "Rahul");
     *         map.put(40, "Arjun");
     *
     *         // Sorted order
     *         System.out.println(map);
     *
     *         // Navigation
     *         System.out.println(map.lowerKey(30));
     *         System.out.println(map.higherKey(30));
     *
     *         // First and last
     *         System.out.println(map.firstKey());
     *         System.out.println(map.lastKey());
     *
     *         // Range
     *         System.out.println(map.subMap(10, 30));
     *
     *         // Reverse view
     *         System.out.println(map.descendingMap());
     *
     *         // Iterate
     *         for (Map.Entry<Integer, String> entry :
     *              map.entrySet()) {
     *
     *             System.out.println(
     *                 entry.getKey() + " = " + entry.getValue()
     *             );
     *         }
     *     }
     * }
     *
     *
     * 21. TIME COMPLEXITY
     *
     * Operation:
     *
     * put()              -> O(log n)
     * get()              -> O(log n)
     * remove()           -> O(log n)
     * containsKey()      -> O(log n)
     * firstKey()         -> O(log n) worst case
     * lastKey()          -> O(log n) worst case
     * lowerKey()         -> O(log n)
     * floorKey()         -> O(log n)
     * higherKey()        -> O(log n)
     * ceilingKey()       -> O(log n)
     * size()             -> O(1)
     * containsValue()    -> O(n)
     *
     * Iteration:
     * O(n) for all entries.
     *
     * The Red-Black Tree keeps its height logarithmic.
     *
     *
     * 22. TREEMAP VS HASHMAP
     *
     * HashMap:
     *
     * - Uses a hash table.
     * - Does not guarantee sorted key order.
     * - Average expected basic operations are O(1).
     * - Supports one null key.
     *
     * TreeMap:
     *
     * - Uses a Red-Black Tree.
     * - Maintains sorted key order.
     * - Basic operations are O(log n).
     * - Natural ordering does not permit null keys.
     *
     *
     * 23. TREEMAP VS LINKEDHASHMAP
     *
     * LinkedHashMap:
     *
     * - Maintains insertion order by default.
     * - Can maintain access order.
     * - Uses hash-based lookup.
     *
     * TreeMap:
     *
     * - Maintains sorted key order.
     * - Uses tree-based lookup.
     * - Provides navigation and range operations.
     *
     *
     * 24. THREAD SAFETY
     *
     * TreeMap is NOT thread-safe.
     *
     * Concurrent modifications require suitable synchronization.
     *
     * For concurrent sorted-map behavior, Java provides:
     *
     * ConcurrentSkipListMap
     *
     * It is a concurrent NavigableMap implementation.
     *
     *
     * 25. WHEN SHOULD WE USE TREEMAP?
     *
     * Use TreeMap when:
     *
     * - Keys must remain sorted.
     * - You need first and last keys.
     * - You need lower, floor, higher or ceiling operations.
     * - You need range-based views.
     * - You need reverse-order navigation.
     *
     * Use HashMap when sorted order is unnecessary and
     * average expected constant-time lookup is preferred.
     *
     * Use LinkedHashMap when insertion or access order is required.
     *
     *
     * 26. COMMON MISTAKES
     *
     * 1. Assuming TreeMap maintains insertion order.
     *    It maintains sorted key order.
     *
     * 2. Assuming TreeMap uses hashing.
     *    It uses a Red-Black Tree.
     *
     * 3. Assuming TreeMap permits null keys with natural ordering.
     *    It does not.
     *
     * 4. Confusing lowerKey() and floorKey().
     *    lowerKey() excludes an equal key.
     *    floorKey() includes an equal key.
     *
     * 5. Confusing higherKey() and ceilingKey().
     *    higherKey() excludes an equal key.
     *    ceilingKey() includes an equal key.
     *
     * 6. Assuming subMap() returns an independent copy.
     *    It returns a backed view.
     *
     * 7. Assuming TreeMap is thread-safe.
     *    It is not.
     *
     * 8. Using a Comparator that treats distinct intended keys
     *    as equal by returning zero.
     *
     *
     * 27. INTERVIEW QUESTIONS
     *
     * 1. What is TreeMap?
     * 2. Which interface does TreeMap implement?
     * 3. Which data structure is used internally?
     * 4. What is a Red-Black Tree?
     * 5. Why does TreeMap maintain sorted keys?
     * 6. What is the difference between TreeMap and HashMap?
     * 7. What is the difference between Comparable and Comparator?
     * 8. Does TreeMap permit null keys?
     * 9. What is lowerKey()?
     * 10. What is floorKey()?
     * 11. What is higherKey()?
     * 12. What is ceilingKey()?
     * 13. What is the difference between lowerKey() and floorKey()?
     * 14. What is NavigableMap?
     * 15. What is SortedMap?
     * 16. What does subMap() return?
     * 17. What are tree rotations?
     * 18. What is the time complexity of TreeMap operations?
     * 19. Is TreeMap thread-safe?
     * 20. What is ConcurrentSkipListMap?
     *
     *
     * 28. QUICK REVISION
     *
     * - TreeMap implements NavigableMap.
     * - NavigableMap extends SortedMap.
     * - TreeMap stores key-value pairs.
     * - Keys are maintained in sorted order.
     * - It uses a Red-Black Tree internally.
     * - Basic operations take O(log n).
     * - Natural ordering is used by default.
     * - Comparator supports custom ordering.
     * - Null values are allowed.
     * - Natural ordering does not permit null keys.
     * - Duplicate keys are not allowed according to comparison.
     * - lowerKey(), floorKey(), higherKey() and ceilingKey()
     *   provide navigation.
     * - subMap(), headMap() and tailMap() provide range views.
     * - It is not thread-safe.
     *
     * ============================================================
     *                       END OF TREEMAP
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about TreeMap , Happy Learning ..!");
    }
}
