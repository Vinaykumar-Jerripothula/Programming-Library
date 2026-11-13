
public class Bridge {

    /*
     * ============================================================
     * 1. INTRODUCTION
     * ============================================================
     *
     * Imagine you are developing a remote-control application.
     *
     * You have two types of devices:
     *
     *   - TV
     *   - Radio
     *
     * And two types of remotes:
     *
     *   - Basic Remote
     *   - Advanced Remote
     *
     * Both remotes should work with both devices.
     *
     * If you create separate classes for every combination:
     *
     *   - BasicTVRemote
     *   - AdvancedTVRemote
     *   - BasicRadioRemote
     *   - AdvancedRadioRemote
     *
     * The number of classes increases as new remotes and devices
     * are introduced.
     *
     * What if you add a Smart Speaker and another type of remote?
     *
     * You may end up creating many more combinations.
     *
     * Bridge solves this problem by separating the remote-control
     * abstraction from the device implementation.
     *
     *
     * DEFINITION:
     *
     * Bridge is a structural design pattern that separates an
     * abstraction from its implementation so that both can vary
     * independently.
     *
     *
     * ============================================================
     * 2. WHY DO WE NEED BRIDGE?
     * ============================================================
     *
     * Consider two independent dimensions:
     *
     * REMOTE TYPES:
     *   - Basic Remote
     *   - Advanced Remote
     *
     * DEVICE TYPES:
     *   - TV
     *   - Radio
     *
     * Without Bridge, we may create a separate class for every
     * combination.
     *
     * With Bridge:
     *
     *   Remote (Abstraction)
     *       |
     *       v
     *   Device (Implementation)
     *
     * Remote contains a reference to Device.
     *
     * Now we can combine any compatible remote with any device.
     *
     * Instead of managing every combination through inheritance,
     * we manage two independent class hierarchies.
     *
     *
     * ============================================================
     * 3. REAL-WORLD ANALOGY
     * ============================================================
     *
     * Think about a TV remote.
     *
     * The remote provides operations such as:
     *
     *   - Turn ON
     *   - Turn OFF
     *   - Increase volume
     *   - Decrease volume
     *
     * The TV or radio performs the actual device operations.
     *
     * The remote doesn't need to know the internal implementation
     * of every device.
     *
     * It communicates through a common device interface.
     *
     * You can use different remote types with different devices.
     *
     * REMEMBER:
     *
     * Bridge separates WHAT the system does from HOW it does it.
     *
     *
     * ============================================================
     * 4. STRUCTURE AND PARTICIPANTS
     * ============================================================
     *
     * Main participants:
     *
     * 1. Abstraction:
     *    Defines the high-level operations exposed to clients.
     *
     * 2. Refined Abstraction:
     *    Extends the Abstraction with additional functionality.
     *
     * 3. Implementor:
     *    Defines the interface for implementation-specific
     *    operations.
     *
     * 4. Concrete Implementor:
     *    Provides the actual implementation.
     *
     * 5. Client:
     *    Uses the Abstraction.
     *
     *
     * BASIC STRUCTURE:
     *
     * Client
     *   |
     *   v
     * Abstraction
     *   |
     *   | has-a
     *   v
     * Implementor
     *   |
     *   v
     * Concrete Implementor
     *
     *
     * ============================================================
     * 5. COMPLETE JAVA IMPLEMENTATION
     * ============================================================
     *
     * STEP 1: Create the Implementor interface.
     *
     * interface Device {
     *
     *     void turnOn();
     *
     *     void turnOff();
     *
     *     void setVolume(int volume);
     * }
     *
     *
     * STEP 2: Create Concrete Implementors.
     *
     * class TV implements Device {
     *
     *     public void turnOn() {
     *         System.out.println("TV is ON");
     *     }
     *
     *     public void turnOff() {
     *         System.out.println("TV is OFF");
     *     }
     *
     *     public void setVolume(int volume) {
     *         System.out.println("TV volume: " + volume);
     *     }
     * }
     *
     * class Radio implements Device {
     *
     *     public void turnOn() {
     *         System.out.println("Radio is ON");
     *     }
     *
     *     public void turnOff() {
     *         System.out.println("Radio is OFF");
     *     }
     *
     *     public void setVolume(int volume) {
     *         System.out.println("Radio volume: " + volume);
     *     }
     * }
     *
     *
     * STEP 3: Create the Abstraction.
     *
     * abstract class Remote {
     *
     *     protected final Device device;
     *
     *     protected Remote(Device device) {
     *         this.device = device;
     *     }
     *
     *     public void turnOn() {
     *         device.turnOn();
     *     }
     *
     *     public void turnOff() {
     *         device.turnOff();
     *     }
     *
     *     public void setVolume(int volume) {
     *         device.setVolume(volume);
     *     }
     * }
     *
     *
     * STEP 4: Create Refined Abstractions.
     *
     * class BasicRemote extends Remote {
     *
     *     public BasicRemote(Device device) {
     *         super(device);
     *     }
     * }
     *
     * class AdvancedRemote extends Remote {
     *
     *     public AdvancedRemote(Device device) {
     *         super(device);
     *     }
     *
     *     public void mute() {
     *         device.setVolume(0);
     *     }
     * }
     *
     *
     * STEP 5: Use the Bridge.
     *
     * Device tv = new TV();
     *
     * Remote basicRemote = new BasicRemote(tv);
     *
     * basicRemote.turnOn();
     * basicRemote.setVolume(20);
     * basicRemote.turnOff();
     *
     * System.out.println("----------------");
     *
     * Device radio = new Radio();
     *
     * Remote advancedRemote = new AdvancedRemote(radio);
     *
     * advancedRemote.turnOn();
     * advancedRemote.setVolume(30);
     * advancedRemote.mute();
     * advancedRemote.turnOff();
     *
     *
     * OUTPUT:
     *
     * TV is ON
     * TV volume: 20
     * TV is OFF
     * ----------------
     * Radio is ON
     * Radio volume: 30
     * Radio volume: 0
     * Radio is OFF
     *
     *
     * ============================================================
     * 6. UNDERSTANDING THE EXECUTION FLOW
     * ============================================================
     *
     * Consider:
     *
     * Device tv = new TV();
     *
     * Remote remote = new BasicRemote(tv);
     *
     * Step 1:
     * A TV object is created.
     *
     * Step 2:
     * The TV object is passed to the BasicRemote constructor.
     *
     * Step 3:
     * The Remote abstraction stores the Device reference.
     *
     * Step 4:
     * The client calls remote.turnOn().
     *
     * Step 5:
     * Remote delegates the operation to device.turnOn().
     *
     * Step 6:
     * Runtime polymorphism executes TV.turnOn().
     *
     * The remote doesn't need to know whether the device is
     * a TV or Radio.
     *
     *
     * ============================================================
     * 7. WHY IS IT CALLED BRIDGE?
     * ============================================================
     *
     * Because it connects two independently varying hierarchies:
     *
     *   Abstraction Hierarchy
     *          |
     *          |
     *        Bridge
     *          |
     *          |
     *   Implementation Hierarchy
     *
     * The abstraction communicates with the implementation
     * through a reference to the Implementor interface.
     *
     *
     * ============================================================
     * 8. BRIDGE VS ADAPTER
     * ============================================================
     *
     * ADAPTER:
     *
     * - Makes existing incompatible interfaces work together.
     * - Commonly introduced when integrating existing components.
     * - Converts one interface into another expected interface.
     *
     * BRIDGE:
     *
     * - Separates abstraction from implementation.
     * - Designed to allow both sides to evolve independently.
     * - Connects two independently varying hierarchies.
     *
     * Example:
     *
     * Adapter:
     * Convert an incompatible third-party payment API into
     * your application's PaymentProcessor interface.
     *
     * Bridge:
     * Support multiple remote types and multiple device types
     * without creating a class for every combination.
     *
     *
     * ============================================================
     * 9. BRIDGE VS STRATEGY
     * ============================================================
     *
     * Both patterns commonly use composition and interfaces.
     *
     * STRATEGY:
     *
     * Encapsulates interchangeable algorithms or behaviors.
     *
     * Example:
     * Different payment calculation or sorting algorithms.
     *
     * BRIDGE:
     *
     * Separates an abstraction from its implementation so that
     * both can evolve independently.
     *
     * Example:
     * Different remote types working with different devices.
     *
     * IMPORTANT:
     *
     * Their structures may look similar, but their design
     * intentions are different.
     *
     *
     * ============================================================
     * 10. BRIDGE VS DECORATOR
     * ============================================================
     *
     * BRIDGE:
     *
     * Separates two dimensions of a design into independent
     * hierarchies.
     *
     * DECORATOR:
     *
     * Adds responsibilities to an object dynamically while
     * preserving its interface.
     *
     * Example:
     *
     * Bridge:
     * Remote abstraction + TV/Radio implementations.
     *
     * Decorator:
     * Add logging, compression or encryption around a component.
     *
     *
     * ============================================================
     * 11. WHY NOT USE INHERITANCE FOR EVERYTHING?
     * ============================================================
     *
     * Suppose we use inheritance for every combination:
     *
     * BasicTVRemote
     * AdvancedTVRemote
     * BasicRadioRemote
     * AdvancedRadioRemote
     *
     * Now add:
     *
     * SmartSpeaker
     * Projector
     *
     * More combinations appear.
     *
     * This is called a combinatorial class explosion.
     *
     * Bridge avoids this by separating the two dimensions:
     *
     * Remote hierarchy:
     *   - BasicRemote
     *   - AdvancedRemote
     *
     * Device hierarchy:
     *   - TV
     *   - Radio
     *   - SmartSpeaker
     *   - Projector
     *
     * New remote types and device types can be added
     * independently, provided they satisfy their abstractions.
     *
     *
     * ============================================================
     * 12. BRIDGE AND COMPOSITION
     * ============================================================
     *
     * In our example:
     *
     * abstract class Remote {
     *
     *     protected final Device device;
     * }
     *
     * Remote HAS-A Device.
     *
     * This is composition.
     *
     * The Remote does not inherit from TV or Radio.
     *
     * Instead, it delegates device operations to the
     * Device implementation.
     *
     * This is the key structural relationship in our example.
     *
     *
     * ============================================================
     * 13. REAL-WORLD APPLICATIONS
     * ============================================================
     *
     * 1. GUI frameworks:
     *    Separate UI controls from platform-specific rendering.
     *
     * 2. Payment systems:
     *    Separate payment operations from provider-specific
     *    implementations.
     *
     * 3. Notification systems:
     *    Separate notification types from delivery mechanisms.
     *
     * 4. Database abstraction:
     *    Separate high-level operations from database-specific
     *    implementations.
     *
     * 5. Remote-control systems:
     *    Separate remote functionality from device functionality.
     *
     *
     * ============================================================
     * 14. BRIDGE IN BACKEND DEVELOPMENT
     * ============================================================
     *
     * Consider a notification system.
     *
     * You have different notification types:
     *
     *   - Alert
     *   - Reminder
     *   - Promotional notification
     *
     * And different delivery channels:
     *
     *   - Email
     *   - SMS
     *   - Push notification
     *
     * These are two independent dimensions.
     *
     * You can model:
     *
     * Notification (Abstraction)
     *       |
     *       v
     * NotificationSender (Implementor)
     *
     * Concrete notifications define what should be sent.
     *
     * Concrete senders define how it should be delivered.
     *
     * This avoids creating a separate class for every
     * notification-type and delivery-channel combination.
     *
     * The exact design should depend on whether the two
     * dimensions genuinely need to vary independently.
     *
     *
     * ============================================================
     * 15. BRIDGE AND SOLID PRINCIPLES
     * ============================================================
     *
     * 1. OPEN/CLOSED PRINCIPLE:
     *
     * New device implementations can be added without changing
     * existing remote implementations.
     *
     * New remote abstractions can be added without changing
     * existing device implementations.
     *
     *
     * 2. DEPENDENCY INVERSION PRINCIPLE:
     *
     * Remote depends on the Device abstraction rather than
     * directly depending on TV or Radio.
     *
     *
     * 3. SINGLE RESPONSIBILITY PRINCIPLE:
     *
     * Remote behavior and device-specific behavior are
     * separated into different hierarchies.
     *
     *
     * ============================================================
     * 16. ADVANTAGES
     * ============================================================
     *
     * 1. Separates abstraction and implementation.
     * 2. Allows both hierarchies to evolve independently.
     * 3. Reduces class explosion caused by combinations.
     * 4. Encourages composition over inheritance.
     * 5. Reduces coupling between high-level and low-level code.
     * 6. Makes adding new implementations easier.
     *
     *
     * ============================================================
     * 17. DISADVANTAGES
     * ============================================================
     *
     * 1. Introduces additional abstractions and interfaces.
     * 2. Can make simple designs unnecessarily complex.
     * 3. Requires careful identification of independent dimensions.
     * 4. Delegation adds another layer of indirection.
     *
     *
     * ============================================================
     * 18. WHEN SHOULD WE USE BRIDGE?
     * ============================================================
     *
     * Use Bridge when:
     *
     * - You have two independent dimensions of variation.
     * - Both abstraction and implementation need to evolve.
     * - Inheritance creates too many combinations.
     * - You want to separate high-level behavior from
     *   implementation-specific details.
     *
     * Avoid Bridge when:
     *
     * - There is only one simple implementation.
     * - The two hierarchies do not need to vary independently.
     * - The additional abstraction creates unnecessary complexity.
     *
     *
     * ============================================================
     * 19. COMMON MISCONCEPTIONS
     * ============================================================
     *
     * 1. Bridge and Adapter are the same.
     *    FALSE. Adapter focuses on interface compatibility;
     *    Bridge focuses on independent variation.
     *
     * 2. Bridge always requires an interface.
     *    FALSE. The Implementor can also be an abstract class.
     *
     * 3. Bridge eliminates inheritance.
     *    FALSE. It often uses inheritance within both hierarchies.
     *    It reduces dependence on inheritance for combinations.
     *
     * 4. Bridge and Strategy are identical.
     *    FALSE. They can have similar structures but different
     *    design intentions.
     *
     * 5. Bridge is only useful for hardware devices.
     *    FALSE. It is useful wherever two dimensions vary
     *    independently.
     *
     *
     * ============================================================
     * 20. INTERVIEW QUESTIONS
     * ============================================================
     *
     * Q1. What is the Bridge pattern?
     *
     * A: A structural pattern that separates an abstraction
     *    from its implementation so both can vary independently.
     *
     * Q2. What problem does Bridge solve?
     *
     * A: It prevents class explosion when multiple independent
     *    dimensions are combined through inheritance.
     *
     * Q3. What are its main participants?
     *
     * A: Abstraction, Refined Abstraction, Implementor,
     *    Concrete Implementor and Client.
     *
     * Q4. What is the main relationship in Bridge?
     *
     * A: The Abstraction contains a reference to the Implementor.
     *
     * Q5. Does Bridge use composition?
     *
     * A: Yes. The Abstraction delegates implementation-specific
     *    operations through the Implementor reference.
     *
     * Q6. What is the difference between Adapter and Bridge?
     *
     * A: Adapter makes existing interfaces compatible;
     *    Bridge separates abstraction and implementation
     *    for independent variation.
     *
     * Q7. What is the difference between Bridge and Strategy?
     *
     * A: Strategy encapsulates interchangeable algorithms;
     *    Bridge separates two independently varying dimensions.
     *
     * Q8. What is class explosion?
     *
     * A: An excessive number of classes created to represent
     *    every possible combination of multiple dimensions.
     *
     *
     * ============================================================
     * 21. QUICK REVISION
     * ============================================================
     *
     * PATTERN: Bridge
     *
     * CATEGORY: Structural
     *
     * PURPOSE:
     * Separate abstraction from implementation.
     *
     * MAIN IDEA:
     * Allow both sides to vary independently.
     *
     * KEY RELATIONSHIP:
     * Abstraction HAS-A Implementor.
     *
     * COMMON TECHNIQUE:
     * Composition and delegation.
     *
     * MAIN BENEFIT:
     * Avoid class explosion caused by combinations.
     *
     * REMEMBER:
     *
     * Adapter -> Make incompatible interfaces work together.
     *
     * Bridge -> Separate abstraction and implementation.
     *
     * Strategy -> Encapsulate interchangeable algorithms.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Bridge Design Pattern , Happy Learning ..!");
    }
}
