package rentCars.dto;

import lombok.Builder;
import lombok.Value;
import rentCars.entity.enums.RoleEnum;

@Value
@Builder
public class UserDto {
    Integer id;
    String fio;
    String login;
    RoleEnum role;
}
