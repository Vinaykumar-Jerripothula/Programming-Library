
public class AdvancedComparisonAndNavigableCollections {

    /*
     * ============================================================
     * ADVANCED COMPARISON AND NAVIGABLE COLLECTIONS IN JAVA
     * ============================================================
     *
     * 1. WHY DO WE NEED ADVANCED COMPARISON?
     *
     * So far, we have learned:
     *
     * Comparable  -> Defines natural ordering inside a class.
     * Comparator  -> Defines external/custom ordering.
     *
     * But Java collections need comparison for more than sorting.
     *
     * Consider:
     *
     * - TreeSet must arrange elements in sorted order.
     * - TreeMap must arrange keys in sorted order.
     * - PriorityQueue must determine which element has priority.
     * - Binary search must compare elements.
     * - Navigable collections must find nearby elements.
     *
     * All these operations depend on comparison logic.
     *
     *
     * ============================================================
     * 2. COMPARISON CONTRACT - RECAP
     * ============================================================
     *
     * compareTo() and compare() return an integer:
     *
     * Negative value -> First element is smaller.
     * Zero           -> Both are equal under this ordering.
     * Positive value -> First element is greater.
     *
     * IMPORTANT:
     *
     * The exact negative or positive number does not matter.
     * Only its sign matters.
     *
     * Example:
     *
     * 10.compareTo(20) -> Negative
     * 20.compareTo(20) -> Zero
     * 30.compareTo(20) -> Positive
     *
     *
     * ============================================================
     * 3. COMPARATOR CONTRACT
     * ============================================================
     *
     * A valid Comparator must obey these important rules:
     *
     * A. SIGN SYMMETRY
     *
     * sign(compare(a, b)) must equal
     * -sign(compare(b, a)).
     *
     * If a is smaller than b, b must be greater than a.
     *
     * B. TRANSITIVITY
     *
     * If:
     * compare(a, b) > 0
     * compare(b, c) > 0
     *
     * Then:
     * compare(a, c) > 0
     *
     * C. CONSISTENCY WHEN COMPARISON RETURNS ZERO
     *
     * If compare(a, b) == 0, then for every z:
     *
     * sign(compare(a, z)) must equal
     * sign(compare(b, z)).
     *
     * D. OPTIONAL CONSISTENCY WITH EQUALS
     *
     * It is recommended that:
     *
     * compare(a, b) == 0
     *
     * agrees with:
     *
     * a.equals(b)
     *
     * However, this is not mandatory.
     *
     * IMPORTANT:
     *
     * A Comparator violating its contract may cause incorrect
     * sorting, unexpected collection behavior, or exceptions.
     *
     *
     * ============================================================
     * 4. COMPARISON RESULT ZERO IS IMPORTANT
     * ============================================================
     *
     * Consider:
     *
     * class Employee {
     *     int id;
     *     String name;
     * }
     *
     * Comparator<Employee> byId =
     *     Comparator.comparingInt(e -> e.id);
     *
     * Employee e1 = new Employee(101, "Vinay");
     * Employee e2 = new Employee(101, "Rahul");
     *
     * byId.compare(e1, e2) == 0
     *
     * Even though the names are different.
     *
     * Why?
     *
     * The comparator only considers employee IDs.
     *
     * This matters especially in TreeSet and TreeMap.
     *
     *
     * ============================================================
     * 5. TREESET AND COMPARISON
     * ============================================================
     *
     * TreeSet implements NavigableSet.
     *
     * It stores elements in sorted order.
     *
     * Internally, TreeSet is backed by a TreeMap.
     *
     * Example:
     *
     * TreeSet<Integer> numbers = new TreeSet<>();
     *
     * numbers.add(40);
     * numbers.add(10);
     * numbers.add(30);
     * numbers.add(20);
     *
     * Result:
     * [10, 20, 30, 40]
     *
     * IMPORTANT:
     *
     * TreeSet uses:
     * - Comparable, when no Comparator is supplied.
     * - Comparator, when a custom Comparator is supplied.
     *
     * It uses comparison results to determine ordering and
     * whether an element is considered a duplicate.
     *
     * If comparison returns zero, TreeSet treats the elements
     * as the same set element.
     *
     *
     * ============================================================
     * 6. TREESET WITH COMPARATOR
     * ============================================================
     *
     * Example:
     *
     * TreeSet<Integer> numbers = new TreeSet<>(
     *     Comparator.reverseOrder()
     * );
     *
     * numbers.add(10);
     * numbers.add(40);
     * numbers.add(20);
     * numbers.add(30);
     *
     * Result:
     * [40, 30, 20, 10]
     *
     * Custom object:
     *
     * TreeSet<String> names = new TreeSet<>(
     *     Comparator.comparingInt(String::length)
     * );
     *
     * names.add("Ram");
     * names.add("Vinay");
     * names.add("Joe");
     *
     * Result:
     * [Ram, Vinay]
     *
     * WHY IS JOE MISSING?
     *
     * "Ram" and "Joe" both have length 3.
     *
     * The comparator returns zero for them.
     *
     * TreeSet therefore treats them as equivalent for set
     * membership.
     *
     * SOLUTION:
     *
     * Add a secondary comparison:
     *
     * TreeSet<String> names = new TreeSet<>(
     *     Comparator.comparingInt(String::length)
     *               .thenComparing(Comparator.naturalOrder())
     * );
     *
     * Now equal-length names are also compared alphabetically.
     *
     *
     * ============================================================
     * 7. TREESET AND NULL
     * ============================================================
     *
     * With natural ordering:
     *
     * TreeSet<String> names = new TreeSet<>();
     *
     * names.add(null);
     *
     * This throws NullPointerException.
     *
     * WHY?
     *
     * Natural String ordering cannot compare null with a String.
     *
     * A custom Comparator can explicitly support null:
     *
     * TreeSet<String> names = new TreeSet<>(
     *     Comparator.nullsFirst(Comparator.naturalOrder())
     * );
     *
     * names.add(null);
     * names.add("Vinay");
     * names.add("Anil");
     *
     * Result:
     * [null, Anil, Vinay]
     *
     * Null support depends on the Comparator and collection
     * implementation.
     *
     *
     * ============================================================
     * 8. SORTEDSET INTERFACE
     * ============================================================
     *
     * SortedSet extends Set.
     *
     * It maintains elements in sorted order.
     *
     * Important methods:
     *
     * comparator()
     * first()
     * last()
     * headSet()
     * tailSet()
     * subSet()
     *
     * Example:
     *
     * SortedSet<Integer> numbers = new TreeSet<>(
     *     List.of(10, 20, 30, 40, 50)
     * );
     *
     * numbers.first(); // 10
     * numbers.last();  // 50
     *
     * numbers.headSet(30);
     * // [10, 20]
     *
     * numbers.tailSet(30);
     * // [30, 40, 50]
     *
     * numbers.subSet(20, 40);
     * // [20, 30]
     *
     * IMPORTANT:
     *
     * SortedSet range boundaries:
     *
     * headSet(toElement) -> Exclusive upper boundary.
     * tailSet(fromElement) -> Inclusive lower boundary.
     * subSet(from, to) -> Inclusive from, exclusive to.
     *
     *
     * ============================================================
     * 9. NAVIGABLESET INTERFACE
     * ============================================================
     *
     * NavigableSet extends SortedSet.
     *
     * It provides additional navigation methods.
     *
     * Think of it as a sorted set that can answer:
     *
     * - What is the closest smaller element?
     * - What is the closest greater element?
     * - What is the closest element including an exact match?
     *
     * Main methods:
     *
     * lower()
     * floor()
     * ceiling()
     * higher()
     * pollFirst()
     * pollLast()
     * descendingSet()
     * descendingIterator()
     *
     *
     * ============================================================
     * 10. LOWER, FLOOR, CEILING AND HIGHER
     * ============================================================
     *
     * Consider:
     *
     * NavigableSet<Integer> set = new TreeSet<>(
     *     List.of(10, 20, 30, 40, 50)
     * );
     *
     * Search value = 30.
     *
     * lower(30):
     * Greatest element strictly less than 30.
     * Result: 20
     *
     * floor(30):
     * Greatest element less than or equal to 30.
     * Result: 30
     *
     * ceiling(30):
     * Smallest element greater than or equal to 30.
     * Result: 30
     *
     * higher(30):
     * Smallest element strictly greater than 30.
     * Result: 40
     *
     * MEMORY TRICK:
     *
     * lower   -> Strictly lower.
     * floor   -> Lower or equal.
     * ceiling -> Equal or higher.
     * higher  -> Strictly higher.
     *
     *
     * ============================================================
     * 11. NAVIGABLESET WITH NON-EXISTING ELEMENT
     * ============================================================
     *
     * Consider:
     *
     * NavigableSet<Integer> set = new TreeSet<>(
     *     List.of(10, 20, 30, 40, 50)
     * );
     *
     * Search value = 35.
     *
     * lower(35);   // 30
     * floor(35);   // 30
     * ceiling(35); // 40
     * higher(35);  // 40
     *
     * If no matching element exists:
     *
     * These methods return null.
     *
     * Example:
     *
     * set.lower(10);  // null
     * set.higher(50); // null
     *
     *
     * ============================================================
     * 12. NAVIGABLESET RANGE METHODS
     * ============================================================
     *
     * NavigableSet provides:
     *
     * subSet(from, fromInclusive, to, toInclusive)
     * headSet(to, inclusive)
     * tailSet(from, inclusive)
     *
     * Example:
     *
     * NavigableSet<Integer> set = new TreeSet<>(
     *     List.of(10, 20, 30, 40, 50)
     * );
     *
     * set.subSet(20, true, 40, true);
     *
     * Result:
     * [20, 30, 40]
     *
     * set.headSet(30, true);
     *
     * Result:
     * [10, 20, 30]
     *
     * set.tailSet(30, false);
     *
     * Result:
     * [40, 50]
     *
     * IMPORTANT:
     *
     * These methods return backed views, not independent copies.
     *
     * Changes to the original set may be reflected in the view.
     *
     * Changes through the view can affect the original set.
     *
     *
     * ============================================================
     * 13. DESCENDINGSET AND DESCENDINGITERATOR
     * ============================================================
     *
     * descendingSet():
     * Returns a reverse-order view of the set.
     *
     * descendingIterator():
     * Traverses elements in descending order.
     *
     * Example:
     *
     * NavigableSet<Integer> set = new TreeSet<>(
     *     List.of(10, 20, 30, 40)
     * );
     *
     * set.descendingSet();
     *
     * Result:
     * [40, 30, 20, 10]
     *
     * Original set remains:
     * [10, 20, 30, 40]
     *
     * The returned set is a view backed by the original set.
     *
     *
     * ============================================================
     * 14. TREEMAP AND COMPARISON
     * ============================================================
     *
     * TreeMap implements NavigableMap.
     *
     * It stores entries sorted by keys.
     *
     * Internally, TreeMap uses a Red-Black Tree.
     *
     * Example:
     *
     * TreeMap<Integer, String> students = new TreeMap<>();
     *
     * students.put(103, "Rahul");
     * students.put(101, "Vinay");
     * students.put(102, "Anil");
     *
     * Result:
     *
     * {101=Vinay, 102=Anil, 103=Rahul}
     *
     * IMPORTANT:
     *
     * TreeMap sorts keys, not values.
     *
     * Key comparison determines ordering and key uniqueness.
     *
     * If two keys compare as zero, TreeMap treats them as
     * the same key.
     *
     *
     * ============================================================
     * 15. NAVIGABLEMAP INTERFACE
     * ============================================================
     *
     * NavigableMap extends SortedMap.
     *
     * It provides navigation methods for keys and entries.
     *
     * Important methods:
     *
     * lowerKey()
     * floorKey()
     * ceilingKey()
     * higherKey()
     *
     * lowerEntry()
     * floorEntry()
     * ceilingEntry()
     * higherEntry()
     *
     * firstKey()
     * lastKey()
     * firstEntry()
     * lastEntry()
     *
     * pollFirstEntry()
     * pollLastEntry()
     *
     * descendingMap()
     *
     *
     * ============================================================
     * 16. LOWERKEY, FLOORKEY, CEILINGKEY AND HIGHERKEY
     * ============================================================
     *
     * Example:
     *
     * NavigableMap<Integer, String> map = new TreeMap<>();
     *
     * map.put(10, "A");
     * map.put(20, "B");
     * map.put(30, "C");
     * map.put(40, "D");
     *
     * lowerKey(30);   // 20
     * floorKey(30);   // 30
     * ceilingKey(30); // 30
     * higherKey(30);  // 40
     *
     * Entry-returning methods:
     *
     * lowerEntry(30);
     * floorEntry(30);
     * ceilingEntry(30);
     * higherEntry(30);
     *
     * These return Map.Entry objects.
     *
     * If no matching key exists, the navigation methods return null.
     *
     *
     * ============================================================
     * 17. TREEMAP RANGE VIEWS
     * ============================================================
     *
     * NavigableMap provides:
     *
     * subMap()
     * headMap()
     * tailMap()
     *
     * Example:
     *
     * NavigableMap<Integer, String> map = new TreeMap<>();
     *
     * map.put(10, "A");
     * map.put(20, "B");
     * map.put(30, "C");
     * map.put(40, "D");
     *
     * map.subMap(20, true, 40, false);
     *
     * Result:
     * {20=B, 30=C}
     *
     * map.headMap(30, true);
     *
     * Result:
     * {10=A, 20=B, 30=C}
     *
     * map.tailMap(30, false);
     *
     * Result:
     * {40=D}
     *
     * These are backed views.
     *
     * Modifying a view affects the original map.
     *
     *
     * ============================================================
     * 18. DESCENDINGMAP
     * ============================================================
     *
     * descendingMap() returns a reverse-order view.
     *
     * Example:
     *
     * NavigableMap<Integer, String> map = new TreeMap<>();
     *
     * map.put(10, "A");
     * map.put(20, "B");
     * map.put(30, "C");
     *
     * map.descendingMap();
     *
     * Result:
     * {30=C, 20=B, 10=A}
     *
     * The original TreeMap remains in ascending key order.
     *
     *
     * ============================================================
     * 19. SORTEDSET VS NAVIGABLESET
     * ============================================================
     *
     * SortedSet:
     *
     * - Maintains sorted order.
     * - Provides first() and last().
     * - Provides headSet(), tailSet(), subSet().
     *
     * NavigableSet:
     *
     * - Extends SortedSet.
     * - Provides lower(), floor(), ceiling(), higher().
     * - Provides descendingSet().
     * - Provides pollFirst() and pollLast().
     * - Provides inclusive/exclusive range operations.
     *
     * Relationship:
     *
     * Set
     *   |
     * SortedSet
     *   |
     * NavigableSet
     *
     *
     * ============================================================
     * 20. SORTEDMAP VS NAVIGABLEMAP
     * ============================================================
     *
     * SortedMap:
     *
     * - Maintains keys in sorted order.
     * - Provides firstKey() and lastKey().
     * - Provides headMap(), tailMap(), subMap().
     *
     * NavigableMap:
     *
     * - Extends SortedMap.
     * - Provides lowerKey(), floorKey(), ceilingKey(),
     *   higherKey().
     * - Provides lowerEntry(), floorEntry(), ceilingEntry(),
     *   higherEntry().
     * - Provides descendingMap().
     * - Provides inclusive/exclusive range operations.
     *
     * Relationship:
     *
     * Map
     *   |
     * SortedMap
     *   |
     * NavigableMap
     *
     *
     * ============================================================
     * 21. COMPARATOR() AND COMPARATOR.REVERSED()
     * ============================================================
     *
     * SortedSet and SortedMap provide comparator().
     *
     * It returns the Comparator used for ordering.
     *
     * If natural ordering is used, comparator() returns null.
     *
     * Example:
     *
     * TreeSet<Integer> natural = new TreeSet<>();
     *
     * natural.comparator(); // null
     *
     * TreeSet<Integer> reverse = new TreeSet<>(
     *     Comparator.reverseOrder()
     * );
     *
     * reverse.comparator();
     *
     * Returns the Comparator used for reverse ordering.
     *
     * Comparator.reversed():
     *
     * Comparator<Integer> ascending = Comparator.naturalOrder();
     *
     * Comparator<Integer> descending = ascending.reversed();
     *
     * This creates a reversed ordering.
     *
     *
     * ============================================================
     * 22. PRIORITYQUEUE AND COMPARISON
     * ============================================================
     *
     * PriorityQueue also depends on comparison.
     *
     * It does not maintain the entire collection in sorted order.
     *
     * Instead, it ensures that the head is the least element
     * according to its ordering.
     *
     * Example:
     *
     * PriorityQueue<Integer> queue = new PriorityQueue<>();
     *
     * queue.add(40);
     * queue.add(10);
     * queue.add(30);
     *
     * queue.peek(); // 10
     *
     * A Comparator can define custom priority.
     *
     * PriorityQueue<Integer> queue = new PriorityQueue<>(
     *     Comparator.reverseOrder()
     * );
     *
     * queue.add(10);
     * queue.add(40);
     * queue.add(20);
     *
     * queue.peek(); // 40
     *
     * IMPORTANT:
     *
     * Iterating over a PriorityQueue does not guarantee sorted order.
     *
     * Repeatedly calling poll() retrieves elements in priority order.
     *
     *
     * ============================================================
     * 23. COMPARISON AND EQUALS()
     * ============================================================
     *
     * equals():
     * - Checks logical equality according to the class definition.
     *
     * compareTo() / compare():
     * - Determines ordering.
     * - A result of zero means equivalent under that ordering.
     *
     * These concepts are related but not identical.
     *
     * Example:
     *
     * Employee e1 = new Employee(101, "Vinay");
     * Employee e2 = new Employee(101, "Rahul");
     *
     * A Comparator comparing only IDs may return zero.
     *
     * But equals() may return false if it compares both ID
     * and name.
     *
     * This is allowed, but can cause surprising behavior in
     * TreeSet and TreeMap.
     *
     * BEST PRACTICE:
     *
     * Whenever possible, make natural ordering consistent
     * with equals().
     *
     * If a custom Comparator intentionally uses a different
     * definition of equivalence, understand the consequences.
     *
     *
     * ============================================================
     * 24. COMMON REAL-WORLD USE CASES
     * ============================================================
     *
     * TreeSet:
     * - Unique sorted student IDs.
     * - Sorted product prices.
     * - Maintaining ordered timestamps.
     *
     * TreeMap:
     * - Events ordered by timestamp.
     * - Scores ordered by rank or key.
     * - Range-based lookups.
     *
     * NavigableSet:
     * - Find the nearest available seat number.
     * - Find the closest price below or above a target.
     * - Find the next scheduled time.
     *
     * NavigableMap:
     * - Find the nearest timestamp.
     * - Retrieve the previous or next price level.
     * - Query a range of keys.
     *
     * PriorityQueue:
     * - Task scheduling.
     * - Job processing.
     * - Top-K problems.
     * - Shortest-path algorithms.
     *
     *
     * ============================================================
     * 25. COMPLEXITY OVERVIEW
     * ============================================================
     *
     * TreeSet:
     *
     * add()       -> O(log n)
     * remove()    -> O(log n)
     * contains()  -> O(log n)
     * lower()     -> O(log n)
     * floor()     -> O(log n)
     * ceiling()   -> O(log n)
     * higher()    -> O(log n)
     *
     * TreeMap:
     *
     * put()       -> O(log n)
     * get()       -> O(log n)
     * remove()    -> O(log n)
     * containsKey()-> O(log n)
     * Navigation  -> O(log n)
     *
     * PriorityQueue:
     *
     * offer()     -> O(log n)
     * poll()      -> O(log n)
     * peek()      -> O(1)
     * contains()  -> O(n)
     *
     * These are standard complexity expectations for the
     * usual implementations.
     *
     *
     * ============================================================
     * 26. IMPORTANT INTERVIEW QUESTIONS
     * ============================================================
     *
     * 1. What is the difference between Comparable and Comparator?
     *
     * 2. What happens when Comparator.compare() returns zero?
     *
     * 3. Why can TreeSet remove apparently different objects?
     *
     * 4. What is the difference between equals() and compareTo()?
     *
     * 5. What is SortedSet?
     *
     * 6. What is NavigableSet?
     *
     * 7. Difference between lower(), floor(), ceiling() and higher()?
     *
     * 8. What is NavigableMap?
     *
     * 9. Difference between TreeMap and HashMap?
     *
     * 10. Does TreeSet allow null?
     *
     * 11. Are TreeSet range views independent copies?
     *
     * 12. What does descendingSet() return?
     *
     * 13. Does PriorityQueue maintain complete sorted order?
     *
     * 14. What happens if a Comparator violates its contract?
     *
     * 15. Why is consistency between compareTo() and equals()
     *     recommended?
     *
     *
     * ============================================================
     * 27. QUICK REVISION
     * ============================================================
     *
     * Comparable:
     *     -> Defines natural ordering.
     *
     * Comparator:
     *     -> Defines custom ordering.
     *
     * compare() == 0:
     *     -> Equivalent under the Comparator's ordering.
     *
     * TreeSet:
     *     -> Sorted unique elements.
     *
     * TreeMap:
     *     -> Sorted keys.
     *
     * SortedSet:
     *     -> Sorted Set operations and range views.
     *
     * NavigableSet:
     *     -> SortedSet + navigation methods.
     *
     * SortedMap:
     *     -> Sorted Map operations and range views.
     *
     * NavigableMap:
     *     -> SortedMap + navigation methods.
     *
     * lower():
     *     -> Strictly smaller.
     *
     * floor():
     *     -> Smaller than or equal.
     *
     * ceiling():
     *     -> Greater than or equal.
     *
     * higher():
     *     -> Strictly greater.
     *
     * PriorityQueue:
     *     -> Head element has highest priority according to
     *        its Comparator or natural ordering.
     *
     * ============================================================
     * END OF ADVANCED COMPARISON AND NAVIGABLE COLLECTIONS
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Advanced Comparison and Navigable Collections , Happy Learning ..!");
    }
}
