package rentCars.service;

import rentCars.dao.ClientDao;
import rentCars.dto.ClientDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ClientService {
    private static final ClientService INSTANCE = new ClientService();
    private final ClientDao clientDao = ClientDao.getInstance();
    private ClientService(){}

    public List<ClientDto> findAll() {
        return clientDao.findAll().stream()
                .map(client -> ClientDto.builder()
                        .id(client.getId())
                        .description("""
                                %d - %s - %d - %d - %s
                                """.formatted(client.getId(), client.getFio(), client.getAge(), client.getLicenceNo(), client.getLogin()))
                        .build()
                )
                .collect(toList());
    }

    public static ClientService getInstance() {
        return INSTANCE;
    }
}
