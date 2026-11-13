
public class FileIO {

    /*
     * ================================================================
     *                         JAVA FILE I/O
     * ================================================================
     *
     * 1. WHAT IS I/O?
     *
     * I/O stands for Input and Output.
     *
     * A Java program often needs to communicate with things outside
     * its own execution:
     *
     * Input examples:
     * - Reading data from a file.
     * - Receiving data from a keyboard.
     * - Receiving data from another application.
     * - Reading data from a network connection.
     *
     * Output examples:
     * - Writing data into a file.
     * - Displaying information on the console.
     * - Sending data to another application.
     * - Sending data through a network connection.
     *
     * In simple words:
     * Input  = Bringing data into a program.
     * Output = Sending data out of a program.
     *
     *
     * 2. WHAT IS FILE I/O?
     *
     * File I/O refers to reading data from files and writing data
     * into files.
     *
     * Without File I/O, data stored in ordinary program variables
     * generally disappears when the program terminates.
     *
     * File I/O allows applications to store and retrieve data
     * using files on a storage device.
     *
     * Examples:
     * - Reading a text file.
     * - Creating a new file.
     * - Writing user information into a file.
     * - Copying one file into another.
     * - Reading images, PDFs and other binary files.
     *
     *
     * 3. WHY DO WE NEED FILE I/O?
     *
     * Consider an application that stores student information.
     *
     * Without persistent storage:
     * - Student information exists only while the program runs.
     * - Once the program terminates, that in-memory information
     *   is lost.
     *
     * With File I/O:
     * - Information can be written into files.
     * - The application can read the information later.
     * - Data can survive program termination.
     *
     * Real-world applications use I/O for:
     * - Configuration files.
     * - Log files.
     * - Reports and documents.
     * - File uploads and downloads.
     * - Importing and exporting data.
     * - Processing images, videos and other files.
     *
     * Note:
     * File I/O provides file-based persistence. It is not a
     * replacement for a database when an application requires
     * structured querying, transactions and concurrent data access.
     *
     *
     * 4. JAVA I/O APIS
     *
     * Java provides two major generations of I/O APIs:
     *
     * 1. Java I/O  - java.io
     * 2. Java NIO  - java.nio and related packages
     *
     * They provide different programming models for performing
     * input and output operations.
     *
     *
     * 5. JAVA I/O (java.io)
     *
     * Java I/O is the traditional I/O API introduced in Java 1.0.
     *
     * Its primary programming model is based on STREAMS.
     *
     * A stream represents a flow of data between a source and
     * a destination.
     *
     * Example:
     *
     * File
     *  |
     *  | Reading
     *  v
     * Input Stream
     *  |
     *  v
     * Java Program
     *
     * Java I/O primarily provides:
     *
     * A. File Handling
     *    - File
     *
     * B. Byte Streams
     *    - InputStream
     *    - OutputStream
     *    - FileInputStream
     *    - FileOutputStream
     *
     * C. Character Streams
     *    - Reader
     *    - Writer
     *    - FileReader
     *    - FileWriter
     *
     * D. Buffered Streams
     *    - BufferedInputStream
     *    - BufferedOutputStream
     *    - BufferedReader
     *    - BufferedWriter
     *
     * E. Data and Print Streams
     *    - DataInputStream
     *    - DataOutputStream
     *    - PrintStream
     *    - PrintWriter
     *
     * F. Object Streams and Serialization
     *    - ObjectInputStream
     *    - ObjectOutputStream
     *    - Serializable
     *    - Externalizable
     *
     * G. Other Utilities
     *    - PushbackInputStream
     *    - SequenceInputStream
     *    - PipedInputStream
     *    - PipedOutputStream
     *
     * Main package:
     * java.io
     *
     *
     * 6. JAVA NIO (java.nio)
     *
     * NIO stands for New I/O.
     *
     * Java NIO was introduced in Java 1.4 to provide additional
     * I/O capabilities and a different programming model.
     *
     * Its core concepts include:
     *
     * A. Buffers
     *    - Buffer
     *    - ByteBuffer
     *    - CharBuffer
     *    - IntBuffer
     *    - LongBuffer
     *    - Other typed buffers
     *
     * B. Channels
     *    - Channel
     *    - FileChannel
     *    - SocketChannel
     *    - ServerSocketChannel
     *    - DatagramChannel
     *
     * C. Selectors
     *    - Selector
     *    - SelectionKey
     *    - SelectableChannel
     *
     * D. Character Encoding
     *    - Charset
     *    - StandardCharsets
     *    - CharsetEncoder
     *    - CharsetDecoder
     *
     * E. Modern File-System Operations (NIO.2)
     *    - Path
     *    - Paths
     *    - Files
     *    - FileSystem
     *    - FileSystems
     *
     * F. File Attributes and Monitoring
     *    - BasicFileAttributes
     *    - PosixFileAttributes
     *    - WatchService
     *    - WatchKey
     *    - WatchEvent
     *
     * G. Advanced I/O
     *    - AsynchronousFileChannel
     *    - MappedByteBuffer
     *    - FileLock
     *
     * Main packages include:
     * java.nio
     * java.nio.channels
     * java.nio.charset
     * java.nio.file
     * java.nio.file.attribute
     *
     *
     * 7. WHAT IS NIO.2?
     *
     * NIO.2 is a major enhancement to Java NIO introduced in Java 7.
     *
     * It provides a modern file-system API with classes such as:
     *
     * - Path       : Represents a file-system path.
     * - Files      : Provides file and directory operations.
     * - FileSystem : Represents a file system.
     * - FileStore  : Represents a storage volume.
     *
     * It also introduced enhanced file attributes, directory
     * monitoring and asynchronous file operations.
     *
     * Important:
     * NIO.2 is an enhancement to the NIO family, not an entirely
     * separate replacement for Java NIO.
     *
     *
     * 8. CORE DIFFERENCE BETWEEN JAVA I/O AND JAVA NIO
     *
     * JAVA I/O:
     * - Primarily stream-based.
     * - Uses InputStream, OutputStream, Reader and Writer.
     * - Streams generally support sequential data processing.
     * - Primarily blocking I/O.
     *
     * JAVA NIO:
     * - Provides buffer-based and channel-based I/O.
     * - Supports blocking and non-blocking channel operations.
     * - Provides selectors for multiplexing suitable channels.
     * - Provides modern file-system operations through NIO.2.
     * - Provides asynchronous I/O APIs.
     *
     * Important:
     * NIO does not mean every NIO operation is non-blocking.
     * FileChannel operations, for example, are generally blocking.
     *
     *
     * 9. STREAMS VS BUFFERS AND CHANNELS
     *
     * STREAM:
     *
     * A stream provides a flow of data between a source and
     * a destination.
     *
     * Examples:
     * - FileInputStream
     * - FileOutputStream
     * - BufferedReader
     *
     * BUFFER:
     *
     * A buffer is a temporary container that holds data while
     * performing I/O operations.
     *
     * Examples:
     * - ByteBuffer
     * - CharBuffer
     *
     * CHANNEL:
     *
     * A channel represents a connection to an I/O entity and
     * transfers data between the channel and buffers.
     *
     * Examples:
     * - FileChannel
     * - SocketChannel
     *
     * Remember:
     * Java I/O  -> Primarily Streams.
     * Java NIO  -> Buffers and Channels.
     *
     *
     * 10. BLOCKING, NON-BLOCKING AND ASYNCHRONOUS I/O
     *
     * BLOCKING I/O:
     *
     * A thread waits for an I/O operation to complete before
     * proceeding past that operation.
     *
     * NON-BLOCKING I/O:
     *
     * An operation can return without waiting for the requested
     * I/O operation to complete.
     *
     * The application can check for readiness and continue
     * performing other work.
     *
     * ASYNCHRONOUS I/O:
     *
     * An application initiates an operation and receives
     * notification or a completion result when the operation
     * finishes.
     *
     * These are different I/O execution models.
     *
     * Important:
     * - Traditional Java I/O is primarily blocking.
     * - NIO channels can support non-blocking operations.
     * - NIO.2 provides asynchronous channel APIs.
     *
     *
     * 11. JAVA I/O AND JAVA NIO COEXIST
     *
     * Java NIO did not remove Java I/O.
     *
     * Both APIs are available in modern Java applications.
     *
     * They can also work together.
     *
     * For example:
     * - Files.newBufferedReader() returns a BufferedReader.
     * - Files.newInputStream() returns an InputStream.
     * - Channels can provide adapters that connect channels
     *   with streams.
     *
     * Therefore, understanding both APIs is important.
     *
     *
     * 12. WHERE ARE THEY USED IN BACKEND DEVELOPMENT?
     *
     * JAVA I/O:
     * - Reading application configuration files.
     * - Writing log files.
     * - Processing text and CSV files.
     * - Reading and writing uploaded files.
     *
     * JAVA NIO:
     * - Efficient file operations.
     * - Network communication.
     * - Non-blocking server architectures.
     * - Large-file processing and memory mapping.
     * - Asynchronous file operations.
     *
     * In Spring Boot applications, both APIs can be useful.
     *
     *
     * 13. IMPORTANT PACKAGE SUMMARY
     *
     * java.io
     *     Traditional streams, files, serialization and utilities.
     *
     * java.nio
     *     Buffers and core NIO types.
     *
     * java.nio.channels
     *     Channels, selectors, locks and asynchronous channels.
     *
     * java.nio.charset
     *     Character encodings and encoding/decoding.
     *
     * java.nio.file
     *     Path, Files, file systems and directory operations.
     *
     * java.nio.file.attribute
     *     File-system attributes.
     *
     *
     * 14. LEARNING ROADMAP
     *
     * PART 1: JAVA I/O
     *
     * - I/O Fundamentals
     * - File Class and File Operations
     * - Byte Streams
     * - Character Streams
     * - Buffered I/O and Data Streams
     * - Object Serialization
     * - Other I/O Utilities
     * - I/O Performance and Best Practices
     *
     * PART 2: JAVA NIO
     *
     * - NIO Fundamentals
     * - Buffers
     * - Channels
     * - Selectors
     * - NIO.2 File Operations
     * - File Attributes and File Systems
     * - Asynchronous I/O
     * - Advanced NIO Concepts
     *
     *
     * 15. FINAL REVISION
     *
     * - I/O means Input and Output.
     * - File I/O deals with reading and writing files.
     * - java.io is the traditional stream-based I/O API.
     * - java.nio introduced buffers, channels and selectors.
     * - NIO.2 arrived in Java 7 with modern file-system APIs.
     * - Path and Files belong to java.nio.file.
     * - NIO supports blocking and non-blocking channel operations.
     * - NIO.2 provides asynchronous I/O capabilities.
     * - Java I/O and Java NIO coexist in modern Java.
     *
     * ================================================================
     *                         END OF JAVA FILE I/O
     * ================================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Java File I/O , Happy Learning ..!");
    }
}
