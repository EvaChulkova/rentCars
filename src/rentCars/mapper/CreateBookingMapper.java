package rentCars.mapper;

import lombok.NoArgsConstructor;
import rentCars.dto.CreateDto.CreateBookingDto;
import rentCars.entity.Booking;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateBookingMapper implements Mapper<CreateBookingDto, Booking>{
    private static final CreateBookingMapper INSTANCE = new CreateBookingMapper();

    @Override
    public Booking mapFrom(CreateBookingDto object) {
        return new Booking(
                object.getUserId(),
                object.getCarId(),
                LocalDateTime.parse(object.getRentalStart()),
                LocalDateTime.parse(object.getRentalFinish()),
                object.getStatus(),
                object.getComment()
        );
    }

    public static CreateBookingMapper getInstance() {
        return INSTANCE;
    }
}
