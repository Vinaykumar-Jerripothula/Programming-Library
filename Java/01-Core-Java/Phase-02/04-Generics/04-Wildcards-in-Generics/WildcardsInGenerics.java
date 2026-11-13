
public class WildcardsInGenerics {

    /*
     * ============================================================
     *                  WILDCARDS IN GENERICS
     * ============================================================
     *
     * 1. WHY DO WE NEED WILDCARDS?
     *
     * Consider:
     *
     *     List<Integer> integers = new ArrayList<>();
     *
     *     List<Number> numbers = integers; // Compilation error
     *
     * Integer is a subtype of Number.
     *
     * But List<Integer> is NOT a subtype of List<Number>.
     *
     * Why?
     *
     * Imagine Java allowed this assignment:
     *
     *     List<Integer> integers = new ArrayList<>();
     *     List<Number> numbers = integers;
     *
     *     numbers.add(10.5);
     *
     * Now the original List<Integer> contains a Double!
     *
     * This would violate type safety.
     *
     * Therefore, Java treats different parameterizations of
     * the same generic type as invariant.
     *
     * Wildcards provide flexibility when working with
     * different parameterized types.
     *
     *
     * 2. WHAT IS A WILDCARD?
     *
     * A wildcard is represented by the question mark:
     *
     *     ?
     *
     * It represents an unknown type.
     *
     * Example:
     *
     *     List<?> values;
     *
     * This means values is a List of some unknown reference type.
     *
     * It could refer to:
     *
     *     List<String>
     *     List<Integer>
     *     List<Double>
     *     List<Employee>
     *
     * The exact type is unknown to the code using List<?>.
     *
     *
     * 3. UNBOUNDED WILDCARD (?)
     *
     * Syntax:
     *
     *     List<?>
     *
     * It means a List of an unknown type.
     *
     * Example:
     *
     *     public void display(List<?> list) {
     *
     *         for (Object value : list) {
     *             System.out.println(value);
     *         }
     *     }
     *
     * Usage:
     *
     *     display(List.of("Java", "Python"));
     *     display(List.of(10, 20, 30));
     *
     *     display(List.of(10.5, 20.5));
     *
     * The same method can accept lists with different
     * element types.
     *
     * Important:
     *
     * When reading from List<?>, the result is available
     * as Object because the exact element type is unknown.
     *
     *
     * 4. WHAT CAN WE ADD TO List<?>?
     *
     * Consider:
     *
     *     List<?> list = new ArrayList<String>();
     *
     * We cannot normally add a value:
     *
     *     list.add("Java"); // Compilation error
     *     list.add(100);    // Compilation error
     *
     * Why?
     *
     * The compiler does not know the actual element type.
     *
     * It might be List<String>, List<Integer>, or another type.
     *
     * Adding an arbitrary value could violate type safety.
     *
     * However, we can add null:
     *
     *     list.add(null); // Valid
     *
     * Because null is compatible with every reference type.
     *
     * We can also remove elements:
     *
     *     list.remove(0);
     *     list.clear();
     *
     * Reading is allowed:
     *
     *     Object value = list.get(0);
     *
     *
     * 5. UNBOUNDED WILDCARD VS List<Object>
     *
     * These two declarations are different:
     *
     *     List<?> list1;
     *     List<Object> list2;
     *
     * List<Object> means a List whose element type is Object.
     *
     * It can hold values of different reference types:
     *
     *     List<Object> values = new ArrayList<>();
     *
     *     values.add("Java");
     *     values.add(100);
     *     values.add(10.5);
     *
     * List<?> means a List of some unknown type.
     *
     * It can refer to a List<String>, List<Integer>,
     * List<Double>, etc.
     *
     * Example:
     *
     *     List<String> names = new ArrayList<>();
     *
     *     List<?> unknown = names;       // Valid
     *     List<Object> objects = names; // Invalid
     *
     * Remember:
     *
     * List<Object> = Known element type: Object.
     * List<?>      = Unknown element type.
     *
     *
     * 6. UPPER-BOUNDED WILDCARD (? extends T)
     *
     * Syntax:
     *
     *     List<? extends Number>
     *
     * It means a List whose element type is Number
     * or a subtype of Number.
     *
     * Possible types:
     *
     *     List<Number>
     *     List<Integer>
     *     List<Double>
     *     List<Float>
     *
     * Example:
     *
     *     public void displayNumbers(List<? extends Number> list) {
     *
     *         for (Number number : list) {
     *             System.out.println(number);
     *         }
     *     }
     *
     * Usage:
     *
     *     List<Integer> integers = List.of(10, 20, 30);
     *     List<Double> decimals = List.of(10.5, 20.5);
     *
     *     displayNumbers(integers);
     *     displayNumbers(decimals);
     *
     * Why can we read values as Number?
     *
     * Because every possible element type accepted by this
     * declaration is Number or a subtype of Number.
     *
     * Therefore, each element can safely be treated as Number.
     *
     *
     * 7. WHY IS IT CALLED AN UPPER BOUND?
     *
     * Consider:
     *
     *     ? extends Number
     *
     * Number is the upper bound.
     *
     * The unknown type must be Number or a subtype of Number.
     *
     * The compiler guarantees that the unknown type is
     * within that upper limit.
     *
     * Important:
     *
     * extends in a wildcard declaration does not mean
     * that only classes can be used.
     *
     * It also works with interface bounds.
     *
     * Example:
     *
     *     List<? extends Comparable<?>>
     *
     *
     * 8. WHAT CAN WE ADD TO List<? extends Number>?
     *
     * Consider:
     *
     *     List<? extends Number> numbers;
     *
     * It could refer to:
     *
     *     List<Integer>
     *     List<Double>
     *     List<Number>
     *
     * Can we add an Integer?
     *
     *     numbers.add(10); // Compilation error
     *
     * Can we add a Double?
     *
     *     numbers.add(10.5); // Compilation error
     *
     * Why?
     *
     * The compiler does not know the exact element type.
     *
     * If numbers actually refers to List<Integer>,
     * inserting a Double would be unsafe.
     *
     * Therefore, adding a non-null element is not allowed.
     *
     * We can still:
     *
     *     Number value = numbers.get(0);
     *     numbers.remove(0);
     *     numbers.clear();
     *
     * And:
     *
     *     numbers.add(null);
     *
     * is allowed.
     *
     *
     * 9. LOWER-BOUNDED WILDCARD (? super T)
     *
     * Syntax:
     *
     *     List<? super Integer>
     *
     * It means a List whose element type is Integer
     * or a supertype of Integer.
     *
     * Possible types:
     *
     *     List<Integer>
     *     List<Number>
     *     List<Object>
     *
     * Example:
     *
     *     public void addNumbers(List<? super Integer> list) {
     *
     *         list.add(10);
     *         list.add(20);
     *         list.add(30);
     *     }
     *
     * Usage:
     *
     *     List<Integer> integers = new ArrayList<>();
     *     List<Number> numbers = new ArrayList<>();
     *     List<Object> objects = new ArrayList<>();
     *
     *     addNumbers(integers);
     *     addNumbers(numbers);
     *     addNumbers(objects);
     *
     * Why is adding Integer safe?
     *
     * Every accepted list has an element type that can
     * hold an Integer.
     *
     * Integer itself, Number, and Object can all store
     * Integer values.
     *
     *
     * 10. WHY IS IT CALLED A LOWER BOUND?
     *
     * Consider:
     *
     *     ? super Integer
     *
     * Integer is the lower bound.
     *
     * The unknown type must be Integer or a supertype of Integer.
     *
     * The compiler guarantees that an Integer can safely
     * be inserted into the list.
     *
     *
     * 11. WHAT CAN WE READ FROM List<? super Integer>?
     *
     * Consider:
     *
     *     List<? super Integer> list;
     *
     * It could refer to:
     *
     *     List<Integer>
     *     List<Number>
     *     List<Object>
     *
     * What is the exact type returned by get()?
     *
     * It is only guaranteed to be Object.
     *
     * Example:
     *
     *     Object value = list.get(0); // Valid
     *
     *     Integer number = list.get(0); // Compilation error
     *
     * Why?
     *
     * If the actual list is List<Object>, an element
     * retrieved from it is not guaranteed to be Integer.
     *
     * Therefore, Object is the safe reading type.
     *
     *
     * 12. COMPARISON OF THREE WILDCARDS
     *
     * UNBOUNDED:
     *
     *     List<?>
     *
     * Accepts a List of any reference type.
     * Read as Object.
     * Cannot add non-null values.
     *
     * UPPER BOUNDED:
     *
     *     List<? extends Number>
     *
     * Accepts Number or its subtypes.
     * Read as Number.
     * Cannot add non-null values.
     *
     * LOWER BOUNDED:
     *
     *     List<? super Integer>
     *
     * Accepts Integer or its supertypes.
     * Can safely add Integer values.
     * Read as Object.
     *
     *
     * 13. PECS PRINCIPLE
     *
     * PECS stands for:
     *
     *     Producer Extends, Consumer Super.
     *
     * This is a useful rule for choosing wildcard bounds.
     *
     * PRODUCER:
     *
     * If a collection produces values for your code to read,
     * use an upper-bounded wildcard.
     *
     *     ? extends T
     *
     * Example:
     *
     *     public double sum(List<? extends Number> numbers) {
     *
     *         double total = 0;
     *
     *         for (Number number : numbers) {
     *             total += number.doubleValue();
     *         }
     *
     *         return total;
     *     }
     *
     * The list produces Number values for the method.
     *
     * CONSUMER:
     *
     * If your code inserts values into a collection,
     * use a lower-bounded wildcard.
     *
     *     ? super T
     *
     * Example:
     *
     *     public void addIntegers(List<? super Integer> list) {
     *         list.add(10);
     *         list.add(20);
     *     }
     *
     * The list consumes Integer values from the method.
     *
     * Remember:
     *
     * Producer -> extends
     * Consumer -> super
     *
     *
     * 14. PECS WITH COPY OPERATION
     *
     * Imagine copying values from one list to another.
     *
     * Source produces values.
     * Destination consumes values.
     *
     * Example:
     *
     *     public static <T> void copy(
     *             List<? super T> destination,
     *             List<? extends T> source) {
     *
     *         for (T value : source) {
     *             destination.add(value);
     *         }
     *     }
     *
     * Usage:
     *
     *     List<Integer> source = List.of(10, 20, 30);
     *     List<Number> destination = new ArrayList<>();
     *
     *     copy(destination, source);
     *
     * Why does this work?
     *
     * Source:
     *     ? extends T
     *
     * It produces values that can be treated as T.
     *
     * Destination:
     *     ? super T
     *
     * It can safely consume T values.
     *
     * This is a classic PECS example.
     *
     *
     * 15. WILDCARD VS TYPE PARAMETER
     *
     * Consider two methods:
     *
     *     void display(List<?> list) { }
     *
     *     <T> void display(List<T> list) { }
     *
     * Both can accept lists of different element types.
     *
     * But there is an important difference.
     *
     * With List<?>:
     *
     * The exact element type is unknown.
     * We do not give it a name that can be reused elsewhere
     * in the method declaration.
     *
     * With List<T>:
     *
     * T names the element type.
     * The same T can be used in multiple parameters
     * and the return type.
     *
     * Example:
     *
     *     public <T> T first(List<T> list) {
     *         return list.get(0);
     *     }
     *
     * The return type is linked to the list's element type.
     *
     * A wildcard is useful when that relationship
     * does not need to be named.
     *
     *
     * 16. WILDCARDS WITH MULTIPLE PARAMETERS
     *
     * A generic type can contain wildcards in multiple positions.
     *
     * Example:
     *
     *     Map<? extends Number, ? extends CharSequence> map;
     *
     * This accepts Maps whose:
     *
     * Key type is Number or a subtype.
     * Value type is CharSequence or a subtype.
     *
     * Another example:
     *
     *     Map<String, ? super Integer> map;
     *
     * The key type must be String.
     * The value type must be Integer or a supertype of Integer.
     *
     *
     * 17. WILDCARDS WITH COLLECTIONS
     *
     * Wildcards are heavily used in Java Collections.
     *
     * Examples:
     *
     *     Collection<?> values;
     *
     *     List<? extends Number> numbers;
     *
     *     List<? super Integer> destination;
     *
     *     Comparator<? super String> comparator;
     *
     *     Consumer<? super String> consumer;
     *
     *     Function<? super String, ? extends Number> function;
     *
     * These declarations allow APIs to work with
     * compatible types instead of requiring exact matches.
     *
     *
     * 18. WILDCARDS IN COMPARATORS
     *
     * Consider:
     *
     *     Comparator<? super String> comparator;
     *
     * A Comparator<String> can compare String values.
     *
     * A Comparator<Object> can also compare String values,
     * because String is an Object.
     *
     * Therefore, a comparator accepting String or a supertype
     * of String can be useful.
     *
     * This is why many Java APIs use lower-bounded wildcards
     * for consumers.
     *
     *
     * 19. WILDCARD CAPTURE
     *
     * Wildcard capture occurs when the compiler assigns
     * an internal type variable to an unknown wildcard type.
     *
     * Consider:
     *
     *     void display(List<?> list) {
     *         // ...
     *     }
     *
     * The compiler knows that the list has some specific
     * element type, even though the method does not know
     * what that type is.
     *
     * It can internally represent that unknown type
     * using a captured type variable.
     *
     * A helper method can sometimes work with that captured type.
     *
     * Example:
     *
     *     public void reverseFirst(List<?> list) {
     *         reverseHelper(list);
     *     }
     *
     *     private <T> void reverseHelper(List<T> list) {
     *
     *         if (!list.isEmpty()) {
     *             T value = list.get(0);
     *             list.set(0, value);
     *         }
     *     }
     *
     * The compiler captures the unknown type and infers T
     * for the helper invocation.
     *
     * This example demonstrates the technique; the operation
     * itself leaves the list unchanged.
     *
     * Wildcard capture is useful when an operation needs
     * to preserve the relationship between values of
     * an otherwise unknown type.
     *
     *
     * 20. WILDCARDS CANNOT BE USED EVERYWHERE
     *
     * Wildcards are allowed in parameterized type arguments.
     *
     * Examples:
     *
     *     List<?> list;
     *     List<? extends Number> numbers;
     *     List<? super Integer> integers;
     *
     * A wildcard cannot be used as a standalone variable type:
     *
     *     ? value; // Invalid
     *
     * It must appear within a permitted generic type context.
     *
     *
     * 21. NESTED WILDCARDS
     *
     * Wildcards can appear inside other generic types.
     *
     * Example:
     *
     *     List<List<? extends Number>> values;
     *
     * This is a List whose elements are lists of Number
     * or a subtype of Number.
     *
     * Another example:
     *
     *     Map<String, List<? super Integer>> data;
     *
     * This is a Map with String keys and values that are
     * lists accepting Integer or its supertypes.
     *
     * Nested generic declarations can become difficult to read.
     * Use meaningful type aliases through helper classes
     * or methods when appropriate.
     *
     *
     * 22. COMMON MISCONCEPTIONS
     *
     * 1. List<Integer> is not a subtype of List<Number>.
     *
     * 2. List<?> does not mean List<Object>.
     *
     * 3. ? extends Number does not mean that only Number
     *    itself is accepted. Subtypes are accepted too.
     *
     * 4. ? super Integer accepts Integer and its supertypes.
     *
     * 5. An extends wildcard generally prevents adding
     *    non-null values because the exact subtype is unknown.
     *
     * 6. A super wildcard allows safe insertion of values
     *    of its lower-bound type.
     *
     * 7. PECS is a useful guideline, not a rule that every
     *    generic API must follow mechanically.
     *
     * 8. Wildcards do not change the underlying object.
     *    They change the types through which the code
     *    is allowed to use that object.
     *
     *
     * 23. INTERVIEW QUESTIONS
     *
     * Q1. What is a wildcard in Java Generics?
     * Q2. What does List<?> mean?
     * Q3. What is an unbounded wildcard?
     * Q4. What is an upper-bounded wildcard?
     * Q5. What is a lower-bounded wildcard?
     * Q6. Why is List<Integer> not a subtype of List<Number>?
     * Q7. What is the difference between List<?> and List<Object>?
     * Q8. Why can't we add values to List<?>?
     * Q9. Why can we read Number values from List<? extends Number>?
     * Q10. Why can we add Integer values to List<? super Integer>?
     * Q11. What is the PECS principle?
     * Q12. What is the difference between ? extends T and ? super T?
     * Q13. What is wildcard capture?
     * Q14. What is the difference between List<T> and List<?>?
     * Q15. Why do Java APIs use Comparator<? super T>?
     * Q16. Can we add null to a List<?>?
     * Q17. Can wildcards be nested?
     *
     *
     * 24. QUICK REVISION
     *
     * Unbounded:
     *     List<?>
     *
     * Upper bound:
     *     List<? extends Number>
     *
     * Lower bound:
     *     List<? super Integer>
     *
     * Unbounded wildcard:
     * Accepts any parameterized List.
     * Read as Object.
     *
     * Upper-bounded wildcard:
     * Accepts Number or subtypes.
     * Read as Number.
     *
     * Lower-bounded wildcard:
     * Accepts Integer or supertypes.
     * Read as Object.
     * Can safely insert Integer values.
     *
     * PECS:
     * Producer Extends.
     * Consumer Super.
     *
     * List<?>:
     * Unknown element type.
     *
     * List<Object>:
     * Known element type Object.
     *
     * Generic invariance:
     * List<Integer> is not a subtype of List<Number>.
     *
     * Wildcard capture:
     * Compiler gives an unknown wildcard type
     * an internal captured type variable.
     *
     * ============================================================
     *                 END OF WILDCARDS IN GENERICS
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Wildcards in Generics , Happy Learning ..!");
    }
}
