package rentCars.exception;

import lombok.Getter;
import rentCars.validator.RentErrors;

import java.util.List;

public class ValidationException extends RuntimeException {
    @Getter
    private final List<RentErrors> errors;

    public ValidationException(List<RentErrors> errors) {
        this.errors = errors;
    }
}
