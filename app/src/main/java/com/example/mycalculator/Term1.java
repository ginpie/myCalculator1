package com.example.mycalculator;

public class Term1 {
    Term1 _term1 = null;
    Term2 _term2 = null;
    Operation _op = null;

    public Term1(Term2 term2) {
        _term2 = term2;
        _op = Operation.None;
    }

    public Term1(Term1 term1, Operation op, Term2 term2) {
        _term1 = term1;
        _op = op;
        _term2 = term2;
    }

    public String toString() {
        return _op == Operation.None ? _term2.toString() : (_term1.toString() + _op.toString() + _term2.toString());
    }

    public double value () {
        switch (_op) {
            case Mult:
                return _term1.value() * _term2.value();
            case Div:
                return _term1.value() / _term2.value();
            default:
                return _term2.value();
        }
    }
}
