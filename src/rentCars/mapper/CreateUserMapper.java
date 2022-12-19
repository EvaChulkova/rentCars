package rentCars.mapper;

import lombok.NoArgsConstructor;
import rentCars.dto.CreateDto.CreateUserDto;
import rentCars.entity.User;
import rentCars.entity.enums.RoleEnum;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateUserMapper implements Mapper<CreateUserDto, User> {
    private static final CreateUserMapper INSTANCE = new CreateUserMapper();

    @Override
    public User mapFrom(CreateUserDto createUserDto) {
        return User.builder()
                .fio(createUserDto.getFio())
                .login(createUserDto.getLogin())
                .password(createUserDto.getPassword())
                .role(RoleEnum.valueOf(createUserDto.getRole()))
                .build();
    }

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }
}
