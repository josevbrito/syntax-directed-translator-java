/**
 * Main.java
 * Main class to execute the Parser.
 *
 * Example input: "8+5-7*9"
 * Expected output (postfix notation):
 * push 8
 * push 5
 * add
 * push 7
 * sub
 * push 9
 * mul
 */
public class Main {
    public static void main(String[] args) throws Exception {
        // Test expression using single-digit numbers and operators +, -, *, /
        String input = "8+5-7*9"; 
        
        // The input is converted to a byte array, as we do not yet have a Lexer (Scanner).
        Parser p = new Parser(input.getBytes());
        System.out.println("Starting translation of: " + input + "\n");
        
        try {
            p.parse();
        } catch (Error e) {
            System.err.println("\nError: " + e.getMessage());
            // This example only supports single digits. An input like "45+8" will cause a syntax error.
        }
    }
}
