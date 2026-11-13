
public class HashSet {

    /*
     * ============================================================
     *                    JAVA HASHSET
     * ============================================================
     *
     * 1. WHAT IS HASHSET?
     *
     * Imagine you are maintaining a list of registered users.
     *
     * Every user has a unique ID:
     *
     *     101, 102, 103, 101, 104
     *
     * You don't want duplicate IDs.
     *
     * You also don't need to access users using positions like
     * index 0, index 1, etc.
     *
     * You simply want:
     * - Unique elements.
     * - Fast insertion.
     * - Fast deletion.
     * - Fast searching.
     *
     * HashSet is designed for these requirements.
     *
     * HashSet is a class in java.util that implements the Set
     * interface and uses hashing to store elements.
     *
     * Declaration:
     *
     *     public class HashSet<E>
     *         extends AbstractSet<E>
     *         implements Set<E>, Cloneable, Serializable
     *
     * IMPORTANT:
     *
     * HashSet:
     * - Does not allow duplicate elements.
     * - Does not guarantee iteration order.
     * - Allows at most one null element.
     * - Is not synchronized by default.
     * - Offers expected constant-time basic operations when
     *   hash values are distributed properly.
     *
     *
     * ============================================================
     * 2. PACKAGE AND IMPORT
     * ============================================================
     *
     * Package:
     *
     *     java.util
     *
     * Import:
     *
     *     import java.util.HashSet;
     *     import java.util.Set;
     *
     * Example:
     *
     *     Set<String> names = new HashSet<>();
     *
     * Prefer the Set interface as the reference type when your
     * code only needs Set operations.
     *
     *
     * ============================================================
     * 3. HOW TO CREATE A HASHSET
     * ============================================================
     *
     * Empty HashSet:
     *
     *     HashSet<Integer> numbers = new HashSet<>();
     *
     * Using the Set reference:
     *
     *     Set<Integer> numbers = new HashSet<>();
     *
     * With initial capacity:
     *
     *     HashSet<Integer> numbers = new HashSet<>(32);
     *
     * With initial capacity and load factor:
     *
     *     HashSet<Integer> numbers = new HashSet<>(32, 0.75f);
     *
     * From another collection:
     *
     *     List<Integer> values = Arrays.asList(10, 20, 10, 30);
     *
     *     Set<Integer> numbers = new HashSet<>(values);
     *
     * Result contains:
     *
     *     10, 20, 30
     *
     * Duplicate elements are automatically eliminated.
     *
     *
     * ============================================================
     * 4. BASIC HASHSET OPERATIONS
     * ============================================================
     *
     *     Set<String> names = new HashSet<>();
     *
     * ADD:
     *
     *     names.add("Vinay");
     *     names.add("Ravi");
     *     names.add("Kiran");
     *
     * ADD DUPLICATE:
     *
     *     boolean result = names.add("Vinay");
     *
     *     System.out.println(result); // false
     *
     * REMOVE:
     *
     *     names.remove("Ravi");
     *
     * SEARCH:
     *
     *     names.contains("Vinay");
     *
     * SIZE:
     *
     *     names.size();
     *
     * CHECK EMPTY:
     *
     *     names.isEmpty();
     *
     * REMOVE EVERYTHING:
     *
     *     names.clear();
     *
     * These operations are inherited from the Collection
     * hierarchy, but HashSet provides their hash-based behavior.
     *
     *
     * ============================================================
     * 5. HOW DOES HASHSET WORK INTERNALLY?
     * ============================================================
     *
     * This is the most important section.
     *
     * HashSet internally uses a HashMap to store its elements.
     *
     * Simplified internal representation:
     *
     *     HashSet<E>
     *          |
     *          v
     *     HashMap<E, Object>
     *
     * When we add an element to a HashSet, it is stored as
     * a KEY in the internal HashMap.
     *
     * The value is a shared dummy object.
     *
     * Conceptually:
     *
     *     map.put(element, PRESENT);
     *
     * PRESENT is an internal constant object.
     *
     * Why use a HashMap?
     *
     * Because HashMap already provides:
     * - Hash-based storage.
     * - Key uniqueness.
     * - Collision handling.
     * - Resizing.
     * - Efficient lookup.
     *
     * HashSet reuses these capabilities rather than implementing
     * a separate hashing mechanism.
     *
     * IMPORTANT:
     *
     * This is an implementation detail of standard OpenJDK
     * HashSet, not a requirement imposed on every possible
     * Java implementation.
     *
     *
     * ============================================================
     * 6. WHAT IS HASHING?
     * ============================================================
     *
     * Hashing is a technique used to convert an object's
     * information into an integer hash value.
     *
     * In Java, objects can provide this value through:
     *
     *     hashCode()
     *
     * Example:
     *
     *     String name = "Java";
     *
     *     int hash = name.hashCode();
     *
     * HashSet uses the hash value to help decide where an
     * element should be stored.
     *
     * Think of a college library:
     *
     * Instead of searching every book one by one, the library
     * assigns books to shelves using a classification system.
     *
     * Hashing works similarly:
     *
     *     Object -> Hash value -> Bucket location
     *
     * A good hash function distributes elements across buckets
     * to reduce collisions.
     *
     *
     * ============================================================
     * 7. WHAT IS A BUCKET?
     * ============================================================
     *
     * A bucket is a storage position in the internal hash table.
     *
     * Imagine a simplified table:
     *
     *     Bucket 0
     *     Bucket 1
     *     Bucket 2
     *     Bucket 3
     *     Bucket 4
     *     Bucket 5
     *     Bucket 6
     *     Bucket 7
     *
     * HashSet uses the hash value to determine the bucket
     * where an element should be stored.
     *
     * Simplified conceptual formula:
     *
     *     index = hash % capacity
     *
     * This formula is useful for understanding the idea.
     *
     * However, modern HashMap implementations use a hash
     * spreading operation and a bitwise index calculation.
     * They do not simply use this modulo formula.
     *
     *
     * ============================================================
     * 8. WHAT IS A COLLISION?
     * ============================================================
     *
     * A collision occurs when two different elements map to
     * the same bucket.
     *
     * Example:
     *
     *     Element A -> Bucket 3
     *     Element B -> Bucket 3
     *
     * Both elements want to occupy the same bucket.
     *
     * This is called a hash collision.
     *
     * IMPORTANT:
     *
     * Two objects having the same hashCode() does not mean
     * they are equal.
     *
     * Example:
     *
     *     "FB".hashCode() == "Ea".hashCode()
     *
     * Both Strings have the same hash code, but:
     *
     *     "FB".equals("Ea") // false
     *
     * HashSet must be able to store both.
     *
     *
     * ============================================================
     * 9. HOW DOES HASHSET HANDLE COLLISIONS?
     * ============================================================
     *
     * HashSet delegates collision handling to its internal
     * HashMap.
     *
     * In modern OpenJDK implementations, a bucket can contain
     * multiple entries.
     *
     * These entries are initially organized using linked
     * structures.
     *
     * Under certain conditions, a heavily populated bucket
     * can be converted into a balanced tree structure.
     *
     * This is called treeification.
     *
     * Why treeification?
     *
     * If many elements fall into the same bucket, searching
     * through a long linked structure becomes slower.
     *
     * A balanced tree can improve lookup performance.
     *
     * OpenJDK implementation details:
     *
     * - TREEIFY_THRESHOLD = 8
     * - UNTREEIFY_THRESHOLD = 6
     * - MIN_TREEIFY_CAPACITY = 64
     *
     * These are implementation details, not guarantees of
     * the Java Set or HashSet API.
     *
     * Treeification generally requires a sufficiently large
     * table. Otherwise, HashMap may resize instead.
     *
     *
     * ============================================================
     * 10. HOW DOES HASHSET ADD AN ELEMENT?
     * ============================================================
     *
     * Consider:
     *
     *     Set<String> names = new HashSet<>();
     *
     *     names.add("Java");
     *
     * Conceptually, the process is:
     *
     * Step 1:
     *     HashSet receives the element "Java".
     *
     * Step 2:
     *     It delegates insertion to its internal HashMap.
     *
     * Step 3:
     *     HashMap calculates the hash value.
     *
     * Step 4:
     *     HashMap identifies the bucket.
     *
     * Step 5:
     *     If the bucket is empty, the entry is inserted.
     *
     * Step 6:
     *     If the bucket is occupied, existing entries are
     *     checked to determine whether an equal key exists.
     *
     * Step 7:
     *     If an equal key exists, the new element is not added.
     *
     * Step 8:
     *     Otherwise, the new element is stored.
     *
     * Step 9:
     *     If the table exceeds its resize threshold, resizing
     *     may occur.
     *
     *
     * ============================================================
     * 11. ROLE OF HASHCODE() AND EQUALS()
     * ============================================================
     *
     * HashSet relies on hashCode() and equals() through
     * its internal HashMap.
     *
     * hashCode():
     *     Helps locate the bucket.
     *
     * equals():
     *     Helps determine whether two keys are logically equal.
     *
     * GENERAL PROCESS:
     *
     * 1. Calculate hashCode().
     *
     * 2. Identify the relevant bucket.
     *
     * 3. Compare candidate keys using identity and/or equals().
     *
     * 4. If an equal key exists, treat the element as duplicate.
     *
     * IMPORTANT CONTRACT:
     *
     * If:
     *
     *     a.equals(b) == true
     *
     * Then:
     *
     *     a.hashCode() == b.hashCode()
     *
     * But:
     *
     *     a.hashCode() == b.hashCode()
     *
     * does not require:
     *
     *     a.equals(b) == true
     *
     * Equal objects must have equal hash codes.
     * Equal hash codes do not necessarily mean equal objects.
     *
     *
     * ============================================================
     * 12. WHY MUST WE OVERRIDE BOTH METHODS?
     * ============================================================
     *
     * Consider a Student class:
     *
     *     class Student {
     *         int id;
     *
     *         Student(int id) {
     *             this.id = id;
     *         }
     *     }
     *
     * Now:
     *
     *     Set<Student> students = new HashSet<>();
     *
     *     students.add(new Student(101));
     *     students.add(new Student(101));
     *
     * Both objects contain the same ID.
     *
     * But they are two different object instances.
     *
     * Without overriding equals() and hashCode(), the default
     * Object implementations use object identity behavior.
     *
     * Therefore, these two objects may both be stored.
     *
     * To define logical equality based on student ID:
     *
     *     class Student {
     *         int id;
     *
     *         Student(int id) {
     *             this.id = id;
     *         }
     *
     *         @Override
     *         public boolean equals(Object obj) {
     *             if (this == obj) return true;
     *             if (!(obj instanceof Student)) return false;
     *
     *             Student other = (Student) obj;
     *             return this.id == other.id;
     *         }
     *
     *         @Override
     *         public int hashCode() {
     *             return Integer.hashCode(id);
     *         }
     *     }
     *
     * Now the Set recognizes two Students with the same ID
     * as equal.
     *
     * In production code, consider records or IDE-generated
     * equals() and hashCode() implementations when appropriate.
     *
     *
     * ============================================================
     * 13. INTERNAL HASH SPREADING
     * ============================================================
     *
     * In modern OpenJDK HashMap implementations, the hash
     * returned by the key is spread before bucket selection.
     *
     * Conceptually:
     *
     *     h = key.hashCode();
     *     hash = h ^ (h >>> 16);
     *
     * This mixes some higher bits into lower bits.
     *
     * Why?
     *
     * Because bucket selection uses a subset of hash bits.
     *
     * Spreading helps make those bits more useful for
     * distributing entries.
     *
     * IMPORTANT:
     *
     * This is an OpenJDK implementation detail, not a public
     * API guarantee.
     *
     *
     * ============================================================
     * 14. INITIAL CAPACITY
     * ============================================================
     *
     * Initial capacity is the initial size of the internal
     * hash table when it is allocated.
     *
     * Example:
     *
     *     Set<Integer> numbers = new HashSet<>(32);
     *
     * This requests an initial capacity of 32.
     *
     * IMPORTANT:
     *
     * In common OpenJDK implementations, the constructor
     * initially records a capacity-related threshold and the
     * backing table is allocated lazily when needed.
     *
     * Also, the actual table capacity may be rounded to a
     * power of two.
     *
     * Initial capacity is not the same as the maximum number
     * of elements that can be stored without resizing.
     *
     *
     * ============================================================
     * 15. LOAD FACTOR
     * ============================================================
     *
     * Load factor controls how full the hash table is allowed
     * to become before resizing is triggered.
     *
     * Default load factor:
     *
     *     0.75
     *
     * Example:
     *
     *     Set<Integer> numbers = new HashSet<>(16, 0.75f);
     *
     * If table capacity is 16:
     *
     *     Threshold = 16 * 0.75
     *               = 12
     *
     * When the number of entries exceeds the threshold,
     * resizing is triggered in the usual OpenJDK HashMap
     * implementation.
     *
     * Why use a load factor?
     *
     * A very full table can cause more collisions.
     *
     * A lower load factor:
     * - Can reduce collisions.
     * - Uses more memory.
     *
     * A higher load factor:
     * - Can reduce memory usage.
     * - May increase collisions and lookup costs.
     *
     *
     * ============================================================
     * 16. RESIZING AND REHASHING
     * ============================================================
     *
     * Resizing means increasing the capacity of the internal
     * hash table when its threshold is exceeded.
     *
     * In common OpenJDK implementations, the capacity
     * generally doubles.
     *
     * Example:
     *
     *     Old capacity = 16
     *     New capacity = 32
     *
     * Why resize?
     *
     * To maintain efficient distribution of entries.
     *
     * During resizing, entries are redistributed according
     * to the new table capacity.
     *
     * This process is commonly called rehashing.
     *
     * Modern HashMap implementations optimize this process
     * and do not necessarily call hashCode() again for every
     * key.
     *
     * Resizing is relatively expensive, but it does not happen
     * on every insertion.
     *
     *
     * ============================================================
     * 17. TIME COMPLEXITY
     * ============================================================
     *
     * Operation             Expected time
     *
     * add()                 O(1)
     * remove()              O(1)
     * contains()            O(1)
     * size()                O(1)
     *
     * These are expected or typical performance characteristics
     * under good hash distribution.
     *
     * Worst-case behavior can be slower when many keys collide.
     *
     * Modern OpenJDK HashMap can use tree bins to improve
     * heavily collided bucket behavior.
     *
     * Do not interpret O(1) as a guarantee for every possible
     * input or every Java implementation.
     *
     *
     * ============================================================
     * 18. HASHSET AND NULL
     * ============================================================
     *
     * HashSet allows one null element.
     *
     * Example:
     *
     *     Set<String> names = new HashSet<>();
     *
     *     names.add(null);
     *     names.add("Java");
     *     names.add(null);
     *
     * The Set contains only one null.
     *
     * In the standard HashMap-backed implementation, null
     * is handled as a special key with a hash value of zero.
     *
     *
     * ============================================================
     * 19. ITERATION ORDER
     * ============================================================
     *
     * HashSet does not guarantee iteration order.
     *
     * Example:
     *
     *     Set<Integer> numbers = new HashSet<>();
     *
     *     numbers.add(10);
     *     numbers.add(20);
     *     numbers.add(30);
     *
     *     for (Integer number : numbers) {
     *         System.out.println(number);
     *     }
     *
     * Do not expect the output to follow insertion order.
     *
     * The observed order can depend on:
     * - Hash values.
     * - Table capacity.
     * - Resizing.
     * - Implementation details.
     *
     * If insertion order matters, use LinkedHashSet.
     *
     * If sorted order matters, use TreeSet.
     *
     *
     * ============================================================
     * 20. HASHSET IS NOT THREAD-SAFE
     * ============================================================
     *
     * HashSet is not synchronized by default.
     *
     * Concurrent modification from multiple threads without
     * appropriate coordination can cause incorrect behavior.
     *
     * For synchronized access:
     *
     *     Set<Integer> numbers =
     *         Collections.synchronizedSet(new HashSet<>());
     *
     * When iterating over a synchronized wrapper, synchronize
     * on the returned Set for the entire iteration:
     *
     *     synchronized (numbers) {
     *         for (Integer number : numbers) {
     *             System.out.println(number);
     *         }
     *     }
     *
     * Other options include concurrent Set implementations,
     * depending on the application's requirements.
     *
     *
     * ============================================================
     * 21. FAIL-FAST ITERATORS
     * ============================================================
     *
     * HashSet iterators are fail-fast on a best-effort basis.
     *
     * Example:
     *
     *     Set<Integer> numbers = new HashSet<>();
     *     numbers.add(10);
     *     numbers.add(20);
     *
     *     Iterator<Integer> iterator = numbers.iterator();
     *
     *     while (iterator.hasNext()) {
     *         Integer value = iterator.next();
     *
     *         numbers.add(30); // Structural modification
     *     }
     *
     * This may result in ConcurrentModificationException.
     *
     * Correct removal during iteration:
     *
     *     Iterator<Integer> iterator = numbers.iterator();
     *
     *     while (iterator.hasNext()) {
     *         Integer value = iterator.next();
     *
     *         if (value == 10) {
     *             iterator.remove();
     *         }
     *     }
     *
     * IMPORTANT:
     *
     * Fail-fast behavior is a bug-detection mechanism.
     * It is not a thread-safety guarantee.
     * Never rely on ConcurrentModificationException for
     * program correctness.
     *
     *
     * ============================================================
     * 22. HASHSET VS LINKEDHASHSET VS TREESET
     * ============================================================
     *
     * HashSet:
     * - Hash-based.
     * - No guaranteed iteration order.
     * - Expected O(1) basic operations.
     *
     * LinkedHashSet:
     * - Hash-based with linked ordering information.
     * - Maintains insertion order.
     * - Expected O(1) basic operations.
     * - Requires additional memory for ordering links.
     *
     * TreeSet:
     * - Tree-based.
     * - Maintains sorted order.
     * - Basic operations generally O(log n).
     *
     * Choose based on the ordering and performance
     * requirements of your application.
     *
     *
     * ============================================================
     * 23. HASHSET VS HASHMAP
     * ============================================================
     *
     * HashSet:
     * - Stores unique elements.
     * - Implements Set.
     * - Internally uses HashMap in standard OpenJDK.
     *
     * HashMap:
     * - Stores key-value pairs.
     * - Implements Map.
     * - Keys are unique.
     * - Values can be duplicated.
     *
     * Conceptually:
     *
     * HashSet:
     *
     *     element -> PRESENT
     *
     * HashMap:
     *
     *     key -> value
     *
     * HashSet uses the element as a HashMap key and a shared
     * dummy value internally.
     *
     *
     * ============================================================
     * 24. COMMON INTERVIEW QUESTIONS
     * ============================================================
     *
     * Q1. What is HashSet?
     * A hash-based implementation of the Set interface that
     * stores unique elements.
     *
     * Q2. How does HashSet work internally?
     * Standard OpenJDK HashSet uses a HashMap internally.
     *
     * Q3. How are duplicates prevented?
     * Through hash-based key lookup and equality checks.
     *
     * Q4. Can HashSet store null?
     * Yes, at most one null element.
     *
     * Q5. Does HashSet maintain insertion order?
     * No.
     *
     * Q6. Is HashSet thread-safe?
     * No.
     *
     * Q7. What is the default load factor?
     * 0.75 in standard OpenJDK HashMap-backed implementations.
     *
     * Q8. What is a collision?
     * When different keys map to the same bucket.
     *
     * Q9. What happens when the threshold is exceeded?
     * The hash table is resized.
     *
     * Q10. Why override equals() and hashCode()?
     * To define logical equality and consistent hash-based
     * duplicate detection for custom objects.
     *
     * Q11. What happens if equals() is overridden without
     *      hashCode()?
     * Equal objects may have different hash codes, violating
     * the contract and potentially allowing logical duplicates.
     *
     * Q12. What happens if two unequal objects have the same
     *      hash code?
     * They can still coexist. A collision does not mean equality.
     *
     * Q13. What is treeification?
     * Converting a heavily populated hash bucket into a tree
     * structure in applicable OpenJDK HashMap implementations.
     *
     * Q14. What is the expected time complexity of contains()?
     * O(1), assuming good hash distribution.
     *
     * Q15. Can HashSet store custom objects?
     * Yes. Correct equals() and hashCode() implementations
     * are important when logical equality is required.
     *
     *
     * ============================================================
     * 25. FINAL UNDERSTANDING
     * ============================================================
     *
     * Remember HashSet through this flow:
     *
     *     Element
     *        |
     *        v
     *     hashCode()
     *        |
     *        v
     *     Hash spreading
     *        |
     *        v
     *     Bucket selection
     *        |
     *        v
     *     Collision handling
     *        |
     *        v
     *     equals() comparison
     *        |
     *        v
     *     Insert only if not already present
     *
     * HashSet is useful when:
     *
     * - Uniqueness is required.
     * - Fast membership checks are important.
     * - Ordering is not required.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about HashSet , Happy Learning ..!");
    }
}
