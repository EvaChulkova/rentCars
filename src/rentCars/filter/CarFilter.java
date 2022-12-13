package rentCars.filter;

import java.math.BigDecimal;

public record CarFilter(int limit,
                        int offset,
                        String brand,
                        String color,
                        Integer seatAmount,
                        BigDecimal price,
                        String status) {
}
