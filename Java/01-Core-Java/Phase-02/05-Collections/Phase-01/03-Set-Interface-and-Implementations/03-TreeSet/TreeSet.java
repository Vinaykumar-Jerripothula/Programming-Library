
public class TreeSet {

    /*
     * ============================================================
     *                      JAVA TREESET
     * ============================================================
     *
     * 1. WHAT IS TREESET?
     *
     * Imagine you are maintaining student marks.
     *
     * You want:
     * - Unique marks.
     * - Marks arranged in ascending order.
     *
     * HashSet provides uniqueness but does not guarantee sorted
     * order.
     *
     * LinkedHashSet preserves insertion order, not sorted order.
     *
     * TreeSet solves this problem.
     *
     * TreeSet is a sorted implementation of the Set interface.
     *
     * Declaration:
     *
     *     public class TreeSet<E>
     *         extends AbstractSet<E>
     *         implements NavigableSet<E>, Cloneable, Serializable
     *
     * TreeSet implements NavigableSet.
     *
     * NavigableSet extends SortedSet, and SortedSet extends Set.
     *
     * IMPORTANT:
     *
     * - Does not allow duplicate elements.
     * - Maintains elements in sorted order.
     * - Uses natural ordering or a Comparator.
     * - Does not support null with natural ordering.
     * - Is not synchronized by default.
     * - Basic operations generally take O(log n) time.
     *
     *
     * ============================================================
     * 2. PACKAGE AND IMPORT
     * ============================================================
     *
     * Package:
     *
     *     java.util
     *
     * Imports:
     *
     *     import java.util.TreeSet;
     *     import java.util.Set;
     *     import java.util.SortedSet;
     *     import java.util.NavigableSet;
     *     import java.util.Comparator;
     *
     *
     * ============================================================
     * 3. CREATING TREESET
     * ============================================================
     *
     * Empty TreeSet:
     *
     *     Set<Integer> numbers = new TreeSet<>();
     *
     * Using SortedSet:
     *
     *     SortedSet<Integer> numbers = new TreeSet<>();
     *
     * Using NavigableSet:
     *
     *     NavigableSet<Integer> numbers = new TreeSet<>();
     *
     * Using a Comparator:
     *
     *     TreeSet<Integer> numbers =
     *         new TreeSet<>(Comparator.reverseOrder());
     *
     * From another collection:
     *
     *     List<Integer> values =
     *         Arrays.asList(30, 10, 20, 10);
     *
     *     Set<Integer> numbers = new TreeSet<>(values);
     *
     * Result:
     *
     *     [10, 20, 30]
     *
     * Duplicates are eliminated and elements are sorted.
     *
     *
     * ============================================================
     * 4. HOW DOES TREESET MAINTAIN SORTED ORDER?
     * ============================================================
     *
     * TreeSet uses a tree-based data structure.
     *
     * In standard OpenJDK implementations, TreeSet is backed
     * by a TreeMap.
     *
     * Conceptually:
     *
     *     TreeSet
     *        |
     *        v
     *     TreeMap
     *        |
     *        v
     *     Red-Black Tree
     *
     * A Red-Black Tree is a self-balancing binary search tree.
     *
     * It keeps elements arranged according to their ordering.
     *
     * When a new element is inserted:
     *
     * 1. The tree compares the new element with existing elements.
     * 2. It finds the correct position.
     * 3. If an equivalent element already exists, insertion
     *    does not create a duplicate.
     * 4. Otherwise, the element is inserted.
     * 5. The tree may rebalance itself.
     *
     * IMPORTANT:
     *
     * TreeMap backing and Red-Black Tree implementation are
     * OpenJDK implementation details.
     *
     *
     * ============================================================
     * 5. WHAT IS A BINARY SEARCH TREE?
     * ============================================================
     *
     * A Binary Search Tree (BST) is a tree in which each node
     * has at most two children.
     *
     * For natural ascending order:
     *
     *     Left subtree  -> Smaller values
     *     Right subtree -> Larger values
     *
     * Example:
     *
     *             20
     *            /  \
     *          10    30
     *         / \    / \
     *        5  15  25  35
     *
     * Searching follows the comparison:
     *
     * - If the target is smaller, move left.
     * - If the target is larger, move right.
     * - If comparison is zero, the element is found.
     *
     * A normal BST can become unbalanced.
     *
     * Example:
     *
     *     10
     *       \
     *        20
     *          \
     *           30
     *             \
     *              40
     *
     * This can behave like a linked list.
     *
     * A Red-Black Tree balances itself to keep operations
     * efficient.
     *
     *
     * ============================================================
     * 6. WHAT IS A RED-BLACK TREE?
     * ============================================================
     *
     * A Red-Black Tree is a self-balancing binary search tree.
     *
     * It assigns each node a color:
     *
     * - RED
     * - BLACK
     *
     * It follows balancing rules that prevent the tree from
     * becoming excessively tall.
     *
     * Important properties:
     *
     * 1. Every node is either red or black.
     *
     * 2. The root is black.
     *
     * 3. Null leaves are treated as black.
     *
     * 4. A red node cannot have a red child.
     *
     * 5. Every path from a node to its descendant null leaves
     *    contains the same number of black nodes.
     *
     * The tree uses rotations and recoloring to maintain
     * these properties.
     *
     * You do not need to implement these operations yourself
     * when using TreeSet.
     *
     *
     * ============================================================
     * 7. NATURAL ORDERING
     * ============================================================
     *
     * Natural ordering is the default ordering of a type.
     *
     * Examples:
     *
     * Integer:
     *     Ascending numerical order.
     *
     * String:
     *     Lexicographical order.
     *
     * Double:
     *     Ascending numerical order.
     *
     * Example:
     *
     *     TreeSet<Integer> numbers = new TreeSet<>();
     *
     *     numbers.add(30);
     *     numbers.add(10);
     *     numbers.add(20);
     *
     * Result:
     *
     *     [10, 20, 30]
     *
     * Natural ordering requires elements to be mutually
     * comparable, normally through Comparable.
     *
     *
     * ============================================================
     * 8. WHAT IS COMPARABLE?
     * ============================================================
     *
     * Comparable defines the natural ordering of objects.
     *
     * Package:
     *
     *     java.lang
     *
     * Declaration:
     *
     *     public interface Comparable<T>
     *
     * Main method:
     *
     *     int compareTo(T other)
     *
     * Return values:
     *
     *     Negative -> Current object is smaller.
     *     Zero     -> Both are equivalent in ordering.
     *     Positive -> Current object is greater.
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
     * Now TreeSet<Student> can use the natural ordering
     * defined by compareTo().
     *
     * Example:
     *
     *     Set<Student> students = new TreeSet<>();
     *
     *     students.add(new Student(103));
     *     students.add(new Student(101));
     *     students.add(new Student(102));
     *
     * Elements are sorted by student ID.
     *
     *
     * ============================================================
     * 9. WHAT IS COMPARATOR?
     * ============================================================
     *
     * Comparable defines natural ordering inside the class.
     *
     * But what if we want different sorting rules?
     *
     * Example:
     *
     * A Student class may naturally sort by ID.
     *
     * But sometimes we want to sort by:
     * - Name.
     * - Marks.
     * - Age.
     *
     * Comparator allows us to define external/custom ordering.
     *
     * Package:
     *
     *     java.util
     *
     * Main method:
     *
     *     int compare(T first, T second)
     *
     * Example:
     *
     *     Comparator<Integer> descending =
     *         (a, b) -> Integer.compare(b, a);
     *
     *     TreeSet<Integer> numbers =
     *         new TreeSet<>(descending);
     *
     *     numbers.add(10);
     *     numbers.add(30);
     *     numbers.add(20);
     *
     * Result:
     *
     *     [30, 20, 10]
     *
     *
     * ============================================================
     * 10. COMPARABLE VS COMPARATOR
     * ============================================================
     *
     * Comparable:
     *
     * - Defines natural ordering.
     * - Implemented by the class being compared.
     * - Uses compareTo().
     * - Usually represents the default ordering.
     *
     * Comparator:
     *
     * - Defines custom ordering.
     * - Implemented separately from the compared class.
     * - Uses compare().
     * - Supports multiple sorting strategies.
     *
     * Example:
     *
     * Student implements Comparable<Student>
     *     -> Natural order by ID.
     *
     * Comparator<Student>
     *     -> Custom order by name or marks.
     *
     * A Comparator supplied to TreeSet takes precedence
     * over the elements' natural ordering.
     *
     *
     * ============================================================
     * 11. TREESET AND DUPLICATES
     * ============================================================
     *
     * TreeSet identifies equivalent elements using its
     * comparison mechanism.
     *
     * If:
     *
     *     compare(a, b) == 0
     *
     * TreeSet treats a and b as equivalent for Set membership.
     *
     * This is important because it may differ from equals().
     *
     * Example:
     *
     *     TreeSet<String> names = new TreeSet<>(
     *         String.CASE_INSENSITIVE_ORDER
     *     );
     *
     *     names.add("Java");
     *     names.add("JAVA");
     *
     * Only one is stored because the comparator considers
     * them equivalent.
     *
     * IMPORTANT:
     *
     * For a SortedSet to fully obey the general Set contract,
     * its ordering should be consistent with equals().
     *
     * If compare() returns zero for objects that are not equal,
     * the sorted Set can behave unexpectedly when compared
     * with other Set implementations.
     *
     *
     * ============================================================
     * 12. BASIC TREESET METHODS
     * ============================================================
     *
     * TreeSet supports common Set methods:
     *
     * add(E e)
     *     Adds an element if it is not already present.
     *
     * remove(Object o)
     *     Removes an element.
     *
     * contains(Object o)
     *     Checks whether an element exists.
     *
     * size()
     *     Returns the number of elements.
     *
     * first()
     *     Returns the lowest element.
     *
     * last()
     *     Returns the highest element.
     *
     * Example:
     *
     *     TreeSet<Integer> numbers = new TreeSet<>();
     *
     *     numbers.add(30);
     *     numbers.add(10);
     *     numbers.add(20);
     *
     *     numbers.first(); // 10
     *     numbers.last();  // 30
     *
     *
     * ============================================================
     * 13. SORTEDSET INTERFACE
     * ============================================================
     *
     * TreeSet implements NavigableSet, which extends SortedSet.
     *
     * SortedSet provides sorted-set operations.
     *
     * Important methods:
     *
     * first()
     *     Returns the first (lowest) element.
     *
     * last()
     *     Returns the last (highest) element.
     *
     * headSet(toElement)
     *     Returns elements strictly less than toElement.
     *
     * tailSet(fromElement)
     *     Returns elements greater than or equal to fromElement.
     *
     * subSet(fromElement, toElement)
     *     Returns elements from fromElement inclusive to
     *     toElement exclusive.
     *
     * comparator()
     *     Returns the Comparator, or null for natural ordering.
     *
     *
     * ============================================================
     * 14. NAVIGABLESET INTERFACE
     * ============================================================
     *
     * NavigableSet extends SortedSet.
     *
     * It adds navigation methods that help find nearby elements.
     *
     * Important methods:
     *
     * lower(e)
     *     Greatest element strictly less than e.
     *
     * floor(e)
     *     Greatest element less than or equal to e.
     *
     * ceiling(e)
     *     Smallest element greater than or equal to e.
     *
     * higher(e)
     *     Smallest element strictly greater than e.
     *
     * Example:
     *
     *     NavigableSet<Integer> numbers = new TreeSet<>();
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
     * These methods are useful for finding nearest values
     * without manually iterating through the Set.
     *
     *
     * ============================================================
     * 15. DESCENDING ORDER
     * ============================================================
     *
     * descendingSet() returns a reverse-order view of the Set.
     *
     * Example:
     *
     *     NavigableSet<Integer> numbers = new TreeSet<>();
     *
     *     numbers.add(10);
     *     numbers.add(20);
     *     numbers.add(30);
     *
     *     NavigableSet<Integer> reverse =
     *         numbers.descendingSet();
     *
     * Result:
     *
     *     [30, 20, 10]
     *
     * IMPORTANT:
     *
     * descendingSet() returns a backed view, not an independent
     * copy. Changes through the view are reflected in the
     * original Set, and vice versa.
     *
     *
     * ============================================================
     * 16. RANGE OPERATIONS
     * ============================================================
     *
     * TreeSet supports retrieving ranges of elements.
     *
     * headSet():
     *
     *     numbers.headSet(30);
     *
     * Returns elements strictly less than 30.
     *
     * tailSet():
     *
     *     numbers.tailSet(20);
     *
     * Returns elements greater than or equal to 20.
     *
     * subSet():
     *
     *     numbers.subSet(10, 30);
     *
     * Returns elements from 10 inclusive to 30 exclusive.
     *
     * NavigableSet also provides methods with explicit
     * inclusivity options:
     *
     *     numbers.subSet(10, true, 30, true);
     *
     * Includes both endpoints.
     *
     *     numbers.headSet(30, true);
     *
     * Includes 30.
     *
     *     numbers.tailSet(20, false);
     *
     * Excludes 20.
     *
     * IMPORTANT:
     *
     * These range operations return backed views.
     * They are not independent copies.
     *
     *
     * ============================================================
     * 17. NULL ELEMENT
     * ============================================================
     *
     * TreeSet using natural ordering does not permit null.
     *
     * Example:
     *
     *     TreeSet<Integer> numbers = new TreeSet<>();
     *
     *     numbers.add(null); // NullPointerException
     *
     * Why?
     *
     * Natural ordering needs to compare elements.
     * null cannot be compared using Integer's natural ordering.
     *
     * A custom Comparator can support null if it explicitly
     * handles null values.
     *
     * Example:
     *
     *     Comparator<String> comparator =
     *         Comparator.nullsFirst(Comparator.naturalOrder());
     *
     *     TreeSet<String> names = new TreeSet<>(comparator);
     *
     *     names.add(null);
     *     names.add("Java");
     *
     * This works because the comparator defines how null
     * should be ordered.
     *
     *
     * ============================================================
     * 18. TIME COMPLEXITY
     * ============================================================
     *
     * TreeSet uses a balanced tree in standard OpenJDK.
     *
     * Operation             Time complexity
     *
     * add()                 O(log n)
     * remove()              O(log n)
     * contains()            O(log n)
     * first()               O(log n) worst case in tree traversal
     * last()                O(log n) worst case in tree traversal
     * lower()               O(log n)
     * floor()               O(log n)
     * ceiling()             O(log n)
     * higher()              O(log n)
     *
     * Iterating through all elements takes O(n).
     *
     * The balanced tree keeps the height logarithmic in the
     * number of elements.
     *
     *
     * ============================================================
     * 19. TREESET IS NOT THREAD-SAFE
     * ============================================================
     *
     * TreeSet is not synchronized by default.
     *
     * For synchronized access:
     *
     *     SortedSet<Integer> numbers =
     *         Collections.synchronizedSortedSet(new TreeSet<>());
     *
     * For concurrent sorted storage, consider:
     *
     *     ConcurrentSkipListSet
     *
     * Choose based on the required concurrency behavior.
     *
     *
     * ============================================================
     * 20. FAIL-FAST ITERATORS
     * ============================================================
     *
     * TreeSet iterators are fail-fast on a best-effort basis.
     *
     * Structural modification outside the iterator during
     * iteration may cause ConcurrentModificationException.
     *
     * Use Iterator.remove() when removing during iteration.
     *
     * Fail-fast behavior is not a thread-safety guarantee.
     *
     *
     * ============================================================
     * 21. TREESET VS HASHSET VS LINKEDHASHSET
     * ============================================================
     *
     * HashSet:
     *     Unique elements.
     *     No guaranteed iteration order.
     *     Expected O(1) basic operations.
     *
     * LinkedHashSet:
     *     Unique elements.
     *     Insertion order.
     *     Expected O(1) basic operations.
     *
     * TreeSet:
     *     Unique elements.
     *     Sorted order.
     *     O(log n) basic operations.
     *
     * Choose:
     *
     * - HashSet when ordering is unnecessary.
     * - LinkedHashSet when insertion order matters.
     * - TreeSet when sorted order and navigation matter.
     *
     *
     * ============================================================
     * 22. COMMON USE CASES
     * ============================================================
     *
     * 1. Maintaining sorted unique scores.
     *
     * 2. Maintaining unique dates in chronological order.
     *
     * 3. Finding the nearest available value.
     *
     * 4. Managing sorted unique product prices.
     *
     * 5. Performing range queries.
     *
     * 6. Maintaining rankings or ordered identifiers.
     *
     *
     * ============================================================
     * 23. COMMON MISTAKES
     * ============================================================
     *
     * 1. Assuming TreeSet preserves insertion order.
     *    It maintains sorted order.
     *
     * 2. Assuming TreeSet uses hashCode() for ordering.
     *    It uses comparison.
     *
     * 3. Forgetting to implement Comparable for custom elements
     *    when using natural ordering.
     *
     * 4. Providing a Comparator that is inconsistent with equals()
     *    without understanding the consequences.
     *
     * 5. Assuming null is allowed with natural ordering.
     *
     * 6. Assuming subSet(), headSet(), and tailSet() return copies.
     *    They return backed views.
     *
     * 7. Assuming TreeSet is thread-safe.
     *
     *
     * ============================================================
     * 24. INTERVIEW QUICK REVISION
     * ============================================================
     *
     * Q1. What is TreeSet?
     * A sorted implementation of the Set interface.
     *
     * Q2. Which interface does TreeSet directly implement?
     * NavigableSet.
     *
     * Q3. What is its internal implementation in OpenJDK?
     * TreeMap backed by a Red-Black Tree.
     *
     * Q4. Does TreeSet allow duplicates?
     * No. Comparison-equivalent elements are treated as duplicates.
     *
     * Q5. Does TreeSet allow null?
     * Not with natural ordering. A custom Comparator can
     * explicitly support null.
     *
     * Q6. What is Comparable?
     * An interface used to define natural ordering.
     *
     * Q7. What is Comparator?
     * An interface used to define custom ordering.
     *
     * Q8. Difference between Comparable and Comparator?
     * Comparable defines natural ordering; Comparator defines
     * external/custom ordering.
     *
     * Q9. What is the time complexity of add()?
     * O(log n).
     *
     * Q10. What does floor() return?
     * Greatest element less than or equal to the given element.
     *
     * Q11. What does ceiling() return?
     * Smallest element greater than or equal to the given element.
     *
     * Q12. What does lower() return?
     * Greatest element strictly less than the given element.
     *
     * Q13. What does higher() return?
     * Smallest element strictly greater than the given element.
     *
     * Q14. Is TreeSet thread-safe?
     * No.
     *
     *
     * ============================================================
     * 25. FINAL UNDERSTANDING
     * ============================================================
     *
     * Remember:
     *
     * HashSet:
     *     Uniqueness + no guaranteed order.
     *
     * LinkedHashSet:
     *     Uniqueness + insertion order.
     *
     * TreeSet:
     *     Uniqueness + sorted order.
     *
     * TreeSet is the right choice when you need unique elements
     * maintained in sorted order, together with useful navigation
     * and range-query operations.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about TreeSet , Happy Learning ..!");
    }
}
