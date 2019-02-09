package com.afkl.cases.df.domain.authentication;

import com.afkl.cases.df.RestTemplateComponent;
import com.afkl.cases.df.util.ServiceClientCall;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
public class OAuthStrategy implements AuthenticationStrategy {
    private Logger logger = LogManager.getLogger(OAuthStrategy.class);

    @Value("${OAuth.username}")
    private String username;

    @Value("${OAuth.password}")
    private String password;

    @Value("${server.url}")
    private String serverUrl;

    @Value("${OAuth.path}")
    private String oAuthPath;

    private final ServiceClientCall serviceClientCall;
    private String token;
    private long tokenExpiresAt = 0l;

    @Autowired
    public OAuthStrategy(RestTemplateComponent restTemplateComponent, ServiceClientCall serviceClientCall) {
        this.serviceClientCall = serviceClientCall;
    }
        @Override
    public synchronized String getToken() {
        if(System.currentTimeMillis()>tokenExpiresAt)
        {

            MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
            map.add("grant_type", "client_credentials");
            map.add("username", username);
            map.add("password", password);
            String auth = username + ":" + password;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String( encodedAuth );
            Map<String ,String > headerMap = new HashMap<>();
            headerMap.put("content-type","application/x-www-form-urlencoded");
            headerMap.put("Authorization",authHeader);
            try{
                CompletableFuture<OAuthDto> oAuthDtoCompletableFuture =
                        CompletableFuture.supplyAsync(() -> serviceClientCall.postRequest(serverUrl+oAuthPath,headerMap,map,OAuthDto.class));
                OAuthDto response = oAuthDtoCompletableFuture.get();
                token =response.getTokenType()+" "+response.getAccessToken();
                tokenExpiresAt = response.getExpiresIn();
            }
            catch(Exception e){
                token = e.getMessage();
            }


        }
        return token;
    }
}
