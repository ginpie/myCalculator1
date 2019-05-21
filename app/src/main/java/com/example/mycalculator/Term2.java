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
        switch (_op) {
            case Sin:
                return Math.sin(_term2.value());
            case Cos:
                return Math.cos(_term2.value());
            case Tan:
                return Math.tan(_term2.value());
            case Cot:
                return 1.0/Math.tan(_term2.value());
            default:
                return _term3.value();
        }
    }
}
