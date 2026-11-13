
public class SortingFundamentals {

    /*
     * ============================================================
     * 1. WHAT IS SORTING?
     * ============================================================
     *
     * Imagine you have these numbers:
     *
     * [50, 10, 40, 20, 30]
     *
     * The elements are not arranged in any particular order.
     *
     * After sorting in ascending order:
     *
     * [10, 20, 30, 40, 50]
     *
     * After sorting in descending order:
     *
     * [50, 40, 30, 20, 10]
     *
     * Sorting means arranging elements according to a particular
     * order or comparison rule.
     *
     * Common sorting orders:
     *
     * 1. Ascending order.
     * 2. Descending order.
     * 3. Alphabetical order.
     * 4. Reverse alphabetical order.
     * 5. Custom-defined order.
     *
     *
     * ============================================================
     * 2. WHY DO WE NEED SORTING?
     * ============================================================
     *
     * Sorting makes data easier to understand, search, and process.
     *
     * Consider an employee list:
     *
     * Employees:
     *
     * Vinay  -> Salary: 30000
     * Rahul  -> Salary: 50000
     * Arjun  -> Salary: 25000
     *
     * We may want to:
     *
     * - Display employees by salary.
     * - Display students by marks.
     * - Arrange products by price.
     * - Display names alphabetically.
     * - Find the highest or lowest values.
     * - Prepare ranked results.
     *
     * Real-world applications:
     *
     * 1. E-commerce: Sort products by price or rating.
     *
     * 2. Banking: Sort transactions by date or amount.
     *
     * 3. Employee management: Sort employees by salary.
     *
     * 4. Student management: Sort students by marks.
     *
     * 5. Search engines: Rank results using relevance criteria.
     *
     * 6. Databases: ORDER BY sorts query results.
     *
     *
     * ============================================================
     * 3. TYPES OF SORTING ORDER
     * ============================================================
     *
     * ------------------------------------------------------------
     * 3.1 ASCENDING ORDER
     * ------------------------------------------------------------
     *
     * Arranges elements from smallest to largest.
     *
     * Numbers:
     *
     * [5, 2, 8, 1, 4]
     *
     * Result:
     *
     * [1, 2, 4, 5, 8]
     *
     * Characters:
     *
     * ['d', 'a', 'c', 'b']
     *
     * Result:
     *
     * ['a', 'b', 'c', 'd']
     *
     * Strings:
     *
     * ["Zebra", "Apple", "Mango"]
     *
     * Result under natural String ordering:
     *
     * ["Apple", "Mango", "Zebra"]
     *
     *
     * ------------------------------------------------------------
     * 3.2 DESCENDING ORDER
     * ------------------------------------------------------------
     *
     * Arranges elements from largest to smallest.
     *
     * Numbers:
     *
     * [5, 2, 8, 1, 4]
     *
     * Result:
     *
     * [8, 5, 4, 2, 1]
     *
     * Strings:
     *
     * ["Apple", "Mango", "Zebra"]
     *
     * Reverse natural ordering:
     *
     * ["Zebra", "Mango", "Apple"]
     *
     *
     * ------------------------------------------------------------
     * 3.3 CUSTOM ORDER
     * ------------------------------------------------------------
     *
     * Sometimes ascending or descending order is not enough.
     *
     * Example:
     *
     * Employees should be sorted by:
     *
     * 1. Salary in descending order.
     * 2. If salaries are equal, sort by name alphabetically.
     *
     * Another example:
     *
     * Sort words according to their length.
     *
     * ["Java", "C", "Python", "Go"]
     *
     * Result:
     *
     * ["C", "Go", "Java", "Python"]
     *
     * Custom ordering is implemented using Comparator.
     *
     *
     * ============================================================
     * 4. WHAT IS NATURAL ORDERING?
     * ============================================================
     *
     * Natural ordering is the default ordering defined by a
     * type itself.
     *
     * Examples:
     *
     * Integer:
     * Smaller number comes before larger number.
     *
     * String:
     * Lexicographical ordering based on String's natural
     * comparison rules.
     *
     * Character:
     * Based on Unicode values.
     *
     * BigDecimal:
     * Numerical ordering based on its comparison rules.
     *
     * Java provides natural ordering through:
     *
     * java.lang.Comparable<T>
     *
     * The Comparable interface defines:
     *
     * int compareTo(T other);
     *
     * We will study Comparable in the next file.
     *
     *
     * ============================================================
     * 5. HOW DOES JAVA COMPARE TWO ELEMENTS?
     * ============================================================
     *
     * Java needs a rule to decide which element comes first.
     *
     * For objects, two major comparison mechanisms are:
     *
     * 1. Comparable
     * 2. Comparator
     *
     * Comparable:
     *
     * Defines the natural ordering inside the class.
     *
     * Comparator:
     *
     * Defines an external or custom ordering.
     *
     * Example:
     *
     * Integer a = 10;
     * Integer b = 20;
     *
     * a.compareTo(b);
     *
     * Result:
     * Negative value because 10 comes before 20.
     *
     * The exact negative or positive value is not guaranteed
     * to be -1 or 1.
     *
     * Comparison contract:
     *
     * Negative result -> First object comes before second.
     *
     * Zero result -> Both objects are equal for ordering.
     *
     * Positive result -> First object comes after second.
     *
     *
     * ============================================================
     * 6. STABLE VS UNSTABLE SORTING
     * ============================================================
     *
     * This is an important sorting concept.
     *
     * STABLE SORTING:
     *
     * A sorting algorithm is stable when elements having equal
     * sorting keys retain their relative original order.
     *
     * Example:
     *
     * Original employee order:
     *
     * Vinay -> Salary 30000
     * Rahul -> Salary 20000
     * Arjun -> Salary 30000
     *
     * Sort by salary ascending.
     *
     * Stable result:
     *
     * Rahul -> 20000
     * Vinay -> 30000
     * Arjun -> 30000
     *
     * Vinay appeared before Arjun originally, and that order
     * is preserved because their salaries are equal.
     *
     *
     * UNSTABLE SORTING:
     *
     * An unstable sorting algorithm does not guarantee that
     * equal-key elements retain their original relative order.
     *
     * Important:
     *
     * Unstable does not mean equal elements must change order.
     *
     * It only means their original relative order is not
     * guaranteed to be preserved.
     *
     *
     * Why does stability matter?
     *
     * Suppose employees are already sorted by name.
     *
     * Now we sort them by salary.
     *
     * A stable sort preserves alphabetical order among
     * employees with equal salaries.
     *
     *
     * ============================================================
     * 7. IN-PLACE VS OUT-OF-PLACE SORTING
     * ============================================================
     *
     * ------------------------------------------------------------
     * 7.1 IN-PLACE SORTING
     * ------------------------------------------------------------
     *
     * An in-place sorting algorithm rearranges elements within
     * the original data structure using little auxiliary memory.
     *
     * Example:
     *
     * Original:
     * [5, 2, 4, 1]
     *
     * After sorting:
     * [1, 2, 4, 5]
     *
     * The original array itself is rearranged.
     *
     * Important:
     * In-place does not necessarily mean absolutely zero
     * additional memory.
     *
     * Some algorithms use a small amount of extra memory,
     * including recursion stack space.
     *
     *
     * ------------------------------------------------------------
     * 7.2 OUT-OF-PLACE SORTING
     * ------------------------------------------------------------
     *
     * An out-of-place sorting algorithm uses additional memory
     * to store elements during sorting.
     *
     * For example, Merge Sort commonly uses auxiliary arrays
     * when implemented for arrays.
     *
     * Original array:
     * [5, 2, 4, 1]
     *
     * Additional storage is used while sorting.
     *
     * The sorted result may be written into another structure
     * or copied back into the original structure.
     *
     *
     * ============================================================
     * 8. TIME COMPLEXITY OF SORTING
     * ============================================================
     *
     * Sorting performance is commonly measured using
     * Big-O notation.
     *
     * Common time complexities:
     *
     * O(n²):
     * Bubble Sort, Selection Sort, Insertion Sort
     * in their worst cases.
     *
     * O(n log n):
     * Merge Sort, Heap Sort, and many efficient comparison
     * sorting implementations.
     *
     * O(n log n) average:
     * Quick Sort.
     *
     * O(n):
     * Counting Sort under suitable assumptions about the
     * input value range.
     *
     * Important:
     *
     * These are algorithm-level complexity examples.
     *
     * Java's actual sorting methods use implementation-specific
     * algorithms and optimizations.
     *
     *
     * ============================================================
     * 9. JAVA'S BUILT-IN SORTING METHODS
     * ============================================================
     *
     * Java provides built-in sorting methods.
     *
     *
     * ------------------------------------------------------------
     * 9.1 Arrays.sort()
     * ------------------------------------------------------------
     *
     * Used to sort arrays.
     *
     * Example:
     *
     * int[] numbers = {5, 2, 8, 1, 4};
     *
     * Arrays.sort(numbers);
     *
     * Result:
     *
     * [1, 2, 4, 5, 8]
     *
     * It sorts the original array.
     *
     *
     * ------------------------------------------------------------
     * 9.2 Collections.sort()
     * ------------------------------------------------------------
     *
     * Used to sort Lists.
     *
     * Example:
     *
     * List<Integer> numbers = new ArrayList<>(
     *     List.of(5, 2, 8, 1, 4)
     * );
     *
     * Collections.sort(numbers);
     *
     * Result:
     *
     * [1, 2, 4, 5, 8]
     *
     *
     * ------------------------------------------------------------
     * 9.3 List.sort()
     * ------------------------------------------------------------
     *
     * A sorting method available directly on List.
     *
     * Example:
     *
     * List<Integer> numbers = new ArrayList<>(
     *     List.of(5, 2, 8, 1, 4)
     * );
     *
     * numbers.sort(null);
     *
     * Passing null selects the elements' natural ordering.
     *
     * List.sort() was introduced in Java 8.
     *
     *
     * ------------------------------------------------------------
     * 9.4 Arrays.parallelSort()
     * ------------------------------------------------------------
     *
     * Provides parallel sorting for supported arrays.
     *
     * Example:
     *
     * int[] numbers = {5, 2, 8, 1, 4};
     *
     * Arrays.parallelSort(numbers);
     *
     * Result:
     *
     * [1, 2, 4, 5, 8]
     *
     * Introduced in Java 8.
     *
     * Parallel sorting may be useful for sufficiently large
     * arrays, but it is not automatically faster for small arrays.
     *
     *
     * ============================================================
     * 10. SORTING ARRAYS VS SORTING COLLECTIONS
     * ============================================================
     *
     * Arrays:
     *
     * - Use Arrays.sort().
     * - Use Arrays.parallelSort() when appropriate.
     * - Works with primitive and object arrays.
     *
     * Collections:
     *
     * - Use Collections.sort() for Lists.
     * - Use List.sort() directly on a List.
     * - Other collection types may have their own ordering
     *   mechanisms.
     *
     * Important:
     *
     * Collections.sort() accepts a List, not every Collection.
     *
     * For example, a Set cannot be passed directly to
     * Collections.sort().
     *
     * If you need to sort Set elements into a list:
     *
     * List<Integer> sorted = new ArrayList<>(mySet);
     * Collections.sort(sorted);
     *
     *
     * ============================================================
     * 11. SORTING OBJECTS
     * ============================================================
     *
     * Sorting primitive values is straightforward.
     *
     * But how do we sort custom objects?
     *
     * Consider:
     *
     * class Employee {
     *     int id;
     *     String name;
     *     double salary;
     * }
     *
     * Suppose we have multiple employees.
     *
     * How should Java decide which employee comes first?
     *
     * We must define a comparison rule.
     *
     * Two approaches:
     *
     * 1. Implement Comparable in Employee.
     * 2. Supply a Comparator when sorting.
     *
     * Example requirements:
     *
     * - Sort by salary.
     * - Sort by name.
     * - Sort by employee ID.
     * - Sort by salary, then name.
     *
     * These are covered in the next two files.
     *
     *
     * ============================================================
     * 12. SORTING AND SEARCHING ARE RELATED
     * ============================================================
     *
     * Sorting can make certain searching operations more efficient.
     *
     * Example:
     *
     * Unsorted:
     * [40, 10, 50, 20, 30]
     *
     * Sorted:
     * [10, 20, 30, 40, 50]
     *
     * Binary Search can search a sorted array or list
     * efficiently.
     *
     * Time complexity:
     *
     * Linear Search -> O(n)
     *
     * Binary Search -> O(log n)
     *
     * Important:
     *
     * Binary Search requires data arranged according to
     * the same ordering used by the search.
     *
     * Java provides binary search methods in:
     *
     * - Arrays
     * - Collections
     *
     *
     * ============================================================
     * 13. COMPARISON CONTRACT AND EQUAL ELEMENTS
     * ============================================================
     *
     * A sorting mechanism relies on a consistent comparison rule.
     *
     * For Comparable:
     *
     * compareTo()
     *
     * For Comparator:
     *
     * compare()
     *
     * A comparison result should follow a consistent ordering.
     *
     * Important rules include:
     *
     * 1. Sign symmetry:
     *    The sign of compare(a, b) should be the opposite of
     *    compare(b, a).
     *
     * 2. Transitivity:
     *    If a comes before b, and b comes before c,
     *    then a must come before c.
     *
     * 3. Consistency of zero comparisons:
     *    If a compares equal to b, their comparisons against
     *    another object should have consistent signs.
     *
     * The full contracts will be studied in Comparable and
     * Comparator.
     *
     *
     * ============================================================
     * 14. COMMON SORTING MISTAKES
     * ============================================================
     *
     * 1. Assuming sorting works directly on every Collection.
     *
     *    Correction:
     *    Collections.sort() accepts List.
     *
     * 2. Assuming sorting always creates a new collection.
     *
     *    Correction:
     *    Arrays.sort() and List.sort() modify the supplied
     *    array or list.
     *
     * 3. Assuming every sorting algorithm is stable.
     *
     *    Correction:
     *    Stability depends on the algorithm.
     *
     * 4. Assuming sorting objects happens automatically.
     *
     *    Correction:
     *    Objects need a valid natural or supplied comparison rule.
     *
     * 5. Assuming parallel sorting is always faster.
     *
     *    Correction:
     *    Parallel overhead can outweigh its benefits.
     *
     * 6. Assuming compareTo() always returns -1, 0, or 1.
     *
     *    Correction:
     *    It can return any negative integer, zero, or
     *    any positive integer.
     *
     * 7. Assuming binary search works correctly on unsorted data.
     *
     *    Correction:
     *    The data must be sorted according to the search order.
     *
     *
     * ============================================================
     * 15. IMPORTANT POINTS TO REMEMBER
     * ============================================================
     *
     * 1. Sorting arranges elements according to an ordering rule.
     *
     * 2. Ascending means smallest to largest.
     *
     * 3. Descending means largest to smallest.
     *
     * 4. Natural ordering is commonly defined by Comparable.
     *
     * 5. Custom ordering is commonly defined by Comparator.
     *
     * 6. Stable sorting preserves relative order of equal-key
     *    elements.
     *
     * 7. In-place sorting uses little auxiliary memory.
     *
     * 8. Out-of-place sorting uses additional storage.
     *
     * 9. Arrays.sort() sorts arrays.
     *
     * 10. Collections.sort() sorts Lists.
     *
     * 11. List.sort() sorts a List directly.
     *
     * 12. Arrays.parallelSort() supports parallel array sorting.
     *
     * 13. Binary Search requires correctly ordered input.
     *
     *
     * ============================================================
     * 16. INTERVIEW QUESTIONS
     * ============================================================
     *
     * 1. What is sorting?
     *
     * 2. Why is sorting required?
     *
     * 3. What is ascending and descending order?
     *
     * 4. What is natural ordering?
     *
     * 5. What is the difference between Comparable and Comparator?
     *
     * 6. What is stable sorting?
     *
     * 7. What is unstable sorting?
     *
     * 8. What is in-place sorting?
     *
     * 9. What is out-of-place sorting?
     *
     * 10. What is the difference between Arrays.sort()
     *     and Collections.sort()?
     *
     * 11. What is List.sort()?
     *
     * 12. What is Arrays.parallelSort()?
     *
     * 13. Can Collections.sort() sort a Set directly?
     *
     * 14. Why is sorting important for Binary Search?
     *
     * 15. What happens if a Comparator violates its contract?
     *
     *
     * ============================================================
     * 17. QUICK REVISION
     * ============================================================
     *
     * Sorting:
     * Arranging elements according to a comparison rule.
     *
     * Ascending:
     * Smallest to largest.
     *
     * Descending:
     * Largest to smallest.
     *
     * Natural ordering:
     * Default ordering defined by a type.
     *
     * Comparable:
     * Defines natural ordering.
     *
     * Comparator:
     * Defines custom ordering.
     *
     * Stable sorting:
     * Preserves relative order of equal-key elements.
     *
     * In-place:
     * Uses little additional memory.
     *
     * Arrays.sort():
     * Sorts arrays.
     *
     * Collections.sort():
     * Sorts Lists.
     *
     * List.sort():
     * Sorts a List directly.
     *
     * Arrays.parallelSort():
     * Supports parallel array sorting.
     *
     * Binary Search:
     * Efficient searching on correctly ordered data.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Sorting Fundamentals , Happy Learning ..!");
    }
}
