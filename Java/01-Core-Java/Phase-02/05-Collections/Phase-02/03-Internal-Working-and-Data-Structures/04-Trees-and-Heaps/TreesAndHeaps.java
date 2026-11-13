
public class TreesAndHeaps {

    /*
     * ============================================================
     * TREES AND HEAPS IN JAVA
     * ============================================================
     *
     * 1. WHY DO WE NEED TREES?
     *
     * So far, we have studied linear data structures:
     *
     * Array:
     * [10, 20, 30, 40]
     *
     * Linked List:
     * 10 -> 20 -> 30 -> 40
     *
     * In linear structures, elements are arranged sequentially.
     *
     * But consider an organization:
     *
     *                  CEO
     *                   |
     *           +-------+-------+
     *           |               |
     *         CTO             CFO
     *           |               |
     *       +---+---+         Manager
     *       |       |
     *    Developer Developer
     *
     * This is hierarchical data.
     *
     * A tree is a data structure designed to represent
     * hierarchical relationships.
     *
     * Trees are also useful for efficient searching, sorting,
     * indexing and priority management.
     *
     *
     * ============================================================
     * 2. WHAT IS A TREE?
     * ============================================================
     *
     * A Tree is a non-linear hierarchical data structure
     * consisting of nodes connected by edges.
     *
     * Example:
     *
     *             10
     *            /  \
     *           20   30
     *          /  \
     *         40   50
     *
     * Each value represents a node.
     *
     * Connections between nodes are called edges.
     *
     * The topmost node is called the root.
     *
     *
     * ============================================================
     * 3. IMPORTANT TREE TERMINOLOGY
     * ============================================================
     *
     * ROOT:
     * The topmost node.
     *
     * PARENT:
     * A node that has one or more children.
     *
     * CHILD:
     * A node directly connected below another node.
     *
     * SIBLINGS:
     * Nodes having the same parent.
     *
     * LEAF NODE:
     * A node that has no children.
     *
     * INTERNAL NODE:
     * A node that has at least one child.
     *
     * EDGE:
     * A connection between two nodes.
     *
     * ANCESTOR:
     * Any node on the path from the root to a particular node,
     * excluding that node itself.
     *
     * DESCENDANT:
     * Any node in the subtree rooted at a particular node,
     * excluding that node itself.
     *
     * SUBTREE:
     * A node together with all its descendants.
     *
     * DEPTH:
     * Number of edges from the root to a node.
     *
     * HEIGHT OF A NODE:
     * Number of edges on the longest downward path from
     * that node to a leaf.
     *
     * HEIGHT OF A TREE:
     * Height of its root.
     *
     * IMPORTANT:
     *
     * A leaf has height 0.
     *
     * The root has depth 0.
     *
     * Some textbooks define height using the number of nodes
     * instead of edges. Always check the convention being used.
     *
     *
     * ============================================================
     * 4. BINARY TREE
     * ============================================================
     *
     * A Binary Tree is a tree in which each node can have
     * at most two children.
     *
     * The children are called:
     *
     * 1. Left child.
     * 2. Right child.
     *
     * Example:
     *
     *              10
     *             /  \
     *            20   30
     *           / \
     *          40  50
     *
     * A node can have:
     *
     * - No children.
     * - Only a left child.
     * - Only a right child.
     * - Both children.
     *
     *
     * ============================================================
     * 5. TYPES OF BINARY TREES
     * ============================================================
     *
     * A. FULL BINARY TREE
     *
     * Every node has either:
     *
     * - Zero children.
     * - Exactly two children.
     *
     * No node has exactly one child.
     *
     * B. COMPLETE BINARY TREE
     *
     * Every level is completely filled except possibly
     * the last level.
     *
     * The last level is filled from left to right.
     *
     * C. PERFECT BINARY TREE
     *
     * Every internal node has exactly two children.
     *
     * All leaf nodes are at the same level.
     *
     * D. SKEWED BINARY TREE
     *
     * Every node has only one child, producing a chain-like
     * structure.
     *
     * It can be:
     *
     * - Left-skewed.
     * - Right-skewed.
     *
     * E. BALANCED BINARY TREE
     *
     * A tree whose height is maintained within a suitable
     * bound relative to its number of nodes.
     *
     * The exact definition depends on the balancing rule.
     *
     *
     * ============================================================
     * 6. BINARY TREE NODE IMPLEMENTATION
     * ============================================================
     *
     * A binary tree node generally contains:
     *
     * - Data.
     * - Reference to left child.
     * - Reference to right child.
     *
     * Example:
     *
     * class Node {
     *     int data;
     *     Node left;
     *     Node right;
     *
     *     Node(int data) {
     *         this.data = data;
     *     }
     * }
     *
     * Creating a tree:
     *
     * Node root = new Node(10);
     *
     * root.left = new Node(20);
     * root.right = new Node(30);
     *
     * root.left.left = new Node(40);
     * root.left.right = new Node(50);
     *
     *
     * ============================================================
     * 7. BINARY TREE TRAVERSALS
     * ============================================================
     *
     * Traversal means visiting every node in a particular order.
     *
     * Consider:
     *
     *              10
     *             /  \
     *            20   30
     *           / \
     *          40  50
     *
     * Four commonly studied traversals:
     *
     * 1. Inorder.
     * 2. Preorder.
     * 3. Postorder.
     * 4. Level Order.
     *
     *
     * ============================================================
     * 8. INORDER TRAVERSAL
     * ============================================================
     *
     * Order:
     *
     * LEFT -> ROOT -> RIGHT
     *
     * Steps:
     *
     * 1. Visit left subtree.
     * 2. Visit current node.
     * 3. Visit right subtree.
     *
     * Example:
     *
     *              10
     *             /  \
     *            20   30
     *           / \
     *          40  50
     *
     * Inorder:
     *
     * 40, 20, 50, 10, 30
     *
     * Recursive implementation:
     *
     * void inorder(Node root) {
     *
     *     if (root == null) return;
     *
     *     inorder(root.left);
     *
     *     System.out.println(root.data);
     *
     *     inorder(root.right);
     * }
     *
     * In a Binary Search Tree, inorder traversal produces
     * values in sorted order under the tree's ordering.
     *
     *
     * ============================================================
     * 9. PREORDER TRAVERSAL
     * ============================================================
     *
     * Order:
     *
     * ROOT -> LEFT -> RIGHT
     *
     * Steps:
     *
     * 1. Visit current node.
     * 2. Visit left subtree.
     * 3. Visit right subtree.
     *
     * Example:
     *
     * Preorder:
     *
     * 10, 20, 40, 50, 30
     *
     * Implementation:
     *
     * void preorder(Node root) {
     *
     *     if (root == null) return;
     *
     *     System.out.println(root.data);
     *
     *     preorder(root.left);
     *
     *     preorder(root.right);
     * }
     *
     * Useful for:
     *
     * - Tree serialization.
     * - Copying tree structures.
     * - Prefix expression generation.
     *
     *
     * ============================================================
     * 10. POSTORDER TRAVERSAL
     * ============================================================
     *
     * Order:
     *
     * LEFT -> RIGHT -> ROOT
     *
     * Steps:
     *
     * 1. Visit left subtree.
     * 2. Visit right subtree.
     * 3. Visit current node.
     *
     * Example:
     *
     * Postorder:
     *
     * 40, 50, 20, 30, 10
     *
     * Implementation:
     *
     * void postorder(Node root) {
     *
     *     if (root == null) return;
     *
     *     postorder(root.left);
     *
     *     postorder(root.right);
     *
     *     System.out.println(root.data);
     * }
     *
     * Useful for:
     *
     * - Deleting tree nodes.
     * - Evaluating expression trees.
     * - Processing children before parents.
     *
     *
     * ============================================================
     * 11. LEVEL ORDER TRAVERSAL
     * ============================================================
     *
     * Level Order visits nodes level by level.
     *
     * Example:
     *
     *              10
     *             /  \
     *            20   30
     *           / \
     *          40  50
     *
     * Level Order:
     *
     * 10, 20, 30, 40, 50
     *
     * It is commonly implemented using a Queue.
     *
     * Steps:
     *
     * 1. Insert root into queue.
     * 2. Remove the front node.
     * 3. Visit that node.
     * 4. Add its left child if present.
     * 5. Add its right child if present.
     * 6. Repeat until queue is empty.
     *
     * Implementation:
     *
     * void levelOrder(Node root) {
     *
     *     if (root == null) return;
     *
     *     Queue<Node> queue = new LinkedList<>();
     *
     *     queue.offer(root);
     *
     *     while (!queue.isEmpty()) {
     *
     *         Node current = queue.poll();
     *
     *         System.out.println(current.data);
     *
     *         if (current.left != null) {
     *             queue.offer(current.left);
     *         }
     *
     *         if (current.right != null) {
     *             queue.offer(current.right);
     *         }
     *     }
     * }
     *
     *
     * ============================================================
     * 12. BINARY SEARCH TREE (BST)
     * ============================================================
     *
     * A Binary Search Tree is a Binary Tree with an ordering rule.
     *
     * For a BST using distinct integer keys:
     *
     * - All keys in the left subtree are smaller than the node.
     * - All keys in the right subtree are greater than the node.
     *
     * Example:
     *
     *              50
     *             /  \
     *            30   70
     *           / \   / \
     *          20 40 60 80
     *
     * Left of 50:
     * 20, 30, 40
     *
     * Right of 50:
     * 60, 70, 80
     *
     * IMPORTANT:
     *
     * Duplicate-key handling depends on the BST implementation.
     * A common approach is to reject duplicates or store a count.
     *
     *
     * ============================================================
     * 13. BST SEARCH OPERATION
     * ============================================================
     *
     * Search for 60:
     *
     * Start at 50.
     *
     * 60 > 50:
     * Move right.
     *
     * Current node = 70.
     *
     * 60 < 70:
     * Move left.
     *
     * Current node = 60.
     *
     * Found.
     *
     * At each node, we eliminate one side of the search tree.
     *
     * Complexity:
     *
     * Balanced BST:
     * O(log n).
     *
     * Skewed BST:
     * O(n).
     *
     * IMPORTANT:
     *
     * A normal BST does not automatically guarantee balance.
     *
     *
     * ============================================================
     * 14. BST INSERTION
     * ============================================================
     *
     * To insert a new key:
     *
     * 1. Start from the root.
     * 2. Compare the new key with the current node.
     * 3. Move left if smaller.
     * 4. Move right if greater.
     * 5. Insert at the first available position.
     *
     * Example:
     *
     * Existing keys:
     * 50, 30, 70, 20, 40
     *
     * Insert 60:
     *
     * 60 > 50 -> right.
     * 60 < 70 -> left.
     * Insert 60 as left child of 70.
     *
     * Complexity:
     *
     * O(h), where h is the height of the tree.
     *
     *
     * ============================================================
     * 15. BST DELETION
     * ============================================================
     *
     * Three major cases:
     *
     * CASE 1: LEAF NODE
     *
     * The node has no children.
     *
     * Simply remove it.
     *
     * CASE 2: ONE CHILD
     *
     * Replace the node with its only child.
     *
     * CASE 3: TWO CHILDREN
     *
     * Replace the node with its inorder successor
     * or inorder predecessor.
     *
     * Inorder successor:
     * Smallest key in the right subtree.
     *
     * Inorder predecessor:
     * Largest key in the left subtree.
     *
     * After replacement, remove the successor or predecessor
     * from its original position.
     *
     *
     * ============================================================
     * 16. WHY DO WE NEED BALANCED TREES?
     * ============================================================
     *
     * Consider inserting sorted values:
     *
     * 10, 20, 30, 40, 50
     *
     * A normal BST may become:
     *
     * 10
     *   \
     *    20
     *      \
     *       30
     *         \
     *          40
     *            \
     *             50
     *
     * This resembles a linked list.
     *
     * Search complexity becomes O(n).
     *
     * Balanced trees control their height to keep operations
     * efficient.
     *
     * Examples:
     *
     * - AVL Tree.
     * - Red-Black Tree.
     * - B-Tree.
     * - B+ Tree.
     *
     *
     * ============================================================
     * 17. AVL TREE
     * ============================================================
     *
     * An AVL Tree is a self-balancing Binary Search Tree.
     *
     * It maintains a balance condition at every node.
     *
     * Balance Factor:
     *
     * Height(left subtree) - Height(right subtree)
     *
     * For an AVL Tree, the balance factor of every node
     * must be -1, 0 or +1.
     *
     * If an insertion or deletion violates the balance
     * condition, rotations restore balance.
     *
     * Four rotation cases:
     *
     * 1. LL - Left Left.
     * 2. RR - Right Right.
     * 3. LR - Left Right.
     * 4. RL - Right Left.
     *
     * Search, insertion and deletion:
     *
     * O(log n).
     *
     * AVL Trees are strictly balanced compared with
     * Red-Black Trees.
     *
     *
     * ============================================================
     * 18. RED-BLACK TREE
     * ============================================================
     *
     * A Red-Black Tree is a self-balancing Binary Search Tree.
     *
     * Each node has a color:
     *
     * - Red.
     * - Black.
     *
     * It follows specific properties to keep the tree
     * approximately balanced.
     *
     * Important properties:
     *
     * 1. Every node is either red or black.
     *
     * 2. The root is black.
     *
     * 3. All null leaves (NIL leaves) are black.
     *
     * 4. A red node cannot have a red child.
     *
     * 5. Every path from a node to its descendant NIL leaves
     *    contains the same number of black nodes.
     *
     * These rules ensure the tree height remains O(log n).
     *
     * Operations:
     *
     * Search:     O(log n)
     * Insertion:  O(log n)
     * Deletion:   O(log n)
     *
     * Balancing uses:
     *
     * - Recoloring.
     * - Left rotation.
     * - Right rotation.
     *
     * Java uses Red-Black Trees internally in TreeMap.
     *
     * TreeSet is backed by TreeMap in OpenJDK.
     *
     *
     * ============================================================
     * 19. WHAT IS A HEAP?
     * ============================================================
     *
     * A Heap is a specialized tree-based data structure
     * that follows the heap property.
     *
     * It is commonly used to implement Priority Queues.
     *
     * Two major types:
     *
     * 1. Min Heap.
     * 2. Max Heap.
     *
     * IMPORTANT:
     *
     * A Heap is not the same as the JVM heap memory.
     *
     * Heap data structure:
     * A tree-based ordering structure.
     *
     * JVM heap:
     * Runtime memory area where objects are allocated.
     *
     *
     * ============================================================
     * 20. MIN HEAP
     * ============================================================
     *
     * In a Min Heap:
     *
     * Every parent is less than or equal to its children.
     *
     * Example:
     *
     *              10
     *             /  \
     *            20   30
     *           / \
     *          40  50
     *
     * The minimum element is at the root.
     *
     * Root = 10.
     *
     * IMPORTANT:
     *
     * A Min Heap does not mean every level is completely sorted.
     *
     * It only guarantees the parent-child heap property.
     *
     *
     * ============================================================
     * 21. MAX HEAP
     * ============================================================
     *
     * In a Max Heap:
     *
     * Every parent is greater than or equal to its children.
     *
     * Example:
     *
     *              90
     *             /  \
     *            70   80
     *           / \
     *          20  50
     *
     * The maximum element is at the root.
     *
     * Root = 90.
     *
     *
     * ============================================================
     * 22. COMPLETE BINARY TREE AND HEAPS
     * ============================================================
     *
     * A Binary Heap is commonly represented using a
     * Complete Binary Tree.
     *
     * Why?
     *
     * A complete tree can be stored efficiently in an array.
     *
     * Example:
     *
     *              10
     *             /  \
     *            20   30
     *           / \
     *          40  50
     *
     * Array representation:
     *
     * [10, 20, 30, 40, 50]
     *
     * No separate left and right references are required
     * for each element in this representation.
     *
     *
     * ============================================================
     * 23. HEAP ARRAY INDEX RELATIONSHIPS
     * ============================================================
     *
     * For a zero-based array representation:
     *
     * Parent index:
     * (i - 1) / 2
     *
     * Left child:
     * (2 * i) + 1
     *
     * Right child:
     * (2 * i) + 2
     *
     * Example:
     *
     * Index:  0   1   2   3   4
     * Value: 10  20  30  40  50
     *
     * For index 1:
     *
     * Parent = 0
     * Left child = 3
     * Right child = 4
     *
     * These formulas assume the child index exists.
     *
     *
     * ============================================================
     * 24. HEAP INSERTION
     * ============================================================
     *
     * Insertion in a Binary Heap:
     *
     * 1. Insert the new element at the end of the array.
     * 2. Compare it with its parent.
     * 3. If the heap property is violated, swap them.
     * 4. Repeat until the property is restored.
     *
     * This process is called:
     *
     * Heapify Up
     * or
     * Sift Up.
     *
     * Example: Min Heap.
     *
     * Existing:
     * [10, 20, 30, 40]
     *
     * Insert 5:
     *
     * [10, 20, 30, 40, 5]
     *
     * Compare 5 with parent 20.
     *
     * Swap:
     * [10, 5, 30, 40, 20]
     *
     * Compare 5 with parent 10.
     *
     * Swap:
     * [5, 10, 30, 40, 20]
     *
     * Heap property is restored.
     *
     * Complexity: O(log n).
     *
     *
     * ============================================================
     * 25. HEAP DELETION
     * ============================================================
     *
     * The most common deletion removes the root.
     *
     * For a Min Heap, the root is the minimum element.
     *
     * Steps:
     *
     * 1. Save the root element.
     * 2. Move the last element to the root position.
     * 3. Remove the last array position.
     * 4. Compare the new root with its children.
     * 5. Swap with the smaller child if necessary.
     * 6. Repeat until the heap property is restored.
     *
     * This process is called:
     *
     * Heapify Down
     * or
     * Sift Down.
     *
     * Complexity: O(log n).
     *
     *
     * ============================================================
     * 26. HEAP OPERATIONS COMPLEXITY
     * ============================================================
     *
     * Peek root:
     * O(1)
     *
     * Insert:
     * O(log n)
     *
     * Remove root:
     * O(log n)
     *
     * Build heap from an existing array:
     * O(n)
     *
     * Search for an arbitrary element:
     * O(n)
     *
     * IMPORTANT:
     *
     * A heap is designed to access the minimum or maximum
     * efficiently, not to search for arbitrary elements.
     *
     *
     * ============================================================
     * 27. BUILD HEAP - O(N)
     * ============================================================
     *
     * A common mistake is assuming that building a heap
     * requires O(n log n).
     *
     * Bottom-up heap construction takes O(n).
     *
     * It starts heapifying from the last non-leaf node
     * toward the root.
     *
     * Why?
     *
     * Most nodes are near the leaves and require very little
     * movement.
     *
     * Only a small number of nodes can move through many levels.
     *
     * Therefore, the total work is O(n).
     *
     *
     * ============================================================
     * 28. JAVA PRIORITYQUEUE
     * ============================================================
     *
     * Java provides:
     *
     * java.util.PriorityQueue
     *
     * It is based on a priority heap.
     *
     * By default, it orders elements according to natural order.
     *
     * Example:
     *
     * PriorityQueue<Integer> queue = new PriorityQueue<>();
     *
     * queue.offer(40);
     * queue.offer(10);
     * queue.offer(30);
     *
     * queue.peek(); // 10
     *
     * poll() removes and returns the head.
     *
     * queue.poll(); // 10
     *
     * The next head becomes 30.
     *
     *
     * ============================================================
     * 29. PRIORITYQUEUE - MIN HEAP AND MAX HEAP
     * ============================================================
     *
     * Default PriorityQueue:
     *
     * PriorityQueue<Integer> minHeap =
     *     new PriorityQueue<>();
     *
     * The smallest element has the highest priority.
     *
     * Max Heap:
     *
     * PriorityQueue<Integer> maxHeap =
     *     new PriorityQueue<>(Comparator.reverseOrder());
     *
     * The largest element has the highest priority.
     *
     * IMPORTANT:
     *
     * Java's PriorityQueue is a Min Heap by default
     * when natural ordering is used.
     *
     *
     * ============================================================
     * 30. PRIORITYQUEUE INTERNAL ARRAY
     * ============================================================
     *
     * PriorityQueue uses an array-based heap.
     *
     * It does not use a linked tree of separate node objects.
     *
     * The heap is represented using an internal array.
     *
     * Example:
     *
     *              10
     *             /  \
     *            20   30
     *           / \
     *          40  50
     *
     * Internal logical order:
     *
     * [10, 20, 30, 40, 50]
     *
     * The smallest element is maintained at the root.
     *
     * IMPORTANT:
     *
     * Iterating over a PriorityQueue does not guarantee
     * sorted order.
     *
     * Repeatedly calling poll() retrieves elements in priority
     * order.
     *
     *
     * ============================================================
     * 31. PRIORITYQUEUE TIME COMPLEXITY
     * ============================================================
     *
     * offer():
     * O(log n)
     *
     * add():
     * O(log n)
     *
     * poll():
     * O(log n)
     *
     * peek():
     * O(1)
     *
     * size():
     * O(1)
     *
     * contains():
     * O(n)
     *
     * remove(Object):
     * O(n)
     *
     * IMPORTANT:
     *
     * These are the standard complexity expectations for
     * Java PriorityQueue.
     *
     *
     * ============================================================
     * 32. HEAP VS BINARY SEARCH TREE
     * ============================================================
     *
     * Binary Search Tree:
     *
     * - Left subtree follows smaller-key ordering.
     * - Right subtree follows greater-key ordering.
     * - Supports efficient searching when balanced.
     * - Inorder traversal gives sorted order.
     *
     * Heap:
     *
     * - Follows parent-child heap ordering.
     * - Root contains minimum or maximum.
     * - Efficient priority retrieval.
     * - Does not provide efficient arbitrary searching.
     *
     * Remember:
     *
     * BST -> Efficient ordered searching.
     * Heap -> Efficient priority retrieval.
     *
     *
     * ============================================================
     * 33. HEAP SORT
     * ============================================================
     *
     * Heap Sort is a comparison-based sorting algorithm
     * that uses a Binary Heap.
     *
     * Steps for ascending order:
     *
     * 1. Build a Max Heap.
     * 2. Swap the root with the last element.
     * 3. Reduce the heap's effective size.
     * 4. Heapify the root.
     * 5. Repeat until sorted.
     *
     * Complexity:
     *
     * Best case:    O(n log n)
     * Average case: O(n log n)
     * Worst case:   O(n log n)
     *
     * Auxiliary space:
     * O(1) for the standard in-place array implementation.
     *
     * Heap Sort is not stable.
     *
     *
     * ============================================================
     * 34. B-TREE
     * ============================================================
     *
     * A B-Tree is a self-balancing multiway search tree.
     *
     * Unlike a Binary Search Tree, a node can contain
     * multiple keys and have multiple children.
     *
     * It is designed to reduce tree height.
     *
     * Commonly used in:
     *
     * - Database indexes.
     * - File systems.
     * - Storage systems.
     *
     * Why?
     *
     * Disk and storage access can be expensive.
     *
     * B-Trees store multiple keys per node, reducing the
     * number of levels and potentially reducing disk accesses.
     *
     * IMPORTANT:
     *
     * Java TreeMap does not use a B-Tree.
     * It uses a Red-Black Tree in OpenJDK.
     *
     *
     * ============================================================
     * 35. B+ TREE
     * ============================================================
     *
     * A B+ Tree is a variation of a B-Tree.
     *
     * Common characteristics:
     *
     * - Internal nodes guide searching.
     * - Actual records or record references are stored
     *   at the leaf level.
     * - Leaf nodes are commonly linked for sequential traversal.
     *
     * Advantages:
     *
     * - Efficient range queries.
     * - Efficient sequential access.
     * - Suitable for database indexing.
     *
     * B+ Trees are widely used in database storage engines.
     *
     * The exact design differs between database systems.
     *
     *
     * ============================================================
     * 36. TREE VS HEAP VS HASH TABLE
     * ============================================================
     *
     * TREE:
     *
     * - Hierarchical structure.
     * - Can support ordered searching.
     * - Performance depends on tree balancing.
     *
     * HEAP:
     *
     * - Specialized complete binary tree.
     * - Maintains minimum or maximum at the root.
     * - Used for priority queues.
     *
     * HASH TABLE:
     *
     * - Uses hashing and buckets.
     * - Expected O(1) lookup under suitable distribution.
     * - Does not maintain sorted key order.
     *
     * Typical Java implementations:
     *
     * TreeMap -> Red-Black Tree.
     * TreeSet -> Backed by TreeMap.
     * PriorityQueue -> Array-based binary heap.
     * HashMap -> Hash table with list/tree bins.
     *
     *
     * ============================================================
     * 37. COMMON MISTAKES
     * ============================================================
     *
     * 1. Assuming every binary tree is a Binary Search Tree.
     *
     * 2. Assuming a normal BST is always balanced.
     *
     * 3. Confusing tree height and node depth.
     *
     * 4. Confusing full, complete and perfect binary trees.
     *
     * 5. Assuming every node in a heap is globally sorted.
     *
     * 6. Assuming PriorityQueue iteration is sorted.
     *
     * 7. Confusing the data-structure heap with JVM heap memory.
     *
     * 8. Assuming a heap supports O(log n) arbitrary searching.
     *
     * 9. Assuming Java TreeMap uses a B-Tree.
     *
     * 10. Assuming building a heap requires O(n log n).
     *
     * 11. Forgetting that BST performance depends on height.
     *
     * 12. Confusing AVL Trees and Red-Black Trees.
     *
     *
     * ============================================================
     * 38. IMPORTANT INTERVIEW QUESTIONS
     * ============================================================
     *
     * 1. What is a Tree?
     *
     * 2. What is a Binary Tree?
     *
     * 3. Difference between Full, Complete and Perfect Trees?
     *
     * 4. What is a Binary Search Tree?
     *
     * 5. What are inorder, preorder and postorder traversals?
     *
     * 6. Why does inorder traversal of a BST produce sorted data?
     *
     * 7. What is a balanced tree?
     *
     * 8. What is an AVL Tree?
     *
     * 9. What is a Red-Black Tree?
     *
     * 10. What are the properties of a Red-Black Tree?
     *
     * 11. What is a Heap?
     *
     * 12. Difference between Min Heap and Max Heap?
     *
     * 13. How is a Binary Heap represented in an array?
     *
     * 14. What is heapify up and heapify down?
     *
     * 15. Why does PriorityQueue use a heap?
     *
     * 16. Why is peek() O(1) in PriorityQueue?
     *
     * 17. Why is arbitrary search O(n) in a heap?
     *
     * 18. What is Heap Sort?
     *
     * 19. Why is bottom-up heap construction O(n)?
     *
     * 20. Difference between a BST and a Heap?
     *
     * 21. What is a B-Tree?
     *
     * 22. What is a B+ Tree?
     *
     * 23. Which tree does Java TreeMap use internally?
     *
     *
     * ============================================================
     * 39. QUICK REVISION
     * ============================================================
     *
     * Tree:
     *     -> Hierarchical non-linear data structure.
     *
     * Root:
     *     -> Topmost node.
     *
     * Leaf:
     *     -> Node without children.
     *
     * Binary Tree:
     *     -> At most two children per node.
     *
     * BST:
     *     -> Ordered Binary Tree.
     *
     * Inorder:
     *     -> Left, Root, Right.
     *
     * Preorder:
     *     -> Root, Left, Right.
     *
     * Postorder:
     *     -> Left, Right, Root.
     *
     * Level Order:
     *     -> Level by level using a Queue.
     *
     * AVL:
     *     -> Self-balancing BST with balance factor -1, 0, +1.
     *
     * Red-Black Tree:
     *     -> Self-balancing BST using node colors.
     *
     * Heap:
     *     -> Complete binary tree with heap ordering.
     *
     * Min Heap:
     *     -> Smallest element at root.
     *
     * Max Heap:
     *     -> Largest element at root.
     *
     * Heap insertion:
     *     -> O(log n).
     *
     * Heap removal:
     *     -> O(log n).
     *
     * Heap peek:
     *     -> O(1).
     *
     * Heap construction:
     *     -> O(n).
     *
     * PriorityQueue:
     *     -> Java's priority-based queue implementation.
     *
     * TreeMap:
     *     -> Red-Black Tree-based sorted Map in OpenJDK.
     *
     * B-Tree / B+ Tree:
     *     -> Multiway search trees used in storage and databases.
     *
     * ============================================================
     * END OF TREES AND HEAPS
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Trees and Heaps , Happy Learning ..!");
    }
}
