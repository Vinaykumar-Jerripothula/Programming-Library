
public class ListInterface {

    /*
     * ============================================================
     *                       LIST INTERFACE
     * ============================================================
     *
     * 1. WHAT IS A LIST?
     *
     * Imagine maintaining a list of students:
     *
     *     0 -> Vinay
     *     1 -> Ravi
     *     2 -> Kiran
     *     3 -> Arun
     *
     * Every student has a position.
     *
     * If you want the student at position 2, you can directly
     * request that element using its index.
     *
     * This is the fundamental idea behind a List.
     *
     * A List is an ordered collection of elements that supports
     * positional access and generally allows duplicate elements.
     *
     * A List maintains an encounter order.
     *
     * This means elements are returned in a defined sequence
     * when the list is traversed.
     *
     * IMPORTANT CHARACTERISTICS:
     *
     * 1. Maintains element order.
     * 2. Allows duplicate elements.
     * 3. Supports index-based access.
     * 4. Supports insertion at a particular position.
     * 5. Supports replacement at a particular position.
     * 6. Supports searching for elements.
     * 7. May allow null elements, depending on implementation.
     *
     *
     * 2. LIST INTERFACE DECLARATION
     *
     * Package:
     *     java.util
     *
     * Declaration:
     *
     *     public interface List<E> extends Collection<E>
     *
     * E represents the type of elements stored in the List.
     *
     * Examples:
     *
     *     List<String> names;
     *     List<Integer> numbers;
     *     List<Double> prices;
     *
     * List is an interface, not a concrete class.
     *
     * Therefore, we cannot create an object directly:
     *
     *     List<String> names = new List<>(); // INVALID
     *
     * We need an implementation:
     *
     *     List<String> names = new ArrayList<>();
     *
     * Here:
     * - List is the reference type.
     * - ArrayList is the implementation.
     *
     *
     * 3. LIST INTERFACE HIERARCHY
     *
     *                     Iterable
     *                         |
     *                     Collection
     *                         |
     *                       List
     *                         |
     *             +-----------+-----------+
     *             |           |           |
     *         ArrayList   LinkedList    Vector
     *                                      |
     *                                    Stack
     *
     * This is a simplified hierarchy.
     *
     * Important relationships:
     *
     * - List extends Collection.
     * - Collection extends Iterable.
     * - ArrayList implements List.
     * - LinkedList implements List and Deque.
     * - Vector implements List.
     * - Stack extends Vector.
     *
     * Other important implementation:
     *
     * - CopyOnWriteArrayList implements List and belongs to
     *   java.util.concurrent.
     *
     *
     * 4. WHY DOES LIST EXTEND COLLECTION?
     *
     * Collection already provides common operations such as:
     *
     *     add()
     *     remove()
     *     contains()
     *     size()
     *     isEmpty()
     *     clear()
     *     iterator()
     *
     * List inherits these operations.
     *
     * It additionally introduces positional operations:
     *
     *     get(index)
     *     set(index, element)
     *     add(index, element)
     *     remove(index)
     *
     * Think of it this way:
     *
     * Collection provides general collection operations.
     * List adds position-aware operations.
     *
     *
     * 5. CREATING A LIST
     *
     * Preferred approach:
     *
     *     List<String> names = new ArrayList<>();
     *
     * Other examples:
     *
     *     List<String> names = new LinkedList<>();
     *
     *     List<String> names = new Vector<>();
     *
     *     List<String> names = new CopyOnWriteArrayList<>();
     *
     * WHY USE THE LIST INTERFACE AS THE REFERENCE TYPE?
     *
     * Because your code can depend on the List contract rather
     * than a particular implementation.
     *
     * For example:
     *
     *     List<String> names = new ArrayList<>();
     *
     * Later, you may choose:
     *
     *     List<String> names = new LinkedList<>();
     *
     * Common List operations can remain unchanged.
     *
     * IMPORTANT:
     * Different implementations have different performance,
     * memory and concurrency characteristics.
     *
     * Programming to an interface does not mean every
     * implementation is suitable for every requirement.
     *
     *
     * 6. LIST ALLOWS DUPLICATE ELEMENTS
     *
     * Unlike a Set, a List allows duplicate elements.
     *
     * Example:
     *
     *     List<String> names = new ArrayList<>();
     *
     *     names.add("Vinay");
     *     names.add("Ravi");
     *     names.add("Vinay");
     *
     *     System.out.println(names);
     *
     * Output:
     *
     *     [Vinay, Ravi, Vinay]
     *
     * Both occurrences of Vinay are stored.
     *
     * Each occurrence occupies a separate position.
     *
     *
     * 7. LIST MAINTAINS ORDER
     *
     * Consider:
     *
     *     List<Integer> numbers =
     *         new ArrayList<>();
     *
     *     numbers.add(30);
     *     numbers.add(10);
     *     numbers.add(20);
     *
     * Traversal returns:
     *
     *     [30, 10, 20]
     *
     * The List interface maintains encounter order.
     *
     * IMPORTANT:
     *
     * Encounter order does not necessarily mean sorted order.
     *
     * Insertion order:
     *     [30, 10, 20]
     *
     * Sorted order:
     *     [10, 20, 30]
     *
     * A List does not automatically sort its elements.
     *
     *
     * 8. INDEX-BASED ACCESS
     *
     * A List uses zero-based indexing.
     *
     * Example:
     *
     *     List<String> names =
     *         new ArrayList<>();
     *
     *     names.add("Vinay");
     *     names.add("Ravi");
     *     names.add("Kiran");
     *
     *     System.out.println(names.get(0)); // Vinay
     *     System.out.println(names.get(1)); // Ravi
     *     System.out.println(names.get(2)); // Kiran
     *
     * Index positions:
     *
     *     First element  -> index 0
     *     Second element -> index 1
     *     Third element  -> index 2
     *
     * For a List of size n:
     *
     *     Valid indexes: 0 to n - 1
     *
     * Invalid examples:
     *
     *     names.get(-1);
     *     names.get(3);
     *
     * These throw IndexOutOfBoundsException.
     *
     * IMPORTANT:
     * Index-based access is part of the List contract.
     * Its performance depends on the implementation.
     *
     *
     * 9. ADDING ELEMENTS
     *
     * METHOD 1: add(element)
     *
     * Adds an element at the end.
     *
     *     List<String> names = new ArrayList<>();
     *
     *     names.add("Vinay");
     *     names.add("Ravi");
     *
     *     // [Vinay, Ravi]
     *
     * METHOD 2: add(index, element)
     *
     * Inserts an element at a specified position.
     *
     *     names.add(1, "Kiran");
     *
     *     // [Vinay, Kiran, Ravi]
     *
     * Existing elements from that position onward move right.
     *
     * METHOD 3: addAll(collection)
     *
     * Adds all elements from another collection at the end.
     *
     *     List<String> additional =
     *         List.of("Arun", "Suresh");
     *
     *     names.addAll(additional);
     *
     * METHOD 4: addAll(index, collection)
     *
     * Inserts all elements at the specified position.
     *
     *     names.addAll(1, additional);
     *
     * The order of elements from the supplied collection
     * is preserved in the insertion sequence.
     *
     *
     * 10. ACCESSING ELEMENTS
     *
     * get(index):
     *     Returns the element at the specified position.
     *
     *     String name = names.get(0);
     *
     * indexOf(element):
     *     Returns the index of the first matching occurrence.
     *
     *     int index = names.indexOf("Vinay");
     *
     * lastIndexOf(element):
     *     Returns the index of the last matching occurrence.
     *
     *     int index = names.lastIndexOf("Vinay");
     *
     * If the element is not found:
     *
     *     indexOf()     -> -1
     *     lastIndexOf() -> -1
     *
     *
     * 11. UPDATING ELEMENTS
     *
     * set(index, element) replaces an existing element.
     *
     * Example:
     *
     *     List<String> names =
     *         new ArrayList<>(List.of("Vinay", "Ravi", "Kiran"));
     *
     *     names.set(1, "Arun");
     *
     * Result:
     *
     *     [Vinay, Arun, Kiran]
     *
     * IMPORTANT:
     *
     * set() replaces an element.
     * It does not insert a new element.
     *
     * Compare:
     *
     *     names.set(1, "Arun");
     *
     * Replaces the element at index 1.
     *
     *     names.add(1, "Arun");
     *
     * Inserts a new element at index 1 and shifts
     * existing elements to the right.
     *
     *
     * 12. REMOVING ELEMENTS
     *
     * List provides two important overloaded remove() methods.
     *
     * remove(int index):
     *     Removes the element at the specified position.
     *
     * remove(Object object):
     *     Removes the first occurrence of the specified object.
     *
     * Example:
     *
     *     List<Integer> numbers =
     *         new ArrayList<>(List.of(10, 20, 30, 20));
     *
     *     numbers.remove(1);
     *
     * Result:
     *
     *     [10, 30, 20]
     *
     * It removed the element at index 1.
     *
     * To remove the value 20:
     *
     *     numbers.remove(Integer.valueOf(20));
     *
     * Result:
     *
     *     [10, 30]
     *
     * WHY IS THIS IMPORTANT?
     *
     * Because Java selects an overloaded method based on
     * the argument type.
     *
     * For List<Integer>:
     *
     *     remove(1)
     *
     * Calls remove(int index).
     *
     *     remove(Integer.valueOf(1))
     *
     * Calls remove(Object object).
     *
     *
     * 13. OTHER IMPORTANT COLLECTION METHODS
     *
     * List inherits several methods from Collection.
     *
     * size():
     *     Returns the number of elements.
     *
     * isEmpty():
     *     Returns true if there are no elements.
     *
     * contains(element):
     *     Checks whether an element exists.
     *
     * containsAll(collection):
     *     Checks whether all specified elements are present.
     *
     * removeAll(collection):
     *     Removes elements that are also in the supplied collection.
     *
     * retainAll(collection):
     *     Retains only elements that are in the supplied collection.
     *
     * clear():
     *     Removes all elements.
     *
     * toArray():
     *     Converts the List into an array.
     *
     * toArray(generator):
     *     Creates an array of the requested type.
     *
     * Example:
     *
     *     String[] array = names.toArray(new String[0]);
     *
     *
     * 14. LIST EQUALITY
     *
     * Lists use element-based equality.
     *
     * Two Lists are equal when:
     *
     * 1. They have the same size.
     * 2. Corresponding elements are equal.
     * 3. Elements appear in the same order.
     *
     * Example:
     *
     *     List<Integer> first =
     *         List.of(10, 20, 30);
     *
     *     List<Integer> second =
     *         List.of(10, 20, 30);
     *
     *     List<Integer> third =
     *         List.of(30, 20, 10);
     *
     *     System.out.println(first.equals(second)); // true
     *     System.out.println(first.equals(third));  // false
     *
     * WHY?
     *
     * List equality considers position and element equality.
     *
     * It does not simply compare whether both Lists contain
     * the same elements.
     *
     * hashCode():
     *     List also defines an order-sensitive hash code.
     *
     * Equal Lists must have equal hash codes.
     *
     *
     * 15. NULL ELEMENTS
     *
     * The List interface does not universally guarantee that
     * every implementation accepts null.
     *
     * Some implementations permit null.
     * Others reject it.
     *
     * Examples:
     *
     *     ArrayList permits null elements.
     *
     *     LinkedList permits null elements.
     *
     *     CopyOnWriteArrayList permits null elements.
     *
     *     List.of() rejects null elements.
     *
     * Therefore, always check the implementation's contract
     * before relying on null support.
     *
     *
     * 16. LIST ITERATION
     *
     * A List can be traversed in several ways.
     *
     * METHOD 1: Traditional for-loop
     *
     *     for (int i = 0; i < names.size(); i++) {
     *         System.out.println(names.get(i));
     *     }
     *
     * METHOD 2: Enhanced for-loop
     *
     *     for (String name : names) {
     *         System.out.println(name);
     *     }
     *
     * METHOD 3: Iterator
     *
     *     Iterator<String> iterator = names.iterator();
     *
     *     while (iterator.hasNext()) {
     *         System.out.println(iterator.next());
     *     }
     *
     * METHOD 4: ListIterator
     *
     *     ListIterator<String> iterator =
     *         names.listIterator();
     *
     *     while (iterator.hasNext()) {
     *         System.out.println(iterator.next());
     *     }
     *
     * ListIterator is especially useful because it supports:
     * - Forward traversal.
     * - Backward traversal.
     * - Element replacement.
     * - Element insertion.
     * - Element removal.
     *
     * These iterator interfaces will be studied in detail
     * in the dedicated Iterators folder.
     *
     *
     * 17. LISTITERATOR
     *
     * List provides:
     *
     *     listIterator()
     *     listIterator(index)
     *
     * Unlike Iterator, ListIterator supports bidirectional
     * traversal and modification operations.
     *
     * Important methods:
     *
     * hasNext()
     * next()
     * hasPrevious()
     * previous()
     * nextIndex()
     * previousIndex()
     * add(element)
     * set(element)
     * remove()
     *
     * Example:
     *
     *     ListIterator<String> iterator =
     *         names.listIterator();
     *
     *     while (iterator.hasNext()) {
     *         System.out.println(iterator.next());
     *     }
     *
     * Backward traversal:
     *
     *     while (iterator.hasPrevious()) {
     *         System.out.println(iterator.previous());
     *     }
     *
     *
     * 18. SUBLIST
     *
     * List provides:
     *
     *     subList(fromIndex, toIndex)
     *
     * It returns a view of a portion of the original List.
     *
     * The starting index is inclusive.
     * The ending index is exclusive.
     *
     * Example:
     *
     *     List<Integer> numbers =
     *         new ArrayList<>(
     *             List.of(10, 20, 30, 40, 50)
     *         );
     *
     *     List<Integer> part =
     *         numbers.subList(1, 4);
     *
     *     System.out.println(part);
     *
     * Output:
     *
     *     [20, 30, 40]
     *
     * IMPORTANT:
     *
     * subList() normally returns a backed view, not an
     * independent copy.
     *
     * Changes made through the view are reflected in
     * the original List.
     *
     * Structural modifications to the backing List outside
     * the view can make the view's behavior undefined by
     * the List contract, except through the returned view.
     *
     * To create an independent shallow copy:
     *
     *     List<Integer> copy =
     *         new ArrayList<>(numbers.subList(1, 4));
     *
     *
     * 19. REPLACING ELEMENTS USING REPLACEALL()
     *
     * List provides:
     *
     *     replaceAll(UnaryOperator<E> operator)
     *
     * It applies an operation to every element.
     *
     * Example:
     *
     *     List<Integer> numbers =
     *         new ArrayList<>(List.of(1, 2, 3));
     *
     *     numbers.replaceAll(n -> n * 10);
     *
     * Result:
     *
     *     [10, 20, 30]
     *
     * This method modifies the List.
     *
     * The operation may be unsupported by an unmodifiable List.
     *
     *
     * 20. SORTING A LIST
     *
     * List provides:
     *
     *     sort(Comparator<? super E> comparator)
     *
     * Example:
     *
     *     List<Integer> numbers =
     *         new ArrayList<>(List.of(30, 10, 20));
     *
     *     numbers.sort(null);
     *
     * Result:
     *
     *     [10, 20, 30]
     *
     * Passing null uses natural ordering.
     *
     * Reverse ordering:
     *
     *     numbers.sort(Comparator.reverseOrder());
     *
     * Custom sorting:
     *
     *     numbers.sort(
     *         Comparator.comparingInt(String::length)
     *     );
     *
     * Detailed sorting concepts belong in:
     *
     *     Phase-02/02-Sorting-Searching-and-Comparison
     *
     *
     * 21. REMOVEIF()
     *
     * List inherits removeIf() from Collection.
     *
     * It removes elements matching a condition.
     *
     * Example:
     *
     *     List<Integer> numbers =
     *         new ArrayList<>(List.of(10, 15, 20, 25, 30));
     *
     *     numbers.removeIf(n -> n % 2 != 0);
     *
     * Result:
     *
     *     [10, 20, 30]
     *
     * It removes all odd numbers.
     *
     * This is often clearer than manually removing elements
     * inside a loop.
     *
     *
     * 22. STREAM SUPPORT
     *
     * List inherits stream() and parallelStream()
     * from Collection.
     *
     * Example:
     *
     *     List<Integer> numbers =
     *         List.of(10, 20, 30, 40);
     *
     *     numbers.stream()
     *            .filter(n -> n > 20)
     *            .forEach(System.out::println);
     *
     * Output:
     *
     *     30
     *     40
     *
     * Streams provide a declarative way to process data.
     *
     * A stream does not automatically modify the original List.
     *
     *
     * 23. MUTABLE, FIXED-SIZE AND UNMODIFIABLE LISTS
     *
     * Not every object implementing List supports every
     * modification operation.
     *
     * A. MUTABLE LIST
     *
     *     List<String> names = new ArrayList<>();
     *
     *     names.add("Vinay");
     *     names.remove("Vinay");
     *     names.set(0, "Ravi"); // Only if index 0 exists
     *
     * A mutable List generally supports modifications.
     *
     * B. FIXED-SIZE LIST
     *
     *     List<String> names =
     *         Arrays.asList("Vinay", "Ravi");
     *
     * Characteristics:
     * - Size cannot change.
     * - set() is supported.
     * - add() is unsupported.
     * - remove() is unsupported.
     *
     * C. UNMODIFIABLE LIST
     *
     *     List<String> names =
     *         List.of("Vinay", "Ravi");
     *
     * Characteristics:
     * - Modification operations are unsupported.
     * - Null elements are not allowed.
     * - add(), remove() and set() throw
     *   UnsupportedOperationException.
     *
     * IMPORTANT:
     *
     * Fixed-size and unmodifiable are not the same.
     *
     * Arrays.asList() allows replacement.
     * List.of() does not allow replacement.
     *
     *
     * 24. LIST FACTORY METHODS
     *
     * Java 9 introduced convenient factory methods:
     *
     *     List.of()
     *     List.of(element)
     *     List.of(element1, element2)
     *
     * Example:
     *
     *     List<String> names =
     *         List.of("Vinay", "Ravi", "Kiran");
     *
     * These Lists are unmodifiable and reject null.
     *
     * Java 10 introduced:
     *
     *     List.copyOf(collection)
     *
     * Example:
     *
     *     List<String> original =
     *         new ArrayList<>(List.of("Vinay", "Ravi"));
     *
     *     List<String> copy = List.copyOf(original);
     *
     * The returned List is unmodifiable.
     *
     * It is a shallow copy:
     * The elements themselves are not cloned.
     *
     *
     * 25. ARRAYS.ASLIST() VS LIST.OF() VS LIST.COPYOF()
     *
     * Arrays.asList():
     *
     *     List<String> names =
     *         Arrays.asList("Vinay", "Ravi");
     *
     * - Fixed-size List backed by an array.
     * - Allows set().
     * - Does not allow add() or remove().
     * - Allows null elements.
     *
     * List.of():
     *
     *     List<String> names =
     *         List.of("Vinay", "Ravi");
     *
     * - Creates an unmodifiable List.
     * - Does not allow set(), add() or remove().
     * - Rejects null elements.
     *
     * List.copyOf():
     *
     *     List<String> copy = List.copyOf(names);
     *
     * - Creates an unmodifiable List containing elements
     *   from the supplied collection.
     * - Rejects null elements.
     * - Does not create a deep copy of the elements.
     *
     *
     * 26. LIST AND THREAD SAFETY
     *
     * The List interface itself does not guarantee
     * thread safety.
     *
     * Common implementations:
     *
     * ArrayList:
     *     Not synchronized.
     *
     * LinkedList:
     *     Not synchronized.
     *
     * Vector:
     *     Individual methods are synchronized.
     *
     * Stack:
     *     Inherits synchronized methods from Vector.
     *
     * CopyOnWriteArrayList:
     *     Thread-safe implementation.
     *
     * Synchronized wrapper:
     *
     *     List<String> names =
     *         Collections.synchronizedList(
     *             new ArrayList<>()
     *         );
     *
     * IMPORTANT:
     *
     * Thread safety depends on the implementation and
     * how the List is accessed.
     *
     * Synchronizing individual methods does not automatically
     * make a sequence of multiple method calls atomic.
     *
     *
     * 27. SEQUENCED COLLECTIONS (JAVA 21+)
     *
     * Java 21 introduced the SequencedCollection interface.
     *
     * It represents a collection with a defined encounter order.
     *
     * Declaration conceptually:
     *
     *     SequencedCollection<E> extends Collection<E>
     *
     * It provides operations such as:
     *
     *     addFirst(element)
     *     addLast(element)
     *     getFirst()
     *     getLast()
     *     removeFirst()
     *     removeLast()
     *     reversed()
     *
     * List extends SequencedCollection in Java 21+.
     *
     * This means Lists have a common contract for operations
     * involving the first and last elements.
     *
     * IMPORTANT:
     * This is a Java 21 feature.
     * Code targeting older Java versions cannot use it.
     *
     *
     * 28. LIST METHOD CATEGORY REVISION
     *
     * ADD:
     *     add()
     *     add(index, element)
     *     addAll()
     *     addAll(index, collection)
     *
     * READ:
     *     get()
     *     indexOf()
     *     lastIndexOf()
     *
     * UPDATE:
     *     set()
     *     replaceAll()
     *
     * DELETE:
     *     remove(index)
     *     remove(object)
     *     removeAll()
     *     removeIf()
     *     retainAll()
     *     clear()
     *
     * TRAVERSAL:
     *     iterator()
     *     listIterator()
     *     forEach()
     *     stream()
     *     parallelStream()
     *
     * OTHER:
     *     size()
     *     isEmpty()
     *     contains()
     *     subList()
     *     sort()
     *     toArray()
     *     equals()
     *     hashCode()
     *
     *
     * 29. LIST VS COLLECTION
     *
     * Collection:
     * - Parent interface of List.
     * - Represents a group of elements.
     * - Does not provide index-based access.
     *
     * List:
     * - Extends Collection.
     * - Maintains encounter order.
     * - Allows duplicates.
     * - Provides positional access.
     *
     * Example:
     *
     *     Collection<String> names = new ArrayList<>();
     *
     *     names.add("Vinay");
     *
     *     // names.get(0); // INVALID
     *
     *     List<String> students = new ArrayList<>();
     *
     *     students.add("Vinay");
     *
     *     System.out.println(students.get(0)); // VALID
     *
     *
     * 30. LIST VS SET
     *
     * List:
     * - Allows duplicates.
     * - Maintains encounter order.
     * - Supports index-based access.
     *
     * Set:
     * - Does not allow duplicate elements.
     * - Does not provide index-based access.
     * - Ordering depends on implementation.
     *
     * Example:
     *
     *     List<Integer> list =
     *         List.of(10, 20, 10);
     *
     *     Set<Integer> set =
     *         Set.of(10, 20);
     *
     * List stores repeated values.
     * Set stores unique values.
     *
     *
     * 31. LIST PERFORMANCE
     *
     * The List interface does not promise a particular
     * time complexity for its operations.
     *
     * Complexity depends on the implementation.
     *
     * Examples:
     *
     * ArrayList:
     * - get(index): O(1)
     * - add(element): Amortized O(1)
     * - add(index, element): O(n)
     *
     * LinkedList:
     * - get(index): O(n)
     * - addFirst(): O(1)
     * - addLast(): O(1)
     *
     * CopyOnWriteArrayList:
     * - Reads are efficient.
     * - Writes involve copying the underlying array.
     *
     * These implementation details are covered in their
     * individual files.
     *
     *
     * 32. COMMON EXCEPTIONS
     *
     * IndexOutOfBoundsException:
     * - Invalid index supplied to positional operations.
     *
     * UnsupportedOperationException:
     * - Operation is not supported by the List implementation.
     *
     * NullPointerException:
     * - May occur when null is not supported or an operation
     *   requires a non-null argument.
     *
     * ConcurrentModificationException:
     * - May be thrown when a fail-fast iterator detects
     *   an unexpected structural modification.
     *
     *
     * 33. COMMON MISCONCEPTIONS
     *
     * 1. List is a class.
     *    WRONG: List is an interface.
     *
     * 2. Every List is an ArrayList.
     *    WRONG: ArrayList is only one implementation.
     *
     * 3. List automatically sorts elements.
     *    WRONG: List preserves encounter order, not sorted order.
     *
     * 4. Every List allows null.
     *    WRONG: Null support depends on implementation.
     *
     * 5. set() inserts a new element.
     *    WRONG: set() replaces an existing element.
     *
     * 6. subList() creates a fully independent copy.
     *    WRONG: It normally returns a backed view.
     *
     * 7. All List implementations have O(1) get().
     *    WRONG: Complexity depends on implementation.
     *
     * 8. List is always thread-safe.
     *    WRONG: The interface makes no such guarantee.
     *
     * 9. Arrays.asList() creates an ordinary resizable ArrayList.
     *    WRONG: It creates a fixed-size List backed by an array.
     *
     * 10. List.of() creates a mutable List.
     *     WRONG: It creates an unmodifiable List.
     *
     *
     * 34. REAL-WORLD USE CASES
     *
     * Student Management:
     *     List<Student>
     *
     * Product Catalogue:
     *     List<Product>
     *
     * API Response:
     *     List<EmployeeDTO>
     *
     * Order Items:
     *     List<OrderItem>
     *
     * Search Results:
     *     List<SearchResult>
     *
     * Transaction History:
     *     List<Transaction>
     *
     * A List is useful whenever:
     * - Position matters.
     * - Order matters.
     * - Duplicate values are meaningful.
     *
     *
     * 35. INTERVIEW QUESTIONS
     *
     * FUNDAMENTALS:
     *
     * 1. What is the List interface?
     * 2. What is the relationship between List and Collection?
     * 3. What are the main characteristics of List?
     * 4. Does List allow duplicate elements?
     * 5. Does List maintain insertion order?
     * 6. Can a List contain null?
     * 7. Can we instantiate List directly?
     *
     * METHODS:
     *
     * 8. Difference between add() and set()?
     * 9. Difference between remove(int) and remove(Object)?
     * 10. What do indexOf() and lastIndexOf() return?
     * 11. What is the purpose of subList()?
     * 12. What is the difference between Iterator and ListIterator?
     * 13. What does replaceAll() do?
     * 14. What does removeIf() do?
     *
     * ADVANCED:
     *
     * 15. How does List equality work?
     * 16. What is the difference between Arrays.asList() and List.of()?
     * 17. What is List.copyOf()?
     * 18. What is a backed view?
     * 19. Is the List interface thread-safe?
     * 20. What are Sequenced Collections?
     *
     *
     * 36. FINAL REVISION CHECKLIST
     *
     * [ ] Understand the List interface and its hierarchy.
     * [ ] Understand why List extends Collection.
     * [ ] Understand ordering and duplicate behavior.
     * [ ] Understand zero-based indexing.
     * [ ] Know all important List methods.
     * [ ] Understand add() versus set().
     * [ ] Understand remove(int) versus remove(Object).
     * [ ] Understand List equality and hashCode().
     * [ ] Understand Iterator and ListIterator.
     * [ ] Understand subList() and backed views.
     * [ ] Understand replaceAll(), removeIf() and sort().
     * [ ] Understand mutable and unmodifiable Lists.
     * [ ] Understand List.of() and List.copyOf().
     * [ ] Understand Arrays.asList().
     * [ ] Understand List thread-safety considerations.
     * [ ] Understand SequencedCollection in Java 21+.
     *
     *
     * 37. WHAT YOU SHOULD REMEMBER
     *
     * List is an interface in java.util.
     *
     * List extends Collection.
     *
     * List represents an ordered collection.
     *
     * Duplicate elements are allowed.
     *
     * Elements can be accessed using indexes.
     *
     * The interface defines the contract.
     *
     * The implementation determines internal storage,
     * performance, memory usage and concurrency behavior.
     *
     * IMPORTANT:
     *
     * ArrayList, LinkedList, Vector, Stack and
     * CopyOnWriteArrayList deserve individual study.
     *
     * ============================================================
     *                    END OF LIST INTERFACE
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about List Interface , Happy Learning ..!");
    }
}
