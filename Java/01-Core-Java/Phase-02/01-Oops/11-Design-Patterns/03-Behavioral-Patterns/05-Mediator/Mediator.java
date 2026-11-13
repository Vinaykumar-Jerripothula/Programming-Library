
public class Mediator {

    /*
     * ============================================================
     * 1. INTRODUCTION
     * ============================================================
     *
     * Imagine you are developing a group chat application.
     *
     * There are 100 users in a chat room.
     *
     * When one user sends a message, all other users should
     * receive that message.
     *
     * One approach is to make every user communicate directly
     * with every other user.
     *
     * But consider what happens when the number of users grows.
     *
     * Every user needs to know about many other users.
     *
     * This creates complex dependencies between objects.
     *
     * Instead, what if all users communicate through
     * a central ChatRoom object?
     *
     * User A -> ChatRoom -> User B
     *
     * User B -> ChatRoom -> User C
     *
     * User C -> ChatRoom -> User A
     *
     * Users do not need to communicate directly.
     *
     * The ChatRoom coordinates their communication.
     *
     * This is the Mediator Design Pattern.
     *
     *
     * DEFINITION:
     *
     * Mediator is a behavioral design pattern that defines
     * an object responsible for coordinating communication
     * between a group of other objects.
     *
     *
     * SIMPLE DEFINITION:
     *
     * Instead of objects communicating directly with each other,
     * they communicate through a central mediator.
     *
     *
     * ============================================================
     * 2. WHY DO WE NEED MEDIATOR?
     * ============================================================
     *
     * Consider three objects:
     *
     * A, B and C.
     *
     * Without Mediator:
     *
     * A communicates with B and C.
     * B communicates with A and C.
     * C communicates with A and B.
     *
     * Every object needs knowledge of the other objects.
     *
     * As the number of objects increases, dependencies
     * become difficult to manage.
     *
     *
     * WITH MEDIATOR:
     *
     * A -> Mediator
     * B -> Mediator
     * C -> Mediator
     *
     * The mediator coordinates communication.
     *
     * Objects no longer need direct references to all
     * the other participating objects.
     *
     *
     * ============================================================
     * 3. REAL-WORLD ANALOGY
     * ============================================================
     *
     * Imagine an airport.
     *
     * Multiple aircraft want to take off and land.
     *
     * Should every aircraft communicate directly with
     * every other aircraft?
     *
     * That would make coordination difficult.
     *
     * Instead, aircraft communicate with Air Traffic Control.
     *
     * Air Traffic Control coordinates:
     *
     *   - Landing permissions.
     *   - Takeoff permissions.
     *   - Runway usage.
     *   - Aircraft movement.
     *
     * Aircraft do not need to coordinate directly
     * with every other aircraft.
     *
     * In this analogy:
     *
     * Aircraft -> Colleague objects
     *
     * Air Traffic Control -> Mediator
     *
     * Communication -> Mediator coordination
     *
     *
     * ============================================================
     * 4. STRUCTURE AND PARTICIPANTS
     * ============================================================
     *
     * Main participants:
     *
     * 1. Mediator:
     *
     *    Defines an interface for communication between
     *    participating objects.
     *
     * 2. Concrete Mediator:
     *
     *    Implements the coordination logic.
     *
     * 3. Colleague:
     *
     *    Defines a common interface or base type for objects
     *    that communicate through the mediator.
     *
     * 4. Concrete Colleague:
     *
     *    Represents an individual object that communicates
     *    with other colleagues through the mediator.
     *
     *
     * BASIC STRUCTURE:
     *
     *              Colleague A
     *                   |
     *                   v
     *                Mediator
     *                   |
     *          -------------------
     *          |                 |
     *          v                 v
     *      Colleague B       Colleague C
     *
     *
     * ============================================================
     * 5. COMPLETE JAVA IMPLEMENTATION
     * ============================================================
     *
     * We will implement a group chat application.
     *
     *
     * STEP 1: Create the Mediator interface.
     *
     * interface ChatMediator {
     *
     *     void sendMessage(String message, User sender);
     *
     *     void addUser(User user);
     * }
     *
     * This interface defines how users communicate
     * through the mediator.
     *
     *
     * STEP 2: Create the Concrete Mediator.
     *
     * import java.util.ArrayList;
     * import java.util.List;
     *
     * class ChatRoom implements ChatMediator {
     *
     *     private final List<User> users = new ArrayList<>();
     *
     *     @Override
     *     public void addUser(User user) {
     *
     *         users.add(user);
     *     }
     *
     *     @Override
     *     public void sendMessage(
     *         String message,
     *         User sender
     *     ) {
     *
     *         for (User user : users) {
     *
     *             if (user != sender) {
     *
     *                 user.receive(message, sender);
     *             }
     *         }
     *     }
     * }
     *
     *
     * STEP 3: Create the Colleague.
     *
     * abstract class User {
     *
     *     protected final ChatMediator mediator;
     *
     *     protected final String name;
     *
     *     public User(ChatMediator mediator, String name) {
     *
     *         this.mediator = mediator;
     *         this.name = name;
     *     }
     *
     *     public abstract void send(String message);
     *
     *     public abstract void receive(
     *         String message,
     *         User sender
     *     );
     * }
     *
     *
     * STEP 4: Create the Concrete Colleague.
     *
     * class ChatUser extends User {
     *
     *     public ChatUser(ChatMediator mediator, String name) {
     *
     *         super(mediator, name);
     *     }
     *
     *     @Override
     *     public void send(String message) {
     *
     *         System.out.println(
     *             name + " sends: " + message
     *         );
     *
     *         mediator.sendMessage(message, this);
     *     }
     *
     *     @Override
     *     public void receive(String message, User sender) {
     *
     *         System.out.println(
     *             name + " receives from " +
     *             sender.name + ": " + message
     *         );
     *     }
     * }
     *
     *
     * STEP 5: Client code.
     *
     * ChatMediator chatRoom = new ChatRoom();
     *
     * User user1 = new ChatUser(chatRoom, "Vinay");
     * User user2 = new ChatUser(chatRoom, "Rahul");
     * User user3 = new ChatUser(chatRoom, "Kiran");
     *
     * chatRoom.addUser(user1);
     * chatRoom.addUser(user2);
     * chatRoom.addUser(user3);
     *
     * user1.send("Hello everyone!");
     *
     *
     * OUTPUT:
     *
     * Vinay sends: Hello everyone!
     * Rahul receives from Vinay: Hello everyone!
     * Kiran receives from Vinay: Hello everyone!
     *
     *
     * ============================================================
     * 6. UNDERSTANDING THE EXECUTION FLOW
     * ============================================================
     *
     * Consider:
     *
     * user1.send("Hello everyone!");
     *
     * STEP 1:
     *
     * Vinay calls send().
     *
     *
     * STEP 2:
     *
     * ChatUser sends the message to the mediator:
     *
     * mediator.sendMessage(message, this);
     *
     *
     * STEP 3:
     *
     * ChatRoom receives the message.
     *
     *
     * STEP 4:
     *
     * ChatRoom iterates through its registered users.
     *
     *
     * STEP 5:
     *
     * It checks whether each user is the sender.
     *
     * The sender does not receive their own message.
     *
     *
     * STEP 6:
     *
     * The mediator forwards the message to the remaining users.
     *
     *
     * STEP 7:
     *
     * Each receiving user displays the message.
     *
     *
     * IMPORTANT:
     *
     * User objects do not directly communicate with each other.
     *
     * ChatRoom coordinates the communication.
     *
     *
     * ============================================================
     * 7. UNDERSTANDING COLLEAGUE OBJECTS
     * ============================================================
     *
     * In this pattern, participating objects are called
     * Colleagues.
     *
     * They are not necessarily Java's java.util.concurrent
     * thread-related concepts.
     *
     * Colleagues are simply objects whose interactions
     * are coordinated by a Mediator.
     *
     * Examples:
     *
     * - Chat users.
     * - UI components.
     * - Airplanes.
     * - Workflow components.
     * - Application modules.
     *
     * Each colleague knows the mediator.
     *
     * It does not need to know every other colleague.
     *
     *
     * ============================================================
     * 8. MEDIATOR VS CHAIN OF RESPONSIBILITY
     * ============================================================
     *
     * Both patterns coordinate the flow of requests.
     *
     * But their intentions are different.
     *
     *
     * CHAIN OF RESPONSIBILITY:
     *
     * Passes a request through a sequence of potential handlers.
     *
     * Example:
     *
     * Team Leader -> Manager -> Director.
     *
     * A handler processes the request or forwards it.
     *
     *
     * MEDIATOR:
     *
     * Coordinates communication between multiple objects.
     *
     * Example:
     *
     * Multiple chat users communicate through ChatRoom.
     *
     *
     * REMEMBER:
     *
     * Chain of Responsibility -> Request moves through handlers.
     *
     * Mediator -> Objects communicate through a coordinator.
     *
     *
     * ============================================================
     * 9. MEDIATOR VS OBSERVER
     * ============================================================
     *
     * Both patterns help objects communicate indirectly.
     *
     * But their primary intentions differ.
     *
     *
     * OBSERVER:
     *
     * One subject notifies multiple observers when its
     * state changes or an event occurs.
     *
     * Example:
     *
     * A stock price update notifies subscribed users.
     *
     *
     * MEDIATOR:
     *
     * Coordinates interactions between multiple objects.
     *
     * Example:
     *
     * A chat room coordinates communication between users.
     *
     *
     * IMPORTANT:
     *
     * A Mediator may internally use Observer-style
     * notifications.
     *
     * But the two patterns solve different coordination problems.
     *
     *
     * ============================================================
     * 10. MEDIATOR VS FACADE
     * ============================================================
     *
     * Both patterns introduce an intermediary object.
     *
     * But their intentions differ.
     *
     *
     * FACADE:
     *
     * Provides a simplified interface to a complex subsystem.
     *
     * Example:
     *
     * An OrderFacade coordinates inventory, payment and shipping.
     *
     *
     * MEDIATOR:
     *
     * Coordinates communication between participating objects.
     *
     * Example:
     *
     * A ChatRoom coordinates communication between users.
     *
     *
     * REMEMBER:
     *
     * Facade -> Simplifies subsystem usage.
     *
     * Mediator -> Centralizes object interactions.
     *
     *
     * ============================================================
     * 11. MEDIATOR VS DIRECT COMMUNICATION
     * ============================================================
     *
     * WITHOUT MEDIATOR:
     *
     * A -> B
     * A -> C
     * B -> A
     * B -> C
     * C -> A
     * C -> B
     *
     * Objects have multiple direct dependencies.
     *
     *
     * WITH MEDIATOR:
     *
     * A -> Mediator
     * B -> Mediator
     * C -> Mediator
     *
     * The mediator manages the communication.
     *
     *
     * IMPORTANT:
     *
     * Mediator reduces direct dependencies between colleagues.
     *
     * However, the mediator itself may become complex
     * if too much coordination logic is placed inside it.
     *
     *
     * ============================================================
     * 12. MEDIATOR IN GUI APPLICATIONS
     * ============================================================
     *
     * Mediator is particularly useful in applications
     * containing multiple UI components.
     *
     * Consider a registration form:
     *
     * - Username field.
     * - Password field.
     * - Confirm password field.
     * - Submit button.
     *
     * When the user enters information:
     *
     * - Password field may affect the confirm-password field.
     * - Form validation may affect the submit button.
     * - Checkbox selection may affect other components.
     *
     * Without coordination, UI components may become
     * tightly coupled.
     *
     * A FormMediator can coordinate these interactions.
     *
     * Example:
     *
     * UsernameField ----\
     * PasswordField -----\
     * ConfirmPassword ----> FormMediator
     * SubmitButton -------/
     *
     * The mediator decides which component should react
     * to a particular event.
     *
     *
     * ============================================================
     * 13. MEDIATOR IN BACKEND DEVELOPMENT
     * ============================================================
     *
     * Consider an order-processing system.
     *
     * Different components are involved:
     *
     * - Order Service.
     * - Inventory Service.
     * - Payment Service.
     * - Shipping Service.
     * - Notification Service.
     *
     * A coordinator can manage interactions between
     * these components.
     *
     * Example:
     *
     * OrderCoordinator
     *       |
     *       |----> Inventory
     *       |
     *       |----> Payment
     *       |
     *       |----> Shipping
     *       |
     *       |----> Notification
     *
     * This can reduce direct dependencies between components.
     *
     * IMPORTANT:
     *
     * A service coordinating an application workflow may
     * resemble the Mediator pattern, but not every service
     * orchestration class is automatically a GoF Mediator.
     *
     * In distributed systems, communication failures,
     * retries, transactions and consistency also need
     * separate consideration.
     *
     *
     * ============================================================
     * 14. MEDIATOR AND MICROSERVICES
     * ============================================================
     *
     * In distributed systems, a central coordinator can
     * coordinate a multi-step business workflow.
     *
     * For example:
     *
     * Order Service
     *       |
     *       v
     * Order Coordinator
     *       |
     *       |----> Inventory Service
     *       |
     *       |----> Payment Service
     *       |
     *       |----> Shipping Service
     *
     * A related architectural concept is orchestration.
     *
     * ORCHESTRATION:
     *
     * A central component controls the sequence of operations.
     *
     * CHOREOGRAPHY:
     *
     * Components react to events without a central
     * component controlling every step.
     *
     * These are distributed-system architecture concepts,
     * not simply alternative names for the GoF Mediator pattern.
     *
     *
     * ============================================================
     * 15. ADVANTAGES
     * ============================================================
     *
     * 1. Reduces direct dependencies between objects.
     *
     * 2. Centralizes communication logic.
     *
     * 3. Makes colleagues easier to reuse independently.
     *
     * 4. Makes interaction rules easier to modify in one place.
     *
     * 5. Reduces complex many-to-many relationships.
     *
     *
     * ============================================================
     * 16. DISADVANTAGES
     * ============================================================
     *
     * 1. The mediator can become a large, complex class.
     *
     * 2. Too much responsibility may accumulate in one object.
     *
     * 3. Debugging communication can become difficult.
     *
     * 4. The mediator can become a bottleneck.
     *
     * 5. Changes to coordination logic may affect many colleagues.
     *
     *
     * ============================================================
     * 17. WHEN SHOULD WE USE MEDIATOR?
     * ============================================================
     *
     * Use Mediator when:
     *
     * - Many objects communicate with one another.
     * - Direct dependencies are becoming difficult to manage.
     * - Communication rules should be centralized.
     * - Objects should remain relatively independent.
     * - A complex UI or workflow needs coordination.
     *
     * Avoid Mediator when:
     *
     * - Only a few objects communicate.
     * - Direct communication is simple and clear.
     * - The mediator would become unnecessarily complicated.
     *
     *
     * ============================================================
     * 18. COMMON MISCONCEPTIONS
     * ============================================================
     *
     * 1. Mediator and Facade are identical.
     *    FALSE. Facade simplifies a subsystem; Mediator
     *    coordinates interactions between objects.
     *
     * 2. Mediator eliminates all dependencies.
     *    FALSE. Colleagues still depend on the mediator abstraction.
     *
     * 3. Mediator must always be a single global object.
     *    FALSE. Its scope depends on the application design.
     *
     * 4. Mediator is only useful in chat applications.
     *    FALSE. It is also useful in GUI systems and workflows.
     *
     * 5. Mediator always improves architecture.
     *    FALSE. A poorly designed mediator can become a
     *    complex central object.
     *
     * 6. Mediator and Observer are the same.
     *    FALSE. Their primary intentions are different,
     *    although they can be used together.
     *
     *
     * ============================================================
     * 19. INTERVIEW QUESTIONS
     * ============================================================
     *
     * Q1. What is Mediator Design Pattern?
     *
     * A: A behavioral pattern that centralizes communication
     *    between a group of objects.
     *
     * Q2. What are its main participants?
     *
     * A: Mediator, Concrete Mediator, Colleague and
     *    Concrete Colleague.
     *
     * Q3. What is a Colleague?
     *
     * A: An object that communicates with other participating
     *    objects through the mediator.
     *
     * Q4. What problem does Mediator solve?
     *
     * A: It reduces complex direct dependencies between objects.
     *
     * Q5. What is the difference between Mediator and Facade?
     *
     * A: Facade simplifies access to a subsystem;
     *    Mediator coordinates interactions between objects.
     *
     * Q6. What is the difference between Mediator and Observer?
     *
     * A: Observer notifies subscribers about events or state
     *    changes; Mediator coordinates object interactions.
     *
     * Q7. What is the biggest disadvantage of Mediator?
     *
     * A: The mediator can become overly complex and accumulate
     *    too much responsibility.
     *
     * Q8. Give a real-world software example.
     *
     * A: A chat room coordinating messages between users
     *    or a form mediator coordinating UI components.
     *
     *
     * ============================================================
     * 20. QUICK REVISION
     * ============================================================
     *
     * PATTERN:
     * Mediator
     *
     * CATEGORY:
     * Behavioral Design Pattern
     *
     * PURPOSE:
     * Coordinate communication between multiple objects.
     *
     * MAIN IDEA:
     * Objects communicate through a central coordinator
     * instead of depending directly on one another.
     *
     * PARTICIPANTS:
     *
     * Mediator
     * Concrete Mediator
     * Colleague
     * Concrete Colleague
     *
     * EXAMPLES:
     *
     * Chat Room
     * Air Traffic Control
     * GUI Form Coordination
     * Workflow Coordination
     *
     * RELATED CONCEPTS:
     *
     * Orchestration
     * Choreography
     *
     * REMEMBER:
     *
     * Mediator -> Centralizes communication.
     *
     * Facade -> Simplifies subsystem access.
     *
     * Observer -> Notifies subscribers.
     *
     * Chain of Responsibility -> Passes requests through handlers.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Mediator Design Pattern , Happy Learning ..!");
    }
}
