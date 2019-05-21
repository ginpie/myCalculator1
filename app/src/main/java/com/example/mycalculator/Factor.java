package com.example.mycalculator;

public class Factor {
    Lit _lit = null;
    Exp _exp = null;

    public Factor(Lit lit) {
        _lit = lit;
    }

    public Factor(Exp exp) {
        _exp = exp;
    }

    public String toString() {
        return _lit == null ? ("("+_exp.toString()+")") : _lit.toString();
    }

    public double value() {
        if (_lit!=null) {
            return _lit.value();
        }else{
            return _exp.value();
        }
    }
}
