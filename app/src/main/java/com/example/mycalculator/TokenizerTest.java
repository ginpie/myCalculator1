package com.example.mycalculator;
public class TokenizerTest {
    private static Tokenizer tokenizer;

    private static void parse(String equation) {
        System.out.println("Tokenize equation: " + equation);
        tokenizer.setBuffer(equation);
        while(tokenizer.hasNext()) {
            Token t = tokenizer.takeNext();
            System.out.print((t.type() == Token.Type.Lit ? t.token() : t.type()) + " ");

        }
        System.out.println();
    }

    public static void main(String[] args) {
        tokenizer = new Tokenizer();
        String[] equations = {"1.2 + 2 + ( 3 - 4)",
                "(10 - 4 - 4 )",
                "12 x (5 - 3 )",
                "3 / (4 + 4) - 1",
                "sin 1 + 2",
                "1+cot2^3!",
                "1.2 + 4.5"
        };

        for(String equation : equations)
            parse(equation);
    }
}

