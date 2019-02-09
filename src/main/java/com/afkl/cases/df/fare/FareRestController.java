package com.afkl.cases.df.fare;

import com.afkl.cases.df.ApplicationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FareRestController {
    private final FareService fareService;

    public FareRestController(FareService fareService) {
        this.fareService = fareService;
    }

    @GetMapping("/fares/{origin}/{destination}")
    public Fare calculateFare(@PathVariable("origin") String origin,
                              @PathVariable("destination") String destination,
                              @RequestParam(value = "currency", defaultValue = "EUR") String currency) throws ApplicationException {
        return fareService.getFareWithDetail(origin,destination,currency);
    }
}
