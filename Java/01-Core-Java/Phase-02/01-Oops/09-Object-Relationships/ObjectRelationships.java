
public class ObjectRelationships {

    

    /*
     * ============================================================
     *                    OBJECT RELATIONSHIPS
     * ============================================================
     *
     * 1. INTRODUCTION
     *
     * Object relationships describe how objects of different
     * classes are connected, communicate, collaborate, or
     * depend on one another.
     *
     * In Object-Oriented Programming, classes represent types
     * and objects interact to perform application operations.
     *
     * Example:
     *
     *      class Student {}
     *      class Department {}
     *      class Teacher {}
     *
     * A Student may belong to a Department.
     * A Teacher may teach Students.
     * A Department may have multiple Teachers.
     *
     * These connections represent object relationships.
     *
     * Understanding relationships helps us design:
     * - Maintainable applications.
     * - Reusable classes.
     * - Loosely coupled systems.
     * - Correct object ownership.
     * - Clear object lifecycles.
     * - Extensible application architectures.
     *
     *
     * 2. TYPES OF OBJECT RELATIONSHIPS
     *
     * Common object-oriented relationships:
     *
     * 1. IS-A relationship.
     * 2. HAS-A relationship.
     * 3. Association.
     * 4. Aggregation.
     * 5. Composition.
     * 6. Dependency.
     *
     * Important:
     * Aggregation and composition are specialized forms
     * of association.
     *
     * HAS-A is a general way of describing a relationship
     * where one object holds or uses another object.
     *
     * IS-A is generally represented through inheritance
     * or interface implementation.
     *
     *
     * 3. IS-A RELATIONSHIP
     *
     * IS-A represents a subtype relationship.
     *
     * It indicates that one type is a specialized form
     * of another type.
     *
     * It is commonly implemented using:
     *
     *      extends
     *      implements
     *
     * Example using class inheritance:
     *
     *      class Animal {}
     *
     *      class Dog extends Animal {}
     *
     * Dog IS-A Animal.
     *
     * Example using interface implementation:
     *
     *      interface Flyable {}
     *
     *      class Bird implements Flyable {}
     *
     * Bird IS-A Flyable type.
     *
     * IMPORTANT:
     * IS-A is a type relationship, not necessarily a
     * statement about physical ownership.
     *
     *
     * 4. IS-A RELATIONSHIP EXAMPLE
     *
     *      class Vehicle {
     *          void start() {
     *              System.out.println("Vehicle started");
     *          }
     *      }
     *
     *      class Car extends Vehicle {
     *          void drive() {
     *              System.out.println("Car driving");
     *          }
     *      }
     *
     *      Car car = new Car();
     *
     *      car.start();
     *      car.drive();
     *
     * Car inherits the accessible behavior of Vehicle.
     *
     * A Car object can be treated as a Vehicle:
     *
     *      Vehicle vehicle = new Car();
     *
     * This is an example of upcasting and polymorphism.
     *
     *
     * 5. IS-A AND LISKOV SUBSTITUTION PRINCIPLE
     *
     * A subtype should be usable wherever its supertype
     * is expected without breaking the correctness of
     * the program.
     *
     * This idea is formalized by the Liskov Substitution
     * Principle (LSP).
     *
     * Example:
     *
     *      class Bird {
     *          void eat() {}
     *      }
     *
     *      class Sparrow extends Bird {
     *          void fly() {}
     *      }
     *
     * Sparrow is a Bird and can be used where Bird is expected.
     *
     * However, not every biological Bird can necessarily fly.
     *
     * Modeling all birds with a mandatory fly() method can
     * create an incorrect abstraction.
     *
     * Better design:
     *
     *      interface Flyable {
     *          void fly();
     *      }
     *
     *      class Sparrow extends Bird implements Flyable {}
     *
     *      class Penguin extends Bird {}
     *
     * This separates the general Bird type from the
     * flying capability.
     *
     *
     * 6. HAS-A RELATIONSHIP
     *
     * HAS-A describes a relationship in which one object
     * contains, holds, or uses a reference to another object.
     *
     * It is commonly implemented through instance fields.
     *
     * Example:
     *
     *      class Engine {}
     *
     *      class Car {
     *          Engine engine;
     *      }
     *
     * A Car HAS-A Engine.
     *
     * HAS-A relationships are commonly implemented using
     * composition or aggregation, depending on ownership
     * and lifecycle semantics.
     *
     * IMPORTANT:
     * HAS-A does not automatically mean composition.
     *
     *
     * 7. ASSOCIATION
     *
     * Association is a general relationship between
     * independent objects.
     *
     * It indicates that objects are connected or
     * communicate with one another.
     *
     * Neither object necessarily owns the other.
     *
     * Example:
     *
     *      class Teacher {
     *          String name;
     *      }
     *
     *      class Student {
     *          String name;
     *      }
     *
     * A Teacher teaches Students.
     *
     * The Teacher and Student can exist independently.
     *
     * Association describes the connection between
     * their objects.
     *
     *
     * 8. IMPLEMENTING ASSOCIATION IN JAVA
     *
     * Association can be implemented using object references.
     *
     * Example:
     *
     *      class Teacher {
     *          String name;
     *
     *          Teacher(String name) {
     *              this.name = name;
     *          }
     *
     *          void teach(Student student) {
     *              System.out.println(
     *                  name + " teaches " + student.name
     *              );
     *          }
     *      }
     *
     *      class Student {
     *          String name;
     *
     *          Student(String name) {
     *              this.name = name;
     *          }
     *      }
     *
     *      Teacher teacher = new Teacher("Ravi");
     *      Student student = new Student("Vinay");
     *
     *      teacher.teach(student);
     *
     * Both objects are created independently.
     * The Teacher temporarily interacts with the Student.
     *
     * This is an example of association.
     *
     *
     * 9. ASSOCIATION CARDINALITY
     *
     * Cardinality describes how many objects can participate
     * in a relationship.
     *
     * Common forms:
     *
     * A. One-to-one
     *
     * One object is associated with one other object.
     *
     * Example:
     * One Person has one Passport in a particular model.
     *
     * B. One-to-many
     *
     * One object is associated with multiple objects.
     *
     * Example:
     * One Department has multiple Employees.
     *
     * C. Many-to-one
     *
     * Multiple objects are associated with one object.
     *
     * Example:
     * Multiple Employees belong to one Department.
     *
     * D. Many-to-many
     *
     * Multiple objects are associated with multiple objects.
     *
     * Example:
     * Students enroll in multiple Courses.
     * Each Course has multiple Students.
     *
     * Cardinality depends on the application's domain rules.
     *
     *
     * 10. ONE-TO-ONE ASSOCIATION
     *
     * Example:
     *
     *      class Person {
     *          String name;
     *          Passport passport;
     *
     *          Person(String name, Passport passport) {
     *              this.name = name;
     *              this.passport = passport;
     *          }
     *      }
     *
     *      class Passport {
     *          String passportNumber;
     *
     *          Passport(String passportNumber) {
     *              this.passportNumber = passportNumber;
     *          }
     *      }
     *
     *      Passport passport = new Passport("P12345");
     *      Person person = new Person("Vinay", passport);
     *
     * One Person object refers to one Passport object.
     *
     * This example demonstrates a one-to-one reference
     * relationship.
     *
     *
     * 11. ONE-TO-MANY ASSOCIATION
     *
     * Example:
     *
     *      class Department {
     *          String name;
     *          List<Employee> employees;
     *
     *          Department(String name) {
     *              this.name = name;
     *              this.employees = new ArrayList<>();
     *          }
     *
     *          void addEmployee(Employee employee) {
     *              employees.add(employee);
     *          }
     *      }
     *
     *      class Employee {
     *          String name;
     *
     *          Employee(String name) {
     *              this.name = name;
     *          }
     *      }
     *
     *      Department department =
     *          new Department("Engineering");
     *
     *      department.addEmployee(new Employee("A"));
     *      department.addEmployee(new Employee("B"));
     *
     * One Department references multiple Employee objects.
     *
     * Required imports:
     *
     *      import java.util.List;
     *      import java.util.ArrayList;
     *
     *
     * 12. MANY-TO-MANY ASSOCIATION
     *
     * Example:
     *
     *      class Student {
     *          String name;
     *          List<Course> courses = new ArrayList<>();
     *      }
     *
     *      class Course {
     *          String title;
     *          List<Student> students = new ArrayList<>();
     *      }
     *
     * A Student can enroll in multiple Courses.
     * A Course can contain multiple Students.
     *
     * In a real application, both sides must be kept
     * consistent when relationships are added or removed.
     *
     * In database modeling, many-to-many relationships
     * are commonly represented using a join table.
     *
     *
     * 13. ASSOCIATION DIRECTION
     *
     * Association can be:
     *
     * A. Unidirectional
     * B. Bidirectional
     *
     * UNIDIRECTIONAL:
     *
     * One class knows about or holds a reference to another.
     *
     * Example:
     *
     *      class Car {
     *          Engine engine;
     *      }
     *
     * Car has a reference to Engine.
     * Engine does not necessarily have a reference to Car.
     *
     * BIDIRECTIONAL:
     *
     * Both classes hold references to one another.
     *
     * Example:
     *
     *      class Person {
     *          Passport passport;
     *      }
     *
     *      class Passport {
     *          Person owner;
     *      }
     *
     * Both objects can navigate to the other.
     *
     * Bidirectional relationships require careful
     * synchronization of both references.
     *
     *
     * 14. AGGREGATION
     *
     * Aggregation is a specialized form of association.
     *
     * It represents a whole-part relationship where the
     * part can exist independently of the whole.
     *
     * Example:
     *
     * A Department has Employees.
     *
     * Employees can exist independently of a Department.
     * An Employee may move from one Department to another.
     *
     * Aggregation is often described as a weak HAS-A
     * relationship.
     *
     * UML notation:
     *
     * A hollow diamond is placed on the whole/aggregate side.
     *
     *
     * 15. IMPLEMENTING AGGREGATION IN JAVA
     *
     * Example:
     *
     *      class Employee {
     *          String name;
     *
     *          Employee(String name) {
     *              this.name = name;
     *          }
     *      }
     *
     *      class Department {
     *          String name;
     *          List<Employee> employees;
     *
     *          Department(
     *              String name,
     *              List<Employee> employees
     *          ) {
     *              this.name = name;
     *              this.employees = employees;
     *          }
     *      }
     *
     *      Employee e1 = new Employee("Vinay");
     *      Employee e2 = new Employee("Rahul");
     *
     *      List<Employee> employees =
     *          new ArrayList<>();
     *
     *      employees.add(e1);
     *      employees.add(e2);
     *
     *      Department department =
     *          new Department("Engineering", employees);
     *
     * The Employee objects are created separately.
     * The Department receives references to existing objects.
     *
     * This is a common Java representation of aggregation.
     *
     *
     * 16. AGGREGATION CHARACTERISTICS
     *
     * - Represents a whole-part relationship.
     * - The part can exist independently.
     * - The whole references the part.
     * - The part may be shared depending on the domain.
     * - The whole does not necessarily control the part's
     *   entire lifecycle.
     *
     * IMPORTANT:
     * Java does not have a dedicated aggregation keyword.
     *
     * Aggregation is a modeling concept represented through
     * object references and application design.
     *
     *
     * 17. COMPOSITION
     *
     * Composition is a stronger form of association.
     *
     * It represents a whole-part relationship with strong
     * ownership.
     *
     * The part is conceptually owned by the whole and
     * generally does not have an independent lifecycle
     * in the domain model.
     *
     * Example:
     *
     * A House contains Rooms.
     *
     * In a simplified model, Rooms are considered parts
     * of the House.
     *
     * UML notation:
     *
     * A filled diamond is placed on the whole/composite side.
     *
     *
     * 18. IMPLEMENTING COMPOSITION IN JAVA
     *
     * Example:
     *
     *      class Room {
     *          String name;
     *
     *          Room(String name) {
     *              this.name = name;
     *          }
     *      }
     *
     *      class House {
     *          private final List<Room> rooms;
     *
     *          House() {
     *              rooms = new ArrayList<>();
     *
     *              rooms.add(new Room("Bedroom"));
     *              rooms.add(new Room("Kitchen"));
     *              rooms.add(new Room("Living Room"));
     *          }
     *      }
     *
     *      House house = new House();
     *
     * House creates and owns its Room objects in this model.
     *
     * The Room references are not exposed directly.
     *
     * Required imports:
     *
     *      import java.util.List;
     *      import java.util.ArrayList;
     *
     *
     * 19. COMPOSITION CHARACTERISTICS
     *
     * - Represents a strong whole-part relationship.
     * - The whole controls the creation or ownership
     *   of its parts in the domain model.
     * - Parts are generally not shared between multiple
     *   composite owners.
     * - The part's lifecycle is tied to the whole's
     *   lifecycle in the domain model.
     *
     * IMPORTANT:
     * Java garbage collection does not automatically
     * implement composition lifecycle rules.
     *
     * A Room object can remain alive if another reachable
     * object holds a reference to it.
     *
     * Composition is a semantic ownership rule, not a
     * special Java memory-management feature.
     *
     *
     * 20. AGGREGATION VS COMPOSITION
     *
     * AGGREGATION:
     *
     * - Weak whole-part relationship.
     * - Parts can exist independently.
     * - Parts may be shared.
     * - Whole does not necessarily control part lifecycle.
     * - UML uses a hollow diamond.
     *
     * COMPOSITION:
     *
     * - Strong whole-part relationship.
     * - Parts are owned by the whole.
     * - Parts are generally not shared between composites.
     * - Part lifecycle is tied to the whole in the domain model.
     * - UML uses a filled diamond.
     *
     * Example aggregation:
     *
     * Department -> Employee
     *
     * Example composition:
     *
     * House -> Room
     *
     * IMPORTANT:
     * These are domain-model examples, not automatic
     * classifications based only on Java field declarations.
     *
     *
     * 21. ASSOCIATION VS AGGREGATION VS COMPOSITION
     *
     * ASSOCIATION:
     * A general connection between objects.
     *
     * AGGREGATION:
     * A whole-part association with independent parts.
     *
     * COMPOSITION:
     * A whole-part association with strong ownership
     * and dependent lifecycle semantics.
     *
     * Relationship strength:
     *
     *      Association
     *          |
     *          +-- Aggregation
     *          |
     *          +-- Composition
     *
     * Aggregation and composition are specialized
     * association relationships.
     *
     *
     * 22. DEPENDENCY
     *
     * Dependency represents a relationship in which one
     * class uses another class to perform an operation.
     *
     * The dependent class needs the other class for
     * some behavior.
     *
     * A dependency can be temporary and may not be stored
     * as an instance field.
     *
     * Common ways to express dependency:
     *
     * - Method parameters.
     * - Local variables.
     * - Method return types.
     * - Constructor parameters.
     * - Static method calls.
     *
     * Example:
     *
     *      class Printer {
     *          void print(String message) {
     *              System.out.println(message);
     *          }
     *      }
     *
     *      class ReportService {
     *          void generateReport(Printer printer) {
     *              printer.print("Report generated");
     *          }
     *      }
     *
     * ReportService depends on Printer inside the method.
     *
     *
     * 23. DEPENDENCY THROUGH METHOD PARAMETERS
     *
     * Example:
     *
     *      class EmailService {
     *          void send(String message) {}
     *      }
     *
     *      class UserService {
     *          void registerUser(EmailService emailService) {
     *              emailService.send("Welcome");
     *          }
     *      }
     *
     * UserService uses EmailService as a method parameter.
     *
     * The dependency is supplied when the method is called.
     *
     *
     * 24. DEPENDENCY THROUGH LOCAL VARIABLES
     *
     * Example:
     *
     *      class FileReaderService {
     *          void read() {
     *              File file = new File("data.txt");
     *          }
     *      }
     *
     * FileReaderService uses File locally.
     *
     * This is a dependency even though the File reference
     * is not stored as an instance field.
     *
     *
     * 25. DEPENDENCY THROUGH CONSTRUCTOR PARAMETERS
     *
     * Example:
     *
     *      class Engine {}
     *
     *      class Car {
     *          private final Engine engine;
     *
     *          Car(Engine engine) {
     *              this.engine = engine;
     *          }
     *      }
     *
     * Car depends on Engine.
     *
     * Since the reference is retained as a field, this
     * also represents a structural HAS-A relationship.
     *
     * The exact classification depends on ownership
     * and lifecycle semantics.
     *
     *
     * 26. DEPENDENCY VS ASSOCIATION
     *
     * DEPENDENCY:
     * - One class temporarily uses another.
     * - Often appears in method parameters or local variables.
     * - Does not necessarily retain a reference.
     *
     * ASSOCIATION:
     * - Represents a structural relationship between objects.
     * - Commonly represented by stored references.
     * - Can be unidirectional or bidirectional.
     *
     * A dependency can exist without a long-lived association.
     *
     *
     * 27. HAS-A VS IS-A
     *
     * IS-A:
     * - Represents inheritance or interface implementation.
     * - Describes a subtype relationship.
     * - Uses extends or implements.
     *
     * HAS-A:
     * - Represents an object relationship.
     * - Describes holding or using another object.
     * - Commonly uses fields or references.
     *
     * Example IS-A:
     *
     *      class Dog extends Animal {}
     *
     * Example HAS-A:
     *
     *      class Car {
     *          Engine engine;
     *      }
     *
     * A Dog is an Animal.
     * A Car has an Engine.
     *
     *
     * 28. INHERITANCE VS COMPOSITION
     *
     * INHERITANCE:
     *
     * - Establishes an IS-A relationship.
     * - Reuses behavior through a superclass.
     * - Creates a subtype relationship.
     * - Can introduce tight coupling to the parent class.
     * - Supports overriding and runtime polymorphism.
     *
     * COMPOSITION:
     *
     * - Establishes a HAS-A relationship.
     * - Reuses behavior by collaborating with other objects.
     * - Does not require a subtype relationship.
     * - Allows components to be replaced independently.
     * - Often supports flexible designs.
     *
     * Example inheritance:
     *
     *      class Dog extends Animal {}
     *
     * Example composition:
     *
     *      class Car {
     *          private Engine engine;
     *      }
     *
     * Prefer composition when the relationship represents
     * using or owning another object rather than being
     * a specialized form of that type.
     *
     *
     * 29. COMPOSITION OVER INHERITANCE
     *
     * Composition over inheritance is a design guideline.
     *
     * It suggests preferring object composition when it
     * provides more flexibility than an inheritance hierarchy.
     *
     * Example:
     *
     *      interface Engine {
     *          void start();
     *      }
     *
     *      class PetrolEngine implements Engine {
     *          public void start() {}
     *      }
     *
     *      class ElectricEngine implements Engine {
     *          public void start() {}
     *      }
     *
     *      class Car {
     *          private final Engine engine;
     *
     *          Car(Engine engine) {
     *              this.engine = engine;
     *          }
     *
     *          void start() {
     *              engine.start();
     *          }
     *      }
     *
     * Usage:
     *
     *      Car petrolCar = new Car(new PetrolEngine());
     *      Car electricCar = new Car(new ElectricEngine());
     *
     * Car can use different engine implementations.
     *
     * It does not need separate subclasses for every
     * combination of car and engine type.
     *
     * IMPORTANT:
     * Composition is not always better.
     * Use inheritance when a valid subtype relationship exists.
     *
     *
     * 30. OBJECT OWNERSHIP
     *
     * Object ownership describes which object or component
     * is responsible for managing another object's lifecycle
     * or usage.
     *
     * Common ownership questions:
     *
     * - Who creates the object?
     * - Who stores the reference?
     * - Who is allowed to modify it?
     * - Can multiple objects share it?
     * - Who is responsible for closing external resources?
     * - Can the object exist independently?
     *
     * Ownership is especially important in:
     *
     * - Composition.
     * - Resource management.
     * - Dependency injection.
     * - Domain-driven design.
     * - Object lifecycle management.
     *
     * Java does not enforce a universal ownership system.
     * Ownership is normally expressed through class design
     * and application rules.
     *
     *
     * 31. OBJECT LIFECYCLE AND RELATIONSHIPS
     *
     * Consider:
     *
     *      class House {
     *          private Room room;
     *
     *          House() {
     *              room = new Room();
     *          }
     *      }
     *
     * House creates and stores a Room reference.
     *
     * However, Java's garbage collector reclaims objects
     * based on reachability, not merely on the relationship
     * being called composition.
     *
     * If another reachable object holds a reference to Room,
     * Room can remain reachable even if House is unreachable.
     *
     * Therefore:
     * - Domain lifecycle ownership and garbage collection
     *   are different concepts.
     * - A reference field alone does not enforce composition.
     * - Resource cleanup must be handled explicitly when
     *   external resources are involved.
     *
     *
     * 32. OBJECT RELATIONSHIPS AND UML
     *
     * UML stands for Unified Modeling Language.
     *
     * UML class diagrams can represent relationships
     * between classes.
     *
     * Common relationship notations:
     *
     * ASSOCIATION:
     * A solid line between classes.
     *
     * AGGREGATION:
     * A solid line with a hollow diamond at the whole side.
     *
     * COMPOSITION:
     * A solid line with a filled diamond at the whole side.
     *
     * DEPENDENCY:
     * A dashed arrow from the dependent class toward
     * the class it uses.
     *
     * GENERALIZATION:
     * A solid line with a hollow triangle pointing toward
     * the superclass.
     *
     * REALIZATION:
     * A dashed line with a hollow triangle pointing toward
     * the interface.
     *
     *
     * 33. UML MULTIPLICITY
     *
     * Multiplicity specifies how many instances can
     * participate in a relationship.
     *
     * Common UML multiplicities:
     *
     *      1       Exactly one.
     *
     *      0..1    Zero or one.
     *
     *      *       Zero or more.
     *
     *      0..*    Zero or more.
     *
     *      1..*    One or more.
     *
     *      2..5    At least two and at most five.
     *
     * Example:
     *
     * Department 1 -------- 0..* Employee
     *
     * One Department can be associated with zero or more
     * Employees.
     *
     * The multiplicity rules describe the domain model.
     *
     *
     * 34. UML NAVIGABILITY
     *
     * Navigability indicates whether one object can
     * navigate to or access the other through the modeled
     * relationship.
     *
     * Unidirectional:
     *
     *      Car ------> Engine
     *
     * Bidirectional:
     *
     *      Person <----> Passport
     *
     * In Java, navigability is commonly represented by
     * references stored in one or both classes.
     *
     * A UML association does not require a specific
     * Java implementation.
     *
     *
     * 35. OBJECT RELATIONSHIPS AND COLLECTIONS
     *
     * Collections are commonly used to represent relationships
     * involving multiple objects.
     *
     * Example:
     *
     *      class Team {
     *          private List<Player> players =
     *              new ArrayList<>();
     *      }
     *
     *      class Player {
     *          private String name;
     *      }
     *
     * Team has a collection of Player references.
     *
     * A collection represents cardinality in the object model.
     *
     * Important design considerations:
     *
     * - Can duplicate references be stored?
     * - Is ordering important?
     * - Can the collection be modified externally?
     * - Can elements be null?
     * - Can an element belong to multiple owners?
     * - How are additions and removals validated?
     *
     *
     * 36. ENCAPSULATING OBJECT RELATIONSHIPS
     *
     * Avoid exposing mutable internal collections directly
     * when callers should not modify them freely.
     *
     * Example:
     *
     *      class Department {
     *          private final List<Employee> employees =
     *              new ArrayList<>();
     *
     *          public void addEmployee(Employee employee) {
     *              employees.add(employee);
     *          }
     *
     *          public List<Employee> getEmployees() {
     *              return List.copyOf(employees);
     *          }
     *      }
     *
     * List.copyOf() returns an unmodifiable snapshot of
     * the collection structure.
     *
     * It is a shallow copy:
     * The Employee objects themselves are not copied.
     *
     * This prevents callers from adding or removing elements
     * through the returned list.
     *
     *
     * 37. BIDIRECTIONAL RELATIONSHIP CONSISTENCY
     *
     * Consider:
     *
     *      class Department {
     *          List<Employee> employees = new ArrayList<>();
     *      }
     *
     *      class Employee {
     *          Department department;
     *      }
     *
     * Both sides represent the same relationship.
     *
     * If only one side is updated, the object graph becomes
     * inconsistent.
     *
     * A controlled method can maintain both sides:
     *
     *      class Department {
     *          private final List<Employee> employees =
     *              new ArrayList<>();
     *
     *          void addEmployee(Employee employee) {
     *              if (employee == null) {
     *                  throw new IllegalArgumentException();
     *              }
     *
     *              if (!employees.contains(employee)) {
     *                  employees.add(employee);
     *                  employee.setDepartment(this);
     *              }
     *          }
     *      }
     *
     *      class Employee {
     *          private Department department;
     *
     *          void setDepartment(Department department) {
     *              this.department = department;
     *          }
     *      }
     *
     * In a complete implementation, removal and reassignment
     * must also maintain both sides consistently.
     *
     *
     * 38. RELATIONSHIPS IN DATABASE MODELING
     *
     * Object relationships and relational database
     * relationships are related concepts, but they are
     * not identical.
     *
     * Common database relationships:
     *
     * - One-to-one.
     * - One-to-many.
     * - Many-to-one.
     * - Many-to-many.
     *
     * Example:
     *
     * Department:
     *      DepartmentId
     *      DepartmentName
     *
     * Employee:
     *      EmployeeId
     *      EmployeeName
     *      DepartmentId
     *
     * The DepartmentId foreign key represents a
     * many-to-one relationship from Employee to Department.
     *
     * In Java applications, these relationships can be
     * represented by object references and collections.
     *
     * Object-relational mapping frameworks such as JPA
     * provide annotations for mapping relationships.
     *
     *
     * 39. JPA RELATIONSHIP ANNOTATIONS
     *
     * Jakarta Persistence provides relationship annotations:
     *
     *      @OneToOne
     *      @OneToMany
     *      @ManyToOne
     *      @ManyToMany
     *
     * Example:
     *
     *      @Entity
     *      class Employee {
     *
     *          @ManyToOne
     *          private Department department;
     *      }
     *
     *      @Entity
     *      class Department {
     *
     *          @OneToMany(mappedBy = "department")
     *          private List<Employee> employees;
     *      }
     *
     * These annotations describe persistence relationships.
     *
     * They do not replace the underlying Java object
     * relationship concepts.
     *
     * JPA ownership, cascade operations, and fetch behavior
     * are additional persistence-specific concerns.
     *
     *
     * 40. CASCADE OPERATIONS IN JPA
     *
     * Cascade operations allow certain persistence operations
     * on one entity to propagate to related entities.
     *
     * Common cascade types:
     *
     *      PERSIST
     *      MERGE
     *      REMOVE
     *      REFRESH
     *      DETACH
     *      ALL
     *
     * Example:
     *
     *      @OneToMany(
     *          mappedBy = "department",
     *          cascade = CascadeType.ALL
     *      )
     *      private List<Employee> employees;
     *
     * Cascade operations are persistence behavior.
     *
     * They are not the same as Java garbage collection
     * or the general meaning of composition.
     *
     * Use cascade rules carefully because they can
     * propagate destructive operations such as REMOVE.
     *
     *
     * 41. DEPENDENCY INJECTION AND OBJECT RELATIONSHIPS
     *
     * Dependency injection provides an object's required
     * dependencies from outside.
     *
     * Example:
     *
     *      interface NotificationSender {
     *          void send(String message);
     *      }
     *
     *      class EmailSender implements NotificationSender {
     *          public void send(String message) {}
     *      }
     *
     *      class NotificationService {
     *          private final NotificationSender sender;
     *
     *          NotificationService(NotificationSender sender) {
     *              this.sender = sender;
     *          }
     *      }
     *
     * NotificationService has a structural relationship
     * with NotificationSender.
     *
     * It also depends on the NotificationSender abstraction.
     *
     * Constructor injection makes the dependency explicit.
     *
     *
     * 42. OBJECT GRAPH
     *
     * An object graph is a collection of objects connected
     * through references.
     *
     * Example:
     *
     *      University
     *          |
     *          +---- Department
     *          |         |
     *          |         +---- Employee
     *          |
     *          +---- Student
     *                    |
     *                    +---- Address
     *
     * Each node represents an object.
     * Each reference represents a connection.
     *
     * Object graphs appear in:
     *
     * - Application domain models.
     * - Collections.
     * - Serialization.
     * - Garbage collection.
     * - ORM frameworks.
     * - Dependency injection containers.
     *
     *
     * 43. CYCLIC OBJECT REFERENCES
     *
     * Objects can reference one another in a cycle.
     *
     * Example:
     *
     *      class A {
     *          B b;
     *      }
     *
     *      class B {
     *          A a;
     *      }
     *
     *      A first = new A();
     *      B second = new B();
     *
     *      first.b = second;
     *      second.a = first;
     *
     * A and B form a cyclic reference relationship.
     *
     * Java's garbage collector can reclaim unreachable
     * cyclic object graphs.
     *
     * Reference cycles alone do not prevent garbage collection.
     *
     * However, cycles can complicate:
     *
     * - equals() implementations.
     * - toString() implementations.
     * - Serialization.
     * - Bidirectional relationship maintenance.
     *
     *
     * 44. OBJECT RELATIONSHIPS AND GARBAGE COLLECTION
     *
     * Java uses reachability to determine whether objects
     * can be reclaimed.
     *
     * An object is generally eligible for garbage collection
     * when it is no longer reachable from GC roots through
     * strong references.
     *
     * Common GC roots include:
     *
     * - References from active thread stacks.
     * - Static references.
     * - References held by active threads.
     * - Other JVM-defined root references.
     *
     * A cycle of objects can still become unreachable.
     *
     * Example:
     *
     *      A references B.
     *      B references A.
     *
     * If no GC root can reach either object, the cycle
     * can be reclaimed.
     *
     * Object relationships do not automatically guarantee
     * that objects remain alive.
     *
     *
     * 45. WEAK REFERENCES
     *
     * Java provides reference types in java.lang.ref.
     *
     * Important types:
     *
     *      WeakReference
     *      SoftReference
     *      PhantomReference
     *
     * A weak reference does not keep its referent alive
     * against garbage collection.
     *
     * Example:
     *
     *      WeakReference<Student> reference =
     *          new WeakReference<>(student);
     *
     *      Student value = reference.get();
     *
     * The referent may become unavailable after garbage
     * collection.
     *
     * Weak references are used in specialized cases such as
     * caches and reference-tracking mechanisms.
     *
     * They should not replace ordinary object ownership
     * without a clear lifecycle design.
     *
     *
     * 46. COMMON DESIGN MISTAKES
     *
     * 1. Using inheritance when the relationship is HAS-A.
     *
     * 2. Treating every field reference as composition.
     *
     * 3. Confusing aggregation with ordinary association.
     *
     * 4. Assuming composition automatically controls
     *    Java garbage collection.
     *
     * 5. Exposing mutable internal collections.
     *
     * 6. Failing to maintain both sides of a bidirectional
     *    relationship.
     *
     * 7. Creating unnecessary bidirectional references.
     *
     * 8. Building overly deep inheritance hierarchies.
     *
     * 9. Making classes depend directly on unnecessary
     *    concrete implementations.
     *
     * 10. Ignoring relationship cardinality.
     *
     * 11. Creating accidental object cycles in toString()
     *     or equals().
     *
     * 12. Confusing database cascade operations with
     *     object ownership.
     *
     *
     * 47. INTERVIEW QUESTIONS AND ANSWERS
     *
     * Q1. What are object relationships?
     *
     * A. Connections and dependencies between objects.
     *
     * Q2. What is an IS-A relationship?
     *
     * A. A subtype relationship generally established
     *    through inheritance or interface implementation.
     *
     * Q3. What is a HAS-A relationship?
     *
     * A. A relationship in which one object holds or uses
     *    another object.
     *
     * Q4. What is association?
     *
     * A. A general relationship between independent objects.
     *
     * Q5. What is aggregation?
     *
     * A. A whole-part association where the part can exist
     *    independently of the whole.
     *
     * Q6. What is composition?
     *
     * A. A strong whole-part association with ownership
     *    and dependent lifecycle semantics.
     *
     * Q7. What is dependency?
     *
     * A. A relationship in which one class uses another
     *    class to perform an operation.
     *
     * Q8. What is the difference between association
     *    and dependency?
     *
     * A. Association generally represents a structural
     *    connection; dependency represents usage.
     *
     * Q9. What is the difference between aggregation
     *    and composition?
     *
     * A. Aggregation allows independent part lifecycles;
     *    composition represents stronger ownership.
     *
     * Q10. Does Java have an aggregation keyword?
     *
     * A. No.
     *
     * Q11. Does Java have a composition keyword?
     *
     * A. No.
     *
     * Q12. Does composition guarantee that the garbage
     *     collector destroys the part with the whole?
     *
     * A. No. Garbage collection is based on reachability.
     *
     * Q13. What is unidirectional association?
     *
     * A. Only one side maintains a navigable reference.
     *
     * Q14. What is bidirectional association?
     *
     * A. Both sides maintain references to one another.
     *
     * Q15. What is cardinality?
     *
     * A. The number of objects participating in a relationship.
     *
     * Q16. What is a one-to-many relationship?
     *
     * A. One object is associated with multiple objects.
     *
     * Q17. What is many-to-many?
     *
     * A. Multiple objects on each side can be associated
     *    with multiple objects on the other side.
     *
     * Q18. What is composition over inheritance?
     *
     * A. A design guideline favoring composition when
     *    it offers more flexibility than inheritance.
     *
     * Q19. What is an object graph?
     *
     * A. A network of objects connected through references.
     *
     * Q20. Can Java garbage collection reclaim cyclic
     *     object references?
     *
     * A. Yes, if the cycle is unreachable from GC roots.
     *
     * Q21. What is UML?
     *
     * A. Unified Modeling Language, used to model software
     *    structures and behavior.
     *
     * Q22. What is the UML symbol for aggregation?
     *
     * A. A hollow diamond at the whole side.
     *
     * Q23. What is the UML symbol for composition?
     *
     * A. A filled diamond at the whole side.
     *
     * Q24. What is the UML symbol for dependency?
     *
     * A. A dashed arrow toward the depended-on element.
     *
     * Q25. What is dependency injection?
     *
     * A. Supplying an object's dependencies from outside
     *    rather than constructing them internally.
     *
     *
     * 48. FINAL REVISION CHECKLIST
     *
     * FUNDAMENTALS
     * [ ] Meaning of object relationships.
     * [ ] IS-A relationship.
     * [ ] HAS-A relationship.
     * [ ] Association.
     * [ ] Aggregation.
     * [ ] Composition.
     * [ ] Dependency.
     *
     * ASSOCIATION
     * [ ] Unidirectional association.
     * [ ] Bidirectional association.
     * [ ] One-to-one.
     * [ ] One-to-many.
     * [ ] Many-to-one.
     * [ ] Many-to-many.
     * [ ] Cardinality.
     *
     * AGGREGATION AND COMPOSITION
     * [ ] Independent part lifecycle.
     * [ ] Whole-part ownership.
     * [ ] UML hollow diamond.
     * [ ] UML filled diamond.
     * [ ] Aggregation vs composition.
     * [ ] Composition vs garbage collection.
     *
     * DESIGN
     * [ ] Inheritance vs composition.
     * [ ] Composition over inheritance.
     * [ ] Dependency injection.
     * [ ] Object ownership.
     * [ ] Encapsulation of relationships.
     * [ ] Bidirectional consistency.
     *
     * UML
     * [ ] Association notation.
     * [ ] Aggregation notation.
     * [ ] Composition notation.
     * [ ] Dependency notation.
     * [ ] Generalization.
     * [ ] Realization.
     * [ ] Multiplicity.
     * [ ] Navigability.
     *
     * ADVANCED
     * [ ] Object graphs.
     * [ ] Cyclic references.
     * [ ] Garbage collection and reachability.
     * [ ] Weak references.
     * [ ] JPA relationship mapping.
     * [ ] Cascade operations.
     *
     * ============================================================
     *                END OF OBJECT RELATIONSHIPS
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Object Relationships. Happy Learning!");
    }
}
