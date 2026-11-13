
public class IOPerformanceAndBestPractices {

    /*
     * ================================================================
     *              I/O PERFORMANCE AND BEST PRACTICES
     * ================================================================
     *
     * 1. WHY I/O PERFORMANCE MATTERS
     *
     * I/O means transferring data between a Java application
     * and an external resource.
     *
     * Examples:
     *
     * - Reading files.
     * - Writing files.
     * - Reading configuration files.
     * - Processing logs.
     * - Uploading and downloading files.
     * - Communicating with external processes.
     *
     * I/O operations can be significantly slower than operations
     * performed entirely in memory.
     *
     * Poor I/O implementation can cause:
     *
     * - Slow application response times.
     * - High CPU usage.
     * - Excessive memory consumption.
     * - Too many open file handles.
     * - Resource leaks.
     * - Unnecessary disk operations.
     *
     *
     * 2. USE BUFFERED STREAMS
     *
     * Reading or writing one byte at a time can cause excessive
     * method calls and potentially excessive underlying I/O.
     *
     * Buffering collects data in memory and transfers it in
     * larger chunks.
     *
     * Without buffering:
     *
     * Application -> File for every small operation.
     *
     * With buffering:
     *
     * Application -> Memory buffer -> File in larger chunks.
     *
     * Example:
     *
     * // Without explicit buffering
     * try (FileInputStream input =
     *          new FileInputStream("source.txt")) {
     *
     *     int data;
     *
     *     while ((data = input.read()) != -1) {
     *         // Process one byte.
     *     }
     * }
     *
     * // With buffering
     * try (BufferedInputStream input =
     *          new BufferedInputStream(
     *              new FileInputStream("source.txt"))) {
     *
     *     int data;
     *
     *     while ((data = input.read()) != -1) {
     *         // Read through the buffer.
     *     }
     * }
     *
     * Buffered streams are especially useful when processing
     * many small reads or writes.
     *
     * Important:
     * BufferedInputStream and BufferedOutputStream are useful
     * for byte data.
     *
     * BufferedReader and BufferedWriter are useful for
     * character data.
     *
     *
     * 3. READ DATA IN CHUNKS
     *
     * Avoid reading large files one byte at a time when you
     * can process data in chunks.
     *
     * Example:
     *
     * try (InputStream input =
     *          new BufferedInputStream(
     *              new FileInputStream("source.bin"))) {
     *
     *     byte[] buffer = new byte[8192];
     *
     *     int bytesRead;
     *
     *     while ((bytesRead = input.read(buffer)) != -1) {
     *
     *         // Process only buffer[0] through
     *         // buffer[bytesRead - 1].
     *     }
     * }
     *
     * Important:
     *
     * - The buffer size is 8192 bytes in this example.
     * - It is not a universally optimal size.
     * - Choose the size based on workload and measurements.
     * - Never assume the entire buffer is filled on every read.
     *
     *
     * 4. EFFICIENT FILE COPYING
     *
     * A common I/O operation is copying data from one file
     * to another.
     *
     * Example:
     *
     * try (
     *     InputStream input =
     *         new BufferedInputStream(
     *             new FileInputStream("source.bin"));
     *
     *     OutputStream output =
     *         new BufferedOutputStream(
     *             new FileOutputStream("destination.bin"))
     * ) {
     *
     *     byte[] buffer = new byte[8192];
     *
     *     int bytesRead;
     *
     *     while ((bytesRead = input.read(buffer)) != -1) {
     *
     *         output.write(buffer, 0, bytesRead);
     *     }
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     * Why use bytesRead?
     *
     * The final read may return fewer bytes than the buffer size.
     *
     * Writing the entire buffer could copy old or unused bytes.
     *
     * Therefore:
     *
     * output.write(buffer, 0, bytesRead);
     *
     * writes only the bytes actually read.
     *
     *
     * 5. USE JAVA NIO FOR FILE OPERATIONS WHEN APPROPRIATE
     *
     * Java NIO provides APIs for file-system operations.
     *
     * Important classes:
     *
     * Path
     * Files
     * FileChannel
     * ByteBuffer
     *
     * Example using Files.copy():
     *
     * Path source = Path.of("source.txt");
     *
     * Path destination = Path.of("destination.txt");
     *
     * try {
     *
     *     Files.copy(
     *         source,
     *         destination,
     *         StandardCopyOption.REPLACE_EXISTING
     *     );
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     * Benefits:
     *
     * - Convenient file operations.
     * - Better file-system API.
     * - Support for file attributes.
     * - Directory traversal.
     * - File channels and buffers.
     *
     * Important:
     * NIO does not automatically make every I/O operation faster.
     * Select the API based on the actual requirement.
     *
     *
     * 6. AVOID UNNECESSARY FLUSH OPERATIONS
     *
     * flush() pushes buffered output toward its underlying
     * destination.
     *
     * Calling flush() after every small write can reduce
     * the benefit of buffering.
     *
     * Example:
     *
     * // Avoid unnecessary repeated flushing.
     *
     * for (int i = 0; i < 10000; i++) {
     *
     *     writer.write("Hello");
     *
     *     // writer.flush();  // Usually unnecessary here.
     * }
     *
     * Better:
     *
     * try (BufferedWriter writer =
     *          new BufferedWriter(
     *              new FileWriter("output.txt"))) {
     *
     *     for (int i = 0; i < 10000; i++) {
     *         writer.write("Hello");
     *         writer.newLine();
     *     }
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     * Closing the writer normally flushes its buffered data.
     *
     * Important:
     * flush() does not guarantee that data has reached
     * physical storage.
     *
     *
     * 7. ALWAYS CLOSE RESOURCES
     *
     * Files, streams and readers may hold operating-system
     * resources.
     *
     * Examples:
     *
     * - File handles.
     * - Socket connections.
     * - Native resources.
     *
     * If resources are not closed, the application can
     * experience resource leaks.
     *
     * Preferred approach:
     *
     * try-with-resources.
     *
     * Example:
     *
     * try (BufferedReader reader =
     *          new BufferedReader(
     *              new FileReader("input.txt"))) {
     *
     *     String line;
     *
     *     while ((line = reader.readLine()) != null) {
     *         System.out.println(line);
     *     }
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     * Benefits:
     *
     * - Automatically closes resources.
     * - Works when exceptions occur.
     * - Reduces boilerplate.
     * - Supports multiple resources.
     *
     * Resources are closed in reverse order of initialization.
     *
     *
     * 8. PREFER TRY-WITH-RESOURCES OVER MANUAL CLOSING
     *
     * Traditional approach:
     *
     * FileInputStream input = null;
     *
     * try {
     *
     *     input = new FileInputStream("data.txt");
     *
     *     // Read data.
     *
     * } catch (IOException e) {
     *
     *     e.printStackTrace();
     *
     * } finally {
     *
     *     if (input != null) {
     *
     *         try {
     *             input.close();
     *
     *         } catch (IOException e) {
     *             e.printStackTrace();
     *         }
     *     }
     * }
     *
     * Modern approach:
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
     * Prefer the modern approach for resources implementing
     * AutoCloseable.
     *
     *
     * 9. HANDLE EXCEPTIONS PROPERLY
     *
     * I/O operations can fail for many reasons:
     *
     * - File does not exist.
     * - Permission denied.
     * - Disk is full.
     * - File is unavailable.
     * - Network or device failure.
     * - Invalid file path.
     *
     * Avoid swallowing exceptions.
     *
     * Bad practice:
     *
     * try {
     *
     *     // File operation.
     *
     * } catch (IOException e) {
     *
     *     // Do nothing.
     * }
     *
     * Better:
     *
     * try {
     *
     *     // File operation.
     *
     * } catch (IOException e) {
     *
     *     System.err.println(
     *         "Unable to process the file: " + e.getMessage()
     *     );
     *
     *     throw new RuntimeException(
     *         "File processing failed",
     *         e
     *     );
     * }
     *
     * Good exception handling should:
     *
     * - Preserve the original cause.
     * - Provide meaningful context.
     * - Avoid silently ignoring failures.
     * - Avoid exposing sensitive file-system details
     *   to external users.
     *
     *
     * 10. USE THE CORRECT CHARACTER ENCODING
     *
     * Text is stored as bytes.
     *
     * A character encoding defines how characters are
     * converted into bytes and vice versa.
     *
     * Common encodings:
     *
     * UTF-8
     * UTF-16
     * ISO-8859-1
     * US-ASCII
     *
     * Avoid relying on the platform's default charset when
     * the file format requires a specific encoding.
     *
     * Example:
     *
     * try (BufferedReader reader =
     *          Files.newBufferedReader(
     *              Path.of("input.txt"),
     *              StandardCharsets.UTF_8)) {
     *
     *     String line;
     *
     *     while ((line = reader.readLine()) != null) {
     *         // Process line.
     *     }
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     * For writing:
     *
     * try (BufferedWriter writer =
     *          Files.newBufferedWriter(
     *              Path.of("output.txt"),
     *              StandardCharsets.UTF_8)) {
     *
     *     writer.write("Hello Java");
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     * Important:
     * UTF-8 is the default charset in standard Java
     * implementations since Java 18 (JEP 400).
     *
     * Explicitly specifying the charset is still recommended
     * when the data format requires a particular encoding.
     *
     *
     * 11. AVOID LOADING LARGE FILES ENTIRELY INTO MEMORY
     *
     * Consider a file containing several gigabytes of data.
     *
     * Loading the entire file into a byte array or String
     * may cause:
     *
     * - Excessive heap usage.
     * - Garbage collection pressure.
     * - OutOfMemoryError.
     *
     * Instead, process the file incrementally.
     *
     * Example:
     *
     * try (BufferedReader reader =
     *          Files.newBufferedReader(
     *              Path.of("large-file.txt"),
     *              StandardCharsets.UTF_8)) {
     *
     *     String line;
     *
     *     while ((line = reader.readLine()) != null) {
     *
     *         // Process one line at a time.
     *     }
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     * Important:
     * readAllBytes(), readString() and readAllLines()
     * are convenient for appropriately sized files.
     *
     * They are not automatically suitable for very large files.
     *
     *
     * 12. CHOOSE BETWEEN BYTE AND CHARACTER STREAMS
     *
     * Use byte streams for:
     *
     * - Images.
     * - Videos.
     * - Audio.
     * - PDFs.
     * - ZIP files.
     * - Other binary data.
     *
     * Use character streams for:
     *
     * - Text files.
     * - CSV files.
     * - Configuration files.
     * - Source code.
     * - Human-readable logs.
     *
     * Important:
     * A byte stream can also process text bytes, but it does
     * not automatically decode them into characters.
     *
     * A character stream handles character encoding and decoding.
     *
     *
     * 13. AVOID UNNECESSARY OBJECT CREATION
     *
     * Repeatedly creating temporary objects inside large
     * processing loops can increase allocation pressure.
     *
     * Example:
     *
     * // Prefer reusing a buffer when appropriate.
     *
     * byte[] buffer = new byte[8192];
     *
     * try (InputStream input =
     *          new FileInputStream("data.bin")) {
     *
     *     int bytesRead;
     *
     *     while ((bytesRead = input.read(buffer)) != -1) {
     *
     *         // Process the existing buffer.
     *     }
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     * Do not introduce complicated buffer reuse or pooling
     * without evidence that it improves performance.
     *
     *
     * 14. USE FILES METHODS FOR COMMON OPERATIONS
     *
     * Java NIO's Files class provides convenient methods.
     *
     * Read a small text file:
     *
     * String content = Files.readString(
     *     Path.of("data.txt"),
     *     StandardCharsets.UTF_8
     * );
     *
     * Write text:
     *
     * Files.writeString(
     *     Path.of("output.txt"),
     *     "Hello Java",
     *     StandardCharsets.UTF_8
     * );
     *
     * Read all lines:
     *
     * List<String> lines = Files.readAllLines(
     *     Path.of("data.txt"),
     *     StandardCharsets.UTF_8
     * );
     *
     * Copy a file:
     *
     * Files.copy(
     *     Path.of("source.txt"),
     *     Path.of("destination.txt"),
     *     StandardCopyOption.REPLACE_EXISTING
     * );
     *
     * Delete a file:
     *
     * Files.deleteIfExists(Path.of("data.txt"));
     *
     * Important:
     * readString(), writeString() and Files.readAllLines()
     * are suitable for files that fit comfortably in memory.
     *
     *
     * 15. BE CAREFUL WITH FILE PATHS
     *
     * Avoid hardcoding operating-system-specific separators.
     *
     * Bad:
     *
     * String path = "C:\\Users\\Vinay\\Documents\\data.txt";
     *
     * Better:
     *
     * Path path = Path.of(
     *     "documents",
     *     "data.txt"
     * );
     *
     * Java handles the platform-specific path separator.
     *
     * Prefer Path and Files over the older File API
     * for new file-system code.
     *
     *
     * 16. VALIDATE FILE PATHS
     *
     * Never blindly trust file paths received from users.
     *
     * A malicious user might attempt path traversal.
     *
     * Example input:
     *
     * ../../../../etc/passwd
     *
     * This attempts to escape an intended directory.
     *
     * Example validation:
     *
     * Path baseDirectory =
     *     Path.of("uploads").toAbsolutePath().normalize();
     *
     * Path requestedPath =
     *     baseDirectory.resolve(userProvidedFileName)
     *                  .normalize();
     *
     * if (!requestedPath.startsWith(baseDirectory)) {
     *
     *     throw new SecurityException(
     *         "Invalid file path"
     *     );
     * }
     *
     * Important:
     *
     * - Normalize and validate paths.
     * - Restrict file access to intended directories.
     * - Validate file names and extensions where appropriate.
     * - Do not rely only on a file extension for file validation.
     * - Consider symbolic links and race conditions.
     *
     * For security-sensitive operations, path validation alone
     * may not eliminate every filesystem attack.
     *
     *
     * 17. AVOID RACE CONDITIONS IN FILE OPERATIONS
     *
     * A race condition occurs when another process or thread
     * changes a resource between checking it and using it.
     *
     * Example of a risky pattern:
     *
     * if (!Files.exists(path)) {
     *
     *     Files.createFile(path);
     * }
     *
     * Another process may create the file after the exists()
     * check but before createFile().
     *
     * Better:
     *
     * try {
     *
     *     Files.createFile(path);
     *
     * } catch (FileAlreadyExistsException e) {
     *
     *     // Handle the existing file.
     * }
     *
     * Prefer atomic filesystem operations when available
     * instead of separate check-then-act operations.
     *
     *
     * 18. USE ATOMIC FILE REPLACEMENT WHEN NEEDED
     *
     * Sometimes an application must replace a file without
     * exposing a partially written result.
     *
     * A common strategy:
     *
     * 1. Write the new content to a temporary file.
     * 2. Close the temporary file.
     * 3. Move it over the destination.
     *
     * Example:
     *
     * Path temporary = Path.of("config.tmp");
     *
     * Path destination = Path.of("config.txt");
     *
     * try {
     *
     *     Files.writeString(
     *         temporary,
     *         "new configuration",
     *         StandardCharsets.UTF_8
     *     );
     *
     *     Files.move(
     *         temporary,
     *         destination,
     *         StandardCopyOption.REPLACE_EXISTING,
     *         StandardCopyOption.ATOMIC_MOVE
     *     );
     *
     * } catch (AtomicMoveNotSupportedException e) {
     *
     *     // Use a documented fallback if appropriate.
     *
     * } catch (IOException e) {
     *
     *     e.printStackTrace();
     * }
     *
     * Important:
     * ATOMIC_MOVE support depends on the filesystem.
     *
     * Atomic visibility of a rename is not the same as
     * guaranteed durability after a power failure.
     *
     *
     * 19. DO NOT ASSUME FILE OPERATIONS ARE ALWAYS ATOMIC
     *
     * Examples:
     *
     * - A file may disappear after it is checked.
     * - A directory may become unavailable.
     * - A disk may become full during writing.
     * - Permissions may change.
     * - Another process may modify the same file.
     *
     * Design file operations to handle these situations.
     *
     *
     * 20. UNDERSTAND BLOCKING I/O
     *
     * Traditional Java I/O operations are generally blocking.
     *
     * A thread performing a read may wait until data becomes
     * available or an operation completes.
     *
     * Example:
     *
     * int data = inputStream.read();
     *
     * The calling thread may block while waiting for data.
     *
     * For ordinary file processing, this is often acceptable.
     *
     * For applications managing many concurrent connections,
     * NIO channels, selectors or asynchronous APIs may be useful.
     *
     * Important:
     * NIO does not mean every operation is non-blocking.
     * Blocking and non-blocking behavior depends on the API
     * and channel configuration.
     *
     *
     * 21. THREAD SAFETY
     *
     * Many stream and reader/writer implementations should not
     * be assumed to support concurrent access safely.
     *
     * If multiple threads access the same resource:
     *
     * - Define ownership of the resource.
     * - Avoid unsynchronized concurrent writes.
     * - Use synchronization where required.
     * - Consider separate streams or channels.
     * - Coordinate closing and resource lifetime.
     *
     * Important:
     * Thread-safe individual operations do not necessarily
     * make a complete multi-operation workflow atomic.
     *
     *
     * 22. LOGGING BEST PRACTICES
     *
     * Logging is a common I/O operation in backend applications.
     *
     * Avoid manually writing every application log to files
     * using FileWriter.
     *
     * Prefer a logging framework such as:
     *
     * SLF4J
     * Logback
     * Log4j 2
     *
     * These provide features such as:
     *
     * - Log levels.
     * - File appenders.
     * - Rolling log files.
     * - Structured logging.
     * - Configurable output destinations.
     *
     * Avoid logging:
     *
     * - Passwords.
     * - Access tokens.
     * - Private keys.
     * - Sensitive personal data.
     *
     *
     * 23. FILE UPLOAD BEST PRACTICES
     *
     * File uploads are common in backend applications.
     *
     * Examples:
     *
     * - Resume uploads.
     * - Profile pictures.
     * - Documents.
     * - Reports.
     *
     * Best practices:
     *
     * 1. Set an upload size limit.
     * 2. Validate the file type.
     * 3. Do not trust the supplied file name.
     * 4. Generate server-side file names where appropriate.
     * 5. Prevent path traversal.
     * 6. Store files outside publicly executable directories.
     * 7. Apply access control.
     * 8. Handle duplicate names.
     * 9. Clean up temporary files.
     * 10. Avoid loading large uploads entirely into memory.
     *
     *
     * 24. FILE DOWNLOAD BEST PRACTICES
     *
     * When serving files:
     *
     * - Verify that the user is authorized.
     * - Validate the requested file identifier.
     * - Avoid exposing internal server paths.
     * - Set appropriate content type and disposition headers.
     * - Stream large files instead of loading them entirely.
     * - Handle client disconnects.
     *
     * A backend should not expose arbitrary filesystem paths
     * directly to clients.
     *
     *
     * 25. MEASURE PERFORMANCE INSTEAD OF GUESSING
     *
     * Do not assume that one buffer size or API is always faster.
     *
     * Measure:
     *
     * - File size.
     * - Read and write throughput.
     * - Memory usage.
     * - CPU usage.
     * - Allocation rate.
     * - Number of I/O operations.
     *
     * Useful tools:
     *
     * JMH:
     * Java Microbenchmark Harness.
     *
     * Java Flight Recorder:
     * Helps inspect runtime behavior and performance.
     *
     * Java Mission Control:
     * Helps analyze Java Flight Recorder recordings.
     *
     * Operating-system monitoring tools:
     * Help inspect disk and process I/O.
     *
     * Benchmark realistic workloads rather than tiny examples
     * that do not represent production behavior.
     *
     *
     * 26. COMMON I/O PERFORMANCE MISTAKES
     *
     * 1. Reading large files one byte at a time.
     *
     * 2. Writing every small piece of data directly to disk.
     *
     * 3. Flushing after every write without a reason.
     *
     * 4. Loading huge files entirely into memory.
     *
     * 5. Creating unnecessary temporary objects.
     *
     * 6. Forgetting to close resources.
     *
     * 7. Using the wrong stream type.
     *
     * 8. Relying on the default charset for a defined file format.
     *
     * 9. Hardcoding platform-specific paths.
     *
     * 10. Ignoring exceptions.
     *
     * 11. Trusting user-provided file paths.
     *
     * 12. Assuming NIO is always faster than classic I/O.
     *
     * 13. Sharing streams across threads without coordination.
     *
     * 14. Ignoring file size limits in upload functionality.
     *
     *
     * 27. JAVA I/O IN REAL BACKEND APPLICATIONS
     *
     * Java I/O is useful in backend development for:
     *
     * - Reading application configuration.
     * - Processing CSV and text files.
     * - Reading and writing reports.
     * - Handling file uploads and downloads.
     * - Processing images and documents.
     * - Creating application logs.
     * - Reading certificates and key stores.
     * - Importing and exporting data.
     *
     * In Spring Boot applications, you may encounter:
     *
     * MultipartFile:
     * Represents an uploaded file.
     *
     * InputStream:
     * Reads uploaded file content.
     *
     * OutputStream:
     * Writes file content to a destination.
     *
     * Path and Files:
     * Manage file-system operations.
     *
     * Resource:
     * Spring abstraction for accessing resources.
     *
     * ResourceLoader:
     * Loads resources from different locations.
     *
     * Important:
     * Spring provides higher-level abstractions, but
     * understanding Java I/O is still essential.
     *
     *
     * 28. FINAL BEST-PRACTICE CHECKLIST
     *
     * [ ] Choose byte streams for binary data.
     *
     * [ ] Choose character streams for text.
     *
     * [ ] Specify the required charset explicitly.
     *
     * [ ] Use buffering for workloads with many small operations.
     *
     * [ ] Process large files incrementally.
     *
     * [ ] Use try-with-resources.
     *
     * [ ] Handle IOException properly.
     *
     * [ ] Avoid unnecessary flush operations.
     *
     * [ ] Prefer Path and Files for modern file-system work.
     *
     * [ ] Validate paths received from users.
     *
     * [ ] Handle concurrent file access carefully.
     *
     * [ ] Set upload and download limits.
     *
     * [ ] Avoid logging sensitive data.
     *
     * [ ] Measure before optimizing.
     *
     *
     * 29. INTERVIEW QUESTIONS
     *
     * 1. Why is buffering important in Java I/O?
     *
     * 2. What is the difference between BufferedInputStream
     *    and FileInputStream?
     *
     * 3. Why should files be processed in chunks?
     *
     * 4. Why should try-with-resources be preferred?
     *
     * 5. What happens if a stream is not closed?
     *
     * 6. What is the difference between flush() and close()?
     *
     * 7. Does flush() guarantee that data is physically
     *    written to storage?
     *
     * 8. Why should we specify a character encoding?
     *
     * 9. Why is reading a huge file using readAllBytes()
     *    potentially dangerous?
     *
     * 10. When should we use byte streams instead of
     *     character streams?
     *
     * 11. What is path traversal?
     *
     * 12. How can file uploads be secured?
     *
     * 13. What is the difference between blocking and
     *     non-blocking I/O?
     *
     * 14. Is Java NIO always faster than Java I/O?
     *
     * 15. How do you optimize file processing?
     *
     *
     * ================================================================
     *                 END OF JAVA I/O BEST PRACTICES
     * ================================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about I/O Performance and Best Practices , Happy Learning ..!");
    }
}
