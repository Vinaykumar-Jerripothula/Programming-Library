
public class ArrayList {

    /*
     * ============================================================
     *                         ARRAYLIST
     * ============================================================
     *
     * 1. WHAT IS ARRAYLIST?
     *
     * Imagine you want to store 100 student names.
     *
     * You can use an array:
     *
     *     String[] students = new String[100];
     *
     * But what happens when you need to store 150 students?
     *
     * You must create another array and copy the existing
     * elements manually.
     *
     * This is where ArrayList helps.
     *
     * ArrayList is a resizable-array implementation of the
     * List interface.
     *
     * It automatically manages its internal array when
     * elements are added or removed.
     *
     * In simple words:
     *
     * ArrayList = Array + Automatic resizing + List operations.
     *
     *
     * 2. PACKAGE AND DECLARATION
     *
     * Package:
     *     java.util
     *
     * Declaration:
     *
     *     public class ArrayList<E>
     *         extends AbstractList<E>
     *         implements List<E>, RandomAccess,
     *                    Cloneable, Serializable
     *
     * E represents the type of elements stored.
     *
     * Example:
     *
     *     ArrayList<String> names = new ArrayList<>();
     *
     * IMPORTANT:
     * ArrayList is a class, whereas List is an interface.
     *
     *
     * 3. ARRAYLIST HIERARCHY
     *
     *                     Iterable
     *                         |
     *                     Collection
     *                         |
     *                       List
     *                         |
     *                    AbstractList
     *                         |
     *                     ArrayList
     *
     * ArrayList also implements:
     *
     * - RandomAccess
     * - Cloneable
     * - Serializable
     *
     * RandomAccess is a marker interface indicating that
     * indexed access is generally fast.
     *
     *
     * 4. CREATING ARRAYLIST OBJECTS
     *
     * METHOD 1: Default constructor
     *
     *     ArrayList<String> names = new ArrayList<>();
     *
     * METHOD 2: Initial capacity
     *
     *     ArrayList<String> names = new ArrayList<>(20);
     *
     * METHOD 3: From another collection
     *
     *     ArrayList<String> names =
     *         new ArrayList<>(List.of("Vinay", "Ravi"));
     *
     * METHOD 4: Using the List reference
     *
     *     List<String> names = new ArrayList<>();
     *
     * RECOMMENDATION:
     *
     * Prefer the List interface as the reference type when
     * you do not need ArrayList-specific methods.
     *
     *
     * 5. INTERNAL DATA STRUCTURE
     *
     * ArrayList internally uses an array to store elements.
     *
     * In OpenJDK implementations, the backing array is
     * represented by an Object[].
     *
     * Conceptually:
     *
     *     Object[] elementData;
     *
     * Example:
     *
     *     ArrayList<Integer> numbers = new ArrayList<>();
     *
     *     numbers.add(10);
     *     numbers.add(20);
     *     numbers.add(30);
     *
     * Internal conceptual representation:
     *
     *     elementData:
     *
     *     Index:      0       1       2       3       4
     *               [10]    [20]    [30]    [  ]    [  ]
     *
     * Only the first three positions contain elements.
     *
     * The remaining positions are unused capacity.
     *
     * IMPORTANT:
     * ArrayList does not create a separate node for each element.
     * It stores element references in an array.
     *
     *
     * 6. SIZE VS CAPACITY
     *
     * These two concepts are extremely important.
     *
     * SIZE:
     * Number of elements currently stored.
     *
     * CAPACITY:
     * Number of elements the internal array can hold
     * without growing.
     *
     * Example:
     *
     *     ArrayList<Integer> numbers = new ArrayList<>(10);
     *
     *     numbers.add(10);
     *     numbers.add(20);
     *     numbers.add(30);
     *
     *     System.out.println(numbers.size()); // 3
     *
     * Capacity = 10
     * Size     = 3
     *
     * IMPORTANT:
     *
     * size() returns the number of elements.
     * ArrayList does not expose a public capacity() method.
     *
     * The capacity is an internal implementation detail.
     *
     *
     * 7. DEFAULT CAPACITY
     *
     * In commonly used OpenJDK implementations:
     *
     * - A default-constructed ArrayList initially uses a
     *   shared empty array internally.
     * - Its first element insertion grows the backing array
     *   to the default capacity of 10.
     *
     * IMPORTANT:
     *
     * Do not assume the backing array is physically allocated
     * with length 10 immediately when the constructor runs.
     *
     * The List contract does not specify a default capacity.
     *
     * This is an OpenJDK implementation detail.
     *
     *
     * 8. ARRAYLIST CONSTRUCTORS
     *
     * A. ArrayList()
     *
     *     ArrayList<String> list = new ArrayList<>();
     *
     * Creates an empty ArrayList.
     *
     * B. ArrayList(int initialCapacity)
     *
     *     ArrayList<String> list = new ArrayList<>(20);
     *
     * Creates an ArrayList with the requested initial capacity.
     *
     * It does not create 20 elements.
     *
     *     list.size() == 0
     *
     * C. ArrayList(Collection<? extends E> c)
     *
     *     ArrayList<String> list =
     *         new ArrayList<>(List.of("A", "B", "C"));
     *
     * Creates a list containing elements from another collection.
     *
     * IMPORTANT:
     * A negative initial capacity causes
     * IllegalArgumentException.
     *
     *
     * 9. HOW ARRAYLIST ADDITION WORKS
     *
     * Consider:
     *
     *     ArrayList<Integer> numbers = new ArrayList<>();
     *
     *     numbers.add(10);
     *     numbers.add(20);
     *
     * Conceptual process:
     *
     * 1. Check whether capacity is available.
     * 2. If available, store the element at the end.
     * 3. Increase the size.
     *
     * Example:
     *
     * Before:
     *
     *     [10] [20] [  ] [  ]
     *      size = 2
     *
     * Add 30:
     *
     *     numbers.add(30);
     *
     * After:
     *
     *     [10] [20] [30] [  ]
     *      size = 3
     *
     * If sufficient capacity exists, appending an element
     * requires constant-time work.
     *
     *
     * 10. WHAT HAPPENS WHEN CAPACITY IS FULL?
     *
     * Suppose:
     *
     *     Size     = 10
     *     Capacity = 10
     *
     * Now:
     *
     *     numbers.add(110);
     *
     * There is no free position in the backing array.
     *
     * ArrayList must grow its internal array.
     *
     * Conceptual process:
     *
     * 1. Detect insufficient capacity.
     * 2. Calculate a larger capacity.
     * 3. Allocate a new array.
     * 4. Copy existing elements.
     * 5. Replace the old backing array reference.
     * 6. Insert the new element.
     *
     * The old array becomes eligible for garbage collection
     * when no references to it remain.
     *
     *
     * 11. HOW ARRAYLIST GROWS
     *
     * In commonly used OpenJDK implementations, the new
     * capacity is approximately 1.5 times the old capacity.
     *
     * Conceptual example:
     *
     * Old capacity = 10
     *
     * New capacity = 10 + (10 / 2)
     *              = 15
     *
     * Next growth:
     *
     * Old capacity = 15
     *
     * New capacity = 15 + (15 / 2)
     *              = 22
     *
     * This is an implementation detail, not a guarantee
     * made by the ArrayList API contract.
     *
     * The implementation also handles minimum required
     * capacity and very large arrays.
     *
     *
     * 12. WHY IS ADD() AMORTIZED O(1)?
     *
     * Most additions do not require resizing.
     *
     * Occasionally, an addition triggers resizing and copying.
     *
     * Example:
     *
     *     add(10) -> O(1)
     *     add(20) -> O(1)
     *     add(30) -> O(1)
     *
     * But an addition that triggers resizing may take O(n).
     *
     * However, resizing does not happen on every addition.
     *
     * Across a long sequence of additions, the average cost
     * per append remains constant.
     *
     * This is called amortized O(1).
     *
     * IMPORTANT:
     *
     * Amortized O(1) does not mean every add() call takes O(1).
     *
     *
     * 13. ADDING ELEMENT AT A SPECIFIC INDEX
     *
     * Consider:
     *
     *     [10] [20] [30] [40]
     *
     * Now insert 15 at index 1:
     *
     *     numbers.add(1, 15);
     *
     * Java must shift existing elements to the right.
     *
     * Before:
     *
     *     [10] [20] [30] [40]
     *
     * After:
     *
     *     [10] [15] [20] [30] [40]
     *
     * Time complexity:
     *
     * - Insertion at the end: Amortized O(1).
     * - Insertion at the beginning: O(n).
     * - Insertion in the middle: O(n).
     *
     * Why?
     *
     * Because elements after the insertion position
     * must be shifted.
     *
     *
     * 14. HOW ARRAYLIST REMOVAL WORKS
     *
     * Consider:
     *
     *     [10] [20] [30] [40]
     *
     * Remove the element at index 1:
     *
     *     numbers.remove(1);
     *
     * Java removes 20 and shifts later elements left.
     *
     * Result:
     *
     *     [10] [30] [40]
     *
     * The unused reference at the end is cleared to avoid
     * retaining an unnecessary reference to the removed object.
     *
     * Time complexity:
     *
     * - Removing the last element: O(1).
     * - Removing the first element: O(n).
     * - Removing from the middle: O(n).
     *
     *
     * 15. REMOVING BY VALUE VS INDEX
     *
     * ArrayList inherits two overloaded remove() methods.
     *
     * remove(int index)
     *     Removes an element by index.
     *
     * remove(Object object)
     *     Removes the first matching object.
     *
     * Example:
     *
     *     ArrayList<Integer> numbers =
     *         new ArrayList<>(List.of(10, 20, 30));
     *
     *     numbers.remove(1);
     *
     * Result:
     *
     *     [10, 30]
     *
     * To remove the value 20:
     *
     *     numbers.remove(Integer.valueOf(20));
     *
     * This distinction is particularly important for
     * ArrayList<Integer>.
     *
     *
     * 16. ARRAYLIST TIME COMPLEXITY
     *
     * Operation                         Complexity
     * ------------------------------------------------
     * get(index)                        O(1)
     * set(index, element)                O(1)
     * add(element)                       Amortized O(1)
     * add(index, element)                O(n)
     * remove(index)                      O(n)
     * remove(object)                     O(n)
     * contains(element)                  O(n)
     * indexOf(element)                   O(n)
     * lastIndexOf(element)               O(n)
     * Iteration                          O(n)
     * clear()                            O(n) generally
     *
     * NOTE:
     *
     * The exact cost of an operation can depend on its position
     * and whether resizing is required.
     *
     *
     * 17. WHY IS GET() O(1)?
     *
     * ArrayList uses an array.
     *
     * Every element has a direct index.
     *
     * Suppose:
     *
     *     [10] [20] [30] [40] [50]
     *      0    1    2    3    4
     *
     * To get index 3:
     *
     *     numbers.get(3);
     *
     * Java can access the array position directly.
     *
     * It does not need to traverse previous elements.
     *
     * Therefore:
     *
     *     get(index) = O(1)
     *
     *
     * 18. WHY IS CONTAINS() O(n)?
     *
     * contains() checks whether an element exists.
     *
     * Example:
     *
     *     numbers.contains(40);
     *
     * ArrayList generally checks elements one by one
     * until a matching element is found.
     *
     * Worst case:
     * - The element is at the end.
     * - Or the element does not exist.
     *
     * Therefore:
     *
     *     contains() = O(n)
     *
     * Equality checks use equals(), not reference identity.
     *
     *
     * 19. RANDOMACCESS INTERFACE
     *
     * ArrayList implements RandomAccess.
     *
     * Package:
     *     java.util
     *
     * RandomAccess is a marker interface.
     *
     * It does not declare methods.
     *
     * Its purpose is to indicate that indexed access is
     * generally fast.
     *
     * Example:
     *
     *     if (list instanceof RandomAccess) {
     *         // Indexed traversal is generally suitable.
     *     }
     *
     * This marker can help algorithms choose a suitable
     * traversal strategy.
     *
     * ArrayList implements RandomAccess.
     * LinkedList does not.
     *
     *
     * 20. ARRAYLIST VS ARRAY
     *
     * Array:
     * - Fixed length.
     * - Can store primitives directly.
     * - Supports index-based access.
     * - Has a length field.
     *
     * ArrayList:
     * - Resizable storage.
     * - Stores object references.
     * - Provides List methods.
     * - Uses size() to obtain the number of elements.
     *
     * Example:
     *
     *     int[] array = new int[5];
     *
     *     ArrayList<Integer> list = new ArrayList<>();
     *
     * ArrayList<Integer> uses Integer objects, not primitive
     * int elements directly.
     *
     *
     * 21. GENERICS AND AUTOBOXING
     *
     * Collections use reference types as generic arguments.
     *
     * INVALID:
     *
     *     ArrayList<int> numbers;
     *
     * VALID:
     *
     *     ArrayList<Integer> numbers;
     *
     * Autoboxing:
     *
     *     numbers.add(10);
     *
     * Java converts int to Integer automatically.
     *
     * Unboxing:
     *
     *     int value = numbers.get(0);
     *
     * Java converts Integer to int automatically.
     *
     * IMPORTANT:
     *
     * Unboxing a null Integer causes NullPointerException.
     *
     *
     * 22. ARRAYLIST AND NULL
     *
     * ArrayList permits null elements.
     *
     * Example:
     *
     *     ArrayList<String> names = new ArrayList<>();
     *
     *     names.add("Vinay");
     *     names.add(null);
     *     names.add("Ravi");
     *     names.add(null);
     *
     * Result:
     *
     *     [Vinay, null, Ravi, null]
     *
     * Multiple null elements are allowed.
     *
     * Be careful when accessing or unboxing null values.
     *
     *
     * 23. ARRAYLIST AND ITERATORS
     *
     * ArrayList supports:
     *
     * - Iterator
     * - ListIterator
     * - Enhanced for-loop
     * - forEach()
     *
     * Example:
     *
     *     ArrayList<String> names =
     *         new ArrayList<>(List.of("Vinay", "Ravi", "Kiran"));
     *
     *     Iterator<String> iterator = names.iterator();
     *
     *     while (iterator.hasNext()) {
     *         System.out.println(iterator.next());
     *     }
     *
     * ArrayList's iterators are generally fail-fast.
     *
     * Iterators are covered in detail in the dedicated
     * Iterators folder.
     *
     *
     * 24. FAIL-FAST ITERATORS
     *
     * What happens if an ArrayList is structurally modified
     * while an iterator is traversing it?
     *
     * Example:
     *
     *     ArrayList<String> names =
     *         new ArrayList<>(List.of("Vinay", "Ravi", "Kiran"));
     *
     *     for (String name : names) {
     *         if (name.equals("Ravi")) {
     *             names.remove(name);
     *         }
     *     }
     *
     * This may throw ConcurrentModificationException.
     *
     * WHY?
     *
     * ArrayList iterators track structural modifications
     * using an internal modification count in common
     * OpenJDK implementations.
     *
     * When an unexpected structural change is detected,
     * the iterator may throw ConcurrentModificationException.
     *
     * IMPORTANT:
     *
     * Fail-fast is a bug-detection mechanism.
     * It is not a thread-safety guarantee.
     *
     * Detection is best-effort.
     *
     *
     * 25. SAFE REMOVAL WHILE ITERATING
     *
     * METHOD 1: Iterator.remove()
     *
     *     Iterator<String> iterator = names.iterator();
     *
     *     while (iterator.hasNext()) {
     *         String name = iterator.next();
     *
     *         if (name.equals("Ravi")) {
     *             iterator.remove();
     *         }
     *     }
     *
     * METHOD 2: removeIf()
     *
     *     names.removeIf(name -> name.equals("Ravi"));
     *
     * METHOD 3: Reverse index traversal
     *
     *     for (int i = names.size() - 1; i >= 0; i--) {
     *         if (names.get(i).equals("Ravi")) {
     *             names.remove(i);
     *         }
     *     }
     *
     * The reverse approach avoids skipping elements due
     * to shifting, although repeated removals can still
     * be expensive.
     *
     *
     * 26. ENSURECAPACITY()
     *
     * ArrayList provides:
     *
     *     ensureCapacity(int minCapacity)
     *
     * It requests enough internal capacity for at least
     * the specified number of elements.
     *
     * Example:
     *
     *     ArrayList<Integer> numbers = new ArrayList<>();
     *
     *     numbers.ensureCapacity(1000);
     *
     *     for (int i = 0; i < 1000; i++) {
     *         numbers.add(i);
     *     }
     *
     * WHY USE IT?
     *
     * If you know approximately how many elements will
     * be added, you can reduce repeated resizing.
     *
     * IMPORTANT:
     * ensureCapacity() is an ArrayList-specific method.
     * It is not part of the List interface.
     *
     *
     * 27. TRIMTOSIZE()
     *
     * ArrayList provides:
     *
     *     trimToSize()
     *
     * It requests that the backing array capacity be
     * reduced to the current size.
     *
     * Example:
     *
     *     ArrayList<Integer> numbers =
     *         new ArrayList<>(100);
     *
     *     numbers.add(10);
     *     numbers.add(20);
     *
     *     numbers.trimToSize();
     *
     * Before trimming:
     * - Size = 2.
     * - Capacity may be 100.
     *
     * After trimming:
     * - Capacity is requested to match size.
     *
     * IMPORTANT:
     * It may reduce unused storage but can require copying.
     *
     * Avoid calling it repeatedly during normal additions.
     *
     *
     * 28. CLONE()
     *
     * ArrayList supports clone().
     *
     * Example:
     *
     *     ArrayList<Integer> original =
     *         new ArrayList<>(List.of(10, 20, 30));
     *
     *     ArrayList<Integer> copy =
     *         (ArrayList<Integer>) original.clone();
     *
     * This creates a shallow copy.
     *
     * A shallow copy means:
     * - The list structure is copied.
     * - The element references are copied.
     * - The referenced objects are not cloned.
     *
     * Modifying the list structure of one does not change
     * the structure of the other.
     *
     * However, mutable objects stored in both lists may
     * still be shared.
     *
     *
     * 29. TOARRAY()
     *
     * ArrayList provides two important toArray() forms.
     *
     * A. Object[] toArray()
     *
     *     Object[] array = numbers.toArray();
     *
     * B. Typed array
     *
     *     Integer[] array = numbers.toArray(new Integer[0]);
     *
     * C. Array generator form (Java 11+)
     *
     *     Integer[] array = numbers.toArray(Integer[]::new);
     *
     * The typed forms are useful when a correctly typed
     * array is required.
     *
     *
     * 30. SUBLIST()
     *
     * ArrayList inherits subList() from List.
     *
     * Example:
     *
     *     ArrayList<Integer> numbers =
     *         new ArrayList<>(List.of(10, 20, 30, 40, 50));
     *
     *     List<Integer> part = numbers.subList(1, 4);
     *
     * Result:
     *
     *     [20, 30, 40]
     *
     * IMPORTANT:
     *
     * This is a backed view, not an independent ArrayList copy.
     *
     * To create an independent shallow copy:
     *
     *     ArrayList<Integer> copy =
     *         new ArrayList<>(numbers.subList(1, 4));
     *
     *
     * 31. ARRAYLIST SORTING
     *
     * ArrayList inherits sort() through the List interface.
     *
     * Example:
     *
     *     ArrayList<Integer> numbers =
     *         new ArrayList<>(List.of(30, 10, 20));
     *
     *     numbers.sort(null);
     *
     * Result:
     *
     *     [10, 20, 30]
     *
     * Reverse order:
     *
     *     numbers.sort(Comparator.reverseOrder());
     *
     * Custom sorting:
     *
     *     numbers.sort(
     *         Comparator.comparingInt(String::length)
     *     );
     *
     * Sorting is covered separately in Phase-02.
     *
     *
     * 32. ARRAYLIST THREAD SAFETY
     *
     * ArrayList is NOT synchronized.
     *
     * Multiple threads modifying the same ArrayList without
     * proper coordination can cause race conditions.
     *
     * Example:
     *
     *     ArrayList<Integer> numbers = new ArrayList<>();
     *
     * Two threads adding elements concurrently may interfere
     * with one another.
     *
     * POSSIBLE SOLUTIONS:
     *
     * 1. Collections.synchronizedList()
     *
     *     List<Integer> numbers =
     *         Collections.synchronizedList(
     *             new ArrayList<>()
     *         );
     *
     * 2. CopyOnWriteArrayList
     *
     *     List<Integer> numbers =
     *         new CopyOnWriteArrayList<>();
     *
     * 3. External synchronization or another concurrency design.
     *
     * IMPORTANT:
     * Thread-safe access and atomic multi-step operations
     * are different concerns.
     *
     * Synchronization requirements depend on the operations
     * performed and the concurrency design.
     *
     *
     * 33. SYNCHRONIZEDLIST()
     *
     * Collections provides:
     *
     *     Collections.synchronizedList(list)
     *
     * Example:
     *
     *     List<String> names =
     *         Collections.synchronizedList(
     *             new ArrayList<>()
     *         );
     *
     * This returns a synchronized wrapper around the List.
     *
     * IMPORTANT:
     *
     * Iteration must be manually synchronized on the returned
     * List:
     *
     *     synchronized (names) {
     *         for (String name : names) {
     *             System.out.println(name);
     *         }
     *     }
     *
     * The wrapper synchronizes individual method calls.
     * The synchronized block protects the complete traversal.
     *
     *
     * 34. COPYONWRITEARRAYLIST VS ARRAYLIST
     *
     * ArrayList:
     * - Not thread-safe.
     * - Efficient general-purpose mutable List.
     * - No copying of the entire backing array on every write.
     *
     * CopyOnWriteArrayList:
     * - Thread-safe.
     * - Creates a new backing array for mutations.
     * - Iterators observe a snapshot.
     * - Useful when reads greatly outnumber writes.
     *
     * Do not choose CopyOnWriteArrayList for write-heavy
     * workloads without considering its copying cost.
     *
     *
     * 35. ARRAYLIST MEMORY USAGE
     *
     * ArrayList stores references in an internal array.
     *
     * Important memory components:
     *
     * 1. ArrayList object itself.
     * 2. Internal reference array.
     * 3. Referenced element objects.
     *
     * ArrayList does not store every element object directly
     * inside the internal array.
     *
     * It stores references to those objects.
     *
     * Therefore, memory usage includes both the backing array
     * and the referenced objects.
     *
     * LinkedList generally requires additional per-node
     * reference storage.
     *
     *
     * 36. ARRAYLIST AND GARBAGE COLLECTION
     *
     * When an element is removed:
     *
     * - ArrayList shifts remaining references.
     * - The vacated array position is cleared.
     * - This avoids keeping an unnecessary reference alive.
     *
     * If no other reachable references point to the removed
     * object, it may eventually be garbage collected.
     *
     * IMPORTANT:
     * Removing an element from the List does not immediately
     * destroy the object.
     *
     * Garbage collection is managed by the JVM.
     *
     *
     * 37. JAVA VERSION FEATURES RELATED TO ARRAYLIST
     *
     * Java 1.2:
     * - Collections Framework introduced.
     * - ArrayList introduced as a modern List implementation.
     *
     * Java 5:
     * - Generics.
     * - Enhanced for-loop.
     *
     * Java 8:
     * - removeIf().
     * - replaceAll().
     * - sort().
     * - forEach().
     * - Spliterator support.
     *
     * Java 9:
     * - Convenient List factory methods such as List.of().
     *
     * Java 10:
     * - List.copyOf().
     *
     * Java 11:
     * - toArray(IntFunction<T[]> generator) support.
     *
     * Java 21:
     * - ArrayList participates in the SequencedCollection
     *   hierarchy through List.
     *
     *
     * 38. ARRAYLIST VS LINKEDLIST
     *
     * Feature             ArrayList          LinkedList
     * ----------------------------------------------------
     * Internal structure  Resizable array    Doubly linked list
     * get(index)          O(1)               O(n)
     * set(index)          O(1)               O(n)
     * Add at end          Amortized O(1)     O(1)
     * Add at front        O(n)               O(1)
     * Remove at front     O(n)               O(1)
     * Memory overhead     Generally lower   Generally higher
     * Cache locality      Better             Generally poorer
     *
     * ArrayList is usually a good default for general-purpose
     * List operations.
     *
     * LinkedList is useful when its specific end-operation
     * behavior or Deque capabilities are needed.
     *
     *
     * 39. ARRAYLIST VS VECTOR
     *
     * Feature             ArrayList         Vector
     * ------------------------------------------------
     * Internal structure  Resizable array   Resizable array
     * Synchronization     No                Yes, per method
     * Performance         Usually faster    Synchronization overhead
     * Status              Modern choice     Legacy class
     *
     * Both implement List.
     *
     * Vector's method synchronization does not automatically
     * make a multi-call operation atomic.
     *
     *
     * 40. WHEN SHOULD YOU USE ARRAYLIST?
     *
     * Use ArrayList when:
     *
     * - You need an ordered collection.
     * - Duplicate elements are allowed.
     * - You frequently access elements by index.
     * - You frequently traverse elements.
     * - You mostly append elements.
     * - You need a general-purpose mutable List.
     *
     * Examples:
     *
     * - Student records.
     * - Employee records.
     * - Product catalogues.
     * - API response data.
     * - Search results.
     * - Transaction history.
     *
     *
     * 41. WHEN SHOULD YOU AVOID ARRAYLIST?
     *
     * Consider another data structure when:
     *
     * - You frequently insert/remove at the beginning.
     * - You need a queue or double-ended queue.
     * - You require efficient concurrent modification support.
     * - You need unique elements rather than duplicates.
     * - You need key-value storage.
     *
     * Possible alternatives:
     *
     * - LinkedList / ArrayDeque for particular Deque operations.
     * - CopyOnWriteArrayList for read-heavy concurrent workloads.
     * - HashSet for uniqueness.
     * - HashMap for key-value lookup.
     *
     *
     * 42. COMMON ARRAYLIST EXCEPTIONS
     *
     * IndexOutOfBoundsException:
     * - Invalid index supplied to get(), set(), add() or remove().
     *
     * IllegalArgumentException:
     * - Negative initial capacity passed to the constructor.
     *
     * ConcurrentModificationException:
     * - May occur when a fail-fast iterator detects an
     *   unexpected structural modification.
     *
     * UnsupportedOperationException:
     * - Can occur when using an unmodifiable List implementation.
     *
     * NullPointerException:
     * - Can occur during operations such as unboxing a null Integer.
     *
     *
     * 43. COMMON MISTAKES
     *
     * 1. Confusing size and capacity.
     *    size() is the number of elements, not capacity.
     *
     * 2. Assuming default construction immediately allocates
     *    a backing array of length 10.
     *    Common OpenJDK implementations defer this allocation.
     *
     * 3. Assuming every add() takes O(1).
     *    Resizing can make an individual addition O(n).
     *
     * 4. Assuming ArrayList is thread-safe.
     *    It is not synchronized.
     *
     * 5. Removing by value using remove(int).
     *    Use Integer.valueOf() when removing an Integer value.
     *
     * 6. Assuming subList() creates a separate copy.
     *    It normally returns a backed view.
     *
     * 7. Using ArrayList for frequent front insertions.
     *    Existing elements must shift.
     *
     * 8. Calling trimToSize() after every addition.
     *    This can create unnecessary copying.
     *
     * 9. Assuming removal immediately destroys an object.
     *    Garbage collection is separate.
     *
     * 10. Assuming ensureCapacity() changes size.
     *     It only requests internal storage capacity.
     *
     *
     * 44. INTERVIEW QUESTIONS
     *
     * FUNDAMENTALS:
     *
     * 1. What is ArrayList?
     * 2. Which interface does ArrayList implement?
     * 3. Which data structure does ArrayList use internally?
     * 4. Does ArrayList allow duplicates?
     * 5. Does ArrayList allow null?
     *
     * INTERNAL WORKING:
     *
     * 6. How does ArrayList work internally?
     * 7. What is the difference between size and capacity?
     * 8. What is the default capacity in common OpenJDK implementations?
     * 9. How does ArrayList grow?
     * 10. Why does ArrayList use an Object[]?
     * 11. What happens when the internal array becomes full?
     * 12. Why is add() amortized O(1)?
     *
     * PERFORMANCE:
     *
     * 13. Why is get(index) O(1)?
     * 14. Why is insertion in the middle O(n)?
     * 15. Why is contains() O(n)?
     * 16. What is RandomAccess?
     * 17. What is the time complexity of remove(index)?
     *
     * ADVANCED:
     *
     * 18. What does ensureCapacity() do?
     * 19. What does trimToSize() do?
     * 20. What is a fail-fast iterator?
     * 21. Is ArrayList thread-safe?
     * 22. What is the purpose of modCount in OpenJDK?
     * 23. What does clone() produce?
     * 24. Is subList() a copy or a view?
     * 25. What is the difference between ArrayList and Vector?
     * 26. What is the difference between ArrayList and LinkedList?
     *
     *
     * 45. FINAL REVISION CHECKLIST
     *
     * [ ] Understand ArrayList and its hierarchy.
     * [ ] Understand its internal Object[] backing array.
     * [ ] Understand size versus capacity.
     * [ ] Understand the default constructor behavior.
     * [ ] Understand initial capacity.
     * [ ] Understand how resizing works.
     * [ ] Understand amortized O(1).
     * [ ] Understand indexed insertion and removal.
     * [ ] Know all major time complexities.
     * [ ] Understand RandomAccess.
     * [ ] Understand null and duplicate support.
     * [ ] Understand fail-fast iterators.
     * [ ] Know safe removal during iteration.
     * [ ] Understand ensureCapacity().
     * [ ] Understand trimToSize().
     * [ ] Understand clone() and shallow copying.
     * [ ] Understand toArray().
     * [ ] Understand subList() and backed views.
     * [ ] Understand thread-safety limitations.
     * [ ] Understand memory and garbage collection behavior.
     * [ ] Know when to choose and avoid ArrayList.
     *
     *
     * 46. WHAT YOU SHOULD REMEMBER
     *
     * ArrayList = Resizable Array + List Interface.
     *
     * Internal storage:
     *     Object[] in common OpenJDK implementations.
     *
     * Size:
     *     Number of stored elements.
     *
     * Capacity:
     *     Available backing-array positions.
     *
     * get(index):
     *     O(1).
     *
     * add(element):
     *     Amortized O(1).
     *
     * add(index, element):
     *     O(n).
     *
     * remove(index):
     *     O(n) in general.
     *
     * ArrayList:
     *     Allows duplicates.
     *     Allows null.
     *     Maintains encounter order.
     *     Is not synchronized.
     *
     * ============================================================
     *                     END OF ARRAYLIST
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about ArrayList , Happy Learning ..!");
    }
}
