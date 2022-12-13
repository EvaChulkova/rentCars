package rentCars.entity;

import java.time.LocalDate;

public class Client {
    private Long id;
    private String fio;
    private Integer age;
    private Integer licenceNo;
    private LocalDate validity;
    private String login;
    private String password;
    private Integer roleId;

    public Client(Long id, String fio, Integer age, Integer licenceNo, LocalDate validity, String login, String password, Integer roleId) {
        this.id = id;
        this.fio = fio;
        this.age = age;
        this.licenceNo = licenceNo;
        this.validity = validity;
        this.login = login;
        this.password = password;
        this.roleId = roleId;
    }

    public Client() {}

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", age=" + age +
                ", licenceNo=" + licenceNo +
                ", validity=" + validity +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", roleId=" + roleId +
                '}' + '\n';
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(Integer licenceNo) {
        this.licenceNo = licenceNo;
    }

    public LocalDate getValidity() {
        return validity;
    }

    public void setValidity(LocalDate validity) {
        this.validity = validity;
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
