
public class CollectionFactoryMethodsAndViews {

    /*
     * ============================================================
     * 1. WHAT ARE COLLECTION FACTORY METHODS?
     * ============================================================
     *
     * A factory method is a method that creates and returns an
     * object instead of requiring us to call a constructor directly.
     *
     * Java provides factory methods to create collections easily.
     *
     * Examples:
     *
     * List<String> names = List.of("Vinay", "Ravi");
     *
     * Set<Integer> numbers = Set.of(10, 20, 30);
     *
     * Map<Integer, String> students =
     *     Map.of(101, "Vinay", 102, "Ravi");
     *
     * Here:
     * - List.of() creates a List.
     * - Set.of() creates a Set.
     * - Map.of() creates a Map.
     *
     * These are static factory methods.
     *
     * WHY USE THEM?
     *
     * - Less boilerplate code.
     * - Convenient initialization.
     * - Unmodifiable results.
     * - Useful for fixed configuration data.
     *
     *
     * ============================================================
     * 2. LIST.OF(), SET.OF(), MAP.OF()
     * ============================================================
     *
     * Introduced in Java 9.
     *
     * LIST:
     *
     * List<String> languages =
     *     List.of("Java", "Python", "C++");
     *
     * - Preserves encounter order.
     * - Allows duplicate elements.
     * - Does not allow null elements.
     * - Unmodifiable.
     *
     *
     * SET:
     *
     * Set<String> languages =
     *     Set.of("Java", "Python", "C++");
     *
     * - Does not allow duplicate elements.
     * - Does not allow null elements.
     * - Unmodifiable.
     * - Iteration order is unspecified.
     *
     *
     * MAP:
     *
     * Map<Integer, String> students =
     *     Map.of(
     *         101, "Vinay",
     *         102, "Ravi"
     *     );
     *
     * - Keys must be unique.
     * - Null keys and values are not allowed.
     * - Unmodifiable.
     * - Iteration order is unspecified.
     *
     *
     * ============================================================
     * 3. MAP.OFENTRIES()
     * ============================================================
     *
     * Map.of() provides overloads for up to 10 key-value pairs.
     *
     * What if we need more entries?
     *
     * Use Map.ofEntries().
     *
     * Example:
     *
     * Map<Integer, String> students =
     *     Map.ofEntries(
     *         Map.entry(101, "Vinay"),
     *         Map.entry(102, "Ravi"),
     *         Map.entry(103, "Kiran"),
     *         Map.entry(104, "Arun")
     *     );
     *
     * Map.entry(key, value) creates an unmodifiable Map.Entry.
     *
     * Map.ofEntries():
     * - Supports any number of supplied entries.
     * - Rejects null keys and values.
     * - Rejects duplicate keys.
     * - Returns an unmodifiable Map.
     *
     *
     * ============================================================
     * 4. LIST.COPYOF(), SET.COPYOF(), MAP.COPYOF()
     * ============================================================
     *
     * Introduced in Java 10.
     *
     * These methods create unmodifiable copies of supplied
     * collections or Maps.
     *
     * Example:
     *
     * List<String> original = new ArrayList<>();
     * original.add("Java");
     * original.add("Python");
     *
     * List<String> copy = List.copyOf(original);
     *
     * original.add("C++");
     *
     * System.out.println(copy);
     *
     * Output:
     * [Java, Python]
     *
     * The copy does not reflect later structural changes
     * made to the original List.
     *
     * SET.COPYOF():
     *
     * Set<String> original = new HashSet<>();
     * original.add("Java");
     *
     * Set<String> copy = Set.copyOf(original);
     *
     * MAP.COPYOF():
     *
     * Map<Integer, String> original = new HashMap<>();
     * original.put(101, "Vinay");
     *
     * Map<Integer, String> copy = Map.copyOf(original);
     *
     * IMPORTANT:
     *
     * These methods create shallow copies.
     *
     * They copy collection structure, not the objects stored
     * inside the collection.
     *
     *
     * ============================================================
     * 5. ARRAYS.ASLIST()
     * ============================================================
     *
     * Arrays.asList() converts an array into a List backed by
     * that array.
     *
     * Example:
     *
     * String[] array = {"Java", "Python", "C++"};
     *
     * List<String> languages = Arrays.asList(array);
     *
     * System.out.println(languages);
     *
     * Output:
     * [Java, Python, C++]
     *
     * IMPORTANT CHARACTERISTICS:
     *
     * 1. The returned List has fixed size.
     *
     * 2. You cannot add or remove elements.
     *
     * languages.add("JavaScript");
     * // UnsupportedOperationException
     *
     * languages.remove("Java");
     * // UnsupportedOperationException
     *
     * 3. You can replace existing elements using set().
     *
     * languages.set(0, "JavaScript");
     *
     * 4. Changes are reflected in the original array.
     *
     * System.out.println(array[0]);
     *
     * Output:
     * JavaScript
     *
     * 5. Changes in the array are also reflected in the List.
     *
     * array[1] = "C";
     *
     * System.out.println(languages.get(1));
     *
     * Output:
     * C
     *
     * IMPORTANT:
     *
     * Arrays.asList() returns a fixed-size List backed by
     * the supplied array.
     *
     * It is not an ArrayList from java.util.
     *
     *
     * ============================================================
     * 6. ARRAYS.ASLIST() WITH PRIMITIVE ARRAYS
     * ============================================================
     *
     * Be careful when passing primitive arrays.
     *
     * Example:
     *
     * int[] numbers = {10, 20, 30};
     *
     * List<int[]> list = Arrays.asList(numbers);
     *
     * Why is the type List<int[]> and not List<Integer>?
     *
     * Because a primitive array is itself an object.
     *
     * The varargs method receives the entire int[] as one
     * argument.
     *
     * Therefore, the List contains one element: the int[] array.
     *
     * To obtain a List<Integer>, use:
     *
     * List<Integer> list =
     *     Arrays.stream(numbers)
     *           .boxed()
     *           .toList();
     *
     * Stream.toList() requires Java 16 or later and returns
     * an unmodifiable List.
     *
     * For a mutable ArrayList:
     *
     * List<Integer> list =
     *     new ArrayList<>(
     *         Arrays.stream(numbers)
     *               .boxed()
     *               .toList()
     *     );
     *
     *
     * ============================================================
     * 7. COLLECTIONS.EMPTYLIST(), EMPTYSET(), EMPTYMAP()
     * ============================================================
     *
     * These methods return empty unmodifiable collections.
     *
     * Example:
     *
     * List<String> names = Collections.emptyList();
     *
     * Set<Integer> numbers = Collections.emptySet();
     *
     * Map<Integer, String> students = Collections.emptyMap();
     *
     * Useful when a method needs to return an empty collection.
     *
     * Example:
     *
     * public List<String> findStudents() {
     *     return Collections.emptyList();
     * }
     *
     * This is usually better than returning null when the
     * correct result is simply "no elements".
     *
     *
     * ============================================================
     * 8. COLLECTIONS.SINGLETONLIST(), SINGLETON(), SINGLETONMAP()
     * ============================================================
     *
     * These methods create unmodifiable collections containing
     * exactly one element or one mapping.
     *
     * Examples:
     *
     * List<String> names =
     *     Collections.singletonList("Vinay");
     *
     * Set<Integer> numbers =
     *     Collections.singleton(10);
     *
     * Map<Integer, String> students =
     *     Collections.singletonMap(101, "Vinay");
     *
     * They are unmodifiable.
     *
     * Unlike List.of(), Set.of() and Map.of(), these singleton
     * methods permit null elements, keys and values respectively.
     *
     *
     * ============================================================
     * 9. WHAT IS A COLLECTION VIEW?
     * ============================================================
     *
     * A view is an object that provides another way to access
     * an existing collection or part of it.
     *
     * A view usually does not store an independent copy of
     * all the elements.
     *
     * Changes through a modifiable view can affect the backing
     * collection, and changes to the backing collection may be
     * visible through the view.
     *
     * Think of a view like a window into another collection.
     *
     * You are looking at the same underlying data through
     * another reference.
     *
     * Common examples:
     *
     * - List.subList()
     * - Map.keySet()
     * - Map.values()
     * - Map.entrySet()
     * - NavigableSet range views
     * - NavigableMap range views
     * - Collections.unmodifiableList()
     *
     *
     * ============================================================
     * 10. SUBLIST() - A VIEW OF A LIST
     * ============================================================
     *
     * List.subList(fromIndex, toIndex) returns a view of a
     * portion of the original List.
     *
     * The starting index is inclusive.
     *
     * The ending index is exclusive.
     *
     * Example:
     *
     * List<Integer> numbers =
     *     new ArrayList<>(
     *         List.of(10, 20, 30, 40, 50)
     *     );
     *
     * List<Integer> sub =
     *     numbers.subList(1, 4);
     *
     * System.out.println(sub);
     *
     * Output:
     * [20, 30, 40]
     *
     * Index positions:
     *
     * 10 -> index 0
     * 20 -> index 1
     * 30 -> index 2
     * 40 -> index 3
     * 50 -> index 4
     *
     * subList(1, 4):
     * - Includes index 1.
     * - Includes index 2.
     * - Includes index 3.
     * - Excludes index 4.
     *
     *
     * ============================================================
     * 11. MODIFYING A SUBLIST
     * ============================================================
     *
     * Example:
     *
     * List<Integer> numbers =
     *     new ArrayList<>(
     *         List.of(10, 20, 30, 40, 50)
     *     );
     *
     * List<Integer> sub = numbers.subList(1, 4);
     *
     * sub.set(0, 200);
     *
     * System.out.println(numbers);
     *
     * Output:
     * [10, 200, 30, 40, 50]
     *
     * The modification is reflected in the original List.
     *
     * You can also remove elements through the subList:
     *
     * sub.remove(1);
     *
     * System.out.println(numbers);
     *
     * Output:
     * [10, 200, 40, 50]
     *
     * The original List is modified because sub is a view.
     *
     *
     * ============================================================
     * 12. STRUCTURAL MODIFICATION AND SUBLIST
     * ============================================================
     *
     * IMPORTANT:
     *
     * If the original List is structurally modified outside
     * the subList, the behavior of the subList becomes undefined.
     *
     * Example:
     *
     * List<Integer> numbers =
     *     new ArrayList<>(
     *         List.of(10, 20, 30, 40)
     *     );
     *
     * List<Integer> sub = numbers.subList(1, 3);
     *
     * numbers.add(50);
     *
     * System.out.println(sub);
     *
     * Do not rely on this operation.
     *
     * It may result in ConcurrentModificationException.
     *
     * The safe approach is to avoid independently modifying
     * the backing List while using its subList view.
     *
     * If an independent List is required:
     *
     * List<Integer> independent =
     *     new ArrayList<>(numbers.subList(1, 3));
     *
     * This creates a separate mutable List structure.
     *
     *
     * ============================================================
     * 13. MAP.KEYSET() - KEY VIEW
     * ============================================================
     *
     * keySet() returns a Set view of the keys in a Map.
     *
     * Example:
     *
     * Map<Integer, String> students = new HashMap<>();
     *
     * students.put(101, "Vinay");
     * students.put(102, "Ravi");
     *
     * Set<Integer> keys = students.keySet();
     *
     * System.out.println(keys);
     *
     * Output:
     * [101, 102]
     *
     * The returned Set is backed by the Map.
     *
     * Removing a key through the Set removes its mapping
     * from the Map.
     *
     * keys.remove(101);
     *
     * System.out.println(students);
     *
     * Output:
     * {102=Ravi}
     *
     * Adding a key directly through keySet() is not supported.
     *
     * keys.add(103);
     * // UnsupportedOperationException
     *
     *
     * ============================================================
     * 14. MAP.VALUES() - VALUES VIEW
     * ============================================================
     *
     * values() returns a Collection view of the values.
     *
     * Example:
     *
     * Map<Integer, String> students = new HashMap<>();
     *
     * students.put(101, "Vinay");
     * students.put(102, "Ravi");
     *
     * Collection<String> values = students.values();
     *
     * System.out.println(values);
     *
     * Output:
     * [Vinay, Ravi]
     *
     * Removing a value through the view removes a corresponding
     * mapping from the Map.
     *
     * values.remove("Vinay");
     *
     * System.out.println(students);
     *
     * Output:
     * {102=Ravi}
     *
     * Important:
     *
     * Map values are not unique.
     *
     * The values view is a Collection, not a Set.
     *
     *
     * ============================================================
     * 15. MAP.ENTRYSET() - ENTRY VIEW
     * ============================================================
     *
     * entrySet() returns a Set view of the key-value mappings.
     *
     * Example:
     *
     * Map<Integer, String> students = new HashMap<>();
     *
     * students.put(101, "Vinay");
     * students.put(102, "Ravi");
     *
     * Set<Map.Entry<Integer, String>> entries =
     *     students.entrySet();
     *
     * for (Map.Entry<Integer, String> entry : entries) {
     *
     *     System.out.println(
     *         entry.getKey() + " : " + entry.getValue()
     *     );
     * }
     *
     * You can remove a mapping through the entry view.
     *
     * entries.remove(
     *     new AbstractMap.SimpleEntry<>(101, "Vinay")
     * );
     *
     * This removes the matching mapping.
     *
     * Adding entries through the returned Set is generally
     * unsupported.
     *
     *
     * ============================================================
     * 16. WHY DOES MAP PROVIDE KEYSET(), VALUES(), ENTRYSET()?
     * ============================================================
     *
     * Map does not extend Collection.
     *
     * But we often need to work with its keys, values and entries
     * using collection operations.
     *
     * Therefore, Map provides collection views.
     *
     * Example:
     *
     * Set<Integer> keys = students.keySet();
     *
     * Collection<String> values = students.values();
     *
     * Set<Map.Entry<Integer, String>> entries =
     *     students.entrySet();
     *
     * These views allow us to:
     *
     * - Iterate over keys.
     * - Iterate over values.
     * - Iterate over key-value pairs.
     * - Remove mappings through supported view operations.
     *
     *
     * ============================================================
     * 17. SORTEDSET AND NAVIGABLESET RANGE VIEWS
     * ============================================================
     *
     * TreeSet implements NavigableSet.
     *
     * It provides range-view methods:
     *
     * - subSet()
     * - headSet()
     * - tailSet()
     *
     * Example:
     *
     * NavigableSet<Integer> numbers =
     *     new TreeSet<>(
     *         List.of(10, 20, 30, 40, 50, 60)
     *     );
     *
     * NavigableSet<Integer> sub =
     *     numbers.subSet(20, true, 50, false);
     *
     * System.out.println(sub);
     *
     * Output:
     * [20, 30, 40]
     *
     * Parameters:
     *
     * subSet(fromElement, fromInclusive, toElement, toInclusive)
     *
     * - fromElement: starting boundary.
     * - fromInclusive: whether to include the starting element.
     * - toElement: ending boundary.
     * - toInclusive: whether to include the ending element.
     *
     * This is a backed range view.
     *
     * Removing elements through the view affects the original Set.
     *
     *
     * ============================================================
     * 18. HEADSET() AND TAILSET()
     * ============================================================
     *
     * headSet() returns elements before a specified boundary.
     *
     * NavigableSet<Integer> numbers =
     *     new TreeSet<>(
     *         List.of(10, 20, 30, 40, 50)
     *     );
     *
     * System.out.println(numbers.headSet(30, false));
     *
     * Output:
     * [10, 20]
     *
     * tailSet() returns elements from a specified boundary onward.
     *
     * System.out.println(numbers.tailSet(30, true));
     *
     * Output:
     * [30, 40, 50]
     *
     * The boolean controls whether the boundary element is included.
     *
     *
     * ============================================================
     * 19. SORTEDMAP AND NAVIGABLEMAP RANGE VIEWS
     * ============================================================
     *
     * TreeMap provides:
     *
     * - subMap()
     * - headMap()
     * - tailMap()
     *
     * Example:
     *
     * NavigableMap<Integer, String> students = new TreeMap<>();
     *
     * students.put(101, "Vinay");
     * students.put(102, "Ravi");
     * students.put(103, "Kiran");
     * students.put(104, "Arun");
     *
     * NavigableMap<Integer, String> sub =
     *     students.subMap(102, true, 104, false);
     *
     * System.out.println(sub);
     *
     * Output:
     * {102=Ravi, 103=Kiran}
     *
     * This is a backed range view.
     *
     * Removing an entry through the view also removes it
     * from the original Map.
     *
     *
     * ============================================================
     * 20. REVERSED VIEWS
     * ============================================================
     *
     * NavigableSet and NavigableMap provide descending views.
     *
     * Example:
     *
     * NavigableSet<Integer> numbers =
     *     new TreeSet<>(
     *         List.of(10, 20, 30, 40)
     *     );
     *
     * NavigableSet<Integer> descending =
     *     numbers.descendingSet();
     *
     * System.out.println(descending);
     *
     * Output:
     * [40, 30, 20, 10]
     *
     * This is a view, not an independent copy.
     *
     * Similarly:
     *
     * NavigableMap<Integer, String> map = new TreeMap<>();
     *
     * NavigableMap<Integer, String> descendingMap =
     *     map.descendingMap();
     *
     * The returned view presents the mappings in descending
     * key order.
     *
     *
     * ============================================================
     * 21. COLLECTIONS WRAPPER METHODS
     * ============================================================
     *
     * The Collections utility class provides wrapper methods.
     *
     * UNMODIFIABLE WRAPPERS:
     *
     * Collections.unmodifiableCollection()
     * Collections.unmodifiableList()
     * Collections.unmodifiableSet()
     * Collections.unmodifiableMap()
     *
     * SYNCHRONIZED WRAPPERS:
     *
     * Collections.synchronizedCollection()
     * Collections.synchronizedList()
     * Collections.synchronizedSet()
     * Collections.synchronizedMap()
     *
     * CHECKED WRAPPERS:
     *
     * Collections.checkedCollection()
     * Collections.checkedList()
     * Collections.checkedSet()
     * Collections.checkedMap()
     *
     * These wrappers add behavior around an existing collection.
     *
     * They are generally views, not independent copies.
     *
     *
     * ============================================================
     * 22. CHECKED COLLECTIONS
     * ============================================================
     *
     * Collections.checkedList() creates a dynamically
     * type-checked view.
     *
     * Example:
     *
     * List rawList = new ArrayList();
     *
     * List<String> checked =
     *     Collections.checkedList(rawList, String.class);
     *
     * checked.add("Java");
     *
     * checked.add(100);
     * // ClassCastException
     *
     * WHY IS THIS USEFUL?
     *
     * Java generics normally provide compile-time type safety.
     *
     * But raw types and unchecked code can bypass generic checks.
     *
     * Checked wrappers add runtime checks when elements are
     * inserted through the checked view.
     *
     * IMPORTANT:
     *
     * They cannot prevent incompatible insertion through
     * another reference to the backing raw collection.
     *
     * Use generics normally; checked wrappers are mainly useful
     * when working with legacy or raw-type APIs.
     *
     *
     * ============================================================
     * 23. COLLECTIONS.SYNCHRONIZED WRAPPERS
     * ============================================================
     *
     * Example:
     *
     * List<String> original = new ArrayList<>();
     *
     * List<String> synchronizedList =
     *     Collections.synchronizedList(original);
     *
     * Individual operations are synchronized through the wrapper.
     *
     * IMPORTANT:
     *
     * Iteration requires external synchronization:
     *
     * synchronized (synchronizedList) {
     *     for (String value : synchronizedList) {
     *         System.out.println(value);
     *     }
     * }
     *
     * The wrapper does not automatically make every compound
     * operation atomic.
     *
     * For deeper concurrency concepts, refer to:
     * Concurrent and Thread-Safe Collections.
     *
     *
     * ============================================================
     * 24. VIEW VS COPY - COMPLETE COMPARISON
     * ============================================================
     *
     * VIEW:
     *
     * - Another access path to existing data.
     * - Often backed by the original collection.
     * - Changes may be reflected in both directions.
     * - Does not necessarily allocate a full independent copy.
     *
     * Examples:
     * - List.subList()
     * - Map.keySet()
     * - Map.values()
     * - Map.entrySet()
     * - TreeSet.subSet()
     * - TreeMap.subMap()
     * - Collections.unmodifiableList()
     *
     *
     * COPY:
     *
     * - Separate collection structure.
     * - Later structural changes to the source are not reflected.
     * - Usually requires additional memory.
     * - May be mutable or unmodifiable depending on how created.
     *
     * Examples:
     *
     * List<String> mutableCopy =
     *     new ArrayList<>(original);
     *
     * List<String> unmodifiableCopy =
     *     List.copyOf(original);
     *
     * IMPORTANT:
     *
     * Both examples create shallow copies.
     *
     *
     * ============================================================
     * 25. COMMON MISTAKES
     * ============================================================
     *
     * 1. Thinking Arrays.asList() returns a resizable ArrayList.
     *
     *    It returns a fixed-size array-backed List.
     *
     * 2. Passing a primitive array to Arrays.asList() and
     *    expecting a List of wrapper objects.
     *
     * 3. Thinking subList() creates an independent List.
     *
     *    It returns a view.
     *
     * 4. Modifying the backing List independently while using
     *    a subList view.
     *
     * 5. Thinking keySet() returns an independent Set.
     *
     *    It is backed by the Map.
     *
     * 6. Thinking values() returns a Set.
     *
     *    It returns a Collection because duplicate values
     *    are allowed.
     *
     * 7. Thinking Map.copyOf() creates a deep copy.
     *
     * 8. Assuming all factory methods allow null.
     *
     * 9. Assuming all collection views support add().
     *
     *    Many views do not support adding elements.
     *
     *
     * ============================================================
     * 26. JAVA VERSION REFERENCE
     * ============================================================
     *
     * Java 1.2:
     * - Collections.emptyList()
     * - Collections.singletonList()
     * - Collections.unmodifiableList()
     * - Collections.synchronizedList()
     * - Collections.checkedList()
     *
     * Java 5:
     * - Generic collection APIs.
     *
     * Java 9:
     * - List.of()
     * - Set.of()
     * - Map.of()
     * - Map.ofEntries()
     * - Map.entry()
     *
     * Java 10:
     * - List.copyOf()
     * - Set.copyOf()
     * - Map.copyOf()
     *
     * Java 16:
     * - Stream.toList()
     *
     *
     * ============================================================
     * 27. INTERVIEW REVISION
     * ============================================================
     *
     * Q1. What are static collection factory methods?
     *
     * Q2. What is the difference between List.of() and List.copyOf()?
     *
     * Q3. What does Arrays.asList() return?
     *
     * Q4. Why can't we add or remove elements from its result?
     *
     * Q5. What happens when a primitive array is passed to
     *     Arrays.asList()?
     *
     * Q6. What is a backed view?
     *
     * Q7. What does List.subList() return?
     *
     * Q8. What is the difference between a view and a copy?
     *
     * Q9. What are keySet(), values() and entrySet()?
     *
     * Q10. How can removing an element from keySet() affect a Map?
     *
     * Q11. Why does Map.values() return a Collection instead of Set?
     *
     * Q12. What are NavigableSet range views?
     *
     * Q13. What is the purpose of Collections.checkedList()?
     *
     * Q14. What is the difference between synchronized and
     *      unmodifiable wrappers?
     *
     *
     * FINAL TAKEAWAY:
     *
     * Factory method:
     * "A convenient method that creates an object."
     *
     * View:
     * "Another window into existing collection data."
     *
     * Copy:
     * "A separate collection structure."
     *
     * Backed view:
     * "Changes can be reflected in the original collection."
     *
     * Arrays.asList():
     * "Fixed-size List backed by an array."
     *
     * Map views:
     * "Access keys, values and entries through collection APIs."
     *
     * Understand these concepts and you will avoid many
     * real-world Java Collections mistakes.
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Collection Factory Methods and Views , Happy Learning ..!");
    }
}
