package rentCars.dao;

import rentCars.filter.ClientFilter;
import rentCars.entity.Client;
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

public class ClientDao implements DaoRentCar<Long, Client> {
    public static final ClientDao INSTANCE = new ClientDao();

    public static final String DELETE_CLIENT_SQL = """
            DELETE FROM client
            WHERE id = ?
            """;

    public static final String ADD_CLIENT_SQL = """
            INSERT INTO client(fio, age, licence_no, validity, login, password, role_id) 
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

    public static final String UPDATE_CLIENT_SQL = """
            UPDATE client
            SET fio = ?,
            age = ?,
            licence_no = ?,
            validity = ?,
            login = ?,
            password = ?,
            role_id = ?
            WHERE id = ?
            """;

    public static final String FIND_ALL_CLIENTS_SQL = """
            SELECT id,
            fio,
            age,
            licence_no,
            validity,
            login,
            password,
            role_id
            FROM client
            """;

    public static final String FIND_CLIENT_BY_ID_SQL = FIND_ALL_CLIENTS_SQL + """
            WHERE id = ?
            """;

    private ClientDao() {}

    @Override
    public void update(Client client) {
        try (Connection connection = RentCarsConnectionManager.open();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLIENT_SQL)) {
        preparedStatement.setString(1, client.getFio());
        preparedStatement.setInt(2, client.getAge());
        preparedStatement.setInt(3, client.getLicenceNo());
        preparedStatement.setTimestamp(4, Timestamp.valueOf(client.getValidity().atStartOfDay()));
        preparedStatement.setString(5, client.getLogin());
        preparedStatement.setString(6, client.getPassword());
        preparedStatement.setInt(7, client.getRoleId());
        preparedStatement.setLong(8, client.getId());

        preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    public List<Client> findClientWithFilters(ClientFilter clientFilter) {
        List<Object> parameters = new ArrayList<>();
        List<String> whereSQL = new ArrayList<>();

        if (clientFilter.fio() != null) {
            whereSQL.add("fio LIKE ? ");
            parameters.add("%" + clientFilter.fio() + "%");
        }
        if (clientFilter.licenceNo() != null) {
            whereSQL.add("licence_no = ? ");
            parameters.add(clientFilter.licenceNo());
        }
        if (clientFilter.login() != null) {
            whereSQL.add("login LIKE ? ");
            parameters.add("%" + clientFilter.login() + "%");
        }
        parameters.add(clientFilter.limit());
        parameters.add(clientFilter.offset());

        String where = whereSQL.stream()
                .collect(Collectors.joining(" AND ", " WHERE ", " LIMIT ? OFFSET ?"));

        String sql = FIND_ALL_CLIENTS_SQL + where;
        try (Connection connection = RentCarsConnectionManager.open();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Client> clients = new ArrayList<>();
            while (resultSet.next()) {
                clients.add(buildClient(resultSet));
            }
            return clients;
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    @Override
    public Optional<Client> findById(Long id) {
        try (Connection connection = RentCarsConnectionManager.open()) {
            return findClientById(id, connection);
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    public Optional<Client> findClientById(Long id, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_CLIENT_BY_ID_SQL)) {
        preparedStatement.setLong(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        Client client = null;
        if (resultSet.next()) {
            client = buildClient(resultSet);
        }
        return Optional.ofNullable(client);
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    @Override
    public List<Client> findAll() {
        try (Connection connection = RentCarsConnectionManager.open();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_CLIENTS_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Client> clients = new ArrayList<>();
            while (resultSet.next()) {
                clients.add(buildClient(resultSet));
            }
            return clients;
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    private Client buildClient(ResultSet resultSet) throws SQLException {
        return new Client(
                resultSet.getLong("id"),
                resultSet.getString("fio"),
                resultSet.getInt("age"),
                resultSet.getInt("licence_no"),
                resultSet.getDate("validity").toLocalDate(),
                resultSet.getString("login"),
                resultSet.getString("password"),
                resultSet.getInt("role_id")
        );
    }

    @Override
    public Client add(Client client) {
        try (Connection connection = RentCarsConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_CLIENT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, client.getFio());
            preparedStatement.setInt(2, client.getAge());
            preparedStatement.setInt(3, client.getLicenceNo());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(client.getValidity().atStartOfDay()));
            preparedStatement.setString(5, client.getLogin());
            preparedStatement.setString(6, client.getPassword());
            preparedStatement.setInt(7, client.getRoleId());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            while (generatedKeys.next()) {
                client.setId(generatedKeys.getLong("id"));
            }
            return client;
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = RentCarsConnectionManager.open();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENT_SQL)) {
        preparedStatement.setLong(1, id);
        return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    public static ClientDao getInstance() {
        return INSTANCE;
    }
}
