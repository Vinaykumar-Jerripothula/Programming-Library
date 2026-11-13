
public class Interpreter {

    /*
     * ============================================================
     * 1. INTRODUCTION
     * ============================================================
     *
     * Imagine you are developing a calculator application.
     *
     * A user enters:
     *
     *     10 + 20
     *
     * Your application must understand this expression
     * and calculate its result.
     *
     * Now consider:
     *
     *     10 + 20 - 5
     *
     *     10 + 20 * 2
     *
     *     (10 + 20) * 2
     *
     * As expressions become more complex, simply checking
     * individual characters is not enough.
     *
     * We need a way to represent the grammar of the language
     * and interpret expressions according to that grammar.
     *
     * The Interpreter pattern helps us do this.
     *
     *
     * DEFINITION:
     *
     * Interpreter is a behavioral design pattern that defines
     * a representation for a language's grammar and provides
     * an interpreter to evaluate sentences or expressions
     * in that language.
     *
     *
     * SIMPLE DEFINITION:
     *
     * Define rules for a small language and create objects
     * that understand and execute those rules.
     *
     *
     * ============================================================
     * 2. WHY DO WE NEED INTERPRETER?
     * ============================================================
     *
     * Consider an application that understands expressions:
     *
     *     10 + 20
     *
     *     50 - 10
     *
     *     5 * 4
     *
     * We could write separate if-else conditions for each
     * possible expression.
     *
     * But as the language grows, the conditions become
     * difficult to maintain.
     *
     * Instead, we can define grammar rules such as:
     *
     * Expression = Number + Number
     *
     * Expression = Number - Number
     *
     * Expression = Number * Number
     *
     * Then create classes that represent these rules.
     *
     * Each expression knows how to interpret itself.
     *
     *
     * ============================================================
     * 3. REAL-WORLD ANALOGY
     * ============================================================
     *
     * Imagine a person giving instructions in a simple language:
     *
     *     ADD 10 20
     *
     *     SUBTRACT 50 15
     *
     * A system that understands this language must:
     *
     *   1. Understand the grammar.
     *   2. Identify the operation.
     *   3. Read the operands.
     *   4. Execute the operation.
     *
     * The Interpreter pattern provides a structured way
     * to represent and evaluate these language rules.
     *
     *
     * ============================================================
     * 4. IMPORTANT TERMINOLOGY
     * ============================================================
     *
     * A. GRAMMAR
     *
     * Grammar defines the rules of a language.
     *
     * Example:
     *
     * Expression = Number + Number
     *
     *
     * B. TERMINAL EXPRESSION
     *
     * Represents a basic element that does not contain
     * another expression.
     *
     * Example:
     *
     * NumberExpression(10)
     *
     *
     * C. NON-TERMINAL EXPRESSION
     *
     * Represents an expression made from other expressions.
     *
     * Example:
     *
     * AddExpression(
     *     NumberExpression(10),
     *     NumberExpression(20)
     * )
     *
     *
     * D. ABSTRACT SYNTAX TREE (AST)
     *
     * A tree representation of an expression according
     * to the language grammar.
     *
     * Example:
     *
     *         +
     *        / \
     *       10  20
     *
     * This tree represents:
     *
     *     10 + 20
     *
     *
     * ============================================================
     * 5. STRUCTURE AND PARTICIPANTS
     * ============================================================
     *
     * Main participants:
     *
     * 1. Abstract Expression:
     *
     *    Defines a common interface for interpreting expressions.
     *
     * 2. Terminal Expression:
     *
     *    Represents basic language elements.
     *
     * 3. Non-terminal Expression:
     *
     *    Represents grammar rules involving other expressions.
     *
     * 4. Context:
     *
     *    Stores information needed during interpretation,
     *    if the language requires it.
     *
     * 5. Client:
     *
     *    Builds or obtains the expression representation
     *    and invokes interpretation.
     *
     *
     * BASIC STRUCTURE:
     *
     *              Expression
     *                  |
     *          -------------------
     *          |                 |
     *          v                 v
     *       Terminal        Non-terminal
     *       Expression       Expression
     *                            |
     *                            v
     *                     Other Expressions
     *
     *
     * ============================================================
     * 6. COMPLETE JAVA IMPLEMENTATION
     * ============================================================
     *
     * We will build a simple expression interpreter.
     *
     * Supported operations:
     *
     *     Addition
     *     Subtraction
     *
     * This example constructs expression objects directly.
     * It does not parse arbitrary user-entered strings.
     *
     *
     * STEP 1: Create the Abstract Expression.
     *
     * interface Expression {
     *
     *     int interpret();
     * }
     *
     * Every expression must implement interpret().
     *
     *
     * STEP 2: Create the Terminal Expression.
     *
     * class NumberExpression implements Expression {
     *
     *     private final int number;
     *
     *     public NumberExpression(int number) {
     *         this.number = number;
     *     }
     *
     *     @Override
     *     public int interpret() {
     *         return number;
     *     }
     * }
     *
     * NumberExpression represents a basic numeric value.
     *
     *
     * STEP 3: Create the Non-terminal Expression for addition.
     *
     * class AddExpression implements Expression {
     *
     *     private final Expression left;
     *     private final Expression right;
     *
     *     public AddExpression(
     *         Expression left,
     *         Expression right
     *     ) {
     *         this.left = left;
     *         this.right = right;
     *     }
     *
     *     @Override
     *     public int interpret() {
     *
     *         return left.interpret() + right.interpret();
     *     }
     * }
     *
     *
     * STEP 4: Create the Non-terminal Expression for subtraction.
     *
     * class SubtractExpression implements Expression {
     *
     *     private final Expression left;
     *     private final Expression right;
     *
     *     public SubtractExpression(
     *         Expression left,
     *         Expression right
     *     ) {
     *         this.left = left;
     *         this.right = right;
     *     }
     *
     *     @Override
     *     public int interpret() {
     *
     *         return left.interpret() - right.interpret();
     *     }
     * }
     *
     *
     * STEP 5: Client code.
     *
     * Expression expression = new AddExpression(
     *     new NumberExpression(10),
     *     new NumberExpression(20)
     * );
     *
     * System.out.println(expression.interpret());
     *
     *
     * OUTPUT:
     *
     * 30
     *
     *
     * ============================================================
     * 7. UNDERSTANDING THE EXECUTION FLOW
     * ============================================================
     *
     * Consider:
     *
     * Expression expression = new AddExpression(
     *     new NumberExpression(10),
     *     new NumberExpression(20)
     * );
     *
     * STEP 1:
     *
     * Two NumberExpression objects are created:
     *
     * NumberExpression(10)
     * NumberExpression(20)
     *
     *
     * STEP 2:
     *
     * They are passed to AddExpression.
     *
     * AddExpression stores references to both expressions.
     *
     *
     * STEP 3:
     *
     * Client calls:
     *
     * expression.interpret();
     *
     *
     * STEP 4:
     *
     * AddExpression calls:
     *
     * left.interpret()
     *
     * right.interpret()
     *
     *
     * STEP 5:
     *
     * The left expression returns 10.
     *
     * The right expression returns 20.
     *
     *
     * STEP 6:
     *
     * AddExpression adds both values:
     *
     * 10 + 20 = 30
     *
     *
     * STEP 7:
     *
     * The final result is returned to the client.
     *
     *
     * ============================================================
     * 8. UNDERSTANDING RECURSIVE EXPRESSIONS
     * ============================================================
     *
     * The real strength of this pattern is that an expression
     * can contain other expressions.
     *
     * Consider:
     *
     *     (10 + 20) - 5
     *
     * We can represent it as:
     *
     * Expression expression = new SubtractExpression(
     *
     *     new AddExpression(
     *         new NumberExpression(10),
     *         new NumberExpression(20)
     *     ),
     *
     *     new NumberExpression(5)
     * );
     *
     * System.out.println(expression.interpret());
     *
     *
     * OUTPUT:
     *
     * 25
     *
     *
     * TREE REPRESENTATION:
     *
     *              -
     *             / \
     *            +   5
     *           / \
     *          10  20
     *
     *
     * EXECUTION:
     *
     * 1. Evaluate the left AddExpression.
     *
     *    10 + 20 = 30
     *
     * 2. Evaluate the right NumberExpression.
     *
     *    5
     *
     * 3. Perform subtraction.
     *
     *    30 - 5 = 25
     *
     *
     * IMPORTANT:
     *
     * Non-terminal expressions can contain other terminal
     * and non-terminal expressions.
     *
     * This is why the structure can represent nested expressions.
     *
     *
     * ============================================================
     * 9. HOW DOES GRAMMAR RELATE TO THE CODE?
     * ============================================================
     *
     * Consider this simple grammar:
     *
     * Expression -> Number
     *
     * Expression -> Expression + Expression
     *
     * Expression -> Expression - Expression
     *
     *
     * Corresponding Java classes:
     *
     * Number
     *     -> NumberExpression
     *
     * Expression + Expression
     *     -> AddExpression
     *
     * Expression - Expression
     *     -> SubtractExpression
     *
     * Each class represents one grammar rule.
     *
     * The expression tree represents a sentence
     * in that grammar.
     *
     * The interpret() method evaluates that representation.
     *
     *
     * ============================================================
     * 10. INTERPRETER VS COMPILER
     * ============================================================
     *
     * These concepts are related, but they are not identical.
     *
     *
     * INTERPRETER:
     *
     * Evaluates or executes a program or expression.
     *
     * Example:
     *
     * Evaluating a calculator expression.
     *
     *
     * COMPILER:
     *
     * Translates a source program into another representation,
     * often machine code or bytecode.
     *
     * Example:
     *
     * Java source code is compiled into bytecode.
     *
     *
     * IMPORTANT:
     *
     * A compiler may use parsing and an Abstract Syntax Tree.
     *
     * An interpreter may also use an Abstract Syntax Tree.
     *
     * The AST itself is not exclusive to the Interpreter pattern.
     *
     *
     * ============================================================
     * 11. INTERPRETER VS STRATEGY
     * ============================================================
     *
     * STRATEGY:
     *
     * Encapsulates interchangeable algorithms.
     *
     * Example:
     *
     * Different payment calculation algorithms.
     *
     *
     * INTERPRETER:
     *
     * Represents and evaluates expressions according
     * to grammar rules.
     *
     * Example:
     *
     * Evaluating a mathematical expression tree.
     *
     *
     * REMEMBER:
     *
     * Strategy   -> Select an algorithm.
     *
     * Interpreter -> Evaluate a language expression.
     *
     *
     * ============================================================
     * 12. INTERPRETER VS COMPOSITE
     * ============================================================
     *
     * Both patterns can use recursive object structures.
     *
     * COMPOSITE:
     *
     * Organizes objects into tree structures and treats
     * individual objects and groups uniformly.
     *
     * INTERPRETER:
     *
     * Uses expression objects to represent and evaluate
     * grammar rules.
     *
     * IMPORTANT:
     *
     * An Interpreter implementation may internally use
     * a structure similar to Composite.
     *
     * However, their intentions are different.
     *
     *
     * ============================================================
     * 13. INTERPRETER IN JAVA AND REAL APPLICATIONS
     * ============================================================
     *
     * Interpreter-style designs are useful for small languages
     * and expression evaluation.
     *
     * Examples:
     *
     * 1. Mathematical expression evaluators.
     *
     * 2. Search query languages.
     *
     * 3. Rule evaluation engines.
     *
     * 4. Simple configuration languages.
     *
     * 5. Boolean condition evaluators.
     *
     * 6. Domain-specific languages (DSLs).
     *
     * A DSL is a language designed for a particular domain.
     *
     * Example:
     *
     *     age > 18 AND country == "India"
     *
     * An application can interpret such rules to decide
     * whether a user satisfies a condition.
     *
     *
     * ============================================================
     * 14. INTERPRETER IN BACKEND DEVELOPMENT
     * ============================================================
     *
     * Consider a backend application that supports
     * configurable business rules.
     *
     * Example:
     *
     *     orderAmount > 5000 AND customerType == "PREMIUM"
     *
     * The application can represent the condition as
     * an expression tree.
     *
     * Each expression evaluates its own rule.
     *
     * Possible use cases:
     *
     * - Eligibility rules.
     * - Discount calculation rules.
     * - Access conditions.
     * - Search filters.
     * - Validation expressions.
     *
     * IMPORTANT:
     *
     * For large or complex expression languages, a dedicated
     * parser or expression engine is usually more suitable
     * than manually creating one class for every grammar rule.
     *
     *
     * ============================================================
     * 15. PARSING VS INTERPRETING
     * ============================================================
     *
     * These are two different activities.
     *
     *
     * PARSING:
     *
     * Reads input text and determines whether it follows
     * the grammar.
     *
     * It commonly builds an expression tree or another
     * internal representation.
     *
     *
     * INTERPRETING:
     *
     * Evaluates the expression representation.
     *
     *
     * EXAMPLE:
     *
     * Input:
     *
     *     10 + 20
     *
     * Parsing:
     *
     * Builds an AddExpression containing two NumberExpressions.
     *
     * Interpreting:
     *
     * Evaluates the two numbers and returns 30.
     *
     * IMPORTANT:
     *
     * Our earlier Java example starts after parsing.
     *
     * We manually created the expression tree.
     *
     * It does not contain a tokenizer or parser that reads
     * arbitrary expression strings.
     *
     *
     * ============================================================
     * 16. LIMITATIONS OF INTERPRETER
     * ============================================================
     *
     * Interpreter is most suitable for relatively simple
     * languages and grammars.
     *
     * When grammar rules become very complex:
     *
     * - Many expression classes may be required.
     * - The expression tree can become large.
     * - Maintaining grammar changes can become difficult.
     * - Evaluation may become inefficient without optimization.
     *
     * In such situations, parser generators or established
     * expression engines may be better choices.
     *
     *
     * ============================================================
     * 17. ADVANTAGES
     * ============================================================
     *
     * 1. Represents grammar rules using classes.
     *
     * 2. Makes simple language rules easier to extend.
     *
     * 3. Supports nested and recursive expressions.
     *
     * 4. Separates expression representation from evaluation.
     *
     * 5. Can be useful for small domain-specific languages.
     *
     *
     * ============================================================
     * 18. DISADVANTAGES
     * ============================================================
     *
     * 1. Complex grammars can produce many classes.
     *
     * 2. Large expression trees may be expensive to evaluate.
     *
     * 3. Grammar changes can require changes to multiple classes.
     *
     * 4. Parsing arbitrary input requires additional logic.
     *
     * 5. Not ideal for large programming languages.
     *
     *
     * ============================================================
     * 19. WHEN SHOULD WE USE INTERPRETER?
     * ============================================================
     *
     * Use Interpreter when:
     *
     * - You have a relatively simple grammar.
     * - You need to evaluate repeated expressions.
     * - You want to represent language rules as objects.
     * - You are building a small DSL or rule engine.
     *
     * Avoid Interpreter when:
     *
     * - The grammar is very large or complex.
     * - The expression language requires sophisticated parsing.
     * - A dedicated parser or existing expression engine
     *   is more appropriate.
     *
     *
     * ============================================================
     * 20. COMMON MISCONCEPTIONS
     * ============================================================
     *
     * 1. Interpreter means compiling source code.
     *    FALSE. It evaluates expressions or language constructs.
     *
     * 2. Every Interpreter must read strings directly.
     *    FALSE. An expression tree can be constructed separately.
     *
     * 3. Every expression must be a terminal expression.
     *    FALSE. Non-terminal expressions combine other expressions.
     *
     * 4. Interpreter and Composite are identical.
     *    FALSE. Their structures can be similar, but their intent
     *    is different.
     *
     * 5. Interpreter is suitable for every programming language.
     *    FALSE. It is generally most suitable for simpler grammars.
     *
     * 6. AST and Interpreter pattern are the same.
     *    FALSE. An AST is a representation; Interpreter is a
     *    design pattern for evaluating grammar-based expressions.
     *
     *
     * ============================================================
     * 21. INTERVIEW QUESTIONS
     * ============================================================
     *
     * Q1. What is Interpreter Design Pattern?
     *
     * A: A behavioral pattern that represents a language's
     *    grammar and provides a way to evaluate its expressions.
     *
     * Q2. What is grammar?
     *
     * A: A set of rules that defines valid expressions
     *    in a language.
     *
     * Q3. What is a Terminal Expression?
     *
     * A: An expression representing a basic language element
     *    that does not contain other expressions.
     *
     * Q4. What is a Non-terminal Expression?
     *
     * A: An expression that combines or operates on
     *    other expressions.
     *
     * Q5. What is an Abstract Syntax Tree?
     *
     * A: A tree representation of an expression according
     *    to its grammar.
     *
     * Q6. What is the difference between parsing and interpreting?
     *
     * A: Parsing builds or identifies the expression structure;
     *    interpreting evaluates that structure.
     *
     * Q7. When should we avoid Interpreter?
     *
     * A: When the grammar becomes too complex and requires
     *    a more sophisticated parser or expression engine.
     *
     * Q8. Give real-world use cases.
     *
     * A: Calculator expressions, search queries, rule engines
     *    and domain-specific languages.
     *
     *
     * ============================================================
     * 22. QUICK REVISION
     * ============================================================
     *
     * PATTERN:
     * Interpreter
     *
     * CATEGORY:
     * Behavioral Design Pattern
     *
     * PURPOSE:
     * Represent and evaluate a language's grammar.
     *
     * MAIN IDEA:
     * Each expression represents a grammar rule and knows
     * how to interpret itself.
     *
     * PARTICIPANTS:
     *
     * Abstract Expression
     * Terminal Expression
     * Non-terminal Expression
     * Context
     * Client
     *
     * IMPORTANT CONCEPTS:
     *
     * Grammar
     * Abstract Syntax Tree
     * Terminal Expression
     * Non-terminal Expression
     * Parsing
     * Interpretation
     *
     * USE CASES:
     *
     * Calculators
     * Rule Engines
     * Search Queries
     * Domain-Specific Languages
     *
     * REMEMBER:
     *
     * Grammar -> Defines the language rules.
     *
     * Parsing -> Builds the expression representation.
     *
     * Interpreting -> Evaluates the expression.
     *
     * ============================================================
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Interpreter Design Pattern , Happy Learning ..!");
    }
}
