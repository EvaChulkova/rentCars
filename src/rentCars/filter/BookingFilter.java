package rentCars.filter;

public record BookingFilter(int limit,
                            int offset,
                            Integer clientId,
                            Integer carId,
                            Integer administratorId,
                            String status) {
}
