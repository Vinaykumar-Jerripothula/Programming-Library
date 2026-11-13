

 class IO {

    /*
    ============================================================
                  JAVA INPUT AND OUTPUT (I/O)
    ============================================================


    ============================================================
                    1. INTRODUCTION TO JAVA I/O
    ============================================================

    I/O stands for Input and Output.

    Input:
        Receiving data from an external source.

    Output:
        Sending data to an external destination.

    Examples:

        Input:
            Keyboard
            User-provided data
            Command-line arguments

        Output:
            Console
            Error messages
            Program results

    Java provides I/O functionality through packages such as:

        java.io
        java.util
        java.nio

    This file focuses on console I/O and stream fundamentals.
    File handling is covered separately.


    ============================================================
                    2. STANDARD I/O STREAMS
    ============================================================

    Java provides three standard I/O streams through
    the System class.

    1. System.in
       Standard input stream.

    2. System.out
       Standard output stream.

    3. System.err
       Standard error stream.

    A. System.in

    - Used to receive input.
    - Its type is InputStream.
    - Usually connected to keyboard input.

        int data = System.in.read();

    read() reads one byte and returns an int.

    It returns -1 when the stream reaches its end.

    B. System.out

    - Used to display normal output.
    - Its type is PrintStream.
    - Usually connected to the console.

        System.out.println("Hello Java");

    C. System.err

    - Used to display error messages.
    - Its type is PrintStream.
    - Usually connected to the console's error output.

        System.err.println("Invalid input");

    IMPORTANT:

    System.in, System.out and System.err are static fields
    of the System class.

    System.out and System.err are separate output streams,
    although both may appear in the same terminal.

    Their destinations can be redirected.


    ============================================================
                    3. PRINTSTREAM AND PRINTWRITER
    ============================================================

    A. PrintStream

    PrintStream is a class used to print formatted
    representations of values to an output stream.

    System.out and System.err are PrintStream objects.

    Common methods:

        print()
        println()
        printf()
        format()
        write()
        flush()
        close()

    Example:

        System.out.print("Java ");
        System.out.println("Programming");
        System.out.printf("Age: %d%n", 22);

    B. PrintWriter

    PrintWriter is a character-oriented output class.

    It provides convenient methods to write text.

    Common methods:

        print()
        println()
        printf()
        format()
        write()
        flush()
        close()

    Example:

        PrintWriter writer =
            new PrintWriter(System.out, true);

        writer.println("Hello Java");

    The second argument enables automatic flushing
    after println(), printf() or format().

    IMPORT:

        import java.io.PrintWriter;

    PrintStream vs PrintWriter:

    PrintStream:
        - Byte-oriented output stream.
        - Provides convenient text-printing methods.
        - Used by System.out and System.err.

    PrintWriter:
        - Character-oriented writer.
        - Designed for writing characters and text.
        - Does not directly expose byte-oriented operations.

    IMPORTANT:

    Both classes generally suppress I/O exceptions during
    printing and maintain an internal error state.

    checkError() can be used to check whether an error
    occurred.


    ============================================================
                    4. OUTPUT METHODS
    ============================================================

    A. print()

    Prints the supplied value without automatically
    adding a line terminator.

        System.out.print("Hello");
        System.out.print("Java");

    Output:

        HelloJava


    B. println()

    Prints the supplied value followed by a line terminator.

        System.out.println("Hello");
        System.out.println("Java");

    Output:

        Hello
        Java

    println() without arguments prints a line terminator.


    C. printf()

    Prints formatted output using format specifiers.

    Syntax:

        System.out.printf(format, arguments);

    Example:

        String name = "Vinay";
        int age = 22;

        System.out.printf("Name: %s, Age: %d%n", name, age);

    Output:

        Name: Vinay, Age: 22

    D. IMPORTANT FORMAT SPECIFIERS

    Specifier       Meaning
    -----------------------------------
    %d              Decimal integer
    %f              Floating-point number
    %s              String representation
    %c              Character
    %b              Boolean representation
    %x              Hexadecimal integer
    %o              Octal integer
    %e              Scientific notation
    %n              Platform-specific line separator
    %%              Literal percentage sign

    Examples:

        System.out.printf("%d%n", 100);
        System.out.printf("%f%n", 12.5);
        System.out.printf("%s%n", "Java");
        System.out.printf("%c%n", 'A');
        System.out.printf("%b%n", true);

    E. WIDTH AND PRECISION

    Width specifies the minimum field width.

        System.out.printf("%10s%n", "Java");

    The output occupies at least 10 character positions.

    Precision for floating-point values controls
    the number of digits after the decimal point.

        System.out.printf("%.2f%n", 12.3456);

    Output:

        12.35

    Combining width and precision:

        System.out.printf("%8.2f%n", 12.3456);

    F. LEFT ALIGNMENT

        System.out.printf("%-10s%n", "Java");

    The minus sign left-aligns the value within the field.

    G. ZERO PADDING

        System.out.printf("%05d%n", 42);

    Output:

        00042

    H. LOCALE

    Formatting can depend on locale.

        System.out.printf("%.2f%n", 1234.56);

    Locale-specific decimal and grouping separators
    may differ.

    Locale can be specified explicitly:

        System.out.printf(
            java.util.Locale.US,
            "%,.2f%n",
            1234567.89
        );

    IMPORTANT:

    - print() does not append a line terminator.
    - println() appends a line terminator.
    - printf() supports formatted output.
    - %n is preferred over hardcoding "\n" for
      platform-specific line separation.
    - printf() returns a PrintStream, allowing chaining.


    ============================================================
                    5. SCANNER CLASS
    ============================================================

    Scanner is a class in the java.util package.

    It is used to read and parse input from different sources,
    including keyboard input, strings and input streams.

    Import:

        import java.util.Scanner;

    Creating a Scanner object:

        Scanner sc = new Scanner(System.in);

    System.in provides the input stream.

    Scanner reads and parses the input into tokens.

    A. COMMON SCANNER METHODS

    Method              Purpose
    --------------------------------------------
    next()              Reads the next token
    nextLine()          Reads the remaining line
    nextInt()           Reads an int token
    nextLong()          Reads a long token
    nextFloat()         Reads a float token
    nextDouble()        Reads a double token
    nextBoolean()       Reads a boolean token
    nextByte()          Reads a byte token
    nextShort()         Reads a short token
    hasNext()           Checks for another token
    hasNextLine()       Checks for another line
    hasNextInt()        Checks whether next token is an int
    useDelimiter()      Changes the delimiter
    close()             Closes the Scanner

    B. EXAMPLE: READING DIFFERENT DATA TYPES

        import java.util.Scanner;

        class Main {

            public static void main(String[] args) {

                Scanner sc = new Scanner(System.in);

                System.out.print("Enter name: ");
                String name = sc.nextLine();

                System.out.print("Enter age: ");
                int age = sc.nextInt();

                System.out.print("Enter salary: ");
                double salary = sc.nextDouble();

                System.out.println("Name: " + name);
                System.out.println("Age: " + age);
                System.out.println("Salary: " + salary);

                sc.close();
            }
        }

    C. next() VS nextLine()

    next():
        Reads the next token until a delimiter is encountered.
        By default, whitespace separates tokens.

    nextLine():
        Reads the remaining contents of the current line
        and advances to the next line.

    Example input:

        Vinay Kumar

    Code:

        String first = sc.next();
        String second = sc.next();

    Result:

        first  -> Vinay
        second -> Kumar

    Using:

        String fullName = sc.nextLine();

    Result:

        fullName -> Vinay Kumar

    D. THE nextInt() AND nextLine() PROBLEM

    Consider:

        int age = sc.nextInt();
        String name = sc.nextLine();

    Input:

        22
        Vinay

    nextInt() reads the integer token but does not consume
    the line separator after that token.

    nextLine() then consumes the remaining part of that
    current line, which may be empty.

    Correct approach:

        int age = sc.nextInt();
        sc.nextLine();

        String name = sc.nextLine();

    The additional nextLine() consumes the remainder
    of the integer's line.

    IMPORTANT:

    This issue occurs when a line-based read follows
    a token-based read and an unconsumed line remainder
    is present.


    E. DELIMITERS

    Scanner uses whitespace as its default delimiter.

    Example:

        Scanner sc = new Scanner("Java Python C++");

        System.out.println(sc.next());

    Output:

        Java

    Custom delimiter:

        Scanner sc = new Scanner("Java,Python,C++");

        sc.useDelimiter(",");

        while (sc.hasNext()) {
            System.out.println(sc.next());
        }

    Output:

        Java
        Python
        C++

    useDelimiter() accepts a regular expression.

    F. INPUT VALIDATION

    Scanner provides hasNextXxx() methods to check
    whether the next token can be interpreted as a
    particular data type.

    Example:

        if (sc.hasNextInt()) {

            int number = sc.nextInt();

            System.out.println(number);

        } else {

            System.out.println("Invalid integer");
        }

    Calling nextInt() when the next token is not a valid
    integer can result in InputMismatchException.

    G. COMMON SCANNER EXCEPTIONS

    InputMismatchException:
        The next token does not match the expected type.

    NoSuchElementException:
        No more input is available when a token or line
        is requested.

    IllegalStateException:
        Scanner has already been closed.

    H. CLOSING SCANNER

        sc.close();

    Closing a Scanner also closes its underlying source
    if that source implements Closeable.

    IMPORTANT:

    If a Scanner wraps System.in, closing it closes
    System.in as well.

    Avoid closing a shared System.in Scanner prematurely,
    especially when more input is required later.

    I. SCANNER PERFORMANCE

    Scanner performs token parsing and regular-expression
    delimiter processing.

    It is convenient for ordinary input but can be slower
    than buffered input for large amounts of data.


    ============================================================
                    6. BUFFEREDREADER
    ============================================================

    BufferedReader is a character-oriented class in
    java.io used to read text efficiently.

    It buffers characters to reduce repeated underlying
    read operations.

    Commonly used with:

        InputStreamReader

    A. INPUTSTREAMREADER

    InputStreamReader acts as a bridge between byte streams
    and character streams.

    It converts bytes into characters using a charset.

    B. CREATING BUFFEREDREADER

        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );

    Imports:

        import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.io.IOException;

    C. READING INPUT

        String name = br.readLine();

    readLine():

    - Reads a line of text.
    - Returns the line without its line terminator.
    - Returns null when the end of the stream is reached.

    D. EXAMPLE

        import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.io.IOException;

        class Main {

            public static void main(String[] args)
                    throws IOException {

                BufferedReader br = new BufferedReader(
                    new InputStreamReader(System.in)
                );

                System.out.print("Enter name: ");
                String name = br.readLine();

                System.out.print("Enter age: ");
                int age = Integer.parseInt(br.readLine());

                System.out.println(name);
                System.out.println(age);
            }
        }

    E. READING NUMBERS

    BufferedReader reads text as String.

    Convert the String into the required data type
    using parsing methods.

        int number = Integer.parseInt(br.readLine());

        long value = Long.parseLong(br.readLine());

        double price = Double.parseDouble(br.readLine());

        float percentage = Float.parseFloat(br.readLine());

        boolean active = Boolean.parseBoolean(br.readLine());

    Invalid numeric input can cause NumberFormatException.

    F. BUFFEREDREADER WITH EXPLICIT CHARSET

    InputStreamReader can be created with an explicit
    charset.

        BufferedReader br = new BufferedReader(
            new InputStreamReader(
                System.in,
                java.nio.charset.StandardCharsets.UTF_8
            )
        );

    This avoids depending on the default charset.

    G. IMPORTANT BUFFEREDREADER METHODS

    Method              Purpose
    --------------------------------------------
    read()              Reads one character
    readLine()          Reads one line
    ready()             Checks whether reading can proceed
    skip()              Skips characters
    mark()              Marks a position
    reset()             Returns to a marked position
    close()             Closes the reader

    read() returns an int representing a character,
    or -1 when the end of the stream is reached.

    H. EXCEPTION HANDLING

    readLine() can throw IOException.

    Handle it using:

        try-catch

    or:

        throws IOException

    I. TRY-WITH-RESOURCES

    BufferedReader implements AutoCloseable.

    It can be used with try-with-resources:

        try (
            BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
            )
        ) {

            String input = br.readLine();

            System.out.println(input);

        } catch (IOException e) {

            System.err.println(e.getMessage());
        }

    IMPORTANT:

    Closing this BufferedReader also closes System.in
    through its underlying reader.


    ============================================================
                    7. SCANNER VS BUFFEREDREADER
    ============================================================

    Feature             Scanner       BufferedReader
    --------------------------------------------------
    Package             java.util     java.io
    Reading             Tokens and    Character data
                        lines
    Data conversion     Built-in      Manual parsing
    read method         nextInt(),    readLine()
                        nextDouble()
    Buffering           Uses internal Uses character
                        buffering     buffering
    Delimiters          Supports      No token delimiter
                        delimiters    processing
    Convenience         High          Requires parsing
    Performance         Generally     Generally faster
                        slower        for large text input

    WHEN TO USE SCANNER:

    - Small console programs.
    - Simple user input.
    - Convenient parsing of different data types.

    WHEN TO USE BUFFEREDREADER:

    - Large text input.
    - Competitive programming input.
    - When line-based reading and manual parsing are suitable.

    IMPORTANT:

    BufferedReader is not automatically better for every
    application. Choose based on the input requirements.


    ============================================================
                    8. COMMAND-LINE ARGUMENTS
    ============================================================

    Command-line arguments are values supplied to a Java
    program when it is launched.

    They are received through the String array parameter
    of the main() method.

    Standard main method:

        public static void main(String[] args)

    args:
        An array containing command-line arguments.

    Example:

        class Main {

            public static void main(String[] args) {

                System.out.println(args[0]);
                System.out.println(args[1]);
            }
        }

    Run:

        java Main Vinay 22

    Output:

        Vinay
        22

    A. ACCESSING ARGUMENTS

        args[0] -> First argument
        args[1] -> Second argument
        args[2] -> Third argument

    Array indexing starts from zero.

    B. NUMBER OF ARGUMENTS

        args.length

    Example:

        for (int i = 0; i < args.length; i++) {

            System.out.println(args[i]);
        }

    C. CONVERTING COMMAND-LINE ARGUMENTS

    All command-line arguments are received as Strings.

        int age = Integer.parseInt(args[0]);

        double salary = Double.parseDouble(args[1]);

    Invalid numeric input can cause NumberFormatException.

    D. IMPORTANT EDGE CASE

    Accessing an index that does not exist causes
    ArrayIndexOutOfBoundsException.

    Always validate the number of arguments.

        if (args.length >= 2) {

            System.out.println(args[0]);
            System.out.println(args[1]);

        } else {

            System.out.println("Insufficient arguments");
        }

    E. COMMAND-LINE ARGUMENTS VS CONSOLE INPUT

    Command-line arguments:
        Supplied when launching the program.

    Scanner / BufferedReader:
        Read input while the program is running.

    Example:

        java Main Vinay 22

    Here, Vinay and 22 are command-line arguments.


    ============================================================
                    9. IMPORTANT RULES AND REMINDERS
    ============================================================

    1. System.in is an InputStream.

    2. System.out and System.err are PrintStream objects.

    3. print() does not automatically move to a new line.

    4. println() appends a line terminator.

    5. printf() supports formatted output.

    6. Scanner belongs to java.util.

    7. BufferedReader belongs to java.io.

    8. Scanner can directly parse primitive values.

    9. BufferedReader reads text and requires parsing
       for numeric values.

    10. Scanner's default delimiter is whitespace.

    11. next() reads a token, whereas nextLine()
        reads the remaining line.

    12. nextInt() followed by nextLine() may require
        consuming the remaining line first.

    13. Scanner.close() closes its underlying source.

    14. BufferedReader.readLine() returns null at EOF.

    15. Integer.parseInt() can throw NumberFormatException.

    16. BufferedReader.readLine() can throw IOException.

    17. Command-line arguments are received as Strings.

    18. args[0] is the first command-line argument.

    19. Validate args.length before accessing array elements.

    20. Avoid closing shared System.in readers prematurely.


    ============================================================
                    10. INTERVIEW QUESTIONS
    ============================================================

    1. What is Java I/O?
       The mechanism for receiving input and producing output.

    2. What are the three standard I/O streams?
       System.in, System.out and System.err.

    3. What is the type of System.in?
       InputStream.

    4. What is the type of System.out?
       PrintStream.

    5. What is the difference between print() and println()?
       println() appends a line terminator.

    6. What is printf()?
       A method used for formatted output.

    7. What does %n represent?
       A platform-specific line separator.

    8. What is Scanner?
       A class used to read and parse input.

    9. What is the difference between next() and nextLine()?
       next() reads a token; nextLine() reads the remaining
       contents of a line.

    10. Why does nextInt() followed by nextLine()
        sometimes return an empty String?
        Because nextInt() leaves the line terminator
        or remaining line content unread.

    11. What is BufferedReader?
        A buffered character-oriented reader.

    12. Why is InputStreamReader used with BufferedReader?
        It converts bytes into characters.

    13. What does readLine() return at EOF?
        null.

    14. How do you convert a String into an integer?
        Integer.parseInt().

    15. What is the difference between Scanner and
        BufferedReader?
        Scanner provides convenient token parsing;
        BufferedReader provides buffered character reading.

    16. What happens when Scanner closes System.in?
        The underlying standard input stream is closed.

    17. What are command-line arguments?
        Values supplied when launching a program.

    18. What is the type of args in main()?
        String[].

    19. What happens when an invalid integer is passed
        to nextInt()?
        InputMismatchException.

    20. What happens when an invalid number is passed
        to Integer.parseInt()?
        NumberFormatException.


    ============================================================
                    11. PART 1 REVISION CHECKLIST
    ============================================================

    [ ] Understand Java input and output.
    [ ] Know System.in, System.out and System.err.
    [ ] Understand PrintStream and PrintWriter.
    [ ] Know print(), println() and printf().
    [ ] Understand format specifiers.
    [ ] Know Scanner and its input methods.
    [ ] Understand next() vs nextLine().
    [ ] Understand the nextInt() and nextLine() issue.
    [ ] Know Scanner delimiters and input validation.
    [ ] Understand BufferedReader.
    [ ] Know InputStreamReader.
    [ ] Understand readLine() and EOF.
    [ ] Know how to parse numeric input.
    [ ] Compare Scanner and BufferedReader.
    [ ] Understand command-line arguments.
    [ ] Know common exceptions and resource-closing rules.


    
    /*
    ============================================================
                  JAVA INPUT AND OUTPUT (I/O)
    ============================================================

    PART 2: JAVA I/O STREAMS


    ============================================================
                    1. WHAT IS A STREAM?
    ============================================================

    A stream is a sequence of data flowing between a
    source and a destination.

    Input stream:
        Transfers data from a source into a program.

    Output stream:
        Transfers data from a program to a destination.

    Example:

        Keyboard -> Input Stream -> Java Program

        Java Program -> Output Stream -> Console

    Java I/O streams are primarily available through
    the java.io package.

    Two major stream categories:

                        I/O Streams
                             |
                 +-----------+-----------+
                 |                       |
             Byte Streams          Character Streams
                 |                       |
             InputStream               Reader
             OutputStream              Writer

    Byte streams work with bytes.

    Character streams work with character data.


    ============================================================
                    2. BYTE STREAMS
    ============================================================

    Byte streams are used to read and write raw bytes.

    They are suitable for binary data such as:

        Images
        Audio
        Video
        Binary data
        Encoded text bytes

    Two main abstract classes:

        InputStream
        OutputStream

    A. INPUTSTREAM

    InputStream is the abstract superclass for classes
    that read bytes.

    Important methods:

        read()
        read(byte[])
        read(byte[], offset, length)
        available()
        skip()
        close()

    read():

        int value = input.read();

    Reads one byte and returns it as an int.

    Returns -1 when the end of the stream is reached.

    read(byte[]):

        byte[] buffer = new byte[1024];

        int count = input.read(buffer);

    Reads bytes into the supplied array.

    Returns the number of bytes read, or -1 at EOF.

    IMPORTANT:

    read(byte[]) is not guaranteed to fill the entire array.
    Always use the returned count.

    B. OUTPUTSTREAM

    OutputStream is the abstract superclass for classes
    that write bytes.

    Important methods:

        write(int)
        write(byte[])
        write(byte[], offset, length)
        flush()
        close()

    Example:

        output.write(65);

    Writes the byte value 65.

    Another example:

        byte[] data = {65, 66, 67};

        output.write(data);

    The bytes represent ASCII characters A, B and C.

    C. BYTE ARRAY STREAMS

    ByteArrayInputStream and ByteArrayOutputStream allow
    byte-based input and output using memory buffers.

    ByteArrayInputStream:

        Reads bytes from an internal byte array.

    Example:

        byte[] data = {10, 20, 30};

        ByteArrayInputStream input =
            new ByteArrayInputStream(data);

        int value;

        while ((value = input.read()) != -1) {
            System.out.println(value);
        }

    ByteArrayOutputStream:

        Writes bytes into an expandable memory buffer.

    Example:

        ByteArrayOutputStream output =
            new ByteArrayOutputStream();

        output.write(65);
        output.write(66);
        output.write(67);

        byte[] result = output.toByteArray();

    D. IMPORTANT BYTE STREAM CLASSES

    InputStream subclasses:

        ByteArrayInputStream
        BufferedInputStream
        DataInputStream
        ObjectInputStream
        PipedInputStream

    OutputStream subclasses:

        ByteArrayOutputStream
        BufferedOutputStream
        DataOutputStream
        ObjectOutputStream
        PipedOutputStream

    FileInputStream and FileOutputStream are also byte
    stream implementations, but file operations are
    covered in the separate File IO topic.


    ============================================================
                    3. CHARACTER STREAMS
    ============================================================

    Character streams are designed for reading and writing
    character data.

    They are suitable for text.

    Two main abstract classes:

        Reader
        Writer

    A. READER

    Reader is the abstract superclass for character input.

    Important methods:

        read()
        read(char[])
        read(char[], offset, length)
        skip()
        ready()
        mark()
        reset()
        close()

    Example:

        int character = reader.read();

    Reads one character and returns its value as an int.

    Returns -1 when the end of the stream is reached.

    B. WRITER

    Writer is the abstract superclass for character output.

    Important methods:

        write(int)
        write(char[])
        write(String)
        append()
        flush()
        close()

    Example:

        writer.write("Hello Java");

    Writes character data.

    C. STRING READER AND STRING WRITER

    StringReader:

        Reads character data from a String.

    Example:

        StringReader reader =
            new StringReader("Hello Java");

        int ch;

        while ((ch = reader.read()) != -1) {
            System.out.print((char) ch);
        }

    StringWriter:

        Writes character data into a String buffer.

    Example:

        StringWriter writer = new StringWriter();

        writer.write("Hello ");
        writer.write("Java");

        String result = writer.toString();

    D. IMPORTANT CHARACTER STREAM CLASSES

    Reader subclasses:

        BufferedReader
        InputStreamReader
        StringReader
        CharArrayReader
        PipedReader

    Writer subclasses:

        BufferedWriter
        OutputStreamWriter
        PrintWriter
        StringWriter
        CharArrayWriter
        PipedWriter

    FileReader and FileWriter are also character stream
    implementations, but file operations are covered
    separately.


    ============================================================
                    4. BYTE STREAMS VS CHARACTER STREAMS
    ============================================================

    Feature             Byte Streams       Character Streams
    ------------------------------------------------------------
    Base classes        InputStream        Reader
                         OutputStream       Writer
    Unit                 Bytes              Characters
    Data                 Raw bytes          Character data
    Suitable for         Binary data        Text
    Encoding             No character       Uses character
                         decoding layer     encoding/decoding
    Examples             ByteArrayInput     StringReader
                         Stream             StringWriter

    IMPORTANT:

    - Byte streams do not interpret bytes as characters.
    - Character streams handle character encoding and
      decoding through appropriate implementations.
    - Do not use character streams for arbitrary binary data.
    - Do not assume one character always occupies one byte.

    For text, choose a character stream with an appropriate
    charset.

    For binary data, use byte streams.


    ============================================================
                    5. BUFFERED STREAMS
    ============================================================

    Buffering means temporarily storing data in memory
    before reading or writing it.

    Buffered streams reduce repeated interactions with
    the underlying stream.

    A. BUFFEREDINPUTSTREAM

    BufferedInputStream adds buffering to byte input.

    Example:

        BufferedInputStream input =
            new BufferedInputStream(
                new ByteArrayInputStream(
                    new byte[]{10, 20, 30}
                )
            );

        int value;

        while ((value = input.read()) != -1) {
            System.out.println(value);
        }

    B. BUFFEREDOUTPUTSTREAM

    BufferedOutputStream adds buffering to byte output.

    Example:

        ByteArrayOutputStream target =
            new ByteArrayOutputStream();

        BufferedOutputStream output =
            new BufferedOutputStream(target);

        output.write(65);
        output.write(66);

        output.flush();

        byte[] result = target.toByteArray();

    flush() ensures buffered output is passed to the
    underlying stream.

    C. BUFFEREDREADER

    BufferedReader adds buffering to character input.

    It also provides readLine().

    Example:

        BufferedReader reader =
            new BufferedReader(
                new StringReader("Java\nPython")
            );

        String line;

        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

    D. BUFFEREDWRITER

    BufferedWriter adds buffering to character output.

    It provides newLine() to write the platform-specific
    line separator.

    Example:

        StringWriter target = new StringWriter();

        BufferedWriter writer =
            new BufferedWriter(target);

        writer.write("Java");
        writer.newLine();
        writer.write("Python");

        writer.flush();

        System.out.println(target.toString());

    IMPORTANT:

    Buffering improves efficiency in many I/O scenarios.
    It does not change the logical content being transferred.

    Buffered streams should be closed when their work
    is complete, or flushed when pending output must
    be passed to the underlying stream.


    ============================================================
                    6. DATA STREAMS
    ============================================================

    DataInputStream and DataOutputStream allow Java
    primitive values to be read and written in a
    portable binary format.

    They are byte stream wrappers.

    A. DATAOUTPUTSTREAM

    Used to write primitive values and Strings in
    a defined binary representation.

    Common methods:

        writeInt()
        writeLong()
        writeFloat()
        writeDouble()
        writeBoolean()
        writeChar()
        writeUTF()

    Example:

        ByteArrayOutputStream bytes =
            new ByteArrayOutputStream();

        DataOutputStream output =
            new DataOutputStream(bytes);

        output.writeInt(100);
        output.writeDouble(25.5);
        output.writeBoolean(true);
        output.writeUTF("Java");

        output.flush();

    B. DATAINPUTSTREAM

    Used to read primitive values and Strings written
    in the corresponding DataOutputStream format.

    Common methods:

        readInt()
        readLong()
        readFloat()
        readDouble()
        readBoolean()
        readChar()
        readUTF()

    Example:

        byte[] data = bytes.toByteArray();

        DataInputStream input =
            new DataInputStream(
                new ByteArrayInputStream(data)
            );

        int number = input.readInt();
        double price = input.readDouble();
        boolean active = input.readBoolean();
        String name = input.readUTF();

    C. IMPORTANT RULES

    1. Read values in the same order in which they
       were written.

    2. Use matching write and read methods.

    3. Reading beyond available data can cause EOFException.

    4. writeUTF() and readUTF() use Java's modified UTF-8
       format, not a general-purpose standard UTF-8
       text encoding.

    5. Data streams are useful for structured binary
       primitive data.

    D. EXAMPLE: COMPLETE DATA STREAM OPERATION

        ByteArrayOutputStream bytes =
            new ByteArrayOutputStream();

        DataOutputStream out =
            new DataOutputStream(bytes);

        out.writeInt(25);
        out.writeUTF("Vinay");

        out.flush();

        DataInputStream in =
            new DataInputStream(
                new ByteArrayInputStream(
                    bytes.toByteArray()
                )
            );

        int age = in.readInt();
        String name = in.readUTF();

        System.out.println(age);
        System.out.println(name);


    ============================================================
                    7. OBJECT STREAMS AND SERIALIZATION
    ============================================================

    Serialization is the process of converting an object's
    state into a byte stream.

    Deserialization is the process of reconstructing
    an object from serialized data.

    Java provides:

        ObjectOutputStream
        ObjectInputStream

    A. SERIALIZABLE INTERFACE

    Serializable is a marker interface in java.io.

    A class implements Serializable to indicate that
    its objects are eligible for Java serialization.

    Example:

        import java.io.Serializable;

        class Student implements Serializable {

            private static final long serialVersionUID = 1L;

            int id;
            String name;

            Student(int id, String name) {
                this.id = id;
                this.name = name;
            }
        }

    B. OBJECTOUTPUTSTREAM

    ObjectOutputStream is used to serialize objects.

    Important method:

        writeObject()

    Example:

        ByteArrayOutputStream bytes =
            new ByteArrayOutputStream();

        ObjectOutputStream out =
            new ObjectOutputStream(bytes);

        Student student = new Student(1, "Vinay");

        out.writeObject(student);

        out.flush();

    C. OBJECTINPUTSTREAM

    ObjectInputStream is used to deserialize objects.

    Important method:

        readObject()

    Example:

        ObjectInputStream in =
            new ObjectInputStream(
                new ByteArrayInputStream(
                    bytes.toByteArray()
                )
            );

        Student student =
            (Student) in.readObject();

    readObject() returns Object, so a cast is commonly
    required.

    It can throw ClassNotFoundException as well as
    IOException.

    D. TRANSIENT KEYWORD

    transient is used with instance fields to indicate
    that they should not be serialized by the default
    serialization mechanism.

    Example:

        class User implements Serializable {

            String username;

            transient String password;
        }

    The password field is not restored from the
    serialized stream through default field serialization.

    For a transient reference field, the value after
    default deserialization is its default value, null.

    E. SERIALVERSIONUID

    serialVersionUID is a version identifier used during
    serialization and deserialization.

    Example:

        private static final long serialVersionUID = 1L;

    It helps determine whether the serialized object
    and the class definition are compatible.

    If the identifiers do not match, deserialization
    can fail with InvalidClassException.

    F. IMPORTANT SERIALIZATION RULES

    1. The class must implement Serializable for ordinary
       default object serialization.

    2. Its non-transient, non-static serializable fields
       participate in default serialization.

    3. A non-transient field referring to a non-serializable
       object can cause NotSerializableException.

    4. static fields belong to the class, not individual
       serialized object state, and are not serialized
       as ordinary instance fields.

    5. transient fields are skipped by default
       serialization.

    6. Deserialization does not normally execute the
       serializable class's constructors or field
       initializers.

    7. The first non-serializable superclass's no-argument
       constructor is invoked during ordinary deserialization.

    8. Custom serialization can be implemented using
       methods such as writeObject() and readObject().

    G. SECURITY WARNING

    Java native deserialization of untrusted data can
    be dangerous.

    Avoid deserializing data from untrusted sources.
    Use safer data formats and strict validation where
    appropriate.

    H. SERIALIZATION VS DESERIALIZATION

    Serialization:
        Object -> Byte Stream

    Deserialization:
        Byte Stream -> Object


    ============================================================
                    8. FLUSH() VS CLOSE()
    ============================================================

    A. flush()

    flush() forces buffered output to be passed to the
    underlying output destination.

    Example:

        output.write("Hello");
        output.flush();

    It does not close the stream.

    B. close()

    close() releases the resource and closes the stream.

    Example:

        output.close();

    Closing an output stream generally flushes it first.

    After closing, further operations may fail.

    C. DIFFERENCES

    Feature          flush()          close()
    ------------------------------------------------
    Purpose          Push pending     Close resource
                     output
    Closes stream    No               Yes
    Releases         No               Yes
    resource
    Further use      Allowed          Generally invalid

    IMPORTANT:

    flush() is meaningful for output streams and writers.
    Input streams do not provide a flush() method.

    Not every output stream needs buffering or has
    pending data to flush.


    ============================================================
                    9. CHARACTER ENCODING AND CHARSET
    ============================================================

    Computers store data as bytes.

    Character encoding defines how characters are
    represented as bytes and decoded back into characters.

    Examples of character encodings:

        UTF-8
        UTF-16
        US-ASCII
        ISO-8859-1

    A. ENCODING

    Character data -> Bytes

    B. DECODING

    Bytes -> Character data

    C. CHARSET

    Java provides the Charset class in java.nio.charset.

    Common usage:

        import java.nio.charset.StandardCharsets;

        StandardCharsets.UTF_8

    Example:

        byte[] data = "Java".getBytes(
            StandardCharsets.UTF_8
        );

    Decode:

        String text = new String(
            data,
            StandardCharsets.UTF_8
        );

    D. INPUTSTREAMREADER

    Converts bytes into characters using a charset.

        InputStreamReader reader =
            new InputStreamReader(
                System.in,
                StandardCharsets.UTF_8
            );

    E. OUTPUTSTREAMWRITER

    Converts characters into bytes using a charset.

        ByteArrayOutputStream bytes =
            new ByteArrayOutputStream();

        OutputStreamWriter writer =
            new OutputStreamWriter(
                bytes,
                StandardCharsets.UTF_8
            );

        writer.write("Hello Java");
        writer.flush();

    IMPORTANT:

    1. Use the same charset for encoding and decoding.

    2. UTF-8 is a widely used Unicode encoding.

    3. A character does not necessarily occupy one byte.

    4. Avoid relying on the platform's default charset
       when consistent encoding is required.

    5. Java's char represents a UTF-16 code unit,
       not necessarily one complete Unicode code point.


    ============================================================
                    10. EXCEPTION HANDLING IN I/O
    ============================================================

    I/O operations can fail for several reasons.

    Examples:

        Input source becomes unavailable.
        Output destination cannot accept data.
        Stream is closed.
        Data is malformed.
        End of stream is reached unexpectedly.

    Java uses exceptions to report I/O failures.

    A. IOEXCEPTION

    IOException is a checked exception.

    It is a superclass for many I/O-related exceptions.

    It must generally be handled using try-catch or
    declared using throws.

    Example:

        try {

            int value = System.in.read();

        } catch (IOException e) {

            System.err.println(e.getMessage());
        }

    B. IMPORTANT I/O EXCEPTIONS

    IOException:
        General I/O failure.

    EOFException:
        Unexpected end of input while reading.

    FileNotFoundException:
        A requested file cannot be opened.
        File-related details belong to File IO.

    UnsupportedEncodingException:
        A requested character encoding is unsupported.

    ObjectStreamException:
        Base class for object serialization-related
        exceptions.

    NotSerializableException:
        An object cannot be serialized because it or
        part of its object graph is not serializable.

    InvalidClassException:
        A serialized object is incompatible with the
        local class definition.

    C. EXCEPTION HANDLING APPROACHES

    Using try-catch:

        try {
            // I/O operation
        } catch (IOException e) {
            // Handle exception
        }

    Using throws:

        void readInput() throws IOException {
            // I/O operation
        }

    IMPORTANT:

    Some I/O methods throw IOException, while others
    can throw different exceptions.

    Always check the specific method's exception contract.


    ============================================================
                    11. TRY-WITH-RESOURCES
    ============================================================

    Try-with-resources automatically closes resources
    that implement AutoCloseable.

    Introduced in Java 7.

    Syntax:

        try (Resource resource = new Resource()) {

            // Use resource

        } catch (Exception e) {

            // Handle exception
        }

    Example:

        try (
            BufferedReader reader =
                new BufferedReader(
                    new StringReader("Hello Java")
                )
        ) {

            System.out.println(reader.readLine());

        } catch (IOException e) {

            System.err.println(e.getMessage());
        }

    IMPORTANT RULES:

    1. Resources are declared inside the try parentheses.

    2. Resources are automatically closed when the
       try block completes.

    3. Multiple resources can be declared.

    4. Resources are closed in reverse order of creation.

    5. Exceptions thrown during closing can become
       suppressed exceptions.

    6. The resource must implement AutoCloseable.

    7. Closeable extends AutoCloseable.

    A. MULTIPLE RESOURCES

        try (
            BufferedReader reader =
                new BufferedReader(
                    new StringReader("Java")
                );

            StringWriter target = new StringWriter();

            BufferedWriter writer =
                new BufferedWriter(target)
        ) {

            writer.write(reader.readLine());
            writer.flush();

        } catch (IOException e) {

            e.printStackTrace();
        }

    B. JAVA 9 ENHANCEMENT

    Java 9 allows effectively final variables that
    implement AutoCloseable to be used as resources.

    Example:

        BufferedReader reader =
            new BufferedReader(
                new StringReader("Java")
            );

        try (reader) {

            System.out.println(reader.readLine());

        } catch (IOException e) {

            e.printStackTrace();
        }

    C. SUPPRESSED EXCEPTIONS

    If an exception occurs inside the try block and
    another exception occurs while closing a resource,
    the closing exception can be suppressed.

    Access suppressed exceptions using:

        exception.getSuppressed();

    IMPORTANT:

    Try-with-resources is preferred for managing
    closeable I/O resources.


    ============================================================
                    12. IMPORTANT STREAM CONCEPTS
    ============================================================

    A. EOF - END OF FILE / END OF STREAM

    EOF indicates that no more data is available.

    Common read() methods return -1 at the end of a stream.

    Example:

        int value;

        while ((value = input.read()) != -1) {

            System.out.println(value);
        }

    B. AVAILABLE()

    available() estimates the number of bytes that can
    be read without blocking.

    It does not necessarily indicate the total remaining
    size of the stream.

    Do not use available() as a general way to determine
    whether the entire stream has been read.

    C. SKIP()

    skip() attempts to skip a specified number of bytes
    or characters, depending on the stream or reader.

    D. MARK() AND RESET()

    Some input streams and readers support marking
    a position and returning to it.

    markSupported() checks whether marking is supported.

    E. BLOCKING I/O

    Traditional Java I/O operations can block while
    waiting for input or output.

    For example, reading from keyboard input can wait
    until input becomes available.

    F. PIPED STREAMS

    PipedInputStream and PipedOutputStream allow byte
    data to be transferred between connected streams.

    PipedReader and PipedWriter provide character-based
    piped communication.

    They are commonly used for communication between
    threads.


    ============================================================
                    13. JAVA I/O CLASS QUICK REFERENCE
    ============================================================

    CONSOLE INPUT:

        Scanner
        BufferedReader
        InputStreamReader

    CONSOLE OUTPUT:

        PrintStream
        PrintWriter

    BYTE INPUT:

        InputStream
        ByteArrayInputStream
        BufferedInputStream
        DataInputStream
        ObjectInputStream

    BYTE OUTPUT:

        OutputStream
        ByteArrayOutputStream
        BufferedOutputStream
        DataOutputStream
        ObjectOutputStream

    CHARACTER INPUT:

        Reader
        StringReader
        CharArrayReader
        BufferedReader
        InputStreamReader

    CHARACTER OUTPUT:

        Writer
        StringWriter
        CharArrayWriter
        BufferedWriter
        OutputStreamWriter
        PrintWriter

    OBJECT SERIALIZATION:

        Serializable
        ObjectInputStream
        ObjectOutputStream

    ENCODING:

        Charset
        StandardCharsets


    ============================================================
                    14. INTERVIEW QUESTIONS
    ============================================================

    1. What is a stream?
       A sequence of data flowing between a source
       and a destination.

    2. What are the two major Java I/O stream categories?
       Byte streams and character streams.

    3. What is the superclass of byte input streams?
       InputStream.

    4. What is the superclass of byte output streams?
       OutputStream.

    5. What are the base classes for character streams?
       Reader and Writer.

    6. What is the difference between byte and character
       streams?
       Byte streams handle raw bytes; character streams
       handle character data with encoding and decoding.

    7. What is buffering?
       Temporarily storing data to reduce repeated
       underlying I/O operations.

    8. What is BufferedInputStream?
       A buffered byte input stream.

    9. What is BufferedOutputStream?
       A buffered byte output stream.

    10. What is DataInputStream?
        A stream used to read primitive values from
        binary input.

    11. What is DataOutputStream?
        A stream used to write primitive values to
        binary output.

    12. Why must data be read in the same order in which
        it was written using data streams?
        Because the stream does not independently identify
        the application-level order and meaning of values.

    13. What is serialization?
        Converting an object's state into a byte stream.

    14. What is deserialization?
        Reconstructing an object from serialized data.

    15. What is Serializable?
        A marker interface indicating eligibility for
        Java's ordinary object serialization mechanism.

    16. What is transient?
        A modifier that excludes an instance field from
        default serialization.

    17. What is serialVersionUID?
        A version identifier used during serialization
        compatibility checks.

    18. What is the difference between flush() and close()?
        flush() pushes pending output; close() closes
        the resource.

    19. What is a charset?
        A mapping between characters and byte sequences.

    20. What is UTF-8?
        A variable-length Unicode encoding.

    21. What is IOException?
        A checked exception representing I/O failures.

    22. What is try-with-resources?
        A construct that automatically closes resources
        implementing AutoCloseable.

    23. What is EOF?
        The end of the available input stream.

    24. What does read() return at EOF?
        -1.

    25. What is the purpose of mark() and reset()?
        To mark a position and return to it when supported.

    26. What is the purpose of PipedInputStream?
        To receive bytes from a connected PipedOutputStream.

    27. Does available() return the total size of a stream?
        No. It estimates bytes readable without blocking.

    28. Is Java serialization safe for untrusted data?
        No. Native deserialization of untrusted data
        can introduce serious security risks.


    ============================================================
                    15. PART 2 REVISION CHECKLIST
    ============================================================

    [ ] Understand streams and their purpose.
    [ ] Know InputStream and OutputStream.
    [ ] Know Reader and Writer.
    [ ] Understand byte streams vs character streams.
    [ ] Understand buffered streams.
    [ ] Know ByteArrayInputStream and ByteArrayOutputStream.
    [ ] Understand DataInputStream and DataOutputStream.
    [ ] Understand object serialization and deserialization.
    [ ] Know Serializable, transient and serialVersionUID.
    [ ] Understand flush() vs close().
    [ ] Understand character encoding and UTF-8.
    [ ] Know IOException and important subclasses.
    [ ] Understand try-with-resources.
    [ ] Know EOF, available(), skip(), mark() and reset().
    [ ] Understand piped streams.
    [ ] Know common I/O interview questions.


    ============================================================
                    END OF JAVA I/O
    ============================================================

    


    


    */
}



  