
public class Memento {

    /*
     * ============================================================
     * 1. INTRODUCTION
     * ============================================================
     *
     * Imagine you are developing a text editor.
     *
     * A user types:
     *
     *     Hello
     *
     * Then changes it to:
     *
     *     Hello World
     *
     * Then changes it again:
     *
     *     Hello World Java
     *
     * Now the user presses UNDO.
     *
     * The editor should restore the previous content:
     *
     *     Hello World
     *
     * How can we achieve this?
     *
     * Before changing the document, we can save its current state.
     *
     * Later, if the user wants to undo the change,
     * we restore the saved state.
     *
     * But there is an important design requirement:
     *
     * The object whose state is saved should not expose
     * its internal implementation details to other objects.
     *
     * The Memento pattern solves this problem.
     *
     *
     * DEFINITION:
     *
     * Memento is a behavioral design pattern that captures
     * and stores an object's internal state so that the object
     * can be restored to that state later without exposing
     * its internal representation.
     *
     *
     * SIMPLE DEFINITION:
     *
     * Save an object's state as a snapshot and restore it later.
     *
     *
     * ============================================================
     * 2. WHY DO WE NEED MEMENTO?
     * ============================================================
     *
     * Consider a TextEditor class:
     *
     * class TextEditor {
     *
     *     private String content;
     *
     *     public void setContent(String content) {
     *         this.content = content;
     *     }
     *
     *     public String getContent() {
     *         return content;
     *     }
     * }
     *
     * We can save the content like this:
     *
     * String backup = editor.getContent();
     *
     * But what if the object has many internal fields?
     *
     * - Content
     * - Cursor position
     * - Font size
     * - Selection range
     * - Formatting information
     *
     * The client would need to know all these implementation
     * details to save and restore the object's state.
     *
     * This creates tight coupling.
     *
     * Instead, the object can create a snapshot of its own state.
     *
     * The snapshot is represented by a Memento.
     *
     * The client can store and request restoration of snapshots
     * without directly accessing the object's internal state.
     *
     *
     * ============================================================
     * 3. REAL-WORLD ANALOGY
     * ============================================================
     *
     * Imagine playing a video game.
     *
     * You reach level 5 and save your game.
     *
     * Then you continue playing and lose a life.
     *
     * You can load the previous save point.
     *
     * The game restores the saved state:
     *
     * - Player position.
     * - Health.
     * - Inventory.
     * - Level progress.
     *
     * The saved game represents a snapshot of the game state.
     *
     * Similarly, Memento captures an object's state
     * so it can be restored later.
     *
     *
     * ============================================================
     * 4. STRUCTURE AND PARTICIPANTS
     * ============================================================
     *
     * Main participants:
     *
     * 1. Originator:
     *
     *    The object whose state needs to be saved.
     *
     *    It creates Mementos and restores its state from them.
     *
     *
     * 2. Memento:
     *
     *    Stores a snapshot of the Originator's state.
     *
     *    Its internal state should be protected from
     *    unauthorized access or modification.
     *
     *
     * 3. Caretaker:
     *
     *    Stores and manages Mementos.
     *
     *    It does not inspect or modify their internal state.
     *
     *
     * 4. Client:
     *
     *    Coordinates saving and restoration, often by asking
     *    the Originator to create a Memento and giving it
     *    to the Caretaker for storage.
     *
     *
     * BASIC STRUCTURE:
     *
     *          Client
     *             |
     *             v
     *         Originator
     *             |
     *             | createMemento()
     *             v
     *           Memento
     *             |
     *             v
     *          Caretaker
     *
     * Later:
     *
     *          Caretaker
     *             |
     *             | returns saved Memento
     *             v
     *         Originator
     *             |
     *             | restore()
     *             v
     *         Previous State
     *
     *
     * ============================================================
     * 5. COMPLETE JAVA IMPLEMENTATION
     * ============================================================
     *
     * We will implement a simple text editor with undo support.
     *
     *
     * STEP 1: Create the Memento.
     *
     * final class EditorMemento {
     *
     *     private final String content;
     *
     *     public EditorMemento(String content) {
     *         this.content = content;
     *     }
     *
     *     String getSavedContent() {
     *         return content;
     *     }
     * }
     *
     * The Memento stores the editor's previous content.
     *
     * Its constructor and accessor can be restricted
     * to package-level access in this simple Java example.
     *
     * The client should not receive unrestricted access
     * to the saved state.
     *
     *
     * STEP 2: Create the Originator.
     *
     * class TextEditor {
     *
     *     private String content = "";
     *
     *     public void write(String text) {
     *
     *         content += text;
     *     }
     *
     *     public void showContent() {
     *
     *         System.out.println("Content: " + content);
     *     }
     *
     *     public EditorMemento save() {
     *
     *         return new EditorMemento(content);
     *     }
     *
     *     public void restore(EditorMemento memento) {
     *
     *         content = memento.getSavedContent();
     *     }
     * }
     *
     * TextEditor is the Originator.
     *
     * It creates snapshots of its state.
     *
     * It also knows how to restore its own state.
     *
     *
     * STEP 3: Create the Caretaker.
     *
     * import java.util.ArrayDeque;
     * import java.util.Deque;
     *
     * class History {
     *
     *     private final Deque<EditorMemento> history =
     *         new ArrayDeque<>();
     *
     *     public void save(EditorMemento memento) {
     *
     *         history.push(memento);
     *     }
     *
     *     public EditorMemento undo() {
     *
     *         if (history.isEmpty()) {
     *             return null;
     *         }
     *
     *         return history.pop();
     *     }
     * }
     *
     * History stores snapshots.
     *
     * It does not need to understand the editor's internal state.
     *
     *
     * STEP 4: Client code.
     *
     * TextEditor editor = new TextEditor();
     * History history = new History();
     *
     * editor.write("Hello");
     * history.save(editor.save());
     *
     * editor.write(" World");
     * history.save(editor.save());
     *
     * editor.write(" Java");
     *
     * editor.showContent();
     *
     * editor.restore(history.undo());
     *
     * editor.showContent();
     *
     * editor.restore(history.undo());
     *
     * editor.showContent();
     *
     *
     * OUTPUT:
     *
     * Content: Hello World Java
     * Content: Hello World
     * Content: Hello
     *
     *
     * ============================================================
     * 6. UNDERSTANDING THE EXECUTION FLOW
     * ============================================================
     *
     * Initially:
     *
     * content = ""
     *
     *
     * STEP 1:
     *
     * editor.write("Hello");
     *
     * Current state:
     *
     * Hello
     *
     * Save the state:
     *
     * history.save(editor.save());
     *
     * Memento 1:
     *
     * Hello
     *
     *
     * STEP 2:
     *
     * editor.write(" World");
     *
     * Current state:
     *
     * Hello World
     *
     * Save again.
     *
     * Memento 2:
     *
     * Hello World
     *
     *
     * STEP 3:
     *
     * editor.write(" Java");
     *
     * Current state:
     *
     * Hello World Java
     *
     *
     * STEP 4:
     *
     * User presses Undo.
     *
     * history.undo() returns Memento 2.
     *
     * Originator restores:
     *
     * Hello World
     *
     *
     * STEP 5:
     *
     * User presses Undo again.
     *
     * history.undo() returns Memento 1.
     *
     * Originator restores:
     *
     * Hello
     *
     *
     * IMPORTANT:
     *
     * The Caretaker stores snapshots.
     *
     * The Originator understands and restores them.
     *
     *
     * ============================================================
     * 7. WHY DOES THE CARETAKER NOT MODIFY THE MEMENTO?
     * ============================================================
     *
     * Consider:
     *
     * EditorMemento memento = editor.save();
     *
     * The Caretaker stores this object.
     *
     * It should not need to know:
     *
     * - How the content is stored.
     * - Which fields are included.
     * - How the state should be restored.
     *
     * It simply stores and returns the Memento.
     *
     * This protects encapsulation.
     *
     * The Originator remains responsible for its own state.
     *
     *
     * ============================================================
     * 8. WHY SHOULD A MEMENTO BE IMMUTABLE?
     * ============================================================
     *
     * Imagine saving this state:
     *
     * content = "Hello"
     *
     * Later, someone modifies the saved snapshot:
     *
     * content = "Modified"
     *
     * Now the snapshot no longer represents the original state.
     *
     * This can break undo functionality.
     *
     * Therefore, Mementos should generally be immutable.
     *
     * In our example:
     *
     * private final String content;
     *
     * String is immutable, and the field is final.
     *
     * This makes the stored content safe from modification
     * through the Memento reference.
     *
     * IMPORTANT:
     *
     * If the saved state contains mutable objects,
     * copying only their references may not create a true snapshot.
     *
     * A defensive copy or another suitable snapshot strategy
     * may be necessary.
     *
     *
     * ============================================================
     * 9. SHALLOW SNAPSHOT VS DEEP SNAPSHOT
     * ============================================================
     *
     * Consider:
     *
     * class User {
     *
     *     String name;
     *     Address address;
     * }
     *
     * Suppose a Memento stores:
     *
     * this.address = user.address;
     *
     * This stores a reference to the same Address object.
     *
     * If the original Address is modified later,
     * the saved Memento may also appear to have changed.
     *
     *
     * SHALLOW SNAPSHOT:
     *
     * Copies field values but may retain references
     * to the same nested mutable objects.
     *
     *
     * DEEP SNAPSHOT:
     *
     * Copies the relevant nested mutable state so that
     * later changes do not alter the saved snapshot.
     *
     *
     * IMPORTANT:
     *
     * Memento does not automatically guarantee deep copying.
     *
     * The Originator must decide how to capture its state correctly.
     *
     *
     * ============================================================
     * 10. UNDO VS REDO
     * ============================================================
     *
     * UNDO:
     *
     * Restores an earlier state.
     *
     * REDO:
     *
     * Restores or reapplies a state that was undone.
     *
     *
     * A common approach uses two stacks:
     *
     * Undo Stack:
     *
     * Stores previous states.
     *
     * Redo Stack:
     *
     * Stores states that can be restored again.
     *
     *
     * BASIC FLOW:
     *
     * Save state:
     *
     * Push current snapshot to undo history.
     *
     *
     * Undo:
     *
     * 1. Save the current state in redo history.
     * 2. Restore the most recent state from undo history.
     *
     *
     * Redo:
     *
     * 1. Save the current state in undo history.
     * 2. Restore the most recent state from redo history.
     *
     *
     * IMPORTANT:
     *
     * When a new edit happens after undoing,
     * the redo history is generally cleared.
     *
     * Otherwise, the redo branch may no longer represent
     * a valid future editing sequence.
     *
     *
     * ============================================================
     * 11. MEMENTO VS COMMAND
     * ============================================================
     *
     * Both patterns can be used to implement undo functionality.
     *
     *
     * COMMAND:
     *
     * Encapsulates an operation as an object.
     *
     * Undo may be implemented by reversing the operation
     * or restoring information stored by the command.
     *
     *
     * MEMENTO:
     *
     * Captures an object's state at a particular point in time.
     *
     * Undo is implemented by restoring a previous snapshot.
     *
     *
     * EXAMPLE:
     *
     * Command:
     *
     * A DeleteTextCommand stores information about the text
     * it deleted so that it can restore that text.
     *
     *
     * Memento:
     *
     * Stores a snapshot of the complete editor state
     * before the deletion.
     *
     *
     * REMEMBER:
     *
     * Command -> Stores or represents an operation.
     *
     * Memento -> Stores an object's state.
     *
     * They can also be combined.
     *
     *
     * ============================================================
     * 12. MEMENTO VS PROTOTYPE
     * ============================================================
     *
     * PROTOTYPE:
     *
     * Creates new objects by copying existing objects.
     *
     *
     * MEMENTO:
     *
     * Captures an object's state so that it can be restored later.
     *
     *
     * REMEMBER:
     *
     * Prototype -> Copy an object to create another object.
     *
     * Memento -> Save and restore an object's state.
     *
     *
     * ============================================================
     * 13. MEMENTO IN JAVA AND REAL APPLICATIONS
     * ============================================================
     *
     * Memento-style approaches are useful in applications
     * that need state history.
     *
     * Examples:
     *
     * 1. Text editors:
     *
     *    Save document states for undo and redo.
     *
     * 2. Drawing applications:
     *
     *    Restore earlier canvas states.
     *
     * 3. Games:
     *
     *    Save checkpoints and restore gameplay state.
     *
     * 4. Configuration editors:
     *
     *    Restore previous configuration versions.
     *
     * 5. Workflow applications:
     *
     *    Restore selected application state after an action.
     *
     *
     * ============================================================
     * 14. MEMENTO IN BACKEND DEVELOPMENT
     * ============================================================
     *
     * Consider a backend application that allows users
     * to edit configuration settings.
     *
     * Before applying a change:
     *
     * 1. Capture the current configuration.
     * 2. Store the snapshot.
     * 3. Apply the new configuration.
     *
     * If the change must be reversed:
     *
     * 1. Retrieve the previous snapshot.
     * 2. Restore the saved configuration.
     *
     * Other possible use cases:
     *
     * - Document version history.
     * - Draft recovery.
     * - Workflow state restoration.
     * - Configuration rollback.
     *
     * IMPORTANT:
     *
     * For persistent history in backend applications,
     * snapshots may need database storage, version identifiers,
     * authorization and retention policies.
     *
     * Memento itself does not provide persistence or
     * distributed transaction guarantees.
     *
     *
     * ============================================================
     * 15. MEMORY AND STORAGE CONSIDERATIONS
     * ============================================================
     *
     * Saving every state can consume significant memory.
     *
     * Example:
     *
     * A document contains 10 MB of data.
     *
     * Saving 100 complete snapshots may require substantial
     * storage, depending on the implementation.
     *
     * Possible optimizations:
     *
     * - Limit the number of stored snapshots.
     * - Store only changed data where appropriate.
     * - Use incremental snapshots.
     * - Compress snapshots.
     * - Persist older snapshots outside memory.
     *
     * IMPORTANT:
     *
     * Incremental snapshots and change-based history require
     * additional logic to reconstruct the complete state.
     *
     *
     * ============================================================
     * 16. ADVANTAGES
     * ============================================================
     *
     * 1. Preserves encapsulation of the Originator.
     *
     * 2. Supports undo and redo functionality.
     *
     * 3. Allows state restoration without exposing
     *    internal implementation details.
     *
     * 4. Separates snapshot management from the Originator.
     *
     * 5. Supports checkpoints and state history.
     *
     *
     * ============================================================
     * 17. DISADVANTAGES
     * ============================================================
     *
     * 1. Storing many snapshots can consume significant memory.
     *
     * 2. Deep snapshots can be expensive to create.
     *
     * 3. Snapshot management can become complex.
     *
     * 4. Mutable nested objects require careful copying.
     *
     * 5. Large objects may make full-state snapshots inefficient.
     *
     *
     * ============================================================
     * 18. WHEN SHOULD WE USE MEMENTO?
     * ============================================================
     *
     * Use Memento when:
     *
     * - You need to restore an object's previous state.
     * - You need undo and redo functionality.
     * - You need checkpoints or snapshots.
     * - You want to preserve encapsulation.
     * - The object should control how its state is saved.
     *
     * Avoid Memento when:
     *
     * - The state is very large and snapshots are too expensive.
     * - A simple value assignment is sufficient.
     * - There is no meaningful need for restoration.
     * - Snapshot management adds unnecessary complexity.
     *
     *
     * ============================================================
     * 19. COMMON MISCONCEPTIONS
     * ============================================================
     *
     * 1. Memento is the object whose state is being saved.
     *    FALSE. The Originator is the object whose state
     *    is captured.
     *
     * 2. Caretaker creates the snapshot.
     *    FALSE. The Originator creates the Memento.
     *
     * 3. Caretaker understands the internal state.
     *    FALSE. It stores and manages snapshots without
     *    inspecting their internal representation.
     *
     * 4. Memento automatically creates a deep copy.
     *    FALSE. The Originator must implement a suitable
     *    snapshot strategy.
     *
     * 5. Memento is the same as Prototype.
     *    FALSE. Prototype creates copies; Memento saves
     *    state for restoration.
     *
     * 6. Memento automatically provides undo and redo.
     *    FALSE. It provides the snapshot mechanism.
     *    History and undo/redo logic must also be implemented.
     *
     *
     * ============================================================
     * 20. INTERVIEW QUESTIONS
     * ============================================================
     *
     * Q1. What is Memento Design Pattern?
     *
     * A: A behavioral pattern that captures and restores
     *    an object's state without exposing its internals.
     *
     * Q2. What are the main participants?
     *
     * A: Originator, Memento and Caretaker.
     *
     * Q3. What is the responsibility of the Originator?
     *
     * A: It creates snapshots and restores its state from them.
     *
     * Q4. What is the responsibility of the Caretaker?
     *
     * A: It stores and manages Mementos.
     *
     * Q5. Why should Mementos generally be immutable?
     *
     * A: To prevent saved snapshots from being accidentally
     *    changed after creation.
     *
     * Q6. Does Memento guarantee deep copying?
     *
     * A: No. The Originator must correctly capture nested
     *    mutable state when necessary.
     *
     * Q7. What is the difference between Command and Memento?
     *
     * A: Command represents an operation; Memento represents
     *    a saved state.
     *
     * Q8. How can undo and redo be implemented?
     *
     * A: By maintaining history using stacks of saved states.
     *
     *
     * ============================================================
     * 21. QUICK REVISION
     * ============================================================
     *
     * PATTERN:
     * Memento
     *
     * CATEGORY:
     * Behavioral Design Pattern
     *
     * PURPOSE:
     * Capture and restore an object's previous state.
     *
     * MAIN IDEA:
     * Save snapshots without exposing internal representation.
     *
     * PARTICIPANTS:
     *
     * Originator
     * Memento
     * Caretaker
     *
     * COMMON USE CASES:
     *
     * Undo / Redo
     * Text Editors
     * Games
     * Configuration Rollback
     * Version History
     *
     * IMPORTANT CONCEPTS:
     *
     * Snapshot
     * Encapsulation
     * Shallow Copy
     * Deep Copy
     * Undo Stack
     * Redo Stack
     *
     * REMEMBER:
     *
     * Originator -> Creates and restores state.
     *
     * Memento -> Stores the snapshot.
     *
     * Caretaker -> Manages saved snapshots.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Memento Design Pattern , Happy Learning ..!");
    }
}
