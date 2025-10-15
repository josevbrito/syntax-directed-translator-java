/**
 * Parser.java
 * Implements a Recursive Descent Parser for simple arithmetic expressions.
 * This version handles assignment statements with `let`, identifiers, keywords, and new operators.
 * Grammar:
 *   letStatement -> 'let' identifier '=' expression ';'
 *   expr -> term oper
 *   oper -> + term oper | - term oper | ε
 *   term -> number | identifier
 *   number -> [0-9]+
 */
public class Parser {
    
    private Scanner scan;
    private Token currentToken;

    /**
     * Constructor for the Parser.
     * Initializes the Scanner and fetches the first token.
     * @param input The input byte array representing the expression to parse.
     */
    public Parser(byte[] input) {
        scan = new Scanner(input);
        currentToken = scan.nextToken();
    }

    // --- Helper Methods ---

    /**
     * Fetches the next token from the scanner.
     */
    private void nextToken () {
        currentToken = scan.nextToken();
    }

    /**
     * Matches the current token's type with the expected token type.
     * Advances to the next token if they match; otherwise, throws a syntax error.
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
     * Entry point for parsing. Starts by expecting a 'let' statement.
     */
    public void parse() {
        letStatement();
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
        System.out.println("pop " + id);
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
            System.out.println("push " + currentToken.lexeme);
            match(TokenType.IDENT);
        } else {
            throw new Error("syntax error: unexpected token " + currentToken.type);
        }
    }
    
    /**
     * Grammar Rule: number -> [0-9]+
     */
    void number () {
        System.out.println("push " + currentToken.lexeme);
        match(TokenType.NUMBER);
    }

    /**
     * Grammar Rule: oper -> + term oper | - term oper | ε
     */
    void oper() {
        if (currentToken.type == TokenType.PLUS) {
            match(TokenType.PLUS);
            term();
            System.out.println("add");
            oper();
        } else if (currentToken.type == TokenType.MINUS) {
            match(TokenType.MINUS);
            term();
            System.out.println("sub");
            oper();
        }
        // ε-production: do nothing
    }
}