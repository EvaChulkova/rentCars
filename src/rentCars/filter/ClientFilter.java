package rentCars.filter;

public record ClientFilter(int limit,
                           int offset,
                           Integer licenceNo) {
}
