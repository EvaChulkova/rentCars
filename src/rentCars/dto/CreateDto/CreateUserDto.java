package rentCars.dto.CreateDto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateUserDto {
    String fio;
    String login;
    String password;
    String role;
}
