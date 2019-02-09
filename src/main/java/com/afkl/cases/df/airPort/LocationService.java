package com.afkl.cases.df.airPort;

import com.afkl.cases.df.airPort.Location;
import com.afkl.cases.df.domain.authentication.OAuthStrategy;
import com.afkl.cases.df.util.ServiceClientCall;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

@Service
public class LocationService {
    private final OAuthStrategy oAuthStrategy;
    private final ServiceClientCall serviceClientCall;
    @Value("${location.path}")
    private String locationPathPath;

    @Value("${server.url}")
    private String serverUrl;

    public LocationService(OAuthStrategy oAuthStrategy, ServiceClientCall serviceClientCall) {
        this.oAuthStrategy = oAuthStrategy;
        this.serviceClientCall = serviceClientCall;
    }

    public Location getLocation(String locationCode, String lang) {

        String token  =  oAuthStrategy.getToken();


        MultiValueMap<String, String> parameterMap = new LinkedMultiValueMap<String, String>();
        if(lang!=null)
            parameterMap.add("lang",lang);
        Map<String,String> headerMap = new HashMap<>();
        headerMap.put("Authorization",token);
        return serviceClientCall.getRequest(serverUrl+locationPathPath+"/"+locationCode,headerMap,parameterMap,Location.class);
    }

    public String findLocation(String term, String lang) {

        String token  =  oAuthStrategy.getToken();
        MultiValueMap<String, String> parameterMap = new LinkedMultiValueMap<String, String>();
        if(term!=null)
            parameterMap.add("term",term);
        if(lang!=null)
            parameterMap.add("lang",lang);
        Map<String,String> headerMap = new HashMap<>();
        headerMap.put("Authorization",token);
        return serviceClientCall.getRequest(serverUrl+locationPathPath,headerMap,parameterMap,String.class);
    }
}
