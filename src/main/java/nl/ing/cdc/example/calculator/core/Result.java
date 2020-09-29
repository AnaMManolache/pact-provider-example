package nl.ing.cdc.example.calculator.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {
    @JsonProperty("result")
    private Number value;

    public Result(Number value) {
        this.value = value;
    }

    public Number getValue() {
        return value;
    }
}
