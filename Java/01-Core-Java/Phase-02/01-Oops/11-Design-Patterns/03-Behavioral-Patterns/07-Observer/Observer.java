
public class Observer {

    /*
     * ============================================================
     * 1. INTRODUCTION
     * ============================================================
     *
     * Imagine you subscribe to a YouTube channel.
     *
     * Whenever the channel uploads a new video,
     * you receive a notification.
     *
     * The channel does not need to know how every subscriber
     * handles that notification.
     *
     * It only needs to notify its subscribers when something
     * important happens.
     *
     * Now imagine implementing this in Java.
     *
     * We have:
     *
     * - A YouTube channel.
     * - Multiple subscribers.
     *
     * Whenever a new video is uploaded, all subscribers
     * should automatically receive an update.
     *
     * The Observer pattern solves this problem.
     *
     *
     * DEFINITION:
     *
     * Observer is a behavioral design pattern that defines
     * a one-to-many dependency between objects so that when
     * one object changes its state, all its dependent objects
     * are automatically notified and updated.
     *
     *
     * SIMPLE DEFINITION:
     *
     * One object changes -> It notifies multiple subscribed objects.
     *
     *
     * ============================================================
     * 2. WHY DO WE NEED OBSERVER?
     * ============================================================
     *
     * Consider a news application.
     *
     * A NewsChannel publishes news.
     *
     * Multiple users want to receive the latest news.
     *
     * Without Observer, we might write:
     *
     * class NewsChannel {
     *
     *     private User user1;
     *     private User user2;
     *     private User user3;
     *
     *     public void publishNews(String news) {
     *
     *         user1.receive(news);
     *         user2.receive(news);
     *         user3.receive(news);
     *     }
     * }
     *
     * Problems:
     *
     * 1. The NewsChannel is tightly coupled to User.
     *
     * 2. Adding a new user requires changing NewsChannel.
     *
     * 3. Removing a user also requires changing NewsChannel.
     *
     * 4. The channel must know every receiver.
     *
     * 5. It becomes difficult to maintain as subscribers grow.
     *
     *
     * OBSERVER SOLUTION:
     *
     * Define a common Observer interface.
     *
     * Every subscriber implements that interface.
     *
     * The NewsChannel maintains a collection of observers.
     *
     * When news is published, it notifies all registered
     * observers through the common interface.
     *
     * Now the channel does not depend on specific user classes.
     *
     *
     * ============================================================
     * 3. REAL-WORLD ANALOGY
     * ============================================================
     *
     * Think about a newspaper subscription.
     *
     * 1. You subscribe to a newspaper.
     *
     * 2. The newspaper maintains a list of subscribers.
     *
     * 3. A new edition is published.
     *
     * 4. The newspaper sends the edition to its subscribers.
     *
     * 5. A subscriber can unsubscribe at any time.
     *
     * This is the basic idea behind Observer.
     *
     *
     * ============================================================
     * 4. STRUCTURE AND PARTICIPANTS
     * ============================================================
     *
     * Main participants:
     *
     * 1. Subject / Publisher:
     *
     *    Maintains a collection of observers.
     *
     *    Provides methods to:
     *
     *    - Register observers.
     *    - Remove observers.
     *    - Notify observers.
     *
     *
     * 2. Observer:
     *
     *    Defines a common interface for receiving updates.
     *
     *
     * 3. Concrete Subject / Concrete Publisher:
     *
     *    Maintains the actual state or event information.
     *
     *    Notifies observers when relevant changes occur.
     *
     *
     * 4. Concrete Observer / Subscriber:
     *
     *    Implements the Observer interface.
     *
     *    Reacts to notifications from the Subject.
     *
     *
     * STRUCTURE:
     *
     *                  Subject
     *                     |
     *              Observer List
     *               /     |     \
     *              v      v      v
     *          Observer Observer Observer
     *              |      |      |
     *              v      v      v
     *           Update  Update  Update
     *
     *
     * ============================================================
     * 5. COMPLETE JAVA IMPLEMENTATION
     * ============================================================
     *
     * Example: YouTube Channel and Subscribers.
     *
     *
     * STEP 1: Create the Observer interface.
     *
     * interface Subscriber {
     *
     *     void update(String videoTitle);
     * }
     *
     * Every subscriber must implement update().
     *
     *
     * STEP 2: Create Concrete Observers.
     *
     * class User implements Subscriber {
     *
     *     private String name;
     *
     *     public User(String name) {
     *
     *         this.name = name;
     *     }
     *
     *     @Override
     *     public void update(String videoTitle) {
     *
     *         System.out.println(
     *             name + " received notification: " + videoTitle
     *         );
     *     }
     * }
     *
     * User is a Concrete Observer.
     *
     * Each User receives updates through the same interface.
     *
     *
     * STEP 3: Create the Subject interface.
     *
     * import java.util.List;
     *
     * interface Channel {
     *
     *     void subscribe(Subscriber subscriber);
     *
     *     void unsubscribe(Subscriber subscriber);
     *
     *     void notifySubscribers(String videoTitle);
     * }
     *
     * This interface defines subscription management
     * and notification operations.
     *
     *
     * STEP 4: Create the Concrete Subject.
     *
     * import java.util.ArrayList;
     * import java.util.List;
     *
     * class YouTubeChannel implements Channel {
     *
     *     private List<Subscriber> subscribers =
     *         new ArrayList<>();
     *
     *     @Override
     *     public void subscribe(Subscriber subscriber) {
     *
     *         subscribers.add(subscriber);
     *     }
     *
     *     @Override
     *     public void unsubscribe(Subscriber subscriber) {
     *
     *         subscribers.remove(subscriber);
     *     }
     *
     *     @Override
     *     public void notifySubscribers(String videoTitle) {
     *
     *         for (Subscriber subscriber : subscribers) {
     *
     *             subscriber.update(videoTitle);
     *         }
     *     }
     *
     *     public void uploadVideo(String videoTitle) {
     *
     *         System.out.println(
     *             "\nNew video uploaded: " + videoTitle
     *         );
     *
     *         notifySubscribers(videoTitle);
     *     }
     * }
     *
     * YouTubeChannel is the Concrete Subject.
     *
     * It maintains subscribers and notifies them
     * whenever a new video is uploaded.
     *
     *
     * STEP 5: Client code.
     *
     * YouTubeChannel channel = new YouTubeChannel();
     *
     * User user1 = new User("Vinay");
     * User user2 = new User("Rahul");
     * User user3 = new User("Kiran");
     *
     * channel.subscribe(user1);
     * channel.subscribe(user2);
     * channel.subscribe(user3);
     *
     * channel.uploadVideo("Java Design Patterns");
     *
     * channel.unsubscribe(user2);
     *
     * channel.uploadVideo("Spring Boot Tutorial");
     *
     *
     * OUTPUT:
     *
     * New video uploaded: Java Design Patterns
     * Vinay received notification: Java Design Patterns
     * Rahul received notification: Java Design Patterns
     * Kiran received notification: Java Design Patterns
     *
     * New video uploaded: Spring Boot Tutorial
     * Vinay received notification: Spring Boot Tutorial
     * Kiran received notification: Spring Boot Tutorial
     *
     *
     * ============================================================
     * 6. UNDERSTANDING THE EXECUTION FLOW
     * ============================================================
     *
     * Initially:
     *
     * subscribers = []
     *
     *
     * STEP 1: Subscribe users.
     *
     * channel.subscribe(user1);
     * channel.subscribe(user2);
     * channel.subscribe(user3);
     *
     * Now:
     *
     * subscribers = [user1, user2, user3]
     *
     *
     * STEP 2: Upload a video.
     *
     * channel.uploadVideo("Java Design Patterns");
     *
     * This calls:
     *
     * notifySubscribers(videoTitle);
     *
     *
     * STEP 3: Notify each subscriber.
     *
     * for (Subscriber subscriber : subscribers) {
     *
     *     subscriber.update(videoTitle);
     * }
     *
     * Each subscriber receives the notification.
     *
     *
     * STEP 4: Unsubscribe a user.
     *
     * channel.unsubscribe(user2);
     *
     * Now:
     *
     * subscribers = [user1, user3]
     *
     * user2 will no longer receive notifications.
     *
     *
     * STEP 5: Upload another video.
     *
     * Only user1 and user3 are notified.
     *
     *
     * IMPORTANT:
     *
     * The Subject does not need to know how each subscriber
     * processes the notification.
     *
     * It only calls the common update() method.
     *
     *
     * ============================================================
     * 7. PUSH MODEL VS PULL MODEL
     * ============================================================
     *
     * Observer pattern supports two common ways of
     * delivering updates.
     *
     *
     * A. PUSH MODEL
     *
     * The Subject sends the required data directly
     * to the Observer.
     *
     * Example:
     *
     * interface Observer {
     *
     *     void update(String message);
     * }
     *
     * subject.notifyObservers("New video uploaded");
     *
     * The Observer receives the message as an argument.
     *
     * Advantages:
     *
     * - Simple notification mechanism.
     * - Observer receives the information immediately.
     *
     * Disadvantages:
     *
     * - The Subject may send information that some observers
     *   do not need.
     *
     *
     * B. PULL MODEL
     *
     * The Subject notifies observers that something changed.
     *
     * Observers then request the required information
     * from the Subject.
     *
     * Example:
     *
     * interface Observer {
     *
     *     void update();
     * }
     *
     * class WeatherStation {
     *
     *     private float temperature;
     *
     *     public float getTemperature() {
     *
     *         return temperature;
     *     }
     * }
     *
     * The observer receives a notification and then calls:
     *
     * weatherStation.getTemperature();
     *
     *
     * COMPARISON:
     *
     * PUSH:
     *
     * Subject -> Sends data to Observer.
     *
     * PULL:
     *
     * Subject -> Notifies Observer.
     *
     * Observer -> Requests required data.
     *
     *
     * ============================================================
     * 8. OBSERVER VS PUB-SUB
     * ============================================================
     *
     * These concepts are related, but they are not exactly
     * the same architectural approach.
     *
     *
     * OBSERVER:
     *
     * - Observers register with a Subject.
     * - Subject generally maintains observer references.
     * - Subject directly notifies registered observers.
     * - Often used within the same application or process.
     *
     *
     * PUBLISH-SUBSCRIBE:
     *
     * - Publishers publish messages to a topic or channel.
     * - Subscribers subscribe to topics.
     * - A message broker or event infrastructure
     *   commonly distributes messages.
     * - Publishers and subscribers can be more independent.
     *
     *
     * EXAMPLE:
     *
     * Observer:
     *
     * YouTubeChannel directly notifies its registered subscribers.
     *
     *
     * Publish-Subscribe:
     *
     * An application publishes an event to Kafka.
     *
     * Multiple independent consumers receive that event.
     *
     *
     * REMEMBER:
     *
     * Observer -> Direct subject-observer relationship.
     *
     * Pub-Sub -> Often uses an intermediary message broker.
     *
     * The exact implementation can vary.
     *
     *
     * ============================================================
     * 9. OBSERVER VS MEDIATOR
     * ============================================================
     *
     * Both patterns help reduce direct dependencies
     * between objects, but their intentions are different.
     *
     *
     * OBSERVER:
     *
     * One Subject notifies multiple Observers about changes.
     *
     *
     * MEDIATOR:
     *
     * A central object coordinates communication
     * between multiple interacting objects.
     *
     *
     * EXAMPLE:
     *
     * Observer:
     *
     * A news channel notifies all subscribers.
     *
     *
     * Mediator:
     *
     * A chat room coordinates messages between users.
     *
     *
     * REMEMBER:
     *
     * Observer -> One-to-many notification.
     *
     * Mediator -> Centralized interaction coordination.
     *
     *
     * ============================================================
     * 10. OBSERVER VS CHAIN OF RESPONSIBILITY
     * ============================================================
     *
     * OBSERVER:
     *
     * A Subject notifies all registered observers.
     *
     * CHAIN OF RESPONSIBILITY:
     *
     * A request passes through a sequence of handlers
     * until it is handled or the chain ends.
     *
     *
     * REMEMBER:
     *
     * Observer -> Broadcast notification.
     *
     * Chain -> Pass a request along handlers.
     *
     *
     * ============================================================
     * 11. OBSERVER IN JAVA
     * ============================================================
     *
     * Java provides several APIs and mechanisms
     * that support observer-style designs.
     *
     *
     * A. PropertyChangeListener
     *
     * Java Beans provides:
     *
     * java.beans.PropertyChangeListener
     *
     * It allows an object to listen for property changes.
     *
     * Example:
     *
     * import java.beans.PropertyChangeEvent;
     * import java.beans.PropertyChangeListener;
     *
     * class TemperatureListener
     *         implements PropertyChangeListener {
     *
     *     @Override
     *     public void propertyChange(PropertyChangeEvent event) {
     *
     *         System.out.println(
     *             "Changed from " + event.getOldValue()
     *             + " to " + event.getNewValue()
     *         );
     *     }
     * }
     *
     * A Java Beans source can register this listener
     * and notify it when a bound property changes.
     *
     *
     * B. Swing Event Listeners
     *
     * Java Swing uses listener-based event handling.
     *
     * Example:
     *
     * button.addActionListener(event -> {
     *
     *     System.out.println("Button clicked");
     * });
     *
     * The button acts as an event source.
     *
     * The ActionListener receives the event.
     *
     *
     * C. Flow API
     *
     * Java 9 introduced the Flow API:
     *
     * - Flow.Publisher
     * - Flow.Subscriber
     * - Flow.Subscription
     * - Flow.Processor
     *
     * It supports asynchronous, demand-aware
     * publish-subscribe communication.
     *
     * A Subscriber can request how many items it is ready
     * to receive through its Subscription.
     *
     * This is more than a simple synchronous Observer loop.
     *
     *
     * IMPORTANT:
     *
     * java.util.Observer and java.util.Observable
     * were deprecated in Java 9.
     *
     * They are legacy APIs and should generally not be
     * selected for new applications.
     *
     *
     * ============================================================
     * 12. OBSERVER IN SPRING FRAMEWORK
     * ============================================================
     *
     * Spring provides an application event mechanism.
     *
     * Main concepts:
     *
     * - ApplicationEventPublisher
     * - ApplicationListener
     * - @EventListener
     *
     *
     * EXAMPLE:
     *
     * STEP 1: Define an event.
     *
     * public record UserRegisteredEvent(
     *     String email
     * ) {}
     *
     *
     * STEP 2: Publish the event.
     *
     * import org.springframework.context.ApplicationEventPublisher;
     * import org.springframework.stereotype.Service;
     *
     * @Service
     * class UserService {
     *
     *     private final ApplicationEventPublisher publisher;
     *
     *     UserService(ApplicationEventPublisher publisher) {
     *
     *         this.publisher = publisher;
     *     }
     *
     *     public void registerUser(String email) {
     *
     *         // Save the user to the database.
     *
     *         publisher.publishEvent(
     *             new UserRegisteredEvent(email)
     *         );
     *     }
     * }
     *
     *
     * STEP 3: Listen for the event.
     *
     * import org.springframework.context.event.EventListener;
     * import org.springframework.stereotype.Component;
     *
     * @Component
     * class WelcomeEmailListener {
     *
     *     @EventListener
     *     public void handle(UserRegisteredEvent event) {
     *
     *         System.out.println(
     *             "Send welcome email to: " + event.email()
     *         );
     *     }
     * }
     *
     *
     * FLOW:
     *
     * UserService
     *     |
     *     | publishEvent()
     *     v
     * Spring Event Publisher
     *     |
     *     v
     * WelcomeEmailListener
     *     |
     *     v
     * Send Welcome Email
     *
     *
     * IMPORTANT:
     *
     * Spring application events are commonly delivered
     * synchronously by default.
     *
     * Asynchronous event handling can be configured,
     * for example with @Async and appropriate configuration.
     *
     * Spring application events are generally scoped
     * to the application context.
     *
     * They are not automatically a distributed message broker.
     *
     *
     * ============================================================
     * 13. OBSERVER IN BACKEND DEVELOPMENT
     * ============================================================
     *
     * Consider an e-commerce application.
     *
     * When an order is placed, several actions may be required:
     *
     * - Send an email.
     * - Send an SMS.
     * - Update analytics.
     * - Notify the inventory module.
     * - Create an audit record.
     *
     * Instead of making the OrderService directly depend
     * on every notification or reporting component,
     * it can publish an OrderPlaced event.
     *
     * Multiple listeners can react to that event.
     *
     *
     * ORDER SERVICE
     *       |
     *       | OrderPlaced event
     *       v
     *   Event Publisher
     *       |
     *       +------> Email Listener
     *       |
     *       +------> SMS Listener
     *       |
     *       +------> Analytics Listener
     *       |
     *       +------> Audit Listener
     *
     *
     * BENEFITS:
     *
     * - Reduces direct coupling.
     * - New listeners can be added independently.
     * - Separates event publication from event handling.
     *
     *
     * IMPORTANT BACKEND CONSIDERATIONS:
     *
     * 1. Transaction boundaries:
     *
     *    An event listener may execute before or after
     *    the publishing transaction commits, depending
     *    on the event mechanism and configuration.
     *
     *    Spring's @TransactionalEventListener can bind
     *    listener execution to transaction phases.
     *
     *
     * 2. Reliability:
     *
     *    In-memory events are not automatically durable.
     *
     *    If the application stops unexpectedly,
     *    an event may not be processed.
     *
     *    For reliable distributed delivery, consider
     *    a message broker and patterns such as the
     *    Transactional Outbox.
     *
     *
     * 3. Duplicate processing:
     *
     *    Distributed event systems may deliver events
     *    more than once.
     *
     *    Consumers should consider idempotency.
     *
     *
     * ============================================================
     * 14. ADVANTAGES
     * ============================================================
     *
     * 1. Reduces coupling between Subject and Observers.
     *
     * 2. Supports one-to-many communication.
     *
     * 3. New observers can be added without changing
     *    the Subject's notification logic.
     *
     * 4. Observers can subscribe and unsubscribe dynamically.
     *
     * 5. Supports event-driven application design.
     *
     * 6. Separates event production from event handling.
     *
     *
     * ============================================================
     * 15. DISADVANTAGES
     * ============================================================
     *
     * 1. A large number of observers can make notification
     *    processing expensive.
     *
     * 2. Notification order may matter in some applications.
     *
     * 3. Circular notifications can cause unexpected behavior.
     *
     * 4. Debugging can become difficult when many observers
     *    react to the same event.
     *
     * 5. Synchronous observers can slow down the publisher.
     *
     * 6. Observer references may cause memory leaks if
     *    subscriptions are never removed.
     *
     *
     * ============================================================
     * 16. IMPORTANT IMPLEMENTATION CONSIDERATIONS
     * ============================================================
     *
     * A. DUPLICATE SUBSCRIPTIONS
     *
     * ArrayList allows the same observer to be registered
     * multiple times.
     *
     * If duplicate notifications are not desired,
     * prevent duplicate registration or use a suitable Set.
     *
     *
     * B. CONCURRENT MODIFICATION
     *
     * Suppose an observer subscribes or unsubscribes
     * while the Subject is iterating over its observer list.
     *
     * This can cause ConcurrentModificationException
     * when using an ordinary ArrayList iterator.
     *
     * Possible solutions include:
     *
     * - Iterate over a defensive copy.
     * - Use an appropriate concurrent collection.
     * - Define clear subscription rules.
     *
     * The correct choice depends on the application's
     * concurrency and ordering requirements.
     *
     *
     * C. THREAD SAFETY
     *
     * ArrayList is not thread-safe.
     *
     * If multiple threads register, remove or notify observers,
     * synchronization or a suitable concurrent design
     * may be required.
     *
     *
     * D. MEMORY LEAKS
     *
     * A Subject holding a strong reference to an Observer
     * can prevent that Observer from being garbage-collected.
     *
     * Unsubscribe when the observer no longer needs updates.
     *
     *
     * E. NOTIFICATION FAILURES
     *
     * If one observer throws an exception,
     * a simple notification loop may stop before notifying
     * the remaining observers.
     *
     * Decide whether to isolate observer failures,
     * log them or propagate them.
     *
     *
     * ============================================================
     * 17. WHEN SHOULD WE USE OBSERVER?
     * ============================================================
     *
     * Use Observer when:
     *
     * - One object needs to notify multiple dependent objects.
     * - Subscribers can be added or removed dynamically.
     * - You want to reduce direct dependencies.
     * - Multiple components react to the same event.
     * - You are implementing event-driven behavior.
     *
     *
     * Avoid Observer when:
     *
     * - There is only one simple receiver.
     * - Direct method calls are clearer and sufficient.
     * - Notification dependencies become difficult to trace.
     * - A durable distributed messaging system is required
     *   but only in-memory notifications are available.
     *
     *
     * ============================================================
     * 18. COMMON MISCONCEPTIONS
     * ============================================================
     *
     * 1. Observer means one object calls one other object.
     *
     *    FALSE.
     *
     *    Its central idea is one-to-many notification.
     *
     *
     * 2. Subject must know every concrete observer class.
     *
     *    FALSE.
     *
     *    It should generally depend on the Observer abstraction.
     *
     *
     * 3. Observer and Pub-Sub are exactly the same.
     *
     *    FALSE.
     *
     *    Pub-Sub commonly introduces an intermediary broker.
     *
     *
     * 4. Observer always runs asynchronously.
     *
     *    FALSE.
     *
     *    A normal Observer implementation can be synchronous.
     *
     *
     * 5. Java's Observable class is the recommended modern API.
     *
     *    FALSE.
     *
     *    java.util.Observable and Observer were deprecated
     *    in Java 9.
     *
     *
     * 6. Observer automatically guarantees reliable delivery.
     *
     *    FALSE.
     *
     *    Reliability depends on the implementation and
     *    infrastructure.
     *
     *
     * ============================================================
     * 19. INTERVIEW QUESTIONS
     * ============================================================
     *
     * Q1. What is Observer Design Pattern?
     *
     * A: A behavioral pattern that defines a one-to-many
     *    dependency so that changes in a Subject notify
     *    its registered Observers.
     *
     *
     * Q2. What are the main participants?
     *
     * A: Subject, Observer, Concrete Subject and
     *    Concrete Observer.
     *
     *
     * Q3. What is the responsibility of a Subject?
     *
     * A: It manages observers and notifies them of changes.
     *
     *
     * Q4. What is the difference between push and pull models?
     *
     * A: Push sends data with the notification.
     *    Pull notifies observers and lets them request
     *    the information they need.
     *
     *
     * Q5. What is the difference between Observer and Pub-Sub?
     *
     * A: Observer commonly uses direct Subject-to-Observer
     *    notification. Pub-Sub commonly uses an intermediary
     *    such as a message broker.
     *
     *
     * Q6. Does Observer support dynamic subscription?
     *
     * A: Yes. Observers can generally register and unregister
     *    at runtime.
     *
     *
     * Q7. Is Observer synchronous or asynchronous?
     *
     * A: It can be either. A basic implementation is usually
     *    synchronous unless asynchronous behavior is added.
     *
     *
     * Q8. How does Spring support Observer-style communication?
     *
     * A: Through ApplicationEventPublisher, ApplicationListener
     *    and @EventListener.
     *
     *
     * Q9. What is the difference between Observer and Mediator?
     *
     * A: Observer broadcasts changes from one Subject to
     *    multiple Observers. Mediator coordinates interactions
     *    between multiple objects.
     *
     *
     * Q10. What problems can occur with Observer?
     *
     * A: Memory leaks, excessive notifications, concurrency
     *     issues, notification ordering and difficult debugging.
     *
     *
     * ============================================================
     * 20. QUICK REVISION
     * ============================================================
     *
     * PATTERN:
     * Observer
     *
     * CATEGORY:
     * Behavioral Design Pattern
     *
     * PURPOSE:
     * Notify multiple objects when one object's state changes.
     *
     * MAIN IDEA:
     * One-to-many dependency.
     *
     * PARTICIPANTS:
     *
     * Subject
     * Observer
     * Concrete Subject
     * Concrete Observer
     *
     * COMMON USE CASES:
     *
     * Notification Systems
     * Event Handling
     * GUI Listeners
     * News Subscriptions
     * Spring Application Events
     *
     * IMPORTANT CONCEPTS:
     *
     * Subscribe
     * Unsubscribe
     * Notify
     * Push Model
     * Pull Model
     * Event-Driven Architecture
     * Publish-Subscribe
     *
     * REMEMBER:
     *
     * Subject -> Maintains and notifies observers.
     *
     * Observer -> Defines the update contract.
     *
     * Concrete Observer -> Reacts to notifications.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Observer Design Pattern , Happy Learning ..!");
    }
}
