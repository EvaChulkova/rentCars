package rentCars.mapper;

import lombok.NoArgsConstructor;
import rentCars.dto.CreateDto.CreateClientDto;
import rentCars.entity.Client;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateClientMapper implements Mapper<CreateClientDto, Client> {
    private static final CreateClientMapper INSTANCE = new CreateClientMapper();

    @Override
    public Client mapFrom(CreateClientDto createClientDto) {
        return Client.builder()
                .userId(Integer.valueOf(createClientDto.getUserId()))
                .age(Integer.valueOf(createClientDto.getAge()))
                .licenceNo(Integer.valueOf(createClientDto.getLicenceNo()))
                .validity(LocalDate.parse(createClientDto.getValidity()))
                .build();
    }

    public static CreateClientMapper getInstance() {
        return INSTANCE;
    }
}
