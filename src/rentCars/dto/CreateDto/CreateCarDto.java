package rentCars.dto.CreateDto;

import jakarta.servlet.http.Part;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateCarDto {
    String brand;
    String color;
    String seatAmount;
    String price;
    String status;
    Part image;
}
