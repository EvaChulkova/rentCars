package rentCars.validator;

import rentCars.dto.CreateDto.CreateUserDto;
import rentCars.entity.enums.RoleEnum;

public class CreateUserValidator implements Validator<CreateUserDto> {

    private static final CreateUserValidator INSTANCE = new CreateUserValidator();
    @Override
    public ValidationResult isValid(CreateUserDto object) {
        ValidationResult validationResult = new ValidationResult();
        if(RoleEnum.find(object.getRole()).isEmpty()) {
            validationResult.add(RentErrors.of("invalid.role", "Role is invalid"));
        }
        return validationResult;
    }

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }
}
