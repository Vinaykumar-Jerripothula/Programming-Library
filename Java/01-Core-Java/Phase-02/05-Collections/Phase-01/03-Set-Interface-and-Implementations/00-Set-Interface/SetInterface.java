
public class SetInterface {

    /*
     * ============================================================
     *                  JAVA SET INTERFACE
     * ============================================================
     *
     * 1. WHAT IS A SET?
     *
     * Imagine a collection of student roll numbers:
     *
     *     101, 102, 103, 101, 104
     *
     * If we store these values in a List, the duplicate 101
     * can appear more than once.
     *
     * But what if we want every roll number to be unique?
     *
     * That is exactly what a Set is designed for.
     *
     * A Set is a collection that does not allow duplicate
     * elements.
     *
     * Example:
     *
     *     Set<Integer> numbers = new HashSet<>();
     *
     *     numbers.add(10);
     *     numbers.add(20);
     *     numbers.add(10);
     *
     *     System.out.println(numbers);
     *
     * The value 10 is stored only once.
     *
     * IMPORTANT:
     * - A Set does not provide index-based access.
     * - A Set does not guarantee a particular iteration order.
     * - Specific implementations may provide ordering.
     * - A Set may allow one null element, or may reject null.
     *
     *
     * ============================================================
     * 2. SET INTERFACE: PACKAGE AND DECLARATION
     * ============================================================
     *
     * Package:
     *
     *     java.util
     *
     * Declaration:
     *
     *     public interface Set<E> extends Collection<E>
     *
     * Set is an interface, not a concrete class.
     *
     * E represents the element type.
     *
     * Examples:
     *
     *     Set<String> names;
     *     Set<Integer> numbers;
     *     Set<Double> prices;
     *
     * Since Set extends Collection, it inherits the common
     * collection operations such as add(), remove(), size(),
     * contains(), and clear().
     *
     *
     * ============================================================
     * 3. SET INTERFACE HIERARCHY
     * ============================================================
     *
     *                    Iterable<E>
     *                         |
     *                    Collection<E>
     *                         |
     *                       Set<E>
     *                         |
     *          --------------------------------
     *          |              |               |
     *       HashSet       SortedSet       Other Set types
     *          |              |
     *    LinkedHashSet   NavigableSet
     *                         |
     *                      TreeSet
     *
     * Important implementations:
     *
     * 1. HashSet
     *    - Uses hashing.
     *    - Does not guarantee iteration order.
     *    - Allows one null element.
     *
     * 2. LinkedHashSet
     *    - Maintains insertion order.
     *    - Allows one null element.
     *
     * 3. TreeSet
     *    - Maintains sorted order.
     *    - Uses natural ordering or a Comparator.
     *    - Natural ordering does not support null.
     *
     * 4. EnumSet
     *    - Specialized Set for enum values.
     *    - Very efficient for enum elements.
     *
     * 5. CopyOnWriteArraySet
     *    - Thread-safe Set.
     *    - Suitable for read-heavy situations.
     *
     * 6. ConcurrentSkipListSet
     *    - Thread-safe, sorted Set.
     *    - Supports concurrent operations.
     *
     *
     * ============================================================
     * 4. WHY DO WE NEED THE SET INTERFACE?
     * ============================================================
     *
     * Consider an application that stores registered email
     * addresses.
     *
     * We should not register the same email address twice.
     *
     * Using a List:
     *
     *     List<String> emails = new ArrayList<>();
     *
     *     emails.add("a@gmail.com");
     *     emails.add("b@gmail.com");
     *     emails.add("a@gmail.com");
     *
     * Duplicate values are possible.
     *
     * Using a Set:
     *
     *     Set<String> emails = new HashSet<>();
     *
     *     emails.add("a@gmail.com");
     *     emails.add("b@gmail.com");
     *     emails.add("a@gmail.com");
     *
     * The duplicate is not added.
     *
     * Common use cases:
     *
     * - Removing duplicate values.
     * - Storing unique usernames or email addresses.
     * - Maintaining unique product IDs.
     * - Finding common elements between collections.
     * - Finding elements present in one collection but not another.
     * - Checking whether an element has already been processed.
     *
     *
     * ============================================================
     * 5. CREATING SET OBJECTS
     * ============================================================
     *
     * Set is an interface, so we cannot directly instantiate it.
     *
     * INVALID:
     *
     *     Set<Integer> numbers = new Set<>();
     *
     * We must use an implementation class.
     *
     * HashSet:
     *
     *     Set<Integer> numbers = new HashSet<>();
     *
     * LinkedHashSet:
     *
     *     Set<Integer> numbers = new LinkedHashSet<>();
     *
     * TreeSet:
     *
     *     Set<Integer> numbers = new TreeSet<>();
     *
     * Notice the reference type and object type:
     *
     *     Set<Integer> numbers = new HashSet<>();
     *
     * Set is the reference type.
     * HashSet is the actual object type.
     *
     * This is programming to an interface.
     *
     * We can change the implementation later without changing
     * code that depends only on the Set interface.
     *
     *
     * ============================================================
     * 6. DUPLICATE ELEMENTS
     * ============================================================
     *
     * A Set does not allow duplicate elements.
     *
     * Example:
     *
     *     Set<Integer> numbers = new HashSet<>();
     *
     *     System.out.println(numbers.add(10)); // true
     *     System.out.println(numbers.add(20)); // true
     *     System.out.println(numbers.add(10)); // false
     *
     * The add() method returns:
     *
     *     true  -> Element was added.
     *     false -> Element was already present.
     *
     * The Set remains unchanged when a duplicate is added.
     *
     * IMPORTANT:
     *
     * What counts as a duplicate depends on the Set implementation.
     *
     * Hash-based Sets generally use hashCode() and equals().
     *
     * Sorted Sets such as TreeSet use their ordering comparison
     * to determine whether two elements are equivalent for
     * Set membership.
     *
     *
     * ============================================================
     * 7. HOW DOES SET IDENTIFY DUPLICATES?
     * ============================================================
     *
     * HASH-BASED SETS:
     *
     * HashSet and LinkedHashSet use hashing.
     *
     * The general process is:
     *
     * Step 1: Calculate the object's hashCode().
     *
     * Step 2: Use the hash to identify a storage location.
     *
     * Step 3: If necessary, compare objects using equals().
     *
     * Step 4: If an equal element already exists, do not add
     *         another copy.
     *
     * Therefore, for objects stored in a HashSet, correctly
     * implementing equals() and hashCode() is important.
     *
     * TREE-BASED SET:
     *
     * TreeSet uses comparison through:
     *
     * - Comparable
     * - Comparator
     *
     * If comparison returns zero, TreeSet treats the elements
     * as equivalent for its Set membership.
     *
     * This is why comparison consistency with equals() matters.
     *
     *
     * ============================================================
     * 8. IMPORTANT METHODS OF SET
     * ============================================================
     *
     * Set inherits most of its methods from Collection.
     *
     * add(E e)
     *     Adds an element if it is not already present.
     *
     * remove(Object o)
     *     Removes the specified element.
     *
     * contains(Object o)
     *     Checks whether an element exists.
     *
     * size()
     *     Returns the number of elements.
     *
     * isEmpty()
     *     Checks whether the Set is empty.
     *
     * clear()
     *     Removes all elements.
     *
     * iterator()
     *     Returns an iterator for traversing elements.
     *
     * addAll(Collection<? extends E> c)
     *     Adds elements from another collection.
     *
     * removeAll(Collection<?> c)
     *     Removes elements that are also present in c.
     *
     * retainAll(Collection<?> c)
     *     Keeps only elements that are also present in c.
     *
     * containsAll(Collection<?> c)
     *     Checks whether all elements of c are present.
     *
     * toArray()
     *     Converts the Set elements into an array.
     *
     * These methods are not exclusive to Set. They are inherited
     * through the Collection hierarchy.
     *
     *
     * ============================================================
     * 9. WHY DOES SET NOT HAVE INDEX-BASED ACCESS?
     * ============================================================
     *
     * A List supports positional access:
     *
     *     list.get(0);
     *     list.get(1);
     *
     * But Set does not provide get(index).
     *
     * Why?
     *
     * Because the Set abstraction focuses on membership and
     * uniqueness, not element positions.
     *
     * A HashSet does not promise a stable positional order.
     *
     * To access its elements, use iteration:
     *
     *     for (Integer number : numbers) {
     *         System.out.println(number);
     *     }
     *
     * Or use an Iterator:
     *
     *     Iterator<Integer> iterator = numbers.iterator();
     *
     *     while (iterator.hasNext()) {
     *         System.out.println(iterator.next());
     *     }
     *
     *
     * ============================================================
     * 10. ORDER OF ELEMENTS
     * ============================================================
     *
     * The Set interface itself does not guarantee iteration order.
     *
     * The actual order depends on the implementation.
     *
     * HashSet:
     *     No guaranteed iteration order.
     *
     * LinkedHashSet:
     *     Maintains insertion order.
     *
     * TreeSet:
     *     Maintains sorted order.
     *
     * EnumSet:
     *     Iterates in the natural order of enum constants.
     *
     * IMPORTANT:
     *
     * Never depend on HashSet iteration order in your application.
     * Its observed order is not a contractual guarantee.
     *
     *
     * ============================================================
     * 11. SET VS LIST
     * ============================================================
     *
     * List:
     *
     * - Allows duplicate elements.
     * - Supports index-based access.
     * - Maintains positional order.
     * - Examples: ArrayList, LinkedList, Vector.
     *
     * Set:
     *
     * - Does not allow duplicate elements.
     * - Does not provide index-based access.
     * - Ordering depends on its implementation.
     * - Examples: HashSet, LinkedHashSet, TreeSet.
     *
     * Choose List when position and duplicates matter.
     * Choose Set when uniqueness matters.
     *
     *
     * ============================================================
     * 12. SET VS COLLECTION
     * ============================================================
     *
     * Collection is the general root interface for many
     * collection types.
     *
     * Set extends Collection.
     *
     * Collection does not impose a general uniqueness rule.
     *
     * Set adds the uniqueness requirement to the Collection
     * contract.
     *
     * Therefore:
     *
     *     Collection<Integer> values = new ArrayList<>();
     *
     * can contain duplicates.
     *
     *     Set<Integer> values = new HashSet<>();
     *
     * cannot contain duplicate elements.
     *
     *
     * ============================================================
     * 13. EQUALS() AND HASHCODE() CONTRACT
     * ============================================================
     *
     * For HashSet, understanding equals() and hashCode() is
     * essential when storing custom objects.
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
     *             return this.id == other.id;
     *         }
     *
     *         @Override
     *         public int hashCode() {
     *             return Integer.hashCode(id);
     *         }
     *     }
     *
     * Now:
     *
     *     Set<Student> students = new HashSet<>();
     *
     *     students.add(new Student(101));
     *     students.add(new Student(101));
     *
     * Only one logically equal Student is retained.
     *
     * CONTRACT:
     *
     * 1. If two objects are equal according to equals(),
     *    they must have the same hashCode().
     *
     * 2. If two objects have the same hashCode(), they do not
     *    necessarily have to be equal.
     *
     * 3. equals() must follow its general contract:
     *    reflexive, symmetric, transitive, consistent, and
     *    false for comparison with null.
     *
     * 4. Avoid changing fields used by equals() and hashCode()
     *    while an object is stored in a HashSet.
     *
     *    Changing those fields can make the element difficult
     *    to find or remove.
     *
     *
     * ============================================================
     * 14. MATHEMATICAL SET OPERATIONS
     * ============================================================
     *
     * Java Set supports common mathematical operations through
     * inherited Collection methods.
     *
     * Let:
     *
     *     A = {1, 2, 3, 4}
     *     B = {3, 4, 5, 6}
     *
     * UNION:
     *
     *     A ∪ B = {1, 2, 3, 4, 5, 6}
     *
     * Java:
     *
     *     Set<Integer> union = new HashSet<>(A);
     *     union.addAll(B);
     *
     * INTERSECTION:
     *
     *     A ∩ B = {3, 4}
     *
     * Java:
     *
     *     Set<Integer> intersection = new HashSet<>(A);
     *     intersection.retainAll(B);
     *
     * DIFFERENCE:
     *
     *     A - B = {1, 2}
     *
     * Java:
     *
     *     Set<Integer> difference = new HashSet<>(A);
     *     difference.removeAll(B);
     *
     * IMPORTANT:
     *
     * These operations modify the target Set.
     *
     * Creating a copy first helps preserve the original Set.
     *
     *
     * ============================================================
     * 15. NULL ELEMENTS IN SET
     * ============================================================
     *
     * The Set contract permits at most one null element,
     * but individual implementations may reject null.
     *
     * HashSet:
     *     Allows one null.
     *
     * LinkedHashSet:
     *     Allows one null.
     *
     * TreeSet:
     *     Natural ordering does not support null.
     *     A custom Comparator may support null if it explicitly
     *     handles null values.
     *
     * EnumSet:
     *     Does not allow null.
     *
     * CopyOnWriteArraySet:
     *     Allows null.
     *
     * ConcurrentSkipListSet:
     *     Does not allow null.
     *
     * Set.of():
     *     Does not allow null.
     *
     * Never assume every Set implementation accepts null.
     *
     *
     * ============================================================
     * 16. IMMUTABLE SETS
     * ============================================================
     *
     * Java provides factory methods to create unmodifiable Sets.
     *
     * Set.of() was introduced in Java 9.
     *
     * Example:
     *
     *     Set<String> names = Set.of("Vinay", "Ravi", "Kiran");
     *
     * Characteristics:
     *
     * - The returned Set is unmodifiable.
     * - Null elements are not allowed.
     * - Duplicate arguments cause IllegalArgumentException.
     *
     * Example:
     *
     *     Set<Integer> numbers = Set.of(10, 20, 30);
     *
     *     numbers.add(40); // UnsupportedOperationException
     *
     * Set.copyOf() was introduced in Java 10.
     *
     * Example:
     *
     *     Set<String> names = Set.copyOf(existingCollection);
     *
     * It creates an unmodifiable Set containing the elements
     * of the source collection.
     *
     * Duplicate source elements are collapsed.
     * Null elements are rejected.
     *
     * IMPORTANT:
     *
     * Unmodifiable does not necessarily mean deeply immutable.
     * If elements are mutable objects, their internal state
     * may still be changed.
     *
     * Collections.unmodifiableSet(set) is a read-only wrapper,
     * not an independent immutable snapshot. Changes made through
     * another reference to the backing Set can still be visible.
     *
     *
     * ============================================================
     * 17. THREAD SAFETY
     * ============================================================
     *
     * Most common Set implementations such as HashSet,
     * LinkedHashSet, and TreeSet are not thread-safe.
     *
     * If multiple threads modify the same Set concurrently,
     * external synchronization may be required.
     *
     * Options include:
     *
     * 1. Collections.synchronizedSet(set)
     *    Creates a synchronized wrapper.
     *
     * 2. CopyOnWriteArraySet
     *    Useful when reads greatly outnumber writes.
     *
     * 3. ConcurrentSkipListSet
     *    Supports concurrent operations while maintaining
     *    sorted order.
     *
     * IMPORTANT:
     *
     * Thread-safe individual operations do not automatically
     * make a sequence of multiple operations atomic.
     *
     *
     * ============================================================
     * 18. SET EQUALS() AND HASHCODE()
     * ============================================================
     *
     * Two Sets are equal when:
     *
     * - They have the same size.
     * - They contain the same elements.
     *
     * Their iteration order does not matter.
     *
     * Example:
     *
     *     Set<Integer> first = new HashSet<>();
     *     first.add(10);
     *     first.add(20);
     *
     *     Set<Integer> second = new LinkedHashSet<>();
     *     second.add(20);
     *     second.add(10);
     *
     *     System.out.println(first.equals(second)); // true
     *
     * The Set hashCode is defined as the sum of the hash codes
     * of its elements.
     *
     * Therefore, equal Sets have equal hash codes, regardless
     * of iteration order.
     *
     *
     * ============================================================
     * 19. COMMON MISTAKES
     * ============================================================
     *
     * 1. Expecting HashSet to preserve insertion order.
     *
     * 2. Trying to access elements using get(index).
     *
     * 3. Assuming every Set implementation permits null.
     *
     * 4. Forgetting equals() and hashCode() for custom objects
     *    stored in hash-based Sets.
     *
     * 5. Modifying equality-related fields after inserting an
     *    object into a HashSet.
     *
     * 6. Assuming Set.of() creates a mutable Set.
     *
     * 7. Assuming a Comparator-based TreeSet always uses
     *    equals() to decide whether two elements are duplicates.
     *
     * 8. Assuming ordinary HashSet is thread-safe.
     *
     *
     * ============================================================
     * 20. INTERVIEW QUICK REVISION
     * ============================================================
     *
     * Q1. What is Set?
     * A collection that does not allow duplicate elements.
     *
     * Q2. Can we instantiate Set directly?
     * No. It is an interface.
     *
     * Q3. Does Set maintain insertion order?
     * The Set interface does not guarantee it.
     *
     * Q4. Which Set maintains insertion order?
     * LinkedHashSet.
     *
     * Q5. Which Set maintains sorted order?
     * TreeSet.
     *
     * Q6. Does Set support index-based access?
     * No.
     *
     * Q7. Can a Set contain null?
     * Depends on the implementation.
     *
     * Q8. How does HashSet identify duplicates?
     * Using hashCode() and equals().
     *
     * Q9. How does TreeSet identify equivalent elements?
     * Through its comparison mechanism.
     *
     * Q10. Is HashSet thread-safe?
     * No.
     *
     * Q11. What does add() return?
     * true if added; false if already present.
     *
     * Q12. What is Set.of()?
     * A Java 9+ factory method that creates an unmodifiable Set.
     *
     * Q13. What is Set.copyOf()?
     * A Java 10+ factory method that creates an unmodifiable
     * Set from a collection.
     *
     * Q14. Can two different Set implementations be equal?
     * Yes, if they contain the same elements.
     *
     *
     * ============================================================
     * 21. FINAL UNDERSTANDING
     * ============================================================
     *
     * Think of the Collection hierarchy this way:
     *
     * List:
     *     "I care about positions and sequence."
     *
     * Set:
     *     "I care about uniqueness."
     *
     * Queue:
     *     "I care about processing elements."
     *
     * Set is the right abstraction whenever uniqueness is
     * a requirement.
     *
     * The implementation determines additional characteristics:
     *
     * HashSet              -> Hash-based storage
     * LinkedHashSet        -> Insertion order
     * TreeSet              -> Sorted order
     * EnumSet              -> Specialized enum storage
     * CopyOnWriteArraySet  -> Read-heavy concurrent use
     * ConcurrentSkipListSet -> Concurrent sorted storage
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Set Interface , Happy Learning ..!");
    }
}
