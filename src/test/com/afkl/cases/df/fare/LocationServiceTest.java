package com.afkl.cases.df.fare;

import com.afkl.cases.df.airPort.Location;
import com.afkl.cases.df.airPort.LocationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationServiceTest {

    @Autowired
    LocationService locationService;
    @Test
    public void getLocation() throws ExecutionException, InterruptedException {
        Location location = locationService.getLocation("YOW","nl");
        assertTrue(location.getCode().equals("YOW"));
    }
    @Test
    public void findLocation()
    {
        String location = locationService.findLocation(null,null);
        assertTrue(location!=null);
    }
}