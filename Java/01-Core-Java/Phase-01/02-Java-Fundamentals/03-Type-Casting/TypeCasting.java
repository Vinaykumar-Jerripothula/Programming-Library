
public class TypeCasting {

    /*
    ============================================================
                       JAVA TYPE CASTING
    ============================================================

    CONTENTS:

    1. Introduction to Type Casting
    2. Primitive Type Casting
    3. Widening Type Casting
    4. Narrowing Type Casting
    5. Type Promotion in Expressions
    6. Assignment Conversion
    7. Reference Type Casting
    8. Upcasting and Downcasting
    9. instanceof Operator
    10. Special Casting and Conversions
    11. Common Mistakes and Edge Cases
    12. Interview Questions
    13. Revision Checklist


    ============================================================
                    1. INTRODUCTION TO TYPE CASTING
    ============================================================

    Type casting is the process of converting a value
    from one data type to another compatible data type.

    Example:

        int number = 100;

        double value = number;

    Here, an int value is converted into a double.

    WHY IS TYPE CASTING REQUIRED?

    - To perform operations involving different data types.
    - To store a value in a variable of another compatible type.
    - To control the type used in an expression.
    - To work with parent and child class references.
    - To convert between different numeric representations.

    TWO MAIN CATEGORIES:

                      Type Conversion
                            |
               +------------+------------+
               |                         |
          Primitive Casting       Reference Casting
               |                         |
           Widening                  Upcasting
           Narrowing                 Downcasting

    IMPORTANT:

    Primitive casting converts primitive values.

    Reference casting changes the reference type through
    which an object is accessed. It does not transform
    the actual object into another class.


    ============================================================
                    2. PRIMITIVE TYPE CASTING
    ============================================================

    Primitive casting converts one primitive data type
    into another compatible primitive data type.

    Java has eight primitive data types:

        byte
        short
        int
        long
        float
        double
        char
        boolean

    Numeric primitive types can be converted according
    to Java's conversion rules.

    boolean is not compatible with numeric primitive
    types for casting.

    Two main types:

    1. Widening conversion - automatic in permitted contexts.
    2. Narrowing conversion - generally requires explicit cast.


    ============================================================
                    3. WIDENING TYPE CASTING
    ============================================================

    Widening conversion converts a value from a type
    to another type that can represent its range or
    precision more broadly according to Java's rules.

    It is also called implicit type conversion.

    Syntax:

        smallerType variable1 = value;

        largerType variable2 = variable1;

    Example:

        int number = 100;

        double value = number;

    The compiler automatically converts int to double.

    A. PRIMITIVE WIDENING CONVERSION TABLE

    Common permitted widening conversions:

        byte  -> short, int, long, float, double

        short -> int, long, float, double

        char  -> int, long, float, double

        int   -> long, float, double

        long  -> float, double

        float -> double

    IMPORTANT:

    char does not widen to short, even though both
    occupy 16 bits.

    boolean cannot be widened to any numeric type.

    B. WIDENING EXAMPLES

        byte a = 10;
        short b = a;

        int c = b;
        long d = c;

        float e = d;
        double f = e;

    C. WIDENING WITH CHAR

        char ch = 'A';

        int number = ch;

        System.out.println(number); // 65

    char is an unsigned 16-bit integral type representing
    UTF-16 code units.

    D. WIDENING DOES NOT ALWAYS PRESERVE EXACT PRECISION

    Widening is not necessarily lossless in terms of
    numerical precision.

    Example:

        long number = 123456789123456789L;

        float value = number;

    The float type cannot represent every long value
    exactly.

    Similarly:

        long number = 9007199254740993L;

        double value = number;

    The double value may be rounded because double
    cannot represent every large integer exactly.

    IMPORTANT:

    Widening means a permitted automatic conversion.
    It does not guarantee exact preservation of every value.


    ============================================================
                    4. NARROWING TYPE CASTING
    ============================================================

    Narrowing conversion converts a value into a type
    with a smaller range or precision.

    It generally requires explicit casting.

    Syntax:

        targetType variable = (targetType) value;

    Example:

        double number = 100.75;

        int value = (int) number;

        System.out.println(value); // 100

    The fractional part is discarded.

    A. COMMON NARROWING CONVERSIONS

        double -> float

        float  -> long, int, short, byte, char

        long   -> int, short, byte, char

        int    -> short, byte, char

        short  -> byte

    B. NARROWING EXAMPLE

        int number = 130;

        byte value = (byte) number;

        System.out.println(value); // -126

    The value cannot be represented as a byte.

    Narrowing integral conversion keeps the low-order
    bits of the value and interprets them using the
    destination type's representation.

    C. INTEGER OVERFLOW DURING NARROWING

        int number = 300;

        byte value = (byte) number;

        System.out.println(value); // 44

    A byte can represent values from -128 to 127.

    The narrowing conversion does not throw an exception
    when the value is outside that range.

    D. FLOATING-POINT TO INTEGER CONVERSION

        double number = 12.99;

        int value = (int) number;

        System.out.println(value); // 12

    Important rules:

    - Fractional digits are discarded.
    - Conversion truncates toward zero.
    - It does not round to the nearest integer.

    Examples:

        (int) 12.99   -> 12
        (int) -12.99  -> -12
        (int) 12.0    -> 12

    E. FLOATING-POINT TO INTEGRAL EDGE CASES

    When converting floating-point values to int or long:

    - NaN converts to 0.
    - Positive infinity converts to the maximum value.
    - Negative infinity converts to the minimum value.
    - Values outside the destination range are clamped
      to the corresponding minimum or maximum value.

    Example:

        double value = Double.POSITIVE_INFINITY;

        int number = (int) value;

        System.out.println(number); // 2147483647

    F. DOUBLE TO FLOAT

        double value = 123.456789;

        float number = (float) value;

    Precision may be lost because float has less
    precision than double.

    G. CHAR NARROWING

        int number = 65;

        char ch = (char) number;

        System.out.println(ch); // A

    The numeric value is converted to a UTF-16 code unit.

    IMPORTANT:

    Narrowing may cause:
        - Loss of precision.
        - Truncation.
        - Overflow or unexpected values.
        - Loss of information.


    ============================================================
                    5. TYPE PROMOTION IN EXPRESSIONS
    ============================================================

    Type promotion is the automatic conversion of operands
    to appropriate types while evaluating expressions.

    Java performs numeric promotion during arithmetic
    and other numeric operations.

    A. UNARY NUMERIC PROMOTION

    Unary numeric promotion applies to certain unary
    operations and numeric contexts.

    byte, short and char are promoted to int.

    Example:

        byte a = 10;

        int result = +a;

    Even though a is byte, the unary numeric promotion
    produces an int value.

    B. BINARY NUMERIC PROMOTION

    When two numeric operands are used in a binary
    numeric operation, Java determines a common
    promoted type.

    Rules:

    1. If either operand is double, both are promoted
       to double.

    2. Otherwise, if either operand is float, both
       are promoted to float.

    3. Otherwise, if either operand is long, both
       are promoted to long.

    4. Otherwise, both operands are promoted to int.

    C. EXAMPLES

        byte a = 10;
        byte b = 20;

        int result = a + b;

    a and b are promoted to int.

    Therefore:

        byte result = a + b;

    causes a compilation error without an explicit cast.

    D. MIXED DATA TYPES

        int a = 10;
        double b = 20.5;

        double result = a + b;

    The int value is promoted to double.

    E. LONG AND FLOAT

        long a = 100L;
        float b = 20.5f;

        float result = a + b;

    The long operand is converted to float.

    Precision may be lost.

    F. EXPRESSION RESULT TYPE

        byte a = 10;
        byte b = 20;

        System.out.println(a + b);

    The expression has type int.

    G. INCREMENT AND DECREMENT

        byte number = 10;

        number++;

    This is valid.

    Increment and decrement operators have special
    language-defined behaviour and do not require
    an explicit cast in this case.

    H. COMPOUND ASSIGNMENT

        byte number = 10;

        number += 20;

    This is valid.

    Compound assignment includes an implicit conversion
    back to the left-hand variable's type.

    Conceptually:

        number = (byte) (number + 20);

    This is an explanatory equivalent for the conversion
    behaviour, not a complete replacement for the
    compound assignment operator's semantics.

    I. COMPOUND ASSIGNMENT VS NORMAL ASSIGNMENT

        byte a = 10;

        // a = a + 20; // Compilation error

        a += 20;        // Valid

    The normal addition expression produces int.

    Compound assignment performs the required implicit
    conversion to the target type.


    ============================================================
                    6. ASSIGNMENT CONVERSION
    ============================================================

    Assignment conversion is the conversion applied
    when assigning an expression to a variable.

    Example:

        double value = 100;

    The int value is widened to double.

    A. CONSTANT EXPRESSION NARROWING

    Java allows certain compile-time constant expressions
    of type int to be assigned to byte, short or char
    when their values fit in the destination range.

    Example:

        byte a = 100;

        short b = 200;

        char c = 65;

    These are valid because the constant values fit.

    B. OUT-OF-RANGE CONSTANT

        // byte a = 200;

    Compilation error because 200 is outside the
    byte range.

    C. VARIABLE VS CONSTANT EXPRESSION

        int value = 100;

        // byte a = value;

    Compilation error because value is a variable,
    not a compile-time constant expression.

    Explicit casting is required:

        byte a = (byte) value;

    D. FINAL CONSTANT VARIABLE

        final int value = 100;

        byte a = value;

    Valid because value is a constant variable whose
    value fits in the byte range.

    IMPORTANT:

    Constant narrowing is a special assignment conversion
    rule. It does not mean Java automatically narrows
    every int expression.


    ============================================================
                    7. REFERENCE TYPE CASTING
    ============================================================

    Reference casting applies to object references
    in class and interface hierarchies.

    Unlike primitive casting, it does not convert
    the actual object into another object.

    It changes the reference type through which
    an object is accessed.

    Example:

        class Animal { }

        class Dog extends Animal { }

        Animal animal = new Dog();

    The object is still a Dog.

    The reference variable is of type Animal.

    A. REFERENCE TYPE COMPATIBILITY

    Reference types can be related through:

        Class inheritance
        Interface implementation
        Interface inheritance

    Casting is permitted only when the types have
    a valid compile-time casting relationship.

    B. COMPILE-TIME VS RUNTIME CHECKING

    Reference casting involves two important checks:

    Compile-time:
        Determines whether the conversion is permitted
        by Java's type rules.

    Runtime:
        Checks whether the actual object is compatible
        with the requested reference type when required.

    An invalid runtime cast causes ClassCastException.


    ============================================================
                    8. UPCASTING AND DOWNCASTING
    ============================================================

    A. UPCASTING

    Upcasting means assigning a child-class object
    reference to a parent-class reference.

    Example:

        class Animal {

            void eat() {
                System.out.println("Eating");
            }
        }

        class Dog extends Animal {

            void bark() {
                System.out.println("Barking");
            }
        }

        Dog dog = new Dog();

        Animal animal = dog;

    This is upcasting.

    It is automatic because every Dog is an Animal.

    B. UPCASTING CHARACTERISTICS

    - Usually implicit.
    - No explicit cast is required.
    - Supports runtime polymorphism.
    - Only members accessible through the reference
      type can be directly accessed.

    Example:

        animal.eat();

        // animal.bark(); // Compilation error

    The reference type is Animal.

    C. DOWNCASTING

    Downcasting means converting a parent-class
    reference into a child-class reference.

    Example:

        Animal animal = new Dog();

        Dog dog = (Dog) animal;

        dog.bark();

    Explicit casting is required.

    D. UNSAFE DOWNCASTING

        Animal animal = new Animal();

        // Dog dog = (Dog) animal;

    This compiles when the types are cast-convertible,
    but causes ClassCastException at runtime.

    The actual object is an Animal, not a Dog.

    E. ACTUAL OBJECT TYPE VS REFERENCE TYPE

        Animal animal = new Dog();

    Reference type:
        Animal

    Runtime object type:
        Dog

    The reference type determines which members are
    accessible at compile time.

    The runtime object type determines which overridden
    instance method implementation executes.

    Example:

        class Animal {

            void sound() {
                System.out.println("Animal sound");
            }
        }

        class Dog extends Animal {

            @Override
            void sound() {
                System.out.println("Bark");
            }

            void bark() {
                System.out.println("Dog barking");
            }
        }

        Animal animal = new Dog();

        animal.sound(); // Bark

        // animal.bark(); // Compilation error

        Dog dog = (Dog) animal;

        dog.bark(); // Dog barking

    IMPORTANT:

    Casting does not change the actual object's runtime type.

    F. DOWNCASTING AND POLYMORPHISM

    A common pattern:

        Animal animal = new Dog();

        if (animal instanceof Dog) {

            Dog dog = (Dog) animal;

            dog.bark();
        }

    Check compatibility before downcasting when
    the runtime type is uncertain.


    ============================================================
                    9. INSTANCEOF OPERATOR
    ============================================================

    instanceof checks whether an object is compatible
    with a particular reference type.

    Syntax:

        objectReference instanceof Type

    It returns a boolean.

    Example:

        Animal animal = new Dog();

        System.out.println(animal instanceof Dog);    // true
        System.out.println(animal instanceof Animal); // true

    A. NULL CHECK

        Animal animal = null;

        System.out.println(animal instanceof Dog); // false

    instanceof returns false when the reference is null.

    B. PATTERN MATCHING FOR INSTANCEOF

    Pattern matching for instanceof became permanent
    in Java 16.

    Example:

        Object obj = "Java";

        if (obj instanceof String text) {

            System.out.println(text.length());
        }

    If the test succeeds, text is available in its
    applicable scope and has type String.

    This avoids a separate explicit cast.

    C. IMPORTANT RULES

    - instanceof works with reference types.
    - It cannot be used with primitive values.
    - A null reference produces false.
    - Compile-time type compatibility still applies.
    - Pattern matching can declare a variable after
      a successful type check.

    D. INSTANCEOF VS CASTING

    instanceof:
        Checks runtime type compatibility.

    Casting:
        Requests a reference conversion.

    instanceof does not perform the cast itself,
    unless pattern matching declares the typed variable.


    ============================================================
                    10. SPECIAL CASTING AND CONVERSIONS
    ============================================================

    A. STRING TO PRIMITIVE

    String cannot be directly cast to a primitive type.

    Incorrect:

        String value = "100";

        // int number = (int) value;

    Use parsing methods.

        int a = Integer.parseInt("100");

        long b = Long.parseLong("1000");

        float c = Float.parseFloat("12.5");

        double d = Double.parseDouble("99.99");

        boolean e = Boolean.parseBoolean("true");

    Invalid numeric strings can cause NumberFormatException.

    Boolean.parseBoolean() returns true only when the
    String equals "true", ignoring case. Other values
    produce false.

    B. PRIMITIVE TO STRING

    Common approaches:

        int number = 100;

        String a = String.valueOf(number);

        String b = Integer.toString(number);

        String c = "" + number;

    String.valueOf() is a general conversion method
    for many value types.

    C. STRING TO CHARACTER

    There is no Character.parseChar() method.

    Use charAt() when the String contains the desired
    character.

        String text = "Java";

        char ch = text.charAt(0);

    Output:

        J

    D. CHARACTER TO STRING

        char ch = 'A';

        String text = String.valueOf(ch);

    E. BOXING

    Boxing converts a primitive value into its
    corresponding wrapper object.

        int number = 100;

        Integer value = Integer.valueOf(number);

    F. AUTOBOXING

    Autoboxing is automatic boxing by the compiler.

        Integer value = 100;

    The primitive int is converted to Integer.

    G. UNBOXING

    Unboxing converts a wrapper object into its
    corresponding primitive value.

        Integer value = 100;

        int number = value.intValue();

    H. AUTO-UNBOXING

    The compiler automatically extracts the primitive.

        Integer value = 100;

        int number = value;

    IMPORTANT:

    Unboxing a null wrapper reference causes
    NullPointerException.

        Integer value = null;

        // int number = value;

    I. WRAPPER CONVERSION METHODS

    Wrapper classes provide methods such as:

        Integer.parseInt()
        Integer.valueOf()
        Integer.intValue()

        Double.parseDouble()
        Double.valueOf()
        Double.doubleValue()

        Long.parseLong()
        Long.valueOf()
        Long.longValue()

    J. NULL REFERENCE CASTING

    null can be assigned to reference types.

        String text = null;

        Object obj = (String) null;

    Casting null to a reference type produces null.

    null cannot be cast to a primitive type.


    ============================================================
                    11. COMMON MISTAKES AND EDGE CASES
    ============================================================

    A. ASSUMING WIDENING ALWAYS PRESERVES PRECISION

        long number = 123456789123456789L;

        double value = number;

    The value may lose precision.

    B. ASSUMING NARROWING THROWS AN EXCEPTION

        int number = 300;

        byte value = (byte) number;

    This is permitted and produces a narrowed value.

    C. CONFUSING CASTING WITH PARSING

        int number = Integer.parseInt("100");

    Parsing converts textual representation into
    a numeric value.

    Casting converts between compatible types.

    D. CONFUSING REFERENCE CASTING WITH OBJECT CONVERSION

        Animal animal = new Dog();

        Dog dog = (Dog) animal;

    No new Dog object is created.

    Both references refer to the same object.

    E. OPERATOR PRECEDENCE

        double value = (double) 5 / 2;

    Result:

        2.5

    But:

        double value = (double) (5 / 2);

    Result:

        2.0

    In the second example, integer division happens
    before casting.

    F. COMPOUND ASSIGNMENT

        byte number = 10;

        number += 1000;

    This is valid, but narrowing may change the value.

    G. NULL UNBOXING

        Integer number = null;

        // int value = number;

    Causes NullPointerException at runtime.

    H. INVALID REFERENCE CAST

        Object obj = new String("Java");

        // Integer number = (Integer) obj;

    Causes ClassCastException at runtime.

    I. BOOLEAN CASTING

        boolean value = true;

        // int number = (int) value;

    Compilation error.

    boolean cannot be converted to numeric primitive types.

    J. CHAR AND SHORT

        char ch = 'A';

        // short number = ch;

    Compilation error.

    Both are 16-bit types, but char is unsigned
    and short is signed. There is no direct widening
    conversion from char to short.


    ============================================================
                    12. IMPORTANT CASTING RULES
    ============================================================

    1. Widening conversions are generally implicit.

    2. Narrowing conversions generally require explicit casts.

    3. Widening does not always guarantee exact precision.

    4. Narrowing can cause truncation or information loss.

    5. byte, short and char are promoted to int in
       ordinary numeric arithmetic.

    6. Mixed numeric expressions follow binary
       numeric promotion rules.

    7. Compound assignment includes an implicit conversion
       back to the left-hand operand's type.

    8. Certain constant int expressions can be assigned
       to byte, short or char when their values fit.

    9. boolean cannot be converted to or from numeric
       primitive types.

    10. Upcasting is generally implicit.

    11. Downcasting generally requires an explicit cast.

    12. Invalid downcasting can cause ClassCastException.

    13. Casting a reference does not change the actual
        runtime type of its object.

    14. instanceof checks runtime reference compatibility.

    15. String-to-number conversion requires parsing,
        not primitive casting.

    16. Boxing converts primitive values into wrappers.

    17. Unboxing converts wrapper objects into primitives.

    18. Unboxing null causes NullPointerException.

    19. null can be converted to a reference type,
        but not to a primitive type.

    20. Java performs compile-time and runtime checks
        for reference conversions.


    ============================================================
                    13. INTERVIEW QUESTIONS
    ============================================================

    1. What is type casting?
       Converting a value from one compatible type
       into another type.

    2. What are the two types of primitive casting?
       Widening and narrowing.

    3. What is widening?
       An automatic conversion to a permitted wider type.

    4. What is narrowing?
       An explicit conversion to a type with a narrower
       range or precision.

    5. Does widening always preserve precision?
       No.

    6. What happens when double is cast to int?
       The fractional part is discarded by truncation
       toward zero, with defined handling for extreme
       values, infinities and NaN.

    7. What is type promotion?
       Automatic promotion of operands during expression
       evaluation.

    8. Why does byte + byte produce int?
       Because byte operands undergo unary/binary
       numeric promotion.

    9. What is the difference between a = a + 1
       and a += 1 for byte?
       The first expression generally requires an explicit
       cast; the second includes implicit narrowing.

    10. What is constant narrowing?
        A special assignment conversion that permits
        certain fitting constant int expressions to be
        assigned to byte, short or char.

    11. What is upcasting?
        Assigning a child reference to a parent reference.

    12. What is downcasting?
        Converting a parent reference to a child reference.

    13. Is upcasting implicit?
        Yes.

    14. Is downcasting implicit?
        Generally no.

    15. What happens when an invalid downcast is performed?
        ClassCastException at runtime.

    16. Does casting change the actual object type?
        No.

    17. What is instanceof?
        An operator that checks reference type compatibility.

    18. Can String be directly cast to int?
        No. Use Integer.parseInt().

    19. What is autoboxing?
        Automatic conversion from primitive to wrapper.

    20. What is unboxing?
        Conversion from wrapper to primitive.

    21. What happens when null is unboxed?
        NullPointerException.

    22. Can boolean be cast to int?
        No.

    23. Can char be widened to int?
        Yes.

    24. Can char be widened to short?
        No.

    25. What is the difference between casting and parsing?
        Casting converts compatible types; parsing converts
        textual representations into values.


    ============================================================
                    14. REVISION CHECKLIST
    ============================================================

    [ ] Understand type casting.
    [ ] Know primitive and reference casting.
    [ ] Understand widening conversion.
    [ ] Understand narrowing conversion.
    [ ] Know primitive conversion rules.
    [ ] Understand precision loss and overflow.
    [ ] Know floating-point to integer conversion.
    [ ] Understand unary numeric promotion.
    [ ] Understand binary numeric promotion.
    [ ] Understand compound assignment conversion.
    [ ] Know constant narrowing.
    [ ] Understand upcasting and downcasting.
    [ ] Understand reference type vs runtime object type.
    [ ] Know instanceof and pattern matching.
    [ ] Understand String parsing and conversion.
    [ ] Know boxing and unboxing.
    [ ] Understand null-related casting behaviour.
    [ ] Know ClassCastException and NumberFormatException.
    [ ] Practice casting output-prediction questions.


    ============================================================
                    END OF TYPE CASTING
    ============================================================

    FILE COMPLETE:
        Primitive Type Casting
        Widening and Narrowing
        Numeric Promotion
        Assignment Conversion
        Reference Type Casting
        Upcasting and Downcasting
        instanceof
        Boxing and Unboxing
        Special Conversions
        Interview Questions

    ============================================================

    */

   public static void main(String[] args) {
        System.out.println("The above is all about Type Casting , Happy Learning ..!");
   }
}
