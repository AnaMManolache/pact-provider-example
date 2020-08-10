package nl.ing.cdc.example.calculator.web;

import nl.ing.cdc.example.calculator.core.Calculation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CalculatorController {

    @PostMapping(value = "/calculate", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Integer> calculate(@RequestBody Calculation calculation) {
        List<Integer> operands = calculation.getOperands();

        Integer firstOperand = operands.get(0);
        Integer secondOperand = operands.get(1);

        return ResponseEntity.status(HttpStatus.OK)
                .body(calculation.getOperation().calculate(firstOperand, secondOperand));
    }
}
