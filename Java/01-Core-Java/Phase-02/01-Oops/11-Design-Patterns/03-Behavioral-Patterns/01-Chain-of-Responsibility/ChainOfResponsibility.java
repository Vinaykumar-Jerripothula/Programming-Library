

public class ChainOfResponsibility {

    /*
     * ============================================================
     * 1. INTRODUCTION
     * ============================================================
     *
     * Imagine you are working on an online shopping application.
     *
     * A customer requests a refund of ₹50,000.
     *
     * Should every refund be approved by the same person?
     *
     * Not necessarily.
     *
     * Consider these approval rules:
     *
     *   - Team Leader can approve up to ₹5,000.
     *   - Manager can approve up to ₹20,000.
     *   - Director can approve up to ₹1,00,000.
     *
     * When a refund request arrives:
     *
     *   1. First, send it to the Team Leader.
     *   2. If the Team Leader cannot approve it, forward it
     *      to the Manager.
     *   3. If the Manager cannot approve it, forward it
     *      to the Director.
     *
     * The request travels through a chain of handlers.
     *
     * Each handler either processes the request or passes it
     * to the next handler.
     *
     * This is the Chain of Responsibility pattern.
     *
     *
     * DEFINITION:
     *
     * Chain of Responsibility is a behavioral design pattern
     * that passes a request along a chain of potential handlers
     * until one of them handles the request or the chain ends.
     *
     *
     * SIMPLE DEFINITION:
     *
     * Give multiple objects a chance to handle a request
     * without making the sender know which object will handle it.
     *
     *
     * ============================================================
     * 2. WHY DO WE NEED THIS PATTERN?
     * ============================================================
     *
     * Consider writing approval logic directly in one method:
     *
     * if (amount <= 5000) {
     *     // Team Leader approves
     * } else if (amount <= 20000) {
     *     // Manager approves
     * } else if (amount <= 100000) {
     *     // Director approves
     * }
     *
     * This may work initially.
     *
     * But what happens when:
     *
     *   - A new approval level is introduced?
     *   - Approval limits change?
     *   - Different requests need different handlers?
     *   - The approval workflow becomes more complex?
     *
     * The central method keeps growing.
     *
     * Instead, each approval level can become an independent
     * handler.
     *
     * Each handler knows only:
     *
     *   1. How to process a request it can handle.
     *   2. Which handler comes next.
     *
     * The sender does not need to know the entire chain.
     *
     *
     * ============================================================
     * 3. REAL-WORLD ANALOGY
     * ============================================================
     *
     * Imagine submitting a leave request in a company.
     *
     * The request may pass through:
     *
     * Employee
     *    |
     *    v
     * Team Leader
     *    |
     *    v
     * Manager
     *    |
     *    v
     * HR
     *
     * Depending on the request and company rules,
     * one of these people may approve, reject or forward it.
     *
     * The employee does not need to manually decide
     * which person should handle every request.
     *
     * The workflow manages the chain.
     *
     *
     * ============================================================
     * 4. STRUCTURE AND PARTICIPANTS
     * ============================================================
     *
     * Main participants:
     *
     * 1. Handler:
     *
     *    Defines a common interface for handling requests.
     *    It may also maintain a reference to the next handler.
     *
     * 2. Concrete Handler:
     *
     *    Implements the handling logic.
     *    It either processes the request or forwards it.
     *
     * 3. Client:
     *
     *    Creates or configures the chain and sends requests
     *    to its starting handler.
     *
     *
     * BASIC STRUCTURE:
     *
     *       Client
     *          |
     *          v
     *       Handler 1
     *          |
     *          v
     *       Handler 2
     *          |
     *          v
     *       Handler 3
     *          |
     *          v
     *       End of chain
     *
     *
     * ============================================================
     * 5. COMPLETE JAVA IMPLEMENTATION
     * ============================================================
     *
     * We will implement the refund approval example.
     *
     *
     * STEP 1: Create the Handler abstraction.
     *
     * abstract class RefundHandler {
     *
     *     protected RefundHandler nextHandler;
     *
     *     public void setNextHandler(RefundHandler nextHandler) {
     *         this.nextHandler = nextHandler;
     *     }
     *
     *     public abstract void handleRequest(double amount);
     * }
     *
     * Every handler follows the same contract.
     *
     * The nextHandler reference connects one handler
     * to another.
     *
     *
     * STEP 2: Create the Team Leader handler.
     *
     * class TeamLeader extends RefundHandler {
     *
     *     @Override
     *     public void handleRequest(double amount) {
     *
     *         if (amount <= 5000) {
     *
     *             System.out.println(
     *                 "Team Leader approved refund: ₹" + amount
     *             );
     *
     *         } else if (nextHandler != null) {
     *
     *             nextHandler.handleRequest(amount);
     *
     *         } else {
     *
     *             System.out.println("No handler available");
     *         }
     *     }
     * }
     *
     *
     * STEP 3: Create the Manager handler.
     *
     * class Manager extends RefundHandler {
     *
     *     @Override
     *     public void handleRequest(double amount) {
     *
     *         if (amount <= 20000) {
     *
     *             System.out.println(
     *                 "Manager approved refund: ₹" + amount
     *             );
     *
     *         } else if (nextHandler != null) {
     *
     *             nextHandler.handleRequest(amount);
     *
     *         } else {
     *
     *             System.out.println("No handler available");
     *         }
     *     }
     * }
     *
     *
     * STEP 4: Create the Director handler.
     *
     * class Director extends RefundHandler {
     *
     *     @Override
     *     public void handleRequest(double amount) {
     *
     *         if (amount <= 100000) {
     *
     *             System.out.println(
     *                 "Director approved refund: ₹" + amount
     *             );
     *
     *         } else {
     *
     *             System.out.println(
     *                 "Refund rejected: Amount exceeds limit"
     *             );
     *         }
     *     }
     * }
     *
     *
     * STEP 5: Configure and use the chain.
     *
     * RefundHandler teamLeader = new TeamLeader();
     * RefundHandler manager = new Manager();
     * RefundHandler director = new Director();
     *
     * teamLeader.setNextHandler(manager);
     * manager.setNextHandler(director);
     *
     * teamLeader.handleRequest(3000);
     * teamLeader.handleRequest(15000);
     * teamLeader.handleRequest(75000);
     * teamLeader.handleRequest(150000);
     *
     *
     * OUTPUT:
     *
     * Team Leader approved refund: ₹3000.0
     * Manager approved refund: ₹15000.0
     * Director approved refund: ₹75000.0
     * Refund rejected: Amount exceeds limit
     *
     *
     * ============================================================
     * 6. UNDERSTANDING THE EXECUTION FLOW
     * ============================================================
     *
     * Consider:
     *
     * teamLeader.handleRequest(15000);
     *
     * STEP 1:
     *
     * The request reaches TeamLeader.
     *
     * TeamLeader checks:
     *
     * amount <= 5000
     *
     * 15000 <= 5000 is FALSE.
     *
     * So it forwards the request to Manager.
     *
     *
     * STEP 2:
     *
     * Manager receives the request.
     *
     * Manager checks:
     *
     * amount <= 20000
     *
     * 15000 <= 20000 is TRUE.
     *
     * Manager approves the request.
     *
     *
     * STEP 3:
     *
     * The request is handled.
     *
     * It does not reach Director.
     *
     *
     * IMPORTANT:
     *
     * A handler does not have to forward every request.
     *
     * It can:
     *
     *   - Handle the request.
     *   - Forward the request.
     *   - Reject the request.
     *   - Stop processing.
     *
     * The exact behavior depends on the application's rules.
     *
     *
     * ============================================================
     * 7. IMPORTANT: THE REQUEST MAY REACH NO HANDLER
     * ============================================================
     *
     * Not every request is guaranteed to be handled.
     *
     * Consider a refund of ₹2,00,000.
     *
     * Team Leader cannot approve it.
     *
     * Manager cannot approve it.
     *
     * Director cannot approve it.
     *
     * The chain ends without approval.
     *
     * The application must define what happens in this case.
     *
     * Possible approaches:
     *
     *   - Reject the request.
     *   - Return an unhandled result.
     *   - Throw an exception.
     *   - Forward it to another handler.
     *
     * This is an important design decision.
     *
     *
     * ============================================================
     * 8. TWO COMMON CHAIN IMPLEMENTATION STYLES
     * ============================================================
     *
     * A. PASS-ALONG STYLE
     *
     * Each handler decides whether it can handle the request.
     *
     * If it cannot:
     *
     *     nextHandler.handleRequest(request);
     *
     * If it can:
     *
     *     // Process request
     *
     *
     * B. CENTRALIZED FORWARDING STYLE
     *
     * The base Handler provides a common method to forward
     * a request when a subclass does not handle it.
     *
     * Example:
     *
     * abstract class Handler {
     *
     *     protected Handler next;
     *
     *     public Handler setNext(Handler next) {
     *         this.next = next;
     *         return next;
     *     }
     *
     *     public void handle(Request request) {
     *
     *         if (next != null) {
     *             next.handle(request);
     *         }
     *     }
     * }
     *
     * Concrete handlers can process requests and call
     * super.handle(request) when forwarding is required.
     *
     * Both styles can implement Chain of Responsibility.
     *
     *
     * ============================================================
     * 9. CHAIN OF RESPONSIBILITY VS DECORATOR
     * ============================================================
     *
     * Both patterns can involve multiple objects wrapping
     * or referring to other objects.
     *
     * But their intentions differ.
     *
     * DECORATOR:
     *
     * Adds responsibilities to an object.
     *
     * Example:
     *
     * Adding compression and encryption to a data stream.
     *
     *
     * CHAIN OF RESPONSIBILITY:
     *
     * Passes a request through potential handlers.
     *
     * Example:
     *
     * Passing a refund request through approval levels.
     *
     *
     * REMEMBER:
     *
     * Decorator -> Adds behavior.
     *
     * Chain of Responsibility -> Passes requests through handlers.
     *
     *
     * ============================================================
     * 10. CHAIN OF RESPONSIBILITY VS COMMAND
     * ============================================================
     *
     * COMMAND:
     *
     * Encapsulates a request as an object.
     *
     * Example:
     *
     * A PlaceOrderCommand object represents an order operation.
     *
     *
     * CHAIN OF RESPONSIBILITY:
     *
     * Routes a request through multiple potential handlers.
     *
     * Example:
     *
     * A refund request passes through approval handlers.
     *
     * These patterns can also work together:
     *
     * A Command object can be passed through a chain
     * of handlers.
     *
     *
     * ============================================================
     * 11. CHAIN OF RESPONSIBILITY IN JAVA
     * ============================================================
     *
     * Java's exception-handling mechanism has a chain-like
     * propagation behavior.
     *
     * If a method cannot handle an exception, the exception
     * can propagate to its caller.
     *
     * Example:
     *
     * try {
     *     // Some operation
     * } catch (IOException e) {
     *     // Handle IOException
     * }
     *
     * If the current method does not catch the exception,
     * it may propagate up the call stack to a caller.
     *
     * IMPORTANT:
     *
     * Exception propagation is a useful chain-like analogy.
     * It is not necessarily a direct implementation of the
     * classic GoF Chain of Responsibility structure.
     *
     *
     * ============================================================
     * 12. CHAIN OF RESPONSIBILITY IN SERVLET FILTERS
     * ============================================================
     *
     * Servlet filters are a practical example of chained
     * request processing.
     *
     * A request may pass through:
     *
     * Client
     *   |
     *   v
     * Authentication Filter
     *   |
     *   v
     * Logging Filter
     *   |
     *   v
     * Authorization Filter
     *   |
     *   v
     * Servlet
     *
     * Each filter can:
     *
     *   - Perform processing before the next component.
     *   - Modify the request or response.
     *   - Stop the chain.
     *   - Pass control to the next filter.
     *
     * A simplified Servlet Filter call:
     *
     * chain.doFilter(request, response);
     *
     * This allows the request to continue through
     * the remaining filters.
     *
     *
     * ============================================================
     * 13. CHAIN OF RESPONSIBILITY IN SPRING SECURITY
     * ============================================================
     *
     * Spring Security uses a filter-chain architecture.
     *
     * An incoming HTTP request passes through a configured
     * sequence of security filters.
     *
     * Examples of responsibilities include:
     *
     *   - Authentication.
     *   - Authorization.
     *   - Security context handling.
     *   - Exception translation.
     *   - Request protection.
     *
     * A filter may process the request, pass it along,
     * or stop the chain.
     *
     * This is closely related to the Chain of Responsibility
     * idea.
     *
     * IMPORTANT:
     *
     * Spring Security's filter architecture has its own
     * detailed rules and lifecycle. Do not assume every
     * filter behaves exactly like the simple refund example.
     *
     *
     * ============================================================
     * 14. ADVANTAGES
     * ============================================================
     *
     * 1. Reduces coupling between sender and receiver.
     *
     * 2. Multiple handlers can process the same request type.
     *
     * 3. The chain can be configured or reordered.
     *
     * 4. New handlers can often be introduced without
     *    changing the client.
     *
     * 5. Each handler can focus on one responsibility.
     *
     * 6. Supports flexible request-processing workflows.
     *
     *
     * ============================================================
     * 15. DISADVANTAGES
     * ============================================================
     *
     * 1. A request may travel through many handlers.
     *
     * 2. A request may reach the end without being handled.
     *
     * 3. Debugging the complete flow can be difficult.
     *
     * 4. Incorrect chain configuration can cause unexpected
     *    behavior.
     *
     * 5. A circular chain can cause infinite recursion
     *    in recursive implementations.
     *
     * 6. The order of handlers may affect the result.
     *
     *
     * ============================================================
     * 16. WHEN SHOULD WE USE THIS PATTERN?
     * ============================================================
     *
     * Use Chain of Responsibility when:
     *
     *   - Multiple handlers may process a request.
     *   - The sender should not know the exact receiver.
     *   - Processing follows an ordered sequence.
     *   - Handlers should be independently configurable.
     *   - A request may be handled or rejected at different stages.
     *
     * Avoid it when:
     *
     *   - There is only one fixed handler.
     *   - The chain adds unnecessary complexity.
     *   - Every request must follow a rigid workflow
     *     better represented by another design.
     *
     *
     * ============================================================
     * 17. COMMON MISCONCEPTIONS
     * ============================================================
     *
     * 1. Every handler must process the request.
     *    FALSE. A handler may forward or reject it.
     *
     * 2. Every request must be handled.
     *    FALSE. A request can reach the end of the chain.
     *
     * 3. The client must know every handler.
     *    FALSE. The client can send the request to the
     *    first handler.
     *
     * 4. The chain must be created using inheritance.
     *    FALSE. It can also be implemented using composition
     *    and interfaces.
     *
     * 5. Handler order does not matter.
     *    FALSE. Order can affect which handler processes
     *    a request.
     *
     * 6. Servlet filters and Spring Security filters are
     *    identical to the textbook GoF implementation.
     *    FALSE. They are practical chain-based architectures
     *    with framework-specific behavior.
     *
     *
     * ============================================================
     * 18. INTERVIEW QUESTIONS
     * ============================================================
     *
     * Q1. What is Chain of Responsibility?
     *
     * A: A behavioral pattern that passes a request through
     *    a chain of potential handlers until it is handled
     *    or the chain ends.
     *
     * Q2. What are its main participants?
     *
     * A: Handler, Concrete Handler and Client.
     *
     * Q3. What is the role of the next handler reference?
     *
     * A: It connects the current handler to the next handler.
     *
     * Q4. Does every handler process a request?
     *
     * A: No. It may handle, forward or reject the request.
     *
     * Q5. Can a request remain unhandled?
     *
     * A: Yes. The chain can end without any handler
     *    processing the request.
     *
     * Q6. Give a Java-related example.
     *
     * A: Servlet filters provide a practical chain-based
     *    request-processing architecture.
     *
     * Q7. How does Spring Security use this idea?
     *
     * A: HTTP requests pass through a configured security
     *    filter chain.
     *
     * Q8. What is the difference between Chain of Responsibility
     *    and Decorator?
     *
     * A: Chain of Responsibility forwards requests to potential
     *    handlers; Decorator adds responsibilities to an object.
     *
     *
     * ============================================================
     * 19. QUICK REVISION
     * ============================================================
     *
     * PATTERN:
     * Chain of Responsibility
     *
     * CATEGORY:
     * Behavioral Design Pattern
     *
     * PURPOSE:
     * Pass requests through multiple potential handlers.
     *
     * MAIN IDEA:
     * Each handler either processes the request or forwards it.
     *
     * PARTICIPANTS:
     * Handler
     * Concrete Handler
     * Client
     *
     * EXAMPLES:
     * Approval workflows
     * Servlet filters
     * Spring Security filter chain
     *
     * REMEMBER:
     *
     * Client -> First Handler -> Next Handler -> Next Handler
     *
     * The request stops when it is handled, rejected,
     * or reaches the end of the chain.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Chain of Responsibility Design Pattern , Happy Learning ..!");
    }
}
