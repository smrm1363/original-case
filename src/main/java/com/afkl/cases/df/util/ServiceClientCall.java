package com.afkl.cases.df.util;

import com.afkl.cases.df.domain.authentication.OAuthDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Map;

@Component
public class ServiceClientCall {
    public <T extends Serializable> T postRequest(String servicePath,Map<String,String> headersMap,LinkedMultiValueMap<String,String> entityParamMap)
    {

        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);


//        MultiValueMap<String, String> mapEntity = new LinkedMultiValueMap<String, String>();
//        mapEntity.add("grant_type", "client_credentials");
//        mapEntity.add("username", username);
//        mapEntity.add("password", password);
        String auth = username + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(Charset.forName("US-ASCII")) );
        String authHeader = "Basic " + new String( encodedAuth );
        headersMap.entrySet().forEach(headersMapEntry ->
        {
            headers.set(headersMapEntry.getKey(),headersMapEntry.getValue());
        });
//        headers.set("Authorization",authHeader);


        HttpEntity<MultiValueMap<String, String>> requestEntity=
                new HttpEntity<MultiValueMap<String, String>>(entityParamMap, headers);

        try{
            T response = template.postForObject(servicePath, requestEntity,Class<T>.getClass());
            token = response.getTokenType()+" "+response.getAccessToken();
            tokenExpiresAt = response.getExpiresIn();
        }
    }
}
