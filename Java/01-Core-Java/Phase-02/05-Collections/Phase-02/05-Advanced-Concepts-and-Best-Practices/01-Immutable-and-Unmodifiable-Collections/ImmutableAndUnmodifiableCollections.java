
public class ImmutableAndUnmodifiableCollections {

    /*
     * ============================================================
     * 1. WHY DO WE NEED IMMUTABLE COLLECTIONS?
     * ============================================================
     *
     * Imagine you create a List containing a user's permissions:
     *
     * List<String> permissions = new ArrayList<>();
     * permissions.add("READ");
     * permissions.add("WRITE");
     *
     * You pass this List to another class.
     *
     * That class can modify your original List:
     *
     * permissions.clear();
     *
     * Now your original data has changed unexpectedly.
     *
     * This creates problems such as:
     * - Accidental modifications.
     * - Unexpected behavior between classes.
     * - Difficult debugging.
     * - Thread-safety concerns.
     * - Violation of business rules.
     *
     * One solution is to prevent modification of collections.
     *
     * Java provides multiple approaches to achieve this.
     *
     *
     * ============================================================
     * 2. MUTABLE VS UNMODIFIABLE VS IMMUTABLE
     * ============================================================
     *
     * MUTABLE COLLECTION:
     *
     * A mutable collection allows modifications.
     *
     * Example:
     *
     * List<String> names = new ArrayList<>();
     * names.add("Vinay");
     * names.remove("Vinay");
     *
     * Both operations are allowed.
     *
     *
     * UNMODIFIABLE COLLECTION:
     *
     * An unmodifiable collection does not allow modifications
     * through its reference.
     *
     * Example:
     *
     * List<String> original = new ArrayList<>();
     * original.add("Java");
     *
     * List<String> view =
     *     Collections.unmodifiableList(original);
     *
     * view.add("Python"); // UnsupportedOperationException
     *
     * However, the original List can still be modified:
     *
     * original.add("Python");
     *
     * The change is visible through view.
     *
     * Therefore, an unmodifiable collection is not necessarily
     * immutable.
     *
     *
     * IMMUTABLE COLLECTION:
     *
     * An immutable collection cannot be changed after creation.
     *
     * Its structure and membership remain unchanged.
     *
     * Example:
     *
     * List<String> languages = List.of("Java", "Python");
     *
     * languages.add("C++"); // UnsupportedOperationException
     *
     * languages.set(0, "JavaScript"); // UnsupportedOperationException
     *
     * The collection itself cannot be modified.
     *
     * IMPORTANT:
     * Java's standard collection APIs commonly provide
     * unmodifiable collections, not a universal deep-immutability
     * guarantee.
     *
     * Even List.of() does not make mutable elements immutable.
     *
     *
     * ============================================================
     * 3. Collections.unmodifiableList()
     * ============================================================
     *
     * The Collections utility class provides wrapper methods:
     *
     * Collections.unmodifiableCollection()
     * Collections.unmodifiableList()
     * Collections.unmodifiableSet()
     * Collections.unmodifiableSortedSet()
     * Collections.unmodifiableNavigableSet()
     * Collections.unmodifiableMap()
     * Collections.unmodifiableSortedMap()
     * Collections.unmodifiableNavigableMap()
     *
     * These methods return unmodifiable views of existing
     * collections.
     *
     * Example:
     *
     * List<String> original = new ArrayList<>();
     * original.add("Java");
     * original.add("Python");
     *
     * List<String> readOnly =
     *     Collections.unmodifiableList(original);
     *
     * readOnly.add("C++");
     * // UnsupportedOperationException
     *
     * readOnly.remove("Java");
     * // UnsupportedOperationException
     *
     * readOnly.set(0, "JavaScript");
     * // UnsupportedOperationException
     *
     * readOnly.clear();
     * // UnsupportedOperationException
     *
     *
     * IMPORTANT: THIS IS A VIEW, NOT A COPY.
     *
     * original.add("C");
     *
     * System.out.println(readOnly);
     *
     * Output:
     * [Java, Python, C]
     *
     * The wrapper reflects changes made through the original
     * collection reference.
     *
     * Think of it like a window:
     *
     * - You can see the original collection through the window.
     * - You cannot modify it through the window.
     * - Someone holding the original reference can still modify it.
     *
     *
     * ============================================================
     * 4. Collections.unmodifiableSet()
     * ============================================================
     *
     * Example:
     *
     * Set<String> original = new HashSet<>();
     * original.add("Java");
     *
     * Set<String> readOnly =
     *     Collections.unmodifiableSet(original);
     *
     * readOnly.add("Python");
     * // UnsupportedOperationException
     *
     * original.add("Python");
     *
     * System.out.println(readOnly);
     *
     * Output:
     * [Java, Python]
     *
     * The unmodifiable Set is backed by the original Set.
     *
     *
     * ============================================================
     * 5. Collections.unmodifiableMap()
     * ============================================================
     *
     * Example:
     *
     * Map<Integer, String> original = new HashMap<>();
     *
     * original.put(1, "Java");
     * original.put(2, "Python");
     *
     * Map<Integer, String> readOnly =
     *     Collections.unmodifiableMap(original);
     *
     * readOnly.put(3, "C++");
     * // UnsupportedOperationException
     *
     * readOnly.remove(1);
     * // UnsupportedOperationException
     *
     * readOnly.clear();
     * // UnsupportedOperationException
     *
     * original.put(3, "C++");
     *
     * System.out.println(readOnly);
     *
     * Output:
     * {1=Java, 2=Python, 3=C++}
     *
     * The Map wrapper is also a view.
     *
     *
     * ============================================================
     * 6. List.of() - IMMUTABLE LIST FACTORY
     * ============================================================
     *
     * Introduced in Java 9.
     *
     * List.of() creates an unmodifiable List containing the
     * supplied elements.
     *
     * Examples:
     *
     * List<String> empty = List.of();
     *
     * List<String> names = List.of("Vinay");
     *
     * List<String> languages =
     *     List.of("Java", "Python", "C++");
     *
     * You can also pass multiple elements:
     *
     * List<Integer> numbers = List.of(10, 20, 30, 40);
     *
     * All these Lists are unmodifiable.
     *
     * The following operations throw
     * UnsupportedOperationException:
     *
     * names.add("Kumar");
     * names.remove("Vinay");
     * names.set(0, "Ravi");
     * names.clear();
     *
     *
     * IMPORTANT CHARACTERISTICS:
     *
     * 1. List.of() does not allow null elements.
     *
     * List<String> names = List.of("Vinay", null);
     * // NullPointerException
     *
     * 2. It preserves the supplied element order.
     *
     * 3. Duplicate elements are allowed.
     *
     * List<String> values = List.of("Java", "Java");
     * // Valid
     *
     * 4. The returned List is unmodifiable.
     *
     * 5. It is not a mutable ArrayList.
     *
     * 6. It is safe to share the collection structure between
     *    threads because it cannot be structurally modified.
     *
     *    However, mutable elements still need their own
     *    thread-safety considerations.
     *
     *
     * ============================================================
     * 7. Set.of() - IMMUTABLE SET FACTORY
     * ============================================================
     *
     * Introduced in Java 9.
     *
     * Set.of() creates an unmodifiable Set.
     *
     * Example:
     *
     * Set<String> languages =
     *     Set.of("Java", "Python", "C++");
     *
     * IMPORTANT CHARACTERISTICS:
     *
     * 1. Null elements are not allowed.
     *
     * Set.of("Java", null);
     * // NullPointerException
     *
     * 2. Duplicate elements are not allowed.
     *
     * Set.of("Java", "Java");
     * // IllegalArgumentException
     *
     * 3. The Set is unmodifiable.
     *
     * 4. Iteration order is unspecified.
     *
     * Do not depend on a particular iteration order.
     *
     *
     * ============================================================
     * 8. Map.of() - IMMUTABLE MAP FACTORY
     * ============================================================
     *
     * Introduced in Java 9.
     *
     * Map.of() creates an unmodifiable Map.
     *
     * Example:
     *
     * Map<Integer, String> students =
     *     Map.of(
     *         101, "Vinay",
     *         102, "Ravi",
     *         103, "Kiran"
     *     );
     *
     * IMPORTANT CHARACTERISTICS:
     *
     * 1. Null keys are not allowed.
     *
     * 2. Null values are not allowed.
     *
     * 3. Duplicate keys are not allowed.
     *
     * Map.of(1, "Java", 1, "Python");
     * // IllegalArgumentException
     *
     * 4. The Map is unmodifiable.
     *
     * 5. Iteration order is unspecified.
     *
     * 6. Map.of() supports up to 10 key-value pairs through
     *    its fixed-argument overloads.
     *
     *    For more entries, use Map.ofEntries().
     *
     *
     * ============================================================
     * 9. Map.ofEntries() - CREATING LARGER IMMUTABLE MAPS
     * ============================================================
     *
     * Map.ofEntries() supports any number of supplied entries.
     *
     * Example:
     *
     * Map<Integer, String> students =
     *     Map.ofEntries(
     *         Map.entry(101, "Vinay"),
     *         Map.entry(102, "Ravi"),
     *         Map.entry(103, "Kiran")
     *     );
     *
     * Map.entry() creates an unmodifiable Map.Entry.
     *
     * Null keys and values are not allowed.
     *
     * Duplicate keys cause IllegalArgumentException.
     *
     *
     * ============================================================
     * 10. List.copyOf(), Set.copyOf(), Map.copyOf()
     * ============================================================
     *
     * Introduced in Java 10.
     *
     * These methods create unmodifiable copies of supplied
     * collections or Maps.
     *
     * LIST:
     *
     * List<String> original = new ArrayList<>();
     * original.add("Java");
     * original.add("Python");
     *
     * List<String> copy = List.copyOf(original);
     *
     * original.add("C++");
     *
     * System.out.println(copy);
     *
     * Output:
     * [Java, Python]
     *
     * The copy does not reflect later structural changes
     * to the original List.
     *
     *
     * SET:
     *
     * Set<String> original = new HashSet<>();
     * original.add("Java");
     *
     * Set<String> copy = Set.copyOf(original);
     *
     * The returned Set is unmodifiable.
     *
     *
     * MAP:
     *
     * Map<Integer, String> original = new HashMap<>();
     * original.put(1, "Java");
     *
     * Map<Integer, String> copy = Map.copyOf(original);
     *
     * The returned Map is unmodifiable.
     *
     *
     * IMPORTANT CHARACTERISTICS:
     *
     * - List.copyOf() preserves encounter order.
     * - Set.copyOf() does not guarantee iteration order.
     * - Map.copyOf() does not guarantee iteration order.
     * - Null elements, keys or values are rejected.
     * - The returned collections are unmodifiable.
     *
     * If the source contains duplicate elements when passed
     * to Set.copyOf(), duplicates are collapsed.
     *
     * IMPORTANT:
     * These are shallow copies, not deep copies.
     *
     *
     * ============================================================
     * 11. VIEW VS COPY - VERY IMPORTANT
     * ============================================================
     *
     * A VIEW provides another way to access an existing collection.
     *
     * A COPY creates a separate collection structure.
     *
     * Example:
     *
     * List<String> original = new ArrayList<>();
     * original.add("Java");
     *
     * VIEW:
     *
     * List<String> view =
     *     Collections.unmodifiableList(original);
     *
     * COPY:
     *
     * List<String> copy =
     *     List.copyOf(original);
     *
     * Now modify the original:
     *
     * original.add("Python");
     *
     * System.out.println(view);
     * // [Java, Python]
     *
     * System.out.println(copy);
     * // [Java]
     *
     * WHY?
     *
     * view:
     * - Backed by original.
     * - Reflects original's changes.
     *
     * copy:
     * - Separate collection structure.
     * - Does not reflect later structural changes.
     *
     *
     * ============================================================
     * 12. SHALLOW COPY VS DEEP COPY
     * ============================================================
     *
     * A shallow copy creates a new collection structure but
     * retains references to the same elements.
     *
     * A deep copy creates independent copies of the elements
     * as well, according to the application's copying rules.
     *
     * Example:
     *
     * class Student {
     *     String name;
     *
     *     Student(String name) {
     *         this.name = name;
     *     }
     * }
     *
     * List<Student> original = new ArrayList<>();
     *
     * Student student = new Student("Vinay");
     * original.add(student);
     *
     * List<Student> copy = List.copyOf(original);
     *
     * Both Lists refer to the SAME Student object.
     *
     * student.name = "Ravi";
     *
     * System.out.println(original.get(0).name);
     * // Ravi
     *
     * System.out.println(copy.get(0).name);
     * // Ravi
     *
     * Why?
     *
     * List.copyOf() copied the collection structure,
     * not the Student object.
     *
     * The collection structure is unmodifiable, but its
     * elements may still be mutable.
     *
     *
     * ============================================================
     * 13. HOW TO ACHIEVE DEEP IMMUTABILITY
     * ============================================================
     *
     * Deep immutability requires the collection structure and
     * the objects reachable through it to be immutable or
     * safely encapsulated.
     *
     * One practical approach:
     *
     * - Use immutable element classes.
     * - Avoid exposing mutable internal references.
     * - Make defensive copies where required.
     *
     * Example:
     *
     * final class Student {
     *
     *     private final String name;
     *
     *     Student(String name) {
     *         this.name = name;
     *     }
     *
     *     public String getName() {
     *         return name;
     *     }
     * }
     *
     * Here Student is immutable because:
     *
     * - The class is final.
     * - The field is private and final.
     * - There is no setter.
     * - String itself is immutable.
     *
     * A List<Student> containing such Student objects can be
     * safely exposed through an unmodifiable copy, assuming
     * no other mutable state is exposed.
     *
     *
     * ============================================================
     * 14. DEFENSIVE COPYING
     * ============================================================
     *
     * Defensive copying means creating a copy of mutable data
     * before storing it or returning it.
     *
     * WHY?
     *
     * To prevent external code from modifying internal state.
     *
     * Example:
     *
     * public final class Team {
     *
     *     private final List<String> members;
     *
     *     public Team(List<String> members) {
     *         this.members = List.copyOf(members);
     *     }
     *
     *     public List<String> getMembers() {
     *         return members;
     *     }
     * }
     *
     * The constructor copies the incoming List.
     *
     * The caller cannot modify the internal collection
     * by changing its original List.
     *
     * The getter returns an unmodifiable List.
     *
     * This protects the collection structure.
     *
     * For mutable elements, copying the List alone is not
     * enough to protect their internal state.
     *
     *
     * ============================================================
     * 15. DEFENSIVE COPYING WITH MUTABLE ELEMENTS
     * ============================================================
     *
     * Suppose Team stores mutable Student objects.
     *
     * List.copyOf(students) protects the List structure,
     * but not the Student objects.
     *
     * A caller could still execute:
     *
     * team.getStudents().get(0).setName("Changed");
     *
     * If Student is mutable, internal state changes.
     *
     * Possible solutions:
     *
     * 1. Make Student immutable.
     *
     * 2. Create copies of Student objects when accepting input.
     *
     * 3. Return copies of Student objects when exposing data.
     *
     * 4. Use immutable DTOs or records when appropriate.
     *
     * The copying strategy depends on the object graph.
     *
     *
     * ============================================================
     * 16. COLLECTIONS.EMPTY_LIST, EMPTY_SET, EMPTY_MAP
     * ============================================================
     *
     * Java provides predefined empty unmodifiable collections:
     *
     * Collections.emptyList()
     * Collections.emptySet()
     * Collections.emptyMap()
     *
     * Example:
     *
     * List<String> names = Collections.emptyList();
     *
     * names.add("Vinay");
     * // UnsupportedOperationException
     *
     * These are useful when a method needs to return an empty
     * collection instead of null.
     *
     * Example:
     *
     * public List<String> findNames() {
     *     return Collections.emptyList();
     * }
     *
     * They avoid unnecessary mutable empty collection creation.
     *
     * Modern alternatives:
     *
     * List.of()
     * Set.of()
     * Map.of()
     *
     *
     * ============================================================
     * 17. COLLECTIONS.SINGLETONLIST, SINGLETON
     * ============================================================
     *
     * Java provides:
     *
     * Collections.singletonList(element)
     * Collections.singleton(element)
     * Collections.singletonMap(key, value)
     *
     * These create unmodifiable collections containing
     * exactly one element or one mapping.
     *
     * Example:
     *
     * List<String> names =
     *     Collections.singletonList("Vinay");
     *
     * names.add("Ravi");
     * // UnsupportedOperationException
     *
     * Unlike List.of(), singletonList() permits a null element.
     *
     * Collections.singleton() permits a null element.
     *
     * Collections.singletonMap() permits a null key and
     * a null value.
     *
     * These methods are useful for legacy compatibility and
     * APIs requiring a single-element collection.
     *
     *
     * ============================================================
     * 18. UNMODIFIABLE COLLECTIONS AND THREAD SAFETY
     * ============================================================
     *
     * An unmodifiable collection prevents structural
     * modifications through its exposed reference.
     *
     * This can make sharing collection structure between
     * threads easier.
     *
     * However:
     *
     * 1. An unmodifiable wrapper around a mutable collection
     *    does not prevent another reference from modifying it.
     *
     * 2. Mutable elements can still be changed.
     *
     * 3. Safe publication and visibility still matter.
     *
     * 4. Immutability of a reference does not automatically
     *    make the referenced object graph thread-safe.
     *
     * For genuinely immutable data, use immutable elements
     * and safely publish the collection.
     *
     *
     * ============================================================
     * 19. COMPARISON TABLE
     * ============================================================
     *
     * Approach:
     *
     * Collections.unmodifiableList(original)
     *
     * - Unmodifiable view.
     * - Backed by original.
     * - Reflects changes through original.
     * - Null behavior depends on original.
     *
     *
     * List.of(...)
     *
     * - Unmodifiable List.
     * - Created from supplied elements.
     * - Does not allow null elements.
     * - Duplicate elements allowed.
     *
     *
     * List.copyOf(original)
     *
     * - Unmodifiable copy of collection structure.
     * - Does not reflect later source changes.
     * - Does not allow null elements.
     * - Shallow copy.
     *
     *
     * Collections.emptyList()
     *
     * - Empty unmodifiable List.
     * - Useful for empty return values.
     *
     *
     * Collections.singletonList(value)
     *
     * - One-element unmodifiable List.
     * - Allows null.
     *
     *
     * ============================================================
     * 20. JAVA VERSION REFERENCE
     * ============================================================
     *
     * Java 1.2:
     * - Collections.unmodifiableList()
     * - Collections.unmodifiableSet()
     * - Collections.unmodifiableMap()
     * - Collections.emptyList()
     * - Collections.singletonList()
     *
     * Java 9:
     * - List.of()
     * - Set.of()
     * - Map.of()
     * - Map.ofEntries()
     * - Map.entry()
     *
     * Java 10:
     * - List.copyOf()
     * - Set.copyOf()
     * - Map.copyOf()
     *
     *
     * ============================================================
     * 21. COMMON MISTAKES
     * ============================================================
     *
     * 1. Assuming unmodifiable means immutable.
     *
     *    An unmodifiable view may reflect changes made through
     *    another reference.
     *
     * 2. Assuming List.copyOf() performs a deep copy.
     *
     *    It copies the collection structure, not mutable elements.
     *
     * 3. Assuming List.of() allows null.
     *
     *    It throws NullPointerException for null elements.
     *
     * 4. Assuming Set.of() silently removes duplicates.
     *
     *    It throws IllegalArgumentException for duplicates.
     *
     * 5. Assuming Map.of() accepts duplicate keys.
     *
     *    It throws IllegalArgumentException.
     *
     * 6. Assuming an unmodifiable collection is automatically
     *    thread-safe in every situation.
     *
     * 7. Returning a mutable internal List directly from a class.
     *
     *    This exposes internal state.
     *
     * 8. Using Collections.unmodifiableList() when an independent
     *    snapshot is required.
     *
     *    Use List.copyOf() when a shallow unmodifiable snapshot
     *    is appropriate.
     *
     *
     * ============================================================
     * 22. WHEN SHOULD YOU USE WHAT?
     * ============================================================
     *
     * Use ArrayList:
     * - When callers need to modify the List.
     *
     * Use Collections.unmodifiableList():
     * - When you want a read-only view of an existing List.
     *
     * Use List.of():
     * - When creating a fixed, unmodifiable List from known values.
     *
     * Use List.copyOf():
     * - When creating an unmodifiable snapshot of an input collection.
     *
     * Use Collections.emptyList():
     * - When returning an empty unmodifiable List.
     *
     * Use Collections.singletonList():
     * - When returning exactly one element in an unmodifiable List.
     *
     *
     * ============================================================
     * 23. INTERVIEW REVISION
     * ============================================================
     *
     * Q1. What is the difference between mutable and immutable
     *     collections?
     *
     * Q2. What is the difference between immutable and unmodifiable?
     *
     * Q3. What does Collections.unmodifiableList() return?
     *
     * Q4. Does an unmodifiable view reflect changes in the original?
     *
     * Q5. What is the difference between List.of() and List.copyOf()?
     *
     * Q6. What happens if List.of() receives null?
     *
     * Q7. Does Set.of() allow duplicate elements?
     *
     * Q8. What happens if Map.of() receives duplicate keys?
     *
     * Q9. What is the difference between a view and a copy?
     *
     * Q10. What is shallow copying?
     *
     * Q11. What is deep copying?
     *
     * Q12. Does List.copyOf() make its elements immutable?
     *
     * Q13. What is defensive copying?
     *
     * Q14. How can a class protect its internal collection?
     *
     * Q15. Are unmodifiable collections automatically thread-safe?
     *
     *
     * FINAL TAKEAWAY:
     *
     * Unmodifiable view:
     * "You cannot modify it through this reference."
     *
     * Immutable collection:
     * "Its collection structure cannot change."
     *
     * Copy:
     * "A separate collection structure."
     *
     * Shallow copy:
     * "New container, same element references."
     *
     * Deep copy:
     * "New container and independently copied elements."
     *
     * Defensive copy:
     * "Protect internal state from external modification."
     *
     * Remember these five distinctions and this topic becomes
     * much easier to understand.
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Immutable and Unmodifiable Collections , Happy Learning ..!");
    }
}
