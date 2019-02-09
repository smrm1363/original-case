package com.afkl.cases.df.fare;

import com.afkl.cases.df.airPort.Location;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class Fare implements Serializable {


    private Double amount;
    private String currency;
    private String origin;
    private String destination;
    @JsonIgnore
    private Location originLocation;
    @JsonIgnore
    private Location destinationLocation;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Location getOrigin() {
        return originLocation;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Location getDestination() {
        return destinationLocation;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Location getOriginLocation() {
        return originLocation;
    }

    public void setOriginLocation(Location originLocation) {
        this.originLocation = originLocation;
    }

    public Location getDestinationLocation() {
        return destinationLocation;
    }

    public void setDestinationLocation(Location destinationLocation) {
        this.destinationLocation = destinationLocation;
    }
}
