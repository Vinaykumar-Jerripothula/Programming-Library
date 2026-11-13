
public class IteratorInterface {

    /*
     * ============================================================
     * 1. WHY DO WE NEED ITERATORS?
     * ============================================================
     *
     * Imagine that you have a collection containing 100 student
     * names. You want to visit each name one by one.
     *
     * Different collections store elements differently:
     *
     * ArrayList       -> Uses an array internally.
     * LinkedList      -> Uses linked nodes internally.
     * HashSet         -> Uses a hash table internally.
     * TreeSet         -> Uses a tree internally.
     *
     * If every collection required a different way of accessing
     * its elements, our code would become complicated.
     *
     * For example:
     *
     * ArrayList -> Access elements using indexes.
     * LinkedList -> Traverse nodes.
     * HashSet -> No index-based access.
     *
     * Java solves this problem using ITERATORS.
     *
     * An Iterator provides a common way to traverse elements
     * without needing to know how the collection stores them.
     *
     * Think of an iterator as a person walking through a line
     * of people, visiting one person at a time.
     *
     * It remembers its current traversal position.
     *
     *
     * ============================================================
     * 2. WHAT IS AN ITERATOR?
     * ============================================================
     *
     * Iterator is an interface available in java.util.
     *
     * Declaration:
     *
     * public interface Iterator<E>
     *
     * E represents the type of elements being traversed.
     *
     * Examples:
     *
     * Iterator<String>
     * Iterator<Integer>
     * Iterator<Student>
     *
     * An Iterator is used to:
     *
     * 1. Check whether another element exists.
     * 2. Retrieve the next element.
     * 3. Optionally remove the last returned element.
     * 4. Process the remaining elements using a callback.
     *
     * Important:
     *
     * Iterable and Iterator are different interfaces.
     *
     * Iterable:
     *     Represents an object whose elements can be iterated.
     *     Provides the iterator() method.
     *
     * Iterator:
     *     Represents the actual traversal mechanism.
     *     Provides methods such as hasNext() and next().
     *
     * Relationship:
     *
     * Collection -> Iterable -> iterator() -> Iterator
     *
     * The Collection interface extends Iterable.
     *
     * Therefore, collections such as ArrayList, LinkedList,
     * HashSet and TreeSet provide the iterator() method.
     *
     * A Map does not directly implement Iterable.
     * To iterate over a Map, use keySet(), values(), or entrySet().
     *
     *
     * ============================================================
     * 3. HOW TO CREATE AN ITERATOR?
     * ============================================================
     *
     * Syntax:
     *
     * Iterator<Type> iteratorName = collection.iterator();
     *
     * Example:
     *
     * List<String> names = new ArrayList<>();
     *
     * names.add("Vinay");
     * names.add("Rahul");
     * names.add("Arjun");
     *
     * Iterator<String> iterator = names.iterator();
     *
     * The iterator() method returns an Iterator for traversing
     * the elements of the collection.
     *
     * Each call to iterator() normally creates a new iterator
     * with its own traversal state.
     *
     *
     * ============================================================
     * 4. IMPORTANT METHODS OF ITERATOR
     * ============================================================
     *
     * Iterator provides four important methods:
     *
     * 1. hasNext()
     * 2. next()
     * 3. remove()
     * 4. forEachRemaining()
     *
     *
     * ------------------------------------------------------------
     * 4.1 hasNext()
     * ------------------------------------------------------------
     *
     * Declaration:
     *
     * boolean hasNext();
     *
     * Purpose:
     * Checks whether another element is available.
     *
     * Returns:
     * true  -> Another element exists.
     * false -> No more elements exist.
     *
     * Example:
     *
     * Iterator<String> it = names.iterator();
     *
     * System.out.println(it.hasNext());
     *
     * Important:
     * hasNext() does not move the iterator forward.
     * It only checks whether another element is available.
     *
     *
     * ------------------------------------------------------------
     * 4.2 next()
     * ------------------------------------------------------------
     *
     * Declaration:
     *
     * E next();
     *
     * Purpose:
     * Returns the next element and advances the iterator.
     *
     * Example:
     *
     * Iterator<String> it = names.iterator();
     *
     * System.out.println(it.next());
     *
     * Suppose the collection contains:
     *
     * [Vinay, Rahul, Arjun]
     *
     * First next()  -> Vinay
     * Second next() -> Rahul
     * Third next()  -> Arjun
     *
     * Calling next() again throws:
     *
     * NoSuchElementException
     *
     * Therefore, normally check hasNext() before calling next().
     *
     *
     * ------------------------------------------------------------
     * 4.3 remove()
     * ------------------------------------------------------------
     *
     * Declaration:
     *
     * void remove();
     *
     * Purpose:
     * Removes the last element returned by next().
     *
     * Example:
     *
     * List<String> names = new ArrayList<>(
     *     List.of("Vinay", "Rahul", "Arjun")
     * );
     *
     * Iterator<String> it = names.iterator();
     *
     * while (it.hasNext()) {
     *
     *     String name = it.next();
     *
     *     if (name.equals("Rahul")) {
     *         it.remove();
     *     }
     * }
     *
     * System.out.println(names);
     *
     * Output:
     * [Vinay, Arjun]
     *
     * IMPORTANT RULES:
     *
     * 1. remove() removes the element returned by the most recent
     *    next() call.
     *
     * 2. You cannot call remove() before calling next().
     *
     * 3. You cannot call remove() twice for the same next() call.
     *
     * 4. Calling remove() illegally throws IllegalStateException.
     *
     * 5. remove() is an optional operation.
     *    Some iterators do not support removal and throw
     *    UnsupportedOperationException.
     *
     * 6. After removing an element through iterator.remove(),
     *    the iterator remains usable according to its contract.
     *
     * Why use iterator.remove()?
     *
     * Consider:
     *
     * for (String name : names) {
     *     if (name.equals("Rahul")) {
     *         names.remove(name);
     *     }
     * }
     *
     * This directly modifies the collection while its iterator
     * is traversing it. For many ordinary collections, this can
     * cause ConcurrentModificationException.
     *
     * Prefer iterator.remove() when removing during traversal.
     *
     * Note:
     * Some collections, such as immutable collections and
     * CopyOnWriteArrayList iterators, do not support removal.
     *
     *
     * ------------------------------------------------------------
     * 4.4 forEachRemaining()
     * ------------------------------------------------------------
     *
     * Introduced in Java 8.
     *
     * Declaration:
     *
     * default void forEachRemaining(
     *     Consumer<? super E> action
     * );
     *
     * Purpose:
     * Performs the given action on every remaining element.
     *
     * Example:
     *
     * List<String> names = List.of(
     *     "Vinay", "Rahul", "Arjun"
     * );
     *
     * Iterator<String> it = names.iterator();
     *
     * it.forEachRemaining(System.out::println);
     *
     * Output:
     * Vinay
     * Rahul
     * Arjun
     *
     * You can also use a lambda:
     *
     * it.forEachRemaining(name -> System.out.println(name));
     *
     * Important:
     * It processes only the elements remaining from the
     * iterator's current position.
     *
     *
     * ============================================================
     * 5. HOW DOES AN ITERATOR WORK?
     * ============================================================
     *
     * Consider:
     *
     * List<String> names = List.of(
     *     "A", "B", "C"
     * );
     *
     * Iterator<String> it = names.iterator();
     *
     * Conceptually, the iterator starts before the first element.
     *
     * Initial state:
     *
     * [A, B, C]
     *  ^
     *  |
     * Iterator is ready to return A.
     *
     * First next():
     *
     * Returns A.
     *
     * Second next():
     *
     * Returns B.
     *
     * Third next():
     *
     * Returns C.
     *
     * Fourth next():
     *
     * Throws NoSuchElementException.
     *
     * This is a conceptual model. The actual implementation
     * depends on the collection.
     *
     * For example:
     *
     * ArrayList iterator maintains an index-like cursor.
     *
     * LinkedList iterator maintains references to nodes.
     *
     * HashSet iterator traverses its internal hash table.
     *
     * The Iterator interface hides these implementation details.
     *
     *
     * ============================================================
     * 6. TRAVERSING A COLLECTION USING ITERATOR
     * ============================================================
     *
     * Example:
     *
     * List<Integer> numbers = new ArrayList<>();
     *
     * numbers.add(10);
     * numbers.add(20);
     * numbers.add(30);
     *
     * Iterator<Integer> it = numbers.iterator();
     *
     * while (it.hasNext()) {
     *     Integer number = it.next();
     *     System.out.println(number);
     * }
     *
     * Output:
     * 10
     * 20
     * 30
     *
     * The loop works as follows:
     *
     * 1. hasNext() checks whether an element exists.
     * 2. next() retrieves that element.
     * 3. The loop repeats until hasNext() returns false.
     *
     *
     * ============================================================
     * 7. ITERATOR WORKS WITH DIFFERENT COLLECTIONS
     * ============================================================
     *
     * Iterator provides a common traversal mechanism.
     *
     * ArrayList:
     *
     * List<String> list = new ArrayList<>(
     *     List.of("A", "B", "C")
     * );
     *
     * Iterator<String> it1 = list.iterator();
     *
     *
     * LinkedList:
     *
     * List<String> linkedList = new LinkedList<>(
     *     List.of("A", "B", "C")
     * );
     *
     * Iterator<String> it2 = linkedList.iterator();
     *
     *
     * HashSet:
     *
     * Set<String> set = new HashSet<>(
     *     List.of("A", "B", "C")
     * );
     *
     * Iterator<String> it3 = set.iterator();
     *
     *
     * Queue:
     *
     * Queue<Integer> queue = new LinkedList<>(
     *     List.of(10, 20, 30)
     * );
     *
     * Iterator<Integer> it4 = queue.iterator();
     *
     * The traversal code is the same:
     *
     * while (it.hasNext()) {
     *     System.out.println(it.next());
     * }
     *
     * However, the iteration order depends on the collection.
     *
     * ArrayList -> Index order.
     * LinkedList -> List order.
     * LinkedHashSet -> Insertion order.
     * TreeSet -> Sorted order.
     * HashSet -> No guaranteed encounter order.
     *
     *
     * ============================================================
     * 8. ITERABLE VS ITERATOR
     * ============================================================
     *
     * Iterable:
     *
     * - Represents something that can be iterated.
     * - Provides iterator().
     * - Supports enhanced for loop.
     *
     * Iterator:
     *
     * - Performs the actual traversal.
     * - Provides hasNext(), next(), remove(), etc.
     * - Maintains traversal state.
     *
     * Example:
     *
     * List<String> names = new ArrayList<>();
     *
     * Iterable<String> iterable = names;
     *
     * Iterator<String> iterator = iterable.iterator();
     *
     * Remember:
     *
     * Iterable = Something that can be traversed.
     * Iterator = The object that performs the traversal.
     *
     *
     * ============================================================
     * 9. ITERATOR VS INDEX-BASED FOR LOOP
     * ============================================================
     *
     * Index-based loop:
     *
     * for (int i = 0; i < list.size(); i++) {
     *     System.out.println(list.get(i));
     * }
     *
     * Iterator-based loop:
     *
     * Iterator<String> it = list.iterator();
     *
     * while (it.hasNext()) {
     *     System.out.println(it.next());
     * }
     *
     * Differences:
     *
     * Index-based loop:
     * - Requires index-based access.
     * - Suitable when the index is important.
     * - Can be inefficient for repeated LinkedList.get(i).
     *
     * Iterator:
     * - Does not require indexes.
     * - Works across different collection types.
     * - Provides controlled removal during traversal when supported.
     *
     *
     * ============================================================
     * 10. ITERATOR VS LISTITERATOR
     * ============================================================
     *
     * Iterator:
     *
     * - Works with Collection implementations.
     * - Moves only in the forward direction.
     * - Supports next() and hasNext().
     * - Does not support adding or replacing elements.
     *
     * ListIterator:
     *
     * - Works specifically with List implementations.
     * - Moves forward and backward.
     * - Supports next() and previous().
     * - Supports add() and set() operations.
     *
     * ListIterator is explained in the next topic.
     *
     *
     * ============================================================
     * 11. IMPORTANT EXCEPTIONS
     * ============================================================
     *
     * NoSuchElementException:
     * Thrown when next() is called but no element remains.
     *
     * IllegalStateException:
     * Thrown when remove() is called in an invalid state.
     *
     * UnsupportedOperationException:
     * Thrown when an optional operation such as remove() is
     * not supported by that iterator.
     *
     * ConcurrentModificationException:
     * May be thrown by fail-fast iterators when the collection
     * is structurally modified outside the iterator during
     * iteration.
     *
     * Fail-fast behavior is covered separately in:
     * 06-Fail-Fast-and-Fail-Safe.
     *
     *
     * ============================================================
     * 12. IMPORTANT POINTS TO REMEMBER
     * ============================================================
     *
     * 1. Iterator belongs to java.util.
     *
     * 2. Iterator is a generic interface.
     *
     * 3. iterator() is declared by Iterable.
     *
     * 4. Collection extends Iterable.
     *
     * 5. hasNext() checks for another element.
     *
     * 6. next() retrieves and advances.
     *
     * 7. next() throws NoSuchElementException when exhausted.
     *
     * 8. remove() removes the last element returned by next(),
     *    if supported.
     *
     * 9. remove() is an optional operation.
     *
     * 10. forEachRemaining() was introduced in Java 8.
     *
     * 11. Iterator moves only forward.
     *
     * 12. Every iterator maintains its own traversal state.
     *
     * 13. A Map is not directly Iterable.
     *
     * 14. Iterator does not guarantee thread safety.
     *
     * 15. Iteration order depends on the collection.
     *
     *
     * ============================================================
     * 13. COMMON MISTAKES
     * ============================================================
     *
     * Mistake 1:
     *
     * Calling next() without checking hasNext().
     *
     * Problem:
     * May cause NoSuchElementException.
     *
     * Mistake 2:
     *
     * Calling remove() before next().
     *
     * Problem:
     * Causes IllegalStateException.
     *
     * Mistake 3:
     *
     * Calling remove() twice after one next().
     *
     * Problem:
     * Causes IllegalStateException.
     *
     * Mistake 4:
     *
     * Modifying a fail-fast collection directly while iterating.
     *
     * Problem:
     * May cause ConcurrentModificationException.
     *
     * Mistake 5:
     *
     * Assuming every Iterator supports remove().
     *
     * Problem:
     * Some iterators are read-only.
     *
     * Mistake 6:
     *
     * Assuming every collection follows insertion order.
     *
     * Problem:
     * Iteration order depends on the collection implementation.
     *
     *
     * ============================================================
     * 14. INTERVIEW QUESTIONS
     * ============================================================
     *
     * 1. What is an Iterator in Java?
     *
     * 2. Why do we need Iterator when we already have loops?
     *
     * 3. What is the difference between Iterable and Iterator?
     *
     * 4. Which package contains Iterator?
     *
     * 5. What is the purpose of hasNext()?
     *
     * 6. What happens when next() is called without remaining
     *    elements?
     *
     * 7. Can we remove elements while iterating?
     *
     * 8. What happens if remove() is called before next()?
     *
     * 9. Is remove() a mandatory Iterator operation?
     *
     * 10. What is forEachRemaining()?
     *
     * 11. Can Iterator move backward?
     *
     * 12. Does Map directly provide an Iterator?
     *
     * 13. Can two iterators traverse the same collection
     *     independently?
     *
     * 14. Is Iterator thread-safe?
     *
     * 15. What is the difference between Iterator and
     *     ListIterator?
     *
     *
     * ============================================================
     * 15. QUICK REVISION
     * ============================================================
     *
     * Iterator = Common mechanism for traversing elements.
     *
     * iterator() = Creates/returns an iterator.
     *
     * hasNext() = Checks whether another element exists.
     *
     * next() = Returns the next element and advances.
     *
     * remove() = Removes the last returned element, if supported.
     *
     * forEachRemaining() = Processes all remaining elements.
     *
     * Iterable = Provides the iterator().
     *
     * Iterator = Performs traversal.
     *
     * ListIterator = Bidirectional iterator for Lists.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Iterator Interface , Happy Learning ..!");
    }
}
