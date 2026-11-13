
public class CharacterStreams {

    /*
     * ================================================================
     *                       CHARACTER STREAMS
     * ================================================================
     *
     * 1. WHAT ARE CHARACTER STREAMS?
     *
     * Character streams are used to read and write character data.
     *
     * They are primarily designed for text-based content.
     *
     * Examples:
     * - .txt files
     * - .csv files
     * - .java files
     * - .html files
     * - .xml files
     * - .json files
     * - Configuration files
     *
     * Unlike byte streams, character streams work with characters
     * rather than exposing raw bytes to the application.
     *
     * Main abstract classes:
     *
     * Reader -> Reads character data.
     * Writer -> Writes character data.
     *
     * Both belong to java.io.
     *
     *
     * 2. WHY DO WE NEED CHARACTER STREAMS?
     *
     * Consider a text file containing:
     *
     * Hello Vinay
     *
     * The file is physically stored as bytes.
     *
     * But while processing the file, we generally want to work
     * with meaningful characters rather than individual bytes.
     *
     * Character streams handle the conversion between bytes
     * and characters using a character encoding.
     *
     * For example:
     *
     * File bytes
     *     |
     *     v
     * Character decoding
     *     |
     *     v
     * Reader
     *     |
     *     v
     * Java characters
     *
     * When writing:
     *
     * Java characters
     *     |
     *     v
     * Writer
     *     |
     *     v
     * Character encoding
     *     |
     *     v
     * File bytes
     *
     *
     * 3. READER CLASS
     *
     * Reader is an abstract class in java.io.
     *
     * It provides the common abstraction for reading character
     * streams.
     *
     * Important implementations:
     *
     * FileReader
     * BufferedReader
     * InputStreamReader
     * CharArrayReader
     * StringReader
     * PipedReader
     * PushbackReader
     * LineNumberReader
     *
     * Example:
     *
     * Reader reader = new FileReader("data.txt");
     *
     * The Reader reference can point to different concrete
     * implementations.
     *
     *
     * 4. IMPORTANT READER METHODS
     *
     * A. read()
     *
     * Reads one character.
     *
     * Returns:
     * - Character value as an int.
     * -1 when the end of the stream is reached.
     *
     * Example:
     *
     * int data = reader.read();
     *
     *
     * B. read(char[] cbuf)
     *
     * Reads characters into a character array.
     *
     * Returns the number of characters actually read,
     * or -1 when EOF is reached before reading any character.
     *
     * Example:
     *
     * char[] buffer = new char[1024];
     * int count = reader.read(buffer);
     *
     *
     * C. read(char[] cbuf, int off, int len)
     *
     * Reads up to len characters into the array starting at
     * index off.
     *
     *
     * D. skip(long n)
     *
     * Attempts to skip characters.
     *
     *
     * E. ready()
     *
     * Checks whether the stream is ready to be read without
     * blocking.
     *
     * Important:
     * ready() does not guarantee that the next read will
     * return a character.
     *
     *
     * F. mark(int readAheadLimit)
     *
     * Marks the current position if the reader supports marking.
     *
     *
     * G. reset()
     *
     * Attempts to return to the marked position.
     *
     *
     * H. markSupported()
     *
     * Checks whether mark and reset are supported.
     *
     *
     * I. close()
     *
     * Closes the reader and releases associated resources.
     *
     *
     * 5. WRITER CLASS
     *
     * Writer is an abstract class in java.io.
     *
     * It provides the common abstraction for writing character
     * data.
     *
     * Important implementations:
     *
     * FileWriter
     * BufferedWriter
     * OutputStreamWriter
     * CharArrayWriter
     * StringWriter
     * PrintWriter
     * PipedWriter
     *
     *
     * 6. IMPORTANT WRITER METHODS
     *
     * A. write(int c)
     *
     * Writes one character.
     *
     * Example:
     *
     * writer.write('A');
     *
     * B. write(char[] cbuf)
     *
     * Writes a character array.
     *
     * C. write(char[] cbuf, int off, int len)
     *
     * Writes a specified portion of a character array.
     *
     * D. write(String str)
     *
     * Writes a String.
     *
     * E. write(String str, int off, int len)
     *
     * Writes a specified portion of a String.
     *
     * F. append(CharSequence csq)
     *
     * Appends a character sequence.
     *
     * G. flush()
     *
     * Requests that buffered output be passed to the destination.
     *
     * H. close()
     *
     * Closes the writer and releases associated resources.
     *
     *
     * 7. FILEREADER
     *
     * FileReader is a concrete Reader implementation.
     *
     * It reads character data from a file.
     *
     * Common constructors:
     *
     * FileReader(String fileName)
     *
     * FileReader(File file)
     *
     * FileReader(FileDescriptor fd)
     *
     * Java 11 and later also provide constructors accepting Charset:
     *
     * FileReader(String fileName, Charset charset)
     *
     * FileReader(File file, Charset charset)
     *
     * Example:
     *
     * try (FileReader reader =
     *          new FileReader("data.txt")) {
     *
     *     int data;
     *
     *     while ((data = reader.read()) != -1) {
     *         System.out.print((char) data);
     *     }
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     * Important:
     * - Reads text from a file.
     * - Uses a character encoding to decode bytes.
     * - Default-charset constructors use the JVM's default charset.
     *
     * Recommended:
     * Specify the required charset explicitly.
     *
     *
     * 8. FILEWRITER
     *
     * FileWriter is a concrete Writer implementation.
     *
     * It writes character data into a file.
     *
     * Common constructors:
     *
     * FileWriter(String fileName)
     *
     * FileWriter(String fileName, boolean append)
     *
     * FileWriter(File file)
     *
     * FileWriter(File file, boolean append)
     *
     * Java 11 and later also provide constructors accepting Charset.
     *
     * Example:
     *
     * try (FileWriter writer =
     *          new FileWriter("data.txt")) {
     *
     *     writer.write("Hello Vinay");
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     * Important:
     * - Creates the file if it does not exist.
     * - By default, truncates an existing file.
     * - Uses the default charset unless an explicit charset
     *   is supplied.
     *
     *
     * 9. APPEND MODE IN FILEWRITER
     *
     * FileWriter provides an append option.
     *
     * append = false:
     * Existing contents are truncated.
     *
     * append = true:
     * New characters are written at the end of the file.
     *
     * Example:
     *
     * try (FileWriter writer =
     *          new FileWriter("data.txt", true)) {
     *
     *     writer.write("\nWelcome to Java I/O");
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     *
     * 10. BUFFEREDREADER
     *
     * BufferedReader wraps another Reader and provides
     * character buffering.
     *
     * It is commonly used for efficient text reading.
     *
     * Its most useful method is:
     *
     * readLine()
     *
     * readLine() reads a line of text.
     *
     * It returns:
     * - The line without its line-termination characters.
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
     * 11. BUFFEREDWRITER
     *
     * BufferedWriter wraps another Writer and provides
     * character buffering.
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
     * 12. INPUTSTREAMREADER
     *
     * InputStreamReader is a bridge between byte streams
     * and character streams.
     *
     * It converts bytes into characters using a Charset.
     *
     * This is an important class because many input sources
     * provide bytes, while applications need characters.
     *
     * Example:
     *
     * try (InputStreamReader reader =
     *          new InputStreamReader(
     *              new FileInputStream("data.txt"),
     *              StandardCharsets.UTF_8)) {
     *
     *     int data;
     *
     *     while ((data = reader.read()) != -1) {
     *         System.out.print((char) data);
     *     }
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     * Explanation:
     *
     * FileInputStream:
     * Reads bytes from the file.
     *
     * InputStreamReader:
     * Decodes bytes into characters using UTF-8.
     *
     * Reader:
     * Provides character-oriented reading operations.
     *
     * Important:
     * InputStreamReader is useful when we need to specify
     * an encoding explicitly or read from a byte-based source.
     *
     *
     * 13. OUTPUTSTREAMWRITER
     *
     * OutputStreamWriter is a bridge between character streams
     * and byte streams.
     *
     * It converts characters into bytes using a Charset.
     *
     * Example:
     *
     * try (OutputStreamWriter writer =
     *          new OutputStreamWriter(
     *              new FileOutputStream("data.txt"),
     *              StandardCharsets.UTF_8)) {
     *
     *     writer.write("Hello Vinay");
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     * Explanation:
     *
     * Writer:
     * Accepts character data.
     *
     * OutputStreamWriter:
     * Encodes characters into bytes using UTF-8.
     *
     * FileOutputStream:
     * Writes bytes into the file.
     *
     *
     * 14. CHARACTER ENCODING
     *
     * Computers store file contents as bytes.
     *
     * Java Strings represent text using UTF-16 code units.
     *
     * A character encoding defines how text is converted
     * between characters and bytes.
     *
     * Common charsets:
     *
     * UTF-8
     * UTF-16
     * ISO-8859-1
     * US-ASCII
     *
     * Example:
     *
     * StandardCharsets.UTF_8
     *
     * Java provides the StandardCharsets class to access
     * commonly used standard charsets.
     *
     * Example:
     *
     * StandardCharsets.UTF_8
     * StandardCharsets.UTF_16
     * StandardCharsets.ISO_8859_1
     * StandardCharsets.US_ASCII
     *
     * Important:
     * UTF-8 uses a variable number of bytes per Unicode code point.
     *
     * A Java char is a 16-bit UTF-16 code unit, not necessarily
     * a complete Unicode code point.
     *
     * Some Unicode characters require two char values
     * (a surrogate pair).
     *
     *
     * 15. WHAT HAPPENS WHEN THE WRONG CHARSET IS USED?
     *
     * Suppose a file is encoded using UTF-8.
     *
     * If it is read using an incompatible charset:
     *
     * - Characters may become corrupted.
     * - Replacement characters may appear.
     * - Some text may be interpreted incorrectly.
     *
     * This problem is commonly called mojibake.
     *
     * Recommended practice:
     * Use the same explicitly chosen charset when writing
     * and reading text.
     *
     *
     * 16. DEFAULT CHARSET
     *
     * Java provides:
     *
     * Charset.defaultCharset()
     *
     * It returns the default charset used by the JVM.
     *
     * Important:
     * The default charset is environment-dependent.
     *
     * JEP 400:
     * Starting with Java 18, UTF-8 became the default charset
     * for standard Java APIs, subject to documented compatibility
     * and environment considerations.
     *
     * Do not depend on the default charset when a file format
     * specifies a particular encoding.
     *
     *
     * 17. STRINGREADER
     *
     * StringReader reads character data directly from a String.
     *
     * It behaves like a Reader but does not need a physical file.
     *
     * Example:
     *
     * String text = "Hello Java";
     *
     * try (StringReader reader = new StringReader(text)) {
     *
     *     int data;
     *
     *     while ((data = reader.read()) != -1) {
     *         System.out.print((char) data);
     *     }
     *
     * }
     *
     * Common use cases:
     * - Testing text-processing logic.
     * - Reading text already available in memory.
     * - Supplying String content to APIs expecting Reader.
     *
     *
     * 18. STRINGWRITER
     *
     * StringWriter writes character data into an internal
     * StringBuffer.
     *
     * It does not write directly to a file.
     *
     * Example:
     *
     * try (StringWriter writer = new StringWriter()) {
     *
     *     writer.write("Hello ");
     *     writer.write("Java");
     *
     *     String result = writer.toString();
     *
     *     System.out.println(result);
     * }
     *
     * Output:
     * Hello Java
     *
     * Common use cases:
     * - Building text in memory.
     * - Generating reports.
     * - Capturing textual output.
     *
     *
     * 19. CHARARRAYREADER
     *
     * CharArrayReader reads character data from a char array.
     *
     * Example:
     *
     * char[] data = {'J', 'a', 'v', 'a'};
     *
     * try (CharArrayReader reader =
     *          new CharArrayReader(data)) {
     *
     *     int value;
     *
     *     while ((value = reader.read()) != -1) {
     *         System.out.print((char) value);
     *     }
     * }
     *
     *
     * 20. CHARARRAYWRITER
     *
     * CharArrayWriter writes character data into an internal
     * character array.
     *
     * Example:
     *
     * try (CharArrayWriter writer = new CharArrayWriter()) {
     *
     *     writer.write("Java");
     *
     *     char[] result = writer.toCharArray();
     *
     *     System.out.println(result);
     * }
     *
     * Important methods:
     *
     * toCharArray()
     *     Returns a copy of the character data.
     *
     * toString()
     *     Returns the accumulated text.
     *
     * size()
     *     Returns the number of characters written.
     *
     * reset()
     *     Resets the current character count.
     *
     *
     * 21. PUSHBACKREADER
     *
     * PushbackReader allows characters that have already been
     * read to be pushed back into the reader.
     *
     * The next read can return those characters again.
     *
     * Example:
     *
     * try (PushbackReader reader =
     *          new PushbackReader(
     *              new StringReader("Java"))) {
     *
     *     int first = reader.read();
     *
     *     reader.unread(first);
     *
     *     int again = reader.read();
     *
     *     System.out.println((char) again);
     * }
     *
     * Common use cases:
     * - Parsing.
     * - Looking ahead at characters.
     * - Returning characters to the input.
     *
     *
     * 22. LINENUMBERREADER
     *
     * LineNumberReader is a BufferedReader subclass.
     *
     * It keeps track of line numbers while reading text.
     *
     * Important methods:
     *
     * getLineNumber()
     *     Returns the current line number.
     *
     * setLineNumber(int lineNumber)
     *     Sets the current line number.
     *
     * Example:
     *
     * try (LineNumberReader reader =
     *          new LineNumberReader(
     *              new FileReader("data.txt"))) {
     *
     *     String line;
     *
     *     while ((line = reader.readLine()) != null) {
     *         System.out.println(
     *             reader.getLineNumber() + ": " + line
     *         );
     *     }
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     * Note:
     * The line number is maintained by the reader.
     * It is not necessarily derived from a physical line-number
     * index stored inside the file.
     *
     *
     * 23. PRINTWRITER
     *
     * PrintWriter provides convenient methods for writing
     * formatted text.
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
     *          new PrintWriter("student.txt")) {
     *
     *     writer.println("Vinay");
     *     writer.println(22);
     *     writer.printf("CGPA: %.2f%n", 8.5);
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     * Important:
     * - PrintWriter does not throw ordinary IOExceptions from
     *   its write, print and println methods.
     * - Use checkError() to check whether an output error occurred.
     * - Constructors that open files can throw IOException.
     *
     *
     * 24. READER VS INPUTSTREAM
     *
     * InputStream:
     * - Reads bytes.
     * - Suitable for binary data.
     * - read() returns an integer representing a byte or -1.
     *
     * Reader:
     * - Reads character data.
     * - Suitable for text processing.
     * - read() returns an integer representing a UTF-16 code unit
     *   or -1.
     *
     *
     * 25. WRITER VS OUTPUTSTREAM
     *
     * OutputStream:
     * - Writes bytes.
     * - Suitable for binary output.
     *
     * Writer:
     * - Writes character data.
     * - Converts characters into bytes when connected to
     *   a byte-based destination through an encoding layer.
     *
     *
     * 26. FILE READER VS INPUTSTREAMREADER
     *
     * FileReader:
     * - Designed specifically for reading characters from files.
     * - Internally uses byte-to-character decoding.
     * - Java 11+ allows an explicit Charset constructor.
     *
     * InputStreamReader:
     * - Converts any InputStream into a Reader.
     * - Supports explicit charset selection.
     * - Useful for files, network input and other byte sources.
     *
     *
     * 27. FILE WRITER VS OUTPUTSTREAMWRITER
     *
     * FileWriter:
     * - Designed specifically for writing characters to files.
     * - Java 11+ supports explicit Charset constructors.
     *
     * OutputStreamWriter:
     * - Converts a Writer's characters into bytes.
     * - Can wrap any OutputStream.
     * - Supports explicit charset selection.
     *
     *
     * 28. TRY-WITH-RESOURCES
     *
     * Readers and writers should be closed after use.
     *
     * Example:
     *
     * try (BufferedReader reader =
     *          new BufferedReader(
     *              new FileReader("data.txt"))) {
     *
     *     System.out.println(reader.readLine());
     *
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     *
     * Java automatically closes the reader when the try block
     * finishes, including when an exception occurs.
     *
     * Reader and Writer implement Closeable, which extends
     * AutoCloseable.
     *
     *
     * 29. COMMON MISTAKES
     *
     * 1. Using FileReader without considering character encoding.
     *
     * 2. Assuming a Java char always represents a complete
     *    Unicode code point.
     *
     * 3. Forgetting that readLine() returns null at EOF.
     *
     * 4. Forgetting to close readers and writers.
     *
     * 5. Using character streams for binary files.
     *
     * 6. Assuming flush() closes a writer.
     *
     * 7. Assuming PrintWriter automatically reports every
     *    output failure through IOException.
     *
     * 8. Assuming BufferedReader reads only one character at a time.
     *
     * 9. Mixing incompatible encodings while reading and writing.
     *
     *
     * 30. INTERVIEW QUESTIONS
     *
     * 1. What are character streams?
     * 2. What is Reader?
     * 3. What is Writer?
     * 4. What is the difference between byte and character streams?
     * 5. What is FileReader?
     * 6. What is FileWriter?
     * 7. What is BufferedReader?
     * 8. What is the purpose of readLine()?
     * 9. What is BufferedWriter?
     * 10. What is the purpose of newLine()?
     * 11. What is InputStreamReader?
     * 12. What is OutputStreamWriter?
     * 13. What is character encoding?
     * 14. What is UTF-8?
     * 15. What is the difference between FileReader and
     *     InputStreamReader?
     * 16. What is StringReader?
     * 17. What is StringWriter?
     * 18. What is PushbackReader?
     * 19. What is LineNumberReader?
     * 20. What is PrintWriter?
     * 21. Why does Reader.read() return int?
     * 22. What is mojibake?
     * 23. What is the default charset in modern Java?
     *
     *
     * 31. QUICK REVISION
     *
     * Reader:
     * Abstract base class for reading characters.
     *
     * Writer:
     * Abstract base class for writing characters.
     *
     * FileReader:
     * Reads text from a file.
     *
     * FileWriter:
     * Writes text into a file.
     *
     * BufferedReader:
     * Provides buffered reading and readLine().
     *
     * BufferedWriter:
     * Provides buffered writing and newLine().
     *
     * InputStreamReader:
     * Converts bytes into characters.
     *
     * OutputStreamWriter:
     * Converts characters into bytes.
     *
     * StringReader:
     * Reads text from a String.
     *
     * StringWriter:
     * Writes text into an in-memory buffer.
     *
     * PrintWriter:
     * Provides convenient formatted text output.
     *
     * Charset:
     * Defines how characters are encoded into bytes and decoded
     * back into characters.
     *
     * UTF-8:
     * A widely used variable-width Unicode encoding.
     *
     * ================================================================
     *                    END OF CHARACTER STREAMS
     * ================================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Character Streams , Happy Learning ..!");
    }
}
