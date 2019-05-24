package com.example.mycalculator;

/*
 Tokenizer
 Input:

 */

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

        if(firstChar == '+')
            return new TokenResult("+", 1, Token.Type.Add);
        if(firstChar == '-')
            return new TokenResult("-", 1, Token.Type.Minus);
        if(firstChar == '*')
            return new TokenResult("*",1,Token.Type.Multiply);
        if(firstChar == '/')
            return new TokenResult("/",1,Token.Type.Divide);

        if(Character.isLetter(firstChar)) {
            String str = "";
            int i = 0;
            while (i<_buffer.length() && Character.isLetter(_buffer.charAt(i))) {
                Character c = _buffer.charAt(i);
                if(Character.isLetter(c)){
                    str += "" + c;
                    i++;
                }
            }
            if (firstChar=='s'){
                return new TokenResult("sin", 3, Token.Type.Sin);
            }
            if (firstChar=='t'){
                return new TokenResult("tan", 3, Token.Type.Tan);
            }
            if (firstChar=='c'){
                return str.charAt(2) == 's' ? new TokenResult("cos", 3, Token.Type.Cos) : new TokenResult("cot", 3, Token.Type.Cot);
            }
        }

        if(Character.isDigit(firstChar)) {
            String num = "";
            int i = 0;
            while (i<_buffer.length() && (Character.isDigit(_buffer.charAt(i)) || _buffer.charAt(i)=='.')) {
                num += "" + _buffer.charAt(i);
                i++;

            }
            return new TokenResult(num, i, Token.Type.Lit);
        }

        if (firstChar=='!') {
            return new TokenResult("!", 1, Token.Type.Factorial);
        }

        if (firstChar=='^') {
            return new TokenResult("^", 1, Token.Type.Power);
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
