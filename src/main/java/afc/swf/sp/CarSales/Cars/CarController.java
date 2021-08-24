package afc.swf.sp.CarSales.Cars;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("")
@RequestMapping("/car")
public class CarController {
    public CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("")
    public Car createCar(@RequestBody Car car) {
        return this.carService.saveCar(car);
    }

    @GetMapping("/{id}")
    public Optional<Car> readCar(@PathVariable long id) {
        return this.carService.getCar(id);
    }

    @PatchMapping("/{id}")
    public Car updateCar(@PathVariable long id, @RequestBody Car car) {
        return this.carService.updateCarData(id, car);
    }

    @DeleteMapping("/{id}")
    public void deleteCarReturnCommission(@PathVariable long id) {
        this.carService.deleteCar(id);
    }

    @GetMapping("/")
    public Iterable<Car> listCar() {
        return this.carService.getListOfCars();
    }

    @GetMapping("/commission")
    public double getCommission(){
        return this.carService.getSalesCommission();
    }


}
