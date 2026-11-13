
public class IdentityHashMap {

    /*
     * ============================================================
     *                   IDENTITYHASHMAP IN JAVA
     * ============================================================
     *
     * 1. WHAT IS IDENTITYHASHMAP?
     *
     * IdentityHashMap is a specialized Map implementation
     * available in java.util.
     *
     * It compares keys using reference equality (==)
     * instead of logical equality (equals()).
     *
     * In simple words:
     *
     * HashMap:
     * Checks whether two keys are logically equal.
     *
     * IdentityHashMap:
     * Checks whether two keys refer to the exact same object.
     *
     *
     * 2. WHY DO WE NEED IDENTITYHASHMAP?
     *
     * Imagine creating two Student objects with identical data.
     *
     * Student s1 = new Student(101, "Vinay");
     * Student s2 = new Student(101, "Vinay");
     *
     * Both objects contain the same information.
     *
     * But they are two different objects in memory.
     *
     * If Student overrides equals() and hashCode():
     *
     * s1.equals(s2) -> true
     * s1 == s2      -> false
     *
     * HashMap treats them as equivalent keys.
     *
     * IdentityHashMap treats them as different keys.
     *
     * This is the main purpose of IdentityHashMap.
     *
     *
     * 3. UNDERSTANDING == VS equals()
     *
     * == operator:
     *
     * For object references, checks whether both references
     * point to the same object.
     *
     * equals():
     *
     * Checks logical equality according to the implementation
     * of the object's equals() method.
     *
     * Example:
     *
     * String s1 = new String("Java");
     * String s2 = new String("Java");
     *
     * System.out.println(s1 == s2);      // false
     * System.out.println(s1.equals(s2)); // true
     *
     * IdentityHashMap uses the first comparison principle.
     *
     *
     * 4. CREATING AN IDENTITYHASHMAP
     *
     * Import:
     *
     * import java.util.IdentityHashMap;
     *
     * Create:
     *
     * IdentityHashMap<Object, String> map =
     *     new IdentityHashMap<>();
     *
     * It implements the Map interface.
     *
     *
     * 5. HASHMAP VS IDENTITYHASHMAP EXAMPLE
     *
     * import java.util.HashMap;
     * import java.util.IdentityHashMap;
     * import java.util.Map;
     *
     * public class Example {
     *
     *     public static void main(String[] args) {
     *
     *         String s1 = new String("Java");
     *         String s2 = new String("Java");
     *
     *         Map<String, Integer> hashMap = new HashMap<>();
     *
     *         hashMap.put(s1, 100);
     *         hashMap.put(s2, 200);
     *
     *         System.out.println(hashMap.size());
     *
     *         Map<String, Integer> identityMap =
     *             new IdentityHashMap<>();
     *
     *         identityMap.put(s1, 100);
     *         identityMap.put(s2, 200);
     *
     *         System.out.println(identityMap.size());
     *     }
     * }
     *
     * Output:
     *
     * 1
     * 2
     *
     * Why?
     *
     * HashMap:
     *
     * s1.equals(s2) is true.
     * The second insertion replaces the first value.
     *
     * IdentityHashMap:
     *
     * s1 and s2 are different object references.
     * Both mappings are retained.
     *
     *
     * 6. INTERNAL WORKING
     *
     * IdentityHashMap uses a specialized hash table.
     *
     * Unlike HashMap, it uses:
     *
     * - System.identityHashCode(key) for hashing.
     * - == for key comparison.
     *
     * What is System.identityHashCode()?
     *
     * It returns the identity hash code associated with
     * an object, regardless of whether the object's class
     * overrides hashCode().
     *
     * Important:
     *
     * An identity hash code is not guaranteed to be unique.
     *
     * Two different objects can have the same identity hash code.
     *
     * Therefore, IdentityHashMap still uses == to distinguish
     * keys when hash collisions occur.
     *
     *
     * 7. IDENTITYHASHMAP STORAGE
     *
     * IdentityHashMap uses an array-based hash table.
     *
     * Its internal representation is different from HashMap.
     *
     * It stores keys and values in alternating positions
     * in an internal Object array.
     *
     * Conceptual representation:
     *
     * [Key1, Value1, Key2, Value2, Key3, Value3, ...]
     *
     * This is called an open-addressed hash table.
     *
     * It does not use the same separate-chaining bucket
     * structure as HashMap.
     *
     * When a collision occurs, it searches for another
     * available slot using its probing strategy.
     *
     * This is an implementation detail, not something
     * application code should depend on.
     *
     *
     * 8. HASHCODE VS IDENTITYHASHCODE
     *
     * Object.hashCode():
     *
     * Can be overridden by a class.
     *
     * Two logically equal objects should return the same
     * hash code when equals() is correctly implemented.
     *
     * System.identityHashCode():
     *
     * Returns the identity hash code of an object.
     *
     * It does not depend on an overridden hashCode() method.
     *
     * Example:
     *
     * String s1 = new String("Java");
     *
     * System.out.println(s1.hashCode());
     *
     * System.out.println(System.identityHashCode(s1));
     *
     * These values may differ.
     *
     * IMPORTANT:
     *
     * Do not assume identityHashCode() is a memory address.
     *
     * Do not assume it is unique.
     *
     *
     * 9. DUPLICATE KEYS AND VALUES
     *
     * IdentityHashMap considers two keys equivalent only
     * when they are the same object reference.
     *
     * Example:
     *
     * Object key1 = new Object();
     * Object key2 = new Object();
     *
     * map.put(key1, "A");
     * map.put(key2, "B");
     *
     * Both mappings are retained.
     *
     * But:
     *
     * map.put(key1, "C");
     *
     * Replaces the value associated with key1.
     *
     * Duplicate values are allowed.
     *
     *
     * 10. NULL KEYS AND VALUES
     *
     * IdentityHashMap permits null keys.
     *
     * It permits null values.
     *
     * Example:
     *
     * IdentityHashMap<Object, String> map =
     *     new IdentityHashMap<>();
     *
     * map.put(null, "Java");
     * map.put(new Object(), null);
     *
     *
     * 11. IMPORTANT METHODS
     *
     * IdentityHashMap implements Map.
     *
     * It supports ordinary Map operations:
     *
     * put()
     * get()
     * remove()
     * containsKey()
     * containsValue()
     * size()
     * isEmpty()
     * clear()
     * putAll()
     *
     * It also supports:
     *
     * putIfAbsent()
     * computeIfAbsent()
     * computeIfPresent()
     * compute()
     * merge()
     * replace()
     * replaceAll()
     *
     * IMPORTANT:
     *
     * Key-based operations use reference identity.
     *
     * containsValue() uses ordinary value equality,
     * not reference identity.
     *
     *
     * 12. IDENTITYHASHMAP AND MAP CONTRACT
     *
     * IdentityHashMap intentionally uses reference equality
     * instead of the standard Map's usual logical key equality.
     *
     * Therefore, it violates the general Map contract's
     * expectations for key comparison.
     *
     * It is a specialized implementation intended for
     * situations where object identity matters.
     *
     * Do not casually replace HashMap with IdentityHashMap.
     *
     *
     * 13. NULL AND EQUALITY DETAILS
     *
     * IdentityHashMap permits null keys and values.
     *
     * Since it uses reference identity:
     *
     * Two distinct objects that are equals() to each other
     * can still be separate keys.
     *
     * Null is handled as a valid key.
     *
     * There can be only one null key mapping.
     *
     *
     * 14. ITERATION ORDER
     *
     * IdentityHashMap does not guarantee iteration order.
     *
     * It does not preserve insertion order.
     *
     * It does not sort keys.
     *
     * Its iteration order depends on its internal hash table.
     *
     *
     * 15. THREAD SAFETY
     *
     * IdentityHashMap is NOT synchronized.
     *
     * It is not designed for concurrent modification
     * by multiple threads without external coordination.
     *
     * A synchronized wrapper can be created:
     *
     * Map<Object, String> map =
     *     Collections.synchronizedMap(
     *         new IdentityHashMap<>()
     *     );
     *
     * When iterating, synchronize on the wrapper:
     *
     * synchronized (map) {
     *
     *     for (Map.Entry<Object, String> entry :
     *          map.entrySet()) {
     *
     *         System.out.println(entry);
     *     }
     * }
     *
     *
     * 16. ITERATORS
     *
     * IdentityHashMap iterators are fail-fast on a
     * best-effort basis.
     *
     * Structural modifications outside the iterator
     * may cause ConcurrentModificationException.
     *
     * Fail-fast behavior is not a thread-safety guarantee.
     *
     *
     * 17. REAL-WORLD USE CASES
     *
     * IdentityHashMap is useful when object identity matters
     * more than logical equality.
     *
     * Examples:
     *
     * 1. Object graph traversal:
     *
     *    Track exactly which object instances have already
     *    been visited.
     *
     * 2. Serialization:
     *
     *    Track object instances and preserve reference identity.
     *
     * 3. Deep-copy algorithms:
     *
     *    Maintain a mapping from original object instances
     *    to their copied instances.
     *
     * 4. Object-to-metadata association:
     *
     *    Associate information with a particular object instance,
     *    even when equals() considers multiple objects equal.
     *
     * 5. Cycle detection:
     *
     *    Detect revisiting the same object in a graph.
     *
     *
     * 18. IDENTITYHASHMAP VS HASHMAP
     *
     * HashMap:
     *
     * - Uses hashCode() for hashing.
     * - Uses equals() for key equality.
     * - Uses separate chaining.
     * - Allows one null key.
     * - Allows multiple null values.
     *
     * IdentityHashMap:
     *
     * - Uses System.identityHashCode() for hashing.
     * - Uses == for key equality.
     * - Uses open addressing.
     * - Allows null keys.
     * - Allows null values.
     *
     *
     * 19. WHEN SHOULD WE USE IDENTITYHASHMAP?
     *
     * Use it when:
     *
     * - Object identity is important.
     * - Two logically equal objects must remain separate keys.
     * - You are implementing object graph algorithms.
     * - You need to track specific object instances.
     *
     * Avoid it when:
     *
     * - Normal logical key equality is expected.
     * - You need sorted or insertion-order iteration.
     * - You need concurrent access without synchronization.
     *
     *
     * 20. COMMON MISTAKES
     *
     * 1. Assuming IdentityHashMap uses equals().
     *    It uses == for keys.
     *
     * 2. Assuming identityHashCode() is a memory address.
     *    It is not guaranteed to be one.
     *
     * 3. Assuming identity hash codes are unique.
     *    Collisions are possible.
     *
     * 4. Assuming IdentityHashMap is a drop-in replacement
     *    for HashMap.
     *    It has intentionally different key semantics.
     *
     * 5. Assuming containsValue() uses identity comparison.
     *    It uses ordinary value equality.
     *
     * 6. Assuming IdentityHashMap preserves insertion order.
     *    It does not.
     *
     * 7. Assuming IdentityHashMap is thread-safe.
     *    It is not.
     *
     *
     * 21. INTERVIEW QUESTIONS
     *
     * 1. What is IdentityHashMap?
     * 2. How is it different from HashMap?
     * 3. What is reference equality?
     * 4. What is the difference between == and equals()?
     * 5. What is System.identityHashCode()?
     * 6. Is identityHashCode() guaranteed to be unique?
     * 7. Which collision-resolution technique does
     *    IdentityHashMap use?
     * 8. Does IdentityHashMap allow null keys?
     * 9. Does IdentityHashMap allow null values?
     * 10. Is IdentityHashMap thread-safe?
     * 11. Does containsValue() use reference equality?
     * 12. Why does IdentityHashMap violate the general
     *     Map contract?
     * 13. What are practical use cases of IdentityHashMap?
     *
     *
     * 22. QUICK REVISION
     *
     * - IdentityHashMap is a specialized Map implementation.
     * - It uses == for key comparison.
     * - It uses System.identityHashCode() for hashing.
     * - It uses an array-based open-addressed hash table.
     * - Two distinct objects can be separate keys even when
     *   equals() returns true.
     * - It permits null keys and null values.
     * - It does not guarantee iteration order.
     * - It is not synchronized.
     * - containsValue() uses ordinary value equality.
     * - It is useful for object identity-based algorithms.
     *
     * ============================================================
     *                  END OF IDENTITYHASHMAP
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about IdentityHashMap , Happy Learning ..!");
    }
}
