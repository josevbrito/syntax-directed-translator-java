/**
 * Scanner.java
 * Implementation of a simple scanner (lexer) to tokenize a stream of input characters into a stream of Tokens.
 * Uses the Adhoc approach.
 */
public class Scanner {
    private final char[] input;
    private int cursor;

    public Scanner(String input) {
        // Adds a null character ('\0') at the end to simplify EOF logic
        this.input = (input + '\0').toCharArray();
        this.cursor = 0;
    }

    // --- Helper Methods ---
    private char peek() {
        return input[cursor];
    }
    
    // Move to the next character in the input
    private void advance() {
        cursor++;
    }

    // --- Main Method to Get the Next Token ---
    public Token getNextToken() {
        
        // 1. Ignore Whitespace
        while (Character.isWhitespace(peek())) {
            advance();
        }

        char currentChar = peek();

        // 2. Recognize End Of File (EOF)
        if (currentChar == '\0') {
            return new Token(Token.EOF);
        }

        // 3. Recognize Numbers (rule: [0-9]+)
        if (Character.isDigit(currentChar)) {
            StringBuilder lexeme = new StringBuilder();

            // Loop to consume all consecutive digits
            while (Character.isDigit(peek())) {
                lexeme.append(peek());
                advance();
            }
            // Returns the NUMBER token with the complete lexeme (e.g., "45", "876")
            return new Token(Token.NUMBER, lexeme.toString());
        }

        // 4. Recognize Single-Character Operators
        switch (currentChar) {
            case '+':
                advance();
                return new Token(Token.PLUS, "+");
            case '-':
                advance();
                return new Token(Token.MINUS, "-");
            case '*':
                advance();
                return new Token(Token.MULT, "*");
            case '/':
                advance();
                return new Token(Token.DIV, "/");
            default:
                // If the character is not recognized, it's a lexical error
                throw new Error("Lexical error: Unexpected character found: '" + currentChar + "'");
        }
    }
}
