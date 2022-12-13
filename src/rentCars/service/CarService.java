package rentCars.service;

import rentCars.dao.CarDao;
import rentCars.dto.CarDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class CarService {
    private static final CarService INSTANCE = new CarService();
    private final CarDao carDao = CarDao.getInstance();
    private CarService(){}

    public List<CarDto> findAll() {
        return carDao.findAll().stream()
                .map(car -> CarDto.builder()
                        .id(car.getId())
                        .description("""
                                %d - %s - %s - %d - %s
                                """.formatted(car.getId(), car.getBrand(), car.getColor(), car.getSeatAmount(), car.getStatus()))
                        .build()
                )
                .collect(toList());
    }

    public static CarService getInstance(){
        return INSTANCE;
    }
}
