package com.example.mycalculator;

// <term3>    ::= <term4> | <term3> ^ <term4>

public class Term3 {
    Term3 _term3 = null;
    Term4 _term4 = null;
    Operation _op = null;
    int prec = 1000;

    public Term3(Term4 term4) {
        _term4 = term4;
        _op = Operation.None;
    }

    public Term3(Term3 term3, Operation op, Term4 term4) {
        _term3 = term3;
        _op = op;
        _term4 = term4;
    }

    public String toString() {
        return _op == Operation.None ? _term4.toString() : (_term3.toString() + _op.toString() + _term4.toString());
    }

    public double value () {
        switch (_op) {
            case Power:
                return precision(Math.pow(_term3.value(), _term4.value()), prec);
            default:
                return _term4.value();
        }
    }

    public double precision(double input, int prec) {
        return ((double)Math.round(input * prec))/prec;
    }
}
