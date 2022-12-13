package rentCars.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdministratorDto {
    private final Long id;
    private final String description;

}
