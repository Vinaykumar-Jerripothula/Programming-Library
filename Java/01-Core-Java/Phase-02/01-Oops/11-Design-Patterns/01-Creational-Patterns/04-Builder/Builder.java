
public class Builder {

    /*
     * ============================================================
     * 1. INTRODUCTION
     * ============================================================
     *
     * Imagine you are creating a User object.
     *
     * A User has several fields:
     *
     *   - username
     *   - email
     *   - password
     *   - age
     *   - phoneNumber
     *   - address
     *
     * Some fields are mandatory, while others are optional.
     *
     * One way to create the object is by using a constructor:
     *
     * User user = new User(
     *     "Vinay",
     *     "vinay@gmail.com",
     *     "password123",
     *     22,
     *     "9876543210",
     *     "Hyderabad"
     * );
     *
     * But what if the class has 15 or 20 fields?
     *
     * The constructor becomes difficult to read and maintain.
     *
     * What if some fields are optional?
     *
     * We might end up creating many overloaded constructors.
     *
     * The Builder pattern provides a cleaner way to construct
     * complex objects step by step.
     *
     *
     * DEFINITION:
     *
     * Builder is a creational design pattern that separates the
     * construction of a complex object from its representation,
     * allowing the same construction process to create
     * different representations.
     *
     *
     * ============================================================
     * 2. WHY DO WE NEED BUILDER?
     * ============================================================
     *
     * Consider these common object-creation approaches:
     *
     * 1. Constructor:
     *
     *    User user = new User(
     *        "Vinay", "vinay@gmail.com", 22, "Hyderabad"
     *    );
     *
     *    Problem:
     *    The meaning of each argument may not be obvious.
     *
     *
     * 2. Multiple overloaded constructors:
     *
     *    User(String name)
     *
     *    User(String name, String email)
     *
     *    User(String name, String email, int age)
     *
     *    User(String name, String email, int age, String address)
     *
     *    Problem:
     *    Too many constructors make the class difficult to maintain.
     *
     *
     * 3. Setters:
     *
     *    User user = new User();
     *    user.setName("Vinay");
     *    user.setEmail("vinay@gmail.com");
     *    user.setAge(22);
     *
     *    Problem:
     *    The object may temporarily exist in an incomplete state.
     *    It can also be modified after construction.
     *
     *
     * BUILDER SOLUTION:
     *
     *    User user = new User.Builder()
     *        .name("Vinay")
     *        .email("vinay@gmail.com")
     *        .age(22)
     *        .address("Hyderabad")
     *        .build();
     *
     * Benefits:
     * - Readable object creation.
     * - Optional fields can be omitted.
     * - Construction happens step by step.
     * - Validation can happen before the object is created.
     *
     *
     * ============================================================
     * 3. REAL-WORLD ANALOGY
     * ============================================================
     *
     * Think about ordering a pizza.
     *
     * You choose:
     *
     * - Size
     * - Crust
     * - Cheese
     * - Toppings
     * - Extra sauce
     *
     * You don't need to specify every option in one huge constructor.
     *
     * You select the options one by one.
     *
     * Finally, you confirm your order.
     *
     * The final confirmation is similar to build().
     *
     * Remember:
     * Configure step by step, then build the final object.
     *
     *
     * ============================================================
     * 4. BUILDER PATTERN STRUCTURE
     * ============================================================
     *
     * Main participants in the classic GoF Builder pattern:
     *
     * 1. Product:
     *    The complex object being constructed.
     *
     * 2. Builder:
     *    Declares the construction steps.
     *
     * 3. Concrete Builder:
     *    Implements the construction steps and builds a product.
     *
     * 4. Director:
     *    Controls the sequence of construction steps.
     *
     * IMPORTANT:
     *
     * In modern Java, a common implementation is the fluent
     * builder style, where the Builder is nested inside the
     * Product class.
     *
     * This implementation often does not need a separate Director.
     *
     *
     * ============================================================
     * 5. COMPLETE JAVA IMPLEMENTATION - CLASSIC BUILDER
     * ============================================================
     *
     * STEP 1: Define the Product.
     *
     * class Computer {
     *
     *     private final String processor;
     *     private final String ram;
     *     private final String storage;
     *
     *     Computer(String processor, String ram, String storage) {
     *         this.processor = processor;
     *         this.ram = ram;
     *         this.storage = storage;
     *     }
     *
     *     void showConfiguration() {
     *         System.out.println("Processor: " + processor);
     *         System.out.println("RAM: " + ram);
     *         System.out.println("Storage: " + storage);
     *     }
     * }
     *
     *
     * STEP 2: Define the Builder interface.
     *
     * interface ComputerBuilder {
     *
     *     void buildProcessor();
     *     void buildRAM();
     *     void buildStorage();
     *     Computer getComputer();
     * }
     *
     *
     * STEP 3: Create a Concrete Builder.
     *
     * class GamingComputerBuilder implements ComputerBuilder {
     *
     *     private String processor;
     *     private String ram;
     *     private String storage;
     *
     *     public void buildProcessor() {
     *         processor = "High-performance Intel Processor";
     *     }
     *
     *     public void buildRAM() {
     *         ram = "32 GB RAM";
     *     }
     *
     *     public void buildStorage() {
     *         storage = "2 TB SSD";
     *     }
     *
     *     public Computer getComputer() {
     *         return new Computer(processor, ram, storage);
     *     }
     * }
     *
     *
     * STEP 4: Create the Director.
     *
     * class ComputerDirector {
     *
     *     public Computer construct(ComputerBuilder builder) {
     *
     *         builder.buildProcessor();
     *         builder.buildRAM();
     *         builder.buildStorage();
     *
     *         return builder.getComputer();
     *     }
     * }
     *
     *
     * STEP 5: Use the Builder.
     *
     * ComputerBuilder builder = new GamingComputerBuilder();
     *
     * ComputerDirector director = new ComputerDirector();
     *
     * Computer computer = director.construct(builder);
     *
     * computer.showConfiguration();
     *
     *
     * OUTPUT:
     *
     * Processor: High-performance Intel Processor
     * RAM: 32 GB RAM
     * Storage: 2 TB SSD
     *
     *
     * ============================================================
     * 6. FLUENT BUILDER PATTERN IN JAVA
     * ============================================================
     *
     * The previous example demonstrates the classic Builder
     * structure with a separate Builder interface and Director.
     *
     * In everyday Java development, you will frequently see
     * a simpler implementation called the fluent Builder.
     *
     * Example:
     *
     * class User {
     *
     *     private final String name;
     *     private final String email;
     *     private final int age;
     *     private final String address;
     *
     *     private User(UserBuilder builder) {
     *
     *         this.name = builder.name;
     *         this.email = builder.email;
     *         this.age = builder.age;
     *         this.address = builder.address;
     *     }
     *
     *     public static class UserBuilder {
     *
     *         private final String name;
     *         private final String email;
     *
     *         private int age;
     *         private String address;
     *
     *         public UserBuilder(String name, String email) {
     *             this.name = name;
     *             this.email = email;
     *         }
     *
     *         public UserBuilder age(int age) {
     *             this.age = age;
     *             return this;
     *         }
     *
     *         public UserBuilder address(String address) {
     *             this.address = address;
     *             return this;
     *         }
     *
     *         public User build() {
     *
     *             if (name == null || name.isBlank()) {
     *                 throw new IllegalArgumentException(
     *                     "Name is required"
     *                 );
     *             }
     *
     *             if (email == null || email.isBlank()) {
     *                 throw new IllegalArgumentException(
     *                     "Email is required"
     *                 );
     *             }
     *
     *             if (age < 0) {
     *                 throw new IllegalArgumentException(
     *                     "Age cannot be negative"
     *                 );
     *             }
     *
     *             return new User(this);
     *         }
     *     }
     * }
     *
     *
     * USAGE:
     *
     * User user = new User.UserBuilder(
     *     "Vinay",
     *     "vinay@gmail.com"
     * )
     * .age(22)
     * .address("Hyderabad")
     * .build();
     *
     *
     * ============================================================
     * 7. WHAT IS A FLUENT API?
     * ============================================================
     *
     * Notice this:
     *
     * builder.age(22)
     *        .address("Hyderabad")
     *        .build();
     *
     * Each configuration method returns the same Builder object.
     *
     * Therefore, we can call another method on the returned object.
     *
     * This style is called method chaining.
     *
     * An API designed to support this natural chaining style
     * is commonly called a Fluent API.
     *
     * Remember:
     * Returning this enables method chaining.
     *
     *
     * ============================================================
     * 8. WHY IS THE CONSTRUCTOR PRIVATE?
     * ============================================================
     *
     * In the fluent Builder example:
     *
     * private User(UserBuilder builder)
     *
     * The constructor is private so that clients cannot directly
     * create a User without going through the Builder.
     *
     * This allows the class to control its construction process.
     *
     * It is especially useful when mandatory fields and validation
     * rules must be enforced.
     *
     *
     * ============================================================
     * 9. WHY ARE FIELDS FINAL?
     * ============================================================
     *
     * Example:
     *
     * private final String name;
     *
     * The final fields are assigned during construction and cannot
     * be reassigned afterward.
     *
     * This supports immutability.
     *
     * IMPORTANT:
     *
     * A Builder does not automatically make an object immutable.
     * Immutability depends on the Product class design.
     *
     * For example, mutable objects stored inside the Product may
     * still need defensive copies.
     *
     *
     * ============================================================
     * 10. BUILDER VS CONSTRUCTOR
     * ============================================================
     *
     * CONSTRUCTOR:
     *
     * User user = new User(
     *     "Vinay",
     *     "vinay@gmail.com",
     *     22,
     *     "Hyderabad"
     * );
     *
     * - Simple for a small number of fields.
     * - Can become confusing with many parameters.
     * - Optional arguments can be difficult to manage.
     *
     *
     * BUILDER:
     *
     * User user = new UserBuilder("Vinay", "vinay@gmail.com")
     *     .age(22)
     *     .address("Hyderabad")
     *     .build();
     *
     * - More readable for many fields.
     * - Optional fields can be skipped.
     * - Validation can be centralized in build().
     * - Requires additional Builder code.
     *
     *
     * ============================================================
     * 11. BUILDER VS SETTERS
     * ============================================================
     *
     * SETTERS:
     *
     * User user = new User();
     * user.setName("Vinay");
     * user.setEmail("vinay@gmail.com");
     *
     * The object can be observed before all fields are configured.
     * It may also be modified after construction.
     *
     *
     * BUILDER:
     *
     * User user = new UserBuilder("Vinay", "vinay@gmail.com")
     *     .age(22)
     *     .build();
     *
     * The object is created only when build() is called.
     *
     * A well-designed Builder can ensure required information
     * is present before constructing the final object.
     *
     *
     * ============================================================
     * 12. BUILDER VS FACTORY METHOD
     * ============================================================
     *
     * FACTORY METHOD:
     *
     * Focuses on deciding which concrete product implementation
     * should be created.
     *
     * BUILDER:
     *
     * Focuses on constructing an object step by step, especially
     * when it has many configuration options.
     *
     * Example:
     *
     * Factory Method:
     * Create an EmailNotification or SMSNotification.
     *
     * Builder:
     * Construct a User with name, email, age and address.
     *
     *
     * ============================================================
     * 13. BUILDER VS ABSTRACT FACTORY
     * ============================================================
     *
     * ABSTRACT FACTORY:
     *
     * Creates families of related objects.
     *
     * BUILDER:
     *
     * Constructs one complex object through a sequence of steps.
     *
     * Example:
     *
     * Abstract Factory:
     * Create WindowsButton and WindowsCheckbox.
     *
     * Builder:
     * Configure and construct a Computer with processor,
     * RAM, storage and graphics card.
     *
     *
     * ============================================================
     * 14. BUILDER AND IMMUTABILITY
     * ============================================================
     *
     * Builder is frequently used to create immutable objects.
     *
     * Typical design:
     *
     * - Private final fields.
     * - Private constructor.
     * - No public setters.
     * - Nested Builder class.
     * - build() creates the final object.
     *
     * Once constructed, the object's fields cannot be reassigned.
     *
     * However, if a field refers to a mutable List or Date,
     * additional defensive copying may be necessary.
     *
     *
     * ============================================================
     * 15. BUILDER IN JAVA LIBRARIES
     * ============================================================
     *
     * StringBuilder:
     *
     * StringBuilder is named Builder, but it is not an example
     * of the GoF Builder design pattern.
     *
     * It is a mutable sequence used to construct strings.
     *
     * StringBuilder builder = new StringBuilder();
     *
     * builder.append("Hello");
     * builder.append(" World");
     *
     * String result = builder.toString();
     *
     *
     * StringBuilder is useful for understanding step-by-step
     * construction, but don't confuse its name with the GoF pattern.
     *
     *
     * ============================================================
     * 16. BUILDER IN SPRING AND BACKEND DEVELOPMENT
     * ============================================================
     *
     * In backend applications, Builder is useful for constructing:
     *
     * - Request objects.
     * - Response DTOs.
     * - Configuration objects.
     * - Complex domain objects.
     * - Test data.
     *
     * Example with Lombok:
     *
     * @Builder
     * public class Employee {
     *     private String name;
     *     private String email;
     *     private String department;
     * }
     *
     * Usage:
     *
     * Employee employee = Employee.builder()
     *     .name("Vinay")
     *     .email("vinay@gmail.com")
     *     .department("Engineering")
     *     .build();
     *
     * Lombok generates Builder-related code at compile time.
     *
     * IMPORTANT:
     *
     * Lombok's @Builder is a convenient implementation technique.
     * It is not itself a separate design pattern.
     *
     *
     * ============================================================
     * 17. ADVANTAGES
     * ============================================================
     *
     * 1. Makes complex object construction readable.
     * 2. Supports optional configuration fields.
     * 3. Reduces the need for many overloaded constructors.
     * 4. Can centralize validation before object creation.
     * 5. Works well with immutable objects.
     * 6. Supports fluent method chaining.
     * 7. Separates construction from the final object's usage.
     *
     *
     * ============================================================
     * 18. DISADVANTAGES
     * ============================================================
     *
     * 1. Requires additional classes or code.
     * 2. Can be unnecessary for simple objects.
     * 3. May introduce duplication between Product and Builder.
     * 4. Mutable Builder objects require care when reused.
     *
     *
     * ============================================================
     * 19. WHEN SHOULD WE USE BUILDER?
     * ============================================================
     *
     * Use Builder when:
     *
     * - An object has many fields.
     * - Several fields are optional.
     * - Constructors become difficult to read.
     * - You need validation before construction.
     * - You want to create immutable objects conveniently.
     * - Construction involves multiple meaningful steps.
     *
     * Avoid Builder when:
     *
     * - The object has only one or two simple fields.
     * - A normal constructor is already clear.
     * - The additional Builder code provides no benefit.
     *
     *
     * ============================================================
     * 20. COMMON MISCONCEPTIONS
     * ============================================================
     *
     * 1. Builder is only for immutable objects.
     *    FALSE. It can construct mutable objects too.
     *
     * 2. Every Builder requires a Director.
     *    FALSE. Fluent Builders commonly do not use a Director.
     *
     * 3. Builder automatically validates all fields.
     *    FALSE. Validation must be implemented.
     *
     * 4. Builder automatically makes an object thread-safe.
     *    FALSE. Thread safety depends on the Product and Builder.
     *
     * 5. Every method named build() implements GoF Builder.
     *    FALSE. The overall construction design matters.
     *
     * 6. StringBuilder is the GoF Builder pattern.
     *    FALSE. It is a mutable string-construction class.
     *
     *
     * ============================================================
     * 21. INTERVIEW QUESTIONS
     * ============================================================
     *
     * Q1. What is Builder?
     *
     * A: A creational design pattern that separates complex
     *    object construction from its representation.
     *
     * Q2. What problem does it solve?
     *
     * A: It makes construction easier to read and manage when
     *    objects have many optional or configurable fields.
     *
     * Q3. What is a Director?
     *
     * A: A participant that controls the sequence of construction
     *    steps in the classic Builder pattern.
     *
     * Q4. Is a Director mandatory?
     *
     * A: No. Fluent Builder implementations commonly omit it.
     *
     * Q5. What is a Fluent API?
     *
     * A: An API that allows method chaining, often by returning
     *    the current object from configuration methods.
     *
     * Q6. How does Builder help with immutability?
     *
     * A: It allows configuration to happen in a separate Builder
     *    object and creates the final object once build() is called.
     *
     * Q7. What is the difference between Builder and Factory?
     *
     * A: Factory focuses on selecting or creating a product;
     *    Builder focuses on constructing a complex product
     *    through configurable steps.
     *
     *
     * ============================================================
     * 22. QUICK REVISION
     * ============================================================
     *
     * PATTERN: Builder
     *
     * CATEGORY: Creational
     *
     * PURPOSE:
     * Construct complex objects step by step.
     *
     * MAIN IDEA:
     * Configure first, build afterward.
     *
     * IMPORTANT METHOD:
     * build()
     *
     * COMMON STYLE:
     * Fluent API / Method Chaining.
     *
     * CLASSIC PARTICIPANTS:
     * Product, Builder, Concrete Builder, Director.
     *
     * MODERN JAVA STYLE:
     * Product with a nested Builder.
     *
     * REMEMBER:
     *
     * Constructor -> Pass values together.
     *
     * Setters -> Modify an already-created object.
     *
     * Builder -> Configure step by step, then create the object.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Builder Design Pattern , Happy Learning ..!");
    }
}
