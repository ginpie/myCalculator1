package com.example.mycalculator;

public class Lit {
    private double _value = 0;

    public Lit(double value) {
        _value = value;
    }

    public double value() {
        return _value;
    }

    public String toString() {
        return Double.toString(_value);
    }
}
