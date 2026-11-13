
public class SequencedCollections {

    /*
     * ============================================================
     * 1. WHY WERE SEQUENCED COLLECTIONS INTRODUCED?
     * ============================================================
     *
     * Before Java 21, different collection types used different
     * approaches to access their first and last elements.
     *
     * Examples:
     *
     * List:
     * list.get(0);
     * list.get(list.size() - 1);
     *
     * Deque:
     * deque.getFirst();
     * deque.getLast();
     *
     * NavigableSet:
     * set.first();
     * set.last();
     *
     * NavigableMap:
     * map.firstKey();
     * map.lastKey();
     *
     * LinkedHashSet:
     * No direct getFirst() or getLast() methods before Java 21.
     *
     * There was no common interface for all these ordered
     * collection types.
     *
     * Java 21 introduced Sequenced Collections to solve this.
     *
     * The goal:
     *
     * - Provide a common abstraction for ordered collections.
     * - Standardize access to first and last elements.
     * - Support adding and removing at both ends where applicable.
     * - Provide reversed views.
     * - Reduce differences between collection APIs.
     *
     *
     * ============================================================
     * 2. WHAT IS A SEQUENCED COLLECTION?
     * ============================================================
     *
     * A SequencedCollection is a Collection that has a defined
     * encounter order.
     *
     * Encounter order means the order in which elements are
     * presented when traversing the collection.
     *
     * Example:
     *
     * [10, 20, 30, 40]
     *
     * First element: 10
     * Last element : 40
     *
     * The SequencedCollection interface provides common methods
     * for accessing and modifying both ends.
     *
     * Package:
     *
     * java.util
     *
     *
     * ============================================================
     * 3. SEQUENCED COLLECTION INTERFACE HIERARCHY
     * ============================================================
     *
     * Collection
     *     |
     *     +---- SequencedCollection
     *                |
     *                +---- List
     *                |
     *                +---- Deque
     *
     * SequencedSet extends:
     *     Set
     *     SequencedCollection
     *
     * SequencedMap extends:
     *     Map
     *
     * Important Java 21 types:
     *
     * - SequencedCollection<E>
     * - SequencedSet<E>
     * - SequencedMap<K, V>
     *
     * Existing interfaces such as List, Deque and LinkedHashSet
     * gained integration with the new sequenced interfaces.
     *
     *
     * ============================================================
     * 4. SEQUENCEDCOLLECTION - IMPORTANT METHODS
     * ============================================================
     *
     * The SequencedCollection interface provides:
     *
     * addFirst(E element)
     * addLast(E element)
     *
     * getFirst()
     * getLast()
     *
     * removeFirst()
     * removeLast()
     *
     * reversed()
     *
     *
     * METHOD EXPLANATION:
     *
     * addFirst():
     * Adds an element at the beginning.
     *
     * addLast():
     * Adds an element at the end.
     *
     * getFirst():
     * Returns the first element without removing it.
     *
     * getLast():
     * Returns the last element without removing it.
     *
     * removeFirst():
     * Removes and returns the first element.
     *
     * removeLast():
     * Removes and returns the last element.
     *
     * reversed():
     * Returns a reversed-order view of the collection.
     *
     * IMPORTANT:
     *
     * Whether adding at a particular end is supported depends
     * on the collection implementation.
     *
     * For example, a sorted collection cannot arbitrarily
     * reposition an element at the beginning or end.
     *
     *
     * ============================================================
     * 5. EXAMPLE USING SEQUENCEDCOLLECTION
     * ============================================================
     *
     * Example:
     *
     * SequencedCollection<String> languages =
     *     new ArrayList<>(
     *         List.of("Java", "Python", "C++")
     *     );
     *
     * languages.addFirst("C");
     * languages.addLast("JavaScript");
     *
     * System.out.println(languages);
     *
     * Output:
     * [C, Java, Python, C++, JavaScript]
     *
     * Accessing both ends:
     *
     * System.out.println(languages.getFirst());
     * // C
     *
     * System.out.println(languages.getLast());
     * // JavaScript
     *
     * Removing both ends:
     *
     * languages.removeFirst();
     * languages.removeLast();
     *
     * System.out.println(languages);
     *
     * Output:
     * [Java, Python, C++]
     *
     * Notice:
     *
     * We are using the SequencedCollection reference type,
     * even though the actual object is an ArrayList.
     *
     * This is interface-based programming.
     *
     *
     * ============================================================
     * 6. WHAT IS A REVERSED VIEW?
     * ============================================================
     *
     * reversed() returns a view that presents the elements
     * in reverse encounter order.
     *
     * Example:
     *
     * List<Integer> numbers =
     *     new ArrayList<>(List.of(10, 20, 30, 40));
     *
     * List<Integer> reversed = numbers.reversed();
     *
     * System.out.println(reversed);
     *
     * Output:
     * [40, 30, 20, 10]
     *
     * IMPORTANT:
     *
     * reversed() does not create an independent copy.
     *
     * It returns a view backed by the original collection.
     *
     * Example:
     *
     * reversed.set(0, 400);
     *
     * System.out.println(numbers);
     *
     * Output:
     * [10, 20, 30, 400]
     *
     * The original List is modified.
     *
     * Likewise, structural changes through a supported reversed
     * view affect the original collection.
     *
     * Think of it as looking at the same collection from
     * the opposite direction.
     *
     *
     * ============================================================
     * 7. SEQUENCEDSET
     * ============================================================
     *
     * SequencedSet is an interface that combines:
     *
     * - Set uniqueness.
     * - SequencedCollection encounter order.
     *
     * It represents a Set whose elements have a defined
     * encounter order.
     *
     * Example implementation:
     *
     * LinkedHashSet.
     *
     * Important methods are inherited from SequencedCollection:
     *
     * addFirst()
     * addLast()
     * getFirst()
     * getLast()
     * removeFirst()
     * removeLast()
     * reversed()
     *
     * It also provides:
     *
     * SequencedSet<E> reversed()
     *
     * The reversed view presents the same elements in
     * reverse encounter order.
     *
     *
     * ============================================================
     * 8. LINKEDHASHSET WITH SEQUENCEDSET
     * ============================================================
     *
     * Before Java 21:
     *
     * LinkedHashSet preserves insertion order, but did not
     * provide the common first/last methods.
     *
     * Java 21:
     *
     * LinkedHashSet implements SequencedSet.
     *
     * Example:
     *
     * LinkedHashSet<String> languages =
     *     new LinkedHashSet<>();
     *
     * languages.add("Java");
     * languages.add("Python");
     * languages.add("C++");
     *
     * System.out.println(languages.getFirst());
     * // Java
     *
     * System.out.println(languages.getLast());
     * // C++
     *
     * Add at the beginning:
     *
     * languages.addFirst("C");
     *
     * Add at the end:
     *
     * languages.addLast("JavaScript");
     *
     * System.out.println(languages);
     *
     * Output:
     * [C, Java, Python, C++, JavaScript]
     *
     * IMPORTANT:
     *
     * A Set still does not allow duplicates.
     *
     * Adding an element that is already present does not create
     * another occurrence.
     *
     * LinkedHashSet can reposition an existing element using
     * addFirst() or addLast() according to its sequenced ordering.
     *
     *
     * ============================================================
     * 9. REVERSED LINKEDHASHSET
     * ============================================================
     *
     * Example:
     *
     * LinkedHashSet<Integer> numbers =
     *     new LinkedHashSet<>(
     *         List.of(10, 20, 30, 40)
     *     );
     *
     * SequencedSet<Integer> reversed =
     *     numbers.reversed();
     *
     * System.out.println(reversed);
     *
     * Output:
     * [40, 30, 20, 10]
     *
     * It is a reversed view, not an independent copy.
     *
     * Removing an element through the reversed view also
     * removes it from the original Set.
     *
     *
     * ============================================================
     * 10. SORTEDSET AND SEQUENCEDSET
     * ============================================================
     *
     * SortedSet represents a Set maintained according to
     * natural ordering or a Comparator.
     *
     * Java 21 integrates SortedSet with SequencedSet.
     *
     * Example:
     *
     * TreeSet<Integer> numbers =
     *     new TreeSet<>(
     *         List.of(40, 10, 30, 20)
     *     );
     *
     * System.out.println(numbers);
     *
     * Output:
     * [10, 20, 30, 40]
     *
     * System.out.println(numbers.getFirst());
     * // 10
     *
     * System.out.println(numbers.getLast());
     * // 40
     *
     * IMPORTANT:
     *
     * TreeSet determines order through its sorting rules.
     *
     * It cannot arbitrarily place an element at the beginning
     * or end while violating the comparator.
     *
     * Therefore, operations such as addFirst() and addLast()
     * are unsupported by TreeSet and throw
     * UnsupportedOperationException.
     *
     * Use add() to insert elements according to sorted order.
     *
     *
     * ============================================================
     * 11. SEQUENCEDMAP
     * ============================================================
     *
     * SequencedMap is the Map counterpart of SequencedCollection.
     *
     * It represents a Map with a defined encounter order
     * for its key-value mappings.
     *
     * Important methods:
     *
     * putFirst(K key, V value)
     * putLast(K key, V value)
     *
     * firstEntry()
     * lastEntry()
     *
     * pollFirstEntry()
     * pollLastEntry()
     *
     * reversed()
     *
     * sequencedKeySet()
     * sequencedValues()
     * sequencedEntrySet()
     *
     *
     * METHOD EXPLANATION:
     *
     * putFirst():
     * Places a mapping at the beginning where supported.
     *
     * putLast():
     * Places a mapping at the end where supported.
     *
     * firstEntry():
     * Returns the first key-value mapping.
     *
     * lastEntry():
     * Returns the last key-value mapping.
     *
     * pollFirstEntry():
     * Removes and returns the first mapping.
     *
     * pollLastEntry():
     * Removes and returns the last mapping.
     *
     * reversed():
     * Returns a reversed-order view of the Map.
     *
     *
     * ============================================================
     * 12. LINKEDHASHMAP WITH SEQUENCEDMAP
     * ============================================================
     *
     * LinkedHashMap implements SequencedMap in Java 21.
     *
     * Example:
     *
     * LinkedHashMap<Integer, String> students =
     *     new LinkedHashMap<>();
     *
     * students.put(101, "Vinay");
     * students.put(102, "Ravi");
     * students.put(103, "Kiran");
     *
     * System.out.println(students.firstEntry());
     *
     * Output:
     * 101=Vinay
     *
     * System.out.println(students.lastEntry());
     *
     * Output:
     * 103=Kiran
     *
     *
     * ============================================================
     * 13. PUTFIRST() AND PUTLAST()
     * ============================================================
     *
     * Example:
     *
     * LinkedHashMap<Integer, String> students =
     *     new LinkedHashMap<>();
     *
     * students.put(101, "Vinay");
     * students.put(102, "Ravi");
     *
     * students.putFirst(100, "Kiran");
     * students.putLast(103, "Arun");
     *
     * System.out.println(students);
     *
     * Encounter order:
     *
     * 100=Kiran
     * 101=Vinay
     * 102=Ravi
     * 103=Arun
     *
     * IMPORTANT:
     *
     * A Map still maintains unique keys.
     *
     * If a key already exists, putting it at an endpoint
     * changes the position of its mapping where supported.
     *
     * The values themselves need not be unique.
     *
     *
     * ============================================================
     * 14. FIRSTENTRY() AND LASTENTRY()
     * ============================================================
     *
     * These methods return the first and last mappings.
     *
     * Example:
     *
     * Map.Entry<Integer, String> first =
     *     students.firstEntry();
     *
     * Map.Entry<Integer, String> last =
     *     students.lastEntry();
     *
     * System.out.println(first);
     * System.out.println(last);
     *
     * IMPORTANT:
     *
     * These methods do not remove mappings.
     *
     * They return immutable Map.Entry snapshots.
     *
     * To remove mappings, use pollFirstEntry() or
     * pollLastEntry().
     *
     *
     * ============================================================
     * 15. POLLFIRSTENTRY() AND POLLLASTENTRY()
     * ============================================================
     *
     * Example:
     *
     * LinkedHashMap<Integer, String> students =
     *     new LinkedHashMap<>();
     *
     * students.put(101, "Vinay");
     * students.put(102, "Ravi");
     * students.put(103, "Kiran");
     *
     * Map.Entry<Integer, String> removed =
     *     students.pollFirstEntry();
     *
     * System.out.println(removed);
     * // 101=Vinay
     *
     * System.out.println(students);
     * // {102=Ravi, 103=Kiran}
     *
     * pollLastEntry() removes and returns the last mapping.
     *
     * If the Map is empty, firstEntry(), lastEntry(),
     * pollFirstEntry() and pollLastEntry() return null.
     *
     *
     * ============================================================
     * 16. SEQUENCED MAP VIEWS
     * ============================================================
     *
     * SequencedMap provides ordered views:
     *
     * sequencedKeySet()
     * sequencedValues()
     * sequencedEntrySet()
     *
     * Example:
     *
     * LinkedHashMap<Integer, String> students =
     *     new LinkedHashMap<>();
     *
     * students.put(101, "Vinay");
     * students.put(102, "Ravi");
     *
     * SequencedSet<Integer> keys =
     *     students.sequencedKeySet();
     *
     * SequencedCollection<String> values =
     *     students.sequencedValues();
     *
     * SequencedSet<Map.Entry<Integer, String>> entries =
     *     students.sequencedEntrySet();
     *
     * These views preserve the Map's encounter order.
     *
     * They are backed views.
     *
     * Changes through supported view operations affect
     * the underlying Map.
     *
     *
     * ============================================================
     * 17. REVERSED SEQUENCEDMAP
     * ============================================================
     *
     * Example:
     *
     * LinkedHashMap<Integer, String> students =
     *     new LinkedHashMap<>();
     *
     * students.put(101, "Vinay");
     * students.put(102, "Ravi");
     * students.put(103, "Kiran");
     *
     * SequencedMap<Integer, String> reversed =
     *     students.reversed();
     *
     * System.out.println(reversed);
     *
     * Encounter order:
     *
     * 103=Kiran
     * 102=Ravi
     * 101=Vinay
     *
     * This is a reversed view, not an independent copy.
     *
     * Changes made through the reversed Map affect the
     * original Map where supported.
     *
     *
     * ============================================================
     * 18. TREEMAP AND SEQUENCEDMAP
     * ============================================================
     *
     * TreeMap implements NavigableMap, which integrates with
     * SequencedMap in Java 21.
     *
     * Example:
     *
     * TreeMap<Integer, String> students = new TreeMap<>();
     *
     * students.put(103, "Kiran");
     * students.put(101, "Vinay");
     * students.put(102, "Ravi");
     *
     * System.out.println(students);
     *
     * Output:
     * {101=Vinay, 102=Ravi, 103=Kiran}
     *
     * System.out.println(students.firstEntry());
     * // 101=Vinay
     *
     * System.out.println(students.lastEntry());
     * // 103=Kiran
     *
     * The ordering is controlled by the TreeMap comparator.
     *
     * putFirst() and putLast() are unsupported because
     * TreeMap must maintain its sorted key order.
     *
     *
     * ============================================================
     * 19. SEQUENCED COLLECTIONS VS DEQUE
     * ============================================================
     *
     * Both SequencedCollection and Deque support operations
     * at both ends.
     *
     * However, they have different purposes.
     *
     * SEQUENCEDCOLLECTION:
     *
     * - General abstraction for encounter-ordered collections.
     * - Provides first/last access and modification methods.
     * - Does not require Queue behavior.
     *
     * DEQUE:
     *
     * - Double-ended queue.
     * - Supports insertion, removal and inspection at both ends.
     * - Extends Queue.
     * - Supports queue and stack operations.
     *
     * Examples:
     *
     * SequencedCollection<String> list = new ArrayList<>();
     *
     * Deque<String> deque = new ArrayDeque<>();
     *
     * Both provide first/last operations, but Deque also
     * provides queue-specific operations such as:
     *
     * offer()
     * poll()
     * peek()
     *
     *
     * ============================================================
     * 20. IMPLEMENTATIONS AND THEIR SEQUENCED SUPPORT
     * ============================================================
     *
     * ArrayList:
     * - Implements List.
     * - Supports SequencedCollection.
     * - Supports first/last operations.
     * - Supports reversed views.
     *
     * LinkedList:
     * - Implements List and Deque.
     * - Supports SequencedCollection.
     * - Supports operations at both ends.
     *
     * ArrayDeque:
     * - Implements Deque.
     * - Supports SequencedCollection.
     * - Supports operations at both ends.
     *
     * LinkedHashSet:
     * - Implements SequencedSet.
     * - Preserves encounter order.
     * - Supports endpoint operations.
     *
     * TreeSet:
     * - Implements NavigableSet and SequencedSet.
     * - Maintains sorted order.
     * - Endpoint repositioning is unsupported.
     *
     * LinkedHashMap:
     * - Implements SequencedMap.
     * - Maintains encounter order.
     * - Supports endpoint mapping operations.
     *
     * TreeMap:
     * - Implements NavigableMap and SequencedMap.
     * - Maintains sorted key order.
     * - Endpoint repositioning is unsupported.
     *
     *
     * ============================================================
     * 21. IMPORTANT DIFFERENCE: REVERSED VIEW VS SORTING
     * ============================================================
     *
     * reversed() does not sort elements.
     *
     * It reverses the encounter order.
     *
     * Example:
     *
     * List<Integer> numbers =
     *     new ArrayList<>(List.of(30, 10, 40, 20));
     *
     * System.out.println(numbers.reversed());
     *
     * Output:
     * [20, 40, 10, 30]
     *
     * Sorting:
     *
     * numbers.sort(null);
     *
     * System.out.println(numbers);
     *
     * Output:
     * [10, 20, 30, 40]
     *
     * Reversing and sorting are different operations.
     *
     *
     * ============================================================
     * 22. JAVA 21 VERSION REQUIREMENT
     * ============================================================
     *
     * Sequenced Collections were introduced in Java 21.
     *
     * Important interfaces:
     *
     * - SequencedCollection
     * - SequencedSet
     * - SequencedMap
     *
     * Important methods:
     *
     * - addFirst()
     * - addLast()
     * - getFirst()
     * - getLast()
     * - removeFirst()
     * - removeLast()
     * - reversed()
     * - putFirst()
     * - putLast()
     * - firstEntry()
     * - lastEntry()
     * - pollFirstEntry()
     * - pollLastEntry()
     * - sequencedKeySet()
     * - sequencedValues()
     * - sequencedEntrySet()
     *
     * Compile and run these examples using JDK 21 or later.
     *
     *
     * ============================================================
     * 23. COMMON MISTAKES
     * ============================================================
     *
     * 1. Thinking reversed() creates a copy.
     *
     *    It returns a reversed-order view.
     *
     * 2. Thinking reversed() sorts elements.
     *
     *    It only reverses encounter order.
     *
     * 3. Assuming every SequencedCollection supports addFirst()
     *    and addLast().
     *
     *    Some implementations, such as TreeSet, reject them.
     *
     * 4. Thinking SequencedSet allows duplicates.
     *
     *    It still follows the Set uniqueness contract.
     *
     * 5. Thinking SequencedMap allows duplicate keys.
     *
     *    Map keys remain unique.
     *
     * 6. Assuming TreeMap can arbitrarily reposition a key
     *    at the beginning or end.
     *
     *    Its comparator determines key order.
     *
     * 7. Confusing encounter order with natural sorting.
     *
     *    Insertion order and sorted order are different concepts.
     *
     * 8. Trying to use SequencedCollection with JDK 17 or earlier.
     *
     *    The interface was introduced in Java 21.
     *
     *
     * ============================================================
     * 24. INTERVIEW REVISION
     * ============================================================
     *
     * Q1. What are Sequenced Collections?
     *
     * Q2. Why were they introduced in Java 21?
     *
     * Q3. What is encounter order?
     *
     * Q4. What is SequencedCollection?
     *
     * Q5. What is SequencedSet?
     *
     * Q6. What is SequencedMap?
     *
     * Q7. Which methods are provided by SequencedCollection?
     *
     * Q8. What does reversed() return?
     *
     * Q9. Does reversed() create a copy?
     *
     * Q10. Does TreeSet support addFirst() and addLast()?
     *
     * Q11. Does LinkedHashSet implement SequencedSet?
     *
     * Q12. Does LinkedHashMap implement SequencedMap?
     *
     * Q13. What is the difference between firstEntry() and
     *      pollFirstEntry()?
     *
     * Q14. What are sequencedKeySet(), sequencedValues() and
     *      sequencedEntrySet()?
     *
     * Q15. What is the difference between reversing and sorting?
     *
     *
     * ============================================================
     * 25. FINAL TAKEAWAY
     * ============================================================
     *
     * SequencedCollection:
     * "A collection with a defined encounter order."
     *
     * SequencedSet:
     * "A Set with a defined encounter order and unique elements."
     *
     * SequencedMap:
     * "A Map with a defined encounter order for its mappings."
     *
     * getFirst() / getLast():
     * "Access the elements at both ends."
     *
     * addFirst() / addLast():
     * "Insert or reposition elements at both ends where supported."
     *
     * reversed():
     * "View the same collection in reverse encounter order."
     *
     * Remember:
     *
     * Java 21 made ordered collection operations more consistent
     * by introducing these three interfaces.
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Sequenced Collections , Happy Learning ..!");
    }
}
