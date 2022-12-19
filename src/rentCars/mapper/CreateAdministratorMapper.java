package rentCars.mapper;

import lombok.NoArgsConstructor;
import rentCars.dto.CreateDto.CreateAdministratorDto;
import rentCars.entity.Administrator;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateAdministratorMapper implements Mapper<CreateAdministratorDto, Administrator> {
    private static final CreateAdministratorMapper INSTANCE = new CreateAdministratorMapper();
    @Override
    public Administrator mapFrom(CreateAdministratorDto object) {
        return new Administrator(
          object.getFio(),
          object.getLogin(),
          object.getPassword(),
          Integer.valueOf(object.getRole())
        );
    }

    public static CreateAdministratorMapper getInstance() {
        return INSTANCE;
    }
}
