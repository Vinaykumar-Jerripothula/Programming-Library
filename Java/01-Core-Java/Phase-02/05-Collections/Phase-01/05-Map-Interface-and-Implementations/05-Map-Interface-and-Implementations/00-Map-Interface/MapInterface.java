
public class MapInterface {

    /*
     * ============================================================
     *                       MAP INTERFACE
     * ============================================================
     *
     * 1. WHAT IS A MAP?
     *
     * Imagine a college maintaining student records.
     *
     * Each student has a unique roll number.
     * Using that roll number, we can retrieve the student's details.
     *
     * Example:
     *
     * Roll Number    Student Name
     *    101         Vinay
     *    102         Rahul
     *    103         Kiran
     *
     * Instead of searching through every student record, we can
     * directly use the roll number to retrieve the corresponding
     * student name.
     *
     * This is the basic idea behind a Map.
     *
     * A Map stores data as KEY-VALUE pairs.
     *
     * Key   -> Identifies an entry.
     * Value -> Stores the information associated with that key.
     *
     * Example:
     *
     * 101 -> Vinay
     * 102 -> Rahul
     * 103 -> Kiran
     *
     * In Java:
     *
     * Map<Integer, String> students = new HashMap<>();
     *
     *
     * 2. WHY DO WE NEED A MAP?
     *
     * Consider storing student records using an ArrayList.
     *
     * ArrayList:
     *
     * [Vinay, Rahul, Kiran]
     *
     * To find Rahul, we may need to search through the list.
     *
     * But what if we want to retrieve information using a
     * unique identifier such as an employee ID or email?
     *
     * A Map provides a direct key-based lookup abstraction.
     *
     * Common use cases:
     *
     * - Student ID -> Student details
     * - Employee ID -> Employee information
     * - Username -> User object
     * - Product ID -> Product details
     * - Country code -> Country name
     * - Word -> Frequency count
     * - Configuration key -> Configuration value
     * - Cache key -> Cached result
     *
     * Note:
     * A Map provides key-based lookup, but its actual performance
     * depends on the implementation.
     *
     *
     * 3. MAP IS AN INTERFACE
     *
     * Map is an interface available in java.util.
     *
     * It is part of the Java Collections Framework.
     *
     * However, Map does NOT extend the Collection interface.
     *
     * Why?
     *
     * Collection represents a group of individual elements.
     *
     * Map represents associations between keys and values.
     *
     * Their APIs and purposes are different.
     *
     * Simplified hierarchy:
     *
     *              Iterable
     *                 |
     *             Collection
     *             /    |    \
     *           List  Set  Queue
     *
     *              Map
     *               |
     *        Map Implementations
     *
     * Important:
     * Map is part of the Collections Framework, but it is not
     * a subtype of Collection.
     *
     *
     * 4. CREATING A MAP
     *
     * Import:
     *
     * import java.util.Map;
     * import java.util.HashMap;
     *
     * Declaration:
     *
     * Map<Integer, String> students = new HashMap<>();
     *
     * Syntax:
     *
     * Map<KeyType, ValueType> reference =
     *     new Implementation<>();
     *
     * Example:
     *
     * Map<Integer, String> students = new HashMap<>();
     *
     * students.put(101, "Vinay");
     * students.put(102, "Rahul");
     * students.put(103, "Kiran");
     *
     * Each entry contains one key and its associated value.
     *
     *
     * 5. KEY AND VALUE RULES
     *
     * A. KEYS MUST BE UNIQUE
     *
     * A Map cannot contain duplicate keys.
     *
     * Example:
     *
     * Map<Integer, String> students = new HashMap<>();
     *
     * students.put(101, "Vinay");
     * students.put(102, "Rahul");
     * students.put(101, "Kiran");
     *
     * Result:
     *
     * 101 -> Kiran
     * 102 -> Rahul
     *
     * Why?
     *
     * When a key already exists, put() replaces its old value.
     *
     * The key 101 still represents only one mapping.
     *
     * B. VALUES CAN BE DUPLICATED
     *
     * Example:
     *
     * students.put(101, "Vinay");
     * students.put(102, "Vinay");
     *
     * Both entries are valid.
     *
     * 101 -> Vinay
     * 102 -> Vinay
     *
     * C. NULL KEYS AND VALUES
     *
     * Null support depends on the implementation.
     *
     * Examples:
     *
     * HashMap:
     * - Allows one null key.
     * - Allows multiple null values.
     *
     * TreeMap:
     * - Natural ordering does not permit a null key.
     * - Null values are permitted.
     * - A custom comparator may support null keys if designed
     *   to handle them.
     *
     * ConcurrentHashMap:
     * - Does not permit null keys or null values.
     *
     * We will study these differences in their respective files.
     *
     *
     * 6. IMPORTANT MAP METHODS
     *
     * A. INSERTION AND UPDATING
     *
     * put(key, value)
     *
     * - Inserts a new key-value mapping.
     * - If the key already exists, replaces its value.
     * - Returns the previous value, or null if there was no
     *   previous mapping.
     *
     * Example:
     *
     * map.put(101, "Vinay");
     * map.put(101, "Rahul");
     *
     * Final mapping:
     *
     * 101 -> Rahul
     *
     * putIfAbsent(key, value)
     *
     * - Inserts the mapping only if the key is absent or
     *   currently mapped to null.
     * - Otherwise, leaves the existing non-null value unchanged.
     *
     * Example:
     *
     * map.put(101, "Vinay");
     * map.putIfAbsent(101, "Rahul");
     *
     * Result:
     *
     * 101 -> Vinay
     *
     * putAll(map)
     *
     * - Copies all mappings from another Map into this Map.
     *
     *
     * B. RETRIEVAL
     *
     * get(key)
     *
     * - Returns the value associated with the key.
     * - Returns null if the key is not mapped.
     *
     * Example:
     *
     * String name = map.get(101);
     *
     * getOrDefault(key, defaultValue)
     *
     * - Returns the mapped value if the key exists.
     * - Otherwise, returns the supplied default value.
     *
     * Example:
     *
     * String name = map.getOrDefault(105, "Unknown");
     *
     *
     * C. REMOVAL
     *
     * remove(key)
     *
     * - Removes the mapping for the specified key.
     * - Returns its previous value, or null if there was no mapping.
     *
     * remove(key, value)
     *
     * - Removes the mapping only if both key and value match.
     * - Returns true if removal succeeds.
     *
     *
     * D. SEARCHING
     *
     * containsKey(key)
     *
     * - Checks whether the key exists.
     *
     * containsValue(value)
     *
     * - Checks whether at least one mapping has the given value.
     *
     * Example:
     *
     * map.containsKey(101);
     * map.containsValue("Vinay");
     *
     *
     * E. SIZE AND STATUS
     *
     * size()
     *
     * - Returns the number of mappings.
     *
     * isEmpty()
     *
     * - Checks whether the Map contains no mappings.
     *
     * clear()
     *
     * - Removes all mappings.
     *
     *
     * 7. COMPLETE MAP EXAMPLE
     *
     * import java.util.Map;
     * import java.util.HashMap;
     *
     * public class MapExample {
     *
     *     public static void main(String[] args) {
     *
     *         Map<Integer, String> students = new HashMap<>();
     *
     *         // Insert
     *         students.put(101, "Vinay");
     *         students.put(102, "Rahul");
     *         students.put(103, "Kiran");
     *
     *         // Retrieve
     *         System.out.println(students.get(101));
     *
     *         // Update
     *         students.put(102, "Arjun");
     *
     *         // Search
     *         System.out.println(students.containsKey(103));
     *
     *         // Default value
     *         System.out.println(
     *             students.getOrDefault(104, "Unknown")
     *         );
     *
     *         // Remove
     *         students.remove(103);
     *
     *         // Size
     *         System.out.println(students.size());
     *
     *         // Display
     *         System.out.println(students);
     *     }
     * }
     *
     *
     * 8. HOW TO ITERATE OVER A MAP?
     *
     * Unlike List, Map does not directly implement Iterable.
     *
     * Therefore, we cannot directly write:
     *
     * for (var entry : map) { }
     *
     * Instead, Map provides three important views:
     *
     * 1. keySet()
     * 2. values()
     * 3. entrySet()
     *
     *
     * A. ITERATING USING keySet()
     *
     * keySet() returns a Set containing all keys.
     *
     * Example:
     *
     * for (Integer key : map.keySet()) {
     *     System.out.println(key);
     * }
     *
     *
     * B. ITERATING USING values()
     *
     * values() returns a Collection containing all values.
     *
     * Example:
     *
     * for (String value : map.values()) {
     *     System.out.println(value);
     * }
     *
     *
     * C. ITERATING USING entrySet()
     *
     * entrySet() returns a Set of Map.Entry objects.
     *
     * Each Map.Entry represents one key-value mapping.
     *
     * Example:
     *
     * for (Map.Entry<Integer, String> entry : map.entrySet()) {
     *
     *     System.out.println(entry.getKey());
     *     System.out.println(entry.getValue());
     * }
     *
     * This is generally the preferred way to iterate when
     * both keys and values are required.
     *
     *
     * 9. UNDERSTANDING MAP.ENTRY
     *
     * Map.Entry represents a single key-value mapping.
     *
     * Example:
     *
     * 101 -> Vinay
     *
     * Here:
     *
     * Key   = 101
     * Value = Vinay
     *
     * Important methods:
     *
     * getKey()
     * - Returns the key.
     *
     * getValue()
     * - Returns the value.
     *
     * setValue(value)
     * - Replaces the value of the entry, when supported.
     *
     * equals(object)
     * - Compares an entry with another object.
     *
     * hashCode()
     * - Returns the entry's hash code.
     *
     * Map.Entry is a nested interface inside Map.
     *
     *
     * 10. THREE IMPORTANT MAP VIEWS
     *
     * A. keySet()
     *
     * Returns a Set view of the keys.
     *
     * B. values()
     *
     * Returns a Collection view of the values.
     *
     * C. entrySet()
     *
     * Returns a Set view of key-value mappings.
     *
     * Important:
     *
     * These are backed views, not independent copies.
     *
     * Changes made through a supported view operation are
     * reflected in the original Map.
     *
     * Example:
     *
     * Map<Integer, String> map = new HashMap<>();
     *
     * map.put(1, "A");
     * map.put(2, "B");
     *
     * map.keySet().remove(1);
     *
     * System.out.println(map);
     *
     * Output:
     *
     * {2=B}
     *
     * The mapping with key 1 is removed from the original Map.
     *
     * However, the views have implementation-specific iterator
     * behavior and modification restrictions. Do not assume that
     * every modification is safe during iteration.
     *
     *
     * 11. DEFAULT METHODS IN MAP
     *
     * Java 8 introduced several useful default methods in Map.
     *
     * forEach(action)
     *
     * Performs an action for each mapping.
     *
     * Example:
     *
     * map.forEach((key, value) ->
     *     System.out.println(key + " = " + value)
     * );
     *
     * replace(key, value)
     *
     * Replaces the value only if the key exists.
     *
     * replace(key, oldValue, newValue)
     *
     * Replaces only if the current value matches oldValue.
     *
     * replaceAll(function)
     *
     * Replaces every value using the supplied function.
     *
     * compute(key, function)
     *
     * Computes a new value for a key.
     *
     * computeIfAbsent(key, function)
     *
     * Computes a value only if the key is absent or mapped
     * to null.
     *
     * computeIfPresent(key, function)
     *
     * Computes a new value only if the key is present and
     * currently mapped to a non-null value.
     *
     * merge(key, value, function)
     *
     * Combines a supplied value with an existing non-null value,
     * or associates the supplied value when no non-null mapping
     * exists.
     *
     * These methods are especially useful for counters,
     * grouping and updating Map values.
     *
     * Important:
     * Their concurrency guarantees depend on the implementation.
     * Do not assume that default Map methods are atomic.
     *
     *
     * 12. PRACTICAL EXAMPLE: FREQUENCY COUNTING
     *
     * A Map is commonly used to count how many times each
     * element appears.
     *
     * Example:
     *
     * int[] numbers = {10, 20, 10, 30, 20, 10};
     *
     * Map<Integer, Integer> frequency = new HashMap<>();
     *
     * for (int number : numbers) {
     *
     *     frequency.put(
     *         number,
     *         frequency.getOrDefault(number, 0) + 1
     *     );
     * }
     *
     * System.out.println(frequency);
     *
     * Result:
     *
     * 10 -> 3
     * 20 -> 2
     * 30 -> 1
     *
     * A shorter Java 8+ approach:
     *
     * for (int number : numbers) {
     *
     *     frequency.merge(number, 1, Integer::sum);
     * }
     *
     * This is a common DSA and interview pattern.
     *
     *
     * 13. MAP AND GENERICS
     *
     * Map uses two generic type parameters:
     *
     * Map<K, V>
     *
     * K -> Key type.
     * V -> Value type.
     *
     * Examples:
     *
     * Map<Integer, String> students;
     *
     * Map<String, Integer> marks;
     *
     * Map<String, List<String>> departments;
     *
     * Map<Integer, Student> studentRecords;
     *
     * Primitive types cannot be used directly as generic
     * type arguments.
     *
     * Incorrect:
     *
     * Map<int, String> map;
     *
     * Correct:
     *
     * Map<Integer, String> map;
     *
     *
     * 14. MAP VS COLLECTION
     *
     * Collection:
     *
     * - Represents a group of individual elements.
     * - List, Set and Queue extend Collection.
     * - Provides methods such as add(), remove() and iterator().
     *
     * Map:
     *
     * - Represents key-value associations.
     * - Does not extend Collection.
     * - Provides methods such as put(), get() and entrySet().
     *
     *
     * 15. MAP VS SET
     *
     * Set:
     *
     * - Stores unique individual elements.
     * - Does not store key-value pairs.
     *
     * Map:
     *
     * - Stores key-value mappings.
     * - Keys are unique.
     * - Values can be duplicated.
     *
     * A Map's keySet() returns a Set because keys are unique.
     *
     *
     * 16. MAP IMPLEMENTATIONS
     *
     * Important implementations:
     *
     * 1. HashMap
     *    - Hash-table-based Map.
     *    - Does not guarantee iteration order.
     *
     * 2. LinkedHashMap
     *    - Maintains a defined encounter order.
     *    - Can use insertion order or access order.
     *
     * 3. TreeMap
     *    - Maintains keys in sorted order.
     *    - Uses a Red-Black Tree.
     *
     * 4. Hashtable
     *    - Legacy synchronized Map implementation.
     *    - Does not permit null keys or values.
     *
     * 5. EnumMap
     *    - Specialized Map for enum keys.
     *
     * 6. WeakHashMap
     *    - Uses weak references for keys.
     *
     * 7. IdentityHashMap
     *    - Compares keys using reference identity (==).
     *
     * 8. Properties
     *    - Specialized configuration-oriented class.
     *    - Extends Hashtable.
     *
     * 9. ConcurrentHashMap
     *    - Concurrent Map implementation.
     *
     * 10. ConcurrentSkipListMap
     *     - Concurrent sorted Map implementation.
     *
     *
     * 17. IMPORTANT MAP CHARACTERISTICS
     *
     * - Keys must be unique.
     * - Values can be duplicated.
     * - Map stores key-value mappings.
     * - Map does not extend Collection.
     * - Ordering depends on the implementation.
     * - Null support depends on the implementation.
     * - Thread safety depends on the implementation.
     * - keySet(), values() and entrySet() provide backed views.
     *
     *
     * 18. COMMON MISTAKES
     *
     * 1. Assuming Map extends Collection.
     *    It does not.
     *
     * 2. Assuming values must be unique.
     *    Duplicate values are allowed.
     *
     * 3. Assuming inserting an existing key creates another
     *    mapping.
     *    It replaces the old value.
     *
     * 4. Assuming every Map maintains insertion order.
     *    Ordering depends on the implementation.
     *
     * 5. Assuming every Map permits null.
     *    Null support depends on the implementation.
     *
     * 6. Assuming keySet() returns an independent copy.
     *    It returns a backed view.
     *
     * 7. Assuming get(key) returning null always means the key
     *    does not exist.
     *    Some Maps permit a key mapped to null.
     *    Use containsKey() when this distinction matters.
     *
     * 8. Assuming Map is automatically thread-safe.
     *    It is not.
     *
     *
     * 19. INTERVIEW QUESTIONS
     *
     * 1. What is a Map in Java?
     * 2. Does Map extend Collection?
     * 3. What is a key-value pair?
     * 4. Can Map contain duplicate keys?
     * 5. Can Map contain duplicate values?
     * 6. What happens when an existing key is inserted again?
     * 7. What is the difference between put() and putIfAbsent()?
     * 8. What is the difference between get() and getOrDefault()?
     * 9. What is Map.Entry?
     * 10. What is the difference between keySet(), values()
     *     and entrySet()?
     * 11. Why is entrySet() useful for iteration?
     * 12. Are Map views backed by the original Map?
     * 13. What is the difference between Map and Set?
     * 14. What are the important Map implementations?
     * 15. What is the purpose of computeIfAbsent()?
     * 16. What is the purpose of merge()?
     * 17. What is the difference between HashMap and TreeMap?
     * 18. Is Map thread-safe?
     * 19. Can get() return null for an existing key?
     * 20. What is the difference between Map and Collection?
     *
     *
     * 20. QUICK REVISION
     *
     * - Map stores key-value pairs.
     * - Map is an interface in java.util.
     * - Map does not extend Collection.
     * - Keys are unique.
     * - Values can be duplicated.
     * - put() inserts or updates a mapping.
     * - get() retrieves a value using its key.
     * - remove() removes a mapping.
     * - containsKey() checks for a key.
     * - containsValue() checks for a value.
     * - keySet() returns a Set view of keys.
     * - values() returns a Collection view of values.
     * - entrySet() returns a Set view of entries.
     * - Map.Entry represents one key-value mapping.
     * - Ordering, null support and thread safety depend on
     *   the implementation.
     *
     * ============================================================
     *                     END OF MAP INTERFACE
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Map Interface , Happy Learning ..!");
    }
}
