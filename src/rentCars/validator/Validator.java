package rentCars.validator;

public interface Validator<T> {
    ValidationResult isValid(T object);

}
