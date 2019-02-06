package com.afkl.cases.df.fare;

public class Location {
    private String code;
    private String name;
    private String description;
    Coordinate coordinate;
    Location ParentObject;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Location getParentObject() {
        return ParentObject;
    }

    public void setParentObject(Location parentObject) {
        ParentObject = parentObject;
    }
}
