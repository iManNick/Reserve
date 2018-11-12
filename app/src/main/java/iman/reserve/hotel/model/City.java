package iman.reserve.hotel.model;


public class City {
    private String name;
    private int numOfHotels;
    private int thumbnail;

    public City() {
    }

    public City(String name, int numOfHotels, int thumbnail) {
        this.name = name;
        this.numOfHotels = numOfHotels;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfHotels() {
        return numOfHotels;
    }

    public void setNumOfHotels(int numOfHotels) {
        this.numOfHotels = numOfHotels;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
