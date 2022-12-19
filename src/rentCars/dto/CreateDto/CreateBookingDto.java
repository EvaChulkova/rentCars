package rentCars.dto.CreateDto;

import lombok.Builder;
import lombok.Value;
import rentCars.entity.enums.BookingStatusEnum;

@Value
@Builder
public class CreateBookingDto {
    Integer userId;
    Integer carId;
    String rentalStart;
    String rentalFinish;
    BookingStatusEnum status;
    String comment;
}
