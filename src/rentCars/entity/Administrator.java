package rentCars.entity;

public class Administrator {
    private Long id;
    private String fio;
    private String login;
    private String password;
    private Integer roleId;

    public Administrator() {}

    public Administrator(Long id, String fio, String login, String password, Integer roleId) {
        this.id = id;
        this.fio = fio;
        this.login = login;
        this.password = password;
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", roleId=" + roleId +
                '}' + "\n";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
