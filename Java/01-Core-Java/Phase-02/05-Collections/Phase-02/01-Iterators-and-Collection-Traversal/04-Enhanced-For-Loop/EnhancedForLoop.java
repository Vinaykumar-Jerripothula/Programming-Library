
public class EnhancedForLoop {

    /*
     * ============================================================
     * 1. WHY DO WE NEED THE ENHANCED FOR LOOP?
     * ============================================================
     *
     * Consider a collection:
     *
     * List<String> names = List.of("Vinay", "Rahul", "Arjun");
     *
     * Using an ordinary for loop:
     *
     * for (int i = 0; i < names.size(); i++) {
     *     System.out.println(names.get(i));
     * }
     *
     * Using an Iterator:
     *
     * Iterator<String> it = names.iterator();
     *
     * while (it.hasNext()) {
     *     System.out.println(it.next());
     * }
     *
     * Both approaches work, but they require us to manage
     * the traversal manually.
     *
     * Java introduced the enhanced for loop to make this easier.
     *
     * Enhanced for loop:
     *
     * for (String name : names) {
     *     System.out.println(name);
     * }
     *
     * Much simpler!
     *
     *
     * ============================================================
     * 2. WHAT IS THE ENHANCED FOR LOOP?
     * ============================================================
     *
     * The enhanced for loop is also called:
     *
     * - For-each loop
     * - For-each statement
     *
     * It was introduced in Java 5.
     *
     * It provides a simple way to traverse:
     *
     * 1. Arrays
     * 2. Objects implementing Iterable, including Collections
     *
     * Syntax:
     *
     * for (DataType variable : collectionOrArray) {
     *
     *     // Statements
     *
     * }
     *
     * Explanation:
     *
     * DataType:
     * Type of each element.
     *
     * variable:
     * Receives the current element during each iteration.
     *
     * collectionOrArray:
     * The array or Iterable object being traversed.
     *
     * The loop automatically moves from one element to the next.
     *
     *
     * ============================================================
     * 3. ENHANCED FOR LOOP WITH ARRAYS
     * ============================================================
     *
     * Example:
     *
     * int[] numbers = {10, 20, 30, 40};
     *
     * for (int number : numbers) {
     *     System.out.println(number);
     * }
     *
     * Output:
     *
     * 10
     * 20
     * 30
     * 40
     *
     * The loop visits every array element in order.
     *
     * Important:
     *
     * Arrays do not implement Iterable.
     *
     * Java supports enhanced for loops for arrays separately
     * through the language specification.
     *
     *
     * ============================================================
     * 4. ENHANCED FOR LOOP WITH COLLECTIONS
     * ============================================================
     *
     * Since Collection extends Iterable, collections can be
     * traversed using enhanced for loops.
     *
     *
     * Example 1: ArrayList
     *
     * List<String> names = new ArrayList<>(
     *     List.of("Vinay", "Rahul", "Arjun")
     * );
     *
     * for (String name : names) {
     *     System.out.println(name);
     * }
     *
     *
     * Example 2: HashSet
     *
     * Set<Integer> numbers = new HashSet<>(
     *     List.of(10, 20, 30)
     * );
     *
     * for (Integer number : numbers) {
     *     System.out.println(number);
     * }
     *
     * Remember:
     * HashSet does not guarantee a particular iteration order.
     *
     *
     * Example 3: Queue
     *
     * Queue<String> queue = new LinkedList<>(
     *     List.of("A", "B", "C")
     * );
     *
     * for (String element : queue) {
     *     System.out.println(element);
     * }
     *
     *
     * ============================================================
     * 5. HOW DOES THE ENHANCED FOR LOOP WORK INTERNALLY?
     * ============================================================
     *
     * This is one of the most important concepts.
     *
     * Consider:
     *
     * List<String> names = List.of("A", "B", "C");
     *
     * Enhanced for loop:
     *
     * for (String name : names) {
     *     System.out.println(name);
     * }
     *
     * Conceptually, for an Iterable object, Java translates
     * this into an Iterator-based traversal similar to:
     *
     * for (Iterator<String> it = names.iterator();
     *      it.hasNext();) {
     *
     *     String name = it.next();
     *
     *     System.out.println(name);
     * }
     *
     * Therefore:
     *
     * Enhanced for loop over an Iterable
     *              |
     *              v
     *          iterator()
     *              |
     *              v
     *          hasNext()
     *              |
     *              v
     *           next()
     *              |
     *              v
     *       Execute loop body
     *
     * This is the conceptual translation for Iterable objects.
     *
     * Important:
     *
     * Arrays are handled differently. Their enhanced for loop
     * is translated into index-based traversal rather than
     * using Iterator.
     *
     *
     * ============================================================
     * 6. ENHANCED FOR LOOP WITH MAP
     * ============================================================
     *
     * Map does not implement Iterable directly.
     *
     * Therefore, we cannot write:
     *
     * for (Map.Entry<Integer, String> entry : map) {
     *     // Invalid
     * }
     *
     * Instead, use one of the Map views:
     *
     * 1. keySet()
     * 2. values()
     * 3. entrySet()
     *
     *
     * Example 1: Traversing keys
     *
     * Map<Integer, String> students = new HashMap<>();
     *
     * students.put(1, "Vinay");
     * students.put(2, "Rahul");
     * students.put(3, "Arjun");
     *
     * for (Integer key : students.keySet()) {
     *     System.out.println(key);
     * }
     *
     *
     * Example 2: Traversing values
     *
     * for (String value : students.values()) {
     *     System.out.println(value);
     * }
     *
     *
     * Example 3: Traversing key-value pairs
     *
     * for (Map.Entry<Integer, String> entry :
     *      students.entrySet()) {
     *
     *     System.out.println(
     *         entry.getKey() + " : " + entry.getValue()
     *     );
     * }
     *
     * Prefer entrySet() when both keys and values are required.
     *
     *
     * ============================================================
     * 7. CAN WE MODIFY A COLLECTION USING ENHANCED FOR?
     * ============================================================
     *
     * We can modify the variable holding the current element,
     * but that does not normally replace the element stored
     * inside the collection.
     *
     * Example:
     *
     * List<Integer> numbers = new ArrayList<>(
     *     List.of(10, 20, 30)
     * );
     *
     * for (Integer number : numbers) {
     *     number = number * 2;
     * }
     *
     * System.out.println(numbers);
     *
     * Output:
     * [10, 20, 30]
     *
     * Why?
     *
     * The variable number receives the current element.
     *
     * Assigning a new value to number does not replace the
     * corresponding element in the List.
     *
     * To replace elements, use:
     *
     * 1. ListIterator.set()
     * 2. List.set(index, value)
     * 3. List.replaceAll()
     *
     *
     * ============================================================
     * 8. REMOVING ELEMENTS DURING ENHANCED FOR LOOP
     * ============================================================
     *
     * Consider:
     *
     * List<Integer> numbers = new ArrayList<>(
     *     List.of(10, 20, 30, 40)
     * );
     *
     * for (Integer number : numbers) {
     *
     *     if (number == 20) {
     *         numbers.remove(number);
     *     }
     * }
     *
     * This may throw:
     *
     * ConcurrentModificationException
     *
     * Why?
     *
     * The enhanced for loop uses an Iterator for Iterable objects.
     *
     * Directly modifying an ordinary fail-fast collection
     * during its iteration can invalidate that iterator.
     *
     * Better approaches:
     *
     * Approach 1: Iterator.remove()
     *
     * Iterator<Integer> it = numbers.iterator();
     *
     * while (it.hasNext()) {
     *
     *     Integer number = it.next();
     *
     *     if (number == 20) {
     *         it.remove();
     *     }
     * }
     *
     *
     * Approach 2: removeIf()
     *
     * numbers.removeIf(number -> number == 20);
     *
     * removeIf() was introduced in Java 8.
     *
     * Important:
     *
     * Do not assume that every collection throws
     * ConcurrentModificationException in this situation.
     *
     * The behavior depends on the collection and its iterator.
     * For example, CopyOnWriteArrayList uses snapshot iterators.
     *
     *
     * ============================================================
     * 9. ENHANCED FOR LOOP WITH NESTED COLLECTIONS
     * ============================================================
     *
     * We can use nested enhanced for loops to traverse
     * multidimensional arrays or nested collections.
     *
     *
     * Example 1: Two-dimensional array
     *
     * int[][] matrix = {
     *     {1, 2, 3},
     *     {4, 5, 6},
     *     {7, 8, 9}
     * };
     *
     * for (int[] row : matrix) {
     *
     *     for (int value : row) {
     *         System.out.print(value + " ");
     *     }
     *
     *     System.out.println();
     * }
     *
     *
     * Example 2: Nested Lists
     *
     * List<List<Integer>> matrix = List.of(
     *     List.of(1, 2),
     *     List.of(3, 4)
     * );
     *
     * for (List<Integer> row : matrix) {
     *
     *     for (Integer value : row) {
     *         System.out.println(value);
     *     }
     * }
     *
     *
     * ============================================================
     * 10. LIMITATIONS OF ENHANCED FOR LOOP
     * ============================================================
     *
     * The enhanced for loop is convenient, but it does not
     * provide all the capabilities of an ordinary for loop
     * or an explicit iterator.
     *
     * Limitations:
     *
     * 1. No direct access to the current index.
     *
     * 2. Cannot move backward.
     *
     * 3. Cannot directly skip to a particular position.
     *
     * 4. Cannot safely remove elements through the loop variable.
     *
     * 5. Cannot replace a List element simply by assigning
     *    a new value to the loop variable.
     *
     * 6. Cannot directly traverse a Map without using its views.
     *
     * Use an ordinary for loop when indexes are important.
     *
     * Use Iterator when controlled traversal and removal are needed.
     *
     * Use ListIterator when bidirectional traversal and
     * List modifications are required.
     *
     *
     * ============================================================
     * 11. ENHANCED FOR LOOP VS NORMAL FOR LOOP
     * ============================================================
     *
     * Normal for loop:
     *
     * for (int i = 0; i < numbers.length; i++) {
     *     System.out.println(numbers[i]);
     * }
     *
     * Enhanced for loop:
     *
     * for (int number : numbers) {
     *     System.out.println(number);
     * }
     *
     * Normal for loop:
     *
     * - Gives direct access to the index.
     * - Supports custom index movement.
     * - Can move forward or backward using index changes.
     * - Useful when position matters.
     *
     * Enhanced for loop:
     *
     * - Cleaner syntax for sequential traversal.
     * - No explicit index management.
     * - Suitable for arrays and Iterable objects.
     * - Does not directly expose the index.
     *
     *
     * ============================================================
     * 12. ENHANCED FOR LOOP VS ITERATOR
     * ============================================================
     *
     * Enhanced for loop:
     *
     * for (String name : names) {
     *     System.out.println(name);
     * }
     *
     * Iterator:
     *
     * Iterator<String> it = names.iterator();
     *
     * while (it.hasNext()) {
     *     System.out.println(it.next());
     * }
     *
     * Differences:
     *
     * Enhanced for loop:
     * - Shorter and easier to read.
     * - Iterator management is hidden.
     * - Does not expose iterator.remove().
     *
     * Iterator:
     * - Requires explicit iterator creation.
     * - Gives control over traversal.
     * - Allows removal when supported.
     *
     * Important:
     *
     * For Iterable objects, enhanced for loop uses an Iterator
     * behind the scenes.
     *
     *
     * ============================================================
     * 13. PERFORMANCE CONSIDERATIONS
     * ============================================================
     *
     * Enhanced for loops are not automatically faster than
     * ordinary loops or explicit iterators.
     *
     * Their performance depends on:
     *
     * - The collection implementation.
     * - The iterator implementation.
     * - The operation performed inside the loop.
     * - Whether the collection supports efficient index access.
     *
     * For example:
     *
     * Repeated get(i) calls on a LinkedList can be inefficient.
     *
     * Enhanced for loop or Iterator traversal is generally
     * preferable for sequential traversal of a LinkedList.
     *
     * Choose based on readability and the collection's
     * characteristics, not on assumptions about speed.
     *
     *
     * ============================================================
     * 14. IMPORTANT POINTS TO REMEMBER
     * ============================================================
     *
     * 1. Enhanced for loop was introduced in Java 5.
     *
     * 2. It is also called the for-each loop.
     *
     * 3. It works with arrays.
     *
     * 4. It works with objects implementing Iterable.
     *
     * 5. Collection extends Iterable.
     *
     * 6. For Iterable objects, it uses an Iterator internally.
     *
     * 7. Arrays are traversed differently, without Iterator.
     *
     * 8. Map is not directly Iterable.
     *
     * 9. Use keySet(), values(), or entrySet() to traverse a Map.
     *
     * 10. The loop variable is not a direct reference to a
     *     collection position.
     *
     * 11. Direct collection modification during traversal can
     *     cause ConcurrentModificationException.
     *
     * 12. Use Iterator.remove() or removeIf() for supported
     *     removal operations.
     *
     *
     * ============================================================
     * 15. INTERVIEW QUESTIONS
     * ============================================================
     *
     * 1. What is an enhanced for loop?
     *
     * 2. When was it introduced?
     *
     * 3. What is another name for the enhanced for loop?
     *
     * 4. Can enhanced for loop work with arrays?
     *
     * 5. Can it work with HashSet?
     *
     * 6. Does it use Iterator internally?
     *
     * 7. Does it use Iterator when traversing arrays?
     *
     * 8. Can we access the index directly?
     *
     * 9. Can we remove elements using enhanced for loop?
     *
     * 10. Why can direct collection modification cause
     *     ConcurrentModificationException?
     *
     * 11. How can we traverse a Map using enhanced for loop?
     *
     * 12. What is the difference between enhanced for loop
     *     and Iterator?
     *
     * 13. What happens if the collection is modified during
     *     enhanced for loop traversal?
     *
     *
     * ============================================================
     * 16. QUICK REVISION
     * ============================================================
     *
     * Enhanced for loop = Simple way to traverse arrays
     *                     and Iterable objects.
     *
     * Introduced = Java 5.
     *
     * Syntax = for (Type variable : source)
     *
     * Collections = Uses Iterator internally.
     *
     * Arrays = Uses array traversal, not Iterator.
     *
     * Map = Traverse using keySet(), values(), or entrySet().
     *
     * Removal = Use Iterator.remove() or removeIf().
     *
     * Index = Not directly available.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Enhanced For Loop , Happy Learning ..!");
    }
}
