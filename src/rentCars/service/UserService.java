package rentCars.service;

import rentCars.dao.UserDao;
import rentCars.dto.CreateDto.CreateUserDto;
import rentCars.exception.ValidationException;
import rentCars.mapper.CreateUserMapper;
import rentCars.validator.CreateUserValidator;
import rentCars.validator.ValidationResult;

public class UserService {
    private static final UserService INSTANCE = new UserService();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();

    public Integer create(CreateUserDto createUserDto) {
        //validator -> map -> userDao.save -> return id
        ValidationResult validationResult = createUserValidator.isValid(createUserDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getRentErrors());
        }
        var user = createUserMapper.mapFrom(createUserDto);
        userDao.add(user);
        return user.getId();
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
