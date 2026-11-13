
public class Hashtable {

    /*
     * ============================================================
     *                      HASHTABLE IN JAVA
     * ============================================================
     *
     * 1. WHAT IS HASHTABLE?
     *
     * Hashtable is a legacy class in java.util used to store
     * data in key-value pairs.
     *
     * Example:
     *
     * 101 -> "Vinay"
     * 102 -> "Rahul"
     * 103 -> "Kiran"
     *
     * Each key is unique, but values can be duplicated.
     *
     * Hashtable is synchronized, meaning its individual
     * operations are synchronized for thread safety.
     *
     * It is one of the original collection classes introduced
     * in Java 1.0.
     *
     *
     * 2. WHY WAS HASHTABLE INTRODUCED?
     *
     * Before the modern Collections Framework, Java provided
     * classes such as:
     *
     * - Vector
     * - Stack
     * - Hashtable
     * - Dictionary
     *
     * Hashtable provided hash-based key-value storage with
     * synchronized operations.
     *
     * Later, Java introduced the Collections Framework.
     *
     * HashMap became the commonly used general-purpose
     * hash-based Map implementation.
     *
     * ConcurrentHashMap became the preferred option for
     * many concurrent key-value use cases.
     *
     *
     * 3. HASHTABLE HIERARCHY
     *
     * Hashtable extends:
     *
     * Dictionary<K, V>
     *
     * Hashtable implements:
     *
     * - Map<K, V>
     * - Cloneable
     * - Serializable
     *
     * Important:
     *
     * Dictionary is a legacy abstract class.
     *
     * Hashtable does not extend AbstractMap.
     *
     *
     * 4. CREATING A HASHTABLE
     *
     * Import:
     *
     * import java.util.Hashtable;
     * import java.util.Map;
     *
     * Default constructor:
     *
     * Hashtable<Integer, String> map = new Hashtable<>();
     *
     * Constructor with initial capacity:
     *
     * Hashtable<Integer, String> map = new Hashtable<>(20);
     *
     * Constructor with capacity and load factor:
     *
     * Hashtable<Integer, String> map =
     *     new Hashtable<>(20, 0.75f);
     *
     * Constructor using another Map:
     *
     * Hashtable<Integer, String> map =
     *     new Hashtable<>(anotherMap);
     *
     *
     * 5. HOW DOES HASHTABLE WORK INTERNALLY?
     *
     * Hashtable uses a hash table internally.
     *
     * A hash table stores entries in an array of buckets.
     *
     * When we insert a key-value pair:
     *
     * Step 1:
     * The key's hashCode() is calculated.
     *
     * Step 2:
     * The hash is used to determine a bucket index.
     *
     * Step 3:
     * The entry is stored in that bucket.
     *
     * Step 4:
     * If multiple keys map to the same bucket, a collision
     * occurs.
     *
     * Hashtable handles collisions using linked-list chains.
     *
     * Important distinction:
     *
     * Modern HashMap can convert sufficiently large collision
     * bins into balanced trees under specific conditions.
     *
     * Hashtable does not use HashMap's tree-bin mechanism.
     *
     *
     * 6. HASHCODE AND EQUALS
     *
     * Hashtable uses hashCode() to determine where a key
     * should be stored.
     *
     * It uses equals() to identify an equivalent existing key.
     *
     * The following contract must be followed:
     *
     * If two objects are equal according to equals(),
     * they must return the same hash code.
     *
     * If this contract is violated, key lookup may behave
     * unexpectedly.
     *
     * Example:
     *
     * class Student {
     *     int id;
     *
     *     Student(int id) {
     *         this.id = id;
     *     }
     *
     *     @Override
     *     public int hashCode() {
     *         return Integer.hashCode(id);
     *     }
     *
     *     @Override
     *     public boolean equals(Object obj) {
     *         if (this == obj) return true;
     *
     *         if (!(obj instanceof Student)) return false;
     *
     *         Student other = (Student) obj;
     *
     *         return this.id == other.id;
     *     }
     * }
     *
     * If two Student objects have the same ID, they are
     * treated as equivalent keys.
     *
     *
     * 7. NULL KEYS AND NULL VALUES
     *
     * Hashtable does not allow null keys.
     *
     * Hashtable does not allow null values.
     *
     * Example:
     *
     * Hashtable<Integer, String> map = new Hashtable<>();
     *
     * map.put(null, "Vinay"); // NullPointerException
     *
     * map.put(101, null);     // NullPointerException
     *
     * Why?
     *
     * Hashtable's implementation rejects null keys and values.
     *
     * This differs from HashMap, which permits one null key
     * and multiple null values.
     *
     *
     * 8. DUPLICATE KEYS AND VALUES
     *
     * Duplicate keys are not allowed.
     *
     * Inserting an equivalent key again replaces its value.
     *
     * Example:
     *
     * map.put(101, "Vinay");
     * map.put(101, "Rahul");
     *
     * Result:
     *
     * {101=Rahul}
     *
     * Duplicate values are allowed.
     *
     * Example:
     *
     * map.put(102, "Kiran");
     * map.put(103, "Kiran");
     *
     *
     * 9. SYNCHRONIZATION
     *
     * This is the most important characteristic of Hashtable.
     *
     * Hashtable is synchronized.
     *
     * Its individual methods are synchronized so that
     * concurrent calls to those methods are coordinated
     * using the Hashtable object's monitor.
     *
     * Example:
     *
     * Hashtable<Integer, String> map = new Hashtable<>();
     *
     * map.put(1, "Java");
     *
     * The put() operation is synchronized.
     *
     * Similarly, methods such as get(), remove() and containsKey()
     * are synchronized.
     *
     * What does synchronized mean here?
     *
     * Only one thread at a time can execute a synchronized
     * instance method on the same Hashtable object.
     *
     * Other threads calling synchronized instance methods
     * on that same object must wait for the monitor.
     *
     * IMPORTANT:
     *
     * Synchronized individual methods do not automatically make
     * a sequence of multiple operations atomic.
     *
     * Example:
     *
     * if (!map.containsKey(101)) {
     *     map.put(101, "Vinay");
     * }
     *
     * Another thread can modify the map between containsKey()
     * and put().
     *
     * For atomic compound operations, use suitable synchronization
     * or an appropriate atomic Map method.
     *
     *
     * 10. HASHTABLE VS HASHMAP
     *
     * HashMap:
     *
     * - Introduced in Java 1.2.
     * - Not synchronized.
     * - Allows one null key.
     * - Allows multiple null values.
     * - Generally faster for single-threaded use.
     * - Uses tree bins for certain collision-heavy buckets
     *   in modern implementations.
     *
     * Hashtable:
     *
     * - Introduced in Java 1.0.
     * - Synchronized individual methods.
     * - Does not allow null keys.
     * - Does not allow null values.
     * - Has legacy API characteristics.
     * - Uses linked-list collision chains.
     *
     *
     * 11. HASHTABLE VS CONCURRENTHASHMAP
     *
     * Both support concurrent access, but their designs differ.
     *
     * Hashtable:
     *
     * - Uses synchronized methods.
     * - Synchronization is generally at the whole-object level.
     * - Concurrent operations can contend on the same monitor.
     *
     * ConcurrentHashMap:
     *
     * - Designed specifically for concurrent access.
     * - Supports concurrent reads and updates with a more
     *   scalable internal synchronization strategy.
     * - Provides atomic methods such as putIfAbsent(),
     *   computeIfAbsent() and replace().
     * - Does not allow null keys or null values.
     *
     * For most modern concurrent Map use cases,
     * ConcurrentHashMap is preferred.
     *
     *
     * 12. IMPORTANT HASHTABLE METHODS
     *
     * Hashtable provides Map methods:
     *
     * put(key, value)
     * get(key)
     * remove(key)
     * containsKey(key)
     * containsValue(value)
     * size()
     * isEmpty()
     * clear()
     * putAll(map)
     *
     * Legacy methods inherited from Dictionary include:
     *
     * get(key)
     * put(key, value)
     * remove(key)
     * keys()
     * elements()
     *
     * Hashtable also provides:
     *
     * keys()
     * elements()
     * rehash()
     *
     * rehash() is protected and is used internally when
     * the table needs to grow.
     *
     *
     * 13. ENUMERATION IN HASHTABLE
     *
     * Hashtable provides two legacy methods:
     *
     * keys()
     *
     * Returns an Enumeration of keys.
     *
     * elements()
     *
     * Returns an Enumeration of values.
     *
     * Example:
     *
     * Enumeration<Integer> keys = map.keys();
     *
     * while (keys.hasMoreElements()) {
     *     System.out.println(keys.nextElement());
     * }
     *
     * Values:
     *
     * Enumeration<String> values = map.elements();
     *
     * while (values.hasMoreElements()) {
     *     System.out.println(values.nextElement());
     * }
     *
     * Enumeration is a legacy traversal interface.
     *
     * It provides:
     *
     * - hasMoreElements()
     * - nextElement()
     *
     * Unlike Iterator, Enumeration does not provide remove().
     *
     *
     * 14. ITERATION USING ENTRYSET
     *
     * Modern code generally uses Map views and iterators.
     *
     * Example:
     *
     * for (Map.Entry<Integer, String> entry :
     *      map.entrySet()) {
     *
     *     System.out.println(
     *         entry.getKey() + " = " + entry.getValue()
     *     );
     * }
     *
     * Hashtable's iterators are fail-fast on a best-effort basis.
     *
     * If the map is structurally modified outside the iterator
     * while iteration is in progress, the iterator may throw
     * ConcurrentModificationException.
     *
     * This is a bug-detection mechanism, not a thread-safety
     * guarantee.
     *
     *
     * 15. ENUMERATION VS ITERATOR
     *
     * Enumeration:
     *
     * - Legacy traversal interface.
     * - Uses hasMoreElements().
     * - Uses nextElement().
     * - Does not support remove().
     * - Used by Hashtable's keys() and elements().
     *
     * Iterator:
     *
     * - Part of the Collections Framework.
     * - Uses hasNext().
     * - Uses next().
     * - Supports optional remove().
     * - Used by modern collection views.
     *
     * Important:
     *
     * Hashtable's Enumeration is not fail-fast.
     *
     * Its Iterator is fail-fast on a best-effort basis.
     *
     *
     * 16. CAPACITY AND LOAD FACTOR
     *
     * Capacity:
     *
     * The number of buckets in the internal hash table.
     *
     * Load factor:
     *
     * Controls how full the table can become before it grows.
     *
     * Default initial capacity:
     *
     * 11
     *
     * Default load factor:
     *
     * 0.75
     *
     * Example:
     *
     * Hashtable<Integer, String> map = new Hashtable<>();
     *
     * When the number of entries crosses the threshold,
     * Hashtable increases its capacity and rehashes entries.
     *
     * Rehashing means:
     *
     * - A larger bucket array is created.
     * - Existing entries are redistributed.
     * - Their bucket positions are recalculated.
     *
     * Important:
     *
     * Hashtable's default initial capacity is 11,
     * unlike HashMap's default initial capacity of 16.
     *
     *
     * 17. TIME COMPLEXITY
     *
     * Average expected complexity with a well-distributed hash:
     *
     * put()           -> O(1)
     * get()           -> O(1)
     * remove()        -> O(1)
     * containsKey()   -> O(1)
     *
     * Worst case with heavy collisions:
     *
     * These operations can take O(n).
     *
     * containsValue() -> O(n)
     *
     * Iterating all entries -> O(capacity + size)
     *
     * Actual performance depends on hash distribution,
     * table capacity and synchronization contention.
     *
     *
     * 18. COMPLETE HASHTABLE EXAMPLE
     *
     * import java.util.Hashtable;
     * import java.util.Map;
     *
     * public class HashtableExample {
     *
     *     public static void main(String[] args) {
     *
     *         Hashtable<Integer, String> map =
     *             new Hashtable<>();
     *
     *         map.put(101, "Vinay");
     *         map.put(102, "Rahul");
     *         map.put(103, "Kiran");
     *
     *         System.out.println(map);
     *
     *         System.out.println(map.get(101));
     *
     *         System.out.println(map.containsKey(102));
     *
     *         map.remove(103);
     *
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
     * 19. WHEN SHOULD WE USE HASHTABLE?
     *
     * Hashtable is mainly important when:
     *
     * - Working with legacy Java code that already uses it.
     * - Maintaining compatibility with older APIs.
     *
     * For new development:
     *
     * - Use HashMap for ordinary non-concurrent Map operations.
     * - Use ConcurrentHashMap for most concurrent Map operations.
     *
     *
     * 20. COMMON MISTAKES
     *
     * 1. Assuming Hashtable allows null keys.
     *    It does not.
     *
     * 2. Assuming Hashtable allows null values.
     *    It does not.
     *
     * 3. Assuming Hashtable is always the best concurrent Map.
     *    ConcurrentHashMap is generally preferred.
     *
     * 4. Assuming synchronized methods make multiple operations
     *    automatically atomic.
     *    They do not.
     *
     * 5. Confusing Enumeration and Iterator.
     *    They are different traversal interfaces.
     *
     * 6. Assuming Hashtable preserves insertion order.
     *    It does not guarantee iteration order.
     *
     * 7. Assuming fail-fast iterators provide thread safety.
     *    They do not.
     *
     *
     * 21. INTERVIEW QUESTIONS
     *
     * 1. What is Hashtable?
     * 2. Is Hashtable synchronized?
     * 3. Does Hashtable allow null keys?
     * 4. Does Hashtable allow null values?
     * 5. What is the difference between HashMap and Hashtable?
     * 6. What is the difference between Hashtable and
     *    ConcurrentHashMap?
     * 7. What is Enumeration?
     * 8. What is the difference between Enumeration and Iterator?
     * 9. What is rehashing?
     * 10. What is the default initial capacity of Hashtable?
     * 11. What is its default load factor?
     * 12. Does Hashtable maintain insertion order?
     * 13. Is Hashtable a legacy class?
     * 14. What happens when duplicate keys are inserted?
     * 15. How does Hashtable handle collisions?
     * 16. Are Hashtable iterators fail-fast?
     * 17. Does synchronization make a compound operation atomic?
     *
     *
     * 22. QUICK REVISION
     *
     * - Hashtable is a legacy Map implementation.
     * - Introduced in Java 1.0.
     * - Extends Dictionary.
     * - Implements Map, Cloneable and Serializable.
     * - Stores key-value pairs.
     * - Uses a hash table internally.
     * - Uses linked-list collision chains.
     * - Individual methods are synchronized.
     * - Does not allow null keys or null values.
     * - Duplicate keys replace existing values.
     * - Does not guarantee iteration order.
     * - Default initial capacity is 11.
     * - Default load factor is 0.75.
     * - Provides legacy Enumeration methods.
     * - Iterators are fail-fast on a best-effort basis.
     * - ConcurrentHashMap is generally preferred for
     *   modern concurrent Map use cases.
     *
     * ============================================================
     *                      END OF HASHTABLE
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Hashtable , Happy Learning ..!");
    }
}
