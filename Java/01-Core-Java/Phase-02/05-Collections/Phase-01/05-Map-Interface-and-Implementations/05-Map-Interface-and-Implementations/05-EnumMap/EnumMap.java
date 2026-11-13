
public class EnumMap {

    /*
     * ============================================================
     *                       ENUMMAP IN JAVA
     * ============================================================
     *
     * 1. WHAT IS ENUMMAP?
     *
     * EnumMap is a specialized Map implementation designed
     * specifically for enum keys.
     *
     * It belongs to java.util.
     *
     * Every key in an EnumMap must belong to the same enum type.
     *
     * Example:
     *
     * enum Day {
     *     MONDAY, TUESDAY, WEDNESDAY, THURSDAY,
     *     FRIDAY, SATURDAY, SUNDAY
     * }
     *
     * EnumMap<Day, String> schedule =
     *     new EnumMap<>(Day.class);
     *
     * schedule.put(Day.MONDAY, "Java");
     * schedule.put(Day.TUESDAY, "SQL");
     * schedule.put(Day.WEDNESDAY, "Spring Boot");
     *
     * Here:
     *
     * Key   -> Day enum constants
     * Value -> String
     *
     * You cannot use arbitrary String or Integer keys.
     *
     *
     * 2. WHY DO WE NEED ENUMMAP?
     *
     * Consider an application that stores the working status
     * of each day.
     *
     * enum Day {
     *     MONDAY, TUESDAY, WEDNESDAY
     * }
     *
     * We could use HashMap:
     *
     * Map<Day, String> map = new HashMap<>();
     *
     * But when we know that every key is an enum constant,
     * EnumMap is a better specialized choice.
     *
     * Benefits:
     *
     * - Compact internal representation.
     * - Efficient lookup and update operations.
     * - Predictable iteration order.
     * - Type-safe enum keys.
     *
     *
     * 3. CREATING AN ENUMMAP
     *
     * First define an enum:
     *
     * enum Department {
     *     HR, IT, FINANCE, SALES
     * }
     *
     * Import:
     *
     * import java.util.EnumMap;
     *
     * Create EnumMap:
     *
     * EnumMap<Department, String> map =
     *     new EnumMap<>(Department.class);
     *
     * The enum class is passed to the constructor.
     *
     * Why?
     *
     * It tells EnumMap which enum type is allowed for keys.
     *
     *
     * 4. DIFFERENT ENUMMAP CONSTRUCTORS
     *
     * Constructor 1:
     *
     * EnumMap<Day, String> map =
     *     new EnumMap<>(Day.class);
     *
     * Creates an empty EnumMap for the specified enum type.
     *
     * Constructor 2:
     *
     * EnumMap<Day, String> map =
     *     new EnumMap<>(anotherEnumMap);
     *
     * Creates an EnumMap containing mappings from another
     * EnumMap of the same key type.
     *
     * Constructor 3:
     *
     * EnumMap<Day, String> map =
     *     new EnumMap<>(anotherMap);
     *
     * Creates an EnumMap from another Map.
     *
     * The source Map must be non-empty, or it must itself be
     * an EnumMap, because an empty ordinary Map does not reveal
     * the enum key type.
     *
     *
     * 5. INTERNAL DATA STRUCTURE
     *
     * EnumMap uses an ARRAY internally.
     *
     * This is its most important internal characteristic.
     *
     * Every enum constant has an ordinal value.
     *
     * ordinal() returns the position of an enum constant,
     * starting from zero.
     *
     * Example:
     *
     * enum Day {
     *     MONDAY, TUESDAY, WEDNESDAY, THURSDAY
     * }
     *
     * MONDAY    -> ordinal 0
     * TUESDAY   -> ordinal 1
     * WEDNESDAY -> ordinal 2
     * THURSDAY  -> ordinal 3
     *
     * EnumMap uses these ordinal positions to manage
     * its internal array.
     *
     * Conceptual representation:
     *
     * Index       0        1          2
     *          --------------------------------
     * Value      Java     SQL        Spring
     *
     * Key       MONDAY   TUESDAY   WEDNESDAY
     *
     * This is a simplified illustration of its storage.
     *
     * It does not need a general-purpose hash table for enum keys.
     *
     *
     * 6. WHY IS ENUMMAP EFFICIENT?
     *
     * EnumMap knows the complete set of possible keys
     * because enum constants are fixed.
     *
     * It can use ordinal positions to access the corresponding
     * array slots.
     *
     * This avoids general-purpose hashing for key lookup.
     *
     * Advantages:
     *
     * - Compact storage.
     * - Low memory overhead.
     * - Fast key lookup.
     * - Fast insertion and removal.
     *
     * Expected basic operation complexity:
     *
     * put()          -> O(1)
     * get()          -> O(1)
     * remove()       -> O(1)
     * containsKey()  -> O(1)
     *
     * These operations are constant-time with respect to
     * the number of mappings.
     *
     *
     * 7. ITERATION ORDER
     *
     * EnumMap iterates in the natural order of enum constants.
     *
     * That means declaration order.
     *
     * Example:
     *
     * enum Priority {
     *     LOW, MEDIUM, HIGH, CRITICAL
     * }
     *
     * EnumMap<Priority, String> map =
     *     new EnumMap<>(Priority.class);
     *
     * map.put(Priority.HIGH, "Important");
     * map.put(Priority.LOW, "Normal");
     * map.put(Priority.CRITICAL, "Urgent");
     *
     * Iteration order:
     *
     * LOW
     * HIGH
     * CRITICAL
     *
     * Notice:
     *
     * The order is based on enum declaration order,
     * not insertion order.
     *
     * MEDIUM is skipped because no mapping exists for it.
     *
     *
     * 8. NULL KEYS AND VALUES
     *
     * EnumMap does not allow null keys.
     *
     * Example:
     *
     * map.put(null, "Java");
     *
     * This throws NullPointerException.
     *
     * Null values are allowed.
     *
     * Example:
     *
     * map.put(Day.MONDAY, null);
     *
     * IMPORTANT:
     *
     * EnumMap can distinguish between:
     *
     * - A key that has no mapping.
     * - A key that has a mapping whose value is null.
     *
     * Use containsKey() when this distinction matters.
     *
     *
     * 9. DUPLICATE KEYS AND VALUES
     *
     * Duplicate enum keys are not allowed.
     *
     * Inserting the same enum key again replaces its value.
     *
     * Example:
     *
     * map.put(Day.MONDAY, "Java");
     * map.put(Day.MONDAY, "Spring");
     *
     * The final value for MONDAY is Spring.
     *
     * Duplicate values are allowed.
     *
     *
     * 10. ENUMMAP VS HASHMAP
     *
     * HashMap:
     *
     * - Supports general object keys.
     * - Uses hashing internally.
     * - Does not guarantee iteration order.
     * - Allows one null key and multiple null values.
     *
     * EnumMap:
     *
     * - Supports keys from one enum type only.
     * - Uses an array internally.
     * - Iterates in enum declaration order.
     * - Does not allow null keys.
     * - Allows null values.
     *
     * Use EnumMap when all keys are enum constants.
     *
     *
     * 11. ENUMMAP VS TREEMAP
     *
     * TreeMap:
     *
     * - Uses a Red-Black Tree.
     * - Maintains keys according to natural ordering
     *   or a supplied Comparator.
     * - Basic operations take O(log n).
     *
     * EnumMap:
     *
     * - Uses an array.
     * - Maintains enum declaration order.
     * - Basic operations are O(1).
     *
     * Both provide predictable key iteration order,
     * but their ordering mechanisms are different.
     *
     *
     * 12. ENUMMAP IS NOT SYNCHRONIZED
     *
     * EnumMap is not synchronized.
     *
     * If multiple threads access an EnumMap and at least one
     * thread modifies it, external synchronization may
     * be required.
     *
     * Example:
     *
     * Map<Day, String> synchronizedMap =
     *     Collections.synchronizedMap(map);
     *
     * This creates a synchronized wrapper.
     *
     * When iterating over the synchronized wrapper,
     * synchronize on the wrapper itself.
     *
     * Example:
     *
     * synchronized (synchronizedMap) {
     *     for (Map.Entry<Day, String> entry :
     *          synchronizedMap.entrySet()) {
     *
     *         System.out.println(entry);
     *     }
     * }
     *
     *
     * 13. ENUMMAP ITERATORS
     *
     * EnumMap iterators are weakly consistent in the sense
     * that they do not throw ConcurrentModificationException
     * when the map is modified during iteration.
     *
     * They may or may not reflect modifications made
     * after the iterator is created.
     *
     * This does not make EnumMap thread-safe.
     *
     *
     * 14. COMPLETE ENUMMAP EXAMPLE
     *
     * import java.util.EnumMap;
     * import java.util.Map;
     *
     * public class EnumMapExample {
     *
     *     enum Day {
     *         MONDAY, TUESDAY, WEDNESDAY,
     *         THURSDAY, FRIDAY, SATURDAY, SUNDAY
     *     }
     *
     *     public static void main(String[] args) {
     *
     *         EnumMap<Day, String> schedule =
     *             new EnumMap<>(Day.class);
     *
     *         schedule.put(Day.MONDAY, "Java");
     *         schedule.put(Day.TUESDAY, "SQL");
     *         schedule.put(Day.WEDNESDAY, "Spring Boot");
     *         schedule.put(Day.FRIDAY, "DSA");
     *
     *         System.out.println(schedule);
     *
     *         System.out.println(
     *             schedule.get(Day.MONDAY)
     *         );
     *
     *         System.out.println(
     *             schedule.containsKey(Day.FRIDAY)
     *         );
     *
     *         for (Map.Entry<Day, String> entry :
     *              schedule.entrySet()) {
     *
     *             System.out.println(
     *                 entry.getKey() + " = " + entry.getValue()
     *             );
     *         }
     *     }
     * }
     *
     *
     * 15. REAL-WORLD USE CASES
     *
     * EnumMap is useful when keys represent a fixed set of
     * categories or states.
     *
     * Examples:
     *
     * 1. Days of the week:
     *    EnumMap<Day, String>
     *
     * 2. Order status:
     *    EnumMap<OrderStatus, Integer>
     *
     * 3. Application roles:
     *    EnumMap<UserRole, List<String>>
     *
     * 4. Traffic light states:
     *    EnumMap<TrafficLight, String>
     *
     * 5. Payment methods:
     *    EnumMap<PaymentMethod, Double>
     *
     * 6. Task priorities:
     *    EnumMap<Priority, List<Task>>
     *
     *
     * 16. WHEN SHOULD WE USE ENUMMAP?
     *
     * Use EnumMap when:
     *
     * - All keys belong to one enum type.
     * - You want compact and efficient storage.
     * - You want iteration in enum declaration order.
     * - Your key domain is fixed and known.
     *
     * Use HashMap when keys are general objects.
     *
     * Use TreeMap when custom sorted key navigation is required.
     *
     *
     * 17. COMMON MISTAKES
     *
     * 1. Trying to use String keys in EnumMap.
     *    EnumMap requires enum keys of one specific type.
     *
     * 2. Forgetting to pass the enum class to the constructor.
     *    Use new EnumMap<>(Day.class).
     *
     * 3. Assuming EnumMap preserves insertion order.
     *    It follows enum declaration order.
     *
     * 4. Assuming EnumMap permits null keys.
     *    It does not.
     *
     * 5. Assuming EnumMap permits no null values.
     *    Null values are allowed.
     *
     * 6. Assuming EnumMap is thread-safe.
     *    It is not.
     *
     * 7. Assuming ordinal() should be used as a permanent
     *    external identifier.
     *    Enum ordinals depend on declaration position and
     *    can change when constants are reordered.
     *
     *
     * 18. INTERVIEW QUESTIONS
     *
     * 1. What is EnumMap?
     * 2. Why is EnumMap used?
     * 3. Which interface does EnumMap implement?
     * 4. Which data structure does EnumMap use internally?
     * 5. Why is EnumMap efficient?
     * 6. What is ordinal()?
     * 7. Does EnumMap allow null keys?
     * 8. Does EnumMap allow null values?
     * 9. What is its iteration order?
     * 10. Is EnumMap synchronized?
     * 11. What is the difference between EnumMap and HashMap?
     * 12. What is the difference between EnumMap and TreeMap?
     * 13. Can EnumMap contain keys from different enum types?
     * 14. What happens when the same enum key is inserted again?
     * 15. Why should ordinal() not be used as a persistent ID?
     *
     *
     * 19. QUICK REVISION
     *
     * - EnumMap is a specialized Map implementation.
     * - It belongs to java.util.
     * - It implements Map and Serializable, Cloneable.
     * - Keys must belong to one enum type.
     * - It uses an array internally.
     * - Enum ordinal positions help locate array entries.
     * - Basic operations are O(1).
     * - Iteration follows enum declaration order.
     * - Null keys are not allowed.
     * - Null values are allowed.
     * - It is not synchronized.
     * - It is useful for fixed sets of enum-based keys.
     *
     * ============================================================
     *                       END OF ENUMMAP
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about EnumMap , Happy Learning ..!");
    }
}
