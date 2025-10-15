/**
 * Parser.java
 * Implements a Recursive Descent Parser for a simple language with variables and print statements.
 *
 * Grammar:
 *   program -> statements
 *   statements -> statement*
 *   statement -> printStatement | letStatement
 *   letStatement -> 'let' identifier '=' expression ';'
 *   printStatement -> 'print' expression ';'
 *   expr -> term oper
 *   oper -> + term oper | - term oper | ε
 *   term -> number | identifier
 *   number -> [0-9]+
 */
public class Parser {
    
    private Scanner scan;
    private Token currentToken;
    private StringBuilder outputBuilder;

    /**
     * Constructor for the Parser.
     * Initializes the Scanner and fetches the first token.
     * @param input The input byte array representing the expression to parse.
     */
    public Parser(byte[] input) {
        scan = new Scanner(input);
        currentToken = scan.nextToken();
        this.outputBuilder = new StringBuilder();
    }

    // --- Helper Methods ---

    /**
     * Returns the generated output as a string.
     */
    public String output() {
        return outputBuilder.toString();
    }

    /**
     * Fetches the next token from the scanner.
     */
    private void nextToken() {
        currentToken = scan.nextToken();
    }

    /**
     * Matches the current token's type with the expected token type.
     * @param t The expected TokenType.
     */
    private void match(TokenType t) {
        if (currentToken.type == t) {
            nextToken();
        } else {
            throw new Error("syntax error: expected " + t + " but found " + currentToken.type);
        }
    }
    
    // --- Grammar Rules ---

    /**
     * Entry point for parsing.
     */
    public void parse() {
        statements();
    }

    /**
     * Grammar Rule: statements -> statement*
     */
    void statements() {
        while (currentToken.type != TokenType.EOF) {
            statement();
        }
    }

    /**
     * Grammar Rule: statement -> printStatement | letStatement
     */
    void statement () {
        if (currentToken.type == TokenType.PRINT) {
            printStatement();
        } else if (currentToken.type == TokenType.LET) {
            letStatement();
        } else {
            throw new Error("syntax error: unexpected token " + currentToken.type + ", expected a statement.");
        }
    }

    /**
     * Grammar Rule: printStatement -> 'print' expression ';'
     */
    void printStatement() {
        match(TokenType.PRINT);
        expr();
        outputBuilder.append("print\n");
        match(TokenType.SEMICOLON);
    }

    /**
     * Grammar Rule: letStatement -> 'let' identifier '=' expression ';'
     */
    void letStatement () {
        match(TokenType.LET);
        String id = currentToken.lexeme;
        match(TokenType.IDENT);
        match(TokenType.EQ);
        expr();
        outputBuilder.append("pop ").append(id).append("\n");
        match(TokenType.SEMICOLON);
    }

    /**
     * Grammar Rule: expr -> term oper
     */
    void expr() {
       term();
       oper();
    }

    /**
     * Grammar Rule: term -> number | identifier
     */
    void term () {
        if (currentToken.type == TokenType.NUMBER) {
            number();
        } else if (currentToken.type == TokenType.IDENT) {
            outputBuilder.append("push ").append(currentToken.lexeme).append("\n");
            match(TokenType.IDENT);
        } else {
            throw new Error("syntax error: unexpected token " + currentToken.type);
        }
    }

    /**
     * Grammar Rule: number -> [0-9]+
     */
    void number() {
        outputBuilder.append("push ").append(currentToken.lexeme).append("\n");
        match(TokenType.NUMBER);
    }

    /**
     * Grammar Rule: oper -> + term oper | - term oper | ε
     */
    void oper() {
        if (currentToken.type == TokenType.PLUS) {
            match(TokenType.PLUS);
            term();
            outputBuilder.append("add\n");
            oper();
        } else if (currentToken.type == TokenType.MINUS) {
            match(TokenType.MINUS);
            term();
            outputBuilder.append("sub\n");
            oper();
        }
        // ε-production: do nothing
    }
}