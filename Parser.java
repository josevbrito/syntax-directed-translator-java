/**
 * Parser.java
 * Implements a Recursive Descent Parser for simple arithmetic expressions.
 * This version uses a Scanner that returns single characters as tokens.
 * Grammar:
 *   expr -> digit oper
 *   oper -> + digit oper | - digit oper | ε
 *   digit -> digit -> 0 | .. | 9
 */
public class Parser {
    
    private Scanner scan;
    private char currentToken; 

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
    private void nextToken () {
        currentToken = scan.nextToken();
    }

    /**
     * Matches the current token with the expected token.
     * Advances to the next token if they match; otherwise, throws a syntax error.
     * @param t The expected token character.
     */
    private void match(char t) {
        if (currentToken == t) {
            nextToken();
        }else {
            throw new Error("syntax error");
        }
   }
    
    // --- Grammar Rules ---
    public void parse() {
        expr();
    }

    // Grammar Rule: expr -> digit oper
    void expr() {
       digit();
       oper();
    }

    void digit() {
        if (Character.isDigit(currentToken)) {
			System.out.println("push " + currentToken);
            match(currentToken);
        } else {
           throw new Error("syntax error");
        }
    }

    void oper() {
        if (currentToken == '+') {
            match('+');
            digit();
            System.out.println("add");
            oper();
        } else if (currentToken == '-') {
            match('-');
            digit();
            System.out.println("sub");
            oper();
        }
        // ε-production: do nothing (end of recursion)
    }
}
