package rentCars.filter;

public record ClientFilter(int limit,
                           int offset,
                           String fio,
                           Integer licenceNo,
                           String login) {
}
