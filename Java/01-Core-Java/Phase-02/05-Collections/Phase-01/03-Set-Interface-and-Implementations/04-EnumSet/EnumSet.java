
public class EnumSet {

    /*
     * ============================================================
     *                       JAVA ENUMSET
     * ============================================================
     *
     * 1. WHAT IS ENUMSET?
     *
     * Imagine you are developing an employee management system.
     *
     * Every employee has a status:
     *
     *     ACTIVE
     *     INACTIVE
     *     ON_LEAVE
     *     TERMINATED
     *
     * These values are fixed and known in advance.
     *
     * Java provides enum to represent a fixed set of constants.
     *
     * Now imagine you want to maintain a collection of these
     * enum values without duplicates.
     *
     * You could use HashSet, but Java provides a specialized
     * implementation called EnumSet.
     *
     * EnumSet is a high-performance Set implementation
     * specifically designed for enum types.
     *
     * IMPORTANT:
     *
     * - EnumSet can store only enum constants of one enum type.
     * - Does not allow duplicate elements.
     * - Does not allow null.
     * - Maintains enum declaration order.
     * - Is not synchronized by default.
     * - Provides efficient operations using bit vectors.
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
     *     import java.util.EnumSet;
     *     import java.util.Set;
     *
     *
     * ============================================================
     * 3. ENUMSET DECLARATION
     * ============================================================
     *
     * Declaration:
     *
     *     public abstract class EnumSet<E extends Enum<E>>
     *         extends AbstractSet<E>
     *         implements Cloneable, Serializable
     *
     * Notice the bounded type parameter:
     *
     *     E extends Enum<E>
     *
     * This means E must be an enum type.
     *
     * EnumSet is an abstract class, so we cannot directly
     * instantiate it using new EnumSet<>().
     *
     * Instead, we use its static factory methods.
     *
     *
     * ============================================================
     * 4. CREATING AN ENUM
     * ============================================================
     *
     * Example:
     *
     *     enum Day {
     *         MONDAY,
     *         TUESDAY,
     *         WEDNESDAY,
     *         THURSDAY,
     *         FRIDAY,
     *         SATURDAY,
     *         SUNDAY
     *     }
     *
     * Enum constants are fixed values.
     *
     * They are instances of the enum type.
     *
     * We cannot add arbitrary String or Integer values
     * to an EnumSet of Day.
     *
     *
     * ============================================================
     * 5. CREATING ENUMSET USING ALL OF()
     * ============================================================
     *
     * EnumSet.allOf() creates a Set containing every constant
     * of the specified enum type.
     *
     * Example:
     *
     *     EnumSet<Day> days = EnumSet.allOf(Day.class);
     *
     * Result:
     *
     *     [MONDAY, TUESDAY, WEDNESDAY, THURSDAY,
     *      FRIDAY, SATURDAY, SUNDAY]
     *
     * Syntax:
     *
     *     EnumSet.allOf(EnumType.class)
     *
     * Useful when you need all constants of an enum.
     *
     *
     * ============================================================
     * 6. CREATING ENUMSET USING NONE OF()
     * ============================================================
     *
     * EnumSet.noneOf() creates an empty EnumSet for an enum type.
     *
     * Example:
     *
     *     EnumSet<Day> days = EnumSet.noneOf(Day.class);
     *
     * Initially:
     *
     *     []
     *
     * Add elements:
     *
     *     days.add(Day.MONDAY);
     *     days.add(Day.FRIDAY);
     *
     * Result:
     *
     *     [MONDAY, FRIDAY]
     *
     * IMPORTANT:
     *
     * We must provide the enum class because an empty EnumSet
     * has no elements from which its enum type can be inferred.
     *
     *
     * ============================================================
     * 7. CREATING ENUMSET USING OF()
     * ============================================================
     *
     * EnumSet.of() creates an EnumSet containing specified
     * enum constants.
     *
     * Example:
     *
     *     EnumSet<Day> weekends =
     *         EnumSet.of(Day.SATURDAY, Day.SUNDAY);
     *
     * Result:
     *
     *     [SATURDAY, SUNDAY]
     *
     * Other overloads support one or multiple constants.
     *
     * Example:
     *
     *     EnumSet<Day> workingDays =
     *         EnumSet.of(
     *             Day.MONDAY,
     *             Day.TUESDAY,
     *             Day.WEDNESDAY,
     *             Day.THURSDAY,
     *             Day.FRIDAY
     *         );
     *
     *
     * ============================================================
     * 8. CREATING ENUMSET USING RANGE()
     * ============================================================
     *
     * EnumSet.range() creates a Set containing enum constants
     * between two specified constants, inclusive.
     *
     * Example:
     *
     *     EnumSet<Day> weekdays =
     *         EnumSet.range(Day.MONDAY, Day.FRIDAY);
     *
     * Result:
     *
     *     [MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY]
     *
     * IMPORTANT:
     *
     * The starting constant must not come after the ending
     * constant in enum declaration order.
     *
     * Otherwise, IllegalArgumentException is thrown.
     *
     *
     * ============================================================
     * 9. CREATING ENUMSET USING COPY OF()
     * ============================================================
     *
     * EnumSet.copyOf() creates a new EnumSet containing
     * the elements of another collection or EnumSet.
     *
     * Example:
     *
     *     EnumSet<Day> first =
     *         EnumSet.of(Day.MONDAY, Day.FRIDAY);
     *
     *     EnumSet<Day> second = EnumSet.copyOf(first);
     *
     * The second Set contains the same enum constants.
     *
     * It is a separate Set object.
     *
     * IMPORTANT:
     *
     * If the source is an ordinary empty Collection, EnumSet
     * cannot infer its enum type and throws IllegalArgumentException.
     *
     * An empty EnumSet can be copied because its enum type
     * is already known.
     *
     *
     * ============================================================
     * 10. COMPLEMENT OF()
     * ============================================================
     *
     * EnumSet.complementOf() creates a Set containing all enum
     * constants that are not present in the supplied EnumSet.
     *
     * Example:
     *
     *     EnumSet<Day> weekends =
     *         EnumSet.of(Day.SATURDAY, Day.SUNDAY);
     *
     *     EnumSet<Day> weekdays =
     *         EnumSet.complementOf(weekends);
     *
     * Result:
     *
     *     [MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY]
     *
     * Think of complement as:
     *
     *     All enum constants - Existing constants
     *
     *
     * ============================================================
     * 11. HOW DOES ENUMSET WORK INTERNALLY?
     * ============================================================
     *
     * EnumSet is optimized because enum constants are:
     *
     * - Known in advance.
     * - Finite in number.
     * - Associated with ordinal positions.
     *
     * Each enum constant has an ordinal value representing
     * its declaration position.
     *
     * Example:
     *
     *     enum Permission {
     *         READ,    // ordinal 0
     *         WRITE,   // ordinal 1
     *         DELETE   // ordinal 2
     *     }
     *
     * EnumSet can use these ordinal positions as bit positions.
     *
     * Internally, standard OpenJDK EnumSet implementations
     * use bit-vector representations.
     *
     * Two main implementation classes are:
     *
     * 1. RegularEnumSet
     * 2. JumboEnumSet
     *
     * These are internal implementation classes, not public
     * APIs that application code should instantiate.
     *
     *
     * ============================================================
     * 12. WHAT IS A BIT VECTOR?
     * ============================================================
     *
     * A bit vector represents a collection using individual
     * bits.
     *
     * Each bit represents whether an enum constant is present.
     *
     * Example:
     *
     *     enum Permission {
     *         READ, WRITE, DELETE
     *     }
     *
     * Suppose:
     *
     *     READ   = 0
     *     WRITE  = 1
     *     DELETE = 2
     *
     * If READ and DELETE are present:
     *
     *     Bit positions:
     *
     *       DELETE WRITE READ
     *          1     0    1
     *
     * The bit pattern is:
     *
     *       101
     *
     * This compactly represents the selected enum constants.
     *
     * Adding or removing an element can be performed using
     * efficient bitwise operations.
     *
     * This is why EnumSet is generally very memory-efficient
     * and fast.
     *
     *
     * ============================================================
     * 13. REGULARENUMSET
     * ============================================================
     *
     * RegularEnumSet is used for enum types with up to 64
     * constants in standard OpenJDK implementations.
     *
     * It stores the membership information in a single long.
     *
     * A long contains 64 bits.
     *
     * Each bit represents one enum constant.
     *
     * Example:
     *
     *     enum Status {
     *         ACTIVE,
     *         INACTIVE,
     *         PENDING
     *     }
     *
     * Only three bits are needed to represent membership.
     *
     * IMPORTANT:
     *
     * RegularEnumSet is an internal implementation detail.
     *
     *
     * ============================================================
     * 14. JUMBOENUMSET
     * ============================================================
     *
     * What if an enum has more than 64 constants?
     *
     * A single long is not sufficient.
     *
     * Standard OpenJDK uses JumboEnumSet for larger enum types.
     *
     * It stores the membership information using an array
     * of long values.
     *
     * Each long represents 64 bit positions.
     *
     * Example:
     *
     *     130 enum constants
     *
     * Approximately three long values are required:
     *
     *     First long  -> 64 constants
     *     Second long -> 64 constants
     *     Third long  -> 2 constants
     *
     * IMPORTANT:
     *
     * This implementation choice is specific to OpenJDK.
     *
     *
     * ============================================================
     * 15. ENUM ORDINAL()
     * ============================================================
     *
     * Every enum constant has an ordinal position.
     *
     * Example:
     *
     *     enum Level {
     *         LOW,
     *         MEDIUM,
     *         HIGH
     *     }
     *
     *     Level.LOW.ordinal();    // 0
     *     Level.MEDIUM.ordinal(); // 1
     *     Level.HIGH.ordinal();   // 2
     *
     * EnumSet uses ordinal positions internally in standard
     * OpenJDK implementations.
     *
     * IMPORTANT:
     *
     * Do not use ordinal() as a permanent business identifier
     * or store it as a database value unless you have a very
     * specific reason and understand the risks.
     *
     * Reordering enum constants changes their ordinal values.
     *
     *
     * ============================================================
     * 16. ENUMSET ITERATION ORDER
     * ============================================================
     *
     * EnumSet iterates in enum declaration order.
     *
     * Example:
     *
     *     enum Color {
     *         RED,
     *         GREEN,
     *         BLUE
     *     }
     *
     *     EnumSet<Color> colors =
     *         EnumSet.of(Color.BLUE, Color.RED, Color.GREEN);
     *
     * Iteration order:
     *
     *     RED
     *     GREEN
     *     BLUE
     *
     * Even though elements were inserted in a different order,
     * iteration follows enum declaration order.
     *
     *
     * ============================================================
     * 17. DUPLICATE ELEMENTS
     * ============================================================
     *
     * EnumSet does not allow duplicates.
     *
     * Example:
     *
     *     EnumSet<Day> days = EnumSet.noneOf(Day.class);
     *
     *     days.add(Day.MONDAY); // true
     *     days.add(Day.MONDAY); // false
     *
     * Each enum constant is a unique instance of its enum type.
     *
     *
     * ============================================================
     * 18. NULL ELEMENTS
     * ============================================================
     *
     * EnumSet does not allow null elements.
     *
     * Example:
     *
     *     EnumSet<Day> days = EnumSet.noneOf(Day.class);
     *
     *     days.add(null); // NullPointerException
     *
     * This is part of the EnumSet contract.
     *
     *
     * ============================================================
     * 19. ENUMSET AND THREAD SAFETY
     * ============================================================
     *
     * EnumSet is not synchronized by default.
     *
     * Concurrent modification from multiple threads requires
     * appropriate synchronization.
     *
     * A synchronized wrapper can be used:
     *
     *     Set<Day> days =
     *         Collections.synchronizedSet(
     *             EnumSet.noneOf(Day.class)
     *         );
     *
     * During iteration, synchronize on the returned Set:
     *
     *     synchronized (days) {
     *         for (Day day : days) {
     *             System.out.println(day);
     *         }
     *     }
     *
     *
     * ============================================================
     * 20. ENUMSET VS HASHSET
     * ============================================================
     *
     * EnumSet:
     *
     * - Works only with enum constants.
     * - Uses a specialized bit-vector representation.
     * - Maintains enum declaration order.
     * - Does not allow null.
     * - Usually has very low memory overhead.
     *
     * HashSet:
     *
     * - Works with general object types.
     * - Uses hashing.
     * - Does not guarantee iteration order.
     * - Allows one null element.
     *
     * Choose EnumSet when all elements belong to one enum type.
     *
     * Choose HashSet when you need a general-purpose Set.
     *
     *
     * ============================================================
     * 21. ENUMSET VS ENUMMAP
     * ============================================================
     *
     * EnumSet:
     *
     *     Stores a collection of enum constants.
     *
     * EnumMap:
     *
     *     Stores key-value pairs where keys are enum constants.
     *
     * Example:
     *
     *     EnumSet<Day> holidays;
     *
     *     EnumMap<Day, String> events;
     *
     * Think:
     *
     *     EnumSet -> Enum values only.
     *
     *     EnumMap -> Enum keys mapped to values.
     *
     *
     * ============================================================
     * 22. COMMON USE CASES
     * ============================================================
     *
     * 1. User permissions:
     *
     *     READ, WRITE, DELETE
     *
     * 2. Employee statuses:
     *
     *     ACTIVE, INACTIVE, ON_LEAVE
     *
     * 3. Days of the week.
     *
     * 4. Supported application features.
     *
     * 5. Workflow states.
     *
     * 6. Feature flags represented by enum constants.
     *
     * 7. Selecting multiple options from a fixed set.
     *
     *
     * ============================================================
     * 23. COMMON MISTAKES
     * ============================================================
     *
     * 1. Trying to instantiate EnumSet using new.
     *    It is abstract; use its factory methods.
     *
     * 2. Trying to store Strings or Integers in EnumSet.
     *    It accepts only one enum type.
     *
     * 3. Assuming insertion order is maintained.
     *    It follows enum declaration order.
     *
     * 4. Assuming null is allowed.
     *    Null is rejected.
     *
     * 5. Using ordinal() as a permanent identifier.
     *    Enum declaration changes can change ordinal values.
     *
     * 6. Assuming EnumSet is thread-safe.
     *    It is not.
     *
     *
     * ============================================================
     * 24. INTERVIEW QUICK REVISION
     * ============================================================
     *
     * Q1. What is EnumSet?
     * A specialized Set implementation for enum constants.
     *
     * Q2. Can EnumSet store normal Strings?
     * No.
     *
     * Q3. Can EnumSet store constants from different enum types?
     * No. One EnumSet is associated with one enum type.
     *
     * Q4. Does EnumSet allow null?
     * No.
     *
     * Q5. Does EnumSet allow duplicates?
     * No.
     *
     * Q6. Does EnumSet maintain insertion order?
     * No. It follows enum declaration order.
     *
     * Q7. How does EnumSet work internally?
     * Standard OpenJDK implementations use bit vectors.
     *
     * Q8. What is RegularEnumSet?
     * An internal implementation using a single long for
     * enum types with up to 64 constants.
     *
     * Q9. What is JumboEnumSet?
     * An internal implementation using an array of longs
     * for larger enum types.
     *
     * Q10. Is EnumSet thread-safe?
     * No.
     *
     * Q11. What is EnumSet.allOf()?
     * Creates a Set containing all constants of an enum type.
     *
     * Q12. What is EnumSet.noneOf()?
     * Creates an empty EnumSet for a specified enum type.
     *
     * Q13. What is EnumSet.range()?
     * Creates an EnumSet containing constants between two
     * specified enum constants, inclusive.
     *
     * Q14. What is the difference between EnumSet and EnumMap?
     * EnumSet stores enum constants; EnumMap maps enum keys
     * to values.
     *
     *
     * ============================================================
     * 25. FINAL UNDERSTANDING
     * ============================================================
     *
     * Remember:
     *
     * HashSet:
     *     General-purpose uniqueness using hashing.
     *
     * LinkedHashSet:
     *     Uniqueness + insertion order.
     *
     * TreeSet:
     *     Uniqueness + sorted order.
     *
     * EnumSet:
     *     Uniqueness + specialized enum storage.
     *
     * EnumSet is usually the best choice when you need a
     * collection of constants belonging to one enum type.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about EnumSet , Happy Learning ..!");
    }
}
