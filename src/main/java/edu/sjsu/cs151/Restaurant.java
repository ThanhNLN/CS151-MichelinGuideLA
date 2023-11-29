package edu.sjsu.cs151;

public class Restaurant {
    private String name;
    private String url;
    private String location;
    private String cuisine;
    private String address;

    public Restaurant(String name, String url, String location, String cusine, String address) {
        this.name = name;
        this.url = url;
        this.location = location;
        this.cuisine = cusine;
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
                ", cusine='" + cuisine + '\'' +
                ", address='" + address + '\'' +
                '}';
    }


}
