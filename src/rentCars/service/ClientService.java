package rentCars.service;

import rentCars.dao.ClientDao;
import rentCars.dto.ClientDto;
import rentCars.dto.CreateDto.CreateClientDto;
import rentCars.mapper.CreateClientMapper;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ClientService {
    private static final ClientService INSTANCE = new ClientService();
    private final ClientDao clientDao = ClientDao.getInstance();
    private final CreateClientMapper createClientMapper = CreateClientMapper.getInstance();
    private ClientService(){}

    public List<ClientDto> findAll() {
        return clientDao.findAll().stream()
                .map(client -> ClientDto.builder()
                        .id(client.getId())
                        .description("""
                                %d - %d - %d - %d - %s
                                """.formatted(client.getId(), client.getUserId(), client.getAge(), client.getLicenceNo(), client.getValidity()))
                        .build()
                )
                .collect(toList());
    }

    public void create(CreateClientDto createClientDto) {
        var client = createClientMapper.mapFrom(createClientDto);
        clientDao.add(client);
    }

    public static ClientService getInstance() {
        return INSTANCE;
    }
}
