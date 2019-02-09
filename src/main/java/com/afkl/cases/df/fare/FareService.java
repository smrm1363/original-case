package com.afkl.cases.df.fare;

import com.afkl.cases.df.ApplicationException;
import com.afkl.cases.df.airPort.Location;
import com.afkl.cases.df.airPort.LocationService;
import com.afkl.cases.df.domain.authentication.OAuthStrategy;
import com.afkl.cases.df.util.ServiceClientCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class FareService {

    private final OAuthStrategy oAuthStrategy;
    private final LocationService locationService;
    private final ServiceClientCall serviceClientCall;
    @Value("${fares.path}")
    private String faresPathPath;

    @Value("${server.url}")
    private String serverUrl;
    @Autowired
    public FareService(OAuthStrategy oAuthStrategy, LocationService locationService, ServiceClientCall serviceClientCall) {
        this.oAuthStrategy = oAuthStrategy;
        this.locationService = locationService;
        this.serviceClientCall = serviceClientCall;
    }

    public Fare getFare(String originCode,String destinationCode,String currency)
    {
        String token  =  oAuthStrategy.getToken();
        Map<String,String> headerMap = new HashMap<>();
        headerMap.put("Authorization",token);
        MultiValueMap<String, String> parameterMap = new LinkedMultiValueMap<String, String>();
        if(currency!=null)
            parameterMap.add("currency",currency);
        return serviceClientCall.getRequest(serverUrl+faresPathPath+"/"+originCode+"/"+destinationCode,headerMap,parameterMap,Fare.class);

    }
    public Fare getFareWithDetail(String originCode,String destinationCode,String currency) throws ApplicationException {
        CompletableFuture<Fare> fareCompletableFuture =
                CompletableFuture.supplyAsync(() -> getFare(originCode,destinationCode,currency));
        CompletableFuture<Location> originLocationCompletableFuture =
                CompletableFuture.supplyAsync(() -> locationService.getLocation(originCode,null));
        CompletableFuture<Location> destinationLocationCompletableFuture =
                CompletableFuture.supplyAsync(() -> locationService.getLocation(destinationCode,null));
        Fare fare = null;
        try {
            fare = fareCompletableFuture.get();

        fare.setOriginLocation(originLocationCompletableFuture.get());
        fare.setDestinationLocation(destinationLocationCompletableFuture.get());
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new ApplicationException("An exception happened in service calls");
        }
        return fare;
    }


}
