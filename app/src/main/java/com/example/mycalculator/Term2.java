package com.example.mycalculator;

public class Term2 {
    Term2 _term2 = null;
    Term3 _term3 = null;
    Operation _op = null;
    int prec = 1000;

    public Term2(Term3 term3) {
        _term3 = term3;
        _op = Operation.None;
    }

    public Term2(Term2 term2, Operation op) {
        _term2 = term2;
        _op = op;
    }

    public String toString() {
        return _op == Operation.None ? _term3.toString() : (_op.toString() + _term2.toString());
    }

    public double value () {
        double R_to_D = Math.PI/180;
        switch (_op) {
            case Sin:
                return precision(Math.sin(_term2.value() * R_to_D), prec);
            case Cos:
                return precision(Math.cos(_term2.value() * R_to_D), prec);
            case Tan:
                return precision(Math.tan(_term2.value() * R_to_D), prec);
            case Cot:
                return precision(1.0 / (Math.sin(_term2.value() * R_to_D)), prec);
            case Ln:
                return precision(Math.log(_term2.value()), prec);
            default:
                return _term3.value();
        }
    }

    public double precision(double input, int prec) {
        return ((double)Math.round(input * prec))/prec;
    }
}
