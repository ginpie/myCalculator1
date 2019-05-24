package com.example.mycalculator;

public class Term2 {
    Term2 _term2 = null;
    Term3 _term3 = null;
    Operation _op = null;

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
                return ((double)Math.round(Math.sin(_term2.value() * R_to_D) * 1000)) / 1000;
            case Cos:
                return ((double)Math.round(Math.cos(_term2.value() * R_to_D) * 1000)) / 1000;
            case Tan:
                return ((double)Math.round(Math.tan(_term2.value() * R_to_D) * 1000)) / 1000;
            case Cot:
                return 1.0/((double)Math.round(Math.tan(_term2.value() * R_to_D) * 1000)) / 1000;
            default:
                return _term3.value();
        }
    }
}
