package com.example.mycalculator;

// <term4>    ::= <factor> | <term4> !

public class Term4 {
    Term4 _term4 = null;
    Factor _factor = null;
    Operation _op = null;

    public Term4(Factor factor) {
        _factor = factor;
        _op = Operation.None;
    }

    public Term4(Term4 term4, Operation op, Factor factor) {
        _term4 = term4;
        _op = op;
        _factor = factor;
    }

    public String toString() {
        return _op == Operation.None ? _factor.toString() : (_term4.toString() + _op.toString());
    }

    public double value () {
        switch (_op) {
            case Factorial:
                return fact(_term4.value());
            default:
                return _term4.value();
        }
    }

    public double fact(double n) {
        int nt = (int) n;
        if (nt!=n){
            throw new ArithmeticException("Math error");
        } else {
            int fact = 1;
            for (int i=2; i<=nt; i++) {
                fact *= i;
            }
            return fact;
        }
    }
}
