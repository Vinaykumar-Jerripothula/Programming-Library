
/*
    ============================================================
                         JAVA DATA TYPES
    ============================================================

    FILE: DataTypes.java

    CONTENTS:
    1. Introduction to Data Types
    2. Java Type System
    3. Primitive Data Types
    4. Sizes, Ranges and Precision
    5. Default Values
    6. Important Primitive Type Rules


    ============================================================
                    1. INTRODUCTION TO DATA TYPES
    ============================================================

    A data type specifies the kind of value that a variable,
    expression or other program construct can represent.

    It determines:

    - What kind of values can be represented.
    - What operations can be performed.
    - How values are interpreted.
    - Which type-checking rules apply.
    - What conversions are permitted.

    Example:

        int age = 22;

        int     -> Data type
        age     -> Variable name
        22      -> Value

    Java is a statically typed language.

    This means that every variable and expression has a type
    determined by Java's compile-time type rules.


    ============================================================
                    2. JAVA TYPE SYSTEM
    ============================================================

    Java divides types into two major categories:

                         Java Types
                              |
                 +------------+------------+
                 |                         |
             Primitive                 Reference
               Types                    Types
                 |                         |
          +------+------+          +------+------+
          |      |      |          |      |      |
         byte  short  int        Classes Arrays Interfaces
          char  long  float       Enums   Records
                double            Type Variables
                 boolean

    A. PRIMITIVE TYPES

    Java has exactly eight primitive types:

    1. byte
    2. short
    3. int
    4. long
    5. float
    6. double
    7. char
    8. boolean

    Primitive types are built into the Java language.

    They represent primitive values directly and are not
    objects.

    B. REFERENCE TYPES

    Reference types include:

    - Class types.
    - Interface types.
    - Array types.
    - Enum types.
    - Record types.
    - Type variables.

    A reference-type variable can hold:

    - A reference to an object.
    - The null reference.

    Examples:

        String name = "Vinay";

        int[] numbers = {10, 20, 30};

    String and arrays are reference types.

    We will study reference types in Part 2.


    ============================================================
                    3. PRIMITIVE DATA TYPES
    ============================================================

    Java's eight primitive types are classified into
    four groups:

    A. Integer Types:
       byte, short, int, long

    B. Floating-Point Types:
       float, double

    C. Character Type:
       char

    D. Boolean Type:
       boolean


    ------------------------------------------------------------
    A. INTEGER TYPES
    ------------------------------------------------------------

    Java provides four signed integer primitive types.

    All four use two's-complement representation.


    3.1 BYTE

    - Size: 8 bits (1 byte)
    - Minimum: -128
    - Maximum: 127
    - Range: -2^7 to (2^7 - 1)

    Example:

        byte age = 22;
        byte temperature = -10;
        byte maximum = 127;

    Invalid:

        byte number = 128;

    Reason:

        128 exceeds the maximum byte value.

    Uses:

        - Binary data.
        - Raw byte streams.
        - Compact numeric representations.

    Important:

        Java does not have an unsigned byte primitive type.
        byte is always signed.


    3.2 SHORT

    - Size: 16 bits (2 bytes)
    - Minimum: -32,768
    - Maximum: 32,767
    - Range: -2^15 to (2^15 - 1)

    Example:

        short year = 2026;
        short temperature = -300;

    Invalid:

        short number = 40000;

    Reason:

        40000 exceeds the maximum short value.

    Important:

        Although short is a 16-bit type, arithmetic
        operations involving short values generally
        promote the operands to int.


    3.3 INT

    - Size: 32 bits (4 bytes)
    - Minimum: -2,147,483,648
    - Maximum: 2,147,483,647
    - Range: -2^31 to (2^31 - 1)

    Example:

        int age = 22;
        int population = 1000000;
        int balance = -5000;

    Important:

        int is Java's default integer literal type.

    Example:

        int number = 100;

    The literal 100 has type int.


    3.4 LONG

    - Size: 64 bits (8 bytes)
    - Minimum: -9,223,372,036,854,775,808
    - Maximum:  9,223,372,036,854,775,807
    - Range: -2^63 to (2^63 - 1)

    Example:

        long population = 8000000000L;
        long distance = 9876543210L;

    Important:

        Use L or l as a suffix to indicate a long literal.

        Prefer uppercase L because lowercase l can look
        similar to the digit 1.

    Example:

        long value = 10000000000L;

    Invalid:

        long value = 10000000000;

    Reason:

        An unsuffixed integer literal is treated as an int
        literal if it fits in int, otherwise Java applies
        special rules for decimal integer literals.

        In this example, the value exceeds the permitted
        range of a decimal int literal.

    Uses:

        - Large counters.
        - Timestamps.
        - Large integer calculations.
        - File sizes.
        - Large numerical values.


    INTEGER TYPE COMPARISON

    Type      Size       Minimum          Maximum
    ------------------------------------------------------------
    byte      8 bits     -128             127
    short     16 bits    -32,768          32,767
    int       32 bits    -2^31            2^31 - 1
    long      64 bits    -2^63            2^63 - 1

    Important:

    - All four types are signed.
    - Their widths are fixed.
    - They use two's-complement representation.
    - Integer arithmetic can overflow.
    - Ordinary integer overflow does not automatically
      throw an exception.


    ------------------------------------------------------------
    B. FLOATING-POINT TYPES
    ------------------------------------------------------------

    Java provides two floating-point primitive types:

        float
        double

    They represent values with fractional components
    and a wide range of magnitudes.

    Java floating-point types follow IEEE 754
    binary floating-point semantics.


    3.5 FLOAT

    - Size: 32 bits (4 bytes).
    - Format: IEEE 754 binary32.
    - Precision: Approximately 6 to 7 significant
      decimal digits.

    Example:

        float price = 99.5f;
        float temperature = 36.6F;

    Important:

        Floating-point literals with a decimal point
        are double literals by default.

    Invalid:

        float value = 10.5;

    Reason:

        10.5 is a double literal, and assigning it to
        float requires an explicit narrowing conversion.

    Correct:

        float value = 10.5f;

    Or:

        float value = (float) 10.5;

    Uses:

        - Graphics.
        - Memory-sensitive numerical calculations.
        - Applications where float precision is sufficient.


    3.6 DOUBLE

    - Size: 64 bits (8 bytes).
    - Format: IEEE 754 binary64.
    - Precision: Approximately 15 to 16 significant
      decimal digits.

    Example:

        double salary = 45000.75;
        double pi = 3.141592653589793;

    Important:

        double is Java's default floating-point type.

    Example:

        double value = 10.5;

    The literal 10.5 has type double.

    Uses:

        - Scientific calculations.
        - Mathematical operations.
        - Measurements.
        - General floating-point calculations.


    FLOAT VS DOUBLE

    Feature          float             double
    ------------------------------------------------------------
    Width            32 bits           64 bits
    Storage          4 bytes            8 bytes
    Format           binary32           binary64
    Precision        ~6-7 digits       ~15-16 digits
    Default          No                 Yes
    Suffix           f or F             d or D (optional)

    Important:

        Precision means significant decimal digits,
        not the number of digits after the decimal point.

        Neither float nor double represents every decimal
        fraction exactly.

    Example:

        System.out.println(0.1 + 0.2);

    Output:

        0.30000000000000004

    Reason:

        Many decimal fractions cannot be represented
        exactly in binary floating-point format.

    For exact decimal arithmetic, especially financial
    calculations, consider BigDecimal.


    ------------------------------------------------------------
    C. CHARACTER TYPE
    ------------------------------------------------------------

    3.7 CHAR

    - Size: 16 bits (2 bytes).
    - Type: Unsigned integer type.
    - Range: 0 to 65,535.
    - Representation: UTF-16 code unit.

    Example:

        char letter = 'A';
        char digit = '7';
        char symbol = '@';

    Character literals use single quotes.

    Important:

        char is not a String.

        char:

            char letter = 'A';

        String:

            String name = "Java";

    A char represents one UTF-16 code unit.
    It does not necessarily represent one complete
    Unicode character.

    Unicode code points outside the Basic Multilingual
    Plane require two char values (a surrogate pair).

    Example:

        int codePoint = 0x1F600;

        String emoji = new String(
            Character.toChars(codePoint)
        );

    Important distinctions:

        UTF-16 code unit:
            A 16-bit unit represented by char.

        Unicode code point:
            A numerical value identifying a Unicode character.

        Grapheme cluster:
            A user-perceived character that may consist
            of multiple code points.

    char can also be assigned an integer value within
    its permitted range.

    Example:

        char letter = 65;

        System.out.println(letter);

    Output:

        A

    Because Unicode code point value 65 represents A.

    Common escape sequences:

        '\n' -> Newline
        '\t' -> Horizontal tab
        '\'' -> Single quote
        '\"' -> Double quote
        '\\' -> Backslash

    Important:

        char is unsigned, unlike byte, short, int and long.


    ------------------------------------------------------------
    D. BOOLEAN TYPE
    ------------------------------------------------------------

    3.8 BOOLEAN

    boolean represents a logical value.

    It has exactly two possible values:

        true
        false

    Example:

        boolean isActive = true;
        boolean isLoggedIn = false;

    Uses:

        - Conditions.
        - Decision making.
        - Loop conditions.
        - Logical expressions.
        - Comparisons.

    Example:

        int age = 22;

        boolean isAdult = age >= 18;

    Important:

        Java does not allow integers to be used as booleans.

    Invalid:

        boolean result = 1;

    Invalid:

        if (1) {
            // ...
        }

    Correct:

        boolean result = true;

        if (result) {
            // ...
        }

    boolean is not interchangeable with numeric types.

    SIZE NOTE:

        Java specifies boolean and its two possible values,
        but does not specify a universally fixed storage
        size for boolean variables.

        Do not assume that every boolean always occupies
        exactly one bit or one byte.


    ============================================================
                    4. COMPLETE PRIMITIVE TYPE TABLE
    ============================================================

    Type       Category          Width
    ------------------------------------------------------------
    byte       Integer           8 bits
    short      Integer           16 bits
    int        Integer           32 bits
    long       Integer           64 bits
    float      Floating-point    32 bits
    double     Floating-point    64 bits
    char       Character         16 bits
    boolean    Logical           Not specified

    Important facts:

    1. Java has exactly eight primitive types.
    2. Integer types are byte, short, int and long.
    3. Floating-point types are float and double.
    4. char is an unsigned 16-bit UTF-16 code unit.
    5. boolean represents true or false.
    6. Primitive types are not objects.
    7. Primitive types do not have instance methods.
    8. Every primitive type has a corresponding wrapper class.
    9. Primitive values cannot be null.
    10. Primitive arithmetic follows Java's defined
        promotion and conversion rules.


    ============================================================
                    5. DEFAULT VALUES
    ============================================================

    Java provides default values for fields and array elements.

    Primitive type       Default value
    ------------------------------------------------------------
    byte                 0
    short                0
    int                  0
    long                 0L
    float                0.0f
    double               0.0d
    char                 '\u0000'
    boolean              false

    Example:

        class Example {

            int number;
            double price;
            boolean active;
            char letter;

        }

    When an object is created, its instance fields receive
    their default values.

    IMPORTANT:

        Local variables do not receive automatic default values.

    Example:

        void display() {

            int number;

            System.out.println(number);

        }

    Compilation error:

        Variable number might not have been initialized.

    A local variable must be definitely assigned before
    it is read.

    Distinction:

        Fields and array elements:
            Automatically initialized with default values.

        Local variables:
            Must be definitely assigned before use.

    Detailed initialization and scope rules belong in
    Variables.java.


    ============================================================
                    6. IMPORTANT PRIMITIVE TYPE RULES
    ============================================================

    1. Java has exactly eight primitive types.

    2. Integer types have fixed widths.

    3. Integer types use two's-complement representation.

    4. Java has no unsigned primitive integer types.

    5. Integer overflow does not automatically throw
       an exception.

    6. Floating-point calculations can introduce
       rounding errors.

    7. Integer literals are int by default, subject
       to Java's literal rules.

    8. Floating-point literals are double by default.

    9. Use L for long literals.

    10. Use f or F for float literals.

    11. char is an unsigned 16-bit UTF-16 code unit.

    12. boolean has only true and false values.

    13. boolean cannot be converted to or from numeric types.

    14. Primitive variables cannot contain null.

    15. Arithmetic involving byte, short and char
        generally promotes their operands to int.

    16. Every primitive type has a corresponding wrapper class.

    17. Primitive types and reference types follow
        different assignment and comparison rules.

    18. Java provides unsigned arithmetic methods for
        certain integer types, but the primitive types
        themselves remain signed.


   
    ============================================================
                    7. REFERENCE DATA TYPES
    ============================================================

    Reference types are types that refer to objects.

    Major categories:

    1. Class types
    2. Interface types
    3. Array types
    4. Enum types
    5. Record types
    6. Type variables

    Examples:

        String name = "Vinay";

        int[] numbers = {10, 20, 30};

        Object obj = new Object();

    IMPORTANT CHARACTERISTICS:

    - Reference variables can hold object references.
    - A reference variable can also hold null.
    - Reference types can have fields and methods.
    - Objects are created at runtime.
    - Multiple references can refer to the same object.
    - Reference types support inheritance and polymorphism
      where applicable.

    Example:

        String first = new String("Java");
        String second = first;

    Both variables refer to the same String object.

    IMPORTANT:

    A reference variable does not contain the complete
    object itself. It holds a reference to the object.

    Java does not expose ordinary pointer arithmetic
    or direct memory-address manipulation.


    ============================================================
              8. PRIMITIVE TYPES VS REFERENCE TYPES
    ============================================================

    Feature             Primitive          Reference
    ------------------------------------------------------------
    Represents          Primitive value     Object reference
    Categories          8 types             Multiple types
    Can be null         No                  Yes
    Methods             No instance methods Yes, depending on type
    Default field value 0/false/etc.        null
    Wrapper classes     Available           Not applicable
    Inheritance         No                  Supported by classes
    Equality            Value comparison    Depends on operator
    Memory              Implementation-     Implementation-
                        dependent           dependent

    Example:

        int a = 10;
        int b = a;

        b = 20;

        System.out.println(a);  // 10

    Primitive assignment copies the value.

    Reference example:

        int[] first = {10, 20};
        int[] second = first;

        second[0] = 100;

        System.out.println(first[0]); // 100

    Both references refer to the same array object.

    IMPORTANT:

    Java is always pass-by-value.

    When a reference is passed to a method, the reference
    value is copied, not the object itself.


    ============================================================
                    9. WRAPPER CLASSES
    ============================================================

    Wrapper classes represent primitive values as objects.

    Java provides eight wrapper classes.

    Primitive Type       Wrapper Class
    -------------------------------------
    byte                 Byte
    short                Short
    int                  Integer
    long                 Long
    float                Float
    double               Double
    char                 Character
    boolean              Boolean

    All eight wrapper classes are in java.lang.

    Example:

        int number = 100;

        Integer object = Integer.valueOf(number);

    Wrapper classes provide:

    - Object representation of primitive values.
    - Conversion and parsing methods.
    - Constants such as MIN_VALUE and MAX_VALUE.
    - Utility methods.
    - Compatibility with APIs requiring objects.

    IMPORTANT:

    Wrapper classes are immutable.

    Once a wrapper object is created, its represented
    value cannot be changed.


    ------------------------------------------------------------
    9.1 BOXING AND UNBOXING
    ------------------------------------------------------------

    BOXING:

    Converting a primitive value into its wrapper object.

        int number = 10;

        Integer obj = Integer.valueOf(number);

    AUTOBOXING:

    Automatic conversion performed by the compiler.

        Integer obj = 10;

    UNBOXING:

    Extracting a primitive value from a wrapper object.

        Integer obj = 20;

        int number = obj.intValue();

    AUTO-UNBOXING:

    Automatic conversion performed by the compiler.

        Integer obj = 20;

        int number = obj;

    IMPORTANT:

    Auto-unboxing a null reference throws
    NullPointerException.

        Integer number = null;

        int value = number; // NullPointerException


    ------------------------------------------------------------
    9.2 WRAPPER CLASS COMPARISON
    ------------------------------------------------------------

    Primitive:

        int a = 100;
        int b = 100;

        System.out.println(a == b); // true

    Wrapper:

        Integer a = 100;
        Integer b = 100;

        System.out.println(a == b); // true

    However, wrapper comparison using == checks
    reference identity when both operands are references.

    Wrapper objects may be cached for certain values.

    Therefore, do not depend on == for comparing
    wrapper values.

    Correct:

        Integer a = 1000;
        Integer b = 1000;

        System.out.println(a.equals(b)); // true

    Or:

        System.out.println(a.intValue() == b.intValue());

    IMPORTANT:

    Use equals() for value comparison of wrapper objects,
    while accounting for possible null references.


    ============================================================
                    10. LITERALS IN JAVA
    ============================================================

    A literal is a fixed value written directly in
    source code.

    Examples:

        int age = 22;

        double price = 99.99;

        char grade = 'A';

        String name = "Java";

        boolean active = true;

    Java has different categories of literals.


    10.1 INTEGER LITERALS

    Decimal:

        int a = 100;

    Binary:

        int b = 0b1010;

    Octal:

        int c = 012;

    Hexadecimal:

        int d = 0x1A;

    Integer literal number systems:

        Decimal       Base 10
        Binary        Base 2
        Octal         Base 8
        Hexadecimal   Base 16

    Binary literals were introduced in Java 7.

    Underscores can improve readability:

        int number = 1_000_000;

        long population = 8_000_000_000L;

    Rules for underscores:

    - Cannot appear at the beginning or end of a number.
    - Cannot appear immediately before or after a decimal point.
    - Cannot appear before an F, D or L suffix.
    - Cannot appear in positions where the Java grammar
      does not permit them.

    Example:

        int value = 1_000;   // Valid
        int value2 = 10_00;  // Valid

        // int x = _100;     // Invalid


    10.2 FLOATING-POINT LITERALS

    Examples:

        double a = 10.5;

        float b = 10.5f;

        double c = 10.5d;

    Suffixes:

        f or F -> float
        d or D -> double

    The double suffix is optional.

    Floating-point literals can also use scientific notation.

        double x = 1.5e3;  // 1500.0

        double y = 2.5e-2; // 0.025


    10.3 CHARACTER LITERALS

    Character literals use single quotes.

        char a = 'A';
        char b = '7';
        char c = '\n';

    A character literal represents one UTF-16 code unit.

    It must not be confused with a String literal.


    10.4 STRING LITERALS

    String literals use double quotes.

        String name = "Java";

        String message = "Hello World";

    String is a reference type, not a primitive type.

    String literals are interned by Java.

    This means identical string literals can share
    the same interned String object.


    10.5 BOOLEAN LITERALS

    Java has exactly two boolean literals:

        true
        false

    Example:

        boolean active = true;

        boolean completed = false;

    IMPORTANT:

    true and false are keywords, not numeric values.


    10.6 NULL LITERAL

    null represents the null reference.

    Example:

        String name = null;

    null can be assigned to reference-type variables.

    It cannot be assigned to primitive variables.

        // int number = null; // Compilation error

    IMPORTANT:

    null is a literal, not a primitive value or object.


    ============================================================
                    11. UNICODE IN JAVA
    ============================================================

    Unicode is a universal character encoding standard
    that assigns code points to characters from many
    writing systems.

    Java uses UTF-16 code units in its char type and
    String representation.

    Example:

        char letter = 'A';

        String text = "Hello";

    A Unicode escape can represent a character:

        char letter = '\u0041';

    Output:

        A

    Unicode code point:

        U+0041 represents A.

    IMPORTANT DISTINCTIONS:

    Unicode:
        Defines code points for characters.

    UTF-16:
        Encodes Unicode code points using one or two
        16-bit code units.

    char:
        Stores one UTF-16 code unit.

    String:
        Stores a sequence of UTF-16 code units.

    A single visible character may consist of multiple
    code points or code units.


    ============================================================
                    12. ESCAPE SEQUENCES
    ============================================================

    Escape sequences represent special characters
    inside character and String literals.

    Sequence       Meaning
    -------------------------------------
    \n              Newline
    \t              Horizontal tab
    \b              Backspace
    \r              Carriage return
    \f              Form feed
    \'              Single quote
    \"              Double quote
    \\              Backslash

    Examples:

        System.out.println("Hello\nWorld");

        System.out.println("Hello\tJava");

        System.out.println("He said \"Hello\"");

    IMPORTANT:

    Java also supports Unicode escapes:

        \u0041

    Unicode escapes are processed before normal
    tokenization of the Java source code.

    They are not simply ordinary runtime escape sequences.


    ============================================================
                    END OF PART 2
    ============================================================

    
    ============================================================
                    13. TYPE CONVERSION
    ============================================================

    Type conversion means converting a value from one data
    type to another.

    Java supports two major categories:

    1. Widening Conversion (Implicit)
    2. Narrowing Conversion (Explicit)


    ------------------------------------------------------------
    13.1 WIDENING PRIMITIVE CONVERSION
    ------------------------------------------------------------

    Widening means converting a smaller-range numeric type
    into a larger-range numeric type.

    It generally happens automatically.

    Conversion order:

        byte -> short -> int -> long -> float -> double
                  char -> int

    Example:

        int number = 100;

        long value = number;

    No explicit cast is required.

    More examples:

        byte a = 10;
        short b = a;

        int c = b;
        long d = c;

        float e = d;
        double f = e;

    IMPORTANT:

    Widening does not always preserve exact precision.

    Example:

        long value = 123456789123456789L;

        float result = value;

    This conversion is permitted, but float cannot
    represent every long value exactly.

    Therefore, widening does not always mean
    lossless conversion.


    ------------------------------------------------------------
    13.2 NARROWING PRIMITIVE CONVERSION
    ------------------------------------------------------------

    Narrowing means converting a value into a type with
    a smaller range or precision.

    It generally requires an explicit cast.

    Example:

        double price = 99.99;

        int value = (int) price;

    Result:

        99

    The fractional part is discarded.

    More examples:

        int a = 100;
        byte b = (byte) a;

        long c = 5000L;
        int d = (int) c;

    IMPORTANT:

    Narrowing may cause:

    - Loss of fractional information.
    - Loss of precision.
    - Overflow or wrapping.
    - Unexpected values.

    Example:

        int number = 130;

        byte result = (byte) number;

    Result:

        -126

    Because the narrowed 8-bit representation is interpreted
    as a signed byte.


    ============================================================
                    14. TYPE CASTING
    ============================================================

    Type casting explicitly converts a value into another type.

    Syntax:

        targetType variable = (targetType) value;

    Example:

        double price = 99.99;

        int amount = (int) price;

    Casting is commonly used for narrowing conversions.

    IMPORTANT:

    Casting does not change the original variable.

    Example:

        double value = 10.75;

        int number = (int) value;

        System.out.println(value);  // 10.75
        System.out.println(number); // 10


    A. CASTING BETWEEN INTEGER TYPES

        int number = 300;

        byte result = (byte) number;

    Result:

        44

    The value cannot fit in byte, so the resulting
    8-bit value is interpreted using signed two's-complement.


    B. CASTING BETWEEN FLOATING-POINT TYPES

        double value = 10.99;

        float result = (float) value;

    The conversion may lose precision.


    C. CASTING FLOATING-POINT TO INTEGER

        double value = 10.99;

        int result = (int) value;

    Result:

        10

    Floating-point to integer conversion:

    - Discards the fractional part.
    - Rounds toward zero.
    - Does not round to the nearest integer.

    Examples:

        (int) 10.9   -> 10
        (int) -10.9  -> -10


    D. CHAR CONVERSION

        char letter = 'A';

        int number = letter;

    Result:

        65

    Reverse conversion:

        int value = 66;

        char letter = (char) value;

    Result:

        'B'

    A char can be converted to an integer type.
    An integer can be explicitly converted to char.


    ============================================================
                    15. TYPE PROMOTION
    ============================================================

    Type promotion occurs when Java automatically converts
    operands into another type during an expression.

    IMPORTANT:

    byte, short and char are generally promoted to int
    when used in arithmetic expressions.

    Example:

        byte a = 10;
        byte b = 20;

        int result = a + b;

    The expression a + b has type int.

    This is invalid:

        byte result = a + b;

    Correct:

        byte result = (byte) (a + b);


    A. UNARY NUMERIC PROMOTION

    Unary numeric promotion applies to certain unary
    operations and also to the operands of shift operators.

    byte, short and char are promoted to int.

    float, int and long generally retain their types.

    Example:

        byte value = 10;

        int result = +value;


    B. BINARY NUMERIC PROMOTION

    Binary numeric promotion applies to arithmetic
    and certain other binary numeric operations.

    Simplified rules:

    - If either operand is double, result is double.
    - Otherwise, if either is float, result is float.
    - Otherwise, if either is long, result is long.
    - Otherwise, result is int.

    Example:

        int a = 10;
        double b = 20.5;

        double result = a + b;

    The int operand is converted to double.

    Another example:

        int a = 10;
        long b = 20L;

        long result = a + b;


    C. COMPOUND ASSIGNMENT

    Compound assignment operators include:

        +=
        -=
        *=
        /=
        %=

    Example:

        byte number = 10;

        number += 5;

    This is valid.

    The compound assignment operator includes an
    implicit conversion back to the left-hand type.

    Conceptually:

        number = (byte) (number + 5);

    However, compound assignment evaluates the left-hand
    expression only once, so it is not identical to
    manually rewriting every possible expression.

    IMPORTANT:

        byte number = 10;

        number = number + 5;

    This is invalid without an explicit cast because
    number + 5 has type int.


    ============================================================
                    16. INTEGER OVERFLOW AND UNDERFLOW
    ============================================================

    Integer overflow occurs when an arithmetic result
    exceeds the maximum representable value.

    Integer underflow occurs when a result goes below
    the minimum representable value.

    Example:

        int number = Integer.MAX_VALUE;

        number++;

    Result:

        -2147483648

    The value wraps around to the minimum int value.

    Another example:

        int number = Integer.MIN_VALUE;

        number--;

    Result:

        2147483647

    IMPORTANT:

    - Ordinary integer overflow does not throw an exception.
    - Java integer arithmetic uses defined fixed-width rules.
    - Overflow can produce unexpected application results.

    To detect overflow, Java provides methods such as:

        Math.addExact(a, b);
        Math.subtractExact(a, b);
        Math.multiplyExact(a, b);
        Math.incrementExact(a);
        Math.decrementExact(a);
        Math.toIntExact(value);

    These methods throw ArithmeticException when
    the corresponding operation overflows.

    Example:

        int result = Math.addExact(
            Integer.MAX_VALUE, 1
        );

    Result:

        ArithmeticException


    ============================================================
                    17. FLOATING-POINT SPECIAL VALUES
    ============================================================

    Java floating-point types support special values.

    A. POSITIVE INFINITY

        double value = Double.POSITIVE_INFINITY;

    B. NEGATIVE INFINITY

        double value = Double.NEGATIVE_INFINITY;

    C. NaN (NOT A NUMBER)

        double value = Double.NaN;

    NaN can result from operations such as:

        double result = 0.0 / 0.0;

    Important NaN rules:

    - NaN is not equal to itself.
    - NaN != NaN evaluates to true.
    - Use Double.isNaN(value) to test for NaN.

    Example:

        double value = Double.NaN;

        System.out.println(value == value); // false

        System.out.println(Double.isNaN(value)); // true


    D. INFINITY CHECK

        double value = 10.0 / 0.0;

    Result:

        Positive infinity.

    Check:

        Double.isInfinite(value);

    IMPORTANT:

    Integer division by zero throws ArithmeticException.

        int result = 10 / 0;

    Floating-point division by zero follows IEEE 754
    rules and produces infinity or NaN as appropriate.


    ============================================================
                    18. FLOATING-POINT PRECISION
    ============================================================

    float and double use binary floating-point representation.

    Many decimal fractions cannot be represented exactly.

    Example:

        double result = 0.1 + 0.2;

        System.out.println(result);

    Output:

        0.30000000000000004

    This is not necessarily a Java bug.

    It is a consequence of binary floating-point representation.

    IMPORTANT:

    Avoid direct equality comparisons for floating-point
    calculations when exact equality is not appropriate.

    Instead, use a tolerance when comparing approximate
    numerical results.

    Example:

        double a = 0.1 + 0.2;
        double b = 0.3;

        double epsilon = 0.0000001;

        boolean equal = Math.abs(a - b) < epsilon;

    For exact decimal arithmetic, use BigDecimal.


    ============================================================
                    19. BIGDECIMAL
    ============================================================

    BigDecimal is a class in java.math.

    It supports arbitrary-precision decimal arithmetic
    subject to available memory and scale limits.

    It is commonly used in financial calculations.

    Example:

        import java.math.BigDecimal;

        BigDecimal a = new BigDecimal("0.1");

        BigDecimal b = new BigDecimal("0.2");

        BigDecimal result = a.add(b);

        System.out.println(result);

    Output:

        0.3

    IMPORTANT:

    Prefer constructing BigDecimal from a String
    when exact decimal values are required.

    Avoid:

        new BigDecimal(0.1);

    This captures the exact decimal representation
    of the already approximated binary double value.

    Useful methods:

        add()
        subtract()
        multiply()
        divide()
        compareTo()
        setScale()
        abs()
        negate()
        max()
        min()

    IMPORTANT:

    BigDecimal is immutable.

    Operations return new BigDecimal objects.

    BigDecimal.equals() compares both numerical value
    and scale.

    BigDecimal.compareTo() compares numerical value
    without requiring equal scales.

    Example:

        BigDecimal a = new BigDecimal("2.0");
        BigDecimal b = new BigDecimal("2.00");

        a.equals(b);     // false
        a.compareTo(b);  // 0


    ============================================================
                    20. IMPORTANT TYPE CONVERSION RULES
    ============================================================

    1. Widening conversions generally happen automatically.

    2. Narrowing conversions generally require explicit casts.

    3. Widening can still lose precision.

    4. Narrowing can lose information or overflow.

    5. byte, short and char are generally promoted to int
       in arithmetic expressions.

    6. If either binary numeric operand is double,
       the result is double.

    7. Otherwise, float takes precedence over long and int.

    8. Otherwise, long takes precedence over int.

    9. Floating-point to integer conversion rounds toward zero.

    10. Compound assignment performs an implicit conversion
        back to the left-hand type.

    11. Ordinary integer overflow does not throw an exception.

    12. Math exact arithmetic methods can detect overflow.

    13. Floating-point types support NaN and infinity.

    14. BigDecimal is useful for exact decimal arithmetic.


    ============================================================
                    21. INTERVIEW REVISION POINTS
    ============================================================

    1. How many primitive types exist in Java?
       Eight.

    2. What is the default integer type?
       int.

    3. What is the default floating-point type?
       double.

    4. Is char signed?
       No, it is an unsigned 16-bit type.

    5. Does Java have unsigned primitive integer types?
       No.

    6. What is widening?
       Conversion to a wider numeric type.

    7. What is narrowing?
       Conversion to a narrower numeric type.

    8. Why does byte + byte produce int?
       Because of binary numeric promotion.

    9. What happens during integer overflow?
       The result follows fixed-width integer arithmetic.

    10. Does integer overflow throw an exception?
        Not for ordinary arithmetic operations.

    11. What is NaN?
        A special floating-point value meaning Not a Number.

    12. Is NaN equal to itself?
        No.

    13. Why can 0.1 + 0.2 differ from 0.3?
        Binary floating-point representation.

    14. Which class supports decimal arithmetic?
        BigDecimal.

    15. What is the difference between equals()
        and compareTo() in BigDecimal?
        equals() considers scale; compareTo() compares
        numerical value.

    16. Can primitive variables hold null?
        No.

    17. Can reference variables hold null?
        Yes.

    18. What happens when a null wrapper is unboxed?
        NullPointerException.

    19. Does casting change the original variable?
        No. It produces a converted value.

    20. What happens when a floating-point value
        is converted to an integer?
        The fractional part is discarded toward zero.


    ============================================================
                    22. DATA TYPES - FINAL CHECKLIST
    ============================================================

    [ ] Understand Java's type system.
    [ ] Know all eight primitive types.
    [ ] Know primitive sizes and ranges.
    [ ] Understand float and double precision.
    [ ] Understand char and UTF-16.
    [ ] Understand boolean.
    [ ] Know default primitive values.
    [ ] Understand reference types.
    [ ] Differentiate primitive and reference types.
    [ ] Understand wrapper classes.
    [ ] Understand boxing and unboxing.
    [ ] Understand primitive and reference equality.
    [ ] Know integer and floating-point literals.
    [ ] Understand binary, octal and hexadecimal literals.
    [ ] Understand String and null literals.
    [ ] Understand Unicode and escape sequences.
    [ ] Understand widening and narrowing conversions.
    [ ] Understand explicit casting.
    [ ] Understand unary and binary numeric promotion.
    [ ] Understand compound assignment.
    [ ] Understand integer overflow and underflow.
    [ ] Understand NaN and infinity.
    [ ] Understand floating-point precision.
    [ ] Know when to use BigDecimal.


    ============================================================
                    END OF DATA TYPES
    ============================================================




*/
