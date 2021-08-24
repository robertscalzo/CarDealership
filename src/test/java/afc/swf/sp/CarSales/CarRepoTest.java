package afc.swf.sp.CarSales;

import afc.swf.sp.CarSales.Cars.Car;
import afc.swf.sp.CarSales.Cars.CarRepo;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CarRepoTest {
    @Autowired
    CarRepo carRepo;
    //literally autowires the spring repo to this bean

    @BeforeEach
    public void cleanup() {
        this.carRepo.deleteAll();
    }

    @Test
    public void testGet() {
        this.carRepo.save(new Car().withId(1));
        List<Car> cars = (List<Car>) this.carRepo.findAll();
        assertEquals(cars.size(), 1);

    }

    @Test
    public void testGetByMake(){
        this.carRepo.save(new Car().withId(1).withMake("Chevy"));
        this.carRepo.save(new Car().withId(2).withMake("Ford"));
        this.carRepo.save(new Car().withId(3).withMake("Chevy"));

        List<Car> cars=(List<Car>) this.carRepo.findByMake("Chevy");

        assertEquals(cars.size(),2);

    }

}
