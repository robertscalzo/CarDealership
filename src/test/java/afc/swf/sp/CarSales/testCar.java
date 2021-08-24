package afc.swf.sp.CarSales;

import afc.swf.sp.CarSales.Cars.Car;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class testCar {
    MockMvc mvc;

    @Test
    public void testGetter(){
    Car test=new Car(1l, "Chevy", "Volt",2000);
    assertEquals(test.getPrice(),2000);
    }
    @Test
    public void testSetter(){
     Car test=new Car();
     test.setMake("Chevy");
     assertEquals(test.getMake(), "Chevy");
    }

    @Test
    public void testCommission() throws Exception {

//        MockHttpServletRequestBuilder request = post("/car").contentType(MediaType.APPLICATION_JSON).content(data);
//        this.mvc.perform(request).andExpect(status().isOk());
//
//        MockHttpServletRequestBuilder deleter = delete("/car/1");
//        this.mvc.perform(deleter).andExpect(status().isOk());
//

    }
}