
public class Spliterator {

    /*
     * ============================================================
     * 1. WHY DO WE NEED SPLITERATOR?
     * ============================================================
     *
     * Imagine a collection containing 1 million records.
     *
     * Suppose we want to process every record.
     *
     * Traditional Iterator:
     *
     * Thread 1:
     *     Record 1 -> Record 2 -> Record 3 -> ... -> Record 1000000
     *
     * It traverses elements sequentially.
     *
     * But what if we could divide the collection into smaller
     * portions and process those portions independently?
     *
     * For example:
     *
     * Portion 1 -> Records 1 to 250000
     * Portion 2 -> Records 250001 to 500000
     * Portion 3 -> Records 500001 to 750000
     * Portion 4 -> Records 750001 to 1000000
     *
     * These portions could potentially be processed in parallel.
     *
     * Java provides Spliterator for this purpose.
     *
     *
     * ============================================================
     * 2. WHAT IS SPLITERATOR?
     * ============================================================
     *
     * Spliterator stands for:
     *
     * Split + Iterator
     *
     * It is an interface available in java.util.
     *
     * Declaration:
     *
     * public interface Spliterator<T>
     *
     * It was introduced in Java 8.
     *
     * A Spliterator is used to:
     *
     * 1. Traverse elements sequentially.
     * 2. Divide elements into smaller portions.
     * 3. Support efficient parallel processing.
     * 4. Describe characteristics of the elements.
     *
     * The Stream API uses Spliterator internally for
     * traversing and splitting data.
     *
     *
     * ============================================================
     * 3. HOW TO CREATE A SPLITERATOR?
     * ============================================================
     *
     * Collection provides the spliterator() method.
     *
     * Syntax:
     *
     * Spliterator<Type> spliterator =
     *     collection.spliterator();
     *
     * Example:
     *
     * List<String> names = List.of(
     *     "Vinay", "Rahul", "Arjun", "Kiran"
     * );
     *
     * Spliterator<String> sp = names.spliterator();
     *
     * We can now traverse the collection using this Spliterator.
     *
     *
     * ============================================================
     * 4. IMPORTANT METHODS OF SPLITERATOR
     * ============================================================
     *
     * The main methods are:
     *
     * 1. tryAdvance()
     * 2. forEachRemaining()
     * 3. trySplit()
     * 4. estimateSize()
     * 5. getExactSizeIfKnown()
     * 6. characteristics()
     * 7. hasCharacteristics()
     * 8. getComparator()
     *
     *
     * ------------------------------------------------------------
     * 4.1 tryAdvance()
     * ------------------------------------------------------------
     *
     * Declaration:
     *
     * boolean tryAdvance(Consumer<? super T> action);
     *
     * Purpose:
     * Processes one element if available.
     *
     * Returns:
     *
     * true  -> An element was processed.
     * false -> No more elements are available.
     *
     * Example:
     *
     * List<Integer> numbers = List.of(10, 20, 30);
     *
     * Spliterator<Integer> sp = numbers.spliterator();
     *
     * sp.tryAdvance(System.out::println);
     * sp.tryAdvance(System.out::println);
     *
     * Output:
     * 10
     * 20
     *
     * Each successful call processes one element and advances
     * the Spliterator.
     *
     *
     * ------------------------------------------------------------
     * 4.2 forEachRemaining()
     * ------------------------------------------------------------
     *
     * Declaration:
     *
     * default void forEachRemaining(
     *     Consumer<? super T> action
     * );
     *
     * Purpose:
     * Processes all remaining elements.
     *
     * Example:
     *
     * List<Integer> numbers = List.of(10, 20, 30);
     *
     * Spliterator<Integer> sp = numbers.spliterator();
     *
     * sp.forEachRemaining(System.out::println);
     *
     * Output:
     * 10
     * 20
     * 30
     *
     * Important:
     * It processes only the elements remaining from the
     * current position.
     *
     *
     * ------------------------------------------------------------
     * 4.3 trySplit()
     * ------------------------------------------------------------
     *
     * Declaration:
     *
     * Spliterator<T> trySplit();
     *
     * Purpose:
     * Attempts to divide the remaining elements into two parts.
     *
     * Returns:
     *
     * A new Spliterator covering a portion of the elements,
     * or null if the elements cannot be split.
     *
     * Example:
     *
     * List<Integer> numbers = List.of(
     *     10, 20, 30, 40, 50, 60, 70, 80
     * );
     *
     * Spliterator<Integer> first = numbers.spliterator();
     *
     * Spliterator<Integer> second = first.trySplit();
     *
     * if (second != null) {
     *
     *     System.out.println("First portion:");
     *     first.forEachRemaining(System.out::println);
     *
     *     System.out.println("Second portion:");
     *     second.forEachRemaining(System.out::println);
     * }
     *
     * For this ordered list, a typical ArrayList Spliterator
     * divides the elements into approximately equal portions.
     *
     * Important:
     *
     * trySplit() does not guarantee that every implementation
     * divides elements into equal halves.
     *
     * The returned Spliterator and the original Spliterator
     * represent separate portions of the traversal.
     *
     *
     * ------------------------------------------------------------
     * 4.4 estimateSize()
     * ------------------------------------------------------------
     *
     * Declaration:
     *
     * long estimateSize();
     *
     * Purpose:
     * Returns an estimate of the number of elements remaining.
     *
     * Example:
     *
     * List<Integer> numbers = List.of(10, 20, 30, 40);
     *
     * Spliterator<Integer> sp = numbers.spliterator();
     *
     * System.out.println(sp.estimateSize());
     *
     * Output:
     * 4
     *
     * The result is an estimate, not necessarily an exact count.
     *
     *
     * ------------------------------------------------------------
     * 4.5 getExactSizeIfKnown()
     * ------------------------------------------------------------
     *
     * Declaration:
     *
     * default long getExactSizeIfKnown();
     *
     * Purpose:
     * Returns the exact remaining size when the Spliterator
     * reports the SIZED characteristic.
     *
     * Otherwise, returns -1.
     *
     *
     * ------------------------------------------------------------
     * 4.6 characteristics()
     * ------------------------------------------------------------
     *
     * Declaration:
     *
     * int characteristics();
     *
     * Purpose:
     * Returns a bit mask describing the properties of the
     * elements and traversal.
     *
     * Examples of characteristics:
     *
     * ORDERED
     * DISTINCT
     * SORTED
     * SIZED
     * NONNULL
     * IMMUTABLE
     * CONCURRENT
     * SUBSIZED
     *
     *
     * ------------------------------------------------------------
     * 4.7 hasCharacteristics()
     * ------------------------------------------------------------
     *
     * Declaration:
     *
     * default boolean hasCharacteristics(int characteristics);
     *
     * Purpose:
     * Checks whether the Spliterator has the specified
     * characteristics.
     *
     * Example:
     *
     * Spliterator<Integer> sp = numbers.spliterator();
     *
     * System.out.println(
     *     sp.hasCharacteristics(Spliterator.ORDERED)
     * );
     *
     *
     * ------------------------------------------------------------
     * 4.8 getComparator()
     * ------------------------------------------------------------
     *
     * Declaration:
     *
     * Comparator<? super T> getComparator();
     *
     * Purpose:
     * Returns the comparator used for ordering elements when
     * the Spliterator reports the SORTED characteristic.
     *
     * If the elements are sorted according to their natural
     * ordering, it returns null.
     *
     * If the Spliterator is not SORTED, it throws
     * IllegalStateException.
     *
     *
     * ============================================================
     * 5. SPLITERATOR CHARACTERISTICS
     * ============================================================
     *
     * Characteristics describe the properties of the elements
     * and the traversal.
     *
     * They help the Stream API and other consumers optimize
     * processing.
     *
     *
     * 1. ORDERED
     *
     * Elements have a defined encounter order.
     *
     *
     * 2. DISTINCT
     *
     * Elements are distinct according to equals().
     *
     *
     * 3. SORTED
     *
     * Elements follow a defined sorting order.
     *
     *
     * 4. SIZED
     *
     * The exact number of remaining elements is known.
     *
     *
     * 5. NONNULL
     *
     * Elements are guaranteed to be non-null.
     *
     *
     * 6. IMMUTABLE
     *
     * The element source cannot be structurally modified.
     *
     *
     * 7. CONCURRENT
     *
     * The element source can be safely modified concurrently
     * according to the source's documented guarantees.
     *
     *
     * 8. SUBSIZED
     *
     * Spliterators produced by trySplit() are also SIZED
     * and SUBSIZED.
     *
     *
     * Important:
     *
     * Characteristics are properties promised by the
     * Spliterator implementation.
     *
     * Do not assume every Spliterator supports every
     * characteristic.
     *
     *
     * ============================================================
     * 6. SPLITTING AND PARALLEL PROCESSING
     * ============================================================
     *
     * Consider:
     *
     * List<Integer> numbers = List.of(
     *     1, 2, 3, 4, 5, 6, 7, 8
     * );
     *
     * Spliterator<Integer> first = numbers.spliterator();
     *
     * Spliterator<Integer> second = first.trySplit();
     *
     * Conceptually, the original Spliterator may cover:
     *
     * [5, 6, 7, 8]
     *
     * The new Spliterator may cover:
     *
     * [1, 2, 3, 4]
     *
     * These portions can be processed independently.
     *
     * This is the foundation for parallel traversal.
     *
     * Important:
     *
     * Spliterator itself does not automatically create threads.
     *
     * It provides the ability to split work.
     *
     * Parallel Streams and frameworks can use that ability
     * to distribute work across threads.
     *
     *
     * ============================================================
     * 7. SPLITERATOR AND STREAM API
     * ============================================================
     *
     * Java 8 introduced both Spliterator and the Stream API.
     *
     * Collections provide spliterator().
     *
     * Stream implementations use Spliterators to traverse
     * and split their data sources.
     *
     * Example:
     *
     * List<Integer> numbers = List.of(
     *     1, 2, 3, 4, 5
     * );
     *
     * numbers.stream()
     *        .forEach(System.out::println);
     *
     * Parallel Stream:
     *
     * numbers.parallelStream()
     *        .forEach(System.out::println);
     *
     * A parallel stream can use Spliterator splitting to
     * distribute work among worker threads.
     *
     * Important:
     *
     * Parallel processing does not guarantee that output
     * appears in encounter order.
     *
     * Use forEachOrdered() when encounter-order output
     * is required for an ordered stream.
     *
     * Parallel streams are not automatically faster.
     *
     * Their performance depends on:
     *
     * - Collection size.
     * - Cost of each operation.
     * - Splitting efficiency.
     * - Available processor cores.
     * - Coordination and synchronization overhead.
     *
     *
     * ============================================================
     * 8. PRIMITIVE SPECIALIZED SPLITERATORS
     * ============================================================
     *
     * Java provides specialized Spliterator interfaces
     * for primitive values.
     *
     * 1. Spliterator.OfInt
     * 2. Spliterator.OfLong
     * 3. Spliterator.OfDouble
     *
     * These are designed to traverse primitive values
     * without requiring ordinary wrapper objects.
     *
     * They provide specialized methods such as:
     *
     * IntConsumer
     * LongConsumer
     * DoubleConsumer
     *
     * Example:
     *
     * int[] numbers = {10, 20, 30, 40};
     *
     * Spliterator.OfInt sp =
     *     Arrays.spliterator(numbers);
     *
     * sp.forEachRemaining(
     *     (int value) -> System.out.println(value)
     * );
     *
     * Specialized primitive Spliterators are useful when
     * working with primitive arrays and primitive streams.
     *
     *
     * ============================================================
     * 9. SPLITERATOR VS ITERATOR
     * ============================================================
     *
     * Iterator:
     *
     * - General-purpose sequential traversal.
     * - Provides hasNext() and next().
     * - Optional remove().
     * - Does not provide splitting.
     *
     * Spliterator:
     *
     * - Supports sequential traversal.
     * - Supports splitting.
     * - Designed to support parallel processing.
     * - Provides characteristics about the data source.
     * - Uses tryAdvance() and forEachRemaining().
     *
     * Remember:
     *
     * Iterator = Traverse.
     *
     * Spliterator = Traverse + Split.
     *
     *
     * ============================================================
     * 10. SPLITERATOR VS ITERABLE
     * ============================================================
     *
     * Iterable:
     *
     * - Represents a source of elements that can be iterated.
     * - Provides iterator().
     * - Provides a default spliterator() method.
     *
     * Spliterator:
     *
     * - Performs traversal.
     * - Can divide the source into portions.
     * - Describes characteristics of the source.
     *
     *
     * ============================================================
     * 11. CUSTOM SPLITERATOR
     * ============================================================
     *
     * We can implement Spliterator ourselves when we have
     * a custom data source.
     *
     * A custom implementation must provide:
     *
     * 1. tryAdvance()
     * 2. trySplit()
     * 3. estimateSize()
     * 4. characteristics()
     *
     * It may also override other default methods.
     *
     * Example use cases:
     *
     * - Reading a large custom data source.
     * - Processing records from a file.
     * - Dividing data into batches.
     * - Building custom Stream sources.
     *
     * Important:
     *
     * A correct custom Spliterator must ensure that splitting
     * does not lose or duplicate elements.
     *
     * Its reported characteristics must accurately describe
     * its actual behavior.
     *
     *
     * ============================================================
     * 12. IMPORTANT POINTS TO REMEMBER
     * ============================================================
     *
     * 1. Spliterator belongs to java.util.
     *
     * 2. It was introduced in Java 8.
     *
     * 3. Spliterator means Split + Iterator.
     *
     * 4. Collection provides spliterator().
     *
     * 5. tryAdvance() processes one element.
     *
     * 6. forEachRemaining() processes remaining elements.
     *
     * 7. trySplit() attempts to divide remaining elements.
     *
     * 8. estimateSize() returns an estimate of remaining size.
     *
     * 9. characteristics() describes source properties.
     *
     * 10. Spliterator supports sequential and split traversal.
     *
     * 11. Spliterator does not itself create threads.
     *
     * 12. Stream API uses Spliterator internally.
     *
     * 13. Parallel Streams can use splitting for parallel work.
     *
     * 14. Parallel processing is not always faster.
     *
     *
     * ============================================================
     * 13. INTERVIEW QUESTIONS
     * ============================================================
     *
     * 1. What is Spliterator?
     *
     * 2. When was Spliterator introduced?
     *
     * 3. Why is it called Split + Iterator?
     *
     * 4. What is the difference between Iterator and Spliterator?
     *
     * 5. What is the purpose of tryAdvance()?
     *
     * 6. What is the purpose of trySplit()?
     *
     * 7. Does trySplit() always return a new Spliterator?
     *
     * 8. What are Spliterator characteristics?
     *
     * 9. What is the difference between SIZED and SUBSIZED?
     *
     * 10. How does Spliterator help Parallel Streams?
     *
     * 11. Does Spliterator create threads by itself?
     *
     * 12. What are OfInt, OfLong, and OfDouble?
     *
     * 13. What is the purpose of getComparator()?
     *
     * 14. Can we implement our own Spliterator?
     *
     *
     * ============================================================
     * 14. QUICK REVISION
     * ============================================================
     *
     * Spliterator = Split + Iterator.
     *
     * Introduced = Java 8.
     *
     * tryAdvance() = Processes one element.
     *
     * forEachRemaining() = Processes remaining elements.
     *
     * trySplit() = Divides remaining elements when possible.
     *
     * estimateSize() = Estimates remaining elements.
     *
     * characteristics() = Describes traversal properties.
     *
     * Stream API = Uses Spliterator internally.
     *
     * Parallel Stream = Can use splitting for parallel work.
     *
     * Important = Spliterator does not create threads itself.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Spliterator , Happy Learning ..!");
    }
}
