package rentCars.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rentCars.entity.enums.RoleEnum;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Integer id;
    private String fio;
    private String login;
    private String password;
    private RoleEnum role;
}
