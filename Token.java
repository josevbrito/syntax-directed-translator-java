/**
 * Token.java
 * Class to represent tokens for the Scanner.
 */

public class Token {
    // Token type (e.g., NUMBER, PLUS, MINUS, EOF)
    final TokenType type;
    final String lexeme; // Lexeme (input character sequence)

    /**
     * Constructor for Tokens that have a value (like numbers).
     * @param type The type of the token.
     * @param lexeme The literal value associated with the token.
     */
    public Token(TokenType type, String lexeme) {
        this.type = type;
        this.lexeme = lexeme;
    }

    @Override
    public String toString() {
        return "<" + type + ">" + lexeme + "</" + type + ">";
    }
}
