package rentCars.validator;

import lombok.Value;

@Value(staticConstructor = "of")
public class RentErrors {
    String code;
    String message;
}
