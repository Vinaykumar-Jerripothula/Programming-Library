
class WrapperClasses {

    /*
        ============================================================
                           WRAPPER CLASSES
        ============================================================


        1. DEFINITION

        Wrapper classes convert primitive data types into objects.

        Primitive       Wrapper Class
        --------------------------------
        byte            Byte
        short           Short
        int             Integer
        long            Long
        float           Float
        double          Double
        char            Character
        boolean         Boolean


        2. WRAPPER CLASS HIERARCHY

        Object
          |
          |-- Number (Abstract Class)
          |     |-- Byte
          |     |-- Short
          |     |-- Integer
          |     |-- Long
          |     |-- Float
          |     |-- Double
          |
          |-- Character
          |
          |-- Boolean

        Note:
        - Six numeric wrapper classes extend Number.
        - Character and Boolean do not extend Number.
        - All eight wrapper classes are final.
        - All wrapper objects are immutable.


        3. WHY DO WE NEED WRAPPER CLASSES?

        - Collections work with objects, not primitive types.
        - Required when working with Generics.
        - Allow primitive values to be represented as objects.
        - Provide useful conversion and utility methods.
        - Wrapper references can hold null.


        4. AUTOBOXING AND UNBOXING

        Autoboxing:
        Automatic conversion of primitive into wrapper object.

        int num = 100;
        Integer obj = num;

        Unboxing:
        Automatic conversion of wrapper object into primitive.

        Integer obj = 100;
        int num = obj;

        Note:
        - Both conversions are performed automatically by Java.
        - Autoboxing internally uses valueOf() methods.
        - Unboxing internally uses xxxValue() methods.
        - Unboxing a null wrapper reference causes
          NullPointerException.


        5. IMPORTANT METHODS

        A. valueOf()

        Converts a String or primitive value into its
        corresponding wrapper object, where supported.

        Integer obj = Integer.valueOf("100");
        Integer obj2 = Integer.valueOf(100);

        Note:
        - Character provides valueOf(char).
        - Character does not provide valueOf(String).
        - valueOf() may reuse cached wrapper objects.


        B. parseXxx()

        Converts a String into its corresponding primitive value.

        int num = Integer.parseInt("100");
        double value = Double.parseDouble("10.5");
        boolean flag = Boolean.parseBoolean("true");

        Note:
        - Numeric wrapper classes provide parseXxx() methods.
        - Character does not provide parseCharacter().
        - Boolean.parseBoolean() does not throw
          NumberFormatException for invalid input.


        C. toString()

        Converts a value into its String representation.

        String str = Integer.toString(100);

        Note:
        - Wrapper classes provide static toString() methods.
        - Wrapper objects also have instance toString() methods.


        D. compare()

        Static method used to compare two primitive values.

        int result = Integer.compare(10, 20);

        Returns:
        Negative value -> First value is smaller.
        Zero            -> Both values are equal.
        Positive value  -> First value is greater.


        E. compareTo()

        Instance method used to compare two wrapper objects.

        int result = Integer.valueOf(10).compareTo(20);

        Returns:
        Negative value -> Current object is smaller.
        Zero            -> Both values are equal.
        Positive value  -> Current object is greater.


        F. xxxValue()

        Converts a wrapper object into another primitive type.

        Integer obj = 100;

        byte b = obj.byteValue();
        short s = obj.shortValue();
        int i = obj.intValue();
        long l = obj.longValue();
        float f = obj.floatValue();
        double d = obj.doubleValue();

        Note:
        - These methods are inherited from Number.
        - Character and Boolean do not provide these Number methods.
        - Narrowing conversions may lose information or overflow.


        6. STRING CONVERSION METHODS

        parseXxx()  -> String to primitive.
        valueOf()   -> String/primitive to wrapper object.
        toString()  -> Value to String representation.

        Example:

        int a = Integer.parseInt("100");
        Integer b = Integer.valueOf("100");
        String c = Integer.toString(100);


        7. IMPORTANT CONSTANTS

        Numeric wrapper classes:

        MIN_VALUE -> Minimum representable value.
        MAX_VALUE -> Maximum representable value.
        SIZE      -> Number of bits.
        BYTES     -> Number of bytes.
        TYPE      -> Class object representing primitive type.

        Example:

        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.SIZE);
        System.out.println(Integer.BYTES);
        System.out.println(Integer.TYPE);

        Character:

        Character.MIN_VALUE -> '\\u0000'
        Character.MAX_VALUE -> '\\uFFFF'
        Character.SIZE      -> 16
        Character.BYTES     -> 2
        Character.TYPE      -> char.class

        Boolean:

        Boolean.TRUE
        Boolean.FALSE
        Boolean.TYPE

        Note:
        - TYPE is available in all eight wrapper classes.
        - MIN_VALUE and MAX_VALUE represent the minimum and
          maximum values, not the smallest and largest magnitudes.
        - Floating-point MIN_VALUE represents the smallest
          positive nonzero value, not the most negative value.


        8. WRAPPER OBJECT CACHING

        Wrapper classes may reuse previously created objects
        for certain values instead of creating new objects.

        Standard Java caching behaviour:

        Byte       -> -128 to 127 (all Byte values)
        Short      -> -128 to 127 (guaranteed)
        Integer    -> -128 to 127 (guaranteed minimum range)
        Long       -> -128 to 127 (guaranteed)
        Character  -> 0 to 127 (guaranteed)
        Boolean    -> true and false
        Float      -> No guaranteed value cache
        Double     -> No guaranteed value cache

        Example:

        Integer a = 100;
        Integer b = 100;

        System.out.println(a == b);       // true
        System.out.println(a.equals(b));  // true

        Integer x = 200;
        Integer y = 200;

        System.out.println(x == y);       // Normally false
        System.out.println(x.equals(y));  // true

        Important:

        - valueOf() and autoboxing may use wrapper caches.
        - == compares object references when used with two
          wrapper references.
        - equals() compares wrapper values.
        - Integer caching is guaranteed for -128 to 127.
        - JVM implementations may cache additional values.
        - Never depend on == for comparing wrapper values.


        9. PRIMITIVE VS WRAPPER CLASSES

        Primitive                  Wrapper
        -----------------------------------------
        int                        Integer
        char                       Character
        double                     Double
        boolean                    Boolean

        Important differences:

        - Primitives store values directly.
        - Wrapper variables store object references.
        - Primitives cannot hold null.
        - Wrapper references can hold null.
        - Primitives are not objects.
        - Wrapper classes provide utility methods.
        - Wrapper objects are immutable.
        - Wrapper objects can be used with Collections and Generics.


        10. WRAPPER CLASSES AND COLLECTIONS

        Java Collections cannot use primitive types as generic
        type arguments.

        Invalid:

        ArrayList<int> numbers;

        Valid:

        ArrayList<Integer> numbers;

        Example:

        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(10);

        Autoboxing automatically converts int into Integer.


        11. IMMUTABILITY

        All eight wrapper classes are immutable.

        Once a wrapper object is created, its value cannot
        be changed.

        Example:

        Integer num = 100;
        num = 200;

        Here:
        - The original Integer object is not modified.
        - The reference is reassigned to another Integer object.


        12. IMPORTANT EXCEPTIONS AND SPECIAL BEHAVIOUR

        A. NumberFormatException

        Occurs when an invalid numeric String is passed to
        numeric parsing or conversion methods.

        Example:

        Integer.parseInt("ABC");


        B. NullPointerException

        Occurs when a null wrapper reference is automatically
        unboxed into a primitive.

        Example:

        Integer obj = null;
        int num = obj;


        C. Boolean.parseBoolean()

        Boolean.parseBoolean("true")  -> true
        Boolean.parseBoolean("TRUE")  -> true
        Boolean.parseBoolean("false") -> false
        Boolean.parseBoolean("hello") -> false
        Boolean.parseBoolean(null)    -> false

        Note:
        Only "true" (case-insensitive) produces true.
        Every other input produces false.
        No NumberFormatException is thrown.


        D. Floating-Point Special Values

        Float and Double support:
        - NaN (Not a Number)
        - Positive Infinity
        - Negative Infinity

        Methods:
        - isNaN()
        - isInfinite()
        - isFinite()

        Special equals() behaviour:

        - NaN is not equal to itself using ==.
        - NaN wrapper objects compare equal using equals().
        - Positive zero and negative zero are equal using ==.
        - Positive zero and negative zero are different
          using equals().


        13. IMPORTANT CLASS-SPECIFIC METHODS

        Integer / Long:

        - toBinaryString()
        - toHexString()
        - toOctalString()
        - bitCount()
        - reverse()
        - highestOneBit()
        - lowestOneBit()

        Float / Double:

        - isNaN()
        - isInfinite()
        - isFinite()

        Character:

        - isDigit()
        - isLetter()
        - isLetterOrDigit()
        - isWhitespace()
        - isUpperCase()
        - isLowerCase()
        - toUpperCase()
        - toLowerCase()

        Boolean:

        - parseBoolean()
        - logicalAnd()
        - logicalOr()
        - logicalXor()


        14. METHODS THAT DO NOT EXIST

        Character:

        - No parseCharacter() method.
        - No valueOf(String) method.
        - Use Character.valueOf(char) to obtain a Character object.
        - Use next().charAt(0) with Scanner to read a character.

        Boolean:

        - No parseBoolean() exception for invalid input.
        - Invalid input returns false.

        General:

        - No common parse() method shared by all eight wrappers.
        - Character has no xxxValue() methods inherited from Number.
        - Boolean and Character do not extend Number.


        15. IMPORTANT CONVERSION LIMITATIONS

        - Widening numeric conversions generally preserve the value.
        - Narrowing conversions may lose information or overflow.
        - Numeric conversion methods do not necessarily throw an
          exception when the destination type cannot represent
          the original value.
        - Floating-point conversions may lose precision.
        - Integer conversions can lose higher-order bits.


        16. DEPRECATED CONSTRUCTORS

        Older Java versions allowed explicit wrapper construction:

        Integer obj = new Integer(100);

        These constructors are deprecated since Java 9.

        Recommended:

        Integer obj = Integer.valueOf(100);


        17. IMPORTANT INTERVIEW POINTS

        1. Java provides eight primitive wrapper classes.
        2. All wrapper classes are final and immutable.
        3. Six numeric wrappers extend Number.
        4. Character and Boolean do not extend Number.
        5. Autoboxing converts primitive into wrapper object.
        6. Unboxing converts wrapper object into primitive.
        7. parseXxx() returns a primitive.
        8. valueOf() returns a wrapper object.
        9. Wrapper caching helps reuse objects for certain values.
        10. Use equals() instead of == for value comparison.
        11. Wrapper objects can hold null; primitives cannot.
        12. Unboxing null causes NullPointerException.
        13. Invalid numeric Strings cause NumberFormatException.
        14. Collections use wrapper classes instead of primitives.
        15. Float and Double have special NaN and infinity values.
        16. Integer.MAX_VALUE is 2147483647.
        17. Integer.MIN_VALUE is -2147483648.
        18. Character represents a UTF-16 code unit, not necessarily
            an entire Unicode character.
        19. Character does not have parseCharacter().
        20. Boolean.parseBoolean() returns false for invalid input.
        21. Wrapper object caching makes == unreliable for value
            comparison.
        22. Wrapper classes are useful when an object reference
            or generic type is required.


        ============================================================
                       END OF WRAPPER CLASSES
        ============================================================
    */

    public static void main(String[] args) {

        System.out.println("Wrapper Classes - Practice Area");

    }
}
