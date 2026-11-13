
public class Enumeration {

    /*
     * ============================================================
     * 1. WHY DO WE NEED ENUMERATION?
     * ============================================================
     *
     * Before Java introduced the Collection Framework and Iterator,
     * Java provided older collection classes such as:
     *
     * - Vector
     * - Hashtable
     *
     * These classes needed a mechanism to access their elements
     * one by one.
     *
     * Java introduced Enumeration for this purpose.
     *
     * Later, Java introduced the Collection Framework and Iterator,
     * which provided a more flexible traversal mechanism.
     *
     * Enumeration is still supported for backward compatibility.
     *
     *
     * ============================================================
     * 2. WHAT IS ENUMERATION?
     * ============================================================
     *
     * Enumeration is an interface available in java.util.
     *
     * Declaration:
     *
     * public interface Enumeration<E>
     *
     * E represents the type of elements being traversed.
     *
     * Enumeration allows us to access elements one by one.
     *
     * It provides only two methods:
     *
     * 1. hasMoreElements()
     * 2. nextElement()
     *
     * Important:
     *
     * Enumeration is a legacy interface.
     *
     * It is not a replacement for modern Iterator.
     *
     *
     * ============================================================
     * 3. METHODS OF ENUMERATION
     * ============================================================
     *
     * Enumeration provides two methods.
     *
     *
     * ------------------------------------------------------------
     * 3.1 hasMoreElements()
     * ------------------------------------------------------------
     *
     * Declaration:
     *
     * boolean hasMoreElements();
     *
     * Purpose:
     * Checks whether more elements are available.
     *
     * Returns:
     *
     * true  -> More elements are available.
     * false -> No more elements are available.
     *
     *
     * ------------------------------------------------------------
     * 3.2 nextElement()
     * ------------------------------------------------------------
     *
     * Declaration:
     *
     * E nextElement();
     *
     * Purpose:
     * Returns the next element in the enumeration.
     *
     * If no element is available, it throws:
     *
     * NoSuchElementException
     *
     *
     * ============================================================
     * 4. HOW TO CREATE AN ENUMERATION?
     * ============================================================
     *
     * Enumeration is commonly obtained from legacy classes.
     *
     * Example using Vector:
     *
     * Vector<String> names = new Vector<>();
     *
     * names.add("Vinay");
     * names.add("Rahul");
     * names.add("Arjun");
     *
     * Enumeration<String> enumeration = names.elements();
     *
     * The elements() method returns an Enumeration of the
     * elements in the Vector.
     *
     *
     * ============================================================
     * 5. TRAVERSING ELEMENTS USING ENUMERATION
     * ============================================================
     *
     * Example:
     *
     * Vector<Integer> numbers = new Vector<>();
     *
     * numbers.add(10);
     * numbers.add(20);
     * numbers.add(30);
     *
     * Enumeration<Integer> e = numbers.elements();
     *
     * while (e.hasMoreElements()) {
     *
     *     Integer number = e.nextElement();
     *
     *     System.out.println(number);
     * }
     *
     * Output:
     *
     * 10
     * 20
     * 30
     *
     * Explanation:
     *
     * 1. elements() obtains an Enumeration.
     *
     * 2. hasMoreElements() checks whether another element exists.
     *
     * 3. nextElement() retrieves the next element.
     *
     * 4. The loop stops when no elements remain.
     *
     *
     * ============================================================
     * 6. ENUMERATION WITH HASHTABLE
     * ============================================================
     *
     * Hashtable is another legacy collection class.
     *
     * It provides methods such as:
     *
     * - keys()
     * - elements()
     *
     * keys():
     * Returns an Enumeration of the keys.
     *
     * elements():
     * Returns an Enumeration of the values.
     *
     * Example:
     *
     * Hashtable<Integer, String> students = new Hashtable<>();
     *
     * students.put(1, "Vinay");
     * students.put(2, "Rahul");
     * students.put(3, "Arjun");
     *
     * Enumeration<Integer> keys = students.keys();
     *
     * while (keys.hasMoreElements()) {
     *     System.out.println(keys.nextElement());
     * }
     *
     * Enumeration<String> values = students.elements();
     *
     * while (values.hasMoreElements()) {
     *     System.out.println(values.nextElement());
     * }
     *
     * Important:
     * Hashtable does not guarantee a particular iteration order.
     *
     *
     * ============================================================
     * 7. ENUMERATION VS ITERATOR
     * ============================================================
     *
     * Enumeration:
     *
     * - Legacy traversal interface.
     * - Introduced in early Java.
     * - Provides hasMoreElements().
     * - Provides nextElement().
     * - Does not provide remove().
     * - Mainly used with legacy APIs.
     * - Supports forward traversal only.
     *
     * Iterator:
     *
     * - Modern traversal interface.
     * - Introduced with the Collection Framework in Java 1.2.
     * - Provides hasNext().
     * - Provides next().
     * - Provides optional remove().
     * - Provides forEachRemaining() since Java 8.
     * - Used throughout the modern Collection Framework.
     * - Supports forward traversal only.
     *
     * Remember:
     *
     * Enumeration:
     * hasMoreElements() + nextElement()
     *
     * Iterator:
     * hasNext() + next()
     *
     *
     * ============================================================
     * 8. ENUMERATION VS ITERATOR VS LISTITERATOR
     * ============================================================
     *
     * Enumeration:
     *
     * - Legacy traversal mechanism.
     * - Forward traversal.
     * - No removal method.
     * - Used with legacy APIs.
     *
     * Iterator:
     *
     * - Modern general-purpose traversal mechanism.
     * - Forward traversal.
     * - Optional removal.
     * - Works with Collection implementations.
     *
     * ListIterator:
     *
     * - Specialized iterator for Lists.
     * - Forward and backward traversal.
     * - Supports add(), set(), and remove().
     *
     *
     * ============================================================
     * 9. CAN ENUMERATION MODIFY COLLECTIONS?
     * ============================================================
     *
     * Enumeration does not provide a remove() method.
     *
     * Therefore, elements cannot be removed through the
     * Enumeration itself.
     *
     * However, this does not mean the underlying collection
     * cannot be modified through another reference.
     *
     * Modifying a collection while an Enumeration is being
     * used may produce implementation-specific results.
     *
     * Do not assume that Enumeration provides fail-fast
     * behavior or safe concurrent traversal.
     *
     *
     * ============================================================
     * 10. IS ENUMERATION THREAD-SAFE?
     * ============================================================
     *
     * Enumeration itself does not guarantee thread safety.
     *
     * Vector and Hashtable are legacy synchronized classes.
     *
     * However, synchronization of individual collection methods
     * does not automatically make an entire traversal atomic.
     *
     * For example, another thread may modify the collection
     * between calls to hasMoreElements() and nextElement().
     *
     * Use appropriate synchronization or modern concurrent
     * collections when concurrent access is required.
     *
     *
     * ============================================================
     * 11. WHERE IS ENUMERATION USED TODAY?
     * ============================================================
     *
     * Enumeration is still found in some Java APIs.
     *
     * Examples:
     *
     * 1. Vector.elements()
     *
     * 2. Hashtable.keys()
     *
     * 3. Hashtable.elements()
     *
     * 4. Properties.propertyNames()
     *
     * 5. Class.getEnumConstants() does NOT return Enumeration.
     *
     * Important:
     * java.util.Enumeration and Java enum types are different.
     *
     * Enumeration:
     * An interface for traversing elements.
     *
     * enum:
     * A language feature for defining a fixed set of constants.
     *
     *
     * ============================================================
     * 12. IMPORTANT POINTS TO REMEMBER
     * ============================================================
     *
     * 1. Enumeration belongs to java.util.
     *
     * 2. It is a legacy interface.
     *
     * 3. It was available before the Collection Framework.
     *
     * 4. It provides only two methods:
     *    hasMoreElements() and nextElement().
     *
     * 5. It supports forward traversal.
     *
     * 6. It does not provide remove().
     *
     * 7. Vector.elements() returns Enumeration.
     *
     * 8. Hashtable.keys() returns Enumeration.
     *
     * 9. Hashtable.elements() returns Enumeration.
     *
     * 10. Iterator is generally preferred for modern collections.
     *
     * 11. Enumeration itself does not guarantee thread safety.
     *
     *
     * ============================================================
     * 13. INTERVIEW QUESTIONS
     * ============================================================
     *
     * 1. What is Enumeration in Java?
     *
     * 2. Why is Enumeration called a legacy interface?
     *
     * 3. Which package contains Enumeration?
     *
     * 4. How many methods does Enumeration provide?
     *
     * 5. What is the difference between hasMoreElements()
     *    and hasNext()?
     *
     * 6. What is the difference between Enumeration and Iterator?
     *
     * 7. Can we remove elements using Enumeration?
     *
     * 8. Which Vector method returns Enumeration?
     *
     * 9. Which Hashtable methods return Enumeration?
     *
     * 10. Is Enumeration thread-safe?
     *
     * 11. Is Enumeration the same as Java enum?
     *
     *
     * ============================================================
     * 14. QUICK REVISION
     * ============================================================
     *
     * Enumeration = Legacy traversal interface.
     *
     * hasMoreElements() = Checks whether another element exists.
     *
     * nextElement() = Returns the next element.
     *
     * Vector.elements() = Returns Enumeration.
     *
     * Hashtable.keys() = Enumerates keys.
     *
     * Hashtable.elements() = Enumerates values.
     *
     * Iterator = Preferred modern traversal mechanism.
     *
     * ListIterator = Bidirectional traversal for Lists.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Enumeration , Happy Learning ..!");
    }
}
