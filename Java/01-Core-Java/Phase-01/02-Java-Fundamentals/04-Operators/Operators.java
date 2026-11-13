
public class Operators {

    /*
    ============================================================
                       JAVA OPERATORS
    ============================================================

    CONTENTS:
    1. Introduction to Operators
    2. Arithmetic Operators
    3. Unary Operators
    4. Relational Operators
    5. Logical Operators
    6. Assignment Operators
    7. Bitwise Operators
    8. Shift Operators
    9. Ternary Operator
    10. Other Important Operators
    11. Operator Precedence and Associativity
    12. Common Mistakes and Interview Questions


    ============================================================
                    1. INTRODUCTION TO OPERATORS
    ============================================================

    An operator is a symbol that performs an operation
    on one or more operands.

    Example:

        int result = 10 + 20;

        +       -> Operator
        10, 20  -> Operands
        result  -> Result

    TYPES OF OPERATORS:

    1. Arithmetic
    2. Unary
    3. Relational
    4. Logical
    5. Assignment
    6. Bitwise
    7. Shift
    8. Ternary
    9. Type comparison (instanceof)

    Operators can be classified by operand count:

        Unary       -> One operand
        Binary      -> Two operands
        Ternary     -> Three operands


    ============================================================
                    2. ARITHMETIC OPERATORS
    ============================================================

    Used to perform mathematical operations.

    Operator       Operation
    --------------------------------
    +              Addition
    -              Subtraction
    *              Multiplication
    /              Division
    %              Remainder

    Example:

        int a = 10;
        int b = 3;

        System.out.println(a + b); // 13
        System.out.println(a - b); // 7
        System.out.println(a * b); // 30
        System.out.println(a / b); // 3
        System.out.println(a % b); // 1

    A. INTEGER VS FLOATING-POINT DIVISION

        System.out.println(10 / 3);     // 3
        System.out.println(10.0 / 3);   // 3.333...

    Integer division discards the fractional part.

    B. DIVISION BY ZERO

        int a = 10 / 0;       // ArithmeticException

        double b = 10.0 / 0;  // Infinity

    Integer division by zero throws ArithmeticException.

    Floating-point division by zero produces infinity
    or NaN according to the operands.

    C. MODULUS OPERATOR

    % returns the remainder.

        System.out.println(10 % 3);  // 1
        System.out.println(10 % 2);  // 0
        System.out.println(-10 % 3); // -1

    The remainder has the sign of the dividend.

    D. STRING CONCATENATION

    The + operator also concatenates Strings.

        String name = "Java" + " Programming";

        System.out.println(name);

    If either operand is a String, + performs
    String concatenation.

        System.out.println("Age: " + 22); // Age: 22

    Evaluation is left-to-right according to associativity:

        System.out.println(10 + 20 + "Java"); // 30Java
        System.out.println("Java" + 10 + 20); // Java1020

    E. INTEGER OVERFLOW

        int value = Integer.MAX_VALUE;

        System.out.println(value + 1);

    Integer arithmetic can overflow without throwing
    an exception.


    ============================================================
                    3. UNARY OPERATORS
    ============================================================

    Unary operators operate on one operand.

    A. UNARY PLUS (+)

        int a = +10;

    Indicates a positive value.

    B. UNARY MINUS (-)

        int a = 10;

        int b = -a; // -10

    C. INCREMENT (++)

    Increases a variable by 1.

    Prefix:

        int a = 5;

        int b = ++a;

    a = 6, b = 6

    Postfix:

        int a = 5;

        int b = a++;

    a = 6, b = 5

    D. DECREMENT (--)

    Decreases a variable by 1.

    Prefix:

        int a = 5;

        int b = --a;

    a = 4, b = 4

    Postfix:

        int a = 5;

        int b = a--;

    a = 4, b = 5

    E. LOGICAL NOT (!)

    Reverses a boolean value.

        boolean a = true;

        System.out.println(!a); // false

    F. BITWISE COMPLEMENT (~)

    Reverses every bit of an integral value.

        int a = 5;

        System.out.println(~a); // -6

    Formula for int values:

        ~n = -(n + 1)

    IMPORTANT:

    Increment and decrement operators require a
    variable that can be modified.

    They cannot be applied to arbitrary literals.


    ============================================================
                    4. RELATIONAL OPERATORS
    ============================================================

    Relational operators compare two values.

    They produce a boolean result.

    Operator       Meaning
    --------------------------------
    ==             Equal to
    !=             Not equal to
    >              Greater than
    <              Less than
    >=             Greater than or equal
    <=             Less than or equal

    Example:

        int a = 10;
        int b = 20;

        System.out.println(a == b); // false
        System.out.println(a != b); // true
        System.out.println(a < b);  // true
        System.out.println(a > b);  // false

    A. PRIMITIVE COMPARISON

    == compares primitive values.

        int a = 10;
        int b = 10;

        System.out.println(a == b); // true

    B. REFERENCE COMPARISON

    == compares object references for identity.

        String a = new String("Java");
        String b = new String("Java");

        System.out.println(a == b); // false
        System.out.println(a.equals(b)); // true

    Use equals() to compare String contents.

    IMPORTANT:

    == is not a general content-comparison operator
    for objects.


    ============================================================
                    5. LOGICAL OPERATORS
    ============================================================

    Used to combine or reverse boolean expressions.

    Operator       Meaning
    --------------------------------
    &&             Logical AND
    ||             Logical OR
    !              Logical NOT

    A. LOGICAL AND (&&)

    Returns true only when both operands are true.

        true && true   -> true
        true && false  -> false
        false && true  -> false
        false && false -> false

    B. LOGICAL OR (||)

    Returns true when at least one operand is true.

        true || true   -> true
        true || false  -> true
        false || true  -> true
        false || false -> false

    C. LOGICAL NOT (!)

        !true  -> false
        !false -> true

    D. SHORT-CIRCUIT EVALUATION

    &&:
        If the left operand is false, the right operand
        is not evaluated.

    ||:
        If the left operand is true, the right operand
        is not evaluated.

    Example:

        int a = 10;

        if (a > 0 || ++a > 20) {
            System.out.println(a); // 10
        }

    ++a is not evaluated.

    E. BOOLEAN & AND | OPERATORS

    & and | can also operate on boolean operands.

        true & false -> false
        true | false -> true

    Unlike && and ||, they evaluate both operands.

    IMPORTANT:

    &, | and ^ are also bitwise operators for integral
    operands.


    ============================================================
                    6. ASSIGNMENT OPERATORS
    ============================================================

    Used to assign values to variables.

    A. SIMPLE ASSIGNMENT

        int a = 10;

    B. COMPOUND ASSIGNMENT

    Operator       Equivalent concept
    ---------------------------------------
    +=             a = a + value
    -=             a = a - value
    *=             a = a * value
    /=             a = a / value
    %=             a = a % value
    &=             a = a & value
    |=             a = a | value
    ^=             a = a ^ value
    <<=            a = a << value
    >>=            a = a >> value
    >>>=           a = a >>> value

    Example:

        int a = 10;

        a += 5;  // 15
        a -= 2;  // 13
        a *= 2;  // 26
        a /= 2;  // 13
        a %= 5;  // 3

    C. COMPOUND ASSIGNMENT AND CASTING

        byte number = 10;

        number += 20; // Valid

    But:

        // number = number + 20;
        // Compilation error

    Compound assignment includes an implicit conversion
    to the left-hand variable's type.

    D. ASSIGNMENT IS AN EXPRESSION

        int a, b;

        a = b = 100;

    Both variables receive 100.

    Assignment operators associate right-to-left.


    ============================================================
                    7. BITWISE OPERATORS
    ============================================================

    Bitwise operators perform operations on individual
    bits of integral values.

    Java provides:

    Operator       Meaning
    --------------------------------
    &              Bitwise AND
    |              Bitwise OR
    ^              Bitwise XOR
    ~              Bitwise complement

    A. BITWISE AND (&)

        1 & 1 -> 1
        1 & 0 -> 0
        0 & 1 -> 0
        0 & 0 -> 0

        5 & 3

        5 = 0101
        3 = 0011
        ----------
            0001 = 1

    B. BITWISE OR (|)

        1 | 1 -> 1
        1 | 0 -> 1
        0 | 1 -> 1
        0 | 0 -> 0

        5 | 3

        0101
        0011
        ----
        0111 = 7

    C. BITWISE XOR (^)

    Returns 1 when the corresponding bits differ.

        1 ^ 1 -> 0
        1 ^ 0 -> 1
        0 ^ 1 -> 1
        0 ^ 0 -> 0

        5 ^ 3

        0101
        0011
        ----
        0110 = 6

    D. BITWISE COMPLEMENT (~)

    Reverses every bit.

        ~5 = -6

    Formula:

        ~n = -(n + 1)

    E. COMMON APPLICATIONS

    - Bit masking.
    - Setting and clearing flags.
    - Checking individual bits.
    - Low-level data manipulation.
    - Efficient bitwise algorithms.

    IMPORTANT:

    Bitwise operators work with integral primitive types:
    byte, short, char, int and long.

    Smaller integral operands undergo numeric promotion.


    ============================================================
                    8. SHIFT OPERATORS
    ============================================================

    Shift operators move bits left or right.

    A. LEFT SHIFT (<<)

    Shifts bits to the left.

        int a = 5;

        System.out.println(a << 1); // 10

    Conceptually, shifting left by one position
    multiplies by 2 when no overflow affects the result.

    B. SIGNED RIGHT SHIFT (>>)

    Shifts bits to the right while preserving
    the sign bit.

        int a = 8;

        System.out.println(a >> 1); // 4

    C. UNSIGNED RIGHT SHIFT (>>>)

    Shifts bits right and fills the leftmost positions
    with zeros.

        int a = -8;

        System.out.println(a >>> 1);

    D. DIFFERENCE BETWEEN >> AND >>>

    >>:
        Preserves the sign bit.

    >>>:
        Fills leftmost positions with zeros.

    E. SHIFT DISTANCE

    Java masks the shift distance:

        int  -> only the lowest 5 bits are used.
        long -> only the lowest 6 bits are used.

    Therefore:

        x << 32

    for int uses a shift distance of zero.

    IMPORTANT:

    byte, short and char operands are promoted to int
    before shift operations.

    F. COMMON USES

    - Bit manipulation.
    - Extracting bit fields.
    - Multiplying or dividing by powers of two
      when the numeric behaviour is appropriate.


    ============================================================
                    9. TERNARY OPERATOR
    ============================================================

    The ternary operator is a conditional expression
    that uses three operands.

    Syntax:

        condition ? expression1 : expression2;

    Example:

        int age = 22;

        String result =
            age >= 18 ? "Adult" : "Minor";

    If the condition is true, expression1 is selected.
    Otherwise, expression2 is selected.

    A. NESTED TERNARY

        int marks = 85;

        String grade =
            marks >= 90 ? "A" :
            marks >= 75 ? "B" :
            marks >= 50 ? "C" : "Fail";

    B. IMPORTANT RULES

    - It is an expression and produces a value.
    - Both result expressions must have compatible types.
    - It can replace simple if-else expressions.
    - Excessive nesting reduces readability.


    ============================================================
                    10. OTHER IMPORTANT OPERATORS
    ============================================================

    A. INSTANCEOF

    Checks whether an object is compatible with a type.

        Object obj = "Java";

        System.out.println(obj instanceof String); // true

    B. TYPE CAST OPERATOR

    Converts a value to a compatible type.

        double number = 10.5;

        int value = (int) number; // 10

    C. NEW OPERATOR

    Creates objects or arrays.

        String name = new String("Java");

        int[] numbers = new int[5];

    D. MEMBER ACCESS OPERATOR (.)

    Accesses fields and methods.

        System.out.println("Java".length());

    E. ARRAY ACCESS OPERATOR ([])

    Accesses an array element using an index.

        int[] numbers = {10, 20, 30};

        System.out.println(numbers[0]); // 10

    IMPORTANT:

    The dot operator, array access and new are important
    Java operators/syntax constructs, but they are not
    arithmetic or logical operators.


    ============================================================
                    11. OPERATOR PRECEDENCE
    ============================================================

    Operator precedence determines which operators
    are grouped first in an expression.

    Higher-precedence operators are grouped before
    lower-precedence operators.

    HIGHER TO LOWER PRECEDENCE:

    1. Postfix:
       expr++, expr--

    2. Unary:
       ++, --, +, -, !, ~

    3. Multiplicative:
       *, /, %

    4. Additive:
       +, -

    5. Shift:
       <<, >>, >>>

    6. Relational:
       <, >, <=, >=, instanceof

    7. Equality:
       ==, !=

    8. Bitwise AND:
       &

    9. Bitwise XOR:
       ^

    10. Bitwise OR:
        |

    11. Logical AND:
        &&

    12. Logical OR:
        ||

    13. Ternary:
        ? :

    14. Assignment:
        =, +=, -=, *=, /=, %=, etc.

    A. EXAMPLE

        int result = 10 + 5 * 2;

    Multiplication happens before addition.

        result = 20;

    B. PARENTHESES

        int result = (10 + 5) * 2;

    Parentheses change grouping.

        result = 30;

    C. ASSOCIATIVITY

    Associativity determines grouping when operators
    have the same precedence.

    Left-to-right examples:

        10 - 5 - 2

        (10 - 5) - 2 = 3

    Right-to-left examples:

        a = b = 100

        a = (b = 100)

    IMPORTANT:

    Operator precedence determines grouping,
    not necessarily the order in which every operand
    is evaluated.

    Java generally evaluates operands from left to right.

    Use parentheses to make complex expressions clear.


    ============================================================
                    12. COMMON MISTAKES
    ============================================================

    1. Using = instead of == for comparison.

    2. Using == instead of equals() for String content.

    3. Forgetting integer division behaviour.

    4. Confusing % remainder with percentage.

    5. Confusing prefix and postfix increment.

    6. Assuming && and & have identical evaluation behaviour.

    7. Forgetting that byte, short and char are promoted
       to int in arithmetic expressions.

    8. Ignoring integer overflow.

    9. Confusing >> with >>>.

    10. Ignoring operator precedence.

    11. Writing complex nested ternary expressions.

    12. Assuming assignment operators and normal
        assignment expressions always behave identically.


    ============================================================
                    13. INTERVIEW QUESTIONS
    ============================================================

    1. What is an operator?
       A symbol that performs an operation on operands.

    2. What is the difference between / and %?
       Division produces a quotient; remainder produces
       the remainder of integral division.

    3. What is the difference between ++a and a++?
       Prefix increments before producing the expression
       value; postfix produces the old value before increment.

    4. What is the difference between == and equals()?
       == compares primitive values or reference identity;
       equals() compares object equality as implemented
       by the class.

    5. What is short-circuit evaluation?
       Skipping evaluation of an unnecessary right operand.

    6. What is the difference between && and &?
       && short-circuits; boolean & evaluates both operands.

    7. What is the difference between >> and >>>?
       >> preserves the sign bit; >>> fills with zeros.

    8. What does ~5 produce?
       -6.

    9. What is operator precedence?
       The rules determining how operators are grouped.

    10. What is associativity?
        The grouping direction for operators of equal
        precedence.

    11. What is the result of 10 / 3?
        3.

    12. What is the result of 10.0 / 3?
        Approximately 3.3333333333333335.

    13. What does 5 & 3 produce?
        1.

    14. What does 5 | 3 produce?
        7.

    15. What does 5 ^ 3 produce?
        6.

    16. What is the ternary operator?
        A conditional expression using condition,
        true result and false result.

    17. What is instanceof?
        An operator that checks reference type compatibility.

    18. What is the result of 10 + 20 + "Java"?
        "30Java".

    19. What is the result of "Java" + 10 + 20?
        "Java1020".

    20. What is the result of 10 + 5 * 2?
        20.


    ============================================================
                    14. REVISION CHECKLIST
    ============================================================

    [ ] Arithmetic operators.
    [ ] Unary operators.
    [ ] Prefix and postfix.
    [ ] Relational operators.
    [ ] Logical operators.
    [ ] Short-circuit evaluation.
    [ ] Assignment and compound assignment.
    [ ] Bitwise operators.
    [ ] Shift operators.
    [ ] Ternary operator.
    [ ] instanceof and type casting operators.
    [ ] Operator precedence.
    [ ] Associativity.
    [ ] Common mistakes and output questions.


    ============================================================
                    END OF OPERATORS
    ============================================================

    FILE COMPLETE:
        Java Operators and Expressions

    ============================================================

    */
   public static void main(String[] args) {
    System.out.print("The above is all about the Operators , Happy Learning ..!");
   }
}
