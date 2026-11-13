
public class FileIOFundamentals {

    /*
     * ================================================================
     *                    JAVA I/O FUNDAMENTALS
     * ================================================================
     *
     * 1. WHAT IS JAVA I/O?
     *
     * I/O stands for Input and Output.
     *
     * Java programs frequently need to communicate with external
     * resources such as files, keyboards, consoles, and network
     * connections.
     *
     * Java provides the java.io package to perform these operations.
     *
     * Input:
     * Bringing data from an external source into a Java program.
     *
     * Examples:
     * - Reading data from a file.
     * - Reading input from the keyboard.
     * - Receiving data from another application.
     *
     * Output:
     * Sending data from a Java program to an external destination.
     *
     * Examples:
     * - Writing data into a file.
     * - Displaying output on the console.
     * - Sending data to another application.
     *
     *
     * 2. WHY DO WE NEED I/O?
     *
     * Consider:
     *
     * int age = 22;
     *
     * The variable stores information in the program's memory.
     * When the program terminates, this ordinary in-memory value
     * does not automatically remain available for the next execution.
     *
     * What if we want to preserve that information?
     *
     * We need to write it to an external storage destination,
     * such as a file.
     *
     * Later, we can read that file and retrieve the information.
     *
     * I/O helps applications:
     * - Read existing data.
     * - Store information permanently in files.
     * - Process external data.
     * - Exchange information with other systems.
     * - Generate reports and logs.
     *
     *
     * 3. WHAT IS A STREAM?
     *
     * A stream is a flow of data between a source and a destination.
     *
     * Think of a stream like a pipe through which data travels.
     *
     * Example: Reading a file.
     *
     *       FILE
     *         |
     *         | Data flows
     *         v
     *    INPUT STREAM
     *         |
     *         v
     *    JAVA PROGRAM
     *
     * Example: Writing into a file.
     *
     *    JAVA PROGRAM
     *         |
     *         v
     *   OUTPUT STREAM
     *         |
     *         | Data flows
     *         v
     *       FILE
     *
     * Important:
     * - Input streams bring data into the program.
     * - Output streams send data out of the program.
     *
     * The direction is defined from the application's perspective.
     *
     *
     * 4. TYPES OF STREAMS IN JAVA
     *
     * Java I/O provides two fundamental categories of streams:
     *
     * 1. Byte Streams
     * 2. Character Streams
     *
     *
     * A. BYTE STREAMS
     *
     * Byte streams process data as individual bytes.
     *
     * A byte contains 8 bits.
     *
     * They are suitable for handling binary data.
     *
     * Examples:
     * - Images
     * - Audio files
     * - Video files
     * - PDF files
     * - ZIP files
     * - Any arbitrary binary content
     *
     * Main abstract classes:
     *
     * InputStream  -> Reads bytes.
     * OutputStream -> Writes bytes.
     *
     * Common implementations:
     *
     * FileInputStream
     * FileOutputStream
     * BufferedInputStream
     * BufferedOutputStream
     * DataInputStream
     * DataOutputStream
     * ObjectInputStream
     * ObjectOutputStream
     *
     *
     * B. CHARACTER STREAMS
     *
     * Character streams are designed for text.
     *
     * They read and write character data while handling the
     * conversion between bytes and characters using a character
     * encoding.
     *
     * Examples:
     * - Text files
     * - CSV files
     * - Configuration files
     * - Source code files
     *
     * Main abstract classes:
     *
     * Reader -> Reads characters.
     * Writer -> Writes characters.
     *
     * Common implementations:
     *
     * FileReader
     * FileWriter
     * BufferedReader
     * BufferedWriter
     * InputStreamReader
     * OutputStreamWriter
     * PrintWriter
     *
     *
     * 5. BYTE STREAMS VS CHARACTER STREAMS
     *
     * BYTE STREAMS:
     * - Process bytes.
     * - Use InputStream and OutputStream.
     * - Suitable for binary data.
     * - Do not automatically interpret data as text.
     *
     * CHARACTER STREAMS:
     * - Process character data.
     * - Use Reader and Writer.
     * - Designed for text processing.
     * - Perform byte-to-character or character-to-byte conversion
     *   through a charset when connected to byte-based sources.
     *
     * Example:
     *
     * Copying an image:
     * Use FileInputStream and FileOutputStream.
     *
     * Reading a text file:
     * Use FileReader or BufferedReader.
     *
     * Important:
     * Text files are also stored as bytes on disk.
     * Character streams convert those bytes into characters
     * according to an encoding.
     *
     *
     * 6. JAVA I/O CLASS HIERARCHY
     *
     * Java I/O is built around abstract base classes.
     *
     *
     * BYTE STREAM HIERARCHY:
     *
     *                 InputStream
     *                      |
     *          +-----------+------------+
     *          |           |            |
     *    FileInputStream  FilterInputStream
     *                          |
     *                  +-------+--------+
     *                  |                |
     *          BufferedInputStream  DataInputStream
     *
     *
     *                 OutputStream
     *                      |
     *          +-----------+------------+
     *          |           |            |
     *   FileOutputStream  FilterOutputStream
     *                          |
     *                  +-------+--------+
     *                  |                |
     *         BufferedOutputStream  DataOutputStream
     *         PrintStream
     *
     * These are representative relationships, not the complete
     * class hierarchy.
     *
     *
     * CHARACTER STREAM HIERARCHY:
     *
     *                    Reader
     *                      |
     *          +-----------+------------+
     *          |           |            |
     *      FileReader  InputStreamReader
     *                      |
     *                BufferedReader
     *
     *
     *                    Writer
     *                      |
     *          +-----------+------------+
     *          |           |            |
     *      FileWriter  OutputStreamWriter
     *                      |
     *                BufferedWriter
     *
     * Other important Writer implementations include:
     * PrintWriter and StringWriter.
     *
     * Important:
     * InputStream, OutputStream, Reader and Writer are abstract
     * classes. We generally use their concrete implementations
     * to perform actual I/O operations.
     *
     *
     * 7. IMPORTANT STREAM OPERATIONS
     *
     * BYTE STREAM METHODS:
     *
     * InputStream:
     *
     * read()
     *     Reads one byte and returns it as an int.
     *     Returns -1 when the end of the stream is reached.
     *
     * read(byte[] b)
     *     Reads bytes into an array.
     *
     * read(byte[] b, int off, int len)
     *     Reads bytes into a specified portion of an array.
     *
     * skip(long n)
     *     Skips bytes.
     *
     * available()
     *     Estimates the number of bytes readable without blocking.
     *     It does not report the total remaining file size.
     *
     * close()
     *     Closes the stream and releases associated resources.
     *
     *
     * OutputStream:
     *
     * write(int b)
     *     Writes one byte.
     *
     * write(byte[] b)
     *     Writes an array of bytes.
     *
     * write(byte[] b, int off, int len)
     *     Writes a specified portion of an array.
     *
     * flush()
     *     Forces buffered output to be passed to its destination.
     *
     * close()
     *     Closes the stream.
     *
     *
     * CHARACTER STREAM METHODS:
     *
     * Reader:
     *
     * read()
     *     Reads one character and returns it as an int.
     *
     * read(char[] cbuf)
     *     Reads characters into an array.
     *
     * read(char[] cbuf, int off, int len)
     *     Reads characters into a specified portion of an array.
     *
     * close()
     *     Closes the reader.
     *
     *
     * Writer:
     *
     * write(int c)
     *     Writes a character.
     *
     * write(char[] cbuf)
     *     Writes a character array.
     *
     * write(String str)
     *     Writes a string.
     *
     * append(CharSequence csq)
     *     Appends character data.
     *
     * flush()
     *     Forces buffered output to be passed to its destination.
     *
     * close()
     *     Closes the writer.
     *
     * Note:
     * The methods listed above are representative. Concrete
     * classes may provide additional methods.
     *
     *
     * 8. WHY DOES read() RETURN int INSTEAD OF byte OR char?
     *
     * This is an important design decision.
     *
     * The read() method must represent both:
     *
     * 1. Every possible byte or character value.
     * 2. The special end-of-stream marker -1.
     *
     * A byte has values from -128 to 127 when represented as
     * a signed Java byte.
     *
     * An int can represent the byte values returned by
     * InputStream as 0 to 255, along with -1.
     *
     * Similarly, Reader.read() returns character values as
     * integers from 0 to 65535, along with -1.
     *
     * Therefore, int provides room for the special -1 marker.
     *
     * Correct reading pattern:
     *
     * int data;
     *
     * while ((data = inputStream.read()) != -1) {
     *     // Process data.
     * }
     *
     * Common mistake:
     *
     * byte data;
     * while ((data = (byte) inputStream.read()) != -1) {
     *     // Incorrect approach.
     * }
     *
     * A byte cannot safely represent the end-of-stream marker
     * separately from all possible byte values.
     *
     *
     * 9. WHAT IS END OF STREAM (EOF)?
     *
     * EOF means End Of File or, more generally, the end of a stream.
     *
     * When read() returns -1, there is no more data available
     * from that stream.
     *
     * Example:
     *
     * int data;
     *
     * while ((data = reader.read()) != -1) {
     *     System.out.print((char) data);
     * }
     *
     * The loop stops when the stream reaches its end.
     *
     * Important:
     * EOF is not a character stored inside every file.
     * It is a return value used by reading methods to indicate
     * that the stream has ended.
     *
     *
     * 10. WHAT IS BUFFERING?
     *
     * Imagine reading a large file one byte at a time.
     *
     * Without buffering:
     * - Many individual read operations may be required.
     * - This can create unnecessary I/O overhead.
     *
     * Buffering uses a temporary memory area to collect data
     * and process it in larger chunks.
     *
     * Example:
     *
     * File
     *  |
     *  v
     * BufferedInputStream
     *  |
     *  v
     * Java Program
     *
     * Important buffered classes:
     *
     * BufferedInputStream
     * BufferedOutputStream
     * BufferedReader
     * BufferedWriter
     *
     * Benefits:
     * - Reduces the number of underlying I/O operations.
     * - Can improve performance.
     * - Provides convenient methods such as readLine().
     *
     * Note:
     * Buffering is not the same as asynchronous I/O.
     *
     *
     * 11. WHAT IS FLUSHING?
     *
     * Output operations may temporarily keep data in a buffer.
     *
     * flush() requests that buffered output be sent to the
     * underlying destination.
     *
     * Example:
     *
     * writer.write("Hello");
     * writer.flush();
     *
     * This requests that the buffered characters be passed
     * to the underlying output destination.
     *
     * Important:
     * - flush() does not close the stream.
     * - close() closes the stream and normally flushes output
     *   where applicable.
     * - flush() does not guarantee that data has been physically
     *   persisted to storage hardware.
     *
     *
     * 12. WHAT IS CHARACTER ENCODING?
     *
     * Computers store file content as bytes.
     *
     * But Java programs often work with characters and strings.
     *
     * Character encoding defines how characters are represented
     * as bytes and how bytes are decoded into characters.
     *
     * Common encodings:
     *
     * UTF-8
     * UTF-16
     * ISO-8859-1
     * US-ASCII
     *
     * Example:
     *
     * The text "Hello" is converted into bytes using an encoding.
     * When read, those bytes must be decoded using a compatible
     * encoding to obtain the intended text.
     *
     * What happens if the wrong encoding is used?
     *
     * - Text may appear corrupted.
     * - Some characters may be replaced.
     * - Non-English characters may not display correctly.
     *
     * Recommended practice:
     * Use an explicit charset when reading or writing text.
     *
     * Example:
     *
     * StandardCharsets.UTF_8
     *
     * Note:
     * FileReader and FileWriter gained constructors accepting
     * Charset in Java 11.
     *
     * In Java 8, InputStreamReader and OutputStreamWriter can
     * be used with an explicitly specified Charset.
     *
     *
     * 13. WHAT IS RESOURCE MANAGEMENT?
     *
     * Streams and readers may hold operating-system resources.
     *
     * Examples:
     * - File handles.
     * - Operating-system resources.
     * - Network connection resources.
     *
     * These resources should be closed when they are no longer
     * required.
     *
     * Traditional approach:
     *
     * InputStream input = null;
     *
     * try {
     *     input = new FileInputStream("data.txt");
     *     // Read data.
     * } catch (IOException e) {
     *     e.printStackTrace();
     * } finally {
     *     if (input != null) {
     *         try {
     *             input.close();
     *         } catch (IOException e) {
     *             e.printStackTrace();
     *         }
     *     }
     * }
     *
     * Modern approach:
     *
     * try (InputStream input =
     *          new FileInputStream("data.txt")) {
     *
     *     // Read data.
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     * The modern approach uses try-with-resources.
     *
     * It automatically closes resources that implement
     * AutoCloseable.
     *
     * We will study this in detail in the relevant topic.
     *
     *
     * 14. CHECKED EXCEPTIONS IN JAVA I/O
     *
     * Most traditional file and stream operations can fail.
     *
     * Examples:
     * - The requested file does not exist.
     * - The program does not have permission.
     * - The storage device encounters an error.
     * - A stream is already closed.
     *
     * Important exception classes:
     *
     * IOException
     * FileNotFoundException
     * EOFException
     * UnsupportedEncodingException
     *
     * IOException is the general checked exception for many
     * I/O-related failures.
     *
     * FileNotFoundException is a subclass of IOException.
     *
     * Note:
     * Not every failure is represented by IOException.
     * Some invalid arguments and programming errors can produce
     * unchecked exceptions.
     *
     *
     * 15. BLOCKING NATURE OF TRADITIONAL JAVA I/O
     *
     * Traditional stream-based file I/O is generally blocking.
     *
     * Consider:
     *
     * int data = inputStream.read();
     *
     * The calling thread waits until the read operation can
     * return data, reach the end of the stream, or fail.
     *
     * This means the thread cannot proceed past that call
     * until the operation returns.
     *
     * Important:
     * Blocking does not mean the entire application stops.
     * Only the thread performing the blocking operation waits.
     *
     *
     * 16. IMPORTANT JAVA I/O PACKAGES
     *
     * java.io
     *
     * Contains:
     * - InputStream
     * - OutputStream
     * - Reader
     * - Writer
     * - File
     * - FileInputStream
     * - FileOutputStream
     * - BufferedReader
     * - BufferedWriter
     * - ObjectInputStream
     * - ObjectOutputStream
     * - Many other I/O classes.
     *
     * java.nio
     *
     * Contains core NIO types such as:
     * - Buffer
     * - ByteBuffer
     * - CharBuffer
     *
     * Java NIO will be covered separately.
     *
     *
     * 17. JAVA I/O IN REAL-WORLD BACKEND DEVELOPMENT
     *
     * Java I/O is useful in backend applications for:
     *
     * - Reading application configuration files.
     * - Generating log files.
     * - Processing CSV and text files.
     * - Reading and writing uploaded documents.
     * - Creating reports.
     * - Importing and exporting data.
     * - Processing binary content.
     *
     * Example:
     *
     * A Spring Boot application receives a CSV file from a user.
     *
     * The application may:
     *
     * 1. Receive the uploaded file.
     * 2. Read its contents.
     * 3. Parse the records.
     * 4. Validate the data.
     * 5. Store valid records in a database.
     *
     * Java I/O provides the reading and writing capabilities
     * needed in such workflows.
     *
     *
     * 18. COMMON MISTAKES
     *
     * 1. Using byte streams for ordinary text processing without
     *    considering character encoding.
     *
     * 2. Using character streams to copy arbitrary binary files.
     *
     * 3. Forgetting to close streams.
     *
     * 4. Assuming read() returns -1 when it reads a byte whose
     *    value is 255. InputStream.read() returns 255 for that byte.
     *
     * 5. Assuming available() returns the total remaining file size.
     *
     * 6. Assuming flush() physically saves data to storage.
     *
     * 7. Using platform-default character encoding when a specific
     *    encoding is required.
     *
     * 8. Assuming buffering makes an operation asynchronous.
     *
     * 9. Assuming EOF is a special character physically stored
     *    at the end of every file.
     *
     *
     * 19. INTERVIEW QUESTIONS
     *
     * 1. What is Java I/O?
     * 2. What is a stream?
     * 3. What are the two major types of streams?
     * 4. What is the difference between byte and character streams?
     * 5. What are InputStream and OutputStream?
     * 6. What are Reader and Writer?
     * 7. Why does read() return int instead of byte or char?
     * 8. What does -1 represent in read()?
     * 9. What is buffering?
     * 10. What is the purpose of flush()?
     * 11. What is character encoding?
     * 12. Why should streams be closed?
     * 13. What is try-with-resources?
     * 14. What is the difference between flush() and close()?
     * 15. What is the difference between blocking and non-blocking
     *     I/O?
     *
     *
     * 20. QUICK REVISION
     *
     * JAVA I/O:
     * Traditional input and output API in java.io.
     *
     * STREAM:
     * A flow of data between a source and destination.
     *
     * BYTE STREAM:
     * Processes bytes using InputStream and OutputStream.
     *
     * CHARACTER STREAM:
     * Processes text using Reader and Writer.
     *
     * BUFFER:
     * A temporary memory area used to process data in chunks.
     *
     * EOF:
     * End of stream, represented by -1 from read() methods.
     *
     * ENCODING:
     * Defines conversion between characters and bytes.
     *
     * FLUSH:
     * Requests that buffered output be passed to its destination.
     *
     * CLOSE:
     * Releases the resources associated with a stream or reader.
     *
     * ================================================================
     *                 END OF JAVA I/O FUNDAMENTALS
     * ================================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Java I/O Fundamentals , Happy Learning ..!");
    }
}
