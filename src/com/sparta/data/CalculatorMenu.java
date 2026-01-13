package com.sparta.data;

public enum CalculatorMenu {
    CALCULATOR("c"),
    HISTORY("h"),
    CONTINUE("co"),
    RESET("r"),
    PREVIOUS("p"),
    DELETE_OLDEST("delete"),
    EXIT("exit");

    private final String menu;
    CalculatorMenu(String menu) {
        this.menu = menu;
    }

    public static CalculatorMenu fromString(final String menu) {
        for(CalculatorMenu cm :  CalculatorMenu.values()) {
            if(cm.menu.equalsIgnoreCase(menu)) {
                return cm;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.menu;
    }
}
