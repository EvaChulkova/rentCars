package rentCars.validator;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {
    @Getter
    private final List<RentErrors> rentErrors = new ArrayList<>();

    public void add(RentErrors rentErrors) {
        this.rentErrors.add(rentErrors);
    }

    public boolean isValid() {
        return rentErrors.isEmpty();
    }
}
