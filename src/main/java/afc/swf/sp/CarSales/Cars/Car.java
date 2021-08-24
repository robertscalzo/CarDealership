package afc.swf.sp.CarSales.Cars;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@With
@Table(name="Car_DB", indexes = @Index(columnList = "make"))
public class Car {
    @Id
    private long id;
    private String make;
    private String model;
    private int price;


}
