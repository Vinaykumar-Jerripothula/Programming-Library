
public class GenericsWithCollectionsAndBestPractices {

    /*
     * ============================================================
     *       GENERICS WITH COLLECTIONS AND BEST PRACTICES
     * ============================================================
     *
     * 1. GENERICS IN THE JAVA COLLECTIONS FRAMEWORK
     *
     * The Java Collections Framework uses Generics extensively.
     *
     * Generics allow collections to store and retrieve
     * elements with compile-time type safety.
     *
     * Examples:
     *
     *     List<String> names = new ArrayList<>();
     *
     *     Set<Integer> numbers = new HashSet<>();
     *
     *     Queue<Double> prices = new LinkedList<>();
     *
     *     Map<Integer, String> students = new HashMap<>();
     *
     * Without Generics, collections would commonly work
     * with Object references, requiring explicit casts.
     *
     * With Generics, the compiler knows the expected types.
     *
     *
     * 2. GENERICS WITH LIST
     *
     * List represents an ordered collection that allows
     * duplicate elements.
     *
     * Example:
     *
     *     List<String> names = new ArrayList<>();
     *
     *     names.add("Vinay");
     *     names.add("Kumar");
     *     names.add("Vinay");
     *
     *     String name = names.get(0);
     *
     * The compiler ensures that the List contains String values.
     *
     * Important:
     *
     * List<String> is not a subtype of List<Object>.
     *
     * Generic types are invariant.
     *
     *
     * 3. GENERICS WITH SET
     *
     * Set represents a collection that does not allow
     * duplicate elements according to its equality rules.
     *
     * Example:
     *
     *     Set<Integer> numbers = new HashSet<>();
     *
     *     numbers.add(10);
     *     numbers.add(20);
     *     numbers.add(10);
     *
     *     Integer number = numbers.iterator().next();
     *
     * Generics ensure that only Integer values are accepted
     * through the type-safe API.
     *
     *
     * 4. GENERICS WITH QUEUE
     *
     * Queue represents elements waiting to be processed.
     *
     * Example:
     *
     *     Queue<String> tasks = new LinkedList<>();
     *
     *     tasks.offer("Task 1");
     *     tasks.offer("Task 2");
     *
     *     String task = tasks.poll();
     *
     * The queue stores and returns String values.
     *
     *
     * 5. GENERICS WITH MAP
     *
     * Map stores key-value pairs.
     *
     * It has two type parameters:
     *
     *     Map<K, V>
     *
     * K -> Key type
     * V -> Value type
     *
     * Example:
     *
     *     Map<Integer, String> students = new HashMap<>();
     *
     *     students.put(101, "Vinay");
     *     students.put(102, "Rahul");
     *
     *     String name = students.get(101);
     *
     * Here:
     *
     * Keys must be Integer.
     * Values must be String.
     *
     * Important:
     *
     * Map is part of the Collections Framework, but it does
     * not extend the Collection interface.
     *
     *
     * 6. GENERICS WITH ITERATOR
     *
     * Iterator<E> is a generic interface.
     *
     * Example:
     *
     *     List<String> names = new ArrayList<>();
     *
     *     names.add("Java");
     *     names.add("Python");
     *
     *     Iterator<String> iterator = names.iterator();
     *
     *     while (iterator.hasNext()) {
     *         String name = iterator.next();
     *         System.out.println(name);
     *     }
     *
     * Because Iterator<String> is used, next() returns String.
     *
     * No explicit cast is required.
     *
     *
     * 7. GENERICS WITH COMPARABLE
     *
     * Comparable<T> is a generic interface.
     *
     * It defines natural ordering for objects.
     *
     * Its important method is:
     *
     *     int compareTo(T other);
     *
     * Example:
     *
     *     class Student implements Comparable<Student> {
     *
     *         int marks;
     *
     *         Student(int marks) {
     *             this.marks = marks;
     *         }
     *
     *         public int compareTo(Student other) {
     *             return Integer.compare(this.marks, other.marks);
     *         }
     *     }
     *
     * The Student class implements Comparable<Student>.
     *
     * This means a Student is compared with another Student.
     *
     * Usage:
     *
     *     List<Student> students = new ArrayList<>();
     *
     *     students.add(new Student(80));
     *     students.add(new Student(95));
     *     students.add(new Student(70));
     *
     *     Collections.sort(students);
     *
     * The natural ordering is defined by compareTo().
     *
     * Important:
     *
     * compareTo() should return:
     *
     * Negative value -> Current object is smaller.
     * Zero           -> Both are equal in ordering.
     * Positive value -> Current object is greater.
     *
     *
     * 8. GENERICS WITH COMPARATOR
     *
     * Comparator<T> is another generic interface.
     *
     * It defines an external comparison strategy.
     *
     * Its important method is:
     *
     *     int compare(T first, T second);
     *
     * Example:
     *
     *     Comparator<Student> byMarks =
     *         (s1, s2) -> Integer.compare(s1.marks, s2.marks);
     *
     *     students.sort(byMarks);
     *
     * Comparable:
     * Defines the natural ordering inside the class.
     *
     * Comparator:
     * Defines an external or alternative ordering.
     *
     * Generics ensure both compared objects have
     * the expected type.
     *
     *
     * 9. GENERICS WITH FUNCTIONAL INTERFACES
     *
     * Many functional interfaces in Java are generic.
     *
     * Examples:
     *
     * Predicate<T>
     * Function<T, R>
     * Consumer<T>
     * Supplier<T>
     * UnaryOperator<T>
     * BinaryOperator<T>
     *
     * Predicate<T>:
     *
     *     boolean test(T value);
     *
     * Example:
     *
     *     Predicate<String> isLong =
     *         value -> value.length() > 5;
     *
     * Function<T, R>:
     *
     *     R apply(T value);
     *
     * Example:
     *
     *     Function<String, Integer> getLength =
     *         value -> value.length();
     *
     * Consumer<T>:
     *
     *     void accept(T value);
     *
     * Example:
     *
     *     Consumer<String> printer =
     *         value -> System.out.println(value);
     *
     * Supplier<T>:
     *
     *     T get();
     *
     * Example:
     *
     *     Supplier<String> supplier = () -> "Java";
     *
     * These interfaces make reusable, type-safe
     * functional programming possible.
     *
     *
     * 10. GENERICS WITH STREAM API
     *
     * Java Streams also use Generics extensively.
     *
     * Example:
     *
     *     List<String> names = List.of("Vinay", "Rahul");
     *
     *     List<Integer> lengths = names.stream()
     *         .map(String::length)
     *         .toList();
     *
     * The stream starts with String elements.
     *
     * map() transforms each String into an Integer.
     *
     * The resulting stream contains Integer elements.
     *
     * The compiler tracks these types through the pipeline.
     *
     * Another example:
     *
     *     List<String> filtered = names.stream()
     *         .filter(name -> name.length() > 3)
     *         .toList();
     *
     * filter() preserves the stream's element type.
     *
     *
     * 11. GENERICS AND COLLECTION FACTORY METHODS
     *
     * Java provides generic factory methods for creating
     * collections.
     *
     * Examples:
     *
     *     List<String> names = List.of("Java", "Python");
     *
     *     Set<Integer> numbers = Set.of(10, 20, 30);
     *
     *     Map<Integer, String> students =
     *         Map.of(101, "Vinay", 102, "Rahul");
     *
     * The compiler infers the type arguments from context
     * and supplied values.
     *
     * Important:
     *
     * List.of(), Set.of(), and Map.of() create unmodifiable
     * collections.
     *
     * They reject null elements, keys, and values.
     *
     *
     * 12. GENERIC COLLECTIONS AND SUBTYPING
     *
     * Consider:
     *
     *     class Animal { }
     *
     *     class Dog extends Animal { }
     *
     * A Dog is an Animal.
     *
     * But:
     *
     *     List<Dog> dogs = new ArrayList<>();
     *
     * This is not valid:
     *
     *     List<Animal> animals = dogs;
     *
     * Why?
     *
     * If Java allowed it, we could insert a Cat into
     * the original List<Dog>.
     *
     * Therefore:
     *
     * List<Dog> is NOT a subtype of List<Animal>.
     *
     * This is called generic invariance.
     *
     * To support flexible read-only access, use:
     *
     *     List<? extends Animal> animals = dogs;
     *
     * To support inserting Dog objects into a collection
     * of Dog or a supertype, use:
     *
     *     List<? super Dog> destination;
     *
     * These wildcard rules were covered in the
     * Wildcards folder.
     *
     *
     * 13. GENERIC API DESIGN
     *
     * Generics help us design reusable APIs.
     *
     * Example:
     *
     *     interface Repository<T, ID> {
     *
     *         T findById(ID id);
     *
     *         void save(T entity);
     *
     *         void deleteById(ID id);
     *     }
     *
     * Here:
     *
     * T  -> Entity type.
     * ID -> Identifier type.
     *
     * Different repositories can specialize the interface.
     *
     * Example:
     *
     *     class UserRepository implements Repository<User, Long> {
     *
     *         public User findById(Long id) {
     *             return null;
     *         }
     *
     *         public void save(User user) { }
     *
     *         public void deleteById(Long id) { }
     *     }
     *
     *     class ProductRepository
     *             implements Repository<Product, Integer> {
     *
     *         public Product findById(Integer id) {
     *             return null;
     *         }
     *
     *         public void save(Product product) { }
     *
     *         public void deleteById(Integer id) { }
     *     }
     *
     * The same contract can support different entities
     * and identifier types.
     *
     *
     * 14. GENERIC API RESPONSE
     *
     * A common backend use case is a reusable API response.
     *
     * Example:
     *
     *     class ApiResponse<T> {
     *
     *         private boolean success;
     *         private String message;
     *         private T data;
     *
     *         public ApiResponse(
     *                 boolean success, String message, T data) {
     *
     *             this.success = success;
     *             this.message = message;
     *             this.data = data;
     *         }
     *
     *         public T getData() {
     *             return data;
     *         }
     *     }
     *
     * Usage:
     *
     *     ApiResponse<User> userResponse;
     *
     *     ApiResponse<List<User>> usersResponse;
     *
     *     ApiResponse<Product> productResponse;
     *
     * One response class can represent different data types.
     *
     *
     * 15. GENERIC SERVICE LAYER
     *
     * Generics can also reduce repeated service-layer code
     * when multiple services genuinely share the same behavior.
     *
     * Example:
     *
     *     interface CrudService<T, ID> {
     *
     *         T getById(ID id);
     *
     *         T create(T entity);
     *
     *         void delete(ID id);
     *     }
     *
     * A UserService can implement CrudService<User, Long>.
     *
     * A ProductService can implement CrudService<Product, Long>.
     *
     * Important:
     *
     * Do not force unrelated business logic into a generic
     * abstraction just to reduce the number of classes.
     *
     * Use Generics when the behavior and type relationship
     * are genuinely reusable.
     *
     *
     * 16. GENERICS IN SPRING DATA JPA
     *
     * Spring Data JPA uses generic interfaces.
     *
     * Example:
     *
     *     interface UserRepository
     *             extends JpaRepository<User, Long> {
     *     }
     *
     * JpaRepository<T, ID> uses:
     *
     * T  -> Entity type.
     * ID -> Primary-key type.
     *
     * The repository inherits type-safe operations such as:
     *
     *     save(User entity)
     *     findById(Long id)
     *     deleteById(Long id)
     *
     * The exact inherited API is defined by Spring Data.
     *
     * Generics allow Spring Data to provide reusable
     * repository behavior without requiring a separate
     * implementation for every entity.
     *
     *
     * 17. GENERICS WITH OPTIONAL
     *
     * Optional<T> is a generic container that represents
     * either a present value or an absent value.
     *
     * Example:
     *
     *     Optional<String> name = Optional.of("Vinay");
     *
     *     Optional<Integer> age = Optional.empty();
     *
     * Another example:
     *
     *     Optional<User> user = repository.findById(101L);
     *
     * The type parameter tells us what kind of value
     * may be present.
     *
     *
     * 18. GENERICS WITH CLASS<T>
     *
     * Class<T> represents a runtime Class object
     * associated with a type.
     *
     * Example:
     *
     *     Class<String> stringClass = String.class;
     *
     *     Class<Integer> integerClass = Integer.class;
     *
     * A generic method can accept Class<T>:
     *
     *     public <T> T createInstance(Class<T> type)
     *             throws ReflectiveOperationException {
     *
     *         return type.getDeclaredConstructor().newInstance();
     *     }
     *
     * Usage:
     *
     *     StringBuilder builder =
     *         createInstance(StringBuilder.class);
     *
     * Class<T> is useful when runtime code needs
     * an explicit class token.
     *
     * It does not preserve parameterized types such as
     * List<String> as an ordinary class literal.
     *
     *
     * 19. GENERIC FACTORY METHODS
     *
     * A factory method can declare a generic return type.
     *
     * Example:
     *
     *     public static <T> List<T> createList() {
     *         return new ArrayList<>();
     *     }
     *
     * Usage:
     *
     *     List<String> names = createList();
     *
     *     List<Integer> numbers = createList();
     *
     * The compiler infers T from the assignment context.
     *
     * This is useful for creating reusable object factories.
     *
     *
     * 20. GENERICS AND METHOD REFERENCES
     *
     * Method references work with generic functional interfaces.
     *
     * Example:
     *
     *     Function<String, Integer> parser = Integer::parseInt;
     *
     *     Integer value = parser.apply("123");
     *
     * The functional interface describes:
     *
     * Input type  -> String
     * Output type -> Integer
     *
     * The method reference must be compatible with
     * that functional interface's method signature.
     *
     *
     * 21. GENERIC TYPE INFERENCE IN COMPLEX EXPRESSIONS
     *
     * Java can infer type arguments from:
     *
     * 1. Method arguments.
     * 2. Assignment context.
     * 3. Method invocation context.
     * 4. Target functional interface type.
     *
     * Example:
     *
     *     List<String> names = Collections.emptyList();
     *
     * The compiler infers String for the returned List.
     *
     * Another example:
     *
     *     Comparator<String> comparator =
     *         Comparator.comparing(String::length);
     *
     * The target type helps determine the generic types
     * involved in the expression.
     *
     * If inference becomes difficult, explicit type arguments
     * or helper methods can improve readability.
     *
     *
     * 22. COMMON GENERICS BEST PRACTICES
     *
     * 1. Prefer parameterized types over raw types.
     *
     *     List<String> names = new ArrayList<>();
     *
     * 2. Use the diamond operator where appropriate.
     *
     *     List<String> names = new ArrayList<>();
     *
     * 3. Use meaningful type relationships.
     *
     *     Map<String, User>
     *
     * 4. Use bounded types when operations require
     *    specific capabilities.
     *
     *     <T extends Number>
     *
     * 5. Use wildcards to make APIs flexible.
     *
     * 6. Follow PECS when designing producer/consumer APIs.
     *
     * 7. Avoid unchecked casts whenever possible.
     *
     * 8. Do not suppress warnings without understanding them.
     *
     * 9. Keep generic declarations readable.
     *
     * 10. Avoid unnecessary type parameters.
     *
     * 11. Prefer List<T> over generic arrays when appropriate.
     *
     * 12. Avoid unsafe raw-type interactions.
     *
     * 13. Do not overuse Generics when a concrete type
     *     makes the API clearer.
     *
     * 14. Use descriptive method and class names.
     *
     * 15. Document complicated generic relationships.
     *
     *
     * 23. COMMON GENERICS MISTAKES
     *
     * 1. Using List<Object> when List<?> is intended.
     *
     * 2. Assuming List<Integer> can be assigned to List<Number>.
     *
     * 3. Using raw collections in new code.
     *
     * 4. Adding values to List<? extends Number>.
     *
     * 5. Trying to create T[] directly.
     *
     * 6. Trying to instantiate T using new T().
     *
     * 7. Using class type parameters in static fields.
     *
     * 8. Ignoring unchecked conversion warnings.
     *
     * 9. Confusing Comparable<T> with Comparator<T>.
     *
     * 10. Creating overly complicated generic APIs.
     *
     *
     * 24. GENERICS AND JAVA VERSION NOTES
     *
     * Java 5:
     * Generics were introduced.
     *
     * Java 7:
     * Diamond operator <> introduced.
     *
     * Java 8:
     * Functional interfaces, lambdas, and Stream API
     * made extensive use of generic types.
     *
     * Java 9:
     * Collection factory methods such as List.of(),
     * Set.of(), and Map.of() were introduced.
     *
     * Java 10:
     * Local variable type inference using var was introduced.
     *
     * Java 16:
     * Stream.toList() was introduced.
     *
     * These are useful milestones when reading modern
     * Java code.
     *
     *
     * 25. INTERVIEW QUESTIONS
     *
     * Q1. Why do Java Collections use Generics?
     * Q2. What is the difference between List<T> and List<Object>?
     * Q3. Why is Map not a subtype of Collection?
     * Q4. How does Iterator<T> use Generics?
     * Q5. What is Comparable<T>?
     * Q6. What is Comparator<T>?
     * Q7. What is the difference between Comparable and Comparator?
     * Q8. How do Predicate<T> and Function<T, R> use Generics?
     * Q9. How do Generics work with Streams?
     * Q10. What is generic invariance?
     * Q11. Why is List<Dog> not a subtype of List<Animal>?
     * Q12. What is a generic repository?
     * Q13. How does Spring Data JPA use Generics?
     * Q14. What is Optional<T>?
     * Q15. What is Class<T>?
     * Q16. What is a generic factory method?
     * Q17. What are good practices when designing generic APIs?
     * Q18. Why should raw types be avoided?
     * Q19. When should we use wildcards?
     * Q20. When should we avoid overusing Generics?
     *
     *
     * 26. FINAL GENERICS REVISION CHECKLIST
     *
     * FUNDAMENTALS:
     *
     * [ ] Explain why Generics were introduced.
     * [ ] Explain type parameters and type arguments.
     * [ ] Explain parameterized types and raw types.
     * [ ] Understand compile-time type safety.
     * [ ] Understand the diamond operator.
     *
     * GENERIC CLASSES AND INTERFACES:
     *
     * [ ] Create generic classes.
     * [ ] Create generic interfaces.
     * [ ] Use multiple type parameters.
     * [ ] Implement generic interfaces.
     * [ ] Understand generic inheritance.
     * [ ] Understand static restrictions.
     *
     * GENERIC METHODS AND BOUNDS:
     *
     * [ ] Write generic methods.
     * [ ] Understand type inference.
     * [ ] Use bounded type parameters.
     * [ ] Understand multiple bounds.
     * [ ] Understand recursive bounds.
     *
     * WILDCARDS:
     *
     * [ ] Understand unbounded wildcards.
     * [ ] Understand upper-bounded wildcards.
     * [ ] Understand lower-bounded wildcards.
     * [ ] Apply PECS.
     * [ ] Understand wildcard capture.
     * [ ] Understand generic invariance.
     *
     * TYPE ERASURE:
     *
     * [ ] Explain type erasure.
     * [ ] Understand reifiable types.
     * [ ] Understand bridge methods.
     * [ ] Understand heap pollution.
     * [ ] Understand generic array restrictions.
     * [ ] Understand unchecked warnings.
     *
     * PRACTICAL APPLICATIONS:
     *
     * [ ] Use Generics with List, Set, Map and Queue.
     * [ ] Understand Comparable and Comparator.
     * [ ] Use generic functional interfaces.
     * [ ] Understand Generics in Streams.
     * [ ] Design a generic Repository.
     * [ ] Design a generic API response.
     * [ ] Understand Generics in Spring Data JPA.
     *
     *
     * 27. FINAL UNDERSTANDING
     *
     * Generics allow us to write reusable code without
     * sacrificing compile-time type safety.
     *
     * They are not limited to Collections.
     *
     * They are used throughout the Java ecosystem:
     *
     * Collections
     * Functional interfaces
     * Streams
     * Optional
     * Spring Data JPA
     * Repositories
     * API responses
     * Reusable data structures
     *
     * The most important concepts to master are:
     *
     * 1. Generic classes and interfaces.
     * 2. Generic methods and bounded types.
     * 3. Wildcards and PECS.
     * 4. Generic invariance.
     * 5. Type erasure.
     * 6. Practical generic API design.
     *
     * ============================================================
     *     END OF GENERICS WITH COLLECTIONS AND BEST PRACTICES
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Generics with Collections and Best Practices , Happy Learning ..!");
    }
}
