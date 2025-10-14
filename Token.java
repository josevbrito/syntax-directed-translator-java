/**
 * Token.java
 * Class to represent tokens for a simple arithmetic expression parser.
 * Tokens include single-digit numbers and operators +, -, *, /.
 */
public class Token {
    // Token types (Using String constants for simplicity)
    public static final String NUMBER = "NUMBER";
    public static final String PLUS   = "PLUS";
    public static final String MINUS  = "MINUS";
    public static final String MULT   = "MULT";
    public static final String DIV    = "DIV";
    public static final String EOF    = "EOF"; // End Of File

    public final String type;  // Syntactic category of the token
    public final String lexeme; // Lexeme (input character sequence)

    /**
     * Constructor for Tokens that have a value (like NUMBER).
     * @param type The type of the token.
     * @param lexeme The literal value associated with the token.
     */
    public Token(String type, String lexeme) {
        this.type = type;
        this.lexeme = lexeme;
    }
    
    /**
     * Constructor for simple Tokens without a value (like operators).
     * The lexeme is the type itself.
     * @param type The type of the token (e.g., "PLUS").
     */
    public Token(String type) {
        this.type = type;
        this.lexeme = type;
    }

    @Override
    public String toString() {
        if (type.equals(NUMBER)) {
            return "<" + type + ", " + lexeme + ">";
        }
        return "<" + type + ">";
    }
}
