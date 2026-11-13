
public class GenericMethodsAndBoundedTypes {

    /*
     * ============================================================
     *          GENERIC METHODS AND BOUNDED TYPE PARAMETERS
     * ============================================================
     *
     * 1. WHAT IS A GENERIC METHOD?
     *
     * A generic method is a method that declares its own
     * type parameter and can operate on different data types.
     *
     * Imagine a method that prints a value.
     *
     * Without Generics:
     *
     *     void printString(String value) {
     *         System.out.println(value);
     *     }
     *
     *     void printInteger(Integer value) {
     *         System.out.println(value);
     *     }
     *
     *     void printDouble(Double value) {
     *         System.out.println(value);
     *     }
     *
     * We need separate methods for different types,
     * even though their logic is identical.
     *
     * With a generic method:
     *
     *     <T> void print(T value) {
     *         System.out.println(value);
     *     }
     *
     * Usage:
     *
     *     print("Java");
     *     print(100);
     *     print(25.5);
     *
     * One method works with different reference types.
     *
     *
     * 2. GENERIC METHOD SYNTAX
     *
     * General syntax:
     *
     *     <TypeParameter> returnType methodName(parameters) {
     *         // Method body
     *     }
     *
     * Example:
     *
     *     public <T> void display(T value) {
     *         System.out.println(value);
     *     }
     *
     * Understand the declaration:
     *
     *     public       -> Access modifier
     *     <T>          -> Declares the method's type parameter
     *     void         -> Return type
     *     display      -> Method name
     *     (T value)    -> Parameter using T
     *
     * Important:
     *
     * The type parameter declaration <T> comes before
     * the return type.
     *
     * This is how Java knows that T is a type parameter
     * belonging to this method.
     *
     *
     * 3. GENERIC METHOD WITH A RETURN TYPE
     *
     * A generic method can also return a value of type T.
     *
     * Example:
     *
     *     public <T> T getValue(T value) {
     *         return value;
     *     }
     *
     * Usage:
     *
     *     String name = getValue("Vinay");
     *     Integer number = getValue(100);
     *
     * The method returns the same type as the supplied argument.
     *
     *
     * 4. GENERIC METHOD WITH MULTIPLE TYPE PARAMETERS
     *
     * A method can declare multiple type parameters.
     *
     * Example:
     *
     *     public <T, U> void display(T first, U second) {
     *         System.out.println(first);
     *         System.out.println(second);
     *     }
     *
     * Usage:
     *
     *     display("Java", 100);
     *
     * Here:
     *
     * T represents String.
     * U represents Integer.
     *
     * Another example:
     *
     *     public <K, V> V getValue(K key, V value) {
     *         System.out.println(key);
     *         return value;
     *     }
     *
     * Different parameters can have different types.
     *
     *
     * 5. GENERIC METHOD INSIDE A NON-GENERIC CLASS
     *
     * A class does not need to be generic to contain
     * a generic method.
     *
     * Example:
     *
     *     class Utility {
     *
     *         public <T> void print(T value) {
     *             System.out.println(value);
     *         }
     *     }
     *
     * Usage:
     *
     *     Utility utility = new Utility();
     *
     *     utility.print("Java");
     *     utility.print(100);
     *
     * The class is not generic.
     * Only the method declares a type parameter.
     *
     *
     * 6. GENERIC METHOD INSIDE A GENERIC CLASS
     *
     * A generic class can also contain generic methods.
     *
     * Example:
     *
     *     class Box<T> {
     *
     *         T value;
     *
     *         public void setValue(T value) {
     *             this.value = value;
     *         }
     *
     *         public <U> void display(U data) {
     *             System.out.println(data);
     *         }
     *     }
     *
     * Here:
     *
     * T -> Class-level type parameter.
     * U -> Method-level type parameter.
     *
     * Usage:
     *
     *     Box<String> box = new Box<>();
     *
     *     box.setValue("Java");
     *     box.display(100);
     *
     * The class's T and method's U are independent.
     *
     *
     * 7. CLASS TYPE PARAMETER VS METHOD TYPE PARAMETER
     *
     * Consider:
     *
     *     class Box<T> {
     *
     *         T value;
     *
     *         public <U> void display(U data) {
     *             System.out.println(value);
     *             System.out.println(data);
     *         }
     *     }
     *
     * T belongs to the class.
     * U belongs to the method.
     *
     * A class type parameter is available throughout
     * the applicable class declaration.
     *
     * A method type parameter is available within
     * that method's declaration.
     *
     * A method can use the class's T without declaring it again.
     *
     * A static method cannot use the class's T directly,
     * but it can declare its own independent type parameter.
     *
     *
     * 8. GENERIC METHOD TYPE INFERENCE
     *
     * Java can often determine the method's type argument
     * automatically.
     *
     * Example:
     *
     *     public <T> void display(T value) {
     *         System.out.println(value);
     *     }
     *
     * Calling:
     *
     *     display("Java");
     *
     * The compiler infers T as String.
     *
     * Calling:
     *
     *     display(100);
     *
     * The compiler infers T as Integer.
     *
     * We can also explicitly supply the type argument:
     *
     *     utility.<String>display("Java");
     *
     * This is called an explicit type witness.
     *
     * Most of the time, type inference makes explicit
     * type arguments unnecessary.
     *
     *
     * 9. WHAT IS A BOUNDED TYPE PARAMETER?
     *
     * Consider a generic method:
     *
     *     <T> void calculate(T value) {
     *         // ...
     *     }
     *
     * T can represent almost any reference type.
     *
     * But what if we want to perform a mathematical
     * operation on the supplied value?
     *
     * We cannot directly write:
     *
     *     value.doubleValue();
     *
     * because the compiler only knows that T is some type.
     *
     * It does not know that T has a doubleValue() method.
     *
     * We solve this using a bounded type parameter.
     *
     * A bounded type parameter restricts which types
     * can be supplied.
     *
     *
     * 10. UPPER BOUND USING extends
     *
     * Syntax:
     *
     *     <T extends SomeType>
     *
     * Example:
     *
     *     public <T extends Number> void display(T value) {
     *         System.out.println(value.doubleValue());
     *     }
     *
     * Now T must be Number or a subtype of Number.
     *
     * Valid type arguments:
     *
     *     Integer
     *     Double
     *     Float
     *     Long
     *     Short
     *     Byte
     *     Number
     *
     * Invalid type arguments:
     *
     *     String
     *     Boolean
     *     Character
     *
     * Why?
     *
     * These types do not extend Number.
     *
     * Because T is bounded by Number, the compiler
     * allows methods declared by Number to be called.
     *
     * Important:
     *
     * The keyword extends is used for both class
     * inheritance and interface bounds in generic declarations.
     *
     *
     * 11. BOUNDED GENERIC CLASS
     *
     * Bounds are not limited to generic methods.
     * Generic classes can also use bounds.
     *
     * Example:
     *
     *     class Calculator<T extends Number> {
     *
     *         private T value;
     *
     *         Calculator(T value) {
     *             this.value = value;
     *         }
     *
     *         double getDoubleValue() {
     *             return value.doubleValue();
     *         }
     *     }
     *
     * Usage:
     *
     *     Calculator<Integer> c1 = new Calculator<>(100);
     *     Calculator<Double> c2 = new Calculator<>(25.5);
     *
     * Invalid:
     *
     *     Calculator<String> c3 = new Calculator<>("Java");
     *
     * The compiler rejects String because it does not
     * satisfy the Number bound.
     *
     *
     * 12. BOUNDED GENERIC INTERFACE
     *
     * Generic interfaces can also restrict their type arguments.
     *
     * Example:
     *
     *     interface Repository<T extends Number> {
     *         void save(T value);
     *     }
     *
     * Valid:
     *
     *     class IntegerRepository
     *             implements Repository<Integer> {
     *
     *         public void save(Integer value) {
     *             // Save value
     *         }
     *     }
     *
     * Invalid:
     *
     *     class StringRepository
     *             implements Repository<String> {
     *     }
     *
     * String does not satisfy the Number bound.
     *
     *
     * 13. MULTIPLE BOUNDS
     *
     * Sometimes a type must satisfy more than one requirement.
     *
     * Java allows multiple bounds using the & symbol.
     *
     * Syntax:
     *
     *     <T extends ClassName & Interface1 & Interface2>
     *
     * Example:
     *
     *     class Employee implements Comparable<Employee> {
     *
     *         public int compareTo(Employee other) {
     *             return 0;
     *         }
     *     }
     *
     *     interface Printable {
     *         void print();
     *     }
     *
     *     public <T extends Employee & Printable> void process(T value) {
     *         value.compareTo(value);
     *         value.print();
     *     }
     *
     * Here T must:
     *
     * 1. Extend Employee.
     * 2. Implement Printable.
     *
     * Therefore, the compiler allows methods from both
     * Employee and Printable to be called.
     *
     * IMPORTANT ORDER RULE:
     *
     * If a class bound exists, it must appear first.
     *
     * Correct:
     *
     *     <T extends Employee & Printable>
     *
     * Incorrect:
     *
     *     <T extends Printable & Employee>
     *
     * A type parameter can have at most one class bound,
     * followed by zero or more interface bounds.
     *
     *
     * 14. MULTIPLE INTERFACE BOUNDS
     *
     * A type parameter can be restricted by multiple interfaces.
     *
     * Example:
     *
     *     interface Printable {
     *         void print();
     *     }
     *
     *     interface Storable {
     *         void store();
     *     }
     *
     *     public <T extends Printable & Storable> void process(T value) {
     *         value.print();
     *         value.store();
     *     }
     *
     * Any supplied type must implement both interfaces.
     *
     *
     * 15. RECURSIVE TYPE BOUNDS
     *
     * A recursive bound is a bound that refers to
     * the type parameter itself.
     *
     * Example:
     *
     *     <T extends Comparable<T>>
     *
     * This means T must implement Comparable using
     * T as its comparison type.
     *
     * Example:
     *
     *     class Student implements Comparable<Student> {
     *
     *         private int marks;
     *
     *         public int compareTo(Student other) {
     *             return Integer.compare(this.marks, other.marks);
     *         }
     *     }
     *
     * A generic method can use this bound:
     *
     *     public <T extends Comparable<T>> T findMaximum(
     *             T first, T second) {
     *
     *         return first.compareTo(second) >= 0 ? first : second;
     *     }
     *
     * The compiler knows that T supports compareTo(T).
     *
     * This pattern is commonly used in sorting and comparison APIs.
     *
     *
     * 16. RECURSIVE BOUNDS WITH MULTIPLE TYPES
     *
     * Recursive bounds can also be combined with interfaces.
     *
     * Example:
     *
     *     <T extends Comparable<T> & Serializable>
     *
     * This requires T to:
     *
     * 1. Implement Comparable<T>.
     * 2. Implement Serializable.
     *
     * The class-bound-first ordering rule still applies
     * if a class bound is included.
     *
     *
     * 17. BOUNDS WITH GENERIC CLASSES AND METHODS
     *
     * A generic class and its method can declare
     * independent bounds.
     *
     * Example:
     *
     *     class Data<T extends Number> {
     *
     *         T value;
     *
     *         public <U extends CharSequence> void display(U text) {
     *             System.out.println(value.doubleValue());
     *             System.out.println(text.length());
     *         }
     *     }
     *
     * Here:
     *
     * T must extend Number.
     * U must implement or extend CharSequence.
     *
     * Both bounds are checked by the compiler.
     *
     *
     * 18. BOUNDS AND INHERITANCE
     *
     * A bounded type parameter can accept the bound itself
     * or a subtype of the bound.
     *
     * Example:
     *
     *     class Calculator<T extends Number> { }
     *
     * Valid:
     *
     *     Calculator<Number> a;
     *     Calculator<Integer> b;
     *     Calculator<Double> c;
     *
     * Invalid:
     *
     *     Calculator<String> d;
     *
     * The bound establishes which type arguments are permitted.
     *
     * It does not mean that Calculator<Integer> is automatically
     * a subtype of Calculator<Number>.
     *
     * Generic type invariance and subtyping are covered
     * in the Wildcards folder.
     *
     *
     * 19. WHAT IF WE DON'T PROVIDE A BOUND?
     *
     * Consider:
     *
     *     <T> void display(T value) { }
     *
     * There is no explicit bound.
     *
     * The compiler treats T as having the upper bound Object.
     *
     * Therefore, only methods available on Object are
     * guaranteed to be accessible through T.
     *
     * With:
     *
     *     <T extends Number>
     *
     * T has Number as its upper bound.
     *
     * Therefore, Number methods are available.
     *
     *
     * 20. GENERIC METHOD VS OVERLOADED METHODS
     *
     * Consider:
     *
     *     void print(String value) { }
     *     void print(Integer value) { }
     *
     * These are overloaded methods.
     *
     * Each method supports a particular parameter type.
     *
     * A generic method:
     *
     *     <T> void print(T value) { }
     *
     * Can support many reference types through one declaration.
     *
     * Use overloading when different types require different
     * behaviour.
     *
     * Use Generics when the same logic works across types.
     *
     *
     * 21. GENERIC METHODS IN STATIC CONTEXT
     *
     * A static method cannot directly use a generic class's
     * type parameter because that parameter belongs to
     * an instance-level generic type.
     *
     * Example:
     *
     *     class Box<T> {
     *
     *         // Invalid:
     *         // static T getValue() { return null; }
     *
     *         // Valid:
     *         static <U> U getValue(U value) {
     *             return value;
     *         }
     *     }
     *
     * U is declared by the static method itself.
     *
     * It does not depend on any Box<T> instance.
     *
     *
     * 22. COMMON COMPILATION MISTAKES
     *
     * 1. Missing the type parameter declaration:
     *
     *     T display(T value) { }
     *
     *    Invalid unless T is already declared in the class
     *    or an enclosing scope.
     *
     *    Correct:
     *
     *     <T> T display(T value) { }
     *
     * 2. Wrong bound:
     *
     *     <T extends Number>
     *
     *    Passing String is invalid.
     *
     * 3. Wrong multiple-bound order:
     *
     *     <T extends Printable & Employee>
     *
     *    Invalid when Employee is a class bound.
     *
     * 4. Calling methods outside the bound:
     *
     *     <T extends Number> void test(T value) {
     *         // value.length(); // Invalid
     *     }
     *
     *    Number does not declare length().
     *
     * 5. Assuming that a generic method must belong
     *    to a generic class.
     *
     *    A non-generic class can contain generic methods.
     *
     *
     * 23. INTERVIEW QUESTIONS
     *
     * Q1. What is a generic method?
     * Q2. What is the syntax of a generic method?
     * Q3. Can a generic method return T?
     * Q4. Can a generic method declare multiple type parameters?
     * Q5. Can a non-generic class contain generic methods?
     * Q6. What is the difference between class-level and
     *     method-level type parameters?
     * Q7. What is type inference in generic methods?
     * Q8. What is an explicit type witness?
     * Q9. What is a bounded type parameter?
     * Q10. What does extends mean in a generic bound?
     * Q11. Can a generic class have a bounded type parameter?
     * Q12. Can a generic interface have a bounded type parameter?
     * Q13. What are multiple bounds?
     * Q14. Why must a class bound appear before interface bounds?
     * Q15. What is a recursive type bound?
     * Q16. What is the purpose of Comparable<T>?
     * Q17. What is the default upper bound of an unbounded T?
     * Q18. Can a static generic method declare its own T?
     * Q19. What is the difference between a generic method
     *      and method overloading?
     *
     *
     * 24. QUICK REVISION
     *
     * Generic method:
     *     <T> void display(T value) { }
     *
     * Generic return type:
     *     <T> T getValue(T value) { return value; }
     *
     * Multiple type parameters:
     *     <T, U> void display(T a, U b) { }
     *
     * Generic method in a generic class:
     *     class Box<T> {
     *         <U> void display(U value) { }
     *     }
     *
     * Upper bound:
     *     <T extends Number>
     *
     * Multiple bounds:
     *     <T extends Employee & Printable>
     *
     * Multiple interface bounds:
     *     <T extends Printable & Storable>
     *
     * Recursive bound:
     *     <T extends Comparable<T>>
     *
     * Explicit type witness:
     *     utility.<String>display("Java");
     *
     * Unbounded type parameter:
     *     <T> is effectively bounded by Object.
     *
     * Class bound ordering:
     *     Class first, then interfaces.
     *
     * ============================================================
     *          END OF GENERIC METHODS AND BOUNDED TYPES
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Generic Methods and Bounded Types , Happy Learning ..!");
    }
}
