
public class Encapsulation {

    

    /*
     * =================================================================
     *                       JAVA ENCAPSULATION
     * =================================================================
     *
     * 1. INTRODUCTION TO ENCAPSULATION
     *
     * Encapsulation is one of the four fundamental pillars of
     * Object-Oriented Programming.
     *
     * Encapsulation means combining data and the methods that
     * operate on that data within a class, while controlling
     * access to the internal state.
     *
     * It helps protect an object's state from uncontrolled
     * modification.
     *
     * MAIN OBJECTIVES:
     *
     * 1. Bundle data and related behaviour together.
     * 2. Restrict direct access to internal state.
     * 3. Provide controlled access through methods.
     * 4. Maintain object validity through validation.
     * 5. Reduce unwanted dependencies on implementation details.
     *
     * EXAMPLE:
     *
     *     class BankAccount {
     *
     *         private double balance;
     *
     *         public void deposit(double amount) {
     *             if (amount > 0) {
     *                 balance += amount;
     *             }
     *         }
     *
     *         public double getBalance() {
     *             return balance;
     *         }
     *     }
     *
     * Here:
     *
     * - balance is private.
     * - External classes cannot directly modify balance.
     * - deposit() controls how the balance changes.
     * - getBalance() provides read access.
     *
     *
     * =================================================================
     * 2. WHY IS ENCAPSULATION NEEDED?
     * =================================================================
     *
     * Consider a class without encapsulation:
     *
     *     class BankAccount {
     *
     *         public double balance;
     *     }
     *
     *     BankAccount account = new BankAccount();
     *
     *     account.balance = -100000;
     *
     * This allows invalid data to be assigned directly.
     *
     * PROBLEMS:
     *
     * 1. No validation of incoming values.
     * 2. Internal state can be modified unexpectedly.
     * 3. Business rules can be bypassed.
     * 4. Changes to internal representation affect external code.
     * 5. Debugging and maintenance become harder.
     *
     * Encapsulation provides controlled access.
     *
     * BENEFITS:
     *
     * - Data protection
     * - Validation
     * - Maintainability
     * - Modularity
     * - Reduced coupling
     * - Better control over object state
     *
     *
     * =================================================================
     * 3. DATA HIDING VS ENCAPSULATION
     * =================================================================
     *
     * These terms are related but not identical.
     *
     * DATA HIDING:
     *
     * Restricting direct access to internal data.
     *
     * Encapsulation commonly achieves this through private fields
     * and controlled methods.
     *
     * ENCAPSULATION:
     *
     * Combining data and related operations within a class and
     * controlling how its internal state is accessed or modified.
     *
     * COMPARISON:
     *
     * Feature          Encapsulation        Data Hiding
     * ----------------------------------------------------
     * Meaning          Bundling data and    Restricting access
     *                  related behaviour    to internal data
     * Focus            Class design         Visibility/control
     * Mechanism        Class structure      Access restrictions
     * Relationship     Broader concept      Common technique
     *
     * IMPORTANT:
     *
     * Encapsulation is not achieved merely by declaring fields private.
     * A good design also provides meaningful, controlled operations.
     *
     *
     * =================================================================
     * 4. IMPLEMENTING ENCAPSULATION IN JAVA
     * =================================================================
     *
     * A common implementation approach:
     *
     * 1. Declare fields private.
     * 2. Provide public methods when external access is required.
     * 3. Validate data before modifying internal state.
     * 4. Expose only the operations that make sense for the class.
     *
     * EXAMPLE:
     *
     *     class Employee {
     *
     *         private String name;
     *         private double salary;
     *
     *         public void setName(String name) {
     *             if (name != null && !name.isBlank()) {
     *                 this.name = name;
     *             }
     *         }
     *
     *         public String getName() {
     *             return name;
     *         }
     *
     *         public void setSalary(double salary) {
     *             if (salary >= 0) {
     *                 this.salary = salary;
     *             }
     *         }
     *
     *         public double getSalary() {
     *             return salary;
     *         }
     *     }
     *
     * The class controls how its fields are accessed and modified.
     *
     * IMPORTANT:
     *
     * Getters and setters are common techniques, not mandatory
     * requirements for encapsulation.
     *
     *
     * =================================================================
     * 5. GETTERS AND SETTERS
     * =================================================================
     *
     * GETTER:
     *
     * A method used to retrieve a field's value.
     *
     * Common naming convention:
     *
     *     getFieldName()
     *
     * SETTER:
     *
     * A method used to modify a field's value.
     *
     * Common naming convention:
     *
     *     setFieldName(value)
     *
     * EXAMPLE:
     *
     *     class Student {
     *
     *         private int age;
     *
     *         public int getAge() {
     *             return age;
     *         }
     *
     *         public void setAge(int age) {
     *             if (age > 0) {
     *                 this.age = age;
     *             }
     *         }
     *     }
     *
     * USAGE:
     *
     *     Student student = new Student();
     *
     *     student.setAge(21);
     *
     *     System.out.println(student.getAge());
     *
     * IMPORTANT:
     *
     * 1. Getters and setters provide controlled access.
     * 2. A setter can validate values before assignment.
     * 3. A getter can return a transformed or calculated value.
     * 4. A class can expose a getter without providing a setter.
     * 5. A class can provide a setter without exposing a getter.
     * 6. A field does not need a getter or setter in every design.
     *
     *
     * =================================================================
     * 6. VALIDATION THROUGH SETTERS
     * =================================================================
     *
     * Setters can enforce business rules before modifying fields.
     *
     * EXAMPLE:
     *
     *     class BankAccount {
     *
     *         private double balance;
     *
     *         public void setBalance(double balance) {
     *
     *             if (balance < 0) {
     *                 throw new IllegalArgumentException(
     *                     "Balance cannot be negative"
     *                 );
     *             }
     *
     *             this.balance = balance;
     *         }
     *
     *         public double getBalance() {
     *             return balance;
     *         }
     *     }
     *
     * INVALID INPUT:
     *
     *     account.setBalance(-500);
     *
     * This throws IllegalArgumentException.
     *
     * IMPORTANT:
     *
     * Validation should protect the object's invariants, meaning
     * conditions that must remain true for a valid object.
     *
     * A setter should not silently accept invalid state.
     * Choose a clear strategy, such as throwing an exception
     * or returning an explicit validation result.
     *
     *
     * =================================================================
     * 7. READ-ONLY AND WRITE-ONLY PROPERTIES
     * =================================================================
     *
     * A. READ-ONLY ACCESS
     *
     * A class exposes a getter but no public setter.
     *
     * EXAMPLE:
     *
     *     class Employee {
     *
     *         private final int id;
     *
     *         Employee(int id) {
     *             this.id = id;
     *         }
     *
     *         public int getId() {
     *             return id;
     *         }
     *     }
     *
     * External code can read the ID but cannot change it through
     * a public setter.
     *
     * B. WRITE-ONLY ACCESS
     *
     * A class exposes a setter or input operation without a
     * corresponding getter.
     *
     * EXAMPLE:
     *
     *     class Credential {
     *
     *         private String password;
     *
     *         public void setPassword(String password) {
     *             this.password = password;
     *         }
     *     }
     *
     * This design can be useful when a value should be supplied
     * but not exposed through an ordinary getter.
     *
     * IMPORTANT:
     *
     * Write-only access does not automatically guarantee security.
     * Sensitive data needs appropriate storage and handling.
     *
     *
     * =================================================================
     * 8. IMMUTABLE CLASSES
     * =================================================================
     *
     * An immutable object is an object whose observable state
     * cannot be changed after construction.
     *
     * EXAMPLE:
     *
     *     final class Employee {
     *
     *         private final int id;
     *         private final String name;
     *
     *         Employee(int id, String name) {
     *             this.id = id;
     *             this.name = name;
     *         }
     *
     *         public int getId() {
     *             return id;
     *         }
     *
     *         public String getName() {
     *             return name;
     *         }
     *     }
     *
     * FEATURES:
     *
     * 1. Prevent external modification of state.
     * 2. Avoid exposing setters for immutable fields.
     * 3. Initialize required state during construction.
     * 4. Use private final fields where appropriate.
     * 5. Prevent subclassing when necessary to preserve invariants.
     * 6. Avoid exposing mutable internal objects.
     *
     * IMPORTANT:
     *
     * final on a reference prevents reassignment of that reference.
     * It does not automatically make the referenced object immutable.
     *
     *
     * =================================================================
     * 9. DEFENSIVE COPYING
     * =================================================================
     *
     * Defensive copying protects internal mutable objects from
     * external modification.
     *
     * Consider:
     *
     *     class Student {
     *
     *         private java.util.Date birthDate;
     *
     *         Student(java.util.Date birthDate) {
     *             this.birthDate = birthDate;
     *         }
     *
     *         public java.util.Date getBirthDate() {
     *             return birthDate;
     *         }
     *     }
     *
     * PROBLEM:
     *
     * The caller can modify the Date object passed to the constructor
     * or modify the Date returned by the getter.
     *
     * DEFENSIVE COPYING:
     *
     *     class Student {
     *
     *         private final java.util.Date birthDate;
     *
     *         Student(java.util.Date birthDate) {
     *             this.birthDate =
     *                 new java.util.Date(birthDate.getTime());
     *         }
     *
     *         public java.util.Date getBirthDate() {
     *             return new java.util.Date(birthDate.getTime());
     *         }
     *     }
     *
     * Both constructor input and getter output are copied.
     *
     * IMPORTANT:
     *
     * 1. Copy mutable input objects when retaining them internally.
     * 2. Return copies instead of internal mutable references.
     * 3. A shallow copy may not protect nested mutable objects.
     * 4. Deep copying may be necessary for nested object graphs.
     * 5. Immutable types such as String can safely be shared.
     *
     *
     * =================================================================
     * 10. SHALLOW COPY VS DEEP COPY
     * =================================================================
     *
     * SHALLOW COPY:
     *
     * Copies the outer object, but nested object references may
     * still be shared.
     *
     * DEEP COPY:
     *
     * Copies the outer object and the mutable objects it owns,
     * according to the chosen copying policy.
     *
     * EXAMPLE:
     *
     *     class Address {
     *         String city;
     *     }
     *
     *     class Person {
     *         String name;
     *         Address address;
     *     }
     *
     * A shallow copy of Person may share the same Address object.
     *
     * Changing the shared Address can affect both Person objects.
     *
     * A deep copy creates a separate Address object as well.
     *
     * IMPORTANT:
     *
     * Java does not automatically deep-copy an object graph.
     * Copying strategy must be designed according to the domain.
     *
     *
     * =================================================================
     * 11. ENCAPSULATION AND ACCESS MODIFIERS
     * =================================================================
     *
     * Access modifiers help control visibility.
     *
     * private:
     *     Restricts access to the private-access scope.
     *
     * package-private:
     *     Allows access within the same package.
     *
     * protected:
     *     Allows same-package access and qualified subclass access.
     *
     * public:
     *     Provides the widest language-level access.
     *
     * EXAMPLE:
     *
     *     class Account {
     *
     *         private double balance;
     *
     *         public double getBalance() {
     *             return balance;
     *         }
     *     }
     *
     * private is commonly used for internal fields.
     *
     * IMPORTANT:
     *
     * Access modifiers are not the whole of encapsulation.
     * Good encapsulation also depends on meaningful APIs,
     * validation and avoiding unintended exposure of mutable state.
     *
     *
     * =================================================================
     * 12. ENCAPSULATION VS IMMUTABILITY
     * =================================================================
     *
     * ENCAPSULATION:
     *
     * Controls access to internal state and related operations.
     *
     * IMMUTABILITY:
     *
     * Prevents observable state changes after construction.
     *
     * COMPARISON:
     *
     * Feature          Encapsulation         Immutability
     * -------------------------------------------------------
     * Goal             Control access        Prevent state changes
     * Modification     May be allowed        Not allowed
     * Setters          May be provided       Usually absent
     * State            Can change            Remains unchanged
     *
     * An encapsulated class can be mutable or immutable.
     *
     * An immutable class is commonly designed using encapsulation.
     *
     *
     * =================================================================
     * 13. ENCAPSULATION AND ABSTRACTION
     * =================================================================
     *
     * Abstraction exposes essential functionality and hides
     * implementation complexity.
     *
     * Encapsulation controls access to internal state and combines
     * data with related behaviour.
     *
     * EXAMPLE:
     *
     *     class Payment {
     *
     *         private double amount;
     *
     *         public void pay() {
     *             validate();
     *             processPayment();
     *         }
     *
     *         private void validate() {
     *             // Validation logic
     *         }
     *
     *         private void processPayment() {
     *             // Internal processing
     *         }
     *     }
     *
     * Encapsulation:
     *     amount and internal operations are controlled.
     *
     * Abstraction:
     *     The user calls pay() without needing to understand
     *     the internal processing.
     *
     *
     * =================================================================
     * 14. ENCAPSULATION IN COLLECTIONS
     * =================================================================
     *
     * Returning a mutable collection directly can expose
     * an object's internal state.
     *
     * EXAMPLE:
     *
     *     class Team {
     *
     *         private java.util.List<String> members =
     *             new java.util.ArrayList<>();
     *
     *         public java.util.List<String> getMembers() {
     *             return members;
     *         }
     *     }
     *
     * PROBLEM:
     *
     *     team.getMembers().clear();
     *
     * External code can modify the internal collection.
     *
     * SOLUTION 1: UNMODIFIABLE VIEW
     *
     *     public java.util.List<String> getMembers() {
     *         return java.util.Collections.unmodifiableList(
     *             members
     *         );
     *     }
     *
     * This prevents modification through the returned view.
     * However, it is a view backed by the original collection.
     * Internal changes may still be visible through the view.
     *
     * SOLUTION 2: DEFENSIVE COPY
     *
     *     public java.util.List<String> getMembers() {
     *         return new java.util.ArrayList<>(members);
     *     }
     *
     * This returns a separate collection.
     *
     * SOLUTION 3: IMMUTABLE SNAPSHOT
     *
     *     public java.util.List<String> getMembers() {
     *         return java.util.List.copyOf(members);
     *     }
     *
     * List.copyOf() returns an unmodifiable list.
     * It is a shallow copy of the collection structure.
     * Its elements may still be mutable.
     *
     * IMPORTANT:
     *
     * Choose a strategy according to whether callers need:
     *
     * - A live read-only view
     * - A separate mutable copy
     * - An unmodifiable snapshot
     *
     *
     * =================================================================
     * 15. ENCAPSULATION AND OBJECT INVARIANTS
     * =================================================================
     *
     * An invariant is a condition that must remain true for a
     * valid object throughout its lifetime.
     *
     * EXAMPLE:
     *
     * A bank account may enforce:
     *
     *     balance >= 0
     *
     * A class should ensure that all public operations preserve
     * its invariants.
     *
     * EXAMPLE:
     *
     *     class BankAccount {
     *
     *         private double balance;
     *
     *         BankAccount(double initialBalance) {
     *
     *             if (initialBalance < 0) {
     *                 throw new IllegalArgumentException();
     *             }
     *
     *             balance = initialBalance;
     *         }
     *
     *         public void withdraw(double amount) {
     *
     *             if (amount <= 0 || amount > balance) {
     *                 throw new IllegalArgumentException();
     *             }
     *
     *             balance -= amount;
     *         }
     *     }
     *
     * All state-changing operations must preserve the invariant.
     *
     *
     * =================================================================
     * 16. ENCAPSULATION IN REAL-WORLD APPLICATIONS
     * =================================================================
     *
     * 1. BANKING:
     *
     *    Balance is modified only through validated transactions.
     *
     * 2. USER MANAGEMENT:
     *
     *    Passwords and account status are handled through
     *    controlled operations.
     *
     * 3. E-COMMERCE:
     *
     *    Order totals and payment status are updated through
     *    business operations.
     *
     * 4. INVENTORY:
     *
     *    Stock quantity is changed through validated operations.
     *
     * 5. SPRING APPLICATIONS:
     *
     *    Domain entities and service classes encapsulate state
     *    and business behaviour.
     *
     * IMPORTANT:
     *
     * Encapsulation is not a substitute for authentication,
     * authorization, encryption or database security.
     *
     *
     * =================================================================
     * 17. COMMON MISTAKES
     * =================================================================
     *
     * 1. Declaring fields private but returning mutable internal
     *    objects directly.
     *
     * 2. Creating setters for every field without considering
     *    whether modification should be permitted.
     *
     * 3. Treating getters and setters as the complete definition
     *    of encapsulation.
     *
     * 4. Assuming final references make referenced objects immutable.
     *
     * 5. Performing incomplete validation in setters.
     *
     * 6. Exposing internal collections directly.
     *
     * 7. Confusing encapsulation with abstraction.
     *
     * 8. Assuming private fields alone guarantee application security.
     *
     * 9. Returning shallow copies when deep copying is required.
     *
     * 10. Allowing public operations to violate object invariants.
     *
     *
     * =================================================================
     * 18. INTERVIEW QUESTIONS
     * =================================================================
     *
     * Q1. What is encapsulation?
     *
     * Combining data and related behaviour within a class while
     * controlling access to internal state.
     *
     * Q2. How is encapsulation implemented in Java?
     *
     * Commonly through private fields and controlled public methods.
     *
     * Q3. What is data hiding?
     *
     * Restricting direct access to internal data.
     *
     * Q4. Is encapsulation the same as data hiding?
     *
     * No. Data hiding is one aspect of encapsulation.
     *
     * Q5. Are getters and setters mandatory?
     *
     * No. They are common techniques, not mandatory requirements.
     *
     * Q6. Can an encapsulated class be mutable?
     *
     * Yes.
     *
     * Q7. What is an immutable class?
     *
     * A class whose objects cannot have their observable state
     * changed after construction.
     *
     * Q8. What is defensive copying?
     *
     * Creating copies to prevent external code from modifying
     * internal mutable objects.
     *
     * Q9. What is the difference between shallow and deep copying?
     *
     * Shallow copying may share nested references; deep copying
     * duplicates the relevant nested mutable objects.
     *
     * Q10. Does private guarantee complete data security?
     *
     * No. It controls Java language-level access, not all forms
     * of security or exposure.
     *
     * Q11. Can a getter modify an object's state?
     *
     * It can, although a getter is conventionally expected to
     * retrieve data without unexpected side effects.
     *
     * Q12. What is an object invariant?
     *
     * A condition that must remain true for an object to be valid.
     *
     * Q13. How can a collection's internal state be protected?
     *
     * By returning an unmodifiable view, defensive copy or
     * immutable snapshot, depending on requirements.
     *
     * Q14. What is the difference between encapsulation
     *      and immutability?
     *
     * Encapsulation controls access; immutability prevents
     * observable state changes.
     *
     *
     * =================================================================
     * 19. FINAL REVISION CHECKLIST
     * =================================================================
     *
     * FUNDAMENTALS:
     *
     * [ ] Definition and purpose of encapsulation
     * [ ] Need for encapsulation
     * [ ] Data hiding vs encapsulation
     * [ ] Abstraction vs encapsulation
     *
     * IMPLEMENTATION:
     *
     * [ ] Private fields
     * [ ] Getters and setters
     * [ ] Validation
     * [ ] Read-only properties
     * [ ] Write-only properties
     * [ ] Access modifiers
     *
     * ADVANCED:
     *
     * [ ] Immutable classes
     * [ ] Defensive copying
     * [ ] Shallow vs deep copy
     * [ ] Collection encapsulation
     * [ ] Object invariants
     * [ ] Encapsulation in real applications
     *
     * INTERVIEW PREPARATION:
     *
     * [ ] Common mistakes
     * [ ] Practical design scenarios
     * [ ] Interview questions
     *
     *
     * =================================================================
     *                      END OF ENCAPSULATION
     * =================================================================
     */

    public static void main(String[] args) {
        System.out.print("The above is all about Encapsulation , Happy Learning ..!");
    }
}
