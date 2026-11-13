
public class ArrayBasedDataStructures {

    /*
     * ============================================================
     * ARRAY-BASED DATA STRUCTURES IN JAVA
     * ============================================================
     *
     * 1. WHAT IS A DATA STRUCTURE?
     *
     * A data structure is a way of organizing and storing data
     * so that we can perform operations efficiently.
     *
     * Examples:
     *
     * Array       -> Stores elements in indexed positions.
     * LinkedList  -> Stores elements using connected nodes.
     * Stack       -> Follows LIFO.
     * Queue       -> Follows FIFO.
     * HashTable   -> Stores data using hashing.
     * Tree        -> Organizes data hierarchically.
     *
     * Different data structures solve different problems.
     *
     *
     * ============================================================
     * 2. WHAT IS AN ARRAY?
     * ============================================================
     *
     * An array is a fixed-length data structure that stores
     * multiple values of the same component type.
     *
     * Example:
     *
     * int[] numbers = {10, 20, 30, 40, 50};
     *
     * Each element can be accessed using an index.
     *
     * Index:  0   1   2   3   4
     * Value: 10  20  30  40  50
     *
     * IMPORTANT:
     *
     * Array indexing starts from zero.
     *
     * Accessing numbers[2] returns 30.
     *
     * Array length:
     *
     * numbers.length
     *
     * Result: 5
     *
     * Arrays have a fixed length after creation.
     *
     * We cannot increase or decrease the length of an existing
     * array.
     *
     *
     * ============================================================
     * 3. HOW ARE ARRAYS CREATED?
     * ============================================================
     *
     * Declaration:
     *
     * int[] numbers;
     *
     * Creation:
     *
     * numbers = new int[5];
     *
     * Declaration and creation:
     *
     * int[] numbers = new int[5];
     *
     * Initialization:
     *
     * int[] numbers = {10, 20, 30, 40, 50};
     *
     * Another valid declaration:
     *
     * int numbers[] = new int[5];
     *
     * Recommended style:
     *
     * int[] numbers;
     *
     * The array object is created on the heap.
     *
     * The variable stores a reference to that array object.
     *
     * An array's length is available through its length field.
     *
     * Unlike String, arrays do not have a length() method.
     *
     *
     * ============================================================
     * 4. HOW DOES ARRAY INDEXING WORK?
     * ============================================================
     *
     * An array provides direct access to elements using indexes.
     *
     * Example:
     *
     * int[] arr = {10, 20, 30, 40, 50};
     *
     * arr[0] -> 10
     * arr[1] -> 20
     * arr[2] -> 30
     *
     * Conceptually, the location of an element is calculated
     * using:
     *
     * Base address + (index * element size)
     *
     * This explains why array access is generally O(1).
     *
     * IMPORTANT:
     *
     * This is a conceptual memory model.
     *
     * Java guarantees indexed array access, but the actual
     * physical memory layout is managed by the JVM.
     *
     * Accessing an invalid index:
     *
     * arr[10]
     *
     * Throws:
     * ArrayIndexOutOfBoundsException.
     *
     *
     * ============================================================
     * 5. DEFAULT VALUES IN ARRAYS
     * ============================================================
     *
     * When an array is created, its elements receive default values.
     *
     * int[]       -> 0
     * long[]      -> 0L
     * short[]     -> 0
     * byte[]      -> 0
     * float[]     -> 0.0f
     * double[]    -> 0.0
     * char[]      -> '\u0000'
     * boolean[]   -> false
     * Object[]    -> null
     *
     * Example:
     *
     * int[] numbers = new int[3];
     *
     * Result:
     * [0, 0, 0]
     *
     * String[] names = new String[3];
     *
     * Result:
     * [null, null, null]
     *
     * IMPORTANT:
     *
     * Local variables do not automatically receive these defaults.
     * The default values apply to array elements.
     *
     *
     * ============================================================
     * 6. PRIMITIVE ARRAYS VS OBJECT ARRAYS
     * ============================================================
     *
     * Primitive array:
     *
     * int[] numbers = {10, 20, 30};
     *
     * Object array:
     *
     * String[] names = {"Vinay", "Anil", "Rahul"};
     *
     * Primitive arrays store primitive values as array elements.
     *
     * Object arrays store references to objects.
     *
     * Example:
     *
     * Student[] students = new Student[3];
     *
     * The array contains three null references initially.
     *
     * It does not automatically create three Student objects.
     *
     * You must create them separately:
     *
     * students[0] = new Student();
     *
     *
     * ============================================================
     * 7. MULTIDIMENSIONAL ARRAYS
     * ============================================================
     *
     * Java supports arrays of arrays.
     *
     * Two-dimensional array:
     *
     * int[][] matrix = {
     *     {1, 2, 3},
     *     {4, 5, 6},
     *     {7, 8, 9}
     * };
     *
     * Access:
     *
     * matrix[0][0] -> 1
     * matrix[1][2] -> 6
     *
     * IMPORTANT:
     *
     * Java multidimensional arrays are arrays whose elements
     * can themselves be arrays.
     *
     * They are not required to have equal row lengths.
     *
     * Such arrays are called jagged arrays.
     *
     * Example:
     *
     * int[][] arr = new int[3][];
     *
     * arr[0] = new int[2];
     * arr[1] = new int[4];
     * arr[2] = new int[3];
     *
     * Each row has a different length.
     *
     *
     * ============================================================
     * 8. ARRAY OPERATIONS AND TIME COMPLEXITY
     * ============================================================
     *
     * Access by index:
     * O(1)
     *
     * Update by index:
     * O(1)
     *
     * Search without sorting:
     * O(n)
     *
     * Search using binary search on sorted data:
     * O(log n)
     *
     * Insertion at the end when free space exists:
     * O(1)
     *
     * Insertion at the beginning:
     * O(n)
     *
     * Deletion from the beginning:
     * O(n)
     *
     * WHY ARE INSERTION AND DELETION O(n)?
     *
     * Elements may need to be shifted to maintain the required
     * positions.
     *
     * Example:
     *
     * [10, 20, 30, 40]
     *
     * Insert 5 at index 0:
     *
     * [5, 10, 20, 30, 40]
     *
     * Existing elements must shift to the right.
     *
     *
     * ============================================================
     * 9. WHAT IS A DYNAMIC ARRAY?
     * ============================================================
     *
     * A normal array has a fixed length.
     *
     * But what if we don't know how many elements we will store?
     *
     * Imagine:
     *
     * You start with 3 elements.
     * Later you add 5 more.
     * Later you add 100 more.
     *
     * A fixed-length array cannot grow automatically.
     *
     * A dynamic array solves this problem.
     *
     * A dynamic array manages an internal array and increases
     * its capacity when necessary.
     *
     * Java's ArrayList is a well-known dynamic-array implementation.
     *
     *
     * ============================================================
     * 10. SIZE VS CAPACITY
     * ============================================================
     *
     * These two terms are extremely important.
     *
     * SIZE:
     * Number of elements currently stored.
     *
     * CAPACITY:
     * Number of elements the internal array can hold before
     * it needs to grow.
     *
     * Example:
     *
     * Internal array capacity = 10
     * Actual elements stored  = 4
     *
     * Size = 4
     * Capacity = 10
     *
     * Visual representation:
     *
     * [10, 20, 30, 40, _, _, _, _, _, _]
     *
     * First 4 positions contain elements.
     * Remaining positions are unused capacity.
     *
     * IMPORTANT:
     *
     * ArrayList exposes size() but does not provide a public
     * capacity() method.
     *
     *
     * ============================================================
     * 11. HOW DOES A DYNAMIC ARRAY GROW?
     * ============================================================
     *
     * Suppose an internal array has capacity 4.
     *
     * [10, 20, 30, 40]
     *
     * Now we want to add 50.
     *
     * There is no free position.
     *
     * A dynamic array generally performs these steps:
     *
     * 1. Create a larger array.
     * 2. Copy existing elements into the new array.
     * 3. Store the new element.
     * 4. Use the new array internally.
     *
     * Conceptual example:
     *
     * Old array:
     * [10, 20, 30, 40]
     *
     * New array:
     * [10, 20, 30, 40, _, _, _, _]
     *
     * After adding 50:
     * [10, 20, 30, 40, 50, _, _, _]
     *
     * The original array is not resized.
     * A new array is allocated and elements are copied.
     *
     *
     * ============================================================
     * 12. WHY DOES A DYNAMIC ARRAY GROW BY MORE THAN ONE?
     * ============================================================
     *
     * Suppose capacity increases by only one each time:
     *
     * 4 -> 5 -> 6 -> 7 -> 8
     *
     * Every insertion after capacity is exhausted requires
     * copying all existing elements.
     *
     * This creates many expensive copy operations.
     *
     * Instead, dynamic arrays commonly grow geometrically.
     *
     * Example:
     *
     * 4 -> 8 -> 16 -> 32 -> 64
     *
     * The exact growth factor depends on the implementation.
     *
     * Geometric growth reduces the frequency of resizing.
     *
     *
     * ============================================================
     * 13. AMORTIZED TIME COMPLEXITY
     * ============================================================
     *
     * This is one of the most important concepts in dynamic arrays.
     *
     * Normal insertion:
     * O(1) when capacity is available.
     *
     * Insertion during resizing:
     * O(n) because existing elements must be copied.
     *
     * Does that mean every insertion is O(n)?
     *
     * No.
     *
     * We use amortized analysis.
     *
     * Amortized analysis studies the average cost per operation
     * over a sequence of operations.
     *
     * For a dynamic array with geometric growth:
     *
     * Individual append:
     * - Usually O(1).
     * - Occasionally O(n) during resizing.
     *
     * Amortized append:
     * O(1).
     *
     * IMPORTANT:
     *
     * Amortized O(1) does not mean every single insertion
     * takes constant time.
     *
     *
     * ============================================================
     * 14. ARRAYLIST INTERNAL STRUCTURE
     * ============================================================
     *
     * ArrayList is a resizable-array implementation of List.
     *
     * Internally, it maintains an array to store element references.
     *
     * Conceptually:
     *
     * ArrayList
     *     |
     *     v
     * Internal Object[]
     *     |
     *     +----> Element 1
     *     +----> Element 2
     *     +----> Element 3
     *
     * Important properties:
     *
     * - Maintains insertion order.
     * - Allows duplicate elements.
     * - Allows null elements.
     * - Supports indexed access.
     * - Can grow dynamically.
     * - Is not synchronized by default.
     *
     * ArrayList stores object references.
     *
     * For primitive values, wrapper objects are used through
     * autoboxing.
     *
     * Example:
     *
     * ArrayList<Integer> numbers = new ArrayList<>();
     *
     * numbers.add(10);
     *
     * The stored element is an Integer reference, not a primitive
     * int stored directly in the internal Object[].
     *
     *
     * ============================================================
     * 15. ARRAYLIST DEFAULT CAPACITY
     * ============================================================
     *
     * IMPORTANT IMPLEMENTATION DETAIL:
     *
     * In modern OpenJDK implementations, a default-constructed
     * ArrayList uses a shared empty array initially.
     *
     * Its backing array is allocated when the first element
     * is added.
     *
     * The default first growth is commonly to capacity 10.
     *
     * Example:
     *
     * ArrayList<Integer> list = new ArrayList<>();
     *
     * list.add(10);
     *
     * The backing array is allocated.
     *
     * IMPORTANT:
     *
     * This is an implementation detail, not a guaranteed
     * capacity contract of the List API.
     *
     * Do not depend on the exact internal capacity in application
     * code.
     *
     *
     * ============================================================
     * 16. ARRAYLIST GROWTH FACTOR
     * ============================================================
     *
     * In current OpenJDK implementations, ArrayList generally
     * grows its capacity by approximately 50% when it needs
     * more space.
     *
     * Conceptual growth:
     *
     * 10 -> 15 -> 22 -> 33 -> 49
     *
     * The exact sequence can depend on required minimum capacity
     * and implementation details.
     *
     * When growing:
     *
     * 1. A new backing array is allocated.
     * 2. Existing references are copied.
     * 3. The internal array reference is updated.
     *
     * ArrayList uses internal array-copying logic to perform
     * this operation.
     *
     * IMPORTANT:
     *
     * The exact growth factor is not part of the public API
     * contract and may change between implementations or versions.
     *
     *
     * ============================================================
     * 17. ARRAYLIST ADD OPERATION
     * ============================================================
     *
     * add(element):
     *
     * Adds an element at the end.
     *
     * Example:
     *
     * ArrayList<Integer> list = new ArrayList<>();
     *
     * list.add(10);
     * list.add(20);
     * list.add(30);
     *
     * Result:
     * [10, 20, 30]
     *
     * Complexity:
     *
     * Amortized O(1).
     *
     * Worst case during resizing:
     * O(n).
     *
     * add(index, element):
     *
     * Inserts an element at a particular position.
     *
     * Example:
     *
     * list.add(1, 15);
     *
     * Existing elements at and after index 1 shift right.
     *
     * Complexity:
     *
     * O(n) in general.
     *
     *
     * ============================================================
     * 18. ARRAYLIST GET AND SET OPERATIONS
     * ============================================================
     *
     * get(index):
     *
     * Returns the element at the specified index.
     *
     * Example:
     *
     * list.get(1);
     *
     * set(index, element):
     *
     * Replaces the element at the specified index.
     *
     * Example:
     *
     * list.set(1, 100);
     *
     * Neither operation changes the list's size.
     *
     * Complexity:
     *
     * get() -> O(1)
     * set() -> O(1)
     *
     *
     * ============================================================
     * 19. ARRAYLIST REMOVE OPERATION
     * ============================================================
     *
     * remove(index):
     *
     * Removes the element at a particular index.
     *
     * Example:
     *
     * ArrayList<Integer> list = new ArrayList<>(
     *     List.of(10, 20, 30, 40)
     * );
     *
     * list.remove(1);
     *
     * Result:
     * [10, 30, 40]
     *
     * Elements after the removed position shift left.
     *
     * remove(Object):
     *
     * Removes the first occurrence of an equal object.
     *
     * Example:
     *
     * list.remove(Integer.valueOf(30));
     *
     * IMPORTANT:
     *
     * With ArrayList<Integer>:
     *
     * list.remove(1);
     *
     * Calls remove(int index), not remove(Object).
     *
     * To remove the integer value 1:
     *
     * list.remove(Integer.valueOf(1));
     *
     * Complexity:
     *
     * Removing the last element:
     * O(1) in typical conditions.
     *
     * Removing an element from the middle or beginning:
     * O(n) due to shifting.
     *
     *
     * ============================================================
     * 20. ENSURING CAPACITY
     * ============================================================
     *
     * ArrayList provides:
     *
     * ensureCapacity(int minCapacity)
     *
     * It requests enough backing-array capacity for at least
     * the specified number of elements.
     *
     * Example:
     *
     * ArrayList<Integer> list = new ArrayList<>();
     *
     * list.ensureCapacity(1000);
     *
     * Useful when we know in advance that many elements
     * will be added.
     *
     * Benefits:
     *
     * - Can reduce repeated resizing.
     * - Can improve performance in bulk insertion scenarios.
     *
     * IMPORTANT:
     *
     * ensureCapacity() does not change the list's size.
     *
     *
     * ============================================================
     * 21. TRIMTOSIZE()
     * ============================================================
     *
     * ArrayList provides:
     *
     * trimToSize()
     *
     * It requests that the backing array's capacity be reduced
     * to the current list size.
     *
     * Example:
     *
     * ArrayList<Integer> list = new ArrayList<>(100);
     *
     * list.add(10);
     * list.add(20);
     *
     * list.trimToSize();
     *
     * The backing array can be reduced to the number of
     * currently stored elements.
     *
     * Useful when:
     *
     * - A list has finished growing.
     * - Memory usage matters.
     *
     * It may involve allocating and copying an array.
     *
     * It does not change the list's size.
     *
     *
     * ============================================================
     * 22. ARRAYS VS ARRAYLIST
     * ============================================================
     *
     * Array:
     *
     * - Fixed length.
     * - Can store primitives and references.
     * - Supports direct indexed access.
     * - Has no built-in add() or remove() methods.
     *
     * ArrayList:
     *
     * - Dynamically resizable.
     * - Stores object references.
     * - Supports add(), remove(), get(), set().
     * - Uses an internal array.
     *
     * Example:
     *
     * int[] arr = new int[5];
     *
     * ArrayList<Integer> list = new ArrayList<>();
     *
     * Use an array when:
     *
     * - Size is fixed or known.
     * - Primitive storage is important.
     * - You need a simple indexed structure.
     *
     * Use ArrayList when:
     *
     * - Number of elements changes.
     * - You need List operations.
     * - You need convenient resizing.
     *
     *
     * ============================================================
     * 23. ARRAYLIST VS LINKEDLIST - INTERNAL PERSPECTIVE
     * ============================================================
     *
     * ArrayList:
     *
     * - Uses a resizable array.
     * - Fast indexed access.
     * - Insertions/removals in the middle require shifting.
     * - Usually has better cache locality.
     *
     * LinkedList:
     *
     * - Uses a doubly linked list.
     * - Each node stores links to neighboring nodes.
     * - Indexed access requires traversal.
     * - Insertions/removals at known node positions can be O(1).
     * - Has additional node and reference overhead.
     *
     * IMPORTANT:
     *
     * LinkedList.add(index, element) is not generally O(1).
     *
     * It must first locate the indexed position, which takes
     * O(n) traversal in the general case.
     *
     * The actual linking operation after locating the position
     * is O(1).
     *
     *
     * ============================================================
     * 24. TWO-DIMENSIONAL ARRAY MEMORY CONCEPT
     * ============================================================
     *
     * Example:
     *
     * int[][] matrix = new int[3][4];
     *
     * Conceptually:
     *
     * matrix
     *    |
     *    +----> Row 0 array
     *    |
     *    +----> Row 1 array
     *    |
     *    +----> Row 2 array
     *
     * Each row is a separate array object.
     *
     * Therefore, Java supports jagged arrays naturally.
     *
     * Example:
     *
     * int[][] matrix = {
     *     {1, 2},
     *     {3, 4, 5},
     *     {6}
     * };
     *
     * matrix.length -> 3
     * matrix[0].length -> 2
     * matrix[1].length -> 3
     * matrix[2].length -> 1
     *
     *
     * ============================================================
     * 25. ARRAY COPYING
     * ============================================================
     *
     * Java provides multiple ways to copy arrays.
     *
     * A. System.arraycopy()
     *
     * System.arraycopy(
     *     source,
     *     sourcePosition,
     *     destination,
     *     destinationPosition,
     *     length
     * );
     *
     * Example:
     *
     * int[] source = {10, 20, 30};
     * int[] destination = new int[3];
     *
     * System.arraycopy(
     *     source, 0, destination, 0, 3
     * );
     *
     * B. Arrays.copyOf()
     *
     * int[] copy = Arrays.copyOf(source, source.length);
     *
     * C. Arrays.copyOfRange()
     *
     * int[] copy = Arrays.copyOfRange(source, 0, 2);
     *
     * IMPORTANT:
     *
     * These methods copy array elements.
     *
     * For primitive arrays, primitive values are copied.
     *
     * For object arrays, references are copied, not the
     * referenced objects themselves.
     *
     * Therefore, object-array copying is shallow.
     *
     *
     * ============================================================
     * 26. SHALLOW COPY VS DEEP COPY
     * ============================================================
     *
     * Consider:
     *
     * Student[] original = {
     *     new Student("Vinay")
     * };
     *
     * Student[] copy = Arrays.copyOf(
     *     original,
     *     original.length
     * );
     *
     * Both arrays refer to the same Student object.
     *
     * Conceptually:
     *
     * original[0] ----+
     *                 |
     *                 v
     *              Student object
     *                 ^
     *                 |
     * copy[0] --------+
     *
     * Changing the Student object through either array is
     * visible through both references.
     *
     * A deep copy would create separate copies of the
     * referenced objects.
     *
     * Java does not automatically deep-copy objects when
     * copying an array.
     *
     *
     * ============================================================
     * 27. ARRAYLIST TO ARRAY CONVERSION
     * ============================================================
     *
     * Convert a List into an Object array:
     *
     * List<String> names = List.of("Vinay", "Anil");
     *
     * Object[] arr = names.toArray();
     *
     * Convert into a typed array:
     *
     * String[] arr = names.toArray(new String[0]);
     *
     * Modern alternative:
     *
     * String[] arr = names.toArray(String[]::new);
     *
     * The generator overload is available since Java 11.
     *
     * Convert an array into a List:
     *
     * String[] arr = {"A", "B", "C"};
     *
     * List<String> list = Arrays.asList(arr);
     *
     * Remember:
     *
     * Arrays.asList() returns a fixed-size list backed by
     * the original array.
     *
     *
     * ============================================================
     * 28. ARRAYLIST MEMORY AND PERFORMANCE
     * ============================================================
     *
     * ArrayList stores references in an internal array.
     *
     * For example:
     *
     * ArrayList<Integer> numbers = new ArrayList<>();
     *
     * It does not store primitive int values directly.
     *
     * The elements are Integer objects or references to
     * Integer objects.
     *
     * This can involve:
     *
     * - Object allocation.
     * - Reference storage.
     * - Autoboxing and unboxing.
     *
     * Compared with an int[]:
     *
     * int[] stores primitive int values directly as elements.
     *
     * This is one reason primitive arrays can be more
     * memory-efficient for large numeric datasets.
     *
     *
     * ============================================================
     * 29. COMMON ARRAY AND ARRAYLIST MISTAKES
     * ============================================================
     *
     * 1. Assuming arrays can grow automatically.
     *
     * 2. Confusing array.length with String.length().
     *
     * 3. Confusing ArrayList size with capacity.
     *
     * 4. Assuming ArrayList stores primitive values directly.
     *
     * 5. Assuming every ArrayList.add() operation always
     *    takes O(1) time.
     *
     * 6. Assuming LinkedList.add(index, value) is always O(1).
     *
     * 7. Assuming Arrays.copyOf() performs a deep copy.
     *
     * 8. Assuming ArrayList.ensureCapacity() changes size.
     *
     * 9. Assuming trimToSize() changes the number of elements.
     *
     * 10. Forgetting that Arrays.asList() is fixed-size.
     *
     * 11. Accessing an invalid array index.
     *
     * 12. Assuming a two-dimensional Java array must have
     *     equal-length rows.
     *
     *
     * ============================================================
     * 30. IMPORTANT INTERVIEW QUESTIONS
     * ============================================================
     *
     * 1. What is an array?
     *
     * 2. Why does array indexing take O(1) time?
     *
     * 3. Why are arrays fixed in size?
     *
     * 4. What is a dynamic array?
     *
     * 5. How does ArrayList grow internally?
     *
     * 6. Difference between size and capacity?
     *
     * 7. What is amortized time complexity?
     *
     * 8. Why is ArrayList.add() amortized O(1)?
     *
     * 9. What happens internally when ArrayList capacity
     *    becomes insufficient?
     *
     * 10. What is the difference between ensureCapacity()
     *     and trimToSize()?
     *
     * 11. Why is ArrayList.get() O(1)?
     *
     * 12. Why is ArrayList insertion at index 0 O(n)?
     *
     * 13. Difference between primitive arrays and object arrays?
     *
     * 14. What is the difference between shallow copy
     *     and deep copy?
     *
     * 15. Does Arrays.copyOf() perform deep copying?
     *
     * 16. How does Java represent multidimensional arrays?
     *
     * 17. Why can int[] be more memory-efficient than
     *     ArrayList<Integer>?
     *
     * 18. What is the difference between ArrayList and LinkedList?
     *
     * 19. What is the purpose of System.arraycopy()?
     *
     * 20. What is a jagged array?
     *
     *
     * ============================================================
     * 31. QUICK REVISION
     * ============================================================
     *
     * Array:
     *     -> Fixed-length indexed structure.
     *
     * Array indexing:
     *     -> O(1).
     *
     * Linear search:
     *     -> O(n).
     *
     * Dynamic array:
     *     -> Resizable array structure.
     *
     * ArrayList:
     *     -> Dynamic array implementation of List.
     *
     * Size:
     *     -> Number of stored elements.
     *
     * Capacity:
     *     -> Available backing-array storage.
     *
     * ArrayList append:
     *     -> Amortized O(1).
     *
     * ArrayList indexed access:
     *     -> O(1).
     *
     * ArrayList middle insertion:
     *     -> O(n).
     *
     * ArrayList middle deletion:
     *     -> O(n).
     *
     * ensureCapacity():
     *     -> Requests sufficient backing-array capacity.
     *
     * trimToSize():
     *     -> Requests capacity reduction to current size.
     *
     * System.arraycopy():
     *     -> Copies array elements.
     *
     * Shallow copy:
     *     -> Copies references, not referenced objects.
     *
     * Deep copy:
     *     -> Creates independent copies of nested objects.
     *
     * Jagged array:
     *     -> Array whose rows can have different lengths.
     *
     * ============================================================
     * END OF ARRAY-BASED DATA STRUCTURES
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Array Based Data Structures , Happy Learning ..!");
    }
}
