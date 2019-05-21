package com.example.mycalculator;

public class Tokenizer {
    private String _buffer;

    // Inner class to encapsulate token and buffer information
    class TokenResult {
        String data;
        int length;
        Token.Type type;

        public TokenResult(String d, int l, Token.Type t) {
            data = d;
            length = l;
            type = t;
        }
    }

    public Tokenizer() {
        setBuffer("");
    }

    public Tokenizer(String buffer) {
        setBuffer(buffer);
    }

    public void setBuffer(String buffer) {
        _buffer = buffer;
    }

    // Method to extract next token
    private TokenResult nextToken() {
        _buffer = _buffer.trim(); // Remove whitespace
        if(_buffer.isEmpty())
            return null;
        char firstChar = _buffer.charAt(0);
        if(firstChar=='+')
            return new TokenResult("+", 1, Token.Type.Add);
        if(firstChar=='-')
            return new TokenResult("-", 1, Token.Type.Minus);


        if(firstChar == '*')
            return new TokenResult("*",1,Token.Type.Multiply);
        if(firstChar == '/')
            return new TokenResult("/",1,Token.Type.Divide);

        if(Character.isDigit(firstChar)) {
            String num = "";
            int i = 0;
            while (i<_buffer.length() && Character.isDigit(_buffer.charAt(i))) {
                Character digit = _buffer.charAt(i);
                if(Character.isDigit(digit)){
                    num += "" + digit;
                    i++;
                }
            }
            return new TokenResult(num, i, Token.Type.Lit);
        }

        if(firstChar == '(')
            return new TokenResult("(", 1, Token.Type.LeftBracket);
        if(firstChar == ')')
            return new TokenResult(")", 1, Token.Type.RightBracket);

        return null;
    }

    // Return the next token in the buffer (without removing it)
    public Token next() {
        TokenResult nextResult = nextToken();
        if(nextResult==null)
            return null;
        return new Token(nextResult.data, nextResult.type);
    }

    // Return the next token and remove it from the buffer
    public Token takeNext() {
        TokenResult nextResult = nextToken();
        if(nextResult==null)
            return null;
        _buffer = _buffer.substring(nextResult.length);
        return new Token(nextResult.data, nextResult.type);
    }

    // Return whether is another token to parse in the buffer
    public boolean hasNext() {
        return !(_buffer.equals(""));
    }
}
