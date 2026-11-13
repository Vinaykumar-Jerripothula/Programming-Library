
public class GenericClassesAndInterfaces {

    /*
     * ============================================================
     *              GENERIC CLASSES AND INTERFACES
     * ============================================================
     *
     * 1. WHAT IS A GENERIC CLASS?
     *
     * A normal class works with the data types explicitly
     * mentioned in its declaration.
     *
     * Suppose we need separate containers for Integer and String.
     *
     * Without Generics:
     *
     *     class IntegerBox {
     *         Integer value;
     *     }
     *
     *     class StringBox {
     *         String value;
     *     }
     *
     * This creates duplicate code.
     *
     * Generics allow us to create one reusable class that can
     * work with different reference types.
     *
     * Example:
     *
     *     class Box<T> {
     *         private T value;
     *
     *         public void setValue(T value) {
     *             this.value = value;
     *         }
     *
     *         public T getValue() {
     *             return value;
     *         }
     *     }
     *
     * Here:
     *
     * Box  -> Generic class
     * T    -> Type parameter
     * value -> Field whose type is T
     *
     * Usage:
     *
     *     Box<Integer> integerBox = new Box<>();
     *     integerBox.setValue(100);
     *
     *     Integer number = integerBox.getValue();
     *
     *     Box<String> stringBox = new Box<>();
     *     stringBox.setValue("Java");
     *
     *     String language = stringBox.getValue();
     *
     * The same class works with different types.
     *
     *
     * 2. HOW DOES A GENERIC CLASS WORK?
     *
     * Consider:
     *
     *     class Box<T> {
     *         T value;
     *     }
     *
     * When we declare:
     *
     *     Box<String> box = new Box<>();
     *
     * The compiler treats T as String for this parameterized
     * use of Box.
     *
     * Conceptually, the field is used as:
     *
     *     String value;
     *
     * When we declare:
     *
     *     Box<Integer> box = new Box<>();
     *
     * T represents Integer for that use.
     *
     * Important:
     *
     * We are not creating a separate source-code class for
     * every type argument.
     *
     * Java implements Generics through type erasure.
     * We will study its runtime implications in the
     * Type Erasure folder.
     *
     *
     * 3. GENERIC CLASS WITH MULTIPLE TYPE PARAMETERS
     *
     * A generic class can have more than one type parameter.
     *
     * Example:
     *
     *     class Pair<K, V> {
     *         private K key;
     *         private V value;
     *
     *         public Pair(K key, V value) {
     *             this.key = key;
     *             this.value = value;
     *         }
     *
     *         public K getKey() {
     *             return key;
     *         }
     *
     *         public V getValue() {
     *             return value;
     *         }
     *     }
     *
     * Usage:
     *
     *     Pair<Integer, String> pair =
     *         new Pair<>(101, "Vinay");
     *
     * Here:
     *
     * K represents Integer.
     * V represents String.
     *
     * Another example:
     *
     *     Pair<String, Double> product =
     *         new Pair<>("Laptop", 55000.0);
     *
     * Common real-world example:
     *
     *     Map<K, V>
     *
     * A Map uses two type parameters:
     *
     * K -> Key type
     * V -> Value type
     *
     * Example:
     *
     *     Map<Integer, String> students;
     *
     * Integer represents the key type.
     * String represents the value type.
     *
     *
     * 4. GENERIC CLASS WITH THREE OR MORE TYPE PARAMETERS
     *
     * There is no fixed limit of three type parameters.
     *
     * Example:
     *
     *     class StudentRecord<A, B, C> {
     *         A name;
     *         B rollNumber;
     *         C percentage;
     *     }
     *
     * Usage:
     *
     *     StudentRecord<String, Integer, Double> student;
     *
     * Here:
     *
     * A -> String
     * B -> Integer
     * C -> Double
     *
     * However, use only the number of type parameters
     * actually needed to represent the relationship.
     *
     *
     * 5. GENERIC CLASS WITH CONSTRUCTORS
     *
     * A generic class can have constructors that accept
     * its type parameter.
     *
     * Example:
     *
     *     class Container<T> {
     *         private T data;
     *
     *         Container(T data) {
     *             this.data = data;
     *         }
     *     }
     *
     * Usage:
     *
     *     Container<String> c1 = new Container<>("Java");
     *     Container<Integer> c2 = new Container<>(100);
     *
     * The constructor parameter type is T.
     *
     * The compiler checks the supplied argument against
     * the type argument of the Container.
     *
     *
     * 6. GENERIC CONSTRUCTORS
     *
     * A constructor can also declare its own type parameter.
     *
     * This is called a generic constructor.
     *
     * Example:
     *
     *     class Printer {
     *
     *         <T> Printer(T value) {
     *             System.out.println(value);
     *         }
     *     }
     *
     * Usage:
     *
     *     Printer p1 = new Printer("Java");
     *     Printer p2 = new Printer(100);
     *
     * Here:
     *
     * <T> declares a type parameter for the constructor.
     *
     * Important distinction:
     *
     * A generic class constructor:
     *
     *     class Box<T> {
     *         Box(T value) { }
     *     }
     *
     * Uses the class's T.
     *
     * A generic constructor:
     *
     *     class Printer {
     *         <T> Printer(T value) { }
     *     }
     *
     * Declares its own T, independent of the class.
     *
     * A class can have both class-level type parameters
     * and constructor-level type parameters.
     *
     *
     * 7. GENERIC INTERFACES
     *
     * An interface can also declare type parameters.
     *
     * Example:
     *
     *     interface Repository<T> {
     *         void save(T data);
     *         T findById(int id);
     *     }
     *
     * Here:
     *
     * Repository is a generic interface.
     * T represents the type of data being handled.
     *
     * A class can implement this interface for a specific type.
     *
     *
     * 8. IMPLEMENTING A GENERIC INTERFACE WITH A CONCRETE TYPE
     *
     * Example:
     *
     *     class UserRepository implements Repository<User> {
     *
     *         public void save(User user) {
     *             // Save user
     *         }
     *
     *         public User findById(int id) {
     *             // Find user
     *             return null;
     *         }
     *     }
     *
     * Here:
     *
     * Repository<User> means T is User.
     *
     * Therefore:
     *
     * save(T data) becomes save(User data).
     * T findById(int id) becomes User findById(int id).
     *
     * The implementing class must provide methods compatible
     * with the specialized interface contract.
     *
     *
     * 9. IMPLEMENTING A GENERIC INTERFACE WITH A TYPE PARAMETER
     *
     * Instead of fixing the type, the implementing class
     * can remain generic.
     *
     * Example:
     *
     *     class DatabaseRepository<T> implements Repository<T> {
     *
     *         public void save(T data) {
     *             // Save data
     *         }
     *
     *         public T findById(int id) {
     *             return null;
     *         }
     *     }
     *
     * Usage:
     *
     *     DatabaseRepository<User> userRepo =
     *         new DatabaseRepository<>();
     *
     *     DatabaseRepository<Product> productRepo =
     *         new DatabaseRepository<>();
     *
     * The same implementation can work with User, Product,
     * or another reference type.
     *
     *
     * 10. IMPLEMENTING AN INTERFACE WITH DIFFERENT TYPE PARAMETERS
     *
     * An implementing class may use different type parameter
     * names from the interface.
     *
     * Example:
     *
     *     interface Processor<T> {
     *         void process(T data);
     *     }
     *
     *     class StringProcessor implements Processor<String> {
     *
     *         public void process(String data) {
     *             System.out.println(data);
     *         }
     *     }
     *
     * The name T is not important by itself.
     * What matters is the actual type argument String.
     *
     *
     * 11. GENERIC INTERFACE WITH MULTIPLE TYPE PARAMETERS
     *
     * Example:
     *
     *     interface Converter<S, T> {
     *         T convert(S source);
     *     }
     *
     * Here:
     *
     * S -> Source type
     * T -> Target type
     *
     * Implementation:
     *
     *     class StringToIntegerConverter
     *             implements Converter<String, Integer> {
     *
     *         public Integer convert(String source) {
     *             return Integer.parseInt(source);
     *         }
     *     }
     *
     * Usage:
     *
     *     Converter<String, Integer> converter =
     *         new StringToIntegerConverter();
     *
     *     Integer result = converter.convert("123");
     *
     * This pattern is useful when converting one data type
     * into another.
     *
     *
     * 12. GENERIC CLASS INHERITANCE
     *
     * A generic class can extend another generic class.
     *
     * Example:
     *
     *     class Parent<T> {
     *         T value;
     *     }
     *
     *     class Child<T> extends Parent<T> {
     *     }
     *
     * Here, Child carries the same type parameter to Parent.
     *
     * Usage:
     *
     *     Child<String> child = new Child<>();
     *
     * Parent's T and Child's T represent String in this use.
     *
     *
     * 13. INHERITING A GENERIC CLASS WITH A FIXED TYPE
     *
     * A subclass can provide a concrete type argument
     * to its generic superclass.
     *
     * Example:
     *
     *     class Parent<T> {
     *         T value;
     *     }
     *
     *     class StringChild extends Parent<String> {
     *     }
     *
     * Usage:
     *
     *     StringChild child = new StringChild();
     *
     * Parent's T is fixed as String.
     *
     * Therefore, the inherited value field is used as String.
     *
     * The subclass does not need its own type parameter.
     *
     *
     * 14. INHERITING WITH A DIFFERENT TYPE PARAMETER NAME
     *
     * The subclass may use a different type parameter name.
     *
     * Example:
     *
     *     class Parent<T> {
     *         T value;
     *     }
     *
     *     class Child<U> extends Parent<U> {
     *     }
     *
     * Here:
     *
     * Parent declares T.
     * Child declares U.
     *
     * Both represent the same supplied type argument
     * when Child<U> is used.
     *
     * Type parameter names do not have to match.
     *
     *
     * 15. GENERIC SUBCLASS WITH ADDITIONAL TYPE PARAMETERS
     *
     * A subclass can introduce additional type parameters.
     *
     * Example:
     *
     *     class Parent<T> {
     *         T value;
     *     }
     *
     *     class Child<T, U> extends Parent<T> {
     *         U extraValue;
     *     }
     *
     * Usage:
     *
     *     Child<String, Integer> child = new Child<>();
     *
     * Here:
     *
     * Parent's T -> String
     * Child's T  -> String
     * Child's U  -> Integer
     *
     * The subclass adds another independently typed field.
     *
     *
     * 16. GENERIC INTERFACE INHERITANCE
     *
     * One generic interface can extend another generic interface.
     *
     * Example:
     *
     *     interface Parent<T> {
     *         void display(T value);
     *     }
     *
     *     interface Child<T> extends Parent<T> {
     *         void process(T value);
     *     }
     *
     * The Child interface inherits the generic contract
     * from Parent.
     *
     * A class implementing Child<T> must satisfy both
     * interface contracts.
     *
     *
     * 17. FIXING THE TYPE DURING INTERFACE INHERITANCE
     *
     * A child interface can fix the parent interface's
     * type argument.
     *
     * Example:
     *
     *     interface Parent<T> {
     *         void display(T value);
     *     }
     *
     *     interface StringChild extends Parent<String> {
     *     }
     *
     * Now StringChild inherits display(String value).
     *
     * Its implementing class must provide a compatible method.
     *
     *
     * 18. GENERIC CLASS IMPLEMENTING MULTIPLE INTERFACES
     *
     * A class can implement multiple generic interfaces.
     *
     * Example:
     *
     *     interface Reader<T> {
     *         T read();
     *     }
     *
     *     interface Writer<T> {
     *         void write(T value);
     *     }
     *
     *     class DataStore<T> implements Reader<T>, Writer<T> {
     *
     *         public T read() {
     *             return null;
     *         }
     *
     *         public void write(T value) {
     *             // Store value
     *         }
     *     }
     *
     * Usage:
     *
     *     DataStore<String> store = new DataStore<>();
     *
     * Both interfaces use String as their type argument.
     *
     *
     * 19. SAME GENERIC INTERFACE WITH DIFFERENT TYPE ARGUMENTS
     *
     * Be careful when implementing the same generic interface
     * through different inheritance paths.
     *
     * Example:
     *
     *     interface Box<T> { }
     *
     *     interface A extends Box<String> { }
     *
     *     interface B extends Box<Integer> { }
     *
     *     class C implements A, B { }
     *
     * This is illegal.
     *
     * Why?
     *
     * C would inherit two different parameterizations of
     * the same generic interface: Box<String> and Box<Integer>.
     *
     * Java does not allow a type to inherit the same generic
     * interface with different type arguments.
     *
     *
     * 20. STATIC MEMBERS IN GENERIC CLASSES
     *
     * A static member belongs to the class itself, not to
     * a particular object or parameterized type.
     *
     * Example:
     *
     *     class Box<T> {
     *
     *         // Invalid:
     *         // static T value;
     *
     *         // Valid:
     *         static int count;
     *     }
     *
     * Why is static T invalid?
     *
     * Consider:
     *
     *     Box<String> stringBox;
     *     Box<Integer> integerBox;
     *
     * Both use the same generic class declaration.
     *
     * A static field is shared at the class level.
     *
     * There is no single class-level T that can represent
     * both String and Integer.
     *
     * Therefore, a class's type parameter cannot be used
     * in the declaration of a static field or static method.
     *
     * However, a static method can declare its own
     * independent type parameter.
     *
     * Example:
     *
     *     class Utility {
     *
     *         static <T> void display(T value) {
     *             System.out.println(value);
     *         }
     *     }
     *
     * The method's T belongs to the method itself,
     * not to a generic class.
     *
     *
     * 21. GENERIC CLASS VS GENERIC INTERFACE
     *
     * Generic class:
     *
     *     class Box<T> {
     *         T value;
     *     }
     *
     * Generic interface:
     *
     *     interface Repository<T> {
     *         void save(T value);
     *     }
     *
     * Differences:
     *
     * Generic class:
     * - Can have instance fields.
     * - Can have constructors.
     * - Can contain implemented methods.
     * - Can be instantiated if it is not abstract.
     *
     * Generic interface:
     * - Defines a type-safe contract.
     * - Cannot be directly instantiated.
     * - Has no constructors.
     * - Can declare abstract, default, static and private
     *   methods, subject to normal interface rules.
     *
     * Both can declare one or more type parameters.
     *
     *
     * 22. GENERICS IN SPRING AND BACKEND DEVELOPMENT
     *
     * Generics are widely used in Java backend frameworks.
     *
     * Example: Spring Data JPA
     *
     *     interface UserRepository
     *             extends JpaRepository<User, Long> {
     *     }
     *
     * Here:
     *
     * User -> Entity type
     * Long -> Entity identifier type
     *
     * Another example:
     *
     *     class ApiResponse<T> {
     *         private boolean success;
     *         private String message;
     *         private T data;
     *     }
     *
     * Usage:
     *
     *     ApiResponse<User> userResponse;
     *     ApiResponse<List<User>> usersResponse;
     *
     * The same response structure can carry different
     * types of data.
     *
     * This avoids creating separate response classes
     * for every entity.
     *
     *
     * 23. COMMON MISTAKES
     *
     * 1. Trying to use a class type parameter in a static field.
     *
     * 2. Confusing a generic constructor with a constructor
     *    that simply uses the class's type parameter.
     *
     * 3. Forgetting to specify type arguments when implementing
     *    a generic interface.
     *
     * 4. Assuming type parameter names must match across
     *    inheritance.
     *
     * 5. Assuming a generic subclass must remain generic.
     *
     * 6. Implementing the same generic interface through
     *    multiple paths with incompatible type arguments.
     *
     * 7. Declaring unnecessary type parameters that make
     *    the API harder to understand.
     *
     *
     * 24. INTERVIEW QUESTIONS
     *
     * Q1. What is a generic class?
     * Q2. What is a generic interface?
     * Q3. Can a generic class have multiple type parameters?
     * Q4. Can a generic class have a constructor?
     * Q5. What is a generic constructor?
     * Q6. What is the difference between a generic constructor
     *     and a constructor using the class's type parameter?
     * Q7. Can a class implement a generic interface with
     *     a concrete type?
     * Q8. Can a class implement a generic interface while
     *     remaining generic?
     * Q9. Can a generic class extend another generic class?
     * Q10. Can a generic subclass fix its superclass's type?
     * Q11. Can a subclass introduce additional type parameters?
     * Q12. Can an interface extend a generic interface?
     * Q13. Can a class implement multiple generic interfaces?
     * Q14. Why can't a generic class use its type parameter
     *      in a static field?
     * Q15. Can a static method in a generic class declare
     *      its own type parameter?
     * Q16. Can a class inherit the same generic interface
     *      with different type arguments?
     *
     *
     * 25. QUICK REVISION
     *
     * Generic class:
     *     class Box<T> { }
     *
     * Multiple parameters:
     *     class Pair<K, V> { }
     *
     * Generic interface:
     *     interface Repository<T> { }
     *
     * Generic constructor:
     *     <T> ClassName(T value) { }
     *
     * Concrete implementation:
     *     class UserRepo implements Repository<User> { }
     *
     * Generic implementation:
     *     class DatabaseRepo<T> implements Repository<T> { }
     *
     * Same type inheritance:
     *     class Child<T> extends Parent<T> { }
     *
     * Fixed type inheritance:
     *     class StringChild extends Parent<String> { }
     *
     * Additional type parameter:
     *     class Child<T, U> extends Parent<T> { }
     *
     * Generic interface inheritance:
     *     interface Child<T> extends Parent<T> { }
     *
     * Static field:
     * Cannot use the generic class's T.
     *
     * Static generic method:
     * Can declare its own independent type parameter.
     *
     * ============================================================
     *             END OF GENERIC CLASSES AND INTERFACES
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Generic Classes and Interfaces , Happy Learning ..!");
    }
}
