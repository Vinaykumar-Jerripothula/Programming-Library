
public class ListIterator {

    /*
     * ============================================================
     * 1. WHY DO WE NEED LISTITERATOR?
     * ============================================================
     *
     * Consider a list:
     *
     * [10, 20, 30, 40, 50]
     *
     * Using an ordinary Iterator, we can move forward:
     *
     * 10 -> 20 -> 30 -> 40 -> 50
     *
     * But what if we want to:
     *
     * 1. Move backward?
     * 2. Retrieve the previous element?
     * 3. Replace an element while traversing?
     * 4. Insert an element at the current traversal position?
     *
     * An ordinary Iterator does not provide these operations.
     *
     * Java provides ListIterator specifically for Lists.
     *
     *
     * ============================================================
     * 2. WHAT IS LISTITERATOR?
     * ============================================================
     *
     * ListIterator is an interface in java.util.
     *
     * Declaration:
     *
     * public interface ListIterator<E> extends Iterator<E>
     *
     * Important:
     *
     * ListIterator extends Iterator.
     *
     * Therefore, it inherits:
     *
     * - hasNext()
     * - next()
     * - remove()
     * - forEachRemaining()
     *
     * It additionally provides:
     *
     * - hasPrevious()
     * - previous()
     * - nextIndex()
     * - previousIndex()
     * - set()
     * - add()
     *
     * ListIterator is designed specifically for List collections.
     *
     *
     * ============================================================
     * 3. HOW TO CREATE A LISTITERATOR?
     * ============================================================
     *
     * Syntax:
     *
     * ListIterator<Type> iteratorName = list.listIterator();
     *
     * Example:
     *
     * List<Integer> numbers = new ArrayList<>(
     *     List.of(10, 20, 30, 40, 50)
     * );
     *
     * ListIterator<Integer> it = numbers.listIterator();
     *
     * Important:
     *
     * The listIterator() method is available in the List interface.
     *
     * Unlike iterator(), which is available through Iterable,
     * listIterator() is specifically available through List.
     *
     *
     * ============================================================
     * 4. IMPORTANT METHODS OF LISTITERATOR
     * ============================================================
     *
     * ListIterator provides seven additional methods.
     *
     *
     * ------------------------------------------------------------
     * 4.1 hasPrevious()
     * ------------------------------------------------------------
     *
     * Declaration:
     *
     * boolean hasPrevious();
     *
     * Purpose:
     * Checks whether an element exists before the current cursor.
     *
     * Returns:
     * true  -> An element exists before the cursor.
     * false -> No previous element exists.
     *
     *
     * ------------------------------------------------------------
     * 4.2 previous()
     * ------------------------------------------------------------
     *
     * Declaration:
     *
     * E previous();
     *
     * Purpose:
     * Returns the previous element and moves the cursor backward.
     *
     * If no previous element exists, it throws:
     *
     * NoSuchElementException
     *
     * Example:
     *
     * List<Integer> numbers = new ArrayList<>(
     *     List.of(10, 20, 30)
     * );
     *
     * ListIterator<Integer> it = numbers.listIterator();
     *
     * // Move to the end first.
     * while (it.hasNext()) {
     *     it.next();
     * }
     *
     * // Now traverse backward.
     * while (it.hasPrevious()) {
     *     System.out.println(it.previous());
     * }
     *
     * Output:
     * 30
     * 20
     * 10
     *
     *
     * ------------------------------------------------------------
     * 4.3 nextIndex()
     * ------------------------------------------------------------
     *
     * Declaration:
     *
     * int nextIndex();
     *
     * Purpose:
     * Returns the index of the element that would be returned
     * by the next() call.
     *
     * Example:
     *
     * List<String> names = new ArrayList<>(
     *     List.of("A", "B", "C")
     * );
     *
     * ListIterator<String> it = names.listIterator();
     *
     * System.out.println(it.nextIndex());
     *
     * Output:
     * 0
     *
     * After calling next():
     *
     * System.out.println(it.nextIndex());
     *
     * Output:
     * 1
     *
     * If the cursor is at the end of the list, nextIndex()
     * returns the list size.
     *
     *
     * ------------------------------------------------------------
     * 4.4 previousIndex()
     * ------------------------------------------------------------
     *
     * Declaration:
     *
     * int previousIndex();
     *
     * Purpose:
     * Returns the index of the element that would be returned
     * by the previous() call.
     *
     * At the beginning of the list, it returns -1.
     *
     * Example:
     *
     * List<String> names = new ArrayList<>(
     *     List.of("A", "B", "C")
     * );
     *
     * ListIterator<String> it = names.listIterator();
     *
     * System.out.println(it.previousIndex());
     *
     * Output:
     * -1
     *
     *
     * ------------------------------------------------------------
     * 4.5 set()
     * ------------------------------------------------------------
     *
     * Declaration:
     *
     * void set(E e);
     *
     * Purpose:
     * Replaces the last element returned by next() or previous()
     * with the specified element.
     *
     * Example:
     *
     * List<String> names = new ArrayList<>(
     *     List.of("Vinay", "Rahul", "Arjun")
     * );
     *
     * ListIterator<String> it = names.listIterator();
     *
     * while (it.hasNext()) {
     *
     *     String name = it.next();
     *
     *     if (name.equals("Rahul")) {
     *         it.set("Kiran");
     *     }
     * }
     *
     * System.out.println(names);
     *
     * Output:
     * [Vinay, Kiran, Arjun]
     *
     * Important rules:
     *
     * 1. set() can be called after next() or previous().
     *
     * 2. It cannot be called before retrieving an element.
     *
     * 3. It cannot be called immediately after add() or remove()
     *    without another next() or previous() call.
     *
     * 4. Calling set() in an invalid state throws
     *    IllegalStateException.
     *
     * 5. Some ListIterator implementations do not support set().
     *    They may throw UnsupportedOperationException.
     *
     *
     * ------------------------------------------------------------
     * 4.6 add()
     * ------------------------------------------------------------
     *
     * Declaration:
     *
     * void add(E e);
     *
     * Purpose:
     * Inserts an element immediately before the element that
     * would be returned by next(), if any.
     *
     * In other words, it inserts the element at the current
     * cursor position.
     *
     * Example:
     *
     * List<String> names = new ArrayList<>(
     *     List.of("A", "B", "C")
     * );
     *
     * ListIterator<String> it = names.listIterator();
     *
     * it.next();       // Returns A.
     * it.add("X");     // Inserts X after A.
     *
     * System.out.println(names);
     *
     * Output:
     * [A, X, B, C]
     *
     * Important rules:
     *
     * 1. The inserted element appears before the next element
     *    that would be returned by next().
     *
     * 2. The newly inserted element is not returned by the
     *    next() call immediately following add().
     *
     * 3. The cursor moves past the inserted element.
     *
     * 4. add() resets the state required for set() and remove().
     *
     * 5. Some ListIterator implementations do not support add().
     *
     *
     * ------------------------------------------------------------
     * 4.7 remove()
     * ------------------------------------------------------------
     *
     * Declaration:
     *
     * void remove();
     *
     * Purpose:
     * Removes the last element returned by next() or previous().
     *
     * It is inherited from Iterator.
     *
     * Important:
     *
     * It can be called only once after a successful next() or
     * previous() call, unless another next() or previous()
     * establishes a new removable element.
     *
     * Calling remove() in an invalid state throws
     * IllegalStateException.
     *
     *
     * ============================================================
     * 5. UNDERSTANDING THE LISTITERATOR CURSOR
     * ============================================================
     *
     * This is the most important concept to understand.
     *
     * Consider:
     *
     * [A, B, C]
     *
     * A ListIterator cursor is positioned BETWEEN elements.
     *
     * Initial position:
     *
     * | A | B | C |
     * ^
     *
     * After next():
     *
     * | A | B | C |
     *     ^
     *
     * The returned element is A.
     *
     * After another next():
     *
     * | A | B | C |
     *         ^
     *
     * The returned element is B.
     *
     * After previous():
     *
     * | A | B | C |
     *     ^
     *
     * The returned element is A.
     *
     * Remember:
     *
     * next() moves the cursor forward.
     * previous() moves the cursor backward.
     *
     * The cursor is not positioned directly on an element.
     * It is positioned between two elements.
     *
     *
     * ============================================================
     * 6. STARTING ITERATION FROM A PARTICULAR INDEX
     * ============================================================
     *
     * List provides two overloaded listIterator() methods:
     *
     * 1. listIterator()
     * 2. listIterator(int index)
     *
     * The second method creates a ListIterator starting at
     * the specified position.
     *
     * Example:
     *
     * List<String> names = new ArrayList<>(
     *     List.of("A", "B", "C", "D", "E")
     * );
     *
     * ListIterator<String> it = names.listIterator(2);
     *
     * System.out.println(it.next());
     *
     * Output:
     * C
     *
     * Why?
     *
     * The cursor starts at index 2, immediately before C.
     *
     * Valid starting positions range from:
     *
     * 0 to list.size(), inclusive.
     *
     * An invalid index causes IndexOutOfBoundsException.
     *
     *
     * ============================================================
     * 7. COMPLETE EXAMPLE: FORWARD AND BACKWARD TRAVERSAL
     * ============================================================
     *
     * List<Integer> numbers = new ArrayList<>(
     *     List.of(10, 20, 30, 40)
     * );
     *
     * ListIterator<Integer> it = numbers.listIterator();
     *
     * System.out.println("Forward:");
     *
     * while (it.hasNext()) {
     *     System.out.println(it.next());
     * }
     *
     * System.out.println("Backward:");
     *
     * while (it.hasPrevious()) {
     *     System.out.println(it.previous());
     * }
     *
     * Output:
     *
     * Forward:
     * 10
     * 20
     * 30
     * 40
     *
     * Backward:
     * 40
     * 30
     * 20
     * 10
     *
     *
     * ============================================================
     * 8. COMPLETE EXAMPLE: MODIFYING A LIST
     * ============================================================
     *
     * List<Integer> numbers = new ArrayList<>(
     *     List.of(10, 20, 30, 40)
     * );
     *
     * ListIterator<Integer> it = numbers.listIterator();
     *
     * while (it.hasNext()) {
     *
     *     int number = it.next();
     *
     *     if (number == 20) {
     *         it.set(200);
     *     }
     *
     *     if (number == 30) {
     *         it.add(35);
     *     }
     * }
     *
     * System.out.println(numbers);
     *
     * Output:
     * [10, 200, 30, 35, 40]
     *
     * Explanation:
     *
     * - 20 was replaced with 200.
     * - 35 was inserted immediately after 30.
     *
     *
     * ============================================================
     * 9. ITERATOR VS LISTITERATOR
     * ============================================================
     *
     * Iterator:
     *
     * - Available through Iterable.
     * - Works with collections.
     * - Supports forward traversal.
     * - Provides hasNext() and next().
     * - remove() is optional.
     * - Does not provide add() or set().
     *
     * ListIterator:
     *
     * - Available through List.
     * - Works specifically with Lists.
     * - Supports forward and backward traversal.
     * - Provides hasNext(), next(), hasPrevious(),
     *   and previous().
     * - Provides nextIndex() and previousIndex().
     * - Provides add(), set(), and remove().
     *
     * Remember:
     *
     * ListIterator is more powerful than Iterator for List
     * traversal, but it is not available for every collection.
     *
     *
     * ============================================================
     * 10. IMPORTANT EXCEPTIONS
     * ============================================================
     *
     * NoSuchElementException:
     * Thrown when next() or previous() is called without an
     * available element in that direction.
     *
     * IllegalStateException:
     * Thrown when remove() or set() is called in an invalid state.
     *
     * UnsupportedOperationException:
     * Thrown when an optional operation such as add(), set(),
     * or remove() is not supported.
     *
     * IndexOutOfBoundsException:
     * Thrown when listIterator(index) receives an invalid index.
     *
     *
     * ============================================================
     * 11. IMPORTANT POINTS TO REMEMBER
     * ============================================================
     *
     * 1. ListIterator belongs to java.util.
     *
     * 2. ListIterator extends Iterator.
     *
     * 3. It is specifically designed for List implementations.
     *
     * 4. It supports bidirectional traversal.
     *
     * 5. next() moves forward.
     *
     * 6. previous() moves backward.
     *
     * 7. nextIndex() returns the next element's index.
     *
     * 8. previousIndex() returns the previous element's index.
     *
     * 9. set() replaces the last returned element.
     *
     * 10. add() inserts an element at the cursor position.
     *
     * 11. remove() removes the last returned element.
     *
     * 12. listIterator(index) starts at a specified position.
     *
     * 13. The cursor is positioned between elements.
     *
     * 14. Not every implementation supports every modification.
     *
     *
     * ============================================================
     * 12. INTERVIEW QUESTIONS
     * ============================================================
     *
     * 1. What is ListIterator?
     *
     * 2. Does ListIterator extend Iterator?
     *
     * 3. Can ListIterator traverse a Set?
     *
     * 4. What is the difference between Iterator and ListIterator?
     *
     * 5. How do you traverse a List backward?
     *
     * 6. What is the purpose of nextIndex()?
     *
     * 7. What is the purpose of previousIndex()?
     *
     * 8. What is the difference between add() and set()?
     *
     * 9. Can we modify a List while iterating using ListIterator?
     *
     * 10. Where is the cursor positioned in a ListIterator?
     *
     * 11. What happens if previous() is called at the beginning?
     *
     * 12. Can ListIterator start from a particular index?
     *
     *
     * ============================================================
     * 13. QUICK REVISION
     * ============================================================
     *
     * Iterator:
     * Forward traversal.
     *
     * ListIterator:
     * Forward + backward traversal.
     *
     * hasNext():
     * Checks for a next element.
     *
     * next():
     * Returns the next element.
     *
     * hasPrevious():
     * Checks for a previous element.
     *
     * previous():
     * Returns the previous element.
     *
     * nextIndex():
     * Index of the next element.
     *
     * previousIndex():
     * Index of the previous element.
     *
     * set():
     * Replaces the last returned element.
     *
     * add():
     * Inserts at the cursor position.
     *
     * remove():
     * Removes the last returned element.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about ListIterator , Happy Learning ..!");
    }
}
