
public class ByteStreams {

    /*
     * ================================================================
     *                         BYTE STREAMS
     * ================================================================
     *
     * 1. WHAT ARE BYTE STREAMS?
     *
     * Byte streams are used to read and write data in the form of
     * bytes.
     *
     * A byte contains 8 bits.
     *
     * Java uses byte streams when we want to process raw binary data
     * without treating it as text.
     *
     * Examples:
     * - Images
     * - Audio files
     * - Video files
     * - PDF files
     * - ZIP files
     * - Executable files
     * - Any arbitrary binary content
     *
     * Java provides two fundamental abstract classes:
     *
     * InputStream  -> Reads bytes.
     * OutputStream -> Writes bytes.
     *
     * Both belong to java.io.
     *
     *
     * 2. INPUTSTREAM
     *
     * InputStream is an abstract class that represents a source
     * of byte input.
     *
     * It provides methods for reading bytes from a source.
     *
     * Common implementations:
     *
     * FileInputStream
     * ByteArrayInputStream
     * BufferedInputStream
     * DataInputStream
     * ObjectInputStream
     * PipedInputStream
     * SequenceInputStream
     *
     * Example:
     *
     * InputStream input = new FileInputStream("photo.jpg");
     *
     * Here:
     * - FileInputStream connects to the file.
     * - InputStream provides the common reading abstraction.
     *
     *
     * 3. IMPORTANT INPUTSTREAM METHODS
     *
     * A. read()
     *
     * Reads one byte.
     *
     * Returns:
     * - An integer from 0 to 255 representing the byte.
     * -1 when the end of the stream is reached.
     *
     * Example:
     *
     * int data = input.read();
     *
     * Why int?
     *
     * An unsigned byte value requires 0 to 255.
     * The method also needs a separate value, -1, to represent EOF.
     * int can represent both.
     *
     *
     * B. read(byte[] b)
     *
     * Reads bytes into the supplied array.
     *
     * Returns the number of bytes actually read.
     *
     * Returns -1 if the end of the stream is reached before
     * any byte can be read.
     *
     * Example:
     *
     * byte[] buffer = new byte[1024];
     * int bytesRead = input.read(buffer);
     *
     *
     * C. read(byte[] b, int off, int len)
     *
     * Reads up to len bytes into the array, starting at index off.
     *
     * Example:
     *
     * byte[] buffer = new byte[1024];
     * int bytesRead = input.read(buffer, 0, 512);
     *
     *
     * D. skip(long n)
     *
     * Attempts to skip over and discard n bytes.
     *
     * Returns the actual number of bytes skipped.
     *
     * Important:
     * The returned number may be less than requested.
     *
     *
     * E. available()
     *
     * Returns an estimate of the number of bytes that can be read
     * or skipped without blocking.
     *
     * Important:
     * It does NOT return the total remaining file size.
     *
     * Never use available() as a general way to determine the
     * length of an input file.
     *
     *
     * F. close()
     *
     * Closes the stream and releases associated resources.
     *
     *
     * G. mark(int readLimit) AND reset()
     *
     * mark() marks the current position in a stream.
     *
     * reset() attempts to return to the marked position.
     *
     * markSupported() checks whether the stream supports marking.
     *
     * Not every InputStream implementation supports mark/reset.
     *
     *
     * 4. OUTPUTSTREAM
     *
     * OutputStream is an abstract class that represents a
     * destination for byte output.
     *
     * It provides methods for writing bytes.
     *
     * Common implementations:
     *
     * FileOutputStream
     * ByteArrayOutputStream
     * BufferedOutputStream
     * DataOutputStream
     * ObjectOutputStream
     * PipedOutputStream
     *
     * Example:
     *
     * OutputStream output =
     *     new FileOutputStream("photo-copy.jpg");
     *
     *
     * 5. IMPORTANT OUTPUTSTREAM METHODS
     *
     * A. write(int b)
     *
     * Writes one byte.
     *
     * Only the lowest 8 bits of the supplied integer are written.
     *
     * Example:
     *
     * output.write(65);
     *
     * Writes the byte value 65.
     *
     *
     * B. write(byte[] b)
     *
     * Writes all bytes from the supplied array.
     *
     * Example:
     *
     * byte[] data = {65, 66, 67};
     * output.write(data);
     *
     *
     * C. write(byte[] b, int off, int len)
     *
     * Writes len bytes starting from array index off.
     *
     * Example:
     *
     * output.write(data, 0, 2);
     *
     * Writes the first two bytes.
     *
     *
     * D. flush()
     *
     * Requests that buffered output be sent to the underlying
     * destination.
     *
     * Not all OutputStream implementations maintain their own
     * internal buffer.
     *
     *
     * E. close()
     *
     * Closes the output stream and releases associated resources.
     *
     *
     * 6. FILEINPUTSTREAM
     *
     * FileInputStream is a concrete InputStream implementation.
     *
     * It reads raw bytes from a file.
     *
     * Common constructors:
     *
     * FileInputStream(String name)
     *
     * FileInputStream(File file)
     *
     * FileInputStream(FileDescriptor fdObj)
     *
     * The constructors using a filename or File can throw
     * FileNotFoundException.
     *
     * Example:
     *
     * try (FileInputStream input =
     *          new FileInputStream("data.bin")) {
     *
     *     int data;
     *
     *     while ((data = input.read()) != -1) {
     *         System.out.println(data);
     *     }
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     * Important:
     * - Reads the file as bytes.
     * - read() returns values from 0 to 255, or -1.
     * - Reading one byte at a time is simple but may be inefficient
     *   for large files.
     *
     *
     * 7. FILEOUTPUTSTREAM
     *
     * FileOutputStream is a concrete OutputStream implementation.
     *
     * It writes raw bytes into a file.
     *
     * Common constructors:
     *
     * FileOutputStream(String name)
     *
     * FileOutputStream(String name, boolean append)
     *
     * FileOutputStream(File file)
     *
     * FileOutputStream(File file, boolean append)
     *
     * FileOutputStream(FileDescriptor fdObj)
     *
     * Important:
     *
     * By default, opening a FileOutputStream for a file truncates
     * the existing file contents.
     *
     * Example:
     *
     * try (FileOutputStream output =
     *          new FileOutputStream("data.bin")) {
     *
     *     output.write(65);
     *     output.write(66);
     *     output.write(67);
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     * The file now contains three bytes:
     *
     * 65, 66, 67
     *
     *
     * 8. APPEND MODE
     *
     * FileOutputStream provides constructors with an append flag.
     *
     * append = false:
     * Existing file contents are truncated when the stream is opened.
     *
     * append = true:
     * New data is written at the end of the existing file.
     *
     * Example:
     *
     * try (FileOutputStream output =
     *          new FileOutputStream("data.bin", true)) {
     *
     *     output.write(68);
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     * If the file previously contained:
     *
     * 65, 66, 67
     *
     * It now contains:
     *
     * 65, 66, 67, 68
     *
     * Important:
     * If the file does not exist, FileOutputStream creates it
     * when possible.
     *
     *
     * 9. READING A FILE BYTE BY BYTE
     *
     * Example:
     *
     * try (FileInputStream input =
     *          new FileInputStream("data.bin")) {
     *
     *     int data;
     *
     *     while ((data = input.read()) != -1) {
     *         System.out.println(data);
     *     }
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     * Suppose the file contains:
     *
     * A B C
     *
     * The output is:
     *
     * 65
     * 66
     * 67
     *
     * Why?
     *
     * FileInputStream reads the encoded byte values, not
     * Java character objects.
     *
     * For ASCII characters:
     *
     * A = 65
     * B = 66
     * C = 67
     *
     *
     * 10. READING MULTIPLE BYTES USING AN ARRAY
     *
     * Instead of reading one byte at a time, we can read a group
     * of bytes into an array.
     *
     * Example:
     *
     * byte[] buffer = new byte[1024];
     *
     * try (FileInputStream input =
     *          new FileInputStream("data.bin")) {
     *
     *     int bytesRead;
     *
     *     while ((bytesRead = input.read(buffer)) != -1) {
     *
     *         for (int i = 0; i < bytesRead; i++) {
     *             System.out.println(buffer[i]);
     *         }
     *     }
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     * Important:
     *
     * bytesRead tells us how many bytes were actually read.
     *
     * Do not process the entire array every time.
     *
     * The final read may fill only part of the array.
     *
     * The remaining positions may contain old or default values.
     *
     *
     * 11. WRITING MULTIPLE BYTES
     *
     * Example:
     *
     * byte[] data = {65, 66, 67, 68};
     *
     * try (FileOutputStream output =
     *          new FileOutputStream("data.bin")) {
     *
     *     output.write(data);
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     * The complete array is written to the file.
     *
     *
     * 12. COPYING A FILE USING BYTE STREAMS
     *
     * One of the most common practical uses of byte streams
     * is copying files.
     *
     * Example:
     *
     * try (
     *     FileInputStream input =
     *         new FileInputStream("source.jpg");
     *
     *     FileOutputStream output =
     *         new FileOutputStream("destination.jpg")
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
     * Explanation:
     *
     * 1. Open the source file for reading.
     * 2. Open the destination file for writing.
     * 3. Create a temporary byte array.
     * 4. Read a group of bytes into the array.
     * 5. Write only the bytes actually read.
     * 6. Repeat until EOF.
     * 7. Close both streams automatically.
     *
     * Important:
     * This approach copies raw bytes and is suitable for
     * arbitrary binary files.
     *
     *
     * 13. WHY DO WE USE BUFFERS?
     *
     * Consider a file containing 10 MB of data.
     *
     * Reading one byte per call means a very large number of
     * read method invocations.
     *
     * Using an array allows us to read multiple bytes per call.
     *
     * Example:
     *
     * byte[] buffer = new byte[8192];
     *
     * The array can hold 8192 bytes.
     *
     * This reduces method-call overhead and can improve performance.
     *
     * Important:
     * Reading into a byte array does not automatically mean that
     * BufferedInputStream is being used.
     *
     * The array is an application-managed buffer.
     * BufferedInputStream provides its own internal buffering.
     *
     *
     * 14. BUFFEREDINPUTSTREAM
     *
     * BufferedInputStream wraps another InputStream.
     *
     * It maintains an internal byte buffer.
     *
     * This reduces the number of underlying read operations.
     *
     * Example:
     *
     * try (BufferedInputStream input =
     *          new BufferedInputStream(
     *              new FileInputStream("data.bin"))) {
     *
     *     int data;
     *
     *     while ((data = input.read()) != -1) {
     *         // Process data.
     *     }
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     * Important:
     * BufferedInputStream does not replace FileInputStream.
     * It wraps an existing InputStream and adds buffering.
     *
     * We will study buffered streams separately.
     *
     *
     * 15. BUFFEREDOUTPUTSTREAM
     *
     * BufferedOutputStream wraps another OutputStream.
     *
     * It temporarily collects bytes before sending them
     * to the underlying output destination.
     *
     * Example:
     *
     * try (BufferedOutputStream output =
     *          new BufferedOutputStream(
     *              new FileOutputStream("data.bin"))) {
     *
     *     output.write(65);
     *     output.write(66);
     *     output.write(67);
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     * When the stream is closed, pending buffered output
     * is flushed as part of the close operation.
     *
     *
     * 16. BYTEARRAYINPUTSTREAM
     *
     * ByteArrayInputStream reads bytes from an in-memory
     * byte array.
     *
     * It does not need to read from an external file.
     *
     * Example:
     *
     * byte[] data = {65, 66, 67};
     *
     * try (ByteArrayInputStream input =
     *          new ByteArrayInputStream(data)) {
     *
     *     int value;
     *
     *     while ((value = input.read()) != -1) {
     *         System.out.println(value);
     *     }
     *
     * }
     *
     * Common use cases:
     * - Testing I/O logic.
     * - Processing data already stored in memory.
     * - Supplying byte arrays to APIs expecting InputStream.
     *
     *
     * 17. BYTEARRAYOUTPUTSTREAM
     *
     * ByteArrayOutputStream writes data into an internal
     * memory buffer.
     *
     * It does not directly write to a file.
     *
     * Example:
     *
     * try (ByteArrayOutputStream output =
     *          new ByteArrayOutputStream()) {
     *
     *     output.write(65);
     *     output.write(66);
     *     output.write(67);
     *
     *     byte[] result = output.toByteArray();
     *
     *     System.out.println(result.length);
     * }
     *
     * Important methods:
     *
     * toByteArray()
     *     Returns a new byte array containing the output.
     *
     * toString()
     *     Converts the buffered bytes into a String using
     *     a character encoding.
     *
     * writeTo(OutputStream out)
     *     Writes the complete contents to another output stream.
     *
     * reset()
     *     Resets the internal count to zero.
     *
     * Common use cases:
     * - Building byte arrays dynamically.
     * - Testing.
     * - Preparing data before sending it elsewhere.
     *
     *
     * 18. PUSHBACKINPUTSTREAM
     *
     * PushbackInputStream allows bytes that have already been
     * read to be pushed back into the stream.
     *
     * This means the next read can return the pushed-back byte.
     *
     * Example:
     *
     * try (PushbackInputStream input =
     *          new PushbackInputStream(
     *              new FileInputStream("data.bin"))) {
     *
     *     int first = input.read();
     *
     *     input.unread(first);
     *
     *     int again = input.read();
     *
     *     System.out.println(first == again);
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     * Common use cases:
     * - Parsing input.
     * - Looking ahead at data.
     * - Returning a byte to the input stream.
     *
     *
     * 19. SEQUENCEINPUTSTREAM
     *
     * SequenceInputStream combines multiple InputStreams into
     * one logical input stream.
     *
     * It reads from the first stream until EOF, then continues
     * with the next stream.
     *
     * Example:
     *
     * try (
     *     FileInputStream first =
     *         new FileInputStream("part1.bin");
     *
     *     FileInputStream second =
     *         new FileInputStream("part2.bin");
     *
     *     SequenceInputStream combined =
     *         new SequenceInputStream(first, second)
     * ) {
     *
     *     int data;
     *
     *     while ((data = combined.read()) != -1) {
     *         // Process data.
     *     }
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     * Important:
     * SequenceInputStream closes its component streams when
     * it is closed.
     *
     * The try-with-resources example also closes the original
     * streams explicitly.
     *
     *
     * 20. PIPEDINPUTSTREAM AND PIPEDOUTPUTSTREAM
     *
     * These streams allow data to be transferred between threads
     * through a pipe.
     *
     * PipedOutputStream:
     * Writes bytes into the pipe.
     *
     * PipedInputStream:
     * Reads bytes from the pipe.
     *
     * Concept:
     *
     * THREAD A
     *    |
     *    v
     * PipedOutputStream
     *    |
     *    v
     *    PIPE
     *    |
     *    v
     * PipedInputStream
     *    |
     *    v
     * THREAD B
     *
     * Example:
     *
     * PipedInputStream input = new PipedInputStream();
     * PipedOutputStream output = new PipedOutputStream(input);
     *
     * Thread writer = new Thread(() -> {
     *     try {
     *         output.write(65);
     *         output.close();
     *     } catch (IOException e) {
     *         e.printStackTrace();
     *     }
     * });
     *
     * Thread reader = new Thread(() -> {
     *     try {
     *         int data;
     *
     *         while ((data = input.read()) != -1) {
     *             System.out.println(data);
     *         }
     *
     *         input.close();
     *     } catch (IOException e) {
     *         e.printStackTrace();
     *     }
     * });
     *
     * reader.start();
     * writer.start();
     *
     * Common use case:
     * Passing data between cooperating threads.
     *
     *
     * 21. FILEINPUTSTREAM VS BUFFEREDINPUTSTREAM
     *
     * FileInputStream:
     * - Reads bytes directly from a file.
     * - Does not provide its own buffering layer.
     * - Can read one byte or multiple bytes into an array.
     *
     * BufferedInputStream:
     * - Wraps another InputStream.
     * - Maintains an internal buffer.
     * - Reduces underlying read operations.
     * - Supports mark() and reset().
     *
     * Example:
     *
     * InputStream input =
     *     new BufferedInputStream(
     *         new FileInputStream("data.bin"));
     *
     *
     * 22. FILEOUTPUTSTREAM VS BUFFEREDOUTPUTSTREAM
     *
     * FileOutputStream:
     * - Writes bytes to a file.
     * - Does not provide its own buffering layer.
     *
     * BufferedOutputStream:
     * - Wraps another OutputStream.
     * - Maintains an internal buffer.
     * - Reduces underlying write operations.
     *
     *
     * 23. BYTE STREAMS AND CHARACTER STREAMS
     *
     * BYTE STREAMS:
     *
     * InputStream
     * OutputStream
     *
     * Process raw bytes.
     *
     * CHARACTER STREAMS:
     *
     * Reader
     * Writer
     *
     * Process character data.
     *
     * Important:
     * Do not copy arbitrary binary files using character streams.
     * Character decoding and encoding can change the original
     * byte sequence.
     *
     *
     * 24. COMMON EXCEPTIONS
     *
     * IOException:
     * General checked exception for I/O failures.
     *
     * FileNotFoundException:
     * Commonly thrown when a file cannot be opened.
     *
     * EOFException:
     * Indicates unexpected end of input for certain data-reading
     * operations, such as DataInputStream.readFully().
     *
     * Important:
     * FileInputStream's read() normally indicates EOF by returning
     * -1 rather than throwing EOFException.
     *
     *
     * 25. COMMON MISTAKES
     *
     * 1. Using FileInputStream to process text without considering
     *    character encoding.
     *
     * 2. Using character streams to copy arbitrary binary files.
     *
     * 3. Forgetting that read() returns -1 at EOF.
     *
     * 4. Processing the entire buffer instead of bytesRead bytes.
     *
     * 5. Forgetting to close streams.
     *
     * 6. Forgetting that FileOutputStream truncates an existing
     *    file by default.
     *
     * 7. Assuming available() returns total remaining file bytes.
     *
     * 8. Assuming flush() physically persists data to storage.
     *
     * 9. Assuming byte-array buffering and BufferedInputStream
     *    are exactly the same thing.
     *
     *
     * 26. INTERVIEW QUESTIONS
     *
     * 1. What is a byte stream?
     * 2. What is InputStream?
     * 3. What is OutputStream?
     * 4. Why does read() return int?
     * 5. What is the meaning of -1?
     * 6. What is the difference between read() and read(byte[])?
     * 7. What does available() return?
     * 8. What is FileInputStream?
     * 9. What is FileOutputStream?
     * 10. What is append mode?
     * 11. How do you copy an image using byte streams?
     * 12. What is BufferedInputStream?
     * 13. What is BufferedOutputStream?
     * 14. What is ByteArrayInputStream?
     * 15. What is ByteArrayOutputStream?
     * 16. What is PushbackInputStream?
     * 17. What is SequenceInputStream?
     * 18. What are piped streams?
     * 19. What is the difference between FileInputStream and
     *     BufferedInputStream?
     * 20. Why should byte streams be used for binary files?
     *
     *
     * 27. QUICK REVISION
     *
     * InputStream:
     * Abstract base class for byte input.
     *
     * OutputStream:
     * Abstract base class for byte output.
     *
     * FileInputStream:
     * Reads bytes from a file.
     *
     * FileOutputStream:
     * Writes bytes to a file.
     *
     * BufferedInputStream:
     * Adds input buffering.
     *
     * BufferedOutputStream:
     * Adds output buffering.
     *
     * ByteArrayInputStream:
     * Reads from an in-memory byte array.
     *
     * ByteArrayOutputStream:
     * Writes into an in-memory byte buffer.
     *
     * PushbackInputStream:
     * Allows bytes to be pushed back into an input stream.
     *
     * SequenceInputStream:
     * Combines multiple input streams sequentially.
     *
     * PipedInputStream and PipedOutputStream:
     * Transfer bytes between cooperating threads.
     *
     * ================================================================
     *                       END OF BYTE STREAMS
     * ================================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Byte Streams , Happy Learning ..!");
    }
}
