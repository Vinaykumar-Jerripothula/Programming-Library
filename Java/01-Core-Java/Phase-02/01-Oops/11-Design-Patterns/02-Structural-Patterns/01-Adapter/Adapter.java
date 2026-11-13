
public class Adapter {

    /*
     * ============================================================
     * 1. INTRODUCTION
     * ============================================================
     *
     * Imagine you have a laptop with USB-C ports.
     *
     * But you have an old USB-A pen drive.
     *
     * The pen drive cannot directly fit into the laptop's port.
     *
     * Do you need to replace the laptop or the pen drive?
     *
     * No.
     *
     * You use a USB-C to USB-A adapter.
     *
     * The adapter allows two incompatible interfaces to work
     * together without changing either device.
     *
     * This is the core idea behind the Adapter Design Pattern.
     *
     *
     * DEFINITION:
     *
     * Adapter is a structural design pattern that allows objects
     * with incompatible interfaces to collaborate by converting
     * the interface of one object into an interface expected
     * by another object.
     *
     *
     * ============================================================
     * 2. WHY DO WE NEED ADAPTER?
     * ============================================================
     *
     * Imagine your application expects this interface:
     *
     * interface PaymentProcessor {
     *     void pay(double amount);
     * }
     *
     * Your application already works with PaymentProcessor.
     *
     * Now you want to integrate a third-party payment service.
     *
     * Unfortunately, that service provides:
     *
     * class ThirdPartyPaymentService {
     *     void makePayment(double amount) {
     *         System.out.println("Payment: " + amount);
     *     }
     * }
     *
     * Notice the problem:
     *
     * Your application expects:
     *     pay()
     *
     * The third-party service provides:
     *     makePayment()
     *
     * Their interfaces are incompatible.
     *
     * You could modify your existing application to use
     * makePayment(), but that would couple your application
     * directly to the third-party implementation.
     *
     * Instead, introduce an Adapter.
     *
     * The Adapter implements PaymentProcessor and internally
     * calls makePayment() on the third-party service.
     *
     *
     * ============================================================
     * 3. REAL-WORLD ANALOGY
     * ============================================================
     *
     * Think about a travel plug adapter.
     *
     * Your charger has one type of plug.
     *
     * The wall socket has another type of connection.
     *
     * The adapter sits between them and translates the connection.
     *
     * You don't need to redesign the charger or the socket.
     *
     * Similarly:
     *
     * Client -> Adapter -> Existing incompatible service
     *
     * REMEMBER:
     *
     * Adapter = A bridge between incompatible interfaces.
     *
     * Note:
     * Adapter and Bridge are two different GoF patterns.
     * Their purposes are explained later.
     *
     *
     * ============================================================
     * 4. STRUCTURE AND PARTICIPANTS
     * ============================================================
     *
     * The main participants are:
     *
     * 1. Target:
     *    The interface expected by the client.
     *
     * 2. Client:
     *    The class that uses the Target interface.
     *
     * 3. Adaptee:
     *    The existing class whose interface is incompatible.
     *
     * 4. Adapter:
     *    Converts the Adaptee interface into the Target interface.
     *
     *
     * BASIC STRUCTURE:
     *
     * Client
     *   |
     *   v
     * Target Interface
     *   |
     *   v
     * Adapter
     *   |
     *   v
     * Adaptee
     *
     *
     * ============================================================
     * 5. COMPLETE JAVA IMPLEMENTATION - OBJECT ADAPTER
     * ============================================================
     *
     * Java commonly implements Adapter using composition.
     *
     * STEP 1: Define the Target interface.
     *
     * interface PaymentProcessor {
     *     void pay(double amount);
     * }
     *
     *
     * STEP 2: Create the Adaptee.
     *
     * This is an existing third-party class.
     * Assume we cannot modify its implementation.
     *
     * class ThirdPartyPaymentService {
     *
     *     public void makePayment(double amount) {
     *         System.out.println(
     *             "Third-party payment processed: " + amount
     *         );
     *     }
     * }
     *
     *
     * STEP 3: Create the Adapter.
     *
     * class PaymentAdapter implements PaymentProcessor {
     *
     *     private final ThirdPartyPaymentService service;
     *
     *     public PaymentAdapter(ThirdPartyPaymentService service) {
     *         this.service = service;
     *     }
     *
     *     @Override
     *     public void pay(double amount) {
     *
     *         // Convert the Target operation into the Adaptee call.
     *         service.makePayment(amount);
     *     }
     * }
     *
     *
     * STEP 4: Create the Client.
     *
     * class PaymentApplication {
     *
     *     private final PaymentProcessor processor;
     *
     *     public PaymentApplication(PaymentProcessor processor) {
     *         this.processor = processor;
     *     }
     *
     *     public void checkout(double amount) {
     *         processor.pay(amount);
     *     }
     * }
     *
     *
     * STEP 5: Use the Adapter.
     *
     * ThirdPartyPaymentService service =
     *     new ThirdPartyPaymentService();
     *
     * PaymentProcessor adapter =
     *     new PaymentAdapter(service);
     *
     * PaymentApplication application =
     *     new PaymentApplication(adapter);
     *
     * application.checkout(1500);
     *
     *
     * OUTPUT:
     *
     * Third-party payment processed: 1500.0
     *
     *
     * ============================================================
     * 6. UNDERSTANDING THE EXECUTION FLOW
     * ============================================================
     *
     * Consider:
     *
     * PaymentProcessor adapter =
     *     new PaymentAdapter(service);
     *
     * Step 1:
     * PaymentAdapter implements PaymentProcessor.
     *
     * Step 2:
     * It receives the ThirdPartyPaymentService object through
     * its constructor.
     *
     * Step 3:
     * PaymentApplication receives the adapter through the
     * PaymentProcessor interface.
     *
     * Step 4:
     * The client calls:
     *
     *     processor.pay(1500);
     *
     * Step 5:
     * The adapter receives the call.
     *
     * Step 6:
     * The adapter translates it into:
     *
     *     service.makePayment(1500);
     *
     * Step 7:
     * The third-party service processes the payment.
     *
     * IMPORTANT:
     *
     * The client does not know that a third-party service
     * is being used internally.
     *
     * It only knows the PaymentProcessor interface.
     *
     *
     * ============================================================
     * 7. OBJECT ADAPTER VS CLASS ADAPTER
     * ============================================================
     *
     * There are two commonly discussed Adapter implementations.
     *
     *
     * A. OBJECT ADAPTER
     *
     * Uses composition.
     *
     * The Adapter contains a reference to the Adaptee object.
     *
     * Example:
     *
     * class PaymentAdapter implements PaymentProcessor {
     *
     *     private final ThirdPartyPaymentService service;
     *
     *     PaymentAdapter(ThirdPartyPaymentService service) {
     *         this.service = service;
     *     }
     *
     *     public void pay(double amount) {
     *         service.makePayment(amount);
     *     }
     * }
     *
     * Characteristics:
     *
     * - Uses composition.
     * - Can adapt an existing object.
     * - Does not require inheriting from the Adaptee.
     * - Commonly used in Java.
     *
     *
     * B. CLASS ADAPTER
     *
     * Uses inheritance.
     *
     * Conceptual example:
     *
     * class PaymentAdapter extends ThirdPartyPaymentService
     *                        implements PaymentProcessor {
     *
     *     public void pay(double amount) {
     *         makePayment(amount);
     *     }
     * }
     *
     * Characteristics:
     *
     * - Uses inheritance.
     * - Adapter inherits Adaptee behavior.
     * - Depends on the inheritance capabilities of the language.
     *
     * IMPORTANT JAVA LIMITATION:
     *
     * Java does not support multiple class inheritance.
     *
     * Therefore, a class cannot extend two unrelated classes.
     *
     * Object Adapter is generally more flexible in Java.
     *
     *
     * ============================================================
     * 8. WHY IS COMPOSITION COMMONLY PREFERRED?
     * ============================================================
     *
     * In Object Adapter:
     *
     * class PaymentAdapter implements PaymentProcessor {
     *
     *     private final ThirdPartyPaymentService service;
     * }
     *
     * The Adapter contains an Adaptee object.
     *
     * This means:
     *
     * - We can adapt different instances.
     * - We don't inherit the Adaptee's implementation.
     * - We can replace the wrapped object when appropriate.
     * - We avoid unnecessary inheritance coupling.
     *
     * This follows the general design principle:
     *
     * Prefer composition over inheritance when it provides
     * better flexibility.
     *
     *
     * ============================================================
     * 9. ADAPTER WITH DATA CONVERSION
     * ============================================================
     *
     * An Adapter does not only rename method calls.
     *
     * It can also convert data formats or units.
     *
     * Example:
     *
     * interface TemperatureSensor {
     *     double getTemperatureInCelsius();
     * }
     *
     * class FahrenheitSensor {
     *
     *     public double getTemperature() {
     *         return 98.6;
     *     }
     * }
     *
     * class TemperatureAdapter implements TemperatureSensor {
     *
     *     private final FahrenheitSensor sensor;
     *
     *     TemperatureAdapter(FahrenheitSensor sensor) {
     *         this.sensor = sensor;
     *     }
     *
     *     public double getTemperatureInCelsius() {
     *
     *         double fahrenheit = sensor.getTemperature();
     *
     *         return (fahrenheit - 32) * 5 / 9;
     *     }
     * }
     *
     * The client expects Celsius.
     *
     * The existing sensor provides Fahrenheit.
     *
     * The Adapter converts Fahrenheit into Celsius.
     *
     *
     * ============================================================
     * 10. ADAPTER VS DECORATOR
     * ============================================================
     *
     * ADAPTER:
     *
     * - Converts one interface into another.
     * - Its main purpose is compatibility.
     * - Allows incompatible components to collaborate.
     *
     * DECORATOR:
     *
     * - Adds responsibilities or behavior to an object.
     * - Preserves the same interface.
     * - Allows behavior to be added dynamically.
     *
     * Example:
     *
     * Adapter:
     * Convert an old payment service into PaymentProcessor.
     *
     * Decorator:
     * Add logging or caching around a PaymentProcessor.
     *
     *
     * ============================================================
     * 11. ADAPTER VS BRIDGE
     * ============================================================
     *
     * ADAPTER:
     *
     * Makes existing incompatible interfaces work together.
     *
     * It is commonly introduced when integrating existing code.
     *
     * BRIDGE:
     *
     * Separates an abstraction from its implementation so that
     * both can vary independently.
     *
     * It is commonly planned as part of the system design.
     *
     * Example:
     *
     * Adapter:
     * Make a third-party payment API compatible with your interface.
     *
     * Bridge:
     * Separate remote-control abstractions from device
     * implementations.
     *
     *
     * ============================================================
     * 12. ADAPTER VS FACADE
     * ============================================================
     *
     * ADAPTER:
     *
     * Converts an existing interface into another expected
     * interface.
     *
     * FACADE:
     *
     * Provides a simpler interface to a complex subsystem.
     *
     * Example:
     *
     * Adapter:
     * Convert an incompatible legacy API into a new interface.
     *
     * Facade:
     * Provide one simple method to coordinate several
     * complicated subsystem operations.
     *
     *
     * ============================================================
     * 13. ADAPTER IN JAVA
     * ============================================================
     *
     * Java has several APIs that demonstrate adapter-like ideas.
     *
     * 1. InputStreamReader:
     *
     *    InputStreamReader adapts a byte-oriented InputStream
     *    into a character-oriented Reader.
     *
     *    Example:
     *
     *    InputStream input = new FileInputStream("data.txt");
     *
     *    Reader reader = new InputStreamReader(input);
     *
     *    The Reader interface allows character-based reading.
     *
     *
     * 2. OutputStreamWriter:
     *
     *    Adapts a character-oriented Writer into a byte-oriented
     *    OutputStream destination.
     *
     *
     * 3. Arrays.asList():
     *
     *    Provides a List view backed by an array.
     *
     *    It can be discussed as an adapter-like conversion,
     *    but it is not necessarily a textbook GoF Adapter example.
     *
     * IMPORTANT:
     *
     * A class or method should not be labelled as a GoF Adapter
     * merely because it converts data or wraps another object.
     * The interface-compatibility role is the important part.
     *
     *
     * ============================================================
     * 14. ADAPTER IN BACKEND DEVELOPMENT
     * ============================================================
     *
     * Consider a Spring Boot application.
     *
     * Your application defines:
     *
     * interface PaymentGateway {
     *     void processPayment(double amount);
     * }
     *
     * You integrate different providers:
     *
     * - Razorpay
     * - Stripe
     * - PayPal
     *
     * Each provider may expose a different API.
     *
     * You can create separate adapters:
     *
     * RazorpayAdapter implements PaymentGateway
     *
     * StripeAdapter implements PaymentGateway
     *
     * PayPalAdapter implements PaymentGateway
     *
     * Your service depends only on PaymentGateway.
     *
     * The selected adapter translates your application's
     * operation into the provider-specific API.
     *
     * This makes provider integration easier to maintain.
     *
     * IMPORTANT:
     *
     * Real payment integrations must also handle:
     * - Authentication.
     * - Request and response mapping.
     * - Error handling.
     * - Idempotency.
     * - Timeouts and retries.
     * - Payment verification.
     *
     * An Adapter provides the interface boundary; it does not
     * automatically solve all integration concerns.
     *
     *
     * ============================================================
     * 15. ADAPTER AND SOLID PRINCIPLES
     * ============================================================
     *
     * 1. SINGLE RESPONSIBILITY PRINCIPLE:
     *
     * The Adapter handles compatibility between interfaces.
     *
     * The client does not need to contain provider-specific
     * conversion logic.
     *
     *
     * 2. OPEN/CLOSED PRINCIPLE:
     *
     * We can introduce another Adapter implementation for
     * another provider without rewriting the client logic.
     *
     * However, changes to the Target interface may require
     * updating its adapters.
     *
     *
     * 3. DEPENDENCY INVERSION PRINCIPLE:
     *
     * The client depends on the Target abstraction rather
     * than the concrete Adaptee.
     *
     *
     * ============================================================
     * 16. ADVANTAGES
     * ============================================================
     *
     * 1. Allows incompatible interfaces to work together.
     * 2. Helps integrate third-party libraries and APIs.
     * 3. Can preserve existing legacy code.
     * 4. Reduces coupling between client and Adaptee.
     * 5. Supports replacing external implementations.
     * 6. Separates conversion logic from business logic.
     *
     *
     * ============================================================
     * 17. DISADVANTAGES
     * ============================================================
     *
     * 1. Introduces additional classes.
     * 2. Can add another layer of indirection.
     * 3. Complex conversions may make adapters difficult to maintain.
     * 4. Poorly designed adapters can hide important behavior.
     * 5. It cannot automatically resolve incompatible business
     *    rules or semantics.
     *
     *
     * ============================================================
     * 18. WHEN SHOULD WE USE ADAPTER?
     * ============================================================
     *
     * Use Adapter when:
     *
     * - Two components have incompatible interfaces.
     * - You need to integrate a third-party library.
     * - You need to work with legacy systems.
     * - You cannot or should not modify the existing class.
     * - You want the client to depend on your own interface.
     *
     * Avoid Adapter when:
     *
     * - Both interfaces are already compatible.
     * - A direct method call is clear and appropriate.
     * - The conversion adds unnecessary complexity.
     * - The real problem is a deeper mismatch in business behavior.
     *
     *
     * ============================================================
     * 19. COMMON MISCONCEPTIONS
     * ============================================================
     *
     * 1. Adapter adds new behavior.
     *    NOT NECESSARILY. Its primary purpose is compatibility.
     *
     * 2. Adapter and Decorator are the same.
     *    FALSE. Adapter changes the interface; Decorator adds
     *    responsibilities while preserving the interface.
     *
     * 3. Adapter always uses inheritance.
     *    FALSE. Object Adapter uses composition.
     *
     * 4. Java supports multiple class inheritance for Class Adapter.
     *    FALSE. Java allows only one direct superclass.
     *
     * 5. Adapter requires modifying the Adaptee.
     *    FALSE. It commonly wraps an existing class without
     *    modifying it.
     *
     * 6. Every wrapper is an Adapter.
     *    FALSE. The wrapper must serve the purpose of converting
     *    an interface expected by the client.
     *
     *
     * ============================================================
     * 20. INTERVIEW QUESTIONS
     * ============================================================
     *
     * Q1. What is the Adapter pattern?
     *
     * A: A structural design pattern that converts an existing
     *    interface into one expected by the client.
     *
     * Q2. What are the main participants?
     *
     * A: Target, Client, Adaptee and Adapter.
     *
     * Q3. What is an Adaptee?
     *
     * A: The existing class whose interface is incompatible
     *    with the interface expected by the client.
     *
     * Q4. What is the difference between Object Adapter
     *    and Class Adapter?
     *
     * A: Object Adapter uses composition; Class Adapter uses
     *    inheritance.
     *
     * Q5. Which is commonly preferred in Java?
     *
     * A: Object Adapter, because Java does not support multiple
     *    class inheritance and composition is flexible.
     *
     * Q6. Is Adapter used only for third-party APIs?
     *
     * A: No. It can adapt legacy code, internal components,
     *    libraries and other incompatible interfaces.
     *
     * Q7. What is the difference between Adapter and Facade?
     *
     * A: Adapter makes an interface compatible; Facade provides
     *    a simpler interface to a complex subsystem.
     *
     * Q8. What is the difference between Adapter and Decorator?
     *
     * A: Adapter changes the interface; Decorator preserves the
     *    interface while adding responsibilities.
     *
     *
     * ============================================================
     * 21. QUICK REVISION
     * ============================================================
     *
     * PATTERN: Adapter
     *
     * CATEGORY: Structural
     *
     * PURPOSE:
     * Make incompatible interfaces work together.
     *
     * MAIN PARTICIPANTS:
     * Target, Client, Adaptee, Adapter.
     *
     * COMMON JAVA IMPLEMENTATION:
     * Object Adapter using composition.
     *
     * KEY IDEA:
     * Convert the interface expected by the client into calls
     * understood by an existing component.
     *
     * REMEMBER:
     *
     * Adapter -> Interface compatibility.
     *
     * Decorator -> Add responsibilities.
     *
     * Facade -> Simplify a complex subsystem.
     *
     * Bridge -> Separate abstraction and implementation.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Adapter Design Pattern , Happy Learning ..!");
    }
}
