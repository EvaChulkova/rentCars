package rentCars;

import rentCars.util.RentCarsConnectionManager;

import java.sql.SQLException;

public class RentCarsRunner {
    public static void main(String[] args) throws SQLException {
        /*try {
            checkMetaData();
        } finally {
            RentCarsConnectionManager.closePool();
        }*/
    }

    private static void checkMetaData() throws SQLException {
        try (var connection = RentCarsConnectionManager.open()) {
            var metadata = connection.getMetaData();
            var catalogs = metadata.getCatalogs();
            while (catalogs.next()) {
                var catalog = catalogs.getString(1);
                var schemas = metadata.getSchemas();
                while (schemas.next()) {
                    var schema = schemas.getString("TABLE_SCHEM");
                    var tables = metadata.getTables(catalog, schema, "%", null);
                    if(schema.equals("public")) {
                        while (tables.next()) {
                            var table = tables.getString("TABLE_NAME");
                            System.out.println(tables.getString("TABLE_NAME"));
                            metadata.getColumns(catalog, schema, table, null);
                        }
                    }
                }
            }
        }
    }

    public static class DaoRentCarRunner {
        public static void main(String[] args) {

        }
    }
}
