package com.example.mycalculator;

/*
 <exp>      ::= <term1> | <exp> + <term> | <exp> - <term>
 <term1>    ::= <term2> | <term1> * <term2> | <term1> / <term2>
 <term2>    ::= <term3> | sin <term2> | cos <term2> | tan <term2> | cot <term2>
 <term3>    ::= <term4> | <term3> ^ term4
 <term4>    ::= <factor> | <term4>!
 <factor>   ::= <integer lit> | ( <exp> )
 <Lit>      ::= Integer
*/

public class Parser {
    Tokenizer _tokenizer;

    public Parser(Tokenizer tokenizer) {
        _tokenizer = tokenizer;
    }

    public Exp parse() {
        // Parse term1
        Exp exp = new Exp(parseTerm1());
        // Check if more term1s to parse
        while (_tokenizer.hasNext() &&  (_tokenizer.next().type() == Token.Type.Add
                || _tokenizer.next().type() == Token.Type.Minus)) {
            Token.Type op = _tokenizer.takeNext().type();
            // Merge previous term into factor
            exp = new Exp(exp, op == Token.Type.Add ? Operation.Add : Operation.Sub, parseTerm1());
        }
        return exp;
    }

    public Term1 parseTerm1() {
        Term1 term1 = new Term1(parseTerm2());
        while (_tokenizer.hasNext() &&  (_tokenizer.next().type() == Token.Type.Multiply
                || _tokenizer.next().type() == Token.Type.Divide)){
            Token.Type op = _tokenizer.takeNext().type();
            term1 = new Term1(term1, op == Token.Type.Multiply ? Operation.Mult : Operation.Div, parseTerm2());
        }
        return term1;
    }

    public Term2 parseTerm2() {

        while (_tokenizer.hasNext() && (_tokenizer.next().type() == Token.Type.Sin || _tokenizer.next().type() == Token.Type.Cos
                || _tokenizer.next().type() == Token.Type.Tan || _tokenizer.next().type() == Token.Type.Cot)) {

            Token.Type op = _tokenizer.takeNext().type();
            if (op == Token.Type.Sin) {
                return new Term2(parseTerm2(), Operation.Sin);
            }
            if (op == Token.Type.Cos) {
                return new Term2(parseTerm2(), Operation.Cos);
            }
            if (op == Token.Type.Tan) {
                return new Term2(parseTerm2(), Operation.Tan);
            }
            if (op == Token.Type.Cot) {
                return new Term2(parseTerm2(), Operation.Cot);
            }
        }
        return new Term2(parseTerm3());
    }

    public Term3 parseTerm3() {
        Term3 term3 = new Term3(parseTerm4());
        while (_tokenizer.hasNext() && (_tokenizer.next().type() == Token.Type.Power)) {
            _tokenizer.takeNext();
            term3 = new Term3(term3, Operation.Power, parseTerm4());
        }
        return term3;
    }

    public Term4 parseTerm4() {
        Term4 term4 = new Term4(parseFactor());
        while (_tokenizer.hasNext() && (_tokenizer.next().type() == Token.Type.Factorial)) {
            _tokenizer.takeNext();
            term4 = new Term4(term4, Operation.Factorial);
        }
        return term4;
    }

    public Factor parseFactor() {
        while (_tokenizer.hasNext()) {
            if (_tokenizer.next().type() == Token.Type.Lit) {
                Lit lit = new Lit(Integer.parseInt(_tokenizer.takeNext().token()));

                // check if trigonometric function happens after numbers
                if (_tokenizer.hasNext()){
                    if (_tokenizer.next().type()==Token.Type.Sin || _tokenizer.next().type()==Token.Type.Cos || _tokenizer.next().type()==Token.Type.Tan || _tokenizer.next().type()==Token.Type.Cot) {
                        throw new IllegalArgumentException("Syntax Error!");
                    }
                }

                return new Factor(lit);
            }
            if (_tokenizer.next().type() == Token.Type.LeftBracket) {
                _tokenizer.takeNext();
                Exp exp = parse();
                _tokenizer.takeNext();
                return new Factor(exp);
            }
        }
        return null;
    }
}
