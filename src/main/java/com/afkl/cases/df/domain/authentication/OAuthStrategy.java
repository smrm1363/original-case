package com.afkl.cases.df.domain.authentication;

import com.afkl.cases.df.RestTemplateComponent;
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

    private final RestTemplateComponent restTemplateComponent;
    private String token;
    private long tokenExpiresAt = 0l;

    @Autowired
    public OAuthStrategy(RestTemplateComponent restTemplateComponent) {
        this.restTemplateComponent = restTemplateComponent;
    }

    @Override
    public synchronized String getToken() {
        if(System.currentTimeMillis()>tokenExpiresAt)
        {
//            OAuthDto oAuthDto = restTemplateComponent.getRestTemplate().postForObject()

//            JSONObject tokenObject = Unirest.post(simpleTravelApiUrl + "/oauth/token")
//                    .header("accept", "application/json")
//                    .header("content-type", "application/x-www-form-urlencoded")
//                    .basicAuth("travel-api-client", "psw")
//                    .queryString("grant_type", "client_credentials")
//                    .queryString("username", username)
//                    .queryString("password", password).asJson().getBody().getObject();
//
//            token = tokenObject.get("access_token").toString();
//            tokenExpiresAtMillis = System.currentTimeMillis() + tokenObject.getInt("expires_in") * 1000;
//
//            logger.debug("Auth token received " + token);

            RestTemplate template = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            
            MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
            map.add("grant_type", "client_credentials");
            map.add("username", username);
            map.add("password", password);
            String auth = username + ":" + password;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String( encodedAuth );
            headers.set("Authorization",authHeader);


            HttpEntity<MultiValueMap<String, String>> requestEntity=
                    new HttpEntity<MultiValueMap<String, String>>(map, headers);

            try{
                OAuthDto response = template.postForObject(serverUrl+oAuthPath, requestEntity,  OAuthDto.class);
                token = response.getTokenType()+" "+response.getAccessToken();
                tokenExpiresAt = response.getExpiresIn();
            }
            catch(Exception e){
                token = e.getMessage();
            }


        }
        return token;
    }

//    protected getRestTempla
//    protected Json getTockenJsonObject
}
