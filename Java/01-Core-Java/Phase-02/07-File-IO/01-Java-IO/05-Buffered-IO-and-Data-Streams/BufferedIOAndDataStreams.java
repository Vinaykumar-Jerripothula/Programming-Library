
public class BufferedIOAndDataStreams {

    /*
     * ================================================================
     *                 BUFFERED I/O AND DATA STREAMS
     * ================================================================
     *
     * 1. WHY DO WE NEED BUFFERED I/O?
     *
     * Consider reading a large file containing 100 MB of data.
     *
     * Without buffering:
     * - The program may perform many small read operations.
     * - Each operation can involve interaction with the underlying
     *   I/O system.
     * - Repeated operations can introduce unnecessary overhead.
     *
     * With buffering:
     * - Data is temporarily collected in memory.
     * - Multiple small operations can be served from a buffer.
     * - Fewer underlying I/O operations may be required.
     *
     * A buffer is simply a temporary memory area used to hold data
     * while reading or writing.
     *
     *
     * 2. WHAT IS BUFFERED I/O?
     *
     * Buffered I/O means using an intermediate memory buffer
     * while performing input or output operations.
     *
     * Java provides four commonly used buffered stream classes:
     *
     * BYTE STREAMS:
     *
     * BufferedInputStream
     * BufferedOutputStream
     *
     * CHARACTER STREAMS:
     *
     * BufferedReader
     * BufferedWriter
     *
     * All four classes belong to java.io.
     *
     *
     * 3. BUFFEREDINPUTSTREAM
     *
     * BufferedInputStream is a FilterInputStream subclass.
     *
     * It wraps another InputStream and adds internal buffering.
     *
     * Instead of repeatedly reading from the underlying source
     * for every small read request, it can read a larger block
     * into its internal buffer.
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
     *         // Process each byte.
     *     }
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     * Important:
     * - Wraps an existing InputStream.
     * - Provides an internal byte buffer.
     * - Supports mark() and reset().
     * - Closing it closes the wrapped input stream.
     *
     *
     * 4. BUFFEREDINPUTSTREAM CONSTRUCTORS
     *
     * BufferedInputStream(InputStream in)
     *
     * Uses the default buffer size.
     *
     * BufferedInputStream(InputStream in, int size)
     *
     * Allows us to specify the buffer size.
     *
     * Example:
     *
     * BufferedInputStream input =
     *     new BufferedInputStream(
     *         new FileInputStream("data.bin"),
     *         8192
     *     );
     *
     * Here, the requested buffer size is 8192 bytes.
     *
     * Important:
     * The buffer size is a performance-related choice.
     * A larger buffer does not automatically mean better performance.
     *
     *
     * 5. MARK AND RESET IN BUFFEREDINPUTSTREAM
     *
     * BufferedInputStream supports mark() and reset().
     *
     * mark(int readLimit):
     * Marks the current position in the stream.
     *
     * reset():
     * Attempts to return to the marked position.
     *
     * Example:
     *
     * try (BufferedInputStream input =
     *          new BufferedInputStream(
     *              new FileInputStream("data.bin"))) {
     *
     *     if (input.markSupported()) {
     *
     *         input.mark(100);
     *
     *         int first = input.read();
     *         int second = input.read();
     *
     *         input.reset();
     *
     *         int again = input.read();
     *
     *         System.out.println(first == again);
     *     }
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     * Important:
     * The read limit influences how much data can be read before
     * the mark may become invalid.
     *
     *
     * 6. BUFFEREDOUTPUTSTREAM
     *
     * BufferedOutputStream is a FilterOutputStream subclass.
     *
     * It wraps another OutputStream and maintains an internal
     * byte buffer.
     *
     * Instead of immediately forwarding every small write,
     * it collects bytes and sends them to the underlying stream
     * when the buffer fills or is flushed.
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
     * Important:
     * - Wraps an existing OutputStream.
     * - Reduces underlying write operations.
     * - flush() sends buffered bytes to the underlying stream.
     * - close() flushes and closes the wrapped stream.
     *
     *
     * 7. BUFFEREDOUTPUTSTREAM CONSTRUCTORS
     *
     * BufferedOutputStream(OutputStream out)
     *
     * Uses the default buffer size.
     *
     * BufferedOutputStream(OutputStream out, int size)
     *
     * Allows a custom buffer size.
     *
     * Example:
     *
     * BufferedOutputStream output =
     *     new BufferedOutputStream(
     *         new FileOutputStream("data.bin"),
     *         8192
     *     );
     *
     *
     * 8. BUFFEREDREADER
     *
     * BufferedReader is a Reader subclass.
     *
     * It wraps another Reader and provides internal
     * character buffering.
     *
     * One of its most useful methods is:
     *
     * readLine()
     *
     * It reads one line of text.
     *
     * Returns:
     * - A String containing the line without its line terminator.
     * - null when the end of the stream is reached.
     *
     * Example:
     *
     * try (BufferedReader reader =
     *          new BufferedReader(
     *              new FileReader("data.txt"))) {
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
     * - Efficient text reading.
     * - Convenient line-by-line processing.
     * - Reduces underlying read operations.
     *
     *
     * 9. BUFFEREDREADER CONSTRUCTORS
     *
     * BufferedReader(Reader in)
     *
     * Uses the default buffer size.
     *
     * BufferedReader(Reader in, int size)
     *
     * Allows a custom buffer size.
     *
     * Example:
     *
     * BufferedReader reader =
     *     new BufferedReader(
     *         new FileReader("data.txt"),
     *         8192
     *     );
     *
     * Important:
     * BufferedReader wraps a Reader, not an InputStream directly.
     *
     * To read bytes using BufferedReader, first convert them into
     * characters using an InputStreamReader.
     *
     *
     * 10. BUFFEREDWRITER
     *
     * BufferedWriter is a Writer subclass.
     *
     * It wraps another Writer and provides internal
     * character buffering.
     *
     * It is useful when writing large amounts of text.
     *
     * Important method:
     *
     * newLine()
     *
     * Writes the platform-specific line separator.
     *
     * Example:
     *
     * try (BufferedWriter writer =
     *          new BufferedWriter(
     *              new FileWriter("data.txt"))) {
     *
     *     writer.write("First line");
     *     writer.newLine();
     *
     *     writer.write("Second line");
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     * Benefits:
     * - Efficient text writing.
     * - Convenient line separation.
     * - Reduces underlying write operations.
     *
     *
     * 11. BUFFEREDWRITER CONSTRUCTORS
     *
     * BufferedWriter(Writer out)
     *
     * Uses the default buffer size.
     *
     * BufferedWriter(Writer out, int size)
     *
     * Allows a custom buffer size.
     *
     *
     * 12. FLUSHING BUFFERED OUTPUT
     *
     * Consider:
     *
     * BufferedWriter writer =
     *     new BufferedWriter(
     *         new FileWriter("data.txt"));
     *
     * writer.write("Hello");
     *
     * The characters may still be held in the buffer.
     *
     * Calling:
     *
     * writer.flush();
     *
     * Requests that pending buffered characters be passed
     * to the underlying writer.
     *
     * Important:
     * - flush() does not close the writer.
     * - close() flushes pending output and closes the resource.
     * - flush() does not guarantee physical persistence to disk.
     *
     *
     * 13. BUFFERED STREAMS VS UNBUFFERED STREAMS
     *
     * UNBUFFERED:
     *
     * FileInputStream
     * FileOutputStream
     * FileReader
     * FileWriter
     *
     * These classes do not add their own buffering layer.
     *
     * BUFFERED:
     *
     * BufferedInputStream
     * BufferedOutputStream
     * BufferedReader
     * BufferedWriter
     *
     * These classes add buffering around another stream or reader.
     *
     * Important:
     * An unbuffered stream can still read or write multiple bytes
     * in one method call using arrays.
     *
     * Buffering and reading into an array are not the same thing.
     *
     *
     * 14. WHAT ARE DATA STREAMS?
     *
     * Data streams allow Java programs to read and write
     * primitive Java data types in binary format.
     *
     * Java provides:
     *
     * DataInputStream
     * DataOutputStream
     *
     * Both belong to java.io.
     *
     * Supported primitive types include:
     *
     * boolean
     * byte
     * short
     * char
     * int
     * long
     * float
     * double
     *
     * Data streams also provide methods for reading and writing
     * modified UTF-8 strings.
     *
     * Important:
     * Data streams do not automatically serialize arbitrary
     * Java objects.
     *
     *
     * 15. DATAOUTPUTSTREAM
     *
     * DataOutputStream implements DataOutput.
     *
     * It writes primitive values in a portable binary format
     * to an underlying OutputStream.
     *
     * Important methods:
     *
     * writeBoolean(boolean v)
     * writeByte(int v)
     * writeShort(int v)
     * writeChar(int v)
     * writeInt(int v)
     * writeLong(long v)
     * writeFloat(float v)
     * writeDouble(double v)
     * writeUTF(String str)
     *
     * Example:
     *
     * try (DataOutputStream output =
     *          new DataOutputStream(
     *              new FileOutputStream("student.dat"))) {
     *
     *     output.writeInt(101);
     *     output.writeUTF("Vinay");
     *     output.writeDouble(8.5);
     *     output.writeBoolean(true);
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     * The data is stored in binary form, not as readable text.
     *
     *
     * 16. DATAINPUTSTREAM
     *
     * DataInputStream implements DataInput.
     *
     * It reads primitive values from an underlying InputStream.
     *
     * Important methods:
     *
     * readBoolean()
     * readByte()
     * readUnsignedByte()
     * readShort()
     * readUnsignedShort()
     * readChar()
     * readInt()
     * readLong()
     * readFloat()
     * readDouble()
     * readUTF()
     *
     * Example:
     *
     * try (DataInputStream input =
     *          new DataInputStream(
     *              new FileInputStream("student.dat"))) {
     *
     *     int id = input.readInt();
     *     String name = input.readUTF();
     *     double cgpa = input.readDouble();
     *     boolean active = input.readBoolean();
     *
     *     System.out.println(id);
     *     System.out.println(name);
     *     System.out.println(cgpa);
     *     System.out.println(active);
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     *
     * 17. WHY MUST READING ORDER MATCH WRITING ORDER?
     *
     * Suppose we write:
     *
     * output.writeInt(101);
     * output.writeUTF("Vinay");
     * output.writeDouble(8.5);
     *
     * We should read in the same order:
     *
     * input.readInt();
     * input.readUTF();
     * input.readDouble();
     *
     * Why?
     *
     * Data streams do not store field names or automatically
     * understand the meaning of each value.
     *
     * They read sequentially from the binary data.
     *
     * If we read using the wrong method or order:
     *
     * - Values can be interpreted incorrectly.
     * - Subsequent reads can become misaligned.
     * - Exceptions may occur.
     *
     * This is why both sides must agree on the data format.
     *
     *
     * 18. BIG-ENDIAN BYTE ORDER
     *
     * DataOutputStream writes multi-byte primitive values
     * using big-endian byte order.
     *
     * Big-endian means:
     * The most significant byte is stored first.
     *
     * Example:
     *
     * int value = 258;
     *
     * Hexadecimal:
     * 0x00000102
     *
     * Bytes in big-endian order:
     *
     * 00 00 01 02
     *
     * DataInputStream reads the corresponding primitive values
     * using the same byte order.
     *
     *
     * 19. MODIFIED UTF-8 IN DATA STREAMS
     *
     * DataOutputStream.writeUTF() writes a String using
     * Java's modified UTF-8 format.
     *
     * DataInputStream.readUTF() reads the same format.
     *
     * Important:
     * Modified UTF-8 is not identical to standard UTF-8.
     *
     * The writeUTF() format uses a two-byte length prefix.
     *
     * It supports a maximum encoded length of 65,535 bytes.
     *
     * If the encoded string is too long, writeUTF() throws
     * UTFDataFormatException.
     *
     * Do not assume writeUTF() creates an ordinary text file.
     *
     *
     * 20. DATA STREAMS VS OBJECT STREAMS
     *
     * DATA STREAMS:
     *
     * DataInputStream
     * DataOutputStream
     *
     * - Read and write primitive values.
     * - Use a predefined sequence of values.
     * - Do not automatically preserve arbitrary object structure.
     *
     * OBJECT STREAMS:
     *
     * ObjectInputStream
     * ObjectOutputStream
     *
     * - Support reading and writing serialized objects.
     * - Preserve object graphs according to Java serialization.
     * - Require serializable objects.
     *
     * Object serialization will be covered separately.
     *
     *
     * 21. PRINTSTREAM
     *
     * PrintStream provides convenient printing methods
     * for output streams.
     *
     * Important methods:
     *
     * print()
     * println()
     * printf()
     * format()
     * append()
     *
     * Example:
     *
     * try (PrintStream output =
     *          new PrintStream("output.txt")) {
     *
     *     output.println("Hello Java");
     *     output.println(100);
     *     output.printf("CGPA: %.2f%n", 8.5);
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     * Important:
     * - PrintStream writes bytes to an underlying OutputStream.
     * - It provides convenient text-printing methods.
     * - It uses a charset to encode text.
     * - It does not throw ordinary IOExceptions from print methods.
     * - checkError() can be used to detect an output error.
     *
     * System.out is a PrintStream.
     *
     *
     * 22. PRINTWRITER
     *
     * PrintWriter provides convenient character-oriented
     * text output.
     *
     * Important methods:
     *
     * print()
     * println()
     * printf()
     * format()
     * append()
     *
     * Example:
     *
     * try (PrintWriter writer =
     *          new PrintWriter("output.txt")) {
     *
     *     writer.println("Hello Java");
     *     writer.println(100);
     *     writer.printf("CGPA: %.2f%n", 8.5);
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     * Important:
     * - PrintWriter is character-oriented.
     * - It can wrap a Writer or write to a file.
     * - It does not throw ordinary IOExceptions from print methods.
     * - checkError() can be used to detect output errors.
     *
     *
     * 23. PRINTSTREAM VS PRINTWRITER
     *
     * PrintStream:
     * - Extends OutputStream.
     * - Writes bytes.
     * - Provides print and println methods.
     * - Encodes text into bytes.
     *
     * PrintWriter:
     * - Extends Writer.
     * - Writes character data.
     * - Provides print and println methods.
     *
     * Both provide formatted output.
     *
     * Choose based on the underlying I/O model and API requirements.
     *
     *
     * 24. AUTOFLUSH
     *
     * PrintStream and PrintWriter provide constructors
     * with an autoFlush option.
     *
     * When enabled, flushing occurs under documented conditions.
     *
     * For example, for PrintStream, automatic flushing occurs
     * when a byte array is written, println() is invoked,
     * or a newline character is written.
     *
     * PrintWriter's autoFlush behavior is tied to println(),
     * printf() or format() calls.
     *
     * Important:
     * AutoFlush does not mean every print() call automatically
     * flushes the output.
     *
     *
     * 25. WRAPPING STREAMS - DECORATOR APPROACH
     *
     * Java I/O frequently uses wrapper classes.
     *
     * A wrapper adds additional behavior around an existing
     * stream without changing the original stream's core role.
     *
     * Example:
     *
     * new BufferedInputStream(
     *     new FileInputStream("data.bin")
     * );
     *
     * Here:
     *
     * FileInputStream:
     * Reads bytes from the file.
     *
     * BufferedInputStream:
     * Adds buffering.
     *
     * Another example:
     *
     * new DataInputStream(
     *     new BufferedInputStream(
     *         new FileInputStream("student.dat")
     *     )
     * );
     *
     * Here:
     *
     * FileInputStream:
     * Reads file bytes.
     *
     * BufferedInputStream:
     * Adds buffering.
     *
     * DataInputStream:
     * Provides primitive data-reading methods.
     *
     * This wrapping approach is closely related to the
     * Decorator design pattern.
     *
     *
     * 26. COMMON COMBINATIONS
     *
     * A. BUFFERED BINARY INPUT:
     *
     * DataInputStream input =
     *     new DataInputStream(
     *         new BufferedInputStream(
     *             new FileInputStream("data.dat")
     *         )
     *     );
     *
     *
     * B. BUFFERED BINARY OUTPUT:
     *
     * DataOutputStream output =
     *     new DataOutputStream(
     *         new BufferedOutputStream(
     *             new FileOutputStream("data.dat")
     *         )
     *     );
     *
     *
     * C. BUFFERED TEXT INPUT:
     *
     * BufferedReader reader =
     *     new BufferedReader(
     *         new InputStreamReader(
     *             new FileInputStream("data.txt"),
     *             StandardCharsets.UTF_8
     *         )
     *     );
     *
     *
     * D. BUFFERED TEXT OUTPUT:
     *
     * BufferedWriter writer =
     *     new BufferedWriter(
     *         new OutputStreamWriter(
     *             new FileOutputStream("data.txt"),
     *             StandardCharsets.UTF_8
     *         )
     *     );
     *
     *
     * 27. BUFFERING AND PERFORMANCE
     *
     * Buffering can improve performance by reducing the number
     * of underlying I/O operations.
     *
     * However:
     *
     * - A larger buffer is not always better.
     * - Performance depends on workload and storage.
     * - Operating systems may also perform their own caching.
     * - Repeated benchmarking is needed for meaningful comparisons.
     *
     * Do not choose buffer sizes based only on assumptions.
     *
     *
     * 28. COMMON MISTAKES
     *
     * 1. Assuming BufferedInputStream reads only one byte
     *    from the underlying source for every read() call.
     *
     * 2. Forgetting to flush buffered output when it must be
     *    visible before closing.
     *
     * 3. Reading primitive values in a different order from
     *    the order in which they were written.
     *
     * 4. Assuming DataOutputStream writes human-readable text.
     *
     * 5. Confusing writeUTF() with standard UTF-8 encoding.
     *
     * 6. Assuming PrintStream and PrintWriter throw IOException
     *    from every print operation.
     *
     * 7. Assuming autoFlush flushes after every print() call.
     *
     * 8. Creating many unnecessary buffering layers.
     *
     * 9. Forgetting that closing a wrapper also closes its
     *    underlying stream.
     *
     *
     * 29. INTERVIEW QUESTIONS
     *
     * 1. What is buffered I/O?
     * 2. Why do we need buffering?
     * 3. What is BufferedInputStream?
     * 4. What is BufferedOutputStream?
     * 5. What is BufferedReader?
     * 6. What is BufferedWriter?
     * 7. What is the difference between buffering and reading
     *    into a byte array?
     * 8. What is mark() and reset()?
     * 9. What is DataInputStream?
     * 10. What is DataOutputStream?
     * 11. Which primitive types can data streams handle?
     * 12. Why must reading order match writing order?
     * 13. What is big-endian byte order?
     * 14. What is modified UTF-8?
     * 15. What is the difference between data streams and
     *     object streams?
     * 16. What is PrintStream?
     * 17. What is PrintWriter?
     * 18. What is the difference between PrintStream and PrintWriter?
     * 19. What is autoFlush?
     * 20. Why are Java I/O classes commonly wrapped?
     *
     *
     * 30. QUICK REVISION
     *
     * BufferedInputStream:
     * Adds buffering to byte input.
     *
     * BufferedOutputStream:
     * Adds buffering to byte output.
     *
     * BufferedReader:
     * Adds buffering to character input and provides readLine().
     *
     * BufferedWriter:
     * Adds buffering to character output and provides newLine().
     *
     * DataInputStream:
     * Reads primitive values from a binary input stream.
     *
     * DataOutputStream:
     * Writes primitive values to a binary output stream.
     *
     * PrintStream:
     * Provides convenient printing over byte output.
     *
     * PrintWriter:
     * Provides convenient printing over character output.
     *
     * flush():
     * Requests that buffered output be passed to the destination.
     *
     * Big-endian:
     * Stores the most significant byte first.
     *
     * Modified UTF-8:
     * Encoding format used by writeUTF() and readUTF().
     *
     * ================================================================
     *             END OF BUFFERED I/O AND DATA STREAMS
     * ================================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Buffered I/O and Data Streams , Happy Learning ..!");
    }
}
