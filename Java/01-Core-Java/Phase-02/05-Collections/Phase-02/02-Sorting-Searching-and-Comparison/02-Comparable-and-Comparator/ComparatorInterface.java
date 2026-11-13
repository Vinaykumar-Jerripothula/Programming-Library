
public class ComparatorInterface {

    /*
     * ============================================================
     * 1. WHY DO WE NEED COMPARATOR?
     * ============================================================
     *
     * Consider an Employee class:
     *
     * class Employee {
     *     int id;
     *     String name;
     *     double salary;
     * }
     *
     * Suppose Employee implements Comparable<Employee> and
     * defines natural ordering by employee ID.
     *
     * Now we have these requirements:
     *
     * 1. Sort by ID.
     * 2. Sort by name.
     * 3. Sort by salary.
     * 4. Sort by salary in descending order.
     * 5. Sort by name and then salary.
     *
     * Should we continuously modify compareTo()?
     *
     * No.
     *
     * A class generally has one natural ordering.
     *
     * For additional sorting requirements, Java provides
     * the Comparator interface.
     *
     *
     * ============================================================
     * 2. WHAT IS COMPARATOR?
     * ============================================================
     *
     * Comparator is a functional interface available in:
     *
     * java.util
     *
     * Declaration:
     *
     * public interface Comparator<T>
     *
     * Its primary abstract method is:
     *
     * int compare(T o1, T o2);
     *
     * Comparator defines an external comparison strategy.
     *
     * Unlike Comparable, we do not need to implement
     * Comparator inside the class whose objects are sorted.
     *
     * We can create separate Comparator objects for
     * different sorting requirements.
     *
     *
     * ============================================================
     * 3. UNDERSTANDING compare()
     * ============================================================
     *
     * Method:
     *
     * int compare(T o1, T o2);
     *
     * It compares two objects.
     *
     * Returns:
     *
     * Negative integer:
     * o1 comes before o2.
     *
     * Zero:
     * o1 and o2 are equal for ordering.
     *
     * Positive integer:
     * o1 comes after o2.
     *
     * Example:
     *
     * Comparator<Integer> comparator =
     *     (a, b) -> Integer.compare(a, b);
     *
     * comparator.compare(10, 20);
     *
     * Result:
     * Negative integer.
     *
     * Explanation:
     * 10 comes before 20.
     *
     * Important:
     * The result does not have to be exactly -1, 0, or 1.
     *
     *
     * ============================================================
     * 4. HOW TO CREATE A COMPARATOR?
     * ============================================================
     *
     * There are multiple ways.
     *
     *
     * ------------------------------------------------------------
     * 4.1 USING AN ANONYMOUS CLASS
     * ------------------------------------------------------------
     *
     * Comparator<Integer> comparator =
     *     new Comparator<Integer>() {
     *
     *         @Override
     *         public int compare(Integer a, Integer b) {
     *             return Integer.compare(a, b);
     *         }
     *     };
     *
     * This uses an anonymous class to provide the comparison rule.
     *
     *
     * ------------------------------------------------------------
     * 4.2 USING A LAMBDA EXPRESSION
     * ------------------------------------------------------------
     *
     * Comparator<Integer> comparator =
     *     (a, b) -> Integer.compare(a, b);
     *
     * This is shorter because Comparator is a functional interface.
     *
     * It has one abstract method: compare().
     *
     *
     * ------------------------------------------------------------
     * 4.3 USING A STATIC FACTORY METHOD
     * ------------------------------------------------------------
     *
     * Comparator<Integer> comparator =
     *     Comparator.naturalOrder();
     *
     * This returns a Comparator that uses natural ordering.
     *
     * For reverse natural ordering:
     *
     * Comparator<Integer> comparator =
     *     Comparator.reverseOrder();
     *
     *
     * ============================================================
     * 5. COMPLETE EXAMPLE: SORTING EMPLOYEES BY NAME
     * ============================================================
     *
     * import java.util.*;
     *
     * class Employee {
     *
     *     int id;
     *     String name;
     *     double salary;
     *
     *     Employee(int id, String name, double salary) {
     *         this.id = id;
     *         this.name = name;
     *         this.salary = salary;
     *     }
     *
     *     @Override
     *     public String toString() {
     *         return id + " - " + name + " - " + salary;
     *     }
     * }
     *
     * public class Main {
     *
     *     public static void main(String[] args) {
     *
     *         List<Employee> employees = new ArrayList<>();
     *
     *         employees.add(
     *             new Employee(103, "Vinay", 30000)
     *         );
     *
     *         employees.add(
     *             new Employee(101, "Rahul", 40000)
     *         );
     *
     *         employees.add(
     *             new Employee(102, "Arjun", 35000)
     *         );
     *
     *         Comparator<Employee> byName =
     *             (e1, e2) -> e1.name.compareTo(e2.name);
     *
     *         employees.sort(byName);
     *
     *         System.out.println(employees);
     *     }
     * }
     *
     * Output:
     *
     * [102 - Arjun - 35000.0,
     *  101 - Rahul - 40000.0,
     *  103 - Vinay - 30000.0]
     *
     *
     * ============================================================
     * 6. ASCENDING AND DESCENDING ORDER
     * ============================================================
     *
     * ASCENDING ORDER:
     *
     * Comparator<Integer> ascending =
     *     (a, b) -> Integer.compare(a, b);
     *
     * Result:
     * [10, 20, 30, 40, 50]
     *
     *
     * DESCENDING ORDER:
     *
     * Comparator<Integer> descending =
     *     (a, b) -> Integer.compare(b, a);
     *
     * Result:
     * [50, 40, 30, 20, 10]
     *
     * Remember:
     *
     * compare(a, b):
     * Ascending.
     *
     * compare(b, a):
     * Descending.
     *
     * You can also use:
     *
     * Comparator.reverseOrder()
     *
     * for reverse natural ordering.
     *
     *
     * ============================================================
     * 7. COMPARATOR.COMPARING()
     * ============================================================
     *
     * Java 8 introduced useful Comparator factory methods.
     *
     * One of the most important is:
     *
     * Comparator.comparing()
     *
     * It creates a Comparator using a key extracted from
     * each object.
     *
     * Example:
     *
     * Comparator<Employee> bySalary =
     *     Comparator.comparing(
     *         employee -> employee.salary
     *     );
     *
     * This compares employees using their salary.
     *
     * With method references:
     *
     * Comparator<Employee> byName =
     *     Comparator.comparing(Employee::getName);
     *
     * This requires a getName() method in Employee.
     *
     * Another example:
     *
     * Comparator<Employee> byId =
     *     Comparator.comparingInt(Employee::getId);
     *
     * Specialized methods include:
     *
     * comparingInt()
     * comparingLong()
     * comparingDouble()
     *
     * These are useful for primitive-valued keys.
     *
     *
     * ============================================================
     * 8. REVERSE ORDERING USING REVERSED()
     * ============================================================
     *
     * Comparator provides:
     *
     * default Comparator<T> reversed();
     *
     * It returns a Comparator with the opposite ordering.
     *
     * Example:
     *
     * Comparator<Integer> ascending =
     *     Integer::compare;
     *
     * Comparator<Integer> descending =
     *     ascending.reversed();
     *
     * Employee example:
     *
     * Comparator<Employee> bySalary =
     *     Comparator.comparingDouble(Employee::getSalary);
     *
     * Comparator<Employee> salaryDescending =
     *     bySalary.reversed();
     *
     * Important:
     *
     * reversed() reverses the entire comparator on which
     * it is called.
     *
     *
     * ============================================================
     * 9. MULTIPLE-LEVEL SORTING USING thenComparing()
     * ============================================================
     *
     * Real-world sorting often involves multiple conditions.
     *
     * Requirement:
     *
     * 1. Salary in descending order.
     * 2. If salary is equal, name in ascending order.
     * 3. If name is equal, ID in ascending order.
     *
     * Comparator<Employee> comparator =
     *     Comparator.comparingDouble(Employee::getSalary)
     *         .reversed()
     *         .thenComparing(Employee::getName)
     *         .thenComparingInt(Employee::getId);
     *
     * Explanation:
     *
     * First:
     * Compare salaries in descending order.
     *
     * If salaries are equal:
     * Compare names alphabetically.
     *
     * If names are also equal:
     * Compare employee IDs.
     *
     * This is called chained or multi-level comparison.
     *
     *
     * ============================================================
     * 10. COMPARATOR.THENCOMPARING()
     * ============================================================
     *
     * Common overloads include:
     *
     * thenComparing(Comparator<? super T> other)
     *
     * thenComparing(Function<? super T, ? extends U> keyExtractor)
     *
     * thenComparingInt(ToIntFunction<? super T> keyExtractor)
     *
     * thenComparingLong(ToLongFunction<? super T> keyExtractor)
     *
     * thenComparingDouble(ToDoubleFunction<? super T> keyExtractor)
     *
     * These methods allow multiple comparison rules to
     * be combined.
     *
     * Important:
     *
     * The next comparison is used only when the previous
     * comparison returns zero.
     *
     *
     * ============================================================
     * 11. NULL HANDLING IN COMPARATOR
     * ============================================================
     *
     * Comparing null values directly can cause
     * NullPointerException.
     *
     * Java provides:
     *
     * Comparator.nullsFirst()
     *
     * Comparator.nullsLast()
     *
     * Example:
     *
     * Comparator<String> comparator =
     *     Comparator.nullsFirst(
     *         Comparator.naturalOrder()
     *     );
     *
     * List<String> names = new ArrayList<>(
     *     Arrays.asList("Vinay", null, "Rahul", "Arjun")
     * );
     *
     * names.sort(comparator);
     *
     * Result:
     *
     * [null, Arjun, Rahul, Vinay]
     *
     * Using nullsLast():
     *
     * Comparator<String> comparator =
     *     Comparator.nullsLast(
     *         Comparator.naturalOrder()
     *     );
     *
     * Result:
     *
     * [Arjun, Rahul, Vinay, null]
     *
     * Important:
     *
     * nullsFirst() and nullsLast() define how null values
     * are ordered relative to non-null values.
     *
     *
     * ============================================================
     * 12. COMPARATOR WITH CASE-INSENSITIVE STRINGS
     * ============================================================
     *
     * Natural String ordering is case-sensitive.
     *
     * Example:
     *
     * List<String> names = new ArrayList<>(
     *     List.of("vinay", "Arjun", "rahul", "Kiran")
     * );
     *
     * To sort without considering case:
     *
     * names.sort(String.CASE_INSENSITIVE_ORDER);
     *
     * This uses a predefined Comparator supplied by String.
     *
     *
     * ============================================================
     * 13. COMPARATOR EQUALITY AND COMPARISON CONTRACT
     * ============================================================
     *
     * Comparator follows a comparison contract similar to
     * Comparable.
     *
     * Important rules:
     *
     * 1. Sign symmetry:
     *
     * The sign of compare(a, b) must be the opposite of
     * compare(b, a).
     *
     *
     * 2. Transitivity:
     *
     * If compare(a, b) > 0 and compare(b, c) > 0,
     * then compare(a, c) must be positive.
     *
     *
     * 3. Consistency of zero comparisons:
     *
     * If compare(a, b) == 0, then a and b must have the
     * same comparison sign against every third object.
     *
     *
     * 4. Consistency with equals:
     *
     * It is recommended, but not mandatory, that:
     *
     * compare(a, b) == 0
     *
     * agrees with:
     *
     * a.equals(b)
     *
     * Comparator also provides:
     *
     * boolean equals(Object obj);
     *
     * This method tests whether two Comparator objects
     * are considered equal. It is not the same as comparing
     * the objects being sorted.
     *
     *
     * ============================================================
     * 14. COMPARATOR WITH TREESET AND TREEMAP
     * ============================================================
     *
     * Comparator is not limited to sorting Lists.
     *
     * It can also define ordering in:
     *
     * - TreeSet
     * - TreeMap
     *
     * Example:
     *
     * TreeSet<Integer> numbers =
     *     new TreeSet<>(Comparator.reverseOrder());
     *
     * numbers.add(10);
     * numbers.add(30);
     * numbers.add(20);
     *
     * Output:
     * [30, 20, 10]
     *
     * Another example:
     *
     * TreeMap<String, Integer> map =
     *     new TreeMap<>(Comparator.reverseOrder());
     *
     * map.put("Apple", 10);
     * map.put("Mango", 20);
     * map.put("Banana", 30);
     *
     * The keys are stored in reverse natural order.
     *
     * Important:
     *
     * In TreeSet and TreeMap, comparison results determine
     * ordering and whether elements or keys are equivalent.
     *
     * If the comparator returns zero, the sorted collection
     * treats the elements or keys as equivalent.
     *
     *
     * ============================================================
     * 15. COMPARATOR VS COMPARABLE
     * ============================================================
     *
     * Comparable:
     *
     * - Available in java.lang.
     * - Implemented by the class being compared.
     * - Defines natural ordering.
     * - Uses compareTo().
     * - Generally represents one default ordering.
     *
     * Comparator:
     *
     * - Available in java.util.
     * - Defines an external comparison strategy.
     * - Uses compare().
     * - Supports multiple sorting strategies.
     * - Can be implemented using lambdas.
     *
     * Example:
     *
     * Employee implements Comparable<Employee>
     *
     * This defines natural ordering by employee ID.
     *
     * Comparator<Employee> bySalary =
     *     Comparator.comparingDouble(Employee::getSalary);
     *
     * This defines a separate ordering by salary.
     *
     *
     * ============================================================
     * 16. JAVA VERSION NOTES
     * ============================================================
     *
     * Java 1.2:
     * Comparator introduced with the Collections Framework.
     *
     * Java 8:
     * Comparator became a functional interface and gained
     * important default and static methods, including:
     *
     * - comparing()
     * - comparingInt()
     * - comparingLong()
     * - comparingDouble()
     * - thenComparing()
     * - reversed()
     * - nullsFirst()
     * - nullsLast()
     * - naturalOrder()
     * - reverseOrder()
     *
     * Java 8 also introduced lambda expressions and method
     * references, making Comparator implementations shorter.
     *
     *
     * ============================================================
     * 17. COMMON MISTAKES
     * ============================================================
     *
     * Mistake 1:
     *
     * return a.id - b.id;
     *
     * Problem:
     * Integer subtraction can overflow.
     *
     * Better:
     *
     * Integer.compare(a.id, b.id);
     *
     *
     * Mistake 2:
     *
     * Reversing only one part of a chained comparator
     * without understanding where reversed() is applied.
     *
     * Better:
     * Build and reverse the intended comparison carefully.
     *
     *
     * Mistake 3:
     *
     * Comparing nullable fields without a null policy.
     *
     * Problem:
     * May cause NullPointerException.
     *
     * Better:
     * Use nullsFirst() or nullsLast().
     *
     *
     * Mistake 4:
     *
     * Returning inconsistent comparison results.
     *
     * Problem:
     * Can cause incorrect ordering or sorting exceptions.
     *
     *
     * Mistake 5:
     *
     * Assuming Comparator is only used with Collections.sort().
     *
     * Correction:
     * Comparator is also used with List.sort(), Arrays.sort(),
     * TreeSet, TreeMap, and many other APIs.
     *
     *
     * ============================================================
     * 18. WHEN SHOULD WE USE COMPARATOR?
     * ============================================================
     *
     * Use Comparator when:
     *
     * 1. You need multiple sorting orders for one class.
     *
     * 2. You cannot modify the original class.
     *
     * 3. Sorting rules depend on business requirements.
     *
     * 4. You want reusable sorting logic.
     *
     * 5. You want to build sorting rules using lambdas.
     *
     * Real-world example:
     *
     * An employee management application may need:
     *
     * - Sort employees by salary.
     * - Sort employees by name.
     * - Sort employees by joining date.
     * - Sort employees by department and salary.
     *
     * Comparator makes these different sorting strategies
     * possible without changing the Employee class.
     *
     *
     * ============================================================
     * 19. IMPORTANT POINTS TO REMEMBER
     * ============================================================
     *
     * 1. Comparator belongs to java.util.
     *
     * 2. Comparator is a functional interface.
     *
     * 3. Its main abstract method is compare().
     *
     * 4. It defines external/custom ordering.
     *
     * 5. Negative means first object comes before second.
     *
     * 6. Zero means equal for ordering.
     *
     * 7. Positive means first object comes after second.
     *
     * 8. Comparator.comparing() extracts a comparison key.
     *
     * 9. comparingInt(), comparingLong(), and comparingDouble()
     *    are useful for primitive-valued keys.
     *
     * 10. reversed() reverses the comparator.
     *
     * 11. thenComparing() adds secondary sorting rules.
     *
     * 12. nullsFirst() and nullsLast() define null handling.
     *
     * 13. Comparator can be used with TreeSet and TreeMap.
     *
     * 14. Comparison results must follow the Comparator contract.
     *
     *
     * ============================================================
     * 20. INTERVIEW QUESTIONS
     * ============================================================
     *
     * 1. What is Comparator in Java?
     *
     * 2. Which package contains Comparator?
     *
     * 3. Why is Comparator a functional interface?
     *
     * 4. What is the difference between compare() and compareTo()?
     *
     * 5. How do you create a Comparator using a lambda?
     *
     * 6. What is Comparator.comparing()?
     *
     * 7. What is the purpose of reversed()?
     *
     * 8. What is the purpose of thenComparing()?
     *
     * 9. How do you sort objects using multiple fields?
     *
     * 10. How do you handle null values in a Comparator?
     *
     * 11. What is the difference between nullsFirst()
     *     and nullsLast()?
     *
     * 12. Can Comparator be used with TreeSet?
     *
     * 13. Can Comparator be used with TreeMap?
     *
     * 14. What happens when Comparator returns zero
     *     for two different objects?
     *
     * 15. What is the difference between Comparable
     *     and Comparator?
     *
     *
     * ============================================================
     * 21. QUICK REVISION
     * ============================================================
     *
     * Comparator:
     * Defines external/custom ordering.
     *
     * Package:
     * java.util
     *
     * Main method:
     * compare(T o1, T o2)
     *
     * Negative:
     * First object comes before second.
     *
     * Zero:
     * Equal for ordering.
     *
     * Positive:
     * First object comes after second.
     *
     * comparing():
     * Creates a Comparator using an extracted key.
     *
     * reversed():
     * Reverses the ordering.
     *
     * thenComparing():
     * Adds secondary comparison rules.
     *
     * nullsFirst():
     * Places null values first.
     *
     * nullsLast():
     * Places null values last.
     *
     * Comparable:
     * Natural ordering inside the class.
     *
     * Comparator:
     * Custom ordering outside the class.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Comparator Interface , Happy Learning ..!");
    }
}
