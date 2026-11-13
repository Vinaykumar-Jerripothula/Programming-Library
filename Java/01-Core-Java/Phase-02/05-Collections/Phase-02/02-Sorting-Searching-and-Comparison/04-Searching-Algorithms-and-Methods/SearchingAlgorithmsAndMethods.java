
public class SearchingAlgorithmsAndMethods {

    /*
     * ============================================================
     * SEARCHING ALGORITHMS AND METHODS IN JAVA
     * ============================================================
     *
     * 1. WHAT IS SEARCHING?
     *
     * Searching means finding whether a particular element exists
     * in a data structure and, if it exists, locating its position.
     *
     * Example:
     *
     * int[] numbers = {10, 20, 30, 40, 50};
     *
     * Search for 30.
     *
     * Result: Element found at index 2.
     *
     * Searching is one of the most common operations in programming.
     *
     * Examples:
     * - Finding a student by roll number.
     * - Finding a product by product ID.
     * - Finding a user by email.
     * - Finding a value in an array.
     * - Finding a key in a Map.
     *
     * Two fundamental searching algorithms:
     *
     * 1. Linear Search
     * 2. Binary Search
     *
     *
     * ============================================================
     * 2. LINEAR SEARCH
     * ============================================================
     *
     * Linear Search checks elements one by one until the target
     * is found or the collection is exhausted.
     *
     * Think of searching for a name in an unsorted list:
     *
     * You start from the first name and check each name one by one.
     *
     * Example:
     *
     * int[] arr = {40, 10, 50, 20, 30};
     *
     * Target = 20
     *
     * Step 1: Check 40 -> Not found.
     * Step 2: Check 10 -> Not found.
     * Step 3: Check 50 -> Not found.
     * Step 4: Check 20 -> Found at index 3.
     *
     * Pseudocode:
     *
     * for (int i = 0; i < arr.length; i++) {
     *     if (arr[i] == target) {
     *         return i;
     *     }
     * }
     *
     * return -1;
     *
     * Complexity:
     *
     * Best case:    O(1)       Target is first element.
     * Average case: O(n)       May inspect many elements.
     * Worst case:   O(n)       Target is last or absent.
     * Space:        O(1)       Iterative implementation.
     *
     * Advantages:
     * - Simple to implement.
     * - Works on sorted and unsorted data.
     * - No extra memory required.
     *
     * Disadvantages:
     * - Slow for large datasets.
     * - May inspect every element.
     *
     *
     * ============================================================
     * 3. BINARY SEARCH
     * ============================================================
     *
     * Binary Search repeatedly divides the search space into
     * two halves.
     *
     * IMPORTANT REQUIREMENT:
     *
     * The data must be sorted according to the same ordering
     * used by the search.
     *
     * Think of searching for a word in a dictionary.
     *
     * You don't start reading from the first page.
     *
     * You open somewhere near the middle:
     *
     * - If your word comes before that page, search the left half.
     * - If your word comes after that page, search the right half.
     * - If it matches, you found it.
     *
     * Example:
     *
     * int[] arr = {10, 20, 30, 40, 50, 60, 70};
     *
     * Target = 50
     *
     * low = 0
     * high = 6
     *
     * Step 1:
     * mid = (0 + 6) / 2 = 3
     * arr[3] = 40
     *
     * 50 > 40
     * Search right half.
     *
     * low = 4
     * high = 6
     *
     * Step 2:
     * mid = (4 + 6) / 2 = 5
     * arr[5] = 60
     *
     * 50 < 60
     * Search left half.
     *
     * low = 4
     * high = 4
     *
     * Step 3:
     * mid = 4
     * arr[4] = 50
     *
     * Target found at index 4.
     *
     *
     * ============================================================
     * 4. ITERATIVE BINARY SEARCH
     * ============================================================
     *
     * Binary Search can be implemented using a loop.
     *
     * Example:
     *
     * int binarySearch(int[] arr, int target) {
     *
     *     int low = 0;
     *     int high = arr.length - 1;
     *
     *     while (low <= high) {
     *
     *         int mid = low + (high - low) / 2;
     *
     *         if (arr[mid] == target) {
     *             return mid;
     *         }
     *
     *         if (arr[mid] < target) {
     *             low = mid + 1;
     *         } else {
     *             high = mid - 1;
     *         }
     *     }
     *
     *     return -1;
     * }
     *
     * WHY USE:
     *
     * mid = low + (high - low) / 2
     *
     * Instead of:
     *
     * mid = (low + high) / 2
     *
     * Because low + high can overflow for very large integer
     * indices. The first expression avoids that particular
     * overflow.
     *
     * Complexity:
     *
     * Best case:    O(1)
     * Average case: O(log n)
     * Worst case:   O(log n)
     * Space:        O(1)
     *
     *
     * ============================================================
     * 5. RECURSIVE BINARY SEARCH
     * ============================================================
     *
     * Binary Search can also be implemented using recursion.
     *
     * Example:
     *
     * int binarySearch(
     *     int[] arr,
     *     int low,
     *     int high,
     *     int target
     * ) {
     *
     *     if (low > high) {
     *         return -1;
     *     }
     *
     *     int mid = low + (high - low) / 2;
     *
     *     if (arr[mid] == target) {
     *         return mid;
     *     }
     *
     *     if (arr[mid] < target) {
     *         return binarySearch(
     *             arr, mid + 1, high, target
     *         );
     *     }
     *
     *     return binarySearch(
     *         arr, low, mid - 1, target
     *     );
     * }
     *
     * Complexity:
     *
     * Time:  O(log n)
     * Space: O(log n) due to recursive call stack.
     *
     * Difference:
     *
     * Iterative:
     * - Uses a loop.
     * - O(1) auxiliary space.
     *
     * Recursive:
     * - Uses function calls.
     * - O(log n) auxiliary call-stack space.
     *
     *
     * ============================================================
     * 6. LINEAR SEARCH VS BINARY SEARCH
     * ============================================================
     *
     * Linear Search:
     * - Does not require sorted data.
     * - Checks elements one by one.
     * - Worst-case time: O(n).
     * - Suitable for small or unsorted datasets.
     *
     * Binary Search:
     * - Requires data sorted under the search ordering.
     * - Divides the search space in half.
     * - Worst-case time: O(log n).
     * - Suitable for large sorted datasets with efficient access.
     *
     * IMPORTANT:
     *
     * Binary Search is not always the better choice.
     *
     * If data is unsorted and we need only one search, sorting
     * it first may cost more than performing a linear search.
     *
     * If we perform many searches on relatively unchanged data,
     * sorting once and searching repeatedly may be beneficial.
     *
     *
     * ============================================================
     * 7. ARRAYS.BINARYSEARCH()
     * ============================================================
     *
     * Java provides binary search through the Arrays utility class.
     *
     * Import:
     *
     * import java.util.Arrays;
     *
     * Example:
     *
     * int[] arr = {10, 20, 30, 40, 50};
     *
     * int index = Arrays.binarySearch(arr, 30);
     *
     * Result:
     * 2
     *
     * IMPORTANT:
     *
     * The array must be sorted before searching.
     *
     * Example:
     *
     * int[] arr = {50, 10, 40, 20, 30};
     *
     * Arrays.sort(arr);
     *
     * int index = Arrays.binarySearch(arr, 30);
     *
     * Result:
     * 2
     *
     * Do not use binarySearch() on an unsorted array and expect
     * a correct result.
     *
     *
     * ============================================================
     * 8. ARRAYS.BINARYSEARCH() - NOT FOUND RESULT
     * ============================================================
     *
     * What happens when the element does not exist?
     *
     * Arrays.binarySearch() returns a negative value.
     *
     * More precisely:
     *
     * return -(insertionPoint) - 1;
     *
     * Insertion point means the position where the target should
     * be inserted to maintain sorted order.
     *
     * Example:
     *
     * int[] arr = {10, 20, 30, 40, 50};
     *
     * int result = Arrays.binarySearch(arr, 35);
     *
     * Insertion point = 3
     *
     * Return value:
     *
     * -(3) - 1 = -4
     *
     * Therefore, result = -4.
     *
     * Recover insertion point:
     *
     * int insertionPoint = -result - 1;
     *
     * IMPORTANT:
     *
     * Never assume that every negative result is simply -1.
     *
     * -1, -2, -3, etc. encode different insertion points.
     *
     *
     * ============================================================
     * 9. ARRAYS.BINARYSEARCH() - RANGE SEARCH
     * ============================================================
     *
     * Java also supports searching within a specific range.
     *
     * Syntax:
     *
     * Arrays.binarySearch(
     *     array,
     *     fromIndex,
     *     toIndex,
     *     key
     * );
     *
     * fromIndex is inclusive.
     * toIndex is exclusive.
     *
     * Example:
     *
     * int[] arr = {10, 20, 30, 40, 50, 60};
     *
     * int index = Arrays.binarySearch(
     *     arr, 1, 5, 40
     * );
     *
     * Search range:
     * [20, 30, 40, 50]
     *
     * Result:
     * 3
     *
     * The searched range must be sorted.
     *
     *
     * ============================================================
     * 10. ARRAYS.BINARYSEARCH() WITH OBJECTS
     * ============================================================
     *
     * Binary Search also works with object arrays.
     *
     * Natural ordering:
     *
     * String[] names = {
     *     "Anil", "Rahul", "Vinay"
     * };
     *
     * int index = Arrays.binarySearch(names, "Rahul");
     *
     * Result:
     * 1
     *
     * The objects must be comparable under their natural ordering.
     *
     * Custom Comparator:
     *
     * String[] names = {
     *     "Anil", "Rahul", "Vinay"
     * };
     *
     * int index = Arrays.binarySearch(
     *     names,
     *     "vinay",
     *     String.CASE_INSENSITIVE_ORDER
     * );
     *
     * The array must be sorted using the same comparator.
     *
     * Example:
     *
     * Arrays.sort(names, String.CASE_INSENSITIVE_ORDER);
     *
     * int index = Arrays.binarySearch(
     *     names,
     *     "vinay",
     *     String.CASE_INSENSITIVE_ORDER
     * );
     *
     * IMPORTANT:
     *
     * Sorting with one ordering and searching with another
     * ordering can produce incorrect results.
     *
     *
     * ============================================================
     * 11. COLLECTIONS.BINARYSEARCH()
     * ============================================================
     *
     * Java provides binary search for Lists through Collections.
     *
     * Syntax:
     *
     * Collections.binarySearch(list, key);
     *
     * Example:
     *
     * List<Integer> numbers = List.of(
     *     10, 20, 30, 40, 50
     * );
     *
     * int index = Collections.binarySearch(numbers, 30);
     *
     * Result:
     * 2
     *
     * The List must be sorted according to natural ordering.
     *
     * Custom Comparator:
     *
     * List<String> names = new ArrayList<>(
     *     List.of("Anil", "Rahul", "Vinay")
     * );
     *
     * names.sort(String.CASE_INSENSITIVE_ORDER);
     *
     * int index = Collections.binarySearch(
     *     names,
     *     "vinay",
     *     String.CASE_INSENSITIVE_ORDER
     * );
     *
     * Result: 2
     *
     * The list must use the same ordering as the search comparator.
     *
     *
     * ============================================================
     * 12. COLLECTIONS.BINARYSEARCH() - NOT FOUND
     * ============================================================
     *
     * Like Arrays.binarySearch(), Collections.binarySearch()
     * returns a negative value when the element is not found.
     *
     * Formula:
     *
     * -(insertionPoint) - 1
     *
     * Example:
     *
     * List<Integer> numbers = List.of(
     *     10, 20, 30, 40, 50
     * );
     *
     * int result = Collections.binarySearch(numbers, 35);
     *
     * Insertion point = 3
     *
     * Result = -4
     *
     *
     * ============================================================
     * 13. BINARY SEARCH AND DUPLICATE ELEMENTS
     * ============================================================
     *
     * What happens if the array contains duplicates?
     *
     * Example:
     *
     * int[] arr = {10, 20, 20, 20, 30, 40};
     *
     * int index = Arrays.binarySearch(arr, 20);
     *
     * The method returns an index containing 20.
     *
     * IMPORTANT:
     *
     * It does not guarantee the first or last occurrence
     * when duplicates exist.
     *
     * If we need:
     * - First occurrence
     * - Last occurrence
     * - Count of occurrences
     *
     * We need additional logic, such as modified binary search.
     *
     *
     * ============================================================
     * 14. SEARCHING IN COLLECTIONS USING CONTAINS()
     * ============================================================
     *
     * Collection provides:
     *
     * contains(Object o)
     *
     * It checks whether an element exists.
     *
     * Example:
     *
     * List<Integer> numbers = List.of(10, 20, 30);
     *
     * boolean found = numbers.contains(20);
     *
     * Result:
     * true
     *
     * contains() returns a boolean, not an index.
     *
     * For List implementations such as ArrayList and LinkedList,
     * contains() generally performs a linear search using equals().
     *
     * Time complexity:
     *
     * ArrayList: O(n)
     * LinkedList: O(n)
     *
     * For HashSet:
     * - Expected O(1) under suitable hash distribution.
     *
     * For TreeSet:
     * - O(log n).
     *
     * IMPORTANT:
     *
     * Actual performance depends on the collection implementation.
     *
     *
     * ============================================================
     * 15. LIST.INDEXOF() AND LASTINDEXOF()
     * ============================================================
     *
     * List provides methods to locate elements by equality.
     *
     * indexOf():
     * - Returns the first matching index.
     * - Returns -1 if not found.
     *
     * lastIndexOf():
     * - Returns the last matching index.
     * - Returns -1 if not found.
     *
     * Example:
     *
     * List<Integer> numbers = List.of(
     *     10, 20, 30, 20, 40
     * );
     *
     * numbers.indexOf(20);     // 1
     * numbers.lastIndexOf(20); // 3
     * numbers.indexOf(99);     // -1
     *
     * These methods use equals() to compare elements.
     *
     * They do not require sorted data.
     *
     *
     * ============================================================
     * 16. SEARCHING IN SETS
     * ============================================================
     *
     * Set provides:
     *
     * contains(Object o)
     *
     * Different Set implementations have different performance.
     *
     * HashSet:
     * - Expected O(1) contains().
     *
     * LinkedHashSet:
     * - Expected O(1) contains().
     *
     * TreeSet:
     * - O(log n) contains().
     *
     * ConcurrentSkipListSet:
     * - Expected O(log n) contains().
     *
     * EnumSet:
     * - Generally very efficient, often O(1).
     *
     * IMPORTANT:
     *
     * Set does not provide indexOf() because Set does not
     * represent elements using positional indexes.
     *
     *
     * ============================================================
     * 17. SEARCHING IN MAPS
     * ============================================================
     *
     * Map provides several searching methods.
     *
     * containsKey():
     * - Checks whether a key exists.
     *
     * containsValue():
     * - Checks whether a value exists.
     *
     * get():
     * - Retrieves the value associated with a key.
     *
     * Example:
     *
     * Map<String, Integer> marks = new HashMap<>();
     *
     * marks.put("Vinay", 90);
     * marks.put("Anil", 75);
     *
     * marks.containsKey("Vinay"); // true
     * marks.containsValue(90);   // true
     * marks.get("Vinay");        // 90
     *
     * IMPORTANT:
     *
     * get() returning null does not always mean the key is absent.
     *
     * A Map may allow null values.
     *
     * Use containsKey() when you need to distinguish:
     *
     * 1. Key does not exist.
     * 2. Key exists and maps to null.
     *
     * Typical complexity:
     *
     * HashMap:
     * - get() and containsKey(): expected O(1).
     *
     * TreeMap:
     * - get() and containsKey(): O(log n).
     *
     * ConcurrentHashMap:
     * - get() and containsKey(): expected O(1).
     *
     *
     * ============================================================
     * 18. NAVIGABLESET SEARCH METHODS
     * ============================================================
     *
     * NavigableSet provides methods to find nearby elements.
     *
     * Consider:
     *
     * NavigableSet<Integer> set = new TreeSet<>(
     *     List.of(10, 20, 30, 40, 50)
     * );
     *
     * lower(30):
     * Returns greatest element strictly less than 30.
     * Result: 20
     *
     * floor(30):
     * Returns greatest element less than or equal to 30.
     * Result: 30
     *
     * ceiling(30):
     * Returns smallest element greater than or equal to 30.
     * Result: 30
     *
     * higher(30):
     * Returns smallest element strictly greater than 30.
     * Result: 40
     *
     * first():
     * Returns smallest element.
     *
     * last():
     * Returns largest element.
     *
     * pollFirst():
     * Retrieves and removes the smallest element.
     *
     * pollLast():
     * Retrieves and removes the largest element.
     *
     * These methods are particularly useful when we need
     * nearest values rather than exact matches.
     *
     *
     * ============================================================
     * 19. NAVIGABLEMAP SEARCH METHODS
     * ============================================================
     *
     * NavigableMap provides navigation methods for keys.
     *
     * Example:
     *
     * NavigableMap<Integer, String> map = new TreeMap<>();
     *
     * map.put(10, "A");
     * map.put(20, "B");
     * map.put(30, "C");
     * map.put(40, "D");
     *
     * lowerKey(30):
     * Greatest key strictly less than 30.
     * Result: 20
     *
     * floorKey(30):
     * Greatest key less than or equal to 30.
     * Result: 30
     *
     * ceilingKey(30):
     * Smallest key greater than or equal to 30.
     * Result: 30
     *
     * higherKey(30):
     * Smallest key strictly greater than 30.
     * Result: 40
     *
     * Also available:
     *
     * lowerEntry()
     * floorEntry()
     * ceilingEntry()
     * higherEntry()
     *
     * These return Map.Entry objects instead of only keys.
     *
     *
     * ============================================================
     * 20. BINARY SEARCH ON LINKEDLIST
     * ============================================================
     *
     * LinkedList implements List, so it can be passed to
     * Collections.binarySearch().
     *
     * Example:
     *
     * LinkedList<Integer> numbers = new LinkedList<>(
     *     List.of(10, 20, 30, 40, 50)
     * );
     *
     * int index = Collections.binarySearch(numbers, 30);
     *
     * Logically, the search still uses binary-search comparisons.
     *
     * HOWEVER:
     *
     * LinkedList does not provide efficient random access.
     *
     * Accessing an element by index requires traversal.
     *
     * Therefore, binary search on a LinkedList may perform
     * substantially more navigation work than on an ArrayList.
     *
     * ArrayList:
     * - Efficient indexed access.
     * - Suitable for binary search.
     *
     * LinkedList:
     * - Sequential node traversal.
     * - Binary search is generally not an efficient choice.
     *
     *
     * ============================================================
     * 21. SEARCHING CUSTOM OBJECTS
     * ============================================================
     *
     * Searching custom objects depends on the comparison method.
     *
     * Linear search through a List:
     *
     * class Student {
     *     int id;
     *     String name;
     *
     *     Student(int id, String name) {
     *         this.id = id;
     *         this.name = name;
     *     }
     *
     *     public boolean equals(Object obj) {
     *         if (this == obj) return true;
     *         if (!(obj instanceof Student)) return false;
     *
     *         Student other = (Student) obj;
     *         return this.id == other.id;
     *     }
     *
     *     public int hashCode() {
     *         return Integer.hashCode(id);
     *     }
     * }
     *
     * List<Student> students = new ArrayList<>();
     *
     * students.add(new Student(101, "Vinay"));
     * students.add(new Student(102, "Anil"));
     *
     * students.contains(new Student(101, "Another Name"));
     *
     * Result: true
     *
     * Why?
     *
     * Student.equals() compares IDs.
     *
     * For binary search:
     *
     * The list must be sorted using a compatible ordering.
     *
     * Example:
     *
     * students.sort(Comparator.comparingInt(s -> s.id));
     *
     * int index = Collections.binarySearch(
     *     students,
     *     new Student(101, "Vinay"),
     *     Comparator.comparingInt(s -> s.id)
     * );
     *
     * IMPORTANT:
     *
     * contains() uses equals().
     *
     * Binary search uses natural ordering or the supplied
     * Comparator.
     *
     * These are different comparison mechanisms.
     *
     *
     * ============================================================
     * 22. WHEN SHOULD WE USE EACH SEARCHING METHOD?
     * ============================================================
     *
     * Linear Search:
     * - Unsorted arrays.
     * - Small collections.
     * - One-time searches.
     *
     * Arrays.binarySearch():
     * - Sorted arrays.
     * - Efficient indexed access.
     *
     * Collections.binarySearch():
     * - Sorted Lists.
     * - Prefer Lists with efficient random access.
     *
     * contains():
     * - Checking whether an element exists in a Collection.
     *
     * indexOf():
     * - Finding the first matching position in a List.
     *
     * HashSet.contains():
     * - Fast membership checks using hashing.
     *
     * TreeSet navigation methods:
     * - Finding nearest lower or higher elements.
     *
     * HashMap.get():
     * - Retrieving a value using a key.
     *
     * TreeMap navigation methods:
     * - Finding nearby keys and entries.
     *
     *
     * ============================================================
     * 23. COMMON MISTAKES
     * ============================================================
     *
     * 1. Performing binary search on unsorted data.
     *
     * 2. Sorting with one Comparator and searching with another.
     *
     * 3. Assuming binarySearch() returns -1 whenever an element
     *    is not found.
     *
     * 4. Assuming binarySearch() returns the first occurrence
     *    of a duplicate element.
     *
     * 5. Forgetting that Arrays.binarySearch() returns an index.
     *
     * 6. Forgetting that contains() returns a boolean.
     *
     * 7. Confusing List.indexOf() with binary search.
     *
     * 8. Assuming every Collection has O(1) contains().
     *
     * 9. Using binary search on LinkedList without considering
     *    its inefficient indexed access.
     *
     * 10. Assuming Map.get(key) returning null always means
     *     the key does not exist.
     *
     *
     * ============================================================
     * 24. IMPORTANT INTERVIEW QUESTIONS
     * ============================================================
     *
     * 1. What is the difference between Linear Search and
     *    Binary Search?
     *
     * 2. Why must data be sorted for Binary Search?
     *
     * 3. What is the time complexity of Binary Search?
     *
     * 4. What is the space complexity of recursive Binary Search?
     *
     * 5. What does Arrays.binarySearch() return if the element
     *    is not found?
     *
     * 6. How do you calculate the insertion point?
     *
     * 7. Does binarySearch() return the first occurrence
     *    of a duplicate?
     *
     * 8. Can we use binarySearch() with custom objects?
     *
     * 9. Why is binary search generally inefficient on LinkedList?
     *
     * 10. Difference between contains() and indexOf()?
     *
     * 11. What is the difference between HashSet.contains()
     *     and TreeSet.contains()?
     *
     * 12. What is the purpose of NavigableSet?
     *
     * 13. Difference between lower(), floor(), ceiling()
     *     and higher()?
     *
     * 14. How do we search Map entries by key or value?
     *
     * 15. Why must sorting and searching use compatible
     *     comparison logic?
     *
     *
     * ============================================================
     * 25. QUICK REVISION
     * ============================================================
     *
     * Linear Search:
     *     -> O(n), works on unsorted data.
     *
     * Binary Search:
     *     -> O(log n), requires sorted data.
     *
     * Arrays.binarySearch():
     *     -> Searches arrays.
     *
     * Collections.binarySearch():
     *     -> Searches Lists.
     *
     * Negative binary search result:
     *     -> -(insertionPoint) - 1.
     *
     * contains():
     *     -> Checks element existence.
     *
     * indexOf():
     *     -> Returns first matching List index.
     *
     * lastIndexOf():
     *     -> Returns last matching List index.
     *
     * HashSet.contains():
     *     -> Expected O(1).
     *
     * TreeSet.contains():
     *     -> O(log n).
     *
     * Map.get():
     *     -> Retrieves a value by key.
     *
     * NavigableSet:
     *     -> lower, floor, ceiling, higher.
     *
     * NavigableMap:
     *     -> lowerKey, floorKey, ceilingKey, higherKey.
     *
     * ============================================================
     * END OF SEARCHING ALGORITHMS AND METHODS
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Searching Algorithms and Methods , Happy Learning ..!");
    }
}
