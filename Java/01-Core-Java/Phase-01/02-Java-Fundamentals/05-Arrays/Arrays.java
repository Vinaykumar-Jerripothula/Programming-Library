
public class Arrays {

    /*
    ============================================================
                         JAVA ARRAYS
    ============================================================

    CONTENTS:
    1. Introduction to Arrays
    2. Declaration and Creation
    3. Array Initialization
    4. Accessing and Modifying Elements
    5. Types of Arrays
    6. Memory and Internal Representation
    7. Copying Arrays
    8. Comparing Arrays
    9. java.util.Arrays Utility Class
    10. Arrays and Methods
    11. Advanced Array Concepts
    12. Common Mistakes and Interview Questions


    ============================================================
                    1. INTRODUCTION TO ARRAYS
    ============================================================

    An array is an object that stores a fixed number of
    elements of the same component type.

    Example:

        int[] numbers = {10, 20, 30, 40, 50};

    FEATURES:

    - Stores multiple values in a single variable.
    - Stores elements of a compatible component type.
    - Has a fixed length after creation.
    - Elements are accessed using zero-based indexes.
    - Supports primitive values and object references.
    - Arrays are objects in Java.
    - Arrays are dynamically allocated.
    - Array length is available through the length property.

    IMPORTANT:

    Arrays can store either:
        1. Primitive values.
        2. References to objects.

    Java arrays are not resizable after creation.
    Use a collection such as ArrayList when dynamic
    resizing is required.


    ============================================================
                    2. DECLARATION AND CREATION
    ============================================================

    An array variable stores a reference to an array object.

    A. DECLARATION

        int[] numbers;

    Alternative syntax:

        int numbers[];

    Preferred convention:

        int[] numbers;

    Both are valid Java syntax.

    B. CREATION

        numbers = new int[5];

    Creates an array capable of storing five integers.

    C. DECLARATION AND CREATION TOGETHER

        int[] numbers = new int[5];

    D. DECLARATION, CREATION AND INITIALIZATION

        int[] numbers = {10, 20, 30, 40, 50};

    E. ARRAY LENGTH

        int[] numbers = new int[5];

        System.out.println(numbers.length); // 5

    length is a property, not a method.

        numbers.length     // Correct
        numbers.length()   // Incorrect

    F. ARRAY LENGTH MUST BE NON-NEGATIVE

        int[] a = new int[0];  // Valid
        // int[] b = new int[-1]; // NegativeArraySizeException

    An array can have zero length.

    G. MULTIPLE ARRAY VARIABLES

        int[] a, b;

    Both a and b are arrays.

        int a[], b;

    Only a is an array; b is an int.

    Avoid mixing declaration styles.


    ============================================================
                    3. ARRAY INITIALIZATION
    ============================================================

    A. ARRAY INITIALIZER

        int[] numbers = {10, 20, 30};

    The compiler determines the length from the initializer.

    B. INITIALIZATION AFTER DECLARATION

        int[] numbers;

        numbers = new int[]{10, 20, 30};

    The shorter initializer form is valid only where
    an array variable is declared and initialized.

    C. ANONYMOUS ARRAY CREATION

        printArray(new int[]{10, 20, 30});

    Creates an array without storing it in a named variable.

    D. DEFAULT VALUES

    When an array is created, its elements receive defaults.

    Component Type          Default Value
    -----------------------------------------
    byte                    0
    short                   0
    int                     0
    long                    0L
    float                   0.0f
    double                  0.0
    char                    '\u0000'
    boolean                 false
    Reference types         null

    Example:

        int[] numbers = new int[3];

        // {0, 0, 0}

        String[] names = new String[3];

        // {null, null, null}

    IMPORTANT:

    Array elements are automatically initialized.
    Local variables themselves must still be definitely
    assigned before use.


    ============================================================
                    4. ACCESSING AND MODIFYING ELEMENTS
    ============================================================

    Array indexes start from zero.

        int[] numbers = {10, 20, 30, 40};

    Accessing:

        numbers[0] // 10
        numbers[1] // 20
        numbers[2] // 30
        numbers[3] // 40

    Last valid index:

        numbers.length - 1

    Modifying:

        numbers[0] = 100;

    Traversing using for loop:

        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);
        }

    Traversing using enhanced for loop:

        for (int number : numbers) {
            System.out.println(number);
        }

    IMPORTANT:

    Enhanced for loop provides values, not direct indexes.

    Reassigning its loop variable does not update the
    corresponding array element.

    For object arrays, modifying the referenced object
    itself can still affect that object.

    ARRAY INDEX OUT OF BOUNDS:

        int[] a = {10, 20, 30};

        // a[3] -> ArrayIndexOutOfBoundsException
        // a[-1] -> ArrayIndexOutOfBoundsException

    Valid indexes:

        0 to length - 1


    ============================================================
                    5. TYPES OF ARRAYS
    ============================================================

    A. ONE-DIMENSIONAL ARRAY

    Stores elements in a single sequence.

        int[] numbers = {10, 20, 30, 40};

    Access:

        numbers[0];

    B. TWO-DIMENSIONAL ARRAY

    An array whose components are references to arrays.

        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

    Access:

        matrix[0][0] // 1
        matrix[1][2] // 6
        matrix[2][1] // 8

    Traversal:

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

    C. MULTIDIMENSIONAL ARRAY

    Java supports arrays with multiple dimensions.

        int[][][] data = new int[2][3][4];

    Access:

        data[0][1][2];

    D. JAGGED ARRAY

    A multidimensional array whose inner arrays
    can have different lengths.

        int[][] numbers = {
            {1, 2},
            {3, 4, 5},
            {6}
        };

    Creating manually:

        int[][] a = new int[3][];

        a[0] = new int[2];
        a[1] = new int[3];
        a[2] = new int[1];

    Inner arrays can also be null.

    IMPORTANT:

    Java multidimensional arrays are arrays of arrays.
    They are not necessarily rectangular.


    ============================================================
                    6. MEMORY AND INTERNAL REPRESENTATION
    ============================================================

    Arrays are objects created dynamically.

        int[] numbers = new int[3];

    Conceptually:

        numbers -----> Array object
                      [0] [0] [0]

    A. REFERENCE VARIABLE

    The variable numbers stores a reference to the array,
    not the array elements directly.

    B. PRIMITIVE ARRAY

        int[] a = {10, 20, 30};

    Stores primitive values as array elements.

    C. REFERENCE ARRAY

        String[] names = {"Java", "Python"};

    Stores references to String objects.

    D. REFERENCE COPYING / ALIASING

        int[] a = {10, 20, 30};

        int[] b = a;

    Both variables refer to the same array object.

        b[0] = 100;

    a[0] is also 100.

    E. NULL ARRAY REFERENCE

        int[] numbers = null;

    Accessing:

        // numbers.length
        // NullPointerException

    F. GARBAGE COLLECTION

    An array object becomes eligible for garbage collection
    when it is no longer reachable.

    IMPORTANT:

    Java does not guarantee a particular physical memory
    layout for arrays. The heap/reference model is the
    useful programming-level model.


    ============================================================
                    7. COPYING ARRAYS
    ============================================================

    A. REFERENCE ASSIGNMENT

        int[] a = {10, 20, 30};

        int[] b = a;

    No new array is created.

    Both references point to the same object.

    B. MANUAL COPYING

        int[] a = {10, 20, 30};
        int[] b = new int[a.length];

        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }

    Creates a separate array.

    C. System.arraycopy()

        System.arraycopy(
            source, sourcePosition,
            destination, destinationPosition,
            length
        );

    Example:

        int[] a = {10, 20, 30, 40};
        int[] b = new int[4];

        System.arraycopy(a, 0, b, 0, 4);

    Copies elements efficiently.

    D. Arrays.copyOf()

        int[] a = {10, 20, 30};

        int[] b = java.util.Arrays.copyOf(a, 5);

    Result:

        {10, 20, 30, 0, 0}

    Can create a copy with a different length.

    E. Arrays.copyOfRange()

        int[] a = {10, 20, 30, 40, 50};

        int[] b = java.util.Arrays.copyOfRange(a, 1, 4);

    Result:

        {20, 30, 40}

    The starting index is inclusive.
    The ending index is exclusive.

    F. SHALLOW COPY VS DEEP COPY

    Primitive array copying:

        int[] a = {1, 2, 3};
        int[] b = a.clone();

    Creates an independent array of primitive values.

    Object array copying:

        StringBuilder[] a = {
            new StringBuilder("Java")
        };

        StringBuilder[] b = a.clone();

    The arrays are different, but their elements refer
    to the same StringBuilder object.

    Shallow copy:
        Copies references, not the referenced objects.

    Deep copy:
        Copies the elements and the objects they reference,
        as required by the application.

    Java does not automatically perform deep copying.


    ============================================================
                    8. COMPARING ARRAYS
    ============================================================

    A. == OPERATOR

    Checks whether two references refer to the same array.

        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3};

        System.out.println(a == b); // false

    B. Arrays.equals()

    Compares corresponding elements of one-dimensional
    arrays.

        java.util.Arrays.equals(a, b); // true

    C. Arrays.deepEquals()

    Compares nested arrays recursively.

        int[][] a = {{1, 2}, {3, 4}};
        int[][] b = {{1, 2}, {3, 4}};

        java.util.Arrays.deepEquals(a, b); // true

    D. Arrays.compare()

    Compares arrays lexicographically.

    Returns:
        Negative value -> first array is smaller.
        Zero            -> arrays are equal.
        Positive value  -> first array is greater.

    E. Arrays.mismatch()

    Returns the index of the first differing element.
    Returns -1 if the arrays match.

    IMPORTANT:

    Arrays.equals() compares elements using their
    corresponding equality behaviour.

    For object elements, this normally uses equals().


    ============================================================
                    9. java.util.Arrays UTILITY CLASS
    ============================================================

    java.util.Arrays provides static utility methods
    for working with arrays.

    Import:

        import java.util.Arrays;

    A. toString()

    Converts a one-dimensional array into a readable String.

        int[] a = {10, 20, 30};

        System.out.println(Arrays.toString(a));

        // [10, 20, 30]

    B. deepToString()

    Prints nested arrays in readable form.

        int[][] a = {{1, 2}, {3, 4}};

        System.out.println(Arrays.deepToString(a));

    C. sort()

    Sorts an array.

        int[] a = {40, 10, 30, 20};

        Arrays.sort(a);

        // [10, 20, 30, 40]

    Primitive arrays are sorted in ascending order.

    Object arrays can also be sorted using natural ordering
    or a supplied Comparator.

    D. parallelSort()

    Sorts arrays using parallel sorting where appropriate.

        Arrays.parallelSort(a);

    E. binarySearch()

    Searches for a value in a sorted array.

        int[] a = {10, 20, 30, 40};

        int index = Arrays.binarySearch(a, 30);

        // 2

    The array should be sorted using the matching ordering.

    If the key is not found, the result is negative.

    F. fill()

    Fills an array with a specified value.

        int[] a = new int[5];

        Arrays.fill(a, 10);

        // [10, 10, 10, 10, 10]

    G. setAll()

    Sets elements using a generator function.

        int[] a = new int[5];

        Arrays.setAll(a, i -> i * 10);

        // [0, 10, 20, 30, 40]

    H. asList()

    Creates a fixed-size List backed by the supplied
    object array.

        String[] names = {"Java", "Python"};

        java.util.List<String> list =
            Arrays.asList(names);

    Changes to list elements are reflected in the array.

    The returned list supports set(), but not structural
    additions or removals.

    IMPORTANT:

    For primitive arrays, asList() treats the entire
    primitive array as one element.

        int[] a = {1, 2, 3};

        Arrays.asList(a);

    This does not produce a List<Integer> containing
    three elements.

    I. stream()

    Creates a stream from an array.

        int[] a = {10, 20, 30};

        Arrays.stream(a)
              .forEach(System.out::println);

    J. hashCode() AND deepHashCode()

    Provide hash codes for arrays.

        Arrays.hashCode(a);
        Arrays.deepHashCode(a);

    Use deepHashCode() for nested arrays.


    ============================================================
                    10. ARRAYS AND METHODS
    ============================================================

    A. PASSING ARRAY AS ARGUMENT

        static void display(int[] numbers) {
            for (int n : numbers) {
                System.out.println(n);
            }
        }

    Calling:

        display(new int[]{10, 20, 30});

    B. MODIFYING ARRAY INSIDE A METHOD

        static void update(int[] a) {
            a[0] = 100;
        }

    The caller sees the changed element because the
    reference value was passed to the method.

    Java is always pass-by-value.
    For arrays, the copied value is the reference.

    C. RETURNING AN ARRAY

        static int[] createArray() {
            return new int[]{10, 20, 30};
        }

    Calling:

        int[] result = createArray();

    D. VARARGS

    Variable-length arguments allow a method to receive
    zero or more arguments of a specified type.

        static void display(int... numbers) {
            for (int n : numbers) {
                System.out.println(n);
            }
        }

    Calling:

        display(10, 20, 30);
        display();

    Inside the method, varargs is treated as an array.

    IMPORTANT:

    A method can have only one varargs parameter.
    It must be the last parameter.

        void test(String name, int... values) {
            // Valid
        }


    ============================================================
                    11. ADVANCED ARRAY CONCEPTS
    ============================================================

    A. ARRAYS OF OBJECTS

        class Student {
            String name;
        }

        Student[] students = new Student[3];

    Initially, all three elements are null.

        students[0] = new Student();

    Creating the array does not automatically create
    Student objects.

    B. ARRAY COVARIANCE

    Java arrays are covariant.

        String[] names = {"Java", "Python"};

        Object[] objects = names;

    This assignment is valid because String is a subtype
    of Object.

    However:

        // objects[0] = new Object();

    Throws ArrayStoreException at runtime.

    The actual array still has String as its component type.

    C. ARRAYSTOREEXCEPTION

    Occurs when an incompatible object is stored in
    an array at runtime.

    D. RUNTIME COMPONENT TYPE

        String[] names = new String[5];

    The actual array object retains its runtime component
    type, String.

    E. clone()

        int[] a = {10, 20, 30};

        int[] b = a.clone();

    Creates a new array with the same elements.

    Array clone is shallow for reference arrays.

    F. REFLECTION

    java.lang.reflect.Array provides dynamic array
    operations when the component type is known at runtime.

        Object a = java.lang.reflect.Array.newInstance(
            int.class, 5
        );

    G. GENERIC ARRAY RESTRICTION

    Direct creation of arrays of parameterized types
    is not allowed.

        // List<String>[] lists =
        //     new List<String>[5];

    Generic type information is erased at runtime.
    This restriction prevents unsafe array creation.

    H. ARRAYS AND COLLECTIONS

    Arrays:
        - Fixed length.
        - Can store primitives directly.
        - Built into the Java language.

    Collections:
        - Provide dynamic data structures.
        - Store objects, not primitive values directly.
        - Provide richer APIs.

    Example:

        int[] a = {10, 20, 30};

        java.util.ArrayList<Integer> list =
            new java.util.ArrayList<>();

        list.add(10);
        list.add(20);


    ============================================================
                    12. COMMON MISTAKES
    ============================================================

    1. Accessing an invalid index.

    2. Accessing an array through a null reference.

    3. Confusing length with length().

    4. Assuming arrays resize automatically.

    5. Confusing reference assignment with copying.

    6. Using == for comparing array contents.

    7. Forgetting that multidimensional arrays are arrays
       of arrays.

    8. Assuming every row of a 2D array has the same length.

    9. Forgetting that object-array elements default to null.

    10. Assuming creating an object array creates its objects.

    11. Using binarySearch() on an unsorted array.

    12. Assuming Arrays.asList() supports add() and remove().

    13. Assuming Arrays.asList() converts int[] into
        List<Integer>.

    14. Confusing shallow copying with deep copying.

    15. Forgetting ArrayStoreException when using
        covariant reference arrays.


    ============================================================
                    13. TIME COMPLEXITY
    ============================================================

    Operation                         Typical Complexity
    ----------------------------------------------------
    Access by index                   O(1)
    Update by index                   O(1)
    Linear search                     O(n)
    Binary search (sorted array)      O(log n)
    Traversal                         O(n)
    Copying n elements                O(n)

    Sorting complexity depends on the selected sorting
    method, input type and implementation.

    Array memory usage is proportional to its length,
    excluding referenced objects for reference arrays.


    ============================================================
                    14. INTERVIEW QUESTIONS
    ============================================================

    1. What is an array?
       An object storing a fixed number of elements
       of the same component type.

    2. Are arrays objects in Java?
       Yes.

    3. Is array length fixed?
       Yes, after creation.

    4. What is the default value of an int array?
       Zero.

    5. What is the default value of a reference array?
       Null.

    6. What is the first index of an array?
       Zero.

    7. What is the difference between length and length()?
       length is an array property; length() is a method
       used by types such as String.

    8. What happens when an invalid index is accessed?
       ArrayIndexOutOfBoundsException.

    9. Can an array store different data types?
       It stores elements compatible with its component type.

    10. What is a jagged array?
        An array of arrays whose inner arrays can have
        different lengths.

    11. What is array covariance?
        The ability to assign a subtype array to a
        supertype array reference.

    12. What is ArrayStoreException?
        A runtime exception caused by storing an
        incompatible reference in an array.

    13. What is the difference between a shallow copy
        and a deep copy?
        A shallow copy shares referenced objects;
        a deep copy duplicates the referenced objects
        as well.

    14. What is the difference between a and a.clone()?
        Assignment copies the reference; clone creates
        a separate array.

    15. What is System.arraycopy()?
        A method that copies elements between arrays.

    16. What does Arrays.sort() do?
        Sorts array elements.

    17. What does Arrays.binarySearch() require?
        An array sorted using the matching ordering.

    18. Can we create a zero-length array?
        Yes.

    19. Can an array reference be null?
        Yes.

    20. Are arrays passed by reference in Java?
        No. Java passes the reference value by value.

    21. Can we create generic arrays directly?
        Not arrays of parameterized types such as
        new List<String>[5].

    22. What is the difference between arrays and ArrayList?
        Arrays have fixed length; ArrayList is resizable.

    23. What does Arrays.deepToString() do?
        Produces a readable String representation of
        nested arrays.

    24. What is the result of Arrays.equals(a, b)?
        A boolean indicating whether corresponding
        elements are equal.

    25. What is the result of Arrays.mismatch(a, b)?
        The first differing index, or -1 if they match.


    ============================================================
                    15. REVISION CHECKLIST
    ============================================================

    [ ] Array fundamentals.
    [ ] Declaration and creation.
    [ ] Initialization and default values.
    [ ] Indexing and traversal.
    [ ] One-dimensional arrays.
    [ ] Two-dimensional and multidimensional arrays.
    [ ] Jagged arrays.
    [ ] Array references and memory model.
    [ ] Copying and cloning.
    [ ] Shallow copy and deep copy.
    [ ] Array comparison.
    [ ] java.util.Arrays methods.
    [ ] Passing and returning arrays.
    [ ] Varargs.
    [ ] Array covariance.
    [ ] ArrayStoreException.
    [ ] Generic array restrictions.
    [ ] Arrays vs Collections.
    [ ] Time complexity and interview questions.


    ============================================================
                       END OF ARRAYS
    ============================================================

    FILE COMPLETE:
        Java Arrays

    ============================================================

    */

   public static void main(String[] args) {
    System.out.println("The above is all about Arrays , Happy Learning ...!");
   }
}
