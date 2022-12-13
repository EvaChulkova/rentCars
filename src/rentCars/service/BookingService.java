package rentCars.service;

import rentCars.dao.BookingDao;
import rentCars.dto.BookingDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class BookingService {
    private static final BookingService INSTANCE = new BookingService();
    private final BookingDao bookingDao = BookingDao.getInstance();
    private BookingService(){}

    public List<BookingDto> findAll() {
        return bookingDao.findAll().stream()
                .map(booking -> BookingDto.builder()
                        .id(booking.getId())
                        .description("""
                                %d - %d - %s - %s
                                """.formatted(booking.getClientId(), booking.getCarId(), booking.getStatus(), booking.getComment()))
                        .build()
                )
                .collect(toList());
    }

    public static BookingService getInstance(){
        return INSTANCE;
    }
}
