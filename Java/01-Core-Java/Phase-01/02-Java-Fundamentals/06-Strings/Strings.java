
public class Strings {

    /*
    ============================================================
                         JAVA STRINGS
    ============================================================

    CONTENTS:

    PART 1  - String Fundamentals
    PART 2  - String Creation and String Pool
    PART 3  - String Methods
    PART 4  - String Immutability and Internal Concepts
    PART 5  - Unicode and Character Encoding
    PART 6  - String Concatenation and Formatting
    PART 7  - StringBuilder
    PART 8  - StringBuffer
    PART 9  - String vs StringBuilder vs StringBuffer
    PART 10 - Common Mistakes and Interview Questions


    ============================================================
                    PART 1: STRING FUNDAMENTALS
    ============================================================

    1. INTRODUCTION TO STRINGS

    A String is a sequence of characters.

    Java provides the String class to represent text.

        String name = "Vinay";

    String belongs to the java.lang package.

    Therefore, no explicit import is required.

    FEATURES:

    - String is a class, not a primitive data type.
    - String objects are immutable.
    - Strings can be created using literals or constructors.
    - String literals are handled using the String pool.
    - String supports many built-in methods.
    - String implements CharSequence and Comparable<String>.
    - String is final and cannot be subclassed.

    Example:

        String language = "Java";

        System.out.println(language.length()); // 4


    2. STRING AS AN OBJECT

    String is a reference type.

        String a = "Java";

    Here:
        a is a reference variable.
        "Java" is a String object.

    Unlike primitive variables, a String variable stores
    a reference to an object.

    String is a reference type but has special language
    support through literals and the + operator.


    3. STRING CLASS HIERARCHY

    String implements:

        Serializable
        Comparable<String>
        CharSequence
        Constable
        ConstantDesc

    String is a final class.

    StringBuilder and StringBuffer also implement
    CharSequence and Appendable.

    CharSequence represents a readable sequence of
    characters.


    ============================================================
                 PART 2: STRING CREATION AND STRING POOL
    ============================================================

    4. CREATING STRING OBJECTS

    A. USING STRING LITERALS

        String a = "Java";
        String b = "Programming";

    String literals are interned and can share objects
    from the String pool.

    B. USING new KEYWORD

        String a = new String("Java");

    Explicitly creates a new String object.

    The literal "Java" is also interned if it was not
    already present in the pool.

    C. USING CHARACTER ARRAY

        char[] chars = {'J', 'a', 'v', 'a'};

        String a = new String(chars);

    D. USING BYTE ARRAY

        byte[] data = {65, 66, 67};

        String a = new String(data);

    The bytes are decoded using the specified or default
    charset depending on the constructor used.

    Prefer specifying a charset explicitly:

        String a = new String(
            data,
            java.nio.charset.StandardCharsets.UTF_8
        );

    E. USING StringBuilder

        StringBuilder sb = new StringBuilder("Java");

        String a = sb.toString();

    F. USING StringBuffer

        StringBuffer sb = new StringBuffer("Java");

        String a = sb.toString();


    5. STRING CONSTANT POOL

    The String pool is a special runtime-managed area
    where interned String objects are shared.

        String a = "Java";
        String b = "Java";

    Both references point to the same interned String object.

        System.out.println(a == b); // true

    The pool avoids creating duplicate objects for
    identical interned strings.

    IMPORTANT:

    String pool is not a separate Java language data type.
    It is a runtime mechanism for sharing String objects.


    6. HEAP VS STRING POOL

        String a = "Java";
        String b = new String("Java");

    a refers to the interned String.

    b refers to a separately created String object.

        System.out.println(a == b);      // false
        System.out.println(a.equals(b)); // true

    String pool storage is managed as part of the
    runtime heap in modern Java implementations.

    Do not assume that the String pool is a separate
    physical memory region outside the heap.


    7. STRING INTERNING

    intern() returns the canonical pooled representation
    of a String.

        String a = new String("Java");
        String b = a.intern();

        String c = "Java";

        System.out.println(b == c); // true

    If an equal String is already in the pool,
    intern() returns that reference.

    Otherwise, the String is added to the pool and
    its canonical reference is returned.

    IMPORTANT:

    intern() does not make the original String mutable
    or change its contents.


    8. == VS equals()

    ==:
        Compares primitive values or reference identity.

    equals():
        Compares object equality according to the class.

        String a = new String("Java");
        String b = new String("Java");

        System.out.println(a == b);      // false
        System.out.println(a.equals(b)); // true

    String overrides equals() to compare character content.

    Do not use == to compare String contents.

    NOTE:

    String literals may share the same pooled object.
    Therefore, == can sometimes return true for literals.

    Always use equals() for content comparison.


    ============================================================
                    PART 3: STRING METHODS
    ============================================================

    9. length()

    Returns the number of UTF-16 code units in a String.

        String s = "Java";

        System.out.println(s.length()); // 4

    length() is a method, unlike array length.


    10. isEmpty()

    Checks whether the String length is zero.

        "".isEmpty();       // true
        "Java".isEmpty();   // false
        " ".isEmpty();      // false


    11. isBlank() - JAVA 11+

    Checks whether a String is empty or contains only
    whitespace characters as defined by Character.isWhitespace().

        "".isBlank();       // true
        "   ".isBlank();    // true
        "Java".isBlank();  // false

    Difference:

        isEmpty() -> Checks length == 0.
        isBlank() -> Checks empty or whitespace-only.


    12. charAt()

    Returns the char at a specified index.

        String s = "Java";

        System.out.println(s.charAt(0)); // J
        System.out.println(s.charAt(2)); // v

    Index starts at zero.

    Invalid indexes cause StringIndexOutOfBoundsException.

    charAt() returns a UTF-16 code unit, not necessarily
    a complete Unicode character.


    13. toCharArray()

    Converts a String into a new character array.

        String s = "Java";

        char[] chars = s.toCharArray();

    Modifying the returned array does not modify
    the original String.


    14. substring()

    Extracts a portion of a String.

    A. TWO ARGUMENTS

        substring(beginIndex, endIndex)

        String s = "Programming";

        System.out.println(s.substring(0, 4));
        // Prog

    beginIndex is inclusive.
    endIndex is exclusive.

    B. ONE ARGUMENT

        System.out.println(s.substring(4));

    Returns the String from the given index to the end.

    Invalid ranges cause StringIndexOutOfBoundsException.

    Modern Java implementations do not expose a shared
    mutable backing character array through substring().


    15. subSequence()

    Returns a CharSequence representing a portion
    of the String.

        String s = "Programming";

        CharSequence result = s.subSequence(0, 4);

    It uses the same inclusive/exclusive index convention
    as substring().


    16. contains()

    Checks whether a sequence of characters is present.

        String s = "Java Programming";

        s.contains("Java"); // true
        s.contains("Python"); // false

    It performs a case-sensitive search.


    17. startsWith()

    Checks whether a String starts with a given prefix.

        String s = "Java Programming";

        s.startsWith("Java"); // true

    An overloaded version accepts an offset.

        s.startsWith("Pro", 5); // true


    18. endsWith()

    Checks whether a String ends with a given suffix.

        String s = "Java Programming";

        s.endsWith("Programming"); // true


    19. indexOf()

    Returns the index of the first occurrence.

        String s = "Java Programming";

        s.indexOf('a');       // 1
        s.indexOf("gram");    // 10
        s.indexOf('z');       // -1

    Overloads support:
        - Character search.
        - String search.
        - Starting search position.


    20. lastIndexOf()

    Returns the index of the last occurrence.

        String s = "Java Programming";

        s.lastIndexOf('a');

    Returns -1 if the character or sequence is absent.

    Overloads support characters, Strings and positions.


    21. equals()

    Compares String contents.

        String a = "Java";
        String b = "Java";

        a.equals(b); // true

    It is case-sensitive.


    22. equalsIgnoreCase()

    Compares Strings without considering case differences.

        "JAVA".equalsIgnoreCase("java"); // true

    This is not a complete solution for every
    locale-sensitive text comparison requirement.


    23. compareTo()

    Performs lexicographical comparison.

        "Apple".compareTo("Banana"); // negative
        "Java".compareTo("Java");    // 0
        "Zoo".compareTo("Apple");    // positive

    Returns:
        Negative -> First String is smaller.
        Zero     -> Strings are equal in ordering.
        Positive -> First String is greater.

    The result is not guaranteed to be exactly -1 or 1.

    Comparison is based on UTF-16 code unit values.


    24. compareToIgnoreCase()

    Performs lexicographical comparison ignoring case.

        "java".compareToIgnoreCase("JAVA"); // 0


    25. concat()

    Concatenates two Strings.

        String a = "Java";
        String b = a.concat(" Programming");

    Result:

        Java Programming

    concat() returns a new String.

    The original String remains unchanged.


    26. join()

    Joins multiple character sequences using a delimiter.

        String result = String.join(
            ", ",
            "Java",
            "Python",
            "C++"
        );

    Result:

        Java, Python, C++

    It also supports an Iterable of CharSequence.


    27. replace()

    Replaces literal character or character-sequence matches.

        String s = "Java Java";

        s.replace("Java", "Python");

    Result:

        Python Python

    replace() does not use regular expressions.


    28. replaceFirst()

    Replaces the first substring matching a regular expression.

        String s = "Java Java";

        s.replaceFirst("Java", "Python");

    Result:

        Python Java

    The first argument is a regex.

    Replacement strings support special replacement syntax,
    including $ for group references and backslash escaping.


    29. replaceAll()

    Replaces every substring matching a regular expression.

        String s = "Java123Programming456";

        s.replaceAll("[0-9]", "");

    Result:

        JavaProgramming

    IMPORTANT:

        replace()     -> Literal replacement.
        replaceFirst()-> First regex match.
        replaceAll()  -> All regex matches.


    30. toUpperCase()

    Converts characters to uppercase.

        "java".toUpperCase(); // JAVA

    A locale-aware overload is available.

        s.toUpperCase(java.util.Locale.ROOT);

    For locale-independent identifiers, Locale.ROOT
    is generally preferable.


    31. toLowerCase()

    Converts characters to lowercase.

        "JAVA".toLowerCase(); // java

    A locale-aware overload is available.

        s.toLowerCase(java.util.Locale.ROOT);

    Default-locale conversion can produce different
    results in certain languages, such as Turkish.


    32. trim()

    Removes leading and trailing characters whose values
    are at most U+0020.

        String s = "   Java   ";

        s.trim(); // "Java"

    It does not remove all Unicode whitespace.


    33. strip() - JAVA 11+

    Removes leading and trailing Unicode whitespace
    according to Character.isWhitespace().

        String s = "   Java   ";

        s.strip(); // "Java"


    34. stripLeading() - JAVA 11+

    Removes leading whitespace.

        "   Java   ".stripLeading();

        // "Java   "


    35. stripTrailing() - JAVA 11+

    Removes trailing whitespace.

        "   Java   ".stripTrailing();

        // "   Java"


    36. split()

    Splits a String using a regular expression.

        String s = "Java,Python,C++";

        String[] languages = s.split(",");

    Result:

        ["Java", "Python", "C++"]

    A. LIMIT ARGUMENT

        s.split(regex, limit);

    limit > 0:
        Pattern applied at most limit - 1 times.
        Result has at most limit elements.

    limit == 0:
        Trailing empty strings are discarded.

    limit < 0:
        Trailing empty strings are retained.

    Example:

        "a,b,".split(",");    // ["a", "b"]
        "a,b,".split(",", -1); // ["a", "b", ""]

    B. SPECIAL REGEX CHARACTERS

    Dot is a regex metacharacter.

        String s = "a.b.c";

        s.split("\\.");

    Escape regex metacharacters when literal matching
    is intended.


    37. matches()

    Checks whether the entire String matches a regex.

        "12345".matches("[0-9]+"); // true

    IMPORTANT:

    matches() checks the entire input, not merely whether
    some substring matches.


    38. repeat() - JAVA 11+

    Repeats a String a specified number of times.

        "Java".repeat(3);

    Result:

        JavaJavaJava

    Negative repetition count causes IllegalArgumentException.


    39. lines() - JAVA 11+

    Returns a Stream of lines.

        String s = "Java\nPython\nC++";

        s.lines().forEach(System.out::println);

    Line terminators include LF, CR and CRLF.


    40. indent() - JAVA 12+

    Adjusts indentation of each line.

        String s = "Java\nPython";

        String result = s.indent(4);

    A positive value adds indentation.
    A negative value removes indentation where possible.

    Line termination is normalized as part of the operation.


    41. stripIndent() - JAVA 15+

    Removes incidental leading whitespace from a
    multiline String according to the method's rules.

        String s = """
                Java
                Python
                """;

        String result = s.stripIndent();


    42. translateEscapes() - JAVA 15+

    Translates escape sequences in a String.

        String s = "Java\\nProgramming";

        String result = s.translateEscapes();

    Result contains an actual line break.

    This method handles Java-style escape sequences;
    it is not a complete Java source-code parser.


    43. formatted() - JAVA 15+

    Formats a String using the String as a format template.

        String result = "Name: %s, Age: %d"
                .formatted("Vinay", 22);

    Result:

        Name: Vinay, Age: 22


    44. format()

    Static formatting method.

        String result = String.format(
            "Name: %s, Age: %d",
            "Vinay",
            22
        );

    Locale-specific formatting is also supported.


    45. chars()

    Returns an IntStream of UTF-16 code units.

        String s = "Java";

        s.chars().forEach(System.out::println);

    Each value represents a UTF-16 code unit.


    46. codePoints()

    Returns an IntStream of Unicode code points.

        String s = "Java";

        s.codePoints().forEach(System.out::println);

    Unlike chars(), codePoints() combines valid
    surrogate pairs into a single code point.


    47. getBytes()

    Encodes a String into a byte array.

        String s = "Java";

        byte[] data = s.getBytes(
            java.nio.charset.StandardCharsets.UTF_8
        );

    Prefer an explicit charset.

    The no-argument getBytes() uses the default charset.


    48. getChars()

    Copies characters into an existing character array.

        String s = "Java";

        char[] destination = new char[4];

        s.getChars(0, 4, destination, 0);

    Parameters:
        source start index
        source end index
        destination array
        destination start index


    ============================================================
            PART 4: STRING IMMUTABILITY AND INTERNAL CONCEPTS
    ============================================================

    49. STRING IMMUTABILITY

    String objects are immutable.

    Once a String object is created, its content cannot
    be changed.

    Example:

        String s = "Java";

        s.concat(" Programming");

        System.out.println(s); // Java

    concat() creates and returns another String.
    It does not modify the original object.

    Correct:

        s = s.concat(" Programming");

    Now s refers to the new String object.


    50. WHY IS STRING IMMUTABLE?

    Major benefits:

    1. Security:
       Strings are commonly used for file paths,
       class names, URLs and configuration values.

    2. Thread safety:
       Immutable content can safely be shared between
       threads without synchronization for modification.

    3. String pool:
       Shared String objects can be reused safely.

    4. Hashing:
       A String's content does not change after creation,
       making it suitable as a HashMap key.

    5. Predictability:
       A String cannot be modified unexpectedly through
       another reference.


    51. STRING HASH CODE

    String overrides hashCode().

        String s = "Java";

        System.out.println(s.hashCode());

    The hash code is based on String contents.

    Strings with equal contents have equal hash codes.

    Different Strings can still have the same hash code.

    Since Strings are immutable, their hash code remains
    consistent throughout their lifetime.


    52. STRING AS A HASHMAP KEY

        java.util.Map<String, Integer> map =
            new java.util.HashMap<>();

        map.put("Java", 100);

    String is commonly used as a key because its contents
    cannot change after insertion.

    Equal Strings have compatible equals() and hashCode()
    behaviour required by hash-based collections.


    53. STRING INTERNAL REPRESENTATION

    Before Java 9, String implementations commonly used
    a char[] backing representation.

    Java 9 introduced Compact Strings.

    Modern OpenJDK implementations use a byte[] plus
    a coder field to represent String contents.

    Compact Strings can use:
        LATIN-1 representation for suitable characters.
        UTF-16 representation when required.

    IMPORTANT:

    This is an implementation detail, not a guarantee
    that every Java implementation uses the same layout.


    54. STRING FINALITY

    String is declared final.

    Therefore, it cannot be subclassed.

    This helps preserve its immutability and equality
    behaviour.


    ============================================================
                PART 5: UNICODE AND CHARACTER ENCODING
    ============================================================

    55. CHARACTER REPRESENTATION

    Java char is a 16-bit unsigned UTF-16 code unit.

    A char can represent:
        - A character within the Basic Multilingual Plane.
        - One half of a surrogate pair.

    A Unicode code point may require one or two char values.


    56. UTF-16

    UTF-16 represents Unicode code points using:

        One 16-bit code unit:
            For code points in the Basic Multilingual Plane
            excluding the surrogate range.

        Two 16-bit code units:
            For supplementary code points.

    Therefore:

        String.length()

    Counts UTF-16 code units, not necessarily visible
    characters or Unicode code points.


    57. CODE POINT VS CHAR

        String s = "A";

        s.length(); // 1

    A supplementary character can occupy two UTF-16
    code units.

        String emoji = "😀";

        emoji.length(); // 2

        emoji.codePointCount(0, emoji.length()); // 1

    This demonstrates the difference between code units
    and Unicode code points.

    A code point is not always the same as a user-perceived
    character. Some visible characters consist of multiple
    code points joined together.


    58. SURROGATE PAIRS

    UTF-16 uses:
        High surrogate.
        Low surrogate.

    Together they represent a supplementary code point.

        String s = "😀";

        char high = s.charAt(0);
        char low = s.charAt(1);

    Use codePoints() or codePointAt() when processing
    Unicode code points rather than individual code units.


    59. CHARACTER ENCODING

    Encoding converts text into bytes.

    Common encodings:
        UTF-8
        UTF-16
        ISO-8859-1

    Example:

        byte[] bytes = "Java".getBytes(
            java.nio.charset.StandardCharsets.UTF_8
        );

    Decoding:

        String s = new String(
            bytes,
            java.nio.charset.StandardCharsets.UTF_8
        );

    Use the same intended charset for encoding and decoding.

    Character encoding is different from the internal
    representation of a String.


    ============================================================
                PART 6: STRING CONCATENATION AND FORMATTING
    ============================================================

    60. CONCATENATION USING +

    The + operator performs String concatenation when
    one operand is a String.

        String a = "Java";
        String b = "Programming";

        String c = a + " " + b;


    61. CONCATENATION ORDER

        System.out.println(10 + 20 + "Java");
        // 30Java

        System.out.println("Java" + 10 + 20);
        // Java1020

    Expressions are evaluated according to Java's
    evaluation and operator rules.


    62. COMPILE-TIME CONCATENATION

    Constant expressions involving String literals
    can be evaluated at compile time.

        String a = "Java" + "Programming";

    The compiler can represent this as a single
    constant String.


    63. RUNTIME CONCATENATION

        String language = "Java";

        String result = language + " Programming";

    Runtime concatenation may use compiler/runtime
    optimizations.

    Modern Java compilers commonly use invokedynamic-based
    concatenation strategies.

    Do not assume every + operation always creates
    a StringBuilder explicitly.


    64. STRING CONCATENATION IN LOOPS

    Repeated String concatenation can create many
    intermediate String objects.

        String result = "";

        for (int i = 0; i < 1000; i++) {
            result += i;
        }

    This can be inefficient.

    Prefer StringBuilder for repeated accumulation.

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 1000; i++) {
            result.append(i);
        }

        String output = result.toString();


    65. String.valueOf()

    Converts values into String representations.

        String.valueOf(100);      // "100"
        String.valueOf(true);     // "true"
        String.valueOf(10.5);     // "10.5"

    For null references:

        String.valueOf((Object) null); // "null"

    This differs from calling toString() directly
    on a null reference, which throws NullPointerException.


    66. toString()

    Object classes commonly override toString() to provide
    a readable representation.

        Integer number = 100;

        String s = number.toString();

    Calling toString() on a null reference throws
    NullPointerException.


    67. PARSING STRINGS

    Converting text into primitive values:

        int a = Integer.parseInt("100");

        double b = Double.parseDouble("10.5");

        boolean c = Boolean.parseBoolean("true");

    Invalid numeric text can cause NumberFormatException.

    Boolean.parseBoolean() returns true only when the
    input equals "true", ignoring case. Other inputs
    return false.


    68. REGULAR EXPRESSIONS

    Strings support regex operations through methods
    such as matches(), split(), replaceAll() and replaceFirst().

    Example:

        String number = "12345";

        boolean valid = number.matches("[0-9]+");

    For reusable patterns, use java.util.regex.Pattern.

        java.util.regex.Pattern pattern =
            java.util.regex.Pattern.compile("[0-9]+");

        boolean result = pattern.matcher("12345").matches();

    Pattern is reusable and can avoid recompiling
    the same regex repeatedly.


    69. STRING IN SWITCH

    String has been supported as a switch selector
    since Java 7.

        String language = "Java";

        switch (language) {
            case "Java" ->
                System.out.println("Backend");

            case "Python" ->
                System.out.println("Data Science");

            default ->
                System.out.println("Other");
        }

    A null selector causes NullPointerException
    in a traditional String switch unless handled
    separately.


    ============================================================
                    PART 7: STRINGBUILDER
    ============================================================

    70. INTRODUCTION TO STRINGBUILDER

    StringBuilder is a mutable sequence of characters.

    Unlike String, StringBuilder content can be modified
    without creating a new object for every operation.

    StringBuilder is not synchronized.

    It is generally preferred for string construction
    within a single thread.


    71. CREATING STRINGBUILDER

        StringBuilder sb = new StringBuilder();

    Creates an empty builder with default capacity 16.

        StringBuilder sb = new StringBuilder(50);

    Creates a builder with initial capacity 50.

        StringBuilder sb = new StringBuilder("Java");

    Initializes it with the supplied String.


    72. MUTABILITY

        StringBuilder sb = new StringBuilder("Java");

        sb.append(" Programming");

        System.out.println(sb);

    The same builder object is modified.

    String:
        Immutable.

    StringBuilder:
        Mutable.


    73. length()

    Returns the number of characters currently stored.

        StringBuilder sb = new StringBuilder("Java");

        sb.length(); // 4


    74. capacity()

    Returns the current allocated character capacity.

        StringBuilder sb = new StringBuilder();

        sb.capacity(); // 16

    Capacity is not the same as length.

    Length:
        Number of stored characters.

    Capacity:
        Amount of character storage available before
        expansion is required.


    75. append()

    Appends data to the end.

        StringBuilder sb = new StringBuilder("Java");

        sb.append(" Programming");
        sb.append(100);
        sb.append(true);

    Supports many primitive types, objects,
    character sequences and arrays of characters.

    append() returns the same StringBuilder,
    allowing method chaining.


    76. insert()

    Inserts data at a specified position.

        StringBuilder sb = new StringBuilder("Jva");

        sb.insert(1, "a");

        // Java

    Existing characters are shifted to make room.

    Invalid indexes cause StringIndexOutOfBoundsException.


    77. delete()

    Deletes a range of characters.

        StringBuilder sb = new StringBuilder("Java Programming");

        sb.delete(4, 16);

        // Java

    Start index is inclusive.
    End index is exclusive.


    78. deleteCharAt()

    Deletes the character at a specified index.

        StringBuilder sb = new StringBuilder("Java");

        sb.deleteCharAt(1);

        // Jva


    79. replace()

    Replaces characters in a specified range.

        StringBuilder sb = new StringBuilder("Java");

        sb.replace(0, 4, "Python");

        // Python

    Start index is inclusive.
    End index is exclusive.


    80. reverse()

    Reverses the character sequence.

        StringBuilder sb = new StringBuilder("Java");

        sb.reverse();

        // avaJ

    The implementation handles surrogate pairs
    so that valid pairs are not simply reversed
    into invalid order.


    81. charAt()

    Returns the char at a given index.

        StringBuilder sb = new StringBuilder("Java");

        sb.charAt(0); // J


    82. setCharAt()

    Changes the character at a given index.

        StringBuilder sb = new StringBuilder("Java");

        sb.setCharAt(0, 'L');

        // Lava


    83. substring()

    Returns a String containing a portion of the builder.

        StringBuilder sb = new StringBuilder("Programming");

        String result = sb.substring(0, 7);

    The builder itself is not modified.


    84. subSequence()

    Returns a CharSequence representing a portion
    of the builder.


    85. setLength()

    Changes the builder's logical length.

        StringBuilder sb = new StringBuilder("Java");

        sb.setLength(2);

        // Ja

    Increasing length fills newly exposed positions
    with null characters ('\u0000').


    86. ensureCapacity()

    Ensures that the builder has at least the requested
    capacity.

        StringBuilder sb = new StringBuilder();

        sb.ensureCapacity(100);


    87. trimToSize()

    Attempts to reduce capacity to the current length.

        StringBuilder sb = new StringBuilder(100);

        sb.append("Java");

        sb.trimToSize();


    88. CONVERTING TO STRING

        StringBuilder sb = new StringBuilder("Java");

        String result = sb.toString();

    Creates a String representation of the builder's
    current contents.


    89. CAPACITY GROWTH

    StringBuilder expands its capacity when necessary.

    A common implementation growth strategy is:

        newCapacity = oldCapacity * 2 + 2

    The required minimum capacity is also considered.

    IMPORTANT:

    This growth formula describes common OpenJDK
    implementation behaviour, not a Java language guarantee.


    90. STRINGBUILDER THREAD SAFETY

    StringBuilder is not synchronized.

    Concurrent modifications from multiple threads
    require external synchronization or another
    appropriate coordination mechanism.


    ============================================================
                    PART 8: STRINGBUFFER
    ============================================================

    91. INTRODUCTION TO STRINGBUFFER

    StringBuffer is a mutable sequence of characters.

    It is similar to StringBuilder but its methods
    are synchronized.

    It provides thread-safe individual method operations
    when used according to its synchronization model.


    92. CREATING STRINGBUFFER

        StringBuffer sb = new StringBuffer();

    Default initial capacity: 16.

        StringBuffer sb = new StringBuffer(50);

    Creates a buffer with capacity 50.

        StringBuffer sb = new StringBuffer("Java");

    Initializes it with the supplied String.


    93. MUTABILITY

        StringBuffer sb = new StringBuffer("Java");

        sb.append(" Programming");

    The existing buffer is modified.


    94. STRINGBUFFER METHODS

    StringBuffer provides methods similar to StringBuilder:

        append()
        insert()
        delete()
        deleteCharAt()
        replace()
        reverse()
        charAt()
        setCharAt()
        substring()
        subSequence()
        setLength()
        ensureCapacity()
        trimToSize()
        length()
        capacity()
        toString()

    These operations are generally synchronized
    at the individual method level.


    95. THREAD SAFETY

    StringBuffer synchronizes its methods.

    This helps protect individual method operations
    from concurrent interference.

    However, multiple method calls that form one logical
    operation may still require external synchronization.

    Example:

        if (buffer.length() > 0) {
            buffer.deleteCharAt(0);
        }

    The sequence is not automatically atomic as a whole.

    Another thread could modify the buffer between calls.


    96. STRINGBUFFER CAPACITY

    Capacity and growth behaviour are conceptually
    similar to StringBuilder.

    Both classes support:

        length()
        capacity()
        ensureCapacity()
        trimToSize()


    97. CONVERTING TO STRING

        StringBuffer sb = new StringBuffer("Java");

        String result = sb.toString();


    ============================================================
            PART 9: STRING VS STRINGBUILDER VS STRINGBUFFER
    ============================================================

    98. COMPARISON

    Feature          String       StringBuilder    StringBuffer
    ------------------------------------------------------------
    Mutability       Immutable    Mutable          Mutable
    Thread-safe      Yes*         No               Synchronized
    Modification     New String   Same builder     Same buffer
    Synchronization  Not needed   Not synchronized Synchronized
    Performance      Good for     Good for         Usually slower
                     fixed text   construction     due to locking

    *String's immutable content can safely be shared.
     This does not make every surrounding operation
     or reference-management action automatically thread-safe.


    99. WHEN TO USE STRING

    Use String when:
        - Text should remain unchanged.
        - Values are shared between components.
        - Working with identifiers and keys.
        - Building fixed or small text values.
        - Using String APIs and literals.


    100. WHEN TO USE STRINGBUILDER

    Use StringBuilder when:
        - Repeated concatenation is required.
        - Constructing large Strings.
        - Building text inside loops.
        - Multiple modifications are required.
        - The builder is confined to one thread or
          appropriately synchronized externally.


    101. WHEN TO USE STRINGBUFFER

    Use StringBuffer when:
        - A mutable character sequence is shared between
          threads.
        - Synchronized individual method operations
          are useful.
        - Existing APIs specifically require it.

    In many modern applications, StringBuilder with
    appropriate external synchronization or thread
    confinement is preferable.


    102. PERFORMANCE

    String:
        Repeated modifications can create intermediate
        objects.

    StringBuilder:
        Usually efficient for repeated modifications
        in a single-threaded context.

    StringBuffer:
        Synchronization adds overhead compared with
        StringBuilder in typical single-threaded use.

    Actual performance depends on workload, JVM,
    allocation behaviour and optimizations.

    Avoid assuming one class is always fastest.


    103. IMPORTANT DIFFERENCES

    String:

        String s = "Java";
        s.concat(" Programming");

        // Original String remains unchanged.

    StringBuilder:

        StringBuilder sb = new StringBuilder("Java");
        sb.append(" Programming");

        // Original builder is modified.

    StringBuffer:

        StringBuffer buffer = new StringBuffer("Java");
        buffer.append(" Programming");

        // Original buffer is modified.


    ============================================================
                    PART 10: COMMON MISTAKES
    ============================================================

    104. COMMON STRING MISTAKES

    1. Using == instead of equals() for content comparison.

    2. Assuming String methods modify the original object.

    3. Forgetting that substring() uses an exclusive end index.

    4. Confusing isEmpty() with isBlank().

    5. Confusing trim() with strip().

    6. Treating replaceAll() as literal replacement.

    7. Forgetting that split() accepts a regular expression.

    8. Forgetting that matches() checks the entire String.

    9. Assuming length() counts visible Unicode characters.

    10. Using the default charset without considering
        portability.

    11. Calling toString() on a null reference.

    12. Using repeated String concatenation in large loops.

    13. Assuming intern() modifies the original reference.

    14. Assuming all String literals require new objects.

    15. Assuming the String pool is outside the heap.


    105. COMMON STRINGBUILDER / STRINGBUFFER MISTAKES

    1. Confusing length() and capacity().

    2. Assuming StringBuilder is thread-safe.

    3. Assuming StringBuffer makes a multi-method sequence
       atomic.

    4. Forgetting that toString() returns a separate String.

    5. Confusing substring() with delete().

    6. Assuming setLength() only reduces length.

    7. Assuming the exact capacity growth formula is
       guaranteed by Java.

    8. Assuming shallow copying of object arrays duplicates
       their referenced objects.


    ============================================================
                    PART 11: INTERVIEW QUESTIONS
    ============================================================

    106. STRING FUNDAMENTALS

    1. What is String in Java?
       An immutable class representing a sequence
       of characters.

    2. Is String a primitive or reference type?
       Reference type.

    3. Is String final?
       Yes.

    4. Why is String immutable?
       Security, sharing, thread safety, hashing
       and predictability.

    5. What is the String pool?
       A runtime mechanism for sharing canonical
       String objects.

    6. What is intern()?
       Returns the canonical pooled representation
       of a String.

    7. Difference between == and equals()?
       Reference identity versus object equality.

    8. What happens when a String is concatenated?
       A resulting String is produced; the original
       String is not modified.

    9. Can String be inherited?
       No. It is final.

    10. Why can String be used as a HashMap key?
        Its immutable content gives stable equality
        and hash-code behaviour.


    107. STRING METHODS

    11. Difference between length() and capacity()?
        String has length(); builders and buffers
        also provide capacity().

    12. Difference between isEmpty() and isBlank()?
        Empty checks zero length; blank also checks
        whitespace-only content.

    13. Difference between trim() and strip()?
        trim() removes characters up to U+0020;
        strip() uses Unicode whitespace rules.

    14. Difference between replace() and replaceAll()?
        Literal replacement versus regex replacement.

    15. Difference between substring() and subSequence()?
        substring() returns String; subSequence()
        returns CharSequence.

    16. Difference between indexOf() and lastIndexOf()?
        First occurrence versus last occurrence.

    17. What does compareTo() return?
        A negative, zero or positive comparison result.

    18. What does split() use?
        A regular expression.

    19. What does matches() check?
        Whether the entire String matches a regex.

    20. What does intern() return?
        The canonical pooled String reference.


    108. STRINGBUILDER AND STRINGBUFFER

    21. Difference between String and StringBuilder?
        Immutable versus mutable.

    22. Difference between StringBuilder and StringBuffer?
        StringBuilder is not synchronized;
        StringBuffer synchronizes its methods.

    23. Which is usually faster?
        StringBuilder in typical single-threaded
        modification workloads.

    24. Is StringBuilder thread-safe?
        No.

    25. Is StringBuffer thread-safe?
        Its individual synchronized methods are protected,
        but compound operations may need external locking.

    26. What is capacity?
        The current allocated character storage.

    27. What is ensureCapacity()?
        Ensures a minimum capacity.

    28. What is trimToSize()?
        Attempts to reduce capacity to current length.

    29. What does reverse() do?
        Reverses the character sequence.

    30. What does setLength() do?
        Changes the logical character sequence length.


    109. OUTPUT-BASED QUESTIONS

    Q1:

        String a = "Java";
        String b = "Java";

        System.out.println(a == b);

    Output:
        true

    Reason:
        Both refer to the same interned literal.

    Q2:

        String a = new String("Java");
        String b = new String("Java");

        System.out.println(a == b);

    Output:
        false

    Reason:
        Two distinct String objects.

    Q3:

        String a = "Java";
        String b = new String("Java");

        System.out.println(a.equals(b));

    Output:
        true

    Reason:
        Equal String contents.

    Q4:

        String s = "Java";

        s.concat(" Programming");

        System.out.println(s);

    Output:
        Java

    Reason:
        String is immutable and the result was ignored.

    Q5:

        String s = "Java";

        s = s.concat(" Programming");

        System.out.println(s);

    Output:
        Java Programming

    Q6:

        StringBuilder sb = new StringBuilder("Java");

        sb.append(" Programming");

        System.out.println(sb);

    Output:
        Java Programming

    Q7:

        String s = "Java";

        System.out.println(s.length());

    Output:
        4

    Q8:

        String s = "Java";

        System.out.println(s.substring(1, 3));

    Output:
        av

    Q9:

        System.out.println("Java".equals("java"));

    Output:
        false

    Q10:

        System.out.println("Java".equalsIgnoreCase("java"));

    Output:
        true


    ============================================================
                    PART 12: REVISION CHECKLIST
    ============================================================

    STRING:

    [ ] String fundamentals.
    [ ] String creation.
    [ ] String pool.
    [ ] Heap and pool relationship.
    [ ] intern().
    [ ] == vs equals().
    [ ] String immutability.
    [ ] String methods.
    [ ] String comparison.
    [ ] String conversion.
    [ ] String formatting.
    [ ] Regular expressions.
    [ ] String concatenation.
    [ ] Compile-time and runtime concatenation.
    [ ] String hashCode().
    [ ] Unicode and UTF-16.
    [ ] Code points and surrogate pairs.
    [ ] Character encoding.
    [ ] Compact Strings.
    [ ] String in switch.

    STRINGBUILDER:

    [ ] Creation and constructors.
    [ ] Mutability.
    [ ] length() and capacity().
    [ ] append().
    [ ] insert().
    [ ] delete() and deleteCharAt().
    [ ] replace().
    [ ] reverse().
    [ ] setCharAt().
    [ ] setLength().
    [ ] ensureCapacity().
    [ ] trimToSize().
    [ ] toString().
    [ ] Thread safety.

    STRINGBUFFER:

    [ ] Creation and constructors.
    [ ] Mutability.
    [ ] Synchronized methods.
    [ ] Thread safety limitations.
    [ ] Capacity management.
    [ ] Common methods.
    [ ] toString().

    COMPARISON:

    [ ] String vs StringBuilder.
    [ ] StringBuilder vs StringBuffer.
    [ ] Immutability vs mutability.
    [ ] Thread safety.
    [ ] Performance.
    [ ] Real-world use cases.


    ============================================================
                         END OF STRINGS
    ============================================================

    FILE COMPLETE:
        String
        StringBuilder
        StringBuffer

    ============================================================

    */

   public static void main(String[] args) {
    System.out.println("The above is all about Strings , StringBuffer and StringBuilder , Happy Learning ..!");
   }
}
