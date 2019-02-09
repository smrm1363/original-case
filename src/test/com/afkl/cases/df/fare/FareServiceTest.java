package com.afkl.cases.df.fare;

import com.afkl.cases.df.ApplicationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FareServiceTest {

    @Autowired
    FareService fareService;
    @Test
    public void getFare() {
        Fare fare = fareService.getFare("YOW","HNL",null);
        assertTrue(fare.getAmount()>0);

    }
    @Test
    public void getFareWithDetail() throws ApplicationException {
        Fare fare = fareService.getFareWithDetail("YOW","HNL",null);
        assertTrue(fare.getAmount()>0);
    }
}