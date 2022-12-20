package rentCars.dao;

import rentCars.filter.BookingFilter;
import rentCars.entity.Booking;
import rentCars.entity.enums.BookingStatusEnum;
import rentCars.exception.RentCarsDaoException;
import rentCars.util.RentCarsConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookingDao implements DaoRentCar<Long, Booking> {
    public static final BookingDao INSTANCE = new BookingDao();
    private final ClientDao clientDao = ClientDao.getInstance();
    private final CarDao carDao = CarDao.getInstance();

    public static final String DELETE_BOOKING_SQL = """
            DELETE FROM booking
            WHERE id = ?
            """;

    public static final String ADD_BOOKING_SQL = """
            INSERT INTO booking (client_id, car_id, rental_start, rental_finish, administrator_id, status, comment) 
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

    public static final String UPDATE_BOOKING_SQL = """
            UPDATE booking
            SET client_id = ?,
            car_id = ?,
            rental_start = ?,
            rental_finish = ?,
            administrator_id = ?,
            status = ?,
            comment = ?
            WHERE id = ?
            """;

    public static final String FIND_ALL_BOOKINGS_SQL = """
            SELECT id,
            client_id,
            car_id,
            rental_start,
            rental_finish,
            administrator_id,
            status,
            comment
            FROM booking
            """;

    public static final String FIND_BOOKING_BY_ID = FIND_ALL_BOOKINGS_SQL + """
            WHERE id = ?
            """;

    private BookingDao() {}

    @Override
    public void update(Booking booking) {
        try (Connection connection = RentCarsConnectionManager.open();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOKING_SQL)) {
            preparedStatement.setLong(1, booking.getClientId());
            preparedStatement.setLong(2, booking.getCarId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(booking.getRentalStart()));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(booking.getRentalFinish()));
            preparedStatement.setLong(5, booking.getAdministratorId());
            preparedStatement.setString(6, booking.getStatus().name());
            preparedStatement.setString(7, booking.getComment());
            preparedStatement.setLong(8, booking.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    public List<Booking> findBookingWithFilters(BookingFilter bookingFilter) {
        List<Object> parameters = new ArrayList<>();
        List<String> whereSQL = new ArrayList<>();

        if (bookingFilter.clientId() != null) {
            whereSQL.add("client_id = ? ");
            parameters.add(bookingFilter.clientId());
        }
        if (bookingFilter.carId() != null) {
            whereSQL.add("car_id = ?");
            parameters.add(bookingFilter.carId());
        }
        if(bookingFilter.administratorId() != null) {
            whereSQL.add("administrator_id = ?");
            parameters.add(bookingFilter.administratorId());
        }
        if(bookingFilter.status() != null) {
            whereSQL.add("status LIKE = ? ");
            parameters.add("%" + bookingFilter.status() + "%");
        }
        parameters.add(bookingFilter.limit());
        parameters.add(bookingFilter.offset());

        String where = whereSQL.stream()
                .collect(Collectors.joining(" AND ", " WHERE ", " LIMIT ? OFFSET ?"));

        String sql = FIND_ALL_BOOKINGS_SQL + where;
        try (Connection connection = RentCarsConnectionManager.open();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Booking> bookings = new ArrayList<>();
            while (resultSet.next()) {
                bookings.add(buildBooking(resultSet));
            }
            return bookings;
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    @Override
    public Optional<Booking> findById(Long id) {
        try (Connection connection = RentCarsConnectionManager.open();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BOOKING_BY_ID)) {
        preparedStatement.setLong(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        Booking booking = null;
        if (resultSet.next()) {
            booking = buildBooking(resultSet);
        }
        return Optional.ofNullable(booking);
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    @Override
    public List<Booking> findAll() {
        try (Connection connection = RentCarsConnectionManager.open();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BOOKINGS_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Booking> bookings = new ArrayList<>();
            while (resultSet.next()) {
                bookings.add(buildBooking(resultSet));
            }
            return bookings;
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    private Booking buildBooking(ResultSet resultSet) throws SQLException {
        /*var car = new Car(
                resultSet.getLong("id"),
                resultSet.getString("brand"),
                CarColorEnum.valueOf(resultSet.getObject("color", String.class)),
                resultSet.getInt("seat_amount"),
                resultSet.getBigDecimal("price"),
                CarStatusEnum.valueOf(resultSet.getObject("status", String.class))
        );

        var client = new Client(
                resultSet.getLong("id"),
                resultSet.getString("fio"),
                resultSet.getInt("age"),
                resultSet.getInt("licence_no"),
                resultSet.getDate("validity").toLocalDate(),
                resultSet.getString("login"),
                resultSet.getString("password"),
                resultSet.getInt("role_id")
        );

        var administrator = new Administrator(
                resultSet.getLong("id"),
                resultSet.getString("fio"),
                resultSet.getString("login"),
                resultSet.getString("password"),
                resultSet.getInt("role_id")
        );*/

        return new Booking(
                resultSet.getLong("id"),
                resultSet.getInt("client_id"),
                resultSet.getInt("car_id"),
                resultSet.getTimestamp("rental_start").toLocalDateTime(),
                resultSet.getTimestamp("rental_finish").toLocalDateTime(),
                resultSet.getInt("administrator_id"),
                BookingStatusEnum.valueOf(resultSet.getObject("status", String.class)),
                resultSet.getString("comment")
        );

        /*return new Booking(
                resultSet.getLong("id"),
                clientDao.findClientById(resultSet.getLong("client_id"),
                        resultSet.getStatement().getConnection()).orElse(null),
                carDao.findCarById((resultSet.getLong("car_id")),
                        resultSet.getStatement().getConnection()).orElse(null),
                resultSet.getTimestamp("rental_start").toLocalDateTime(),
                resultSet.getTimestamp("rental_finish").toLocalDateTime(),
                administratorDao.findById(resultSet.getLong("administrator_id"),
                        resultSet.getStatement().getConnection()).orElse(null),
                BookingStatusEnum.valueOf(resultSet.getObject("status", String.class)),
                resultSet.getString("comment")
        );*/
    }

    @Override
    public Booking add(Booking booking) {
        try (Connection connection = RentCarsConnectionManager.open();
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_BOOKING_SQL, Statement.RETURN_GENERATED_KEYS)) {
        preparedStatement.setLong(1, booking.getClientId());
        preparedStatement.setLong(2, booking.getCarId());
        preparedStatement.setTimestamp(3, Timestamp.valueOf(booking.getRentalStart()));
        preparedStatement.setTimestamp(4, Timestamp.valueOf(booking.getRentalFinish()));
        preparedStatement.setLong(5, booking.getAdministratorId());
        preparedStatement.setString(6, booking.getStatus().name());
        preparedStatement.setString(7, booking.getComment());

        preparedStatement.executeUpdate();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        while (generatedKeys.next()) {
            booking.setId(generatedKeys.getLong("id"));
        }
        return booking;
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = RentCarsConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOKING_SQL)) {
        preparedStatement.setLong(1, id);
        return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    public static BookingDao getInstance() {
        return INSTANCE;
    }
}
