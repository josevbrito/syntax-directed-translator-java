/**
 * TokenType.java
 * Defines the TokenType enum and the Token class used by the Scanner.
 */

public enum TokenType {
    // Operators and Punctuation
    PLUS, MINUS, EQ, SEMICOLON,

    // Keywords
    LET,

    // Literals
    NUMBER,
    IDENT, 

    // End of file
    EOF
}