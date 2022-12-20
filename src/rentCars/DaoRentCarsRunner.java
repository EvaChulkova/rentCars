package rentCars;

import rentCars.dao.BookingDao;
import rentCars.dao.CarDao;
import rentCars.dao.ClientDao;
import rentCars.entity.Booking;
import rentCars.entity.Car;
import rentCars.entity.Client;
import rentCars.entity.enums.BookingStatusEnum;
import rentCars.entity.enums.CarColorEnum;
import rentCars.entity.enums.CarStatusEnum;
import rentCars.filter.BookingFilter;
import rentCars.filter.CarFilter;
import rentCars.filter.ClientFilter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class DaoRentCarsRunner {
    public static void main(String[] args) {
        //addCar();
        //deleteCar();
        //updateCar();
        //findAllCars();
        //findCarWithFilter();

        //addClient();
        //deleteClient();
        //findAllClients();
        //updateClient();
        //findClientWithFilters();
        // findClientByUserId();


        //addBooking();
        //deleteBooking();
        //findAllBookings();
        //updateBooking();
        //findBookingWithFilters();

    }

    private static void findClientByUserId() {
        Optional<Integer> clientIdByUserId = ClientDao.getInstance().findClientIdByUserId(2);
        System.out.println(clientIdByUserId);
    }

    private static void findBookingWithFilters() {
        BookingFilter bookingFilter = new BookingFilter(5, 0, null, null, 3, null);
        List<Booking> bookingWithFilters = BookingDao.getInstance().findBookingWithFilters(bookingFilter);
        System.out.println(bookingWithFilters);
    }

    private static void updateBooking() {
        BookingDao bookingDao = BookingDao.getInstance();
        Optional<Booking> maybeBooking = bookingDao.findById(3L);
        System.out.println(maybeBooking);

        maybeBooking.ifPresent(booking -> {
            booking.setCarId(13);
            bookingDao.update(booking);
        });
    }

    private static void findAllBookings() {
        List<Booking> allBookings = BookingDao.getInstance().findAll();
        System.out.println(allBookings);
    }

    private static void deleteBooking() {
        BookingDao bookingDao = BookingDao.getInstance();
        boolean deleteBooking = bookingDao.delete(7L);
        System.out.println(deleteBooking);
    }

    private static void addBooking() {
        BookingDao bookingDao = BookingDao.getInstance();
        Booking booking = new Booking();
        booking.setClientId(7);
        booking.setCarId(7);
        booking.setRentalStart(LocalDateTime.of(2022, 11, 23, 19, 46, 0));
        booking.setRentalFinish(LocalDateTime.of(2022, 12, 21, 19, 46, 0));
        booking.setAdministratorId(2);
        booking.setStatus(BookingStatusEnum.APPROVED);
        booking.setComment("Замечаний нет");

        Booking addBooking = bookingDao.add(booking);
        System.out.println(addBooking);
    }


    private static void findClientWithFilters() {
        ClientFilter clientFilter = new ClientFilter(5, 0,  95443);
        List<Client> clientWithFilters = ClientDao.getInstance().findClientWithFilters(clientFilter);
        System.out.println(clientWithFilters);
    }

    private static void updateClient() {
        ClientDao clientDao = ClientDao.getInstance();
        Optional<Client> maybeClient = clientDao.findById(9L);
        System.out.println(maybeClient);

        maybeClient.ifPresent(client -> {
            client.setAge(35);
            clientDao.update(client);
        });
    }

    private static void findAllClients() {
        List<Client> allClients = ClientDao.getInstance().findAll();
        System.out.println(allClients);
    }

    private static void deleteClient() {
        ClientDao clientDao = ClientDao.getInstance();
        boolean deleteClient = clientDao.delete(10L);
        System.out.println(deleteClient);
    }

    private static void addClient() {
        ClientDao clientDao = ClientDao.getInstance();
        Client client = new Client();
        client.setAge(28);
        client.setLicenceNo(100916);
        client.setValidity(LocalDate.of(2029, 3, 11));


        Client addedClient = clientDao.add(client);
        System.out.println(addedClient);
    }


    private static void findCarWithFilter() {
        var carFilter = new CarFilter(10,0,null, null, 4, null, null);
        var cars = CarDao.getInstance().findAllCarWithFilters(carFilter);
        System.out.println(cars);
    }

    private static void findAllCars() {
        var cars = CarDao.getInstance().findAll();
        System.out.println(cars);
    }

    private static void updateCar() {
        var carDao = CarDao.getInstance();
        var maybeCar = carDao.findById(7L);
        System.out.println(maybeCar);

        maybeCar.ifPresent(car -> {
            car.setStatus(CarStatusEnum.BOOKED);
            carDao.update(car);
        });
    }

    private static void deleteCar() {
        var carDao = CarDao.getInstance();
        var deleteCar = carDao.delete(18L);
        System.out.println(deleteCar);
    }

    private static void addCar() {
        var carDao = CarDao.getInstance();
        var car = new Car();
        car.setBrand("Audi RS6");
        car.setColor(CarColorEnum.Red);
        car.setSeatAmount(5);
        car.setPrice(18150);
        car.setStatus(CarStatusEnum.AVAILABLE);

        var addedCar = carDao.add(car);
        System.out.println(addedCar);
    }
}
