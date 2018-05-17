package iman.reserve.hotel;

/**
 * Created by Lincoln on 18/05/16.
 */
public class Hotel {
    private String name;
    private int numOfStars;
    private int thumbnail;
    private String desc;

    public Hotel() {
    }

    public Hotel(String name, int numOfStars, int thumbnail,String desc) {
        this.name = name;
        this.numOfStars = numOfStars;
        this.thumbnail = thumbnail;
        this.desc = desc;
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
        return desc;
    }

    public void setDesc(String name) {
        this.name = name;
    }

}
