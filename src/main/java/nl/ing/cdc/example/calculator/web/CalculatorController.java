package nl.ing.cdc.example.calculator.web;

import nl.ing.cdc.example.calculator.core.Calculation;
import nl.ing.cdc.example.calculator.core.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CalculatorController {

    @PostMapping(value = "/calculate", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Result> calculate(@RequestBody Calculation calculation) {
        List<Number> operands = calculation.getOperands();

        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        operands.stream()
                                .reduce((o1, o2) -> calculation.getOperation().calculate(o1, o2))
                                .map(Result::new)
                                .orElse(null)
                );
    }
}
