
public class HashMap {

    /*
     * ============================================================
     *                       HASHMAP IN JAVA
     * ============================================================
     *
     * 1. WHAT IS HASHMAP?
     *
     * HashMap is a class in java.util that implements the Map
     * interface.
     *
     * It stores data in KEY-VALUE pairs.
     *
     * Example:
     *
     * 101 -> Vinay
     * 102 -> Rahul
     * 103 -> Kiran
     *
     * We can retrieve a value using its key.
     *
     * Map<Integer, String> students = new HashMap<>();
     *
     * students.put(101, "Vinay");
     * students.put(102, "Rahul");
     *
     * System.out.println(students.get(101));
     *
     * Output:
     * Vinay
     *
     * The main advantage of HashMap is efficient key-based
     * insertion, retrieval and removal.
     *
     *
     * 2. IMPORTANT CHARACTERISTICS
     *
     * - Implements Map.
     * - Stores key-value pairs.
     * - Does not allow duplicate keys.
     * - Allows duplicate values.
     * - Allows one null key.
     * - Allows multiple null values.
     * - Does not guarantee iteration order.
     * - Is not synchronized.
     * - Allows mutable values.
     *
     * Package:
     *
     * java.util
     *
     *
     * 3. CREATING A HASHMAP
     *
     * Default constructor:
     *
     * HashMap<Integer, String> map = new HashMap<>();
     *
     * With initial capacity:
     *
     * HashMap<Integer, String> map = new HashMap<>(32);
     *
     * With initial capacity and load factor:
     *
     * HashMap<Integer, String> map = new HashMap<>(32, 0.75f);
     *
     * From another Map:
     *
     * HashMap<Integer, String> map = new HashMap<>(anotherMap);
     *
     * Prefer programming to the interface:
     *
     * Map<Integer, String> map = new HashMap<>();
     *
     *
     * 4. BASIC OPERATIONS
     *
     * Map<Integer, String> map = new HashMap<>();
     *
     * // Insert
     * map.put(101, "Vinay");
     * map.put(102, "Rahul");
     *
     * // Retrieve
     * System.out.println(map.get(101));
     *
     * // Update
     * map.put(101, "Arjun");
     *
     * // Check key
     * System.out.println(map.containsKey(102));
     *
     * // Check value
     * System.out.println(map.containsValue("Rahul"));
     *
     * // Remove
     * map.remove(102);
     *
     * // Size
     * System.out.println(map.size());
     *
     * // Clear
     * map.clear();
     *
     *
     * 5. HOW DOES HASHMAP WORK INTERNALLY?
     *
     * This is the most important part.
     *
     * HashMap uses a HASH TABLE internally.
     *
     * A hash table is a data structure that uses a hash function
     * to determine where an entry should be stored.
     *
     * Think of a college library with many numbered shelves.
     *
     * Instead of checking every shelf, a system calculates
     * which shelf should contain a particular book.
     *
     * HashMap works using a similar idea.
     *
     * Key
     *  |
     *  v
     * hashCode()
     *  |
     *  v
     * Hash spreading
     *  |
     *  v
     * Bucket index calculation
     *  |
     *  v
     * Store or retrieve the entry
     *
     *
     * 6. WHAT IS HASHING?
     *
     * Hashing is the process of converting a key into a
     * numerical hash value using a hash function.
     *
     * In Java, objects can provide a hash code through:
     *
     * hashCode()
     *
     * Example:
     *
     * String key = "Java";
     *
     * int hash = key.hashCode();
     *
     * System.out.println(hash);
     *
     * Every object inherits hashCode() from Object unless
     * its class overrides the method.
     *
     * Important:
     *
     * A hash code is an integer.
     * It is not the bucket index itself.
     *
     *
     * 7. WHAT IS hashCode()?
     *
     * hashCode() is a method defined in the Object class.
     *
     * It returns an int representing the object's hash code.
     *
     * Example:
     *
     * String name = "Vinay";
     *
     * System.out.println(name.hashCode());
     *
     * Different objects can produce the same hash code.
     *
     * This is called a hash collision.
     *
     * The hashCode() contract:
     *
     * 1. If two objects are equal according to equals(),
     *    they must have the same hash code.
     *
     * 2. If two objects have the same hash code, they are
     *    not necessarily equal.
     *
     * 3. The hash code should remain consistent while information
     *    used in equals() remains unchanged.
     *
     *
     * 8. WHAT IS equals()?
     *
     * equals() checks logical equality between objects.
     *
     * Example:
     *
     * String a = new String("Java");
     * String b = new String("Java");
     *
     * System.out.println(a == b);
     * System.out.println(a.equals(b));
     *
     * Output:
     *
     * false
     * true
     *
     * == checks reference identity for objects.
     * equals() checks logical equality when properly overridden.
     *
     * HashMap uses hashCode() and equals() together.
     *
     * hashCode():
     * Helps locate the bucket.
     *
     * equals():
     * Helps identify the correct key among candidates.
     *
     *
     * 9. HASH SPREADING IN HASHMAP
     *
     * In modern OpenJDK implementations, HashMap spreads
     * the hash bits before calculating the bucket index.
     *
     * Conceptually, the implementation uses:
     *
     * h = key.hashCode()
     *
     * hash = h ^ (h >>> 16)
     *
     * Here:
     *
     * ^   -> Bitwise XOR
     * >>> -> Unsigned right shift
     *
     * Why?
     *
     * It mixes some higher-order bits into lower-order bits.
     *
     * This helps distribute keys across buckets when the
     * bucket index uses only lower-order bits.
     *
     * Important:
     * This is an implementation detail of modern OpenJDK
     * HashMap, not a general requirement of the Map interface.
     *
     *
     * 10. HOW IS THE BUCKET INDEX CALCULATED?
     *
     * In modern HashMap implementations, the bucket index is
     * calculated using:
     *
     * index = (capacity - 1) & hash
     *
     * Here:
     *
     * capacity = current table length.
     * hash     = spread hash value.
     *
     * Example:
     *
     * capacity = 16
     *
     * capacity - 1 = 15
     *
     * Binary:
     *
     * 15 = 00001111
     *
     * Suppose:
     *
     * hash = 00010110
     *
     * AND:
     *
     * 00001111
     * 00010110
     * --------
     * 00000110
     *
     * Bucket index = 6
     *
     * Important:
     *
     * This calculation works because HashMap uses a power-of-two
     * table capacity.
     *
     *
     * 11. WHAT IS A BUCKET?
     *
     * A bucket is a position in the internal hash table.
     *
     * Imagine an array:
     *
     * Index     Bucket
     *
     *   0       [ ]
     *   1       [ ]
     *   2       [ ]
     *   3       [ ]
     *   4       [ ]
     *   5       [ ]
     *   6       [Entry]
     *   7       [ ]
     *   8       [ ]
     *
     * Every bucket can hold:
     *
     * - No entry.
     * - One entry.
     * - Multiple entries when collisions occur.
     *
     *
     * 12. WHAT IS A HASH COLLISION?
     *
     * A hash collision occurs when different keys are assigned
     * to the same bucket.
     *
     * Example:
     *
     * Key A -> Bucket 5
     * Key B -> Bucket 5
     *
     * Both keys need to be stored in the same bucket.
     *
     * A collision does not mean that the keys are equal.
     *
     * Different keys can have:
     *
     * - The same hash code.
     * - Different hash codes that map to the same bucket.
     *
     *
     * 13. HOW DOES HASHMAP HANDLE COLLISIONS?
     *
     * HashMap uses separate chaining.
     *
     * Traditionally, colliding entries in a bucket are
     * connected using a linked list.
     *
     * Example:
     *
     * Bucket 5
     *    |
     *    v
     *  [A] -> [B] -> [C] -> null
     *
     * In modern Java implementations, a heavily populated
     * bucket can be converted into a balanced Red-Black Tree.
     *
     * Conceptually:
     *
     * Small collision group -> Linked list
     * Large collision group -> Red-Black Tree (when conditions
     *                         for treeification are satisfied)
     *
     *
     * 14. TREEIFICATION
     *
     * Treeification means converting a bucket's linked-list
     * structure into a Red-Black Tree.
     *
     * Important OpenJDK HashMap constants:
     *
     * TREEIFY_THRESHOLD = 8
     * UNTREEIFY_THRESHOLD = 6
     * MIN_TREEIFY_CAPACITY = 64
     *
     * These are implementation details, not public API guarantees.
     *
     * TREEIFY_THRESHOLD:
     *
     * When a bucket becomes sufficiently populated, HashMap
     * may convert it into a tree.
     *
     * MIN_TREEIFY_CAPACITY:
     *
     * If the table is too small, HashMap generally resizes
     * instead of treeifying the bucket.
     *
     * UNTREEIFY_THRESHOLD:
     *
     * During resizing, a sufficiently small tree bin may
     * be converted back into a linked list.
     *
     * Why use a Red-Black Tree?
     *
     * Searching a long linked list can take O(n).
     *
     * A balanced tree can provide O(log n) search behavior
     * under suitable comparison conditions.
     *
     * Do not assume every collision bucket immediately becomes
     * a tree when it contains eight entries.
     *
     *
     * 15. HOW DOES put() WORK?
     *
     * Consider:
     *
     * map.put("Java", 100);
     *
     * Conceptual steps:
     *
     * Step 1:
     * Calculate the key's hash code.
     *
     * Step 2:
     * Spread the hash bits.
     *
     * Step 3:
     * Calculate the bucket index.
     *
     * Step 4:
     * Check the selected bucket.
     *
     * Step 5:
     * If the bucket is empty, insert the new entry.
     *
     * Step 6:
     * If entries already exist, compare candidate keys.
     *
     * Step 7:
     * If an equal key exists, replace its value.
     *
     * Step 8:
     * Otherwise, add a new entry to the bucket.
     *
     * Step 9:
     * Check whether resizing is required.
     *
     *
     * 16. HOW DOES get() WORK?
     *
     * Consider:
     *
     * map.get("Java");
     *
     * Conceptual steps:
     *
     * Step 1:
     * Calculate the key's hash.
     *
     * Step 2:
     * Calculate its bucket index.
     *
     * Step 3:
     * Visit the selected bucket.
     *
     * Step 4:
     * Compare hashes and keys.
     *
     * Step 5:
     * Return the matching value.
     *
     * If no matching key exists, return null.
     *
     * Important:
     * HashMap may use both hash comparison and equals()
     * comparison to locate a matching key.
     *
     *
     * 17. WHY ARE BOTH hashCode() AND equals() REQUIRED?
     *
     * Imagine two different Student objects:
     *
     * Student A:
     * id = 101
     * name = "Vinay"
     *
     * Student B:
     * id = 101
     * name = "Vinay"
     *
     * If they represent the same logical student, we may want
     * them to be treated as equal Map keys.
     *
     * Therefore, we should override both methods consistently.
     *
     * Example:
     *
     * class Student {
     *
     *     int id;
     *     String name;
     *
     *     Student(int id, String name) {
     *         this.id = id;
     *         this.name = name;
     *     }
     *
     *     @Override
     *     public boolean equals(Object obj) {
     *
     *         if (this == obj) return true;
     *
     *         if (!(obj instanceof Student)) return false;
     *
     *         Student other = (Student) obj;
     *
     *         return this.id == other.id
     *             && Objects.equals(this.name, other.name);
     *     }
     *
     *     @Override
     *     public int hashCode() {
     *         return Objects.hash(id, name);
     *     }
     * }
     *
     * Import:
     *
     * import java.util.Objects;
     *
     * Now two Student objects with the same id and name
     * have equal logical identity under this definition.
     *
     * Important:
     * The fields used by equals() and hashCode() should not
     * change while the object is being used as a HashMap key.
     *
     *
     * 18. WHAT HAPPENS IF WE OVERRIDE ONLY equals()?
     *
     * Suppose two logically equal objects return different
     * hash codes.
     *
     * HashMap may place them in different buckets.
     *
     * It may then fail to find an existing equal key when
     * inserting or retrieving.
     *
     * Therefore:
     *
     * If equals() is overridden, hashCode() must also be
     * overridden consistently.
     *
     *
     * 19. WHAT IS INITIAL CAPACITY?
     *
     * Initial capacity represents the initial table capacity
     * requested for a HashMap.
     *
     * Example:
     *
     * HashMap<Integer, String> map = new HashMap<>(32);
     *
     * In modern OpenJDK implementations, the internal table
     * is allocated lazily in many cases.
     *
     * This means creating a HashMap does not necessarily
     * immediately allocate its bucket array.
     *
     *
     * 20. WHAT IS LOAD FACTOR?
     *
     * Load factor controls how full the hash table can become
     * before resizing is triggered.
     *
     * Default load factor:
     *
     * 0.75
     *
     * Example:
     *
     * Initial capacity = 16
     * Load factor = 0.75
     *
     * Threshold:
     *
     * 16 * 0.75 = 12
     *
     * When the number of mappings exceeds the threshold,
     * HashMap resizes its table.
     *
     * Constructor:
     *
     * HashMap<Integer, String> map =
     *     new HashMap<>(16, 0.75f);
     *
     *
     * 21. WHY DOES HASHMAP RESIZE?
     *
     * As more entries are inserted, the number of collisions
     * can increase.
     *
     * Resizing increases the table capacity.
     *
     * In typical modern OpenJDK implementations, capacity
     * doubles during normal resizing.
     *
     * Example:
     *
     * Before:
     * Capacity = 16
     * Threshold = 12
     *
     * After resize:
     * Capacity = 32
     * Threshold = 24 (with default load factor)
     *
     * Existing entries are redistributed into the new table.
     *
     * This process is called rehashing or redistribution.
     *
     * Important:
     * HashMap does not necessarily call every key's hashCode()
     * method again during every resize.
     *
     * Modern OpenJDK implementations use stored hash values
     * and the changed capacity to redistribute entries.
     *
     *
     * 22. WHY IS THE DEFAULT LOAD FACTOR 0.75?
     *
     * There is a trade-off between:
     *
     * - Memory usage.
     * - Number of collisions.
     * - Resizing frequency.
     *
     * A smaller load factor:
     *
     * - Causes earlier resizing.
     * - Uses more table space.
     * - Can reduce collisions.
     *
     * A larger load factor:
     *
     * - Allows more entries per table slot on average.
     * - May reduce memory spent on empty buckets.
     * - Can increase collisions and lookup costs.
     *
     * 0.75 is a practical default balance.
     *
     *
     * 23. NULL KEY AND NULL VALUES
     *
     * HashMap allows:
     *
     * - One null key.
     * - Multiple null values.
     *
     * Example:
     *
     * Map<String, Integer> map = new HashMap<>();
     *
     * map.put(null, 100);
     * map.put("A", null);
     * map.put("B", null);
     *
     * This is valid.
     *
     * Why only one null key?
     *
     * Because keys must be unique.
     *
     * A second put(null, value) updates the existing
     * null-key mapping.
     *
     * Important:
     *
     * get(key) returning null can mean:
     *
     * 1. The key does not exist.
     * 2. The key exists and is mapped to null.
     *
     * Use containsKey() to distinguish these cases.
     *
     *
     * 24. DUPLICATE KEYS AND VALUES
     *
     * Duplicate keys:
     * Not allowed.
     *
     * Inserting an existing key replaces its old value.
     *
     * Duplicate values:
     * Allowed.
     *
     * Example:
     *
     * map.put(1, "Java");
     * map.put(2, "Java");
     *
     * Both mappings are valid.
     *
     *
     * 25. ITERATION ORDER
     *
     * HashMap does not guarantee insertion order.
     *
     * Example:
     *
     * map.put(10, "A");
     * map.put(20, "B");
     * map.put(30, "C");
     *
     * Do not assume iteration will always produce:
     *
     * 10, 20, 30
     *
     * The observed order can depend on:
     *
     * - Hash values.
     * - Table capacity.
     * - Collisions.
     * - Resizing.
     * - Implementation details.
     *
     * If insertion order is required, use LinkedHashMap.
     *
     * If sorted key order is required, use TreeMap.
     *
     *
     * 26. TIME COMPLEXITY
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
     * Worst-case behavior:
     *
     * Collisions can make operations slower.
     *
     * Tree bins help improve heavily collided buckets.
     *
     * Exact worst-case behavior can depend on key comparison,
     * hash distribution and implementation details.
     *
     * Do not interpret O(1) average as a guarantee that
     * every operation always takes constant time.
     *
     *
     * 27. IS HASHMAP THREAD-SAFE?
     *
     * No.
     *
     * HashMap is not synchronized.
     *
     * Multiple threads modifying the same HashMap concurrently
     * without proper coordination can cause incorrect behavior.
     *
     * Alternatives:
     *
     * 1. ConcurrentHashMap
     *    - Designed for concurrent access.
     *
     * 2. Collections.synchronizedMap()
     *    - Provides a synchronized wrapper.
     *
     * Example:
     *
     * Map<Integer, String> map =
     *     Collections.synchronizedMap(new HashMap<>());
     *
     * Important:
     * When iterating over a synchronizedMap, synchronize on
     * the returned map during the iteration.
     *
     * Example:
     *
     * synchronized (map) {
     *     for (Integer key : map.keySet()) {
     *         System.out.println(key);
     *     }
     * }
     *
     *
     * 28. FAIL-FAST ITERATORS
     *
     * HashMap iterators are fail-fast on a best-effort basis.
     *
     * Structural modifications made outside the iterator
     * during iteration may cause ConcurrentModificationException.
     *
     * Structural modification generally means adding or
     * removing mappings.
     *
     * Updating the value associated with an existing key
     * is not generally considered a structural modification.
     *
     * Example:
     *
     * for (Integer key : map.keySet()) {
     *     map.remove(key);
     * }
     *
     * This may throw ConcurrentModificationException.
     *
     * Safer approach:
     *
     * Iterator<Integer> iterator = map.keySet().iterator();
     *
     * while (iterator.hasNext()) {
     *     Integer key = iterator.next();
     *     iterator.remove();
     * }
     *
     * Or use:
     *
     * map.keySet().removeIf(key -> key < 10);
     *
     * Important:
     * Fail-fast behavior is not a synchronization mechanism
     * and must not be relied upon for program correctness.
     *
     *
     * 29. HASHMAP VS HASHTABLE
     *
     * HashMap:
     *
     * - Not synchronized.
     * - Allows one null key.
     * - Allows null values.
     * - Generally preferred for ordinary non-concurrent use.
     *
     * Hashtable:
     *
     * - Synchronized legacy implementation.
     * - Does not allow null keys or values.
     * - Generally replaced by modern alternatives.
     *
     *
     * 30. HASHMAP VS LINKEDHASHMAP
     *
     * HashMap:
     *
     * - No guaranteed iteration order.
     * - Uses a hash table.
     *
     * LinkedHashMap:
     *
     * - Maintains insertion order by default.
     * - Can also maintain access order.
     * - Uses a hash table plus linked ordering.
     *
     *
     * 31. HASHMAP VS TREEMAP
     *
     * HashMap:
     *
     * - Uses a hash table.
     * - Does not guarantee sorted key order.
     * - Average expected basic operations are O(1).
     *
     * TreeMap:
     *
     * - Uses a Red-Black Tree.
     * - Maintains keys in sorted order.
     * - Basic operations are O(log n).
     *
     *
     * 32. WHEN SHOULD WE USE HASHMAP?
     *
     * Use HashMap when:
     *
     * - You need key-value storage.
     * - Fast average key lookup is important.
     * - You do not require sorted keys.
     * - You do not require insertion-order iteration.
     * - You do not need built-in thread safety.
     *
     * Examples:
     *
     * - Frequency counting.
     * - Caching.
     * - Indexing records by ID.
     * - Grouping data by a key.
     * - Storing configuration data.
     *
     *
     * 33. COMMON MISTAKES
     *
     * 1. Assuming HashMap maintains insertion order.
     * 2. Assuming hashCode() directly gives the bucket index.
     * 3. Assuming equal hash codes mean equal objects.
     * 4. Overriding equals() without overriding hashCode().
     * 5. Using mutable objects as keys and changing fields
     *    involved in equality or hashing.
     * 6. Assuming HashMap is thread-safe.
     * 7. Assuming get() returning null always means the key
     *    is absent.
     * 8. Assuming all collisions immediately become trees.
     * 9. Assuming average O(1) means guaranteed O(1).
     * 10. Assuming iteration order is predictable.
     *
     *
     * 34. INTERVIEW QUESTIONS
     *
     * 1. What is HashMap?
     * 2. How does HashMap work internally?
     * 3. What is hashing?
     * 4. What is hashCode()?
     * 5. What is equals()?
     * 6. Why must equals() and hashCode() be consistent?
     * 7. What is a bucket?
     * 8. What is a hash collision?
     * 9. How does HashMap handle collisions?
     * 10. What is a linked-list bucket?
     * 11. What is treeification?
     * 12. Why does HashMap use a Red-Black Tree?
     * 13. What is the default initial capacity?
     * 14. What is the default load factor?
     * 15. What is the resize threshold?
     * 16. Why does HashMap resize?
     * 17. How is the bucket index calculated?
     * 18. Can HashMap contain null keys and values?
     * 19. Does HashMap maintain insertion order?
     * 20. Is HashMap thread-safe?
     * 21. What is the difference between HashMap and Hashtable?
     * 22. What is the difference between HashMap and TreeMap?
     * 23. What happens if a key's hashCode() changes?
     * 24. What happens if equals() is overridden but hashCode()
     *     is not?
     * 25. What is the difference between average and worst-case
     *     lookup complexity?
     *
     *
     * 35. QUICK REVISION
     *
     * - HashMap implements Map.
     * - It stores key-value pairs.
     * - It uses a hash table internally.
     * - It uses hashCode() and equals().
     * - Keys are unique; values can be duplicated.
     * - One null key and multiple null values are permitted.
     * - Default initial capacity is 16 in modern implementations.
     * - Default load factor is 0.75.
     * - Bucket index uses (capacity - 1) & hash in modern
     *   OpenJDK implementations.
     * - Collisions are handled using linked structures and,
     *   when conditions are satisfied, tree bins.
     * - Normal resizing generally doubles the table capacity
     *   in modern OpenJDK implementations.
     * - Average expected put(), get() and remove() are O(1).
     * - HashMap does not guarantee iteration order.
     * - HashMap is not thread-safe.
     *
     * ============================================================
     *                       END OF HASHMAP
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about HashMap , Happy Learning ..!");
    }
}
