
public class CollectionsFundamentals {

    /*
     * ============================================================
     *                  JAVA COLLECTIONS FRAMEWORK
     * ============================================================
     *
     * 1. INTRODUCTION TO COLLECTIONS
     *
     * Imagine you are developing a student management system.
     *
     * You need to store:
     * - Student names
     * - Student IDs
     * - Student marks
     *
     * You could use arrays:
     *
     *     String[] students = new String[100];
     *
     * But arrays have limitations:
     * - Fixed size after creation.
     * - Limited built-in operations.
     * - Insertion and deletion require manual shifting.
     * - No standard abstraction for different data structures.
     *
     * Java Collections solve these problems by providing
     * ready-made, reusable data structures.
     *
     * A collection is an object that groups multiple objects
     * together into a single unit.
     *
     * Examples:
     * - List: Stores elements in a sequence.
     * - Set: Stores unique elements.
     * - Queue: Processes elements according to a processing order.
     * - Map: Stores key-value pairs.
     *
     *
     * 2. WHAT IS THE COLLECTIONS FRAMEWORK?
     *
     * The Java Collections Framework (JCF) is a unified
     * architecture for representing and manipulating groups
     * of objects.
     *
     * It provides:
     *
     * 1. Interfaces:
     *    Define common contracts for different data structures.
     *
     * 2. Implementations:
     *    Ready-made classes that implement those interfaces.
     *
     * 3. Algorithms:
     *    Reusable operations such as sorting, searching,
     *    reversing and shuffling.
     *
     * 4. Utility methods:
     *    Helper methods to work with collections.
     *
     * Example:
     *
     *     List<String> names = new ArrayList<>();
     *
     * Here:
     * - List is an interface.
     * - ArrayList is an implementation.
     * - names is a reference variable.
     *
     * The interface defines WHAT operations are available.
     * The implementation defines HOW those operations work.
     *
     *
     * 3. WHY DO WE NEED THE COLLECTIONS FRAMEWORK?
     *
     * Without collections:
     * - We write more data-structure code ourselves.
     * - We repeatedly implement insertion, deletion and searching.
     * - Different developers may implement operations differently.
     * - Changing a data structure may require many code changes.
     *
     * With collections:
     * - Reusable data structures are available.
     * - Standard interfaces provide consistency.
     * - Implementations can be changed with fewer code changes.
     * - Generic types provide compile-time type safety.
     * - Utility algorithms reduce development effort.
     *
     *
     * 4. COLLECTIONS FRAMEWORK ARCHITECTURE
     *
     * The major interfaces are:
     *
     *                         Iterable
     *                            |
     *                        Collection
     *                            |
     *             +--------------+--------------+
     *             |              |              |
     *            List           Set            Queue
     *             |              |              |
     *       ArrayList        HashSet       PriorityQueue
     *       LinkedList       LinkedHashSet ArrayDeque*
     *       Vector           TreeSet       LinkedList
     *       Stack
     *
     * Map is a separate interface:
     *
     *                         Map
     *                          |
     *             +------------+------------+
     *             |            |            |
     *          HashMap     LinkedHashMap  TreeMap
     *             |
     *       ConcurrentHashMap*
     *
     * This is a simplified conceptual diagram, not a complete
     * inheritance tree.
     *
     * IMPORTANT:
     * - Map does NOT extend Collection.
     * - Map stores key-value pairs.
     * - Collection stores individual elements.
     * - ArrayDeque implements Deque, which extends Queue.
     * - LinkedList implements both List and Deque.
     * - ConcurrentHashMap belongs to the concurrent collections.
     *
     * *Implementations are not necessarily direct children of
     * the interfaces shown in this simplified diagram.
     *
     *
     * 5. ITERABLE INTERFACE
     *
     * Iterable is the root interface for objects that can be
     * traversed using the enhanced for-loop.
     *
     * Package:
     *     java.lang
     *
     * Important method:
     *
     *     Iterator<T> iterator();
     *
     * Iterable provides a standard way to obtain an iterator.
     *
     * Example:
     *
     *     List<String> names = List.of("Vinay", "Ravi", "Kiran");
     *
     *     for (String name : names) {
     *         System.out.println(name);
     *     }
     *
     * The enhanced for-loop internally uses an iterator for
     * Iterable objects.
     *
     * Iterable also provides:
     * - forEach()
     * - spliterator()
     *
     * NOTE:
     * Arrays support enhanced for-loops too, but arrays do not
     * implement Iterable.
     *
     *
     * 6. COLLECTION INTERFACE
     *
     * Collection is the root interface of the main collection
     * hierarchy.
     *
     * Package:
     *     java.util
     *
     * It represents a group of individual elements.
     *
     * Important methods:
     *
     * add(element)             -> Adds an element.
     * addAll(collection)       -> Adds all elements.
     * remove(element)          -> Removes an element.
     * removeAll(collection)    -> Removes matching elements.
     * retainAll(collection)    -> Keeps only matching elements.
     * contains(element)        -> Checks whether an element exists.
     * containsAll(collection)  -> Checks whether all exist.
     * size()                   -> Returns the number of elements.
     * isEmpty()                -> Checks whether it is empty.
     * clear()                  -> Removes all elements.
     * iterator()               -> Returns an iterator.
     * toArray()                -> Converts to an array.
     * stream()                 -> Creates a sequential stream.
     * parallelStream()         -> Creates a parallel stream.
     *
     * Example:
     *
     *     Collection<String> names = new ArrayList<>();
     *
     *     names.add("Vinay");
     *     names.add("Ravi");
     *     names.add("Kiran");
     *
     *     System.out.println(names.size());       // 3
     *     System.out.println(names.contains("Ravi")); // true
     *
     * NOTE:
     * Collection is an interface, not a concrete class.
     * You cannot instantiate it directly.
     *
     *
     * 7. COLLECTION VS COLLECTIONS
     *
     * These two names are often confused.
     *
     * Collection:
     * - An interface.
     * - Represents a group of objects.
     * - Parent interface of List, Set and Queue.
     *
     * Collections:
     * - A utility class.
     * - Provides static methods to operate on collections.
     * - Examples: sort(), reverse(), shuffle(), min(), max().
     *
     * Package for both:
     *     java.util
     *
     * Example:
     *
     *     List<Integer> numbers =
     *         new ArrayList<>(List.of(30, 10, 20));
     *
     *     Collections.sort(numbers);
     *
     *     System.out.println(numbers); // [10, 20, 30]
     *
     *
     * 8. LIST INTERFACE
     *
     * A List represents an ordered collection of elements.
     *
     * Think of a numbered queue of items:
     *
     * Index:    0       1       2
     *          Vinay   Ravi    Kiran
     *
     * Important characteristics:
     * - Maintains positional order.
     * - Allows duplicate elements.
     * - Supports index-based access.
     * - Usually allows null elements, depending on implementation.
     *
     * Common implementations:
     * - ArrayList
     * - LinkedList
     * - Vector
     * - Stack
     *
     * Example:
     *
     *     List<String> names = new ArrayList<>();
     *
     *     names.add("Vinay");
     *     names.add("Ravi");
     *     names.add("Vinay");
     *
     *     System.out.println(names);
     *
     *     // [Vinay, Ravi, Vinay]
     *
     * Duplicate values are allowed.
     *
     *
     * 9. SET INTERFACE
     *
     * A Set represents a collection that does not allow
     * duplicate elements.
     *
     * Think of a college admission system where each student
     * ID must be unique.
     *
     * Important characteristics:
     * - Does not permit duplicate elements.
     * - Does not provide index-based access.
     * - Ordering depends on the implementation.
     *
     * Common implementations:
     * - HashSet
     * - LinkedHashSet
     * - TreeSet
     *
     * Example:
     *
     *     Set<Integer> ids = new HashSet<>();
     *
     *     ids.add(101);
     *     ids.add(102);
     *     ids.add(101);
     *
     *     System.out.println(ids.size()); // 2
     *
     * The duplicate 101 is not added again.
     *
     * IMPORTANT:
     * - HashSet does not guarantee iteration order.
     * - LinkedHashSet maintains insertion order.
     * - TreeSet maintains sorted order.
     *
     *
     * 10. QUEUE INTERFACE
     *
     * A Queue is designed to hold elements before processing.
     *
     * Think of a ticket counter:
     *
     * First person enters -> First person gets served.
     *
     * This is called FIFO:
     *     First In, First Out.
     *
     * Important operations:
     *
     * add(element)       -> Inserts; may throw an exception.
     * offer(element)     -> Inserts; returns false if it fails.
     * remove()           -> Removes the head; throws if empty.
     * poll()             -> Removes the head; returns null if empty.
     * element()          -> Retrieves the head; throws if empty.
     * peek()             -> Retrieves the head; returns null if empty.
     *
     * Common implementations:
     * - LinkedList
     * - PriorityQueue
     * - ArrayDeque
     *
     * IMPORTANT:
     * Queue does not always mean FIFO.
     * PriorityQueue processes elements according to priority.
     *
     *
     * 11. DEQUE INTERFACE
     *
     * Deque means Double-Ended Queue.
     *
     * It allows insertion and removal from both ends.
     *
     * Visualize:
     *
     * Front  <---- [ A | B | C ] ---->  Rear
     *
     * You can:
     * - Insert at the front.
     * - Insert at the rear.
     * - Remove from the front.
     * - Remove from the rear.
     *
     * Important methods:
     *
     * addFirst()
     * addLast()
     * offerFirst()
     * offerLast()
     * removeFirst()
     * removeLast()
     * pollFirst()
     * pollLast()
     * getFirst()
     * getLast()
     * peekFirst()
     * peekLast()
     *
     * Common implementations:
     * - ArrayDeque
     * - LinkedList
     *
     * A Deque can be used as:
     * - A queue (FIFO).
     * - A stack (LIFO).
     *
     *
     * 12. MAP INTERFACE
     *
     * A Map stores data in key-value pairs.
     *
     * Think of a dictionary:
     *
     * Word       Meaning
     * ------------------------
     * Java       Programming language
     * Python     Programming language
     * SQL        Query language
     *
     * Here:
     * - Word is the key.
     * - Meaning is the value.
     *
     * Important characteristics:
     * - Each key is unique.
     * - Values can be duplicated.
     * - A key maps to at most one value at a time.
     * - Ordering depends on the implementation.
     *
     * Common implementations:
     * - HashMap
     * - LinkedHashMap
     * - TreeMap
     * - Hashtable
     * - ConcurrentHashMap
     *
     * Example:
     *
     *     Map<Integer, String> students = new HashMap<>();
     *
     *     students.put(101, "Vinay");
     *     students.put(102, "Ravi");
     *     students.put(101, "Kiran");
     *
     *     System.out.println(students);
     *
     * The second put() for key 101 replaces its previous value.
     *
     * IMPORTANT:
     * Map does not extend Collection.
     *
     * However, Map provides collection views:
     * - keySet()
     * - values()
     * - entrySet()
     *
     * These allow keys, values and entries to be traversed.
     *
     *
     * 13. LIST VS SET VS QUEUE VS MAP
     *
     * LIST:
     * - Ordered sequence.
     * - Duplicates allowed.
     * - Index-based access.
     * - Example: Student attendance list.
     *
     * SET:
     * - Unique elements.
     * - No index-based access.
     * - Ordering depends on implementation.
     * - Example: Unique email IDs.
     *
     * QUEUE:
     * - Elements waiting for processing.
     * - Processing order depends on implementation.
     * - Example: Job processing system.
     *
     * MAP:
     * - Key-value pairs.
     * - Keys are unique.
     * - Values may repeat.
     * - Example: Employee ID mapped to employee details.
     *
     *
     * 14. GENERICS IN COLLECTIONS
     *
     * Generics allow us to specify the type of elements
     * a collection can store.
     *
     * Without generics:
     *
     *     List names = new ArrayList();
     *     names.add("Vinay");
     *     names.add(100);
     *
     *     String name = (String) names.get(0);
     *
     * Problems:
     * - Different types can accidentally be mixed.
     * - Explicit casting is required.
     * - Runtime ClassCastException may occur.
     *
     * With generics:
     *
     *     List<String> names = new ArrayList<>();
     *
     *     names.add("Vinay");
     *     names.add("Ravi");
     *
     *     String name = names.get(0);
     *
     * Benefits:
     * - Compile-time type safety.
     * - No manual casting for normal retrieval.
     * - Clearer code.
     *
     * Collections generally store objects, not primitive values.
     *
     * Use wrapper classes:
     *
     *     List<Integer> numbers = new ArrayList<>();
     *     List<Double> prices = new ArrayList<>();
     *     List<Character> letters = new ArrayList<>();
     *
     * Autoboxing:
     *     int -> Integer
     *
     * Unboxing:
     *     Integer -> int
     *
     * NOTE:
     * Generics use reference types. List<int> is invalid.
     *
     *
     * 15. COLLECTIONS AND ARRAYS
     *
     * ARRAYS:
     * - Fixed length after creation.
     * - Can store primitives and references.
     * - Direct index-based access.
     * - Built-in length field.
     *
     * COLLECTIONS:
     * - Many implementations grow or shrink dynamically.
     * - Store objects (including wrapper types).
     * - Provide standard methods.
     * - Offer different data structures and behaviors.
     *
     * Example:
     *
     *     int[] numbers = new int[5];
     *     numbers[0] = 10;
     *
     *     List<Integer> values = new ArrayList<>();
     *     values.add(10);
     *     values.add(20);
     *
     * IMPORTANT:
     * Some collections have fixed-size or unmodifiable behavior.
     * Do not assume every List is dynamically resizable.
     *
     *
     * 16. MUTABLE, FIXED-SIZE AND UNMODIFIABLE COLLECTIONS
     *
     * Mutable collection:
     * - Elements can generally be added, removed or replaced.
     *
     *     List<String> names = new ArrayList<>();
     *     names.add("Vinay");
     *
     * Fixed-size list:
     * - Size cannot change.
     * - Existing elements can be replaced.
     *
     *     List<String> names =
     *         Arrays.asList("Vinay", "Ravi");
     *
     *     names.set(0, "Kiran"); // Allowed
     *     names.add("Arun");    // UnsupportedOperationException
     *
     * Unmodifiable list:
     * - Modification operations are not allowed.
     *
     *     List<String> names =
     *         List.of("Vinay", "Ravi");
     *
     *     names.add("Kiran"); // UnsupportedOperationException
     *
     * IMPORTANT:
     * Fixed-size and unmodifiable are not the same.
     * List.of() also rejects null elements.
     *
     *
     * 17. NULL HANDLING
     *
     * Null support depends on the collection implementation.
     *
     * Examples:
     * - ArrayList permits null elements.
     * - HashSet permits one null element.
     * - HashMap permits one null key and multiple null values.
     * - TreeSet generally does not support null with natural ordering.
     * - ConcurrentHashMap does not permit null keys or values.
     * - List.of() does not permit null elements.
     *
     * Never assume every collection accepts null.
     *
     *
     * 18. ORDERING IN COLLECTIONS
     *
     * Three important concepts:
     *
     * INSERTION ORDER:
     * Elements are traversed in insertion order.
     *
     * SORTED ORDER:
     * Elements are traversed according to a sorting rule.
     *
     * NO ORDER GUARANTEE:
     * Iteration order is not guaranteed by the implementation.
     *
     * Examples:
     *
     * ArrayList       -> Maintains positional order.
     * LinkedHashSet   -> Maintains insertion order.
     * TreeSet         -> Sorted order.
     * HashSet         -> No guaranteed iteration order.
     * LinkedHashMap   -> Maintains insertion order by default.
     * TreeMap         -> Sorted by keys.
     * HashMap         -> No guaranteed iteration order.
     *
     * IMPORTANT:
     * Do not depend on an order that an implementation does
     * not guarantee.
     *
     *
     * 19. COLLECTION FRAMEWORK PACKAGE STRUCTURE
     *
     * java.lang:
     * - Iterable
     *
     * java.util:
     * - Collection
     * - List
     * - Set
     * - Queue
     * - Deque
     * - Map
     * - ArrayList
     * - LinkedList
     * - HashSet
     * - HashMap
     * - Collections
     * - Iterator
     * - Comparator
     *
     * java.util.concurrent:
     * - ConcurrentHashMap
     * - CopyOnWriteArrayList
     * - BlockingQueue
     * - ConcurrentLinkedQueue
     *
     * NOTE:
     * These are examples, not a complete list of classes.
     *
     *
     * 20. COLLECTIONS FRAMEWORK VS DATA STRUCTURES
     *
     * A data structure is a way of organizing and storing data.
     *
     * Examples:
     * - Array
     * - Linked list
     * - Stack
     * - Queue
     * - Hash table
     * - Tree
     *
     * The Collections Framework provides Java interfaces and
     * implementations that use data structures internally.
     *
     * Examples:
     * - ArrayList uses a resizable array.
     * - LinkedList uses a doubly linked list.
     * - HashMap uses hash-based storage.
     * - TreeMap uses a red-black tree.
     *
     * Knowing the interface tells you the contract.
     * Knowing the implementation tells you the internal behavior.
     *
     *
     * 21. IMPORTANT DESIGN PRINCIPLE:
     *     PROGRAM TO AN INTERFACE
     *
     * Prefer:
     *
     *     List<String> names = new ArrayList<>();
     *
     * Instead of:
     *
     *     ArrayList<String> names = new ArrayList<>();
     *
     * Why?
     *
     * The variable depends on the List contract rather than
     * a particular implementation.
     *
     * You can change the implementation:
     *
     *     List<String> names = new LinkedList<>();
     *
     * The code using common List operations can remain unchanged.
     *
     * IMPORTANT:
     * This does not mean every implementation is interchangeable
     * for every requirement.
     *
     * Choose an implementation based on:
     * - Required behavior.
     * - Performance.
     * - Ordering.
     * - Thread-safety requirements.
     * - Memory usage.
     *
     *
     * 22. COLLECTION FRAMEWORK AND JAVA VERSIONS
     *
     * Java 1.2:
     * - Collections Framework introduced.
     * - List, Set, Map and common implementations introduced.
     *
     * Java 5:
     * - Generics.
     * - Enhanced for-loop.
     * - Improved type safety.
     *
     * Java 8:
     * - Collection.forEach().
     * - Collection.removeIf().
     * - Collection.stream().
     * - Collection.parallelStream().
     *
     * Java 9:
     * - List.of().
     * - Set.of().
     * - Map.of().
     * - Unmodifiable collection factory methods.
     *
     * Java 10:
     * - List.copyOf().
     * - Set.copyOf().
     * - Map.copyOf().
     *
     * Java 16:
     * - Stream.toList().
     *
     * Java 21:
     * - SequencedCollection.
     * - SequencedSet.
     * - SequencedMap.
     *
     * These version milestones are important when working with
     * different Java environments.
     *
     *
     * 23. SEQUENCED COLLECTIONS (JAVA 21+)
     *
     * Before Java 21, different ordered collection types did
     * not share one common interface for first/last operations.
     *
     * Java 21 introduced:
     *
     * SequencedCollection:
     * - Represents a collection with a defined encounter order.
     * - Provides first/last element operations.
     * - Provides reversed() view.
     *
     * SequencedSet:
     * - A Set with defined encounter order.
     *
     * SequencedMap:
     * - A Map with defined encounter order.
     *
     * Examples of relevant implementations:
     * - ArrayList implements SequencedCollection.
     * - LinkedHashSet implements SequencedSet.
     * - LinkedHashMap implements SequencedMap.
     *
     * This is a Java 21+ feature.
     *
     *
     * 24. COMMON MISCONCEPTIONS
     *
     * 1. Collection and Collections are the same.
     *    WRONG: Collection is an interface; Collections is a class.
     *
     * 2. Map extends Collection.
     *    WRONG: Map is a separate hierarchy.
     *
     * 3. Every Set is sorted.
     *    WRONG: Only implementations such as TreeSet maintain
     *    sorted order.
     *
     * 4. Every Queue follows FIFO.
     *    WRONG: PriorityQueue uses priority-based ordering.
     *
     * 5. Collections store primitive values directly.
     *    WRONG: Generic collections use reference types.
     *
     * 6. Every collection is thread-safe.
     *    WRONG: Most common implementations are not synchronized.
     *
     * 7. Every List can grow dynamically.
     *    WRONG: Some lists are fixed-size or unmodifiable.
     *
     * 8. HashMap maintains insertion order.
     *    WRONG: HashMap makes no such guarantee.
     *
     * 9. Arrays implement Collection.
     *    WRONG: Arrays are separate language-level structures.
     *
     * 10. Collection is a concrete class.
     *     WRONG: Collection is an interface.
     *
     *
     * 25. INTERVIEW QUESTIONS
     *
     * BASIC:
     * 1. What is the Java Collections Framework?
     * 2. Why do we need collections?
     * 3. What are the three main parts of the framework?
     * 4. What is the difference between Collection and Collections?
     * 5. What is the difference between an array and a collection?
     *
     * INTERFACES:
     * 6. What is Iterable?
     * 7. What is the Collection interface?
     * 8. What is the difference between List and Set?
     * 9. What is the difference between Queue and Deque?
     * 10. Why does Map not extend Collection?
     *
     * BEHAVIOR:
     * 11. Which collections allow duplicates?
     * 12. Which collections maintain insertion order?
     * 13. Which collections maintain sorted order?
     * 14. Can a collection store null?
     * 15. Are collections thread-safe by default?
     *
     * ADVANCED:
     * 16. What is the difference between mutable and unmodifiable?
     * 17. What is the difference between fixed-size and unmodifiable?
     * 18. What is program-to-an-interface design?
     * 19. What is the purpose of generics in collections?
     * 20. What are Sequenced Collections in Java 21?
     *
     *
     * 26. REVISION CHECKLIST
     *
     * [ ] Understand why the Collections Framework exists.
     * [ ] Understand the difference between Collection and Collections.
     * [ ] Understand Iterable and Collection.
     * [ ] Understand List, Set, Queue, Deque and Map.
     * [ ] Know that Map does not extend Collection.
     * [ ] Understand duplicates, ordering and null handling.
     * [ ] Understand generics and wrapper types.
     * [ ] Understand arrays versus collections.
     * [ ] Understand mutable, fixed-size and unmodifiable lists.
     * [ ] Know the important Java version milestones.
     * [ ] Understand the Java 21 Sequenced Collections interfaces.
     *
     *
     * 27. WHAT YOU SHOULD REMEMBER
     *
     * Collections Framework = Interfaces + Implementations
     *                      + Algorithms + Utilities.
     *
     * Iterable is the root of the main collection hierarchy.
     *
     * Collection is the parent of List, Set and Queue.
     *
     * Map is separate and stores key-value pairs.
     *
     * List  -> Ordered elements; duplicates allowed.
     * Set   -> Unique elements.
     * Queue -> Elements waiting for processing.
     * Deque -> Operations at both ends.
     * Map   -> Unique keys mapped to values.
     *
     * The interface defines the contract.
     * The implementation determines the behavior and internals.
     *
     * ============================================================
     *                 END OF COLLECTIONS FUNDAMENTALS
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Collections Fundamentals , Happy Learning ..!");
    }
}
