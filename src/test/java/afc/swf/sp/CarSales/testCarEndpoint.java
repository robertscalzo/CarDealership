package afc.swf.sp.CarSales;

import afc.swf.sp.CarSales.Cars.Car;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class testCarEndpoint {
    @Autowired
    MockMvc mvc;
    Car test;
    ObjectMapper objectMapper;
    String data;

    @BeforeEach
    public void setup() throws Exception {
        test = new Car(1L, "Chevy", "Volt", 2000);
        objectMapper = new ObjectMapper();
        data = objectMapper.writeValueAsString(test);
    }

    @Test
    @Transactional
    @Rollback
    public void testCreate() throws Exception {
        MockHttpServletRequestBuilder request = post("/car").contentType(MediaType.APPLICATION_JSON).content(data);
        this.mvc.perform(request).andExpect(status().isOk()).andExpect(jsonPath("$.make", is("Chevy")));

    }

    @Test
    @Transactional
    @Rollback
    public void testRead() throws Exception {
        MockHttpServletRequestBuilder requestCreate = post("/car").contentType(MediaType.APPLICATION_JSON).content(data);
        this.mvc.perform(requestCreate).andExpect(status().isOk());

        MockHttpServletRequestBuilder request = get("/car/1");
        this.mvc.perform(request).andExpect(status().isOk()).andExpect(jsonPath("$.make", is("Chevy")));

    }

    @Test
    @Transactional
    @Rollback
    public void testUpdate() throws Exception {
        MockHttpServletRequestBuilder request = post("/car").contentType(MediaType.APPLICATION_JSON).content(data);
        this.mvc.perform(request).andExpect(status().isOk());

        test.setPrice(9999);
        data = objectMapper.writeValueAsString(test);

        MockHttpServletRequestBuilder update = patch("/car/1").contentType(MediaType.APPLICATION_JSON).content(data);
        this.mvc.perform(update).andExpect(status().isOk()).andExpect(jsonPath("$.price", is(9999)));
    }

    @Test
    @Transactional
    @Rollback
    public void testDelete() throws Exception {
        MockHttpServletRequestBuilder request = post("/car").contentType(MediaType.APPLICATION_JSON).content(data);
        this.mvc.perform(request).andExpect(status().isOk());

        MockHttpServletRequestBuilder deleter = delete("/car/1");
        this.mvc.perform(deleter).andExpect(status().isOk());

        MockHttpServletRequestBuilder requestRead=get("/car/1");
        this.mvc.perform(requestRead).andExpect(status().isOk());


    }

    @Test
    @Transactional
    @Rollback
    public void testList() throws Exception {
        MockHttpServletRequestBuilder request = post("/car").contentType(MediaType.APPLICATION_JSON).content(data);
        this.mvc.perform(request).andExpect(status().isOk());
        Car test2=new Car(2, "Ford","F150",10000);
        Car test3=new Car(3, "Dodge", "Dart",200);

        data=objectMapper.writeValueAsString(test2);
        MockHttpServletRequestBuilder request2=post("/car").contentType(MediaType.APPLICATION_JSON).content(data);
        this.mvc.perform(request2).andExpect(status().isOk());
        data=objectMapper.writeValueAsString(test3);
        MockHttpServletRequestBuilder request3=post("/car").contentType(MediaType.APPLICATION_JSON).content(data);
        this.mvc.perform(request3).andExpect(status().isOk());

        MockHttpServletRequestBuilder requestList=get("/car/");
        this.mvc.perform(requestList).andExpect(status().isOk()).andExpect(jsonPath("$.[2].price", is(200)));
    }

    @Test
    public void testGetCommission() throws Exception{
        MockHttpServletRequestBuilder request = get("/car/commission");
        this.mvc.perform(request).andExpect(status().isOk()).andExpect(jsonPath("$", is(0.0)));
    }

}
