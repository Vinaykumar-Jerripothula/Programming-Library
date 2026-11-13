
public class HashingAndHashTables {

    /*
     * ============================================================
     * HASHING AND HASH TABLES IN JAVA
     * ============================================================
     *
     * 1. WHY DO WE NEED HASHING?
     *
     * Imagine an ArrayList containing 1 million employee records.
     *
     * You want to find an employee using employee ID 500123.
     *
     * With a normal linear search, Java may need to check
     * every element.
     *
     * Worst-case time complexity: O(n).
     *
     * What if we could directly calculate where the employee
     * should be stored?
     *
     * This is the idea behind HASHING.
     *
     * Hashing helps us locate data efficiently by converting
     * a key into a numeric hash value.
     *
     * Common Java collections that use hashing:
     *
     * - HashMap
     * - HashSet
     * - LinkedHashMap
     * - LinkedHashSet
     * - Hashtable
     * - ConcurrentHashMap
     *
     *
     * ============================================================
     * 2. WHAT IS HASHING?
     * ============================================================
     *
     * Hashing is the process of converting input data into
     * a numeric value using a hash function.
     *
     * Example:
     *
     * Input: "Vinay"
     *
     * Hash function:
     *     hash("Vinay") -> some integer
     *
     * That integer is used to help determine where the data
     * should be stored in a hash table.
     *
     * Think of a library:
     *
     * Instead of checking every shelf for a book, you calculate
     * which shelf should contain it.
     *
     * A hash function helps determine the storage location.
     *
     *
     * ============================================================
     * 3. WHAT IS A HASH FUNCTION?
     * ============================================================
     *
     * A hash function accepts an input and produces a hash value.
     *
     * Conceptually:
     *
     * Input -> Hash Function -> Hash Value
     *
     * Example:
     *
     * hash("Java") -> 2301506
     *
     * The exact value depends on the hash function.
     *
     * A good hash function should:
     *
     * - Be deterministic for the same input.
     * - Distribute values reasonably across hash values.
     * - Be efficient to calculate.
     * - Reduce collisions as much as possible.
     *
     * IMPORTANT:
     *
     * A hash function cannot guarantee that every different
     * input produces a different hash value.
     *
     * Different inputs can produce the same hash.
     *
     *
     * ============================================================
     * 4. WHAT IS A HASH CODE IN JAVA?
     * ============================================================
     *
     * Java provides the hashCode() method in java.lang.Object.
     *
     * Method:
     *
     * public int hashCode()
     *
     * Every Java object inherits this method.
     *
     * Example:
     *
     * String name = "Vinay";
     *
     * int hash = name.hashCode();
     *
     * System.out.println(hash);
     *
     * hashCode() returns an int.
     *
     * IMPORTANT:
     *
     * hashCode() is not the object's memory address.
     *
     * The default implementation is not required to expose
     * an object's physical memory location.
     *
     * The JVM and class implementation determine the behavior.
     *
     *
     * ============================================================
     * 5. EQUALS() AND HASHCODE() CONTRACT
     * ============================================================
     *
     * This is extremely important for HashMap and HashSet.
     *
     * Java expects the following contract:
     *
     * RULE 1:
     *
     * If two objects are equal according to equals(),
     * they MUST have the same hashCode().
     *
     * If:
     *
     * a.equals(b) == true
     *
     * Then:
     *
     * a.hashCode() == b.hashCode()
     *
     * RULE 2:
     *
     * If two objects have the same hashCode(),
     * they do NOT necessarily have to be equal.
     *
     * If:
     *
     * a.hashCode() == b.hashCode()
     *
     * It does not guarantee:
     *
     * a.equals(b) == true
     *
     * RULE 3:
     *
     * During one application execution, repeated hashCode()
     * calls should return the same value as long as information
     * used by equals() has not changed.
     *
     * RULE 4:
     *
     * equals() should satisfy:
     *
     * - Reflexive
     * - Symmetric
     * - Transitive
     * - Consistent
     * - Non-null comparison returns false
     *
     * IMPORTANT:
     *
     * Always override hashCode() when overriding equals()
     * for value-based equality.
     *
     *
     * ============================================================
     * 6. WHY DO WE NEED BOTH EQUALS() AND HASHCODE()?
     * ============================================================
     *
     * Imagine two Student objects:
     *
     * Student s1 = new Student(101, "Vinay");
     * Student s2 = new Student(101, "Vinay");
     *
     * They represent the same logical student.
     *
     * equals() checks logical equality.
     *
     * hashCode() helps determine the hash-table location.
     *
     * Hash-based collections use both concepts.
     *
     * Simplified lookup:
     *
     * 1. Calculate the key's hash.
     * 2. Locate the corresponding bucket.
     * 3. Check candidate entries.
     * 4. Compare hash values.
     * 5. Use equals() to identify the matching key.
     *
     * Therefore:
     *
     * hashCode() helps locate.
     * equals() helps identify.
     *
     *
     * ============================================================
     * 7. WHAT IS A HASH TABLE?
     * ============================================================
     *
     * A Hash Table is a data structure that stores data using
     * a hash function.
     *
     * It generally uses an array of buckets.
     *
     * Example:
     *
     * Bucket 0 -> Entry
     * Bucket 1 -> Entry
     * Bucket 2 -> Entry
     * Bucket 3 -> Entry
     * Bucket 4 -> Entry
     *
     * A hash function determines which bucket should receive
     * an element.
     *
     * A hash table allows efficient:
     *
     * - Insertion
     * - Searching
     * - Deletion
     *
     * Expected average complexity is O(1) under suitable
     * hash distribution.
     *
     *
     * ============================================================
     * 8. WHAT IS A BUCKET?
     * ============================================================
     *
     * A bucket is a position or slot in the hash table's
     * internal storage array.
     *
     * Example:
     *
     * Index       Bucket
     * -----       ------
     *   0         Entry A
     *   1         null
     *   2         Entry B
     *   3         Entry C
     *   4         null
     *
     * Multiple entries can map to the same bucket.
     *
     * This situation is called a collision.
     *
     *
     * ============================================================
     * 9. HASH CODE VS BUCKET INDEX
     * ============================================================
     *
     * These are NOT the same thing.
     *
     * HASH CODE:
     * Integer value produced by hashCode().
     *
     * BUCKET INDEX:
     * Position calculated from the hash and table capacity.
     *
     * Conceptual example:
     *
     * hashCode = 123456
     * capacity = 16
     *
     * bucketIndex = hashCode % capacity
     *
     * bucketIndex = 0
     *
     * Java HashMap uses a different optimized calculation
     * based on a power-of-two table capacity.
     *
     * In OpenJDK implementations, the index calculation
     * is conceptually:
     *
     * index = (capacity - 1) & hash
     *
     * The hash is first spread using additional bit operations.
     *
     *
     * ============================================================
     * 10. WHAT IS A HASH COLLISION?
     * ============================================================
     *
     * A collision occurs when different keys map to the same
     * bucket.
     *
     * Example:
     *
     * hash("A") -> bucket 2
     * hash("B") -> bucket 2
     *
     * Both keys need the same bucket.
     *
     * Collisions are normal in hash tables.
     *
     * WHY?
     *
     * The number of possible inputs is much larger than the
     * number of available buckets.
     *
     * Therefore, different inputs can map to the same bucket.
     *
     * A good hash table implementation must handle collisions.
     *
     *
     * ============================================================
     * 11. COLLISION HANDLING - SEPARATE CHAINING
     * ============================================================
     *
     * One common collision-handling technique is separate chaining.
     *
     * Each bucket can hold a collection of entries.
     *
     * Conceptual structure:
     *
     * Bucket 0 -> null
     *
     * Bucket 1 -> Entry A -> Entry B -> Entry C
     *
     * Bucket 2 -> Entry D
     *
     * Bucket 3 -> null
     *
     * When multiple entries map to the same bucket,
     * they are connected within that bucket.
     *
     * Searching:
     *
     * 1. Calculate the bucket.
     * 2. Visit entries in that bucket.
     * 3. Compare hash values.
     * 4. Use equals() to find the correct key.
     *
     * Java HashMap uses this general approach, with tree-based
     * bins in certain collision-heavy situations.
     *
     *
     * ============================================================
     * 12. COLLISION HANDLING - OPEN ADDRESSING
     * ============================================================
     *
     * Another technique is Open Addressing.
     *
     * All entries are stored directly in the table array.
     *
     * When a collision occurs, the implementation searches
     * for another available slot.
     *
     * Common techniques:
     *
     * 1. Linear Probing.
     * 2. Quadratic Probing.
     * 3. Double Hashing.
     *
     * LINEAR PROBING:
     *
     * If the calculated bucket is occupied, check the next slot.
     *
     * Example:
     *
     * Initial bucket = 3
     *
     * Bucket 3 occupied.
     * Check bucket 4.
     * If occupied, check bucket 5.
     *
     * QUADRATIC PROBING:
     *
     * Checks positions using increasing quadratic offsets.
     *
     * DOUBLE HASHING:
     *
     * Uses a second hash function to determine the probe step.
     *
     * IMPORTANT:
     *
     * Standard Java HashMap uses bucket-based chaining,
     * not open addressing.
     *
     * Open addressing is a general hash-table technique.
     *
     *
     * ============================================================
     * 13. WHAT IS LOAD FACTOR?
     * ============================================================
     *
     * Load factor indicates how full a hash table is allowed
     * to become before resizing is triggered.
     *
     * Formula:
     *
     * Load Factor = Number of Entries / Table Capacity
     *
     * Example:
     *
     * Number of entries = 12
     * Capacity = 16
     *
     * Load factor = 12 / 16 = 0.75
     *
     * A high load factor:
     *
     * - Uses less table space.
     * - Can increase collisions.
     *
     * A low load factor:
     *
     * - Uses more table space.
     * - Can reduce collisions.
     *
     *
     * ============================================================
     * 14. LOAD FACTOR IN HASHMAP
     * ============================================================
     *
     * Default HashMap load factor:
     *
     * 0.75
     *
     * Example:
     *
     * HashMap<Integer, String> map = new HashMap<>();
     *
     * Conceptually:
     *
     * Initial capacity = 16
     * Load factor = 0.75
     *
     * Resize threshold:
     *
     * 16 * 0.75 = 12
     *
     * When the number of entries exceeds the threshold,
     * resizing is triggered.
     *
     * IMPORTANT:
     *
     * The default initial capacity and load factor are
     * implementation defaults, not universal rules for
     * every hash-table implementation.
     *
     *
     * ============================================================
     * 15. WHAT IS RESIZING OR REHASHING?
     * ============================================================
     *
     * As more entries are inserted, the table may become
     * too crowded.
     *
     * The implementation increases the table capacity.
     *
     * Example:
     *
     * Old capacity = 16
     * New capacity = 32
     *
     * Entries are redistributed according to the new capacity.
     *
     * This process is commonly called resizing or rehashing.
     *
     * IMPORTANT:
     *
     * In Java HashMap, resizing generally redistributes
     * existing nodes into the new table.
     *
     * It does not necessarily call each key's hashCode()
     * method again.
     *
     * The stored hash values are reused.
     *
     *
     * ============================================================
     * 16. HASHMAP INTERNAL HASH SPREADING
     * ============================================================
     *
     * In modern OpenJDK HashMap implementations, the hash
     * calculation spreads higher bits into lower bits.
     *
     * Simplified implementation:
     *
     * static final int hash(Object key) {
     *
     *     int h;
     *
     *     return (key == null)
     *         ? 0
     *         : (h = key.hashCode()) ^ (h >>> 16);
     * }
     *
     * WHY?
     *
     * The bucket index calculation uses a mask derived from
     * the table capacity.
     *
     * Mixing high bits into low bits can improve distribution
     * when calculating bucket indexes.
     *
     * IMPORTANT:
     *
     * This is an implementation detail of modern OpenJDK
     * HashMap, not a requirement for every Map implementation.
     *
     *
     * ============================================================
     * 17. WHY HASHMAP CAPACITY IS A POWER OF TWO
     * ============================================================
     *
     * Modern OpenJDK HashMap uses table capacities that are
     * powers of two.
     *
     * Examples:
     *
     * 16, 32, 64, 128, 256 ...
     *
     * Bucket calculation:
     *
     * index = (capacity - 1) & hash
     *
     * When capacity is a power of two, this bit-mask operation
     * efficiently calculates an index within the table.
     *
     * Example:
     *
     * capacity = 16
     * capacity - 1 = 15
     *
     * 15 in binary:
     * 00001111
     *
     * Applying bitwise AND selects the lower bits.
     *
     *
     * ============================================================
     * 18. HASHMAP TREEIFICATION
     * ============================================================
     *
     * What if too many entries collide in the same bucket?
     *
     * A long linked chain can make searching slower.
     *
     * Modern OpenJDK HashMap can convert a heavily populated
     * bucket into a Red-Black Tree.
     *
     * This process is called TREEIFICATION.
     *
     * Important OpenJDK implementation constants:
     *
     * TREEIFY_THRESHOLD = 8
     *
     * UNTREEIFY_THRESHOLD = 6
     *
     * MIN_TREEIFY_CAPACITY = 64
     *
     * Meaning:
     *
     * - Treeification is considered when a bucket becomes
     *   sufficiently populated.
     * - If the table is too small, resizing is preferred.
     * - Tree bins can later become ordinary linked bins.
     *
     * IMPORTANT:
     *
     * These are implementation details, not guaranteed API
     * constants.
     *
     * The exact transition also depends on insertion and
     * resizing circumstances.
     *
     *
     * ============================================================
     * 19. HASHMAP COMPLEXITY
     * ============================================================
     *
     * Typical expected performance with good hash distribution:
     *
     * put()        -> O(1)
     * get()        -> O(1)
     * remove()     -> O(1)
     * containsKey()-> O(1)
     *
     * Worst-case behavior:
     *
     * A heavily collided linked bin can require O(n) work.
     *
     * Tree bins can provide O(log n) searching under suitable
     * ordering and implementation conditions.
     *
     * IMPORTANT:
     *
     * O(1) is expected average performance, not a guarantee
     * for every individual operation.
     *
     *
     * ============================================================
     * 20. HOW HASHMAP PUT() WORKS
     * ============================================================
     *
     * Consider:
     *
     * map.put("Java", 100);
     *
     * Conceptual steps:
     *
     * 1. Check whether the key is null.
     *
     * 2. Calculate the key's hash code.
     *
     * 3. Spread the hash bits.
     *
     * 4. Calculate the bucket index.
     *
     * 5. Check whether the bucket is empty.
     *
     * 6. If empty, insert the new entry.
     *
     * 7. If occupied, compare existing entries.
     *
     * 8. If an equal key exists, replace its value.
     *
     * 9. Otherwise, add another entry to the bucket.
     *
     * 10. Check whether resizing is required.
     *
     * IMPORTANT:
     *
     * HashMap does not allow duplicate keys according to
     * its key equality rules.
     *
     *
     * ============================================================
     * 21. HOW HASHMAP GET() WORKS
     * ============================================================
     *
     * Consider:
     *
     * map.get("Java");
     *
     * Conceptual steps:
     *
     * 1. Calculate the key's hash.
     *
     * 2. Spread the hash bits.
     *
     * 3. Calculate the bucket index.
     *
     * 4. Examine entries in that bucket.
     *
     * 5. Compare stored hash values.
     *
     * 6. Use equals() to identify the matching key.
     *
     * 7. Return the associated value.
     *
     * If no matching key exists, return null.
     *
     *
     * ============================================================
     * 22. WHY MUST HASHCODE() AND EQUALS() BE OVERRIDDEN TOGETHER?
     * ============================================================
     *
     * Consider:
     *
     * class Student {
     *     int id;
     *
     *     Student(int id) {
     *         this.id = id;
     *     }
     *
     *     public boolean equals(Object obj) {
     *
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
     * Student s1 = new Student(101);
     * Student s2 = new Student(101);
     *
     * s1.equals(s2) -> true
     *
     * But if hashCode() is not overridden, the inherited
     * implementation may produce different hash values.
     *
     * A HashMap may then search a different bucket for s2.
     *
     * This violates the equals/hashCode contract.
     *
     * CORRECT APPROACH:
     *
     * @Override
     * public int hashCode() {
     *     return Integer.hashCode(id);
     * }
     *
     * Now equal Student objects have the same hash code.
     *
     *
     * ============================================================
     * 23. MUTABLE KEYS - A DANGEROUS MISTAKE
     * ============================================================
     *
     * Consider:
     *
     * class Employee {
     *     int id;
     *
     *     Employee(int id) {
     *         this.id = id;
     *     }
     *
     *     public boolean equals(Object obj) {
     *         if (this == obj) return true;
     *         if (!(obj instanceof Employee)) return false;
     *
     *         Employee other = (Employee) obj;
     *         return this.id == other.id;
     *     }
     *
     *     public int hashCode() {
     *         return Integer.hashCode(id);
     *     }
     * }
     *
     * Employee employee = new Employee(101);
     *
     * Map<Employee, String> map = new HashMap<>();
     *
     * map.put(employee, "Developer");
     *
     * employee.id = 202;
     *
     * map.get(employee);
     *
     * The lookup may fail.
     *
     * WHY?
     *
     * The entry was placed using the original hash value.
     *
     * After changing the key's ID, its hash code changes.
     *
     * Lookup calculates a different hash and may search
     * a different bucket.
     *
     * BEST PRACTICE:
     *
     * Use immutable objects as HashMap keys whenever possible.
     *
     *
     * ============================================================
     * 24. HASHSET INTERNAL WORKING
     * ============================================================
     *
     * HashSet internally uses a HashMap in modern OpenJDK.
     *
     * Conceptually:
     *
     * HashSet:
     *     |
     *     v
     * HashMap
     *     |
     *     +---- Element stored as key.
     *     +---- Dummy constant stored as value.
     *
     * Example:
     *
     * HashSet<Integer> set = new HashSet<>();
     *
     * set.add(10);
     * set.add(20);
     *
     * Internally, the elements act as map keys.
     *
     * WHY?
     *
     * HashMap already provides efficient key uniqueness
     * and lookup.
     *
     * HashSet reuses that behavior.
     *
     * IMPORTANT:
     *
     * This describes the usual OpenJDK implementation,
     * not the public Set API contract.
     *
     *
     * ============================================================
     * 25. LINKEDHASHMAP AND LINKEDHASHSET
     * ============================================================
     *
     * LinkedHashMap extends HashMap.
     *
     * It maintains a linked ordering of entries in addition
     * to hash-table storage.
     *
     * It can maintain:
     *
     * - Insertion order.
     * - Access order.
     *
     * LinkedHashSet maintains insertion order.
     *
     * They combine hashing with linked ordering.
     *
     * IMPORTANT:
     *
     * Linked ordering does not replace hashing.
     *
     * Hashing is still used for efficient lookup.
     *
     *
     * ============================================================
     * 26. HASHTABLE
     * ============================================================
     *
     * Hashtable is a legacy synchronized Map implementation.
     *
     * It uses a hash-table structure.
     *
     * Important properties:
     *
     * - Synchronized methods.
     * - Does not permit null keys.
     * - Does not permit null values.
     * - Legacy class.
     *
     * In modern applications, ConcurrentHashMap is generally
     * preferred for concurrent access.
     *
     *
     * ============================================================
     * 27. HASHING AND SECURITY
     * ============================================================
     *
     * Hashing is used in many areas:
     *
     * - HashMap and HashSet.
     * - Hash-based indexing.
     * - Caches.
     * - Data retrieval.
     * - Checksums.
     * - Cryptographic systems.
     *
     * IMPORTANT:
     *
     * Java's ordinary hashCode() is NOT a cryptographic hash.
     *
     * Do not use hashCode() for:
     *
     * - Password storage.
     * - Digital signatures.
     * - Security-sensitive integrity checks.
     *
     * Passwords should be stored using dedicated password
     * hashing algorithms such as Argon2id, bcrypt or PBKDF2,
     * with appropriate salts and parameters.
     *
     * Cryptographic digest algorithms such as SHA-256 serve
     * different purposes from ordinary hashCode().
     *
     *
     * ============================================================
     * 28. HASHING VS ENCRYPTION
     * ============================================================
     *
     * Hashing:
     *
     * - One-way transformation in the general cryptographic sense.
     * - Does not provide a decryption operation.
     * - Used for lookup and integrity-related applications,
     *   depending on the hash function.
     *
     * Encryption:
     *
     * - Transforms data using a key.
     * - Designed to be reversible by authorized decryption.
     *
     * IMPORTANT:
     *
     * Java hashCode() is not secure cryptographic hashing.
     *
     * The word "hashing" is used in both data structures
     * and cryptography, but the requirements differ.
     *
     *
     * ============================================================
     * 29. COMMON HASHING MISTAKES
     * ============================================================
     *
     * 1. Assuming equal hash codes mean equal objects.
     *
     * 2. Overriding equals() without overriding hashCode().
     *
     * 3. Assuming hashCode() is a memory address.
     *
     * 4. Assuming HashMap always takes O(1).
     *
     * 5. Assuming collisions are programming errors.
     *
     * 6. Modifying fields used in hashCode() after inserting
     *    an object as a HashMap key.
     *
     * 7. Confusing hash code with bucket index.
     *
     * 8. Assuming Java HashMap uses open addressing.
     *
     * 9. Assuming every bucket is always a linked list.
     *
     * 10. Treating ordinary hashCode() as a cryptographic hash.
     *
     * 11. Assuming load factor is the percentage of free space.
     *
     * 12. Assuming resizing happens after every insertion.
     *
     *
     * ============================================================
     * 30. IMPORTANT INTERVIEW QUESTIONS
     * ============================================================
     *
     * 1. What is hashing?
     *
     * 2. What is a hash function?
     *
     * 3. What is hashCode() in Java?
     *
     * 4. Difference between hashCode() and equals()?
     *
     * 5. Explain the equals/hashCode contract.
     *
     * 6. What is a hash table?
     *
     * 7. What is a bucket?
     *
     * 8. What is a hash collision?
     *
     * 9. How does Java HashMap handle collisions?
     *
     * 10. What is separate chaining?
     *
     * 11. What is open addressing?
     *
     * 12. What is load factor?
     *
     * 13. What is the default HashMap load factor?
     *
     * 14. What happens when HashMap resizes?
     *
     * 15. Why does HashMap use power-of-two capacities?
     *
     * 16. What is hash spreading?
     *
     * 17. What is treeification?
     *
     * 18. What are TREEIFY_THRESHOLD and MIN_TREEIFY_CAPACITY?
     *
     * 19. What happens when a mutable object is used as a key?
     *
     * 20. How does HashSet work internally?
     *
     * 21. Why does HashMap provide expected O(1) operations?
     *
     * 22. Difference between hashing and encryption?
     *
     * 23. Is hashCode() suitable for password storage?
     *
     *
     * ============================================================
     * 31. QUICK REVISION
     * ============================================================
     *
     * Hashing:
     *     -> Converts input into a hash value.
     *
     * hashCode():
     *     -> Java method returning an int.
     *
     * equals():
     *     -> Checks logical equality.
     *
     * Hash Table:
     *     -> Stores data using hashing.
     *
     * Bucket:
     *     -> Storage position in the table.
     *
     * Collision:
     *     -> Different keys map to the same bucket.
     *
     * Separate Chaining:
     *     -> Multiple entries share a bucket structure.
     *
     * Open Addressing:
     *     -> Finds another table slot after collision.
     *
     * Load Factor:
     *     -> Number of entries / capacity.
     *
     * HashMap default load factor:
     *     -> 0.75.
     *
     * HashMap resizing:
     *     -> Increases table capacity and redistributes entries.
     *
     * Treeification:
     *     -> Converts a heavily populated bucket into a tree bin
     *        under suitable implementation conditions.
     *
     * HashSet:
     *     -> Uses HashMap internally in OpenJDK.
     *
     * Mutable keys:
     *     -> Can make hash-based lookups unreliable.
     *
     * Expected HashMap lookup:
     *     -> O(1), with suitable hash distribution.
     *
     * ============================================================
     * END OF HASHING AND HASH TABLES
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Hashing and Hash Tables , Happy Learning ..!");
    }
}
