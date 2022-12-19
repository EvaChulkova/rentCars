package rentCars.service;

import lombok.SneakyThrows;
import rentCars.dao.CarDao;
import rentCars.dto.CarDto;
import rentCars.dto.CreateDto.CreateCarDto;
import rentCars.mapper.CreateCarMapper;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class CarService {
    private static final CarService INSTANCE = new CarService();
    private final CarDao carDao = CarDao.getInstance();
    private final CreateCarMapper createCarMapper = CreateCarMapper.getInstance();
    private final ImageService imageService = ImageService.getInstance();
    private CarService(){}

    public List<CarDto> findAll() {
        return carDao.findAll().stream()
                .map(car -> CarDto.builder()
                        .id(car.getId())
                        .description("""
                                %d - %s - %s - %d - %d - %s - %s
                                """.formatted(car.getId(), car.getBrand(), car.getColor(), car.getSeatAmount(), car.getPrice(), car.getStatus(), car.getImage()))
                        .build()
                )
                .collect(toList());
    }

    @SneakyThrows
    public Long create(CreateCarDto createCarDto) {
        var car = createCarMapper.mapFrom(createCarDto);
        imageService.upload(car.getImage(), createCarDto.getImage().getInputStream());
        carDao.add(car);
        return car.getId();
    }


    public static CarService getInstance(){
        return INSTANCE;
    }
}
