import java.util.HashMap;
import java.util.Map;

/**
 * Scanner.java
 * Implements a simple Scanner (Lexer) for arithmetic expressions.
 * This version supports identifiers, assignment statements with `let`, keywords and new operators.
 */
public class Scanner {

    private byte[] input;
    private int current;

    // Map to hold keywords and their corresponding TokenTypes
    private static final Map<String, TokenType> keywords;
 
    static {
        keywords = new HashMap<>();
        keywords.put("let",    TokenType.LET);
    }

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
        if (current < input.length) {
            current++;
        }
    }

    // Check if a character is a letter or underscore
    private boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') ||
               (c >= 'A' && c <= 'Z') ||
                c == '_';
    }

    // Check if a character is a letter, number or underscore
    private boolean isAlphaNumeric(char c) {
        return isAlpha(c) || Character.isDigit(c);
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
     * Logic to recognize identifiers and keywords.
     * @return A Token of type IDENT or a keyword (e.g., LET).
     */
    private Token identifier() {
        // Consumes all consecutive alphanumeric characters
        int start = current;
        while (isAlphaNumeric(peek())) {
            advance();
        }
    
        // Extracts the substring (the lexeme) that forms the identifier
        String id = new String(input, start, current - start);
        // Checks if the identifier is a keyword
        TokenType type = keywords.get(id);
        // If not a keyword, it's an identifier
        if (type == null) {
            type = TokenType.IDENT;
        }

        // Returns a Token of the appropriate type
        return new Token(type, id);
    }

    /**
     * Returns the next Token from the input.
     * @return The next Token.
     */
    public Token nextToken() {

        skipWhitespace(); // Skip any whitespace before processing the next token

        char ch = peek();

        // 1. Check for identifiers and keywords
        if (isAlpha(ch)) {
           return identifier();
        }

        // 2. Check for numbers
        if (Character.isDigit(ch)) {
            return number();
        }

        // 3. Check for operators, punctuation and EOF
        switch (ch) {
            case '+':
                advance();
                return new Token(TokenType.PLUS, "+");
            case '-':
                advance();
                return new Token(TokenType.MINUS, "-");
            case '=':
                advance();
                return new Token(TokenType.EQ,"=");
            case ';':
               advance();
               return new Token(TokenType.SEMICOLON,";");
            case '\0':
                return new Token(TokenType.EOF, "EOF"); // End of file
            default:
                throw new Error("lexical error at '" + ch + "'");
        }
    }
}