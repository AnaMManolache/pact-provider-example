package nl.ing.cdc.example.calculator.core;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Calculation {
    private final List<Integer> operands;
    private final Operation operation;

    public Calculation(@JsonProperty("operants") List<Integer> operands,
                       @JsonProperty("operation") Operation operation) {
        this.operands = operands;
        this.operation = operation;
    }

    public List<Integer> getOperands() {
        return operands;
    }

    public Operation getOperation() {
        return operation;
    }
}
