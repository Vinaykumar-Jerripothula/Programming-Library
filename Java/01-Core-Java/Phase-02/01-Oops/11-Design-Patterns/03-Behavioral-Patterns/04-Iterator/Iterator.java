
public class Iterator {

    /*
     * ============================================================
     * 1. INTRODUCTION
     * ============================================================
     *
     * Imagine you have a collection containing 1,000 students.
     *
     * You want to access each student one by one.
     *
     * You might use an array:
     *
     *     Student[] students = new Student[1000];
     *
     * Then access elements using indexes:
     *
     *     students[0];
     *     students[1];
     *     students[2];
     *
     * But what if the data is stored in:
     *
     *   - An ArrayList?
     *   - A LinkedList?
     *   - A HashSet?
     *   - A custom collection?
     *
     * Each collection may have a different internal structure.
     *
     * Should the client understand how every collection
     * stores its elements?
     *
     * No.
     *
     * Instead, we can provide a common way to traverse
     * a collection without exposing its internal structure.
     *
     * That is the purpose of the Iterator pattern.
     *
     *
     * DEFINITION:
     *
     * Iterator is a behavioral design pattern that provides
     * a way to access elements of a collection sequentially
     * without exposing its underlying representation.
     *
     *
     * SIMPLE DEFINITION:
     *
     * Traverse a collection one element at a time without
     * knowing how the collection stores its elements.
     *
     *
     * ============================================================
     * 2. WHY DO WE NEED ITERATOR?
     * ============================================================
     *
     * Consider two collections:
     *
     * ArrayList:
     *
     * Elements are stored using a resizable array.
     *
     * LinkedList:
     *
     * Elements are stored using linked nodes.
     *
     * Their internal structures are different.
     *
     * Without a common traversal mechanism, client code may
     * need to understand each collection's implementation.
     *
     * Iterator provides a common traversal interface.
     *
     * The client only needs to know how to:
     *
     *   1. Check whether another element exists.
     *   2. Retrieve the next element.
     *
     * It does not need to know whether the collection
     * uses an array, linked nodes or another structure.
     *
     *
     * ============================================================
     * 3. REAL-WORLD ANALOGY
     * ============================================================
     *
     * Imagine reading a book.
     *
     * You do not need to know how the pages are physically
     * stored or bound together.
     *
     * You simply:
     *
     *   1. Open the book.
     *   2. Read the current page.
     *   3. Move to the next page.
     *   4. Continue until the book ends.
     *
     * An Iterator provides a similar way to move through
     * a collection.
     *
     *
     * ============================================================
     * 4. STRUCTURE AND PARTICIPANTS
     * ============================================================
     *
     * Main participants:
     *
     * 1. Iterator:
     *
     *    Defines operations for traversing elements.
     *
     *    Common operations:
     *
     *       hasNext()
     *       next()
     *
     *
     * 2. Concrete Iterator:
     *
     *    Implements the traversal logic for a particular
     *    collection.
     *
     *
     * 3. Aggregate:
     *
     *    Defines an interface for creating an Iterator.
     *
     *
     * 4. Concrete Aggregate:
     *
     *    Represents the actual collection and creates
     *    an appropriate Iterator.
     *
     *
     * 5. Client:
     *
     *    Uses the Iterator to traverse the collection.
     *
     *
     * BASIC STRUCTURE:
     *
     *             Client
     *                |
     *                v
     *             Iterator
     *                |
     *                v
     *          Concrete Iterator
     *                |
     *                v
     *          Concrete Aggregate
     *                |
     *                v
     *             Elements
     *
     *
     * ============================================================
     * 5. COMPLETE JAVA IMPLEMENTATION
     * ============================================================
     *
     * We will create a custom collection of books and
     * implement our own Iterator.
     *
     *
     * STEP 1: Create the Iterator interface.
     *
     * interface MyIterator<T> {
     *
     *     boolean hasNext();
     *
     *     T next();
     * }
     *
     * hasNext():
     *
     * Checks whether another element is available.
     *
     * next():
     *
     * Returns the next element and advances the iterator.
     *
     *
     * STEP 2: Create the Aggregate interface.
     *
     * interface BookCollection {
     *
     *     MyIterator<String> createIterator();
     * }
     *
     * This interface allows a collection to provide
     * an appropriate iterator.
     *
     *
     * STEP 3: Create the Concrete Aggregate.
     *
     * class Library implements BookCollection {
     *
     *     private final String[] books;
     *
     *     public Library(String[] books) {
     *         this.books = books;
     *     }
     *
     *     @Override
     *     public MyIterator<String> createIterator() {
     *         return new BookIterator(books);
     *     }
     * }
     *
     *
     * STEP 4: Create the Concrete Iterator.
     *
     * class BookIterator implements MyIterator<String> {
     *
     *     private final String[] books;
     *
     *     private int position = 0;
     *
     *     public BookIterator(String[] books) {
     *         this.books = books;
     *     }
     *
     *     @Override
     *     public boolean hasNext() {
     *         return position < books.length;
     *     }
     *
     *     @Override
     *     public String next() {
     *
     *         if (!hasNext()) {
     *             throw new java.util.NoSuchElementException();
     *         }
     *
     *         return books[position++];
     *     }
     * }
     *
     *
     * STEP 5: Client code.
     *
     * String[] books = {
     *     "Java",
     *     "Spring Boot",
     *     "Data Structures",
     *     "Database Systems"
     * };
     *
     * BookCollection library = new Library(books);
     *
     * MyIterator<String> iterator = library.createIterator();
     *
     * while (iterator.hasNext()) {
     *
     *     System.out.println(iterator.next());
     * }
     *
     *
     * OUTPUT:
     *
     * Java
     * Spring Boot
     * Data Structures
     * Database Systems
     *
     *
     * ============================================================
     * 6. UNDERSTANDING THE EXECUTION FLOW
     * ============================================================
     *
     * Initially:
     *
     * position = 0
     *
     * Collection:
     *
     * Index:     0          1              2
     *          --------------------------------
     * Element:  Java     Spring Boot    Data Structures
     *
     *
     * FIRST ITERATION:
     *
     * hasNext():
     *
     * 0 < 3 -> true
     *
     * next():
     *
     * Returns books[0].
     *
     * Element: Java
     *
     * position becomes 1.
     *
     *
     * SECOND ITERATION:
     *
     * hasNext():
     *
     * 1 < 3 -> true
     *
     * next():
     *
     * Returns books[1].
     *
     * Element: Spring Boot
     *
     * position becomes 2.
     *
     *
     * THIRD ITERATION:
     *
     * hasNext():
     *
     * 2 < 3 -> true
     *
     * next():
     *
     * Returns books[2].
     *
     * Element: Data Structures
     *
     * position becomes 3.
     *
     *
     * FOURTH CHECK:
     *
     * hasNext():
     *
     * 3 < 3 -> false
     *
     * The loop stops.
     *
     *
     * IMPORTANT:
     *
     * hasNext() checks availability.
     *
     * next() retrieves the element and advances the iterator.
     *
     *
     * ============================================================
     * 7. JAVA ITERATOR INTERFACE
     * ============================================================
     *
     * Java already provides an Iterator interface:
     *
     *     java.util.Iterator<E>
     *
     * Important methods:
     *
     * 1. hasNext()
     *
     *    Returns true if another element is available.
     *
     *
     * 2. next()
     *
     *    Returns the next element.
     *
     *    Throws NoSuchElementException if no element remains.
     *
     *
     * 3. remove()
     *
     *    Removes the last element returned by next(),
     *    if supported by the iterator.
     *
     *    This is an optional operation.
     *
     *
     * 4. forEachRemaining()
     *
     *    Performs an action for each remaining element.
     *
     *
     * Example:
     *
     * import java.util.ArrayList;
     * import java.util.Iterator;
     * import java.util.List;
     *
     * List<String> languages = new ArrayList<>();
     *
     * languages.add("Java");
     * languages.add("Python");
     * languages.add("JavaScript");
     *
     * Iterator<String> iterator = languages.iterator();
     *
     * while (iterator.hasNext()) {
     *
     *     System.out.println(iterator.next());
     * }
     *
     *
     * OUTPUT:
     *
     * Java
     * Python
     * JavaScript
     *
     *
     * ============================================================
     * 8. ITERABLE VS ITERATOR
     * ============================================================
     *
     * These two interfaces are frequently confused.
     *
     *
     * ITERATOR:
     *
     * Represents the object that traverses a collection.
     *
     * It maintains traversal state such as the current position.
     *
     * Main methods:
     *
     *     hasNext()
     *     next()
     *
     *
     * ITERABLE:
     *
     * Represents an object that can provide an Iterator.
     *
     * It defines:
     *
     *     Iterator<T> iterator();
     *
     *
     * SIMPLE EXPLANATION:
     *
     * Iterable -> Can provide an iterator.
     *
     * Iterator -> Actually traverses the elements.
     *
     *
     * EXAMPLE:
     *
     * List<String> names = List.of("A", "B", "C");
     *
     * Iterable<String> iterable = names;
     *
     * Iterator<String> iterator = iterable.iterator();
     *
     *
     * ============================================================
     * 9. HOW DOES ENHANCED FOR LOOP WORK?
     * ============================================================
     *
     * Consider:
     *
     * for (String name : names) {
     *
     *     System.out.println(name);
     * }
     *
     * This is called the enhanced for loop or for-each loop.
     *
     * For objects implementing Iterable, Java uses
     * their Iterator behind the scenes.
     *
     * Conceptually, the loop behaves like:
     *
     * Iterator<String> iterator = names.iterator();
     *
     * while (iterator.hasNext()) {
     *
     *     String name = iterator.next();
     *
     *     System.out.println(name);
     * }
     *
     * IMPORTANT:
     *
     * This is a conceptual expansion.
     *
     * Enhanced for loops also support arrays, where Java
     * uses index-based traversal rather than Iterator.
     *
     *
     * ============================================================
     * 10. WHY CAN WE CREATE MULTIPLE ITERATORS?
     * ============================================================
     *
     * Consider:
     *
     * List<String> names = List.of("A", "B", "C");
     *
     * Iterator<String> first = names.iterator();
     *
     * Iterator<String> second = names.iterator();
     *
     * Both iterators maintain independent traversal state.
     *
     * Example:
     *
     * first.next();
     * first.next();
     *
     * second.next();
     *
     * The first iterator has advanced twice.
     *
     * The second iterator has advanced only once.
     *
     * The collection itself has not needed to store
     * a single shared current position.
     *
     * This allows multiple independent traversals.
     *
     *
     * ============================================================
     * 11. FAIL-FAST ITERATORS
     * ============================================================
     *
     * Consider:
     *
     * List<String> names = new ArrayList<>();
     *
     * names.add("A");
     * names.add("B");
     * names.add("C");
     *
     * Iterator<String> iterator = names.iterator();
     *
     * while (iterator.hasNext()) {
     *
     *     String name = iterator.next();
     *
     *     names.add("D");
     * }
     *
     * This may throw:
     *
     * ConcurrentModificationException
     *
     *
     * WHY?
     *
     * ArrayList iterators are generally fail-fast.
     *
     * They detect certain structural modifications to the
     * collection outside the iterator while traversal is active.
     *
     *
     * STRUCTURAL MODIFICATION:
     *
     * A modification that changes the collection's structure,
     * such as adding or removing elements.
     *
     *
     * IMPORTANT:
     *
     * Fail-fast behavior is best-effort.
     *
     * It is not a synchronization mechanism and must not
     * be relied upon for thread safety.
     *
     *
     * ============================================================
     * 12. HOW TO REMOVE ELEMENTS SAFELY DURING ITERATION?
     * ============================================================
     *
     * If the iterator supports removal, use iterator.remove().
     *
     * Example:
     *
     * List<String> names = new ArrayList<>(
     *     List.of("Java", "Python", "JavaScript")
     * );
     *
     * Iterator<String> iterator = names.iterator();
     *
     * while (iterator.hasNext()) {
     *
     *     String name = iterator.next();
     *
     *     if (name.equals("Python")) {
     *
     *         iterator.remove();
     *     }
     * }
     *
     * System.out.println(names);
     *
     *
     * OUTPUT:
     *
     * [Java, JavaScript]
     *
     *
     * IMPORTANT:
     *
     * remove() removes the last element returned by next().
     *
     * Calling remove() before next(), or calling it twice
     * without another next(), can throw IllegalStateException.
     *
     * Some iterators do not support removal and throw
     * UnsupportedOperationException.
     *
     *
     * ============================================================
     * 13. LISTITERATOR
     * ============================================================
     *
     * Java provides another interface:
     *
     *     java.util.ListIterator<E>
     *
     * It is designed specifically for List collections.
     *
     * Unlike Iterator, ListIterator supports bidirectional
     * traversal.
     *
     *
     * IMPORTANT METHODS:
     *
     * hasNext()
     * next()
     * hasPrevious()
     * previous()
     * nextIndex()
     * previousIndex()
     * add()
     * set()
     * remove()
     *
     *
     * EXAMPLE:
     *
     * List<String> names = new ArrayList<>(
     *     List.of("A", "B", "C")
     * );
     *
     * ListIterator<String> iterator = names.listIterator();
     *
     * while (iterator.hasNext()) {
     *     System.out.println(iterator.next());
     * }
     *
     * while (iterator.hasPrevious()) {
     *     System.out.println(iterator.previous());
     * }
     *
     *
     * OUTPUT:
     *
     * A
     * B
     * C
     * C
     * B
     * A
     *
     *
     * IMPORTANT:
     *
     * ListIterator is available for List implementations.
     *
     * It is not a general-purpose iterator for every collection.
     *
     *
     * ============================================================
     * 14. ITERATOR VS ENUMERATION
     * ============================================================
     *
     * Enumeration is an older Java interface used to traverse
     * elements in legacy collection APIs.
     *
     * Common methods:
     *
     *     hasMoreElements()
     *     nextElement()
     *
     *
     * ITERATOR:
     *
     * - hasNext()
     * - next()
     * - Optional remove()
     *
     *
     * ENUMERATION:
     *
     * - hasMoreElements()
     * - nextElement()
     * - Does not provide remove()
     *
     * Iterator is generally preferred for modern collection
     * traversal.
     *
     *
     * ============================================================
     * 15. ITERATOR VS STREAM
     * ============================================================
     *
     * Both can be used to process collection elements,
     * but their purposes and programming styles differ.
     *
     *
     * ITERATOR:
     *
     * Provides explicit, sequential traversal.
     *
     * Example:
     *
     * Iterator<String> iterator = names.iterator();
     *
     * while (iterator.hasNext()) {
     *     System.out.println(iterator.next());
     * }
     *
     *
     * STREAM:
     *
     * Provides a declarative pipeline for processing data.
     *
     * Example:
     *
     * names.stream()
     *      .filter(name -> name.startsWith("J"))
     *      .forEach(System.out::println);
     *
     *
     * REMEMBER:
     *
     * Iterator -> Explicit traversal.
     *
     * Stream -> Declarative data processing.
     *
     * A Stream is not itself a replacement for every use
     * of Iterator.
     *
     *
     * ============================================================
     * 16. ITERATOR IN JAVA COLLECTIONS FRAMEWORK
     * ============================================================
     *
     * Most Java collection types provide iterators.
     *
     * Examples:
     *
     * ArrayList:
     *
     * Iterator<String> i1 = arrayList.iterator();
     *
     *
     * LinkedList:
     *
     * Iterator<String> i2 = linkedList.iterator();
     *
     *
     * HashSet:
     *
     * Iterator<String> i3 = hashSet.iterator();
     *
     *
     * HashMap:
     *
     * Iterator<Map.Entry<Integer, String>> i4 =
     *     map.entrySet().iterator();
     *
     *
     * IMPORTANT:
     *
     * HashMap itself is not a Collection.
     *
     * Its keySet(), values() and entrySet() views provide
     * iterable collections.
     *
     * Iteration order depends on the collection implementation.
     *
     * For example:
     *
     * - ArrayList preserves positional order.
     * - LinkedHashSet preserves insertion order.
     * - HashSet does not guarantee a particular iteration order.
     *
     *
     * ============================================================
     * 17. ITERATOR IN BACKEND DEVELOPMENT
     * ============================================================
     *
     * Iterators are useful when processing collections
     * without exposing their internal representation.
     *
     * Examples:
     *
     * 1. Processing users from a collection.
     *
     * 2. Traversing records in a custom data structure.
     *
     * 3. Iterating through map entries.
     *
     * 4. Processing batches of in-memory objects.
     *
     * 5. Building custom collection APIs.
     *
     * IMPORTANT:
     *
     * Java collection iteration is not the same as database
     * pagination or streaming a large database result.
     *
     * For very large datasets, use appropriate pagination,
     * cursor-based retrieval or streaming mechanisms.
     *
     *
     * ============================================================
     * 18. ADVANTAGES
     * ============================================================
     *
     * 1. Hides the internal structure of a collection.
     *
     * 2. Provides a common traversal interface.
     *
     * 3. Supports independent traversals.
     *
     * 4. Simplifies client-side iteration logic.
     *
     * 5. Allows different collection implementations to
     *    expose a consistent traversal mechanism.
     *
     *
     * ============================================================
     * 19. DISADVANTAGES
     * ============================================================
     *
     * 1. Introduces additional iterator objects.
     *
     * 2. Iterator behavior may differ between collections.
     *
     * 3. Concurrent modification can cause exceptions
     *    for fail-fast iterators.
     *
     * 4. Some iterators do not support removal.
     *
     * 5. More complex traversal requirements may need
     *    specialized iterator implementations.
     *
     *
     * ============================================================
     * 20. WHEN SHOULD WE USE ITERATOR?
     * ============================================================
     *
     * Use Iterator when:
     *
     * - You need to traverse a collection sequentially.
     * - You do not want to expose the collection's internals.
     * - You need independent traversal state.
     * - You need to remove elements through supported iterators.
     * - You are implementing a custom collection.
     *
     * Use ListIterator when:
     *
     * - You need forward and backward traversal of a List.
     * - You need supported insertion or replacement operations
     *   during traversal.
     *
     * Avoid implementing a custom Iterator when:
     *
     * - Java's existing collection iterator already satisfies
     *   your requirements.
     *
     *
     * ============================================================
     * 21. COMMON MISCONCEPTIONS
     * ============================================================
     *
     * 1. Iterable and Iterator are the same.
     *    FALSE. Iterable provides an iterator;
     *    Iterator performs traversal.
     *
     * 2. Iterator always supports remove().
     *    FALSE. Removal is optional.
     *
     * 3. Iterator supports backward traversal.
     *    FALSE. ListIterator provides bidirectional traversal.
     *
     * 4. Enhanced for loops always use Iterator.
     *    FALSE. Arrays are traversed using indexes.
     *
     * 5. Fail-fast iterators make collections thread-safe.
     *    FALSE. Fail-fast behavior is not synchronization.
     *
     * 6. Every collection guarantees iteration order.
     *    FALSE. Ordering depends on the collection implementation.
     *
     * 7. HashMap directly implements Collection.
     *    FALSE. It implements Map, and its views can be traversed.
     *
     *
     * ============================================================
     * 22. INTERVIEW QUESTIONS
     * ============================================================
     *
     * Q1. What is Iterator Design Pattern?
     *
     * A: It provides sequential access to collection elements
     *    without exposing the collection's internal structure.
     *
     * Q2. What are its main participants?
     *
     * A: Iterator, Concrete Iterator, Aggregate,
     *    Concrete Aggregate and Client.
     *
     * Q3. What is the difference between Iterable and Iterator?
     *
     * A: Iterable provides an Iterator; Iterator traverses elements.
     *
     * Q4. What is the purpose of hasNext()?
     *
     * A: It checks whether another element is available.
     *
     * Q5. What happens when next() is called after the
     *    final element?
     *
     * A: It throws NoSuchElementException.
     *
     * Q6. What is ConcurrentModificationException?
     *
     * A: An exception commonly thrown by fail-fast iterators
     *    when they detect certain unsupported structural
     *    modifications during iteration.
     *
     * Q7. What is ListIterator?
     *
     * A: An iterator for Lists that supports forward and
     *    backward traversal and additional modification methods.
     *
     * Q8. What is the difference between Iterator and Enumeration?
     *
     * A: Iterator supports optional removal and is generally
     *    preferred for modern collection traversal.
     *
     * Q9. How does enhanced for loop work?
     *
     * A: For Iterable objects, it uses an Iterator internally.
     *    For arrays, it uses index-based traversal.
     *
     *
     * ============================================================
     * 23. QUICK REVISION
     * ============================================================
     *
     * PATTERN:
     * Iterator
     *
     * CATEGORY:
     * Behavioral Design Pattern
     *
     * PURPOSE:
     * Traverse elements without exposing internal structure.
     *
     * MAIN IDEA:
     * Access elements one by one through a common interface.
     *
     * PARTICIPANTS:
     *
     * Iterator
     * Concrete Iterator
     * Aggregate
     * Concrete Aggregate
     * Client
     *
     * JAVA INTERFACES:
     *
     * Iterable<E>
     * Iterator<E>
     * ListIterator<E>
     *
     * IMPORTANT METHODS:
     *
     * hasNext()
     * next()
     * remove()
     * hasPrevious()
     * previous()
     *
     * IMPORTANT DIFFERENCES:
     *
     * Iterable -> Provides an Iterator.
     *
     * Iterator -> Traverses elements.
     *
     * ListIterator -> Traverses a List in both directions.
     *
     * Stream -> Processes data through a pipeline.
     *
     * REMEMBER:
     *
     * The collection stores the elements.
     *
     * The Iterator manages traversal.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Iterator Design Pattern , Happy Learning ..!");
    }
}
