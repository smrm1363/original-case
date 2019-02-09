package com.afkl.cases.df.airPort;

import com.afkl.cases.df.ApplicationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AirPortRestController {
    private final LocationService locationService;

    public AirPortRestController(LocationService locationService) {
        this.locationService = locationService;
    }


    @GetMapping("/airports")
    public String calculateFare(@RequestParam(value = "lang", defaultValue = "en") String lang,
                                @RequestParam(value = "term", defaultValue = "") String term) throws ApplicationException {
        return locationService.findLocation(term,lang);
    }
}
