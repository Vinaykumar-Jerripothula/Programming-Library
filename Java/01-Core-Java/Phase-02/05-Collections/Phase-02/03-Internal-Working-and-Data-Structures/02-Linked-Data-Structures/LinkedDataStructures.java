
public class LinkedDataStructures {

    /*
     * ============================================================
     * LINKED DATA STRUCTURES IN JAVA
     * ============================================================
     *
     * 1. WHY DO WE NEED LINKED DATA STRUCTURES?
     *
     * Consider an array:
     *
     * [10, 20, 30, 40, 50]
     *
     * If we insert 15 at index 1:
     *
     * [10, 15, 20, 30, 40, 50]
     *
     * Existing elements must shift to make space.
     *
     * What if we could connect elements using references instead?
     *
     * That is the basic idea behind a Linked List.
     *
     * Instead of storing elements in consecutive indexed
     * positions, a linked structure stores elements in nodes.
     *
     * Each node contains:
     *
     * 1. Data.
     * 2. Reference(s) to other node(s).
     *
     *
     * ============================================================
     * 2. WHAT IS A NODE?
     * ============================================================
     *
     * A node is an individual unit of a linked data structure.
     *
     * A basic node contains:
     *
     * +------+------+
     * | Data | Next |
     * +------+------+
     *
     * Example:
     *
     * class Node {
     *     int data;
     *     Node next;
     *
     *     Node(int data) {
     *         this.data = data;
     *         this.next = null;
     *     }
     * }
     *
     * Here:
     *
     * data -> Stores the actual value.
     * next -> Stores the reference to the next node.
     *
     * IMPORTANT:
     *
     * next is not the next node's data.
     *
     * It is a reference pointing to the next Node object.
     *
     *
     * ============================================================
     * 3. WHAT IS A LINKED LIST?
     * ============================================================
     *
     * A Linked List is a linear data structure consisting of
     * nodes connected through references.
     *
     * Example:
     *
     * head
     *  |
     *  v
     * +------+------+
     * |  10  | next | ----+
     * +------+------+    |
     *                     v
     *               +------+------+
     *               |  20  | next | ----+
     *               +------+------+    |
     *                                    v
     *                              +------+------+
     *                              |  30  | null |
     *                              +------+------+
     *
     * The first node is called the head.
     *
     * The last node points to null in a normal singly linked list.
     *
     * A linked list can grow or shrink dynamically by connecting
     * or disconnecting nodes.
     *
     *
     * ============================================================
     * 4. HEAD AND TAIL
     * ============================================================
     *
     * HEAD:
     * Reference to the first node.
     *
     * TAIL:
     * Reference to the last node.
     *
     * Example:
     *
     * head                         tail
     *  |                            |
     *  v                            v
     * [10 | next] -> [20 | next] -> [30 | null]
     *
     * Why maintain a tail?
     *
     * Without a tail reference, adding an element at the end
     * requires traversing the entire list.
     *
     * With a tail reference, appending can be O(1).
     *
     * IMPORTANT:
     *
     * Not every linked list implementation maintains a tail.
     *
     *
     * ============================================================
     * 5. TYPES OF LINKED LISTS
     * ============================================================
     *
     * Major types:
     *
     * 1. Singly Linked List.
     * 2. Doubly Linked List.
     * 3. Circular Singly Linked List.
     * 4. Circular Doubly Linked List.
     *
     *
     * ============================================================
     * 6. SINGLY LINKED LIST
     * ============================================================
     *
     * Each node stores:
     *
     * - Data.
     * - Reference to the next node.
     *
     * Structure:
     *
     * head
     *  |
     *  v
     * [10 | next] -> [20 | next] -> [30 | null]
     *
     * Traversal is possible only in the forward direction.
     *
     * Example node:
     *
     * class Node {
     *     int data;
     *     Node next;
     * }
     *
     * Advantages:
     *
     * - Dynamic size.
     * - No shifting of existing elements during insertion
     *   after a known node.
     * - Requires only one link per node.
     *
     * Disadvantages:
     *
     * - Cannot directly access an element by index.
     * - Cannot move backward.
     * - Searching requires traversal.
     *
     *
     * ============================================================
     * 7. CREATING A SINGLY LINKED LIST
     * ============================================================
     *
     * Example:
     *
     * class Node {
     *     int data;
     *     Node next;
     *
     *     Node(int data) {
     *         this.data = data;
     *     }
     * }
     *
     * Node first = new Node(10);
     * Node second = new Node(20);
     * Node third = new Node(30);
     *
     * first.next = second;
     * second.next = third;
     *
     * Node head = first;
     *
     * The resulting list:
     *
     * 10 -> 20 -> 30 -> null
     *
     * Each next reference connects one node to another.
     *
     *
     * ============================================================
     * 8. TRAVERSING A SINGLY LINKED LIST
     * ============================================================
     *
     * Traversal means visiting nodes one by one.
     *
     * Example:
     *
     * Node current = head;
     *
     * while (current != null) {
     *
     *     System.out.println(current.data);
     *
     *     current = current.next;
     * }
     *
     * HOW DOES IT WORK?
     *
     * 1. Start from head.
     * 2. Read the current node's data.
     * 3. Move to current.next.
     * 4. Repeat until current becomes null.
     *
     * Time complexity: O(n).
     *
     * IMPORTANT:
     *
     * We move using references, not indexes.
     *
     *
     * ============================================================
     * 9. INSERTION IN SINGLY LINKED LIST
     * ============================================================
     *
     * A. INSERT AT THE BEGINNING
     *
     * Before:
     *
     * head -> 10 -> 20 -> 30 -> null
     *
     * Insert 5:
     *
     * newNode.next = head;
     * head = newNode;
     *
     * After:
     *
     * head -> 5 -> 10 -> 20 -> 30 -> null
     *
     * Complexity: O(1).
     *
     * B. INSERT AT THE END
     *
     * If we maintain a tail:
     *
     * tail.next = newNode;
     * tail = newNode;
     *
     * Complexity: O(1).
     *
     * Without a tail, traversal is required.
     *
     * Complexity without tail: O(n).
     *
     * C. INSERT AFTER A KNOWN NODE
     *
     * Suppose we want to insert 25 after 20.
     *
     * Before:
     * 10 -> 20 -> 30 -> null
     *
     * Steps:
     *
     * newNode.next = current.next;
     * current.next = newNode;
     *
     * After:
     * 10 -> 20 -> 25 -> 30 -> null
     *
     * Complexity: O(1), once the node is already known.
     *
     *
     * ============================================================
     * 10. DELETION IN SINGLY LINKED LIST
     * ============================================================
     *
     * A. DELETE FIRST NODE
     *
     * Before:
     * head -> 10 -> 20 -> 30 -> null
     *
     * Operation:
     *
     * head = head.next;
     *
     * After:
     * head -> 20 -> 30 -> null
     *
     * Complexity: O(1).
     *
     * B. DELETE A NODE AFTER A KNOWN PREVIOUS NODE
     *
     * Before:
     * 10 -> 20 -> 30 -> 40 -> null
     *
     * Delete 30:
     *
     * previous.next = current.next;
     *
     * After:
     * 10 -> 20 -> 40 -> null
     *
     * Complexity: O(1), once the previous node is known.
     *
     * C. DELETE LAST NODE
     *
     * In a singly linked list, we need to find the node
     * immediately before the last node.
     *
     * Complexity: O(n).
     *
     * WHY?
     *
     * We cannot move backward from the last node.
     *
     *
     * ============================================================
     * 11. DOUBLY LINKED LIST
     * ============================================================
     *
     * A Doubly Linked List stores two references in each node:
     *
     * 1. Previous node.
     * 2. Next node.
     *
     * Node structure:
     *
     * +------+-------+------+
     * | Prev | Data  | Next |
     * +------+-------+------+
     *
     * Example:
     *
     * null <- 10 <-> 20 <-> 30 -> null
     *
     * Each node can move:
     *
     * - Forward using next.
     * - Backward using prev.
     *
     * Example:
     *
     * class Node {
     *     int data;
     *     Node prev;
     *     Node next;
     * }
     *
     * Advantages:
     *
     * - Forward traversal.
     * - Backward traversal.
     * - Easier deletion when the node is already known.
     *
     * Disadvantages:
     *
     * - Additional memory for the previous reference.
     * - More reference updates during insertion and deletion.
     *
     *
     * ============================================================
     * 12. INSERTION IN DOUBLY LINKED LIST
     * ============================================================
     *
     * Insert a new node between A and B.
     *
     * Before:
     *
     * A <-> B
     *
     * After:
     *
     * A <-> NewNode <-> B
     *
     * Required connections:
     *
     * newNode.prev = A;
     * newNode.next = B;
     *
     * A.next = newNode;
     * B.prev = newNode;
     *
     * IMPORTANT:
     *
     * Handle special cases:
     *
     * - Inserting at the beginning.
     * - Inserting at the end.
     * - Inserting into an empty list.
     *
     * If the new node becomes the head or tail, update those
     * references appropriately.
     *
     * Complexity: O(1) once the insertion position is known.
     *
     *
     * ============================================================
     * 13. DELETION IN DOUBLY LINKED LIST
     * ============================================================
     *
     * Suppose we want to delete node B.
     *
     * Before:
     *
     * A <-> B <-> C
     *
     * Update:
     *
     * A.next = C;
     * C.prev = A;
     *
     * After:
     *
     * A <-> C
     *
     * Complexity: O(1), when the node reference is already known.
     *
     * WHY IS THIS EASIER THAN SINGLY LINKED LIST?
     *
     * A doubly linked node already knows its previous node.
     *
     * A singly linked node does not.
     *
     *
     * ============================================================
     * 14. CIRCULAR SINGLY LINKED LIST
     * ============================================================
     *
     * In a normal singly linked list:
     *
     * 10 -> 20 -> 30 -> null
     *
     * In a circular singly linked list:
     *
     * 10 -> 20 -> 30
     * ^           |
     * |___________|
     *
     * The last node points back to the first node.
     *
     * Therefore:
     *
     * tail.next = head;
     *
     * There is no null reference at the end of a non-empty
     * circular list.
     *
     * Applications:
     *
     * - Round-robin scheduling.
     * - Repeated cyclic traversal.
     * - Turn-based systems.
     *
     * IMPORTANT:
     *
     * Traversal needs a stopping condition other than
     * current != null.
     *
     * Otherwise, it may continue forever.
     *
     *
     * ============================================================
     * 15. CIRCULAR DOUBLY LINKED LIST
     * ============================================================
     *
     * It combines:
     *
     * - Doubly linked structure.
     * - Circular connections.
     *
     * In a circular doubly linked list:
     *
     * tail.next = head;
     * head.prev = tail;
     *
     * Both forward and backward traversal are circular.
     *
     * Applications:
     *
     * - Repeating navigation systems.
     * - Playlists with next and previous navigation.
     * - Cyclic scheduling.
     *
     *
     * ============================================================
     * 16. LINKED LIST VS ARRAY
     * ============================================================
     *
     * Array:
     *
     * - Elements are indexed.
     * - Direct access is O(1).
     * - Fixed length.
     * - Insertions in the middle require shifting.
     *
     * Linked List:
     *
     * - Elements are connected through references.
     * - Indexed access is O(n).
     * - Can grow and shrink dynamically.
     * - Insertion/deletion at a known node can be O(1).
     *
     * IMPORTANT:
     *
     * Linked Lists do not automatically make every insertion
     * and deletion O(1).
     *
     * Finding the required position may still take O(n).
     *
     *
     * ============================================================
     * 17. JAVA LINKEDLIST IMPLEMENTATION
     * ============================================================
     *
     * java.util.LinkedList is a doubly linked list implementation.
     *
     * It implements:
     *
     * - List
     * - Deque
     * - Queue
     * - Cloneable
     * - Serializable
     *
     * It extends AbstractSequentialList.
     *
     * Example:
     *
     * LinkedList<Integer> list = new LinkedList<>();
     *
     * list.add(10);
     * list.add(20);
     * list.add(30);
     *
     * Result:
     * [10, 20, 30]
     *
     * It supports:
     *
     * - addFirst()
     * - addLast()
     * - removeFirst()
     * - removeLast()
     * - getFirst()
     * - getLast()
     * - offer()
     * - poll()
     * - peek()
     *
     *
     * ============================================================
     * 18. HOW DOES JAVA LINKEDLIST WORK INTERNALLY?
     * ============================================================
     *
     * OpenJDK's LinkedList uses a doubly linked list.
     *
     * Its structure conceptually maintains:
     *
     * - first: reference to the first node.
     * - last: reference to the last node.
     * - size: number of elements.
     *
     * Each internal node stores:
     *
     * - item: element reference.
     * - next: next node reference.
     * - prev: previous node reference.
     *
     * Conceptual internal node:
     *
     * static class Node<E> {
     *     E item;
     *     Node<E> next;
     *     Node<E> prev;
     * }
     *
     * IMPORTANT:
     *
     * This is a simplified representation of the implementation.
     *
     * The actual Node class is an internal implementation detail,
     * not a public API that application code should use.
     *
     *
     * ============================================================
     * 19. LINKEDLIST INDEXED ACCESS
     * ============================================================
     *
     * Example:
     *
     * LinkedList<Integer> list = new LinkedList<>(
     *     List.of(10, 20, 30, 40, 50)
     * );
     *
     * list.get(3);
     *
     * Result: 40
     *
     * But how does it locate index 3?
     *
     * It traverses nodes.
     *
     * OpenJDK's LinkedList optimizes traversal direction:
     *
     * - If the index is closer to the beginning, traverse forward.
     * - Otherwise, traverse backward from the end.
     *
     * Complexity:
     *
     * get(index) -> O(n) in general.
     *
     *
     * ============================================================
     * 20. LINKEDLIST INSERTION AND DELETION COMPLEXITY
     * ============================================================
     *
     * addFirst()       -> O(1)
     * addLast()        -> O(1)
     * removeFirst()    -> O(1)
     * removeLast()     -> O(1)
     *
     * get(index)       -> O(n)
     * add(index, e)    -> O(n) in general
     * remove(index)    -> O(n) in general
     *
     * WHY?
     *
     * Indexed operations must first locate the relevant node.
     *
     * Once the node is located, updating links is O(1).
     *
     *
     * ============================================================
     * 21. WHY DOES LINKEDLIST NOT IMPLEMENT RANDOMACCESS?
     * ============================================================
     *
     * RandomAccess is a marker interface.
     *
     * It indicates that a List implementation supports
     * fast random access, typically constant-time indexed access.
     *
     * ArrayList implements RandomAccess.
     *
     * LinkedList does not implement RandomAccess.
     *
     * WHY?
     *
     * LinkedList must traverse nodes to access an index.
     *
     * Therefore, it does not provide efficient random access.
     *
     * Some library algorithms can use this marker to choose
     * an appropriate traversal strategy.
     *
     *
     * ============================================================
     * 22. LINKEDLIST MEMORY OVERHEAD
     * ============================================================
     *
     * ArrayList:
     *
     * Stores element references in an internal array.
     *
     * LinkedList:
     *
     * Each node stores:
     *
     * - Element reference.
     * - Previous reference.
     * - Next reference.
     *
     * Therefore, LinkedList has additional per-node memory overhead.
     *
     * It also involves more individual node objects and reference
     * chasing.
     *
     * ArrayList often provides better cache locality because
     * its element references are stored in a contiguous array.
     *
     * IMPORTANT:
     *
     * Java does not guarantee that separately allocated objects
     * are physically located in any particular memory order.
     *
     *
     * ============================================================
     * 23. MEMORY MANAGEMENT AND GARBAGE COLLECTION
     * ============================================================
     *
     * Consider:
     *
     * head -> Node1 -> Node2 -> Node3 -> null
     *
     * If we remove Node2 by changing:
     *
     * Node1.next = Node3;
     *
     * Node2 may become unreachable.
     *
     * If no other reachable object refers to Node2, it becomes
     * eligible for garbage collection.
     *
     * IMPORTANT:
     *
     * Eligible for garbage collection does not mean immediately
     * collected.
     *
     * The JVM decides when garbage collection happens.
     *
     * Linked structures must manage references correctly to avoid
     * accidentally retaining objects.
     *
     *
     * ============================================================
     * 24. LINKED LIST APPLICATIONS
     * ============================================================
     *
     * 1. Implementing stacks.
     *
     * 2. Implementing queues.
     *
     * 3. Implementing deques.
     *
     * 4. Browser navigation history.
     *
     * 5. Undo and redo functionality.
     *
     * 6. Round-robin scheduling.
     *
     * 7. Playlists with forward and backward navigation.
     *
     * 8. Maintaining frequently changing sequences.
     *
     * IMPORTANT:
     *
     * A linked list is a possible implementation of these
     * structures, not necessarily the best choice in every case.
     *
     *
     * ============================================================
     * 25. LINKED LIST VS ARRAYLIST - PRACTICAL DECISION
     * ============================================================
     *
     * Choose ArrayList when:
     *
     * - Frequent indexed access is required.
     * - Iteration is frequent.
     * - Appending is common.
     * - Memory efficiency and cache locality matter.
     *
     * Choose LinkedList when:
     *
     * - You specifically need linked-node behavior.
     * - You frequently insert or remove at the ends.
     * - You already have references to the nodes involved.
     * - Deque operations are needed and its trade-offs are suitable.
     *
     * IMPORTANT:
     *
     * For many real-world Java applications, ArrayList is a
     * better default than LinkedList.
     *
     * Do not select LinkedList simply because it supports
     * insertion and deletion.
     *
     *
     * ============================================================
     * 26. COMMON LINKED LIST MISTAKES
     * ============================================================
     *
     * 1. Assuming LinkedList supports O(1) indexed access.
     *
     * 2. Assuming insertion at any index is O(1).
     *
     * 3. Forgetting to update head after deleting the first node.
     *
     * 4. Forgetting to update tail after deleting the last node.
     *
     * 5. Forgetting to update both prev and next in a doubly
     *    linked list.
     *
     * 6. Creating an infinite traversal in a circular linked list.
     *
     * 7. Assuming a removed node is immediately garbage-collected.
     *
     * 8. Assuming LinkedList always performs better than ArrayList
     *    for insertions and deletions.
     *
     * 9. Forgetting that LinkedList stores node objects and
     *    references, not just elements.
     *
     * 10. Losing the head reference and making the list inaccessible.
     *
     *
     * ============================================================
     * 27. IMPORTANT INTERVIEW QUESTIONS
     * ============================================================
     *
     * 1. What is a Linked List?
     *
     * 2. What is a Node?
     *
     * 3. Difference between singly and doubly linked lists?
     *
     * 4. What is a circular linked list?
     *
     * 5. What is the purpose of head and tail?
     *
     * 6. Why is indexed access O(n) in LinkedList?
     *
     * 7. Why is insertion after a known node O(1)?
     *
     * 8. Why is deletion from a singly linked list more
     *    complicated than from a doubly linked list?
     *
     * 9. Which interfaces does java.util.LinkedList implement?
     *
     * 10. Why does LinkedList not implement RandomAccess?
     *
     * 11. How does Java LinkedList locate an indexed node?
     *
     * 12. Why does LinkedList have more memory overhead
     *     than ArrayList?
     *
     * 13. What happens to a disconnected node?
     *
     * 14. Can a circular linked list be traversed using
     *     current != null?
     *
     * 15. Is LinkedList always better for insertion and deletion?
     *
     *
     * ============================================================
     * 28. QUICK REVISION
     * ============================================================
     *
     * Node:
     *     -> Stores data and reference(s).
     *
     * Linked List:
     *     -> Nodes connected through references.
     *
     * Head:
     *     -> First node.
     *
     * Tail:
     *     -> Last node.
     *
     * Singly Linked List:
     *     -> One next reference per node.
     *
     * Doubly Linked List:
     *     -> Previous and next references.
     *
     * Circular Linked List:
     *     -> Last node connects back to first.
     *
     * Traversal:
     *     -> O(n).
     *
     * Insert at head:
     *     -> O(1).
     *
     * Insert at tail:
     *     -> O(1) when tail is maintained.
     *
     * Indexed access:
     *     -> O(n).
     *
     * Java LinkedList:
     *     -> Doubly linked implementation of List and Deque.
     *
     * RandomAccess:
     *     -> Marker for fast indexed access.
     *
     * Garbage Collection:
     *     -> Reclaims unreachable objects when the JVM collects.
     *
     * ============================================================
     * END OF LINKED DATA STRUCTURES
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Linked Data Structures , Happy Learning ..!");
    }
}
