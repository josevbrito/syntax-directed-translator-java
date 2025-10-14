/**
 * Scanner.java
 * Implements a simple Scanner that returns single characters as tokens.
 * Recognizes single-digit numbers and the operators + and -.
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

    // --- Main Method to Get the Next Token ---
    public char nextToken() {
        char ch = peek();

        if (Character.isDigit(ch)) {
			advance();
            return ch;
        }

        switch (ch) {
            case '+':
            case '-':
                advance();
                return ch;
            default:
                break;
        }

        return '\0'; // EOF or unrecognized character
    }
}
