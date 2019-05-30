package com.example.mycalculator;

public enum Operation {
    None (""),
    Add ("+"),
    Sub ("-"),
    Mult ("x"),
    Div ("÷"),
    Sin ("sin"),
    Cos ("cos"),
    Tan ("tan"),
    Cot ("cot"),
    Power ("^"),
    Factorial ("!"),
    Ln("ln");

    String op;

    Operation(String s) {
        op = s;
    }

    @Override
    public String toString() {
        return op;
    }
}
