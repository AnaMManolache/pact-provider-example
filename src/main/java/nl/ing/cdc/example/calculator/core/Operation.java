package nl.ing.cdc.example.calculator.core;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Map;

public enum Operation {

    PLUS {
        @Override
        public int calculate(int first, int second) {
            return first + second;
        }
    },
    MINUS {
        @Override
        public int calculate(int first, int second) {
            return first - second;
        }
    },
    DIVISION {
        @Override
        public int calculate(int first, int second) {
            return first / second;
        }
    },
    MULTIPLICATION {
        @Override
        public int calculate(int first, int second) {
            return first * second;
        }
    };

    private static Map<Character, Operation> operations =
            Map.of('+', PLUS, '-', MINUS, '/', DIVISION, '*', MULTIPLICATION);

    @JsonCreator
    public static Operation fromSymbol(char symbol) {
        return operations.get(symbol);
    }

    public abstract int calculate(int first, int second);

}
