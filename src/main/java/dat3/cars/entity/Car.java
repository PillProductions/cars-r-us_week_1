package dat3.cars.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Car {

    private String brand;
    private String model;
    private int pricePerDay;

    @Id
    private String licensePlate;

    public Car(String brand, String model, int pricePerDay, String licensePlate) {
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.model = model;
        this.pricePerDay = pricePerDay;
    }

    public Car() {

    }
}
