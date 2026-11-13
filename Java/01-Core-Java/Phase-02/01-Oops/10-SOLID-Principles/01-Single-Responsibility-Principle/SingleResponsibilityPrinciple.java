
public class SingleResponsibilityPrinciple {

    

    /*
     * ============================================================
     * SINGLE RESPONSIBILITY PRINCIPLE (SRP)
     * ============================================================
     *
     * 1. WHAT IS SRP?
     *
     * SRP is the first principle of the SOLID design principles.
     *
     * Definition:
     * A class should have only one reason to change.
     *
     * This principle was popularized by Robert C. Martin
     * (Uncle Bob).
     *
     * IMPORTANT:
     * SRP does NOT mean that a class must contain only one method.
     * It does NOT mean that a class should perform only one
     * individual operation.
     *
     * It means that all the responsibilities of a class should
     * belong to one cohesive area of responsibility.
     *
     * In simple words:
     * A class should have one well-defined job, and changes to
     * that job should be the reason for modifying the class.
     *
     *
     * 2. UNDERSTANDING "ONE REASON TO CHANGE"
     *
     * Imagine an Employee class in a company application.
     *
     * It handles:
     *   1. Employee information.
     *   2. Salary calculation.
     *   3. Saving employee details to a database.
     *   4. Generating employee reports.
     *
     * Initially, this may appear convenient because everything
     * related to an employee is in one class.
     *
     * But consider what happens when requirements change:
     *
     *   - HR changes employee information requirements.
     *   - Finance changes salary calculation rules.
     *   - Database team changes the database technology.
     *   - Management changes report formatting.
     *
     * Each change affects the same Employee class.
     *
     * The class now has multiple independent reasons to change.
     *
     * This is a violation of SRP.
     *
     * A better design separates these responsibilities:
     *
     *   Employee          -> Employee information
     *   SalaryCalculator  -> Salary calculation
     *   EmployeeRepository-> Employee persistence
     *   EmployeeReport    -> Report generation
     *
     * Now each class has a focused responsibility.
     *
     *
     * 3. WHY DOES SRP EXIST?
     *
     * Without SRP, a class can become responsible for unrelated
     * business rules and technical operations.
     *
     * This creates several problems:
     *
     *   1. Difficult maintenance:
     *      A small change in one responsibility may affect
     *      unrelated functionality.
     *
     *   2. Difficult testing:
     *      Testing one behavior may require setting up
     *      dependencies used by other behaviors.
     *
     *   3. High coupling:
     *      Changes in one area can affect other areas.
     *
     *   4. Poor reusability:
     *      A class containing unrelated operations is harder
     *      to reuse in another part of the application.
     *
     *   5. Difficult collaboration:
     *      Different developers working on unrelated features
     *      may need to modify the same class.
     *
     * SRP helps keep responsibilities cohesive and changes
     * localized.
     *
     *
     * 4. VIOLATION OF SRP - EXAMPLE
     *
     * Consider this example:
     *
     * class Employee {
     *
     *     private int id;
     *     private String name;
     *     private double salary;
     *
     *     public void calculateSalary() {
     *         // Salary calculation logic
     *     }
     *
     *     public void saveToDatabase() {
     *         // Database insertion logic
     *     }
     *
     *     public void generateReport() {
     *         // Report generation logic
     *     }
     * }
     *
     * Why is this a problem?
     *
     * Because Employee is handling three different concerns:
     *
     *   - Business logic: salary calculation
     *   - Data persistence: saving to a database
     *   - Presentation/reporting: generating reports
     *
     * These responsibilities change for different reasons.
     *
     * Therefore, the class has multiple reasons to change.
     *
     *
     * 5. APPLYING SRP - IMPROVED DESIGN
     *
     * Separate the responsibilities into focused classes:
     *
     * class Employee {
     *
     *     private int id;
     *     private String name;
     *     private double salary;
     *
     *     // Constructor, getters and setters
     * }
     *
     * class SalaryCalculator {
     *
     *     public double calculateSalary(Employee employee) {
     *         // Salary calculation logic
     *         return employee.getSalary();
     *     }
     * }
     *
     * class EmployeeRepository {
     *
     *     public void save(Employee employee) {
     *         // Database persistence logic
     *     }
     * }
     *
     * class EmployeeReport {
     *
     *     public void generate(Employee employee) {
     *         // Report generation logic
     *     }
     * }
     *
     * Now:
     *
     *   Employee           -> Holds employee information.
     *   SalaryCalculator   -> Calculates salary.
     *   EmployeeRepository -> Saves employee data.
     *   EmployeeReport     -> Generates employee reports.
     *
     * Each class has a focused responsibility.
     *
     *
     * 6. DOES ONE RESPONSIBILITY MEAN ONE METHOD?
     *
     * NO!
     *
     * A class can contain multiple methods and still follow SRP.
     *
     * For example:
     *
     * class EmployeeRepository {
     *
     *     public void save(Employee employee) {
     *         // Save employee
     *     }
     *
     *     public Employee findById(int id) {
     *         // Find employee
     *         return null;
     *     }
     *
     *     public void update(Employee employee) {
     *         // Update employee
     *     }
     *
     *     public void delete(int id) {
     *         // Delete employee
     *     }
     * }
     *
     * This class has multiple methods, but they all belong to
     * one cohesive responsibility: employee persistence.
     *
     * Therefore, multiple methods do not automatically violate SRP.
     *
     *
     * 7. SRP AND COHESION
     *
     * Cohesion describes how closely related the responsibilities
     * and operations inside a module or class are.
     *
     * High cohesion:
     *   The methods and data work together toward one focused purpose.
     *
     * Low cohesion:
     *   The class contains unrelated responsibilities.
     *
     * SRP encourages high cohesion.
     *
     * Example:
     *
     * EmployeeRepository:
     *   save(), findById(), update(), delete()
     *
     * These operations are closely related.
     *
     * Employee:
     *   calculateSalary(), sendEmail(), saveToDatabase(),
     *   generatePDF()
     *
     * These operations belong to different concerns.
     *
     *
     * 8. SRP AND COUPLING
     *
     * Coupling describes how strongly one component depends
     * on other components.
     *
     * Separating responsibilities can reduce unnecessary
     * dependencies between unrelated parts of an application.
     *
     * However:
     * SRP does not automatically guarantee low coupling.
     *
     * A poorly designed system can have many tiny classes
     * that are still tightly coupled.
     *
     * Good design aims for:
     *
     *   - High cohesion within a class.
     *   - Appropriate, manageable coupling between classes.
     *
     *
     * 9. SRP IN REAL-WORLD JAVA BACKEND APPLICATIONS
     *
     * Consider a Spring Boot application.
     *
     * A typical separation might look like this:
     *
     * EmployeeController:
     *   Handles HTTP requests and responses.
     *
     * EmployeeService:
     *   Handles employee-related business logic.
     *
     * EmployeeRepository:
     *   Handles database access.
     *
     * Employee:
     *   Represents employee data and domain state.
     *
     * EmployeeMapper:
     *   Converts between entities and DTOs.
     *
     * EmployeeEmailService:
     *   Handles employee-related email operations.
     *
     * This separation helps prevent a single class from
     * becoming responsible for the entire application workflow.
     *
     * IMPORTANT:
     * These class names are common architectural conventions.
     * SRP does not mandate that every application must have
     * exactly these classes.
     *
     *
     * 10. COMMON MISCONCEPTIONS
     *
     * Misconception 1:
     * A class must have only one method.
     *
     * Reality:
     * A class can have many methods as long as they belong
     * to one cohesive responsibility.
     *
     * Misconception 2:
     * Every method should be moved into a separate class.
     *
     * Reality:
     * Excessive splitting can create unnecessary complexity.
     * Group related behavior together.
     *
     * Misconception 3:
     * A class containing multiple fields violates SRP.
     *
     * Reality:
     * Multiple fields are perfectly acceptable when they
     * support the same responsibility.
     *
     * Misconception 4:
     * SRP means one class per database table.
     *
     * Reality:
     * SRP is about responsibility and reasons to change,
     * not about database table count.
     *
     * Misconception 5:
     * SRP guarantees that a class never changes for multiple
     * reasons in the future.
     *
     * Reality:
     * Requirements evolve. Responsibilities must be reviewed
     * as the application grows.
     *
     *
     * 11. WHEN SHOULD WE APPLY SRP?
     *
     * Consider separating a class when:
     *
     *   - It handles unrelated business and technical concerns.
     *   - Different teams or stakeholders request changes to
     *     different parts of the class.
     *   - Changes in one feature repeatedly break another.
     *   - The class is difficult to test because of unrelated
     *     dependencies.
     *   - The class has become difficult to understand and maintain.
     *
     *
     * 12. WHEN NOT TO OVERAPPLY SRP
     *
     * Do not create a new class for every tiny operation.
     *
     * For example, separating every simple calculation into
     * its own class may make the code harder to navigate.
     *
     * Keep closely related operations together.
     *
     * The goal is not to maximize the number of classes.
     * The goal is to organize responsibilities meaningfully.
     *
     *
     * 13. INTERVIEW QUESTIONS
     *
     * Q1. What is the Single Responsibility Principle?
     * A. A class should have one reason to change.
     *
     * Q2. Who popularized SRP?
     * A. Robert C. Martin (Uncle Bob).
     *
     * Q3. Does SRP mean one method per class?
     * A. No. A class can have multiple methods that support
     *    one cohesive responsibility.
     *
     * Q4. What is the main benefit of SRP?
     * A. Better maintainability, cohesion, testability and
     *    separation of concerns.
     *
     * Q5. Is SRP related to cohesion?
     * A. Yes. SRP encourages high cohesion.
     *
     * Q6. Does SRP automatically guarantee loose coupling?
     * A. No. It helps separate responsibilities, but coupling
     *    must also be considered independently.
     *
     * Q7. Can a class have multiple reasons to change even
     *     if it has only a few methods?
     * A. Yes. Method count does not determine responsibility.
     *
     * Q8. Is a Controller-Service-Repository architecture
     *     an example of applying SRP?
     * A. It can be. Each layer handles a different concern,
     *    provided the actual implementation respects those
     *    responsibilities.
     *
     *
     * 14. QUICK REVISION
     *
     *   - SRP is the first SOLID principle.
     *   - Popularized by Robert C. Martin.
     *   - One class should have one reason to change.
     *   - It is not a one-method-per-class rule.
     *   - It encourages high cohesion.
     *   - It helps localize changes.
     *   - Separate unrelated responsibilities.
     *   - Avoid creating unnecessary classes.
     *
     * CORE IDEA:
     *
     * If different responsibilities change for different
     * reasons, consider separating them into different classes.
     *
     * ============================================================
     */
    public static void main(String[] args) {
        System.out.println("The above is all about Single Responsibility Principle , Happy Learning ..!");
    }
}
