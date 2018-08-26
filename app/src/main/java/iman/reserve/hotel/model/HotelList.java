
package iman.reserve.hotel.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HotelList {
    @SerializedName("hotelList")
    private ArrayList<Hotel> hotelList;

    public ArrayList<Hotel> getHotelArrayList() {
        return hotelList;
    }

    public void setHotelArrayList(ArrayList<Hotel> hotelArrayList) {
        this.hotelList = hotelArrayList;
    }
}
