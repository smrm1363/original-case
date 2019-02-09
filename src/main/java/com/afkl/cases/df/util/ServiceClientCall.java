package com.afkl.cases.df.util;

import com.afkl.cases.df.domain.authentication.OAuthDto;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
public class ServiceClientCall {

    public <T extends Serializable> T postRequest(String servicePath, Map<String,String> headersMap, MultiValueMap<String, String> entityParamMap, Class<OAuthDto> tClass)
    {
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = generateHeders(headersMap);
        HttpEntity<MultiValueMap<String, String>> requestEntity=
                new HttpEntity<MultiValueMap<String, String>>(entityParamMap, headers);
            return (T) template.postForObject(servicePath, requestEntity,tClass);

    }

    public <T extends Serializable> T getRequest(String servicePath, Map<String,String> headersMap, MultiValueMap<String, String> entityParamMap, Class<T> tClass)
    {
        RestTemplate template = new RestTemplate();
        HttpEntity headers = new HttpEntity(generateHeders(headersMap));
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(servicePath).queryParams(entityParamMap);

        ResponseEntity response = template.exchange(
                uriBuilder.toUriString(), HttpMethod.GET, headers, tClass);
        return (T) response.getBody();
    }
    private HttpHeaders generateHeders(Map<String,String> headersMap)
    {
        HttpHeaders headers = new HttpHeaders();
        headersMap.entrySet().forEach(headersMapEntry ->
        {
            headers.set(headersMapEntry.getKey(),headersMapEntry.getValue());
        });
        return headers;
    }
}
