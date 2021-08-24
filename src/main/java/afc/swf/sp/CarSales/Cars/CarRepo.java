package afc.swf.sp.CarSales.Cars;

import org.springframework.data.repository.CrudRepository;

public interface CarRepo extends CrudRepository <Car, Long> {
    Iterable<Car> findByMake(String make);
}
