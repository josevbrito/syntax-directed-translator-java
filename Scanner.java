/**
 * Scanner.java
 * Implements a simple Scanner (Lexer) for arithmetic expressions.
 * This version recognizes multi-digit numbers, operators and skips whitespace.
 */
public class Scanner {

    private byte[] input;
    private int current; 

    public Scanner (byte[] input) {
        this.input = input;
    }

    // --- Helper Methods ---
    private char peek() {
        if (current < input.length)
            return (char) input[current];
        return '\0';
    }

    // Move to the next character in the input
    private void advance() {
        char ch = peek();
        if (ch != '\0') {
            current++;
        }
    }

    /**
     * Skips whitespace characters (space, tab, newline, etc.).
     */
    private void skipWhitespace() {
        char ch = peek();
        while (ch == ' ' || ch == '\r' || ch == '\t' || ch == '\n') {
            advance();
            ch = peek();
        }
    }

    /**
     * Logic to recognize multi-digit numbers ([0-9]+).
     * @return A Token of type NUMBER.
     */
    private Token number() {
        int start = current;
        // Consumes all consecutive digits
        while (Character.isDigit(peek())) {
            advance();
        }

        // Extracts the substring (the lexeme) that forms the number
        String n = new String(input, start, current - start);
        return new Token(TokenType.NUMBER, n);
    }

    /**
     * Returns the next Token from the input.
     *
     * @return The next Token.
     */
    public Token nextToken() {
        
        skipWhitespace(); // Skip any whitespace before processing the next token

        char ch = peek();

        // 1. Check for end of input
        if (ch == '0') {
            advance();
            return new Token(TokenType.NUMBER, Character.toString(ch));
        } else if (Character.isDigit(ch))
            return number();

        // 2. Check for operators and EOF
        switch (ch) {
            case '+':
                advance();
                return new Token(TokenType.PLUS, "+");
            case '-':
                advance();
                return new Token(TokenType.MINUS, "-");
            case '\0':
                return new Token(TokenType.EOF, "EOF"); // End of file
            default:
                throw new Error("lexical error at " + ch); // unrecognized character
        }
    }
}