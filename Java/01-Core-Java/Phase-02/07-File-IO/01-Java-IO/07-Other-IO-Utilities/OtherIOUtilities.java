
public class OtherIOUtilities {

    /*
     * ================================================================
     *                       OTHER I/O UTILITIES
     * ================================================================
     *
     * 1. OVERVIEW
     *
     * The java.io package provides several additional classes
     * and interfaces beyond streams, readers, writers and
     * serialization.
     *
     * Important utilities include:
     *
     * - RandomAccessFile
     * - Console
     * - FileDescriptor
     * - FileFilter
     * - FilenameFilter
     * - StreamTokenizer
     * - FilePermission
     * - Closeable
     * - Flushable
     * - ObjectStreamConstants
     * - Null streams and readers
     *
     *
     * 2. RANDOMACCESSFILE
     *
     * RandomAccessFile allows us to read and write data at
     * arbitrary positions inside a file.
     *
     * Normally, streams process data sequentially.
     *
     * For example:
     *
     * FileInputStream:
     * Reads data sequentially from the current position.
     *
     * But what if we want to:
     *
     * - Read data from the middle of a file?
     * - Update a particular record?
     * - Jump to a specific byte position?
     *
     * RandomAccessFile provides this capability.
     *
     * Package:
     * java.io
     *
     * Important:
     * RandomAccessFile is NOT a subclass of InputStream
     * or OutputStream.
     *
     * It implements:
     *
     * DataInput
     * DataOutput
     * Closeable
     *
     *
     * 3. RANDOMACCESSFILE CONSTRUCTORS
     *
     * RandomAccessFile(String name, String mode)
     *
     * RandomAccessFile(File file, String mode)
     *
     * The mode determines how the file can be accessed.
     *
     * Supported modes:
     *
     * "r"  -> Open for reading only.
     *
     * "rw" -> Open for reading and writing.
     *
     * "rws" -> Open for reading and writing, with updates to
     *          file content and metadata synchronously written
     *          to the underlying storage device.
     *
     * "rwd" -> Open for reading and writing, with updates to
     *          file content synchronously written to storage.
     *          Metadata updates need not be synchronously written.
     *
     * Example:
     *
     * RandomAccessFile file =
     *     new RandomAccessFile("data.txt", "rw");
     *
     * Important:
     * - "r" requires the file to exist.
     * - "rw" creates the file if it does not exist.
     * - Opening with "rw" does not automatically truncate
     *   an existing file.
     *
     *
     * 4. IMPORTANT RANDOMACCESSFILE METHODS
     *
     * read()
     *     Reads one byte and returns it as an int, or -1 at EOF.
     *
     * read(byte[] b)
     *     Reads bytes into an array.
     *
     * write(int b)
     *     Writes one byte.
     *
     * write(byte[] b)
     *     Writes an array of bytes.
     *
     * seek(long pos)
     *     Moves the file pointer to a specified byte position.
     *
     * getFilePointer()
     *     Returns the current file pointer position.
     *
     * length()
     *     Returns the current file length in bytes.
     *
     * setLength(long newLength)
     *     Changes the file length.
     *
     * skipBytes(int n)
     *     Attempts to skip the specified number of bytes.
     *
     * getFD()
     *     Returns the associated FileDescriptor.
     *
     * getChannel()
     *     Returns the associated FileChannel.
     *
     * close()
     *     Closes the file.
     *
     *
     * 5. UNDERSTANDING THE FILE POINTER
     *
     * RandomAccessFile maintains a file pointer.
     *
     * The file pointer represents the position at which the
     * next read or write operation will occur.
     *
     * Example:
     *
     * RandomAccessFile file =
     *     new RandomAccessFile("data.txt", "rw");
     *
     * file.write("Hello".getBytes());
     *
     * long position = file.getFilePointer();
     *
     * System.out.println(position);
     *
     * After writing five ASCII bytes:
     *
     * File:
     * H e l l o
     *
     * Positions:
     * 0 1 2 3 4
     *
     * File pointer:
     * 5
     *
     * The next write will start at byte position 5.
     *
     *
     * 6. SEEK METHOD
     *
     * seek(long pos) moves the file pointer to a specified
     * byte position.
     *
     * Example:
     *
     * RandomAccessFile file =
     *     new RandomAccessFile("data.txt", "rw");
     *
     * file.seek(2);
     *
     * int data = file.read();
     *
     * System.out.println((char) data);
     *
     * If the file contains:
     *
     * Hello
     *
     * The pointer moves to position 2.
     * The next read returns 'l'.
     *
     * Important:
     * - File positions are byte-based.
     * - Seeking beyond the current file length is permitted.
     * - Reading beyond EOF returns -1.
     * - Writing beyond EOF can extend the file, leaving a
     *   zero-filled gap between the previous end and the write.
     *
     *
     * 7. MODIFYING DATA AT A PARTICULAR POSITION
     *
     * RandomAccessFile can update data without rewriting
     * the entire file.
     *
     * Example:
     *
     * try (RandomAccessFile file =
     *          new RandomAccessFile("data.txt", "rw")) {
     *
     *     file.seek(0);
     *     file.write('J');
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     * If the original file contains:
     *
     * Hello
     *
     * It becomes:
     *
     * Jello
     *
     * Important:
     * Writing overwrites bytes at the current position.
     * It does not insert bytes and shift the existing content.
     *
     *
     * 8. SETLENGTH METHOD
     *
     * setLength(long newLength) changes the file length.
     *
     * If newLength is smaller:
     * The file is truncated.
     *
     * If newLength is larger:
     * The file is extended, and the newly extended region
     * reads as zero-filled bytes.
     *
     * Example:
     *
     * try (RandomAccessFile file =
     *          new RandomAccessFile("data.txt", "rw")) {
     *
     *     file.setLength(100);
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     * The file is changed to 100 bytes.
     *
     *
     * 9. RANDOMACCESSFILE VS STREAMS
     *
     * FileInputStream:
     * - Sequential byte reading.
     * - Input only.
     *
     * FileOutputStream:
     * - Sequential byte writing.
     * - Output only.
     *
     * RandomAccessFile:
     * - Supports reading and writing depending on mode.
     * - Supports moving the file pointer.
     * - Supports access at arbitrary byte positions.
     * - Implements DataInput and DataOutput.
     *
     * RandomAccessFile is useful for applications that need
     * direct access to particular file records or positions.
     *
     *
     * 10. CONSOLE CLASS
     *
     * Console represents a character-based console device
     * associated with the current Java process, when available.
     *
     * It belongs to java.io.
     *
     * It is commonly used for:
     *
     * - Reading input from a terminal.
     * - Displaying prompts.
     * - Reading passwords without echoing them.
     *
     * Obtain a Console:
     *
     * Console console = System.console();
     *
     * Important:
     * System.console() may return null.
     *
     * This commonly happens when the application is launched
     * through an IDE or when no console is attached.
     *
     *
     * 11. IMPORTANT CONSOLE METHODS
     *
     * reader()
     *     Returns the Reader associated with the console.
     *
     * writer()
     *     Returns the PrintWriter associated with the console.
     *
     * readLine()
     *     Reads a line of input.
     *
     * readLine(String format, Object... args)
     *     Displays a formatted prompt and reads a line.
     *
     * readPassword()
     *     Reads a password without echoing characters.
     *
     * readPassword(String format, Object... args)
     *     Displays a formatted prompt and reads a password.
     *
     * printf()
     *     Displays formatted output.
     *
     * format()
     *     Displays formatted output.
     *
     * flush()
     *     Flushes the console output.
     *
     *
     * 12. READING USER INPUT USING CONSOLE
     *
     * Example:
     *
     * Console console = System.console();
     *
     * if (console != null) {
     *
     *     String name = console.readLine("Enter name: ");
     *
     *     console.printf("Welcome %s%n", name);
     *
     * } else {
     *
     *     System.out.println("No console available");
     * }
     *
     *
     * 13. READING PASSWORDS USING CONSOLE
     *
     * Console provides readPassword().
     *
     * It reads password characters without displaying them
     * on the console.
     *
     * Example:
     *
     * Console console = System.console();
     *
     * if (console != null) {
     *
     *     char[] password =
     *         console.readPassword("Enter password: ");
     *
     *     try {
     *         // Process the password.
     *
     *     } finally {
     *         java.util.Arrays.fill(password, '\0');
     *     }
     *
     * }
     *
     * Important:
     * - readPassword() returns char[].
     * - The entered password is not echoed to the console.
     * - Clear the character array when it is no longer required.
     * - This does not guarantee that no copies of the password
     *   exist elsewhere in memory.
     *
     * Prefer char[] over String for sensitive input because
     * a String cannot be explicitly cleared.
     *
     *
     * 14. CONSOLE VS SCANNER
     *
     * Console:
     * - Belongs to java.io.
     * - Designed for console-based input and output.
     * - Supports password input without echoing.
     * - May return null when no console is available.
     *
     * Scanner:
     * - Belongs to java.util.
     * - Parses input into tokens and different data types.
     * - Can read from many sources, including System.in.
     * - Does not provide password masking by itself.
     *
     * Example:
     *
     * Scanner scanner = new Scanner(System.in);
     *
     * Console console = System.console();
     *
     * Both can read user input, but they serve different needs.
     *
     *
     * 15. FILEDESCRIPTOR
     *
     * FileDescriptor represents a handle to an underlying
     * file or other I/O resource.
     *
     * It belongs to java.io.
     *
     * It is commonly associated with operating-system-level
     * file descriptors or handles.
     *
     * Java provides three standard descriptors:
     *
     * FileDescriptor.in
     *     Standard input.
     *
     * FileDescriptor.out
     *     Standard output.
     *
     * FileDescriptor.err
     *     Standard error.
     *
     * These correspond conceptually to:
     *
     * System.in
     * System.out
     * System.err
     *
     *
     * 16. IMPORTANT FILEDESCRIPTOR METHODS
     *
     * valid()
     *     Checks whether the descriptor is valid.
     *
     * sync()
     *     Forces modified data for the descriptor to be
     *     synchronized with the underlying storage device,
     *     where supported.
     *
     * Example:
     *
     * try (FileOutputStream output =
     *          new FileOutputStream("data.txt")) {
     *
     *     output.write("Hello".getBytes());
     *
     *     FileDescriptor descriptor = output.getFD();
     *
     *     descriptor.sync();
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     * Important:
     * FileDescriptor.sync() can throw SyncFailedException.
     *
     * It is not a general-purpose replacement for flush().
     *
     * flush() moves buffered data toward the underlying stream.
     *
     * sync() requests synchronization of modified data with
     * the underlying storage device.
     *
     *
     * 17. FILEFILTER INTERFACE
     *
     * FileFilter belongs to java.io.
     *
     * It is a functional interface.
     *
     * It contains:
     *
     * boolean accept(File pathname)
     *
     * It is used to decide whether a File should be accepted.
     *
     * Example:
     *
     * File directory = new File("documents");
     *
     * File[] files = directory.listFiles(
     *     (File file) -> file.isFile()
     * );
     *
     * This selects regular files from the directory.
     *
     * Common use cases:
     * - Selecting particular file types.
     * - Filtering directories.
     * - Finding files based on conditions.
     *
     *
     * 18. FILENAMEFILTER INTERFACE
     *
     * FilenameFilter also belongs to java.io.
     *
     * It is a functional interface.
     *
     * It contains:
     *
     * boolean accept(File dir, String name)
     *
     * It receives:
     *
     * dir:
     * Parent directory.
     *
     * name:
     * Name of the directory entry.
     *
     * Example:
     *
     * File directory = new File("documents");
     *
     * String[] names = directory.list(
     *     (dir, name) -> name.endsWith(".txt")
     * );
     *
     * This selects entries whose names end with ".txt".
     *
     * Difference:
     *
     * FileFilter:
     * Receives a File object.
     *
     * FilenameFilter:
     * Receives the parent File and entry name.
     *
     *
     * 19. FILEFILTER VS FILENAMEFILTER
     *
     * FileFilter:
     *
     * boolean accept(File pathname)
     *
     * Used by:
     * - File.listFiles(FileFilter)
     *
     * FilenameFilter:
     *
     * boolean accept(File dir, String name)
     *
     * Used by:
     * - File.list(FilenameFilter)
     * - File.listFiles(FilenameFilter)
     *
     * Both are functional interfaces and support lambda expressions.
     *
     *
     * 20. STREAMTOKENIZER
     *
     * StreamTokenizer belongs to java.io.
     *
     * It reads an input stream and breaks the input into tokens.
     *
     * Tokens may include:
     *
     * - Words.
     * - Numbers.
     * - Quoted strings.
     * - Special characters.
     *
     * It can be useful for simple text parsing.
     *
     * Example:
     *
     * String text = "Vinay 100 Java";
     *
     * try (StreamTokenizer tokenizer =
     *          new StreamTokenizer(
     *              new StringReader(text))) {
     *
     *     while (tokenizer.nextToken()
     *             != StreamTokenizer.TT_EOF) {
     *
     *         if (tokenizer.ttype ==
     *                 StreamTokenizer.TT_WORD) {
     *
     *             System.out.println(tokenizer.sval);
     *
     *         } else if (tokenizer.ttype ==
     *                    StreamTokenizer.TT_NUMBER) {
     *
     *             System.out.println(tokenizer.nval);
     *         }
     *     }
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     *
     * 21. IMPORTANT STREAMTOKENIZER MEMBERS
     *
     * nextToken()
     *     Reads the next token.
     *
     * ttype
     *     Identifies the type of the current token.
     *
     * sval
     *     Contains the current token as a String when it is
     *     a word or quoted string.
     *
     * nval
     *     Contains the current numeric token value.
     *
     * TT_EOF
     *     Represents the end of input.
     *
     * TT_WORD
     *     Represents a word token.
     *
     * TT_NUMBER
     *     Represents a numeric token.
     *
     * quoteChar
     *     Identifies the quote character for a quoted token.
     *
     * StreamTokenizer also provides configuration methods:
     *
     * wordChars()
     * whitespaceChars()
     * ordinaryChars()
     * ordinaryChar()
     * commentChar()
     * quoteChar()
     * parseNumbers()
     *
     * Important:
     * StreamTokenizer is a relatively old tokenization utility.
     * Modern applications often use dedicated parsers for
     * complex formats.
     *
     *
     * 22. CLOSEABLE INTERFACE
     *
     * Closeable belongs to java.io.
     *
     * It extends AutoCloseable.
     *
     * It declares:
     *
     * void close() throws IOException;
     *
     * Classes implementing Closeable represent resources
     * that should be closed when no longer required.
     *
     * Examples:
     *
     * InputStream
     * OutputStream
     * Reader
     * Writer
     * RandomAccessFile
     *
     * Closeable resources can be used with try-with-resources.
     *
     * Example:
     *
     * try (FileInputStream input =
     *          new FileInputStream("data.txt")) {
     *
     *     // Read data.
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     *
     * 23. FLUSHABLE INTERFACE
     *
     * Flushable belongs to java.io.
     *
     * It declares:
     *
     * void flush() throws IOException;
     *
     * It represents an output destination that can flush
     * buffered data.
     *
     * Examples:
     *
     * OutputStream
     * Writer
     *
     * Important:
     * Not every output operation requires an explicit flush.
     * Closing a buffered output resource normally flushes it.
     *
     *
     * 24. OBJECTSTREAMCONSTANTS
     *
     * ObjectStreamConstants belongs to java.io.
     *
     * It defines constants used by the Java object
     * serialization protocol.
     *
     * Examples include constants related to:
     *
     * - Serialization stream magic number.
     * - Serialization stream version.
     * - Object type codes.
     * - Serialization flags.
     *
     * These constants are primarily useful for understanding
     * or implementing low-level serialization-related behavior.
     *
     * Ordinary application developers generally use
     * ObjectInputStream and ObjectOutputStream instead.
     *
     *
     * 25. FILEPERMISSION
     *
     * FilePermission belongs to java.io.
     *
     * It represents access permissions for files and directories
     * within Java's permission framework.
     *
     * It supports actions such as:
     *
     * read
     * write
     * execute
     * delete
     * readlink
     *
     * Example:
     *
     * FilePermission permission =
     *     new FilePermission(
     *         "/home/vinay/data.txt",
     *         "read"
     *     );
     *
     * Important:
     * FilePermission is part of Java's legacy security
     * permission model.
     *
     * The Security Manager has been deprecated for removal
     * since Java 17 and permanently disabled in modern Java
     * releases.
     *
     * Do not treat FilePermission as a modern application-level
     * file-access control mechanism.
     *
     *
     * 26. NULL INPUT AND OUTPUT STREAMS
     *
     * Java 11 introduced convenient methods that create
     * streams which discard or provide no data.
     *
     * InputStream.nullInputStream()
     *
     * Creates an InputStream that contains no bytes.
     *
     * Its read() method immediately returns -1.
     *
     * OutputStream.nullOutputStream()
     *
     * Creates an OutputStream that discards all written bytes.
     *
     * Reader.nullReader()
     *
     * Creates a Reader that contains no characters.
     *
     * Writer.nullWriter()
     *
     * Creates a Writer that discards all written characters.
     *
     * Example:
     *
     * try (InputStream input =
     *          InputStream.nullInputStream()) {
     *
     *     System.out.println(input.read());
     *     // -1
     * }
     *
     * try (OutputStream output =
     *          OutputStream.nullOutputStream()) {
     *
     *     output.write(65);
     *     // Byte is discarded.
     * }
     *
     * Use cases:
     * - Testing.
     * - Discarding unnecessary output.
     * - Providing empty input to APIs.
     *
     *
     * 27. IMPORTANT I/O EXCEPTION CLASSES
     *
     * Java provides several specialized I/O exceptions.
     *
     * IOException:
     * General checked I/O exception.
     *
     * FileNotFoundException:
     * File could not be opened.
     *
     * EOFException:
     * Unexpected end of input for certain operations.
     *
     * UTFDataFormatException:
     * Invalid modified UTF-8 data.
     *
     * CharConversionException:
     * Character conversion failure.
     *
     * SyncFailedException:
     * Synchronization of a file buffer with the storage device
     * failed.
     *
     * InterruptedIOException:
     * An I/O operation was interrupted.
     *
     * StreamCorruptedException:
     * Serialization stream data is invalid or corrupted.
     *
     * ObjectStreamException:
     * Base class for serialization-related exceptions.
     *
     *
     * 28. IMPORTANT VERSION NOTES
     *
     * Java 1.0:
     * Original java.io API, including File, streams and
     * RandomAccessFile.
     *
     * Java 1.1:
     * Reader and Writer character-stream APIs introduced.
     *
     * Java 7:
     * try-with-resources introduced.
     *
     * Java 9:
     * Improved try-with-resources syntax allows effectively
     * final existing resources.
     *
     * Java 11:
     * FileReader and FileWriter gained Charset constructors.
     * Null input/output streams and readers/writers introduced.
     *
     * Java 17:
     * Security Manager deprecated for removal.
     *
     * Modern Java:
     * NIO.2 is generally preferred for richer file-system
     * operations.
     *
     *
     * 29. COMMON MISTAKES
     *
     * 1. Assuming RandomAccessFile extends InputStream.
     *
     * 2. Assuming seek() positions are character positions.
     *    They are byte positions.
     *
     * 3. Assuming opening RandomAccessFile in "rw" mode
     *    automatically truncates an existing file.
     *
     * 4. Forgetting that seek() beyond EOF does not immediately
     *    extend the file.
     *
     * 5. Assuming System.console() always returns a Console.
     *
     * 6. Using readPassword() but retaining the password array
     *    longer than necessary.
     *
     * 7. Confusing FileFilter with FilenameFilter.
     *
     * 8. Assuming FileDescriptor.sync() is equivalent to flush().
     *
     * 9. Treating FilePermission as modern operating-system
     *    permission management.
     *
     * 10. Assuming StreamTokenizer is a full-featured parser
     *     for modern data formats.
     *
     *
     * 30. INTERVIEW QUESTIONS
     *
     * 1. What is RandomAccessFile?
     * 2. Does RandomAccessFile extend InputStream?
     * 3. What are the four RandomAccessFile modes?
     * 4. What is the file pointer?
     * 5. What does seek() do?
     * 6. What is the difference between setLength() and length()?
     * 7. What is the difference between RandomAccessFile
     *    and FileInputStream?
     * 8. What is the Console class?
     * 9. Why can System.console() return null?
     * 10. What is readPassword()?
     * 11. What is FileDescriptor?
     * 12. What are FileDescriptor.in, out and err?
     * 13. What does FileDescriptor.sync() do?
     * 14. What is FileFilter?
     * 15. What is FilenameFilter?
     * 16. What is the difference between FileFilter
     *     and FilenameFilter?
     * 17. What is StreamTokenizer?
     * 18. What is Closeable?
     * 19. What is Flushable?
     * 20. What are null input and output streams?
     *
     *
     * 31. QUICK REVISION
     *
     * RandomAccessFile:
     * Reads and writes data at arbitrary file positions.
     *
     * seek():
     * Moves the file pointer.
     *
     * getFilePointer():
     * Returns the current file pointer position.
     *
     * setLength():
     * Changes the file length.
     *
     * Console:
     * Provides console-based input and output.
     *
     * readPassword():
     * Reads password characters without echoing them.
     *
     * FileDescriptor:
     * Represents an underlying I/O resource handle.
     *
     * sync():
     * Requests synchronization of modified data with storage.
     *
     * FileFilter:
     * Filters File objects.
     *
     * FilenameFilter:
     * Filters directory entry names.
     *
     * StreamTokenizer:
     * Breaks input into tokens.
     *
     * Closeable:
     * Represents resources that can be closed.
     *
     * Flushable:
     * Represents output that can be flushed.
     *
     * FilePermission:
     * Legacy Java permission framework class.
     *
     * Null streams:
     * Provide empty input or discard output.
     *
     * ================================================================
     *                    END OF OTHER I/O UTILITIES
     * ================================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Other I/O Utilities , Happy Learning ..!");
    }
}
