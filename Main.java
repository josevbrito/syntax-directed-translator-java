/**
 * Main.java
 * Entry point for the Recursive Descent Parser.
 */
public class Main {
    public static void main(String[] args) {
        // Test input with multiple 'let' and 'print' statements
        String input = """
            let a = 42 + 5 - 8;
            let b = 56 + 8;
            print a + b + 6;        
                """;
        
        Parser p = new Parser(input.getBytes());
        
        p.parse();
    }
}