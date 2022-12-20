package rentCars.dao;

import lombok.SneakyThrows;
import rentCars.entity.User;
import rentCars.entity.enums.RoleEnum;
import rentCars.exception.RentCarsDaoException;
import rentCars.util.RentCarsConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class UserDao implements DaoRentCar<Integer, User> {
    private static final UserDao INSTANCE = new UserDao();
    private static final String DELETE_USER_SQL = """
            DELETE FROM users
            WHERE id = ?
            """;
    private static final String ADD_USER_SQL = """
            INSERT INTO users (fio, login, password, role) 
            VALUES (?, ?, ?, ?)
            """;

    private static final String UPDATE_USER_SQL = """
            UPDATE users
            SET fio = ?,
            login = ?,
            password = ?,
            role = ?
            WHERE id = ?
            """;

    public static final String FIND_ALL_USERS_SQL = """
            SELECT id,
            fio,
            login,
            password,
            role
            FROM users
            """;

    public static final String FIND_USER_BY_ID_SQL = FIND_ALL_USERS_SQL + """
            WHERE id = ?
            """;

    private static final String FIND_USER_BY_LOGIN_AND_PASSWORD_SQL = """
            SELECT id,
            fio,
            login,
            password,
            role 
            FROM users 
            WHERE login = ? AND password = ?
            """;


    private UserDao(){}

    @Override
    public boolean delete(Integer id) {
        try (Connection connection = RentCarsConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_SQL) ) {
        preparedStatement.setInt(1, id);
        return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    @Override
    public User add(User user) {
        try (Connection connection = RentCarsConnectionManager.open();
             var preparedStatement = connection.prepareStatement(ADD_USER_SQL, RETURN_GENERATED_KEYS) ) {
            preparedStatement.setString(1, user.getFio());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole().name());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt("id"));
            }
            return user;
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    @Override
    public void update(User entity) {
        try (Connection connection = RentCarsConnectionManager.open();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_SQL) ) {
            preparedStatement.setObject(1, entity.getFio());
            preparedStatement.setObject(2, entity.getLogin());
            preparedStatement.setObject(3, entity.getPassword());
            preparedStatement.setObject(4, entity.getRole().name());
            preparedStatement.setObject(5, entity.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    @Override
    public Optional<User> findById(Integer id) {
        try (Connection connection = RentCarsConnectionManager.open()) {
            return findUserById(id, connection);
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    public Optional<User> findUserById(Integer id, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = buildUser(resultSet);
            }
            return Optional.ofNullable(user);
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    @SneakyThrows
    public Optional<User> findByEmailAndPassword(String login, String password) {
        try (Connection connection = RentCarsConnectionManager.open();
             var preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN_AND_PASSWORD_SQL)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = buildUser(resultSet);
            }
            return Optional.ofNullable(user);
        }
    }

    @Override
    public List<User> findAll() {
        try (Connection connection = RentCarsConnectionManager.open();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_USERS_SQL) ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(buildUser(resultSet));
            }
            return users;
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getObject("id", Integer.class))
                .fio(resultSet.getObject("fio", String.class))
                .login(resultSet.getObject("login", String.class))
                .password(resultSet.getObject("password", String.class))
                .role(RoleEnum.valueOf(resultSet.getObject("role", String.class)))
                .build();
    }

    public static UserDao getInstance(){
        return INSTANCE;
    }
}
