/**
 * Main.java
 * Entry point for the Recursive Descent Parser.
 */
public class Main {
    public static void main(String[] args) {
        // Test input with a 'let' statement
        String input = "let a = 42 + 5 - 8;";
        
        // Create a Parser instance and parse the input
        Parser p = new Parser(input.getBytes());
        p.parse();
    }
}