package rentCars.dto.CreateDto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateAdministratorDto {
    String fio;
    String login;
    String password;
    String role;
}
