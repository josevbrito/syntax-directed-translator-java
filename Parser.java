/**
 * Parser.java
 * Implementation of the syntax-directed translator to convert
 * arithmetic expressions (single digits, +, -, *, /) from infix to postfix notation.
 *
 * Grammar (left recursion removed):
 * expr -> digit oper
 * oper -> (+ | - | * | /) digit oper | ε
 * digit -> 0 | .. | 9
 */
public class Parser {
    private byte[] input;
    private int current; 

    public Parser(byte[] input) {
        this.input = input;
        this.current = 0;
    }

    // --- Helper Methods ---

    private char peek() {
        if (current < input.length)
           return (char)input[current];
       return '\0'; // End Of File (EOF)
    }

    private void match(char c) {
        if (c == peek()) {
            current++;
        } else {
            throw new Error("Syntax error! Expected '" + c + "', found '" + peek() + 
                            "' at position " + current);
        }
    }

    // --- Grammar Rules (Parser Procedures) ---
    
    public void parse() {
        expr();
    }
    
    void expr() {
        digit();
        oper();
    }

    void digit() {
        char c = peek();
        if (Character.isDigit(c)) {
            System.out.println("push " + c); 
            match(c);
        } else {
           throw new Error("Syntax error! Expected a digit (0-9), found '" + c + 
                           "' at position " + current);
        }
    }

    void oper() {
        char op = peek();

        if (op == '+') {
            match('+');
            digit();
            System.out.println("add");
            oper();
        } else if (op == '-') {
            match('-');
            digit();
            System.out.println("sub");
            oper();
        } else if (op == '*') {
            match('*');
            digit();
            System.out.println("mul");
            oper();
        } else if (op == '/') {
            match('/');
            digit();
            System.out.println("div");
            oper();
        }
        // ε-production: do nothing (end of recursion)
    }
}
