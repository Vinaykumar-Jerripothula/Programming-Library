
public class Command {

    /*
     * ============================================================
     * 1. INTRODUCTION
     * ============================================================
     *
     * Imagine you are developing a remote-control application.
     *
     * The remote has different buttons:
     *
     *   - Turn ON the light.
     *   - Turn OFF the light.
     *   - Turn ON the fan.
     *   - Turn OFF the fan.
     *
     * One approach is to write separate logic inside every
     * button to perform its corresponding operation.
     *
     * But what happens when:
     *
     *   - You add more devices?
     *   - You add undo functionality?
     *   - You want to schedule operations?
     *   - You want to store operations in a history?
     *   - You want to execute operations later?
     *
     * The remote becomes tightly coupled to the devices.
     *
     * Instead, what if we represent each operation as an object?
     *
     * For example:
     *
     *   TurnOnLightCommand
     *   TurnOffLightCommand
     *   TurnOnFanCommand
     *   TurnOffFanCommand
     *
     * Each command object knows what operation to perform
     * and which object should perform it.
     *
     * The remote only needs to execute a Command.
     *
     * It does not need to know how the operation works.
     *
     * This is the Command Design Pattern.
     *
     *
     * DEFINITION:
     *
     * Command is a behavioral design pattern that turns a
     * request into a standalone object containing all the
     * information needed to perform that request.
     *
     *
     * SIMPLE DEFINITION:
     *
     * Represent an operation as an object so that it can be
     * executed, stored, passed, queued or undone independently.
     *
     *
     * ============================================================
     * 2. WHY DO WE NEED COMMAND?
     * ============================================================
     *
     * Consider this code:
     *
     * class RemoteControl {
     *
     *     private Light light;
     *
     *     public void pressButton() {
     *         light.turnOn();
     *     }
     * }
     *
     * The RemoteControl directly knows about Light.
     *
     * If we introduce a Fan, Television and AirConditioner,
     * the remote may need more device-specific logic.
     *
     * This increases coupling.
     *
     * Instead:
     *
     * RemoteControl -> Command interface
     *
     * TurnOnLightCommand -> Light
     *
     * TurnOnFanCommand -> Fan
     *
     * The remote works with the Command abstraction.
     *
     * Different command objects encapsulate different operations.
     *
     * The sender does not need to understand the receiver's
     * internal implementation.
     *
     *
     * ============================================================
     * 3. REAL-WORLD ANALOGY
     * ============================================================
     *
     * Imagine ordering food in a restaurant.
     *
     * You place an order with the waiter.
     *
     * The waiter does not personally cook the food.
     *
     * Instead:
     *
     *   1. Your order is recorded.
     *   2. The order is sent to the kitchen.
     *   3. The chef prepares the food.
     *
     * The order contains information about what needs to
     * be prepared.
     *
     * It can be passed from one person to another.
     *
     * In this analogy:
     *
     * Customer -> Client
     *
     * Waiter -> Invoker
     *
     * Order -> Command
     *
     * Chef -> Receiver
     *
     * The order represents an operation independently
     * of the person who requests it.
     *
     *
     * ============================================================
     * 4. STRUCTURE AND PARTICIPANTS
     * ============================================================
     *
     * Main participants:
     *
     * 1. Command:
     *
     *    Defines a common interface for executing an operation.
     *
     * 2. Concrete Command:
     *
     *    Implements the Command interface.
     *    Stores the receiver and the information required
     *    to execute the operation.
     *
     * 3. Receiver:
     *
     *    Knows how to perform the actual operation.
     *
     * 4. Invoker:
     *
     *    Stores or triggers a Command.
     *    It does not need to know how the operation works.
     *
     * 5. Client:
     *
     *    Creates Concrete Commands and connects them
     *    to their receivers and invokers.
     *
     *
     * BASIC STRUCTURE:
     *
     *              Client
     *                 |
     *                 v
     *          Concrete Command
     *                 |
     *                 v
     *             Receiver
     *
     *              Invoker
     *                 |
     *                 v
     *              Command
     *
     *
     * ============================================================
     * 5. COMPLETE JAVA IMPLEMENTATION
     * ============================================================
     *
     * We will implement a remote control for a light.
     *
     *
     * STEP 1: Create the Command interface.
     *
     * interface Command {
     *
     *     void execute();
     * }
     *
     * Every command must provide an execute() operation.
     *
     *
     * STEP 2: Create the Receiver.
     *
     * class Light {
     *
     *     public void turnOn() {
     *         System.out.println("Light is ON");
     *     }
     *
     *     public void turnOff() {
     *         System.out.println("Light is OFF");
     *     }
     * }
     *
     * The Light knows how to perform the actual operations.
     *
     *
     * STEP 3: Create Concrete Commands.
     *
     * class TurnOnLightCommand implements Command {
     *
     *     private final Light light;
     *
     *     public TurnOnLightCommand(Light light) {
     *         this.light = light;
     *     }
     *
     *     @Override
     *     public void execute() {
     *         light.turnOn();
     *     }
     * }
     *
     *
     * class TurnOffLightCommand implements Command {
     *
     *     private final Light light;
     *
     *     public TurnOffLightCommand(Light light) {
     *         this.light = light;
     *     }
     *
     *     @Override
     *     public void execute() {
     *         light.turnOff();
     *     }
     * }
     *
     * Each command encapsulates a particular operation.
     *
     * Notice:
     *
     * TurnOnLightCommand knows how to turn on the light.
     *
     * TurnOffLightCommand knows how to turn off the light.
     *
     *
     * STEP 4: Create the Invoker.
     *
     * class RemoteControl {
     *
     *     private Command command;
     *
     *     public void setCommand(Command command) {
     *         this.command = command;
     *     }
     *
     *     public void pressButton() {
     *
     *         if (command != null) {
     *             command.execute();
     *         }
     *     }
     * }
     *
     * The remote does not know about Light.
     *
     * It only knows about the Command interface.
     *
     *
     * STEP 5: Client code.
     *
     * Light light = new Light();
     *
     * Command turnOn = new TurnOnLightCommand(light);
     * Command turnOff = new TurnOffLightCommand(light);
     *
     * RemoteControl remote = new RemoteControl();
     *
     * remote.setCommand(turnOn);
     * remote.pressButton();
     *
     * remote.setCommand(turnOff);
     * remote.pressButton();
     *
     *
     * OUTPUT:
     *
     * Light is ON
     * Light is OFF
     *
     *
     * ============================================================
     * 6. UNDERSTANDING THE EXECUTION FLOW
     * ============================================================
     *
     * Consider:
     *
     * remote.setCommand(turnOn);
     *
     * STEP 1:
     *
     * Client creates the Light object.
     *
     *
     * STEP 2:
     *
     * Client creates TurnOnLightCommand and passes the
     * Light object to its constructor.
     *
     * The command now knows its receiver.
     *
     *
     * STEP 3:
     *
     * Client gives the command to RemoteControl.
     *
     * RemoteControl stores the command.
     *
     *
     * STEP 4:
     *
     * Client presses the button.
     *
     * remote.pressButton();
     *
     *
     * STEP 5:
     *
     * RemoteControl calls:
     *
     * command.execute();
     *
     *
     * STEP 6:
     *
     * TurnOnLightCommand.execute() calls:
     *
     * light.turnOn();
     *
     *
     * STEP 7:
     *
     * The Light performs the actual operation.
     *
     *
     * IMPORTANT:
     *
     * The remote triggers the operation.
     *
     * The command represents the operation.
     *
     * The receiver performs the operation.
     *
     *
     * ============================================================
     * 7. UNDERSTANDING THE DIFFERENCE BETWEEN
     *    CLIENT, INVOKER AND RECEIVER
     * ============================================================
     *
     * These three roles are frequently confused.
     *
     *
     * CLIENT:
     *
     * Creates and configures the command.
     *
     * Example:
     *
     * Command command = new TurnOnLightCommand(light);
     *
     *
     * INVOKER:
     *
     * Triggers the command.
     *
     * Example:
     *
     * remote.pressButton();
     *
     *
     * RECEIVER:
     *
     * Performs the actual operation.
     *
     * Example:
     *
     * light.turnOn();
     *
     *
     * REMEMBER:
     *
     * Client   -> Creates the command.
     *
     * Invoker  -> Executes the command.
     *
     * Command  -> Encapsulates the operation.
     *
     * Receiver -> Performs the operation.
     *
     *
     * ============================================================
     * 8. IMPLEMENTING UNDO FUNCTIONALITY
     * ============================================================
     *
     * One of the important benefits of Command is that
     * commands can support undo operations.
     *
     * For example:
     *
     * If a command turns on a light, its undo operation
     * can turn the light off.
     *
     * We can extend the interface:
     *
     * interface Command {
     *
     *     void execute();
     *
     *     void undo();
     * }
     *
     *
     * Concrete command:
     *
     * class TurnOnLightCommand implements Command {
     *
     *     private final Light light;
     *
     *     public TurnOnLightCommand(Light light) {
     *         this.light = light;
     *     }
     *
     *     @Override
     *     public void execute() {
     *         light.turnOn();
     *     }
     *
     *     @Override
     *     public void undo() {
     *         light.turnOff();
     *     }
     * }
     *
     *
     * Invoker:
     *
     * class RemoteControl {
     *
     *     private Command command;
     *
     *     public void setCommand(Command command) {
     *         this.command = command;
     *     }
     *
     *     public void pressButton() {
     *         if (command != null) {
     *             command.execute();
     *         }
     *     }
     *
     *     public void pressUndo() {
     *         if (command != null) {
     *             command.undo();
     *         }
     *     }
     * }
     *
     *
     * Client:
     *
     * Light light = new Light();
     *
     * Command command = new TurnOnLightCommand(light);
     *
     * RemoteControl remote = new RemoteControl();
     *
     * remote.setCommand(command);
     *
     * remote.pressButton();
     * remote.pressUndo();
     *
     *
     * OUTPUT:
     *
     * Light is ON
     * Light is OFF
     *
     *
     * IMPORTANT:
     *
     * This simple example assumes the undo operation is
     * the opposite of the execute operation.
     *
     * In real applications, undo may need to restore the
     * previous state rather than simply perform an opposite action.
     *
     *
     * ============================================================
     * 9. COMMAND HISTORY AND UNDO STACK
     * ============================================================
     *
     * What if the user executes multiple commands?
     *
     * Example:
     *
     * 1. Turn on light.
     * 2. Turn on fan.
     * 3. Turn off light.
     *
     * We may want to undo the most recently executed command.
     *
     * A stack is useful for this.
     *
     * Concept:
     *
     * Execute command -> Push onto history stack.
     *
     * Undo -> Pop the latest command and call undo().
     *
     * Example:
     *
     * import java.util.Stack;
     *
     * class CommandHistory {
     *
     *     private final Stack<Command> history = new Stack<>();
     *
     *     public void execute(Command command) {
     *
     *         command.execute();
     *         history.push(command);
     *     }
     *
     *     public void undo() {
     *
     *         if (!history.empty()) {
     *
     *             Command command = history.pop();
     *             command.undo();
     *         }
     *     }
     * }
     *
     * IMPORTANT:
     *
     * This is a simplified example.
     *
     * Real undo systems must consider:
     *
     * - Whether a command can be undone.
     * - Whether it has enough information to restore state.
     * - What happens when an operation partially fails.
     * - Whether undo itself can fail.
     * - How command history is persisted.
     *
     *
     * ============================================================
     * 10. IMPLEMENTING MACRO COMMANDS
     * ============================================================
     *
     * What if one button should execute multiple operations?
     *
     * Example:
     *
     * Press "Good Night":
     *
     *   - Turn off the light.
     *   - Turn off the fan.
     *   - Lock the door.
     *
     * Instead of making the remote understand all these
     * operations, create one command that contains other commands.
     *
     * This is called a Macro Command.
     *
     *
     * class MacroCommand implements Command {
     *
     *     private final List<Command> commands;
     *
     *     public MacroCommand(List<Command> commands) {
     *         this.commands = new ArrayList<>(commands);
     *     }
     *
     *     @Override
     *     public void execute() {
     *
     *         for (Command command : commands) {
     *             command.execute();
     *         }
     *     }
     * }
     *
     *
     * Example usage:
     *
     * Command goodNight = new MacroCommand(
     *     List.of(
     *         turnOffLight,
     *         turnOffFan,
     *         lockDoor
     *     )
     * );
     *
     * remote.setCommand(goodNight);
     * remote.pressButton();
     *
     * The macro executes all commands in sequence.
     *
     * NOTE:
     *
     * A Macro Command can also implement undo by undoing
     * its component commands in reverse order.
     *
     * That requires each component command to support
     * a correct undo operation.
     *
     *
     * ============================================================
     * 11. COMMAND VS STRATEGY
     * ============================================================
     *
     * Both patterns encapsulate behavior behind an interface.
     *
     * But their intentions are different.
     *
     *
     * STRATEGY:
     *
     * Encapsulates interchangeable algorithms.
     *
     * Example:
     *
     * Different payment calculation or sorting strategies.
     *
     * The client selects which algorithm to use.
     *
     *
     * COMMAND:
     *
     * Encapsulates a request or operation as an object.
     *
     * Example:
     *
     * A PlaceOrderCommand that performs an order operation.
     *
     * Commands can be queued, logged, stored or undone.
     *
     *
     * REMEMBER:
     *
     * Strategy -> Which algorithm should be used?
     *
     * Command  -> Which operation should be executed?
     *
     *
     * ============================================================
     * 12. COMMAND VS CHAIN OF RESPONSIBILITY
     * ============================================================
     *
     * COMMAND:
     *
     * Represents a request as an object.
     *
     * Example:
     *
     * DeleteFileCommand.
     *
     *
     * CHAIN OF RESPONSIBILITY:
     *
     * Passes a request through potential handlers.
     *
     * Example:
     *
     * A refund request passing through approval levels.
     *
     *
     * IMPORTANT:
     *
     * These patterns can work together.
     *
     * A Command object can be passed through a chain
     * of handlers for validation and processing.
     *
     *
     * ============================================================
     * 13. COMMAND VS OBSERVER
     * ============================================================
     *
     * COMMAND:
     *
     * Encapsulates an operation that can be triggered.
     *
     *
     * OBSERVER:
     *
     * Notifies multiple subscribers when an object's state changes.
     *
     *
     * Example:
     *
     * Command:
     * A user clicks a button to submit an order.
     *
     * Observer:
     * Multiple listeners are notified when an order is placed.
     *
     *
     * ============================================================
     * 14. COMMAND IN JAVA AND REAL APPLICATIONS
     * ============================================================
     *
     * Command-like designs appear in many applications.
     *
     *
     * A. GUI APPLICATIONS
     *
     * A button click can trigger a particular operation.
     *
     * Example:
     *
     * SaveCommand
     * CopyCommand
     * PasteCommand
     * UndoCommand
     *
     * The UI component triggers a command without needing
     * to understand all the business logic.
     *
     *
     * B. TASK QUEUES
     *
     * A task can be represented as a command object.
     *
     * A worker retrieves the task and executes it later.
     *
     * Examples:
     *
     * - SendEmailCommand
     * - GenerateReportCommand
     * - ProcessPaymentCommand
     *
     *
     * C. JOB SCHEDULERS
     *
     * An operation can be represented as a command and
     * scheduled for future execution.
     *
     *
     * D. TRANSACTION SYSTEMS
     *
     * Commands can represent operations that need auditing,
     * retry handling or compensation logic.
     *
     * IMPORTANT:
     *
     * A command object alone does not guarantee that an
     * operation is durable, transactional or safely retryable.
     *
     * Those guarantees require additional design.
     *
     *
     * ============================================================
     * 15. COMMAND IN SPRING / BACKEND DEVELOPMENT
     * ============================================================
     *
     * Consider an order-processing application.
     *
     * We can represent operations as:
     *
     * CreateOrderCommand
     * CancelOrderCommand
     * UpdateOrderCommand
     * RefundOrderCommand
     *
     * A handler or service can execute these commands.
     *
     * Example conceptual flow:
     *
     * Controller
     *    |
     *    v
     * CreateOrderCommand
     *    |
     *    v
     * Command Handler
     *    |
     *    v
     * Order Service / Repository
     *
     * This style is commonly used in CQRS architectures.
     *
     *
     * CQRS:
     *
     * Command Query Responsibility Segregation.
     *
     * It separates operations that change state
     * from operations that read state.
     *
     * COMMAND:
     *
     * Represents an intention to change application state.
     *
     * QUERY:
     *
     * Represents a request to retrieve information.
     *
     * IMPORTANT:
     *
     * Command Design Pattern and CQRS are related concepts,
     * but they are not the same thing.
     *
     * CQRS can use command objects and handlers, but the
     * GoF Command pattern does not require CQRS.
     *
     *
     * ============================================================
     * 16. COMMAND AND EVENT-DRIVEN ARCHITECTURE
     * ============================================================
     *
     * Do not confuse a Command with an Event.
     *
     * COMMAND:
     *
     * Represents a request to perform an operation.
     *
     * Example:
     *
     * PlaceOrderCommand
     *
     * It expresses an intention.
     *
     *
     * EVENT:
     *
     * Represents something that has already happened.
     *
     * Example:
     *
     * OrderPlacedEvent
     *
     * It records a fact.
     *
     *
     * REMEMBER:
     *
     * Command -> Please do this.
     *
     * Event   -> This has happened.
     *
     * A command may succeed and produce one or more events.
     *
     * A command may also fail and produce no success event.
     *
     *
     * ============================================================
     * 17. ADVANTAGES
     * ============================================================
     *
     * 1. Decouples the invoker from the receiver.
     *
     * 2. Encapsulates operations as independent objects.
     *
     * 3. Supports undo and redo functionality.
     *
     * 4. Supports command history and logging.
     *
     * 5. Commands can be queued and executed later.
     *
     * 6. Supports macro commands.
     *
     * 7. Makes it easier to introduce new operations.
     *
     *
     * ============================================================
     * 18. DISADVANTAGES
     * ============================================================
     *
     * 1. Can introduce many small command classes.
     *
     * 2. Adds extra abstraction and object creation.
     *
     * 3. Undo functionality can be difficult for complex operations.
     *
     * 4. Command history may consume memory.
     *
     * 5. Retry and failure handling need additional design.
     *
     *
     * ============================================================
     * 19. WHEN SHOULD WE USE COMMAND?
     * ============================================================
     *
     * Use Command when:
     *
     * - You want to decouple the sender from the receiver.
     * - You need to represent operations as objects.
     * - You need undo or redo functionality.
     * - You want to queue or schedule operations.
     * - You want command history or auditing.
     * - You need to execute multiple operations as one macro.
     *
     * Avoid Command when:
     *
     * - The operation is very simple.
     * - There is no benefit from representing it as an object.
     * - The additional command classes create unnecessary complexity.
     *
     *
     * ============================================================
     * 20. COMMON MISCONCEPTIONS
     * ============================================================
     *
     * 1. The Invoker performs the actual operation.
     *    FALSE. The Invoker triggers the command.
     *    The Receiver performs the actual operation.
     *
     * 2. A Command must always support undo.
     *    FALSE. Undo is optional.
     *
     * 3. Command and Strategy are identical.
     *    FALSE. Strategy encapsulates an algorithm;
     *    Command encapsulates a request.
     *
     * 4. Every Command needs a separate Receiver class.
     *    FALSE. Different commands can use the same receiver.
     *
     * 5. Command and CQRS are the same.
     *    FALSE. They are related but distinct concepts.
     *
     * 6. A command can always be safely retried.
     *    FALSE. Repeated execution may cause duplicate side effects.
     *    Idempotency and retry handling require additional design.
     *
     *
     * ============================================================
     * 21. INTERVIEW QUESTIONS
     * ============================================================
     *
     * Q1. What is Command Design Pattern?
     *
     * A: A behavioral pattern that encapsulates a request
     *    as an object.
     *
     * Q2. What are the main participants?
     *
     * A: Command, Concrete Command, Receiver, Invoker and Client.
     *
     * Q3. What is the responsibility of the Invoker?
     *
     * A: It triggers a command without knowing how the
     *    underlying operation is performed.
     *
     * Q4. What is the responsibility of the Receiver?
     *
     * A: It performs the actual operation.
     *
     * Q5. How does Command support undo?
     *
     * A: A command can store the information needed to reverse
     *    its operation or restore the previous state.
     *
     * Q6. What is a Macro Command?
     *
     * A: A command that executes multiple other commands.
     *
     * Q7. What is the difference between Command and Strategy?
     *
     * A: Strategy encapsulates interchangeable algorithms;
     *    Command encapsulates requests as objects.
     *
     * Q8. What is the difference between a Command and an Event?
     *
     * A: A Command expresses an intention to perform an action;
     *    an Event records something that has already happened.
     *
     * Q9. Is Command used in CQRS?
     *
     * A: Yes, command objects and handlers are commonly used
     *    in CQRS, but the two concepts are not identical.
     *
     *
     * ============================================================
     * 22. QUICK REVISION
     * ============================================================
     *
     * PATTERN:
     * Command
     *
     * CATEGORY:
     * Behavioral Design Pattern
     *
     * PURPOSE:
     * Encapsulate a request as an object.
     *
     * MAIN IDEA:
     * Represent an operation independently of the object
     * that triggers it.
     *
     * PARTICIPANTS:
     *
     * Command
     * Concrete Command
     * Receiver
     * Invoker
     * Client
     *
     * IMPORTANT FEATURES:
     *
     * Undo / Redo
     * Command History
     * Queueing
     * Scheduling
     * Macro Commands
     *
     * BACKEND APPLICATIONS:
     *
     * Task Queues
     * Job Scheduling
     * CQRS
     * Order Processing
     *
     * REMEMBER:
     *
     * Client   -> Creates and configures the command.
     *
     * Invoker  -> Triggers the command.
     *
     * Command  -> Encapsulates the operation.
     *
     * Receiver -> Performs the actual operation.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Command Design Pattern , Happy Learning ..!");
    }
}
