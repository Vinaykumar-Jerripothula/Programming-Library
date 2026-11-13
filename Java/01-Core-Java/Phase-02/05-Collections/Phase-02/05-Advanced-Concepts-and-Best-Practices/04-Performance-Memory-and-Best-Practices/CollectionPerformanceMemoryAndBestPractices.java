
public class CollectionPerformanceMemoryAndBestPractices {

    /*
     * ============================================================
     * 1. WHY COLLECTION PERFORMANCE MATTERS
     * ============================================================
     *
     * Choosing a collection is not just about storing data.
     *
     * It affects:
     *
     * - Execution time.
     * - Memory consumption.
     * - CPU usage.
     * - Searching and insertion performance.
     * - Data ordering.
     * - Duplicate handling.
     * - Thread safety.
     *
     * Imagine storing 10 lakh student records.
     *
     * If you repeatedly search using a List, performance may
     * become poor.
     *
     * If you need fast key-based lookup, a HashMap may be
     * a better choice.
     *
     * If you need sorted keys, TreeMap may be appropriate.
     *
     * The correct collection depends on the operations your
     * application performs most frequently.
     *
     *
     * ============================================================
     * 2. TIME COMPLEXITY - QUICK REVISION
     * ============================================================
     *
     * Time complexity describes how an operation's running
     * time grows as the amount of data increases.
     *
     * Common complexities:
     *
     * O(1)       Constant time
     * O(log n)   Logarithmic time
     * O(n)       Linear time
     * O(n log n) Linearithmic time
     * O(n^2)     Quadratic time
     *
     * Example:
     *
     * Accessing an ArrayList element by index:
     * O(1)
     *
     * Searching an unsorted ArrayList:
     * O(n)
     *
     * Searching a HashMap by key:
     * Expected O(1)
     *
     * Searching a TreeMap by key:
     * O(log n)
     *
     *
     * ============================================================
     * 3. LIST IMPLEMENTATIONS - COMPLEXITY
     * ============================================================
     *
     * ArrayList:
     *
     * get(index)           O(1)
     * set(index, element)  O(1)
     * add(element)         Amortized O(1)
     * add(index, element)  O(n)
     * remove(index)        O(n)
     * contains(element)    O(n)
     * indexOf(element)     O(n)
     *
     * WHY?
     *
     * ArrayList uses a resizable array.
     *
     * Direct indexing is fast.
     *
     * Inserting or removing in the middle may require shifting
     * elements.
     *
     *
     * LinkedList:
     *
     * get(index)           O(n)
     * set(index, element)  O(n)
     * addFirst()           O(1)
     * addLast()            O(1)
     * removeFirst()        O(1)
     * removeLast()         O(1)
     * contains(element)    O(n)
     *
     * WHY?
     *
     * LinkedList uses linked nodes.
     *
     * It does not support direct array-style indexing.
     *
     * It is efficient at the ends, but locating an arbitrary
     * index requires traversal.
     *
     * IMPORTANT:
     *
     * Even though LinkedList can insert or remove in O(1)
     * once a node or ListIterator position is already located,
     * locating that position can take O(n).
     *
     *
     * ============================================================
     * 4. SET IMPLEMENTATIONS - COMPLEXITY
     * ============================================================
     *
     * HashSet:
     *
     * add()       Expected O(1)
     * remove()    Expected O(1)
     * contains()  Expected O(1)
     *
     * LinkedHashSet:
     *
     * add()       Expected O(1)
     * remove()    Expected O(1)
     * contains()  Expected O(1)
     *
     * TreeSet:
     *
     * add()       O(log n)
     * remove()    O(log n)
     * contains()  O(log n)
     *
     * WHY?
     *
     * HashSet:
     * Uses hashing and a hash table.
     *
     * LinkedHashSet:
     * Uses hashing and linked encounter-order tracking.
     *
     * TreeSet:
     * Uses a balanced search tree.
     *
     *
     * ============================================================
     * 5. MAP IMPLEMENTATIONS - COMPLEXITY
     * ============================================================
     *
     * HashMap:
     *
     * put()       Expected O(1)
     * get()       Expected O(1)
     * remove()    Expected O(1)
     * containsKey() Expected O(1)
     *
     * LinkedHashMap:
     *
     * put()       Expected O(1)
     * get()       Expected O(1)
     * remove()    Expected O(1)
     *
     * TreeMap:
     *
     * put()       O(log n)
     * get()       O(log n)
     * remove()    O(log n)
     * containsKey() O(log n)
     *
     * ConcurrentHashMap:
     *
     * get()       Expected O(1)
     * put()       Expected O(1)
     * remove()    Expected O(1)
     *
     * These are general performance expectations, not
     * guarantees for every possible workload.
     *
     *
     * ============================================================
     * 6. QUEUE AND DEQUE IMPLEMENTATIONS
     * ============================================================
     *
     * ArrayDeque:
     *
     * addFirst()  Amortized O(1)
     * addLast()   Amortized O(1)
     * pollFirst() Amortized O(1)
     * pollLast()  Amortized O(1)
     *
     * PriorityQueue:
     *
     * offer()     O(log n)
     * poll()      O(log n)
     * peek()      O(1)
     *
     * WHY?
     *
     * ArrayDeque uses a resizable circular array.
     *
     * PriorityQueue uses a binary heap.
     *
     *
     * ============================================================
     * 7. BIG-O COMPLEXITY COMPARISON
     * ============================================================
     *
     * Operation       ArrayList   LinkedList   HashSet   TreeSet
     *
     * Access index       O(1)        O(n)         -         -
     * Search             O(n)        O(n)      O(1)*     O(log n)
     * Add element        O(1)*       O(1)**     O(1)*     O(log n)
     * Remove element     O(n)        O(n)      O(1)*     O(log n)
     *
     * * Expected or amortized, depending on operation.
     *
     * ** LinkedList insertion is O(1) at an already located
     *    position; locating an arbitrary position takes O(n).
     *
     * HashSet does not provide index-based access.
     *
     *
     * ============================================================
     * 8. WHAT IS AMORTIZED TIME COMPLEXITY?
     * ============================================================
     *
     * Amortized analysis considers the average cost of a
     * sequence of operations, including occasional expensive
     * operations.
     *
     * Example: ArrayList.add()
     *
     * Normally:
     * - An element is inserted into the next available array slot.
     * - This takes O(1).
     *
     * Occasionally:
     * - The internal array becomes full.
     * - A larger array must be allocated.
     * - Existing elements must be copied.
     *
     * That particular insertion can take O(n).
     *
     * But across many insertions, the average cost per insertion
     * remains amortized O(1).
     *
     * IMPORTANT:
     *
     * Amortized O(1) does not mean every individual operation
     * takes O(1).
     *
     *
     * ============================================================
     * 9. WHAT IS INITIAL CAPACITY?
     * ============================================================
     *
     * Initial capacity is the initial amount of storage allocated
     * for a collection's internal data structure.
     *
     * Example:
     *
     * List<Integer> numbers = new ArrayList<>(1000);
     *
     * This creates an ArrayList with capacity for 1000 elements.
     *
     * Capacity is not the same as size.
     *
     * size():
     * Number of elements currently stored.
     *
     * capacity:
     * Amount of allocated storage available before resizing.
     *
     * Example:
     *
     * List<Integer> numbers = new ArrayList<>(100);
     *
     * System.out.println(numbers.size());
     *
     * Output:
     * 0
     *
     * The List is empty even though it has an initial capacity.
     *
     *
     * ============================================================
     * 10. ARRAYLIST CAPACITY PLANNING
     * ============================================================
     *
     * If you know approximately how many elements will be stored,
     * providing an initial capacity can reduce resizing.
     *
     * Example:
     *
     * List<Integer> numbers = new ArrayList<>(10000);
     *
     * for (int i = 0; i < 10000; i++) {
     *     numbers.add(i);
     * }
     *
     * This can avoid repeated growth while adding these elements.
     *
     * You can also use:
     *
     * numbers.ensureCapacity(20000);
     *
     * This requests enough capacity for at least 20000 elements.
     *
     * IMPORTANT:
     *
     * Do not allocate an unnecessarily huge capacity.
     *
     * It wastes memory.
     *
     * ArrayList does not expose its capacity through a public
     * getter.
     *
     *
     * ============================================================
     * 11. HASHMAP INITIAL CAPACITY AND LOAD FACTOR
     * ============================================================
     *
     * HashMap uses:
     *
     * - Initial capacity.
     * - Load factor.
     * - Resize operation.
     *
     * Example:
     *
     * Map<Integer, String> students =
     *     new HashMap<>(1000);
     *
     * The constructor argument is an initial-capacity request,
     * not a guaranteed final capacity or maximum size.
     *
     * Default load factor:
     * 0.75
     *
     * Conceptual resize threshold:
     *
     * capacity * load factor
     *
     * For example:
     *
     * capacity = 16
     * load factor = 0.75
     *
     * threshold = 12
     *
     * When the number of mappings exceeds the threshold,
     * HashMap may resize.
     *
     * IMPORTANT:
     *
     * The exact internal capacity and resize behavior are
     * implementation details.
     *
     * For a known expected number of mappings, capacity planning
     * can reduce resizing.
     *
     *
     * ============================================================
     * 12. WHAT IS MEMORY OVERHEAD?
     * ============================================================
     *
     * Memory overhead means additional memory used by a data
     * structure beyond the actual stored values.
     *
     * Example:
     *
     * ArrayList:
     * - Backing array.
     * - Reference slots.
     * - Unused capacity.
     *
     * LinkedList:
     * - Node objects.
     * - Previous references.
     * - Next references.
     *
     * HashMap:
     * - Bucket array.
     * - Node objects.
     * - Key and value references.
     * - Hash values and possible tree-bin structures.
     *
     * TreeMap:
     * - Tree node objects.
     * - Parent references.
     * - Left and right child references.
     * - Balancing metadata.
     *
     * The actual memory usage depends on the JVM, object layout,
     * compressed references, alignment and implementation.
     *
     *
     * ============================================================
     * 13. ARRAYLIST VS LINKEDLIST - MEMORY
     * ============================================================
     *
     * ArrayList:
     *
     * - Stores references in a backing array.
     * - Has unused capacity sometimes.
     * - Usually has relatively low per-element overhead.
     * - Provides fast index access.
     *
     * LinkedList:
     *
     * - Stores each element inside a separate node.
     * - Each node maintains links to neighboring nodes.
     * - Has additional object and reference overhead.
     * - Does not provide constant-time index access.
     *
     * In many practical applications, ArrayList is a better
     * default choice.
     *
     * Do not choose LinkedList merely because its insertion
     * operation is described as O(1).
     *
     * Consider traversal and memory costs as well.
     *
     *
     * ============================================================
     * 14. OBJECTS AND PRIMITIVE VALUES
     * ============================================================
     *
     * Java Collections store object references, not primitive
     * values directly.
     *
     * Example:
     *
     * List<Integer> numbers = new ArrayList<>();
     *
     * numbers.add(10);
     *
     * Here, int is converted to Integer through autoboxing.
     *
     * Conceptually:
     *
     * int -> Integer
     *
     * When retrieving:
     *
     * int value = numbers.get(0);
     *
     * Integer -> int through unboxing.
     *
     * Boxing and object storage can introduce memory and
     * performance overhead compared with primitive arrays.
     *
     * For performance-sensitive primitive numeric processing,
     * consider primitive arrays or specialized libraries.
     *
     *
     * ============================================================
     * 15. AVOID UNNECESSARY COLLECTION CONVERSIONS
     * ============================================================
     *
     * Avoid creating copies repeatedly when they are unnecessary.
     *
     * Example:
     *
     * List<String> names = new ArrayList<>();
     *
     * Set<String> uniqueNames = new HashSet<>(names);
     *
     * This conversion is useful when uniqueness is required.
     *
     * But repeatedly converting between List, Set and ArrayList
     * without a real need wastes time and memory.
     *
     * Choose the right data structure early.
     *
     *
     * ============================================================
     * 16. CHOOSE THE RIGHT COLLECTION
     * ============================================================
     *
     * ARRAYLIST:
     *
     * Use when:
     * - You need index-based access.
     * - You frequently iterate.
     * - You need a resizable sequence.
     *
     * LINKEDLIST:
     *
     * Use when:
     * - You specifically need linked-node behavior.
     * - You need efficient operations at both ends.
     *
     * ARRAYDEQUE:
     *
     * Use when:
     * - You need a stack or queue.
     * - You need efficient operations at both ends.
     *
     * HASHSET:
     *
     * Use when:
     * - You need unique elements.
     * - Ordering is not important.
     * - Fast expected membership checks matter.
     *
     * LINKEDHASHSET:
     *
     * Use when:
     * - You need unique elements.
     * - Insertion order must be preserved.
     *
     * TREESET:
     *
     * Use when:
     * - You need unique sorted elements.
     * - Range and navigation operations are required.
     *
     * HASHMAP:
     *
     * Use when:
     * - You need key-value lookup.
     * - Key ordering is not important.
     *
     * LINKEDHASHMAP:
     *
     * Use when:
     * - You need key-value lookup.
     * - Encounter order matters.
     * - You need access-order behavior for an LRU-style cache.
     *
     * TREEMAP:
     *
     * Use when:
     * - Keys must remain sorted.
     * - Range and navigation operations are required.
     *
     * PRIORITYQUEUE:
     *
     * Use when:
     * - You repeatedly need the smallest or highest-priority item.
     *
     * CONCURRENTHASHMAP:
     *
     * Use when:
     * - Multiple threads access and modify a shared Map.
     * - You need concurrent key-value operations.
     *
     *
     * ============================================================
     * 17. DON'T USE LINKEDLIST BY DEFAULT
     * ============================================================
     *
     * A common misconception:
     *
     * "LinkedList is always faster for insertion and deletion."
     *
     * This is not true.
     *
     * Consider:
     *
     * List<Integer> list = new LinkedList<>();
     *
     * list.add(500, 100);
     *
     * To reach index 500, LinkedList must traverse nodes.
     *
     * That traversal takes O(n).
     *
     * ArrayList may need to shift elements, but it benefits
     * from contiguous reference storage and efficient memory access.
     *
     * In practice, ArrayList often performs better for
     * general-purpose List usage.
     *
     *
     * ============================================================
     * 18. USE HASHMAP WHEN ORDER IS NOT REQUIRED
     * ============================================================
     *
     * HashMap provides expected constant-time basic operations
     * under suitable hash distribution.
     *
     * If you do not need ordering, it is usually a good
     * general-purpose Map choice.
     *
     * If you need insertion order:
     *
     * LinkedHashMap.
     *
     * If you need sorted keys:
     *
     * TreeMap.
     *
     * If you need concurrent access:
     *
     * ConcurrentHashMap.
     *
     * Always choose based on requirements rather than habit.
     *
     *
     * ============================================================
     * 19. IMPLEMENT HASHCODE() AND EQUALS() CORRECTLY
     * ============================================================
     *
     * Hash-based collections depend on the equals() and
     * hashCode() contract.
     *
     * IMPORTANT RULE:
     *
     * If two objects are equal according to equals(),
     * they must return the same hashCode().
     *
     * Example:
     *
     * class Student {
     *
     *     int id;
     *
     *     Student(int id) {
     *         this.id = id;
     *     }
     *
     *     @Override
     *     public boolean equals(Object obj) {
     *
     *         if (this == obj) {
     *             return true;
     *         }
     *
     *         if (!(obj instanceof Student other)) {
     *             return false;
     *         }
     *
     *         return this.id == other.id;
     *     }
     *
     *     @Override
     *     public int hashCode() {
     *         return Integer.hashCode(id);
     *     }
     * }
     *
     * Without a consistent hashCode() implementation,
     * HashSet and HashMap may behave unexpectedly for
     * logically equal objects.
     *
     *
     * ============================================================
     * 20. DON'T MODIFY HASH-BASED KEYS UNSAFELY
     * ============================================================
     *
     * Avoid changing fields used by equals() and hashCode()
     * while an object is being used as a HashMap key or
     * HashSet element.
     *
     * Example:
     *
     * Student student = new Student(101);
     *
     * Set<Student> students = new HashSet<>();
     *
     * students.add(student);
     *
     * student.id = 200;
     *
     * students.contains(student);
     *
     * The lookup may fail because the object's hash code
     * has changed since insertion.
     *
     * Prefer immutable key objects.
     *
     *
     * ============================================================
     * 21. USE ENTRYSET() WHEN YOU NEED KEYS AND VALUES
     * ============================================================
     *
     * Less efficient style:
     *
     * for (Integer key : map.keySet()) {
     *     System.out.println(key + " " + map.get(key));
     * }
     *
     * Better style:
     *
     * for (Map.Entry<Integer, String> entry : map.entrySet()) {
     *
     *     System.out.println(
     *         entry.getKey() + " " + entry.getValue()
     *     );
     * }
     *
     * entrySet() directly provides each mapping.
     *
     * It avoids performing a separate lookup for every key.
     *
     *
     * ============================================================
     * 22. USE THE RIGHT ITERATION APPROACH
     * ============================================================
     *
     * For-each loop:
     *
     * for (String name : names) {
     *     System.out.println(name);
     * }
     *
     * Iterator:
     *
     * Iterator<String> iterator = names.iterator();
     *
     * while (iterator.hasNext()) {
     *
     *     String name = iterator.next();
     *
     *     if (name.equals("Vinay")) {
     *         iterator.remove();
     *     }
     * }
     *
     * Use Iterator.remove() when removing elements during
     * traversal where the iterator supports removal.
     *
     * Do not structurally modify an ordinary collection
     * independently while iterating over it.
     *
     *
     * ============================================================
     * 23. USE COMPUTEIFABSENT() FOR FREQUENCY MAPS
     * ============================================================
     *
     * Example:
     *
     * Map<String, Integer> frequency = new HashMap<>();
     *
     * String[] words = {
     *     "Java", "Python", "Java", "C++", "Java"
     * };
     *
     * for (String word : words) {
     *
     *     frequency.merge(word, 1, Integer::sum);
     * }
     *
     * System.out.println(frequency);
     *
     * merge() is convenient for frequency counting.
     *
     * Another approach:
     *
     * for (String word : words) {
     *
     *     frequency.compute(
     *         word,
     *         (key, value) -> value == null ? 1 : value + 1
     *     );
     * }
     *
     * For simple frequency counting, merge() is often clearer.
     *
     *
     * ============================================================
     * 24. PREFER INTERFACES AS REFERENCE TYPES
     * ============================================================
     *
     * Prefer:
     *
     * List<String> names = new ArrayList<>();
     *
     * Instead of:
     *
     * ArrayList<String> names = new ArrayList<>();
     *
     * Prefer:
     *
     * Map<Integer, String> students = new HashMap<>();
     *
     * Instead of:
     *
     * HashMap<Integer, String> students = new HashMap<>();
     *
     * WHY?
     *
     * Your code depends on the interface contract rather than
     * a specific implementation.
     *
     * You can replace the implementation more easily.
     *
     * Example:
     *
     * List<String> names = new LinkedList<>();
     *
     * The variable type remains List.
     *
     * IMPORTANT:
     *
     * Use the concrete implementation as the reference type
     * when you genuinely need implementation-specific methods.
     *
     *
     * ============================================================
     * 25. AVOID RETURNING NULL FOR COLLECTION RESULTS
     * ============================================================
     *
     * Avoid:
     *
     * public List<String> findNames() {
     *     return null;
     * }
     *
     * Prefer:
     *
     * public List<String> findNames() {
     *     return Collections.emptyList();
     * }
     *
     * This makes callers' code simpler:
     *
     * for (String name : findNames()) {
     *     System.out.println(name);
     * }
     *
     * A caller does not need a separate null check.
     *
     * Use null only when it has a deliberate and documented
     * meaning in the API.
     *
     *
     * ============================================================
     * 26. USE IMMUTABLE RESULTS WHEN APPROPRIATE
     * ============================================================
     *
     * If a method should return data that callers must not
     * modify, consider returning an unmodifiable result.
     *
     * Example:
     *
     * public List<String> getLanguages() {
     *
     *     return List.of("Java", "Python", "C++");
     * }
     *
     * Or:
     *
     * public List<String> getNames(List<String> source) {
     *
     *     return List.copyOf(source);
     * }
     *
     * Choose this only when the API contract requires
     * an unmodifiable result.
     *
     *
     * ============================================================
     * 27. STREAMS AND COLLECTIONS
     * ============================================================
     *
     * Streams provide a declarative way to process collection data.
     *
     * Example:
     *
     * List<Integer> numbers =
     *     List.of(10, 15, 20, 25, 30);
     *
     * List<Integer> evenNumbers =
     *     numbers.stream()
     *            .filter(number -> number % 2 == 0)
     *            .toList();
     *
     * Output:
     * [10, 20, 30]
     *
     * Stream.toList() was introduced in Java 16.
     *
     * Its returned List is unmodifiable.
     *
     * For a mutable List:
     *
     * List<Integer> mutableEvenNumbers =
     *     numbers.stream()
     *            .filter(number -> number % 2 == 0)
     *            .collect(Collectors.toCollection(ArrayList::new));
     *
     * IMPORTANT:
     *
     * Collectors.toList() does not guarantee a particular
     * List implementation or mutability.
     *
     * Use Collectors.toCollection(ArrayList::new) when you
     * specifically require a mutable ArrayList.
     *
     *
     * ============================================================
     * 28. PARALLEL STREAMS - USE CAREFULLY
     * ============================================================
     *
     * Parallel streams can process work using multiple threads.
     *
     * Example:
     *
     * numbers.parallelStream()
     *        .map(number -> number * 2)
     *        .toList();
     *
     * Parallel streams are not automatically faster.
     *
     * They introduce overhead from:
     *
     * - Task splitting.
     * - Thread scheduling.
     * - Coordination.
     * - Combining results.
     *
     * They are more suitable for sufficiently large workloads
     * with independent operations.
     *
     * Avoid unsafe shared mutable state in parallel operations.
     *
     *
     * ============================================================
     * 29. COLLECTIONS AND SPRING BOOT
     * ============================================================
     *
     * Collections are used throughout Spring Boot applications.
     *
     * Examples:
     *
     * List<User>
     * Set<Role>
     * Map<String, Object>
     * List<Order>
     * Map<String, String>
     *
     * Spring Data JPA:
     *
     * Repository methods often return:
     *
     * List<User>
     * Set<Role>
     * Optional<User>
     *
     * Service layer:
     *
     * - Processes collections returned by repositories.
     * - Filters and transforms data.
     * - Creates DTO lists.
     * - Builds lookup Maps.
     *
     * Controller layer:
     *
     * - Returns collections as JSON arrays or objects.
     *
     * Example:
     *
     * @GetMapping("/users")
     * public List<UserDto> getUsers() {
     *     return userService.findAll();
     * }
     *
     * Collection choice affects application behavior and
     * performance even in ordinary REST APIs.
     *
     *
     * ============================================================
     * 30. COLLECTIONS AND DATABASE RESULTS
     * ============================================================
     *
     * Be careful when loading large database results.
     *
     * Example:
     *
     * List<User> users = userRepository.findAll();
     *
     * This may load many records into application memory.
     *
     * For large datasets, consider:
     *
     * - Pagination.
     * - Sorting at the database level.
     * - Filtering at the database level.
     * - Streaming or chunked processing where appropriate.
     *
     * Example with Spring Data:
     *
     * Page<User> page =
     *     userRepository.findAll(
     *         PageRequest.of(0, 20)
     *     );
     *
     * This requests a page of 20 records.
     *
     * Do not load an entire large table into memory when
     * the application only needs a small subset.
     *
     *
     * ============================================================
     * 31. COMMON PERFORMANCE MISTAKES
     * ============================================================
     *
     * 1. Using LinkedList for every List requirement.
     *
     * 2. Using TreeMap when key ordering is unnecessary.
     *
     * 3. Repeatedly searching a List when a Set or Map is more
     *    suitable.
     *
     * 4. Ignoring initial capacity when the expected size is known.
     *
     * 5. Creating unnecessary intermediate collections.
     *
     * 6. Performing repeated Map lookups when entrySet() is enough.
     *
     * 7. Using parallel streams for tiny workloads.
     *
     * 8. Loading huge database results into memory.
     *
     * 9. Using mutable objects as hash keys without care.
     *
     * 10. Choosing a collection without considering ordering,
     *     duplicates and null requirements.
     *
     *
     * ============================================================
     * 32. COLLECTION SELECTION QUICK GUIDE
     * ============================================================
     *
     * Requirement                         Suitable choice
     *
     * Resizable sequence                  ArrayList
     * Frequent index access               ArrayList
     * Queue or stack                      ArrayDeque
     * Unique elements                     HashSet
     * Unique elements + insertion order   LinkedHashSet
     * Unique sorted elements              TreeSet
     * Key-value lookup                    HashMap
     * Map + insertion order               LinkedHashMap
     * Map + sorted keys                   TreeMap
     * Priority-based processing           PriorityQueue
     * Concurrent key-value access         ConcurrentHashMap
     * Blocking producer-consumer          BlockingQueue implementation
     *
     * These are starting recommendations.
     *
     * Always verify the required behavior and workload.
     *
     *
     * ============================================================
     * 33. FINAL COLLECTIONS REVISION CHECKLIST
     * ============================================================
     *
     * FUNDAMENTALS:
     *
     * [ ] Collection hierarchy
     * [ ] Iterable and Collection
     * [ ] List, Set, Queue and Deque
     * [ ] Map and its views
     *
     * IMPLEMENTATIONS:
     *
     * [ ] ArrayList
     * [ ] LinkedList
     * [ ] Vector and Stack
     * [ ] CopyOnWriteArrayList
     * [ ] HashSet
     * [ ] LinkedHashSet
     * [ ] TreeSet
     * [ ] EnumSet
     * [ ] CopyOnWriteArraySet
     * [ ] ConcurrentSkipListSet
     * [ ] PriorityQueue
     * [ ] ArrayDeque
     * [ ] HashMap
     * [ ] LinkedHashMap
     * [ ] TreeMap
     * [ ] Hashtable
     * [ ] EnumMap
     * [ ] WeakHashMap
     * [ ] IdentityHashMap
     * [ ] Properties
     * [ ] ConcurrentHashMap
     * [ ] ConcurrentSkipListMap
     *
     * TRAVERSAL:
     *
     * [ ] Iterator
     * [ ] ListIterator
     * [ ] Enumeration
     * [ ] Enhanced for loop
     * [ ] Spliterator
     * [ ] Fail-fast and weakly consistent iterators
     *
     * SORTING AND SEARCHING:
     *
     * [ ] Comparable
     * [ ] Comparator
     * [ ] Collections.sort()
     * [ ] Arrays.sort()
     * [ ] Binary search
     * [ ] Navigable collections
     *
     * INTERNAL WORKING:
     *
     * [ ] Dynamic arrays
     * [ ] Linked structures
     * [ ] Hash tables
     * [ ] Trees and heaps
     * [ ] Collection implementation internals
     *
     * CONCURRENCY:
     *
     * [ ] Thread safety
     * [ ] Synchronized wrappers
     * [ ] Concurrent queues
     * [ ] Blocking queues
     *
     * ADVANCED:
     *
     * [ ] Immutable and unmodifiable collections
     * [ ] Factory methods and views
     * [ ] Sequenced Collections
     * [ ] Performance and memory
     * [ ] Collection selection and best practices
     *
     *
     * ============================================================
     * 34. FINAL TAKEAWAY
     * ============================================================
     *
     * There is no single best collection for every situation.
     *
     * Choose based on:
     *
     * 1. Required operations.
     * 2. Time complexity.
     * 3. Memory overhead.
     * 4. Ordering requirements.
     * 5. Duplicate requirements.
     * 6. Null handling.
     * 7. Thread-safety requirements.
     *
     * A good Java developer does not simply know collection names.
     *
     * A good Java developer understands:
     *
     * - How collections work internally.
     * - Why one implementation is suitable for a situation.
     * - What performance trade-offs exist.
     * - How to avoid common mistakes.
     *
     * That is the real goal of mastering Java Collections.
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Collection Performance, Memory and Best Practices , Happy Learning ..!");
    }
}
