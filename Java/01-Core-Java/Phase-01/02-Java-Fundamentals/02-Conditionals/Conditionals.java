
public class Conditionals {

    /*
    ============================================================
                  JAVA CONTROL FLOW STATEMENTS
    ============================================================

    CONTENTS:

    1. Introduction to Control Flow
    2. Conditional Statements
    3. Switch Statement
    4. Switch Expressions
    5. Looping Statements
    6. For Loop
    7. While Loop
    8. Do-While Loop
    9. Enhanced For Loop (For-Each)
    10. Nested Loops
    11. Jump Statements
    12. Labeled Break and Continue
    13. Return Statement
    14. Important Rules and Edge Cases
    15. Interview Questions
    16. Revision Checklist


    ============================================================
                    1. INTRODUCTION TO CONTROL FLOW
    ============================================================

    Control flow determines the order in which statements
    are executed in a program.

    By default, Java executes statements sequentially,
    from top to bottom.

    Control flow statements allow us to change this
    execution order.

    Java control flow statements are broadly classified
    into three categories:

                        Control Flow
                             |
             +---------------+---------------+
             |               |               |
          Selection       Iteration       Branching
             |               |               |
          if              for             break
          if-else         while           continue
          switch          do-while        return

    A. SEQUENTIAL EXECUTION

    Statements execute one after another.

        int a = 10;
        int b = 20;
        int sum = a + b;

        System.out.println(sum);

    B. SELECTION STATEMENTS

    Used to execute different blocks based on conditions.

        if
        if-else
        switch

    C. ITERATION STATEMENTS

    Used to execute a block repeatedly.

        for
        while
        do-while
        enhanced for

    D. BRANCHING STATEMENTS

    Used to alter the normal execution flow.

        break
        continue
        return


    ============================================================
                    2. CONDITIONAL STATEMENTS
    ============================================================

    Conditional statements execute code depending on
    whether a boolean condition evaluates to true or false.

    Java provides:

        if
        if-else
        if-else-if
        nested if-else

    IMPORTANT:

    Java conditions must evaluate to boolean.

    Unlike some languages, Java does not treat integers
    such as 0 and 1 as false and true.


    A. IF STATEMENT

    Executes a block only when the condition is true.

    Syntax:

        if (condition) {
            // Statements
        }

    Example:

        int age = 22;

        if (age >= 18) {
            System.out.println("Eligible to vote");
        }

    If the condition is false, the block is skipped.

    B. IF-ELSE STATEMENT

    Executes one block when the condition is true
    and another block when it is false.

    Syntax:

        if (condition) {
            // True block
        } else {
            // False block
        }

    Example:

        int number = 7;

        if (number % 2 == 0) {
            System.out.println("Even");
        } else {
            System.out.println("Odd");
        }

    Exactly one of the two blocks executes.

    C. IF-ELSE-IF LADDER

    Used when multiple conditions need to be checked.

    Syntax:

        if (condition1) {
            // Block 1
        } else if (condition2) {
            // Block 2
        } else if (condition3) {
            // Block 3
        } else {
            // Default block
        }

    Example:

        int marks = 85;

        if (marks >= 90) {
            System.out.println("Grade A");
        } else if (marks >= 75) {
            System.out.println("Grade B");
        } else if (marks >= 50) {
            System.out.println("Grade C");
        } else {
            System.out.println("Fail");
        }

    Execution:

    - Conditions are checked from top to bottom.
    - The first true condition's block executes.
    - Remaining conditions are skipped.
    - else executes when all previous conditions are false.

    IMPORTANT:

    Condition ordering matters.

    Incorrect ordering:

        if (marks >= 50) {
            System.out.println("Grade C");
        } else if (marks >= 90) {
            System.out.println("Grade A");
        }

    The second condition is unreachable for values
    satisfying the first condition.

    D. NESTED IF-ELSE

    An if or else block can contain another if-else.

    Example:

        int age = 22;
        boolean hasLicense = true;

        if (age >= 18) {

            if (hasLicense) {
                System.out.println("Can drive");
            } else {
                System.out.println("Need a license");
            }

        } else {
            System.out.println("Underage");
        }

    Nested conditions are useful when one decision
    depends on another decision.

    E. INDEPENDENT IF VS IF-ELSE-IF

    Independent if statements:

        int number = 10;

        if (number > 0) {
            System.out.println("Positive");
        }

        if (number % 2 == 0) {
            System.out.println("Even");
        }

    Both conditions are checked independently.

    if-else-if:

        if (number > 0) {
            System.out.println("Positive");
        } else if (number % 2 == 0) {
            System.out.println("Even");
        }

    Only the first matching block executes.

    IMPORTANT:

    Use independent if statements when multiple blocks
    may need to execute.

    Use if-else-if when exactly one matching branch
    should execute.


    F. BOOLEAN EXPRESSIONS IN CONDITIONS

    Conditions can use relational and logical operators.

    Relational operators:

        ==      Equal to
        !=      Not equal to
        >       Greater than
        <       Less than
        >=      Greater than or equal to
        <=      Less than or equal to

    Logical operators:

        &&      Logical AND
        ||      Logical OR
        !       Logical NOT

    Example:

        int age = 22;
        boolean hasId = true;

        if (age >= 18 && hasId) {
            System.out.println("Allowed");
        }

    G. SHORT-CIRCUIT EVALUATION

    Java supports short-circuit logical operators.

    Logical AND (&&):

    If the left operand is false, the right operand
    is not evaluated.

        int number = 0;

        if (number != 0 && 100 / number > 5) {
            System.out.println("Valid");
        }

    The division is never evaluated because the
    first condition is false.

    Logical OR (||):

    If the left operand is true, the right operand
    is not evaluated.

        if (number == 0 || 100 / number > 5) {
            System.out.println("Safe");
        }

    H. SHORT-CIRCUIT VS NON-SHORT-CIRCUIT OPERATORS

    &&  -> Short-circuit AND.
    &   -> Boolean AND without short-circuiting.

    ||  -> Short-circuit OR.
    |   -> Boolean OR without short-circuiting.

    Example:

        int a = 10;

        if (a > 0 || ++a > 20) {
            System.out.println(a);
        }

    Output:

        10

    ++a is not evaluated because the left side is true.

    IMPORTANT:

    & and | can also operate on integral values as
    bitwise operators.

    I. TERNARY OPERATOR

    The conditional operator ?: is an expression,
    not a control statement.

    Syntax:

        condition ? expression1 : expression2;

    Example:

        int age = 22;

        String result =
            age >= 18 ? "Adult" : "Minor";

    Nested ternary expressions are valid but can reduce
    readability. Prefer if-else for complex decisions.


    ============================================================
                    3. SWITCH STATEMENT
    ============================================================

    switch is a selection statement used to choose
    one execution path based on an expression.

    Syntax:

        switch (expression) {

            case value1:
                // Statements
                break;

            case value2:
                // Statements
                break;

            default:
                // Default statements
        }

    A. TRADITIONAL SWITCH EXAMPLE

        int day = 3;

        switch (day) {

            case 1:
                System.out.println("Monday");
                break;

            case 2:
                System.out.println("Tuesday");
                break;

            case 3:
                System.out.println("Wednesday");
                break;

            default:
                System.out.println("Invalid day");
        }

    B. CASE LABELS

    Each case identifies a possible matching value.

    Case labels in traditional switch statements must
    be compatible with the switch selector type and
    satisfy Java's constant-expression rules.

    C. DEFAULT

    default executes when no case matches.

    It is optional.

    It can appear before or after case labels.

    D. BREAK

    break terminates the switch statement.

    Without break, execution can continue into
    subsequent case sections.

    E. FALL-THROUGH

    Fall-through occurs when execution continues from
    one case section into another without a break.

    Example:

        int number = 1;

        switch (number) {

            case 1:
                System.out.println("One");

            case 2:
                System.out.println("Two");

            case 3:
                System.out.println("Three");
                break;

            default:
                System.out.println("Other");
        }

    Output:

        One
        Two
        Three

    F. MULTIPLE CASE LABELS

    Multiple labels can share one execution block.

        int day = 6;

        switch (day) {

            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                System.out.println("Weekday");
                break;

            case 6:
            case 7:
                System.out.println("Weekend");
                break;

            default:
                System.out.println("Invalid day");
        }

    G. SWITCH DATA TYPES

    Traditional switch supports:

        byte
        short
        char
        int
        Byte
        Short
        Character
        Integer
        String
        enum types

    IMPORTANT:

    long, float, double and boolean cannot be used
    directly as traditional switch selector types.

    H. SWITCH WITH STRING

        String language = "Java";

        switch (language) {

            case "Java":
                System.out.println("Backend");
                break;

            case "Python":
                System.out.println("Data Science");
                break;

            default:
                System.out.println("Other language");
        }

    String switch support was introduced in Java 7.

    String matching in switch is case-sensitive.

    A null selector in a traditional switch causes
    NullPointerException.

    I. SWITCH WITH ENUM

        enum Status {
            PENDING,
            APPROVED,
            REJECTED
        }

        Status status = Status.APPROVED;

        switch (status) {

            case PENDING:
                System.out.println("Waiting");
                break;

            case APPROVED:
                System.out.println("Accepted");
                break;

            case REJECTED:
                System.out.println("Declined");
                break;
        }

    J. NESTED SWITCH

    A switch block can contain another switch statement.

        int category = 1;
        int option = 2;

        switch (category) {

            case 1:

                switch (option) {

                    case 1:
                        System.out.println("Option 1");
                        break;

                    case 2:
                        System.out.println("Option 2");
                        break;
                }

                break;

            default:
                System.out.println("Invalid category");
        }

    IMPORTANT:

    A break inside the inner switch terminates only
    the inner switch, not the outer switch.


    ============================================================
                    4. SWITCH EXPRESSIONS
    ============================================================

    Java introduced switch expressions as a preview
    feature in Java 12.

    They became a permanent feature in Java 14.

    A switch expression produces a value.

    A. ARROW LABELS

        int day = 2;

        String result = switch (day) {

            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";

            default -> "Other";
        };

        System.out.println(result);

    B. NO FALL-THROUGH WITH ARROW LABELS

    Arrow-style switch rules do not fall through
    into subsequent rules.

        int number = 1;

        switch (number) {

            case 1 -> System.out.println("One");
            case 2 -> System.out.println("Two");
            default -> System.out.println("Other");
        }

    C. MULTIPLE LABELS

        int day = 6;

        String type = switch (day) {

            case 1, 2, 3, 4, 5 -> "Weekday";

            case 6, 7 -> "Weekend";

            default -> "Invalid";
        };

    D. YIELD KEYWORD

    yield is used to return a value from a block
    inside a switch expression.

        int number = 2;

        String result = switch (number) {

            case 1 -> "One";

            case 2 -> {
                System.out.println("Processing");

                yield "Two";
            }

            default -> "Other";
        };

    E. SWITCH STATEMENT VS SWITCH EXPRESSION

    Switch statement:
        Performs actions.
        Does not have to produce a value.
        Traditional fall-through is possible.

    Switch expression:
        Produces a value.
        Must produce a value for every possible path.
        Uses yield when a block must return a value.
        Arrow rules do not fall through.

    IMPORTANT:

    switch expressions require exhaustive handling
    of possible values, commonly using default when
    needed.


    ============================================================
                    5. LOOPING STATEMENTS
    ============================================================

    Loops repeatedly execute a block of code while
    a condition or iteration rule allows it.

    Java provides:

        for
        while
        do-while
        enhanced for

    General loop components:

        Initialization
        Condition
        Body
        Update

    Example:

        for (int i = 0; i < 5; i++) {

            System.out.println(i);
        }

    Execution:

        1. Initialization executes once.
        2. Condition is checked.
        3. If true, the loop body executes.
        4. Update executes.
        5. Condition is checked again.
        6. The process continues until false.

    IMPORTANT:

    for and while are entry-controlled loops.

    do-while is an exit-controlled loop.


    ============================================================
                    6. FOR LOOP
    ============================================================

    A for loop is commonly used when the iteration
    count or iteration structure is known.

    Syntax:

        for (initialization; condition; update) {

            // Statements
        }

    A. BASIC FOR LOOP

        for (int i = 1; i <= 5; i++) {

            System.out.println(i);
        }

    Output:

        1
        2
        3
        4
        5

    B. EXECUTION ORDER

        for (int i = 0; i < 3; i++) {

            System.out.println(i);
        }

    Execution:

        Initialization: i = 0

        Check i < 3
        Execute body
        Update i++

        Check i < 3
        Execute body
        Update i++

        Check i < 3
        Execute body
        Update i++

        Check i < 3 -> false

    C. MULTIPLE INITIALIZATION AND UPDATE EXPRESSIONS

        for (
            int i = 0, j = 10;
            i < 5;
            i++, j--
        ) {

            System.out.println(i + " " + j);
        }

    Multiple declarations in the initialization section
    must use compatible declared types.

    D. OMITTING FOR-LOOP SECTIONS

    Initialization, condition and update sections
    can be omitted.

    Example:

        int i = 0;

        for (; i < 5; ) {

            System.out.println(i);

            i++;
        }

    E. INFINITE FOR LOOP

        for (;;) {

            System.out.println("Running");
        }

    An omitted condition is treated as true.

    F. VARIABLE SCOPE IN FOR LOOP

        for (int i = 0; i < 5; i++) {

            System.out.println(i);
        }

        // System.out.println(i);
        // Compilation error

    i is scoped to the for statement and its body.

    G. DECLARING THE COUNTER OUTSIDE THE LOOP

        int i;

        for (i = 0; i < 5; i++) {

            System.out.println(i);
        }

        System.out.println(i); // 5

    Here, i is accessible after the loop because
    it was declared outside the loop.

    H. COMMON USE CASES

    - Iterating through arrays.
    - Repeating a task a fixed number of times.
    - Generating patterns.
    - Counting and accumulating values.
    - Nested iteration.


    ============================================================
                    7. WHILE LOOP
    ============================================================

    A while loop repeatedly executes a block as long
    as its condition is true.

    Syntax:

        while (condition) {

            // Statements
        }

    A. BASIC EXAMPLE

        int i = 1;

        while (i <= 5) {

            System.out.println(i);

            i++;
        }

    B. EXECUTION ORDER

        1. Check the condition.
        2. If true, execute the body.
        3. Update the relevant variables.
        4. Check the condition again.
        5. Stop when the condition becomes false.

    C. ZERO ITERATIONS

    If the condition is false initially, the body
    never executes.

        int i = 10;

        while (i < 5) {

            System.out.println(i);
        }

    Output:

        No output.

    D. INFINITE WHILE LOOP

        while (true) {

            System.out.println("Running");
        }

    E. COMMON USE CASES

    - Repeating until a condition changes.
    - Reading input until a stopping condition.
    - Searching for a particular value.
    - Processing data while it remains available.

    IMPORTANT:

    Ensure that the loop condition can eventually
    become false, or provide an appropriate exit.


    ============================================================
                    8. DO-WHILE LOOP
    ============================================================

    A do-while loop executes its body first and checks
    the condition afterward.

    Syntax:

        do {

            // Statements

        } while (condition);

    IMPORTANT:

    A semicolon is required after the while condition.

    A. BASIC EXAMPLE

        int i = 1;

        do {

            System.out.println(i);

            i++;

        } while (i <= 5);

    B. AT LEAST ONE EXECUTION

    The body executes at least once, even when the
    condition is initially false.

        int i = 10;

        do {

            System.out.println(i);

        } while (i < 5);

    Output:

        10

    C. COMMON USE CASES

    - Menu-driven programs.
    - Asking users to retry input.
    - Performing an operation at least once.
    - Repeating an action after checking its result.


    ============================================================
                    9. FOR VS WHILE VS DO-WHILE
    ============================================================

    Feature          for          while        do-while
    --------------------------------------------------------
    Type             Entry        Entry        Exit
                     controlled   controlled   controlled
    Condition        Before       Before       After
                     body         body         body
    Minimum          0            0            1
    executions
    Initialization  In header    Usually      Usually
                                  outside      outside
    Update           In header    Usually      Usually
                                  in body       in body

    WHEN TO USE:

    for:
        When iteration structure is clear and often
        count-controlled.

    while:
        When repetition depends on a condition.

    do-while:
        When the body must execute at least once.


    ============================================================
                    10. ENHANCED FOR LOOP (FOR-EACH)
    ============================================================

    Enhanced for loop was introduced in Java 5.

    It is used to iterate over arrays and objects
    implementing Iterable.

    Syntax:

        for (dataType variable : collection) {

            // Statements
        }

    A. ARRAY EXAMPLE

        int[] numbers = {10, 20, 30, 40};

        for (int number : numbers) {

            System.out.println(number);
        }

    B. STRING ARRAY EXAMPLE

        String[] names = {"Vinay", "Rahul", "Kiran"};

        for (String name : names) {

            System.out.println(name);
        }

    C. COLLECTION EXAMPLE

        java.util.List<String> languages =
            java.util.List.of("Java", "Python", "C++");

        for (String language : languages) {

            System.out.println(language);
        }

    D. INTERNAL BEHAVIOUR

    For arrays, the enhanced for loop uses an index-based
    traversal conceptually.

    For Iterable collections, it uses an Iterator-based
    traversal conceptually.

    E. LIMITATIONS

    1. No direct access to the current index.

    2. Not suitable when elements must be accessed
       using arbitrary indexes.

    3. Cannot directly control iteration steps such
       as incrementing an index by 2.

    4. Assigning a new value to the loop variable does
       not replace the corresponding array or collection
       element.

    Example:

        int[] numbers = {10, 20, 30};

        for (int number : numbers) {

            number = 100;
        }

        System.out.println(numbers[0]); // 10

    The loop variable receives the current element value.

    F. MODIFYING COLLECTIONS DURING ITERATION

    Structural modification of a collection during
    enhanced for iteration can cause
    ConcurrentModificationException for many
    fail-fast collection iterators.

    Example:

        java.util.List<Integer> numbers =
            new java.util.ArrayList<>(
                java.util.List.of(1, 2, 3)
            );

        for (int number : numbers) {

            if (number == 2) {
                numbers.remove(Integer.valueOf(2));
            }
        }

    This can throw ConcurrentModificationException.

    Safer alternatives include:

        Iterator.remove()

        Removing elements using an appropriate
        collection method such as removeIf().

    IMPORTANT:

    Fail-fast behaviour is best-effort, not a universal
    guarantee for every collection implementation.

    G. FOR-EACH VS TRADITIONAL FOR

    Traditional for:
        Provides index control.
        Allows custom update logic.
        Useful for index-based processing.

    Enhanced for:
        Simplifies traversal.
        No explicit index management.
        Useful when only elements are needed.


    ============================================================
                    11. NESTED LOOPS
    ============================================================

    A loop inside another loop is called a nested loop.

    Example:

        for (int i = 1; i <= 3; i++) {

            for (int j = 1; j <= 3; j++) {

                System.out.print("* ");
            }

            System.out.println();
        }

    Output:

        * * *
        * * *
        * * *

    A. EXECUTION

    For every iteration of the outer loop,
    the inner loop completes all its iterations,
    unless control is transferred out of it.

    B. ITERATION COUNT

    If an outer loop executes n times and an inner
    loop executes m times for each outer iteration,
    the inner body executes n * m times.

    Example:

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 4; j++) {

                System.out.println(i + " " + j);
            }
        }

    Total inner body executions:

        3 * 4 = 12

    C. TRIANGULAR NESTED LOOP

        for (int i = 1; i <= 4; i++) {

            for (int j = 1; j <= i; j++) {

                System.out.print("* ");
            }

            System.out.println();
        }

    Output:

        *
        * *
        * * *
        * * * *

    D. TIME COMPLEXITY

    Two nested loops do not always mean O(n²).

    Complexity depends on how many times each loop runs.

    Examples:

        Outer n, inner n:
            O(n²)

        Outer n, inner constant:
            O(n)

        Outer n, inner log n:
            O(n log n)

        Independent sequential loops:
            O(n + n) = O(n)

    IMPORTANT:

    Analyze actual iteration counts rather than
    simply counting the number of loops.


    ============================================================
                    12. JUMP STATEMENTS
    ============================================================

    Jump statements alter the normal flow of execution.

    Java provides:

        break
        continue
        return

    A. BREAK

    break terminates the nearest enclosing loop
    or switch statement.

    Example: break in a loop

        for (int i = 1; i <= 10; i++) {

            if (i == 5) {
                break;
            }

            System.out.println(i);
        }

    Output:

        1
        2
        3
        4

    The loop terminates when i becomes 5.

    Example: break in switch

        int day = 1;

        switch (day) {

            case 1:
                System.out.println("Monday");
                break;

            case 2:
                System.out.println("Tuesday");
                break;
        }

    break terminates the switch statement.

    B. CONTINUE

    continue skips the remaining statements in the
    current loop iteration and proceeds to the next
    iteration.

    Example:

        for (int i = 1; i <= 5; i++) {

            if (i == 3) {
                continue;
            }

            System.out.println(i);
        }

    Output:

        1
        2
        4
        5

    The iteration for i = 3 is skipped.

    C. CONTINUE IN A FOR LOOP

    In a traditional for loop, continue transfers
    control to the update expression, then the condition
    is checked again.

    Example:

        for (int i = 1; i <= 5; i++) {

            if (i == 3) {
                continue;
            }

            System.out.println(i);
        }

    After continue at i = 3:

        i++ executes.
        Condition is checked again.

    D. CONTINUE IN A WHILE LOOP

    In a while loop, continue transfers control
    to the condition check.

    Example:

        int i = 0;

        while (i < 5) {

            i++;

            if (i == 3) {
                continue;
            }

            System.out.println(i);
        }

    IMPORTANT:

    If the update operation is placed after continue
    in a while loop, it may never execute.

    Incorrect example:

        int i = 0;

        while (i < 5) {

            if (i == 3) {
                continue;
            }

            i++;
        }

    Once i becomes 3, the loop becomes infinite because
    i is never incremented again.

    E. BREAK VS CONTINUE

    break:
        Terminates the nearest enclosing loop or switch.

    continue:
        Skips the current iteration of the nearest
        enclosing loop.

    IMPORTANT:

    continue is valid inside loops, not directly inside
    a switch statement.

    break can be used inside a switch statement.


    ============================================================
                    13. LABELED BREAK AND CONTINUE
    ============================================================

    Java supports labeled break and continue.

    A label identifies a statement, commonly a loop.

    Syntax:

        labelName:
            statement

    A. LABELED BREAK

    Exits the labeled statement.

    Example:

        outer:
        for (int i = 1; i <= 3; i++) {

            for (int j = 1; j <= 3; j++) {

                if (i == 2 && j == 2) {
                    break outer;
                }

                System.out.println(i + " " + j);
            }
        }

    When break outer executes, the entire outer loop
    is terminated.

    B. LABELED CONTINUE

    Skips to the next iteration of the labeled loop.

    Example:

        outer:
        for (int i = 1; i <= 3; i++) {

            for (int j = 1; j <= 3; j++) {

                if (j == 2) {
                    continue outer;
                }

                System.out.println(i + " " + j);
            }
        }

    When continue outer executes, the current inner
    and outer iteration are abandoned.

    Execution resumes with the next outer iteration.

    C. IMPORTANT RULES

    1. Labels are followed by a colon.

    2. A labeled break can exit the labeled statement.

    3. A labeled continue must target a labeled loop.

    4. Labels can be used with nested loops.

    5. Labels are useful when normal break or continue
       cannot express the required control flow.

    6. Excessive use of labels can make code harder
       to understand.


    ============================================================
                    14. RETURN STATEMENT
    ============================================================

    return terminates execution of the current method
    and optionally returns a value to its caller.

    A. RETURN FROM VOID METHOD

        void display() {

            System.out.println("Start");

            return;

            // Statements after return are unreachable.
        }

    B. RETURNING A VALUE

        int add(int a, int b) {

            return a + b;
        }

    C. RETURN INSIDE A LOOP

        int findNumber(int[] numbers) {

            for (int number : numbers) {

                if (number == 10) {
                    return number;
                }
            }

            return -1;
        }

    return exits the entire method, not just the loop.

    D. BREAK VS RETURN

    break:
        Exits the nearest enclosing loop or switch.

    return:
        Exits the current method.

    IMPORTANT:

    A non-void method must return a value on every
    possible normal execution path.


    ============================================================
                    15. IMPORTANT RULES AND EDGE CASES
    ============================================================

    A. DANGLING ELSE

    An else is associated with the nearest preceding
    unmatched if.

    Example:

        if (true)

            if (false)
                System.out.println("A");

            else
                System.out.println("B");

    The else belongs to the inner if.

    Use braces to avoid ambiguity.

    B. SEMICOLON AFTER IF

    Incorrect:

        if (true);
        {
            System.out.println("Hello");
        }

    The semicolon forms an empty if statement.

    The block executes independently.

    Correct:

        if (true) {
            System.out.println("Hello");
        }

    C. EMPTY LOOP BODY

        for (int i = 0; i < 5; i++);

    The loop body is empty.

    The semicolon terminates the loop statement.

    D. OFF-BY-ONE ERROR

    Example:

        for (int i = 0; i <= 5; i++) {
            System.out.println(i);
        }

    This executes six times, for values 0 through 5.

    Using i < 5 executes five times.

    E. INFINITE LOOPS

    A loop may continue forever when its condition
    never becomes false.

    Example:

        int i = 0;

        while (i < 5) {
            System.out.println(i);
        }

    i is never updated.

    F. INTEGER OVERFLOW

    Loop counters using integer types can overflow.

    Example:

        int i = Integer.MAX_VALUE;

        i++;

    The value wraps to Integer.MIN_VALUE.

    An overflowed loop condition can lead to unexpected
    behaviour or non-termination.

    G. FLOATING-POINT LOOP CONDITIONS

    Avoid relying on repeated floating-point increments
    to reach an exact decimal value.

    Example:

        for (double i = 0.0; i != 1.0; i += 0.1) {
            System.out.println(i);
        }

    Floating-point representation may prevent i from
    becoming exactly 1.0.

    Prefer integer counters when exact iteration counts
    are required.

    H. BOOLEAN CONDITIONS

    Java conditions must be boolean expressions.

    Invalid:

        // if (1) {
        // }

    Valid:

        if (1 == 1) {
            System.out.println("True");
        }

    I. ASSIGNMENT VS COMPARISON

    Assignment:

        int a = 10;

    Equality comparison:

        a == 10

    Java does not allow an integer assignment expression
    to be used as an if condition.

    J. UNREACHABLE STATEMENTS

    Java detects certain unreachable statements
    at compile time.

    Example:

        return;

        // System.out.println("Hello");

    The statement after return is unreachable.

    However, Java does not generally perform complete
    runtime-value analysis for every conditional.

    K. SWITCH FALL-THROUGH

    Traditional switch case sections can fall through
    when break or another control transfer is omitted.

    Arrow-style switch rules do not fall through.

    L. VARIABLE SCOPE

    Variables declared in a for-loop initializer
    are scoped to the for statement.

    Variables declared inside a block are scoped
    to that block.

    M. FINAL LOOP VARIABLES

    A loop counter declared final cannot be updated.

    Example:

        final int i = 0;

        // i++; // Compilation error

    N. SWITCH NULL HANDLING

    A null selector in a traditional switch causes
    NullPointerException.

    Modern switch constructs support null handling
    through appropriate case null labels in supported
    Java versions and switch forms.

    Do not assume every switch automatically accepts null.

    O. ENHANCED FOR MODIFICATION

    Reassigning the enhanced-for variable does not
    replace the original array or collection element.

    Structural collection modification during iteration
    may cause ConcurrentModificationException.


    ============================================================
                    16. INTERVIEW QUESTIONS
    ============================================================

    1. What is control flow?
       The order in which program statements execute.

    2. What are the three major control flow categories?
       Selection, iteration and branching.

    3. What is the difference between if and if-else?
       if executes a block when true; if-else provides
       an alternative block when false.

    4. What is an if-else-if ladder?
       A sequence of conditions where the first matching
       branch executes.

    5. Can Java use integers directly as conditions?
       No. Conditions must evaluate to boolean.

    6. What is short-circuit evaluation?
       Skipping evaluation of the right operand when
       the left operand already determines the result.

    7. What is the difference between && and &?
       && short-circuits; boolean & evaluates both operands.

    8. What is the difference between || and |?
       || short-circuits; boolean | evaluates both operands.

    9. What is a switch statement?
       A selection statement that chooses a branch
       based on a selector expression.

    10. What is fall-through?
        Execution continuing into subsequent traditional
        switch case sections.

    11. Which data types are supported by traditional switch?
        byte, short, char, int, their supported wrappers,
        String and enum types.

    12. Can long be used in a traditional switch?
        No.

    13. Can String be used in switch?
        Yes, since Java 7.

    14. What is a switch expression?
        A switch construct that produces a value.

    15. When did switch expressions become permanent?
        Java 14.

    16. What is yield?
        A statement used to produce a value from a block
        in a switch expression.

    17. What is an entry-controlled loop?
        A loop that checks its condition before execution.

    18. Which Java loop executes at least once?
        do-while.

    19. What is the difference between while and do-while?
        while checks before execution; do-while checks after.

    20. What is an enhanced for loop?
        A simplified loop for arrays and Iterable objects.

    21. Can for-each provide an index?
        Not directly.

    22. Can we modify array elements in a for-each loop?
        Yes, through the array reference and index or
        other means, but reassigning the loop variable
        alone does not modify the array element.

    23. What is a nested loop?
        A loop inside another loop.

    24. What is break?
        A statement that exits the nearest enclosing
        loop or switch.

    25. What is continue?
        A statement that skips the current loop iteration.

    26. What happens after continue in a for loop?
        The update expression executes, followed by
        the condition check.

    27. What happens after continue in a while loop?
        Control returns to the condition check.

    28. What is labeled break?
        A break that exits a specified labeled statement.

    29. What is labeled continue?
        A continue that starts the next iteration of
        a specified labeled loop.

    30. Can continue be used directly inside switch?
        No. It must target an enclosing loop.

    31. What is the difference between break and return?
        break exits a loop or switch; return exits a method.

    32. What is a dangling else?
        An else that associates with the nearest unmatched if.

    33. What is an off-by-one error?
        An error caused by an incorrect iteration boundary.

    34. What is an infinite loop?
        A loop that does not terminate.

    35. Does two nested loops always mean O(n²)?
        No. Complexity depends on the actual iteration counts.

    36. Can a switch have multiple default labels?
        No. A switch statement or expression can have
        at most one default label.

    37. Can break be used in an if statement?
        Only when the if is inside an enclosing loop
        or switch that the break can exit.

    38. Can return be used inside a loop?
        Yes. It terminates the enclosing method.

    39. What is the difference between independent if
        statements and an if-else-if ladder?
        Independent if statements check every condition;
        an if-else-if ladder executes only the first
        matching branch.

    40. What is the difference between a switch statement
        and a switch expression?
        A switch statement performs control-flow actions;
        a switch expression produces a value.


    ============================================================
                    17. REVISION CHECKLIST
    ============================================================

    CONDITIONALS:

    [ ] Understand control flow.
    [ ] Know if and if-else.
    [ ] Know if-else-if ladder.
    [ ] Understand nested conditions.
    [ ] Understand independent if statements.
    [ ] Know relational and logical operators.
    [ ] Understand short-circuit evaluation.
    [ ] Understand the ternary operator.

    SWITCH:

    [ ] Understand traditional switch.
    [ ] Know case, default and break.
    [ ] Understand fall-through.
    [ ] Know supported selector types.
    [ ] Understand String and enum switches.
    [ ] Know switch expressions.
    [ ] Understand arrow labels and yield.

    LOOPS:

    [ ] Understand for loop.
    [ ] Understand while loop.
    [ ] Understand do-while loop.
    [ ] Compare all three loop types.
    [ ] Understand enhanced for loop.
    [ ] Know its limitations.
    [ ] Understand nested loops.
    [ ] Calculate loop iteration counts.

    JUMP STATEMENTS:

    [ ] Understand break.
    [ ] Understand continue.
    [ ] Understand labeled break.
    [ ] Understand labeled continue.
    [ ] Understand return.
    [ ] Compare break, continue and return.

    EDGE CASES:

    [ ] Understand dangling else.
    [ ] Avoid accidental semicolons.
    [ ] Understand variable scope.
    [ ] Identify off-by-one errors.
    [ ] Identify infinite loops.
    [ ] Understand integer overflow.
    [ ] Avoid floating-point loop conditions.
    [ ] Understand switch fall-through.
    [ ] Understand collection modification during iteration.


    ============================================================
                    END OF CONDITIONALS
    ============================================================

    */

   public static void main(String[] args) {
    System.out.print("The above is all about Conditionals , Happy Learning ..!");
   }



}
