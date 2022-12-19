package rentCars.mapper;

import rentCars.dto.UserDto;
import rentCars.entity.User;

public class UserMapper implements Mapper<User, UserDto>{
    private static final UserMapper INSTANCE = new UserMapper();
    @Override
    public UserDto mapFrom(User object) {
        return UserDto.builder()
                .id(object.getId())
                .fio(object.getFio())
                .login(object.getLogin())
                .role(object.getRole())
                .build();
    }

    public static UserMapper getInstance() {
        return INSTANCE;
    }
}
