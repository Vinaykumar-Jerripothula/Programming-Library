
public class InterfaceSegregationPrinciple {


    /*
     * ============================================================
     * INTERFACE SEGREGATION PRINCIPLE (ISP)
     * ============================================================
     *
     * 1. WHAT IS ISP?
     *
     * ISP is the fourth principle of the SOLID design principles.
     *
     * Definition:
     * Clients should not be forced to depend on interfaces
     * they do not use.
     *
     * This principle was introduced and popularized by
     * Robert C. Martin (Uncle Bob).
     *
     * In simple words:
     *
     * Do not create one large interface containing many unrelated
     * methods when different classes need only a few of them.
     *
     * Instead, split that large interface into smaller,
     * focused interfaces.
     *
     * Each class should implement only the contracts relevant
     * to its actual capabilities.
     *
     *
     * 2. UNDERSTANDING ISP WITH A SIMPLE EXAMPLE
     *
     * Imagine a company has different types of workers:
     *
     *   - Human workers
     *   - Robot workers
     *
     * Both can perform work.
     *
     * But only human workers can eat.
     *
     * Suppose we create this interface:
     *
     * interface Worker {
     *
     *     void work();
     *
     *     void eat();
     * }
     *
     * Now a HumanWorker can implement both methods.
     *
     * But a RobotWorker does not eat.
     *
     * What should RobotWorker do?
     *
     * It may be forced to implement eat() even though eating
     * is not one of its capabilities.
     *
     * It might provide an empty implementation:
     *
     * public void eat() {
     *     // Nothing to do
     * }
     *
     * Or throw UnsupportedOperationException.
     *
     * Both are warning signs that the interface may be too broad.
     *
     *
     * 3. VIOLATION OF ISP
     *
     * Example:
     *
     * interface Worker {
     *
     *     void work();
     *
     *     void eat();
     *
     *     void takeBreak();
     * }
     *
     * class HumanWorker implements Worker {
     *
     *     public void work() {
     *         System.out.println("Human is working");
     *     }
     *
     *     public void eat() {
     *         System.out.println("Human is eating");
     *     }
     *
     *     public void takeBreak() {
     *         System.out.println("Human is taking a break");
     *     }
     * }
     *
     * class RobotWorker implements Worker {
     *
     *     public void work() {
     *         System.out.println("Robot is working");
     *     }
     *
     *     public void eat() {
     *         throw new UnsupportedOperationException();
     *     }
     *
     *     public void takeBreak() {
     *         throw new UnsupportedOperationException();
     *     }
     * }
     *
     * Why is this a problem?
     *
     * RobotWorker is forced to depend on methods that do not
     * represent its actual capabilities.
     *
     * The interface combines multiple responsibilities:
     *
     *   - Working
     *   - Eating
     *   - Taking breaks
     *
     * Not every worker supports every operation.
     *
     * This is a typical ISP violation.
     *
     *
     * 4. APPLYING ISP - BETTER DESIGN
     *
     * Split the large interface into smaller interfaces.
     *
     * Example:
     *
     * interface Workable {
     *
     *     void work();
     * }
     *
     * interface Eatable {
     *
     *     void eat();
     * }
     *
     * interface Restable {
     *
     *     void takeBreak();
     * }
     *
     * Now HumanWorker can implement all three:
     *
     * class HumanWorker implements Workable, Eatable, Restable {
     *
     *     public void work() {
     *         System.out.println("Human is working");
     *     }
     *
     *     public void eat() {
     *         System.out.println("Human is eating");
     *     }
     *
     *     public void takeBreak() {
     *         System.out.println("Human is taking a break");
     *     }
     * }
     *
     * RobotWorker only implements Workable:
     *
     * class RobotWorker implements Workable {
     *
     *     public void work() {
     *         System.out.println("Robot is working");
     *     }
     * }
     *
     * Now:
     *
     *   HumanWorker -> Workable + Eatable + Restable
     *   RobotWorker -> Workable
     *
     * Each class implements only the interfaces it actually needs.
     *
     * This is an example of applying ISP.
     *
     *
     * 5. WHY DOES ISP EXIST?
     *
     * Without ISP, large interfaces can create unnecessary
     * dependencies between unrelated classes.
     *
     * Consider an interface with 20 methods.
     *
     * Class A needs 3 methods.
     * Class B needs 5 methods.
     * Class C needs 7 methods.
     *
     * If all classes must implement the same 20-method interface,
     * they are forced to depend on many irrelevant operations.
     *
     * This can result in:
     *
     *   - Empty method implementations.
     *   - UnsupportedOperationException methods.
     *   - Unnecessary recompilation or changes when the
     *     interface evolves.
     *   - Difficult testing.
     *   - Unclear contracts.
     *
     * ISP reduces these unnecessary dependencies.
     *
     *
     * 6. ISP AND INTERFACE DESIGN
     *
     * An interface defines a contract that its clients or
     * implementations must understand.
     *
     * A well-designed interface should represent a cohesive
     * set of operations.
     *
     * Example:
     *
     * interface Printer {
     *
     *     void print(Document document);
     * }
     *
     * interface Scanner {
     *
     *     void scan(Document document);
     * }
     *
     * interface Fax {
     *
     *     void fax(Document document);
     * }
     *
     * A basic printer only needs Printer.
     *
     * A multifunction printer may implement:
     *
     * Printer + Scanner + Fax
     *
     * This is better than forcing every printer to implement
     * printing, scanning and faxing.
     *
     *
     * 7. ISP AND CLIENTS
     *
     * The word "client" in ISP is important.
     *
     * A client is code that uses an interface.
     *
     * ISP says that a client should not depend on methods
     * it does not need.
     *
     * For example:
     *
     * interface EmployeeOperations {
     *
     *     void viewProfile();
     *
     *     void updateProfile();
     *
     *     void deleteEmployee();
     *
     *     void calculatePayroll();
     * }
     *
     * A profile-viewing component may only need viewProfile().
     *
     * Giving it access to an interface containing deleteEmployee()
     * and calculatePayroll() creates an unnecessarily broad
     * dependency.
     *
     * We can split the contract:
     *
     * interface EmployeeReader {
     *
     *     void viewProfile();
     * }
     *
     * interface EmployeeEditor {
     *
     *     void updateProfile();
     * }
     *
     * interface EmployeeDeleter {
     *
     *     void deleteEmployee();
     * }
     *
     * Components can depend only on the operations they need.
     *
     *
     * 8. ISP AND MULTIPLE INTERFACES IN JAVA
     *
     * Java allows a class to implement multiple interfaces.
     *
     * Example:
     *
     * class HumanWorker implements Workable, Eatable, Restable {
     * }
     *
     * This makes it possible to combine small capabilities
     * into a class without requiring multiple class inheritance.
     *
     * A class does not need to implement every available
     * interface. It implements the contracts relevant to it.
     *
     *
     * 9. ISP IN REAL-WORLD JAVA BACKEND APPLICATIONS
     *
     * Consider an application that manages employee information.
     *
     * Different components may require different operations:
     *
     *   EmployeeReader:
     *     Read employee information.
     *
     *   EmployeeWriter:
     *     Create and update employee information.
     *
     *   EmployeeDeleter:
     *     Delete employee information.
     *
     *   PayrollCalculator:
     *     Calculate payroll.
     *
     * A component that only reads employee data should not
     * be forced to depend on an interface exposing delete
     * and payroll operations.
     *
     * In Spring Boot, services can depend on small interfaces
     * representing the operations they actually require.
     *
     * This can improve testability and make dependencies
     * easier to understand.
     *
     * IMPORTANT:
     * ISP does not require every application to create
     * separate interfaces for every CRUD operation.
     *
     * Group operations when they form a cohesive contract.
     *
     *
     * 10. ISP AND SRP - DIFFERENCE
     *
     * Both principles encourage focused responsibilities,
     * but they focus on different design concerns.
     *
     * SRP:
     * A class should have one reason to change.
     *
     * ISP:
     * Clients should not depend on interface methods they
     * do not use.
     *
     * Example:
     *
     * A large interface may combine printing, scanning
     * and faxing.
     *
     * ISP suggests splitting those contracts.
     *
     * SRP focuses on whether a class has unrelated reasons
     * to change.
     *
     * They are related, but they are not the same principle.
     *
     *
     * 11. ISP AND LSP - DIFFERENCE
     *
     * ISP:
     * Avoid forcing classes or clients to depend on
     * unnecessary interface operations.
     *
     * LSP:
     * Ensure that implementations can safely substitute
     * for their abstractions.
     *
     * A broad interface can lead to implementations that
     * throw UnsupportedOperationException for certain methods.
     *
     * Such implementations may also violate LSP if the
     * interface contract promised those operations.
     *
     *
     * 12. ADVANTAGES OF ISP
     *
     *   1. Smaller and clearer interfaces.
     *
     *   2. Fewer unnecessary dependencies.
     *
     *   3. Easier implementation.
     *
     *   4. Better testability.
     *
     *   5. Improved maintainability.
     *
     *   6. Better separation of capabilities.
     *
     *   7. Less impact when unrelated interface operations change.
     *
     *
     * 13. DISADVANTAGES AND TRADE-OFFS
     *
     *   1. Too many interfaces:
     *      Excessive splitting can make the project difficult
     *      to navigate.
     *
     *   2. More types:
     *      Every interface introduces another contract to maintain.
     *
     *   3. Poor grouping:
     *      Splitting methods without understanding their
     *      relationships can make the design less clear.
     *
     *   4. Unnecessary abstraction:
     *      Small applications may not need many separate
     *      interfaces.
     *
     *
     * 14. WHEN SHOULD WE APPLY ISP?
     *
     * Consider ISP when:
     *
     *   - An interface has many unrelated methods.
     *   - Implementations contain empty method bodies.
     *   - Implementations throw UnsupportedOperationException
     *     for methods they cannot support.
     *   - A client depends on a large interface but uses only
     *     a small part of it.
     *   - Unrelated interface changes affect many classes.
     *
     *
     * 15. COMMON MISCONCEPTIONS
     *
     * Misconception 1:
     * Every interface should contain only one method.
     *
     * Reality:
     * An interface can contain multiple methods when they
     * form one cohesive contract.
     *
     * Misconception 2:
     * A class should implement only one interface.
     *
     * Reality:
     * Java supports implementing multiple interfaces.
     *
     * Misconception 3:
     * ISP means creating as many interfaces as possible.
     *
     * Reality:
     * Split interfaces according to meaningful capabilities
     * and client needs.
     *
     * Misconception 4:
     * ISP applies only to classes implementing interfaces.
     *
     * Reality:
     * It also concerns clients that depend on interfaces.
     *
     * Misconception 5:
     * A large interface is always wrong.
     *
     * Reality:
     * A large interface can be appropriate when its methods
     * form one cohesive contract and its clients need that
     * complete contract.
     *
     *
     * 16. INTERVIEW QUESTIONS
     *
     * Q1. What is Interface Segregation Principle?
     * A. Clients should not be forced to depend on interfaces
     *    they do not use.
     *
     * Q2. Who popularized ISP?
     * A. Robert C. Martin.
     *
     * Q3. What is the main goal of ISP?
     * A. To create focused interfaces and eliminate
     *    unnecessary dependencies.
     *
     * Q4. Does ISP mean one method per interface?
     * A. No. Related methods can belong to one interface.
     *
     * Q5. What is a fat interface?
     * A. An interface containing too many unrelated operations
     *    for its clients or implementations.
     *
     * Q6. How does ISP relate to LSP?
     * A. Smaller capability-based interfaces can prevent
     *    implementations from being forced to support
     *    operations they cannot honor.
     *
     * Q7. Can a class implement multiple small interfaces?
     * A. Yes. Java supports multiple interface implementation.
     *
     * Q8. What is a practical ISP example?
     * A. Separating Printer, Scanner and Fax capabilities
     *    instead of forcing every device to support all three.
     *
     *
     * 17. QUICK REVISION
     *
     *   - ISP is the fourth SOLID principle.
     *   - Popularized by Robert C. Martin.
     *   - Clients should not depend on methods they do not use.
     *   - Split large interfaces into cohesive contracts.
     *   - Classes implement only relevant capabilities.
     *   - Avoid empty or unsupported method implementations.
     *   - Avoid creating unnecessary tiny interfaces.
     *
     * CORE IDEA:
     *
     * Give each client a focused interface containing only
     * the operations it actually needs.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Interface-Segregation Principle , Happy Learning ..!");
    }
}
