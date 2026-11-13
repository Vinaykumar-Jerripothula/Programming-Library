
public class FileClassAndFileOperations {

    /*
     * ================================================================
     *                 FILE CLASS AND FILE OPERATIONS
     * ================================================================
     *
     * 1. WHAT IS THE FILE CLASS?
     *
     * The File class belongs to the java.io package.
     *
     * It represents a file-system path, which may refer to:
     *
     * - An existing file.
     * - An existing directory.
     * - A file that does not yet exist.
     * - A directory that does not yet exist.
     *
     * Package:
     * java.io
     *
     * Class:
     * java.io.File
     *
     * Important:
     * Creating a File object does NOT create an actual file or
     * directory on the storage device.
     *
     * It only creates a Java object representing a pathname.
     *
     *
     * 2. WHY DO WE NEED THE FILE CLASS?
     *
     * Consider that we want to create a file named student.txt.
     *
     * Before performing operations, we need to identify the
     * location of that file.
     *
     * The File class provides methods to:
     *
     * - Represent file and directory paths.
     * - Check whether a file exists.
     * - Create files.
     * - Create directories.
     * - Delete files and empty directories.
     * - Rename or move files.
     * - Retrieve file information.
     * - List directory contents.
     * - Check file permissions.
     *
     * Important:
     * File primarily represents a pathname and provides basic
     * file-system operations. It does not read or write file
     * contents. Streams are used for that purpose.
     *
     *
     * 3. CREATING A FILE OBJECT
     *
     * The File class provides multiple constructors.
     *
     * A. File(String pathname)
     *
     * File file = new File("student.txt");
     *
     * Represents a file using a pathname.
     *
     *
     * B. File(String parent, String child)
     *
     * File file = new File("documents", "student.txt");
     *
     * Combines a parent directory and a child pathname.
     *
     *
     * C. File(File parent, String child)
     *
     * File directory = new File("documents");
     * File file = new File(directory, "student.txt");
     *
     * Uses an existing File object as the parent pathname.
     *
     *
     * D. File(URI uri)
     *
     * File file = new File(
     *     java.net.URI.create("file:///tmp/student.txt")
     * );
     *
     * Creates a File object from a file URI.
     *
     * The URI must represent a valid file URI.
     *
     *
     * 4. ABSOLUTE AND RELATIVE PATHS
     *
     * A path identifies the location of a file or directory.
     *
     * There are two important types of paths.
     *
     * A. ABSOLUTE PATH
     *
     * An absolute path specifies the complete location of a file
     * from the root of the file system.
     *
     * Linux example:
     *
     * /home/vinay/documents/student.txt
     *
     * Windows example:
     *
     * C:\Users\Vinay\Documents\student.txt
     *
     * Example:
     *
     * File file = new File("/home/vinay/student.txt");
     *
     *
     * B. RELATIVE PATH
     *
     * A relative path specifies a location relative to the
     * application's current working directory.
     *
     * Example:
     *
     * File file = new File("student.txt");
     *
     * The actual location depends on the process's working directory.
     *
     * Another example:
     *
     * File file = new File("documents/student.txt");
     *
     * This represents a file inside the documents directory
     * relative to the working directory.
     *
     *
     * 5. CURRENT WORKING DIRECTORY
     *
     * The current working directory is the directory from which
     * the Java process is running.
     *
     * Java provides:
     *
     * System.getProperty("user.dir")
     *
     * Example:
     *
     * String currentDirectory =
     *     System.getProperty("user.dir");
     *
     * System.out.println(currentDirectory);
     *
     * If the working directory is:
     *
     * /home/vinay/Java
     *
     * Then:
     *
     * new File("student.txt")
     *
     * refers to:
     *
     * /home/vinay/Java/student.txt
     *
     * Important:
     * The working directory is not necessarily the directory
     * containing the Java source file.
     *
     *
     * 6. IMPORTANT PATH METHODS
     *
     * getName()
     *     Returns the final name component of the pathname.
     *
     * getPath()
     *     Returns the pathname used to create the File object,
     *     in its normalized platform representation.
     *
     * getAbsolutePath()
     *     Returns an absolute pathname.
     *
     * getAbsoluteFile()
     *     Returns a File object representing the absolute pathname.
     *
     * getParent()
     *     Returns the parent pathname as a String, or null if
     *     there is no parent component.
     *
     * getParentFile()
     *     Returns the parent as a File object, or null.
     *
     * toString()
     *     Returns the pathname representation.
     *
     * toURI()
     *     Converts the pathname into a file URI.
     *
     * Example:
     *
     * File file = new File("documents/student.txt");
     *
     * System.out.println(file.getName());
     * // student.txt
     *
     * System.out.println(file.getPath());
     * // documents/student.txt
     *
     * System.out.println(file.getAbsolutePath());
     * // Absolute path based on the working directory.
     *
     * System.out.println(file.getParent());
     * // documents
     *
     * Note:
     * getPath() does not necessarily return an absolute path.
     *
     *
     * 7. NORMALIZED AND CANONICAL PATHS
     *
     * A. ABSOLUTE PATH
     *
     * Converts a relative pathname into an absolute pathname.
     *
     * B. CANONICAL PATH
     *
     * getCanonicalPath() returns the canonical pathname.
     *
     * It generally resolves:
     * - Relative path components.
     * - "." components.
     * - ".." components.
     * - Symbolic links where supported by the file system.
     *
     * Example:
     *
     * File file = new File("documents/../student.txt");
     *
     * System.out.println(file.getAbsolutePath());
     *
     * try {
     *     System.out.println(file.getCanonicalPath());
     * } catch (java.io.IOException e) {
     *     e.printStackTrace();
     * }
     *
     * Important:
     * - getCanonicalPath() can throw IOException.
     * - Canonicalization does not guarantee that the target file
     *   exists.
     * - Canonical paths depend on the file system.
     *
     *
     * 8. CHECKING WHETHER A FILE EXISTS
     *
     * exists()
     *
     * Checks whether the represented file or directory exists.
     *
     * Example:
     *
     * File file = new File("student.txt");
     *
     * if (file.exists()) {
     *     System.out.println("File exists");
     * } else {
     *     System.out.println("File does not exist");
     * }
     *
     * Important:
     * exists() does not tell us whether the pathname represents
     * a regular file or a directory.
     *
     *
     * 9. CHECKING FILE TYPE
     *
     * isFile()
     *
     * Checks whether the pathname represents an existing
     * regular file.
     *
     * isDirectory()
     *
     * Checks whether the pathname represents an existing directory.
     *
     * Example:
     *
     * File file = new File("student.txt");
     *
     * if (file.isFile()) {
     *     System.out.println("It is a file");
     * }
     *
     * if (file.isDirectory()) {
     *     System.out.println("It is a directory");
     * }
     *
     * Important:
     * These methods generally follow symbolic links.
     *
     * A symbolic link to a regular file can therefore return true
     * for isFile().
     *
     *
     * 10. CREATING A FILE
     *
     * createNewFile()
     *
     * Creates a new empty file if the pathname does not already
     * exist and the operation succeeds.
     *
     * Returns:
     * true  -> File was created.
     * false -> File already existed.
     *
     * Throws:
     * IOException if an I/O error occurs.
     *
     * Example:
     *
     * File file = new File("student.txt");
     *
     * try {
     *     if (file.createNewFile()) {
     *         System.out.println("File created");
     *     } else {
     *         System.out.println("File already exists");
     *     }
     * } catch (java.io.IOException e) {
     *     e.printStackTrace();
     * }
     *
     * Important:
     * - The parent directory must already exist.
     * - The application must have appropriate permissions.
     * - createNewFile() is an atomic create-if-absent operation
     *   with respect to the file-system operation.
     * - It does not overwrite an existing file.
     *
     *
     * 11. CREATING DIRECTORIES
     *
     * Java provides two important methods.
     *
     * A. mkdir()
     *
     * Creates a single directory.
     *
     * Example:
     *
     * File directory = new File("documents");
     *
     * boolean created = directory.mkdir();
     *
     * If the parent directory does not exist, mkdir() fails.
     *
     *
     * B. mkdirs()
     *
     * Creates the directory and all missing parent directories.
     *
     * Example:
     *
     * File directory =
     *     new File("company/departments/development");
     *
     * boolean created = directory.mkdirs();
     *
     * This attempts to create:
     *
     * company/
     *     departments/
     *         development/
     *
     * Difference:
     *
     * mkdir()  -> Creates one directory.
     * mkdirs() -> Creates missing parent directories too.
     *
     * Important:
     * Both methods return boolean values.
     * A false result does not necessarily mean that an error
     * occurred; the directory might already exist.
     *
     *
     * 12. DELETING FILES AND DIRECTORIES
     *
     * delete()
     *
     * Deletes the represented file or an empty directory.
     *
     * Example:
     *
     * File file = new File("student.txt");
     *
     * if (file.delete()) {
     *     System.out.println("Deleted successfully");
     * } else {
     *     System.out.println("Deletion failed");
     * }
     *
     * Important:
     * - delete() returns boolean.
     * - A non-empty directory cannot be deleted using delete().
     * - Deletion can fail because of permissions or file-system
     *   restrictions.
     * - Deleting a file is not the same as securely erasing
     *   its physical storage contents.
     *
     * deleteOnExit()
     *
     * Requests that the file or empty directory be deleted when
     * the JVM terminates normally.
     *
     * Example:
     *
     * File temp = new File("temporary.txt");
     * temp.deleteOnExit();
     *
     * Important:
     * - Deletion happens only on normal JVM termination.
     * - It is not guaranteed if the process terminates abnormally.
     * - Registered files are deleted in reverse registration order.
     * - Avoid registering huge numbers of files.
     *
     *
     * 13. RENAMING AND MOVING FILES
     *
     * renameTo(File destination)
     *
     * Attempts to rename the represented file or directory.
     *
     * It can also be used to move a file to another location.
     *
     * Example:
     *
     * File oldFile = new File("old.txt");
     * File newFile = new File("new.txt");
     *
     * boolean success = oldFile.renameTo(newFile);
     *
     * if (success) {
     *     System.out.println("Renamed successfully");
     * }
     *
     * Important:
     * - renameTo() returns boolean.
     * - Its behavior is platform-dependent.
     * - It may fail across different file systems.
     * - It does not provide the same clearly specified atomic
     *   move options as NIO's Files.move().
     *
     * Modern alternative:
     *
     * java.nio.file.Files.move()
     *
     * We will study this in the Java NIO section.
     *
     *
     * 14. RETRIEVING FILE INFORMATION
     *
     * length()
     *
     * Returns the length of a regular file in bytes.
     *
     * Example:
     *
     * File file = new File("student.txt");
     *
     * System.out.println(file.length());
     *
     * Important:
     * - Returns 0 if the file does not exist.
     * - Returns 0 for a directory.
     * - A zero-length result does not prove that a file exists.
     *
     *
     * lastModified()
     *
     * Returns the last modification time in milliseconds
     * since the Unix epoch.
     *
     * Example:
     *
     * File file = new File("student.txt");
     *
     * long modifiedTime = file.lastModified();
     *
     * If the file does not exist or an I/O error occurs,
     * the result is 0.
     *
     *
     * 15. CHECKING FILE PERMISSIONS
     *
     * canRead()
     *
     * Checks whether the application can read the file.
     *
     * canWrite()
     *
     * Checks whether the application can modify the file.
     *
     * canExecute()
     *
     * Checks whether the application can execute the file.
     *
     * Example:
     *
     * File file = new File("script.sh");
     *
     * System.out.println(file.canRead());
     * System.out.println(file.canWrite());
     * System.out.println(file.canExecute());
     *
     * Important:
     * These methods report whether access appears to be permitted.
     * Permissions and actual access can change between the check
     * and the operation.
     *
     * Do not use a permission check as a guarantee that a later
     * operation will succeed.
     *
     *
     * 16. MODIFYING FILE PERMISSIONS
     *
     * File provides basic permission-changing methods.
     *
     * setReadable(boolean readable)
     *
     * setWritable(boolean writable)
     *
     * setExecutable(boolean executable)
     *
     * setReadOnly()
     *
     * Example:
     *
     * File file = new File("student.txt");
     *
     * file.setReadable(true);
     * file.setWritable(false);
     * file.setExecutable(false);
     *
     * setReadOnly() attempts to make the file read-only.
     *
     * Important:
     * - These methods return boolean.
     * - Their behavior depends on the operating system.
     * - They do not provide complete POSIX permission management.
     *
     *
     * 17. LISTING DIRECTORY CONTENTS
     *
     * list()
     *
     * Returns the names of files and directories inside a directory
     * as a String array.
     *
     * Example:
     *
     * File directory = new File("documents");
     *
     * String[] names = directory.list();
     *
     * if (names != null) {
     *     for (String name : names) {
     *         System.out.println(name);
     *     }
     * }
     *
     *
     * listFiles()
     *
     * Returns the contents as an array of File objects.
     *
     * Example:
     *
     * File[] files = directory.listFiles();
     *
     * if (files != null) {
     *     for (File file : files) {
     *         System.out.println(file.getName());
     *     }
     * }
     *
     * Important:
     * - These methods return null if the pathname is not a directory
     *   or an I/O error occurs.
     * - They do not recursively list nested directories.
     * - Directory listing order is not guaranteed.
     *
     *
     * 18. FILTERING DIRECTORY CONTENTS
     *
     * File provides methods that accept filters.
     *
     * list(FilenameFilter filter)
     *
     * listFiles(FilenameFilter filter)
     *
     * listFiles(FileFilter filter)
     *
     * Example:
     *
     * File directory = new File("documents");
     *
     * File[] textFiles = directory.listFiles(
     *     (dir, name) -> name.endsWith(".txt")
     * );
     *
     * if (textFiles != null) {
     *     for (File file : textFiles) {
     *         System.out.println(file.getName());
     *     }
     * }
     *
     * FilenameFilter:
     * Receives the parent directory and the entry name.
     *
     * FileFilter:
     * Receives a File object representing the entry.
     *
     * These interfaces are useful for selecting particular
     * files from a directory.
     *
     *
     * 19. FILE SEPARATOR AND PATH SEPARATOR
     *
     * Different operating systems use different conventions.
     *
     * File separator:
     *
     * Linux:
     * /
     *
     * Windows:
     * \
     *
     * Java provides:
     *
     * File.separator
     *
     * File.separatorChar
     *
     * Path separator:
     *
     * Used to separate multiple path entries in system properties
     * such as the classpath.
     *
     * Linux:
     * :
     *
     * Windows:
     * ;
     *
     * Java provides:
     *
     * File.pathSeparator
     *
     * File.pathSeparatorChar
     *
     * Example:
     *
     * String path = "documents"
     *     + File.separator
     *     + "student.txt";
     *
     * Important:
     * Prefer Path and Paths from NIO when constructing and
     * manipulating paths in modern Java applications.
     *
     *
     * 20. TEMPORARY FILES
     *
     * File provides a static createTempFile() method.
     *
     * Example:
     *
     * try {
     *     File temp = File.createTempFile(
     *         "student-",
     *         ".tmp"
     *     );
     *
     *     System.out.println(temp.getAbsolutePath());
     *
     *     temp.deleteOnExit();
     *
     * } catch (java.io.IOException e) {
     *     e.printStackTrace();
     * }
     *
     * The method creates a temporary file with a generated name.
     *
     * Parameters:
     * - Prefix: Beginning of the temporary filename.
     * - Suffix: Ending of the temporary filename.
     *
     * Important:
     * - The prefix must contain at least three characters.
     * - The suffix can be null.
     * - The file is created in the default temporary directory
     *   unless a directory is supplied.
     *
     * System.getProperty("java.io.tmpdir")
     * returns the default temporary directory.
     *
     *
     * 21. FILE CLASS AND INPUT/OUTPUT STREAMS
     *
     * This is an important distinction.
     *
     * File:
     * Represents a file-system pathname and provides basic
     * file-system operations.
     *
     * FileInputStream:
     * Reads bytes from a file.
     *
     * FileOutputStream:
     * Writes bytes to a file.
     *
     * FileReader:
     * Reads characters from a file.
     *
     * FileWriter:
     * Writes characters to a file.
     *
     * Example:
     *
     * File file = new File("student.txt");
     *
     * try (java.io.FileInputStream input =
     *          new java.io.FileInputStream(file)) {
     *
     *     int data;
     *
     *     while ((data = input.read()) != -1) {
     *         System.out.print((char) data);
     *     }
     *
     * } catch (java.io.IOException e) {
     *     e.printStackTrace();
     * }
     *
     * Here:
     * - File identifies the pathname.
     * - FileInputStream performs the actual reading.
     *
     *
     * 22. FILE CLASS VS PATH INTERFACE
     *
     * File:
     * - Belongs to java.io.
     * - Represents a file-system pathname.
     * - Provides basic file and directory operations.
     * - Has a relatively limited API.
     *
     * Path:
     * - Belongs to java.nio.file.
     * - Represents a file-system path.
     * - Provides richer path manipulation capabilities.
     * - Works with the Files utility class.
     *
     * Example:
     *
     * File file = new File("student.txt");
     *
     * java.nio.file.Path path = file.toPath();
     *
     * File fileAgain = path.toFile();
     *
     * These conversion methods allow interoperability between
     * the traditional File API and NIO.
     *
     * Important:
     * File and Path represent path information.
     * Neither one should be confused with the file's contents.
     *
     *
     * 23. LIMITATIONS OF THE FILE CLASS
     *
     * Although useful, the File class has limitations:
     *
     * - Limited file attribute support.
     * - Basic permission management.
     * - Limited symbolic link handling.
     * - Boolean return values often provide little error detail.
     * - renameTo() behavior can be platform-dependent.
     * - No convenient built-in recursive directory traversal.
     * - Does not provide modern asynchronous file operations.
     *
     * Modern Java provides the NIO.2 API to address many of these
     * limitations.
     *
     * Important NIO classes:
     *
     * Path
     * Files
     * FileVisitor
     * BasicFileAttributes
     *
     *
     * 24. IMPORTANT FILE CLASS METHODS - QUICK REFERENCE
     *
     * PATH INFORMATION:
     *
     * getName()
     * getPath()
     * getAbsolutePath()
     * getAbsoluteFile()
     * getCanonicalPath()
     * getParent()
     * getParentFile()
     * toURI()
     * toPath()
     *
     * EXISTENCE AND TYPE:
     *
     * exists()
     * isFile()
     * isDirectory()
     * isHidden()
     *
     * CREATION:
     *
     * createNewFile()
     * mkdir()
     * mkdirs()
     * createTempFile()
     *
     * DELETION:
     *
     * delete()
     * deleteOnExit()
     *
     * RENAME:
     *
     * renameTo()
     *
     * INFORMATION:
     *
     * length()
     * lastModified()
     *
     * PERMISSIONS:
     *
     * canRead()
     * canWrite()
     * canExecute()
     * setReadable()
     * setWritable()
     * setExecutable()
     * setReadOnly()
     *
     * DIRECTORY LISTING:
     *
     * list()
     * listFiles()
     *
     *
     * 25. COMMON MISTAKES
     *
     * 1. Assuming new File("student.txt") creates a physical file.
     *
     * 2. Assuming getPath() always returns an absolute path.
     *
     * 3. Assuming createNewFile() creates missing parent directories.
     *
     * 4. Using mkdir() when multiple parent directories must be
     *    created.
     *
     * 5. Trying to delete a non-empty directory using delete().
     *
     * 6. Assuming renameTo() always succeeds.
     *
     * 7. Assuming length() returning 0 proves that a file exists.
     *
     * 8. Forgetting that list() and listFiles() can return null.
     *
     * 9. Assuming listFiles() automatically traverses subdirectories.
     *
     * 10. Assuming a permission check guarantees that a later
     *     file operation will succeed.
     *
     * 11. Confusing File with FileInputStream or FileOutputStream.
     *
     *
     * 26. INTERVIEW QUESTIONS
     *
     * 1. What is the File class in Java?
     * 2. Does creating a File object create a physical file?
     * 3. What is the difference between absolute and relative paths?
     * 4. What is the difference between getPath() and
     *    getAbsolutePath()?
     * 5. What is the difference between getAbsolutePath() and
     *    getCanonicalPath()?
     * 6. What is the difference between exists(), isFile() and
     *    isDirectory()?
     * 7. What is the difference between createNewFile() and mkdir()?
     * 8. What is the difference between mkdir() and mkdirs()?
     * 9. Can File.delete() delete a non-empty directory?
     * 10. What is the difference between delete() and deleteOnExit()?
     * 11. What is renameTo() and why can it fail?
     * 12. What is the difference between list() and listFiles()?
     * 13. What is FilenameFilter?
     * 14. What is the difference between File and Path?
     * 15. Does the File class provide methods to read file contents?
     *
     *
     * 27. FINAL REVISION
     *
     * - File belongs to java.io.
     * - File represents a pathname, not the actual file contents.
     * - Creating a File object does not create a physical file.
     * - createNewFile() creates a new empty file.
     * - mkdir() creates one directory.
     * - mkdirs() creates missing parent directories too.
     * - delete() deletes files and empty directories.
     * - renameTo() attempts to rename or move a file.
     * - list() returns entry names.
     * - listFiles() returns File objects.
     * - getCanonicalPath() can throw IOException.
     * - File.toPath() converts File into Path.
     * - Path and Files provide modern alternatives through NIO.2.
     *
     * ================================================================
     *              END OF FILE CLASS AND FILE OPERATIONS
     * ================================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about File Class and File Operations , Happy Learning ..!");
    }
}
