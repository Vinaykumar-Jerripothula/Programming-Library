
public class GenericsFundamentals {

    /*
     * ============================================================
     *                     JAVA GENERICS
     * ============================================================
     *
     * 1. WHAT ARE GENERICS?
     *
     * Imagine you are creating a storage box.
     *
     * One box stores Integer values.
     * Another box stores String values.
     * Another box stores Employee objects.
     *
     * Without Generics, you may need to write separate classes
     * for each type, or store everything using Object.
     *
     * Generics solve this problem by allowing us to write reusable
     * code that works with different data types while maintaining
     * compile-time type safety.
     *
     * Generics allow types (classes, interfaces, and methods) to
     * operate on different data types using type parameters.
     *
     * Example:
     *
     *     Box<Integer> integerBox;
     *     Box<String> stringBox;
     *
     * Here, Box is reusable, but the data type is specified
     * when we use it.
     *
     *
     * 2. WHY WERE GENERICS INTRODUCED?
     *
     * Before Java 5, collections and reusable containers commonly
     * stored objects using the Object type.
     *
     * Example without Generics:
     *
     *     List names = new ArrayList();
     *
     *     names.add("Vinay");
     *     names.add(100);
     *
     *     String name = (String) names.get(0);
     *
     * Problems:
     *
     * 1. We can accidentally insert unrelated data types.
     * 2. We must perform explicit type casting when retrieving.
     * 3. Incorrect casts can cause ClassCastException at runtime.
     * 4. Type-related errors are not caught early by the compiler.
     *
     * Generics were introduced in Java 5 to solve these problems.
     *
     * With Generics:
     *
     *     List<String> names = new ArrayList<>();
     *
     *     names.add("Vinay");
     *     names.add("Kumar");
     *
     *     String name = names.get(0);
     *
     * Benefits:
     *
     * 1. Only String values can be inserted.
     * 2. No explicit casting is required when retrieving.
     * 3. Many type errors are detected during compilation.
     * 4. Code becomes more readable and reusable.
     *
     *
     * 3. WHAT IS A TYPE PARAMETER?
     *
     * A type parameter is a placeholder for a data type.
     *
     * Example:
     *
     *     class Box<T> {
     *         T value;
     *     }
     *
     * T is a type parameter.
     *
     * It represents a type that will be supplied when the
     * generic class is used.
     *
     * Usage:
     *
     *     Box<Integer> box1;
     *     Box<String> box2;
     *
     * In the first declaration, T represents Integer.
     * In the second declaration, T represents String.
     *
     * Important:
     *
     * T is not a special Java keyword.
     * It is simply a conventional name chosen by developers.
     *
     *
     * 4. GENERIC TYPE VS ACTUAL TYPE ARGUMENT
     *
     * Consider:
     *
     *     class Box<T> { }
     *
     * T is called a type parameter.
     *
     * Now consider:
     *
     *     Box<Integer> box;
     *
     * Integer is called a type argument.
     *
     * Remember:
     *
     * Type parameter = placeholder declared by the generic type.
     * Type argument  = actual type supplied while using it.
     *
     *
     * 5. GENERIC TYPE SYNTAX
     *
     * General syntax:
     *
     *     ClassName<TypeArgument> reference;
     *
     * Examples:
     *
     *     List<String> names;
     *     List<Integer> numbers;
     *     Set<Double> prices;
     *     Map<String, Integer> marks;
     *
     * A generic type can accept one or more type arguments,
     * depending on how it is declared.
     *
     *
     * 6. COMMON TYPE PARAMETER NAMING CONVENTIONS
     *
     * Java developers commonly use single uppercase letters:
     *
     * T = Type
     * E = Element
     * K = Key
     * V = Value
     * N = Number
     * S, U, V = Additional type parameters in some APIs
     *
     * Examples:
     *
     *     class Box<T> { }
     *
     *     interface List<E> { }
     *
     *     interface Map<K, V> { }
     *
     * These letters are conventions, not mandatory names.
     *
     * We can also write descriptive names:
     *
     *     class Box<DataType> { }
     *
     * However, conventional short names are more common.
     *
     *
     * 7. COMPILE-TIME TYPE SAFETY
     *
     * One of the biggest advantages of Generics is type safety.
     *
     * Example:
     *
     *     List<String> names = new ArrayList<>();
     *
     *     names.add("Vinay");  // Valid
     *     names.add("Kumar");  // Valid
     *     names.add(100);      // Compilation error
     *
     * Why?
     *
     * The compiler knows that names is intended to contain
     * String values only.
     *
     * It prevents an incompatible value from being inserted.
     *
     * Without Generics, such an error might remain unnoticed
     * until runtime.
     *
     * Important:
     *
     * Generics provide compile-time type safety for normal,
     * type-checked generic usage. Raw types, unchecked operations,
     * and certain legacy interactions can bypass these protections.
     *
     *
     * 8. TYPE CASTING: BEFORE AND AFTER GENERICS
     *
     * Without Generics:
     *
     *     List list = new ArrayList();
     *     list.add("Java");
     *
     *     String language = (String) list.get(0);
     *
     * The cast is required because get() returns Object
     * for a raw List.
     *
     * With Generics:
     *
     *     List<String> list = new ArrayList<>();
     *     list.add("Java");
     *
     *     String language = list.get(0);
     *
     * No explicit cast is required.
     *
     * The compiler already knows the expected element type.
     *
     *
     * 9. RAW TYPES
     *
     * A raw type is a generic type used without specifying
     * its type arguments.
     *
     * Example:
     *
     *     List<String> names = new ArrayList<>();
     *
     *     List list = new ArrayList();  // Raw type
     *
     * Raw types exist mainly for backward compatibility with
     * Java code written before Generics were introduced.
     *
     * Example:
     *
     *     List list = new ArrayList();
     *
     *     list.add("Java");
     *     list.add(100);
     *
     *     String value = (String) list.get(1);
     *
     * The last statement throws ClassCastException at runtime.
     *
     * Problems with raw types:
     *
     * 1. They bypass normal generic type checking.
     * 2. They may introduce unchecked warnings.
     * 3. They require casts when retrieving values.
     * 4. They can cause runtime ClassCastException.
     *
     * Prefer parameterized types:
     *
     *     List<String> names = new ArrayList<>();
     *
     * Avoid raw types in new code unless there is a specific
     * interoperability or legacy requirement.
     *
     *
     * 10. PARAMETERIZED TYPES
     *
     * A parameterized type is a generic type supplied with
     * actual type arguments.
     *
     * Examples:
     *
     *     List<String>
     *     List<Integer>
     *     Map<String, Integer>
     *
     * Here:
     *
     * List<String>       -> String parameterized List
     * List<Integer>      -> Integer parameterized List
     * Map<String,Integer> -> Map with String keys and Integer values
     *
     * Each declaration specifies the type arguments that
     * the generic type should use.
     *
     *
     * 11. GENERICS WITH REFERENCE TYPES
     *
     * Java generic type arguments must be reference types,
     * not primitive types.
     *
     * Invalid:
     *
     *     List<int> numbers;
     *     Box<double> box;
     *     List<char> characters;
     *
     * Valid:
     *
     *     List<Integer> numbers;
     *     Box<Double> box;
     *     List<Character> characters;
     *
     * Why?
     *
     * Java generics work with reference types.
     * Primitive types such as int and double are not reference
     * types.
     *
     * Use wrapper classes instead:
     *
     * int       -> Integer
     * double    -> Double
     * float     -> Float
     * long      -> Long
     * short     -> Short
     * byte      -> Byte
     * char      -> Character
     * boolean   -> Boolean
     *
     * Java automatically converts between primitives and
     * their corresponding wrapper types in many contexts.
     *
     * This is called:
     *
     * Autoboxing:
     *     int -> Integer
     *
     * Unboxing:
     *     Integer -> int
     *
     * Example:
     *
     *     List<Integer> numbers = new ArrayList<>();
     *     numbers.add(10);
     *
     * The primitive int value 10 is automatically boxed
     * into an Integer object.
     *
     *
     * 12. DIAMOND OPERATOR (<>)
     *
     * Before Java 7:
     *
     *     List<String> names = new ArrayList<String>();
     *
     * The type argument String was repeated on both sides.
     *
     * From Java 7:
     *
     *     List<String> names = new ArrayList<>();
     *
     * The compiler can infer the type argument from the
     * variable declaration in this context.
     *
     * The empty angle brackets <> are called the diamond
     * operator.
     *
     * It reduces unnecessary repetition.
     *
     * Important:
     *
     * The diamond operator does not mean that the object
     * has no type. The compiler infers its type arguments.
     *
     *
     * 13. GENERICS AND TYPE INFERENCE
     *
     * Type inference means the compiler determines a type
     * from the surrounding context.
     *
     * Example:
     *
     *     List<String> names = new ArrayList<>();
     *
     * The compiler infers String for the ArrayList's
     * type argument.
     *
     * Another example:
     *
     *     var names = new ArrayList<String>();
     *
     * Here, var allows the compiler to infer the local
     * variable's declared type as ArrayList<String>.
     *
     * Important distinction:
     *
     * var is a local variable type inference feature
     * introduced in Java 10.
     *
     * The diamond operator <> is a generic type argument
     * inference feature introduced in Java 7.
     *
     * They are related to inference but are not the same feature.
     *
     *
     * 14. GENERICS ARE NOT JUST FOR COLLECTIONS
     *
     * Although Java Collections use Generics extensively,
     * Generics can be used in many other places.
     *
     * Examples:
     *
     * 1. Generic classes
     * 2. Generic interfaces
     * 3. Generic methods
     * 4. Generic constructors
     * 5. Generic repositories
     * 6. Reusable API response types
     * 7. Data structures such as stacks and queues
     *
     * Example from backend development:
     *
     *     class ApiResponse<T> {
     *         private T data;
     *     }
     *
     * Usage:
     *
     *     ApiResponse<User> userResponse;
     *     ApiResponse<List<Product>> productResponse;
     *
     * The same response structure can represent different
     * kinds of data without sacrificing type safety.
     *
     *
     * 15. IMPORTANT GENERICS TERMINOLOGY
     *
     * Generic type:
     * A class or interface declared with type parameters.
     *
     * Type parameter:
     * A placeholder declared in a generic declaration.
     *
     * Type argument:
     * The actual type supplied to a generic declaration.
     *
     * Parameterized type:
     * A generic type supplied with actual type arguments.
     *
     * Raw type:
     * A generic type used without its type arguments.
     *
     * Type safety:
     * Preventing incompatible type operations.
     *
     * Type inference:
     * The compiler determining types from context.
     *
     * Autoboxing:
     * Automatic conversion from a primitive to its wrapper.
     *
     * Unboxing:
     * Automatic conversion from a wrapper to its primitive.
     *
     *
     * 16. COMMON MISCONCEPTIONS
     *
     * 1. T is not a Java keyword.
     *    It is a conventional type parameter name.
     *
     * 2. Generics are not limited to Collections.
     *    They can be used in classes, interfaces and methods.
     *
     * 3. Generics do not accept primitive type arguments.
     *    Use wrapper classes instead.
     *
     * 4. Raw types are not the same as parameterized types.
     *    Raw types omit generic type arguments.
     *
     * 5. Generics do not eliminate every possible runtime
     *    type error. Raw types and unchecked operations can
     *    bypass compile-time protections.
     *
     * 6. The diamond operator does not create an untyped object.
     *    It allows the compiler to infer type arguments.
     *
     * 7. Generic type parameters are not ordinary runtime
     *    variables. Java implements generics using type erasure.
     *    This is explained in the Type Erasure folder.
     *
     *
     * 17. INTERVIEW QUESTIONS
     *
     * Q1. What are Generics in Java?
     * Q2. Why were Generics introduced in Java 5?
     * Q3. What problems do Generics solve?
     * Q4. What is a type parameter?
     * Q5. What is a type argument?
     * Q6. What is a parameterized type?
     * Q7. What is a raw type?
     * Q8. What are the advantages of Generics?
     * Q9. How do Generics provide compile-time type safety?
     * Q10. Why can't we use primitive types as type arguments?
     * Q11. What is the diamond operator?
     * Q12. What is type inference?
     * Q13. What is the difference between a raw List and
     *      a List<String>?
     * Q14. Are Generics limited to Collections?
     * Q15. What are autoboxing and unboxing?
     *
     *
     * 18. QUICK REVISION
     *
     * Generics were introduced in Java 5.
     *
     * Main purpose:
     * Reusable code with compile-time type safety.
     *
     * T:
     * A type parameter placeholder.
     *
     * Integer, String:
     * Examples of actual type arguments.
     *
     * List<String>:
     * A parameterized type.
     *
     * List:
     * A raw type when used without type arguments.
     *
     * <>:
     * Diamond operator, introduced in Java 7.
     *
     * Primitive types:
     * Not allowed as generic type arguments.
     *
     * Wrapper classes:
     * Used instead of primitive types.
     *
     * var:
     * Local variable type inference, introduced in Java 10.
     *
     * ============================================================
     *                    END OF GENERICS FUNDAMENTALS
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Generics Fundamentals , Happy Learning ..!");
    }
}
