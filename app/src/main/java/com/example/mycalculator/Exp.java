package com.example.mycalculator;

public class Exp {
    Exp _exp = null;
    Term1 _term1 = null;
    Operation _op = null;
    int prec = 1000;

    public Exp(Term1 term1) {
        _term1 = term1;
        _op = Operation.None;
    }

    public Exp(Exp exp, Operation op, Term1 term1) {
        _exp = exp;
        _term1 = term1;
        _op = op;
    }

    public String toString() {
        return _op == Operation.None ? _term1.toString() : (_exp.toString() + _op.toString() + _term1.toString());
    }

    public double value() {
        switch (_op) {
            case Add:
                return precision(_exp.value() + _term1.value(), prec);
            case Sub:
                return precision(_exp.value() - _term1.value(), prec);
            default:
                return _term1.value();
        }
    }

    public double precision(double input, int prec) {
        return ((double)Math.round(input * prec))/prec;
    }
}
