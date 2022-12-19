package rentCars.filter;

public record CarFilter(int limit,
                        int offset,
                        String brand,
                        String color,
                        Integer seatAmount,
                        Integer price,
                        String status) {
}
