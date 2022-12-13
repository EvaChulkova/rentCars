package rentCars.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarDto {
    private final Long id;
    private final String description;

}
