/**
 * Main.java
 * Entry point for the Recursive Descent Parser.
 */
public class Main {
    public static void main(String[] args) {
        // Test input with whitespace
        String input = "45  + 89   -       876";
        
        // Passes the input to the Parser constructor
        Parser p = new Parser(input.getBytes());
        
        p.parse();
    }
}
