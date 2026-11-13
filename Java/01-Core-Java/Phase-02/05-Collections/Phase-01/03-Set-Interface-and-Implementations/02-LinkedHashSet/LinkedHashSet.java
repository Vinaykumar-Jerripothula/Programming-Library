
public class LinkedHashSet {

    /*
     * ============================================================
     *                  JAVA LINKEDHASHSET
     * ============================================================
     *
     * 1. WHAT IS LINKEDHASHSET?
     *
     * Imagine you are maintaining a list of recently visited
     * websites.
     *
     * You want:
     * - No duplicate websites.
     * - Websites to appear in the order they were added.
     *
     * HashSet provides uniqueness, but it does not guarantee
     * insertion order.
     *
     * LinkedHashSet solves this problem.
     *
     * LinkedHashSet is a hash-table-based implementation of
     * the Set interface that maintains insertion order.
     *
     * Declaration:
     *
     *     public class LinkedHashSet<E>
     *         extends HashSet<E>
     *         implements Set<E>, Cloneable, Serializable
     *
     * IMPORTANT:
     *
     * - Does not allow duplicate elements.
     * - Maintains insertion order.
     * - Allows one null element.
     * - Is not synchronized by default.
     * - Provides expected O(1) basic operations with good
     *   hash distribution.
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
     * Import:
     *
     *     import java.util.LinkedHashSet;
     *     import java.util.Set;
     *
     *
     * ============================================================
     * 3. CREATING LINKEDHASHSET
     * ============================================================
     *
     * Empty LinkedHashSet:
     *
     *     Set<Integer> numbers = new LinkedHashSet<>();
     *
     * With initial capacity:
     *
     *     Set<Integer> numbers = new LinkedHashSet<>(32);
     *
     * With initial capacity and load factor:
     *
     *     Set<Integer> numbers = new LinkedHashSet<>(32, 0.75f);
     *
     * From another collection:
     *
     *     List<Integer> values = Arrays.asList(10, 20, 10, 30);
     *
     *     Set<Integer> numbers = new LinkedHashSet<>(values);
     *
     * Result:
     *
     *     [10, 20, 30]
     *
     * Duplicates are eliminated while insertion order is retained.
     *
     *
     * ============================================================
     * 4. BASIC OPERATIONS
     * ============================================================
     *
     *     Set<String> names = new LinkedHashSet<>();
     *
     * ADD:
     *
     *     names.add("Vinay");
     *     names.add("Ravi");
     *     names.add("Kiran");
     *
     * ADD DUPLICATE:
     *
     *     names.add("Vinay");
     *
     * The duplicate is ignored.
     *
     * ITERATE:
     *
     *     for (String name : names) {
     *         System.out.println(name);
     *     }
     *
     * Output:
     *
     *     Vinay
     *     Ravi
     *     Kiran
     *
     * REMOVE:
     *
     *     names.remove("Ravi");
     *
     * SEARCH:
     *
     *     names.contains("Vinay");
     *
     * SIZE:
     *
     *     names.size();
     *
     * CLEAR:
     *
     *     names.clear();
     *
     * LinkedHashSet inherits its general Set operations.
     *
     *
     * ============================================================
     * 5. HOW DOES LINKEDHASHSET WORK INTERNALLY?
     * ============================================================
     *
     * LinkedHashSet extends HashSet.
     *
     * In standard OpenJDK implementations, it uses the
     * HashMap infrastructure with linked entries.
     *
     * Conceptually:
     *
     *     LinkedHashSet
     *           |
     *           v
     *     Hash-table structure
     *           |
     *           v
     *     Linked entries
     *
     * Each entry participates in two structures:
     *
     * 1. Hash table:
     *    Helps locate elements efficiently.
     *
     * 2. Doubly linked list:
     *    Maintains insertion order.
     *
     * This combination provides:
     *
     * - Fast hash-based lookup.
     * - Predictable iteration order.
     *
     * IMPORTANT:
     *
     * These are standard OpenJDK implementation details.
     * The public API guarantees insertion-order iteration,
     * not a particular internal representation.
     *
     *
     * ============================================================
     * 6. WHY DOES IT MAINTAIN INSERTION ORDER?
     * ============================================================
     *
     * Consider:
     *
     *     Set<Integer> numbers = new LinkedHashSet<>();
     *
     *     numbers.add(30);
     *     numbers.add(10);
     *     numbers.add(20);
     *     numbers.add(40);
     *
     * Iteration order:
     *
     *     30, 10, 20, 40
     *
     * The order is the order in which the elements were
     * successfully inserted.
     *
     * Adding an existing element does not move it to the end.
     *
     * Example:
     *
     *     numbers.add(30);
     *
     * The iteration order remains:
     *
     *     30, 10, 20, 40
     *
     * Removing and adding an element again:
     *
     *     numbers.remove(10);
     *     numbers.add(10);
     *
     * New order:
     *
     *     30, 20, 40, 10
     *
     * The re-added element appears at the end.
     *
     *
     * ============================================================
     * 7. HASHING AND EQUALITY
     * ============================================================
     *
     * LinkedHashSet uses hashing to identify elements.
     *
     * Its duplicate detection follows the hash-based Set
     * behavior.
     *
     * For custom objects:
     *
     * - hashCode() helps identify the bucket.
     * - equals() determines logical equality among candidates.
     *
     * Example:
     *
     *     class Student {
     *         int id;
     *
     *         Student(int id) {
     *             this.id = id;
     *         }
     *
     *         @Override
     *         public boolean equals(Object obj) {
     *             if (this == obj) return true;
     *             if (!(obj instanceof Student)) return false;
     *
     *             Student other = (Student) obj;
     *             return id == other.id;
     *         }
     *
     *         @Override
     *         public int hashCode() {
     *             return Integer.hashCode(id);
     *         }
     *     }
     *
     * Two Student objects with the same ID are treated as
     * duplicates when these methods are implemented correctly.
     *
     *
     * ============================================================
     * 8. NULL ELEMENT
     * ============================================================
     *
     * LinkedHashSet allows one null element.
     *
     * Example:
     *
     *     Set<String> names = new LinkedHashSet<>();
     *
     *     names.add("Java");
     *     names.add(null);
     *     names.add("Python");
     *     names.add(null);
     *
     * Only one null is stored.
     *
     * Iteration order:
     *
     *     Java, null, Python
     *
     * The null appears at the position of its first successful
     * insertion.
     *
     *
     * ============================================================
     * 9. TIME COMPLEXITY
     * ============================================================
     *
     * Operation             Expected time
     *
     * add()                 O(1)
     * remove()              O(1)
     * contains()            O(1)
     * size()                O(1)
     *
     * These are expected performance characteristics when
     * hash values are distributed properly.
     *
     * Iterating through all elements takes O(n) time.
     *
     * LinkedHashSet generally needs more memory than HashSet
     * because it maintains additional links for ordering.
     *
     *
     * ============================================================
     * 10. INITIAL CAPACITY AND LOAD FACTOR
     * ============================================================
     *
     * LinkedHashSet provides constructors similar to HashSet.
     *
     *     new LinkedHashSet<>();
     *
     *     new LinkedHashSet<>(32);
     *
     *     new LinkedHashSet<>(32, 0.75f);
     *
     * Default load factor:
     *
     *     0.75
     *
     * Initial capacity and load factor influence the underlying
     * hash table's allocation and resizing behavior.
     *
     * A suitable initial capacity can reduce repeated resizing
     * when the approximate number of elements is known.
     *
     *
     * ============================================================
     * 11. LINKEDHASHSET VS HASHSET
     * ============================================================
     *
     * HashSet:
     *
     * - Does not guarantee iteration order.
     * - Uses hashing.
     * - Generally requires less memory.
     *
     * LinkedHashSet:
     *
     * - Maintains insertion order.
     * - Uses hashing plus ordering links.
     * - Generally requires more memory.
     *
     * Both:
     *
     * - Reject duplicates.
     * - Allow one null.
     * - Are not synchronized by default.
     * - Offer expected O(1) basic operations with good
     *   hash distribution.
     *
     * Choose HashSet when order does not matter.
     * Choose LinkedHashSet when insertion order matters.
     *
     *
     * ============================================================
     * 12. LINKEDHASHSET VS TREESET
     * ============================================================
     *
     * LinkedHashSet:
     * - Maintains insertion order.
     * - Hash-based.
     * - Expected O(1) basic operations.
     *
     * TreeSet:
     * - Maintains sorted order.
     * - Tree-based.
     * - O(log n) basic operations.
     *
     * Example:
     *
     *     LinkedHashSet: 30, 10, 20
     *     TreeSet:       10, 20, 30
     *
     * Use LinkedHashSet when insertion order matters.
     * Use TreeSet when sorted order matters.
     *
     *
     * ============================================================
     * 13. LINKEDHASHSET AND THREAD SAFETY
     * ============================================================
     *
     * LinkedHashSet is not thread-safe by default.
     *
     * For synchronized access:
     *
     *     Set<Integer> numbers =
     *         Collections.synchronizedSet(new LinkedHashSet<>());
     *
     * During iteration, synchronize on the returned Set:
     *
     *     synchronized (numbers) {
     *         for (Integer number : numbers) {
     *             System.out.println(number);
     *         }
     *     }
     *
     * The synchronized wrapper protects access through that
     * wrapper. Avoid unsynchronized access through another
     * reference to the backing Set.
     *
     *
     * ============================================================
     * 14. FAIL-FAST ITERATORS
     * ============================================================
     *
     * LinkedHashSet iterators are fail-fast on a best-effort basis.
     *
     * Structural modification outside the iterator during
     * iteration may cause ConcurrentModificationException.
     *
     * Use Iterator.remove() for safe removal during iteration:
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
     * Fail-fast behavior is not a synchronization mechanism
     * and should not be relied upon for correctness.
     *
     *
     * ============================================================
     * 15. INSERTION ORDER VS ACCESS ORDER
     * ============================================================
     *
     * LinkedHashSet maintains insertion order.
     *
     * It does not move an element when that element is accessed.
     *
     * Example:
     *
     *     Set<Integer> numbers = new LinkedHashSet<>();
     *
     *     numbers.add(10);
     *     numbers.add(20);
     *     numbers.add(30);
     *
     *     numbers.contains(10);
     *
     * The order remains:
     *
     *     10, 20, 30
     *
     * LinkedHashMap, unlike LinkedHashSet, supports insertion
     * order and can also be configured for access order.
     *
     * Do not confuse LinkedHashSet with an access-order LRU cache.
     *
     *
     * ============================================================
     * 16. COMMON USE CASES
     * ============================================================
     *
     * 1. Removing duplicates while preserving original order.
     *
     * 2. Maintaining unique user IDs in insertion order.
     *
     * 3. Keeping unique search history entries.
     *
     * 4. Preserving the order of unique configuration values.
     *
     * 5. Processing unique items in the order they first appeared.
     *
     * Example:
     *
     *     List<String> values =
     *         Arrays.asList("A", "B", "A", "C", "B");
     *
     *     Set<String> unique = new LinkedHashSet<>(values);
     *
     * Result:
     *
     *     [A, B, C]
     *
     *
     * ============================================================
     * 17. COMMON MISTAKES
     * ============================================================
     *
     * 1. Assuming LinkedHashSet sorts elements.
     *    It preserves insertion order, not sorted order.
     *
     * 2. Assuming adding a duplicate moves it to the end.
     *    It does not.
     *
     * 3. Assuming LinkedHashSet is thread-safe.
     *    It is not.
     *
     * 4. Forgetting equals() and hashCode() for custom objects.
     *
     * 5. Assuming it has index-based access.
     *    It does not.
     *
     * 6. Confusing insertion order with access order.
     *
     *
     * ============================================================
     * 18. INTERVIEW QUICK REVISION
     * ============================================================
     *
     * Q1. What is LinkedHashSet?
     * A hash-based Set implementation that maintains insertion
     * order.
     *
     * Q2. Does LinkedHashSet allow duplicates?
     * No.
     *
     * Q3. Does LinkedHashSet allow null?
     * Yes, one null element.
     *
     * Q4. How does LinkedHashSet maintain insertion order?
     * Standard OpenJDK implementations maintain linked ordering
     * information between entries.
     *
     * Q5. Is LinkedHashSet thread-safe?
     * No.
     *
     * Q6. Does adding a duplicate change its position?
     * No.
     *
     * Q7. What is the expected time complexity of add()?
     * O(1), assuming good hash distribution.
     *
     * Q8. Difference between HashSet and LinkedHashSet?
     * LinkedHashSet guarantees insertion-order iteration.
     *
     * Q9. Difference between LinkedHashSet and TreeSet?
     * LinkedHashSet preserves insertion order; TreeSet maintains
     * sorted order.
     *
     * Q10. Can LinkedHashSet store custom objects?
     * Yes. Implement equals() and hashCode() correctly when
     * logical equality is required.
     *
     *
     * ============================================================
     * 19. FINAL UNDERSTANDING
     * ============================================================
     *
     * Remember:
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
     * LinkedHashSet combines efficient hash-based membership
     * with predictable insertion-order iteration.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about LinkedHashSet , Happy Learning ..!");
    }
}
