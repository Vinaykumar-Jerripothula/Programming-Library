
public class Visitor {

    /*
     * ============================================================
     * 1. INTRODUCTION
     * ============================================================
     *
     * Imagine you are developing a shopping application.
     *
     * You have different types of products:
     *
     * - Electronics
     * - Books
     * - Clothing
     *
     * Now you need to perform different operations on them:
     *
     * - Calculate tax.
     * - Generate invoices.
     * - Calculate discounts.
     * - Generate reports.
     *
     * One approach is to add every new operation directly
     * into every product class.
     *
     * For example:
     *
     * class Electronics {
     *
     *     void calculateTax() { }
     *
     *     void generateInvoice() { }
     *
     *     void calculateDiscount() { }
     * }
     *
     * class Book {
     *
     *     void calculateTax() { }
     *
     *     void generateInvoice() { }
     *
     *     void calculateDiscount() { }
     * }
     *
     * class Clothing {
     *
     *     void calculateTax() { }
     *
     *     void generateInvoice() { }
     *
     *     void calculateDiscount() { }
     * }
     *
     *
     * PROBLEM:
     *
     * Every time a new operation is introduced,
     * we must modify multiple product classes.
     *
     * What if we could define these operations separately
     * without putting all of them inside the product classes?
     *
     * This is where Visitor becomes useful.
     *
     *
     * DEFINITION:
     *
     * Visitor is a behavioral design pattern that lets you
     * define new operations on a group of existing object
     * structures without changing the classes of those objects.
     *
     *
     * SIMPLE DEFINITION:
     *
     * Keep operations separate from the objects on which
     * those operations are performed.
     *
     *
     * ============================================================
     * 2. WHY DO WE NEED VISITOR?
     * ============================================================
     *
     * Consider a document application.
     *
     * A document contains different elements:
     *
     * - Text
     * - Images
     * - Tables
     *
     * Now imagine adding these operations:
     *
     * - Export to PDF.
     * - Export to HTML.
     * - Calculate document size.
     * - Generate accessibility information.
     *
     * Without Visitor, we may add all these operations
     * to every element class.
     *
     * As the number of operations increases,
     * the element classes become difficult to maintain.
     *
     *
     * VISITOR SOLUTION:
     *
     * Define a common Visitor interface.
     *
     * Each operation gets its own Visitor implementation.
     *
     * The existing elements accept a Visitor and allow it
     * to perform the appropriate operation on them.
     *
     *
     * BENEFIT:
     *
     * We can introduce new operations without modifying
     * every existing element class.
     *
     *
     * ============================================================
     * 3. REAL-WORLD ANALOGY
     * ============================================================
     *
     * Imagine a doctor visiting different patients.
     *
     * The doctor performs a checkup depending on the
     * type of patient.
     *
     * For example:
     *
     * - Child -> Pediatric examination.
     * - Adult -> General examination.
     * - Senior citizen -> Age-specific examination.
     *
     * The patients remain the same.
     *
     * The doctor brings the operation being performed.
     *
     * Similarly, Visitor separates an operation from
     * the objects on which it operates.
     *
     *
     * ============================================================
     * 4. STRUCTURE AND PARTICIPANTS
     * ============================================================
     *
     * Main participants:
     *
     *
     * 1. Visitor:
     *
     *    Defines operations for each Concrete Element type.
     *
     *    Example:
     *
     *    visit(Electronics)
     *    visit(Book)
     *    visit(Clothing)
     *
     *
     * 2. Concrete Visitor:
     *
     *    Implements the actual operations.
     *
     *    Examples:
     *
     *    - TaxVisitor
     *    - InvoiceVisitor
     *    - DiscountVisitor
     *
     *
     * 3. Element:
     *
     *    Defines an accept() method that accepts a Visitor.
     *
     *
     * 4. Concrete Elements:
     *
     *    Implement accept() and pass themselves to
     *    the appropriate Visitor method.
     *
     *    Examples:
     *
     *    - Electronics
     *    - Book
     *    - Clothing
     *
     *
     * 5. Object Structure:
     *
     *    Maintains or provides access to a collection
     *    of Elements.
     *
     *
     * STRUCTURE:
     *
     *                  Visitor
     *                     |
     *          -------------------------
     *          |           |           |
     *          v           v           v
     *       visit(A)    visit(B)    visit(C)
     *
     *
     *             Concrete Visitor
     *                     |
     *              Implements visits
     *
     *
     *                Element
     *                   |
     *                accept()
     *                   |
     *          ---------------------
     *          |         |         |
     *          v         v         v
     *       ElementA  ElementB  ElementC
     *
     *
     * ============================================================
     * 5. COMPLETE JAVA IMPLEMENTATION
     * ============================================================
     *
     * Example: Shopping Cart Tax Calculation.
     *
     *
     * STEP 1: Create the Visitor interface.
     *
     * interface ProductVisitor {
     *
     *     void visit(Electronics electronics);
     *
     *     void visit(Book book);
     *
     *     void visit(Clothing clothing);
     * }
     *
     * Notice that the Visitor interface has a separate
     * visit() method for every Concrete Element type.
     *
     *
     * STEP 2: Create the Element interface.
     *
     * interface Product {
     *
     *     void accept(ProductVisitor visitor);
     * }
     *
     * Every product must provide an accept() method.
     *
     *
     * STEP 3: Create Concrete Elements.
     *
     * A. Electronics
     *
     * class Electronics implements Product {
     *
     *     private double price;
     *
     *     public Electronics(double price) {
     *
     *         this.price = price;
     *     }
     *
     *     public double getPrice() {
     *
     *         return price;
     *     }
     *
     *     @Override
     *     public void accept(ProductVisitor visitor) {
     *
     *         visitor.visit(this);
     *     }
     * }
     *
     *
     * B. Book
     *
     * class Book implements Product {
     *
     *     private double price;
     *
     *     public Book(double price) {
     *
     *         this.price = price;
     *     }
     *
     *     public double getPrice() {
     *
     *         return price;
     *     }
     *
     *     @Override
     *     public void accept(ProductVisitor visitor) {
     *
     *         visitor.visit(this);
     *     }
     * }
     *
     *
     * C. Clothing
     *
     * class Clothing implements Product {
     *
     *     private double price;
     *
     *     public Clothing(double price) {
     *
     *         this.price = price;
     *     }
     *
     *     public double getPrice() {
     *
     *         return price;
     *     }
     *
     *     @Override
     *     public void accept(ProductVisitor visitor) {
     *
     *         visitor.visit(this);
     *     }
     * }
     *
     *
     * STEP 4: Create a Concrete Visitor.
     *
     * class TaxCalculator implements ProductVisitor {
     *
     *     @Override
     *     public void visit(Electronics electronics) {
     *
     *         double tax = electronics.getPrice() * 0.18;
     *
     *         System.out.println(
     *             "Electronics tax: Rs." + tax
     *         );
     *     }
     *
     *     @Override
     *     public void visit(Book book) {
     *
     *         double tax = book.getPrice() * 0.05;
     *
     *         System.out.println(
     *             "Book tax: Rs." + tax
     *         );
     *     }
     *
     *     @Override
     *     public void visit(Clothing clothing) {
     *
     *         double tax = clothing.getPrice() * 0.12;
     *
     *         System.out.println(
     *             "Clothing tax: Rs." + tax
     *         );
     *     }
     * }
     *
     * TaxCalculator is the Concrete Visitor.
     *
     * It implements tax calculation for each product type.
     *
     *
     * STEP 5: Create another Concrete Visitor.
     *
     * class InvoiceVisitor implements ProductVisitor {
     *
     *     @Override
     *     public void visit(Electronics electronics) {
     *
     *         System.out.println(
     *             "Electronics invoice: Rs." +
     *             electronics.getPrice()
     *         );
     *     }
     *
     *     @Override
     *     public void visit(Book book) {
     *
     *         System.out.println(
     *             "Book invoice: Rs." + book.getPrice()
     *         );
     *     }
     *
     *     @Override
     *     public void visit(Clothing clothing) {
     *
     *         System.out.println(
     *             "Clothing invoice: Rs." + clothing.getPrice()
     *         );
     *     }
     * }
     *
     * InvoiceVisitor performs a different operation
     * on the same product types.
     *
     *
     * STEP 6: Create the Object Structure.
     *
     * import java.util.ArrayList;
     * import java.util.List;
     *
     * class ShoppingCart {
     *
     *     private List<Product> products = new ArrayList<>();
     *
     *     public void addProduct(Product product) {
     *
     *         products.add(product);
     *     }
     *
     *     public void accept(ProductVisitor visitor) {
     *
     *         for (Product product : products) {
     *
     *             product.accept(visitor);
     *         }
     *     }
     * }
     *
     *
     * STEP 7: Client code.
     *
     * ShoppingCart cart = new ShoppingCart();
     *
     * cart.addProduct(new Electronics(10000));
     * cart.addProduct(new Book(500));
     * cart.addProduct(new Clothing(2000));
     *
     *
     * System.out.println("Tax Calculation:");
     *
     * cart.accept(new TaxCalculator());
     *
     *
     * System.out.println("\nInvoice Generation:");
     *
     * cart.accept(new InvoiceVisitor());
     *
     *
     * OUTPUT:
     *
     * Tax Calculation:
     * Electronics tax: Rs.1800.0
     * Book tax: Rs.25.0
     * Clothing tax: Rs.240.0
     *
     * Invoice Generation:
     * Electronics invoice: Rs.10000.0
     * Book invoice: Rs.500.0
     * Clothing invoice: Rs.2000.0
     *
     *
     * NOTE:
     *
     * The tax percentages are illustrative only.
     * Actual tax rules depend on applicable regulations.
     *
     *
     * ============================================================
     * 6. UNDERSTANDING THE EXECUTION FLOW
     * ============================================================
     *
     * STEP 1:
     *
     * Create a ShoppingCart.
     *
     * Add:
     *
     * Electronics
     * Book
     * Clothing
     *
     *
     * STEP 2:
     *
     * Create a TaxCalculator.
     *
     * cart.accept(new TaxCalculator());
     *
     *
     * STEP 3:
     *
     * ShoppingCart iterates over its products.
     *
     * for (Product product : products) {
     *
     *     product.accept(visitor);
     * }
     *
     *
     * STEP 4:
     *
     * Suppose the current product is Electronics.
     *
     * Electronics.accept(visitor) executes:
     *
     * visitor.visit(this);
     *
     * Since this refers to an Electronics object,
     * the following overload is selected:
     *
     * visit(Electronics electronics)
     *
     *
     * STEP 5:
     *
     * TaxCalculator calculates the electronics tax.
     *
     *
     * STEP 6:
     *
     * The same process happens for Book and Clothing.
     *
     *
     * STEP 7:
     *
     * Later, we can pass InvoiceVisitor to the same cart.
     *
     * The products remain unchanged.
     *
     * A different operation is performed on them.
     *
     *
     * IMPORTANT:
     *
     * Visitor separates the operation from the elements.
     *
     *
     * ============================================================
     * 7. UNDERSTANDING DOUBLE DISPATCH
     * ============================================================
     *
     * Double dispatch is the key mechanism behind
     * the classic Visitor pattern.
     *
     * First, understand normal method overloading.
     *
     *
     * A. METHOD OVERLOADING
     *
     * class Printer {
     *
     *     void print(Electronics item) { }
     *
     *     void print(Book item) { }
     * }
     *
     * The compiler chooses an overloaded method based
     * on the argument's compile-time type.
     *
     *
     * B. THE PROBLEM WITH A GENERIC ELEMENT REFERENCE
     *
     * Product product = new Electronics(10000);
     *
     * If we try:
     *
     * visitor.visit(product);
     *
     * The compile-time type of product is Product.
     *
     * But our Visitor interface has methods for:
     *
     * Electronics
     * Book
     * Clothing
     *
     * It does not have visit(Product).
     *
     * Java does not automatically select an overloaded
     * method based only on the runtime type of the argument.
     *
     *
     * C. HOW VISITOR SOLVES THIS
     *
     * Each Concrete Element implements:
     *
     * public void accept(ProductVisitor visitor) {
     *
     *     visitor.visit(this);
     * }
     *
     * Inside Electronics.accept(), the compile-time type
     * of this is Electronics.
     *
     * Therefore, Java selects:
     *
     * visit(Electronics electronics)
     *
     *
     * TWO DISPATCHES:
     *
     * FIRST DISPATCH:
     *
     * The runtime type of the Element determines
     * which accept() implementation executes.
     *
     *
     * SECOND DISPATCH:
     *
     * The Concrete Element's accept() method calls
     * the appropriate overloaded visit() method.
     *
     *
     * FLOW:
     *
     * Product reference
     *       |
     *       v
     * Runtime dispatch to Electronics.accept()
     *       |
     *       v
     * visitor.visit(this)
     *       |
     *       v
     * visit(Electronics)
     *
     *
     * REMEMBER:
     *
     * Double Dispatch -> The operation depends on
     *                    both the Visitor type and
     *                    the Element type.
     *
     *
     * ============================================================
     * 8. WHY IS VISITOR USEFUL FOR ADDING NEW OPERATIONS?
     * ============================================================
     *
     * Imagine we already have:
     *
     * Electronics
     * Book
     * Clothing
     *
     * And we have:
     *
     * TaxCalculator
     * InvoiceVisitor
     *
     * Now the business requests a DiscountCalculator.
     *
     * We can create:
     *
     * class DiscountCalculator implements ProductVisitor {
     *
     *     public void visit(Electronics item) {
     *         // Electronics discount
     *     }
     *
     *     public void visit(Book item) {
     *         // Book discount
     *     }
     *
     *     public void visit(Clothing item) {
     *         // Clothing discount
     *     }
     * }
     *
     *
     * We do not need to modify the existing product classes.
     *
     * This is the main strength of Visitor.
     *
     *
     * ============================================================
     * 9. THE IMPORTANT TRADE-OFF
     * ============================================================
     *
     * Visitor makes it easy to add new operations.
     *
     * But adding a new Element type is difficult.
     *
     * Suppose we introduce:
     *
     * Furniture
     *
     * We must update:
     *
     * 1. ProductVisitor:
     *
     *    Add visit(Furniture furniture).
     *
     *
     * 2. TaxCalculator:
     *
     *    Implement visit(Furniture furniture).
     *
     *
     * 3. InvoiceVisitor:
     *
     *    Implement visit(Furniture furniture).
     *
     *
     * 4. DiscountCalculator:
     *
     *    Implement visit(Furniture furniture).
     *
     *
     * Every Concrete Visitor must support the new Element.
     *
     *
     * CORE TRADE-OFF:
     *
     * NEW OPERATION:
     *
     * Easy to add.
     *
     *
     * NEW ELEMENT TYPE:
     *
     * Requires changes to the Visitor interface
     * and all Concrete Visitors.
     *
     *
     * REMEMBER:
     *
     * Visitor is useful when the element structure
     * is relatively stable but new operations are expected.
     *
     *
     * ============================================================
     * 10. VISITOR VS STRATEGY
     * ============================================================
     *
     * Both patterns encapsulate behavior in separate objects.
     *
     * But their purposes are different.
     *
     *
     * STRATEGY:
     *
     * Encapsulates interchangeable algorithms.
     *
     * The Context selects a strategy to perform a task.
     *
     *
     * VISITOR:
     *
     * Adds operations to a set of different element types
     * without placing those operations inside the elements.
     *
     *
     * REMEMBER:
     *
     * Strategy -> Select an algorithm.
     *
     * Visitor -> Apply an operation to different element types.
     *
     *
     * ============================================================
     * 11. VISITOR VS DECORATOR
     * ============================================================
     *
     * DECORATOR:
     *
     * Adds responsibilities to an object by wrapping it
     * with another object that implements the same interface.
     *
     *
     * VISITOR:
     *
     * Adds external operations to a group of element types.
     *
     *
     * REMEMBER:
     *
     * Decorator -> Adds behavior through wrapping.
     *
     * Visitor -> Adds operations through double dispatch.
     *
     *
     * ============================================================
     * 12. VISITOR VS COMMAND
     * ============================================================
     *
     * COMMAND:
     *
     * Encapsulates a request as an object.
     *
     *
     * VISITOR:
     *
     * Encapsulates an operation that can be applied
     * to different element types.
     *
     *
     * REMEMBER:
     *
     * Command -> Represents a request or action.
     *
     * Visitor -> Performs type-specific operations
     *            on existing elements.
     *
     *
     * ============================================================
     * 13. VISITOR IN JAVA AND REAL APPLICATIONS
     * ============================================================
     *
     * Visitor is particularly useful when working with
     * structured object hierarchies.
     *
     *
     * A. COMPILER AND ABSTRACT SYNTAX TREES
     *
     * A compiler can represent source code as an
     * Abstract Syntax Tree (AST).
     *
     * The tree may contain:
     *
     * - Expressions.
     * - Statements.
     * - Variables.
     * - Function declarations.
     *
     * Different Visitors can perform:
     *
     * - Type checking.
     * - Code generation.
     * - Pretty printing.
     * - Static analysis.
     *
     * The AST structure can remain separate from
     * these operations.
     *
     *
     * B. FILE SYSTEM OPERATIONS
     *
     * A file-system structure may contain:
     *
     * - Files.
     * - Directories.
     * - Symbolic links.
     *
     * Visitors can perform operations such as:
     *
     * - Calculate total size.
     * - Generate reports.
     * - Scan metadata.
     *
     *
     * C. DOCUMENT PROCESSING
     *
     * A document may contain:
     *
     * - Text.
     * - Images.
     * - Tables.
     *
     * Different Visitors can export the document
     * into different formats.
     *
     *
     * ============================================================
     * 14. VISITOR AND JAVA LIBRARY EXAMPLES
     * ============================================================
     *
     * A. FileVisitor
     *
     * Java NIO provides:
     *
     * java.nio.file.FileVisitor
     *
     * It defines callbacks for visiting file-system entries:
     *
     * - preVisitDirectory()
     * - visitFile()
     * - visitFileFailed()
     * - postVisitDirectory()
     *
     * SimpleFileVisitor provides default implementations
     * that can be selectively overridden.
     *
     * This is a visitor-style API for file-tree traversal.
     *
     *
     * B. Annotation Processing
     *
     * Java annotation processing APIs provide visitor-style
     * interfaces for inspecting language-model elements.
     *
     * For example:
     *
     * javax.lang.model.util.ElementVisitor
     *
     * These APIs allow operations to be performed
     * on different kinds of program elements.
     *
     *
     * IMPORTANT:
     *
     * Not every API with the word Visitor implements
     * the exact classic GoF Visitor structure.
     *
     *
     * ============================================================
     * 15. VISITOR IN BACKEND DEVELOPMENT
     * ============================================================
     *
     * Consider a reporting application.
     *
     * It has different report elements:
     *
     * - SalesRecord
     * - EmployeeRecord
     * - CustomerRecord
     *
     * The application needs:
     *
     * - CSV export.
     * - PDF export.
     * - Validation.
     * - Statistics calculation.
     *
     * Instead of adding all operations to every record class,
     * separate Visitors can implement each operation.
     *
     *
     * POSSIBLE STRUCTURE:
     *
     * ReportElement
     *       |
     *       +---- SalesRecord
     *       |
     *       +---- EmployeeRecord
     *       |
     *       +---- CustomerRecord
     *
     *
     * ReportVisitor
     *       |
     *       +---- CsvExportVisitor
     *       |
     *       +---- PdfExportVisitor
     *       |
     *       +---- ValidationVisitor
     *
     *
     * BENEFITS:
     *
     * - Keeps reporting operations separate.
     * - Makes new operations easier to introduce.
     * - Avoids placing every operation inside data classes.
     *
     *
     * IMPORTANT:
     *
     * Visitor is not automatically the best choice
     * for every Spring Boot service.
     *
     * If element types change frequently, a simpler
     * polymorphic design or another approach may be better.
     *
     *
     * ============================================================
     * 16. VISITOR AND OPEN/CLOSED PRINCIPLE
     * ============================================================
     *
     * Visitor supports the Open/Closed Principle
     * in one particular direction.
     *
     *
     * ADDING NEW OPERATIONS:
     *
     * We can add another Concrete Visitor without modifying
     * existing Concrete Element classes.
     *
     *
     * ADDING NEW ELEMENTS:
     *
     * We generally need to modify the Visitor interface
     * and existing Concrete Visitors.
     *
     *
     * Therefore:
     *
     * Visitor is especially suitable when:
     *
     * - Element types are relatively stable.
     * - New operations are added frequently.
     *
     * It is less suitable when new element types
     * are introduced frequently.
     *
     *
     * ============================================================
     * 17. ADVANTAGES
     * ============================================================
     *
     * 1. Adds new operations without modifying
     *    existing Concrete Element classes.
     *
     * 2. Separates operations from object structures.
     *
     * 3. Groups related operations inside Concrete Visitors.
     *
     * 4. Supports double dispatch.
     *
     * 5. Makes operations easier to organize and test.
     *
     * 6. Useful for stable object structures with
     *    frequently changing operations.
     *
     *
     * ============================================================
     * 18. DISADVANTAGES
     * ============================================================
     *
     * 1. Adding a new Element type requires updating
     *    the Visitor interface.
     *
     * 2. Every Concrete Visitor may need to implement
     *    the new Element operation.
     *
     * 3. Visitor may require access to internal Element data.
     *
     * 4. The pattern can introduce many additional classes.
     *
     * 5. It is less convenient for frequently changing
     *    object structures.
     *
     * 6. Double dispatch can be difficult to understand
     *    initially.
     *
     *
     * ============================================================
     * 19. WHEN SHOULD WE USE VISITOR?
     * ============================================================
     *
     * Use Visitor when:
     *
     * - You have a relatively stable set of element types.
     * - You need to add new operations frequently.
     * - Different operations must work on multiple element types.
     * - You want to keep operations separate from elements.
     * - You are working with trees or structured object models.
     *
     *
     * Avoid Visitor when:
     *
     * - New element types are introduced frequently.
     * - The object structure changes often.
     * - There are only one or two simple operations.
     * - The extra Visitor classes add unnecessary complexity.
     * - Operations require excessive access to internal state.
     *
     *
     * ============================================================
     * 20. COMMON MISCONCEPTIONS
     * ============================================================
     *
     * 1. Visitor adds new classes to the object structure.
     *
     *    FALSE.
     *
     *    It primarily adds new operations over existing
     *    element types.
     *
     *
     * 2. Visitor makes adding new element types easy.
     *
     *    FALSE.
     *
     *    New element types require updating Visitor
     *    and its implementations.
     *
     *
     * 3. Visitor and Strategy are the same.
     *
     *    FALSE.
     *
     *    Strategy selects interchangeable algorithms.
     *    Visitor performs operations on different element types.
     *
     *
     * 4. Visitor uses only one dispatch.
     *
     *    FALSE.
     *
     *    The classic implementation uses double dispatch.
     *
     *
     * 5. Visitor automatically guarantees encapsulation.
     *
     *    FALSE.
     *
     *    Elements must expose the information Visitors need,
     *    or provide another controlled access mechanism.
     *
     *
     * 6. Every class named Visitor implements the GoF pattern.
     *
     *    FALSE.
     *
     *    The structure and intent matter, not just the name.
     *
     *
     * ============================================================
     * 21. INTERVIEW QUESTIONS
     * ============================================================
     *
     * Q1. What is Visitor Design Pattern?
     *
     * A: A behavioral pattern that adds operations to
     *    existing object structures without modifying
     *    their Concrete Element classes.
     *
     *
     * Q2. What are the main participants?
     *
     * A: Visitor, Concrete Visitor, Element,
     *    Concrete Element and Object Structure.
     *
     *
     * Q3. What is the purpose of accept()?
     *
     * A: It allows an Element to pass itself to the Visitor
     *    so the appropriate visit() method can be called.
     *
     *
     * Q4. What is double dispatch?
     *
     * A: A technique where the executed operation depends
     *    on both the runtime type of the Element and
     *    the type of the Visitor.
     *
     *
     * Q5. What is the main advantage of Visitor?
     *
     * A: Adding new operations without modifying
     *    existing Concrete Element classes.
     *
     *
     * Q6. What is the main disadvantage?
     *
     * A: Adding a new Element type requires changes
     *    to the Visitor interface and Concrete Visitors.
     *
     *
     * Q7. When is Visitor most suitable?
     *
     * A: When the element structure is stable but
     *    new operations are frequently required.
     *
     *
     * Q8. What is the difference between Visitor and Strategy?
     *
     * A: Strategy encapsulates interchangeable algorithms.
     *    Visitor applies operations to different element types.
     *
     *
     * Q9. Give a real-world application.
     *
     * A: Compiler AST processing, file-tree traversal,
     *    document export and report generation.
     *
     *
     * Q10. What is the relationship between Visitor
     *      and Open/Closed Principle?
     *
     * A: Visitor makes adding operations easy without
     *    modifying existing elements, but adding new
     *    element types is more difficult.
     *
     *
     * ============================================================
     * 22. QUICK REVISION
     * ============================================================
     *
     * PATTERN:
     * Visitor
     *
     * CATEGORY:
     * Behavioral Design Pattern
     *
     * PURPOSE:
     * Add operations to existing object structures
     * without modifying their element classes.
     *
     * MAIN IDEA:
     * Separate operations from the objects being operated on.
     *
     * PARTICIPANTS:
     *
     * Visitor
     * Concrete Visitor
     * Element
     * Concrete Element
     * Object Structure
     *
     * IMPORTANT CONCEPTS:
     *
     * accept()
     * visit()
     * Double Dispatch
     * Operation Separation
     * Stable Element Structure
     *
     * COMMON USE CASES:
     *
     * Compiler AST
     * File System Traversal
     * Document Processing
     * Report Generation
     * Static Analysis
     *
     * REMEMBER:
     *
     * Visitor -> Defines operations for element types.
     *
     * Concrete Visitor -> Implements those operations.
     *
     * Element -> Defines accept().
     *
     * Concrete Element -> Passes itself to the Visitor.
     *
     * Object Structure -> Provides access to elements.
     *
     * MOST IMPORTANT TRADE-OFF:
     *
     * New operation -> Easy.
     *
     * New element type -> Requires updating Visitors.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Visitor Design Pattern , Happy Learning ..!");
    }
}
