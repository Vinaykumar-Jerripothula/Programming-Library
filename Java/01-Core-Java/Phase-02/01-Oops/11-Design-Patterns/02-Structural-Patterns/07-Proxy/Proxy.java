
public class Proxy {

    /*
     * ============================================================
     * 1. INTRODUCTION
     * ============================================================
     *
     * Imagine you are using an online banking application.
     *
     * You want to access your bank account.
     *
     * Should every user directly access the bank account object?
     *
     * Not necessarily.
     *
     * Before allowing access, the application may need to:
     *
     *   - Verify the user's identity.
     *   - Check permissions.
     *   - Record the access in logs.
     *   - Control when the actual account object is created.
     *
     * Instead of allowing direct access, we can place another
     * object between the user and the actual bank account.
     *
     * This intermediate object controls access to the real object.
     *
     * That intermediate object is called a PROXY.
     *
     *
     * DEFINITION:
     *
     * Proxy is a structural design pattern that provides a
     * substitute or placeholder for another object to control
     * access to that object.
     *
     *
     * SIMPLE DEFINITION:
     *
     * A Proxy acts as a middleman between the client and
     * the real object.
     *
     *
     * ============================================================
     * 2. WHY DO WE NEED PROXY?
     * ============================================================
     *
     * Consider an application that loads large images.
     *
     * Loading every image immediately can consume memory
     * and slow down the application.
     *
     * Instead, we can create a lightweight proxy object.
     *
     * The actual image is loaded only when the user requests
     * it for the first time.
     *
     * Similarly, a proxy can:
     *
     *   - Control access to sensitive objects.
     *   - Delay expensive object creation.
     *   - Add logging.
     *   - Add caching.
     *   - Handle remote object access.
     *
     *
     * ============================================================
     * 3. REAL-WORLD ANALOGY
     * ============================================================
     *
     * Imagine a company reception desk.
     *
     * A visitor wants to meet an employee.
     *
     * The visitor does not directly enter the employee's office.
     *
     * First, the receptionist:
     *
     *   1. Receives the visitor.
     *   2. Checks the visitor's details.
     *   3. Verifies whether the visitor is allowed.
     *   4. Connects the visitor to the employee.
     *
     * The receptionist acts as an intermediary.
     *
     * Similarly, a Proxy controls access to the actual object.
     *
     *
     * ============================================================
     * 4. STRUCTURE AND PARTICIPANTS
     * ============================================================
     *
     * Main participants:
     *
     * 1. Subject:
     *    Defines the common interface for the Real Subject
     *    and Proxy.
     *
     * 2. Real Subject:
     *    The actual object that performs the real operation.
     *
     * 3. Proxy:
     *    Implements the same Subject interface and controls
     *    access to the Real Subject.
     *
     * 4. Client:
     *    Uses the Subject interface without necessarily knowing
     *    whether it is interacting with the Proxy or Real Subject.
     *
     *
     * BASIC STRUCTURE:
     *
     *              Client
     *                 |
     *                 v
     *              Subject
     *                 |
     *          ----------------
     *          |              |
     *          v              v
     *        Proxy        Real Subject
     *          |
     *          v
     *    Controls access to
     *    Real Subject
     *
     *
     * ============================================================
     * 5. COMPLETE JAVA IMPLEMENTATION
     * ============================================================
     *
     * We will implement a document access system.
     *
     * Only authorized users should be allowed to open a document.
     *
     *
     * STEP 1: Create the Subject interface.
     *
     * interface Document {
     *
     *     void display();
     * }
     *
     * Both the real object and proxy implement this interface.
     *
     *
     * STEP 2: Create the Real Subject.
     *
     * class RealDocument implements Document {
     *
     *     private final String fileName;
     *
     *     public RealDocument(String fileName) {
     *
     *         this.fileName = fileName;
     *         loadFromDisk();
     *     }
     *
     *     private void loadFromDisk() {
     *
     *         System.out.println(
     *             "Loading document: " + fileName
     *         );
     *     }
     *
     *     @Override
     *     public void display() {
     *
     *         System.out.println(
     *             "Displaying document: " + fileName
     *         );
     *     }
     * }
     *
     * This is the actual object that performs the operation.
     *
     *
     * STEP 3: Create the Proxy.
     *
     * class DocumentProxy implements Document {
     *
     *     private RealDocument realDocument;
     *     private final String fileName;
     *     private final String userRole;
     *
     *     public DocumentProxy(
     *         String fileName,
     *         String userRole
     *     ) {
     *
     *         this.fileName = fileName;
     *         this.userRole = userRole;
     *     }
     *
     *     @Override
     *     public void display() {
     *
     *         if (!"ADMIN".equals(userRole)) {
     *
     *             System.out.println(
     *                 "Access denied: " + userRole
     *             );
     *
     *             return;
     *         }
     *
     *         System.out.println(
     *             "Access granted: " + userRole
     *         );
     *
     *         if (realDocument == null) {
     *
     *             realDocument = new RealDocument(fileName);
     *         }
     *
     *         realDocument.display();
     *     }
     * }
     *
     * Notice two important things:
     *
     * 1. The proxy checks authorization before allowing access.
     *
     * 2. The RealDocument is created only when an authorized
     *    user actually requests the document.
     *
     *
     * STEP 4: Client code.
     *
     * Document user1 =
     *     new DocumentProxy("confidential.pdf", "USER");
     *
     * Document user2 =
     *     new DocumentProxy("confidential.pdf", "ADMIN");
     *
     * user1.display();
     *
     * user2.display();
     *
     * user2.display();
     *
     *
     * OUTPUT:
     *
     * Access denied: USER
     * Access granted: ADMIN
     * Loading document: confidential.pdf
     * Displaying document: confidential.pdf
     * Access granted: ADMIN
     * Displaying document: confidential.pdf
     *
     *
     * ============================================================
     * 6. UNDERSTANDING THE EXECUTION FLOW
     * ============================================================
     *
     * Consider:
     *
     * Document user1 =
     *     new DocumentProxy("confidential.pdf", "USER");
     *
     * STEP 1:
     *
     * Client creates a Proxy object.
     *
     * The actual RealDocument is not created yet.
     *
     *
     * STEP 2:
     *
     * Client calls:
     *
     * user1.display();
     *
     * The call reaches DocumentProxy.
     *
     *
     * STEP 3:
     *
     * Proxy checks the user's role.
     *
     * The role is USER, so access is denied.
     *
     * RealDocument is never created.
     *
     *
     * STEP 4:
     *
     * An ADMIN user calls display().
     *
     * Proxy allows access.
     *
     * It creates RealDocument if it does not already exist.
     *
     *
     * STEP 5:
     *
     * Proxy delegates the operation:
     *
     * realDocument.display();
     *
     * The actual object performs the work.
     *
     *
     * STEP 6:
     *
     * When the ADMIN calls display() again, the same
     * RealDocument object is reused.
     *
     *
     * ============================================================
     * 7. IMPORTANT: WHY DO PROXY AND REAL SUBJECT
     *    IMPLEMENT THE SAME INTERFACE?
     * ============================================================
     *
     * Consider:
     *
     * Document document = new DocumentProxy(...);
     *
     * The client depends on the Document interface.
     *
     * It does not need to know the internal implementation.
     *
     * The client can use either:
     *
     * Document document = new RealDocument(...);
     *
     * OR
     *
     * Document document = new DocumentProxy(...);
     *
     * Both expose the same operations.
     *
     * This makes the Proxy replaceable from the client's
     * point of view.
     *
     * The Proxy can control access without changing the
     * client's code.
     *
     *
     * ============================================================
     * 8. TYPES OF PROXY
     * ============================================================
     *
     * There are several commonly discussed proxy types.
     *
     *
     * 1. VIRTUAL PROXY
     *
     * Controls the creation of expensive objects.
     *
     * The real object is created only when required.
     *
     * Example:
     *
     * Loading a large image only when it is displayed.
     *
     * Our document example also demonstrates lazy creation.
     *
     *
     * 2. PROTECTION PROXY
     *
     * Controls access based on permissions or authorization.
     *
     * Example:
     *
     * Only administrators can access confidential documents.
     *
     *
     * 3. REMOTE PROXY
     *
     * Represents an object that exists in another process
     * or on another machine.
     *
     * The proxy handles communication with the remote object.
     *
     * Example:
     *
     * A local client calling a remote service.
     *
     *
     * 4. CACHING PROXY
     *
     * Stores results so repeated requests can avoid
     * performing the same expensive operation.
     *
     * Example:
     *
     * Caching the result of an expensive database query.
     *
     *
     * 5. LOGGING PROXY
     *
     * Records information about method calls.
     *
     * Example:
     *
     * Recording when a user accesses a document.
     *
     *
     * 6. SMART REFERENCE PROXY
     *
     * Performs additional actions when an object is accessed.
     *
     * Example:
     *
     * Reference counting, access tracking or resource management.
     *
     * NOTE:
     *
     * These are commonly discussed proxy variations.
     * The original GoF pattern focuses on providing a
     * surrogate or placeholder to control access.
     *
     *
     * ============================================================
     * 9. PROXY VS ADAPTER
     * ============================================================
     *
     * Both patterns can implement the same interface as
     * another object, but their intentions differ.
     *
     * ADAPTER:
     *
     * Converts one interface into another interface expected
     * by the client.
     *
     * Example:
     *
     * Adapting a third-party payment gateway to your
     * application's PaymentProcessor interface.
     *
     *
     * PROXY:
     *
     * Provides the same interface while controlling access
     * to the actual object.
     *
     * Example:
     *
     * Checking permissions before opening a document.
     *
     *
     * REMEMBER:
     *
     * Adapter -> Changes the interface.
     *
     * Proxy -> Controls access.
     *
     *
     * ============================================================
     * 10. PROXY VS DECORATOR
     * ============================================================
     *
     * Both patterns wrap another object and often implement
     * the same interface.
     *
     * But their primary intentions are different.
     *
     * DECORATOR:
     *
     * Adds responsibilities or behavior to an object.
     *
     * Example:
     *
     * Adding encryption or compression around a data stream.
     *
     *
     * PROXY:
     *
     * Controls access to an object.
     *
     * Example:
     *
     * Checking authorization before allowing a method call.
     *
     *
     * REMEMBER:
     *
     * Decorator -> Adds behavior.
     *
     * Proxy -> Controls access.
     *
     * IMPORTANT:
     *
     * Their structures can look very similar.
     * The main distinction is their intent.
     *
     *
     * ============================================================
     * 11. PROXY VS FACADE
     * ============================================================
     *
     * FACADE:
     *
     * Provides a simplified interface to a complex subsystem.
     *
     * Example:
     *
     * One place to coordinate inventory, payment and shipping.
     *
     *
     * PROXY:
     *
     * Provides a substitute for an individual object
     * to control access to it.
     *
     * Example:
     *
     * A proxy that checks permissions before opening a file.
     *
     *
     * REMEMBER:
     *
     * Facade -> Simplifies subsystem usage.
     *
     * Proxy -> Controls access to an object.
     *
     *
     * ============================================================
     * 12. STATIC PROXY VS DYNAMIC PROXY
     * ============================================================
     *
     * A. STATIC PROXY
     *
     * A developer manually creates a Proxy class.
     *
     * Example:
     *
     * class DocumentProxy implements Document {
     *
     *     private final Document target;
     *
     *     public DocumentProxy(Document target) {
     *         this.target = target;
     *     }
     *
     *     @Override
     *     public void display() {
     *
     *         System.out.println("Before display");
     *
     *         target.display();
     *
     *         System.out.println("After display");
     *     }
     * }
     *
     * Here, DocumentProxy is written explicitly.
     *
     *
     * B. DYNAMIC PROXY
     *
     * A proxy implementation is generated at runtime.
     *
     * Java provides:
     *
     * java.lang.reflect.Proxy
     *
     * Dynamic proxies can implement one or more interfaces.
     *
     * Method calls are dispatched to an InvocationHandler.
     *
     * BASIC EXAMPLE:
     *
     * import java.lang.reflect.InvocationHandler;
     * import java.lang.reflect.Method;
     * import java.lang.reflect.Proxy;
     *
     * interface Service {
     *     void execute();
     * }
     *
     * class RealService implements Service {
     *
     *     public void execute() {
     *         System.out.println("Executing service");
     *     }
     * }
     *
     * class LoggingHandler implements InvocationHandler {
     *
     *     private final Object target;
     *
     *     public LoggingHandler(Object target) {
     *         this.target = target;
     *     }
     *
     *     @Override
     *     public Object invoke(
     *         Object proxy,
     *         Method method,
     *         Object[] args
     *     ) throws Throwable {
     *
     *         System.out.println("Before method call");
     *
     *         Object result = method.invoke(target, args);
     *
     *         System.out.println("After method call");
     *
     *         return result;
     *     }
     * }
     *
     * Service service = (Service) Proxy.newProxyInstance(
     *     Service.class.getClassLoader(),
     *     new Class<?>[] { Service.class },
     *     new LoggingHandler(new RealService())
     * );
     *
     * service.execute();
     *
     * OUTPUT:
     *
     * Before method call
     * Executing service
     * After method call
     *
     * IMPORTANT:
     *
     * java.lang.reflect.Proxy creates proxies for interfaces.
     *
     * It does not directly create subclasses of arbitrary classes.
     *
     * Frameworks such as Spring also use class-based proxying
     * techniques when appropriate.
     *
     *
     * ============================================================
     * 13. PROXY IN SPRING FRAMEWORK
     * ============================================================
     *
     * Proxy is particularly important in Spring.
     *
     * Spring uses proxies to provide additional behavior
     * around method calls.
     *
     * Common examples:
     *
     * 1. Spring AOP
     *
     *    Logging, auditing and other cross-cutting concerns.
     *
     * 2. @Transactional
     *
     *    Transaction management can be applied through
     *    proxy-based method interception.
     *
     * 3. @Cacheable
     *
     *    Cache behavior can be applied through Spring's
     *    caching infrastructure and proxy interception.
     *
     * 4. Method security
     *
     *    Security checks can be applied around method calls.
     *
     *
     * ============================================================
     * 14. SPRING PROXY TYPES
     * ============================================================
     *
     * Spring AOP commonly uses two proxy approaches.
     *
     *
     * A. JDK DYNAMIC PROXY
     *
     * Creates a proxy that implements the target's interfaces.
     *
     * Example:
     *
     * A service implements PaymentService.
     *
     * Spring can create a proxy implementing PaymentService.
     *
     *
     * B. CGLIB-BASED PROXY
     *
     * Creates a subclass-based proxy for a target class.
     *
     * This is useful when class-based proxying is selected
     * or required.
     *
     * IMPORTANT LIMITATIONS:
     *
     * - Final classes cannot be subclassed.
     * - Final methods cannot be overridden.
     * - Private methods cannot be overridden.
     *
     * Therefore, such methods cannot be intercepted through
     * ordinary subclass-based proxy overriding.
     *
     * Spring's proxy selection depends on configuration,
     * framework version and the proxying context.
     *
     *
     * ============================================================
     * 15. IMPORTANT SPRING @TRANSACTIONAL CONCEPT
     * ============================================================
     *
     * Consider:
     *
     * @Service
     * class PaymentService {
     *
     *     @Transactional
     *     public void makePayment() {
     *
     *         System.out.println("Processing payment");
     *     }
     * }
     *
     * Conceptually:
     *
     * Client
     *   |
     *   v
     * Spring Proxy
     *   |
     *   | Begin transaction
     *   v
     * PaymentService.makePayment()
     *   |
     *   | Commit or rollback
     *   v
     * Return to client
     *
     * The proxy intercepts the method call and coordinates
     * transaction behavior.
     *
     * IMPORTANT:
     *
     * In the default proxy-based Spring transaction model,
     * self-invocation does not pass through the proxy.
     *
     * Example:
     *
     * public void firstMethod() {
     *     secondMethod();
     * }
     *
     * @Transactional
     * public void secondMethod() {
     *     // Transactional operation
     * }
     *
     * Calling secondMethod() directly from firstMethod()
     * on the same object normally bypasses proxy interception.
     *
     * Therefore, the transaction advice may not be applied
     * to that self-invocation.
     *
     *
     * ============================================================
     * 16. ADVANTAGES
     * ============================================================
     *
     * 1. Controls access to real objects.
     * 2. Supports lazy initialization.
     * 3. Can add security checks.
     * 4. Can introduce logging and monitoring.
     * 5. Can support caching.
     * 6. Can represent remote objects.
     * 7. Can add behavior without modifying the Real Subject.
     *
     *
     * ============================================================
     * 17. DISADVANTAGES
     * ============================================================
     *
     * 1. Introduces additional classes or runtime proxy objects.
     * 2. Can make debugging more complicated.
     * 3. May introduce method-call overhead.
     * 4. Proxy behavior can become difficult to understand.
     * 5. Dynamic proxies and framework proxies have limitations.
     *
     *
     * ============================================================
     * 18. WHEN SHOULD WE USE PROXY?
     * ============================================================
     *
     * Use Proxy when:
     *
     * - You need to control access to an object.
     * - Object creation is expensive.
     * - You need lazy initialization.
     * - You need logging or access tracking.
     * - You need caching around an object.
     * - You need a local representative for a remote object.
     *
     * Avoid Proxy when:
     *
     * - Direct access is already simple and sufficient.
     * - The extra layer provides no meaningful benefit.
     * - Proxy behavior introduces unnecessary complexity.
     *
     *
     * ============================================================
     * 19. COMMON MISCONCEPTIONS
     * ============================================================
     *
     * 1. Proxy and Adapter are the same.
     *    FALSE. Adapter changes the interface; Proxy controls access.
     *
     * 2. Proxy always delays object creation.
     *    FALSE. That is specifically a Virtual Proxy behavior.
     *
     * 3. Proxy always performs security checks.
     *    FALSE. Protection Proxy does this; other proxies
     *    may handle caching, remoting or lazy loading.
     *
     * 4. Proxy must always create the Real Subject.
     *    FALSE. A remote proxy, for example, may communicate
     *    with an object on another machine.
     *
     * 5. Decorator and Proxy have completely different structures.
     *    FALSE. Their structures can be similar, but their intent
     *    is different.
     *
     * 6. Spring @Transactional directly changes the method.
     *    FALSE. In proxy-based transaction management,
     *    calls are intercepted by a proxy.
     *
     *
     * ============================================================
     * 20. INTERVIEW QUESTIONS
     * ============================================================
     *
     * Q1. What is Proxy Design Pattern?
     *
     * A: It provides a substitute or placeholder for another
     *    object to control access to that object.
     *
     * Q2. What are the main participants?
     *
     * A: Subject, Real Subject, Proxy and Client.
     *
     * Q3. What is a Virtual Proxy?
     *
     * A: A proxy that delays creation of an expensive object
     *    until it is actually needed.
     *
     * Q4. What is a Protection Proxy?
     *
     * A: A proxy that controls access based on permissions.
     *
     * Q5. What is a Remote Proxy?
     *
     * A: A local representative that communicates with
     *    an object in another process or machine.
     *
     * Q6. What is the difference between Proxy and Decorator?
     *
     * A: Proxy primarily controls access; Decorator adds
     *    responsibilities to an object.
     *
     * Q7. What is a Dynamic Proxy in Java?
     *
     * A: A runtime-generated proxy that dispatches interface
     *    method calls through an InvocationHandler.
     *
     * Q8. How does Spring use Proxy?
     *
     * A: Spring uses proxies for features such as AOP,
     *    transaction management, caching and method security.
     *
     * Q9. What is self-invocation in Spring?
     *
     * A: A method calling another method on the same object.
     *    In default proxy-based AOP, that internal call
     *    normally bypasses the proxy.
     *
     *
     * ============================================================
     * 21. QUICK REVISION
     * ============================================================
     *
     * PATTERN:
     * Proxy
     *
     * CATEGORY:
     * Structural Design Pattern
     *
     * PURPOSE:
     * Control access to another object.
     *
     * MAIN IDEA:
     * Place an intermediate object between client and real object.
     *
     * PARTICIPANTS:
     * Subject
     * Real Subject
     * Proxy
     * Client
     *
     * COMMON TYPES:
     * Virtual Proxy
     * Protection Proxy
     * Remote Proxy
     * Caching Proxy
     * Logging Proxy
     * Smart Reference Proxy
     *
     * JAVA:
     * java.lang.reflect.Proxy
     * InvocationHandler
     *
     * SPRING:
     * Spring AOP
     * @Transactional
     * @Cacheable
     * Method Security
     *
     * REMEMBER:
     *
     * Adapter   -> Converts an interface.
     * Decorator -> Adds responsibilities.
     * Facade    -> Simplifies a subsystem.
     * Proxy     -> Controls access to an object.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Proxy Design Pattern , Happy Learning ..!");
    }
}
