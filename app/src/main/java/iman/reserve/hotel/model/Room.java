package iman.reserve.hotel.model;

import com.google.gson.annotations.SerializedName;

public class Room {
    @SerializedName("h_id")
    private String h_id;

    @SerializedName("type")
    private String type;
    @SerializedName("food")
    private String food;
    @SerializedName("price")
    private String price;
    @SerializedName("avroom")
    private String avroom;
    @SerializedName("size")
    private String size;
    @SerializedName("totroom")
    private String totroom;

    public Room(String type, String food, String price, String avroom, String size) {
        this.type = type;
        this.food = food;
        this.price = price;
        this.avroom = avroom;
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAvroom() {
        return avroom;
    }

    public void setAvroom(String avroom) {
        this.avroom = avroom;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

}
