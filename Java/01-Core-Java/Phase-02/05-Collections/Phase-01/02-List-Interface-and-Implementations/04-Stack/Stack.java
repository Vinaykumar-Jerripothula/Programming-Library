
public class Stack {

    /*
     * ============================================================
     *                            STACK
     * ============================================================
     *
     * 1. WHAT IS A STACK?
     *
     * Imagine a pile of plates.
     *
     * You place a new plate on top of the existing plates.
     *
     * When you need a plate, you normally remove the top plate
     * first.
     *
     * You cannot directly remove a plate from the middle
     * without disturbing the plates above it.
     *
     * This is the fundamental idea behind a Stack.
     *
     * A Stack is a linear data structure that follows:
     *
     *     LIFO = Last In, First Out
     *
     * The element inserted last is the first one removed.
     *
     * Example:
     *
     *     Push 10
     *     Push 20
     *     Push 30
     *
     *     Stack:
     *
     *          30  <- TOP
     *          20
     *          10
     *
     *     Pop() -> 30
     *
     *
     * 2. WHAT IS THE STACK CLASS IN JAVA?
     *
     * Java provides a built-in Stack class.
     *
     * Package:
     *     java.util
     *
     * Declaration:
     *
     *     public class Stack<E> extends Vector<E>
     *
     * E represents the element type.
     *
     * IMPORTANT:
     *
     * Stack is a class, not an interface.
     *
     * Stack extends Vector.
     *
     * Therefore, Stack inherits Vector's public methods
     * and its resizable-array implementation.
     *
     *
     * 3. STACK HIERARCHY
     *
     *                    Iterable
     *                        |
     *                    Collection
     *                        |
     *                      List
     *                        |
     *                  AbstractList
     *                        |
     *                     Vector
     *                        |
     *                      Stack
     *
     * Stack inherits:
     *
     * - List behavior.
     * - Vector behavior.
     * - RandomAccess support.
     * - Synchronized Vector methods.
     *
     * This inheritance relationship is one reason Stack
     * is considered a legacy collection class.
     *
     *
     * 4. WHY DOES JAVA PROVIDE STACK?
     *
     * A normal List allows operations at different positions.
     *
     * But a Stack represents a restricted access pattern:
     *
     *     Insert at the top.
     *     Remove from the top.
     *     Inspect the top.
     *
     * This makes Stack useful for problems where the most
     * recently added element must be processed first.
     *
     *
     * 5. CREATING A STACK
     *
     * Example:
     *
     *     Stack<Integer> stack = new Stack<>();
     *
     * Add elements:
     *
     *     stack.push(10);
     *     stack.push(20);
     *     stack.push(30);
     *
     * Display:
     *
     *     System.out.println(stack);
     *
     * Output:
     *
     *     [10, 20, 30]
     *
     * The last element is the top.
     *
     * Stack uses the end of its underlying Vector as the top.
     *
     *
     * 6. PUSH OPERATION
     *
     * push(element) inserts an element at the top.
     *
     * Example:
     *
     *     Stack<Integer> stack = new Stack<>();
     *
     *     stack.push(10);
     *     stack.push(20);
     *     stack.push(30);
     *
     * Conceptual representation:
     *
     *          30  <- TOP
     *          20
     *          10
     *
     * Each new element becomes the top element.
     *
     * Return value:
     *
     *     push(element) returns the inserted element.
     *
     * Time complexity:
     *
     *     Amortized O(1)
     *
     * A resize may make an individual insertion O(n).
     *
     *
     * 7. POP OPERATION
     *
     * pop() removes and returns the top element.
     *
     * Example:
     *
     *     Stack<Integer> stack = new Stack<>();
     *
     *     stack.push(10);
     *     stack.push(20);
     *     stack.push(30);
     *
     *     int removed = stack.pop();
     *
     *     System.out.println(removed); // 30
     *     System.out.println(stack);   // [10, 20]
     *
     * The top element is removed first.
     *
     * IMPORTANT:
     *
     * Calling pop() on an empty Stack throws
     * EmptyStackException.
     *
     * Time complexity:
     *
     *     O(1)
     *
     *
     * 8. PEEK OPERATION
     *
     * peek() returns the top element without removing it.
     *
     * Example:
     *
     *     Stack<Integer> stack = new Stack<>();
     *
     *     stack.push(10);
     *     stack.push(20);
     *     stack.push(30);
     *
     *     System.out.println(stack.peek()); // 30
     *     System.out.println(stack);        // [10, 20, 30]
     *
     * The Stack remains unchanged.
     *
     * Difference:
     *
     *     pop()  -> Returns and removes the top element.
     *     peek() -> Returns the top element without removing it.
     *
     * Calling peek() on an empty Stack throws
     * EmptyStackException.
     *
     * Time complexity:
     *
     *     O(1)
     *
     *
     * 9. EMPTY OPERATION
     *
     * empty() checks whether the Stack contains no elements.
     *
     * Example:
     *
     *     Stack<Integer> stack = new Stack<>();
     *
     *     System.out.println(stack.empty()); // true
     *
     *     stack.push(10);
     *
     *     System.out.println(stack.empty()); // false
     *
     * This is a legacy Stack-specific method.
     *
     * You can also use the inherited:
     *
     *     isEmpty()
     *
     * Both indicate whether the Stack contains zero elements.
     *
     *
     * 10. SEARCH OPERATION
     *
     * Stack provides:
     *
     *     search(Object object)
     *
     * It searches for an element starting from the top.
     *
     * IMPORTANT:
     *
     * The returned position is 1-based, not zero-based.
     *
     * Example:
     *
     *     Stack<String> stack = new Stack<>();
     *
     *     stack.push("A");
     *     stack.push("B");
     *     stack.push("C");
     *
     * Stack:
     *
     *          C  <- TOP
     *          B
     *          A
     *
     *     stack.search("C"); // 1
     *     stack.search("B"); // 2
     *     stack.search("A"); // 3
     *
     * If the element is not found:
     *
     *     search("X") -> -1
     *
     * If duplicates exist, search() returns the position
     * of the nearest matching occurrence from the top.
     *
     * Time complexity:
     *
     *     O(n)
     *
     *
     * 11. COMPLETE STACK EXAMPLE
     *
     *     Stack<Integer> stack = new Stack<>();
     *
     *     stack.push(10);
     *     stack.push(20);
     *     stack.push(30);
     *
     *     System.out.println(stack);        // [10, 20, 30]
     *     System.out.println(stack.peek()); // 30
     *     System.out.println(stack.pop());  // 30
     *     System.out.println(stack);        // [10, 20]
     *     System.out.println(stack.empty()); // false
     *
     *
     * 12. INTERNAL WORKING OF STACK
     *
     * Stack extends Vector.
     *
     * Vector internally uses a resizable array.
     *
     * Therefore, Stack also uses a resizable array.
     *
     * Conceptual representation:
     *
     *     Index:       0       1       2       3
     *                [10]    [20]    [30]    [  ]
     *                                ^
     *                                |
     *                               TOP
     *
     * In Java's Stack implementation:
     *
     * - push() adds an element at the end of Vector.
     * - pop() removes the last element.
     * - peek() reads the last element.
     *
     * IMPORTANT:
     *
     * Java's Stack class is not implemented using linked nodes.
     *
     *
     * 13. WHY DOES STACK EXTEND VECTOR?
     *
     * Vector already provides:
     *
     * - Resizable storage.
     * - Indexed access.
     * - Element insertion and removal.
     * - Synchronized methods.
     *
     * Stack adds LIFO-specific methods:
     *
     * - push()
     * - pop()
     * - peek()
     * - empty()
     * - search()
     *
     * However, this inheritance design exposes many operations
     * that are not normally desirable for a restricted Stack.
     *
     * For example, callers can still use:
     *
     *     stack.add(10);
     *     stack.remove(0);
     *     stack.set(0, 100);
     *
     * These operations can bypass the intended LIFO usage.
     *
     *
     * 14. STACK TIME COMPLEXITY
     *
     * Operation                Complexity
     * ------------------------------------
     * push(element)            Amortized O(1)
     * pop()                    O(1)
     * peek()                   O(1)
     * empty()                  O(1)
     * search(element)          O(n)
     * size()                   O(1)
     *
     * These complexities describe the usual implementation.
     *
     * A push that triggers backing-array growth may take O(n).
     *
     *
     * 15. STACK AND EMPTY EXCEPTIONS
     *
     * Consider:
     *
     *     Stack<Integer> stack = new Stack<>();
     *
     *     stack.pop();
     *
     * This throws:
     *
     *     EmptyStackException
     *
     * Similarly:
     *
     *     stack.peek();
     *
     * Also throws EmptyStackException.
     *
     * Check before accessing:
     *
     *     if (!stack.empty()) {
     *         System.out.println(stack.pop());
     *     }
     *
     * IMPORTANT:
     *
     * A separate empty() check followed by pop() is not
     * an atomic operation when multiple threads access
     * the Stack.
     *
     *
     * 16. STACK AND SYNCHRONIZATION
     *
     * Stack extends Vector.
     *
     * Vector synchronizes its individual public methods.
     *
     * Stack's push(), pop(), peek(), empty() and search()
     * methods are also synchronized in the JDK implementation.
     *
     * Therefore, individual Stack operations obtain the
     * Stack object's monitor.
     *
     * IMPORTANT:
     *
     * Synchronization of individual operations does not
     * automatically make a sequence of operations atomic.
     *
     * Example:
     *
     *     if (!stack.empty()) {
     *         stack.pop();
     *     }
     *
     * Another thread could modify the Stack between these calls.
     *
     * If the complete sequence must be atomic:
     *
     *     synchronized (stack) {
     *         if (!stack.empty()) {
     *             stack.pop();
     *         }
     *     }
     *
     *
     * 17. STACK VS ARRAYDEQUE
     *
     * ArrayDeque is a modern Deque implementation.
     *
     * It can be used to implement Stack behavior.
     *
     * Example:
     *
     *     Deque<Integer> stack = new ArrayDeque<>();
     *
     *     stack.push(10);
     *     stack.push(20);
     *     stack.push(30);
     *
     *     System.out.println(stack.pop());  // 30
     *     System.out.println(stack.peek()); // 20
     *
     * Comparison:
     *
     * Feature              Stack              ArrayDeque
     * ---------------------------------------------------
     * Type                 Class               Class
     * Interface            List via Vector     Deque
     * Internal structure   Resizable array     Resizable array
     * LIFO operations      Yes                 Yes
     * Synchronization      Synchronized        Not synchronized
     * Null elements        Allowed             Not allowed
     * Legacy status        Legacy              Modern choice
     *
     * IMPORTANT:
     *
     * ArrayDeque is generally preferred for single-threaded
     * stack operations.
     *
     * If multiple threads need to access a stack, choose
     * an appropriate concurrency design rather than assuming
     * ArrayDeque is thread-safe.
     *
     *
     * 18. WHY IS ARRAYDEQUE GENERALLY PREFERRED?
     *
     * Reasons:
     *
     * 1. It is designed specifically for Deque operations.
     * 2. It avoids the legacy inheritance design of Stack.
     * 3. It does not synchronize every operation.
     * 4. It is generally efficient for stack and queue usage.
     *
     * Example:
     *
     *     Deque<String> history = new ArrayDeque<>();
     *
     *     history.push("Home");
     *     history.push("Products");
     *     history.push("Cart");
     *
     *     System.out.println(history.pop()); // Cart
     *
     * IMPORTANT:
     *
     * ArrayDeque rejects null elements.
     *
     *
     * 19. STACK VS LINKEDLIST AS A STACK
     *
     * LinkedList also implements Deque.
     *
     * Therefore, it can be used as a Stack.
     *
     * Example:
     *
     *     Deque<Integer> stack = new LinkedList<>();
     *
     *     stack.push(10);
     *     stack.push(20);
     *     stack.push(30);
     *
     *     System.out.println(stack.pop()); // 30
     *
     * Comparison:
     *
     * Stack:
     * - Legacy class.
     * - Extends Vector.
     * - Synchronized individual operations.
     *
     * LinkedList:
     * - Doubly linked list.
     * - Implements Deque.
     * - Not synchronized.
     *
     * ArrayDeque is generally preferred for ordinary
     * stack operations.
     *
     *
     * 20. STACK AND NULL
     *
     * Stack inherits Vector's ability to store null.
     *
     * Example:
     *
     *     Stack<String> stack = new Stack<>();
     *
     *     stack.push("Vinay");
     *     stack.push(null);
     *     stack.push("Ravi");
     *
     * Null is permitted.
     *
     * However, null values are usually best avoided
     * in Stack applications.
     *
     *
     * 21. STACK AND DUPLICATES
     *
     * Stack permits duplicate elements.
     *
     * Example:
     *
     *     Stack<Integer> stack = new Stack<>();
     *
     *     stack.push(10);
     *     stack.push(20);
     *     stack.push(10);
     *
     * Result:
     *
     *     [10, 20, 10]
     *
     * The top element is the last 10.
     *
     *
     * 22. STACK TRAVERSAL
     *
     * Stack inherits List traversal methods.
     *
     * Example:
     *
     *     Stack<Integer> stack = new Stack<>();
     *
     *     stack.push(10);
     *     stack.push(20);
     *     stack.push(30);
     *
     *     for (Integer value : stack) {
     *         System.out.println(value);
     *     }
     *
     * Iteration through the inherited List iterator
     * traverses elements from index 0 to the last index.
     *
     * This means bottom to top.
     *
     * IMPORTANT:
     *
     * Traversal order is not the same as repeated pop order.
     *
     * Iteration:
     *     10, 20, 30
     *
     * Repeated pop:
     *     30, 20, 10
     *
     *
     * 23. STACK AND FAIL-FAST ITERATORS
     *
     * Stack inherits Vector's iterator behavior.
     *
     * Its Iterator and ListIterator are generally fail-fast.
     *
     * Example:
     *
     *     Stack<String> stack =
     *         new Stack<>();
     *
     *     stack.push("A");
     *     stack.push("B");
     *     stack.push("C");
     *
     *     for (String value : stack) {
     *         if (value.equals("B")) {
     *             stack.pop();
     *         }
     *     }
     *
     * This may throw ConcurrentModificationException.
     *
     * Fail-fast behavior is best-effort bug detection.
     *
     * It does not make Stack thread-safe for iteration.
     *
     *
     * 24. STACK REAL-WORLD APPLICATIONS
     *
     * A. UNDO OPERATIONS
     *
     * In a text editor:
     *
     *     Type A
     *     Type B
     *     Type C
     *
     * Undo removes the most recent action first.
     *
     * B. BROWSER HISTORY
     *
     * Recently visited pages can be managed using
     * stack-like history behavior.
     *
     * C. FUNCTION CALL STACK
     *
     * Method calls are managed using a runtime call stack.
     *
     * D. EXPRESSION EVALUATION
     *
     * Stacks are used in:
     *
     * - Infix to postfix conversion.
     * - Postfix expression evaluation.
     * - Parentheses matching.
     *
     * E. BACKTRACKING
     *
     * Stacks help track previous states or decisions.
     *
     * F. DEPTH-FIRST SEARCH
     *
     * Graph traversal can use an explicit Stack.
     *
     * IMPORTANT:
     *
     * These are applications of the Stack data structure.
     * They do not necessarily use java.util.Stack.
     *
     *
     * 25. STACK VS CALL STACK
     *
     * java.util.Stack:
     *
     * - A Java collection class.
     * - Created and managed by application code.
     * - Stores references to objects.
     *
     * JVM call stack:
     *
     * - Runtime memory structure used for method execution.
     * - Each thread has its own JVM stack.
     * - Stores stack frames for method invocations.
     * - Supports local variables and method execution state.
     *
     * IMPORTANT:
     *
     * java.util.Stack and the JVM call stack are different.
     *
     *
     * 26. STACK OVERFLOW
     *
     * StackOverflowError occurs when a thread's JVM stack
     * cannot accommodate additional method-call frames.
     *
     * Common cause:
     *
     * Infinite or excessively deep recursion.
     *
     * Example:
     *
     *     static void call() {
     *         call();
     *     }
     *
     * This is different from java.util.Stack being full.
     *
     * IMPORTANT:
     *
     * StackOverflowError is an Error, not an exception
     * representing an empty java.util.Stack.
     *
     *
     * 27. COMMON STACK EXCEPTIONS
     *
     * EmptyStackException:
     * - Calling pop() or peek() on an empty Stack.
     *
     * IndexOutOfBoundsException:
     * - Invalid inherited List index operations.
     *
     * ConcurrentModificationException:
     * - May occur when a fail-fast iterator detects
     *   unexpected structural modification.
     *
     *
     * 28. COMMON MISTAKES
     *
     * 1. Assuming Stack is an interface.
     *    WRONG: It is a class.
     *
     * 2. Assuming Stack uses linked nodes.
     *    WRONG: It inherits Vector's resizable array.
     *
     * 3. Assuming Stack is the same as the JVM call stack.
     *    WRONG: They are different concepts.
     *
     * 4. Assuming pop() only reads the top element.
     *    WRONG: It removes and returns it.
     *
     * 5. Assuming peek() removes the top element.
     *    WRONG: It only reads the top element.
     *
     * 6. Assuming search() returns a zero-based index.
     *    WRONG: It returns a 1-based position from the top.
     *
     * 7. Assuming Stack allows only push and pop.
     *    WRONG: It inherits many Vector/List operations.
     *
     * 8. Assuming synchronization makes every sequence atomic.
     *    WRONG: Multiple operations may need an external lock.
     *
     * 9. Assuming ArrayDeque is thread-safe.
     *    WRONG: ArrayDeque is not synchronized.
     *
     * 10. Confusing StackOverflowError with EmptyStackException.
     *     They represent entirely different problems.
     *
     *
     * 29. INTERVIEW QUESTIONS
     *
     * FUNDAMENTALS:
     *
     * 1. What is a Stack?
     * 2. What does LIFO mean?
     * 3. What is the Stack class in Java?
     * 4. Which class does Stack extend?
     * 5. Which package contains Stack?
     *
     * METHODS:
     *
     * 6. What is push()?
     * 7. What is pop()?
     * 8. What is peek()?
     * 9. What is empty()?
     * 10. What is search()?
     * 11. What does search() return when an element is absent?
     * 12. What exception does pop() throw on an empty Stack?
     *
     * INTERNAL WORKING:
     *
     * 13. Which data structure does Stack use internally?
     * 14. Why does Stack extend Vector?
     * 15. Is Stack synchronized?
     * 16. Is Stack completely thread-safe?
     * 17. Why can inherited methods violate LIFO usage?
     *
     * COMPARISONS:
     *
     * 18. Stack vs ArrayDeque?
     * 19. Stack vs LinkedList?
     * 20. Stack vs Vector?
     * 21. java.util.Stack vs JVM call stack?
     *
     * ADVANCED:
     *
     * 22. What is StackOverflowError?
     * 23. What is EmptyStackException?
     * 24. What is the difference between iteration and pop order?
     * 25. Why is ArrayDeque generally preferred?
     *
     *
     * 30. FINAL REVISION CHECKLIST
     *
     * [ ] Understand Stack and LIFO.
     * [ ] Understand the java.util.Stack class.
     * [ ] Understand its inheritance from Vector.
     * [ ] Understand its internal resizable array.
     * [ ] Know push(), pop() and peek().
     * [ ] Understand empty() and isEmpty().
     * [ ] Understand search() and its 1-based position.
     * [ ] Know Stack's time complexities.
     * [ ] Understand synchronization and atomicity.
     * [ ] Understand inherited Vector/List methods.
     * [ ] Understand iteration order versus pop order.
     * [ ] Understand Stack versus ArrayDeque.
     * [ ] Understand Stack versus JVM call stack.
     * [ ] Understand StackOverflowError.
     * [ ] Know real-world Stack applications.
     * [ ] Know why ArrayDeque is generally preferred.
     *
     *
     * 31. WHAT YOU SHOULD REMEMBER
     *
     * Stack = LIFO.
     *
     * LIFO = Last In, First Out.
     *
     * Java Stack:
     *     java.util.Stack
     *
     * Inheritance:
     *     Stack extends Vector.
     *
     * Internal structure:
     *     Resizable array.
     *
     * Main methods:
     *
     *     push()  -> Insert at top.
     *     pop()   -> Remove and return top.
     *     peek()  -> Read top without removing.
     *     empty() -> Check whether empty.
     *     search()-> Find position from top (1-based).
     *
     * Empty pop/peek:
     *     EmptyStackException.
     *
     * Modern alternative:
     *     ArrayDeque.
     *
     * IMPORTANT:
     *
     * Stack is a legacy class.
     * ArrayDeque is generally preferred for modern
     * single-threaded Stack operations.
     *
     * ============================================================
     *                        END OF STACK
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Stack , Happy Learning ..!");
    }
}
