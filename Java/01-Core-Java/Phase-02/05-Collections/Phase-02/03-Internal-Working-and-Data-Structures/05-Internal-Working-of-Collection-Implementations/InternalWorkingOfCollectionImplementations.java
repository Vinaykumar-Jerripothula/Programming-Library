
public class InternalWorkingOfCollectionImplementations {

    /*
     * ============================================================
     * INTERNAL WORKING OF JAVA COLLECTION IMPLEMENTATIONS
     * ============================================================
     *
     * 1. WHY SHOULD WE UNDERSTAND INTERNAL WORKING?
     *
     * Consider:
     *
     * ArrayList<Integer> list = new ArrayList<>();
     *
     * list.add(10);
     * list.add(20);
     * list.add(30);
     *
     * We know how to use add().
     *
     * But what happens internally?
     *
     * - Where are these elements stored?
     * - How does ArrayList find an element using get(index)?
     * - What happens when its internal array becomes full?
     * - Why is insertion at the beginning expensive?
     *
     * Similarly:
     *
     * HashMap<String, Integer> map = new HashMap<>();
     *
     * map.put("Java", 10);
     *
     * How does HashMap decide where to store "Java"?
     *
     * How does it find the value later?
     *
     * Understanding internals helps us:
     *
     * - Choose the right collection.
     * - Understand time complexity.
     * - Debug performance problems.
     * - Avoid inefficient operations.
     * - Answer collection-related interview questions.
     *
     *
     * ============================================================
     * 2. IMPORTANT: API CONTRACT VS IMPLEMENTATION
     * ============================================================
     *
     * Java Collections Framework provides interfaces and
     * implementations.
     *
     * Example:
     *
     * List<Integer> list = new ArrayList<>();
     *
     * List:
     * Defines the API contract.
     *
     * ArrayList:
     * Provides a particular implementation.
     *
     * The Java API documentation defines guaranteed behavior.
     *
     * Internal implementation details can change between
     * Java versions or JDK implementations.
     *
     * IMPORTANT:
     *
     * Do not confuse:
     *
     * API guarantee:
     * What Java promises to applications.
     *
     * Implementation detail:
     * How a particular JDK version achieves that behavior.
     *
     * This file describes common OpenJDK implementation
     * strategies. Exact internal details should be checked
     * against the JDK version being studied.
     *
     *
     * ============================================================
     * 3. ARRAYLIST INTERNAL WORKING
     * ============================================================
     *
     * ArrayList is a resizable-array implementation of List.
     *
     * Internally, it uses an array to store references
     * to elements.
     *
     * Conceptual representation:
     *
     * Object[] elementData;
     *
     * Example:
     *
     * ArrayList<Integer> list = new ArrayList<>();
     *
     * list.add(10);
     * list.add(20);
     * list.add(30);
     *
     * Internal logical storage:
     *
     * [10, 20, 30, null, null, ...]
     *
     * The array's length is its capacity.
     *
     * The number of stored elements is its size.
     *
     * SIZE != CAPACITY
     *
     * Size:
     * Number of elements currently stored.
     *
     * Capacity:
     * Number of elements the internal array can hold
     * before it needs to grow.
     *
     *
     * ============================================================
     * 4. ARRAYLIST - ADD OPERATION
     * ============================================================
     *
     * list.add(40);
     *
     * Internally:
     *
     * 1. Check whether the internal array has enough capacity.
     * 2. If space exists, place the element at the next position.
     * 3. Increase the logical size.
     *
     * If the array is full:
     *
     * 1. Allocate a larger array.
     * 2. Copy existing element references.
     * 3. Insert the new element.
     * 4. Use the new array as internal storage.
     *
     * This is called dynamic array resizing.
     *
     *
     * ============================================================
     * 5. ARRAYLIST - CAPACITY GROWTH
     * ============================================================
     *
     * In common OpenJDK implementations, ArrayList grows
     * approximately by 1.5 times its old capacity.
     *
     * Conceptual formula:
     *
     * newCapacity = oldCapacity + (oldCapacity / 2)
     *
     * Example:
     *
     * Old capacity = 10
     *
     * New capacity = 10 + 5
     *
     * New capacity = 15
     *
     * IMPORTANT:
     *
     * This growth strategy is an implementation detail,
     * not a universal List interface guarantee.
     *
     * ArrayList also handles minimum required capacity
     * and large-array limits.
     *
     *
     * ============================================================
     * 6. ARRAYLIST - GET OPERATION
     * ============================================================
     *
     * list.get(2);
     *
     * ArrayList directly accesses the internal array index.
     *
     * Conceptually:
     *
     * return elementData[2];
     *
     * No traversal is required.
     *
     * Time Complexity: O(1).
     *
     *
     * ============================================================
     * 7. ARRAYLIST - INSERTION AT AN INDEX
     * ============================================================
     *
     * list.add(1, 50);
     *
     * Existing:
     *
     * [10, 20, 30, 40]
     *
     * Insert 50 at index 1:
     *
     * [10, 50, 20, 30, 40]
     *
     * Elements from index 1 onward must shift right.
     *
     * Therefore, insertion in the middle or beginning
     * can require O(n) work.
     *
     * Insertion at the end:
     *
     * Amortized O(1).
     *
     * Insertion at an arbitrary index:
     *
     * O(n) in general.
     *
     *
     * ============================================================
     * 8. ARRAYLIST - REMOVE OPERATION
     * ============================================================
     *
     * list.remove(1);
     *
     * Existing:
     *
     * [10, 20, 30, 40]
     *
     * After removing index 1:
     *
     * [10, 30, 40]
     *
     * Elements after the removed position shift left.
     *
     * Time Complexity:
     *
     * Remove last element: O(1).
     *
     * Remove at an arbitrary index: O(n) in general.
     *
     * Removed positions are cleared to avoid retaining
     * unnecessary object references.
     *
     *
     * ============================================================
     * 9. LINKEDLIST INTERNAL WORKING
     * ============================================================
     *
     * LinkedList is a doubly linked list implementation
     * of List and Deque.
     *
     * Each node conceptually stores:
     *
     * - Element.
     * - Reference to previous node.
     * - Reference to next node.
     *
     * Conceptual Node:
     *
     * class Node<E> {
     *     E item;
     *     Node<E> next;
     *     Node<E> prev;
     * }
     *
     * Example:
     *
     * null <- 10 <-> 20 <-> 30 -> null
     *
     * LinkedList maintains references to:
     *
     * - First node.
     * - Last node.
     *
     *
     * ============================================================
     * 10. LINKEDLIST - ADD AND REMOVE
     * ============================================================
     *
     * Adding at the beginning:
     *
     * A new node is connected before the current first node.
     *
     * Adding at the end:
     *
     * A new node is connected after the current last node.
     *
     * Removing the first or last node:
     *
     * References are updated to bypass the removed node.
     *
     * These operations are O(1) when the required node
     * is already known.
     *
     * IMPORTANT:
     *
     * LinkedList does not provide direct indexed access.
     *
     * get(index) requires traversal from the nearer end.
     *
     * Indexed access is O(n) in the general case.
     *
     *
     * ============================================================
     * 11. ARRAYLIST VS LINKEDLIST INTERNALS
     * ============================================================
     *
     * ArrayList:
     *
     * - Resizable array.
     * - Direct indexed access.
     * - Elements stored in contiguous array positions.
     * - Insertions in the middle require shifting references.
     * - Less per-element structural overhead.
     *
     * LinkedList:
     *
     * - Doubly linked nodes.
     * - No direct indexed access.
     * - Each node stores previous and next references.
     * - Insertion/removal is efficient at known positions.
     * - More memory overhead per element.
     *
     * IMPORTANT:
     *
     * LinkedList is not automatically faster for insertion.
     *
     * Finding the insertion position may itself take O(n).
     *
     * For many ordinary application workloads, ArrayList
     * is a better default choice.
     *
     *
     * ============================================================
     * 12. VECTOR AND STACK INTERNALS
     * ============================================================
     *
     * Vector:
     *
     * - Resizable-array implementation.
     * - Similar basic storage idea to ArrayList.
     * - Individual methods are synchronized.
     *
     * Stack:
     *
     * - Extends Vector.
     * - Uses the Vector storage mechanism.
     * - Provides legacy stack operations.
     *
     * Modern recommendation:
     *
     * Use ArrayDeque for most ordinary stack and queue
     * requirements.
     *
     * Use concurrent collections or explicit synchronization
     * when thread safety is required.
     *
     *
     * ============================================================
     * 13. HASHMAP INTERNAL WORKING
     * ============================================================
     *
     * HashMap stores key-value pairs using hashing.
     *
     * It uses a hash table.
     *
     * Conceptual structure:
     *
     * Bucket 0 -> Entry
     * Bucket 1 -> null
     * Bucket 2 -> Entry -> Entry
     * Bucket 3 -> null
     *
     * Each bucket can contain one or more entries.
     *
     * In common OpenJDK implementations, the table is
     * an array of nodes.
     *
     * Each node conceptually contains:
     *
     * - hash.
     * - key.
     * - value.
     * - next reference.
     *
     *
     * ============================================================
     * 14. HASHMAP - PUT OPERATION
     * ============================================================
     *
     * map.put(key, value);
     *
     * Conceptual steps:
     *
     * 1. Calculate the key's hashCode().
     *
     * 2. Apply hash spreading.
     *
     * 3. Calculate the bucket index.
     *
     * 4. Check the bucket.
     *
     * 5. If the bucket is empty, insert a new entry.
     *
     * 6. If entries already exist, compare hashes and keys.
     *
     * 7. If an equal key exists, replace its value.
     *
     * 8. Otherwise, add a new entry to the bucket.
     *
     * 9. Check whether resizing is needed.
     *
     *
     * ============================================================
     * 15. HASHING AND BUCKET INDEX
     * ============================================================
     *
     * Hashing converts a key into an integer hash value.
     *
     * A hash table uses this value to determine where
     * the entry should be stored.
     *
     * In common OpenJDK HashMap implementations, the hash
     * is spread using its higher bits.
     *
     * Conceptual hash spreading:
     *
     * h ^ (h >>> 16)
     *
     * Bucket index:
     *
     * (tableLength - 1) & hash
     *
     * This index calculation works with a power-of-two
     * table length.
     *
     * IMPORTANT:
     *
     * hashCode() does not directly represent the array index.
     *
     * HashMap transforms the hash before selecting a bucket.
     *
     *
     * ============================================================
     * 16. HASHMAP - COLLISIONS
     * ============================================================
     *
     * A collision occurs when different keys map to
     * the same bucket.
     *
     * Example:
     *
     * Key A -> Bucket 2
     * Key B -> Bucket 2
     *
     * Both entries need to be stored in the same bucket.
     *
     * HashMap handles collisions using bucket structures.
     *
     * In modern OpenJDK implementations:
     *
     * - Small collision groups use linked nodes.
     * - Large collision groups may be converted into
     *   balanced tree bins when conditions are met.
     *
     * This improves performance in heavily collided buckets.
     *
     *
     * ============================================================
     * 17. HASHMAP - TREEIFICATION
     * ============================================================
     *
     * Common OpenJDK constants:
     *
     * TREEIFY_THRESHOLD = 8
     *
     * UNTREEIFY_THRESHOLD = 6
     *
     * MIN_TREEIFY_CAPACITY = 64
     *
     * When a bucket becomes sufficiently large, HashMap may
     * convert its linked structure into a tree bin.
     *
     * The table must also be sufficiently large.
     *
     * Otherwise, HashMap may resize instead.
     *
     * Tree bins use red-black tree structures.
     *
     * IMPORTANT:
     *
     * These are implementation details, not HashMap API
     * guarantees.
     *
     *
     * ============================================================
     * 18. HASHMAP - GET OPERATION
     * ============================================================
     *
     * map.get(key);
     *
     * Conceptual steps:
     *
     * 1. Calculate hash.
     * 2. Find bucket index.
     * 3. Inspect the first node.
     * 4. Compare hash and key equality.
     * 5. Search the remaining bucket structure if required.
     * 6. Return the matching value.
     *
     * HashMap uses hashCode() to locate a bucket and
     * equals() to identify the matching key.
     *
     * Expected complexity:
     *
     * O(1) under suitable hash distribution.
     *
     * Worst-case behavior depends on collisions and
     * the bucket structure.
     *
     *
     * ============================================================
     * 19. HASHMAP - EQUALS AND HASHCODE CONTRACT
     * ============================================================
     *
     * For keys used in HashMap:
     *
     * If:
     *
     * a.equals(b) == true
     *
     * Then:
     *
     * a.hashCode() == b.hashCode()
     *
     * The reverse is not required.
     *
     * Two unequal objects may have the same hash code.
     *
     * That situation is a hash collision.
     *
     * If equals() is overridden without correctly overriding
     * hashCode(), HashMap behavior may become surprising.
     *
     * BEST PRACTICE:
     *
     * Override equals() and hashCode() consistently
     * for value-based key classes.
     *
     * Avoid modifying fields that affect equality or hashing
     * while an object is being used as a map key.
     *
     *
     * ============================================================
     * 20. HASHMAP - RESIZING
     * ============================================================
     *
     * HashMap has:
     *
     * - Capacity.
     * - Load factor.
     * - Threshold.
     *
     * Threshold is commonly calculated from:
     *
     * capacity * loadFactor
     *
     * Default initial capacity:
     *
     * 16
     *
     * Default load factor:
     *
     * 0.75
     *
     * Initial threshold:
     *
     * 16 * 0.75 = 12
     *
     * When the number of entries exceeds the threshold,
     * resizing is triggered.
     *
     * The table commonly grows to approximately double
     * its previous capacity.
     *
     * Entries are redistributed into the new table.
     *
     * IMPORTANT:
     *
     * Resizing is expensive when it happens, but it does not
     * happen on every put().
     *
     * This contributes to expected amortized performance.
     *
     *
     * ============================================================
     * 21. HASHMAP NULL HANDLING
     * ============================================================
     *
     * HashMap permits:
     *
     * - One null key.
     * - Multiple null values.
     *
     * Example:
     *
     * map.put(null, 100);
     * map.put("Java", null);
     * map.put("Python", null);
     *
     * Different keys may have null values.
     *
     *
     * ============================================================
     * 22. LINKEDHASHMAP INTERNAL WORKING
     * ============================================================
     *
     * LinkedHashMap extends HashMap.
     *
     * It maintains:
     *
     * - Hash table for lookup.
     * - Doubly linked ordering between entries.
     *
     * This allows predictable encounter order.
     *
     * In insertion-order mode:
     *
     * Entries are encountered in insertion order.
     *
     * In access-order mode:
     *
     * Entries are ordered based on access activity.
     *
     * Access-order mode is useful for implementing LRU caches.
     *
     * Common operations that affect access order include
     * get() and certain update or compute operations.
     *
     *
     * ============================================================
     * 23. TREEMAP INTERNAL WORKING
     * ============================================================
     *
     * TreeMap implements NavigableMap and SortedMap.
     *
     * In OpenJDK, it is implemented using a Red-Black Tree.
     *
     * It stores entries according to key ordering.
     *
     * Each node contains information conceptually including:
     *
     * - Key.
     * - Value.
     * - Parent reference.
     * - Left child reference.
     * - Right child reference.
     * - Color.
     *
     * Key comparison is based on:
     *
     * - Comparable.
     * - Comparator.
     *
     * Search, insertion and removal:
     *
     * O(log n).
     *
     * TreeMap is useful when sorted keys and range operations
     * are required.
     *
     *
     * ============================================================
     * 24. HASHSET INTERNAL WORKING
     * ============================================================
     *
     * HashSet is backed by a HashMap in OpenJDK.
     *
     * When adding an element:
     *
     * set.add("Java");
     *
     * It is conceptually stored as:
     *
     * Map key   = "Java"
     * Map value = a shared dummy object
     *
     * HashMap keys are unique.
     *
     * Therefore, HashSet elements are unique.
     *
     * When adding an existing equal element,
     * the Set does not add a duplicate.
     *
     * IMPORTANT:
     *
     * This backing-map arrangement is an implementation detail.
     *
     *
     * ============================================================
     * 25. LINKEDHASHSET INTERNAL WORKING
     * ============================================================
     *
     * LinkedHashSet maintains:
     *
     * - Hash-based lookup.
     * - Insertion-order iteration.
     *
     * In OpenJDK, it uses LinkedHashMap-backed storage.
     *
     * Duplicate elements are not added.
     *
     * It preserves insertion order during iteration.
     *
     *
     * ============================================================
     * 26. TREESET INTERNAL WORKING
     * ============================================================
     *
     * TreeSet is backed by a NavigableMap.
     *
     * In OpenJDK, it uses a TreeMap.
     *
     * Elements are stored as map keys.
     *
     * A shared dummy value is used internally.
     *
     * Elements are ordered according to:
     *
     * - Natural ordering.
     * - A supplied Comparator.
     *
     * Operations such as add(), remove() and contains()
     * take O(log n) time.
     *
     *
     * ============================================================
     * 27. PRIORITYQUEUE INTERNAL WORKING
     * ============================================================
     *
     * Java PriorityQueue uses an array-based binary heap.
     *
     * By default, it behaves as a Min Heap under natural ordering.
     *
     * Its internal array represents a complete binary tree.
     *
     * offer():
     * Adds an element and performs sift-up when required.
     *
     * poll():
     * Removes the root and performs sift-down when required.
     *
     * peek():
     * Returns the root without removing it.
     *
     * Complexity:
     *
     * offer(): O(log n)
     * poll():  O(log n)
     * peek():  O(1)
     *
     * Iteration is not guaranteed to be sorted.
     *
     *
     * ============================================================
     * 28. ARRAYDEQUE INTERNAL WORKING
     * ============================================================
     *
     * ArrayDeque is a resizable-array implementation of Deque.
     *
     * It supports operations at both ends:
     *
     * - addFirst().
     * - addLast().
     * - removeFirst().
     * - removeLast().
     *
     * It uses a circular-array strategy.
     *
     * A circular array allows the logical beginning and end
     * to move around the physical array.
     *
     * When the array becomes full, it grows and rearranges
     * its stored elements.
     *
     * Most end operations are amortized O(1).
     *
     * ArrayDeque does not permit null elements.
     *
     *
     * ============================================================
     * 29. COPYONWRITEARRAYLIST INTERNAL WORKING
     * ============================================================
     *
     * CopyOnWriteArrayList is designed for concurrent use.
     *
     * Its main idea:
     *
     * Every mutation creates a new backing array.
     *
     * Example:
     *
     * Existing:
     * [10, 20, 30]
     *
     * Add 40:
     *
     * A new array is created:
     * [10, 20, 30, 40]
     *
     * The list then uses the new array.
     *
     * Readers can continue using a previously obtained
     * snapshot while a writer publishes a new array.
     *
     * Advantages:
     *
     * - Iterators see a snapshot.
     * - Iteration does not require locking the entire traversal.
     * - Useful when reads greatly outnumber writes.
     *
     * Disadvantages:
     *
     * - Mutations copy the array.
     * - Extra memory and copying cost.
     * - Poor choice for write-heavy workloads.
     *
     *
     * ============================================================
     * 30. CONCURRENTHASHMAP INTERNAL WORKING
     * ============================================================
     *
     * ConcurrentHashMap is designed for concurrent access.
     *
     * Multiple threads can access and update the map safely.
     *
     * Modern OpenJDK implementations use techniques including:
     *
     * - CAS (Compare-And-Set).
     * - Synchronization on individual bins when needed.
     * - Volatile reads and writes.
     * - Tree bins for certain collision-heavy buckets.
     *
     * Older Java versions used a segmented architecture.
     *
     * Modern implementations do not use that old fixed
     * Segment-based design.
     *
     * IMPORTANT:
     *
     * ConcurrentHashMap does not permit null keys or values.
     *
     * It provides atomic methods such as:
     *
     * - putIfAbsent().
     * - compute().
     * - computeIfAbsent().
     * - merge().
     *
     * Iterators are weakly consistent.
     *
     *
     * ============================================================
     * 31. CONCURRENTSKIPLISTMAP INTERNAL WORKING
     * ============================================================
     *
     * ConcurrentSkipListMap is a concurrent sorted map.
     *
     * It uses a Skip List.
     *
     * A Skip List is a layered linked-list structure.
     *
     * The upper levels provide shortcuts to skip over
     * multiple elements.
     *
     * Conceptual idea:
     *
     * Level 2:  10 ----------------> 50
     * Level 1:  10 ------> 30 ----> 50
     * Level 0:  10 -> 20 -> 30 -> 40 -> 50
     *
     * Searching starts from an upper level and moves
     * down when necessary.
     *
     * Expected search, insertion and removal complexity:
     *
     * O(log n).
     *
     * It supports concurrent sorted-map operations.
     *
     *
     * ============================================================
     * 32. ENUMSET INTERNAL WORKING
     * ============================================================
     *
     * EnumSet is a specialized Set for enum constants.
     *
     * It uses a compact bit-vector representation.
     *
     * Each enum constant corresponds to a bit position.
     *
     * A bit can indicate whether an enum value is present.
     *
     * This makes EnumSet memory-efficient and fast
     * for many enum-based operations.
     *
     * OpenJDK commonly uses:
     *
     * RegularEnumSet:
     * For enum types with up to 64 constants.
     *
     * JumboEnumSet:
     * For enum types with more than 64 constants.
     *
     *
     * ============================================================
     * 33. ENUMMAP INTERNAL WORKING
     * ============================================================
     *
     * EnumMap is a specialized Map for enum keys.
     *
     * It uses an array-based representation.
     *
     * Each enum constant maps to a corresponding array position.
     *
     * It is efficient because enum keys have a fixed,
     * known set of possible values.
     *
     * It maintains keys in enum declaration order.
     *
     *
     * ============================================================
     * 34. WEAKHASHMAP INTERNAL WORKING
     * ============================================================
     *
     * WeakHashMap uses weak references for its keys.
     *
     * If a key is no longer strongly reachable elsewhere,
     * the garbage collector may reclaim it.
     *
     * Its entry can then be removed from the map.
     *
     * A ReferenceQueue helps identify cleared weak references.
     *
     * IMPORTANT:
     *
     * A value that strongly references its own key can
     * indirectly keep that key alive.
     *
     * WeakHashMap is not a deterministic cache eviction system.
     *
     * Garbage collection timing is not predictable.
     *
     *
     * ============================================================
     * 35. IDENTITYHASHMAP INTERNAL WORKING
     * ============================================================
     *
     * IdentityHashMap compares keys using reference identity:
     *
     * key1 == key2
     *
     * Instead of ordinary value equality:
     *
     * key1.equals(key2)
     *
     * It uses System.identityHashCode() for hashing.
     *
     * It uses an open-addressed table.
     *
     * It is useful in specialized situations such as
     * object graph processing where object identity matters.
     *
     *
     * ============================================================
     * 36. COLLECTION IMPLEMENTATION COMPARISON
     * ============================================================
     *
     * ArrayList:
     * Resizable array.
     *
     * LinkedList:
     * Doubly linked list.
     *
     * Vector:
     * Synchronized resizable array.
     *
     * HashSet:
     * HashMap-backed Set in OpenJDK.
     *
     * LinkedHashSet:
     * Insertion-ordered hash-based Set in OpenJDK.
     *
     * TreeSet:
     * TreeMap-backed sorted Set in OpenJDK.
     *
     * HashMap:
     * Hash table with bucket structures.
     *
     * LinkedHashMap:
     * Hash table plus linked ordering.
     *
     * TreeMap:
     * Red-Black Tree in OpenJDK.
     *
     * PriorityQueue:
     * Array-based binary heap.
     *
     * ArrayDeque:
     * Resizable circular-array Deque.
     *
     * CopyOnWriteArrayList:
     * Copy-on-write array.
     *
     * ConcurrentHashMap:
     * Concurrent hash table.
     *
     * ConcurrentSkipListMap:
     * Concurrent skip list.
     *
     * EnumSet:
     * Bit-vector-based Set.
     *
     * EnumMap:
     * Array-based Map.
     *
     *
     * ============================================================
     * 37. IMPORTANT PERFORMANCE COMPARISON
     * ============================================================
     *
     * Operation             Typical implementation
     *
     * ArrayList.get()       O(1)
     *
     * ArrayList.add(end)    Amortized O(1)
     *
     * ArrayList.add(index)  O(n) in general
     *
     * LinkedList.get()      O(n)
     *
     * LinkedList.addFirst() O(1)
     *
     * HashMap.get()         Expected O(1)
     *
     * HashMap.put()         Expected O(1)
     *
     * TreeMap.get()         O(log n)
     *
     * TreeMap.put()         O(log n)
     *
     * HashSet.contains()    Expected O(1)
     *
     * TreeSet.contains()    O(log n)
     *
     * PriorityQueue.peek()  O(1)
     *
     * PriorityQueue.offer() O(log n)
     *
     * PriorityQueue.poll()  O(log n)
     *
     * ArrayDeque end ops    Amortized O(1)
     *
     * IMPORTANT:
     *
     * These are typical complexity expectations, not
     * guarantees that every individual operation takes
     * exactly the same amount of time.
     *
     *
     * ============================================================
     * 38. HOW TO CHOOSE THE RIGHT COLLECTION?
     * ============================================================
     *
     * Need indexed access?
     * -> ArrayList.
     *
     * Need frequent insertion/removal at both ends?
     * -> ArrayDeque.
     *
     * Need unique elements with fast average lookup?
     * -> HashSet.
     *
     * Need unique elements in insertion order?
     * -> LinkedHashSet.
     *
     * Need unique elements in sorted order?
     * -> TreeSet.
     *
     * Need key-value storage with fast average lookup?
     * -> HashMap.
     *
     * Need key-value storage with insertion order?
     * -> LinkedHashMap.
     *
     * Need sorted keys and range operations?
     * -> TreeMap.
     *
     * Need priority-based processing?
     * -> PriorityQueue.
     *
     * Need concurrent key-value access?
     * -> ConcurrentHashMap.
     *
     * Need concurrent sorted keys?
     * -> ConcurrentSkipListMap.
     *
     * Need read-heavy concurrent list access?
     * -> CopyOnWriteArrayList.
     *
     * Need enum constants as Set elements?
     * -> EnumSet.
     *
     * Need enum constants as Map keys?
     * -> EnumMap.
     *
     *
     * ============================================================
     * 39. COMMON PERFORMANCE MISTAKES
     * ============================================================
     *
     * 1. Using LinkedList when frequent indexed access is needed.
     *
     * 2. Using ArrayList for frequent insertion at the beginning
     *    without considering shifting costs.
     *
     * 3. Assuming HashMap always takes O(1).
     *
     * 4. Implementing equals() without hashCode() for map keys.
     *
     * 5. Using mutable objects as HashMap keys and then changing
     *    fields involved in equality or hashing.
     *
     * 6. Using TreeMap when sorted ordering is not needed.
     *
     * 7. Assuming PriorityQueue iteration is sorted.
     *
     * 8. Using CopyOnWriteArrayList for write-heavy workloads.
     *
     * 9. Assuming synchronized individual methods make
     *    compound operations atomic.
     *
     * 10. Using a non-thread-safe collection from multiple
     *     threads without suitable coordination.
     *
     * 11. Ignoring resizing and memory overhead.
     *
     * 12. Choosing a collection only by Big-O complexity
     *     without considering actual workload and memory.
     *
     *
     * ============================================================
     * 40. IMPORTANT INTERVIEW QUESTIONS
     * ============================================================
     *
     * 1. How does ArrayList work internally?
     *
     * 2. What is the difference between size and capacity?
     *
     * 3. How does ArrayList resize its internal array?
     *
     * 4. Why is ArrayList.get() O(1)?
     *
     * 5. Why is insertion in the middle of ArrayList O(n)?
     *
     * 6. How does LinkedList store elements?
     *
     * 7. Why is LinkedList indexed access O(n)?
     *
     * 8. How does HashMap work internally?
     *
     * 9. What is hash spreading?
     *
     * 10. How does HashMap handle collisions?
     *
     * 11. What is treeification in HashMap?
     *
     * 12. What are capacity, load factor and threshold?
     *
     * 13. What happens when HashMap resizes?
     *
     * 14. Why must equals() and hashCode() be consistent?
     *
     * 15. How does HashSet work internally?
     *
     * 16. How does TreeMap maintain sorted keys?
     *
     * 17. How does PriorityQueue work internally?
     *
     * 18. Why does PriorityQueue iteration not guarantee
     *     sorted order?
     *
     * 19. How does CopyOnWriteArrayList work?
     *
     * 20. How does ConcurrentHashMap support concurrency?
     *
     * 21. How does EnumSet represent its elements?
     *
     * 22. What is the difference between HashMap and TreeMap?
     *
     * 23. What is the difference between ArrayList and LinkedList?
     *
     *
     * ============================================================
     * 41. QUICK REVISION
     * ============================================================
     *
     * ArrayList:
     *     -> Resizable array.
     *
     * LinkedList:
     *     -> Doubly linked list.
     *
     * HashMap:
     *     -> Hash table.
     *
     * Hashing:
     *     -> Helps locate a bucket.
     *
     * equals():
     *     -> Identifies logically equal keys.
     *
     * hashCode():
     *     -> Helps determine hash-based placement.
     *
     * Collision:
     *     -> Different keys map to the same bucket.
     *
     * Treeification:
     *     -> Converts a sufficiently large HashMap bucket
     *        into a tree bin under applicable conditions.
     *
     * Load factor:
     *     -> Controls when HashMap resizing is triggered.
     *
     * TreeMap:
     *     -> Red-Black Tree in OpenJDK.
     *
     * HashSet:
     *     -> HashMap-backed in OpenJDK.
     *
     * TreeSet:
     *     -> TreeMap-backed in OpenJDK.
     *
     * PriorityQueue:
     *     -> Array-based binary heap.
     *
     * ArrayDeque:
     *     -> Resizable circular-array Deque.
     *
     * CopyOnWriteArrayList:
     *     -> Creates a new backing array on mutation.
     *
     * ConcurrentHashMap:
     *     -> Concurrent hash-based Map.
     *
     * EnumSet:
     *     -> Bit-vector-based Set.
     *
     * EnumMap:
     *     -> Array-based Map.
     *
     * ============================================================
     * END OF INTERNAL WORKING OF COLLECTION IMPLEMENTATIONS
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Internal Working of Collection Implementations , Happy Learning ..!");
    }
}
