package com.sparta.data;

public enum Operator {
    PLUS("+"), MINUS("-"), MULTIPLY("*"), DIVIDE("/"),
    MOD("%"), POW("^"), SQRT("q");

    private final String symbol;

    Operator(String symbol) {
        this.symbol = symbol;
    }

    public static Operator fromString(char text) {
        for (Operator op : Operator.values()) {
            if (op.symbol.equalsIgnoreCase(String.valueOf(text))) {
                return op;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.symbol;
    }
}