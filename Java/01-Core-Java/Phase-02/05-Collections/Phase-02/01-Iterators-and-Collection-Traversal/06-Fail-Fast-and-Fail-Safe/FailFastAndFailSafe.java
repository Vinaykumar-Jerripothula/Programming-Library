
public class FailFastAndFailSafe {

    /*
     * ============================================================
     * 1. WHY DO WE NEED TO UNDERSTAND FAIL-FAST AND FAIL-SAFE?
     * ============================================================
     *
     * Imagine that an Iterator is traversing an ArrayList:
     *
     * [10, 20, 30, 40]
     *
     * While the iterator is traversing, another operation
     * modifies the same collection:
     *
     * [10, 20, 30, 40] -> [10, 20, 40]
     *
     * Now the iterator's expected view of the collection
     * may no longer match its actual structure.
     *
     * This can lead to unexpected traversal behavior.
     *
     * Java provides different iterator behaviors for
     * handling modifications during traversal.
     *
     * Two commonly discussed terms are:
     *
     * 1. Fail-Fast
     * 2. Fail-Safe
     *
     *
     * ============================================================
     * 2. WHAT IS FAIL-FAST?
     * ============================================================
     *
     * A fail-fast iterator attempts to detect structural
     * modifications to its collection while iteration is
     * in progress.
     *
     * If it detects an unexpected structural modification,
     * it may throw:
     *
     * ConcurrentModificationException
     *
     * Example:
     *
     * List<Integer> numbers = new ArrayList<>(
     *     List.of(10, 20, 30, 40)
     * );
     *
     * Iterator<Integer> it = numbers.iterator();
     *
     * while (it.hasNext()) {
     *
     *     Integer number = it.next();
     *
     *     if (number == 20) {
     *         numbers.remove(number);
     *     }
     * }
     *
     * This commonly throws:
     *
     * ConcurrentModificationException
     *
     * Why?
     *
     * The collection was structurally modified directly
     * while its iterator was active.
     *
     * Important:
     *
     * Fail-fast behavior is best-effort.
     *
     * It is not guaranteed that every structural modification
     * will always be detected.
     *
     * Do not depend on ConcurrentModificationException
     * for program correctness.
     *
     *
     * ============================================================
     * 3. WHAT IS A STRUCTURAL MODIFICATION?
     * ============================================================
     *
     * A structural modification changes the structure of
     * a collection.
     *
     * Examples:
     *
     * - Adding an element.
     * - Removing an element.
     * - Clearing the collection.
     *
     * For ArrayList:
     *
     * List<Integer> numbers = new ArrayList<>(
     *     List.of(10, 20, 30)
     * );
     *
     * numbers.add(40);      // Structural modification.
     * numbers.remove(0);    // Structural modification.
     * numbers.clear();      // Structural modification.
     *
     * Replacing an existing element using set() does not
     * normally count as a structural modification.
     *
     * Example:
     *
     * numbers.set(0, 100);
     *
     * This replaces an existing element without changing
     * the collection's size or structure.
     *
     * Important:
     *
     * The exact meaning of structural modification depends
     * on the collection's contract.
     *
     *
     * ============================================================
     * 4. HOW DOES FAIL-FAST WORK INTERNALLY?
     * ============================================================
     *
     * Many fail-fast collection implementations use an
     * internal modification counter.
     *
     * In ArrayList, the relevant field is:
     *
     * modCount
     *
     * It tracks structural modifications.
     *
     * When an iterator is created, it records the current
     * modification count in a field commonly called:
     *
     * expectedModCount
     *
     * Conceptual example:
     *
     * ArrayList modCount = 5
     *
     * Iterator expectedModCount = 5
     *
     * Both values initially match.
     *
     * Now someone modifies the ArrayList directly.
     *
     * ArrayList modCount = 6
     *
     * Iterator expectedModCount = 5
     *
     * The values no longer match.
     *
     * During certain iterator operations, the implementation
     * checks whether:
     *
     * modCount != expectedModCount
     *
     * If a mismatch is detected, it may throw:
     *
     * ConcurrentModificationException
     *
     * Important:
     *
     * modCount and expectedModCount are implementation details,
     * not fields required by the Iterator interface.
     *
     * Different collection implementations may use
     * different mechanisms.
     *
     *
     * ============================================================
     * 5. HOW TO AVOID CONCURRENTMODIFICATIONEXCEPTION?
     * ============================================================
     *
     * There are several appropriate approaches.
     *
     *
     * ------------------------------------------------------------
     * 5.1 USE ITERATOR.REMOVE()
     * ------------------------------------------------------------
     *
     * Example:
     *
     * List<Integer> numbers = new ArrayList<>(
     *     List.of(10, 20, 30, 40)
     * );
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
     * System.out.println(numbers);
     *
     * Output:
     * [10, 30, 40]
     *
     * Why does this work?
     *
     * The iterator performs the removal itself and updates
     * its expected traversal state appropriately.
     *
     *
     * ------------------------------------------------------------
     * 5.2 USE COLLECTION.REMOVEIF()
     * ------------------------------------------------------------
     *
     * Introduced in Java 8.
     *
     * Example:
     *
     * List<Integer> numbers = new ArrayList<>(
     *     List.of(10, 20, 30, 40)
     * );
     *
     * numbers.removeIf(number -> number == 20);
     *
     * System.out.println(numbers);
     *
     * Output:
     * [10, 30, 40]
     *
     * removeIf() is often the simplest option when removing
     * elements that satisfy a condition.
     *
     *
     * ------------------------------------------------------------
     * 5.3 USE A SEPARATE COLLECTION
     * ------------------------------------------------------------
     *
     * If you need to preserve the original collection,
     * create a separate collection for the result.
     *
     * Example:
     *
     * List<Integer> numbers = List.of(10, 20, 30, 40);
     *
     * List<Integer> filtered = new ArrayList<>();
     *
     * for (Integer number : numbers) {
     *
     *     if (number != 20) {
     *         filtered.add(number);
     *     }
     * }
     *
     * System.out.println(filtered);
     *
     * Output:
     * [10, 30, 40]
     *
     *
     * ============================================================
     * 6. WHAT IS FAIL-SAFE?
     * ============================================================
     *
     * Fail-safe is a commonly used informal term for iterators
     * that can continue traversal without throwing
     * ConcurrentModificationException when the underlying
     * collection is modified in certain ways.
     *
     * Important:
     *
     * Java's Iterator API does not officially define an
     * iterator category named "fail-safe".
     *
     * The term is commonly used to describe mechanisms such as:
     *
     * 1. Snapshot-based iteration.
     * 2. Weakly consistent iteration.
     *
     * These mechanisms behave differently.
     *
     *
     * ============================================================
     * 7. SNAPSHOT-BASED ITERATION
     * ============================================================
     *
     * CopyOnWriteArrayList provides snapshot-based iterators.
     *
     * When an iterator is created, it observes a snapshot
     * of the array at that point in time.
     *
     * Later modifications to the original collection do not
     * change the snapshot used by that iterator.
     *
     * Example:
     *
     * CopyOnWriteArrayList<Integer> numbers =
     *     new CopyOnWriteArrayList<>(
     *         List.of(10, 20, 30)
     *     );
     *
     * Iterator<Integer> it = numbers.iterator();
     *
     * numbers.add(40);
     *
     * while (it.hasNext()) {
     *     System.out.println(it.next());
     * }
     *
     * Output:
     *
     * 10
     * 20
     * 30
     *
     * Notice:
     *
     * 40 was added after the iterator was created.
     *
     * Therefore, the existing iterator does not see 40.
     *
     * A new iterator created after the addition can see 40.
     *
     * Important:
     *
     * CopyOnWriteArrayList iterators do not support remove(),
     * set(), or add().
     *
     * Calling these methods throws UnsupportedOperationException.
     *
     *
     * ============================================================
     * 8. WEAKLY CONSISTENT ITERATORS
     * ============================================================
     *
     * Some concurrent collections provide weakly consistent
     * iterators.
     *
     * Examples:
     *
     * - ConcurrentHashMap
     * - ConcurrentLinkedQueue
     * - ConcurrentSkipListSet
     *
     * These iterators:
     *
     * 1. Do not normally throw ConcurrentModificationException
     *    because of concurrent modifications.
     *
     * 2. Can traverse while other threads modify the collection.
     *
     * 3. May reflect some modifications made after iterator
     *    creation, depending on the collection and timing.
     *
     * 4. Do not necessarily represent one fixed snapshot.
     *
     * Example:
     *
     * ConcurrentHashMap<Integer, String> students =
     *     new ConcurrentHashMap<>();
     *
     * students.put(1, "Vinay");
     * students.put(2, "Rahul");
     *
     * Iterator<Integer> it = students.keySet().iterator();
     *
     * students.put(3, "Arjun");
     *
     * while (it.hasNext()) {
     *     System.out.println(it.next());
     * }
     *
     * The iterator can continue without
     * ConcurrentModificationException.
     *
     * Whether it observes the newly added key depends on
     * the iterator's weakly consistent traversal behavior.
     *
     *
     * ============================================================
     * 9. SNAPSHOT VS WEAKLY CONSISTENT ITERATORS
     * ============================================================
     *
     * Snapshot-based:
     *
     * - Traverses a fixed snapshot.
     * - Later changes are not reflected in that snapshot.
     * - Example: CopyOnWriteArrayList.
     *
     * Weakly consistent:
     *
     * - Can observe some changes made during traversal.
     * - Does not promise a fixed snapshot.
     * - Example: ConcurrentHashMap.
     *
     * Important:
     *
     * Both approaches support traversal during certain
     * concurrent modifications, but their guarantees differ.
     *
     *
     * ============================================================
     * 10. FAIL-FAST VS SNAPSHOT-BASED VS WEAKLY CONSISTENT
     * ============================================================
     *
     * Fail-fast:
     *
     * - Detects certain unexpected structural modifications.
     * - May throw ConcurrentModificationException.
     * - Example: ArrayList iterator.
     *
     * Snapshot-based:
     *
     * - Traverses a snapshot captured at iterator creation.
     * - Does not reflect later changes in that snapshot.
     * - Example: CopyOnWriteArrayList iterator.
     *
     * Weakly consistent:
     *
     * - Can reflect some concurrent modifications.
     * - Does not guarantee a fixed snapshot.
     * - Example: ConcurrentHashMap iterator.
     *
     *
     * ============================================================
     * 11. FAIL-FAST DOES NOT MEAN THREAD-SAFE
     * ============================================================
     *
     * This is an important interview concept.
     *
     * A fail-fast iterator does not make a collection
     * thread-safe.
     *
     * It only attempts to detect certain unexpected
     * structural modifications.
     *
     * For example:
     *
     * ArrayList is not thread-safe.
     *
     * Its iterator being fail-fast does not make concurrent
     * access safe.
     *
     * Similarly, the absence of ConcurrentModificationException
     * does not prove that concurrent access is safe.
     *
     * Use appropriate concurrent collections or synchronization
     * when multiple threads access shared mutable data.
     *
     *
     * ============================================================
     * 12. WHICH COLLECTIONS USE WHICH ITERATOR BEHAVIOR?
     * ============================================================
     *
     * Common examples:
     *
     * ArrayList:
     * Fail-fast iterator.
     *
     * LinkedList:
     * Fail-fast iterator.
     *
     * HashSet:
     * Fail-fast iterator.
     *
     * HashMap:
     * Fail-fast iterators for its collection views.
     *
     * TreeSet:
     * Fail-fast iterator.
     *
     * TreeMap:
     * Fail-fast iterators for its collection views.
     *
     * CopyOnWriteArrayList:
     * Snapshot-based iterator.
     *
     * CopyOnWriteArraySet:
     * Snapshot-based iterator.
     *
     * ConcurrentHashMap:
     * Weakly consistent iterators.
     *
     * ConcurrentLinkedQueue:
     * Weakly consistent iterator.
     *
     * ConcurrentSkipListSet:
     * Weakly consistent iterator.
     *
     * ConcurrentSkipListMap:
     * Weakly consistent iterators for its views.
     *
     * Note:
     * Always check the documentation for the particular
     * collection implementation.
     *
     *
     * ============================================================
     * 13. COMMON MISCONCEPTIONS
     * ============================================================
     *
     * Misconception 1:
     *
     * Fail-fast guarantees ConcurrentModificationException.
     *
     * Correction:
     * Detection is best-effort, not guaranteed.
     *
     *
     * Misconception 2:
     *
     * Fail-fast means thread-safe.
     *
     * Correction:
     * Fail-fast does not provide thread safety.
     *
     *
     * Misconception 3:
     *
     * Fail-safe is an official Java interface.
     *
     * Correction:
     * It is an informal term, not an official Iterator category.
     *
     *
     * Misconception 4:
     *
     * All concurrent collection iterators use snapshots.
     *
     * Correction:
     * Some use snapshot-based traversal; others use
     * weakly consistent traversal.
     *
     *
     * Misconception 5:
     *
     * Every collection modification causes
     * ConcurrentModificationException.
     *
     * Correction:
     * It depends on the collection, iterator implementation,
     * and type of modification.
     *
     *
     * ============================================================
     * 14. IMPORTANT POINTS TO REMEMBER
     * ============================================================
     *
     * 1. Fail-fast iterators attempt to detect unexpected
     *    structural modifications.
     *
     * 2. They may throw ConcurrentModificationException.
     *
     * 3. Fail-fast detection is best-effort.
     *
     * 4. ArrayList, LinkedList, HashSet, and HashMap provide
     *    commonly encountered fail-fast iterators.
     *
     * 5. Iterator.remove() is the supported way to remove
     *    elements during traversal when available.
     *
     * 6. CopyOnWriteArrayList provides snapshot-based iterators.
     *
     * 7. ConcurrentHashMap provides weakly consistent iterators.
     *
     * 8. Snapshot and weakly consistent are different behaviors.
     *
     * 9. Fail-fast does not mean thread-safe.
     *
     * 10. Fail-safe is an informal term, not an official
     *     Java Iterator category.
     *
     *
     * ============================================================
     * 15. INTERVIEW QUESTIONS
     * ============================================================
     *
     * 1. What is a fail-fast iterator?
     *
     * 2. What is ConcurrentModificationException?
     *
     * 3. Why does ArrayList use fail-fast iterators?
     *
     * 4. What is modCount?
     *
     * 5. What is expectedModCount?
     *
     * 6. What is a structural modification?
     *
     * 7. How can we safely remove elements during iteration?
     *
     * 8. Is fail-fast behavior guaranteed?
     *
     * 9. What is a snapshot-based iterator?
     *
     * 10. How does CopyOnWriteArrayList iteration work?
     *
     * 11. What is a weakly consistent iterator?
     *
     * 12. How does ConcurrentHashMap iteration behave?
     *
     * 13. What is the difference between snapshot-based
     *     and weakly consistent iteration?
     *
     * 14. Is fail-fast the same as thread-safe?
     *
     * 15. Is fail-safe an official Java term?
     *
     *
     * ============================================================
     * 16. QUICK REVISION
     * ============================================================
     *
     * Fail-fast:
     * Attempts to detect unexpected structural modifications.
     *
     * ConcurrentModificationException:
     * May be thrown when an unexpected modification is detected.
     *
     * modCount:
     * Internal structural modification counter in certain
     * collection implementations.
     *
     * expectedModCount:
     * Iterator's expected modification count in certain
     * implementations.
     *
     * Snapshot-based:
     * Traverses a fixed snapshot.
     *
     * Weakly consistent:
     * Can reflect some concurrent modifications.
     *
     * CopyOnWriteArrayList:
     * Snapshot-based iterator.
     *
     * ConcurrentHashMap:
     * Weakly consistent iterator.
     *
     * Fail-fast:
     * Does not guarantee thread safety.
     *
     * Fail-safe:
     * Informal term, not an official Java interface category.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Fail-Fast and Fail-Safe Iterators , Happy Learning ..!");
    }
}
