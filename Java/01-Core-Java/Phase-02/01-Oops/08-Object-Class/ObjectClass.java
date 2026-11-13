
public class ObjectClass {

    

    /*
     * ============================================================
     *                   OBJECT CLASS AND ITS METHODS
     * ============================================================
     *
     * 1. INTRODUCTION TO THE OBJECT CLASS
     *
     * java.lang.Object is the root superclass of the Java
     * class hierarchy.
     *
     * Every Java class directly or indirectly inherits from
     * Object, unless it explicitly extends another class.
     *
     * Example:
     *
     *      class Student {}
     *
     * This is conceptually equivalent to:
     *
     *      class Student extends Object {}
     *
     * Another example:
     *
     *      class Animal {}
     *
     *      class Dog extends Animal {}
     *
     * Dog indirectly inherits Object through Animal.
     *
     * IMPORTANT:
     * - Object belongs to the java.lang package.
     * - java.lang is implicitly imported into every compilation
     *   unit.
     * - Object is a concrete class.
     * - Object can be instantiated.
     * - Object has a public no-argument constructor.
     * - Object is the root of the class inheritance hierarchy.
     *
     * Example:
     *
     *      Object obj = new Student();
     *
     * An Object reference can refer to an instance of any
     * reference type that is compatible with Object.
     *
     * Primitive values are not objects, although Java supports
     * boxing them into wrapper objects.
     *
     *
     * 2. WHY IS THE OBJECT CLASS IMPORTANT?
     *
     * Object provides common behavior available to all classes.
     *
     * Its methods support:
     * - Object equality.
     * - Hash-based collections.
     * - String representation.
     * - Runtime type identification.
     * - Object cloning.
     * - Thread coordination.
     * - Object lifecycle hooks.
     *
     * Important methods declared by Object:
     *
     *      public final Class<?> getClass()
     *
     *      public int hashCode()
     *
     *      public boolean equals(Object obj)
     *
     *      protected Object clone()
     *
     *      public String toString()
     *
     *      public final void notify()
     *
     *      public final void notifyAll()
     *
     *      public final void wait()
     *
     *      public final void wait(long timeout)
     *
     *      public final void wait(long timeout, int nanos)
     *
     *      protected void finalize()
     *
     * finalize() is deprecated for removal.
     *
     *
     * 3. OBJECT CLASS METHOD CATEGORIES
     *
     * METHODS FOR OBJECT IDENTITY AND EQUALITY:
     * - equals()
     * - hashCode()
     *
     * METHODS FOR REPRESENTATION:
     * - toString()
     *
     * METHODS FOR RUNTIME TYPE INFORMATION:
     * - getClass()
     *
     * METHODS FOR COPYING:
     * - clone()
     *
     * METHODS FOR THREAD COORDINATION:
     * - wait()
     * - notify()
     * - notifyAll()
     *
     * LIFECYCLE METHOD:
     * - finalize()
     *
     *
     * 4. toString() METHOD
     *
     * toString() returns a string representation of an object.
     *
     * Declaration:
     *
     *      public String toString()
     *
     * The default implementation provided by Object returns
     * a string consisting of:
     *
     *      class name + '@' + hexadecimal hash code
     *
     * Example:
     *
     *      class Student {}
     *
     *      Student s = new Student();
     *      System.out.println(s.toString());
     *
     * Possible output:
     *
     *      Student@5acf9800
     *
     * The hexadecimal portion is based on the object's
     * hashCode() value.
     *
     * IMPORTANT:
     * - The output is not guaranteed to be a memory address.
     * - The exact output is implementation-dependent.
     * - The default format is not intended as a stable
     *   serialization format or unique identifier.
     *
     *
     * 5. WHY OVERRIDE toString()?
     *
     * The default representation is usually not meaningful
     * for application objects.
     *
     * Overriding toString() provides useful object information.
     *
     * Example:
     *
     *      class Student {
     *          int id;
     *          String name;
     *
     *          Student(int id, String name) {
     *              this.id = id;
     *              this.name = name;
     *          }
     *
     *          @Override
     *          public String toString() {
     *              return "Student{id=" + id
     *                      + ", name='" + name + "'}";
     *          }
     *      }
     *
     *      Student s = new Student(101, "Vinay");
     *      System.out.println(s);
     *
     * Output:
     *
     *      Student{id=101, name='Vinay'}
     *
     * println(Object) effectively obtains a string
     * representation of the supplied object.
     *
     * Therefore, printing an object normally invokes
     * its toString() implementation.
     *
     *
     * 6. toString() AND STRING CONCATENATION
     *
     * When an object is concatenated with a String using +,
     * Java converts the object to a string representation.
     *
     * Example:
     *
     *      Student s = new Student(101, "Vinay");
     *
     *      System.out.println("Details: " + s);
     *
     * This uses the object's string representation.
     *
     * For a non-null object, its toString() implementation
     * is normally used.
     *
     * If the reference is null:
     *
     *      Student s = null;
     *      System.out.println(s);
     *
     * Output:
     *
     *      null
     *
     * This does not invoke a method on a null reference.
     *
     *
     * 7. toString() BEST PRACTICES
     *
     * - Return a readable representation.
     * - Include useful fields.
     * - Avoid exposing passwords, tokens, or secrets.
     * - Avoid expensive database or network operations.
     * - Avoid recursive relationships that can cause
     *   StackOverflowError.
     * - Do not rely on the default output format.
     * - Keep the representation useful for debugging.
     *
     * toString() should generally be side-effect-free.
     *
     *
     * 8. equals() METHOD
     *
     * equals() is used to determine whether two objects
     * are logically equal according to a class's equality
     * definition.
     *
     * Declaration:
     *
     *      public boolean equals(Object obj)
     *
     * The default implementation in Object compares
     * object identity, equivalent to:
     *
     *      this == obj
     *
     * It does not automatically compare object fields.
     *
     * Example:
     *
     *      class Student {}
     *
     *      Student s1 = new Student();
     *      Student s2 = new Student();
     *
     *      System.out.println(s1.equals(s2));
     *
     * Output:
     *
     *      false
     *
     * They are two distinct objects.
     *
     *
     * 9. == VS equals()
     *
     * ==:
     * - Compares primitive values when used with primitives.
     * - Compares reference identity when used with references.
     * - Does not call equals().
     *
     * equals():
     * - Compares objects according to its implementation.
     * - Object's default implementation checks identity.
     * - A class can override it to compare logical values.
     *
     * Example:
     *
     *      String s1 = new String("Java");
     *      String s2 = new String("Java");
     *
     *      System.out.println(s1 == s2);
     *      System.out.println(s1.equals(s2));
     *
     * Output:
     *
     *      false
     *      true
     *
     * Both references point to different String objects,
     * but their contents are equal.
     *
     *
     * 10. OVERRIDING equals()
     *
     * A class can override equals() to compare object state.
     *
     * Example:
     *
     *      class Student {
     *          int id;
     *          String name;
     *
     *          Student(int id, String name) {
     *              this.id = id;
     *              this.name = name;
     *          }
     *
     *          @Override
     *          public boolean equals(Object obj) {
     *
     *              if (this == obj) {
     *                  return true;
     *              }
     *
     *              if (obj == null ||
     *                  getClass() != obj.getClass()) {
     *                  return false;
     *              }
     *
     *              Student other = (Student) obj;
     *
     *              return id == other.id
     *                      && Objects.equals(name, other.name);
     *          }
     *      }
     *
     *      Student s1 = new Student(101, "Vinay");
     *      Student s2 = new Student(101, "Vinay");
     *
     *      System.out.println(s1.equals(s2));
     *
     * Output:
     *
     *      true
     *
     * The Objects class belongs to java.util.
     * Import java.util.Objects to use Objects.equals().
     *
     *
     * 11. EQUALS() CONTRACT
     *
     * Any correct equals() implementation must satisfy
     * the general contract defined by Object.
     *
     * 1. REFLEXIVE
     *
     *      x.equals(x) must return true.
     *
     * An object must be equal to itself.
     *
     * 2. SYMMETRIC
     *
     *      If x.equals(y) is true,
     *      then y.equals(x) must also be true.
     *
     * Equality must work in both directions.
     *
     * 3. TRANSITIVE
     *
     *      If x.equals(y) is true
     *      and y.equals(z) is true,
     *      then x.equals(z) must be true.
     *
     * 4. CONSISTENT
     *
     * Repeated calls must return the same result as long
     * as information used in equality comparisons is not
     * modified.
     *
     * 5. NON-NULL
     *
     *      x.equals(null) must return false.
     *
     * IMPORTANT:
     * These rules are not merely coding conventions.
     * They are part of the equals() contract.
     *
     *
     * 12. REFLEXIVITY EXAMPLE
     *
     *      Student s = new Student(101, "Vinay");
     *
     *      System.out.println(s.equals(s));
     *
     * Output:
     *
     *      true
     *
     * If a class's equals() returns false for the same
     * object, it violates reflexivity.
     *
     *
     * 13. SYMMETRY EXAMPLE
     *
     * If:
     *
     *      a.equals(b) == true
     *
     * then:
     *
     *      b.equals(a) must also be true.
     *
     * A poorly designed inheritance-based equality check
     * can violate symmetry.
     *
     * For example, if a base class compares only its own
     * fields while a subclass adds another equality field,
     * the base and subclass implementations may disagree.
     *
     *
     * 14. TRANSITIVITY EXAMPLE
     *
     * Suppose:
     *
     *      A equals B
     *      B equals C
     *
     * Then:
     *
     *      A must equal C.
     *
     * Equality based on inconsistent or changing rules
     * can violate transitivity.
     *
     * This is one reason equality involving inheritance
     * requires careful design.
     *
     *
     * 15. getClass() VS instanceof IN equals()
     *
     * Two common strategies are used to check the type
     * of the object passed to equals().
     *
     * A. getClass()
     *
     *      if (obj == null ||
     *          getClass() != obj.getClass()) {
     *          return false;
     *      }
     *
     * This requires both objects to have exactly the same
     * runtime class.
     *
     * B. instanceof
     *
     *      if (!(obj instanceof Student)) {
     *          return false;
     *      }
     *
     * This accepts instances of Student and its subclasses.
     *
     * IMPORTANT:
     * Neither strategy is universally correct for every
     * equality design.
     *
     * Exact-class equality is often simpler.
     * Inheritance-based equality must preserve symmetry,
     * transitivity, and substitutability.
     *
     *
     * 16. equals() OVERLOADING MISTAKE
     *
     * A common mistake is to overload equals() rather than
     * override Object.equals(Object).
     *
     * Incorrect:
     *
     *      class Student {
     *
     *          boolean equals(Student other) {
     *              return true;
     *          }
     *      }
     *
     * This declares a new overload.
     * It does not override Object.equals(Object).
     *
     * Correct:
     *
     *      @Override
     *      public boolean equals(Object obj) {
     *          // Equality implementation
     *          return super.equals(obj);
     *      }
     *
     * The parameter must be Object for a normal override.
     *
     * Always use @Override to catch signature mistakes.
     *
     *
     * 17. hashCode() METHOD
     *
     * hashCode() returns an integer hash value for an object.
     *
     * Declaration:
     *
     *      public int hashCode()
     *
     * It is used by hash-based data structures to organize
     * objects into buckets.
     *
     * Common hash-based collections:
     *
     *      HashMap
     *      HashSet
     *      Hashtable
     *      LinkedHashMap
     *      LinkedHashSet
     *
     * Hashing helps collections locate potential matches
     * efficiently.
     *
     *
     * 18. hashCode() CONTRACT
     *
     * Object defines the following important requirements.
     *
     * 1. CONSISTENCY
     *
     * Repeated calls to hashCode() on the same object must
     * return the same integer during one application
     * execution, provided information used by equals()
     * comparisons has not changed.
     *
     * 2. EQUAL OBJECTS MUST HAVE EQUAL HASH CODES
     *
     * If:
     *
     *      x.equals(y) == true
     *
     * then:
     *
     *      x.hashCode() == y.hashCode()
     *
     * must be true.
     *
     * 3. UNEQUAL OBJECTS MAY HAVE THE SAME HASH CODE
     *
     * If:
     *
     *      x.equals(y) == false
     *
     * it is still permitted that:
     *
     *      x.hashCode() == y.hashCode()
     *
     * This is called a hash collision.
     *
     * IMPORTANT:
     * Equal hash codes do not prove object equality.
     *
     *
     * 19. WHY OVERRIDE hashCode() WHEN OVERRIDING equals()?
     *
     * If a class overrides equals(), it should normally
     * override hashCode() consistently.
     *
     * Example:
     *
     *      class Student {
     *          int id;
     *          String name;
     *
     *          Student(int id, String name) {
     *              this.id = id;
     *              this.name = name;
     *          }
     *
     *          @Override
     *          public boolean equals(Object obj) {
     *              if (this == obj) return true;
     *              if (!(obj instanceof Student)) return false;
     *
     *              Student other = (Student) obj;
     *
     *              return id == other.id
     *                      && Objects.equals(name, other.name);
     *          }
     *
     *          @Override
     *          public int hashCode() {
     *              return Objects.hash(id, name);
     *          }
     *      }
     *
     * Import:
     *
     *      import java.util.Objects;
     *
     * Objects.hash() generates a hash value using the
     * supplied values.
     *
     * IMPORTANT:
     * Objects.hash() is convenient, but it creates an
     * array internally and may have more overhead than
     * a carefully written implementation.
     *
     *
     * 20. WHAT HAPPENS IF hashCode() IS NOT OVERRIDDEN?
     *
     * Consider two logically equal Student objects.
     *
     * If equals() is overridden but hashCode() is not,
     * they may receive different hash values.
     *
     * A HashSet or HashMap may then fail to find an object
     * that should be considered equal.
     *
     * Example:
     *
     *      Student s1 = new Student(101, "Vinay");
     *      Student s2 = new Student(101, "Vinay");
     *
     *      Set<Student> students = new HashSet<>();
     *
     *      students.add(s1);
     *
     *      System.out.println(students.contains(s2));
     *
     * If equals() says the objects are equal but their
     * hash codes differ, the lookup may return false.
     *
     * Always maintain the equals()/hashCode() contract.
     *
     *
     * 21. HASH COLLISIONS
     *
     * A hash collision occurs when two unequal objects
     * produce the same hash code.
     *
     * Example:
     *
     *      x.hashCode() == y.hashCode()
     *
     * while:
     *
     *      x.equals(y) == false
     *
     * Collisions are permitted.
     *
     * Hash-based collections handle collisions using
     * their internal bucket structures.
     *
     * In modern HashMap implementations, heavily collided
     * buckets may be converted into tree structures
     * under suitable conditions.
     *
     * This is an implementation detail, not a guarantee
     * that every collision always becomes a tree.
     *
     *
     * 22. HASHING IN HASHMAP
     *
     * Simplified HashMap lookup process:
     *
     * 1. Calculate the key's hash.
     * 2. Determine the bucket index.
     * 3. Locate the relevant bucket.
     * 4. Compare candidate keys using equality checks.
     * 5. Return the matching value or report no match.
     *
     * HashMap uses hashCode() to help locate a bucket.
     * It uses equals() to distinguish keys that collide.
     *
     * Therefore:
     *
     *      hashCode() -> Helps locate candidates.
     *
     *      equals() -> Determines logical equality.
     *
     * Exact internal implementation details can vary
     * between Java versions.
     *
     *
     * 23. MUTABLE OBJECTS AS HASHMAP KEYS
     *
     * Avoid changing fields used by equals() or hashCode()
     * while an object is being used as a key in a hash-based
     * collection.
     *
     * Example:
     *
     *      class Student {
     *          int id;
     *
     *          Student(int id) {
     *              this.id = id;
     *          }
     *
     *          @Override
     *          public int hashCode() {
     *              return Integer.hashCode(id);
     *          }
     *
     *          @Override
     *          public boolean equals(Object obj) {
     *              if (!(obj instanceof Student)) return false;
     *              return id == ((Student) obj).id;
     *          }
     *      }
     *
     *      Student s = new Student(101);
     *
     *      Map<Student, String> map = new HashMap<>();
     *      map.put(s, "Vinay");
     *
     *      s.id = 202;
     *
     * The key's hash code has changed.
     * The map may search a different bucket and fail to
     * find the entry using that same key.
     *
     * This is a major reason immutable key objects are useful.
     *
     *
     * 24. hashCode() VS IDENTITY
     *
     * hashCode() is not guaranteed to be unique.
     *
     * Two different objects can have the same hash code.
     *
     * System.identityHashCode(obj) returns the identity-based
     * hash value associated with an object, even if the
     * object's class overrides hashCode().
     *
     * Example:
     *
     *      int value = System.identityHashCode(obj);
     *
     * This value is not guaranteed to be a memory address
     * or globally unique identifier.
     *
     *
     * 25. getClass() METHOD
     *
     * getClass() returns the runtime Class object representing
     * the actual class of an object.
     *
     * Declaration:
     *
     *      public final Class<?> getClass()
     *
     * Example:
     *
     *      class Animal {}
     *
     *      class Dog extends Animal {}
     *
     *      Animal animal = new Dog();
     *
     *      System.out.println(animal.getClass());
     *
     * Possible output:
     *
     *      class Dog
     *
     * Reference type:
     *      Animal
     *
     * Runtime class:
     *      Dog
     *
     * getClass() reports the actual runtime class.
     *
     *
     * 26. getClass() VS instanceof
     *
     * getClass():
     * - Returns a Class object.
     * - Can be used for exact runtime-class comparison.
     * - Is final and cannot be overridden.
     *
     * instanceof:
     * - Tests compatibility with a type.
     * - Returns true for instances of compatible subtypes.
     * - Returns false for null.
     *
     * Example:
     *
     *      Animal animal = new Dog();
     *
     *      System.out.println(animal.getClass() == Dog.class);
     *      System.out.println(animal instanceof Animal);
     *
     * Output:
     *
     *      true
     *      true
     *
     * A Dog object is exactly a Dog class instance and is
     * also compatible with Animal.
     *
     *
     * 27. CLASS OBJECT
     *
     * Every class and interface has an associated Class object.
     *
     * Class<?> represents a class or interface at runtime.
     *
     * Examples:
     *
     *      Class<?> c1 = String.class;
     *
     *      String text = "Java";
     *      Class<?> c2 = text.getClass();
     *
     *      Class<?> c3 = Class.forName("java.lang.String");
     *
     * These are different ways to obtain Class objects.
     *
     * Class objects are used for:
     * - Reflection.
     * - Runtime type inspection.
     * - Accessing metadata.
     * - Creating objects reflectively, subject to access
     *   and construction rules.
     *
     *
     * 28. REFLECTION BASICS
     *
     * Reflection allows a program to inspect and interact
     * with classes, methods, fields, and constructors
     * at runtime.
     *
     * Example:
     *
     *      Class<?> clazz = Student.class;
     *
     *      System.out.println(clazz.getName());
     *
     *      for (Method method : clazz.getDeclaredMethods()) {
     *          System.out.println(method.getName());
     *      }
     *
     * Required imports:
     *
     *      import java.lang.reflect.Method;
     *
     * Common reflection methods:
     *
     *      getName()
     *      getSimpleName()
     *      getSuperclass()
     *      getInterfaces()
     *      getDeclaredFields()
     *      getFields()
     *      getDeclaredMethods()
     *      getMethods()
     *      getDeclaredConstructors()
     *      getConstructors()
     *
     * Reflection is used by frameworks, dependency injection
     * containers, testing frameworks, serializers, and tools.
     *
     *
     * 29. getMethods() VS getDeclaredMethods()
     *
     * getMethods():
     * - Returns public methods of the class.
     * - Includes inherited public methods.
     *
     * getDeclaredMethods():
     * - Returns methods declared directly in the class.
     * - Includes methods with different access levels.
     * - Does not include inherited methods.
     *
     * Similar distinction exists for fields:
     *
     *      getFields()
     *      getDeclaredFields()
     *
     * And constructors:
     *
     *      getConstructors()
     *      getDeclaredConstructors()
     *
     * Reflection APIs have their own access restrictions,
     * especially with Java modules and strong encapsulation.
     *
     *
     * 30. clone() METHOD
     *
     * clone() is used to create and return a copy of an object.
     *
     * Declaration:
     *
     *      protected Object clone() throws CloneNotSupportedException
     *
     * It is declared protected in Object.
     *
     * Its default implementation performs a field-by-field
     * copy of the object.
     *
     * This is generally a shallow copy.
     *
     * IMPORTANT:
     * - Object does not make clone() public.
     * - A class commonly overrides clone() to expose it.
     * - The class normally implements Cloneable.
     * - Cloneable is a marker interface.
     * - Object.clone() throws CloneNotSupportedException
     *   if the object's class does not implement Cloneable.
     *
     *
     * 31. CLONEABLE INTERFACE
     *
     * Cloneable belongs to java.lang.
     *
     * It does not declare a clone() method.
     *
     * It acts as a marker that permits Object.clone()
     * to perform its field-copy behavior.
     *
     * Example:
     *
     *      class Student implements Cloneable {
     *          int id;
     *
     *          @Override
     *          public Student clone() throws CloneNotSupportedException {
     *              return (Student) super.clone();
     *          }
     *      }
     *
     *      Student s1 = new Student();
     *      Student s2 = s1.clone();
     *
     * Here:
     * - super.clone() invokes Object.clone().
     * - The result is cast to Student.
     * - The method exposes clone() as public.
     * - The covariant return type is Student.
     *
     *
     * 32. SHALLOW COPYING
     *
     * A shallow copy copies the object's fields.
     *
     * Primitive fields receive copied primitive values.
     *
     * Reference fields receive copied references.
     *
     * The referenced objects are not recursively copied.
     *
     * Example:
     *
     *      class Address {
     *          String city;
     *      }
     *
     *      class Student implements Cloneable {
     *          int id;
     *          Address address;
     *
     *          @Override
     *          public Student clone()
     *                  throws CloneNotSupportedException {
     *              return (Student) super.clone();
     *          }
     *      }
     *
     *      Student s1 = new Student();
     *      s1.address = new Address();
     *
     *      Student s2 = s1.clone();
     *
     *      System.out.println(s1 == s2);
     *      System.out.println(s1.address == s2.address);
     *
     * Output:
     *
     *      false
     *      true
     *
     * The Student objects are different.
     * Their address references point to the same Address object.
     *
     *
     * 33. DEEP COPYING
     *
     * A deep copy duplicates the object and the mutable
     * objects it owns, according to the required copy policy.
     *
     * Example:
     *
     *      class Address {
     *          String city;
     *
     *          Address(String city) {
     *              this.city = city;
     *          }
     *
     *          Address copy() {
     *              return new Address(city);
     *          }
     *      }
     *
     *      class Student {
     *          int id;
     *          Address address;
     *
     *          Student copy() {
     *              Student copy = new Student();
     *              copy.id = id;
     *              copy.address = address.copy();
     *              return copy;
     *          }
     *      }
     *
     * The copied Student has a separate Address object.
     *
     * Deep copying must define how nested objects,
     * collections, shared references, and cycles are handled.
     *
     * A deep copy does not automatically mean every referenced
     * object in the entire reachable object graph must be copied.
     *
     *
     * 34. SHALLOW COPY VS DEEP COPY
     *
     * SHALLOW COPY:
     * - Copies field values.
     * - Reference fields point to the same referenced objects.
     * - Usually cheaper.
     * - Changes to shared mutable objects can affect both copies.
     *
     * DEEP COPY:
     * - Copies the object and selected nested mutable state.
     * - Reduces shared mutable references.
     * - Requires an explicit copying policy.
     * - Can be more expensive.
     *
     * Object.clone() performs a shallow field copy by default.
     *
     *
     * 35. ALTERNATIVES TO clone()
     *
     * The Cloneable/clone() mechanism has design limitations.
     *
     * Common alternatives:
     *
     * 1. Copy constructor.
     *
     *      Student(Student other) {
     *          this.id = other.id;
     *          this.name = other.name;
     *      }
     *
     * 2. Static factory method.
     *
     *      static Student copyOf(Student other) {
     *          return new Student(other);
     *      }
     *
     * 3. Explicit copy method.
     *
     *      Student copy() {
     *          return new Student(this);
     *      }
     *
     * Advantages:
     * - Clear copying behavior.
     * - No CloneNotSupportedException.
     * - Explicit control over deep and shallow copying.
     * - No dependence on Object.clone().
     *
     * Copy constructors and factories are often preferred
     * for application-level copying.
     *
     *
     * 36. wait() METHOD
     *
     * wait() is used for thread coordination.
     *
     * It causes the current thread to wait until it is
     * notified, interrupted, or otherwise resumes according
     * to the method's waiting rules.
     *
     * Declarations:
     *
     *      public final void wait()
     *
     *      public final void wait(long timeout)
     *
     *      public final void wait(long timeout, int nanos)
     *
     * IMPORTANT:
     * wait() must be called while the current thread owns
     * the monitor of the object on which wait() is invoked.
     *
     * Otherwise, IllegalMonitorStateException is thrown.
     *
     * Example:
     *
     *      synchronized (lock) {
     *          lock.wait();
     *      }
     *
     * wait() releases the object's monitor while waiting.
     *
     * The thread must reacquire the monitor before returning
     * from wait().
     *
     *
     * 37. notify() METHOD
     *
     * notify() wakes one thread waiting on the object's monitor.
     *
     * Declaration:
     *
     *      public final void notify()
     *
     * IMPORTANT:
     * - The current thread must own the object's monitor.
     * - Otherwise, IllegalMonitorStateException is thrown.
     * - The notified thread does not immediately execute.
     * - It must reacquire the monitor before continuing.
     * - If multiple threads are waiting, the choice is
     *   not guaranteed to follow a particular order.
     *
     * Example:
     *
     *      synchronized (lock) {
     *          lock.notify();
     *      }
     *
     *
     * 38. notifyAll() METHOD
     *
     * notifyAll() wakes all threads waiting on the
     * object's monitor.
     *
     * Declaration:
     *
     *      public final void notifyAll()
     *
     * Every awakened thread must compete to reacquire
     * the monitor.
     *
     * Only one thread at a time can own that monitor.
     *
     * Other awakened threads continue waiting to acquire it.
     *
     * Example:
     *
     *      synchronized (lock) {
     *          lock.notifyAll();
     *      }
     *
     *
     * 39. wait() VS sleep()
     *
     * wait():
     * - Declared in Object.
     * - Used for coordination between threads.
     * - Must be called while owning the object's monitor.
     * - Releases that object's monitor while waiting.
     * - Can resume after notification, interruption,
     *   timeout, or spurious wakeup.
     *
     * sleep():
     * - Declared in Thread.
     * - Pauses the current thread for a specified time.
     * - Does not release monitors owned by the thread.
     * - Does not require synchronized context.
     *
     * Example:
     *
     *      Thread.sleep(1000);
     *
     *      synchronized (lock) {
     *          lock.wait();
     *      }
     *
     * sleep() and wait() serve different purposes.
     *
     *
     * 40. WHY WAIT() SHOULD BE USED INSIDE A LOOP
     *
     * A thread can wake up without receiving a notification.
     *
     * This is called a spurious wakeup.
     *
     * A waiting thread must recheck the condition after
     * it resumes.
     *
     * Incorrect:
     *
     *      synchronized (lock) {
     *          if (!condition) {
     *              lock.wait();
     *          }
     *      }
     *
     * Correct pattern:
     *
     *      synchronized (lock) {
     *          while (!condition) {
     *              lock.wait();
     *          }
     *      }
     *
     * The while loop ensures that the condition is checked
     * again before proceeding.
     *
     *
     * 41. WAIT(), NOTIFY(), AND NOTIFYALL() EXAMPLE
     *
     *      class SharedResource {
     *          private boolean ready = false;
     *
     *          synchronized void waitForReady()
     *                  throws InterruptedException {
     *
     *              while (!ready) {
     *                  wait();
     *              }
     *
     *              System.out.println("Resource ready");
     *          }
     *
     *          synchronized void makeReady() {
     *              ready = true;
     *              notifyAll();
     *          }
     *      }
     *
     * One thread waits until ready becomes true.
     * Another thread changes the condition and notifies waiters.
     *
     * The condition is checked while holding the same monitor.
     *
     * For production concurrency code, higher-level
     * concurrency utilities are often preferable.
     *
     *
     * 42. TIMED WAITING
     *
     * Object provides timed wait methods.
     *
     * wait(long timeout):
     *
     * - timeout is measured in milliseconds.
     * - A positive timeout specifies a maximum wait duration.
     * - A timeout of zero means wait indefinitely.
     *
     * wait(long timeout, int nanos):
     *
     * - Supports additional nanosecond precision.
     * - nanos must be between 0 and 999999 inclusive.
     *
     * Invalid argument values can cause IllegalArgumentException.
     *
     * Timed waiting does not guarantee that the thread
     * resumes immediately when the timeout expires.
     *
     * The thread still needs to reacquire the monitor.
     *
     *
     * 43. INTERRUPTING A WAITING THREAD
     *
     * A thread waiting through wait() can be interrupted.
     *
     * wait() throws InterruptedException.
     *
     * Example:
     *
     *      synchronized (lock) {
     *          try {
     *              lock.wait();
     *          } catch (InterruptedException e) {
     *              Thread.currentThread().interrupt();
     *          }
     *      }
     *
     * InterruptedException is a checked exception.
     *
     * Handling interruption should preserve the
     * application's cancellation policy.
     *
     * Restoring the interrupt flag is common when the
     * current method cannot propagate InterruptedException.
     *
     *
     * 44. OBJECT MONITOR
     *
     * Every Java object can act as a monitor for
     * synchronization.
     *
     * A monitor provides mutual exclusion and coordination.
     *
     * Example:
     *
     *      synchronized (obj) {
     *          // Critical section
     *      }
     *
     * Only one thread at a time can own the same object's
     * monitor.
     *
     * Instance synchronized method:
     *
     *      synchronized void display() {}
     *
     * Uses the current object's monitor: this.
     *
     * Static synchronized method:
     *
     *      static synchronized void display() {}
     *
     * Uses the monitor associated with the Class object
     * representing the declaring class.
     *
     * wait(), notify(), and notifyAll() operate on
     * the monitor of the object on which they are called.
     *
     *
     * 45. WAIT/NOTIFY VS LOCK/CONDITION
     *
     * Object monitor coordination:
     *
     *      wait()
     *      notify()
     *      notifyAll()
     *
     * Explicit locking:
     *
     *      Lock
     *      Condition
     *
     * Condition provides:
     * - await()
     * - signal()
     * - signalAll()
     *
     * Condition is part of java.util.concurrent.locks.
     *
     * Explicit locks can provide multiple condition queues
     * and additional locking features.
     *
     * Higher-level tools such as BlockingQueue, CountDownLatch,
     * Semaphore, and other concurrency utilities can simplify
     * common coordination problems.
     *
     *
     * 46. finalize() METHOD
     *
     * finalize() was historically intended to provide an
     * opportunity for an object to perform cleanup before
     * it was reclaimed by garbage collection.
     *
     * Declaration in Object:
     *
     *      protected void finalize() throws Throwable
     *
     * IMPORTANT:
     * - It is not a destructor.
     * - Its execution is not guaranteed.
     * - Its timing is not guaranteed.
     * - It should not be used for critical resource cleanup.
     * - It can cause performance and security problems.
     *
     * Finalization is deprecated for removal.
     *
     * It was deprecated in Java 9 and deprecated for removal
     * in Java 18.
     *
     * Finalization can be disabled and is being phased out
     * from the platform.
     *
     *
     * 47. WHY finalize() IS NOT RECOMMENDED
     *
     * Problems with finalization:
     *
     * 1. Unpredictable execution time.
     *
     * 2. No guarantee that it will execute before program exit.
     *
     * 3. It can delay garbage collection.
     *
     * 4. It can cause resource exhaustion.
     *
     * 5. It can introduce security and object-resurrection
     *    problems.
     *
     * 6. It can make application behavior difficult to reason
     *    about.
     *
     * 7. It is unsuitable for deterministic cleanup.
     *
     * Do not rely on finalize() for closing files,
     * database connections, sockets, or other resources.
     *
     *
     * 48. OBJECT RESURRECTION
     *
     * Object resurrection occurs when an object becomes
     * reachable again during finalization.
     *
     * Example concept:
     *
     *      class Demo {
     *          static Demo saved;
     *
     *          @Override
     *          protected void finalize() {
     *              saved = this;
     *          }
     *      }
     *
     * The object can become reachable through the static
     * reference during finalization.
     *
     * This behavior is problematic and is one reason
     * finalization is deprecated for removal.
     *
     * Never design application lifecycle management
     * around object resurrection.
     *
     *
     * 49. ALTERNATIVES TO finalize()
     *
     * Preferred approaches:
     *
     * 1. try-with-resources.
     *
     * 2. AutoCloseable.
     *
     * 3. Closeable.
     *
     * 4. Explicit close() methods.
     *
     * 5. Cleaner for specialized fallback cleanup.
     *
     * Example:
     *
     *      class Resource implements AutoCloseable {
     *
     *          public void use() {
     *              System.out.println("Using resource");
     *          }
     *
     *          @Override
     *          public void close() {
     *              System.out.println("Resource closed");
     *          }
     *      }
     *
     *      try (Resource resource = new Resource()) {
     *          resource.use();
     *      }
     *
     * close() is invoked automatically when the
     * try-with-resources block completes.
     *
     * This provides deterministic cleanup.
     *
     *
     * 50. CLEANER API
     *
     * Cleaner was introduced in Java 9.
     *
     * It provides a mechanism to register cleanup actions
     * that may run when an object becomes phantom reachable.
     *
     * Cleaner is generally intended as a fallback mechanism.
     *
     * It is not a replacement for deterministic resource
     * management using close() and try-with-resources.
     *
     * Cleaner actions should not depend on the object
     * being cleaned in a way that keeps it reachable.
     *
     * Use Cleaner only when its lifecycle semantics are
     * appropriate for the resource being managed.
     *
     *
     * 51. OBJECT CLASS AND INTERFACES
     *
     * Every class inherits from Object.
     *
     * Interfaces do not extend Object.
     *
     * However, public instance methods declared in Object
     * have special relationships with interface method
     * declarations and interface implementation rules.
     *
     * Example:
     *
     *      interface Displayable {
     *          String toString();
     *      }
     *
     *      class Product implements Displayable {
     *      }
     *
     * The public toString() implementation inherited
     * from Object can satisfy the interface method.
     *
     * An interface does not inherit Object as a superclass.
     *
     *
     * 52. OBJECT CLASS AND POLYMORPHISM
     *
     * Object references can hold objects of different classes.
     *
     * Example:
     *
     *      Object a = "Java";
     *      Object b = new ArrayList<>();
     *      Object c = new Student();
     *
     * The Object reference provides access to methods
     * declared in Object.
     *
     * It does not provide direct access to subclass-specific
     * methods without a suitable cast or other abstraction.
     *
     * Example:
     *
     *      Object obj = "Java";
     *
     *      System.out.println(obj.toString());
     *
     * The String implementation of toString() executes.
     *
     * This demonstrates runtime dispatch.
     *
     *
     * 53. OBJECT CLASS AND COLLECTIONS
     *
     * Object is important in collection APIs.
     *
     * Older collection APIs and raw collections can hold
     * heterogeneous reference types.
     *
     * Example:
     *
     *      List<Object> values = new ArrayList<>();
     *
     *      values.add("Java");
     *      values.add(100);
     *      values.add(new Student());
     *
     * Wrapper conversion allows primitive values to be
     * stored through autoboxing.
     *
     * Example:
     *
     *      values.add(10);
     *
     * The int value is boxed into an Integer object.
     *
     * Prefer parameterized collection types when the
     * collection should contain one specific kind of value.
     *
     *
     * 54. OBJECT CLASS AND ARRAYS
     *
     * Every Java array is an object.
     *
     * Arrays are reference types and ultimately have
     * Object as a superclass in the class hierarchy.
     *
     * Example:
     *
     *      int[] numbers = {10, 20, 30};
     *
     *      Object obj = numbers;
     *
     * This assignment is valid.
     *
     * An array also implements:
     *
     *      Cloneable
     *      java.io.Serializable
     *
     * Array objects have a public length field.
     *
     * Arrays do not inherit ordinary methods from a
     * user-defined array class.
     *
     *
     * 55. OBJECT CLASS AND ENUMS
     *
     * Every enum type indirectly inherits from
     * java.lang.Enum, which itself extends Object.
     *
     * Example:
     *
     *      enum Status {
     *          ACTIVE,
     *          INACTIVE
     *      }
     *
     * Status provides inherited Object behavior along
     * with methods from Enum.
     *
     * Enum also defines final methods such as:
     *
     *      name()
     *      ordinal()
     *      getDeclaringClass()
     *
     * These are declared by Enum, not Object.
     *
     *
     * 56. OBJECT CLASS AND RECORDS
     *
     * A record is a special kind of class.
     *
     * Records implicitly extend java.lang.Record.
     *
     * Record extends Object.
     *
     * Records automatically provide:
     * - Accessor methods for record components.
     * - equals().
     * - hashCode().
     * - toString().
     *
     * Example:
     *
     *      record Student(int id, String name) {}
     *
     * The compiler generates implementations of
     * equals(), hashCode(), and toString() based on
     * the record components.
     *
     * These generated methods can be explicitly overridden
     * when appropriate.
     *
     *
     * 57. OBJECT CLASS AND IMMUTABILITY
     *
     * Object does not automatically make subclasses immutable.
     *
     * A class is immutable when its observable state cannot
     * change after construction.
     *
     * Common techniques:
     *
     * - Make the class final when subclassing is not required.
     * - Make fields private and final.
     * - Initialize fields through constructors.
     * - Do not provide mutating methods.
     * - Use defensive copies for mutable inputs and outputs.
     * - Avoid exposing mutable internal references.
     *
     * Immutable objects are useful as hash-based keys because
     * their equality and hash-related state remains stable.
     *
     *
     * 58. OBJECT CLASS AND THREAD SAFETY
     *
     * Object provides monitor coordination methods.
     *
     * However, merely inheriting Object does not make a class
     * thread-safe.
     *
     * Thread safety requires correct coordination of
     * shared mutable state.
     *
     * Common mechanisms:
     *
     * - synchronized methods and blocks.
     * - volatile fields.
     * - Lock implementations.
     * - Atomic classes.
     * - Concurrent collections.
     * - Immutable objects.
     *
     * wait(), notify(), and notifyAll() are only part of
     * the broader Java concurrency model.
     *
     *
     * 59. COMMON MISTAKES
     *
     * 1. Assuming == and equals() always do the same thing.
     *
     * 2. Overriding equals() without overriding hashCode().
     *
     * 3. Assuming different objects must have different
     *    hash codes.
     *
     * 4. Treating hashCode() as a unique identifier.
     *
     * 5. Overloading equals(Student) instead of overriding
     *    equals(Object).
     *
     * 6. Assuming toString() returns a memory address.
     *
     * 7. Assuming getClass() returns the reference type.
     *
     * 8. Assuming Object.clone() performs a deep copy.
     *
     * 9. Forgetting that Cloneable does not declare clone().
     *
     * 10. Calling wait() without owning the object's monitor.
     *
     * 11. Calling notify() outside synchronized ownership.
     *
     * 12. Using if instead of while when checking a condition
     *     around wait().
     *
     * 13. Assuming sleep() releases a monitor.
     *
     * 14. Depending on finalize() for resource cleanup.
     *
     * 15. Assuming every Java reference is an Object reference.
     *     Primitive values are not reference types.
     *
     *
     * 60. IMPORTANT COMPARISONS
     *
     * == VS equals():
     *
     * ==:
     * - Primitive value comparison or reference identity.
     *
     * equals():
     * - Logical equality according to implementation.
     *
     * equals() VS hashCode():
     *
     * equals():
     * - Checks logical equality.
     *
     * hashCode():
     * - Produces an integer used by hash-based structures.
     *
     * getClass() VS instanceof:
     *
     * getClass():
     * - Exact runtime class information.
     *
     * instanceof:
     * - Type compatibility check.
     *
     * wait() VS sleep():
     *
     * wait():
     * - Object monitor coordination.
     * - Releases the object's monitor.
     *
     * sleep():
     * - Pauses the current thread.
     * - Does not release held monitors.
     *
     * SHALLOW COPY VS DEEP COPY:
     *
     * Shallow:
     * - Copies field values, including references.
     *
     * Deep:
     * - Copies selected nested mutable state as well.
     *
     * clone() VS COPY CONSTRUCTOR:
     *
     * clone():
     * - Uses Object.clone() behavior.
     * - Requires Cloneable for default clone support.
     * - Performs shallow field copying by default.
     *
     * Copy constructor:
     * - Explicitly defines what is copied.
     * - Does not require Cloneable.
     *
     *
     * 61. INTERVIEW QUESTIONS AND ANSWERS
     *
     * Q1. What is the Object class?
     *
     * A. Object is the root superclass of Java's class hierarchy.
     *
     * Q2. Is Object a class or interface?
     *
     * A. Object is a class.
     *
     * Q3. Can we create an Object instance?
     *
     * A. Yes.
     *
     * Q4. Which package contains Object?
     *
     * A. java.lang.
     *
     * Q5. Is java.lang imported automatically?
     *
     * A. Types in java.lang are implicitly available.
     *
     * Q6. What does toString() do?
     *
     * A. Returns a string representation of an object.
     *
     * Q7. What is the default toString() format?
     *
     * A. It is generally the class name, '@', and the
     *    hexadecimal representation of the hash code.
     *
     * Q8. Does toString() return a memory address?
     *
     * A. No. The default output is not guaranteed to
     *    represent a memory address.
     *
     * Q9. What is equals()?
     *
     * A. A method used to determine logical equality
     *    according to its implementation.
     *
     * Q10. What is the default behavior of equals()?
     *
     * A. Object's implementation compares object identity.
     *
     * Q11. What is the difference between == and equals()?
     *
     * A. == compares primitive values or reference identity;
     *    equals() can compare logical object values.
     *
     * Q12. What are the equals() contract rules?
     *
     * A. Reflexivity, symmetry, transitivity, consistency,
     *    and non-null behavior.
     *
     * Q13. What is hashCode()?
     *
     * A. It returns an integer hash value for an object.
     *
     * Q14. If two objects are equal, must their hash codes
     *     be equal?
     *
     * A. Yes.
     *
     * Q15. If two objects have equal hash codes, must they
     *     be equal?
     *
     * A. No. Hash collisions are permitted.
     *
     * Q16. Why override hashCode() when overriding equals()?
     *
     * A. To preserve the hashCode contract and ensure
     *    correct behavior in hash-based collections.
     *
     * Q17. What is getClass()?
     *
     * A. It returns the runtime Class object of the object.
     *
     * Q18. Can getClass() be overridden?
     *
     * A. No. It is final.
     *
     * Q19. What is clone()?
     *
     * A. It creates a field-by-field copy of an object
     *    using Object's default implementation.
     *
     * Q20. What is Cloneable?
     *
     * A. A marker interface that permits Object.clone()
     *    to perform its default field-copy behavior.
     *
     * Q21. Does Object.clone() perform a deep copy?
     *
     * A. No. It performs a shallow field copy by default.
     *
     * Q22. What is a shallow copy?
     *
     * A. A copy where reference fields still refer to
     *    the same referenced objects.
     *
     * Q23. What is a deep copy?
     *
     * A. A copy that duplicates selected nested mutable
     *    objects according to a defined copying policy.
     *
     * Q24. What is wait()?
     *
     * A. An Object method used for thread coordination
     *    that releases the object's monitor while waiting.
     *
     * Q25. Why must wait() be called inside synchronized
     *     ownership?
     *
     * A. The current thread must own the object's monitor.
     *
     * Q26. What does notify() do?
     *
     * A. It wakes one thread waiting on the object's monitor.
     *
     * Q27. What does notifyAll() do?
     *
     * A. It wakes all threads waiting on the object's monitor.
     *
     * Q28. Does notify() immediately transfer the monitor?
     *
     * A. No. The notified thread must reacquire the monitor.
     *
     * Q29. What is a spurious wakeup?
     *
     * A. A waiting thread can resume without an explicit
     *    notification or timeout being the reason.
     *
     * Q30. Why use while with wait()?
     *
     * A. To recheck the condition after waking up.
     *
     * Q31. What is finalize()?
     *
     * A. A deprecated lifecycle hook historically associated
     *    with cleanup before garbage collection.
     *
     * Q32. Is finalize() guaranteed to execute?
     *
     * A. No.
     *
     * Q33. What should replace finalize() for resource cleanup?
     *
     * A. AutoCloseable, close(), and try-with-resources.
     *
     * Q34. What is object resurrection?
     *
     * A. Making an object reachable again during finalization.
     *
     * Q35. Can an array be assigned to an Object reference?
     *
     * A. Yes. Arrays are objects.
     *
     *
     * 62. FINAL REVISION CHECKLIST
     *
     * FUNDAMENTALS
     * [ ] Object class and its role.
     * [ ] Object's position in the class hierarchy.
     * [ ] java.lang package.
     * [ ] Object reference and polymorphism.
     *
     * OBJECT METHODS
     * [ ] toString().
     * [ ] equals().
     * [ ] hashCode().
     * [ ] getClass().
     * [ ] clone().
     * [ ] wait().
     * [ ] notify().
     * [ ] notifyAll().
     * [ ] finalize().
     *
     * EQUALITY AND HASHING
     * [ ] == vs equals().
     * [ ] equals() contract.
     * [ ] equals() overriding.
     * [ ] equals() overloading mistake.
     * [ ] hashCode() contract.
     * [ ] HashMap and HashSet behavior.
     * [ ] Hash collisions.
     * [ ] Mutable keys.
     *
     * RUNTIME TYPE INFORMATION
     * [ ] getClass().
     * [ ] Class object.
     * [ ] instanceof.
     * [ ] Reflection basics.
     * [ ] Declared vs inherited methods.
     *
     * COPYING
     * [ ] clone().
     * [ ] Cloneable.
     * [ ] Shallow copy.
     * [ ] Deep copy.
     * [ ] Copy constructors.
     * [ ] Static copy factories.
     *
     * THREAD COORDINATION
     * [ ] Object monitors.
     * [ ] wait() and monitor ownership.
     * [ ] notify() and notifyAll().
     * [ ] Spurious wakeups.
     * [ ] while condition pattern.
     * [ ] wait() vs sleep().
     * [ ] InterruptedException.
     * [ ] Lock and Condition alternatives.
     *
     * OBJECT LIFECYCLE
     * [ ] finalize() deprecation.
     * [ ] Object resurrection.
     * [ ] AutoCloseable.
     * [ ] try-with-resources.
     * [ ] Cleaner.
     *
     * ADVANCED
     * [ ] Object and interfaces.
     * [ ] Object and arrays.
     * [ ] Object and records.
     * [ ] Object and enums.
     * [ ] Object and collections.
     * [ ] Object and immutability.
     *
     * ============================================================
     *                    END OF OBJECT CLASS
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Object Class. Happy Learning ..!");
    }
}
