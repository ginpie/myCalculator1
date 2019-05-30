package com.example.mycalculator;
public class ParserTest {
    private static Tokenizer tokenizer;
    private static Parser parser;

    private static void parse(String equation) {
        System.out.println("Parsing equation: " + equation);
        tokenizer.setBuffer(equation);
        Exp exp = parser.parse();
        if(exp==null) {
            System.out.println("Get an null expression.");
        } else {
            System.out.println(exp.toString() + "=" + exp.value());
        }
    }

    public static void main(String[] args) {
        tokenizer = new Tokenizer();
        parser = new Parser(tokenizer);

        String[] equations = {
                "10 - 4 - 4 + 1 + 3",
                "12 x 5 - 3",
                "(10 - 2) x (10 ÷ 2) + 1",
                "100 ÷ 5 - 3 x (4 + 1 - 2)",
                "2 x 3 - 2 x 3",
                "1 + 2 x sin 30",
                "1+5!÷2",
                "1.2 + 2.4",
                "2.5 x 4.0",
                "100xsin90+1.99÷1.99-2!",
                "~6"
        };
        for(String equation : equations)
            parse(equation);
    }
}
