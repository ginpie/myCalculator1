package com.example.mycalculator;

public class Token {
    public enum Type {Unknown, Lit, Add, Minus, Multiply, Divide, LeftBracket, RightBracket, Sin, Cos, Tan, Cot, Factorial, Power};
    private String _token = "";
    private Type _type = Type.Unknown;

    public Token(String token, Type type) {
        _token = token;
        _type = type;
    }

    public String token() {
        return _token;
    }

    public Type type() {
        return _type;
    }
}
