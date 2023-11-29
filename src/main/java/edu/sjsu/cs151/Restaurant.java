package edu.sjsu.cs151;

public class Restaurant {
    private String name;
    private String url;
    private String location;
    private String cost;
    private String cuisine;
    private String address;


    public Restaurant(String name, String url, String location, String cost, String cuisine, String address) {
        this.name = name;
        this.url = url;
        this.location = location;
        this.cost = cost;
        this.cuisine = cuisine;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", location='" + location + '\'' +
                ", cuisine='" + cuisine + '\'' +
                ", address='" + address + '\'' +
                '}';
    }


}
