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
        // Check if more terms to parse
        if (_tokenizer.hasNext() &&  (_tokenizer.next().type() == Token.Type.Add
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
        Term2 term2 = new Term2(parseTerm3());
        while (_tokenizer.hasNext() && (_tokenizer.next().type() == Token.Type.Sin) || (_tokenizer.next().type() == Token.Type.Cos)
                || (_tokenizer.next().type() == Token.Type.Tan) || (_tokenizer.next().type() == Token.Type.Cot)) {
            Token.Type op = _tokenizer.takeNext().type();
            if (op == Token.Type.Sin) {
                term2 = new Term2(term2, Operation.Sin);
            }
            if (op == Token.Type.Cos) {
                term2 = new Term2(term2, Operation.Cos);
            }
            if (op == Token.Type.Tan) {
                term2 = new Term2(term2, Operation.Tan);
            }
            if (op == Token.Type.Cot) {
                term2 = new Term2(term2, Operation.Cot);
            }
        }
        return term2;
    }

    public Term3 parseTerm3() {
        Term3 term3 = new Term3(parseTerm4());
        while (_tokenizer.hasNext() && (_tokenizer.next().type() == Token.Type.Power)) {
            term3 = new Term3(term3, Operation.Power, parseTerm4());
        }
        return term3;
    }

    public Term4 parseTerm4() {
        Term4 term4 = new Term4(parseFactor());
        while (_tokenizer.hasNext() && (_tokenizer.next().type() == Token.Type.Factorial)) {
            term4 = new Term4(term4, Operation.Power);
        }
        return term4;
    }

    public Factor parseFactor() {
        if (_tokenizer.hasNext()) {
            if (_tokenizer.next().type() == Token.Type.Lit) {
                Lit lit = new Lit(Integer.parseInt(_tokenizer.takeNext().token()));
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
