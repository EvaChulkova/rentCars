package rentCars.entity.enums;

import java.util.Arrays;
import java.util.Optional;

public enum RoleEnum {
    ADMIN,
    CLIENT;

    public static Optional<RoleEnum> find(String role) {
        return Arrays.stream(values())
                .filter(it -> it.name().equals(role))
                .findFirst();
    }
}
