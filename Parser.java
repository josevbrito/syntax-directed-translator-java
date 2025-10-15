/**
 * Parser.java
 * Implements a Recursive Descent Parser for simple arithmetic expressions.
 * This version handles multi-digit numbers, operators and whitespace.
 * Grammar:
 *   expr -> number oper
 *   oper -> + number oper | - number oper | ε
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
        }else {
            throw new Error("syntax error: expected " + t + " but found " + currentToken.type);
        }
   }
    
    // --- Grammar Rules ---

    /**
     * Entry point for parsing.
     */
    public void parse() {
        expr();
    }

    /**
     * Grammar Rule: expr -> number oper
     */
    void expr() {
       number();
       oper();
    }

    /**
     * Grammar Rule: number -> [0-9]+
     */
    void number () {
        System.out.println("push " + currentToken.lexeme); 
        match(TokenType.NUMBER);
    }

    /**
     * Grammar Rule: oper -> + number oper | - number oper | ε
     */
    void oper() {
        if (currentToken.type == TokenType.PLUS) {
            match(TokenType.PLUS);
            number();
            System.out.println("add");
            oper();
        } else if (currentToken.type == TokenType.MINUS) {
            match(TokenType.MINUS);
            number();
            System.out.println("sub");
            oper();
        }
        // ε-production: do nothing (end of recursion)
    }
}