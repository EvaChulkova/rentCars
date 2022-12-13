package rentCars.filter;

public record AdministratorFilter(int limit,
                                  int offset,
                                  String fio,
                                  String login) {
}
