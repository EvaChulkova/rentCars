package rentCars.util;

import java.io.IOException;
import java.util.Properties;

public final class RentCarsPropertiesUtil {
    public static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private RentCarsPropertiesUtil() {}


    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

    private static void loadProperties() {
        try (var inputStream = RentCarsPropertiesUtil.class.getClassLoader().getResourceAsStream("applicationRentCar.properties")) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
