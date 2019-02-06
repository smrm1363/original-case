package com.afkl.cases.df.fare;

import com.afkl.cases.df.domain.authentication.OAuthStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FareService {
private final OAuthStrategy oAuthStrategy;

    @Autowired
    public FareService(OAuthStrategy oAuthStrategy) {
        this.oAuthStrategy = oAuthStrategy;
    }

    public Fare getFare(String originCode,String destinationCode)
    {

    }


}
