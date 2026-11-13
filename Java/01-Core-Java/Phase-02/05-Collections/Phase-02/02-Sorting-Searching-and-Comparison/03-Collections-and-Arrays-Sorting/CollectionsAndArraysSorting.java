
public class CollectionsAndArraysSorting {

    /*
     * ============================================================
     * COLLECTIONS AND ARRAYS SORTING IN JAVA
     * ============================================================
     *
     * 1. WHY DO WE NEED SORTING APIs?
     *
     * Imagine we have:
     *
     * int[] numbers = {40, 10, 30, 20};
     *
     * ArrayList<Integer> numbers = new ArrayList<>(
     *     List.of(40, 10, 30, 20)
     * );
     *
     * We want to arrange these elements in ascending order.
     *
     * We could implement sorting algorithms manually, but Java
     * already provides optimized sorting methods.
     *
     * The two main utility classes are:
     *
     * 1. Arrays     -> Works with arrays.
     * 2. Collections -> Works with collections, especially Lists.
     *
     *
     * ============================================================
     * 2. ARRAYS CLASS
     * ============================================================
     *
     * Arrays belongs to java.util.
     *
     * It provides utility methods to work with arrays:
     *
     * - Sorting
     * - Searching
     * - Copying
     * - Comparing
     * - Filling
     * - Converting arrays into other representations
     *
     * Import:
     *
     * import java.util.Arrays;
     *
     *
     * 3. Arrays.sort()
     *
     * Used to sort arrays in ascending or natural order.
     *
     * Primitive array:
     *
     * int[] arr = {40, 10, 30, 20};
     *
     * Arrays.sort(arr);
     *
     * Result:
     * [10, 20, 30, 40]
     *
     * Supported primitive arrays:
     *
     * byte[]
     * short[]
     * int[]
     * long[]
     * float[]
     * double[]
     * char[]
     *
     * Boolean arrays are not supported because Java has no
     * primitive boolean sorting overload in Arrays.
     *
     *
     * 4. SORTING A SPECIFIC RANGE OF AN ARRAY
     *
     * Sometimes we don't want to sort the complete array.
     *
     * Arrays.sort(array, fromIndex, toIndex);
     *
     * IMPORTANT:
     * - fromIndex is inclusive.
     * - toIndex is exclusive.
     *
     * Example:
     *
     * int[] arr = {50, 40, 30, 20, 10};
     *
     * Arrays.sort(arr, 1, 4);
     *
     * Only these elements are sorted:
     * 40, 30, 20
     *
     * Result:
     * [50, 20, 30, 40, 10]
     *
     * Elements outside the specified range remain unchanged.
     *
     * Invalid ranges can cause:
     * - IllegalArgumentException
     * - ArrayIndexOutOfBoundsException
     *
     *
     * ============================================================
     * 5. SORTING OBJECT ARRAYS
     * ============================================================
     *
     * Arrays.sort() also supports object arrays.
     *
     * Example:
     *
     * String[] names = {"Vinay", "Anil", "Rahul"};
     *
     * Arrays.sort(names);
     *
     * Result:
     * [Anil, Rahul, Vinay]
     *
     * WHY DOES THIS WORK?
     *
     * String implements Comparable<String>.
     *
     * Therefore, Java knows the natural ordering of Strings.
     *
     * For custom objects, the class should implement Comparable
     * or we should provide a Comparator.
     *
     *
     * ============================================================
     * 6. SORTING OBJECT ARRAYS USING COMPARATOR
     * ============================================================
     *
     * We can provide our own sorting logic.
     *
     * Example:
     *
     * String[] names = {"Vinay", "Anil", "Rahul"};
     *
     * Arrays.sort(names, Comparator.reverseOrder());
     *
     * Result:
     * [Vinay, Rahul, Anil]
     *
     * Case-insensitive sorting:
     *
     * Arrays.sort(names, String.CASE_INSENSITIVE_ORDER);
     *
     * Custom object example:
     *
     * class Employee {
     *     String name;
     *     int salary;
     *
     *     Employee(String name, int salary) {
     *         this.name = name;
     *         this.salary = salary;
     *     }
     * }
     *
     * Employee[] employees = {
     *     new Employee("A", 50000),
     *     new Employee("B", 30000),
     *     new Employee("C", 40000)
     * };
     *
     * Arrays.sort(
     *     employees,
     *     Comparator.comparingInt(e -> e.salary)
     * );
     *
     * Employees are sorted by salary in ascending order.
     *
     * Descending order:
     *
     * Arrays.sort(
     *     employees,
     *     Comparator.comparingInt((Employee e) -> e.salary)
     *               .reversed()
     * );
     *
     *
     * ============================================================
     * 7. COLLECTIONS CLASS
     * ============================================================
     *
     * Collections is a utility class in java.util.
     *
     * It provides static methods to operate on collections.
     *
     * Common methods:
     *
     * - sort()
     * - reverse()
     * - shuffle()
     * - binarySearch()
     * - min()
     * - max()
     * - frequency()
     * - copy()
     * - fill()
     * - rotate()
     * - swap()
     *
     * IMPORTANT DIFFERENCE:
     *
     * Collection -> Interface.
     * Collections -> Utility class.
     *
     * Collection represents a group of objects.
     *
     * Collections provides useful operations on collections.
     *
     *
     * ============================================================
     * 8. Collections.sort()
     * ============================================================
     *
     * Used to sort a List.
     *
     * Example:
     *
     * List<Integer> numbers = new ArrayList<>(
     *     List.of(40, 10, 30, 20)
     * );
     *
     * Collections.sort(numbers);
     *
     * Result:
     * [10, 20, 30, 40]
     *
     * Internally, this uses the natural ordering of elements.
     *
     * Therefore, elements must be mutually comparable.
     *
     * The method signature conceptually is:
     *
     * public static <T extends Comparable<? super T>>
     * void sort(List<T> list)
     *
     *
     * ============================================================
     * 9. Collections.sort() WITH COMPARATOR
     * ============================================================
     *
     * We can provide custom ordering.
     *
     * Example:
     *
     * List<Integer> numbers = new ArrayList<>(
     *     List.of(40, 10, 30, 20)
     * );
     *
     * Collections.sort(numbers, Comparator.reverseOrder());
     *
     * Result:
     * [40, 30, 20, 10]
     *
     * Sorting Strings by length:
     *
     * List<String> names = new ArrayList<>(
     *     List.of("Vinay", "Ram", "Alexander", "Joe")
     * );
     *
     * Collections.sort(
     *     names,
     *     Comparator.comparingInt(String::length)
     * );
     *
     * Result:
     * [Ram, Joe, Vinay, Alexander]
     *
     * Note:
     * Strings with equal lengths compare as equal under this
     * comparator. Their original relative order is preserved
     * because the sorting operation is stable.
     *
     *
     * ============================================================
     * 10. List.sort()
     * ============================================================
     *
     * Java 8 introduced the default sort() method in List.
     *
     * Syntax:
     *
     * list.sort(comparator);
     *
     * Example:
     *
     * List<Integer> numbers = new ArrayList<>(
     *     List.of(40, 10, 30, 20)
     * );
     *
     * numbers.sort(null);
     *
     * Result:
     * [10, 20, 30, 40]
     *
     * IMPORTANT:
     *
     * Passing null means natural ordering.
     *
     * Equivalent:
     *
     * Collections.sort(numbers);
     *
     * Custom ordering:
     *
     * numbers.sort(Comparator.reverseOrder());
     *
     * List.sort() modifies the existing list.
     *
     * It does not return a new sorted list.
     *
     *
     * ============================================================
     * 11. Arrays.sort() vs Collections.sort() vs List.sort()
     * ============================================================
     *
     * Arrays.sort():
     * - Works with arrays.
     * - Can sort primitive arrays and object arrays.
     * - Supports custom Comparator for object arrays.
     *
     * Collections.sort():
     * - Works with Lists.
     * - Supports natural ordering and Comparator.
     * - Delegates to the list's sort operation.
     *
     * List.sort():
     * - Defined directly in the List interface.
     * - Available since Java 8.
     * - Sorts the list itself.
     * - Accepts Comparator or null for natural ordering.
     *
     * Remember:
     *
     * Arrays.sort(array);
     * Collections.sort(list);
     * list.sort(comparator);
     *
     *
     * ============================================================
     * 12. ARRAYS.PARALLELSORT()
     * ============================================================
     *
     * Introduced in Java 8.
     *
     * Arrays.parallelSort() can sort arrays using parallel
     * processing.
     *
     * Example:
     *
     * int[] numbers = {50, 10, 40, 20, 30};
     *
     * Arrays.parallelSort(numbers);
     *
     * Result:
     * [10, 20, 30, 40, 50]
     *
     * Difference:
     *
     * Arrays.sort():
     * - Performs sorting using its normal sorting strategy.
     *
     * Arrays.parallelSort():
     * - Can divide sorting work into smaller tasks.
     * - Uses parallel execution where appropriate.
     *
     * IMPORTANT:
     *
     * Parallel sorting is not automatically faster.
     *
     * It may be beneficial for sufficiently large arrays.
     *
     * For small arrays, parallel processing overhead may make
     * it slower.
     *
     * Object-array parallel sorting also preserves stability.
     *
     *
     * ============================================================
     * 13. STABILITY OF JAVA SORTING METHODS
     * ============================================================
     *
     * A stable sorting algorithm preserves the relative order
     * of elements that compare as equal.
     *
     * Example:
     *
     * Employee A -> salary 30000
     * Employee B -> salary 20000
     * Employee C -> salary 30000
     *
     * If we sort by salary:
     *
     * Employee B
     * Employee A
     * Employee C
     *
     * A and C have equal salary.
     *
     * Their original relative order remains unchanged.
     *
     * Java guarantees stable sorting for:
     *
     * - Arrays.sort(Object[])
     * - Arrays.sort(Object[], Comparator)
     * - Arrays.parallelSort(Object[])
     * - Arrays.parallelSort(Object[], Comparator)
     * - Collections.sort()
     * - List.sort()
     *
     * Primitive array sorting does not have an observable
     * object-identity stability guarantee.
     *
     *
     * ============================================================
     * 14. OTHER USEFUL ARRAYS METHODS
     * ============================================================
     *
     * Arrays.toString():
     *
     * int[] arr = {10, 20, 30};
     * System.out.println(Arrays.toString(arr));
     *
     * Output:
     * [10, 20, 30]
     *
     * Arrays.deepToString():
     *
     * int[][] matrix = {
     *     {1, 2},
     *     {3, 4}
     * };
     *
     * System.out.println(Arrays.deepToString(matrix));
     *
     * Output:
     * [[1, 2], [3, 4]]
     *
     * Arrays.copyOf():
     *
     * int[] original = {10, 20, 30};
     * int[] copy = Arrays.copyOf(original, 5);
     *
     * Result:
     * [10, 20, 30, 0, 0]
     *
     * Arrays.copyOfRange():
     *
     * int[] arr = {10, 20, 30, 40, 50};
     * int[] copy = Arrays.copyOfRange(arr, 1, 4);
     *
     * Result:
     * [20, 30, 40]
     *
     * The ending index is exclusive.
     *
     * Arrays.fill():
     *
     * int[] arr = new int[5];
     * Arrays.fill(arr, 10);
     *
     * Result:
     * [10, 10, 10, 10, 10]
     *
     * Arrays.equals():
     *
     * int[] a = {1, 2, 3};
     * int[] b = {1, 2, 3};
     *
     * Arrays.equals(a, b); // true
     *
     * Arrays.deepEquals() is used for nested arrays.
     *
     *
     * ============================================================
     * 15. OTHER USEFUL COLLECTIONS METHODS
     * ============================================================
     *
     * Collections.reverse():
     *
     * Collections.reverse(list);
     *
     * Reverses the order of elements in the existing List.
     *
     * Collections.shuffle():
     *
     * Collections.shuffle(list);
     *
     * Randomly rearranges the list.
     *
     * Collections.min():
     *
     * Collections.min(list);
     *
     * Returns the smallest element according to natural order.
     *
     * Collections.max():
     *
     * Collections.max(list);
     *
     * Returns the largest element according to natural order.
     *
     * Collections.frequency():
     *
     * Collections.frequency(list, 10);
     *
     * Returns the number of occurrences of the specified
     * element.
     *
     * Collections.swap():
     *
     * Collections.swap(list, 0, 2);
     *
     * Swaps elements at the specified positions.
     *
     * Collections.rotate():
     *
     * Collections.rotate(list, 2);
     *
     * Rotates the elements by the specified distance.
     *
     * Collections.binarySearch():
     *
     * Searches for an element in a sorted List.
     *
     * Collections.copy(destination, source):
     *
     * Copies elements from source into destination.
     *
     * IMPORTANT:
     *
     * destination must already have enough elements.
     * It does not increase the destination list's size.
     *
     * Collections.fill(list, value):
     *
     * Replaces every element in the list with the given value.
     *
     *
     * ============================================================
     * 16. SORTING IMMUTABLE LISTS
     * ============================================================
     *
     * Example:
     *
     * List<Integer> numbers = List.of(30, 10, 20);
     *
     * numbers.sort(null);
     *
     * This throws UnsupportedOperationException because
     * List.of() returns an unmodifiable list.
     *
     * Solution:
     *
     * List<Integer> numbers = new ArrayList<>(
     *     List.of(30, 10, 20)
     * );
     *
     * numbers.sort(null);
     *
     * IMPORTANT:
     *
     * Unmodifiable does not necessarily mean deeply immutable.
     * If a list contains mutable objects, those objects may
     * still be modified.
     *
     *
     * ============================================================
     * 17. ARRAYS.ASList() AND ITS SORTING BEHAVIOR
     * ============================================================
     *
     * Arrays.asList() creates a fixed-size List backed by
     * the original array.
     *
     * Example:
     *
     * String[] arr = {"C", "A", "B"};
     *
     * List<String> list = Arrays.asList(arr);
     *
     * list.sort(null);
     *
     * Sorting is allowed and also changes the original array.
     *
     * But:
     *
     * list.add("D");    // UnsupportedOperationException
     * list.remove(0);   // UnsupportedOperationException
     *
     * Why?
     *
     * The list has a fixed size because it is backed by the
     * original array.
     *
     *
     * ============================================================
     * 18. SORTING A LINKEDLIST
     * ============================================================
     *
     * LinkedList implements List.
     *
     * Therefore, it supports:
     *
     * Collections.sort(linkedList);
     *
     * linkedList.sort(comparator);
     *
     * The sorting methods work on the List interface.
     *
     * However, random access in LinkedList is slower than
     * ArrayList because LinkedList uses linked nodes.
     *
     * Do not assume that all List implementations have the
     * same performance characteristics.
     *
     *
     * ============================================================
     * 19. SORTING MAPS
     * ============================================================
     *
     * A Map is not a List and does not directly support
     * Collections.sort(map).
     *
     * To sort Map entries, we can sort its entrySet().
     *
     * Example:
     *
     * Map<String, Integer> marks = new HashMap<>();
     *
     * marks.put("Vinay", 90);
     * marks.put("Anil", 75);
     * marks.put("Rahul", 85);
     *
     * List<Map.Entry<String, Integer>> entries =
     *     new ArrayList<>(marks.entrySet());
     *
     * Sort by value:
     *
     * entries.sort(
     *     Map.Entry.comparingByValue()
     * );
     *
     * Sort by key:
     *
     * entries.sort(
     *     Map.Entry.comparingByKey()
     * );
     *
     * Sort by value descending:
     *
     * entries.sort(
     *     Map.Entry.<String, Integer>comparingByValue()
     *              .reversed()
     * );
     *
     * IMPORTANT:
     *
     * This sorts the separate List of entries.
     * It does not change the original HashMap's iteration order.
     *
     * If we need a sorted Map by key, consider TreeMap.
     *
     *
     * ============================================================
     * 20. SORTING WITH STREAMS
     * ============================================================
     *
     * Java 8 introduced the Stream API.
     *
     * Stream.sorted() returns a stream with elements in sorted
     * order.
     *
     * Example:
     *
     * List<Integer> numbers = List.of(40, 10, 30, 20);
     *
     * List<Integer> sorted = numbers.stream()
     *     .sorted()
     *     .toList();
     *
     * Result:
     * [10, 20, 30, 40]
     *
     * Descending:
     *
     * List<Integer> sorted = numbers.stream()
     *     .sorted(Comparator.reverseOrder())
     *     .toList();
     *
     * Custom sorting:
     *
     * List<String> names = List.of(
     *     "Vinay", "Ram", "Alexander"
     * );
     *
     * List<String> sorted = names.stream()
     *     .sorted(Comparator.comparingInt(String::length))
     *     .toList();
     *
     * IMPORTANT:
     *
     * Stream.sorted() does not modify the source collection.
     *
     * Stream.toList() was introduced in Java 16.
     * It returns an unmodifiable List.
     *
     * For Java 8 or Java 11:
     *
     * List<Integer> sorted = numbers.stream()
     *     .sorted()
     *     .collect(Collectors.toList());
     *
     * Collectors.toList() does not guarantee a particular
     * mutability or implementation type.
     *
     *
     * ============================================================
     * 21. SORTING PERFORMANCE OVERVIEW
     * ============================================================
     *
     * Arrays.sort():
     * - Primitive arrays: optimized primitive sorting algorithm.
     * - Object arrays: stable sorting algorithm.
     *
     * Collections.sort():
     * - Uses List.sort().
     * - Object sorting is stable.
     *
     * Arrays.parallelSort():
     * - Uses parallel sorting strategies where appropriate.
     * - Can improve performance for large arrays.
     *
     * Stream.sorted():
     * - Creates a sorted stream.
     * - Usually requires buffering elements before emitting
     *   the sorted result.
     *
     * Performance depends on:
     *
     * - Number of elements.
     * - Data distribution.
     * - Comparator complexity.
     * - Memory availability.
     * - Array or collection implementation.
     * - Parallel processing overhead.
     *
     *
     * ============================================================
     * 22. COMMON MISTAKES
     * ============================================================
     *
     * 1. Using Collections.sort() on an array.
     *    Use Arrays.sort().
     *
     * 2. Using Arrays.sort() directly on a List.
     *    Use List.sort() or Collections.sort().
     *
     * 3. Sorting an unmodifiable List.
     *    It can throw UnsupportedOperationException.
     *
     * 4. Assuming Arrays.asList() is fully resizable.
     *    It is fixed-size.
     *
     * 5. Assuming sorting a Map directly is possible.
     *    Sort its entries or use a sorted Map implementation.
     *
     * 6. Using binary search on an unsorted collection.
     *    Binary search requires sorted data under the same
     *    ordering.
     *
     * 7. Assuming parallelSort() is always faster.
     *    Parallel overhead matters.
     *
     * 8. Assuming sorting creates a new array or list.
     *    Arrays.sort() and List.sort() modify their input.
     *
     * 9. Forgetting the difference between:
     *    Collection and Collections.
     *
     * 10. Using a Comparator that violates its contract.
     *     This can produce incorrect results or exceptions.
     *
     *
     * ============================================================
     * 23. IMPORTANT INTERVIEW QUESTIONS
     * ============================================================
     *
     * 1. Difference between Arrays.sort() and Collections.sort()?
     *
     * 2. Difference between Collections.sort() and List.sort()?
     *
     * 3. Can we sort primitive arrays using a Comparator?
     *
     * 4. Can we sort an array of custom objects?
     *
     * 5. What happens when we sort an unmodifiable List?
     *
     * 6. What is the difference between Arrays.asList()
     *    and List.of()?
     *
     * 7. Is Java's object sorting stable?
     *
     * 8. What is the purpose of Arrays.parallelSort()?
     *
     * 9. Does Collections.sort() create a new List?
     *
     * 10. How can we sort Map entries by value?
     *
     * 11. Can we sort a LinkedList?
     *
     * 12. What is the difference between sorting a collection
     *     and using Stream.sorted()?
     *
     * 13. What happens if two objects compare as equal?
     *
     * 14. Can we sort a List containing null values?
     *
     * 15. Why must the destination List have enough elements
     *     when using Collections.copy()?
     *
     *
     * ============================================================
     * 24. QUICK REVISION
     * ============================================================
     *
     * Arrays.sort(array)
     *     -> Sorts arrays.
     *
     * Arrays.parallelSort(array)
     *     -> Parallel sorting of arrays where appropriate.
     *
     * Collections.sort(list)
     *     -> Sorts Lists.
     *
     * list.sort(comparator)
     *     -> Sorts a List using the supplied ordering.
     *
     * Arrays.asList(array)
     *     -> Fixed-size list backed by an array.
     *
     * List.of(...)
     *     -> Unmodifiable list.
     *
     * Comparator.reverseOrder()
     *     -> Reverse natural ordering.
     *
     * Comparator.comparing(...)
     *     -> Sort using an extracted key.
     *
     * Stream.sorted()
     *     -> Produces a sorted stream without modifying source.
     *
     * TreeMap
     *     -> Maintains keys in sorted order.
     *
     * TreeSet
     *     -> Maintains elements in sorted order.
     *
     * ============================================================
     * END OF COLLECTIONS AND ARRAYS SORTING
     * ============================================================
     */

    public static void main(String[] args) {
                   System.out.println("The above is all about Collections and Arrays Sorting , Happy Learning ..!");
    }
}
