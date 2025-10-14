/**
 * Main.java
 * Entry point for the Recursive Descent Parser.
 */
public class Main {
    public static void main(String[] args) {
        // Test input
        String input = "8+5-7+9";
        
        // Passes the input to the Parser constructor
        Parser p = new Parser(input.getBytes());
        
        p.parse();
    }
}
