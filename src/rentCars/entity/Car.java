package rentCars.entity;

import rentCars.entity.enums.CarColorEnum;
import rentCars.entity.enums.CarStatusEnum;

import java.util.Objects;

public class Car {
    private Long id;
    private String brand;
    private CarColorEnum color;
    private Integer seatAmount;
    private Integer price;
    private CarStatusEnum status;
    private String image;

    public Car(){}

    public Car(Long id, String brand, CarColorEnum color, Integer seatAmount, Integer price, CarStatusEnum status, String image) {
        this.id = id;
        this.brand = brand;
        this.color = color;
        this.seatAmount = seatAmount;
        this.price = price;
        this.status = status;
        this.image = image;
    }

    public Car(String brand, CarColorEnum color, Integer seatAmount, Integer price, CarStatusEnum status, String image) {
        this.brand = brand;
        this.color = color;
        this.seatAmount = seatAmount;
        this.price = price;
        this.status = status;
        this.image = image;
    }

    public Car(String brand, CarColorEnum color, Integer seatAmount, String image) {
        this.brand = brand;
        this.color = color;
        this.seatAmount = seatAmount;
        this.image = image;
    }




    @Override
    public String toString() {
        return "Car{" +
               "id=" + id +
               ", brand='" + brand + '\'' +
               ", color=" + color +
               ", seatAmount=" + seatAmount +
               ", price=" + price +
               ", status=" + status +
               ", image='" + image + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public CarColorEnum getColor() {
        return color;
    }

    public void setColor(CarColorEnum color) {
        this.color = color;
    }

    public Integer getSeatAmount() {
        return seatAmount;
    }

    public void setSeatAmount(Integer seatAmount) {
        this.seatAmount = seatAmount;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public CarStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CarStatusEnum status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
