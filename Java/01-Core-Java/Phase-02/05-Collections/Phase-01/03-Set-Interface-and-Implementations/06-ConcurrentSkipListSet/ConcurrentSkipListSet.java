
public class ConcurrentSkipListSet {

    /*
     * ============================================================
     *                 JAVA CONCURRENTSKIPLISTSET
     * ============================================================
     *
     * 1. WHAT IS CONCURRENTSKIPLISTSET?
     *
     * Imagine an online application where multiple threads
     * maintain a collection of unique user IDs.
     *
     * You need:
     *
     * - Unique elements.
     * - Sorted order.
     * - Concurrent access from multiple threads.
     * - Efficient searching, insertion and deletion.
     *
     * TreeSet provides sorted order but is not thread-safe.
     *
     * ConcurrentSkipListSet solves this problem.
     *
     * ConcurrentSkipListSet is a thread-safe implementation
     * of the NavigableSet interface.
     *
     * It uses a skip-list data structure to maintain elements
     * in sorted order.
     *
     * IMPORTANT:
     *
     * - Does not allow duplicate elements.
     * - Maintains sorted order.
     * - Supports concurrent operations.
     * - Does not allow null elements.
     * - Provides weakly consistent iterators.
     * - Basic operations generally take O(log n) expected time.
     *
     *
     * ============================================================
     * 2. PACKAGE AND IMPORT
     * ============================================================
     *
     * Package:
     *
     *     java.util.concurrent
     *
     * Imports:
     *
     *     import java.util.concurrent.ConcurrentSkipListSet;
     *     import java.util.NavigableSet;
     *     import java.util.Comparator;
     *
     *
     * ============================================================
     * 3. DECLARATION AND HIERARCHY
     * ============================================================
     *
     * Declaration:
     *
     *     public class ConcurrentSkipListSet<E>
     *         extends AbstractSet<E>
     *         implements NavigableSet<E>, Cloneable, Serializable
     *
     * It implements NavigableSet.
     *
     * Therefore, it also provides:
     *
     * - SortedSet operations.
     * - Set operations.
     * - NavigableSet operations.
     *
     * It belongs to the Java concurrent collections framework.
     *
     *
     * ============================================================
     * 4. CREATING CONCURRENTSKIPLISTSET
     * ============================================================
     *
     * Empty Set:
     *
     *     ConcurrentSkipListSet<Integer> numbers =
     *         new ConcurrentSkipListSet<>();
     *
     * Using NavigableSet:
     *
     *     NavigableSet<Integer> numbers =
     *         new ConcurrentSkipListSet<>();
     *
     * Using a Comparator:
     *
     *     ConcurrentSkipListSet<Integer> numbers =
     *         new ConcurrentSkipListSet<>(
     *             Comparator.reverseOrder()
     *         );
     *
     * From another collection:
     *
     *     List<Integer> values =
     *         Arrays.asList(30, 10, 20, 10);
     *
     *     ConcurrentSkipListSet<Integer> numbers =
     *         new ConcurrentSkipListSet<>(values);
     *
     * Result:
     *
     *     [10, 20, 30]
     *
     *
     * ============================================================
     * 5. WHAT IS A SKIP LIST?
     * ============================================================
     *
     * A skip list is a data structure that stores elements
     * in sorted order using multiple linked-list levels.
     *
     * A normal linked list:
     *
     *     10 -> 20 -> 30 -> 40 -> 50 -> 60
     *
     * To find 50, we may need to traverse several nodes.
     *
     * A skip list adds higher-level links that allow us
     * to skip over multiple elements.
     *
     * Conceptual representation:
     *
     * Level 2: 10 ----------------> 50
     *
     * Level 1: 10 ------> 30 -----> 50 -----> 70
     *
     * Level 0: 10 -> 20 -> 30 -> 40 -> 50 -> 60 -> 70
     *
     * Level 0 contains all elements.
     *
     * Higher levels contain additional links that help
     * searching move faster.
     *
     * The search process:
     *
     * 1. Start at a higher level.
     * 2. Move forward while the next value is smaller
     *    than the target.
     * 3. Move down a level when the next value would
     *    exceed the target.
     * 4. Continue until the target is found or determined
     *    to be absent.
     *
     * This allows expected logarithmic search performance.
     *
     *
     * ============================================================
     * 6. HOW DOES CONCURRENTSKIPLISTSET WORK INTERNALLY?
     * ============================================================
     *
     * ConcurrentSkipListSet uses a ConcurrentNavigableMap
     * internally.
     *
     * In standard OpenJDK implementations, the backing
     * structure is ConcurrentSkipListMap.
     *
     * Conceptually:
     *
     *     ConcurrentSkipListSet
     *                |
     *                v
     *     ConcurrentSkipListMap
     *                |
     *                v
     *             Skip List
     *
     * Elements are stored as keys in the internal map.
     *
     * A shared dummy value is used as the associated map value.
     *
     * This is similar to how HashSet uses keys in HashMap,
     * but the underlying data structures are different.
     *
     * IMPORTANT:
     *
     * This is an OpenJDK implementation detail.
     *
     *
     * ============================================================
     * 7. WHY USE A SKIP LIST?
     * ============================================================
     *
     * A normal linked list:
     *
     * - Maintains sequence.
     * - Searching is O(n).
     *
     * A balanced tree:
     *
     * - Maintains sorted order.
     * - Searching is O(log n).
     *
     * A skip list:
     *
     * - Maintains sorted order.
     * - Uses multiple linked-list levels.
     * - Provides expected O(log n) search.
     * - Supports concurrent operations.
     *
     * Skip lists can be implemented without the complex
     * tree-balancing rotations used by balanced search trees.
     *
     * ConcurrentSkipListSet uses a concurrent skip-list
     * implementation designed for safe concurrent access.
     *
     *
     * ============================================================
     * 8. BASIC OPERATIONS
     * ============================================================
     *
     *     ConcurrentSkipListSet<Integer> numbers =
     *         new ConcurrentSkipListSet<>();
     *
     * ADD:
     *
     *     numbers.add(30);
     *     numbers.add(10);
     *     numbers.add(20);
     *
     * Result:
     *
     *     [10, 20, 30]
     *
     * ADD DUPLICATE:
     *
     *     numbers.add(20); // false
     *
     * SEARCH:
     *
     *     numbers.contains(20);
     *
     * REMOVE:
     *
     *     numbers.remove(10);
     *
     * SIZE:
     *
     *     numbers.size();
     *
     * CLEAR:
     *
     *     numbers.clear();
     *
     *
     * ============================================================
     * 9. SORTED ORDER
     * ============================================================
     *
     * ConcurrentSkipListSet maintains elements in sorted order.
     *
     * By default, it uses natural ordering.
     *
     * Example:
     *
     *     ConcurrentSkipListSet<Integer> numbers =
     *         new ConcurrentSkipListSet<>();
     *
     *     numbers.add(50);
     *     numbers.add(10);
     *     numbers.add(30);
     *
     * Iteration order:
     *
     *     10, 30, 50
     *
     * A custom Comparator can change the ordering.
     *
     * Example:
     *
     *     ConcurrentSkipListSet<Integer> numbers =
     *         new ConcurrentSkipListSet<>(
     *             Comparator.reverseOrder()
     *         );
     *
     * Iteration order:
     *
     *     Descending order.
     *
     *
     * ============================================================
     * 10. COMPARABLE AND COMPARATOR
     * ============================================================
     *
     * Like TreeSet, ConcurrentSkipListSet needs an ordering
     * for its elements.
     *
     * Natural ordering:
     *
     *     Elements implement Comparable.
     *
     * Custom ordering:
     *
     *     Supply a Comparator.
     *
     * Example:
     *
     *     class Student implements Comparable<Student> {
     *
     *         int id;
     *
     *         Student(int id) {
     *             this.id = id;
     *         }
     *
     *         @Override
     *         public int compareTo(Student other) {
     *             return Integer.compare(this.id, other.id);
     *         }
     *     }
     *
     * The Set can now maintain Student objects sorted by ID.
     *
     * IMPORTANT:
     *
     * The comparison mechanism determines whether two elements
     * are equivalent for Set membership.
     *
     * If compare(a, b) returns zero, the Set treats them
     * as equivalent.
     *
     *
     * ============================================================
     * 11. THREAD SAFETY
     * ============================================================
     *
     * ConcurrentSkipListSet is designed for concurrent access.
     *
     * Multiple threads can perform operations such as:
     *
     * - add()
     * - remove()
     * - contains()
     * - iteration
     *
     * without requiring the application to synchronize every
     * individual operation.
     *
     * Example:
     *
     *     ConcurrentSkipListSet<Integer> numbers =
     *         new ConcurrentSkipListSet<>();
     *
     *     Thread t1 = new Thread(() -> {
     *         numbers.add(10);
     *         numbers.add(20);
     *     });
     *
     *     Thread t2 = new Thread(() -> {
     *         numbers.add(30);
     *         numbers.add(40);
     *     });
     *
     *     t1.start();
     *     t2.start();
     *
     * The collection safely coordinates concurrent updates.
     *
     * IMPORTANT:
     *
     * Thread-safe individual operations do not automatically
     * make a sequence of multiple operations atomic.
     *
     *
     * ============================================================
     * 12. WEAKLY CONSISTENT ITERATORS
     * ============================================================
     *
     * ConcurrentSkipListSet provides weakly consistent iterators.
     *
     * What does this mean?
     *
     * An iterator:
     *
     * - Does not throw ConcurrentModificationException merely
     *   because the collection is concurrently modified.
     * - Can reflect elements that existed when iteration began.
     * - May also reflect some modifications made during iteration.
     * - Does not promise a fixed snapshot.
     *
     * Example:
     *
     *     ConcurrentSkipListSet<Integer> numbers =
     *         new ConcurrentSkipListSet<>();
     *
     *     numbers.add(10);
     *     numbers.add(20);
     *
     *     Iterator<Integer> iterator = numbers.iterator();
     *
     *     numbers.add(30);
     *
     *     while (iterator.hasNext()) {
     *         System.out.println(iterator.next());
     *     }
     *
     * The iterator may reflect the newly added element,
     * depending on when it is encountered during traversal.
     *
     * Do not depend on a particular result for concurrent
     * modifications.
     *
     * IMPORTANT:
     *
     * Weakly consistent is different from snapshot iteration.
     *
     * CopyOnWriteArraySet:
     *     Snapshot iterator.
     *
     * ConcurrentSkipListSet:
     *     Weakly consistent iterator.
     *
     *
     * ============================================================
     * 13. ITERATOR REMOVE()
     * ============================================================
     *
     * Unlike CopyOnWriteArraySet's snapshot iterator,
     * ConcurrentSkipListSet iterators support removal.
     *
     * Example:
     *
     *     Iterator<Integer> iterator = numbers.iterator();
     *
     *     while (iterator.hasNext()) {
     *         Integer value = iterator.next();
     *
     *         if (value == 10) {
     *             iterator.remove();
     *         }
     *     }
     *
     * This removes the element from the collection.
     *
     *
     * ============================================================
     * 14. DUPLICATE ELEMENTS
     * ============================================================
     *
     * ConcurrentSkipListSet does not allow duplicates.
     *
     * It uses its ordering comparison to determine equivalence.
     *
     * Example:
     *
     *     ConcurrentSkipListSet<String> names =
     *         new ConcurrentSkipListSet<>(
     *             String.CASE_INSENSITIVE_ORDER
     *         );
     *
     *     names.add("Java");
     *     names.add("JAVA");
     *
     * Only one element is stored because the Comparator
     * considers both equivalent.
     *
     * For consistency with the Set contract, ordering should
     * ideally be consistent with equals().
     *
     *
     * ============================================================
     * 15. NULL ELEMENT
     * ============================================================
     *
     * ConcurrentSkipListSet does not allow null elements.
     *
     * Example:
     *
     *     ConcurrentSkipListSet<Integer> numbers =
     *         new ConcurrentSkipListSet<>();
     *
     *     numbers.add(null); // NullPointerException
     *
     * The ordering mechanism cannot safely handle null
     * under the collection's contract.
     *
     *
     * ============================================================
     * 16. NAVIGABLESET METHODS
     * ============================================================
     *
     * ConcurrentSkipListSet implements NavigableSet.
     *
     * Therefore, it provides navigation methods.
     *
     * lower(e):
     *     Greatest element strictly less than e.
     *
     * floor(e):
     *     Greatest element less than or equal to e.
     *
     * ceiling(e):
     *     Smallest element greater than or equal to e.
     *
     * higher(e):
     *     Smallest element strictly greater than e.
     *
     * Example:
     *
     *     ConcurrentSkipListSet<Integer> numbers =
     *         new ConcurrentSkipListSet<>();
     *
     *     numbers.add(10);
     *     numbers.add(20);
     *     numbers.add(30);
     *
     *     numbers.lower(20);   // 10
     *     numbers.floor(20);   // 20
     *     numbers.ceiling(25); // 30
     *     numbers.higher(20);  // 30
     *
     *
     * ============================================================
     * 17. FIRST AND LAST
     * ============================================================
     *
     * first():
     *     Returns the lowest element.
     *
     * last():
     *     Returns the highest element.
     *
     * Example:
     *
     *     numbers.first();
     *     numbers.last();
     *
     *
     * ============================================================
     * 18. RANGE OPERATIONS
     * ============================================================
     *
     * headSet():
     *     Returns elements less than a specified element.
     *
     * tailSet():
     *     Returns elements greater than or equal to a specified
     *     element.
     *
     * subSet():
     *     Returns elements in a specified range.
     *
     * Example:
     *
     *     numbers.headSet(30);
     *     numbers.tailSet(20);
     *     numbers.subSet(10, 30);
     *
     * NavigableSet overloads support explicit inclusivity:
     *
     *     numbers.subSet(10, true, 30, true);
     *
     * These methods return backed views.
     *
     * Changes through the view are reflected in the original
     * Set, subject to the range restrictions.
     *
     *
     * ============================================================
     * 19. DESCENDING ORDER
     * ============================================================
     *
     * descendingSet() returns a reverse-order view.
     *
     * Example:
     *
     *     NavigableSet<Integer> reverse =
     *         numbers.descendingSet();
     *
     * If numbers contains:
     *
     *     [10, 20, 30]
     *
     * The descending view iterates as:
     *
     *     [30, 20, 10]
     *
     * This is a backed view, not an independent copy.
     *
     *
     * ============================================================
     * 20. TIME COMPLEXITY
     * ============================================================
     *
     * Operation             Expected complexity
     *
     * add()                 O(log n)
     * remove()              O(log n)
     * contains()            O(log n)
     * lower()               O(log n)
     * floor()               O(log n)
     * ceiling()             O(log n)
     * higher()              O(log n)
     *
     * Iterating through all elements takes O(n).
     *
     * These are expected performance characteristics of
     * the skip-list implementation.
     *
     *
     * ============================================================
     * 21. CONCURRENTSKIPLISTSET VS TREESET
     * ============================================================
     *
     * TreeSet:
     *
     * - Uses a tree-based structure in OpenJDK.
     * - Maintains sorted order.
     * - Not thread-safe by default.
     * - Basic operations O(log n).
     * - Iterators are fail-fast on a best-effort basis.
     *
     * ConcurrentSkipListSet:
     *
     * - Uses a skip-list structure in OpenJDK.
     * - Maintains sorted order.
     * - Thread-safe.
     * - Expected O(log n) basic operations.
     * - Iterators are weakly consistent.
     *
     * Choose TreeSet for ordinary sorted Set usage without
     * concurrent access requirements.
     *
     * Choose ConcurrentSkipListSet when sorted Set operations
     * must be safely shared between multiple threads.
     *
     *
     * ============================================================
     * 22. CONCURRENTSKIPLISTSET VS COPYONWRITEARRAYSET
     * ============================================================
     *
     * CopyOnWriteArraySet:
     *
     * - Uses array-based storage.
     * - Thread-safe.
     * - Snapshot iterators.
     * - O(n) search and modification.
     * - Suitable for small, read-heavy collections.
     *
     * ConcurrentSkipListSet:
     *
     * - Uses a skip-list structure.
     * - Thread-safe.
     * - Weakly consistent iterators.
     * - Expected O(log n) basic operations.
     * - Maintains sorted order.
     *
     * Choose based on:
     *
     * - Collection size.
     * - Read/write frequency.
     * - Whether sorted order is needed.
     * - Iterator consistency requirements.
     *
     *
     * ============================================================
     * 23. COMMON USE CASES
     * ============================================================
     *
     * 1. Concurrently maintained sorted user IDs.
     *
     * 2. Real-time sorted rankings.
     *
     * 3. Concurrent price tracking.
     *
     * 4. Shared sorted registries.
     *
     * 5. Maintaining unique ordered identifiers across threads.
     *
     * 6. Concurrent range and navigation queries.
     *
     *
     * ============================================================
     * 24. COMMON MISTAKES
     * ============================================================
     *
     * 1. Assuming it preserves insertion order.
     *    It maintains sorted order.
     *
     * 2. Assuming it allows null.
     *    It does not.
     *
     * 3. Confusing weakly consistent iterators with snapshot
     *    iterators.
     *
     * 4. Assuming thread safety makes compound operations
     *    atomic.
     *
     * 5. Forgetting that compare() returning zero means
     *    equivalent elements for Set membership.
     *
     * 6. Assuming it is internally implemented using
     *    a Red-Black Tree.
     *    Standard OpenJDK uses a skip-list structure.
     *
     *
     * ============================================================
     * 25. INTERVIEW QUICK REVISION
     * ============================================================
     *
     * Q1. What is ConcurrentSkipListSet?
     * A thread-safe sorted implementation of NavigableSet.
     *
     * Q2. What data structure does it use?
     * A skip list.
     *
     * Q3. Which class backs it in standard OpenJDK?
     * ConcurrentSkipListMap.
     *
     * Q4. Does it allow duplicates?
     * No.
     *
     * Q5. Does it allow null?
     * No.
     *
     * Q6. Is it thread-safe?
     * Yes.
     *
     * Q7. What type of iterator does it provide?
     * Weakly consistent.
     *
     * Q8. Does it maintain sorted order?
     * Yes.
     *
     * Q9. What is the expected time complexity of add()?
     * O(log n).
     *
     * Q10. Difference between TreeSet and
     *      ConcurrentSkipListSet?
     * Both maintain sorted order, but ConcurrentSkipListSet
     * supports concurrent operations.
     *
     * Q11. Difference between snapshot and weakly consistent
     *      iterators?
     * Snapshot iterators use a fixed captured state;
     * weakly consistent iterators may reflect concurrent
     * modifications without throwing
     * ConcurrentModificationException.
     *
     * Q12. Does it support lower(), floor(), ceiling(), higher()?
     * Yes, through NavigableSet.
     *
     *
     * ============================================================
     * 26. FINAL UNDERSTANDING
     * ============================================================
     *
     * Remember all six Set implementations:
     *
     * HashSet:
     *     Unique elements + no guaranteed order.
     *
     * LinkedHashSet:
     *     Unique elements + insertion order.
     *
     * TreeSet:
     *     Unique elements + sorted order.
     *
     * EnumSet:
     *     Specialized storage for enum constants.
     *
     * CopyOnWriteArraySet:
     *     Thread-safe + snapshot iteration + read-heavy use.
     *
     * ConcurrentSkipListSet:
     *     Thread-safe + sorted order + concurrent operations.
     *
     * ConcurrentSkipListSet is a good choice when you need
     * a sorted Set that can be safely accessed by multiple
     * threads.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about ConcurrentSkipListSet , Happy Learning ..!");
    }
}
