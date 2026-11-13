
public class ComparableInterface {

    /*
     * ============================================================
     * 1. WHY DO WE NEED COMPARABLE?
     * ============================================================
     *
     * Consider:
     *
     * Integer[] numbers = {50, 10, 40, 20, 30};
     *
     * Arrays.sort(numbers);
     *
     * Output:
     * [10, 20, 30, 40, 50]
     *
     * Java already knows how to compare Integer objects.
     *
     * But consider our own class:
     *
     * class Employee {
     *     int id;
     *     String name;
     *     double salary;
     * }
     *
     * Employee e1 = new Employee(103, "Vinay", 30000);
     * Employee e2 = new Employee(101, "Rahul", 40000);
     * Employee e3 = new Employee(102, "Arjun", 35000);
     *
     * How should Java sort these employees?
     *
     * By ID?
     * By name?
     * By salary?
     *
     * Java cannot automatically know our business requirement.
     *
     * We need to define a default comparison rule.
     *
     * Comparable allows a class to define its own natural ordering.
     *
     *
     * ============================================================
     * 2. WHAT IS COMPARABLE?
     * ============================================================
     *
     * Comparable is an interface available in java.lang.
     *
     * Declaration:
     *
     * public interface Comparable<T>
     *
     * It defines one important method:
     *
     * int compareTo(T other);
     *
     * A class implements Comparable to specify how its objects
     * should be naturally ordered.
     *
     * Example:
     *
     * class Employee implements Comparable<Employee> {
     *
     *     int id;
     *
     *     @Override
     *     public int compareTo(Employee other) {
     *         return Integer.compare(this.id, other.id);
     *     }
     * }
     *
     * Here, employees are naturally ordered by ID.
     *
     * Important:
     *
     * Comparable defines the comparison rule inside the class
     * whose objects are being compared.
     *
     *
     * ============================================================
     * 3. UNDERSTANDING compareTo()
     * ============================================================
     *
     * Method:
     *
     * int compareTo(T other);
     *
     * It compares the current object with another object.
     *
     * It returns:
     *
     * Negative integer:
     * Current object comes before the other object.
     *
     * Zero:
     * Both objects are equal for ordering.
     *
     * Positive integer:
     * Current object comes after the other object.
     *
     * Example:
     *
     * Integer a = 10;
     * Integer b = 20;
     *
     * System.out.println(a.compareTo(b));
     *
     * Result:
     * Negative integer.
     *
     * Explanation:
     * 10 comes before 20.
     *
     * Another example:
     *
     * Integer a = 20;
     * Integer b = 10;
     *
     * System.out.println(a.compareTo(b));
     *
     * Result:
     * Positive integer.
     *
     * Explanation:
     * 20 comes after 10.
     *
     * Another example:
     *
     * Integer a = 10;
     * Integer b = 10;
     *
     * System.out.println(a.compareTo(b));
     *
     * Result:
     * 0
     *
     * Important:
     *
     * compareTo() is not required to return exactly -1, 0, or 1.
     *
     * It can return any negative integer, zero, or any
     * positive integer.
     *
     *
     * ============================================================
     * 4. HOW TO IMPLEMENT COMPARABLE?
     * ============================================================
     *
     * Steps:
     *
     * 1. Implement Comparable<T>.
     *
     * 2. Provide the compareTo() method.
     *
     * 3. Define the natural ordering rule.
     *
     * 4. Use Arrays.sort() or Collections.sort().
     *
     * Example:
     *
     * class Student implements Comparable<Student> {
     *
     *     int rollNumber;
     *     String name;
     *
     *     Student(int rollNumber, String name) {
     *         this.rollNumber = rollNumber;
     *         this.name = name;
     *     }
     *
     *     @Override
     *     public int compareTo(Student other) {
     *         return Integer.compare(
     *             this.rollNumber,
     *             other.rollNumber
     *         );
     *     }
     * }
     *
     * Now Student objects have natural ordering by roll number.
     *
     *
     * ============================================================
     * 5. COMPLETE EXAMPLE: SORTING STUDENTS BY ROLL NUMBER
     * ============================================================
     *
     * import java.util.*;
     *
     * class Student implements Comparable<Student> {
     *
     *     int rollNumber;
     *     String name;
     *
     *     Student(int rollNumber, String name) {
     *         this.rollNumber = rollNumber;
     *         this.name = name;
     *     }
     *
     *     @Override
     *     public int compareTo(Student other) {
     *         return Integer.compare(
     *             this.rollNumber,
     *             other.rollNumber
     *         );
     *     }
     *
     *     @Override
     *     public String toString() {
     *         return rollNumber + " - " + name;
     *     }
     * }
     *
     * public class Main {
     *
     *     public static void main(String[] args) {
     *
     *         List<Student> students = new ArrayList<>();
     *
     *         students.add(new Student(103, "Vinay"));
     *         students.add(new Student(101, "Rahul"));
     *         students.add(new Student(102, "Arjun"));
     *
     *         Collections.sort(students);
     *
     *         System.out.println(students);
     *     }
     * }
     *
     * Output:
     *
     * [101 - Rahul, 102 - Arjun, 103 - Vinay]
     *
     * Explanation:
     *
     * Collections.sort() uses the natural ordering of Student.
     *
     * Student's compareTo() method defines that ordering.
     *
     *
     * ============================================================
     * 6. ASCENDING AND DESCENDING ORDER USING COMPARABLE
     * ============================================================
     *
     * ASCENDING ORDER:
     *
     * @Override
     * public int compareTo(Employee other) {
     *     return Integer.compare(this.id, other.id);
     * }
     *
     * Example:
     *
     * 10, 20, 30, 40
     *
     *
     * DESCENDING ORDER:
     *
     * @Override
     * public int compareTo(Employee other) {
     *     return Integer.compare(other.id, this.id);
     * }
     *
     * Example:
     *
     * 40, 30, 20, 10
     *
     * Remember:
     *
     * compareTo() is called on the current object.
     *
     * this.id compared with other.id:
     * Ascending.
     *
     * other.id compared with this.id:
     * Descending.
     *
     *
     * ============================================================
     * 7. COMPARING DIFFERENT DATA TYPES
     * ============================================================
     *
     * Java wrapper classes already implement Comparable.
     *
     * Examples:
     *
     * Integer:
     * Integer.compare(a, b);
     *
     * Long:
     * Long.compare(a, b);
     *
     * Double:
     * Double.compare(a, b);
     *
     * String:
     * a.compareTo(b);
     *
     * BigDecimal:
     * a.compareTo(b);
     *
     * Example:
     *
     * class Employee implements Comparable<Employee> {
     *
     *     String name;
     *
     *     @Override
     *     public int compareTo(Employee other) {
     *         return this.name.compareTo(other.name);
     *     }
     * }
     *
     * This sorts employees alphabetically by name according
     * to String's natural ordering.
     *
     *
     * ============================================================
     * 8. COMPARABLE WITH MULTIPLE FIELDS
     * ============================================================
     *
     * What if we want to sort employees using multiple fields?
     *
     * Requirement:
     *
     * 1. Salary in descending order.
     * 2. If salary is equal, name in ascending order.
     *
     * Example:
     *
     * Employee 1: Vinay, 30000
     * Employee 2: Rahul, 40000
     * Employee 3: Arjun, 30000
     *
     * Expected order:
     *
     * Rahul, 40000
     * Arjun, 30000
     * Vinay, 30000
     *
     * Implementation:
     *
     * @Override
     * public int compareTo(Employee other) {
     *
     *     int salaryComparison =
     *         Double.compare(other.salary, this.salary);
     *
     *     if (salaryComparison != 0) {
     *         return salaryComparison;
     *     }
     *
     *     return this.name.compareTo(other.name);
     * }
     *
     * Explanation:
     *
     * First:
     * Compare salaries in descending order.
     *
     * If salaries are different:
     * Return the salary comparison.
     *
     * If salaries are equal:
     * Compare names alphabetically.
     *
     * This is called multi-level or multi-field comparison.
     *
     * We can define multiple comparison rules inside compareTo().
     *
     * However, if different parts of an application need
     * different sorting orders, Comparator is usually more
     * suitable.
     *
     *
     * ============================================================
     * 9. COMPARABLE AND COLLECTIONS.SORT()
     * ============================================================
     *
     * Collections.sort() has two important overloads:
     *
     * 1. sort(List<T> list)
     * 2. sort(List<T> list, Comparator<? super T> c)
     *
     * The first overload uses natural ordering.
     *
     * Example:
     *
     * List<Integer> numbers = new ArrayList<>(
     *     List.of(50, 10, 40, 20)
     * );
     *
     * Collections.sort(numbers);
     *
     * Result:
     * [10, 20, 40, 50]
     *
     * For custom objects, the objects must have a valid
     * natural ordering when using the first overload.
     *
     *
     * ============================================================
     * 10. COMPARABLE AND LIST.SORT()
     * ============================================================
     *
     * List provides:
     *
     * default void sort(Comparator<? super E> c);
     *
     * Passing null selects natural ordering.
     *
     * Example:
     *
     * List<Integer> numbers = new ArrayList<>(
     *     List.of(30, 10, 20)
     * );
     *
     * numbers.sort(null);
     *
     * Result:
     * [10, 20, 30]
     *
     * If the elements do not have a valid natural ordering,
     * natural-order sorting cannot be performed.
     *
     *
     * ============================================================
     * 11. COMPARABLE AND TREESET / TREEMAP
     * ============================================================
     *
     * Comparable is not used only for sorting.
     *
     * It is also important for sorted collections.
     *
     * Examples:
     *
     * TreeSet
     * TreeMap
     *
     * These collections need an ordering rule to organize
     * their elements or keys.
     *
     * If no Comparator is supplied, they generally use
     * natural ordering.
     *
     * Example:
     *
     * TreeSet<Integer> numbers = new TreeSet<>();
     *
     * numbers.add(30);
     * numbers.add(10);
     * numbers.add(20);
     *
     * Output:
     * [10, 20, 30]
     *
     * For custom objects, natural ordering must be available
     * unless a Comparator is supplied.
     *
     * Important:
     *
     * TreeSet and TreeMap use comparison results to determine
     * ordering and key uniqueness.
     *
     * If compareTo() returns zero, TreeSet treats the elements
     * as equivalent for set membership.
     *
     * TreeMap treats keys comparing as zero as the same key.
     *
     * This can happen even if equals() returns false.
     *
     *
     * ============================================================
     * 12. COMPARABLE CONTRACT
     * ============================================================
     *
     * The compareTo() method must follow a consistent contract.
     *
     * Important rules:
     *
     * 1. Sign symmetry:
     *
     * If x.compareTo(y) is positive, then
     * y.compareTo(x) must be negative.
     *
     * More generally, the sign of x.compareTo(y) must be
     * the opposite of the sign of y.compareTo(x).
     *
     *
     * 2. Transitivity:
     *
     * If:
     *
     * x.compareTo(y) > 0
     * y.compareTo(z) > 0
     *
     * Then:
     *
     * x.compareTo(z) > 0
     *
     *
     * 3. Consistency of zero comparisons:
     *
     * If:
     *
     * x.compareTo(y) == 0
     *
     * Then x and y must have the same comparison sign
     * against every third object z.
     *
     *
     * 4. Recommended consistency with equals():
     *
     * It is strongly recommended that:
     *
     * x.compareTo(y) == 0
     *
     * agrees with:
     *
     * x.equals(y)
     *
     * However, this consistency is not strictly required
     * by the Comparable contract.
     *
     * If they disagree, sorted collections may behave
     * differently from HashSet or HashMap regarding equality.
     *
     *
     * ============================================================
     * 13. COMPARABLE VS EQUALS
     * ============================================================
     *
     * equals():
     *
     * Checks whether two objects are equal according to
     * the class's equality definition.
     *
     * compareTo():
     *
     * Determines the relative ordering of two objects.
     *
     * Example:
     *
     * Integer a = 10;
     * Integer b = 10;
     *
     * a.equals(b);       // true
     * a.compareTo(b);    // 0
     *
     * However, for a custom class, they do not have to
     * produce matching equality results.
     *
     * Example:
     *
     * Two employees may have the same salary but different IDs.
     *
     * If compareTo() compares only salary, it may return zero.
     *
     * But equals() may compare employee IDs and return false.
     *
     * This is why consistency between compareTo() and equals()
     * is recommended.
     *
     *
     * ============================================================
     * 14. COMMON MISTAKES WHEN IMPLEMENTING COMPARABLE
     * ============================================================
     *
     * Mistake 1:
     *
     * return this.id - other.id;
     *
     * Problem:
     * Integer subtraction can overflow.
     *
     * Better:
     *
     * return Integer.compare(this.id, other.id);
     *
     *
     * Mistake 2:
     *
     * Implementing Comparable without specifying a type:
     *
     * class Employee implements Comparable
     *
     * Problem:
     * This uses a raw type and loses compile-time type safety.
     *
     * Better:
     *
     * class Employee implements Comparable<Employee>
     *
     *
     * Mistake 3:
     *
     * Returning only positive or negative values without
     * considering equality.
     *
     * Problem:
     * Equal comparison values must return zero.
     *
     *
     * Mistake 4:
     *
     * Writing an inconsistent comparison rule.
     *
     * Problem:
     * Can cause unexpected sorting results or
     * IllegalArgumentException in some sorting implementations.
     *
     *
     * Mistake 5:
     *
     * Comparing nullable fields without handling null.
     *
     * Problem:
     * May cause NullPointerException.
     *
     * Better:
     * Define an explicit null-handling rule or use Comparator
     * utilities when appropriate.
     *
     *
     * ============================================================
     * 15. WHEN SHOULD WE USE COMPARABLE?
     * ============================================================
     *
     * Use Comparable when:
     *
     * 1. A class has one meaningful default ordering.
     *
     * 2. Objects should naturally be sorted without supplying
     *    a separate comparison object.
     *
     * 3. You control the class implementation.
     *
     * 4. The same natural ordering is appropriate across
     *    most uses of the class.
     *
     * Example:
     *
     * - Student by roll number.
     * - Product by product ID.
     * - Employee by employee ID.
     * - Version by version order.
     *
     * Prefer Comparator when:
     *
     * 1. You need multiple sorting orders.
     *
     * 2. You cannot modify the original class.
     *
     * 3. Sorting requirements change depending on the situation.
     *
     * 4. You want reusable external comparison strategies.
     *
     * Comparator is covered in the next file.
     *
     *
     * ============================================================
     * 16. IMPORTANT POINTS TO REMEMBER
     * ============================================================
     *
     * 1. Comparable belongs to java.lang.
     *
     * 2. Comparable is a generic interface.
     *
     * 3. It defines natural ordering.
     *
     * 4. It provides compareTo().
     *
     * 5. Negative means before.
     *
     * 6. Zero means equal for ordering.
     *
     * 7. Positive means after.
     *
     * 8. It is implemented by classes such as Integer and String.
     *
     * 9. It is useful for Arrays.sort(), Collections.sort(),
     *    and List.sort(null).
     *
     * 10. It is useful for TreeSet and TreeMap natural ordering.
     *
     * 11. It is recommended that compareTo() be consistent
     *     with equals().
     *
     * 12. Use Integer.compare() instead of subtracting integers.
     *
     *
     * ============================================================
     * 17. INTERVIEW QUESTIONS
     * ============================================================
     *
     * 1. What is Comparable in Java?
     *
     * 2. Which package contains Comparable?
     *
     * 3. What is the purpose of compareTo()?
     *
     * 4. What does a negative, zero, and positive result mean?
     *
     * 5. How do you implement Comparable in a custom class?
     *
     * 6. What is natural ordering?
     *
     * 7. Can we sort custom objects using Collections.sort()
     *    without Comparable?
     *
     * 8. What is the difference between Comparable and equals()?
     *
     * 9. Should compareTo() be consistent with equals()?
     *
     * 10. What happens if compareTo() returns zero for
     *     two unequal objects?
     *
     * 11. Can Comparable define descending order?
     *
     * 12. Can Comparable compare multiple fields?
     *
     * 13. Why should we avoid subtraction in compareTo()?
     *
     * 14. How does TreeSet use Comparable?
     *
     * 15. When should we prefer Comparator over Comparable?
     *
     *
     * ============================================================
     * 18. QUICK REVISION
     * ============================================================
     *
     * Comparable:
     * Defines natural ordering.
     *
     * Package:
     * java.lang
     *
     * Method:
     * compareTo(T other)
     *
     * Negative:
     * Current object comes before the other.
     *
     * Zero:
     * Equal for ordering.
     *
     * Positive:
     * Current object comes after the other.
     *
     * Implementation:
     * class Employee implements Comparable<Employee>
     *
     * Sorting:
     * Arrays.sort(), Collections.sort(), List.sort(null).
     *
     * Sorted collections:
     * TreeSet and TreeMap.
     *
     * Recommended:
     * Keep compareTo() consistent with equals().
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Comparable Interface , Happy Learning ..!");
    }
}
