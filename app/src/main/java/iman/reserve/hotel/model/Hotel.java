package iman.reserve.hotel.model;


import com.google.gson.annotations.SerializedName;

public class Hotel {
    private int thumbnail;

    @SerializedName("city")
    private String city;
    @SerializedName("name")
    private String name;
    @SerializedName("numOfStars")
    private int numOfStars;

    @SerializedName("descr")
    private String descr;
    @SerializedName("h_id")
    private int h_id;

    public Hotel() {
    }

    public Hotel(int h_id, String name, int numOfStars, int thumbnail,String descr) {
        this.name = name;
        this.numOfStars = numOfStars;
        this.thumbnail = thumbnail;
        this.descr = descr;
        this.h_id = h_id;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfHotels() {
        return numOfStars;
    }

    public void setNumOfStars(int numOfHotels) {
        this.numOfStars = numOfHotels;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDesc() {
        return descr;
    }

    public void setDesc(String name) {
        this.name = name;
    }

    public int getHotelID() {
        return h_id;
    }

    public void setHotelID(int h_id) {
        this.h_id = h_id;
    }

}
