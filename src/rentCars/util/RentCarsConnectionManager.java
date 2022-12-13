package rentCars.util;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class RentCarsConnectionManager {
    public static final String PASSWORD_KEY = "db.password";
    public static final String USERNAME_KEY = "db.username";
    public static final String URL_KEY = "db.url";
    public static final String POOL_SIZE_KEY = "db.pool.size";
    public static final Integer DEFAULT_POOL_SIZE = 10;
    public static BlockingQueue<Connection> pool;
    public static List<Connection> sourceConnections;

    private RentCarsConnectionManager(){}

    static {
        loadDriver();
        //initConnectionPool();
    }

    private static void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*private static void initConnectionPool() {
        var poolSize = RentCarsPropertiesUtil.get(POOL_SIZE_KEY);
        var size = poolSize == null ? DEFAULT_POOL_SIZE : Integer.parseInt(poolSize);
        pool = new ArrayBlockingQueue<>(size);
        sourceConnections = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            var connection = open();
            var ProxyConnection = (Connection) Proxy.newProxyInstance(rentCars.util.RentCarsConnectionManager.class.getClassLoader(), new Class[]{Connection.class},
                    (proxy, method, args) -> method.getName().equals("close")
                            ? pool.add((Connection) proxy)
                            : method.invoke(connection, args));
            pool.add(ProxyConnection);
            sourceConnections.add(connection);
        }
    }*/

    /*public static Connection get() {
        try {
            return pool.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }*/

    public static Connection open() {
        try {
            return DriverManager.getConnection(
                    RentCarsPropertiesUtil.get(URL_KEY),
                    RentCarsPropertiesUtil.get(USERNAME_KEY),
                    RentCarsPropertiesUtil.get(PASSWORD_KEY)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*public static void closePool() {
        try {
            for (Connection sourceConnection : sourceConnections) {
                sourceConnection.close(); }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/
}
