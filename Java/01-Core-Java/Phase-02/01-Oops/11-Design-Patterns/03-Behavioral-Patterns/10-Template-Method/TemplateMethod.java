
public class TemplateMethod {

    /*
     * ============================================================
     * 1. INTRODUCTION
     * ============================================================
     *
     * Imagine you are developing an application that generates
     * different types of reports.
     *
     * You need to generate:
     *
     * - PDF Report
     * - Excel Report
     * - CSV Report
     *
     * Every report follows almost the same sequence:
     *
     * 1. Fetch data.
     * 2. Process data.
     * 3. Format data.
     * 4. Export the report.
     *
     * However, the implementation of some steps differs.
     *
     * For example:
     *
     * PDF  -> Export as PDF.
     * Excel -> Export as Excel.
     * CSV  -> Export as CSV.
     *
     * We could implement the entire workflow separately
     * in every report class.
     *
     * But this would duplicate the common workflow.
     *
     * What if we define the overall workflow once
     * in a parent class and allow subclasses to customize
     * only the required steps?
     *
     * This is the idea behind Template Method.
     *
     *
     * DEFINITION:
     *
     * Template Method is a behavioral design pattern that
     * defines the skeleton of an algorithm in a superclass,
     * allowing subclasses to override specific steps
     * without changing the overall algorithm structure.
     *
     *
     * SIMPLE DEFINITION:
     *
     * Define the overall algorithm once in a parent class
     * and let child classes customize selected steps.
     *
     *
     * ============================================================
     * 2. WHY DO WE NEED TEMPLATE METHOD?
     * ============================================================
     *
     * Consider these classes:
     *
     * class PdfReport {
     *
     *     public void generateReport() {
     *
     *         fetchData();
     *         processData();
     *         formatData();
     *         exportPdf();
     *     }
     * }
     *
     *
     * class ExcelReport {
     *
     *     public void generateReport() {
     *
     *         fetchData();
     *         processData();
     *         formatData();
     *         exportExcel();
     *     }
     * }
     *
     *
     * class CsvReport {
     *
     *     public void generateReport() {
     *
     *         fetchData();
     *         processData();
     *         formatData();
     *         exportCsv();
     *     }
     * }
     *
     *
     * PROBLEMS:
     *
     * 1. The same workflow is repeated in multiple classes.
     *
     * 2. Changes to the workflow must be made in multiple places.
     *
     * 3. Different subclasses may accidentally execute
     *    the steps in different orders.
     *
     * 4. Maintaining common behavior becomes difficult.
     *
     *
     * TEMPLATE METHOD SOLUTION:
     *
     * Put the common algorithm structure in an abstract class.
     *
     * Let subclasses implement only the steps that vary.
     *
     * This avoids duplicating the overall workflow.
     *
     *
     * ============================================================
     * 3. REAL-WORLD ANALOGY
     * ============================================================
     *
     * Think about preparing tea and coffee.
     *
     * Both follow a similar process:
     *
     * 1. Boil water.
     * 2. Add the main ingredient.
     * 3. Pour into a cup.
     * 4. Add optional ingredients.
     *
     *
     * TEA:
     *
     * - Add tea leaves.
     * - Add lemon or sugar if required.
     *
     *
     * COFFEE:
     *
     * - Add coffee powder.
     * - Add milk or sugar if required.
     *
     *
     * The overall preparation process is similar,
     * but certain steps differ.
     *
     * We can define the common preparation algorithm once
     * and allow tea and coffee to customize individual steps.
     *
     *
     * ============================================================
     * 4. STRUCTURE AND PARTICIPANTS
     * ============================================================
     *
     * Main participants:
     *
     * 1. Abstract Class:
     *
     *    Defines the template method containing
     *    the overall algorithm.
     *
     *    It also declares abstract or overridable steps.
     *
     *
     * 2. Concrete Classes:
     *
     *    Implement or customize the steps defined
     *    by the abstract class.
     *
     *
     * STRUCTURE:
     *
     *             AbstractClass
     *                  |
     *                  |
     *          templateMethod()
     *                  |
     *          ------------------
     *          |        |       |
     *          v        v       v
     *        Step1    Step2   Step3
     *
     *                  |
     *          ------------------
     *          |                |
     *          v                v
     *      ConcreteA        ConcreteB
     *          |                |
     *     Implements       Implements
     *      Steps             Steps
     *
     *
     * ============================================================
     * 5. COMPLETE JAVA IMPLEMENTATION
     * ============================================================
     *
     * Example: Tea and Coffee Preparation.
     *
     *
     * STEP 1: Create the Abstract Class.
     *
     * abstract class Beverage {
     *
     *     // Template Method
     *     public final void prepareBeverage() {
     *
     *         boilWater();
     *
     *         addMainIngredient();
     *
     *         pourIntoCup();
     *
     *         if (customerWantsExtras()) {
     *
     *             addExtras();
     *         }
     *     }
     *
     *     // Common implementation
     *     private void boilWater() {
     *
     *         System.out.println("Boiling water");
     *     }
     *
     *     // Steps customized by subclasses
     *     protected abstract void addMainIngredient();
     *
     *     private void pourIntoCup() {
     *
     *         System.out.println("Pouring into cup");
     *     }
     *
     *     protected abstract void addExtras();
     *
     *     // Hook method
     *     protected boolean customerWantsExtras() {
     *
     *         return true;
     *     }
     * }
     *
     *
     * IMPORTANT:
     *
     * prepareBeverage() defines the complete algorithm.
     *
     * Its steps must execute in the defined order.
     *
     * The method is final so subclasses cannot override
     * the overall workflow.
     *
     *
     * STEP 2: Create the Tea class.
     *
     * class Tea extends Beverage {
     *
     *     @Override
     *     protected void addMainIngredient() {
     *
     *         System.out.println("Adding tea leaves");
     *     }
     *
     *     @Override
     *     protected void addExtras() {
     *
     *         System.out.println("Adding lemon");
     *     }
     * }
     *
     *
     * STEP 3: Create the Coffee class.
     *
     * class Coffee extends Beverage {
     *
     *     @Override
     *     protected void addMainIngredient() {
     *
     *         System.out.println("Adding coffee powder");
     *     }
     *
     *     @Override
     *     protected void addExtras() {
     *
     *         System.out.println("Adding milk and sugar");
     *     }
     *
     *     @Override
     *     protected boolean customerWantsExtras() {
     *
     *         return false;
     *     }
     * }
     *
     *
     * STEP 4: Client code.
     *
     * Beverage tea = new Tea();
     *
     * System.out.println("Preparing Tea:");
     * tea.prepareBeverage();
     *
     * System.out.println("\nPreparing Coffee:");
     *
     * Beverage coffee = new Coffee();
     * coffee.prepareBeverage();
     *
     *
     * OUTPUT:
     *
     * Preparing Tea:
     * Boiling water
     * Adding tea leaves
     * Pouring into cup
     * Adding lemon
     *
     * Preparing Coffee:
     * Boiling water
     * Adding coffee powder
     * Pouring into cup
     *
     *
     * ============================================================
     * 6. UNDERSTANDING THE EXECUTION FLOW
     * ============================================================
     *
     * Consider:
     *
     * Beverage tea = new Tea();
     *
     * tea.prepareBeverage();
     *
     *
     * STEP 1:
     *
     * prepareBeverage() executes from the parent class.
     *
     *
     * STEP 2:
     *
     * boilWater() executes.
     *
     * This is a common step implemented in the parent.
     *
     *
     * STEP 3:
     *
     * addMainIngredient() is called.
     *
     * Since the actual object is Tea,
     * Tea.addMainIngredient() executes.
     *
     *
     * STEP 4:
     *
     * pourIntoCup() executes.
     *
     *
     * STEP 5:
     *
     * customerWantsExtras() executes.
     *
     * The Tea implementation inherits the default behavior,
     * which returns true.
     *
     *
     * STEP 6:
     *
     * addExtras() executes.
     *
     * Tea adds lemon.
     *
     *
     * IMPORTANT:
     *
     * The parent class controls the workflow.
     *
     * Runtime polymorphism allows subclasses to provide
     * their own implementations of selected steps.
     *
     *
     * ============================================================
     * 7. WHAT IS A TEMPLATE METHOD?
     * ============================================================
     *
     * The Template Method is the method that defines
     * the overall algorithm structure.
     *
     * In our example:
     *
     * public final void prepareBeverage() {
     *
     *     boilWater();
     *     addMainIngredient();
     *     pourIntoCup();
     *
     *     if (customerWantsExtras()) {
     *         addExtras();
     *     }
     * }
     *
     *
     * This method is called the Template Method.
     *
     * It defines:
     *
     * - Which steps must execute.
     * - The order of execution.
     * - Where subclasses can customize behavior.
     *
     *
     * IMPORTANT:
     *
     * Template Method is the name of the design pattern's
     * central method. It is not a special Java keyword.
     *
     *
     * ============================================================
     * 8. TYPES OF METHODS USED IN TEMPLATE METHOD
     * ============================================================
     *
     * A. ABSTRACT PRIMITIVE OPERATIONS
     *
     * These methods must be implemented by subclasses.
     *
     * Example:
     *
     * protected abstract void addMainIngredient();
     *
     *
     * B. CONCRETE OPERATIONS
     *
     * These methods have implementations in the parent class.
     *
     * Example:
     *
     * private void boilWater() {
     *
     *     System.out.println("Boiling water");
     * }
     *
     *
     * C. HOOK METHODS
     *
     * These methods provide optional customization points.
     *
     * A subclass can override them, but it is not required.
     *
     * Example:
     *
     * protected boolean customerWantsExtras() {
     *
     *     return true;
     * }
     *
     * Coffee overrides this method to return false.
     *
     *
     * D. TEMPLATE METHOD
     *
     * Defines the complete algorithm using the other methods.
     *
     * Example:
     *
     * public final void prepareBeverage() {
     *
     *     boilWater();
     *     addMainIngredient();
     *     pourIntoCup();
     * }
     *
     *
     * QUICK COMPARISON:
     *
     * Abstract method -> Subclass must implement it.
     *
     * Concrete method -> Parent provides implementation.
     *
     * Hook method -> Subclass may optionally override it.
     *
     * Template method -> Defines the algorithm's structure.
     *
     *
     * ============================================================
     * 9. WHY IS THE TEMPLATE METHOD OFTEN FINAL?
     * ============================================================
     *
     * Consider:
     *
     * public void prepareBeverage() {
     *
     *     boilWater();
     *     addMainIngredient();
     *     pourIntoCup();
     * }
     *
     *
     * If subclasses override prepareBeverage(),
     * they could change the order of steps.
     *
     * For example:
     *
     * class Tea extends Beverage {
     *
     *     @Override
     *     public void prepareBeverage() {
     *
     *         pourIntoCup();
     *         boilWater();
     *     }
     * }
     *
     *
     * This may violate the intended workflow.
     *
     * Declaring the template method final prevents
     * subclasses from overriding it.
     *
     * IMPORTANT:
     *
     * The GoF pattern does not require the template method
     * to be final in every implementation.
     *
     * It is a useful Java design choice when the algorithm
     * sequence must be protected.
     *
     *
     * ============================================================
     * 10. TEMPLATE METHOD VS STRATEGY
     * ============================================================
     *
     * Both patterns allow some parts of an algorithm
     * to vary.
     *
     * But they use different approaches.
     *
     *
     * TEMPLATE METHOD:
     *
     * Uses inheritance.
     *
     * A parent class defines the overall algorithm.
     *
     * Subclasses override selected steps.
     *
     *
     * STRATEGY:
     *
     * Uses composition.
     *
     * Different algorithm implementations are placed
     * in separate strategy objects.
     *
     * The Context delegates work to the selected strategy.
     *
     *
     * EXAMPLE:
     *
     * Template Method:
     *
     * Beverage -> Tea / Coffee
     *
     * Strategy:
     *
     * PaymentService -> UPI / Card / PayPal
     *
     *
     * COMPARISON:
     *
     * Template Method -> Inheritance-based variation.
     *
     * Strategy -> Composition-based variation.
     *
     *
     * ============================================================
     * 11. TEMPLATE METHOD VS FACTORY METHOD
     * ============================================================
     *
     * These patterns are related.
     *
     * FACTORY METHOD:
     *
     * Provides a method for creating an object.
     *
     * TEMPLATE METHOD:
     *
     * Defines the steps of an algorithm.
     *
     *
     * IMPORTANT:
     *
     * Factory Method can itself be used as one step
     * inside a Template Method.
     *
     * For example, a parent algorithm may call createProduct()
     * while executing its overall workflow.
     *
     *
     * REMEMBER:
     *
     * Factory Method -> Object creation.
     *
     * Template Method -> Algorithm structure.
     *
     *
     * ============================================================
     * 12. TEMPLATE METHOD IN JAVA APIs
     * ============================================================
     *
     * Java libraries contain several template-method-style
     * designs.
     *
     *
     * A. AbstractList
     *
     * java.util.AbstractList provides common List behavior
     * while allowing subclasses to implement fundamental
     * operations such as get() and size().
     *
     * Many higher-level operations can be built using
     * these primitive operations.
     *
     *
     * B. InputStream
     *
     * java.io.InputStream provides common stream-reading
     * operations and allows concrete subclasses to implement
     * the underlying read operation.
     *
     * Some inherited operations build on that primitive method.
     *
     *
     * C. HttpServlet
     *
     * Jakarta Servlet's HttpServlet provides request-handling
     * infrastructure and dispatches HTTP requests to methods
     * such as doGet() and doPost().
     *
     * Developers customize selected methods while the
     * servlet framework controls the request lifecycle.
     *
     *
     * IMPORTANT:
     *
     * These APIs illustrate template-method-style design
     * and controlled customization.
     *
     * Not every inherited method in these classes is
     * automatically a GoF Template Method.
     *
     *
     * ============================================================
     * 13. TEMPLATE METHOD IN SPRING / BACKEND DEVELOPMENT
     * ============================================================
     *
     * Template Method is useful when several operations
     * follow the same workflow but differ in selected steps.
     *
     *
     * EXAMPLE: DATA IMPORT SYSTEM
     *
     * Suppose an application imports data from:
     *
     * - CSV files.
     * - Excel files.
     * - JSON files.
     *
     * All imports follow this sequence:
     *
     * 1. Validate input.
     * 2. Read data.
     * 3. Transform data.
     * 4. Save data.
     * 5. Generate import report.
     *
     *
     * The common workflow can be placed in an abstract
     * DataImporter class.
     *
     * Subclasses implement:
     *
     * - readData()
     * - transformData()
     *
     * The parent class controls the complete import process.
     *
     *
     * POSSIBLE STRUCTURE:
     *
     * DataImporter
     *      |
     *      +---- CsvImporter
     *      |
     *      +---- ExcelImporter
     *      |
     *      +---- JsonImporter
     *
     *
     * Spring also provides template-style helper classes:
     *
     * - JdbcTemplate
     * - RestTemplate (synchronous HTTP client)
     *
     * JdbcTemplate handles common JDBC workflow tasks
     * such as resource management and exception translation,
     * while application code supplies SQL and callbacks
     * where required.
     *
     * IMPORTANT:
     *
     * A class named *Template does not automatically mean
     * it implements the GoF Template Method pattern.
     *
     * JdbcTemplate is commonly described as using
     * template-style workflow design.
     *
     *
     * ============================================================
     * 14. HOLLYWOOD PRINCIPLE
     * ============================================================
     *
     * Template Method is often associated with the
     * Hollywood Principle:
     *
     * "Don't call us, we'll call you."
     *
     * What does this mean?
     *
     * The parent class controls the overall workflow.
     *
     * It calls subclass methods at the appropriate points.
     *
     * The subclass does not control the entire algorithm.
     *
     * Example:
     *
     * prepareBeverage()
     *
     * calls:
     *
     * addMainIngredient()
     *
     * The Tea class provides the implementation,
     * but the parent class decides when it is called.
     *
     *
     * ============================================================
     * 15. ADVANTAGES
     * ============================================================
     *
     * 1. Avoids duplication of common algorithm steps.
     *
     * 2. Keeps the overall workflow in one place.
     *
     * 3. Allows subclasses to customize selected operations.
     *
     * 4. Enforces a consistent execution sequence.
     *
     * 5. Supports code reuse through inheritance.
     *
     * 6. Makes common workflow changes easier to maintain.
     *
     *
     * ============================================================
     * 16. DISADVANTAGES
     * ============================================================
     *
     * 1. Uses inheritance, which creates a parent-child
     *    relationship between classes.
     *
     * 2. Subclasses may have limited freedom to change
     *    the overall algorithm.
     *
     * 3. Too many hooks and abstract steps can make
     *    the parent class difficult to understand.
     *
     * 4. Changes to the superclass can affect all subclasses.
     *
     * 5. It may be less flexible than composition-based
     *    approaches for independently interchangeable algorithms.
     *
     *
     * ============================================================
     * 17. WHEN SHOULD WE USE TEMPLATE METHOD?
     * ============================================================
     *
     * Use Template Method when:
     *
     * - Multiple classes follow the same algorithm structure.
     * - Some steps are common and others vary.
     * - The execution order must remain consistent.
     * - You want subclasses to customize selected steps.
     * - Common workflow logic is duplicated across classes.
     *
     *
     * Avoid Template Method when:
     *
     * - The algorithms are fundamentally different.
     * - The workflow changes frequently and independently.
     * - Composition would provide better flexibility.
     * - Inheritance creates unnecessary coupling.
     * - There are only a few simple operations.
     *
     *
     * ============================================================
     * 18. COMMON MISCONCEPTIONS
     * ============================================================
     *
     * 1. Template Method is a Java keyword.
     *
     *    FALSE.
     *
     *    It is a design pattern implemented using
     *    ordinary methods and inheritance.
     *
     *
     * 2. Every method in the parent class must be abstract.
     *
     *    FALSE.
     *
     *    The parent can contain concrete methods,
     *    abstract methods and hook methods.
     *
     *
     * 3. Subclasses control the overall algorithm.
     *
     *    FALSE.
     *
     *    The superclass controls the algorithm's structure.
     *
     *
     * 4. Template Method and Strategy are the same.
     *
     *    FALSE.
     *
     *    Template Method primarily uses inheritance.
     *    Strategy primarily uses composition.
     *
     *
     * 5. The Template Method must always be final.
     *
     *    FALSE.
     *
     *    Making it final is a useful implementation choice
     *    when subclasses must not change the workflow.
     *
     *
     * 6. Every class with a method named template() uses
     *    the Template Method pattern.
     *
     *    FALSE.
     *
     *    The important feature is a fixed algorithm structure
     *    with customizable steps.
     *
     *
     * ============================================================
     * 19. INTERVIEW QUESTIONS
     * ============================================================
     *
     * Q1. What is Template Method Design Pattern?
     *
     * A: A behavioral pattern that defines an algorithm's
     *    skeleton in a superclass and allows subclasses
     *    to customize selected steps.
     *
     *
     * Q2. What are the main participants?
     *
     * A: Abstract Class and Concrete Classes.
     *
     *
     * Q3. What is the Template Method?
     *
     * A: The method that defines the overall algorithm
     *    and calls its individual steps in a fixed sequence.
     *
     *
     * Q4. What is a hook method?
     *
     * A: An optional method that subclasses can override
     *    to customize or influence the algorithm.
     *
     *
     * Q5. What is the difference between abstract and hook methods?
     *
     * A: Abstract methods must be implemented by concrete
     *    subclasses. Hook methods generally have a default
     *    implementation and can optionally be overridden.
     *
     *
     * Q6. Does Template Method use inheritance or composition?
     *
     * A: It primarily uses inheritance.
     *
     *
     * Q7. What is the difference between Template Method
     *    and Strategy?
     *
     * A: Template Method uses inheritance to customize
     *    algorithm steps. Strategy uses composition to
     *    exchange complete algorithm implementations.
     *
     *
     * Q8. What is the Hollywood Principle?
     *
     * A: "Don't call us, we'll call you." The superclass
     *    controls the workflow and calls subclass methods
     *    when required.
     *
     *
     * Q9. Can Template Method contain concrete methods?
     *
     * A: Yes. Common steps are often implemented directly
     *    in the superclass.
     *
     *
     * Q10. Give real-world examples.
     *
     * A: Beverage preparation, report generation,
     *    data import workflows and framework lifecycle methods.
     *
     *
     * ============================================================
     * 20. QUICK REVISION
     * ============================================================
     *
     * PATTERN:
     * Template Method
     *
     * CATEGORY:
     * Behavioral Design Pattern
     *
     * PURPOSE:
     * Define an algorithm's skeleton and allow customization
     * of selected steps.
     *
     * MAIN IDEA:
     * Parent controls the workflow; subclasses customize steps.
     *
     * PARTICIPANTS:
     *
     * Abstract Class
     * Concrete Classes
     *
     * IMPORTANT METHODS:
     *
     * Template Method
     * Abstract Operations
     * Concrete Operations
     * Hook Methods
     *
     * COMMON USE CASES:
     *
     * Beverage Preparation
     * Report Generation
     * Data Import
     * Framework Lifecycle
     *
     * IMPORTANT CONCEPTS:
     *
     * Inheritance
     * Polymorphism
     * Algorithm Skeleton
     * Hollywood Principle
     * Hook Methods
     *
     * REMEMBER:
     *
     * Abstract Class -> Defines the algorithm structure.
     *
     * Template Method -> Controls the sequence of steps.
     *
     * Concrete Class -> Customizes selected steps.
     *
     * Hook Method -> Provides optional customization.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Template Method Design Pattern , Happy Learning ..!");
    }
}
