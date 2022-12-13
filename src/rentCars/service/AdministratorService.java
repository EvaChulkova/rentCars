package rentCars.service;

import rentCars.dao.AdministratorDao;
import rentCars.dto.AdministratorDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class AdministratorService {
    private static final AdministratorService INSTANCE = new AdministratorService();
    private final AdministratorDao administratorDao = AdministratorDao.getInstance();
    private AdministratorService(){}

    public List<AdministratorDto> findAll() {
        return administratorDao.findAll().stream()
                .map(administrator -> AdministratorDto.builder()
                        .id(administrator.getId())
                        .description("""
                                %d - %s - %s
                                """.formatted(administrator.getId(), administrator.getFio(), administrator.getLogin()))
                        .build()
                )
                .collect(toList());
    }

    public static AdministratorService getInstance() {
        return INSTANCE;
    }
}
