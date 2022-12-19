package rentCars.mapper;

import lombok.NoArgsConstructor;
import rentCars.dto.CreateDto.CreateCarDto;
import rentCars.entity.Car;
import rentCars.entity.enums.CarColorEnum;
import rentCars.entity.enums.CarStatusEnum;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateCarMapper implements Mapper<CreateCarDto, Car>{
    private static final String IMAGE_FOLDER = "/cars";
    private static final CreateCarMapper INSTANCE = new CreateCarMapper();

    @Override
    public Car mapFrom(CreateCarDto createCarDto) {
        return new Car(
                createCarDto.getBrand(),
                CarColorEnum.valueOf(createCarDto.getColor()),
                Integer.valueOf(createCarDto.getSeatAmount()),
                Integer.valueOf(createCarDto.getPrice()),
                CarStatusEnum.valueOf(createCarDto.getStatus()),
                IMAGE_FOLDER + createCarDto.getImage().getSubmittedFileName()
        );
    }

    public static CreateCarMapper getInstance() {
        return INSTANCE;
    }
}
