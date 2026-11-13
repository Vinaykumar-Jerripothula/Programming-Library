
public class State {

    /*
     * ============================================================
     * 1. INTRODUCTION
     * ============================================================
     *
     * Imagine an ATM machine.
     *
     * An ATM behaves differently depending on its current state.
     *
     * For example:
     *
     * STATE 1: No Card Inserted
     *
     * - The user can insert a card.
     * - The user cannot withdraw money.
     *
     *
     * STATE 2: Card Inserted
     *
     * - The user can enter a PIN.
     * - The user cannot withdraw money yet.
     *
     *
     * STATE 3: Authenticated
     *
     * - The user can withdraw money.
     * - The user can check the balance.
     *
     *
     * STATE 4: Transaction Completed
     *
     * - The ATM may return the card.
     * - The user can start another session.
     *
     *
     * Notice something important:
     *
     * The same ATM object behaves differently depending
     * on its current state.
     *
     * One approach is to write many if-else or switch statements
     * inside every operation.
     *
     * As the number of states and operations increases,
     * this logic becomes difficult to maintain.
     *
     * The State pattern provides a cleaner solution.
     *
     *
     * DEFINITION:
     *
     * State is a behavioral design pattern that allows an object
     * to change its behavior when its internal state changes.
     *
     * The object appears to change its class because
     * its behavior is delegated to a different state object.
     *
     *
     * SIMPLE DEFINITION:
     *
     * Move state-specific behavior into separate classes
     * and let the object delegate operations to its current state.
     *
     *
     * ============================================================
     * 2. WHY DO WE NEED STATE?
     * ============================================================
     *
     * Consider a simple MediaPlayer.
     *
     * It has three states:
     *
     * - Playing
     * - Paused
     * - Stopped
     *
     * Without the State pattern:
     *
     * class MediaPlayer {
     *
     *     private String state = "STOPPED";
     *
     *     public void play() {
     *
     *         if (state.equals("STOPPED")) {
     *
     *             System.out.println("Starting playback");
     *
     *         } else if (state.equals("PAUSED")) {
     *
     *             System.out.println("Resuming playback");
     *
     *         } else if (state.equals("PLAYING")) {
     *
     *             System.out.println("Already playing");
     *         }
     *
     *         state = "PLAYING";
     *     }
     * }
     *
     * This may work for a small example.
     *
     * But imagine adding:
     *
     * - Buffering
     * - Loading
     * - Error
     * - Completed
     * - Repeating
     *
     * Every operation may need more conditions:
     *
     * play()
     * pause()
     * stop()
     * next()
     * previous()
     * seek()
     *
     * The class becomes filled with conditional logic.
     *
     *
     * PROBLEMS:
     *
     * 1. Too many if-else or switch statements.
     *
     * 2. State-specific logic is mixed together.
     *
     * 3. Adding a new state may require modifying many methods.
     *
     * 4. Invalid transitions are difficult to manage.
     *
     * 5. The class becomes harder to test and maintain.
     *
     *
     * STATE PATTERN SOLUTION:
     *
     * Create a separate class for each state.
     *
     * Each state class implements a common State interface.
     *
     * The main object delegates operations to its current
     * state object.
     *
     *
     * ============================================================
     * 3. REAL-WORLD ANALOGY
     * ============================================================
     *
     * Think about a traffic signal.
     *
     * It has three states:
     *
     * RED:
     *
     * Vehicles must stop.
     *
     * GREEN:
     *
     * Vehicles can move.
     *
     * YELLOW:
     *
     * Vehicles should prepare to stop.
     *
     * The traffic signal behaves differently depending
     * on its current state.
     *
     * Instead of putting every state's behavior into
     * one large class, each state can define its own behavior.
     *
     *
     * ============================================================
     * 4. STRUCTURE AND PARTICIPANTS
     * ============================================================
     *
     * Main participants:
     *
     *
     * 1. Context:
     *
     *    The main object whose behavior changes.
     *
     *    It maintains a reference to the current State object.
     *
     *    It delegates operations to that state.
     *
     *
     * 2. State:
     *
     *    Defines a common interface for state-specific behavior.
     *
     *
     * 3. Concrete States:
     *
     *    Implement behavior for individual states.
     *
     *    Examples:
     *
     *    - PlayingState
     *    - PausedState
     *    - StoppedState
     *
     *
     * STRUCTURE:
     *
     *              Context
     *                 |
     *                 | currentState
     *                 v
     *              State
     *                 |
     *          ----------------
     *          |      |       |
     *          v      v       v
     *       StateA  StateB  StateC
     *
     *
     * The Context delegates operations to its current State.
     *
     *
     * ============================================================
     * 5. COMPLETE JAVA IMPLEMENTATION
     * ============================================================
     *
     * Example: Media Player.
     *
     *
     * STEP 1: Create the State interface.
     *
     * interface PlayerState {
     *
     *     void play(MediaPlayer player);
     *
     *     void pause(MediaPlayer player);
     *
     *     void stop(MediaPlayer player);
     * }
     *
     * This interface defines operations available in
     * every state.
     *
     *
     * STEP 2: Create the PlayingState.
     *
     * class PlayingState implements PlayerState {
     *
     *     @Override
     *     public void play(MediaPlayer player) {
     *
     *         System.out.println("Already playing");
     *     }
     *
     *     @Override
     *     public void pause(MediaPlayer player) {
     *
     *         System.out.println("Pausing playback");
     *
     *         player.setState(new PausedState());
     *     }
     *
     *     @Override
     *     public void stop(MediaPlayer player) {
     *
     *         System.out.println("Stopping playback");
     *
     *         player.setState(new StoppedState());
     *     }
     * }
     *
     *
     * STEP 3: Create the PausedState.
     *
     * class PausedState implements PlayerState {
     *
     *     @Override
     *     public void play(MediaPlayer player) {
     *
     *         System.out.println("Resuming playback");
     *
     *         player.setState(new PlayingState());
     *     }
     *
     *     @Override
     *     public void pause(MediaPlayer player) {
     *
     *         System.out.println("Already paused");
     *     }
     *
     *     @Override
     *     public void stop(MediaPlayer player) {
     *
     *         System.out.println("Stopping playback");
     *
     *         player.setState(new StoppedState());
     *     }
     * }
     *
     *
     * STEP 4: Create the StoppedState.
     *
     * class StoppedState implements PlayerState {
     *
     *     @Override
     *     public void play(MediaPlayer player) {
     *
     *         System.out.println("Starting playback");
     *
     *         player.setState(new PlayingState());
     *     }
     *
     *     @Override
     *     public void pause(MediaPlayer player) {
     *
     *         System.out.println("Cannot pause. Player is stopped");
     *     }
     *
     *     @Override
     *     public void stop(MediaPlayer player) {
     *
     *         System.out.println("Already stopped");
     *     }
     * }
     *
     *
     * STEP 5: Create the Context.
     *
     * class MediaPlayer {
     *
     *     private PlayerState currentState;
     *
     *     public MediaPlayer() {
     *
     *         currentState = new StoppedState();
     *     }
     *
     *     public void setState(PlayerState state) {
     *
     *         currentState = state;
     *     }
     *
     *     public void play() {
     *
     *         currentState.play(this);
     *     }
     *
     *     public void pause() {
     *
     *         currentState.pause(this);
     *     }
     *
     *     public void stop() {
     *
     *         currentState.stop(this);
     *     }
     * }
     *
     * MediaPlayer is the Context.
     *
     * It maintains the current state.
     *
     * It does not implement every state's behavior itself.
     *
     * Instead, it delegates operations to currentState.
     *
     *
     * STEP 6: Client code.
     *
     * MediaPlayer player = new MediaPlayer();
     *
     * player.play();
     * player.pause();
     * player.play();
     * player.stop();
     * player.pause();
     *
     *
     * OUTPUT:
     *
     * Starting playback
     * Pausing playback
     * Resuming playback
     * Stopping playback
     * Cannot pause. Player is stopped
     *
     *
     * ============================================================
     * 6. UNDERSTANDING THE EXECUTION FLOW
     * ============================================================
     *
     * Initially:
     *
     * currentState = StoppedState
     *
     *
     * STEP 1:
     *
     * player.play();
     *
     * MediaPlayer delegates:
     *
     * currentState.play(this);
     *
     * Since the current state is StoppedState,
     * StoppedState.play() executes.
     *
     * It changes the Context's state:
     *
     * player.setState(new PlayingState());
     *
     * Now:
     *
     * currentState = PlayingState
     *
     *
     * STEP 2:
     *
     * player.pause();
     *
     * The current state is PlayingState.
     *
     * PlayingState.pause() executes.
     *
     * It changes the state to PausedState.
     *
     *
     * STEP 3:
     *
     * player.play();
     *
     * PausedState.play() executes.
     *
     * It changes the state back to PlayingState.
     *
     *
     * STEP 4:
     *
     * player.stop();
     *
     * PlayingState.stop() executes.
     *
     * It changes the state to StoppedState.
     *
     *
     * STEP 5:
     *
     * player.pause();
     *
     * StoppedState.pause() executes.
     *
     * It reports that pausing is not allowed.
     *
     *
     * IMPORTANT:
     *
     * The same MediaPlayer object behaves differently
     * because its current State object changes.
     *
     *
     * ============================================================
     * 7. HOW DOES STATE TRANSITION HAPPEN?
     * ============================================================
     *
     * A state transition means moving from one state to another.
     *
     * In our example:
     *
     * StoppedState
     *      |
     *      | play()
     *      v
     * PlayingState
     *      |
     *      | pause()
     *      v
     * PausedState
     *      |
     *      | play()
     *      v
     * PlayingState
     *
     *
     * There are two common design choices.
     *
     *
     * A. STATE OBJECT CHANGES THE CONTEXT
     *
     * Each Concrete State receives the Context.
     *
     * It can call:
     *
     * context.setState(new AnotherState());
     *
     * This approach allows each state to control
     * its own transitions.
     *
     *
     * B. CONTEXT CONTROLS STATE TRANSITIONS
     *
     * The Context can inspect the current state
     * and decide which state should come next.
     *
     * This centralizes transition logic.
     *
     *
     * Neither approach is universally best.
     *
     * Choose based on the complexity and ownership
     * of the state-transition rules.
     *
     *
     * ============================================================
     * 8. STATE PATTERN VS IF-ELSE / SWITCH
     * ============================================================
     *
     * WITHOUT STATE PATTERN:
     *
     * class MediaPlayer {
     *
     *     private String state;
     *
     *     public void play() {
     *
     *         switch (state) {
     *
     *             case "STOPPED":
     *                 // Starting playback
     *                 break;
     *
     *             case "PAUSED":
     *                 // Resuming playback
     *                 break;
     *
     *             case "PLAYING":
     *                 // Already playing
     *                 break;
     *         }
     *     }
     * }
     *
     *
     * WITH STATE PATTERN:
     *
     * currentState.play(this);
     *
     * The current state object determines the behavior.
     *
     *
     * COMPARISON:
     *
     * IF-ELSE / SWITCH:
     *
     * - State-specific behavior is centralized.
     * - Simple for a small number of states.
     * - Can become difficult as conditions grow.
     *
     *
     * STATE PATTERN:
     *
     * - State-specific behavior is separated into classes.
     * - Reduces large conditional blocks.
     * - Makes individual states easier to maintain.
     * - Introduces additional classes and objects.
     *
     *
     * IMPORTANT:
     *
     * The State pattern is not automatically better
     * than switch statements.
     *
     * For a small, simple state machine,
     * an enum and switch may be clearer.
     *
     *
     * ============================================================
     * 9. STATE VS STRATEGY DESIGN PATTERN
     * ============================================================
     *
     * Both patterns use composition and polymorphism.
     *
     * Both can have a Context that delegates behavior
     * to an interchangeable interface implementation.
     *
     * But their intentions are different.
     *
     *
     * STRATEGY:
     *
     * Allows an algorithm or behavior to be selected
     * and exchanged.
     *
     * Example:
     *
     * Payment processing:
     *
     * - CreditCardPayment
     * - UpiPayment
     * - NetBankingPayment
     *
     * The client selects a payment strategy.
     *
     *
     * STATE:
     *
     * Changes behavior according to the object's
     * current internal state.
     *
     * Example:
     *
     * Media player:
     *
     * - Playing
     * - Paused
     * - Stopped
     *
     * The current state controls valid behavior
     * and may trigger transitions.
     *
     *
     * COMPARISON:
     *
     * STRATEGY:
     *
     * Focuses on interchangeable algorithms.
     *
     * STATE:
     *
     * Focuses on state-dependent behavior and transitions.
     *
     *
     * IMPORTANT:
     *
     * Their structures can look very similar.
     * Their intent and how they are used distinguish them.
     *
     *
     * ============================================================
     * 10. STATE VS COMMAND
     * ============================================================
     *
     * COMMAND:
     *
     * Encapsulates a request or operation as an object.
     *
     * Example:
     *
     * PlayCommand
     * PauseCommand
     * StopCommand
     *
     *
     * STATE:
     *
     * Encapsulates behavior associated with the current state.
     *
     * Example:
     *
     * PlayingState
     * PausedState
     * StoppedState
     *
     *
     * REMEMBER:
     *
     * Command -> Represents what action should be performed.
     *
     * State -> Represents how an object behaves in its current state.
     *
     *
     * ============================================================
     * 11. STATE PATTERN IN JAVA
     * ============================================================
     *
     * Java does not provide one dedicated standard
     * State design-pattern class.
     *
     * It can be implemented using:
     *
     * - Interfaces.
     * - Abstract classes.
     * - Composition.
     * - Polymorphism.
     * - Enums for simpler state models.
     *
     *
     * ENUM-BASED STATE:
     *
     * enum OrderStatus {
     *
     *     CREATED,
     *     PAID,
     *     SHIPPED,
     *     DELIVERED,
     *     CANCELLED
     * }
     *
     * An enum can represent a finite set of states.
     *
     * However, simply declaring an enum is not the full
     * State pattern.
     *
     * A State-pattern implementation typically encapsulates
     * state-specific behavior behind a common abstraction.
     *
     *
     * ============================================================
     * 12. STATE PATTERN IN BACKEND DEVELOPMENT
     * ============================================================
     *
     * Consider an order management application.
     *
     * An order may move through these states:
     *
     * CREATED
     *    |
     *    v
     * PAID
     *    |
     *    v
     * SHIPPED
     *    |
     *    v
     * DELIVERED
     *
     *
     * An order can also be cancelled when allowed.
     *
     * Different operations are valid in different states.
     *
     *
     * EXAMPLE:
     *
     * CREATED:
     *
     * - Payment can be initiated.
     * - Order can be cancelled.
     *
     *
     * PAID:
     *
     * - Shipping can be initiated.
     * - Refund may be requested.
     *
     *
     * SHIPPED:
     *
     * - Delivery tracking is available.
     * - Cancellation may require a different process.
     *
     *
     * DELIVERED:
     *
     * - Return or replacement may be available.
     *
     *
     * A State-based design can encapsulate these
     * state-specific rules.
     *
     *
     * SPRING BOOT APPLICATION:
     *
     * Possible classes:
     *
     * OrderState
     * CreatedState
     * PaidState
     * ShippedState
     * DeliveredState
     * Order
     *
     * The Order object delegates operations to its
     * current state.
     *
     *
     * IMPORTANT BACKEND CONSIDERATIONS:
     *
     * 1. Persistence:
     *
     *    If order state is stored in a database,
     *    the state must be restored when the entity is loaded.
     *
     *
     * 2. Concurrency:
     *
     *    Two requests may try to transition the same order
     *    at the same time.
     *
     *    Use suitable transaction and concurrency controls,
     *    such as optimistic locking when appropriate.
     *
     *
     * 3. Validation:
     *
     *    Do not allow invalid transitions merely because
     *    a controller calls a method.
     *
     *
     * 4. Distributed workflows:
     *
     *    A multi-service business process may need a
     *    workflow or saga design rather than only
     *    in-memory State objects.
     *
     *
     * ============================================================
     * 13. STATE PATTERN VS STATE MACHINE
     * ============================================================
     *
     * These concepts are related but are not identical.
     *
     *
     * STATE MACHINE:
     *
     * A model describing:
     *
     * - Possible states.
     * - Events or conditions.
     * - Transitions between states.
     *
     *
     * STATE PATTERN:
     *
     * An object-oriented design pattern that encapsulates
     * state-specific behavior in separate objects.
     *
     *
     * REMEMBER:
     *
     * State machine -> Describes states and transitions.
     *
     * State pattern -> Organizes state-dependent behavior
     *                  using objects and polymorphism.
     *
     * A state machine can be implemented using enums,
     * tables, switch statements, State objects or other methods.
     *
     *
     * ============================================================
     * 14. ADVANTAGES
     * ============================================================
     *
     * 1. Separates behavior for individual states.
     *
     * 2. Reduces large conditional statements.
     *
     * 3. Makes state-specific logic easier to maintain.
     *
     * 4. Supports adding new states with less modification
     *    to existing state classes.
     *
     * 5. Keeps state transition behavior organized.
     *
     * 6. Uses polymorphism and composition effectively.
     *
     *
     * ============================================================
     * 15. DISADVANTAGES
     * ============================================================
     *
     * 1. Introduces multiple classes.
     *
     * 2. Can be excessive for simple state logic.
     *
     * 3. State transitions can become difficult to follow
     *    if responsibilities are spread across many classes.
     *
     * 4. Shared data and transition rules need careful design.
     *
     * 5. Managing persistent and concurrent state transitions
     *    requires additional application-level logic.
     *
     *
     * ============================================================
     * 16. WHEN SHOULD WE USE STATE?
     * ============================================================
     *
     * Use State when:
     *
     * - An object has multiple meaningful states.
     * - Its behavior changes according to its current state.
     * - Many operations contain state-dependent conditions.
     * - State transitions are part of the object's behavior.
     * - You want to separate state-specific logic.
     *
     *
     * Avoid State when:
     *
     * - There are only one or two simple states.
     * - A small enum and switch are easier to understand.
     * - State-specific behavior is almost identical.
     * - Creating many classes adds unnecessary complexity.
     *
     *
     * ============================================================
     * 17. COMMON MISCONCEPTIONS
     * ============================================================
     *
     * 1. State and Strategy are exactly the same.
     *
     *    FALSE.
     *
     *    Their structures may be similar, but their intentions
     *    are different.
     *
     *
     * 2. State pattern eliminates every conditional statement.
     *
     *    FALSE.
     *
     *    It organizes state-dependent behavior.
     *    Other conditions may still be necessary.
     *
     *
     * 3. Context must always control transitions.
     *
     *    FALSE.
     *
     *    Concrete states can also initiate transitions.
     *
     *
     * 4. Every enum-based state implementation is State pattern.
     *
     *    FALSE.
     *
     *    The key idea is encapsulating state-specific behavior.
     *
     *
     * 5. State pattern automatically handles database persistence.
     *
     *    FALSE.
     *
     *    Persistence and concurrency are separate concerns.
     *
     *
     * ============================================================
     * 18. INTERVIEW QUESTIONS
     * ============================================================
     *
     * Q1. What is State Design Pattern?
     *
     * A: A behavioral pattern that allows an object to change
     *    its behavior when its internal state changes.
     *
     *
     * Q2. What are the main participants?
     *
     * A: Context, State and Concrete States.
     *
     *
     * Q3. What is the responsibility of Context?
     *
     * A: It maintains the current state and delegates
     *    operations to it.
     *
     *
     * Q4. How does State reduce conditional logic?
     *
     * A: It moves state-specific behavior into separate
     *    classes implementing a common State interface.
     *
     *
     * Q5. What is the difference between State and Strategy?
     *
     * A: Strategy encapsulates interchangeable algorithms.
     *    State encapsulates behavior that depends on
     *    an object's current state.
     *
     *
     * Q6. Can a Concrete State change the Context's state?
     *
     * A: Yes. It can request a transition through the Context.
     *
     *
     * Q7. What is the difference between State pattern
     *     and a state machine?
     *
     * A: A state machine models states and transitions.
     *    State pattern organizes state-specific behavior
     *    using objects.
     *
     *
     * Q8. Give a real-world application.
     *
     * A: Order management, media players, ATM systems,
     *    workflow engines and vending machines.
     *
     *
     * ============================================================
     * 19. QUICK REVISION
     * ============================================================
     *
     * PATTERN:
     * State
     *
     * CATEGORY:
     * Behavioral Design Pattern
     *
     * PURPOSE:
     * Change object behavior according to its current state.
     *
     * MAIN IDEA:
     * Encapsulate state-specific behavior in separate classes.
     *
     * PARTICIPANTS:
     *
     * Context
     * State
     * Concrete States
     *
     * COMMON USE CASES:
     *
     * Media Players
     * ATM Systems
     * Order Management
     * Workflow Systems
     * Vending Machines
     *
     * IMPORTANT CONCEPTS:
     *
     * Current State
     * State Transition
     * Polymorphism
     * Composition
     * State Machine
     *
     * REMEMBER:
     *
     * Context -> Maintains the current state.
     *
     * State -> Defines state-specific behavior.
     *
     * Concrete State -> Implements behavior and may
     *                   initiate transitions.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about State Design Pattern , Happy Learning ..!");
    }
}
