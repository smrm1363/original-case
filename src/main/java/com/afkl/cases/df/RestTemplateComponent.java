package com.afkl.cases.df;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateComponent {
    public RestTemplate getRestTemplate()
    {
        return new RestTemplate();
    }
}
