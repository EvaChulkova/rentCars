package rentCars.dto.CreateDto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateClientDto {
    String userId;
    String age;
    String licenceNo;
    String validity;
}
