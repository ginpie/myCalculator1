package com.example.mycalculator;

public class Lit {
    private double _value = 0.0;

    public Lit(double value) {
        _value = value;
    }

    public double value() {
        System.out.println("Lit: "+_value);
        return _value;
    }

    public String toString() {
        if (Math.round(_value)==_value) {
            String str = Double.toString(_value);
            return str.substring(0, str.length()-2);
        }
        return Double.toString(_value);
    }
}
