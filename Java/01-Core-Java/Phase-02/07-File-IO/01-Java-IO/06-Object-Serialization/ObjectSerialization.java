
public class ObjectSerialization {

    /*
     * ================================================================
     *                     OBJECT SERIALIZATION
     * ================================================================
     *
     * 1. WHAT IS OBJECT SERIALIZATION?
     *
     * Serialization is the process of converting an object's state
     * into a byte stream.
     *
     * This byte stream can be:
     *
     * - Stored in a file.
     * - Sent over a network.
     * - Transferred between applications.
     * - Used for later reconstruction of the object.
     *
     * In simple words:
     *
     * Serialization:
     * Java Object -> Byte Stream
     *
     * Deserialization:
     * Byte Stream -> Java Object
     *
     * Example:
     *
     * Suppose we have a Student object:
     *
     * Student student = new Student(101, "Vinay", 8.5);
     *
     * Serialization converts its serializable object state
     * into a byte representation.
     *
     * Later, deserialization reconstructs an object from
     * the serialized data.
     *
     *
     * 2. WHY DO WE NEED SERIALIZATION?
     *
     * Consider an application that creates a Student object.
     *
     * The object exists in JVM memory.
     *
     * What if we want to:
     *
     * - Save its state into a file?
     * - Transfer it to another application?
     * - Restore it in another JVM?
     *
     * Serialization provides a mechanism for these operations.
     *
     * Common historical and technical use cases:
     *
     * - Object persistence.
     * - Network communication.
     * - Distributed Java applications.
     * - Session replication.
     * - Caching and object transfer.
     *
     * Important:
     * Java native serialization is not automatically the best
     * choice for modern application data exchange.
     * Formats such as JSON, Protocol Buffers and Avro are often
     * preferred for interoperable systems.
     *
     *
     * 3. SERIALIZABLE INTERFACE
     *
     * Serializable belongs to java.io.
     *
     * Fully qualified name:
     * java.io.Serializable
     *
     * It is a marker interface.
     *
     * A marker interface does not declare methods.
     *
     * It indicates that a class participates in a particular
     * mechanism recognized by the Java platform.
     *
     * A class generally implements Serializable to allow its
     * instances to be serialized by Java's object serialization
     * mechanism.
     *
     * Example:
     *
     * class Student implements Serializable {
     *
     *     int id;
     *     String name;
     *     double cgpa;
     * }
     *
     * Important:
     * Implementing Serializable does not itself perform
     * serialization.
     *
     * ObjectOutputStream performs the serialization operation.
     *
     *
     * 4. OBJECTOUTPUTSTREAM
     *
     * ObjectOutputStream writes objects and primitive values
     * to an underlying OutputStream.
     *
     * It belongs to java.io.
     *
     * Important methods:
     *
     * writeObject(Object obj)
     *     Writes an object.
     *
     * writeInt(int value)
     *     Writes an int.
     *
     * writeBoolean(boolean value)
     *     Writes a boolean.
     *
     * writeUTF(String value)
     *     Writes a modified UTF-8 string.
     *
     * flush()
     *     Flushes buffered output.
     *
     * close()
     *     Closes the stream.
     *
     *
     * 5. OBJECTINPUTSTREAM
     *
     * ObjectInputStream reads objects and primitive values
     * from an underlying InputStream.
     *
     * Important methods:
     *
     * readObject()
     *     Reads and reconstructs an object.
     *
     * readInt()
     *     Reads an int.
     *
     * readBoolean()
     *     Reads a boolean.
     *
     * readUTF()
     *     Reads a modified UTF-8 string.
     *
     * Important:
     * readObject() returns Object.
     *
     * The result generally needs to be cast to the expected type.
     *
     *
     * 6. BASIC SERIALIZATION EXAMPLE
     *
     * Consider:
     *
     * import java.io.*;
     *
     * class Student implements Serializable {
     *
     *     private int id;
     *     private String name;
     *     private double cgpa;
     *
     *     Student(int id, String name, double cgpa) {
     *         this.id = id;
     *         this.name = name;
     *         this.cgpa = cgpa;
     *     }
     *
     *     public String toString() {
     *         return id + " " + name + " " + cgpa;
     *     }
     * }
     *
     * SERIALIZATION:
     *
     * Student student =
     *     new Student(101, "Vinay", 8.5);
     *
     * try (ObjectOutputStream output =
     *          new ObjectOutputStream(
     *              new FileOutputStream("student.ser"))) {
     *
     *     output.writeObject(student);
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     * DESERIALIZATION:
     *
     * try (ObjectInputStream input =
     *          new ObjectInputStream(
     *              new FileInputStream("student.ser"))) {
     *
     *     Student restored =
     *         (Student) input.readObject();
     *
     *     System.out.println(restored);
     *
     * } catch (IOException | ClassNotFoundException e) {
     *     e.printStackTrace();
     * }
     *
     * Important:
     * - ObjectOutputStream writes the object representation.
     * - ObjectInputStream reconstructs the object.
     * - readObject() can throw ClassNotFoundException.
     * - Serialization-related operations can throw IOException.
     *
     *
     * 7. WHAT HAPPENS DURING SERIALIZATION?
     *
     * Consider:
     *
     * Student student =
     *     new Student(101, "Vinay", 8.5);
     *
     * output.writeObject(student);
     *
     * Conceptually, Java:
     *
     * 1. Checks whether the object's class is serializable.
     * 2. Processes the serializable class hierarchy.
     * 3. Identifies the serializable instance state.
     * 4. Handles referenced objects in the object graph.
     * 5. Writes class and object information.
     * 6. Writes the serialized representation to the stream.
     *
     * Important:
     * The actual serialization format is more complex than
     * simply writing the values of the object's fields.
     *
     * It also maintains information about object identity,
     * references and class descriptors.
     *
     *
     * 8. WHAT IS AN OBJECT GRAPH?
     *
     * An object can refer to other objects.
     *
     * Example:
     *
     * class Address implements Serializable {
     *     String city;
     * }
     *
     * class Student implements Serializable {
     *     String name;
     *     Address address;
     * }
     *
     * Student
     *    |
     *    v
     * Address
     *
     * The connected objects form an object graph.
     *
     * When an object is serialized, its reachable object graph
     * is traversed according to serialization rules.
     *
     * Important:
     * Every object reached through a non-transient,
     * non-static serializable field must itself be serializable,
     * unless it is handled specially.
     *
     * Otherwise, serialization can fail with
     * NotSerializableException.
     *
     *
     * 9. WHAT HAPPENS IF A REFERENCED OBJECT IS NOT SERIALIZABLE?
     *
     * Example:
     *
     * class Address {
     *     String city;
     * }
     *
     * class Student implements Serializable {
     *     String name;
     *     Address address;
     * }
     *
     * Student implements Serializable.
     * Address does not.
     *
     * When Student is serialized, Java also encounters Address.
     *
     * Because Address is not serializable, serialization
     * normally fails with:
     *
     * java.io.NotSerializableException
     *
     * Possible solutions:
     *
     * 1. Make Address implement Serializable.
     *
     * 2. Mark the Address reference transient if it should not
     *    be serialized.
     *
     * 3. Use custom serialization logic when appropriate.
     *
     *
     * 10. SERIALVERSIONUID
     *
     * serialVersionUID is a version identifier used by Java
     * serialization to verify compatibility between the
     * serialized data and the local class.
     *
     * Example:
     *
     * private static final long serialVersionUID = 1L;
     *
     * Why is it important?
     *
     * Consider:
     *
     * 1. Serialize an object using version 1 of a class.
     * 2. Modify the class.
     * 3. Attempt to deserialize the old object using the new class.
     *
     * Java checks the class's serialization version identifier.
     *
     * If the identifiers are incompatible, deserialization
     * may fail with InvalidClassException.
     *
     * Recommended practice:
     *
     * class Student implements Serializable {
     *
     *     private static final long serialVersionUID = 1L;
     *
     *     int id;
     *     String name;
     * }
     *
     * Explicitly declaring serialVersionUID makes the intended
     * serialization version identifier clear.
     *
     * Important:
     * A matching serialVersionUID does not guarantee that every
     * class change is semantically compatible.
     *
     *
     * 11. WHAT HAPPENS IF WE DO NOT DECLARE SERIALVERSIONUID?
     *
     * If a serializable class does not explicitly declare
     * serialVersionUID, the serialization runtime calculates
     * a default value based on class details.
     *
     * Changes to the class can change that calculated value.
     *
     * This can cause deserialization incompatibility.
     *
     * Therefore, declaring serialVersionUID explicitly is
     * generally recommended for classes whose serialized
     * data must remain compatible across versions.
     *
     *
     * 12. TRANSIENT KEYWORD
     *
     * transient is a Java keyword used with instance fields.
     *
     * It indicates that a field should be excluded from
     * default Java serialization.
     *
     * Example:
     *
     * class User implements Serializable {
     *
     *     String username;
     *
     *     transient String password;
     * }
     *
     * During default serialization:
     *
     * username -> Serialized.
     * password -> Not serialized.
     *
     * Why use transient?
     *
     * - Avoid serializing sensitive information.
     * - Exclude temporary state.
     * - Exclude values that can be recalculated.
     * - Exclude non-serializable references.
     *
     * Important:
     * transient does not encrypt a field.
     * It only excludes that field from default serialization.
     *
     *
     * 13. WHAT VALUE DOES A TRANSIENT FIELD GET AFTER
     *     DESERIALIZATION?
     *
     * During default deserialization, fields excluded from
     * serialization receive their default values before
     * applicable serialization callbacks are executed.
     *
     * Examples:
     *
     * int      -> 0
     * boolean  -> false
     * double   -> 0.0
     * Object   -> null
     *
     * Example:
     *
     * class Student implements Serializable {
     *
     *     int id = 101;
     *     transient int marks = 90;
     * }
     *
     * After ordinary deserialization:
     *
     * id    -> 101
     * marks -> 0
     *
     * Important:
     * Constructors and field initializers of serializable
     * classes are not executed during ordinary deserialization.
     *
     *
     * 14. STATIC FIELDS AND SERIALIZATION
     *
     * Static fields belong to the class, not individual objects.
     *
     * Example:
     *
     * class Student implements Serializable {
     *
     *     int id;
     *     static String college = "Miracle";
     * }
     *
     * The static field is not part of the serialized
     * instance state.
     *
     * Why?
     *
     * Serialization saves the state of an object, not the
     * state of its class.
     *
     * After deserialization, the static field's value comes
     * from the currently loaded class.
     *
     * Important:
     * transient is generally unnecessary for static fields,
     * because static fields are already excluded from
     * default instance serialization.
     *
     *
     * 15. SERIALIZATION AND INHERITANCE
     *
     * Consider:
     *
     * class Person {
     *     String name;
     * }
     *
     * class Student extends Person implements Serializable {
     *     int id;
     * }
     *
     * The serializable subclass inherits from a non-serializable
     * superclass.
     *
     * During deserialization:
     *
     * - The no-argument constructor of the first non-serializable
     *   superclass is invoked.
     * - Its serializable subclasses' constructors are not invoked
     *   through ordinary deserialization.
     * - Serializable class fields are restored from the stream.
     *
     * Important:
     * The first non-serializable superclass must have an
     * accessible no-argument constructor.
     *
     * If it does not, deserialization can fail.
     *
     * The non-serializable superclass's state is not restored
     * from the serialized stream by default.
     *
     *
     * 16. SERIALIZATION OF INHERITANCE HIERARCHIES
     *
     * If both parent and child implement Serializable:
     *
     * class Person implements Serializable {
     *     String name;
     * }
     *
     * class Student extends Person {
     *     int id;
     * }
     *
     * The serializable state of both classes participates
     * in serialization.
     *
     * If a serializable class inherits from another serializable
     * class, it does not need to repeat implements Serializable.
     *
     * The child inherits the serializability contract.
     *
     *
     * 17. EXTERNALIZABLE INTERFACE
     *
     * Externalizable belongs to java.io.
     *
     * It extends Serializable.
     *
     * It allows a class to control its own serialization format
     * by implementing two methods:
     *
     * writeExternal(ObjectOutput out)
     *
     * readExternal(ObjectInput in)
     *
     * Example:
     *
     * class Student implements Externalizable {
     *
     *     int id;
     *     String name;
     *
     *     public Student() {
     *         // Public no-argument constructor required.
     *     }
     *
     *     public void writeExternal(ObjectOutput out)
     *             throws IOException {
     *
     *         out.writeInt(id);
     *         out.writeUTF(name);
     *     }
     *
     *     public void readExternal(ObjectInput in)
     *             throws IOException {
     *
     *         id = in.readInt();
     *         name = in.readUTF();
     *     }
     * }
     *
     * Important:
     * - Externalizable provides explicit control over the
     *   serialized representation.
     * - Its public no-argument constructor is required for
     *   ordinary deserialization.
     * - The class is responsible for reading and writing
     *   its state correctly.
     *
     * Difference:
     *
     * Serializable:
     * Uses default serialization unless customized.
     *
     * Externalizable:
     * Requires explicit readExternal() and writeExternal()
     * implementations.
     *
     *
     * 18. CUSTOM SERIALIZATION METHODS
     *
     * Serializable classes can customize default serialization
     * using specially named private methods.
     *
     * A. writeObject()
     *
     * private void writeObject(ObjectOutputStream out)
     *         throws IOException {
     *
     *     out.defaultWriteObject();
     *
     *     // Write additional custom data.
     * }
     *
     * B. readObject()
     *
     * private void readObject(ObjectInputStream in)
     *         throws IOException, ClassNotFoundException {
     *
     *     in.defaultReadObject();
     *
     *     // Restore or validate custom state.
     * }
     *
     * These methods are recognized by the serialization
     * mechanism when declared with the required signatures.
     *
     * defaultWriteObject():
     * Performs default serialization of the serializable fields.
     *
     * defaultReadObject():
     * Performs default restoration of the serializable fields.
     *
     * Important:
     * - These methods are not ordinary overrides.
     * - Their signatures and access requirements matter.
     * - The private methods are invoked by the serialization
     *   mechanism.
     *
     *
     * 19. readResolve()
     *
     * readResolve() allows a class to replace the object
     * returned by deserialization.
     *
     * Example:
     *
     * private Object readResolve() throws ObjectStreamException {
     *     return INSTANCE;
     * }
     *
     * Common use case:
     * Maintaining singleton identity during deserialization.
     *
     * Without readResolve(), deserialization can create
     * another object even when the class follows a singleton
     * pattern.
     *
     * Important:
     * readResolve() is a special serialization callback.
     * It is not an ordinary method override.
     *
     *
     * 20. writeReplace()
     *
     * writeReplace() allows an object to substitute another
     * object before serialization.
     *
     * Example:
     *
     * private Object writeReplace() throws ObjectStreamException {
     *     return new SerializationProxy(this);
     * }
     *
     * This is commonly used in the serialization proxy pattern.
     *
     * The proxy object is serialized instead of the original
     * object.
     *
     * During deserialization, the proxy can validate the data
     * and reconstruct the intended object.
     *
     *
     * 21. SERIALIZATION PROXY PATTERN
     *
     * The serialization proxy pattern is a safer design
     * approach for certain serializable classes.
     *
     * Instead of serializing the original object directly:
     *
     * Original Object
     *       |
     *       v
     * Serialization Proxy
     *       |
     *       v
     * Serialized Data
     *
     * During deserialization:
     *
     * Serialized Data
     *       |
     *       v
     * Proxy Object
     *       |
     *       v
     * Validated Original Object
     *
     * Benefits:
     * - Controls the serialized representation.
     * - Supports validation during reconstruction.
     * - Helps preserve class invariants.
     * - Helps prevent certain serialization-related attacks.
     *
     *
     * 22. OBJECT IDENTITY AND SHARED REFERENCES
     *
     * Java serialization preserves object identity and shared
     * references within a serialization stream.
     *
     * Example:
     *
     * Address address = new Address();
     *
     * Student first = new Student();
     * Student second = new Student();
     *
     * first.address = address;
     * second.address = address;
     *
     * Both objects refer to the same Address instance.
     *
     * If they are serialized within the same ObjectOutputStream,
     * the shared reference is normally preserved when restored
     * from that stream.
     *
     * This means the restored objects can still refer to the
     * same reconstructed Address instance.
     *
     *
     * 23. SERIALIZATION HANDLES AND REFERENCES
     *
     * ObjectOutputStream maintains information about objects
     * already written to the stream.
     *
     * When the same object is encountered again, serialization
     * can write a reference to the earlier object rather than
     * serializing it again.
     *
     * This helps preserve:
     *
     * - Object identity.
     * - Shared references.
     * - Cyclic object graphs.
     *
     * Example:
     *
     * An object can refer to another object that eventually
     * refers back to the original object.
     *
     * Java serialization can handle such cyclic references.
     *
     *
     * 24. RESETTING OBJECT STREAM HANDLES
     *
     * ObjectOutputStream provides:
     *
     * reset()
     *
     * It resets the stream's object handle state.
     *
     * Why is this useful?
     *
     * Suppose the same object is modified and written multiple
     * times to the same ObjectOutputStream.
     *
     * Without resetting the stream's handles, a later writeObject()
     * may write a reference to the previously written object
     * rather than a fresh representation of its modified state.
     *
     * Calling reset() allows subsequent objects to be written
     * without reusing the previous handle mappings.
     *
     * Important:
     * reset() does not erase bytes already written.
     *
     *
     * 25. OBJECT STREAMS AND PRIMITIVE DATA
     *
     * ObjectOutputStream and ObjectInputStream also implement
     * DataOutput and DataInput respectively.
     *
     * Therefore, they can write and read primitive values
     * alongside objects.
     *
     * Example:
     *
     * output.writeInt(100);
     * output.writeObject(student);
     *
     * Corresponding reading:
     *
     * input.readInt();
     * input.readObject();
     *
     * The order and data types must match.
     *
     *
     * 26. EXCEPTIONS IN SERIALIZATION
     *
     * Important exceptions:
     *
     * NotSerializableException:
     * Thrown when an object that must be serialized is not
     * serializable.
     *
     * InvalidClassException:
     * Can occur when the local class is incompatible with
     * the serialized class descriptor, including certain
     * serialVersionUID mismatches.
     *
     * ClassNotFoundException:
     * Can occur when the class needed to reconstruct an object
     * cannot be found.
     *
     * StreamCorruptedException:
     * Indicates invalid or corrupted serialization stream data.
     *
     * OptionalDataException:
     * Can occur when object data is read but primitive data
     * is encountered where an object was expected, or vice versa.
     *
     * IOException:
     * General I/O failure.
     *
     *
     * 27. SERIALIZATION SECURITY
     *
     * Native Java deserialization can be dangerous when
     * processing untrusted input.
     *
     * Why?
     *
     * Deserialization can reconstruct complex object graphs.
     * Some classes can execute behavior during deserialization
     * through callbacks or related mechanisms.
     *
     * Carefully crafted streams may exploit classes available
     * on the application's classpath.
     *
     * Risks include:
     *
     * - Unexpected object construction.
     * - Denial of service.
     * - Gadget-chain exploitation.
     * - Security-sensitive behavior during deserialization.
     *
     * Best practices:
     *
     * 1. Never deserialize untrusted data without strong controls.
     *
     * 2. Prefer safer interchange formats where appropriate.
     *
     * 3. Use ObjectInputFilter to restrict allowed classes,
     *    graph depth, references and array sizes.
     *
     * 4. Validate reconstructed data.
     *
     * 5. Keep dependencies updated.
     *
     * 6. Avoid exposing unrestricted native deserialization
     *    endpoints.
     *
     *
     * 28. OBJECTINPUTFILTER
     *
     * ObjectInputFilter was introduced in Java 9.
     *
     * It allows filtering of classes and object graphs during
     * deserialization.
     *
     * Example:
     *
     * ObjectInputFilter filter =
     *     ObjectInputFilter.Config.createFilter(
     *         "com.example.Student;!*"
     *     );
     *
     * ObjectInputStream input =
     *     new ObjectInputStream(
     *         new FileInputStream("student.ser")
     *     );
     *
     * input.setObjectInputFilter(filter);
     *
     * The filter allows the specified class and rejects
     * classes not covered by the allow rule.
     *
     * Important:
     * Filter patterns and policies must be designed carefully.
     * Restricting classes alone may not address every resource
     * exhaustion risk.
     *
     *
     * 29. SERIALIZATION AND RECORD CLASSES
     *
     * Java records can implement Serializable.
     *
     * Example:
     *
     * record Student(int id, String name)
     *         implements Serializable {
     * }
     *
     * Records have special serialization and deserialization
     * behavior.
     *
     * During record deserialization, the canonical constructor
     * is invoked with the component values.
     *
     * This differs from ordinary serializable classes,
     * whose constructors are generally not invoked.
     *
     *
     * 30. JAVA SERIALIZATION VERSION HISTORY
     *
     * Java 1.1:
     * Object serialization introduced.
     *
     * Java 1.2:
     * Serialization-related APIs evolved.
     *
     * Java 9:
     * ObjectInputFilter introduced.
     *
     * Modern Java:
     * Records support serialization when they implement
     * Serializable.
     *
     * Important:
     * Native serialization is a long-standing Java mechanism.
     * It should be used with care, especially across security
     * boundaries.
     *
     *
     * 31. JAVA SERIALIZATION VS JSON
     *
     * JAVA SERIALIZATION:
     *
     * - Java-specific binary format.
     * - Preserves object graph references.
     * - Can reconstruct Java objects.
     * - Requires compatible classes.
     * - Dangerous with untrusted input if not filtered.
     *
     * JSON:
     *
     * - Text-based data-interchange format.
     * - Language-independent.
     * - Human-readable.
     * - Requires explicit mapping between JSON data and objects.
     * - Does not automatically preserve Java object identity.
     *
     * In modern REST APIs, JSON is commonly used instead of
     * native Java serialization.
     *
     *
     * 32. COMMON MISTAKES
     *
     * 1. Forgetting to implement Serializable.
     *
     * 2. Assuming Serializable automatically writes objects
     *    without ObjectOutputStream.
     *
     * 3. Forgetting that referenced objects must also be
     *    serializable unless handled specially.
     *
     * 4. Assuming static fields are serialized.
     *
     * 5. Assuming transient fields retain their original values
     *    after deserialization.
     *
     * 6. Assuming constructors of serializable classes execute
     *    during ordinary deserialization.
     *
     * 7. Forgetting to declare serialVersionUID when long-term
     *    compatibility matters.
     *
     * 8. Assuming matching serialVersionUID guarantees
     *    semantic compatibility.
     *
     * 9. Reading objects from untrusted serialized data.
     *
     * 10. Confusing Serializable with Externalizable.
     *
     * 11. Assuming every writeObject() method is automatically
     *     treated as a serialization callback. Its signature
     *     and access requirements must match.
     *
     *
     * 33. INTERVIEW QUESTIONS
     *
     * 1. What is serialization?
     * 2. What is deserialization?
     * 3. What is Serializable?
     * 4. Why is Serializable called a marker interface?
     * 5. What are ObjectInputStream and ObjectOutputStream?
     * 6. What is serialVersionUID?
     * 7. What happens if serialVersionUID is not declared?
     * 8. What is transient?
     * 9. Are static fields serialized?
     * 10. What happens to transient fields after deserialization?
     * 11. What happens if a referenced object is not serializable?
     * 12. How does serialization work with inheritance?
     * 13. What is Externalizable?
     * 14. What is the difference between Serializable and
     *     Externalizable?
     * 15. What are writeObject() and readObject()?
     * 16. What is readResolve()?
     * 17. What is writeReplace()?
     * 18. What is the serialization proxy pattern?
     * 19. What is an object graph?
     * 20. How does serialization preserve shared references?
     * 21. What is ObjectInputFilter?
     * 22. Why is deserializing untrusted input dangerous?
     * 23. Can Java records be serialized?
     * 24. What is the difference between Java serialization
     *     and JSON?
     *
     *
     * 34. QUICK REVISION
     *
     * Serialization:
     * Object state -> Byte stream.
     *
     * Deserialization:
     * Byte stream -> Reconstructed object.
     *
     * Serializable:
     * Marker interface enabling default Java serialization.
     *
     * ObjectOutputStream:
     * Writes objects.
     *
     * ObjectInputStream:
     * Reads objects.
     *
     * serialVersionUID:
     * Serialization version identifier.
     *
     * transient:
     * Excludes an instance field from default serialization.
     *
     * static:
     * Static fields are not part of default serialized
     * instance state.
     *
     * Externalizable:
     * Provides explicit serialization control.
     *
     * writeObject():
     * Custom serialization callback.
     *
     * readObject():
     * Custom deserialization callback.
     *
     * readResolve():
     * Replaces the object returned by deserialization.
     *
     * writeReplace():
     * Replaces the object before serialization.
     *
     * ObjectInputFilter:
     * Restricts objects and resources during deserialization.
     *
     * Security:
     * Never blindly deserialize untrusted data.
     *
     * ================================================================
     *                   END OF OBJECT SERIALIZATION
     * ================================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Object Serialization , Happy Learning ..!");
    }
}
