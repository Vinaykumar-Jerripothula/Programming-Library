
public class Properties {

    /*
     * ============================================================
     *                     PROPERTIES IN JAVA
     * ============================================================
     *
     * 1. WHAT IS PROPERTIES?
     *
     * Properties is a class in java.util used to store
     * configuration data as key-value pairs.
     *
     * Both keys and values are generally represented as Strings.
     *
     * Example:
     *
     * username = vinay
     * password = secret
     * database = mysql
     * port = 3306
     *
     * Properties is commonly used to store application settings
     * outside Java source code.
     *
     * This allows configuration to be changed without modifying
     * the application logic.
     *
     *
     * 2. WHY DO WE NEED PROPERTIES?
     *
     * Imagine a Java application connecting to a database.
     *
     * We need:
     *
     * - Database URL
     * - Username
     * - Password
     * - Database driver
     *
     * One approach is to hardcode these values:
     *
     * String url = "jdbc:mysql://localhost:3306/company";
     * String username = "root";
     * String password = "password";
     *
     * Problem:
     *
     * If the database configuration changes, we must modify
     * the source code and rebuild the application.
     *
     * Better approach:
     *
     * Store configuration in a properties file.
     *
     * Example: application.properties
     *
     * db.url=jdbc:mysql://localhost:3306/company
     * db.username=root
     * db.password=secret
     *
     * Java can load these settings at runtime.
     *
     * Benefits:
     *
     * - Separates configuration from application logic.
     * - Makes configuration easier to maintain.
     * - Supports different environments.
     * - Avoids hardcoding configuration values.
     *
     *
     * 3. PROPERTIES CLASS HIERARCHY
     *
     * Object
     *   |
     * Dictionary
     *   |
     * Hashtable
     *   |
     * Properties
     *
     * Properties extends Hashtable<Object, Object>.
     *
     * It inherits synchronized Map operations.
     *
     * Important:
     *
     * Properties is a legacy class, but it remains useful
     * for configuration management.
     *
     *
     * 4. CREATING A PROPERTIES OBJECT
     *
     * Import:
     *
     * import java.util.Properties;
     *
     * Create:
     *
     * Properties properties = new Properties();
     *
     * Add properties:
     *
     * properties.setProperty("username", "vinay");
     * properties.setProperty("database", "mysql");
     *
     * Read properties:
     *
     * System.out.println(
     *     properties.getProperty("username")
     * );
     *
     *
     * 5. IMPORTANT METHODS
     *
     * setProperty(key, value)
     *
     * Stores a String key-value pair.
     *
     * getProperty(key)
     *
     * Retrieves a String value.
     *
     * getProperty(key, defaultValue)
     *
     * Returns the default value when the property is unavailable.
     *
     * Example:
     *
     * String port = properties.getProperty("port", "8080");
     *
     * If port is not present, 8080 is returned.
     *
     * remove(key)
     *
     * Removes a property.
     *
     * containsKey(key)
     *
     * Checks whether the key exists in this Properties object.
     *
     * containsValue(value)
     *
     * Checks whether a value exists.
     *
     * size()
     *
     * Returns the number of mappings in this object.
     *
     * clear()
     *
     * Removes all mappings from this object.
     *
     *
     * 6. SETPROPERTY() VS PUT()
     *
     * Properties inherits put() from Hashtable.
     *
     * However, the recommended method for adding properties is:
     *
     * setProperty()
     *
     * Example:
     *
     * properties.setProperty("language", "Java");
     *
     * put() accepts Object keys and values:
     *
     * properties.put("language", "Java");
     *
     * IMPORTANT:
     *
     * Properties is intended for String key-value pairs.
     *
     * If non-String keys or values are inserted using inherited
     * methods such as put(), methods like getProperty() and
     * store() may not behave as expected for those entries.
     *
     * Prefer setProperty() for normal configuration data.
     *
     *
     * 7. GETPROPERTY() VS GET()
     *
     * getProperty():
     *
     * Returns a String value.
     *
     * It also searches the defaults chain.
     *
     * get():
     *
     * Is inherited from Hashtable.
     *
     * Returns an Object.
     *
     * It looks up a mapping in the current Properties object.
     *
     * Example:
     *
     * String value = properties.getProperty("username");
     *
     * Object object = properties.get("username");
     *
     * For normal configuration access, prefer getProperty().
     *
     *
     * 8. WHAT IS A PROPERTIES FILE?
     *
     * A properties file is a text file used to store
     * configuration entries.
     *
     * Common extension:
     *
     * .properties
     *
     * Example: application.properties
     *
     * app.name=Student Management System
     * app.version=1.0
     * db.username=root
     * db.password=secret
     * server.port=8080
     *
     * Each entry represents a key-value pair.
     *
     * Common separators include:
     *
     * =
     * :
     * whitespace
     *
     * Example:
     *
     * username=vinay
     * username:vinay
     * username vinay
     *
     * All three forms are supported by the properties format.
     *
     *
     * 9. COMMENTS IN PROPERTIES FILES
     *
     * Lines beginning with # or ! are comments.
     *
     * Example:
     *
     * # Database configuration
     * db.username=root
     *
     * ! Server configuration
     * server.port=8080
     *
     * Comments are ignored while loading the file.
     *
     *
     * 10. LOADING A PROPERTIES FILE
     *
     * Properties provides load() methods.
     *
     * Example:
     *
     * import java.io.FileInputStream;
     * import java.io.IOException;
     * import java.util.Properties;
     *
     * public class Example {
     *
     *     public static void main(String[] args)
     *             throws IOException {
     *
     *         Properties properties = new Properties();
     *
     *         try (FileInputStream input =
     *                  new FileInputStream("application.properties")) {
     *
     *             properties.load(input);
     *         }
     *
     *         System.out.println(
     *             properties.getProperty("db.username")
     *         );
     *     }
     * }
     *
     * What happens?
     *
     * 1. A Properties object is created.
     * 2. The file is opened.
     * 3. load() reads the configuration entries.
     * 4. The entries become available through getProperty().
     * 5. The stream is closed automatically.
     *
     *
     * 11. LOAD() USING DIFFERENT INPUT TYPES
     *
     * Properties supports:
     *
     * load(InputStream)
     *
     * Reads properties from a byte stream.
     *
     * load(Reader)
     *
     * Reads properties from a character stream.
     *
     * Example using Reader:
     *
     * try (FileReader reader =
     *          new FileReader("application.properties")) {
     *
     *     properties.load(reader);
     * }
     *
     * IMPORTANT ENCODING DIFFERENCE:
     *
     * load(InputStream):
     *
     * Uses the traditional properties-file encoding:
     * ISO-8859-1, with Unicode characters represented
     * using \uXXXX escapes when necessary.
     *
     * load(Reader):
     *
     * Uses the character encoding already applied by the Reader.
     *
     * For modern UTF-8 configuration files, using an explicitly
     * configured UTF-8 Reader is a clear choice.
     *
     *
     * 12. READING PROPERTIES FROM A RESOURCE
     *
     * Configuration files are often placed inside the
     * application's classpath.
     *
     * Example project structure:
     *
     * src/
     *   main/
     *     java/
     *       Example.java
     *     resources/
     *       application.properties
     *
     * Load using the class loader:
     *
     * Properties properties = new Properties();
     *
     * try (InputStream input =
     *          Example.class.getClassLoader()
     *              .getResourceAsStream("application.properties")) {
     *
     *     if (input == null) {
     *         throw new IOException("Properties file not found");
     *     }
     *
     *     properties.load(input);
     * }
     *
     * This works when the resource is available on the classpath.
     *
     *
     * 13. STORING PROPERTIES INTO A FILE
     *
     * Properties provides store() methods.
     *
     * store(OutputStream, comments)
     *
     * Writes properties to a byte stream.
     *
     * store(Writer, comments)
     *
     * Writes properties to a character stream.
     *
     * Example:
     *
     * Properties properties = new Properties();
     *
     * properties.setProperty("username", "vinay");
     * properties.setProperty("language", "Java");
     *
     * try (FileOutputStream output =
     *          new FileOutputStream("application.properties")) {
     *
     *     properties.store(output, "Application Settings");
     * }
     *
     * The file contains the stored entries and a comment.
     *
     * IMPORTANT:
     *
     * store() writes a timestamp comment as part of the
     * generated properties file.
     *
     * The order of entries is not guaranteed.
     *
     *
     * 14. STORE() USING WRITER
     *
     * Example:
     *
     * try (Writer writer =
     *          new OutputStreamWriter(
     *              new FileOutputStream("application.properties"),
     *              StandardCharsets.UTF_8)) {
     *
     *     properties.store(writer, "UTF-8 Configuration");
     * }
     *
     * Using a Writer lets us control character encoding.
     *
     *
     * 15. LOADING AND STORING XML
     *
     * Properties also supports XML-based configuration files.
     *
     * XML methods:
     *
     * loadFromXML(InputStream)
     *
     * Loads properties from an XML document.
     *
     * storeToXML(OutputStream, comment)
     *
     * Stores properties in XML format.
     *
     * Example:
     *
     * Properties properties = new Properties();
     *
     * properties.setProperty("username", "vinay");
     * properties.setProperty("language", "Java");
     *
     * try (FileOutputStream output =
     *          new FileOutputStream("config.xml")) {
     *
     *     properties.storeToXML(output, "Application Settings");
     * }
     *
     * Loading:
     *
     * Properties loaded = new Properties();
     *
     * try (FileInputStream input =
     *          new FileInputStream("config.xml")) {
     *
     *     loaded.loadFromXML(input);
     * }
     *
     * XML properties files use UTF-8 encoding by default
     * when written by storeToXML().
     *
     * The XML format has its own required document structure.
     *
     *
     * 16. DEFAULT PROPERTIES
     *
     * Properties supports default values through another
     * Properties object.
     *
     * Example:
     *
     * Properties defaults = new Properties();
     *
     * defaults.setProperty("language", "Java");
     * defaults.setProperty("port", "8080");
     *
     * Properties properties = new Properties(defaults);
     *
     * properties.setProperty("username", "vinay");
     *
     * Now:
     *
     * properties.getProperty("username") -> vinay
     * properties.getProperty("language") -> Java
     * properties.getProperty("port") -> 8080
     *
     * Why?
     *
     * The second Properties object searches its own entries first.
     *
     * If a property is missing, it checks the defaults object.
     *
     * This is called a defaults chain.
     *
     *
     * 17. DEFAULT VALUE VS GETPROPERTY DEFAULT ARGUMENT
     *
     * Method:
     *
     * getProperty(key, defaultValue)
     *
     * Example:
     *
     * String port = properties.getProperty("port", "8080");
     *
     * This returns 8080 when no matching property is found
     * in the object or its defaults chain.
     *
     * Constructor defaults:
     *
     * Properties properties = new Properties(defaults);
     *
     * This provides a reusable fallback Properties object.
     *
     * Both approaches help avoid null values when reading
     * optional configuration.
     *
     *
     * 18. PROPERTY NAMES AND INHERITED DEFAULTS
     *
     * stringPropertyNames()
     *
     * Returns a Set of String property names.
     *
     * It includes names from the defaults chain.
     *
     * Example:
     *
     * for (String name : properties.stringPropertyNames()) {
     *
     *     System.out.println(
     *         name + " = " + properties.getProperty(name)
     *     );
     * }
     *
     * propertyNames()
     *
     * Returns an Enumeration of property names,
     * including inherited defaults.
     *
     * keys()
     *
     * Is inherited from Hashtable.
     *
     * It enumerates keys stored directly in the current object,
     * not keys inherited from the defaults chain.
     *
     * IMPORTANT:
     *
     * getProperty() searches defaults.
     * stringPropertyNames() includes defaults.
     * Ordinary Map views such as entrySet() represent mappings
     * stored directly in the current object.
     *
     *
     * 19. LISTING PROPERTIES
     *
     * Properties provides list() methods for displaying
     * property information.
     *
     * Example:
     *
     * properties.list(System.out);
     *
     * This prints the property entries.
     *
     * Another overload:
     *
     * properties.list(PrintWriter);
     *
     * IMPORTANT:
     *
     * list() is mainly useful for debugging.
     *
     * It truncates long values in its output.
     *
     * Do not use it to reliably export or persist configuration.
     *
     *
     * 20. PROPERTY VALUE CONVERSION
     *
     * Properties stores values as Strings.
     *
     * If a numeric value is required, convert it.
     *
     * Example:
     *
     * String portValue =
     *     properties.getProperty("server.port", "8080");
     *
     * int port = Integer.parseInt(portValue);
     *
     * Boolean:
     *
     * String enabledValue =
     *     properties.getProperty("feature.enabled", "false");
     *
     * boolean enabled = Boolean.parseBoolean(enabledValue);
     *
     * Double:
     *
     * String priceValue =
     *     properties.getProperty("product.price", "99.5");
     *
     * double price = Double.parseDouble(priceValue);
     *
     * IMPORTANT:
     *
     * Integer.parseInt() can throw NumberFormatException
     * if the configured value is not a valid integer.
     *
     *
     * 21. PROPERTIES VS HASHMAP
     *
     * HashMap:
     *
     * - General-purpose Map implementation.
     * - Supports arbitrary object keys and values.
     * - Allows one null key and multiple null values.
     * - Does not guarantee iteration order.
     * - Not synchronized.
     *
     * Properties:
     *
     * - Specialized configuration class.
     * - Intended for String keys and values.
     * - Does not allow null keys or values through Hashtable.
     * - Provides load() and store() methods.
     * - Supports defaults.
     * - Inherits synchronized Hashtable operations.
     *
     *
     * 22. PROPERTIES VS APPLICATION.PROPERTIES IN SPRING BOOT
     *
     * These are related but not the same thing.
     *
     * java.util.Properties:
     *
     * A Java class that stores and loads property entries.
     *
     * application.properties:
     *
     * A conventional Spring Boot configuration file.
     *
     * Spring Boot can automatically read configuration from
     * application.properties.
     *
     * Example:
     *
     * server.port=8081
     * spring.application.name=student-service
     *
     * Spring Boot can inject configuration values using:
     *
     * @Value("${server.port}")
     * private int port;
     *
     * Or bind grouped settings using:
     *
     * @ConfigurationProperties
     *
     * Spring Boot has its own configuration-loading system.
     * It is not simply the same as manually creating a
     * java.util.Properties object.
     *
     *
     * 23. SECURITY CONSIDERATIONS
     *
     * Properties files can contain sensitive configuration.
     *
     * Examples:
     *
     * - Database passwords.
     * - API keys.
     * - Service credentials.
     *
     * Avoid committing real secrets to public repositories.
     *
     * Prefer environment variables or a dedicated secrets
     * management system for sensitive production credentials.
     *
     * Also remember:
     *
     * Properties does not encrypt values automatically.
     *
     * A password stored in plain text remains readable
     * by anyone who can access the file.
     *
     *
     * 24. THREAD SAFETY
     *
     * Properties extends Hashtable.
     *
     * Therefore, inherited individual Map operations
     * are synchronized.
     *
     * However, a sequence of multiple operations is not
     * automatically atomic.
     *
     * Example:
     *
     * if (properties.getProperty("port") == null) {
     *
     *     properties.setProperty("port", "8080");
     * }
     *
     * Another thread could modify the object between
     * these two operations.
     *
     * Use appropriate synchronization when compound
     * operations must be atomic.
     *
     *
     * 25. WHEN SHOULD WE USE PROPERTIES?
     *
     * Use Properties when:
     *
     * - You need simple Java key-value configuration.
     * - You need to load or store .properties files.
     * - You need defaults for configuration entries.
     * - You need to read or write XML properties.
     *
     * For more complex configuration systems, consider
     * appropriate configuration frameworks.
     *
     *
     * 26. COMMON MISTAKES
     *
     * 1. Using put() instead of setProperty() for configuration.
     *    Prefer setProperty() and String values.
     *
     * 2. Assuming getProperty() returns Object.
     *    It returns String.
     *
     * 3. Forgetting to close file streams.
     *    Use try-with-resources.
     *
     * 4. Assuming load(InputStream) reads UTF-8 automatically.
     *    It uses the traditional ISO-8859-1 properties encoding.
     *
     * 5. Assuming properties are stored in insertion order.
     *    No such guarantee exists.
     *
     * 6. Assuming getProperty() only checks the current object.
     *    It also searches defaults.
     *
     * 7. Storing passwords in plain-text configuration files
     *    and committing them to source control.
     *
     * 8. Assuming Properties automatically converts values
     *    into integers, booleans or other types.
     *    Values must be converted explicitly.
     *
     *
     * 27. INTERVIEW QUESTIONS
     *
     * 1. What is Properties in Java?
     * 2. Which class does Properties extend?
     * 3. Why is Properties used?
     * 4. What is a .properties file?
     * 5. What is the difference between get() and getProperty()?
     * 6. What is the difference between put() and setProperty()?
     * 7. What is the difference between load() and store()?
     * 8. What is the difference between load(InputStream)
     *    and load(Reader)?
     * 9. What are default properties?
     * 10. What is stringPropertyNames()?
     * 11. What is propertyNames()?
     * 12. Does Properties allow null keys or values?
     * 13. Is Properties synchronized?
     * 14. What is loadFromXML()?
     * 15. What is storeToXML()?
     * 16. How do you read an integer from a Properties file?
     * 17. How does Spring Boot use application.properties?
     * 18. Why should passwords not be committed to
     *     configuration files?
     *
     *
     * 28. QUICK REVISION
     *
     * - Properties belongs to java.util.
     * - It extends Hashtable<Object, Object>.
     * - It is intended for String key-value configuration.
     * - setProperty() stores String properties.
     * - getProperty() retrieves String properties.
     * - getProperty() searches the defaults chain.
     * - load() reads configuration.
     * - store() writes configuration.
     * - loadFromXML() reads XML properties.
     * - storeToXML() writes XML properties.
     * - load(InputStream) uses ISO-8859-1 properties encoding.
     * - load(Reader) uses the Reader's character decoding.
     * - It supports default Properties objects.
     * - It inherits synchronized Hashtable operations.
     * - It does not guarantee iteration order.
     * - It is useful for simple Java configuration management.
     *
     * ============================================================
     *                     END OF PROPERTIES
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Properties , Happy Learning ..!");
    }
}
