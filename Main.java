/**
 * Main.java
 * The main entry point for the simple stack-based interpreter.
 */
public class Main {
    public static void main(String[] args) {
        // Sample input program
        String input = """
            let a = 42 + 2;
            let b = 15 + 3;
            print a + b;        
                """;
        
        // 1. The Parser generates the commands
        Parser p = new Parser(input.getBytes());
        p.parse();

        // 2. The Interpreter executes the commands
        Interpreter i = new Interpreter(p.output());
        i.run();
    }
}