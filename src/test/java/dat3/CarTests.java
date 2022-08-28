package dat3;

import dat3.cars.entity.Car;
import dat3.cars.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class CarTests {

    @Autowired
    CarRepository carRepository;

    @Test
    void contextLoads() {
        prepareTestData();
        testCreate();
        testRead();
        testUpdate();
        testDelete();
    }

    public void prepareTestData() {
        Car car1 = new Car("brand1", "model1", 1000, "AA00000");
        Car car2 = new Car("brand2", "model2", 2000,"BB11111");
        carRepository.save(car1);
        carRepository.save(car2);
    }

    public void testCreate() {
        Car newCar = new Car("brand3", "model3", 3000, "CC22222");
        carRepository.save(newCar);
        Car foundCar = carRepository.findById("CC22222").get();
        assertEquals("brand3", foundCar.getBrand());
    }

    public void testRead() {
        Car foundCar = carRepository.findById("AA00000").get();
        assertEquals("brand1", foundCar.getBrand());
    }

    public void testUpdate() {
        Car foundCar = carRepository.findById("BB11111").get();
        foundCar.setBrand("testBrand4");
        carRepository.save(foundCar);

        foundCar = carRepository.findById("BB11111").get();
        assertEquals("testBrand4", foundCar.getBrand());
    }

    public void testDelete() {
        carRepository.deleteById("AA00000");
        assertFalse(carRepository.findById("AA00000").isPresent());

    }
}
