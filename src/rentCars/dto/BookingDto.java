package rentCars.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingDto {
    private final Long id;
    private final String description;

}
