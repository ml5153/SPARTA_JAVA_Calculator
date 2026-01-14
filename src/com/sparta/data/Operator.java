package com.sparta.data;

import java.util.*;

public enum Operator {
    PLUS("+"), MINUS("-"), MULTIPLY("*"), DIVIDE("/"),
    MOD("%"), POW("^"), SQRT("q");

    private final String symbol;

    Operator(String symbol) {
        this.symbol = symbol;

    }


//    public static Operator fromString(char text) {
//        for (Operator op : Operator.values()) {
//            if (op.symbol.equalsIgnoreCase(String.valueOf(text))) {
//                return op;
//            }
//        }
//        return null;
//    }

//    public static Operator fromString(char text) {
//        return Arrays.stream(Operator.values())
//                .filter(op -> op.symbol.equalsIgnoreCase(String.valueOf(text)))
//                .findFirst()
//                .orElse(null); // 찾지 못했을 경우 null 반환
//    }

    public static Optional<Operator> fromString(char text) {
        return Arrays.stream(Operator.values())
                .filter(op -> op.symbol.equalsIgnoreCase(String.valueOf(text)))
                .findFirst();
    }

    @Override
    public String toString() {
        return this.symbol;
    }
}