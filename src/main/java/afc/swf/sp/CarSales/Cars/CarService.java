package afc.swf.sp.CarSales.Cars;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Getter
@Setter
public class CarService {
    private int totalNumberOfSales;
    private double salesCommission;
    public CarRepo carRepo;

    public CarService(CarRepo carRepo) {
        this.carRepo = carRepo;
        this.init();
        this.salesCommission = 0;
        this.totalNumberOfSales = 0;
    }

    private void init() {
        Car car1 = new Car(1, "Chevy", "Volt", 4000);
        Car car2 = new Car(2, "Honda", "Odyssey", 11000);
        Car car3 = new Car(3, "Porsche", "911", 200000);
        this.carRepo.save(car1);
        this.carRepo.save(car2);
        this.carRepo.save(car3);
    }

    public Car saveCar(Car car) {
        return this.carRepo.save(car);
    }

    public Optional<Car> getCar(long id) {
        return this.carRepo.findById(id);
    }

    public Car updateCarData(long id, Car car) {
        return this.carRepo.save(car);
    }

    public void deleteCar(long id) {
        this.updateCommission(id);
        this.carRepo.deleteById(id);

    }

    private void updateCommission(long id) {
        Optional<Car> temp=this.carRepo.findById(id);
        if(temp.isPresent()){
            Car tempCar=temp.get();
            double tempVal=(this.getSalesCommission()+ tempCar.getPrice())*.2;
            this.setSalesCommission(tempVal);
            this.setTotalNumberOfSales(this.getTotalNumberOfSales()+1);
        }
    }

    public Iterable<Car> getListOfCars() {
        return this.carRepo.findAll();
    }
}
