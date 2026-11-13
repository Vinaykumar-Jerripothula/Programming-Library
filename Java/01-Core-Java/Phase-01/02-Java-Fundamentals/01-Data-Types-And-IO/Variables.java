
class Variables {

/*
    ============================================================
                         JAVA VARIABLES
    ============================================================

    FILE: Variables.java

    CONTENTS:
    1. Introduction to Variables
    2. Variable Declaration and Initialization
    3. Assignment and Reassignment
    4. Types of Variables
    5. Local Variables
    6. Instance Variables
    7. Static Variables
    8. Scope and Lifetime
    9. Variable Default Values
    10. final Variables and Constants
    11. var - Local Variable Type Inference
    12. Variable Shadowing and Hiding
    13. Variable Naming Rules
    14. Memory and Variable Behaviour
    15. Important Rules and Interview Questions


    ============================================================
                    1. INTRODUCTION TO VARIABLES
    ============================================================

    A variable is a named storage location used to represent
    a value that a program can access and manipulate.

    Example:

        int age = 22;

        int  -> Data type
        age  -> Variable name
        22   -> Initial value

    Variables allow programs to store and work with data.

    A variable has:

    - Name.
    - Type.
    - Value (once initialized).
    - Scope.
    - Lifetime.
    - Storage characteristics determined by its context.


    ============================================================
                    2. VARIABLE DECLARATION AND INITIALIZATION
    ============================================================

    A. VARIABLE DECLARATION

    Declaration introduces a variable and specifies its type
    and name.

    Syntax:

        dataType variableName;

    Example:

        int age;
        double salary;
        String name;


    B. VARIABLE INITIALIZATION

    Initialization means assigning the variable its initial
    value.

    Example:

        int age = 22;

    Declaration and initialization together:

        int number = 100;

    Separate declaration and initialization:

        int number;

        number = 100;


    C. VARIABLE ASSIGNMENT

    Assignment means storing a value in a variable.

    Example:

        int number = 10;

        number = 20;

    The value of number is now 20.

    IMPORTANT:

    A variable's declared type does not change during
    reassignment.

        int value = 10;

        value = 20;       // Valid

        // value = "Java"; // Compilation error


    D. MULTIPLE VARIABLE DECLARATIONS

    Same type:

        int a, b, c;

    Declaration with initialization:

        int x = 10, y = 20, z = 30;

    Different types require separate declarations:

        int age = 22;
        double salary = 45000.5;

    IMPORTANT:

    In a declaration such as:

        int a, b[];

    a is an int, whereas b is an int array.

    Prefer declaring one variable per statement for clarity.


    ============================================================
                    3. TYPES OF VARIABLES
    ============================================================

    Java variables are commonly classified into three
    categories:

                         Variables
                             |
              +--------------+--------------+
              |              |              |
            Local         Instance        Static
          Variables       Variables      Variables

    1. Local Variables
       Declared inside methods, constructors or blocks.

    2. Instance Variables
       Non-static fields belonging to individual objects.

    3. Static Variables
       Fields declared using static and associated with
       the class.


    ============================================================
                    4. LOCAL VARIABLES
    ============================================================

    A local variable is declared inside a method,
    constructor, initializer or block.

    Example:

        class Example {

            void display() {

                int number = 100;

                System.out.println(number);
            }
        }

    Characteristics:

    - Declared inside a method, constructor or block.
    - Accessible only within its applicable scope.
    - Must be definitely assigned before reading.
    - Does not receive an automatic default value.
    - Exists during the execution of its enclosing context.
    - Cannot be declared with the static modifier.

    Example:

        void calculate() {

            int a = 10;
            int b = 20;

            int result = a + b;

            System.out.println(result);
        }

    A. BLOCK SCOPE

    A local variable declared inside a block is accessible
    only within that block and its nested scopes.

    Example:

        void display() {

            if (true) {

                int number = 10;

                System.out.println(number);
            }

            // System.out.println(number);
            // Compilation error
        }

    B. DEFINITE ASSIGNMENT

    Java requires local variables to be definitely assigned
    before they are read.

    Invalid:

        void display() {

            int number;

            System.out.println(number);
        }

    Compilation error:

        Variable number might not have been initialized.

    Valid:

        void display() {

            int number;

            number = 100;

            System.out.println(number);
        }

    C. LOCAL VARIABLES IN LOOPS

        for (int i = 0; i < 5; i++) {

            System.out.println(i);
        }

    The variable i is scoped to the for-loop statement
    and is not accessible after the loop.

    D. LOCAL VARIABLES IN CONSTRUCTORS

        class Student {

            Student() {

                int age = 22;
            }
        }

    age is a local variable of the constructor.


    ============================================================
                    5. INSTANCE VARIABLES
    ============================================================

    An instance variable is a non-static field declared
    inside a class but outside methods, constructors
    and initializer blocks.

    Example:

        class Student {

            int age;
            String name;
        }

    Characteristics:

    - Belongs to an individual object.
    - Each object has its own instance fields.
    - Receives a default value if not explicitly initialized.
    - Can be accessed through an object reference,
      subject to access control.
    - Exists as part of the object's state.

    Example:

        class Student {

            int age;

            void display() {
                System.out.println(age);
            }
        }

        class Main {

            public static void main(String[] args) {

                Student s1 = new Student();
                Student s2 = new Student();

                s1.age = 20;
                s2.age = 22;

                System.out.println(s1.age); // 20
                System.out.println(s2.age); // 22
            }
        }

    Each object has its own age field.

    A. INSTANCE VARIABLE INITIALIZATION

    Instance fields can be initialized:

    1. At declaration.
    2. Inside instance initializer blocks.
    3. Inside constructors.

    Example:

        class Student {

            int age = 20;

            {
                age = 21;
            }

            Student() {
                age = 22;
            }
        }

    The final value after construction is 22.

    Instance field initialization and constructor execution
    follow a defined initialization order.


    ============================================================
                    6. STATIC VARIABLES
    ============================================================

    A static variable is a class-level field declared
    using the static keyword.

    Example:

        class Student {

            static String college = "Miracle";

            int age;
        }

    Characteristics:

    - Associated with the class.
    - Shared by instances of that class.
    - Has one class variable per class definition
      in a particular class loader.
    - Receives a default value if not explicitly initialized.
    - Can be accessed using the class name.
    - Exists as part of the class's runtime state.

    Example:

        class Student {

            static String college = "Miracle";

            String name;
        }

        class Main {

            public static void main(String[] args) {

                Student s1 = new Student();
                Student s2 = new Student();

                s1.name = "A";
                s2.name = "B";

                System.out.println(Student.college);
            }
        }

    Both objects share the same static field.

    A. ACCESSING STATIC VARIABLES

    Recommended:

        Student.college;

    Also permitted, but discouraged:

        s1.college;

    Static members should be accessed using the class name.

    B. STATIC VARIABLE INITIALIZATION

    Static fields can be initialized:

    1. At declaration.
    2. Inside static initializer blocks.

    Example:

        class Example {

            static int count = 10;

            static {
                count = 20;
            }
        }

    C. STATIC VARIABLE SHARING

        class Counter {

            static int count = 0;

            Counter() {
                count++;
            }
        }

        class Main {

            public static void main(String[] args) {

                new Counter();
                new Counter();
                new Counter();

                System.out.println(Counter.count); // 3
            }
        }

    IMPORTANT:

    A static field is not separately created for every object.

    Its storage and lifecycle are associated with the
    class and its defining class loader.


    ============================================================
                    7. LOCAL VS INSTANCE VS STATIC
    ============================================================

    Feature          Local       Instance       Static
    ------------------------------------------------------------
    Declaration      Method/     Class body      Class body
                     block
    static allowed   No          Yes             Yes
    Belongs to       Block or    Object          Class
                     method
    Default value    No          Yes             Yes
    Shared           No          No              Yes
    Scope            Local       Access rules    Access rules
    Lifetime         Execution   Object          Class runtime
                                 lifetime        lifecycle

    IMPORTANT:

    - Local variables do not receive default values.
    - Instance variables belong to objects.
    - Static variables are associated with classes.
    - Access modifiers cannot be applied to local variables.
    - Instance and static fields can have access modifiers.


    ============================================================
                    8. VARIABLE SCOPE AND LIFETIME
    ============================================================

    SCOPE:

    Scope determines where a variable can be accessed
    in source code.

    LIFETIME:

    Lifetime refers to how long a variable's value or
    associated storage remains relevant during execution.

    These are related but different concepts.

    A. LOCAL VARIABLE SCOPE

    A local variable is accessible within its declared
    block and applicable nested scopes.

    B. INSTANCE FIELD LIFETIME

    An instance field is part of an object's state.

    It remains associated with the object while that
    object exists.

    C. STATIC FIELD LIFETIME

    A static field belongs to a class definition.

    Its lifecycle is associated with the defining
    class loader and class initialization.

    D. METHOD PARAMETERS

    Parameters are local variables of the method or
    constructor.

    Example:

        void display(int age) {

            System.out.println(age);
        }

    age is a parameter variable.


    ============================================================
                    9. DEFAULT VALUES
    ============================================================

    Instance and static fields receive default values.

    Local variables do not receive automatic default values.

    Type             Default value
    -------------------------------------
    byte             0
    short            0
    int              0
    long             0L
    float            0.0f
    double           0.0d
    char             '\u0000'
    boolean          false
    Reference        null

    Example:

        class Example {

            int number;
            boolean active;
            String name;

            static double price;
        }

    Default values:

        number -> 0
        active -> false
        name   -> null
        price  -> 0.0

    IMPORTANT:

    An uninitialized local variable causes a compile-time
    definite-assignment error when read.

    It is not automatically initialized to zero or null.


    ============================================================
                    10. FINAL VARIABLES AND CONSTANTS
    ============================================================

    final is a modifier used to restrict reassignment
    of a variable.

    Example:

        final int MAX = 100;

        // MAX = 200; // Compilation error

    A final variable must be assigned exactly once
    before it is read.

    A. FINAL LOCAL VARIABLE

        void display() {

            final int number = 10;

            // number = 20; // Compilation error
        }

    B. FINAL INSTANCE VARIABLE

        class Student {

            final int id;

            Student(int id) {
                this.id = id;
            }
        }

    A blank final instance field can be assigned in
    an appropriate constructor or initializer.

    C. FINAL STATIC VARIABLE

        class Configuration {

            static final int MAX_USERS = 100;
        }

    A blank static final field can be assigned in
    a static initializer block.

    D. CONSTANTS

    A constant is commonly represented by a static final
    field whose value is fixed.

        static final double PI = 3.141592653589793;

    Naming convention:

        UPPER_SNAKE_CASE

    E. COMPILE-TIME CONSTANTS

    A constant variable is a final variable of primitive
    type or String initialized with a constant expression.

    Example:

        static final int MAX = 100;

    This can be a compile-time constant.

    Not every final variable is a compile-time constant.

    Example:

        final int value = getValue();

    Its value is assigned once, but the method call
    is not a constant expression.

    F. FINAL REFERENCE VARIABLES

    final on a reference variable prevents reassignment
    of the reference.

    It does not automatically make the referenced object
    immutable.

    Example:

        final int[] numbers = {10, 20};

        numbers[0] = 100; // Valid

        // numbers = new int[]{30, 40};
        // Compilation error

    The array can be modified, but the reference
    cannot be reassigned.

    IMPORTANT:

    final does not mean the object itself is immutable.


    ============================================================
                    11. VAR - LOCAL VARIABLE TYPE INFERENCE
    ============================================================

    var was introduced in Java 10.

    It allows the compiler to infer the type of a local
    variable from its initializer.

    Example:

        var age = 22;

    The inferred type is int.

    Another example:

        var name = "Vinay";

    The inferred type is String.

    Equivalent declarations:

        int age = 22;

        String name = "Vinay";

    IMPORTANT RULES:

    - var can be used for local variables with initializers.
    - var cannot be used for instance fields.
    - var cannot be used for static fields.
    - var cannot be used for method parameters.
    - var cannot be used as a method return type.
    - var cannot be used without an initializer.
    - var cannot be initialized with null alone.
    - var is not a dynamically typed variable.
    - The inferred type is fixed at compile time.

    Invalid:

        var age;

        var name = null;

        class Example {
            // var number = 10;
        }

    Valid:

        var number = 100;

        var message = "Hello";

    IMPORTANT:

    var is not a keyword in the same sense as int or class.
    It is a restricted type name used for local-variable
    type inference.


    ============================================================
                    12. VARIABLE SHADOWING AND HIDING
    ============================================================

    These concepts occur when variables with the same
    name exist in different scopes or declarations.

    A. LOCAL VARIABLE SHADOWING A FIELD

        class Example {

            int number = 10;

            void display() {

                int number = 20;

                System.out.println(number);      // 20
                System.out.println(this.number); // 10
            }
        }

    The local variable shadows the instance field.

    this.number accesses the instance field.

    B. STATIC FIELD ACCESS

        class Example {

            static int count = 10;

            void display() {

                int count = 20;

                System.out.println(count);        // 20
                System.out.println(Example.count); // 10
            }
        }

    C. FIELD HIDING IN INHERITANCE

        class Parent {

            int value = 10;
        }

        class Child extends Parent {

            int value = 20;
        }

    A field declared in the child hides the parent's field.

    Accessing fields depends on the compile-time reference
    type and the field selected.

    Unlike method overriding, field access is not
    dynamically dispatched.

    Example:

        Parent obj = new Child();

        System.out.println(obj.value); // 10

    IMPORTANT:

    Field hiding is different from method overriding.


    ============================================================
                    13. VARIABLE NAMING RULES
    ============================================================

    Java identifiers are names used for variables,
    methods, classes and other declarations.

    Rules:

    1. Can contain letters, digits, underscore and dollar sign.
    2. Cannot begin with a digit.
    3. Cannot be a Java keyword.
    4. Cannot contain spaces.
    5. Identifiers are case-sensitive.
    6. Unicode characters are permitted subject to
       Java identifier rules.

    Valid:

        age
        studentName
        _count
        $value
        number1

    Invalid:

        1number
        student name
        class
        total-value

    Naming conventions:

    Local and instance variables:

        camelCase

        studentName
        totalAmount
        employeeId

    Static final constants:

        UPPER_SNAKE_CASE

        MAX_SIZE
        DEFAULT_VALUE

    IMPORTANT:

    Naming conventions improve readability.
    They are not all compiler-enforced rules.


    ============================================================
                    14. MEMORY AND VARIABLE BEHAVIOUR
    ============================================================

    Java variables have different runtime storage
    characteristics depending on their kind.

    A. LOCAL VARIABLES

    Local variables are associated with method execution
    and block execution.

    JVM implementations commonly represent method-local
    variables in stack frames.

    However, the Java language specification does not
    require every local variable to occupy a physical
    stack location.

    B. INSTANCE VARIABLES

    Instance fields are part of object state.

    Objects are commonly allocated on the heap by
    JVM implementations.

    C. STATIC VARIABLES

    Static fields are associated with class state.

    Their actual storage implementation is JVM-dependent.

    IMPORTANT:

    Do not assume that every Java variable has a fixed
    physical memory location.

    The Java language specification defines behaviour,
    while JVM implementations decide many physical
    storage details.


    ============================================================
                    15. IMPORTANT VARIABLE RULES
    ============================================================

    1. Every variable has a declared or inferred type.

    2. Local variables must be definitely assigned
       before use.

    3. Instance fields receive default values.

    4. Static fields receive default values.

    5. Local variables cannot be declared static.

    6. Instance variables belong to objects.

    7. Static variables belong to class state.

    8. Static variables are shared by instances of
       the same class definition and class loader.

    9. final variables cannot be reassigned after
       definite assignment.

    10. A final reference does not make its object immutable.

    11. var performs compile-time type inference.

    12. var is allowed only in permitted local-variable
        declaration contexts.

    13. Method parameters are local variables.

    14. Local variables can shadow fields.

    15. Fields can be hidden in inheritance.

    16. Field access is not dynamically dispatched.

    17. this refers to the current object in an instance
        context.

    18. ClassName.field is the recommended way to access
        static fields.

    19. Variable scope and variable lifetime are different.

    20. Java is pass-by-value, including when references
        are passed to methods.


    ============================================================
                    16. INTERVIEW QUESTIONS
    ============================================================

    1. What is a variable?
       A named entity used to represent a value.

    2. What are the three main categories of variables?
       Local, instance and static.

    3. What is an instance variable?
       A non-static field belonging to an object.

    4. What is a static variable?
       A class-level field declared using static.

    5. Do local variables receive default values?
       No.

    6. Do instance variables receive default values?
       Yes.

    7. Do static variables receive default values?
       Yes.

    8. Can a local variable be static?
       No.

    9. What is a final variable?
       A variable that cannot be reassigned after
       it has been definitely assigned.

    10. Does final make an object immutable?
        No.

    11. What is a compile-time constant?
        A constant variable initialized with a
        constant expression.

    12. What is var?
        Local-variable type inference introduced in Java 10.

    13. Is var dynamically typed?
        No. Its type is inferred at compile time.

    14. What is variable shadowing?
        A declaration hides another variable with
        the same name in an enclosing scope.

    15. What is field hiding?
        A subclass declares a field with the same name
        as a field in its superclass.

    16. What is the difference between scope and lifetime?
        Scope concerns accessibility; lifetime concerns
        how long the variable's state exists.

    17. How do you access a static variable?
        Using the class name.

    18. Can a static method directly access an instance field?
        No. It needs an appropriate object reference.

    19. Are static variables shared across all objects?
        They are shared for the same class definition
        loaded by the same class loader.

    20. Is Java pass-by-reference?
        No. Java is pass-by-value.


    ============================================================
                    17. VARIABLES - FINAL CHECKLIST
    ============================================================

    [ ] Understand variable declaration and initialization.
    [ ] Understand assignment and reassignment.
    [ ] Know local variables.
    [ ] Know instance variables.
    [ ] Know static variables.
    [ ] Understand the differences between all three.
    [ ] Understand block scope.
    [ ] Understand variable lifetime.
    [ ] Understand definite assignment.
    [ ] Know default values.
    [ ] Understand final variables.
    [ ] Understand compile-time constants.
    [ ] Understand final reference variables.
    [ ] Understand var and type inference.
    [ ] Understand shadowing and field hiding.
    [ ] Know variable naming rules.
    [ ] Understand variable storage at a conceptual level.
    [ ] Know common interview questions.


    ============================================================
                    END OF VARIABLES
    ============================================================

*/
    public static void main(String[] args) {
        System.out.println("The above is all about Variables , Happy Learning ..!");
    }

}