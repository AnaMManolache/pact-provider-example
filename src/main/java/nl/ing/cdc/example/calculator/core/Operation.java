package nl.ing.cdc.example.calculator.core;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Map;

public enum Operation {

    PLUS {
        @Override
        public Number calculate(Number first, Number second) {
            return first.intValue() + second.intValue();
        }
    },
    MINUS {
        @Override
        public Number calculate(Number first, Number second) {
            return first.intValue() - second.intValue();
        }
    },
    DIVISION {
        @Override
        public Number calculate(Number first, Number second) {
            return first.doubleValue() / second.doubleValue();
        }
    },
    MULTIPLICATION {
        @Override
        public Number calculate(Number first, Number second) {
            return first.intValue() * second.intValue();
        }
    },
    EXPONENTIATION {
        @Override
        public Number calculate(Number first, Number second) {
            return Math.pow(first.doubleValue(), second.doubleValue());
        }
    };

    private static Map<String, Operation> operations =
            Map.of("+", PLUS,
                    "-", MINUS,
                    "/", DIVISION,
                    "*", MULTIPLICATION,
                    "**", EXPONENTIATION
            );

    @JsonCreator
    public static Operation fromSymbol(String symbol) {
        return operations.get(symbol);
    }

    public abstract Number calculate(Number first, Number second);

}
